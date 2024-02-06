package com.script.fairy;

import com.example.publicfunctionlibrary.FunctionClass;
import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.opencv.core.Mat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class SingleTask {
    //单人任务
    private AtFairyImpl mFairy;
    String[] picArray;
    FindResult findResult;
    private AtFairy2.OpencvResult result;
    private GamePublicFunction gamePublicFunction;
    private long time1 = System.currentTimeMillis() / 1000, time1x = 0;
    private long time2 = System.currentTimeMillis() / 1000, time2x = 0;
    private int xy = 0, xy1 = 0;
    private long mTime_i = 0;
    private Random random = new Random(100);
    private PublicFunction publicFunction;
    private Mat mat, mat1 = null;
    private int alchem_index = 0;
    FunctionClass functionClass;

    public SingleTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
        gamePublicFunction = new GamePublicFunction(mFairy);
        functionClass = new FunctionClass(mFairy, mFairy.getContext());
    }

    public void wenquan() throws Exception {
        new TaskContent() {

            boolean exit;

            public void content_0() throws Exception {

                exit = false;

                boolean bool = gamePublicFunction.activity(4, "wenquan.png");
                if (bool == false) {
                    setTaskEnd();
                    return;
                }

                setTaskName(1);
            }

            public void inOperation() throws Exception {



                findResult = mFairy.findPic(500, 499, 784, 533, "qian.png");
                if (findResult.sim > 0.72f) {
                    err = 0;
                }

                findResult = mFairy.findPic(440, 174, 585, 296, "xunlu.png");
                if (findResult.sim > 0.8f) {
                    err = 0;
                }

                findResult = mFairy.findPic(650, 390, 650, 420, "qiehuan.png");
                if (findResult.sim > 0.8f) {
                    err = 0;
                }
            }

            public void content_1() throws Exception {

                findResult = mFairy.findPic("wenquan1.png");
                if (findResult.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, findResult, "进入温泉", 3000);
                }

                findResult = mFairy.findPic(486, 272, 879, 414, "wenquan5.png");
                mFairy.onTap(0.8f, findResult, 761, 466, 785, 480, "进入温泉", 3000);

                findResult = mFairy.findPic("jiayuan.png");
                if (findResult.sim > 0.8f) {

                    findResult = mFairy.findPic(577, 491, 654, 539, new String[]{"wenquan3.png", "wenquan4.png"});
                    if (findResult.sim > 0.8f) {
                        err = 0;
                        exit = true;
                        return;
                    }

                    if (exit) {
                        gamePublicFunction.closeWindow();

                        findResult = mFairy.findPic("jiayuan.png");
                        if (findResult.sim > 0.8f) {
                            mFairy.onTap(0.8f, findResult, "离开家园", 1000);
                            mFairy.onTap(759, 468, 784, 479, "", 1000);
                            setTaskEnd();
                            return;
                        }
                    }
                }

                findResult = mFairy.findPic("wenquan2.png");
                if (findResult.sim > 0.8f) {
                    mFairy.onTap(492, 465, 513, 474, "", 500);
                    setTaskEnd();
                    return;
                }

                Thread.sleep(1000);

                overtime(10, 0);
            }
        }.taskContent(mFairy, "家族温泉");
    }

    public int everydayTask() throws Exception {
        //日常单人任务

        Other other = new Other(mFairy);

        LtLog.i(publicFunction.getLineInfo() + "-------TaskMain.taskList>" + TaskMain.taskList);

        if (AtFairyConfig.getOption("wenquan").equals("1")) {
            //家园温泉
            wenquan();
        }

        if (TaskMain.taskList.size() == 0) {
            return 99;
        }

        //timex = 0;
        long time3 = System.currentTimeMillis() / 1000, time3x = 0;

        try {
            if (TaskMain.optionJson.get("preservationArticles").equals("1")) {
                other.preservationArticles();
                //保存物品
            }
            gamePublicFunction.closeWindow();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        gamePublicFunction.automaticCombat(0);

        gamePublicFunction.exitTeam();

        gamePublicFunction.exitHome();


        if (TaskMain.taskList.indexOf("post.png") >= 0) {
            //领邮件
            post();
            TaskMain.taskList.remove("post.png");
        }


        if (TaskMain.taskList.indexOf("BattleDonation.png") >= 0) {
            //征战捐献
            BattleDonation();
            TaskMain.taskList.remove("BattleDonation.png");
        }

        if (TaskMain.taskList.indexOf("desire.png") >= 0) {

            result = publicFunction.localOptimalFindPic(4, 264, 131, 454, 140, 255.0, 0, "desire8.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "许愿");
                publicFunction.rndTap(result.x, result.y, result.x + 138, result.y + 20);
                Thread.sleep(500);
                Collections.swap(TaskMain.taskList, 0, TaskMain.taskList.indexOf("desire.png"));
            } else {
                gamePublicFunction.automaticCombat(0);
                gamePublicFunction.openActivity(1);//打开日常活动
            }

        } else if (("linkTask.png").equals(TaskMain.taskList.get(0))) {
            result = publicFunction.localOptimalFindPic(5, 266, 61, 475, 140, 255.0, 0, "linkTask4.png");
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--linkTask4>" + result);
            if (result.sim >= 0.75) {
                publicFunction.rndTap(result.x, result.y, result.x + 138, result.y + 20);
                Thread.sleep(500);
            } else {
                gamePublicFunction.openActivity(1);//打开日常活动
            }
            gamePublicFunction.automaticCombat(0);
        } else {
            gamePublicFunction.automaticCombat(0);
            gamePublicFunction.openActivity(1);//打开日常活动
        }

        while (mFairy.condit()) {
            gamePublicFunction.follow(0);//取消跟随



            result = publicFunction.localFindPic(582, 8, 721, 80, "activitiesWindow.png" + "|" + "activitiesWindow1.png|activitiesWindow2.png");
            if (result.sim >= 0.8) {
                //如果在
                LtLog.i(publicFunction.getLineInfo() + "【活动界面】" + TaskMain.taskList.size());
                if (TaskMain.taskList.size() > 0) {
                    TaskMain.taskList = gamePublicFunction.lookupTask(TaskMain.taskList);//查看任务
                }
                //currentTask=taskList.get(0);
                Thread.sleep(1000);
            }




            if (TaskMain.taskList.size() == 0) {
                gamePublicFunction.closeWindow();
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--everydayTask--taskList.size()>" + "任务已完成");
                return 99;
            } else {
                LtLog.i(publicFunction.getLineInfo() + "当前执行任务=" + TaskMain.taskList.get(0));
            }


            findResult = mFairy.findPic(653,1,1104,96, "activity.png");
            if (findResult.sim > 0.8f) {
                //没有活动按钮 不识别坐标
                LtLog.i(publicFunction.getLineInfo() + "在主场景,识别坐标");

                mTime_i = mFairy.mMatTime(1126, 27, 61, 20, 0.8f);

                //xy = publicFunction.getXY(1118, 28, 1198, 48, 200, 255, 0);
            } else {
                mFairy.initMatTime();
                mTime_i = 0;
            }

            //LtLog.i(publicFunction.getLineInfo() + "坐标, SingleTask--xy>" + xy);

          /*  if (xy != xy1 && xy > 0) {
                xy1 = xy;
                time = System.currentTimeMillis() / 1000;
            } else if (xy == 0) {
                time = System.currentTimeMillis() / 1000;
            }*/


            //timex = System.currentTimeMillis() / 1000 - time;

            LtLog.i(publicFunction.getLineInfo() + "超时计时:" + mTime_i);
            if (mTime_i >= 35) {
                //30s坐标无变动，打开日常活动

                LtLog.i(publicFunction.getLineInfo() + "30s坐标无变动，打开日常活动");

                gamePublicFunction.automaticCombat(0);

                if (TaskMain.taskList.get(0) != "examination2.png" && TaskMain.taskList.get(0) != "heroExperience.png") {
                    //开启日常前 查看左边任务栏有没有任务，没有则打开
                    if (lookTaskbar() == 0) {
                        LtLog.i(publicFunction.getLineInfo() + "------lookTaskbar==0-->");
                        gamePublicFunction.openActivity(1);//打开日常活动
                    }
                } else {
                    gamePublicFunction.openActivity(1);//打开日常活动
                }

                Thread.sleep(1000);
                mFairy.initMatTime();
                mTime_i = 0;

            } else if (mTime_i >= 11 && TaskMain.taskList.get(0).equals("examination2.png") == false && TaskMain.taskList.get(0).equals("heroExperience.png") == false) {
                lookTaskbar();
            }

            result = publicFunction.localFindPicHLS(859, 564, 914, 615, "automaticCombat1.png" + "|" + "automaticCombat1-1.png|automaticCombat1-2.png|automaticCombat1-3.png");
            if (result.sim >= 0.65) {
                //连续战斗2分钟以上,打开活动
                LtLog.i(publicFunction.getLineInfo() + "连续战斗2分钟以上,打开活动");
                time3x = System.currentTimeMillis() / 1000 - time3;
                //连续2分钟一直在战斗中，打开活动
                //某些任务需要打怪，任务完成后还在打怪，近战会不停的在移动中，所以 连续2分钟一直在打怪，打开活动检测是否任务已经完成

                LtLog.i(publicFunction.getLineInfo() + "连续战斗2分钟以上,打开活动:" + time3x);

                if (time3x >= 120) {
                    gamePublicFunction.openActivity(1);
                    time3 = System.currentTimeMillis() / 1000;
                }
            } else {
                time3 = System.currentTimeMillis() / 1000;
            }


            if (("everydayTask.png").equals(TaskMain.taskList.get(0))) {
                //每日
                LtLog.i(publicFunction.getLineInfo() + "【每日任务】");

                if (gamePublicFunction.buyRope() == 99) {
                    //购买捕兽绳
                    LtLog.i(publicFunction.getLineInfo() + "------SingleTask--没有捕兽绳>");

                    TaskMain.taskList.remove(0);
                    if (TaskMain.taskList.size() == 0) {
                        gamePublicFunction.closeWindow();
                        LtLog.i(publicFunction.getLineInfo() + "------SingleTask--everydayTask--taskList.size()>" + "任务已完成");
                        return 99;
                    }
                }

                gamePublicFunction.luckDraw();
                tongAndEveryday(publicFunction);
                singleEveryday(publicFunction);

            } else if (("examination2.png").equals(TaskMain.taskList.get(0))) {
                //科举
                //timex = timex - 1;
                Examination(publicFunction);
            } else if (("hitBesto.png").equals(TaskMain.taskList.get(0))) {
                //惩凶打图
                singleHitBesto(publicFunction);
            } else if (("tongTask.png").equals(TaskMain.taskList.get(0))) {
                //帮会任务
                if (gamePublicFunction.buyRope() == 99) {
                    //购买捕兽绳
                    LtLog.i(publicFunction.getLineInfo() + "------SingleTask--没有捕兽绳>");
                    TaskMain.taskList.remove(0);
                    if (TaskMain.taskList.size() == 0) {
                        gamePublicFunction.closeWindow();
                        LtLog.i(publicFunction.getLineInfo() + "------SingleTask--everydayTask--taskList.size()>" + "任务已完成");
                        return 99;
                    }
                }

                tongTask(publicFunction);
                tongAndEveryday(publicFunction);
                gamePublicFunction.luckDraw();

            } else if (("alchemy.png").equals(TaskMain.taskList.get(0))) {
                //帮会炼金
                int alchemyState = alchemy(publicFunction);
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--alchemyState=>" + alchemyState);
                switch (alchemyState) {
                    case 1:
                        TaskMain.taskList.remove(0);
                        gamePublicFunction.openActivity(1);
                        break;
                    case 2:
                        gamePublicFunction.openActivity(1);
                        break;
                }
                if (mTime_i >= 180) {
                    LtLog.i(publicFunction.getLineInfo() + "------mTime_i out--=>" + mTime_i);
                    TaskMain.taskList.remove(0);
                    gamePublicFunction.openActivity(1);
                }
            } else if (("branchGold.png").equals(TaskMain.taskList.get(0))) {
                //分金定穴
                branchGold(publicFunction);
            } else if (("heroExperience.png").equals(TaskMain.taskList.get(0))) {
                //英雄试炼
                if (TaskMain.optionJson.optString("challenge").equals("1") == true) {
                    LtLog.e(mFairy.getLineInfo("挑战"));

                    switch (heroExperience(publicFunction)) {
                        case 1:
                            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--英雄试炼完成>");
                            consumeFragment();
                            TaskMain.taskList.remove(0);
                            gamePublicFunction.openActivity(1);
                            break;
                    }

                    if (gamePublicFunction.outDungeons() == 1) {
                        //如果在 副本中 开启自动战斗
                        mFairy.initMatTime();
                        mTime_i = 0;
                        gamePublicFunction.automaticCombat(1);
                    }
                } else {
                    if (heroExperienceSweep(publicFunction) == 1) {
                        consumeFragment();
                        gamePublicFunction.openActivity(1);
                        TaskMain.taskList.remove(0);
                    }
                }
            } else if (("desire.png").equals(TaskMain.taskList.get(0))) {
                result = publicFunction.localFindPic(898, 478, 1063, 544, "complete.png");
                if (result.sim >= 0.7) {
                    LtLog.i(publicFunction.getLineInfo() + "------SingleTask--complete>" + result);
                    publicFunction.rndTap(result.x, result.y, result.x + 96, result.y + 27);
                    Thread.sleep(2000);
                    gamePublicFunction.openActivity(1);
                }
                desire();
            } else if (("reward.png").equals(TaskMain.taskList.get(0))) {
                reward();
            } else if (("linkTask.png").equals(TaskMain.taskList.get(0))) {
                int linkState = linkTask();
                if (linkState != 0) {
                    return linkState;
                }
            }
            Thread.sleep(100);
        }
        return 0;
    }

    private int lookTaskbar() throws Exception {
        String currentTask = "";
        int[] range = new int[4];
        switch (TaskMain.taskList.get(0)) {
            case "everydayTask.png":
                currentTask = "everyday.png";
                range = new int[]{4, 239, 59, 461};
                break;
            case "tongTask.png":
                currentTask = "tong.png" + "|" + "tong-1.png";
                range = new int[]{4, 239, 59, 461};
                break;
            case "hitBesto.png":
                currentTask = "hitBesto1.png";
                range = new int[]{55, 284, 97, 468};
                break;
            case "alchemy.png":
                currentTask = "goldMine.png";
                range = new int[]{89, 289, 250, 473};
                break;
            case "branchGold.png":
                currentTask = "branchGold1.png|branchGold1-1.png";
                range = new int[]{51, 278, 250, 465};
                break;
            case "desire.png":
                currentTask = "desire8.png";
                range = new int[]{4, 264, 131, 454};
                break;
            case "reward.png":
                currentTask = "reward2.png";
                range = new int[]{43, 275, 115, 475};
                break;
            case "linkTask.png":
                currentTask = "linkTask4.png";
                range = new int[]{5, 266, 61, 475};
                break;
        }


        gamePublicFunction.switchTaskOrTeam("task");


        mFairy.ranSwipe(130,286,130,395,500,500);
        mFairy.ranSwipe(130,286,130,395,500,1000);

        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPicHLS(653,1,1104,96, "activity.png");
            if (result.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----------------------PublicFunction---openActivity--activity>" + result);
                return 1;
            }


            result = publicFunction.localOptimalFindPic(range[0], range[1], range[2], range[3], 140, 255.0, 0, currentTask);
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--currentTask>" + currentTask + ",result=" + result);
            if (result.sim >= 0.70) {
                if (gamePublicFunction.outDungeons() == 0) {
                    rnd_move();
                }

                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--everydayTask>" + result);
//                    AtFairy2.OpencvResult result1 = publicFunction.localOptimalFindPic(859, 564, 914, 615, 240, 255, 0, "automaticCombat1.png|automaticCombat1-1.png|automaticCombat1-2.png");
                AtFairy2.OpencvResult result1 = publicFunction.localFindPicHLS(859, 564, 914, 615, "automaticCombat1.png|automaticCombat1-1.png|automaticCombat1-2.png");
                if (TaskMain.taskList.get(0).equals("branchGold.png") && result1.sim >= 0.75) {
                    for (int j = 0; j < 60; j++) {
                        LtLog.i(publicFunction.getLineInfo() + "-------automaticCombat1>" + result1);
                        Thread.sleep(500);
                    }
                }
                if (TaskMain.taskList.get(0).equals("everydayTask.png")) {
                }
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--everydayTask>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 138, result.y + 20);
                for (int j = 0; j < 11; j++) {
                    Thread.sleep(500);
                }
                if (TaskMain.taskList.get(0).equals("desire.png")) {
                    mFairy.initMatTime();
                    mTime_i = 0;
                }
                range = null;
                return 1;
            }else{
                if(i==5){
                    mFairy.ranSwipe(130,395,130,286,500,1000);
                }

            }
            Thread.sleep(100);
        }
        range = null;
        return 0;
    }

    /**
     * 随机用方向盘移动一下，有可能卡死了；
     */
    public void rnd_move() {
        mFairy.touchDown(165, 617);
        mFairy.touchMove(301, 601, 0);
        mFairy.touchUp();
    }

    //领邮件
    private void post() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "领邮件");
        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPic(653,1,1104,96, "activity.png");
            if (result.sim > 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "点邮件");
                publicFunction.rndTap(367, 655, 401, 685); //点邮件
                Thread.sleep(5000);
            }
            result = publicFunction.localFindPic(10, 415, 152, 663, "post.png");
            if (result.sim > 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "邮件界面");
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(319, 595, 521, 698, "dispose.png");
            if (result.sim > 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "一键处理");
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(1000);
                break;
            }
            Thread.sleep(1000);
        }

        gamePublicFunction.closeWindow();

    }

    private int linkTask() throws Exception {
        result = publicFunction.localFindPic(577, 0, 736, 102, "shop2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--shop2>" + "result=" + result + ",buy=" + TaskMain.optionJson.optString("buy").equals("1") + ",tongHelp=" + TaskMain.optionJson.optString("tongHelp").equals("1"));
            if (TaskMain.optionJson.optString("buy").equals("1")) {
                publicFunction.rndTap(488, 166, 570, 202);
                Thread.sleep(1000);
            } else if (TaskMain.optionJson.optString("tongHelp").equals("1")) {
                gamePublicFunction.closeWindow();
                Thread.sleep(2000);
                result = publicFunction.localFindPic(0, 153, 126, 274, "task3.png|task1.png");
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--task1>" + "result=" + result);
                if (result.sim >= 0.8) {
                    publicFunction.rndTapWH(result.x, result.y, 45, 21);
                    Thread.sleep(2000);
                }

                for (int i = 0; i < 10; i++) {
                    result = publicFunction.localFindPic(189, 85, 334, 677, "linkTask5.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------SingleTask--linkTask5>" + "result=" + result);
                        publicFunction.rndTapWH(result.x, result.y, 45, 21);
                        Thread.sleep(2000);
                    }
                    result = publicFunction.localFindPicHLS(1033, 125, 1149, 575, "help.png");
                    LtLog.i(publicFunction.getLineInfo() + "------SingleTask--help>" + "result=" + result);
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------SingleTask--help>" + "result=" + result);
                        publicFunction.rndTapWH(result.x, result.y, 10, 10);
                        Thread.sleep(2000);
                        break;
                    }
                    Thread.sleep(500);
                }
                gamePublicFunction.closeWindow();
            }
        }
        if (gamePublicFunction.buyRope() == 99) {
            //购买捕兽绳
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--没有捕兽绳>");
            return 640;
        }
        result = publicFunction.localOptimalFindPic(5, 266, 61, 475, 140, 255.0, 0, "linkTask4.png");
        LtLog.i(publicFunction.getLineInfo() + "------SingleTask--linkTask4>" + "result=" + result);
        if (result.sim >= 0.70) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--linkTask4>" + "result=" + result);
            mat = mFairy.getScreenMat(result.x + 46, result.y, result.x + 123, result.y + 20, 1, 0, 0, 1);
            boolean taskStste = false;
            if (mat1 != null) {
                taskStste = publicFunction.judgeMatAndMatChange(0.9, mat, mat1);
                //判断两个矩阵的相似度大于 sim 则返回 true;
            }
            LtLog.i(publicFunction.getLineInfo() + "-------taskStste=>" + taskStste);
            if (taskStste) {
                time2x = System.currentTimeMillis() / 1000 - time2;
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--linkTask4>" + "taskStste=" + taskStste + ",time2x=" + time2x);
                if (time2x >= 900) {
                    LtLog.i(publicFunction.getLineInfo() + "------SingleTask--长时间求助>");
                    return 641;
                } else if (time2x >= 300 && time2x <= 330) {
                    mFairy.tap(result.x + 10, result.y + 10);
                }
            } else {
                mat1 = mFairy.getScreenMat(result.x + 46, result.y, result.x + 123, result.y + 20, 1, 0, 0, 1);
                time2 = System.currentTimeMillis() / 1000;
            }
        }
        gamePublicFunction.switchTaskOrTeam("task");
        result = publicFunction.localFindPic(583, 471, 726, 591, "buy.png");
        LtLog.i(publicFunction.getLineInfo() + "---------buy>" + result);
        if (result.sim >= 0.8) {
            publicFunction.rndTapWH(result.x, result.y, 43, 20);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(376, 269, 563, 388, "linkTask2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------linkTask2>" + result);
            publicFunction.rndTap(750, 462, 793, 484);//点击确认
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(475, 367, 708, 412, "linkTask3.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------linkTask3>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 50, result.y + 10);
            Thread.sleep(300);
        }
        result = publicFunction.localFindPic(908, 489, 1046, 534, "task.png");
        if (result.sim >= 0.6) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--task>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 1, result.y + 1);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(475, 367, 708, 412, "linkTask1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------linkTask1>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 50, result.y + 10);
            Thread.sleep(300);
        }
        result = publicFunction.localFindPic(475, 367, 708, 412, "invaluable.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------invaluable>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 50, result.y + 10);
            Thread.sleep(300);
        }
        result = publicFunction.localFindPic(618, 17, 693, 61, "shop.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--shop.png商店窗口>" + result);
            Thread.sleep(500);
            result = publicFunction.localFindPic(164, 103, 1153, 563, "demand.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--demand.png>" + result);
                publicFunction.rndTap(result.x + 300, result.y + 45, result.x + 310, result.y + 50);
                Thread.sleep(500);
            }
        }
        result = publicFunction.localFindPic(898, 478, 1063, 544, "complete.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--complete>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 96, result.y + 27);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(539, 370, 648, 414, "again.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--again>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 100, result.y + 20);
            Thread.sleep(500);
        }
        gamePublicFunction.luckDraw();
        tongAndEveryday(publicFunction);
        Thread.sleep(100);
        return 0;
    }

    private int reward() throws Exception {
        result = publicFunction.localFindPic(531, 0, 718, 101, "reward1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------reward1>" + result);
            for (int i = 0; i < 5; i++) {
                result = publicFunction.localFindPic(531, 0, 718, 101, "reward1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------reward1>" + result);
                    publicFunction.rndTap(196 + ((TaskMain.rewardSet - 1) * 208), 598, 275 + ((TaskMain.rewardSet - 1) * 208), 618);
                    Thread.sleep(1500);

                    result = publicFunction.localFindPic(387, 298, 605, 390, "spend.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "-------spend>" + result);
                        publicFunction.rndTap(754, 466, 785, 482);
                        Thread.sleep(500);
                    }

                } else {
                    break;
                }
                Thread.sleep(500);
            }
            gamePublicFunction.closeWindow();
        }
        result = publicFunction.localFindPic(455, 350, 952, 533, "reward3.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------reward3>" + result);
            publicFunction.rndTapWH(result.x, result.y, 67, 23);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(387, 298, 605, 390, "spend.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------spend>" + result);
            publicFunction.rndTap(754, 466, 785, 482);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(393, 287, 677, 392, "experienceLimit.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------experienceLimit>" + result);
            TaskMain.taskList.remove("reward.png");
            publicFunction.rndTap(488, 461, 525, 480);//点取消
            Thread.sleep(2000);
            gamePublicFunction.closeWindow();
            Thread.sleep(2000);
            gamePublicFunction.openActivity(1);
        }
        return 0;
    }

    private int heroExperience(PublicFunction publicFunction) throws Exception {
        List<String> listRegion = new ArrayList<>();
        List<String> listPic = new ArrayList<>();
        listRegion.add("513,368,684,414");
        listPic.add("heroExperience1.png");
        listRegion.add("1046,622,1138,671");
        listPic.add("challenge.png");
        listRegion.add("1046,622,1138,671");
        listPic.add("challenge3.png");
        listRegion.add("719,495,864,540");
        listPic.add("challenge1.png");
        publicFunction.localMultiRegionFindManyPic(listRegion, listPic);
        result = publicFunction.localFindPic(898, 478, 1063, 544, "complete.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "-------complete>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 96, result.y + 27);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(738, 319, 833, 370, "fail.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------fail>" + result);
            for (int i = 0; i < 30; i++) {
                result = publicFunction.localFindPic(738, 319, 833, 370, "fail.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------tofail>" + result);
                    publicFunction.rndTap(485, 460, 533, 485);
                    Thread.sleep(300);
                }
                result = publicFunction.localFindPic(653,1,1104,96, "activity.png");
                if (result.sim > 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------activity>" + result);
                    return 1;
                }
                gamePublicFunction.closeWindow();
                Thread.sleep(300);
            }
        }
        return 0;
    }

    private int heroExperienceSweep(PublicFunction publicFunction) throws Exception {
        List<String> listRegion = new ArrayList<>();
        List<String> listPic = new ArrayList<>();
        listRegion.add("513,368,684,414");
        listPic.add("heroExperience1.png");
        listRegion.add("701,441,840,496");
        listPic.add("confirm1.png");
        listRegion.add("861,622,1001,674");
        listPic.add("sweep1.png");
        listRegion.add("1046,622,1138,671");
        listPic.add("challenge3.png");

        publicFunction.localMultiRegionFindManyPic(listRegion, listPic);

        result = publicFunction.localFindPicHSV(861, 622, 1001, 674, "sweep.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------sweep>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 50, result.y + 20);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(573, 204, 712, 536, "sweepOff.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------sweepOff>" + result);
            publicFunction.rndTap(622, 568, 657, 584);//关闭扫荡
            Thread.sleep(1000);
            return 1;
        }

        findResult = mFairy.findPic(563, 9, 705, 72, "tiaozhan.png");
        if (findResult.sim > 0.8f) {
            long num = mFairy.getColorNum(884, 638, 903, 661, "143,143,143", 0.9f);
            if (num > 10) {
                LtLog.i(publicFunction.getLineInfo() + "无法扫荡了");
                return 1;
            }
        }


        result = publicFunction.localFindPicHLS(861, 622, 1001, 674, "sweepOff1.png" + "|" + "sweepOff1-1.png" + "|" + "sweepOff1-2.png" + "|" + "sweepOff1-3.png" + "|" + "sweepOff1-4.png");
        LtLog.i(publicFunction.getLineInfo() + "-------sweepOff1>" + result);
        if (result.sim >= 0.78) {
            LtLog.i(publicFunction.getLineInfo() + "-------sweepOff1>" + result);
            return 1;
        }

        findResult = mFairy.findPic("chongzhi.png");
        if (findResult.sim > 0.8f) {
            LtLog.i(publicFunction.getLineInfo() + "发现重置");
            return 1;
        }

        result = publicFunction.localFindPic(558, 238, 679, 509, "notSpace.png");
        LtLog.i(publicFunction.getLineInfo() + "-------notSpace>" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------notSpace>" + result);
            publicFunction.rndTap(622, 568, 657, 584);//关闭扫荡
            Thread.sleep(1000);
            consumeFragment();
            gamePublicFunction.openActivity(1);
        }
        return 0;
    }

    //帮会炼金
    private int alchemy(PublicFunction publicFunction) throws Exception {
        result = publicFunction.localFindPic(252, 371, 358, 462, "npc1.png");
        if (result.sim >= 0.85) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--alchemy-npc1>" + result);
            Thread.sleep(1000);
            result = publicFunction.localFindPic(898, 478, 1063, 544, "luckyBag.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--alchemy-luckyBag>" + result);
                publicFunction.rndTap(1055, 261, 1071, 279);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(898, 478, 1063, 544, "complete.png");
            if (result.sim >= 0.7) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--complete>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 96, result.y + 27);
                Thread.sleep(2000);
                alchem_index = 0;
                return 2;
            }
            int xy = publicFunction.getXY(587, 327, 641, 353, 101, 255, 0);

