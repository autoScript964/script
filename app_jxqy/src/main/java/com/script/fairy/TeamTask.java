package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/6/14.
 */

public class TeamTask {
    private AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    private GamePublicFunction gamePublicFunction;
    private Random random = new Random(100);

    public TeamTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
//        publicFunction=mFairy.publicFunction;
        gamePublicFunction = new GamePublicFunction(mFairy);
    }

    public int leadTeam() throws Exception {
        if (TaskMain.taskMap.get("reprimand") == 1) {
            reprimand();
        }
        List<String> taskList = setTaskList();
        long timex = 0;
        int peopleNum = 0;
        gamePublicFunction.goSecurity();
        gamePublicFunction.closeWindow();
        gamePublicFunction.createTeam();
        gamePublicFunction.recall(true);
        gamePublicFunction.openActivity();
        AtFairy2.OpencvResult result;

        long time  = System.currentTimeMillis();
        int err  = 0;
        while (mFairy.condit()) {

            if (Math.abs(random.nextInt()) % 100 >= 50) {
                LtLog.i(publicFunction.getLineInfo() + "【 timex=" + timex + " , 任务list:" + taskList+" 】");
            }

            result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
            if (result.sim >= 0.8) {

                LtLog.i(publicFunction.getLineInfo() + "【在主场景】");

                timex = mFairy.mMatTime(1173, 134, 76, 23,0.9f);

            }

            result = publicFunction.localFindPic(1112, 171, 1233, 244, "current.png");
            if (result.sim >= 0.8) {
                //在秘境或者凌绝峰
                LtLog.i(publicFunction.getLineInfo() + "【在秘境或者凌绝峰】");
                mFairy.initMatTime();
                timex=0;
                Thread.sleep(10000);
            }

            result = publicFunction.localFindPic(1140, 1, 1279, 44, "bandit1.png");
            if (result.sim >= 0.8) {
                //在山贼秘窟
                LtLog.i(publicFunction.getLineInfo() + "【在山贼秘窟】");
                mFairy.initMatTime();
                timex=0;
                Thread.sleep(10000);
            }



            result = publicFunction.localFindPic(1140, 1, 1279, 44, "wsmk1.png");
            if (result.sim >= 0.8) {
                //在山贼秘窟
                LtLog.i(publicFunction.getLineInfo() + "【五色秘窟】");
                mFairy.initMatTime();
                timex=0;
                Thread.sleep(10000);
            }

            peopleNum = peopleNumber(); //获取当前队伍人数

            if (timex >= 300 || peopleNum == 4) {
                //打开活动条件
                if (peopleNum == 4) {
                    gamePublicFunction.recall(true);
                }

                result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【在主场景】");
                    gamePublicFunction.openActivity();
                    mFairy.initMatTime();
                    timex=0;
                }
            }

            gamePublicFunction.taskOrTeam("team");//切换到队伍

            if (taskList.size() > 0) {

                taskList = gamePublicFunction.lookupTask(taskList);//判断活动状态
                if (taskList.size() == 0) {
                    return 99;
                }
                selectTask(taskList.get(0));//每个任务特有的东西
            } else {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  任务完成");
                gamePublicFunction.closeWindow();
                gamePublicFunction.exitTeam();
                return 99;
            }

            result = publicFunction.localFindPic(1027, 553, 1181, 680, "target.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "切换目标" + result);
                publicFunction.rndTapWH(result.x, result.y, 54, 27);
                Thread.sleep(500);
            }

            result = publicFunction.localFindPic(282,240,920,510, "target1.png");
            if (result.sim >= 0.7) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  target1=" + result);

                if(err>3){
                    mFairy.onTap(489,443,528,460,"点击异常,取消",2000);
                    err=0;
                    gamePublicFunction.closeWindow();
                    gamePublicFunction.openActivity();
                    continue;
                }

                if(System.currentTimeMillis() - time <5000){
                    err++;
                }else{
                    err=0;
                }

                time = System.currentTimeMillis();

                gamePublicFunction.clickDetermine();
            }

            result = publicFunction.localFindPic(967, 555, 1122, 680, "into2.png");
            if (result.sim >= 0.8) {

                LtLog.i(publicFunction.getLineInfo() + "进入活动");
                publicFunction.rndTapWH(result.x, result.y, 55, 25);
                Thread.sleep(2000);

                for (int i = 0; i < 9; i++) {
                    //如果开启副本时少人，直接开启副本
                    result = publicFunction.localFindPic(373,351,593,426, "open1.png");
                    if(result.sim>=0.8){
                        LtLog.i(publicFunction.getLineInfo() + "直接开启");
                        publicFunction.rndTapWH(result.x, result.y, 20, 10);
                        Thread.sleep(1000);
                    }
                    Thread.sleep(1000);
                }
                gamePublicFunction.closeWindow();

                LtLog.i(publicFunction.getLineInfo() + "--------------------  sleep 10S");

                Thread.sleep(1000);

            }
            result = publicFunction.localFindPic(1091, 0, 1280, 82, "skip.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  skip=" + result);
                publicFunction.rndTapWH(result.x, result.y, 112, 22);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(30, 436, 123, 522, "end.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  end=" + result);
                publicFunction.rndTap(1237, 21, 1255, 47);
                Thread.sleep(500);
            }
            Thread.sleep(100);
        }
        return 0;
    }

    public int followTeam() throws Exception {
        List<String> taskList = setFollowTaskList();
        long timex = 0;
        AtFairy2.OpencvResult result;
        gamePublicFunction.goSecurity();
        gamePublicFunction.exitTeam();
        gamePublicFunction.taskOrTeam("team");

        result = publicFunction.localFindPic(36, 174, 188, 297, "createTeam.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------createTeam>" + result);
            gamePublicFunction.openTeam();
            mFairy.initMatTime();
            timex=0;
        }
        while (mFairy.condit()) {

            if (timex >= 300) {
                gamePublicFunction.taskOrTeam("team");
                gamePublicFunction.goSecurity();
                gamePublicFunction.exitTeam();
                result = publicFunction.localFindPic(36, 174, 188, 297, "createTeam.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------createTeam>" + result);
                    gamePublicFunction.openTeam();
                    mFairy.initMatTime();
                    timex=0;
                }
            }
            result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【主场景】");
                gamePublicFunction.taskOrTeam("team");

                timex = mFairy.mMatTime(1173, 134, 76, 23,0.9f);

                /*boolean matSim = false;
                mat1 = mFairy.getScreenMat(1173, 134, 76, 23, 1, 0, 0, 1);
                if (mat2 != null) {
                    matSim = publicFunction.judgeMatAndMatChange(0.8, mat1, mat2);
                    //判断两个矩阵的相似度大于 sim 则返回 true;
                }
                if (matSim) {
                    LtLog.i(publicFunction.getLineInfo() + "-------matSim>" + matSim);
                } else {
                    //如果两个矩阵不相等 重置时间
                    LtLog.i(publicFunction.getLineInfo() + "-------matSim>" + matSim);
                    time = System.currentTimeMillis() / 1000;
                    Thread.sleep(3000);
                }
                mat2 = mFairy.getScreenMat(1173, 134, 76, 23, 1, 0, 0, 1);*/
            }


            result = publicFunction.localFindPic(0, 76, 116, 222, "teamCopy1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------teamCopy1>" + result);



                result = publicFunction.localFindPic(131, 238, 337, 361, "bandit2.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------bandit2>" + result);
                    publicFunction.rndTapWH(result.x, result.y, 106, 23);
                    Thread.sleep(1000);
                }


                result = publicFunction.localFindPic(131, 238, 337, 361, "wsmk2.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------wsmk2>" + result);
                    publicFunction.rndTapWH(result.x, result.y, 106, 23);
                    Thread.sleep(1000);
                }

                result = publicFunction.localFindPic(357, 552, 513, 680, "matching1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------matching1>" + result);
                    publicFunction.rndTapWH(result.x, result.y, 56, 28);
                    Thread.sleep(1000);
                }
            }
            result = publicFunction.localFindPic(854, 500, 1005, 624, "matching.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------matching>" + result);
                taskList = setFollowTask(taskList);//设置跟队副本
                if (taskList.size() == 0) {
                    LtLog.i(publicFunction.getLineInfo() + "-------跟队完成>");
                    gamePublicFunction.closeWindow();
                    gamePublicFunction.exitTeam();
                    return 99;
                }
            }

            result = publicFunction.localFindPic(1112, 171, 1233, 244, "current.png");
            if (result.sim >= 0.8) {
                //在秘境或者凌绝峰
                LtLog.i(publicFunction.getLineInfo() + "--------------------  current=" + result);
                mFairy.initMatTime();
                timex=0;
                Thread.sleep(10000);
            }
            result = publicFunction.localFindPic(1140, 1, 1279, 44, "bandit1.png");
            if (result.sim >= 0.8) {
                //在山贼秘窟
                LtLog.i(publicFunction.getLineInfo() + "--------------------  bandit1=" + result);
                mFairy.initMatTime();
                timex=0;
                Thread.sleep(10000);
            }


            result = publicFunction.localFindPic(1140, 1, 1279, 44, "wsmk1.png");
            if (result.sim >= 0.8) {
                //在山贼秘窟
                LtLog.i(publicFunction.getLineInfo() + "【五色秘窟】");
                mFairy.initMatTime();
                timex=0;
                Thread.sleep(10000);
            }


            result = publicFunction.localFindPic(1091, 0, 1280, 82, "skip.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  skip=" + result);
                publicFunction.rndTapWH(result.x, result.y, 112, 22);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(30, 436, 123, 522, "end.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  end=" + result);
                publicFunction.rndTap(1237, 21, 1255, 47);
                Thread.sleep(500);
            }
            Thread.sleep(1000);
        }
