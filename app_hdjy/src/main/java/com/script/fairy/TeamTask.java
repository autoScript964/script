package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

/**
 * Created by user on 2019/6/3.
 */

public class TeamTask {
    public GamePublicFuntion gamePublicFuntion;
    public FindResult result;
    public AtFairyImpl mFairy;
    public SingleTask singleTask;
    private int hour;
    private int minute;
    private int week;
    public String activity_name;
    public int activity_type;
    public int ranks_num = 3;

    public TeamTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        singleTask = new SingleTask(ypFairy);
    }

    class TeamTask_Content extends TaskContent {

        public TeamTask_Content(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);
        }

        void create() throws Exception {
            super.create();
            singleTask.li_fb();
        }

        void init() throws Exception {
            gamePublicFuntion.init(true);
            setTaskName(1);
        }

        void content_01() throws Exception {
            if (timeCount(10, 0)) {
                if (frequencyMap("activity_end", 1)) {
                    setTaskEnd();
                    return;
                }
            }

            result = mFairy.findPic(795, 61, 1241, 310, "activity.png");
            if (result.sim > 0.8f) {
                err = 0;
                mFairy.onTap(0.8f, result, "活动", 1000);
            }

            result = mFairy.findPic("activity1.png");
            if (result.sim > 0.75f) {

                if (oneSecond()) {
                    mFairy.onTap(1220, 26, 1234, 40, "", 200);
                    mFairy.onTap(320, 23, 765, 50, "", 200);
                    mFairy.onTap(320, 23, 765, 50, "", 200);

                    gamePublicFuntion.activity_type(activity_type);
                }


                FindResult activity = mFairy.findPic(126, 70, 1191, 626, activity_name);
                if (activity.sim > 0.8f) {

                    if (activity_end(activity)) {
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(activity.x - 90, activity.y + 135, activity.x + 146, activity.y + 356, "qian.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(33, 18, 43, 50, "", 500);
                        setTaskName(2);
                        oneSecond = 0;
                        frequencyInit("activity_end");
                        return;
                    }
                }

                if (err == 3 || err == 5 || err == 7) {
                    mFairy.onTap(1219, 332, 1239, 350, "下一页活动", 1000);
                }

                return;
            }
        }

        void rank(String name, String str1, String str2, String str3) throws Exception {
            timeCount(10, 0);

            result = mFairy.findPic("rankScene.png");
            if (result.sim > 0.8f) {
                err = 0;
                result = mFairy.findPic(151, 55, 451, 140, name);
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(1044, 539, 1268, 661, "rank8.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(12, 552, 217, 670, "rank4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "退出队伍", 1000);
                            mFairy.onTap(727, 424, 757, 441, "", 500);
                            return;
                        }
                    } else {
                        result = mFairy.findPic(1044, 539, 1268, 661, "rank7.png");
                        mFairy.onTap(0.8f, result, "跟随队长", 500);

                        mFairy.onTap(32, 10, 50, 42, "", 500);

                        setTaskName(3);
                    }
                    return;
                } else {
                    if (frequencyMap("rank_err", 1)) {
                        result = mFairy.findPic(12, 552, 217, 670, "rank4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "退出队伍", 1000);
                            mFairy.onTap(727, 424, 757, 441, "", 500);
                            return;
                        }
                    }
                }
                return;
            }

            result = mFairy.findPic("rank6.png");
            if (result.sim > 0.8f) {
                err = 0;

                result = mFairy.findPic(str3);
                if (result.sim > 0.8f) {
                    if (oneSecond()) {
                        mFairy.onTap(1088, 604, 1166, 615, "", 1000);
                    }
                    if (timeMap("relese_sq", 10000, true)) {
                        mFairy.onTap(951, 607, 1001, 617, "", 500);
                        for (int i = 0; i < 4; i++) {
                            result = modularLookup(1045, 156, 1196, 260, "rank11.png");
                            mFairy.onTap(0.8f, result, "申请", 500);
                            modularLookup++;
                        }
                        modularLookup = 0;
                    }

                    result = mFairy.findPic("pp2.png");
                    mFairy.onTap(0.8f, result, "自动匹配", 500);
                    return;
                }

                result = mFairy.findPic(86, 56, 371, 664, str2);
                if (result.sim > 0.8f) {
                    oneSecond = 0;
                    mFairy.onTap(0.8f, result, "发现 str2 >>>", 1000);
                    return;
                }

                result = mFairy.findPic(86, 56, 371, 664, str1);
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "发现 str1 >>>", 1000);
                    return;
                }

                if (oneSecond()) {
                    mFairy.ranSwipe(236, 167, 236, 411, 500, 1000);
                    mFairy.ranSwipe(236, 167, 236, 411, 500, 1000);
                    mFairy.ranSwipe(236, 167, 236, 411, 500, 1000);
                    mFairy.onTap(180, 117, 267, 136, "全部目标", 500);
                    return;
                }

                if (frequencyMap("pp_count", 1)) {
                    mFairy.ranSwipe(236, 411, 236, 167, 500, 1000);
                }
                return;
            }

            result = mFairy.findPic(new String[]{"rank1.png", "rank2.png"});
            mFairy.onTap(0.8f, result, "点击队伍", 1000);


        }//队伍

        boolean activity_end(FindResult activity) throws Exception {
            result = mFairy.findPic(activity.x - 90, activity.y + 135, activity.x + 146, activity.y + 356,
                    new String[]{"wan1.png", "wan2.png", "wan3.png", "wan4.png", "wan5.png"});
            if (result.sim > 0.75f) {
                setTaskEnd();
                return true;
            }
            return false;
        }

        void cancel() throws Exception {
            FindResult cancel = gamePublicFuntion.cancel();
            if (cancel.sim > 0.8f) {

                result = mFairy.findPic("rank9.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(708, 422, 768, 440, "", 500);
                    gamePublicFuntion.close(3);
                    setTaskName(3);
                    return;
                }


                if (frequencyMap("qx", 1)) {
                    mFairy.onTap(0.8f, cancel, "取消 | 拒绝", 500);
                }
            } else {
                frequencyInit("qx");
            }
        }//取消 | 拒绝

        void inOperation() throws Exception {
            cancel();

            result = mFairy.findPic(470, 495, 641, 600, "song.png");
            if (result.sim > 0.75f) {
                err = 0;
            }

            result = mFairy.findPic(888, 260, 1030, 460, "fh2.png");
            if (result.sim > 0.8f) {
                err = 0;
            }

            long l = mFairy.getColorNum(92, 712, 198, 718, "237,194,0", 0.95f);
            if (l > 100) {
                LtLog.e(mFairy.getLineInfo("过图中 >>>"));
                err = 0;
            }

            result = mFairy.findPic(6, 186, 54, 239, "jian1.png");
            mFairy.onTap(0.85f, result, "箭头", 500);

            gamePublicFuntion.close_use();
            gamePublicFuntion.skip();
            gamePublicFuntion.fh();
        }
    }

    public void ysmj() throws Exception {
        new TeamTask_Content(mFairy, "元素秘境") {

            void create() throws Exception {
                super.create();
                activity_name = "ysmj.png";
                activity_type = 1;

            }

            void content_02() throws Exception {
                rank("ysmj1.png", "ysmj2.png", "ysmj3.png", "ysmj4.png");
            }

            void content_03() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.fbScene()) {
                    err = 0;
                    mFairy.initMatTime();
                    gamePublicFuntion.auto_battle();

                    if (timeMap("a", 10000, false)) {
                        mFairy.onTap(1094, 592, 1132, 608, "副本中 >>>", 500);
                    }

                    if (timeMap("fbtime", 600000, false)) {
                        setTaskName(0);
                        return;
                    }
                } else {
                    if (gamePublicFuntion.mainScene()) {
                        if (timeMap("err_click", 10000, false)) {
                            mFairy.onTap(599, 529, 662, 557, "err", 500);
                        }
                        if (gamePublicFuntion.judgeStop(480, false)) {
                            singleTask.li_rank();
                            setTaskName(0);
                            return;
                        }
                    }
                }
            }
        };
    }//元素秘境

    public void hd() throws Exception {
        new TeamTask_Content(mFairy, "混沌入侵:" + AtFairyConfig.getOption("hd")) {

            void create() throws Exception {
                super.create();
                activity_name = "hd.png";
                activity_type = 1;

            }

            void content_02() throws Exception {
                switch (AtFairyConfig.getOption("hd")) {
                    case "1":
                        rank("hd01_1.png", "hd2.png", "hd01_2.png", "hd01_3.png");
                        break;
                    case "2":
                        rank("hd02_1.png", "hd2.png", "hd02_2.png", "hd02_3.png");
                        break;
                    case "3":
                        rank("hd03_1.png", "hd2.png", "hd03_2.png", "hd03_3.png");
                        break;
                    case "4":
                        rank("hd04_1.png", "hd2.png", "hd04_2.png", "hd04_3.png");
                        break;
                    default:
                        rank("hd02_1.png", "hd2.png", "hd02_2.png", "hd02_3.png");
                        break;
                }
            }

            void content_03() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.fbScene()) {
                    err = 0;
                    mFairy.initMatTime();
                    gamePublicFuntion.auto_battle();

                    if (timeMap("a", 10000, false)) {
                        mFairy.onTap(1094, 592, 1132, 608, "副本中 >>>", 500);
                    }

                } else {
                    if (gamePublicFuntion.mainScene()) {
                        if (gamePublicFuntion.judgeStop(480, false)) {
                            singleTask.li_rank();
                            setTaskName(2);
                            return;
                        }
                    }
                }
            }
        };
    }//混沌入侵

    public String fb_name = "";

    public String fb_str2 = "";

    public String fb_str3 = "";

    public void fb(final String num, final String num1) throws Exception {
        new TeamTask_Content(mFairy, "副本任务") {

            void create() throws Exception {
                super.create();
                switch (num) {
                    case "1":
                        fb_name = "b1.png";
                        fb_str2 = "f1.png";
                        fb_str3 = "s1.png";
                        break;
                    case "2":
                        fb_name = "b2.png";
                        fb_str2 = "f2.png";
                        fb_str3 = "s2.png";
                        break;
                    case "3":
                        fb_name = "b3.png";
                        fb_str2 = "f3.png";
                        fb_str3 = "s3.png";
                        break;
                    case "4":
                        fb_name = "b4.png";
                        fb_str2 = "f4.png";
                        fb_str3 = "s4.png";
                        break;
                    case "5":
                        fb_name = "b5.png";
                        fb_str2 = "f5.png";
                        fb_str3 = "s5.png";
                        break;
                    case "6":
                        fb_name = "b6.png";
                        fb_str2 = "f6.png";
                        fb_str3 = "s6.png";
                        break;
                }
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(795, 61, 1241, 310, "activity.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "活动", 1000);
                }

                result = mFairy.findPic("activity1.png");
                if (result.sim > 0.75f) {
                    err = 0;

                    result = mFairy.findPic("fb1.png");
                    if (result.sim > 0.85f) {

                        switch (num) {
                            case "1":
                                mFairy.onTap(231, 131, 280, 145, "结晶洞窟", 200);
                                mFairy.onTap(231, 131, 280, 145, "结晶洞窟", 300);
                                break;
                            case "2":
                                mFairy.onTap(228, 187, 303, 212, "圣者高台", 200);
                                mFairy.onTap(228, 187, 303, 212, "圣者高台", 300);
                                break;
                            case "3":
                                mFairy.onTap(234, 248, 281, 262, "破帆船", 200);
                                mFairy.onTap(234, 248, 281, 262, "破帆船", 300);
                                break;
                            case "4":
                                mFairy.onTap(221, 315, 312, 333, "卡米卡泽祭坛", 200);
                                mFairy.onTap(221, 315, 312, 333, "卡米卡泽祭坛", 300);
                                break;
                            case "5":
                                mFairy.onTap(224, 368, 281, 393, "珊瑚宫", 200);
                                mFairy.onTap(224, 368, 281, 393, "珊瑚宫", 300);
                                break;
                            case "6":
                                mFairy.onTap(232, 438, 295, 457, "遗忘大厅", 200);
                                mFairy.onTap(232, 438, 295, 457, "遗忘大厅", 300);
                                break;
                        }

                        switch (num1) {
                            case "1":
                                mFairy.onTap(906, 116, 961, 133, "普通", 800);
                                break;
                            case "2":
                                mFairy.onTap(1056, 116, 1128, 132, "精英", 800);
                                break;
                        }

                        result = mFairy.findPic("fb2.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(29, 19, 40, 52, "", 500);
                            setTaskEnd();
                            return;
                        }

                        mFairy.onTap(29, 19, 40, 52, "", 500);
                        setTaskName(2);
                        oneSecond=0;
                        return;

                    } else {
                        mFairy.onTap(999, 677, 1035, 684, "选择副本", 500);
                    }
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("rankScene.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(151, 55, 451, 140, fb_name);
                    if (result.sim > 0.8f) {

                        result = mFairy.findPic(1044, 539, 1268, 661, "rank8.png");
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic(12, 552, 217, 670, "rank4.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "退出队伍", 1000);
                                mFairy.onTap(727, 424, 757, 441, "", 500);
                                return;
                            }
                        } else {
                            result = mFairy.findPic(1044, 539, 1268, 661, "rank7.png");
                            mFairy.onTap(0.8f, result, "跟随队长", 500);

                            mFairy.onTap(32, 10, 50, 42, "", 500);

                            setTaskName(3);
                        }
                        return;
                    } else {
                        if (frequencyMap("rank_err", 1)) {
                            result = mFairy.findPic(12, 552, 217, 670, "rank4.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "退出队伍", 1000);
                                mFairy.onTap(727, 424, 757, 441, "", 500);
                                return;
                            }
                        }
                    }
                    return;
                }

                result = mFairy.findPic("rank6.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(fb_str3);
                    if (result.sim > 0.8f) {

                        if (oneSecond()) {

                            switch (num1) {
                                case "1":
                                    mFairy.onTap(929, 106, 1013, 125, "普通", 500);
                                    break;
                                case "2":
                                    mFairy.onTap(1089, 108, 1149, 120, "精英", 500);
                                    if (mapCount(0.8f, 548, 67, 771, 234, "fb3.png")) {
                                        mFairy.onTap(32, 11, 47, 34, "", 500);
                                        setTaskEnd();
                                        return;
                                    }
                                    break;
                            }

                            if (AtFairyConfig.getOption("fbing").equals("1")) {
                                result = mFairy.findPic("fb5.png");
                                if(result.sim<0.85f) {
                                    mFairy.onTap(577,609,590,624,"匹配进行中的副本", 500);
                                }
                            } else {
                                result = mFairy.findPic("fb5.png");
                                mFairy.onTap(0.85f, result, "取消 匹配进行中的副本", 500);
                            }

                            mFairy.onTap(1088, 604, 1166, 615, "", 1000);
                        }

                        if (timeMap("relese_sq", 10000, true)) {
                            mFairy.onTap(951, 607, 1001, 617, "", 500);
                            for (int i = 0; i < 4; i++) {
                                result = modularLookup(1045, 156, 1196, 260, "rank11.png");
                                mFairy.onTap(0.8f, result, "申请", 500);
                                modularLookup++;
                            }
                            modularLookup = 0;
                        }

                        result = mFairy.findPic("pp2.png");
                        mFairy.onTap(0.8f, result, "自动匹配", 500);
                        return;
                    }

                    result = mFairy.findPic(86, 56, 371, 664, fb_str2);
                    if (result.sim > 0.8f) {
                        oneSecond = 0;
                        mFairy.onTap(0.8f, result, "发现 str2 >>>", 500);
                        if (mapCount(0.8f, 548, 67, 771, 234, "fb3.png")) {
                            mFairy.onTap(32, 11, 47, 34, "", 500);
                            setTaskEnd();
                            return;
                        }
                        return;
                    }

                    result = mFairy.findPic(86, 56, 371, 664, "fb6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "发现 str1 >>>", 1000);
                        return;
                    }

                    if (oneSecond()) {
                        mFairy.ranSwipe(236, 167, 236, 411, 500, 1000);
                        mFairy.ranSwipe(236, 167, 236, 411, 500, 1000);
                        mFairy.ranSwipe(236, 167, 236, 411, 500, 1000);
                        mFairy.onTap(180, 117, 267, 136, "全部目标", 500);
                    }
                    return;
                }

                result = mFairy.findPic(new String[]{"rank1.png", "rank2.png"});
                mFairy.onTap(0.8f, result, "点击队伍", 1000);
            }

            void cancel() throws Exception {
                FindResult cancel = gamePublicFuntion.cancel();
                if (cancel.sim > 0.8f) {

                    result = mFairy.findPic("rank9.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(708, 422, 768, 440, "", 500);
                        gamePublicFuntion.close(3);
                        setTaskName(3);
                        return;
                    }

                    result = mFairy.findPic("fb8.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(720, 456, 759, 469, "", 500);
                        return;
                    }


                    if (frequencyMap("qx", 1)) {
                        mFairy.onTap(0.8f, cancel, "取消 | 拒绝", 500);
                    }
                } else {
                    frequencyInit("qx");
                }
            }

            void content_03() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("ljlx1.png");
                mFairy.onTap(0.8f, result, "准备", 500);

                if (gamePublicFuntion.fbScene()) {
                    err = 0;
                    mFairy.initMatTime();
                    gamePublicFuntion.auto_battle();

                    if (timeMap("a", 10000, false)) {
                        mFairy.onTap(1094, 592, 1132, 608, "副本中 >>>", 500);
                    }
                } else {
                    if (gamePublicFuntion.mainScene()) {
                        if (timeMap("err_click", 10000, false)) {
                            mFairy.onTap(599, 529, 662, 557, "err", 500);
                        }
                    }
                }

                gamePublicFuntion.close(1);

                result = mFairy.findPic("fb7.png");
                mFairy.onTap(0.8f, result, 623, 457, 653, 475, "", 500);

                result = mFairy.findPic(new String[]{"rank3.png", "rank10.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("没有队伍，开始查看任务是否完成"));
                    setTaskName(0);
                    return;
                }

                if (gamePublicFuntion.judgeStop(480, false)) {
                    singleTask.li_rank();
                    setTaskName(0);
                    return;
                }
            }

        };
    }//副本任务

    public void sjsl() throws Exception {
        new TeamTask_Content(mFairy, "世界首领") {

            void create() throws Exception {
                super.create();
                activity_name = "sjsl.png";
                activity_type = 1;
            }

            void content_02() throws Exception {
                rank("sjsl4.png", "sjsl1.png", "sjsl2.png", "sjsl3.png");
            }

            void inOperation() throws Exception {
                super.inOperation();
                if (timeMap("sjsl_time", 610000, false)) {
                    setTaskEnd();
                    return;
                }
            }

            void content_03() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.fbScene()) {
                    err = 0;
                    mFairy.initMatTime();
                    gamePublicFuntion.auto_battle();

                    if (timeMap("a", 10000, false)) {
                        mFairy.onTap(1094, 592, 1132, 608, "副本中 >>>", 500);
                    }
                } else {
                    if (gamePublicFuntion.mainScene()) {
                        if (timeMap("err_click", 10000, false)) {
                            mFairy.onTap(599, 529, 662, 557, "err", 500);
                        }
                        if (gamePublicFuntion.judgeStop(480, false)) {
                            singleTask.li_rank();
                            setTaskName(0);
                            return;
                        }
                    }
                }
            }
        };
    }//世界首领

    public void jybwz() throws Exception {
        new TeamTask_Content(mFairy, "家园保卫战") {

            void create() throws Exception {
                super.create();
                activity_name = "jybwz.png";
                activity_type = 2;

            }

            void inOperation() throws Exception {
                super.inOperation();

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();

                if (hour == 22) {
                    LtLog.e(mFairy.getLineInfo("到达活动的结束时间,End!"));
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                rank("jybwz1.png", "sjsl1.png", "jybwz2.png", "jybwz3.png");
            }

            void content_03() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.fbScene()) {
                    err = 0;
                    mFairy.initMatTime();
                    gamePublicFuntion.auto_battle();

                    if (timeMap("a", 10000, false)) {
                        mFairy.onTap(1094, 592, 1132, 608, "副本中 >>>", 500);
                    }
                } else {
                    if (gamePublicFuntion.mainScene()) {
                        if (timeMap("err_click", 10000, false)) {
                            mFairy.onTap(599, 529, 662, 557, "err", 500);
                        }
                        if (gamePublicFuntion.judgeStop(480, false)) {
                            singleTask.li_rank();
                            setTaskName(0);
                            return;
                        }
                    }
                }
            }

        };
    }//家园保卫战

}