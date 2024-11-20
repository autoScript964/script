package com.script.fairy;

import android.graphics.Bitmap;

import com.example.publicfunctionlibrary.FunctionClass;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018-10-18.
 */

public class SingleTask {

    private AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    private GamePublicFunction gamePublicFunction;
    private FindResult findResult;
    private Other other;
    private String currentTask;
    private boolean visitTask2 = false;
    private int feather_count = 0;//记录更换鸡毛的次数
    boolean feather_start = false;// 鸡毛信，是否要上交任务
    public FunctionClass functionClass;

    public SingleTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(ypFairy);
        gamePublicFunction = new GamePublicFunction(ypFairy);
        functionClass = new FunctionClass(ypFairy, ypFairy.getContext());
        other = new Other(mFairy);
    }

    public int everydayTask() throws Exception {
        AtFairy2.OpencvResult result;
        MatTime matTime = new MatTime(mFairy);
        long sleepTime = 0;

        initialSet();//起始设置

        other.OtherFunction();//其他小任务功能


        if (TaskMain.taskList.indexOf("ZXY_everday") > -1) {
            other.ZXY_everday();
        }
        if (TaskMain.taskList.indexOf("quartermaster") > -1) {
            receiveQuartermasterTask(); //接取运镖 不去护送
        }


        gamePublicFunction.openActivity(gamePublicFunction.openActivity_dailt);
        while (mFairy.condit()) {
            sleepTime = matTime.mMatTime(1119, 54, 71, 15, 0.8);
            LtLog.i(publicFunction.getLineInfo() + "------sleepTime->" + sleepTime+"    当前任务："+TaskMain.taskList.get(0));
            if (sleepTime >= 30) {
                gamePublicFunction.closeWindow();
                gamePublicFunction.openActivity(gamePublicFunction.openActivity_dailt);
                matTime.resetTime();
            }


            result = publicFunction.localFindPic(121, 0, 249, 115, "dailt.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------dailt->" + result);
                if (TaskMain.taskList.size() > 0) {
                    TaskMain.taskList = gamePublicFunction.lookupTask(TaskMain.taskList, gamePublicFunction.openActivity_dailt);//查看任务
                    if (gamePublicFunction.taskStart()) {
                        // 在任务完成的情况下，并且勾选了运镖，接取运镖任务，不去护送
                        if (TaskMain.taskList.indexOf("quartermaster") > -1) {
                            receiveQuartermasterTask();
                        }
                    }
                    Thread.sleep(3000);
                }
            }
            if (TaskMain.taskList.size() == 0) {
                LtLog.i(publicFunction.getLineInfo() + "------任务完成->");
                return 99;
            }
            switch (TaskMain.taskList.get(0)) {
                case "pig":
                    pigTask();
                    break;
                case "dragon":
                    dragonTask();
                    break;
                case "feather":
                    featherTask();
                    break;
                case "learn":
                    learnTask();
                    break;
                case "huangbang":
                    huangbangTask();
                    break;
                case "crusade":
                    crusadeTask();
                    break;
                case "hot_spring":
                    hot_springTask();
                    break;
                case "visit":
                    visitTask();
                    break;
                case "suppressBandits":
                    suppressBanditsTask();
                    break;
                case "quartermaster":
                    quartermasterTask();
                    break;
                case "travel":
                    travelTask();
                    break;
                case "anchor":
                    anchorTask();
                    break;
            }
            gamePublicFunction.Resurrection();//复活
            if (TaskMain.taskList.get(0).equals("pig") || TaskMain.taskList.get(0).equals("dragon") || TaskMain.taskList.get(0).equals("feather") || TaskMain.taskList.get(0).equals("learn")) {
                gamePublicFunction.car();//车夫传送
                acceptAndComplete();//接受 和 完成
            }
            Thread.sleep(1000);
        }
        return 0;
    }

    //主播陪你玩
    private void anchorTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(14, 261, 185, 433, "anchor1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------anchor1->" + result);
            publicFunction.rndTapWH(result.x, result.y, 29, 10);
            Thread.sleep(1000);
            gamePublicFunction.openActivity(gamePublicFunction.openActivity_other);

        }
    }

    //周游列国
    private void travelTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(864, 487, 993, 616, "accept2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------accept2->" + result);
            publicFunction.rndTapWH(result.x, result.y, 29, 29);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(863, 486, 994, 618, "goto2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------goto2->" + result);
            publicFunction.rndTapWH(result.x, result.y, 31, 32);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(834, 534, 991, 661, "receive2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------receive2->" + result);
            publicFunction.rndTapWH(result.x, result.y, 57, 27);
            Thread.sleep(500);
        }


        result = publicFunction.localFindPic(11, 251, 73, 415, "travel1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------travel1->" + result);
            gamePublicFunction.openActivity(gamePublicFunction.openActivity_other);
        }


    }

    //剿匪
    private void suppressBanditsTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(476, 292, 806, 405, "delivery.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------delivery->" + result);
            gamePublicFunction.clickDetermine(710, 442, 851, 499);
            Thread.sleep(1000);
        }
        gamePublicFunction.automaticCombat(1);//开启自动
    }

    //接取运镖 不去护送
    private void receiveQuartermasterTask() throws Exception {
        AtFairy2.OpencvResult result;
        List<String> task = new ArrayList<String>(Arrays.asList("quartermaster"));
        for (int i = 0; i < 3; i++) {
            gamePublicFunction.openActivity(gamePublicFunction.openActivity_dailt);
            task = gamePublicFunction.lookupTask(task, gamePublicFunction.openActivity_dailt);
            if (task.size() == 0) {
                LtLog.i(publicFunction.getLineInfo() + "------quartermaster over->");
                TaskMain.taskList.remove("quartermaster");
            }
            if (gamePublicFunction.taskStart() || task.size() == 0) {
                break;
            }
            result = publicFunction.localFindPic(769, 571, 901, 701, "quartermasterAttend.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------quartermasterAttend->" + result);
                publicFunction.rndTapWH(result.x, result.y, 32, 30);
                Thread.sleep(1000);

            }
            result = publicFunction.localFindPic(438, 290, 610, 380, "notMoney.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------notMoney->" + result);
                gamePublicFunction.clickDetermine(692, 430, 878, 514);
                Thread.sleep(1000);

            }
            result = publicFunction.localFindPic(430, 303, 867, 387, "insure.png");
            LtLog.i(publicFunction.getLineInfo() + "------insure->" + result);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------insure->" + result);
                gamePublicFunction.clickDetermine(698, 438, 882, 514);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(452, 283, 638, 405, "quartermaster1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------quartermaster1->" + result);
                gamePublicFunction.clickDetermine(705, 445, 859, 494);
                break;
            }
            Thread.sleep(1000);
        }

        for (int i = 0; i < 3; i++) {
            gamePublicFunction.closeWindow();
            result = publicFunction.localFindPic(615, 282, 817, 390, "insure.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------insure->" + result);
                gamePublicFunction.clickDetermine(698, 438, 882, 514);
            }
        }
        if (TaskMain.biaoche == 0) {
            LtLog.i(publicFunction.getLineInfo() + "------biaoche = 0 ->");
            return;
        }
        LtLog.i(publicFunction.getLineInfo() + "------biaoche = ->" + TaskMain.biaoche);
        for (int j = 0; j < 52; j++) { //最多换镖50 次
            for (int i = 1; i <= 5; i++) {
                result = publicFunction.localFindPic(673, 512, 801, 643, "biao" + Integer.toString(i) + ".png");
                //查看当前镖车是什么颜色
                if (result.sim >= 0.85) {
                    LtLog.i(publicFunction.getLineInfo() + "------biao  " + i + "=" + result);
                    if (i >= TaskMain.biaoche) {
                        //根据 i 的值与 mFairy.biaoche（用户勾选的值）比对，如果i的值大于用户勾选值，跳出循环
                        LtLog.i(publicFunction.getLineInfo() + "------biao" + i + ">= " + TaskMain.biaoche);
                        return;
                    } else {
                        LtLog.i(publicFunction.getLineInfo() + "------点换镖 ->");
                        publicFunction.rndTap(843, 566, 867, 585);
                        Thread.sleep(5000);
                        break;
                    }
                }


                Thread.sleep(100);
            }

            result = publicFunction.localFindPic(430, 303, 867, 387, "insure.png");
            LtLog.i(publicFunction.getLineInfo() + "------insure->" + result);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------insure->" + result);
                gamePublicFunction.clickDetermine(698, 438, 882, 514);
                Thread.sleep(1000);
            }

            Thread.sleep(100);
        }
    }

    //边境军需  运镖
    private void quartermasterTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(769, 571, 901, 701, "quartermasterAttend.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------quartermasterAttend->" + result);
            publicFunction.rndTapWH(result.x, result.y, 32, 30);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(452, 283, 638, 405, "quartermaster1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------quartermaster1->" + result);
            gamePublicFunction.clickDetermine(705, 445, 859, 494);
        }
        result = publicFunction.localFindPic(749, 414, 956, 534, "details.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------details->" + result);
            gamePublicFunction.clickDetermine(654, 518, 1041, 703);
        }


    }

    //中立区参拜
    private void visitTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(597, 476, 688, 564, "dialogue.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------dialogue->" + result);
            for (int i = 0; i < 3; i++) {
                publicFunction.rndTapWH(result.x, result.y, 30, 18);
                Thread.sleep(2000);
                result = publicFunction.localFindPic(558, 442, 684, 570, "visit1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------visit1->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 26, 28);
                    Thread.sleep(500);
                }
            }
        }

        visitTask2(155, 256);
        visitTask2(183, 256);


    }

    //参拜其他雕像
    private void visitTask2(int x1, int y1) throws Exception {
        if (visitTask2) {
            //如果 visitTask2 为真，则已经参拜过其他雕像
            return;
        }
        AtFairy2.OpencvResult result;
        int gmx = x1;
        int gmy = y1;
        double x = 0, y = 0;
        result = publicFunction.localFindPic(1085, 0, 1270, 79, "LuoYang2.png");
        if (result.sim < 0.8) {
            return;
        }
        LtLog.i(publicFunction.getLineInfo() + "------LuoYang2->" + result);

        x = gmx * 1.7378 + gmy * 0.0039 + 147.2726;
        y = gmx * -0.0032 + gmy * -1.7357 + 633.7744;

        for (int i = 0; i < 3; i++) {
            gamePublicFunction.openMap("current");
            Thread.sleep(500);
            mFairy.tap((int) x, (int) y);
            Thread.sleep(5000);
            gamePublicFunction.closeWindow();
            Thread.sleep(5000);
            for (int j = 0; j < 20; j++) {
                result = publicFunction.localFindPic(597, 476, 688, 564, "dialogue.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------dialogue->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 30, 18);
                    Thread.sleep(1000);
                }
                result = publicFunction.localFindPic(928, 571, 1248, 711, "reward4.png|reward5.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------reward4->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 30, 18);
                    Thread.sleep(1000);
                    if (x1 == 183) {
                        visitTask2 = true;
                        // x1= 183 第二次参拜完成后 打开活动，引导到国王参拜
                        gamePublicFunction.openActivity(gamePublicFunction.openActivity_dailt);
                    }
                    return;
                }
                Thread.sleep(1000);
            }
        }


    }

    //御龙温泉
    private void hot_springTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(575, 285, 849, 424, "hot_spring1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------hot_spring1->" + result);
            publicFunction.rndTap(752, 456, 821, 488);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(597, 269, 832, 386, "hot_spring2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------hot_spring2->" + result);
            publicFunction.rndTap(746, 457, 819, 487);
            Thread.sleep(500);
        }


    }

    //讨伐
    private void crusadeTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(53, 253, 206, 390, "crusade2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------crusade2->" + result);
            publicFunction.rndTapWH(result.x, result.y, 87, 18);
            Thread.sleep(2000);
        }
        result = publicFunction.localFindPic(143, 0, 300, 115, "crusade1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------crusade1->" + result);

        } else {
            return;
        }

        for (int j = 0; j < 20; j++) {
            result = publicFunction.localFindPic(935, 136, 1083, 259, "receive1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------receive1->" + result);
                for (int i = 0; i < 10; i++) {
                    result = publicFunction.localFindPic(935, 136, 1083, 259, "receive1.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------receive1->" + result);
                        publicFunction.rndTapWH(result.x, result.y, 48, 23);
                        Thread.sleep(1500);
                    }
                }
            }
            result = publicFunction.localFindPic(913, 153, 1134, 562, "use.png");//使用讨伐令
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------use->" + result);
                publicFunction.rndTapWH(result.x, result.y, 51, 25);
                Thread.sleep(1000);
                receiveCrusade();//使用讨伐令策略
            }
        }
        TaskMain.taskList.remove(0);


