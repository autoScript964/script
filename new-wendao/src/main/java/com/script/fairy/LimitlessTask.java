package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;


/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class LimitlessTask {
    AtFairyImpl mFairy;
    FindResult result;
    TimingActivity timingActivity;
    Util util;

    public LimitlessTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        util = new Util(mFairy);
        timingActivity = new TimingActivity(mFairy);
    }

    //刷道
    public void sd() throws Exception {
        long time = 0;
        String strTarget = "";
        String strParent = "shuadao.png";
        String strSub = "";
        String rightstr = "";
        if (AtFairyConfig.getOption("sdxy").equals("1")) {
            if (AtFairyConfig.getOption("dj").equals("1")){
                strTarget = "target_xy1j.png";
                strSub = "xiangyao1j.png";
                rightstr="xytask1j.png";
            }else {
                strTarget = "target_xy2j.png";
                strSub = "xiangyao2j.png";
                rightstr="xytask2j.png";
            }
        }
        if (AtFairyConfig.getOption("sdfm").equals("1")) {
            if (AtFairyConfig.getOption("dj").equals("1")){
                strTarget = "target_fm1j.png";
                strSub = "fumo1j.png";
                rightstr="fmtask1j.png";
            }else {
                strTarget = "target_fm2j.png";
                strSub = "fumo2j.png";
                rightstr="fmtask2j.png";
            }
        }

        if (AtFairyConfig.getOption("sdfxdx").equals("1")) {
            if (AtFairyConfig.getOption("dj").equals("1")){
                strTarget = "target_fxdx1j.png";
                strSub = "fxdx1j.png";
                rightstr="fxdxtask1j.png";
            }else {
                strTarget = "target_fxdx2j.png";
                strSub = "fxdx2j.png";
                rightstr="fxdxtask2j.png";
            }
        }
        if (AtFairyConfig.getOption("sdsb").equals("1")) {
            util.receiveDouble();
        } else {
            util.closeDouble();
        }
        int bj = 0;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("刷道中bj=" + bj));
            int ret = timingActivity.timingActivity();
            if (ret == 1) {
                bj = 0;
            }
            if (bj == 0) {
                util.close();
                bj = 1;
            }
            if (bj == 1) {
                if (AtFairyConfig.getOption("zdrw").equals("1")) {
                    util.teamRanks("队长");
                    int teamRet = util.leadFollowTeam("带队", strTarget, strParent, strSub);
                    if (teamRet == 1) {
                        bj = 2;
                    }
                } else {
                    util.teamRanks("队员");
                    int teamRet = util.leadFollowTeam("跟队", strTarget, strParent, strSub);
                    if (teamRet == 1) {
                        time = System.currentTimeMillis();
                        bj = 4;
                    }
                }

            }
            if (bj == 2) {
                result = mFairy.findPic(1010, 165, 1278, 512, new String[]{"rwlxy.png", "rwlfm.png","rwfxdx.png"});
                mFairy.onTap(0.7f, result, "右侧任务栏降妖伏魔", 1000);
                if (result.sim > 0.7f) {
                    bj = 3;
                } else {
                    result = mFairy.findPic(210, 21, 703, 98, "sdtask.png");
                    mFairy.onTap(0.8f, result, "刷道", 1000);

                    result = mFairy.findPic(553, 5, 725, 60, "Tshuadao.png");
                    mFairy.onTap(0.8f, result, 1083, 123, 1101, 150, "刷道界面切换到刷道", 1000);
                    LtLog.e(mFairy.getLineInfo(0.8f, result, "刷道前往..."));
                    if (result.sim > 0.8f) {
                        if (AtFairyConfig.getOption("sdxy").equals("1")) {
                            mFairy.onTap(0.8f, result, 331, 472, 411, 492, "前往", 2000);
                        }
                        if (AtFairyConfig.getOption("sdfm").equals("1")) {
                            mFairy.onTap(0.8f, result, 608, 475, 679, 492, "前往", 2000);
                        }
                        if (AtFairyConfig.getOption("sdfxdx").equals("1")) {
                            mFairy.onTap(0.8f, result, 892,481,943,500, "前往", 2000);
                        }
                        Thread.sleep(5000);
                        bj = 3;
                    } else {
                        util.close();
                    }
                }
            }
            if (bj == 3) {
                result = mFairy.findPic(766, 18, 1265, 702, new String[]{rightstr});
                mFairy.onTap(0.8f, result, "伏魔降妖接到了任务", 2000);

                result = mFairy.findPic(766, 18, 1265, 702, new String[]{"min3r.png", "djbg.png", "min3r2.png", "min3r5.png", "min3r4.png", "min3r3.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "人不满从新开始组队，或有人等级不够"));
                if (result.sim > 0.8f) {
                    bj = 0;
                } else {
                    result = mFairy.findPic(376, 210, 916, 514, "qx.png");
                    mFairy.onTap(0.8f, result, "取消", 1000);

                    result = mFairy.findPic(974, 91, 1280, 233, "right_task1.png");
                    mFairy.onTap(0.9f, result, "右侧任务暗", 1000);


                    result = mFairy.findPic("someone_applied.png");
                    mFairy.onTap(0.8f, result, "有人申请", 1000);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(587, 63, 649, 108, "point.png");
                        mFairy.onTap(0.8f, result, 548, 100, 549, 101, "点击申请表", 1000);
                        if (result.sim > 0.8f) {
                            for (int i = 0; i < 3; i++) {
                                result = mFairy.findPic(508, 217, 1096, 304, "need.png");
                                mFairy.onTap(0.8f, result, "点击对勾", 1000);
                            }
                        }
                        util.close();
                    }

                    long dazeTime = mFairy.mMatTime(24, 537, 20, 14, 0.9f);

                    if (dazeTime >= 20) {
                        mFairy.initMatTime();
                        result = mFairy.findPic(1010, 165, 1278, 512, new String[]{"rwlxy.png", "rwlfm.png","rwfxdx.png"});
                        mFairy.onTap(0.7f, result, "右侧任务栏降妖伏魔", 1000);
                        if (result.sim > 0.7f) {
                        } else {
                            bj = 2;
                        }
                    }

                    result = mFairy.findPic("combat.png");
                    LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                    if (result.sim > 0.8f) {
                        Thread.sleep(5000);
                        mFairy.initMatTime();
                    }
                }
            }
            if (bj == 4) {
                Thread.sleep(5000);
                result = mFairy.findPic("combat.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                if (result.sim > 0.8f) {
                    Thread.sleep(5000);
                    time = System.currentTimeMillis();
                } else {
                    if (System.currentTimeMillis() - time > 180000) {
                        util.teamRanks("退队");
                        bj = 0;
                    }
                }
            }
        }
    }

    //巡逻
    public void xunluo() throws Exception {
        long time = 0;
        String strTarget = "target_xunluo.png";
        String strParent = "xunluo.png";
        String strSub = "";
        String strMap = "";
        if (AtFairyConfig.getOption("kqsb").equals("1")) {
            util.receiveDouble();
        } else {
            util.closeDouble();
        }
        if (AtFairyConfig.getOption("map").equals("1")) {
            strMap = "1-5.png";
        }
        if (AtFairyConfig.getOption("map").equals("2")) {
            strMap = "5-10.png";
        }
        if (AtFairyConfig.getOption("map").equals("3")) {
            strMap = "10-12.png";
        }
        if (AtFairyConfig.getOption("map").equals("4")) {
            strMap = "13-15.png";
        }
        if (AtFairyConfig.getOption("map").equals("5")) {
            strMap = "16-20.png";
        }
        if (AtFairyConfig.getOption("map").equals("6")) {
            strMap = "21-25.png";
        }
        if (AtFairyConfig.getOption("map").equals("7")) {
            strMap = "23-27.png";
        }
        if (AtFairyConfig.getOption("map").equals("8")) {
            strMap = "26-30.png";
        }
        if (AtFairyConfig.getOption("map").equals("9")) {
            strMap = "30-35.png";
        }
        if (AtFairyConfig.getOption("map").equals("10")) {
            strMap = "31-35.png";
        }
        if (AtFairyConfig.getOption("map").equals("11")) {
            strMap = "36-40.png";
        }
        if (AtFairyConfig.getOption("map").equals("12")) {
            strMap = "41-43.png";
        }
        if (AtFairyConfig.getOption("map").equals("13")) {
            strMap = "44-46.png";
        }
        if (AtFairyConfig.getOption("map").equals("14")) {
            strMap = "47-49.png";
        }
        if (AtFairyConfig.getOption("map").equals("15")) {
            strMap = "50-52.png";
        }
        if (AtFairyConfig.getOption("map").equals("16")) {
            strMap = "50-55.png";
        }
        if (AtFairyConfig.getOption("map").equals("17")) {
            strMap = "53-55.png";
        }
        if (AtFairyConfig.getOption("map").equals("18")) {
            strMap = "56-60.png";
        }
        if (AtFairyConfig.getOption("map").equals("19")) {
            strMap = "61-63.png";
        }
        if (AtFairyConfig.getOption("map").equals("20")) {
            strMap = "64-66.png";
        }
        if (AtFairyConfig.getOption("map").equals("21")) {
            strMap = "67-69.png";
        }
        if (AtFairyConfig.getOption("map").equals("22")) {
            strMap = "70-72.png";
        }
        if (AtFairyConfig.getOption("map").equals("23")) {
            strMap = "73-75.png";
        }
        if (AtFairyConfig.getOption("map").equals("24")) {
            strMap = "76-78.png";
        }
        if (AtFairyConfig.getOption("map").equals("25")) {
            strMap = "79-81.png";
        }
        if (AtFairyConfig.getOption("map").equals("26")) {
            strMap = "80-83.png";
        }

        if (AtFairyConfig.getOption("map").equals("27")) {
            strMap = "82-85.png";
        }


        int bj = 0, js_1 = 0;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("巡逻中bj=" + bj));
            int ret = timingActivity.timingActivity();
            if (ret == 1) {
                bj = 0;
            }
            if (bj == 0) {
                util.close();
                bj = 1;
            }
            if (bj == 1) {
                if (AtFairyConfig.getOption("zdrw").equals("1")) {
                    util.teamRanks("队长");
                    int teamRet = util.leadFollowTeam1("带队", strTarget, strParent, strSub);
                    if (teamRet == 1) {
                        bj = 2;
                    }
                } else {
                    util.teamRanks("队员");
                    int teamRet = util.leadFollowTeam("跟队", strTarget, strParent, strSub);
                    if (teamRet == 1) {
                        time = System.currentTimeMillis();
                        bj = 4;
                    }
                }
            }
            if (bj == 2) {

                result = mFairy.findPic("patrol.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "巡逻界面"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(163, 325, 1112, 511, strMap);
                    mFairy.onTap(0.9f, result, "选择地图" + strMap, 2000);
                    if (result.sim > 0.9f) {
                        result = mFairy.findPic(402,239,878,475,"guaixiao.png");
                        mFairy.onTap(0.8f, result, 715, 417, 783, 440, "怪物弱小", 1000);
                        bj = 3;
                    } else {
                        mFairy.ranSwipe(940, 297,331, 283,  1500, (long)3000,2);
                        js_1++;
                        if (js_1 > 12) {
                            js_1 = 0;
                            bj = 0;
                        }
                    }
                } else {
                    util.close();
                }
                result = mFairy.findPic(214, 5, 534, 82, "patrol1.png");
                mFairy.onTap(0.8f, result, "点开巡逻图标", 1000);
            }
            if (bj == 3) {
                long dazeTime = mFairy.mMatTime(24, 537, 20, 14, 0.9f);
                if (dazeTime >= 20) {
                    mFairy.initMatTime();
                    bj = 0;
                }
                result = mFairy.findPic("combat.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                if (result.sim > 0.8f) {
                    Thread.sleep(5000);
                    mFairy.initMatTime();
                }
            }
            if (bj == 4) {
                Thread.sleep(5000);
                result = mFairy.findPic("combat.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                if (result.sim > 0.8f) {
                    Thread.sleep(5000);
                    time = System.currentTimeMillis();
                } else {
                    if (System.currentTimeMillis() - time > 180000) {
                        util.teamRanks("退队");
                        bj = 0;
                    }
                }
            }
        }
    }
}
