package com.script.fairy;

import com.example.publicfunctionlibrary.FunctionClass;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/5/30.
 */

public class TimingActivity {
    private AtFairyImpl mFairy;
    private GamePublicFunction gamePublicFunction;
    private PublicFunction publicFunction;
    private Abnormal abnormal;
    private FindResult findResult;
    private int MJmap = 0;
    private String MJname;
    private int[][] MJXY;
    private boolean mapChange = false;
    private boolean tenState = true;
    private boolean JZKH_CG_state = true;
    private boolean challenge_WLMZ = false;//是否挑战过盟主，挑战过盟主的情况下开始抢分
    private final int dance_UP = 0;
    private final int dance_DOWN = 1;
    private final int dance_LEFT = 2;
    private final int dance_RIGHT = 3;
    private FunctionClass functionClass;
    private Map<Integer, int[]> queenMap = new HashMap<>();//女帝房间位置
    private final List<Mat> dance_png = new ArrayList<>();
    private Random rand = new Random();

    private List MJlist = new ArrayList();
    AtFairy2.OpencvResult result;

    public TimingActivity(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        gamePublicFunction = new GamePublicFunction(mFairy);
        publicFunction = new PublicFunction(mFairy);
//        publicFunction=mFairy.publicFunction;
        abnormal = new Abnormal(mFairy);
        functionClass = new FunctionClass(mFairy, mFairy.getContext());
    }

    public void timingTask(String excuteTask) throws Exception {

        GoSecurityXiakeIsland();//如果在侠客岛,需要先回到安全区,,初始设计脚本时没有考虑到侠客岛这个奇葩,所以这个地方还要调用一次。

        String task = excuteTask;

        Mat mat1 = null, mat2 = null;
        int timingTask = publicFunction.getMinuteNumber();//任务开启时间

        int MinuteNumber;
        boolean out_team = false;   //退出队伍参加的活动，活动结束后，退出队伍；
        if (excuteTask.equals("tenMJ") || excuteTask.equals("tenSH") || excuteTask.equals("BHT") ||
                excuteTask.equals("nineAttackCity") || excuteTask.equals("WLMZ") ||
                excuteTask.equals("JZKH") || excuteTask.equals("SJZC") ||
                excuteTask.equals("nineExperiment") || excuteTask.equals("nineAttackCity1") || excuteTask.equals("tenMJ1") || excuteTask.equals("queen")) {
            LtLog.i(publicFunction.getLineInfo() + "------->不退队=");
        } else {
            out_team = true;
            gamePublicFunction.exitTeam();
        }

        gamePublicFunction.goSecurity();//回安全区

        /*LtLog.i(publicFunction.getLineInfo() + "------->等待40S=" + ",要开始执行的任务：" + task);
        //如果是在侠客岛挂机 ,就不等了。
        for (int i = 0; i < 19 && TaskMain.taskMap.get("OnHookMap") != 999; i++) {
            Thread.sleep(2000);//等待40S
        }*/

        if (task.equals("WLMZ")) {
            //(45000 <= currentlyTIME && currentlyTIME < 45600) || (70200 <= currentlyTIME && currentlyTIME < 70800))
            for (int i = 0; i < 200; i++) {
                long currentlyTIME = publicFunction.getSecondNumber();
                if ((currentlyTIME >= 45000 && currentlyTIME <= 45720) || (currentlyTIME >= 70200 && currentlyTIME < 71400)) {
                    break;
                }

                Thread.sleep(3000);
            }
        }

        gamePublicFunction.openActivity();

        LtLog.i(publicFunction.getLineInfo() + "打开活动窗口");

        long time = System.currentTimeMillis() / 1000, timex = 0;
        long time1 = System.currentTimeMillis() / 1000, time1x = 0;
        boolean currentTaskState = false;

        if (task.equals("tenMJ") || task.equals("tenMJ1")) {
            setMJ(task); //设置名将
        }

        if (task.equals("SJZC")) {
            task = "SJZC.png" + "|" + "SJZC1.png" + "|" + "SJZC2";//宋金
        }

        if (task.equals("XMHJ")) {
            task = "XMHJ1.png" + "|" + "XMHJ2";//心魔幻境
        }

        if (task.equals("WLMZ")) {
            task = "WLMZ.png" + "|" + "KFMZ";
        }

        while (mFairy.condit()) {

            MinuteNumber = publicFunction.getMinuteNumber();

            LtLog.i(publicFunction.getLineInfo() + "超时计时:" + timex + " 当前时间 MinuteNumber :" + MinuteNumber + " 任务开启时间:" + timingTask + " 任务:" + task);

            result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【活动场景】");

                for (int i = 0; i < 2; i++) {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                }

                Thread.sleep(2000);

                result = publicFunction.localFindPic(894, 126, 1076, 317, task + ".png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "发现活动");
                    LtLog.i(publicFunction.getLineInfo() + "------->" + task + "=" + result);
                    publicFunction.rndTap(result.x + 19, result.y + 33, result.x + 89, result.y + 59);
                    Thread.sleep(3000);
                } else {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                    Thread.sleep(500);
                }

                time1x = System.currentTimeMillis() / 1000 - time1;
                if (time1x >= 90) {
                    gamePublicFunction.closeWindow();
                    LtLog.i(publicFunction.getLineInfo() + "---------------------outTime  not task----return---------------------->");
                    return;
                }

            } else {
                time1 = System.currentTimeMillis() / 1000;
            }

            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 90) {

                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                LtLog.i(publicFunction.getLineInfo() + "超时 close");

                gamePublicFunction.closeWindow();

                if (!task.equals("JZKH")) {
                    gamePublicFunction.openActivity();
                    time = System.currentTimeMillis() / 1000;
                }
            }

