package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;

/**
 * Created by user on 2019/6/3.
 */
public class TeamTask {
    public GamePublicFuntion gamePublicFuntion;
    public FindResult result;
    public AtFairyImpl mFairy;
    public LimitlessTask limitlessTask;
    public SingleTask singleTask;

    public TeamTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        limitlessTask = new LimitlessTask(ypFairy);
        singleTask = new SingleTask(ypFairy);
    }
}