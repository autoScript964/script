package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;



public class Abnormal {

    AtFairyImpl mFairy;
    FindResult result;
    PublicFunction publicFunction;
    GamePublicFuntion gamePublicFuntion;

    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
        gamePublicFuntion = new GamePublicFuntion(mFairy);
    }
    public void erro() throws Exception {

        result=mFairy.findPic(292,586,425,894,"ok.png");
        mFairy.onTap(0.85f,result,"确定",1000);

        result=mFairy.findPic(292,586,425,894,"ok.png");
        mFairy.onTap(0.85f,result,"确定",1000);

        result=mFairy.findPic(217,851,486,1261,"kong.png");
        mFairy.onTap(0.8f,result,"点击空白处",1000);

        result=mFairy.findPic("err1.png");
        mFairy.onTap(0.85f,result,627,278,650,294,"获得英雄",500);

        result = mFairy.findPic("erryp.png");
        mFairy.onTap(0.8f, result, "启动", 5000);

        result = mFairy.findPic("gengxin.png");
        mFairy.onTap(0.8f, result, "更新", 3000);

        result = mFairy.findPic("hong3.png");
        mFairy.onTap(0.8f, result, 38,101,83,146,"guanbi", 3000);

        result=mFairy.findPic("qq.png");
        mFairy.onTap(0.85f,result,"qq登录",1000);

        result = mFairy.findPic(134, 742, 572, 1129, "qqlogin1.png");
        mFairy.onTap(0.85f, result, "errQQ登录重新获取", 2000);

        result = mFairy.findPic(134, 742, 572, 1129, "qqlogin2.png");
        mFairy.onTap(0.85f, result, "errQQ登录授权登录", 2000);

        result = mFairy.findPic(134, 742, 572, 1129, "qqlogin3.png");
        mFairy.onTap(0.85f, result, "errQQ登录重新获取", 2000);

        result = mFairy.findPic(134, 742, 572, 1129, "qqlogin.png");
        mFairy.onTap(0.85f, result, "errQQ登录", 2000);

        result=mFairy.findPic("login.png");
        mFairy.onTap(0.85f,result,"进入游戏",1000);

        result=mFairy.findPic("xinban.png");
        if(result.sim>0.85f){
            mFairy.killUserGame();
            Thread.sleep(3000);
        }

        result=mFairy.findPic(192,475,524,671,"xinban1.png");
        mFairy.onTap(0.8f,result,341,724,377,740,"新版",500);

        result=mFairy.findPic("jietu.png");
        mFairy.onTap(0.85f,result,"截图确定",500);

        result=mFairy.findPic("ti1.png");
        mFairy.onTap(0.85f,result,479,1204,531,1227,"全区排名",1000);

        result=mFairy.findPic(78,548,348,742,"shengji.png");
        mFairy.onTap(0.85f,result,214,1176,255,1194,"角色升级",1000);

        result=mFairy.findPic("guizu3.png");
        mFairy.onTap(0.85f,result,324,1080,421,1115,"贵族升级",1000);

        result=mFairy.findPic("jiesuo.png");
        mFairy.onTap(0.85f,result,330,1083,387,1102,"新兵种解锁",1000);

        result=mFairy.findPic("shuoming.png");
        mFairy.onTap(0.85f,result,649,134,667,154,"采集说明",1000);

        result=mFairy.findPic("guizu5.png");
        mFairy.onTap(0.85f,result,108,20,155,46,"队列不足",1000);

        result=mFairy.findPic(359,101,489,215,"gonglei.png");
        mFairy.onTap(0.85f,result,648,135,669,153,"攻略",1000);

        result=mFairy.findPic(56,485,363,653,"kaizhao5.png");
        mFairy.onTap(0.85f,result,342,835,385,855,"开罩解除提示",1000);

        result = mFairy.findPic(195, 874, 527, 1029,new String[]{"nn11.png","ok1.png","sb1.png"});
        mFairy.onTap(0.8f, result, "确定", 500);

        result=mFairy.findPic("gongxi.png");
        mFairy.onTap(0.85f,result,333,1127,408,1174,"err弹框",1000);

        result=mFairy.findPic("fanhui.png");
        mFairy.onTap(0.85f,result,"返回游戏",1000);
    }
}