package com.script.fairy;

import com.example.publicfunctionlibrary.FunctionClass;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

public class Abnormal {
    private static final int MIN_TIME = 200;
    //异常处理
    private AtFairyImpl mFairy;
    AtFairy2.OpencvResult result;
    private static long mLastCaptureTime;
    private long time = System.currentTimeMillis() / 1000, timex;
    private long time1 = System.currentTimeMillis() / 1000, time1x;
    private long time2 = System.currentTimeMillis() / 1000, time2x;
    private long time3 = System.currentTimeMillis() / 1000, time3x;
    private long time4 = System.currentTimeMillis() / 1000, time4x;
    private PublicFunction publicFunction;
    private GamePublicFunction gamePublicFunction;
    private FunctionClass functionClass;
    private FindResult findResult;

    PicTime answer_picTime;
    PicTime pic_ok;
    PicTime fork_picTime;
    PicTime Resurrection_picTime;
    PicTime delivery1_picTime;
    PicTime cat_picTime;
    PicTime QSHL_picTime;

    public Abnormal(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
        gamePublicFunction = new GamePublicFunction(mFairy);
        functionClass = new FunctionClass(ypFairy, ypFairy.getContext());
        answer_picTime = new PicTime(396, 269, 584, 389, "answer.png|answer1.png", 0.8, mFairy);
        pic_ok = new PicTime(516, 577, 778, 696, "ok.png", 0.8, mFairy);
        fork_picTime = new PicTime(643, 5, 1278, 445, "fork.png", 0.8, mFairy);
        Resurrection_picTime = new PicTime(284, 295, 498, 393, "Resurrection4.png"+"|"+"Resurrection.png", 0.8, mFairy);
        delivery1_picTime = new PicTime(517, 289, 857, 410, "delivery1.png", 0.8, mFairy);
        cat_picTime = new PicTime(666, 215, 808, 353, "cat.png", 0.8, mFairy);
        QSHL_picTime = new PicTime(420, 293, 613, 370, "QSHL.png", 0.8, mFairy);
    }



    public void erro() throws Exception {
        timex = System.currentTimeMillis() / 1000;

        if (timex - time > 60) {
            time = System.currentTimeMillis() / 1000;
            LtLog.i(publicFunction.getLineInfo() + "******abnormal start ing> ");
        }


        if (pic_ok.getPicTime() > 20) {//获取新副将的确定按钮
            LtLog.i(publicFunction.getLineInfo() + "******pic_ok> ");
            publicFunction.rndTap(621, 636, 662, 650);
        }


        if (answer_picTime.getPicTime() >= 30) {
            LtLog.i(publicFunction.getLineInfo() + "******answer_picTime > 60->");
            publicFunction.rndTap(468, 461, 536, 481);
            Thread.sleep(1000);
            answer_picTime.resetTime();
        }
        if (QSHL_picTime.getPicTime() >= 30) {
            LtLog.i(publicFunction.getLineInfo() + "******QSHL_picTime > 60->");
            publicFunction.rndTap(476, 460, 520, 483);
            Thread.sleep(1000);
            QSHL_picTime.resetTime();
        }
        if (fork_picTime.getPicTime() >= 60) {
            LtLog.i(publicFunction.getLineInfo() + "******fork_picTIme > 60->");
            int x = fork_picTime.getPicX();
            int y = fork_picTime.getPicY();
            LtLog.i(publicFunction.getLineInfo() + "******fork_picTIme > 60->" + ",x=" + x + ",y=" + y);
            publicFunction.rndTap(x, y, x + 10, y + 10);
            Thread.sleep(1000);
            fork_picTime.resetTime();
        }
        if (delivery1_picTime.getPicTime() > 10) {
            LtLog.i(publicFunction.getLineInfo() + "******delivery1_picTime > 15->");
            publicFunction.rndTap(749, 458, 812, 486);
            Thread.sleep(1000);
            delivery1_picTime.resetTime();
        }

        if (Resurrection_picTime.getPicTime() >= 20) {

            LimitlessTask.ResurrectionIndex++;

            LtLog.i(publicFunction.getLineInfo() + "******Resurrection_picTime > 15->");
            int x = Resurrection_picTime.getPicX();
            int y = Resurrection_picTime.getPicY();
            publicFunction.rndTap(x, y, x + 10, y + 10);
            Thread.sleep(1000);
            Resurrection_picTime.resetTime();
        }


        if (cat_picTime.getPicTime() >= 10) {
            LtLog.i(publicFunction.getLineInfo() + "******cat_picTime > 10->");
            publicFunction.rndTap(934, 286, 960, 314);
            Thread.sleep(1000);
            cat_picTime.resetTime();
        }

        result = publicFunction.localFindPic(1219, 3, 1276, 60, "skip.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******skip->" + result);
            mFairy.touchDown(result.x + 5, result.y + 5);
            Thread.sleep(2000);
            mFairy.touchUp();
            Thread.sleep(1000);
        }



        findResult = mFairy.findPic("hdclose.png");
       mFairy.onTap(0.8f,findResult,"hdclose",1000);

        findResult = mFairy.findPic("sd.png");
        mFairy.onTap(0.8f,findResult,1217,22,1239,36,"商店界面",1000);