//            int xy=ocrNumber.getNumber(587, 327, 54, 26,new Scalar(0,150,0),new Scalar(50,255,50));
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--当前活跃=>" + xy);
            if (xy < 30 && result.sim < 0.7) { //活跃不足 并且没有完成任务
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--alchemy-活跃值不足>" + xy + ",alchem_index  " + alchem_index);
                alchem_index = alchem_index + 1;
                //数字识别并不准确  所以计2次后才返回活跃不足
                if (alchem_index > 1) {
                    publicFunction.rndTap(1055, 261, 1076, 284);//关闭对话框
                    Thread.sleep(500);
                    return 1;
                } else {
                    publicFunction.rndTap(552, 380, 633, 397);//搬运金矿
                    Thread.sleep(500);
                }

            } else if (xy >= 150) {
                result = publicFunction.localFindPic(513, 431, 696, 483, "goldMine1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------SingleTask--alchemy-goldMine1>" + result);
                    publicFunction.rndTapWH(result.x, result.y, 50, 20);
                    Thread.sleep(1000);
                }
            }
            result = publicFunction.localFindPic(513, 368, 684, 414, "goldMine1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--alchemy-goldMine1>" + result);
                publicFunction.rndTapWH(result.x, result.y, 50, 20);
                Thread.sleep(300);
            }
            for (int i = 0; i < 7; i++) {
                result = publicFunction.localFindPic(471, 246, 688, 381, "active150.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------SingleTask--alchemy-active150>" + result);
                    publicFunction.rndTap(755, 465, 801, 486);
                    Thread.sleep(1000);
                }
                result = publicFunction.localFindPic(252, 371, 358, 462, "npc1.png");
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--alchemy-npc1>" + result);
                if (result.sim >= 0.85) {
                    publicFunction.rndTap(1055, 261, 1071, 279);//关闭npc对话
                    Thread.sleep(500);
                }
                Thread.sleep(1000);

            }
        }


        return 0;
    }

    private void desire() throws Exception {
        List<String> listRegion = new ArrayList<>();
        List<String> listPic = new ArrayList<>();
        listRegion.add("492,365,699,411");
        listRegion.add("507,365,681,414");
        listRegion.add("509,361,673,426");
        listPic.add("desire1.png");
        listPic.add("desire3.png");
        listPic.add("desire6.png");
        publicFunction.localMultiRegionFindManyPic(listRegion, listPic);

        result = publicFunction.localFindPic(568, 197, 708, 246, "desire2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------desire2>" + result);
            publicFunction.rndTap(754, 459, 799, 486);
            Thread.sleep(500);

        }

        result = publicFunction.localOptimalFindPic(4, 264, 131, 454, 140, 255.0, 0, "desire8.png");
        if (result.sim >= 0.70) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--everydayTask>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 138, result.y + 20);
            Thread.sleep(1000);
        }


        result = publicFunction.localFindPic(842, 413, 953, 450, "desire4.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------desire4>" + result);
            publicFunction.rndTap(871, 502, 917, 523);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(650, 150, 882, 311, "desire5.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------desire5>" + result);
            publicFunction.rndTap(761, 519, 798, 536);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(634, 134, 704, 286, "desire7.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------desire7>" + result);
            mFairy.touchDown(142, 249);
            Thread.sleep(200);
            mFairy.touchMove(419, 266, 200);
            Thread.sleep(1000);
            mFairy.touchUp();
            Thread.sleep(500);
            mFairy.touchDown(148, 538);
            Thread.sleep(200);
            mFairy.touchMove(590, 513, 200);
            Thread.sleep(1000);
            mFairy.touchUp();
            Thread.sleep(500);
            mFairy.touchDown(1166, 364);
            Thread.sleep(200);
            mFairy.touchMove(952, 455, 200);
            Thread.sleep(1000);
            mFairy.touchUp();
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(1182, 20, 1249, 84, "skip.png");
        if (result.sim >= 0.6) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--skip>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(500);
        }

    }

    private void branchGold(PublicFunction publicFunction) throws Exception {
        //分金定穴
        AtFairy2.OpencvResult result1;
        result = publicFunction.localOptimalFindPic(51, 278, 131, 465, 140, 255.0, 0, "branchGold1.png");
        result1 = publicFunction.localOptimalFindPic(51, 278, 131, 465, 140, 255.0, 0, "branchGold2.png");
        if (result1.sim > result.sim) {
            result = result1;
        }
        if (result.sim >= 0.75) {
            LtLog.i(publicFunction.getLineInfo() + "-------branchGold1>" + result);
            result1 = publicFunction.localOptimalFindPic(result.x + 119, result.y + 17, result.x + 218, result.y + 49, 140, 255.0, 0, "complete2.png");
            if (result1.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "-------complete2>" + result);
                publicFunction.rndTapWH(result.x, result.y, 164, 28);
                Thread.sleep(500);
                gamePublicFunction.openActivity(1);
                return;
            } else {
                result = publicFunction.localFindPic(833, 302, 955, 410, "chart.png");
                if (result.sim >= 0.80) {
                    LtLog.i(publicFunction.getLineInfo() + "-------chart>" + result);
                    mFairy.tap(result.x, result.y + 200);
                    Thread.sleep(500);
                }
            }
        }
        result = publicFunction.localFindPicHLS(859, 564, 914, 615, "automaticCombat1.png" + "|" + "automaticCombat1-1.png|automaticCombat1-2.png");
        if (result.sim >= 0.75) {
            LtLog.i(publicFunction.getLineInfo() + "-------------------------automaticCombat1--->" + result);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(575, 0, 740, 103, "shop1.png|shop1-1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------------发现需要购买宝图-------------shop1>" + result);
            TaskMain.taskList.remove(0);
            for (int i = 0; i < 3; i++) {
                gamePublicFunction.closeWindow();
            }
            gamePublicFunction.openActivity(1);
        }
        result = publicFunction.localFindPic(860, 491, 928, 537, "use.png");
        if (result.sim >= 0.8) {


        }
    }

    //帮会任务
    private void tongTask(PublicFunction publicFunction) throws Exception {

        if (TaskMain.tongSet >= 1 && TaskMain.tongSet <= 5) {
            result = publicFunction.localFindPic(1048, 135 + ((TaskMain.tongSet - 1) * 82), 1149, 181 + ((TaskMain.tongSet - 1) * 82), "receive2.png");
            if (result.sim >= 0.85) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--tongTask--receive2>" + result);
                publicFunction.rndTapWH(result.x, result.y, 45, 26);
                Thread.sleep(1000);
                result = publicFunction.localFindPic(1157, 30, 1238, 98, "fork1.png");
                if (result.sim >= 0.8) {
                    //点完接取 关闭窗口
                    publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                    Thread.sleep(200);
                }
            }
        } else if (TaskMain.tongSet == 6) {
            //找通宝最高的接
            result = publicFunction.localFindPic(546, 0, 764, 101, "tongTask1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--tongTask--tongTask1>" + result);
                int maxIndex = 0;
                for (int i = 1; i <= 5; i++) {
                    result = publicFunction.localFindPic(931, 121, 1055, 528, "tongX" + Integer.toString(i) + ".png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------SingleTask--tongTask--tongTask1>" + result + ",i=" + i);
                        if (i > maxIndex) {
                            maxIndex = i;
                        }
                    }
                }
                result = publicFunction.localFindPic(931, 121, 1055, 528, "tongX" + Integer.toString(maxIndex) + ".png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------SingleTask--tongTask--tongTask1>" + result + ",maxIndex=" + maxIndex);
                    publicFunction.rndTap(result.x + 111, result.y, result.x + 157, result.y + 10);
                    Thread.sleep(2000);
                    gamePublicFunction.closeWindow();
                }
            }
        }
        //稀世之宝
        result = publicFunction.localFindPic(577, 0, 736, 102, "shop2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------shop2>" + result);
            if (TaskMain.judgeSelected("buy")) {
                publicFunction.rndTap(488, 166, 570, 202);
                Thread.sleep(2000);
            } else {
                gamePublicFunction.closeWindow();
                TaskMain.taskList.remove("tongTask.png");
                return;
            }
        }
        result = publicFunction.localFindPic(583, 471, 726, 591, "buy.png");
        if (result.sim >= 0.8) {
            publicFunction.rndTapWH(result.x, result.y, 43, 20);
            Thread.sleep(2000);
        }
        result = publicFunction.localFindPic(577, 0, 736, 102, "shop2.png");
        if (result.sim >= 0.8) {
            gamePublicFunction.closeWindow();
        }
        //拜访名士
        for (int i = 0; i < 20; i++) {
            result = publicFunction.localFindPic(569, 169, 649, 220, "visit1.png");
            if (result.sim >= 0.85) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--tongTask--visit1>" + result);


                publicFunction.rndTapWH(352, 316, 574, 175);
                Thread.sleep(1000);

            } else {
                break;
            }
            Thread.sleep(10);
        }
    }

    public void sendTongTask(String mToken, String answer) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd|HHmmss");
        String date = sDateFormat.format(new java.util.Date());
        LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------....." + date.split("\\|"));
        String[] dataTime = date.split("\\|");
        String keyStr = dataTime[0] + "_141_" + answer + "_" + dataTime[1] + ".png";
        LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------....." + keyStr);
        String filePath = "/sdcard/screen.png";
        publicFunction.httpPost(filePath, keyStr, mToken);
        publicFunction.fileDelete(filePath);
        return;
    }

    private void tongAndEveryday(PublicFunction publicFunction) throws Exception {

        result = publicFunction.localFindPic(908, 489, 1046, 534, "task.png");
        if (result.sim >= 0.7f) {
            LtLog.i(publicFunction.getLineInfo() + "右侧任务");
            publicFunction.rndTap(result.x, result.y, result.x + 1, result.y + 1);
            Thread.sleep(1000);
        }

        result = publicFunction.localFindManyPic(466, 362, 961, 542, new String[]{"rareBirds.png", "visit.png", "outstanding.png", "rareBirds1.png", "meridian.png", "outstanding1.png"});
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "各种选项");
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(300);
        }
        result = publicFunction.localFindPic(602, 530, 683, 576, "submit.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "提交");
            publicFunction.rndTap(result.x, result.y, result.x + 90, result.y + 20);
            Thread.sleep(500);
        }
    }

    public void dian() throws Exception {

        result = publicFunction.localOptimalFindPic(495, 26, 568, 71, 200, 255, 0, "according.png");
        AtFairy2.OpencvResult result2 = publicFunction.localFindPic(452, 11, 681, 83, "acupoint1.png" + "|" + "help1.png");
        if (result.sim >= 0.8 || result2.sim > 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "发现点穴");
            Thread.sleep(1000);
            try {
                gamePublicFunction.dianxues();
                mFairy.touchUp();
                Thread.sleep(1000);

                result = publicFunction.localOptimalFindPic(495, 26, 568, 71, 200, 255, 0, "according.png");
                result2 = publicFunction.localFindPic(452, 11, 681, 83, "acupoint1.png" + "|" + "help1.png");
                if (result.sim >= 0.8 || result2.sim > 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "自动点穴没通过");



                    findResult = mFairy.findPic("dianxue6.png");
                    if (findResult.sim > 0.9f) {
                        LtLog.i(publicFunction.getLineInfo() + "【dianxue6】");
                        mFairy.touchDown(485,160);
                        mFairy.touchMove(557,239, 300);
                        mFairy.touchMove(645,210, 300);
                        mFairy.touchMove(586,301, 300);
                        mFairy.touchMove(707,343, 300);
                        mFairy.touchMove(763,418, 300);
                        mFairy.touchMove(658,442, 300);
                        mFairy.touchMove(745,565, 300);
                        mFairy.touchUp();
                        return;
                    }




                    findResult = mFairy.findPic("dianxue5.png");
                    if (findResult.sim > 0.9f) {
                        LtLog.i(publicFunction.getLineInfo() + "【dianxue5】");
                        mFairy.touchDown(639,134);
                        mFairy.touchMove(602,267, 300);
                        mFairy.touchMove(739,383, 300);
                        mFairy.touchMove(735,595, 300);
                        mFairy.touchMove(483,484, 300);
                        mFairy.touchUp();
                        return;
                    }




                    findResult = mFairy.findPic("dianxue4.png");
                    if (findResult.sim > 0.9f) {
                        LtLog.i(publicFunction.getLineInfo() + "【dianxue4】");
                        mFairy.touchDown(616,78);
                        mFairy.touchMove(756,172, 300);
                        mFairy.touchMove(682,250, 300);
                        mFairy.touchMove(776,355, 300);
                        mFairy.touchMove(780,461, 300);
                        mFairy.touchMove(759,589, 300);
                        mFairy.touchMove(562,462, 300);
                        mFairy.touchUp();
                        return;
                    }





                    findResult = mFairy.findPic("dianxue3.png");
                    if (findResult.sim > 0.9f) {
                        LtLog.i(publicFunction.getLineInfo() + "【dianxue3】");
                        mFairy.touchDown(641, 134);
                        mFairy.touchMove(603, 269, 300);
                        mFairy.touchMove(737, 384, 300);
                        mFairy.touchMove(740, 589, 300);
                        mFairy.touchMove(484, 485, 300);
                        mFairy.touchUp();
                        return;
                    }






                    findResult = mFairy.findPic("dianxue2.png");
                    if (findResult.sim > 0.9f) {
                        LtLog.i(publicFunction.getLineInfo() + "【dianxue2】");
                        mFairy.touchDown(482, 157);
                        mFairy.touchMove(559, 243, 300);
                        mFairy.touchMove(643, 214, 300);
                        mFairy.touchMove(589, 302, 300);
                        mFairy.touchMove(703, 340, 300);
                        mFairy.touchMove(763, 420, 300);
                        mFairy.touchMove(658, 443, 300);
                        mFairy.touchMove(748, 564, 300);
                        mFairy.touchUp();
                        return;
                    }

                    findResult = mFairy.findPic("dianxue1.png");
                    if (findResult.sim > 0.9f) {
                        LtLog.i(publicFunction.getLineInfo() + "【dianxue1】");
                        mFairy.touchDown(670, 166);
                        mFairy.touchMove(600, 209, 300);
                        mFairy.touchMove(495, 157, 300);
                        mFairy.touchMove(557, 241, 300);
                        mFairy.touchMove(635, 317, 300);
                        mFairy.touchMove(579, 373, 300);
                        mFairy.touchMove(479, 424, 300);
                        mFairy.touchMove(566, 465, 300);
                        mFairy.touchUp();
                        return;
                    }

                }

                Thread.sleep(1000);

                result = publicFunction.localOptimalFindPic(495, 26, 568, 71, 200, 255, 0, "according.png");
                result2 = publicFunction.localFindPic(452, 11, 681, 83, "acupoint1.png" + "|" + "help1.png");
                if (result.sim >= 0.8 || result2.sim > 0.8) {
                    gamePublicFunction.Acupuncture();
                }


            } catch (Exception e) {
                LtLog.i(publicFunction.getLineInfo() + "------------------------e" + e);
            }


            for (int i = 0; i < 60; i++) {
                result = publicFunction.localFindPic(653,1,1104,96, "activity.png");
                LtLog.i(publicFunction.getLineInfo() + "---------------------SingleTask---activity=" + result);
                if (result.sim > 0.8) {
                    break;
                }
                Thread.sleep(1000);
            }
        }
    }

    private void singleEveryday(PublicFunction publicFunction) throws Exception {
        AtFairy2.OpencvResult result1;
        result = publicFunction.localFindPic(475, 367, 708, 412, "receive1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "领取");
            publicFunction.rndTap(result.x, result.y, result.x + 140, result.y + 23);
            Thread.sleep(300);
        }
        result = publicFunction.localFindPic(898, 478, 1063, 544, "complete.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "完成");
            publicFunction.rndTap(result.x, result.y, result.x + 96, result.y + 27);
            Thread.sleep(1000);
            gamePublicFunction.openActivity(1);
        }
        result = publicFunction.localFindPic(618, 17, 693, 61, "shop.png");
        if (result.sim >= 0.8) {
            Thread.sleep(500);
            LtLog.i(publicFunction.getLineInfo() + "商店界面");





            result = publicFunction.localFindPic(164, 103, 1153, 563, "demand.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--demand.png>" + result);
                publicFunction.rndTap(result.x + 300, result.y + 45, result.x + 310, result.y + 50);
                Thread.sleep(500);
            }

            findResult = mFairy.findPic(481,373,794,552,"queding.png");
            mFairy.onTap(0.75f,findResult,"确定",1000);


        }

        result = publicFunction.localFindPic(536,377,749,539, "determine.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "确定");
            publicFunction.rndTap(result.x, result.y, result.x + 100, result.y + 20);
            Thread.sleep(500);
        }

        dian();


        result = publicFunction.localOptimalFindPic(51, 286, 137, 473, 127, 255.0, 0, "space.png");
        result1 = publicFunction.localOptimalFindPic(51, 286, 137, 473, 127, 255.0, 0, "space3.png");
        if (result1.sim > result.sim) {
            result = result1;
        }

        if (result.sim >= 0.70) {
            LtLog.i(publicFunction.getLineInfo() + "踩踩空间");
            //
            result = publicFunction.localOptimalFindPic(489, 554, 504, 687, 180, 255, 0, "roleName.png");
            result1 = publicFunction.localOptimalFindPic(489, 554, 504, 687, 180, 255.0, 0, "roleName1.png");
            if (result1.sim > result.sim) {
                result = result1;
            }

            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--roleName>" + result);
                mFairy.tap(result.x + 10, result.y + 5);
                Thread.sleep(1000);
            }
        }


        result = publicFunction.localFindPic(9, 7, 129, 587, "world.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "世界");
            publicFunction.rndTapWH(result.x, result.y, 45, 21);

            Thread.sleep(2000);

            for (int i = 0; i < 4; i++) {
                result = publicFunction.localFindPic(214, 39, 273, 613, "world1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------world1");
                    mFairy.tap(result.x + 68, result.y + 10);
                    Thread.sleep(2000);
                    break;
                }
                Thread.sleep(1000);
            }
        }

        result = publicFunction.localFindPic(682, 265, 1021, 623, "space1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "点击个人空间");
            publicFunction.rndTap(result.x, result.y, result.x + 50, result.y + 20);
            Thread.sleep(1500);
        }


        result = publicFunction.localFindPic(587, 20, 739, 63, "space2.png|space4.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "个人空间界面");

            publicFunction.rndTap(475, 87, 515, 103);//点留言
            Thread.sleep(1000);
            publicFunction.rndTap(912, 647, 965, 664);//点踩一踩
            Thread.sleep(2000);
            result = publicFunction.localFindPic(587, 20, 739, 63, "space2.png|space4.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "关闭");
                publicFunction.rndTap(1159, 32, 1180, 53);//点关闭
                Thread.sleep(1000);
            }
        }

        result = publicFunction.localFindPic(5, 237, 116, 474, "everdayFurniture.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "每日 - 家具 - 放弃");
            //放弃家具互交 这个任务
            gamePublicFunction.renounceEverday();
        }
