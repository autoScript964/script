package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;

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
    }

}