        //温泉
        result = publicFunction.localFindPic(597, 269, 832, 386, "hot_spring2.png");//温泉经验上限
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******hot_spring2->" + result);
            publicFunction.rndTap(746, 457, 819, 487);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(499, 290, 823, 411, "outTime.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******outTime->" + result);
            publicFunction.rndTap(592, 457, 672, 488);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(529, 281, 852, 413, "outTime1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******outTime1->" + result);
            publicFunction.rndTap(604, 464, 671, 485);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(1197, 0, 1280, 80, "down.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******down->" + result);
            publicFunction.rndTapWH(result.x, result.y, 25, 16);
            Thread.sleep(500);
        }
        error();
        LuckDraw();
        redPackage();//抢红包
        Resurrection();
        land();
        Thread.sleep(1000);

    }

    /**
     * 登录相关
     *
     * @throws Exception
     */
    public void land() throws Exception {
        AtFairy2.OpencvResult result;
        //登陆相关
        result = publicFunction.localFindPic(286, 744, 500, 1076, "Land.png|Land1.png|Land2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******Land=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(491, 614, 757, 691, "startUp1.png|startUp.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******startUp=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(768, 536, 901, 646, "QQ.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******QQ=" + result);
            publicFunction.rndTapWH(result.x, result.y, 37, 43);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(420, 517, 865, 680, "start_game1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******start_game1=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(10000);
        }

        result = publicFunction.localFindPic(897, 602, 1237, 707, "start_game2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******start_game2=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(10000);
        }
    }


    //抽奖
    public void LuckDraw() throws Exception {
        AtFairy2.OpencvResult result, result1;
//        result = publicFunction.localFindPic(443, 289, 850, 403, "luck.png");
//        if (result.sim >= 0.8) {
//            LtLog.i(publicFunction.getLineInfo() + "******luck->" + result);
//            publicFunction.rndTap(472, 466, 537, 489);//点取消
//            Thread.sleep(1000);
//        }

        result = publicFunction.localFindPic(634, 322, 734, 386, "luckDraw.png");
        result1 = publicFunction.localFindPic(167, 595, 332, 669, "lookUp.png");
        if (result.sim < 0.8 && result1.sim < 0.8) {
            return;
        } else if (result.sim > 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******luckDraw->" + result);
            publicFunction.rndTap(749, 457, 814, 487);//点击确认
            Thread.sleep(500);
        }
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(634, 322, 734, 386, "luckDraw.png");
            if (result.sim > 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "******luckDraw->" + result);
                publicFunction.rndTap(749, 457, 814, 487);//点击确认
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(167, 595, 332, 669, "lookUp.png");
            if (result.sim > 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "******lookUp->" + result);
                publicFunction.rndTap(832, 326, 872, 368);
                Thread.sleep(500);
            } else {
                break;
            }
            Thread.sleep(500);
        }
        gamePublicFunction.closeWindow();
    }

    public void Resurrection() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(520, 297, 758, 390, "Resurrection1.png|Resurrection2.png|Resurrection3.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------Resurrection1->" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(276, 277, 489, 405, "Resurrection5.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------Resurrection5->" + result);
            publicFunction.rndTapWH(result.x, result.y, 113, 28);
            Thread.sleep(500);
        }
    }

    private void error() throws Exception {
        result = publicFunction.localFindPic(406, 278, 654, 399, "error.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******error->" + result);
            publicFunction.rndTap(471, 455, 535, 481);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(479, 294, 797, 410, "error3.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******error3->" + result);
            publicFunction.rndTap(454, 458, 568, 487);//点取消
            Thread.sleep(1000);

        }
        result = publicFunction.localFindPic(440, 284, 718, 393, "error2.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "******error2->" + result);
            publicFunction.rndTap(475, 457, 557, 479);//点取消
            Thread.sleep(1000);

        }

        result = publicFunction.localFindPic(497, 293, 770, 420, "error1.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "******error1->" + result);
            publicFunction.rndTap(610, 466, 682, 491);//点确定
            Thread.sleep(1000);

        }

        result = publicFunction.localFindPic(464, 301, 683, 397, "error4.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******error4->" + result);
            publicFunction.rndTap(457, 456, 557, 482);//点取消
            Thread.sleep(1000);

        }
        result = publicFunction.localFindPic(464, 301, 683, 397, "error5.png");
        if (result.sim >= 0.75) {
            LtLog.i(publicFunction.getLineInfo() + "******error5->" + result);
            publicFunction.rndTap(599, 457, 653, 483);//点确定
            Thread.sleep(1000);

        }

        result = publicFunction.localFindPic(441, 299, 846, 405, "error6.png");
        if (result.sim >= 0.75) {
            LtLog.i(publicFunction.getLineInfo() + "******error6->" + result);
            publicFunction.rndTap(609, 462, 665, 481);//点确定
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(505, 293, 839, 437, "error7.png");
        if (result.sim >= 0.75) {
            LtLog.i(publicFunction.getLineInfo() + "******error7->" + result);
            publicFunction.rndTap(594, 455, 672, 483);//点确定
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(640, 283, 781, 404, "error8.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******error8->" + result);
            publicFunction.rndTap(617, 460, 679, 483);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(465, 280, 845, 405, "error9.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******error9->" + result);
            publicFunction.rndTap(623, 461, 686, 481);//点确定
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(17, 529, 132, 690, "huangbang1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******huangbang1->" + result);
            publicFunction.rndTap(623, 348, 655, 381);//点屏幕中间
            Thread.sleep(500);
        }

    }

    private void redPackage() throws Exception {
        result = publicFunction.localFindPic(257, 533, 329, 617, "redPackage.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******redPackage->" + result);
            publicFunction.rndTapWH(result.x, result.y, 30, 22);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(178, 295, 353, 416, "redPackage1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******redPackage1->" + result);
            publicFunction.rndTapWH(result.x, result.y, 75, 21);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(440, 96, 585, 233, "redPackage2.png");
        if (result.sim >= 0.85) {
            LtLog.i(publicFunction.getLineInfo() + "******redPackage2->" + result);
            publicFunction.rndTap(838, 62, 857, 80);
            Thread.sleep(1000);
            publicFunction.rndTap(1143, 58, 1168, 79);
            Thread.sleep(1000);
        }
    }

}