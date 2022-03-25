package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;


/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class LimitlessTask {//无限任务

    private AtFairyImpl mFairy;
    private FindResult result;
    private SingleTask singleTask;
    private GamePublicFuntion gamePublicFuntion;
    private int hour;
    private int minute;
    private int week;

    public LimitlessTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(mFairy);
        singleTask = new SingleTask(mFairy);
    }

}