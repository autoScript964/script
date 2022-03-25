package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.framework.AtFairyImpl;

/**
 * Created by user on 2019/4/11.
 */

public abstract class TaskContent {
    private int taskContentNum = 0;
    private boolean taskContentEnd = false;
    private  AtFairyImpl mFairy;
    private String name;
    public static int err = 0;
    public GamePublicFuntion gamePublicFuntion;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

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

    public TaskContent(AtFairyImpl mFairy,String name) throws Exception {
        gamePublicFuntion=new GamePublicFuntion(mFairy);
        this.mFairy=mFairy;
        this.name=name;
        /*** 进入任务循环
         */
        initTaskContent(name);
    }

    public void initTaskContent(String name)throws Exception{
        create();
        while (mFairy.condit()) {
            if (taskContentEnd) {
                LtLog.e("                                                                                        【 " + getName() + " 任务结束,End!】");
                LtLog.e("                                                                                        【 切换任务中.....】");
                LtLog.e("                                                                                        【 切换任务中.....】");
                LtLog.e("                                                                                        【 切换任务中.....】");
                break;
            }

            inOperation();

            if (taskContentNum == 0) {
                LtLog.e("                                                                                        【 " + getName() + " init】");
                init();
                err = 0;
            }

            if (taskContentNum == 1) {
                LtLog.e("                                                                                        【 " + getName() + " content_01  err:" + err + "】");
                content_01();
            }

            if (taskContentNum == 2) {
                LtLog.e("                                                                                        【 " + getName() + " content_02  err:" + err + "】");
                content_02();
            }

            if (taskContentNum == 3) {
                LtLog.e("                                                                                        【 " + getName() + " content_03  err:" + err + "】");
                content_03();
            }
            if (taskContentNum == 4) {
                LtLog.e("                                                                                        【 " + getName() + " content_04  err:" + err + "】");
                content_04();
            }
            if (taskContentNum == 5) {
                LtLog.e("                                                                                        【 " + getName() + " content_05  err:" + err + "】");
                content_05();
            }
        }
    }

    void create() throws Exception{}

    void init() throws Exception{
        gamePublicFuntion.init();
        setTaskName(1);
    }

    abstract void content_01() throws Exception;

    void content_02() throws Exception{}

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
    boolean mapCount(float sim,int x,int y,int x1,int y1,String[] name)throws Exception{
        for(int i =0;i<5;i++){

            for(int j = 0;j<name.length;j++){
                FindResult result =  mFairy.findPic(x,y,x1,y1,name[j]);
                if(result.sim>=sim){
                    return true;
                }
            }

        }
        return false;
    }

    boolean mapCount(float sim,String name)throws Exception{
        for(int i =0;i<5;i++){

            FindResult result =  mFairy.findPic(name);
            if(result.sim>=sim){
                return true;
            }
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

    public int oneSecond = 0;
    boolean oneSecond()throws Exception{
        if(oneSecond ==0){
            oneSecond =1;
            return true;
        }
        return false;
    }

    public int judgeOneSecond = 0;
    boolean judgeOneSecond()throws Exception{
        if(judgeOneSecond ==0){
            return true;
        }
        return false;
    }

    public boolean slide(int i)throws Exception{
        if(err%i==0 && err!=0){
            return true;
        }
        return false;
    }//
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
                    for(int i =0;i<3;i++) {
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
                    for(int i =0;i<3;i++) {
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
