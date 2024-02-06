package com.script.fairy;

import com.example.publicfunctionlibrary.FunctionClass;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.ScreenInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/6/20.
 */

public class SingleTask {

    private AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    private GamePublicFunction gamePublicFunction;
    private Random random = new Random(100);
    private MatTime matTime;
    FindResult findResult;
    protected List<String> taskList = new ArrayList<>();
    protected FunctionClass functionClass;

    public SingleTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
//        publicFunction=TaskMain.publicFunction;
        gamePublicFunction = new GamePublicFunction(mFairy);
        matTime = new MatTime(mFairy);
        functionClass = new FunctionClass(mFairy, mFairy.getContext());
    }

    public int mSingleTask() throws Exception {

        AtFairy2.OpencvResult result;
        long timex = 0;


        LtLog.i(publicFunction.getLineInfo() + "-------commerceNotTeam > " + TaskMain.taskMap.get("commerceNotTeam"));

        gamePublicFunction.closeWindow();

        if (TaskMain.taskMap.get("commerceNotTeam") == 0) {
            //  commerceNotTeam     1 不退  0 退队
            gamePublicFunction.exitTeam(); //退出当前队伍
        }

        findResult = mFairy.findPic(991, 29, 1122, 97, "jiayuan1.png");
        mFairy.onTap(0.8f, findResult, 1007, 222, 1024, 234, "离开家园", 5000);

        findResult = mFairy.findPic(1035,130,1187,360,"li3.png");
        mFairy.onTap(0.8f, findResult, "离开", 1000);

        gamePublicFunction.goSecurity();//回安全区

        taskList = setTaskList();

        LtLog.i(publicFunction.getLineInfo() + "【 timex=" + timex + " , 任务list:" + taskList + " 】");

        gamePublicFunction.closeWindow();
        gamePublicFunction.taskOrTeam("task");

        gamePublicFunction.goMainCity();
        gamePublicFunction.openActivity();

        if (TaskMain.taskMap.get("patrons") > 0) {
            //门客任务
            patronsTask();
        }

        wsd = true;//武神殿领奖
        tlqm_num=1;//田连阡陌
        while (mFairy.condit()) {

            if (Math.abs(random.nextInt()) % 100 >= 20) {
                LtLog.i(publicFunction.getLineInfo() + "【任务list:" + taskList + " 】");
            }

            result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
            if (result.sim >= 0.8) {

                LtLog.i(publicFunction.getLineInfo() + "【在主场景】");

                gamePublicFunction.taskOrTeam("task");

                timex = mFairy.mMatTime(1173, 134, 76, 23, 0.9f);
            }

            if (timex >= 120) {
                LtLog.i(publicFunction.getLineInfo() + "超时打开活动：" + timex);
                gamePublicFunction.openActivity();

                tlqm_num=1;//田连阡陌

                mFairy.initMatTime();
                timex = 0;
            }

            if (taskList.size() > 0) {

                if (taskList.get(0).equals("wsxyx")) {
                    sgame();
                    taskList.remove(0);
                    gamePublicFunction.closeWindow();
                    gamePublicFunction.goMainCity();
                    gamePublicFunction.openActivity();
                    mFairy.initMatTime();

                } else {

                    taskList = gamePublicFunction.lookupTask(taskList);//判断活动状态

                    LtLog.i(publicFunction.getLineInfo() + "【任务list: " + taskList + "】");

                    if (taskList.size() == 0) {
                        return 99;
                    }

                    LtLog.i(publicFunction.getLineInfo() + "每个任务特有的东西:" + taskList.get(0) + " 超时时间：" + timex);
                    selectTask(taskList.get(0));//每个任务特有的东西
                    LtLog.i(publicFunction.getLineInfo() + "每个任务特有的东西 - 结束");
                }
            } else {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  任务完成");
                gamePublicFunction.closeWindow();
                gamePublicFunction.goMainCity();
                gamePublicFunction.closeWindow();
                return 99;
            }
            Thread.sleep(100);
        }
        return 99;
    }

    public int mainTask() throws Exception {
        AtFairy2.OpencvResult result;
        boolean skillState = false;
        int maxTime = 0;
        long timex = 0;
        gamePublicFunction.taskOrTeam("task");

        while (mFairy.condit()) {

            findResult = mFairy.findPic(403, 229, 881, 407, "yao.png");
            mFairy.onTap(0.8f, findResult, 497, 446, 519, 452, "拒绝邀请", 1000);

            findResult = mFairy.findPic("nn1.png");
            mFairy.onTap(0.8f, findResult, 1211, 18, 1233, 38, "服饰关闭", 1000);

            result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
            if (result.sim >= 0.8) {
                timex = mFairy.mMatTime(1173, 134, 76, 23, 0.9f);
            }

            result = publicFunction.localFindPic(1039, 252, 1160, 375, "automatic.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【有自动图标 maxTime:" + maxTime + "】");
                maxTime = 30;
            } else {
                maxTime = 8;
            }

            if (timex >= maxTime) {
                result = publicFunction.localFindPic(0, 160, 85, 323, "main.png");
                LtLog.i(publicFunction.getLineInfo() + "【主线sim:" + result.sim + "】");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------main>" + result);
                    publicFunction.rndTapWH(result.x, result.y, 20, 20);
                    mFairy.initMatTime();
                    timex = 0;
                    Thread.sleep(500);
                }
            }


            result = publicFunction.localFindPic(1043, 28, 1187, 148, "jump2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------jump2=" + result);
                publicFunction.rndTapWH(result.x, result.y, 44, 20);
                Thread.sleep(2000);
                result = publicFunction.localFindPic(693, 388, 842, 513, "agree.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------agree=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 49, 25);
                    Thread.sleep(500);
                }

            }
            result = publicFunction.localFindPic(0, 28, 118, 171, "companion1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------companion1=" + result);
                for (int i = 0; i <= 3; i++) {
                    result = publicFunction.localFindPic(118, 100 + (i * 101), 175, 165 + (i * 101), "tick1Off.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------tick1Off=" + result);
                        mFairy.tap(result.x + 5, result.y + 5);
                        Thread.sleep(1000);
                    }
                }
                gamePublicFunction.closeWindow();
            }

            result = publicFunction.localFindPic(24, 84, 88, 315, "tongban.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【同伴界面】");
                Thread.sleep(2000);
                publicFunction.rndTap(140, 126, 157, 142);
                Thread.sleep(2000);
                publicFunction.rndTap(1207, 25, 1223, 43);
            }


            result = publicFunction.localFindPic(238, 210, 395, 553, "chaozhi.png");
            if (result.sim >= 0.8) {
                publicFunction.rndTap(1115, 162, 1130, 177);
                Thread.sleep(1000);
            }

            FindResult resultf = mFairy.findPic("nnclose.png");
            mFairy.onTap(0.8f, resultf, "关闭 残卷", 1000);


            result = publicFunction.localFindPic(1117, 0, 1280, 81, "cavern.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------cavern=" + result);
                result = gamePublicFunction.leave();
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------leave=" + result);
                    mainTaskSnowberg();
                }
            }
            result = publicFunction.localFindPic(561, 622, 740, 720, "return.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------return=" + result);
                gamePublicFunction.closeWindow();
            }
            result = publicFunction.localFindPic(0, 28, 113, 173, "task2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------task2=" + result);
                gamePublicFunction.closeWindow();
            }
            result = gamePublicFunction.leave();
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------leave=" + result);
                gamePublicFunction.manualOrAutomatic("automatic");
                if (!skillState) {
                    skillState = true;
                    gamePublicFunction.setSkill();
                }
            }

            result = publicFunction.localFindPic(7, 31, 85, 338, "book3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------book3=" + result);
                //关闭
                publicFunction.rndTap(1205, 24, 1234, 53);
                Thread.sleep(1500);
            }

            result = publicFunction.localFindPic(1114, 0, 1280, 80, "snowberg.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------snowberg=" + result);
                mainTaskSnowberg();
            }
            result = publicFunction.localFindPic(1091, 0, 1280, 82, "skip.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  skip=" + result);
                publicFunction.rndTapWH(result.x, result.y, 112, 22);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(171, 585, 299, 713, "task1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  task1=" + result);
                publicFunction.rndTapWH(result.x, result.y, 28, 28);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(150, 38, 1112, 631, "goTo1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------goTo1=" + result);
                publicFunction.rndTapWH(result.x, result.y, 45, 26);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(973, 498, 1129, 625, "accouter.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------accouter=" + result);
                publicFunction.rndTapWH(result.x, result.y, 56, 27);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(364, 491, 492, 616, "determine.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------determine=" + result);
                publicFunction.rndTapWH(result.x, result.y, 28, 25);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(1090, 607, 1218, 720, "determine.png");

            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "------determine=" + result);
                publicFunction.rndTapWH(result.x, result.y, 28, 25);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(403, 514, 530, 641, "zhao.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------zhao=" + result);
                publicFunction.rndTapWH(result.x, result.y, 27, 27);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(533, 387, 745, 514, "companion3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------companion3=" + result);
                publicFunction.rndTapWH(result.x, result.y, 112, 27);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(0, 191, 106, 309, "mainOff.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------mainOff=" + result);
                return 99;
            }
            result = publicFunction.localFindPic(10, 191, 178, 308, "mainOff1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------mainOff1=" + result);
                return 99;
            }

            findResult = mFairy.findPic(20, 190, 150, 428, "nnGrade.png");
            if (findResult.sim > 0.8f) {
                LtLog.i(publicFunction.getLineInfo() + "End!");
                return 99;
            }

            Thread.sleep(100);

        }
        return 99;
    }

    private void mainTaskSnowberg() throws Exception {
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(421, 355, 570, 478, "left.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------left=" + result);
                mFairy.touchDown(156, 578);
                mFairy.touchMove(352, 558, 700);
                mFairy.touchUp();
            }
            result = publicFunction.localFindPic(150, 38, 1112, 631, "goTo1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------goTo1=" + result);
                publicFunction.rndTapWH(result.x, result.y, 45, 26);
                Thread.sleep(1000);
            } else {
//                publicFunction.rndTap(1000, 426, 1037, 453);
//                Thread.sleep(200);
                for (int j = 0; j < 5; j++) {
                    publicFunction.rndTap(1153, 594, 1200, 638);
                    Thread.sleep(200);
                }
            }
            Thread.sleep(100);
        }

    }

    private List<String> setTaskList() throws Exception {
        //设置 能活动里能找到的任务
        // 执行 活动窗口中不能找到的任务
        LtLog.i(publicFunction.getLineInfo() + "-------goSecurity>");
//        gamePublicFunction.goSecurity();

        List<String> list = new ArrayList<>();

        if (TaskMain.taskMap.get("Hero") == 1) {
            list.add("Hero");
        }
        if (TaskMain.taskMap.get("Valkyrie") == 1) {
            list.add("Valkyrie");
        }

        if (AtFairyConfig.getOption("zjwsd").equals("1")) {
            list.add("zjwsd");
        }

        if (TaskMain.taskMap.get("commerce") == 1) {
            list.add("commerce");
        }
        if (TaskMain.taskMap.get("meditation") == 1) {
            list.add("meditation");
        }
        if (TaskMain.taskMap.get("FamilyMuse") == 1) {
            FamilyMuse();
        }
        if (TaskMain.taskMap.get("treasure") == 1) {
            list.add("treasure");
        }


        if (AtFairyConfig.getOption("wsxyx").equals("1")) {
            list.add("wsxyx");
        }

        if (AtFairyConfig.getOption("zhanlong").equals("1")) {
            list.add("zhanlong");
        }

        if (AtFairyConfig.getOption("tlqm").equals("1")) {//田连阡陌
            list.add("tlqm");
        }


        if (TaskMain.taskMap.get("Acer") == 1 || TaskMain.taskMap.get("BackReward") == 1 || TaskMain.taskMap.get("offline") == 1 || TaskMain.taskMap.get("hutSign") == 1) {
            acerAndBackRewardAndOffline();
        }
        if (TaskMain.taskMap.get("five") == 1 || TaskMain.taskMap.get("ten") == 1) {
            familyDonate();
        }
        if (TaskMain.taskMap.get("crystal") == 1
                || TaskMain.taskMap.get("antique_white") == 1
                || TaskMain.taskMap.get("antique_green") == 1
                || TaskMain.taskMap.get("antique_blue") == 1
                || TaskMain.taskMap.get("book") == 1
                || TaskMain.taskMap.get("soul_green") == 1
                || TaskMain.taskMap.get("soul_blue") == 1
                || TaskMain.taskMap.get("soul_purple") == 1
                || TaskMain.taskMap.get("ExpMedicine") == 1
                || TaskMain.taskMap.get("goldBox") == 1
                || TaskMain.taskMap.get("ZQMedicine") == 1
                || AtFairyConfig.getOption("zbg1").equals("1")
                || AtFairyConfig.getOption("zbg2").equals("1")
                || AtFairyConfig.getOption("goujia1").equals("1")
                || AtFairyConfig.getOption("goujia2").equals("1")
                || AtFairyConfig.getOption("goujia3").equals("1")
                || AtFairyConfig.getOption("goujia4").equals("1")
                || AtFairyConfig.getOption("goujia5").equals("1")
                || AtFairyConfig.getOption("goujia6").equals("1")
                || AtFairyConfig.getOption("goujia7").equals("1")
                || AtFairyConfig.getOption("goujia8").equals("1")
                || AtFairyConfig.getOption("goujia9").equals("1")
        ) {
            familyTreasure();
        }
        if (TaskMain.taskMap.get("stall") == 1) {
            stall();
        }
        LtLog.i(publicFunction.getLineInfo() + "-------list>" + list);
        return list;
    }

    //门客任务
    private void patronsTask() throws Exception {
        //门客任务与其他单人任务不一样，门客任务不能通过活动页引导完成，需要进入家园，在家园中点门客领取，并点击左侧任务栏的任务才能引导

        LtLog.i(publicFunction.getLineInfo() + "【开始门客任务】");

        AtFairy2.OpencvResult result;
        MatTime matTime = new MatTime(mFairy);
        long sleepTime = 0;
        int patrons = TaskMain.taskMap.get("patrons"); //第几个门客执行任务
        int taskStart = 0;
        int second = 0;
        while (mFairy.condit()) {

            sleepTime = matTime.mMatTime(1177, 137, 68, 19, 0.9f);
            if (Math.abs(random.nextInt()) % 100 >= 70) {
                LtLog.i(publicFunction.getLineInfo() + "------->sleepTime=" + sleepTime);
            }

            sleepTime = matTime.mMatTime(1177, 137, 68, 19, 0.9f);
            if (sleepTime >= 60) {
                // 30,315,112,424

                LtLog.e(publicFunction.getLineInfo() + "sleepTime 超时 ");

                result = publicFunction.localFindPic(0, 201, 51, 434, "patrons1.png");
                LtLog.i(publicFunction.getLineInfo() + "--------------------  patrons1=" + result);
                if (result.sim >= 0.75) {
                    mFairy.touchDown(151, 581);
                    mFairy.touchMove(314, 551, 0);
                    mFairy.touchUp();
                    Thread.sleep(1000);//有可能一直被怪打导致传送打断，所以先控制方向键移动一下
                    publicFunction.rndTapWH(result.x, result.y, 36, 23);
                    Thread.sleep(500);
                } else {
                    result = publicFunction.localFindPic(949, 163, 1093, 284, "leave-5.png");
                    LtLog.i(publicFunction.getLineInfo() + "离开标志sim：" + result.sim);
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "离开");
                        publicFunction.rndTapWH(result.x, result.y, 44, 21);
                        Thread.sleep(10000);
                    }


                    if (second >= 4) {
                        LtLog.i(publicFunction.getLineInfo() + "---------从上往下滑动=" + result);
                        publicFunction.RanSwipe(98, 300, 118, 417, 0, 500);
                    } else {
                        LtLog.i(publicFunction.getLineInfo() + "---------从下往上滑动=" + result);
                        publicFunction.RanSwipe(98, 300, 118, 417, 2, 500);
                    }


                    /*if (second >= 4) {
                        LtLog.i(publicFunction.getLineInfo() + "---------从上往下滑动=" + result);
                        publicFunction.RanSwipe(233, 108, 719, 393, 0, 500);
                    } else {
                        LtLog.i(publicFunction.getLineInfo() + "---------从下往上滑动=" + result);
                        publicFunction.RanSwipe(233, 108, 719, 393, 2, 500);
                    }*/
                    second = second + 1;
                    if (second > 8) {
                        second = 0;
                    }
                    gamePublicFunction.openActivity();
                }
                matTime.resetTime();
            }

            result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "主场景");
                gamePublicFunction.taskOrTeam("task");
            }

            result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  activity1=" + result);
                taskStart = patronsTaskLookupTask();


                if (taskStart == 1) {
                    LtLog.i(publicFunction.getLineInfo() + "--------------------  taskStart == 1");


                    findResult = mFairy.findPic("li2.png");
                    mFairy.onTap(0.8f, findResult, "关闭活动栏", 2000);

                    findResult = mFairy.findPic("jiayuan2.png");
                    mFairy.onTap(0.8f, findResult, "row", 2000);


                    findResult = mFairy.findPic("li1.png");
                    mFairy.onTap(0.8f, findResult, "离开家园", 1000);


                } else if (taskStart == 2) {
                    LtLog.i(publicFunction.getLineInfo() + "--------------------  patronsTask  over=");
                    gamePublicFunction.closeWindow();
                    break;
                } else if (taskStart == 3) {
                    //门客任务已接取
                    LtLog.i(publicFunction.getLineInfo() + "--------------------Already exist=");
                    gamePublicFunction.closeWindow();
                    Thread.sleep(2000);
                    result = publicFunction.localFindPic(0, 216, 52, 442, "patrons1.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "--------------------  patrons1=" + result);
                        publicFunction.rndTapWH(result.x, result.y, 36, 23);
                        Thread.sleep(500);
                    }
                }
            }

            result = publicFunction.localFindPic(0, 29, 117, 172, "home.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------home=" + result);
                result = publicFunction.localFindPicHLS(256, 131 + ((patrons - 1) * 104), 378, 191 + ((patrons - 1) * 104), "patrons_receive.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------------------- " + patrons + ", patrons_receive=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 80, 20);
                    for (int i = 0; i < 50; i++) {
                        //点击参加，会进入家园。进入家园后，把游戏选功能栏会自动切换，循环等待切换的箭头，切换回去
                        result = publicFunction.localFindPic(1037, 0, 1192, 99, "arrow2.png");
                        if (result.sim >= 0.7) {
                            LtLog.i(publicFunction.getLineInfo() + "------->arrow2=" + result);
                            mFairy.tap(result.x + 10, result.y + 10);
                            Thread.sleep(500);
                            break;
                        }
                        Thread.sleep(100);
                    }
                } else {
                    //如果没有发现用户指定的门客  ，随意找一个领取任务，如果一个门客都没有，任务结束
                    result = publicFunction.localFindPicHLS(239, 85, 396, 507, "patrons_receive.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "--------------------  patrons_receive=" + result);
                        publicFunction.rndTapWH(result.x, result.y, 80, 20);
                        Thread.sleep(500);
                    } else {
                        //没有门客 ，退出任务
                        break;
                    }
                }
                result = publicFunction.localFindPicHLS(192, 84, 261, 507, "foot.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "--------------------  foot=" + result);
                    publicFunction.rndTapWH(result.x + 58, result.y, 20, 10);
                    Thread.sleep(2000);
                }
                gamePublicFunction.closeWindow();
            }

            result = publicFunction.localFindPic(364, 317, 582, 446, "patrons_receive1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  patrons_receive1=" + result);
                publicFunction.rndTapWH(result.x, result.y, 118, 29);
                Thread.sleep(1500);
            }
            result = publicFunction.localFindPic(670, 228, 838, 350, "friendliness.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  friendliness=" + result);
                //点同意
                publicFunction.rndTap(738, 439, 797, 464);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(1091, 0, 1280, 82, "skip.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  skip=" + result);
                publicFunction.rndTapWH(result.x, result.y, 112, 22);
                Thread.sleep(500);
            }

            Thread.sleep(1000);
        }
        //离开家园
        for (int i = 0; i < 4; i++) {
            gamePublicFunction.closeWindow();
            result = publicFunction.localFindPic(949, 163, 1093, 284, "leave-5.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------leave-5=" + result);
                publicFunction.rndTapWH(result.x, result.y, 44, 21);
                Thread.sleep(500);
            }

            Thread.sleep(1000);
        }
        gamePublicFunction.goMainCity();
        gamePublicFunction.openActivity();

    }

    public int patronsTaskLookupTask() throws Exception {
        // 返回值 0 : 不在活动窗口 ，1： 参加 ， 2： 完成 ，3： 已接取
        long time = System.currentTimeMillis() / 1000, timex = 0;
        AtFairy2.OpencvResult result, result2;

        int second = 0;
        result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "活动界面");
        } else {
            return 0;
        }
        while (mFairy.condit()) {

            LtLog.i(publicFunction.getLineInfo() + "【寻找门客任务】");

            AtFairy2.OpencvResult result1 = publicFunction.localFindPic(226, 87, 722, 414, "patrons.png");
            if (result1.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "找到门客任务");
                result = publicFunction.localFindPic(result1.x - 10, result1.y + 72, result1.x + 111, result1.y + 129, "complete1.png");
                if (result.sim >= 0.8) {

                    LtLog.i(publicFunction.getLineInfo() + "已完成");
                    return 2;
                }
                //245,171  287,205,304,229    235,243,356,300

                result2 = publicFunction.localFindPic(result1.x + 30, result1.y + 24, result1.x + 59, result1.y + 58, "patronsNumber.png");
                LtLog.i(publicFunction.getLineInfo() + "------->patronsNumber=" + result2);

                result = publicFunction.localFindPic(result1.x - 10, result1.y + 72, result1.x + 111, result1.y + 129, "attend1.png");
                if (result.sim >= 0.8 && result2.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------->attend1=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 21, 21);
                    Thread.sleep(2000);
                    return 1;
                }
                //没有完成 而且次数小于5 表示已经接取了任务，在接取任务的情况下不能点参加
                if (result.sim >= 0.8) {
                    return 3;
                }
            }

            if (second >= 4) {
                LtLog.i(publicFunction.getLineInfo() + "---------从上往下滑动=" + result);
                publicFunction.RanSwipe(233, 108, 719, 393, 0, 500);
            } else {
                LtLog.i(publicFunction.getLineInfo() + "---------从下往上滑动=" + result);
                publicFunction.RanSwipe(233, 108, 719, 393, 2, 500);
            }
            second = second + 1;
            if (second > 8) {
                second = 0;
            }
            Thread.sleep(2000);
            result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
            if (result.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->Not activity1=" + result);
                return 0;
            }
            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 120) {
                LtLog.i(publicFunction.getLineInfo() + "------->查找超时");
                return 2;
            }
            Thread.sleep(100);
        }
        return 2;
    }

    private void selectTask(String str) throws Exception {
        switch (str) {
            case "tlqm":
                tlqm();
                break;
            case "zjwsd":
                zjwsd();
                break;
            case "zhanlong":
                zhanlong();
                break;
            case "treasure":
                switchTreasure();
                treasure();
                break;
            case "Hero":
                Hero();
                break;
            case "Valkyrie":
                Valkyrie();
                break;
            case "commerce":
                commerce();
                break;
//            case "outdoorsOnHook_commerce":
//                outdoorsOnHook_commerce();
//                break;
            case "meditation":
                meditation();
                break;
        }
    }

    //重新上架
    private void stall() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "---------重新上架");
        AtFairy2.OpencvResult result;
        int index = 0;


        for (int i = 0; i < 15; i++) {


            result = publicFunction.localFindPic(682, 0, 801, 104, "stall.png" + "|" + "stall4.png" + "|" + "stall5.png");
            LtLog.i(publicFunction.getLineInfo() + "-------stall>" + result);
            if (result.sim >= 0.8) {
                publicFunction.rndTapWH(result.x, result.y, 19, 20);
                Thread.sleep(500);
            }

            result = publicFunction.localFindPic(369, 244, 555, 331, "err7.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------err7>" + result);
                publicFunction.rndTap(606, 435, 668, 461);//点确认
                Thread.sleep(1000);
            }

            result = publicFunction.localFindPic(231, 57, 435, 179, "stall2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------stall2>" + result);

                result = publicFunction.localFindPic(85, 161, 201, 274, "overtime.png");
                if (result.sim >= 0.7) {
                    LtLog.i(publicFunction.getLineInfo() + "-------overtime>" + result);
                    publicFunction.rndTapWH(result.x + 100, result.y, 16, 13);
                    Thread.sleep(1500);
                    index = 0;
                } else {
                    index = index + 1;
                }
            } else {
                result = publicFunction.localFindPic(0, 135, 106, 266, "stall1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------stall1>" + result);
                    publicFunction.rndTap(303, 106, 349, 129);//点我的摊位
                    Thread.sleep(500);
                }
            }

            result = publicFunction.localFindPic(745, 423, 874, 552, "stall3.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "-------stall3>" + result);
                publicFunction.rndTapWH(result.x, result.y, 29, 29);
                Thread.sleep(1000);
            }

            if (index >= 3) {
                break;
            }


            Thread.sleep(100);
        }


    }

    private void acerAndBackRewardAndOffline() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "---------acerAndBackRewardAndOffline=");
        AtFairy2.OpencvResult result;
        gamePublicFunction.closeWindow();

        while (mFairy.condit()) {

            LtLog.e(mFairy.getLineInfo("找福利"));

            result = publicFunction.localFindPic(840, 0, 959, 112, "welfare.png" + "|" + "welfare2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->welfare=" + result);
                publicFunction.rndTapWH(result.x, result.y, 19, 25);
                Thread.sleep(500);
            }

            findResult = mFairy.findPic(828, 8, 974, 101, "fuli.png");
            mFairy.onTap(0.8f, findResult, "福利", 1000);

            result = publicFunction.localFindPic(0, 28, 115, 192, "welfare1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->welfare1=" + result);
                break;
            }

            Thread.sleep(100);
        }

        if (TaskMain.taskMap.get("Acer") == 1) {
            Acer();
        }
        if (TaskMain.taskMap.get("offline") == 1) {
            offline();
        }
        if (TaskMain.taskMap.get("BackReward") == 1) {
            BackReward();
        }
        gamePublicFunction.closeWindow();
        if (TaskMain.taskMap.get("hutSign") == 1) {
            hutSign();
        }

    }

    private void Acer() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "【签到】");
        AtFairy2.OpencvResult result;
        //签到
        for (int i = 0; i < 4; i++) {
            result = publicFunction.localFindPic(140, 105, 300, 647, "Acer.png");
            if (result.sim >= 0.72) {
                publicFunction.rndTapWH(result.x, result.y, 5, 5);
                Thread.sleep(2000);
                break;
            } else {
                publicFunction.RanSwipe(168, 186, 237, 378, 2, 500);
                Thread.sleep(1000);
            }
        }


        List<FindResult> resultList = mFairy.findPic(338, 187, 1185, 671, 0.75f, "qian.png");
        if (resultList.size() == 0) {
            mFairy.onTap(404, 309, 413, 323, "第一次签到", 1000);
        } else {
            findResult = resultList.get(resultList.size() - 1);
            if (findResult.x > 1041) {
                mFairy.onTap(findResult.x - 685, findResult.y - 165, findResult.x - 680, findResult.y - 170, "签到", 1000);
            } else {
                mFairy.onTap(findResult.x + 115, findResult.y, findResult.x + 120, findResult.y + 1, "签到", 1000);
            }
        }


        /**
         * 签到功能
         *
         FindResult findResult = new FindResult();
         for (int i = 0; i < 20; i++) {
         FindResult fin = mFairy.findPic(328, 80, 1179, 652, "hongdian.png");
         if (fin.sim > findResult.sim) {
         findResult = fin;
         }
         }

         mFairy.onTap(findResult.x - 50, findResult.y - 30, findResult.x - 40, findResult.y - 29, "签到", 1000);
         mFairy.onTap(findResult.x - 50, findResult.y, findResult.x - 40, findResult.y + 1, "签到", 1000);
         mFairy.onTap(findResult.x - 50, findResult.y + 29, findResult.x - 40, findResult.y + 30, "签到", 1000);
         */
        LtLog.i(publicFunction.getLineInfo() + "【摇钱树】");
        //摇钱树
        for (int i = 0; i < 4; i++) {
            result = publicFunction.localFindPic(140, 105, 300, 647, "Acer1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->Acer1=" + result);
                publicFunction.rndTapWH(result.x, result.y, 70, 24);
                Thread.sleep(2000);
                break;
            } else {
                publicFunction.RanSwipe(168, 186, 237, 378, 2, 500);
                Thread.sleep(1000);
            }
        }
        result = publicFunction.localFindPic(957, 542, 1019, 601, "redDot.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->redDot=" + result);
            publicFunction.rndTapWH(result.x - 10, result.y + 10, 24, 26);
            Thread.sleep(500);
        }
    }

    //奖励找回
    private void BackReward() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "------->BackReward=");
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPic(140, 105, 300, 647, "BackReward.png");
            if (result.sim >= 0.72) {
                LtLog.i(publicFunction.getLineInfo() + "------->BackReward=" + result);
                publicFunction.rndTapWH(result.x, result.y, 92, 25);
                Thread.sleep(1000);

                for (int j = 0; j < 30; j++) {
                    result = publicFunction.localFindPic(860, 273, 1104, 396, "money.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------->money=" + result);
                        publicFunction.rndTap(1016, 355, 1060, 368);
                        Thread.sleep(1000);
                        gamePublicFunction.clickDetermine();
                    } else {
                        publicFunction.rndTap(523, 270, 577, 290);
                        Thread.sleep(2000);
                    }
                    result = publicFunction.localFindPic(939, 310, 1138, 411, "BackReward1.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------->BackReward1=" + result);
                        break;
                    }
                    Thread.sleep(100);
                }
                break;
            } else {

                mFairy.ranSwipe(168, 378, 168, 186, 1000, 1000);

                Thread.sleep(1000);
            }
        }
    }

    //在线奖励
    private void offline() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "------->offline=");
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 2; i++) {
            result = publicFunction.localFindPic(140, 105, 300, 647, "offline.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->offline=" + result);
                publicFunction.rndTapWH(result.x, result.y, 95, 27);
                Thread.sleep(500);
                break;
            } else {
                publicFunction.RanSwipe(168, 186, 237, 378, 2, 500);
                Thread.sleep(1000);
            }
        }

        result = publicFunction.localFindPic(732, 448, 860, 577, "receive1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->receive1=" + result);
            publicFunction.rndTapWH(result.x, result.y, 28, 29);
            Thread.sleep(500);
        }
    }

    //家族打坐
    private void FamilyMuse() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "---------FamilyMuse=");
        AtFairy2.OpencvResult result, result1 = null;
        int index = 0;
        while (mFairy.condit()) {

            result = publicFunction.localFindPic(1116, 0, 1280, 81, "home1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------home1.png=" + result);
                gamePublicFunction.openMapWorldOrCurrent("current");
                result = publicFunction.localFindPic(342, 57, 933, 649, "smallMap.png" + "|" + "smallMap1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------->smallMap=" + result);
                    mFairy.tap(705, 277);
                    Thread.sleep(3000);
                    gamePublicFunction.closeWindow();
                }

            } else {
                LtLog.i(publicFunction.getLineInfo() + "---------home1.png=" + result);
                goFamily();
            }


            boolean matState = publicFunction.judgeMatChange(1183, 141, 56, 14, 0.9, 3000);
            if (matState == false) {
                publicFunction.rndTap(698, 323, 712, 369);
                Thread.sleep(2000);
            }

            result = publicFunction.localFindPic(340, 321, 725, 546, "other.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------other.png=" + result);
                publicFunction.rndTapWH(result.x, result.y, 20, 20);
                Thread.sleep(1000);
            }

            for (int i = 0; i < 3; i++) {

                findResult = mFairy.findPic(399, 325, 581, 611, "dazuo.png");
                if (findResult.sim > 0.8f) {
                    mFairy.ranSwipe(655, 583, 608, 68, 500, 2000);
                    Thread.sleep(2000);
                }

                findResult = mFairy.findPic(399, 325, 581, 611, "dazuo1.png");
                if (findResult.sim > 0.8f) {
                    mFairy.ranSwipe(655, 583, 608, 68, 500, 2000);
                    Thread.sleep(2000);
                }


                result = publicFunction.localFindPic(380, 340, 570, 612, "meditation1.png");
                LtLog.i(publicFunction.getLineInfo() + "---------meditation1.png=" + result);
                if (result.sim >= 0.8) {
                    if (i == 0) {
                        index = index + 1;
                    }

                    publicFunction.rndTapWH(result.x, result.y, 60, 26);


                    for (int i1 = 0; i1 < 5; i1++) {
                        Thread.sleep(200);
                        findResult = mFairy.findPic(489, 25, 549, 389, "bei.png");
                        if (findResult.sim > 0.8f) {
                            return;
                        }
                    }
                    Thread.sleep(4000);

                }
            }

            for (int i = 0; i < 2000; i++) {


                result1 = publicFunction.localFindPic(589, 497, 689, 540, "meditationING.png");
                if (result1.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "---------meditationING.png=" + result1);
                    Thread.sleep(2000);
                } else {
                    Thread.sleep(2000);

                    result = publicFunction.localFindPic(664, 387, 872, 513, "meditation2.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "---------meditation2.png=" + result);
                        publicFunction.rndTapWH(result.x, result.y, 108, 26);
                        Thread.sleep(3000);
                        index = index + 1;
                    }
                    break;
                }


            }
            LtLog.i(publicFunction.getLineInfo() + "---------index=" + index);
            if (index >= 5) {
                if (result.sim < 0.8 && result1.sim < 0.8) { //继续修炼 和 打坐中 没有出现 并且 已经打坐超过5次  结束任务，有部分角色可能会有很多的打坐次数
                    break;
                }
            }
            Thread.sleep(100);
        }


        result = gamePublicFunction.leave();
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "离开");
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
            Thread.sleep(2000);
        }

        gamePublicFunction.clickDetermine();

        findResult = mFairy.findPic(991, 29, 1122, 97, "jiayuan1.png");
        mFairy.onTap(0.8f, findResult, 1007, 222, 1024, 234, "离开家园", 5000);

        gamePublicFunction.clickDetermine();

        gamePublicFunction.goMainCity();


    }

    //珍宝阁
    private void familyTreasure() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "珍宝阁任务开始");

        AtFairy2.OpencvResult result;

        gamePublicFunction.switchSkillOrTong("tong");
        for (int i = 0; i < 20; i++) {

            Thread.sleep(500);

            result = publicFunction.localFindPic(900, 618, 1018, 720, "tong.png" + "|" + "tong2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "点家族");
                publicFunction.rndTapWH(result.x, result.y, 26, 25);
                Thread.sleep(2000);
            }

            result = publicFunction.localFindPic(0, 31, 108, 165, "tong1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "家族界面");
                publicFunction.rndTap(1204, 423, 1230, 466);
                Thread.sleep(1000);
            }

            result = publicFunction.localFindPic(892, 387, 1029, 506, "gold.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "金库");
                publicFunction.rndTap(798, 557, 813, 578);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(0, 27, 114, 172, "treasure3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "珍宝阁界面");
                Thread.sleep(3000);
                familyTreasure1();
                gamePublicFunction.closeWindow();
                break;
            }
        }
    }

    public void familyTreasure1() throws Exception {
        //设置珍宝阁需要购买的道具
        AtFairy2.OpencvResult result;
        List<String> articleList = new ArrayList<>();
        if (TaskMain.taskMap.get("crystal") == 1) {
            articleList.add("crystal1.png");
            articleList.add("crystal2.png");
            articleList.add("crystal3.png");
            articleList.add("crystal4.png");
            articleList.add("crystal5.png");
        }

        if (TaskMain.taskMap.get("antique_white") == 1) {
            articleList.add("antique_white1.png");//
            articleList.add("antique_white2.png");
            articleList.add("antique_white3.png");
            articleList.add("antique_white4.png");//**
            articleList.add("antique_white5.png");//**
            articleList.add("antique_white6.png");
            articleList.add("antique_white7.png");//
            articleList.add("antique_white8.png");//
        }

        if (TaskMain.taskMap.get("antique_green") == 1) {
            articleList.add("antique_green1.png");
            articleList.add("antique_green2.png");
            articleList.add("antique_green3.png");
        }

        if (TaskMain.taskMap.get("antique_blue") == 1) {
            articleList.add("antique_blue1.png");
        }

        if (TaskMain.taskMap.get("book") == 1) {
            articleList.add("book1.png");
            articleList.add("book2.png");
        }

        if (TaskMain.taskMap.get("soul_green") == 1) {
            articleList.add("soul_green.png");
        }

        if (TaskMain.taskMap.get("soul_blue") == 1) {
            articleList.add("soul_blue.png");
        }

        if (TaskMain.taskMap.get("soul_purple") == 1) {
            articleList.add("soul_purple.png");
        }

        if (TaskMain.taskMap.get("goldBox") == 1) {
            articleList.add("goldBox.png");
        }

        if (TaskMain.taskMap.get("ExpMedicine") == 1) {
            articleList.add("ExpMedicine.png");
        }

        if (TaskMain.taskMap.get("ZQMedicine") == 1) {
            articleList.add("ZQMedicine.png");
        }

        if (AtFairyConfig.getOption("zbg1").equals("1")) {
            articleList.add("zbg1.png");
        }
        if (AtFairyConfig.getOption("zbg2").equals("1")) {
            articleList.add("zbg2.png");
        }

        if (AtFairyConfig.getOption("goujia1").equals("1")) {
            articleList.add("goujia1.png");//披风进阶符
        }

        if (AtFairyConfig.getOption("goujia2").equals("1")) {
            articleList.add("goujia2.png");//璀璨的神秘矿石
        }

        if (AtFairyConfig.getOption("goujia3").equals("1")) {
            articleList.add("goujia3.png");//金水晶
        }
        if (AtFairyConfig.getOption("goujia4").equals("1")) {
            articleList.add("goujia4.png");//和氏璧
        }
        if (AtFairyConfig.getOption("goujia5").equals("1")) {
            articleList.add("goujia5.png");//赤金驭天蹬碎片
        }

        if (AtFairyConfig.getOption("goujia6").equals("1")) {
            articleList.add("goujia6.png");//赤金驭天绳碎片
        }
        if (AtFairyConfig.getOption("goujia7").equals("1")) {
            articleList.add("goujia7.png");//赤金驭天鞍碎片
        }
        if (AtFairyConfig.getOption("goujia8").equals("1")) {
            articleList.add("goujia8.png");//金蚕丝
        }
        if (AtFairyConfig.getOption("goujia9").equals("1")) {
            articleList.add("goujia9.png");//离云丝
        }

        for (int i = 0; i < 2; i++) {

            if (i == 0) {
                LtLog.i(publicFunction.getLineInfo() + "---------从上往下滑动=");
                publicFunction.RanSwipe(216, 161, 713, 598, 0, 1500);
            } else {
                LtLog.i(publicFunction.getLineInfo() + "---------从下往上滑动=");
                publicFunction.RanSwipe(216, 161, 713, 598, 2, 1500);
            }

            for (int j = 0; j < articleList.size(); j++) {
                result = publicFunction.localFindPicHLS(111, 126, 851, 665, articleList.get(j));
                LtLog.i(publicFunction.getLineInfo() + "----------------------" + articleList.get(j) + "--------result=" + result);
                if (result.sim >= 0.72) {

                    publicFunction.rndTapWH(result.x + 200, result.y + 20, 38, 41);
                    Thread.sleep(1000);

                    mFairy.onTap(1030, 448, 1043, 462, "", 1000);

                    for (int c = 0; c < 3; c++) {
                        mFairy.onTap(1004, 349, 1019, 367, "", 200);
                    }

                    publicFunction.continuityClick(987, 606, 1056, 642, 3, 100);
                }
            }

            Thread.sleep(1000);
        }
    }

    //家族捐献
    public void familyDonate() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "---------familyDonate=");
        AtFairy2.OpencvResult result;

        Thread.sleep(1000);

        for (int i = 0; i < 3; i++) {
            Thread.sleep(500);
            gamePublicFunction.closeWindow();
        }

        gamePublicFunction.switchSkillOrTong("tong");


        for (int i = 0; i < 20; i++) {

            Thread.sleep(500);

            result = publicFunction.localFindPic(900, 618, 1018, 720, "tong.png" + "|" + "tong2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->tong=" + result);
                publicFunction.rndTapWH(result.x, result.y, 26, 25);
                Thread.sleep(2000);
            }

            result = publicFunction.localFindPic(0, 31, 108, 165, "tong1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------------------------tong1--->=" + result);
                publicFunction.rndTap(1204, 423, 1230, 466);
                Thread.sleep(1000);
            }


            result = publicFunction.localFindPic(892, 387, 1029, 506, "gold.png");
            if (result.sim >= 0.7) {
                LtLog.i(publicFunction.getLineInfo() + "-------------------------gold--->=" + result);
                publicFunction.rndTap(948, 439, 970, 460);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(0, 29, 112, 174, "gold1.png");
            LtLog.i(publicFunction.getLineInfo() + "-------------------------gold1--->=" + result);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------------------------gold1--->=" + result);
                familyDonate1();
                break;
            }
        }


        for (int i = 0; i < 2; i++) {
            gamePublicFunction.closeWindow();
        }


    }

    private void familyDonate1() throws Exception {
        AtFairy2.OpencvResult result;
        for (int j = 0; j < 3; j++) {
            result = publicFunction.localFindPic(253, 108, 468, 264, "gold1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------------------------gold1--->=" + result);
                for (int i = 0; i < 2; i++) {
                    publicFunction.rndTap(902, 314, 916, 337);
                    Thread.sleep(500);
                }
                for (int i = 0; i < 20; i++) {
                    publicFunction.rndTap(650, 311, 676, 341);
                    Thread.sleep(100);
                }
                int index = 0;
                if (TaskMain.taskMap.get("ten") == 1) {
                    index = 10;
                } else if (TaskMain.taskMap.get("five") == 1) {
                    index = 5;
                }
                for (int i = 1; i < index; i++) {
                    publicFunction.rndTap(902, 314, 916, 337);
                    Thread.sleep(200);
                }
                Thread.sleep(1000);
                gamePublicFunction.clickDetermine(649, 462, 865, 568);
                return;
            } else {
                //点捐献
                result = publicFunction.localFindPic(991, 116, 1150, 244, "donate.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------------------------donate--->=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 59, 28);
                    Thread.sleep(500);
                }

            }
            Thread.sleep(1000);
        }
    }

    private void goFamily() throws Exception {
        AtFairy2.OpencvResult result;
        gamePublicFunction.switchSkillOrTong("tong");
        result = publicFunction.localFindPic(900, 618, 1018, 720, "tong.png" + "|" + "tong2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------tong.png=" + result);
            publicFunction.rndTapWH(result.x, result.y, 18, 17);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(0, 25, 111, 168, "home.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------home.png=" + result);
            publicFunction.rndTap(1197, 136, 1232, 179);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(971, 555, 1178, 683, "goHome1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------goHome1.png=" + result);
            publicFunction.rndTapWH(result.x, result.y, 107, 28);
            Thread.sleep(2000);
            gamePublicFunction.closeWindow();
        }
    }

    //打坐冥想
    private void meditation() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(546, 463, 734, 573, "sleepING.png");
        LtLog.i(publicFunction.getLineInfo() + "sleepING :" + result.sim);
        if (result.sim >= 0.8) {
            for (int i = 0; i < 300; i++) {
                mFairy.condit();
                result = publicFunction.localFindPic(546, 463, 734, 573, "sleepING.png");
                LtLog.i(publicFunction.getLineInfo() + "冥想中");
                if (result.sim < 0.8) {
                    gamePublicFunction.goMainCity();
                    break;
                }
                Thread.sleep(1000);
            }
        } else {
            long sleepTime = mFairy.mMatTime(1189, 137, 50, 14, 0.9f);
            result = gamePublicFunction.leave();
            LtLog.i(publicFunction.getLineInfo() + "--------------------  sleepTime=" + sleepTime + ",leave=" + result);
            if (sleepTime >= 10 && result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "发呆判断成功,回主城");
                gamePublicFunction.goMainCity();
            }
        }
        result = publicFunction.localFindPic(1138, 0, 1280, 80, "mainCity1.png" + "|" + "mainCity.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "到主城了,开始验证活动");
            gamePublicFunction.openActivity();
        }
    }

    //商会任务
    private void commerce() throws Exception {
        AtFairy2.OpencvResult result;

        result = publicFunction.localFindPic(336, 315, 923, 610, "commerce1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  commerce1=" + result);
            publicFunction.rndTapWH(result.x, result.y, 86, 28);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(336, 315, 923, 522, "commerce2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  commerce2=" + result);
            publicFunction.rndTapWH(result.x, result.y, 86, 28);
            Thread.sleep(500);
        }


        result = publicFunction.localFindPic(533, 531, 635, 612, "reward.png");
        if (result.sim >= 0.8) {
            //悬赏任务超过3 个 ，上划
            LtLog.i(publicFunction.getLineInfo() + "悬赏任务超过3个,上划");

            publicFunction.RanSwipe(420, 356, 771, 594, 2, 500);//从下往上滑

            Thread.sleep(2000);
        }

        //上划之后再查找一次商会任务，和上面的查找不冲突
        result = publicFunction.localFindPic(336, 315, 923, 610, "commerce1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  commerce1=" + result);
            publicFunction.rndTapWH(result.x, result.y, 86, 28);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(364, 322, 578, 445, "commerce7.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "缴纳货物");
            publicFunction.rndTapWH(result.x, result.y, 114, 23);
            Thread.sleep(2000);
            result = publicFunction.localFindPic(422, 247, 618, 364, "unfilled.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  unfilled=" + result);
                publicFunction.rndTap(735, 436, 799, 464);//点确认
                Thread.sleep(2000);
            }
            Thread.sleep(3000);
