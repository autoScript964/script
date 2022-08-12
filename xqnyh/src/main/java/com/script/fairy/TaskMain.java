package com.script.fairy;
import com.script.content.ScProxy;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;



import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

/**
 * Created by Administrator on 2019/1/24 0024.
 *
 *
 */

public class TaskMain {
    AtFairyImpl mFairy;
    GameUtil gameUtil;
    TeamTask teamTask;
    SingleTask singleTask;
    FindResult result;
    LimitlessTask limitlessTask;
    TimingActivity timingActivity;
    OtherGame otherGame;

    static List<String> list = new ArrayList<>();

    public TaskMain(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        mFairy.setGameName("新倩女幽魂");
        mFairy.setGameVersion(586);
        init();
        gameUtil = new GameUtil(mFairy);
        teamTask = new TeamTask(mFairy);
        singleTask = new SingleTask(mFairy);
        limitlessTask = new LimitlessTask(mFairy);
        timingActivity = new TimingActivity(mFairy);
        otherGame = new OtherGame(mFairy);
        mFairy.initMatTime();
        list.clear();
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

    public void main() throws Exception {

        if (!AtFairyConfig.getOption("task_id").equals("")) {
            task_id = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }

        ScProxy.config().Level().capturing(4);

        taskStartTime(AtFairyConfig.getOption("start_time"));

        if (task_id != 1809 && task_id != 1825 && task_id != 1837 && task_id != 2454 && task_id != 2701&& task_id != 2733) {
            gameUtil.setUp();
            gameUtil.close(1);
            result = mFairy.findPic(806, 597, 1275, 710, "qhhome.png");
            if (result.sim > 0.8f) {
                gameUtil.goCity("杭州");
            }
        }

        switch (task_id) {
            case 2733:
                singleTask.diaoyu();
                break;
            case 2701:
                gameUtil.coordinate("镇郊荒野", 17, 22);
                break;
            case 2454:
                int shop = 0;
                while (shop == 0) {
                    Thread.sleep(5000);
                }
                break;
            case 2615:
                gameUtil.cancelFollowing();

                if (AtFairyConfig.getOption("cs").equals("1")) {
                    gameUtil.clearbag();
                }

                singleTask.digMap();//挖宝
                break;
            case 1811://日常

                if (AtFairyConfig.getOption("juese_1").equals("1")) {
                    list.add("1");
                }
                if (AtFairyConfig.getOption("juese_2").equals("1")) {
                    list.add("2");
                }
                if (AtFairyConfig.getOption("juese_3").equals("1")) {
                    list.add("3");
                }
                if (AtFairyConfig.getOption("juese_4").equals("1")) {
                    list.add("4");
                }
                if (AtFairyConfig.getOption("jues_1").equals("1")) {
                    list.add("1");
                }

                if (AtFairyConfig.getOption("jues_2").equals("1")) {
                    list.add("2");
                }

                if (AtFairyConfig.getOption("jues_3").equals("1")) {
                    list.add("3");
                }
                if (AtFairyConfig.getOption("jues_4").equals("1")) {
                    list.add("4");
                }

                for (int i = 0; i < 10; i++) {

                    gameUtil.cancelFollowing();
                    if (AtFairyConfig.getOption("4609").equals("1")) {
                        gameUtil.clearbag();
                    }
                    //签到
                    if (AtFairyConfig.getOption("qd").equals("1")) {
                        otherGame.welfare();
                    }
                    //师门
                    if (AtFairyConfig.getOption("4601").equals("1")) {
                        singleTask.master();
                    }
                    //师门课业
                    if (AtFairyConfig.getOption("5033").equals("1")) {
                        singleTask.master1();
                    }
                    //宝图
                    if (AtFairyConfig.getOption("bt").equals("1")) {
                        singleTask.map();
                    }
                    //关宁积分买宝图
                    if (AtFairyConfig.getOption("gnjfmbt").equals("1")) {
                        otherGame.gnjfhbt();
                    }
                    if ((AtFairyConfig.getOption("cbtpt").equals("1") || AtFairyConfig.getOption("cbtzj").equals("1") || AtFairyConfig.getOption("cbtgj").equals("1") || AtFairyConfig.getOption("cbthj").equals("1"))) {
                        singleTask.digMap();
                    }
                    //联赛战龙
                    if (AtFairyConfig.getOption("lszl").equals("1")) {
                        singleTask.lsWar();
                    }
                    //战龙
                    if (AtFairyConfig.getOption("zlt").equals("1")) {
                        singleTask.war();
                    }
                    //联赛备战
                    if (AtFairyConfig.getOption("lscg").equals("1")) {
                        singleTask.lsroutine();
                    }
                    //联赛门派挑战
                    if (AtFairyConfig.getOption("lsmptz").equals("1")) {
                        singleTask.lsFactions();
                    }
                    //门派挑战
                    if ((AtFairyConfig.getOption("mptz").equals("1") || AtFairyConfig.getOption("mptz1").equals("1"))) {
                        singleTask.factions();
                    }
                    //货运
                    if (AtFairyConfig.getOption("hyrw").equals("1")) {
                        singleTask.freight();
                    }
                    //跑商
                    if (AtFairyConfig.getOption("bhps").equals("1")) {
                        singleTask.sports();
                    }
                    //重温剧情
                    singleTask.plot();
                    if (AtFairyConfig.getOption("4609").equals("1")) {
                        gameUtil.clearbag();
                    }
                    //带队一条龙
                    if (AtFairyConfig.getOption("dd").equals("1")) {
                        teamTask.aDragon();
                    }
                    //跟队一条龙
                    if (AtFairyConfig.getOption("gd").equals("1")) {
                        teamTask.aDragon1();
                    }
                    if (AtFairyConfig.getOption("4609").equals("1")) {
                        gameUtil.clearbag();
                    }
                    if (!(AtFairyConfig.getOption("phb").equals(""))) {
                        singleTask.rankingList();
                    }
                    if (AtFairyConfig.getOption("4609").equals("1")) {
                        gameUtil.clearbag();
                    }
                    otherGame.collection();
                    otherGame.collection1();
                    otherGame.collection2();

                    LtLog.e(mFairy.getLineInfo("list===" + list.toString()));
                    if (list.size() != 0) {
                        singleTask.switchedRoles();
                        TaskMain.list.remove(0);
                    } else {
                        break;
                    }
                }
                break;
            case 1817://野外挂机
                limitlessTask.fieldHangMachine();
                break;
            case 1821://签到
                otherGame.welfare();
                break;
            case 1813://一条龙
                if (AtFairyConfig.getOption("4833").equals("1")) {
                    gameUtil.clearbag();
                }
                //带队一条龙
                if (AtFairyConfig.getOption("dd").equals("1")) {
                    teamTask.aDragon();
                }
                //跟队一条龙
                if (AtFairyConfig.getOption("gd").equals("1")) {
                    teamTask.aDragon1();
                }
                if (AtFairyConfig.getOption("4833").equals("1")) {
                    gameUtil.clearbag();
                }

                gameUtil.goCity("杭州");

                break;
            case 1815:
                //重温剧情
                if (AtFairyConfig.getOption("jqnd").equals("1")) {
                    singleTask.plot();
                }
                if (AtFairyConfig.getOption("jqnd").equals("2")) {
                    singleTask.plot1();
                }
                break;
            case 1819:
                //采集
                otherGame.collection();
                otherGame.collection1();
                otherGame.collection2();
                break;
            case 1827:
                //出家
                singleTask.makeHome();
                break;
            case 1829:
                //固定队跟队
                limitlessTask.fixedTeam();
                break;
            case 1831:
                //收菜种菜
                /*otherGame.homefoodWile();*/
                gameUtil.goCity("家园");
                otherGame.newhomefoodjc();
                break;

            case 1825:
                //自动技能
                otherGame.sfjnWhile();
                break;
            case 1823:
                //抢红包
                otherGame.grabRed(0);
                break;
            case 1809:
                //新手
                if (AtFairyConfig.getOption("juese_1").equals("1")) {
                    list.add("1");
                }
                if (AtFairyConfig.getOption("juese_2").equals("1")) {
                    list.add("2");
                }
                if (AtFairyConfig.getOption("juese_3").equals("1")) {
                    list.add("3");
                }
                if (AtFairyConfig.getOption("juese_4").equals("1")) {
                    list.add("4");
                }
                if (AtFairyConfig.getOption("jues_1").equals("1")) {
                    list.add("1");
                }
                if (AtFairyConfig.getOption("jues_2").equals("1")) {
                    list.add("2");
                }
                if (AtFairyConfig.getOption("jues_3").equals("1")) {
                    list.add("3");
                }
                if (AtFairyConfig.getOption("jues_4").equals("1")) {
                    list.add("4");
                }
                while (mFairy.condit()) {
                    singleTask.novice();

                    if (AtFairyConfig.getOption("sm").equals("1")) {
                        singleTask.master1();
                    }

                    LtLog.e(mFairy.getLineInfo("list===" + list.toString()));
                    if (list.size() != 0) {
                        singleTask.switchedRoles();
                        TaskMain.list.remove(0);
                    } else {
                        break;
                    }
                }
                break;
            case 1837:
                gameUtil.goCity("家园");
                otherGame.newhomefoodjc();
                //   mFairy.killUserGame();
                break;
        }

        LtLog.e(mFairy.getLineInfo("勾选任务已经全部完成,End!" + AtFairyConfig.getTaskID()));
        mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
        Thread.sleep(2000);
    }

    private int task_id;

    public void init() throws Exception {
        task_id = 0;
        try {
            JSONObject optionJson = new JSONObject(AtFairyConfig.getUserTaskConfig());
            LtLog.e(mFairy.getLineInfo("选项列表" + optionJson));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!AtFairyConfig.getOption("task_id").equals("")) {
            task_id = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }
    }

}
