package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.framework.AtFairyImpl;


/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class LimitlessTask {//无限任务
    public GamePublicFuntion gamePublicFuntion;
    public AtFairyImpl mFairy;
    private FindResult result;

    public LimitlessTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }



}
