package com.script.fairy;

import com.script.content.ScProxy;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.json.JSONObject;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class TaskMain extends TaskContent {
    AtFairyImpl mFairy;
    SingleTask singleTask;
    JSONObject optionJson = null;
    GamePublicFuntion gamePublicFunction;
    LimitlessTask limitlessTask;
    TeamTask teamTask;
    private int taskId = 0;

    public TaskMain(AtFairyImpl ypFairy) throws Exception {
        super(ypFairy);
        singleTask = new SingleTask(ypFairy);
        gamePublicFunction = new GamePublicFuntion(ypFairy);
        limitlessTask = new LimitlessTask(ypFairy);
        teamTask = new TeamTask(ypFairy);
        mFairy = ypFairy;
        mFairy.setGameName("魔力宝贝");
        mFairy.setGameVersion(139);
        init();
    }

    public void main() throws Exception {

        ScProxy.config().Level().capturing(4);

        if (!AtFairyConfig.getOption("task_id").equals("")) {
            taskId = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }

        switch (taskId) {
            case 1214:
                limitlessTask.Knights();
                break;
            case 1206:
                String cz = AtFairyConfig.getOption("cz");
                if ((!cz.equals(""))) {
                    limitlessTask.prestige();
                }
                break;
            case 1200://
                for (int i = 0; i < 3; i++) {
                    gamePublicFunction.chat();
                }


                if (AtFairyConfig.getOption("xyrw").equals("1")) {
                    singleTask.college();
                }
                if (AtFairyConfig.getOption("wstdky").equals("1")) {
                    singleTask.EverythingGoesWellAndSmoothly();
                }
                if (AtFairyConfig.getOption("promise").equals("1")) {
                    singleTask.Promise();
                }
                if (AtFairyConfig.getOption("bento").equals("1")) {
                    singleTask.Bento();
                }
                if (AtFairyConfig.getOption("boss").equals("1")) {
                    singleTask.boos();
                }

                break;
            case 1286:
                limitlessTask.gd();
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
