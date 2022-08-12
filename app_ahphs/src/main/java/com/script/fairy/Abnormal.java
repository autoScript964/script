package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class Abnormal  {
    AtFairyImpl mFairy;
    FindResult result;
    GamePublicFuntion gamePublicFuntion;

    static long thTime =System.currentTimeMillis();

    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion=new GamePublicFuntion(mFairy);
    }
    //全局处理

    public long time = System.currentTimeMillis();


    public long time_qx = System.currentTimeMillis();

    public void erro() throws Exception {

        thTime =System.currentTimeMillis();


        result = mFairy.findPic("jieshou.png");
        mFairy.onTap(0.8f,result,"接受",1000);

        result = mFairy.findPic("zhidao.png");
        mFairy.onTap(0.8f,result,"知道了",1000);

        result = mFairy.findPic(577,196,664,597,"ok.png");
        mFairy.onTap(0.8f,result,"确定 err",1000);

        result = mFairy.findPic(690,423,763,497,"xun1.png");
        mFairy.onTap(0.8f,result,"寻路 err",1000);

        result = mFairy.findPic(487,608,841,693,"login1.png");
        mFairy.onTap(0.8f,result,"点屏幕进入游戏",1000);

        result = mFairy.findPic("login2.png");
        mFairy.onTap(0.8f,result,"进入游戏",3000);

        if(AtFairyConfig.getOption("shiqu").equals("1")){
            result = mFairy.findPic(842,307,1004,432,"shiqu.png");
            mFairy.onTap(0.8f,result,"拾取",300);
        }

        result = mFairy.findPic(302,243,651,637,"qx.png");
        if(result.sim>0.8f){
            if(System.currentTimeMillis()-time_qx>6000){
                mFairy.onTap(0.8f,result,"取消 err",1000);
            }

        }else{
            time_qx = System.currentTimeMillis();
        }


    }
}
