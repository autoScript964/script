package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class SingleTask {
    public GamePublicFuntion gamePublicFuntion;
    public FindResult result;
    public AtFairyImpl mFairy;


    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }

    /**
     * 领奖设置
     */
    public void setUp() throws Exception {
        new TaskContent(mFairy, "设置") {
            void inOperation() throws Exception {

                result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                mFairy.onTap(0.8f, result, "回城", 1000);
            }

            void content_01() throws Exception {
                if (timeCount(8, 0)) {
                    gamePublicFuntion.init();
                    return;
                }

                result = mFairy.findPic(new String[]{"hz1.png","hz2.png","hz3.png"});
                mFairy.onTap(0.8f, result, "更多", 500);

                result = mFairy.findPic(33,226,691,951,new String[]{"hz4.png","hz5.png","hz6.png"});
                mFairy.onTap(0.85f, result, "设置", 1000);

                result = mFairy.findPic(107,27,349,144,new String[]{"hz7.png","hz8.png","hz9.png","set3.png"});
                if(result.sim>0.8f){
                    mFairy.onTap(123,161,181,178,"选项",500);
                    mFairy.onTap(45,447,67,464,"画质",500);
                    setTaskEnd();
                    return;
                }
            }
        };
    }//设置

    public void qian() throws Exception {
        new TaskContent(mFairy, "每日签到") {
            @Override
            void inOperation() throws Exception {
                result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                mFairy.onTap(0.8f, result, "回城", 1000);
            }

            void other() throws Exception {
                for (int i = 0; i < 3; i++) {

                    result = mFairy.findPic(208, 475, 719, 537, "fuli7.png");
                    if (result.sim > 0.8f) {
                        i = 0;
                        mFairy.onTap(0.8f, result, result.x - 35, result.y + 50, result.x + 30, result.y + 55, "点击领取宝箱", 1000);
                    }
                    gamePublicFuntion.click();
                }
                setTaskName(2);
                gamePublicFuntion.init();
            }

            @Override
            void content_01() throws Exception {
                if (timeCount(10, 2)) {
                    gamePublicFuntion.init();
                    return;
                }

                gamePublicFuntion.click();

                result = mFairy.findPic(583, 114, 717, 546, "fuli.png");
                mFairy.onTap(0.85f, result, "福利", 1000);


                result = mFairy.findPic(282, 524, 594, 640, "fuli4.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(420, 640, 436, 653, "不再提示", 200);
                    mFairy.onTap(484, 717, 527, 737, "确定", 500);
                }

                result = mFairy.findPic("fuli8.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(194, 715, 235, 735, "金币不足", 1000);
                    other();
                    return;
                }


                result = mFairy.findPic("fuli3.png");
                if (result.sim > 0.85f) {

                    result = mFairy.findPic(97, 41, 717, 201, "fuli1.png");
                    mFairy.onTap(0.85f, result, "月签", 1000);

                    result = mFairy.findPic("fuli2.png");
                    mFairy.onTap(0.85f, result, "签到", 1000);

                    if (AtFairyConfig.getOption("buqian").equals("1")) {
                        result = mFairy.findPic("fuli5.png");
                        if (result.sim > 0.85f) {
                            err = 0;
                            mFairy.onTap(0.85f, result, "补签", 500);
                        }
                    } else {
                        result = mFairy.findPic("fuli5.png");
                        if (result.sim > 0.85f) {
                            other();
                            return;
                        }
                    }

                    result = mFairy.findPic("fuli6.png");
                    if (result.sim > 0.85f) {
                        other();
                        return;
                    }

                    if (slide(2)) {
                        mFairy.ranSwipe(169, 110, 655, 137, 3, 500, (long)1000);
                    }
                }
            }

            @Override
            void content_02() throws Exception {
                if (timeCount(10, 99)) {
                    gamePublicFuntion.init();
                    return;
                }

                result = mFairy.findPic(583, 114, 717, 546, "chaozhi.png");
                mFairy.onTap(0.85f, result, "超值礼包", 1000);

                result = mFairy.findPic("chaozhi1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(131, 153, 189, 170, "", 500);
                    mFairy.onTap(626, 407, 654, 441, "免费领取", 500);
                    gamePublicFuntion.init();
                    setTaskEnd();
                    return;
                }
            }
        };
    }//每日签到

    public void gui() throws Exception {
        new TaskContent(mFairy, "贵族领取") {
            @Override
            void inOperation() throws Exception {
                result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                mFairy.onTap(0.8f, result, "回城", 1000);
            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("guizu.png");
                mFairy.onTap(0.85f, result, "贵族", 1000);

                result = mFairy.findPic("guizu1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 566, 1134, 617, 1152, "领取", 1000);
                    gamePublicFuntion.init();
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("guizu2.png");
                if (result.sim > 0.85f) {
                    gamePublicFuntion.init();
                    setTaskEnd();
                    return;
                }
            }
        };
    }//贵族领取

    public void rchy() throws Exception {

        new TaskContent(mFairy, "日常活跃") {

            @Override
            void inOperation() throws Exception {
                result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                mFairy.onTap(0.8f, result, "回城", 1000);
            }

            void other() throws Exception {
                for (int i = 1; i <= 8; i++) {


                    result = mFairy.findPic(243, 96, 631, 148, "rchy6.png");
                    if (result.sim < 0.85f) {
                        return;
                    }

                    switch (i) {
                        case 1:
                            mFairy.onTap(101, 339, 131, 361, "活跃" + i, 500);
                            break;
                        case 2:
                            mFairy.onTap(181, 237, 199, 254, "活跃" + i, 500);
                            break;
                        case 3:
                            mFairy.onTap(256, 336, 274, 353, "活跃" + i, 500);
                            break;
                        case 4:
                            mFairy.onTap(332, 235, 361, 253, "活跃" + i, 500);
                            break;
                        case 5:
                            mFairy.onTap(410, 335, 428, 349, "活跃" + i, 500);
                            break;
                        case 6:
                            mFairy.onTap(489, 236, 511, 262, "活跃" + i, 500);
                            break;
                        case 7:
                            mFairy.onTap(565, 333, 587, 350, "活跃" + i, 500);
                            break;
                        case 8:
                            mFairy.onTap(656, 233, 675, 254, "活跃" + i, 500);
                            break;
                    }

                    result = mFairy.findPic("rchy2.png");
                    mFairy.onTap(0.8f, result, "知道了", 1000);

                    result = mFairy.findPic(152,799,540,1133,new String[]{"rchy4.png","ling.png","ling1.png"});
                    mFairy.onTap(0.8f, result, "领取", 1000);
                }
            }

            @Override
            void content_01() throws Exception {
                timeCount(6, 0);

                result = mFairy.findPic("rchy.png");
                mFairy.onTap(0.85f, result, "日常活跃", 1000);

                result = mFairy.findPic("rchy1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    for (int i = 1; i <= 2; i++) {

                        switch (i) {
                            case 1:
                                mFairy.onTap(437, 154, 517, 173, "使命任务", 1000);

                                for (int j = 0; j < 3; j++) {

                                    result = mFairy.findPic(541, 291, 696, 736, new String[]{"rchy5.png","rchy8.png"});
                                    if (result.sim > 0.85f) {
                                        j = 0;
                                        mFairy.onTap(0.85f, result, "领取", 500);
                                    }
                                }
                                break;
                            case 2:
                                mFairy.onTap(196, 154, 276, 169, "每日任务", 1000);

                                result = mFairy.findPic(new String[]{"rchy3.png","rchy7.png"});
                                if (result.sim > 0.85f) {
                                    other();
                                }
                                break;
                        }
                    }

                    result = mFairy.findPic("rchy1.png");
                    if (result.sim > 0.85f) {
                        result = mFairy.findPic(243, 96, 631, 148, "rchy6.png");
                        if (result.sim > 0.8f) {
                            return;
                        }
                        setTaskEnd();
                        gamePublicFuntion.init();
                        return;
                    }

                }
            }
        };


    }//日常活跃

    public void email() throws Exception {
        new TaskContent(mFairy, "读取邮件") {
            @Override
            void content_01() throws Exception {
                timeCount(20, 99);


                result = mFairy.findPic("email1.png");
                mFairy.onTap(0.85f, result, "邮件", 500);

                result = mFairy.findPic("email.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "一键读取", 1000);

                    gamePublicFuntion.click();

                    mFairy.onTap(49, 61, 80, 91, "", 500);
                }

                result = mFairy.findPic("email4.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "删除", 500);

                    gamePublicFuntion.click();

                    mFairy.onTap(49, 61, 80, 91, "", 500);
                }

                result = mFairy.findPic("email2.png");
                if (result.sim > 0.85f) {
                    result = mFairy.findPic(9, 300, 547, 879, "email3.png");
                    if (result.sim > 0.85f) {
                        oneJudgeCount = 0;
                        mFairy.onTap(0.85f, result, "发现红标", 500);
                    } else {
                        if (oneJudgeCount(2)) {
                            setTaskEnd();
                            gamePublicFuntion.init();
                            return;
                        }
                    }
                }
            }
        };


    }//领取邮件

    public void ys() throws Exception {
        new TaskContent(mFairy, "勇士殿堂") {
            @Override
            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(559, 497, 719, 768, "zonglan2.png");
                mFairy.onTap(0.85f, result, "坐标", 1000);

                result = mFairy.findPic(551, 468, 716, 770, "zonglan.png");
                mFairy.onTap(0.8f, result, "总览", 500);

                result = mFairy.findPic(177, 509, 320, 651, "zonglan1.png");
                mFairy.onTap(0.85f,result,642,287,673,317,"特殊",500);

                result = mFairy.findPic("teshu.png");
                mFairy.onTap(0.85f, result,371,469,450,483, "勇士殿堂", 500);

                result = mFairy.findPic("ysdt1.png");
                mFairy.onTap(0.85f, result, "勇士殿堂", 500);

                result = mFairy.findPic("ysdt2.png");
                mFairy.onTap(0.85f, result, "开始试炼", 500);
            }
        };
    }//勇士

    /**
     * 功能设置
     */
    public void yingxiong() throws Exception {
        new TaskContent(mFairy, "英雄升星") {
            @Override
            void inOperation() throws Exception {

            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("yingxiong.png");
                mFairy.onTap(0.85f, result, "英雄", 500);

                result = mFairy.findPic("yingxiong3.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(0.85f, result, "英雄升星", 1500);

                    result = mFairy.findPic("yingxiong4.png");
                    mFairy.onTap(0.85f, result, "确定", 1000);

                    gamePublicFuntion.close();
                }

                result = mFairy.findPic("yingxiong1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic(70, 470, 378, 578, "yingxiong2.png");
                    if (result.sim > 0.85f) {
                        oneJudgeCount = 0;
                        mFairy.onTap(0.85f, result, "升星", 500);
                        return;
                    } else {
                        if (oneJudgeCount(2)) {
                            setTaskEnd();
                            gamePublicFuntion.init();
                            return;
                        }
                    }
                }
            }
        };

    }//英雄升星

    public void haoyou() throws Exception {
        new TaskContent(mFairy, "好友赠送") {
            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("haoyou.png");
                mFairy.onTap(0.85f, result, "更多", 500);

                result = mFairy.findPic(33,226,691,951,new String[]{"haoyou1.png","yin_hy.png"});
                mFairy.onTap(0.85f, result, "好友", 500);

                result = mFairy.findPic("haoyou2.png");
                if (result.sim > 0.85f) {
                    for (int i = 1; i <= 2; i++) {
                        switch (i) {
                            case 1:
                                mFairy.onTap(126, 159, 196, 179, "qq好友", 500);
                                mFairy.onTap(553, 1185, 584, 1217, "一键赠送", 500);
                                break;
                            case 2:
                                mFairy.onTap(322, 161, 415, 181, "游戏好友", 500);
                                mFairy.onTap(469, 1181, 505, 1223, "一键赠送", 500);
                                break;
                        }
                    }

                    setTaskEnd();
                    gamePublicFuntion.init();
                    return;
                }
            }
        };

    }//好友赠送

    public void cheng() throws Exception {
        new TaskContent(mFairy, "成就领取") {
            @Override
            void content_01() throws Exception {
                timeCount(6, 0);
                result = mFairy.findPic("cheng.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic("cheng1.png");
                    if (result.sim > 0.85f) {
                        oneJudgeCount = 0;
                        mFairy.onTap(0.85f, result, "成就", 500);
                    } else {
                        if (oneJudgeCount(2)) {
                            setTaskEnd();
                            gamePublicFuntion.init();
                            return;
                        }
                    }
                    return;
                }

                result = mFairy.findPic("cheng3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    result = mFairy.findPic(521, 321, 702, 908, new String[]{"cheng4.png","yin_lq.png"});
                    if (result.sim > 0.85f) {
                        twoJudgeCount = 0;
                        mFairy.onTap(0.85f, result, "可领取", 500);
                        return;
                    }

                    result = mFairy.findPic(7, 143, 719, 187, "cheng2.png");
                    if (result.sim > 0.85f) {
                        twoJudgeCount = 0;
                        mFairy.onTap(0.85f, result, "发现红标", 500);
                        return;
                    }

                    if (twoJudgeCount(2)) {
                        setTaskEnd();
                        gamePublicFuntion.init();
                        return;
                    }
                    return;
                }

                mFairy.onTap(56, 76, 90, 107, "点击头像", 1000);
            }
        };
    }//成就领取

    public void suiji() throws Exception {
        new TaskContent(mFairy, "随机建设") {
            void inOperation() throws Exception {
                result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "回城", 1000);
                    err = 0;
                }
            }

            @Override
            void content_01() throws Exception {
                timeCount(5, 99);

                result = mFairy.findPic(173, 376, 461, 996, new String[]{"suiji3.png","suiji6.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "升级", 500);
                } else {
                    result = mFairy.findPic(new String[]{"suiji1.png", "suiji2.png"});
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "随机建设", 1000);
                    }
                }

                result = mFairy.findPic(431, 561, 573, 1124, "suiji5.png");
                if (result.sim > 0.85f) {
                    setTaskEnd();
                    gamePublicFuntion.init();
                    return;
                }

                result = mFairy.findPic(2, 159, 155, 457, "mainfei.png");
                mFairy.onTap(0.8f, result, "免费升级", 500);

                result = mFairy.findPic("shuiji4.png");
                mFairy.onTap(0.8f, result, "升级", 1000);

                result = mFairy.findPic("jianzao.png");
                mFairy.onTap(0.8f, result, "建造", 1000);

                result = mFairy.findPic(98, 191, 538, 958, "jianshe.png");
                mFairy.onTap(0.85f, result, "帮助", 1000);

                result = mFairy.findPic("suiji4.png");
                if (result.sim > 0.85f) {
                    setTaskEnd();
                    gamePublicFuntion.init();
                    return;
                }
            }
        };

    }//随机建设

    private int zhaomu = 0;
    private int xinpian = 0;
    private int zhuangbei = 0;

    public void zhaomu() throws Exception {
        new TaskContent(mFairy, "英雄招募") {

            @Override
            void create() throws Exception {
                zhaomu = 0;
            }

            void inOperation() throws Exception {
                result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "回城", 500);
                    judgeOneSecond = 1;
                }
                result = mFairy.findPic("buji1.png");
                mFairy.onTap(0.8f, result, "领取", 500);

            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("zhaomu4.png");
                mFairy.onTap(0.85f, result, 506, 1052, 553, 1078, "购买经验胶囊成功", 500);

                result = mFairy.findPic("zhaomu5.png");
                mFairy.onTap(0.85f, result, "确定", 500);

                result = mFairy.findPic(500, 17, 678, 149, "main.png");
                if (result.sim > 0.75f) {
                    result = mFairy.findPic(257, 428, 468, 616, "buji.png");
                    if (result.sim > 0.8f) {
                        judgeOneSecond = 0;
                        err = 0;
                        mFairy.onTap(0.8f, result, "补给箱", 500);
                        return;
                    }

                    result = mFairy.findPic(4, 619, 139, 875, "zhaomu1.png");
                    if (result.sim > 0.85f) {
                        err = 0;
                        mFairy.onTap(0.85f, result, "招募", 500);
                        return;
                    }

                    if (judgeOneSecond()) {
                        result = mFairy.findPic(9, 1184, 151, 1276, "chu.png");
                        mFairy.onTap(0.8f, result, "出城", 3000);
                    }
                }


                result = mFairy.findPic("zhaomu2.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic("zhaomu3.png");
                    if (result.sim > 0.85f) {
                        oneJudgeCount = 0;
                        mFairy.onTap(0.85f, result, "免费", 500);
                    } else {
                        if (oneJudgeCount(2)) {
                            setTaskEnd();
                            gamePublicFuntion.init();
                            return;
                        }
                    }

                }
            }
        }

        ;
    }//英雄招募

    public void xinpian() throws Exception {
        new TaskContent(mFairy, "芯片研究") {

            @Override
            void create() throws Exception {
                xinpian = 0;
            }

            void inOperation() throws Exception {
                result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "回城", 500);
                    judgeOneSecond = 1;
                }
                result = mFairy.findPic("buji1.png");
                mFairy.onTap(0.85f, result, "领取", 500);
            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("zhaomu7.png");
                mFairy.onTap(0.85f, result, 506, 1052, 553, 1078, "研究芯片成功", 500);

                result = mFairy.findPic("zhaomu5.png");
                mFairy.onTap(0.8f, result, "确定", 500);

                result = mFairy.findPic(500, 17, 678, 149, "main.png");
                if (result.sim > 0.75f) {
                    result = mFairy.findPic(257, 428, 468, 616, "buji.png");
                    if (result.sim > 0.8f) {
                        judgeOneSecond = 0;
                        err = 0;
                        mFairy.onTap(0.85f, result, "补给箱", 500);
                        return;
                    }

                    result = mFairy.findPic(4, 619, 139, 875, "zhaomu0.png");
                    if (result.sim > 0.85f) {
                        err = 0;
                        mFairy.onTap(0.85f, result, "研究", 500);
                        return;
                    }

                    if (judgeOneSecond()) {
                        result = mFairy.findPic(9, 1184, 151, 1276, "chu.png");
                        mFairy.onTap(0.8f, result, "出城", 3000);
                    }
                }


                result = mFairy.findPic("zhaomu2.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic("zhaomu6.png");
                    if (result.sim > 0.85f) {
                        oneJudgeCount = 0;
                        mFairy.onTap(0.85f, result, "免费", 500);
                    } else {
                        if (oneJudgeCount(2)) {
                            setTaskEnd();
                            gamePublicFuntion.init();
                            return;
                        }
                    }

                }
            }
        };
    }//芯片研究

    public void zhuangbei() throws Exception {
        new TaskContent(mFairy, "装备打造") {

            @Override
            void create() throws Exception {
                zhuangbei = 0;
            }

            void inOperation() throws Exception {
                result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "回城", 500);
                    judgeOneSecond = 1;
                }
                result = mFairy.findPic("buji1.png");
                mFairy.onTap(0.85f, result, "领取", 500);

            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("zhuangbei4.png");
                mFairy.onTap(0.85f, result, "确定", 500);

                result = mFairy.findPic(500, 17, 678, 149, "main.png");
                if (result.sim > 0.75f) {
                    result = mFairy.findPic(257, 428, 468, 616, "buji.png");
                    if (result.sim > 0.85f) {
                        judgeOneSecond = 0;
                        err = 0;
                        mFairy.onTap(0.85f, result, "补给箱", 500);
                        return;
                    }

                    result = mFairy.findPic(147, 468, 324, 668, "zhuangbei1.png");
                    if (result.sim > 0.85f) {
                        err = 0;
                        mFairy.onTap(0.85f, result, "打造", 500);
                        return;
                    }

                    if (judgeOneSecond()) {
                        result = mFairy.findPic(9, 1184, 151, 1276, "chu.png");
                        mFairy.onTap(0.8f, result, "出城", 3000);
                    }
                }


                result = mFairy.findPic("zhuangbei3.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic("zhuangbei2.png");
                    if (result.sim > 0.85f) {
                        oneJudgeCount = 0;
                        mFairy.onTap(0.85f, result, "免费", 500);
                    } else {
                        if (oneJudgeCount(2)) {
                            setTaskEnd();
                            gamePublicFuntion.init();
                            return;
                        }
                    }

                }
            }
        };
    }//装备打造

    public void shouqu() throws Exception {
        new TaskContent(mFairy, "收取资源") {


            void inOperation() throws Exception {
                result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "回城", 500);
                    judgeOneSecond = 1;
                }
                result = mFairy.findPic("buji1.png");
                mFairy.onTap(0.85f, result, "领取", 500);

            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic(500, 17, 678, 149, "main.png");
                if (result.sim > 0.75f) {
                    result = mFairy.findPic(257, 428, 468, 616, "buji.png");
                    if (result.sim > 0.85f) {
                        judgeOneSecond = 0;
                        err = 0;
                        mFairy.onTap(0.85f, result, "补给箱", 500);
                        return;
                    }

                    if (judgeOneSecond()) {
                        result = mFairy.findPic(9, 1184, 151, 1276, "chu.png");
                        mFairy.onTap(0.8f, result, "出城", 3000);
                        return;
                    }

                    for (int i = 0; i < 7; i++) {
                        switch (i) {
                            case 0:
                            case 1:
                            case 2:
                                mFairy.touchDown(580, 430);
                                mFairy.touchMove(140, 430, 200);
                                mFairy.touchMove(135, 430, 200);
                                mFairy.touchUp();
                                break;
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                                for (int j = 0; j < 2; j++) {

                                    result = mFairy.findPic(94, 193, 539, 955, new String[]{"zi1.png", "zi2.png", "z3.png", "z4.png"});
                                    if (result.sim > 0.92f) {
                                        mFairy.onTap(0.92f, result, "收取资源", 1000);
                                        j = 0;
                                    }
                                }
                                mFairy.touchDown(250, 650);
                                mFairy.touchMove(485, 285, 200);
                                mFairy.touchMove(485, 290, 200);
                                mFairy.touchUp();
                                break;
                        }
                    }
                    result = mFairy.findPic(94, 193, 539, 955, new String[]{"zi1.png", "zi2.png", "z3.png", "z4.png"});
                    mFairy.onTap(0.92f, result, "收取资源", 1000);

                    setTaskEnd();
                }
            }
        };
    }//收取资源

    private int jxc = 1;

    public void jxc() throws Exception {
        new TaskContent(mFairy, "军需处") {
            @Override
            void create() throws Exception {
                if (!AtFairyConfig.getOption("jxc").equals("")) {
                    jxc = Integer.parseInt(AtFairyConfig.getOption("jxc"));
                }
            }

            void inOperation() throws Exception {
                result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "回城", 500);
                    judgeOneSecond = 1;
                }
                result = mFairy.findPic("buji1.png");
                mFairy.onTap(0.85f, result, "领取", 500);
            }


            @Override
            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("jxc1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic(231, 419, 456, 534, "jxc2.png");
                    if (result.sim > 0.85f) {
                        oneJudgeCount = 0;
                        switch (jxc) {
                            case 1:
                                result = mFairy.findPic(71, 639, 221, 780, "jxc3.png");
                                if (result.sim > 0.85f) {
                                    setTaskEnd();
                                    return;
                                }
                                mFairy.onTap(130, 692, 172, 729, "矿石", 1000);
                                break;
                            case 2:
                                result = mFairy.findPic(494, 618, 673, 794, "jxc3.png");
                                if (result.sim > 0.85f) {
                                    setTaskEnd();
                                    return;
                                }
                                mFairy.onTap(552, 670, 598, 724, "石油", 1000);
                                break;
                            case 3:
                                result = mFairy.findPic(54, 891, 208, 1046, "jxc3.png");
                                if (result.sim > 0.85f) {
                                    setTaskEnd();
                                    return;
                                }
                                mFairy.onTap(117, 951, 154, 986, "合金", 1000);
                                break;
                            case 4:
                                result = mFairy.findPic(490, 893, 675, 1050, "jxc3.png");
                                if (result.sim > 0.85f) {
                                    setTaskEnd();
                                    return;
                                }
                                mFairy.onTap(552, 670, 598, 724, "稀土", 1000);
                                break;
                        }
                    } else {
                        if (oneJudgeCount(3)) {
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic(500, 17, 678, 149, "main.png");
                if (result.sim > 0.75f) {
                    result = mFairy.findPic(257, 428, 468, 616, "buji.png");
                    if (result.sim > 0.85f) {
                        judgeOneSecond = 0;
                        err = 0;
                        mFairy.onTap(0.85f, result, "补给箱", 500);
                        return;
                    }

                    if (judgeOneSecond()) {
                        result = mFairy.findPic(9, 1184, 151, 1276, "chu.png");
                        mFairy.onTap(0.8f, result, "出城", 3000);
                        return;
                    }

                    for (int i = 0; i < 2; i++) {
                        mFairy.touchDown(38, 622);
                        mFairy.touchMove(597, 485, 200);
                        mFairy.touchMove(590, 485, 200);
                        mFairy.touchUp();
                        Thread.sleep(1000);
                    }

                    Thread.sleep(1000);

                    for (int i = 0; i < 3; i++) {


                        result = mFairy.findPic(91, 231, 549, 783, "jxc.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "军需处", 1000);
                            return;
                        }
                    }
                    setTaskEnd();
                }
            }
        };
    }//军需处

    public void kaizhao() throws Exception {
        new TaskContent(mFairy, "开罩") {

            @Override
            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(515, 11, 677, 153, "map.png");
                if (result.sim > 0.75f) {
                    mFairy.onTap(343, 582, 371, 605, "点击中心点", 3000);

                    result = mFairy.findPic(356, 329, 673, 921, new String[]{"kaizhao1.png","kaizhao7.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "其他增益", 1500);
                        return;
                    }

                    result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                    mFairy.onTap(0.8f, result, "回城", 1000);
                }

                result = mFairy.findPic(9, 1184, 151, 1276, "chu.png");
                mFairy.onTap(0.8f, result, "出城", 2000);

                result = mFairy.findPic("kaizhao2.png");
                if (result.sim > 0.85f) {

                    if (oneJudgeCount(3)) {
                        result = mFairy.findPic(7, 120, 200, 343, "kaizhao3.png");
                        mFairy.onTap(0.85f, result, "时空屏障", 1000);
                    }

                    result = mFairy.findPic(236, 295, 520, 711, "kaizhao4.png");
                    if (result.sim > 0.85f) {
                        oneJudgeCount = 0;

                        result = mFairy.findPic(430, 209, 699, 1188, "zeng2.png");
                        if (result.sim > 0.95f) {
                            mFairy.onTap(0.95f, result, "使用", 1000);
                            gamePublicFuntion.close();
                            setTaskEnd();
                            return;
                        }

                        if (twoJudgeCount(2)) {
                            if (AtFairyConfig.getOption("jinkaizhao").equals("1")) {
                                result = mFairy.findPic(430, 209, 699, 1188, "zeng3.png");
                                if (result.sim > 0.85f) {
                                    twoJudgeCount = 0;
                                    mFairy.onTap(0.85f, result, "购买&使用", 3000);

                                    result = mFairy.findPic(74, 487, 362, 658, "zeng4.png");
                                    if (result.sim > 0.85f) {
                                        mFairy.onTap(422, 641, 438, 660, "今天不在提示", 300);
                                        mFairy.onTap(487, 714, 524, 733, "确定", 1000);
                                    }
                                    gamePublicFuntion.close();
                                    setTaskEnd();
                                    return;
                                }
                            } else {
                                gamePublicFuntion.close();
                                setTaskEnd();
                            }
                        }

                    }
                }
                result = mFairy.findPic("huifug.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 176, 831, 209, 845, "会覆盖状态,取消", 1000);
                    gamePublicFuntion.close();
                    setTaskEnd();
                    return;
                }

            }
        }

        ;
    }//开罩

    private int zeng = 1;
    public void zengchan() throws Exception {
        new TaskContent(mFairy, "增益") {
            @Override
            void create() throws Exception {
                zeng = 1;
            }

            void other() throws Exception {
                switch (zeng) {
                    case 1:
                        if (AtFairyConfig.getOption("ze" + zeng + "").equals("1")) {
                            LtLog.e(mFairy.getLineInfo("寻找 >>>攻击增益"));
                            FindResult zengRe = mFairy.findPic(122, 224, 390, 1228, "ze1.png");
                            if (zengRe.sim > 0.85f) {
                                result = mFairy.findPic(zengRe.x - 18, zengRe.y + 35, zengRe.x + 41, zengRe.y + 103, "zeng5.png");
                                if (result.sim > 0.9f) {
                                    zeng++;
                                    return;
                                }
                                mFairy.onTap(0.85f, zengRe, "攻击增益", 1000);
                            }
                        } else {
                            zeng++;
                            return;
                        }

                        break;
                    case 2:
                        if (AtFairyConfig.getOption("ze" + zeng + "").equals("1")) {
                            LtLog.e(mFairy.getLineInfo("寻找 >>>防御增益"));
                            FindResult zengRe = mFairy.findPic(122, 224, 390, 1228, "ze2.png");
                            if (zengRe.sim > 0.85f) {
                                result = mFairy.findPic(zengRe.x - 18, zengRe.y + 35, zengRe.x + 41, zengRe.y + 103, "zeng5.png");
                                if (result.sim > 0.9f) {
                                    zeng++;
                                    return;
                                }
                                mFairy.onTap(0.85f, zengRe, "防御增益", 1000);
                            }
                        } else {
                            zeng++;
                            return;
                        }
                        break;

                    case 3:
                        if (AtFairyConfig.getOption("ze" + zeng + "").equals("1")) {
                            LtLog.e(mFairy.getLineInfo("寻找 >>>军费缩减"));
                            FindResult zengRe = mFairy.findPic(122, 224, 390, 1228, "ze3.png");
                            if (zengRe.sim > 0.85f) {
                                result = mFairy.findPic(zengRe.x - 18, zengRe.y + 35, zengRe.x + 41, zengRe.y + 103, "zeng5.png");
                                if (result.sim > 0.9f) {
                                    zeng++;
                                    return;
                                }

                                mFairy.onTap(0.85f, zengRe, "军费缩减", 1000);
                                mFairy.ranSwipe(459, 552, 491, 701, 2, 500, (long)1000);
                            }
                        } else {
                            zeng++;
                            return;
                        }
                        break;
                    case 4:
                        if (AtFairyConfig.getOption("ze" + zeng + "").equals("1")) {
                            LtLog.e(mFairy.getLineInfo("寻找 >>>反侦察"));
                            for (int i = 0; i < 5; i++) {
                                FindResult zengRe = mFairy.findPic(122, 224, 390, 1228, "ze4.png");
                                if (zengRe.sim > 0.85f) {
                                    result = mFairy.findPic(zengRe.x - 18, zengRe.y + 35, zengRe.x + 41, zengRe.y + 103, "zeng5.png");
                                    if (result.sim > 0.9f) {
                                        zeng++;
                                        return;
                                    }

                                    mFairy.onTap(0.85f, zengRe, "反侦察", 1000);

                                    break;
                                } else {
                                    mFairy.ranSwipe(444, 535, 497, 860, 2, 500,  (long)1000);
                                }

                                if (i == 4) {
                                    gamePublicFuntion.init();
                                    return;
                                }
                            }
                        } else {
                            zeng++;
                            return;
                        }

                        break;
                    case 5:
                        if (AtFairyConfig.getOption("ze" + zeng + "").equals("1")) {
                            LtLog.e(mFairy.getLineInfo("寻找 >>>部队镜像"));
                            for (int i = 0; i < 5; i++) {
                                FindResult zengRe = mFairy.findPic(122, 224, 390, 1228, new String[]{"ze5.png","ze9.png"});
                                if (zengRe.sim > 0.85f) {
                                    result = mFairy.findPic(zengRe.x - 18, zengRe.y + 35, zengRe.x + 41, zengRe.y + 103, "zeng5.png");
                                    if (result.sim > 0.9f) {
                                        zeng++;
                                        return;
                                    }

                                    mFairy.onTap(0.85f, zengRe, "部队镜像", 1000);
                                    break;
                                } else {
                                    mFairy.ranSwipe(444, 535, 497, 860, 2, 500,  (long)1000);
                                }

                                if (i == 4) {
                                    gamePublicFuntion.init();
                                    return;
                                }
                            }
                        } else {
                            zeng++;
                            return;
                        }
                        break;
                    case 6:
                        if (AtFairyConfig.getOption("ze" + zeng + "").equals("1")) {
                            LtLog.e(mFairy.getLineInfo("寻找 >>>行军上限"));
                            for (int i = 0; i < 5; i++) {
                                FindResult zengRe = mFairy.findPic(122, 224, 390, 1228, "ze6.png");
                                if (zengRe.sim > 0.85f) {
                                    result = mFairy.findPic(zengRe.x - 18, zengRe.y + 35, zengRe.x + 41, zengRe.y + 103, "zeng5.png");
                                    if (result.sim > 0.9f) {
                                        zeng++;
                                        return;
                                    }

                                    mFairy.onTap(0.85f, zengRe, "行军上限", 1000);
                                    mFairy.ranSwipe(423, 376, 471, 1025, 2, 500,  (long)1500);
                                    break;
                                } else {
                                    mFairy.ranSwipe(444, 535, 497, 860, 2, 500,  (long)1000);
                                }

                                if (i == 4) {
                                    gamePublicFuntion.init();
                                    return;
                                }
                            }
                        } else {
                            zeng++;
                            return;
                        }
                        break;
                    case 7:
                        if (AtFairyConfig.getOption("ze" + zeng + "").equals("1")) {
                            LtLog.e(mFairy.getLineInfo("寻找 >>>采集加速"));
                            for (int i = 0; i < 5; i++) {
                                FindResult zengRe = mFairy.findPic(122, 224, 390, 1228, "ze7.png");
                                if (zengRe.sim > 0.85f) {
                                    result = mFairy.findPic(zengRe.x - 18, zengRe.y + 35, zengRe.x + 41, zengRe.y + 103, "zeng5.png");
                                    if (result.sim > 0.9f) {
                                        zeng++;
                                        return;
                                    }
                                    mFairy.onTap(0.85f, zengRe, "采集加速", 1000);
                                    mFairy.ranSwipe(423, 376, 471, 1025, 2, 500,  (long)1500);
                                    break;
                                } else {
                                    mFairy.ranSwipe(444, 535, 497, 860, 2, 500,  (long)1000);
                                }

                                if (i == 4) {
                                    gamePublicFuntion.init();
                                    return;
                                }
                            }
                        } else {
                            setTaskEnd();
                            zeng++;
                            return;
                        }

                        break;
                }

                for (int i = 0; i < 6; i++) {

                    result = mFairy.findPic(430, 209, 699, 1188, "zeng2.png");
                    if (result.sim > 0.85f) {
                        zeng++;
                        mFairy.onTap(0.85f, result, "使用", 1000);
                        return;
                    }

                    if (i >= 3) {
                        if (AtFairyConfig.getOption("jinze" + zeng + "").equals("1")) {
                            result = mFairy.findPic(430, 209, 699, 1188, "zeng3.png");
                            if (result.sim > 0.85f) {
                                mFairy.onTap(0.85f, result, "购买&使用", 3000);

                                result = mFairy.findPic(74, 487, 362, 658, "zeng4.png");
                                if (result.sim > 0.85f) {
                                    mFairy.onTap(422, 641, 438, 660, "今天不在提示", 300);
                                    mFairy.onTap(487, 714, 524, 733, "确定", 1000);
                                }
                                zeng++;
                                return;
                            }
                        }
                    }
                }

                zeng++;
            }//

            void content_01() throws Exception {
                timeCount(10, 99);

                if (zeng > 7 || zeng <= 0) {
                    setTaskEnd();
                    gamePublicFuntion.init();
                    return;
                }

                result = mFairy.findPic(515, 11, 677, 153, "map.png");
                if (result.sim > 0.75f) {
                    mFairy.onTap(343, 582, 371, 605, "点击中心点", 2000);

                    result = mFairy.findPic(356, 329, 673, 921, new String[]{"kaizhao1.png","kaizhao6.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "其他增益", 1500);
                        return;
                    }

                    result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                    mFairy.onTap(0.9f, result, "回城", 1000);
                }

                result = mFairy.findPic(9, 1184, 151, 1276, "chu.png");
                mFairy.onTap(0.9f, result, "出城", 3000);

                result = mFairy.findPic("kaizhao2.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    other();
                }

                result = mFairy.findPic("jinbuzu.png");
                mFairy.onTap(0.85f, result, 183, 714, 219, 735, "金币不足", 1000);

                result = mFairy.findPic("huifug.png");
                mFairy.onTap(0.85f, result, 176, 831, 209, 845, "会覆盖状态,取消", 1000);
            }
        };
    }//增产

    private int zhanlue = 1;
    public void zhanlue() throws Exception {
        new TaskContent(mFairy, "战略") {
            @Override
            void create() throws Exception {
                zhanlue = 1;
            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(559, 497, 719, 768, "zonglan2.png");
                mFairy.onTap(0.85f, result, "坐标", 1000);

                result = mFairy.findPic(551, 468, 716, 770, "zonglan.png");
                mFairy.onTap(0.8f, result, 679, 675, 702, 700, "战略", 500);

                result = mFairy.findPic(272, 34, 391, 404, "zhanlue.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    switch (zhanlue) {
                        case 1:
                            if (AtFairyConfig.getOption("zhanlue1").equals("1")) {
                                result = mFairy.findPic(234, 112, 715, 1097, "zhanlue1.png");
                                if (result.sim > 0.8f) {
                                    mFairy.onTap(0.8f, result, "高效采集", 1500);

                                    result = mFairy.findPic(360, 118, 603, 1095, "zhanlue0.png");
                                    mFairy.onTap(0.8f, result, "使用", 500);
                                    oneJudgeCount = 0;
                                    zhanlue++;
                                    return;
                                }
                            } else {
                                zhanlue++;
                                return;
                            }
                            break;
                        case 2:
                            if (AtFairyConfig.getOption("zhanlue2").equals("1")) {
                                result = mFairy.findPic(234, 112, 715, 1097, "zhanlue2.png");
                                if (result.sim > 0.8f) {
                                    mFairy.onTap(0.8f, result, "资源补给", 1000);

                                    result = mFairy.findPic(360, 118, 603, 1095, "zhanlue0.png");
                                    mFairy.onTap(0.8f, result, "使用", 500);
                                    oneJudgeCount = 0;
                                    zhanlue++;
                                    return;
                                }
                            } else {
                                zhanlue++;
                                return;
                            }
                            break;
                        case 3:
                            if (AtFairyConfig.getOption("zhanlue3").equals("1")) {
                                result = mFairy.findPic(234, 112, 715, 1097, "zhanlue3.png");
                                if (result.sim > 0.8f) {
                                    mFairy.onTap(0.8f, result, "行军扩编", 1000);

                                    result = mFairy.findPic(360, 118, 603, 1095, "zhanlue0.png");
                                    mFairy.onTap(0.8f, result, "使用", 500);
                                    oneJudgeCount = 0;
                                    zhanlue++;
                                    return;
                                }
                            } else {
                                zhanlue++;
                                return;
                            }
                            break;
                        case 4:
                            if (AtFairyConfig.getOption("zhanlue4").equals("1")) {
                                result = mFairy.findPic(234, 112, 715, 1097, "zhanlue4.png");
                                if (result.sim > 0.8f) {
                                    mFairy.onTap(0.8f, result, "精力倍增", 1000);

                                    result = mFairy.findPic(360, 118, 603, 1095, "zhanlue0.png");
                                    mFairy.onTap(0.8f, result, "使用", 500);
                                    oneJudgeCount = 0;
                                    zhanlue++;
                                    return;
                                }
                            } else {
                                zhanlue++;
                                return;
                            }

                            break;
                        case 5:
                            if (AtFairyConfig.getOption("zhanlue5").equals("1")) {
                                result = mFairy.findPic(234, 112, 715, 1097, "zhanlue5.png");
                                if (result.sim > 0.8f) {
                                    mFairy.onTap(0.8f, result, "城防急援", 1000);

                                    result = mFairy.findPic(360, 118, 603, 1095, "zhanlue0.png");
                                    mFairy.onTap(0.8f, result, "使用", 500);
                                    oneJudgeCount = 0;
                                    setTaskEnd();
                                    return;
                                }
                            } else {
                                setTaskEnd();
                                return;
                            }
                            break;
                        default:
                            setTaskEnd();
                            break;
                    }

                    mFairy.ranSwipe(455, 496, 501, 738, 2, 500,  (long)500);
                    if (oneJudgeCount(3)) {
                        zhanlue++;
                        for (int i = 0; i < 3; i++) {
                            mFairy.ranSwipe(455, 496, 501, 738, 0, 500,  (long)100);
                        }
                    }

                }
            }
        };
    }//战略

    private int zhekou = 1;
    private boolean booljin = false;

    public void shangren() throws Exception {
        new TaskContent(mFairy, "特惠商人") {

            @Override
            void create() throws Exception {
                if (AtFairyConfig.getOption("jinbi").equals("1")) {
                    booljin = true;
                }

                if (!AtFairyConfig.getOption("zhekou").equals("")) {
                    zhekou = Integer.parseInt(AtFairyConfig.getOption("zhekou"));
                }
            }

            boolean pai1() throws Exception {
                if (AtFairyConfig.getOption("mai1").equals("1")) {
                    return true;
                }
                return false;
            }//第一排

            boolean pai2() throws Exception {
                if (AtFairyConfig.getOption("mai9").equals("1")) {
                    return true;
                }
                if (AtFairyConfig.getOption("mai10").equals("1")) {
                    return true;
                }
                if (AtFairyConfig.getOption("mai11").equals("1")) {
                    return true;
                }
                if (AtFairyConfig.getOption("mai12").equals("1")) {
                    return true;
                }
                if (AtFairyConfig.getOption("mai13").equals("1")) {
                    return true;
                }
                return false;
            }//第一排

            boolean pai3() throws Exception {

                if (AtFairyConfig.getOption("mai8").equals("1")) {
                    return true;
                }

                if (AtFairyConfig.getOption("mai2").equals("1")) {
                    return true;
                }

                if (AtFairyConfig.getOption("mai3").equals("1")) {
                    return true;
                }
                if (AtFairyConfig.getOption("mai4").equals("1")) {
                    return true;
                }
                if (AtFairyConfig.getOption("mai5").equals("1")) {
                    return true;
                }

                if (AtFairyConfig.getOption("mai6").equals("1")) {
                    return true;
                }

                if (AtFairyConfig.getOption("mai7").equals("1")) {
                    return true;
                }

                if (AtFairyConfig.getOption("mai14").equals("1")) {
                    return true;
                }

                if (AtFairyConfig.getOption("mai15").equals("1")) {
                    return true;
                }
                if (AtFairyConfig.getOption("mai16").equals("1")) {
                    return true;
                }
                return false;
            }//第一排

            boolean selectGm(int x, int y, int x1, int y1, int pai) throws Exception {
                switch (pai) {
                    case 1:
                        if (AtFairyConfig.getOption("mai1").equals("1")) {
                            return true;
                        }
                        break;
                    case 2:
                        if (AtFairyConfig.getOption("mai9").equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "mai9.png");
                            if (result.sim > 0.85f) {
                                return true;
                            }
                        }
                        if (AtFairyConfig.getOption("mai10").equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "mai10.png");
                            if (result.sim > 0.85f) {
                                return true;
                            }
                        }
                        if (AtFairyConfig.getOption("mai11").equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "mai11.png");
                            if (result.sim > 0.85f) {
                                return true;
                            }
                        }
                        if (AtFairyConfig.getOption("mai12").equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "mai12.png");
                            if (result.sim > 0.85f) {
                                return true;
                            }
                        }
                        if (AtFairyConfig.getOption("mai13").equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "mai13.png");
                            if (result.sim > 0.85f) {
                                return true;
                            }
                        }
                        break;
                    case 3:
                        if (AtFairyConfig.getOption("mai8").equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "mai8.png");
                            if (result.sim > 0.85f) {
                                return true;
                            }
                        }

                        if (AtFairyConfig.getOption("mai2").equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "mai2.png");
                            if (result.sim > 0.85f) {
                                return true;
                            }
                        }

                        if (AtFairyConfig.getOption("mai3").equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, new String[]{"mai3.png", "mai3a.png"});
                            if (result.sim > 0.85f) {
                                return true;
                            }
                        }
                        if (AtFairyConfig.getOption("mai4").equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "mai4.png");
                            if (result.sim > 0.85f) {
                                return true;
                            }
                        }
                        if (AtFairyConfig.getOption("mai5").equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "mai5.png");
                            if (result.sim > 0.85f) {
                                return true;
                            }
                        }

                        if (AtFairyConfig.getOption("mai6").equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "mai6.png");
                            if (result.sim > 0.85f) {
                                return true;
                            }
                        }

                        if (AtFairyConfig.getOption("mai7").equals("1")) {


                        }

                        if (AtFairyConfig.getOption("mai14").equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "mai14.png");
                            if (result.sim > 0.85f) {
                                return true;
                            }
                        }

                        if (AtFairyConfig.getOption("mai15").equals("1")) {


                        }
                        if (AtFairyConfig.getOption("mai16").equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "mai16.png");
                            if (result.sim > 0.85f) {
                                return true;
                            }
                        }
                        break;

                }
                return false;
            }//查询购买

            boolean gm() throws Exception {
                result = mFairy.findPic("shangren3.png");
                mFairy.onTap(0.85f, result, "购买", 1000);

                result = mFairy.findPic("shangren5.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(422, 640, 435, 657, "不再提示", 300);
                    mFairy.onTap(482, 715, 520, 736, "确定购买", 1000);
                }

                result = mFairy.findPic("shangren6.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(183, 715, 235, 734, "取消", 1000);
                    return true;
                }
                result = mFairy.findPic("shangren7.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(171, 829, 215, 849, "取消", 1000);
                    return true;
                }
                return false;
            }//

            boolean gms() throws Exception {
                int x = 170;
                int y = 215;
                boolean gm = false;
                FindResult shua = null;
                for (int j = 0; j < 3; j++) {

                    LtLog.e(mFairy.getLineInfo("第" + (j + 1) + "排"));

                    switch (j + 1) {
                        case 1:
                            if (pai1() == false) {
                                continue;
                            }
                            break;
                        case 2:
                            if (pai2() == false) {
                                continue;
                            }
                            break;
                        case 3:
                            if (pai3() == false) {
                                continue;
                            }
                            break;
                    }

                    for (int i = 0; i < 4; i++) {

                        result = mFairy.findPic(19 + (i * x), 560 + (j * y), 186 + (i * x), 615 + (j * y), "shangren4.png");
                        if (result.sim > 0.85f) {
                            continue;
                        }

                        if (booljin == false) {
                            result = mFairy.findPic(19 + (i * x), 560 + (j * y), 186 + (i * x), 615 + (j * y), "shangren2.png");
                            if (result.sim > 0.85f) {
                                continue;
                            }
                        }

                        result = mFairy.findPic(19 + (i * x), 560 + (j * y), 186 + (i * x), 615 + (j * y), "jinzhuan.png");
                        if (result.sim > 0.8f) {
                            continue;
                        }

                        switch (j + 1) {
                            case 1:
                                if (selectGm(17 + (i * x), 401 + (j * y), 186 + (i * x), 613 + (j * y), j + 1) == false) {
                                    continue;
                                }
                                break;
                            case 2:
                                if (selectGm(17 + (i * x), 401 + (j * y), 186 + (i * x), 613 + (j * y), j + 1) == false) {
                                    continue;
                                }
                                break;
                            case 3:
                                if (selectGm(17 + (i * x), 401 + (j * y), 186 + (i * x), 613 + (j * y), j + 1) == false) {
                                    continue;
                                }
                                break;
                        }

                        switch (zhekou) {
                            case 1:
                                gm = true;
                            case 2:
                                result = mFairy.findPic(12 + (i * x), 401 + (j * y), 70 + (i * x), 453 + (j * y), "zhekou1.png");
                                if (result.sim < 0.85f) {
                                    gm = true;
                                }
                                break;
                            case 3:
                                result = mFairy.findPic(12 + (i * x), 401 + (j * y), 70 + (i * x), 453 + (j * y), new String[]{"zhekou1.png", "zhekou2.png"});
                                if (result.sim < 0.85f) {
                                    gm = true;
                                }
                                break;
                            case 4:
                                result = mFairy.findPic(12 + (i * x), 401 + (j * y), 70 + (i * x), 453 + (j * y),
                                        new String[]{"zhekou1.png", "zhekou2.png", "zhekou3.png"});
                                if (result.sim < 0.85f) {
                                    gm = true;
                                }
                                break;
                        }

                        if (gm) {
                            mFairy.onTap(90 + (i * x), 578 + (j * y), 139 + (i * x), 599 + (j * y), "点击购买", 1000);
                            gm = false;
                        }

                        if (gm()) {
                            LtLog.e("资源或者金币不足。");
                            setTaskEnd();
                            return true;
                        }
                    }
                }
                return false;
            }//gm

            void inOperation() throws Exception {
                result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "回城", 500);
                    judgeOneSecond = 1;
                }
                result = mFairy.findPic("buji1.png");
                mFairy.onTap(0.85f, result, "领取", 500);
            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("hei.png");
                mFairy.onTap(0.85f, result, 484, 755, 530, 773, "确定刷新", 1000);

                result = mFairy.findPic(500, 17, 678, 149, "main.png");
                if (result.sim > 0.75f) {
                    result = mFairy.findPic(257, 428, 468, 616, "buji.png");
                    if (result.sim > 0.85f) {
                        judgeOneSecond = 0;
                        err = 0;
                        mFairy.onTap(0.85f, result, "补给箱", 500);
                        return;
                    }
                    result = mFairy.findPic(349, 772, 622, 1054, "shangren8.png");
                    if (result.sim > 0.85f) {
                        err = 0;
                        mFairy.onTap(0.85f, result, "特惠商人", 500);
                        return;
                    }
                    if (judgeOneSecond()) {
                        result = mFairy.findPic(9, 1184, 151, 1276, "chu.png");
                        mFairy.onTap(0.8f, result, "出城", 3000);
                    }
                }

                result = mFairy.findPic("shangren1.png");
                if (result.sim > 0.85f) {
                    result = mFairy.findPic(262, 327, 434, 418, "shangren10.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        if (gms()) {
                            return;
                        }

                        result = mFairy.findPic(241, 1142, 465, 1274, "shangren9.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "免费刷新", 1000);
                            return;
                        }
                        setTaskEnd();
                        gamePublicFuntion.close();
                    }
                    return;
                }
            }
        };
    }//特惠商人

    /**
     * 联盟任务
     */
    public void lmqian() throws Exception {
        new TaskContent(mFairy, "联盟签到") {

            @Override
            void init() throws Exception {
                result = mFairy.findPic("lm.png");
                if (result.sim < 0.85f) {
                    gamePublicFuntion.init();
                }

                setTaskName(1);
            }

            @Override
            void content_01() throws Exception {
                timeCount(6, 99);


                result = mFairy.findPic("lmqian.png");
                mFairy.onTap(0.85f, result, "联盟", 500);

                result = mFairy.findPic(346, 1141, 677, 1263, "zdlm.png");
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("zdlm").equals("1")) {
                        mFairy.onTap(0.8f, result, "自动加入联盟", 3000);
                    } else {
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("lm1.png");
                if (result.sim > 0.85f) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("lm.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(339, 964, 366, 998, "任务", 500);
                    return;
                }

                result = mFairy.findPic("lmqian1.png");
                if (result.sim > 0.85f) {
                    for (int i = 1; i <= 2; i++) {


                        result = mFairy.findPic(280, 109, 629, 153, "lmqian3.png");
                        if (result.sim < 0.85f) {
                            setTaskEnd();
                            gamePublicFuntion.close();
                            return;
                        }

                        switch (i) {
                            case 1:
                                mFairy.onTap(217, 156, 279, 173, "日常任务", 500);
                                result = mFairy.findPic("lmqian2.png");
                                if (result.sim > 0.85f) {
                                    mFairy.onTap(592, 381, 615, 394, "签到", 500);
                                    mFairy.onTap(310, 1215, 408, 1231, "全部领取", 500);
                                }

                                break;
                            case 2:
                                mFairy.onTap(448, 157, 502, 176, "随机任务", 1000);
                                mFairy.onTap(310, 1215, 408, 1231, "全部领取", 500);
                                break;
                        }
                    }
                }
            }
        };
    }//联盟签到

    private int errjx = 0;
    public void lmkeji() throws Exception {
        new TaskContent(mFairy, "联盟科技") {
            @Override
            void init() throws Exception {
                result = mFairy.findPic("lm.png");
                if (result.sim < 0.85f) {
                    gamePublicFuntion.init();
                }
                errjx=0;
                setTaskName(1);
            }

            @Override
            void content_01() throws Exception {
                timeCount(6, 0);

                result = mFairy.findPic("lmqian.png");
                mFairy.onTap(0.85f, result, "联盟", 500);

                result = mFairy.findPic(346, 1141, 677, 1263, "zdlm.png");
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("zdlm").equals("1")) {
                        mFairy.onTap(0.8f, result, "自动加入联盟", 3000);
                    } else {
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("lm1.png");
                if (result.sim > 0.85f) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("lm.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(100, 719, 132, 757, "科技", 500);
                    return;
                }

                result = mFairy.findPic("lmkeji.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    for (int i = 1; i <= 7; i++) {


                        result = mFairy.findPic(96, 374, 346, 570, "lmkeji3.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(181, 601, 221, 615, "次数不足", 500);
                            setTaskEnd();
                            gamePublicFuntion.close();
                            return;
                        }
                        switch (i) {
                            case 1:
                                mFairy.onTap(225, 320, 339, 361, "位置" + i, 1000);
                                break;
                            case 2:
                                mFairy.onTap(220, 459, 322, 489, "位置" + i, 1000);
                                break;
                            case 3:
                                mFairy.onTap(215, 640, 314, 677, "位置" + i, 1000);
                                break;
                            case 4:
                                mFairy.onTap(235, 767, 384, 807, "位置" + i, 1000);
                                break;
                            case 5:
                                mFairy.onTap(241, 895, 365, 929, "位置" + i, 1000);
                                break;
                            case 6:
                                mFairy.onTap(201, 1034, 301, 1060, "位置" + i, 1000);
                                break;
                            case 7:
                                mFairy.onTap(218, 1164, 310, 1193, "位置" + i, 1000);
                                break;

                        }

                        for (int j = 0; j < 3; j++) {
                            result = mFairy.findPic(new String[]{"lmkeji1.png","lmkeji4.png"});
                            if (result.sim > 0.85f) {
                                j = 0;
                                errjx++;
                                if(errjx>25){
                                    setTaskEnd();
                                    errjx=0;
                                    return;
                                }
                                mFairy.onTap(0.85f, result, "捐献", 200);
                            } else {
                                result = mFairy.findPic("lmkeji.png");
                                if (result.sim < 0.85f) {
                                    result = mFairy.findPic(387, 5, 713, 999, new String[]{"close1.png", "close2.png", "close3.png"});
                                    mFairy.onTap(0.85f, result, "关闭", 500);
                                }
                                break;
                            }
                        }

                        Thread.sleep(1000);
                    }

                    setTaskEnd();
                    gamePublicFuntion.close();
                    return;
                }
            }
        };
    }//联盟科技

    public void lmliwu() throws Exception {
        new TaskContent(mFairy, "联盟礼物") {
            @Override
            void init() throws Exception {
                result = mFairy.findPic("lm.png");
                if (result.sim < 0.85f) {
                    gamePublicFuntion.init();
                }
                setTaskName(1);
            }

            @Override
            void content_01() throws Exception {
                timeCount(6, 0);

                result = mFairy.findPic("lmqian.png");
                mFairy.onTap(0.85f, result, "联盟", 500);

                result = mFairy.findPic(346, 1141, 677, 1263, "zdlm.png");
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("zdlm").equals("1")) {
                        mFairy.onTap(0.8f, result, "自动加入联盟", 3000);
                    } else {
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("lm1.png");
                if (result.sim > 0.85f) {
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("lm.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(208, 1195, 228, 1220, "礼物", 500);
                    return;
                }

                result = mFairy.findPic("lmliwu.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(305, 1217, 393, 1230, "全部开启", 500);
                    mFairy.onTap(305, 1217, 393, 1230, "全部开启", 500);
                    gamePublicFuntion.click();
                    setTaskEnd();
                    gamePublicFuntion.close();
                    return;
                }
            }
        };
    }//联盟礼物

    private int lmbzType = 1;

    public void lmbz() throws Exception {
        final String range1 = "434,321,466,357";
        final String color3 = "90,70,125";//紫色
        final String color2 = "65,81,131";//蓝色
        final String color1 = "76,120,67";//绿色

        new TaskContent(mFairy, "联盟宝藏") {

            boolean other(final String color) throws Exception {
                if (mFairy.getColorNum(range1, color, 0.95f, 1) > 100) {

                    result = mFairy.findPic(525, 415, 704, 601, new String[]{"lmbz6.png","wj.png"});
                    mFairy.onTap(0.8f, result, "挖掘", 500);

                    return false;
                } else {
                    result = mFairy.findPic("lmbz3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "免费刷新", 500);
                        return true;
                    } else {
                        if (AtFairyConfig.getOption("shua").equals("2")) {
                            result = mFairy.findPic(242, 1201, 379, 1273, "lmbz4.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "金币刷新", 500);
                                return true;
                            }
                        } else {
                            result = mFairy.findPic(525, 415, 704, 601, new String[]{"lmbz6.png","wj.png"});
                            mFairy.onTap(0.8f, result, "挖掘", 500);
                        }
                    }
                }
                return false;
            }

            @Override
            void init() throws Exception {
                result = mFairy.findPic("lm.png");
                if (result.sim < 0.85f) {
                    gamePublicFuntion.init();
                }

                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(6, 0);

                result = mFairy.findPic("lmqian.png");
                mFairy.onTap(0.85f, result, "联盟", 500);

                result = mFairy.findPic(346, 1141, 677, 1263, "zdlm.png");
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("zdlm").equals("1")) {
                        mFairy.onTap(0.8f, result, "自动加入联盟", 3000);
                    } else {
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("lmbz5.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(418, 638, 434, 657, "不再提示", 300);
                    mFairy.onTap(477, 716, 536, 737, "确定", 500);
                }

                result = mFairy.findPic("lm1.png");
                if (result.sim > 0.85f) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("lm.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(349, 1196, 375, 1211, "宝藏", 500);
                    return;
                }

                result = mFairy.findPic(new String[]{"lmbz.png","bz.png"});
                if (result.sim > 0.85f) {
                    for (int i = 1; i <= 3; ) {
                        switch (i) {
                            case 1:
                                mFairy.onTap(125, 165, 203, 187, "宝藏列表", 500);

                                result = mFairy.findPic("lmbz2.png");
                                if (result.sim > 0.85f) {
                                    switch (lmbzType) {
                                        case 3:
                                            if (other(color3)) {
                                                i = 0;
                                            }
                                            break;
                                        case 2:
                                            if (other(color3) == false) {

                                            } else if (other(color2)) {
                                                i = 0;
                                            }
                                            break;
                                        case 1:
                                            if (other(color3) == false) {

                                            } else if (other(color2) == false) {

                                            } else if (other(color1)) {
                                                i = 0;
                                            }
                                            break;
                                    }
                                    i++;
                                }
                                break;
                            case 2:

                                mFairy.onTap(335, 166, 422, 185, "我的宝藏", 500);

                                result = mFairy.findPic(504, 400, 710, 1162, "lmbz7.png");
                                mFairy.onTap(0.8f, result, "求助", 500);

                                result = mFairy.findPic(504, 400, 710, 1162, "baozang.png");
                                mFairy.onTap(0.8f, result, "领取", 500);

                                if (oneJudgeCount(3)) {
                                    i++;
                                }

                                break;
                            case 3:
                                mFairy.onTap(545, 164, 634, 185, "帮助列表", 500);

                                result = mFairy.findPic(516, 423, 710, 585, new String[]{"lmbz8.png", "lmbz9.png"});
                                mFairy.onTap(0.85f, result, "帮助", 500);

                                if (twoJudgeCount(3)) {
                                    i++;
                                }

                                break;

                        }
                    }
                    setTaskEnd();
                    gamePublicFuntion.close();
                    return;
                }
            }
        };
    }//联盟宝藏

    private TaskContent.Time lmkejiTime;
    private TaskContent.Time lmkelmbz;
    private TaskContent.Time yingxiongTime;
    private TaskContent.Time xinpianTime;
    private TaskContent.Time zhuangbeiTime;
    private TaskContent.Time shangrenTime;
    private TaskContent.Time suijiTime;
    private TaskContent.Time shouquTime;
    private TaskContent.Time zengTime;
    private TaskContent.Time rchyTime;
    private TaskContent.Time zhanlueTime;
    private int shangrenNum = 1;
    private int zengNum = 24;

    private int zonglan = 1;
    private TaskContent.Time zonglanTime;
    private boolean zonglanBool = true;

    //训练
    private int tk = -1;
    private int fj = -1;
    private int by = -1;
    private int zc = -1;
    private int cf = -1;
    private int jiasu = 0;

    //行军
    private int chazhao = 1;
    private boolean chazhaoBool = true;
    private int c1 = 0;
    private int c2 = 0;
    private int c3 = 0;
    private int c4 = 0;
    private int c5 = 0;
    private int c6 = 0;
    private int c7 = 0;
    private int c8 = 0;
    private int bian = 0;
    //其他
    private int yanjiu = -1;
    private boolean yanjiuBool = true;

    private boolean zhiliao = true;
    private boolean zljiasu = false;
    private boolean reset = true;

    private int use = 0;
    private boolean kaizhao = false;
    private int errZong = 0;

    public void infinite() throws Exception {
        new TaskContent(mFairy, "无限任务") {
            void create() throws Exception {
                errZong = 0;
                lmkejiTime = new Time();
                lmkelmbz = new Time();
                yingxiongTime = new Time();
                xinpianTime = new Time();
                zhuangbeiTime = new Time();
                shangrenTime = new Time();
                zonglanTime = new Time();
                suijiTime = new Time();
                shouquTime = new Time();
                zengTime = new Time();
                rchyTime = new Time();
                zhanlueTime = new Time();

                zonglanTime.setTime(System.currentTimeMillis());

                if (!AtFairyConfig.getOption("shangtime").equals("")) {/**商人查看时间*/
                    shangrenNum = Integer.parseInt(AtFairyConfig.getOption("shangtime"));
                }
                if (!AtFairyConfig.getOption("zytime").equals("")) {/**增益查看时间*/
                    zengNum = Integer.parseInt(AtFairyConfig.getOption("zytime"));
                }

                if (AtFairyConfig.getOption("daokaizhao").equals("1") ||
                        AtFairyConfig.getOption("jinkaizhao").equals("1")) {/**开罩*/
                    kaizhao = true;
                }
                /**
                 * 训练 */
                if (!AtFairyConfig.getOption("tk").equals("")) {
                    tk = Integer.parseInt(AtFairyConfig.getOption("tk"));
                }
                if (!AtFairyConfig.getOption("fj").equals("")) {
                    fj = Integer.parseInt(AtFairyConfig.getOption("fj"));
                }
                if (!AtFairyConfig.getOption("by").equals("")) {
                    by = Integer.parseInt(AtFairyConfig.getOption("by"));
                }
                if (!AtFairyConfig.getOption("zc").equals("")) {
                    zc = Integer.parseInt(AtFairyConfig.getOption("zc"));
                }
                if (!AtFairyConfig.getOption("cf").equals("")) {
                    cf = Integer.parseInt(AtFairyConfig.getOption("cf"));
                }
                if (!AtFairyConfig.getOption("jiasu").equals("")) {
                    jiasu = Integer.parseInt(AtFairyConfig.getOption("jiasu"));
                }

                /**
                 * 行军 */
                if (!AtFairyConfig.getOption("bian").equals("")) {
                    bian = Integer.parseInt(AtFairyConfig.getOption("bian"));
                }

                if (!AtFairyConfig.getOption("c1").equals("")) {
                    c1 = Integer.parseInt(AtFairyConfig.getOption("c1"));
                }

                if (!AtFairyConfig.getOption("c2").equals("")) {
                    c2 = Integer.parseInt(AtFairyConfig.getOption("c2"));
                }
                if (!AtFairyConfig.getOption("c3").equals("")) {
                    c3 = Integer.parseInt(AtFairyConfig.getOption("c3"));
                }
                if (!AtFairyConfig.getOption("c4").equals("")) {
                    c4 = Integer.parseInt(AtFairyConfig.getOption("c4"));
                }
                if (!AtFairyConfig.getOption("c5").equals("")) {
                    c5 = Integer.parseInt(AtFairyConfig.getOption("c5"));
                }

                if (!AtFairyConfig.getOption("c6").equals("")) {
                    c6 = Integer.parseInt(AtFairyConfig.getOption("c6"));
                }
                if (!AtFairyConfig.getOption("c7").equals("")) {
                    c7 = Integer.parseInt(AtFairyConfig.getOption("c7"));
                }
                if (!AtFairyConfig.getOption("c8").equals("")) {
                    c8 = Integer.parseInt(AtFairyConfig.getOption("c8"));
                }

                /**
                 * 其他*/
                if (!AtFairyConfig.getOption("yanjiu").equals("")) {
                    yanjiu = Integer.parseInt(AtFairyConfig.getOption("yanjiu"));
                }

                if (AtFairyConfig.getOption("zljiasu").equals("1")) {
                    zljiasu = true;
                }

                if (AtFairyConfig.getOption("qian").equals("1")) {/**签到*/
                    qian();
                }
                if (AtFairyConfig.getOption("gui").equals("1")) {/**贵族领取*/
                    gui();
                }
                if (AtFairyConfig.getOption("email").equals("1")) {/**邮件领取*/
                    email();
                }

                if (AtFairyConfig.getOption("yingxiong").equals("1")) {/**英雄升星*/
                    yingxiong();
                }
                if (AtFairyConfig.getOption("cheng").equals("1")) {/**成就领取*/
                    cheng();
                }
                if (AtFairyConfig.getOption("haoyou").equals("1")) {/**好友赠送*/
                    haoyou();
                }
                if (AtFairyConfig.getOption("lmliwu").equals("1")) {/**联盟礼物*/
                    lmliwu();
                }
                if (AtFairyConfig.getOption("lmqian").equals("1")) {/**联盟签到*/
                    lmqian();
                }
                if (!AtFairyConfig.getOption("jxc").equals("")) {
                    jxc();
                }
            }

            @Override
            void init() throws Exception {
                super.init();
                oneJudgeCount = 0;
                twoJudgeCount = 0;
                threeJudgeCount = 0;
            }

            @Override
            void inOperation() throws Exception {
                int hour = mFairy.dateHour();
                if (hour == 24 || hour == 0) {
                    if (reset) {
                        mFairy.killUserGame();
                        create();
                        setTaskName(0);
                        reset = false;
                        chazhaoBool = true;
                        return;
                    }
                } else {
                    reset = true;
                }

                if (timeTask()) {
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(519, 825, 706, 1032, "bangzhu.png");
                mFairy.onTap(0.85f, result, "联盟帮助", 1000);

                result = mFairy.findPic("ok3.png");
                mFairy.onTap(0.85f, result, "确定", 1000);

                result = mFairy.findPic("chuzheng7.png");
                mFairy.onTap(0.85f, result, 189, 702, 230, 723, "撞矿取消", 1000);

                result = mFairy.findPic(57, 485, 663, 687, "taru.png");
                mFairy.onTap(0.85f, result, 181, 701, 228, 722, "战争地区-取消", 1000);

                result = mFairy.findPic(2, 387, 405, 933, "kaizhao.png");
                if (result.sim > 0.75f) {
                    if (kaizhao) {
                        kaizhao();
                    }
                }
                /*result = mFairy.findPic(2,387,405,933, "huo.png");
                if (result.sim > 0.75f) {
                   mFairy.onTap(0.75f,result,"火",1000);

                }*/
            }

            boolean timeTask() throws Exception {

                Boolean bools = false;

                if (AtFairyConfig.getOption("rc").equals("1") && rchyTime.timeJudge(3600000)) {/**日常活跃领取*/
                    rchy();
                    bools = true;
                }

                if (AtFairyConfig.getOption("lmkeji").equals("1") && lmkejiTime.timeJudge(3600000)) {/**联盟科技*/
                    lmkeji();
                    bools = true;
                }
                if (AtFairyConfig.getOption("lmbao").equals("1") && lmkelmbz.timeJudge(3600000)) {  /**联盟宝藏*/

                    if (!AtFairyConfig.getOption("lmbz").equals("")) {
                        lmbzType = Integer.parseInt(AtFairyConfig.getOption("lmbz"));
                    }
                    lmbz();
                    bools = true;
                }
                if (AtFairyConfig.getOption("zhaomu").equals("1") && yingxiongTime.timeJudge(600000) && (zhaomu < 6)) {/**英雄招募*/
                    zhaomu();
                    zhaomu++;
                    bools = true;
                }
                if (AtFairyConfig.getOption("xinpian").equals("1") && xinpianTime.timeJudge(600000) && (xinpian < 6)) {/**芯片研究*/
                    xinpian();
                    xinpian++;
                    bools = true;
                }

                if (AtFairyConfig.getOption("zhuangbei").equals("1") && zhuangbeiTime.timeJudge(600000) && (zhuangbei < 4)) {/**大招装备*/
                    zhuangbei();
                    zhuangbei++;
                    bools = true;
                }
                if (AtFairyConfig.getOption("2945").equals("1") && shangrenTime.timeJudge(shangrenNum * 3600000)) {/**特会商人*/
                    shangren();
                    bools = true;
                }
                if (AtFairyConfig.getOption("suiji").equals("1") && suijiTime.timeJudge(7200000)) {/**随机建设*/
                    suiji();
                    bools = true;
                }
                if (AtFairyConfig.getOption("shouqu").equals("1") && shouquTime.timeJudge(18000000)) {/**收取资源*/
                    shouqu();
                    bools = true;
                }
                if (AtFairyConfig.getOption("3065").equals("1") && zengTime.timeJudge(zengNum * 3600000)) {/**增益设置*/
                    zengchan();
                    bools = true;
                }

                if (AtFairyConfig.getOption("3111").equals("1") && zhanlueTime.timeJudge(7200000)) {/**战略组*/
                    zhanlue();
                    bools = true;
                }

                return bools;
            }//

            /**
             * 训练
             */
            boolean redengji(int x) throws Exception {
                result = mFairy.findPic(445, 1140, 609, 1260, "xunlian4.png");
                if (result.sim > 0.85f && x > 0) {
                    int num = 0;
                    for (int i = 1; i <= 11; i++) {
                        result = mFairy.findPic("xlde" + i + ".png");
                        if (result.sim > 0.92f) {
                            num = i;
                            break;
                        }
                    }

                    LtLog.e(mFairy.getLineInfo("当前的等级为：" + num));
                    if (num == 0) {
                        setTaskName(0);
                        return false;
                    } else if (x > num) {
                        for (int j = 0; j < (x - num); j++) {
                            mFairy.onTap(662, 869, 684, 920, "右侧", 500);
                        }
                    } else if (x < num) {
                        for (int j = 0; j < (num - x); j++) {
                            mFairy.onTap(35, 872, 54, 921, "左侧", 500);
                        }
                    }
                }

                result = mFairy.findPic(174, 848, 548, 1273, new String[]{"xunlian5.png", "xunlian6.png"});
                if (result.sim > 0.85f) {
                    setTaskName(0);
                    return true;
                } else {
                    result = mFairy.findPic(445, 1140, 609, 1260, "xunlian4.png");
                    mFairy.onTap(0.85f, result, "训练", 1000);
                }


                result = mFairy.findPic("xunlian8.png");
                mFairy.onTap(0.85f, result, "道具加速", 1500);
                return false;
            }//等级

            void xunlian() throws Exception {
                result = mFairy.findPic("tk.png");
                if (result.sim > 0.85f && tk != -1) {
                    oneJudgeCount = 0;
                    if (tk > -1) {
                        if (redengji(tk)) {
                            tk = -1;
                        }
                    }
                }

                result = mFairy.findPic("fj.png");
                if (result.sim > 0.85f && fj != -1) {
                    oneJudgeCount = 0;
                    if (fj > -1) {
                        if (redengji(fj)) {
                            fj = -1;
                        }
                    }
                    return;
                }

                result = mFairy.findPic("by.png");
                if (result.sim > 0.85f && by != -1) {
                    oneJudgeCount = 0;
                    if (by > -1) {
                        if (redengji(by)) {
                            by = -1;
                        }
                    }
                    return;
                }

                result = mFairy.findPic("zc.png");
                if (result.sim > 0.85f && zc != -1) {
                    oneJudgeCount = 0;
                    if (zc > -1) {
                        if (redengji(zc)) {
                            zc = -1;
                        }
                    }
                    return;
                }

                result = mFairy.findPic("cf.png");
                if (result.sim > 0.85f && cf != -1) {
                    LtLog.e(mFairy.getLineInfo("建造工厂"));
                    oneJudgeCount = 0;
                    if (cf > 0) {

                        for (int i = 0; i < 16; i++) {
                            mFairy.onTap(35, 872, 54, 921, "左侧", 500);
                        }
                        Thread.sleep(500);

                        for (int i = 0; i < (cf - 1); i++) {
                            mFairy.onTap(662, 869, 684, 920, "右侧", 500);
                        }
                    }

                    Thread.sleep(1000);

                    result = mFairy.findPic(174, 848, 548, 1273, new String[]{"xunlian5.png", "xunlian6.png"});
                    if (result.sim > 0.85f) {
                        setTaskName(0);
                        cf = -1;
                    } else {
                        result = mFairy.findPic(445, 1140, 609, 1260, "xunlian7.png");
                        mFairy.onTap(0.85f, result, "建造", 2000);

                        result = mFairy.findPic(445, 1140, 609, 1260, "xunlian7.png");
                        if (result.sim > 0.85f) {
                            setTaskName(0);
                            zonglan = 2;
                        }
                    }
                    return;
                }
            }//

            /**
             * 行军
             */
            void xingjun() throws Exception {
                result = mFairy.findPic("chazhao.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("开始查找: " + chazhao));
                    switch (chazhao) {
                        case 1:
                            if (c1 != 0) {
                                chazhao("c1.png", c1);
                                chuzheng();
                                setTaskName(0);
                            }
                            chazhao++;
                            break;
                        case 2:
                            if (c2 != 0) {
                                chazhao("c2.png", c2);
                                chuzheng();
                                setTaskName(0);
                            }
                            chazhao++;
                            break;
                        case 3:
                            if (c3 != 0) {
                                chazhao("c3.png", c3);
                                chuzheng();
                                setTaskName(0);
                            }
                            chazhao++;
                            break;
                        case 4:
                            if (c4 != 0) {
                                chazhao("c4.png", c4);
                                chuzheng();
                                setTaskName(0);
                            }
                            chazhao++;
                            break;
                        case 5:
                            if (c5 != 0) {
                                chazhao("c5.png", c5);
                                chuzheng();
                                setTaskName(0);
                            }
                            chazhao++;
                            break;
                        case 6:
                            if (c6 != 0) {
                                chazhao("c6.png", c6);
                                chuzheng();
                                setTaskName(0);
                            }
                            chazhao++;
                            break;
                        case 7:
                            if (c7 != 0) {
                                chazhao("c7.png", c7);
                                chuzheng();
                                setTaskName(0);
                            }
                            chazhao++;
                            break;
                        case 8:
                            if (c8 != 0) {
                                chazhao("c8.png", c8);
                                chuzheng();
                                setTaskName(0);
                            }
                            chazhao = 1;
                            chazhaoBool = false;
                            break;
                    }


                }
            }//行军

            void chazhao(String img, int c) throws Exception {
                int i = 0;
                while (mFairy.condit()) {

                    result = mFairy.findPic(7, 1023, 712, 1117, img);
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, result.x + 40, result.y - 50, result.x + 50, result.y - 30, "找到：" + img, 500);
                        break;
                    }

                    if (i == 2) {
                        mFairy.ranSwipe(177, 978, 659, 1008, 1, 500,  (long)2000);
                    } else if (i == 4) {
                        mFairy.ranSwipe(177, 978, 659, 1008, 3, 500,  (long)2000);
                    } else if (i > 5) {
                        return;
                    }
                    i++;
                }

                if (chazhaoBool) {

                    Thread.sleep(1000);

                    result = mFairy.findPic(50, 1158, 507, 1236, "chazhao2.png");
                    if (result.sim > 0.85f) {
                        mFairy.ranSwipe(1, result.y, result.x, result.y + 2, 3, 800,  (long)500);
                    }

                    for (int q = 0; q < (c - 1); q++) {
                        mFairy.onTap(432, 1185, 453, 1202, "", 100);
                    }
                }

                for (int h = 0; h < 3; h++) {


                    result = mFairy.findPic("chazhao3.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "查找>>>", 1000);
                        if (mapCount(0.8f, 5, 181, 243, 340, new String[]{"chazhao1.png","nn60.png"})) {
                            mFairy.onTap(59, 1187, 80, 1203, "未发现资源 等级降低-1", 2000);
                        } else {
                            break;
                        }
                    }
                }
            }//查找

            void chuzheng() throws Exception {
                int err = 0;
                while (mFairy.condit()) {
                    result = mFairy.findPic(200, 122, 500, 987, "faxian.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(result.x, result.y + 100, result.x + 3, result.y + 110, "发现野怪", 1000);
                        continue;
                    }

                    result = mFairy.findPic(353, 200, 679, 809, "chuzheng5.png");
                    if (result.sim > 0.75f) {
                        mFairy.onTap(0.75f, result, "发现采集", 1000);
                        continue;
                    }

                    result = mFairy.findPic(353, 354, 679, 809, "chuzheng6.png");
                    if (result.sim > 0.75f) {
                        mFairy.onTap(0.75f, result, "发现进攻", 1000);
                        continue;
                    }

                    result = mFairy.findPic("chuzheng1.png");
                    mFairy.onTap(0.85f, result, "快速搜索", 500);

                    result = mFairy.findPic("chuzheng2.png");
                    mFairy.onTap(0.85f, result, "进攻", 500);

                    result = mFairy.findPic("chuzheng3.png");
                    if (result.sim > 0.8f) {

                        if (chazhao == 8) {
                            result = mFairy.findPic("jidi.png");
                            if (result.sim > 0.9f) {
                                c8 = 0;
                                gamePublicFuntion.close();
                                return;
                            }
                        }

                        result = mFairy.findPic("meiyou.png");
                        if (result.sim > 0.85f) {
                            setTaskName(0);
                            zonglan = 3;
                            return;
                        }

                        switch (bian) {
                            case 1:
                                mFairy.onTap(330, 132, 361, 154, "编队1", 1000);
                                break;
                            case 2:
                                mFairy.onTap(419, 132, 444, 153, "编队2", 1000);
                                break;
                            case 3:
                                mFairy.onTap(513, 133, 540, 150, "编队3", 1000);
                                break;
                            case 4:
                                mFairy.onTap(600, 130, 624, 148, "编队4", 1000);
                                break;
                        }

                        result = mFairy.findPic("chuzheng4.png");
                        mFairy.onTap(0.85f, result, "快速选择", 500);

                        mFairy.onTap(529, 1193, 566, 1215, "出征", 1000);

                        return;
                    }
                    err++;
                    if (err > 8) {
                        return;
                    }
                }
            }//出征

            /**
             * 其他 */
            void yanjiu() throws Exception {
                result = mFairy.findPic("yanjiu.png");
                if (result.sim > 0.85f) {
                    switch (yanjiu) {
                        case 0:
                            result = mFairy.findPic(68, 1161, 691, 1273, "yanjiu2.png");
                            mFairy.onTap(0.85f, result, "推荐研究", 500);
                            break;
                        case 1:
                            mFairy.onTap(162, 290, 202, 327, "军事", 500);
                            break;
                        case 2:
                            mFairy.onTap(504, 283, 535, 315, "资源", 500);
                            break;
                        case 3:
                            mFairy.onTap(75, 498, 113, 536, "发展", 500);
                            break;
                        case 4:
                            mFairy.onTap(601, 501, 640, 532, "城防", 500);
                            break;
                    }
                }

                result = mFairy.findPic("cf.png");
                if (result.sim > 0.85f) {
                    return;
                }

                result = mFairy.findPic(new String[]{"y1.png", "y2.png", "y3.png", "y4.png"});
                if (result.sim > 0.85f) {
                    int x = 0;
                    int y = 0;
                    while (mFairy.condit()) {
                        FindResult yan = mFairy.findPic(5 + (x * 120), 125 + (y * 230), 125 + (x * 120), 355 + (y * 230), "yanjiu1.png");
                        if (yan.sim > 0.75f) {
                            result = mFairy.findPic(yan.x + 40, yan.y - 35, yan.x + 139, yan.y + 35, new String[]{"yanjiu3.png", "yanjiu4.png"});
                            if (result.sim < 0.75f) {
                                mFairy.onTap(yan.x + 72, yan.y - 35, yan.x + 100, yan.y - 30, "点", 1000);
                                break;
                            }
                        }
                        x++;
                        if (x > 4) {
                            x = 0;
                            y++;
                            if (y > 3) {
                                mFairy.ranSwipe(347, 300, 393, 1078, 2, 1000,  (long)1000);
                                y = 0;
                                x = 0;
                            }
                        }
                    }
                }

                result = mFairy.findPic(380, 581, 642, 1242, "yanjiu5.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "研究", 1500);

                    result = mFairy.findPic(1, 1, 719, 1216, "qiuzhu.png");
                    mFairy.onTap(0.8f, result, "求助", 100);

                    setTaskName(0);
                    yanjiuBool = false;
                    return;
                }
            }//研究

            void content_01() throws Exception {
                timeCount(15, 0);

                result = mFairy.findPic(9, 1184, 151, 1276, "chu.png");
                mFairy.onTap(0.8f, result, "出城", 1000);

                result = mFairy.findPic("buji1.png");
                mFairy.onTap(0.8f, result, "补给箱领取", 500);

                if (zonglan == 1) {
                    result = mFairy.findPic(217, 588, 495, 797, "xunlian11.png");
                    if (result.sim > 0.85f) {
                        jiasu = 0;
                        setTaskName(0);
                        return;
                    }

                    result = mFairy.findPic("xunlian9.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "一键使用", 1000);
                        use++;
                        if (use > 2) {
                            jiasu = 0;
                            setTaskName(0);
                            return;
                        }
                    } else {
                        use = 0;
                    }

                    result = mFairy.findPic("xunlian10.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(425, 1028, 440, 1043, "今日不再提示", 500);
                        mFairy.onTap(483, 1079, 520, 1099, "确定", 1000);
                    }

                    result = mFairy.findPic("buzu1.png");
                    if (result.sim > 0.85f) {
                        setTaskName(0);
                        zonglan = 2;
                        return;
                    }
                }

                result = mFairy.findPic("zhiliao2.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic("zhiliao5.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "治疗", 500);
                        gamePublicFuntion.close();
                        return;
                    }

                    if (zljiasu) {
                        result = mFairy.findPic("zhiliao3.png");
                        mFairy.onTap(0.85f, result, "道具加速", 500);
                    }
                }

                if (zljiasu) {
                    result = mFairy.findPic("zhiliao4.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, 546, 374, 631, 389, "一键使用", 1000);
                        use++;
                        if (use > 3) {
                            zljiasu = false;
                            setTaskName(0);
                            return;
                        }
                    } else {
                        use = 0;
                    }

                    result = mFairy.findPic("jiasu1.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(425, 1029, 437, 1044, "今天不在提示>> 医疗加速", 300);
                        mFairy.onTap(484, 1082, 520, 1102, "确定", 1000);
                        gamePublicFuntion.close();
                    }
                }

                result = mFairy.findPic(559, 497, 719, 768, "zonglan2.png");
                mFairy.onTap(0.85f, result, "坐标", 1000);


                result = mFairy.findPic(515, 11, 677, 153, "map.png");
                if (result.sim > 0.75f) {
                    if (zonglanBool) {
                        result = mFairy.findPic("chazhao.png");
                        if (result.sim < 0.8f) {
                            result = mFairy.findPic(551, 468, 716, 770, "zonglan.png");
                            if (result.sim > 0.8f) {
                                errZong = 0;
                                mFairy.onTap(0.8f, result, "总览", 500);
                            } else {
                                errZong++;
                                if (errZong > 15) {
                                    setTaskEnd();
                                    mFairy.finish(AtFairyConfig.getTaskID(), 16702);
                                    return;
                                }
                            }
                        }
                    } else {
                        err = 0;

                        result = mFairy.findPic(214,913,700,1172, "hong.png");
                        mFairy.onTap(0.85f,result,"红包",1000);

                        result = mFairy.findPic(144,540,597,972, "hong1.png");
                        mFairy.onTap(0.85f,result,"红包-开",1000);

                        result = mFairy.findPic(216,789,560,1001, "hong2.png");
                            mFairy.onTap(0.85f,result,580,308,607,333,"红包关闭",1000);

                        if (zonglanTime.timeJudge(300000)) {
                            yanjiuBool = true;
                            zhiliao = true;
                            zonglanBool = true;
                            return;
                        }
                    }
                }

                result = mFairy.findPic(177, 509, 320, 651, "zonglan1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    errZong = 0;
                    LtLog.e("当前分类: " + zonglan);
                    switch (zonglan) {
                        case 1:
                            result = mFairy.findPic("xunlian.png");
                            if (result.sim > 0.8f) {

                                result = mFairy.findPic(288, 403, 710, 848, "xunlian2.png");
                                if (result.sim > 0.8f) {
                                    oneJudgeCount = 0;
                                    mFairy.onTap(0.8f, result, "可收取", 500);
                                    return;
                                }

                                if (tk != -1) {
                                    result = mFairy.findPic(297, 433, 499, 515, "xunlian1.png");
                                    if (result.sim > 0.75f) {
                                        mFairy.onTap(0.75f, result, "可训练", 500);
                                        return;
                                    } else {
                                        if (jiasu == 1) {
                                            mFairy.onTap(370, 414, 421, 440, "勾选了加速 :" + jiasu, 500);
                                            return;
                                        }
                                    }
                                }

                                if (fj != -1) {
                                    result = mFairy.findPic(508, 434, 708, 509, "xunlian1.png");
                                    if (result.sim > 0.75f) {
                                        mFairy.onTap(0.75f, result, "可训练", 500);
                                        return;
                                    } else {
                                        if (jiasu == 2) {
                                            mFairy.onTap(579, 419, 632, 438, "勾选了加速 :" + jiasu, 500);
                                            return;
                                        }
                                    }
                                }
                                if (by != -1) {
                                    result = mFairy.findPic(294, 602, 496, 664, "xunlian1.png");
                                    if (result.sim > 0.75f) {
                                        mFairy.onTap(0.75f, result, "可训练", 500);
                                        return;
                                    } else {
                                        if (jiasu == 3) {
                                            mFairy.onTap(368, 574, 443, 615, "勾选了加速 :" + jiasu, 500);
                                            return;
                                        }
                                    }
                                }
                                if (zc != -1) {
                                    result = mFairy.findPic(510, 603, 706, 667, "xunlian1.png");
                                    if (result.sim > 0.75f) {
                                        mFairy.onTap(0.75f, result, "可训练", 500);
                                        return;
                                    } else {
                                        if (jiasu == 4) {
                                            mFairy.onTap(585, 579, 644, 614, "勾选了加速 :" + jiasu, 500);
                                            return;
                                        }
                                    }
                                }
                                if (cf != -1) {
                                    result = mFairy.findPic(295, 768, 489, 819, "xunlian3.png");
                                    if (result.sim > 0.75f) {
                                        mFairy.onTap(0.75f, result, "可训练", 500);
                                        return;
                                    } else {
                                        if (jiasu == 5) {
                                            mFairy.onTap(374, 732, 412, 753, "勾选了加速 :" + jiasu, 500);
                                            return;
                                        }
                                    }
                                }
                                if (oneJudgeCount(3)) {
                                    zonglan = 2;
                                    oneJudgeCount = 0;
                                    return;
                                }
                            } else {
                                mFairy.onTap(330, 285, 360, 319, "训练", 500);
                            }
                            break;
                        case 2:
                            result = mFairy.findPic("xingjun.png");
                            if (result.sim > 0.85f) {

                                if (c1 == 0 && c2 == 0 && c3 == 0 && c4 == 0 && c5 == 0 && c6 == 0 && c7 == 0 && c8 == 0) {
                                    zonglan = 3;
                                    return;
                                }
                                result = mFairy.findPic(288, 428, 705, 859, "xingjun1.png");
                                if (result.sim > 0.85f) {
                                    twoJudgeCount = 0;
                                    mFairy.onTap(0.85f, result, "空闲", 500);
                                    return;
                                }

                                if (twoJudgeCount(3)) {
                                    zonglan = 3;
                                    twoJudgeCount = 0;
                                }

                            } else {
                                mFairy.onTap(432, 288, 460, 317, "行军", 1000);
                            }
                            break;
                        case 3:
                            result = mFairy.findPic("qita.png");
                            if (result.sim > 0.8f) {

                                result = mFairy.findPic(304, 436, 497, 511, "yanjiu6.png");
                                if (result.sim > 0.8f) {
                                    threeJudgeCount = 0;
                                    mFairy.onTap(0.8f, result, "免费加速", 1000);
                                    yanjiuBool = true;
                                }

                                result = mFairy.findPic(510, 436, 699, 517, "xunlian2.png");
                                if (result.sim > 0.8f) {
                                    threeJudgeCount = 0;
                                    mFairy.onTap(0.8f, result, "可收取", 500);
                                    return;
                                }

                                if (yanjiu != -1) {
                                    result = mFairy.findPic(304, 436, 497, 511, "qita1.png");
                                    if (result.sim > 0.8f && yanjiuBool) {
                                        mFairy.onTap(0.8f, result, "前往研究", 500);
                                        return;
                                    }
                                }

                                if (zhiliao) {
                                    if (AtFairyConfig.getOption("zhiliao").equals("1")) {
                                        result = mFairy.findPic(510, 436, 699, 517, "zhiliao.png");
                                        mFairy.onTap(0.8f, result, "治疗修复", 500);
                                        zhiliao = false;
                                        return;
                                    }
                                }

                                if (zljiasu) {
                                    result = mFairy.findPic(510, 436, 699, 517, "zhiliao1.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(0.8f, result, "治疗加速", 500);
                                        return;
                                    }
                                }

                                result = mFairy.findPic(331, 495, 454, 631, "buji2.png");
                                if (result.sim > 0.8f) {
                                    mFairy.onTap(0.8f, result, "补给箱", 500);
                                    return;
                                }

                                if (threeJudgeCount(5)) {
                                    zonglan = 1;
                                    threeJudgeCount = 0;
                                    zonglanBool = false;
                                    setTaskName(0);
                                    return;
                                }

                            } else {
                                mFairy.onTap(539, 289, 572, 322, "其他", 500);
                            }
                            break;
                    }
                }
                xunlian();
                xingjun();
                yanjiu();
            }
        };

    }//无限任务

    private int nn = 1;
    private TaskContent.Time sjTime;
    private TaskContent.Time zyTime;
    private boolean tishi = false;

    public void nn() throws Exception {
        new TaskContent(mFairy, "一键10级") {
            @Override
            void create() throws Exception {
                sjTime = new Time();
                zyTime = new Time();
                sjTime.setTime(2);
                gamePublicFuntion.close();
                setTaskName(0);
            }

            @Override
            void inOperation() throws Exception {
                result = mFairy.findPic("guan.png");
                mFairy.onTap(0.85f, result, "guan", 500);

                result = mFairy.findPic(530, 995, 714, 1113, "nn20.png");
                mFairy.onTap(0.85f, result, "点击继续", 500);

                result = mFairy.findPic("nn1.png");
                mFairy.onTap(0.85f, result, "跳过", 500);

                result = mFairy.findPic(1, 1, 719, 1279, "nn2.png");
                mFairy.onTap(0.8f, result, result.x - 50, result.y + 30, result.x - 30, result.y + 50, "指引", 500);

                result = mFairy.findPic("nn49.png");
                mFairy.onTap(0.85f, result, 649, 140, 675, 158, "关闭采集说明", 1000);
            }


            @Override
            void init() throws Exception {
                for (int i = 0; i < 3; i++) {


                    gamePublicFuntion.close();

                    result = mFairy.findPic(6, 1009, 108, 1141, new String[]{"nn5.png", "juqing2.png"});
                    if (result.sim > 0.75f) {
                        mFairy.onTap(0.75f, result, "剧情", 500);
                        break;
                    }
                }
                oneJudgeCount = 0;
                setTaskName(1);
            }

            @Override
            void content_01() throws Exception {
                timeCount(8, 0);

                result = mFairy.findPic("nn24.png");
                mFairy.onTap(0.85f, result, "取消开启第二列队", 500);

                result = mFairy.findPic("nn22.png");
                mFairy.onTap(0.85f, result, "进攻", 1000);

                result = mFairy.findPic("nn58.png");
                mFairy.onTap(0.85f, result, "解锁", 1000);

                result = mFairy.findPic("nn48.png");
                mFairy.onTap(0.85f, result, 32, 1071, 73, 1097, "解锁日常任务", 1000);

                result = mFairy.findPic(413, 1088, 712, 1274, "nn23.png");
                mFairy.onTap(0.85f, result, "出征", 500);

                result = mFairy.findPic("nn21.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "查找", 1500);

                    result = mFairy.findPic(230, 176, 454, 826, "nn51.png");
                    LtLog.e("野怪" + result.sim);
                    if (result.sim > 0.85f) {
                        mFairy.onTap(result.x, result.y - 35, result.x + 2, result.y - 28, "发现野怪", 1000);
                    }
                }

                result = mFairy.findPic("zhaomu5.png");
                mFairy.onTap(0.85f, result, "确定", 500);

                result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                mFairy.onTap(0.8f, result, "回城", 1000);

                result = mFairy.findPic(216, 877, 517, 1042, "nn14.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "确定", 500);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(619, 1147, 706, 1274, new String[]{"nn3.png", "nn17.png"});
                mFairy.onTap(0.85f, result, "对话", 500);

                result = mFairy.findPic("nn19.png");
                mFairy.onTap(0.85f, result, "训练", 500);

                result = mFairy.findPic("nn4.png");
                mFairy.onTap(0.8f, result, 341, 724, 379, 784, "扫描指纹", 500);

                result = mFairy.findPic("nn15.png");
                mFairy.onTap(0.85f, result, "研究", 500);

                result = mFairy.findPic("nn16.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "研究", 500);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(232,59,482,359,"juqing.png");
                if (result.sim > 0.85f) {

                    result = mFairy.findPic("nn54.png");
                    if (result.sim > 0.85f) {
                        setTaskName(2);
                        gamePublicFuntion.init();
                        return;
                    }

                    err = 0;
                    result = mFairy.findPic(145,628,577,1235,"nn56.png");
                    mFairy.onTap(0.85f, result, "全部领取", 500);

                    for (int i = 0; i < 3; i++) {


                        result = mFairy.findPic(496, 341, 683, 846, "nn13.png");
                        mFairy.onTap(0.85f, result, "剧情 领取", 500);
                    }
                    result = mFairy.findPic(506,340,676,592, "juqing1.png");
                    mFairy.onTap(0.8f, result, "剧情 前往", 1000);

                    result = mFairy.findPic(510,591,682,980, "juqing1.png");
                    mFairy.onTap(0.8f, result, "剧情 前往", 1000);
                }


                result = mFairy.findPic(134, 432, 613, 910, "nn8.png");
                mFairy.onTap(0.85f, result, "建筑 升级", 500);


                result = mFairy.findPic(1, 1, 719, 1279, "nn2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, result.x - 50, result.y + 30, result.x - 30, result.y + 50, "指引", 500);
                } else {
                    result = mFairy.findPic("nn6.png");
                    if (result.sim > 0.85f) {
                        LtLog.e("建筑界面");
                        result = mFairy.findPic(516, 764, 695, 1102, "nn59.png");
                        mFairy.onTap(0.78f, result, "建筑 前往", 500);
                    }

                    result = mFairy.findPic(new String[]{"nn10.png", "nn12.png"});
                    if (result.sim > 0.85f) {

                        result = mFairy.findPic(516, 764, 695, 1102, "nn18.png");
                        mFairy.onTap(0.78f, result, "建筑 免费加速", 500);

                        result = mFairy.findPic(516, 764, 695, 1102, "nn7.png");
                        mFairy.onTap(0.78f, result, "建筑 前往", 500);


                        result = mFairy.findPic(116, 1066, 284, 1175, "nn9.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(162, 1174, 227, 1195, "建筑 升级", 500);
                        } else {
                            mFairy.onTap(498, 1164, 583, 1199, "建筑 升级", 500);
                        }
                        if (oneJudgeCount(2)) {
                            setTaskName(0);
                            return;
                        }
                    }
                }
            }

            @Override
            void content_02() throws Exception {
                if (timeCount(10, 3)) {
                    gamePublicFuntion.init();
                    return;
                }

                result = mFairy.findPic("nn26.png");
                mFairy.onTap(0.85f, result, "wupin", 500);

                result = mFairy.findPic("nn27.png");
                if (result.sim > 0.85f) {
                    for (int i = 1; i <= 2; i++) {

                        switch (i) {
                            case 1:
                                mFairy.onTap(202, 325, 256, 340, "资源", 500);
                                mFairy.onTap(316, 1219, 414, 1237, "一键使用", 1000);

                                result = mFairy.findPic("nn28.png");
                                mFairy.onTap(0.85f, result, "确定使用", 500);

                                break;
                            case 2:
                                mFairy.onTap(627, 321, 677, 341, "其他", 500);

                                for (int j = 0; j < 5; j++) {


                                    gamePublicFuntion.click();

                                    result = mFairy.findPic(new String[]{"nn34.png", "buzu.png"});
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(334, 703, 382, 723, "工厂等级不足,确定", 500);
                                        gamePublicFuntion.init();
                                        setTaskName(3);
                                        oneSecond = 0;
                                        return;
                                    }

                                    result = mFairy.findPic(23, 654, 713, 1264, "nn30.png");
                                    if (result.sim > 0.85f) {
                                        j = 0;
                                        err = 0;
                                        mFairy.onTap(0.85f, result, "使用", 500);
                                    } else {
                                        result = mFairy.findPic(8, 366, 705, 1112, "nn33.png");
                                        if (result.sim > 0.75f) {
                                            j = 0;
                                            mFairy.onTap(0.75f, result, result.x, result.y - 50, result.x + 5, result.y - 40, "坦克传送", 500);
                                        } else {
                                            result = mFairy.findPic(8, 366, 705, 1112, "nn29.png");
                                            if (result.sim > 0.8f) {
                                                j = 0;
                                                err = 0;
                                                mFairy.onTap(0.8f, result, "发现新手礼包", 500);
                                            }
                                        }
                                    }

                                    result = mFairy.findPic(12, 519, 698, 1041, "nn31.png");
                                    if (result.sim > 0.85f) {
                                        j = 0;
                                        mFairy.touchDown(1, result.x + 10, result.y + 10);
                                        mFairy.touchMove(1, 700, result.y, 1500);
                                        mFairy.touchUp(1);

                                        result = mFairy.findPic(192, 589, 536, 1105, "nn32.png");
                                        mFairy.onTap(0.85f, result, "使用", 500);
                                    }
                                }

                                setTaskName(3);
                                gamePublicFuntion.init();
                                break;
                        }
                    }
                }
            }

            boolean shengji() throws Exception {
                gamePublicFuntion.init();
                while (mFairy.condit()) {

                    if (threeJudgeCount(10)) {
                        return false;
                    }

                    result = mFairy.findPic(1, 1, 719, 1279, "nn2.png");
                    mFairy.onTap(0.8f, result, result.x - 50, result.y + 30, result.x - 30, result.y + 50, "指引", 500);

                    result = mFairy.findPic(9, 1184, 151, 1276, "hui.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "回城", 500);
                        threeJudgeCount = 0;
                        judgeOneSecond = 1;
                    }

                    result = mFairy.findPic(96, 494, 652, 932, "nn44.png");
                    if (result.sim > 0.85f) {
                        return false;
                    }

                    result = mFairy.findPic("nn47.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "左侧免费", 500);
                        judgeOneSecond = 0;
                    }

                    result = mFairy.findPic("nn12.png");
                    if (result.sim > 0.85f) {
                        threeJudgeCount = 0;

                        result = mFairy.findPic(516, 764, 695, 1102, "nn18.png");
                        mFairy.onTap(0.85f, result, "建筑 免费加速", 500);

                        result = mFairy.findPic(116, 1066, 284, 1175, "nn9.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(162, 1174, 227, 1195, "建筑 升级", 500);
                        } else {
                            mFairy.onTap(498, 1164, 583, 1199, "建筑 升级", 500);
                        }
                    }

                    FindResult resultSj = mFairy.findPic(450, 1097, 627, 1257, "nn42.png");
                    if (resultSj.sim > 0.85f) {
                        threeJudgeCount = 0;

                        result = mFairy.findPic(116, 1066, 284, 1175, "nn9.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, 162, 1174, 227, 1195, "免费升级建筑", 500);
                            continue;
                        }

                        result = mFairy.findPic(516, 764, 695, 1102, "nn45.png");
                        if (result.sim > 0.85f) {
                            return false;
                        }

                        result = mFairy.findPic(516, 764, 695, 1102, "nn18.png");
                        mFairy.onTap(0.85f, result, "建筑 免费加速", 500);

                        result = mFairy.findPic(516, 764, 695, 1102, "nn7.png");
                        if (result.sim > 0.75f) {
                            mFairy.onTap(0.75f, result, "建筑 前往", 1500);
                            continue;
                        }

                        result = mFairy.findPic("nn43.png");
                        if (result.sim > 0.85f) {

                            mFairy.onTap(0.85f, resultSj, "升级", 2000);

                            result = mFairy.findPic("nn50.png");
                            mFairy.onTap(0.85f, result, 496, 670, 534, 687, "确定升级到8级", 500);

                            return false;
                        } else {
                            mFairy.onTap(0.85f, resultSj, "升级", 500);
                            gamePublicFuntion.init();
                            judgeOneSecond = 0;
                        }
                    }

                    result = mFairy.findPic("buji1.png");
                    mFairy.onTap(0.85f, result, "领取", 500);

                    result = mFairy.findPic(227, 602, 391, 792, "nn41.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "升级", 500);
                        threeJudgeCount = 0;
                    }

                    result = mFairy.findPic(500, 17, 678, 149, "main.png");
                    if (result.sim > 0.75f) {
                        result = mFairy.findPic(257, 428, 468, 616, "buji.png");
                        if (result.sim > 0.85f) {
                            judgeOneSecond = 0;
                            threeJudgeCount = 0;
                            mFairy.onTap(0.85f, result, "补给箱", 500);
                            continue;
                        }

                        result = mFairy.findPic(305, 337, 498, 525, "nn40.png");
                        mFairy.onTap(0.85f, result, "点击工厂", 1000);

                        if (judgeOneSecond()) {
                            result = mFairy.findPic(9, 1184, 151, 1276, "chu.png");
                            mFairy.onTap(0.8f, result, "出城", 3000);
                        }
                    }
                }
                return false;
            }//升级

            @Override
            void content_03() throws Exception {
                if (timeCount(10, 3)) {
                    oneSecond = 0;
                }

                if (oneSecond()) {
                    gamePublicFuntion.init();
                }

                result = mFairy.findPic(9, 1184, 151, 1276, "chu.png");
                mFairy.onTap(0.8f, result, "出城", 500);

                result = mFairy.findPic("birang.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 184, 698, 227, 717, "避让", 500);
                    gamePublicFuntion.close();
                }

                FindResult chu = mFairy.findPic(413, 1088, 712, 1274, "nn23.png");
                if (chu.sim > 0.85f) {

                    result = mFairy.findPic("meiyou.png");
                    if (result.sim > 0.85f) {
                        if (tishi) {
                            mFairy.finish(AtFairyConfig.getTaskID(), 16701);
                            return;
                        }
                    }

                    mFairy.onTap(0.85f, chu, "出征", 500);

                    gamePublicFuntion.close();

                    tishi = false;
                }

                if (zyTime.timeJudge(120000)) {
                    judgeOneSecond = 0;
                }

                if (judgeOneSecond()) {
                    result = mFairy.findPic("nn46.png");
                    if (result.sim > 0.8f) {
                        judgeOneSecond = 1;
                        return;
                    }

                    result = mFairy.findPic(487, 926, 714, 1194, "nn52.png");
                    mFairy.onTap(0.85f, result, "位置", 1000);

                    FindResult zhao = mFairy.findPic("nn35.png");
                    if (zhao.sim > 0.85f) {

                        result = mFairy.findPic("nn55.png");
                        if (result.sim > 0.8f) {
                            tishi = true;
                        }
                        mFairy.onTap(0.85f, zhao, "查找", 500);
                    }

                    result = mFairy.findPic("nn36.png");
                    if (result.sim > 0.85f) {
                        switch (nn) {
                            case 1:
                                result = mFairy.findPic(7, 1026, 709, 1102, "nn37.png");
                                mFairy.onTap(0.85f, result, result.x + 15, result.y - 50, result.x + 20, result.y - 40, "矿厂", 500);
                                nn = 2;
                                break;
                            case 2:
                                result = mFairy.findPic(7, 1026, 709, 1102, "nn38.png");
                                mFairy.onTap(0.85f, result, result.x + 15, result.y - 50, result.x + 20, result.y - 40, "石油", 500);
                                nn = 1;
                                judgeOneSecond = 1;
                                break;
                        }

                        for (int i = 0; i < 3; i++) {
                            mFairy.onTap(58, 1189, 80, 1208, "", 100);
                        }
                        Thread.sleep(1000);
                        for (int i = 0; i < 2; i++) {
                            mFairy.onTap(432, 1185, 453, 1206, "", 100);
                        }

                        for (int h = 0; h < 3; h++) {


                            result = mFairy.findPic("chazhao3.png");
                            if (result.sim > 0.85f) {
                                mFairy.onTap(0.85f, result, "查找>>>", 1000);
                                if (mapCount(0.8f, 5, 181, 243, 340, new String[]{"chazhao1.png","nn60.png"})) {
                                    mFairy.onTap(59, 1187, 80, 1203, "未发现资源 等级降低-1", 2000);
                                } else {
                                    break;
                                }
                            }
                        }

                        for (int i = 0; i < 5; i++) {

                            result = mFairy.findPic(353, 200, 679, 809, new String[]{"chuzheng5.png", "nn53.png"});
                            if (result.sim > 0.75f) {
                                mFairy.onTap(0.75f, result, "采集", 1500);
                                break;
                            }
                        }
                    }
                } else {
                    err = 0;
                    result = mFairy.findPic(413, 1088, 712, 1274, "nn23.png");
                    if (result.sim < 0.85f) {
                        if (sjTime.timeJudge(600000)) {
                            judgeOneSecond = 0;
                            if (shengji()) {
                                setTaskEnd();
                                return;
                            }
                            setTaskName(2);
                            gamePublicFuntion.init();
                            judgeOneSecond = 1;
                        }
                    }
                }
            }
        };

    }//一键起号

}