//        "everdayfamily-1.png"
//                "everdayfamily.png"

        result = publicFunction.localFindPic(1076, 179, 1270, 334, "family.png");
        if (result.sim >= 0.8) {
            //在家园中，切换到活动
            LtLog.i(publicFunction.getLineInfo() + "在家园中,切换到活动");
            result = publicFunction.localFindPic(653,1,1104,96, "activity.png");
            if (result.sim < 0.8) {
                publicFunction.rndTap(1069, 18, 1088, 34);
                Thread.sleep(1000);
            }
        }

        result = publicFunction.localFindPic(6, 238, 142, 472, "everdayfamily-1.png" + "|" + "everdayfamily.png");
        if (result.sim > 0.7) {

            LtLog.i(publicFunction.getLineInfo() + "家园打扫 | 家园拜访");
            publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 10);
            Thread.sleep(10000);

            result = publicFunction.localOptimalFindPic(489, 554, 504, 687, 180, 255, 0, "roleName.png");
            result1 = publicFunction.localOptimalFindPic(489, 554, 504, 687, 180, 255.0, 0, "roleName1.png");
            if (result1.sim > result.sim) {
                result = result1;
            }
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--roleName>" + result);
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--roleName>" + result);
                mFairy.tap(result.x + 10, result.y + 5);
                Thread.sleep(3000);
            }
            result = publicFunction.localFindPic(556, 225, 1052, 659, "everdayfamily1-1.png" + "|" + "everdayfamily1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "进入家园");
                publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 10);
                Thread.sleep(2000);
            }
        }


        result = publicFunction.localFindPic(678, 261, 1011, 647, "everdayfamily2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "聊天");
            publicFunction.rndTap(184, 712, 1266, 716);
            Thread.sleep(1000);
        }
        Thread.sleep(100);
    }

    private void singleHitBesto(PublicFunction publicFunction) throws Exception {
        //惩凶打图
        result = publicFunction.localFindPic(537, 368, 651, 409, "actViolently1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--singleHitBesto--actViolently1>" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(300);
        }
        result = publicFunction.localFindPic(898, 478, 1063, 544, "complete.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--complete>" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 96, result.y + 27);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(860, 491, 928, 537, "use.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------Abnormal--use>" + result);
            AtFairy2.OpencvResult result1 = publicFunction.localFindPic(814, 406, 978, 459, "openBox15.png");
            if (result1.sim >= 0.9) {
                LtLog.i(publicFunction.getLineInfo() + "------Abnormal--openBox1>" + result);
                publicFunction.rndTapWH(result.x, result.y, 43, 22);
                Thread.sleep(2000);
            }
            result1 = publicFunction.localFindPic(519, 200, 759, 249, "inputQuantity.png");
            if (result1.sim >= 0.85) {
                LtLog.i(publicFunction.getLineInfo() + "------Abnormal--inputQuantity>" + result);
                publicFunction.rndTap(615, 466, 665, 488);
                Thread.sleep(2000);
            }

        }
    }

    public int mainTask() throws Exception {
        //主线任务

        picArray = new String[]{"again.png", "outstanding.png", "rareBirds.png", "wipe.png", "forTheFirstTime.png", "mysterious.png", "comeOn.png"};
        gamePublicFunction.switchTaskOrTeam("task");
        int[] color_xy;
        while (mFairy.condit()) {
            //result = publicFunction.localFindPic(539,370,648,414, "again.png");


            result = publicFunction.localFindManyPic(539, 370, 648, 414, picArray);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--picArray>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 100, result.y + 20);
                Thread.sleep(500);
            }


            result = publicFunction.localFindPic(53, 123, 121, 237, "nn3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "******nn3=" + result);//
                publicFunction.rndTap(1187, 132, 1201, 145);
                Thread.sleep(500);
            }

            mTime_i = mFairy.mMatTime(1126, 27, 61, 20, 0.9f);
            //xy = publicFunction.getXY(1118, 31, 1198, 48, 200, 255, 0);
