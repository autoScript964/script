package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.json.JSONObject;


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
        mFairy.setGameName("想不想修真");
        mFairy.setGameVersion(16);
        init();
    }

    public void main() throws Exception {

        FINISH = 0;
        switch (taskId) {
            case 1504:
                singleTask.wx();
                break;
        }
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
