package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.TaskContent;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class TimingActivity extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    GameUtil gameUtil;
    boolean scn = true;

    public TimingActivity(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gameUtil = new GameUtil(mFairy);
        cwlist.add(0);
        cwlist.add(9);
        cwlist.add(12);
        cwlist.add(14);
        cwlist.add(16);
        cwlist.add(18);
        cwlist.add(21);
        cwlist.add(23);

        bhddlist.add(11);
        bhddlist.add(12);
        bhddlist.add(13);
        bhddlist.add(14);
        bhddlist.add(15);
        bhddlist.add(16);
        bhddlist.add(17);
        bhddlist.add(18);
        bhddlist.add(19);

        gnlist.add(12);
        gnlist.add(20);

        jxlist.add(10);
        jxlist.add(14);
        jxlist.add(18);
        jxlist.add(22);
        if (!AtFairyConfig.getOption("bhweek").equals("")) {
            bhweek = strSplit(AtFairyConfig.getOption("bhweek"));
        }
        if (!AtFairyConfig.getOption("bhtime").equals("")) {
            bhtime = strSplit(AtFairyConfig.getOption("bhtime"));
        }
    }

    public void inOperation() throws Exception {
        result = mFairy.findPic("Over drawing.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("过图中"));
            Thread.sleep(5000);
            mFairy.initMatTime();
            err = 0;
        }
        result = mFairy.findPic(new String[]{"nogang.png", "nogang1.png"});
        if (result.sim > 0.7f) {
            LtLog.e(mFairy.getLineInfo("没有帮会"));
            Thread.sleep(2000);
            setTaskEnd();
            return;
        }
        gameUtil.zh();
        gameUtil.gdFBTeam();

    }

    public void rank() throws Exception {

        result = mFairy.findPic("rank1.png");
        mFairy.onTap(0.8f, result, 162, 158, 187, 172, "", 1000);

        result = mFairy.findPic("rank2.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(202, 331, 236, 344, "", 500);
            mFairy.onTap(202, 331, 236, 344, "", 500);
            mFairy.onTap(202, 331, 236, 344, "接受", 500);
            mFairy.onTap(1177, 56, 1192, 68, "", 1000);
            gameUtil.callToFollow();
        }
    }

    List<Integer> cwlist = new ArrayList<>();
    List<Integer> bhddlist = new ArrayList<>();
    List<Integer> gnlist = new ArrayList<>();
    List<Integer> jxlist = new ArrayList<>();

    int scn_0 = 0, scn_15 = 0, scn_30 = 0, kj = 0, ssj = 0;
    int ls8 = 0, ls9 = 0;
    boolean bhdd = true, bhdzz = true;
    ControlSplit bhweek = null, bhtime = null;

    public int timingActivity() throws Exception {
        int h = mFairy.dateHour();
        int m = mFairy.dateMinute();
        int w = mFairy.week();

        //帮花大作战
        if (bhweek != null) {
            if (AtFairyConfig.getOption("4837").equals("1") && w == bhweek.count && h == bhtime.h && m >= bhtime.m && bhdzz) {
                bhdzz();
                bhdzz = false;
            }
        }

        //吉星高照
        if (AtFairyConfig.getOption("jxgz").equals("1") && (w == 1 || w == 3 || w == 5) && jxlist.size() != 0) {
            if (jxlist.contains(h)) {
                if (m >= 27) {
                    jixing();
                    jxlist.remove(jxlist.indexOf(h));
                    return 1;
                }
            }
        }

        //采薇
        if (AtFairyConfig.getOption("cw").equals("1") && (w == 2 || w == 4 || w == 6) && cwlist.size() != 0) {
            if (cwlist.contains(h)) {
                if (m >= 27) {
                    mined();
                    cwlist.remove(cwlist.indexOf(h));
                    return 1;
                }
            }
        }

        //帮会大盗
        if (AtFairyConfig.getOption("bhdd").equals("1") && (w == 1 || w == 3 || w == 5) && bhddlist.size() != 0) {
            if (bhddlist.contains(h)) {
                if (m >= 57) {
                    bhdd = false;
                    gangRobber();
                    bhddlist.remove(bhddlist.indexOf(h));
                    return 1;
                }

                if (m < 20 && h != 11 && bhdd) {
                    bhdd = false;
                    gangRobber();
                    return 1;
                }
            }
        }

        //守财奴
        if (AtFairyConfig.getOption("scn").equals("1") && h >= 13 && scn) {//scn 判断是否继续守财

            if (m >= 45) {
                scn_0 = 0;
                scn_15 = 0;
                scn_30 = 0;
            }

            if (((m >= 0 && m < 10) || m >= 57) && scn_0 == 0) {
                miser("兰若寺", 1);
                scn_0 = 1;
                return 1;
            }

            if (m >= 12 && m < 25 && scn_15 == 0) {
                miser("黑风洞", 16);
                scn_15 = 1;
                return 1;
            }

            if (m >= 27 && m < 45 && scn_30 == 0) {
                miser("兰若地宫", 31);
                scn_30 = 1;
                return 1;
            }
        }

        //关宁
        if (AtFairyConfig.getOption("4691").equals("1") && w != 2 && w != 4 && gnlist.size() != 0) {
            if (gnlist.contains(h) && m >= 1) {
                guanNing();
                gnlist.remove(gnlist.indexOf(h));
                return 1;
            }
        }

        //高昌
        if (AtFairyConfig.getOption("gc").equals("1") && (w == 1 || w == 3 || w == 4) && h == 21 && m >= 31 && m <= 45) {
            gaochang();
            return 1;
        }

        //行酒令
        if (AtFairyConfig.getOption("xjl").equals("1") && (w == 4 || w == 6) && h == 19 && m >= 31 && m <= 45) {
            gameUtil.callToFollow();
            alcohol();
            return 1;
        }
        //科举
        if (AtFairyConfig.getOption("kjxs").equals("1") && h >= 18 && w != 6 && w != 7 && kj == 0) {
            local();
            kj = 1;
        }
        //十世镜
        if (AtFairyConfig.getOption("ssj").equals("1") && h >= 13 && h < 15 && m > 5 && w == 7 && ssj == 0) {
            ssj();
            ssj = 1;
        }

        //帮会联赛
        if (AtFairyConfig.getOption("bhls").equals("1")) {
            if (w == 2) {
                if (h == 20 && ls8 == 0) {
                    leagueMatch();
                    ls8 = 1;
                    return 1;
                }

                if (h == 21 && m >= 10 && ls9 == 0) {
                    leagueMatch();
                    ls9 = 1;
                    return 1;
                }
            }

            if (w == 4) {
                if (h == 20 && ls8 == 0) {
                    leagueMatch();
                    ls8 = 1;
                    return 1;
                }
            }
        }

        return 0;
    }

    public int timingActivity1() throws Exception {
        int h = mFairy.dateHour();
        int m = mFairy.dateMinute();
        int w = mFairy.week();
        //关宁
        if (AtFairyConfig.getOption("4777").equals("1") && w != 2 && w != 4 && gnlist.size() != 0) {
            if (gnlist.contains(h) && m >= 1) {
                gameUtil.cancelFollowing();
                guanNing();
                gnlist.remove(gnlist.indexOf(h));
                return 1;
            }
        }

        //高昌
        if (AtFairyConfig.getOption("gc").equals("1") && (w == 1 || w == 3 || w == 4) && h == 21 && m >= 31 && m <= 45) {
            gameUtil.cancelFollowing();
            gaochang();
            return 1;
        }

        //行酒令
        if (AtFairyConfig.getOption("xjl").equals("1") && (w == 4 || w == 6) && h == 19 && m >= 31 && m <= 45) {
            gameUtil.cancelFollowing();
            alcohol();
            return 1;
        }

        //科举
        if (AtFairyConfig.getOption("kjxs").equals("1") && h >= 18 && w != 6 && w != 7 && kj == 0) {
            gameUtil.cancelFollowing();
            local();
            kj = 1;
        }
        //帮会联赛
        if (AtFairyConfig.getOption("bhls").equals("1")) {
            if (w == 2) {
                if (h == 20 && ls8 == 0) {
                    leagueMatch();
                    ls8 = 1;
                    return 1;
                }

                if (h == 21 && m >= 10 && ls9 == 0) {
                    leagueMatch();
                    ls9 = 1;
                    return 1;
                }
            }

            if (w == 4) {
                if (h == 20 && ls8 == 0) {
                    leagueMatch();
                    ls8 = 1;
                    return 1;
                }
            }
        }
        //帮花大作战
        if (AtFairyConfig.getOption("4839").equals("1") && w == bhweek.count && h == bhtime.h && m >= bhtime.m && bhdzz) {
            bhdzz();
            bhdzz = false;
        }
        return 0;
    }

    //守财奴带队
    public void miser(final String str, final int min) throws Exception {
        new TimingActivity(mFairy) {

            public void content_0() throws Exception {
                gameUtil.close(1);
                int m = mFairy.dateMinute();
                if (m < min || m >= 55) {
                    LtLog.e(mFairy.getLineInfo("未达到任务时间去组人"));
                } else if (m > min) {
                    result = mFairy.findPic(830, 76, 1125, 173, new String[]{"Miser2.png", "Miser4.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("活动时间内还有守财，去组人"));
                    } else {
                        LtLog.e(mFairy.getLineInfo("指定时间内没有守财了结束"));
                        setTaskEnd();
                        return;
                    }
                }
                gameUtil.callToFollow();
                gameUtil.goCity(str);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(927, 177, 1276, 708, "Convenientteam.png");
                mFairy.onTap(0.8f, result, "右侧便捷组队", Sleep);

                result = mFairy.findPic("Openteam.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Openteam1.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Convenientinterface.png");
                mFairy.onTap(0.8f, result, 885, 624, 905, 638, "便捷组队界面,是一个人开始创建队伍匹配", 2000);


                result = mFairy.findPic("teaminterface.png");
                mFairy.onTap(0.8f, result, 1163, 167, 1164, 168, "队伍界面已经有队伍了开始切换目标", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(252, 84, 705, 120, "Miser.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("目标为守财奴进行下一步"));
                    result = mFairy.findPic("zdpp.png");
                    mFairy.onTap(0.8f, result, "自动匹配", Sleep);
                    gameUtil.close(1);
                    setTaskName(3);
                    return;
                } else {
                    result = mFairy.findPic("Cancelingmatch.png");
                    mFairy.onTap(0.8f, result, "需要切换目标,取消匹配", Sleep);

                    result = mFairy.findPic("zd1.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("调整目标界面"));

                        result = mFairy.findPic(334, 132, 639, 545, "Miser1.png");
                        mFairy.onTap(0.8f, result, "目标为守财奴进行下一步", Sleep);
                        if (result.sim > 0.8f) {
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.onTap(0.8f, result, 786, 561, 787, 562, "确定", 2000);
                            result = mFairy.findPic("zdpp.png");
                            mFairy.onTap(0.8f, result, "自动匹配", Sleep);
                            gameUtil.close(1);
                            setTaskName(3);
                            return;
                        } else {
                            mFairy.taskSlid(err, new int[]{4, 6, 8}, 3, 452, 490, 471, 182, 200, 1000);
                            mFairy.sleep(2000);
                        }
                    } else {
                        result = mFairy.findPic("teaminterface.png");
                        mFairy.onTap(0.8f, result, 1163, 167, 1164, 168, "目标不对，切换目标", Sleep);
                        mFairy.onTap(0.8f, result, 165, 116, 166, 117, "目标不对，切换目标", 2000);
                    }
                }
            }

            public void content_3() throws Exception {
                int m = mFairy.dateMinute();
                if (m >= (min + 12) && m < 50) {
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(830, 76, 1125, 173, new String[]{"Miser2.png", "Miser4.png"});
                if (result.sim < 0.8f && m > min && m < 50) {
                    LtLog.e(mFairy.getLineInfo("还没组到人就没有守财了"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("teaminterface.png");
                mFairy.onTap(0.8f, result, 1178, 52, 1191, 66, "关闭组队界面", Sleep);

                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);
                gameUtil.apply();
                int mannum = 0;
                result = mFairy.findPic(196, 197, 242, 253, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }

                result = mFairy.findPic(195, 257, 242, 315, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }

                result = mFairy.findPic(194, 315, 241, 374, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }
                result = mFairy.findPic(194, 376, 243, 432, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }
                if (mannum > 1) {
                    gameUtil.callToFollow();
                    LtLog.e(mFairy.getLineInfo("队员齐了开始任务"));
                    setTaskName(4);
                    return;
                }
                if (timekeep(1, 120000, "2分钟招募一下")) {
                    LtLog.e(mFairy.getLineInfo("--------2分钟招募一下"));
                    gameUtil.recruit();
                }
                Thread.sleep(3000);
            }

            public void content_4() throws Exception {

                result = mFairy.findPic("Hangup1.png");
                mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", Sleep);

                int m = mFairy.dateMinute();

                result = mFairy.findPic(830, 76, 1125, 173, new String[]{"Miser2.png", "Miser4.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "守财奴刷新出来了", 2000);
                    result = mFairy.findPic("Fulloftimes.png");
                    if (result.sim > 0.8f) {
                        TimingActivity.this.scn = false;
                        LtLog.e(mFairy.getLineInfo("守财奴次数满了"));
                        setTaskEnd();
                        return;
                    }
                } else {
                    if (m > min && m < 50) {
                        err++;
                        if (err > 10) {
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic(786, 180, 946, 260, "Getrich.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "守财奴前往", 5000);
                    setTaskName(5);
                }
            }

            public void content_5() throws Exception {

                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 10) {
                    LtLog.e(mFairy.getLineInfo("到达守财奴地点"));

                    result = mFairy.findPic(927, 177, 1276, 708, "Righttreasure.png");
                    mFairy.onTap(0.8f, result, "交出宝藏", 2000);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(927, 177, 1276, 708, "Righttreasure.png");
                        if (result.sim > 0.8f) {
                            Thread.sleep(7000);
                            mFairy.onTap(0.8f, result, "交出宝藏", 2000);
                        }
                    } else {
                        setTaskName(4);
                        return;
                    }

                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);

                    gameUtil.cancelFollowing();
                    setTaskName(6);
                    return;
                }

                result = mFairy.findPic(927, 177, 1276, 708, "Righttreasure.png");
                mFairy.onTap(0.8f, result, "交出宝藏", 2000);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(927, 177, 1276, 708, "Righttreasure.png");
                    if (result.sim > 0.8f) {
                        Thread.sleep(7000);
                        mFairy.onTap(0.8f, result, "交出宝藏", 2000);
                    }

                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);

                    gameUtil.callToFollow();
                    gameUtil.cancelFollowing();

                    setTaskName(6);
                    return;
                }
                Thread.sleep(1000);
            }

            public void content_6() throws Exception {
                if (overtime(15, 4)) {
                    gameUtil.callToFollow();
                    return;
                }
                Thread.sleep(500);

                result = mFairy.findPic("Hangup.png");
                mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);

                result = mFairy.findPic("Miser3.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("守财奴boss"));
                    err = 0;
                }
                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);
            }
        }.taskContent(mFairy, "守财奴带队");
    }

    //采薇带队
    public void mined() throws Exception {
        new TimingActivity(mFairy) {

            public void content_0() throws Exception {
                gameUtil.close(1);
                int m = mFairy.dateMinute();
                if (m < 31) {
                    LtLog.e(mFairy.getLineInfo("未达到任务时间去组人"));
                } else {
                    result = mFairy.findPic(830, 76, 1125, 173, "Mined2.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("活动时间内还有采薇，去组人"));
                    } else {
                        LtLog.e(mFairy.getLineInfo("指定时间内没有采薇了结束"));
                        setTaskEnd();
                        return;
                    }
                }
                gameUtil.callToFollow();
                gameUtil.goCity("忘川");
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(927, 177, 1276, 708, "Convenientteam.png");
                mFairy.onTap(0.8f, result, "右侧便捷组队", Sleep);

                result = mFairy.findPic("Openteam.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Openteam1.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Convenientinterface.png");
                mFairy.onTap(0.8f, result, 885, 624, 905, 638, "便捷组队界面,是一个人开始创建队伍匹配", 2000);

                result = mFairy.findPic("teaminterface.png");
                mFairy.onTap(0.8f, result, 1163, 167, 1164, 168, "队伍界面已经有队伍了开始切换目标", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                if (overtime(10, 0)) return;

                result = mFairy.findPic(252, 84, 705, 120, "Mined1.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("目标为采薇进行下一步"));
                    result = mFairy.findPic("zdpp.png");
                    mFairy.onTap(0.8f, result, "自动匹配", Sleep);
                    gameUtil.close(1);
                    setTaskName(3);
                    return;

                } else {

                    result = mFairy.findPic("Cancelingmatch.png");
                    mFairy.onTap(0.8f, result, "需要切换目标,取消匹配", Sleep);

                    result = mFairy.findPic("zd1.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("调整目标界面"));

                        result = mFairy.findPic(334, 132, 639, 545, "Mined.png");
                        mFairy.onTap(0.8f, result, "目标为采薇进行下一步", Sleep);
                        if (result.sim > 0.8f) {
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.onTap(0.8f, result, 786, 561, 787, 562, "确定", 2000);

                            result = mFairy.findPic("zdpp.png");
                            mFairy.onTap(0.8f, result, "自动匹配", Sleep);

                            gameUtil.close(1);

                            setTaskName(3);
                            return;
                        } else {
                            mFairy.taskSlid(err, new int[]{4, 6, 8}, 3, 452, 490, 471, 182, 200, 1000);
                            Thread.sleep(2000);
                        }
                    } else {
                        result = mFairy.findPic("teaminterface.png");
                        mFairy.onTap(0.8f, result, 1163, 167, 1164, 168, "目标不对，切换目标", Sleep);
                        mFairy.onTap(0.8f, result, 165, 116, 166, 117, "目标不对，切换目标", 2000);
                    }
                }
            }

            public void content_3() throws Exception {
                int m = mFairy.dateMinute();
                if (m < 20) {
                    setTaskEnd();
                    return;
                }
                if (m > 31) {
                    result = mFairy.findPic(830, 76, 1125, 173, "Mined2.png");
                    if (result.sim < 0.8f) {
                        LtLog.e(mFairy.getLineInfo("还没组到人就没有采薇了"));
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("teaminterface.png");
                mFairy.onTap(0.8f, result, 1178, 52, 1191, 66, "关闭组队界面", Sleep);

                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                gameUtil.apply();

                int mannum = 0;
                result = mFairy.findPic(196, 197, 242, 253, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }

                result = mFairy.findPic(195, 257, 242, 315, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }

                result = mFairy.findPic(194, 315, 241, 374, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }
                result = mFairy.findPic(194, 376, 243, 432, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }
                if (mannum > 1) {
                    gameUtil.callToFollow();
                    LtLog.e(mFairy.getLineInfo("人满了出发"));
                    setTaskName(4);
                    return;
                }

                if (timekeep(1, 120000, "2分钟招募一下")) {
                    LtLog.e(mFairy.getLineInfo("--------2分钟招募一下"));
                    gameUtil.recruit();
                }
                Thread.sleep(3000);
            }

            public void content_4() throws Exception {

                result = mFairy.findPic("Hangup1.png");
                mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", Sleep);
                int m = mFairy.dateMinute();
                result = mFairy.findPic(830, 76, 1125, 173, new String[]{"Mined2.png", "Mined4.png"});
                mFairy.onTap(0.8f, result, "采薇刷新出来了", 2000);
                if (result.sim > 0.8f) {
                } else {
                    if (m > 31) {
                        err++;
                        if (err > 10) {
                            setTaskEnd();
                            return;
                        }
                    }
                }
                result = mFairy.findPic(786, 180, 946, 260, "Getrich.png");
                mFairy.onTap(0.8f, result, "采薇前往", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(5);
                }
            }

            public void content_5() throws Exception {
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 10) {
                    LtLog.e(mFairy.getLineInfo("到达采薇地点"));
                    result = mFairy.findPic(927, 177, 1276, 708, "Mined3.png");
                    mFairy.onTap(0.8f, result, "右侧采薇", 10000);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(927, 177, 1276, 708, "Mined3.png");
                        if (result.sim > 0.8f) {
                            Thread.sleep(7000);
                            mFairy.onTap(0.8f, result, "右侧采薇", 10000);
                        }
                    } else {
                        setTaskName(4);
                        return;
                    }
                    setTaskName(6);
                    return;
                }
                result = mFairy.findPic(927, 177, 1276, 708, "Mined3.png");
                mFairy.onTap(0.8f, result, "右侧采薇", 10000);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(927, 177, 1276, 708, "Mined3.png");
                    if (result.sim > 0.8f) {
                        Thread.sleep(7000);
                        mFairy.onTap(0.8f, result, "右侧采薇", 1000);

                        for (int i = 0; i < 5; i++) {
                            mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1);
                            result = mFairy.findPic(573, 466, 880, 590, "jj2.png");
                            if (result.sim > 0.85f) {
                                setTaskEnd();
                                return;
                            }
                        }
                        Thread.sleep(10000);
                    }
                    setTaskName(6);
                    return;
                }
                Thread.sleep(1000);
            }

            public void content_6() throws Exception {
                if (overtime(15, 4)) {
                    gameUtil.callToFollow();
                    return;
                }
                Thread.sleep(500);

                result = mFairy.findPic(642, 8, 1273, 318, new String[]{"Replica.png", "Replica1.png"});
                if (result.sim > 0.8f) {

                    mFairy.initMatTime();
                    err = 0;

                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "副本中开启挂机", Sleep);
                }

                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);
                Thread.sleep(1000);
            }
        }.taskContent(mFairy, "采薇带队");
    }

    //吉星高照带队
    public void jixing() throws Exception {
        new TimingActivity(mFairy) {

            public void content_0() throws Exception {
                // gameUtil.close(1);
                /*int m = mFairy.dateMinute();
                if (m<30) {
                    LtLog.e(mFairy.getLineInfo("未达到任务时间去组人"));
                }else {
                    result = mFairy.findPic(830, 76, 1125, 173, "jixing.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("活动时间内还有吉星高照，去组人"));
                    }else {
                        LtLog.e(mFairy.getLineInfo("指定时间内没有吉星高照了结束"));
                        setTaskEnd();
                        return;
                    }
                }*/
                int h = mFairy.dateHour();
                gameUtil.callToFollow();
                if (h == 10) {
                    gameUtil.goCity("神机营");
                } else if (h == 14) {
                    gameUtil.goCity("逍遥观");
                } else if (h == 18) {
                    gameUtil.goCity("万妖宫");
                } else if (h == 22) {
                    gameUtil.goCity("天工阁");
                }
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(927, 177, 1276, 708, "Convenientteam.png");
                mFairy.onTap(0.8f, result, "右侧便捷组队", Sleep);

                result = mFairy.findPic("Openteam.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Openteam1.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Convenientinterface.png");
                mFairy.onTap(0.8f, result, 885, 624, 905, 638, "便捷组队界面,是一个人开始创建队伍匹配", 2000);


                result = mFairy.findPic("teaminterface.png");
                mFairy.onTap(0.8f, result, 1163, 167, 1164, 168, "队伍界面已经有队伍了开始切换目标", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(252, 84, 705, 120, "jixing1.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("目标为吉星高照进行下一步"));
                    result = mFairy.findPic("zdpp.png");
                    mFairy.onTap(0.8f, result, "自动匹配", Sleep);
                    gameUtil.close(1);
                    setTaskName(3);
                    return;
                } else {
                    result = mFairy.findPic("Cancelingmatch.png");
                    mFairy.onTap(0.8f, result, "需要切换目标,取消匹配", Sleep);

                    result = mFairy.findPic("zd1.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("调整目标界面"));
                        result = mFairy.findPic(334, 132, 639, 545, "jixing2.png");
                        mFairy.onTap(0.8f, result, "目标为吉星高照进行下一步", Sleep);
                        if (result.sim > 0.8f) {
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.onTap(0.8f, result, 786, 561, 787, 562, "确定", 2000);
                            result = mFairy.findPic("zdpp.png");
                            mFairy.onTap(0.8f, result, "自动匹配", Sleep);
                            gameUtil.close(1);
                            setTaskName(3);
                            return;
                        } else {
                            mFairy.taskSlid(err, new int[]{4, 6, 8}, 3, 452, 490, 471, 182, 200, 1000);
                            Thread.sleep(2000);
                        }
                    } else {
                        result = mFairy.findPic("teaminterface.png");
                        mFairy.onTap(0.8f, result, 1163, 167, 1164, 168, "目标不对，切换目标", Sleep);
                        mFairy.onTap(0.8f, result, 165, 116, 166, 117, "目标不对，切换目标", 2000);
                    }
                }
            }

            public void content_3() throws Exception {
                int m = mFairy.dateMinute();
                if (m < 20 || m > 52) {
                    setTaskEnd();
                    return;
                }
              /*  if (m > 30) {
                    result = mFairy.findPic(830, 76, 1125, 173, "jixing.png");
                    if (result.sim < 0.8f) {
                        LtLog.e(mFairy.getLineInfo("还没组到人就没有吉星高照了"));
                        setTaskEnd();
                        return;
                    }
                }*/

                result = mFairy.findPic("teaminterface.png");
                mFairy.onTap(0.8f, result, 1178, 52, 1191, 66, "关闭组队界面", Sleep);

                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                gameUtil.apply();
                int mannum = 0;
                result = mFairy.findPic(196, 197, 242, 253, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }

                result = mFairy.findPic(195, 257, 242, 315, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }

                result = mFairy.findPic(194, 315, 241, 374, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }
                result = mFairy.findPic(194, 376, 243, 432, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }
                if (mannum > 1) {
                    gameUtil.callToFollow();
                    LtLog.e(mFairy.getLineInfo("人满了出发"));
                    setTaskName(5);
                    return;
                }
                if (timekeep(1, 120000, "2分钟招募一下")) {
                    LtLog.e(mFairy.getLineInfo("--------2分钟招募一下"));
                    gameUtil.recruit();
                }
                Thread.sleep(3000);
            }

            public void content_4() throws Exception {
                result = mFairy.findPic("Hangup1.png");
                mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", Sleep);

                int m = mFairy.dateMinute();
                result = mFairy.findPic(830, 76, 1125, 173, "jixing.png");
                if (result.sim > 0.8f) {
                    setTaskName(5);
                    return;
                } else {
                    if (m > 52 || m < 20) {
                        err++;
                        if (err > 10) {
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic(444, 616, 831, 711, "jixingxy.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("在逍遥观"));
                    for (int i = 0; i < 10; i++) {

                        result = mFairy.findPic("package.png");
                        mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "包裹", 2000);

                        result = mFairy.findPic("Mapinterface.png");
                        mFairy.onTap(0.8f, result, "地图界面", Sleep);

                        result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                        mFairy.onTap(0.8f, result, 1160, 546, 1171, 563, "逍遥观", Sleep);
                        if (result.sim > 0.8f) {
                            break;
                        }
                    }
                    gameUtil.close(0);
                    setTaskName(5);
                    return;
                }

                result = mFairy.findPic(444, 616, 831, 711, "jixingtgg.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("在天工阁"));
                    for (int i = 0; i < 10; i++) {

                        result = mFairy.findPic("package.png");
                        mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "包裹", 2000);

                        result = mFairy.findPic("Mapinterface.png");
                        mFairy.onTap(0.8f, result, "地图界面", Sleep);

                        result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                        mFairy.onTap(0.8f, result, 290, 159, 303, 178, "天工阁", Sleep);
                        if (result.sim > 0.8f) {
                            break;
                        }
                    }
                    gameUtil.close(0);
                    setTaskName(5);
                    return;
                }

                result = mFairy.findPic(444, 616, 831, 711, "jxwy.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("在万妖宫"));
                    for (int i = 0; i < 10; i++) {

                        result = mFairy.findPic("package.png");
                        mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "包裹", 2000);

                        result = mFairy.findPic("Mapinterface.png");
                        mFairy.onTap(0.8f, result, "地图界面", Sleep);

                        result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                        mFairy.onTap(0.8f, result, 639, 237, 650, 253, "万妖宫", Sleep);
                        if (result.sim > 0.8f) {
                            break;
                        }
                    }
                    gameUtil.close(0);
                    setTaskName(5);
                    return;
                }
                result = mFairy.findPic(444, 616, 831, 711, "jxsj.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("在神机营"));
                    for (int i = 0; i < 10; i++) {

                        result = mFairy.findPic("package.png");
                        mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "包裹", 2000);

                        result = mFairy.findPic("Mapinterface.png");
                        mFairy.onTap(0.8f, result, "地图界面", Sleep);

                        result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                        mFairy.onTap(0.8f, result, 902, 171, 914, 185, "神机营", Sleep);
                        if (result.sim > 0.8f) {
                            break;
                        }
                    }
                    gameUtil.close(0);
                    setTaskName(5);
                    return;
                }
                result = mFairy.findPic(444, 616, 831, 711, "jxkl.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("在昆仑"));
                    for (int i = 0; i < 10; i++) {

                        result = mFairy.findPic("package.png");
                        mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "包裹", 2000);

                        result = mFairy.findPic("Mapinterface.png");
                        mFairy.onTap(0.8f, result, "地图界面", Sleep);

                        result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                        mFairy.onTap(0.8f, result, 215, 339, 231, 354, "昆仑", Sleep);
                        if (result.sim > 0.8f) {
                            break;
                        }
                    }
                    gameUtil.close(0);
                    setTaskName(5);
                    return;
                }


            }

            public void content_5() throws Exception {
                int m = mFairy.dateMinute();
                result = mFairy.findPic(830, 76, 1125, 173, "jixing.png");
                mFairy.onTap(0.8f, result, "吉星刷新出来了", 2000);
                if (result.sim > 0.8f) {
                } else {
                    if (m > 52 || m < 20) {
                        err++;
                        if (err > 10) {
                            setTaskEnd();
                            return;
                        }
                    }
                }
                result = mFairy.findPic(786, 180, 946, 260, "Getrich.png");
                mFairy.onTap(0.8f, result, "吉星前往", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(6);
                }
            }

            public void content_6() throws Exception {
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 5) {
                    LtLog.e(mFairy.getLineInfo("到达吉星地点"));
                    result = mFairy.findPic(927, 177, 1276, 708, "rightjx.png");
                    mFairy.onTap(0.8f, result, "右侧吉星", 10000);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(927, 177, 1276, 708, "rightjx.png");
                        if (result.sim > 0.8f) {
                            Thread.sleep(7000);
                            mFairy.onTap(0.8f, result, "右侧吉星", 10000);
                        }
                    } else {
                        setTaskName(4);
                        return;
                    }
                    setTaskName(7);
                    return;
                }
                result = mFairy.findPic(927, 177, 1276, 708, "rightjx.png");
                mFairy.onTap(0.8f, result, "右侧吉星", 10000);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(927, 177, 1276, 708, "rightjx.png");
                    if (result.sim > 0.8f) {
                        Thread.sleep(7000);
                        mFairy.onTap(0.8f, result, "右侧吉星", 10000);
                    }
                    setTaskName(7);
                    return;
                }
                Thread.sleep(1000);
            }

            public void content_7() throws Exception {
                if (overtime(10, 4)) {
                    gameUtil.callToFollow();
                    return;
                }
                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    err = 0;
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "副本中开启挂机", Sleep);
                }
                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);

            }
        }.taskContent(mFairy, "吉星高照带队");
    }

    //关宁
    public void guanNing() throws Exception {
        new TimingActivity(mFairy) {

            public void inOperation() throws Exception {
                result = mFairy.findPic("Over drawing.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("过图中"));
                    mFairy.initMatTime();
                    err = 0;
                }

                result = mFairy.findPic(408, 264, 749, 372, "guanning2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(751, 423, 805, 439, "即将开战", 1000);
                }

                int h = mFairy.dateHour();
                if (h >= 13 && h < 20) {
                    setTaskEnd();
                    return;
                }
                if (h >= 21) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(428, 253, 860, 383, "guanning1.png");
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("gnsb").equals("1")) {
                        mFairy.onTap(771, 422, 799, 437, "关宁领双", 2000);
                    } else {
                        mFairy.onTap(472, 421, 492, 432, "取消", 2000);
                    }
                }
            }

            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("guanNingtask.png", 1);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    Thread.sleep(2000);
                    setTaskName(3);
                }
            }

            int gnnum;

            public void content_3() throws Exception {
                if (overtime(10, 0)) return;
                gnnum = 1;


                result = mFairy.findPic(921, 211, 1061, 278, "signup.png");
                mFairy.onTap(0.8f, result, "报名", 3000);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(921, 211, 1061, 278, "signup.png");
                    if (result.sim < 0.8f) {
                        LtLog.e(mFairy.getLineInfo("gnnum++"));
                        gnnum++;
                    }
                }

                result = mFairy.findPic(921, 286, 1061, 340, "signup.png");
                mFairy.onTap(0.8f, result, "报名", 3000);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(921, 286, 1061, 340, "signup.png");
                    if (result.sim < 0.8f) {
                        LtLog.e(mFairy.getLineInfo("gnnum++"));
                        gnnum++;
                    }
                }

                result = mFairy.findPic(922, 349, 1063, 406, "signup.png");
                mFairy.onTap(0.8f, result, "报名", 3000);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(922, 349, 1063, 406, "signup.png");
                    if (result.sim < 0.8f) {
                        LtLog.e(mFairy.getLineInfo("gnnum++"));
                        gnnum++;
                    }
                }
                result = mFairy.findPic(919, 413, 1062, 473, "signup.png");
                mFairy.onTap(0.8f, result, "报名", 3000);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(919, 413, 1062, 473, "signup.png");
                    if (result.sim < 0.8f) {
                        LtLog.e(mFairy.getLineInfo("gnnum++"));
                        gnnum++;
                    }
                }
                result = mFairy.findPic(918, 482, 1065, 540, "signup.png");
                mFairy.onTap(0.8f, result, "报名", 3000);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(918, 482, 1065, 540, "signup.png");
                    if (result.sim < 0.8f) {
                        LtLog.e(mFairy.getLineInfo("gnnum++"));
                        gnnum++;
                    }
                }

                LtLog.e(mFairy.getLineInfo("报名了【" + gnnum + "】场"));
                LtLog.e(mFairy.getLineInfo("报名了【" + gnnum + "】场"));
                LtLog.e(mFairy.getLineInfo("报名了【" + gnnum + "】场"));
                setTaskName(4);
            }

            public void content_4() throws Exception {

                if (gnnum == 0) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("guanningwithin.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("关宁内"));
                    mFairy.onTap(0.8f, result, 1225, 462, 1239, 473, "技能1", Sleep);
                    mFairy.onTap(0.8f, result, 1108, 459, 1132, 477, "技能2", Sleep);
                    mFairy.onTap(0.8f, result, 1025, 535, 1043, 548, "技能3", Sleep);
                    mFairy.onTap(0.8f, result, 1019, 643, 1036, 661, "技能4", Sleep);
                    mFairy.onTap(0.8f, result, 1147, 573, 1162, 587, "技能5", Sleep);
                    mFairy.onTap(0.8f, result, 1238, 590, 1254, 600, "绝技1", Sleep);
                    mFairy.onTap(0.8f, result, 1164, 664, 1175, 681, "绝技2", Sleep);
                }

                if (picCount(0.8f, result, "关宁上高地") > 7) {
                    mFairy.onTap(1197, 84, 1198, 85, "点开地图上高地", 10);
                }

                result = mFairy.findPic(198, 39, 304, 120, "Mapinterface.png");
                mFairy.onTap(0.8f, result, 666, 343, 667, 344, "选择城市", 5000);
                mFairy.onTap(0.8f, result, 1206, 27, 1220, 42, "关闭界面", Sleep);

                result = mFairy.findPic("guanningintask.png");
                mFairy.onTap(0.8f, result, 1090, 82, 1091, 83, "关宁任务", Sleep);

                result = mFairy.findPic("guanningstop.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("关宁结束了"));

                    for (int i = 0; i < 15; i++) {

                        Thread.sleep(1000);

                        result = mFairy.findPic(408, 264, 749, 372, "guanning2.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(751, 423, 805, 439, "即将开战", 1000);
                            break;
                        }

                        result = mFairy.findPic("guanningstop.png");
                        if (result.sim > 0.8f) {
                            if (AtFairyConfig.getOption("gnsb").equals("1")) {
                                mFairy.onTap(1017, 634, 1044, 639, "关宁领双", 2000);
                                mFairy.onTap(799, 425, 800, 426, "确定关宁领双", 2000);
                            }
                        }

                        result = mFairy.findPic(428, 253, 860, 383, "guanning1.png");
                        if (result.sim > 0.8f) {
                            if (AtFairyConfig.getOption("gnsb").equals("1")) {
                                mFairy.onTap(771, 422, 799, 437, "关宁领双 - 弹框", 2000);
                            } else {
                                mFairy.onTap(472, 421, 492, 432, "取消", 2000);
                            }
                        }
                    }

                    gnnum = gnnum - 1;
                    LtLog.e(mFairy.getLineInfo("还剩【" + gnnum + "】场"));
                    //gameUtil.close(0);

                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png"});
                        mFairy.onTap(0.8f, result, "叉子", Sleep);
                    }
                }

                Thread.sleep(1000);
            }
        }.taskContent(mFairy, "关宁中");
    }

    //帮会大盗
    public void gangRobber() throws Exception {
        new TimingActivity(mFairy) {

            int berr;
            int fbJudge;
            boolean fbBool;

            public void create() throws Exception {
                super.create();
                berr = 0;
                fbJudge = 0;
                fbBool = false;
            }

            public void inOperation() throws Exception {
                super.inOperation();
                rank();
            }

            public void content_0() throws Exception {
                int m = mFairy.dateMinute();
                gameUtil.callToFollow();
                gameUtil.goCity("帮会");
                if (m >= 0 && m < 50) {
                    LtLog.e(mFairy.getLineInfo("时间到了看下还有没有大盗"));
                    for (int i = 0; i < 5; i++) {

                        result = mFairy.findPic("package.png");
                        mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "包裹", 5000);

                        result = mFairy.findPic("Mapinterface.png");
                        if (result.sim > 0.8f) {
                            Thread.sleep(5000);

                            result = mFairy.findPic(112, 46, 1194, 671, "robber.png");
                            if (result.sim > 0.7f) {
                                LtLog.e(mFairy.getLineInfo("还有大盗去组队"));
                                gameUtil.close(0);
                                setTaskName(1);
                                return;
                            } else {
                                LtLog.e(mFairy.getLineInfo("没有大盗了结束任务"));
                                gameUtil.close(0);
                                setTaskEnd();
                                return;
                            }
                        }
                        Thread.sleep(2000);
                    }
                } else {
                    Thread.sleep(20000);
                    LtLog.e(mFairy.getLineInfo("大盗时间没到先去组队"));
                }
                setTaskName(1);
                gameUtil.kicking("踢人");
            }

            public void content_1() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(927, 177, 1276, 708, "Convenientteam.png");
                mFairy.onTap(0.8f, result, "右侧便捷组队", Sleep);

                result = mFairy.findPic("Openteam.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Openteam1.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Convenientinterface.png");
                mFairy.onTap(0.8f, result, 885, 624, 905, 638, "便捷组队界面,是一个人开始创建队伍匹配", 2000);

                result = mFairy.findPic("teaminterface.png");
                mFairy.onTap(0.8f, result, 1163, 167, 1164, 168, "队伍界面已经有队伍了开始切换目标", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(252, 84, 705, 120, "Gangrobber.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("目标为帮会大盗进行下一步"));
                    result = mFairy.findPic("zdpp.png");
                    mFairy.onTap(0.8f, result, "自动匹配", Sleep);
                    gameUtil.close(1);
                    setTaskName(3);
                    return;
                } else {
                    result = mFairy.findPic("Cancelingmatch.png");
                    mFairy.onTap(0.8f, result, "需要切换目标,取消匹配", Sleep);

                    result = mFairy.findPic("zd1.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("调整目标界面"));
                        result = mFairy.findPic(334, 132, 639, 545, "Gangrobber1.png");
                        mFairy.onTap(0.8f, result, "目标为帮会大盗进行下一步", Sleep);
                        if (result.sim > 0.8f) {
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.onTap(0.8f, result, 786, 561, 787, 562, "确定", 2000);
                            result = mFairy.findPic("zdpp.png");
                            mFairy.onTap(0.8f, result, "自动匹配", Sleep);
                            gameUtil.close(1);
                            setTaskName(3);
                            return;
                        } else {
                            mFairy.taskSlid(err, new int[]{4, 6, 8}, 3, 452, 490, 471, 182, 200, 1000);
                            Thread.sleep(2000);
                        }
                    } else {
                        result = mFairy.findPic("teaminterface.png");
                        mFairy.onTap(0.8f, result, 1163, 167, 1164, 168, "目标不对，切换目标", Sleep);
                        mFairy.onTap(0.8f, result, 165, 116, 166, 117, "目标不对，切换目标", 2000);
                    }
                }
            }

            public void content_3() throws Exception {
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                if (m >= 10 && m < 55 && h != 19) {
                    setTaskEnd();
                    return;
                }
                if (h == 19) {
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);
                }
                if (m >= 20 && m < 55 && h == 19) {
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("teaminterface.png");
                mFairy.onTap(0.8f, result, 1178, 52, 1191, 66, "关闭组队界面", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                gameUtil.apply();

                int mannum = 0;
                result = mFairy.findPic(196, 197, 242, 253, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }

                result = mFairy.findPic(195, 257, 242, 315, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }

                result = mFairy.findPic(194, 315, 241, 374, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }
                result = mFairy.findPic(194, 376, 243, 432, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }
                if (mannum > 1) {
                    gameUtil.callToFollow();
                    LtLog.e(mFairy.getLineInfo("人够了去任务"));
                    setTaskName(4);
                    return;
                }
                if (timekeep(1, 120000, "2分钟招募一下")) {
                    LtLog.e(mFairy.getLineInfo("--------2分钟招募一下"));
                    gameUtil.recruit();
                }

                Thread.sleep(3000);
            }

            public void content_4() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic("Hangup1.png");
                mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", Sleep);


                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                if (result.sim > 0.72f) {
                    setTaskName(6);
                    return;
                }

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "包裹", 2000);

                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();

                result = mFairy.findPic("Mapinterface.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(5000);
                    err = 0;
                    result = mFairy.findPic(112, 46, 1194, 671, "robber.png");
                    mFairy.onTap(0.7f, result, result.x - 18, result.y + 32, result.x - 17, result.y + 33, "地图大盗", 2000);
                    if (result.sim > 0.7f) {
                        berr = 0;
                        fbBool = false;
                        // gameUtil.close(0);
                        setTaskName(6);
                    } else if (result.sim < 0.7f && m > 0 && m < 55 && h != 19) {
                        berr++;
                        if (berr > 2) {
                            LtLog.e(mFairy.getLineInfo("没有大盗了结束任务"));
                            setTaskEnd();
                        }
                        return;
                    } else if (result.sim < 0.7f && m > 0 && m < 55 && h == 19) {
                        gameUtil.close(0);
                        result = mFairy.findPic("Hangup.png");
                        mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);
                        gameUtil.cancelFollowing();
                        while (true) {
                            Thread.sleep(5000);
                            if (mFairy.dateMinute() >= 20) {
                                setTaskEnd();
                                return;
                            }
                        }
                    } else {
                        gameUtil.close(0);
                    }
                }
            }

            public void content_6() throws Exception {
                if (overtime(10, 4)) {
                    gameUtil.callToFollow();
                    return;
                }

                Thread.sleep(1000);

                result = mFairy.findPic(927, 177, 1276, 708, "rGangrobber.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "右侧大盗", 10000);
                    err = 0;
                }

               /* result = mFairy.findPic(305, 2, 1262, 701, "Gangrobber2.png");
                mFairy.onTap(0.65f, result, result.x + 30, result.y + 30, result.x + 31, result.y + 31, "找到帮会大盗", 2000);*/

                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                LtLog.e(mFairy.getLineInfo("fb:" + result.sim));
                if (result.sim > 0.72f) {
                    err = 0;
                    fbJudge = 0;
                    fbBool = true;
                    mFairy.initMatTime();
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "副本中开启挂机", Sleep);
                } else {
                    fbJudge++;
                    if (fbJudge > 2) {
                        if (fbBool) {
                            gameUtil.callToFollow();
                            setTaskName(4);
                            return;
                        } else {
                            fbJudge = 0;
                        }
                    }
                }

                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);
            }
        }.taskContent(mFairy, "帮会大盗");
    }

    //高昌
    public void gaochang() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                if (m >= 58) {
                    setTaskEnd();
                    return;
                }
            }

            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("gcmg.png", 1);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                result = mFairy.findPic("gc_baoming.png");
                mFairy.onTap(0.8f, result, "高昌报名", Sleep);

                result = mFairy.findPic("gc_sureq.png");
                mFairy.onTap(0.8f, result, "高昌确定花钱", Sleep);

                result = mFairy.findPic("gc_ybm.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "高昌已报名等待进入副本"));

                result = mFairy.findPic("gc_map.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "高昌内开始打人"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("gc_zbx.png");
                    mFairy.onTap(0.8f, result, "高昌珍宝箱", Sleep);
                    result = mFairy.findPic("gc_cbt.png");
                    mFairy.onTap(0.8f, result, "高昌藏宝图", Sleep);
                }
            }
        }.taskContent(mFairy, "高昌");
    }

    //十世镜
    public void ssj() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int h = mFairy.dateHour();
                if (h >= 15) {
                    setTaskEnd();
                    return;
                }
            }

            public void content_0() throws Exception {
                gameUtil.callToFollow();
                result = mFairy.findPic("Hangup1.png");
                mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", Sleep);
                setTaskName(1);
            }

            int count = 0;

            public void content_1() throws Exception {
                if (overtime(30, 99)) return;
                result = mFairy.findPic("taskbar.png");
                mFairy.onTap(0.85f, result, "切换到任务栏", Sleep);

                result = mFairy.findPic("leftssj.png");
                mFairy.onTap(0.8f, result, "左侧十世镜", 3000);

                result = mFairy.findPic("ssjinface.png");
                mFairy.onTap(0.8f, result, "十世镜界面", Sleep);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("ssjstop.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("十世镜次数用完结束"));
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("ssjjinru.png");
                    mFairy.onTap(0.8f, result, "进入", 5000);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("ssjjinru.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("十世镜进不去"));
                            count++;
                            if (count >= 3) {
                                LtLog.e(mFairy.getLineInfo("十世镜进不去结束"));
                                setTaskEnd();
                                return;
                            }
                        }
                    }
                }
                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "副本中"));
                if (result.sim > 0.8f) {
                    count = 0;
                    err = 0;
                    result = mFairy.findPic("gj.png");
                    mFairy.onTap(0.8f, result, 1225, 462, 1239, 473, "技能1", Sleep);
                    result = mFairy.findPic("gj.png");
                    mFairy.onTap(0.8f, result, 1108, 459, 1132, 477, "技能2", Sleep);
                    result = mFairy.findPic("gj.png");
                    mFairy.onTap(0.8f, result, 1025, 535, 1043, 548, "技能3", Sleep);
                    result = mFairy.findPic("gj.png");
                    mFairy.onTap(0.8f, result, 1019, 643, 1036, 661, "技能4", Sleep);
                    result = mFairy.findPic("gj.png");
                    mFairy.onTap(0.8f, result, 1147, 573, 1162, 587, "技能5", Sleep);
                    result = mFairy.findPic("gj.png");
                    mFairy.onTap(0.8f, result, 1238, 590, 1254, 600, "绝技1", Sleep);
                    result = mFairy.findPic("gj.png");
                    mFairy.onTap(0.8f, result, 1164, 664, 1175, 681, "绝技2", Sleep);
                }
            }
        }.taskContent(mFairy, "十世镜");
    }

    //行酒令
    public void alcohol() throws Exception {
        new TimingActivity(mFairy) {

            public void content_0() throws Exception {
                gameUtil.cancelFollowing();
                gameUtil.goCity("帮会");
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int m = mFairy.dateMinute();
                if (m > 45) {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "行酒令");
    }

    //科举乡试
    public void local() throws Exception {
        new TimingActivity(mFairy) {
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("Imperial.png", 3);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic("Imperialinterface.png");
                mFairy.onTap(0.8f, result, 584, 454, 611, 466, "科举乡试选A", 2000);
                if (result.sim > 0.8f) {
                    err = 0;
                }
                result = mFairy.findPic("Qualify.png");
                mFairy.onTap(0.8f, result, 639, 415, 640, 416, "科举会试资格", Sleep);

                result = mFairy.findPic(506, 177, 1083, 279, "Imperialstop.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("科举乡试结束"));
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "科举乡试");
    }

    int err_bpls = 0;

    //帮会联赛
    public void leagueMatch() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                if (m >= 50) {
                    setTaskEnd();
                    return;
                }
            }

            public void content_0() throws Exception {
                err = 0;
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("Leaguematch.png", 1);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                if (overtime(30, 1)) {
                    err_bpls++;
                    if (err_bpls > 1) {
                        mFairy.onTap(1009, 32, 1023, 45, "联赛结束", Sleep);
                        setTaskEnd();
                        return;
                    }
                }

                Thread.sleep(500);

                result = mFairy.findPic(952, 14, 1272, 541, "Deputybattlefield.png");
                mFairy.onTap(0.75f, result, "副战场联赛常规", Sleep);

                result = mFairy.findPic("IntheLeague.png");
                if (result.sim > 0.9f) {
                    LtLog.e(mFairy.getLineInfo("联赛内"));
                    setTaskName(3);
                }
            }

            int kscount = 0;

            public void content_3() throws Exception {
                result = mFairy.findPic("IntheLeague.png");
                mFairy.onTap(0.8f, result, 1202, 101, 1203, 102, "联赛内", Sleep);

                result = mFairy.findPic(198, 39, 304, 120, "Mapinterface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("选择城市"));
                    if (kscount == 0) {
                        mFairy.onTap(919, 59, 920, 60, "第1个矿位置", Sleep);
                    }
                    if (kscount == 1) {
                        mFairy.onTap(967, 107, 968, 108, "第2个矿位置", Sleep);
                    }
                    if (kscount == 2) {
                        mFairy.onTap(805, 195, 806, 196, "第3个矿位置", Sleep);
                    }
                    if (kscount == 3) {
                        mFairy.onTap(958, 610, 959, 611, "第4个矿位置", Sleep);
                    }
                    if (kscount == 4) {
                        mFairy.onTap(821, 504, 822, 505, "第5个矿位置", Sleep);
                    }
                    if (kscount == 5) {
                        mFairy.onTap(762, 552, 763, 553, "第6个矿位置", Sleep);
                    }
                    kscount++;
                    if (kscount >= 5) {
                        kscount = 0;
                    }
                    Thread.sleep(13000);
                    result = mFairy.findPic(305, 2, 1262, 701, "lingkuang.png");
                    mFairy.onTap(0.65f, result, result.x, result.y + 30, result.x + 1, result.y + 31, "找到灵矿", 3000);

                    result = mFairy.findPic(927, 177, 1276, 708, "youcelingkuang.png");
                    mFairy.onTap(0.8f, result, "右侧灵矿", 3000);

                    result = mFairy.findPic("IntheLeague.png");
                    mFairy.onTap(0.8f, result, 1202, 101, 1203, 102, "联赛内", 3000);

                    result = mFairy.findPic(198, 39, 304, 120, "Mapinterface.png");
                    mFairy.onTap(0.8f, result, 995, 349, 996, 350, "去收矿官那", 13000);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(305, 2, 1262, 701, "jiaokuang.png");
                        mFairy.onTap(0.65f, result, result.x, result.y + 30, result.x + 1, result.y + 31, "找到收矿的", 3000);

                        result = mFairy.findPic(927, 177, 1276, 708, "tijiaokuangshi.png");
                        mFairy.onTap(0.8f, result, result.x, result.y + 30, result.x + 1, result.y + 31, "右侧提交矿石", Sleep);
                    }
                }
                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);

                result = mFairy.findPic(639, 6, 1126, 176, "activity.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("活动"));
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "帮会联赛");
    }

    //帮花大作战
    int bhstop = 0;

    public void bhdzz() throws Exception {
        new TimingActivity(mFairy) {
            public void content_0() throws Exception {
                gameUtil.goCity("帮会");
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(200, 2)) return;
                result = mFairy.findPic("taskbar.png");
                mFairy.onTap(0.85f, result, "切换到任务栏", Sleep);

                result = mFairy.findPic("leftbh.png");
                mFairy.onTap(0.8f, result, "左侧帮花", Sleep);
                if (result.sim > 0.8f) {
                    Thread.sleep(15000);
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);
                    Thread.sleep(200000);
                    setTaskName(2);
                }
                Thread.sleep(1000);
            }

            public void content_2() throws Exception {
                int ret = gameUtil.mission("bhdzz.png", 1);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    Thread.sleep(5000);
                    setTaskName(2);
                }
            }

            public void content_3() throws Exception {

                result = mFairy.findPic("bhdzzno.png");
                if (result.sim > 0.8f) {
                    bhstop++;
                    if (bhstop >= 2) {
                        setTaskEnd();
                        return;
                    }
                }


                result = mFairy.findPic("bh_d1stop.png");
                mFairy.onTap(0.8f, result, 599, 557, 655, 572, "帮花第一关结束选第二关", 2000);
                mFairy.onTap(0.8f, result, 631, 305, 661, 319, "选羊肠道1", Sleep);
                if (result.sim < 0.8f) {
                    setTaskName(0);
                } else {
                    setTaskName(4);
                }


            }

            public void content_4() throws Exception {
                if (overtime(20, 99)) return;
                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "第二关或第三关中"));
                if (result.sim > 0.8f) {
                } else {
                    gameUtil.close(0);
                }
            }
        }.taskContent(mFairy, "帮花大作战");
    }
}