            result = publicFunction.localFindPic(335, 200, 870, 514, "goSecurity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->goSecurity=" + result);
                publicFunction.rndTap(734, 434, 805, 464);//点击同意
                Thread.sleep(1000);
            }

            LtLog.i(publicFunction.getLineInfo() + "每个限时任务特有的点击 >>>");

            switch (task) {
                case "WLMZ.png|KFMZ":
                    currentTaskState = WLMZ();
                    break;
                case "JZKH":
                    result = publicFunction.localFindPic(1116, 0, 1280, 81, "home1.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "---------------------home1------->" + result);
                        currentTaskState = JZKH();
                    } else {
                        gamePublicFunction.openActivity();
                    }
                    break;
                case "BHT":
                    currentTaskState = BHT();
                    break;
                case "SJZC.png|SJZC1.png|SJZC2":
                    //宋金战场
                    currentTaskState = SJZC();
                    break;
                case "XMHJ1.png" + "|" + "XMHJ2":
                    //心魔幻境
                    currentTaskState = XMHJ();
                    break;
                case "nineAthletics":
                    currentTaskState = nineAthletics();
                    break;
                case "nineAttackCity":
                    //攻城战
                    currentTaskState = nineAttackCity();
                    break;
                case "nineAttackCity1":
                    //跨服攻城战
                    currentTaskState = nineAttackCity();
                    break;
                case "territory":
                    //领土战
                    currentTaskState = nineAttackCity();
                    AttackCity_selectMap(TaskMain.taskMap.get("territoryMap"));
                    break;
                case "nineExperiment":
                    currentTaskState = nineExperiment();
                    break;
                case "nineHero":
                    //群英会
                    currentTaskState = nineHero();
                    break;
                case "nineTTT":
                    currentTaskState = nineTTT();
                    break;
                case "huashan":
                    currentTaskState = huaShan();
                    break;
                case "tenMJ":
                case "tenMJ1":
                    currentTaskState = tenMJ();
                    break;
                case "queen":
                    //女帝
                    currentTaskState = queen();
                    break;
                case "tenSH":
                    currentTaskState = tenSH();
                    break;
                case "familyMJ":
                    currentTaskState = familyMJ();
                    break;
                case "ruins":
                    //遗迹寻宝
                    LtLog.i(publicFunction.getLineInfo() + "------->ruins=");
                    currentTaskState = ruins();
                    break;
                case "familyProtect":
                    //家族保卫战
                    LtLog.i(publicFunction.getLineInfo() + "------->familyProtect=");
                    currentTaskState = familyProtect();
                    break;
                case "DM":
                    //达摩洞
                    currentTaskState = DM();
                    break;
                case "AZXY":
                    //鏖战襄阳
                    currentTaskState = AZXY();
                    break;
                case "NHGC":
                    //南海钩沉
                    currentTaskState = NHGC();
                    break;
            }

            LtLog.i(publicFunction.getLineInfo() + "每个限时任务特有的点击 - 结束 >>>");

            // state = true; 统一返回此状态判断是否要重新查找任务
            if (currentTaskState == true) {
                time = System.currentTimeMillis() / 1000;
            }

            if (task.equals("tenSH") || task.equals("tenMJ") || task.equals("queen")) {
                //十点活动退出条件
                if (tenState == false || MinuteNumber >= 1335 || (MinuteNumber >= 950 && MinuteNumber <= 990)) {

                    LtLog.i(publicFunction.getLineInfo() + "十点活动退出");
                    LtLog.i(publicFunction.getLineInfo() + "十点活动退出");
                    LtLog.i(publicFunction.getLineInfo() + "十点活动退出");

                    result = gamePublicFunction.leave();
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "离开");
                        publicFunction.rndTapWH(result.x, result.y, 10, 10);
                        Thread.sleep(1000);
                    }

                    gamePublicFunction.clickDetermine();

                    for (int i = 0; i < 40; i++) {
                        gamePublicFunction.closeWindow();
                    }

                    result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
                    if (result.sim >= 0.8) {
                        if (mat1 != null) {
                            mat1.release();
                        }
                        if (mat2 != null) {
                            mat2.release();
                        }
                        LtLog.i(publicFunction.getLineInfo() + "----------------------名将或者始皇退出------>=");
                        return;
                    }
                }
            } else if (task.equals("JZKH")) {
                if (MinuteNumber >= 1166) {
                    //19.25
                    LtLog.i(publicFunction.getLineInfo() + "----------------------烤火时间到------>=");
                    return;
                }
            } else if (task.equals("nineAthletics")) {
                //九点活动..门派竞技，退出条件与其他活动例外
                result = publicFunction.localFindPic(1141, 1, 1226, 43, "athletics1.png|athletics2.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "----------------------门派竞技中------>=");
                } else {
                    result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------activity--->=" + result);
                        if (mat1 != null) {
                            mat1.release();
                        }
                        if (mat2 != null) {
                            mat2.release();
                        }
                        LtLog.i(publicFunction.getLineInfo() + "----------------------九点活动退出------>=");
                        return;
                    }
                }
            } else {
                if (task.equals("SJZC.png|SJZC1.png|SJZC2")) {
                    //宋金战场退出条件,发现需要使用宋金诏书，关闭窗口，退出
                    AtFairy2.OpencvResult result1 = publicFunction.localFindPic(493, 318, 788, 700, "SJZCoff.png");
                    result = publicFunction.localFindPic(392, 247, 889, 411, "SJZS.png");
                    if (result.sim >= 0.8 || result1.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------SJZS--->=" + result + ",result1=" + result1);
                        if (result.sim >= 0.8) {
                            publicFunction.rndTap(480, 435, 539, 467);//点击取消
                            Thread.sleep(2000);
                        }
                        gamePublicFunction.closeWindow();
                        if (mat1 != null) {
                            mat1.release();
                        }
                        if (mat2 != null) {
                            mat2.release();
                        }
                        return;
                    }
                }

                //九点活动..每日定时活动 退出条件
                result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
                if (result.sim >= 0.8) {
                    if (activityButtonTime(10)) {
                        LtLog.i(publicFunction.getLineInfo() + "------activity--->=" + result);
                        if (mat1 != null) {
                            mat1.release();
                        }
                        if (mat2 != null) {
                            mat2.release();
                        }
                        LtLog.i(publicFunction.getLineInfo() + "----------------------九点活动..每日定时活动---退出--->=");
                        if (out_team) {


                            gamePublicFunction.closeWindow();

                            for (int i = 0; i < 10; i++) {
                                result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
                                if (result.sim >= 0.8) {
                                    mFairy.onTap(1105, 38, 1117, 53, "", 2000);
                                }

                                result = gamePublicFunction.leave();
                                if (result.sim >= 0.8) {
                                    LtLog.i(publicFunction.getLineInfo() + "离开");
                                    publicFunction.rndTapWH(result.x, result.y, 10, 10);
                                    Thread.sleep(1000);
                                }

                                gamePublicFunction.clickDetermine();
                            }

                            gamePublicFunction.closeWindow();

                            for (int i = 0; i < 5; i++) {
                                result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
                                if (result.sim >= 0.8) {
                                    break;
                                } else {
                                    mFairy.onTap(1107, 25, 1123, 42, "", 2000);
                                }
                            }

                            //退出队伍参加的活动，活动结束后，退出队伍；
                            gamePublicFunction.exitTeam();
                        }
                        return;
                    }
                }
            }

            result = publicFunction.localFindPic(138, 266, 224, 303, "SJover.png");
            if (MinuteNumber - timingTask >= 30 || result.sim >= 0.8) {
                //定时任务执行时间超过30分钟 退出；
                LtLog.i(publicFunction.getLineInfo() + "定时任务执行时间超过30分钟 退出");
                gamePublicFunction.closeWindow();
                Thread.sleep(1000);
                for (int i = 0; i < 60; i++) {
                    result = gamePublicFunction.leave();
                    //离开按钮有可能被弹幕 遮挡 ，正常说来活动结束会被强制传送离开，,但是有偶尔不会。故 此 for是做特殊处理
                    LtLog.i(publicFunction.getLineInfo() + "离开");
                    if (result.sim >= 0.8) {
                        publicFunction.rndTapWH(result.x, result.y, 10, 10);
                        Thread.sleep(1000);
                        break;
                    }

                    result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
                    if (result.sim > 0.8) {
                        break;
                    }

                    Thread.sleep(1000);
                }
                gamePublicFunction.clickDetermine();
                gamePublicFunction.closeWindow();
                if (mat1 != null) {
                    mat1.release();
                }
                if (mat2 != null) {
                    mat2.release();
                }
                return;
            }
            Thread.sleep(100);
        }
    }

    // 家族保卫战
    private boolean familyProtect() throws Exception {
        boolean state = false;
        boolean skillState = false;
        gamePublicFunction.manualOrAutomatic("automatic");
        result = publicFunction.localFindPic(877, 539, 1158, 665, "attend3.png");
        LtLog.i(publicFunction.getLineInfo() + "------->attend3=" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->attend3=" + result);
            publicFunction.rndTapWH(result.x, result.y, 40, 24);
            Thread.sleep(500);
        }
        result = gamePublicFunction.leave();
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->leave=" + result);
            state = true;
        }
        result = publicFunction.localFindPic(1091, 0, 1280, 82, "skip.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------------------  skip=" + result);
            publicFunction.rndTapWH(result.x, result.y, 112, 22);
            Thread.sleep(500);
        }

        return state;
    }

    //遗迹寻宝
    private boolean ruins() throws Exception {
        boolean state = false;
        LtLog.i(publicFunction.getLineInfo() + "------->ruins=");
        result = publicFunction.localFindPic(113, 551, 636, 714, "single.png");
        LtLog.i(publicFunction.getLineInfo() + "------->single=" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->single=" + result);
            publicFunction.rndTapWH(result.x, result.y, 53, 24);
            Thread.sleep(500);
        }
        result = gamePublicFunction.leave();
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->leave=" + result);
            state = true;
        }
        gamePublicFunction.manualOrAutomatic("automatic");
        result = publicFunction.localFindPic(947, 219, 1088, 322, "open.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->open=" + result);
            gamePublicFunction.manualOrAutomatic("manual");
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
            Thread.sleep(5000);
            gamePublicFunction.manualOrAutomatic("automatic");
        }
        return state;
    }

    //武林盟主
    public boolean WLMZ() throws Exception {
        boolean state = false;
        result = publicFunction.localFindPic(1038, 251, 1160, 374, "manual.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->manual=" + result);
            state = true;
        }
        result = publicFunction.localFindPic(1039, 252, 1160, 375, "automatic.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->automatic=" + result);
            state = true;
        }
        result = publicFunction.localFindPic(557, 388, 899, 660, "determine.png" + "|" + "determine1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "确定");
            publicFunction.rndTapWH(result.x, result.y, 28, 25);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(1035, 605, 1275, 716, "determine.png" + "|" + "determine1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "确定");
            publicFunction.rndTapWH(result.x, result.y, 28, 25);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(803, 522, 985, 608, "startCombat.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "开始战斗");
            publicFunction.rndTapWH(result.x, result.y, 20, 20);
            Thread.sleep(500);
            state = true;
        }

        result = publicFunction.localFindPic(560, 567, 765, 693, "takePrize.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "前去领奖");
            gamePublicFunction.closeWindow();
        }

        result = publicFunction.localFindPic(545, 0, 800, 40, "WLMZ1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "武林盟主界面");
            state = true;
        } else {
            return state;
        }


        int currentlyTIME = publicFunction.getSecondNumber();

        result = publicFunction.localFindPic(958, 613, 1198, 719, "challenge.png");
        if (result.sim > 0.8f) {

            findResult = mFairy.findPic("qf.png");
            mFairy.onTap(0.8f, findResult, 976, 95, 989, 106, "关闭抢分界面", 1000);

            LtLog.i(publicFunction.getLineInfo() + "武林盟主挑战按钮");

            if (TaskMain.taskMap.get("customize") == 1) {

                LtLog.i(publicFunction.getLineInfo() + "用户勾选了自定义时间");

                int cuuent;
                if (publicFunction.getHourNumber() == 12) {
                    cuuent = publicFunction.timeToInt("12:30:00", publicFunction.SECOND);
                } else {
                    cuuent = publicFunction.timeToInt("19:30:00", publicFunction.SECOND);
                }

                int time1 = cuuent + 720 - TaskMain.taskMap.get(TaskMain.WLMZ_time1);
                int time2 = cuuent + 720 - TaskMain.taskMap.get(TaskMain.WLMZ_time2);
                int time3 = cuuent + 720 - TaskMain.taskMap.get(TaskMain.WLMZ_time3);
                int time4 = cuuent + 720 - TaskMain.taskMap.get(TaskMain.WLMZ_time4);

                while (mFairy.condit()) {

                    result = publicFunction.localFindPic(958, 613, 1198, 719, "challenge.png");
                    if (result.sim < 0.8f) {
                        break;
                    }

                    currentlyTIME = publicFunction.getSecondNumber();

                    LtLog.i(publicFunction.getLineInfo() + "【 time1=" + time1 + ",  time2=" + time2 + ",  time3=" + time3 + ",  time4=" + time4 + ",  currentlyTIME=" + currentlyTIME + "】");

                    if (result.sim >= 0.8 && ((time1 <= currentlyTIME && currentlyTIME <= time1 + 1)
                            || (time2 <= currentlyTIME && currentlyTIME <= time2 + 1)
                            || (time3 <= currentlyTIME && currentlyTIME <= time3 + 1)
                            || (time4 <= currentlyTIME && currentlyTIME <= time4 + 1))) {

                        /*if(time1 == currentlyTIME || time2 == currentlyTIME || time3 == currentlyTIME || time4 == currentlyTIME ){*/
                        LtLog.i(mFairy.getLineInfo("时间到了,开始挑战盟主：" + currentlyTIME));

                        publicFunction.rndTapWH(result.x, result.y, 20, 20);
                        challenge_WLMZ = true;//挑战盟主  ，设置challenge_WLMZ 为真，只有当挑战过盟主的情况下，才执行抢分
                        Thread.sleep(500);
                        state = true;
                    }
                }

            } else {
                LtLog.i(publicFunction.getLineInfo() + "用户勾选了默认打盟主");

                if (result.sim >= 0.8 && ((currentlyTIME >= 45100 && currentlyTIME <= 46290) || (currentlyTIME >= 70300 && currentlyTIME < 71400))) {

                    LtLog.i(mFairy.getLineInfo("时间到了,开始挑战盟主：" + currentlyTIME));
                    publicFunction.rndTapWH(result.x, result.y, 20, 20);
                    challenge_WLMZ = true;//挑战盟主  ，设置challenge_WLMZ 为真，只有当挑战过盟主的情况下，才执行抢分
                    Thread.sleep(500);
                    state = true;
                }
            }
        }

        //currentlyTIME = publicFunction.getSecondNumber();

        if (TaskMain.taskMap.get("rob") == 1 && challenge_WLMZ == true && ((45000 <= currentlyTIME && currentlyTIME < 45600) || (70200 <= currentlyTIME && currentlyTIME < 70800))) {
            //勾选了抢分，并且challenge_WLMZ为真，就是在挑战过盟主的情况下，才执行抢分操作
            // 后面两个时间判断分别是 12：:30 <= currentlyTIME <12:40   , 19：:30 <= currentlyTIME <19:40
            result = publicFunction.localFindPic(731, 118, 965, 691, "rob.png" + "|" + "rob1.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "前往抢分");
                publicFunction.rndTapWH(result.x, result.y, 20, 50);
                Thread.sleep(500);
                state = true;
            }
        }
        return state;
    }

    private void WLMZrob() throws Exception {
        result = publicFunction.localFindPic(731, 118, 965, 691, "rob.png" + "|" + "rob1.png");
        LtLog.i(publicFunction.getLineInfo() + "------->rob=" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->rob=" + result);
            publicFunction.rndTapWH(result.x, result.y, 20, 50);
            Thread.sleep(500);
        }
    }

    //家族烤火
    private boolean JZKH() throws Exception {
        if (TaskMain.taskMap.get("answer") == 1 && publicFunction.getMinuteNumber() <= 1161) {
            //答题
            JZKHanswer();
        }
        if (TaskMain.taskMap.get("CG") == 1 && JZKH_CG_state == true) {
            //传功
            JZKHCG();
        }


        if(AtFairyConfig.getOption("kj").equals("1")) {
            // 科举
            result = publicFunction.localFindPic(246, 137, 384, 244, "exam.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "------->exam=" + result);
                publicFunction.rndTapWH(result.x, result.y, 20, 20);
                Thread.sleep(1000);
            }
        }

        findResult = mFairy.findPic(904, 346, 1114, 498, "exam1.png");
        if (findResult.sim >= 0.8f) {
            LtLog.i(mFairy.getLineInfo("答题界面"));
            int rnd = rand.nextInt(4);
            switch (rnd) {
                case 0:
                    mFairy.onTap(293, 484, 386, 514,"A",1000);
                    break;
                case 1:
                    mFairy.onTap(660, 488, 736, 508,"B",1000);
                    break;
                case 2:
                    mFairy.onTap(289, 582, 396, 607,"C",1000);
                    break;
                case 3:
                    mFairy.onTap(639, 581, 768, 606,"D",1000);
                    break;
            }

            Thread.sleep(1000);
        }


        result = publicFunction.localFindPic(393, 323, 651, 408, "exam_over.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->exam_over=" + result);
            publicFunction.rndTap(1203, 22, 1229, 51);//考试结束，关闭窗口
            Thread.sleep(1000);
        }
        return true;
    }

    private boolean familyMJ() throws Exception {
        boolean state = false;
        result = publicFunction.localFindPic(271, 65, 487, 250, "day.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->day=" + result);
            switch (TaskMain.taskMap.get("familyMJ")) {
                case 1:
                    publicFunction.rndTap(372, 215, 417, 270);
                    break;
                case 2:
                    publicFunction.rndTap(617, 250, 659, 312);
                    break;
                case 3:
                    publicFunction.rndTap(855, 262, 934, 355);
                    break;
            }
            Thread.sleep(1000);
            gamePublicFunction.clickDetermine();
        }
        return state;
    }

    //家族烤火 答题
    private void JZKHanswer() throws Exception {

        int range[] = {};
        result = publicFunction.localFindPic(827, 255, 1180, 444, "answer.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->answer=" + result);
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(297, 332, 630, 436, "A.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->A=" + result);
            switch ((int) (Math.random() * 3)) {
                case 0:
                    range = new int[]{371, 366, 474, 395};
                    break;
                case 1:
                    range = new int[]{698, 359, 767, 390};
                    break;
                case 2:
                    range = new int[]{362, 457, 462, 490};
                    break;
                case 3:
                    range = new int[]{707, 462, 854, 496};
                    break;

            }
            publicFunction.rndTap(range[0], range[1], range[2], range[3]);
            Thread.sleep(2000);
        }
        gamePublicFunction.clickDetermine();
        Thread.sleep(1000);

        result = publicFunction.localFindPic(545, 114, 661, 166, "dice.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->dice=" + result);
            for (int i = 0; i < 3; i++) {
                publicFunction.rndTap(612, 522, 663, 547);
                Thread.sleep(2000);
            }


        } else {
            gamePublicFunction.closeWindow();
        }
    }

    //家族烤火 传功
    private void JZKHCG() throws Exception {
        gamePublicFunction.switchSkillOrTong("tong");
        result = publicFunction.localFindPic(900, 618, 1018, 720, "tong.png" + "|" + "tong2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------tong--->=" + result);
            publicFunction.rndTapWH(result.x, result.y, 18, 17);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(0, 31, 108, 165, "tong1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------tong1--->=" + result);
            Thread.sleep(1000);
            publicFunction.rndTap(1198, 268, 1234, 335);//点成员
            Thread.sleep(2000);
            for (int i = 0; i < 4; i++) {
                publicFunction.RanSwipe(809, 164, 1019, 503, 0, 500);
                Thread.sleep(1000);
            }
        }

        //默认JZKH_CG_state=false
        JZKH_CG_state = false;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                result = publicFunction.localFindPic(985, 195 + (j * 48), 1039, 322 + (j * 48), "CG.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------CG--->=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 10, 10);
                    Thread.sleep(500);
                    //如果能找到传功 设置JZKH_CG_state 为真
                    JZKH_CG_state = true;
                }
            }
            publicFunction.RanSwipe(809, 164, 1019, 503, 2, 500);
            Thread.sleep(2000);
        }
        gamePublicFunction.closeWindow();
    }

    //白虎堂
    private boolean BHT() throws Exception {
        boolean state = false;
        boolean skillState = true;

        gamePublicFunction.manualOrAutomatic("automatic");

        result = gamePublicFunction.leave();
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "在副本中" + result);
            state = true;
            result = publicFunction.localFindPic(681, 405, 883, 509, "agree.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "同意" + result);
                publicFunction.rndTapWH(result.x, result.y, 49, 25);
                Thread.sleep(500);
            }
        }

        result = publicFunction.localFindPic(841, 73, 1081, 208, "into.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "进入" + result);
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
            Thread.sleep(1000);
        }

        result = publicFunction.localFindPic(702, 73, 915, 153, "entrance.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "入口");
            mFairy.tap(result.x, result.y + (TaskMain.taskMap.get("sel10") * 58));
            Thread.sleep(1000);
        }

        for (int i = 0; i < 12; i++) {
            result = publicFunction.localFindPic(298, 279, 757, 529, "into1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "进入");
                publicFunction.rndTapWH(result.x, result.y, 10, 10);
                Thread.sleep(500);
            }
            Thread.sleep(1000);
        }


        result = publicFunction.localFindPic(1141, 1, 1237, 45, "BHT1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "在白虎堂中");
            //判断在白虎堂中 ， 如果不在打怪，打开当前地图，点击地图中间
            //skillState = publicFunction.judgeMatChange(1031, 631, 20, 20, 0.95, 9000);

            long skill_change = mFairy.mMatTime(1024, 631, 48, 34, 0.95f);
            if (skill_change > 5) {
                mFairy.initMatTime();
                LtLog.i(publicFunction.getLineInfo() + "技能没有发生变化");
                gamePublicFunction.openMapWorldOrCurrent("current");
                Thread.sleep(1000);
                publicFunction.rndTap(646, 353, 673, 377);
                Thread.sleep(3000);
                gamePublicFunction.closeWindow();
                Thread.sleep(1000);
            }
            state = true;
        }

        result = publicFunction.localFindPic(563, 53, 656, 103, "leave-2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------leave-2--->=" + result);
            result = gamePublicFunction.leave();
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------leave--->=" + result);
                publicFunction.rndTapWH(result.x, result.y, 10, 10);
                Thread.sleep(2000);
                gamePublicFunction.clickDetermine();
            }
            result = publicFunction.localFindPic(686, 418, 841, 479, "leave-4.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------leave-4--->=" + result);
                publicFunction.rndTapWH(result.x, result.y, 10, 10);
                Thread.sleep(2000);
            }
        }
        return state;
    }

    //宋金战场
    private boolean SJZC() throws Exception {
        boolean state = false;
        gamePublicFunction.switchSkillOrTong("skill");
        gamePublicFunction.manualOrAutomatic("automatic");

        result = gamePublicFunction.leave();
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------leave--->=" + result);
            state = true;
        }
        result = publicFunction.localFindPic(532, 585, 756, 686, "attend.png");
        LtLog.i(publicFunction.getLineInfo() + "------attend--->=" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------attend--->=" + result);
            publicFunction.rndTapWH(result.x, result.y, 28, 26);
            Thread.sleep(5000);
        }
        result = publicFunction.localFindPic(554, 645, 715, 715, "return.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------return--->=" + result);
            publicFunction.rndTapWH(result.x, result.y, 28, 26);
            Thread.sleep(500);
        }

        return state;
    }

    public void lmzz() throws Exception {

        gamePublicFunction.closeWindow();

        GoSecurityXiakeIsland();//如果在侠客岛,需要先回到安全区,,初始设计脚本时没有考虑到侠客岛这个奇葩,所以这个地方还要调用一次。

        gamePublicFunction.exitTeam();

        gamePublicFunction.goSecurity();//回安全区

        LtLog.i(publicFunction.getLineInfo() + "------->等待40S=" + ",要开始执行的任务：龙门之争");
        //如果是在侠客岛挂机 ,就不等了。
        for (int i = 0; i < 19 && TaskMain.taskMap.get("OnHookMap") != 999; i++) {
            Thread.sleep(2000);//等待40S
        }

        while (mFairy.condit()) {

            LtLog.i(publicFunction.getLineInfo() + "龙门之争活动中");

            int currentlyTIME = publicFunction.getMinuteNumber();
            if (currentlyTIME >= 1290) {
                break;
            } else if (currentlyTIME > 1261) {
                result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "在主场景时间超时,end!");
                    break;
                }
            }

            findResult = mFairy.findPic(460, 579, 643, 665, "lmzz1.png");
            mFairy.onTap(0.8f, findResult, "普通赛场", 1000);

            findResult = mFairy.findPic(1050, 175, 1259, 293, "lmzz.png");
            mFairy.onTap(0.8f, findResult, "龙门之争", 1000);

            findResult = mFairy.findPic(476, 25, 541, 692, "lmzz2.png");
            if (findResult.sim > 0.8f) {
                mFairy.onTap(0.8f, findResult, 1211, 578, 1222, 591, "结束!", 1000);
                mFairy.onTap(0.8f, findResult, 1211, 578, 1222, 591, "结束!", 1000);
                gamePublicFunction.closeWindow();
            }

            Thread.sleep(2000);
        }

    }//龙门之争

    public void shzj() throws Exception {

        gamePublicFunction.closeWindow();

        GoSecurityXiakeIsland();//如果在侠客岛,需要先回到安全区,,初始设计脚本时没有考虑到侠客岛这个奇葩,所以这个地方还要调用一次。

        gamePublicFunction.exitTeam();

        gamePublicFunction.goSecurity();//回安全区


        LtLog.i(publicFunction.getLineInfo() + "------->等待40S=" + ",要开始执行的任务：山河战境");
        //如果是在侠客岛挂机 ,就不等了。
        for (int i = 0; i < 19 && TaskMain.taskMap.get("OnHookMap") != 999; i++) {
            Thread.sleep(2000);//等待40S
        }

        gamePublicFunction.openActivity();

        LtLog.i(publicFunction.getLineInfo() + "打开活动窗口");

        long time = System.currentTimeMillis() / 1000, timex = 0;
        long time1 = System.currentTimeMillis() / 1000, time1x = 0;

        while (mFairy.condit()) {

            LtLog.i(publicFunction.getLineInfo() + "山河战境活动中");

            int currentlyTIME = publicFunction.getMinuteNumber();
            if (currentlyTIME > 1290) {
                break;
            }

            result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【活动场景】");

                for (int i = 0; i < 2; i++) {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                }

                Thread.sleep(2000);

                result = publicFunction.localFindPic(894, 126, 1076, 317, "shzj.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "发现活动");
                    publicFunction.rndTap(result.x + 19, result.y + 33, result.x + 89, result.y + 59);
                    Thread.sleep(3000);
                } else {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                    Thread.sleep(500);
                }

                time1x = System.currentTimeMillis() / 1000 - time1;
                if (time1x >= 60) {
                    gamePublicFunction.closeWindow();
                    LtLog.i(publicFunction.getLineInfo() + "---------------------outTime  not task----return---------------------->");
                    return;
                }

            } else {
                time1 = System.currentTimeMillis() / 1000;
            }

            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 90) {
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                gamePublicFunction.closeWindow();
                gamePublicFunction.openActivity();
                time = System.currentTimeMillis() / 1000;
            }

            result = publicFunction.localFindPic(335, 200, 870, 514, "goSecurity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->goSecurity=" + result);
                publicFunction.rndTap(734, 434, 805, 464);//点击同意
                Thread.sleep(1000);
            }

            findResult = mFairy.findPic( "shzj2.png");
            mFairy.onTap(0.8f, findResult, "参展", 1000);

            findResult = mFairy.findPic(969, 5, 1156, 78, "shzj3.png");
            if (findResult.sim > 0.8f) {
                time = System.currentTimeMillis() / 1000;
                LtLog.i(mFairy.getLineInfo("副本中"));
                long s = mFairy.mMatTime(1179, 135, 67, 17, 0.95f);
                if (s > 8) {
                    mFairy.onTap(1188, 68, 1215, 94, "点地图", 2000);
                    continue;
                }
                gamePublicFunction.manualOrAutomatic("automatic");
            }

            findResult = mFairy.findPic(170, 21, 625, 301, "shzj4.png");
            if (findResult.sim > 0.8f) {
                LtLog.i(mFairy.getLineInfo("地图界面"));
                for (int i = 0; i < 3; i++) {
                    mFairy.onTap(592, 386, 613, 406, "", 200);
                }
                gamePublicFunction.closeWindow();
                mFairy.initMatTime();
            }

            Thread.sleep(2000);
        }
    }//山河战境

    public void hsg() throws Exception {

        gamePublicFunction.closeWindow();

        GoSecurityXiakeIsland();//如果在侠客岛,需要先回到安全区,,初始设计脚本时没有考虑到侠客岛这个奇葩,所以这个地方还要调用一次。

        gamePublicFunction.goSecurity();//回安全区

        LtLog.i(publicFunction.getLineInfo() + "------->等待40S=" + ",要开始执行的任务：智取花石纲");
        //如果是在侠客岛挂机 ,就不等了。
        for (int i = 0; i < 19 && TaskMain.taskMap.get("OnHookMap") != 999; i++) {
            Thread.sleep(2000);//等待40S
        }

        gamePublicFunction.openActivity();

        LtLog.i(publicFunction.getLineInfo() + "打开活动窗口");

        long time = System.currentTimeMillis() / 1000, timex = 0;
        long time1 = System.currentTimeMillis() / 1000, time1x = 0;

        while (mFairy.condit()) {

            LtLog.i(publicFunction.getLineInfo() + "智取花石纲活动中");

            result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【活动场景】");
                time = System.currentTimeMillis() / 1000;

                for (int i = 0; i < 2; i++) {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                }

                Thread.sleep(2000);

                result = publicFunction.localFindPic(894, 126, 1076, 317, "hsg1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "发现活动");
                    publicFunction.rndTap(result.x + 19, result.y + 33, result.x + 89, result.y + 59);
                    Thread.sleep(3000);
                } else {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                    Thread.sleep(500);
                }

                time1x = System.currentTimeMillis() / 1000 - time1;
                if (time1x >= 60) {
                    gamePublicFunction.closeWindow();
                    LtLog.i(publicFunction.getLineInfo() + "---------------------outTime  not task----return---------------------->");
                    return;
                }

            } else {
                time1 = System.currentTimeMillis() / 1000;
            }

            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 60) {
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                gamePublicFunction.closeWindow();
                gamePublicFunction.openActivity();
                time = System.currentTimeMillis() / 1000;
            }

            result = publicFunction.localFindPic(335, 200, 870, 514, "goSecurity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->goSecurity=" + result);
                publicFunction.rndTap(734, 434, 805, 464);//点击同意
                Thread.sleep(1000);
            }

            findResult = mFairy.findPic(11, 61, 89, 375, "hsg2.png");
            if (findResult.sim > 0.8f) {
                switch (AtFairyConfig.getOption("hsgMap")) {
                    case "1":
                        mFairy.onTap(545, 305, 570, 321, "剑门蜀道", 2000);
                        break;
                    case "2":
                        mFairy.onTap(650, 181, 671, 203, "太白古道", 2000);
                        break;
                    case "3":
                        mFairy.onTap(819, 241, 836, 262, "琼山商道", 2000);
                        break;
                    case "4":
                        mFairy.onTap(956, 328, 974, 343, "秦岭古道", 2000);
                        break;
                }
            }

            findResult = mFairy.findPic(1141, 3, 1273, 39, "hsg3.png");
            if (findResult.sim > 0.8f) {
                time = System.currentTimeMillis() / 1000;
                LtLog.i(mFairy.getLineInfo("副本中"));
                gamePublicFunction.manualOrAutomatic("automatic");
            }

            Thread.sleep(2000);
        }

    }//智取花石纲

    public void hgkm() throws Exception {

        gamePublicFunction.closeWindow();

        GoSecurityXiakeIsland();//如果在侠客岛,需要先回到安全区,,初始设计脚本时没有考虑到侠客岛这个奇葩,所以这个地方还要调用一次。

        gamePublicFunction.closeWindow();

        gamePublicFunction.exitTeam();

        gamePublicFunction.goSecurity();//回安全区

        LtLog.i(publicFunction.getLineInfo() + "------->等待40S=" + ",要开始执行的任务：山河战境");
        //如果是在侠客岛挂机 ,就不等了。
        for (int i = 0; i < 19 && TaskMain.taskMap.get("OnHookMap") != 999; i++) {
            Thread.sleep(2000);//等待40S
        }

        gamePublicFunction.openActivity();

        LtLog.i(publicFunction.getLineInfo() + "打开活动窗口");

        long time = System.currentTimeMillis() / 1000, timex = 0;
        long time1 = System.currentTimeMillis() / 1000, time1x = 0;

        while (mFairy.condit()) {

            LtLog.i(publicFunction.getLineInfo() + "灰谷矿脉活动中");

            int currentlyTIME = publicFunction.getMinuteNumber();
            if (currentlyTIME >= 1248) {
                break;
            }

            result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【活动场景】");

                for (int i = 0; i < 2; i++) {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                }

                Thread.sleep(2000);

                result = publicFunction.localFindPic(894, 126, 1076, 317, "hgkm.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "发现活动");
                    publicFunction.rndTap(result.x + 19, result.y + 33, result.x + 89, result.y + 59);
                    Thread.sleep(3000);
                } else {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                    Thread.sleep(500);
                }

                time1x = System.currentTimeMillis() / 1000 - time1;
                if (time1x >= 60) {
                    gamePublicFunction.closeWindow();
                    LtLog.i(publicFunction.getLineInfo() + "---------------------outTime  not task----return---------------------->");
                    return;
                }

            } else {
                time1 = System.currentTimeMillis() / 1000;
            }

            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 90) {
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                gamePublicFunction.closeWindow();
                gamePublicFunction.openActivity();
                time = System.currentTimeMillis() / 1000;
            }

            result = publicFunction.localFindPic(335, 200, 870, 514, "goSecurity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->goSecurity=" + result);
                publicFunction.rndTap(734, 434, 805, 464);//点击同意
                Thread.sleep(1000);
            }


            findResult = mFairy.findPic(684, 549, 1123, 674, "hgkm1.png");
            mFairy.onTap(0.8f, findResult, "单人报名", 1000);


            findResult = mFairy.findPic(950, 211, 1257, 417, "hgkm2.png");
            if (findResult.sim > 0.8f) {
                time = System.currentTimeMillis() / 1000;
                LtLog.i(mFairy.getLineInfo("匹配队伍中"));

            }


            findResult = mFairy.findPic(483, 5, 792, 95, "hgkm3.png");
            if (findResult.sim > 0.8f) {
                time = System.currentTimeMillis() / 1000;
                LtLog.i(mFairy.getLineInfo("副本中"));


                long s = mFairy.mMatTime(1179, 135, 67, 17, 0.95f);
                if (s > 8) {
                    mFairy.onTap(1188, 68, 1215, 94, "点地图", 2000);
                    continue;
                }

                gamePublicFunction.manualOrAutomatic("automatic");
            }


            findResult = mFairy.findPic(182, 73, 350, 170, "hgkm5.png");
            mFairy.onTap(0.8f, findResult, 1215, 41, 1228, 56, "排名界面", 1000);


            findResult = mFairy.findPic(170, 21, 625, 301, "shzj4.png");
            if (findResult.sim > 0.8f) {

                LtLog.i(mFairy.getLineInfo("地图界面"));

                boolean bool = false;

                for (int i = 0; i < 5; i++) {

                    findResult = mFairy.findPic(507, 250, 760, 454, "hgkm4.png");
                    if (findResult.sim > 0.8f) {
                        mFairy.onTap(0.8f, findResult, "马车", 3000);
                        bool = true;
                        break;
                    }
                    Thread.sleep(300);
                }

                if (bool == false) {
                    mFairy.onTap(621, 302, 632, 311, "点击默认位置", 1000);
                }

                gamePublicFunction.closeWindow();
                mFairy.initMatTime();
            }

            Thread.sleep(2000);
        }

    }//灰谷矿脉

    public void qqyz() throws Exception {

        gamePublicFunction.closeWindow();

        GoSecurityXiakeIsland();//如果在侠客岛,需要先回到安全区,,初始设计脚本时没有考虑到侠客岛这个奇葩,所以这个地方还要调用一次。

        gamePublicFunction.closeWindow();

        gamePublicFunction.exitTeam();

        gamePublicFunction.goSecurity();//回安全区

        LtLog.i(publicFunction.getLineInfo() + "------->等待40S=" + ",要开始执行的任务：山河战境");
        //如果是在侠客岛挂机 ,就不等了。
        for (int i = 0; i < 19 && TaskMain.taskMap.get("OnHookMap") != 999; i++) {
            Thread.sleep(2000);//等待40S
        }

        gamePublicFunction.openActivity();

        LtLog.i(publicFunction.getLineInfo() + "打开活动窗口");

        long time = System.currentTimeMillis() / 1000, timex = 0;
        long time1 = System.currentTimeMillis() / 1000, time1x = 0;


        boolean fb = false;
        while (mFairy.condit()) {

            LtLog.i(publicFunction.getLineInfo() + "青丘疑阵活动中");

            int currentlyTIME = publicFunction.getMinuteNumber();
            if (currentlyTIME >= 1223) {
                break;
            }

            result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【活动场景】");

                for (int i = 0; i < 2; i++) {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                }

                Thread.sleep(2000);

                result = publicFunction.localFindPic(894, 126, 1076, 317, "qqyz.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "发现活动");
                    publicFunction.rndTap(result.x + 19, result.y + 33, result.x + 89, result.y + 59);
                    Thread.sleep(3000);
                } else {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                    Thread.sleep(500);
                }

                time1x = System.currentTimeMillis() / 1000 - time1;
                if (time1x >= 60) {
                    gamePublicFunction.closeWindow();
                    LtLog.i(publicFunction.getLineInfo() + "---------------------outTime  not task----return---------------------->");
                    return;
                }

            } else {
                time1 = System.currentTimeMillis() / 1000;
            }

            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 90) {
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                LtLog.i(publicFunction.getLineInfo() + "超时 close");

                gamePublicFunction.closeWindow();

                if(fb){
                    result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
                    if (result.sim >= 0.8) {
                        LtLog.e(mFairy.getLineInfo("活动完成"));
                        return;
                    }
                }

                gamePublicFunction.closeWindow();
                gamePublicFunction.openActivity();
                time = System.currentTimeMillis() / 1000;
            }

            result = publicFunction.localFindPic(335, 200, 870, 514, "goSecurity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->goSecurity=" + result);
                publicFunction.rndTap(734, 434, 805, 464);//点击同意
                Thread.sleep(1000);
            }


            findResult = mFairy.findPic(969,595,1181,693, "qqyz1.png");
            mFairy.onTap(0.8f, findResult, "参加活动", 1000);


            findResult = mFairy.findPic(950, 211, 1257, 417, "hgkm2.png");
            if (findResult.sim > 0.75) {
                time = System.currentTimeMillis() / 1000;
                LtLog.i(mFairy.getLineInfo("匹配队伍中"));

            }


            findResult = mFairy.findPic(483, 5, 792, 95, "hgkm3.png");
            if (findResult.sim > 0.8f) {
                time = System.currentTimeMillis() / 1000;
                LtLog.i(mFairy.getLineInfo("副本中"));
                fb=true;

                gamePublicFunction.manualOrAutomatic("automatic");
            }
            Thread.sleep(2000);
        }

    }//青丘疑阵

    public void lmx() throws Exception {

        gamePublicFunction.closeWindow();

        GoSecurityXiakeIsland();//如果在侠客岛,需要先回到安全区,,初始设计脚本时没有考虑到侠客岛这个奇葩,所以这个地方还要调用一次。

        gamePublicFunction.closeWindow();

        gamePublicFunction.exitTeam();

        gamePublicFunction.goSecurity();//回安全区

        LtLog.i(publicFunction.getLineInfo() + "------->等待40S=" + ",要开始执行的任务：粮秣行");
        //如果是在侠客岛挂机 ,就不等了。
        for (int i = 0; i < 19 && TaskMain.taskMap.get("OnHookMap") != 999; i++) {
            Thread.sleep(2000);//等待40S
        }

        gamePublicFunction.openActivity();

        LtLog.i(publicFunction.getLineInfo() + "打开活动窗口");

        long time = System.currentTimeMillis() / 1000, timex = 0;

        long time1 = System.currentTimeMillis() / 1000, time1x = 0;


        boolean fb = false;


        //粮秣行变量
        boolean song  = true;

        while (mFairy.condit()) {

            LtLog.i(publicFunction.getLineInfo() + "粮秣行活动中");

            /*int currentlyTIME = publicFunction.getMinuteNumber();
            if (currentlyTIME >= 1223) {
                break;
            }*/

            result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【活动场景】");

                for (int i = 0; i < 2; i++) {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                }

                Thread.sleep(2000);

                result = publicFunction.localFindPic(894, 126, 1076, 317, "lmx.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "发现活动");
                    publicFunction.rndTap(result.x + 19, result.y + 33, result.x + 89, result.y + 59);
                    Thread.sleep(3000);
                } else {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                    Thread.sleep(500);
                }

                time1x = System.currentTimeMillis() / 1000 - time1;
                if (time1x >= 60) {
                    gamePublicFunction.closeWindow();
                    LtLog.i(publicFunction.getLineInfo() + "---------------------outTime  not task----return---------------------->");
                    return;
                }

            } else {
                time1 = System.currentTimeMillis() / 1000;
            }

            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 90) {
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                LtLog.i(publicFunction.getLineInfo() + "超时 close");
                LtLog.i(publicFunction.getLineInfo() + "超时 close");

                gamePublicFunction.closeWindow();

                if(fb){
                    result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
                    if (result.sim >= 0.8) {
                        LtLog.e(mFairy.getLineInfo("活动完成"));
                        return;
                    }
                }

                gamePublicFunction.closeWindow();
                gamePublicFunction.openActivity();
                time = System.currentTimeMillis() / 1000;
            }


            result = publicFunction.localFindPic(335, 200, 870, 514, "goSecurity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->goSecurity=" + result);
                publicFunction.rndTap(734, 434, 805, 464);//点击同意
                Thread.sleep(1000);
            }


            findResult = mFairy.findPic(646,614,1158,715, "lmx1.png");
            mFairy.onTap(0.72f, findResult, "粮秣行参加", 1000);


            findResult = mFairy.findPic(918,52,1089,199, "fb.png");
            if (findResult.sim > 0.75f) {

                time = System.currentTimeMillis() / 1000;
                LtLog.i(mFairy.getLineInfo("副本中"));
                fb=true;

                if(song){
                    //开始拉货
                    mFairy.onTap(607,69,661,85,"开始位置",3000);
                }else{
                    mFairy.onTap(860,72,898,80,"结束位置",3000);
                }

                findResult = mFairy.findPic(943,190,1078,300, "lmx2.png");
                mFairy.onTap(0.8f, findResult, "人头", 2000);

            }


            findResult = mFairy.findPic(325,67,445,243, "lmx3.png");
            if (findResult.sim > 0.75f) {

                if(song){
                    //开始拉货
                    mFairy.onTap(441,378,490,397,"领取",3000);
                    song=false;
                }else{
                    mFairy.onTap(426,467,476,482,"交付",3000);


                    for (int i = 0; i < 10; i++) {

                        findResult = mFairy.findPic(918,52,1089,199, "fb.png");
                        if(findResult.sim>0.8f) {
                            mFairy.onTap(0.8f, findResult, "离开", 2000);
                            mFairy.onTap(0.8f, findResult, 747, 442, 778, 457, "离开", 1000);
                            break;
                        }
                    }

                    return;
                }
            }

            findResult = mFairy.findPic(1182,177,1272,288, "lmx4.png");
            mFairy.onTap(0.8f, findResult, "上车", 8000);

            Thread.sleep(2000);
        }
    }//粮秣行


    //心魔幻境
    private boolean XMHJ() throws Exception {
        boolean state = false;

        gamePublicFunction.manualOrAutomatic("automatic");

        result = publicFunction.localFindPic(473, 584, 695, 674, "attend.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "参加" + result);
            publicFunction.rndTapWH(result.x, result.y, 28, 26);
            Thread.sleep(500);
        }
        result = gamePublicFunction.leave();
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------leave--->=" + result);
            state = true;
        }
        return state;
    }

    private boolean huaShan() throws Exception {
        boolean state = false;
        gamePublicFunction.manualOrAutomatic("automatic");
        result = publicFunction.localFindPic(955, 589, 1160, 664, "attend.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------attend--->=" + result);
            publicFunction.rndTapWH(result.x, result.y, 28, 26);
            Thread.sleep(500);
        }
        result = gamePublicFunction.leave();
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------leave--->=" + result);
            state = true;
        }
        result = publicFunction.localFindPic(1039, 252, 1160, 375, "automatic.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------automatic--->=" + result);
            state = true;
        }
        return state;
    }

    public void activityNiceOrTen(String mTaskTime) throws Exception {
        GoSecurityXiakeIsland();//如果在侠客岛,需要先回到安全区
        //活动判断
        LtLog.i(publicFunction.getLineInfo() + "----------------------activityNiceOrTen------>=");

        gamePublicFunction.openActivity();//打开活动

        Thread.sleep(500);

        String[] taskList = {};

        //familyProtect 家族保卫战
        //nineTTT 通天塔
        //nineAttackCity1 跨服攻城战
        //territory 跨服领土战
        //nineExperiment 家族试炼
        //nineAttackCity攻城战
        //nineAthletics 门派竞技
        //nineHero 群英会
        String currentSun = publicFunction.getCurrSun();
        if (mTaskTime.equals("nice")) {
            taskList = new String[]{"nineAthletics", "nineAttackCity", "nineExperiment", "nineHero", "nineTTT", "familyMJ", "familyProtect", "territory", "nineAttackCity1"};
        } else if (mTaskTime.equals("ten")) {
            //wednesdayAZXYandDM 决定了周三22点检测什么活动，如果勾选此选项，周三至检测 达摩 鏖战襄阳 不再检测名将，未勾选的话照旧去打名将；
            if (TaskMain.taskMap.get("wednesdayAZXYandDM") == 1 && currentSun.equals("星期三")) {
                taskList = new String[]{"tenSH", /*"tenMJ1",*/ "queen", "DM", "AZXY"};//  始皇 女帝 达摩 鏖战襄阳 跨服名将
            } else {
                taskList = new String[]{"tenMJ", "tenSH", "tenMJ1", "queen"};// 名将 始皇 女帝
            }
        }
        LtLog.i(publicFunction.getLineInfo() + "------->=");

        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
            // 如果在活动界面 进去以下逻辑
            if (result.sim >= 0.8) {

                //如果在 活动日历
                for (int j = 0; j < 5; j++) {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                }

                Thread.sleep(2000);
                LtLog.i(publicFunction.getLineInfo() + "------->activity1=" + result);

                for (String task : taskList) {
                    //遍历活动
                    result = publicFunction.localFindPic(843, 105, 1099, 315, task + ".png");
                    LtLog.i(publicFunction.getLineInfo() + "------->task=" + task + ", result " + result);
                    if (result.sim >= 0.8) {
                        //发现活动
                        LtLog.i(publicFunction.getLineInfo() + "------->task=" + result);

                        gamePublicFunction.closeWindow();

                        if ((task.equals("tenMJ") || task.equals("tenMJ1")) && AtFairyConfig.getOption("mjexit").equals("1")) {
                            gamePublicFunction.exitTeam(); //退出当前队伍
                            gamePublicFunction.closeWindow();
                        }

                        if (task.equals("nineHero") || task.equals("territory")) {
                            //如果是 群英会 或者 领土战，等待3分钟
                            for (int j = 0; j < 60; j++) {
                                LtLog.i(publicFunction.getLineInfo() + "------->群英会 或者 领土战 ,等待3分钟=" + task);
                                if (publicFunction.getMinuteNumber() >= 1264) {
                                    break;
                                }
                                Thread.sleep(3000);
                            }
                        }

                        timingTask(task);//进入9点活动--任务执行过程.参数:任务名称

                        break;
                    }
                }
                publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                Thread.sleep(500);
            } else {
                return;
            }
            result = publicFunction.localFindPic(1156, 84, 1280, 241, "everyday1.png");
            LtLog.i(publicFunction.getLineInfo() + "------->everyday1=" + result);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->everyday1=" + result);
                publicFunction.rndTapWH(result.x, result.y, 24, 57);
                Thread.sleep(500);
            }
            Thread.sleep(500);
        }
        Thread.sleep(100);
        gamePublicFunction.closeWindow();
        Thread.sleep(100);
    }

    private boolean tenSH() throws Exception {
        boolean state = false;
        boolean skillState;
        skillState = publicFunction.judgeMatChange(1031, 631, 20, 20, 0.95, 2000);
        if (skillState == false) {
            LtLog.i(publicFunction.getLineInfo() + "------skillState--->=" + skillState);
            gamePublicFunction.openActivity();
        }
        result = publicFunction.localFindPic(1, 56, 74, 326, "SH.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------SH--->=" + result);
            SH();//选择BOSS
            state = true;
        }
        result = gamePublicFunction.leave();
        if (result.sim >= 0.8) {
            gamePublicFunction.switchSkillOrTong("skill");
            LtLog.i(publicFunction.getLineInfo() + "------leave--->=" + result);
            result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->activity=" + result);
            } else if (skillState == false) {
                //如果没有活动按钮 并且右上出现离开 并且技能无变动 执行 打开小地图 走到地图中间
                gamePublicFunction.openMapWorldOrCurrent("current");
                result = publicFunction.localFindPic(342, 57, 933, 649, "smallMap.png|smallMap1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------->smallMap=" + result);
                    publicFunction.rndTap(681, 293, 698, 308);//点击地图中间
                    Thread.sleep(1000);
                    gamePublicFunction.closeWindow();
                }
                gamePublicFunction.manualOrAutomatic("automatic");
            }
            state = true;
        }
        //gamePublicFunction.clickDetermine();
        return state;
    }

    private void SH() throws Exception {
        //选择打哪个BOSS
        int colorNunber = 0;
        int sh = 0;
        result = publicFunction.localFindPic(98, 78, 377, 452, "LS.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------LS--->=" + result + ",,taskMap=" + TaskMain.taskMap.get("sh"));
            if (TaskMain.taskMap.get("sh") <= 3) {
                LtLog.i(publicFunction.getLineInfo() + "------打玄武--->=");
                mFairy.tap(result.x, result.y - 169);
            } else if (TaskMain.taskMap.get("sh") >= 4 && TaskMain.taskMap.get("sh") <= 6) {
                LtLog.i(publicFunction.getLineInfo() + "------打白起--->=");
                mFairy.tap(result.x, result.y - 103);
            } else if (TaskMain.taskMap.get("sh") >= 7 && TaskMain.taskMap.get("sh") <= 9) {
                LtLog.i(publicFunction.getLineInfo() + "------打李斯--->=");
                mFairy.tap(result.x, result.y);
            }
            Thread.sleep(2000);
            //sh  定位前往区域 ,
            sh = TaskMain.taskMap.get("sh") % 3;
            if (sh == 0) {
                sh = 3;
            }
            sh = sh - 1;
        }
        for (int i = 0; i < 3; i++) {
            result = publicFunction.localFindPic(685, 155 + (sh * 77), 811, 223 + (sh * 77), "goTo.png");
            LtLog.i(publicFunction.getLineInfo() + "------goTo--->=" + result);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------goTo--->=" + result);
                publicFunction.rndTapWH(result.x, result.y, 20, 20);
                Thread.sleep(500);
                for (int j = 0; j < 5; j++) {
                    //使用夜明珠 点击确认
                    gamePublicFunction.clickDetermine();
                    Thread.sleep(500);
                }
                break;
            }
