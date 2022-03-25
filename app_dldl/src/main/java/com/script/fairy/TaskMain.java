package com.script.fairy;

import com.script.content.ScProxy;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.json.JSONObject;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;


/**
 * Created by Administrator on 2019/1/24 0024.
 */

public class TaskMain {
    AtFairyImpl mFairy;
    SingleTask singleTask;
    JSONObject optionJson = null;
    GamePublicFuntion gamePublicFuntion;
    public static int FINISH;
    public static String TASKNAME = "";
    private int taskId = 0;

    public TaskMain(AtFairyImpl ypFairy) throws Exception {
        singleTask = new SingleTask(ypFairy);
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        mFairy = ypFairy;
        mFairy.setGameName("斗罗大陆");
        mFairy.setGameVersion(16);
        init();
    }
    public void main() throws Exception {


        ScProxy.config().Level().capturing(4);

        if (!AtFairyConfig.getOption("task_id").equals("")) {
            taskId = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }

        FINISH = 0;
        switch (taskId) {
            case 1522:
                singleTask.wx();
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
