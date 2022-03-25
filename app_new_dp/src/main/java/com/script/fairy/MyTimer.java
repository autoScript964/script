package com.script.fairy;
/**
 * Created by Administrator on 2018-07-11.
 */

public class MyTimer {

    long time=System.currentTimeMillis()/1000,timex=0;
    public long myTiming(){
        timex=System.currentTimeMillis()/1000-time;
        return timex;
    }
    public int countDown(int second){
        timex=System.currentTimeMillis()/1000-time;

        int countDown=second-(int)timex;

        return countDown;
    }

    public void resetTime(){
        time=System.currentTimeMillis()/1000;
    }
}
