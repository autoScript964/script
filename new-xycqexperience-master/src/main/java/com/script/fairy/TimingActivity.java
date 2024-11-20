package com.script.fairy;


import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class TimingActivity extends TaskContent {
    AtFairyImpl mFairy;
    LimitlessTask limitlessTask;
    FindResult result;
    FindResult result1;
    FindResult result2;
    FindResult result3;
    GameUtil gameUtil;
    boolean zchd = true, hejiu = true, ylwq = true, qllj = true, jiuyousan = true, ljp = true, ljp1 = true, xhyx = true, mqzs = true, sjhd = true, sjjz = true, jzwz = true, rxjzwz = true, jdp = true, bwzq = true;
    TaskContent.ControlSplit begin = null;

    public TimingActivity(AtFairyImpl ATFairy) throws Exception {
        mFairy = ATFairy;
        gameUtil = new GameUtil(mFairy);
    }

    public int timingActivity() throws Exception {
        int h = mFairy.dateHour();
        int m = mFairy.dateMinute();
        int w = mFairy.week();
        int s = (h * 60 + m) * 60;
        int sm = s * 1000;
        int back = 0;
        for (int i = 0; i <= 1; i++) {
            LtLog.e("查看限时任务！！！！！" + i);

            if (!AtFairyConfig.getOption("optime6").equals("")) {
                begin = strSplit(AtFairyConfig.getOption("optime6"));
                LtLog.e("============" + begin.timeMillis);
            }
            //周常活动
            if (AtFairyConfig.getOption("zchd").equals("1") && zchd && h == 20 && m >= 1) {//h==20 && m>=1
                gameUtil.goCity("轩辕");
                if (w == 1) {
                    judianzhan();
                }
                if (w == 2 && sm >= begin.timeMillis) {
                    swl();
                }
                if (w == 3) {
                    jitian();
                }
                if (w == 4) {
                    bqlt();
                }
                if (w == 5) {
                    judianzhan();
                }
                zchd = false;
                back = 1;
            }
            //千里良驹 12:00-15 18:00-15
            if (AtFairyConfig.getOption("qllj").equals("1") && qllj && (h == 12 || h == 18) && m <= 13) {
                gameUtil.goCity("轩辕");
                paoma();
                paoma();
                qllj = false;
                back = 1;
            }
            //千里良驹 20:30-45
            if (AtFairyConfig.getOption("qllj").equals("1") && qllj && h == 21 && m >= 30 && m < 43) {
                gameUtil.goCity("轩辕");
                paoma();
                paoma();
                qllj = false;
                back = 1;
            }
       /* //九幽界 12:15-45 18:15-45
        if (AtFairyConfig.getOption("jiuyousan").equals("1")&& jiuyousan  &&  (h==12 || h==18) && m>13 && m<45){
            gameUtil.goCity("轩辕");
            jiuyou();
            jiuyou();
            jiuyousan=false;
            back=1;
        }*/
            //九幽界                   21:45   22:15                 12:15 12:45     18:15  18:45
            if (AtFairyConfig.getOption("jiuyousan").equals("1") && jiuyousan && ((s > 78300 && s < 80100)
                    || (s > 44100 && s < 45900) || (s > 65700 && s < 67500))) {
                gameUtil.goCity("轩辕");
                jiuyou();
                jiuyou();
                jiuyousan = false;
                back = 1;
            }
            //喝酒神兽 19:30 20:30
            if (AtFairyConfig.getOption("hejiu").equals("1") && hejiu && s > 70200 && s < 73800) {
                gameUtil.goCity("轩辕");//图腾回盟
                // hejiu();
                hejiu();
                hejiu = false;
                back = 1;
            }

            //云乐温泉 h>19
            if (AtFairyConfig.getOption("ylwq").equals("1") && ylwq && h >= 19) {
                gameUtil.goCity("轩辕");
                wenquan();
                ylwq = false;
                back = 1;
            }

            //比武招亲竞猜 18:00 -- 19:00
           /* if (AtFairyConfig.getOption("bwzq").equals("1") && bwzq && h == 18) {
                // hejiu();
                bwzq();
                bwzq = false;
                back = 1;
            }
*/
           /* if (AtFairyConfig.getOption("gxrc").equals("1") && h == 5 && m == 1) {
                zchd = true;
                hejiu = true;
                ylwq = true;
                qllj = true;
                jiuyousan = true;
                ljp = true;
                ljp1 = true;
                xhyx = true;
                sjhd = true;
                jzwz = true;
                sjjz = true;
                jdp = true;
                bwzq = true;
                limitlessTask.eliminate = false;
                limitlessTask.sevenStar = false;
                LtLog.e("5点重置任务");
                limitlessTask.create();
                setTaskName(0);
            }*/


            //灵宠夺宝
            if (AtFairyConfig.getOption("lcdb").equals("1") && (w == 6) && (s > 64800 && s < 84600)) {
                gameUtil.goCity("轩辕");
                spoil();
                back = 1;
            }
            //玄海演习场
            if (AtFairyConfig.getOption("xhyx").equals("1") && xhyx && (w == 6 || w == 7) && s > 54000 && s < 84600) {
                gameUtil.goCity("轩辕");
                xuanhai();
                xhyx = false;
                back = 1;
            }

            //赛季活动
            if (AtFairyConfig.getOption("zsld").equals("1") && sjhd && s > 36000 && s < 85800) {
                gameUtil.goCity("轩辕");
                zsld();
                sjhd = false;
                back = 1;
            }

            //秘潜铸身
            if (AtFairyConfig.getOption("9303").equals("1") && mqzs && h >= 10 && h < 18) {
                gameUtil.goCity("轩辕");
                mqzs();
                mqzs = false;
                back = 1;
            }

            //	赛季剑指（）
            if (AtFairyConfig.getOption("8855").equals("1") && sjjz && s > 36000 && s < 85800) {
                gameUtil.goCity("轩辕");
                sjjz();
                sjjz = false;
                back = 1;
            }

            //	剑指王者  (s > 45000 && s < 54000) || (s > 75600 && s < 85800)
            if (AtFairyConfig.getOption("5219").equals("1") && jzwz && s > 36000 && s < 85800) {

                boolean j = false;//判断是否开始剑指王者活动

                if (AtFairyConfig.getOption("jjc").equals("1") || AtFairyConfig.getOption("jjc").equals("2")) {
                    LtLog.e(mFairy.getLineInfo("用户勾选无限打 - 开始活动"));
                    j = true;
                } else {
                    if (AtFairyConfig.getOption("jjc").equals("1") || AtFairyConfig.getOption("DIYsj").equals("1")) {//是否勾选 diy 或者 首胜
                        if (!AtFairyConfig.getOption("start_time").equals("") && !AtFairyConfig.getOption("stop_time").equals("")) {//是否设置了时间
                            ControlSplit start_time = strSplit(AtFairyConfig.getOption("start_time"));//开始时间
                            ControlSplit stop_time = strSplit(AtFairyConfig.getOption("stop_time"));//结束时间

                            if ((h > start_time.h && h < stop_time.h) || (h == start_time.h && m >= start_time.m) || (h == stop_time.h && m < stop_time.m)) {
                                j = true;
                            }
                        } else {
                            LtLog.e(mFairy.getLineInfo("玩家没有设置时间！！！"));
                        }
                    }
                }
                if (j) {
                    LtLog.e(mFairy.getLineInfo("开始活动"));
                    gameUtil.goCity("轩辕");
                    jzwz();
                    jzwz = false;
                    back = 1;
                }
            }
            //决斗牌在线
            if (jdp && AtFairyConfig.getOption("7764").equals("1") && !AtFairyConfig.getOption("jdpzx").equals("3")) {
                gameUtil.goCity("轩辕");
                jdp();
                jdp = false;
            }
            //领金票
            if (AtFairyConfig.getOption("ljp").equals("1") && ljp && s > 45000 && s < 50400) {
                gameUtil.cifu();
                ljp = false;
                back = 1;
            }
            //领金票
            if (AtFairyConfig.getOption("ljp").equals("1") && ljp1 && s > 66600 && s < 72000) {
                gameUtil.cifu();
                ljp1 = false;
                back = 1;
            }

           /* //领金票
            if (AtFairyConfig.getOption("ljp").equals("1") && ljp && s > 45000 && s < 50400) {
                gameUtil.tansuo();
                ljp = false;
                back = 1;
            }*/

        }
        return back;

    }

    public int rxbtimingActivity() throws Exception {
        int h = mFairy.dateHour();
        int m = mFairy.dateMinute();
        int w = mFairy.week();
        int s = (h * 60 + m) * 60;
        int sm = s * 1000;
        int back = 0;
        for (int i = 0; i <= 1; i++) {
            LtLog.e("热血版查看限时任务！！！！！" + i);

            //  剑指王者
            if (AtFairyConfig.getOption("9301").equals("1") && rxjzwz) {
                boolean j = false;//判断是否开始剑指王者活动
                if (AtFairyConfig.getOption("rxwxzx").equals("1")) {
                    LtLog.e(mFairy.getLineInfo("用户勾选无限打 - 开始活动"));
                    j = true;
                } else {
                    if (AtFairyConfig.getOption("rxxszx").equals("1")) {//是否勾选限时
                        if (!AtFairyConfig.getOption("rxkssj").equals("") && !AtFairyConfig.getOption("rxjssj").equals("")) {//是否设置了时间
                            ControlSplit start_time = strSplit(AtFairyConfig.getOption("rxkssj"));//开始时间
                            ControlSplit stop_time = strSplit(AtFairyConfig.getOption("rxjssj"));//结束时间
                            if ((h > start_time.h && h < stop_time.h) || (h == start_time.h && m >= start_time.m) || (h == stop_time.h && m < stop_time.m)) {
                                j = true;
                            }
                        } else {
                            LtLog.e(mFairy.getLineInfo("玩家没有设置时间！！！"));
                        }
                    }
                }
                if (j) {
                    LtLog.e(mFairy.getLineInfo("开始活动"));
                    rxjzwz();
                    rxjzwz = false;
                    back = 1;
                }
            }

        }
        return back;

    }

    public void inOperation() throws Exception {
        result = mFairy.findPic("smOverGraph.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("过图中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic("In transmission.png");
        if (result.sim > 0.7f) {
            LtLog.e(mFairy.getLineInfo("传送中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic("road.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("寻路中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic(188, 533, 369, 592, "complete.png");
        mFairy.onTap(0.8f, result, "完成", Sleep);
        if (result.sim > 0.8f) {
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic(188, 533, 369, 592, "accept.png");
        mFairy.onTap(0.8f, result, "接受", Sleep);
        if (result.sim > 0.8f) {
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
    }

    public void rxjzwz() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;

                if (AtFairyConfig.getOption("rxxszx").equals("1")) {//是否勾选限时
                    if (!AtFairyConfig.getOption("rxjssj").equals("")) {//是否设置了时间
                        ControlSplit stop_time = strSplit(AtFairyConfig.getOption("rxjssj"));//结束时间
                        if ((h > stop_time.h) || (h == stop_time.h && m > stop_time.m)) {
                            LtLog.e(mFairy.getLineInfo("已经达到设置的时间,结束任务!"));
                            setTaskEnd();
                            return;
                        }
                    }
                }
            }

            int ydcount = 0;
            int x = 0, y = 0, x1 = 0, y1 = 0;
            boolean jjtime = true;

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(15, 0))

                    result = mFairy.findPic(1185, 5, 1229, 71, "rxjzwzxdt.png");
                mFairy.onTap(0.8f, result, "剑指王者小地图", 1000);

                result = mFairy.findPic(959, 2, 1193, 135, "rxjzwzxdt1.png");
                mFairy.onTap(0.8f, result, "小地图进入", Sleep);

                result = mFairy.findPic(5, 2, 224, 52, new String[]{"jz.png", "jz1.png", "jz2.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "剑指王者", 500);
                    for (int i = 0; i < 20; i++) {
                        result = mFairy.findPic(474, 105, 790, 190, "rxwkfpp.png");
                        if (result.sim > 0.8f) {
                            LtLog.e("未开放!!!");
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic(543, 71, 731, 143, "rxzdjd.png");
                if (result.sim > 0.8f) {
                    LtLog.e("活动地图中!!!");
                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic(5, 2, 224, 52, "rxjzwz.png");
                if (result.sim > 0.8f) {
                    LtLog.e("剑指地图选择界面中!!!");
                    if (AtFairyConfig.getOption("jzwzdt").equals("1")) {
                        result = mFairy.findPic(354, 307, 607, 450, "rxdxhz.png");
                    }
                    if (AtFairyConfig.getOption("jzwzdt").equals("2")) {
                        result = mFairy.findPic(680, 334, 908, 440, "rxxxdz.png");
                    }
                    if (AtFairyConfig.getOption("jzwzdt").equals("3")) {
                        result = mFairy.findPic(1010, 339, 1223, 447, "rxgpjj.png");
                    }
                    mFairy.onTap(0.8f, result, "地图选择", 1000);
                    if (result.sim > 0.8f) {
                        result1 = mFairy.findPic(552, 611, 1047, 707, new String[]{"rxgrpp.png"});
                        if (result1.sim > 0.8f) {
                            LtLog.e("活动开始!!!");
                            setTaskName(2);
                            return;
                        }
                    }
                }

                result = mFairy.findPic(474, 105, 790, 190, "rxwkfpp.png");
                if (result.sim > 0.8f) {
                    LtLog.e("未开放!!!");
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(30, 0)) return;

                result = mFairy.findPic(552, 611, 1047, 707, new String[]{"rxgrpp.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "个人匹配", Sleep);
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic(709, 85, 783, 196, "rxwkfpp.png");
                        if (result.sim > 0.8f) {
                            LtLog.e("未开放!!!");
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic(481, 67, 671, 188, new String[]{"rxwkfpp.png", "rxzctc.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("战场逃出等待!!!");
                    mFairy.sleep(100000);
                }

                result = mFairy.findPic(420, 390, 840, 492, "rxjrzd.png");
                mFairy.onTap(0.8f, result, "加入战斗", Sleep);

                result = mFairy.findPic(369, 265, 920, 500, "zp.png");
                mFairy.onTap(0.8f, result, 625, 428, 659, 448, "战袍确认", Sleep);

                result = mFairy.findPic(826, 253, 1065, 345, "rxgxhd.png");
                mFairy.onTap(0.8f, result, 940, 508, 947, 514, "确认", Sleep);

                result = mFairy.findPic(349, 253, 913, 518, "yjzdwz.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(657, 392, 913, 503, "Intheteam.png");
                    mFairy.onTap(0.8f, result, "队伍中确定", Sleep);
                }

                result = mFairy.findPic(404, 467, 883, 597, "jzwzppz.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                }
                result = mFairy.findPic(567, 431, 706, 519, "rxfh.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("等待复活"));
                }

                result = mFairy.findPic(395, 3, 896, 141, "rxvs.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(527, 2, 607, 68, "rxwf.png");
                    result2 = mFairy.findPic(1119, 4, 1280, 37, new String[]{"1v1gpjjc.png"});
                    result1 = mFairy.findPic(1174, 8, 1232, 125, new String[]{"xqby.png"});
                    result3 = mFairy.findPic(1119, 4, 1280, 37, "3v3.png");
                    if (result3.sim > 0.8f) {
                        LtLog.e("3v3竞技场");
                        if (result.sim > 0.8f) {
                            LtLog.e("我方在左边");
                            x = 172;
                            y = 448;
                            x1 = 172;
                            y1 = 679;
                        } else {
                            LtLog.e("我方在右边");
                            x = 172;
                            y = 448;
                            x1 = 172;
                            y1 = 679;
                        }
                    }
                    if (result2.sim > 0.8f) {
                        if (result.sim > 0.8f) {
                            LtLog.e("我方在左边");
                            x = 172;
                            y = 430;
                            x1 = 172;
                            y1 = 666;
                        } else {
                            LtLog.e("我方在右边");
                            x = 238;
                            y = 496;
                            x1 = 97;
                            y1 = 652;
                        }
                    }
                    if (result1.sim > 0.8f) {
                        if (result.sim > 0.8f) {
                            LtLog.e("竞技场我方在左边 直走");
                            x = 175;
                            y = 496;
                            x1 = 175;
                            y1 = 651;
                        } else {
                            LtLog.e("竞技场我方在右边 直走");
                            x = 175;
                            y = 496;
                            x1 = 175;
                            y1 = 651;
                        }
                    }
                }

                result = mFairy.findPic(395, 3, 896, 141, "rxvs.png");
                result = mFairy.findPic(1118, 2, 1280, 33, new String[]{"gp.png", "jjc.png",});
                result2 = mFairy.findPic(543, 71, 731, 143, "rxzdjd.png");
                if (result2.sim > 0.8f) {
                    if (jjtime) {
                        Thread.sleep(12000);
                        jjtime = false;
                    }
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("竞技场中"));
                    result3 = mFairy.findPic(304, 101, 418, 198, new String[]{"rxduishou.png", "rxduishou1.png"});
                    if (result3.sim > 0.85f) {
                        LtLog.e(mFairy.getLineInfo("有敌人"));
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 200);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 200);
                        mFairy.onTap(1118, 483, 1138, 497, "技能1", 300);
                        mFairy.onTap(1223, 473, 1242, 489, "技能2", 300);
                        mFairy.onTap(1039, 552, 1057, 569, "技能3", 300);
                        mFairy.onTap(1049, 646, 1065, 663, "技能4", 300);
                        mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 200);
                        mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 200);
                        mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 200);
                        mFairy.onTap(953, 646, 960, 656, "技能5", 300);
                        mFairy.onTap(952, 548, 962, 560, "技能6", 300);
                        mFairy.onTap(1126, 394, 1129, 403, "技能7", 300);
                        mFairy.onTap(1233, 382, 1236, 391, "技能8", 300);
                        mFairy.onTap(1118, 483, 1138, 497, "技能1", 300);
                        mFairy.onTap(1223, 473, 1242, 489, "技能2", 300);
                        mFairy.onTap(1039, 552, 1057, 569, "技能3", 300);
                        mFairy.onTap(1049, 646, 1065, 663, "技能4", 300);
                    } else {
                        result3 = mFairy.findPic(826, 172, 1048, 412, "dsxz.png");
                        if (result3.sim > 0.85f) {
                            //mFairy.onTap(0.8f, result3, "选择下对手", 100);
                            mFairy.onTap(1172, 610, 1191, 622, "普攻下", 200);
                            mFairy.onTap(1172, 610, 1191, 622, "普攻下", 200);
                            mFairy.onTap(1118, 483, 1138, 497, "技能1", 300);
                            mFairy.onTap(1223, 473, 1242, 489, "技能2", 300);
                            mFairy.onTap(1039, 552, 1057, 569, "技能3", 300);
                            mFairy.onTap(1049, 646, 1065, 663, "技能4", 300);
                            mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 200);
                            mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 200);
                            mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 200);
                            mFairy.onTap(953, 646, 960, 656, "技能5", 300);
                            mFairy.onTap(952, 548, 962, 560, "技能6", 300);
                            mFairy.onTap(1126, 394, 1129, 403, "技能7", 300);
                            mFairy.onTap(1233, 382, 1236, 391, "技能8", 300);
                            mFairy.onTap(1118, 483, 1138, 497, "技能1", 300);
                            mFairy.onTap(1223, 473, 1242, 489, "技能2", 300);
                            mFairy.onTap(1039, 552, 1057, 569, "技能3", 300);
                            mFairy.onTap(1049, 646, 1065, 663, "技能4", 300);
                        } else {
                            mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 200);
                            mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 200);
                            mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 200);
                            LtLog.e(mFairy.getLineInfo("没有敌人，移动一下"));
                        }
                        ydcount++;
                        if (ydcount < 15) {
                            mFairy.ranSwipe(174, 572, x, y, 3000, (long) 100, 1);
                        } else if (ydcount >= 15 && ydcount <= 17) {
                            mFairy.ranSwipe(174, 572, x1, y1, 3000, (long) 100, 1);
                        }
                        if (ydcount > 17) {
                            ydcount = 0;
                        }
                    }
                }
                result = mFairy.findPic(996, 464, 1192, 547, "rxcksj.png");
                result1 = mFairy.findPic(967, 580, 1190, 690, "rxtczc.png");
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    jjtime = true;
                    LtLog.e("竞技结束");
                    if (AtFairyConfig.getOption("rxsstz").equals("1")) {
                        result2 = mFairy.findPic(439, 8, 858, 179, new String[]{"rxshengli.png", "rxshengli1.png", "sheng.png"});
                        if (result2.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("胜利一次完成"));
                            result = mFairy.findPic(996, 464, 1192, 547, "rxcksj.png");
                            mFairy.onTap(0.8f, result, "查看数据", Sleep);

                            result = mFairy.findPic(967, 580, 1190, 690, "rxtczc.png");
                            mFairy.onTap(0.8f, result, "退出战场", Sleep);
                            if (result.sim > 0.8f) {
                                setTaskEnd();
                                return;
                            }
                        }
                    } else {
                        result = mFairy.findPic(996, 464, 1192, 547, "rxcksj.png");
                        mFairy.onTap(0.8f, result, "查看数据", Sleep);

                        result1 = mFairy.findPic(967, 580, 1190, 690, "rxtczc.png");
                        mFairy.onTap(0.8f, result, "退出战场", Sleep);
                        if (result.sim > 0.8f) {
                            setTaskName(1);
                            return;
                        }
                    }
                }
            }
        }.taskContent(mFairy, "热血版剑指王者中");
    } //热血版剑指王者

    public void sjjz1() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("hu1.png");
                mFairy.onTap(0.8f, result, 986, 122, 1002, 134, "关闭图腾界面", 1000);
                int s1 = 0;
                int s2 = 0;
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;

                if (!(s > 36000 && s < 85800)) {        //(（当前时间大于10点小于24点）取反
                    LtLog.e(mFairy.getLineInfo("活动时间未到结束"));
                    setTaskEnd();
                    return;
                }

                if (!AtFairyConfig.getOption("mqzs_start_time").equals("") && !AtFairyConfig.getOption("mqzs_stop_time").equals("")) {//是否设置了时间
                    ControlSplit stop_time = strSplit(AtFairyConfig.getOption("mqzs_stop_time"));//结束时间

                    if ((h > stop_time.h) || (h == stop_time.h && m > stop_time.m)) {
                        LtLog.e(mFairy.getLineInfo("已经达到设置的时间,结束任务!"));
                        setTaskEnd();
                        return;
                    }
                } else {
                    LtLog.e(mFairy.getLineInfo("玩家没有设置时间！！！"));
                }

            }

            @Override
            public void create() throws Exception {
               /* if (AtFairyConfig.getOption("rbt").equals("1")) {
                    back = strSplit(AtFairyConfig.getOption("jzsj"));
                }*/
            }

            int ydcount = 0, x11 = 0, x12 = 0, n = 0;
            int x = 286, y = 541, x1 = 42, y1 = 605, a = 0;
            boolean jjtime = true;

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                a = 0;
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(18, 0)) return;

                if (a >= 10) {
                    a = 0;
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png", "bahuang.png", "bh.png"});
                mFairy.onTap(0.8f, result, "天外山海图标", 1500);
                if (result.sim > 0.8f) {
                    err = 0;
                }
                result = mFairy.findPic(298, 475, 1027, 682, new String[]{"bhjc.png"});
                mFairy.onTap(0.8f, result, "八荒进程", 1500);
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic(487, 132, 783, 209, new String[]{"sjwkq.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("赛季未开启!!!");
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(426, 17, 858, 126, "saiji.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(156, 116, 1185, 307, "sjjz.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "赛季剑指点击", Sleep);
                        a = 0;
                        err = 0;
                        n = 0;
                        setTaskName(2);
                        return;
                    } else {
                        a++;
                    }
                }

                result = mFairy.findPic(481, 42, 807, 248, new String[]{"bnbm.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("不能报名!!!");
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(30, 0)) return;


                result = mFairy.findPic(673, 124, 772, 221, "3v3 dont match.png");
                mFairy.onTap(0.8f, result, "战场逃出，无法报名,等待5分钟", 1000);
                //mFairy.onTap(0.8f,result,1241,60,1257,78,"关闭",1000);
                if (result.sim > 0.8f) {
                    mFairy.sleep(300000);
                }
                result = mFairy.findPic(911, 387, 1197, 523, "zhidaole.png");
                mFairy.onTap(0.8f, result, "知道了", Sleep);

                result = mFairy.findPic("zhanpao.png");
                mFairy.onTap(0.8f, result, 641, 437, 650, 443, "战袍模式", Sleep);

                result = mFairy.findPic(485, 515, 517, 542, "02.png");
                if (result.sim > 0.8f) {
                    LtLog.e("奖励次数无 退出");
                    setTaskEnd();
                } else {
                    result = mFairy.findPic(718, 412, 1128, 633, new String[]{"sjjzgrpp.png"});
                    mFairy.onTap(0.8f, result, "个人匹配", Sleep);
                }

                result = mFairy.findPic(368, 262, 910, 495, "sjbm.png");
                mFairy.onTap(0.8f, result, 772, 432, 787, 443, "自己报名", Sleep);


                result = mFairy.findPic(349, 253, 913, 518, "yjzdwz.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(657, 392, 913, 503, "Intheteam.png");
                    mFairy.onTap(0.8f, result, "队伍中确定", Sleep);
                }

                result = mFairy.findPic("nobao.png");
                if (result.sim > 0.8f) {
                    LtLog.e("当前时间段不能报名");
                    setTaskEnd();
                }
                result = mFairy.findPic("nobao.png");
                if (result.sim > 0.8f) {
                    LtLog.e("当前时间段不能报名");
                    setTaskEnd();
                }

                result = mFairy.findPic(817, 536, 1082, 630, "sjjzpipeizhong.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                }
                result = mFairy.findPic(355, 372, 921, 499, "jybattle.png");
                mFairy.onTap(0.8f, result, "加入战斗", Sleep);


                result = mFairy.findPic(1114, 4, 1279, 35, "sjjzdt.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("赛季剑指执行中"));
                    mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                    mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                    mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                    result2 = mFairy.findPic(319, 87, 556, 155, "diren2.png");
                    result3 = mFairy.findPic(319, 87, 556, 155, "diren2.png");
                    result1 = mFairy.findPic(506, 7, 770, 73, "zbjd.png");
                    if (result3.sim > 0.8f || result2.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("战斗阶段有敌人"));
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1118, 483, 1138, 497, "技能1", 500);
                        mFairy.onTap(1223, 473, 1242, 489, "技能2", 500);
                        mFairy.onTap(1039, 552, 1057, 569, "技能3", 500);
                        mFairy.onTap(1049, 646, 1065, 663, "技能4", 500);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(960, 654, 966, 659, "技能5", 500);
                        mFairy.onTap(962, 553, 973, 561, "技能6", 500);
                        mFairy.onTap(1126, 411, 1129, 419, "技能7", 500);
                        mFairy.onTap(1224, 410, 1230, 415, "技能8", 500);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                    } else if (result1.sim > 0.8f) {
                        LtLog.e("准备阶段1");
                    } else {
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        LtLog.e(mFairy.getLineInfo("没有敌人，移动一下"));
                        ydcount++;
                        result = mFairy.findPic(770, 60, 846, 107, "wofang1.png");
                        if (result.sim > 0.8f) {
                            if (ydcount < 15) {
                                mFairy.ranSwipe(174, 572, 291, 536, 1500, (long) 100, 1);
                            } else if (ydcount >= 15 && ydcount <= 18) {
                                mFairy.ranSwipe(174, 572, 60, 607, 1500, (long) 100, 1);
                            }
                        } else {
                            if (ydcount < 15) {
                                mFairy.ranSwipe(174, 572, 81, 640, 1500, (long) 100, 1);
                            } else if (ydcount >= 15 && ydcount <= 18) {
                                mFairy.ranSwipe(174, 572, 278, 495, 1500, (long) 100, 1);
                            }
                        }
                        if (ydcount > 18) {
                            ydcount = 0;
                        }
                    }
                }

                result = mFairy.findPic(913, 429, 1269, 704, "tuichuzhanchang.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 408, 613, 418, 623, "退出战场", Sleep);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(227, 25, 921, 657, new String[]{"tcfb1.png", "jixugz.png", "jiangli.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("竞技结束");
                    mFairy.onTap(0.8f, result, 408, 613, 418, 623, "退出战场", Sleep);


                    result = mFairy.findPic(1171, 135, 1276, 280, "3v3leave1.png");
                    mFairy.onTap(0.8f, result, "退出副本", Sleep);

                    result = mFairy.findPic(913, 429, 1269, 704, "tuichuzhanchang.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 408, 613, 418, 623, "退出战场", Sleep);
                        setTaskName(0);
                        return;
                    }

                    setTaskName(0);
                    return;
                }
            }

        }.taskContent(mFairy, "赛季剑指");
    } //赛季剑指

    public void mqzs() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("hu1.png");
                mFairy.onTap(0.8f, result, 986, 122, 1002, 134, "关闭图腾界面", 1000);

            }

            @Override
            public void create() throws Exception {
            }

            int n = 0;
            int a = 0;
            int tc = 0;

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(18, 0)) return;
                if (a == 10 || a == 20) {
                    gameUtil.close(1);
                }

                if (a >= 30) {
                    setTaskEnd();
                    return;
                }
                result1 = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png", "bahuang.png", "bh.png"});
                mFairy.onTap(0.8f, result1, "天外山海图标", 1500);

                result2 = mFairy.findPic(298, 475, 1027, 682, new String[]{"bhjc.png"});
                mFairy.onTap(0.8f, result2, "八荒进程", 1500);

                result = mFairy.findPic(55, 127, 1197, 340, new String[]{"mqzs.png"});
                if (result.sim > 0.8f || result1.sim > 0.8f || result2.sim > 0.8f) {
                    a = 0;
                } else {
                    a++;
                }
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "秘潜铸身", Sleep);
                    n = 0;
                    setTaskName(2);
                    return;
                }
                result1 = mFairy.findPic(1104, 1, 1280, 34, "sjhddt.png");
                if (result1.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("赛季活动地图中"));
                    a = 0;
                    setTaskName(2);
                    return;
                }

                result1 = mFairy.findPic(587, 149, 744, 212, new String[]{"jiesuan.png", "jiesuan1.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result1, 665, 433, 682, 441, "确定", Sleep);
                    result = mFairy.findPic(913, 429, 1269, 704, "tuichuzhanchang.png");
                    if (result.sim > 0.8f) {
                        mFairy.sleep(5000);
                        mFairy.onTap(0.8f, result, "退出战场", Sleep);
                        tc = 0;
                    }
                }
                result = mFairy.findPic(481, 42, 807, 248, new String[]{"bnbm.png", "jiesuo.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("不能报名!!!");
                    setTaskEnd();
                    return;
                }
            }

            int x = 0, y = 0, x1 = 0, y1 = 0, i = 1;

            public void content_2() throws Exception {
                if (overtime(30, 0)) return;

                result = mFairy.findPic(673, 124, 772, 221, "3v3 dont match.png");
                mFairy.onTap(0.8f, result, "战场逃出，无法报名,等待5分钟", 1000);
                if (result.sim > 0.8f) {
                    mFairy.sleep(300000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(524, 502, 786, 654, new String[]{"new_jymatching.png", "sjhdgrpp.png"});
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic(683, 599, 742, 638, "33.png");
                    if (result1.sim > 0.8f) {
                        LtLog.e("无奖励次数");
                        setTaskEnd();
                    } else {
                        mFairy.onTap(0.8f, result, "个人匹配", Sleep);
                    }
                }

                result = mFairy.findPic("nobao.png");
                if (result.sim > 0.8f) {
                    LtLog.e("当前时间段不能报名");
                    setTaskEnd();
                }
                result = mFairy.findPic(524, 502, 786, 654, "sjhdppz.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                }

                result = mFairy.findPic(662, 381, 894, 500, "jybattle.png");
                mFairy.onTap(0.8f, result, "加入战斗", Sleep);

                result = mFairy.findPic(367, 270, 916, 508, "sjhdqr.png");
                mFairy.onTap(0.8f, result, "确认", Sleep);

                result = mFairy.findPic(543, 211, 928, 324, "wzgw.png");
                mFairy.onTap(0.8f, result, 755, 587, 762, 596, "确认", Sleep);

                result = mFairy.findPic(1104, 1, 1280, 34, "sjhddt.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("秘潜铸身中"));
                    result = mFairy.findPic(477, 0, 796, 77, "sjhdjljs.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("秘潜铸身中1"));

                        mFairy.onTap(1053, 658, 1058, 663, "cs技能1", 500);
                        mFairy.onTap(1055, 558, 1059, 562, "cs技能2", 500);
                        mFairy.onTap(1131, 494, 1136, 501, "cs技能3", 500);
                        mFairy.onTap(1228, 488, 1232, 490, "cs技能4", 500);
                        result = mFairy.findPic(383, 16, 905, 255, "shifang.png");
                        if (result.sim > 0.8f) {
                            switch (i) {
                                case 1:
                                    x = 174;
                                    y = 497;
                                    break;
                                case 2:
                                    x = 175;
                                    y = 642;
                                    break;
                                case 3:
                                    x = 103;
                                    y = 575;
                                    break;
                                case 4:
                                    x = 243;
                                    y = 573;
                                    break;
                            }
                            mFairy.ranSwipe(174, 572, x, y, 3000, (long) 100, 1);
                            if (i >= 4) {
                                i = 0;
                            } else {
                                i++;
                            }
                        } else {
                            mFairy.onTap(1053, 658, 1058, 663, "技能1", 500);
                            mFairy.onTap(1055, 558, 1059, 562, "技能2", 500);
                            mFairy.onTap(1131, 494, 1136, 501, "技能3", 500);
                            mFairy.onTap(1228, 488, 1232, 490, "技能4", 500);
                        }
                    }
                }
                result = mFairy.findPic(432, 143, 908, 497, "zhanbao.png");
                if (result.sim > 0.8f) {
                    LtLog.e("竞技结束");
                    tc = 1;
                    mFairy.onTap(0.8f, result, 670, 432, 675, 443, "确定", 5000);
                    setTaskName(1);
                    return;
                }

            }

        }.taskContent(mFairy, "秘潜铸身中");
    } //秘潜铸身

    public void sjjz() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("hu1.png");
                mFairy.onTap(0.8f, result, 986, 122, 1002, 134, "关闭图腾界面", 1000);
                int s1 = 0;
                int s2 = 0;
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;

                if (!(s > 36000 && s < 85800)) {        //(（当前时间大于10点小于24点）取反
                    LtLog.e(mFairy.getLineInfo("活动时间未到结束"));
                    setTaskEnd();
                    return;
                }
            }


            int ydcount = 0;

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(18, 99)) return;

                result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png", "bahuang.png", "bh.png"});
                mFairy.onTap(0.8f, result, "天外山海图标", 1500);
                if (result.sim > 0.8f) {
                    err = 0;
                }
                result = mFairy.findPic(298, 475, 1027, 682, new String[]{"bhjc1.png"});
                mFairy.onTap(0.8f, result, "八荒进程", 1500);
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic(487, 132, 783, 209, new String[]{"sjwkq.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("赛季未开启!!!");
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(156, 116, 1185, 307, "sjjz.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "赛季剑指点击", Sleep);
                    err = 0;
                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic(481, 42, 807, 248, new String[]{"bnbm.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("不能报名!!!");
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(30, 0)) return;


                result = mFairy.findPic(673, 124, 772, 221, "3v3 dont match.png");
                mFairy.onTap(0.8f, result, "战场逃出，无法报名,等待5分钟", 1000);
                //mFairy.onTap(0.8f,result,1241,60,1257,78,"关闭",1000);
                if (result.sim > 0.8f) {
                    mFairy.sleep(300000);
                }
                result = mFairy.findPic(911, 387, 1197, 523, "zhidaole.png");
                mFairy.onTap(0.8f, result, "知道了", Sleep);

                result = mFairy.findPic("zhanpao.png");
                mFairy.onTap(0.8f, result, 641, 437, 650, 443, "战袍模式", Sleep);

                result = mFairy.findPic(463, 503, 610, 552, "hdjs.png");
                result1 = mFairy.findPic(492, 517, 515, 541, "02.png");
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    LtLog.e("奖励次数无 退出");
                    setTaskEnd();
                } else {
                    result = mFairy.findPic(722, 544, 1123, 636, new String[]{"sjjzgrpp.png"});
                    mFairy.onTap(0.8f, result, "个人匹配", Sleep);
                }

                result = mFairy.findPic(368, 262, 910, 495, "sjbm.png");
                mFairy.onTap(0.8f, result, 772, 432, 787, 443, "自己报名", Sleep);


                result = mFairy.findPic(349, 253, 913, 518, "yjzdwz.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(657, 392, 913, 503, "Intheteam.png");
                    mFairy.onTap(0.8f, result, "队伍中确定", Sleep);
                }

                result = mFairy.findPic(495, 86, 757, 194, "nobao.png");
                if (result.sim > 0.8f) {
                    LtLog.e("当前时间段不能报名");
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(722, 544, 1123, 636, "sjjzpipeizhong.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                }
                result = mFairy.findPic(355, 372, 921, 499, "jybattle.png");
                mFairy.onTap(0.8f, result, "加入战斗", Sleep);


                result = mFairy.findPic(1114, 4, 1279, 35, "sehp.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("赛季剑指执行中"));
                    result2 = mFairy.findPic(296, 68, 575, 172, new String[]{"dhd.png", "eh.png"});
                    result1 = mFairy.findPic(499, 3, 768, 104, "zbjd.png");
                    if (result2.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("战斗阶段有敌人"));
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1118, 483, 1138, 497, "技能1", 500);
                        mFairy.onTap(1223, 473, 1242, 489, "技能2", 500);
                        mFairy.onTap(1039, 552, 1057, 569, "技能3", 500);
                        mFairy.onTap(1049, 646, 1065, 663, "技能4", 500);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(960, 654, 966, 659, "技能5", 500);
                        mFairy.onTap(962, 553, 973, 561, "技能6", 500);
                        mFairy.onTap(1126, 411, 1129, 419, "技能7", 500);
                        mFairy.onTap(1224, 410, 1230, 415, "技能8", 500);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                    } else if (result1.sim > 0.8f) {
                        LtLog.e("准备阶段1");
                    } else {
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        LtLog.e(mFairy.getLineInfo("没有敌人，移动一下"));
                        ydcount++;
                        result = mFairy.findPic(770, 60, 846, 107, "wofang1.png");
                        if (result.sim > 0.8f) {
                            if (ydcount < 15) {
                                mFairy.ranSwipe(174, 572, 75, 639, 1500, (long) 100, 1);
                            } else if (ydcount >= 15 && ydcount <= 18) {
                                mFairy.ranSwipe(174, 572, 277, 507, 1500, (long) 100, 1);
                            }
                        } else {
                            if (ydcount < 15) {
                                mFairy.ranSwipe(174, 572, 302, 575, 1500, (long) 100, 1);
                            } else if (ydcount >= 15 && ydcount <= 18) {
                                mFairy.ranSwipe(174, 572, 43, 578, 1500, (long) 100, 1);
                            }
                        }
                        if (ydcount > 18) {
                            ydcount = 0;
                        }
                    }
                }

                result = mFairy.findPic(984, 457, 1189, 561, "cksj.png");
                mFairy.onTap(0.8f, result, "查看数据", Sleep);

                result = mFairy.findPic(227, 25, 921, 657, "tczc1.png");
                if (result.sim > 0.8f) {
                    LtLog.e("竞技结束");
                    mFairy.onTap(0.8f, result, "退出副本", Sleep);

                    result = mFairy.findPic(1171, 135, 1276, 280, "3v3leave1.png");
                    mFairy.onTap(0.8f, result, "退出副本", Sleep);

                    result = mFairy.findPic(913, 429, 1269, 704, "tuichuzhanchang.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 408, 613, 418, 623, "退出战场", Sleep);
                    }
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(1107, 2, 1277, 31, "xmld1.png");
                if (result.sim > 0.8f) {
                    LtLog.e("领地中");
                    setTaskName(0);
                    return;
                }

            }

        }.taskContent(mFairy, "赛季剑指善恶魂魄");
    } //善恶魂魄

    public void zsld() throws Exception {
        new TimingActivity(mFairy) {

            public void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("hu1.png");
                mFairy.onTap(0.8f, result, 986, 122, 1002, 134, "关闭图腾界面", 1000);
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;

                if (!(s > 36000 && s < 85800)) {        //(（当前时间大于10点小于24点）取反
                    LtLog.e(mFairy.getLineInfo("活动时间未到结束"));
                    setTaskEnd();
                    return;
                }
            }

            int x = 286, y = 541;

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(18, 99)) return;

                result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png", "bahuang.png", "bh.png"});
                mFairy.onTap(0.8f, result, "天外山海图标", 1500);
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic(298, 475, 1027, 682, new String[]{"bhjc1.png"});
                mFairy.onTap(0.8f, result, "八荒进程", 1500);
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic(487, 132, 783, 209, new String[]{"sjwkq.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("赛季未开启!!!");
                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic(156, 116, 1185, 307, "zsld1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "诛神乱斗点击", Sleep);
                    err = 0;
                    setTaskName(2);
                    return;
                }


                result = mFairy.findPic(481, 42, 807, 248, new String[]{"bnbm.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("不能报名!!!");
                    setTaskEnd();
                    return;
                }
            }

            int i = 0;
            int num = 0;
            public void content_2() throws Exception {
                if (overtime(15, 0)) return;

                result = mFairy.findPic(673, 124, 772, 221, "3v3 dont match.png");
                mFairy.onTap(0.8f, result, "战场逃出，无法报名,等待5分钟", 1000);
                if (result.sim > 0.8f) {
                    mFairy.sleep(300000);
                }

                result = mFairy.findPic(911, 387, 1197, 523, "zhidaole.png");
                mFairy.onTap(0.8f, result, "知道了", Sleep);

                result = mFairy.findPic("zsld4.png");
                mFairy.onTap(0.8f, result, "x", Sleep);

                result = mFairy.findPic("zhanpao.png");
                mFairy.onTap(0.8f, result, 641, 437, 650, 443, "战袍模式", Sleep);

                result = mFairy.findPic(851, 586, 1105, 639, "sycs.png");
                if (result.sim > 0.8f) {
                    LtLog.e("有剩余次数");
                    result = mFairy.findPic(995, 582, 1131, 643, "06.png");
                    if (result.sim > 0.8f) {
                        LtLog.e("奖励次数无 退出");
                        setTaskEnd();
                    } else {
                        result = mFairy.findPic(861, 523, 1099, 629, new String[]{"sjjzgrpp.png"});
                        mFairy.onTap(0.8f, result, "个人匹配", Sleep);
                    }

                }


                result = mFairy.findPic(349, 253, 913, 518, "yjzdwz.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(657, 392, 913, 503, "Intheteam.png");
                    mFairy.onTap(0.8f, result, "队伍中确定", Sleep);
                }

                result = mFairy.findPic(495, 86, 757, 194, "nobao.png");
                if (result.sim > 0.8f) {
                    LtLog.e("当前时间段不能报名");
                    setTaskEnd();
                }

                result = mFairy.findPic(850, 520, 1118, 631, "ppz2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                }

                result = mFairy.findPic(355, 372, 921, 499, "jybattle.png");
                mFairy.onTap(0.8f, result, "加入战斗", 10000);

                result = mFairy.findPic(1114, 4, 1279, 35, "zslddt.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    LtLog.e(mFairy.getLineInfo("赛季剑指执行中"));

                    result2 = mFairy.findPic(308, 73, 515, 164, new String[]{"dhd.png", "eh.png"});
                    result1 = mFairy.findPic(506, 7, 770, 73, "zbjd.png");
                    if (result2.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("战斗阶段有敌人"));
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1118, 483, 1138, 497, "技能1", 500);
                        mFairy.onTap(1118, 483, 1138, 497, "技能1", 500);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(1118, 483, 1138, 497, "技能1", 500);
                    } else if (result1.sim > 0.8f) {
                        LtLog.e("准备阶段1");
                    } else {
                        LtLog.e(mFairy.getLineInfo("没有敌人，移动一下"));
                        switch (i) {
                            case 1:
                                x = 174;
                                y = 497;
                                break;
                            case 2:
                                x = 175;
                                y = 642;
                                break;
                            case 3:
                                x = 103;
                                y = 575;
                                break;
                            case 4:
                                x = 243;
                                y = 573;
                                break;
                        }
                        mFairy.ranSwipe(174, 572, x, y, 3000, (long) 100, 1);
                        if (i >= 4) {
                            i = 0;
                        } else {
                            i++;
                        }
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 100);
                    }



                    boolean bool = false;

                  /*  long color = mFairy.getColorNum(1196,243,1275,261,"71,75,62",0.95f);
                    LtLog.i(mFairy.getLineInfo("color:"+color));
                    if(color<20){
                        num++;
                        if(num>3){
                            bool=true;
                        }
                    }else {
                        num=0;
                    }*/

                    result = mFairy.findPic(538, 126, 772, 224,"jljs2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 670, 435, 682, 444, "结算确定", 3000);
                        bool=true;
                    }


                    if(bool){
                        LtLog.e("竞技结束");
                        for (int i1 = 0; i1 < 3; i1++) {
                            Thread.sleep(1000);

                            result = mFairy.findPic(1171, 135, 1276, 280, "3v3leave1.png");
                            mFairy.onTap(0.8f, result, "退出副本", 3000);

                            result = mFairy.findPic(569, 315, 722, 381, "tcfb2.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, 770, 436, 779, 442, "退出战场", 10000);
                                break;
                            }

                        }

                        setTaskName(0);
                        return;
                    }

                }

                result = mFairy.findPic(984, 457, 1189, 561, "cksj.png");
                mFairy.onTap(0.8f, result, "查看数据", Sleep);

                result = mFairy.findPic(1107, 2, 1277, 31, "xmld1.png");
                if (result.sim > 0.8f) {
                    LtLog.e("领地中");
                    setTaskName(0);
                    return;
                }

            }

        }.taskContent(mFairy, "诛神乱斗");
    } //诛神乱斗

    public void jzwz() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("hu1.png");
                mFairy.onTap(0.8f, result, 986, 122, 1002, 134, "关闭图腾界面", 1000);
                int s1 = 0;
                int s2 = 0;
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;

                if (!(s > 36000 && s < 85800)) {        //(（当前时间大于10点小于24点）取反
                    LtLog.e(mFairy.getLineInfo("活动时间未到剑指王者结束"));
                    setTaskEnd();
                    return;
                }/*else if (AtFairyConfig.getOption("DIYsj").equals("1") || AtFairyConfig.getOption("jjc").equals("1")) {
                    if (!AtFairyConfig.getOption("start_time").equals("")){

                        if (strSplit(AtFairyConfig.getOption("start_time")) != null) {
                            start = strSplit(AtFairyConfig.getOption("start_time"));//开始时间
                            s1 = (start.h * 60 + start.m) * 60;

                            stop = strSplit(AtFairyConfig.getOption("stop_time"));//结束时间
                            s2 = (stop.h * 60 + stop.m) * 60;

                            if (!(s >= s1 && s < s2)) {
                                LtLog.e(mFairy.getLineInfo("不在开始和结束时间以内剑指王者结束"));
                                setTaskEnd();
                                return;
                            }
                        }
                }
                }*/

                if (AtFairyConfig.getOption("jjc").equals("1") || AtFairyConfig.getOption("DIYsj").equals("1")) {//是否勾选 diy 或者首胜
                    if (!AtFairyConfig.getOption("start_time").equals("") && !AtFairyConfig.getOption("stop_time").equals("")) {//是否设置了时间
                        ControlSplit stop_time = strSplit(AtFairyConfig.getOption("stop_time"));//结束时间

                        if ((h > stop_time.h) || (h == stop_time.h && m > stop_time.m)) {
                            LtLog.e(mFairy.getLineInfo("已经达到设置的时间,结束任务!"));
                            setTaskEnd();
                            return;
                        }
                    } else {
                        LtLog.e(mFairy.getLineInfo("玩家没有设置时间！！！"));
                    }
                }

                /*if (back != null  && timekeep(0, back.timeMillis, "剑指王者打多久")) {
                    LtLog.e(mFairy.getLineInfo("时间到了剑指王者结束"));
                   *//* result =mFairy.findPic("chakanshuju.png");
                    mFairy.onTap(0.8f,result,1242,115,1259,130,"关闭查看数据",Sleep);

                    result =mFairy.findPic(1003,484,1175,540,"tuichuzhanchang.png");
                    mFairy.onTap(0.8f,result,"退出战场",Sleep);
                    if (result.sim > 0.8f){

                    }*//*
                    setTaskEnd();return;
                }else {
                   *//* result =mFairy.findPic("chakanshuju.png");
                    mFairy.onTap(0.8f,result,1242,115,1259,130,"关闭查看数据",Sleep);

                    result =mFairy.findPic(1003,484,1175,540,"tuichuzhanchang.png");
                    mFairy.onTap(0.8f,result,"退出战场",Sleep);*//*
                }*/

            }

            @Override
            public void create() throws Exception {
               /* if (AtFairyConfig.getOption("rbt").equals("1")) {
                    back = strSplit(AtFairyConfig.getOption("jzsj"));
                }*/
            }

            int ydcount = 0, x11 = 0, x12 = 0, n = 0;
            int x = 167, y = 478, x1 = 173, y1 = 662, a = 0;
            int t = 0;
            boolean jjtime = true, dr = false;

            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(15, 0)) {
                    gameUtil.close(1);
                    setTaskName(3);
                    return;
                }
                if (a >= 10) {
                    a = 0;
                    gameUtil.close(1);
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic(950, 4, 1120, 156, "daily.png");
                mFairy.onTap(0.8f, result, 1077, 44, 1081, 56, "打开日常扩展栏", 1000);

                result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bh.png","jzwz1.png","bh1.png"});
                mFairy.onTap(0.8f, result, "天外山海图标", Sleep);

                result = mFairy.findPic(237, 422, 1014, 650, new String[]{"jz.png", "jz1.png", "jz2.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "剑指王者", 500);
                    for (int i = 0; i < 20; i++) {
                        result = mFairy.findPic(493, 56, 805, 258, new String[]{"wkq.png"});
                        if (result.sim > 0.8f) {
                            LtLog.e("未开放!!!");
                            setTaskEnd();
                            return;
                        }
                    }
                }
                a++;
                result = mFairy.findPic(481, 42, 807, 248, new String[]{"bnbm.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("不能报名!!!");
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(7, 8, 256, 83, "jzwz.png");
                result1 = mFairy.findPic(365, 247, 1118, 607, new String[]{"jzwzgrpp.png", "new_jymatching.png", "grpp.png"});
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    LtLog.e("活动开始!!!");
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(30, 0)) return;


                result = mFairy.findPic(673, 124, 772, 221, "3v3 dont match.png");
                mFairy.onTap(0.8f, result, "战场逃出，无法报名,等待5分钟", 1000);
                //mFairy.onTap(0.8f,result,1241,60,1257,78,"关闭",1000);
                if (result.sim > 0.8f) {
                    mFairy.sleep(300000);
//                    setTaskEnd();
//                    return;
                }


                result = mFairy.findPic("zhanpao.png");
                mFairy.onTap(0.8f, result, 641, 437, 650, 443, "战袍模式", Sleep);

                result = mFairy.findPic(917, 395, 1172, 518, "jrzd.png");
                mFairy.onTap(0.8f, result, "加入战斗", Sleep);

                result = mFairy.findPic(353, 450, 957, 611, new String[]{"jzwzgrpp1.png", "jzwzgrpp.png", "grpp.png"});
                mFairy.onTap(0.8f, result, "个人匹配", Sleep);

                result = mFairy.findPic(369, 265, 920, 500, "zp.png");
                mFairy.onTap(0.8f, result, 625, 428, 659, 448, "战袍确认", Sleep);

                result = mFairy.findPic(405, 596, 863, 708, "sjxz.png");
                mFairy.onTap(0.8f, result, "随机选择", Sleep);

                result = mFairy.findPic(687, 401, 875, 473, "qr2.png");
                mFairy.onTap(0.8f, result, "确认", Sleep);

                result = mFairy.findPic(349, 253, 913, 518, "yjzdwz.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(657, 392, 913, 503, "Intheteam.png");
                    mFairy.onTap(0.8f, result, "队伍中确定", Sleep);
                }
                result = mFairy.findPic("nobao.png");
                mFairy.onTap(0.8f, result, 101, 353, 112, 359, "下一模式", Sleep);
                if (result.sim > 0.8f) {
                    t++;
                    LtLog.e("当前时间段不能报名t++");
                }
                if (t >= 2) {
                    LtLog.e("当前时间段不能报名t>=2");
                    t = 0;
                    setTaskEnd();
                }

                result = mFairy.findPic(404, 467, 883, 597, "jzwzppz.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                }
                result = mFairy.findPic("jybattle.png");
                mFairy.onTap(0.8f, result, "加入战斗", Sleep);
                if (result.sim > 0.8f) {
                    t = 0;
                    jjtime = true;
                }
                result = mFairy.findPic(584, 4, 700, 99, "vs.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(765, 53, 848, 96, "difang.png");
                    result2 = mFairy.findPic(1119, 4, 1280, 37, new String[]{"5v5gpjj.png"});
                    result1 = mFairy.findPic(1119, 4, 1280, 37, new String[]{"1d1jjc.png", "jlpk.png"});
                    result3 = mFairy.findPic(1119, 4, 1280, 37, "3v3.png");
                    if (result3.sim > 0.8f) {
                        LtLog.e("3v3竞技场");
                        if (result.sim > 0.8f) {
                            LtLog.e("我方在左边");
                            x = 172;
                            y = 448;
                            x1 = 172;
                            y1 = 679;
                        } else {
                            LtLog.e("我方在右边");
                            x = 172;
                            y = 448;
                            x1 = 172;
                            y1 = 679;
                        }
                    }
                    if (result2.sim > 0.8f) {
                        LtLog.e("5v5公平竞技");
                        if (result.sim > 0.8f) {
                            LtLog.e("我方在左边");
                            x = 189;
                            y = 504;
                            x1 = 149;
                            y1 = 641;
                        } else {
                            LtLog.e("我方在右边");
                            x = 189;
                            y = 504;
                            x1 = 149;
                            y1 = 641;
                        }
                    }
                    if (result1.sim > 0.8f) {
                        if (result.sim > 0.8f) {
                            LtLog.e("竞技场我方在左边 直走");
                            x = 173;
                            y = 468;
                            x1 = 174;
                            y1 = 663;
                        } else {
                            LtLog.e("竞技场我方在右边");
                            x = 232;
                            y = 532;
                            x1 = 113;
                            y1 = 627;
                        }
                    }
                }

                result1 = mFairy.findPic(509, 0, 803, 128, "vs.png");
                result = mFairy.findPic(1118, 2, 1280, 33, new String[]{"gp.png", "jjc.png",});
                result2 = mFairy.findPic(593, 69, 694, 103, "zdjd.png");
                if (result2.sim > 0.8f) {
                    if (jjtime) {
                        Thread.sleep(12000);
                        jjtime = false;
                    }
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("竞技场中"));
                    result1 = mFairy.findPic(384, 108, 563, 149, "duishou.png");
                    result2 = mFairy.findPic(761, 146, 876, 227, "duishou1.png");
                    if (result1.sim > 0.8f || result2.sim > 0.8f) {
                        dr = true;
                        while (dr) {
                            LtLog.e(mFairy.getLineInfo("有敌人"));
                            mFairy.onTap(1172, 610, 1191, 622, "普攻下", 50);
                            mFairy.onTap(1172, 610, 1191, 622, "普攻下", 50);
                            mFairy.onTap(1118, 483, 1138, 497, "技能1", 50);
                            mFairy.onTap(1223, 473, 1242, 489, "技能2", 50);
                            mFairy.onTap(1039, 552, 1057, 569, "技能3", 50);
                            mFairy.onTap(1049, 646, 1065, 663, "技能4", 50);
                            mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                            mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                            mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                            mFairy.onTap(953, 646, 960, 656, "技能5", 50);
                            mFairy.onTap(952, 548, 962, 560, "技能6", 50);
                            mFairy.onTap(1126, 394, 1129, 403, "技能7", 50);
                            mFairy.onTap(1233, 382, 1236, 391, "技能8", 50);
                            mFairy.onTap(1118, 483, 1138, 497, "技能1", 50);
                            mFairy.onTap(1223, 473, 1242, 489, "技能2", 50);
                            mFairy.onTap(1039, 552, 1057, 569, "技能3", 50);
                            mFairy.onTap(1049, 646, 1065, 663, "技能4", 50);
                            result1 = mFairy.findPic(317, 82, 573, 164, "duishou.png");
                            result2 = mFairy.findPic(761, 146, 876, 227, "duishou1.png");
                            if (result1.sim > 0.8f || result2.sim > 0.8f) {
                                dr = true;
                            } else {
                                dr = false;
                            }
                            result1 = mFairy.findPic(985, 463, 1191, 559, new String[]{"chakanshuju1.png", "chakanshuju.png"});
                            result2 = mFairy.findPic(839, 5, 1277, 125, new String[]{"3v3leave.png", "3v3leave1.png"});
                            if (result1.sim > 0.8f || result2.sim > 0.8f) {
                                dr = false;
                            }
                        }
                        /*LtLog.e(mFairy.getLineInfo("有敌人"));
                        mFairy.onTap(0.8f, result3, "选择对手", Sleep);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 50);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 50);
                        mFairy.onTap(1118, 483, 1138, 497, "技能1", 50);
                        mFairy.onTap(1223, 473, 1242, 489, "技能2", 50);
                        mFairy.onTap(1039, 552, 1057, 569, "技能3", 50);
                        mFairy.onTap(1049, 646, 1065, 663, "技能4", 50);
                        mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(953, 646, 960, 656, "技能5", 50);
                        mFairy.onTap(952, 548, 962, 560, "技能6", 50);
                        mFairy.onTap(1126, 394, 1129, 403, "技能7", 50);
                        mFairy.onTap(1233, 382, 1236, 391, "技能8", 50);
                        mFairy.onTap(1118, 483, 1138, 497, "技能1", 50);
                        mFairy.onTap(1223, 473, 1242, 489, "技能2", 50);
                        mFairy.onTap(1039, 552, 1057, 569, "技能3", 50);
                        mFairy.onTap(1049, 646, 1065, 663, "技能4", 50);*/
                    } else {
                        result3 = mFairy.findPic(1033, 168, 1279, 358, "dsxz.png");
                        mFairy.onTap(0.8f, result3, "选择下对手", 100);
                        mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                        LtLog.e(mFairy.getLineInfo("没有敌人，移动一下"));
                        ydcount++;
                        if (ydcount < 15) {
                            mFairy.ranSwipe(175, 575, x, y, 2500, (long) 100, 1);
                        } else if (ydcount >= 15 && ydcount <= 17) {
                            mFairy.ranSwipe(175, 575, x1, y1, 2500, (long) 100, 1);
                        }
                        mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                        mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);

                        result1 = mFairy.findPic(317, 82, 573, 164, "duishou.png");
                        result2 = mFairy.findPic(761, 146, 876, 227, "duishou1.png");
                        if (result1.sim > 0.8f || result2.sim > 0.8f) {
                            while (dr) {
                                LtLog.e(mFairy.getLineInfo("有敌人"));
                                mFairy.onTap(0.8f, result3, "选择对手", Sleep);
                                mFairy.onTap(1172, 610, 1191, 622, "普攻下", 50);
                                mFairy.onTap(1172, 610, 1191, 622, "普攻下", 50);
                                mFairy.onTap(1118, 483, 1138, 497, "技能1", 50);
                                mFairy.onTap(1223, 473, 1242, 489, "技能2", 50);
                                mFairy.onTap(1039, 552, 1057, 569, "技能3", 50);
                                mFairy.onTap(1049, 646, 1065, 663, "技能4", 50);
                                mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                                mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                                mFairy.onTap(0.8f, result, 1172, 610, 1191, 622, "普攻下", 100);
                                mFairy.onTap(953, 646, 960, 656, "技能5", 50);
                                mFairy.onTap(952, 548, 962, 560, "技能6", 50);
                                mFairy.onTap(1126, 394, 1129, 403, "技能7", 50);
                                mFairy.onTap(1233, 382, 1236, 391, "技能8", 50);
                                mFairy.onTap(1118, 483, 1138, 497, "技能1", 50);
                                mFairy.onTap(1223, 473, 1242, 489, "技能2", 50);
                                mFairy.onTap(1039, 552, 1057, 569, "技能3", 50);
                                mFairy.onTap(1049, 646, 1065, 663, "技能4", 50);
                                result1 = mFairy.findPic(317, 82, 573, 164, "duishou.png");
                                result2 = mFairy.findPic(761, 146, 876, 227, "duishou1.png");
                                if (result1.sim > 0.8f || result2.sim > 0.8f) {
                                    dr = true;
                                } else {
                                    dr = false;
                                }
                                result1 = mFairy.findPic(985, 463, 1191, 559, new String[]{"chakanshuju1.png", "chakanshuju.png"});
                                result2 = mFairy.findPic(839, 5, 1277, 125, new String[]{"3v3leave.png", "3v3leave1.png"});
                                if (result1.sim > 0.8f || result2.sim > 0.8f) {
                                    dr = false;
                                }
                            }
                        }
                        if (ydcount > 17) {
                            ydcount = 0;
                        }
                    }
                }
                result = mFairy.findPic(839, 5, 1277, 125, new String[]{"3v3leave.png", "3v3leave1.png"});
                if (result.sim > 0.8f) {
                    jjtime = true;
                    LtLog.e("竞技结束");
                    if (AtFairyConfig.getOption("jjc").equals("1")) {
                        result2 = mFairy.findPic(459, 5, 865, 186, new String[]{"3v3victory.png", "shengli.png", "sheng.png"});
                        if (result2.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("胜利一次完成"));
                            result = mFairy.findPic(985, 463, 1191, 559, new String[]{"chakanshuju1.png", "chakanshuju.png"});
                            mFairy.onTap(0.8f, result, 1248, 88, 1254, 95, "关闭查看数据", Sleep);

                            result = mFairy.findPic(839, 5, 1277, 125, new String[]{"3v3leave.png", "3v3leave1.png"});
                            mFairy.onTap(0.8f, result, "退出副本", Sleep);

                            result = mFairy.findPic(839, 5, 1277, 125, new String[]{"3v3leave.png", "3v3leave1.png"});
                            mFairy.onTap(0.8f, result, "退出副本", Sleep);

                            result2 = mFairy.findPic(913, 429, 1269, 704, "tuichuzhanchang.png");
                            mFairy.onTap(0.8f, result2, "退出战场", Sleep);
                            if (result.sim > 0.8f || result2.sim > 0.8f) {
                                mFairy.sleep(10000);
                                result = mFairy.findPic(913, 429, 1269, 704, "tuichuzhanchang.png");
                                mFairy.onTap(0.8f, result, "退出战场", Sleep);
                                setTaskEnd();
                                return;
                            }
                        }
                    }


                    result = mFairy.findPic(985, 463, 1191, 559, new String[]{"chakanshuju1.png", "chakanshuju.png"});
                    if (result.sim > 0.8f) {
                        mFairy.sleep(10000);
                        mFairy.onTap(0.8f, result, 1248, 88, 1254, 95, "关闭查看数据", Sleep);
                    }

                    result = mFairy.findPic(839, 5, 1277, 125, new String[]{"3v3leave.png", "3v3leave1.png"});
                    mFairy.onTap(0.8f, result, "退出副本", Sleep);
                    result = mFairy.findPic(839, 5, 1277, 125, new String[]{"3v3leave.png", "3v3leave1.png"});
                    mFairy.onTap(0.8f, result, "退出副本", Sleep);

                    result = mFairy.findPic(913, 429, 1269, 704, "tuichuzhanchang.png");
                    mFairy.onTap(0.8f, result, "退出战场", Sleep);
                    result = mFairy.findPic(913, 429, 1269, 704, "tuichuzhanchang.png");
                    if (result.sim > 0.8f) {
                        mFairy.sleep(10000);
                        mFairy.onTap(0.8f, result, "退出战场", Sleep);
                    }

                }
            }

            public void content_3() throws Exception {
                if (overtime(8, 0)) return;
                if (a >= 5) {
                    a = 0;
                    setTaskEnd();
                    return;
                }

                result1 = mFairy.findPic(697, 402, 1190, 581, new String[]{"new_jymatching.png", "grpp.png"});
                int ret = gameUtil.mission("3v3Arena.png", 2);
                a++;
                if (ret == 1 || result1.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
            }
        }.taskContent(mFairy, "剑指王者中");
    } //剑指王者

    public void jdp() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                result = mFairy.findPic("ling.png");
                if (result.sim > 0.8f) {
                    LtLog.e("匹配中。。。");
                    return;
                }

                result = mFairy.findPic(1, 631, 91, 712, "Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result = mFairy.findPic(236, 538, 1003, 632, "juedoupai.png");
                mFairy.onTap(0.8f, result, "打开决斗牌", Sleep);

                result = mFairy.findPic(1139, 86, 1265, 199, "jingji.png");
                mFairy.onTap(0.8f, result, "竞技", Sleep);

                result = mFairy.findPic(199, 532, 1015, 696, "pipei.png");
                mFairy.onTap(0.8f, result, "匹配", Sleep);

              /*  result1 = mFairy.findPic("mianfei.png");
                if (result1.sim > 0.8f) {
                    result = mFairy.findPic(424, 86, 1057, 554, "box.png");
                    mFairy.onTap(0.8f, result, "挖矿2", Sleep);
                }*/
                result1 = mFairy.findPic("null.png");
                if (result1.sim > 0.8f) {
                    LtLog.e("没有牌结束");
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("isme.png");
                LtLog.e("我方回合相似度" + result.sim);
                if (result.sim > 0.9f) {
                    result = mFairy.findPic("roundzero.png");//412,644,900,676,
                    mFairy.onTap(0.8f, result, "上牌", Sleep);
                    mFairy.onTap(0.8f, result, 1198, 402, 1207, 415, "回合结束", 3000);
                } else {
                    LtLog.e("不是我方回合");
                }

               /* result = mFairy.findPic("nome.png");
                LtLog.e("不是我方回合相似度"+result.sim);
                */

                if (AtFairyConfig.getOption("jdpzx").equals("1")) {
                    result1 = mFairy.findPic("win.png");
                    if (result1.sim > 0.8f) {
                        LtLog.e("赢了结束了");
                        gameUtil.close(1);
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("jixu.png");
                mFairy.onTap(0.8f, result, "继续结束了", Sleep);

                result = mFairy.findPic(437, 529, 556, 628, "suo.png");
                if (result.sim > 0.8f) {
                    LtLog.e("还没开启");
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy, "决斗牌");
    } //决斗牌匹配

    public void paoma() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();


                result = mFairy.findPic("pm.png");
                mFairy.onTap(0.8f,result,"跑马奖励",1500);

                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (((h == 12 || h == 18) && m >= 20) || (h == 20 && m >= 50)) {
                    LtLog.e(mFairy.getLineInfo("九幽结束"));
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
                if (mFairy.dateMinute() > 20 && mFairy.dateMinute() < 30) {
                    LtLog.e(mFairy.getLineInfo("跑马超时结束"));
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(533, 301, 775, 406, "csqr.png");
                mFairy.onTap(0.8f, result, 771, 433, 782, 444, "传送确认", Sleep);

                int ret = gameUtil.mission("HorseRacing1.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 40) {

                    result = mFairy.findPic(45, 33, 187, 317, "cfd.png");
                    result1 = mFairy.findPic(1099, 2, 1280, 39, "cfd1.png");
                    if (result.sim > 0.8f || result1.sim > 0.8f) {
                        mFairy.initMatTime();
                    } else {
                        mFairy.initMatTime();
                        setTaskName(1);
                        return;
                    }
                }

                result = mFairy.findPic(18, 116, 532, 607, "TakehorseRace.png");
                mFairy.onTap(0.8f, result, "参加跑马", Sleep);

                result = mFairy.findPic("hcSure.png");
                mFairy.onTap(0.8f, result, "回城确定", Sleep);


                result = mFairy.findPic(533, 301, 775, 406, "csqr.png");
                mFairy.onTap(0.8f, result, 771, 433, 782, 444, "传送确认", Sleep);


                result = mFairy.findPic("pmxsyd.png");
                mFairy.onTap(0.8f, result, "加入战斗", Sleep);

                result = mFairy.findPic("Replica.png");
                result1 = mFairy.findPic(1099, 2, 1280, 39, "cfd1.png");
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    mFairy.onTap(1200, 76, 1201, 77, "打开地图", Sleep);
                }

/*
                result =mFairy.findPic(1120,5,1276,150,"dtzb.png");
                mFairy.onTap(0.8f,result,1200, 76,1201, 77,"蓝点打开地图",Sleep);*/

                result = mFairy.findPic("hcWorld.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }
                result = mFairy.findPic(546, 1, 742, 49, new String[]{"startGame.png", "startGame1.png", "bsks.png", "bsks1.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("比赛开始"));
                    result = mFairy.findPic("Replica.png");
                    result1 = mFairy.findPic(1099, 2, 1280, 39, "cfd1.png");
                    if (result.sim > 0.8f || result1.sim > 0.8f) {
                        mFairy.onTap(1200, 76, 1201, 77, "打开地图", Sleep);
                    }
                    for (int i = 0; i < 2; i++) {
                        List<Integer> list = new ArrayList<>();
                        list.add(442);
                        list.add(522);

                        list.add(535);
                        list.add(521);

                        list.add(606);
                        list.add(421);

                        list.add(499);//474,313,518,350
                        list.add(333);

                        list.add(526);//501,264, 545, 301
                        list.add(284);

                        list.add(363);
                        list.add(208);//338,188, 382,225

                        list.add(616);//591,191, 635, 228
                        list.add(211);

                        /*list.add(349);//324,128,368,165
                        list.add(148);*/

                        list.add(315);
                        list.add(208);//290,188,334,225           新改动

                        list.add(270);//244,484,290,529
                        list.add(505);//245,490,289,527

                        list.add(389);
                        list.add(520);

                        while (list.size() != 0) {
                            mFairy.tap(list.get(0), list.get(1));
                            LtLog.e("本次使用坐标点为————： x==" + list.get(0) + ",y==" + list.get(1));
                            for (int j = 0; j < 100; j++) {
                                mFairy.condit();
                                result = mFairy.findPic(list.get(0) - 26, list.get(1) - 23, list.get(0) + 20, list.get(1) + 22, new String[]{"weizhi.png", "weizhi1.png", "weizhi2.png", "blue.png", "blue1.png"});
                                LtLog.e(mFairy.getLineInfo(0.1f, result, "蓝色坐标相似度") + result.sim);
                                result1 = mFairy.findPic(list.get(0) - 26, list.get(1) - 23, list.get(0) + 20, list.get(1) + 22, "weizhi2.png");
                                LtLog.e(mFairy.getLineInfo(0.1f, result1, "蓝色坐标备用点相似度") + result1.sim);
                                if (result.sim >= 0.7f || result1.sim >= 0.7f) {
                                    LtLog.e(("点击坐标"));
                                    list.remove(0);
                                    list.remove(0);
                                    break;
                                }
                            }
                            result = mFairy.findPic("xiulian.png");
                            if (result.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("挂机修炼了移动一下"));
                                mFairy.ranSwipe(173, 569, 216, 570, 100, (long) 100, 2);
                                return;
                            }
                        }


                        result = mFairy.findPic("pm.png");
                        mFairy.onTap(0.8f,result,"跑马奖励",1500);

                    }
                    Thread.sleep(20000);
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "跑马中");

    } //跑马

    public void jiuyou() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (!((h == 12 || h == 18) && m > 15 && m < 45) && !(s > 74700 && s < 76500)) {
                    LtLog.e(mFairy.getLineInfo("九幽结束"));
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
                int ret = gameUtil.mission("jiuyou1.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(20, 1)) return;

                result = mFairy.findPic(68, 11, 218, 77, "juese.png");
                mFairy.onTap(0.8f, result, 1196, 42, 1204, 52, "角色关闭", Sleep);

                result = mFairy.findPic("jymatching.png");
                mFairy.onTap(0.8f, result, "个人匹配", Sleep);

                result = mFairy.findPic("jybattle.png");
                mFairy.onTap(0.8f, result, "加入战斗", Sleep);

                result = mFairy.findPic(1084, 0, 1277, 39, "xmld.png");
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }

                result1 = mFairy.findPic(456, 118, 860, 213, "taochu.png");
                result = mFairy.findPic(418, 300, 885, 496, "jiuyoufinish.png");
                mFairy.onTap(0.8f, result, 619, 432, 655, 442, "九幽无法匹配了", Sleep);
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    result = mFairy.findPic(418, 300, 885, 496, "jiuyoufinish.png");
                    mFairy.onTap(0.8f, result, 619, 432, 655, 442, "九幽无法匹配了", Sleep);
                    LtLog.e(mFairy.getLineInfo("九幽无法匹配了"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("Intheteam.png");
                mFairy.onTap(0.8f, result, "队伍中确定", Sleep);

                result = mFairy.findPic(6, 245, 1194, 484, "death.png");
                mFairy.onTap(0.8f, result, "复活", Sleep);

                result = mFairy.findPic("gjSafetyZone.png");
                if (picCount(0.65f, result, "九幽安全区") > 6) {
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("Replica.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});
                    if (result.sim < 0.8f) {
                        mFairy.onTap(1133, 329, 1141, 337, "开启自动战斗", Sleep);
                    }
                }
            }
        }.taskContent(mFairy, "九幽中");
    }//九幽

    public void wenquan() throws Exception {
        new TimingActivity(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("hotSpring.png", 2);
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
                result = mFairy.findPic(18, 116, 532, 607, "wqValley.png");
                mFairy.onTap(0.8f, result, "左侧温泉", Sleep);

                result = mFairy.findPic(482, 135, 620, 184, new String[]{"jrwq.png", "jrwq1.png"});
                mFairy.onTap(0.7f, result, 875, 416, 942, 438, "进入温泉选择", Sleep);
                mFairy.onTap(0.7f, result, 899, 563, 930, 577, "进入温泉", Sleep);

                result = mFairy.findPic(366, 123, 748, 191, "wq1.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(374, 166, 841, 538, new String[]{"liuchang.png", "fanmang.png"});
                    mFairy.onTap(0.9f, result, "选择分线", 1000);
                    mFairy.onTap(0.9f, result, 740, 584, 750, 596, "进入温泉", 3000);
                }


                result = mFairy.findPic(1090, 2, 1278, 37, "xhmap.png");
                mFairy.onTap(0.8f, result, 1199, 87, 1207, 93, "打开地图", 2000);

                result = mFairy.findPic(741, 120, 1177, 312, "wq2.png");
                mFairy.onTap(0.8f, result, "温泉入口", 2000);

                result = mFairy.findPic(50, 371, 498, 583, "EnterHotSpring.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "进入温泉", 2000);
                    result = mFairy.findPic(688, 402, 879, 480, "hcSure.png");
                    mFairy.onTap(0.8f, result, 764, 433, 777, 438, "回城确定", Sleep);
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic(688, 402, 879, 480, "hcSure.png");
                mFairy.onTap(0.8f, result, 764, 433, 777, 438, "回城确定", Sleep);
                mFairy.onTap(0.8f, result, 1235, 41, 1254, 52, "关闭地图", Sleep);

                result = mFairy.findPic(487, 2, 785, 35, new String[]{"sysj.png", "sysj1.png"});
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
            }


            public void content_3() throws Exception {
                result = mFairy.findPic(1203, 14, 1277, 478, "1203,14,1277,478.png");
                mFairy.onTap(0.8f, result, 1243, 45, 1248, 52, "关闭地图", Sleep);


                result = mFairy.findPic(368, 408, 911, 610, "HoTspringTickets3.png");
                result1 = mFairy.findPic(368, 408, 911, 610, "HoTspringTickets.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "白色", Sleep);
                } else if (result1.sim > 0.8f) {
                    mFairy.onTap(0.8f, result1, "橙色", Sleep);
                }

/*                result = mFairy.findPic(368,408,911,610, "HoTspringTickets.png");
                if (result.sim>0.8f){
                    List<FindResult> listResult = mFairy.findPic(368,408,911,610, 0.8f,"HoTspringTickets.png");
                    if (listResult.size() != 0) {
                        mFairy.onTap(0.8f, listResult.get(listResult.size()-1), listResult.get(listResult.size()-1).x, listResult.get(listResult.size()-1).y, listResult.get(listResult.size()-1).x + 1, listResult.get(listResult.size()-1).y + 1, "温泉道具", Sleep);
                    }
                }
                result =   mFairy.findPic(368,408,911,610, "HoTspringTickets3.png");
                if (result.sim>0.8f){
                    List<FindResult> listResult = mFairy.findPic(368,408,911,610, 0.8f, "HoTspringTickets3.png");
                    if (listResult.size() != 0) {
                        mFairy.onTap(0.8f, listResult.get(listResult.size()-1), listResult.get(listResult.size()-1).x, listResult.get(listResult.size()-1).y, listResult.get(listResult.size()-1).x + 1, listResult.get(listResult.size()-1).y + 1, "温泉道具", Sleep);
                    }
                }*/

                result = mFairy.findPic(25, 157, 272, 292, "wenquanStop.png");
                if (picCountS(0.8f, result, "温泉stop") > 30) {
                    LtLog.e(mFairy.getLineInfo("温泉结束"));
                    gameUtil.goCity("轩辕");
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "温泉中");
    } //温泉

    public void judianzhan() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                if (m >= 20) {//20
                    LtLog.e(mFairy.getLineInfo("据点战结束"));
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(796, 8, 1115, 157, new String[]{"daily.png", "fuli.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png", "bahuang.png", "bh.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("有天外山海图标去天外据点战"));
                        setTaskName(3);
                    } else {
                        LtLog.e(mFairy.getLineInfo("没有天外山海图标去本服据点战"));
                        setTaskName(3);
                    }
                }
            }

            public void content_2() throws Exception {
                int ret = gameUtil.tymission("tyjdz.png");
                if (ret == 1) {
                    setTaskName(4);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_3() throws Exception {
                int ret = gameUtil.mission("tyjdz.png", 3);
                if (ret == 1) {
                    setTaskName(4);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_4() throws Exception {
                result = mFairy.findPic(945, 578, 1165, 671, "jrjdz.png");
                mFairy.onTap(0.8f, result, "进入据点战", Sleep);

                result = mFairy.findPic(684, 395, 865, 471, "jdzqr.png");
                mFairy.onTap(0.8f, result, "进入据点战确定", 10000);

                result = mFairy.findPic(757, 224, 992, 419, "zys.png");
                mFairy.onTap(0.7f, result, "采集资源", 5000);

                result = mFairy.findPic(1182, 160, 1275, 250, "Replica.png");
                if (picCountS(0.8f, result, "不在据点战内") > 30) {
                    LtLog.e(mFairy.getLineInfo("据点战结束"));
                    setTaskEnd();
                    return;
                }
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});

                    if (result1.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("战斗中"));
                       /* mFairy.onTap(0.8f,result,"据点进攻中",Sleep);
                        setTaskName(5);return;
                        result1= mFairy.findPic("nodiren.png");*/
                        result1 = mFairy.findPic(34, 174, 262, 314, "Hangup.png");
                        if (result1.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "据点进攻中", Sleep);
                            setTaskName(5);
                            return;
                        }
                    } else {
                        mFairy.onTap(0.8f, result, 97, 224, 113, 236, "据点进攻中", Sleep);
                        setTaskName(5);
                        return;
                    }
                }
                result = mFairy.findPic(684, 395, 865, 471, "jdzqr.png");
                mFairy.onTap(0.8f, result, "进入据点战确定", 10000);

                result = mFairy.findPic(517, 318, 770, 545, "jdSure.png");
                mFairy.onTap(0.8f, result, "据点战奖励确定", Sleep);
            }

            public void content_5() throws Exception {
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 5) {
                    mFairy.initMatTime();
                    result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});
                    if (result.sim < 0.8f) {
                        mFairy.onTap(1133, 329, 1141, 337, "开启自动战斗", Sleep);
                    }
                    setTaskName(4);
                    return;
                }
            }
        }.taskContent(mFairy, "据点战中");
    }//据点战

    public void hejiu() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {

                super.inOperation();
                int m = mFairy.dateMinute();
                if (m >= 59) {
                    LtLog.e(mFairy.getLineInfo("喝酒神兽结束"));
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.goCity("轩辕");
                setTaskName(2);
                return;
            }

            public void content_1() throws Exception {
//                int ret = gameUtil.mission("dtfd.png", 2);
//                if (ret == 1) {
//                    setTaskName(2);
//                    return;
//                } else {
//                    setTaskEnd();return;
//                }
            }

            public void content_3() throws Exception {
//                result = mFairy.findPic("xmld.png");
//                if (result.sim>0.7f){
//                    setTaskName(2);
//                }else {
//                    gameUtil.huimeng();
//
            }

            public void content_2() throws Exception {
                result = mFairy.findPic("tuteng2.png");
                mFairy.onTap(0.8f, result, 990, 125, 1001, 132, "关闭", Sleep);

                result = mFairy.findPic(365, 438, 956, 587, new String[]{"jiu.png", "jiu1.png", "jiu2.png"});
                if (result.sim > 0.8f) {
                    List<FindResult> listResult = mFairy.findPic(365, 438, 956, 587, 0.8f, "jiu.png");
                    if (listResult.size() != 0) {
                        mFairy.onTap(0.8f, listResult.get(listResult.size() - 1), listResult.get(listResult.size() - 1).x, listResult.get(listResult.size() - 1).y, listResult.get(listResult.size() - 1).x + 1, listResult.get(listResult.size() - 1).y + 1, "喝酒道具", Sleep);
                        result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});
                        if (result.sim < 0.8f) {
                            mFairy.onTap(1132, 331, 1141, 337, "开启自动战斗", Sleep);
                        }
                    }
                }
                result = mFairy.findPic(427, 313, 666, 384, "xmld2.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(427, 404, 615, 477, "hdld.png");
                    mFairy.onTap(0.8f, result, "回到领地", Sleep);
                }

                result = mFairy.findPic(349, 255, 941, 520, "hjGoAlcohol.png");
                mFairy.onTap(0.8f, result, "继续喝", Sleep);


                result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});
                if (result.sim < 0.8f) {
                    mFairy.onTap(1132, 331, 1141, 337, "开启自动战斗", Sleep);
                }

            }
        }.taskContent(mFairy, "喝酒神兽中");
    }//喝酒神兽

    public void swl() throws Exception {

        new TimingActivity(mFairy) {

            public void inOperation() throws Exception {
                super.inOperation();
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();
                int s = (h * 60 + m) * 60;
                int sm = s * 1000;
                if (h > 22 || sm > (begin.timeMillis + 1200000)) {
                    LtLog.e(mFairy.getLineInfo("山无棱结束"));
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
                int ret = gameUtil.mission("tyjdz.png", 3);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(50, 99)) return;
                Thread.sleep(2000);
                result = mFairy.findPic("swljrfb.png");
                mFairy.onTap(0.8f, result, "山无棱进入副本", Sleep);

                result = mFairy.findPic(548, 321, 739, 379, "csm.png");
                mFairy.onTap(0.8f, result, 771, 434, 784, 444, "山无棱传送进入副本", Sleep);

                result = mFairy.findPic(1069, 173, 1220, 263, "need.png");
                mFairy.onTap(0.8f, result, "需求", Sleep);

                result = mFairy.findPic("backa.png");
                mFairy.onTap(0.8f, result, "击退", Sleep);

                result = mFairy.findPic(1099, 277, 1173, 348, "NoBattle.png");
                mFairy.onTap(0.8f, result, "开启自动战斗", Sleep);

                result = mFairy.findPic(681, 399, 874, 483, "swlqrcs.png");
                mFairy.onTap(0.8f, result, "山无棱确认传送", Sleep);
                if (result.sim > 0.8f) {
                    err = -10000;
                }
                result = mFairy.findPic(466, 89, 816, 212, "wfjr.png");
                mFairy.onTap(0.8f, result, "山无棱未开启", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("swlstop.png");
                mFairy.onTap(0.8f, result, "山无棱结束", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "山无棱中");
    } //山无棱

    public void jitian() throws Exception {
        new TimingActivity(mFairy) {
            @Override
            public void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("death.png");
                mFairy.onTap(0.8f, result, "复活", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(4);
                    return;
                }
                int m = mFairy.dateMinute();
                if (m >= 20) {
                    LtLog.e(mFairy.getLineInfo("祭天结束"));
                    setTaskEnd();
                    return;
                }
            }

            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic("daily.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常"));
                if (result.sim > 0.8f)
                    result = mFairy.findPic(796, 8, 1115, 157, new String[]{"daily.png", "fuli.png"});
                {
                    result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png", "bahuang.png", "bh.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("有天外山海图标去天外据点战"));
                        setTaskName(2);
                    } else {
                        LtLog.e(mFairy.getLineInfo("没有天外山海图标去本服据点战"));
                        setTaskName(3);
                    }
                }
            }

            public void content_2() throws Exception {
                int ret = gameUtil.tymission("jitian.png");
                if (ret == 1) {
                    setTaskName(4);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_3() throws Exception {
                int ret = gameUtil.mission("jitian.png", 3);
                if (ret == 1) {
                    setTaskName(4);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_4() throws Exception {
                result = mFairy.findPic("jitianqianwang.png");
                mFairy.onTap(0.8f, result, "立即前往", 10000);

                result = mFairy.findPic(1182, 160, 1275, 250, "Replica.png");
                if (picCountS(0.8f, result, "不在祭天内") > 30) {
                    LtLog.e(mFairy.getLineInfo("祭天结束"));
                    setTaskEnd();
                    return;
                }
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic("Hangup1.png");
                    if (result1.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("战斗中"));
                        result1 = mFairy.findPic("nodiren.png");
                        if (result1.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, 146, 251, 167, 262, "祭天副本中", Sleep);
                            setTaskName(5);
                            return;
                        }
                    } else {
                        mFairy.onTap(0.8f, result, 146, 251, 167, 262, "祭天副本中", Sleep);
                        setTaskName(5);
                        return;
                    }
                }
            }

            public void content_5() throws Exception {
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 5) {
                    mFairy.initMatTime();
                    result = mFairy.findPic(1099, 277, 1173, 348, "NoBattle.png");
                    mFairy.onTap(0.8f, result, "开启自动战斗", Sleep);
                    setTaskName(4);
                    return;
                }
            }
        }.taskContent(mFairy, "祭天中");
    }//祭天

    public void bqlt() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                if (m >= 30) {
                    LtLog.e(mFairy.getLineInfo("版权擂台结束"));
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(50, 99)) return;
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                if (dazeTime > 5) {

                    result = mFairy.findPic("daily.png");
                    mFairy.onTap(0.8f, result, "日常", 1000);

                    result = mFairy.findPic("xshd.png");
                    mFairy.onTap(0.8f, result, "限时活动", 1000);

                    result = mFairy.findPic("bqlt.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "版权擂台点击", 500);

                        result = mFairy.findPic("bqltqw.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, 955, 587, 1106, 641, "版权擂台点击", 500);
                            setTaskName(2);
                            return;
                        } else {
                            setTaskEnd();
                            return;
                        }
                    }
                }
                /*int ret=gameUtil.mission("tyjdz.png",3);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();return;
                }*/
            }

            public void content_2() throws Exception {
                if (overtime(60, 99)) return;
                Thread.sleep(2000);

                result = mFairy.findPic(589, 556, 820, 673, "wbm.png");
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("bqlt_jrbs.png");
                mFairy.onTap(0.8f, result, "版权擂台进入比赛", Sleep);

                result = mFairy.findPic("bqlt_sure.png");
                mFairy.onTap(0.8f, result, "版权擂台确认传送", Sleep);
                if (result.sim > 0.8f) {
                    err = -10000;
                }
                result = mFairy.findPic("bqlt_sure1.png");
                mFairy.onTap(0.8f, result, "版权擂台确认奖励结束", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "版权擂台中");
    }//版权擂台


    public void spoil() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                int s = (h * 60 + m) * 60;
                if (!((s > 45000 && s < 54000) || (s > 64800 && s < 84600))) {
                    LtLog.e(mFairy.getLineInfo("灵宠夺宝结束"));
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
                int ret = gameUtil.mission("xsSpoil.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(50, 0)) return;
                result = mFairy.findPic("jymatching.png");
                mFairy.onTap(0.8f, result, "个人匹配", Sleep);

                result = mFairy.findPic("jybattle.png");
                mFairy.onTap(0.8f, result, "加入战斗", Sleep);

                result = mFairy.findPic("jiuyoufinish.png");
                mFairy.onTap(0.8f, result, 619, 432, 655, 442, "灵宠无法匹配了", Sleep);
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("灵宠夺宝匹配了"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("word hint pet fight.png");
                mFairy.onTap(0.8f, result, 645, 438, 654, 448, "确定", 1000);
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("没有灵宠出战，任务结束"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("matching.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                    err = 0;
                }

                result = mFairy.findPic("Intheteam.png");
                mFairy.onTap(0.8f, result, "队伍中确定", Sleep);


                result = mFairy.findPic("Openbox.png");
                mFairy.onTap(0.8f, result, "开箱", 10000);


                result = mFairy.findPic("Replica.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    err = 0;
                }

                result = mFairy.findPic(1078, 1, 1278, 158, "Spoilwithin.png");
                result1 = mFairy.findPic(1078, 1, 1278, 158, new String[]{"gjSafetyZone.png", "gjSafetyZone1.png"});
                if (result1.sim > 0.65 && result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(1198, 81, 1199, 82, "打开地图", 2000);
                    mFairy.onTap(423, 351, 424, 352, "去中间", 2000);
                    gameUtil.close(0);
                }
            }
        }.taskContent(mFairy, "灵宠夺宝中");
    }//灵宠夺宝

    public void xuanhai() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                int h = mFairy.dateHour();
                if (h == 23 && m >= 30) {
                    LtLog.e(mFairy.getLineInfo("玄海结束"));
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
                int ret = gameUtil.mission("Genkai.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            int x = 0;

            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                if (overtime(50, 0)) return;
                result = mFairy.findPic("jymatching.png");
                mFairy.onTap(0.8f, result, "个人匹配", Sleep);

                result1 = mFairy.findPic(402, 4, 637, 109, "wof.png");
                result2 = mFairy.findPic(751, 43, 836, 116, "wof.png");
                if (result1.sim > 0.8f) {
                    x = 1;
                }
                if (result2.sim > 0.8f) {
                    x = 2;
                }

                result = mFairy.findPic("matching.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                    err = 0;
                }

                result = mFairy.findPic("jybattle.png");
                mFairy.onTap(0.8f, result, "加入战斗", Sleep);

                result = mFairy.findPic("jiuyoufinish.png");
                mFairy.onTap(0.8f, result, 619, 432, 655, 442, "玄海无法匹配了", Sleep);
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("玄海不能匹配了"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(349, 253, 913, 518, "yjzdwz.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(657, 392, 913, 503, "Intheteam.png");
                    mFairy.onTap(0.8f, result, "队伍中确定", Sleep);
                }

                result1 = mFairy.findPic(377, 272, 905, 480, "zpms1.png");
                if (result1.sim > 0.75f) {
                    mFairy.onTap(0.75f, result1, 763, 430, 782, 447, "开启战袍确认", Sleep);
                }

                result1 = mFairy.findPic("Replica.png");
                result = mFairy.findPic("gjSafetyZone.png");
                if (result.sim > 0.65 && result1.sim > 0.8f) {
                    result = mFairy.findPic(4, 173, 243, 412, "gong.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, result.x, result.y + 24, result.x + 33, result.y + 48, "玄海指引 攻", 6000);
                    } else {
                        if (x == 1) {
                            result = mFairy.findPic(4, 173, 243, 412, new String[]{"zise1.png", "xuanhai.png"});
                            mFairy.onTap(0.8f, result, "玄海指引 紫色", 6000);
                        } else if (x == 2) {
                            result = mFairy.findPic(4, 173, 243, 412, new String[]{"huangse.png", "xuanhai.png"});
                            mFairy.onTap(0.8f, result, "玄海指引 黄色", 6000);
                        } else {
                            mFairy.onTap(121, 293, 122, 294, "玄海指引 中间！", 6000);
                        }
                    }
                }
                result = mFairy.findPic(830, 599, 1036, 658, "xhleave.png");
                result1 = mFairy.findPic("xhfail.png");
                if (result.sim > 0.8f && result1.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "离开", Sleep);
                } else if (result.sim > 0.8f && result1.sim < 0.8f) {
                    mFairy.onTap(0.8f, result, "离开", Sleep);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("Replica.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(4, 173, 243, 412, "gong.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, result.x, result.y + 24, result.x + 33, result.y + 48, "玄海指引 攻", 10000);
                    } else {
                        if (x == 1) {
                            result = mFairy.findPic(4, 173, 243, 412, new String[]{"zise1.png", "xuanhai.png"});
                            mFairy.onTap(0.8f, result, "玄海指引 紫色", 10000);
                        } else if (x == 2) {
                            result = mFairy.findPic(4, 173, 243, 412, new String[]{"huangse.png", "xuanhai.png"});
                            mFairy.onTap(0.8f, result, "玄海指引 黄色", 10000);
                        } else {
                            result = mFairy.findPic("xuanhai.png");
                            mFairy.onTap(0.8f, result, 13, 184, 237, 404, "玄海指引", 10000);
                        }
                    }
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("副本中"));

                }


                result = mFairy.findPic(1116, 2, 1279, 32, "xuanhaidt.png");
                if (result.sim > 0.8f) {
                    if (dazeTime > 2) {
                        mFairy.initMatTime();
                        result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});
                        if (result.sim < 0.8f) {
                            mFairy.onTap(1133, 329, 1141, 337, "开启自动战斗", Sleep);
                        }
                    }
                }

            }

        }.taskContent(mFairy, "玄海中");
    }//玄海

    public void bwzq() throws Exception {
        new TimingActivity(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                overtime(30, 0);
                int hour = mFairy.dateHour();
                if (hour >= 19) {
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(876, 5, 1120, 146, "daily.png");
                mFairy.onTap(0.8f, result, 1074, 34, 1085, 49, "日常", Sleep);

                result = mFairy.findPic(518, 2, 1116, 233, "zhaoq.png");
                mFairy.onTap(0.8f, result, "比武招亲", Sleep);

                result = mFairy.findPic(743, 575, 1162, 689, "filljc.png");
                mFairy.onTap(0.8f, result, "免费竞猜", Sleep);
                result = mFairy.findPic(506, 111, 784, 234, "repeat.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("竞猜过了"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(725, 161, 1033, 594, "lookgood.png");
                mFairy.onTap(0.8f, result, "看好他", Sleep);

                result = mFairy.findPic(361, 253, 934, 502, "marriage.png");
                mFairy.onTap(0.8f, result, 766, 433, 773, 442, "确定", Sleep);


                //
                result = mFairy.findPic("seeover.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("任务完成"));
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("closehuodong.png");
                mFairy.onTap(0.8f, result, 640, 426, 658, 439, "没人报名任务结束", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy, "招亲竞猜");
    }//比武招亲

    public void demo() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int m = mFairy.dateMinute();
                if (m >= 59) {
                    LtLog.e(mFairy.getLineInfo("喝酒神兽结束"));
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.huimeng();
                setTaskName(2);
                return;
            }

            public void content_1() throws Exception {
//                int ret = gameUtil.mission("dtfd.png", 2);
//                if (ret == 1) {
//                    setTaskName(2);
//                    return;
//                } else {
//                    setTaskEnd();return;
//                }
            }

            public void content_3() throws Exception {
//                result = mFairy.findPic("xmld.png");
//                if (result.sim>0.7f){
//                    setTaskName(2);
//                }else {
//                    gameUtil.huimeng();
//                }
            }

            public void content_2() throws Exception {
                result = mFairy.findPic("tuteng2.png");
                mFairy.onTap(0.8f, result, 990, 125, 1001, 132, "关闭", Sleep);

                result = mFairy.findPic(365, 438, 956, 587, "jiu.png");
                if (result.sim > 0.8f) {
                    List<FindResult> listResult = mFairy.findPic(365, 438, 956, 587, 0.8f, "jiu.png");
                    if (listResult.size() != 0) {
                        mFairy.onTap(0.8f, listResult.get(listResult.size() - 1), listResult.get(listResult.size() - 1).x, listResult.get(listResult.size() - 1).y, listResult.get(listResult.size() - 1).x + 1, listResult.get(listResult.size() - 1).y + 1, "喝酒道具", Sleep);
                        result = mFairy.findPic(1099, 277, 1173, 348, "NoBattle.png");
                        mFairy.onTap(0.8f, result, "开启自动战斗", Sleep);
                    }
                }
                result = mFairy.findPic("hjGoAlcohol.png");
                mFairy.onTap(0.8f, result, "继续喝", Sleep);


                result = mFairy.findPic(1099, 277, 1173, 348, "NoBattle.png");
                mFairy.onTap(0.8f, result, "开启自动战斗", Sleep);
            }
        }.taskContent(mFairy, "喝酒神兽中测试");
    }//测试
}
