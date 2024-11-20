package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class LimitlessTask {//无限任务

    private AtFairyImpl mFairy;
    private FindResult result;
    private SingleTask singleTask;
    private GamePublicFuntion gamePublicFuntion;
    private int hour;
    private int minute;
    private int week;

    public LimitlessTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(mFairy);
        singleTask = new SingleTask(mFairy);
    }

    private int taskType = 1;
    private int ranksNum = 1;
    private String taskName = "";
    private boolean jzHan = false;

    private String[] taskNameArray = {};
    private boolean rset = true;

    abstract class limitlessTask extends TaskContent {

        public limitlessTask(AtFairyImpl mFairy) throws Exception {
            super(mFairy);
        }

        @Override
        void create() throws Exception {

            if (AtFairyConfig.getOption("han").equals("1")) {
                jzHan = true;
            }
        }

        @Override
        void init() throws Exception {
            gamePublicFuntion.init();
            gamePublicFuntion.ranksSetUp(1);
            setTaskName(1);

        }

        @Override
        void inOperation() throws Exception {
            result = mFairy.findPic("findWay.png");
            if (result.sim > 0.8f) {
                err = 0;
            }

            long l = mFairy.getColorNum(86, 661, 237, 668, "170,187,255", 0.98f);
            if (l > 50) {
                err = 0;
            }

            result = mFairy.findPic(782, 394, 1001, 570, "shiyong.png");
            mFairy.onTap(0.8f, result, "使用", 1000);

            result = mFairy.findPic(563, 602, 680, 693, "dian.png");
            mFairy.onTap(0.8f, result, 954, 585, 972, 599, "点击空白处关闭", 1000);

            result = mFairy.findPic(573, 71, 822, 230, "err4.png");
            if (result.sim > 0.85f) {
                mFairy.onTap(261, 107, 269, 121, "发现err4", 2000);
                mFairy.onTap(261, 107, 269, 121, "发现err4", 1000);
            }
        }

        @Override
        void content_01() throws Exception {
            timeCount(15, 0);
            result = mFairy.findPic(new String[]{"ranks.png", "ranks1.png"});
            mFairy.onTap(0.8f, result, "队伍", 1500);

            result = mFairy.findPic(new String[]{"UIranks.png", "UIranks1.png"});
            if (result.sim > 0.85f) {
                err = 0;
                result = mFairy.findPic("create.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "创建队伍", 1000);
                    rset = true;
                }
                result = mFairy.findPic("rank5.png");
                mFairy.onTap(0.9f, result, "信息", 1000);

                FindResult result1 = mFairy.findPic(942, 591, 1119, 685, "dd1.png");

                FindResult result2 = mFairy.findPic("tz1.png");
                if (result2.sim > 0.85f) {
                    twoJudgeCount = 0;
                }

                if (result1.sim > 0.85f && result2.sim < 0.85f) {


                    if (rset && AtFairyConfig.getOption("9283").equals("1")) {
                        rset = false;
                        mFairy.onTap(1164, 376, 1197, 417, "设置", 3000);

                        gamePublicFuntion.rdsq();
                        gamePublicFuntion.zrdz();

                        mFairy.onTap(1163, 286, 1191, 324, "返回队伍", 500);
                        mFairy.onTap(1163, 286, 1191, 324, "返回队伍", 1000);
                    }


                    result = mFairy.findPic(268, 480, 1101, 531, "lx.png");
                    mFairy.onTap(0.82f, result, "离线", 2000);

                    gamePublicFuntion.pleaseLeaveRanks();


                    result = mFairy.findPic("cui.png");
                    if (result.sim > 0.85f) {
                        oneJudgeCount = 0;
                        if (ranksNum <= gamePublicFuntion.ranksGetNum()) {
                            gamePublicFuntion.close();
                            setTaskName(2);
                            gamePublicFuntion.ranksSetUp(1);
                            return;
                        }

                        FindResult result3 = mFairy.findPic("Yelling.png");
                        if (result3.sim > 0.85f) {
                            if (jzHan) {
                                result = mFairy.findPic("han1.png");
                                mFairy.onTap(0.8f, result, "√", 1000);
                            } else {
                                result = mFairy.findPic("han.png");
                                mFairy.onTap(0.8f, result, "", 1000);
                            }

                            mFairy.onTap(0.85f, result3, "喊话", 2000);
                        }

                    } else {
                        if (oneJudgeCount(2)) {
                            result = mFairy.findPic("target.png");
                            mFairy.onTap(0.85f, result, "调整目标", 1000);
                        }
                    }

                } else {
                    if (twoJudgeCount(10)) {
                        result = mFairy.findPic("td.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "退出队伍", 1500);
                            mFairy.onTap(853, 506, 855, 510, "确定", 1000);
                        }
                    }
                }
            }
        }
    }

    private int map = 0;
    private TaskContent.Slide mapSlide;
    private TaskContent.Slide slideActivity;
    private TaskContent.Time timeRanks;
    private TaskContent.Time timeLing;
    private boolean lingBools = false;
    private int moveRole = 0;
    private int m = 0;

    public void lingshuang() throws Exception {
        int err = 0;
        gamePublicFuntion.close();
        while (mFairy.condit()) {
            err++;
            Thread.sleep(1000);
            if (err > 10) {
                return;
            }

            result = mFairy.findPic("activity.png");
            if (result.sim > 0.8f) {
                err = 0;
                mFairy.onTap(0.8f, result, "活动", 3000);
            }

            result = mFairy.findPic(new String[]{"UIactivity.png", "UIactivity1.png"});
            if (result.sim > 0.8f) {
                result = mFairy.findPic("activities.png");
                mFairy.onTap(0.9f, result, "全天活动", 1500);


                FindResult result1 = mFairy.findPic(117, 172, 842, 547, "gj.png");
                if (result1.sim > 0.88f) {
                    result = mFairy.findPic(result1.x + 133, result1.y + 22,
                            result1.x + 259, result1.y + 110, "pp.png");
                    mFairy.onTap(0.8f, result, "参加", 1500);

                }

                if (err == 2 || err == 4 || err == 6 || err == 8) {
                    mFairy.ranSwipe(499, 235, 543, 430, 2, 1000, (long) 1500);
                    continue;
                }
            }

            result = mFairy.findPic("lingshuang.png");
            mFairy.onTap(0.85f, result, 386, 492, 458, 513, "组队玩法提示框", 1000);

            result = mFairy.findPic(499, 553, 707, 602, "lingshuang1.png");
            if (result.sim > 0.85f) {
                mFairy.onTap(0.85f, result, "开启30分钟修炼时间", 500);
                for (int i = 0; i < 10; i++) {
                    result = mFairy.findPic(675, 173, 745, 390, "ls.png");
                    if (result.sim > 0.85f) {
                        lingBools = false;
                        break;
                    }
                }
                mFairy.onTap(851, 85, 871, 103, "", 1000);
                return;
            }
        }
    }//领双


    public void hangUp() throws Exception {
        new limitlessTask(mFairy) {
            long timeRecord;
            long timeNow;

            @Override
            void create() throws Exception {
                super.create();

                sblingjiangli();


                TaskMain.TASKNAME = "野外挂机";
                ranksNum = 1;
                rset = true;


                //任务执行时间
                timeNow = System.currentTimeMillis();

                timeRecord = getTimeStamp(AtFairyConfig.getOption("timeRecord"));


                if (!AtFairyConfig.getOption("map").equals("")) {
                    map = Integer.parseInt(AtFairyConfig.getOption("map"));
                }

                if (AtFairyConfig.getOption("ls").equals("1")) {
                    lingBools = true;
                }
                mapSlide = new Slide(1032, 266, 1059, 596);
                timeRanks = new Time();
                timeLing = new Time();
                timeLing.setTime(System.currentTimeMillis() - 1800000);

                gamePublicFuntion.battleEnd();
                gamePublicFuntion.err();

                inOperation();
                sblingjiangli();

            }

            void init() throws Exception {
                super.init();
                rset = true;
                if (map == 0) {
                    setTaskName(3);
                }
            }

            void content_01() throws Exception {
                super.content_01();
                m = 0;
                gamePublicFuntion.selectionTask("team.png", "chxx.png");
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("main.png");
                mFairy.onTap(0.85f, result, 1246, 37, 1264, 59, "点击地图", 1000);

                result = mFairy.findPic("UImap.png");
                if (result.sim > 0.85f) {
                    if (m > 7) {
                        setTaskName(0);
                        return;
                    }
                    err = 0;
                    switch (map) {
                        case 1:
                        case 2:
                            if (gamePublicFuntion.map(1) == false) {
                                return;
                            }
                            break;
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 56:
                        case 57:
                        case 58:
                        case 59:
                        case 60:
                        case 61:
                        case 62:
                        case 63:
                        case 64:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                            if (gamePublicFuntion.map(2) == false) {
                                return;
                            }
                            break;
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                            if (gamePublicFuntion.map(3) == false) {
                                return;
                            }
                            break;
                        case 17:
                        case 18:
                        case 19:
                            if (gamePublicFuntion.map(7) == false) {
                                return;
                            }
                            break;
                        case 20:
                        case 21:
                            if (gamePublicFuntion.map(8) == false) {
                                return;
                            }
                            break;
                        case 22:
                        case 23:
                            if (gamePublicFuntion.map(9) == false) {
                                return;
                            }
                            break;
                        case 24:
                        case 25:
                            if (gamePublicFuntion.map(10) == false) {
                                return;
                            }
                            break;
                        case 26:
                        case 27:
                            if (gamePublicFuntion.map(11) == false) {
                                return;
                            }
                            break;
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                            if (gamePublicFuntion.map(12) == false) {
                                return;
                            }
                            break;
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                            if (gamePublicFuntion.map(13) == false) {
                                return;
                            }
                            break;
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                        case 55:
                            if (gamePublicFuntion.map(14) == false) {
                                return;
                            }
                            break;
                        case 42:
                        case 43:
                        case 44:
                        case 45:
                            if (gamePublicFuntion.map(15) == false) {
                                return;
                            }
                            break;
                        case 46:
                        case 47:
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:

                        case 78:
                        case 79:
                        case 80:
                        case 81:

                        case 73:
                            if (gamePublicFuntion.map(16) == false) {
                                return;
                            }
                            break;
                        case 53:
                        case 54:
                            if (gamePublicFuntion.map(17) == false) {
                                return;
                            }
                            break;

                        case 74:
                        case 75:
                            if (gamePublicFuntion.map(18) == false) {
                                return;
                            }
                            break;

                        case 76:
                        case 77:
                            if (gamePublicFuntion.map(19) == false) {
                                return;
                            }
                            break;

                    }
                    int i;
                    if (selectedMap()) {

                        switch (map) {
                            case 55://雷池随机打怪
                                i = new Random().nextInt(4);
                                i = i + 38;

                                result = mFairy.findPic(945, 102, 1178, 623, "map" + i + ".png");
                                if (result.sim > 0.85f) {
                                    mFairy.onTap(0.85f, result, "点击怪物", 1500);
                                    mFairy.onTap(1139, 60, 1159, 79, "", 1000);
                                    setTaskName(3);
                                    m = 0;
                                    return;
                                }
                                break;
                            case 73://魂天界随机打怪

                                int[] ran = {46,47,48,49,50,51,52,78,79,80,81};

                                int index = new Random().nextInt(ran.length);
                                i = ran[index];

                                result = mFairy.findPic(945, 102, 1178, 623, "map" + i + ".png");
                                if (result.sim > 0.85f) {
                                    mFairy.onTap(0.85f, result, "点击怪物", 1500);
                                    mFairy.onTap(1139, 60, 1159, 79, "", 1000);
                                    setTaskName(3);
                                    m = 0;
                                    return;
                                }
                                break;
                            default:
                                result = mFairy.findPic(945, 102, 1178, 623, "map" + map + ".png");
                                if (result.sim > 0.85f) {
                                    mFairy.onTap(0.85f, result, "点击怪物", 1500);
                                    mFairy.onTap(1139, 60, 1159, 79, "", 3000);
                                    gamePublicFuntion.close();
                                    setTaskName(3);
                                    m = 0;
                                    return;
                                }
                                break;
                        }

                        m++;
                        mapSlide.slideRange(m, new int[]{2, 3, 4, 5}, 2, 0);
                    }
                }
            }

            void inOperation() throws Exception {
                super.inOperation();
                gamePublicFuntion.qx();
                gamePublicFuntion.chat();



                if (timeRanks.timeJudge(120000)) {
                    gamePublicFuntion.ranksSetUp(1);
                }


                if (timeRecord != -1) {
                    if (System.currentTimeMillis() - timeNow >= timeRecord) {
                        LtLog.e(mFairy.getLineInfo("时间到了"));
                        gamePublicFuntion.battleEnd();
                        gamePublicFuntion.err();

                        setTaskEnd();
                        return;
                    }
                }

                if (limitedTime()) {
                    create();
                    setTaskName(0);
                    return;
                }
            }

            void content_03() throws Exception {
                timeCount(80, 0);
                if (map != 0) {
                    gamePublicFuntion.battle(0);
                    if (lingBools) {
                        if (timeLing.timeJudge(1800000)) {
                            lingshuang();
                            gamePublicFuntion.close();
                        }
                    }

                } else {
                    err = 0;
                }


                sblingjiangli();

                if (timeMap("hongbao", getTimeStamp(AtFairyConfig.getOption("hongbaotime")), false)) {

                    err = 0;

                    for (int i = 0; i < 20; i++) {

                        Thread.sleep(1000);

                        LtLog.e(mFairy.getLineInfo("领取红包中"));


                        result = mFairy.findPic("hongbao1.png");
                        if (result.sim > 0.8f) {

                            for (int i1 = 0; i1 < 20; i1++) {

                                mFairy.onTap(264, 245, 291, 285, "", 500);

                                result = mFairy.findPic(614, 634, 745, 688, "hongbao2.png");
                                mFairy.onTap(0.8f, result, "继续", 500);

                            }

                            gamePublicFuntion.close();

                            break;
                        } else {
                            result = mFairy.findPic(313, 625, 528, 712, "hongbao.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "+", 1500);
                                mFairy.onTap(39, 485, 60, 509, "", 2000);
                            } else {
                                gamePublicFuntion.close();
                                mFairy.onTap(606, 680, 623, 692, "打开聊天", 2500);

                                result = mFairy.findPic(14, 111, 107, 326, "hongbao3.png");
                                mFairy.onTap(0.8f, result, "jiazu", 1500);
                            }
                        }


                    }

                }


                if (mFairy.dateHour() == 23) {
                    if (oneSecond()) {
                        singleTask.signIn();
                        gamePublicFuntion.close();
                        create();
                    }
                }

                result = mFairy.findPic(271, 64, 459, 217, "gj2.png");
                if (result.sim > 0.8f) {

                    if (AtFairyConfig.getOption("fnhl").equals("1")) {
                        if (timeMap("fn", 30000)) {
                            mFairy.onTap(949, 662, 959, 672, "佛怒火莲", 1000);
                        }
                    }

                    err = 0;
                }

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    setTaskName(2);
                    return;
                }
            }

            void sblingjiangli() throws Exception {

                if (timeMap("sblingjiangli", getTimeStamp(AtFairyConfig.getOption("sblingjianglitime")), true)) {
                    singleTask.sbling1();
                    singleTask.sbling2();
                    gamePublicFuntion.init();
                }

                if (timeMap("sblingdxzl", getTimeStamp(AtFairyConfig.getOption("sblingdxzl")), true)) {
                    singleTask.sblingdxzl();
                    gamePublicFuntion.init();
                }

            }

        };
    }//野外挂机

    public boolean selectedMap() throws Exception {
        for (int i = 0; i < 3; i++) {

            result = mFairy.findPic(934, 50, 1237, 697, new String[]{"ji.png", "ji1.png"});
            if (result.sim > 0.85f) {
                return true;
            } else {
                result = mFairy.findPic(1111, 95, 1173, 375, "g.png");
                mFairy.onTap(0.9f, result, "关闭所有栏", 1000);

                result = mFairy.findPic("gw.png");
                mFairy.onTap(0.85f, result, "怪物", 1500);
            }
        }
        return false;
    }

    private int go1 = 0, go2 = 0, go3 = 0, go4 = 0, go5 = 0, go6 = 0, go7 = 0, go8 = 0, go9 = 0,
            go10 = 0, go11 = 0, go12 = 0, go13 = 0, go14 = 0, go15 = 0, go16 = 0, go17 = 0, go18 = 0,
            go19 = 0, go20 = 0, go21 = 0, go22 = 0, go23 = 0, go24 = 0, go25 = 0;

    public void initLimitedTime() {
        go1 = 0;
        go2 = 0;
        go3 = 0;
        go4 = 0;
        go5 = 0;
        go6 = 0;
        go7 = 0;
        go8 = 0;
        go9 = 0;
        go10 = 0;
        go11 = 0;
        go12 = 0;
        go13 = 0;
        go14 = 0;
        go15 = 0;
        go16 = 0;
        go17 = 0;
        go18 = 0;
        go19 = 0;
        go20 = 0;
        go21 = 0;
        go22 = 0;
        go23 = 0;
        go24 = 0;
        go25 = 0;
    }

    public boolean limitedTime() throws Exception {
        minute = mFairy.dateMinute();
        hour = mFairy.dateHour();
        if (hour == 24 || hour == 0) {
            initLimitedTime();
        }

        if (hour == 17) {
            go1 = 0;
            go11 = 0;
        }

        boolean go = false;
        while (true) {



            //赛季活动

            if (true && AtFairyConfig.getOption("dsyjsj").equals("1") && go2 == 0) {//赛季----斗圣遗迹
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((week == 2 || week == 6 ) &&
                        ((hour == 12 && minute>=30) || hour == 13 || hour==21 || hour==22)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    LimitlessTask.SJ_BOOLEAN = true;
                    if (AtFairyConfig.getOption("qptd").equals("1")) {
                        dsyj(0);
                    } else {
                        dsyj(1);
                    }
                    LimitlessTask.SJ_BOOLEAN = false;
                    go2 = 1;
                    go = true;
                    continue;
                }
            }

            if (true && AtFairyConfig.getOption("zzxdzsj").equals("1") && go3 == 0) {//赛季----中州浮岛战
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((week == 4 || week == 7) &&
                                ((hour == 12 && minute>=30) || hour == 13 || hour==21 || hour==22)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    LimitlessTask.SJ_BOOLEAN = true;

                    if (AtFairyConfig.getOption("qptd").equals("1")) {
                        zzxdz(0);
                    } else {
                        zzxdz(1);
                    }
                    LimitlessTask.SJ_BOOLEAN = false;
                    go3 = 1;
                    go = true;
                    continue;
                }
            }

            if (false && AtFairyConfig.getOption("jhyhsj").equals("1") && go24 == 0) {//赛季----净化妖火
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (week == 5 &&
                        hour == 20 && minute < 30) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.initGo();
                    LimitlessTask.SJ_BOOLEAN = true;
                    jhyh();
                    LimitlessTask.SJ_BOOLEAN = false;
                    go24 = 1;
                    go = true;
                    continue;
                }
            }




            if (AtFairyConfig.getOption("msxs").equals("1") && go1 == 0) {//魔兽悬赏
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((hour == 12 /*|| hour == 20*/) &&
                        (minute < 30)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    msxs();
                    go1 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("tmsl").equals("1") && go11 == 0) {//天墓试炼
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((week == 2) &&
                        ((hour == 11 && minute >= 29 && minute <= 35) || (hour == 20 && minute <= 5) || (hour == 19 && minute >= 59))) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    tmsl();
                    go11 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("dsyj").equals("1") && go2 == 0) {//斗圣遗迹
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((week == 2 || week == 6 ) &&
                        (hour == 21 || hour == 22 || (hour==12 && minute>=30) || hour==13)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();

                    if (AtFairyConfig.getOption("qptd").equals("1")) {
                        dsyj(0);
                    } else {
                        dsyj(1);
                    }

                    go2 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("zzxdz").equals("1") && go3 == 0) {//中州浮岛战
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (
                        (week == 4 || week == 7) &&
                                (hour == 21 || hour == 22 || (hour==12 && minute>=30) || hour==13)) {

                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    if (AtFairyConfig.getOption("qptd").equals("1")) {
                        zzxdz(0);
                    } else {
                        zzxdz(1);
                    }

                    go3 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("hnbls").equals("1") && go4 == 0) {//火能捕猎赛
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((week == 1 || week == 3) &&
                        (hour == 21 || hour == 22)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    if (AtFairyConfig.getOption("qptd").equals("1")) {
                        hnbls(0);
                    } else {
                        hnbls(1);
                    }

                    go4 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("wywdh").equals("1") && go5 == 0) {//外院武斗会
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((hour == 14 || hour == 15 || hour == 16 || hour == 17 || hour == 18 || hour==20)
                        && minute > 30) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();

                    if (AtFairyConfig.getOption("qptd").equals("1")) {
                        if (wywdh(0)) {
                            go5 = 1;
                        }
                    } else {
                        if (wywdh(1)) {
                            go5 = 1;
                        }
                    }


                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("myqq").equals("1") && go6 == 0) {//魔猿抢亲
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((week == 2 || week == 4 || week == 6) && (hour == 19)
                        && (minute >= 40 && minute <= 55)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.initGo();
                    myqq();
                    go6 = 1;
                    go = true;
                    continue;
                }

            }

            if (AtFairyConfig.getOption("zjly").equals("1") && go7 == 0) {//家族炼药
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((week == 1 || week == 3 || week == 5 || week == 7) && (hour == 19)
                        && (minute >= 40 && minute <= 55)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.initGo();
                    zjly();
                    go7 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("hdzz").equals("1") && go12 == 0) {//魂殿尊者
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((hour == 19) &&
                        (minute < 30)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    hdzz();
                    go12 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("hhdh").equals("1") && go8 == 0) {//花火大会
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((hour == 19) &&
                        (minute >= 15 && minute < 30)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.initGo();
                    hhdh();
                    go8 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("jzfz").equals("1") && go9 == 0) {//家族纷争
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((week == 2 || week == 4 || week == 6) &&
                        hour == 20 && minute < 12) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.initGo();
                    jzfz();
                    go9 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("jhyh").equals("1") && go24 == 0) {//净化妖火
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (week == 5 &&
                        hour == 20 && minute < 30) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.initGo();
                    jhyh();
                    go24 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("zjlx").equals("1") && go10 == 0) {//筑基灵修
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((hour == 19 && minute >= 30) || (hour >= 20 && hour <= 24)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.initGo();
                    zjlx();
                    go10 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("swd").equals("1") && go13 == 0) {//蛇王殿
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((week == 2 || week == 4 || week == 6) &&
                        ((hour == 20 && minute >= 30))) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();

                    if (AtFairyConfig.getOption("qptd").equals("1")) {
                        swd(0);
                    } else {
                        swd(1);
                    }

                    go13 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("lmssz").equals("1") && go14 == 0) {//联盟圣兽战
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((week == 2 || week == 4 || week == 6) &&
                        (hour == 21 && minute < 30)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    lmssz();
                    go14 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("lmgfz").equals("1") && go15 == 0) {//联盟攻防战
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((week == 3 || week == 7) &&
                        (hour == 21 && minute < 30)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    lmgfz();
                    go15 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("lmdqc").equals("1") && go16 == 0) {//联盟斗气车
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((week == 1 ) &&
                        (hour == 22 && minute < 30)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    lmdqc();
                    go16 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("lmldzb").equals("1") && go17 == 0) {//领地争霸
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((hour == 20 && minute >= 30 && minute < 42)) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    lmldzb();
                    go17 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("lmyhxs").equals("1") && go18 == 0) {//异火线索
                hour = mFairy.dateHour();
                if (hour == 12 || hour == 13 || hour == 18 || hour == 19) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    singleTask.lmyhxs();
                    go18 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("lmdsmw").equals("1") && go19 == 0) {//斗圣秘闻
                hour = mFairy.dateHour();
                if (hour == 12 || hour == 13 || hour == 18 || hour == 19) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    singleTask.lmdsmw();
                    go19 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("lmlmct").equals("1") && go20 == 0) {//联盟刺探
                hour = mFairy.dateHour();
                if (hour == 12 || hour == 13 || hour == 18 || hour == 19) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    singleTask.lmlmct();
                    go20 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("lmhb").equals("1") && go23 == 0) {//联盟日常任务
                hour = mFairy.dateHour();
                if (hour == 12 || hour == 13 || hour == 18 || hour == 19) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    singleTask.lmhb();
                    go23 = 1;
                    go = true;
                    continue;
                }
            }


            if (AtFairyConfig.getOption("3239").equals("1") && go22 == 0) {//联盟强者
                hour = mFairy.dateHour();
                if (hour == 10 || hour == 18) {
                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    lmqz();
                    go22 = 1;
                    go = true;
                    continue;
                }
            }

            if (AtFairyConfig.getOption("jjqz").equals("1") && go25 == 0) {//精英强者
                hour = mFairy.dateHour();

                if (hour >= 14 && hour < 24 && hour!=19) {

                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    jjqz();
                    go25 = 1;
                    go = true;
                    continue;
                }
            }




            if (((hour == 10 || hour == 12 || hour == 14 || hour == 16 || hour == 22) && minute >= 57)
                    || ((hour == 11 || hour == 13 || hour == 15 || hour == 17 || hour == 23) && minute < 15)) {

                if (AtFairyConfig.getOption("3237").equals("1") && go21 == 0) {//携宝强者亡墓洞穴

                    gamePublicFuntion.battleEnd();
                    gamePublicFuntion.err();
                    xbqz();
                    go21 = 1;
                    go = true;
                    continue;
                }
            } else {
                go21 = 0;
            }




            return go;
        }
    }//限时任务

    /*** 限时任务*/
    private boolean mReturn = false;

    private int ranksType = 1;

    abstract class limitedTime extends TaskContent {

        public limitedTime(AtFairyImpl mFairy) throws Exception {
            super(mFairy);
        }

        @Override
        void create() throws Exception {
            slideActivity = new Slide(499, 235, 543, 430);
            mReturn = false;
            timeRanks = new Time();
        }

        @Override
        void init() throws Exception {
            gamePublicFuntion.init();
            setTaskName(1);
        }

        @Override
        void inOperation() throws Exception {
            result = mFairy.findPic("findWay.png");
            if (result.sim > 0.8f) {
                err = 0;
            }

            long l = mFairy.getColorNum(86, 661, 237, 668, "170,187,255", 0.95f);
            if (l > 30) {
                err = 0;
            }

            result = mFairy.findPic(782, 394, 1001, 570, "shiyong.png");
            mFairy.onTap(0.8f, result, "使用", 1000);

            result = mFairy.findPic(563, 602, 680, 693, "dian.png");
            mFairy.onTap(0.8f, result, 954, 585, 972, 599, "点击空白处关闭", 1000);

            result = mFairy.findPic(573, 71, 822, 230, "err4.png");
            if (result.sim > 0.85f) {
                mFairy.onTap(261, 107, 269, 121, "发现err4", 2000);
                mFairy.onTap(261, 107, 269, 121, "发现err4", 1000);
            }
        }

        void content_01() throws Exception {
            timeCount(15, 0);
            result = mFairy.findPic(new String[]{"ranks.png", "ranks1.png"});
            mFairy.onTap(0.8f, result, "队伍", 1500);

            result = mFairy.findPic(new String[]{"UIranks.png", "UIranks1.png"});
            if (result.sim > 0.85f) {
                err = 0;

                result = mFairy.findPic("create.png");
                mFairy.onTap(0.85f, result, "创建队伍", 1000);

                result = mFairy.findPic("rank5.png");
                mFairy.onTap(0.9f, result, "信息", 1000);

                FindResult result1 = mFairy.findPic(942, 591, 1119, 685, "dd1.png");
                if (result1.sim > 0.85f) {
                    twoJudgeCount = 0;
                    result = mFairy.findPic(268, 480, 1101, 531, "lx.png");
                    if (result.sim > 0.82f) {
                        mFairy.onTap(0.82f, result, "离线", 2000);
                    } else {
                        setTaskName(2);
                        gamePublicFuntion.close();
                        return;
                    }
                    gamePublicFuntion.pleaseLeaveRanks();
                } else {
                    if (twoJudgeCount(3)) {
                        result = mFairy.findPic("td.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "退出队伍", 1500);
                            mFairy.onTap(853, 506, 855, 510, "确定", 1000);
                        }
                    }
                }
            }
        }

        void content_02() throws Exception {
            if (timeCount(20, 0)) {
                if (activityJudgeCount(2)) {
                    setTaskEnd();
                }
                return;
            }
            Thread.sleep(1500);

            result = mFairy.findPic("activity.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "活动", 1000);
                oneJudgeCount = 0;
            }
            result = mFairy.findPic(new String[]{"UIactivity.png", "UIactivity1.png"});
            if (result.sim > 0.8f) {
                switch (taskType) {
                    case 1:
                        result = mFairy.findPic("activities.png");
                        mFairy.onTap(0.9f, result, "全天活动", 1500);
                        break;
                    case 2:
                        result = mFairy.findPic("timeLimit.png");
                        mFairy.onTap(0.9f, result, "限时活动", 1500);
                        break;
                    case 4:
                        result = mFairy.findPic("sjact.png");
                        mFairy.onTap(0.9f, result, "赛季活动", 1500);
                        break;
                }

                result = mFairy.findPic(270, 548, 1145, 649, "huoyue.png");
                mFairy.onTap(0.92f, result, result.x + 25, result.y + 25, result.x + 35, result.y + 35, "领取活跃", 1000);


                FindResult result1;
                if (taskName.equals("wy1.png")) {
                    result1 = mFairy.findPic(117, 172, 842, 547, new String[]{"wy1.png", "wy4.png"});
                } else {

                    result1 = mFairy.findPic(117, 172, 842, 547, taskName);
                }

                if (result1.sim > 0.88f) {

                    result = mFairy.findPic(result1.x + 133, result1.y + 22,
                            result1.x + 259, result1.y + 110, "pp.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "参加", 500);
                        if (gamePublicFuntion.unableFindWay()) {
                            gamePublicFuntion.err();
                        } else {
                            Thread.sleep(1000);
                            setTaskName(3);
                        }
                        activityJudgeCount = 0;
                        return;
                    }

                    result = mFairy.findPic(result1.x + 133, result1.y + 22,
                            result1.x + 259, result1.y + 110, "complete.png");
                    if (result.sim > 0.85f) {
                        LtLog.e("任务已完成");
                        mReturn = true;
                        setTaskEnd();
                    }

                    result = mFairy.findPic(result1.x + 133, result1.y + 22,
                            result1.x + 259, result1.y + 110, "kai.png");
                    if (result.sim > 0.85f) {
                        LtLog.e("任务未开启");
                        setTaskEnd();
                    }
                }

                slideActivity.slideRange(new int[]{4, 6, 8, 10}, 2);
            } else {
                if (oneJudgeCount(3)) {
                    setTaskName(0);
                    return;
                }
            }
        }
    }

    static boolean SJ_BOOLEAN = false;

    public void msxs() throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "魔兽悬赏";
                ranksType = 1;
                taskType = 2;
                taskName = "msxs.png";
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                result = mFairy.findPic(738, 410, 947, 591, "msxs2.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "确定", 1000);
                } else {
                    gamePublicFuntion.qx();
                }

                minute = mFairy.dateMinute();
                if (minute > 30) {
                    result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                    if (result.sim > 0.85f) {
                        LtLog.e(mFairy.getLineInfo("在副本中等待结束..."));
                        threeJudgeCount = 0;
                    } else {
                        if (threeJudgeCount(6)) {
                            LtLog.e(mFairy.getLineInfo("已经达到副本结束时间,End!"));
                            setTaskEnd();
                            return;
                        }
                    }
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(15, 0);
                Thread.sleep(500);

                result = mFairy.findPic("tackChat.png");
                mFairy.onTap(0.85f, result, "任务聊天", 1000);

                result = mFairy.findPic("ai.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "爱的守护地", 1000);

                    for (int i = 0; i < 10; i++) {

                        result = mFairy.findPic("moshou1.png");
                        if (result.sim > 0.85f) {
                            for (int j = 0; j < 3; j++) {
                                result = mFairy.findPic("moshou2.png");
                                if (result.sim > 0.85f) {
                                    mFairy.onTap(0.85f, result, "费雷", 500);
                                    mFairy.onTap(1145, 64, 1162, 83, "", 500);
                                    return;
                                } else {
                                    result = mFairy.findPic(1111, 95, 1173, 375, "g.png");
                                    mFairy.onTap(0.9f, result, "关闭所有栏", 1000);

                                    result = mFairy.findPic("moshou3.png");
                                    mFairy.onTap(0.9f, result, "怪物", 1500);
                                }
                            }
                        } else {
                            mFairy.onTap(1226, 23, 1244, 48, "点击地图", 1500);
                        }

                    }

                }


                result = mFairy.findPic(new String[]{"msxs1.png", "msxs5.png"});
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(0.85f, result, "开始挑战", 500);
                    if (mapCount(0.8f, "msxsEnd.png")) {
                        setTaskEnd();
                        return;
                    }
                } else {
                    gamePublicFuntion.chat();
                }

                result = mFairy.findPic("msxs4.png");
                mFairy.onTap(0.8f, result, "原地复活", 1000);

                result = mFairy.findPic(560, 470, 731, 537, "fdz1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "安全复活", 1000);
                    oneJudgeCount = 0;
                    err = 0;
                }

                result = mFairy.findPic("map.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(272, 548, 275, 550, "点击下一层", 2000);
                    mFairy.onTap(1149, 71, 1150, 73, "关闭", 6500);
                    for (int i = 0; i < 20; i++) {
                        result = mFairy.findPic("auto.png");
                        if (result.sim > 0.95f) {
                            mFairy.onTap(0.95f, result, "自动战斗", 1000);
                            break;
                        }
                    }
                    err = 0;
                    oneJudgeCount = 0;
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    gamePublicFuntion.battle(1);
                    result = mFairy.findPic(325, 93, 396, 168, "msxs3.png");
                    if (result.sim > 0.8f) {
                        oneJudgeCount = 0;
                    } else {
                        if (oneJudgeCount == 2) {
                            mFairy.touchDown(1, 165, 580);
                            mFairy.touchMove(1, 299, 480, 1000);
                            Thread.sleep(2000);
                            mFairy.touchUp(1);
                            mFairy.onTap(1147, 399, 1150, 400, "战斗异常", 1000);
                        }

                        if (oneJudgeCount(4)) {
                            mFairy.onTap(1202, 68, 1205, 70, "点地图", 2000);
                        }
                    }
                }
                result = mFairy.findPic("msxsEnd1.png");
                mFairy.onTap(0.85f, result, 1148, 594, 1150, 595, "挑战成功,End!", 1000);
            }
        };
    }//魔兽悬赏

    public void dsyj(int i) throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "斗圣遗迹"+SJ_BOOLEAN;
                ranksType = 1;
                if(SJ_BOOLEAN) {
                    taskType = 4;
                    taskName = "dsyjSJ.png";
                }else {
                    taskType = 2;
                    taskName = "dsyj.png";
                }
                gamePublicFuntion.goWTC();

            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (hour == 14 || hour == 23) {
                    result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                    if (result.sim > 0.85f) {
                        LtLog.e(mFairy.getLineInfo("在副本中等待结束..."));
                        threeJudgeCount = 0;
                    } else {
                        if (threeJudgeCount(6)) {
                            LtLog.e(mFairy.getLineInfo("已经达到副本结束时间,End!"));
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic("myf.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("名誉分不足,End!"));
                    setTaskEnd();
                    return;
                }

            }

            void content_01() throws Exception {
                timeCount(15, 0);

                if (i == 0) {
                    result = mFairy.findPic(new String[]{"ranks.png", "ranks1.png"});
                    mFairy.onTap(0.8f, result, "队伍", 1500);

                    result = mFairy.findPic(new String[]{"UIranks.png", "UIranks1.png"});
                    if (result.sim > 0.85f) {
                        err = 0;

                        result = mFairy.findPic("td.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "退出队伍", 1500);
                            mFairy.onTap(853, 506, 855, 510, "确定", 1000);
                        }

                        setTaskName(2);
                        gamePublicFuntion.close();
                    }

                } else {
                    setTaskName(2);

                }

            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("Uifdz.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic("dsyj2.png");
                    if (result.sim > 0.85f) {
                    } else {
                        /*mFairy.onTap(1050, 636, 1052, 640, "组队匹配", 3000);*/
                        mFairy.onTap(818, 627, 860, 647, "单人匹配", 1000);
                    }
                }

                result = mFairy.findPic(522, 447, 763, 562, "dsyj5.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                result = mFairy.findPic(530, 448, 811, 580, "dsyj4.png");
                mFairy.onTap(0.8f, result, "确定进入", 1000);

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(741, 28, 742, 29, "塔", 5000);
                    gamePublicFuntion.battle(1);
                    Thread.sleep(3000);
                }

                result = mFairy.findPic("dsyj3.png");
                if (result.sim > 0.85f) {
                    err = 0;
                }

                result = mFairy.findPic(396, 195, 560, 267, "dsyjEnd.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1166, 610, 1168, 612, "斗圣遗迹完成,End!", 1000);
                    setTaskName(2);
                    gamePublicFuntion.init();
                    return;

                } else {
                    result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                    if (result.sim > 0.85f) {
                        err = 0;
                        mFairy.onTap(741, 28, 742, 29, "塔", 5000);
                        gamePublicFuntion.battle(1);
                        Thread.sleep(3000);
                    }
                }
            }
        };
    }//斗圣遗迹

    private int zzxdz = 0;

    public void zzxdz(int i) throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "中州浮岛战"+SJ_BOOLEAN;
                ranksType = 1;
                if(SJ_BOOLEAN) {
                    taskType = 4;
                    taskName = "fdzSJ.png";
                }else {
                    taskType = 2;
                    taskName = "fdz.png";
                }

                gamePublicFuntion.goWTC();
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (hour == 14 || hour == 23) {
                    result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                    if (result.sim > 0.85f) {
                        LtLog.e(mFairy.getLineInfo("在副本中等待结束..."));
                        threeJudgeCount = 0;
                    } else {
                        if (threeJudgeCount(6)) {
                            LtLog.e(mFairy.getLineInfo("已经达到副本结束时间,End!"));
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic("myf.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("名誉分不足,End!"));
                    setTaskEnd();
                    return;
                }

            }

            void content_01() throws Exception {
                timeCount(15, 0);

                if (i == 0) {
                    result = mFairy.findPic(new String[]{"ranks.png", "ranks1.png"});
                    mFairy.onTap(0.8f, result, "队伍", 1500);

                    result = mFairy.findPic(new String[]{"UIranks.png", "UIranks1.png"});
                    if (result.sim > 0.85f) {
                        err = 0;

                        result = mFairy.findPic("td.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "退出队伍", 1500);
                            mFairy.onTap(853, 506, 855, 510, "确定", 1000);
                        }

                        setTaskName(2);
                        gamePublicFuntion.close();
                    }

                } else {
                    setTaskName(2);

                }

            }

            @Override
            void content_03() throws Exception {
                timeCount(15, 0);

                Thread.sleep(1000);

                result = mFairy.findPic("Uifdz.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic("dsyj2.png");
                    if (result.sim > 0.85f) {
                    } else {
                        //mFairy.onTap(1050, 636, 1052, 640, "组队匹配", 3000);
                        mFairy.onTap(818, 627, 860, 647, "单人匹配", 1000);
                    }
                }
                result = mFairy.findPic(560, 470, 731, 537, "fdz1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    oneJudgeCount = 5;
                    mFairy.onTap(0.85f, result, "安全复活", 1000);
                }


                result = mFairy.findPic(546, 457, 763, 564, "fdz3.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(0.85f, result, "进入", 1000);
                }

                result = mFairy.findPic("fdzEnd.png");
                if (result.sim > 0.85f) {
                    Thread.sleep(5000);
                    mFairy.onTap(1167, 677, 1168, 678, "中州浮岛战完成!", 1000);
                    setTaskName(2);
                    gamePublicFuntion.init();
                    return;
                } else {
                    result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                    if (result.sim > 0.85f) {
                        err = 0;
                        gamePublicFuntion.whileContent();
                        gamePublicFuntion.battle(0);
                        result = mFairy.findPic("red.png");
                        if (result.sim < 0.85f) {
                            result = mFairy.findPic("fdz2.png");
                            mFairy.onTap(0.85f, result, "抢夺据点", 3500);
                        }

                        if (oneJudgeCount(3)) {
                            mFairy.onTap(1229, 46, 1253, 86, "点击地图", 1500);
                        }
                    }

                    result = mFairy.findPic(901, 566, 1021, 707, "wei.png");
                    if (result.sim > 0.8f) {
                        switch (zzxdz) {
                            case 0:
                                mFairy.onTap(335, 443, 353, 457, "点击结晶球", 1000);
                                zzxdz = 1;
                                break;
                            case 1:
                                mFairy.onTap(647, 310, 666, 334, "点击结晶球", 1000);
                                zzxdz = 0;
                                break;
                        }
                        mFairy.onTap(1141, 61, 1161, 79, "关闭", 3000);
                    }
                }

            }
        };
    }//中州浮岛战

    public void hnbls(int i) throws Exception {
        new limitedTime(mFairy) {
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "火能捕猎赛";
                taskType = 2;
                ranksType = 1;
                taskName = "bls.png";
                tm = new HashMap<>();
                gamePublicFuntion.goWTC();
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (hour == 14 || hour == 23) {
                    result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                    if (result.sim > 0.85f) {
                        LtLog.e(mFairy.getLineInfo("在副本中等待结束..."));
                        threeJudgeCount = 0;
                    } else {
                        if (threeJudgeCount(6)) {
                            LtLog.e(mFairy.getLineInfo("已经达到副本结束时间,End!"));
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic("myf.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("名誉分不足,End!"));
                    setTaskEnd();
                    return;
                }

            }

            void content_01() throws Exception {
                timeCount(15, 0);

                if (i == 0) {
                    result = mFairy.findPic(new String[]{"ranks.png", "ranks1.png"});
                    mFairy.onTap(0.8f, result, "队伍", 1500);

                    result = mFairy.findPic(new String[]{"UIranks.png", "UIranks1.png"});
                    if (result.sim > 0.85f) {
                        err = 0;

                        result = mFairy.findPic("td.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "退出队伍", 1500);
                            mFairy.onTap(853, 506, 855, 510, "确定", 1000);
                        }

                        setTaskName(2);
                        gamePublicFuntion.close();
                    }

                } else {
                    setTaskName(2);

                }

            }

            @Override
            void content_03() throws Exception {
                timeCount(15, 0);
                Thread.sleep(1000);
                result = mFairy.findPic("UIbls.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic("dsyj2.png");
                    if (result.sim > 0.85f) {

                    } else {
                        //mFairy.onTap(1050, 636, 1052, 640, "组队匹配", 3000);
                        mFairy.onTap(818, 627, 860, 647, "单人匹配", 1000);
                    }
                }

                result = mFairy.findPic(525, 451, 765, 563, "bls2.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "随机复活", 1000);
                    err = 0;
                }

                result = mFairy.findPic(546, 457, 763, 564, "fdz3.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(0.85f, result, "进入", 1000);
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    if (timeMap("move", 30000)) {
                        mFairy.ranSwipe(190, 571, 312, 598, 1, 500, (long) 800);
                        mFairy.ranSwipe(57, 570, 175, 600, 3, 500, (long) 800);
                    }


                    result = mFairy.findPic(989, 13, 1095, 118, "bls1.png");
                    mFairy.onTap(0.85f, result, "抢夺据点", 1000);

                    gamePublicFuntion.battle(0);
                }

                result = mFairy.findPic("blsEnd.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(626, 686, 630, 690, "火能捕猎赛完成!", 3000);

                    result = mFairy.findPic("blsEnd1.png");
                    mFairy.onTap(0.85f, result, 1162, 605, 1165, 607, "获得奖励", 1000);
                    setTaskName(2);
                    gamePublicFuntion.init();
                    return;
                }
                result = mFairy.findPic("blsEnd1.png");
                if (result.sim > 0.9f) {
                    mFairy.onTap(0.85f, result, 1162, 605, 1165, 607, "获得奖励", 1000);
                    setTaskName(2);
                    gamePublicFuntion.init();
                    return;
                }

            }

        };
    }//火能捕猎赛

    public boolean wywdh(int i) throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "外院武斗会";
                taskType = 2;
                ranksType = 1;
                taskName = "wy1.png";
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (((hour == 15 || hour == 16 || hour == 17 || hour == 18) && minute < 30) || hour == 19 || hour == 21) {
                    result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                    if (result.sim > 0.85f) {
                        LtLog.e(mFairy.getLineInfo("在副本中等待结束..."));
                        threeJudgeCount = 0;
                    } else {
                        if (threeJudgeCount(6)) {
                            LtLog.e(mFairy.getLineInfo("已经达到副本结束时间,End!"));
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic("myf.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("名誉分不足,End!"));
                    setTaskEnd();
                    return;
                }

            }

            void content_01() throws Exception {


                if (i == 0) {
                    timeCount(15, 0);

                    result = mFairy.findPic(new String[]{"ranks.png", "ranks1.png"});
                    mFairy.onTap(0.8f, result, "队伍", 1500);

                    result = mFairy.findPic(new String[]{"UIranks.png", "UIranks1.png"});
                    if (result.sim > 0.85f) {
                        err = 0;

                        result = mFairy.findPic("td.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "退出队伍", 1500);
                            mFairy.onTap(853, 506, 855, 510, "确定", 1000);
                        }

                        setTaskName(2);
                        gamePublicFuntion.close();
                    }

                } else {
                    super.content_01();
                }

            }

            @Override
            void content_03() throws Exception {
                timeCount(30, 0);
                Thread.sleep(1000);

                result = mFairy.findPic(new String[]{"wy2.png", "ljwy.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("dsyj2.png");
                    if (result.sim > 0.85f) {

                    } else {
                        mFairy.onTap(1050, 636, 1052, 640, "组队匹配", 3000);
                        mFairy.onTap(818, 627, 860, 647, "单人匹配", 1000);
                    }
                }

                result = mFairy.findPic(539, 501, 765, 584, "wy3.png");
                mFairy.onTap(0.85f, result, "进入", 2000);

                result = mFairy.findPic(560, 470, 731, 537, "fdz1.png");
                if (result.sim > 0.85f) {
                    oneJudgeCount = 5;
                    mFairy.onTap(0.85f, result, "安全复活", 1000);
                }

                result = mFairy.findPic("wyEnd.png");
                if (result.sim > 0.85f) {
                    Thread.sleep(5000);
                    mFairy.onTap(1167, 677, 1168, 678, "外院武斗会结束,End!", 1000);
                    setTaskName(2);
                    gamePublicFuntion.init();
                    return;
                } else {
                    result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                    if (result.sim > 0.85f) {

                        result = mFairy.findPic(52, 165, 289, 191, "qiu.png");
                        mFairy.onTap(0.8f, result, 142, 211, 168, 227, "", 1000);

                        gamePublicFuntion.whileContent();
                        gamePublicFuntion.battle(0);
                        err = 0;
                        if (oneJudgeCount(4)) {
                            mFairy.onTap(1229, 46, 1253, 86, "点击地图", 1000);
                        }
                    }

                    result = mFairy.findPic(901, 566, 1021, 707, "wei.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(520, 363, 550, 393, "点击地图中心", 1000);
                        mFairy.onTap(1143, 60, 1160, 83, "关闭", 3000);
                    }
                }
            }
        };

        return mReturn;

    }//外院武斗会

    /***家族限时任务*/
    public void hhdh() throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "花火大会";
                taskType = 2;
                ranksType = 2;
                taskName = "hhdh.png";
                tm = new HashMap<>();
            }

            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {
                    setTaskName(2);
                }
                judgeOneSecond = 0;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                result = mFairy.findPic("hhdh4.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 860, 503, 862, 505, "成为花神", 2000);
                } else {
                    gamePublicFuntion.qx();
                }

                gamePublicFuntion.chat();

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }

                minute = mFairy.dateMinute();
                if (minute >= 30) {
                    setTaskEnd();
                    return;
                }


            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("玩家没有加入家族,End!"));
                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic("hhdh6.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(603, 326, 605, 628, "选择区域", 1500);
                    mFairy.onTap(937, 603, 940, 605, "抛花", 1000);
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    if (judgeOneSecond()) {
                        mFairy.onTap(1201, 73, 1205, 75, "点击地图", 1500);
                        return;
                    }

                    if (timeMap("moveRole", 120000, false)) {

                        executeMoveRole();

                        gamePublicFuntion.ranksSetUp(1);
                    }

                }

                result = mFairy.findPic("UImap.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    judgeOneSecond = 1;
                    mFairy.onTap(578, 377, 590, 389, "点击中心位置", 1500);
                    mFairy.onTap(1146, 60, 1160, 78, "", 1500);
                }

                gamePublicFuntion.whileContent();

                result = mFairy.findPic("hhdh3.png");
                mFairy.onTap(0.82f, result, 523, 393, 525, 395, "默认选择答案A", 2000);

            }

        };
    }//花火大会

    private TaskContent.Time taskTime;

    public void executeMoveRole() throws Exception {

        switch (moveRole) {
            case 0:
                mFairy.ranSwipe(155, 555, 155, 575, 500, 1000);
                moveRole = 1;
                break;
            case 1:
                mFairy.ranSwipe(155, 555, 155, 535, 500, 1000);
                moveRole = 0;
                break;
        }
    }

    public void zjlx() throws Exception {
        new limitedTime(mFairy) {
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "筑基灵修";
                taskType = 2;
                ranksType = 2;
                taskName = "zjlx.png";
                taskTime = new Time();
                taskTime.setTime(System.currentTimeMillis() + 500000);

            }

            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {
                    setTaskName(2);
                }
            }

            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }

                if (taskTime.timeJudge(0)) {
                    LtLog.e(mFairy.getLineInfo("筑基灵修任务进行8分钟,End!"));
                    setTaskEnd();
                    return;
                }
            }

            void content_03() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("玩家没有加入家族,End!"));
                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    err = 0;


                    if (timeMap("moveRole", 30000, false)) {

                        executeMoveRole();

                        gamePublicFuntion.ranksSetUp(1);
                    }

                    result = mFairy.findPic("zjlxEnd.png");
                    if (result.sim > 0.88f) {
                        LtLog.e(mFairy.getLineInfo("已达到100%,End!"));
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(1120, 114, 1228, 205, "zjlx2.png");
                mFairy.onTap(0.85f, result, "缩放栏", 1000);
            }

        };
    }//筑基灵修

    public void myqq() throws Exception {
        new limitedTime(mFairy) {
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "魔猿抢亲";
                taskType = 2;
                ranksType = 2;
                taskName = "myqq.png";
                tm = new HashMap<>();
            }

            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {
                    setTaskName(2);
                }
                judgeOneSecond = 0;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    judgeOneSecond = 0;
                }
                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }

                minute = mFairy.dateMinute();
                if (minute > 55) {
                    result = mFairy.findPic(950, 131, 1273, 331, "myqq2.png");
                    if (result.sim < 0.85f) {
                        if (gamePublicFuntion.boss() == false) {
                            setTaskEnd();
                            return;
                        }
                    }

                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("玩家没有加入家族,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    if (judgeOneSecond()) {
                        mFairy.onTap(1201, 73, 1205, 75, "点击地图", 1500);
                        return;
                    }
                    gamePublicFuntion.battle(0);

                    if (timeMap("move", 50000)) {
                        mFairy.ranSwipe(190, 571, 312, 598, 1, 500, (long) 800);
                        mFairy.ranSwipe(57, 570, 175, 600, 3, 500, (long) 800);
                    }

                }

                result = mFairy.findPic("UImap.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    judgeOneSecond = 1;
                    mFairy.onTap(578, 377, 590, 389, "点击中心位置", 1500);
                    mFairy.onTap(1146, 60, 1160, 78, "", 3000);
                }

                if (timeMap("moveRole", 30000, false)) {

                    gamePublicFuntion.ranksSetUp(1);
                }

                gamePublicFuntion.whileContent();

                result = mFairy.findPic(518, 570, 769, 714, "myqqEnd.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "魔猿抢亲结束,End!", 1000);
                    setTaskEnd();
                    return;
                }

            }

        };
    }//魔猿抢亲

    public void zjly() throws Exception {
        new limitedTime(mFairy) {
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "家族炼药";
                taskType = 2;
                ranksType = 2;
                taskName = "jzly.png";
                tm = new HashMap<>();
            }

            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {
                    setTaskName(2);
                }
                judgeOneSecond = 0;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    judgeOneSecond = 0;
                }

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }

                minute = mFairy.dateMinute();
                if (minute > 55) {
                    result = mFairy.findPic(947, 123, 1237, 317, "jzly2.png");
                    if (result.sim < 0.85f) {
                        if (gamePublicFuntion.boss() == false) {
                            setTaskEnd();
                            return;
                        }
                    }
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("玩家没有加入家族,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    if (judgeOneSecond()) {
                        mFairy.onTap(1201, 73, 1205, 75, "点击地图", 1500);
                        return;
                    }

                    if (timeMap("move", 50000)) {
                        mFairy.ranSwipe(190, 571, 312, 598, 1, 500, (long) 800);
                        mFairy.ranSwipe(57, 570, 175, 600, 3, 500, (long) 800);
                    }

                    gamePublicFuntion.battle(0);
                }

                result = mFairy.findPic("UImap.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    judgeOneSecond = 1;
                    mFairy.onTap(578, 377, 590, 389, "点击中心位置", 1500);
                    mFairy.onTap(1146, 60, 1160, 78, "", 3000);
                }

                gamePublicFuntion.whileContent();

                result = mFairy.findPic("jzlyEnd.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(1171, 597, 1175, 600, "家族炼药完成,End!", 1000);
                    setTaskEnd();
                    return;
                }

                if (timeMap("moveRole", 30000, false)) {

                    gamePublicFuntion.ranksSetUp(1);
                }

                result = mFairy.findPic("jzlyEnd1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(1164, 606, 1165, 608, "获得奖励", 1000);
                    setTaskEnd();
                    return;
                }

            }

        };
    }//家族炼药

    public void jzfz() throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "家族纷争";
                taskType = 2;
                ranksType = 2;
                taskName = "jzfz.png";
            }

            @Override
            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {
                    setTaskName(2);
                }
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                result = mFairy.findPic(315, 312, 957, 544, "resurrection.png");
                mFairy.onTap(0.7f, result, "安全复活", 1000);

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }

                minute = mFairy.dateMinute();
                if (minute >= 27) {
                    setTaskEnd();
                    return;
                }


            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("玩家没有加入家族,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(1063, 46, 1065, 50, "点击据点", 1000);
                    gamePublicFuntion.battle(0);
                    Thread.sleep(5000);
                }

                result = mFairy.findPic("jzfzEnd.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(1171, 597, 1175, 600, "家族纷争完成,End!", 1000);
                    setTaskEnd();
                    return;
                }
            }
        };
    }//家族纷争

    public boolean jhyh = false;

    public void jhyh() throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "净化妖火"+SJ_BOOLEAN;

                ranksType = 2;
                if(SJ_BOOLEAN) {
                    taskType = 4;
                    taskName = "jhyhSJ.png";
                }else {
                    taskType = 2;
                    taskName = "jhyh.png";
                }

                jhyh = false;
            }

            @Override
            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {
                    setTaskName(2);
                }
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }

                hour = mFairy.dateHour();
                if (hour >= 21) {
                    setTaskEnd();
                    return;
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(15, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("玩家没有加入家族,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("jhyh1.png");
                mFairy.onTap(0.8f, result, "前往", 1000);

                result = mFairy.findPic("jhyh2.png");
                mFairy.onTap(0.8f, result, 847, 494, 872, 505, "确定进去", 5000);

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(71, 177, 99, 193, "点击据点", 1000);
                    gamePublicFuntion.battle(0);
                    Thread.sleep(5000);
                    jhyh = true;
                    frequencyInit("fbjh");
                } else {
                    if (jhyh) {
                        if (frequencyMap("fbjh", 10)) {
                            setTaskEnd();
                            return;
                        }
                    }
                }
            }
        };
    }//净化妖火

    public void tmsl() throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "天墓试炼";
                taskType = 2;
                ranksType = 2;
                taskName = "tmsl.png";

            }

            @Override
            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(1);
                // gamePublicFuntion.ranksSetUp(3);
               /* if (ranksType == 2) {
                    setTaskName(2);
                }*/
            }

            @Override
            void content_01() throws Exception {
                timeCount(15, 0);

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((hour == 11 && minute >= 30) || (hour == 20)) {
                    setTaskName(2);
                } else {
                    err = 0;
                    if (timeMap("zhao", 20000, false)) {
                        gamePublicFuntion.ranksSetUp(1);
                    }
                }
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                gamePublicFuntion.task();
                gamePublicFuntion.qx();


                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (hour == 12 || (hour == 20 && minute > 45)) {
                    result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                    if (result.sim > 0.85f) {
                        LtLog.e(mFairy.getLineInfo("在副本中等待结束..."));
                        threeJudgeCount = 0;
                    } else {
                        if (threeJudgeCount(6)) {
                            LtLog.e(mFairy.getLineInfo("已经达到副本结束时间,End!"));
                            setTaskEnd();
                            return;
                        }
                    }
                }
            }


            @Override
            void content_03() throws Exception {
                if (timeCount(10, 2)) {

                    hour = mFairy.dateHour();
                    minute = mFairy.dateMinute();
                    if ((hour == 11 && minute > 35) || (hour == 20 && minute > 5)) {

                        mFairy.onTap(1148, 588, 1150, 589, "点击空白处关闭", 1000);

                        LtLog.e(mFairy.getLineInfo("活动时间已经过了 没有进去到里面"));
                        setTaskEnd();
                        return;
                    }

                }

                Thread.sleep(1000);


                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("玩家没有加入家族,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(560, 470, 731, 537, "fdz1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(0.85f, result, "安全复活", 3000);
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.8f) {
                    frequencyInit("fbzj");
                    if (timeMap("moveRole", 30000, false)) {
                        executeMoveRole();
                    }

                    err = 0;
                } else {
                    if (frequencyMap("fbzj", 2)) {
                        result = mFairy.findPic("tmsl1.png");
                        if (result.sim > 0.75f) {
                            mFairy.onTap(0.75f, result, "开始挑战", 3000);
                        } else {
                            gamePublicFuntion.chat();

                            gamePublicFuntion.ranksSetUp(1);

                            result = mFairy.findPic("tmsl5.png");
                            mFairy.onTap(0.85f, result, "鼓足使者", 5000);
                        }
                    }
                }

                gamePublicFuntion.battle(0);
            }
        };
    }//天幕试炼

    public void hdzz() throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "魂殿尊者";
                taskType = 2;
                ranksType = 2;
                taskName = "hdzz.png";
            }

            @Override
            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {
                    setTaskName(2);
                }
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    setTaskName(2);
                    return;
                }

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 2);
                Thread.sleep(1000);

                gamePublicFuntion.battle(0);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("玩家没有加入家族,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("lmEnd.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("还没有加入联盟,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("hdzz1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic2(237, 124, 309, 697, "xbqz4.png");
                    if (result.sim > 0.85f) {
                        oneJudgeCount = 0;
                        mFairy.tap(result.x - 20, result.y + 20);
                        Thread.sleep(1500);
                        mFairy.onTap(924, 646, 960, 661, "前往", 2000);
                        return;
                    } else {
                        if (oneJudgeCount(3)) {
                            setTaskEnd();
                            return;
                        }
                    }
                }
            }
        };
    }//魂殿尊者

    private List<Integer> xbqz;
    private boolean boolxb = false;

    public void xbqz() throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "携宝强者";
                taskType = 1;
                ranksType = 2;

                xbqz = new ArrayList<Integer>();
                boolxb = false;
                for (int i = 1; i <= 20; i++) {
                    if (AtFairyConfig.getOption("xx" + i).equals("1")) {
                        xbqz.add(i);
                    }
                }

                if (xbqz.size() == 0) {
                    setTaskEnd();
                    return;
                }

                taskName = "xbqz.png";
            }

            @Override
            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {

                    gamePublicFuntion.close();

                    int errm = 0;


                    if (boolxb) {
                        setTaskName(2);
                        return;
                    }


                    xh:
                    while (mFairy.condit()) {

                        result = mFairy.findPic("main.png");
                        mFairy.onTap(0.85f, result, 1246, 37, 1264, 59, "点击地图", 1000);

                        result = mFairy.findPic("UImap.png");
                        if (result.sim > 0.85f) {
                            errm = 0;

                            switch (xbqz.get(0)) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:

                                    if (gamePublicFuntion.map(2)) {
                                        mFairy.tap(407, 338);
                                        break xh;
                                    }

                                    break;
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                    if (gamePublicFuntion.map(3)) {
                                        mFairy.tap(527, 344);
                                        break xh;
                                    }

                                    break;
                                case 11:
                                case 12:
                                    if (gamePublicFuntion.map(7)) {
                                        mFairy.tap(295, 338);
                                        break xh;
                                    }

                                    break;
                                case 13:
                                case 14:

                                    if (gamePublicFuntion.map(8)) {
                                        mFairy.tap(295, 338);
                                        break xh;
                                    }

                                    break;
                                case 15:
                                    if (gamePublicFuntion.map(9)) {
                                        mFairy.tap(295, 338);
                                        break xh;
                                    }
                                    break;
                                case 16:
                                case 17:
                                case 18:
                                case 19:
                                case 20:
                                    if (gamePublicFuntion.map(14)) {
                                        mFairy.tap(525, 379);
                                        break xh;
                                    }
                                    break;
                            }

                        }


                        Thread.sleep(1000);
                        errm++;
                        if (errm > 10) {
                            gamePublicFuntion.init();
                        }
                    }


                    while (mFairy.condit()) {
                        Thread.sleep(1000);
                        hour = mFairy.dateHour();
                        LtLog.e(mFairy.getLineInfo("等待时间到了"));
                        if (hour == 11 || hour == 13 || hour == 15 || hour == 17 || hour == 23) {
                            boolxb = true;
                            break;
                        }
                    }

                    gamePublicFuntion.close();

                    setTaskName(2);

                }


            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                gamePublicFuntion.qx();

                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    setTaskName(2);
                    return;
                }

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }
            }

            void other() throws Exception {

                for (int i = 0; i < 3; i++) {
                    result = mFairy.findPic(229, 105, 323, 702, "xbqz6.png");
                    mFairy.onTap(0.85f, result, "关闭所有", 1000);
                }

                mFairy.ranSwipe(192, 555, 192, 157, 500, 500);
                mFairy.ranSwipe(192, 555, 192, 157, 500, 500);
                mFairy.ranSwipe(192, 555, 192, 157, 500, 1000);

            }


            @Override
            void content_03() throws Exception {
                timeCount(10, 2);
                Thread.sleep(1000);

                gamePublicFuntion.battle(0);

                result = mFairy.findPic("xbqz5.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    if (oneSecond()) {
                        other();
                    }

                    switch (xbqz.get(0)) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            mFairy.onTap(166, 167, 223, 187, "魔兽山脉", 1000);

                            if (AtFairyConfig.getOption("xx1").equals("1")) {
                                mFairy.onTap(160, 253, 229, 268, "曼舞藤华", 1000);
                            } else if (AtFairyConfig.getOption("xx2").equals("1")) {
                                mFairy.onTap(171, 326, 228, 346, "合欢巨猿", 1000);
                            } else if (AtFairyConfig.getOption("xx3").equals("1")) {
                                mFairy.onTap(169, 411, 220, 426, "紫翼烈鹰", 1000);
                            } else if (AtFairyConfig.getOption("xx4").equals("1")) {
                                mFairy.onTap(164, 488, 224, 507, "遗弃玩偶", 1000);
                            } else if (AtFairyConfig.getOption("xx5").equals("1")) {
                                mFairy.onTap(162, 570, 223, 585, "寒齿巨狼", 1000);
                            }

                            break;
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:

                            mFairy.onTap(159, 250, 215, 267, "大沙漠", 1000);


                            if (AtFairyConfig.getOption("xx6").equals("1")) {
                                mFairy.onTap(157, 337, 220, 356, "黑煞冥龙", 1000);
                            } else if (AtFairyConfig.getOption("xx7").equals("1")) {
                                mFairy.onTap(151, 414, 226, 430, "噬魂刺客", 1000);
                            } else if (AtFairyConfig.getOption("xx8").equals("1")) {
                                mFairy.onTap(152, 487, 238, 515, "血魔一刀斩", 1000);
                            } else if (AtFairyConfig.getOption("xx9").equals("1")) {
                                mFairy.onTap(168, 568, 217, 587, "炎沙龙皇", 1000);
                            } else if (AtFairyConfig.getOption("xx10").equals("1")) {
                                mFairy.onTap(155, 646, 227, 659, "八翼蛇皇", 1000);
                            }

                            break;
                        case 11:
                        case 12:

                            mFairy.onTap(161, 331, 227, 347, "地心岩洞", 1000);


                            if (AtFairyConfig.getOption("xx11").equals("1")) {
                                mFairy.onTap(164, 417, 222, 434, "蒙面巫女", 1000);
                            } else if (AtFairyConfig.getOption("xx12").equals("1")) {
                                mFairy.onTap(159, 493, 243, 518, "游荡怨灵", 1000);
                            }

                            break;
                        case 13:
                        case 14:

                            mFairy.onTap(145, 417, 243, 437, "地心岩洞 二层", 1000);


                            if (AtFairyConfig.getOption("xx13").equals("1")) {
                                mFairy.onTap(158, 501, 229, 522, "双沟魂殿", 1000);
                            } else if (AtFairyConfig.getOption("xx14").equals("1")) {
                                mFairy.onTap(160, 577, 228, 601, "毒瘴傀儡", 1000);
                            }

                            break;
                        case 15:

                            mFairy.onTap(153, 506, 224, 520, "地心岩洞 三层", 1000);


                            if (AtFairyConfig.getOption("xx15").equals("1")) {
                                mFairy.onTap(159, 580, 202, 596, "神秘斗灵", 1000);
                            }

                            break;
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:

                            mFairy.onTap(160, 580, 233, 605, "虚空雷池", 1000);

                            for (int i = 0; i < 3; i++) {
                                mFairy.ranSwipe(168, 281, 202, 559, 2, 500, (long) 200);
                            }
                            Thread.sleep(1500);

                            if (AtFairyConfig.getOption("xx16").equals("1")) {
                                mFairy.onTap(161, 314, 228, 329, "搜魂傀儡", 1000);
                            } else if (AtFairyConfig.getOption("xx17").equals("1")) {
                                mFairy.onTap(159, 388, 229, 401, "魂殿密使", 1000);
                            } else if (AtFairyConfig.getOption("xx18").equals("1")) {
                                mFairy.onTap(160, 471, 228, 487, "虚无吞炎兽", 1000);
                            } else if (AtFairyConfig.getOption("xx19").equals("1")) {
                                mFairy.onTap(163, 550, 237, 570, "雷池魔灵", 1000);
                            } else if (AtFairyConfig.getOption("xx20").equals("1")) {
                                mFairy.onTap(161, 632, 238, 652, "虚空雷灵", 1000);
                            }
                            break;
                    }

                    result = mFairy.findPic("xbqz7.png");
                    if (result.sim > 0.85f) {
                        oneSecond = 0;
                        mFairy.onTap(0.85f, result, "前往", 5000);
                        return;
                    } else {
                        oneSecond = 0;
                        xbqz.remove(0);

                        if (xbqz.size() == 0) {
                            setTaskEnd();
                            return;
                        }
                    }
                } else {
                    oneSecond = 0;
                }
            }
        };
    }//携宝强者


    public void jjqz() throws Exception {
        new limitedTime(mFairy) {

            boolean ling = false;

            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "精英强者";
                taskType = 1;
                ranksType = 2;
                taskName = "jjqz.png";
                ling = false;
            }

            @Override
            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {
                    setTaskName(2);


                }
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                gamePublicFuntion.qx();

                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    setTaskName(2);
                    return;
                }

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }
            }

            void other() throws Exception {
                for (int i = 0; i < 3; i++) {
                    result = mFairy.findPic(229, 105, 323, 702, "xbqz6.png");
                    mFairy.onTap(0.85f, result, "关闭所有", 1000);
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 2);

                Thread.sleep(1000);

                if (mFairy.dateHour() > 23) {
                    setTaskEnd();
                    return;
                }

                FindResult qiangzhe = mFairy.findPic("jjqz1.png");
                if(result.sim>0.85f) {
                    if(ling){

                        //436,631
                        for (int i = 0; i < 5; i++) {
                            mFairy.onTap(362+(100*i),608,363+(100*i),609,"",500);
                            mFairy.onTap(362+(100*i),608,363+(100*i),609,"",1000);
                        }

                        mFairy.onTap(174,643,192,655,"",500);
                        mFairy.onTap(1155,58,1167,77,"",1000);
                        gamePublicFuntion.close();
                        ling=false;
                        return;
                    }

                    mFairy.onTap(0.85f, qiangzhe, "寻找强者", 2000);
                }

                result = mFairy.findPic("jjqz5.png");
                mFairy.onTap(0.85f, result, "强者积分", 2000);

                gamePublicFuntion.battle(0);

                result = mFairy.findPic(1112, 201, 1250, 652, "jjqz2.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("精英强者界面"));
                    err = 0;

                    result = mFairy.findPic(836, 611, 1051, 679, "jjqz3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.85f, result, "前往", 5000);
                        frequencyInit("jScene");
                        return;
                    }

                    if (frequencyMap("jScene", 5)) {
                        setTaskEnd();
                        return;
                    }

                }

                result = mFairy.findPic(88,170,233,209,"jjqz4.png");
                if (result.sim > 0.85f) {
                    oneSecond=0;
                    err = 0;
                }

                result = mFairy.findPic(97,246,220,287,"jjqz6.png");
                if (result.sim > 0.85f) {
                    if(oneSecond()){
                        ling=true;
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic(949, 416, 1272, 708, "yh.png");
                    if (result.sim < 0.85f) {
                        if(timeMap("deng",60000)){
                            for (int i = 0; i < 3; i++) {
                                mFairy.onTap(1144,604,1169,624,"",500);
                            }
                        }
                    }
                    err = 0;
                }


            }
        };
    }//精英强者

    private int lmqz = 1;

    public void lmqz() throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "联盟强者";
                taskType = 1;
                ranksType = 2;
                lmqz = 1;
                taskName = "lmqz.png";
            }

            @Override
            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {
                    setTaskName(2);
                }
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                gamePublicFuntion.qx();

                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    setTaskName(2);
                    return;
                }

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }
            }

            void other() throws Exception {
                for (int i = 0; i < 3; i++) {
                    result = mFairy.findPic(229, 105, 323, 702, "xbqz6.png");
                    mFairy.onTap(0.8f, result, "关闭所有", 1000);
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 2);
                Thread.sleep(1000);

                gamePublicFuntion.battle(0);

                result = mFairy.findPic("lmqz1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    if (oneSecond()) {
                        other();
                    }
                    switch (lmqz) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            result = mFairy.findPic(255, 151, 322, 201, "xbqz6.png");
                            if (result.sim < 0.85f) {
                                mFairy.onTap(166, 167, 223, 187, "太虚古龙", 1000);
                                return;
                            }

                            if (AtFairyConfig.getOption("ll1").equals("1") && lmqz == 1) {
                                mFairy.onTap(160, 253, 229, 268, "1", 1000);
                            } else if (AtFairyConfig.getOption("ll2").equals("1") && lmqz == 2) {
                                mFairy.onTap(171, 326, 228, 346, "2", 1000);
                            } else if (AtFairyConfig.getOption("ll3").equals("1") && lmqz == 3) {
                                mFairy.onTap(169, 411, 220, 426, "3", 1000);
                            } else if (AtFairyConfig.getOption("ll4").equals("1") && lmqz == 4) {
                                mFairy.onTap(164, 488, 224, 507, "4", 1000);
                            }

                            if (lmqz == 4) {
                                oneSecond = 0;
                            }
                            break;
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            result = mFairy.findPic(257, 239, 318, 281, "xbqz6.png");
                            if (result.sim < 0.85f) {
                                mFairy.onTap(159, 250, 215, 267, "丹塔会", 1000);
                                return;
                            }

                            if (AtFairyConfig.getOption("ll5").equals("1") && lmqz == 5) {
                                mFairy.onTap(157, 337, 220, 356, "5", 1000);
                            } else if (AtFairyConfig.getOption("ll6").equals("1") && lmqz == 6) {
                                mFairy.onTap(151, 414, 226, 430, "6", 1000);
                            } else if (AtFairyConfig.getOption("ll7").equals("1") && lmqz == 7) {
                                mFairy.onTap(152, 487, 238, 515, "7", 1000);
                            } else if (AtFairyConfig.getOption("ll8").equals("1") && lmqz == 8) {
                                mFairy.onTap(168, 568, 217, 587, "8", 1000);
                            }
                            if (lmqz == 8) {
                                oneSecond = 0;
                            }
                            break;
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                            result = mFairy.findPic(248, 317, 322, 369, "xbqz6.png");
                            if (result.sim < 0.85f) {
                                mFairy.onTap(161, 331, 227, 347, "星陨阁", 1000);
                                return;
                            }

                            if (AtFairyConfig.getOption("ll9").equals("1") && lmqz == 9) {
                                mFairy.onTap(157, 413, 215, 430, "5", 1000);
                            } else if (AtFairyConfig.getOption("ll10").equals("1") && lmqz == 10) {
                                mFairy.onTap(166, 493, 222, 512, "6", 1000);
                            } else if (AtFairyConfig.getOption("ll11").equals("1") && lmqz == 11) {
                                mFairy.onTap(157, 578, 218, 588, "7", 1000);
                            } else if (AtFairyConfig.getOption("ll12").equals("1") && lmqz == 12) {
                                mFairy.onTap(143, 644, 241, 666, "8", 1000);
                            }
                            break;
                    }

                    result = mFairy.findPic("xbqz7.png");
                    if (result.sim > 0.85f) {
                        oneSecond = 0;
                        mFairy.onTap(0.85f, result, "前往", 5000);
                        return;
                    }

                    lmqz++;
                    if (lmqz > 12) {
                        setTaskEnd();
                        return;
                    }
                }
            }
        };
    }//联盟强者

    public void swd(int i) throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "蛇王殿";
                taskType = 2;
                taskName = "swd.png";
            }

            @Override
            void init() throws Exception {
                super.init();
                //gamePublicFuntion.ranksSetUp(3);

            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                minute = mFairy.dateMinute();
                if (minute >= 58) {
                    gamePublicFuntion.close();
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("swd5.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 855, 505, 856, 507, "确定前往蛇王殿", 1000);
                } else {
                    gamePublicFuntion.qx();
                }

                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((hour == 21 && minute >= 10)) {
                    result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                    if (result.sim > 0.85f) {
                        LtLog.e(mFairy.getLineInfo("在副本中等待结束..."));
                        threeJudgeCount = 0;
                    } else {
                        if (threeJudgeCount(6)) {
                            LtLog.e(mFairy.getLineInfo("已经达到副本结束时间,End!"));
                            setTaskEnd();
                            return;
                        }
                    }
                }
            }

            void content_01() throws Exception {
                if (i == 0) {

                    timeCount(15, 0);

                    result = mFairy.findPic(new String[]{"ranks.png", "ranks1.png"});
                    mFairy.onTap(0.8f, result, "队伍", 1500);

                    result = mFairy.findPic(new String[]{"UIranks.png", "UIranks1.png"});
                    if (result.sim > 0.85f) {
                        err = 0;

                        result = mFairy.findPic("td.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "退出队伍", 1500);
                            mFairy.onTap(853, 506, 855, 510, "确定", 1000);
                        }

                        setTaskName(2);
                        gamePublicFuntion.close();
                    }

                } else {
                    setTaskName(2);
                    return;
                }

            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("swd5.png");
                mFairy.onTap(0.8f, result, 855, 505, 857, 507, "确定前往蛇王殿", 1000);


                result = mFairy.findPic("swd1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                }

                result = mFairy.findPic("swd6.png");
                mFairy.onTap(0.85f, result, "单人参加", 3000);

                result = mFairy.findPic("swd7.png");
                mFairy.onTap(0.85f, result, "小队参加", 3000);


                result = mFairy.findPic("swd2.png");
                mFairy.onTap(0.85f, result, 1164, 591, 1165, 592, "战斗结束", 1000);

                result = mFairy.findPic("swd3.png");
                mFairy.onTap(0.85f, result, "确定", 1000);

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    gamePublicFuntion.battle(1);

                    if (timeMap("moveRole", 10000, false)) {
                        executeMoveRole();
                    }

                }
            }
        };
    }//蛇王殿

    public void lmssz() throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "联盟圣兽战";
                taskType = 2;
                ranksType = 2;
                taskName = "ssz.png";
            }

            @Override
            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {
                    setTaskName(2);
                }
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    judgeOneSecond = 0;
                }
                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }

                minute = mFairy.dateMinute();
                if (minute > 30) {
                    setTaskEnd();
                    return;
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("玩家没有加入家族,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("lmEnd.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("还没有加入联盟,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(535, 509, 660, 603, "ssz3.png");
                mFairy.onTap(0.85f, result, "参战", 1000);


                result = mFairy.findPic(623, 81, 1128, 153, new String[]{"ssz1.png", "ssz2.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    if (oneJudgeCount(2)) {
                        mFairy.onTap(result.x + 0, result.y - 50, result.x + 1, result.y - 40, "点击敌方建筑", 2000);
                    }
                }
                gamePublicFuntion.whileContent();
                gamePublicFuntion.battle(0);

                result = mFairy.findPic(new String[]{"sszEnd.png", "sszEnd1.png"});
                if (result.sim > 0.85f) {
                    mFairy.onTap(1172, 691, 1175, 692, "联盟圣兽战完成,End!", 1000);
                    setTaskEnd();
                    return;
                }
            }

        };
    }//联盟圣兽战

    public void lmgfz() throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "联盟攻防战";
                taskType = 2;
                ranksType = 2;
                taskName = "gfz.png";
            }

            @Override
            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {
                    setTaskName(2);
                }
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    judgeOneSecond = 0;
                }
                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }

                minute = mFairy.dateMinute();
                if (minute > 30) {
                    setTaskEnd();
                    return;
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("玩家没有加入家族,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("lmEnd.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("还没有加入联盟,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(1120, 1, 1279, 34, "gfz1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(1024, 46, 1025, 48, "点击敌方首领", 1000);
                }
                gamePublicFuntion.whileContent();
                gamePublicFuntion.battle(0);

                result = mFairy.findPic("gfzEnd.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(1172, 691, 1175, 695, "联盟攻防战完成,End!", 1000);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(489, 601, 789, 716, new String[]{"jx.png", "jx1.png"});
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 1148, 588, 1150, 590, "继续", 1000);
                    mFairy.onTap(1172, 691, 1175, 695, "联盟攻防战完成,End!", 1000);
                    setTaskName(0);
                    return;
                }


            }

        };
    }//联盟攻防战

    public void lmdqc() throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "联盟斗气车";
                taskType = 2;
                ranksType = 2;
                taskName = "dqc.png";
            }

            @Override
            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {
                    setTaskName(2);
                }
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    judgeOneSecond = 0;
                }
                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }

                minute = mFairy.dateMinute();
                if (minute > 30) {
                    setTaskEnd();
                    return;
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("玩家没有加入家族,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("lmEnd.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("还没有加入联盟,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("dqc2.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    judgeOneSecond = 1;
                    mFairy.onTap(953, 534, 955, 535, "强化斗气车", 2000);
                    mFairy.onTap(861, 503, 865, 505, "确定强化", 2000);
                    mFairy.onTap(861, 503, 865, 505, "前往保护斗气车", 2000);
                    mFairy.onTap(861, 503, 865, 505, "传送", 2000);
                }


                result = mFairy.findPic(489, 601, 789, 716, new String[]{"jx.png", "jx1.png"});
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 1148, 588, 1150, 590, "继续", 1000);
                    mFairy.onTap(1172, 691, 1175, 695, "联盟攻防战完成,End!", 1000);
                    setTaskName(0);
                    return;
                }


                result = mFairy.findPic("dqc4.png");
                mFairy.onTap(0.85f, result, "强化聚气", 2000);

                result = mFairy.findPic(766, 452, 966, 560, "dqc5.png");
                mFairy.onTap(0.85f, result, "确定", 1000);

                result = mFairy.findPic(464, 547, 686, 682, "dqc1.png");
                mFairy.onTap(0.8f, result, "前往", 2000);


                result = mFairy.findPic(321, 353, 732, 630, "dqc3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (judgeOneSecond()) {
                        mFairy.onTap(0.8f, result, "点击斗气车", 1000);
                    }
                }

                result = mFairy.findPic("dqcEnd.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(1158, 617, 1160, 618, "联盟斗气车完成,End!", 1000);
                    setTaskName(0);
                    return;
                }

            }

        };
    }//联盟斗气车

    public void lmldzb() throws Exception {
        new limitedTime(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "领地争霸";
                taskType = 2;
                ranksType = 2;
                taskName = "mzzb.png";
            }

            @Override
            void init() throws Exception {
                super.init();
                gamePublicFuntion.ranksSetUp(3);
                if (ranksType == 2) {
                    setTaskName(2);
                }
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    judgeOneSecond = 0;
                }
                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(3);
                }

                minute = mFairy.dateMinute();
                if (minute > 42) {
                    setTaskEnd();
                    return;
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 99);
                Thread.sleep(1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("玩家没有加入家族,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("lmEnd.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("还没有加入联盟,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("tackChat.png");
                mFairy.onTap(0.85f, result, "任务聊天", 1000);

                result = mFairy.findPic("mzzb1.png");
                mFairy.onTap(0.85f, result, "进入战场", 2000);


                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    if (oneJudgeCount(2)) {
                        mFairy.onTap(1063, 46, 1065, 50, "点击据点", 1000);
                    }
                }
                gamePublicFuntion.whileContent();
                gamePublicFuntion.battle(0);


                result = mFairy.findPic("mzzb2.png");
                mFairy.onTap(0.85f, result, "确定", 1000);

                result = mFairy.findPic("jzfzEnd.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(1171, 597, 1175, 600, "盟主争霸完成,End!", 1000);
                    setTaskEnd();
                    return;
                }

            }

        };
    }//领地争霸


}