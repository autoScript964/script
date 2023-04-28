package com.script.fairy;

import com.script.content.ScProxy;
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
        mFairy.setGameName("梦幻西游三维版");
        mFairy.setGameVersion(155);
        init();
        BANG = true;
    }

    private int week;
    private int hour;
    private int minute;

    public void main() throws Exception {

        //mFairy.setIme();

        ScProxy.config().Level().capturing(2);

        switch (taskId) {
            case 1997:
                singleTask.setUp();

                if (!AtFairyConfig.getOption("skill").equals("") && !AtFairyConfig.getOption("skill").equals("0")) {
                    singleTask.skill();
                }

                if (AtFairyConfig.getOption("chubei").equals("1")) {
                    singleTask.chubei();
                }

                teamTask.xianshi();
                break;
            case 1995:
                singleTask.setUp();
                if (AtFairyConfig.getOption("chubei").equals("1")) {
                    singleTask.chubei();
                }
                teamTask.gen();
                break;
            case 1959:
                singleTask.cj();
                break;
            case 1935:
                singleTask.nn();
                break;
            case 1949:
                singleTask.setUp();

                singleTask.li_rank();

                if (!AtFairyConfig.getOption("skill").equals("") && !AtFairyConfig.getOption("skill").equals("0")) {
                    singleTask.skill();
                }

                if (AtFairyConfig.getOption("chubei").equals("1")) {
                    singleTask.chubei();
                }

                if (AtFairyConfig.getOption("hjxx").equals("1")) {
                    singleTask.hjxx();
                }

                if (AtFairyConfig.getOption("sm").equals("1")) {
                    singleTask.sm();
                }

                if (AtFairyConfig.getOption("9043").equals("1")) {
                    singleTask.mdsy();
                }

                if (AtFairyConfig.getOption("lczb").equals("1")) {
                    singleTask.lczb();
                }

                if (AtFairyConfig.getOption("xysl").equals("1")) {
                    singleTask.xysl();
                }

                if (AtFairyConfig.getOption("bt").equals("1")) {
                    singleTask.bt();
                }

                if (AtFairyConfig.getOption("use_bt").equals("1")) {
                    singleTask.use_bt();
                }

                if (!AtFairyConfig.getOption("sssw").equals("")) {
                    singleTask.sssw();
                }

                if (AtFairyConfig.getOption("yb").equals("1")) {
                    singleTask.yb();
                    singleTask.li_rank();
                }

                if (!AtFairyConfig.getOption("zyp").equals("") && !AtFairyConfig.getOption("zyp").equals("0")) {
                    singleTask.zyp();
                }

                if (AtFairyConfig.getOption("bpzc").equals("1") && BANG) {
                    singleTask.bpzc();
                }

                if (AtFairyConfig.getOption("5285").equals("1")) {
                    int bpjs = TaskContent.getNumberAssembly(AtFairyConfig.getOption("bpjs_num"));
                    if (bpjs != -1 && BANG) {
                        singleTask.bpjs(bpjs);
                    }
                }

                int bpps = TaskContent.getNumberAssembly(AtFairyConfig.getOption("bpps"));
                if (bpps != -1 && BANG) {
                    singleTask.bpps(bpps);
                }

                if (!AtFairyConfig.getOption("bpjx").equals("") && BANG) {
                    singleTask.bpjx();
                }

                if (!AtFairyConfig.getOption("xlyy").equals("")) {
                    singleTask.xlyy();
                }

                if (AtFairyConfig.getOption("kjxs").equals("1")) {
                    week = mFairy.week();
                    hour = mFairy.dateHour();

                    if (week != 7 && hour >= 11) {
                        singleTask.kjxs();
                    }
                }

                if (AtFairyConfig.getOption("shaox").equals("1")) {
                    singleTask.shaox();
                }

                if (AtFairyConfig.getOption("ling").equals("1")) {
                    singleTask.ling();
                }

                break;
            case 1951:
                singleTask.setUp();

                singleTask.li_rank();

                if (!AtFairyConfig.getOption("skill").equals("") && !AtFairyConfig.getOption("skill").equals("0")) {
                    singleTask.skill();
                }

                if (AtFairyConfig.getOption("chubei").equals("1")) {
                    singleTask.chubei();
                }

                if (AtFairyConfig.getOption("5309").equals("1")) {
                    teamTask.zg();
                }

                if (AtFairyConfig.getOption("5329").equals("1")) {
                    teamTask.ytdg();
                }



                if (AtFairyConfig.getOption("ptfb1").equals("1")) {
                    teamTask.ptfb(1);
                }
                if (AtFairyConfig.getOption("ptfb2").equals("1")) {
                    teamTask.ptfb(2);
                }



                if (AtFairyConfig.getOption("knfb1").equals("1")) {
                    teamTask.knfb(1);
                }
                if (AtFairyConfig.getOption("knfb2").equals("1")) {
                    teamTask.knfb(2);
                }
                if (AtFairyConfig.getOption("knfb3").equals("1")) {
                    teamTask.knfb(3);
                }
                if (AtFairyConfig.getOption("knfb4").equals("1")) {
                    teamTask.knfb(4);
                }


                if (AtFairyConfig.getOption("5351").equals("1")) {
                    teamTask.yb();
                }

                break;
            case 1985:
                singleTask.setUp();

                if (!AtFairyConfig.getOption("skill").equals("") && !AtFairyConfig.getOption("skill").equals("0")) {
                    singleTask.skill();
                }

                if (AtFairyConfig.getOption("chubei").equals("1")) {
                    singleTask.chubei();
                }

                if (AtFairyConfig.getOption("ptfb1").equals("1")) {
                    teamTask.gen_ptfb(1);
                    singleTask.li_rank();
                }
                if (AtFairyConfig.getOption("ptfb2").equals("1")) {
                    teamTask.gen_ptfb(2);
                    singleTask.li_rank();
                }

                if (AtFairyConfig.getOption("knfb1").equals("1")) {
                    teamTask.gen_knfb(1);
                    singleTask.li_rank();
                }
                if (AtFairyConfig.getOption("knfb2").equals("1")) {
                    teamTask.gen_knfb(2);
                    singleTask.li_rank();
                }
                if (AtFairyConfig.getOption("knfb3").equals("1")) {
                    teamTask.gen_knfb(3);
                    singleTask.li_rank();
                }
                if (AtFairyConfig.getOption("knfb4").equals("1")) {
                    teamTask.gen_knfb(4);
                    singleTask.li_rank();
                }

                if (AtFairyConfig.getOption("5429").equals("1")) {
                    teamTask.gen_zg();
                    singleTask.li_rank();
                }
                break;
        }

        LtLog.e(mFairy.getLineInfo("勾选任务已经全部完成,End!" + AtFairyConfig.getTaskID()));
        //mFairy.restoreIme();
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
