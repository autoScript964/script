package com.script.fairy;

import com.script.content.ScProxy;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.json.JSONObject;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class TaskMain extends TaskContent {
    AtFairyImpl mFairy;
    SingleTask singleTask;
    TeamTask teamTask;
    LimitlessTask limitlessTask;
    FindResult result;
    JSONObject optionJson = null;
    GamePublicFuntion gamePublicFuntion;
    private int taskId = 0;

    public TaskMain(AtFairyImpl ypFairy) throws Exception {
        super(ypFairy);
        singleTask = new SingleTask(ypFairy);
        teamTask = new TeamTask(ypFairy);
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        limitlessTask = new LimitlessTask(ypFairy);
        mFairy = ypFairy;
        mFairy.setGameName("部落冲突");
        mFairy.setGameVersion(151);
        init();
    }

    private int week;
    private int hour;
    private int minute;
    public void main() throws Exception {
        ScProxy.config().Level().capturing(4);

        if (!AtFairyConfig.getOption("task_id").equals("")) {
            taskId = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }

        ScProxy.config().Level().capturing(4);

        switch (taskId) {
            case 2446:

                singleTask.wxBattle();
                break;
            case 2448:
                singleTask.jb();
                break;
            case 2460:
                singleTask.y_battle();
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