//            xy=ocrNumber.getNumber(1120,29,74,14,new Scalar(200,200,200),new Scalar(255,255,255));
           /* if (xy != xy1 && xy > 0) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--xy>" + xy);
                xy1 = xy;
                time = System.currentTimeMillis() / 1000;
            }*/


            //timex = System.currentTimeMillis() / 1000 - time;

            time1x = System.currentTimeMillis() / 1000 - time1;//每间隔一分钟点一次主线


            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--mTime_i>" + mTime_i + ",time1x=" + time1x);
            result = publicFunction.localToValueFindPic(0, 240, 120, 290, 147, 255.0, 0, "main.png");
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--main>" + result);
            if ((result.sim >= 0.85 && mTime_i > 10) || time1x >= 60) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--main>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 138, result.y + 20);
                mFairy.initMatTime();
                mTime_i = 0;
                time1 = System.currentTimeMillis() / 1000;
                Thread.sleep(500);
            } else {
                result = publicFunction.localToValueFindPic(0, 240, 120, 290, 200, 255.0, 0, "main1.png");
                if (result.sim >= 0.85 && mTime_i > 10) {
                    LtLog.i(publicFunction.getLineInfo() + "------SingleTask--main>" + result);
                    publicFunction.rndTap(result.x, result.y, result.x + 138, result.y + 20);
                    mFairy.initMatTime();
                    mTime_i = 0;
                    Thread.sleep(500);
                }
            }
            result = publicFunction.localFindPic(423, 550, 501, 596, "receive.png");
            if (result.sim >= 0.85) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--receive.png>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 27);
                Thread.sleep(500);
            }
