package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
//import com.script.opencvapi.Thread.Thread;

/**
 * Created by Administrator on 2018/5/4.
 */

public class Other {
    private AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    private GamePublicFunction gamePublicFunction;
    private AtFairy2.OpencvResult result;
    private FindResult findResult;
    private long time = System.currentTimeMillis() / 1000, timex = 0;
    private boolean location = true;

    public Other(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
        gamePublicFunction = new GamePublicFunction(mFairy);
    }

    //采集
    int errc=0;
    public int collectionTask() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "开始执行采集任务");
        while (mFairy.condit()) {

            gamePublicFunction.follow(0);//取消跟随

            for (int i = 0; i < 100; i++) {
                findResult = mFairy.findPic(487,453,650,518,new String[]{"sheng.png","sheng1.png"});
                if (findResult.sim >= 0.7f) {
                    LtLog.i(publicFunction.getLineInfo() + "采集中");
                    Thread.sleep(3000);
                }else{
                    break;
                }
                Thread.sleep(3000);
            }


           /* for (int i = 0; i < 100; i++) {
                result = publicFunction.localToValueFindPic(495, 462, 804, 545, 180, 255, 0, "collectionING.png");
                if (result.sim >= 0.65) {
                    LtLog.i(publicFunction.getLineInfo() + "---------collection>" + result);
                    Thread.sleep(3000);
                } else {
                    break;
                }
                Thread.sleep(3000);
            }*/

            result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
            if (result.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "没有在主场景");
            }

            gamePublicFunction.switchSkillOrSet("set");//是切换到设置界面还是放技能界面

            /**
             * 点击技能 */
            result = publicFunction.localFindPic(1171, 329, 1280, 455, "menu.png"+"|"+"menu-1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "点击技能");
                publicFunction.rndTap(1214, 480, 1230, 496);
                Thread.sleep(2000);
            }


            for (int i = 0; i < 10; i++) {
                result = publicFunction.localFindPic(35, 68, 138, 605, "caiji.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "点击采集");
                    publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                    Thread.sleep(1000);
                    break;
                }
            }

            errc = 0;

            for (int i = 0; i < 10; i++) {
                AtFairy2.OpencvResult result2 = publicFunction.localFindPic(583, 17, 716, 61, "collection6.png" + "|" + "collection5.png");
                LtLog.i(publicFunction.getLineInfo() + "---------collection2>" + result + ",,result2=" + result2);
                if (result.sim >= 0.8 || result2.sim > 0.8) {
                    Thread.sleep(2000);

                    LtLog.i(publicFunction.getLineInfo()+"采集界面");
                    //            int xy = publicFunction.getXY(237, 136, 312, 157, 200, 255, 0);
                    //                int xy = publicFunction.getColorNunber(165, 144, 479, 150, "223,148,34", 0.9);

                    int xy = mFairy.getColorNum(171,119,250,132, "228,147,32",0.82);
                    LtLog.i(publicFunction.getLineInfo() + "---------当前活跃>" + xy);
                    if (xy <= 5) {
                        errc++;
                        if(errc>3) {
                            gamePublicFunction.closeWindow();
                            return 99;
                        }
                        continue;
                    }

                    publicFunction.rndTap(235,219 + (114 * TaskMain.collectionSkill), 269, 230 + (114 * TaskMain.collectionSkill));//点技能
                    Thread.sleep(2000);
                    publicFunction.rndTap(739, 86, 820, 110);//点采集指南
                    LtLog.i(publicFunction.getLineInfo()+"点采集指南");
                    Thread.sleep(2000);
                    break;
                }
                Thread.sleep(1000);
            }

            for (int i = 0; i < 30; i++) {
                findResult = mFairy.findPic(704,43,872,157, "caiji1.png");
                if (findResult.sim >= 0.8f) {
                    LtLog.i(publicFunction.getLineInfo()+"采集指南界面");
                    publicFunction.rndTap(1057, 179 + (113 * TaskMain.collectionLevel), 1121, 195 + (113 * TaskMain.collectionLevel));
                    Thread.sleep(5000);
                    break;
                }
                Thread.sleep(100);
            }

            gamePublicFunction.closeWindow();

            for (int i = 0; i < 10; i++) {
                if (gamePublicFunction.judgeXYChange(10000) == false) {
                    break;
                }
                Thread.sleep(500);
            }
        }
        return 0;
    }

    public int Practice() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "-------mFairy.taskList>" + TaskMain.taskList);
        gamePublicFunction.openActivity(1);

        boolean bool = true;

        while (mFairy.condit()) {

            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 1800) {
                this.location = true;
                time = System.currentTimeMillis() / 1000;
            }

            LtLog.i(publicFunction.getLineInfo() + "---------timex>" + timex + ",this.location=" + this.location);






            result = publicFunction.localToValueFindManyPic(1119, 2, 1219, 27, 240, 255, 0, new String[]{"theNetherWorld.png", "luoYang.png", "suZhou.png"});
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------theNetherWorld luoYang suZhou>" + result);
                this.location = true;
            }

            if (this.location == true) {
                this.location = false;
                gamePublicFunction.openActivity(1);
            }


            result = publicFunction.localFindPic(582,8,721,80, "activitiesWindow.png"+"|"+"activitiesWindow1.png|activitiesWindow2.png");
            if (result.sim >= 0.9) {
                //如果在活动界面
                LtLog.i(publicFunction.getLineInfo() + "---------activitiesWindow>" + result + "__taskList.size1=" + TaskMain.taskList.size());
                if (TaskMain.taskList.size() > 0) {
                    TaskMain.taskList = gamePublicFunction.lookupTask(TaskMain.taskList);//查看任务
                }
                if (TaskMain.taskList.size() == 0) {
                    gamePublicFunction.closeWindow();
                    LtLog.i(publicFunction.getLineInfo() + "---------taskList.size()>" + "任务已完成");
                    return 99;
                }
                //currentTask=taskList.get(0);
                LtLog.i(publicFunction.getLineInfo() + "---------activitiesWindow>" + result + "__taskList.size2=" + TaskMain.taskList.size() + "当前执行任务=" + TaskMain.taskList.get(0));
/*

                gamePublicFunction.closeWindow();

                for (int i = 0; i < 40; i++) {
                    boolean tf = gamePublicFunction.judgeXYChange(10000);
                    if (tf == false) {
                        break;
                    }
                    Thread.sleep(500);
                }

                gamePublicFunction.openTeam();
                gamePublicFunction.closeWindow();
                LtLog.i(publicFunction.getLineInfo() + "---------点击跟随.png=");
                gamePublicFunction.follow(1);//点击跟随
                LtLog.i(publicFunction.getLineInfo() + "---------取消跟随.png=");
                gamePublicFunction.follow(0);//取消跟随
*/


                Thread.sleep(500);
            }


            result = publicFunction.localFindPic(609, 0, 763, 101, "practice1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------practice1>" + result);
                result = publicFunction.localFindPic(198, 502, 1084, 568, "again" + Integer.toString(TaskMain.again) + ".png");
                if (result.sim >= 0.8) {
                    publicFunction.rndTapWH(result.x, result.y, 10, 10);
                    Thread.sleep(10000);
                }
            }


            result = publicFunction.localFindPic(421, 312, 537, 367, "goToFZ.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------goToFZ.png=" + result);
                publicFunction.rndTap(744, 458, 798, 487);
                Thread.sleep(500);
            }


            result = publicFunction.localFindPic(593, 264, 733, 333, "exceed3000.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------exceed3000>" + result);
                publicFunction.rndTap(609, 454, 671, 490);
                Thread.sleep(500);
                TaskMain.taskList.remove(0);
                gamePublicFunction.closeWindow();
                return 99;
            }
            Thread.sleep(1000);
        }
        return 0;
    }
    //重新上架商品
    public int upShelf() throws Exception {
        time = System.currentTimeMillis() / 1000;
        timex = System.currentTimeMillis() / 1000 - time;
        LtLog.i(publicFunction.getLineInfo() + "---------upShelf>");
        for (int i = 0; i < 60; i++) {
            result = publicFunction.localFindPic(639, 86, 1018, 173, "transaction.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------transaction>" + result);
                publicFunction.rndTapWH(result.x, result.y, 28, 22);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPicHLS(137, 164, 780, 624, "overdue.png|overdue1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------overdue>" + result);
                publicFunction.rndTapWH(result.x, result.y, 93, 56);
                Thread.sleep(500);
                time = System.currentTimeMillis() / 1000;
            } else {
                timex = System.currentTimeMillis() / 1000 - time;
                LtLog.i(publicFunction.getLineInfo() + "---------timex>" + timex);
                if (timex >= 30) {
                    break;
                }
            }
            result = publicFunction.localFindPic(577, 0, 736, 102, "shop2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------shop2>" + result);
//                publicFunction.rndTapWH(result.x,result.y,86,21);
//                Thread.sleep(1000);

                result = publicFunction.localFindPicHLS(255, 35, 441, 156, "sell.png");

                if (result.sim < 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "---------sell>" + result);
                    publicFunction.rndTap(315, 89, 381, 108);
                    Thread.sleep(2000);
                }
            }

            result = publicFunction.localFindPicHLS(435, 251, 898, 652, "shelf.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------shelf>" + result);
                publicFunction.rndTapWH(result.x, result.y, 49, 24);
                Thread.sleep(500);
            }

            result = publicFunction.localFindPic(572, 113, 716, 161, "shelf1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------shelf1>" + result);
                publicFunction.rndTap(767, 326, 805, 347);//点最大
                Thread.sleep(1000);
                switch (TaskMain.priceSet) {
                    case 1:
                        LtLog.i(publicFunction.getLineInfo() + "---------最低价>");
                        publicFunction.continuityClick(581, 392, 611, 418, 10, 50);
                        break;
                    case 2:
                        LtLog.i(publicFunction.getLineInfo() + "---------原价>");
                        break;
                    case 3:
                        LtLog.i(publicFunction.getLineInfo() + "---------最高价>");
                        publicFunction.continuityClick(790, 393, 817, 414, 10, 50);
                        break;

                }
                Thread.sleep(1000);
                publicFunction.rndTap(630, 535, 677, 566);//点确认
                Thread.sleep(1000);

            }
            result = publicFunction.localFindPic(761, 78, 896, 216, "fork2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------fork2>" + result);
                publicFunction.rndTapWH(result.x, result.y, 35, 38);///关闭上架窗口
                Thread.sleep(500);
            }
        }
        gamePublicFunction.closeWindow();
        return 0;
    }

    public void redEnvelopes() throws Exception {
        //抢红包
        result = publicFunction.localFindPicHLS(492, 475, 577, 636, "redPackage.png|redPackage-1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------redPackage>" + result);
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPicHLS(166, 160, 1159, 215, "redPackage1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------redPackage1>" + result);
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPicHLS(456, 354, 603, 464, "redPackage2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------redPackage2>" + result);
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPicHLS(543, 510, 743, 558, "redPackage3.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------redPackage3>" + result);
            publicFunction.rndTap(712, 636, 795, 660);
            Thread.sleep(1000);
            publicFunction.rndTap(1214, 84, 1228, 102);
            Thread.sleep(500);
        }

