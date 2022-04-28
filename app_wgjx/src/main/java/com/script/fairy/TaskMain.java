package com.script.fairy;

import com.script.content.ScProxy;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.*;

import org.json.JSONException;
import org.json.JSONObject;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class TaskMain extends TaskContent {
    AtFairyImpl mFairy;
    SingleTask singleTask;
    FindResult result;
    JSONObject optionJson = null;
    GamePublicFuntion gamePublicFuntion;
    private int taskId = 0;

    private static boolean SET_SWITCH = true;







    public TaskMain(AtFairyImpl ypFairy) throws Exception {
        super(ypFairy);
        singleTask = new SingleTask(ypFairy);
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        mFairy = ypFairy;
        mFairy.setGameName("万国觉醒");
        mFairy.setGameVersion(167);
        init();
    }

    private int week;
    private int hour;
    private int minute;

    public void main() throws Exception {

        ScProxy.config().Level().capturing(4);

        if (SET_SWITCH && (taskId != 2488) &&(taskId != 2480) && (taskId != 2508) ) {
            singleTask.set();
            SET_SWITCH = false;
        }

        switch (taskId) {
            case 2508:
                singleTask.dati(true);
                break;
            case 2468:
                singleTask.ts();
                break;
            case 2486:
                singleTask.battle();
                break;
            case 2482:
                singleTask.jiJie(true);
                break;
            case 2474:
                singleTask.ts_ling();
                break;
            case 2462:
                singleTask.guaJi();
                break;
            case 2488:
                singleTask.hanhua();
                break;
            case 2498:
                singleTask.dhm();
                singleTask.email();
                break;
            case 2480:

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
