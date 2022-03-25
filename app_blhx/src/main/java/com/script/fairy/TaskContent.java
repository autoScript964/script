package com.script.fairy;

import com.script.opencvapi.LtLog;
import com.script.framework.AtFairyImpl;

/**
 * Created by user on 2019/4/11.
 */

public abstract class TaskContent {
    private int taskContentNum = 0;
    private boolean taskContentEnd = false;
    int err=0;

    public void setTaskName(int taskContentNum){
        err=0;
        this.taskContentNum=taskContentNum;
    }
    public void setTaskEnd(){
        err=0;
        taskContentEnd = true;
    }

    public TaskContent(AtFairyImpl mFairy) throws Exception {
        create();
        while (mFairy.condit()) {
            if (taskContentEnd) {
                LtLog.e("                                                                                        【 "+TaskMain.TASKNAME+" 任务结束,End!】");
                TaskMain.TASKNAME="切换任务中.....";
                break;
            }

            inOperation();

            if (taskContentNum == 0) {
                LtLog.e("                                                                                        【 "+TaskMain.TASKNAME+" init】");
                init();
                err=0;
            }

            if (taskContentNum == 1) {
                LtLog.e("                                                                                        【 "+TaskMain.TASKNAME+" content_01  err:"+err+"】");
                content_01();
            }
            if (taskContentNum == 2) {
                LtLog.e("                                                                                        【 "+TaskMain.TASKNAME+" content_02  err:"+err+"】");
                content_02();
            }
            if (taskContentNum == 3) {
                LtLog.e("                                                                                        【 "+TaskMain.TASKNAME+" content_03  err:"+err+"】");
                content_03();
            }
            if (taskContentNum == 4) {
                LtLog.e("                                                                                        【 "+TaskMain.TASKNAME+" content_04  err:"+err+"】");
                content_04();
            }
            if (taskContentNum == 5) {
                LtLog.e("                                                                                        【 "+TaskMain.TASKNAME+" content_05  err:"+err+"】");
                content_05();
            }
        }
    }

    abstract void create()throws Exception;

    abstract void init() throws Exception;

    abstract void content_01() throws Exception;

    abstract void content_02() throws Exception;

    void inOperation()throws Exception{}

    void overtime(int num,int taskContentNum){
        err++;
        if(err>=num){
            if(taskContentNum==99){
                taskContentEnd=true;
            }else {
                this.taskContentNum = taskContentNum;
            }
            err=0;
        }
    }

    void content_03() throws Exception {}

    void content_04() throws Exception {}

    void content_05() throws Exception {}

}
