package com.script.fairy;

import android.util.Log;

import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.utils.Utils;
import com.script.framework.AtFairyImpl;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Administrator on 2018/6/19.
 */

public class CheckThread implements Runnable {

    private AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    private int index = 0;
    private Object lockCheck = new Object();
    private boolean mIsStoped = true;
    private long mTime = System.currentTimeMillis() / 1000, mTimex = 0;
    private long mTime1 = System.currentTimeMillis() / 1000, mTimex1 = 0;

    //        result = publicFunction.localFindPic(392, 229, 885, 413, "IDreplace.png");

    private PicTime picTime;

    public CheckThread(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
        picTime=new PicTime(392, 229, 885, 413, "IDreplace.png",0.8,mFairy);
    }

    @Override
    public void run() {

        MatTime matTime=new MatTime(mFairy);
        long sleep=0;
        boolean playerStopedStart=false;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockCheck) {
                while (this.mIsStoped ) {
                    try {
                        matTime.resetTime();
                        LtLog.i(publicFunction.getLineInfo() + "+++++++++++-+++-----------CheckTask.wait-->=");
                        lockCheck.wait();
                        LtLog.i(publicFunction.getLineInfo() + "+++++++++++-+++-----------CheckTask.state-->=");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (index >= 50) {
                index = 0;
            } else {
                index = index + 1;
            }

            try {
                mCheckThread();
                sleep=matTime.mMatTime(1174,137,70,471,0.9f);

                if(sleep>=600 && playerStopedStart==false){
                    LtLog.i(publicFunction.getLineInfo() + "+++++++++++------mFairy.playerStoped()+++++++++++-------=,sleep=" + sleep);
                    mFairy.playerStoped();
                    matTime.resetTime();
                    playerStopedStart=true;
                }
            } catch (Exception e) {
                LtLog.i(e.toString());
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = sw.toString();
                LtLog.i("============================error" + str);
            }
        }
    }

    private void mCheckThread() throws Exception {
        AtFairy2.OpencvResult result;
        boolean landStart = false;
        boolean resetGameStare = false;
//        result = publicFunction.localFindPic(392, 229, 885, 413, "IDreplace.png");
//        if (result.sim >= 0.8) {
//            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++++---------IDreplace=" + result);
//            mFairy.UpState(575);
//            Utils.sleep(60000);
//        }
        if(picTime.getPicTime()>=60){
            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++++---------IDreplace=" );
            new TaskMain(mFairy).UpState(575);
            Utils.sleep(60000);
            picTime.resetTime();
        }

        //登陆界面
        landStart = Land();
        if (landStart) {
            mTimex = System.currentTimeMillis() / 1000 - mTime;
            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++++---------mTimex=" + mTimex);
            int outTiem;
            if(AtFairyConfig.getOption("task_id").isEmpty()){
                 //如果task_id 为空，在登陆界面5分钟后上报状态
                 outTiem=300;
            }else {
                //如果task_id 不为空，在登陆界面30分钟后上报状态
                outTiem=1800;
            }
            if (mTimex >= outTiem) {
                new TaskMain(mFairy).UpState(577);
                Utils.sleep(60000);
            }
        } else {
            mTime = System.currentTimeMillis() / 1000;
        }
        resetGameStare=resetGame();
        if(resetGameStare){
            mTimex1 = System.currentTimeMillis() / 1000 - mTime1;
            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++++---------mTimex1=" + mTimex1);
            if(mTimex1>=300){
                new TaskMain(mFairy).UpState(581);
                Utils.sleep(60000);
            }
        }else {
            mTime1 = System.currentTimeMillis() / 1000;
        }
    }
    //防沉迷判断
    private boolean resetGame() throws InterruptedException {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(662, 629, 845, 711, "sleep.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++--------------sleep=" + result);
            return true;
        }
        result = publicFunction.localFindPic(604,248,885,382, "err5.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++-------->err5=" + result);
            return true;
        }
        return false;
    }

    //登陆界面
    private boolean Land() throws InterruptedException {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(286, 744, 428, 976, "Land.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++--------------Land=" + result);
            return true;
        }
        result = publicFunction.localFindPic(491, 614, 757, 691, "startUp1.png|startUp.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++--------------startUp=" + result);
            return true;
        }
        result = publicFunction.localFindPic(189, 57, 442, 152, "notice.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++--------------notice=" + result);
            return true;
        }
        result = publicFunction.localFindPic(207, 24, 520, 143, "agreement.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++--------------agreement=" + result);
            return true;
        }
        result = publicFunction.localFindPic(528, 427, 661, 659, "login.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++--------------login=" + result);
            return true;
        }
        result = publicFunction.localFindPic(1014, 587, 1147, 719, "login1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++--------------login=" + result);
            return true;
        }
        result = publicFunction.localFindPic(712, 462, 849, 666, "QQ.png" + "|" + "QQ1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++--------------QQ=" + result);
            return true;
        }
        result = publicFunction.localFindPic(257,376,448,479, "Land1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "++++++++++++++++++++++--------------Land1=" + result);
            return true;
        }
        return false;
    }

    public void start() {
        LtLog.i(publicFunction.getLineInfo() + "+++++++++++-+++-----启动监控------CheckTask.restart-->mIsStoped=" + this.mIsStoped);
        this.mIsStoped = false;
        Utils.sleep(100);
        synchronized (lockCheck) {
            lockCheck.notify();
        }
    }

    public void stop() {
        LtLog.i(publicFunction.getLineInfo() + "++++++++++++++----停止监控-------CheckTask.stop-->mIsStoped=" + this.mIsStoped);
        synchronized (lockCheck) {
            this.mIsStoped = true;
            lockCheck.notify();
        }
        LtLog.i(publicFunction.getLineInfo() + "+++++++++++++++++++++++++CheckTask.stop-->mIsStoped=" + this.mIsStoped);
    }
}
