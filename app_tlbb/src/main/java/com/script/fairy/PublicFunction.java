package com.script.fairy;

/**
 * Created by Administrator on 2018/3/12.
 */


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.ScreenInfo;
import com.script.opencvapi.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class PublicFunction {
    private static final int MIN_TIME = 200;
    private int w;
    private int h;
    private static long mLastCaptureTime;
    private Map<String, Mat> mTemplateMap = new HashMap();
    private ScreenInfo screenInfo;
    byte[] b = null;
    private AtFairy2.OpencvResult result;
    private Context mContext;
    private Random rand = new Random();
    public AtFairyImpl mFairy;
//    private ActivityManager activityManager;
    public PublicFunction(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        result = mFairy.new OpencvResult();
        mContext = mFairy.getContext();
    }

    public AtFairy2.OpencvResult localGrayFindPic(int x_1, int y_1, int x_2, int y_2, String picName) {
        //本地灰度化找图
        w = x_2 - x_1;
        h = y_2 - y_1;
        //b = Tlbb.getInstance().GetCaptureRaw();
//        b = mFairy.captureInterval().raw;
        cap();
        String[] picNameArr = picName.split("\\|");

        AtFairy2.OpencvResult maxResult = mFairy.new OpencvResult();
        // LtLog.i(getLineInfo() +  "--------------------------SingleTask--localFindPic--w>" + w);
        //  LtLog.i(getLineInfo() +  "--------------------------SingleTask--localFindPic--h>" + h);
        if (x_1 + w <= screenInfo.width && y_1 + h <= screenInfo.height) {
            for (int i = 0; i < picNameArr.length; i++) {
                AtFairy2.OpencvResult result = mFairy.new OpencvResult();

                Mat dst;
                Mat dst1 = new Mat();

//        Mat img = Imgcodecs.imread("/sdcard/png/" + picName);
//        Imgcodecs.imwrite("/sdcard/771.png", img);

                Bitmap map = getImageFromAssetsFile(picNameArr[i]);
                Mat img = new Mat();
                org.opencv.android.Utils.bitmapToMat(map, img);
                Imgproc.cvtColor(img, img, Imgproc.COLOR_BGRA2RGB);

                Rect roi = new Rect(x_1, y_1, w, h);
                Mat img1 = new Mat(720, 1280, CvType.CV_8UC4);
                img1.put(0, 0, b);
                Imgproc.cvtColor(img1, img1, Imgproc.COLOR_BGR2RGB);
                dst = new Mat(img1, roi);
                Imgproc.cvtColor(dst, dst, Imgproc.COLOR_RGB2GRAY);
                Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2GRAY);
//                Imgproc.cvtColor(dst, dst, Imgproc.COLOR_RGB2HLS);
//                Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2HLS);


                Imgproc.matchTemplate(img, dst, dst1, Imgproc.TM_CCOEFF_NORMED);

        /*1、cv::TM_SQDIFF：该方法使用平方差进行匹配，因此最佳的匹配结果在结果为0处，值越大匹配结果越差。
        2、cv::TM_SQDIFF_NORMED：该方法使用归一化的平方差进行匹配，最佳匹配也在结果为0处。
        3、cv::TM_CCORR：相关性匹配方法，该方法使用源图像与模板图像的卷积结果进行匹配，因此，最佳匹配位置在值最大处，值越小匹配结果越差。
        4、cv::TM_CCORR_NORMED：归一化的相关性匹配方法，与相关性匹配方法类似，最佳匹配位置也是在值最大处。
        5、cv::TM_CCOEFF：相关性系数匹配方法，该方法使用源图像与其均值的差、模板与其均值的差二者之间的相关性进行匹配，最佳匹配结果在值等于1处，最差匹配结果在值等于-1处，值等于0直接表示二者不相关。
        6、cv::TM_CCOEFF_NORMED：归一化的相关性系数匹配方法，正值表示匹配的结果较好，负值则表示匹配的效果较差，也是值越大，匹配效果也好。
        */
                Core.MinMaxLocResult mmr;
                mmr = Core.minMaxLoc(dst1);
                result.sim = (float) mmr.maxVal;
                //LtLog.i(getLineInfo() +  "--------------------------result.sim"+result.sim);
                result.x = (int) mmr.maxLoc.x + x_1;
                result.y = (int) mmr.maxLoc.y + y_1;
//                LtLog.i(getLineInfo() +  "--------------------------SingleTask--MyFindPic--mmr.maxVal=>" + mmr.maxVal + ","+result.x + ","+result.y + "---mmr.minVal=" + mmr.minVal + "---mmr.minLoc=" + (mmr.minLoc.x + x_1) + "," + (mmr.minLoc.y + y_1));
                LtLog.i(getLineInfo() + "--------------------------result=" + result + ",maxResult=" + maxResult);
                if (result.sim > maxResult.sim) {
                    LtLog.i(getLineInfo() + "--------------------------result=" + result + ",maxResult=" + maxResult);
//                    maxResult.sim = (float) mmr.maxVal;
//                    maxResult.x = (int) mmr.maxLoc.x + x_1;
//                    maxResult.y = (int) mmr.maxLoc.y + y_1;
                    maxResult = result;
//                    LtLog.i(getLineInfo() +  "--------------------------result="+result + ",maxResult=" + maxResult);
                }
            }
            return maxResult;
        }
        return returnResult();
    }

    public AtFairy2.OpencvResult localToValueFindPic(int x_1, int y_1, int x_2, int y_2, double thresh, double maxval, int thresholdType, String picName) {
        //本地二值化找图
        w = x_2 - x_1;
        h = y_2 - y_1;
        AtFairy2.OpencvResult maxResult = returnResult();
        cap();
        String[] picNameArr = picName.split("\\|");
//        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--img>" + picName + ",picNameArr.length=" + picNameArr.length);
        if (x_1 + w <= screenInfo.width && y_1 + h <= screenInfo.height && b != null) {
            for (int i = 0; i < picNameArr.length; i++) {
                AtFairy2.OpencvResult result = mFairy.new OpencvResult();
                Mat dst;
                Mat dst1 = new Mat();
//            Mat img = Imgcodecs.imread("/sdcard/png/" + picName);
//                LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--img>" + picNameArr[i]);
                Bitmap map = getImageFromAssetsFile(picNameArr[i]);
                Mat img = new Mat();
                org.opencv.android.Utils.bitmapToMat(map, img);
                Imgproc.cvtColor(img, img, Imgproc.COLOR_BGRA2RGB);
                // Imgcodecs.imwrite("/sdcard/771.png", img);
        /*byte[] bb=Tlbb.getInstance().getTemplateData(picName);
       Mat img=new Mat();
        img.put(0,0,bb);
        LtLog.i(getLineInfo() +  "--------------------------SingleTask--MyFindPic--img>"+img);
        */
                Rect roi = new Rect(x_1, y_1, w, h);
                Mat img1 = new Mat(720, 1280, CvType.CV_8UC4);
                img1.put(0, 0, b);
                // LtLog.i(getLineInfo() +  "--------------------------PublicFunction--localToValueFindPic--img1"+img1);
                Imgproc.cvtColor(img1, img1, Imgproc.COLOR_BGR2RGB);
                //  Imgcodecs.imwrite("/sdcard/77.png", img1);
                dst = new Mat(img1, roi);
                //  Imgcodecs.imwrite("/sdcard/772.png", dst);

                Imgproc.cvtColor(dst, dst, Imgproc.COLOR_RGB2GRAY);
                Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2GRAY);

                Imgproc.threshold(dst, dst, thresh, maxval, thresholdType);
                Imgproc.threshold(img, img, thresh, maxval, thresholdType);

                // Imgcodecs.imwrite("/sdcard/2772.png", dst);
                // Imgcodecs.imwrite("/sdcard/277.png", img);
        /*第一个参数，InputArray类型的src，输入数组，填单通道 , 8或32位浮点类型的Mat即可。

            第二个参数，OutputArray类型的dst，函数调用后的运算结果存在这里，即这个参数用于存放输出结果，且和第一个参数中的Mat变量有一样的尺寸和类型。

            第三个参数，double类型的thresh，阈值的具体值。

            第四个参数，double类型的maxval，当第五个参数阈值类型type取 THRESH_BINARY 或THRESH_BINARY_INV阈值类型时的最大值.
            0: THRESH_BINARY  当前点值大于阈值时，取Maxval,也就是第四个参数，下面再不说明，否则设置为0

            1: THRESH_BINARY_INV 当前点值大于阈值时，设置为0，否则设置为Maxval

            2: THRESH_TRUNC 当前点值大于阈值时，设置为阈值，否则不改变

            3: THRESH_TOZERO 当前点值大于阈值时，不改变，否则设置为0
            4: THRESH_TOZERO_INV  当前点值大于阈值时，设置为0，否则不改变*/

                Imgproc.matchTemplate(img, dst, dst1, Imgproc.TM_CCOEFF_NORMED);
                Core.MinMaxLocResult mmr;
                mmr = Core.minMaxLoc(dst1);
                result.sim = (float) mmr.maxVal;
                //LtLog.i(getLineInfo() +  "--------------------------result.sim"+result.sim);
                result.x = (int) mmr.maxLoc.x + x_1;
                result.y = (int) mmr.maxLoc.y + y_1;
                //LtLog.i(getLineInfo() +  "--------------------------SingleTask--MyFindPic--mmr.maxVal=>" + mmr.maxVal + ","+result.x + ","+result.y + "---mmr.minVal=" + mmr.minVal + "---mmr.minLoc=" + (mmr.minLoc.x + x_1) + "," + (mmr.minLoc.y + y_1));
//            return result;
                if (result.sim > maxResult.sim) {
//                    LtLog.i(getLineInfo() + "--------------------------result=" + result + ",maxResult=" + maxResult);
                    maxResult.sim = (float) mmr.maxVal;
                    maxResult.x = (int) mmr.maxLoc.x + x_1;
                    maxResult.y = (int) mmr.maxLoc.y + y_1;
//                    maxResult = result;

                }
                dst1.release();
                img.release();
                img1.release();
                dst.release();
            }
//            LtLog.i(getLineInfo() + "--------------------------" + ",maxResult=" + maxResult);
            return maxResult;
        } else {
            return returnResult();
        }
    }

    public AtFairy2.OpencvResult localFindPic(int x_1, int y_1, int x_2, int y_2, String picName) {
        return localFindPicRGBOrHSVOrHLS(x_1, y_1, x_2, y_2, picName, "RGB");
        //本地找图
//        w = x_2 - x_1;
//        h = y_2 - y_1;
//        //result = mFairy.new OpencvResult();
//        cap();
//        if (x_1 + w <= screenInfo.width && y_1 + h <= screenInfo.height) {
//            Mat dst, img1;
//            Mat dst1 = new Mat();
//            Mat img = Imgcodecs.imread("/sdcard/png/" + picName);
//            Rect roi = new Rect(x_1, y_1, w, h);
//            LtLog.i(getLineInfo() + "--------------------------height=" + screenInfo.height + ",screenInfo.width=" + screenInfo.width);
//            img1 = new Mat(screenInfo.height, screenInfo.width, CvType.CV_8UC4);
//            img1.put(0, 0, b);
//            Imgproc.cvtColor(img1, img1, Imgproc.COLOR_BGR2RGB);
//            dst = new Mat(img1, roi);
//            Imgproc.matchTemplate(dst, img, dst1, Imgproc.TM_CCOEFF_NORMED);
//            Core.MinMaxLocResult mmr;
//            mmr = Core.minMaxLoc(dst1);
//            result.sim = (float) mmr.maxVal;
//            result.x = (int) mmr.maxLoc.x + x_1;
//            result.y = (int) mmr.maxLoc.y + y_1;
//            return result;
//        } else {
//            return returnResult();
//        }
    }

    private AtFairy2.OpencvResult localFindPicRGBOrHSVOrHLS(int x_1, int y_1, int x_2, int y_2, String picName, String model) {
        //本地找图
        w = x_2 - x_1;
        h = y_2 - y_1;
        AtFairy2.OpencvResult maxResult = returnResult();
        cap();
        String[] picNameArr = picName.split("\\|");

//        LtLog.i(getLineInfo() + "--------------------------height=" + screenInfo.height + ",screenInfo.width=" + screenInfo.width);
//        LtLog.i(getLineInfo() + "--------------------------x_1 + w=" + (x_1 + w) + ",y_1 + h=" + (y_1 + h));
        if (x_1 + w <= this.screenInfo.width && y_1 + h <= this.screenInfo.height) {
            for (int i = 0; i < picNameArr.length; i++) {
                AtFairy2.OpencvResult result = mFairy.new OpencvResult();
                Mat dst, img1;
                Mat dst1 = new Mat();
//                Mat img = Imgcodecs.imread("/sdcard/png/" + picNameArr[i]);
                Bitmap map = getImageFromAssetsFile(picNameArr[i]);
                Mat img = new Mat();
                org.opencv.android.Utils.bitmapToMat(map, img);
                Imgproc.cvtColor(img, img, Imgproc.COLOR_BGRA2RGB);

                Rect roi = new Rect(x_1, y_1, w, h);
//            LtLog.i(getLineInfo() + "--------------------------height=" + screenInfo.height + ",screenInfo.width=" + screenInfo.width);
                img1 = new Mat(screenInfo.height, screenInfo.width, CvType.CV_8UC4);
                try {
                    img1.put(0, 0, this.b);
                } catch (Exception e) {
                    LtLog.i("this.b==================is null");
                    return returnResult();
                }
                Imgproc.cvtColor(img1, img1, Imgproc.COLOR_BGR2RGB);

//                img1=Imgcodecs.imread("/sdcard/18.png");
//                LtLog.i(getLineInfo() + "--------------------------height=" + img1 + ",screenInfo.width=" + screenInfo.width);
//                Imgcodecs.imwrite("/sdcard/18.png",img1);
//Utils.sleep(100000000);
                dst = new Mat(img1, roi);

                if (model.equals("HSV")) {
                    Imgproc.cvtColor(img1, img1, Imgproc.COLOR_RGB2HSV);
                    Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2HSV);
                } else if (model.equals("HLS")) {
                    Imgproc.cvtColor(img1, img1, Imgproc.COLOR_RGB2HLS);
                    Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2HLS);
                }
//                LtLog.i(getLineInfo() +  "--------------------");
                Imgproc.matchTemplate(dst, img, dst1, Imgproc.TM_CCOEFF_NORMED);
                Core.MinMaxLocResult mmr;
                mmr = Core.minMaxLoc(dst1);
                result.sim = (float) mmr.maxVal;
                result.x = (int) mmr.maxLoc.x + x_1;
                result.y = (int) mmr.maxLoc.y + y_1;

//                LtLog.i(getLineInfo() +  "--------------------------result="+result + ",maxResult=" + maxResult);
                if (result.sim > maxResult.sim) {
                    maxResult = result;
//                    LtLog.i(getLineInfo() +  "--------------------------result="+result + ",maxResult=" + maxResult);
                }
                img1.release();
                img.release();
                dst1.release();
                dst.release();
            }
