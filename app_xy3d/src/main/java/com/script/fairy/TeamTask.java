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
    private int activity_type = 1;
    private String activity_name;
    public boolean bool_genrank_state = false;
    private boolean bool_rank = false;
    private boolean bool_dairank_state = false;

    public AtFairyImpl getmFairy() {
        return mFairy;
    }

    public TeamTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        singleTask = new SingleTask(ypFairy);
    }

    private int start_num = 3;

    private boolean hanhua = true;

    class TeamTask_Content extends TaskContent {

        public TeamTask_Content(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);
        }

        void create() throws Exception {
            super.create();
            hanhua = true;
            if (!AtFairyConfig.getOption("number").equals("")) {
                start_num = Integer.parseInt(AtFairyConfig.getOption("number"));
            }
        }

        void init() throws Exception {
            gamePublicFuntion.init(1);
            setTaskName(1);
        }

        void inOperation() throws Exception {
            gamePublicFuntion.lv();
            gamePublicFuntion.skipScene();
            gamePublicFuntion.chat();
            gamePublicFuntion.deng();
            result = mFairy.findPic("ling3.png");
            mFairy.onTap(0.8f, result, 1213, 52, 1232, 70, "", 1000);
            result = mFairy.findPic(1045, 3, 1265, 50, "nn2.png");
            mFairy.onTap(0.85f, result, "跳过教学", 500);

            result = mFairy.findPic(962, 562, 1077, 636, "map.png");
            mFairy.onTap(0.75f, result, 1123, 67, 1147, 86, "关闭地图界面", 500);

            result = mFairy.findPic(331, 478, 597, 527, "rank20.png");
            mFairy.onTap(0.85f, result, 780, 514, 801, 530, "同意申请", 500);

            if (AtFairyConfig.getOption("jj").equals("1")) {
                if (gamePublicFuntion.fb()) {
                    if (timeMap("jj", 180000, true)) {
                        mFairy.onTap(1032, 433, 1054, 447, "开始释放绝技", 1000);
                        mFairy.onTap(1033, 332, 1058, 350, "", 500);
                        mFairy.onTap(1216, 641, 1234, 662, "", 500);
                        gamePublicFuntion.close(3);
                    }
                }
            }

            cancel();
            use();
        }

        void cancel() throws Exception {
            result = gamePublicFuntion.qx();
            mFairy.onTap(0.8f, result, "取消", 500);
        }

        void use() throws Exception {
            gamePublicFuntion.use();
        }//使用

        boolean activity_end(FindResult act) throws Exception {

            return false;
        }

        void content_01() throws Exception {
            if (timeCount(12, 0)) {
                if (frequencyMap("actError", 1)) {
                    setTaskEnd();
                    return;
                }
            }
            result = mFairy.findPic("activity.png");
            if (result.sim > 0.75f) {
                GamePublicFuntion.activity_bool = false;
                mFairy.onTap(0.75f, result, "活动按钮", 800);
            }

            result = mFairy.findPic("activity1.png");
            if (result.sim > 0.8f) {
                if (oneSecond()) {
                    gamePublicFuntion.activity_type(activity_type);

                    result = mFairy.findPic("activity3.png");
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 5; i++) {

                            result = mFairy.findPic(82,67,180,651, "activity2.png");
                            mFairy.onTap(0.75f, result, result.x + 30, result.y + 0, result.x + 40, result.y + 1, "领取活跃", 500);
                        }
                    }
                }

                FindResult act = mFairy.findPic(209,138,1157,522, activity_name);
                if (act.sim > 0.75f) {

                    if (activity_end(act)) {
                        oneSecond = 0;
                        return;
                    }

                    result = mFairy.findPic(act.x + 223, act.y - 30, act.x + 380, act.y + 71, "wan.png");
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(act.x + 223, act.y - 10, act.x + 380, act.y + 71, "qian.png");
                    if (result.sim > 0.8f) {

                        mFairy.onTap(1177,55,1197,76, "前往", 500);
                        oneSecond = 0;
                        frequencyInit("actError");
                        bool_rank = false;
                        setTaskName(2);
                        return;
                    }
                }

                if (GamePublicFuntion.activity_bool == false) {
                    activitySlide.slideRange(new int[]{3, 5, 6, 7,8,9,10}, 2);
                } else {
                    activitySlide.slideRange(new int[]{3, 5, 6, 7, 8,9,10}, 2, 0);
                }
            }


        }//活动

        void han() throws Exception {
            result = mFairy.findPic("rank19.png");
            if (result.sim > 0.8f) {
                if (AtFairyConfig.getOption("han1").equals("1")) {
                    result = mFairy.findPic("han1.png");
                    mFairy.onTap(0.85f, result, "队伍喊话", 500);
                } else {
                    result = mFairy.findPic("han1.png");
                    if (result.sim < 0.85f) {
                        mFairy.onTap(546, 441, 563, 453, "", 500);
                    }
                }

                if (AtFairyConfig.getOption("han2").equals("1")) {
                    result = mFairy.findPic("han2.png");
                    mFairy.onTap(0.85f, result, "帮派喊话", 500);
                } else {
                    result = mFairy.findPic("han2.png");
                    if (result.sim < 0.85f) {
                        mFairy.onTap(675, 442, 687, 450, "", 500);
                    }
                }

                if (AtFairyConfig.getOption("han3").equals("1")) {
                    result = mFairy.findPic("han3.png");
                    mFairy.onTap(0.85f, result, "当前喊话", 500);
                } else {
                    result = mFairy.findPic("han3.png");
                    if (result.sim < 0.85f) {
                        mFairy.onTap(804, 441, 816, 452, "", 500);
                    }
                }

                result = mFairy.findPic("han.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "一键喊话", 1000);
                    if (mapCount(0.8f, 417, 105, 876, 406, "hanhua.png")) {
                        mFairy.onTap(920, 161, 937, 178, "", 500);
                        hanhua = false;
                        return;
                    } else {
                        result = mFairy.findPic("han.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(920, 161, 937, 178, "", 500);
                            return;
                        }
                    }
                }
            }

        }//喊话

        void rank(String[] img, String mb_str1, String[] mb_str2) throws Exception {
            timeCount(10, 0);


            result = mFairy.findPic("rank12.png");
            if (result.sim > 0.8f) {

                if (frequencyMap("mbError", 7)) {
                    mFairy.onTap(1097, 108, 1119, 121, "", 500);
                    return;
                }

                result = mFairy.findPic(185, 136, 446, 640, mb_str1);
                if (result.sim > 0.85f) {
                    result = mFairy.findPic(result.x + 90, result.y - 20, result.x + 200, result.y + 43, "mb1.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "点击 str1", 1000);
                        err = 4;
                        if (mb_str2 == null) {
                            err = 0;
                            result = mFairy.findPic("rank13.png");
                            mFairy.onTap(0.9f, result, "自动匹配", 500);

                            mFairy.onTap(969, 581, 1026, 603, "开始匹配", 2000);
                            mFairy.onTap(1109, 117, 1126, 129, "", 1000);
                            return;
                        }
                    }
                }

                if (mb_str2 != null) {
                    result = mFairy.findPic(181, 132, 433, 681, mb_str2);
                    if (result.sim > 0.8f) {
                        err = 0;
                        mFairy.onTap(0.8f, result, "点击 str2", 1000);

                        result = mFairy.findPic("rank13.png");
                        mFairy.onTap(0.9f, result, "自动匹配", 500);

                        mFairy.onTap(969, 581, 1026, 603, "开始匹配", 2000);
                        mFairy.onTap(1109, 117, 1126, 129, "", 1000);
                        return;
                    }
                }

                rankmbSlide.slideRange(new int[]{3, 4, 5, 6}, 2, 0);
                return;
            } else {
                frequencyInit("mbError");
            }

            result = mFairy.findPic(1, 1, 85, 214, "rankScene.png");
            LtLog.e(mFairy.getLineInfo("组队场景 sim:" + result.sim));
            if (result.sim > 0.85f) {
                err = 0;

                result = mFairy.findPic(581,556,1244,633, "rank27.png");
                if (result.sim<0.8f) {
                    result = mFairy.findPic("rank5.png");
                    mFairy.onTap(0.85f, result, "退出队伍", 500);
                    return;
                }

                result = mFairy.findPic("yao1.png");
                mFairy.onTap(0.85f, result, 1180, 66, 1200, 82, "批准", 500);

                han();

                result = mFairy.findPic("rank15.png");
                if (result.sim > 0.8f) {
                    for (int i = 0; i < 5; i++) {

                        result = mFairy.findPic(408, 22, 543, 542, "rank16.png");
                        mFairy.onTap(0.85f, result, "批准", 500);
                    }

                    mFairy.onTap(336, 655, 370, 670, "", 1000);
                }


                result = mFairy.findPic(190, 66, 517, 165, img);
                if (result.sim > 0.8f) {
                    frequencyInit("rank_mb");




                    int rn = gamePublicFuntion.rank_num();
                    LtLog.e(mFairy.getLineInfo("队伍人数："+rn));
                    if ( rn>= start_num) {
                        mFairy.onTap(100, 110, 141, 127, "人数达到要求>>>前往", 500);
                        frequencyInit("seeRank");
                        setTaskName(3);
                        return;
                    }



                    if (AtFairyConfig.getOption("5307").equals("1") || AtFairyConfig.getOption("5489").equals("1")) {

                        if (timeMap("han", 15000, true) && hanhua) {
                            result = mFairy.findPic("rank18.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "喊话", 1000);
                                return;
                            }
                        }
                    }

                    result = mFairy.findPic("rank14.png");
                    mFairy.onTap(0.8f, result, "有人申请队伍", 1000);

                    result = mFairy.findPic(590,542,1255,642,"rank28.png");
                    if(result.sim>0.85f) {
                        mFairy.onTap(0.85f, result, "便捷组队", 1500);
                        mFairy.onTap(969, 581, 1026, 603, "开始匹配", 2000);
                        mFairy.onTap(1109, 117, 1126, 129, "", 1000);
                    }

                } else {
                    if (frequencyMap("rank_mb", 2)) {
                        frequencyInit("start_activity");
                        mFairy.onTap(243, 113, 258, 130, "", 1000);
                    }
                }

                if (frequencyMap("errRank_count", 10)) {
                    gamePublicFuntion.close(3);
                    bool_rank = false;
                    return;
                }

                return;
            }

            gamePublicFuntion.clickRank();
        }

    }

    private boolean zg = false;
    private long cb_time = 3600000;

    public void zg() throws Exception {
        new TeamTask_Content(mFairy, "带队捉鬼") {

            void create() throws Exception {
                super.create();
                activity_type = 1;
                activity_name = "zg.png";

                if (AtFairyConfig.getOption("zg").equals("2")) {
                    zg = true;
                } else {
                    zg = false;
                }

                long i = getTimeStamp(AtFairyConfig.getOption("cb"));
                if (i != -1) {
                    cb_time = i;
                }


            }

            void content_02() throws Exception {
                rank(new String[]{"zg1.png"}, "zg6.png", null);
            }

            boolean activity_end(FindResult act) throws Exception {
                if (zg == false) {
                    result = mFairy.findPic(act.x + 80, act.y + 2, act.x + 180, act.y + 66,
                            new String[]{"zg11.png", "zg12.png"});
                    if (result.sim > 0.92f) {
                        LtLog.e(mFairy.getLineInfo("捉鬼活跃度已满"));
                        setTaskEnd();
                        return true;
                    }
                }
                return false;
            }

            void cancel() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {
                    result = mFairy.findPic("zg9.png");
                    if (result.sim > 0.8f) {
                        if (zg) {
                            mFairy.onTap(810, 436, 850, 451, "继续追鬼", 500);
                        } else {
                            mFairy.onTap(403, 436, 489, 448, "完成一轮查看活跃", 500);
                            setTaskName(0);
                        }
                        return;
                    }

                    result = mFairy.findPic("zg10.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(585, 436, 684, 447, "追鬼次数已达上限", 500);
                        setTaskName(0);
                        return;
                    }
                    if (frequencyMap("qx", 1)) {
                        mFairy.onTap(0.8f, qx, "取消", 500);
                    }
                } else {
                    frequencyInit("qx");
                }

            }

            void content_03() throws Exception {
                timeCount(8, 0);

                gamePublicFuntion.task();

                gamePublicFuntion.fhs(2);

                if (timeMap("cb", cb_time, false)) {
                    singleTask.chubei();
                    gamePublicFuntion.close(3);
                }

                if (timeMap("seeRank", 600000, false)) {
                    gamePublicFuntion.close(3);
                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic(997, 57, 1152, 523, "zg7.png");
                mFairy.onTap(0.8f, result, "追鬼任务", 500);

                if (frequencyMap("chat2", 1)) {
                    gamePublicFuntion.chat2();
                }

                if (gamePublicFuntion.fb()) {
                    err = 0;
                    mFairy.onTap(41,209,90,228,"",3000);

                    mFairy.initMatTime();
                }

                if (gamePublicFuntion.mainScene()) {
                    frequencyInit("chat2");
                    if (timeMap("jihe", 180000, true)) {

                        gamePublicFuntion.rank();

                        result = mFairy.findPic("jihe.png");
                        mFairy.onTap(0.72f, result, "集合", 500);
                        mFairy.onTap(0.72f, result, "集合", 500);
                    }

                    if (gamePublicFuntion.judgeStop(6, true, 0.85f)) {

                        FindResult task = mFairy.findPic(7, 127, 74, 396, "zg8.png");
                        if (task.sim > 0.7f) {
                            mFairy.onTap(0.7f, task, "taskClick", 5000);
                            return;
                        }

                        taskSlide.slideRange(new int[]{3, 4, 5,6}, 2, 0);
                    }
                }
            }
        };
    }//带队捉鬼

    private String ytdg_name = "dg1.png";
    private int ytdg_num = 0;
    private boolean ytdg_bool = false;

    public void ytdg() throws Exception {
        new TeamTask_Content(mFairy, "雁塔地宫") {

            void create() throws Exception {
                super.create();
                activity_type = 2;
                activity_name = "ytdg.png";
                ytdg_name = "dg" + AtFairyConfig.getOption("ytdg") + ".png";
            }

            void content_02() throws Exception {
                rank(new String[]{"ytdg2.png"/*,"j1.png"*/}, "ytdg1.png", null);
                ytdg_num = 0;
                ytdg_bool = false;
            }

            void cancel() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic("ytdg8.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(811, 434, 850, 452, "人数不足>确定进入副本", 2000);
                        return;
                    }
                    if (frequencyMap("qx", 1)) {
                        mFairy.onTap(0.8f, qx, "取消", 500);
                    }
                } else {
                    frequencyInit("qx");
                }
            }

            void content_03() throws Exception {
                if (timeCount(10, 0)) {
                    if (ytdg_bool) {
                        setTaskEnd();
                        return;
                    }
                }

                if (AtFairyConfig.getOption("fh").equals("1")) {
                    gamePublicFuntion.fhs(1);
                }

                result = mFairy.findPic(997, 57, 1152, 523, "ytdg3.png");
                mFairy.onTap(0.8f, result, "参加活动", 500);

                result = mFairy.findPic(970, 584, 1198, 681, "ytdg5.png");
                mFairy.onTap(0.8f, result, "确定选择", 500);

                result = mFairy.findPic("ytdg6.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("ytdg9.png");
                    if (result.sim > 0.95f) {
                        mFairy.onTap(1153, 86, 1171, 104, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(215, 88, 605, 647, ytdg_name);
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "选择怪物", 1000);

                        long l = mFairy.getColorNum(970, 584, 995, 605, 0.95f, 0, "108,101,90");
                        if (l > 100) {
                            setTaskEnd();
                            return;
                        }

                        mFairy.onTap(1002, 585, 1058, 597, "", 2000);
                        return;
                    }

                    ytdg_num++;
                    if (ytdg_num > 10) {
                        setTaskEnd();
                        return;
                    }
                    ytdgSlide.slideRange(ytdg_num, new int[]{3, 5, 7, 9}, 2, 0);
                    return;
                } else {
                    if (gamePublicFuntion.mainScene()) {
                        if (timeMap("jihe", 180000, true)) {
                            gamePublicFuntion.rank();

                            result = mFairy.findPic("jihe.png");
                            mFairy.onTap(0.72f, result, "集合", 500);
                            mFairy.onTap(0.72f, result, "集合", 500);
                        }

                        if (gamePublicFuntion.judgeStop(6, true, 0.85f)) {

                        }
                    }
                }

                result = mFairy.findPic("ytdg7.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (frequencyMap("ytdg_end", 2)) {
                        mFairy.onTap(424, 434, 460, 449, "无法获得奖励,end!", 1000);
                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(1000);
                            gamePublicFuntion.init(0);

                            if (gamePublicFuntion.fb() == false) {
                                Thread.sleep(2000);
                                break;
                            }
                        }
                        gamePublicFuntion.rank();

                        result = mFairy.findPic("jihe.png");
                        mFairy.onTap(0.72f, result, "集合", 500);
                        mFairy.onTap(0.72f, result, "集合", 500);

                        setTaskEnd();
                        return;
                    }
                    mFairy.onTap(0.8f, result, 797, 439, 868, 454, "继续下一层", 8000);
                }

                if (gamePublicFuntion.fb()) {
                    err = 0;
                    ytdg_bool = true;
                    long a = mFairy.getColorNum(329, 24, 516, 36, 0.95f, 0, "250,75,75");
                    if (a < 40) {
                        if (gamePublicFuntion.fhs() == false) {
                            if (frequencyMap("rand", 3)) {
                                LtLog.e(mFairy.getLineInfo("没有发现目标,滑动>>>"));
                                mFairy.ranSwipe(199, 608, 199, 373, 5000, (long) 500, 7);
                            }
                        }
                    } else {
                        frequencyInit("rand");
                    }
                    gamePublicFuntion.auto();
                    mFairy.initMatTime();
                }
            }
        };
    }//雁塔地宫

    public void ptfb(final int i) throws Exception {
        new TeamTask_Content(mFairy, "普通副本") {

            void create() throws Exception {
                super.create();
                activity_type = 1;
                activity_name = "ptfb.png";
            }

            void content_02() throws Exception {
                switch (i) {
                    case 1:
                        rank(new String[]{"dxmo.png", "fdyy.png"}, "ptfb1.png", new String[]{"dxmo1.png", "fdyy1.png"});
                        break;
                    case 2:
                        rank(new String[]{"xhlz.png", "dhyf.png"}, "ptfb1.png", new String[]{"xhlz1.png", "dhyf1.png"});
                        break;
                }
            }

            void cancel() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic(335, 245, 940, 404, "jia1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 805, 435, 864, 449, "是否召回", 500);
                        return;
                    }

                    result = mFairy.findPic(316, 263, 966, 401, "jia4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 808, 434, 855, 453, "确定进入", 500);
                        return;
                    }

                    if (frequencyMap("qx", 1)) {
                        mFairy.onTap(0.8f, qx, "取消", 500);
                    }

                } else {
                    frequencyInit("qx");
                }
            }

            void content_03() throws Exception {
                timeCount(15, 0);

                if (AtFairyConfig.getOption("fh").equals("1")) {
                    gamePublicFuntion.fhs(1);
                }

                result = mFairy.findPic("ptfb9.png");
                mFairy.onTap(0.8f, result, "继续挂机", 500);

                result = mFairy.findPic("ptfb17.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "继续开启", 500);
                    if (mapCount(0.8f, 426, 149, 562, 556, "jia3.png")) {
                        setTaskName(2);
                        gamePublicFuntion.init(0);
                        return;
                    }
                }
                result = mFairy.findPic("ptfb18.png");
                mFairy.onTap(0.8f, result, "继续", 500);

                result = mFairy.findPic("ptfb13.png");
                mFairy.onTap(0.8f, result, "跳过旁白", 500);

                result = mFairy.findPic("ptfb14.png");
                if (result.sim > 0.8f) {

                    for (int i = 0; i < 4; i++) {

                        result = modularLookup(1113, 204, 1191, 300, "shaizi.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "骰子", 500);
                            modularLookup++;
                        }

                    }
                    modularLookup = 0;

                    mFairy.onTap(1182, 65, 1202, 83, "", 1000);
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(1000);

                        result = mFairy.findPic("ptfb18.png");
                        mFairy.onTap(0.8f, result, "继续", 500);

                        gamePublicFuntion.init(0);

                        if (gamePublicFuntion.fb() == false) {
                            Thread.sleep(2000);
                            break;
                        }
                    }

                    gamePublicFuntion.rank();

                    result = mFairy.findPic("jihe.png");
                    mFairy.onTap(0.72f, result, "集合", 500);
                    mFairy.onTap(0.72f, result, "集合", 500);

                    setTaskEnd();

                    return;
                }


                result = mFairy.findPic("ptfb11.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(5000);
                    mFairy.onTap(600, 435, 668, 448, "全部战败", 500);

                    mFairy.onTap(424, 434, 460, 449, "无法获得奖励,end!", 1000);
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(1000);
                        gamePublicFuntion.init(0);

                        if (gamePublicFuntion.fb() == false) {
                            Thread.sleep(2000);
                            break;
                        }
                    }
                    gamePublicFuntion.rank();

                    result = mFairy.findPic("jihe.png");
                    mFairy.onTap(0.72f, result, "集合", 500);
                    mFairy.onTap(0.72f, result, "集合", 500);

                    setTaskEnd();
                    return;
                }

                gamePublicFuntion.errClick();

                result = mFairy.findPic("nn1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "跳过剧情", 800);
                    mFairy.onTap(412, 435, 483, 450, "", 1000);
                    err = 0;
                }

                result = mFairy.findPic("nn10.png");
                mFairy.onTap(0.8f, result, "跳过动画", 1000);

                if (gamePublicFuntion.fb()) {
                    err = 0;
                    gamePublicFuntion.errClick = 0;
                    gamePublicFuntion.auto();

                    result = mFairy.findPic(592, 73, 737, 387, "ptfb10.png");
                    if (result.sim > 0.8f) {
                        mFairy.ranSwipe(144, 595, 295, 595, 500, (long) 500, 4);
                    }

                    result = mFairy.findPic("shou.png");
                    if (result.sim > 0.8f) {
                        mFairy.ranSwipe(197, 608, 197, 582, 800, 200);
                        mFairy.onTap(0.8f, result, "手", 2000);
                        mFairy.initMatTime();
                        return;
                    }

                } else {

                    result = mFairy.findPic(997, 57, 1152, 523, "ptfb2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "选择副本", 1500);
                        gamePublicFuntion.errClick = 0;
                    }

                    result = mFairy.findPic("ptfb3.png");
                    if (result.sim > 0.8f) {
                        gamePublicFuntion.errClick = 0;
                        err = 0;
                        FindResult fb;

                        result = mFairy.findPic("ptfb6.png");
                        mFairy.onTap(0.8f, result, "普通副本", 1000);

                        switch (i) {
                            case 1:
                                fb = mFairy.findPic(170, 117, 1119, 190, new String[]{"dxmo2.png", "fdyy2.png"});
                                if (fb.sim > 0.75f) {

                                    result = mFairy.findPic(fb.x - 10, fb.y + 202, fb.x + 156, fb.y + 342, "ptfb15.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(1134, 81, 1156, 97, "", 500);
                                        setTaskEnd();
                                        return;
                                    }
                                    mFairy.onTap(0.8f, fb, fb.x, fb.y + 200, fb.x + 50, fb.y + 250, "地下魔祸", 3000);
                                }

                                break;
                            case 2:
                                fb = mFairy.findPic(170, 117, 1119, 190, new String[]{"xhlz2.png", "dhyf2.png"});
                                if (fb.sim > 0.75f) {

                                    result = mFairy.findPic(fb.x - 10, fb.y + 202, fb.x + 156, fb.y + 342, "ptfb15.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(1134, 81, 1156, 97, "", 500);
                                        setTaskEnd();
                                        return;
                                    }
                                    mFairy.onTap(0.8f, fb, fb.x, fb.y + 200, fb.x + 50, fb.y + 250, "西海龙战", 3000);
                                }
                                break;
                        }

                        if (frequencyMap("ran_fb", 3)) {
                            mFairy.ranSwipe(951, 368, 433, 368, 500, (long) 2000, 3);
                            return;
                        }

                        result = mFairy.findPic("ptfb4.png");
                        if (result.sim > 0.8f) {

                            result = mFairy.findPic("ptfb7.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(1135, 77, 1155, 97, "", 500);
                                setTaskEnd();
                                return;
                            }

                            mFairy.onTap(962, 603, 1002, 620, "开启", 500);
                            if (mapCount(0.8f, 426, 149, 562, 556, "jia3.png")) {
                                setTaskName(2);
                                gamePublicFuntion.init(0);
                                return;
                            }
                        }
                    }

                    result = mFairy.findPic(535, 2, 704, 42, "ptfb8.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        gamePublicFuntion.errClick = 0;
                    }

                    if (gamePublicFuntion.mainScene()) {
                        gamePublicFuntion.errClick = 0;
                        if (timeMap("jihe", 300000, true)) {
                            gamePublicFuntion.rank();

                            result = mFairy.findPic("jihe.png");
                            mFairy.onTap(0.72f, result, "集合", 500);
                        }
                        gamePublicFuntion.judgeStop(10, true, 0.85f);
                    }

                }
            }
        };
    }//普通副本 ....未修改

    public void knfb(final int i) throws Exception {
        new TeamTask_Content(mFairy, "困难副本") {

            void create() throws Exception {
                super.create();
                activity_type = 1;
                activity_name = "knfb.png";

            }

            void content_02() throws Exception {
                switch (i) {
                    case 1:
                        rank(new String[]{"dxmo.png"}, "knfb1.png", new String[]{"dxmo1.png"});
                        break;
                    case 2:
                        rank(new String[]{"fdyy.png"}, "knfb1.png", new String[]{"fdyy1.png"});
                        break;
                    case 3:
                        rank(new String[]{"xhlz.png"}, "knfb1.png", new String[]{"xhlz1.png"});
                        break;
                    case 4:
                        rank(new String[]{"dhyf.png"}, "knfb1.png", new String[]{"dhyf1.png"});
                        break;
                }
            }

            void cancel() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic(335, 245, 940, 404, "jia1.png");
                    mFairy.onTap(0.8f, result, 805, 435, 864, 449, "是否召回", 500);

                    if (frequencyMap("qx", 1)) {
                        mFairy.onTap(0.8f, qx, "取消", 500);
                    }

                } else {
                    frequencyInit("qx");
                }
            }

            void content_03() throws Exception {
                timeCount(15, 0);

                if (AtFairyConfig.getOption("fh").equals("1")) {
                    gamePublicFuntion.fhs(1);
                }

                result = mFairy.findPic("ptfb9.png");
                mFairy.onTap(0.8f, result, "继续挂机", 500);

                result = mFairy.findPic("ptfb17.png");
                mFairy.onTap(0.8f, result, "继续开启", 500);

                result = mFairy.findPic("ptfb13.png");
                mFairy.onTap(0.8f, result, "跳过旁白", 500);

                result = mFairy.findPic("ptfb14.png");
                if (result.sim > 0.8f) {
                    for (int i = 0; i < 4; i++) {

                        result = modularLookup(1113, 204, 1191, 300, "shaizi.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "骰子", 500);
                            modularLookup++;
                        }

                    }
                    modularLookup = 0;

                    mFairy.onTap(1182, 65, 1202, 83, "", 1000);
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(1000);

                        gamePublicFuntion.init(0);

                        if (gamePublicFuntion.fb() == false) {
                            Thread.sleep(2000);
                            break;
                        }
                    }

                    gamePublicFuntion.rank();

                    result = mFairy.findPic("jihe.png");
                    mFairy.onTap(0.72f, result, "集合", 500);
                    mFairy.onTap(0.72f, result, "集合", 500);

                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("ptfb11.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(5000);
                    mFairy.onTap(600, 435, 668, 448, "全部战败", 500);

                    mFairy.onTap(424, 434, 460, 449, "无法获得奖励,end!", 1000);
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(1000);
                        gamePublicFuntion.init(0);

                        if (gamePublicFuntion.fb() == false) {
                            Thread.sleep(2000);
                            break;
                        }
                    }
                    gamePublicFuntion.rank();

                    result = mFairy.findPic("jihe.png");
                    mFairy.onTap(0.72f, result, "集合", 500);
                    mFairy.onTap(0.72f, result, "集合", 500);

                    setTaskEnd();
                    return;
                }

                if (gamePublicFuntion.fb()) {
                    err = 0;
                    gamePublicFuntion.auto();

                    result = mFairy.findPic(592, 73, 737, 387, "ptfb10.png");
                    if (result.sim > 0.8f) {
                        mFairy.ranSwipe(144, 595, 295, 595, 500, (long) 500, 4);
                    }

                    result = mFairy.findPic("shou.png");
                    if (result.sim > 0.8f) {
                        mFairy.ranSwipe(197, 608, 197, 582, 800, 200);
                        mFairy.onTap(0.8f, result, "手", 2000);
                        mFairy.initMatTime();
                        return;
                    }

                } else {

                    result = mFairy.findPic(997, 57, 1152, 523, "ptfb2.png");
                    mFairy.onTap(0.8f, result, "选择副本", 500);

                    result = mFairy.findPic("ptfb3.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        FindResult fb = null;

                        result = mFairy.findPic("knfb2.png");
                        mFairy.onTap(0.8f, result, "困难副本", 1000);

                        switch (i) {
                            case 1:
                                fb = mFairy.findPic(170, 117, 1119, 190, "dxmo2.png");
                                if (fb.sim > 0.8f) {

                                    result = mFairy.findPic(fb.x - 10, fb.y + 202, fb.x + 156, fb.y + 342, "ptfb15.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(1134, 81, 1156, 97, "", 500);
                                        setTaskEnd();
                                        return;
                                    }
                                    mFairy.onTap(0.8f, fb, fb.x, fb.y + 200, fb.x + 50, fb.y + 250, "地下魔祸", 1500);
                                }

                                break;
                            case 2:
                                fb = mFairy.findPic(170, 117, 1119, 190, "fdyy2.png");
                                if (fb.sim > 0.8f) {
                                    result = mFairy.findPic(fb.x - 10, fb.y + 202, fb.x + 156, fb.y + 342, "ptfb15.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(1134, 81, 1156, 97, "", 500);
                                        setTaskEnd();
                                        return;
                                    }
                                    mFairy.onTap(0.8f, fb, fb.x, fb.y + 200, fb.x + 50, fb.y + 250, "分定阴阳", 1500);
                                }

                                break;
                            case 3:
                                fb = mFairy.findPic(170, 117, 1119, 190, "xhlz2.png");
                                if (fb.sim > 0.8f) {

                                    result = mFairy.findPic(fb.x - 10, fb.y + 202, fb.x + 156, fb.y + 342, "ptfb15.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(1134, 81, 1156, 97, "", 500);
                                        setTaskEnd();
                                        return;
                                    }
                                    mFairy.onTap(0.8f, fb, fb.x, fb.y + 200, fb.x + 50, fb.y + 250, "西海龙战", 1500);
                                }

                                break;
                            case 4:
                                fb = mFairy.findPic(170, 117, 1119, 190, "dhyf2.png");
                                if (fb.sim > 0.8f) {

                                    result = mFairy.findPic(fb.x - 10, fb.y + 202, fb.x + 156, fb.y + 342, "ptfb15.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(1134, 81, 1156, 97, "", 500);
                                        setTaskEnd();
                                        return;
                                    }
                                    mFairy.onTap(0.8f, fb, fb.x, fb.y + 200, fb.x + 50, fb.y + 250, "东海妖风", 1500);
                                }
                                break;
                        }

                        if (frequencyMap("ran_fb", 3)) {
                            mFairy.ranSwipe(951, 368, 433, 368, 500, (long) 1000, 3);
                            return;
                        }

                        result = mFairy.findPic("ptfb4.png");
                        if (result.sim > 0.8f) {

                            result = mFairy.findPic("ptfb7.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(1135, 77, 1155, 97, "", 500);
                                setTaskEnd();
                                return;
                            }

                            mFairy.onTap(962, 603, 1002, 620, "开启", 2000);
                        }
                    }

                    result = mFairy.findPic(535, 2, 704, 42, "ptfb8.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                    }

                    if (gamePublicFuntion.mainScene()) {
                        if (timeMap("jihe", 300000, true)) {
                            gamePublicFuntion.rank();

                            result = mFairy.findPic("jihe.png");
                            mFairy.onTap(0.72f, result, "集合", 500);
                        }
                        gamePublicFuntion.judgeStop(10, true, 0.85f);
                    }
                }
            }
        };
    }//困难副本....未修改

    public void yb() throws Exception {
        new TeamTask_Content(mFairy, "组队运镖") {

            void create() throws Exception {
                super.create();
                activity_type = 2;
                activity_name = "yb.png";
            }

            void content_02() throws Exception {
                rank(new String[]{"yb7.png"}, "sf.png", new String[]{"yb6.png"});
            }

            void cancel() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic("yb2.png");
                    mFairy.onTap(0.8f, result, 814, 436, 860, 450, "离镖车太远 - 同意", 500);

                    result = mFairy.findPic(335, 245, 940, 404, "jia1.png");
                    mFairy.onTap(0.8f, result, 805, 435, 864, 449, "是否召回", 500);

                    if (frequencyMap("qx", 1)) {
                        mFairy.onTap(0.8f, qx, "取消", 500);
                    }
                } else {
                    frequencyInit("qx");
                }
            }

            void content_03() throws Exception {
                timeCount(5, 0);

                result = mFairy.findPic("chat2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("yb11.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "活跃不足", 500);
                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic("yb10.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1084, 591, 1119, 623, "", 500);

                    setTaskName(2);
                    return;
                } else {
                    result = mFairy.findPic(966, 197, 1186, 600, "yb3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "确定前往下一镖", 2000);
                        return;
                    }


                    switch (AtFairyConfig.getOption("zdyb")) {
                        case "1":
                            result = mFairy.findPic(966, 197, 1186, 600, "yb8.png");
                            if (result.sim > 0.8f) {
                                frequencyInit("chat2");
                                mFairy.onTap(0.8f, result, "风雨镖", 500);
                                return;
                            }
                            break;
                        case "2":
                            result = mFairy.findPic(966, 197, 1186, 600, "yb9.png");
                            if (result.sim > 0.8f) {
                                frequencyInit("chat2");
                                mFairy.onTap(0.8f, result, "天险镖", 500);
                                return;
                            }
                            break;
                    }

                    if (frequencyMap("chat2", 2)) {
                        gamePublicFuntion.chat2();
                    }
                }


                result = mFairy.findPic("yb4.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(2000);
                    err = 0;
                }

                if (gamePublicFuntion.fb()) {
                    gamePublicFuntion.auto();
                    mFairy.initMatTime();
                }

                gamePublicFuntion.task();

                if (gamePublicFuntion.mainScene()) {
                    frequencyInit("chat2");
                    if (timeMap("jihe", 180000, true)) {
                        gamePublicFuntion.rank();

                        result = mFairy.findPic("jihe.png");
                        mFairy.onTap(0.72f, result, "集合", 500);
                        mFairy.onTap(0.72f, result, "集合", 500);
                    }
                    if (gamePublicFuntion.judgeStop(6, true, 0.85f)) {
                        FindResult task = mFairy.findPic(17, 117, 83, 398, "yb5.png");
                        if (task.sim > 0.7f) {
                            mFairy.onTap(0.7f, task, "taskClick", 2000);
                            mFairy.initMatTime();
                            return;
                        }
                        taskSlide.slideRange(new int[]{2, 4}, 2, 0);
                    }
                }
            }
        };
    }//组队运镖

    /**
     * 限时任务
     */
    public void hgfz() throws Exception {
        new TeamTask_Content(mFairy, "皇宫飞贼") {

            void create() throws Exception {
                super.create();
                activity_type = 3;
                activity_name = "hgfz.png";
            }

            void content_02() throws Exception {
                rank(new String[]{"hgfz2.png"}, "hgfz1.png", null);
            }

            void cancel() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic(335, 245, 940, 404, "jia1.png");
                    mFairy.onTap(0.8f, result, 805, 435, 864, 449, "是否召回", 500);

                    if (frequencyMap("qx", 1)) {
                        mFairy.onTap(0.8f, qx, "取消", 500);
                    }
                } else {
                    frequencyInit("qx");
                }
            }

            void content_03() throws Exception {
                timeCount(6, 0);

                gamePublicFuntion.fhs(2);

                gamePublicFuntion.task();

                result = mFairy.findPic(943, 27, 1219, 537, new String[]{"hgfz3.png", "hgfz4.png"});
                mFairy.onTap(0.8f, result, "让我来!", 500);

                result = mFairy.findPic("hgfz5.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(724, 115, 1117, 171, "hgfz6.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(result.x + 1, result.y - 25, result.x + 2, result.y - 23, "抓贼", 1000);

                        for (int i = 0; i < 3; i++) {

                            result = mFairy.findPic(208, 109, 1125, 630, "hgfz7.png");
                            mFairy.onTap(0.8f, result, "选择贼", 500);
                        }

                        gamePublicFuntion.close(3);
                    } else {
                        mFairy.onTap(754, 106, 768, 123, "", 500);
                    }

                    if (frequencyMap("fzError", 8)) {
                        gamePublicFuntion.close(3);
                        return;
                    }
                } else {
                    frequencyInit("fzError");
                }

                if (gamePublicFuntion.fb()) {
                    err = 0;
                    if (gamePublicFuntion.lv() == false) {
                        gamePublicFuntion.auto();
                        Thread.sleep(3000);
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (timeMap("jihe", 180000, true)) {
                        gamePublicFuntion.rank();

                        result = mFairy.findPic("jihe.png");
                        mFairy.onTap(0.72f, result, "集合", 500);
                        mFairy.onTap(0.72f, result, "集合", 500);
                    }

                    if (gamePublicFuntion.judgeStop(5, true, 0.85f)) {
                        result = mFairy.findPic(5, 119, 69, 426, "hgfz8.png");
                        if (result.sim > 0.7f) {
                            err = 0;
                            mFairy.onTap(0.7f, result, "皇宫飞贼", 3000);
                            mFairy.initMatTime();
                            return;
                        }
                        taskSlide.slideRange(new int[]{3, 4, 5}, 2, 0);
                    }
                }
            }
        };
    }//皇宫飞贼

    public void qzsm() throws Exception {
        new TeamTask_Content(mFairy, "勤政事民") {

            void create() throws Exception {
                super.create();
                activity_type = 3;
                activity_name = "qzsm.png";
            }

            void content_02() throws Exception {
                rank(new String[]{"qzsm2.png"}, "jjzz1.png", new String[]{"qzsm1.png"});
            }

            void cancel() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic(335, 245, 940, 404, "jia1.png");
                    mFairy.onTap(0.8f, result, 805, 435, 864, 449, "是否召回", 500);

                    if (frequencyMap("qx", 1)) {
                        mFairy.onTap(0.8f, qx, "取消", 500);
                    }
                } else {
                    frequencyInit("qx");
                }
            }

            void content_03() throws Exception {
                timeCount(10, 0);

                gamePublicFuntion.fhs(2);

                gamePublicFuntion.task();

                result = mFairy.findPic("qzsm3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("qzsm4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1214, 48, 1236, 72, "", 500);
                        return;
                    }

                    mFairy.onTap(597, 591, 674, 607, "", 200);
                    mFairy.onTap(280, 591, 350, 610, "", 300);

                    if (mapCount(0.8f, 379, 136, 914, 480, "qzsm6.png")) {
                        singleTask.li_rank();
                        setTaskName(0);
                        return;
                    }

                } else {
                    result = mFairy.findPic(new String[]{"chat1.png", "nn11.png"});
                    if (result.sim > 0.8f) {
                        err = 0;
                        mFairy.onTap(1053, 458, 1111, 475, "chat", 500);
                        return;
                    }
                }


                if (frequencyMap("chat", 3)) {
                    gamePublicFuntion.chat2();
                }

                if (gamePublicFuntion.fb()) {
                    err = 0;
                    if (gamePublicFuntion.lv() == false) {
                        gamePublicFuntion.auto();
                        Thread.sleep(3000);
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    frequencyInit("chat2");
                    if (timeMap("jihe", 180000, true)) {
                        gamePublicFuntion.rank();

                        result = mFairy.findPic("jihe.png");
                        mFairy.onTap(0.72f, result, "集合", 500);
                        mFairy.onTap(0.72f, result, "集合", 500);
                    }

                    if (gamePublicFuntion.judgeStop(5, true, 0.85f)) {
                        result = mFairy.findPic(5, 119, 69, 426, "qzsm5.png");
                        if (result.sim > 0.7f) {
                            err = 0;
                            mFairy.onTap(0.7f, result, "官职", 3000);
                            mFairy.initMatTime();
                            return;
                        }
                        taskSlide.slideRange(new int[]{3, 4, 5}, 2, 0);
                    }
                }
            }
        };
    }//勤政事民

    private boolean xycm = false;

    public void xycm() throws Exception {
        new TeamTask_Content(mFairy, "降妖除魔") {

            void create() throws Exception {
                super.create();
                activity_type = 3;
                activity_name = "xycm.png";
                xycm = false;
            }

            void content_02() throws Exception {
                if (xycm) {
                    xycm = false;
                    setTaskName(3);
                    err = 2;
                    return;
                }
                rank(new String[]{"xycm3.png"}, "xycm1.png", new String[]{"xycm2.png"});

            }

            void cancel() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic(335, 245, 940, 404, "jia1.png");
                    mFairy.onTap(0.8f, result, 805, 435, 864, 449, "是否召回", 500);

                    if (frequencyMap("qx", 1)) {
                        mFairy.onTap(0.8f, qx, "取消", 500);
                    }
                } else {
                    frequencyInit("qx");
                }
            }

            void content_03() throws Exception {
                timeCount(10, 0);

                gamePublicFuntion.fhs(2);

                gamePublicFuntion.task();

                result = mFairy.findPic(943, 27, 1219, 537, "xycm4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "降妖除魔", 500);
                    err = 0;
                }

                if (gamePublicFuntion.fb()) {
                    err = 0;
                    xycm = true;
                    Thread.sleep(2000);
                    frequencyInit("fb");
                    if (oneSecond()) {
                        gamePublicFuntion.auto();
                    }
                    mFairy.initMatTime();
                } else {
                    if (frequencyMap("fb", 2)) {
                        oneSecond = 0;
                        gamePublicFuntion.auto1(2);
                        if (xycm) {
                            setTaskName(1);
                        }
                        return;
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (timeMap("jihe", 180000, true)) {
                        gamePublicFuntion.rank();

                        result = mFairy.findPic("jihe.png");
                        mFairy.onTap(0.72f, result, "集合", 500);
                        mFairy.onTap(0.72f, result, "集合", 500);
                    }

                    if (gamePublicFuntion.judgeStop(5, true, 0.85f)) {
                        result = mFairy.findPic(3, 122, 108, 418, "xycm5.png");
                        if (result.sim > 0.7f) {
                            err = 0;
                            mFairy.onTap(0.7f, result, "降妖除魔", 3000);
                            mFairy.initMatTime();
                            return;
                        }
                        taskSlide.slideRange(new int[]{4, 6, 8}, 2, 0);
                    }
                }
            }
        };
        gamePublicFuntion.init(0);
        mFairy.ranSwipe(212, 508, 228, 508, 500, (long) 500, 1);
    }//降妖除魔....未修改

    class GenContent extends TaskContent {
        public GenContent(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);
        }

        void init() throws Exception {
            gamePublicFuntion.init(1);
            setTaskName(1);
            oneSecond = 0;
            bool_rank = false;
        }

        boolean activity_end(FindResult act) throws Exception {

            return false;
        }

        void content_01() throws Exception {
            if (timeCount(10, 0)) {
                if (frequencyMap("actError", 1)) {
                    setTaskEnd();
                    return;
                }
            }
            result = mFairy.findPic("activity.png");
            if (result.sim > 0.75f) {
                GamePublicFuntion.activity_bool = false;
                mFairy.onTap(0.75f, result, "活动按钮", 800);
            }

            result = mFairy.findPic("activity1.png");
            if (result.sim > 0.8f) {
                if (oneSecond()) {
                    gamePublicFuntion.activity_type(activity_type);

                    result = mFairy.findPic("activity3.png");
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 5; i++) {

                            result = mFairy.findPic(617, 55, 660, 575, "activity2.png");
                            mFairy.onTap(0.75f, result, result.x + 30, result.y + 0, result.x + 40, result.y + 1, "领取活跃", 500);
                        }
                    }
                }

                FindResult act = mFairy.findPic(818, 35, 1009, 559, activity_name);
                if (act.sim > 0.85f) {

                    if (activity_end(act)) {
                        oneSecond = 0;
                        return;
                    }

                    result = mFairy.findPic(act.x + 223, act.y - 30, act.x + 350, act.y + 71, "wan.png");
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(act.x + 223, act.y - 10, act.x + 354, act.y + 71, "qian.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1221, 37, 1247, 57, "", 500);
                        oneSecond = 0;
                        frequencyInit("actError");
                        setTaskName(2);
                        return;
                    }
                }

                if (GamePublicFuntion.activity_bool == false) {
                    activitySlide.slideRange(new int[]{3, 5, 6, 7}, 2);
                } else {
                    activitySlide.slideRange(new int[]{3, 5, 6, 7, 8}, 2, 0);
                }
            }

        }//活动

        void inOperation() throws Exception {
            gamePublicFuntion.lv();
            gamePublicFuntion.skipScene();
            gamePublicFuntion.chat();
            gamePublicFuntion.deng();
            gamePublicFuntion.fhs(2);
            gamePublicFuntion.rank();
            cancel();
            use();
            result = mFairy.findPic("ling3.png");
            mFairy.onTap(0.8f, result, 1213, 52, 1232, 70, "", 1000);
            result = mFairy.findPic(1045, 3, 1265, 50, "nn2.png");
            mFairy.onTap(0.85f, result, "跳过教学", 500);

            result = mFairy.findPic(962, 562, 1077, 636, "map.png");
            mFairy.onTap(0.75f, result, 1123, 67, 1147, 86, "", 500);

            result = mFairy.findPic("rank3.png");
            mFairy.onTap(0.85f, result, "跟随", 500);

            result = mFairy.findPic(1000, 588, 1198, 702, "gen2.png");
            mFairy.onTap(0.8f, result, "同意进入副本1", 500);

            result = mFairy.findPic(774, 108, 941, 261, "gen3.png");
            mFairy.onTap(0.8f, result, "同意进入副本2", 500);

            result = mFairy.findPic(577, 275, 683, 649, "ok.png");
            mFairy.onTap(0.8f, result, "ok", 500);

            if (AtFairyConfig.getOption("jj").equals("1")) {
                if (gamePublicFuntion.fb()) {
                    if (timeMap("jj", 180000, true)) {
                        mFairy.onTap(1032, 433, 1054, 447, "开始释放绝技", 1000);
                        mFairy.onTap(1033, 332, 1058, 350, "", 500);
                        mFairy.onTap(1216, 641, 1234, 662, "", 500);
                        gamePublicFuntion.close(3);
                    }
                }
            }
        }

        void cancel() throws Exception {
            result = gamePublicFuntion.qx();
            mFairy.onTap(0.8f, result, "取消", 500);
        }

        void use() throws Exception {
            gamePublicFuntion.use();
        }//使用

        void rank(String[] img, String mb_str1, String[] mb_str2) throws Exception {
            timeCount(10, 0);

            if (bool_rank == false) {
                frequencyInit("errRank_count");

                result = mFairy.findPic("rank1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("发现创建队伍 >>> 队伍状态判断成功"));
                    bool_rank = true;
                    bool_genrank_state = false;
                    return;
                }

                gamePublicFuntion.rank();
                gamePublicFuntion.close(1);
                if (gamePublicFuntion.mainScene()) {

                    result = mFairy.findPic(13, 427, 52, 470, "rank8.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        result = mFairy.findPic(new String[]{"rank2.png", "rank3.png"});
                        if (result.sim > 0.85f) {

                            LtLog.e(mFairy.getLineInfo("发现跟随>>>队伍状态判断成功"));
                            frequencyInit("genRank");
                            bool_rank = true;
                            bool_genrank_state = true;
                            return;
                        } else {
                            if (frequencyMap("genRank", 2)) {
                                LtLog.e(mFairy.getLineInfo("发现是队长>>>队伍状态判断成功"));
                                bool_rank = true;
                                bool_genrank_state = false;
                                return;
                            }
                        }
                    }

                    result = mFairy.findPic(1, 124, 40, 178, "rank23.png");
                    if (result.sim > 0.75f) {
                        err = 0;
                        if (timeMap("ppRank", 300000, false)) {
                            LtLog.e(mFairy.getLineInfo("匹配超时>>>"));
                            bool_rank = true;
                            bool_genrank_state = true;
                            return;
                        }
                    }
                    return;
                }
                gamePublicFuntion.close(1);
            } else {
                frequencyInit("genRank");
                result = mFairy.findPic("rank7.png");
                if (result.sim > 0.8f) {

                    frequencyInit("errRank_count");

                    if (frequencyMap("mbError", 7)) {
                        mFairy.onTap(1092, 99, 1115, 121, "", 500);
                        return;
                    }

                    result = mFairy.findPic(181, 177, 445, 632, mb_str1);
                    if (result.sim > 0.85f) {

                        result = mFairy.findPic(result.x + 50, result.y - 20, result.x + 200, result.y + 43, "mb1.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "点击 str1", 1000);
                            err = 4;
                            if (mb_str2 == null) {
                                err = 0;
                                for (int i = 0; i < 3; i++) {

                                    result = mFairy.findPic(957, 175, 1083, 560, "rank22.png");
                                    mFairy.onTap(0.9f, result, "申请", 500);
                                }

                                result = mFairy.findPic("rank21.png");
                                mFairy.onTap(0.9f, result, "开始匹配", 3000);

                                gamePublicFuntion.close(3);

                                bool_rank = false;

                                return;
                            }
                        }
                    }

                    if (mb_str2 != null) {
                        result = mFairy.findPic(181, 177, 445, 632, mb_str2);
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "str2", 1000);
                            err = 0;
                            for (int i = 0; i < 3; i++) {

                                result = mFairy.findPic(957, 175, 1083, 560, "rank22.png");
                                mFairy.onTap(0.9f, result, "申请", 500);
                            }

                            result = mFairy.findPic("rank21.png");
                            mFairy.onTap(0.9f, result, "开始匹配", 3000);

                            gamePublicFuntion.close(3);

                            bool_rank = false;
                            return;
                        }
                    }
                    rankmbSlide.slideRange(new int[]{3, 4, 5, 6}, 2, 0);
                    return;
                } else {
                    frequencyInit("mbError");
                }

                result = mFairy.findPic(1, 1, 85, 214, "rankScene.png");
                LtLog.e(mFairy.getLineInfo("组队场景 sim:" + result.sim));
                if (result.sim > 0.85f) {
                    err = 0;

                    result = mFairy.findPic("yao1.png");
                    mFairy.onTap(0.85f, result, 1180, 66, 1200, 82, "批准", 500);

                    result = mFairy.findPic(617, 519, 1256, 681, "rank6.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "便捷组队", 500);
                        bool_genrank_state = true;
                        return;
                    }

                    if (bool_genrank_state == false) {
                        result = mFairy.findPic("rank5.png");
                        mFairy.onTap(0.85f, result, "退出队伍", 500);
                        return;
                    }

                    if (mFairy.findPic(190, 66, 517, 165, img).sim > 0.8f || mFairy.findPic("rank24.png").sim > 0.8f) {
                        mFairy.onTap(1214, 36, 1231, 52, "", 500);
                        setTaskName(3);
                        return;
                    }

                    if (frequencyMap("errRank_count", 10)) {
                        gamePublicFuntion.close(3);
                        bool_rank = false;
                        return;
                    }

                    return;
                }
                gamePublicFuntion.clickRank();
            }
        }

        boolean exceptional_case() throws Exception {
            return false;
        }

        void content_03() throws Exception {
            timeCount(20, 0);
            Thread.sleep(1000);

            gamePublicFuntion.close(1);

            result = mFairy.findPic("ptfb9.png");
            mFairy.onTap(0.8f, result, "继续挂机", 500);

            result = mFairy.findPic("ptfb18.png");
            mFairy.onTap(0.8f, result, "继续", 500);

            result = mFairy.findPic("ptfb13.png");
            mFairy.onTap(0.8f, result, "跳过旁白", 500);

            result = mFairy.findPic(535, 2, 704, 42, "ptfb8.png");
            if (result.sim > 0.8f) {
                err = 0;
            }

            result = mFairy.findPic("rank1.png");
            if (result.sim > 0.85f) {
                LtLog.e(mFairy.getLineInfo("发现创建队伍 >>> 跳转"));
                gamePublicFuntion.init(1);
                setTaskName(1);
                bool_rank = true;
                bool_genrank_state = false;
                return;
            }

            if (gamePublicFuntion.mainScene()) {

                result = mFairy.findPic(13, 427, 52, 470, "rank8.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(new String[]{"rank2.png", "rank3.png"});
                    if (result.sim > 0.85f) {
                        frequencyInit("genRank");
                    } else {
                        if (frequencyMap("genRank", 3)) {
                            LtLog.e(mFairy.getLineInfo("跟随长时间未发现 >>> 跳转"));
                            gamePublicFuntion.init(1);
                            setTaskName(1);
                            bool_rank = true;
                            bool_genrank_state = false;
                            return;
                        }
                    }
                }

                if (gamePublicFuntion.fb()) {
                    err = 0;
                    if (gamePublicFuntion.judgeBattle()) {
                        mFairy.initMatTime();
                    }
                }

                result = mFairy.findPic("shengddian.png");
                if (result.sim > 0.75f) {
                    Thread.sleep(2000);
                    return;
                } else {
                    if (gamePublicFuntion.judgeStop(480, false, 0.85f)) {
                        LtLog.e(mFairy.getLineInfo("长时间发呆 >>> 跳转"));
                        mFairy.initMatTime();
                        if (exceptional_case()) {
                            return;
                        }

                        gamePublicFuntion.init(1);
                        setTaskName(1);
                        bool_rank = true;
                        bool_genrank_state = false;
                        return;
                    }
                }
            }
        }
    }

    public void gen_ptfb(final int i) throws Exception {
        new GenContent(mFairy, "跟队-普通副本") {

            void create() throws Exception {
                super.create();
                activity_type = 1;
                activity_name = "ptfb.png";
            }

            void content_02() throws Exception {
                switch (i) {
                    case 1:
                        rank(new String[]{"dxmo.png", "fdyy.png"}, "ptfb16.png", new String[]{"dxmo3.png", "fdyy3.png"});
                        break;
                    case 2:
                        rank(new String[]{"xhlz.png", "dhyf.png"}, "ptfb16.png", new String[]{"xhlz3.png", "dhyf3.png"});
                        break;
                }
            }

            void content_03() throws Exception {
                super.content_03();

                result = mFairy.findPic("ptfb14.png");
                if (result.sim > 0.8f) {

                    for (int i = 0; i < 4; i++) {

                        result = modularLookup(1113, 204, 1191, 300, "shaizi.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "骰子", 500);
                            modularLookup++;
                        }

                    }
                    modularLookup = 0;

                    mFairy.onTap(1182, 65, 1202, 83, "", 1000);
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(1000);

                        gamePublicFuntion.init(0);

                        if (gamePublicFuntion.fb() == false) {
                            Thread.sleep(2000);
                            break;
                        }
                    }
                    setTaskEnd();
                    return;
                }
            }
        };
    }//跟队-普通副本....未修改

    public void gen_knfb(final int i) throws Exception {
        new GenContent(mFairy, "跟队-困难副本") {

            void create() throws Exception {
                super.create();
                activity_type = 1;
                activity_name = "knfb.png";
            }

            void content_02() throws Exception {
                switch (i) {
                    case 1:
                        rank(new String[]{"dxmo.png"}, "knfb3.png", new String[]{"dxmo3.png"});
                        break;
                    case 2:
                        rank(new String[]{"fdyy.png"}, "knfb3.png", new String[]{"fdyy3.png"});
                        break;
                    case 3:
                        rank(new String[]{"xhlz.png"}, "knfb3.png", new String[]{"xhlz3.png"});
                        break;
                    case 4:
                        rank(new String[]{"dhyf.png"}, "knfb3.png", new String[]{"dhyf3.png"});
                        break;
                }
            }

            void content_03() throws Exception {
                super.content_03();

                result = mFairy.findPic("ptfb14.png");
                if (result.sim > 0.8f) {

                    for (int i = 0; i < 4; i++) {

                        result = modularLookup(1113, 204, 1191, 300, "shaizi.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "骰子", 500);
                            modularLookup++;
                        }

                    }
                    modularLookup = 0;

                    mFairy.onTap(1182, 65, 1202, 83, "", 1000);
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(1000);

                        gamePublicFuntion.init(0);

                        if (gamePublicFuntion.fb() == false) {
                            Thread.sleep(2000);
                            break;
                        }
                    }
                    setTaskEnd();
                    return;
                }
            }
        };
    }//跟队-困难副本....未修改

    public void gen_zg() throws Exception {
        new GenContent(mFairy, "跟队-捉鬼") {

            void init() throws Exception {
                gamePublicFuntion.init(1);
                if (zg) {
                    setTaskName(2);
                } else {
                    setTaskName(1);
                }
                oneSecond = 0;
                bool_rank = false;
            }

            void create() throws Exception {
                super.create();
                activity_type = 1;
                activity_name = "zg.png";

                if (AtFairyConfig.getOption("zg").equals("2")) {
                    zg = true;
                } else {
                    zg = false;
                }

                long i = getTimeStamp(AtFairyConfig.getOption("cb"));
                if (i != -1) {
                    cb_time = i;
                }
            }

            boolean activity_end(FindResult act) throws Exception {
                if (zg == false) {
                    result = mFairy.findPic(act.x + 80, act.y + 2, act.x + 180, act.y + 66,
                            new String[]{"zg11.png", "zg12.png"});
                    if (result.sim > 0.92f) {
                        LtLog.e(mFairy.getLineInfo("捉鬼活跃度已满"));
                        setTaskEnd();
                        return true;
                    }
                }
                return false;
            }

            void content_02() throws Exception {
                rank(new String[]{"zg1.png"}, "zg2.png", null);
            }

            boolean exceptional_case() throws Exception {
                if (zg) {
                    setTaskName(2);
                    gamePublicFuntion.init(1);
                    bool_rank = true;
                    bool_genrank_state = false;
                    return true;
                }
                return false;
            }

            void content_03() throws Exception {
                super.content_03();

                if (timeMap("cb", cb_time, false)) {
                    singleTask.chubei();
                    gamePublicFuntion.close(3);
                }

                if (zg == false && timeMap("huoyue", 600000, false)) {
                    setTaskName(0);
                    return;
                }
            }
        };
    }

    private boolean gen_ywz = true;

    public boolean gen_ywz() throws Exception {
        gen_ywz = true;
        new GenContent(mFairy, "妖王战") {

            void create() throws Exception {
                super.create();
                activity_type = 1;
                activity_name = "ywz.png";
            }

            void inOperation() throws Exception {
                super.inOperation();
                minute = mFairy.dateMinute();
                if ((minute > 10 && minute < 30) || (minute > 40 && minute < 59)) {
                    if (gamePublicFuntion.fb() == false) {
                        if (frequencyMap("end_time", 2)) {
                            setTaskEnd();
                            gen_ywz = false;
                            return;
                        }
                    }
                }
            }

            void content_02() throws Exception {
                rank(new String[]{"ywz2.png"}, "fy5.png", new String[]{"ywz7.png"});
            }

            void content_03() throws Exception {
                super.content_03();

                result = mFairy.findPic("ptfb14.png");
                if (result.sim > 0.8f) {

                    for (int i = 0; i < 4; i++) {

                        result = modularLookup(1113, 204, 1191, 300, "shaizi.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "骰子", 500);
                            modularLookup++;
                        }

                    }

                    modularLookup = 0;

                    mFairy.onTap(1182, 65, 1202, 83, "", 1000);
                }
            }
        };
        return gen_ywz;
    }//妖王战

    private boolean gen_xs = true;

    public boolean gen_xs() throws Exception {
        gen_xs = true;

        new GenContent(mFairy, "二十八星宿") {
            void create() throws Exception {
                super.create();
                activity_type = 1;
                activity_name = "xs.png";
            }

            void inOperation() throws Exception {
                super.inOperation();
                minute = mFairy.dateMinute();
                if (minute > 40) {
                    if (gamePublicFuntion.fb() == false) {
                        if (frequencyMap("end_time", 2)) {
                            setTaskEnd();
                            gen_xs = false;
                            return;
                        }
                    }
                }
            }

            void content_02() throws Exception {
                rank(new String[]{"xs3.png"}, "xs1.png", new String[]{"xs2.png"});
            }
        };
        return gen_xs;
    }//二十八星宿

    public void gen_jjzz() throws Exception {
        new GenContent(mFairy, "加急奏章") {

            void create() throws Exception {
                super.create();
                activity_type = 3;
                activity_name = "jjzz.png";
            }

            void inOperation() throws Exception {
                super.inOperation();
                minute = mFairy.dateMinute();
                if (minute > 30) {
                    if (gamePublicFuntion.fb() == false) {
                        if (frequencyMap("end_time", 2)) {
                            setTaskEnd();
                            return;
                        }
                    }
                }
            }

            void content_02() throws Exception {
                rank(new String[]{"jjzz3.png"}, "jjzz1.png", new String[]{"jjzz2.png"});
            }

            void content_03() throws Exception {
                super.content_03();
            }
        };
    }//加急奏章

    public void gen() throws Exception {
        new TaskContent(mFairy, "固定队跟队") {

            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(30, 0);

                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic("ytdg7.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 797, 439, 868, 454, "继续下一层", 8000);
                        return;
                    }

                    result = mFairy.findPic("ptfb9.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "继续挂机", 500);
                        return;
                    }

                    result = mFairy.findPic("ptfb18.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "继续", 500);
                        return;
                    }

                    if (frequencyMap("qx", 3)) {
                        mFairy.onTap(0.8f, qx, "取消", 500);
                    }
                } else {
                    frequencyInit("qx");
                }


                if (gamePublicFuntion.fb()) {
                    err = 0;
                    if (AtFairyConfig.getOption("jj").equals("1")) {
                        if (timeMap("jj", 180000, true)) {
                            mFairy.onTap(1032, 433, 1054, 447, "开始释放绝技", 1000);
                            mFairy.onTap(1033, 332, 1058, 350, "", 500);
                            mFairy.onTap(1216, 641, 1234, 662, "", 500);
                            gamePublicFuntion.close(3);
                        }
                    }
                }

                gamePublicFuntion.rank();

                gamePublicFuntion.fhs(2);


                result = mFairy.findPic("rank3.png");
                mFairy.onTap(0.85f, result, "跟随", 500);

                result = mFairy.findPic(338, 477, 683, 530, "gen4.png");
                mFairy.onTap(0.72f, result, 782, 518, 801, 537, "确定跟随", 500);

                result = mFairy.findPic("ling3.png");
                mFairy.onTap(0.8f, result, 1213, 52, 1232, 70, "", 1000);

                result = mFairy.findPic(1000, 588, 1198, 702, "gen2.png");
                mFairy.onTap(0.8f, result, "同意进入副本1", 500);

                result = mFairy.findPic(774, 108, 941, 261, "gen3.png");
                mFairy.onTap(0.8f, result, "同意进入副本2", 500);

                result = mFairy.findPic(577, 275, 683, 649, "ok.png");
                mFairy.onTap(0.8f, result, "ok", 500);

                result = mFairy.findPic(1045, 3, 1265, 50, "nn2.png");
                mFairy.onTap(0.85f, result, "跳过教学", 500);


                result = mFairy.findPic("ptfb13.png");
                mFairy.onTap(0.8f, result, "跳过旁白", 500);

                result = mFairy.findPic("hgfz5.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(724, 115, 1117, 171, "hgfz6.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(result.x + 1, result.y - 25, result.x + 2, result.y - 23, "抓贼", 1000);

                        for (int i = 0; i < 3; i++) {

                            result = mFairy.findPic(208, 109, 1125, 630, "hgfz7.png");
                            mFairy.onTap(0.8f, result, "选择贼", 500);
                        }

                        gamePublicFuntion.close(3);
                    } else {
                        mFairy.onTap(754, 106, 768, 123, "", 500);
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(10, true, 0.85f)) {

                    }
                }

            }
        };

    }//跟

    /**
     * other
     */
    private int go1, go2, go3, go4, go5 = 0, go6 = 0, go7 = 0, go8 = 0, go9 = 0;

    public void xianshi() throws Exception {
        new TaskContent(mFairy, "限时任务") {

            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.mainScene()) {
                    err = 0;
                }

                result = gamePublicFuntion.qx();
                mFairy.onTap(0.8f, result, "取消", 500);

                result = mFairy.findPic("ptfb14.png");
                if (result.sim > 0.8f) {

                    for (int i = 0; i < 4; i++) {

                        result = modularLookup(1113, 204, 1191, 300, "shaizi.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "骰子", 500);
                            modularLookup++;
                        }

                    }

                    modularLookup = 0;

                    mFairy.onTap(1182, 65, 1202, 83, "", 1000);
                }

                if (xs()) {
                    setTaskName(0);
                    return;
                }
            }

            boolean xs() throws Exception {

                boolean bool = false;

                while (mFairy.condit()) {
                    week = mFairy.week();
                    hour = mFairy.dateHour();
                    minute = mFairy.dateMinute();

                    if (hour == 0 || hour == 24) {
                        go1 = 0;
                        go2 = 0;
                        go3 = 0;
                        go4 = 0;
                        go5 = 0;
                        go6 = 0;
                        go7 = 0;
                        go8 = 0;
                        go9 = 0;
                    }

                    if (AtFairyConfig.getOption("hgfz").equals("1") && go1 == 0) {
                        if ((hour == 12 && minute > 30) || (hour > 12 && hour < 24)) {
                            hgfz();
                            go1 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("xycm").equals("1") && go2 == 0) {
                        if ((week == 6 || week == 7) && hour >= 14) {
                            xycm();
                            go2 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("jjzz").equals("1") && go6 == 0) {
                        if (hour == 19 && minute < 30) {
                            gen_jjzz();
                            go6 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("bpyx").equals("1") && go7 == 0) {
                        if (hour == 20 && minute > 10 && minute < 25) {
                            singleTask.li_rank();
                            singleTask.bpyx();
                            go7 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("jzhs").equals("1") && go8 == 0) {
                        if ((week == 1 || week == 3 || week == 5)
                                && ((hour == 20 && minute > 30) || (hour == 21 && minute < 30))) {
                            singleTask.li_rank();
                            singleTask.jzhs();
                            go8 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("qzsm").equals("1") && go9 == 0) {
                        if (hour >= 12) {
                            qzsm();
                            go9 = 1;
                            bool = true;
                            continue;
                        }
                    }


                    if (AtFairyConfig.getOption("ywz").equals("1") && go3 == 0) {
                        if (hour >= 12 && ((minute >= 0 && minute <= 10) || (minute >= 30 && minute <= 40))) {
                            if (gen_ywz()) {
                                singleTask.li_rank();
                                go3 = 1;
                                bool = true;
                                continue;
                            }
                        }
                    }


                    if (AtFairyConfig.getOption("xs").equals("1") && go4 == 0) {
                        if ((hour >= 12 && hour < 23) && (minute > 20 && minute < 40)) {
                            if (gen_xs()) {
                                singleTask.li_rank();
                                go4 = 1;
                                bool = true;
                                continue;
                            }
                        }
                    }

                    if (AtFairyConfig.getOption("kjxs").equals("1") && go5 == 0) {
                        if (week != 7 && hour >= 11) {
                            singleTask.kjxs();
                            singleTask.li_rank();
                            go5 = 1;
                            bool = true;
                            continue;
                        }

                    }

                    break;
                }
                return bool;
            }

        };
    }//限时任务

}