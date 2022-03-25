package com.script.fairy;

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
    TeamTask teamTask;
    LimitlessTask limitlessTask;
    FindResult result;
    JSONObject optionJson = null;
    GamePublicFuntion gamePublicFuntion;
    private int taskId = 0;
    public static boolean BANG = true;

    public TaskMain(AtFairyImpl ypFairy) throws Exception {
        singleTask = new SingleTask(ypFairy);
        teamTask = new TeamTask(ypFairy);
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        limitlessTask = new LimitlessTask(ypFairy);
        mFairy = ypFairy;
        mFairy.setGameName("新笑傲江湖");
        mFairy.setGameVersion(95);
        init();
        BANG = true;
    }

    private int week;
    private int hour;
    private int minute;

    public void main() throws Exception {
        switch (taskId) {
            case 2045:
                LtLog.e("11111");
                while (mFairy.condit()) {

                    result = mFairy.findPic(2, 23, 201, 711, "fylc.png");

                    LtLog.e(""+result.sim);

                    Thread.sleep(2000);
                }
                break;
            case 2053:
                singleTask.nn();
                break;
            case 2057:
                singleTask.setUp();
                teamTask.limitless();
                break;
            case 2103:
                singleTask.zx();
                break;
            case 2051:
                singleTask.setUp();
                singleTask.li_ranks();

                if (AtFairyConfig.getOption("mpxx").equals("1")) {
                    singleTask.mpxx();
                }

                if (AtFairyConfig.getOption("ywc").equals("1")) {
                    singleTask.ywc();
                }

                if (AtFairyConfig.getOption("shyx").equals("1")) {
                    singleTask.shyx();
                }

                if (AtFairyConfig.getOption("jgdt").equals("1")) {
                    singleTask.jgdt();
                }

                if (AtFairyConfig.getOption("mxsl").equals("1")) {
                    singleTask.mxsl();
                }

                if (AtFairyConfig.getOption("xktz").equals("1")) {
                    singleTask.xktz();
                }

                if (AtFairyConfig.getOption("jhpk").equals("1")) {
                    singleTask.jhpk();
                }

                if (AtFairyConfig.getOption("bhqd").equals("1") && BANG) {
                    singleTask.bhqd();
                }
                if (!AtFairyConfig.getOption("bhfc").equals("") && BANG) {
                    singleTask.bhfc();
                }
                if (AtFairyConfig.getOption("bhrc").equals("1") && BANG) {
                    singleTask.bhrc();
                }
                if (AtFairyConfig.getOption("bhjs").equals("1") && BANG) {
                    singleTask.bhjs();
                }

                if (AtFairyConfig.getOption("5789").equals("1")) {
                    singleTask.yzxs();
                }

                if (AtFairyConfig.getOption("5791").equals("1")) {
                    singleTask.xkwp();
                }

                if (AtFairyConfig.getOption("sljs").equals("1")) {
                    singleTask.sljs();
                }

                if (!AtFairyConfig.getOption("ssqy").equals("")) {
                    singleTask.ssqy();
                }

                if (!AtFairyConfig.getOption("shxz").equals("")) {
                    singleTask.shxz();
                }

                if (AtFairyConfig.getOption("lm").equals("1")) {
                    singleTask.lm();
                }

                if (!AtFairyConfig.getOption("bc").equals("")) {
                    singleTask.bc();
                }

                if (AtFairyConfig.getOption("ling").equals("1")) {
                    singleTask.ling1();
                    singleTask.ling2();
                }

                break;
            case 2055:
                singleTask.setUp();

                if (AtFairyConfig.getOption("jhhb").equals("1")) {
                    teamTask.jhhb();
                }

                if (AtFairyConfig.getOption("wbgcm").equals("1")) {
                    teamTask.wbgcm();
                }

                if (!AtFairyConfig.getOption("fyl").equals("")) {
                    teamTask.fyl();
                }

                if (AtFairyConfig.getOption("6025").equals("1")) {
                    teamTask.yry();
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
