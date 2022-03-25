package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.framework.AtFairyImpl;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class Abnormal {
    AtFairyImpl mFairy;
    FindResult result;
    private boolean qiang = false;

    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;

    }


    //全局处理
    public void erro() throws Exception {
        Thread.sleep(100);

        result = mFairy.findPic2(153,873,354,1000,"qqlogin1.png");
        mFairy.onTap(0.85f, result, "errQQ登录重新获取", 1000);

        result = mFairy.findPic2(243,887,473,996,"qqlogin.png");
        mFairy.onTap(0.85f, result, "errQQ登录", 1000);

        result = mFairy.findPic2("erryp.png");
        mFairy.onTap(0.85f, result, "err启动", 1000);

        result = mFairy.findPic( "login.png");
        mFairy.onTap(0.8f, result, "进入游戏", 1000);

        result = mFairy.findPic( "login1.png");
        mFairy.onTap(0.8f, result, "进入游戏1", 1000);

    }

}