//            LtLog.i(getLineInfo() +  "--------------------------result="+result + ",maxResult=" + maxResult);

            return maxResult;
        }
        return returnResult();
    }

    public AtFairy2.OpencvResult localFindPicHSV(int x_1, int y_1, int x_2, int y_2, String picName) {
        return localFindPicRGBOrHSVOrHLS(x_1, y_1, x_2, y_2, picName, "HSV");

//        w = x_2 - x_1;
//        h = y_2 - y_1;
//        //AtFairy2.OpencvResult result = mFairy.new OpencvResult();
//        //   AtFairy2.RawScreenInfo rawScreenInfo = mFairy.GetCaptureRaw();
//        result = mFairy.new OpencvResult();
//        //rawScreenInfo = mFairy.GetCaptureRaw();
//        b = mFairy.captureInterval().raw;
//        Mat dst;
//        Mat dst1 = new Mat();
//        Mat img = Imgcodecs.imread("/sdcard/png/" + picName);
//        Rect roi = new Rect(x_1, y_1, w, h);
//        Mat img1 = new Mat(720, 1280, CvType.CV_8UC4);
//        img1.put(0, 0, b);
//        Imgproc.cvtColor(img1, img1, Imgproc.COLOR_BGR2RGB);
//        Imgproc.cvtColor(img1, img1, Imgproc.COLOR_RGB2HSV);
//        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2HSV);
//
//        dst = new Mat(img1, roi);
//        // LtLog.i(getLineInfo() +  "--------------------------img="+img + ",dst=" + dst);
//        Imgproc.matchTemplate(dst, img, dst1, Imgproc.TM_CCOEFF_NORMED);
//        Core.MinMaxLocResult mmr;
//        mmr = Core.minMaxLoc(dst1);
//        result.sim = (float) mmr.maxVal;
//        //LtLog.i(getLineInfo() +  "--------------------------result.sim"+result.sim);
//        result.x = (int) mmr.maxLoc.x + x_1;
//        result.y = (int) mmr.maxLoc.y + y_1;
//        //LtLog.i(getLineInfo() +  "--------------------------SingleTask--MyFindPic--mmr.maxVal=>" + mmr.maxVal + ","+result.x + ","+result.y + "---mmr.minVal=" + mmr.minVal + "---mmr.minLoc=" + (mmr.minLoc.x + x_1) + "," + (mmr.minLoc.y + y_1));
//        return result;
//

    }

    public AtFairy2.OpencvResult localFindPicHLS(int x_1, int y_1, int x_2, int y_2, String picName) {
        return localFindPicRGBOrHSVOrHLS(x_1, y_1, x_2, y_2, picName, "HLS");
        //本地找图
//        w = x_2 - x_1;
//        h = y_2 - y_1;
//        //AtFairy2.OpencvResult result = mFairy.new OpencvResult();
//        //   AtFairy2.RawScreenInfo rawScreenInfo = mFairy.GetCaptureRaw();
//        result = mFairy.new OpencvResult();
//        //rawScreenInfo = mFairy.GetCaptureRaw();
//        b = mFairy.captureInterval().raw;
//        //LtLog.i(getLineInfo() +  "--------------------------b.length"+b.length + ",b[0]=" + b[0] + ",picName=" + picName);
//        // b = rawScreenInfo.raw;
//        Mat dst;
//        Mat dst1 = new Mat();
//        Mat img = Imgcodecs.imread("/sdcard/png/" + picName);
//        Rect roi = new Rect(x_1, y_1, w, h);
//        Mat img1 = new Mat(720, 1280, CvType.CV_8UC4);
//        img1.put(0, 0, b);
//        Imgproc.cvtColor(img1, img1, Imgproc.COLOR_BGR2RGB);
//        Imgproc.cvtColor(img1, img1, Imgproc.COLOR_RGB2HLS);
//        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2HLS);
//
//        dst = new Mat(img1, roi);
//        // LtLog.i(getLineInfo() +  "--------------------------img="+img + ",dst=" + dst);
//        Imgproc.matchTemplate(dst, img, dst1, Imgproc.TM_CCOEFF_NORMED);
//        Core.MinMaxLocResult mmr;
//        mmr = Core.minMaxLoc(dst1);
//        result.sim = (float) mmr.maxVal;
//        //LtLog.i(getLineInfo() +  "--------------------------result.sim"+result.sim);
//        result.x = (int) mmr.maxLoc.x + x_1;
//        result.y = (int) mmr.maxLoc.y + y_1;
//        //LtLog.i(getLineInfo() +  "--------------------------SingleTask--MyFindPic--mmr.maxVal=>" + mmr.maxVal + ","+result.x + ","+result.y + "---mmr.minVal=" + mmr.minVal + "---mmr.minLoc=" + (mmr.minLoc.x + x_1) + "," + (mmr.minLoc.y + y_1));
//        return result;

    }

    public AtFairy2.OpencvResult localOptimalFindPic(int x_1, int y_1, int x_2, int y_2, double thresh, double maxval, int thresholdType, String picName) {
        //本地 返回 二值化和原图 最大相似度
        List list = new ArrayList<>();
        AtFairy2.OpencvResult result, result1, result2, result3;
        result = localToValueFindPic(x_1, y_1, x_2, y_2, thresh, maxval, thresholdType, picName);
        result1 = localFindPic(x_1, y_1, x_2, y_2, picName);
        result2 = localFindPicHSV(x_1, y_1, x_2, y_2, picName);
        result3 = localFindPicHLS(x_1, y_1, x_2, y_2, picName);
//        LtLog.i(getLineInfo() + "----------------------------result=>" + result + ",result1=" + result1 + ",result2=" + result2 + ",result3=" + result3);
        list.add(new Double(result.sim));
        list.add(new Double(result1.sim));
        list.add(new Double(result2.sim));
        list.add(new Double(result3.sim));
        double max = (double) Collections.max(list);
//        LtLog.i(getLineInfo() + "---------------------------->" + Collections.max(list) + ",result=" + result + ",result1=" + result1 + ",result2=" + result2 + " ,result3=" + result3);
        if (result.sim == max) {
            return result;
        } else if (result1.sim == max) {
            return result1;
        } else if (result2.sim == max) {
            return result2;
        } else if (result3.sim == max) {
            return result3;
        }
        return result;
    }

    public AtFairy2.OpencvResult localFindManyPic(int x_1, int y_1, int x_2, int y_2, String[] picName) {
        //本地同区域找多图
        AtFairy2.OpencvResult result, result1;
        result1 = localFindPic(x_1, y_1, x_2, y_2, picName[0]);
        // LtLog.i(getLineInfo() +  "--------------------------PublicFunction--localFindManyPic-picName-" + picName[0] + ",sim=" + result1.sim);
        for (int i = 0; i < picName.length; i++) {
            result = localFindPic(x_1, y_1, x_2, y_2, picName[i]);
            if (result.sim > result1.sim && result.sim > 0.8) {
                LtLog.i(getLineInfo() + "--------------------------PublicFunction--localFindManyPic-picName-" + picName[i] + ",sim=" + result.sim);
                result1 = result;
            }
        }
        return result1;
    }

    public AtFairy2.OpencvResult localToValueFindManyPic(int x_1, int y_1, int x_2, int y_2, double thresh, double maxval, int thresholdType, String[] picName) {
        //本地同区域找多图
        AtFairy2.OpencvResult result, result1;
        result1 = localToValueFindPic(x_1, y_1, x_2, y_2, thresh, maxval, thresholdType, picName[0]);
        //    LtLog.i(getLineInfo() +  "--------------------------PublicFunction--localFindManyPic-picName-" + picName[0] + ",sim=" + result1.sim);
        for (int i = 0; i < picName.length; i++) {
            result = localToValueFindPic(x_1, y_1, x_2, y_2, thresh, maxval, thresholdType, picName[i]);
            if (result.sim > result1.sim && result.sim > 0.8) {
                LtLog.i(getLineInfo() + "--------------------------PublicFunction--localFindManyPic-picName-" + picName[i] + ",sim=" + result.sim);
                result1 = result;
            }
        }
        return result1;
    }

    public Mat returnToValueMat(Mat mat, double thresh, double maxval, int thresholdType) {
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY);
        Imgproc.threshold(mat, mat, thresh, maxval, thresholdType);
        return mat;
    }

    public void localMultiRegionFindManyPic(List<String> list_Region, List<String> list_picName)throws Exception {
        //多区域 找多图  只做点击
        /*       List<String> listRegion = new ArrayList<>();
        List<String> listPic = new ArrayList<>();
        listRegion.add("607,367,676,414");
        listPic.add("examination1.png");
        localMultiRegionFindManyPic(listRegion,listPic)
        */
        AtFairy2.OpencvResult result;
        for (int i = 0; i < list_Region.size(); i++) {
            int x1 = Integer.parseInt(list_Region.get(i).split(",")[0]);
            int y1 = Integer.parseInt(list_Region.get(i).split(",")[1]);
            int x2 = Integer.parseInt(list_Region.get(i).split(",")[2]);
            int y2 = Integer.parseInt(list_Region.get(i).split(",")[3]);
            result = localFindPic(x1, y1, x2, y2, list_picName.get(i));
            if (result.sim > 0.8) {
                LtLog.i(getLineInfo() + "--------------------------PublicFunction--localMultiRegionFindManyPic>" + result);
                rndTap(result.x, result.y, result.x + 1, result.y + 1);
                Utils.sleep(500);
            }
        }
    }

    public AtFairy2.OpencvResult localMultiRegionFindManyPic1(List<String> list_Region, List<String> list_picName) {
        //多区域 找多图  不点击
        /*       List<String> listRegion = new ArrayList<>();
        List<String> listPic = new ArrayList<>();
        listRegion.add("607,367,676,414");
        listPic.add("examination1.png");
        localMultiRegionFindManyPic(listRegion,listPic)
        */
        AtFairy2.OpencvResult result, maxResult = returnResult();
        for (int i = 0; i < list_Region.size(); i++) {
            int x1 = Integer.parseInt(list_Region.get(i).split(",")[0]);
            int y1 = Integer.parseInt(list_Region.get(i).split(",")[1]);
            int x2 = Integer.parseInt(list_Region.get(i).split(",")[2]);
            int y2 = Integer.parseInt(list_Region.get(i).split(",")[3]);
            result = localFindPic(x1, y1, x2, y2, list_picName.get(i));
            if (result.sim > maxResult.sim) {
                maxResult = result;
            }

        }
        return maxResult;
    }

    public AtFairy2.OpencvResult localRGBTovalueFindPic(int x_1, int y_1, int x_2, int y_2, double thresh, double maxval, int thresholdType, String RGB, String picName) {
        //RGB单独提取二值化识别
        AtFairy2.OpencvResult result;
        w = x_2 - x_1;
        h = y_2 - y_1;
        //AtFairy2.OpencvResult result = mFairy.new OpencvResult();
        //   AtFairy2.RawScreenInfo rawScreenInfo = mFairy.GetCaptureRaw();
        result = mFairy.new OpencvResult();
        //rawScreenInfo = mFairy.GetCaptureRaw();
        b = mFairy.captureInterval().raw;
        //LtLog.i(getLineInfo() +  "--------------------------b.length"+b.length + ",b[0]=" + b[0] + ",picName=" + picName);
        // b = rawScreenInfo.raw;
        Mat dst;
        Mat dst1 = new Mat();
        Mat img = Imgcodecs.imread("/sdcard/png/" + picName);
        Rect roi = new Rect(x_1, y_1, w, h);
        Mat img1 = new Mat(720, 1280, CvType.CV_8UC4);
        img1.put(0, 0, b);
        Imgproc.cvtColor(img1, img1, Imgproc.COLOR_BGR2RGB);
        dst = new Mat(img1, roi);
        // LtLog.i(getLineInfo() +  "--------------------------img="+img + ",dst=" + dst);
        List<Mat> RGBdst = new ArrayList<Mat>(3);
        List<Mat> RGBdst1 = new ArrayList<Mat>(3);
        Core.split(dst, RGBdst);
        Core.split(img, RGBdst1);
        switch (RGB) {
            case "R":
                dst = RGBdst.get(2);
                img = RGBdst1.get(2);
                break;
            case "G":
                dst = RGBdst.get(1);
                img = RGBdst1.get(1);
                break;
            case "B":
                dst = RGBdst.get(0);
                img = RGBdst1.get(0);
                break;
        }
        Imgproc.threshold(dst, dst, thresh, maxval, thresholdType);
        Imgproc.threshold(img, img, thresh, maxval, thresholdType);
        Imgproc.matchTemplate(dst, img, dst1, Imgproc.TM_CCOEFF_NORMED);
        Core.MinMaxLocResult mmr;
        mmr = Core.minMaxLoc(dst1);
        result.sim = (float) mmr.maxVal;
        //LtLog.i(getLineInfo() +  "--------------------------result.sim"+result.sim);
        result.x = (int) mmr.maxLoc.x + x_1;
        result.y = (int) mmr.maxLoc.y + y_1;
        //LtLog.i(getLineInfo() +  "--------------------------SingleTask--MyFindPic--mmr.maxVal=>" + mmr.maxVal + ","+result.x + ","+result.y + "---mmr.minVal=" + mmr.minVal + "---mmr.minLoc=" + (mmr.minLoc.x + x_1) + "," + (mmr.minLoc.y + y_1));
        return result;
    }

    public int MyFindPic(int x_1, int y_1, int x_2, int y_2, String picName) {
        // String path = "file:///android_asset/文件名";
        byte[] bint = new byte[3686400];
        for (int i = 0; i < b.length; i = i + 4) {
            bint[i] = b[i + 2];
            bint[i + 1] = b[i + 1];
            bint[i + 2] = b[i];
            bint[i + 3] = b[i + 3];
        }
        //InputStream abpath = getClass().getResourceAsStream("/assets/determine.png");
        Core.MinMaxLocResult mmr;
        Mat mat = Imgcodecs.imread("/sdcard/yy1.png");
        Mat mat1 = Imgcodecs.imread("/sdcard/playGame.png");

        //Mat dst=new Mat(mat.rows(),mat.cols(), (int) mat.elemSize());
        Mat dst = new Mat();

        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--mat.elemSize>" + mat.elemSize());
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--mat1.elemSize>" + mat1.elemSize());
        long time, timex;
        time = System.currentTimeMillis();
        Imgproc.matchTemplate(mat1, mat, dst, Imgproc.TM_SQDIFF);
        timex = System.currentTimeMillis() - time;
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--timex>" + timex);
        //Core.normalize( dst, dst, 0, 1, Core.NORM_MINMAX, -1, new Mat() );
        time = System.currentTimeMillis();
        //Core.normalize(dst,dst,0.0,0.1,Core.NORM_MINMAX,-1);
        mmr = Core.minMaxLoc(dst);
        timex = System.currentTimeMillis() - time;
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--timex>" + timex);
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--mmr.maxVal=>" + mmr.maxVal + "---mmr.maxLoc=" + mmr.maxLoc + "---mmr.minVal=" + mmr.minVal + "---mmr.minLoc=" + mmr.minLoc);

        time = System.currentTimeMillis();
        Imgproc.matchTemplate(mat1, mat, dst, Imgproc.TM_SQDIFF_NORMED);
        timex = System.currentTimeMillis() - time;
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--timex>" + timex);
        //Core.normalize( dst, dst, 0, 1, Core.NORM_MINMAX, -1, new Mat() );
        mmr = Core.minMaxLoc(dst);
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--mmr.maxVal>" + mmr.maxVal + "---mmr.maxLoc" + mmr.maxLoc + "---mmr.minVal=" + mmr.minVal + "---mmr.minLoc=" + mmr.minLoc);


        time = System.currentTimeMillis();
        Imgproc.matchTemplate(mat1, mat, dst, Imgproc.TM_CCORR);
        timex = System.currentTimeMillis() - time;
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--timex>" + timex);
        //Core.normalize( dst, dst, 0, 1, Core.NORM_MINMAX, -1, new Mat() );
        mmr = Core.minMaxLoc(dst);
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--mmr.maxVal>" + mmr.maxVal + "---mmr.maxLoc" + mmr.maxLoc + "---mmr.minVal=" + mmr.minVal + "---mmr.minLoc=" + mmr.minLoc);
        time = System.currentTimeMillis();
        Imgproc.matchTemplate(mat1, mat, dst, Imgproc.TM_CCORR_NORMED);
        timex = System.currentTimeMillis() - time;
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--timex>" + timex);
        //Core.normalize( dst, dst, 0, 1, Core.NORM_MINMAX, -1, new Mat() );
        mmr = Core.minMaxLoc(dst);
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--mmr.maxVal>" + mmr.maxVal + "---mmr.maxLoc" + mmr.maxLoc + "---mmr.minVal=" + mmr.minVal + "---mmr.minLoc=" + mmr.minLoc);

        time = System.currentTimeMillis();
        Imgproc.matchTemplate(mat1, mat, dst, Imgproc.TM_CCOEFF);
        timex = System.currentTimeMillis() - time;
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--timex>" + timex);
        //Core.normalize( dst, dst, 0, 1, Core.NORM_MINMAX, -1, new Mat() );
        time = System.currentTimeMillis();
        mmr = Core.minMaxLoc(dst);
        timex = System.currentTimeMillis() - time;
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--timex>" + timex);
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--mmr.maxVal>" + mmr.maxVal + "---mmr.maxLoc" + mmr.maxLoc + "---mmr.minVal=" + mmr.minVal + "---mmr.minLoc=" + mmr.minLoc);


        time = System.currentTimeMillis();
        Imgproc.matchTemplate(mat1, mat, dst, Imgproc.TM_CCOEFF_NORMED);
        timex = System.currentTimeMillis() - time;
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--timex>" + timex);
        //Core.normalize( dst, dst, 0, 1, Core.NORM_MINMAX, -1, new Mat() );
        mmr = Core.minMaxLoc(dst);
        LtLog.i(getLineInfo() + "--------------------------SingleTask--MyFindPic--mmr.maxVal>" + mmr.maxVal + "---mmr.maxLoc" + mmr.maxLoc + "---mmr.minVal=" + mmr.minVal + "---mmr.minLoc=" + mmr.minLoc);
        return 1;

        //matchTemplate
    }

    public int getXY(int x_1, int y_1, int x_2, int y_2, double thresh, double maxval, int thresholdType) {
//        XY result = null;
        String strsim = "";
        w = x_2 - x_1;
        h = y_2 - y_1;
        b = mFairy.captureInterval().raw;
        Mat dst;
        Mat hierarchy = new Mat();
        Rect roi = new Rect(x_1, y_1, w, h);

        Mat img1 = new Mat(720, 1280, CvType.CV_8UC4);
        img1.put(0, 0, b);
//        Imgproc.erode(img1,img1,new Mat());
//        Imgcodecs.imwrite("/sdcard/nunber" + "2" + ".png", img1);
        Imgproc.cvtColor(img1, img1, Imgproc.COLOR_BGR2RGB);
        dst = new Mat(img1, roi);
        Imgproc.cvtColor(dst, dst, Imgproc.COLOR_RGB2GRAY);
        Imgproc.threshold(dst, dst, thresh, maxval, thresholdType);
        //Imgcodecs.imwrite("/sdcard/nunber" + "11" + ".png", dst);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(dst, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
//        LtLog.i(getLineInfo() + "------------------------------contours=" + contours + "__hierarchy=" + hierarchy);
        Mat dst1 = new Mat();
        String strnunber = "";
        // LtLog.i(getLineInfo() +  "--------------------------SingleTask--getXY--contours.size()=" + contours.size());
        //int yy = 0;
        List listNumber = new ArrayList();
        TreeMap<Integer, String> mapList = new TreeMap<>();
        for (int i = 0; i < contours.size(); i++) {
            Rect rect = Imgproc.boundingRect(contours.get(i));
            if (rect.height >= 10 && rect.width >= 2) {
                //yy = yy + 1;
                // LtLog.i(getLineInfo() +  "--------------------------SingleTask--getXY--yy=" + yy);
                Rect roi1 = new Rect(rect.x, rect.y, rect.width, rect.height);
                Mat dst2 = new Mat(dst, roi1);
                //  Imgcodecs.imwrite("/sdcard/nunber" + String.valueOf(i) + ".png", dst2);
                double maxSim = 0.0;
                String maxstr = "";
                for (int k = 0; k < 10; k++) {
                    List<String> nunberNane = getPictures(String.valueOf(k));
//                    LtLog.i(getLineInfo() + "------------------------------nunberNane=" + nunberNane + ",k= " + k );
                    for (int j = 0; j < nunberNane.size(); j++) {
//                        Mat img = Imgcodecs.imread("/sdcard/XY/" + nunberNane.get(j), 0);
                        Mat img =getAssetXY( nunberNane.get(j));
                        if (rect.height >= img.height() && rect.width >= img.width() && rect.width <= img.width() + 1) {
                            Mat dst3=new Mat();
                            Imgproc.cvtColor(img, dst3, Imgproc.COLOR_RGB2GRAY);
                            Imgproc.threshold(dst3, dst3, thresh, maxval, thresholdType);
                            if (dst2.width() >= dst3.width()) {
//                                LtLog.i(getLineInfo() + "------------------------------dst2=" + dst2 );
                                Imgproc.matchTemplate(dst2, dst3, dst1, Imgproc.TM_CCOEFF_NORMED);
                            } else {
                                Imgproc.matchTemplate(dst3, dst2, dst1, Imgproc.TM_CCOEFF_NORMED);
                            }
                            Core.MinMaxLocResult mmr;
                            mmr = Core.minMaxLoc(dst1);
                            if (mmr.maxVal > maxSim && mmr.maxVal > 0.1) {
                                maxSim = mmr.maxVal;
                                maxstr = String.valueOf(k);
//                                 LtLog.i(getLineInfo() +  "--------------------------SingleTask--getXY--maxSim=" + maxSim + "__maxstr=" + maxstr + ",k "+ k);
                            }
                            dst3.release();
                        }
                    }
                }
                dst2.release();
                if (maxSim >= 0.5) {
//                    LtLog.i(getLineInfo() +  "--------------------------SingleTask----maxSim=" + maxSim );
                    listNumber.add(roi1.x);
                    mapList.put(roi1.x, maxstr);
                    strnunber = maxstr + strnunber;
                    //   LtLog.i(getLineInfo() +  "--------------------------PublicFunction--getXY--maxSim=" + maxSim);
                    strsim = maxSim + " | " + strsim;
                    //       String str = "/sdcard/nunber" + String.valueOf(i) + String.format("%.2f", maxSim) + ".png";
                    //     Imgcodecs.imwrite(str, dst2);
                }
                //LtLog.i(getLineInfo() +  "--------------------------SingleTask--getXY--str=" + str);
            }

        }
//        LtLog.i(getLineInfo() + "------------------------------mapList=" + mapList);
        int res=0;
        if (mapList.size() > 0) {
            String str="";
//            LtLog.i(getLineInfo() + "------------------------------mapList.size() > 0=" );
            for (Integer key : mapList.keySet()) {
//                LtLog.i(getLineInfo() + "------------------------------mapList=" + mapList.get(key)+ ","  +  key);
                str=str + mapList.get(key);
            }

            res=Integer.parseInt(str);

        }
        hierarchy.release();
        dst1.release();
        dst.release();
        return res;

    }

    public Mat getAssetXY(String file_name){
        Bitmap image = null;
        AssetManager am = mContext.getResources().getAssets();
        String fileName="XY/" + file_name;
        Mat img=new Mat();
        Mat dst=new Mat();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            org.opencv.android.Utils.bitmapToMat(image, img);
            Imgproc.cvtColor(img, dst, Imgproc.COLOR_BGRA2RGB);
            is.close();
        } catch (Exception e) {
            LtLog.i(getLineInfo() + "--------------getImageFromAssetsFile error----------------" + fileName);
            e.printStackTrace();
        }
