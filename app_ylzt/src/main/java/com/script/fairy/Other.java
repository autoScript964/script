package com.script.fairy;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairy2;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-11-08.
 */

public class Other {
    private AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    private GamePublicFunction gamePublicFunction;

    public Other(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(ypFairy);
        gamePublicFunction = new GamePublicFunction(ypFairy);
    }

    public void OtherFunction() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "------OtherFunction->");
        //签到
        if (TaskMain.taskList.indexOf("Acer") > -1) {
            Acer();
            TaskMain.taskList.remove("Acer");
        }
        // 福神参拜
        if (TaskMain.taskList.indexOf("blessingGod") > -1) {
            tripodAndMusicianAndBlessingGod("blessingGod");
            TaskMain.taskList.remove("blessingGod");
        }
        //探访乐师
        if (TaskMain.taskList.indexOf("musician") > -1) {
            tripodAndMusicianAndBlessingGod("musician");
            TaskMain.taskList.remove("musician");
        }
        //镇国大鼎
        if (TaskMain.taskList.indexOf("tripod") > -1) {
            tripodAndMusicianAndBlessingGod("tripod");
            TaskMain.taskList.remove("tripod");
        }
        //出售装备
        if (TaskMain.taskList.indexOf("sell") > -1) {
            sellTask();
            TaskMain.taskList.remove("sell");
        }
        //清理道具
        if (TaskMain.taskList.indexOf("clear") > -1) {

            exploitTask();


            TaskMain.taskList.remove("clear");
        }
        //领取俸禄
        if (TaskMain.taskList.indexOf("salary") > -1) {
            salaryTask();
            TaskMain.taskList.remove("salary");
        }
        //免费找回
        if (TaskMain.taskList.indexOf("retrieve") > -1) {
            retrieveTask();
            TaskMain.taskList.remove("retrieve");
        }
        //许愿 和副将抽取
        if (TaskMain.taskList.indexOf("wish") > -1) {
            wishTask();
            TaskMain.taskList.remove("wish");
        }
    }

    // 福神参拜 //探访乐师 //镇国大鼎

    private void tripodAndMusicianAndBlessingGod(String taskName) throws Exception {
        AtFairy2.OpencvResult result;
        MatTime matTime = new MatTime(mFairy);
        long sleepTime = 0;
        result = publicFunction.localFindPic(1024, 3, 1274, 71, "mianCity.png");
        LtLog.i(publicFunction.getLineInfo() + "------mianCity->" + result);
        if (result.sim <= 0.75) {
            //不在王城中
            gamePublicFunction.goMianCity();
        }

        gamePublicFunction.openMap("current");
        int target_x = 0;
        int target_y = 0;
        String talk = "";
        switch (taskName) {
            case "blessingGod":
                target_x = 410;
                target_y = 259;
                talk = "blessingGod.png";
                break;
            case "musician":
                target_x = 418;
                target_y = 259;
                talk = "musician1.png|musician.png";
                break;
            case "tripod":
                target_x = 400;
                target_y = 240;
                talk = "tripod.png|tripod1.png";
                break;

        }
        for (int i = 0; i < 120; i++) {
            sleepTime = matTime.mMatTime(1119, 54, 71, 15, 0.8);
            LtLog.i(publicFunction.getLineInfo() + "------sleepTime->" + sleepTime);
            if (sleepTime >= 30) {
                gamePublicFunction.openMap("current");
                matTime.resetTime();
            }
            result = publicFunction.localFindPic(961, 606, 1167, 720, "nationalGeography.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------nationalGeography->" + result);
                mFairy.tap(target_x, target_y);
                Thread.sleep(1000);
                gamePublicFunction.closeWindow();
            }
            result = publicFunction.localFindPic(549, 397, 737, 503, talk);
            if (result.sim >= 0.74 && sleepTime > 5) {
                LtLog.i(publicFunction.getLineInfo() + "------" + talk + "->" + result);
                mFairy.tap(result.x + 29, result.y + 57);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(894, 619, 1250, 715, "reward6.png|reward2.png");
            if (result.sim >= 0.8 && sleepTime > 5) {
                LtLog.i(publicFunction.getLineInfo() + "------reward2->" + result);
                publicFunction.rndTapWH(result.x, result.y, 58, 27);
                Thread.sleep(3000);
                break;
            }
            Thread.sleep(1000);
        }
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(894, 619, 1250, 715, "reward6.png|reward2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------reward2->" + result);
                publicFunction.rndTap(880, 371, 932, 414);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(890, 0, 1020, 123, "activity.png");
            LtLog.i(publicFunction.getLineInfo() + "------activity->" + result);
            if (result.sim >= 0.8) {
                break;
            }
            Thread.sleep(1000);
        }


    }

    //许愿和副将抽取
    private void wishTask() throws Exception {
        AtFairy2.OpencvResult result;
        switchWindow();
        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPic(498, 170, 758, 289, "wish1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------wish1->" + result);
                publicFunction.rndTapWH(result.x, result.y, 32, 45);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(762, 484, 916, 555, "wish2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------wish2->" + result);
                publicFunction.rndTap(793, 565, 862, 595);
                Thread.sleep(1000);
            }
            Thread.sleep(1000);
        }

        for (int i = 0; i < 3; i++) {
            gamePublicFunction.closeWindow();
        }
        switchWindow();
        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPic(498, 170, 758, 289, "wish3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------wish3->" + result);
                publicFunction.rndTapWH(result.x, result.y, 32, 45);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(778, 528, 936, 655, "wish4.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------wish4->" + result);
                publicFunction.rndTapWH(result.x, result.y, 58, 27);
                Thread.sleep(1000);

            }
            result = publicFunction.localFindPic(306, 535, 433, 660, "determine4.png");
            if (result.sim >= 0.8) {
                publicFunction.rndTapWH(result.x, result.y, 27, 25);
                Thread.sleep(1000);
                break;
            }

            Thread.sleep(1000);

        }
        for (int i = 0; i < 3; i++) {
            gamePublicFunction.closeWindow();
        }
    }

    private void switchWindow() throws Exception {
        AtFairy2.OpencvResult result;

        for (int i = 0; i < 3; i++) {
            result = publicFunction.localFindPic(506, 13, 818, 177, "wish.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------wish->" + result);
                publicFunction.rndTapWH(result.x, result.y, 39, 31);
                Thread.sleep(1000);


            } else {

                publicFunction.rndTap(1021, 11, 1045, 28);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(498, 170, 758, 289, "wish3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------wish3->" + result);
                break;
            }

            Thread.sleep(1000);
        }

    }

    public void Acer() throws Exception {
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 6; i++) {
            result = publicFunction.localFindPic(494, 7, 1001, 172, "activity1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------activity1->" + result);
                publicFunction.rndTapWH(result.x, result.y - 10, 10, 10);
                Thread.sleep(1000);
            }

            result = publicFunction.localFindPic(149, 128, 265, 483, "welfare.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------welfare->" + result);
                publicFunction.rndTapWH(result.x, result.y, 20, 20);
                Thread.sleep(2000);
            }

            result = publicFunction.localFindPic(259, 256, 479, 334, "Acer.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------Acer->" + result);
                publicFunction.rndTapWH(result.x, result.y, 20, 20);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(789, 168, 1095, 335, "Acer1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------Acer1->" + result);
                publicFunction.rndTapWH(result.x, result.y, 20, 20);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(773, 325, 1086, 639, "reward1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------reward1->" + result);
                publicFunction.rndTapWH(result.x, result.y, 20, 20);
                Thread.sleep(1000);
            }


            Thread.sleep(1000);
        }
        for (int i = 0; i < 5; i++) {
            gamePublicFunction.closeWindow();
        }

    }

    //免费找回
    private void retrieveTask() throws Exception {
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 5; i++) {
            gamePublicFunction.openActivity(gamePublicFunction.openActivity_retrieve);
            result = publicFunction.localFindPic(592, 571, 787, 693, "retrieve.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------retrieve->" + result);
                publicFunction.rndTapWH(result.x, result.y, 95, 22);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(440, 268, 629, 389, "retrieve1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------retrieve1->" + result);
                publicFunction.rndTap(755, 461, 808, 485);//点确认
                Thread.sleep(1000);
                break;
            }
            Thread.sleep(1000);
        }
        for (int i = 0; i < 3; i++) {
            gamePublicFunction.closeWindow();
        }
    }

    //领取俸禄
    private void salaryTask() throws Exception {
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPic(890, 0, 1020, 123, "activity.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "------activity->" + result);
                publicFunction.rndTap(55, 38, 94, 67);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(0, 294, 158, 415, "salary.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------salary->" + result);
                publicFunction.rndTapWH(result.x, result.y, 59, 21);
                Thread.sleep(1000);
            }

            result = publicFunction.localFindPic(707, 529, 922, 658, "salary1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------salary1->" + result);
                publicFunction.rndTapWH(result.x, result.y, 115, 29);
                Thread.sleep(1000);
                break;
            }
            Thread.sleep(1000);
        }
        for (int i = 0; i < 3; i++) {
            gamePublicFunction.closeWindow();
        }
    }

    /**
     * 清理背包
     * @throws Exception
     */
    private void exploitTask() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "------exploitTask->");
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 3; i++) {
            openPackage();
            result = publicFunction.localFindPic(970, 602, 1142, 720, "sell.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------sell->" + result);
                publicFunction.rndTap(1167, 656, 1237, 677);//点整理
                Thread.sleep(500);
                publicFunction.rndTap(1176,207,1188,228);
                Thread.sleep(500);
                publicFunction.rndTap(1175,123,1189,160);
                Thread.sleep(1000);
                break;
            }
        }
        while (mFairy.condit()) {
            if (TaskMain.clear_string == "") {
                break;
            }
            result = publicFunction.localFindPic(735, 99, 1138, 505, TaskMain.clear_string);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------token->" + result);
                publicFunction.rndTapWH(result.x, result.y, 40, 42);
                Thread.sleep(2000);
            } else {
                result = publicFunction.localFindPic(970, 602, 1142, 720, "sell.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------sell->" + result);
                    publicFunction.RanSwipe(802, 156, 1085, 440, 3, 500);
                    Thread.sleep(1000);
                }else {
                    //如果不在背包界面 ，重新打开背包
                    openPackage();
                }
            }
            result = publicFunction.localFindPic(811, 431, 1013, 595, "exploit_use.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------exploit_use->" + result);
                publicFunction.rndTapWH(result.x, result.y, 102, 19);
                Thread.sleep(2000);
            } else {
                result = publicFunction.localFindPic(819, 588, 987, 681, "use3.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------use3->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 30, 19);
                    Thread.sleep(2000);
                }
            }
            result = publicFunction.localFindPic(751, 411, 877, 535, "determine.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------determine->" + result);
                publicFunction.rndTapWH(result.x, result.y, 26, 24);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(1048, 412, 1136, 495, "lock.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------lock->" + result);
                break;
            }
            Thread.sleep(100);
        }
        if (TaskMain.taskList.indexOf("clear6") > -1) {
            clear2();
            TaskMain.taskList.remove("clear6");
        }
        for (int i = 0; i < 3; i++) {
            gamePublicFunction.closeWindow();
        }
        Thread.sleep(1000);

    }

    /**
     * 清理副将
     * @throws Exception
     */
    private void clear2() throws Exception {
        AtFairy2.OpencvResult result,result1;
        while (mFairy.condit()) {
            result = publicFunction.localFindPic(1141,323,1225,415, "clear6.png");
            result1=publicFunction.localFindPic(723,100,840,200, "empty2.png");
            if (result.sim >= 0.8 && result1.sim<0.8) {
                //如果在副将栏(result) ，并且 包裹第一个位置不是空的(result1),点击包裹第一个位置。
                LtLog.i(publicFunction.getLineInfo() + "------clear6->" + result);
                publicFunction.rndTap(771,133,800,168);
            }else if (result.sim <= 0.8){
                //如果不在副将栏，点击副将
                publicFunction.rndTap(1172,352,1193,374);
            }else if(result1.sim>=0.8){
               //如果包裹第一个位置为空，表示没有副将，退出。
                break;
            }
            Thread.sleep(1000);

            result = publicFunction.localFindPic(811, 431, 1013, 595, "exploit_use.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------exploit_use->" + result);
                publicFunction.rndTapWH(result.x, result.y, 102, 19);
                Thread.sleep(2000);
            } else {
                result = publicFunction.localFindPic(819, 588, 987, 681, "use3.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------use3->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 30, 19);
                    Thread.sleep(2000);
                }
            }
            result = publicFunction.localFindPic(751, 411, 877, 535, "determine.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------determine->" + result);
                publicFunction.rndTapWH(result.x, result.y, 26, 24);
                Thread.sleep(2000);
            }


        }
    }

    //出售装备
    private void sellTask() throws Exception {
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 10; i++) {
            openPackage();
            result = publicFunction.localFindPic(970, 602, 1142, 720, "sell.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------sell->" + mFairy);
                publicFunction.rndTap(1167, 656, 1237, 677);//点整理
                Thread.sleep(1000);

                publicFunction.rndTapWH(result.x, result.y, 72, 33);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(297, 604, 455, 720, "determine3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------sell->" + mFairy);
                publicFunction.rndTap(417, 556, 424, 566);//黄色装备打钩
                Thread.sleep(1000);
                publicFunction.rndTap(517, 555, 524, 565);//绿色装备打钩
                Thread.sleep(1000);
                publicFunction.rndTapWH(result.x, result.y, 58, 24);//确认出售
                Thread.sleep(2000);
                result = publicFunction.localFindPic(584, 282, 728, 405, "commodity.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------commodity->" + mFairy);
                    publicFunction.rndTap(599, 461, 654, 480);
                    Thread.sleep(500);
                }
                for (int j = 0; j < 4; j++) {
                    gamePublicFunction.closeWindow();
                }
                break;
            }

            Thread.sleep(1000);
        }


    }

    public void openPackage() throws Exception {
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 3; i++) {
            result = publicFunction.localFindPic(970, 602, 1142, 720, "sell.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------sell->" + mFairy);
                break;
            }
            result = publicFunction.localFindPic(810, 618, 945, 720, "package.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------package->" + mFairy);
                publicFunction.rndTapWH(result.x, result.y, 35, 20);
                Thread.sleep(500);
            }
            Thread.sleep(1000);
        }
    }

    //坠星屿 日常任务
    public void ZXY_everday() throws Exception {
        AtFairy2.OpencvResult result;
        List<String> taskList = new ArrayList<>();
        taskList.add("ZXY_everday");
        MatTime matTime = new MatTime(mFairy);
        long sleepTime = 0;
        long maxSleepTime = 30;

        for (int i = 0; i < 10; i++) {
            gamePublicFunction.switchDaily();
            result = publicFunction.localFindPic(676, 5, 1273, 345, "ZXY.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------ZXY->" + result);
                break;
            }
            if (i > 8) {
                LtLog.i(publicFunction.getLineInfo() + "------not ZXY->");
                return;
            }
        }

        gamePublicFunction.openZXY();

        while (mFairy.condit()) {
            sleepTime = matTime.mMatTime(1119, 54, 71, 15, 0.8);
            LtLog.i(publicFunction.getLineInfo() + "------sleepTime->" + sleepTime);
            result = publicFunction.localFindPic(1068, 340, 1205, 471, "automaticCombat1-1.png");
            //如果在自动战斗中  发呆时间设置为60s
            if (result.sim >= 0.8) {
                maxSleepTime = 60;
            } else {
                maxSleepTime = 30;
            }
            if (sleepTime >= maxSleepTime) {
                gamePublicFunction.openZXY();
                matTime.resetTime();
            } else if (sleepTime >= 20) {
                result = publicFunction.localFindPic(1102, 0, 1267, 80, "ZXY1.png");
                if (result.sim > 0.75) {
                    LtLog.i(publicFunction.getLineInfo() + "------ZXY1->" + result);

                    result = publicFunction.localFindPic(0, 211, 115, 333, "everday1.png"+"|"+"everday.png");

                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------everday->" + result);
                        publicFunction.rndTapWH(result.x, result.y, 54, 22);
                        Thread.sleep(500);
                        matTime.time = matTime.time - 1;
                    }
                }
            }
            result = publicFunction.localFindPic(0, 78, 166, 197, "goto4.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【点击前往】");
                if (taskList.size() > 0) {
                    taskList = gamePublicFunction.lookupTaskZXY(taskList);
                    if (taskList.size() == 0) {
                        break;
                    }
                    Thread.sleep(3000);
                }
            }
            switch (taskList.get(0)) {
                case "ZXY_everday":
                    ZXY_subEverday();
                    break;
            }
            gamePublicFunction.Resurrection();//复活
            Thread.sleep(1000);
        }
        leave_ZXY();
    }

    private void leave_ZXY() throws Exception {
        AtFairy2.OpencvResult result;
        while (mFairy.condit()) {
            result = publicFunction.localFindPic(1181, 133, 1280, 246, "leave.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------leave >" + result);
                publicFunction.rndTapWH(result.x, result.y, 27, 13);
                Thread.sleep(3000);
            }
            result = publicFunction.localFindPic(566, 455, 715, 579, "dialogue.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------dialogue >" + result);
                publicFunction.rndTapWH(result.x, result.y, 49, 24);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(1003, 618, 1197, 720, "goHome.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------goHome >" + result);
                publicFunction.rndTapWH(result.x, result.y, 94, 23);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(890, 0, 1020, 123, "activity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------activity >" + result);
                break;
            }
        }
    }

    private void ZXY_subEverday() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(990, 616, 1139, 720, "transmit.png");
        LtLog.i(publicFunction.getLineInfo() + "------transmit->" + result);
        if (result.sim >= 0.8) {

            publicFunction.rndTapWH(result.x, result.y, 49, 25);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(1064, 324, 1261, 446, "everday_expel.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------everday_expel->" + result);
            publicFunction.rndTap(1129, 231, 1196, 269);
            Thread.sleep(1500);
            //满足此条件 取消后直接点左边日常
            result = publicFunction.localFindPic(0, 211, 115, 333, "everday1.png"+"|"+"everday.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------everday->" + result);
                publicFunction.rndTapWH(result.x, result.y, 54, 22);
                Thread.sleep(500);
            }
        } else {
            result = publicFunction.localFindPic(1000, 386, 1259, 710, "everday_union.png"+"|"+"everday_union1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------everday_union->" + result);
                for (int i = 0; i < 5; i++) {
                    //如果连续点击4次联盟日常还存在，有可能已经完成，点任其他地方取消对话
                    result = publicFunction.localFindPic(1000, 386, 1259, 710, "everday_union.png"+"|"+"everday_union1.png");
                    if (result.sim >= 0.8) {
                        publicFunction.rndTapWH(result.x, result.y, 93, 23);
                        Thread.sleep(500);
                        if (i == 4) {
                            publicFunction.rndTap(1009, 402, 1144, 448);
                            Thread.sleep(3000);
                            gamePublicFunction.openZXY();
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        result = publicFunction.localFindPic(985, 621, 1140, 720, "complete.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------complete->" + result);
            publicFunction.rndTapWH(result.x, result.y, 55, 25);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(980, 617, 1197, 720, "complete2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------complete2->" + result);
            publicFunction.rndTap(1009, 402, 1144, 448);
            Thread.sleep(500);
        }


        //下面为在坠星屿中才进行判断
        result = publicFunction.localFindPic(1102, 0, 1267, 80, "ZXY1.png");
        if (result.sim < 0.75) {
            LtLog.i(publicFunction.getLineInfo() + "------ZXY1->" + result);
            return;
        }

    }


}
