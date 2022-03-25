package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class TimingActivity {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    Util util;
    int xshd_1 = 0, xshd_2 = 0, xshd_3 = 0;

    public TimingActivity(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        util = new Util(mFairy);
    }

    public int timingActivity() throws Exception {
        int h, m, w;
        h = mFairy.dateHour();
        m = mFairy.dateMinute();
        w = mFairy.week();
        if (h!=20){
            xshd_1 = 0;
        }
        if (h == 20 && m > 30 && AtFairyConfig.getOption("bxwl").equals("1") && xshd_1 == 0) {
            xshd_1 = 1;
            util.backCity();
            bxwl();
            return 1;
        }
        if (h == 20 && m == 10) {
            if (AtFairyConfig.getOption("zdrw").equals("1")) {
                util.backCity();
                util.signIn();
            } else {
                util.teamRanks("暂离");
                util.backCity();
                util.signIn();
                util.teamRanks("回归");
            }
            return 1;
        }

        if (m >30) {
            xshd_2 = 0;
        }
        if (((h == 12 && m < 30) || (h == 21 && m < 30)) && AtFairyConfig.getOption("hdrq").equals("1")&& xshd_2 == 0) {
            xshd_2 = 1;
            util.backCity();
            haidao();
            return 1;
        }

        if (m <30) {
            xshd_3 = 0;
        }
        if (((h == 12 && m > 30) || (h == 18 && m > 30) || (h == 22 && m > 30)) && AtFairyConfig.getOption("ccyw").equals("1")&& xshd_3 == 0) {
            xshd_3 = 1;
            util.backCity();
            yaowang();
            return 1;
        }
        return 0;
    }

    //押镖
    public void bxwl() throws Exception {
        long time = 0;
        String strTarget = "target_bxwl.png";
        String strParent = "xshd1.png";
        String strSub = "bxwl1.png";
        int bj = 0;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("押镖中bj=" + bj));
            if (bj == 0) {
                util.close();
                bj = 1;
            }
            if (bj == 1) {
                int h = mFairy.dateHour();
                if (h >= 21) {
                    return;
                }
                int ret = util.findTask("限时", "taskbxwl.png", "看任务");
                if (ret == 1) {
                    util.close();
                } else if (ret == 0) {
                    return;
                }
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
                result = mFairy.findPic(1010, 165, 1278, 512, new String[]{"ysby.png"});
                mFairy.onTap(0.7f, result, "右侧任务栏押镖", 1000);
                if (result.sim > 0.7f) {
                    bj = 3;
                } else {
                    int ret = util.findTask("限时", "taskbxwl.png", "前往");
                    if (ret == 1) {
                        Thread.sleep(5000);
                        bj = 3;
                    } else if (ret == 0) {
                        return;
                    }
                }
            }
            if (bj == 3) {
                result = mFairy.findPic(766, 18, 1265, 702, new String[]{"ycyb.png"});
                mFairy.onTap(0.8f, result, "押镖接到任务", 2000);


                result = mFairy.findPic(766, 18, 1265, 702, new String[]{"min3r.png", "djbg.png", "min3r2.png", "min3r5.png", "min3r4.png", "min3r3.png", "min3r6.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "人不满从新开始组队，或有人等级不够,结束任务"));
                if (result.sim > 0.8f) {
                    return;
                } else {
                    result = mFairy.findPic(974, 91, 1280, 233, "right_task1.png");
                    mFairy.onTap(0.9f, result, "右侧任务暗", 1000);

                    result = mFairy.findPic(766, 18, 1265, 702, new String[]{"ksyb.png"});
                    mFairy.onTap(0.8f, result, 983, 485, 1053, 510, "开始押镖", 2000);

                    result = mFairy.findPic(414, 251, 864, 381, new String[]{"zlyc.png"});
                    mFairy.onTap(0.8f, result, 726, 421, 769, 438, "再来一次", 2000);

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
                    if (dazeTime >= 120) {
                        mFairy.initMatTime();
                        result = mFairy.findPic(1010, 165, 1278, 512, new String[]{"ysby.png"});
                        mFairy.onTap(0.7f, result, "右侧任务栏押镖", 1000);
                        if (result.sim > 0.7f) {
                        } else {
                            bj = 0;
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

    //海盗入侵
    public void haidao() throws Exception {
        long time = 0;
        String strTarget = "target_hdrq.png";
        String strParent = "xshd1.png";
        String strSub = "hdrq1.png";
        int bj = 0, js_1 = 0;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("海盗入侵中bj=" + bj));
            int m = mFairy.dateMinute();
            if (m > 29) {
                util.backCity();
                return;
            }
            if (bj == 0) {
                util.close();
                bj = 1;
            }
            if (bj == 1) {
                int ret = util.findTask("限时", "taskhdrq.png", "看任务");
                if (ret == 1) {
                    util.close();
                } else if (ret == 0) {
                    return;
                }
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
                result = mFairy.findPic(81, 25, 216, 64, new String[]{"donghaiyucun.png"});
                mFairy.onTap(0.8f, result, "东海渔村", 1000);
                if (result.sim > 0.8f) {
                    bj = 3;
                } else {
                    int ret = util.findTask("限时", "taskhdrq.png", "前往");
                    if (ret == 1) {
                        Thread.sleep(5000);
                        bj = 3;
                    } else if (ret == 0) {
                        return;
                    }
                }
            }
            if (bj == 3) {
                result = mFairy.findPic(37, 12, 1222, 699, new String[]{"haidao.png"});
                mFairy.onTap(0.7f, result, "海盗", 5000);
                if (result.sim > 0.7f) {
                    result = mFairy.findPic(766, 18, 1265, 702, new String[]{"weiminchuhai.png"});
                    mFairy.onTap(0.8f, result, "为民除害", 5000);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("combat.png");
                        LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                        if (result.sim > 0.8f) {
                        } else {
                            bj = 0;
                            continue;
                        }
                    } else {
                        util.close();
                    }
                } else {
                    js_1++;
                    if (js_1 == 20) {
                        util.close();
                        result = mFairy.findPic(214, 5, 534, 82, "activity.png");
                        mFairy.onTap(0.8f, result, 130, 37, 156, 46, "打开地图", 2000);
                        mFairy.onTap(0.8f, result, 580, 232, 594, 240, "海盗第一个点", 2000);
                        util.close();
                    }
                    if (js_1 == 40) {
                        util.close();
                        result = mFairy.findPic(214, 5, 534, 82, "activity.png");
                        mFairy.onTap(0.8f, result, 130, 37, 156, 46, "打开地图", 2000);
                        mFairy.onTap(0.8f, result, 439, 452, 457, 466, "海盗第2个点", 2000);
                        util.close();
                    }
                    if (js_1 == 60) {
                        util.close();
                        result = mFairy.findPic(214, 5, 534, 82, "activity.png");
                        mFairy.onTap(0.8f, result, 130, 37, 156, 46, "打开地图", 2000);
                        mFairy.onTap(0.8f, result, 716, 471, 728, 480, "海盗第3个点", 2000);
                        util.close();
                    }
                    if (js_1 == 80) {
                        util.backCity();
                        return;
                    }
                }

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

                result = mFairy.findPic("combat.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                if (result.sim > 0.8f) {
                    Thread.sleep(5000);
                    js_1--;
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

    //铲除妖王
    public void yaowang() throws Exception {
        long time = 0;
        String strTarget = "target_xunluo.png";
        String strParent = "xunluo.png";
        String strSub = "";
        int bj = 0, js_1 = 0;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("铲除妖王中bj=" + bj));
            int m = mFairy.dateMinute();
            if (m<30){
                util.backCity();
                return;
            }
            if (bj == 0) {
                util.close();
                bj = 1;
            }
            if (bj == 1) {
                /*int  ret = util.findTask("其他","taskccyw.png","看任务");
                if (ret == 1) {
                    util.close();
                } else if (ret == 0) {
                    return;
                }*/
                if (AtFairyConfig.getOption("zdrw").equals("1")) {
                    util.teamRanks("队长");
                    int teamRet = util.leadFollowTeam("带队", strTarget, strParent, strSub);
                    if (teamRet == 1) {
                        bj = 2;
                    }
                } else {
                    return;
                }
            }
            if (bj == 2) {
                util.close();
                result = mFairy.findPic(214, 5, 534, 82, "activity.png");
                mFairy.onTap(0.8f, result, 29, 37, 55, 53, "打开地图", 2000);
                mFairy.onTap(0.8f, result, 973, 349, 983, 358, "妖王第一个点凤凰山", 2000);
                util.close();
                bj = 3;
            }
            if (bj == 3) {
                result = mFairy.findPic(37, 12, 1222, 699, new String[]{"ciweijing.png", "kuangshiguai.png"});
                mFairy.onTap(0.7f, result, "刺猬精，狂狮怪", 5000);
                if (result.sim > 0.7f) {
                    result = mFairy.findPic(766, 18, 1265, 702, new String[]{"weiminchuhai.png"});
                    mFairy.onTap(0.8f, result, "为民除害", 5000);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("combat.png");
                        LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                        if (result.sim > 0.8f) {
                        } else {
                            bj = 0;
                            continue;
                        }
                    } else {
                        util.close();
                    }
                } else {
                    js_1++;
                    if (js_1 == 20) {
                        util.close();
                        result = mFairy.findPic(214, 5, 534, 82, "activity.png");
                        mFairy.onTap(0.8f, result, 29, 37, 55, 53, "打开地图", 2000);
                        mFairy.onTap(0.8f, result, 902, 484, 921, 500, "妖王第2个点骷髅山", 10000);
                        mFairy.onTap(0.8f, result, 130, 37, 156, 46, "打开地图", 2000);
                        mFairy.onTap(0.8f, result, 757, 300, 768, 309, "妖王第2个点骷髅山", 2000);
                        util.close();
                    }
                    if (js_1 == 40) {
                        util.close();
                        result = mFairy.findPic(214, 5, 534, 82, "activity.png");
                        mFairy.onTap(0.8f, result, 29, 37, 55, 53, "打开地图", 2000);
                        mFairy.onTap(0.8f, result, 794, 163, 810, 175, "妖王第3个点五龙山", 2000);
                        util.close();
                    }
                    if (js_1 == 60) {
                        util.close();
                        result = mFairy.findPic(214, 5, 534, 82, "activity.png");
                        mFairy.onTap(0.8f, result, 29, 37, 55, 53, "打开地图", 2000);
                        mFairy.onTap(0.8f, result, 653, 313, 668, 325, "妖王第4个点乾元山", 2000);
                        util.close();
                    }
                    if (js_1 == 80) {
                        util.close();
                        result = mFairy.findPic(214, 5, 534, 82, "activity.png");
                        mFairy.onTap(0.8f, result, 29, 37, 55, 53, "打开地图", 2000);
                        mFairy.onTap(0.8f, result, 759, 387, 779, 399, "妖王第5个点终南山", 10000);
                        mFairy.onTap(0.8f, result, 130, 37, 156, 46, "打开地图", 2000);
                        mFairy.onTap(0.8f, result, 649, 260, 661, 270, "妖王第5个点终南山", 2000);
                        util.close();
                    }
                    if (js_1 == 100) {
                        util.backCity();
                        return;
                    }
                }

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

                result = mFairy.findPic("combat.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                if (result.sim > 0.8f) {
                    Thread.sleep(5000);
                    js_1--;
                }
            }
        }
    }
}