return 0;
    }

    private void selectTask(String str) throws Exception {
        switch (str) {
            case "bandit":
                return;
            case "LJF":
                LJF();
                return;
            case "teamCopy":
                teamCopy();
                return;
        }
    }

    private void bandit() {


    }

    private void LJF() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(143, 151, 321, 276, "LJF1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  LJF1=" + result);
            publicFunction.rndTapWH(result.x, result.y, 78, 25);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(168, 103, 296, 622, "difficulty" + Integer.toString(TaskMain.taskMap.get("LJF")) + ".png");

        LtLog.i(publicFunction.getLineInfo() + "--------------------  difficulty" + Integer.toString(TaskMain.taskMap.get("LJF")) + ".png" + "=" + result);
        if (result.sim >= 0.8) {


            publicFunction.rndTapWH(result.x, result.y, 52, 25);
            Thread.sleep(1000);
        }
        openGear();
    }

    private void teamCopy() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(0, 73, 119, 219, "teamCopy1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  teamCopy1=" + result);
        } else {
            LtLog.i(publicFunction.getLineInfo() + "--------------------Not  teamCopy1=");
            return;
        }
        int index = TaskMain.taskMap.get("teamCopy");


        for (int i = 0; i < 3; i++) {
            if (index >= 5) {
                publicFunction.RanSwipe(274, 192, 928, 355, 3, 500);
                publicFunction.RanSwipe(274, 192, 928, 355, 3, 500);
            } else {
                publicFunction.RanSwipe(274, 192, 928, 355, 1, 500);
                publicFunction.RanSwipe(274, 192, 928, 355, 1, 500);
            }
            Thread.sleep(500);
        }
        Thread.sleep(2000);


        switch (index) {

            case 1:
                mFairy.onTap(205, 407, 278, 425, "", 1000);
                break;
            case 2:
                mFairy.onTap(520, 407, 580, 419, "", 1000);
                break;
            case 3:
                mFairy.onTap(807, 414, 857, 426, "", 1000);
                break;
            case 4:
                mFairy.onTap(1081, 404, 1114, 419, "", 1000);
                break;
            case 5:
                mFairy.onTap(157, 414, 193, 419, "", 1000);
                break;
            case 6:
                mFairy.onTap(382, 405, 449, 421, "", 1000);
                break;
            case 7:
                mFairy.onTap(671, 406, 739, 417, "", 1000);
                break;
            case 8:
                mFairy.onTap(996, 414, 1040, 421, "", 1000);
                break;
        }



        /*result = publicFunction.localFindPic(159, 355, 347, 475, "teamCopy2.png");
        if (result.sim >= 0.8 && index <= 4) {//209 404   502,404,88,20
            LtLog.i(publicFunction.getLineInfo() + "--------------------  teamCopy2=" + result);
            publicFunction.rndTapWH(result.x + ((index - 1) * 293), result.y, 88, 20);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(268,336,569,484, "teamCopy3.png");
        if (result.sim >= 0.8 && index >= 5) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  teamCopy3=" + result);
            publicFunction.rndTapWH(result.x + ((index - 5) * 293), result.y, 88, 20);
            Thread.sleep(500);

    }*/
    }
    private List<String> setTaskList() {
        List<String> list = new ArrayList<>();
//        if (mFairy.taskMap.get("discipline") == 1) {
//            list.add("discipline");
//        }
        if (TaskMain.taskMap.get("bandit") == 1) {
            list.add("bandit");
        }
        if (TaskMain.taskMap.get("LJF") > 0) {
            list.add("LJF");
        }
        if (TaskMain.taskMap.get("teamCopy") > 0) {
            list.add("teamCopy");
        }
        return list;
    }

    private List<String> setFollowTaskList() {
        List<String> list = new ArrayList<>();
        if (TaskMain.taskMap.get("reprimand") == 1) {
            list.add("reprimand1");
        }
        if (TaskMain.taskMap.get("bandit") == 1) {
            list.add("bandit2");
        }
        if (TaskMain.taskMap.get("teamCopy") > 0) {
            LtLog.i(publicFunction.getLineInfo() + "-------teamCopy==>" + TaskMain.taskMap.get("teamCopy"));
            list.add("followCopy" + Integer.toString(TaskMain.taskMap.get("teamCopy")));
        }
        if (TaskMain.taskMap.get("LJF") > 0) {
            list.add("difficulty" + Integer.toString(TaskMain.taskMap.get("LJF")));
        }
        LtLog.i(publicFunction.getLineInfo() + "-------setFollowTaskList==>" + list);
        return list;
    }

    private int peopleNumber() throws Exception {
        //获取当前队伍人数
        LtLog.i(publicFunction.getLineInfo() + "【开始获取队伍人数】");

        AtFairy2.OpencvResult result;
        int index = 0;
        result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "【不在主场景】");
            return index;
        }

        gamePublicFunction.taskOrTeam("team");

        for (int i = 0; i <= 2; i++) {
            result = publicFunction.localFindPic(59, 271 + (i * 64), 145, 332 + (i * 64), "add.png");
            if (result.sim >= 0.8) {
                index = i + 1;
                break;
            } else {

                result = publicFunction.localFindPic(0, 156, 116, 274, "flag.png" + "|" + "flag2.png");
                if (result.sim >= 0.7) {
                    index = 4;
                }

             /*   FindResult findResult = mFairy.findPic(0, 156, 116, 274,"duiz.png");
                if (findResult.sim >= 0.7f) {
                    index = 4;
                }*/

            }
        }

        LtLog.i(publicFunction.getLineInfo() + "【获取队伍人数结束："+index+"人】");
        return index;
    }

    private List<String> setFollowTask(List<String> list) throws Exception {
        AtFairy2.OpencvResult result;
        AtFairy2.OpencvResult result1;
        result = publicFunction.localFindPic(117, 93, 350, 666, "bandit2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------bandit2>" + result);
            publicFunction.rndTapWH(result.x, result.y, 106, 23);
            Thread.sleep(500);
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < list.size(); j++) {
                LtLog.i(publicFunction.getLineInfo() + "-------list.get(j)>" + list.get(j));
                result = publicFunction.localFindPic(201, 143, 1083, 520, list.get(j) + ".png");
                if (result.sim > 0.8) {
                    //首先 找到副本
                    LtLog.i(publicFunction.getLineInfo() + "-------list.get(j)>" + list.get(j) + "=" + result);
                    result1 = publicFunction.localFindPicHLS(result.x, result.y, result.x + 366, result.y + 71, "0.png");
                    LtLog.i(publicFunction.getLineInfo() + "-------副本次数 0>" + result1);
                    if (result1.sim >= 0.8) {
                        //如果副本次数为0 删除当前list.get(j)
                        LtLog.i(publicFunction.getLineInfo() + "-------副本次数 0>" + result1);
                        list.remove(j);
                        if (list.size() == 0) {
                            return list;
                        }
                        continue;
                    }
                    result1 = publicFunction.localFindPic(result.x - 201, result.y - 20, result.x, result.y + 55, "tick1Off.png");
                    if (result1.sim >= 0.8) {
                        //打钩
                        LtLog.i(publicFunction.getLineInfo() + "-------tick1Off>" + result1);
                        mFairy.tap(result1.x + 5, result1.y + 5);
                        Thread.sleep(1000);
                    }
                }
                Thread.sleep(100);
            }
            if (i % 2 == 1) {
                publicFunction.RanSwipe(291, 169, 962, 490, 0, 500);
            } else {
                publicFunction.RanSwipe(291, 169, 962, 490, 2, 500);
            }
            Thread.sleep(1500);
        }

        if (list.size() > 0) {
            result = publicFunction.localFindPic(854, 500, 1005, 624, "matching.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------matching>" + result);
                publicFunction.rndTapWH(result.x, result.y, 51, 24);
                Thread.sleep(2000);
                gamePublicFunction.closeWindow();
            }
        } else {
            for (int i = 0; i < 3; i++) {
                gamePublicFunction.closeWindow();
                Thread.sleep(2000);
            }
        }


        return list;
    }

    private void reprimand() throws Exception {
        //惩戒
        int[][] XY = {{661, 445}, {620, 490}, {670, 519}, {705, 478}, {722, 447}, {740, 414}, {762, 384}, {791, 410},
                {799, 380}, {787, 357}, {827, 361}, {753, 242}, {725, 298}, {712, 255}, {645, 307}, {675, 237},
                {660, 171}, {618, 215}, {583, 218}, {611, 294}, {561, 257}, {507, 316}, {481, 316}, {501, 343},
                {529, 360}, {481, 374}, {461, 393}, {543, 389}, {513, 416}, {568, 463}};
        int index = 0;
        long time = System.currentTimeMillis() / 1000 - 300, timex = 0;
        AtFairy2.OpencvResult result;
        List<String> taskList = new ArrayList<>();
        taskList.add("reprimand");
        gamePublicFunction.taskOrTeam("team");
        gamePublicFunction.createTeam();
        gamePublicFunction.recall(true);
        LimitlessTask limitlessTask = new LimitlessTask(mFairy);
        while (mFairy.condit()) {

            int peopleNum = peopleNumber();

            timex = System.currentTimeMillis() / 1000 - time;
            if (peopleNum < 3 && timex > 300) {
                LtLog.i(publicFunction.getLineInfo() + "--------------peopleNumber=" + peopleNum + ",timex=" + timex);
                gamePublicFunction.goSecurity();
                gamePublicFunction.openActivity();
                time = System.currentTimeMillis() / 1000;
            }

            result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
            if (result.sim >= 0.8 && peopleNum >= 3) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  activity=" + result);
                //判断当前跟战人数

                if (peopleNum-1!=checkFollowCombatPeopleNumber()){
                    //如果队伍人数和跟战人数不符，调用拉队友跟战，如果不跟战，踢出队伍
                    teamCheck(peopleNum);
                }

                result = publicFunction.localFindPic(1127, 0, 1273, 81, "dianCangShan.png");
                //如果在点苍山
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "--------------------  dianCangShan=" + result);
                    gamePublicFunction.taskOrTeam("team");
                    gamePublicFunction.closeWindow();
                    gamePublicFunction.recall(true);//召回队友
                    gamePublicFunction.openMapWorldOrCurrent("current");
                    mFairy.tap(XY[index][0], XY[index][1] - 20);
                    Thread.sleep(1000);
                    determineflag();
                    mFairy.tap(XY[index][0], XY[index][1]);
                    index = index + 1;
                    gamePublicFunction.closeWindow();
                } else {
                    gamePublicFunction.openMapWorldOrCurrent("world");
                    limitlessTask.selectMap2(6);
                    Thread.sleep(5000);
                    gamePublicFunction.closeWindow();
                }
                boolean XYState = true;
                while (XYState) {
                    //当坐标不变动时 退出循环
                    LtLog.i(publicFunction.getLineInfo() + "--------------XYState=" + XYState);
                    XYState = publicFunction.judgeMatChange(1172, 136, 76, 20, 0.95, 3000);
                    LtLog.i(publicFunction.getLineInfo() + "--------------XYState=" + XYState);
                    Thread.sleep(1000);
                }
                result = publicFunction.localFindPic(313, 165, 815, 539, "reprimandDifficulty1.png"+"|"+"reprimandDifficulty2.png|reprimandDifficulty3.png|reprimandDifficulty4.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "--------------------  reprimandDifficulty=" + result);
                    mFairy.tap(result.x + 79, result.y + 143);
                    Thread.sleep(2000);
                }
                result = publicFunction.localFindPic(453, 320, 639, 447, "dialogue.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "--------------------  dialogue=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 86, 27);
                    gamePublicFunction.switchSkillOrTong("skill");
                    Thread.sleep(1000);
                    gamePublicFunction.manualOrAutomatic("automatic");
                    int determineState = determineComplete();
                    if (determineState == 1) {
                        gamePublicFunction.openActivity();
                    }
                }
            }
            if (index > XY.length) {
                index = 0;
            }
            if (index % 5 == 1) {
                //每移动5 次 查看一次是否任务完成
                gamePublicFunction.openActivity();
            }
            taskList = gamePublicFunction.lookupTask(taskList);
            if (taskList.size() == 0) {
                LtLog.i(publicFunction.getLineInfo() + "------------惩戒完成--=");
                gamePublicFunction.closeWindow();
                gamePublicFunction.goSecurity();
                return;
            }
            result = publicFunction.localFindPic(1027, 553, 1181, 680, "target.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  target=" + result);
                publicFunction.rndTapWH(result.x, result.y, 54, 27);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(492, 261, 677, 381, "target1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  target1=" + result);
                gamePublicFunction.clickDetermine();//点击确认
                Thread.sleep(1000);

                gamePublicFunction.closeWindow();
            }
            result = publicFunction.localFindPic(0, 76, 116, 222, "teamCopy1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  teamCopy1=" + result);
                gamePublicFunction.closeWindow();
            }
            Thread.sleep(2000);
        }
    }

    //打开队伍，点击进入活动，目的：让队友进入跟战状态。
    //等待20秒后， 判断当前跟战的队友，如果没有进入跟战的队友踢出队伍重新匹配
    private void teamCheck(int number_people) throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "--------------------  teamCheck=" );
        AtFairy2.OpencvResult result;
        gamePublicFunction.openTeam();
        Thread.sleep(1000);

        result = publicFunction.localFindPic(125,117,329,665, "reprimand2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  reprimand2=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(2000);
        }
        result = publicFunction.localFindPic(1027, 553, 1181, 680, "target.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  target=" + result);
            publicFunction.rndTapWH(result.x, result.y, 54, 27);
            Thread.sleep(2000);
        }
        result = publicFunction.localFindPic(967, 555, 1122, 680, "into2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  into2=" + result);
