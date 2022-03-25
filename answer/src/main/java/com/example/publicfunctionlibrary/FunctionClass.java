//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.publicfunctionlibrary;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.AtFairy2.OpencvResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.ScreenInfo;

import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class FunctionClass {
    private AtFairy2 mFairy;
    public final String RGB = "RGB";
    public final String HLS = "HLS";
    public final String HSV = "HSV";
    public final String BINARYZATION = "BINARYZATION";
    public final String GRAYSCALE = "GRAYSCALE";
    private Context mContext;
    private boolean click = true;
    private ScreenInfo screenInfo;
    private byte[] b;
    private Random rand = new Random();
    public final int edition = 1;
    private Map<String, Mat> picMap = new HashMap();
    private OpencvResult result1;
    private long cap_time = System.currentTimeMillis();
    public FunctionClass.ResultValue result;

    public FunctionClass(AtFairy2 ypFairy, Context context) {
        this.mFairy = ypFairy;
        this.mContext = context;
    }

    public native void multipointFindColor(int var1, int var2, int var3, int var4, byte[] var5, long var6, String var8, String var9, double var10, int[] var12);

    public native void multipointFindColorEx(int var1, int var2, int var3, int var4, byte[] var5, long var6, String var8, String var9, double var10, int[] var12);

    public native void FindPicEx(long var1, long var3);

    public native void findPicByOpenCV(int var1, int var2, int var3, int var4, byte[] var5, long var6, float[] var8);

    public native void test();

    public native void getPackageUid(String var1);

    public void testtest() {
        this.test();
    }

    public void getUid(String packageName1) {
        LtLog.i("------------------------------getUid:" + packageName1);
        this.getPackageUid(packageName1);
    }

    public float[] findPicByOpenCV(int x_1, int y_1, int x_2, int y_2, String picName) {
        ScreenInfo screenInfo = this.mFairy.captureInterval();
        float[] res = new float[3];
        Bitmap map = this.getImageFromAssetsFile(picName);
        Mat img = new Mat();
        Utils.bitmapToMat(map, img);
        Imgproc.cvtColor(img, img, 3);
        long time = System.currentTimeMillis();
        this.findPicByOpenCV(x_1, y_1, x_2, y_2, screenInfo.raw, img.getNativeObjAddr(), res);
        map.recycle();
        img.release();
        return res;
    }

    public int[] multipointFindColor(int x1, int y1, int x2, int y2, String colorStr_start, String colorStr_sub, double sim) {
        ScreenInfo screenInfo = this.mFairy.captureInterval();
        Mat img1 = new Mat(screenInfo.height, screenInfo.width, CvType.CV_8UC4);
        img1.put(0, 0, screenInfo.raw);
        Imgproc.cvtColor(img1, img1, 1);
        int[] xy = new int[2];
        long time1 = System.currentTimeMillis();
        this.multipointFindColor(x1, y1, x2, y2, screenInfo.raw, img1.getNativeObjAddr(), colorStr_start, colorStr_sub, sim, xy);
        LtLog.i("------------------------------" + (System.currentTimeMillis() - time1));
        img1.release();
        return xy;
    }

    public int[] multipointFindColorEx(int x1, int y1, int x2, int y2, String colorStr_start, String colorStr_sub, double sim) {
        ScreenInfo screenInfo = this.mFairy.captureInterval();
        Mat img1 = new Mat(screenInfo.height, screenInfo.width, CvType.CV_8UC4);
        img1.put(0, 0, screenInfo.raw);
        Imgproc.cvtColor(img1, img1, 1);
        int[] xy = new int[2];
        this.multipointFindColorEx(x1, y1, x2, y2, screenInfo.raw, img1.getNativeObjAddr(), colorStr_start, colorStr_sub, sim, xy);
        img1.release();
        return xy;
    }

    public int[] multipointFindColorEx(int x1, int y1, int x2, int y2, String colorStr_start, String colorStr_sub, double sim, ScreenInfo screenInfo) {
        Mat img1 = new Mat(screenInfo.height, screenInfo.width, CvType.CV_8UC4);
        img1.put(0, 0, screenInfo.raw);
        Imgproc.cvtColor(img1, img1, 1);
        int[] xy = new int[2];
        this.multipointFindColorEx(x1, y1, x2, y2, screenInfo.raw, img1.getNativeObjAddr(), colorStr_start, colorStr_sub, sim, xy);
        img1.release();
        return xy;
    }

    public void FindPicEx() {
        Mat mat1 = Imgcodecs.imread("/sdcard/1.png");
        Mat mat2 = Imgcodecs.imread("/sdcard/2.png");
        long time = System.currentTimeMillis();
        this.FindPicEx(mat1.getNativeObjAddr(), mat2.getNativeObjAddr());
        long time2 = System.currentTimeMillis() - time;
        System.out.println("======= time : " + time2);
    }

   /* public void getPermission(Context context, Class<?> cls) {
        if (VERSION.SDK_INT > 23) {
            Intent intent = new Intent(context, cls);
            intent.setFlags(268435456);
            context.startActivity(intent);
            String fileName = "/sdcard/fairy_log/" + context.getPackageName() + ".log";
            LtLog.i("======================set log file  :" + fileName);
            new File(fileName);
            File file1 = new File("/sdcard/fairy_log/" + context.getPackageName() + "1.log");

            for(int i = 0; i < 30; ++i) {
                if (!file1.exists()) {
                    try {
                        file1.createNewFile();
                        LtLog.i("======================createNewFile file  :" + fileName);
                    } catch (IOException var10) {
                        var10.printStackTrace();
                    }
                }

                try {
                    LtLog.i("======================try set log");
                    LtLog.setLogFile(fileName, 10485760);
                    Thread.sleep(1000L);
                } catch (Exception var9) {
                    LtLog.i("======================set log error ");
                }

                if (file1.exists()) {
                    LtLog.i("====================== log file exists ");
                    Object var5 = null;
                    break;
                }
            }

            file1.delete();
            LtLog.i("======================set log 1= " + file1.exists());
        }

    }*/

    public FunctionClass.ResultValue comparisonMat(Mat bigMat, Mat small, String model) {
        FunctionClass.ResultValue result = this.returnResult();
        Mat dst = new Mat();
        if (model.equals("HSV")) {
            Imgproc.cvtColor(bigMat, bigMat, 41);
            Imgproc.cvtColor(small, small, 41);
        } else if (model.equals("HLS")) {
            Imgproc.cvtColor(bigMat, bigMat, 53);
            Imgproc.cvtColor(small, small, 53);
        }

        Imgproc.matchTemplate(bigMat, small, dst, 5);
        MinMaxLocResult mmr = Core.minMaxLoc(dst);
        result.sim = (float)mmr.maxVal;
        result.x = (int)mmr.maxLoc.x;
        result.y = (int)mmr.maxLoc.y;
        dst.release();
        return result;
    }

    public List<FunctionClass.ResultValue> comparisonMat(Mat bigMat, Mat small, String model, float sim) {
        Mat dst = new Mat();
        Mat mask = new Mat(small.height(), small.width(), small.type());
        List<FunctionClass.ResultValue> list = new ArrayList();
        float maxSim = sim;

        while(true) {
            if (model.equals("HSV")) {
                Imgproc.cvtColor(bigMat, bigMat, 41);
                Imgproc.cvtColor(small, small, 41);
            } else if (model.equals("HLS")) {
                Imgproc.cvtColor(bigMat, bigMat, 53);
                Imgproc.cvtColor(small, small, 53);
            }

            Imgproc.matchTemplate(bigMat, small, dst, 5);
            MinMaxLocResult mmr = Core.minMaxLoc(dst);
            if (mmr.maxVal <= (double)maxSim) {
                dst.release();
                mask.release();
                return list;
            }

            FunctionClass.ResultValue result = this.returnResult();
            result.sim = (float)mmr.maxVal;
            result.x = (int)mmr.maxLoc.x;
            result.y = (int)mmr.maxLoc.y;
            list.add(result);
            Mat imgROI = new Mat(bigMat, new Rect(result.x, result.y, small.width(), small.height()));
            mask.copyTo(imgROI);
            imgROI.release();
        }
    }

    public Mat getScreenMat(int leftX, int leftY, int width, int height) {
        Mat mat = this.mFairy.getScreenMat(leftX, leftY, width, height, 1, 0, 0, 1);
        Imgproc.cvtColor(mat, mat, 1);
        return mat;
    }

    public Mat getAssetImageFileMat(String fileName) {
        if (this.picMap.get(fileName) != null) {
            return ((Mat)this.picMap.get(fileName)).clone();
        } else {
            Bitmap map = this.getImageFromAssetsFile(fileName);
            Mat img = new Mat();
            Utils.bitmapToMat(map, img);
            Imgproc.cvtColor(img, img, 3);
            this.picMap.put(fileName, img.clone());
            return img;
        }
    }

    private Bitmap getImageFromAssetsFile(String fileName) {
        Bitmap image = null;
        AssetManager am = this.mContext.getResources().getAssets();

        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception var5) {
            LtLog.i(getLineInfo() + "--------------getImageFromAssetsFile error----------------" + fileName);
            var5.printStackTrace();
        }

        return image;
    }

    public static String getLineInfo() {
        StackTraceElement ste = (new Throwable()).getStackTrace()[1];
        return ste.getFileName() + ": LIU " + ste.getLineNumber();
    }

    public int getColorNunber(int x_1, int y_1, int width, int height, String strColor, double simDouble) {
        double[] match = new double[]{Double.parseDouble(strColor.split(",")[2]), Double.parseDouble(strColor.split(",")[1]), Double.parseDouble(strColor.split(",")[0])};
        double simValue = 255.0D * (1.0D - simDouble);
        double min_r = match[0] - simValue;
        double min_g = match[1] - simValue;
        double min_b = match[2] - simValue;
        double max_r = match[0] + simValue;
        double max_g = match[1] + simValue;
        double max_b = match[2] + simValue;
        if (min_r < 0.0D) {
            min_r = 0.0D;
        }

        if (min_g < 0.0D) {
            min_g = 0.0D;
        }

        if (min_b < 0.0D) {
            min_b = 0.0D;
        }

        if (max_r > 255.0D) {
            max_r = 255.0D;
        }

        if (max_g > 255.0D) {
            max_g = 255.0D;
        }

        if (max_b > 255.0D) {
            max_b = 255.0D;
        }

        Mat mat = this.mFairy.getScreenMat(x_1, y_1, width, height, 1, 0, 0, 1);
        Scalar minValues = new Scalar(min_r, min_g, min_b);
        Scalar maxValues = new Scalar(max_r, max_g, max_b);
        Core.inRange(mat, minValues, maxValues, mat);
        int num = Core.countNonZero(mat);
        mat.release();
        return num;
    }

    public String getToken() {
        String access_key = "cCApuzlAtKeWBg6G0Ezt2Zpxv-Z4hnnYC53DJvnz";
        String secret_key = "MFOPlsyTpBlu_doiizNjSCTwRrhOlImITuGSmRMT";
        SecretKey secretKey = new SecretKeySpec(secret_key.getBytes(Charset.forName("UTF-8")), "HmacSHA1");
        JSONObject jsonObject = new JSONObject();
        long time = System.currentTimeMillis();


        /**
         * padyunexcel
         */

        try {
            jsonObject.put("scope", "scripterror");
            jsonObject.put("deadline", time);
        } catch (JSONException var18) {
            var18.printStackTrace();
        }

        String strBase64 = null;
        String data = jsonObject.toString();
        strBase64 = Base64.encodeToString(data.getBytes(Charset.forName("UTF-8")), 10);
        Mac mac = null;

        try {
            mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKey);
        } catch (InvalidKeyException var16) {
            var16.printStackTrace();
        } catch (NoSuchAlgorithmException var17) {
            var17.printStackTrace();
        }

        byte[] digest = mac.doFinal(strBase64.getBytes(Charset.forName("UTF-8")));
        String encodedSign = Base64.encodeToString(digest, 10);
        JSONObject returnjsonObject = new JSONObject();

        try {
            returnjsonObject.put("code", 0);
            returnjsonObject.put("msg", "");
            returnjsonObject.put("data", access_key + ":" + encodedSign + ":" + strBase64);
        } catch (JSONException var15) {
            var15.printStackTrace();
        }

        String return_val = returnjsonObject.toString();
        return return_val;
    }

    public Mat binaryzationMat(Mat mat, int[] minRange, int[] maxRange) {
        Scalar minValues = new Scalar((double)minRange[0], (double)minRange[1], (double)minRange[2]);
        Scalar maxValues = new Scalar((double)maxRange[0], (double)maxRange[1], (double)maxRange[2]);
        Core.inRange(mat, minValues, maxValues, mat);
        minValues = null;
        maxValues = null;
        return mat;
    }

    public void rndTap(int x_1, int y_1, int x_2, int y_2) throws InterruptedException {
        int rndx = this.rand.nextInt(x_2 - x_1 + 1) + x_1;
        int rndy = this.rand.nextInt(y_2 - y_1 + 1) + y_1;
        this.mFairy.tap(rndx, rndy);
        this.click = true;
    }

    public void rndTapWH(int x_1, int y_1, int w_1, int h_1) throws InterruptedException {
        int rndx = this.rand.nextInt(w_1 + 1) + x_1;
        int rndy = this.rand.nextInt(h_1 + 1) + y_1;
        this.mFairy.tap(rndx, rndy);
        this.click = true;
    }

    private void cap() {
        this.screenInfo = this.mFairy.captureInterval();
        this.b = this.screenInfo.raw;
    }

    public FunctionClass.ResultValue binaryzationFindPic(int x_1, int y_1, int x_2, int y_2, int[] minRange_BGR, int[] maxRange_BGR, String picName) {
        return this.FindPic(x_1, y_1, x_2, y_2, minRange_BGR, maxRange_BGR, picName, (Mat)null, "BINARYZATION");
    }

    public FunctionClass.ResultValue rgbFindPic(int x_1, int y_1, int x_2, int y_2, String picName) {
        return this.FindPic(x_1, y_1, x_2, y_2, (int[])null, (int[])null, picName, (Mat)null, "RGB");
    }

    public FunctionClass.ResultValue rgbFindMat(int x_1, int y_1, int x_2, int y_2, Mat mat) {
        return this.FindPic(x_1, y_1, x_2, y_2, (int[])null, (int[])null, (String)null, mat, "RGB");
    }

    public FunctionClass.ResultValue grayFindPic(int x_1, int y_1, int x_2, int y_2, String picName) {
        return this.FindPic(x_1, y_1, x_2, y_2, (int[])null, (int[])null, picName, (Mat)null, "GRAYSCALE");
    }

    public FunctionClass.ResultValue hlsFindPic(int x_1, int y_1, int x_2, int y_2, String picName) {
        return this.FindPic(x_1, y_1, x_2, y_2, (int[])null, (int[])null, picName, (Mat)null, "HLS");
    }

    public FunctionClass.ResultValue hsvFindPic(int x_1, int y_1, int x_2, int y_2, String picName) {
        return this.FindPic(x_1, y_1, x_2, y_2, (int[])null, (int[])null, picName, (Mat)null, "HSV");
    }

    public FunctionClass.ResultValue FindPic(int x_1, int y_1, int x_2, int y_2, int[] minRange, int[] maxRange, String picName, Mat smallmat, String model) {
        if (System.currentTimeMillis() - this.cap_time > 1000L) {
            this.click = true;
            this.cap_time = System.currentTimeMillis();
        }

        if (this.click) {
            this.cap();
            this.click = false;
        }

        FunctionClass.ResultValue maxResult = this.returnResult();
        int w = x_2 - x_1;
        int h = y_2 - y_1;
        String[] picNameArr = null;
        if (picName != null) {
            picNameArr = picName.split("\\|");
        }

        if (x_1 + w <= this.screenInfo.width && y_1 + h <= this.screenInfo.height) {
            Mat dst = new Mat();
            Mat mat_all = new Mat(this.screenInfo.height, this.screenInfo.width, CvType.CV_8UC4);
            Rect roi = new Rect(x_1, y_1, w, h);

            try {
                mat_all.put(0, 0, this.b);
            } catch (Exception var21) {
                LtLog.i("this.b==================is null");
                return this.returnResult();
            }

            Imgproc.cvtColor(mat_all, mat_all, 4);
            Mat mat_range = new Mat(mat_all, roi);
            mat_range = this.matSwitch(mat_range, model, minRange, maxRange);
            Mat mat_sub;
            if (picName != null) {
                for(int i = 0; i < picNameArr.length; ++i) {
                    mat_sub = this.getAssetImageFileMat(picNameArr[i]);
                    mat_sub = this.matSwitch(mat_sub, model, minRange, maxRange);
                    Imgproc.matchTemplate(mat_range, mat_sub, dst, 5);
                    MinMaxLocResult mmr = Core.minMaxLoc(dst);
                    if ((float)mmr.maxVal > maxResult.sim) {
                        maxResult.sim = (float)mmr.maxVal;
                        maxResult.x = (int)mmr.maxLoc.x + x_1;
                        maxResult.y = (int)mmr.maxLoc.y + y_1;
                    }

                    mat_sub.release();
                }
            } else {
                mat_sub = this.matSwitch(smallmat, model, minRange, maxRange);
                Imgproc.matchTemplate(mat_range, mat_sub, dst, 5);
                MinMaxLocResult mmr = Core.minMaxLoc(dst);
                if ((float)mmr.maxVal > maxResult.sim) {
                    maxResult.sim = (float)mmr.maxVal;
                    maxResult.x = (int)mmr.maxLoc.x + x_1;
                    maxResult.y = (int)mmr.maxLoc.y + y_1;
                }

                mat_sub.release();
            }

            mat_range.release();
            mat_all.release();
            dst.release();
        }

        return maxResult;
    }

    public Mat matSwitch(Mat mat, String model, int[] minRange, int[] maxRange) {
        byte var6 = -1;
        switch(model.hashCode()) {
            case -868309508:
                if (model.equals("BINARYZATION")) {
                    var6 = 3;
                }
                break;
            case 71631:
                if (model.equals("HLS")) {
                    var6 = 0;
                }
                break;
            case 71851:
                if (model.equals("HSV")) {
                    var6 = 1;
                }
                break;
            case 81069:
                if (model.equals("RGB")) {
                    var6 = 2;
                }
                break;
            case 1881183399:
                if (model.equals("GRAYSCALE")) {
                    var6 = 4;
                }
        }

        switch(var6) {
            case 0:
                Imgproc.cvtColor(mat, mat, 53);
                break;
            case 1:
                Imgproc.cvtColor(mat, mat, 41);
            case 2:
            default:
                break;
            case 3:
                mat = this.binaryzationMat(mat, minRange, maxRange);
                break;
            case 4:
                Imgproc.cvtColor(mat, mat, 7);
        }

        return mat;
    }

    public String httpPost(String filePath, String key, String mToken) {
        LtLog.i(getLineInfo() + "--------------------optionJson....." + mToken);

        try {
            JSONObject UrlJson = new JSONObject(mToken);
            LtLog.i(getLineInfo() + "--------------------optionJson....." + UrlJson.optString("data"));
            OkHttpClient client = new OkHttpClient();
            File file = new File(filePath);
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            RequestBody requestBody = (new Builder()).setType(MultipartBody.FORM).addFormDataPart("file", "head_image", body).addFormDataPart("token", UrlJson.optString("data")).addFormDataPart("key", key).build();
            Request request = (new okhttp3.Request.Builder()).url("http://up-z2.qiniu.com/").post(requestBody).build();

            try {
                Response response = client.newCall(request).execute();
                LtLog.i(getLineInfo() + "--------------------response....." + response.body().string());
                return response.toString();
            } catch (IOException var11) {
                var11.printStackTrace();
            }
        } catch (JSONException var12) {
            var12.printStackTrace();
        }

        return "error";
    }

    public boolean fileDelete(String strFile) {
        try {
            File f = new File(strFile);
            if (f.exists()) {
                f.delete();
                return true;
            } else {
                return false;
            }
        } catch (Exception var3) {
            return false;
        }
    }

    public FunctionClass.ResultValue returnResult() {
        this.result = new FunctionClass.ResultValue();
        return this.result;
    }

    static {
        System.loadLibrary("native-lib");
    }

    public class ResultValue {
        public int x = -1;
        public int y = -1;
        public float sim = 0.0F;

        public ResultValue() {
        }

        public String toString() {
            return this.x + ":" + this.y + " " + this.sim;
        }
    }
}
