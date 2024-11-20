package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class TimingActivity extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    FindResult result2;
    FindResult result3;
    GameUtil gameUtil;
    SingleTask singleTask;
    TaskContent.ControlSplit optime1 = null;

    public TimingActivity(AtFairyImpl ATFairy) throws Exception {
        mFairy = ATFairy;
        gameUtil = new GameUtil(mFairy);
        singleTask = new SingleTask(mFairy);
        jzqd.add(13);
        sjzy.add(14);
        jzqd.add(15);
        jzqd.add(16);
        jzqd.add(17);
        sjzy.add(18);
        jzqd.add(19);

        sjzy.add(13);
        sjzy.add(14);
        sjzy.add(15);
        sjzy.add(16);
        sjzy.add(17);
        sjzy.add(18);
        sjzy.add(23);

        if (!AtFairyConfig.getOption("optime1").equals("")) {
            optime1 = strSplit(AtFairyConfig.getOption("optime1"));
        }
    }

    List<Integer> jzqd = new ArrayList<>();
    List<Integer> sjzy = new ArrayList<>();
    int kjxs = 0, jzkd = 0, zzjt = 0, jzls = 0, jzls1 = 0, fqhs = 0, zhanchang = 0, jzsl = 0, jzjh = 0, ddmt = 0, ryjjc = 0, lldb = 0,
            jzms = 0, mphw = 0, ygbh = 0, tsly = 0, zbs = 0, yjdx = 0, szlc = 0, sjzy1 = 0, jzqd1 = 0, qty = 0;
    int ldz = 0, jzwq = 0, cfjj = 0, hlzc = 0, pwzz = 0, jbjl = 0, mzrw = 0, mjxx = 0;
    int sjsy = 0, zcjs = 0;

    public int timingActivity() throws Exception {
        int h = mFairy.dateHour();
        int m = mFairy.dateMinute();
        int w = mFairy.week();
        int s = (h * 60 + m) * 60;
        int back = 0;

        LtLog.e(mFairy.getLineInfo("检测限时任务"));

        for (int i = 0; i < 2; i++) {
            if (AtFairyConfig.getOption("ryjjc").equals("1") && (w == 1 || w == 3) && h == 20 && m < 30 && ryjjc == 0) {
                //  LtLog.e(mFairy.getLineInfo("用户勾选荣耀竞技场"));
                gameUtil.lkfb();
                ryjjc();
                ryjjc = 1;
                back = 1;
            } else if (AtFairyConfig.getOption("zhanchang").equals("1") && w != 7 && (h == 12) || (h == 22) && m < 34 && zhanchang == 0) {
                // LtLog.e(mFairy.getLineInfo("用户勾选战场"));
                gameUtil.lkfb();
                gameUtil.retire();
                zhanchang();
                zcjs++;
                if (zcjs >= 3) {
                    zhanchang = 1;
                }
                back = 1;
            }

            //LtLog.e(mFairy.getLineInfo("用户勾选守卫家族家族强盗"));
            if (AtFairyConfig.getOption("swjz").equals("1") && w != 7 && ((h >= 7 && h <= 18 && m <= 10)) && jzqd.size() != 0 && jzqd1 == 0) {
                if (jzqd.contains(h)) {
                    /*if (m < 20) {*/
                    if (optime1 != null && optime1.choice == 1) {
                        gameUtil.zhjb();
                    }
                    gameUtil.lkfb();
                    jzqd();
                    jzqd1 = 1;
                    jzqd.remove(jzqd.indexOf(h));
                    back = 1;

                }
            }
            if (AtFairyConfig.getOption("ygbh").equals("1") && w == 5 && (h == 12 && m >= 20) && ygbh == 0) {
                //   LtLog.e(mFairy.getLineInfo("用户勾选月光宝盒"));
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                ygbh();
                ygbh = 1;
                back = 1;
            }
            // LtLog.e(mFairy.getLineInfo("用户勾选冲锋竞技"));
            if (AtFairyConfig.getOption("cfjj").equals("1") && w == 4 && (h == 12 && m >= 30) || (h == 20 && m < 30) && cfjj == 0) {
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                cfjj();
                cfjj = 1;
                back = 1;
            }
            if (AtFairyConfig.getOption("sjzy").equals("1") && w != 7 && (h >= 13 && h <= 24 && m <= 20) && sjzy.size() != 0 && sjzy1 == 0) {
                //LtLog.e(mFairy.getLineInfo("用户勾选赏金追妖"));
                if (sjzy.contains(h)) {
                    if (m < 10) {
                        if (optime1 != null && optime1.choice == 1) {
                            gameUtil.zhjb();
                        }
                        gameUtil.lkfb();
                        sjzy();
                        sjzy.remove(sjzy.indexOf(h));
                        back = 1;
                    }
                }
            }
            //水晶深渊
            if (AtFairyConfig.getOption("sjsy").equals("1") && h == 19 && m < 5 && sjsy == 0) {
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                sjsy();
                sjsy = 1;
                back = 1;
            }
            //   LtLog.e(mFairy.getLineInfo("用户勾选家族首领"));
            if (AtFairyConfig.getOption("jzsl").equals("1") && w != 7 && h == 19 && m >= 3 && m < 20 && jzsl == 0) {

                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                jzsl();
                jzsl = 1;
                back = 1;
            }
            //    LtLog.e(mFairy.getLineInfo("用户勾选家族酒会"));
            if (AtFairyConfig.getOption("jzjh").equals("1") && h == 19 && (m >= 10 && m <= 25) && jzjh == 0) {
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                if (w == 6 || w == 7) {
                    jzwh();
                } else {
                    jzjh();
                }
                jzjh = 1;
                back = 1;
            }

            if (AtFairyConfig.getOption("zzjt").equals("1") && (w == 4 || w == 5) && (h == 19 && m > 29) && zzjt == 0) {
                //  LtLog.e(mFairy.getLineInfo("征战九天"));
                gameUtil.lkfb();
                zzjt();
                zzjt = 1;
                back = 1;
            }
            //   LtLog.e(mFairy.getLineInfo("用户勾选家族魔神"));
            if (AtFairyConfig.getOption("5599").equals("1") && w == 4 && (h == 19 && m >= 30) && jzms == 0) {
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                jzms();
                jzms = 1;
                back = 1;
            }
            if ((!AtFairyConfig.getOption("jzls").equals("")) && (w == 1 || w == 2 || w == 3) && (h == 19 && m > 29 && m < 59) && jzls == 0) {
                //  LtLog.e(mFairy.getLineInfo("用户勾选家族联赛"));
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                jzls();
                jzls = 1;
                back = 1;
            }
            if ((!AtFairyConfig.getOption("jzls").equals("")) && (w == 1 || w == 2 || w == 3) && (h == 19 && m > 29 && m < 59) && jzls1 == 0) {
                //  LtLog.e(mFairy.getLineInfo("用户勾选家族联赛"));
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                jzls1();
                jzls1 = 1;
                back = 1;
            }

            if (AtFairyConfig.getOption("pwzz").equals("1") && w == 2 && ((h == 19 && m >= 30) || (h >= 20 && h < 23)) && pwzz == 0) {
                //    LtLog.e(mFairy.getLineInfo("用户破雾之战"));
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                pwzz();
                pwzz = 1;
                back = 1;
            }
            if (AtFairyConfig.getOption("mphw").equals("1") && w == 7 && (h == 20 && m < 30) && mphw == 0) {
                //  LtLog.e(mFairy.getLineInfo("用户勾选门派会武"));
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                mphw();
                mphw = 1;
                back = 1;
            }


            //h == 19 && m>5 && m<25|| m>=35 && m<55
            if (AtFairyConfig.getOption("kjxs").equals("1") && w != 6 && w != 7 && (h == 19 && m >= 35 && m < 55) || (h >= 20 && h < 23) && kjxs == 0) {
                //  LtLog.e(mFairy.getLineInfo("用户勾选科举答题"));
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                kjxs();
                kjxs = 1;
                back = 1;
            }

            if (AtFairyConfig.getOption("jzkd").equals("1") && (w == 1 || w == 3 || w == 5) && ((h >= 12 && m >= 30) || h >= 13) && jzkd == 0) {
                //   LtLog.e(mFairy.getLineInfo("用户勾选家族矿洞"));
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                jzkd();
                jzkd = 1;
                back = 1;
            }

            if (AtFairyConfig.getOption("fqhs").equals("1") && (w == 2 || w == 4 || w == 6) && fqhs == 0) {
                //   LtLog.e(mFairy.getLineInfo("用户勾选法器护送"));
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                fqhs();
                fqhs = 1;
                back = 1;
            }

            if (AtFairyConfig.getOption("ddmt").equals("1") && w == 7 && (h == 11 && m > 56) || (h == 12) && ddmt == 0) {
                //  LtLog.e(mFairy.getLineInfo("用户勾选颠倒魔塔"));
                gameUtil.lkfb();
                ddmt();
                ddmt = 1;
                back = 1;
            }

            if (AtFairyConfig.getOption("lldb").equals("1") && w == 2 && (h == 12 || h == 20) && m < 30 && lldb == 0) {
                //  LtLog.e(mFairy.getLineInfo("用户勾选楼兰夺宝"));
                gameUtil.lkfb();
                lldb();
                lldb = 1;
                back = 1;
            }
            if (AtFairyConfig.getOption("lldb").equals("1") && w == 2 && (h == 20) && m < 30 && lldb == 0) {
                //  LtLog.e(mFairy.getLineInfo("用户勾选楼兰夺宝"));
                gameUtil.lkfb();
                lldb();
                lldb = 1;
                back = 1;
            }

            if (AtFairyConfig.getOption("zbs").equals("1") && w == 6 && h < 20 && zbs == 1) {
                //  LtLog.e(mFairy.getLineInfo("用户勾选争霸赛"));
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                zbs();
                zbs = 1;
                back = 1;
            }

            if (AtFairyConfig.getOption("5615").equals("1") && w == 5 && h == 21 && m >= 35 && m < 45 && tsly == 0) {
                //    LtLog.e(mFairy.getLineInfo("用户勾选天神领域"));
                gameUtil.lkfb();
                tsly();
                tsly = 1;
                back = 1;
            }
            if (AtFairyConfig.getOption("6476").equals("1") && (w == 4 && (h == 13 || h == 21) && m <= 30) /*&& mjxx == 0*/) {
                LtLog.e("用户勾秘境寻仙");
                mjxx();
                //mjxx = 1;
                back = 1;
            }
            if (AtFairyConfig.getOption("6476").equals("1") && (w == 2 && (h == 13 && m <= 30) || (h == 20 && m == 59) || (h == 21 && m <= 30)) && hlzc == 0) {
                LtLog.e("用户勾选荒雷战场");
                hlzc();
                if (h != 13) {
                    hlzc = 1;
                }
                back = 1;
            }


            //一骑当先
            if (AtFairyConfig.getOption("6476").equals("1") && (w == 3 && ((h == 12 && m == 59) || (h == 13 && m <= 30) || (h == 20 && m == 59) || (h == 21 && m <= 30))) && yjdx == 0) {
                //LtLog.e("用户勾选一骑当先");
                LtLog.e("天地之战任务开始");
                gameUtil.lkfb();
                //yjdx_test();
                tdzz();
                yjdx = 1;
                back = 1;
            }

            if (AtFairyConfig.getOption("tdzz").equals("1") && (w == 6 && h >= 14 && h <= 16) && yjdx == 0) {
                //LtLog.e("用户勾选一骑当先");
                LtLog.e("天地之战任务开始");
                //yjdx_test();
                tdzz();
                yjdx = 1;
                back = 1;
            }


            if (AtFairyConfig.getOption("mjxx").equals("1") && (w == 6 && h >= 14 && h < 16) /*&& mjxx == 0*/) {
                LtLog.e("用户勾秘境寻仙");
                mjxx();
                //mjxx = 1;
                back = 1;
            }
            if (AtFairyConfig.getOption("hlzc").equals("1") && (w == 6 && h >= 14 && h <= 16) && hlzc == 0) {
                LtLog.e("用户勾选荒雷战场");
                hlzc();
                hlzc = 1;
                back = 1;
            }

            if (AtFairyConfig.getOption("szlc").equals("1") && (h > 0 && h < 22) && szlc == 0) {
                //    LtLog.e(mFairy.getLineInfo("用户勾选神之猎场"));
                gameUtil.lkfb();
                szlc();
                szlc = 1;
                back = 1;
            }
//    LtLog.e(mFairy.getLineInfo("用户勾选领地战"));
            if (AtFairyConfig.getOption("ldz").equals("1") && w == 5 && h == 20 && m < 30 && ldz == 0) {

                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                ldz();
                ldz = 1;
                back = 1;
            }

            if (AtFairyConfig.getOption("jzwq").equals("1") && jzwq == 0) {
                //   LtLog.e(mFairy.getLineInfo("家族温泉"));
                gameUtil.lkfb();
                gameUtil.cancelFollowing();
                jzwq();
                jzwq = 1;
                back = 1;
            }

            if (AtFairyConfig.getOption("qty").equals("1") && w == 5 || w == 2 && h == 21 && m < 25 && qty == 0) {
                //    LtLog.e(mFairy.getLineInfo("用户勾抢汤圆"));
                gameUtil.lkfb();
                qty();
                qty = 1;
                back = 1;
            }

            if (h == 23 && jbjl == 0) {
                gameUtil.lkfb();
                gameUtil.jpjl();
                singleTask.kgfl();
                jbjl = 1;
                back = 1;
            }
            if (mzrw == 0) {
                singleTask.mzrw();//每周任务
                mzrw = 1;
                back = 1;
            }
        }

        return back;
    }

    public void inOperation() throws Exception {
        if (!AtFairyConfig.getTaskID().equals("2033") && !AtFairyConfig.getTaskID().equals("2035")) {
            result = mFairy.findPic(1144, 1, 1240, 31, new String[]{"llhj.png", "huanjing.png"});
            if (result.sim > 0.8f) {
                for (int i = 0; i < 10; i++) {
                    result = mFairy.findPic(1144, 1, 1240, 31, new String[]{"llhj.png", "huanjing.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("洗刷刷中暂停别的任务"));
                        i = 0;
                    }
                    mFairy.condit();
                    Thread.sleep(2000);
                }
            }
        }
        result = mFairy.findPic(new String[]{"In picture.png", "In picture1.png"});
        LtLog.e(mFairy.getLineInfo("过图：" + result.sim));
        if (result.sim > 0.92f) {
            LtLog.e(mFairy.getLineInfo("过图中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic(511, 6, 805, 437, "Pathfinding.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("寻路中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic("dutiao.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("读条中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic("Join the family.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("没有家族结束当前任务"));
            setTaskEnd();
            return;
        }
    }

    public boolean jzlsbool = false;

    public void jzls() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic("jzlsstop.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("家族联赛结束"));
                    setTaskEnd();
                    return;
                }

                int w = mFairy.week();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if ((h == 19 && m > 29 && m < 59) && (w == 1 || w == 3)) {
                } else {
                    LtLog.e(mFairy.getLineInfo("家族联赛结束"));
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                }
            }

            public void content_0() throws Exception {
                gameUtil.goCity("家族");
                jzlsbool = false;
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;

                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 506, 500, 515, 508, "选点", 1000);
                    mFairy.onTap(0.8f, result, 600, 410, 601, 411, "寻找npc", 8000);
                    mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                    mFairy.onTap(0.8f, result, 749, 468, 753, 476, "卓洛", 3000);
/*                    if (i++>=3){
                        LtLog.e(mFairy.getLineInfo("3次家族没找到结束"));
                        setTaskEnd();
                        return;
                    }*/
                }

                if (AtFairyConfig.getOption("jzls").equals("1")) {
                    result1 = mFairy.findPic(1008, 13, 1266, 601, "leftzhuzc.png");
                } else {
                    result1 = mFairy.findPic(1008, 13, 1266, 601, "leftfuzc.png");
                }

                if (result1.sim > 0.8f) {
                    mFairy.onTap(0.8f, result1, (AtFairyConfig.getOption("jzls").equals("1") ? "主战场" : "副战场"), 10);
                    for (int i = 0; i < 50; i++) {
                        result1 = mFairy.findPic(494, 372, 778, 561, new String[]{"wkq.png", "yjs.png", "lsts.png"});
                        if (result1.sim > 0.75f) {
                            LtLog.i(mFairy.getLineInfo("联赛未开启"));
                            setTaskEnd();
                            return;
                        }
                        LtLog.e("查看是否开启");
                    }
                    LtLog.e("进入联赛");
                    Thread.sleep(5000);
                }

                result = mFairy.findPic(1037, 169, 1119, 242, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("联赛中"));
                    setTaskName(2);
                    return;
                }
            }

            boolean openmap = true;

            public void content_2() throws Exception {
                if (overtime(30, 0)) {
                    if (jzlsbool) {
                        setTaskEnd();
                    }
                    gameUtil.close(1);
                    return;
                }

                Thread.sleep(1000);

                result = mFairy.findPic(1037, 169, 1119, 242, "copy.png");
                mFairy.onTap(0.8f, result, 1196, 98, 1211, 111, "打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("地图界面"));
                    if (AtFairyConfig.getOption("jzlscz").equals("1")) {
                        mFairy.onTap(0.8f, result, 408, 238, 417, 245, "去挖矿", 5000);
                    }

                    if (AtFairyConfig.getOption("jzlscz").equals("2")) {
                        result1 = mFairy.findPic(150, 68, 296, 192, "zb.png");
                        mFairy.onTap(0.8f, result1, 668, 132, 677, 144, "去上杀人", 5000);

                        result2 = mFairy.findPic(106, 207, 249, 470, "zb.png");
                        mFairy.onTap(0.8f, result2, 668, 344, 679, 357, "去中杀人", 5000);

                        result3 = mFairy.findPic(142, 473, 306, 647, "zb.png");
                        mFairy.onTap(0.8f, result3, 668, 580, 677, 591, "去下杀人", 5000);


                    }

                    mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                    setTaskName(4);
                    return;
                }

                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                }

                result = mFairy.findPic(552, 116, 701, 203, new String[]{"jzlsstop.png", "dz.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("家族联赛结束"));
                    setTaskEnd();
                    return;
                }
            }

            public void content_4() throws Exception {

                if (overtime(15, 3)) {
                    jzlsbool = true;
                    gameUtil.close(1);
                    return;
                }

                result = mFairy.findPic(552, 116, 701, 203, new String[]{"jzlsstop.png", "dz.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("家族联赛结束"));
                    Thread.sleep(20000);
                    setTaskEnd();
                    return;
                } else {

                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork.png", "fork3.png"});
                    mFairy.onTap(0.9f, result, "关叉", Sleep);

                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork2.png"});
                    mFairy.onTap(0.9f, result, "关叉2", Sleep);

                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork1.png"});
                    mFairy.onTap(0.9f, result, "关叉1", Sleep);
                }
                result = mFairy.findPic(1037, 169, 1119, 242, "copy.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (AtFairyConfig.getOption("jzlscz").equals("2")) {
                        result = mFairy.findPic("hand.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 200);
                            mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 200);
                            mFairy.onTap(0.8f, result, 1042, 483, 1057, 497, "技能3", 200);
                            mFairy.onTap(0.8f, result, 1008, 568, 1024, 585, "技能4", 200);
                            mFairy.onTap(0.8f, result, 1049, 655, 1064, 671, "技能5", 200);
                            mFairy.onTap(0.8f, result, 959, 656, 972, 663, "技能6", 200);
                            mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                            mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                        }
                    }

                    if (AtFairyConfig.getOption("jzlscz").equals("1")) {

                        result = mFairy.findPic(640, 5, 988, 79, new String[]{"crysta.png"});
                        mFairy.onTap(0.9f, result, "水晶", 4000);

                        result = mFairy.findPic(249, 112, 1111, 560, new String[]{"kuangshi.png", "crysta1.png"});
                        mFairy.onTap(0.8f, result, result.x - 42, result.y + 119, result.x - 31, result.y + 127, "捡取矿石", 4000);
                        if (picCountS(0.8f, result, "家族联赛找不到矿石") > 5) {
                            result = mFairy.findPic("hand.png");
                            if (picCount(0.8f, result, "家族联赛找不到矿石滑动了5次") > 5) {
                                openmap = true;
                                setTaskName(3);
                                LtLog.e(mFairy.getLineInfo("家族联赛找不到矿石滑动了5次"));
                                return;
                            }
                            if (result.sim > 0.8f) {
                                int x = new Random().nextInt(143) + 106;
                                int y = new Random().nextInt(145) + 249;
                                mFairy.ranSwipe(175, 570, x, y, 100, (long) 1000, 2);
                            }
                        }
                    }
                }

                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic("jzlsstop.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("家族联赛结束"));
                    Thread.sleep(20000);
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "家族联赛中");

    } //家族联赛

    public void jzls1() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic("jzlsstop.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("家族联赛结束"));
                    setTaskEnd();
                    return;
                }

                int w = mFairy.week();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if ((h == 19 && m > 29 && m < 59) && (w == 1 || w == 3)) {
                } else {
                    LtLog.e(mFairy.getLineInfo("家族联赛结束"));
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                }
            }

            public void content_0() throws Exception {
                gameUtil.goCity("家族");
                jzlsbool = false;
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;

                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 506, 500, 515, 508, "选点", 1000);
                    mFairy.onTap(0.8f, result, 600, 410, 601, 411, "寻找npc", 8000);
                    mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                    mFairy.onTap(0.8f, result, 749, 468, 753, 476, "卓洛", 3000);
