package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class TeamTask {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    CommonFunction commonFunction;
    GamePublicFunction gamePublicFunction;
    TimingActivity timingActivity;
    FindResult findResult;

    public TeamTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        commonFunction = new CommonFunction(mFairy);
        gamePublicFunction = new GamePublicFunction(mFairy);
        timingActivity = new TimingActivity(mFairy);
    }

    //日常带队捉鬼
    public void DailyGhostChase(String str) throws Exception {
        int bj = 0, bj_1;
        int cs_1 = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        int kf = 0, lqsb = 0;
        if (AtFairyConfig.getOption("kf").equals("1")) {
            kf = 1;
        }
        if (AtFairyConfig.getOption("ls").equals("1")) {
            lqsb = 1;
        }
        if (lqsb == 1) {
            gamePublicFunction.mission2("Hanging.png");
            result = mFairy.findPic2(commonFunction.setImg("gysb.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领取双倍"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
        }
        bj_1 = bj;

        long time = System.currentTimeMillis() - 30000;

        while (mFairy.condit()) {

            if (bj_1 == bj && bj != 5) {
                cs_1++;
            } else if (bj_1 != bj) {
                cs_1 = 0;
                bj_1 = bj;
                LtLog.e(commonFunction.getLineInfo("标记发生变化"));
            }
            if (cs_1 > 150) {
                cs_1 = 0;
                bj = 0;
                LtLog.e(commonFunction.getLineInfo("标记长时间未发生变化"));
            }
            Thread.sleep(1500);

            result = mFairy.findPic2(322, 305, 961, 437, commonFunction.setImg("zgkf.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "跨服抓鬼"));
            commonFunction.RndCompare(0.8f, 401, 487, result, commonFunction.getImg());

            result = mFairy.findPic(304, 308, 600, 425, "kua.png");
            mFairy.onTap(0.8f, result, 395, 485, 421, 498, "确定跨服", 1000);

            LtLog.e(commonFunction.getLineInfo("带队捉鬼中bj=" + bj));

            findResult = mFairy.findPic(753, 421, 994, 560, new String[]{"quxiao.png", "quxiao1.png"});
            mFairy.onTap(0.7f, findResult, "取消", 1000);

            findResult = mFairy.findPic(736, 483, 1037, 563, "kfzd.png");
            if (findResult.sim > 0.8f) {
                findResult = mFairy.findPic(932,497,997,549,"kfzd2.png");
                if(findResult.sim<0.8f){
                    mFairy.onTap(958,515,970,532,"跨服组队",1000);
                }
            }

            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                gamePublicFunction.init();
                if (kf == 1) {
                    kf();
                } else {
                    gamePublicFunction.Hyf();
                }
                bj = 1;
            }
            if (bj == 1) {
                gamePublicFunction.SetJudgeTask("team");

                result = mFairy.findPic2(201, 68, 352, 127, commonFunction.setImg("Bcreateteam.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "创建队伍"));

                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                result = mFairy.findPic2(182, 59, 370, 137, commonFunction.setImg("Blkdw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
                if (result.sim > 0.8f) {



                    result = mFairy.findPic2(787, 594, 928, 651, commonFunction.setImg("Bsqdz.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "是队员"));
                    if (result.sim > 0.8f) {
                        gamePublicFunction.Signout();
                    } else {
                        mFairy.onTap(1132,120,1152,171,"",2000);

                        result = mFairy.findPic2(174, 556, 280, 653, commonFunction.setImg("1people.png"));
                        if (result.sim < 0.8f) {
                            LtLog.e(commonFunction.getLineInfo("4个人"));
                            bj = 2;
                        }

                        findResult = mFairy.findPic(1075,49,1222,480,"pi.png");
                        mFairy.onTap(0.8f,findResult,"匹配",2000);

                        result = mFairy.findPic2(190,137,443,664, commonFunction.setImg("zg2.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "勾选捉鬼"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        if (result.sim < 0.8f) {
                            gamePublicFunction.BackCity(698, 497, "长安");
                        }

                        findResult = mFairy.findPic(929,541,1115,676,"pi1.png");
                        mFairy.onTap(0.8f,findResult,"发布",2000);

                        mFairy.onTap(1132,120,1152,171,"",2000);

                    }
                }
            }

            if (bj == 2) {
                gamePublicFunction.SetJudgeTask("team");
                result = mFairy.findPic2(182, 59, 370, 137, commonFunction.setImg("Blkdw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(172, 238, 290, 338, commonFunction.setImg("1people.png"));
                    if (result.sim < 0.8f) {
                        LtLog.e(commonFunction.getLineInfo("1个人"));
                    }
                    result = mFairy.findPic2(172, 342, 283, 446, commonFunction.setImg("1people.png"));
                    if (result.sim < 0.8f) {
                        LtLog.e(commonFunction.getLineInfo("2个人"));
                        js_2++;
                        if (js_2 > 50) {
                            bj = 3;
                        }
                    }
                    result = mFairy.findPic2(171, 448, 282, 552, commonFunction.setImg("1people.png"));
                    if (result.sim < 0.8f) {
                        LtLog.e(commonFunction.getLineInfo("3个人"));
                        js_1++;
                        if (js_1 > 30) {
                            bj = 3;
                        }
                    }
                    result = mFairy.findPic2(174, 556, 280, 653, commonFunction.setImg("1people.png"));
                    if (result.sim < 0.8f) {
                        LtLog.e(commonFunction.getLineInfo("4个人"));
                        bj = 3;
                    }
                }

                if (System.currentTimeMillis() - time > 30000) {
                    result = mFairy.findPic2(646, 51, 870, 140, commonFunction.setImg("rank.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.85f, "自动组队"));
                    commonFunction.Compare(0.85f, result, commonFunction.getImg());
                    time = System.currentTimeMillis();
                }

                result = mFairy.findPic(651,155,1065,539,"jieshou.png");
                mFairy.onTap(0.8f,result,"接受申请",1000);

                result = mFairy.findPic2(853, 586, 1122, 676, commonFunction.setImg("yin_fbzd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "发布组队"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                for (int i = 0; i < 4; i++) {
                    result = mFairy.findPic2(390, 173, 661, 254, commonFunction.setImg("count_out.png"));
                    if (result.sim > 0.75f) {
                        LtLog.e(commonFunction.getLineInfo("抓鬼次数过多"));
                        return;
                    }
                    result = commonFunction.FindManyPic(333, 240, 442, 650, new String[]{"lixian.png", "zanli.png"}, 0);
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离线"));
                    if (result.sim > 0.8f) {
                        js_5++;
                        if (js_5 > 8) {
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        }
                        bj = 2;
                    } else {
                        js_5 = 0;
                    }
                    result = mFairy.findPic2(477, 5, 631, 711, commonFunction.setImg("qldy.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    if (result.sim > 0.8f) {
                        bj = 2;
                    }
                    result = mFairy.findPic2(314, 298, 963, 443, commonFunction.setImg("yin_qldy.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员扣活跃"));
                    commonFunction.RndCompare(0.8f, 880, 489, result, commonFunction.getImg());

                }
            }
            if (bj == 3) {
                gamePublicFunction.init();
                gamePublicFunction.SetJudgeTask("task");
                result = mFairy.findPic2(1017, 186, 1104, 510, commonFunction.setImg("rwlzg.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.75f, "任务栏捉鬼"));
                commonFunction.Compare(0.75f, result, commonFunction.getImg());
                if (result.sim > 0.75f) {
                    bj = 5;
                } else {
                    ret = gamePublicFunction.mission(str);
                    if (ret == 0) {
                        break;
                    } else if (ret == 1) {
                        bj = 4;
                    }
                }
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
            }
            if (bj == 4) {

                result = mFairy.findPic2(52, 78, 1030, 572, commonFunction.setImg("zhongkui.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.1f, "钟馗"));
                commonFunction.RndCompare(0.7f, result.x - 10, result.y - 50, result, commonFunction.getImg());

                result = mFairy.findPic2(801, 397, 1000, 600, commonFunction.setImg("Bmwt.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "没问题，我去"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    bj = 5;
                }
                js_4++;
                if (js_4 > 20) {
                    commonFunction.RndCompare(768, 302, "点一下移动");
                    js_4 = 0;
                    bj = 0;
                }
            }

            if (bj == 5) {

                findResult = mFairy.findPic("chumo.png");
                mFairy.onTap(0.8f, findResult, "右侧除魔", 1000);

                result = mFairy.findPic2(1017, 186, 1104, 510, commonFunction.setImg("rwlzg.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "任务栏捉鬼"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_3 = 0;
                }


                result = mFairy.findPic2(451, 286, 999, 521, commonFunction.setImg("Blqsb.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领取双倍"));
                if (result.sim > 0.8f && lqsb == 1) {
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                } else if (result.sim > 0.8f && lqsb == 0) {
                    commonFunction.RndCompare(0.8f, 1044, 249, result, commonFunction.getImg());
                }


                result = mFairy.findPic2(806, 365, 945, 563, commonFunction.setImg("Bswhq.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "送我回去"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    bj = 3;
                }

                result = mFairy.findPic2(790, 256, 1243, 383, commonFunction.setImg("People are not enough.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "人不够"));
                if (result.sim > 0.8f) {
                    bj = 0;
                }

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_3 = 0;
                } else {
                    js_3++;
                    if (js_3 > 10) {
                        bj = 0;
                    }
                }

                result = commonFunction.FindManyPic(865, 577, 1090, 647, new String[]{"Bshop.png", "Bsdshop.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购买"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8) {
                    gamePublicFunction.init();
                }

                result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                }

                result = mFairy.findPic2(1118, 217, 1174, 349, commonFunction.setImg("Stalls.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "右侧逛摊"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(245, 121, 482, 578, commonFunction.setImg("gtneed.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "需求"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic2(638, 600, 781, 657, commonFunction.setImg("Byjsj.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.1f, "切换到摊位列表"));
                        commonFunction.RndCompare(0.8f, 302, 95, result, commonFunction.getImg());

                        result1 = mFairy.findPic2(505, 98, 860, 574, commonFunction.setImg("need.png"));
                        LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "物品需求"));
                        commonFunction.RndCompare(0.8f, result1.x + 139, result1.y + 18, result1, commonFunction.getImg());
                        commonFunction.RndCompare(0.8f, 785, 636, result1, commonFunction.getImg());

                        result = mFairy.findPic2(439, 124, 488, 578, commonFunction.setImg("nowshop.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购物当前栏"));
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic2(result.x - 60, result.y - 10, result.x + 5, result.y + 40, commonFunction.setImg("gtneed.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "摊位列表需求"));
                        }

                        if (result.sim > 0.8f && result1.sim < 0.8f) {
                            commonFunction.RndCompare(992, 636, "没有需求物品下一页");
                        } else if (result.sim < 0.8f && result1.sim < 0.8f) {
                            break;
                        }

                        result = mFairy.findPic2(505, 98, 860, 574, commonFunction.setImg("Bshuaxin.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "刷新"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    }
                }
            }
        }
    }

    //日常跟队捉鬼
    public void FollowGui() throws Exception {
        int bj = 0, bj_1;
        int cs_1 = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        int kf = 0, lqsb = 0, zgsl = 0;
        if (AtFairyConfig.getOption("kf").equals("1")) {
            kf = 1;
        }
        if (AtFairyConfig.getOption("lqsb").equals("1")) {
            lqsb = 1;
        }
        if (AtFairyConfig.getOption("zgsl").equals("3")) {
            zgsl = 3;
        }
        bj_1 = bj;
        while (mFairy.condit()) {
            if (bj_1 == bj && bj != 4) {
                cs_1++;
            } else if (bj_1 != bj) {
                cs_1 = 0;
                bj_1 = bj;
                LtLog.e(commonFunction.getLineInfo("标记发生变化"));
            }
            if (cs_1 > 150) {
                cs_1 = 0;
                bj = 0;
                LtLog.e(commonFunction.getLineInfo("标记长时间未发生变化"));
            }
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("跟队捉鬼中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                gamePublicFunction.init();
                if (kf == 1) {
                    kf();
                } else {
                    gamePublicFunction.Hyf();
                }
                bj = 1;
            }
            if (bj == 1) {
                gamePublicFunction.SetJudgeTask("team");

                result = mFairy.findPic2(201, 68, 352, 127, commonFunction.setImg("Bcreateteam.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一个人"));
                if (result.sim > 0.8f) {
                    gamePublicFunction.BackCity(698, 497, "长安");
                    bj = 2;

                }
                result = mFairy.findPic2(182, 59, 370, 137, commonFunction.setImg("Blkdw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(787, 594, 928, 651, commonFunction.setImg("Bsqdz.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "是队员"));
                    if (result.sim > 0.8f) {
                        bj = 4;
                    } else {
                        LtLog.e(commonFunction.getLineInfo("是队长"));
                        gamePublicFunction.Signout();
                        gamePublicFunction.BackCity(698, 497, "长安");

                        bj = 2;
                    }
                }
            }
            if (bj == 2) {
                if (zgsl == 3) {
                    gamePublicFunction.init();
                    bj = 3;
                } else {
                    ret = gamePublicFunction.mission("Ghost Chase.png");
                    if (ret == 0) {
                        break;
                    } else if (ret == 1) {
                        gamePublicFunction.init();
                        bj = 3;
                    }
                }
            }
            if (bj == 3) {
                gamePublicFunction.SetJudgeTask("team");

                findResult = mFairy.findPic(1075,49,1222,480,"pi.png");
                mFairy.onTap(0.8f,findResult,"匹配",2000);

                result = mFairy.findPic2(177, 133, 448, 658, commonFunction.setImg("Catch the ghost hunter.png"));
                if (result.sim > 0.8f) {
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "勾选捉鬼"));


                    result1 = mFairy.findPic2(result.x - 150, result.y - 40, result.x + 25, result.y + 60, commonFunction.setImg("gdpipei.png"));
                    LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "匹配中"));
                    if (result1.sim > 0.8f) {
                    } else {
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        commonFunction.RndCompare(1015, 625, "开始匹配");
                    }
                }


                result = mFairy.findPic2(182, 59, 370, 137, commonFunction.setImg("Blkdw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
                commonFunction.RndCompare(0.8f, 1127, 49, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    bj = 4;
                }
            }
            if (bj == 4) {
                Thread.sleep(3000);
                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_1 = 0;

                } else {
                    js_1++;
                    if (js_1 > 50) {
                        gamePublicFunction.Signout();
                        bj = 0;
                    }
                }

                js_2++;
                if (js_2 > 10 && zgsl != 3) {
                    js_2 = 0;
                    result = mFairy.findPic2(217, 4, 628, 85, commonFunction.setImg("Nricheng1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());

                    result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "打开日程"));
                    commonFunction.RndCompare(0.8f, 43, 310, result, commonFunction.getImg());

                    result = mFairy.findPic2(550, 50, 734, 108, commonFunction.setImg("cghd.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "常规活动"));
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic2(211, 92, 1086, 692, commonFunction.setImg("Ghost Chase.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "找到任务抓鬼"));
                        if (result.sim > 0.96f) {
                            if (AtFairyConfig.getOption("zgsl").equals("1")) {
                                result1 = mFairy.findPic2(result.x + 30, result.y + 56, result.x + 108, result.y + 127, commonFunction.setImg("zghy1.png"));
                                LtLog.e(commonFunction.getLineInfo(result1, 0.1f, "活跃鬼"));
                                if (result1.sim > 0.95f) {
                                    result1 = mFairy.findPic2(result.x + 11, result.y + 70, result.x + 27, result.y + 101, commonFunction.setImg("zghy2.png"));
                                    LtLog.e(commonFunction.getLineInfo(result1, 0.1f, "数量的十位数是不是1"));
                                    if (result1.sim > 0.95f) {
                                    } else {
                                        gamePublicFunction.Signout1();
                                        return;
                                    }

                                }
                            }
                        } else if (result.sim > 0.8f) {
                            LtLog.e(commonFunction.getLineInfo("任务完成"));
                            gamePublicFunction.Signout();
                            return;
                        }
                    }
                }

                result = mFairy.findPic2(414, 184, 863, 246, commonFunction.setImg("zgcsgd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "无限没经验了"));
                if (result.sim > 0.8f) {
                    gamePublicFunction.Signout1();
                    gamePublicFunction.Signout();
                    break;
                }
                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(550, 50, 734, 108, commonFunction.setImg("cghd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "常规活动"));
                commonFunction.RndCompare(0.8f, 1127, 46, result, commonFunction.getImg());

                result = mFairy.findPic2(451, 286, 999, 521, commonFunction.setImg("Blqsb.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领取双倍"));
                if (result.sim > 0.8f && lqsb == 1) {
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                } else if (result.sim > 0.8f && lqsb == 0) {
                    commonFunction.RndCompare(0.8f, 1044, 249, result, commonFunction.getImg());
                }

            }

        }
    }

    //日常带队英雄副本
    public void teamCopy1() throws Exception {
        int bj = 0, bj_1;
        int cs_1 = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        int kf = 0, lqsb = 0, yl = 0;

        if (AtFairyConfig.getOption("fbkf").equals("1")) {
            kf = 1;
        }
        if (AtFairyConfig.getOption("fbls").equals("1")) {

            gamePublicFunction.mission2("Hanging.png");

            result = mFairy.findPic2(commonFunction.setImg("gysb.png"));

            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领取双倍"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
            lqsb = 1;
        }
        if (AtFairyConfig.getOption("yl").equals("1")) {
            yl = 1;
        }
        if (AtFairyConfig.getOption("cxzd").equals("1")) {
            gamePublicFunction.Signout();
        }
        bj_1 = bj;


        while (mFairy.condit()) {
            if (bj_1 == bj && bj != 5) {
                cs_1++;
            } else if (bj_1 != bj) {
                cs_1 = 0;
                bj_1 = bj;
                LtLog.e(commonFunction.getLineInfo("标记发生变化"));
            }
            if (cs_1 > 150) {
                cs_1 = 0;
                bj = 0;
                LtLog.e(commonFunction.getLineInfo("标记长时间未发生变化"));
            }
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("带队副本中bj=" + bj));

            result = mFairy.findPic(304, 308, 600, 425, "kua.png");
            mFairy.onTap(0.8f, result, 395, 485, 421, 498, "确定跨服", 1000);

            findResult = mFairy.findPic(753, 421, 994, 560, new String[]{"quxiao.png", "quxiao1.png"});
            mFairy.onTap(0.7f, findResult, "取消", 1000);

            findResult = mFairy.findPic(736, 483, 1037, 563, "kfzd.png");
            if (findResult.sim > 0.8f) {
                findResult = mFairy.findPic(932,497,997,549,"kfzd1.png");
                mFairy.onTap(0.92f, findResult, "勾选跨服组队", 1000);
            }

            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                gamePublicFunction.init();
                if (kf == 1) {
                    kf();
                } else {
                    gamePublicFunction.Hyf();
                }
                bj = 1;
            }
            if (bj == 1) {
                gamePublicFunction.SetJudgeTask("team");

                result = mFairy.findPic2(201, 68, 352, 127, commonFunction.setImg("Bcreateteam.png"));

                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一个人"));
                if (result.sim > 0.8f) {
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    bj = 2;
                }
                result = mFairy.findPic2(182, 59, 370, 137, commonFunction.setImg("Blkdw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(787, 594, 928, 651, commonFunction.setImg("Bsqdz.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "是队员"));
                    if (result.sim > 0.8f) {
                        gamePublicFunction.Signout();
                    } else {
                        bj = 2;
                    }
                }
            }
            if (bj == 2) {
                ret = gamePublicFunction.mission("cghdfb.png");
                if (ret == 0) {
                    break;
                } else if (ret == 1) {
                    bj = 3;
                }
            }
            if (bj == 3) {
                result = mFairy.findPic2(commonFunction.setImg("xzfbjm.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "选择副本界面"));
                if (result.sim > 0.8f) {
                    commonFunction.RndCompare(513, 178, "英雄战场");
                    commonFunction.RndCompare(759, 635, "立即前往");
                }

                result = mFairy.findPic2(782, 404, 1252, 695, commonFunction.setImg("ddz.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "当队长"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(786, 408, 1248, 664, commonFunction.setImg("yin_yxzcsure.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "是"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                gamePublicFunction.SetJudgeTask("team");

                result = mFairy.findPic2(182, 59, 370, 137, commonFunction.setImg("Blkdw.png"));
                if (result.sim > 0.8f) {
                    LtLog.e(commonFunction.getLineInfo("队伍界面"));
                    bj = 4;


                }
                result = mFairy.findPic2(201, 68, 352, 127, commonFunction.setImg("Bcreateteam.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一个人"));
                if (result.sim > 0.8f) {
                    gamePublicFunction.BackCity(698, 497, "长安");
                    bj = 0;
                }
            }
            if (bj == 4) {
                gamePublicFunction.SetJudgeTask("team");

                result = mFairy.findPic2(182, 59, 370, 137, commonFunction.setImg("Blkdw.png"));
                if (result.sim > 0.8f) {
                    LtLog.e(commonFunction.getLineInfo("队伍界面"));

                    result = mFairy.findPic2(172, 238, 290, 338, commonFunction.setImg("1people.png"));
                    if (result.sim < 0.8f) {
                        LtLog.e(commonFunction.getLineInfo("1个人"));
                    }
                    result = mFairy.findPic2(172, 342, 283, 446, commonFunction.setImg("1people.png"));
                    if (result.sim < 0.8f) {
                        LtLog.e(commonFunction.getLineInfo("2个人"));
                    }
                    result = mFairy.findPic2(171, 448, 282, 552, commonFunction.setImg("1people.png"));
                    if (result.sim < 0.8f) {
                        LtLog.e(commonFunction.getLineInfo("3个人"));
                    }
                    result = mFairy.findPic2(174, 556, 280, 653, commonFunction.setImg("1people.png"));
                    if (result.sim < 0.8f) {
                        LtLog.e(commonFunction.getLineInfo("4个人"));
                        gamePublicFunction.init();
                        bj = 5;
                    }
                }



                result = mFairy.findPic2(commonFunction.setImg("yin_yxzc.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.85f, "英雄战场"));
                commonFunction.RndCompare(0.85f, 684, 196, result, commonFunction.getImg());

                result = mFairy.findPic2(853, 586, 1122, 676, commonFunction.setImg("yin_fbzd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "发布组队"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                commonFunction.delays(0.8f, result, 2000);
                commonFunction.RndCompare(0.8f, 802, 98, result, commonFunction.getImg());

                for (int i = 0; i < 4; i++) {
                    result = commonFunction.FindManyPic(333, 240, 442, 650, new String[]{"lixian.png", "zanli.png"}, 0);
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离线"));
                    if (result.sim > 0.8f) {
                        js_5++;
                        if (js_5 > 8) {
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        }
                        bj = 4;
                    } else {
                        js_5 = 0;
                    }
                    result = mFairy.findPic2(477, 5, 631, 711, commonFunction.setImg("qldy.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    if (result.sim > 0.8f) {
                        bj = 4;
                    }

                    result = mFairy.findPic2(314, 298, 963, 443, commonFunction.setImg("yin_qldy.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员扣活跃"));
                    commonFunction.RndCompare(0.8f, 880, 489, result, commonFunction.getImg());
                }
            }
            if (bj == 5) {
                result = commonFunction.FindManyPic(777, 377, 1252, 697, new String[]{"pxnh.png", "wytzn.png", "fbdh1.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "对话"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(770, 203, 1249, 347, commonFunction.setImg("min4.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "至少四人组队"));
                commonFunction.RndCompare(0.8f, 1247, 61, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    bj = 0;
                }
                result = mFairy.findPic2(451, 286, 999, 521, commonFunction.setImg("Blqsb.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领取双倍"));
                if (result.sim > 0.8f && lqsb == 1) {
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                } else if (result.sim > 0.8f && lqsb == 0) {
                    commonFunction.RndCompare(0.8f, 1044, 249, result, commonFunction.getImg());
                }

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_3 = 0;
                } else {
                    if (yl == 1) {
                        result = mFairy.findPic2(217, 4, 628, 85, commonFunction.setImg("Nricheng1.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());

                        result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "打开日程"));
                        commonFunction.RndCompare(0.8f, 43, 310, result, commonFunction.getImg());

                        result = mFairy.findPic2(550, 50, 734, 108, commonFunction.setImg("cghd.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "常规活动"));
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic2(211, 92, 1086, 692, commonFunction.setImg("cghdfb.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.1f, "找到任务副本 result:" + result));
                            if (result.sim > 0.9f) {

                                // 236,264   266,320,344,391

                                result1 = mFairy.findPic2(result.x + 30, result.y + 56, result.x + 108, result.y + 127, commonFunction.setImg("zghy1.png"));
                                LtLog.e(commonFunction.getLineInfo(result1, 0.1f, "活跃"));
                                if (result1.sim > 0.85f) {
                                    commonFunction.RndCompare(0.8f, result.x + 85, result.y + 97, result, commonFunction.getImg());
                                    Thread.sleep(1000);
                                    result1 = mFairy.findPic2(result.x - 80, result.y + 30, result.x - 51, result.y + 62, commonFunction.setImg("fbyl.png"));
                                    LtLog.e(commonFunction.getLineInfo(result1, 0.1f, "是不是5"));
                                    if (result1.sim > 0.85f) {
                                        return;
                                    }
                                }
                            } else if (result.sim > 0.8f) {
                                LtLog.e(commonFunction.getLineInfo("任务完成"));
                                return;
                            }
                        }
                    }
                    result = mFairy.findPic2(550, 50, 734, 108, commonFunction.setImg("cghd.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "常规活动"));
                    commonFunction.RndCompare(0.8f, 1127, 46, result, commonFunction.getImg());

                    gamePublicFunction.SetJudgeTask("task");
                    result = mFairy.findPic2(1017, 186, 1104, 510, commonFunction.setImg("yin_yxzctz.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "任务栏挑战"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    if (result.sim > 0.8f) {
                        Thread.sleep(8000);
                        js_3 = 0;
                    }
                    js_3++;
                    if (js_3 > 15) {
                        bj = 0;
                    }
                }
                result = mFairy.findPic2(539, 200, 747, 302, commonFunction.setImg("kqbx1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "奖励的箱子"));
                commonFunction.RndCompare(0.8f, 651, 353, result, commonFunction.getImg());
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
              /*  if (result.sim>0.8f){
                    js_1++;
                    if (js_1>=5 && yl==1){
                        LtLog.e(commonFunction.getLineInfo("一轮完成"));
                        gamePublicFunction.init();
                        break;
                    }
                    Thread.sleep(5000);
                }*/
            }
        }
    }

    //带队封妖
    public void DdFengyao() throws Exception {
        int bj = 0, bj_1;
        int cs_1 = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        int kf = 0, lqsb = 0, ditu = 0;
        String string = "";
        if (AtFairyConfig.getOption("fyls").equals("1")) {
            lqsb = 1;
        }
        ditu = Integer.parseInt(AtFairyConfig.getOption("ditu"));
        if (ditu == 1) {
            string = "514.png";
        } else if (ditu == 2) {
            string = "1524.png";
        } else if (ditu == 3) {
            string = "2534.png";
        } else if (ditu == 4) {
            string = "3544.png";
        } else if (ditu == 5) {
            string = "4554.png";
        } else if (ditu == 6) {
            string = "5564.png";
        } else if (ditu == 7) {
            string = "6574.png";
        } else if (ditu == 8) {
            string = "7584.png";
        } else if (ditu == 9) {
            string = "8594.png";
        } else if (ditu == 10) {
            string = "95104.png";
        } else if (ditu == 11) {
            string = "100109.png";
        } else if (ditu == 12) {
            string = "110119.png";
        } else if (ditu == 13) {
            string = "120129.png";
        } else if (ditu == 14) {
            string = "130139.png";
        }
        bj_1 = bj;
        while (mFairy.condit()) {
            if (bj_1 == bj) {
                cs_1++;
            } else if (bj_1 != bj) {
                cs_1 = 0;
                bj_1 = bj;
                LtLog.e(commonFunction.getLineInfo("标记发生变化"));
            }
            if (cs_1 > 150) {
                cs_1 = 0;
                bj = 0;
                LtLog.e(commonFunction.getLineInfo("标记长时间未发生变化"));
            }
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("带队封妖中bj=" + bj));

            findResult = mFairy.findPic(821, 435, 939, 537, "quxiao.png");
            mFairy.onTap(0.8f, findResult, "取消", 1000);

            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                gamePublicFunction.init();
                result = mFairy.findPic2(commonFunction.setImg("yuanfu.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击了原服"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                bj = 1;
            }
            if (bj == 1) {
                gamePublicFunction.SetJudgeTask("team");

                result = mFairy.findPic2(201, 68, 352, 127, commonFunction.setImg("Bcreateteam.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "创建队伍"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(182, 59, 370, 137, commonFunction.setImg("Blkdw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(787, 594, 928, 651, commonFunction.setImg("Bsqdz.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "是队员"));
                    if (result.sim > 0.8f) {
                        gamePublicFunction.Signout();
                    } else {
                        result = mFairy.findPic2(450, 66, 748, 129, commonFunction.setImg("Wild goblin.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "目标封妖"));
                        if (result.sim > 0.8f) {
                           /* result = mFairy.findPic2(934,499,1006,554,commonFunction.setImg("duigou.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "跨服的对勾"));
                            if (result.sim>0.8f && kf==1){
                            }if (result.sim>0.8f && kf==0){
                                commonFunction.RndCompare(0.8f, 959, 527, result, commonFunction.getImg());
                            }if (result.sim<0.8f && kf==1){
                                commonFunction.RndCompare(959, 527, "跨服的对勾");
                            }if (result.sim<0.8f && kf==0){
                            }*/
                            for (int i = 0; i < 5; i++) {
                                result = commonFunction.FindManyPic(333, 240, 442, 650, new String[]{"lixian.png", "zanli.png"}, 0);
                                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离线"));
                                if (result.sim > 0.8f) {
                                    js_5++;
                                    if (js_5 > 2) {
                                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                                    }
                                } else {
                                    js_5 = 0;
                                }
                                result = mFairy.findPic2(477, 5, 631, 711, commonFunction.setImg("qldy.png"));
                                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员"));
                                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                                result = mFairy.findPic2(314, 298, 963, 443, commonFunction.setImg("yin_qldy.png"));
                                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员扣活跃"));
                                commonFunction.RndCompare(0.8f, 880, 489, result, commonFunction.getImg());

                            }
                            result = mFairy.findPic2(853, 586, 1122, 676, commonFunction.setImg("yin_fbzd.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "发布组队"));
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());
                            bj = 2;
                        } else {
                            result = mFairy.findPic2(454, 136, 731, 598, commonFunction.setImg("Wild goblin1.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "勾选封妖"));
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());
                            if (result.sim < 0.8f) {
                                gamePublicFunction.BackCity(698, 497, "长安");
                                result = mFairy.findPic2(commonFunction.setImg("yuanfu.png"));
                                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击了原服"));
                                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                            }
                          /*  if (kf==1){
                                commonFunction.RndCompare(0.8f, 961, 527, result, commonFunction.getImg());
                            }*/
                            commonFunction.RndCompare(0.8f, 1021, 627, result, commonFunction.getImg());
                            result = mFairy.findPic2(174, 556, 280, 653, commonFunction.setImg("1people.png"));
                            if (result.sim < 0.8f) {
                                LtLog.e(commonFunction.getLineInfo("4个人"));
                                bj = 2;
                            }
                        }
                    }
                }

            }
            if (bj == 2) {
                ret = gamePublicFunction.mission("mrtz.png");
                if (ret == 0) {
                    break;
                } else if (ret == 1) {
                    result = mFairy.findPic2(521, 157, 634, 204, commonFunction.setImg("20ci.png"));
                    LtLog.e("封妖20次sim :" + result.sim);
                    if (result.sim > 0.8f) {
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "20次完成"));
                        break;
                    }
                    bj = 3;
                }
            }
            if (bj == 3) {
                gamePublicFunction.init();
                gamePublicFunction.SetJudgeTask("task");
                result = mFairy.findPic2(217, 4, 628, 85, commonFunction.setImg("Nricheng1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(550, 50, 734, 108, commonFunction.setImg("cghd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "常规活动"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(211, 92, 1086, 692, commonFunction.setImg("Hanging.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "找到任务" + "Hanging.png"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    if (result.sim > 0.8f) {
                        bj = 4;
                    } else {
                        bj = 3;
                    }
                }
                //    ret = gamePublicFunction.mission2("Hanging.png");
                Thread.sleep(2000);
                for (int i = 0; i < 3; i++) {
                    result = mFairy.findPic2(commonFunction.setImg("dqmx.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点驱魔香"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());

                    result = mFairy.findPic2(565, 224, 730, 309, commonFunction.setImg("dqmx1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点驱魔香"));
                    commonFunction.RndCompare(0.8f, 390, 480, result, commonFunction.getImg());

                }

            }
            if (bj == 4) {
                result = mFairy.findPic2(commonFunction.setImg("zdset.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.86f, "打怪双倍界面"));
                if (result.sim > 0.8) {
                    result = mFairy.findPic2(246, 403, 1070, 460, commonFunction.setImg(string));
                    LtLog.e(commonFunction.getLineInfo(result, 0.86f, "地图"));
                    commonFunction.RndCompare(0.86f, result.x + 20, result.y - 200, result, commonFunction.getImg());
                    if (result.sim > 0.86f) {
                        bj = 5;
                    } else {
                        js_1++;
                        if (js_1 == 2 || js_1 == 4 || js_1 == 6 || js_1 == 8 || js_1 == 10 || js_1 == 12) {
                            commonFunction.RanSwipe(273, 263, 800, 270, 1, 1500);
                        }
                        if (js_1 > 14) {
                            bj = 0;
                        }
                    }
                } else {
                    bj = 0;
                }

            }
            if (bj == 5) {
                LtLog.e(commonFunction.getLineInfo("js_2=" + js_2 + ",js_3=" + js_3));
                result = mFairy.findPic2(316, 288, 792, 443, commonFunction.setImg("yin_gdj.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "场景等级高确定"));
                commonFunction.RndCompare(0.8f, 401, 487, result, commonFunction.getImg());

                result = mFairy.findPic2(795, 386, 957, 590, commonFunction.setImg("yaonie.png"));
                if (result.sim > 0.8f) {

                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "妖孽"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());

                    js_2++;
                    if (js_2 >= 10) {
                        js_2 = 0;
                        bj = 2;
                    } else {
                        Thread.sleep(5000);
                        result = mFairy.findPic2(451, 286, 999, 521, commonFunction.setImg("Blqsb.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领取双倍"));
                        if (result.sim > 0.8f && lqsb == 1) {
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        } else if (result.sim > 0.8f && lqsb == 0) {
                            commonFunction.RndCompare(0.8f, 1044, 249, result, commonFunction.getImg());
                        }
                        gamePublicFunction.init();
                    }
                    js_3 = 0;
                } else {
                    js_3++;
                    if (js_3 > 15) {
                        js_3 = 0;
                        bj = 3;
                    }
                }

                for (int i = 0; i < 1; i++) {
                  /* result = mFairy.findPic2(78,89,1023,495,commonFunction.setImg("ygyg1.png"));
                   LtLog.e(commonFunction.getLineInfo(result, 0.1f, "远古"));
                   commonFunction.RndCompare(0.7f, result.x+56, result.y-75, result, commonFunction.getImg());
                   if (result.sim>0.7f){
                       break;
                   }*/

                    result = mFairy.findPic2(65, 115, 1271, 622, commonFunction.setImg("ygyg5.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "远古"));
                    commonFunction.RndCompare(0.7f, result.x + 56, result.y - 75, result, commonFunction.getImg());
                    if (result.sim > 0.7f) {
                        break;
                    }


                    result = mFairy.findPic2(65, 115, 1271, 622, commonFunction.setImg("ygyg4.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "远古"));
                    commonFunction.RndCompare(0.7f, result.x + 56, result.y - 75, result, commonFunction.getImg());
                    if (result.sim > 0.7f) {
                        break;
                    }


                    result = mFairy.findPic2(65, 115, 1271, 622, commonFunction.setImg("ygyg6.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "远古"));
                    commonFunction.RndCompare(0.7f, result.x + 56, result.y - 75, result, commonFunction.getImg());
                    if (result.sim > 0.7f) {
                        break;
                    }


                }

                result = mFairy.findPic2(65, 115, 1271, 622, commonFunction.setImg("ygyg6.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.1f, "远古"));
                commonFunction.RndCompare(0.7f, result.x + 56, result.y - 75, result, commonFunction.getImg());


                result = mFairy.findPic2(451, 286, 999, 521, commonFunction.setImg("Blqsb.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领取双倍"));
                if (result.sim > 0.8f && lqsb == 1) {
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                } else if (result.sim > 0.8f && lqsb == 0) {
                    commonFunction.RndCompare(0.8f, 1044, 249, result, commonFunction.getImg());
                }

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


            }
        }
    }

    //跟队封妖
    public void GdFengyao() throws Exception {
        int bj = 0, bj_1;
        int cs_1 = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        int kf = 0, lqsb = 0;
        if (AtFairyConfig.getOption("gdfyls").equals("1")) {
            lqsb = 1;
        }

        bj_1 = bj;
        while (mFairy.condit()) {
            if (bj_1 == bj && bj != 4) {
                cs_1++;
            } else if (bj_1 != bj) {
                cs_1 = 0;
                bj_1 = bj;
                LtLog.e(commonFunction.getLineInfo("标记发生变化"));
            }
            if (cs_1 > 150) {
                cs_1 = 0;
                bj = 0;
                LtLog.e(commonFunction.getLineInfo("标记长时间未发生变化"));
            }
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("跟队封妖中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                gamePublicFunction.init();
                bj = 1;
            }
            if (bj == 1) {
                gamePublicFunction.SetJudgeTask("team");

                result = mFairy.findPic2(201, 68, 352, 127, commonFunction.setImg("Bcreateteam.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一个人"));
                if (result.sim > 0.8f) {
                    gamePublicFunction.BackCity(698, 497, "长安");
                    bj = 2;
                }

                result = mFairy.findPic2(182, 59, 370, 137, commonFunction.setImg("Blkdw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(787, 594, 928, 651, commonFunction.setImg("Bsqdz.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "是队员"));
                    if (result.sim > 0.8f) {
                        bj = 4;
                    } else {
                        LtLog.e(commonFunction.getLineInfo("是队长"));
                        gamePublicFunction.Signout();
                        gamePublicFunction.BackCity(698, 497, "长安");
                        bj = 2;
                    }
                }
            }
            if (bj == 2) {
                ret = gamePublicFunction.mission("mrtz.png");
                if (ret == 0) {
                    break;
                } else if (ret == 1) {
                    result = mFairy.findPic2(521, 157, 634, 204, commonFunction.setImg("20ci.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "20次完成"));
                    if (result.sim > 0.8f) {
                        break;
                    }
                    gamePublicFunction.init();
                    bj = 3;
                }
            }
            if (bj == 3) {
                gamePublicFunction.SetJudgeTask("team");

                result = mFairy.findPic2(177, 133, 448, 658, commonFunction.setImg("gdWild goblin.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "勾选封妖"));
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic2(result.x - 50, result.y - 27, result.x + 25, result.y + 49, commonFunction.setImg("gdpipei.png"));
                    LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "匹配中"));
                    if (result1.sim > 0.8f) {
                    } else {
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                      /*  result = mFairy.findPic2(604,596,673,659,commonFunction.setImg("duigou.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "跨服的对勾"));
                        if (result.sim>0.8f && kf==1){
                        }if (result.sim>0.8f && kf==0){
                            commonFunction.RndCompare(0.8f, 638, 638, result, commonFunction.getImg());
                        }if (result.sim<0.8f && kf==1){
                            commonFunction.RndCompare(638, 638, "跨服的对勾");
                        }if (result.sim<0.8f && kf==0){
                        }*/
                        commonFunction.RndCompare(1015, 625, "开始匹配");
                    }
                }
                result = mFairy.findPic2(182, 59, 370, 137, commonFunction.setImg("Blkdw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
                commonFunction.RndCompare(0.8f, 1127, 49, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    bj = 4;
                }
            }
            if (bj == 4) {
                Thread.sleep(2000);
                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 40) {
                        gamePublicFunction.Signout();
                        bj = 0;
                    }
                }
                result = mFairy.findPic2(550, 50, 734, 108, commonFunction.setImg("cghd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "常规活动"));
                commonFunction.RndCompare(0.8f, 1127, 46, result, commonFunction.getImg());

                result = mFairy.findPic2(451, 286, 999, 521, commonFunction.setImg("Blqsb.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领取双倍"));
                if (result.sim > 0.8f && lqsb == 1) {
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                } else if (result.sim > 0.8f && lqsb == 0) {
                    commonFunction.RndCompare(0.8f, 1044, 249, result, commonFunction.getImg());
                }

            }

        }
    }

    //跟队副本
    public void GdTeamCopy1(int x, String str) throws Exception {
        int bj = 0, bj_1;
        int cs_1 = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        int kf = 0, lqsb = 0, fbone = 0;
        if (AtFairyConfig.getOption("fbkf").equals("1")) {
            kf = 1;
        }
        if (AtFairyConfig.getOption("gdfbls").equals("1")) {
            lqsb = 1;
        }
        if (AtFairyConfig.getOption("fbone").equals("1")) {
            fbone = 1;
        }
        bj_1 = bj;
        while (mFairy.condit()) {
            if (bj_1 == bj && bj != 4) {
                cs_1++;
            } else if (bj_1 != bj) {
                cs_1 = 0;
                bj_1 = bj;
                LtLog.e(commonFunction.getLineInfo("标记发生变化"));
            }
            if (cs_1 > 150) {
                cs_1 = 0;
                bj = 0;
                LtLog.e(commonFunction.getLineInfo("标记长时间未发生变化"));
            }
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("跟队副本中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                gamePublicFunction.init();
                if (kf == 1) {
                    kf();
                } else {
                    gamePublicFunction.Hyf();
                }

                bj = 1;
            }
            if (bj == 1) {
                gamePublicFunction.SetJudgeTask("team");

                result = mFairy.findPic2(201, 68, 352, 127, commonFunction.setImg("Bcreateteam.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一个人"));
                if (result.sim > 0.8f) {
                    gamePublicFunction.BackCity(698, 497, "长安");
                    bj = 2;
                }

                result = mFairy.findPic2(182, 59, 370, 137, commonFunction.setImg("Blkdw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(787, 594, 928, 651, commonFunction.setImg("Bsqdz.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "是队员"));
                    if (result.sim > 0.8f) {
                        bj = 4;
                    } else {
                        LtLog.e(commonFunction.getLineInfo("是队长"));
                        gamePublicFunction.Signout();
                        gamePublicFunction.BackCity(698, 497, "长安");
                        bj = 2;
                    }
                }
            }
            if (bj == 2) {
                ret = gamePublicFunction.mission("cghdfb.png");
                if (ret == 0) {
                    break;
                } else if (ret == 1) {
                    bj = 3;
                }
            }
            if (bj == 3) {
                result = mFairy.findPic2(commonFunction.setImg("xzfbjm.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "选择副本界面"));
                if (result.sim > 0.8f) {
                    if (x == 1) {
                        commonFunction.RndCompare(513, 178, "英雄战场");
                    } else if (x == 2) {
                        commonFunction.RndCompare(513, 267, "玄武门之变");
                    }

                    commonFunction.RndCompare(818, 590, "立即前往");
                }
                result = mFairy.findPic2(782, 404, 1252, 695, commonFunction.setImg("ddy.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "当队员"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                gamePublicFunction.SetJudgeTask("team");

                result = mFairy.findPic2(177, 133, 448, 658, commonFunction.setImg(str));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "勾选战场"));
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic2(result.x - 50, result.y - 27, result.x + 25, result.y + 49, commonFunction.setImg("gdpipei.png"));
                    LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "匹配中"));
                    if (result1.sim > 0.8f) {
                    } else {
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                  /*      result = mFairy.findPic2(604,596,673,659,commonFunction.setImg("duigou.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "跨服的对勾"));
                        if (result.sim>0.8f && kf==1){
                        }if (result.sim>0.8f && kf==0){
                            commonFunction.RndCompare(0.8f, 638, 638, result, commonFunction.getImg());
                        }if (result.sim<0.8f && kf==1){
                            commonFunction.RndCompare(638, 638, "跨服的对勾");
                        }if (result.sim<0.8f && kf==0){
                        }*/
                        commonFunction.RndCompare(1015, 625, "开始匹配");
                    }
                }
                result = mFairy.findPic2(182, 59, 370, 137, commonFunction.setImg("Blkdw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
                commonFunction.RndCompare(0.8f, 1127, 49, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    bj = 4;
                }
            }
            if (bj == 4) {
                Thread.sleep(1000);
                result = mFairy.findPic2(550, 50, 734, 108, commonFunction.setImg("cghd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "常规活动"));
                commonFunction.RndCompare(0.8f, 1127, 46, result, commonFunction.getImg());

                result = mFairy.findPic2(451, 286, 999, 521, commonFunction.setImg("Blqsb.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领取双倍"));
                if (result.sim > 0.8f && lqsb == 1) {
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                } else if (result.sim > 0.8f && lqsb == 0) {
                    commonFunction.RndCompare(0.8f, 1044, 249, result, commonFunction.getImg());
                }


                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_3 = 0;

                } else {
                    js_3++;
                    if (js_3 > 40) {
                        gamePublicFunction.Signout();
                        bj = 0;
                    }
                }
                js_2++;
                if (js_2 > 10 && fbone == 1) {
                    js_2 = 0;
                    result = mFairy.findPic2(217, 4, 628, 85, commonFunction.setImg("Nricheng1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());

                    result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "打开日程"));
                    commonFunction.RndCompare(0.8f, 36, 102, result, commonFunction.getImg());
                    Thread.sleep(2000);
                    commonFunction.RndCompare(0.8f, 43, 310, result, commonFunction.getImg());

                    result = mFairy.findPic2(550, 50, 734, 108, commonFunction.setImg("cghd.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "常规活动"));
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic2(211, 92, 1086, 692, commonFunction.setImg("cghdfb.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "找到任务副本"));
                        if (result.sim > 0.96f) {
                            if (fbone == 1) {
                                result1 = mFairy.findPic2(result.x + 30, result.y + 56, result.x + 108, result.y + 127, commonFunction.setImg("zghy1.png"));
                                LtLog.e(commonFunction.getLineInfo(result1, 0.1f, "活跃"));
                                if (result1.sim > 0.95f) {
                                    commonFunction.RndCompare(0.8f, result.x + 85, result.y + 97, result, commonFunction.getImg());
                                    Thread.sleep(1000);
                                    result1 = mFairy.findPic2(result.x - 80, result.y + 30, result.x - 51, result.y + 62, commonFunction.setImg("fbyl.png"));
                                    LtLog.e(commonFunction.getLineInfo(result1, 0.1f, "是不是5"));
                                    if (result1.sim > 0.9f) {
                                        gamePublicFunction.Signout1();
                                        return;
                                    }
                                }
                            }
                        } else if (result.sim > 0.8f) {
                            LtLog.e(commonFunction.getLineInfo("任务完成"));
                            gamePublicFunction.Signout();
                            return;
                        }
                    }
                }

                result = mFairy.findPic2(550, 50, 734, 108, commonFunction.setImg("cghd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "常规活动"));
                commonFunction.RndCompare(0.8f, 1127, 46, result, commonFunction.getImg());

                result = mFairy.findPic2(539, 200, 747, 302, commonFunction.setImg("kqbx1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "奖励的箱子"));
                commonFunction.RndCompare(0.8f, 651, 353, result, commonFunction.getImg());
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_1++;
                    if (js_1 >= 5 && fbone == 1) {
                        LtLog.e(commonFunction.getLineInfo("一轮完成"));
                        gamePublicFunction.init();
                        gamePublicFunction.Signout1();
                        break;
                    }
                    Thread.sleep(5000);
                }
                result = mFairy.findPic2(539, 200, 747, 302, commonFunction.setImg("fbjl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "奖励的筛子"));
                commonFunction.RndCompare(0.8f, 834, 479, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    Thread.sleep(10000);
                    js_1++;
                    if (js_1 >= 5 && fbone == 1) {
                        LtLog.e(commonFunction.getLineInfo("一轮完成"));
                        gamePublicFunction.init();
                        gamePublicFunction.Signout1();
                        break;
                    }
                    Thread.sleep(5000);
                }
            }

        }
    }

    // 跨服
    public void kf() throws Exception {
        int bj = 0;
        int err = 0;
        for (int i = 0; i < 10; i++) {
            LtLog.e(commonFunction.getLineInfo("跨服bj=" + bj));
            if (bj == 0) {
                bj = 1;
            }
            if (bj == 1) {
                result = mFairy.findPic2(commonFunction.setImg("yuanfu.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "已经在跨服了"));
                if (result.sim > 0.8f) {
                    break;
                } else {
                    LtLog.e(commonFunction.getLineInfo("不在跨服"));
                    gamePublicFunction.mission2("Hanging.png");
                    bj = 2;
                }
            }

            if (bj == 2) {

                findResult = mFairy.findPic(139,36,674,148,"kfljan.png");
                LtLog.e(mFairy.getLineInfo("跨服练级 sim:"+findResult.sim));
                if(findResult.sim>0.8f){
                    mFairy.onTap(0.8f,findResult,"跨服练级",5000);

                    result = mFairy.findPicRange(commonFunction.setImg("kfljtc.png"), 30);
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "跨服练级弹窗"));
                    commonFunction.RndCompare(0.8f, 403, 489, result, commonFunction.getImg());

                    for (int i1 = 0; i1 < 10; i1++) {
                        findResult = mFairy.findPic(305, 419, 496, 561, "kfok.png");
                        mFairy.onTap(0.8f, findResult, "跨服确定", 2000);
                    }

                }

                Thread.sleep(1000);
                err++;
                if (err > 3) {

                    gamePublicFunction.init();
                    break;
                }
            }

        }
    }

}
