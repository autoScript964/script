package com.script.fairy;

import com.example.Answer;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2018/3/22 0022.
 */
public class CommonFunction {
    AtFairyImpl mFairy;
    private String img;
    PublicFunction publicFunction;
    Answer answer;
    public CommonFunction(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
        answer = new Answer(mFairy);
    }

    //做比较的并生成范围点击坐标
    public void Compare(float sim, FindResult result, String img) throws Exception {
        mFairy.onTap(sim,result,img,1000);
    }

    //随机5个像素点点击
    public void RndCompare(float sim, int x, int y, FindResult result, String str) throws InterruptedException {
        if (result.sim >= sim) {
            Random random = new Random();
            int X = random.nextInt(2) + x - 1;
            int Y = random.nextInt(2) + y - 1;
            LtLog.e(getLineInfo(str + ",==点击===" + X + "," + Y));
            mFairy.tap(X, Y);
            Thread.sleep(1000);
        }
    }

    //随机5个像素点点击
    public void RndCompare(int x, int y, String str) throws InterruptedException {
        Random random = new Random();
        int X = random.nextInt(2) + x - 1;
        int Y = random.nextInt(2) + y - 1;
        LtLog.e(getLineInfo(str + ",==点击===" + X + "," + Y));
        mFairy.tap(X, Y);
        Thread.sleep(1000);
    }

    public void RanSwipe(int x, int y, int x1, int y1, int dir, int sleep) throws InterruptedException {
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


    public void RanSwipe2(int x, int y, int x1, int y1, int sleep, int dir, int distance, int Size)throws Exception {
        //x,y,x1,y1 这是范围
        //dir = 0从上往下滑动，dir = 1从左往右滑动，dir = 2从下往上滑动，dir = 3从右往左滑动
        //sleep 滑动延时
        //设定一个弧度数
        int num1 = (x1 - x) / distance;
        mFairy.touchDown(2, x, y);
        for (int j = 1; j <= distance; j++) {
            int i = (int) (Math.random() * Size);
            int end = (int) (Math.random() * 20);
            LtLog.e(getLineInfo("J=" + j + "移动到 X=" + ((num1 * j) + x) + " y = " + (y + i)));
            if (j == distance) {
                mFairy.touchMove(2, ((num1 * j) + x), (y + end), sleep, dir);
            } else {
                mFairy.touchMove(2, ((num1 * j) + x), (y + i), sleep, dir);
            }
        }
        mFairy.touchUp(2);
    }

    public void delays(float sim, FindResult result, long s) throws InterruptedException {
        if (result.sim >= sim) {
            Thread.sleep(s);
        }
    }

    public String setImg(String img_png) throws InterruptedException {
        Thread.sleep(10);
        img = img_png;
        return img;
    }

    public String getImg() throws InterruptedException {
        Thread.sleep(10);
        return this.img;
    }

    public int Week() {
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            return 7;
        } else {
            return (cal.get(Calendar.DAY_OF_WEEK)) - 1;
        }
    }

    public int DateHour() {
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        String nowTime1 = format1.format(new Date());
        int hour = Integer.parseInt(nowTime1.split(":")[0]);
        return hour;
    }

    public int DateMinute() {
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        String nowTime1 = format1.format(new Date());
        int minute = Integer.parseInt(nowTime1.split(":")[1]);
        return minute;
    }


    public String getLineInfo(FindResult result, float usim, String str) throws InterruptedException {
        int x1, y1;
        Thread.sleep(10);
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        if (result.sim >= usim) {
            x1 = result.x;
            y1 = result.y;
            return ste.getFileName() + ": Line " + ste.getLineNumber() + ":sim=" + result.sim + ": IntX=" + x1 + ": IntY=" + y1 + ":img=" + str;
        }
        return "       ";
    }

