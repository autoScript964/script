package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.framework.AtFairyImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2019/4/11.
 */

public abstract class TaskContent {
    private int taskContentNum = 0;
    private boolean taskContentEnd = false;
    private  AtFairyImpl mFairy;
    public  static int err = 0;
    public  Map<String,Integer> fm = new HashMap<>();
    public  Map<String,Long> tm = new HashMap<>();

    public void setTaskName(int taskContentNum) {
        err = 0;
        oneJudgeCount =0;
        twoJudgeCount =0;
        threeJudgeCount =0;
        this.taskContentNum = taskContentNum;
    }

    public void setTaskEnd() {
        err = 0;
        oneJudgeCount =0;
        twoJudgeCount =0;
        threeJudgeCount =0;
        taskContentEnd = true;
    }

    public TaskContent(AtFairyImpl mFairy) throws Exception {
        this.mFairy=mFairy;

        /*** 进入任务循环
         */
        initTaskContent();
    }

    public void initTaskContent()throws Exception{
        create();
        while (mFairy.condit()) {
            if (taskContentEnd) {
                LtLog.e("                                                                                        【 " + TaskMain.TASKNAME + " 任务结束,End!】");
                LtLog.e("                                                                                        【 切换任务中.....】");
                LtLog.e("                                                                                        【 切换任务中.....】");
                LtLog.e("                                                                                        【 切换任务中.....】");
                break;
            }

            if (taskContentNum == 0) {
                LtLog.e("                                                                                        【 " + TaskMain.TASKNAME + " init】");
                init();
                err = 0;
            }

            if (taskContentNum == 1) {
                LtLog.e("                                                                                        【 " + TaskMain.TASKNAME + " content_01  err:" + err + "】");
                content_01();
            }

            if (taskContentNum == 2) {
                LtLog.e("                                                                                        【 " + TaskMain.TASKNAME + " content_02  err:" + err + "】");
                content_02();
            }

            if (taskContentNum == 3) {
                LtLog.e("                                                                                        【 " + TaskMain.TASKNAME + " content_03  err:" + err + "】");
                content_03();
            }
            if (taskContentNum == 4) {
                LtLog.e("                                                                                        【 " + TaskMain.TASKNAME + " content_04  err:" + err + "】");
                content_04();
            }
            if (taskContentNum == 5) {
                LtLog.e("                                                                                        【 " + TaskMain.TASKNAME + " content_05  err:" + err + "】");
                content_05();
            }

            inOperation();
        }
    }

    void create() throws Exception{
        fm = new HashMap<String,Integer>();
        tm = new HashMap<String,Long>();
    }

    abstract void init() throws Exception;

    abstract void content_01() throws Exception;

    abstract void content_02() throws Exception;

    void inOperation() throws Exception {}

    void content_03() throws Exception {}

    void content_04() throws Exception {}

    void content_05() throws Exception {}

    /** 超时处理**/
    boolean timeCount(int num, int taskContentNum) {
        err++;
        if (err >= num) {
            if (taskContentNum == 99) {
                taskContentEnd = true;
            } else {
                this.taskContentNum = taskContentNum;
            }
            err = 0;
            return true;
        }
        return false;
    }

    /** 快速找图**/
    boolean mapCount(float sim,int x,int y,int x1,int y1,String name)throws Exception{
        for(int i =0;i<10;i++){
            FindResult result =  mFairy.findPic(x,y,x1,y1,name);
            if(result.sim>=sim){
                return true;
            }
        }
        return false;
    }

    boolean mapCount(float sim,String name)throws Exception{
        for(int i =0;i<10;i++){
            FindResult result =  mFairy.findPic(name);
            if(result.sim>=sim){
                return true;
            }
        }
        return false;
    }



    /**
     * 次数判断
     **/
    void frequencyInit(String key){
        if(fm.containsKey(key)){
            fm.put(key,0);
        }
    }

    boolean frequencyMap(String key,int frequen){
        if(fm.containsKey(key)){
            if(fm.get(key)>=frequen){
                fm.put(key,0);
                return true;
            }else{
                fm.put(key, fm.get(key)+1);
            }
        }else{
            fm.put(key,1);
        }
        return false;
    }

    boolean timeMap(String key,long sheep,boolean init){
        long s = System.currentTimeMillis();
        if(tm.containsKey(key)){
            if(s - tm.get(key)>=sheep){
                tm.put(key,s);
                return true;
            }
        }else{
            tm.put(key,s);
            return init;
        }
        return false;
    }

    /** 次数判断**/
    public int oneJudgeCount =0;
    boolean oneJudgeCount(int num)throws Exception{
        oneJudgeCount++;
        if(num<= oneJudgeCount){
            oneJudgeCount =0;
            return true;
        }
        return false;
    }//次数判断

    public int twoJudgeCount =0;
    boolean twoJudgeCount(int num)throws Exception{
        twoJudgeCount++;
        if(num<= twoJudgeCount){
            twoJudgeCount =0;
            return true;
        }
        return false;
    }//次数判断

    public int threeJudgeCount =0;
    boolean threeJudgeCount(int num)throws Exception{
        threeJudgeCount++;
        if(num<= threeJudgeCount){
            threeJudgeCount =0;
            return true;
        }
        return false;
    }//次数判断

    public int activityJudgeCount=0;
    boolean activityJudgeCount(int num)throws Exception{
        activityJudgeCount++;
        if(num<= activityJudgeCount){
            LtLog.e(mFairy.getLineInfo("没有找到此活动,End!"));
            LtLog.e(mFairy.getLineInfo("没有找到此活动,End!"));
            LtLog.e(mFairy.getLineInfo("没有找到此活动,End!"));
            activityJudgeCount =0;
            return true;
        }
        return false;
    }//活动结束次数判断

