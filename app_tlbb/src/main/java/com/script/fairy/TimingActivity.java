package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import com.script.opencvapi.Thread.Thread;


/**
 * Created by Administrator on 2018/4/14.
 */

public class TimingActivity {

    private AtFairyImpl mFairy;
    AtFairy2.OpencvResult result;
    FindResult findResult;
    private PublicFunction publicFunction;
    private int indianaIndex = 0;
    private GamePublicFunction gamePublicFunction;
    private Abnormal abnormal;
    private Other other;
    private int tongPractice = 0;
    private List<String> list = new ArrayList<>();
    private List<String> mTaskList = new ArrayList<>();
    private Random random = new Random(100);
    private int mState;
    private int collection = 1;
    private long time = System.currentTimeMillis() / 1000;
    private long timex = System.currentTimeMillis() / 1000;
    private long time1 = System.currentTimeMillis() / 1000;
    private long time1x = System.currentTimeMillis() / 1000;
    private int cultivateProductionTime = 0, rearProductionTime = 0, cultureProductionTime = 0;
    private int cultivate;
    private int rear;
    private int culture;
    private int xy = 0, xy1 = 0;
    private int readbook = 0;

    public TimingActivity(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
        gamePublicFunction = new GamePublicFunction(mFairy);
        other = new Other(mFairy);
    }

    public void timingActivityStart() throws Exception {
        //如果定时任务在执行中 返回 1
        //只有当list 中的任务完成 或者识别到等级不够 时  删除 mFairy.taskList中对应的定时任务 等待当天00.01 分 重置选项中的所有任务
        timingActivityTask();
        weekTimingTask();
        everydayTimingTask();
    }