//                selectTask(taskList.get(0));
            publicFunction.rndTapWH(result.x, result.y, 55, 25);
            Thread.sleep(2000);
            gamePublicFunction.clickDetermine();
            gamePublicFunction.closeWindow();
            LtLog.i(publicFunction.getLineInfo() + "--------------------  sleep 40S");
            Thread.sleep(40000);
        }

        //检测跟战的人数
        int follow_number_people = 0;
        for (int i = 0; i < 3; i++) {
            result = publicFunction.localFindPic(176, 272 + (i * 64), 302, 332 + (i * 64), "followCombat1.png");
            LtLog.i(publicFunction.getLineInfo() + "--------------------  into2=" + result);
            if (result.sim >= 0.8) {
                follow_number_people = follow_number_people +1;
            } else {
                //如果此人没有跟战 ，踢出队伍。
                result = publicFunction.localFindPic(59, 271 + (i * 64), 145, 332 + (i * 64), "add.png");
                LtLog.i(publicFunction.getLineInfo() + "--------------------  add=" + result);
                if (result.sim < 0.8) {
                    publicFunction.rndTap(20, 286 + (i * 64), 53, 313 + (i * 64));//点头像
                    Thread.sleep(2000);
                }
                result = publicFunction.localFindPic(140, 127, 456, 571, "pleaseLeave.png");
                LtLog.i(publicFunction.getLineInfo() + "--------------------  pleaseLeave=" + result);
                if (result.sim >= 0.8) {
                    publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                    Thread.sleep(2000);
                    gamePublicFunction.clickDetermine();
                }
                break;
            }
        }
        LtLog.i(publicFunction.getLineInfo() + "--------------------  follow_number_people=" + follow_number_people + ",number_people=" + number_people);

    }

    //返回当前跟战的人数
    private int checkFollowCombatPeopleNumber() {
        AtFairy2.OpencvResult result;
        int follow_number_people=0;
        for (int i = 0; i < 3; i++) {
            result = publicFunction.localFindPic(176, 272 + (i * 64), 302, 332 + (i * 64), "followCombat1.png");
            LtLog.i(publicFunction.getLineInfo() + "--------------------  into2=" + result);
            if (result.sim >= 0.8) {
                follow_number_people = follow_number_people +1;
            }

        }

        LtLog.i(publicFunction.getLineInfo() + "--------------------  follow_number_people=" + follow_number_people);
        return follow_number_people;
    }

    private int determineComplete() throws Exception {
        //判断打怪完成
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 100; i++) {
            result = publicFunction.localFindPic(665, 489, 776, 623, "reprimandOff.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  reprimandOff=" + result);
                result = publicFunction.localFindPic(729, 483, 797, 612, "0-1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "--------------------  0-1=" + result);
                    //发现0次
                    return 1;
                }
                return 0;
            }
            Thread.sleep(500);
        }
        return 0;
    }

    private void determineflag() throws Exception {
        //移动判断
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 7; i++) {
            result = publicFunction.localFindPic(363, 82, 921, 636, "flag1.png");
            if (result.sim >= 0.9) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  flag1=" + result);
            } else {
                break;
            }
            Thread.sleep(900);
        }


    }

    private void openGear() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(507, 36, 773, 127, "openGear.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  openGear=" + result);
            result = publicFunction.localFindPic(472, 62, 555, 112, "openGear1.png");
            if (result.sim >= 0.9) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  openGear1=" + result);
                publicFunction.rndTap(504, 200, 521, 214);
                Thread.sleep(5000);
            }
            result = publicFunction.localFindPic(258, 294, 365, 365, "openGear2.png");
            if (result.sim >= 0.9) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  openGear2=" + result);
                publicFunction.rndTap(321, 498, 338, 537);
                Thread.sleep(5000);
            }
        }
    }

    //阵法试炼
    public int experiment() throws Exception {
        AtFairy2.OpencvResult result;
        List<String> taskList = new ArrayList<>();
        taskList.add("experiment1");
        long sleepTime = 0;
        LtLog.i(publicFunction.getLineInfo() + "--------------------  experiment=");
        MatTime matTime = new MatTime(mFairy);
        gamePublicFunction.goSecurity();
        gamePublicFunction.recall(true);
        gamePublicFunction.openActivity();
        while (mFairy.condit()) {
            sleepTime = matTime.mMatTime(1177, 140, 69, 15, 0.9f);
            LtLog.i(publicFunction.getLineInfo() + "--------------------  sleepTime=" + sleepTime);
            if (sleepTime >= 30) {
                gamePublicFunction.openActivity();
            }
            if (taskList.size() > 0) {
                taskList = gamePublicFunction.lookupTask(taskList);//判断活动状态
                Thread.sleep(2000);
                if (taskList.size() == 0) {
                    LtLog.i(publicFunction.getLineInfo() + "--------------------  阵法试炼完成=");
                    gamePublicFunction.closeWindow();
                    return 99;
                }
            }

            result = publicFunction.localFindPic(387, 337, 771, 588, "experiment.png|experiment2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  experiment=" + result);
                publicFunction.rndTapWH(result.x, result.y, 58, 28);
                Thread.sleep(500);
            } else {
                result = publicFunction.localFindPic(412, 357, 666, 610, "other.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "--------------------  other=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 57, 28);
                    Thread.sleep(500);
                }
            }
            result = publicFunction.localFindPic(365, 319, 520, 446, "commerce2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  commerce2=" + result);
                publicFunction.rndTapWH(result.x, result.y, 55, 27);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(400, 245, 587, 366, "notAround.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  notAround=" + result);
                publicFunction.rndTap(608, 435, 680, 469);//点好的
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(273, 313, 397, 440, "A.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  A=" + result);
                publicFunction.rndTap(363, 359, 473, 400);
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

