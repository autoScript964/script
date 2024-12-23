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
    public static String TASKNAME = "";
    private int taskId = 0;

    public TaskMain(AtFairyImpl ypFairy) throws Exception {
        singleTask = new SingleTask(ypFairy);
        teamTask = new TeamTask(ypFairy);
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        limitlessTask = new LimitlessTask(ypFairy);
        mFairy = ypFairy;
        mFairy.setGameName("new斗破苍穹");
        mFairy.setGameVersion(411);
        init();
    }

    private int week;
    private int hour;
    public void main() throws Exception {

        if(taskId!=2863 && taskId!=2855){
            LtLog.i(mFairy.getLineInfo("taskid:"+taskId));
            singleTask.setup();
        }

        switch (taskId) {
            case 2855:
                while (mFairy.condit()) {

                    result = mFairy.findPic("battleH.png");
                    LtLog.e("颜色数量为"+result.sim);
                    Thread.sleep(3000);
                }
                break;
            case 1591:
                if(AtFairyConfig.getOption("hbll").equals("1")){
                    singleTask.hbll();
                }

                if (AtFairyConfig.getOption("sz").equals("1")) {
                    singleTask.xz();
                }

                if (AtFairyConfig.getOption("xyxx").equals("1")) {
                    singleTask.xyxx();
                }
                if (AtFairyConfig.getOption("wzhc").equals("1")) {
                    singleTask.wzhc();
                }
                if (AtFairyConfig.getOption("zsxl").equals("1")) {
                    singleTask.zsxl();
                }
                if (AtFairyConfig.getOption("jzsy").equals("1")) {
                    singleTask.jzsy();
                }
                if (AtFairyConfig.getOption("jzxklc").equals("1")) {
                    singleTask.jzxklc();
                }

                if (AtFairyConfig.getOption("ykzl").equals("1")) {
                    singleTask.ykzl();
                }

                if (AtFairyConfig.getOption("klcl").equals("1")) {
                    singleTask.klcl();
                }

                if (AtFairyConfig.getOption("jzsx").equals("1")) {
                    singleTask.jzsx();
                }
                if (!AtFairyConfig.getOption("jzps").equals("")) {
                    singleTask.jzps();
                }

                if (AtFairyConfig.getOption("klcl").equals("1")) {
                    singleTask.klcl();
                }

                /*if (AtFairyConfig.getOption("ykzl").equals("1")) {
                    singleTask.ykzl();
                }*/
                if (!AtFairyConfig.getOption("yhs").equals("")) {
                    singleTask.slyh();
                }
                if (AtFairyConfig.getOption("lmyhxs").equals("1")) {
                    singleTask.lmyhxs();
                }
                if (AtFairyConfig.getOption("lmdsmw").equals("1")) {
                    singleTask.lmdsmw();
                }
                if (AtFairyConfig.getOption("lmlmct").equals("1")) {
                    singleTask.lmlmct();
                }

                if (AtFairyConfig.getOption("lmhb").equals("1")) {
                    singleTask.lmhb();
                }

                if (AtFairyConfig.getOption("qian").equals("1")) {
                    singleTask.signIn();
                }

                if (AtFairyConfig.getOption("email").equals("1")) {
                    singleTask.email();
                }

                if (AtFairyConfig.getOption("shangchu").equals("1") || AtFairyConfig.getOption("jichu").equals("1")) {
                    singleTask.shangjia();
                }

                if (AtFairyConfig.getOption("zhuangfen").equals("1") || AtFairyConfig.getOption("qingfen").equals("1")) {
                    singleTask.zhuang();
                }

                break;
            case 1593:

                if (AtFairyConfig.getOption("dqgnd").equals("1")) {
                    teamTask.dqg(0);
                }

                if (AtFairyConfig.getOption("dqgnd").equals("2")) {
                    teamTask.dqg(1);
                }



                switch (AtFairyConfig.getOption("mjhb")) {
                    case "1":
                        teamTask.mjpt();
                        break;
                    case "2":
                        teamTask.mjkn();
                        break;
                    case "3":
                        teamTask.mjem();
                        break;
                }



                //生死门

                if (AtFairyConfig.getOption("9287").equals("1")) {
                    teamTask.ssm();
                }

                //万兽岛
                if(AtFairyConfig.getOption("9289").equals("1")){
                    teamTask.wsd();
                }


                if (AtFairyConfig.getOption("tftz").equals("1")) {
                    teamTask.tftz();
                }

                if (AtFairyConfig.getOption("ylsl").equals("1")) {
                    teamTask.ylsl();
                }



                if (AtFairyConfig.getOption("ybc").equals("1")) {
                    teamTask.ybc();
                }

                if (TaskContent.getNumberAssembly(AtFairyConfig.getOption("ybcCount"))!=-1) {
                    teamTask.ybcCustom();
                }

                //singleTask.signIn();
                break;
            case 1595:
                teamTask.gen();
                break;
            case 1589:
                limitlessTask.hangUp();
                break;
            case 1240:
                singleTask.lifeCy();
                singleTask.lifeCk();
                if (!AtFairyConfig.getOption("bz").equals("")) {
                    singleTask.xb();
                }
                break;
            case 2863:
                if (AtFairyConfig.getOption("zx").equals("1")) {
                    singleTask.ThreadTask();
                }
                if (AtFairyConfig.getOption("zx").equals("2")) {
                    singleTask.novice();
                }
                if (AtFairyConfig.getOption("zx").equals("3")) {
                    singleTask.fanWai();
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