//            ;;

          /*  color_xy = functionClass.multipointFindColor(993, 430, 1088, 514, "255,230,0", "34|39|222,154,28&36|-31|255,255,112&70|4|255,187,5", 0.8);
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--color_xy>" + color_xy[0]);
            if (color_xy[0] > 0) {
                //检测是否能放大招
                publicFunction.rndTap(1037, 461, 1054, 479);
                Thread.sleep(500);
            }*/

            result = publicFunction.localFindPic(708, 153, 885, 420, "complete3.png");
            if (result.sim >= 0.80) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--complete3.png>" + result);
                publicFunction.rndTap(925, 127, 943, 148);
                Thread.sleep(500);
            }
            //result = mFairy.findPic(908, 489, 1046, 534, 10, 1, "complete.png", tlbb.GetCaptureRaw());
            result = publicFunction.localFindPic(898, 478, 1063, 544, "complete.png");
            if (result.sim >= 0.7) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--complete>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 96, result.y + 27);
                Thread.sleep(500);
            }
            //result = mFairy.findPic(908, 489, 1046, 534, 10, 1, "task.png", tlbb.GetCaptureRaw());
            result = publicFunction.localFindPic(908, 489, 1046, 534, "task.png");
            if (result.sim >= 0.6) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--task>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 1, result.y + 1);
                Thread.sleep(500);
            }

            result = publicFunction.localFindPic(1182, 20, 1249, 84, "skip.png");
            if (result.sim >= 0.6) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--skip>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(500);
            }
            //result = mFairy.findPic(774, 431, 1017, 589, 10, 1, "equipment.png", tlbb.GetCaptureRaw());
            result = publicFunction.localFindPic(848, 490, 934, 538, "equipment.png");
            if (result.sim >= 0.9) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--equipment>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 20);
                Thread.sleep(500);
            }
            // result = mFairy.findPic(774, 431, 1017, 589, 10, 1, "use.png", tlbb.GetCaptureRaw());
            result = publicFunction.localFindPic(848, 490, 934, 538, "use.png");
            if (result.sim >= 0.9) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--use>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 20);
                Thread.sleep(500);
            }

            result = publicFunction.localFindPic(774, 431, 1017, 589, "activation.png");
            if (result.sim >= 0.6) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--complete>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 20);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(359, 582, 475, 631, "receive.png");
            if (result.sim >= 0.6) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--receive>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 20);
                Thread.sleep(200);
            }
            result = publicFunction.localFindPic(469, 369, 574, 413, "complete.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--complete完成科举>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 100, result.y + 20);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(618, 17, 693, 61, "examination.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--examination科举窗口>" + result);
                publicFunction.rndTap(560, 253, 651, 293);//选A
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(618, 17, 693, 61, "shop.png");
            if (result.sim >= 0.8) {
                Thread.sleep(500);
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--shop.png商店窗口>" + result);
                result = publicFunction.localFindPic(164, 103, 1153, 563, "demand.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------SingleTask--demand.png>" + result);
                    publicFunction.rndTap(result.x + 300, result.y + 45, result.x + 310, result.y + 50);
                    Thread.sleep(1500);

                    findResult = mFairy.findPic(576,448,702,495,"ok1.png");
                    mFairy.onTap(0.8f,findResult,"确定",1000);
                }
            }
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--determine>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 100, result.y + 20);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(602, 530, 683, 576, "submit.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--determine>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 90, result.y + 20);
                Thread.sleep(500);
            }

            findResult = mFairy.findPic(400, 5, 500, 100, "wipe1.png");

            //result = publicFunction.localToValueFindPic(421, 29, 462, 65, 200, 255, 0, );
            if (findResult.sim >= 0.8f) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--wipe1>" + findResult);
                int x = 249;
                int y = 164;
                mFairy.touchDown(x, y);
                for (int i = 0; i < 9; i++) {
                    int x1 = x + 100 * i;
                    for (int j = 0; j < 2; j++) {
                        int y1 = y + 460 * j;
                        mFairy.touchMove(x1, y1, 100);
                    }
                }
                mFairy.touchUp();
                Thread.sleep(500);
            }
            result = publicFunction.localOptimalFindPic(448, 26, 568, 71, 200, 255, 0, "according.png");
