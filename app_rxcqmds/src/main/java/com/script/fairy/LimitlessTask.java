package com.script.fairy;


import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class LimitlessTask {
    public GamePublicFuntion gamePublicFuntion;
    public FindResult result;
    public AtFairyImpl mFairy;
    public SingleTask singleTask;
    public int week = 0;
    public int hour = 0;
    public int minute = 0;

    public LimitlessTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        singleTask = new SingleTask(ypFairy);
    }


    List<Integer> map_list = new ArrayList();

    final int MAPNUM = 88;

    private static boolean HMAP = false;
    private static int HMAPCOUNT = 0;
    boolean mainScene_timeBool = false;
    public static boolean SWITCH_USEROPERATION = false;
    public static boolean SWITCH_XUEOPERATION = false;

    public void limitlessBattle() throws Exception {
        new TaskContent(mFairy, "无限战斗") {

            void create() throws Exception {
                HMAP = true;
                SWITCH_USEROPERATION = false;
                SWITCH_XUEOPERATION = false;
                GamePublicFuntion.nnBool = true;
                if (!AtFairyConfig.getOption("users").equals("") && !AtFairyConfig.getOption("users").equals("1")) {
                    UserOperation userOperation = new UserOperation();
                    userOperation.start();
                }

                if (!AtFairyConfig.getOption("xue").equals("") && !AtFairyConfig.getOption("xue").equals("0")) {
                    XuerOperation xuerOperation = new XuerOperation();
                    xuerOperation.start();
                }

                if (AtFairyConfig.getOption("map").equals("1")) {
                    LtLog.e(mFairy.getLineInfo("用户没有勾选地图"));
                    HMAP = false;

                } else {
                    for (int i = 1; i <= MAPNUM; i++) {
                        if (AtFairyConfig.getOption("map" + i).equals("1")) {
                            map_list.add(i);
                        }
                    }
                }
            }

            void init() throws Exception {
                SWITCH_USEROPERATION = false;
                SWITCH_XUEOPERATION = false;
                timeMapInit("boss");
                timeMapInit("boss1");
                judgeOneSecond = 0;

                gamePublicFuntion.close(1);

                if (gamePublicFuntion.mainScene()) {
                    setTaskName(1);
                    return;
                } else {
                    gamePublicFuntion.goHome();
                    Thread.sleep(2000);
                }

            }

            void content_01() throws Exception {
                if (HMAP) {

                    if (AtFairyConfig.getOption("zhuansheng").equals("1")) {
                        LtLog.e(mFairy.getLineInfo("开始转生"));
                        gamePublicFuntion.seekNPC(520, 260, 521, 261, "zhuansheng1.png");
                    }

                    if (!AtFairyConfig.getOption("yjmx").equals("") && !AtFairyConfig.getOption("yjmx").equals("0")) {
                        int ix = Integer.parseInt(AtFairyConfig.getOption("yjmx"));
                        if (ix >= gamePublicFuntion.currentXue()) {
                            LtLog.e(mFairy.getLineInfo("开始一键满血"));
                            gamePublicFuntion.seekNPC(524, 260, 525, 261, "manxue.png");
                        }
                    }


                    long rong_time = getTimeStamp(AtFairyConfig.getOption("rong_time"));

                    if (rong_time != 0) {

                        if (timeMap("rong_time", rong_time)) {

                            if (AtFairyConfig.getOption("ronglian1").equals("1") ||
                                    AtFairyConfig.getOption("ronglian2").equals("1") ||
                                    AtFairyConfig.getOption("ronglian3").equals("1") ||
                                    AtFairyConfig.getOption("ronglian4").equals("1") ||
                                    AtFairyConfig.getOption("ronglian5").equals("1")) {
                                LtLog.e(mFairy.getLineInfo("装备熔炼"));

                                LtLog.e(mFairy.getLineInfo("前往终极之城"));
                                gamePublicFuntion.seekNPC(518, 263, 519, 264, "ma1.png");
                                gamePublicFuntion.seekNPC(893, 169, 894, 170, "ronglian.png");

                                for (int i = 0; i < 5; i++) {
                                    LtLog.e(mFairy.getLineInfo("装备熔炼中"));

                                    if (AtFairyConfig.getOption("ronglian" + (i + 1)).equals("1")) {

                                        mFairy.onTap(208, 150 + (56 * i), 209, 151 + (56 * i), "第" + (7 + i) + "转", 500);
                                        mFairy.onTap(208, 150 + (56 * i), 209, 151 + (56 * i), "第" + (7 + i) + "转", 1000);
                                        for (int q = 0; q < 4; q++) {

                                            for (int w = 0; w < 6; w++) {//340 146  340 214
                                                result = mFairy.findPic(310, 175 + (w * 68) - 20, 350, 175 + (w * 68) + 20, "ronglian2.png");
                                                if (result.sim < 0.8f) {
                                                    mFairy.onTap(330, 175 + (w * 68), 331, 176 + (w * 68), "勾选", 1000);
                                                }
                                            }

                                            mFairy.onTap(687, 586, 725, 600, "下一页", 2000);
                                        }

                                        mFairy.onTap(841, 583, 873, 598, "一键熔炼", 1000);
                                    }
                                }

                                gamePublicFuntion.close(3);
                                gamePublicFuntion.goHome();


                                gamePublicFuntion.cunc();

                            }
                        }
                    }

                    //等复活戒指
                    if (AtFairyConfig.getOption("fhjz").equals("1")) {

                        result = mFairy.findPic("fhjz.png");
                        if (result.sim > 0.8f) {
                            for (int i = 0; i < 65; i++) {
                                Thread.sleep(1000);
                                result = mFairy.findPic("fhjz1.png");
                                if (result.sim > 0.8f) {
                                    break;
                                }
                            }
                        }
                    }

                    gamePublicFuntion.close(3);
                    gamePublicFuntion.cMap(map_list.get(HMAPCOUNT));
                    gamePublicFuntion.close(3);

                    HMAPCOUNT++;
                    if (HMAPCOUNT > map_list.size() - 1) {
                        HMAPCOUNT = 0;
                    }
                }

                mainScene_timeBool = true;
                SWITCH_USEROPERATION = true;
                SWITCH_XUEOPERATION = true;
                setTaskName(2);
            }//切换地图

            void content_02() throws Exception {
                gamePublicFuntion.limErrClose();

                if (gamePublicFuntion.lmiMainScenes()) {
                    if (mainScene_timeBool) {
                        SWITCH_USEROPERATION = false;
                        SWITCH_XUEOPERATION = false;
                        LtLog.e(mFairy.getLineInfo("【主城】"));

                        LtLog.e(mFairy.getLineInfo("等待时间：" + AtFairyConfig.getOption("home_time")));
                        Thread.sleep(getTimeStamp(AtFairyConfig.getOption("home_time")));//设置多少秒
                        mainScene_timeBool = false;

                        setTaskName(0);
                        return;
                    }
                } else {
                    /**
                     *
                     *  副本中的判断
                     *
                     */
                    gamePublicFuntion.autoBattle();

                    //清理背包

                    if(AtFairyConfig.getOption("8795").equals("1")) {
                        long hs_time = getTimeStamp(AtFairyConfig.getOption("hs_time"));
                        if (hs_time != 0) {
                            if (timeMap("hs_time", hs_time)) {
                                gamePublicFuntion.clearPackage();
                                return;
                            }
                        }
                    }

                    //多久没有找打怪回城
                    long boss_time = getTimeStamp(AtFairyConfig.getOption("map_qhtime"));
                    if (boss_time != 0) {

                        result = mFairy.findPic("boss.png");
                        if (result.sim > 0.8f) {
                            timeMapInit("boss");

                        } else {
                            if (timeMap("boss", boss_time)) {
                                gamePublicFuntion.goHome();
                                setTaskName(0);
                                return;
                            }

                        }
                    }

                    //多久强制回城
                    long boss1_time = getTimeStamp(AtFairyConfig.getOption("map_qz_qhtime"));
                    if (boss1_time != 0) {

                        if (timeMap("boss1", boss1_time)) {
                            gamePublicFuntion.goHome();
                            setTaskName(0);
                            return;
                        }


                    }


                    //战斗模式
                    result = mFairy.findPic("map2.png");
                    if (result.sim > 0.8f && judgeOneSecond()) {
                        switch (AtFairyConfig.getOption("battle_type")) {
                            case "1":
                                result = mFairy.findPic("battle_type1.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(957, 148, 968, 184, "行会模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "2":
                                result = mFairy.findPic("battle_type2.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(769, 151, 784, 186, "全体模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "3":
                                result = mFairy.findPic("battle_type3.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(832, 152, 849, 188, "和平模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "4":
                                result = mFairy.findPic("battle_type4.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(1019, 152, 1030, 183, "善恶模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                        }
                    }

                    //站着不动随机移动
                    long yidong = getTimeStamp(AtFairyConfig.getOption("yidong_time"));
                    if (yidong != 0) {
                        long yidong_time = mFairy.mMatTime(1213, 196, 61, 14, 0.98f);
                        if (yidong_time >= (yidong / 1000)) {


                            mFairy.initMatTime();

                            gamePublicFuntion.rany();

                            gamePublicFuntion.close(1);

                        }
                    }


                    //存仓设置

                    if(!AtFairyConfig.getOption("rlhcc").equals("1")) {

                        long cunc_time = getTimeStamp(AtFairyConfig.getOption("cunc_time"));
                        if (cunc_time != 0) {
                            if (timeMap("cunc_time", cunc_time)) {
                                gamePublicFuntion.cunc();
                                return;
                            }
                        }
                    }


                    result = mFairy.findPic(954, 360, 1274, 685, "set1.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(1236,324,1252,339,"切换成战斗界面",1000);
                    }


                    result = mFairy.findPic("boss.png");
                    if (result.sim > 0.8f) {
                        long ying = getTimeStamp(AtFairyConfig.getOption("ying_time"));
                        if (ying != 0) {
                            if (timeMap("ying", ying)) {
                                mFairy.touchDown(1189, 593);
                                mFairy.touchMove(1138, 652, 200);
                                mFairy.touchUp();
                            }
                        }
                    }


                    if(AtFairyConfig.getOption("pug").equals("1")) {
                        for (int i = 0; i < 10; i++) {
                            mFairy.tap(1189, 594);
                        }

                    }
                }

            }

            void inOperation() throws Exception {
                //限时活动
                if (xianshi()) {
                    setTaskName(0);
                    return;
                }
            }

            boolean xianshi() throws Exception {


                int hour = mFairy.dateHour();
                int minute = mFairy.dateMinute();
                int week = mFairy.week();
                if (AtFairyConfig.getOption("xzml").equals("1")) {
                    if ((hour == 14 && minute > 30) || (hour == 21 && minute > 30)) {
                        SWITCH_USEROPERATION = false;
                        xsAct(2);
                        return true;
                    }
                }

                if (AtFairyConfig.getOption("dld").equals("1")) {
                    if (hour == 19 && minute > 30 && minute < 50) {
                        SWITCH_USEROPERATION = false;
                        xsAct(1);
                        return true;
                    }
                }

                if (!AtFairyConfig.getOption("yao").equals("")) {
                    if (hour == 20 && week == 1) {
                        SWITCH_USEROPERATION = false;
                        yt();
                        return true;
                    }
                }

                if (AtFairyConfig.getOption("bsk").equals("1")) {
                    if (hour == 20 && week == 7) {
                        SWITCH_USEROPERATION = false;
                        bsk();
                        return true;
                    }
                }

                if (AtFairyConfig.getOption("kfyz").equals("1")) {
                    if (hour == 20 && week == 5) {
                        SWITCH_USEROPERATION = false;
                        kfyz();
                        return true;
                    }
                }

                if (AtFairyConfig.getOption("ldz").equals("1")) {
                    if (hour == 20 && week == 2) {
                        SWITCH_USEROPERATION = false;
                        ldz();
                        return true;
                    }
                }


                if (AtFairyConfig.getOption("mj").equals("1")) {
                    if (hour == 20 && week == 3) {
                        SWITCH_USEROPERATION = false;
                        mj();
                        return true;
                    }
                }


                if (AtFairyConfig.getOption("hdldz").equals("1")) {
                    if (hour == 20 && week == 4) {
                        SWITCH_USEROPERATION = false;
                        hdldz();
                        return true;
                    }
                }


                if (!AtFairyConfig.getOption("slmj").equals("")) {
                    if (hour == 12) {
                        SWITCH_USEROPERATION = false;
                        slmj();
                        return true;
                    }
                }

                return false;
            }

            class UserOperation extends Thread {

                public void run() {
                    try {
                        int count = 0;
                        while (System.currentTimeMillis() - Abnormal.thTime < 10000) {
                            Thread.sleep(100);
                            if (SWITCH_USEROPERATION == true) {
                                result = mFairy.findPic("map2.png");
                                if (result.sim > 0.8f && !gamePublicFuntion.lmiMainScenes()) {

                                    long l = mFairy.getColorNum(1168, 113, 1220, 158, "255,255,0", 0.98f);
                                    if (l > 0) {
                                        count++;
                                        if (count > 2) {
                                            count = 0;
                                            switch (AtFairyConfig.getOption("users")) {
                                                case "2":
                                                    result = mFairy.findPic(587, 509, 950, 567, "hui.png");
                                                    mFairy.onTap(0.85f, result, "回城", 2000);
                                                    break;
                                                case "3":
                                                    gamePublicFuntion.rany();
                                                    gamePublicFuntion.close(3);
                                                    break;
                                                case "4":
                                                    gamePublicFuntion.delivery();
                                                    break;
                                                case "5":
                                                    mFairy.touchDown(1188, 592);
                                                    mFairy.touchMove(1246, 534, 500);
                                                    mFairy.touchUp();
                                                    Thread.sleep(3000);
                                                    break;
                                            }
                                            Thread.sleep(20000);
                                        }
                                    } else {
                                        count = 0;
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {


                    }


                }
            }

            class XuerOperation extends Thread {
                public void run() {
                    try {

                        int count = 0;
                        while (System.currentTimeMillis() - Abnormal.thTime < 10000) {
                            Thread.sleep(100);

                            if (SWITCH_XUEOPERATION == true && !gamePublicFuntion.lmiMainScenes()) {

                                int ix = Integer.parseInt(AtFairyConfig.getOption("xue"));

                                if (ix >= gamePublicFuntion.currentXue()) {
                                    count++;
                                } else {
                                    count = 0;
                                }

                                if (count > 2) {
                                    count = 0;

                                    switch (AtFairyConfig.getOption("xueop")) {
                                        case "1":
                                            gamePublicFuntion.ranytou();
                                            break;
                                        case "2":
                                            for (int i = 0; i < 5; i++) {
                                                result = mFairy.findPic(943, 385, 1268, 700, "ys.png");
                                                mFairy.onTap(0.8f, result, "隐身", 200);
                                            }

                                            break;
                                        case "3":
                                            for (int i = 0; i < 5; i++) {
                                                result = mFairy.findPic(943, 385, 1268, 700, "ys.png");
                                                mFairy.onTap(0.8f, result, "隐身", 200);
                                            }

                                            gamePublicFuntion.ranytou();
                                            break;
                                        case "4":
                                            gamePublicFuntion.delivery();
                                            break;
                                    }


                                    Thread.sleep(20000);
                                }


                            }
                        }
                    } catch (Exception e) {


                    }


                }
            }
        };


    }//无限战斗

    public void xsAct(int num) throws Exception {
        new TaskContent(mFairy, "限时任务 :" + num) {
            void init() throws Exception {
                gamePublicFuntion.close(3);
                gamePublicFuntion.goHome();

                setTaskName(1);

            }

            void inOperation() throws Exception {

                result = mFairy.findPic("xzml1.png");
                mFairy.onTap(0.8f, result, "前往", 1000);

            }

            void content_01() throws Exception {


                result = mFairy.findPic("act1.png");
                mFairy.onTap(0.8f, result, "活动弹窗", 1000);

                result = mFairy.findPic(543, 13, 1067, 214, "act2.png");
                mFairy.onTap(0.8f, result, "限时活动", 1000);

                result = mFairy.findPic(164, 463, 1111, 629, "act3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("活动界面"));

                    switch (num) {
                        case 1:
                            mFairy.onTap(288, 528, 356, 543, "参加大乱斗", 3000);
                            setTaskName(2);
                            break;
                        case 2:
                            mFairy.onTap(561, 525, 631, 538, "参加血战魔龙", 3000);
                            judgeOneSecond = 0;
                            setTaskName(3);
                            break;
                    }

                }

                timeCount(10, 0);

            }

            //大乱斗
            void content_02() throws Exception {
                int minute = mFairy.dateMinute();
                if (minute >= 50) {
                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic("xzml2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    for (int i = 0; i < 5; i++) {
                        mFairy.tap(1183, 590);
                    }

                    gamePublicFuntion.autoBattle();
                }

                timeCount(30, 0);
            }


            //血战魔龙
            void content_03() throws Exception {
                int hour = mFairy.dateHour();
                if (hour == 15 || hour == 22) {
                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic("xzml2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    //战斗模式
                    result = mFairy.findPic("map2.png");
                    if (result.sim > 0.8f && judgeOneSecond()) {
                        switch (AtFairyConfig.getOption("battle_type")) {
                            case "1":
                                result = mFairy.findPic("battle_type1.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(957, 148, 968, 184, "行会模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "2":
                                result = mFairy.findPic("battle_type2.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(769, 151, 784, 186, "全体模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "3":
                                result = mFairy.findPic("battle_type3.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(832, 152, 849, 188, "和平模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "4":
                                result = mFairy.findPic("battle_type4.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(1019, 152, 1030, 183, "善恶模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                        }
                    }





                    gamePublicFuntion.autoBattle();

                    result = mFairy.findPic(954, 360, 1274, 685, "set1.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(1236,324,1252,339,"切换成战斗界面",1000);
                    }

                    if(AtFairyConfig.getOption("pug").equals("1")) {
                        for (int i = 0; i < 10; i++) {
                            mFairy.tap(1189, 594);
                        }

                    }

                }

                timeCount(30, 0);
            }


        };


    }//限时任务

    public void yt() throws Exception {
        new TaskContent(mFairy, "妖塔") {

            void init() throws Exception {
                gamePublicFuntion.close(3);
                gamePublicFuntion.goHome();
                setTaskName(1);

            }

            void content_01() throws Exception {

                result = mFairy.findPic("act1.png");
                mFairy.onTap(0.8f, result, "活动弹窗", 1000);

                result = mFairy.findPic(543, 13, 1067, 214, "ry.png");
                mFairy.onTap(0.8f, result, "跨服荣耀", 1000);

                result = mFairy.findPic(1060, 296, 1228, 582, "ry1.png");
                mFairy.onTap(0.8f, result, "无尽战场", 1000);


                result = mFairy.findPic(167, 454, 473, 628, "act3.png");
                if (result.sim > 0.8f) {

                    if (frequencyMap("qianwang", 3)) {
                        mFairy.onTap(0.8f, result, "前往", 1000);
                        setTaskName(2);
                        return;
                    }
                }


                timeCount(10, 0);

            }

            void content_02() throws Exception {
                int hour = mFairy.dateHour();
                if (hour == 21) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("xzml1.png");
                mFairy.onTap(0.8f, result, "前往", 1000);

                result = mFairy.findPic(441, 5, 853, 198, "yaota.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    switch (AtFairyConfig.getOption("yao")) {
                        case "1":
                            mFairy.onTap(272, 158, 295, 171, "1", 1500);
                            break;
                        case "2":
                            mFairy.onTap(263, 212, 303, 227, "2", 1500);
                            break;
                        case "3":
                            mFairy.onTap(275, 266, 298, 283, "3", 1500);
                            break;
                        case "4":
                            mFairy.onTap(273, 326, 301, 338, "4", 1500);
                            break;
                        case "5":
                            mFairy.onTap(281, 385, 305, 399, "5", 1500);
                            break;
                        case "6":
                            mFairy.onTap(272, 438, 292, 453, "6", 1500);
                            break;
                        case "7":
                            mFairy.onTap(280, 499, 299, 512, "7", 1500);
                            break;
                        case "8":
                            mFairy.onTap(270, 547, 298, 566, "8", 1500);
                            break;
                        case "9":
                            mFairy.onTap(282, 609, 305, 617, "9", 1500);
                            break;
                    }


                    result = mFairy.findPic(604, 560, 772, 641, "yaota1.png");
                    mFairy.onTap(0.8f, result, "前往挑战", 1000);

                }


                result = mFairy.findPic("xzml2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("map2.png");
                    if (result.sim > 0.8f && judgeOneSecond()) {
                        switch (AtFairyConfig.getOption("battle_type")) {
                            case "1":
                                result = mFairy.findPic("battle_type1.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(957, 148, 968, 184, "行会模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "2":
                                result = mFairy.findPic("battle_type2.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(769, 151, 784, 186, "全体模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "3":
                                result = mFairy.findPic("battle_type3.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(832, 152, 849, 188, "和平模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "4":
                                result = mFairy.findPic("battle_type4.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(1019, 152, 1030, 183, "善恶模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                        }
                    }


                    gamePublicFuntion.autoBattle();

                    result = mFairy.findPic(954, 360, 1274, 685, "set1.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(1236,324,1252,339,"切换成战斗界面",1000);
                    }
                    if(AtFairyConfig.getOption("pug").equals("1")) {
                        for (int i = 0; i < 10; i++) {
                            mFairy.tap(1189, 594);
                        }

                    }
                }


                timeCount(20, 0);
            }


        };


    }//妖塔

    public void bsk() throws Exception {
        new TaskContent(mFairy, "跨服巴沙克") {

            void init() throws Exception {
                gamePublicFuntion.close(3);
                gamePublicFuntion.goHome();
                setTaskName(1);

            }

            void content_01() throws Exception {

                result = mFairy.findPic("act1.png");
                mFairy.onTap(0.8f, result, "活动弹窗", 1000);

                result = mFairy.findPic(543, 13, 1067, 214, "ry.png");
                mFairy.onTap(0.8f, result, "跨服荣耀", 1000);


                result = mFairy.findPic(167, 454, 473, 628, "act3.png");
                if (result.sim > 0.8f) {

                    if (frequencyMap("qianwang", 3)) {
                        mFairy.onTap(0.8f, result, "前往", 1000);
                        setTaskName(2);
                        return;
                    }
                }


                timeCount(10, 0);

            }

            void content_02() throws Exception {
                int minute = mFairy.dateMinute();
                if (minute > 30) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("xzml1.png");
                mFairy.onTap(0.8f, result, "前往", 1000);

                result = mFairy.findPic("xzml2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("map2.png");
                    if (result.sim > 0.8f && judgeOneSecond()) {
                        switch (AtFairyConfig.getOption("battle_type")) {
                            case "1":
                                result = mFairy.findPic("battle_type1.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(957, 148, 968, 184, "行会模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "2":
                                result = mFairy.findPic("battle_type2.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(769, 151, 784, 186, "全体模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "3":
                                result = mFairy.findPic("battle_type3.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(832, 152, 849, 188, "和平模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "4":
                                result = mFairy.findPic("battle_type4.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(1019, 152, 1030, 183, "善恶模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                        }
                    }

                    gamePublicFuntion.autoBattle();
                    result = mFairy.findPic(954, 360, 1274, 685, "set1.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(1236,324,1252,339,"切换成战斗界面",1000);
                    }

                    if(AtFairyConfig.getOption("pug").equals("1")) {
                        for (int i = 0; i < 10; i++) {
                            mFairy.tap(1189, 594);
                        }

                    }
                }


                timeCount(20, 0);
            }


        };


    }//跨服巴沙克

    public void kfyz() throws Exception {
        new TaskContent(mFairy, "跨服远征") {

            void init() throws Exception {
                gamePublicFuntion.close(3);
                gamePublicFuntion.goHome();
                setTaskName(1);

            }

            void content_01() throws Exception {

                result = mFairy.findPic("act1.png");
                mFairy.onTap(0.8f, result, "活动弹窗", 1000);

                result = mFairy.findPic(543, 13, 1067, 214, "ry.png");
                mFairy.onTap(0.8f, result, "跨服荣耀", 1000);

                result = mFairy.findPic(486, 441, 712, 596, "act3.png");
                if (result.sim > 0.8f) {

                    if (frequencyMap("qianwang", 3)) {
                        mFairy.onTap(0.8f, result, "前往", 1000);
                        setTaskName(2);
                        return;
                    }
                }


                timeCount(10, 0);

            }

            void content_02() throws Exception {
                int hour = mFairy.dateHour();
                if (hour == 21) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("xzml1.png");
                mFairy.onTap(0.8f, result, "前往", 1000);

                result = mFairy.findPic("xzml2.png");
                if (result.sim > 0.8f) {
                    err = 0;


                    result = mFairy.findPic("map2.png");
                    if (result.sim > 0.8f && judgeOneSecond()) {
                        switch (AtFairyConfig.getOption("battle_type")) {
                            case "1":
                                result = mFairy.findPic("battle_type1.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(957, 148, 968, 184, "行会模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "2":
                                result = mFairy.findPic("battle_type2.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(769, 151, 784, 186, "全体模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "3":
                                result = mFairy.findPic("battle_type3.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(832, 152, 849, 188, "和平模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "4":
                                result = mFairy.findPic("battle_type4.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(1019, 152, 1030, 183, "善恶模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                        }
                    }

                    gamePublicFuntion.autoBattle();


                    result = mFairy.findPic(954, 360, 1274, 685, "set1.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(1236,324,1252,339,"切换成战斗界面",1000);
                    }
                    if(AtFairyConfig.getOption("pug").equals("1")) {
                        for (int i = 0; i < 10; i++) {
                            mFairy.tap(1189, 594);
                        }

                    }
                }


                timeCount(20, 0);
            }


        };


    }//跨服远征

    public void ldz() throws Exception {
        new TaskContent(mFairy, "掠夺战") {

            void init() throws Exception {
                gamePublicFuntion.close(3);
                gamePublicFuntion.goHome();
                setTaskName(1);

            }

            void content_01() throws Exception {
                result = mFairy.findPic("act1.png");
                mFairy.onTap(0.8f, result, "活动弹窗", 1000);

                result = mFairy.findPic(543, 13, 1067, 214, "ry.png");
                mFairy.onTap(0.8f, result, "跨服荣耀", 1000);

                result = mFairy.findPic(1060, 296, 1228, 582, "ry1.png");
                mFairy.onTap(0.8f, result, "无尽战场", 1000);


                result = mFairy.findPic(510, 466, 699, 575, "act3.png");
                if (result.sim > 0.8f) {

                    if (frequencyMap("qianwang", 3)) {
                        mFairy.onTap(0.8f, result, "前往", 1000);
                        setTaskName(2);
                        return;
                    }
                }


                timeCount(10, 0);

            }

            void content_02() throws Exception {
                int minute = mFairy.dateMinute();
                if (minute > 30) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("xzml1.png");
                mFairy.onTap(0.8f, result, "前往", 1000);

                result = mFairy.findPic(441, 5, 853, 198, "yaota.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    switch (AtFairyConfig.getOption("yao")) {
                        case "1":
                            mFairy.onTap(272, 158, 295, 171, "1", 1500);
                            break;
                        case "2":
                            mFairy.onTap(263, 212, 303, 227, "2", 1500);
                            break;
                        case "3":
                            mFairy.onTap(275, 266, 298, 283, "3", 1500);
                            break;
                        case "4":
                            mFairy.onTap(273, 326, 301, 338, "4", 1500);
                            break;
                        case "5":
                            mFairy.onTap(281, 385, 305, 399, "5", 1500);
                            break;
                        case "6":
                            mFairy.onTap(272, 438, 292, 453, "6", 1500);
                            break;
                        case "7":
                            mFairy.onTap(280, 499, 299, 512, "7", 1500);
                            break;
                        case "8":
                            mFairy.onTap(270, 547, 298, 566, "8", 1500);
                            break;
                        case "9":
                            mFairy.onTap(282, 609, 305, 617, "9", 1500);
                            break;
                    }


                    result = mFairy.findPic(604, 560, 772, 641, "yaota1.png");
                    mFairy.onTap(0.8f, result, "前往挑战", 1000);

                }


                result = mFairy.findPic("xzml2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("map2.png");
                    if (result.sim > 0.8f && judgeOneSecond()) {
                        switch (AtFairyConfig.getOption("battle_type")) {
                            case "1":
                                result = mFairy.findPic("battle_type1.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(957, 148, 968, 184, "行会模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "2":
                                result = mFairy.findPic("battle_type2.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(769, 151, 784, 186, "全体模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "3":
                                result = mFairy.findPic("battle_type3.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(832, 152, 849, 188, "和平模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "4":
                                result = mFairy.findPic("battle_type4.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(1019, 152, 1030, 183, "善恶模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                        }
                    }

                    gamePublicFuntion.autoBattle();

                    result = mFairy.findPic(954, 360, 1274, 685, "set1.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(1236,324,1252,339,"切换成战斗界面",1000);
                    }

                    if(AtFairyConfig.getOption("pug").equals("1")) {
                        for (int i = 0; i < 10; i++) {
                            mFairy.tap(1189, 594);
                        }

                    }
                }


                timeCount(20, 0);
            }


        };


    }//掠夺战

    public void mj() throws Exception {
        new TaskContent(mFairy, "秘境战场") {

            void init() throws Exception {
                gamePublicFuntion.close(3);
                gamePublicFuntion.goHome();
                setTaskName(1);

            }

            void content_01() throws Exception {
                result = mFairy.findPic("act1.png");
                mFairy.onTap(0.8f, result, "活动弹窗", 1000);

                result = mFairy.findPic(543, 13, 1067, 214, "ry.png");
                mFairy.onTap(0.8f, result, "跨服荣耀", 1000);

                result = mFairy.findPic(1060, 296, 1228, 582, "ry1.png");
                mFairy.onTap(0.8f, result, "无尽战场", 1000);


                result = mFairy.findPic(770, 475, 983, 569, "act3.png");
                if (result.sim > 0.8f) {

                    if (frequencyMap("qianwang", 3)) {
                        mFairy.onTap(0.8f, result, "前往", 1000);
                        setTaskName(2);
                        return;
                    }
                }


                timeCount(10, 0);

            }

            void content_02() throws Exception {
                int hour = mFairy.dateHour();
                if (hour == 21) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("xzml1.png");
                mFairy.onTap(0.8f, result, "前往", 1000);

                result = mFairy.findPic("xzml2.png");
                if (result.sim > 0.8f) {
                    err = 0;


                    result = mFairy.findPic("map2.png");
                    if (result.sim > 0.8f && judgeOneSecond()) {
                        switch (AtFairyConfig.getOption("battle_type")) {
                            case "1":
                                result = mFairy.findPic("battle_type1.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(957, 148, 968, 184, "行会模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "2":
                                result = mFairy.findPic("battle_type2.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(769, 151, 784, 186, "全体模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "3":
                                result = mFairy.findPic("battle_type3.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(832, 152, 849, 188, "和平模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "4":
                                result = mFairy.findPic("battle_type4.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(1019, 152, 1030, 183, "善恶模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                        }
                    }

                    gamePublicFuntion.autoBattle();

                    result = mFairy.findPic(954, 360, 1274, 685, "set1.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(1236,324,1252,339,"切换成战斗界面",1000);
                    }

                    if(AtFairyConfig.getOption("pug").equals("1")) {
                        for (int i = 0; i < 10; i++) {
                            mFairy.tap(1189, 594);
                        }

                    }
                }


                timeCount(20, 0);
            }


        };


    }//秘境战场

    public void hdldz() throws Exception {
        new TaskContent(mFairy, "领地战") {


            void init() throws Exception {
                gamePublicFuntion.close(3);
                gamePublicFuntion.goHome();
                setTaskName(1);

            }

            void content_01() throws Exception {
                result = mFairy.findPic("act1.png");
                mFairy.onTap(0.8f, result, "活动弹窗", 1000);

                result = mFairy.findPic(543, 13, 1067, 214, "ry.png");
                mFairy.onTap(0.8f, result, "跨服荣耀", 1000);

                result = mFairy.findPic(1060, 296, 1228, 582, "ry1.png");
                mFairy.onTap(0.8f, result, "无尽战场", 1000);


                result = mFairy.findPic(149, 402, 1120, 635, "act3.png");
                if (result.sim > 0.8f) {
                    mFairy.ranSwipe(941, 370, 289, 370, 500, 1000);
                }

                result = mFairy.findPic(533, 120, 823, 270, "ldz.png");
                if (result.sim > 0.8f) {
                    if (frequencyMap("qianwang", 3)) {
                        mFairy.onTap(641, 523, 691, 538, "前往", 1000);
                        setTaskName(2);
                        return;
                    }
                }


                timeCount(10, 0);

            }

            void content_02() throws Exception {
                int hour = mFairy.dateHour();
                if (hour == 21) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("xzml1.png");
                mFairy.onTap(0.8f, result, "前往", 1000);

                result = mFairy.findPic("xzml2.png");
                if (result.sim > 0.8f) {
                    err = 0;


                    result = mFairy.findPic("map2.png");
                    if (result.sim > 0.8f && judgeOneSecond()) {
                        switch (AtFairyConfig.getOption("battle_type")) {
                            case "1":
                                result = mFairy.findPic("battle_type1.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(957, 148, 968, 184, "行会模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "2":
                                result = mFairy.findPic("battle_type2.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(769, 151, 784, 186, "全体模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "3":
                                result = mFairy.findPic("battle_type3.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(832, 152, 849, 188, "和平模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "4":
                                result = mFairy.findPic("battle_type4.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(1019, 152, 1030, 183, "善恶模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                        }
                    }

                    gamePublicFuntion.autoBattle();

                    result = mFairy.findPic(954, 360, 1274, 685, "set1.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(1236,324,1252,339,"切换成战斗界面",1000);
                    }

                    if(AtFairyConfig.getOption("pug").equals("1")) {
                        for (int i = 0; i < 10; i++) {
                            mFairy.tap(1189, 594);
                        }

                    }
                }


                timeCount(20, 0);
            }


        };


    }//领地战

    public void slmj() throws Exception {
        new TaskContent(mFairy, "神龙秘境") {

            void init() throws Exception {

                judgeOneSecond = 0;
                gamePublicFuntion.close(1);
                if (gamePublicFuntion.mainScene()) {
                    setTaskName(1);
                    return;
                } else {
                    gamePublicFuntion.goHome();
                    Thread.sleep(2000);
                }

            }

            void inOperation() throws Exception {
                int hour = mFairy.dateHour();
                if (hour == 20) {
                    setTaskEnd();
                    return;
                }
            }

            void content_01() throws Exception {
                result = mFairy.findPic("slzy1.png");
                if (result.sim > 0.8f) {
                    frequencyInit("zhuang");
                    err = 0;
                    result = mFairy.findPic(396, 142, 784, 449, "slzy2.png");
                    mFairy.onTap(0.65f, result, "神龙秘境", 3000);

                } else {

                    if (frequencyMap("zhuang", 3)) {
                        gamePublicFuntion.seekNPC(518, 263, 519, 264, "ma5.png");
                    }
                }

                result = mFairy.findPic(735, 68, 951, 238, "slzy3.png");
                if (result.sim > 0.8f) {

                    switch (AtFairyConfig.getOption("slmj")) {
                        case "1":
                            mFairy.onTap(200, 145, 208, 175, "一层", 1000);
                            break;
                        case "2":
                            mFairy.onTap(194, 239, 209, 273, "二层", 1000);
                            break;
                        case "3":
                            mFairy.onTap(195, 338, 214, 382, "三层", 1000);
                            break;
                        case "4":
                            mFairy.onTap(195, 436, 209, 468, "四层", 1000);
                            break;
                        case "5":
                            mFairy.onTap(200, 547, 209, 579, "五层", 1000);
                            break;

                    }

                    mFairy.onTap(825, 551, 875, 564, "传送", 3000);
                    setTaskName(2);
                    return;
                }


                timeCount(10, 0);

            }

            void content_02() throws Exception {


                result = mFairy.findPic(1055, 14, 1279, 87, "slzy4.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("map2.png");
                    if (result.sim > 0.8f && judgeOneSecond()) {
                        switch (AtFairyConfig.getOption("battle_type")) {
                            case "1":
                                result = mFairy.findPic("battle_type1.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(957, 148, 968, 184, "行会模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "2":
                                result = mFairy.findPic("battle_type2.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(769, 151, 784, 186, "全体模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "3":
                                result = mFairy.findPic("battle_type3.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(832, 152, 849, 188, "和平模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                            case "4":
                                result = mFairy.findPic("battle_type4.png");
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1092, 146, 1103, 182, "", 2000);
                                    mFairy.onTap(1019, 152, 1030, 183, "善恶模式", 1000);
                                } else {
                                    judgeOneSecond = 1;
                                }
                                break;
                        }
                    }


                    gamePublicFuntion.autoBattle();

                    if(AtFairyConfig.getOption("pug").equals("1")) {
                        for (int i = 0; i < 10; i++) {
                            mFairy.tap(1189, 594);
                        }

                    }
                }


                if (gamePublicFuntion.lmiMainScenes()) {

                    SWITCH_USEROPERATION = false;
                    SWITCH_XUEOPERATION = false;
                    LtLog.e(mFairy.getLineInfo("【主城】"));

                    LtLog.e(mFairy.getLineInfo("等待时间：" + AtFairyConfig.getOption("home_time")));
                    Thread.sleep(getTimeStamp(AtFairyConfig.getOption("home_time")));//设置多少秒
                    setTaskName(0);
                    return;

                }


                timeCount(20, 0);
            }


        };
    }//神龙秘境


}