package com.script.fairy;


import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairy2;
import com.script.framework.AtFairyImpl;
//import com.script.opencvapi.Thread.Thread;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/3/9.
 */

public class LimitlessTask {
    //无限任务
    private boolean location = true;
    private AtFairyImpl mFairy;
    AtFairy2.OpencvResult result;
    private PublicFunction publicFunction;
    private GamePublicFunction gamePublicFunction;
    private TimingActivity timingActivity;
    private Other other;
    private long time = System.currentTimeMillis() / 1000, timex = 0;
    private long time1 = System.currentTimeMillis() / 1000, timex1 = 0;

    public LimitlessTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
        gamePublicFunction = new GamePublicFunction(mFairy);
        timingActivity = new TimingActivity(mFairy);
        other = new Other(mFairy);
    }

    public int outdoorsOnHook() throws Exception {
        //野外挂机
        LtLog.i(publicFunction.getLineInfo() + "---------.mFairy.tongMeal=" + TaskMain.tongMeal);
        if (TaskMain.judgeSelected("captain")) {
            gamePublicFunction.createTeam();
        } else {
            //如果队员 退出队伍
            result = publicFunction.localFindPic(61, 2, 104, 61, "captain.png|captain-1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------captain=" + result);
                gamePublicFunction.exitTeam();
            }
        }
        this.location = true;
        boolean homeStart = false;
        try {
            homeStart = timingActivity.getUserHomeSet();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        boolean readBook = false;

        try {
            readBook = timingActivity.readBookSet();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int read_book=0;//每定位3次，检测一次子女授课
        LtLog.i(publicFunction.getLineInfo() + "------------homeStart=" + homeStart);
        while (mFairy.condit()) {

            result = publicFunction.localFindPic(523, 383, 807, 482, "out1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------out1.png=" + result);
            }

            result = publicFunction.localToValueFindManyPic(1119, 2, 1219, 27, 240, 255, 0, new String[]{"theNetherWorld.png", "luoYang.png", "suZhou.png"});
            if (result.sim >= 0.8) {
                this.location = true;
            }

            result = publicFunction.localFindPicHLS(859, 564, 914, 615, "automaticCombat.png"+"|"+"automaticCombat-1.png");
            if (result.sim >= 0.8) {
                this.location = true;
            }

            timingActivity.timingActivityStart();//定时任务

            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 300) {
                this.location = true;
                time = System.currentTimeMillis() / 1000;
            }

            if (gamePublicFunction.outDungeons() == 1) {
                LtLog.i(publicFunction.getLineInfo() +"在副本中");
                gamePublicFunction.follow(0);
                gamePublicFunction.automaticCombat(1);//开启自动战斗
            }


            int currTime = publicFunction.getMinuteNumber();
            String mythicalOROnHook = "onHook";
            //护宝神兽 再currTime时间内,并且用户勾选任务
            if (currTime > 541 && currTime < 1425 && TaskMain.taskList.indexOf("mythical.png") != -1) {

                mythicalOROnHook = "mythical";

                LtLog.i(publicFunction.getLineInfo() + "---------" + ",,,currTime=" + currTime + ",mFairy.taskList.indexOf(\"mythical.png\")=" + TaskMain.taskList.indexOf("mythical.png") + ",mythicalOROnHook=" + mythicalOROnHook);
            } else {
                LtLog.i(publicFunction.getLineInfo() + "---------location=" + location + ",,,currTime=" + currTime + ",mFairy.taskList.indexOf(\"mythical.png\")=" + TaskMain.taskList.indexOf("mythical.png") + ",mythicalOROnHook=" + mythicalOROnHook);
            }

            //mythical.png 神兽护宝 没有神兽护宝
            Thread.sleep(2000);

            if (this.location && mythicalOROnHook.equals("onHook")) {
                //家园
                if (homeStart) {
                    timingActivity.home();
                }
                //子女
                if (readBook){
                    read_book=read_book+1;
                    if(read_book==3){
                        timingActivity.readBook();
                        read_book=0;
                    }
                }

                gamePublicFunction.switchSkillOrSet("skill");

                time1 = System.currentTimeMillis() / 1000;

                LtLog.i(publicFunction.getLineInfo() + "---------移动到挂机目标位置" + ",,,currTime=" + currTime);

                this.location = false;

                delivery(TaskMain.map);

                for (int i = 0; i < 30; i++) {
                    result = publicFunction.localToValueFindPic(570, 494, 645, 539, 190, 255, 0, "delivery.png");
                    LtLog.i(publicFunction.getLineInfo() + "---------delivery.png=" + result);
                    if (result.sim >= 0.7) {
                        LtLog.i(publicFunction.getLineInfo() + "---------delivery.png=" + result);
                        Thread.sleep(1000);
                    } else {
                        result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
                        if (result.sim >= 0.8 && i >= 5) {
                            //没有在传送读条 并且 有活动按钮 证明传送完毕 ，退出循环
                            LtLog.i(publicFunction.getLineInfo() + "---------activity.png=" + result);
                            break;
                        }
                    }
                    Thread.sleep(500);
                }

                gamePublicFunction.openMap("current");//打开当前地图

                if (TaskMain.optionJson.optString("xystr").equals("") || TaskMain.optionJson.optString("xystr").equals("-1,-1")) {
                    LtLog.i(publicFunction.getLineInfo() + "---------not xystr=");
                } else {
                    screenXY(Integer.parseInt(TaskMain.optionJson.optString("xystr").split(",")[0]), Integer.parseInt(TaskMain.optionJson.optString("xystr").split(",")[1]), TaskMain.map);//点击对应坐标
                }
                gamePublicFunction.closeWindow();
                for (int i = 0; i < 40; i++) {
                    boolean tf = gamePublicFunction.judgeXYChange(10000);
                    if (tf == false) {
                        break;
                    }
                    Thread.sleep(500);
                }
                Thread.sleep(500);


                gamePublicFunction.automaticCombat(1);//开启自动战斗

                if (TaskMain.optionJson.optString("captain").equals("1")) {
                    //队长
                    gamePublicFunction.openTeam();
                    gamePublicFunction.tirenAndAgree();
                    gamePublicFunction.closeWindow();
                    LtLog.i(publicFunction.getLineInfo() + "---------点击跟随.png=");
                    gamePublicFunction.follow(1);//点击跟随
                    LtLog.i(publicFunction.getLineInfo() + "---------取消跟随.png=");
                    gamePublicFunction.follow(0);//取消跟随
                    gamePublicFunction.createTeam();
                    gamePublicFunction.setTarget("experience");//设置组队目标为 江湖历练
                    gamePublicFunction.closeWindow();
                    gamePublicFunction.outMercenary();
                } else if (TaskMain.optionJson.optString("team").equals("1")) {
                    //队员

                    result = publicFunction.localFindPic(61, 2, 104, 61, "captain.png"+"|"+"captain-1.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------------captain=" + result);
                        gamePublicFunction.exitTeam();
                    }

                    joinRanks();
                }



            } else if (this.location) {
                time1 = System.currentTimeMillis() / 1000;
                this.location = false;
                int mythicalMap = 0;
                String mythicalXY = "";

                switch (TaskMain.mythicalSet) {
                    case 1:
                        mythicalMap = 1;
                        mythicalXY = "93,162";//聚贤庄
                        break;
                    case 2:
                        mythicalMap = 2;
                        mythicalXY = "147,191";//天龙寺
                        break;
                    case 3:
                        mythicalMap = 3;
                        mythicalXY = "20,126";//燕子坞
                        break;
                    case 4:
                        mythicalMap = 4;
                        mythicalXY = "144,165";//夜西湖
                        break;
                    case 5:
                        mythicalMap = 5;
                        mythicalXY = "146,116";//擂鼓山
                        break;
                    case 6:
                        mythicalMap = 6;
                        mythicalXY = "77,99";//擂鼓山
                        break;
                }

                List list = new ArrayList();
                list.add("mythical.png");
                gamePublicFunction.openActivity(2);
                list = gamePublicFunction.lookupTask1(list);
                LtLog.i(publicFunction.getLineInfo() + "---------list.size=" + list.size());
                if (list.size() == 0) {
                    LtLog.i(publicFunction.getLineInfo() + "---------mythical任务完成=" + list.size());
                    TaskMain.taskList.remove("mythical.png");
                    gamePublicFunction.closeWindow();
                    this.location = true;
                    continue;
                }
                gamePublicFunction.closeWindow();
                Thread.sleep(2000);




                LtLog.i(publicFunction.getLineInfo() + "---------移动到神兽位置=" + ",mythicalMap=" + mythicalMap);
                delivery(mythicalMap);
                for (int i = 0; i < 30; i++) {
                    result = publicFunction.localToValueFindPic(570, 494, 645, 539, 190, 255, 0, "delivery.png");
                    LtLog.i(publicFunction.getLineInfo() + "---------delivery.png=" + result);
                    if (result.sim >= 0.7) {
                        LtLog.i(publicFunction.getLineInfo() + "---------delivery.png=" + result);
                        Thread.sleep(1000);
                    } else {
                        result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
                        if (result.sim >= 0.8 && i >= 5) {
                            //没有在传送读条 并且 有活动按钮 证明传送完毕 ，退出循环
                            LtLog.i(publicFunction.getLineInfo() + "---------activity.png=" + result);
                            break;
                        }
                    }
                    Thread.sleep(500);
                }

                gamePublicFunction.openMap("current");//打开当前地图
                screenXY(Integer.parseInt(mythicalXY.split(",")[0]), Integer.parseInt(mythicalXY.split(",")[1]), mythicalMap);//点击对应坐标
                gamePublicFunction.closeWindow();
                for (int i = 0; i < 40; i++) {
                    boolean tf = gamePublicFunction.judgeXYChange(3000);
                    if (tf == false) {
                        break;
                    }
                    Thread.sleep(500);
                }
                Thread.sleep(500);


                gamePublicFunction.automaticCombat(1);//开启自动战斗
                if (TaskMain.optionJson.optString("captain").equals("1")) {
                    gamePublicFunction.openTeam();
                    gamePublicFunction.tirenAndAgree();
                    gamePublicFunction.closeWindow();
                    LtLog.i(publicFunction.getLineInfo() + "---------点击跟随.png=");
                    gamePublicFunction.follow(1);//点击跟随
                    LtLog.i(publicFunction.getLineInfo() + "---------取消跟随.png=");
                    gamePublicFunction.follow(0);//取消跟随
                    gamePublicFunction.createTeam();
                    gamePublicFunction.closeWindow();
                } else if (TaskMain.optionJson.optString("team").equals("1")) {
                    result = publicFunction.localFindPic(61, 2, 104, 61, "captain.png|captain-1.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------------captain=" + result);
                        gamePublicFunction.exitTeam();
                    }
                    joinRanks();
                }
            }
            result = publicFunction.localFindPic(503, 299, 634, 374, "experience2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------experience2.png=" + result);
                publicFunction.rndTap(490, 462, 522, 482);
                Thread.sleep(1000);
                gamePublicFunction.closeWindow();
            }
            other.redEnvelopes();

        }
    return 0;
    }

    public void joinRanks() throws Exception {
        //加入附近队伍
        AtFairy2.OpencvResult result, result1;
        result = publicFunction.localFindPic(61, 2, 104, 61, "captain.png"+"|"+"captain-1.png");
        result1 = publicFunction.localFindPic(61, 2, 104, 61, "teamMember.png|teamMember-1.png");
        if (result.sim >= 0.8 || result1.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------------captain=" + result + ",teamMember=" + result1);
            return;
        }
        result = publicFunction.localFindManyPic(24, 193, 81, 231, new String[]{"task1.png", "task2.png"});
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------------task1=" + result);
            publicFunction.rndTap(143, 202, 182, 224);//点击组队
            Thread.sleep(2000);
        }
        result = publicFunction.localFindPic(816, 624, 1003, 687, "createTeam.png");
        LtLog.i(publicFunction.getLineInfo() + "------------createTeam=" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------------createTeam=" + result);
            publicFunction.rndTap(249, 193, 326, 201);//点击附近队伍
            Thread.sleep(1000);
        }

        result = publicFunction.localFindPic(998, 165, 1170, 242, "apply.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------------apply=" + result);
            publicFunction.rndTapWH(result.x, result.y, 30, 20);
            Thread.sleep(500);
        }
        gamePublicFunction.closeWindow();
    }

    public void delivery(int onHookMap) throws Exception {
        //传送到目标地图
        int map = 0;
        map = onHookMap;
        int k = 0;
        //                1聚贤庄    2天龙寺      3燕子坞 4夜西湖    5擂鼓山    6缥缈峰     7-11燕王古墓  12-16秦皇地宫
        int[][] mapXY = {{610, 438}, {481, 549}, {703, 550}, {855, 588}, {374, 244}, {212, 206}, {485, 335}, {373, 433}};
        //x=1;
        //LtLog.i(publicFunction.getLineInfo() + "---------" + mapXY[0][0] + "," + mapXY[0][1]);
        gamePublicFunction.openMap("world");
        Thread.sleep(1000);
        result = publicFunction.localFindPic(62, 185, 110, 249, "map.png"+"|"+"map1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------map.png=" + result + ",map=" + map);
        } else {
            return;
        }
        if (map >= 1 && map <= 6) {
            LtLog.i(publicFunction.getLineInfo() + "---------.=" + ",mapxy=" + mapXY[map - 1][0] + "," + mapXY[map - 1][1]);
            mFairy.tap(mapXY[map - 1][0], mapXY[map - 1][1]);

        } else if (map >= 7 && map <= 11) {
            mFairy.tap(mapXY[6][0], mapXY[6][1]);
            k = map - 7;
            Thread.sleep(1000);
            mFairy.tap(715, 273 + (k * 57));
            Thread.sleep(1000);
        } else if (map >= 12 && map <= 16) {
            mFairy.tap(mapXY[7][0], mapXY[7][1]);
            k = map - 12;
            Thread.sleep(1000);
            mFairy.tap(608, 272 + (k * 57));
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(733, 448, 808, 494, "determine.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------determine.png=" + result);
            publicFunction.rndTapWH(result.x, result.y, 30, 30);
            Thread.sleep(2000);
        }
        result = publicFunction.localFindPic(62, 185, 110, 249, "map.png"+"|"+"map1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------map.png=" + result);
            publicFunction.rndTap(1182, 111, 1208, 138); //关闭地图窗口
            Thread.sleep(200);
        }
        Thread.sleep(2000);
    }

    public void screenXY(int x_1, int y_1, int onHookMap) throws Exception {

        result = publicFunction.localFindPic(733, 572, 1190, 701, "goTo.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------goTo.png=" + result);
            return;
        }
        double x = 0, y = 0;
        int map = 0;
        map = onHookMap;
        LtLog.i(publicFunction.getLineInfo() + "------->map=" + map);
        if (map == 1) {
            //安全地图
            x = x_1 * 3.0185 + y_1 * -0.0048 + 160.1295;
            y = x_1 * -0.0093 + y_1 * -3.0132 + 665.4196;
        } else if (map == 2) {
            x = x_1 * 2.2477 + y_1 * -0.0034 + 172.7336;
            y = x_1 * 0.0077 + y_1 * -2.2434 + 650.1336;
        } else if (map >= 3 && map <= 6) {
            x = x_1 * 3.0185 + y_1 * -0.0048 + 160.1295;
            y = x_1 * -0.0093 + y_1 * -3.0132 + 665.4196;
        } else if (map >= 7 && map <= 8) {
            //燕王古墓 1-2
            x = x_1 * 6.0159 + y_1 * 0.0109 + 162.3667;
            y = x_1 * -0.0569 + y_1 * -6.0105 + 664.6902;
        } else if (map == 9) {
            x = x_1 * 5.9836 + y_1 * -0.0328 + 164.1639;
            y = x_1 * -0.0759 + y_1 * -5.9413 + 661.233;
        } else if (map == 10) {
            x = x_1 * 4.9376 + y_1 * 0.0027 + 155.0071;
            y = x_1 * 0.03 + y_1 * -4.9713 + 661.5366;
        } else if (map == 11) {
            x = x_1 * 4.5116 + y_1 * -0.0126 + 161.3564;
            y = x_1 * -0.0332 + y_1 * -4.4863 + 663.3737;
        } else if (map >= 12 && map <= 16) {
            //秦皇地宫
            x = x_1 * 3.0 + y_1 * 0.0 + 162.0;
            y = x_1 * 0.0 + y_1 * -3.0 + 663.0;
        }
        LtLog.i(publicFunction.getLineInfo() + "------->x=" + x + ",y=" + y);
        mFairy.tap((int) x, (int) y);
        Thread.sleep(500);
        //        switch (map) {
//            case 1:
//                //聚贤庄
//                x=(x_1*2.9920)+(y_1*(-0.0013))+161.3605;
//                y=(x_1*0.0008)+(y_1*(-3.0099))+663.3339;
//                break;
//
//            case 2:
//                //天龙寺
//                x = x_1 * 2.2508 + (y_1 * (-0.0084)) + 172.3414;
//                y = (x_1 * (-0.0197)) + (y_1 * (-2.2560)) + 655.2876;
//                LtLog.i();
//                break;
//            case 3:
//                //燕子坞
//                x=x_1 *3.0185+y_1 *-0.0048+160.1295;
//                y=x_1 *-0.0093+y_1 *-3.0132+665.4196;
//                break;
//            case 4:
//                //夜西湖
//
//        }
    }

}
