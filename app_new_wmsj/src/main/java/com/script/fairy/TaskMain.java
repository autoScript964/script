package com.script.fairy;


import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import org.json.JSONException;
import org.json.JSONObject;

import static com.script.opencvapi.AtFairy2.FAIRY_TYPE_TASK;
import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class TaskMain {
    Task task;
    JSONObject optionJson;
    SingleTask singlepTask;
    TeamTask teamTask;
    LimitlessTask infiniteTask;
    OtherTask otherTask;
    GamePublicFuntion gamePublicFuntion;
    AtFairyImpl atFairy;


    public TaskMain(AtFairyImpl atFairy)throws Exception {
        task = new Task(atFairy);
        singlepTask = new SingleTask(atFairy);
        teamTask = new TeamTask(atFairy);
        infiniteTask = new LimitlessTask(atFairy);
        otherTask = new OtherTask(atFairy);
        gamePublicFuntion = new GamePublicFuntion(atFairy);
        this.atFairy=atFairy;
        atFairy.setGameName("完美世界");
        atFairy.setGameVersion(195);
        AtFairyConfig.initConfig();
        Task.task_err=0;

        try {
            optionJson = new JSONObject(AtFairyConfig.getUserTaskConfig());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LtLog.e("选项列表" + optionJson);
    }

    int i = 0;
    public void main() throws Exception {
        Thread.sleep(1000);

        if (AtFairyConfig.getTaskID().equals("1651")) {
            while (atFairy.condit()) {
                FindResult r = atFairy.findPic(286, 139, 536, 609, "hhtype1.png");
                LtLog.e("" + r.sim);
            }
        }

        /***
         *  新手引导  */
        if (AtFairyConfig.getTaskID().equals("1424")) {
            singlepTask.novice();
            if (!AtFairyConfig.getOption("nend").equals("")) {
                int num= Integer.parseInt(AtFairyConfig.getOption("nend"));
                singlepTask.noviceEndTask(num);
            }
            LtLog.e("一键25级任务完成!");
            atFairy.finish(FAIRY_TYPE_TASK, AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
            return;
        }

        if(gamePublicFuntion.EndTask(task)){
            return;
        }

        /***
         *  领奖  */

        if (Task.list.get(0).equals("ling")) {
            otherTask.ling();
            return;
        }
        if (Task.list.get(0).equals("linghd")) {
            otherTask.linghd();
            if(Task.list.get(0).equals("ww")){
                infiniteTask.initPrestige();
            }
            return;
        }

        /***
         *  单人任务  */
        if (AtFairyConfig.getTaskID().equals("1426")) {
            singlepTask.mainScene();
            singlepTask.activity();
            singlepTask.ranks();
            singlepTask.cancel();
            singlepTask.fbShiBai();
            singlepTask.packages();
            singlepTask.taskScene();
            task.overallSituation();
            task.package_Task();
            task.autos();
            task.jujue();
            task.map();
            task.setUp();
            if (Task.task_err > 25) {
                LtLog.e(atFairy.getLineInfo("场景未触发,init"));
                singlepTask.unexecuted();
            }
        }

        /***
         *   带队任务   */
        if (AtFairyConfig.getTaskID().equals("1436")) {
            teamTask.mainScene();
            teamTask.activity();
            teamTask.ranks();
            teamTask.ranksmb();
            teamTask.activity();
            teamTask.cancel();
            teamTask.fbShiBai();
            teamTask.zxScene();
            teamTask.tzScene();
            teamTask.hjScene();
            task.overallSituation();
            task.package_Task();
            task.autos();
            task.jujue();
            task.map();
            task.setUp();
            if (Task.task_err  > 25) {
                LtLog.e(atFairy.getLineInfo("场景未触发,init"));
                teamTask.unexecuted();
            }
        }

        /***
         *   无限威望   */
        if (AtFairyConfig.getTaskID().equals("1438")) {
            infiniteTask.limitedTime();
            infiniteTask.mainScene();
            infiniteTask.activity();
            infiniteTask.ranks();
            infiniteTask.ranksmb();
            infiniteTask.activity();
            infiniteTask.cancel();
            infiniteTask.fbShiBai();
            infiniteTask.wwScene();
            infiniteTask.dtScene();
            task.overallSituation();
            task.package_Task();
            task.autos();
            task.jujue();
            task.map();
            task.setUp();
            if (Task.task_err  > 25) {
                LtLog.e(atFairy.getLineInfo("场景未触发,init"));
                infiniteTask.unexecuted();
            }
        }


        /***
         *   跟队   */
        if (AtFairyConfig.getTaskID().equals("1464")) {
            infiniteTask.mainSceneGD();
            infiniteTask.serviceGD();
            if (Task.task_err  > 25) {
                LtLog.e(atFairy.getLineInfo("场景未触发,init"));
                infiniteTask.unexecuted();
            }
        }

        /***
         *   采集 钓鱼   */
        if (AtFairyConfig.getTaskID().equals("1466")) {
            otherTask.mainScene();
            otherTask.ranks();
            otherTask.fbShiBai();
            otherTask.cancel();
            otherTask.sh();
            task.overallSituation();
            task.package_Task();
            task.jujue();
            task.map();
            if (Task.task_err > 25) {
                LtLog.e(atFairy.getLineInfo("场景未触发,init"));
                otherTask.unexecuted();
            }
        }

        /***
         *   无限刷情义   */
        if (AtFairyConfig.getTaskID().equals("1500")) {
            infiniteTask.wxqy_dwmb();
            infiniteTask.wxqy_mainScene();
            infiniteTask.wxqy_ranks();
            task.overallSituation();
            task.package_Task();
            teamTask.cancel();
            task.autos();
            task.jujue();
            task.map();
            task.setUp();
            if (Task.task_err > 25) {
                LtLog.e(atFairy.getLineInfo("场景未触发,init"));
                otherTask.unexecuted();
            }
        }


    }
}
