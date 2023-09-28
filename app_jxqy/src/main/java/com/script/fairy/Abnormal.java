package com.script.fairy;

import com.example.publicfunctionlibrary.FunctionClass;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/22 0022.
 */
public class Abnormal {
    private GamePublicFunction gamePublicFunction;
    private PublicFunction publicFunction;
    private FunctionClass functionClass;
    private AtFairyImpl mFairy;
    private FindResult findResult;
    public Abnormal(AtFairyImpl ypFairy) {
        mFairy = ypFairy;

        publicFunction = new PublicFunction(mFairy);
//        publicFunction=mFairy.publicFunction;
        gamePublicFunction = new GamePublicFunction(mFairy);
//        functionClass = mFairy.functionClass;
        functionClass = new FunctionClass(mFairy,mFairy.getContext());
    }

    public void erro() throws Exception {

        Thread.sleep(5000);

        AtFairy2.OpencvResult result, result1;
        long time = System.currentTimeMillis() / 1000, timex = 0;
        long time1 = System.currentTimeMillis() / 1000, time1x = 0;
        long time2 = System.currentTimeMillis() / 1000, time2x = 0;
        boolean redPack_off_on = true;
        LtLog.i(publicFunction.getLineInfo() + "-----*************-------->mAbnormal");

        PicTime snatch_picTime = new PicTime(461, 274, 791, 378, "snatch.png", 0.8, mFairy);
        PicTime createTeam2_picTime = new PicTime(451, 272, 796, 373, "createTeam2.png", 0.8, mFairy);
        PicTime challenge_over_picTime = new PicTime(451, 272, 796, 373, "challenge_over.png", 0.8, mFairy);

        List<String> list = new ArrayList<>();

        list.add("com.tencent.tmgp.jxqy");


        while (mFairy.condit()) {

            findResult  = mFairy.findPic(1012,165,1255,638,"zhidao.png");
            mFairy.onTap(0.8f,findResult,"知道了",1000);






           if ((TaskMain.mTask.equals("redPackageAndDssist") && TaskMain.taskMap.get("redPack") == 0) || TaskMain.mTask.equals("dance")) {
                LtLog.i(publicFunction.getLineInfo() + "关闭抢红包");
            }else{
                result = publicFunction.localFindPic(714, 355, 835, 480, "redPackage.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "打开红包");
                    publicFunction.rndTapWH(result.x, result.y, 21, 25);
                    Thread.sleep(1000);
                }

                result = publicFunction.localFindPic(523, 141, 789, 338, "redPackage1.png"+"|"+"redPackage2.png");
                if (result.sim >= 0.7){
                    LtLog.i(publicFunction.getLineInfo() + "红包界面");
                    publicFunction.rndTap(369, 44, 385, 51);
                    Thread.sleep(2000);
                }
                Thread.sleep(100);

            }

            findResult  = mFairy.findPic("ok2.png");
            mFairy.onTap(0.8f,findResult,616,630,640,642,"确定",1000);

            findResult  = mFairy.findPic("escGame.png");
            mFairy.onTap(0.8f,findResult,"返回游戏",1000);

            if (TaskMain.mTask.equals("dance")) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->task is dance , sleep=");
                while (mFairy.condit()) {
                    if (!TaskMain.mTask.equals("dance")) {
                        LtLog.i(publicFunction.getLineInfo() + "-----*************-------->dance break=");
                        break;
                    }
                    Thread.sleep(20000);
                }
            }

            result = gamePublicFunction.close();
            if (result.sim >= 0.8) {
                timex = System.currentTimeMillis() / 1000 - time;
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->closeWindow=" + result + ",timex=" + timex);
                if (timex >= 300) {
                    LtLog.i(publicFunction.getLineInfo() + "-----*************-------->closeWindow=");
                    publicFunction.rndTapWH(result.x, result.y, 5, 5);
                    Thread.sleep(500);
                    time = System.currentTimeMillis() / 1000;
                }
            } else {
                time = System.currentTimeMillis() / 1000;
            }
            if (snatch_picTime.getPicTime() >= 30) {
                LtLog.i(publicFunction.getLineInfo() + "*************************snatch_picTime > 60->");
                int x = snatch_picTime.getPicX() + 87;
                int y = snatch_picTime.getPicY() + 128;
                //点击确认，计算偏移
                LtLog.i(publicFunction.getLineInfo() + "*************************snatch_picTime > 60->" + ",x=" + x + ",y=" + y);
                publicFunction.rndTap(x, y, x + 10, y + 10);
                Thread.sleep(1000);
                snatch_picTime.resetTime();
            }
            if (!android.os.Build.VERSION.RELEASE.equals("5.1.1")) {
                //如果不是老板子  才开启此处的抢红包功能
                //老板子使用 RedPackage 的抢红包功能
                result = publicFunction.localFindPic(714, 355, 835, 480, "redPackage.png");
                //LtLog.i(publicFunction.getLineInfo() + "~~~~~~~~~~~~~~~~~~~>redPackage=" + result);
                if (result.sim >= 0.8 && redPack_off_on) {
                    publicFunction.rndTapWH(result.x, result.y, 21, 25);
                    Thread.sleep(500);
                }
                result = publicFunction.localFindPic(523, 141, 789, 338, "redPackage1.png|redPackage2.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "~~~~~~~~~~~~~~~~~~~>redPackage1=" + result);
                    publicFunction.rndTap(369, 44, 385, 51);
                    Thread.sleep(500);
                }
            }
            if (createTeam2_picTime.getPicTime() >= 30) {
                LtLog.i(publicFunction.getLineInfo() + "*************************createTeam2_picTime > 30->");
                publicFunction.rndTap(727, 431, 808, 469);
                Thread.sleep(1000);
                createTeam2_picTime.resetTime();
            }
            if (challenge_over_picTime.getPicTime() >= 10) {
                LtLog.i(publicFunction.getLineInfo() + "*************************challenge_over_picTime > 30->");
                publicFunction.rndTap(610, 433, 673, 463);
                Thread.sleep(1000);
                challenge_over_picTime.resetTime();
            }
            result = publicFunction.localFindPic(404, 246, 851, 392, "closefollow.png");
            if (result.sim >= 0.8) {
                time1x = System.currentTimeMillis() / 1000 - time1;
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->closefollow=" + result + ",time1x=" + time1x);
                if (time1x >= 30) {
                    publicFunction.rndTap(744, 434, 785, 461);
                    Thread.sleep(500);
                    time1 = System.currentTimeMillis() / 1000;
                }
            } else {
                time1 = System.currentTimeMillis() / 1000;
            }
            result = publicFunction.localFindPic(342, 57, 933, 649, "smallMap.png|smallMap1.png");
            result1 = publicFunction.localFindPic(1196, 15, 1262, 84, "fork.png|fork1.png" + "|" + "fork2.png" + "|" + "fork3.png");
//            LtLog.i(publicFunction.getLineInfo() + "---*************-close  and fork -->result=" + result + ",result1 : " + result1);
            if (result.sim > 0.8 && result1.sim > 0.8) {
                //补丁  小地图和大地图叠加   关闭小地图  这是游戏bug
                publicFunction.rndTap(936, 49, 961, 81);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(1173,12,1265,101, "fork5.png");
            if (result.sim > 0.8){
                publicFunction.rndTap(result.x, result.y,result.x+10, result.y+10);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(530, 524, 739, 606, "Iknow1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->Iknow1=" + result);
                publicFunction.rndTapWH(result.x, result.y, 110, 27);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(384, 527, 1042, 604, "Iknow.png"+ "|" +"Iknow2.png" + "|" + "Iknow3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->Iknow=" + result);
                publicFunction.rndTapWH(result.x, result.y, 110, 27);
                Thread.sleep(1000);
//                result = publicFunction.localFindPic(372,518,555,599, "attend2.png");
//                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->attend2=" + result);
//                if (result.sim >= 0.8) {
//                    publicFunction.rndTapWH(result.x, result.y, 30, 20);
//                    Thread.sleep(1000);
//                }
                for (int i = 0; i < 10; i++) {
                    result = publicFunction.localFindPic(384, 527, 1042, 604, "Iknow.png"+ "|" +"Iknow2.png" + "|" + "Iknow3.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "-----*************-------->Iknow=" + result);
                        publicFunction.rndTapWH(result.x, result.y, 110, 27);
                        Thread.sleep(100);
                    }
                    result = publicFunction.localFindPic(770, 2, 1278, 454, "fork.png|fork1.png" + "|" + "fork2.png" + "|" + "fork3.png");
                    LtLog.i(publicFunction.getLineInfo() + "-----*************-------->fork=" + result);
                    if (result.sim >= 0.8) {
                        publicFunction.rndTapWH(result.x, result.y, 10, 10);
                        Thread.sleep(1000);
                        break;
                    }
                    Thread.sleep(1000);
                }
            }
            result = publicFunction.localFindPic(401, 411, 613, 490, "noBuy.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->noBuy=" + result);
                publicFunction.rndTap(472, 440, 547, 461);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(601, 483, 744, 602, "level.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->level=" + result);
                publicFunction.rndTapWH(result.x, result.y, 43, 19);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(509, 406, 770, 500, "goHome.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->goHome=" + result);
                publicFunction.rndTapWH(result.x, result.y, 43, 19);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(557, 288, 721, 357, "err1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err1=" + result);
                publicFunction.rndTap(745, 439, 790, 465);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(429, 244, 887, 386, "err2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err2=" + result);
                publicFunction.rndTap(721, 428, 822, 475);//点确认
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(454, 273, 808, 366, "err3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err3=" + result);
                publicFunction.rndTap(605, 432, 669, 465);//点确认
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(715, 281, 875, 360, "err4.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err4=" + result);
                publicFunction.rndTap(599, 438, 675, 460);//点知道了
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(387, 235, 528, 347, "err6.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err6=" + result);
                publicFunction.rndTap(742, 437, 795, 464);//点重连
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(501, 279, 785, 382, "err8.png");
//            LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err8=" + result);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err8=" + result);
                publicFunction.rndTap(598, 435, 671, 467);//点确定
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(676, 410, 864, 501, "err9.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err9=" + result);
                publicFunction.rndTapWH(result.x, result.y, 10, 10);//不需要
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(649, 447, 878, 537, "err10.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err10=" + result);
                publicFunction.rndTapWH(result.x - 245, result.y, 10, 10);//重试
                Thread.sleep(500);
            }


            result = publicFunction.localFindPic(396, 283, 714, 352, "err11.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err11=" + result);
                publicFunction.rndTap(731, 440, 809, 461);//退出并进入
                Thread.sleep(500);
            }

