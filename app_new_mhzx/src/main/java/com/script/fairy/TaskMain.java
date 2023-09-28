package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.json.JSONObject;

public class TaskMain {
    AtFairyImpl mFairy;
    SingleTask singleTask;
    TeamTask teamTask;
    LimitlessTask limitlessTask;
    FindResult result;
    JSONObject optionJson = null;
    GamePublicFuntion gamePublicFuntion;
    private int taskId = 0;

    public TaskMain(AtFairyImpl ypFairy) throws Exception {
        singleTask = new SingleTask(ypFairy);
        teamTask = new TeamTask(ypFairy);
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        limitlessTask = new LimitlessTask(ypFairy);
        mFairy = ypFairy;
        mFairy.setGameName("梦幻诛仙");
        mFairy.setGameVersion(137);
        init();
    }

    private int week;
    private int hour;
    private int minute;
    private int task = 99;

    public void main() throws Exception {

        switch (taskId) {
            case 1160://新手引导
                LtLog.e("-----勾选了新手引导");
                singleTask.novice();
                break;

            case 1146:
                if (AtFairyConfig.getOption("zhuxian").equals("1")) {
                    LtLog.e("-----勾选了主线");
                    singleTask.thread();
                }

                if (AtFairyConfig.getOption("zhixian").equals("1")) {
                    LtLog.e("-----勾选了支线");
                    singleTask.branchLine();
                }
                break;

            case 1148://日常任务
                    /*
                        必做日常
                     */
                if (AtFairyConfig.getOption("sm").equals("1")) {
                    LtLog.e("-----开始师门任务");
                    singleTask.master();
                }

                if (AtFairyConfig.getOption("hs").equals("1")) {
                    LtLog.e("-----开始护送任务");
                    singleTask.escort();
                }

                if (AtFairyConfig.getOption("bt").equals("1")) {
                    LtLog.e("-----开始宝图任务");
                    singleTask.treasure();
                }

                if (AtFairyConfig.getOption("wbt").equals("1")) {
                    LtLog.e("-----开始挖宝图任务");
                    singleTask.wbt();
                }

                /*
                     可选日常
                   */
                if (AtFairyConfig.getOption("sj").equals("1")) {
                    LtLog.e("-----开始赏金任务任务");
                    singleTask.money();
                }
                if (AtFairyConfig.getOption("jjc").equals("1")) {
                    LtLog.e("-----开始竞技场任务");
                    singleTask.arena();
                }
                if (AtFairyConfig.getOption("lsym").equals("1")) {
                    LtLog.e("-----开始乱世妖魔任务");
                    singleTask.lsym();
                }

                if (AtFairyConfig.getOption("hhms").equals("1")) {
                    LtLog.e("-----开始还魂秘术");
                    singleTask.hhms();
                }
                if (AtFairyConfig.getOption("hl").equals("1")) {
                    LtLog.e("-----开始黑龙浩劫任务");
                    singleTask.hlhj();
                }

                if (AtFairyConfig.getOption("pprw").equals("1")) {
                    LtLog.e("-----开始帮派任务");
                    singleTask.gangs();
                }
                if (AtFairyConfig.getOption("moon").equals("1")) {
                    LtLog.e("-----开始赏月任务");
                    singleTask.moon();
                }
                /*
                    限时日常
                 */
                if (AtFairyConfig.getOption("kjks").equals("1")) {
                    week = mFairy.week();
                    hour = mFairy.dateHour();
                    minute = mFairy.dateMinute();
                    if (week == 6) {
                        switch (hour) {
                            case 17:
                                if (minute > 30) {
                                    LtLog.e("-----科举考试");
                                    singleTask.answerKJ();
                                }
                                break;
                            case 18:
                                LtLog.e("-----科举考试");
                                singleTask.answerKJ();
                                break;
                            case 19:
                                if (minute < 50) {
                                    LtLog.e("-----科举考试");
                                    singleTask.answerKJ();
                                }
                                break;
                            default:
                                break;

                        }
                    }
                }

                if (AtFairyConfig.getOption("zxqy").equals("1")) {
                    hour = mFairy.dateHour();
                    if (hour >= 10) {
                        LtLog.e("-----开始诛仙奇缘");
                        //zxqy();
                        singleTask.answerZXQY();
                    }
                }


                if (AtFairyConfig.getOption("qyxt").equals("1")) {
                    week = mFairy.week();
                    hour = mFairy.dateHour();
                    minute = mFairy.dateMinute();
                    if (week != 6 && hour >= 17) {
                        if (hour == 17 && minute < 30) {
                        } else {
                            LtLog.e("-----开始青云学堂任务");
                            singleTask.answerQYXT();
                        }
                    }
                }


                if (AtFairyConfig.getOption("bpsp").equals("1")) {
                    hour = mFairy.dateHour();
                    minute = mFairy.dateMinute();
                    if (hour == 12 || hour == 19) {
                        if (minute >= 40 && minute < 55) {
                            LtLog.e("-----开始帮派赛跑任务");
                            singleTask.race();
                        }
                    }
                }

                if (AtFairyConfig.getOption("qh").equals("1")) {
                    if (singleTask.qh()) {
                        LtLog.e("玩家勾选切换角色,切换....");
                        return;
                    }
                }

                break;
            case 1154:
                LtLog.e("-----开始挂机");
                limitlessTask.hangUp();
                break;

            case 1156:
                LtLog.e("-----开始挂机(副)");
                limitlessTask.hangUp();
                break;

            case 1152:
                LtLog.e("-----开始试炼任务");
                singleTask.sl();

                if (AtFairyConfig.getOption("qh").equals("1")) {
                    if (singleTask.qh()) {
                        LtLog.e("玩家勾选切换角色,切换....");
                        return;
                    }
                }
                break;

            case 1162:
                if (AtFairyConfig.getOption("qyzpt").equals("1")) {
                    LtLog.e("-----开始青云志-普通");
                    singleTask.qyzpt();
                }

                if (AtFairyConfig.getOption("qyzjy").equals("1")) {
                    LtLog.e("-----开始青云志-精英");
                    teamTask.qyzjy();
                }
                if (AtFairyConfig.getOption("qyzyx").equals("1")) {
                    LtLog.e("-----开始青云志-英雄");
                    teamTask.qyzyx();
                }


                break;

            case 1158:
                LtLog.e("-----开始跟队");
                task = teamTask.ftt();
                break;

            case 1164:
                LtLog.e("-----开始捉宠卖钱任务");
                singleTask.catchPet();
                break;

            case 1150:
                LtLog.e("-----开始带队一条龙任务");
                teamTask.teamOfDragon();

                if (AtFairyConfig.getOption("qh").equals("1")) {
                    if (singleTask.qh()) {
                        LtLog.e("玩家勾选切换角色,切换....");
                        return;
                    }
                }
                break;

        }

        LtLog.e(mFairy.getLineInfo("勾选任务已经全部完成,End!" + AtFairyConfig.getTaskID()));
        mFairy.finish(AtFairyConfig.getTaskID(), task);
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