//            gamePublicFunction.openActivity();
        }


        result = publicFunction.localFindPic(725, 505, 890, 574, "pick.png");
        if (result.sim >= 0.8) {
            if (TaskMain.taskMap.get("reward") == 1) {
                LtLog.i(publicFunction.getLineInfo() + "接取悬赏任务");
                publicFunction.rndTap(346, 249, 929, 333);//随机选择一个悬赏任务
                Thread.sleep(1000);
                publicFunction.rndTapWH(result.x, result.y, 50, 23);//点击接取
            } else {
                LtLog.i(publicFunction.getLineInfo() + "放弃悬赏任务");
                publicFunction.rndTap(445, 525, 499, 549);//放弃悬赏
                Thread.sleep(1000);
                gamePublicFunction.clickDetermine();
            }
            Thread.sleep(2000);
            gamePublicFunction.openActivity();
        }

        result = publicFunction.localFindPic(422, 247, 618, 364, "unfilled.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  unfilled=" + result);
            publicFunction.rndTap(735, 436, 799, 464);//点确认
            Thread.sleep(2000);
        }

        result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
        if (result.sim >= 0.8) {

            long sleepTime = matTime.mMatTime(1177, 140, 69, 15, 0.9f);

            LtLog.i(publicFunction.getLineInfo() + "在主场景, activity=" + result + ",sleepTime=" + sleepTime);
            result = publicFunction.localFindPic(4, 204, 125, 438, "commerce6.png");
            if (result.sim >= 0.8 && sleepTime > 11) {
                LtLog.i(publicFunction.getLineInfo() + "左侧商会任务");
                publicFunction.rndTapWH(result.x, result.y, 30, 20);
                Thread.sleep(500);
            } else {
                result = publicFunction.localFindPic(975, 234, 1045, 281, "commerce4.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "采集");
                    publicFunction.rndTapWH(result.x, result.y, 10, 10);
                    Thread.sleep(500);
                }
            }
        }


        for (int i = 0; i <= 4; i++) {
            boolean taskState = true;
            result = publicFunction.localFindPic(0, 34, 109, 173, "commerce3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "商会任务界面");
                taskState = commerce1();
                LtLog.i(publicFunction.getLineInfo() + "--------------------  taskState=" + taskState + ", i=" + i);
            } else {
                break;
            }

            if (!taskState || i == 4) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  TaskMain.taskMap.get(TaskMain.SUBMISSION)=" + TaskMain.taskMap.get(TaskMain.SUBMISSION));
                result = publicFunction.localFindPic(153, 578, 372, 681, "giveUp.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "--------------------  giveUp=" + taskState);
                    //如果 识别到放弃任务 ，表示商会任务无法提交， 无论用户选择什么,设置 TaskMain.SUBMISSION =2 ,
                    TaskMain.taskMap.put(TaskMain.SUBMISSION, 2);
                }

                switch (TaskMain.taskMap.get(TaskMain.SUBMISSION)) {
                    case 1:
                        //提交任务
                        publicFunction.rndTap(211, 605, 304, 640);//点交任务
                        Thread.sleep(1000);
                        break;
                    case 3:
                        //满箱提交任务
                        result = publicFunction.localFindPicHLS(1048, 625, 1090, 654, "commerce10.png" + "|" + "commerce10-1.png");
                        LtLog.i(publicFunction.getLineInfo() + "--------------------commerce10=" + result);
                        if (result.sim >= 0.85) {
                            publicFunction.rndTap(211, 605, 304, 640);//点交任务
                            Thread.sleep(1000);
                            break;
                        } else {
                            LtLog.i(publicFunction.getLineInfo() + "--------------------  没有满箱 无法提交 跳过任务");
                        }
                    default:
                        //不提交任务 或者任务无法完成
                        gamePublicFunction.closeWindow();
                        taskList.remove(0);
                        Thread.sleep(2000);
                        gamePublicFunction.goSecurity();
                        Thread.sleep(1000);
                        gamePublicFunction.openActivity();
                        break;
                }
            }
            Thread.sleep(100);
        }


        result = publicFunction.localFindPic(364, 322, 578, 445, "commerce7.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "缴纳货物");
            publicFunction.rndTapWH(result.x, result.y, 114, 23);
            Thread.sleep(2000);
            result = publicFunction.localFindPic(422, 247, 618, 364, "unfilled.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "未装满");
                publicFunction.rndTap(735, 436, 799, 464);//点确认
                Thread.sleep(2000);
            }
//          gamePublicFunction.openActivity();
        }
    }

    /**
     * @return 商会任务的状态 false 商会任务无法完成或用户选择满箱提交   true 商会任务正常进行
     * @throws Exception
     */
    private boolean commerce1() throws Exception {
        boolean taskState = true;
        AtFairy2.OpencvResult result;

        publicFunction.RanSwipe(638, 133, 858, 481, 0, 500);
        Thread.sleep(1000);

        for (int i = 0; i <= 4; i++) {//
            result = publicFunction.localFindPic(996, 527 - (i * 102), 1150, 618 - (i * 102), "up.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "开始查看交货状态：" + i);
                int colorNum = 0;
                colorNum = publicFunction.getColorNumber(result.x - 263, result.y, result.x - 207, result.y + 37, "69,250,69", 0.9);
                LtLog.i(publicFunction.getLineInfo() + "是否满足交货条件：" + colorNum);
                if (colorNum >= 50) {

                    LtLog.i(publicFunction.getLineInfo() + "点交货");

                    publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 10);//点交货
                    Thread.sleep(1000);
                } else {
                    colorNum = publicFunction.getColorNumber(result.x - 263, result.y, result.x - 207, result.y + 37, "249,109,109", 0.9);
                    LtLog.i(publicFunction.getLineInfo() + "--------------------red  colorNum=" + colorNum);
                    if (colorNum >= 50) {
                        LtLog.i(publicFunction.getLineInfo() + "点求助");

                        publicFunction.rndTap(result.x - 134, result.y, result.x - 101, result.y + 20);
                        Thread.sleep(1000);
                    }
                }
            }

            result = publicFunction.localFindPicHLS(1048, 625, 1090, 654, "commerce10.png");
            LtLog.i(publicFunction.getLineInfo() + "--------------------commerce10=" + result);
            if (result.sim >= 0.85) {
                LtLog.i(publicFunction.getLineInfo() + "发现交货数量达到10");
                if (TaskMain.taskMap.get(TaskMain.SUBMISSION) == 3) {
                    taskState = false;
                }
                break;
            }
            Thread.sleep(100);
        }

        result = publicFunction.localFindPic(980, 122, 1153, 627, "commerce5.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "采集");
            publicFunction.rndTapWH(result.x, result.y, 51, 25);
            Thread.sleep(5000);
        }

        return taskState;
    }

    /**
     * @return true 不能提交或提交完成 结束任务.
     */
    private void outdoorsOnHook_commerce() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(0, 34, 109, 173, "commerce3.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  commerce3=" + result);
        }
        result = publicFunction.localFindPic(153, 578, 372, 681, "giveUp.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  giveUp=" + result);
            //识别到放弃，表示不能交任务。删除任务
            taskList.remove(0);
            Thread.sleep(100);
            return;
        }
        result = publicFunction.localFindPic(153, 578, 372, 681, "up2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  up2=" + result);
            publicFunction.rndTapWH(result.x, result.y, 114, 23);
            Thread.sleep(2000);
        }
        result = publicFunction.localFindPic(364, 322, 578, 445, "commerce7.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  commerce7=" + result);
            publicFunction.rndTapWH(result.x, result.y, 114, 23);
            Thread.sleep(2000);
            result = publicFunction.localFindPic(422, 247, 618, 364, "unfilled.png");
            //如果 点击 commerce7.png  后出现 unfilled.png  ，可以直接打开活动，检测任务是否完成。
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  unfilled=" + result);
                publicFunction.rndTap(735, 436, 799, 464);//点确认
                Thread.sleep(2000);
                gamePublicFunction.openActivity();
            }
        }
        result = publicFunction.localFindPic(422, 247, 618, 364, "unfilled.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  unfilled=" + result);
            publicFunction.rndTap(735, 436, 799, 464);//点确认
            Thread.sleep(2000);
        }
        result = publicFunction.localFindPic(725, 505, 890, 574, "pick.png");
        LtLog.i(publicFunction.getLineInfo() + "--------------------  pick=" + result);
        if (result.sim >= 0.8) {
            if (TaskMain.taskMap.get("reward") == 1) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  接悬赏=");
                publicFunction.rndTap(346, 249, 929, 333);//随机选择一个悬赏任务
                Thread.sleep(1000);
                publicFunction.rndTapWH(result.x, result.y, 50, 23);//点击接取
                Thread.sleep(2000);
            } else {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  放弃=");
                publicFunction.rndTap(445, 525, 499, 549);//放弃悬赏
                Thread.sleep(1000);
                gamePublicFunction.clickDetermine();
            }
            gamePublicFunction.openActivity();
        }
    }

    /*
        战龙任务
     */
    private void zhanlong() throws Exception {


        gamePublicFunction.manualOrAutomatic("automatic");

        for (int i = 0; i < 3; i++) {

            findResult = mFairy.findPic(399, 325, 581, 611, "dazuo.png");
            if (findResult.sim > 0.8f) {
                mFairy.ranSwipe(655, 583, 608, 68, 500, 2000);
                Thread.sleep(2000);
            }

            findResult = mFairy.findPic(399, 325, 581, 611, "dazuo1.png");
            if (findResult.sim > 0.8f) {
                mFairy.ranSwipe(655, 583, 608, 68, 500, 2000);
                Thread.sleep(2000);
            }

            findResult = mFairy.findPic(332, 197, 765, 586, "zhanlong1.png");
            mFairy.onTap(0.8f, findResult, "战龙任务", 2000);
        }

        findResult = mFairy.findPic(332, 197, 765, 586, "zhanlong5.png");
        mFairy.onTap(0.8f, findResult, "做其他事情", 2000);

        findResult = mFairy.findPic(422, 542, 848, 700, "zhanlong2.png");
        mFairy.onTap(0.8f, findResult, "确定", 2000);

        findResult = mFairy.findPic(651, 451, 850, 502, "zhanlong4.png");
        if (findResult.sim > 0.8f) {
            mFairy.initMatTime();
            gamePublicFunction.manualOrAutomatic("automatic");
        }

        findResult = mFairy.findPic(974, 99, 1084, 178, "li.png");
        if (findResult.sim > 0.8f) {

            long time = mFairy.mMatTime(1179, 137, 78, 14, 0.95f);
            if (time > 5) {
                boolean bool = true;
                for (int i = 0; i < 8; i++) {

                    findResult = mFairy.findPic(15, 176, 138, 458, "zhanlong3.png");
                    if (findResult.sim > 0.8f) {
                        mFairy.onTap(0.8f, findResult, "左侧战龙", 10000);
                        mFairy.initMatTime();
                        bool = false;
                        break;
                    }

                    if (i < 5) {
                        mFairy.ranSwipe(133, 301, 133, 416, 500, 1000);
                    } else {
                        mFairy.ranSwipe(133, 416, 133, 301, 500, 1000);
                    }
                }

                if (bool) {

                    AtFairy2.OpencvResult result;

                    for (int i = 0; i < 10; i++) {
                        result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
                        if (result.sim >= 0.8) {
                            mFairy.onTap(1105, 38, 1117, 53, "", 2000);
                            break;
                        }

                        result = gamePublicFunction.leave();
                        if (result.sim >= 0.8) {
                            LtLog.i(publicFunction.getLineInfo() + "离开");
                            publicFunction.rndTapWH(result.x, result.y, 10, 10);
                            Thread.sleep(2000);
                        }
                        gamePublicFunction.clickDetermine();
                    }
                }
            }
        }
    }

    boolean wsd = true;

    //武神殿
    private void Valkyrie() throws Exception {
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 5; i++) {

            result = publicFunction.localFindPic(780, 494, 989, 619, "startCombat.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "开始战斗");
                publicFunction.rndTapWH(result.x, result.y, 109, 25);
                Thread.sleep(500);
            }


            result = publicFunction.localFindPic(362, 220, 600, 364, "ValkyrieOff1.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "次数不足,end!");
                publicFunction.rndTap(492, 438, 547, 465);
                Thread.sleep(1000);
                gamePublicFunction.closeWindow();
                taskList.remove(0);
                Thread.sleep(1000);
                gamePublicFunction.openActivity();
                return;
            }

            result = publicFunction.localFindPic(456, 287, 682, 359, "combatOver.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "战斗结束,离开");
                publicFunction.rndTap(607, 433, 673, 468);//离开
                Thread.sleep(500);
            }

            gamePublicFunction.clickDetermine(551, 602, 726, 674);

            result = publicFunction.localFindPic(0, 26, 111, 170, "Valkyrie1.png" + "|" + "Valkyrie2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "武神殿界面");


                if (wsd) {
                    mFairy.onTap(331, 614, 359, 631, "领取奖励", 1000);
                    mFairy.onTap(331, 614, 359, 631, "领取奖励", 1000);
                    mFairy.onTap(331, 614, 359, 631, "领取奖励", 1000);
                    wsd = false;
                }

                publicFunction.RanSwipe(832, 172, 872, 508, 2, 500);

                Thread.sleep(2000);

                for (int j = 0; j < 5; j++) {

                    result = publicFunction.localFindPic(1033, 80, 1088, 654, "challenge1.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "--------------------  challenge1=" + result);
                        publicFunction.rndTap(143, 616, 195, 642);//刷新对手
                        Thread.sleep(2000);
                        publicFunction.rndTapWH(result.x, result.y, 29, 28);//每次点击挑战，如果次数不够，都会有一个弹出框
                        Thread.sleep(2000);
                        break;
                    } else {
                        publicFunction.RanSwipe(846, 207, 873, 500, 0, 300);
                        Thread.sleep(2000);
                    }
                }
            }

            result = publicFunction.localFindPic(171, 476, 600, 602, "ValkyrieOff.png" + "|" + "ValkyrieOff2.png");
            LtLog.i(publicFunction.getLineInfo() + "使用道具sim：" + result.sim);
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "使用道具");
                publicFunction.rndTap(948, 148, 970, 174);
                Thread.sleep(1000);
                gamePublicFunction.closeWindow();
                Thread.sleep(1000);
                gamePublicFunction.openActivity();
                taskList.remove(0);
                return;
            }

       /* result = publicFunction.localFindPic(366, 576, 429, 626, "redDot.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  redDot=" + result);
            publicFunction.rndTapWH(result.x, result.y, 24, 26);//领取奖励
            Thread.sleep(500);
        }*/

       /* result = publicFunction.localFindPic(800, 542, 1028, 656, "ranking10.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  ranking10=" + result);
            publicFunction.RanSwipe(597, 147, 922, 545, 0, 500);
            Thread.sleep(2000);
        }*/


            //为什么要循环5次， 应为有可能拿到的图是上划过程中得到的图


            Thread.sleep(500);
        }

    }

    private int tlqm_num = 1;
    //田连阡陌
    private void tlqm() throws Exception {

        LtLog.e(mFairy.getLineInfo("田连阡陌活动中----"+tlqm_num));

        findResult = mFairy.findPic("tlqm2.png");
        if(findResult.sim>0.8f){

            findResult = mFairy.findPic(910,141,1028,347,"tlqm4.png");
            if(findResult.sim>0.8f){
                mFairy.onTap(0.8f,findResult,"松土",5000);
                tlqm_num++;
                if(tlqm_num>3){
                    gamePublicFunction.closeWindow();
                    mFairy.onTap(1088,181,1106,202,"离开",500);
                    mFairy.onTap(1088,181,1106,202,"离开",1000);
                    mFairy.onTap(1088,181,1106,202,"离开",3000);
                    Thread.sleep(1000);
                    gamePublicFunction.openActivity();
                    tlqm_num=1;
                    return;
                }
            }

            mFairy.onTap(1195,80,1217,93,"点击地图",3000);

        }

        findResult = mFairy.findPic("tlqm3.png");
        if(findResult.sim>0.8f){

            switch (tlqm_num){
                case 1:
                    mFairy.tap(516,353);
                    Thread.sleep(5000);
                    break;
                case 2:
                    mFairy.tap(643,353);
                    Thread.sleep(5000);
                    break;
                case 3:
                    mFairy.tap(751,356);
                    Thread.sleep(5000);
                    break;
            }
            mFairy.onTap(941,38,956,50,"",1000);

            for (int i = 0; i < 20; i++) {
                Thread.sleep(1000);

                findResult = mFairy.findPic(266,13,963,434,new String[]{"tl1.png","tl2.png","tl3.png"});
                if(findResult.sim>0.72f){
                    mFairy.onTap(findResult.x,findResult.y+120,findResult.x+1,findResult.y+170,"dian",3000);

                    findResult = mFairy.findPic(910,141,1028,347,"tlqm4.png");
                    if(findResult.sim>0.75f){
                        return;
                    }
                }
            }
        }

    }




    //战境武神殿
    private void zjwsd() throws Exception {
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 5; i++) {

            findResult = mFairy.findPic("zjwsd1.png");
            mFairy.onTap(0.8f, findResult, 1213, 286, 1224, 312, "战境武神殿", 1000);

            result = publicFunction.localFindPic(780, 494, 989, 619, "startCombat.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "开始战斗");
                publicFunction.rndTapWH(result.x, result.y, 109, 25);
                Thread.sleep(500);
            }

            result = publicFunction.localFindPic(362, 220, 600, 364, "ValkyrieOff1.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "次数不足,end!");
                publicFunction.rndTap(492, 438, 547, 465);
                Thread.sleep(1000);
                gamePublicFunction.closeWindow();
                taskList.remove(0);
                Thread.sleep(1000);
                gamePublicFunction.openActivity();
                return;
            }

            result = publicFunction.localFindPic(456, 287, 682, 359, "combatOver.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "战斗结束,离开");
                publicFunction.rndTap(607, 433, 673, 468);//离开
                Thread.sleep(500);
            }

            gamePublicFunction.clickDetermine(551, 602, 726, 674);

            findResult = mFairy.findPic("zjwsd2.png");
            if (findResult.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "战境武神殿界面");


                for (int i1 = 0; i1 < 5; i1++) {
                    publicFunction.RanSwipe(832, 172, 872, 508, 2, 500);
                }

                Thread.sleep(2000);

                for (int j = 0; j < 7; j++) {

                    result = publicFunction.localFindPic(1033, 80, 1088, 654, "challenge1.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "--------------------  challenge1=" + result);
                        publicFunction.rndTap(143, 616, 195, 642);//刷新对手
                        Thread.sleep(2000);
                        publicFunction.rndTapWH(result.x, result.y, 29, 28);//每次点击挑战，如果次数不够，都会有一个弹出框
                        Thread.sleep(2000);
                        break;
                    } else {
                        publicFunction.RanSwipe(832, 172, 872, 508, 0, 500);
                        Thread.sleep(2000);
                    }
                }
            }

            result = publicFunction.localFindPic(171, 476, 600, 602, "ValkyrieOff.png" + "|" + "ValkyrieOff2.png");
            LtLog.i(publicFunction.getLineInfo() + "使用道具sim：" + result.sim);
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "使用道具");
                publicFunction.rndTap(948, 148, 970, 174);
                Thread.sleep(1000);
                gamePublicFunction.closeWindow();
                Thread.sleep(1000);
                gamePublicFunction.openActivity();
                taskList.remove(0);
                return;
            }

       /* result = publicFunction.localFindPic(366, 576, 429, 626, "redDot.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  redDot=" + result);
            publicFunction.rndTapWH(result.x, result.y, 24, 26);//领取奖励
            Thread.sleep(500);
        }*/

       /* result = publicFunction.localFindPic(800, 542, 1028, 656, "ranking10.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  ranking10=" + result);
            publicFunction.RanSwipe(597, 147, 922, 545, 0, 500);
            Thread.sleep(2000);
        }*/


            //为什么要循环5次， 应为有可能拿到的图是上划过程中得到的图


            Thread.sleep(500);
        }

    }

    /**
     * 英雄挑战
     *
     * @throws Exception
     */
    private void Hero() throws Exception {
        //英雄挑战

        findResult = mFairy.findPic("h1.png");
        if (findResult.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("英雄挑战界面"));


            findResult = mFairy.findPic("h3.png");
            if (findResult.sim > 0.8f) {
                LtLog.e(mFairy.getLineInfo("没次数了"));
                gamePublicFunction.closeWindow();
                taskList.remove(0);
                Thread.sleep(1000);
                gamePublicFunction.openActivity();
                return;
            }

            findResult = mFairy.findPic(951, 590, 1173, 675, "h2.png");
            mFairy.onTap(0.7f, findResult, "一键挑战", 3000);
        }


        AtFairy2.OpencvResult result, result1;


        findResult = mFairy.findPic(94, 452, 1156, 592, "tiaozhan.png");
        if (findResult.sim > 0.75f) {
            LtLog.i(publicFunction.getLineInfo() + "挑战");

            for (int i = 0; i < 5; i++) {
                result1 = publicFunction.localFindPic(88, 504, 1152, 596, "receive2.png");
                if (result1.sim > 0.75) {
                    LtLog.i(publicFunction.getLineInfo() + "领取奖励");
                    publicFunction.rndTapWH(result1.x, result1.y, 5, 5);
                    Thread.sleep(1000);
                }
            }

            if (findResult.x > 250) {
                mFairy.tap(findResult.x - 271, findResult.y);//挑战x坐标大于250 点击 领取奖励 固定位置
                Thread.sleep(1000);
                publicFunction.rndTapWH(findResult.x, findResult.y, 28, 26);
                Thread.sleep(3000);
            } else {
                publicFunction.rndTapWH(findResult.x, findResult.y, 28, 26);
                Thread.sleep(3000);
            }

            result1 = publicFunction.localFindPic(findResult.x + 49, findResult.y + 30, findResult.x + 81, findResult.y + 66, "0-2.png");
            LtLog.i(publicFunction.getLineInfo() + "次数sim:" + result1.sim);
            if (result1.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "没次数了");
                gamePublicFunction.closeWindow();
                taskList.remove(0);
                Thread.sleep(1000);
                gamePublicFunction.openActivity();
                return;
            }

        }


        result = publicFunction.localFindPic(873, 106, 932, 161, "ten.png" + "|" + "shi.png");
        LtLog.i(publicFunction.getLineInfo() + "十层sim：" + result.sim);
        if (result.sim >= 0.92) {
            //如果在第十层，并且检测到指定范围内 "领取奖励",说明已经通关，点击领取奖励，退出挑战界面
            LtLog.i(publicFunction.getLineInfo() + "在第十层");

            Thread.sleep(5000);

            result = publicFunction.localFindPic(873, 106, 932, 161, "ten.png" + "|" + "shi.png");
            if (result.sim < 0.92) {
                return;
            }

            result = publicFunction.localFindPic(109, 500, 1174, 583, "challenge.png");//678,527   727,565,759,593
            if (result.sim >= 0.8) {

                for (int i = 0; i < 5; i++) {
                    result = publicFunction.localFindPic(88, 504, 1152, 596, "receive2.png");
                    if (result.sim > 0.75) {
                        LtLog.i(publicFunction.getLineInfo() + "领取奖励");
                        publicFunction.rndTapWH(result.x, result.y, 5, 5);
                        Thread.sleep(1000);
                    }
                }

                return;
            }

            result = publicFunction.localFindPic(904, 490, 1120, 584, "receive2.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "领取奖励");
                publicFunction.rndTapWH(result.x, result.y, 28, 26);
                Thread.sleep(2000);
            }

            gamePublicFunction.closeWindow();
            taskList.remove(0);
            Thread.sleep(1000);
            gamePublicFunction.openActivity();
            return;

        } else {
            result = publicFunction.localFindPic(88, 504, 1152, 596, "receive2.png");
            if (result.sim > 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "领取奖励");
                publicFunction.rndTapWH(result.x, result.y, 5, 5);
                Thread.sleep(1000);
            }

        }

        gamePublicFunction.clickDetermine();
    }

    /**
     * 宝藏探索
     *
     * @throws Exception
     */
    private void treasure() throws Exception {
        AtFairy2.OpencvResult result;
        FunctionClass.ResultValue result1;
        result = gamePublicFunction.leave();
        if (result.sim > 0.8) {
            Thread.sleep(2000);
//            result1 = publicFunction.localFindPic(1158,36,1242,75, "number_0.png");
            result1 = functionClass.binaryzationFindPic(1158, 36, 1227, 75, new int[]{200, 200, 200}, new int[]{255, 255, 255}, "number_0.png");
            LtLog.i(publicFunction.getLineInfo() + "--------------------  leave=" + result + ",result1 :" + result1);
            int colorNum = publicFunction.getColorNumber(1250, 17, 1258, 26, "0,0,0", 1.0);
            LtLog.i(publicFunction.getLineInfo() + "--------------------  colorNum=" + colorNum);
            if (colorNum > 50 && result1.sim < 0.8) {
                publicFunction.rndTap(1183, 628, 1230, 669);//点放大镜
                Thread.sleep(1000);
            } else {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  leave=" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(2000);
                gamePublicFunction.clickDetermine();
                for (int i = 0; i < 60; i++) {
                    result = publicFunction.localFindPic(436, 413, 564, 542, "leave-4.png");
                    LtLog.i(publicFunction.getLineInfo() + "--------------------  leave=" + result);
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "--------------------  leave=" + result);
                        publicFunction.rndTapWH(result.x, result.y, 28, 29);
                        Thread.sleep(500);
                    }
                    result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
                    LtLog.i(publicFunction.getLineInfo() + "--------------------  activity=" + colorNum);
                    if (result.sim >= 0.8) {
                        gamePublicFunction.openActivity();
                        break;
                    }
                    gamePublicFunction.closeWindow();
                    Thread.sleep(1000);
                }

            }
        }


        result = publicFunction.localFindPic(436, 413, 564, 542, "leave-4.png");
        LtLog.i(publicFunction.getLineInfo() + "--------------------  leave=" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  leave=" + result);
            publicFunction.rndTapWH(result.x, result.y, 28, 29);
            Thread.sleep(500);
        }
    }

    private void switchTreasure() throws Exception {
        //选择探索地图
        AtFairy2.OpencvResult result, result1;
        result1 = publicFunction.localFindPic(49, 580, 1249, 632, "treasure1.png");
        if (result1.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  treasure1=" + result1);
            return;
        }

        result = publicFunction.localFindPicRGBOrHSVOrHLS(21, 292, 1275, 470, "lock.png", "HLS", "leftUp", 0.8);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  lock=" + result);
            int colorNum = publicFunction.getColorNumber(result.x - 131, result.y + 249, result.x - 96, result.y + 261, "0,0,0", 1.0);
            LtLog.i(publicFunction.getLineInfo() + "--------------------  colorNum=" + colorNum);
            if (colorNum >= 50) {
                mFairy.tap(result.x - 131, result.y);
                Thread.sleep(1000);
            } else {
                //如果不是黑色 //点前一个
                mFairy.tap(result.x - 421, result.y);
                Thread.sleep(1000);
            }
        } else {
            for (int i = 0; i < 2; i++) {
                publicFunction.RanSwipe(144, 300, 1195, 462, 3, 500);
            }
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(1167, 193, 1280, 384, "treasure2.png");
        if (result.sim >= 0.8) {
            int colorNum = publicFunction.getColorNumber(result.x - 131, result.y + 249, result.x - 96, result.y + 261, "0,0,0", 1.0);
            LtLog.i(publicFunction.getLineInfo() + "--------------------  colorNum=" + colorNum);
            if (colorNum >= 50) {
                mFairy.tap(result.x - 131, result.y);
                Thread.sleep(1000);
            } else {
                //如果不是黑色 //点前一个
                mFairy.tap(result.x - 421, result.y);
                Thread.sleep(1000);
            }
        }

    }

    //挖宝
    public int treasureMap() throws Exception {
        AtFairy2.OpencvResult result;
        gamePublicFunction.goSecurity();
        gamePublicFunction.closeWindow();
        gamePublicFunction.openPackage();
        matTime.resetTime();
        long time = System.currentTimeMillis() / 1000, timex = 0;
        long sleepTime = 0;
        int index = 0;
        MatTime matTime1 = new MatTime(mFairy);
        result = publicFunction.localFindPic(0, 0, 133, 129, "package.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->activity=" + result);
            for (int i = 0; i < 5; i++) {
                publicFunction.RanSwipe(779, 105, 1093, 494, 0, 500);
                Thread.sleep(100);
            }
        }

        while (mFairy.condit()) {
            AtFairy2.OpencvResult resultLeave = gamePublicFunction.leave();
            sleepTime = matTime.mMatTime(1177, 140, 69, 15, 0.9f);
            LtLog.i(publicFunction.getLineInfo() + "------->sleepTime=" + sleepTime + ",,leave==" + resultLeave);
            if (sleepTime >= 60 && resultLeave.sim < 0.8) {
                gamePublicFunction.openPackage();
            }
            if (resultLeave.sim >= 0.8 && TaskMain.taskMap.get("gohole") == 1) {
                long sleepTimg1 = matTime1.mMatTime(1027, 629, 48, 33, 0.9f);//没有在打怪状态中的时间
                LtLog.i(publicFunction.getLineInfo() + "------->sleepTimg1=" + sleepTimg1);
                if (sleepTimg1 >= 60) {
                    outMap();
                }
                gamePublicFunction.manualOrAutomatic("automatic");
                matTime.resetTime();
            }

            if (resultLeave.sim < 0.8) {
                matTime1.resetTime();//重置 matTime1
            }
            if (sleepTime >= 20 && sleepTime < 30) {
                result = publicFunction.localFindPic(930, 484, 1058, 611, "use1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------->use1=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 28, 27);
                    Thread.sleep(6000);
                }
            }
            result = publicFunction.localFindPic(710, 588, 889, 714, "invitation.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->invitation=" + result);
                if (TaskMain.taskMap.get("gohole") == 0) {
                    //没勾选进洞，关闭窗口
                    gamePublicFunction.closeWindow();
                    outMap();
                } else {
                    //勾选进洞 ,,点击随机招募
                    publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                    Thread.sleep(500);
                    publicFunction.rndTap(395, 638, 507, 663);
                    Thread.sleep(1000);
                    gamePublicFunction.closeWindow();
                }
            }
            result = publicFunction.localFindPic(0, 0, 133, 129, "package.png");
            if (result.sim >= 0.8) {
                matTime.resetTime();
                LtLog.i(publicFunction.getLineInfo() + "------->package=" + result + ",index=" + index);
                if (index < 6) {
                    publicFunction.RanSwipe(779, 105, 1093, 494, 2, 500);
                } else {
                    publicFunction.RanSwipe(779, 105, 1093, 494, 0, 500);
                }
                Thread.sleep(2000);
                index = index + 1;
                if (index >= 12) {
                    index = 0;
                }
                result = publicFunction.localFindPic(733, 74, 1147, 635, "treasureMap.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------->treasureMap=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 68, 38);
                    Thread.sleep(2000);
                }
            } else {
                time = System.currentTimeMillis() / 1000;
            }
            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 120) {
                LtLog.i(publicFunction.getLineInfo() + "------->长时间没找到宝图=" + ",timex=" + timex);
                return 99;
            }
            result = publicFunction.localFindPic(562, 407, 717, 533, "use.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->use=" + result);
                publicFunction.rndTapWH(result.x, result.y, 55, 26);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(1091, 0, 1280, 82, "skip.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  skip=" + result);
                publicFunction.rndTapWH(result.x, result.y, 112, 22);
                Thread.sleep(500);
            }
            Thread.sleep(100);
        }
        return 99;
    }

    private void outMap() throws Exception {
        AtFairy2.OpencvResult resultLeave = gamePublicFunction.leave();
        if (resultLeave.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  skip=" + resultLeave);
            publicFunction.rndTapWH(resultLeave.x, resultLeave.y, 10, 10);//点击离开
            Thread.sleep(1000);
        }
        resultLeave = publicFunction.localFindPic(467, 182, 973, 506, "leave-4.png");
        if (resultLeave.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  leave-4.png=" + resultLeave);
            publicFunction.rndTapWH(resultLeave.x, resultLeave.y, 20, 20);
            Thread.sleep(500);
        }
    }

    public void fishing() throws Exception {
        AtFairy2.OpencvResult result;
        String start, sub;
        int[] xy, xy1, xy2;
        while (mFairy.condit()) {
            start = "5,115,221";
            sub = "0|-25|13,134,236";
            xy = functionClass.multipointFindColorEx(1059, 521, 1115, 653, start, sub, 0.9);
            result = publicFunction.localFindPic(1194, 15, 1241, 63, "fork.png" + "|" + "fork2.png" + "|" + "fork3.png");
//            LtLog.i(publicFunction.getLineInfo() + "--------------------  xy=" + xy[0] + "," + xy[1] + ", fork.png: " + result);
            if (xy[0] < 0 && result.sim < 0.8) {
                mFairy.touchDown(1166, 582);
                Thread.sleep(400);
                mFairy.touchUp();
            }
            if (result.sim > 0.8) {
                //鱼 约10S从底部到顶部  ，每s约63.7像素
                mFairy.touchDown(1166, 582);
                Thread.sleep(300);
                mFairy.touchUp();
                Thread.sleep(100);
                while (mFairy.condit()) {
                    // 555,29,662,695
                    ScreenInfo screenInfo = mFairy.captureInterval();
                    start = "31,240,255";
                    sub = "3|10|47,255,255&81|10|47,254,255&83|0|33,236,251";
                    xy = functionClass.multipointFindColorEx(555, 29, 662, 695, start, sub, 0.99, screenInfo);//鱼篓
                    start = "255,248,231";
                    sub = "12|29|255,208,150&19|26|253,204,149&21|9|253,220,171&29|10|255,215,165&28|16|250,211,155";
                    xy1 = functionClass.multipointFindColorEx(555, 29, 662, 695, start, sub, 0.99, screenInfo);//鱼
                    xy2 = xy1;
                    if (xy1[0] > -1) {
                        xy2 = xy1;
                    } else {
                        start = "195,236,245";
                        sub = "9|5|175,224,241&16|29|115,183,200&25|12|139,205,222&25|-5|178,229,246&13|0|178,227,236";
                        xy1 = functionClass.multipointFindColorEx(555, 29, 662, 695, start, sub, 0.99, screenInfo);//鱼
                        if (xy1[0] > -1) {
                            xy2 = xy1;
                        }
                    }
                    if (xy[0] > -1 && xy2[0] > -1) {
                        if (xy[1] > xy2[1]) {
                            LtLog.i(publicFunction.getLineInfo() + "--------------------  up " + xy[0] + "," + xy[1] + "," + xy2[0] + "," + xy2[1]);
                            mFairy.touchDown(1166, 582);
                            Thread.sleep(400);
                            mFairy.touchUp();
                        } else {
                            LtLog.i(publicFunction.getLineInfo() + "--------------------  down " + xy[0] + "," + xy[1] + "," + xy2[0] + "," + xy2[1]);
                            Thread.sleep(100);
                        }
                    } else if (xy[0] > -1) {
                        LtLog.i(publicFunction.getLineInfo() + "--------------------  not fish ");
                        mFairy.touchDown(1166, 582);
                        Thread.sleep(200);
                        mFairy.touchUp();
                        Thread.sleep(200);
                    }
                    result = publicFunction.localFindPic(1194, 15, 1241, 63, "fork.png" + "|" + "fork2.png" + "|" + "fork3.png");
//                    LtLog.i(publicFunction.getLineInfo() + "--------------------  xy=" + xy[0] + "," + xy[1] + ", fork.png: " + result);
                    if (result.sim < 0.8) {
                        Thread.sleep(10000);
                        break;
                    }
                }
            }
            start = "220,255,0";
            sub = "0|11|220,255,0";
            xy = functionClass.multipointFindColorEx(350, 187, 1004, 247, start, sub, 0.9);
//            LtLog.i(publicFunction.getLineInfo() + "-----------2222---------  xy=" + xy[0] + "," + xy[1]);
            if (xy[0] > -1) {
                mFairy.tap(1166, 582);
                Thread.sleep(400);
            }


            Thread.sleep(100);
        }

    }

    /**
     * 小筑签到
     *
     * @throws Exception
     */
    public void hutSign() throws Exception {
        //小筑签到
        AtFairy2.OpencvResult result;
        LtLog.i(publicFunction.getLineInfo() + "【当前任务：小筑签到】");
        boolean op = false;//标记是否领取过
        for (int i = 0; i < 60; i++) {
            result = publicFunction.localFindPic(512, 7, 938, 158, "hutSign.png" + "|" + "hutSign1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  hutSign.png=" + result);
                publicFunction.rndTapWH(result.x, result.y, 10, 10);
                Thread.sleep(1500);
            }


            findResult = mFairy.findPic(937, 361, 1067, 463, "xz1.png");
            mFairy.onTap(0.8f, findResult, "每日抽奖", 1000);

            findResult = mFairy.findPic("xz2.png");
            mFairy.onTap(0.8f, findResult, "抽奖", 1000);


            findResult = mFairy.findPic("xz4.png");
            if (findResult.sim > 0.8f) {
                mFairy.onTap(1018, 53, 1031, 64, "", 1000);
                break;
            }
            findResult = mFairy.findPic("xz3.png");
            mFairy.onTap(0.8f, findResult, "确定", 1000);


            Thread.sleep(1000);
        }
        for (int j = 0; j < 3; j++) {

            findResult = mFairy.findPic("xz5.png");
            mFairy.onTap(0.8f, findResult, "关闭", 2000);

            Thread.sleep(500);
        }
        Thread.sleep(1000);

    }

    public void sgame() throws Exception {


        findResult = mFairy.findPic(1144, 1, 1275, 41, "ws.png");
        if (findResult.sim < 0.8f) {
            new TimingActivity(mFairy).GoSecurityXiakeIsland();//如果在侠客岛,需要先回到安全区,,初始设计脚本时没有考虑到侠客岛这个奇葩,所以这个地方还要调用一次。

            gamePublicFunction.closeWindow();

            gamePublicFunction.goSecurity();//回安全区
        }


        for (int i = 0; i < 10; i++) {


            findResult = mFairy.findPic(1144, 1, 1275, 41, "ws.png");
            if (findResult.sim > 0.75f) {
                LtLog.i("勾栏瓦舍界面");
                break;
            } else {


                gamePublicFunction.goLA();

                gamePublicFunction.openMapWorldOrCurrent("current");

                mFairy.onTap(453, 398, 454, 399, "瓦舍门口坐标", 500);
                mFairy.onTap(453, 398, 454, 399, "", 5000);

                mFairy.onTap(937, 36, 949, 49, "", 8000);

            /*mFairy.touchDown(151, 580);
            mFairy.touchMove(63, 627, 500, 2000);
            mFairy.touchUp();*/


                for (int j = 0; j < 30; j++) {

                    findResult = mFairy.findPic(599, 623, 706, 666, "jd.png");
                    if (findResult.sim > 0.8f) {
                        Thread.sleep(1000);
                    } else {
                        break;
                    }
                }

                Thread.sleep(2000);
            }
        }


        boolean bool = false;
        int sgame_num = 1;
        int err = 0;
        while (mFairy.condit()) {

            findResult = mFairy.findPic("ditu.png");
            if (findResult.sim > 0.8f) {

                switch (sgame_num) {
                    case 1:
                        mFairy.onTap(643, 526, 644, 527, "求不问卦", 1500);
                        break;

                    case 2:
                        mFairy.onTap(609, 517, 610, 518, "万子福", 1500);
                        break;
                    case 3:
                        mFairy.onTap(643, 504, 644, 505, "贾字票", 1500);
                        break;
                    case 4:
                        mFairy.onTap(705, 309, 706, 310, "保温松鼠", 1500);

                        err++;
                        if (err > 3) {
                            return;
                        }
                        break;
                    default:
                        return;
                }

                mFairy.onTap(937, 36, 949, 49, "", 1000);
            }


            findResult = mFairy.findPic(1144, 1, 1275, 41, "ws.png");
            if (findResult.sim > 0.8f) {
                long time = mFairy.mMatTime(1183, 137, 63, 15, 0.95f);

                if (time > 5 || bool) {
                    LtLog.i("勾栏瓦舍界面");
                    bool = false;
                    mFairy.initMatTime();
                    gamePublicFunction.openMapWorldOrCurrent("current");
                    continue;
                }

                findResult = mFairy.findPic(935, 173, 1091, 304, "ws1.png");
                mFairy.onTap(0.8f, findResult, "对话", 1000);

                FindResult can = mFairy.findPic(389, 262, 561, 546, "ws2.png");
                if (can.sim > 0.8f) {
                    findResult = mFairy.findPic("ws5.png");
                    if (findResult.sim > 0.85f) {
                        mFairy.onTap(147, 546, 160, 555, "", 500);
                        sgame_num++;
                        bool = true;
                        continue;
                    }
                    mFairy.onTap(0.8f, can, "参加游戏", 8000);
                }
            }

            findResult = mFairy.findPic("ws3.png");
            mFairy.onTap(0.8f, findResult, 1217, 46, 1230, 64, "关闭算卦界面", 1000);


            findResult = mFairy.findPic("ws6.png");
            if (findResult.sim > 0.8f) {
                LtLog.i("万子福界面");

                mFairy.onTap(353, 258, 354, 259, "", 1000);
                mFairy.onTap(346, 347, 362, 365, "", 1000);

                findResult = mFairy.findPic("ws6.png");
                if (findResult.sim > 0.8f) {
                    mFairy.onTap(890, 467, 915, 475, "提交", 1500);
                    mFairy.onTap(759, 444, 785, 456, "确定", 1000);
                }
            }


            findResult = mFairy.findPic("ws7.png");
            if (findResult.sim > 0.8f) {
                LtLog.i("贾字票界面");

                mFairy.onTap(299, 259, 314, 281, "", 1000);
                mFairy.onTap(301, 347, 315, 362, "", 1000);
                mFairy.onTap(301, 435, 318, 452, "", 1000);
                mFairy.onTap(306, 515, 316, 526, "", 1000);
                mFairy.onTap(466, 597, 503, 607, "", 1000);
                mFairy.onTap(745, 446, 778, 459, "", 8000);
                mFairy.onTap(1118, 566, 1127, 583, "", 3000);
            }

            findResult = mFairy.findPic("ws8.png");
            if (findResult.sim > 0.8f) {
                LtLog.i("打地鼠界面");
                err = 0;
                while (mFairy.condit()) {

                    findResult = mFairy.findPic("ws8.png");
                    if (findResult.sim < 0.8f) {
                        break;
                    }

                    findResult = mFairy.findPic(354, 7, 1030, 507, "ws9.png");
                    if (findResult.sim > 0.8f) {
                        mFairy.onTap(0.8f, findResult, "地鼠", 1000);

                        mFairy.onTap(1027, 528, 1057, 557, "", 1500);

                    }
                }
                Thread.sleep(3000);
            }

        }


    }


}