    private void weekTimingTask() throws Exception {

        //8点周活动
        String currSun = publicFunction.getCurrSun();
        int currtime = publicFunction.getMinuteNumber();
        LtLog.i(publicFunction.getLineInfo() + "---------currtime>" + currtime);
        LtLog.i(publicFunction.getLineInfo() + "---------currSun>" + currSun);

        if (currSun.equals("星期一") || currSun.equals("星期四")) {
            //演武堂周一 四 日 11:00-11:30  17：00-17：30  21：30-22：00
            //|| gamePublicFunction.outDungeons() == 1
            if ((currtime >= 660 && currtime <= 690) || (currtime >= 1020 && currtime <= 1050) || (currtime >= 1290 && currtime <= 1320)) {
                LtLog.i(publicFunction.getLineInfo() + "----------演武堂-------------------->");
                if (TaskMain.taskList.indexOf("YWT.png") != -1) {
                    list.add("YWT.png");

                    gamePublicFunction.automaticCombat(0);
                    timingActivitySingle();
                }
            }
        }

        if (currSun.equals("星期日")) {
            LtLog.i(publicFunction.getLineInfo() + "---------currtime>" + currtime);
            //16:00-18:00  ,, 21:20-23:20
            if ((currtime >= 960 && currtime <= 1079) || (currtime >= 1280 && currtime <= 1399)) {
                if (TaskMain.taskList.indexOf("desert.png") != -1) {
                    list.add("desert.png");
                    timingActivitySingle();
                }
            }
        }


        if (currtime >= 1200 && currtime < 1300) {
            list.clear();
            LtLog.i(publicFunction.getLineInfo() + "---------currtime>" + currtime);
        } else {
            //如果不在 20点到22点间 退出
            return;
        }

        //String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        switch (currSun) {
            case "星期一":
                //帮会杀星 或者 帮会御膳比拼
                //time_1>=1200 and time_1<=1230
                if (currtime >= 1200 && currtime <= 1230) {
                    // Collections.swap(list, 0, list.indexOf("zlqj.png"));
                    LtLog.i(publicFunction.getLineInfo() + "----------帮会杀星-----帮会御膳比拼--------------->" + TaskMain.taskList + ",TaskMain.taskList.indexOf(\"tongMeal.png\")" + TaskMain.taskList.indexOf("tongMeal.png") + ",TaskMain.taskList.indexOf(\"tongAssassin.png\")" + TaskMain.taskList.indexOf("tongAssassin.png"));
                    if (TaskMain.taskList.indexOf("tongMeal.png") != -1) {
                        list.add("tongMeal.png");
                        timingActivityTeam();
                    } else if (TaskMain.taskList.indexOf("tongAssassin.png") != -1) {
                        list.add("tongAssassin.png");
                        timingActivitySingle();
                    }
                }
                break;
            case "星期二":
                //华山论剑
                if (TaskMain.taskList.indexOf("HSLJ.png") != -1 && currtime > 1200 && currtime < 1232) {
                    LtLog.i(publicFunction.getLineInfo() + "----------华山论剑-------------------->");
                    list.add("HSLJ.png");
                    timingActivitySingle();
                }
                break;
            case "星期三":
                //帮会守卫
                if (TaskMain.taskList.indexOf("tongGuard.png") != -1 && currtime > 1200 && currtime < 1230) {
                    LtLog.i(publicFunction.getLineInfo() + "----------帮会守卫-------------------->");
                    list.add("tongGuard.png");
                    timingActivitySingle();
                }
                break;
            case "星期四":
                //门派竞技
                if (TaskMain.taskList.indexOf("sports.png") != -1 && currtime > 1198 && currtime < 1235) {
                    LtLog.i(publicFunction.getLineInfo() + "----------门派竞技-------------------->");
                    list.add("sports.png");
                    timingActivitySingle();
                }
                break;
            case "星期五":
                //领地争夺战
                if (TaskMain.taskList.indexOf("territory.png") != -1 && currtime > 1200 && currtime < 1260) {
                    LtLog.i(publicFunction.getLineInfo() + "----------领地争夺战-------------------->");
                    list.add("territory.png");
                    timingActivitySingle();
                }
                break;
            case "星期六":
                // 领地争夺战
                if (TaskMain.taskList.indexOf("territory.png") != -1 && currtime > 1200 && currtime < 1260) {
                    LtLog.i(publicFunction.getLineInfo() + "----------领地争夺战-------------------->");
                    list.add("territory.png");
                    timingActivitySingle();
                }
                break;
            case "星期日":
                //踢球
                if (TaskMain.taskList.indexOf("footBall.png") != -1 && currtime > 1200 && currtime < 1260) {
                    LtLog.i(publicFunction.getLineInfo() + "----------踢球-------------------->");
                    list.add("footBall.png");
                    timingActivityTeam();
                }
                break;
        }


//        combatFFJ.png

    }

    private void everydayTimingTask() throws Exception {
        //每日定时活动
        String currSun = publicFunction.getCurrSun();
        int currentTime = publicFunction.getMinuteNumber();
        LtLog.i(publicFunction.getLineInfo() + "----------每日定时活动--------------------currentTime>" + currentTime);
        list.clear();
        if ((currentTime > 1080 && currentTime < 1195) || (currentTime >= 720 && currentTime < 835)) {
            //珍珑棋局 12：00-14：00   18:00-20:00
            // list = TaskMain.taskList;
            if (TaskMain.taskList.indexOf("zlqj.png") != -1) {
                // Collections.swap(list, 0, list.indexOf("zlqj.png"));
                LtLog.i(publicFunction.getLineInfo() + "----------珍珑棋局-------------------->");
                list.add("zlqj.png");
                timingActivityTeam();
                //打了个补丁 ，执行完珍珑棋局后，如果list 里还有，删除他
                if (list.size() > 0) {
                    if (list.indexOf("zlqj.png") >= 0) {
                        list.remove("zlqj.png");
                    }
                }
            }
        }


        if ((currentTime >= 1260 && currentTime <= 1270) || (currentTime >= 840 && currentTime <= 850)) {
            //  14:00 -14:10                  21:00-21:10
            if (currSun.equals("星期五") || currSun.equals("星期六")) {
                LtLog.i(publicFunction.getLineInfo() + "----------要诀争夺战-------------------->");
                if (TaskMain.taskList.indexOf("secret.png") != -1) {
                    list.add("secret.png");
                    timingActivitySingle();
                }
            } else if (currSun.equals("星期二") || currSun.equals("星期四")) {
                LtLog.i(publicFunction.getLineInfo() + "----------扶风郡战场-------------------->");
                if (TaskMain.taskList.indexOf("combatFFJ.png") != -1) {
                    list.add("combatFFJ.png");
                    timingActivitySingle();
                }
            } else {
                LtLog.i(publicFunction.getLineInfo() + "----------宋辽战场-------------------->");
                result = publicFunction.localFindPic(1109, 0, 1228, 37, "SLDZ2.png");
                LtLog.i(publicFunction.getLineInfo() + "----------result-------------------->" + result);
                if (TaskMain.taskList.indexOf("SLDZ.png") != -1 || gamePublicFunction.outDungeons() == 1 || result.sim >= 0.7) {
                    // Collections.swap(list, 0, list.indexOf("zlqj.png"));
                    LtLog.i(publicFunction.getLineInfo() + "----------宋辽战场-------------------->");
                    list.add("SLDZ.png");
                    timingActivitySingle();
                }
            }
        }


        if ((currentTime >= 750 && currentTime < 810) || (currentTime > 1230 && currentTime < 1280)) {
            //帮会运镖  12:30-13:30 20：30-21：30 帮会运镖与珍珑棋局有时间冲突
            if (TaskMain.taskList.indexOf("tongYB.png") != -1) {
                // Collections.swap(list, 0, list.indexOf("zlqj.png"));
                LtLog.i(publicFunction.getLineInfo() + "----------帮会运镖-------------------->");
                list.add("tongYB.png");
                timingActivitySingle();
            }
        }

        if ((currentTime >= 750 && currentTime < 840) || (currentTime >= 30 && currentTime < 120) || (currentTime >= 360 && currentTime < 480) || (currentTime >= 1080 && currentTime < 1140)) {
            //定时采集
            if (TaskMain.taskList.indexOf("collectionTime1") != -1 && (currentTime >= 750 && currentTime < 840)) {
                //12:30 -14:00
                LtLog.i(publicFunction.getLineInfo() + "----------定时采集-------------------->");
                collection = other.collectionTask();
                LtLog.i(publicFunction.getLineInfo() + "----------定时采集--------------------collection=>" + collection);
                if (collection == 99) {
                    TaskMain.taskList.remove("collectionTime1");
                }
            }
            if (TaskMain.taskList.indexOf("collectionTime2") != -1 && (currentTime >= 30 && currentTime < 120)) {
                //00:30 -2:00
                LtLog.i(publicFunction.getLineInfo() + "----------定时采集-------------------->");
                collection = other.collectionTask();
                LtLog.i(publicFunction.getLineInfo() + "----------定时采集--------------------collection=>" + collection);
                if (collection == 99) {
                    TaskMain.taskList.remove("collectionTime2");

                }
            }
            if (TaskMain.taskList.indexOf("collectionTime3") != -1 && (currentTime >= 360 && currentTime < 480)) {
                //06:00 -8:00
                LtLog.i(publicFunction.getLineInfo() + "----------定时采集-------------------->");
                collection = other.collectionTask();
                LtLog.i(publicFunction.getLineInfo() + "----------定时采集--------------------collection=>" + collection);
                if (collection == 99) {
                    TaskMain.taskList.remove("collectionTime3");
                }
            }
            if (TaskMain.taskList.indexOf("collectionTime4") != -1 && (currentTime >= 1080 && currentTime < 1140)) {
                //18:00 -19:00
                LtLog.i(publicFunction.getLineInfo() + "----------定时采集-------------------->");
                collection = other.collectionTask();
                LtLog.i(publicFunction.getLineInfo() + "----------定时采集--------------------collection=>" + collection);
                if (collection == 99) {
                    TaskMain.taskList.remove("collectionTime4");
                }
            }

        }
        if ((currentTime > 870 && currentTime < 920) || (currentTime > 960 && currentTime < 1010) || (currentTime > 1320 && currentTime < 1365) || (currentTime > 1083 && currentTime < 1125)) {
            // 夺宝奇兵 12：30-13：20  //此时间段 宝箱抢不到 不参加 //  14:30-15:20// 16:00-16:50 //18:00-18:50//22:00-22:50
            if (TaskMain.taskList.indexOf("indiana.png") != -1) {
                LtLog.i(publicFunction.getLineInfo() + "----------夺宝奇兵-------------------->");
                // Collections.swap(list, 0, list.indexOf("zlqj.png"));
                list.add("indiana.png");
                timingActivityTeam();

            }
        }
        if (currentTime > 1140 && currentTime < 1155) {
            //帮会行酒令 19：00-19：15
            if (TaskMain.taskList.indexOf("tongDrink.png") != -1) {
                LtLog.i(publicFunction.getLineInfo() + "----------帮会行酒令-------------------->");
                // Collections.swap(list, 0, list.indexOf("zlqj.png"));
                list.add("tongDrink.png");
                timingActivitySingle();
            }
        }

        //帮会战备任务
        if (currentTime >= 1200 && currentTime < 1320) {
            //帮会战备任务 20：00-22：00
            if (TaskMain.taskList.indexOf("bhzbrw.png") != -1) {
                LtLog.i(publicFunction.getLineInfo() + "----------帮会战备任务-------------------->");
                list.add("bhzbrw.png");
                timingActivitySingle();
                exitZD();
            }
        }

        //帮会战备任务- 叫嚣
        if (currentTime >= 1200 && currentTime < 1320) {
            //帮会战备任务 20：00-22：00
            if (TaskMain.taskList.indexOf("bhzbrw1.png") != -1) {
                LtLog.i(publicFunction.getLineInfo() + "----------帮会战备任务1-------------------->");
                list.add("bhzbrw1.png");
                timingActivitySingle();
                exitZD();
            }
        }


        if (currentTime >= 1154 && currentTime < 1190) {
            //"帮会练功19:15-20:00"
            LtLog.i(publicFunction.getLineInfo() + "----------帮会练功-------------------->");
            if (TaskMain.taskList.indexOf("tongPractice.png") != -1) {
                LtLog.i(publicFunction.getLineInfo() + "----------帮会练功-------------------->");
                // Collections.swap(list, 0, list.indexOf("zlqj.png"));
                list.add("tongPractice.png");
                tongPractice();
            }
        }
    }

    private void timingActivityTeam() throws Exception {
        //组队活动
        //帮会御膳比拼
        //珍珑棋局
        //夺宝奇兵
        //帮会练功
        //踢球
        //首次进入
        LtLog.i(publicFunction.getLineInfo() + "-------------------首次进入---------mState>" + mState);
        if (list.get(0).equals("tongPractice.png")) {
            gamePublicFunction.exitTeam();
            gamePublicFunction.closeWindow();
        }
        gamePublicFunction.createTeam();//创建队伍
        gamePublicFunction.closeWindow();

        if (list.get(0).equals("tongPractice.png") == false && gamePublicFunction.outDungeons() == 0) {
            gamePublicFunction.openActivity(2);//打开限时活动
        }
        while (mFairy.condit()) {
            if (Math.abs(random.nextInt()) % 100 >= 70) {
                LtLog.i(publicFunction.getLineInfo() + "-------timingActivityTeam>" + "__timex=" + timex);
            }
            if (gamePublicFunction.outDungeons() == 0) {
                LtLog.i(publicFunction.getLineInfo() + "-------不在副本中 取消战斗>");
                gamePublicFunction.automaticCombat(0);
            }
            int state = lookUpTask(list);//查看任务
            if (state == 2) {
                gamePublicFunction.closeWindow();
                return;
            } else if (state == 1) {
                LtLog.i(publicFunction.getLineInfo() + "------- remove >" + list.get(0));
                TaskMain.taskList.remove(list.get(0));
//                list.remove(0);
                LtLog.i(publicFunction.getLineInfo() + "------- remove >" + list.get(0));
                gamePublicFunction.closeWindow();
                return;
            }
            if (gamePublicFunction.outDungeons() == 1) {
                //如果在副本中
                gamePublicFunction.follow(0);
                gamePublicFunction.automaticCombat(1);//开启自动战斗
                time = System.currentTimeMillis() / 1000;
            } else {
                result = publicFunction.localFindPic(653, 1, 1104, 96, "activity.png");
                if (result.sim > 0.8) {
                    //没有活动按钮 不识别坐标
                    LtLog.i(publicFunction.getLineInfo() + "---------activity.png>" + result);
                    xy = publicFunction.getXY(1118, 31, 1198, 48, 200, 255, 0);
//                    xy=ocrNumber.getNumber(1120,29,74,14,new Scalar(200,200,200),new Scalar(255,255,255));
                }
                if (xy != xy1 && xy > 0) {
                    LtLog.i(publicFunction.getLineInfo() + "-------xy>" + xy);
                    xy1 = xy;
                    time = System.currentTimeMillis() / 1000;
                }
            }
            timex = System.currentTimeMillis() / 1000 - time;
            int vacancyNumber = 0;
            result = publicFunction.localFindPic(617, 13, 702, 71, "team.png");
            if (result.sim >= 0.8) {
                //如果在组队界面
                LtLog.i(publicFunction.getLineInfo() + "---------team>" + result);
                result = publicFunction.localFindPic(802, 617, 926, 664, "propaganda.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------propaganda>" + result);
                    if (Math.abs(random.nextInt()) % 100 >= 80) {
                        publicFunction.rndTapWH(result.x, result.y, 50, 20);
                        Thread.sleep(500);
                    }
                }
                gamePublicFunction.tirenAndAgree(); //踢离线  同意申请
                //查看当前有几个空位
                for (int i = 0; i < 4; i++) {
//                result = publicFunction.localToValueFindPic(1000 - (i * 205), 292, 1124 - (i * 205), 418, 90, 255, 1, "vacancy.png");
                    result = publicFunction.localFindPic(1000 - (i * 205), 292, 1124 - (i * 205), 418, "vacancy.png");
                    if (result.sim >= 0.75) {
                        LtLog.i(publicFunction.getLineInfo() + "-------vacancy>" + result);
                        vacancyNumber = vacancyNumber + 1;
                    }
                }
                if (vacancyNumber <= 2) {
                    gamePublicFunction.closeWindow();
                    gamePublicFunction.follow(1);
                    gamePublicFunction.openActivity(2);
                }
                LtLog.i(publicFunction.getLineInfo() + "-------当前队伍空位>" + vacancyNumber);
                if (list.get(0) == "tongPractice.png") {
                    //帮会练功  两个人就可以了
                    if (vacancyNumber <= 3) {
                        gamePublicFunction.closeWindow();
                        gamePublicFunction.openActivity(2);
                    }
                }
            } else if (timex >= 15) {
                //如果不在组队界面
                LtLog.i(publicFunction.getLineInfo() + "-------list.get(0).equals(indiana.png)>" + list.get(0).equals("indiana.png"));
                result = publicFunction.localFindPic(653, 1, 1104, 96, "activity.png");
                LtLog.i(publicFunction.getLineInfo() + "-------activity>" + result);
                if (list.get(0).equals("indiana.png") && result.sim >= 0.8) {
                    for (int i = 0; i < 3; i++) {
                        LtLog.i(publicFunction.getLineInfo() + "-------点击中间>");
                        publicFunction.rndTap(629, 410, 650, 460);
                        Thread.sleep(2000);
                        result = publicFunction.localFindPic(512, 361, 693, 414, "open.png");
                        if (result.sim >= 0.8) {
                            break;
                        }
                    }
                }
                result = publicFunction.localFindPic(512, 361, 693, 414, "open.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------open>" + result);
                    publicFunction.rndTapWH(result.x, result.y, 50, 20);
                    Thread.sleep(500);
                    time = System.currentTimeMillis() / 1000;
                } else {
                    gamePublicFunction.follow(0);
                    gamePublicFunction.follow(1);
                    boolean look = false;
                    if (list.get(0).equals("indiana.png") && indianaIndex < 3) {
                        //如果是 夺宝奇兵任务 首先打开地图查看是否有宝箱 如果有 直接走过去 不需要再打开限时活动
                        //indianaIndex 记录点了多少次地图 每开两次打开一次活动，看活动是否完成
                        LtLog.i(publicFunction.getLineInfo() + "-------indianaIndex>" + indianaIndex);
                        indianaIndex = indianaIndex + 1;
                        look = lookBox();
                    }
                    if (look == false) {
                        indianaIndex = 0;
                        gamePublicFunction.openActivity(2);//打开限时活动
                    }
                    time = System.currentTimeMillis() / 1000;
                }
            }

            result = publicFunction.localFindPic(706, 446, 926, 664, "immediately.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------immediately>" + result);
                publicFunction.rndTapWH(result.x, result.y, 50, 20);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(559, 141, 710, 191, "teamState.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------teamState>" + result);
                publicFunction.rndTapWH(905, 163, 924, 186);
                Thread.sleep(2000);
                gamePublicFunction.follow(0);
                gamePublicFunction.follow(1);
            }
            result = publicFunction.localFindPic(465, 265, 694, 347, "applyCaptain.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------applyCaptain>" + result);
                publicFunction.rndTap(486, 462, 530, 481);//点取消
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(561, 92, 730, 151, "invitingFriends.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------invitingFriends=" + result);
                publicFunction.rndTap(923, 112, 942, 135);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(660, 108, 743, 159, "condition.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------condition=" + result);
                publicFunction.rndTap(949, 123, 971, 147);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(463, 300, 653, 391, "fullPersonnel.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------fullPersonnel=" + result);
                publicFunction.rndTap(745, 460, 796, 487);  //确定
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(398, 283, 715, 396, "noTeam.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------noTeam=" + result);
                publicFunction.rndTap(752, 464, 787, 481);  //确定
                Thread.sleep(1000);
            }
            switch (list.get(0)) {
                case "zlqj.png":
                    zlqj();
                    break;
                case "indiana.png":
                    break;
                case "footBall.png":
                    football();
                    break;
                case "tongPractice.png":
                    tongPractice();
                    break;
                case "tongMeal.png":
                    tongMeal();
                    break;

            }
        }
    }

    private boolean goBHZD(int num) throws Exception {
        for (int i = 0; i < 30; i++) {

            LtLog.i(mFairy.getLineInfo("前往阵地循环" + i));

            Thread.sleep(1000);

            gamePublicFunction.switchSkillOrSet("set");

            result = publicFunction.localFindPic(932, 179, 1267, 702, "tong2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "帮会" + result);
                publicFunction.rndTapWH(result.x, result.y, 29, 32);
                Thread.sleep(2000);
            }
            findResult = mFairy.findPic(576, 617, 1123, 688, "bhzd.png");
            if (findResult.sim >= 0.8) {
                mFairy.onTap(0.8f, findResult, "前往阵地", 3000);
                gamePublicFunction.closeWindow();
                i = 0;
            }
            //

            switch (num) {
                case 1:
                    findResult = mFairy.findPic(474, 346, 716, 515, "bhzd2.png");
                    mFairy.onTap(0.8f, findResult, "敌对阵地", 3000);
                    break;
                default:
                    findResult = mFairy.findPic(474, 346, 716, 515, "bhzd1.png");
                    mFairy.onTap(0.8f, findResult, "本地阵地", 5000);
                    break;
            }

            findResult = mFairy.findPic("bhzd3.png");
            if (findResult.sim >= 0.8f) {
                return true;
            }

            FindResult jinru = mFairy.findPic("bhzd5.png");
            if (jinru.sim >= 0.8f) {

                findResult = mFairy.findPic("bhzd4.png");
                if (findResult.sim >= 0.8f) {
                    mFairy.onTap(869, 155, 886, 169, "没有敌对阵地", 1000);
                    return false;
                }

                mFairy.onTap(612, 211, 651, 222, "", 1500);

                mFairy.onTap(0.8f, jinru, "进入", 5000);
            }

        }

        return false;
    }

    private void removeListTask() throws Exception {
        gamePublicFunction.closeWindow();

        if (TaskMain.taskList.size() > 0 && list.size() > 0) {
            LtLog.i(publicFunction.getLineInfo() + "------- remove >" + list.get(0));
            TaskMain.taskList.remove(list.get(0));
        }

        if (list.size() > 0) {
            LtLog.i(publicFunction.getLineInfo() + "------- remove >" + list.get(0));
            list.remove(0);
        }
    }

    private void timingActivitySingle() throws Exception {
        //单人活动
        //帮会行酒令
        //帮会运镖
        //宋辽大战
        //华山论剑
        //帮会杀星
        //帮会守卫
        //演武堂
        //门派竞技
        //吃鸡
        //首次进入
        //帮会备战任务 bhzbrw

        Mat mat1 = null, mat2 = null;

        if (list.get(0).equals("tongYB.png") || list.get(0).equals("tongDrink.png") || list.get(0).equals("sports.png")
                || list.get(0).equals("secret.png") || list.get(0).equals("bhzbrw.png") || list.get(0).equals("bhzbrw1.png")) {
            gamePublicFunction.exitTeam();//退出队伍
        }

        if (gamePublicFunction.outDungeons() == 1) {
            LtLog.i(publicFunction.getLineInfo() + "-------在副本中>");
        } else {
            LtLog.i(publicFunction.getLineInfo() + "-------不在副本中>");
            gamePublicFunction.closeWindow();
            gamePublicFunction.openActivity(2);//打开限时活动
        }

        while (mFairy.condit()) {
            LtLog.i(publicFunction.getLineInfo() + "任务循环中..." + list.get(0) + "   currTime:" + timex);

            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 50) {
                gamePublicFunction.closeWindow();
                gamePublicFunction.openActivity(2);//打开限时活动
                Thread.sleep(2000);
                time = System.currentTimeMillis() / 1000;
            } else if (timex >= 20) {
                //如果发呆20秒 首先查看任务栏是否有任务

                result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
                if (result.sim < 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------openActivity--activity>" + result);
                    return;
                }

                int result = lookUpTaskbar();

                if (result == 2) {
                    LtLog.i(mFairy.getLineInfo("左侧任务没有找到"));
                    removeListTask();
                    return;
                } else if (result == 1) {
                    time = System.currentTimeMillis() / 1000;
                }

                if (list.get(0) == "bhzbrw1.png") {
                    findResult = mFairy.findPic("bhzd3.png");
                    if (findResult.sim >= 0.8f) {
                        mFairy.onTap(1130, 391, 1153, 409, "背包", 1000);
                        time = System.currentTimeMillis() / 1000;
                    }
                }
            }

            int minuteNumber = publicFunction.getMinuteNumber();

            int state = lookUpTask(list);//查看任务
            if (state == 2) {
                gamePublicFunction.closeWindow();
                return;
            } else if (state == 1) {
                removeListTask();
                return;
            }

            if (gamePublicFunction.outDungeons() == 1) {
                //如果在副本中 并重置 time
                gamePublicFunction.automaticCombat(1);//开启自动战斗
                time = System.currentTimeMillis() / 1000;
            } else {
                /*result = publicFunction.localFindPic(130, 154, 263, 270, "haveTem.png");
                if (list.get(0).equals("tongYB.png") || list.get(0).equals("tongDrink.png") || list.get(0).equals("bhzbrw.png")|| list.get(0).equals("sports.png")
                        && result.sim >= 0.8 || list.get(0).equals("combatFFJ.png")) {
                    LtLog.i(publicFunction.getLineInfo() + "-------result>" + result);
                    gamePublicFunction.exitTeam();//退出队伍
                }*/

                result = publicFunction.localFindPic(653, 1, 1104, 96, "activity.png");
                if (result.sim >= 0.8) {
                    boolean matSim = false;
                    mat1 = mFairy.getScreenMat(1121, 27, 66, 22, 1, 0, 0, 1);
                    mat1 = publicFunction.returnToValueMat(mat1, 200, 255, 0);
                    if (mat2 != null) {
                        matSim = publicFunction.judgeMatAndMatChange(0.9, mat1, mat2);
                        //判断两个矩阵的相似度大于 sim 则返回 true;
                    }

                    if (matSim) {
                        LtLog.i(publicFunction.getLineInfo() + "-------matSim>" + matSim);
                    } else {
                        //如果两个矩阵不相等 重置时间
                        mat2 = mFairy.getScreenMat(1121, 27, 66, 22, 1, 0, 0, 1);
                        mat2 = publicFunction.returnToValueMat(mat2, 200, 255, 0);
                        LtLog.i(publicFunction.getLineInfo() + "-------matSim>" + matSim);
                        time = System.currentTimeMillis() / 1000;
                    }

                }
            }


            if (list.get(0) == "SLDZ.png") {
                result = publicFunction.localFindPic(1117, 3, 1209, 27, "SLDZ2.png");
                if (result.sim >= 0.8) {
                    //宋辽大战 重置开启活动时间
                    LtLog.i(publicFunction.getLineInfo() + "-------SLDZ2>" + result);
                    time = System.currentTimeMillis() / 1000;
                }
            }


            if (list.get(0) == "bhzbrw.png" || list.get(0) == "bhzbrw1.png") {
                findResult = mFairy.findPic(540, 489, 725, 540, "bz4.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "正在备战...");
                    time = System.currentTimeMillis() / 1000;
                }
            }

            LtLog.i(publicFunction.getLineInfo() + "------------list.get(0).png=" + list.get(0));

            findResult = mFairy.findPic("qian2.png");
            mFairy.onTap(0.8f, findResult, 1216, 84, 1228, 96, "关闭签到界面", 3000);

            switch (list.get(0)) {
                case "bhzbrw.png":

                    findResult = mFairy.findPic(517, 361, 588, 552, "bz.png");
                    if (findResult.sim > 0.8f) {

                        findResult = mFairy.findPic(439, 354, 1053, 549, "bz1.png");
                        mFairy.onTap(0.8f, findResult, "练兵", 2000);

                        mFairy.onTap(728, 404, 756, 428, "再次对话", 2000);

                        findResult = mFairy.findPic(439, 354, 1053, 549, "bz2.png");
                        mFairy.onTap(0.8f, findResult, "巡查", 2000);

                        if (goBHZD(0) == false) {
                            removeListTask();
                            return;
                        }
                    }

                    findResult = mFairy.findPic(new String[]{"bz5.png", "bz6.png"});
                    mFairy.onTap(0.8f, findResult, "备战操作", 3000);

                    break;
                case "bhzbrw1.png":

                    findResult = mFairy.findPic(517, 361, 588, 552, "bz.png");
                    if (findResult.sim > 0.8f) {

                        findResult = mFairy.findPic(439, 354, 1053, 549, "bz7.png");
                        mFairy.onTap(0.8f, findResult, "示威", 2000);

                        if (goBHZD(1) == false) {
                            removeListTask();
                            return;
                        }

                        findResult = mFairy.findPic("bhzd3.png");
                        if (findResult.sim >= 0.8f) {
                            mFairy.onTap(1130, 391, 1153, 409, "背包", 1000);
                        }
                    }


                    findResult = mFairy.findPic("pack.png");
                    if (findResult.sim > 0.8f) {
                        mFairy.onTap(1085, 92, 1108, 103, "其他", 1000);

                        for (int i = 0; i < 10; i++) {
                            findResult = mFairy.findPic(686, 139, 1155, 528, "shiwei.png");
                            if (findResult.sim > 0.8f) {
                                mFairy.onTap(0.8f, findResult, "示威", 2500);

                                findResult = mFairy.findPic(381, 132, 693, 358, "shiwei1.png");
                                if (findResult.sim > 0.8f) {
                                    mFairy.onTap(0.8f, findResult, "使用", 2500);
                                    return;
                                }

                            } else {
                                mFairy.ranSwipe(925, 450, 925, 160, 1000, 2000);
                            }
                        }
                        gamePublicFunction.closeWindow();
                    }
                    break;

                case "tongYB.png":
                    tongYB();
                    break;
                case "tongDrink.png":
                    if (minuteNumber >= 1140 && minuteNumber <= 1155 && TaskMain.optionJson.optString("dice_Answer").equals("1")) {
                        LtLog.i(publicFunction.getLineInfo() + "------------minuteNumber=" + minuteNumber);
                        tongAnswerAndDice();
                    }
                    break;
                case "tongAssassin.png":
                    tongAssassin();
                    break;
                case "HSLJ.png":
                    HSLJ();
                    break;
                case "SLDZ.png":
                    songLiao();
                    break;
                case "tongGuard.png":
                    tongGuard();
                    break;
                case "sports.png":
                    sports();
                    break;
                case "territory.png":
                    territory();
                    break;
                case "YWT.png":
                    yanWuTang();
                    break;
                case "desert.png":
                    desert();
                    break;
                case "combatFFJ.png":
                    combatFFJ();
                    break;
                case "secret.png":
                    secret();
                    break;
            }
            result = publicFunction.localFindPic(559, 141, 710, 191, "teamState.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------teamState>" + result);
                publicFunction.rndTap(905, 163, 924, 186);
                Thread.sleep(2000);
                LtLog.i(publicFunction.getLineInfo() + "-------取消跟随>");
                gamePublicFunction.follow(0);
                LtLog.i(publicFunction.getLineInfo() + "-------召唤跟随>");
                gamePublicFunction.follow(1);
            }

            Thread.sleep(100);
        }
    }

    //退出阵地
    private void exitZD() throws Exception {
        for (int i = 0; i < 10; i++) {
            gamePublicFunction.closeWindow();

            findResult = mFairy.findPic("bhzd3.png");
            if (findResult.sim > 0.8f) {
                mFairy.onTap(0.8f, findResult, "退出阵地", 10000);
                return;
            }
        }
    }


    //要诀争夺战
    private void secret() throws Exception {

        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(459, 350, 1020, 542, "secret1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------secret1>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
            Thread.sleep(2000);
        }

    }

    //大战扶风郡
    private void combatFFJ() throws Exception {
        AtFairy2.OpencvResult result;

        findResult = mFairy.findPic("ffg.png");
        mFairy.onTap(0.8f, findResult, "npc", 1000);

        result = publicFunction.localFindPic(461, 354, 1008, 545, "combatFFJ1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------combatFFJ1>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
            Thread.sleep(2000);
        }

        result = publicFunction.localFindPic(512, 70, 768, 176, "selectSkill.png");
        LtLog.i(publicFunction.getLineInfo() + "-------selectSkill>" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------selectSkill>" + result);
//            494,240,573,268 ,,238
            time = System.currentTimeMillis() / 1000;
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(3);
                publicFunction.rndTap(494 + (i * 238), 240 + (index * 115), 573 + (i * 238), 268 + (index * 115));//  随机选择3 个技能
                Thread.sleep(1000);
            }
            publicFunction.rndTap(594, 560, 686, 581);
            Thread.sleep(1000);
        }

        result = publicFunction.localFindPic(1098, 0, 1244, 26, "combatFFJ2.png");
        if (result.sim >= 0.8) {
            time = System.currentTimeMillis() / 1000;
            LtLog.i(publicFunction.getLineInfo() + "-------combatFFJ2>" + result);
            for (int i = 0; i < 5; i++) {
                // 点技能 点攻击
                publicFunction.rndTap(1125, 478, 1150, 504);
                Thread.sleep(500);
                publicFunction.rndTap(1056, 553, 1075, 571);
                Thread.sleep(500);
                publicFunction.rndTap(1052, 652, 1079, 671);
                Thread.sleep(500);
                for (int j = 0; j < 10; j++) {
                    publicFunction.rndTap(1163, 592, 1191, 615);
                    Thread.sleep(200);
                }
            }

            publicFunction.rndTap(1178, 94, 1223, 129);
            Thread.sleep(1000);
        }


        findResult = mFairy.findPic(948, 300, 1270, 372, "ffg1.png");
        if (findResult.sim > 0.8f) {

            switch ((int) (Math.random() * 2) + 1) {
                case 1:
                    mFairy.onTap(1062, 230, 1078, 248, "1", 1000);
                    break;
                case 2:
                    mFairy.onTap(1137, 233, 1172, 246, "2", 1000);
                    break;
               /* default:
                    mFairy.onTap(1189,236,1224,246, "3", 1000);
                    break;*/
            }
            Thread.sleep(4000);
        }

        result = publicFunction.localFindPic(517, 7, 791, 78, "combatFFJ3.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------combatFFJ3>" + result);
            publicFunction.rndTap(260, 509, 262, 511);
            Thread.sleep(3000);
            gamePublicFunction.closeWindow();
        }

        result = publicFunction.localFindPic(542, 487, 736, 577, "combatFFJ4.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------combatFFJ4>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(1000);
        }