//            AtFairy2.OpencvResult result1=publicFunction.localFindPicHLS();
           dian();


            result = publicFunction.localToValueFindPic(3, 267, 254, 314, 200, 255, 0, "upgradingGrade.png");
            //LtLog.i(publicFunction.getLineInfo() +  "--------------------------SingleTask-等级上限,不能继续任务-upgradingGrade>" + result);
            if (result.sim >= 0.65) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask-等级上限,不能继续任务-upgradingGrade>" + result);
                return 99;
            }
            result = publicFunction.localFindPic(421, 319, 557, 448, "team3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask-主线任务需要组队，结束-team3>" + result);
                return 99;
            }
            result = publicFunction.localOptimalFindPic(547, 31, 589, 73, 200, 255, 0, "picture.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask-picture>" + result);
                sister();
            }

            hand();
            mainTsak_TYX();
        }

        return 0;
    }

    public void mainTsak_TYX() throws Exception {

        Boolean st = false;
        result = publicFunction.localFindPic(576, 0, 704, 117, "TYX.png");
        if (result.sim >= 0.8) {
//            publicFunction.rndTapWH(result.x,result.y,28,30);
//            Thread.sleep(500);
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--TYX>" + result);
        } else {
            return;
        }
        for (int i = 0; i < 10; i++) {
            mFairy.touchDown(604, 547);
            mFairy.touchMove(624, 547, 100);
            mFairy.touchUp();
            Thread.sleep(1000);
            result = publicFunction.localFindPic(586, 479, 692, 559, "TYX1.png");
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--TYX1>" + result);
            if (result.sim >= 0.9) {
                st = true;
                break;
            }
            result = publicFunction.localFindPic(576, 0, 704, 117, "TYX.png");
            if (result.sim < 0.8) {
                return;
            }
        }

        for (int i = 0; i < 10 && st == true; i++) {
            mFairy.touchDown(612, 440);
            mFairy.touchMove(666, 440, 100);
            mFairy.touchUp();
            Thread.sleep(1000);
            result = publicFunction.localFindPic(594, 322, 686, 399, "TYX2.png");
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--TYX2>" + result);
            if (result.sim >= 0.9) {

                break;
            }
            result = publicFunction.localFindPic(576, 0, 704, 117, "TYX.png");
            if (result.sim < 0.8) {
                return;
            }
        }

    }

    private void hand() throws Exception {
        result = publicFunction.localFindPic(513, 565, 587, 644, "hand.png");
        if (result.sim >= 0.9) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--hand>" + result);
            publicFunction.rndTap(499, 532, 538, 578);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(1046, 496, 1106, 561, "hand.png");

        if (result.sim >= 0.9) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask--hand>" + result);
            publicFunction.rndTap(1033, 458, 1058, 482);
            Thread.sleep(500);
        }
    }

    private void sister() throws Exception {
        //神仙姐姐拼图
        mFairy.touchDown(229, 212);
        Thread.sleep(200);
        mFairy.touchMove(578, 215, 200);
        mFairy.touchUp();
        Thread.sleep(200);
        mFairy.touchDown(254, 481);
        Thread.sleep(200);
        mFairy.touchMove(563, 500, 200);
        mFairy.touchUp();
        Thread.sleep(200);

        mFairy.touchDown(1022, 365);
        Thread.sleep(200);

        mFairy.touchMove(748, 324, 200);
        mFairy.touchUp();
        Thread.sleep(200);

    }

    //科举
    public void Examination(PublicFunction publicFunction) throws Exception {
        // List<String> listRegion = new ArrayList<>();
        List<String> listRegion = new ArrayList<>();
        List<String> listPic = new ArrayList<>();
        listRegion.add("607,367,676,414");
        listPic.add("examination1.png");
        publicFunction.localMultiRegionFindManyPic(listRegion, listPic);
        result = publicFunction.localFindPic(618, 17, 693, 61, "examination.png");
        if (result.sim >= 0.8) {
            //Answer answer = new Answer(mFairy);
            /*Map<String, List<Integer>> answerMap = answer.initializationMap();
            answerMap.put("game_id", Arrays.asList(141));
            answerMap.put("title_Range", Arrays.asList(474, 157, 408, 46));
            answerMap.put("answer_Range", Arrays.asList(519, 228, 864, 225, 522, 360, 862, 361, 200, 86));
            answerMap.put("title_toValue_min", Arrays.asList(30, 10, 0));
            answerMap.put("title_toValue_max", Arrays.asList(110, 90, 60));
            answerMap.put("answer_toValue_min", Arrays.asList(200, 200, 200));
            answerMap.put("answer_toValue_max", Arrays.asList(255, 255, 255));
            answerMap.put("pic_range", Arrays.asList(463, 207, 1153, 472));
            answerMap.put("right_pic_Relative_range", Arrays.asList(-200, 0, 237, 40));
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask-Examination-examination科举窗口>" + result);*/

            for (int i = 0; i < 20; i++) {
                Thread.sleep(3000);
                mFairy.onTap(519, 228, 530, 230, "A", 1000);

                result = publicFunction.localFindPic(618, 17, 693, 61, "examination.png");
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask-Examination-examination科举窗口>" + result);
                if (result.sim < 0.8) {
                    break;
                }
                result = publicFunction.localFindPic(746, 223, 908, 269, "completeExamination.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------SingleTask-Examination-completeExamination>" + result);
                    mFairy.ocrRelease();
                    break;
                }
            }
//            publicFunction.rndTap(560, 253, 651, 293);//选A
////            sendExamination(str1);
//            Thread.sleep(500);
        }

        findResult = mFairy.findPic(579,168,895,275, "completeExamination.png");
        if (findResult.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask-Examination-completeExamination>" + result);
            for (int i = 0; i < 5; i++) {
                mFairy.onTap(229, 346, 297, 381,"",1000); //点领取
            }

            Thread.sleep(2000);

            publicFunction.rndTap(1208, 65, 1227, 86);//点关闭窗口




            Thread.sleep(500);
        }
    }

    private void consumeFragment() throws Exception {
        //消耗英雄碎片
        for (int i = 0; i < 3; i++) {
            gamePublicFunction.closeWindow();
            Thread.sleep(1000);
        }
        for (int i = 0; i < 3; i++) {
            gamePublicFunction.switchSkillOrSet("set");
            result = publicFunction.localFindPic(1020, 628, 1097, 701, "heroChart.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--heroChart>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 20);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(882, 521, 1045, 570, "consume.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask--consume>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 20);
                Thread.sleep(500);
                break;
            }
            Thread.sleep(500);
        }
        gamePublicFunction.closeWindow();
    }

    public int takePrize() throws Exception {
        //领奖 签到
        for (int i = 0; i < 3; i++) {
            gamePublicFunction.closeWindow();
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(653,1,1104,96, "activity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------activity>" + result);
                publicFunction.rndTap(836, 35, 863, 63);//打开福利
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(611, 10, 709, 62, "welfare.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------welfare>" + result);
                break;
            }
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(611, 10, 709, 62, "welfare.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------welfare>" + result);
            return 0;
        }
        result = publicFunction.localFindPic(822, 24, 954, 72, "sign.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------sign>" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 30);
            Thread.sleep(500);
        }
        PublicFunction.Coordinate coordinate;
