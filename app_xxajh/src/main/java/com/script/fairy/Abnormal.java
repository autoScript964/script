package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;

/**
 * Created by Administrator on 2019/3/22 0022.
 */
public class Abnormal {
    AtFairyImpl mFairy;
    FindResult result;
    GamePublicFuntion gamePublicFuntion;
    private int login = 1;
    public static boolean HONG = false;
    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(mFairy);

        if(!AtFairyConfig.getOption("login").equals("")){
            login=Integer.parseInt(AtFairyConfig.getOption("login"));
        }

        if(AtFairyConfig.getOption("hong").equals("1")){
            HONG=true;
        }
    }

    public void erro() throws Exception {

        result = mFairy.findPic("erryp.png");
        mFairy.onTap(0.85f, result, "err启动", 1000);

        result = mFairy.findPic("esc2.png");
        mFairy.onTap(0.85f, result, "err 关闭活动签到界面窗口 ", 1000);

        result = mFairy.findPic("wfjx.png");
        mFairy.onTap(0.85f, result, 1168,96,1176,104,"err玩法教学", 1000);

        result = mFairy.findPic("close8.png");
        mFairy.onTap(0.85f, result, "err关闭礼包弹框", 1000);

        result = mFairy.findPic(376,470,916,671,"jia3.png");
        mFairy.onTap(0.85f, result, 1093,78,1114,105,"err关闭礼包弹框", 1000);

        result = mFairy.findPic(350,338,598,493,"sh.png");
        mFairy.onTap(0.85f, result, "err稍后处理", 1000);

        result = mFairy.findPic("loginGame1.png");
        mFairy.onTap(0.85f, result, "err进入游戏", 1000);

        result = mFairy.findPic("loginGame3.png");
        mFairy.onTap(0.85f, result, "err进入游戏", 1000);

        result = mFairy.findPic(366,160,898,390,"login1.png");
        mFairy.onTap(0.85f, result, 788,417,835,437,"err下载完成，进入游戏", 1000);

        result = mFairy.findPic(342,274,435,398,"hong1.png");
        mFairy.onTap(0.85f, result, "err红包1", 1500);

        result = mFairy.findPic(518,172,677,241,"hong2.png");
        mFairy.onTap(0.85f, result, 848,582,873,602,    "err红包2", 1000);

        result = mFairy.findPic("shengdian.png");
        mFairy.onTap(0.85f, result, "err省电模式", 1000);
    }

}