    public String getLineInfo(String str) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getFileName() + ": Line " + ste.getLineNumber() + ":other==" + str;
    }

    public String getLineInfo(String str, int x, int y) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getFileName() + ": Line " + ste.getLineNumber() + ": IntX=" + x + ": IntY=" + y + ":other==" + str;
    }

    public String getLineInfo(float usim, int x, int y, FindResult result, String str) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        if (result.sim >= usim) {
            return ste.getFileName() + ": Line " + ste.getLineNumber() + ":sim=" + result.sim + ": IntX=" + x + ": IntY=" + y + ":img=" + str;
        }
        // return ste.getFileName() + ": Line " + ste.getLineNumber()+":sim="+result.sim+": IntX= -1: IntY= -1:img="+str;
        return "                            ";
    }

    public FindResult FindManyPic(int x_1, int y_1, int x_2, int y_2, String[] picName, int dayin) throws InterruptedException {
        //区域找多图返回最相似的那一张
        FindResult result;
        FindResult result1;
        int js_1 = 0;
        result = mFairy.findPic2(x_1, y_1, x_2, y_2, setImg(picName[0]));
        for (int i = 1; i < picName.length; i++) {
            result1 = mFairy.findPic2(x_1, y_1, x_2, y_2, setImg(picName[i]));
            if (result1.sim > result.sim) {
                result = result1;
                js_1 = i;
            }
        }
        if (dayin == 1) {
            LtLog.e(getLineInfo("最相似的图==" + js_1 + ",img=" + picName[js_1]));
        }
        return result;
    }


    public void HaoIhuadong()throws Exception{
        //好爱网址 http://feng.suanst.com/




        String result_ha = answer.haoai(315, 225, 597, 282,"8006");;

        if (!result_ha.equals("") && result_ha != null) {
            try {

                String[] aa = result_ha.split(",");

                int number = new Integer(aa[0]);
                LtLog.e(getLineInfo("坐标是X是" + (number + 315)));
                LtLog.e(getLineInfo("坐标是Y是" + (new Integer(aa[1]) + 225)));
                int X = (int) (Math.random() * 10);
                int Y = (int) (Math.random() * 10);
                RanSwipe2((350 + X), (531 + Y), (315 + number), 540, 500, 5, 3, 20);
                Thread.sleep(3000);


            } catch (Exception e) {
                LtLog.e(mFairy.getLineInfo("验证报错!!!"));
                LtLog.e(mFairy.getLineInfo("验证报错!!!"));
                LtLog.e(mFairy.getLineInfo("验证报错!!!"));
            }
        } else {
            LtLog.e(mFairy.getLineInfo("验证为空!!!"));
            LtLog.e(mFairy.getLineInfo("验证为空!!!"));
            LtLog.e(mFairy.getLineInfo("验证为空!!!"));
        }

    }






    public String testchange(int x1, int y1, int width, int height) throws InterruptedException {

//        result=publicFunction.localFindPic(537, 368, 651, 409, "actViolently1.png");
        Core.MinMaxLocResult mmr;

        Mat mat1 = mFairy.getScreenMat(x1, y1, width, height, 1, 0, 0, 1);

//        Thread.sleep(200);

        for (int i = 0; i < 10; i++) {
            Mat mat2 = mFairy.getScreenMat(x1, y1, width, height, 1, 0, 0, 1);
//            LtLog.i(publicFunction.getLineInfo() + "----------------------------mat2=" +mat2.size()+ "," + mat2.channels());
//        Imgcodecs.imwrite("/sdcard/2.png",mat2);

            Mat dst = new Mat();
            Core.absdiff(mat1, mat2, dst);
            Imgproc.cvtColor(dst, dst, Imgproc.COLOR_RGB2GRAY);
//            LtLog.i(publicFunction.getLineInfo() + "----------------------------answerStr=" +dst.size()+ "," + dst.channels());
            Imgproc.threshold(dst, dst, 0, 0, Imgproc.THRESH_TOZERO);

//            LtLog.i(publicFunction.getLineInfo() + "----------------------------answerStr=" +dst.size()+ "," + dst.channels());

            mmr = Core.minMaxLoc(dst);
            if (mmr.maxLoc.x > 0) {
                return ((int) mmr.maxLoc.x + x1) + "," + ((int) mmr.maxLoc.y + y1);
            }
//            LtLog.i(publicFunction.getLineInfo() + "----------------------------answerStr=" + mmr.maxVal  + "," + mmr.maxLoc.x + "," + mmr.maxLoc.y);
        }


        return "0,0";

    }

}
