package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.json.JSONObject;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class TaskMain {
    AtFairyImpl mFairy;
    SingleTask singleTask;
    LimitlessTask limitlessTask;
    TeamTask teamTask;
    JSONObject optionJson = null;
    GamePublicFuntion gamePublicFuntion;
    private int taskId = 0;
    private int week = 0;
    private int hour = 0;
    private int minute = 0;
    FindResult result;

    public TaskMain(AtFairyImpl ypFairy) throws Exception {
        singleTask = new SingleTask(ypFairy);
        teamTask = new TeamTask(ypFairy);
        limitlessTask = new LimitlessTask(ypFairy);
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        mFairy = ypFairy;
        mFairy.setGameName("梦幻西游");
        mFairy.setGameVersion(459);
        init();
        GamePublicFuntion.ACTLING = 1;

    }

    public void main() throws Exception {

        if (!AtFairyConfig.getOption("task_id").equals("")) {
            taskId = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }

        taskStartTime(AtFairyConfig.getOption("start_time"));



        singleTask.setUp();

        switch (taskId) {

            case 2803:
                if(!AtFairyConfig.getOption("hl").equals("")){
                    singleTask.hl(Integer.parseInt(AtFairyConfig.getOption("hl")));
                }
                break;
            case 1905:
                singleTask.nn();
                break;
            case 1907:
                if (AtFairyConfig.getOption("cb").equals("1")) {
                    singleTask.cb();
                }

                yjrc_singleTask();
                break;

            case 1909:

                if (AtFairyConfig.getOption("cb").equals("1")) {
                    singleTask.cb();
                }

                /**
                 * 限时*/
                if (AtFairyConfig.getOption("ycmht").equals("1")) {
                    week = mFairy.week();
                    hour = mFairy.dateHour();
                    if (week == 5 && hour >= 19 && hour < 22) {
                        teamTask.ycmht();
                        gamePublicFuntion.home();
                    }
                }



                if (AtFairyConfig.getOption("mpcg").equals("1")) {
                    week = mFairy.week();
                    hour = mFairy.dateHour();
                    minute = mFairy.dateMinute();
                    if (week == 1 && hour == 21 && minute < 40) {
                        teamTask.mpcg();
                        gamePublicFuntion.home();
                    }
                }

                yjrc_teamTask();

                break;
            case 1911:
                if (AtFairyConfig.getOption("cb").equals("1")) {
                    singleTask.cb();
                }

                if (TaskContent.getNumberAssembly(AtFairyConfig.getOption("ls")) != -1) {
                    singleTask.ls();
                }
                teamTask.gen();
                break;
            case 1917:
                limitlessTask.guaji();
                break;
            case 2135:
                yjrc();
                break;

        }

        LtLog.e(mFairy.getLineInfo("勾选任务已经全部完成,End!" + AtFairyConfig.getTaskID()));
        mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
    }

    public void taskStartTime(String string) throws Exception {

        if (string.equals("")) {
            return;
        }

        String[] arrstr = string.split("\\|\\|");

        if (arrstr.length < 2) {
            return;
        } else {

            if (!arrstr[0].equals("1")) {
                return;
            }

            String[] arrstr1 = arrstr[1].split(":");
            int h = Integer.parseInt(arrstr1[0]);
            int m = Integer.parseInt(arrstr1[1]);

            long time = System.currentTimeMillis();
            while (mFairy.condit()){

                Thread.sleep(1000);

                if(System.currentTimeMillis() - time >30000) {
                    LtLog.e(mFairy.getLineInfo("等待任务开启 >>>"));
                    time = System.currentTimeMillis();
                }

                int hour = mFairy.dateHour();
                int minute =mFairy.dateMinute();

                if(hour==h && minute==m){
                    return;
                }
            }
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

    public void yjrc() throws Exception {
        qh1 = 0;
        qh2 = 0;
        qh3 = 0;
        qh4 = 0;
        qh5 = 0;
        qh6 = 0;
        qh_init();
        while (mFairy.condit()) {

            if (gamePublicFuntion.home() == false) {
                LtLog.e(mFairy.getLineInfo("角色已被隔离！！！"));
                LtLog.e(mFairy.getLineInfo("角色已被隔离！！！"));
                LtLog.e(mFairy.getLineInfo("角色已被隔离！！！"));
            } else {
                singleTask.setUp();

                if (AtFairyConfig.getOption("cb").equals("1")) {
                    singleTask.cb();
                }

                switch (AtFairyConfig.getOption("sort")) {
                    case "2":

                        if (AtFairyConfig.getOption("6101").equals("1")) {
                            yjrc_teamTask();
                        }

                        if (AtFairyConfig.getOption("6097").equals("1")) {
                            yjrc_singleTask();
                        }
                        break;
                    default:
                        if (AtFairyConfig.getOption("6097").equals("1")) {
                            yjrc_singleTask();
                        }

                        if (AtFairyConfig.getOption("6101").equals("1")) {
                            yjrc_teamTask();
                        }
                        break;
                }
            }

            if (qh_bool() != 0) {
                qh();
            } else {
                if (AtFairyConfig.getOption("zero").equals("1")) {
                    while (true) {
                        LtLog.e("等待0点重启中");
                        Thread.sleep(30000);

                        hour = mFairy.dateHour();
                        minute = mFairy.dateMinute();
                        if ((hour == 0 || hour == 24) && minute < 10) {
                            qh1 = 0;
                            qh2 = 0;
                            qh3 = 0;
                            qh4 = 0;
                            qh5 = 0;
                            qh6 = 0;
                            qh_init();
                            break;
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }//一键日常任务

    public void yjrc_singleTask() throws Exception {
        gamePublicFuntion.rankinit(0);

        if (AtFairyConfig.getOption("shcs").equals("1")) {
            singleTask.shcs();
        }

        if (AtFairyConfig.getOption("sm").equals("1")) {
            singleTask.sm();
        }

        if (AtFairyConfig.getOption("bt").equals("1")) {
            singleTask.bt();
        }

        if (AtFairyConfig.getOption("sybt").equals("1")) {
            singleTask.sybt();
        }

        if (!AtFairyConfig.getOption("mm").equals("")) {
            singleTask.mj();
        }

        if (AtFairyConfig.getOption("bp").equals("1")) {
            singleTask.bp();
        }
        if (AtFairyConfig.getOption("sjqy").equals("1")) {
            hour = mFairy.dateHour();
            if (hour >= 11) {
                singleTask.sjqy();
            }
        }

        if (AtFairyConfig.getOption("kjxs").equals("1")) {
            week = mFairy.week();
            hour = mFairy.dateHour();
            if (week >= 1 && week <= 5 && hour >= 17) {
                singleTask.kjxs();
            }
        }

        if (AtFairyConfig.getOption("yb1").equals("1") || AtFairyConfig.getOption("yb2").equals("1")) {
            singleTask.yb();
        }

        if (AtFairyConfig.getOption("ggl").equals("1")) {
            singleTask.ggl();
        }

        if (AtFairyConfig.getOption("acte").equals("1")) {
            singleTask.acte();
        }
        if (AtFairyConfig.getOption("bpqd").equals("1")) {
            singleTask.bpqd();
        }

        if (AtFairyConfig.getOption("bpfh").equals("1")) {
            singleTask.bpfh();
        }

        if(taskId==1907){
            if(AtFairyConfig.getOption("7868").equals("1") && !AtFairyConfig.getOption("cmap").equals("") && !AtFairyConfig.getOption("gongf").equals("")){
                singleTask.ck(Integer.parseInt(AtFairyConfig.getOption("cmap")),Integer.parseInt(AtFairyConfig.getOption("gongf")));
            }
        }else{
            if (!AtFairyConfig.getOption("cmap").equals("") && !AtFairyConfig.getOption("cmap").equals("0")&& !AtFairyConfig.getOption("gongf").equals("")) {
                singleTask.ck(Integer.parseInt(AtFairyConfig.getOption("cmap")),Integer.parseInt(AtFairyConfig.getOption("gongf")));
            }
        }

        if (AtFairyConfig.getOption("kg").equals("1")) {
            singleTask.kg();
        }

        if (AtFairyConfig.getOption("kgcs").equals("1")) {
            singleTask.kgcs();
        }

        if (AtFairyConfig.getOption("dg").equals("1")) {
            singleTask.dg();
        }
        if (AtFairyConfig.getOption("jyl").equals("1")) {
            singleTask.jyl();
        }
        /*if (AtFairyConfig.getOption("7594").equals("1")) {
            singleTask.cjmy(1);
        }*/

        if (AtFairyConfig.getOption("7596").equals("1")) {
            singleTask.cjmy();
        }

        if (AtFairyConfig.getOption("7596").equals("1")) {
            singleTask.cjmy();
        }


        if (AtFairyConfig.getOption("jy").equals("1")) {
            singleTask.jia();
        }


    }//单人任务

    public void yjrc_teamTask() throws Exception {


        /**
         * 侠士*/

        if (AtFairyConfig.getOption("lyrms").equals("1")) {
            teamTask.lyrms();
        }

        if (AtFairyConfig.getOption("llss").equals("1")) {
            teamTask.llss();
        }
        if (AtFairyConfig.getOption("lsjs").equals("1")) {
            teamTask.lsjs();
        }
        if (AtFairyConfig.getOption("thdqs").equals("1")) {
            teamTask.thdqs();
        }

        if (AtFairyConfig.getOption("ecys").equals("1")) {
            teamTask.ecys();
        }
        if (AtFairyConfig.getOption("wzss").equals("1")) {
            teamTask.wzss();
        }
        if (AtFairyConfig.getOption("jcxs").equals("1")) {
            teamTask.jcxs();
        }
        if (AtFairyConfig.getOption("txxs").equals("1")) {
            teamTask.txxs();
        }

        if (AtFairyConfig.getOption("thqs").equals("1")) {
            teamTask.thqs();
        }

        if (AtFairyConfig.getOption("mzhs").equals("1")) {
            teamTask.mzhs();
        }

        /**
         * 普通*/
        if (AtFairyConfig.getOption("lyrm").equals("1")) {
            teamTask.lyrm();
        }
        if (AtFairyConfig.getOption("lls").equals("1")) {
            teamTask.lls();
        }
        if (AtFairyConfig.getOption("lsj").equals("1")) {
            teamTask.lsj();
        }
        if (AtFairyConfig.getOption("thdq").equals("1")) {
            teamTask.thdq();
        }
        if (AtFairyConfig.getOption("ecy").equals("1")) {
            teamTask.ecy();
        }
        if (AtFairyConfig.getOption("wzs").equals("1")) {
            teamTask.wzs();
        }
        if (AtFairyConfig.getOption("jcx").equals("1")) {
            teamTask.jcx();
        }
        if (AtFairyConfig.getOption("txx").equals("1")) {
            teamTask.txx();
        }

        if (AtFairyConfig.getOption("thq").equals("1")) {
            teamTask.thq();
        }

        if (AtFairyConfig.getOption("mzh").equals("1")) {
            teamTask.mzh();
        }

        if (TaskContent.getNumberAssembly(AtFairyConfig.getOption("ls")) != -1) {
            singleTask.ls();
        }

        if (!AtFairyConfig.getOption("zg").equals("") && !AtFairyConfig.getOption("zg").equals("0")) {
            teamTask.ddzg();
        }
    }//带队任务

    /**
     * 切换角色
     */
    public int qh1, qh2, qh3, qh4, qh5, qh6 = 0;
    public static boolean QH = false;

    public void qh_init() throws Exception {
        if (AtFairyConfig.getOption("qh1").equals("1")) {
            qh1 = 1;
        }
        if (AtFairyConfig.getOption("qh2").equals("1")) {
            qh2 = 1;
        }
        if (AtFairyConfig.getOption("qh3").equals("1")) {
            qh3 = 1;
        }
        if (AtFairyConfig.getOption("qh4").equals("1")) {
            qh4 = 1;
        }
        if (AtFairyConfig.getOption("qh5").equals("1")) {
            qh5 = 1;
        }
        if (AtFairyConfig.getOption("qh6").equals("1")) {
            qh6 = 1;
        }
    }//切换角色_init

    public int qh_bool() throws Exception {
        if (qh1 == 1) {
            return 1;
        }

        if (qh2 == 1) {
            return 2;
        }

        if (qh3 == 1) {
            return 3;
        }

        if (qh4 == 1) {
            return 4;
        }

        if (qh5 == 1) {
            return 5;
        }

        if (qh6 == 1) {
            return 6;
        }
        return 0;
    }//切换角色_bool

    public void qh() throws Exception {
        new TaskContent(mFairy, "切换角色") {

            void create() throws Exception {
                super.create();
                gamePublicFuntion.taskInit(0);
            }

            void init() throws Exception {
                gamePublicFuntion.close();
                setTaskName(1);
                QH = false;
            }

            void content_01() throws Exception {
                timeCount(40, 0);
                Thread.sleep(1000);
                QH = true;
                result = mFairy.findPic("bpqd1.png");
                mFairy.onTap(0.8f, result, "加号", 1500);

                result = mFairy.findPic(522, 548, 1258, 714, "set1.png");
                mFairy.onTap(0.8f, result, "设置", 1000);

                result = mFairy.findPic(479, 12, 789, 82, "set2.png");
                if (result.sim > 0.8f) {

                    err = 0;

                    result = mFairy.findPic(215,573,1019,675,new String[]{"set3.png","qhzh.png","qhzh1.png","qhzh2.png"});
                    mFairy.onTap(0.8f, result, "切换账号", 1500);

                    result = mFairy.findPic("set4.png");
                    mFairy.onTap(0.8f, result, "登出", 8000);
                }

                result = mFairy.findPic(666, 340, 869, 483, new String[]{"set6.png","set14.png","set15.png"});
                mFairy.onTap(0.8f, result, "点击选服", 1500);



                result = mFairy.findPic("set7.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(378,52,625,150,"set8.png");
                    if (result.sim > 0.8f) {
                        if (AtFairyConfig.getOption("yc").equals("1")) {
                            result = mFairy.findPic("set11.png");
                            mFairy.onTap(0.9f, result, "隐藏角色", 1000);
                        } else {
                            result = mFairy.findPic("set10.png");
                            mFairy.onTap(0.9f, result, "不隐藏角色", 1000);
                        }
                        mFairy.ranSwipe(656, 215, 656, 540, 1000, 1000);

                        switch (qh_bool()) {
                            case 1:
                                mFairy.onTap(484, 246, 526, 295, "1", 1000);
                                mFairy.onTap(484, 246, 526, 295, "1", 4000);
                                qh1 = 0;
                                break;
                            case 2:
                                mFairy.onTap(720, 219, 810, 298, "2", 1000);
                                mFairy.onTap(720, 219, 810, 298, "2", 4000);
                                qh2 = 0;
                                break;
                            case 3:
                                mFairy.onTap(1005, 259, 1045, 301, "3", 1000);
                                mFairy.onTap(1005, 259, 1045, 301, "3", 4000);
                                qh3 = 0;
                                break;
                            case 4:
                                mFairy.onTap(457, 520, 549, 613, "4", 1000);
                                mFairy.onTap(457, 520, 549, 613, "4", 4000);
                                qh4 = 0;
                                break;
                            case 5:
                                mFairy.onTap(718, 483, 770, 553, "5", 1000);
                                mFairy.onTap(718, 483, 770, 553, "5", 4000);
                                qh5 = 0;
                                break;
                            case 6:
                                mFairy.onTap(979, 525, 1034, 581, "6", 1000);
                                mFairy.onTap(979, 525, 1034, 581, "6", 4000);
                                qh6 = 0;
                                break;
                        }
                        QH = false;
                        setTaskEnd();
                        Thread.sleep(10000);
                        return;
                    } else {
                        result = mFairy.findPic(170, 26, 358, 588, "set9.png");
                        mFairy.onTap(0.8f, result, "已有角色", 1000);
                    }
                }
            }
        };
    }//切换


}