    public int oneSecond = 0;
    boolean oneSecond()throws Exception{
        if(oneSecond == 0){
            oneSecond =1;
            return true;
        }
        return false;
    }//只执行一次
    public int judgeOneSecond = 0;
    boolean judgeOneSecond()throws Exception{
        if(judgeOneSecond ==0){
            return true;
        }
        return false;
    }//可定义执行一次

    void timeMapInit(String key){
        if (tm.containsKey(key)) {
            tm.put(key, System.currentTimeMillis());
        }
    }

    boolean timeMap(String key, long sheep) {
        long s = System.currentTimeMillis();
        if (tm.containsKey(key)) {
            if (s - tm.get(key) >= sheep) {
                tm.put(key, s);
                return true;
            }
        } else {
            tm.put(key, s);
            return true;
        }
        return false;
    }

    /**
     *  内部类 */

    /** 滑动**/
    class Slide{
        private  int[] slideRangeIndex = new int[4];
        private  int moveTime = 1000;
        private  long endTime = 2500;
        private  int moveInitTime = 500;
        private  long endInitTime = 500;

        public Slide(int x,int y,int x1,int y1){
            slideRangeIndex[0]=x;
            slideRangeIndex[1]=y;
            slideRangeIndex[2]=x1;
            slideRangeIndex[3]=y1;
        }

        public  void slideRange(int[] count,int type,int init)throws Exception{
            if(count.length>0) {
                if (err==count[0]){
                    LtLog.e(mFairy.getLineInfo("slideRange init滑动>>>"));
                    LtLog.e(mFairy.getLineInfo(slideRangeIndex[0]+","+slideRangeIndex[1]+","+
                            slideRangeIndex[2]+","+slideRangeIndex[3]+""));
                    for(int i =0;i<5;i++) {
                        mFairy.ranSwipe(slideRangeIndex[0], slideRangeIndex[1], slideRangeIndex[2], slideRangeIndex[3], init, moveInitTime, endInitTime);
                    }
                    Thread.sleep(endTime);
                }

                for(int i = 1;i<count.length;i++){
                    if(err==count[i]){
                        LtLog.e(mFairy.getLineInfo("slideRange type滑动>>>"));
                        LtLog.e(mFairy.getLineInfo(slideRangeIndex[0]+","+slideRangeIndex[1]+","+
                                slideRangeIndex[2]+","+slideRangeIndex[3]+""));
                        mFairy.ranSwipe(slideRangeIndex[0],slideRangeIndex[1],slideRangeIndex[2],slideRangeIndex[3],type,moveTime,endTime);

                    }
                }

            }
        }

        public  void slideRange(int[] count,int type)throws Exception{
            if(count.length>0) {
                for(int i = 0;i<count.length;i++){
                    if(err==count[i]){
                        LtLog.e(mFairy.getLineInfo("slideRange type滑动>>>"));
                        LtLog.e(mFairy.getLineInfo(slideRangeIndex[0]+","+slideRangeIndex[1]+","+
                                slideRangeIndex[2]+","+slideRangeIndex[3]+""));
                        mFairy.ranSwipe(slideRangeIndex[0],slideRangeIndex[1],slideRangeIndex[2],slideRangeIndex[3],type,moveTime,endTime);
                    }
                }
            }
        }

        /****/
        public  void slideRange(int num,int[] count,int type,int init)throws Exception{
            if(count.length>0) {
                if (num==count[0]){
                    LtLog.e(mFairy.getLineInfo("slideRange init滑动>>>"));
                    LtLog.e(mFairy.getLineInfo(slideRangeIndex[0]+","+slideRangeIndex[1]+","+
                            slideRangeIndex[2]+","+slideRangeIndex[3]+""));
                    for(int i =0;i<5;i++) {
                        mFairy.ranSwipe(slideRangeIndex[0], slideRangeIndex[1], slideRangeIndex[2], slideRangeIndex[3], init, moveInitTime, endInitTime);
                    }
                    Thread.sleep(endTime);
                }

                for(int i = 1;i<count.length;i++){
                    if(num==count[i]){
                        LtLog.e(mFairy.getLineInfo("slideRange type滑动>>>"));
                        LtLog.e(mFairy.getLineInfo(slideRangeIndex[0]+","+slideRangeIndex[1]+","+
                                slideRangeIndex[2]+","+slideRangeIndex[3]+""));
                        mFairy.ranSwipe(slideRangeIndex[0],slideRangeIndex[1],slideRangeIndex[2],slideRangeIndex[3],type,moveTime,endTime);

                    }
                }
            }
        }

        public  void slideRange(int num,int[] count,int type)throws Exception{
            LtLog.e(mFairy.getLineInfo("slideRange >>>"));
            if(count.length>0) {
                for(int i = 0;i<count.length;i++){
                    if(num==count[i]){
                        LtLog.e(mFairy.getLineInfo("slideRange type滑动>>>"));
                        LtLog.e(mFairy.getLineInfo(slideRangeIndex[0]+","+slideRangeIndex[1]+","+
                                slideRangeIndex[2]+","+slideRangeIndex[3]+""));
                        mFairy.ranSwipe(slideRangeIndex[0],slideRangeIndex[1],slideRangeIndex[2],slideRangeIndex[3],type,moveTime,endTime);
                    }
                }
            }
        }
    }

    /** 计时**/
    class Time {
        public long time = 0;

        public void setTime(long time){
            this.time=time;
        }

        public boolean timeJudge(long t) {

            if (time == 0) {
                time = System.currentTimeMillis();
                return true;
            } else {
                if (System.currentTimeMillis() - time >= t) {
                    time=System.currentTimeMillis();
                    return true;
                }
            }
            return false;
        }
    }
}