//                result = publicFunction.localFindPic(143, 0, 300, 115, "crusade1.png");
//        if (result.sim >= 0.8) {
//            LtLog.i(publicFunction.getLineInfo() + "------crusade1->" + result);
//            result = publicFunction.localFindPic(540, 594, 692, 716, "goto1.png");
//            if (result.sim >= 0.8) {
//                LtLog.i(publicFunction.getLineInfo() + "------goto1->" + result);
//                publicFunction.rndTapWH(result.x, result.y, 52, 22);
//                Thread.sleep(2000);
//                for (int i = 0; i < 3; i++) {
//                    gamePublicFunction.closeWindow(1018, 3, 1188, 144);
//                    Thread.sleep(1000);
//                }
//            }
//        }
    }

    //接取讨伐令
    private void receiveCrusade() throws Exception {
        AtFairy2.OpencvResult result;
        int index = 0; //点击使用讨伐令的次数记录
        for (int i = 0; i < 6; i++) {
            result = publicFunction.localFindPic(785, 152 + (i * 97), 937, 252 + (i * 97), "use1.png");//从紫色开始查找是否有使用
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------use1->" + result);
                for (int j = 0; j < 5; j++) {
                    result = publicFunction.localFindPic(785, 152 + (i * 97), 937, 252 + (i * 97), "use1.png"); //有使用则点击一次,如果一直有，则接取5次，没有的话，接取别的品质的讨伐令
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------use1->" + result);
                        index = index + 1;
                        publicFunction.rndTapWH(result.x, result.y, 51, 25);
                        Thread.sleep(500);
                    }
                    Thread.sleep(1000);
                    if (index >= 5) {
                        break;
                    }
                }
            }
            if (index >= 5) {
                break;
            }
        }
        LtLog.i(publicFunction.getLineInfo() + "------close  select  Crusade->");
        while (mFairy.condit()) {
            result = publicFunction.localFindPic(516, 56, 711, 183, "selectCrusade.png");
            if (result.sim >= 0.8) {
                publicFunction.rndTapWH(904, 106, 926, 133);
                Thread.sleep(500);
            } else {
                gamePublicFunction.closeWindow();
                break;
            }
            Thread.sleep(1000);
        }

    }

    //皇榜
    private void huangbangTask() throws Exception {


        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(827, 475, 984, 603, "accept1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------accept1->" + result);
            if (huangbangQuality()) {
                //点击接受
                publicFunction.rndTapWH(result.x, result.y, 57, 28);
                Thread.sleep(500);
            } else {
                //品质未达到设置的要求，点击一键到紫
                publicFunction.rndTapWH(result.x, result.y + 82, 57, 28);
                Thread.sleep(1500);
            }
        }
        result = publicFunction.localFindPic(426, 278, 710, 400, "error10.png");
        if (result.sim >= 0.8) {
            //金券不足，点击取消,然后点击接受
            LtLog.i(publicFunction.getLineInfo() + "------error10->" + result);
            publicFunction.rndTap(468, 459, 519, 481);
            Thread.sleep(1000);
            for (int i = 0; i < 10; i++) {
                result = publicFunction.localFindPic(827, 475, 984, 603, "accept1.png");
                LtLog.i(publicFunction.getLineInfo() + "------accept1->" + result);
                if (result.sim >= 0.8) {
                    publicFunction.rndTapWH(result.x, result.y, 57, 28);
                    Thread.sleep(500);
                    break;
                }
                Thread.sleep(200);
            }
        }


        result = publicFunction.localFindPic(4, 252, 114, 434, "huangbang_over.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------huangbang_over->" + result);
            publicFunction.rndTapWH(result.x, result.y, 15, 10);
            Thread.sleep(500);


        }

        result = publicFunction.localFindPic(936, 474, 1103, 604, "goto.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------goto->" + result);
            publicFunction.rndTapWH(result.x, result.y, 67, 30);
            Thread.sleep(2000);
            for (int i = 0; i < 3; i++) {
                gamePublicFunction.closeWindow(1018, 17, 1216, 146);
            }
        }


        result = publicFunction.localFindPic(1005, 647, 1179, 715, "complete.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------complete->" + result);
            publicFunction.rndTapWH(result.x, result.y, 30, 22);
            Thread.sleep(500);
        }


        result = publicFunction.localFindPic(828, 519, 988, 646, "receive.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------receive->" + result);
            publicFunction.rndTapWH(result.x, result.y, 60, 27);
            Thread.sleep(500);
        }
    }

    //检测皇榜品质
    private boolean huangbangQuality() {
        AtFairy2.OpencvResult result;
        boolean quality = false;

        for (int i = 1; i <= 5; i++) {
            result = publicFunction.localFindPic(279, 92, 442, 200, "quality" + Integer.toString(i) + ".png");
            LtLog.i(publicFunction.getLineInfo() + "--------------quality" + Integer.toString(i) + "->" + result + ",mFairy.select_huangbang=" + TaskMain.select_huangbang);
            if (result.sim >= 0.8) {
                //如果当前品质 i 大于 设置的品质 设置quality 为真
                if (TaskMain.select_huangbang <= i) {
                    quality = true;
                }
                break;
            }
        }
        LtLog.i(publicFunction.getLineInfo() + "--------------quality=" + quality);
        return quality;
    }

    //护国寺
    private void learnTask() throws Exception {
        AtFairy2.OpencvResult result;

        result = publicFunction.localFindPic(552, 40, 686, 174, "car.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------car->" + result);
            //护国寺取经 随机选择去一个国家 ,避免有国家经书没了
            int rnd = ((int) (Math.random() * (3 + 1)) * 108);
            publicFunction.rndTap(506, 198 + rnd, 765, 234 + rnd);
            Thread.sleep(1000);
        }

        findResult = mFairy.findPic("renwu.png");
        mFairy.onTap(0.8f,findResult,1099,53,1108,63,"关掉任务界面",2000);

        result = publicFunction.localFindPic(6, 245, 260, 434, "learn1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------learn1->" + result);
            publicFunction.rndTapWH(result.x, result.y, 61, 21);
            Thread.sleep(2000);
        } else {
            //如果没有经书
            result = publicFunction.localFindPic(1098, 3, 1229, 43, "royalCity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------car->" + result);
                gamePublicFunction.openMap("current");
                result = publicFunction.localFindPic(961, 606, 1167, 720, "nationalGeography.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------nationalGeography->" + result);
                    publicFunction.rndTap(469, 385, 470, 386);
                    gamePublicFunction.closeWindow(1018, 17, 1216, 146);
                    Thread.sleep(10000);
                }
            }
        }


        findResult = mFairy.findPic(506,258,889,404,"jingshu.png");
        if(findResult.sim>0.8f){
            mFairy.onTap(628,463,658,476,"已经是最高品质了",3000);

            mFairy.onTap(51,225,72,236,"",2000);

            return;
        }else{
            AtFairy2.OpencvResult result1 = publicFunction.localFindPic(599, 423, 677, 468, "exchangeBooks3.png"+"|"+"exchangeBooks1.png"+"|"+"exchangeBooks.png");
            AtFairy2.OpencvResult result2 = publicFunction.localFindPic(599, 423, 677, 468, "exchangeBooks2-1.png|exchangeBooks2.png|exchangeBooks2-2.png");
            result = publicFunction.localFindPic(532, 457, 771, 588, "hand.png");
            FunctionClass.ResultValue resultValue = functionClass.binaryzationFindPic(599, 423, 677, 468, new int[]{70, 130, 180}, new int[]{110, 185, 235}, "exchangeBooks2-2.png");
            LtLog.i(publicFunction.getLineInfo() + "---------------hand->" + result + ",exchangeBooks=" + result1 + ",exchangeBooks2:" + result2 + ",resultValue=" + resultValue);

            if (result.sim >= 0.7 && (result2.sim > 0.7 || resultValue.sim >= 0.7)) {
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(100);
            } else {
                if (result.sim >= 0.8 && result1.sim >= 0.75) {
                    publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                    Thread.sleep(100);
                    exchangeBooks();
                }

            }
        }




    }

    //护国寺换经书
    public void exchangeBooks() throws Exception {
        Mat mat;
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 600; i++) {
            result = publicFunction.localFindPic(538, 237, 753, 327, "acquisitionBook.png");
            LtLog.i(publicFunction.getLineInfo() + "------acquisitionBook->" + result);
            if (result.sim >= 0.75) {
                //先把当前图片接取下来，因为刷新很快
                mat = mFairy.getScreenMat(654, 447, 52, 43, 1, 0, 0, 1);
                for (int j = 1; j <= 5; j++) {
                    Bitmap map = publicFunction.getImageFromAssetsFile("book" + Integer.toString(j) + ".png");
                    Mat img = new Mat();
                    org.opencv.android.Utils.bitmapToMat(map, img);
                    Imgproc.cvtColor(img, img, Imgproc.COLOR_BGRA2RGB);
                    //用当前图与 asset 里的图做对比，判断出当前拿到书的品质
                    boolean q = judgeMatAndMatChange(0.8d, img, mat);
                    LtLog.i(publicFunction.getLineInfo() + "------q" + j + "->" + q);
                    if (q) {
                        if (j >= TaskMain.select_book) {
                            return;
                        }
                        Thread.sleep(2000);
                        break;
                    }
                }
            }


            findResult = mFairy.findPic(506,258,889,404,"jingshu.png");
            if(findResult.sim>0.8f){
                mFairy.onTap(628,463,658,476,"已经是最高品质了",3000);
                mFairy.onTap(51,225,72,236,"",2000);
                return;
            }

            result = publicFunction.localFindPic(532, 457, 771, 588, "hand.png");
            LtLog.i(publicFunction.getLineInfo() + "------hand->" + result);
            if (result.sim >= 0.8) {
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(100);
            }
            Thread.sleep(10);
        }
    }

    private boolean judgeMatAndMatChange(double sim, Mat mat, Mat tempMat) throws Exception {
        //判断两个矩阵的相似度大于 sim 则返回 true;
        boolean state = false;
        Mat dstMat = new Mat();
        Imgproc.matchTemplate(mat, tempMat, dstMat, Imgproc.TM_CCOEFF_NORMED);
        Core.MinMaxLocResult mmr;
        mmr = Core.minMaxLoc(dstMat);
        if (mmr.maxVal >= sim) {
            state = true;
        }
        dstMat.release();
        return state;
    }

    //捣龙脉
    private void dragonTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(1006, 619, 1180, 720, "dragon1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------dragon1->" + result);
            publicFunction.rndTapWH(result.x, result.y, 74, 22);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(64, 254, 147, 448, "dragon2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------dragon2->" + result);
            publicFunction.rndTapWH(result.x, result.y, 10, 10);
            Thread.sleep(10000);
        }


        gamePublicFunction.hand();
    }

    //鸡毛信
    private void featherTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(1006, 619, 1180, 720, "feather1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------feather1->" + result);
            publicFunction.rndTapWH(result.x, result.y, 74, 22);
            Thread.sleep(5000);
        }
        result = publicFunction.localFindPic(504, 278, 786, 420, "violet.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------violet->" + result);
            publicFunction.rndTap(608, 459, 688, 487);//点确认
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(532, 457, 771, 588, "hand.png");
        LtLog.i(publicFunction.getLineInfo() + "------hand->" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------hand->" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(3000);
            feather_start = changeFeather();
        }
        result = publicFunction.localFindPic(62, 249, 174, 316, "feather2.png");
        if (result.sim >= 0.8 && feather_start) {
            LtLog.i(publicFunction.getLineInfo() + "------feather2->" + result);
            publicFunction.rndTapWH(result.x, result.y, 20, 10);
            Thread.sleep(3500);
        }
        //gamePublicFunction.hand();
    }

    //换鸡毛
    public boolean changeFeather() throws Exception {
        // 返回true的4种状态，1鸡毛信的品质达到要求 2 没有鸡毛令了 3 换鸡毛次数达到上限,4 鸡毛已经是紫色
        AtFairy2.OpencvResult result;
        boolean start = false;
        for (int i = 0; i < 25; i++) {
            for (int j = 1; j <= 5; j++) {
                //遍历鸡毛的品质

                result = publicFunction.localFindPic(595, 115, 688, 303, "feather_" + Integer.toString(j) + ".png");
                LtLog.i(publicFunction.getLineInfo() + "-----------feather_" + Integer.toString(j) + "->" + result + ", mFairy.select_feather=" + TaskMain.select_feather);
                if (result.sim >= 0.8) {
                    if (j >= TaskMain.select_feather) {
                        return true;
                    }
                    break;
                }
            }
            if (feather_count >= 20) {
                return true;
            }
            result = publicFunction.localFindPic(532, 457, 771, 588, "hand.png");
            LtLog.i(publicFunction.getLineInfo() + "------hand->" + result + ",feather_count=" + feather_count);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------hand->" + result);
                feather_count = feather_count + 1;
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(5000);
            }
            result = publicFunction.localFindPic(501, 53, 775, 136, "featherNO.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------featherNO->" + result);
                //没有鸡毛令了，退出
                for (int j = 0; j < 3; j++) {
                    gamePublicFunction.closeWindow();
                }
                return true;
            }

            result = publicFunction.localFindPic(593, 285, 722, 376, "violet1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------violet1->" + result);
                publicFunction.rndTap(601, 464, 660, 483);
                Thread.sleep(1000);
                return true;
            }


            Thread.sleep(1000);
        }
        return start;
    }

    private void pigTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(1006, 619, 1180, 720, "pig1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------pig1->" + result);
            publicFunction.rndTapWH(result.x, result.y, 74, 22);
            Thread.sleep(500);
        }

    }

    private void acceptAndComplete() throws Exception {
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

    }


    private void initialSet() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "------initialSet->");
        AtFairy2.OpencvResult result;
        for (int i = 0; i < 3; i++) {
            result = publicFunction.localFindPic(1181, 133, 1280, 246, "leave.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------leave->" + result);
                publicFunction.rndTapWH(result.x, result.y, 10, 10);
                Thread.sleep(500);
            }
            gamePublicFunction.clickDetermine(683, 436, 871, 506);
            Thread.sleep(1000);
        }
    }

    public int battlefrontTask() throws Exception {
        AtFairy2.OpencvResult result;
        if (TaskMain.taskList.size() == 0) {
            LtLog.i(publicFunction.getLineInfo() + "------不在任务时间段->");
            return 99;
        }
        MatTime matTime = new MatTime(mFairy);
        long sleepTime = 0;
        gamePublicFunction.openActivity(gamePublicFunction.openActivity_battlefront);
        while (mFairy.condit()) {
            sleepTime = matTime.mMatTime(1119, 54, 71, 15, 0.9);
            LtLog.i(publicFunction.getLineInfo() + "------sleepTime->" + sleepTime);
            if (sleepTime >= 30) {
                gamePublicFunction.openActivity(gamePublicFunction.openActivity_dailt);
                matTime.resetTime();
            }
            result = publicFunction.localFindPic(121, 0, 249, 115, "dailt.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------dailt->" + result);
                if (TaskMain.taskList.size() > 0) {
                    TaskMain.taskList = gamePublicFunction.lookupTask(TaskMain.taskList, gamePublicFunction.openActivity_battlefront);//查看任务
                    Thread.sleep(3000);
                }


            }
            if (TaskMain.taskList.size() == 0) {
                LtLog.i(publicFunction.getLineInfo() + "------任务完成->");
                return 99;
            }
            switch (TaskMain.taskList.get(0)) {
                case "guandu":
                    guanduTask();
                    break;
                case "hero_battlefield":
                    hero_battlefieldTask();
                    break;
                case "loong":
                    loong();
                    break;

            }
            Thread.sleep(1000);
        }

        return 0;
    }

    private void hero_battlefieldTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(916, 529, 1073, 656, "start.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------start->" + result);
            publicFunction.rndTapWH(result.x, result.y, 57, 27);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(544, 39, 990, 127, "HP.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------HP->" + result);
            for (int i = 0; i < 3; i++) {
                publicFunction.rndTapWH(result.x + 10, result.y - 20, 10, 11);
                Thread.sleep(500);
            }
            for (int i = 0; i < 15; i++) {
                result = publicFunction.localFindPic(308, 283, 969, 412, "Resurrection4.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------Resurrection4->" + result);
                    break;
                }
                Thread.sleep(1000);
            }


        }
        result = publicFunction.localFindPic(712, 371, 903, 461, "free.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------free->" + result);
            publicFunction.rndTap(759, 326, 857, 359);
            Thread.sleep(4000);
        } else {

            result = publicFunction.localFindPic(308, 283, 969, 412, "Resurrection4.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------Resurrection4->" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
                Thread.sleep(1000);
            }

        }
        result = publicFunction.localFindPic(480, 610, 609, 720, "leave1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------leave1->" + result);
            int x = result.x;
            int y = result.y;
            result = publicFunction.localFindPic(649, 523, 835, 643, "hero_up.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------hero_up->" + result);

                if(!AtFairyConfig.getOption("hero_battlefield_wx").equals("1")) {
                    TaskMain.taskList.remove(0);
                }
            }
            publicFunction.rndTapWH(x, y, 29, 29);
            Thread.sleep(500);

            for (int i = 0; i < 60; i++) {
                //点击离开后，
                result = publicFunction.localFindPic(480, 610, 609, 720, "leave1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------leave1->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 29, 29);
                    Thread.sleep(500);
                }
                result = publicFunction.localFindPic(890, 0, 1020, 123, "activity.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------activity->" + result);
                    break;
                }

                Thread.sleep(1000);
            }
        }
        result = publicFunction.localFindPic(561, 556, 686, 680, "determine2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------determine2->" + result);
            publicFunction.rndTapWH(result.x, result.y, 25, 24);
            Thread.sleep(500);
        }


    }

    private void guanduTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(756, 593, 879, 719, "visit2.png");

        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------visit2->" + result);
            publicFunction.rndTapWH(result.x, result.y, 23, 26);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(712, 371, 903, 461, "free.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------free->" + result);
            publicFunction.rndTap(759, 326, 857, 359);
            Thread.sleep(500);
        } else {

            result = publicFunction.localFindPic(308, 283, 969, 412, "Resurrection4.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------Resurrection4->" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
                Thread.sleep(1000);
            }

        }

        result = publicFunction.localFindPic(524, 27, 1034, 124, "HP.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------HP->" + result);
            publicFunction.rndTap(result.x + 5, result.y - 10, result.x + 10, result.y + 5);
            for (int i = 0; i < 20; i++) {
                result = publicFunction.localFindPic(308, 283, 969, 412, "Resurrection4.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------Resurrection4->" + result);
                    break;
                }
                Thread.sleep(1000);
            }
        }
        result = publicFunction.localFindPic(543, 605, 715, 720, "guandu1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------guandu1->" + result);
            int x = result.x;
            int y = result.y;

            result = publicFunction.localFindPic(25, 383, 128, 489, "empty.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------empty->" + result);
                //已经没有奖励，删除任务
                TaskMain.taskList.remove(0);
            }
            publicFunction.rndTapWH(x, y, 72, 33);
            Thread.sleep(500);
        }

    }

    //勇闯龙渊岛
    private void loong() throws Exception {
        AtFairy2.OpencvResult result;

        result = publicFunction.localFindPic(414, 363, 563, 457, "out.png");

        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------out->" + result);
            publicFunction.rndTapWH(result.x, result.y, 23, 26);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(409, 281, 690, 387, "loong1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------loong1->" + result);
            publicFunction.rndTap(744, 460, 815, 485);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(874, 479, 1072, 605, "sign.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------sign->" + result);
            publicFunction.rndTapWH(result.x, result.y, 98, 26);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(176, 588, 334, 714, "loong2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------loong2->" + result);
            publicFunction.rndTapWH(result.x, result.y, 58, 26);
            Thread.sleep(500);
        }


        result = publicFunction.localFindPic(1109, 529, 1272, 694, "hit.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------hit->" + result);