//            result = publicFunction.localFindPic(685, 155 + (i * 77), 811, 223 + (i * 77), "goTo.png");
//            LtLog.i(publicFunction.getLineInfo() + "------goTo--->=" + result);
//            if (result.sim >= 0.8) {
//                LtLog.i(publicFunction.getLineInfo() + "------goTo--->=" + result);
//                publicFunction.rndTapWH(result.x, result.y, 20, 20);
//                Thread.sleep(500);
//                for (int j = 0; j < 5; j++) {
//                    //使用夜明珠 点击确认
//                    gamePublicFunction.clickDetermine();
//                    Thread.sleep(500);
//                }
//                  break;
//            }
            result = publicFunction.localFindPic(98, 78, 377, 452, "SH1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------SH1--->=" + result);
                publicFunction.rndTapWH(result.x, result.y, 20, 20);
                Thread.sleep(1000);
                result = publicFunction.localFindPic(662, 137, 828, 397, "goTo.png");
                LtLog.i(publicFunction.getLineInfo() + "------goTo--->=" + result);
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------goTo--->=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 20, 20);
                    Thread.sleep(500);
                    for (int j = 0; j < 5; j++) {
                        //使用夜明珠 点击确认
                        gamePublicFunction.clickDetermine();
                        Thread.sleep(500);
                    }
                    break;
                }
            }
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(352, 56, 522, 180, "SH2.png");
        if (result.sim >= 0.8) {
            int shState = 0;
            for (int i = 0; i <= 2; i++) {
                colorNunber = publicFunction.getColorNumber(399, 172 + (i * 77), 503, 208 + (i * 77), "159,0,0", 0.9);
                LtLog.i(publicFunction.getLineInfo() + "------colorNunber--->=" + colorNunber);
                if (colorNunber >= 10) {
                    LtLog.i(publicFunction.getLineInfo() + "------------------------第" + i + "个始皇已死");
                    shState = shState + 1;
                } else {
                    LtLog.i(publicFunction.getLineInfo() + "------------------------第" + i + "个始皇存活");
                    break;
                }
            }
            if (shState == 3) {
                //始皇全部死亡 设置tenState 状态为false
                tenState = false;
                return;
            }
            result = publicFunction.localFindPic(662, 137, 828, 397, "goTo.png");
            LtLog.i(publicFunction.getLineInfo() + "------goTo--->=" + result);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------goTo--->=" + result);
                publicFunction.rndTapWH(result.x, result.y, 20, 20);
                Thread.sleep(500);
                for (int j = 0; j < 5; j++) {
                    //使用夜明珠 点击确认
                    gamePublicFunction.clickDetermine();
                    Thread.sleep(500);
                }
            }
        }
    }

    private boolean tenMJ() throws Exception {
        boolean state = false;
        boolean skillState;
        int colorNunber;
        gamePublicFunction.manualOrAutomatic("automatic");

        result = publicFunction.localFindPic(0, 27, 94, 345, "MJ.png");
        //如果在名将选择界面
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "名将界面  MJname=" + MJname);
            state = true;
            mapChange = true;
            MJ(); //选择名将地图 并前往
        }

        skillState = publicFunction.judgeMatChange(1031, 631, 20, 20, 0.95, 5000);

        //判断技能在5s 内是否有刷新
        result = gamePublicFunction.leave();

        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------leave--->=" + result + ",skillState=" + skillState);
            state = true;
        }

        if (skillState == false && result.sim >= 0.8) {
            gamePublicFunction.openMapWorldOrCurrent("current");
            if (MJmap > 2 || MJmap < 0) {
                MJmap = 0;
            }

            setMJXY(); //设置名将地图要去的3个位置坐标

            LtLog.i(publicFunction.getLineInfo() + "------MJXY--->=" + MJXY + ",MJmap=" + MJmap);
            mFairy.tap(MJXY[MJmap][0], MJXY[MJmap][1]);
            //名将总共3个位置，每移动一次 MJmap + 1
            MJmap = MJmap + 1;
            Thread.sleep(5000);
            gamePublicFunction.closeWindow();
        }

        colorNunber = publicFunction.getColorNumber(719, 133, 772, 143, "0,0,0", 1.0);
        if (colorNunber >= 300 && result.sim >= 0.8) {
            //如果发现名将血条上黑色的数量大于300， MJmap - 1
            mapChange = false;
            MJmap = MJmap - 1;
        }
        LtLog.i(publicFunction.getLineInfo() + "------MJmap--->=" + MJmap + ",,mapChange=" + mapChange + ",colorNunber=" + colorNunber + ",skillState=" + skillState + ",current=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return state;
    }

    //
    private void MJ() throws Exception {
        int colorNunber = 0;

        for (int i = 0; i < 6; i++) {

            result = publicFunction.localFindPic(98, 77, 362, 664, MJname);
            LtLog.i(publicFunction.getLineInfo() + "Mjname=" + MJname + ",相似度=" + result.sim);
            if (result.sim >= 0.7) {

                LtLog.i(publicFunction.getLineInfo() + "找到选择的名将");

                publicFunction.rndTapWH(result.x, result.y, 20, 20);

                Thread.sleep(2000);

                colorNunber = publicFunction.getColorNumber(492, 154, 596, 176, "159,0,0", 0.85);
                //得到名将名字上的红色色点的数量，红色色点数量大于20 认为此名将已死
                LtLog.i(publicFunction.getLineInfo() + "------->colorNunber=" + colorNunber);
                if (colorNunber >= 20) {
                    LtLog.i(publicFunction.getLineInfo() + "------" + MJname + "-->已死=");
                    findResult = mFairy.findPic(496, 134, 553, 359, "mjtime.png");
                    if (findResult.sim < 0.85f) {
                        break;
                    }
                }

                result = publicFunction.localFindPic(949, 590, 1066, 650, "goTo.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------->goTo=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 20, 20);
                    Thread.sleep(500);
                    return;
                }

            } else {

                LtLog.i(publicFunction.getLineInfo() + "滑动找名将");

                if (i < 3) {
                    publicFunction.RanSwipe(244, 147, 335, 384, 2, 500);
                } else {
                    publicFunction.RanSwipe(244, 147, 335, 384, 0, 500);
                }
            }
            Thread.sleep(1000);
        }

        if (MJlist.size() == 0) {
            tenState = false;
            gamePublicFunction.closeWindow();
        } else {
            LtLog.i(publicFunction.getLineInfo() + "------" + MJlist.get(0).toString() + "-->=");
            //删除列表中的名将
            MJlist.remove(MJname);
            LtLog.i(publicFunction.getLineInfo() + "------remove" + "MJlist.size=" + MJlist.size());
            if (MJlist.size() == 0) {
                tenState = false;
                gamePublicFunction.closeWindow();
                return;
            }

            LtLog.i(publicFunction.getLineInfo() + "------" + MJlist.get(0).toString() + "-->=");

            MJname = MJlist.get(0).toString();//勾选的名将击败后 开始检测其他名将是否刷新

            LtLog.i(publicFunction.getLineInfo() + "------" + MJname + "-->=");

            Thread.sleep(1000);
        }
    }

    private void setMJ(String actName) {

        MJlist.add("MJQLY.png" + "|" + "MJQLY1.png");
        MJlist.add("MJGCG.png|MJGCG1.png");
        MJlist.add("MJZB.png|MJZB1.png");
        MJlist.add("MJHSZ.png|MJHSZ1.png");
        MJlist.add("MJYCZ.png|MJYCZ1.png");
        MJlist.add("MJAB.png" + "|" + "MJAB1.png");
        MJlist.add("MJYF.png|MJYF1.png");
        MJlist.add("MJYJ.png|MJYJ1.png");
        MJlist.add("MJJK.png|MJJK1.png");
        MJlist.add("MJLYB.png|MJLYB1.png");
        MJlist.add("MJZY.png|MJZY1.png");
        MJlist.add("MJLG.png|MJLG1.png");
        //跨服 幻境 名将
        if (actName.equals("tenMJ1")) {
            MJlist.add("dreamlandLG.png" + "|" + "dreamlandLG1.png");
            MJlist.add("dreamlandZY.png" + "|" + "dreamlandZY1.png");
            MJlist.add("dreamlandLYB.png" + "|" + "dreamlandLYB1.png");
            MJlist.add("dreamlandJK.png" + "|" + "dreamlandJK1.png");
            MJlist.add("dreamlandYJ.png" + "|" + "dreamlandYJ1.png");
            MJlist.add("dreamlandYF.png" + "|" + "dreamlandYF1.png");
        }

        switch (TaskMain.taskMap.get("mj")) {
            case 0:
                MJname = "MJQLY.png" + "|" + "MJQLY1.png";
                MJXY = new int[][]{{500, 342}, {640, 267}, {773, 361}};
                break;
            case 1:
                //秦良玉
                MJname = "MJQLY.png|MJQLY1.png";
                MJXY = new int[][]{{500, 342}, {640, 267}, {773, 361}};
                break;
            case 2:
                //高长恭
                MJname = "MJGCG.png|MJGCG1.png";
                MJXY = new int[][]{{493, 316}, {643, 242}, {774, 359}};
                break;
            case 3:
                //哲别
                MJname = "MJZB.png|MJZB1.png";
                MJXY = new int[][]{{475, 360}, {639, 186}, {779, 348}};
                break;
            case 4:
                //韩世忠
                MJname = "MJHSZ.png|MJHSZ1.png";
                MJXY = new int[][]{{500, 339}, {637, 265}, {772, 360}};
                break;
            case 5:
                //袁承志
                MJname = "MJYCZ.png" + "|" + "MJYCZ1.png";
                MJXY = new int[][]{{494, 318}, {642, 241}, {771, 360}};
                break;

            case 6:
                //鳌拜
                MJname = "MJAB.png|MJAB1.png";
                MJXY = new int[][]{{484, 361}, {642, 178}, {776, 361}};
                break;
            case 7:
                //岳飞
                if (actName.equals("tenMJ1")) { //幻境-岳飞
                    MJname = "dreamlandYF.png" + "|" + "dreamlandYF1.png";
                    MJXY = new int[][]{{502, 341}, {644, 265}, {771, 366}};
                    return;
                }

                MJname = "MJYF.png|MJYF1.png";
                MJXY = new int[][]{{503, 340}, {644, 271}, {774, 363}};

                break;
            case 8:
                //虞姬

                if (actName.equals("tenMJ1")) { //幻境-虞姬
                    MJname = "dreamlandYJ.png|dreamlandYJ1.png";
                    MJXY = new int[][]{{494, 318}, {614, 250}, {741, 369}};
                    return;
                }

                MJname = "MJYJ.png" + "|" + "MJYJ1.png";
                MJXY = new int[][]{{493, 316}, {641, 239}, {771, 361}};
                break;
            case 9:
                //荆轲

                if (actName.equals("tenMJ1")) { //幻境-荆轲
                    MJname = "dreamlandJK.png|dreamlandJK1.png";
                    MJXY = new int[][]{{484, 362}, {658, 204}, {780, 348}};
                    return;
                }

                MJname = "MJJK.png|MJJK1.png";
                MJXY = new int[][]{{483, 363}, {637, 182}, {779, 360}};
                break;
            case 10:
                //李元霸
                if (actName.equals("tenMJ1")) { //幻境-李元霸
                    MJname = "dreamlandLYB.png|dreamlandLYB1.png";
                    MJXY = new int[][]{{505, 342}, {625, 272}, {808, 376}};
                    return;
                }

                MJname = "MJLYB.png|MJLYB1.png";
                MJXY = new int[][]{{503, 339}, {643, 265}, {778, 357}};
                break;
            case 11:
                //赵云
                if (actName.equals("tenMJ1")) { //幻境-赵云
                    MJname = "dreamlandZY.png|dreamlandZY1.png";
                    MJXY = new int[][]{{495, 314}, {642, 233}, {774, 363}};
                    return;
                }

                MJname = "MJZY.png|MJZY1.png";
                MJXY = new int[][]{{494, 318}, {641, 239}, {772, 364}};
                break;
            case 12:
                //李广
                if (actName.equals("tenMJ1")) { //幻境-李广
                    MJname = "dreamlandLG.png"+"|"+"dreamlandLG1.png";
                    MJXY = new int[][]{{482, 361}, {629, 182}, {775, 340}};
                    return;
                }

                MJname = "MJLG.png" + "|" + "MJLG1.png";
                MJXY = new int[][]{{480, 357}, {638, 189}, {776, 356}};
                break;

        }
    }

    private void setMJXY() {
        switch (MJname) {
            case "MJQLY.png|MJQLY1.png":
                //秦良玉 0,1
                MJXY = new int[][]{{500, 342}, {640, 267}, {773, 361}};
                break;
            case "MJGCG.png|MJGCG1.png":
                //高长恭2
                MJXY = new int[][]{{493, 316}, {643, 242}, {774, 359}};
                break;
            case "MJZB.png|MJZB1.png":
                //哲别3
                MJXY = new int[][]{{475, 360}, {639, 186}, {779, 348}};
                break;
            case "MJHSZ.png|MJHSZ1.png":
                //韩世忠4
                MJXY = new int[][]{{500, 339}, {637, 265}, {772, 360}};
                break;
            case "MJYCZ.png|MJYCZ1.png":
                //袁承志5
                MJXY = new int[][]{{494, 318}, {642, 241}, {771, 360}};
                break;
            case "MJAB.png|MJAB1.png":
                //鳌拜6
                MJXY = new int[][]{{484, 361}, {642, 178}, {776, 361}};
                break;
            case "MJYF.png|MJYF1.png":
                //岳飞7
                MJXY = new int[][]{{503, 340}, {644, 271}, {774, 363}};
                break;
            case "MJYJ.png|MJYJ1.png":
                //虞姬8
                MJXY = new int[][]{{493, 316}, {641, 239}, {771, 361}};
                break;
            case "MJJK.png|MJJK1.png":
                //荆轲9
                MJXY = new int[][]{{483, 363}, {637, 182}, {779, 360}};
                break;
            case "MJLYB.png|MJLYB1.png":
                //李元霸 10
                MJXY = new int[][]{{503, 339}, {643, 265}, {778, 357}};
                break;
            case "MJZY.png|MJZY1.png":
                //赵云11
                MJXY = new int[][]{{494, 318}, {641, 239}, {772, 364}};
                break;
            case "MJLG.png|MJLG1.png":
                //李广12
                MJXY = new int[][]{{480, 357}, {638, 189}, {776, 356}};
                break;
            case "dreamlandLG.png|dreamlandLG1.png":
                //幻境-李广13
                MJXY = new int[][]{{482, 361}, {629, 182}, {775, 340}};
                break;
            case "dreamlandZY.png|dreamlandZY1.png":
                //幻境-赵云 14
                MJXY = new int[][]{{495, 314}, {642, 233}, {774, 363}};
                break;
            case "dreamlandLYB.png|dreamlandLYB1.png":
                //幻境-李元霸15
                MJXY = new int[][]{{505, 342}, {625, 272}, {808, 376}};
                break;
            case "dreamlandJK.png|dreamlandJK1.png":
                //幻境-荆轲16
                MJXY = new int[][]{{484, 362}, {658, 204}, {780, 348}};
                break;
            case "dreamlandYJ.png|dreamlandYJ1.png":
                //幻境-虞姬 17
                MJXY = new int[][]{{494, 318}, {614, 250}, {741, 369}};
                break;
            case "dreamlandYF.png|dreamlandYF1.png":
                //幻境-岳飞18
                MJXY = new int[][]{{502, 341}, {644, 265}, {771, 366}};
                break;
        }
    }

    /**
     * 女帝
     *
     * @return
     * @throws Exception
     */
    private boolean queen() throws Exception {
        boolean state = false;
        int select_into = TaskMain.taskMap.get("queen") - 1;
        result = publicFunction.localFindPic(1141, 0, 1277, 39, "queen2.png");
        LtLog.i(publicFunction.getLineInfo() + "------queen2--->=" + result);
        if (result.sim > 0.8) {
            //已在副本地图
            //打开活动 ,查看 武则天 在哪个方位刷新。确认方位后前往对应坐标
            if (queenMap.size() == 0) {
                gamePublicFunction.openActivity();
                result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------->activity1=" + result);
                    for (int i = 0; i < 2; i++) {
                        publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                    }
                    Thread.sleep(2000);
                    result = publicFunction.localFindPic(894, 126, 1076, 317, "queen.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------->queen=" + result);
                        publicFunction.rndTap(result.x + 19, result.y + 33, result.x + 89, result.y + 59);
                        Thread.sleep(3000);
                    } else {
                        publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                        Thread.sleep(500);
                    }
                }
                queenMap = getQueenPosition();
            }
            gamePublicFunction.openMapWorldOrCurrent("current");
            result = publicFunction.localFindPic(342, 57, 933, 649, "smallMap.png|smallMap1.png");
            LtLog.i(publicFunction.getLineInfo() + "------smallMap--->=" + result + ", queenMap" + queenMap.size());
            if (result.sim >= 0.8 && queenMap.size() > 0) {
                //                西白虎 563,346  已测 编号 1
                //                东青龙 724,313  已测 编号 2
                //                南朱雀 635,386  已测 编号 3
                //                北玄武 656,310  已测 编号 4
                int[] xy = null;
                for (int i = 1; i <= 4; i++) {
                    xy = queenMap.get(i);
                    if (xy != null) {
                        break;
                    }
                }
                mFairy.tap(xy[0], xy[1]);
                Thread.sleep(1000);
                gamePublicFunction.closeWindow();
                MatTime matTime = new MatTime(mFairy);
                long sleepTime = 0;
                for (int i = 0; i < 30; i++) {
                    sleepTime = matTime.mMatTime(1177, 137, 68, 19, 0.9f);
                    LtLog.i(publicFunction.getLineInfo() + "------sleepTime--->=" + sleepTime);
                    if (sleepTime > 3) {
                        break;
                    }
                    result = publicFunction.localFindPic(509, 406, 770, 500, "goHome.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------>goHome=" + result);
                        publicFunction.rndTapWH(result.x, result.y, 43, 19);
                        Thread.sleep(500);
                    }
                    Thread.sleep(1000);
                }
            }
            gamePublicFunction.closeWindow();
        }
        result = publicFunction.localFindPic(1141, 0, 1277, 39, "queen_room.png");
        LtLog.i(publicFunction.getLineInfo() + "------queen_room--->=" + result);
        if (result.sim > 0.8) {
            //已在女帝房间
            gamePublicFunction.openMapWorldOrCurrent("current");
            if (System.currentTimeMillis() % 2 == 1) {
                mFairy.tap(722, 379);
            } else {
                mFairy.tap(601, 264);
            }
            Thread.sleep(8000);
            mFairy.tap(721, 271);
            Thread.sleep(100);
            gamePublicFunction.closeWindow();
            for (int i = 0; i < 4; i++) {
                gamePublicFunction.manualOrAutomatic("automatic");
                Thread.sleep(1000);
            }
            tenState = true;
        }
        result = publicFunction.localFindPic(3, 41, 82, 218, "queen1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------queen1--->=" + result);
            result = publicFunction.localFindPic(661, 153 + (select_into * 77), 829, 227 + (select_into * 77), "goTo.png");
            LtLog.i(publicFunction.getLineInfo() + "------goTo--->=" + result);
            if (result.sim > 0.8) {
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(1000);
            } else {
                result = publicFunction.localFindPic(629, 151, 849, 478, "goTo.png");
                LtLog.i(publicFunction.getLineInfo() + "------goTo--->=" + result);
                if (result.sim > 0.8) {
                    publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                    Thread.sleep(1000);
                }
            }
        }
        result = publicFunction.localFindPic(539, 292, 673, 357, "gem.png");
        LtLog.i(publicFunction.getLineInfo() + "------gem--->=" + result);
        if (result.sim > 0.8) {
            publicFunction.rndTap(729, 434, 808, 464);//点击确认
            Thread.sleep(1000);
        }
        return state;
    }

    /**
     * 得到女帝所在的方向位置
     *
     * @return
     */
    private Map<Integer, int[]> getQueenPosition() throws Exception {
        //                西白虎 563,346  已测 编号 1
        //                东青龙 724,313  已测 编号 2
        //                南朱雀 635,386  已测 编号 3
        //                北玄武 656,310  已测 编号 4
        Map<Integer, int[]> positionMap = new HashMap<Integer, int[]>();
        int[][] pos = {{563, 346}, {724, 313}, {635, 386}, {656, 310}};
        result = publicFunction.localFindPic(353, 57, 556, 192, "queen3.png");
        LtLog.i(publicFunction.getLineInfo() + "------queen3--->=" + result);
        if (result.sim > 0.8) {
            for (int i = 1; i <= 4; i++) {
                result = publicFunction.localFindPic(396, 164, 550, 465, "queen_into" + Integer.toString(i) + ".png");
                LtLog.i(publicFunction.getLineInfo() + "------queen_into  " + i + "    --->=" + result);
                if (result.sim > 0.8) {
                    positionMap.put(i, pos[i - 1]);
                }
            }
        }
        gamePublicFunction.closeWindow();
        return positionMap;
    }

    /**
     * 达摩洞
     *
     * @return
     * @throws Exception
     */
    private boolean DM() throws Exception {
        boolean state = false;
        gamePublicFunction.manualOrAutomatic("automatic");
        result = publicFunction.localFindPic(950, 557, 1025, 632, "attend5.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------attend5--->=" + result);
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
            state = true;
        }
        return state;
    }

    /**
     * 鏖战襄阳
     *
     * @return
     */
    private boolean AZXY() throws Exception {
        boolean state = false;
        gamePublicFunction.manualOrAutomatic("automatic");
        result = publicFunction.localFindPic(950, 557, 1025, 632, "attend5.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------attend5--->=" + result);
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
            state = true;
        }
        result = publicFunction.localFindPic(928, 19, 1009, 98, "azxy1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------azxy1--->=" + result);
            state = true;
        }
        return state;
    }

    /**
     * 南海钩沉
     *
     * @return
     * @throws Exception
     */
    private boolean NHGC() throws Exception {
        boolean state = false;
        gamePublicFunction.manualOrAutomatic("automatic");
        result = publicFunction.localFindPic(783, 592, 931, 652, "attend.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------attend--->=" + result);
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
            state = true;
        }
        result = publicFunction.localFindPic(981, 97, 1060, 176, "leave-6.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------leave-6--->=" + result);
            state = true;
        }
        result = publicFunction.localFindPic(1142, 1, 1230, 33, "nhgc1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------nhgc1--->=" + result);
            state = true;
        }

        return state;
    }

    private boolean nineAthletics() throws Exception {
        boolean state = false;
        gamePublicFunction.manualOrAutomatic("automatic");
        result = publicFunction.localFindPic(2, 60, 102, 185, "athletics.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------athletics--->=" + result);
            gamePublicFunction.closeWindow();
            state = true;
        }