//        result = publicFunction.localFindPic(369,555,584,644, "combatFFJ5.png");
//        if (result.sim >= 0.8) {
//            LtLog.i(publicFunction.getLineInfo() + "-------combatFFJ5>" + result);
//            publicFunction.rndTap(result.x,result.y,result.x+10,result.y+10);
//            Thread.sleep(2000);
//              //退出副本 没截图到确认按钮 不退也行，自己会被踢出去
//        }


    }

    private void desert() throws Exception {
        result = publicFunction.localFindPic(505, 361, 797, 487, "attend1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------attend1>" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(2000);
            gamePublicFunction.follow(1);
        }
        if (gamePublicFunction.outDungeons() == 1) {
            result = publicFunction.localFindPic(1107, 536, 1266, 688, "combat.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------combat>" + result);
                for (int i = 0; i < 10; i++) {
                    publicFunction.rndTapWH(result.x, result.y, 50, 20);
                    Thread.sleep(500);
                }
            }
        }
    }

    private void yanWuTang() throws Exception {
        result = publicFunction.localFindPic(466, 362, 726, 515, "YWT1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------YWT1>" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(2000);
            LtLog.i(publicFunction.getLineInfo() + "-------取消跟随>");
            gamePublicFunction.follow(0);
            LtLog.i(publicFunction.getLineInfo() + "-------召唤跟随>");
            gamePublicFunction.follow(1);
        }
    }

    private void football() throws Exception {
        //踢球
        result = publicFunction.localFindPic(511, 359, 680, 421, "footBall1.png");
//        LtLog.i(publicFunction.getLineInfo() + "------------footBall1=" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------------footBall1=" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(1000);
        }
    }

    private void territory() throws Exception {
        //领地争夺战
        AtFairy2.OpencvResult result1;
        gamePublicFunction.automaticCombat(0);//取消战斗
        result = publicFunction.localFindPic(1027, 630, 1157, 678, "territoryJoin.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------territoryJoin>" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(2000);
            publicFunction.rndTap(1210, 64, 1225, 86);
        }
        result = publicFunction.localFindPic(552, 137, 788, 216, "territorySelect.png");
        if (result.sim >= 0.8) {

            LtLog.i(publicFunction.getLineInfo() + "-------territorySelect>" + result);
            publicFunction.rndTap(602, 297, 663, 351);//点选战场
            Thread.sleep(2000);
            publicFunction.rndTap(611, 520, 663, 542);//点确认
            Thread.sleep(2000);
        }
        gamePublicFunction.automaticCombat(0);
        for (int i = 0; i < 60; i++) {
//            result1 = publicFunction.localOptimalFindPic(859, 564, 914, 615, 80, 255, 1, "automaticCombat.png|automaticCombat-1.png");
            result1 = publicFunction.localFindPicHLS(859, 564, 914, 615, "automaticCombat.png|automaticCombat-1.png");
            LtLog.i(publicFunction.getLineInfo() + "-------automaticCombat>" + result);
            if (gamePublicFunction.outDungeons() == 1 && result1.sim >= 0.70) {
                LtLog.i(publicFunction.getLineInfo() + "-------automaticCombat>" + result);
                result = publicFunction.localFindPic(1027, 630, 1157, 678, "territoryFlag.png");
                AtFairy2.OpencvResult result2 = publicFunction.localFindPic(1078, 183, 1186, 267, "fight.png");
                LtLog.i(publicFunction.getLineInfo() + "-------territoryFlag>" + result + ",result2=" + result2);
                if (result.sim >= 0.8 || result2.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------territoryFlag>" + result + ",result2=" + result2);
                    publicFunction.rndTap(1120, 248, 1128, 264);//点击旗子
                    Thread.sleep(2000);
                    for (int j = 0; j < 10; j++) {
                        if (gamePublicFunction.judgeXYChange(2000) == false) {
                            //如果两秒内坐标不变动 开启自动战斗 退出循环
                            gamePublicFunction.automaticCombat(1);
                            break;
                        }
                        Thread.sleep(500);
                    }
                } else {
                    //没有旗子 退出循环
                    break;
                }
            }
            if (gamePublicFunction.outDungeons() == 0) {
                break;
            }

            Thread.sleep(500);
        }
    }

    private void sports() throws Exception {
        //门派竞技
        result = publicFunction.localFindPic(527, 359, 670, 417, "sportsJoin.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------sportsJoin>" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(419, 303, 527, 351, "sportsLevel.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------sportsLevel>" + result);
            publicFunction.rndTap(734, 457, 794, 484);
            Thread.sleep(500);
        }
    }

    //宋辽战场
    private void songLiao() throws Exception {

        AtFairy2.OpencvResult result1;
        result = publicFunction.localFindPic(535, 348, 677, 432, "SLDZ1.png");
        LtLog.i(publicFunction.getLineInfo() + "------------SLDZ1.png=" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------------SLDZ1.png=" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(500);
        }


        result = publicFunction.localFindPic(1019, 253, 1271, 279, "battleING1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------------battleING1.png=" + result);
            publicFunction.rndTap(result.x + 5, result.y - 20, result.x + 20, result.y);
            for (int i = 0; i < 10; i++) {
                if (gamePublicFunction.judgeXYChange(2000) == false) {
                    //2000 没有移动 退出
                    gamePublicFunction.automaticCombat(1);
                    break;
                }
            }
        }


        findResult = mFairy.findPic(1029, 244, 1275, 287, "battleING.png");
        LtLog.i(publicFunction.getLineInfo() + "------------battleING.png=" + result);
        if (findResult.sim > 0.8f) {
            mFairy.onTap(0.8f, findResult, "激战中", 5000);
            return;
        }

        findResult = mFairy.findPic(952, 279, 1265, 362, "sljia.png");
        if (findResult.sim > 0.8f) {

            switch ((int) (Math.random() * 2) + 1) {
                case 1:
                    mFairy.onTap(1054, 231, 1061, 241, "1", 1000);
                    break;
                case 2:
                    mFairy.onTap(1139, 238, 1146, 245, "2", 1000);
                    break;
                default:
                    mFairy.onTap(1220, 236, 1230, 242, "3", 1000);
                    break;
            }
            Thread.sleep(4000);
        }


        /*gamePublicFunction.automaticCombat(0);
        Thread.sleep(500);
        for (int i = 0; i < 60; i++) {
            findResult = mFairy.findPic(1029, 244, 1275, 287,  "battleING.png");

            result1 = publicFunction.localFindPicHLS(859, 564, 914, 615, "automaticCombat.png"+"|"+"automaticCombat-1.png");
            if (result.sim >= 0.8 && gamePublicFunction.outDungeons() == 1 && result1.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "------------automaticCombat.png=" + result1);
                LtLog.i(publicFunction.getLineInfo() + "------------battleING.png=" + result);
                publicFunction.rndTap(result.x, result.y - 20, result.x + 20, result.y);
                Thread.sleep(500);
                for (int j = 0; j < 10; j++) {
                    if (gamePublicFunction.judgeXYChange(2000) == false) {
                        gamePublicFunction.automaticCombat(1);
                        break;
                    }
                    Thread.sleep(500);
                }
            } else {
                LtLog.i(publicFunction.getLineInfo() + "------------battleING.png=" + result);
                if (result.sim < 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------激战中没有 退出.=");
                    break;
                }

                result = publicFunction.localFindPic(1156, 49, 1240, 124, "song.png"+"|"+"liao.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------song.png|liao.png=" + result);
                    break;
                }
            }

            Thread.sleep(300);
        }*/
    }

    private void HSLJ() throws Exception {
        //华山论剑
//        goToHS.png
        result = publicFunction.localFindPic(495, 428, 704, 478, "goToHS.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------------goToHS.png=" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(500);
        }

        result = publicFunction.localToValueFindPic(1119, 0, 1229, 26, 240, 255, 0, "HSLJ1.png");
        if (result.sim >= 0.75) {
            LtLog.i(publicFunction.getLineInfo() + "------------HSLJ1.png=" + result);
            gamePublicFunction.automaticCombat(1);//开启战斗
        }

        findResult = mFairy.findPic("hslj2.png");
        if (findResult.sim > 0.8f) {
            gamePublicFunction.automaticCombat(1);//开启战斗
        }
    }

    private void zlqj() throws Exception {
        //珍珑棋局
        result = publicFunction.localFindPic(466, 368, 721, 415, "challenge2.png");

        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------------challenge2.png=" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(500);
        }

    }

    private void tongMeal() throws Exception {
        result = publicFunction.localFindPic(500, 362, 687, 417, "tongMealChallenge.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------tongMealChallenge>" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(531, 159, 738, 214, "tongMealSelect.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------tongMealSelect>" + result);
            switch (TaskMain.tongMeal) {
                case 1:
                    publicFunction.rndTap(428, 285, 462, 322);
                    break;
                case 2:
                    publicFunction.rndTap(617, 286, 656, 316);
                    break;
                case 3:
                    publicFunction.rndTap(428, 285, 462, 322);
                    break;
            }
            Thread.sleep(2000);
            publicFunction.rndTap(599, 496, 653, 514);
            Thread.sleep(2000);
        }

    }

    private void tongGuard() throws Exception {
        //帮会守卫
        result = publicFunction.localFindPic(515, 363, 671, 411, "tongGuard1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------tongGuard1>" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(510, 364, 632, 413, "tongGuard2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------tongGuard2>" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(563, 15, 749, 75, "tongGuard3.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------tongGuard3>" + result);
            publicFunction.rndTap(1210, 67, 1221, 82);
            Thread.sleep(500);
        }
    }

    private void tongPractice() throws Exception {
        //首次进入
        gamePublicFunction.exitTeam();//退出队伍
        LtLog.i(publicFunction.getLineInfo() + "-------------------首次进入---帮会练功------mState>" + mState);
        gamePublicFunction.openActivity(2);//打开限时活动
        while (mFairy.condit()) {
            if (Math.abs(random.nextInt()) % 100 >= 70) {
                LtLog.i(publicFunction.getLineInfo() + "-------tongPractice>" + "__timex=" + timex);
            }
            int state = lookUpTask(list);//查看任务
            LtLog.i(publicFunction.getLineInfo() + "-------tongPractice>" + "state=" + state);
            Thread.sleep(2000);
            result = publicFunction.localFindPic(502, 361, 669, 413, "tongPracticeStart.png");
            LtLog.i(publicFunction.getLineInfo() + "------------tongPracticeStart=" + result);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------tongPracticeStart=" + result);
                publicFunction.rndTapWH(result.x, result.y, 50, 20);
                Thread.sleep(500);
            }
            if (publicFunction.getMinuteNumber() >= 1190) {
                //19:59 退出帮会练功，做8点活动
                state = 1;
            }
            if (state == 2) {
                gamePublicFunction.closeWindow();
                return;
            } else if (state == 1) {
                TaskMain.taskList.remove(list.get(0));
                gamePublicFunction.closeWindow();
                return;
            }
            result = publicFunction.localFindPic(653, 1, 1104, 96, "activity.png");
            if (result.sim > 0.8) {
                //没有活动按钮 不识别坐标
                LtLog.i(publicFunction.getLineInfo() + "---------activity.size()>" + result);
                xy = publicFunction.getXY(1118, 31, 1198, 48, 200, 255, 0);
//                xy=ocrNumber.getNumber(1120,29,74,14,new Scalar(200,200,200),new Scalar(255,255,255));
            }
            if (xy != xy1 && xy > 0) {
                LtLog.i(publicFunction.getLineInfo() + "-------xy>" + xy);
                xy1 = xy;
                time = System.currentTimeMillis() / 1000;
            }
            timex = System.currentTimeMillis() / 1000 - time;
            int vacancyNumber = 0;
            result = publicFunction.localFindPic(617, 13, 702, 71, "team.png");
            if (result.sim >= 0.8) {
                //如果在组队界面

                //查看当前有几个空位
                for (int i = 0; i < 4; i++) {
//                result = publicFunction.localToValueFindPic(1000 - (i * 205), 292, 1124 - (i * 205), 418, 90, 255, 1, "vacancy.png");
                    result = publicFunction.localFindPic(1000 - (i * 205), 292, 1124 - (i * 205), 418, "vacancy.png");
                    if (result.sim >= 0.75) {
                        LtLog.i(publicFunction.getLineInfo() + "-------vacancy>" + result);
                        vacancyNumber = vacancyNumber + 1;
                    }
                }
                LtLog.i(publicFunction.getLineInfo() + "-------当前队伍空位>" + vacancyNumber);
                gamePublicFunction.tirenAndAgree(); //踢离线  同意申请
                if (vacancyNumber <= 3) {
                    gamePublicFunction.closeWindow();
                    gamePublicFunction.openActivity(2);
                } else if (vacancyNumber == 4) {
                    gamePublicFunction.tirenAndAgree(); //踢离线  同意申请
                }

                if (timex >= 60 && vacancyNumber == 4) {
                    gamePublicFunction.exitTeam();
                }
            } else {
                //如果不在组队界面
                if (timex >= 15) {
                    gamePublicFunction.exitTeam();
                    gamePublicFunction.openActivity(2);//打开限时活动
                    time = System.currentTimeMillis() / 1000;
                }
            }
            LtLog.i(publicFunction.getLineInfo() + "------------tongPracticeStart=" + result);
            result = publicFunction.localFindPic(502, 361, 669, 413, "tongPracticeStart.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------tongPracticeStart=" + result);
                publicFunction.rndTapWH(result.x, result.y, 50, 20);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(412, 299, 571, 359, "tongPracticeRequired.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------tongPracticeRequired=" + result);
                publicFunction.rndTap(739, 460, 796, 488);//点击确认
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(653, 1, 1104, 96, "activity.png");
            if (result.sim >= 0.8) {
                this.tongPractice = this.tongPractice + 1;
                LtLog.i(publicFunction.getLineInfo() + "------------activity=" + result);
                result = publicFunction.localFindManyPic(24, 193, 81, 231, new String[]{"task1.png", "task2.png"});
                if (result.sim >= 0.8 && this.tongPractice >= 3) {
                    this.tongPractice = 0;
                    LtLog.i(publicFunction.getLineInfo() + "------------task1=" + result);
                    publicFunction.rndTap(143, 202, 182, 224);//点击组队
                    Thread.sleep(2000);
                }
                result = publicFunction.localFindPic(805, 622, 997, 686, "createTeam.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------createTeam=" + result);
                    gamePublicFunction.closeWindow();
                    Thread.sleep(1000);
                    gamePublicFunction.openActivity(2);
                }
            }
        }
    }

    private void tongYB() throws Exception {
        AtFairy2.OpencvResult result1;
        gamePublicFunction.switchTaskOrTeam("task");

        result = publicFunction.localFindPic(522, 356, 672, 419, "tongYB1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "帮会运镖" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 50, result.y + 21);
            Thread.sleep(1500);
        }


        findResult = mFairy.findPic(767, 608, 985, 686, "kaiqi.png");
        if (findResult.sim >= 0.8) {
            time = System.currentTimeMillis() / 1000;
            LtLog.i(publicFunction.getLineInfo() + "开启运镖" + findResult);

            result1 = publicFunction.localFindPic(361, 520, 406, 547, "rndYB.png");
            if (result1.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------rndYB>" + result1);
                publicFunction.rndTap(405, 642, 468, 657);
                Thread.sleep(1000);
            } else {
                publicFunction.rndTap(1020, 456, 1043, 464);//点领取运镖
                Thread.sleep(2000);

                result1 = publicFunction.localFindPic(423, 283, 629, 402, "notToken.png");
                if (result1.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "没有运镖令牌>" + result1);
                    publicFunction.rndTap(486, 465, 525, 477);//点取消
                    Thread.sleep(2000);
                }

                result1 = publicFunction.localFindPic(376, 281, 605, 405, "useYB.png");
                if (result1.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "使用运镖领" + result1);
                    publicFunction.rndTap(746, 459, 817, 483);//点确认
                    Thread.sleep(2000);
                }
            }
            publicFunction.rndTap(findResult.x, findResult.y, findResult.x + 50, findResult.y + 21);
            Thread.sleep(1000);
        }

        findResult = mFairy.findPic(670, 428, 875, 568, "kaiqi1.png");
        if (findResult.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "开启运镖" + result);
            publicFunction.rndTap(findResult.x, findResult.y, findResult.x + 50, findResult.y + 21);
            time = System.currentTimeMillis() / 1000;
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(918, 487, 1062, 530, "complete.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "运镖完成" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 50, result.y + 21);
            Thread.sleep(2000);
            gamePublicFunction.openActivity(2);
        }

    }

    private void tongAnswerAndDice() throws Exception {
        //帮会答题 掷骰子
        result = publicFunction.localFindPic(1097, 295, 1165, 355, "tongAnswer.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------tongAnswer>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 44, result.y + 21);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(546, 81, 742, 138, "XJLAnswer.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------XJLAnswer>" + result);
            publicFunction.rndTap(402, 297, 463, 320);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(437, 280, 615, 400, "answerComplete.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------answerComplete>" + result);
            publicFunction.rndTap(933, 102, 956, 123);//关闭窗口
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(1085, 298, 1176, 355, "throw.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------throw>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(588, 546, 684, 604, "throw1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------throw1>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(500);
        }
    }

    private void tongAssassin() throws Exception {
        result = publicFunction.localFindPic(486, 354, 689, 422, "tongAssassin1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------tongAssassin1.png>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 44, result.y + 21);
            Thread.sleep(500);
        }
    }

    public int lookUpTask(List<String> list) throws Exception {
        //查看执行任务 定时活动 状态  未到参加时间 参加 已结束 已完成
        //已完成 已结束 删除 taskList  并返回 return 1;
        // 参加 不做操作 return 0;
        // 未到参加时间  return 2 ;
        List<String> list1 = list;
        int second = 0;
        result = publicFunction.localFindPic(582, 8, 721, 80, "activitiesWindow.png" + "|" + "activitiesWindow1.png" + "|" + "activitiesWindow2.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "不在活动界面 ");
            //如不在活动界面
            return 0;
        }

        LtLog.i(publicFunction.getLineInfo() + "查看活动 " + list1.toString());
        Thread.sleep(1000);

        long time = System.currentTimeMillis() / 1000, timex = 0;
        while (mFairy.condit()) {
            int slidingLookup = 0;

            LtLog.i(publicFunction.getLineInfo() + "查找活动中 " + list1.size() + ",timex=" + timex);
            for (int i = 0; i < list1.size(); i++) {

                result = publicFunction.localFindPic(237, 126, 700, 455, list1.get(i));
                LtLog.i(publicFunction.getLineInfo() + "活动sim:" + list1.get(i) + "=" + result);
                if (result.sim >= 0.8) {

                    //找到任务
                    LtLog.i(publicFunction.getLineInfo() + "找到任务--list1.get(i)>" + list1.get(i) + "=" + result);
                    AtFairy2.OpencvResult result1;
                    result1 = publicFunction.localFindPic(result.x, result.y, result.x + 273, result.y + 127, "attend.png");
                    if (result1.sim >= 0.8) {
                        //点击参加
                        LtLog.i(publicFunction.getLineInfo() + "点击参加" + result);
                        publicFunction.rndTap(result1.x, result1.y, result1.x + 44, result1.y + 21);
                        Thread.sleep(2000);
                        return 3;
                    } else {
                        result1 = publicFunction.localFindPic(result.x, result.y, result.x + 273, result.y + 127, "complete1.png" + "|" + "complete1-1.png");
                        if (result1.sim >= 0.8) {
                            LtLog.i(publicFunction.getLineInfo() + "已完成" + result);
                            //已完成
                            return 1;
                        }
                        result1 = publicFunction.localFindPic(result.x, result.y, result.x + 273, result.y + 127, "end.png");
                        if (result1.sim >= 0.8) {
                            LtLog.i(publicFunction.getLineInfo() + "---------end>" + result);
                            //结束
                            long currTime = publicFunction.getMinuteNumber();
                            if (list1.get(0) == "territory.png" && currTime < 1260) {
                                return 2;
                            }
                            return 1;
                        }
                        result1 = publicFunction.localFindPic(result.x, result.y, result.x + 273, result.y + 127, "opentime.png");
                        if (result1.sim >= 0.8) {
                            LtLog.i(publicFunction.getLineInfo() + "---------end>" + result);
                            //不在开启时间
                            return 2;
                        }
                        result1 = publicFunction.localFindPic(result.x, result.y, result.x + 273, result.y + 127, "lackOfRank.png");
                        if (result1.sim >= 0.8) {
                            LtLog.i(publicFunction.getLineInfo() + "---------lackOfRank>" + result);
                            //等级不够
                            TaskMain.taskList.remove(list.get(0));
                            return 2;
                        }
                    }
                } else {

                    if (list1.get(0) == "tongAssassin.png" || list1.get(0) == "tongMeal.png") {
                        result = publicFunction.localFindPic(237, 126, 670, 455, "tongMeal.png");
                        if (result.sim >= 0.8) {
                            LtLog.i(publicFunction.getLineInfo() + "-----------------发现御膳比拼-----------tongMeal>" + list1.get(i) + "=" + result);
                            TaskMain.taskList.remove(list.get(0));
                            return 2;
                        }
                        result = publicFunction.localFindPic(237, 126, 670, 455, "tongAssassin.png");
                        if (result.sim >= 0.8) {
                            LtLog.i(publicFunction.getLineInfo() + "-----------------帮会杀星-----------tongMeal>" + list1.get(i) + "=" + result);
                            TaskMain.taskList.remove(list.get(0));
                            return 2;
                        }

                    }
                }


            }
            result = publicFunction.localFindPic(582, 8, 721, 80, "activitiesWindow.png|activitiesWindow1.png|activitiesWindow2.png");
            AtFairy2.OpencvResult result1 = publicFunction.localFindPicHLS(1033, 548, 1161, 643, "push.png");
            if (result.sim < 0.8 && result1.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------------------不在活动窗口---------result=" + result);
                LtLog.i(publicFunction.getLineInfo() + "---------------------不在活动窗口---------result1=" + result1);
                return 0;
            }

            if (slidingLookup == 0) {
                if (second >= 3) {
                    publicFunction.RanSwipe(232, 187, 717, 445, 0, 500);
                } else {
                    publicFunction.RanSwipe(232, 187, 717, 445, 2, 500);
                }
                Thread.sleep(1000);
                second = second + 1;
                if (second >= 7) {
                    second = 0;
                }
                LtLog.i(publicFunction.getLineInfo() + "---------swipe=");
            }

            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 300) {
                LtLog.i(publicFunction.getLineInfo() + "---------长时间未找到=" + list1.get(0) + "=活动");
                list1.remove(0);
                if (list1.size() == 0) {
                    return 1;
                }
                break;
            }
        }
        return 0;
    }

    private int lookUpTaskbar() throws Exception {
        //查看任务栏
        String[] currentTask;
        int[] range;

        if (list.get(0) == null) {
            return 0;
        }

        switch (list.get(0)) {
            case "tongYB.png":

                range = new int[]{87, 277, 135, 447};

                for (int i = 0; i < 10; i++) {
                    result = publicFunction.localOptimalFindPic(range[0], range[1], range[2], range[3], 140, 255.0, 0, "tongYB2.png" + "|" + "tongYB3.png");
                    LtLog.i(publicFunction.getLineInfo() + result);
                    if (result.sim >= 0.75) {
                        LtLog.i(publicFunction.getLineInfo() + "-------everydayTask>" + result);
                        time = System.currentTimeMillis() / 1000;
                        publicFunction.rndTap(result.x, result.y, result.x + 138, result.y + 20);
                        for (int j = 0; j < 11; j++) {
                            Thread.sleep(500);
                        }
                        break;
                    }
                    Thread.sleep(300);
                }
                return 0;
            case "bhzbrw.png":
                currentTask = new String[]{"bz3.png"};
                range = new int[]{3, 227, 56, 464};
                break;
            default:
                return 0;
        }

        Thread.sleep(1000);

        LtLog.i(mFairy.getLineInfo("发呆时间过长,滑动找任务"));
        for (int i = 0; i < 10; i++) {

            findResult = mFairy.findPic(range[0], range[1], range[2], range[3], currentTask);
            if (findResult.sim > 0.75f) {
                mFairy.onTap(0.75f, findResult, "找到任务", 5000);
                return 1;
            } else {
                if (i == 0) {
                    for (int j = 0; j < 5; j++) {
                        mFairy.ranSwipe(100, 253, 100, 439, 1000, 1000);
                    }
                    Thread.sleep(1000);
                } else {
                    mFairy.ranSwipe(100, 439, 100, 253, 1000, 2000);
                }
            }
        }

        return 2;
    }

    private boolean lookBox() throws Exception {

        gamePublicFunction.openMap("current");
        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPicHLS(158, 87, 739, 667, "box.png");
            if (result.sim >= 0.8) {
                mFairy.tap(result.x + 12, result.y + 9);
                Thread.sleep(500);
                gamePublicFunction.closeWindow();
                return true;
            }
            Thread.sleep(500);

        }
        gamePublicFunction.closeWindow();

        return false;
    }

    //家园
    public void home() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "-------home>");
        AtFairy2.OpencvResult result;
        gamePublicFunction.switchSkillOrSet("set");
        result = publicFunction.localFindPic(936, 170, 1271, 704, "home.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------home.png>" + result);
            publicFunction.rndTapWH(result.x, result.y, 23, 42);
            Thread.sleep(500);
        } else {
            gamePublicFunction.switchSkillOrSet("skill");
            return;
        }
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(173, 201, 305, 351, "geomantic.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------geomantic.png>" + result);
                break;
            }
            Thread.sleep(300);
        }
        if (result.sim < 0.8) {
            gamePublicFunction.closeWindow();
            return;
        }