//        gamePublicFunction.closeWindow();
    }

    //保存物品到仓库
    public void preservationArticles() throws Exception {
        gamePublicFunction.closeWindow();
        openRepository();
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 3; j++) {

                LtLog.i(publicFunction.getLineInfo()+"保存物品到仓库>>>>>");

                result = publicFunction.localFindPic(310, 71, 474, 131, "package2.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "点击仓库");
                    publicFunction.rndTapWH(result.x, result.y, 10, 5);
                    Thread.sleep(1500);
                }

                result = publicFunction.localFindPic(239 + (i * 153), 128 + (j * 48), 393 + (i * 153), 178 + (j * 48), "package2.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "点击仓库...");
                    publicFunction.rndTapWH(result.x, result.y, 10, 5);
                    Thread.sleep(800);
                    publicFunction.rndTap(785, 635, 843, 654);
                    Thread.sleep(500);
                }
                Thread.sleep(1000);
            }
        }
        for (int i = 0; i < 3; i++) {
            gamePublicFunction.closeWindow();
            Thread.sleep(1000);
        }
    }

    //打开仓库
    private void openRepository() throws Exception {
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(1087, 345, 1185, 445, "package.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------package>" + result);
                publicFunction.rndTapWH(result.x, result.y, 10, 5);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(832, 610, 1004, 689, "package1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------package1>" + result);
                publicFunction.rndTapWH(result.x, result.y, 10, 5);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(283, 64, 488, 149, "package2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------package2>" + result);
                break;
            }
            Thread.sleep(1000);
        }
    }

    //开宝图
    public void openMap() throws Exception {

        while (mFairy.condit()) {
            result = publicFunction.localFindPic(860, 491, 928, 537, "use.png");
            if (result.sim >= 0.8) {
                Thread.sleep(5000);
                LtLog.i(publicFunction.getLineInfo() + "------use->" + result);
                publicFunction.rndTapWH(result.x, result.y, 43, 22);
                Thread.sleep(1000);
            }

            Thread.sleep(1000);
        }


    }


}