//        list = new ArrayList();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                list.add((400 + (i * 109)) + "," + (187 + (j * 109)));
            }
        }
        coordinate = publicFunction.judgeListColorChange(list, 1000);
        LtLog.i(publicFunction.getLineInfo() + "------------" + "--------------json....." + coordinate);
        mFairy.tap(coordinate.x + 20, coordinate.y);
        Thread.sleep(1000);
        AtFairy2.OpencvResult result1;
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(595, 314, 647, 364, "spot.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SingleTask-luckDraw-spot>" + result);
                publicFunction.rndTapWH(result.x, result.y, 47, 48);
                Thread.sleep(10000);
            }
            //601,514,706,558
            result1 = publicFunction.localFindManyPic(601, 514, 706, 558, new String[]{"luckdraw.png", "luckdraw1.png"});
            LtLog.i(publicFunction.getLineInfo() + "------SingleTask-luckDraw-luckdraw>" + result);
            if (result1.sim >= 0.8) {
                publicFunction.rndTapWH(result1.x, result1.y, 47, 20);
                Thread.sleep(5000);
            }
            if (result.sim < 0.8 || result1.sim < 0.8) {
                break;
            }
        }

        result = publicFunction.localFindPic(138, 70, 339, 439, "gift.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------gift>" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 30);
            Thread.sleep(2000);
            result = publicFunction.localFindPic(916, 594, 1073, 661, "integral.png");
            LtLog.i(publicFunction.getLineInfo() + "-------integral>" + result);
            if (result.sim >= 0.8) {
                //宝箱全部点一遍，，领奖
                publicFunction.rndTap(989, 510, 1008, 534);
                Thread.sleep(500);
                publicFunction.rndTap(483, 510, 504, 531);
                Thread.sleep(500);
                publicFunction.rndTap(584, 505, 605, 525);
                Thread.sleep(500);
                publicFunction.rndTap(703, 508, 726, 527);
                Thread.sleep(500);

            }
        }


        result = publicFunction.localFindPic(181, 81, 297, 634, "onLine.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------onLine>" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 30);
            Thread.sleep(500);
        }
        for (int i = 0; i < 3; i++) {
            result = publicFunction.localFindPic(639, 395, 873, 490, "bless2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------bless2>" + result);
                break;
            }
            for (int j = 0; j < 20; j++) {
                result = publicFunction.localFindPic(639, 395, 873, 490, "bless2.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------bless2>" + result);
                    break;
                }
                result = publicFunction.localFindPic(639, 395, 873, 490, "bless.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------bless>" + result);
                    publicFunction.rndTap(740, 328, 779, 363);
                    Thread.sleep(1000);
                }
                Thread.sleep(500);
            }
        }
        gamePublicFunction.closeWindow();
        tongSigning();
        return 0;
    }

    //帮会签到
    private void tongSigning() throws Exception {
        for (int i = 0; i < 4; i++) {
            gamePublicFunction.switchSkillOrSet("set");
            result = publicFunction.localFindPic(932, 179, 1267, 702, "tong2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------tong2>" + result);
                publicFunction.rndTapWH(result.x, result.y, 29, 32);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(799, 592, 1164, 713, "goTong.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------goTong>" + result);
                //发现回到帮会，证明已经在帮会界面，点击福利
                publicFunction.rndTap(73, 415, 96, 460);
                Thread.sleep(3000);
            }

            result = publicFunction.localFindPic(964, 89, 1177, 183, "tong_signing.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------tong_signing>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 20);
                Thread.sleep(3000);
            }
            Thread.sleep(2000);
        }
        gamePublicFunction.closeWindow();


    }

    public void BattleDonation() throws Exception {
        AtFairy2.OpencvResult result, result1;
        LtLog.i(publicFunction.getLineInfo() + "-------copper=" + TaskMain.copper + ",silver=" + TaskMain.silver);

        if (TaskMain.copper == 0 && TaskMain.silver == 0) {
            return;
        }
        MatTime matTime = new MatTime(mFairy);
        long sleep = 0;
        gamePublicFunction.goTong();

        for (int i = 0; i < 300; i++) {
            sleep = matTime.mMatTime(1029, 41, 220, 653, 0.9);
            LtLog.i(publicFunction.getLineInfo() + "-------sleep=" + sleep);
            if (sleep >= 10) {
                gamePublicFunction.openMap("current");
                Thread.sleep(1000);
                matTime.resetTime();
            }
            result = publicFunction.localFindPic(545, 0, 719, 102, "tong3.png");
            result1 = publicFunction.localFindPic(733, 572, 1190, 701, "goTo.png");
            if (result.sim >= 0.8 && result1.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------tong3=" + result);
                publicFunction.rndTap(950, 161, 994, 180);// 点击 活动
                Thread.sleep(1000);
                for (int j = 0; j < 5; j++) {
                    result = publicFunction.localFindPic(793, 209, 940, 597, "BattleDonation.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "-------BattleDonation=" + result);
                        publicFunction.rndTapWH(result.x, result.y, 90, 20);
                        Thread.sleep(1000);
                        gamePublicFunction.closeWindow();
                        break;
                    } else {
                        //游戏版本更新后这个滑动应该没用了，有待观察
                        publicFunction.RanSwipe(884, 236, 1034, 565, 2, 500);
                        Thread.sleep(1000);
                    }
                }
            } else if (result1.sim >= 0.8) {
                gamePublicFunction.closeWindow();
                gamePublicFunction.goTong();
            }
            result = publicFunction.localFindPic(500, 329, 687, 451, "BattleDonation1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------BattleDonation1=" + result);
                publicFunction.rndTapWH(result.x, result.y, 87, 22);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(546, 0, 762, 101, "BattleDonation2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------BattleDonation2=" + result);
                result = publicFunction.localFindPic(565, 489, 686, 555, "donation.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------donation=" + result);
                    for (int j = 0; j < TaskMain.copper; j++) {
                        publicFunction.rndTapWH(result.x, result.y, 48, 23);
                        Thread.sleep(500);
                    }
                }
                result = publicFunction.localFindPic(791, 492, 893, 551, "donation.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------donation=" + result);
                    for (int j = 0; j < TaskMain.silver; j++) {
                        publicFunction.rndTapWH(result.x, result.y, 48, 23);
                        Thread.sleep(500);
                    }
                }
                gamePublicFunction.closeWindow();
                return;
            }
            Thread.sleep(1000);
        }
    }

    public void sendExamination(String mToken) throws Exception {
        for (int i = 0; i < 50; i++) {
            result = publicFunction.localFindPic(475, 220, 1119, 453, "cancel2.png");
            if (result.sim >= 0.8) {
                String range = "";
                if (result.x >= 475 && result.x <= 781 && result.y >= 219 && result.y <= 322) {
                    //                    475,219,781,322
//                    range = new int[]{499,264,689,280};
                    range = "499,264,689,280,A";
                } else if (result.x >= 815 && result.x <= 1120 && result.y >= 221 && result.y <= 322) {
//                        815,221,1120,322
//                    range = new int[]{839,261,1045,281};
                    range = "839,261,1045,281,B";
                } else if (result.x >= 476 && result.x <= 781 && result.y >= 353 && result.y <= 452) {
//                        476,353,781,452
//                    range = new int[]{501,396,732,413};
                    range = "501,396,732,413,C";
                } else if (result.x >= 817 && result.x <= 1122 && result.y >= 356 && result.y <= 453) {
//                        817,356,1122,453
//                    range = new int[]{840,395,1018,415};
                    range = "840,395,1018,415,D";
                }
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd|HHmmss");
                String date = sDateFormat.format(new java.util.Date());
                LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------....." + date.split("\\|"));
                String[] dataTime = date.split("\\|");
                String keyStr = dataTime[0] + "_141_" + range + "_" + dataTime[1] + ".png";
                LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------....." + keyStr);
                String filePath = "/sdcard/screen.png";
//                if(publicFunction.fileIsExists(filePath)){
//                    publicFunction.fileDelete(filePath);
//                }
//                Imgcodecs.imwrite(filePath,mMat);
//                for (int j = 0; j <20 ; j++) {
//                    if(publicFunction.fileIsExists(filePath)){
//                            break;
//                    }
//                    Thread.sleep(100);
//                }

                publicFunction.httpPost(filePath, keyStr, mToken);
                publicFunction.fileDelete(filePath);
                return;
            }
            Thread.sleep(100);
        }
    }

    public void sendTongTask(String mToken) throws Exception {
        for (int i = 0; i < 50; i++) {
            result = publicFunction.localFindPic(346, 309, 935, 500, "cancel3.png");
            LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------cancel2=" + result);
            if (result.sim >= 0.8) {
                String range = "";
                //344,311,625,386
                if (result.x >= 344 && result.x <= 625 && result.y >= 311 && result.y <= 386) {
                    range = "355,339,562,358,A";
                } else if (result.x >= 656 && result.x <= 934 && result.y >= 308 && result.y <= 385) {
//                    656,308,934,385
                    range = "666,339,875,357,B";
                } else if (result.x >= 345 && result.x <= 622 && result.y >= 423 && result.y <= 498) {
//                    345,423,622,498
                    range = "354,450,601,471,C";
                } else if (result.x >= 657 && result.x <= 934 && result.y >= 423 && result.y <= 499) {
//                    657,423,934,499
                    range = "664,450,874,469,D";
                }
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd|HHmmss");
                String date = sDateFormat.format(new java.util.Date());
                LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------....." + date.split("\\|"));
                String[] dataTime = date.split("\\|");
                String keyStr = dataTime[0] + "_142_" + range + "_" + dataTime[1] + ".png";
                LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------....." + keyStr);
                String filePath = "/sdcard/screen.png";
                publicFunction.httpPost(filePath, keyStr, mToken);
                publicFunction.fileDelete(filePath);
                return;
            }
            Thread.sleep(100);
        }
    }

    private String game_id = "141";

}

