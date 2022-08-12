package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class Abnormal  {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    Util util;
    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        util = new Util(ypFairy);
    }
    int js_1=0,js_2=0;
    //全局处理
    public void erro() throws Exception {
        Thread.sleep(2000);
        LtLog.e(mFairy.getLineInfo("异常运行中"));
        result = mFairy.findPic(8, 8, 678, 244, "djjx.png");
        if (result.sim>0.8f){
            js_1++;
            if (js_1>1){
                mFairy.onTap(0.7f, result, "err点击任意地方继续", 1000);
            }
        }else {
            js_1=0;
        }

        result = mFairy.findPic(376, 210, 916, 514, "qx.png");
        if (result.sim>0.8f){
            js_2++;
            if (js_2>5){
                mFairy.onTap(0.8f, result, "err取消", 1000);
            }
        }else {
            js_2=0;
        }


        result = mFairy.findPic("dialogue.png");
        mFairy.onTap(0.8f, result, "err对话框被打开了", 1000);

        result = mFairy.findPic(1090, 24, 1277, 124, "skip.png");
        mFairy.onTap(0.7f, result, "err跳过", 1000);


        result = mFairy.findPic(1134, 375, 1203, 440, "errorfork1.png");
        result1 = mFairy.findPic("used.png");
        if (result.sim>0.8f&& result1.sim>0.8f){
            mFairy.onTap(0.8f, result1, "err使用", 1000);
        }else if (result.sim>0.8f && result1.sim<0.8f){
            mFairy.onTap(0.8f, result, "err关闭右下角物品的叉", 1000);
        }
        result = mFairy.findPic("wrap_up.png");
        LtLog.e(mFairy.getLineInfo(0.9f,result,"err包满了"));
        if (result.sim>0.9f){
            mFairy.finish(AtFairyConfig.getTaskID(),39);
        }
        result = mFairy.findPic("wfgm.png");
        LtLog.e(mFairy.getLineInfo(0.9f,result,"err宠物满了,无法购买"));
        if (result.sim>0.9f){
            mFairy.finish(AtFairyConfig.getTaskID(),39);
        }

        result = mFairy.findPic(436,270,837,366, "rysd.png");
        mFairy.onTap(0.8f, result, 496,427,563,446,"err如意刷道提醒取消", 1000);

        result = mFairy.findPic(401,242,871,387, "qmx.png");
        mFairy.onTap(0.8f, result, 722,420,775,435,"err驱魔香确定", 1000);

        result = mFairy.findPic("yp.png");
        mFairy.onTap(0.8f, result, "启动游戏", 1000);

        result = mFairy.findPic("ty.png");
        mFairy.onTap(0.8f, result, "同意并进入", 1000);

        result = mFairy.findPic(412,287,693,487,"jy.png");
        mFairy.onTap(0.8f, result, 727,444,751,454,"确定更新", 1000);

        result = mFairy.findPic("gongg.png");
        mFairy.onTap(0.8f, result, 1082,56,1096,64,"关闭公告", 1000);

        result = mFairy.findPic("login.png");
        mFairy.onTap(0.8f, result, "进入游戏", 1000);


        result = mFairy.findPic(533, 72, 764, 185, "laojun.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo(0.8f,result,"老君查岗了"));
/*
            Mat mat3 = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1);
            //将图片存入路径
            //Mat转byte[]
            Imgcodecs.imwrite("/sdcard/screen1111.png", mat3);*/

            int x=249, y=233,x_1=1025,y_1=238;
            for (int i=0;i<100;i++){
                mFairy.touchDown(2,x,y);
                mFairy.touchMove(2,x_1,y_1,1500);
                mFairy.touchUp(2);
                y=y+10;
                y_1=y_1+10;
                if (y>490){
                    break;
                }
            }
            util.Laojun();
            Thread.sleep(1000);
            mFairy.condit();
        }

        result = mFairy.findPic("Agree.png");
        mFairy.onTap(0.8f, result,"err投票同意", 1000);

        result = mFairy.findPic("zdsw.png");
        mFairy.onTap(0.8f, result,624,617,658,633,"err战斗死亡", 1000);

        if (AtFairyConfig.getOption("jjctp").equals("")) {
            result = mFairy.findPic("auto.png");
            mFairy.onTap(0.8f, result, "开启自动战斗", 1000);
        }

    }


}
