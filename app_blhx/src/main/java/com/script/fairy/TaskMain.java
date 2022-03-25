package com.script.fairy;

import com.script.content.ScProxy;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;
import org.json.JSONException;
import org.json.JSONObject;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class TaskMain {
    AtFairyImpl mFairy;
    SingleTask singleTask;
    FindResult result;
    JSONObject optionJson = null;
    GamePublicFuntion gamePublicFuntion;
    public static int FINISH;
    public static String TASKNAME = "";
    private int taskId = 0;

    public TaskMain(AtFairyImpl ypFairy) throws Exception {
        singleTask = new SingleTask(ypFairy);
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        mFairy = ypFairy;
        mFairy.setGameName("碧蓝航线");
        mFairy.setGameVersion(62);
        init();
    }

    private int week;

    public void main() throws Exception {
        LtLog.e("ypf-99", "TaskMain-main::");
        // ScriptSdkProxy.init(mFairy);
        LtLog.e("ypf-99", "TaskMain-main::2");
        ScProxy.config().Level().capturing(4);

        if (!AtFairyConfig.getOption("task_id").equals("")) {
            taskId = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }

        FINISH = 0;
        switch (taskId) {
            case 1052:
                singleTask.wxwt();
                break;
            case 1066:
                singleTask.battle();
                break;
            case 1222:
                if(AtFairyConfig.getOption("yx").equals("1")){
                    singleTask.yx();
                }
                if(!AtFairyConfig.getOption("sc").equals("")){
                    week=mFairy.week();
                    if(week==1 || week==4 || week==7) {
                        int num = Integer.parseInt(AtFairyConfig.getOption("sc"));
                        singleTask.shangchuan(num);
                    }
                }

                if(!AtFairyConfig.getOption("zs").equals("")){
                    int num =Integer.parseInt(AtFairyConfig.getOption("zs"));
                    singleTask.zhanshu(num);
                }

                if(!AtFairyConfig.getOption("hy").equals("")){
                    week=mFairy.week();
                    if(week==2 || week==5 || week==7) {
                        int num = Integer.parseInt(AtFairyConfig.getOption("hy"));
                        singleTask.haiyu(num);
                    }
                }

                if(!AtFairyConfig.getOption("xd").equals("")){
                    week=mFairy.week();
                    if(week==3 || week==6 || week==7) {
                        int num = Integer.parseInt(AtFairyConfig.getOption("xd"));
                        singleTask.zhanshou(num);
                    }
                }
                break;
        }
        LtLog.e(mFairy.getLineInfo("勾选任务已经全部完成,End!" + AtFairyConfig.getTaskID()));
        mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
    }

    public void init() throws Exception {
        AtFairyConfig.initConfig();
        try {
            optionJson = new JSONObject(AtFairyConfig.getUserTaskConfig());
            LtLog.e("选项列表" + optionJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!AtFairyConfig.getOption("task_id").equals("")) {
            taskId = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }
    }
}