//            publicFunction.rndTapWH(result.x,result.y,63,65);
//            Thread.sleep(500);

            for (int i = 0; i < 20; i++) {
                result = publicFunction.localFindPic(1109, 529, 1272, 694, "hit.png");
                if (result.sim < 0.8) {
                    break;
                }
                publicFunction.rndTapWH(result.x, result.y, 63, 65);
                Thread.sleep(500);
            }


        }


    }

    public int mainTask() throws Exception {
        AtFairy2.OpencvResult result;
        MatTime matTime = new MatTime(mFairy);
        long sleepTime = 0;
        while (mFairy.condit()) {
            sleepTime = matTime.mMatTime(1119, 54, 71, 15, 0.8);
            LtLog.i(publicFunction.getLineInfo() + "------sleepTime->" + sleepTime);

            if (sleepTime >= 30) {

                result = publicFunction.localFindPic(0, 212, 110, 332, "main_task1.png|main_task2.png");
                if (result.sim >= 0.750) {
                    LtLog.i(publicFunction.getLineInfo() + "------main_task1->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 44, 20);
                    Thread.sleep(500);
                } else {
                    publicFunction.RanSwipe(49, 279, 119, 424, 0, 500);
                }
                matTime.resetTime();
            }
            result = publicFunction.localFindPic(474, 409, 630, 535, "main_task.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------main_task->" + result);
                publicFunction.rndTapWH(result.x, result.y, 56, 26);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(613, 93, 842, 229, "main_sign.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------main_task->" + result);
                publicFunction.rndTapWH(1221, 132, 28, 32);
                Thread.sleep(500);
            }

            result = publicFunction.localFindPic(1030, 618, 1199, 720, "main_hotSpring.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------main_hotSpring->" + result);
                publicFunction.rndTapWH(result.x, result.y, 69, 25);
                Thread.sleep(2000);
                gamePublicFunction.clickDetermine(679, 412, 885, 532);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(994, 618, 1140, 720, "main_out.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------main_out->" + result);
                publicFunction.rndTapWH(result.x, result.y, 46, 25);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(557, 581, 683, 707, "determine5.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------determine5->" + result);
                publicFunction.rndTapWH(result.x, result.y, 26, 26);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(23, 211, 166, 334, "main_Achieve.png");
            if (result.sim >= 0.8) {

                LtLog.i(publicFunction.getLineInfo() + "-------------主线结束-------------main_Achieve->" + result);
                break;
            }
            gamePublicFunction.car();//车夫传送
            acceptAndComplete();
            Thread.sleep(1000);
        }
        return 99;
    }

    //挖宝
    public int treasureMap() throws Exception {
        AtFairy2.OpencvResult result;
        MatTime matTime = new MatTime(mFairy);
        long sleepTime = 0;
        other.openPackage();

        while (mFairy.condit()) {
            sleepTime = matTime.mMatTime(1119, 54, 71, 15, 0.8);
            LtLog.i(publicFunction.getLineInfo() + "------sleepTime->" + sleepTime);
            if (sleepTime >= 30) {
                other.openPackage();
                matTime.resetTime();
            }
            result = publicFunction.localFindPic(970, 602, 1142, 720, "sell.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------sell->" + mFairy);
                if (!findTreasureMap()) {
                    //查找宝图
                    break;
                }
            }
            result = publicFunction.localFindPic(285, 24, 1268, 712, "use2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------use2->" + result);
                publicFunction.rndTapWH(result.x, result.y, 23, 10);
                Thread.sleep(1500);
            }

            result = publicFunction.localFindPic(425, 532, 888, 689, "determine6.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------determine6 ->" + result);
                publicFunction.rndTapWH(result.x, result.y, 23, 10);
                Thread.sleep(1500);
            }
            result = publicFunction.localFindPic(417, 287, 864, 388, "treasureMapOver.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------treasureMapOver ->" + result);
                publicFunction.rndTap(603, 461, 663, 480);
                Thread.sleep(1500);
                publicFunction.rndTap(1006, 81, 1023, 92);
                Thread.sleep(1500);
                break;
            }
            result = publicFunction.localFindPic(425, 532, 888, 689, "goto6.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------  goto6->" + result);
                publicFunction.rndTapWH(result.x, result.y, 23, 10);
                Thread.sleep(1500);
                gamePublicFunction.closeWindow();
            }
            result = publicFunction.localFindPic(567, 307, 712, 413, "stop.png|start1.png|reward3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------  start1->" + result);
                for (int i = 0; i < 3; i++) {
                    publicFunction.rndTapWH(result.x, result.y, 23, 10);
                    Thread.sleep(1500);
                }
                other.openPackage();
            }
            Thread.sleep(1000);
        }
        for (int i = 0; i < 3; i++) {
            gamePublicFunction.closeWindow();
        }
        return 99;
    }
    //在背包里查找宝图

    private boolean findTreasureMap() throws Exception {
        //找到宝图 或者不在背包界面返回true 否则返回false
        AtFairy2.OpencvResult result;
        boolean state = false;
        for (int i = 0; i < 20; i++) {
            result = publicFunction.localFindPic(731, 96, 1139, 504, "treasureMap.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------treasureMap->" + mFairy);
                publicFunction.rndTapWH(result.x, result.y, 10, 10);
                Thread.sleep(500);
                state = true;
                break;
            } else {
                if (i < 10) {
                    publicFunction.RanSwipe(802, 155, 1091, 453, 3, 500);
                } else {
                    publicFunction.RanSwipe(802, 155, 1091, 453, 1, 500);
                }
            }
            Thread.sleep(2000);
            result = publicFunction.localFindPic(970, 602, 1142, 720, "sell.png");
            if (result.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------sell->" + mFairy);
                state = true;
                break;
            }


        }
        return state;
    }


    public void resetGame() throws Exception {
        mFairy.killUserGame();
        AtFairy2.OpencvResult result;
        while (mFairy.condit()) {
            result = publicFunction.localFindPic(491, 614, 757, 691, "startUp1.png|startUp.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------startUp=" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(10000);
            }
            result = publicFunction.localFindPic(420, 517, 865, 680, "start_game1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------start_game1=" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(10000);
            }

            result = publicFunction.localFindPic(897, 602, 1237, 707, "start_game2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------start_game2=" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(10000);
            }
            result = publicFunction.localFindPic(363, 366, 658, 579, "cancel.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------cancel=" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(1000);
            }
            gamePublicFunction.closeWindow();
            result = publicFunction.localFindPic(890, 0, 1020, 123, "activity.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "------activity=" + result);
                break;
            }
            Thread.sleep(2000);
        }


    }


}