            result = publicFunction.localFindPic(390, 249, 647, 381, "err12.png");
            if (result.sim >= 0.8) {
                //商会协助弹出
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err11=" + result);
                publicFunction.rndTap(477, 437, 539, 466);//取消
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(488, 308, 626, 332, "err13.png");
//            LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err13=" + result);
            if (result.sim >= 0.8) {
                //凭证失效
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err13=" + result);
                publicFunction.rndTap(598, 435, 671, 467);//点确定
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(548, 521, 740, 609, "err14.png");
            if (result.sim >= 0.8) {

                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err14=" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(443, 239, 820, 393, "err15.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err15=" + result);
                publicFunction.rndTap(482, 438, 542, 466);
                Thread.sleep(500);
            }

            result = publicFunction.localFindPic(393, 281, 494, 325, "err16.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err16=" + result);
                publicFunction.rndTap(478, 436, 536, 461);//点取消
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(357, 425, 526, 488, "err17.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err17=" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);//关闭
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(458,300,564,412, "screenLock.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------screenLock  : 从左往右滑动=" + result);
                publicFunction.RanSwipe(488,326,517,388, 1, 1000);
            }
            result = publicFunction.localFindPic(535, 122, 752, 214, "JXQY.png");
            if (result.sim >= 0.8) {
                //充值窗口 ，点击关闭
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->JXQY=" + result);
                publicFunction.rndTap(69, 155, 88, 175);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(384, 388, 653, 512, "reject.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->reject=" + result);
                publicFunction.rndTapWH(result.x, result.y, 25, 20);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(540, 285, 663, 401, "wanted.png");
            if (result.sim >= 0.8) {
                //通缉
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->wanted=" + result);
                publicFunction.rndTapWH(result.x, result.y, 25, 20);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(504, 237, 836, 370, "wanted1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->wanted1=" + result);
                publicFunction.rndTap(742, 436, 808, 465);//点同意
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(506, 230, 737, 349, "notOperation.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->notOperation=" + result);
                publicFunction.rndTap(735, 431, 803, 465);//点重连
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(1085,186,1153,251, "fork4.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->fork4=" + result);
                publicFunction.rndTap(result.x+5, result.y+5, result.x+10, result.y+10);//关闭
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(510,229,604,271, "insufficient.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->insufficient=" + result);
                publicFunction.rndTap(475,437,542,465);//点取消  商会协助提示道具不足,点取消
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(1, 536, 145, 677, "post.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->post=" + result);
                Thread.sleep(2000);
                publicFunction.rndTap(606, 40, 624, 71);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(0, 104, 82, 245, "arrow1.png");
            if (result.sim >= 0.7) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->arrow1=" + result);
                publicFunction.rndTapWH(result.x, result.y, 25, 41);
                mFairy.tap(result.x + 10, result.y + 10);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(1037, 0, 1192, 99, "arrow2.png");
            if (result.sim >= 0.7) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->arrow2=" + result);
                time2x = System.currentTimeMillis() / 1000 - time2;
                if (time2x > 10) {
                    mFairy.tap(result.x + 10, result.y + 10);
                    Thread.sleep(500);
                }
            } else {
                time2 = System.currentTimeMillis() / 1000;
            }
            result = publicFunction.localFindPic(384, 240, 890, 372, "apply.png");
            if (result.sim >= 0.8) {
                //烤火传功，传功结束后 如果对方不是好友 会弹出对话框询问是否添加好友。有人会在烤火结束之后同意传功，所有这个弹出框要做为异常处理
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->apply=" + result);
                publicFunction.rndTap(475, 430, 540, 471);//取消
                Thread.sleep(1000);
            }
            //登陆相关
            land();
            //点击弹出
            clickPop();
            //被游戏强制下线
            resetGame();
            //进入防沉迷模式
            if(AtFairyConfig.getOption("timeoffline").equals("1")){
            //if (TaskMain.taskMap.get("timeoffline") == 1) {
                boolean resetTime = gamePublicFunction.determineResetTime();
                if (resetTime) {
                    LtLog.i(publicFunction.getLineInfo() + "-----*************----killGame---->=");
//                    mFairy.killUserGame();
                    killApp();
                    Thread.sleep(10000);
                    publicFunction.waitGameReset(1200);
                }
            }

            /**
             * 在这里加图
             */


            findResult = mFairy.findPic(441,408,772,573,"test_login.png");
            mFairy.onTap(0.8f,findResult,"test_login",1000);



            findResult = mFairy.findPic(407,246,859,407,"likai.png");
            mFairy.onTap(0.8f,findResult,614,442,649,462,"战斗结束",1000);

            findResult = mFairy.findPic("shengli.png");
            mFairy.onTap(0.8f,findResult,622,631,660,647,"胜利",1000);

            findResult = mFairy.findPic("shibai.png");
            mFairy.onTap(0.8f,findResult,622,631,660,647,"失败",1000);

            findResult = mFairy.findPic("hdclose.png");
            mFairy.onTap(0.8f,findResult,"活动弹框",1000);

            findResult = mFairy.findPic("hdclose1.png");
            mFairy.onTap(0.8f,findResult,1215,49,1231,59,"活动弹框",1000);

            findResult = mFairy.findPic(560,638,631,677,"hdclose3.png");
            mFairy.onTap(0.8f,findResult,904,64,931,74,"hdclose3",1000);

            findResult = mFairy.findPic(401,845,615,1000,"tongyi.png");
            mFairy.onTap(0.8f,findResult,"同意",1000);

            findResult = mFairy.findPic(251,794,495,1025,"ok1.png");
            mFairy.onTap(0.8f,findResult,"同意",1000);

            findResult = mFairy.findPic("anzhuo.png");
            if(findResult.sim>0.8f) {

                findResult = mFairy.findPic(118,567,408,621,"gou.png");
                mFairy.onTap(0.8f,findResult,"打勾",1500);


                switch (AtFairyConfig.getOption("login_set")){
                    case "1":
                        mFairy.onTap(126,491,187,504, "与ios微信好友一起玩", 1000);
                        break;
                    case "2":
                        mFairy.onTap(421,491,489,509, "与安卓微信好友一起玩", 1000);
                        break;
                    case "3":
                        mFairy.onTap(703,489,848,510, "与iosQQ好友一起玩", 1000);
                        break;
                    default:
                        mFairy.onTap(1004,487,1066,509, "与安卓QQ好友一起玩", 1000);
                        break;
                }

            }

            findResult = mFairy.findPic(433,558,769,654,"sui.png");
            mFairy.onTap(0.8f,findResult,642,564,675,586,"内丹碎片 - 完成",1000);

            findResult = mFairy.findPic(368,211,685,361,"login1.png");
            mFairy.onTap(0.8f,findResult,620,443,654,461,"同意",1000);

            findResult = mFairy.findPic(222,70,406,166,"gonggao.png");
            mFairy.onTap(0.8f,findResult,621,573,656,593,"公告点击确定",1000);

            findResult = mFairy.findPic(437,172,838,479,"denglu.png");
            mFairy.onTap(0.8f,findResult,616,442,667,461,"登录失败",1000);

            findResult = picCount("shxz",5,0.8f,390,212,875,512,"shanghui.png");
            if(findResult!=null) {
                mFairy.onTap(0.8f, findResult, 485, 440, 522, 462, "取消协助交货", 1000);
            }

            Thread.sleep(100);
        }
    }