//        result = publicFunction.localFindPic(410, 212, 1139, 629, "harvest.png");
//        if (result.sim >= 0.8) {
//            LtLog.i(publicFunction.getLineInfo() + "-------harvest.png>" + result);
//            publicFunction.rndTapWH(result.x, result.y, 55, 15);
//            Thread.sleep(500);
//        } else {
//            gamePublicFunction.closeWindow();
//            return;
//        }
        boolean start = false;
        if (cultivateProductionTime != 0) {
            //农田
            LtLog.i(publicFunction.getLineInfo() + "-------农田>");
            start = moveHarvest(1);
            if (start) {
                production(1, cultivateProductionTime, cultivate);
            }

        }
        if (rearProductionTime != 0) {
            //兽栏
            LtLog.i(publicFunction.getLineInfo() + "-------兽栏>");
            openHome();
            start = moveHarvest(2);
            if (start) {
                production(2, rearProductionTime, rear);
            }
        }
        if (cultureProductionTime != 0) {
            //池塘
            LtLog.i(publicFunction.getLineInfo() + "-------池塘>");
            openHome();
            start = moveHarvest(3);
            if (start) {
                production(3, cultureProductionTime, culture);
            }
        }

        for (int i = 0; i < 30; i++) {
            result = publicFunction.localFindPic(1100, 190, 1239, 308, "outDungeons2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------outDungeons2.png>" + result);
                publicFunction.rndTapWH(result.x, result.y, 39, 18);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(702, 413, 843, 534, "determine.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------determine.png>" + result);
                publicFunction.rndTapWH(result.x, result.y, 41, 21);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(1119, 2, 1219, 27, "luoYang.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------luoYang.png>" + result);
                break;
            }
            result = publicFunction.localFindPic(1116, 0, 1280, 99, "return.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------return.png>" + result);
                publicFunction.rndTapWH(result.x, result.y, 73, 19);
                Thread.sleep(500);
            }
            gamePublicFunction.closeWindow();
            Thread.sleep(3000);
        }
        gamePublicFunction.switchSkillOrSet("skill");

    }

    private boolean moveHarvest(int product) throws Exception {
        //product产物类别
        boolean start = true;//能否收获或者种植
        int mProduct = product;
        int[][] position = {{862, 245, 893, 275}, {1054, 308, 1087, 335}, {457, 540, 477, 570}};
        int[][] yesOrNot = {{794, 285, 875, 361}, {963, 332, 1074, 423}, {371, 562, 474, 658}};
        int index = 0;
        for (int i = 0; i < 60; i++) {
            result = publicFunction.localFindPic(173, 201, 305, 351, "geomantic.png");
            AtFairy2.OpencvResult result1 = publicFunction.localFindPic(yesOrNot[mProduct - 1][0], yesOrNot[mProduct - 1][1], yesOrNot[mProduct - 1][2], yesOrNot[mProduct - 1][3], "hourglass.png");
            if (result1.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------------正在生产中----------hourglass.png>" + result1);
                return false;
            }
            if (result.sim >= 0.8 && result1.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------geomantic.png>" + result);
                publicFunction.rndTap(position[mProduct - 1][0], position[mProduct - 1][1], position[mProduct - 1][2], position[mProduct - 1][3]);
                Thread.sleep(5000);
            }

            result = publicFunction.localFindPic(545, 330, 690, 453, "home1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------home1.png>" + result);
                publicFunction.rndTapWH(result.x, result.y, 45, 23);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(686, 621, 819, 720, "home2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------home2.png>" + result);
                publicFunction.rndTapWH(result.x, result.y, 33, 42);
                Thread.sleep(500);
            }
//            result = publicFunction.localFindPic(781, 587, 927, 708, "home3.png");
//            if (result.sim >= 0.8) {
//                LtLog.i(publicFunction.getLineInfo() + "-------home3.png>" + result);
//                break;
//            }
            result = publicFunction.localFindPic(364, 127, 505, 166, "all.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------all.png>" + "==" + result);
                break;
            }
            result = publicFunction.localFindPic(688, 632, 816, 720, "harvest1.png");
            LtLog.i(publicFunction.getLineInfo() + "-------harvest1.png>" + "==" + result);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------harvest1.png>" + "==" + result);
                publicFunction.rndTapWH(result.x, result.y, 28, 31);
                Thread.sleep(500);
            }

            result = publicFunction.localFindPic(747, 475, 890, 596, "harvest2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------harvest2.png>" + "==" + result);
                publicFunction.rndTapWH(result.x, result.y, 43, 21);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(355, 126, 496, 166, "hour.png|hour1.png");
            LtLog.i(publicFunction.getLineInfo() + "-------hour.png>" + "==" + result);
            if (result.sim >= 0.75) {
                publicFunction.rndTap(403, 132, 453, 161);
                Thread.sleep(2000);
                publicFunction.rndTap(405, 182, 457, 199);
                Thread.sleep(1000);
            }
            Thread.sleep(1000);
        }
        return start;
    }

    private void production(int product, int productTime, int productType) throws Exception {
        //product产物类别 1农田 2 兽栏 3池塘  ，productTime生产时间 ，productType产物类型
        int mProduct = product;
        int mProductTime = productTime;
        int mProductType = productType;
        boolean selectStart = false;
        String pic = null;
        for (int i = 0; i < 60; i++) {
            result = publicFunction.localFindPic(340, 86, 519, 206, "all.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------all.png>" + "==" + result);
                publicFunction.rndTapWH(result.x, result.y, 79, 20);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(411, 217, 464, 439, "hour.png|hour1.png");
            LtLog.i(publicFunction.getLineInfo() + "-------hour.png>" + "==" + result);
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "-------hour.png>" + "==" + result);
                publicFunction.rndTap(383, 189 + (mProductTime * 46), 476, 204 + (mProductTime * 46));
                Thread.sleep(1000);
            }

            result = publicFunction.localFindPic(355, 126, 496, 166, "hour.png|hour1.png");
            LtLog.i(publicFunction.getLineInfo() + "-------hour.png>" + "==" + result);
            if (result.sim >= 0.75) {
                selectStart = true;
                LtLog.i(publicFunction.getLineInfo() + "-------hour.png>" + "==" + result + ",,selectStart=" + selectStart);
            }
            if (mProduct == 1 && selectStart) {
                pic = "cultivate" + Integer.toString(mProductType) + ".png";
                LtLog.i(publicFunction.getLineInfo() + "------->" + pic);
            }
            if (mProduct == 2 && selectStart) {
                pic = "rear" + Integer.toString(mProductType) + ".png";
                LtLog.i(publicFunction.getLineInfo() + "------->" + pic);
            }
            if (mProduct == 3 && selectStart) {
                pic = "culture" + Integer.toString(mProductType) + ".png";
                LtLog.i(publicFunction.getLineInfo() + "------->" + pic);
            }
            if (pic != null) {
                result = publicFunction.localFindPic(233, 186, 339, 616, pic);
                LtLog.i(publicFunction.getLineInfo() + "------->" + pic + "==" + result);
                if (result.sim >= 0.8) {
                    publicFunction.rndTapWH(result.x, result.y, 40, 19);
                    Thread.sleep(2000);
                    publicFunction.rndTap(839, 636, 881, 659);//点击生产
                    Thread.sleep(1000);
                }
            }

            result = publicFunction.localFindPic(787, 279, 959, 400, "notHome.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------等级不足-------------------notHome>" + "==" + result);
                gamePublicFunction.closeWindow();

            }
            result = publicFunction.localFindPic(1116, 0, 1280, 99, "return.png");
            if (result.sim >= 0.8) {
                publicFunction.rndTap(623, 661, 644, 692);//点求助
                Thread.sleep(2000);
                publicFunction.rndTap(606, 438, 662, 454);//点世界频道
                Thread.sleep(2000);//
                publicFunction.rndTapWH(result.x, result.y, 73, 19);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(936, 170, 1271, 704, "home.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------home>" + "==" + result);
                return;
            }
            Thread.sleep(1000);
        }

    }

    private void openHome() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "-------openHome>");
        for (int i = 0; i < 5; i++) {
            gamePublicFunction.switchSkillOrSet("set");
            result = publicFunction.localFindPic(936, 170, 1271, 704, "home.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------home.png>" + result);
                publicFunction.rndTapWH(result.x, result.y, 23, 42);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(173, 201, 305, 351, "geomantic.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------geomantic.png>" + result);
                break;
            }
        }
    }

    /**
     * 家园设置
     *
     * @return
     * @throws JSONException
     */
    public boolean getUserHomeSet() throws JSONException {
        boolean homeStart = false;
        // productionTime 生产时间 =1（1小时）=2（3小时） =3（6小时） =4（12小时） =5 （24小时）
        int cultivateProduction;
        int rearProduction;
        int cultureProduction;
        if (!TaskMain.optionJson.get("cultivate").equals("0") && !TaskMain.optionJson.get("cultivateProduction").equals("0")) {
            cultivate = Integer.parseInt(String.valueOf(TaskMain.optionJson.get("cultivate")));
            cultivateProduction = Integer.parseInt(String.valueOf(TaskMain.optionJson.get("cultivateProduction")));
            LtLog.i(publicFunction.getLineInfo() + "-------cultivate>" + cultivate + ",cultivateProduction=" + cultivateProduction);
            // cultivate 1 松树 2 紫竹 3 檀香树 4 南洋杉
            if (cultivate == 1) {
                cultivateProductionTime = cultivateProduction;
            }
            if (cultivate == 2) {
                cultivateProductionTime = cultivateProduction + 1;
            }
            if (cultivate == 3 || cultivate == 4) {
                cultivateProductionTime = cultivateProduction + 2;
            }
            LtLog.i(publicFunction.getLineInfo() + "-------cultivate>" + cultivate + ",cultivateProduction=" + cultivateProduction + ",cultivateProductionTime=" + cultivateProductionTime);
            homeStart = true;
        }
        if (!TaskMain.optionJson.get("rear").equals("0") && !TaskMain.optionJson.get("rearProduction").equals("0")) {
            rear = Integer.parseInt(String.valueOf(TaskMain.optionJson.get("rear")));
            rearProduction = Integer.parseInt(String.valueOf(TaskMain.optionJson.get("rearProduction")));
            LtLog.i(publicFunction.getLineInfo() + "-------rear>" + rear + ",rearProduction=" + rearProduction);
            //rear  1绵羊 2 鸵鸟 3孔雀 4剑龙
            if (rear == 1) {
                rearProductionTime = rearProduction;
            }
            if (rear == 2) {
                rearProductionTime = rearProduction + 1;
            }
            if (rear == 3 || rear == 4) {
                rearProductionTime = rearProduction + 2;
            }
            LtLog.i(publicFunction.getLineInfo() + "-------rear>" + rear + ",rearProduction=" + rearProduction + ",cultivateProductionTime=" + rearProductionTime);
            homeStart = true;
        }
        if (!TaskMain.optionJson.get("culture").equals("0") && !TaskMain.optionJson.get("cultureProduction").equals("0")) {
            culture = Integer.parseInt(String.valueOf(TaskMain.optionJson.get("culture")));
            cultureProduction = Integer.parseInt(String.valueOf(TaskMain.optionJson.get("cultureProduction")));
            LtLog.i(publicFunction.getLineInfo() + "-------culture>" + culture + ",cultureProduction=" + cultureProduction);
            //culture 1白珍珠蚌 2黑珍珠蚌 3北海珠蚌 4南洋珠蚌
            if (culture == 1) {
                cultureProductionTime = cultureProduction;
            }
            if (culture == 2) {
                cultureProductionTime = cultureProduction + 1;
            }
            if (culture == 3 || culture == 4) {
                cultureProductionTime = cultureProduction + 2;
            }
            LtLog.i(publicFunction.getLineInfo() + "-------culture>" + culture + ",cultureProduction=" + cultureProduction + ",cultureProductionTime=" + cultureProductionTime);
            homeStart = true;
        }
        return homeStart;
    }

    public void timingActivityTask() throws Exception {
        //有时间把所有定时任务重构一下，天龙手游作为第一版opencv脚本，逻辑结构不好。
        String currSun = publicFunction.getCurrSun();
        int currtime = publicFunction.getMinuteNumber();
        LtLog.i(publicFunction.getLineInfo() + "---------currtime>" + currtime);
        LtLog.i(publicFunction.getLineInfo() + "---------currSun>" + currSun);
        if (currSun.equals("星期一") || currSun.equals("星期三")) {
            //19:15 - 19:25 征战天下
            if (currtime >= 1156 && currtime <= 1164 && TaskMain.taskList.indexOf("battle.png") >= 0) {
                mTaskList.add("battle.png");
                ActivityTask();
            }
        }
    }

    public void ActivityTask() throws Exception {
        AtFairy2.OpencvResult result;
        MatTime matTime = new MatTime(mFairy);
        long sleep = 0;
        gamePublicFunction.openActivity(2);
        MatTime matTime1 = new MatTime(mFairy);
        long sleep1 = 0;

        while (mFairy.condit()) {
            sleep = matTime.mMatTime(1029, 41, 220, 653, 0.9);
            LtLog.i(publicFunction.getLineInfo() + "---------sleep>" + sleep);
            if (sleep >= 30) {
                gamePublicFunction.openActivity(2);
                matTime.resetTime();
            }
            result = publicFunction.localFindPic(582, 8, 721, 80, "activitiesWindow.png|activitiesWindow1.png|activitiesWindow2.png");
            if (result.sim >= 0.8) {
                //如果在活动界面
                LtLog.i(publicFunction.getLineInfo() + "---------activitiesWindow>" + result + "__taskList.size1=" + mTaskList.size());
                if (mTaskList.size() > 0) {
                    mTaskList = gamePublicFunction.lookupTask(mTaskList);//查看任务
                }
                if (mTaskList.size() == 0) {
                    gamePublicFunction.closeWindow();
                    LtLog.i(publicFunction.getLineInfo() + "---------taskList.size()>" + "任务已完成");
                    return;
                }
                Thread.sleep(1000);
                LtLog.i(publicFunction.getLineInfo() + "---------activitiesWindow>" + result + "__taskList.size2=" + mTaskList.size() + "当前执行任务=" + mTaskList.get(0));
            }
            switch (mTaskList.get(0)) {
                case "battle.png":
                    if (gamePublicFunction.outDungeons() == 1) {
                        //在副本中，重置时间
                        LtLog.i(publicFunction.getLineInfo() + "---------outDungeons ing>");
                        matTime.resetTime();
                        sleep1 = matTime1.mMatTime(1029, 41, 220, 653, 0.9);
                        LtLog.i(publicFunction.getLineInfo() + "---------sleep1>" + sleep1);
                        if (sleep1 >= 20) {
                            //在副本中发呆20s 打开当前地图
                            gamePublicFunction.openMap("current");
                            matTime1.resetTime();
                        }
                    }
                    battleTask();
                    break;
            }
            Thread.sleep(1000);

        }


    }

    private void battleTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(476, 353, 722, 423, "battle1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------battle1>" + result);
            publicFunction.rndTapWH(result.x, result.y, 48, 23);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(1162, 621, 1260, 713, "battle2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------battle2>" + result);
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(534, 571, 740, 636, "expedition.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------expedition>" + result);
            publicFunction.rndTap(400, 171, 463, 201);
            Thread.sleep(1000);
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
            Thread.sleep(2000);
            publicFunction.rndTap(972, 85, 996, 108);//关闭窗口
            Thread.sleep(2000);
            publicFunction.rndTap(1185, 35, 1226, 45);//点返回
            Thread.sleep(2000);
        }

        result = publicFunction.localFindPic(595, 8, 781, 91, "battle3.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------battle3>" + result);
            publicFunction.rndTap(443, 253, 473, 281);//点击交战区域的位置
            Thread.sleep(1000);
            gamePublicFunction.closeWindow();
            Thread.sleep(5000);
            gamePublicFunction.automaticCombat(1);//开启自动
        }
    }


