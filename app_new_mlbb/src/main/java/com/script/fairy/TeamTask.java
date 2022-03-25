package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class TeamTask {//组队任务  带队 跟队
    AtFairyImpl mFairy;
    FindResult result;
    GamePublicFuntion gamePublicFunction;
    public TeamTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        gamePublicFunction = new GamePublicFuntion(mFairy);
    }


}