//        Imgcodecs.imwrite("/sdcard/test.png",dst);
        img.release();
        return dst;
    }


    public Mat getAcupointMat(String file_name){
        Bitmap image = null;
        AssetManager am = mContext.getResources().getAssets();
        String fileName="png/" + file_name;
        Mat img=new Mat();
        Mat dst=new Mat();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            org.opencv.android.Utils.bitmapToMat(image, img);
            Imgproc.cvtColor(img, dst, Imgproc.COLOR_BGRA2RGB);
            is.close();
        } catch (Exception e) {
            LtLog.i(getLineInfo() + "--------------getImageFromAssetsFile error----------------" + fileName);
            e.printStackTrace();
        }
//        Imgcodecs.imwrite("/sdcard/test.png",dst);
        img.release();
        return dst;
    }


    public int getCcr(int x_1, int y_1, int x_2, int y_2, double thresh, double maxval, int thresholdType){




        return 0;
    }

    public List<String> getPictures(final String nunber) {
        //得到字库（XY文件夹）内的所有图片名称
        int j=1;
        List<String> list = new ArrayList<String>();
        AssetManager am = mContext.getResources().getAssets();
        try {
            String[] filePathList = am.list("XY");
            for (int k = 0; k < filePathList.length; k++) {
                //   LtLog.i(getLineInfo() +  "--------------------------SingleTask--getXY--allfiles[k]=" + allfiles[k].getName().toString() + "-----" + nunber + "-" + String.valueOf(j) + ".png");
                if (filePathList[k].equals(nunber + "-" + String.valueOf(j) + ".png")) {
                    //   LtLog.i(getLineInfo() +  "--------------------------SingleTask--getXY--allfiles[k]=" + allfiles[k].getName() + "-----" + nunber + "-" + String.valueOf(j) + ".png");
                    list.add(filePathList[k]);
                    j = j + 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        return list;
    }

    public void RanSwipe(int x, int y, int x1, int y1, int dir, int sleep) {
        //x,y,x1,y1 这是范围
        //dir = 0从上往下滑动，dir = 1从左往右滑动，dir = 2从下往上滑动，dir = 3从右往左滑动
        //sleep 滑动延时
        if (dir == 0) {
            int result = x + (int) (Math.random() * ((x1 - x) + 1));
            mFairy.touchDown(result, y);
            mFairy.touchMove(result, y1, sleep);
            mFairy.touchUp();
        } else if (dir == 1) {
            int result = y + (int) (Math.random() * ((y1 - y) + 1));
            mFairy.touchDown(x, result);
            mFairy.touchMove(x1, result, sleep);
            mFairy.touchUp();
        } else if (dir == 2) {
            int result = x + (int) (Math.random() * ((x1 - x) + 1));
            mFairy.touchDown(result, y1);
            mFairy.touchMove(result, y, sleep);
            mFairy.touchUp();
        } else if (dir == 3) {
            int result = y + (int) (Math.random() * ((y1 - y) + 1));
            mFairy.touchDown(x1, result);
            mFairy.touchMove(x, result, sleep);
            mFairy.touchUp();
        }
    }

    public List<String> getColor(List<String> str_color) {
        //得到指定坐标的颜色
        //参数         List<String> list2=Arrays.asList("x1,y1","x2,y2");
        List<String> list = new ArrayList<>();
        //byte[] b = mFairy.GetCaptureRaw().raw;
        b = mFairy.captureInterval().raw;
        Mat img1 = new Mat(720, 1280, CvType.CV_8UC4);
        img1.put(0, 0, b);
//        LtLog.i(getLineInfo() + "-------------------------        img1.get(566,361)[0]" + img1.get(566, 361)[0]);
        for (int i = 0; i < str_color.size(); i++) {
            int x = Integer.valueOf(str_color.get(i).split(",")[0]);
            int y = Integer.valueOf(str_color.get(i).split(",")[1]);
            // LtLog.i(getLineInfo() + "-------------------------        x=" + x + ",y=" + y + "====="+ (img1.get(y, x)[0] + img1.get(y, x)[1] + img1.get(y, x)[2] + "" ));
            list.add(img1.get(y, x)[0] + img1.get(y, x)[1] + img1.get(y, x)[2] + "");
        }
        return list;
    }


    public Coordinate judgeListColorChange(List<String> list, long millisecond) {
        Coordinate xy = new Coordinate();
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        //        list1.add(640+ "," +38);
//        List list2=getColor(list1);
//        LtLog.i(getLineInfo() + "-------------------------        x="  + list2.get(0));
        list1 = getColor(list);
        Utils.sleep(millisecond);
        list2 = getColor(list);
        for (int i = 0; i < list.size(); i++) {
            if (list1.get(i).equals(list2.get(i)) == false) {
                LtLog.i(getLineInfo() + "-------------------------        list=" + list.get(i));
                xy.x = Integer.parseInt((list.get(i)).split(",")[0]);
                xy.y = Integer.parseInt((list.get(i)).split(",")[1]);
                break;
            }
        }
        return xy;
    }

    private void cap() {
        screenInfo = mFairy.captureInterval();
        b = screenInfo.raw;
    }

    public class Coordinate {
        public int x = 0;
        public int y = 0;

        //return xy;
        public Coordinate() {
        }

        public String toString() {
            return this.x + "," + this.y;
        }
    }

    public void rndTap(int x_1, int y_1, int x_2, int y_2)throws Exception {
        //范围点击
        int rndx = rand.nextInt(x_2 - x_1 + 1) + x_1;
        int rndy = rand.nextInt(y_2 - y_1 + 1) + y_1;
        mFairy.tap(rndx, rndy);
    }

    public void rndTapWH(int x_1, int y_1, int w_1, int h_1)throws Exception{
        //坐标点偏移宽高随机点击
        int rndx = rand.nextInt(w_1 + 1) + x_1;
        int rndy = rand.nextInt(h_1 + 1) + y_1;
        mFairy.tap(rndx, rndy);
    }

    public static String getLineInfo() {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getFileName() + ": Line " + ste.getLineNumber() ;
    }

    public int getMinuteNumber() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        //LtLog.i(getLineInfo() +  "------------------------------=hour=" + hour + ",minute=" + minute + ",second=" + second);

        int current = hour * 60 + minute;

        return current;
    }

    public String getCurrSun() {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String currSun = dateFm.format(date);
//    String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
//    Calendar cal = Calendar.getInstance();
//    cal.setTime(date);
//    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
//    LtLog.i(getLineInfo() +  "------------------------------=weekDays=" + weekDays[w] + ",w=" +w );
        //LtLog.i(getLineInfo() +  "------------------------------=currSun=" + currSun );
        return currSun;
    }


    public class OpencvResult {
        public int x;
        public int y;
        public float sim;

        public OpencvResult() {
        }

        public String toString() {
            return this.x + ":" + this.y + " " + this.sim;
        }
    }


//    public Mat getTemplateMat(String picName) {
//        Mat mat = (Mat) this.mTemplateMap.get(picName);
//        if (mat == null) {
//            Mat buf = new MatOfByte(YpFairyUtils.getInstance().getTemplateData(picName));
//            mat = Imgcodecs.imdecode(buf, -1);
//            buf.release();
//            this.mTemplateMap.put(picName, mat);
//        }
//        return mat;
//    }

    private Bitmap getImageFromAssetsFile(String fileName) {
        Bitmap image = null;
        AssetManager am = mContext.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            LtLog.i(getLineInfo() + "--------------getImageFromAssetsFile error----------------" + fileName);
            e.printStackTrace();
        }
        return image;
    }


    private AtFairy2.OpencvResult returnResult() {
        result.x = -1;
        result.y = -1;
        result.sim = (float) 0.0;
        return result;
    }


    public String httpPost(String filePath, String key, String mToken) {

        LtLog.i(getLineInfo() + "-------" + "-------------httpPost....." + mToken);
        try {
            JSONObject UrlJson = new JSONObject(mToken);
            LtLog.i(getLineInfo() + "-------" + "-------------httpPost....." + UrlJson.optString("data"));
            OkHttpClient client = new OkHttpClient();
            File file = new File(filePath);
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", "head_image", body)
                    .addFormDataPart("token", UrlJson.optString("data"))
                    .addFormDataPart("key", key)
                    .build();
            Request request = new Request.Builder()
                    .url("http://up-z2.qiniu.com/")
                    .post(requestBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                LtLog.i(getLineInfo() + "-------" + "-------------response....." + response.body().string());
                return response.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "error";
    }


    public boolean fileDelete(String strFile) {
        try {
            File f = new File(strFile);
            if (f.exists()) {
                f.delete();
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public void continuityClick(int x_1, int y_1, int x_2, int y_2, int index, long millisecond)throws Exception {

        for (int i = 0; i < index + 1; i++) {
            rndTap(x_1, y_1, x_2, y_2);
            Utils.sleep(millisecond);
        }


    }


    public boolean findMat(int x_1, int y_1, int W, int H, Mat mMat, double sim) {
        //矩阵比对 如果传入的矩阵与指定范围的矩阵相等，则返回true
        Mat mat;
        Mat dstMat = new Mat();
        mat = mFairy.getScreenMat(x_1, y_1, W, H, 1, 0, 0, 1);
        //LtLog.i(getLineInfo() + "-------" + mat + "-------------optionJson....." );
        if (mat == null) {
            LtLog.i(getLineInfo() + "-------" + mat + "-------------optionJson.....");
            return false;
        }
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HLS);
        Imgproc.cvtColor(mMat, mMat, Imgproc.COLOR_RGB2HLS);
        Imgproc.matchTemplate(mat, mMat, dstMat, Imgproc.TM_CCOEFF_NORMED);
        Core.MinMaxLocResult mmr;
        mmr = Core.minMaxLoc(dstMat);
//        LtLog.i(getLineInfo() + "-------" + mmr.maxVal + "-------------optionJson....." );
        if (mmr.maxVal >= sim) {
            mat.release();
            dstMat.release();
            return true;
        }
        mat.release();
        dstMat.release();
        return false;
    }


    public boolean judgeMatAndMatChange(double sim, Mat mat, Mat tempMat) {
        //判断两个矩阵的相似度大于 sim 则返回 true;
        boolean state = false;
        Mat dstMat = new Mat();
//        LtLog.i(getLineInfo() + "--------------------return....." + mat.dims() + "," + mat.channels() + "," + mat.elemSize());
//        LtLog.i(getLineInfo() + "--------------------return....." + tempMat.dims() + "," + tempMat.channels() + "," + tempMat.elemSize());
        if (mat.channels() == 3) {
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HLS);
            Imgproc.cvtColor(tempMat, tempMat, Imgproc.COLOR_RGB2HLS);
        }
        Imgproc.matchTemplate(mat, tempMat, dstMat, Imgproc.TM_CCOEFF_NORMED);
        Core.MinMaxLocResult mmr;
        mmr = Core.minMaxLoc(dstMat);
        LtLog.i(getLineInfo() + "--------------------return....." + mmr.maxVal);
        if (mmr.maxVal >= sim) {
            state = true;
        }
        dstMat.release();
        return state;
    }

}


//    public AtFairy2.RawScreenInfo getCaptureRaw() {
//        do {
//            if (this.rawScreenInfo == null) {
//                this.rawScreenInfo = mFairy.capture2();
//                mLastCaptureTime = System.currentTimeMillis();
//            } else {
//                synchronized (this.rawScreenInfo) {
//                    if (System.currentTimeMillis() - mLastCaptureTime >= MIN_TIME) {
//                        //LtLog.i(getLineInfo() +  "-----------------Abnormal.getCaptureRaw=" + (System.currentTimeMillis() - mLastCaptureTime));
//                        this.rawScreenInfo = mFairy.capture2();
//                        mLastCaptureTime = System.currentTimeMillis();
//                    }
//                }
//            }
//            if (rawScreenInfo.raw == null) {
//                // LtLog.i("==============================================================Abnormal.getCaptureRaw--b="+b+"---b.raw.length=");
//                Utils.sleep(1000);
//            }
//        } while (rawScreenInfo.raw == null);
//        //LtLog.i(getLineInfo() +  "-----------------Abnormal.getCaptureRaw--b="+b+"---b.raw.length="+b.raw.length);
//        return this.rawScreenInfo;
//    }