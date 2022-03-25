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
        mFairy.setGameName("海岛纪元");
        mFairy.setGameVersion(30);
        init();
        BANG = true;
    }

    public void main() throws Exception {

        ScProxy.config().Level().capturing(4);

        if (!AtFairyConfig.getOption("task_id").equals("")) {
            taskId = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }

        singleTask.set_up();
        switch (taskId) {
            case 2167:
                singleTask.li_fb();
                singleTask.li_rank();

                if(!AtFairyConfig.getOption("tf").equals("")){
                    singleTask.set_tf();
                }

                if(AtFairyConfig.getOption("cb").equals("1")){
                    singleTask.cb();
                }

                if(AtFairyConfig.getOption("6231").equals("1")){
                    singleTask.fj();
                }

                if(AtFairyConfig.getOption("wtggb").equals("1")){
                    singleTask.wtggb();
                }

                if(AtFairyConfig.getOption("mtzc").equals("1")){
                    singleTask.mtzc();
                }

                if(AtFairyConfig.getOption("cwpq").equals("1")){
                    singleTask.cwpq();
                }

                if(!AtFairyConfig.getOption("yscs").equals("")){
                    singleTask.yscs();
                }

                if(AtFairyConfig.getOption("wbt").equals("1")){
                    singleTask.tl();
                    singleTask.wbt();
                }

                if(!AtFairyConfig.getOption("jzgf").equals("")){
                    singleTask.jzgf();
                }

                if(!AtFairyConfig.getOption("rldl").equals("")){
                    singleTask.rldl();
                }

                if(!AtFairyConfig.getOption("lhzl").equals("")){
                    singleTask.lhzl();
                }
                if(!AtFairyConfig.getOption("fzxw").equals("")){
                    singleTask.fzxw();
                }

                if(AtFairyConfig.getOption("zcw").equals("1")){
                    singleTask.jysc("zcw.png");
                }
                if(AtFairyConfig.getOption("fmw").equals("1")){
                    singleTask.jysc("fmw.png");
                }
                if(AtFairyConfig.getOption("csc").equals("1")){
                    singleTask.jysc("csc.png");
                }

                if(AtFairyConfig.getOption("6233").equals("1")){
                    singleTask.ttqy();
                }

                if(AtFairyConfig.getOption("mrxl").equals("1")){
                    singleTask.mrxl();
                }

                if(AtFairyConfig.getOption("fl").equals("1")){
                    singleTask.fl();
                }

                if(AtFairyConfig.getOption("edsb").equals("1")){
                    singleTask.edsb();
                }

                break;
            case 2171:

                if(!AtFairyConfig.getOption("tf").equals("")){
                    singleTask.set_tf();
                }

                if(AtFairyConfig.getOption("cb").equals("1")){
                    singleTask.cb();
                }

                if(AtFairyConfig.getOption("6231").equals("1")){
                    singleTask.fj();
                }

                if(AtFairyConfig.getOption("ysmj").equals("1")){
                    teamTask.ysmj();
                }

                if(AtFairyConfig.getOption("fba1").equals("1")){
                    teamTask.fb("1","1");
                }
                if(AtFairyConfig.getOption("fba2").equals("1")){
                    teamTask.fb("2","1");
                }
                if(AtFairyConfig.getOption("fba3").equals("1")){
                    teamTask.fb("3","1");
                }
                if(AtFairyConfig.getOption("fba4").equals("1")){
                    teamTask.fb("4","1");
                }
                if(AtFairyConfig.getOption("fba5").equals("1")){
                    teamTask.fb("5","1");
                }
                if(AtFairyConfig.getOption("fba6").equals("1")){
                    teamTask.fb("6","1");
                }

                if(AtFairyConfig.getOption("fbb1").equals("1")){
                    teamTask.fb("1","2");
                }
                if(AtFairyConfig.getOption("fbb2").equals("1")){
                    teamTask.fb("2","2");
                }
                if(AtFairyConfig.getOption("fbb3").equals("1")){
                    teamTask.fb("3","2");
                }
                if(AtFairyConfig.getOption("fbb4").equals("1")){
                    teamTask.fb("4","2");
                }
                if(AtFairyConfig.getOption("fbb5").equals("1")){
                    teamTask.fb("5","2");
                }
                if(AtFairyConfig.getOption("fbb6").equals("1")){
                    teamTask.fb("6","2");
                }
                break;
            case 2175:
                singleTask.li_rank();

                if(!AtFairyConfig.getOption("tf").equals("")){
                    singleTask.set_tf();
                }

                if(AtFairyConfig.getOption("cb").equals("1")){
                    singleTask.cb();
                }

                if(AtFairyConfig.getOption("6231").equals("1")){
                    singleTask.fj();
                }

                limitlessTask.linitless();
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
