package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;

/**
 * Created by Administrator on 2019/3/22 0022.
 */
public class Abnormal {
    AtFairyImpl mFairy;
    FindResult result;
    GamePublicFuntion gamePublicFuntion;

    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(mFairy);
    }

    public void erro() throws Exception {

        result = mFairy.findPic("erryp.png");
        mFairy.onTap(0.85f, result, "err启动", 3000);

        result = mFairy.findPic("wang.png");
        if(result.sim>0.8f){
            result = mFairy.findPic("wang_ok.png");
            mFairy.onTap(0.85f, result, "网络连接异常", 1000);
        }

        result = mFairy.findPic("lterr.png");
        mFairy.onTap(0.85f,result,"err聊天框提示",1000);

        result = mFairy.findPic("tongyi.png");
        mFairy.onTap(0.85f,result,"err 同意并继续",1000);

        result = mFairy.findPic("hdclose1.png");
        mFairy.onTap(0.85f,result,"err hdclose1",1000);
    }
}