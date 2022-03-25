package com.script.fairy;


import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;
import java.util.List;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

/**
 * Created by user on 2019/2/15.
 */

public class GamePublicFuntion {
    public AtFairyImpl mFairy;
    public FindResult result;
    public static List<String> list;
    public static int activity = 0;
    public long time = System.currentTimeMillis();

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        list = new ArrayList<>();
    }
    int t =0;
    public boolean timeJudge(long s) {
        if(t==0){
            t=1;
            return true;
        }

        if (System.currentTimeMillis() - time > s) {
            time = System.currentTimeMillis();
            return true;
        }
        return false;
    }//time判断

}