/*                    if (i++>=3){
                        LtLog.e(mFairy.getLineInfo("3次家族没找到结束"));
                        setTaskEnd();
                        return;
                    }*/
                }

                if (AtFairyConfig.getOption("jzls").equals("1")) {
                    result1 = mFairy.findPic(1008, 13, 1266, 601, "leftzhuzc.png");
                } else {
                    result1 = mFairy.findPic(1008, 13, 1266, 601, "leftfuzc.png");
                }

                if (result1.sim > 0.8f) {
                    mFairy.onTap(0.8f, result1, (AtFairyConfig.getOption("jzls").equals("1") ? "主战场" : "副战场"), 10);
                    for (int i = 0; i < 50; i++) {
                        result1 = mFairy.findPic(494, 372, 778, 561, new String[]{"wkq.png", "yjs.png", "lsts.png"});
                        if (result1.sim > 0.75f) {
                            LtLog.i(mFairy.getLineInfo("联赛未开启"));
                            setTaskEnd();
                            return;
                        }
                        LtLog.e("查看是否开启");
                    }
                    LtLog.e("进入联赛");
                    Thread.sleep(5000);
                }

                result = mFairy.findPic(1037, 169, 1119, 242, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("联赛中"));
                    setTaskName(2);
                    return;
                }
            }

            boolean openmap = true;

            public void content_2() throws Exception {
                if (overtime(30, 0)) {
                    if (jzlsbool) {
                        setTaskEnd();
                    }
                    gameUtil.close(1);
                    return;
                }

                Thread.sleep(1000);

                result = mFairy.findPic(1037, 169, 1119, 242, "copy.png");
                mFairy.onTap(0.8f, result, 1196, 98, 1211, 111, "打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("地图界面"));
                    if (AtFairyConfig.getOption("jzlscz").equals("1")) {
                        mFairy.onTap(0.8f, result, 408, 238, 417, 245, "去挖矿", 5000);
                    }

                    if (AtFairyConfig.getOption("jzlscz").equals("2")) {
                        result1 = mFairy.findPic(150, 68, 296, 192, "zb.png");
                        mFairy.onTap(0.8f, result1, 668, 132, 677, 144, "去上杀人", 5000);

                        result2 = mFairy.findPic(106, 207, 249, 470, "zb.png");
                        mFairy.onTap(0.8f, result2, 668, 344, 679, 357, "去中杀人", 5000);

                        result3 = mFairy.findPic(142, 473, 306, 647, "zb.png");
                        mFairy.onTap(0.8f, result3, 668, 580, 677, 591, "去下杀人", 5000);


                    }

                    mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                    setTaskName(4);
                    return;
                }

                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                }

                result = mFairy.findPic(552, 116, 701, 203, new String[]{"jzlsstop.png", "dz.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("家族联赛结束"));
                    setTaskEnd();
                    return;
                }
            }

            public void content_4() throws Exception {

                if (overtime(15, 3)) {
                    jzlsbool = true;
                    gameUtil.close(1);
                    return;
                }

                result = mFairy.findPic(552, 116, 701, 203, new String[]{"jzlsstop.png", "dz.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("家族联赛结束"));
                    Thread.sleep(20000);
                    setTaskEnd();
                    return;
                } else {

                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork.png", "fork3.png"});
                    mFairy.onTap(0.9f, result, "关叉", Sleep);

                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork2.png"});
                    mFairy.onTap(0.9f, result, "关叉2", Sleep);

                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork1.png"});
                    mFairy.onTap(0.9f, result, "关叉1", Sleep);
                }
                result = mFairy.findPic(1037, 169, 1119, 242, "copy.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (AtFairyConfig.getOption("jzlscz").equals("2")) {
                        result = mFairy.findPic("hand.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 200);
                            mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 200);
                            mFairy.onTap(0.8f, result, 1042, 483, 1057, 497, "技能3", 200);
                            mFairy.onTap(0.8f, result, 1008, 568, 1024, 585, "技能4", 200);
                            mFairy.onTap(0.8f, result, 1049, 655, 1064, 671, "技能5", 200);
                            mFairy.onTap(0.8f, result, 959, 656, 972, 663, "技能6", 200);
                            mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                            mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                        }
                    }

                    if (AtFairyConfig.getOption("jzlscz").equals("1")) {

                        result = mFairy.findPic(640, 5, 988, 79, new String[]{"crysta.png"});
                        mFairy.onTap(0.9f, result, "水晶", 4000);

                        result = mFairy.findPic(249, 112, 1111, 560, new String[]{"kuangshi.png", "crysta1.png"});
                        mFairy.onTap(0.8f, result, result.x - 42, result.y + 119, result.x - 31, result.y + 127, "捡取矿石", 4000);
                        if (picCountS(0.8f, result, "家族联赛找不到矿石") > 5) {
                            result = mFairy.findPic("hand.png");
                            if (picCount(0.8f, result, "家族联赛找不到矿石滑动了5次") > 5) {
                                openmap = true;
                                setTaskName(3);
                                LtLog.e(mFairy.getLineInfo("家族联赛找不到矿石滑动了5次"));
                                return;
                            }
                            if (result.sim > 0.8f) {
                                int x = new Random().nextInt(143) + 106;
                                int y = new Random().nextInt(145) + 249;
                                mFairy.ranSwipe(175, 570, x, y, 100, (long) 1000, 2);
                            }
                        }
                    }
                }

                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic("jzlsstop.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("家族联赛结束"));
                    Thread.sleep(20000);
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "家族联赛中111");

    }//家族联赛111

    public void zzjt() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                if (m >= 33) {
                    LtLog.e(mFairy.getLineInfo("征战九天结束"));
                    setTaskEnd();
                    return;
                }
            }

            public void content_0() throws Exception {
                gameUtil.retire();
                int ret = gameUtil.mission("zzjt.png", 2);
                if (ret == 1) {
                    setTaskName(1);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_1() throws Exception {
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();
                int w = mFairy.week();
                if (overtime(8, 0)) return;

                result = mFairy.findPic(909, 111, 1136, 642, "jrzc.png");
                mFairy.onTap(0.8f, result, "进入战场", Sleep);

                result = mFairy.findPic(868, 51, 1193, 314, "lkfb.png");
                if (result.sim > 0.8f) {
                    gameUtil.lkfb();
                    //   LtLog.e(mFairy.getLineInfo("用户勾选家族魔神"));
                    if (AtFairyConfig.getOption("5599").equals("1") && w == 4 && (h == 19 && m >= 29) && jzms == 0) {
                        gameUtil.cancelFollowing();
                        jzms();
                        jzms = 1;
                    }
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "征战九天");

    } //征战九天

    public void kjxs() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();
                int w = mFairy.week();

                result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
                mFairy.onTap(0.8f, result, "收起对话栏", Sleep);
                super.inOperation();

                if (w != 6 && w != 7 && ((h == 19 && m > 5 && m < 55) || (h >= 21 && h < 23))) {
                } else {
                    LtLog.e(mFairy.getLineInfo("活动超时退出"));
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                int ret = gameUtil.mission("kjxs.png", 2);
                if (ret == 1) {
                    setTaskName(1);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_1() throws Exception {
                if (overtime(5, 0)) return;
                Thread.sleep(1000);

                if (AtFairyConfig.getOption("fuhuo").equals("1")) {
                    result = mFairy.findPic(491, 133, 784, 539, "fuhuo2.png");
                    mFairy.onTap(0.8f, result, "原地复活", Sleep);
                } else {
                    result = mFairy.findPic(491, 133, 784, 539, "fuhuo1.png");
                    mFairy.onTap(0.8f, result, "默认复活", Sleep);
                }

                result = mFairy.findPic("kjinface.png");
                mFairy.onTap(0.8f, result, 598, 393, 639, 411, "科举界面选A", Sleep);
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("kjxsstop.png");
                mFairy.onTap(0.8f, result, 615, 590, 674, 611, "科举乡试结束", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy, "科举乡试中");
    } //科举乡试

    public void ddmt() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                int w = mFairy.week();
                if (!(w == 7 && (h == 11 && m > 56) || (h == 12 && m <= 30))) {
                    LtLog.e(mFairy.getLineInfo("颠倒魔塔结束"));
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("ddmttask.png", 2);
                if (ret == 1) {
                    gameUtil.retire();
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(8, 0)) return;
                if (gameUtil.duiyuan() == 1) {
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(7, 0)) {
                    return;
                }
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                result = mFairy.findPic(1037, 146, 1105, 212, new String[]{"copy.png", "ddmtnei.png"});
                if (h == 12 && m >= 15 && result.sim < 0.8f) {
                    LtLog.e("未进入退出");
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("dyqxpp.png");
                mFairy.onTap(0.8f, result, "先取消匹配从新定义活动", Sleep);


                result = mFairy.findPic(79, 107, 270, 599, "dshd.png");
                mFairy.onTap(0.95f, result, "找到定时活动", Sleep);


                result = mFairy.findPic(79, 107, 270, 599, "mbddmt.png");
                mFairy.onTap(0.8f, result, "找到颠倒魔塔", Sleep);
                mFairy.onTap(0.8f, result, 1047, 631, 1085, 643, "开启自动匹配", Sleep);
                if (result.sim < 0.8f) {
                    mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5}, 0, 171, 541, 173, 160, 200, 1500, 2);
                } else {
                    setTaskName(4);
                    return;
                }
            }

            public void content_4() throws Exception {
                if (overtime(15, 0)) return;
                if (timekeep(0, 600000, "超过10分钟没组到人")) {
                    LtLog.e(mFairy.getLineInfo("--------超过10分钟没组到人"));
                    setTaskName(0);
                    return;
                }
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                result = mFairy.findPic(1037, 146, 1105, 212, new String[]{"copy.png", "ddmtnei.png"});
                if (h == 12 && m >= 15 && result.sim < 0.8f) {
                    LtLog.e("未进入退出");
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("dyqxpp.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "匹配中"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("shenqing.png");
                    mFairy.onTap(0.8f, result, 957, 103, 1151, 588, "申请进队", Sleep);
                    err = 0;
                }

                result = mFairy.findPic(855, 248, 1116, 410, "youceyaoqing.png");
                mFairy.onTap(0.8f, result, 1030, 370, 1058, 384, "右侧同意邀请", Sleep);

                result = mFairy.findPic("yryaoqing.png");
                mFairy.onTap(0.8f, result, "有人邀请点开", Sleep);

                result = mFairy.findPic(879, 101, 1092, 581, "jiaru.png");
                mFairy.onTap(0.8f, result, "邀请加入", 100);
                mFairy.onTap(0.8f, result, 438, 613, 477, 625, "邀请列表关闭", Sleep);

                result = mFairy.findPic("beitile.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("qxgs.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进入队伍成功跟随队伍"));
                    gameUtil.close(0);
                    setTaskName(5);
                    return;
                }
                result = mFairy.findPic("gensuiduiwu.png");
                mFairy.onTap(0.8f, result, "进入队伍成功跟随队伍", Sleep);
                if (result.sim > 0.8f) {
                    gameUtil.close(0);
                    setTaskName(5);
                    return;
                }
            }

            int mapCount = 0;

            public void content_5() throws Exception {
                result = mFairy.findPic("duiwulan.png");
                mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic(0, 0, 30, 27, "hg.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("成了队长了从来"));
                    gameUtil.retire();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("chuanjianduiwu.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    setTaskName(0);
                    return;
                }

                if (timekeep(0, 300000, "超过5分钟没有在副本")) {
                    LtLog.e(mFairy.getLineInfo("--------超过5分钟没有在副本"));
                    gameUtil.retire();
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("beitile.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    gameUtil.lkfb();
                    setTaskName(0);
                    return;
                }
                gameUtil.fuhuo();
                result = mFairy.findPic(1037, 146, 1105, 212, new String[]{"copy.png", "ddmtnei.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    timekeepInit("超过5分钟没有在副本");

                    gameUtil.zidong();
                }
                result = mFairy.findPic("ddmtdingceng.png");
                mFairy.onTap(0.8f, result, "颠倒魔塔顶层了打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("地图界面"));
                    if (mapCount == 0) {
                        mFairy.onTap(0.8f, result, 633, 592, 646, 605, "顶层第一个位置", Sleep);
                    }
                    if (mapCount == 1) {
                        mFairy.onTap(0.8f, result, 313, 484, 329, 497, "顶层第2个位置", Sleep);
                    }
                    if (mapCount == 2) {
                        mFairy.onTap(0.8f, result, 381, 214, 398, 227, "顶层第3个位置", Sleep);
                    }
                    if (mapCount == 3) {
                        mFairy.onTap(0.8f, result, 632, 149, 650, 162, "顶层第4个位置", Sleep);
                    }
                    if (mapCount == 4) {
                        mFairy.onTap(0.8f, result, 890, 213, 909, 225, "顶层第5个位置", Sleep);
                    }
                    if (mapCount == 5) {
                        mFairy.onTap(0.8f, result, 969, 492, 990, 501, "顶层第6个位置", Sleep);
                    }
                    if (mapCount == 6) {
                        LtLog.e(mFairy.getLineInfo("没有领到宝箱颠倒魔塔结束"));
                        gameUtil.lkfb();
                        gameUtil.retire();
                        setTaskEnd();
                        return;
                    }
                    mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", 5000);

                    result = mFairy.findPic(293, 16, 1170, 581, "ddmtbx.png");
                    mFairy.onTap(0.7f, result, result.x - 37, result.y + 42, result.x - 36, result.y + 43, "宝箱", Sleep);
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("领完宝箱颠倒魔塔结束"));
                        gameUtil.lkfb();
                        gameUtil.retire();
                        setTaskEnd();
                        return;
                    } else {
                        mapCount++;
                    }
                }
            }
        }.taskContent(mFairy, "颠倒魔塔中");
    }//颠倒魔塔

    public void fqhs_test() throws Exception {
        new TimingActivity(mFairy) {
            @Override
            public void inOperation() throws Exception {
                super.inOperation();
                gameUtil.fuhuo();
                gameUtil.fqtask();
            }

            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("fqhs.png", 2);
                if (ret == 1) {
                    setTaskName(3);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            int renwu = 0;

            public void content_2() throws Exception {
                result = mFairy.findPic(40, 124, 282, 411, "leftfqhs.png");
                mFairy.onTap(0.7f, result, "左侧法器护送", Sleep);
                if (result.sim > 0.7f) {
                    result = mFairy.findPic(40, 124, 282, 411, new String[]{"leftdiling.png", "lefttaoyuan.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("地灵童子的任务"));
                        renwu = 2;
                        setTaskName(3);
                        return;
                    }

                    result = mFairy.findPic(40, 124, 282, 411, new String[]{"lefttianling.png", "lefthuashan.png"});
                    LtLog.e(mFairy.getLineInfo("天灵童子的任务"));
                    if (result.sim > 0.8f) {
                        renwu = 1;
                        setTaskName(3);
                        return;
                    }
                }
                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);

                mFairy.taskSlid(err, new int[]{0, 2, 4}, 2, 85, 340, 85, 178, 1000, 1500, 2);

                if (overtime(8, 0)) return;
            }

            boolean index = true;

            public void content_3() throws Exception {
                if (overtime(10, 2)) return;
                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightfqhs.png"});
                mFairy.onTap(0.8f, result, "右侧法器护送", Sleep);

                result = mFairy.findPic(40, 124, 282, 411, "leftfqhs.png");
                mFairy.onTap(0.7f, result, "左侧法器护送", Sleep);
                if (result.sim > 0.7f) {
                    result = mFairy.findPic(40, 124, 282, 411, "lefttianling.png");
                    if (result.sim > 0.7f) {
                        renwu = 1;
                        setTaskName(4);
                        return;
                    }
                    result = mFairy.findPic(40, 124, 282, 411, "leftdiling.png");
                    if (result.sim > 0.7f) {
                        renwu = 2;
                        setTaskName(4);
                        return;
                    }
                }
                Thread.sleep(1000);
            }

            int wcCount = 0;

            public void content_4() throws Exception {
                if (overtime(30, 0)) {
                    wcCount++;
                    return;
                }

              /* if (timekeep(0, 600000, "法器护送超时")) {
                    gameUtil.close(1);
                    result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                    mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                    result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                    mFairy.onTap(0.8f, result, 299, 589, 313, 597, "山妖", 7000);
                    index = true;
                }*/


                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightfqhs.png"});
                mFairy.onTap(0.8f, result, "右侧法器护送", Sleep);
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("法器护送完成"));
                    wcCount++;
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                if (picCount(0.8f, result, "法器护送活动标记") > 2) {
                    mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);
                }

                //在地图界面
                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {
                    if (wcCount > 0) {
                        wcCount = 0;
                        index = true;
                        for (int i = 0; i < 50; i++) {
                            mFairy.condit();
                            result = mFairy.findPic(159, 56, 1130, 675, new String[]{"Lbc.png", "Rbc.png"});
                            mFairy.onTap(0.8f, result, result.x, result.y, result.x + 50, result.y + 21, "去镖车", Sleep);
                            if (result.sim > 0.8f) {
                                break;
                            }
                            result = mFairy.findPic(159, 56, 1130, 675, "dbc.png");
                            mFairy.onTap(0.8f, result, result.x + 5, result.y, result.x + 6, result.y + 1, "去镖车", Sleep);
                            if (result.sim > 0.8f) {
                                break;
                            }
                            result = mFairy.findPic(159, 56, 1130, 675, "house.png");
                            mFairy.onTap(0.8f, result, result.x - 30, result.y + 10, result.x - 29, result.y + 11, "去镖车", Sleep);
                            if (result.sim > 0.8f) {
                                break;
                            }
                        }
                        gameUtil.close(1);
                        result1 = mFairy.findPic("look.png");
                        mFairy.onTap(0.8f, result1, "查看货物", Sleep);

                        result = mFairy.findPic("shanyao.png");
                        mFairy.onTap(0.8f, result, 972, 373, 999, 384, "山妖", Sleep);
                        gameUtil.zidong();
                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(1000);
                            mFairy.condit();
                            result = mFairy.findPic("fqhsyaoguai.png");
                            if (result.sim > 0.9f) {
                                LtLog.e(mFairy.getLineInfo("打妖怪中"));
                                i = 0;
                            }
                        }
                        result = mFairy.findPic("closehand.png");
                        mFairy.onTap(0.8f, result, "关闭自动", Sleep);
                    }

                    if (renwu == 1) {
                        result = mFairy.findPic(319, 128, 395, 179, "fqhsddmdd.png");
                        mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                        mFairy.onTap(0.8f, result, 652, 349, 664, 359, "点击天灵npc", Sleep);
                        if (result.sim > 0.8f) {
                            return;
                        }
                    }

                    if (renwu == 2) {
                        result = mFairy.findPic(645, 197, 717, 254, "fqhsddmdd.png");
                        mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                        mFairy.onTap(0.8f, result, 652, 349, 664, 359, "点击地宝npc", Sleep);
                        if (result.sim > 0.8f) {
                            return;
                        }
                    }

                    result = mFairy.findPic("shanyao.png");
                    mFairy.onTap(0.8f, result, 972, 373, 999, 384, "山妖", Sleep);

                    result1 = mFairy.findPic("look.png");
                    mFairy.onTap(0.8f, result1, "查看货物", Sleep);
                    if (result.sim > 0.8f || result1.sim > 0.8f) {
                        index = true;
                        for (int i = 0; i < 50; i++) {
                            mFairy.condit();
                            result = mFairy.findPic(159, 56, 1130, 675, new String[]{"Lbc.png", "Rbc.png"});
                            mFairy.onTap(0.8f, result, result.x + 8, result.y + 20, result.x + 9, result.y + 21, "去镖车", Sleep);
                            if (result.sim > 0.8f) {
                                break;
                            }
                            result = mFairy.findPic(159, 56, 1130, 675, "dbc.png");
                            mFairy.onTap(0.8f, result, result.x + 5, result.y, result.x + 6, result.y + 1, "去镖车", Sleep);
                            if (result.sim > 0.8f) {
                                break;
                            }
                            result = mFairy.findPic(159, 56, 1130, 675, "house.png");
                            mFairy.onTap(0.8f, result, result.x - 30, result.y + 10, result.x - 29, result.y + 11, "去镖车", Sleep);
                            if (result.sim > 0.8f) {
                                break;
                            }
                        }
                    /*    result = mFairy.findPic(159, 56, 1130, 675, new String[]{"Lbc.png", "Rbc.png", "dbc.png", "house.png"});
                        mFairy.onTap(0.8f, result, "去镖车", Sleep);
                        mFairy.onTap(0.8f, result, "去镖车", Sleep);
                        mFairy.onTap(0.8f, result, "去镖车", Sleep);
                        mFairy.onTap(0.8f, result, "去镖车", 5000);*/
                        gameUtil.close(1);
                        result1 = mFairy.findPic("look.png");
                        mFairy.onTap(0.8f, result1, "查看货物", Sleep);
                        result = mFairy.findPic("shanyao.png");
                        mFairy.onTap(0.8f, result, 972, 373, 999, 384, "山妖", Sleep);
                        gameUtil.zidong();
                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(1000);
                            mFairy.condit();
                            result = mFairy.findPic("fqhsyaoguai.png");
                            if (result.sim > 0.9f) {
                                LtLog.e(mFairy.getLineInfo("打妖怪中"));
                                i = 0;
                            }
                        }
                        result = mFairy.findPic("closehand.png");
                        mFairy.onTap(0.8f, result, "关闭自动", Sleep);
                    }
                    err = 0;

                    //第一次传送
                    result = mFairy.findPic("fqhsinjiazu.png");
                    mFairy.onTap(0.8f, result, 342, 626, 343, 627, "在家族去龙城", Sleep);

                    //第二次传送
                    result = mFairy.findPic("fqhsinlongcheng.png");
                    if (result.sim > 0.8f) {
                        if (renwu == 1) {
                            mFairy.onTap(0.8f, result, 1090, 614, 1091, 615, "在龙城去华山", Sleep);
                        }
                        if (renwu == 2) {
                            mFairy.onTap(0.8f, result, 1100, 134, 1101, 135, "在龙城去桃源", Sleep);
                        }
                    }

                    if (index) {
                        //第三次传送
                        result = mFairy.findPic("fqhsinhuashan.png");
                        mFairy.onTap(0.8f, result, 45, 230, 59, 240, "在华山打开查询", Sleep);
                        mFairy.onTap(0.8f, result, 123, 342, 153, 353, "在华山点天灵童子", Sleep);
                        if (result.sim > 0.8f) {
                            index = false;
                        }
                        result = mFairy.findPic("fqhsintaoyuan.png");
                        mFairy.onTap(0.8f, result, 45, 230, 59, 240, "在桃源村打开查询", Sleep);
                        mFairy.onTap(0.8f, result, 138, 457, 159, 466, "在桃源村点地灵童子", Sleep);
                        if (result.sim > 0.8f) {
                            index = false;
                        }
                    }
                }

            }
        }.taskContent(mFairy, "法器护送中");
    }//法器护送-(旧版)

    private int fqhs_err = 0;
    public int fq = 0;

    public void fqhs() throws Exception {
        new TimingActivity(mFairy) {
            @Override
            public void inOperation() throws Exception {
                super.inOperation();
                gameUtil.fuhuo();
                gameUtil.fqtask();
            }

            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("fqhs.png", 2);
                if (ret == 1) {
                    setTaskName(3);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            int renwu = 0;

            public void content_2() throws Exception {
                result = mFairy.findPic(40, 124, 282, 411, "leftfqhs.png");
                mFairy.onTap(0.7f, result, "左侧法器护送", Sleep);
                if (result.sim > 0.7f) {
                    if (renwu == 0) {
                        LtLog.e(mFairy.getLineInfo("未知前往目的地，转5查找"));
                        setTaskName(5);
                        return;
                    } else {
                        setTaskName(4);
                        return;
                    }
                }
                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);

                mFairy.taskSlid(err, new int[]{0, 2, 4}, 4, 85, 340, 85, 178, 1000, 1500, 2);

                if (overtime(8, 99)) return;
            }

            boolean index = true;

            public void content_3() throws Exception {
                if (overtime(10, 2)) return;
                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightfqhs.png"});
                mFairy.onTap(0.8f, result, "右侧法器护送", Sleep);

                result = mFairy.findPic(40, 124, 282, 411, "leftfqhs.png");
                mFairy.onTap(0.7f, result, "左侧法器护送", 1000);
                if (result.sim > 0.7f) {
                    if (renwu == 0) {
                        LtLog.e(mFairy.getLineInfo("未知前往目的地，转2查找"));
                        setTaskName(2);
                        return;
                    } else {
                        setTaskName(4);
                        return;
                    }
                }
                Thread.sleep(1000);
            }

            int wcCount = 0;

            public void content_4() throws Exception {
                if (overtime(30, 0)) {
                    wcCount++;
                    return;
                }

                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightfqhs.png"});
                mFairy.onTap(0.8f, result, "右侧法器护送", Sleep);
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("法器护送完成"));
                    wcCount++;
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                if (picCount(0.8f, result, "法器护送活动标记") > 2) {
                    mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);
                }

                //在地图界面
                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {

                    if (wcCount > 0) {
                        wcCount = 0;
                        index = true;
                        for (int i = 0; i < 50; i++) {
                            mFairy.condit();
                            result = mFairy.findPic(159, 56, 1130, 675, new String[]{"Lbc.png", "Rbc.png"});
                            mFairy.onTap(0.8f, result, result.x, result.y, result.x + 50, result.y + 21, "去镖车", 3000);
                            if (result.sim > 0.8f) {
                                break;
                            }
                            result = mFairy.findPic(159, 56, 1130, 675, "dbc.png");
                            mFairy.onTap(0.8f, result, result.x + 5, result.y, result.x + 6, result.y + 1, "去镖车", 3000);
                            if (result.sim > 0.8f) {
                                break;
                            }
                            result = mFairy.findPic(159, 56, 1130, 675, "house.png");
                            mFairy.onTap(0.8f, result, result.x - 30, result.y + 10, result.x - 29, result.y + 11, "去镖车", 3000);
                            if (result.sim > 0.8f) {
                                break;
                            }
                        }

                        gameUtil.close(1);


                        while (mFairy.condit()) {
                            Thread.sleep(2000);

                            LtLog.e(mFairy.getLineInfo("等待到达镖车中 "));

                            long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                            if (dazeTime > 6) {
                                break;
                            }

                            result1 = mFairy.findPic("look.png");
                            if (result1.sim > 0.8f) {
                                mFairy.onTap(0.8f, result1, "查看货物", Sleep);
                                break;
                            }

                            result = mFairy.findPic("shanyao.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, 972, 373, 999, 384, "山妖", Sleep);
                                break;
                            }
                        }

                        result1 = mFairy.findPic("look.png");
                        mFairy.onTap(0.8f, result1, "查看货物", Sleep);

                        result = mFairy.findPic("shanyao.png");
                        mFairy.onTap(0.8f, result, 972, 373, 999, 384, "山妖", Sleep);

                        gameUtil.zidong();

                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(1000);
                            mFairy.condit();
                            result = mFairy.findPic("fqhsyaoguai.png");
                            if (result.sim > 0.9f) {
                                LtLog.e(mFairy.getLineInfo("打妖怪中"));
                                i = 0;
                            }
                        }

                        result = mFairy.findPic("closehand.png");
                        mFairy.onTap(0.8f, result, "关闭自动", Sleep);
                    }

                    if (renwu == 1) {
                        result = mFairy.findPic(319, 128, 395, 179, "fqhsddmdd.png");
                        mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                        mFairy.onTap(0.8f, result, 657, 374, 663, 383, "点击天灵npc", Sleep);
                        if (result.sim > 0.8f) {
                            return;
                        }
                    }

                    if (renwu == 2) {
                        result = mFairy.findPic(645, 197, 717, 254, "fqhsddmdd.png");
                        mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                        mFairy.onTap(0.8f, result, 652, 349, 664, 359, "点击地宝npc", Sleep);
                        if (result.sim > 0.8f) {
                            fq++;
                            if (fq > 5) {
                                mFairy.onTap(620, 229, 639, 258, "点击", 1000);
                                fq = 0;
                            }
                            return;
                        }
                    }

                    result = mFairy.findPic("shanyao.png");
                    mFairy.onTap(0.8f, result, 972, 373, 999, 384, "山妖", Sleep);

                    result1 = mFairy.findPic("look.png");
                    mFairy.onTap(0.8f, result1, "查看货物", Sleep);

                    if (result.sim > 0.8f || result1.sim > 0.8f) {
                        index = true;
                        for (int i = 0; i < 50; i++) {
                            mFairy.condit();
                            result = mFairy.findPic(159, 56, 1130, 675, new String[]{"Lbc.png", "Rbc.png"});
                            mFairy.onTap(0.8f, result, result.x + 8, result.y + 20, result.x + 9, result.y + 21, "去镖车", 5000);
                            if (result.sim > 0.8f) {
                                break;
                            }
                            result = mFairy.findPic(159, 56, 1130, 675, "dbc.png");
                            mFairy.onTap(0.8f, result, result.x + 5, result.y, result.x + 6, result.y + 1, "去镖车", 5000);
                            if (result.sim > 0.8f) {
                                break;
                            }
                            result = mFairy.findPic(159, 56, 1130, 675, "house.png");
                            mFairy.onTap(0.8f, result, result.x - 30, result.y + 10, result.x - 29, result.y + 11, "去镖车", 5000);
                            if (result.sim > 0.8f) {
                                break;
                            }
                        }
                    /*    result = mFairy.findPic(159, 56, 1130, 675, new String[]{"Lbc.png", "Rbc.png", "dbc.png", "house.png"});
                        mFairy.onTap(0.8f, result, "去镖车", Sleep);
                        mFairy.onTap(0.8f, result, "去镖车", Sleep);
                        mFairy.onTap(0.8f, result, "去镖车", Sleep);
                        mFairy.onTap(0.8f, result, "去镖车", 5000);*/
                        gameUtil.close(1);
                        result1 = mFairy.findPic("look.png");
                        mFairy.onTap(0.8f, result1, "查看货物", Sleep);
                        result = mFairy.findPic("shanyao.png");
                        mFairy.onTap(0.8f, result, 972, 373, 999, 384, "山妖", Sleep);

                        long timex = System.currentTimeMillis();

                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(1000);
                            mFairy.condit();

                            if (System.currentTimeMillis() - timex > 30000) {
                                timex = System.currentTimeMillis();
                                mFairy.touchDown(173, 572);
                                mFairy.touchMove(284, 572, 500, 1000);
                                mFairy.touchUp();
                            }

                            gameUtil.zidong();

                            result = mFairy.findPic("fqhsyaoguai.png");
                            if (result.sim > 0.9f) {
                                LtLog.e(mFairy.getLineInfo("打妖怪中"));
                                i = 0;
                            }
                        }
                        result = mFairy.findPic("closehand.png");
                        mFairy.onTap(0.8f, result, "关闭自动", Sleep);
                    }

                    err = 0;

                    fqhs_err++;
                    if (fqhs_err > 20) {
                        fqhs_err = 0;
                        index = true;
                    }

                    //第一次传送
                    result = mFairy.findPic(new String[]{"fqhsinjiazu.png", "jiazu1.png"});
                    mFairy.onTap(0.8f, result, 342, 626, 343, 632, "在家族去龙城", Sleep);

                    //第二次传送
                    result = mFairy.findPic("fqhsinlongcheng.png");
                    if (result.sim > 0.8f) {
                        if (renwu == 1) {
                            mFairy.onTap(0.8f, result, 1090, 614, 1091, 615, "在龙城去华山", Sleep);
                        }
                        if (renwu == 2) {
                            mFairy.onTap(0.8f, result, 1100, 134, 1101, 135, "在龙城去桃源", Sleep);
                        }
                    }


                    if (index) {
                        //第三次传送
                        result = mFairy.findPic("fqhsinhuashan.png");
                        if (result.sim > 0.8f) {
                            index = false;
                            mFairy.onTap(0.8f, result, 45, 230, 59, 240, "在华山打开查询", Sleep);
                            mFairy.onTap(0.8f, result, 123, 342, 153, 353, "在华山点天灵童子", Sleep);
                        }

                        result = mFairy.findPic("fqhsintaoyuan.png");
                        if (result.sim > 0.8f) {

                            mFairy.onTap(0.8f, result, 45, 230, 59, 240, "在桃源村打开查询", Sleep);
                            mFairy.onTap(0.8f, result, 138, 457, 159, 466, "在桃源村点地灵童子", Sleep);

                            index = false;
                        }
                    }
                }
            }

            @Override
            public void content_5() throws Exception {
                result = mFairy.findPic(new String[]{"task1.png", "task.png"});
                mFairy.onTap(0.8f, result, "进入任务栏", 1000);

                result = mFairy.findPic("task interface.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(94, 196, 253, 664, "task fqhs.png");
                    mFairy.onTap(0.8f, result, "切换法器护送", 1000);

                    result = mFairy.findPic("task huashan.png");
                    mFairy.onTap(0.8f, result, 1170, 85, 1183, 100, "目标-华山-天灵童子，关闭", 1000);
                    if (result.sim > 0.8f) {
                        renwu = 1;
                        setTaskName(3);
                        return;
                    }

                    result = mFairy.findPic("task taoyuancun.png");
                    mFairy.onTap(0.8f, result, 1170, 85, 1183, 100, "目标-桃源村-地宝童子，关闭", 1000);
                    if (result.sim > 0.8f) {
                        renwu = 2;
                        setTaskName(3);
                        return;
                    }
                }
                overtime(8, 0);
            }
        }.taskContent(mFairy, "法器护送中");
    }//法器护送

    public void lldb() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                if (m > 30) {
                    LtLog.e(mFairy.getLineInfo("楼兰夺宝结束"));
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                if (overtime(10, 0)) {
                    gameUtil.close(0);
                    return;
                }

                result = mFairy.findPic(1122, 141, 1276, 283, "zhankai.png");
                mFairy.onTap(0.8f, result, "展开", Sleep);

                result = mFairy.findPic(767, 443, 1275, 712, "jz.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "打开家族", Sleep);
                    mFairy.onTap(0.8f, result, 1171, 85, 1178, 95, "关闭家族", Sleep);
                    setTaskName(1);
                    return;
                }
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("lldb.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    ret = gameUtil.mission("lldb1.png", 2);
                    if (ret == 1) {
                        setTaskName(2);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
            }

            public void content_2() throws Exception {
                if (overtime(15, 0)) {
                    gameUtil.close(0);
                    return;
                }
                gameUtil.fuhuo();
                result = mFairy.findPic(1050, 206, 1102, 257, "copy.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("楼兰夺宝副本内"));

                    result1 = mFairy.findPic(107, 106, 986, 659, "ypbx.png");
                    mFairy.onTap(0.7f, result1, result1.x, result1.y + 80, result1.x + 17, result1.y + 90, "银牌宝箱", 5000);

                    result = mFairy.findPic(107, 106, 986, 659, "smwg.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.7f, result, result.x, result.y + 80, result.x + 17, result.y + 90, "瓦罐", 5000);
                    }

                    if (result.sim < 0.8f && result1.sim < 0.8f) {
                        result = mFairy.findPic(1218, 342, 1259, 390, "hand.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 200);
                            mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 200);
                            mFairy.onTap(0.8f, result, 1042, 483, 1057, 497, "技能3", 200);
                            mFairy.onTap(0.8f, result, 1008, 568, 1024, 585, "技能4", 200);
                            mFairy.onTap(0.8f, result, 1049, 655, 1064, 671, "技能5", 200);
                            mFairy.onTap(0.8f, result, 959, 656, 972, 663, "技能6", 200);
                            mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                            mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                            mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 200);
                            mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 200);
                            mFairy.onTap(0.8f, result, 1042, 483, 1057, 497, "技能3", 200);
                            mFairy.onTap(0.8f, result, 1008, 568, 1024, 585, "技能4", 200);
                            mFairy.onTap(0.8f, result, 1049, 655, 1064, 671, "技能5", 200);
                            mFairy.onTap(0.8f, result, 959, 656, 972, 663, "技能6", 200);
                            mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                            mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                        }
                    }
                    long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                    if (dazeTime > 10) {
                        mFairy.initMatTime();
                        result = mFairy.findPic(1050, 206, 1102, 257, "copy.png");
                        mFairy.onTap(0.8f, result, 1201, 103, 1205, 114, "打开地图", 1000);
                        mFairy.onTap(0.8f, result, 587, 419, 592, 423, "地图中间", 1000);
                        mFairy.onTap(0.8f, result, 1236, 45, 1243, 52, "关闭地图", 1000);
                    }

                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork1.png"});
                    mFairy.onTap(0.85f, result, "关叉1", Sleep);

                    result = mFairy.findPic(1029, 79, 1124, 137, "csds0.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("传送倒数0退出"));
                        result = mFairy.findPic(1050, 206, 1102, 257, "copy.png");
                        mFairy.onTap(0.7f, result, "退出", 2000);
                    }
                    result = mFairy.findPic(1011, 74, 1139, 260, "lldbstop.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("楼兰夺宝结束"));
                        gameUtil.lkfb();
                        if (AtFairyConfig.getOption("zhanchang").equals("1") && mFairy.dateHour() == 12) {
                            zhanchang();
                            zhanchang();
                        }
                        setTaskEnd();
                        return;
                    }
                }
            }
        }.taskContent(mFairy, "楼兰夺宝中");
    }//楼兰夺宝

    public void ygbh() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (h >= 21) {
                    LtLog.e(mFairy.getLineInfo("月光宝盒结束"));
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.goCity("家族");
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 564, 457, 581, 466, "在家族去中心点打怪", 5000);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                if (result.sim > 0.8f) {
                    gameUtil.zidong();
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (timekeep(0, 600000, "月光宝盒超过10分钟没有在副本")) {
                    LtLog.e(mFairy.getLineInfo("--------超过10分钟没有在副本"));
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("ygbh.png");
                mFairy.onTap(0.8f, result, "月光宝盒", Sleep);

                result = mFairy.findPic("ygbhinface.png");
                mFairy.onTap(0.8f, result, 606, 280, 643, 295, "月光宝盒界面去积累山", Sleep);

                result = mFairy.findPic(924, 79, 1216, 379, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("月光宝盒副本内"));
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(15, 0)) return;
                LtLog.e(mFairy.getLineInfo("在第一层等待进入第二层"));
                result = mFairy.findPic(924, 79, 1216, 379, "copy.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("ygbh1ceng.png");
                    mFairy.onTap(0.7f, result, 1189, 91, 1209, 105, "打开地图", 3000);

                    result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                    mFairy.onTap(0.8f, result, 244, 112, 254, 120, "月光宝盒去第二层", 4000);
                    if (result.sim > 0.8f) {
                        gameUtil.close(1);
                        Thread.sleep(90000);
                        setTaskName(4);
                    }
                }
            }

            public void content_4() throws Exception {
                if (overtime(15, 0)) return;
                LtLog.e(mFairy.getLineInfo("在第二层等待进入第三层"));
                result = mFairy.findPic(924, 79, 1216, 379, "copy.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("ygbh1ceng.png");
                    mFairy.onTap(0.7f, result, 1189, 91, 1209, 105, "打开地图", 3000);

                    result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                    mFairy.onTap(0.8f, result, 647, 116, 654, 124, "月光宝盒去第三层", 4000);
                    if (result.sim > 0.8f) {
                        gameUtil.close(1);
                        Thread.sleep(90000);
                        setTaskName(5);
                    }
                }
            }

            public void content_5() throws Exception {
                if (overtime(15, 99)) {
                    LtLog.e(mFairy.getLineInfo("月光宝盒结束"));
                    return;
                }
                LtLog.e(mFairy.getLineInfo("在第三层"));
                result = mFairy.findPic(924, 79, 1216, 379, "copy.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }
            }
        }.taskContent(mFairy, "月光宝盒中");
    }//月光宝盒

    public void zhanchang() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (m > 40) {
                    LtLog.e(mFairy.getLineInfo("修罗战场结束"));
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                timekeepInit("超过10分钟没有进入战场");
                if (overtime(10, 0)) {
                    gameUtil.close(0);
                    return;
                }
                result = mFairy.findPic(1144, 2, 1254, 29, "llfc.png");
                if (result.sim > 0.8f) {
                    gameUtil.lkfb();
                }

                result = mFairy.findPic(1122, 141, 1276, 283, "zhankai.png");
                mFairy.onTap(0.8f, result, "展开", Sleep);

                result = mFairy.findPic(767, 443, 1275, 712, "jz.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "打开家族", Sleep);
                    mFairy.onTap(0.8f, result, 1171, 85, 1178, 95, "关闭家族", Sleep);
                    gameUtil.close(0);
                    setTaskName(1);
                    return;
                }

            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("zhanchang.png", 2);//cfzc1
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            int bmcount = 0;

            public void content_2() throws Exception {
                if (overtime(8, 0)) return;
                Thread.sleep(2000);
                if (timekeep(0, 720000, "超过10分钟没有进入战场")) {
                    LtLog.e(mFairy.getLineInfo("--------超过10分钟没有进入战场"));
                    setTaskName(0);
                    return;
                }
                timekeepInit("超过10分钟没有进入战场");
                result = mFairy.findPic(807, 218, 950, 438, "zcquxiao.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "战场已报名等待开站"));
                if (result.sim < 0.8f) {
                    result = mFairy.findPic(803, 233, 956, 294, "zcbaoming.png");
                    mFairy.onTap(0.8f, result, "战场报名", Sleep);

                    result = mFairy.findPic(814, 289, 963, 353, "zcbaoming.png");
                    mFairy.onTap(0.8f, result, "战场报名", Sleep);


                    result = mFairy.findPic(813, 353, 968, 416, "zcbaoming.png");
                    mFairy.onTap(0.8f, result, "战场报名", Sleep);
                } else {
                    mFairy.onTap(0.8f, result, 1232, 203, 1246, 220, "报名成功", Sleep);
                    err = 0;
                    bmcount = 1;
                }
                if (bmcount == 1) {
                    err = 0;
                    gameUtil.zidong();
                }

                result = mFairy.findPic("zhanchangqueren.png");
                mFairy.onTap(0.8f, result, 751, 444, 783, 457, "战场确认参加", Sleep);


                result = mFairy.findPic(924, 43, 1143, 254, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("战场副本内"));
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(8, 0)) return;

                result = mFairy.findPic(924, 43, 1143, 254, "copy.png");
                mFairy.onTap(0.8f, result, 1188, 94, 1208, 112, "战场打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {
                    timekeepInit("超过10分钟没有进入战场");

                    result = mFairy.findPic(94, 53, 1192, 676, new String[]{"chongfengwupin.png", "chongfengwupin1.png"});
                    mFairy.onTap(0.9f, result, "战场冲锋球", Sleep);
                    if (result.sim < 0.9f) {
                        mFairy.onTap(578, 359, 579, 360, "可能在修罗战场去地图中心", Sleep);
                    }
                    mFairy.onTap(1228, 40, 1248, 55, "关闭地图", Sleep);
                    setTaskName(4);
                    return;
                }

                result = mFairy.findPic("fuhuotime.png");
                if (result.sim > 0.75f) {
                    LtLog.e(mFairy.getLineInfo("战场复活"));
                    Thread.sleep(8000);
                }

                result = mFairy.findPic("sile.png");
                if (result.sim > 0.82f) {
                    LtLog.e(mFairy.getLineInfo("战场复活"));
                    Thread.sleep(12000);
                }

                result = mFairy.findPic("zc1.png");
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("zhanchangls").equals("1")) {
                        mFairy.onTap(799, 610, 830, 628, "双倍领取", Sleep);
                    } else {
                        mFairy.onTap(1219, 459, 1232, 470, "普通领取", Sleep);
                    }
                    setTaskEnd();
                    return;
                }
            }

            public void content_4() throws Exception {
                long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                if (dazeTime > 2) {
                    mFairy.initMatTime();
                    LtLog.e(mFairy.getLineInfo("到达目的地释放技能"));
                    setTaskName(5);
                    return;
                }
            }

            public void content_5() throws Exception {
                if (overtime(10, 3)) return;
                result = mFairy.findPic(924, 43, 1143, 254, "copy.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 200);
                    mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 200);
                    mFairy.onTap(0.8f, result, 1042, 483, 1057, 497, "技能3", 200);
                    mFairy.onTap(0.8f, result, 1008, 568, 1024, 585, "技能4", 200);
                    mFairy.onTap(0.8f, result, 1049, 655, 1064, 671, "技能5", 200);
                    mFairy.onTap(0.8f, result, 959, 656, 972, 663, "技能6", 200);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                }

                result = mFairy.findPic("fuhuotime.png");
                if (result.sim > 0.75f) {
                    LtLog.e(mFairy.getLineInfo("战场复活"));
                    Thread.sleep(8000);
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic("sile.png");
                if (result.sim > 0.82f) {
                    LtLog.e(mFairy.getLineInfo("战场复活"));
                    Thread.sleep(12000);
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic("zhanchangsb.png");
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("zhanchangls").equals("1")) {
                        mFairy.onTap(799, 610, 830, 628, "双倍领取", Sleep);
                    } else {
                        mFairy.onTap(1219, 459, 1232, 470, "普通领取", Sleep);
                    }
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "战场中");
    }//冲锋战场

    public void jzkd() throws Exception {
        new TimingActivity(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("jzkd.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            boolean openmap = true;

            public void content_2() throws Exception {
                if (overtime(30, 0)) return;
                Thread.sleep(1000);

                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightjzkd.png", "jzkd1.png"});
                mFairy.onTap(0.8f, result, "右侧矿洞", Sleep);


                result = mFairy.findPic(1030, 77, 1127, 168, "copy.png");
                mFairy.onTap(0.8f, result, 1196, 98, 1211, 111, "矿洞中打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 642, 318, 643, 319, "去矿洞中间", Sleep);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", 5000);
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("矿洞中"));
                    setTaskName(3);
                    return;
                }
            }

            int hjkcount = 0;

            public void content_3() throws Exception {
                if (overtime(30, 0)) return;
                Thread.sleep(1000);
                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightjzkd.png", "jzkd1.png"});
                mFairy.onTap(0.8f, result, "右侧矿洞", Sleep);

                result = mFairy.findPic(1030, 77, 1127, 168, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("开始挖矿中"));
                    for (int j = 0; j < 5; j++) {
                        result = mFairy.findPic(272, 84, 1016, 667, new String[]{"jk.png", "jk1.png", "jk2.png"});
                        mFairy.onTap(0.8f, result, result.x - 25, result.y + 91, result.x - 19, result.y + 72, "水晶矿", 4000);
                        if (result.sim > 0.8f) {
                            hjkcount++;
                            if (hjkcount > 20) {
                                gameUtil.close(3);
                                for (int i = 0; i < 3; i++) {
                                    result = mFairy.findPic(862, 308, 1104, 641, "use1.png");
                                    if (result.sim > 0.8f) {
                                        i = 0;
                                        mFairy.onTap(0.8f, result, "使用", 1000);
                                    }
                                }
                                hjkcount = 0;
                                setTaskName(2);
                                return;
                            }
                            err = 0;
                        }
                    }
                }
                result = mFairy.findPic(794, 56, 1128, 177, new String[]{"jzkdstop.png", "jzkdstop1.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("家族矿洞结束"));
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "家族矿洞中");

    }//家族矿洞

    public void jzwq() throws Exception {
        new TimingActivity(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("jzwq.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            boolean openmap = true;

            public void content_2() throws Exception {
                if (overtime(20, 0)) return;
                Thread.sleep(1000);
                result = mFairy.findPic(549, 4, 989, 87, "zoom2.png");
                mFairy.onTap(0.8f, result, "关闭缩放栏", Sleep);

                result1 = mFairy.findPic(284, 72, 859, 282, "pwq.png");
                if (result1.sim > 0.8f) {
                    result = mFairy.findPic(1197, 245, 1273, 335, "xiama.png");
                    mFairy.onTap(0.8f, result, "下马", Sleep);
                    mFairy.onTap(0.8f, result1, "泡温泉", Sleep);
                }

                result = mFairy.findPic(383, 33, 1225, 542, "zhedang.png");
                mFairy.onTap(0.8f, result, 17, 431, 21, 440, "遮挡点击", Sleep);

                result = mFairy.findPic(562, 283, 720, 433, "paopao.png");
                if (result.sim > 0.8f) {
                    mFairy.touchDown(2, 640, 358);
                    mFairy.touchMove(2, 640, 358, 4000);
                    mFairy.touchUp(2);
                    err = 0;
                }

                result = mFairy.findPic(485, 95, 778, 180, "xzyz.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(413, 568, 862, 692, "quxiao2.png");
                    mFairy.onTap(0.8f, result, "取消", Sleep);
                }

                result = mFairy.findPic(484, 61, 1091, 422, new String[]{"wenquan.png", "wenquan2.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("温泉中"));
                    err = 0;
                }
                result = mFairy.findPic(777, 162, 860, 327, "likai1.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("温泉中"));
                    err = 0;
                }
                result = mFairy.findPic(552, 114, 748, 275, "xq.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("奖励中"));
                    err = 0;
                } else {
                    result = mFairy.findPic(739, 224, 768, 251, new String[]{"3.33.png"});
                    //  if (picCount(0.8f, result, "材料已装填") > 5) {
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(484, 61, 1091, 422, "likai1.png");
                        mFairy.onTap(0.8f, result, "离开", 2000);
                        LtLog.e(mFairy.getLineInfo("离开中"));
                        result = mFairy.findPic(484, 61, 1091, 422, "likai1.png");
                        mFairy.onTap(0.8f, result, "离开", 2000);
                        setTaskEnd();
                        return;
                    }
                    // }
                }
            }
        }.taskContent(mFairy, "家族温泉中");

    }//家族温泉

    public void jzqd() throws Exception {
        new TimingActivity(mFairy) {

            public void inOperation() throws Exception {
                super.inOperation();
                if (mFairy.dateMinute() >= 30) {
                    LtLog.e(mFairy.getLineInfo("没有家族强盗了结束"));
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.goCity("家族");
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;

                int ret = gameUtil.mission("jzsw.png", 2);
                if (ret == 1) {
                    if (gameUtil.duizhang() == 1) {
                        setTaskName(3);
                        return;
                    }
                } else {
                    setTaskEnd();
                    return;
                }


            }

            boolean openlan = true;

            public void content_2() throws Exception {
                if (overtime(7, 0)) {
                    return;
                }
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("mbswjz.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("目标正确"));
                        result = mFairy.findPic("dzqxpp.png");
                        mFairy.onTap(0.8f, result, "先取消一下自动匹配", Sleep);
                        result = mFairy.findPic("dzzdpp.png");
                        mFairy.onTap(0.8f, result, "开启自动匹配", Sleep);
                        setTaskName(3);
                        return;
                    } else {
                        result = mFairy.findPic(79, 107, 270, 599, "dshd.png");
                        mFairy.onTap(0.95f, result, "找到定时活动", Sleep);

                        result = mFairy.findPic(79, 107, 270, 599, "jzdd.png");
                        mFairy.onTap(0.8f, result, "找到家族大盗", Sleep);
                        mFairy.onTap(0.8f, result, 162, 615, 191, 628, "设为目标", Sleep);
                        if (result.sim < 0.8f) {
                            mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5}, 0, 171, 541, 173, 160, 200, 1500, 2);
                        }
                    }
                }
            }

            public void content_3() throws Exception {
                if (overtime(8, 0)) {
                    return;
                }
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    int mannum = 0;
                    result = mFairy.findPic(461, 485, 645, 520, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(648, 484, 828, 518, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(832, 486, 1010, 521, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }
                    result = mFairy.findPic(1012, 485, 1190, 520, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }
                    if (mannum > 1) {
                        gameUtil.callToFollow();
                        gameUtil.kicking();
                        LtLog.e(mFairy.getLineInfo("人满了出发"));
                        setTaskName(4);
                        return;
                    } else {
                        setTaskName(2);
                    }
                    gameUtil.shenqing();
                    if (timekeep(1, 120000, "2分钟招募一下")) {
                        LtLog.e(mFairy.getLineInfo("--------2分钟招募一下"));
                        gameUtil.yjhh();
                    }
                    if (timekeep(0, 600000, "家族强盗超过10分钟没组到人")) {
                        LtLog.e(mFairy.getLineInfo("--------超过10分钟没组到人"));
                        setTaskName(0);
                        return;
                    }
                }
            }

            public void content_4() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1196, 98, 1211, 111, "打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {
                    Thread.sleep(2000);
                    mFairy.condit();
                    result = mFairy.findPic(200, 58, 1089, 676, "qdnpc.png");
                    mFairy.onTap(0.7f, result, result.x + 3, result.y + 27, result.x + 4, result.y + 28, "点击强盗", Sleep);
                    mFairy.onTap(0.7f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                    if (result.sim < 0.7f) {
                        LtLog.e(mFairy.getLineInfo("没有强盗了"));
                        setTaskEnd();
                        return;
                    }
                    setTaskName(5);
                    return;
                }
            }

            int juliCount = 0;

            public void content_5() throws Exception {
                long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                if (dazeTime > 3) {
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    mFairy.initMatTime();

                    result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                    mFairy.onTap(0.8f, result, 591, 371, 611, 390, "点击强盗", 2000);

                    result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightjzqd.png"});
                    if (result.sim > 0.8f) {
                        Thread.sleep(7000);
                        mFairy.condit();
                        result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightjzqd.png"});
                        mFairy.onTap(0.8f, result, "右侧少废话看招", 500);
                    }

                    result = mFairy.findPic(449, 436, 814, 578, "julitaiyuan.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("距离太远"));
                        setTaskName(2);
                        return;
                    }
                    result = mFairy.findPic(449, 436, 814, 578, "rsbuzu.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("--------人数不足"));
                        setTaskName(2);
                        return;
                    }
                    setTaskName(6);
                    return;
                }
            }

            public void content_6() throws Exception {
                if (overtime(8, 4)) {
                    gameUtil.callToFollow();
                    return;
                }
                gameUtil.fuhuo();
                result = mFairy.findPic(1030, 77, 1127, 168, "copy.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    gameUtil.zidong();
                }
                result = mFairy.findPic(759, 418, 915, 527, "noqdjl.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("没有奖励"));
                    jzqd1 = 1;
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "家族强盗中");
    }//家族强盗

    public void sjzy() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("sjzy1.png");
                if (result.sim < 0.8f) {
                    if (mFairy.dateMinute() >= 10) {
                        LtLog.e(mFairy.getLineInfo("没有赏金追妖了结束"));
                        setTaskEnd();
                        return;
                    }
                }
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.goCity("出云");
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                if (gameUtil.duizhang() == 1) {
                    setTaskName(3);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(7, 0)) {
                    return;
                }
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("mbsjzy.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("目标正确"));
                        result = mFairy.findPic("dzqxpp.png");
                        mFairy.onTap(0.8f, result, "先取消一下自动匹配", Sleep);
                        result = mFairy.findPic("dzzdpp.png");
                        mFairy.onTap(0.8f, result, "开启自动匹配", Sleep);
                        setTaskName(3);
                        return;
                    } else {
                        result = mFairy.findPic(79, 107, 270, 599, "dshd.png");
                        mFairy.onTap(0.95f, result, "找到定时活动", Sleep);

                        result = mFairy.findPic(79, 107, 270, 599, "sjzy.png");
                        mFairy.onTap(0.8f, result, "找到赏金追妖", Sleep);
                        mFairy.onTap(0.8f, result, 162, 615, 191, 628, "设为目标", Sleep);
                        if (result.sim < 0.8f) {
                            mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5}, 0, 171, 541, 173, 160, 200, 1500, 2);
                        }
                    }
                }
            }

            public void content_3() throws Exception {
                if (overtime(8, 0)) {
                    return;
                }
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    int mannum = 0;
                    result = mFairy.findPic(461, 485, 645, 520, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(648, 484, 828, 518, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(832, 486, 1010, 521, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }
                    result = mFairy.findPic(1012, 485, 1190, 520, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }
                    if (mannum > 1) {
                        gameUtil.callToFollow();
                        LtLog.e(mFairy.getLineInfo("人满了出发"));
                        setTaskName(4);
                        return;
                    } else {
                        setTaskName(2);
                    }
                    gameUtil.shenqing();
                    if (timekeep(1, 120000, "2分钟招募一下")) {
                        LtLog.e(mFairy.getLineInfo("--------2分钟招募一下"));
                        gameUtil.yjhh();
                    }
                    if (timekeep(0, 600000, "赏金追妖超过10分钟没组到人")) {
                        LtLog.e(mFairy.getLineInfo("--------超过10分钟没组到人"));
                        setTaskName(0);
                        return;
                    }
                }
            }

            public void content_4() throws Exception {

                result = mFairy.findPic("sjzy1.png");
                mFairy.onTap(0.8f, result, "赏金追妖刷新出来了", Sleep);

                if (result.sim < 0.8f) {
                    if (mFairy.dateMinute() >= 10) {
                        LtLog.e(mFairy.getLineInfo("没有赏金追妖了结束"));
                        setTaskEnd();
                        return;
                    }
                }
                result = mFairy.findPic("sjzyqw.png");
                mFairy.onTap(0.8f, result, "赏金追妖前往", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(5);
                    return;
                }
            }

            public void content_5() throws Exception {
                long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                if (dazeTime > 3) {
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    mFairy.initMatTime();
                    result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightsjzy.png"});
                    if (result.sim > 0.8f) {
                        Thread.sleep(7000);
                        mFairy.condit();
                        result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightsjzy.png"});
                        mFairy.onTap(0.8f, result, "右侧妖怪哪里跑", 500);
                    }
                    result = mFairy.findPic(759, 418, 915, 527, "nojl.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("没有奖励"));
                        sjzy1 = 1;
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(449, 436, 814, 578, "julitaiyuan.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("距离太远"));
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic(449, 436, 814, 578, "rsbuzu.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("--------人数不足"));
                        setTaskName(2);
                        return;
                    }
                    setTaskName(6);
                    return;
                }
            }

            public void content_6() throws Exception {
                if (overtime(8, 4)) {
                    gameUtil.callToFollow();
                    return;
                }
                result = mFairy.findPic("shjzyboss.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("打boss中"));
                }
            }
        }.taskContent(mFairy, "赏金追妖中");
    }//赏金追妖

    public void yjdx() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (h == 23 && m > 40) {
                    LtLog.e(mFairy.getLineInfo("一骑当先结束"));
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("yjdx.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            int ppcount = 0;

            public void content_2() throws Exception {
                if (overtime(60, 0)) return;
                Thread.sleep(1000);
                result = mFairy.findPic(1008, 13, 1266, 601, "rightyjdx.png");
                mFairy.onTap(0.8f, result, "右侧一骑当先", Sleep);

                result = mFairy.findPic("yjdxpipei.png");
                mFairy.onTap(0.8f, result, "一骑当先匹配", 1000);

                result = mFairy.findPic(835, 518, 1250, 709, "kspp.png");
                if (result.sim > 0.8f) {
                    ppcount++;
                    if (ppcount >= 3) {
                        LtLog.e(mFairy.getLineInfo("一骑当先两次了结束"));
                        gameUtil.lkfb();
                        setTaskEnd();
                        return;
                    }
                    mFairy.onTap(0.8f, result, "一骑当先匹配", 20000);
                }


                result = mFairy.findPic("yjdxzhandouzhong.png");
                mFairy.onTap(0.8f, result, 1196, 98, 1211, 111, "战斗中打开地图", Sleep);


                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 429, 315, 438, 321, "随便去个地方挂机", Sleep);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    return;
                }
                result = mFairy.findPic("yjdxlikai.png");
                mFairy.onTap(0.8f, result, "一骑当先离开", 10000);
            }

            public void content_3() throws Exception {
                if (overtime(10, 2)) return;
             /*   result = mFairy.findPic("yjdxzhandouzhong.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }*/
                result = mFairy.findPic(207, 77, 1138, 580, "yjdxbaohe.png");
                mFairy.onTap(0.8f, result, result.x - 34, result.y + 77, result.x - 33, result.y + 78, "一骑当先宝盒", 5000);

                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    return;
                }

                result = mFairy.findPic("hand.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 100);
                    mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 100);
                    mFairy.onTap(0.8f, result, 1021, 527, 1042, 543, "技能3", 100);
                    mFairy.onTap(0.8f, result, 1016, 636, 1032, 651, "技能4", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                }

                result = mFairy.findPic("yjdxlikai.png");
                mFairy.onTap(0.8f, result, "一骑当先离开", 10000);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
            }
        }.taskContent(mFairy, "一骑当先中");

    }//一骑当先

    public void yjdx_test() throws Exception {
        new TimingActivity(mFairy) {
            Integer fightType = 1;//打怪方式:1、地图打怪。2、原地打怪
            Integer fightCount = 2;//参与次数

            @Override
            public void create() throws Exception {
                if (!AtFairyConfig.getOption("yjdxType").equals("")) {
                    fightType = Integer.parseInt(AtFairyConfig.getOption("yjdxType"));
                }

                if (!AtFairyConfig.getOption("yjdxCount").equals("")) {
                    fightCount = Integer.parseInt(AtFairyConfig.getOption("yjdxCount"));
                    if (fightCount == 0) fightCount = 2;
                    fightCount++;
                }
            }

            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (!((h == 12 && m == 59) || (h == 13 && m <= 40) || (h >= 14 && h <= 16) || (h == 20 && m == 59) || (h == 21 && m <= 40))) {
                    LtLog.e("任务时间已过 结束任务！");
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                result1 = mFairy.findPic(845, 76, 1136, 178, "likai1.png");
                if (result.sim > 0.8f && result1.sim > 0.8f) {
                    setTaskName(2);
                    return;
                } else {
                    gameUtil.goCity("猫猫城");
                    setTaskName(1);
                    return;
                }
            }

            public void content_1() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic(93, 205, 343, 614, "mdk.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "瞄刀客", 15000);
                    mFairy.onTap(0.8f, result, 1236, 42, 1242, 56, "关闭地图", Sleep);
                    mFairy.onTap(0.8f, result, 649, 401, 655, 406, "npc", Sleep);
                } else {
                    result = mFairy.findPic(14, 188, 91, 283, "chaxun.png");
                    mFairy.onTap(0.8f, result, "查询", Sleep);
                }

                result = mFairy.findPic(1018, 346, 1263, 516, "yqdx.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "一骑当先对话", Sleep);
                    setTaskName(2);
                    return;
                }

            }

            int ppcount = 0;

            public void content_2() throws Exception {
                if (overtime(60, 0)) return;
                Thread.sleep(1000);
                result = mFairy.findPic(1008, 13, 1266, 601, "rightyjdx.png");
                mFairy.onTap(0.8f, result, "右侧一骑当先", Sleep);

                result = mFairy.findPic(847, 82, 1123, 168, "yjdxpipei.png");
                mFairy.onTap(0.8f, result, "一骑当先匹配", 1000);

                result = mFairy.findPic(438, 546, 767, 684, "baoxiang1.png");
                mFairy.onTap(0.9f, result, "宝箱", Sleep);
                result = mFairy.findPic(712, 578, 749, 662, "ykqbx.png");
                if (result.sim > 0.8f) {
                    mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4}, 0, 807, 620, 518, 625, 1000, 1300);
                    result = mFairy.findPic(436, 572, 780, 678, "baoxiang1.png");
                    mFairy.onTap(0.9f, result, "宝箱", Sleep);
                } else {
                    result = mFairy.findPic(420, 573, 841, 691, "bxykq.png");
                    result = mFairy.findPic(result1.x + 15, result1.y - 50, result1.x + 30, result1.y - 22, "5zhan.png");
                    if (result.sim > 0.8f) {
                        LtLog.e("完成5战奖励");
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(835, 518, 1250, 709, "kspp.png");
                if (result.sim > 0.8f) {
                    ppcount++;
                    if (ppcount >= fightCount) {
                        LtLog.e(mFairy.getLineInfo("一骑当先" + (ppcount - 1) + "次了结束"));
                        gameUtil.lkfb();
                        setTaskEnd();
                        return;
                    }
                    mFairy.onTap(0.8f, result, "一骑当先开始匹配", 20000);
                }

                result = mFairy.findPic(544, 78, 730, 129, "xzqh1.png");
                mFairy.onTap(0.8f, result, 929, 98, 935, 110, "强化关闭", Sleep);

                result = mFairy.findPic(853, 160, 951, 252, "yjdxzhandouzhong.png");
                if (result.sim > 0.8f) {
                    if (fightType == 1) {
                        mFairy.onTap(0.8f, result, 1196, 98, 1211, 111, "战斗中打开地图", Sleep);
                    }

                    if (fightType == 2) {
                        setTaskName(3);
                        LtLog.e(mFairy.getLineInfo("直接原地挂机"));
                        return;
                    }
                }

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 429, 315, 438, 321, "随便去个地方挂机", Sleep);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    return;
                }

                result = mFairy.findPic(503, 578, 769, 672, "yjdxlikai.png");
                mFairy.onTap(0.8f, result, "一骑当先离开", 10000);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(1122, 47, 1216, 134, "cha2.png");
                    mFairy.onTap(0.8f, result, "一骑当先离开关闭", Sleep);
                }

                result = mFairy.findPic(1126, 0, 1273, 34, "lc1.png");
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(10, 2)) return;
                /*result = mFairy.findPic("yjdxzhandouzhong.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }*/
                result = mFairy.findPic(1126, 0, 1273, 34, "lc1.png");
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(207, 77, 1138, 580, "yjdxbaohe.png");
                mFairy.onTap(0.8f, result, result.x - 34, result.y + 77, result.x - 33, result.y + 78, "一骑当先宝盒", 5000);

                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    return;
                }

                result = mFairy.findPic("hand.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 100);
                    mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 100);
                    mFairy.onTap(0.8f, result, 1021, 527, 1042, 543, "技能3", 100);
                    mFairy.onTap(0.8f, result, 1016, 636, 1032, 651, "技能4", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                }

                result = mFairy.findPic(503, 578, 769, 672, "yjdxlikai.png");
                mFairy.onTap(0.8f, result, "一骑当先离开", 10000);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
            }
        }.taskContent(mFairy, "一骑当先中");

    }//一骑当先 改进版


    public void tdzz() throws Exception {
        new TimingActivity(mFairy) {


            public void inOperation() throws Exception {
                super.inOperation();

                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (!((h == 12 && m == 59) || (h == 13 && m <= 40) || (h >= 14 && h < 16) || (h == 20 && m == 59) || (h == 21 && m <= 40))) {
                    LtLog.e("任务时间已过 结束任务！");
                    setTaskEnd();
                    return;
                }
            }


            Integer fightType = 1;//打怪方式:1、地图打怪。2、原地打怪
            Integer fightCount = 2;//参与次数

            @Override
            public void create() throws Exception {

                if (!AtFairyConfig.getOption("yjdxType").equals("")) {
                    fightType = Integer.parseInt(AtFairyConfig.getOption("yjdxType"));
                }

                if (!AtFairyConfig.getOption("yjdxCount").equals("")) {
                    fightCount = Integer.parseInt(AtFairyConfig.getOption("yjdxCount"));
                    if (fightCount == 0) fightCount = 2;
                    fightCount++;
                }
            }

            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("tdzz.png", 3);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            int ppcount = 0;

            public void content_2() throws Exception {
                if (overtime(60, 0)) return;
                Thread.sleep(1000);


                result = mFairy.findPic(964, 164, 1046, 252, "tdzz1.png");
                mFairy.onTap(0.8f, result, "匹配", 2000);

                result = mFairy.findPic("tdzz6.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic(835, 518, 1250, 709, "kspp.png");
                if (result.sim > 0.8f) {
                    ppcount++;
                    if (ppcount >= fightCount) {
                        LtLog.e(mFairy.getLineInfo("一骑当先" + (ppcount - 1) + "次了结束"));
                        gameUtil.lkfb();
                        setTaskEnd();
                        return;
                    }

                    mFairy.onTap(0.8f, result, "一骑当先开始匹配", 20000);
                }

                result = mFairy.findPic(544, 78, 730, 129, "xzqh1.png");
                mFairy.onTap(0.8f, result, 929, 98, 935, 110, "强化关闭", Sleep);

                result = mFairy.findPic(853, 160, 951, 252, "yjdxzhandouzhong.png");
                if (result.sim > 0.8f) {
                    if (fightType == 1) {
                        mFairy.onTap(0.8f, result, 1196, 98, 1211, 111, "战斗中打开地图", Sleep);
                    }

                    if (fightType == 2) {
                        setTaskName(3);
                        LtLog.e(mFairy.getLineInfo("直接原地挂机"));
                        return;
                    }
                }

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 429, 315, 438, 321, "随便去个地方挂机", Sleep);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", 3000);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    return;
                }


                result = mFairy.findPic(122, 94, 342, 188, "tdzz5.png");
                mFairy.onTap(0.8f, result, 1239, 23, 1260, 42, "", 5000);


                result = mFairy.findPic(1126, 0, 1273, 34, "lc1.png");
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }

            }


            public void content_3() throws Exception {
                if (overtime(10, 2)) return;

                result = mFairy.findPic(1126, 0, 1273, 34, "lc1.png");
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }


                result = mFairy.findPic(853, 160, 951, 252, "yjdxzhandouzhong.png");
                if (result.sim < 0.8f) {
                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic(207, 77, 1138, 580, "yjdxbaohe.png");
                mFairy.onTap(0.8f, result, result.x - 34, result.y + 77, result.x - 33, result.y + 78, "一骑当先宝盒", 5000);

                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    return;
                }

                result = mFairy.findPic("hand.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 100);
                    mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 100);
                    mFairy.onTap(0.8f, result, 1021, 527, 1042, 543, "技能3", 100);
                    mFairy.onTap(0.8f, result, 1016, 636, 1032, 651, "技能4", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                }

            }

        }.taskContent(mFairy, "天地之战");

    }//天地之战

    public boolean ryjjc_bool = false;

    public void ryjjc() throws Exception {
        new TimingActivity(mFairy) {

            public void create() throws Exception {
                super.create();
                ryjjc_bool = false;
            }

            public void inOperation() throws Exception {
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();
                int w = mFairy.week();

                result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
                mFairy.onTap(0.8f, result, "收起对话栏", Sleep);
                super.inOperation();

                if (m > 33) {

                    LtLog.e(mFairy.getLineInfo("活动超时退出"));
                    setTaskEnd();
                    return;
                }
            }

            public void content_0() throws Exception {
                if (overtime(10, 0)) {
                    gameUtil.close(0);
                    return;
                }
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();
                int w = mFairy.week();
                result = mFairy.findPic(951, 71, 1122, 201, "ryjjcnei.png");
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                } else {

                    int ret = gameUtil.mission("ryjjc.png", 2);
                    if (ret == 1) {
                        gameUtil.retire();
                        setTaskName(2);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
            }

            public void content_2() throws Exception {

                if (overtime(60, 0)) return;
                Thread.sleep(1000);
                result = mFairy.findPic(951, 71, 1122, 201, "ryjjcnei.png");
                mFairy.onTap(0.9f, result, "打开竞技", Sleep);

                result = mFairy.findPic(991, 483, 1195, 671, "ryppz.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                    err = 0;
                }
                result = mFairy.findPic(991, 483, 1195, 671, "jjcdrpp.png");
                if (result.sim > 0.8f) {
                    int m = mFairy.dateMinute();
                    if (m >= 31) {
                        LtLog.e(mFairy.getLineInfo("荣耀竞技场结束"));
                        gameUtil.lkfb();
                        setTaskEnd();
                        return;
                    }


                    Thread.sleep(5000);
                    mFairy.onTap(462, 611, 478, 622, "活跃1", 200);
                    mFairy.onTap(462, 611, 478, 622, "活跃1", 200);
                    mFairy.onTap(619, 609, 638, 621, "活跃2", 200);
                    mFairy.onTap(619, 609, 638, 621, "活跃2", 200);
                    mFairy.onTap(707, 607, 732, 626, "活跃3", 200);
                    mFairy.onTap(707, 607, 732, 626, "活跃3", 200);
                    mFairy.onTap(807, 608, 821, 627, "活跃4", 200);
                    mFairy.onTap(807, 608, 821, 627, "活跃4", 200);
                    mFairy.onTap(1031, 383, 1039, 391, "战力", 200);
                    mFairy.onTap(1031, 383, 1039, 391, "战力", Sleep);


                    if (AtFairyConfig.getOption("ryjjc").equals("2") && ryjjc_bool) {
                        LtLog.e(mFairy.getLineInfo("用户勾选只打一次,荣耀竞技场结束!"));
                        gameUtil.lkfb();
                        setTaskEnd();
                        return;
                    }
                    mFairy.onTap(0.8f, result, "单人匹配", Sleep);

                    result = mFairy.findPic("jjcdrpp.png");
                    mFairy.onTap(0.8f, result, "单人匹配", Sleep);


                }

                result = mFairy.findPic(553, 47, 740, 157, "jjczhandou.png");
                mFairy.onTap(0.8f, result, 1196, 98, 1211, 111, "战斗中打开地图", 7000);

                result = mFairy.findPic(516, 625, 762, 716, "ryjjcfanhui.png");
                mFairy.onTap(0.8f, result, "荣耀竞技场返回", Sleep);
                if (result.sim > 0.8f) {
                    ryjjc_bool = true;
                    int m = mFairy.dateMinute();
                    if (m >= 40) {
                        LtLog.e(mFairy.getLineInfo("荣耀竞技场结束"));
                        gameUtil.lkfb();
                        setTaskEnd();
                        return;
                    }
                }
                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    return;
                }
                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 719, 407, 729, 417, "去地图中间挂机", 3000);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(30, 0)) return;
                Thread.sleep(1000);
                result = mFairy.findPic(553, 47, 740, 157, "jjczhandou.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 200);
                    mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 200);
                    mFairy.onTap(0.8f, result, 1042, 483, 1057, 497, "技能3", 200);
                    mFairy.onTap(0.8f, result, 1008, 568, 1024, 585, "技能4", 200);
                    mFairy.onTap(0.8f, result, 1049, 655, 1064, 671, "技能5", 200);
                    mFairy.onTap(0.8f, result, 959, 656, 972, 663, "技能6", 200);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                }
                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic(516, 625, 762, 716, "ryjjcfanhui.png");
                mFairy.onTap(0.8f, result, "荣耀竞技场返回", Sleep);
                if (result.sim > 0.8f) {
                    ryjjc_bool = true;
                    setTaskName(2);
                    return;
                }
            }

        }.taskContent(mFairy, "荣耀竞技场中");

    }//荣耀竞技场

    public void hlzc() throws Exception {
        new TimingActivity(mFairy) {

            public void create() throws Exception {
                super.create();
                ryjjc_bool = false;
            }

            public void inOperation() throws Exception {
                result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
                mFairy.onTap(0.8f, result, "收起对话栏", Sleep);

                super.inOperation();
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();
                int s = (h * 60 + m) * 60;
                if (!((h == 13 && m <= 35) || (h >= 14 && h <= 16) || (h == 20 && m == 59) || (h == 21 && m <= 35))) {
                    LtLog.e("任务时间已过 结束任务！");
                    setTaskEnd();
                    return;
                }
            }

            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                result = mFairy.findPic(849, 71, 1116, 260, "zhankuang.png");
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic(1124, 2, 1278, 28, "zbq.png");
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
                result2 = mFairy.findPic(808, 206, 959, 443, "bm.png");
                if (result2.sim > 0.8f) {
                    setTaskName(2);
                }
                result2 = mFairy.findPic(386, 232, 891, 509, "sfcj1.png");
                if (result2.sim > 0.8f) {
                    mFairy.onTap(0.8f, result2, 753, 455, 768, 466, "参加确定", Sleep);
                    setTaskName(3);
                }
                int ret = gameUtil.mission("hlzc1.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(60, 0)) return;

                result = mFairy.findPic(488, 57, 754, 173, "ppz.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                    err = 0;
                } else {
                    result2 = mFairy.findPic(928, 165, 1066, 254, "pipei.png");
                    if (result2.sim > 0.8f) {
                        mFairy.onTap(0.9f, result2, "匹配", Sleep);
                    } else {
                        result2 = mFairy.findPic(978, 547, 1197, 660, "kspp1.png");
                        mFairy.onTap(0.8f, result2, "开始匹配", Sleep);
                    }
                }

                result = mFairy.findPic(980, 545, 1187, 661, "qxpp1.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                    mFairy.onTap(1187, 68, 1196, 79, "关闭", Sleep);
                    err = 0;
                }

                result2 = mFairy.findPic(808, 206, 959, 443, "bm.png");
                result = mFairy.findPic(810, 187, 962, 454, "quxiao1.png");
                if (result2.sim > 0.9f) {
                    mFairy.onTap(0.9f, result2, "报名", Sleep);
                } else if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                    mFairy.onTap(1017, 145, 1023, 157, "关闭", Sleep);
                    err = 0;
                    mFairy.sleep(50000);
                }

                result = mFairy.findPic(509, 68, 775, 125, "xzqh.png");
                mFairy.onTap(0.8f, result, 928, 101, 935, 111, "关闭强化", 1000);

                result = mFairy.findPic(489, 330, 777, 553, "swkq.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("未开启"));
                    mFairy.onTap(0.8f, result, 1047, 295, 1053, 304, "未开启", 1000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(369, 231, 904, 506, "sfcj.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 758, 451, 767, 462, "参加", 1000);
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic(849, 71, 1116, 260, "zhankuang.png");
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }

            }

            public void content_3() throws Exception {
                if (overtime(20, 2)) return;
                result = mFairy.findPic(554, 261, 714, 366, "Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    return;
                }

                result2 = mFairy.findPic(1124, 2, 1278, 28, "zbq.png");
                result1 = mFairy.findPic(978, 547, 1197, 660, "kspp1.png");
                result = mFairy.findPic(980, 545, 1187, 661, "qxpp1.png");
                if (result.sim > 0.8f || result1.sim > 0.8f || result2.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic(509, 68, 775, 125, "xzqh.png");
                mFairy.onTap(0.8f, result, 928, 101, 935, 111, "关闭强化", 1000);

                result2 = mFairy.findPic(849, 71, 1116, 260, "zhankuang.png");
                mFairy.onTap(0.8f, result2, 1196, 98, 1211, 111, "战斗中打开地图", 1000);
                if (result2.sim > 0.8f) {
                    mFairy.onTap(0.8f, result2, 631, 333, 633, 339, "中间", 2000);//
                    /*result = mFairy.findPic(343,291,555,367,"dingwei.png");//左侧
                    result1 = mFairy.findPic(749,297,939,367,"dingwei.png");//右侧
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 631,333,633,339, "中间", 500);
                    }else if (result1.sim > 0.8f){
                        mFairy.onTap(0.8f, result, 631,333,633,339, "中间1", 500);
                    }*/
                    mFairy.onTap(0.8f, result2, 1234, 41, 1242, 58, "关闭地图", 2000);
                    setTaskName(4);
                    return;
                }
            }

            public void content_4() throws Exception {
                if (overtime(30, 2)) return;
                long dazeTime = mFairy.mMatTime(118, 59, 67, 21, 0.9f);

                result = mFairy.findPic(849, 71, 1116, 260, "zhankuang.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic(860, 155, 1114, 251, "qiagnhua.png");
                mFairy.onTap(0.8f, result, 1034, 203, 1043, 206, "强化", 1000);

                result = mFairy.findPic(509, 68, 775, 125, "xzqh.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, 484, 290, 491, 300, "强化1", 1000);
                    mFairy.onTap(0.8f, result, 577, 293, 584, 297, "强化2", 1000);
                    mFairy.onTap(0.8f, result, 668, 295, 674, 300, "强化3", 1000);
                    mFairy.onTap(0.8f, result, 757, 292, 766, 300, "强化4", 1000);
                    mFairy.onTap(0.8f, result, 928, 101, 935, 111, "关闭强化", 1000);
                }

                result = mFairy.findPic(509, 68, 775, 125, "xzqh.png");
                mFairy.onTap(0.8f, result, 928, 101, 935, 111, "关闭强化", 1000);


                result = mFairy.findPic(849, 71, 1116, 260, "zhankuang.png");
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    err = 0;
                    mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 200);
                    mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 200);
                    mFairy.onTap(0.8f, result, 1042, 483, 1057, 497, "技能3", 200);
                    mFairy.onTap(0.8f, result, 1008, 568, 1024, 585, "技能4", 200);
                    mFairy.onTap(0.8f, result, 1049, 655, 1064, 671, "技能5", 200);
                    mFairy.onTap(0.8f, result, 959, 656, 972, 663, "技能6", 200);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 200);
                    mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 200);
                    mFairy.onTap(0.8f, result, 1042, 483, 1057, 497, "技能3", 200);
                    mFairy.onTap(0.8f, result, 1008, 568, 1024, 585, "技能4", 200);
                    mFairy.onTap(0.8f, result, 1049, 655, 1064, 671, "技能5", 200);
                    mFairy.onTap(0.8f, result, 959, 656, 972, 663, "技能6", 200);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                }


                result = mFairy.findPic(352, 139, 927, 551, "Resurrection.png");
                if (result.sim > 0.8f || dazeTime > 15) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic(672, 570, 893, 668, "sbjflqjf.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(375, 583, 596, 661, "ptlq.png");
                    mFairy.onTap(0.8f, result, "领取积分", 3000);
                    setTaskName(0);
                    return;
                }


            }

        }.taskContent(mFairy, "荒雷战场中");

    }//荒雷战场

    public void mjxx() throws Exception {
        new TimingActivity(mFairy) {

            public void inOperation() throws Exception {
                result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
                mFairy.onTap(0.8f, result, "收起对话栏", Sleep);

                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (!((h == 13 && m <= 40) || (h >= 14 && h < 16) || (h == 21 && m <= 40))) {
                    LtLog.e("任务时间已过 结束任务！");
                    setTaskEnd();
                    return;
                }
            }

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                result = mFairy.findPic(1121, 1, 1267, 27, "lcdf.png");
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
                result2 = mFairy.findPic(920, 73, 1113, 242, "pipei.png");
                if (result2.sim > 0.8f) {
                    setTaskName(2);
                }
                result2 = mFairy.findPic(985, 552, 1190, 657, "kspp1.png");
                if (result2.sim > 0.8f) {
                    setTaskName(2);
                }
                int ret = gameUtil.mission("mjxx.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(60, 0)) return;

                result = mFairy.findPic(1121, 1, 1267, 27, "lcdf.png");
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic(488, 57, 754, 173, "ppz.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                } else {
                    result2 = mFairy.findPic(928, 165, 1066, 254, "pipei.png");
                    if (result2.sim > 0.8f) {
                        mFairy.onTap(0.9f, result2, "匹配", Sleep);
                    } else {
                        result2 = mFairy.findPic(978, 547, 1197, 660, "kspp1.png");
                        mFairy.onTap(0.8f, result2, "开始匹配", Sleep);
                    }
                }

                result = mFairy.findPic(980, 545, 1187, 661, "qxpp1.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                    //mFairy.onTap(1187,68,1196,79,"关闭", Sleep);
                    err = 0;
                }

                result = mFairy.findPic(163, 140, 441, 641, "jzz.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("加载中"));
                    err = 0;
                }
            }

            public void content_3() throws Exception {
                if (overtime(20, 0)) return;
                long dazeTime = mFairy.mMatTime(1185, 40, 35, 10, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                result = mFairy.findPic(1121, 1, 1267, 27, "lcdf.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("地图中"));
                    err = 0;
                }

                result = mFairy.findPic(565, 509, 697, 560, "sqz.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("拾取中"));
                } else {
                    result = mFairy.findPic(1064, 137, 1088, 160, "cyxw.png");
                    if (result.sim < 0.8f) {
                        result = mFairy.findPic(236, 131, 918, 544, "kzdlh.png");
                        mFairy.onTap(0.8f, result, result.x - 41, result.y + 65, result.x - 32, result.y + 77, "盔仔礼盒", 5000);
                    }

                    result = mFairy.findPic(236, 131, 918, 544, "kzxw.png");
                    mFairy.onTap(0.8f, result, result.x - 13, result.y + 110, result.x - 5, result.y + 116, "盔仔信物", 5000);

                    result = mFairy.findPic(236, 131, 918, 544, "xdb.png");
                    mFairy.onTap(0.8f, result, result.x, result.y + 227, result.x + 9, result.y + 234, "仙丹包", 5000);
                }

                result = mFairy.findPic(428, 11, 825, 113, "xxzj.png");
                mFairy.onTap(0.8f, result, 1187, 67, 1193, 79, "关闭战绩", 3000);



               /* result = mFairy.findPic(262,196,986,585,new String[]{"yzw.png","yzw.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("阵亡退出"));
                    result = mFairy.findPic(1037,193,1105,272,"likai2.png");
                    mFairy.onTap(0.8f, result, "离开", 1000);
                }*/

                result2 = mFairy.findPic(928, 165, 1066, 254, "pipei.png");
                result1 = mFairy.findPic(978, 547, 1197, 660, "kspp1.png");
                result = mFairy.findPic(1130, 1, 1269, 30, "xxmj1.png");
                if (result.sim > 0.8f || result1.sim > 0.8f || result2.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("寻仙秘境休息区"));
                    setTaskName(2);
                    return;
                }

                if (dazeTime > 5) {
                    result = mFairy.findPic(1129, 170, 1198, 250, "bb.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 1199, 99, 1204, 104, "打开地图", 1000);
                        mFairy.onTap(0.8f, result, 601, 325, 711, 411, "地图中心", 1000);
                        mFairy.onTap(0.8f, result, 1237, 47, 1241, 54, "关闭地图", 1000);
                    }
                }
            }
        }.taskContent(mFairy, "秘境寻仙中");

    }//秘境寻仙

    public void cfjj() throws Exception {
        new TimingActivity(mFairy) {

            public void create() throws Exception {
                super.create();
                ryjjc_bool = false;
            }

            public void inOperation() throws Exception {
                result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
                mFairy.onTap(0.8f, result, "收起对话栏", Sleep);

                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (!((h == 12 && m >= 35) || (h == 20 && m > 35))) {
                    LtLog.e("任务时间已过 结束任务！");
                    setTaskEnd();
                    return;
                }
            }

            public void content_0() throws Exception {
                if (overtime(10, 0)) {
                    gameUtil.close(0);
                    return;
                }

                result = mFairy.findPic(1122, 141, 1276, 283, "zhankai.png");
                mFairy.onTap(0.8f, result, "展开", Sleep);

                result = mFairy.findPic(767, 443, 1275, 712, "jz.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "打开家族", Sleep);
                    mFairy.onTap(0.8f, result, 1171, 85, 1178, 95, "关闭家族", Sleep);
                    gameUtil.close(0);
                    setTaskName(1);
                    return;
                }
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("cfjj.png", 2);
                if (ret == 1) {
                    gameUtil.retire();
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(60, 0)) return;
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();
                int s = (h * 60 + m) * 60;
                if (!((h == 19 && m >= 55) || (h == 20 && m < 52))) {
                    LtLog.e("任务时间已过 结束任务！");
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(1121, 4, 1276, 26, "cfjjxxq.png");
                if (result.sim > 0.8f) {
                    LtLog.e("竞技休息区");
                    Thread.sleep(1000);
                }

                result = mFairy.findPic(369, 231, 904, 506, "sfcj.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 758, 451, 767, 462, "参加", 1000);
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic(916, 42, 1115, 200, "jjc2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "打开竞技", 50);
                    for (int i = 0; i < 20; i++) {
                        result = mFairy.findPic(436, 432, 814, 569, new String[]{"swjr.png"});
                        if (result.sim > 0.75f) {
                            LtLog.i(mFairy.getLineInfo("尚未加入战队"));
                            setTaskEnd();
                            return;
                        }
                        LtLog.e("查看是否开启");
                    }
                }

                result2 = mFairy.findPic(939, 537, 1264, 704, "bm.png");
                if (result2.sim > 0.8f) {

                    for (int i = 0; i < 8; i++) {//开箱
                        result = mFairy.findPic(328, 549, 951, 694, new String[]{"bx.png"});
                        mFairy.onTap(0.8f, result, result.x, result.y + 20, result.x + 24, result.y + 31, "宝箱点击", Sleep);
                        LtLog.e("查看是否有宝箱");
                    }
                    mFairy.ranSwipe(850, 615, 650, 615, 100, (long) 1000, 2);
                    for (int i = 0; i < 3; i++) {//开箱
                        result = mFairy.findPic(328, 549, 951, 694, new String[]{"bx.png"});
                        mFairy.onTap(0.8f, result, result.x, result.y + 20, result.x + 24, result.y + 31, "宝箱点击", Sleep);
                        LtLog.e("查看是否有宝箱");
                    }

                    for (int i = 0; i < 3; i++) {//4战奖励
                        result = mFairy.findPic(328, 549, 951, 694, new String[]{"sizhan.png"});
                        if (result.sim > 0.8f) {
                            result1 = mFairy.findPic(result.x - 4, result.y - 56, result.x + 20, result.y - 23, new String[]{"ykq.png"});
                            if (result1.sim > 0.8f) {
                                LtLog.i(mFairy.getLineInfo("4战完成  结束任务"));
                                setTaskEnd();
                                return;
                            }
                        }
                    }

                    mFairy.onTap(0.8f, result2, "报名", Sleep);
                }

                result = mFairy.findPic(810, 187, 962, 454, "quxiao1.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                    mFairy.onTap(1017, 145, 1023, 157, "关闭", Sleep);
                    result = mFairy.findPic(1130, 30, 1237, 156, "cha.png");
                    mFairy.onTap(0.8f, result, "关叉", 50000);
                    err = 0;

                }

                result = mFairy.findPic(844, 28, 1153, 261, "zhankuang.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1196, 98, 1211, 111, "鎴樻枟涓墦寮€鍦板浘", 1000);
                    setTaskName(3);
                    return;
                }


            }

            int num = 0, x = 0;

            public void content_3() throws Exception {

                result = mFairy.findPic(554, 261, 714, 366, "Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    return;
                }
                result = mFairy.findPic(1124, 2, 1278, 28, "hlzcdt.png");
                mFairy.onTap(0.8f, result2, 1196, 98, 1211, 111, "战斗中打开地图", 1000);
                if (result2.sim > 0.8f) {

                    result = mFairy.findPic(77, 259, 244, 422, new String[]{"dfq.png", "dfq1.png"});//左侧
                    result1 = mFairy.findPic(1066, 256, 1208, 410, new String[]{"dfq.png", "dfq1.png"});//右侧
                    if (result.sim > 0.8f) {
                        switch (x++ % 3) {
                            case 0:
                                mFairy.onTap(0.8f, result, 168, 336, 174, 345, "左侧", 500);
                                break;
                            case 1:
                                mFairy.onTap(0.8f, result, 236, 176, 248, 187, "左上", 500);
                                break;
                            case 2:
                                mFairy.onTap(0.8f, result, 223, 535, 240, 550, "左下", 500);
                                break;
                        }
                    } else if (result1.sim > 0.8f) {
                        switch (x++ % 3) {
                            case 0:
                                mFairy.onTap(0.8f, result, 1111, 333, 1126, 345, "右侧", 500);
                                break;
                            case 1:
                                mFairy.onTap(0.8f, result, 1016, 142, 1027, 157, "右上", 500);
                                break;
                            case 2:
                                mFairy.onTap(0.8f, result, 1040, 564, 1051, 577, "右下", 500);
                                break;
                        }
                    }


                    mFairy.onTap(0.8f, result2, 1234, 41, 1242, 58, "关闭地图", 1000);
                    setTaskName(4);
                    return;
                }
                if (result2.sim > 0.8f) {
                    setTaskName(4);
                    return;
                }
            }

            public void content_4() throws Exception {
                if (overtime(20, 2)) return;
                long dazeTime = mFairy.mMatTime(118, 59, 67, 21, 0.9f);
                if (dazeTime > 3) {
                    result = mFairy.findPic(415, 40, 895, 141, "jifen.png");
                    if (result.sim > 0.8f) {
                        mFairy.initMatTime();
                        err = 0;
                        mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 200);
                        mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 200);
                        mFairy.onTap(0.8f, result, 1042, 483, 1057, 497, "技能3", 200);
                        mFairy.onTap(0.8f, result, 1008, 568, 1024, 585, "技能4", 200);
                        mFairy.onTap(0.8f, result, 1049, 655, 1064, 671, "技能5", 200);
                        mFairy.onTap(0.8f, result, 959, 656, 972, 663, "技能6", 200);
                        mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                        mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                        num++;
                    }
                }
                if (num % 2 == 0) {
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f || dazeTime > 15) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    num = 0;
                    x++;
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic(149, 31, 1153, 682, new String[]{"quanbzj.png", "lkzz.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(609, 611, 641, 626, "离开战场", 3000);
                    setTaskName(2);
                    return;
                }


            }

        }.taskContent(mFairy, "冲锋竞技场中");

    }//冲锋竞技场

    public void pwzz() throws Exception {
        new TimingActivity(mFairy) {

            public void inOperation() throws Exception {
                super.inOperation();
                int w = mFairy.week();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                if (h == 19 && m >= 30) {

                } else {
                    setTaskEnd();
                    return;
                }

            }

            public void content_0() throws Exception {
                gameUtil.goCity("家族");
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {

                if (overtime(15, 0)) return;
                result = mFairy.findPic(992, 95, 1275, 574, "pwzzjr.png");
                if (result.sim > 0.8f) {
                    LtLog.e("副本內！！");
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 684, 453, 689, 457, "在家族去中心点打怪", 5000);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);

                result = mFairy.findPic(565, 245, 770, 334, "pw.png");
                result1 = mFairy.findPic(670, 175, 1115, 563, "pw1.png");
                result2 = mFairy.findPic(191, 79, 688, 479, "pw3.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, result.x + 13, result.y + 83, result.x + 4, result.y + 88, "NPC点击", Sleep);
                } else if (result1.sim > 0.8f) {
                    mFairy.onTap(0.8f, result1, result1.x - 161, result1.y + 57, result1.x - 156, result1.y + 72, "NPC点击1", Sleep);
                } else if (result2.sim > 0.8f) {
                    mFairy.onTap(0.8f, result2, result2.x + 346, result2.y + 124, result2.x + 352, result2.y + 138, "NPC点击2", Sleep);
                }


                result = mFairy.findPic(992, 95, 1275, 574, "pwzzjr.png");
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }

            }

            public void content_2() throws Exception {
                if (overtime(30, 0)) return;
                Thread.sleep(1000);
                result = mFairy.findPic(992, 95, 1275, 574, "pwzzjr.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "破雾之战进入", 10);
                    for (int i = 0; i < 20; i++) {
                        result = mFairy.findPic(526, 451, 763, 550, new String[]{"fbwkq.png"});
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("破雾之战未开启 结束任务"));
                            setTaskEnd();
                            return;
                        }
                    }
                    setTaskName(3);
                    return;
                }
            }

            int x = 0;

            public void content_3() throws Exception {

                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    return;
                }

                result = mFairy.findPic("hand.png");
                mFairy.onTap(0.8f, result, "开启自动", Sleep);

                result = mFairy.findPic(792, 55, 1247, 328, new String[]{"lk.png", "zk.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("副本內！！");
                    Thread.sleep(5000);

                } else {
                    result = mFairy.findPic(899, 2, 1272, 270, new String[]{"pwzzdt.png", "pwzzdt.png"});
                    if (result.sim < 0.7f) {
                        LtLog.e(mFairy.getLineInfo("不在破雾之战地图 " + x++ + " 次"));
                        if (x >= 10) {
                            setTaskName(1);
                            return;
                        }
                    }
                }
            }

        }.taskContent(mFairy, "破雾之战");

    }//破雾之战

    public void szlc() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (m >= 30) {
                    LtLog.e(mFairy.getLineInfo("神之猎场结束"));
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("szlc.png", 2);
                if (ret == 1) {
                    gameUtil.retire();
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(8, 0)) return;
                if (gameUtil.duiyuan() == 1) {
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(7, 0)) {
                    return;
                }

                result = mFairy.findPic("dyqxpp.png");
                mFairy.onTap(0.8f, result, "先取消匹配重新定义活动", Sleep);

                result = mFairy.findPic(79, 107, 270, 599, "dshd.png");
                mFairy.onTap(0.95f, result, "找到定时活动", Sleep);

                result = mFairy.findPic(79, 107, 270, 599, "mbszlc.png");
                mFairy.onTap(0.8f, result, "找到神之猎场", Sleep);
                mFairy.onTap(0.8f, result, 1047, 631, 1085, 643, "开启自动匹配", Sleep);
                if (result.sim < 0.8f) {
                    mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5}, 0, 171, 541, 173, 160, 200, 1500, 2);
                } else {
                    setTaskName(4);
                    return;
                }
            }

            public void content_4() throws Exception {
                if (overtime(15, 0)) return;
                if (timekeep(0, 600000, "超过10分钟没组到人")) {
                    LtLog.e(mFairy.getLineInfo("--------超过10分钟没组到人"));
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("dyqxpp.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "匹配中"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("shenqing.png");
                    mFairy.onTap(0.8f, result, 957, 103, 1151, 588, "申请进队", Sleep);
                    err = 0;
                }

                result = mFairy.findPic(855, 248, 1116, 410, "youceyaoqing.png");
                mFairy.onTap(0.8f, result, 1030, 370, 1058, 384, "右侧同意邀请", Sleep);

                result = mFairy.findPic("yryaoqing.png");
                mFairy.onTap(0.8f, result, "有人邀请点开", Sleep);

                result = mFairy.findPic(879, 101, 1092, 581, "jiaru.png");
                mFairy.onTap(0.8f, result, "邀请加入", 100);
                mFairy.onTap(0.8f, result, 438, 613, 477, 625, "邀请列表关闭", Sleep);

                result = mFairy.findPic("beitile.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("qxgs.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进入队伍成功跟随队伍"));
                    gameUtil.close(0);
                    setTaskName(5);
                    return;
                }

                result = mFairy.findPic("gensuiduiwu.png");
                mFairy.onTap(0.8f, result, "进入队伍成功跟随队伍", Sleep);
                if (result.sim > 0.8f) {
                    gameUtil.close(0);
                    setTaskName(5);
                    return;
                }
            }

            public void content_5() throws Exception {

                result = mFairy.findPic("duiwulan.png");
                mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic(0, 0, 30, 27, "hg.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("成了队长了从来"));
                    gameUtil.retire();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("chuanjianduiwu.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(1012, 134, 1121, 229, "copy.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    timekeepInit("超过5分钟没有在副本");
                    LtLog.e(mFairy.getLineInfo("神之猎场内"));
                    mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 200);
                    mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 200);
                    mFairy.onTap(0.8f, result, 1042, 483, 1057, 497, "技能3", 200);
                    mFairy.onTap(0.8f, result, 1008, 568, 1024, 585, "技能4", 200);
                    mFairy.onTap(0.8f, result, 1049, 655, 1064, 671, "技能5", 200);
                    mFairy.onTap(0.8f, result, 959, 656, 972, 663, "技能6", 200);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                }

                if (timekeep(0, 300000, "超过5分钟没有在副本")) {
                    LtLog.e(mFairy.getLineInfo("--------超过5分钟没有在副本"));
                    gameUtil.retire();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("beitile.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    gameUtil.lkfb();
                    setTaskName(0);
                    return;
                }

                gameUtil.fuhuo();

                result = mFairy.findPic("szlcstop.png");
                mFairy.onTap(0.8f, result, "神之猎场结束关闭", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    return;
                }
            }
        }.taskContent(mFairy, "神之猎场中");
    }//神之猎场

    public void jzsl() throws Exception {
        new TimingActivity(mFairy) {
            int first;

            public void create() throws Exception {
                super.create();
                first = 0;
            }

            public void inOperation() throws Exception {
                super.inOperation();
                int w = mFairy.week();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;

                if (AtFairyConfig.getOption("jzjh").equals("1") && h == 19 && (m >= 10 && m < 15) && jzjh == 0) {
                    //    LtLog.e(mFairy.getLineInfo("用户勾选家族酒会"));
                    if (w == 6 || w == 7) {
                        jzwh();
                    } else {
                        jzjh();
                    }
                    jzjh = 1;
                    setTaskEnd();
                    return;
                }

                if (h != 19 || m >= 15) {
                    LtLog.e(mFairy.getLineInfo("家族首领结束"));
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.goCity("家族");
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(30, 0)) return;
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();

                if (h == 19 || m <= 5) {
                    err = 0;
                }

                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 564, 457, 581, 466, "在家族去中心点打怪", 5000);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                if (result.sim > 0.8f) {
                    gameUtil.zidong();
                    setTaskName(2);
                    return;
                }
            }

            int ret1 = 0;

            public void content_2() throws Exception {
                int w = mFairy.week();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                if (h == 19 || m <= 5) {
                    err = 0;
                }

                result = mFairy.findPic("hand.png");
                mFairy.onTap(0.8f, result, "开启自动", Sleep);

                if (AtFairyConfig.getOption("kjxs").equals("1") && w != 6 && w != 7 && (h == 19 && m == 5) && kjxs == 0 && ret1 == 0) {
                    //  LtLog.e(mFairy.getLineInfo("用户勾选科举答题"));
                    ret1 = gameUtil.mission("kjxs.png", 2);
                }

                if (ret1 == 1) {
                    result = mFairy.findPic("kjinface.png");
                    mFairy.onTap(0.8f, result, 598, 393, 639, 411, "科举界面选A", Sleep);

                    result = mFairy.findPic("kjxsstop.png");
                    mFairy.onTap(0.8f, result, 615, 590, 674, 611, "科举乡试结束", Sleep);
                    if (result.sim > 0.8f) {
                        kjxs = 1;
                    }
                }

                if (AtFairyConfig.getOption("fuhuo").equals("1")) {
                    result = mFairy.findPic(491, 133, 784, 539, "fuhuo2.png");
                    mFairy.onTap(0.8f, result, "原地复活", Sleep);
                } else {
                    result = mFairy.findPic(491, 133, 784, 539, "fuhuo1.png");
                    mFairy.onTap(0.8f, result, "默认复活", Sleep);
                }
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }
            }
        }.taskContent(mFairy, "家族首领中");
    }//家族首领

    public void sjsy() throws Exception {
        new SingleTask(mFairy) {
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                overtime(10, 99);
                int w = mFairy.week();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                result = mFairy.findPic("hui1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(800, 550, 1150, 707, "jiazu2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "家族", 2000);

                    } else {
                        mFairy.onTap(1226, 207, 1251, 223, "", 1500);
                    }
                }

                result = mFairy.findPic(537, 41, 645, 90, "jiazu3.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1209, 440, 1219, 480, "活动", 1000);

                    result = mFairy.findPic(203, 128, 345, 645, "sjsy.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(result.x + 805, result.y + 18, result.x + 807, result.y + 20, "助力", 1000);
                        mFairy.onTap(1165, 83, 1181, 94, "", 500);
                        setTaskEnd();
                        return;
                    }
                    return;
                }
            }
        }.taskContent(mFairy, "水晶深渊");
    }//水晶深渊

    public void mphw() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (h >= 21) {
                    LtLog.e(mFairy.getLineInfo("门派会武结束"));
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                if (overtime(10, 0)) {
                    gameUtil.close(0);
                    return;
                }

                result = mFairy.findPic(1122, 141, 1276, 283, "zhankai.png");
                mFairy.onTap(0.8f, result, "展开", Sleep);

                result = mFairy.findPic(767, 443, 1275, 712, "jz.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "打开家族", Sleep);
                    mFairy.onTap(0.8f, result, 1171, 85, 1178, 95, "关闭家族", Sleep);
                    gameUtil.close(0);
                    setTaskName(1);
                    return;
                }
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("mphw.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(30, 0)) return;
                Thread.sleep(1000);
                result = mFairy.findPic(1034, 121, 1106, 190, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    err = 0;
                }
                result = mFairy.findPic("hwzhankuang.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 200);
                    mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 200);
                    mFairy.onTap(0.8f, result, 1042, 483, 1057, 497, "技能3", 200);
                    mFairy.onTap(0.8f, result, 1008, 568, 1024, 585, "技能4", 200);
                    mFairy.onTap(0.8f, result, 1049, 655, 1064, 671, "技能5", 200);
                    mFairy.onTap(0.8f, result, 959, 656, 972, 663, "技能6", 200);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                }

                result = mFairy.findPic(519, 40, 735, 121, "mpzk.png");
                mFairy.onTap(0.8f, result, 1113, 268, 1114, 269, "门派战况点击关闭", Sleep);

                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("复活倒计时中"));
                    Thread.sleep(8000);
                    return;
                }
                result = mFairy.findPic(953, 152, 1037, 196, "dzinfo.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(980, 74, 1064, 120, "bisaizhong.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("门派会武任务结束"));
                        gameUtil.lkfb();
                        setTaskEnd();
                        return;
                    }
                }
            }
        }.taskContent(mFairy, "门派会武中");
    }//门派会武

    public void jzms() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (h >= 21) {
                    LtLog.e(mFairy.getLineInfo("家族魔神结束"));
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("jzmstask.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            int msfb = Integer.parseInt(AtFairyConfig.getOption("msfb"));
            boolean openmap = true;
            int msmap = 1;

            public void content_2() throws Exception {
                if (overtime(20, 0)) {
                    msfb++;
                    if (msfb > 5) {
                        msfb = 1;
                    }
                    return;
                }
                if (msfb == 1) {
                    result = mFairy.findPic(1008, 13, 1266, 601, "ms1.png");
                    mFairy.onTap(0.8f, result, "副本1", 3000);
                }
                if (msfb == 2) {
                    result = mFairy.findPic(1008, 13, 1266, 601, "ms2.png");
                    mFairy.onTap(0.8f, result, "副本2", 3000);
                }
                if (msfb == 3) {
                    result = mFairy.findPic(1008, 13, 1266, 601, "ms3.png");
                    mFairy.onTap(0.8f, result, "副本3", 3000);
                }
                if (msfb == 4) {
                    result = mFairy.findPic(1008, 13, 1266, 601, "ms4.png");
                    mFairy.onTap(0.8f, result, "副本4", 3000);
                }
                if (msfb == 5) {
                    result = mFairy.findPic(1008, 13, 1266, 601, "ms5.png");
                    mFairy.onTap(0.8f, result, "副本5", 3000);
                }
                result = mFairy.findPic(1018, 123, 1266, 524, "msjoin.png");
                mFairy.onTap(0.8f, result, "进入", Sleep);

                result1 = mFairy.findPic(555, 404, 715, 543, "wfwkq.png");
                if (result1.sim > 0.8f) {
                    LtLog.e("未开启");
                    setTaskEnd();
                    return;
                }

                result1 = mFairy.findPic(1027, 116, 1097, 194, "copy.png");
                if (result1.sim > 0.8f && mFairy.dateMinute() >= 5) {
                    result = mFairy.findPic("closehand.png");
                    mFairy.onTap(0.8f, result, "关闭自动", Sleep);
                }
                mFairy.onTap(0.8f, result1, 1196, 98, 1211, 111, "魔神中打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {
                    if (msmap == 1) {
                        mFairy.onTap(0.8f, result, 628, 520, 629, 521, "去魔神第一个点", Sleep);
                    }
                    if (msmap == 2) {
                        mFairy.onTap(0.8f, result, 962, 448, 963, 449, "去魔神第2个点", Sleep);
                    }
                    if (msmap == 3) {
                        mFairy.onTap(0.8f, result, 832, 153, 833, 154, "去魔神第3个点", Sleep);
                    }
                    if (msmap == 4) {
                        mFairy.onTap(0.8f, result, 511, 375, 512, 376, "去魔神第4个点", Sleep);
                    }
                    if (msmap == 5) {
                        mFairy.onTap(0.8f, result, 378, 142, 379, 143, "去魔神第5个点", Sleep);
                        msmap = 3;
                    }
                    msmap++;
                }
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", 10000);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(30, 2)) return;
                Thread.sleep(1000);
                result1 = mFairy.findPic(1027, 116, 1097, 194, "copy.png");
                if (result1.sim > 0.8f) {
                    err = 0;
                }
                gameUtil.fuhuo();
                gameUtil.zidong();
                long dazeTime = mFairy.mMatTime(118, 59, 67, 21, 0.9f);
                if (dazeTime > 12) {
                    mFairy.initMatTime();
                    setTaskName(2);
                    return;
                }
            }
        }.taskContent(mFairy, "家族魔神中");
    }//家族魔神

    public void tsly() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (h == 22 && m > 45) {
                    LtLog.e(mFairy.getLineInfo("天神领域结束"));
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("tsly.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(30, 0)) return;
                Thread.sleep(1000);
                result = mFairy.findPic("tslyjm.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("天神领域界面"));
                    if (AtFairyConfig.getOption("tsly").equals("1")) {
                        mFairy.onTap(0.8f, result, 211, 167, 212, 168, "若空的残识", Sleep);
                    }
                    if (AtFairyConfig.getOption("tsly").equals("2")) {
                        mFairy.onTap(0.8f, result, 235, 258, 236, 259, "水疯魔", Sleep);
                    }
                    if (AtFairyConfig.getOption("tsly").equals("3")) {
                        mFairy.onTap(0.8f, result, 237, 361, 238, 362, "不动圣明王", Sleep);
                    }
                    if (AtFairyConfig.getOption("tsly").equals("4")) {
                        mFairy.onTap(0.8f, result, 229, 458, 230, 459, "沐风", Sleep);
                    }
                    if (AtFairyConfig.getOption("tsly").equals("5")) {
                        mFairy.onTap(0.8f, result, 234, 549, 235, 550, "天火魔龙", Sleep);
                    }
                    if (AtFairyConfig.getOption("tsly").equals("6")) {
                        mFairy.onTap(0.8f, result, 232, 638, 233, 639, "亡国守将", Sleep);
                    }
                    mFairy.onTap(0.8f, result, 1036, 631, 1037, 632, "传送", 10000);
                    result = mFairy.findPic(1053, 278, 1147, 456, "tslyjm.png");
                    mFairy.onTap(0.8f, result, 1192, 92, 1193, 93, "家族不能参赛退出", Sleep);
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return;
                    }
                    setTaskName(3);
                    return;
                }
            }

            boolean openmap = true;

            public void content_3() throws Exception {

                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                if (result.sim > 0.8f && openmap) {
                    mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);
                    openmap = false;
                }

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 449, 199, 450, 200, "随便去个地方挂机", Sleep);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);

                gameUtil.fuhuo();
                gameUtil.zidong();
                long dazeTime = mFairy.mMatTime(118, 59, 67, 21, 0.9f);
                if (dazeTime > 20) {
                    mFairy.initMatTime();
                    openmap = true;
                }
            }
        }.taskContent(mFairy, "天神领域中");
    }//天神领域

    public void zbs() throws Exception {
        new TimingActivity(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("zbs.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(15, 99)) return;
                result = mFairy.findPic(1008, 244, 1273, 536, "youceduizhenbiao.png");
                mFairy.onTap(0.8f, result, "右侧对阵表", Sleep);

                result = mFairy.findPic(492, 55, 801, 119, "duizhanbiaojiemian.png");
                mFairy.onTap(0.8f, result, 273, 270, 274, 271, "对阵表界面", Sleep);


                result = mFairy.findPic(579, 478, 827, 597, "zw.png");
                mFairy.onTap(0.8f, result, "助威", Sleep);

                result = mFairy.findPic(579, 478, 827, 597, "xh.png");
                mFairy.onTap(0.8f, result, "献花", Sleep);


                result = mFairy.findPic(828, 106, 932, 655, "xhh.png");
                mFairy.onTap(0.8f, result, "献花花", Sleep);
                mFairy.onTap(0.8f, result, 993, 98, 994, 99, "关闭献花花界面", 60000);

            }
        }.taskContent(mFairy, "争霸赛助威献花中");
    }//争霸赛助威献花

    public void jzwh() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (m >= 30) {
                    LtLog.e(mFairy.getLineInfo("家族舞会结束"));
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(1022, 372, 1119, 431, "szclose.png");
                mFairy.onTap(0.8f, result, "关闭摇色子", Sleep);
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.goCity("家族");
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;

                result1 = mFairy.findPic("tiaowu.png");
                mFairy.onTap(0.8f, result1, "跳舞", 5000);

                result = mFairy.findPic(1201, 249, 1269, 323, "xm.png");
                result2 = mFairy.findPic(0, 588, 134, 710, "xm.png");
                if (result.sim > 0.8f || result2.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "下马", 3000);
                    mFairy.onTap(0.8f, result2, "下马2", 3000);

                    result1 = mFairy.findPic("tiaowu.png");
                    mFairy.onTap(0.8f, result1, "跳舞", 5000);
                }

                mFairy.onTap(0.8f, result1, "跳舞离开", Sleep);
                if (result1.sim > 0.8f) {
                    if (AtFairyConfig.getOption("jhsyyh").equals("1")) {
                        syyh();
                    }
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (AtFairyConfig.getOption("dati").equals("1")) {
                    result = mFairy.findPic(1020, 332, 1134, 440, "dati.png");
                    mFairy.onTap(0.8f, result, "点击答题", Sleep);

                    result = mFairy.findPic("Use of articles.png");
                    mFairy.onTap(0.8f, result, "使用烟花", Sleep);

                    result = mFairy.findPic("baginface.png");
                    mFairy.onTap(0.8f, result, 1161, 88, 1180, 100, "关闭包裹", Sleep);

                    result = mFairy.findPic(638, 134, 710, 196, "ti.png");
                    mFairy.onTap(0.8f, result, 513, 359, 514, 360, "在答题页面", 10000);

                    result = mFairy.findPic(1022, 372, 1119, 431, "ysz.png");
                    mFairy.onTap(0.8f, result, 1071, 388, 1072, 389, "摇色子", 10000);
                }
            }
        }.taskContent(mFairy, "家族舞会中");
    }//家族舞会

    public void jzjh() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                if (m >= 20) {
                    LtLog.e(mFairy.getLineInfo("家族酒会结束"));
                    gameUtil.close(0);
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("gbdm.png");
                mFairy.onTap(0.8f, result, "关闭弹幕", Sleep);

                result = mFairy.findPic(120, 335, 1165, 683, "szclose.png");
                mFairy.onTap(0.8f, result, "关闭摇色子", Sleep);
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.goCity("家族");
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 569, 458, 578, 463, "在家族去中心点喝酒", 5000);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(15, 0)) return;
                int m = mFairy.dateMinute();
                result = mFairy.findPic(846, 112, 1176, 251, "jzjh.png");
                mFairy.onTap(0.8f, result, 414, 138, 452, 156, "家族酒会", Sleep);

                result = mFairy.findPic(1004, 358, 1270, 541, "hejiu.png");//1021, 434, 1270, 531
                mFairy.onTap(0.8f, result, "点击喝酒", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 414, 138, 452, 156, "家族酒会", Sleep);
                    result = mFairy.findPic(1004, 358, 1270, 541, "hejiu.png");//1021, 434, 1270, 531
                    mFairy.onTap(0.8f, result, "点击喝酒", Sleep);
                    mFairy.onTap(0.8f, result, 414, 138, 452, 156, "家族酒会", Sleep);
                    result = mFairy.findPic(1004, 358, 1270, 541, "hejiu.png");//1021, 434, 1270, 531
                    mFairy.onTap(0.8f, result, "点击喝酒", Sleep);

                    if (AtFairyConfig.getOption("jhsyyh").equals("1")) {
                        syyh();
                    }
                    if (AtFairyConfig.getOption("dati").equals("1")) {
                        setTaskName(3);
                        return;
                    }
                }
            }

            public void content_3() throws Exception {
                if (AtFairyConfig.getOption("dati").equals("1")) {

                    result = mFairy.findPic(1020, 332, 1134, 440, "dati.png");
                    mFairy.onTap(0.8f, result, "点击答题", Sleep);

                    result = mFairy.findPic("Use of articles.png");
                    mFairy.onTap(0.8f, result, "使用烟花", Sleep);

                    result = mFairy.findPic("baginface.png");
                    mFairy.onTap(0.8f, result, 1161, 88, 1180, 100, "关闭包裹", Sleep);

                    result = mFairy.findPic(638, 134, 710, 196, "ti.png");
                    mFairy.onTap(0.8f, result, 513, 359, 514, 360, "在答题页面", 10000);

                    result = mFairy.findPic(1022, 372, 1119, 431, "ysz.png");
                    mFairy.onTap(0.8f, result, 1071, 388, 1072, 389, "摇色子", 10000);
                }
            }
        }.taskContent(mFairy, "家族酒会中");
    }//家族酒会

    public void qh_jzwh() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (m > 46) {
                    LtLog.e(mFairy.getLineInfo("家族舞会结束"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(1022, 372, 1119, 431, "szclose.png");
                mFairy.onTap(0.8f, result, "关闭摇色子", Sleep);
            }

            public void content_0() throws Exception {
                gameUtil.goCity("家族");
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;

                result = mFairy.findPic("tiaowu.png");
                mFairy.onTap(0.8f, result, "跳舞", 5000);
                mFairy.onTap(0.8f, result, "跳舞离开", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
            }


        }.taskContent(mFairy, "家族舞会中");
    }//家族舞会

    public void qh_jzjh() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (m >= 25) {
                    LtLog.e(mFairy.getLineInfo("家族酒会结束"));
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("gbdm.png");
                mFairy.onTap(0.8f, result, "关闭弹幕", Sleep);

                result = mFairy.findPic(120, 335, 1165, 683, "szclose.png");
                mFairy.onTap(0.8f, result, "关闭摇色子", Sleep);
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.goCity("家族");
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 564, 457, 581, 466, "在家族去中心点喝酒", 5000);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(15, 0)) return;

                result = mFairy.findPic(846, 112, 1176, 251, "jzjh.png");
                mFairy.onTap(0.8f, result, 414, 138, 452, 156, "家族酒会", Sleep);

                result = mFairy.findPic(1021, 434, 1270, 531, "hejiu.png");
                mFairy.onTap(0.8f, result, "点击喝酒", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "家族酒会中");
    }//家族酒会

    public void qty() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                if (m >= 30) {
                    LtLog.e(mFairy.getLineInfo("抢汤圆结束"));
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("qty.png", 3);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(15, 0)) return;

                result = mFairy.findPic(858, 73, 1108, 182, "qtydt.png");
                result1 = mFairy.findPic(562, 262, 725, 422, "qtydt1.png");
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic(1034, 20, 1240, 187, "zjf.png");
                mFairy.onTap(0.8f, result, 1194, 82, 1199, 92, "战况关闭", Sleep);

                result = mFairy.findPic(845, 80, 1134, 174, "pp.png");
                mFairy.onTap(0.8f, result, "匹配", Sleep);

                result = mFairy.findPic(962, 567, 1176, 672, "qxpp.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }
                result = mFairy.findPic(451, 7, 837, 97, "qtyz.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(438, 546, 767, 684, "baoxiang1.png");
                    mFairy.onTap(0.9f, result, "宝箱", Sleep);
                    result = mFairy.findPic(712, 578, 749, 662, "ykqbx.png");
                    if (result.sim > 0.8f) {
                        mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4}, 0, 807, 620, 518, 625, 1000, 1300);
                        result = mFairy.findPic(436, 572, 780, 678, "baoxiang1.png");
                        mFairy.onTap(0.9f, result, "宝箱", Sleep);
                    } else {
                        result = mFairy.findPic(420, 573, 841, 691, "bxykq.png");
                        result = mFairy.findPic(result1.x + 15, result1.y - 50, result1.x + 30, result1.y - 22, "5zhan.png");
                        if (result.sim > 0.8f) {
                            LtLog.e("完成5战奖励");
                            setTaskEnd();
                            return;
                        }
                        result = mFairy.findPic(962, 567, 1176, 672, "kspp.png");
                        mFairy.onTap(0.8f, result, "开始匹配", Sleep);
                    }
                }
            }

            public void content_3() throws Exception {
                int x = 0, y = 0, x1 = 0, y1 = 0, s = 0;
                switch (s) {
                    case 0:
                        x = 961;
                        y = 556;
                        x1 = 967;
                        y1 = 560;
                        break;
                    case 1:
                        x = 291;
                        y = 559;
                        x1 = 297;
                        y1 = 565;
                        break;
                    case 2:
                        x = 282;
                        y = 146;
                        x1 = 287;
                        y1 = 151;
                        break;
                    case 3:
                        x = 967;
                        y = 153;
                        x1 = 971;
                        y1 = 157;
                        s = 0;
                        break;
                }

                result = mFairy.findPic(1034, 20, 1240, 187, "zjf.png");
                mFairy.onTap(0.8f, result, 1194, 82, 1199, 92, "战况关闭", Sleep);

                result = mFairy.findPic(1117, 2, 1275, 58, "qtyzb.png");
                result1 = mFairy.findPic(451, 7, 837, 97, "qtyz.png");
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic(348, 180, 808, 376, "jidi.png");
                mFairy.onTap(0.8f, result, result.x, result.y + 75, result.x + 10, result.y + 90, "交汤圆", 3000);

                result = mFairy.findPic(858, 73, 1108, 182, "qtydt.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1197, 100, 1203, 111, "打开地图", Sleep);

                    mFairy.onTap(0.8f, result, x, y, x1, y1, "前往", 2000);
                    s++;
                    result = mFairy.findPic(1196, 13, 1274, 84, "shijie.png");
                    mFairy.onTap(0.8f, result, 1236, 44, 1243, 52, "关闭地图", Sleep);
                }

            }
        }.taskContent(mFairy, "抢汤圆中");
    }//抢汤圆

    public void syyh() throws Exception {
        new TimingActivity(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(6, 99)) return;
                result = mFairy.findPic("bag.png");
                mFairy.onTap(0.8f, result, "包裹", Sleep);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面中"));
                    result = mFairy.findPic(761, 177, 1147, 570, new String[]{"yanhua.png", "yanhua1.png", "yanhua2.png"});
                    mFairy.onTap(0.9f, result, "烟花", Sleep);

                    result = mFairy.findPic(224, 189, 736, 652, "sywp.png");
                    mFairy.onTap(0.8f, result, "物品使用", 10000);
                    if (result.sim > 0.8f) {
                        if (gameUtil.man() == 1) {
                            setTaskEnd();
                            return;
                        }
                    }
                    mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5, 6}, 0, 918, 538, 917, 225, 500, 1500, 2);
                }
            }
        }.taskContent(mFairy, "使用烟花中");
    }//使用烟花

    public void ldz() throws Exception {
        new TimingActivity(mFairy) {

            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                if (m >= 30) {
                    LtLog.e(mFairy.getLineInfo("领地战结束"));
                    gameUtil.lkfb();
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                }
            }

            public void content_0() throws Exception {
                if (overtime(10, 0)) {
                    gameUtil.close(0);
                    return;
                }

                result = mFairy.findPic(1122, 141, 1276, 283, "zhankai.png");
                mFairy.onTap(0.8f, result, "展开", Sleep);

                result = mFairy.findPic(767, 443, 1275, 712, "jz.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "打开家族", Sleep);
                    mFairy.onTap(0.8f, result, 1171, 85, 1178, 95, "关闭家族", Sleep);
                    gameUtil.close(0);
                    setTaskName(1);
                    return;
                }
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("ldz.png", 2);
                if (ret == 1) {
                    gameUtil.retire();
                    setTaskName(2);
                    return;
                } else {
                    result1 = mFairy.findPic(1129, 4, 1263, 25, new String[]{"fz.png", "zhu.png"});
                    if (result1.sim > 0.8f) {
                        GameUtil.lkfb();
                    }
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {//rightldz 圣域 jrfzzd 进入放逐
                if (overtime(10, 1)) return;
                Thread.sleep(1000);

                result = mFairy.findPic(292, 81, 1109, 570, new String[]{"bhldz.png", "bhldz1.png"});
                mFairy.onTap(0.8f, result, result.x + 28, result.y + 124, result.x + 37, result.x + 131, "白虎领地战", Sleep);

                result2 = mFairy.findPic(1008, 13, 1266, 601, "sxzb.png");
                if (result2.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("四雄争霸领地战结束"));
                    gameUtil.lkfb();
                    setTaskEnd();
                    return;
                } else {
                    result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"jinru.png", "jinru1.png"});
                    mFairy.onTap(0.8f, result, "对话框进入领地战", Sleep);
                    if (result.sim > 0.8f) {
                        for (int i = 0; i <= 20; i++) {
                            result1 = mFairy.findPic(293, 405, 1029, 586, "my.png");
                            if (result1.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("领地战无法参加"));
                                LtLog.e(mFairy.getLineInfo("领地战结束"));
                                gameUtil.lkfb();
                                setTaskEnd();
                                return;
                            }
                        }
                    }
                }
                result = mFairy.findPic(486, 93, 997, 399, "fzldnpc.png");
                mFairy.onTap(0.8f, result, result.x + 9, result.y + 130, result.x + 28, result.x + 177, "放逐领地npc", Sleep);

                result1 = mFairy.findPic(1129, 4, 1263, 25, new String[]{"fz.png", "zhu.png"});
                if (result1.sim > 0.8f) {
                    mFairy.onTap(0.8f, result1, 1191, 93, 1210, 116, "打开地图", Sleep);
                    mFairy.onTap(0.8f, result1, 653, 378, 659, 383, "npc", Sleep);
                    mFairy.onTap(0.8f, result1, 1230, 48, 1243, 60, "关闭地图", Sleep);
                    mFairy.onTap(0.8f, result1, 651, 292, 701, 362, "npc点击", Sleep);
                }

                result = mFairy.findPic(907, 206, 1113, 312, new String[]{"copy.png", "copy1.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("领地战副本内"));
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(8, 1)) return;
                result = mFairy.findPic(907, 206, 1113, 312, new String[]{"copy.png", "copy1.png"});
                mFairy.onTap(0.8f, result, 1188, 94, 1208, 112, "领地战打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(644, 364, 655, 372, "去地图中心", Sleep);
                    mFairy.onTap(0.8f, result, 1228, 40, 1248, 55, "关闭地图", Sleep);
                    setTaskName(4);
                    return;
                }
                result = mFairy.findPic("fuhuotime.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("领地战复活"));
                    Thread.sleep(8000);
                }
            }

            public void content_4() throws Exception {
                long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                if (dazeTime > 2) {
                    mFairy.initMatTime();
                    LtLog.e(mFairy.getLineInfo("到达目的地释放技能"));
                    setTaskName(5);
                    return;
                }
            }

            public void content_5() throws Exception {
                if (overtime(10, 3)) return;
                Thread.sleep(2000);
                gameUtil.zidong();
                result = mFairy.findPic("fuhuotime.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("领地战复活"));
                    Thread.sleep(8000);
                    setTaskName(3);
                    return;
                }
                Thread.sleep(2000);
            }
        }.taskContent(mFairy, "领地战中");
    }//领地战
}