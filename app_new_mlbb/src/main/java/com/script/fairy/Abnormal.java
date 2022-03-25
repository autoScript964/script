package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;


public class Abnormal {
    AtFairyImpl mFairy;
    FindResult result;
    GamePublicFuntion gamePublicFuntion;
    int index=0;
    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(mFairy);
    }

    public void erro() throws Exception {
        result = mFairy.findPic("leftZoom.png");
        mFairy.onTap(0.9f, result,  "左侧缩放栏", 1000);

        result = mFairy.findPic("rightZoom.png");
         mFairy.onTap(0.9f, result,  "右侧缩放栏", 1000);

        result =  mFairy.findPic(553,391,733,514,"ok.png");
         mFairy.onTap(0.9f, result,  "确定", 1000);

        result = mFairy.findPic("battleEnd.png");
        mFairy.onTap(0.80f,result,979,199,980,200,"战斗失败!",2000);

        result = mFairy.findPic("battleEnd1.png");
        mFairy.onTap(0.80f,result,979,199,980,200,"战斗失败!",2000);

        result = mFairy.findPic("battleEnd2.png");
        mFairy.onTap(0.80f,result,979,199,980,200,"战斗失败!",2000);

        result = mFairy.findPic("login.png");
        if(result.sim>0.8){
            Thread.sleep(3000);
        }
         mFairy.onTap(0.85f, result,  "登录", 1000);

        result = mFairy.findPic("g.png");
         mFairy.onTap(0.85f, result,  "关闭", 1000);

        result = mFairy.findPic("g1.png");
        if(result.sim>0.85){
            index=index+1;
        }else {
            index=0;
        }
        if (index>10){
             mFairy.onTap(0.85f, result,  "继续游戏", 1000);
        }
        result=mFairy.findPic("qx.png");
         mFairy.onTap(0.85f,result,"取消",1000);

    }
}