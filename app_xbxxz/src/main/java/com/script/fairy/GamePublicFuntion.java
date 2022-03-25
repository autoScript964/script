package com.script.fairy;


import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;

/**
 * Created by user on 2019/2/15.
 */

public class GamePublicFuntion {
    public AtFairyImpl mFairy;
    public FindResult result;
    public  long time = System.currentTimeMillis();

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
    }

    int t=0;
    public  boolean timeJudge(long s) {
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
