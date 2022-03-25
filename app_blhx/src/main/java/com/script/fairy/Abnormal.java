package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
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

        result=mFairy.findPic(508,329,795,718,"jx.png");
        mFairy.onTap(0.8f,result,"点击继续",2000);

        result=mFairy.findPic(450,535,695,679,"shibai.png");
        mFairy.onTap(0.8f,result,"战斗失败",2000);

        result=mFairy.findPic(1091,1,1276,80,"sk.png");
        mFairy.onTap(0.8f,result,"skip",1000);
    }

}