//      for (int i = 1; i <= 4; i++) {
//        if (judgeSelected("cultivate" + Integer.toString(i)) == true) {
//            optionJson.put("cultivate", Integer.toString(i));
//            LtLog.i(publicFunction.getLineInfo() + "------ cultivate ....." + i);
//            break;
//        }
//    }
//                for (int i = 1; i <3 ; i++) {
//        if(judgeSelected("cultivateProduction" + Integer.toString(i))){
//            optionJson.put("cultivateProduction", Integer.toString(i));
//            LtLog.i(publicFunction.getLineInfo() + "------ cultivateProduction ....." + i);
//            break;
//        }
//    }
//                for (int i = 1; i <= 4; i++) {
//        if (judgeSelected("rear" + Integer.toString(i)) == true) {
//            optionJson.put("rear", Integer.toString(i));
//            LtLog.i(publicFunction.getLineInfo() + "------ rear ....." + i);
//            break;
//        }
//    }
//                for (int i = 1; i <3 ; i++) {
//        if(judgeSelected("rearProduction" + Integer.toString(i))){
//            optionJson.put("rearProduction", Integer.toString(i));
//            LtLog.i(publicFunction.getLineInfo() + "------ rearProduction ....." + i);
//            break;
//        }
//    }
//
//                for (int i = 1; i <= 4; i++) {
//        if (judgeSelected("culture" + Integer.toString(i)) == true) {
//            optionJson.put("culture", Integer.toString(i));
//            LtLog.i(publicFunction.getLineInfo() + "------ culture ....." + i);
//            break;
//        }
//    }
//                for (int i = 1; i <3 ; i++) {
//        if(judgeSelected("cultureProduction" + Integer.toString(i))){
//            optionJson.put("cultureProduction", Integer.toString(i));
//            LtLog.i(publicFunction.getLineInfo() + "------ cultureProduction ....." + i);
//            break;
//        }
//    }


    /**
     * 子女读书设置
     *
     * @return
     * @throws JSONException
     */
    public boolean readBookSet() throws JSONException {
        if (!TaskMain.optionJson.get("white_book").equals("0")) {
            readbook = Integer.parseInt(String.valueOf(TaskMain.optionJson.get("white_book")));
            return true;
        }
        return false;
    }


    /**
     * 0
     * 子女读书
     */
    public void readBook() throws Exception {
        AtFairy2.OpencvResult result, result1, result2;

        result = publicFunction.localFindPic(75, 104, 153, 179, "nipple.png");
        result1 = publicFunction.localFindPic(75, 104, 153, 179, "redDot.png");
        LtLog.i(publicFunction.getLineInfo() + "------nipple = " + result + ",redDot=" + result1);
        if (result.sim >= 0.8 && result1.sim >= 0.8) {
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(3000);
        } else {
            return;
        }
        //子女安全感


        result = publicFunction.localFindPic(539, 11, 769, 73, "children.png");
        LtLog.i(publicFunction.getLineInfo() + "------children = " + result);
        if (result.sim >= 0.8) {
            for (int j = 0; j < 3; j++) {
                //点击喂奶，清洁，安抚
                mFairy.tap(179, 341 + j * 79);
                Thread.sleep(500);
            }
            mFairy.tap(171, 370);
            Thread.sleep(100);
            mFairy.tap(171, 451);
            Thread.sleep(100);
        } else {
            return;
        }
        //答疑
        result = publicFunction.localFindPic(284, 619, 314, 652, "redDot.png");
        LtLog.i(publicFunction.getLineInfo() + "------redDot = " + result);
        if (result.sim >= 0.8) {
            publicFunction.rndTap(result.x - 50, result.y + 5, result.x - 10, result.y + 20);
            Thread.sleep(100);
        }
        for (int i = 0; i < 30; i++) {
            result = publicFunction.localFindPic(622, 57, 733, 128, "answeringQuestion.png");
            LtLog.i(publicFunction.getLineInfo() + "------answeringQuestion = " + result);
            if (result.sim >= 0.8) {
                publicFunction.rndTap(797, 329, 973, 348);//点击第一个答案
                Thread.sleep(100);
            }
            if (i > 20 && result.sim >= 0.8) {
                publicFunction.rndTap(1073, 88, 1102, 107);//关闭答疑窗口
                Thread.sleep(100);
            } else if (i > 20) {
                break;
            }
            Thread.sleep(1000);
        }

        //读书
        for (int i = 0; i < 20; i++) {
            result = publicFunction.localFindPic(539, 11, 769, 73, "children.png");
            result1 = publicFunction.localFindPic(539, 11, 769, 73, "study.png|study1.png");
            result2 = publicFunction.localFindPic(603, 76, 712, 137, "readwrite.png");
            LtLog.i(publicFunction.getLineInfo() + "------children = " + result + ",study=" + result1 + "，readwrite=" + result2);
            if (result.sim >= 0.8) {
                publicFunction.rndTap(80, 222, 94, 251);//切换到授课
                Thread.sleep(5000);
            }
            if (result.sim < 0.8 && result1.sim < 0.8 && result2.sim < 0.8) {
                break;
            }
            result = publicFunction.localFindPic(925, 520, 1124, 611, "check.png");
            LtLog.i(publicFunction.getLineInfo() + "------check = " + result);
            if (result.sim >= 0.8) {
                publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
                Thread.sleep(1000);
            } else {
                result = publicFunction.localFindPic(352, 579, 436, 642, "book.png");
                LtLog.i(publicFunction.getLineInfo() + "------book = " + result);
                if (result.sim >= 0.8) {
                    publicFunction.rndTap(333, 552, 361, 577);//点加号
                    Thread.sleep(1000);
                }
                result = publicFunction.localFindPic(244, 416, 824, 496, "add.png");
                LtLog.i(publicFunction.getLineInfo() + "------add = " + result);
                if (result.sim >= 0.8) {
                    publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);//点加号
                    Thread.sleep(1000);
                }
            }
            result2 = publicFunction.localFindPic(603, 76, 712, 137, "readwrite.png");
            LtLog.i(publicFunction.getLineInfo() + "------readwrite = " + result);
            if (result2.sim >= 0.8) {
                int[][] listxy = {{284, 210}, {411, 208}, {546, 212}, {276, 355}};
                mFairy.tap(listxy[readbook - 1][0], listxy[readbook - 1][1]);//选择书籍
                Thread.sleep(1000);
                publicFunction.rndTap(859, 596, 927, 629);//点击读写
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(925, 520, 1124, 611, "check1.png");
            LtLog.i(publicFunction.getLineInfo() + "------check = " + result);
            if (result.sim >= 0.8) {
                break;
            }
            Thread.sleep(500);
        }


        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPic(173, 92, 309, 135, "dailyWork.png");
            LtLog.i(publicFunction.getLineInfo() + "------dailyWork = " + result);
            if (result.sim >= 0.8) {
                result = publicFunction.localFindPic(result.x + 91, result.y - 25, result.x + 127, result.y + 10, "redDot.png");
                LtLog.i(publicFunction.getLineInfo() + "------redDot = " + result);
                if (result.sim > 0.8) {
                    publicFunction.rndTap(result.x - 40, result.y + 10, result.x - 10, result.y + 15);
                    Thread.sleep(1000);
                }
            }
            result = publicFunction.localFindPic(923, 559, 1074, 623, "pick.png");
            LtLog.i(publicFunction.getLineInfo() + "------pick = " + result);
            if (result.sim >= 0.8) {
                publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
                Thread.sleep(1000);
            }

            result = publicFunction.localFindPic(626, 313, 986, 356, "submit1.png");
            LtLog.i(publicFunction.getLineInfo() + "------submit1 = " + result);
            if (result.sim >= 0.8) {
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(1000);
            } else {
                result = publicFunction.localFindPic(849, 540, 973, 601, "close.png");
                LtLog.i(publicFunction.getLineInfo() + "------close = " + result);
                if (result.sim >= 0.8) {
                    publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                    Thread.sleep(1000);
                }
            }
            result = publicFunction.localFindPic(669, 573, 830, 631, "submit2.png");
            LtLog.i(publicFunction.getLineInfo() + "------submit2 = " + result);
            if (result.sim >= 0.8) {
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(1000);
            }
            Thread.sleep(1000);
        }


        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(539, 11, 769, 73, "children.png");
            result1 = publicFunction.localFindPic(539, 11, 769, 73, "study1.png|study.png");
            if (result.sim >= 0.8 || result1.sim >= 0.8) {
                gamePublicFunction.closeWindow();
                Thread.sleep(3000);
            }
            result2 = publicFunction.localFindPic(603, 76, 712, 137, "readwrite.png");
            if (result2.sim >= 0.8) {
                publicFunction.rndTap(1096, 97, 1118, 116);//关闭
                Thread.sleep(1000);
            }
            LtLog.i(publicFunction.getLineInfo() + "------children = " + result + ",study=" + result1 + "，readwrite=" + result2);
            if (result.sim < 0.8 && result1.sim < 0.8 && result2.sim < 0.8) {
                return;
            }
            Thread.sleep(1000);
        }
    }


}