return 0;
    }

    //决战凌绝峰
    public int droiyanLJF() throws Exception {
        AtFairy2.OpencvResult result;
        List<String> taskList = new ArrayList<>();
        taskList.add("droiyanLJF");
        long sleepTime = 0;
        LtLog.i(publicFunction.getLineInfo() + "--------------------  droiyanLJF=");
        MatTime matTime = new MatTime(mFairy);
        gamePublicFunction.goSecurity();
        gamePublicFunction.recall(true);
        gamePublicFunction.openActivity();
        PicTime into3_picTime = new PicTime(949, 574, 1159, 686, "into3.png", 0.8, mFairy);
        while (mFairy.condit()) {
            sleepTime = matTime.mMatTime(1177, 140, 69, 15, 0.9f);
            LtLog.i(publicFunction.getLineInfo() + "--------------------  sleepTime=" + sleepTime);
            if (sleepTime >= 30) {
                gamePublicFunction.openActivity();
            }
            gamePublicFunction.manualOrAutomatic("automatic");
            if (taskList.size() > 0) {
                taskList = gamePublicFunction.lookupTask(taskList);//判断活动状态
                Thread.sleep(2000);
                if (taskList.size() == 0) {
                    LtLog.i(publicFunction.getLineInfo() + "--------------------  决战凌绝峰=");
                    gamePublicFunction.closeWindow();
                    return 99;
                }
            }
            if (into3_picTime.getPicTime() >= 30) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  无法进入 ,或已完成=");
                gamePublicFunction.closeWindow();
                return 99;
            }
            result = publicFunction.localFindPic(949, 574, 1159, 686, "into3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  into3=" + result);
                publicFunction.rndTapWH(result.x, result.y, 50, 22);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(1011, 554, 1261, 678, "determine2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  determine2=" + result);

//                165,174
                int x = random.nextInt(3);
                int y = random.nextInt(2);

                publicFunction.rndTap(667 + (x * 165), 118 + (y * 174), 704 + (x * 165), 133 + (y * 174)); //随机选择一个门派

                Thread.sleep(1000);
                publicFunction.rndTapWH(result.x, result.y, 50, 22);
                Thread.sleep(5000);
            }
            result = publicFunction.localFindPic(1091, 0, 1280, 82, "skip.png");
            if (result.sim >= 0.7) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  skip=" + result);
                publicFunction.rndTapWH(result.x, result.y, 112, 22);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(815, 495, 1126, 673, "continue.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------  continue=" + result);
                publicFunction.rndTapWH(result.x, result.y, 112, 22);
                Thread.sleep(500);
            }
            Thread.sleep(1000);

        }

return 0;
    }


}