    private Map<String,Integer> map = new HashMap<>();

    public FindResult picCount(String key,int count,float sim,int x,int y,int x1,int y1,String ...img)throws Exception{
        FindResult fr = mFairy.findPic(x,y,x1,y1,img);
        if(fr.sim>=sim){
            if(map.containsKey(key)){
                if(map.get(key)>=count){
                    map.put(key,0);
                    return fr;
                }else{
                    map.put(key,map.get(key)+1);
                }

            }else{
                map.put(key,1);
            }
        }else{
            if(map.containsKey(key)){
                map.put(key,0);
            }
        }
        return null;
    }























    public void killApp() {

        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(392, 229, 885, 413, "IDreplace.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------IDreplace->" + result);
            return;
        }
        mFairy.killUserGame();
    }

    public void clickPop() throws Exception {
        //弹出框
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(514,274,757,374, "patriarch.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******patriarch=" + result);
            publicFunction.rndTap(936,101,959,125); //关闭
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(653,207,903,308, "parent.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-----*************-----clickPop--->parent=" + result);
            publicFunction.rndTap(1084,112,1100,128); //点叉
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(527,135,749,209, "parent1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******clickPop--->parent1=" + result);
            publicFunction.rndTap(1017,69,1036,88); //点叉
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(386, 260, 850, 366, "WLMZover.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-----*************-----clickPop--->WLMZover=" + result);
            publicFunction.rndTap(604, 433, 665, 464); //点击确认
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(707, 184, 803, 243, "privacy.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-----*************-----clickPop--->privacy=" + result);
            publicFunction.rndTap(420, 483, 475, 505);//点击关闭
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(478, 288, 786, 359, "renounce.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-----*************-----clickPop--->renounce=" + result);
            publicFunction.rndTap(482, 430, 533, 468); //点击取消
            Thread.sleep(500);
        }

        for (int i = 0; i < 3; i++) {
            result = publicFunction.localFindPic(413, 262, 730, 384, "pop1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-----clickPop--->pop1=" + result);
                publicFunction.rndTap(595, 434, 681, 462); //点击知道了
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(774, 367, 1003, 518, "pop2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-----clickPop--->pop2=" + result);
                publicFunction.rndTap(965, 221, 994, 256);
                Thread.sleep(500);
            } else {
                break;
            }
            Thread.sleep(10);
        }
    }

    public void land() throws Exception {
        AtFairy2.OpencvResult result;
        //登陆相关
        result = publicFunction.localFindPic(392, 229, 885, 413, "IDreplace.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "--------- IDreplace>" + result);
            mFairy.finish(AtFairyConfig.getTaskID(),575);
        }
        result = publicFunction.localFindPic(286, 744, 500, 1200, "Land.png" + "|" + "Land1.png" + "|" + "Land2.png" + "|" + "Land3.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------Land=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(411, 1113, 611, 1215, "land4.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------Land=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(491, 614, 757, 691, "startUp1.png|startUp.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------startUp=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(189, 57, 442, 152, "notice.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------startUp=" + result);
            publicFunction.rndTap(601, 570, 659, 601);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(207, 24, 520, 143, "agreement.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------agreement=" + result);
            publicFunction.rndTap(340, 595, 397, 626);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(528, 427, 661, 659, "login.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------login=" + result);
            publicFunction.rndTapWH(result.x, result.y, 33, 32);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(1014, 587, 1147, 719, "login1.png" + "|" + "into4.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------login=" + result);
            publicFunction.rndTapWH(result.x, result.y, 33, 32);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(193,577,401,619, "agree3.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-----*************-------->agree3=" + result);
            result = publicFunction.localFindPic(142,565,203,629, "hook.png");
            LtLog.i(publicFunction.getLineInfo() + "-----*************-------->hook=" + result);
            if (result.sim < 0.8) {
                publicFunction.rndTap(167, 587, 179, 603);//协议打钩
                Thread.sleep(5000);
            }
        }




        findResult = mFairy.findPic(231,896,508,1194,"ty2.png");
        mFairy.onTap(0.7f,findResult,"同意",1000);


        result = publicFunction.localFindPic(712, 462, 849, 666, "QQ.png" + "|" + "QQ1.png");
        if (result.sim >= 0.8) {

            findResult = mFairy.findPic(111,568,178,620,"ty1.png");
            mFairy.onTap(0.85f,findResult,"同意",1000);

            LtLog.i(publicFunction.getLineInfo() + "*******************--------------QQ=" + result);
            publicFunction.rndTapWH(result.x, result.y, 37, 43);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(116, 30, 339, 147, "jurisdiction.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------jurisdiction=" + result);
            //申请权限列表
            publicFunction.rndTapWH(608, 556, 63, 27);//点击确认
            Thread.sleep(500);
        }


        result = publicFunction.localFindPic(286, 124, 981, 241, "agreement1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------agreement1=" + result);
            //腾讯用户协议
            publicFunction.rndTap(412, 441, 471, 469);//点击同意
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(134, 1027, 602, 1194, "empower.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------empower=" + result);
            //点击授权
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);//点击同意
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(263, 729, 467, 911, "arrow4.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------arrow4=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(418, 857, 593, 936, "agree2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------agree2=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 20);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(406,691,614,977, "tga.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------tga=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 20);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(163,784,568,1249, "login2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------login2=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 20);
            Thread.sleep(500);
        }


        result = publicFunction.localFindPic(340,386,566,534, "agree4.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------agree4=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 20);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(89, 929, 656, 1231, "authorize.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------authorize=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 20);
            Thread.sleep(500);
        }

    }

    private void resetGame() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(662, 629, 845, 711, "sleep.png");
        if (result.sim >= 0.9) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------sleep=" + result);
            LtLog.i(publicFunction.getLineInfo() + "-----*************----killGame---->=");
            mFairy.killUserGame();
            Thread.sleep(10000);
            publicFunction.waitGameReset(100);
