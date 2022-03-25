package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

public class SingleTask {
    public GamePublicFuntion gamePublicFuntion;
    public AtFairyImpl mFairy;
    private int hour;
    private int minute;
    private int week;
    public String activity_name;
    public int activity_type;

    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }

    public void nn() throws Exception {
        new TaskContent(mFairy, "新手指引") {
            void init() throws Exception {
                setTaskName(1);

                gamePublicFuntion.close(3);

                mFairy.onTap(814, 644, 842, 675, "err", 500);
            }

            void nn() throws Exception {

                result = mFairy.findPic(1005, 641, 1263, 710, "ban.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("nn1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(396, 303, 450, 363, "经典剧情", 500);
                    mFairy.onTap(615, 658, 670, 674, "确定", 500);
                }

                result = mFairy.findPic(316, 43, 597, 162, "nn5.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(654, 177, 740, 287, "抚摸", 500);
                    return;
                }

                result = mFairy.findPic(154, 52, 364, 132, "nn2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(53, 42, 76, 64, "点击头像", 1000);
                    return;
                }

                result = mFairy.findPic("skill.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "一键潜修", 500);
                    mFairy.onTap(19, 10, 59, 28, "", 500);
                }

                result = mFairy.findPic( new String[]{"nn4.png","yingwei2.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "晋升影位", 500);
                    mFairy.onTap(19, 10, 59, 28, "", 500);
                }

                result = mFairy.findPic("nn6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(558, 363, 576, 381, "", 500);
                    mFairy.onTap(786, 415, 836, 438, "确定跳过剧情", 500);
                }


                result = mFairy.findPic("lu.png");
                if (result.sim < 0.8f) {
                    if (frequencyMap("lu", 2)) {
                        result = mFairy.findPic( "jia2.png");
                        if (result.sim > 0.8f) {
                            if (frequencyMap("battle", 2)) {
                                mFairy.onTap(1088, 519, 1129, 556, "a", 500);
                                mFairy.onTap(1088, 519, 1129, 556, "a", 500);
                                mFairy.onTap(1088, 519, 1129, 556, "a", 500);
                                mFairy.onTap(1030, 644, 1055, 669, "1", 500);
                                mFairy.onTap(970, 560, 995, 583, "2", 500);
                                mFairy.onTap(991, 446, 1016, 483, "3", 500);
                            }
                        }
                    }
                }

                result = mFairy.findPic(8, 3, 90, 51, "esc.png");
                if (result.sim > 0.75f) {
                    if (frequencyMap("esc", 3)) {
                        mFairy.onTap(0.75f, result, "esc", 500);
                    }
                } else {
                    frequencyInit("esc.png");
                }


                result = mFairy.findPic("nn8.png");
                mFairy.onTap(0.8f, result, 1195, 137, 1224, 159, "点击街拍", 500);

                result = mFairy.findPic(1078, 386, 1260, 548, "nn3.png");
                mFairy.onTap(0.8f, result, "跳", 500);
            }

            void content_01() throws Exception {
                timeCount(5, 0);

                nn();

                result = mFairy.findPic(new String[]{"tiao.png", "tiao1.png"});
                mFairy.onTap(0.8f, result, "跳过", 500);

                result = mFairy.findPic("chat.png");
                mFairy.onTap(0.8f, result, "聊天", 500);

                gamePublicFuntion.task();

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {


                        result = mFairy.findPic(66, 110, 236, 254, "nn7.png");
                        if (result.sim > 0.75f) {
                            setTaskEnd();
                            return;
                        }

                        result = mFairy.findPic(47, 91, 114, 458, "zhu.png");
                        mFairy.onTap(0.75f, result, "主线", 1000);

                        mFairy.initMatTime();
                    }
                }


            }
        };
    }

    public void zx() throws Exception {
        new TaskContent(mFairy, "主线") {
            void init() throws Exception {
                setTaskName(1);

                gamePublicFuntion.close(3);

                mFairy.onTap(814, 644, 842, 675, "err", 500);
            }

            void nn() throws Exception {

                result = mFairy.findPic(1005, 641, 1263, 710, "ban.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("nn1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(396, 303, 450, 363, "经典剧情", 500);
                    mFairy.onTap(615, 658, 670, 674, "确定", 500);
                }

                result = mFairy.findPic("skill.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "一键潜修", 500);
                    mFairy.onTap(19, 10, 59, 28, "", 500);
                }

                result = mFairy.findPic("nn4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "晋升影位", 500);
                    mFairy.onTap(19, 10, 59, 28, "", 500);
                }

                result = mFairy.findPic("nn6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(558, 363, 576, 381, "", 500);
                    mFairy.onTap(786, 415, 836, 438, "确定跳过剧情", 500);
                }


                result = mFairy.findPic("lu.png");
                if (result.sim < 0.8f) {
                    if (frequencyMap("lu", 2)) {
                        result = mFairy.findPic("battle.png");
                        if (result.sim > 0.8f) {
                            gamePublicFuntion.auto_battle();
                        }
                    }
                }

                result = mFairy.findPic(8, 3, 90, 51, "esc.png");
                if (result.sim > 0.75f) {
                    if (frequencyMap("esc", 3)) {
                        mFairy.onTap(0.75f, result, "esc", 500);
                    }
                } else {
                    frequencyInit("esc.png");
                }
            }

            void content_01() throws Exception {
                timeCount(5, 0);

                nn();

                result = mFairy.findPic(new String[]{"tiao.png", "tiao1.png"});
                mFairy.onTap(0.8f, result, "跳过", 500);

                result = mFairy.findPic("chat.png");
                mFairy.onTap(0.8f, result, "聊天", 500);

                gamePublicFuntion.task();

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {


                        result = mFairy.findPic(66, 110, 236, 254, "nn7.png");
                        if (result.sim > 0.75f) {
                            setTaskEnd();
                            return;
                        }

                        result = mFairy.findPic(47, 91, 114, 458, "zhu.png");
                        mFairy.onTap(0.75f, result, "主线", 1000);

                        mFairy.initMatTime();
                    }
                }


            }
        };
    }

    abstract class SingleTask_Content extends TaskContent {

        void create() throws Exception {
            super.create();
            gamePublicFuntion.activity_bool = true;
        }

        public SingleTask_Content(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);
        }

        void init() throws Exception {
            gamePublicFuntion.init(true);
            setTaskName(1);
        }

        boolean activity_end(FindResult act) throws Exception {

            result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233,
                    new String[]{"wan.png", "wan1.png", "wan2.png"/*, "wan3.png"*/, "wan4.png"});
            if (result.sim > 0.8f) {
                setTaskEnd();
                return true;
            }

            result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233, new String[]{"qian.png", "wan3.png"});
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "前往", 2000);
                oneSecond = 0;
                frequencyInit("actError");
                setTaskName(2);
                return true;
            }

            return false;
        }

        void content_01() throws Exception {
            if (timeCount(11, 0)) {
                gamePublicFuntion.close(3);
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

                    result = mFairy.findPic("activity2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "", 800);

                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(300);

                            gamePublicFuntion.use();

                            result = mFairy.findPic(884, 226, 1019, 630, "activity3.png");
                            mFairy.onTap(0.8f, result, 731, 661, 785, 698, "关闭活动奖励界面", 500);
                        }
                    }
                }

                FindResult act = mFairy.findPic(326, 97, 1231, 420, activity_name);
                if (act.sim > 0.72f) {
                    if (activity_end(act)) {
                        oneSecond = 0;
                        return;
                    }
                }

                activitySlide.setEndTime(3000);
                if (GamePublicFuntion.activity_bool == false) {
                    activitySlide.slideRange(new int[]{3, 5, 6, 7,8,9}, 3);
                } else {
                    activitySlide.slideRange(new int[]{3, 4, 5, 6, 7,8,9}, 2, 0);
                }
                activitySlide.setEndTime(1000);
            }
        }

        void ls_activity() throws Exception {
            if (timeCount(10, 99)) {
                if (frequencyMap("sl_act", 1)) {

                }
            }

            result = mFairy.findPic(459, 2, 1061, 160, "sl.png");
            mFairy.onTap(0.75f, result, "势力", 500);

            result = mFairy.findPic("sl1.png");
            if (result.sim > 0.8f) {

                result = mFairy.findPic(1153, 68, 1267, 653, "sl2.png");
                mFairy.onTap(0.75f, result, "活动", 2000);

                FindResult activity = mFairy.findPic(31, 23, 1138, 696, activity_name);
                if (activity.sim > 0.75f) {

                    result = mFairy.findPic(activity.x + 297, activity.y + 151,
                            activity.x + 528, activity.y + 327, new String[]{"sl4.png", "sl5.png"});
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return;
                    }


                    result = mFairy.findPic(activity.x + 297, activity.y + 151,
                            activity.x + 528, activity.y + 327, "sl3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "前往", 2000);
                        setTaskName(2);
                        frequencyInit("auto");
                        frequencyInit("ls_act");
                        return;
                    }

                }

                if (frequencyMap("act", 3)) {
                    mFairy.ranSwipe(585, 294, 624, 557, 2, 500, (long) 1000);
                }
            }
        }

        boolean cancel() throws Exception {

            result = mFairy.findPic(359, 181, 743, 420, "jhl.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(560, 368, 572, 375, "", 500);
                mFairy.onTap(795, 418, 841, 438, "", 500);
                return true;
            }

            return false;
        }

        void inOperation() throws Exception {
            result = mFairy.findPic(1005, 641, 1263, 710, "ban.png");
            if (result.sim > 0.8f) {
                err = 0;
            }

            FindResult qx = gamePublicFuntion.cancel();
            if (qx.sim > 0.75f) {

                if (cancel()) {
                    return;
                }
                if (frequencyMap("qx", 1)) {
                    mFairy.onTap(0.75f, qx, "取消", 500);
                }
            } else {
                frequencyInit("qx");
            }


            result = mFairy.findPic("zoom2.png");
            mFairy.onTap(0.8f, result, "左侧任务栏", 500);

            result = mFairy.findPic(941, 486, 1256, 706, "fh.png");
            mFairy.onTap(0.8f, result, "安全区复活", 2000);

            gamePublicFuntion.use();
            gamePublicFuntion.task();

            result = mFairy.findPic(new String[]{"tiao.png", "tiao1.png"});
            mFairy.onTap(0.8f, result, "跳过", 500);
        }
    }

    public void setUp() throws Exception {
        new TaskContent(mFairy, "设置") {
            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(14, 532, 194, 679, "setup1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "点击设置", 1500);
                } else {
                    result = mFairy.findPic(3, 635, 69, 698, "setup.png");
                    mFairy.onTap(0.8f, result, "设置", 1000);
                }

                result = mFairy.findPic("setup2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    mFairy.onTap(171, 82, 231, 107, "基础", 500);
                    mFairy.onTap(164, 382, 175, 392, "极地", 1000);

                    result = mFairy.findPic("setup3.png");
                    mFairy.onTap(0.8f, result, "确认", 1000);

                    mFairy.onTap(1126, 29, 1147, 45, "", 500);

                    setTaskEnd();
                }
            }
        };
    }//设置

    public void li_ranks() throws Exception {
        new TaskContent(mFairy, "离开队伍") {
            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                gamePublicFuntion.clickRanks();

                result = mFairy.findPic(185, 349, 305, 441, "rank3.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "离队", 500);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(94, 114, 257, 303, "rank4.png");
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(new String[]{"rank5.png", "rank8.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("rank6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(945, 672, 966, 688, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(450, 600, 595, 709, "rank9.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "离队", 500);
                        setTaskEnd();
                        return;
                    }


                    result = mFairy.findPic("rank7.png");
                    mFairy.onTap(0.8f, result, "队伍界面,返回", 500);
                }
            }
        };

    }//离开队伍

    public void lm() throws Exception {
        new TaskContent(mFairy, "撸猫") {
            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(809, 7, 1116, 166, "lm1.png");
                mFairy.onTap(0.8f, result, "撸猫", 500);

                result = mFairy.findPic("lm4.png");
                mFairy.onTap(0.8f, result, "打开", 3000);

                result = mFairy.findPic("lm5.png");
                mFairy.onTap(0.8f, result, "关闭", 2000);

                result = mFairy.findPic("lm7.png");
                mFairy.onTap(0.8f, result, "关闭", 2000);


                result = mFairy.findPic("lm2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("lm3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "今日投食", 3000);
                        return;
                    }

                    result = mFairy.findPic(334, 127, 1176, 550, "lm6.png");
                    if (result.sim > 0.85f) {
                        frequencyInit("end");
                        mFairy.onTap(0.85f, result, "抚摸", 4000);
                        return;
                    }

                    result = mFairy.findPic(334, 127, 1176, 550, new String[]{"lm8.png", "lm9.png", "lm10.png"});
                    if (result.sim > 0.75f) {
                        frequencyInit("end");
                        mFairy.onTap(0.75f, result, "抚摸", 4000);
                        return;
                    }

                    Thread.sleep(500);
                    if (frequencyMap("end", 10)) {
                        mFairy.onTap(1201, 68, 1218, 82, "", 500);
                        setTaskEnd();
                        return;
                    }
                }
            }
        };
    }//撸猫

    public void bc() throws Exception {
        new TaskContent(mFairy, "补偿") {
            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic(428, 13, 919, 189, "bc1.png");
                mFairy.onTap(0.8f, result, "补偿", 500);

                result = mFairy.findPic("bc2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (AtFairyConfig.getOption("bc").equals("2")) {
                        mFairy.onTap(1059, 617, 1136, 642, "一键完美", 1500);
                    } else {
                        mFairy.onTap(903, 624, 970, 636, "一键普通", 1500);

                    }

                    result = mFairy.findPic("bc3.png");
                    mFairy.onTap(0.8f, result, "确认", 1500);

                    mFairy.onTap(1145, 51, 1158, 65, "", 500);
                    setTaskEnd();
                    return;
                }
            }
        };
    }//补偿

    public void ling1() throws Exception {
        new TaskContent(mFairy, "领奖1") {
            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic(428, 13, 1100, 189, "ling1.png");
                mFairy.onTap(0.8f, result, "福利", 500);

                result = mFairy.findPic("ling2.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(46, 79, 261, 688, "ling3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "周一豪礼", 1000);
                        mFairy.onTap(720, 286, 766, 352, "祈愿", 1000);
                        mFairy.onTap(665, 596, 711, 618, "", 500);
                        mFairy.onTap(1050, 521, 1116, 535, "周一奖励", 1000);
                        mFairy.onTap(665, 596, 711, 618, "", 500);
                        mFairy.onTap(1198, 53, 1218, 80, "关闭", 500);
                        setTaskEnd();
                    } else {
                        if (frequencyMap("hua", 2)) {
                            mFairy.ranSwipe(169, 512, 170, 285, 500, 1500);
                        }
                    }
                }
            }
        };
    }//领奖1

    public void ling2() throws Exception {
        new TaskContent(mFairy, "领奖2") {
            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic(428, 13, 1100, 189, "ling4.png");
                mFairy.onTap(0.8f, result, "商会", 500);

                result = mFairy.findPic("ling5.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("ling6.png");
                    mFairy.onTap(0.8f, result, "特惠", 500);

                    result = mFairy.findPic(129, 136, 584, 573, "ling7.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "免费", 3500);
                        mFairy.onTap(968, 608, 1005, 621, "", 1000);
                        mFairy.onTap(968, 608, 1005, 621, "", 1000);
                        mFairy.onTap(24, 14, 63, 26, "", 500);
                        setTaskEnd();
                        return;
                    }
                }
            }
        };
    }//领奖2

    public void bhqd() throws Exception {
        new SingleTask_Content(mFairy, "帮会签到") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("xkwp1.png");
                mFairy.onTap(0.8f, result, "缩放栏", 1000);

                result = mFairy.findPic(984, 186, 1265, 719, "bh1.png");
                mFairy.onTap(0.8f, result, "帮会", 1000);

                result = mFairy.findPic("bh.png");
                if (result.sim > 0.8f) {
                    TaskMain.BANG = false;
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("bh2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (oneSecond()) {
                        result = mFairy.findPic("bh3.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(478, 305, 495, 321, "", 500);
                            mFairy.onTap(588, 303, 601, 312, "", 500);
                            mFairy.onTap(746, 303, 763, 316, "", 500);
                            mFairy.onTap(981, 307, 998, 323, "", 500);
                        }
                    } else {
                        result = mFairy.findPic("bh5.png");
                        if (result.sim > 0.8f) {
                            for (int i = 0; i < 3; i++) {
                                result = mFairy.findPic(28, 483, 1097, 607, "bh6.png");
                                mFairy.onTap(0.8f, result, "领取", 1000);

                                mFairy.onTap(602, 646, 647, 673, "", 500);
                            }
                            mFairy.onTap(35, 13, 64, 34, "返回", 500);
                            setTaskEnd();
                            return;
                        } else {
                            result = mFairy.findPic(1149, 62, 1257, 681, "bh4.png");
                            mFairy.onTap(0.8f, result, "福利", 1000);
                        }


                    }

                }

            }
        };
    }//帮会签到

    public void bhfc() throws Exception {
        new SingleTask_Content(mFairy, "帮会奉茶") {
            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("xkwp1.png");
                mFairy.onTap(0.8f, result, "缩放栏", 1000);

                result = mFairy.findPic(984, 186, 1265, 719, "bh1.png");
                mFairy.onTap(0.8f, result, "帮会", 1000);

                result = mFairy.findPic("bh.png");
                if (result.sim > 0.8f) {
                    TaskMain.BANG = false;
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("bh2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("bh5.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(47, 44, 1134, 178, "bh7.png");
                        mFairy.onTap(0.8f, result, "帮派奉茶", 1000);

                    } else {
                        result = mFairy.findPic(1149, 62, 1257, 681, "bh4.png");
                        mFairy.onTap(0.8f, result, "福利", 1000);
                    }
                }

                result = mFairy.findPic("bh8.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("bh9.png");
                    mFairy.onTap(0.8f, result, "关闭弹幕", 1000);

                    result = mFairy.findPic("bh10.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1086, 35, 1096, 44, "", 500);
                        mFairy.onTap(39, 13, 64, 27, "", 500);
                        setTaskEnd();
                        return;
                    }

                    switch (AtFairyConfig.getOption("bhfc")) {
                        case "1":
                            mFairy.onTap(302, 496, 380, 510, "粗陶苦茶", 500);
                            break;
                        case "2":
                            mFairy.onTap(599, 494, 678, 507, "细瓷清茗", 500);
                            break;
                        case "3":
                            mFairy.onTap(895, 497, 1014, 510, "紫砂龙舌", 500);
                            break;
                        default:
                            mFairy.onTap(302, 496, 380, 510, "粗陶苦茶", 500);
                            break;
                    }


                }


            }
        };
    }//帮会奉茶

    public void bhrc() throws Exception {
        new SingleTask_Content(mFairy, "帮会日常") {
            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            boolean cancel() throws Exception {
                result = mFairy.findPic("bhrc2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(788, 418, 838, 442, "前往帮会领地", 6000);
                    return true;
                }
                return false;
            }

            void content_01() throws Exception {
                timeCount(15, 99);


                result = mFairy.findPic("bh.png");
                if (result.sim > 0.8f) {
                    TaskMain.BANG = false;
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("bh2.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic("bh12.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(34, 47, 1115, 172, "bhrc1.png");
                        if(result.sim>0.8f) {
                            err=0;
                            mFairy.onTap(0.8f, result, "帮会日常", 1000);
                            return;
                        }
                    } else {
                        result = mFairy.findPic(1149, 62, 1257, 681, "bh11.png");
                        if(result.sim>0.8f){
                            err=0;
                            mFairy.onTap(0.8f, result, "活动", 1000);
                            return;
                        }

                        if(frequencyMap("bhjs_silde",2)){
                            mFairy.ranSwipe(880,344,580,344,500,1000);
                        }
                    }
                }

                result = mFairy.findPic(714,239,884,395,"bhrc3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.initMatTime();
                    mFairy.onTap(0.8f, result, "祈祷", 500);
                }

                result = mFairy.findPic("bhrc5.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("福源宝箱界面"));
                    err = 0;

                    result = mFairy.findPic("bhrc6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(842, 305, 882, 317, "", 500);
                        mFairy.onTap(762, 347, 853, 368, "", 500);
                        mFairy.onTap(812, 440, 849, 452, "许愿", 3500);
                        return;
                    }

                    result = mFairy.findPic("bhrc4.png");
                    mFairy.onTap(0.8f, result, "弹幕", 500);

                    result = mFairy.findPic(788, 431, 966, 521, "bhrc7.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(39, 9, 72, 34, "今日已祈愿", 500);
                        setTaskEnd();
                        return;
                    }

                    mFairy.onTap(893, 435, 922, 463, "", 500);

                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(5, true) || oneSecond()) {

                        result = mFairy.findPic("xkwp1.png");
                        mFairy.onTap(0.8f, result, "缩放栏", 1000);

                        result = mFairy.findPic(984, 186, 1265, 719, "bh1.png");
                        mFairy.onTap(0.8f, result, "帮会", 1000);

                    }
                }
            }
        };
    }//帮会日常

    public void bhjs() throws Exception {
        new SingleTask_Content(mFairy, "帮会建设") {
            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            boolean cancel() throws Exception {
                result = mFairy.findPic("bhrc2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(788, 418, 838, 442, "前往帮会领地", 6000);
                    return true;
                }
                return false;
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("bh.png");
                if (result.sim > 0.8f) {
                    TaskMain.BANG = false;
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("bh2.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic("bh12.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(39, 36, 1211, 159, "bhjs1.png");
                        if(result.sim>0.8f) {
                            err=0;
                            mFairy.onTap(0.8f, result, "帮会建设", 1000);
                            return;
                        }

                    } else {
                        result = mFairy.findPic(1149, 62, 1257, 681, "bh11.png");
                        if(result.sim>0.8f){
                            err=0;
                            mFairy.onTap(0.8f, result, "活动", 1000);
                            return;
                        }

                        if(frequencyMap("bhjs_silde",2)){
                            mFairy.ranSwipe(880,344,580,344,500,1000);
                        }
                    }
                }


                result = mFairy.findPic(new String[]{"bhjs2.png","by.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "喂养宠物 | 开始保养", 1000);
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(500);
                        result = mFairy.findPic("bpjs.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "喂养", 5000);
                            break;
                        }
                    }
                    setTaskEnd();
                    return;
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(5, true) || oneSecond()) {

                        result = mFairy.findPic("xkwp1.png");
                        mFairy.onTap(0.8f, result, "缩放栏", 1000);

                        result = mFairy.findPic(984, 186, 1265, 719, "bh1.png");
                        mFairy.onTap(0.8f, result, "帮会", 1000);

                    }
                }

            }
        };
    }//帮会建设

    private int xkwp = 2;

    public void xkwp() throws Exception {
        new TaskContent(mFairy, "侠客委派") {

            void create() throws Exception {
                super.create();
                if (!AtFairyConfig.getOption("xkwp_type").equals("")) {
                    xkwp = Integer.parseInt(AtFairyConfig.getOption("xkwp_type"));
                }
            }

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);

            }

            boolean get_type(int type) throws Exception {
                FindResult t;

                for (int j = 0; j < 3; j++) {
                    Thread.sleep(300);

                    for (int i = 3; i >= type; i--) {
                        Thread.sleep(300);

                        switch (i) {
                            case 3:
                                t = mFairy.findPic(898, 100 + (j * 120), 1254, 219 + (j * 120), "xkwp8.png");
                                if (t.sim > 0.8f) {
                                    result = mFairy.findPic(t.x + 60, t.y + 20, t.x + 150, t.y + 100, "xkwp13.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(0.8f, t, "甲", 1000);
                                        return true;
                                    }

                                }

                                break;
                            case 2:
                                t = mFairy.findPic(898, 100 + (j * 120), 1254, 219 + (j * 120), "xkwp9.png");
                                if (t.sim > 0.8f) {
                                    result = mFairy.findPic(t.x + 60, t.y + 20, t.x + 150, t.y + 100, "xkwp13.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(0.8f, t, "乙", 1000);
                                        return true;
                                    }
                                }
                                break;
                            case 1:
                                t = mFairy.findPic(898, 100 + (j * 120), 1254, 219 + (j * 120), "xkwp10.png");
                                if (t.sim > 0.8f) {
                                    result = mFairy.findPic(t.x + 60, t.y + 20, t.x + 150, t.y + 100, "xkwp13.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(0.8f, t, "丙", 1000);
                                        return true;
                                    }
                                }
                                break;
                        }

                    }
                }
                return false;
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                FindResult qx = gamePublicFuntion.cancel();
                if (qx.sim > 0.75f) {

                    result = mFairy.findPic(358, 167, 899, 411, "xkwp17.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(796, 420, 840, 433, "确定刷新", 500);
                        return;
                    }
                    if (frequencyMap("qx", 2)) {
                        mFairy.onTap(0.75f, qx, "取消", 500);
                    }
                } else {
                    frequencyInit("qx");
                }

                result = mFairy.findPic("xkwp1.png");
                mFairy.onTap(0.8f, result, "缩放栏", 1000);

                result = mFairy.findPic(984, 186, 1265, 719, "xkwp.png");
                mFairy.onTap(0.8f, result, "侠客", 1000);

                result = mFairy.findPic(585, 115, 1033, 453, "xkwp3.png");
                mFairy.onTap(0.8f, result, "委派", 1000);

                result = mFairy.findPic(864, 535, 1093, 636, "xkwp11.png");
                mFairy.onTap(0.8f, result, "一键委派", 500);

                result = mFairy.findPic(864, 535, 1093, 636, "xkwp12.png");
                mFairy.onTap(0.8f, result, "进行委托", 500);

                result = mFairy.findPic("xkwp4.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(507, 595, 805, 701, "xkwp6.png");
                    mFairy.onTap(0.8f, result, "点击区域关闭", 1000);

                    result = mFairy.findPic(989, 101, 1192, 668, "xkwp5.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "已完成", 1000);
                        return;
                    }

                    if (get_type(xkwp)) {
                        return;
                    } else {
                        if (frequencyMap("shua", 2)) {
                            result = mFairy.findPic("xkwp14.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "免费刷新", 500);
                                return;
                            } else {
                                if (AtFairyConfig.getOption("xkwp").equals("1")) {

                                    result = mFairy.findPic(981, 524, 1173, 626, "xkwp15.png");
                                    mFairy.onTap(0.8f, result, "刷新", 500);

                                } else {
                                    xkwp--;
                                    if (xkwp <= 1) {
                                        xkwp = 1;
                                    }
                                }
                            }
                        }

                        if (frequencyMap("xkwp", 2)) {
                            result = mFairy.findPic("xkwp7.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(1243, 45, 1255, 54, "", 500);
                                setTaskEnd();
                                return;
                            }
                        }
                    }
                }


            }
        };
    }//侠客委派

    public void mpxx() throws Exception {
        new SingleTask_Content(mFairy, "门派修行") {

            void create() throws Exception {
                super.create();
                activity_name = "mpxx.png";
                activity_type = 1;
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("chat.png");
                mFairy.onTap(0.8f, result, "聊天", 500);

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                        result = mFairy.findPic(47, 91, 114, 458, "mpxx1.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(0.75f, result, "门派修行", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                        taskSlide.slideRange(new int[]{3, 5, 7}, 2, 0);
                    }
                }

            }
        };
    }//门派修行

    public int yzxs_color = 1;

    public void yzxs() throws Exception {
        new SingleTask_Content(mFairy, "影宗悬赏") {

            void create() throws Exception {
                super.create();
                activity_name = "yzxs.png";
                activity_type = 1;
                if (!AtFairyConfig.getOption("yzxs_color").equals("")) {
                    yzxs_color = Integer.parseInt(AtFairyConfig.getOption("yzxs_color"));
                }
            }

            boolean getColor(int color) throws Exception {
                String color_str = "189,143,82";

                for (int i = 5; i >= color; i--) {
                    Thread.sleep(300);
                    switch (i) {
                        case 1:
                            color_str = "124,134,146";
                            break;
                        case 2:
                            color_str = "131,145,108";
                            break;
                        case 3:
                            color_str = "99,143,165";
                            break;
                        case 4:
                            color_str = "139,123,165";
                            break;
                        case 5:
                            color_str = "189,143,82";
                            break;
                    }

                    int lan1 = mFairy.getColorNum(282, 150, 309, 166, 0.98f, 0, color_str);
                    if (lan1 > 100) {
                        mFairy.onTap(276, 465, 313, 477, "", 500);
                        return true;
                    }
                    int lan2 = mFairy.getColorNum(513, 144, 538, 163, 0.98f, 0, color_str);
                    if (lan2 > 100) {
                        mFairy.onTap(515, 466, 547, 475, "", 500);
                        return true;
                    }
                    int lan3 = mFairy.getColorNum(744, 149, 770, 164, 0.98f, 0, color_str);
                    if (lan3 > 100) {
                        mFairy.onTap(746, 464, 778, 478, "", 500);
                        return true;
                    }
                    int lan4 = mFairy.getColorNum(976, 150, 1001, 165, 0.98f, 0, color_str);
                    if (lan4 > 100) {
                        mFairy.onTap(978,461,1005,477, "", 500);
                        return true;
                    }
                }

                return false;
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("yzxs1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(190, 394, 1127, 532, "yzxs7.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "领赏", 5000);
                        return;
                    }

                    result = mFairy.findPic("yzxs8.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1137, 167, 1154, 184, "没有次数了", 500);
                        setTaskEnd();
                        return;
                    }

                    if (getColor(yzxs_color)) {
                        result = mFairy.findPic(190, 394, 1127, 532, "yzxs2.png");
                        mFairy.onTap(0.8f, result, "前往", 500);
                    } else {
                        if (frequencyMap("shua", 1)) {
                            result = mFairy.findPic("yzxs4.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "免费刷新", 1000);
                            } else {
                                if (AtFairyConfig.getOption("yzxs_type").equals("1")) {
                                    yzxs_color--;
                                    if (yzxs_color <= 1) {
                                        yzxs_color = 1;
                                    }
                                    return;
                                }

                                if (AtFairyConfig.getOption("yzxs_type").equals("2")) {
                                    result = mFairy.findPic("yzxs3.png");
                                    mFairy.onTap(0.8f, result, "全部刷新", 500);
                                    return;
                                }

                                if (AtFairyConfig.getOption("yzxs_type").equals("3")) {
                                    if (oneSecond()) {
                                        result = mFairy.findPic("yzxs5.png");
                                        mFairy.onTap(0.8f, result, "一键满星", 500);
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }

                result = mFairy.findPic("chat.png");
                mFairy.onTap(0.8f, result, "聊天", 500);

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                        result = mFairy.findPic(47, 91, 114, 458, "yzxs6.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(0.75f, result, "赏", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                        taskSlide.slideRange(new int[]{3, 5, 7}, 2, 0);
                    }
                }

            }
        };
    }//影宗悬赏

    public void ywc() throws Exception {
        new SingleTask_Content(mFairy, "演武场") {
            void create() throws Exception {
                super.create();
                activity_name = "ywc.png";
                activity_type = 2;
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("ywc1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("ywc4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1240, 35, 1258, 60, "次数不足", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(1006, 505, 1193, 581, "ywc2.png");
                    mFairy.onTap(0.8f, result, "挑战", 5000);
                }

                result = mFairy.findPic(538, 512, 756, 593, "ywc3.png");
                mFairy.onTap(0.8f, result, "继续", 6000);


                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    gamePublicFuntion.auto_battle();
                }
            }
        };
    }//演武场

    public void shyx() throws Exception {
        new SingleTask_Content(mFairy, "山河映像") {

            void create() throws Exception {
                super.create();
                activity_name = "shyx.png";
                activity_type = 2;
            }

            boolean activity_end(FindResult act) throws Exception {

                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233,
                        new String[]{"wan.png", "wan1.png", "wan2.png", "wan3.png", "wan4.png"});
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return true;
                }

                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233, new String[]{"qian.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "前往", 2000);
                    oneSecond = 0;
                    frequencyInit("actError");
                    setTaskName(2);
                    return true;
                }

                return false;
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("shyx1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("shyx9.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1170, 122, 1183, 134, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("shyx2.png");
                    mFairy.onTap(0.8f, result, "接取任务", 500);

                    result = mFairy.findPic("shyx3.png");
                    mFairy.onTap(0.8f, result, "前往任务", 3500);
                }


                result = mFairy.findPic(643, 67, 804, 603, "shyx8.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "记录", 2000);
                } else {
                    result = mFairy.findPic(717, 247, 978, 462, "shyx5.png");
                    mFairy.onTap(0.8f, result, "记录映像", 1500);
                }

                result = mFairy.findPic("shyx6.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(1105, 342, 1145, 367, "拍摄", 2000);
                    gamePublicFuntion.esc(3);
                    setTaskName(0);
                    return;
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                        result = mFairy.findPic(47, 91, 114, 458, "shyx4.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(0.75f, result, "山河映像", 3000);

                            if (gamePublicFuntion.judgeStop(5, true)) {
                                mFairy.onTap(97, 661, 114, 673, "点击相机", 500);
                                return;
                            }

                            mFairy.initMatTime();
                            return;
                        }

                        taskSlide.slideRange(new int[]{3, 5, 7}, 2, 0);
                    }
                }

            }
        };
    }//山河映像

    public void jgdt() throws Exception {
        new SingleTask_Content(mFairy, "酒馆答题") {

            void create() throws Exception {
                super.create();
                activity_name = "jgdt.png";
                activity_type = 2;
            }

            void content_02() throws Exception {
                timeCount(10, 0);


                result = mFairy.findPic("jgdt5.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("jgdt2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("jgdt3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "离开", 2000);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("jgdt4.png");
                    if (result.sim > 0.92f) {
                        mFairy.onTap(35, 4, 63, 26, "离开", 2000);
                        setTaskEnd();
                        return;
                    }

                    mFairy.onTap(1180, 368, 1243, 390, "甲", 3200);
                }

                result = mFairy.findPic(899, 102, 1201, 579, "jgdt1.png");
                mFairy.onTap(0.8f, result, "答题", 8000);

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                    }
                }
            }
        };
    }//酒馆答题

    public void mxsl() throws Exception {
        new SingleTask_Content(mFairy, "冥想试炼") {
            void create() throws Exception {
                super.create();
                activity_name = "mxsl.png";
                activity_type = 3;
            }

            boolean cancel() throws Exception {
                result = mFairy.findPic("mxsl3.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(790, 418, 846, 436, "确定使用默认流派", 500);
                    return true;
                }
                return false;
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("mxsl1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("mxsl2.png");
                    mFairy.onTap(0.8f, result, "挑战", 5000);
                }


                result = mFairy.findPic("mxsl5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(783, 633, 865, 647, "退出", 500);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(957, 610, 1134, 681, "mxsl4.png");
                mFairy.onTap(0.8f, result, "继续挑战", 2000);


                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                    }
                }
            }
        };
    }//冥想试炼

    public void xktz() throws Exception {
        new SingleTask_Content(mFairy, "侠客挑战") {
            void create() throws Exception {
                super.create();
                activity_name = "xktz.png";
                activity_type = 3;
            }

            boolean cancel() throws Exception {
                result = mFairy.findPic("mxsl3.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(790, 418, 846, 436, "确定使用默认流派", 500);
                    return true;
                }
                return false;
            }

            void content_02() throws Exception {
                timeCount(15, 0);

                result = mFairy.findPic(new String[]{"xktz1.png", "xktz7.png"});
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("xktz2.png");
                    mFairy.onTap(0.8f, result, "挑战", 3000);

                    result = mFairy.findPic("xktz6.png");
                    mFairy.onTap(0.8f, result, "挑战", 3000);
                }


                result = mFairy.findPic(896, 217, 1202, 586, "xktz3.png");
                mFairy.onTap(0.8f, result, "开始挑战", 3000);

                result = mFairy.findPic("xktz4.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(2000);
                    mFairy.onTap(798, 686, 815, 695, "退出", 2000);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("xktz5.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(2000);
                    mFairy.onTap(798, 686, 815, 695, "退出", 2000);
                    setTaskEnd();
                    return;
                }

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                    }
                } else {
                    Thread.sleep(2000);
                }
            }
        };
    }//侠客挑战

    public void jhpk() throws Exception {
        new SingleTask_Content(mFairy, "江湖跑酷") {
            void create() throws Exception {
                super.create();
                activity_name = "jhpk.png";
                activity_type = 2;
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("chat.png");
                mFairy.onTap(0.75f, result, "聊天", 500);

                result = mFairy.findPic(900, 167, 1215, 577, "jhpk2.png");
                mFairy.onTap(0.8f, result, "跑酷", 500);

                result = mFairy.findPic("jhpk3.png");
                if (result.sim > 0.75f) {
                    err = 0;
                    mFairy.onTap(919, 647, 939, 686, "", 500);
                    setTaskEnd();
                    return;
                }


                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                        result = mFairy.findPic(47, 91, 114, 458, "jhpk1.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(0.75f, result, "江湖跑酷", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                        taskSlide.slideRange(new int[]{3, 5, 7}, 2, 0);
                    }
                }

            }
        };
    }//江湖跑酷

    public void sljs() throws Exception {
        new SingleTask_Content(mFairy, "势力建设") {
            void create() throws Exception {
                super.create();
                activity_name = "sljs.png";
            }

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                ls_activity();
            }

            void content_02() throws Exception {
                timeCount(8, 0);

                result = mFairy.findPic(898, 148, 1153, 589, "sljs1.png");
                mFairy.onTap(0.75f, result, "建设", 500);

                result = mFairy.findPic("sljs3.png");
                mFairy.onTap(0.75f, result, "手", 500);

                result = mFairy.findPic(497, 489, 645, 612, "auto1.png");
                if (result.sim > 0.72f) {
                    if (frequencyMap("auto", 20)) {
                        setTaskName(0);
                        return;
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                        result = mFairy.findPic(36, 63, 268, 481, "sljs2.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(0.75f, result, "势力建设", 1000);
                            frequencyInit("auto");
                            mFairy.initMatTime();
                            return;
                        }

                        taskSlide.slideRange(new int[]{4, 6}, 2, 0);
                    }
                }
            }
        };
    }//势力建设

    public void ssqy() throws Exception {
        new SingleTask_Content(mFairy, "顺手牵羊") {
            void create() throws Exception {
                super.create();
                activity_name = "ssqy.png";
            }

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                ls_activity();
            }

            void content_02() throws Exception {
                timeCount(8, 0);

                result = mFairy.findPic("auto1.png");
                if (result.sim > 0.72f) {
                    if (frequencyMap("auto", 50)) {
                        setTaskName(0);
                        return;
                    }
                }

                result = mFairy.findPic(898, 148, 1153, 589, "ssqy2.png");
                mFairy.onTap(0.8f, result, "领取顺手牵羊", 500);

                result = mFairy.findPic("ssqy4.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    switch (AtFairyConfig.getOption("ssqy")) {
                        case "1":
                            mFairy.onTap(828, 287, 887, 359, "普通难度", 500);
                            break;
                        case "2":
                            mFairy.onTap(367, 265, 454, 383, "高难度", 500);
                            break;
                        default:
                            mFairy.onTap(828, 287, 887, 359, "普通难度", 500);
                            break;
                    }

                    result = mFairy.findPic("ssqy3.png");
                    mFairy.onTap(0.8f, result, "确定", 500);
                }

                result = mFairy.findPic("ssqy5.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "继续尝试", 3000);
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                        result = mFairy.findPic(36, 63, 268, 481, "ssqy1.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(0.75f, result, "顺手牵羊", 1000);
                            frequencyInit("auto");
                            mFairy.initMatTime();
                            return;
                        }
                        taskSlide.slideRange(new int[]{4, 6}, 2, 0);
                    }
                }
            }
        };
    }//顺手牵羊

    public void shxz() throws Exception {
        new SingleTask_Content(mFairy, "四海行缁") {
            void create() throws Exception {
                super.create();
                activity_name = "shxz.png";
            }

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                ls_activity();
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(898, 148, 1153, 589, "shxz2.png");
                mFairy.onTap(0.8f, result, "领取", 500);

                result = mFairy.findPic("shxz5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "继续运输", 500);
                    if (mapCount(0.8f, 656, 80, 841, 390, "shxz6.png")) {
                        mFairy.onTap(786, 604, 802, 616, "", 500);
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("shxz3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    switch (AtFairyConfig.getOption("shxz")) {
                        case "1":
                            mFairy.onTap(587, 325, 623, 387, "普通难度", 500);
                            break;
                        case "2":
                            mFairy.onTap(275, 291, 326, 386, "高难度", 500);
                            break;
                        default:
                            mFairy.onTap(587, 325, 623, 387, "普通难度", 500);
                            break;
                    }
                    mFairy.onTap(1010, 540, 1112, 553, "", 500);
                    if (mapCount(0.8f, 656, 80, 841, 390, "shxz6.png")) {
                        mFairy.onTap(786, 604, 802, 616, "", 500);
                        setTaskEnd();
                        return;
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(5, true)) {

                        result = mFairy.findPic(36, 63, 268, 481, "shxz4.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(0.75f, result, "四海行缁", 1000);
                            frequencyInit("auto");
                            mFairy.initMatTime();
                            return;
                        }

                        taskSlide.slideRange(new int[]{4, 6}, 2, 0);
                    }
                }
            }
        };
    }//四海行缁

    /**
     * 限时
     */
    public void mpsy() throws Exception {
        new SingleTask_Content(mFairy, "门派授业") {

            void create() throws Exception {
                super.create();
                activity_name = "mpsy.png";
                activity_type = 1;
                li_ranks();
            }

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
            }

            void inOperation() throws Exception {
                super.inOperation();

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (hour < 18) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(369, 429, 608, 562, "mpsy1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.initMatTime();

                    result = mFairy.findPic(517, 459, 693, 594, "mpsy2.png");
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return;
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(5, true)) {

                    }
                }
            }
        };
    }//门派授业

    public void hslj() throws Exception {
        new SingleTask_Content(mFairy, "华山论剑") {

            void create() throws Exception {
                super.create();
                activity_name = "hslj.png";
                activity_type = 2;
                li_ranks();
            }

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
            }

            boolean activity_end(FindResult act) throws Exception {
                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233,
                        new String[]{"wan.png", "wan1.png", "wan2.png", "wan4.png"});
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return true;
                }

                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233, new String[]{"wan3.png", "qian.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "前往", 2000);
                    oneSecond = 0;
                    frequencyInit("actError");
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (hour < 12) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(15, 0);
                Thread.sleep(500);

                result = mFairy.findPic("hslj2.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic("sx.png");
                    if (result.sim > 0.8f) {

                        result = mFairy.findPic("sx1.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "奖励", 1000);
                            mFairy.onTap(893, 580, 958, 594, "", 500);
                            mFairy.onTap(893, 580, 958, 594, "", 500);
                            mFairy.onTap(982, 79, 998, 92, "", 500);
                        }

                        mFairy.onTap(1240, 46, 1262, 62, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("hslj1.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        result = mFairy.findPic("hslj4.png");
                        mFairy.onTap(0.8f, result, "报名", 500);

                    } else {
                        result = mFairy.findPic(17, 34, 374, 304, "hslj3.png");
                        mFairy.onTap(0.8f, result, "论剑", 500);
                    }
                }

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                result = mFairy.findPic(735, 472, 984, 697, "hslj5.png");
                mFairy.onTap(0.8f, result, 883, 665, 916, 678, "继续", 1500);

                result = mFairy.findPic(1034, 577, 1259, 704, "hslj6.png");
                mFairy.onTap(0.8f, result, "离开", 2500);
            }
        };
    }//华山论剑

    public void hszm() throws Exception {
        new SingleTask_Content(mFairy, "华山争鸣") {

            void create() throws Exception {
                super.create();
                activity_name = "hszm.png";
                activity_type = 2;
            }

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
            }

            void inOperation() throws Exception {
                super.inOperation();

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (hour < 12) {
                    setTaskEnd();
                    return;
                }
            }

            boolean activity_end(FindResult act) throws Exception {
                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233,
                        new String[]{"wan.png", "wan1.png", "wan2.png", "wan4.png"});
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return true;
                }

                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233, new String[]{"wan3.png", "qian.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "前往", 2000);
                    oneSecond = 0;
                    frequencyInit("actError");
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void content_02() throws Exception {
                timeCount(15, 0);
                Thread.sleep(500);

                result = mFairy.findPic("hslj2.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic("sx.png");
                    if (result.sim > 0.8f) {

                        result = mFairy.findPic("sx1.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "奖励", 1000);
                            mFairy.onTap(893, 580, 958, 594, "", 500);
                            mFairy.onTap(893, 580, 958, 594, "", 500);
                            mFairy.onTap(982, 79, 998, 92, "", 500);
                        }

                        mFairy.onTap(1240, 46, 1262, 62, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("hszm1.png");
                    if (result.sim > 0.8f) {
                        err = 0;

                        result = mFairy.findPic("hszm3.png");
                        mFairy.onTap(0.8f, result, "报名", 500);

                    } else {
                        result = mFairy.findPic(17, 34, 374, 304, "hszm2.png");
                        mFairy.onTap(0.8f, result, "争鸣", 500);
                    }
                }

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                result = mFairy.findPic(456, 465, 796, 703, "hszm4.png");
                mFairy.onTap(0.8f, result, 883, 665, 916, 678, "继续", 1500);

                result = mFairy.findPic(1028, 527, 1273, 710, "hszm5.png");
                mFairy.onTap(0.8f, result, "离开", 2500);
            }
        };
    }//华山争鸣

    public void cszb() throws Exception {
        new SingleTask_Content(mFairy, "厨神争霸") {

            void create() throws Exception {
                super.create();
                activity_name = "cszb.png";
                activity_type = 2;
                li_ranks();
            }

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
            }

            boolean activity_end(FindResult act) throws Exception {
                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233,
                        new String[]{"wan.png", "wan1.png", "wan2.png", "wan4.png"});
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return true;
                }

                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233, new String[]{"wan3.png", "qian.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "前往", 2000);
                    oneSecond = 0;
                    frequencyInit("actError");
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (hour < 12) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(15, 0);
                Thread.sleep(500);

                result = mFairy.findPic("cszb1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("cszb9.png");
                    mFairy.onTap(0.8f, result, "领取奖励", 500);

                    if(AtFairyConfig.getOption("cszb").equals("1")) {

                        result = mFairy.findPic(930,195,1238,320,"cszb10.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(1240, 46, 1262, 62, "", 500);
                            setTaskEnd();
                            return;
                        }
                    }

                    result = mFairy.findPic(new String[]{"cszb2.png","cszb12.png"});
                    mFairy.onTap(0.8f, result, "报名", 500);
                }

                result = mFairy.findPic("cszb3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("cszb4.png");
                    mFairy.onTap(0.8f, result, "准备", 500);

                }

                result = mFairy.findPic("cszb7.png");
                mFairy.onTap(0.8f, result, "放弃使用", 500);

                result = mFairy.findPic("cszb11.png");
                mFairy.onTap(0.8f, result, "放弃使用", 500);

                result = mFairy.findPic("cszb5.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("cszb6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(618,603,701,706,"选择菜品",500);
                        mFairy.onTap(1201,647,1226,667,"上菜",2500);
                    }
                }

                result = mFairy.findPic(449,635,820,698, "cszb8.png");
                mFairy.onTap(0.8f, result, "离开", 2500);
            }
        };
    }//厨神争霸

    public void sjsl() throws Exception {
        new SingleTask_Content(mFairy, "世界首领") {

            void create() throws Exception {
                super.create();
                activity_name = "sjsl.png";
                activity_type = 2;

            }

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
            }

            boolean activity_end(FindResult act) throws Exception {
                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233,
                        new String[]{"wan.png", "wan1.png", "wan2.png", "wan3.png"});
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return true;
                }

                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233, new String[]{"wan4.png", "qian.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "前往", 2000);
                    oneSecond = 0;
                    frequencyInit("actError");
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (hour >= 20) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(15, 0);

                Thread.sleep(500);

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    mFairy.initMatTime();
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                result = mFairy.findPic("sjsl1.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(2000);
                    mFairy.onTap(773, 518, 833, 547, "", 500);
                    mFairy.onTap(773, 518, 833, 547, "", 500);
                    setTaskEnd();
                    return;
                }


            }
        };

    }//世界首领

    public void dmm() throws Exception {
        new SingleTask_Content(mFairy, "躲猫猫") {

            void create() throws Exception {
                super.create();
                activity_name = "dmm.png";
                activity_type = 2;
                li_ranks();
            }

            void content_02() throws Exception {
                timeCount(15, 0);

                Thread.sleep(500);

                result = mFairy.findPic("dmm1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("dmm2.png");
                    mFairy.onTap(0.8f, result, "开始匹配", 500);

                }

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                result = mFairy.findPic(377, 393, 653, 605, "dmm3.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(483, 504, 533, 522, "", 3000);
                    setTaskName(0);
                    return;
                }
            }
        };
    }//躲猫猫 未完成

    public void ssfy() throws Exception {
        new SingleTask_Content(mFairy, "少室风云") {

            void create() throws Exception {
                super.create();
                activity_name = "ssfy.png";
                activity_type = 2;
                li_ranks();
            }

            boolean activity_end(FindResult act) throws Exception {
                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233,
                        new String[]{"wan.png", "wan1.png", "wan2.png", "wan3.png", "wan4.png"});
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return true;
                }

                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233, new String[]{"wan3.png", "qian.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "前往", 2000);
                    oneSecond = 0;
                    frequencyInit("actError");
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void content_02() throws Exception {
                timeCount(15, 0);

                Thread.sleep(500);

                result = mFairy.findPic("ssfy1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("ssfy4.png");
                    if (result.sim < 0.8f) {
                        if (frequencyMap("end", 3)) {
                            mFairy.onTap(1169, 123, 1188, 138, "", 500);
                            setTaskEnd();
                        }
                        return;
                    }

                    result = mFairy.findPic("ssfy2.png");
                    mFairy.onTap(0.8f, result, "单人报名", 500);

                }

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                result = mFairy.findPic(1057, 563, 1263, 694, "ssfy3.png");
                mFairy.onTap(0.8f, result, "离开", 3000);

            }
        };
    }//少室风云

    public void xsjj() throws Exception {
        new SingleTask_Content(mFairy, "虚生绝境") {

            void create() throws Exception {
                super.create();
                activity_name = "xsjj.png";
                activity_type = 2;
                li_ranks();
            }

            boolean activity_end(FindResult act) throws Exception {
                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233,
                        new String[]{"wan.png", "wan1.png", "wan2.png", "wan3.png", "wan4.png"});
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return true;
                }

                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233, new String[]{"wan3.png", "qian.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "前往", 2000);
                    oneSecond = 0;
                    frequencyInit("actError");
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void content_02() throws Exception {
                timeCount(15, 0);

                Thread.sleep(500);

                result = mFairy.findPic("xsjj1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("xsjj4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1140, 130, 1155, 149, "", 500);
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic("xsjj2.png");
                    mFairy.onTap(0.8f, result, "开始匹配", 500);

                }

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    for (int i = 0; i < 3; i++) {
                        mFairy.onTap(1089, 514, 1129, 551, "", 300);
                    }
                }
                result = mFairy.findPic("xsjj3.png");
                mFairy.onTap(0.8f, result, "再来一局", 3000);

            }
        };
    }//虚生绝境

    public void bhwq() throws Exception {
        new SingleTask_Content(mFairy, "帮会温泉") {

            void create() throws Exception {
                super.create();
                li_ranks();
            }

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            boolean cancel() throws Exception {
                result = mFairy.findPic("bhrc2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(788, 418, 838, 442, "前往帮会领地", 6000);
                    return true;
                }
                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (minute > 30) {
                    setTaskEnd();
                    return;
                }
            }

            void content_01() throws Exception {
                timeCount(15, 99);

                result = mFairy.findPic("bh.png");
                if (result.sim > 0.8f) {
                    TaskMain.BANG = false;
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("bh2.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic("bh13.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "前往领地", 3000);
                        return;
                    } else {
                        result = mFairy.findPic(1149, 62, 1257, 681, "bh14.png");
                        mFairy.onTap(0.8f, result, "信息", 1000);
                    }
                }


                result = mFairy.findPic("bhwq2.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("温泉界面"));
                    err = 0;
                    mFairy.initMatTime();

                    if (AtFairyConfig.getOption("bhwq").equals("1")) {

                        if (judgeOneSecond()) {
                            result = mFairy.findPic("bhwq3.png");
                            mFairy.onTap(0.8f, result, "信息", 1000);

                            result = mFairy.findPic("bhwq4.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "快速选择", 500);
                                mFairy.onTap(734, 492, 792, 506, "", 500);
                                judgeOneSecond = 1;
                                return;
                            }
                        }
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(5, true) || oneSecond()) {


                        result = mFairy.findPic(25, 86, 263, 428, "bhwq1.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(0.75f, result, "帮会温泉", 1000);
                            mFairy.initMatTime();
                            frequencyInit("zhao");
                            return;
                        }

                        if (frequencyMap("zhao", 2)) {
                            result = mFairy.findPic("xkwp1.png");
                            mFairy.onTap(0.8f, result, "缩放栏", 1000);

                            result = mFairy.findPic(984, 186, 1265, 719, "bh1.png");
                            mFairy.onTap(0.8f, result, "帮会", 1000);
                        }

                    }
                }
            }
        };
    }//帮会温泉

    public void djrm() throws Exception {
        new SingleTask_Content(mFairy, "刀剑如梦") {

            void create() throws Exception {
                super.create();
                activity_name = "djrm.png";
                activity_type = 2;
                li_ranks();
            }

            void content_02() throws Exception {
                timeCount(15, 0);

                Thread.sleep(500);

                result = mFairy.findPic("djrm1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("djrm4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1169, 115, 1186, 132, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("djrm2.png");
                    mFairy.onTap(0.8f, result, "开始匹配", 500);

                }

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                result = mFairy.findPic(679, 421, 984, 621, "djrm3.png");
                mFairy.onTap(0.8f, result, "再来一局", 3000);

            }
        };
    }//刀剑如梦

    public void ddqx() throws Exception {
        new SingleTask_Content(mFairy, "岛防前线") {

            void create() throws Exception {
                super.create();
                activity_name = "dfqx.png";
                li_ranks();
            }

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                ls_activity();
            }

            void inOperation() throws Exception {
                super.inOperation();
                hour = mFairy.dateHour();
                if (hour >= 21) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(901, 338, 1164, 613, "dfqx2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "接受委托", 500);
                } else {
                    result = mFairy.findPic("dfqx1.png");
                    mFairy.onTap(0.75f, result, 1169, 645, 1193, 664, "陈", 500);
                }

                result = mFairy.findPic(379, 433, 592, 601, "dfqx4.png");
                if (result.sim > 0.72f) {
                    err = 0;
                    mFairy.initMatTime();
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                        result = mFairy.findPic(36, 63, 268, 481, "dfqx3.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(0.75f, result, "岛防前线", 1000);
                            frequencyInit("auto");
                            mFairy.initMatTime();
                            return;
                        }

                        taskSlide.slideRange(new int[]{4, 6}, 2, 0);
                    }
                }
            }
        };
    }//岛防前线

    public void shzf() throws Exception {
        new SingleTask_Content(mFairy, "沙海争锋") {

            void create() throws Exception {
                super.create();
                activity_name = "shzf.png";
                activity_type = 2;
                li_ranks();
            }

            void inOperation() throws Exception {
                super.inOperation();
                hour = mFairy.dateHour();
                if (hour >= 23) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(15, 0);

                Thread.sleep(500);

                result = mFairy.findPic("shzf1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("shzf5.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1170, 119, 1189, 133, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(991, 442, 1199, 613, "shzf2.png");
                    mFairy.onTap(0.8f, result, "单人报名", 500);
                }

                result = mFairy.findPic("shzf4.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(814, 556, 876, 608, "", 2000);
                    setTaskName(0);
                    return;
                }

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(5, true)) {
                        result = mFairy.findPic("shzf3.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(75, 232, 97, 252, "", 2000);
                            err = 0;
                            mFairy.initMatTime();
                            return;
                        }
                    }
                }
            }
        };
    }//沙海争锋

    public void wdlmt() throws Exception {
        new SingleTask_Content(mFairy, "五毒岭密探") {

            void create() throws Exception {
                super.create();
                activity_name = "wdlmt.png";
                activity_type = 2;
                li_ranks();
            }

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
            }

            void inOperation() throws Exception {
                super.inOperation();

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (minute > 30) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(15, 0);

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                result = mFairy.findPic(475, 585, 804, 699, "wdlmt1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(571, 643, 643, 662, "", 500);
                    setTaskName(0);
                    return;
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(5, true)) {

                    }
                } else {
                    Thread.sleep(1500);
                }
            }
        };
    }//五毒岭密探

}




