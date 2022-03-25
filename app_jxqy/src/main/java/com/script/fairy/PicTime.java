package com.script.fairy;

import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairy2;
import com.script.framework.AtFairyImpl;

/**
 * Created by Administrator on 2018-08-24.
 */

public class PicTime {
    //计算图片存在时间
    private long time=System.currentTimeMillis()/1000,timex=0;
    private int mx_1=0,my_1=0,mx_2=0,my_2=0;
    private String mPicName="";
    private double mSim=0;
    private int picX=0,picY=0;
    private AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    public PicTime(int x_1, int y_1, int x_2, int y_2, String picName, double sim, AtFairyImpl ypFairy){
        mx_1=x_1;
        my_1=y_1;
        mx_2=x_2;
        my_2=y_2;
        mPicName=picName;
        mSim=sim;
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
//        publicFunction=mFairy.publicFunction;
    }
    //返回图片存在的时间
    public long getPicTime(){
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(mx_1,my_1,mx_2,my_2,mPicName);
        if (result.sim >= mSim) {
            LtLog.i(publicFunction.getLineInfo() + "------>" + mPicName + "=" + result);
            picX=result.x;
            picY=result.y;
        }else {
            time=System.currentTimeMillis()/1000;
        }
        timex=System.currentTimeMillis()/1000 - time;
        return timex;
    }
    public void resetTime(){
        time=System.currentTimeMillis()/1000;
        timex=0;
    }

    public int getPicX(){
        return picX;

    }
    public int getPicY(){
        return picY;
    }

}