//            for (int i = 0; i < 10; i++) {
//                Thread.sleep(10000);
//                LtLog.i(publicFunction.getLineInfo() + "*******************--------------sleep=" + result);
//            }
        }
        result = publicFunction.localFindPic(604, 248, 885, 382, "err5.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err5=" + result);
            publicFunction.rndTap(609, 429, 672, 473);//点确认
            Thread.sleep(10000);
            LtLog.i(publicFunction.getLineInfo() + "-----*************----killGame---->=");
            mFairy.killUserGame();
            Thread.sleep(10000);
            LtLog.i(publicFunction.getLineInfo() + "-----*************----killGame---->=");
            publicFunction.waitGameReset(300);
//            for (int i = 0; i <30 ; i++) {
//                LtLog.i(publicFunction.getLineInfo() + "-----*************----sleep 5 Minute---->=" );
//                Thread.sleep(10000);
//            }
        }


    }

    public void mAbnormal2() throws Exception {
        FunctionClass.ResultValue result, result1;
        long time = System.currentTimeMillis() / 1000, timex = 0;
        long time1 = System.currentTimeMillis() / 1000, time1x = 0;
        long time2 = System.currentTimeMillis() / 1000, time2x = 0;
        long time3 = System.currentTimeMillis() / 1000, time3x = 0;
        boolean redPack_off_on = true;
        if (TaskMain.mTask.equals("redPackageAndDssist") && TaskMain.taskMap.get("redPack") == 0) {
            //关闭抢红包
            LtLog.i(publicFunction.getLineInfo() + "-----*************-------->close redPack=");
            redPack_off_on = false;
        }
        PicTime snatch_picTime = new PicTime(461, 274, 791, 378, "snatch.png", 0.8, mFairy);
        PicTime createTeam2_picTime = new PicTime(451, 272, 796, 373, "createTeam2.png", 0.8, mFairy);

        while (mFairy.condit()) {
            result = functionClass.rgbFindPic(770, 2, 1278, 454, "fork.png|fork1.png" + "|" + "fork2.png" + "|" + "fork3.png");
            if (result.sim >= 0.8) {
                timex = System.currentTimeMillis() / 1000 - time;
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->closeWindow=" + result + ",timex=" + timex);
                if (timex >= 300) {
                    LtLog.i(publicFunction.getLineInfo() + "-----*************-------->closeWindow=");
                    publicFunction.rndTapWH(result.x, result.y, 5, 5);
                    Thread.sleep(500);
                    time = System.currentTimeMillis() / 1000;
                }
            } else {
                time = System.currentTimeMillis() / 1000;
            }
            long time_test = System.currentTimeMillis();
            result = functionClass.rgbFindPic(714, 355, 835, 480, "redPackage.png");
            LtLog.i(publicFunction.getLineInfo() + "-----*************----test time---->=" + (System.currentTimeMillis() - time_test));
            if (result.sim >= 0.8 && redPack_off_on) {
                LtLog.i(publicFunction.getLineInfo() + "~~~~~~~~~~~~~~~~~~~>redPackage=" + result);
                publicFunction.rndTapWH(result.x, result.y, 21, 25);
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(523, 141, 789, 338, "redPackage1.png|redPackage2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "~~~~~~~~~~~~~~~~~~~>redPackage1=" + result);
                publicFunction.rndTap(369, 44, 385, 51);
                Thread.sleep(500);
            }

            result = functionClass.rgbFindPic(404, 246, 851, 392, "closefollow.png");
            if (result.sim >= 0.8) {
                time1x = System.currentTimeMillis() / 1000 - time1;
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->closefollow=" + result + ",time1x=" + time1x);
                if (time1x >= 30) {
                    publicFunction.rndTap(744, 434, 785, 461);
                    Thread.sleep(500);
                    time1 = System.currentTimeMillis() / 1000;
                }
            } else {
                time1 = System.currentTimeMillis() / 1000;
            }
            result = functionClass.rgbFindPic(342, 57, 933, 649, "smallMap.png|smallMap1.png");
            result1 = functionClass.rgbFindPic(1196, 15, 1262, 84, "fork.png|fork1.png" + "|" + "fork2.png" + "|" + "fork3.png");
            LtLog.i(publicFunction.getLineInfo() + "---*************-close  and fork -->result=" + result + ",result1 : " + result1);
            if (result.sim > 0.8 && result1.sim > 0.8) {
                //补丁  小地图和大地图叠加   关闭小地图  这是游戏bug
                publicFunction.rndTap(936, 49, 961, 81);
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(530, 524, 739, 606, "Iknow1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->Iknow1=" + result);
                publicFunction.rndTapWH(result.x, result.y, 110, 27);
                Thread.sleep(2000);
            }
            result = functionClass.rgbFindPic(384, 527, 1042, 604, "Iknow.png"+ "|" +"Iknow2.png" + "|" + "Iknow3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->Iknow=" + result);
                publicFunction.rndTapWH(result.x, result.y, 110, 27);
                Thread.sleep(1000);
//                result = publicFunction.localFindPic(372,518,555,599, "attend2.png");
//                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->attend2=" + result);
//                if (result.sim >= 0.8) {
//                    publicFunction.rndTapWH(result.x, result.y, 30, 20);
//                    Thread.sleep(1000);
//                }
                for (int i = 0; i < 10; i++) {
                    result = functionClass.rgbFindPic(384, 527, 1042, 604, "Iknow.png"+ "|" +"Iknow2.png" + "|" + "Iknow3.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "-----*************-------->Iknow=" + result);
                        publicFunction.rndTapWH(result.x, result.y, 110, 27);
                        Thread.sleep(100);
                    }
                    result = functionClass.rgbFindPic(770, 2, 1278, 454, "fork.png|fork1.png" + "|" + "fork2.png" + "|" + "fork3.png");
                    LtLog.i(publicFunction.getLineInfo() + "-----*************-------->fork=" + result);
                    if (result.sim >= 0.8) {
                        publicFunction.rndTapWH(result.x, result.y, 10, 10);
                        Thread.sleep(1000);
                        break;
                    }
                    Thread.sleep(1000);
                }
            }
            result = functionClass.rgbFindPic(401, 411, 613, 490, "noBuy.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->noBuy=" + result);
                publicFunction.rndTap(472, 440, 547, 461);
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(601, 483, 744, 602, "level.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->level=" + result);
                publicFunction.rndTapWH(result.x, result.y, 43, 19);
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(509, 406, 770, 500, "goHome.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->goHome=" + result);
                publicFunction.rndTapWH(result.x, result.y, 43, 19);
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(557, 288, 721, 357, "err1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err1=" + result);
                publicFunction.rndTap(745, 439, 790, 465);
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(429, 244, 887, 386, "err2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err2=" + result);
                publicFunction.rndTap(721, 428, 822, 475);//点确认
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(454, 273, 808, 366, "err3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err3=" + result);
                publicFunction.rndTap(605, 432, 669, 465);//点确认
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(715, 281, 875, 360, "err4.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err4=" + result);
                publicFunction.rndTap(599, 438, 675, 460);//点知道了
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(387, 235, 528, 347, "err6.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err6=" + result);
                publicFunction.rndTap(742, 437, 795, 464);//点重连
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(501, 279, 785, 382, "err8.png");
//            LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err8=" + result);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err8=" + result);
                publicFunction.rndTap(598, 435, 671, 467);//点确定
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(676, 410, 864, 501, "err9.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err9=" + result);
                publicFunction.rndTapWH(result.x, result.y, 10, 10);//不需要
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(649, 447, 878, 537, "err10.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err10=" + result);
                publicFunction.rndTapWH(result.x - 245, result.y, 10, 10);//重试
                Thread.sleep(500);
            }

            result = functionClass.rgbFindPic(396, 283, 714, 352, "err11.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err11=" + result);
                publicFunction.rndTap(731, 440, 809, 461);//退出并进入
                Thread.sleep(500);
            }

            result = functionClass.rgbFindPic(390, 249, 647, 381, "err12.png");
            if (result.sim >= 0.8) {
                //商会协助弹出
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err11=" + result);
                publicFunction.rndTap(477, 437, 539, 466);//取消
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(488, 308, 626, 332, "err13.png");
//            LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err13=" + result);
            if (result.sim >= 0.8) {
                //凭证失效
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err13=" + result);
                publicFunction.rndTap(598, 435, 671, 467);//点确定
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(548, 521, 740, 609, "err14.png");
            if (result.sim >= 0.8) {

                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err14=" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(500);
            }


            result = functionClass.rgbFindPic(384, 388, 653, 512, "reject.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->reject=" + result);
                publicFunction.rndTapWH(result.x, result.y, 25, 20);
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(540, 285, 663, 401, "wanted.png");
            if (result.sim >= 0.8) {
                //通缉
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->wanted=" + result);
                publicFunction.rndTapWH(result.x, result.y, 25, 20);
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(504, 237, 836, 370, "wanted1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->wanted1=" + result);
                publicFunction.rndTap(742, 436, 808, 465);//点同意
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(506, 230, 737, 349, "notOperation.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->notOperation=" + result);
                publicFunction.rndTap(735, 431, 803, 465);//点重连
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(1, 536, 145, 677, "post.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->post=" + result);
                Thread.sleep(2000);
                publicFunction.rndTap(606, 40, 624, 71);
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(0, 104, 82, 245, "arrow1.png");
            if (result.sim >= 0.7) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->arrow1=" + result);
                publicFunction.rndTapWH(result.x, result.y, 25, 41);
                mFairy.tap(result.x + 10, result.y + 10);
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(1037, 0, 1192, 99, "arrow2.png");
            if (result.sim >= 0.7) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->arrow2=" + result);
                time2x = System.currentTimeMillis() / 1000 - time2;
                if (time2x > 10) {
                    mFairy.tap(result.x + 10, result.y + 10);
                    Thread.sleep(500);
                }
            } else {
                time2 = System.currentTimeMillis() / 1000;
            }
            result = functionClass.rgbFindPic(384, 240, 890, 372, "apply.png");
            if (result.sim >= 0.8) {
                //烤火传功，传功结束后 如果对方不是好友 会弹出对话框询问是否添加好友。有人会在烤火结束之后同意传功，所有这个弹出框要做为异常处理
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->apply=" + result);
                publicFunction.rndTap(475, 430, 540, 471);//取消
                Thread.sleep(1000);
            }
            //登陆相关
            land2();
            //点击弹出
            clickPop2();
            //被游戏强制下线
            resetGame2();
            //进入防沉迷模式
            if (TaskMain.taskMap.get("timeoffline") == 1) {
                boolean resetTime = gamePublicFunction.determineResetTime();
                if (resetTime) {
                    LtLog.i(publicFunction.getLineInfo() + "-----*************----killGame---->=");
//                    mFairy.killUserGame();
                    killApp();
                    Thread.sleep(10000);
                    publicFunction.waitGameReset(1200);
                }
            }

            LtLog.i(publicFunction.getLineInfo() + "-----*************----test time---->=" + (System.currentTimeMillis() - time_test));
            if (snatch_picTime.getPicTime() >= 30) {
                LtLog.i(publicFunction.getLineInfo() + "*************************snatch_picTime > 60->");
                int x = snatch_picTime.getPicX() + 87;
                int y = snatch_picTime.getPicY() + 128;
                //点击确认，计算偏移
                LtLog.i(publicFunction.getLineInfo() + "*************************snatch_picTime > 60->" + ",x=" + x + ",y=" + y);
                publicFunction.rndTap(x, y, x + 10, y + 10);
                Thread.sleep(1000);
                snatch_picTime.resetTime();
            }
            if (createTeam2_picTime.getPicTime() >= 30) {
                LtLog.i(publicFunction.getLineInfo() + "*************************createTeam2_picTime > 60->");
                publicFunction.rndTap(727, 431, 808, 469);
                Thread.sleep(1000);
                createTeam2_picTime.resetTime();
            }
            Thread.sleep(100);
        }




    }

    public void clickPop2() throws Exception {
        //弹出框
        FunctionClass.ResultValue result;

        result = functionClass.rgbFindPic(386, 260, 850, 366, "WLMZover.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-----*************-----clickPop--->WLMZover=" + result);
            publicFunction.rndTap(604, 433, 665, 464); //点击确认
            Thread.sleep(500);
        }
        result = functionClass.rgbFindPic(707, 184, 803, 243, "privacy.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-----*************-----clickPop--->privacy=" + result);
            publicFunction.rndTap(420, 483, 475, 505);//点击关闭
            Thread.sleep(500);
        }
        for (int i = 0; i < 3; i++) {
            result = functionClass.rgbFindPic(413, 262, 730, 384, "pop1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-----clickPop--->pop1=" + result);
                publicFunction.rndTap(595, 434, 681, 462); //点击知道了
                Thread.sleep(500);
            }
            result = functionClass.rgbFindPic(774, 367, 1003, 518, "pop2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-----*************-----clickPop--->pop2=" + result);
                publicFunction.rndTap(965, 221, 994, 256);
                Thread.sleep(500);
            } else {
                break;
            }
            Thread.sleep(10);
        }





    }

    public void land2() throws Exception {
        FunctionClass.ResultValue result;
        //登陆相关
        result = functionClass.rgbFindPic(286, 744, 500, 1076, "Land.png" + "|" + "Land1.png" + "|" + "Land2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------Land=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(500);
        }
        result = functionClass.rgbFindPic(491, 614, 757, 691, "startUp1.png" + "|" + "startUp.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------startUp=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(500);
        }
        result = functionClass.rgbFindPic(189, 57, 442, 152, "notice.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------startUp=" + result);
            publicFunction.rndTap(601, 570, 659, 601);
            Thread.sleep(500);
        }
        result = functionClass.rgbFindPic(207, 24, 520, 143, "agreement.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------agreement=" + result);
            publicFunction.rndTap(340, 595, 397, 626);
            Thread.sleep(500);
        }
        result = functionClass.rgbFindPic(528, 427, 661, 659, "login.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------login=" + result);
            publicFunction.rndTapWH(result.x, result.y, 33, 32);
            Thread.sleep(500);
        }
        result = functionClass.rgbFindPic(1014, 587, 1147, 719, "login1.png|into4.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------login=" + result);
            publicFunction.rndTapWH(result.x, result.y, 33, 32);
            Thread.sleep(500);
        }
        result = functionClass.rgbFindPic(712, 462, 849, 666, "QQ.png" + "|" + "QQ1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------QQ=" + result);
            publicFunction.rndTapWH(result.x, result.y, 37, 43);
            Thread.sleep(500);
        }

        result = functionClass.rgbFindPic(116, 30, 339, 147, "jurisdiction.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------jurisdiction=" + result);
            //申请权限列表
            publicFunction.rndTapWH(608, 556, 63, 27);//点击确认
            Thread.sleep(500);
        }

    }

    private void resetGame2() throws Exception {
        FunctionClass.ResultValue result;
        result = functionClass.rgbFindPic(662, 629, 845, 711, "sleep.png");
        if (result.sim >= 0.9) {
            LtLog.i(publicFunction.getLineInfo() + "*******************--------------sleep=" + result);
            LtLog.i(publicFunction.getLineInfo() + "-----*************----killGame---->=");
            mFairy.killUserGame();
            Thread.sleep(10000);
            publicFunction.waitGameReset(100);
//            for (int i = 0; i < 10; i++) {
//                Thread.sleep(10000);
//                LtLog.i(publicFunction.getLineInfo() + "*******************--------------sleep=" + result);
//            }
        }
        result = functionClass.rgbFindPic(604, 248, 885, 382, "err5.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-----*************-------->err5=" + result);
            publicFunction.rndTap(609, 429, 672, 473);//点确认
            Thread.sleep(10000);
            LtLog.i(publicFunction.getLineInfo() + "-----*************----killGame---->=");
            mFairy.killUserGame();
            Thread.sleep(10000);
            LtLog.i(publicFunction.getLineInfo() + "-----*************----killGame---->=");
            publicFunction.waitGameReset(300);
//            for (int i = 0; i <30 ; i++) {
//                LtLog.i(publicFunction.getLineInfo() + "-----*************----sleep 5 Minute---->=" );
//                Thread.sleep(10000);
//            }
        }


    }



}