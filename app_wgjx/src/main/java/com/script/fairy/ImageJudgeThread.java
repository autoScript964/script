package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.framework.AtFairyImpl;

/**
 * Created by user on 2020/10/21.
 */

public abstract class ImageJudgeThread implements Runnable{
    public static boolean EXIT = false;

    public static String TASK = "";

    abstract void threadMethod()throws Exception;
    public void run(){
        try{
            int err = 0;
            while (!EXIT){
                threadMethod();
                err++;
                if(err>50) {
                    LtLog.e(Thread.currentThread().getName() + " 循环了 " + err);
                    Thread.sleep(100);
                    return;
                }
            }
            LtLog.e(Thread.currentThread().getName()+"线程结束了");
        }catch (Exception e){
            e.printStackTrace();
            LtLog.e(e.getMessage());
        }
    }
}

/*
    if (range==null){
                    result = mFairy.findPic(arrayImage);
                    LtLog.e(mFairy.getLineInfo("【相似度 ："+result.sim+"】"));
                    mFairy.onTap(sim,result,"【点击】",500);
                }else{
                    result = mFairy.findPic(range[0],range[1],range[2],range[3],arrayImage);
                    LtLog.e(mFairy.getLineInfo("【相似度 ："+result.sim+"】"));
                    mFairy.onTap(sim,result,"【点击】",500);
                }

 */