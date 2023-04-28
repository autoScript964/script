package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.List;

import static com.script.opencvapi.AtFairy2.FAIRY_TYPE_TASK;
import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class TaskMain {
    AtFairyImpl mFairy;
    CommonFunction commonFunction;
    GamePublicFunction gamePublicFunction;
    SingleTask singleTask;
    TimingActivity timingActivity;
    TeamTask teamTask;
    LimitlessTask limitlessTask;
    Other other;
    static List<String> list = new ArrayList<>();
    static int xshd_1 = 0, xshd_2 = 0, xshd_3 = 0, xshd_4 = 0, xshd_5 = 0, xshd_6 = 0, xshd_7 = 0, xshd_8 = 0, xshd_9 = 0, xshd_10 = 0;
    static int scbj = 0;
    static int task = 0;
    String task_id;

    public TaskMain(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        mFairy.setGameName("神武");
        mFairy.setGameVersion(363);
        mFairy = ypFairy;
        commonFunction = new CommonFunction(mFairy);
        gamePublicFunction = new GamePublicFunction(mFairy);
        singleTask = new SingleTask(mFairy);
        teamTask = new TeamTask(mFairy);

        limitlessTask = new LimitlessTask(mFairy);
        timingActivity = new TimingActivity(mFairy);
        other = new Other(mFairy);
        xshd_1 = 0;
        xshd_2 = 0;
        xshd_3 = 0;
        xshd_4 = 0;
        xshd_5 = 0;
        xshd_6 = 0;
        xshd_7 = 0;
        xshd_8 = 0;
        xshd_9 = 0;
        xshd_10 = 0;
        scbj = 0;
        task = 0;
        list.clear();

    }

    public void main() throws Exception {
        int task_id1;
        task_id = AtFairyConfig.getOption("task_id");
        if (task_id.equals("")) {
            task_id1 = 0;
        } else {
            task_id1 = Integer.parseInt(task_id);
        }

//        singleTask.MainThredTask();
        LtLog.e(commonFunction.getLineInfo("task_id1==" + task_id1));
        switch (task_id1) {
            case 1322:
                gamePublicFunction.Signout();
                gamePublicFunction.BackCity(698, 497, "长安");

                singleTask.ViewTask();

                if (AtFairyConfig.getOption("kjdt").equals("1")  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了科举答题"));
                    timingActivity.Imperial();
                }
                if (AtFairyConfig.getOption("sybt").equals("1")  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了使用宝图"));
                    singleTask.Usemap();
                    singleTask.Usemap1();
                }
                if ((AtFairyConfig.getOption("hbrw").equals("1") || AtFairyConfig.getOption("150tf").equals("1"))  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了伙伴任务"));
                    singleTask.Partner();
                }
                if ((!AtFairyConfig.getOption("nd").equals(""))  && task == 0) {

                    if (AtFairyConfig.getOption("llztz").equals("1")  && task == 0) {
                        LtLog.e(commonFunction.getLineInfo("勾选了玲珑周挑战任务"));
                        singleTask.Pagoda1();
                    }else {
                        LtLog.e(commonFunction.getLineInfo("勾选了玲珑宝塔任务"));
                        singleTask.Pagoda();
                    }
                }
                if (!AtFairyConfig.getOption("llnd").equals("")  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了历练任务"));
                    singleTask.Practice();
                }
                break;
            case 403:
                if ( task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了一键31级"));
                    singleTask.MainThredTask();
                }
                break;
            case 1324:
                if (!AtFairyConfig.getOption("ditu").equals("")  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了带队封妖"));
                    gamePublicFunction.Hyf();
                    teamTask.DdFengyao();
                }
                if ((!AtFairyConfig.getOption("zgsl").equals(""))  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("带队勾选了活跃鬼或日常"));
                    teamTask.DailyGhostChase("Ghost Chase.png");
                }

                if (AtFairyConfig.getOption("yxzc").equals("1")  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了带队英雄战场"));
                    gamePublicFunction.BackCity(698, 497, "长安");
                    teamTask.teamCopy1();
                }
                break;
            case 1328:
                if (AtFairyConfig.getOption("kjdt").equals("1")  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了科举答题"));
                    timingActivity.timeLimitActivity();
                }
                if ( task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了带队挂野"));
                    limitlessTask.Hanging();
                }
                break;
            case 1326:
                if ((!AtFairyConfig.getOption("zgsl").equals(""))  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了跟队活跃鬼或日常或无限"));
                    teamTask.FollowGui();
                }
                gamePublicFunction.BackCity(698, 497, "长安");
                if (AtFairyConfig.getOption("gdfb").equals("1")  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了跟队英雄战场"));
                    teamTask.GdTeamCopy1(1, "gdyxzc.png");
                }
                gamePublicFunction.BackCity(698, 497, "长安");
                if (AtFairyConfig.getOption("gdfb").equals("2")  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了跟队玄武门之变"));
                    teamTask.GdTeamCopy1(2, "gdxwmzb.png");
                }
                if (AtFairyConfig.getOption("2014").equals("1")  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了跟队封妖"));
                    teamTask.GdFengyao();
                }
                break;
            case 1330:
                if ((!AtFairyConfig.getOption("ditu1").equals(""))  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了休闲钓鱼"));
                    other.Gofishing();
                }
                if (AtFairyConfig.getOption("zcmq").equals("1")  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了捉宠卖钱"));
                    other.ZhuoGuiMaiQ();
                }
                break;
            case 1332:
                gamePublicFunction.Hyf();
                if (AtFairyConfig.getOption("rwlnd").equals("1")  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了普通模式"));
                    singleTask.taskChain();
                }
                if (AtFairyConfig.getOption("rwlnd").equals("2")  && task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了土豪模式"));
                    singleTask.taskChain1();
                }
                break;
            case 1334:
                if ( task == 0) {
                    LtLog.e(commonFunction.getLineInfo("勾选了使用宝图"));
                    gamePublicFunction.Signout();
                    gamePublicFunction.BackCity(698, 497, "长安");
                    singleTask.Usemap();
                    singleTask.Usemap1();
                }
                break;
            case 2354:
                if (task==0){
                    LtLog.e(commonFunction.getLineInfo("自动补充饱食"));
                    limitlessTask.satiate();
                }
            default:
                task = 1;
                break;
        }
        if (task_id == "0") {
            task = 0;
        } else if ( task == 0) {
            task = 99;
        }
        //上报 类型 1 check（FAIRY_TYPE_CHECK） 2 （FAIRY_TYPE_TASK）task 任务完成 上报 99 （TASK_STATE_FINISH）
        switch (task) {
            case 99:
                LtLog.e(commonFunction.getLineInfo("-------------------------Swmobile 任务完成 .....") + task);
                mFairy.finish(FAIRY_TYPE_TASK, task_id, TASK_STATE_FINISH);
                break;
            case 4:
                LtLog.e(commonFunction.getLineInfo("-------------------------Swmobile 寿命不足 .....") + task);
                mFairy.finish(FAIRY_TYPE_TASK, task_id, 2046);
                
                break;
            case 7:
                LtLog.e(commonFunction.getLineInfo("-------------------------Swmobile 库存已满 .....") + task);
                mFairy.finish(FAIRY_TYPE_TASK, task_id, 4586);
                
                break;
            case 2042:
                LtLog.e(commonFunction.getLineInfo("-------------------------Swmobile 失败多次 .....") + task);
                mFairy.finish(FAIRY_TYPE_TASK, task_id, 2042);
                
                break;
            case 4585:
                LtLog.e(commonFunction.getLineInfo("-------------------------Swmobile 更换修炼类型 .....") + task);
                mFairy.finish(FAIRY_TYPE_TASK, task_id, 4585);
                
                break;
        }
        LtLog.e(commonFunction.getLineInfo("任务线程结束"));


    }

}

