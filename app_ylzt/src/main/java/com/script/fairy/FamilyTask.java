package com.script.fairy;

import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairy2;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-11-20.
 */

public class FamilyTask {

    private PublicFunction publicFunction;
    private AtFairyImpl mFairy;
    private GamePublicFunction gamePublicFunction;


    public FamilyTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(ypFairy);
        gamePublicFunction = new GamePublicFunction(ypFairy);
    }


    public void mFamilyTask() throws Exception {
        AtFairy2.OpencvResult result;
        MatTime matTime = new MatTime(mFairy);
        long sleepTime = 0;
        gamePublicFunction.openActivity(gamePublicFunction.openActivity_family);
        List<String> familyList = TaskMain.familyList;
        long currentTime = 0;
        gamePublicFunction.goMianCity();
        while (mFairy.condit()) {
            sleepTime = matTime.mMatTime(1119, 54, 71, 15, 0.8);
            LtLog.i(publicFunction.getLineInfo() + "------sleepTime->" + sleepTime + ",currentTime=" + currentTime);
            if (sleepTime >= 30) {
                AtFairy2.OpencvResult result1, result2;
                result = publicFunction.localFindPic(1024, 3, 1274, 71, "mianCity.png");
                result1 = publicFunction.localFindPic(1058, 0, 1203, 80, "family.png");
                result2 = publicFunction.localFindPic(890, 0, 1020, 123, "activity.png");
                LtLog.i(publicFunction.getLineInfo() + "------activity->" + result2 + ",,mianCity=" + result + ",family= " + result1);
                if (result.sim < 0.8 && result1.sim < 0.8 && result2.sim >= 0.8) {
                    //如果不在王城，也不在家族庄园  并且有日常按钮 ，则调用回到王城
                    gamePublicFunction.goMianCity();
                }
                gamePublicFunction.openActivity(gamePublicFunction.openActivity_family);
                matTime.resetTime();
            }
            result = publicFunction.localFindPic(121, 0, 249, 115, "dailt.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------dailt->" + result);
                if (familyList.size() > 0) {
                    familyList = lookupTask(familyList, gamePublicFunction.openActivity_family);//查看任务
                    Thread.sleep(3000);
                }
            }
            if (familyList.size() == 0) {
                LtLog.i(publicFunction.getLineInfo() + "------任务完成->");
                break;
            }
            currentTime = publicFunction.getMinuteNumber();
            if (currentTime >= 1180 && currentTime <= 1210) {
                LtLog.i(publicFunction.getLineInfo() + "------即将到达国战时间->");
                break;
            }
            switch (familyList.get(0)) {
                case "family_hero":
                    familyHero();
                    break;
                case "family_snake":
                    familySnake();
                    break;
                case "family_plant":
                    familyPlant();
                    break;
                case "family_horse":
                    if (sleepTime >= 20) {
                        gamePublicFunction.automaticCombat(1);
                    }
                    familyHorse();
                    break;
            }
            result = publicFunction.localFindPic(513, 287, 803, 414, "intoFamily.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------intoFamily->" + result);
                gamePublicFunction.clickDetermine(669, 435, 878, 512);
                Thread.sleep(1000);
            }

            result = publicFunction.localFindPic(390, 241, 878, 402, "family_snake1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------family_snake1->" + result);
                publicFunction.rndTap(606, 461, 676, 483);
                Thread.sleep(1000);
            }
            Thread.sleep(1000);
        }

        for (int i = 0; i < 12; i++) {
            gamePublicFunction.closeWindow();
            result = publicFunction.localFindPic(1181, 133, 1280, 246, "leave.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------leave->" + result);
                publicFunction.rndTapWH(result.x, result.y, 27, 13);
                Thread.sleep(10000);
                break;
            }

            Thread.sleep(1000);
        }


    }

    //群英会
    public void familyHero() throws Exception {
        AtFairy2.OpencvResult result,result1;
        result = publicFunction.localFindPicHLS(431, 277, 877, 426, "drink.png");
        LtLog.i(publicFunction.getLineInfo() + "------drink->" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------drink->" + result);
//            publicFunction.rndTapWH(result.x, result.y, 44, 22);
            gamePublicFunction.clickDetermine(696, 432, 873, 513);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(627, 406, 748, 528, "drink1.png");
        result1 = publicFunction.localFindPic(1058, 0, 1203, 80, "family.png");
        LtLog.i(publicFunction.getLineInfo() + "------drink1->" + result + ", result1 " + result1);
        if (result.sim >= 0.8 && result1.sim>=0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------drink1->" + result);
            publicFunction.rndTapWH(result.x - 46, result.y + 68, 10, 10);
            Thread.sleep(500);
        }
//        result = publicFunction.localFindPic(362, 552, 500, 673, "drink2.png");
//        if (result.sim >= 0.8) {
//            LtLog.i(publicFunction.getLineInfo() + "------drink2->" + result);
//            for (int i = 0; i < 600; i++) {
//                result = publicFunction.localFindPic(362, 552, 500, 673, "drink2.png");
//                if (result.sim >= 0.8) {
//                    LtLog.i(publicFunction.getLineInfo() + "------drink2->" + result);
//                } else {
//                    break;
//                }
//
//                Thread.sleep(1000);
//            }
//        }


    }

    //蛇腾
    public void familySnake() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(1058, 0, 1203, 80, "family.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------family->" + result);
            gamePublicFunction.automaticCombat(1);
        }
    }

    //种植
    public void familyPlant() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(1005, 647, 1179, 715, "accept.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------accept->" + result);
            publicFunction.rndTapWH(result.x, result.y, 30, 22);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(1005, 647, 1179, 715, "complete.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------complete->" + result);
            publicFunction.rndTapWH(result.x, result.y, 30, 22);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(43, 244, 176, 446, "family_plant1.png|family_plant2.png|family_plant3.png|family_plant4.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------family_plant1->" + result);
            publicFunction.rndTapWH(result.x, result.y, 86, 19);
            Thread.sleep(500);
        }

    }

    //养马
    public void familyHorse() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(276, 277, 489, 405, "Resurrection5.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------Resurrection5->" + result);
            publicFunction.rndTapWH(result.x, result.y, 113, 28);
            Thread.sleep(500);
        }

    }

    public List<String> lookupTask(List<String> list, int TaskType) throws Exception {
        List<String> list1 = list;
        if (list1.size() <= 0) {
            return list1;
        }
        int mType = TaskType;
        AtFairy2.OpencvResult result;
        AtFairy2.OpencvResult result1;
        int second = 0;
        long time = System.currentTimeMillis() / 1000, timex = 0;
        LtLog.i(publicFunction.getLineInfo() + "-------list1>" + list1);
        result = publicFunction.localFindPic(121, 0, 249, 115, "dailt.png");
        if (result.sim < 0.8) {
            return list1;
        }
        gamePublicFunction.rewardReceive();//领取奖励
        Thread.sleep(1000);
        while (mFairy.condit()) {
            result = publicFunction.localFindPic(365, 138, 498, 582, list1.get(0) + ".png");
            LtLog.i(publicFunction.getLineInfo() + "-------lookupTask--list1.get(0)>" + list1.get(0) + "=" + result);
            if (result.sim >= 0.8) {
                result1 = publicFunction.localFindPic(result.x + 609, result.y, result.x + 763, result.y + 71, "attend.png|attend1.png|attend2.png");
                //接任务
                if (result1.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------attend>" + result1);
                    publicFunction.rndTap(result1.x, result1.y, result1.x + 50, result1.y + 10);
                    Thread.sleep(1000);
                    return list1;
                } else {
                    result1 = publicFunction.localFindPic(result.x + 609, result.y, result.x + 763, result.y + 71, "complete1.png");
                    //任务完成
                    if (result1.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "-------complete1>" + result1 + ",list1=" + list1 + list1.size() + list1.get(0));
                        TaskMain.familyList.remove(list1.get(0));//如果此任务完成，删除mFairy.familyList 中的任务名字符串
//                        list1.remove(0);
                        if (list1.size() == 0) {
                            gamePublicFunction.closeWindow(1075, 27, 1138, 89);
                        }
                        return list1;
                    }
                    result1 = publicFunction.localFindPic(result.x + 609, result.y, result.x + 763, result.y + 71, "notStart.png");
                    if (result1.sim >= 0.8) {
                        //这个情况是任务没有开启，不一定之后的时间不会开启，所以不删除mFairy.familyList 中的任务名字符串
                        LtLog.i(publicFunction.getLineInfo() + "-------notStart>" + result1);
                        list1.remove(0);
                        return list1;
                    }
                    result = publicFunction.localFindPic(result.x + 609, result.y, result.x + 763, result.y + 71, "reward.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "-------reward>" + result1);
                        publicFunction.rndTapWH(result.x, result.y, 20, 10);
                        Thread.sleep(500);
                    }
                }
            }
            result = publicFunction.localFindPic(121, 0, 249, 115, "dailt.png");
            if (result.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------不在日常窗口，退出=");
                return list1;
            }
            if (second >= 5) {
                LtLog.i(publicFunction.getLineInfo() + "---------从上往下滑动=" + result);
                publicFunction.RanSwipe(389, 213, 965, 400, 0, 500);
            } else {
                LtLog.i(publicFunction.getLineInfo() + "---------从下往上滑动=" + result);
                publicFunction.RanSwipe(389, 213, 965, 400, 2, 500);
            }
            Thread.sleep(1000);
            second = second + 1;
            if (second >= 10) {
                second = 0;
            }
            timex = System.currentTimeMillis() / 1000 - time;
            LtLog.i(publicFunction.getLineInfo() + "---------timex=" + timex);
            if (timex >= 300) {
                LtLog.i(publicFunction.getLineInfo() + "---------长时间未找到任务");
                list1.remove(0);
                break;
            } else if (timex >= 120) {
                LtLog.i(publicFunction.getLineInfo() + "---------长时间未找到任务删除list1（0）" + list1.get(0));
                list1.remove(0);
                return list1;
            }
            for (int i = 1; i <= 6; i++) {
                result = publicFunction.localFindPic(168, 96 + ((i - 1) * 84), 264, 163 + ((i - 1) * 84), "taskType.png");
                //判断当前窗口类型是否是传入的类型，如果不是就重新定位
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------taskType->" + result);
                    if (i != mType) {
                        gamePublicFunction.openActivity(mType);
                        Thread.sleep(2000);
                        break;
                    }
                }
            }
            Thread.sleep(100);
        }
        return list1;
    }


    public void familyExperiment() throws Exception {
        AtFairy2.OpencvResult result;
        long time = System.currentTimeMillis() / 1000, timex = 0;

        boolean Location=false; //定位开关

        while (mFairy.condit()) {
            result = publicFunction.localFindPic(534, 558, 748, 686, "into2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------into2->" + result);
                publicFunction.rndTapWH(result.x, result.y, 114, 28);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(1184, 13, 1280, 135, "videoCamera.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------videoCamera->" + result);
                result = publicFunction.localFindPic(1181, 133, 1280, 246, "leave.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "---------------- copy ing----------leave->" + result);
                    if (!Location){
                        gamePublicFunction.openMap("current");//打开当前地图
                        publicFunction.rndTap(448,306,472,325);
                        gamePublicFunction.closeWindow();
                        Thread.sleep(6000);
                        Location=true; //只做一次定位
                    }
                    gamePublicFunction.automaticCombat(1);
                } else {
                    result = publicFunction.localFindPic(1048, 236, 1227, 357, "family_experiment.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------family_experiment->" + result);
                        publicFunction.rndTapWH(result.x, result.y, 79, 21);
                        Thread.sleep(500);
                    }
                    time = time - 60;
                    //如果没有发现离开 time减少60 s ；如此 大约10多次没有发现离开按钮 就会触发下面代码中的超时，退出副本
                    LtLog.i(publicFunction.getLineInfo() + "---------------- not  copy leave----------->" + time);
                }
            } else {
                result = publicFunction.localFindPic(1197, 0, 1280, 80, "down.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------down->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 25, 16);
                    Thread.sleep(500);
                }
            }
            result = publicFunction.localFindPic(565, 529, 717, 657, "close.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------close->" + result);
                publicFunction.rndTapWH(result.x, result.y, 52, 28);
                Thread.sleep(500);
                break;
            }
            timex = System.currentTimeMillis() / 1000 - time;
            LtLog.i(publicFunction.getLineInfo() + "---------------- timex----------->" + timex);
            if (timex >= 2000) {
                LtLog.i(publicFunction.getLineInfo() + "------task out time->" + result);
                break;
            }
            Thread.sleep(1000);
        }
    }

    public void familyExperiment2() throws Exception {
        AtFairy2.OpencvResult result;
        gamePublicFunction.openActivity(gamePublicFunction.openActivity_family);

        List list=new ArrayList();
        list.add("experiment1");

        gamePublicFunction.lookupTask(list,gamePublicFunction.openActivity_family);

        MatTime matTime = new MatTime(mFairy);
        long sleepTime = 0;
        for (int i = 0; i <300; i++) {
            sleepTime = matTime.mMatTime(1119, 54, 71, 15, 0.8);
            LtLog.i(publicFunction.getLineInfo() + "------sleepTime->" + sleepTime);
            if (sleepTime >= 30) {
                gamePublicFunction.openActivity(gamePublicFunction.openActivity_family);
                list=gamePublicFunction.lookupTask(list,gamePublicFunction.openActivity_family);
                matTime.resetTime();
            }
            result = publicFunction.localFindPic(940, 156, 1122, 222, "goto3.png");
            if (result.sim >= 0.8) {
                int x,y;
                x=result.x;
                y=result.y;
                LtLog.i(publicFunction.getLineInfo() + "------goto3->" + result + ",," + sleepTime);
                result = publicFunction.localFindPic(442,146,621,230, "empty1.png");
                if(result.sim>=0.8){
                    LtLog.i(publicFunction.getLineInfo() + "------empty1->" + result );
                    for (int j = 0; j <3 ; j++) {
                        gamePublicFunction.closeWindow();
                    }
                    return;
                }
                publicFunction.rndTapWH(x, y, 59, 25);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(339, 0, 477, 87, "experiment.png");
            if (result.sim >= 0.7) {
                LtLog.i(publicFunction.getLineInfo() + "------experiment ing ->" + result);
                matTime.resetTime();
            }
            result = publicFunction.localFindPic(1048, 236, 1227, 357, "family_experiment.png");
            if (result.sim >= 0.8) {
                familyExperiment();
                break;
            }
            Thread.sleep(1000);
        }
    }


}
