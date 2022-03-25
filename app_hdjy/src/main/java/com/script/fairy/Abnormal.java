package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;


public class Abnormal {
    AtFairyImpl mFairy;
    FindResult result;
    GamePublicFuntion gamePublicFuntion;
    private int login = 1;
    public static boolean HONG = false;
    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(mFairy);
    }

    public void erro() throws Exception {

        result = mFairy.findPic("erryp.png");
        mFairy.onTap(0.85f, result, "err启动", 1000);

        result = mFairy.findPic(1199,184,1274,265,"jia4.png");
        mFairy.onTap(0.85f, result, "err有人送花", 1000);

        result = mFairy.findPic("game_login1.png");
        mFairy.onTap(0.85f, result, "err进入游戏", 1000);

        result = mFairy.findPic("game_login2.png");
        mFairy.onTap(0.85f, result, "err进入游戏", 1000);

        result = mFairy.findPic("jiesuo.png");
        mFairy.onTap(0.85f, result, 623,636,664,658,"err宠物解锁技能", 1000);

    }

}