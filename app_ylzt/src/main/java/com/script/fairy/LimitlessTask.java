package com.script.fairy;

import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairy2;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-11-05.
 */

public class LimitlessTask {

    private AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    private GamePublicFunction gamePublicFunction;
    private int Resurrection_index = 0;//记录复活次数
    private FamilyTask familyTask;
    private TimingActivity timingActivity;
    public static int ResurrectionIndex=0;

    public LimitlessTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(ypFairy);
        gamePublicFunction = new GamePublicFunction(ypFairy);
        familyTask = new FamilyTask(ypFairy);
        timingActivity = new TimingActivity(ypFairy);
    }

    public void outdoorsOnHook() throws Exception {
        AtFairy2.OpencvResult result;

        LtLog.i(publicFunction.getLineInfo() + "------outdoorsOnHook->");

        long time = (System.currentTimeMillis() / 1000)-400, timex = 0;
        long time1 = System.currentTimeMillis() / 1000 - 960, time1x = 0;
        long treasureTime = System.currentTimeMillis() / 1000 - 1260, treasureTimex;//挖宝间隔

        long sleepTime = 60;
        ResurrectionIndex = 0;
        LtLog.i(publicFunction.getLineInfo() + "------outdoorsOnHook->");
        while (mFairy.condit()) {
            int currentTime = publicFunction.getMinuteNumber();

            LtLog.i(publicFunction.getLineInfo() + "------currentTime->" + currentTime);

            if (currentTime >= 1199 && currentTime < 1260 && TaskMain.taskList.indexOf("country") > -1) {
                //20:00 - 21:00 检测是否有国战
                result = publicFunction.localFindPic(516, 457, 794, 602, "enterWar.png");
                AtFairy2.OpencvResult result1 = publicFunction.localFindPic(508, 435, 751, 669, "nationalWar_token.png");
                LtLog.i(publicFunction.getLineInfo() + "-----------------国战开始---------enterWar->" + result + ",nationalWar_token=" + result1);
                if (result.sim >= 0.8 || result1.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-----------------国战开始---------enterWar->" + result);
                    nationalWar();
                }
            } else {
                //家族试炼
                if (TaskMain.taskList.indexOf("family_experiment") > -1) {
                    result = publicFunction.localFindPic(1048, 236, 1227, 357, "family_experiment.png");
                    if (result.sim >= 0.8) {
                        //如果已开启试炼 进入
                        LtLog.i(publicFunction.getLineInfo() + "------family_experiment->" + result);
                        gamePublicFunction.goMianCity();
                        publicFunction.rndTapWH(result.x, result.y, 79, 21);
                        Thread.sleep(500);
                        familyTask.familyExperiment();
                    } else {
                        //没有入口自己打怪去
                        if ((currentTime >= 780 && currentTime <= 900) || (currentTime >= 1260 && currentTime <= 1320)) {
                            // 13:00--15:00  and  21:00 -- 22:00
                            familyTask.familyExperiment2();
                        }
                    }
                }
            }

            if ((currentTime >= 1140 && currentTime < 1169) || (currentTime >= 1230 && currentTime < 1380)) {
                //19:00 - 19:30  和 20:30 - 23：00 之间，检测家族任务
                LtLog.i(publicFunction.getLineInfo() + "------family_Task->");
                if (TaskMain.familyList.size() > 0) {
                    LtLog.i(publicFunction.getLineInfo() + "------family_Task->");
                    familyTask.mFamilyTask();
                }
            }


            if (currentTime >= 1165 && currentTime < 1172) {
                //太常战  19:30 开始 提前5分钟回到王城等待
                LtLog.i(publicFunction.getLineInfo() + "------anOffical->");
                if (TaskMain.taskList.indexOf("anOffical") > -1) {
                    LtLog.i(publicFunction.getLineInfo() + "------anOffical->");
                    timingActivity.anOffical();
                }
            }

            timex = System.currentTimeMillis() / 1000 - time;

            if (timex % 60 == 0) {
                LtLog.i(publicFunction.getLineInfo() + "------outdoorsOnHook   timex->" + timex + ",time1x=" + time1x);
            }

            if (TaskMain.taskList.indexOf("treasure") > -1) {
                treasureTimex = System.currentTimeMillis() / 1000 - treasureTime;
                LtLog.i(publicFunction.getLineInfo() + "------treasureTimex->" + treasureTimex);
                //间隔21分钟；
                if (treasureTimex >= 1260) {
                    DigTreasure();
                    treasureTime = System.currentTimeMillis() / 1000;
                }
            }

            result = publicFunction.localFindPic(284, 295, 498, 393, "Resurrection4.png"+"|"+"Resurrection.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------Resurrection->" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
                Thread.sleep(5000);
                ResurrectionIndex=ResurrectionIndex+1;
                timex = 301;
            }

            //间隔10分钟定位一次
            if (timex >= 300) {

                time = System.currentTimeMillis() / 1000;

                xh:for (int i = 0; i < 3; i++) {
//                    result = publicFunction.localFindPic(1085, 0, 1270, 79, "LuoYang2.png");
                    if (!rolePosition()) {      //判断角色当前位置与设置挂机图是否匹配
                        LtLog.i(publicFunction.getLineInfo() +"不在目标地图中 - 开始选择地图");
                        //goLuoYang2();
                        gamePublicFunction.goMianCity();//去主城

                        goCarterToMap();//去指定地图

                        Thread.sleep(1000);
                    } else {
                        LtLog.i(publicFunction.getLineInfo() +"在目标地图中 - 开始定位坐标");

                        gamePublicFunction.openMap("current");

                        if (ResurrectionIndex>=TaskMain.xyList.size()){
                            ResurrectionIndex=0;
                        }

                        LtLog.i(publicFunction.getLineInfo() + "------ResurrectionIndex->" + ResurrectionIndex + "," +
                                "x=" + ((int[]) TaskMain.xyList.get(ResurrectionIndex))[0] + ",y:" + ((int[]) TaskMain.xyList.get(ResurrectionIndex))[1]);

                        screenXY(((int[]) TaskMain.xyList.get(ResurrectionIndex))[0], ((int[]) TaskMain.xyList.get(ResurrectionIndex))[1]);


                        for (int n = 0; n < 60; n++) {
                            long sleepTime1 = mFairy.mMatTime(1117, 51, 77, 22, 0.95f);
                            LtLog.i(publicFunction.getLineInfo() + "------sleepTime->" + sleepTime1);
                            if (sleepTime1 >= 5) {
                                break;
                            }

                            Thread.sleep(1000);

                            result = publicFunction.localFindPic(284, 295, 498, 393, "Resurrection.png");
                            if (result.sim >= 0.8) {
                                LtLog.i(publicFunction.getLineInfo() + "------Resurrection->" + result);
                                publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
                                Thread.sleep(2000);
                                ResurrectionIndex=ResurrectionIndex+1;
                                continue xh;
                            }
                        }
                        gamePublicFunction.automaticCombat(1);
                        break;
                    }
                }

            }

            //掉队经验领取
            time1x = System.currentTimeMillis() / 1000 - time1;
            if (time1x >= 960) {
                LtLog.i(publicFunction.getLineInfo() + "------outdoorsOnHook   " + ",time1x=" + time1x + ",experience==" + TaskMain.taskList.indexOf("experience"));
                time1 = System.currentTimeMillis() / 1000;
                if (TaskMain.taskList.indexOf("experience") > -1) {
                    experienceTask();
                }
            }
            Thread.sleep(1000);
        }
    }

    private void DigTreasure() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "------DigTreasure->");
        AtFairy2.OpencvResult result;
        gamePublicFunction.switchDaily(); //  切换到日常
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(265, 240, 875, 524, "need.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------need->" + result);
                break;
            }
            result = publicFunction.localFindPic(721, 0, 859, 124, "feedback.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------feedback->" + result);
                publicFunction.rndTapWH(result.x, result.y, 38, 26);
                Thread.sleep(1500);
            }
            result = publicFunction.localFindPic(169, 143, 270, 668, "treasure.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------treasure->" + result);
                publicFunction.rndTapWH(result.x, result.y, 64, 23);
                Thread.sleep(1500);
            }
            result = publicFunction.localFindPic(134, 104, 298, 226, "treasure1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------treasure1->" + result);
                result = publicFunction.localFindPic(266, 250, 795, 515, "treasure4.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------treasure4->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 32, 29);
                    Thread.sleep(2000);
                }
                gamePublicFunction.clickDetermine(526, 417, 762, 524);
                result = publicFunction.localFindPic(252, 421, 461, 538, "treasure2.png");
                LtLog.i(publicFunction.getLineInfo() + "------treasure2->" + result);
                if (result.sim >= 0.8) {
                    publicFunction.rndTap(336, 388, 358, 400);
                    Thread.sleep(1500);
                    break;
                }
                result = publicFunction.localFindPic(437, 321, 605, 404, "treasure2.png");
                LtLog.i(publicFunction.getLineInfo() + "------treasure2->" + result);
                if (result.sim >= 0.8) {
                    publicFunction.rndTap(501, 316, 533, 336);
                    Thread.sleep(1500);
                    break;
                }
                result = publicFunction.localFindPic(602, 321, 788, 419, "treasure2.png");
                LtLog.i(publicFunction.getLineInfo() + "------treasure2->" + result);
                if (result.sim >= 0.8) {
                    publicFunction.rndTap(692, 305, 716, 320);
                    Thread.sleep(1500);
                    break;
                }
                result = publicFunction.localFindPic(1039, 475, 1150, 590, "0.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------treasure  over ->" + result);
                    //挖宝完成
                    TaskMain.taskList.remove("treasure");
                    break;
                }
            }
            Thread.sleep(1000);
        }
        for (int i = 0; i < 3; i++) {
            gamePublicFunction.closeWindow();
        }
    }

    //点击当前地图坐标
    private void screenXY(int gmx, int gmy) throws Exception {
        AtFairy2.OpencvResult result;
        int map = TaskMain.map;
        double x = 0, y = 0;
        result = publicFunction.localFindPic(961, 606, 1167, 720, "nationalGeography.png");
        LtLog.i(publicFunction.getLineInfo() + "------result->" + result);
        if (result.sim < 0.8) {
            return;
        }
        switch (map) {
            case 1:
                x = gmx * 1.5404 + gmy * -0.0047 + 86.3838;
                y = gmx * 0.0031 + gmy * -1.56 + 742.0367;
                break;
            case 2:
                x = gmx * 1.3006 + gmy * 0.0042 + 95.252;
                y = gmx * 0.0143 + gmy * -1.2661 + 805.0338;
                break;
            case 3:
                x = gmx * 0.9913 + gmy * 0.012 + 66.0007;
                y = gmx * -0.0029 + gmy * -0.9747 + 660.6608;
                break;
            case 4:
                x = gmx * 1.8588 + gmy * 0.0081 + -283.3683;
                y = gmx * 0.0018 + gmy * -1.8397 + 1298.5419;
                break;
            case 5:
                x = gmx * 3.3504 + gmy * 0.012 + 103.1949;
                y = gmx * -0.0278 + gmy * -3.3778 + 723.8833;
                break;
            case 6:
                x = gmx * 1.9455 + gmy * -0.0023 + 133.4543;
                y = gmx * 0.0112 + gmy * -1.9678 + 671.9539;
                break;
            case 7:
                x = gmx * 2.9461 + gmy * -0.0181 + -184.4698;
                y = gmx * -0.0199 + gmy * -3.0134 + 1165.6924;
                break;
            case 8:
                x = gmx * 3.3844 + gmy * -0.0395 + 106.6795;
                y = gmx * 0.0158 + gmy * -3.3644 + 716.8521;
//                x = gmx * 1.1558 + gmy * 1.4693 + 164.0888;
//                y = gmx * -0.0038 + gmy * -3.3638 + 719.149;
                break;
            case 9:
                x = gmx * 2.047 + gmy * 0.0074 + 155.8241;
                y = gmx * -0.0035 + gmy * -2.088 + 659.0908;
                break;
            case 10:
                //芒砀山 ,
                x = gmx * 2.1039 + gmy * 0.0113 + -377.23;
                y = gmx * -0.0035 + gmy * -2.027 + 1384.2551;
                break;
            case 11:
                x = gmx * 1.7378 + gmy * 0.0039 + 147.2726;
                y = gmx * -0.0032 + gmy * -1.7357 + 633.7744;
                break;
            case 12:
                x = gmx * 2.2202 + gmy * -0.0056 + 6.495;
                y = gmx * -0.0064 + gmy * -2.1108 + 724.2911;
                break;
            //13-22 紫龙窟1-10层
            case 13:
                //1
                x = gmx * 2.5209 + gmy * -0.0537 + 94.3507;
                y = gmx * -0.0254 + gmy * -3.0776 + 573.3955;
                break;
            case 14:
                //2
                x = gmx * -2.5348 + gmy * 0.0204 + 823.7583;
                y = gmx * -0.0404 + gmy * 2.821 + 240.8408;
                break;
            case 15:
                //3
                x = gmx * 0.0197 + gmy * 2.9836 + 284.5645;
                y = gmx * 2.5392 + gmy * -0.0029 + 27.2079;
                break;
            case 16:
                //4
                x = gmx * -0.0045 + gmy * -3.0293 + 629.5272;
                y = gmx * -2.5298 + gmy * 0.0199 + 760.7881;
                break;
            case 17:
                //5
                x = gmx * -2.5341 + gmy * -0.0069 + 825.4116;
                y = gmx * 0.0033 + gmy * 3.0466 + 221.9167;
                break;
            case 18:
                //6
                x = gmx * 0.0032 + gmy * 3.0189 + 283.8696;
                y = gmx * 2.5303 + gmy * 0.0185 + 26.299;
                break;
            case 19:
                //7
                x = gmx * -0.0147 + gmy * -2.9416 + 626.1218;
                y = gmx * -2.5305 + gmy * -0.0138 + 763.1945;
                break;

            case 20:
                //8
                x = gmx * -2.5511 + gmy * 0.0195 + 825.7567;
                y = gmx * -0.0083 + gmy * 2.979 + 227.0087;
                break;
            case 21:
                //9 紫龙窟

                break;
            case 22:
                //10 紫龙窟

                break;
            case 23:
                x = gmx * 2.1133 + gmy * 0.0013 + -375.8062;
                y = gmx * -0.0081 + gmy * -2.0375 + 1391.9502;
                break;
            case 24:
                x = gmx *1.0439+gmy *-0.0068+100.7425;
                y=gmx *-0.021+gmy *-1.0249+900.0951;
                break;

            case 25:
                x=gmx *0.4937+gmy *0.011+137.2134;
                y=gmx *0.001+gmy *-0.4908+707.7567;
                break;
            case 26:
                x=gmx *1.0993+gmy *-0.0091+206.8455;
                y=gmx *0.0142+gmy *-1.086+647.8353;
                break;

        }
        LtLog.i(publicFunction.getLineInfo() + "------tap map ->" + x + "," + y);
        mFairy.tap((int) x, (int) y);
        Thread.sleep(1000);
        gamePublicFunction.closeWindow();

    }

    //掉队经验领取
    private void experienceTask() throws Exception {
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 3; i++) {
            result = publicFunction.localFindPic(0, 591, 115, 714, "experience.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------experience->" + result);
                publicFunction.rndTapWH(result.x, result.y, 35, 23);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(592, 561, 802, 690, "experience_retrieve.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------retrieve->" + result);
                publicFunction.rndTapWH(result.x, result.y, 95, 22);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(904, 102, 1093, 222, "experience1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------experience1->" + result);
                publicFunction.rndTapWH(result.x, result.y, 89, 20);
                Thread.sleep(1000);
                gamePublicFunction.closeWindow();
                break;
            }
            result = publicFunction.localFindPic(840, 116, 993, 239, "star1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------star1->" + result);
                gamePublicFunction.closeWindow();
                break;
            }
            result = publicFunction.localFindPic(892, 115, 1048, 240, "experience2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------experience2->" + result);
                publicFunction.rndTapWH(result.x, result.y, 56, 25);
                Thread.sleep(500);
            }
            Thread.sleep(1000);
        }

        gamePublicFunction.closeWindow();
    }


    private void openCombat() throws Exception {

    }

    //移动到洛阳
    private void goLuoYang() throws Exception {
        AtFairy2.OpencvResult result;
        MatTime matTime = new MatTime(mFairy);
        List<String> currentMap = new ArrayList<>();
        currentMap.add(0, "");
        currentMap.add(1, "WangCheng.png");
        currentMap.add(2, "YangPingGuan.png");
        currentMap.add(3, "BianJing.png");
        currentMap.add(4, "LuoYang.png");
        long sleepTime = 0;
        long time = System.currentTimeMillis() / 1000, timex = 0;

        while (mFairy.condit()) {
            timex = System.currentTimeMillis() / 1000;
            if (timex - time >= 600) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------移动到洛阳超过10分钟，退出------->");
                return;
            }
            sleepTime = matTime.mMatTime(1119, 54, 71, 15, 0.8);
            LtLog.i(publicFunction.getLineInfo() + "------sleepTime->" + sleepTime);
            if (sleepTime >= 10) {
                gamePublicFunction.openMap("current");
            }
            result = publicFunction.localFindPic(961, 606, 1167, 720, "nationalGeography.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------nationalGeography->" + result);
                for (int i = 1; i <= 4; i++) {
                    result = publicFunction.localFindPic(169, 18, 449, 86, currentMap.get(i));
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------" + currentMap.get(i) + "->" + result);
                        selectMap(i);
                        break;
                    }
                }
                gamePublicFunction.closeWindow();
            }
            result = publicFunction.localFindPic(1085, 0, 1270, 79, "LuoYang2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------LuoYang2->" + result);
                return;
            }
            Thread.sleep(1000);
        }
    }

    private void goLuoYang2() throws Exception {
        gamePublicFunction.openMap("current");
        AtFairy2.OpencvResult result, result1;
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            result1 = publicFunction.localFindPic(107, 0, 285, 105, "nationalGeography1.png");
            if (result1.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------nationalGeography1->" + result1);
                publicFunction.rndTapWH(1024, 363, 32, 86);//点中立区
                Thread.sleep(2000);
                for (int j = 0; j < 20; j++) {
                    result = publicFunction.localFindPic(719, 0, 1280, 437, "fork.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------fork->" + result);
                        publicFunction.rndTapWH(result.x, result.y, 29, 29);
                        Thread.sleep(2000);
                        break;
                    }

                    Thread.sleep(1000);
                }
                break;
            }
            result = publicFunction.localFindPic(169, 18, 449, 86, "WangCheng.png");
            if (result.sim < 0.8 && result1.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------WangCheng->" + result);
                gamePublicFunction.goMianCity();
                return;
            }
            result = publicFunction.localFindPic(845, 59, 1030, 182, "npc.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------npc->" + result);
                publicFunction.rndTapWH(result.x, result.y, 85, 23);
                Thread.sleep(5000);
            }
            result = publicFunction.localFindPic(841, 139, 1050, 641, "driver.png");
            LtLog.i(publicFunction.getLineInfo() + "------driver->" + result);
            if (result.sim >= 0.8) {
                publicFunction.rndTapWH(result.x, result.y, 114, 24);
                Thread.sleep(500);
            }
            Thread.sleep(1000);
        }
    }

    //移动到王城车夫,选择挂机地图
    private void goCarterToMap() throws Exception {
        AtFairy2.OpencvResult result, result1;

        gamePublicFunction.openMap("current");

        for (int i = 0; i < 10; i++) {

            result = publicFunction.localFindPic(505, 401, 781, 499, "ZLK1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------ZLK1->" + result);
                publicFunction.rndTapWH(result.x + 36, result.y + 54, 25, 23);
                Thread.sleep(1000);
                select_ZLK_map();//选择紫龙窟地图
                break;
            }

            result1 = publicFunction.localFindPic(107, 0, 285, 105, "nationalGeography1.png");
            if (result1.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------nationalGeography1->" + result1);
                selectMap();//选择普通地图
                break;
            }


            result = publicFunction.localFindPic(169, 18, 449, 86, "WangCheng.png");
            if (result.sim < 0.8 && result1.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------WangCheng->" + result);
                gamePublicFunction.goMianCity();
                return;
            }
            if (TaskMain.map > 12 && TaskMain.map <= 22) {
                //如果勾选的是紫龙窟
                result = publicFunction.localFindPic(767, 203, 1135, 636, "ZLK.png");
                LtLog.i(publicFunction.getLineInfo() + "------ZLK->" + result);
                if (result.sim >= 0.8) {
                    publicFunction.rndTapWH(result.x, result.y, 114, 24);
                    Thread.sleep(1000);
                    gamePublicFunction.closeWindow();
                }
            } else if (TaskMain.map == 10) {
                //芒砀山,  不能通过车夫传送，直接飞过去
                gamePublicFunction.openMap("world");

            } else {
                //如果不是紫龙窟，通过王城车夫传送
                result = publicFunction.localFindPic(845, 59, 1030, 182, "npc.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------npc->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 85, 23);
                    Thread.sleep(5000);
                }
                result = publicFunction.localFindPic(841, 139, 1050, 641, "driver.png");
                LtLog.i(publicFunction.getLineInfo() + "------driver->" + result);
                if (result.sim >= 0.8) {
                    publicFunction.rndTapWH(result.x, result.y, 114, 24);
                    Thread.sleep(500);
                }
            }
            Thread.sleep(1000);
        }
    }

    private void selectMap() throws Exception {
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 10; i++) {
            switch (TaskMain.map) {
                case 1:
                    publicFunction.rndTap(138,149,157,178);
                    break;
                case 2:
                    publicFunction.rndTap(225,259,246,280);
                    break;
                case 3:
                    publicFunction.rndTap(348,361,366,378);
                    break;
                case 4:
                    publicFunction.rndTap(452,186,477,212);
                    break;
                case 5:
                    publicFunction.rndTap(620,522,651,543);
                    break;
                case 6:
                    publicFunction.rndTap(409,513,433,530);
                    break;
                case 7:
                    publicFunction.rndTap(229,494,254,513);
                    break;
                case 8:
                    publicFunction.rndTap(516,596,536,609);
                    break;
                case 9:
                    publicFunction.rndTap(332,600,358,615);
                    break;
                case 10:
                    //芒砀山  这个图无法从车夫过去，需要打开大地图 直接传送过去，所以需要点确认
                    publicFunction.rndTap(123,560,141,579);
                    Thread.sleep(2000);
                    gamePublicFunction.clickDetermine(531,448,834,500);
                    break;
                case 11:
                    publicFunction.rndTap(912,202,930,233);
                    break;
                case 12:
                    publicFunction.rndTap(600,287,616,310);
                    break;
                case 23:
                    publicFunction.rndTap(111,401,143,426);
                    break;
                case 24:
                    publicFunction.rndTap(934,507,951,542);//孤烟壁
                    break;
                case 25:
                    publicFunction.rndTap(1112,571,1135,587);//楼兰古城
                    break;
                case 26:
                    publicFunction.rndTap(1037,367,1059,388);//龟骆林
                    break;


            }

            Thread.sleep(2000);
            result = publicFunction.localFindPic(107, 0, 285, 105, "nationalGeography1.png");
            if (result.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------nationalGeography1->" + result);
                Thread.sleep(4000);
                break;
            }
            Thread.sleep(1000);
        }
        for (int i = 0; i < 30 ; i++) {
            gamePublicFunction.closeWindow();
            Thread.sleep(1000);
            result = publicFunction.localFindPic(890, 0, 1020, 123, "activity.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "------activity->" + result);
                break;
            }
        }
    }

    private void select_ZLK_map() throws Exception {
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(505, 401, 781, 499, "ZLK1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------ZLK1->" + result);
                publicFunction.rndTapWH(result.x + 36, result.y + 54, 25, 23);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(152, 16, 334, 103, "ZLK2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------ZLK2->" + result);
                if (TaskMain.map == 22) {
                    //要去紫龙窟10层，需要滑动一下
                    publicFunction.RanSwipe(1020, 223, 1084, 594, 2, 500);
                    Thread.sleep(2000);
                    publicFunction.rndTap(1033, 646, 1075, 660);
                    Thread.sleep(1000);
                } else {
                    int k = TaskMain.map - 13;
                    publicFunction.rndTap(1031, 130 + (k * 65), 1075, 147 + (k * 65));
                    Thread.sleep(1000);
                }
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(632, 371, 890, 506, "delivery2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------delivery2->" + result);
                publicFunction.rndTapWH(result.x, result.y, 20, 20);
                Thread.sleep(1000);
                //根据所去紫龙窟的层数，定义等待时间
                for (int j = 0; j < TaskMain.map - 12; j++) {
                    LtLog.i(publicFunction.getLineInfo() + "------紫龙窟   移动等待->" + j);
                    result = publicFunction.localFindPic(1024, 3, 1274, 71, "mianCity.png");
                    LtLog.i(publicFunction.getLineInfo() + "------mianCity->" + result);
                    if (result.sim >= 0.75) {
                        break;
                    }
                    Thread.sleep(21000);
                }
            }
        }
    }

    private void selectMap(int index) throws Exception {
        AtFairy2.OpencvResult result;
        switch (index) {
            case 1:
                result = publicFunction.localFindPic(855, 223, 1044, 353, "YangPingGuan1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------YangPingGuan1->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 89, 30);
                    Thread.sleep(500);
                }
                break;
            case 2:
                result = publicFunction.localFindPic(870, 283, 1024, 406, "BianJing1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------BianJing1->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 54, 23);
                    Thread.sleep(500);
                }

                break;
            case 3:
                result = publicFunction.localFindPic(841, 284, 1051, 404, "LuoYang1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------LuoYang1->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 110, 20);
                    Thread.sleep(500);
                }
                break;
            case 4:
                gamePublicFunction.closeWindow();
                return;
        }


    }

    private void nationalWar() throws Exception {
        AtFairy2.OpencvResult result;
        long time = System.currentTimeMillis() / 1000, timex = 0;
        while (mFairy.condit()) {
            result = publicFunction.localFindPic(654, 10, 830, 152, "nationalWar_animals.png|nationalWar_animals1.png");
            LtLog.i(publicFunction.getLineInfo() + "------nationalWar_animals->" + result);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------nationalWar_animals->" + result);
                int num;
                for (int i = 0; i < 2; i++) {
                    num = publicFunction.getColorNunber2(514, 105, 68, 2, "128,9,9", 0.8);
                    LtLog.i(publicFunction.getLineInfo() + "------青龙 num->" + num);
                    if (num >= 20) {
                        publicFunction.rndTap(538, 64, 553, 83);
                        Thread.sleep(5000);
                        break;
                    }
                    num = publicFunction.getColorNunber2(607, 104, 70, 3, "128,9,9", 0.8);
                    LtLog.i(publicFunction.getLineInfo() + "------朱雀 num->" + num);
                    if (num >= 20) {
                        publicFunction.rndTap(627, 62, 656, 84);
                        Thread.sleep(5000);
                        break;
                    }
                    num = publicFunction.getColorNunber2(797, 104, 71, 3, "128,9,9", 0.8);
                    LtLog.i(publicFunction.getLineInfo() + "------玄武 num->" + num);
                    if (num >= 20) {
                        publicFunction.rndTap(821, 62, 844, 84);
                        Thread.sleep(5000);
                        break;
                    }
                    num = publicFunction.getColorNunber2(893, 104, 69, 4, "128,9,9", 0.8);
                    LtLog.i(publicFunction.getLineInfo() + "------白虎 num->" + num);
                    if (num >= 20) {
                        publicFunction.rndTap(907, 60, 935, 85);
                        Thread.sleep(5000);
                        break;
                    }
                }
            }
            result = publicFunction.localFindPic(516, 457, 794, 602, "enterWar.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------enterWar->" + result);
                publicFunction.rndTapWH(result.x, result.y, 10, 10);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(508, 435, 751, 669, "nationalWar_token.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------nationalWar_token->" + result);
                publicFunction.rndTapWH(result.x, result.y, 30, 10);
                Thread.sleep(10000);
            }
            result = publicFunction.localFindPic(284, 295, 498, 393, "Resurrection.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------Resurrection->" + result);
                Resurrection();
            }
            result = publicFunction.localFindPic(584, 629, 847, 713, "nationalWar_receive.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------国战结束-----------------nationalWar_receive->" + result);
                publicFunction.rndTapWH(result.x, result.y, 30, 10);
                Thread.sleep(1000);
                for (int i = 0; i < 3; i++) {
                    gamePublicFunction.closeWindow();
                }
                return;
            }
            timex = System.currentTimeMillis() / 1000;
            if (timex - time >= 1800) {
                LtLog.i(publicFunction.getLineInfo() + "--------------------国战时间超过30分钟，退出------->");
                return;
            }
            Thread.sleep(1000);
        }
    }

    //复活
    public void Resurrection() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "------Resurrection->");
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(520, 297, 758, 390, "Resurrection1.png|Resurrection2.png|Resurrection3.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------Resurrection1->" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(284, 295, 498, 393, "Resurrection.png");
        if (result.sim < 0.8) {
            return;
        }
        result = publicFunction.localFindPic(842, 376, 962, 447, "free.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "------free->" + result);
            publicFunction.rndTap(842, 325, 960, 358);
            Thread.sleep(1000);
            return;
        }
        result = publicFunction.localFindPic(589, 389, 699, 440, "free.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "------free->" + result);
            publicFunction.rndTap(595, 325, 703, 358);
            Thread.sleep(1000);
            return;

        }
        if (TaskMain.taskList.indexOf("revive") > -1) {
            //勾选了原地复活
            result = publicFunction.localFindPic(284, 295, 498, 393, "Resurrection.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------Resurrection->" + result);
                publicFunction.rndTap(595, 325, 703, 358);
                Thread.sleep(2000);
            }
        }
        result = publicFunction.localFindPic(284, 295, 498, 393, "Resurrection.png");
        if (result.sim >= 0.8 && Resurrection_index <= 5) {
            LtLog.i(publicFunction.getLineInfo() + "------Resurrection->" + result);
            publicFunction.rndTap(595, 325, 703, 358);
            Thread.sleep(2000);
            Resurrection_index = Resurrection_index + 1;
            return;
        }
        Resurrection_index = 0;
        result = publicFunction.localFindPic(284, 295, 498, 393, "Resurrection.png");
        LtLog.i(publicFunction.getLineInfo() + "------Resurrection->" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------Resurrection->" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
            Thread.sleep(2000);
        }
    }

    //判断角色当前位置与设置挂机图是否匹配
    public boolean rolePosition() throws Exception {
        AtFairy2.OpencvResult result;
        int currentMap = 0;
        gamePublicFunction.closeWindow();
        int j = 0;
        for (int i = 1; i <= 26; i++) {
            if (i > 12 && i < 23) {
                j = 13;
                //紫龙窟，用同一张图片，13-22都是紫龙窟
            } else {
                j = i;
            }

            result = publicFunction.localFindPic(1085, 0, 1270, 79, "current_map" + Integer.toString(j) + ".png");
            LtLog.i(publicFunction.getLineInfo() + "----- i= " + i + "---------result->" + result);
            if (result.sim >= 0.6) {
                currentMap = i;
                break;
            }
            Thread.sleep(200);
        }
        if (TaskMain.map >= 13 && TaskMain.map <= 22 && currentMap >= 13 && currentMap <= 22) {
            //如果选择的是紫龙窟 ，currentMap 在13-22之间都是对的，紫龙窟用的是同一张图作为当前的判断
            LtLog.i(publicFunction.getLineInfo() + "紫龙窟 mFairy.map= " + TaskMain.map + "----->currentMap" + currentMap);
            return true;
        }

        if (currentMap == TaskMain.map) {
            //如果角色所在地图与设置地图相同 返回 true
            return true;
        } else {
            return false;
        }

    }

}