//        result = publicFunction.localFindPic(814, 1, 1279, 309, "leave.png");
        result = gamePublicFunction.leave();
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------leave--->=" + result);
            state = true;
        }
        result = publicFunction.localFindPic(154, 210, 275, 274, "over.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------over--->=" + result);
            publicFunction.rndTap(1035, 41, 1067, 63);
            Thread.sleep(2000);
        }
        gamePublicFunction.clickDetermine();
        return state;
    }

    //攻城战 and //领土战
    private boolean nineAttackCity() throws Exception {
        boolean state = false;
        boolean skillState = false;
        gamePublicFunction.manualOrAutomatic("automatic");
        result = publicFunction.localFindPic(954, 577, 1200, 691, "attend.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------attend--->=" + result);
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
            state = true;
        }
        //result = publicFunction.localFindPic(814, 1, 1279, 309, "leave.png");
        result = gamePublicFunction.leave();
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------leave--->=" + result);
            state = true;
        }

        AtFairy2.OpencvResult result1 = publicFunction.localFindPic(1039, 252, 1160, 375, "automatic.png");
        skillState = publicFunction.judgeMatChange(1031, 631, 20, 20, 0.95, 1000);
        /*  if (skillState == false && result.sim >= 0.8 && result1.sim <= 0.8 && mFairy.taskMap.get("AttackCityStop").equals("0")) {
            2019.5.28-21.31  去掉条件 自动按钮 领土战中在安全区没有自动按钮 通过判断是否使用技能决定是否要移动*/
        LtLog.i(publicFunction.getLineInfo() + "---skillState--->=" + skillState + ",,result1=" + result1 + ",AttackCityStop=" + TaskMain.taskMap.get("AttackCityStop").equals("0"));
        if (skillState == false && result.sim >= 0.8 && TaskMain.taskMap.get("AttackCityStop").equals("0")) {

            //技能没有在使用skillState  有离开按钮（result）  没有自动按钮  （result1）   没有勾选攻城战挂机 就走到地图中间

            gamePublicFunction.openMapWorldOrCurrent("current");
            result = publicFunction.localFindPic(397, 167, 872, 538, "pillar.png");
            if (result.sim >= 0.8) {
                publicFunction.rndTapWH(result.x - 57, result.y - 33, 78, 50);
                Thread.sleep(500);
            }
            gamePublicFunction.closeWindow();
        }

        return state;
    }

    private boolean nineExperiment() throws Exception {
        boolean state = false;
        result = publicFunction.localFindPic(1132, 1, 1261, 154, "nineExperiment1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------nineExperiment1--->=" + result);
            gamePublicFunction.manualOrAutomatic("automatic");
            state = true;
        }
        result = gamePublicFunction.leave();
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------leave--->=" + result);
            state = true;
        }

        return state;
    }

    private boolean nineHero() throws Exception {
        boolean state = false;
        result = publicFunction.localFindPic(970, 552, 1098, 678, "attend.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------attend--->=" + result);
            publicFunction.rndTapWH(result.x, result.y, 28, 26);
            Thread.sleep(500);
        }

        result = gamePublicFunction.leave();
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------leave--->=" + result);
            state = true;
        }

        gamePublicFunction.manualOrAutomatic("automatic");
        gamePublicFunction.clickDetermine();
        return state;
    }

    //通天塔
    private boolean nineTTT() throws Exception {
        boolean state = false;
        result = publicFunction.localFindPic(460, 589, 678, 677, "attend.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------attend--->=" + result);
            publicFunction.rndTapWH(result.x, result.y, 28, 26);
            Thread.sleep(500);
        }
        gamePublicFunction.manualOrAutomatic("automatic");
        result = publicFunction.localFindPic(125, 14, 374, 81, "nineTTT1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------nineTTT1--->=" + result);
            gamePublicFunction.closeWindow();
        }
        result = gamePublicFunction.leave();
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------leave--->=" + result);
            state = true;
        }
        return state;

    }

    //活动按钮连续存在时间
    private boolean activityButtonTime(int second) throws Exception {
        //判断 活动按钮 在 second 秒内 是否一致存在，存在则返回true
        long time = System.currentTimeMillis() / 1000;
        long timex = 0;
        while (mFairy.condit()) {
            result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------activity--->=" + result);
                timex = System.currentTimeMillis() / 1000 - time;
                if (timex >= second) {
                    return true;
                }
                Thread.sleep(1000);
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 设置领土战驻扎的地方
     * <p>
     * 区域一 左上
     * 1 天兴村
     * 2 敦煌古城
     * 3 沙漠遗迹
     * 4 天阴村
     * 5 龙门村
     * 6 昆虚脉藏
     * 7 祁连山
     * 8 凤凰村
     * 9 伏牛山
     * 10 永乐镇
     * 11 平顶村
     * 12 襄阳
     * 13 漠北草原
     * 14 太行古径
     * 15 风雨村
     * <p>
     * 区域二 右上
     * 16 长白山
     * 17 映雪村
     * 18 药王谷
     * 19 杏花村
     * 20 古战场
     * 21 云中镇
     * 22 荐菊洞
     * 区域三 左下
     * 23 风陵渡
     * 24 剑门关
     * 25 大邑县
     * 26 夜郎废墟
     * 27 点苍山
     * 28 月牙村
     * 29 花木村
     * 30 唐家村
     * 31 灵泉村
     * 32 响水洞
     * 33 五岭村
     * 34 苗岭
     * 35 西陵村
     * 36 巴陵县
     * 37 锁云渊
     * 区域四 右下
     * 38 洞庭湖畔
     * 39 北海村
     * 40 龙泉村
     * 41 见性峰
     * 42 武夷山
     * 43 临安
     * 44 罗霄村
     * 45 忘忧镇
     * 46 雁荡山
     * 47 绍海村
     * 48 西山村
     */
    public void AttackCity_selectMap(int position) throws Exception {
        Random rand = new Random();
        result = publicFunction.localFindPic(6, 162, 82, 354, "territory2.png");
        if (result.sim > 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------territory--->=" + result);
        } else {
            LtLog.i(publicFunction.getLineInfo() + "------territory return--->=" + result);
            return;
        }
        result = publicFunction.localFindPic(954, 577, 1200, 691, "attend.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------attend--->=" + result);
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
        }
        result = publicFunction.localFindPic(6, 162, 82, 354, "territory2.png");
        if (result.sim > 0.8) {
            mFairy.tap(10, 10);
            Thread.sleep(1000);
        }
        position = position - 1;
        for (int i = 0; i < 1000; i++) {
            result = publicFunction.localFindPic(887, 348, 960, 431, "arrow3.png");
            if (result.sim > 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------arrow3--->=" + result);
                publicFunction.rndTapWH(result.x, result.y, 10, 10);
                Thread.sleep(1000);
            }
            if (position <= 14) {
                //左上
                result = publicFunction.localFindPic(185, 200, 257, 280, "territory_map1.png");
                LtLog.i(publicFunction.getLineInfo() + "------territory_map1--->=" + result);
                if (result.sim > 0.8) {
                    AttackCity_XY(position, result);
                    break;
                } else {
                    int x = rand.nextInt(320 - 260) + 260;
                    mFairy.touchDown(x, 184);
                    mFairy.touchMove(930, 577, 300);
                    mFairy.touchUp();
                }
            } else if (position <= 21) {
                //右上
                result = publicFunction.localFindPic(762, 533, 830, 610, "territory_map21.png");
                LtLog.i(publicFunction.getLineInfo() + "------territory_map21--->=" + result);
                if (result.sim > 0.8) {
                    AttackCity_XY(position, result);
                    break;
                } else {
                    int y = rand.nextInt(251 - 157) + 251;
                    mFairy.touchDown(875, y);
                    mFairy.touchMove(212, 593, 300);
                    mFairy.touchUp();
                }
            } else if (position <= 36) {
                //左下
                result = publicFunction.localFindPic(570, 513, 654, 590, "territory_map29.png");
                LtLog.i(publicFunction.getLineInfo() + "------territory_map29--->=" + result);
                if (result.sim > 0.8) {
                    AttackCity_XY(position, result);
                    break;
                } else {
                    mFairy.touchDown(202, 593);
                    mFairy.touchMove(879, 167, 300);
                    mFairy.touchUp();
                }
            } else if (position <= 47) {
                //右下
                result = publicFunction.localFindPic(836, 534, 904, 600, "territory_map45.png");
                LtLog.i(publicFunction.getLineInfo() + "------territory_map45--->=" + result);
                if (result.sim > 0.8) {
                    AttackCity_XY(position, result);
                    break;
                } else {
                    int y = rand.nextInt(628 - 569) + 569;
                    mFairy.touchDown(912, y);
                    mFairy.touchMove(295, 183, 300);
                    mFairy.touchUp();
                }
            }
            Thread.sleep(1000);
        }
        Thread.sleep(2000);
        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPic(445, 460, 838, 635, "stationed.png");
            LtLog.i(publicFunction.getLineInfo() + "------stationed--->=" + result);
            if (result.sim > 0.8) {
                publicFunction.rndTapWH(result.x, result.y, 10, 10);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(692, 422, 844, 483, "determine4.png");
            LtLog.i(publicFunction.getLineInfo() + "------determine4--->=" + result);
            if (result.sim > 0.8) {
                publicFunction.rndTapWH(result.x, result.y, 10, 10);
                Thread.sleep(2000);
                break;
            }
            Thread.sleep(1000);
        }
    }

    /**
     * 领土战 各个地图的相对位置
     */
    private void AttackCity_XY(int position, AtFairy2.OpencvResult res) throws Exception {
        List<int[]> XYlist = new ArrayList();
//        position=position;
        XYlist.add(new int[]{5, 5}); //1 天兴村
        XYlist.add(new int[]{162, 95}); //2 敦煌古城
        XYlist.add(new int[]{76, 173}); //3 沙漠遗迹
        XYlist.add(new int[]{3, 248}); //4 天阴村
        XYlist.add(new int[]{180, 249}); //5 龙门村
        XYlist.add(new int[]{69, 314});//6 昆虚脉藏
        XYlist.add(new int[]{229, 310});//7 祁连山
        XYlist.add(new int[]{402, 220});//8 凤凰村
        XYlist.add(new int[]{485, 272});//9 伏牛山
        XYlist.add(new int[]{509, 388});//10 永乐镇
        XYlist.add(new int[]{571, 305});//11 平顶村
        XYlist.add(new int[]{643, 402});//12 襄阳
        XYlist.add(new int[]{602, 83});//13 漠北草原
        XYlist.add(new int[]{641, 253});//14 太行古径
        XYlist.add(new int[]{689, 341});//15 风雨村

        XYlist.add(new int[]{117, -357});//16 长白山
        XYlist.add(new int[]{-11, -255});//17 映雪村
        XYlist.add(new int[]{-59, -166});//18 药王谷
        XYlist.add(new int[]{-86, -112});//19 杏花村
        XYlist.add(new int[]{-88, -39});//20 古战场
        XYlist.add(new int[]{0, 0});//21 云中镇
        XYlist.add(new int[]{-74, 99});//22 荐菊洞

        XYlist.add(new int[]{-42, -408});//23 风陵渡
        XYlist.add(new int[]{-181, -344});//24 剑门关
        XYlist.add(new int[]{-48, -296});//25 大邑县
        XYlist.add(new int[]{-97, -195});//26 夜郎废墟
        XYlist.add(new int[]{-97, -32});//27 点苍山
        XYlist.add(new int[]{-31, -83});//28 月牙村
        XYlist.add(new int[]{5, 5});//29 花木村
        XYlist.add(new int[]{25, -157});//30 唐家村
        XYlist.add(new int[]{25, -223});//31 灵泉村
        XYlist.add(new int[]{70, -353});//32 响水洞
        XYlist.add(new int[]{107, -267});//33 五岭村
        XYlist.add(new int[]{151, -158});//34 苗岭
        XYlist.add(new int[]{207, -362});//35 西陵村
        XYlist.add(new int[]{213, -234});//36 巴陵县
        XYlist.add(new int[]{213, -20});//37 锁云渊

        XYlist.add(new int[]{-268, -302});//38 洞庭湖畔
        XYlist.add(new int[]{-266, 40});//39 北海村
        XYlist.add(new int[]{-167, -257});//40 龙泉村
        XYlist.add(new int[]{-153, -124});// 41 见性峰
        XYlist.add(new int[]{-85, -63});//42 武夷山
        XYlist.add(new int[]{-44, -369});//43 临安
        XYlist.add(new int[]{-48, -314});//44 罗霄村
        XYlist.add(new int[]{24, -2});//45 忘忧镇
        XYlist.add(new int[]{24, -187});//46 雁荡山
        XYlist.add(new int[]{34, -265});//47 绍海村
        XYlist.add(new int[]{98, -343});//48 西山村

        int x = XYlist.get(position)[0];
        int y = XYlist.get(position)[1];
        mFairy.tap(res.x + x, res.y + y);
    }

    /**
     * 舞动江湖
     *
     * @throws Exception
     */
    public void dance_activity() throws Exception {

        while (mFairy.condit()) {
            result = publicFunction.localFindPic(939, 466, 1089, 622, "danceArrow.png");
            LtLog.i(publicFunction.getLineInfo() + "------danceArrow--->=" + result);
            if (result.sim > 0.8) {
                dance_png.add(functionClass.getAssetImageFileMat("up_Arrow.png"));
                dance_png.add(functionClass.getAssetImageFileMat("down_Arrow.png"));
                dance_png.add(functionClass.getAssetImageFileMat("left_Arrow.png"));
                dance_png.add(functionClass.getAssetImageFileMat("right_Arrow.png"));
                dance();
                dance_png.clear();
            }
            Thread.sleep(100);
        }
    }

    /**
     * 跳舞
     *
     * @throws Exception
     */
    public void dance() throws Exception {
        //13:30  16:00 20:00
        AtFairy2.OpencvResult result;
        int num;
        while (mFairy.condit()) {
            LtLog.i(publicFunction.getLineInfo() + "------dance--->=");
            for (int i = 0; i < 100; i++) {
                result = publicFunction.localFindPic(35, 500, 143, 624, "dance_mark.png");
                num = functionClass.getColorNunber(200, 559, 500, 2, "203,117,0", 0.85);
                //LtLog.i(publicFunction.getLineInfo() + "------dance_mark--->=" + result, ",num=" + num);
                if (result.sim > 0.8 && num < 2) {
                    Thread.sleep(90);
                    getDanceAction();
                }
            }
            /*result = publicFunction.localFindPic(939, 466, 1089, 622, "danceArrow.png");
            if (result.sim < 0.8 || TaskMain.mtaskState == false) {
                LtLog.i(publicFunction.getLineInfo() + "------danceArrow < 0 --->=" + result);
                break;
            }*/
            Thread.sleep(10);
        }
    }

    /**
     * 获取跳舞动作,并执行
     */
    public void getDanceAction() throws Exception {
        TreeMap<Integer, Integer> directionList = new TreeMap<>();
        Mat mat = functionClass.getScreenMat(122, 527, 715, 65);
        LtLog.i(publicFunction.getLineInfo() + "------mat--->=" + mat);

        for (int j = 0; j < dance_png.size(); j++) {

            List<com.example.publicfunctionlibrary.FunctionClass.ResultValue> resultValList = null;

            try {
                resultValList = comparisonMat(mat, dance_png.get(j), functionClass.RGB, 0.8f);
            } catch (Exception e) {
                LtLog.i(publicFunction.getLineInfo() + e.getMessage());
                return;
            }

            LtLog.i(publicFunction.getLineInfo() + "------resultValList--->=" + resultValList);
            for (int i = 0; i < resultValList.size(); i++) {
                directionList.put(resultValList.get(i).x, j);
            }
        }
        LtLog.i(publicFunction.getLineInfo() + "------directionList--->=" + directionList);
        int value;
        for (Integer key : directionList.keySet()) {
            value = directionList.get(key);
//            LtLog.i(publicFunction.getLineInfo() + "------val--->=" + value);
            switch (value) {
                case dance_UP:
                    publicFunction.rndTap(1086, 456, 1110, 475);
                    break;
                case dance_DOWN:
                    publicFunction.rndTap(1088, 609, 1115, 635);
                    break;
                case dance_LEFT:
                    publicFunction.rndTap(1010, 531, 1041, 561);
                    break;
                case dance_RIGHT:
                    publicFunction.rndTap(1160, 528, 1200, 560);
                    break;
            }

            Thread.sleep(50);
        }
        directionList.clear();
        mat.release();
    }

    public List<FunctionClass.ResultValue> comparisonMat(Mat bigMat, Mat small, String model, float sim) throws Exception {
        Mat dst = new Mat();
        Mat mask = new Mat(small.height(), small.width(), small.type());
        List<FunctionClass.ResultValue> list = new ArrayList<>();
        float maxSim = sim;

        int errEqual = 0;

        for (int i = 0; i < 50; i++) {

            if (model.equals("HSV")) {
                Imgproc.cvtColor(bigMat, bigMat, Imgproc.COLOR_RGB2HSV);
                Imgproc.cvtColor(small, small, Imgproc.COLOR_RGB2HSV);
            } else if (model.equals("HLS")) {
                Imgproc.cvtColor(bigMat, bigMat, Imgproc.COLOR_RGB2HLS);
                Imgproc.cvtColor(small, small, Imgproc.COLOR_RGB2HLS);
            }

            Imgproc.matchTemplate(bigMat, small, dst, Imgproc.TM_CCOEFF_NORMED);

            Core.MinMaxLocResult mmr;

            mmr = Core.minMaxLoc(dst);

           /* LtLog.i("======================mmr.maxVal= " + mmr.maxVal);
            LtLog.i("======================maxSim= " + maxSim);*/

            if (mmr.maxVal >= maxSim) {
                FunctionClass.ResultValue result = functionClass.new ResultValue();

                result.sim = (float) mmr.maxVal;
                result.x = (int) mmr.maxLoc.x;
                result.y = (int) mmr.maxLoc.y;
                list.add(result);

                if (errEqual == result.x) {
                    throw new Exception("comparisonMat err");
                }

                errEqual = result.x;

                Mat imgROI = new Mat(bigMat, new Rect(result.x, result.y, small.width(), small.height()));

               /* try {
                    LtLog.e(mFairy.getLineInfo("testhjn  Rect:" + (result.x + 361) + "," + (result.y + 626) + "," + small.width() + "," + small.height()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
*/
                mask.copyTo(imgROI);
                imgROI.release();
            } else {
                break;
            }
        }
        dst.release();
        mask.release();
        return list;
    }

    /**
     * 侠客岛上  回到安全区 然后 离开
     *
     * @throws Exception
     */
    public void GoSecurityXiakeIsland() throws Exception {
        AtFairy2.OpencvResult result, result1;
        result = publicFunction.localFindPic(1164, 0, 1279, 36, "xiakeIsland.png");
        result1 = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
        LtLog.i(publicFunction.getLineInfo() + "------xiakeIsland--->=" + result + ", activity : " + result1);
        if (result.sim >= 0.8 && result1.sim < 0.8) {
            //在侠客岛中,需要回到安全区,然后离开。
        } else {
            //不在侠客岛
            return;
        }

        gamePublicFunction.goSecurity();
        while (mFairy.condit()) {
            result = gamePublicFunction.leave();
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------leave=" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(1000);
            }
            gamePublicFunction.clickDetermine();
            Thread.sleep(2000);
            result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png"+ "|" + "activity3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------activity--->=" + result);
                break;
            }
        }


    }

}





























