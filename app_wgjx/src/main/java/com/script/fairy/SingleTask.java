package com.script.fairy;

//import com.example.Answer;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SingleTask {
    public GamePublicFuntion gamePublicFuntion;
    public AtFairyImpl mFairy;
    private FindResult result;
    //private Answer answer;
    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        //answer = new Answer(ypFairy);
        hyJudge = true;
        LM = true;
    }

    abstract class sing extends TaskContent {

        public sing(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);

        }

        void inOperation() throws Exception {

            gamePublicFuntion.yanzheng();

            result = mFairy.findPic("chat.png");
            mFairy.onTap(0.85f, result, "聊天", 1000);

            result = mFairy.findPic("buzu.png");
            if (result.sim > 0.8f) {
                err = 0;

                if (AtFairyConfig.getOption("buzu").equals("1")) {
                    result = mFairy.findPic("buzu4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "获取更多", 1000);
                        sdCount--;
                    } else {

                        if (getName().equals("挂机")) {
                            LtLog.e(mFairy.getLineInfo("正在执行神秘商店,资源不足不退出任务"));
                            mFairy.onTap(1001, 127, 1016, 147, "", 500);
                            return;
                        }

                        if (getName().equals("商店")) {
                            LtLog.e(mFairy.getLineInfo("正在执行神秘商店,资源不足不退出任务"));
                            mFairy.onTap(1001, 127, 1016, 147, "", 500);
                            return;
                        }

                        if (getName().equals("建造")) {
                            jzTime = System.currentTimeMillis() + 600000;
                        }

                        mFairy.onTap(1001, 127, 1016, 147, "", 500);
                        LtLog.e(mFairy.getLineInfo("发现补充资源，但没有获取更多"));
                        setTaskEnd();
                        return;
                    }

                } else {

                    if (getName().equals("挂机")) {
                        LtLog.e(mFairy.getLineInfo("正在执行神秘商店,资源不足不退出任务"));
                        mFairy.onTap(1001, 127, 1016, 147, "", 500);
                        return;
                    }

                    if (getName().equals("商店")) {
                        LtLog.e(mFairy.getLineInfo("正在执行神秘商店,资源不足不退出任务"));
                        mFairy.onTap(1001, 127, 1016, 147, "", 500);
                        return;
                    }

                    if (getName().equals("建造")) {
                        jzTime = System.currentTimeMillis() + 600000;
                    }

                    mFairy.onTap(1001, 127, 1016, 147, "", 500);
                    setTaskEnd();
                }

                return;
            }

            result = mFairy.findPic("buzu1.png");
            if (result.sim > 0.8f) {
                err = 0;
                result = mFairy.findPic(866, 137, 1105, 655, "buzu2.png");
                if (result.sim > 0.8f) {
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(500);
                        result = mFairy.findPic(866, 137, 1105, 655, "buzu2.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "使用", 1000);

                            result = mFairy.findPic(712, 133, 827, 660, "buzu3.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "快捷使用", 1000);
                                mFairy.onTap(1079, 76, 1099, 90, "", 500);
                                return;
                            }
                        }
                    }

                    mFairy.onTap(1079, 76, 1099, 90, "", 500);

                } else {
                    mFairy.onTap(1082, 74, 1099, 91, "", 500);
                    setTaskEnd();
                }

                return;
            }

            FindResult quxiao = mFairy.findPic(686, 409, 935, 636, new String[]{"quxiao1.png", "quxiao2.png"});
            if (quxiao.sim > 0.8f) {
                result = mFairy.findPic("fangqi.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(450, 492, 498, 518, "确定放弃当前挑战", 1000);
                    return;
                }

                if (quxiao()) {
                    return;
                }

                if (frequencyMap("quxiao", 1)) {
                    mFairy.onTap(0.8f, quxiao, "否", 1000);
                }

            } else {
                frequencyInit("quxiao");
            }

            result = mFairy.findPic(new String[]{"home.png", "home1.png"});
            if (result.sim > 0.8f) {

                if (AtFairyConfig.getOption("lmbz").equals("1")) {//联盟帮助
                    lmbz();
                }

                if (AtFairyConfig.getOption("zhao").equals("1") || AtFairyConfig.getOption("zhao1").equals("1")) {
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(100);
                        result = mFairy.findPic(1114, 401, 1273, 604, new String[]{"beiBattle.png", "beiBattle1.png"});
                        if (result.sim > 0.7f) {
                            if (System.currentTimeMillis() - jiance_time > 600000) {
                                beBattle();
                                jiance_time = System.currentTimeMillis();
                                setTaskName(0);
                                return;
                            } else {
                                LtLog.e(mFairy.getLineInfo("没有达到时间!"));
                            }
                        }
                    }
                }
            }
        }

        boolean quxiao() throws Exception {

            return false;
        }//取消
    }

    public static boolean LM = true;
    public static boolean zy1_switch = true;
    public static boolean zy2_switch = true;
    public static boolean zy3_switch = true;
    public static boolean zy4_switch = true;
    public static boolean restTimeBool = true;
    public static boolean hyJudge = true;

    public long jiance_time = 0;
    public long zhao_time = 0;
    public boolean ymr_bool = true;
    public boolean resetPositionRest = true;
    public int ymr_count = 0;
    public int sdCount = 0;

    public void guaJi() throws Exception {
        new sing(mFairy, "挂机") {

            void create() throws Exception {
                onceClear();
                resetPositionRest = true;
                ymr_bool = true;
                ymr_count = 0;
                LM = true;
                hyJudge = true;
                mPosition.put("1", new Integer[]{-11, -11});
                mPosition.put("2", new Integer[]{-11, -11});
                mPosition.put("3", new Integer[]{-11, -11});
                mPosition.put("4", new Integer[]{-11, -11});
            }

            void init() throws Exception {
                setTaskName(1);
            }

            void content_01() throws Exception {

                if (mFairy.dateHour() == 24 || mFairy.dateHour() == 0) {
                    if (restTimeBool) {
                        restTimeBool = false;
                        mFairy.killUserGame();
                        create();
                        setTaskName(0);
                        return;
                    }
                } else {
                    restTimeBool = true;
                }

                if (resetPositionRest) {
                    gamePublicFuntion.init(1);
                    resetPositionRest = false;
                } else {
                    gamePublicFuntion.init(3);

                    result = mFairy.findPic("home1.png");
                    mFairy.onTap(0.8f, result, 49, 642, 68, 662, "回城", 1000);
                }

                judgeTask();
                /*if () {
                    return;
                }*/

                if (AtFairyConfig.getOption("szy").equals("1")) {//收资源
                    szy();
                }

                if (AtFairyConfig.getOption("zl").equals("1")) {//治疗
                    zl();
                }

                shou();

                result = mFairy.findPic(933, 371, 1271, 653, "huo.png");
                if (result.sim > 0.8f) {
                    resetPositionRest = true;
                    if (timeMap("huo", 1800000, true)) {
                        mFairy.onTap(0.8f, result, "火", 2000);

                        mFairy.onTap(911, 538, 949, 557, "维修", 1000);
                        mFairy.onTap(1079, 70, 1096, 89, "", 1000);
                    }
                }

                if (AtFairyConfig.getOption("ling_zx").equals("1") || AtFairyConfig.getOption("ling_hy").equals("1")) {//活跃宝箱
                    result = mFairy.findPic("huoyue1.png");
                    if (result.sim > 0.9f) {
                        huoyue();
                    }
                }

                if (AtFairyConfig.getOption("vip").equals("1")) {//领vip奖励
                    if (timeMap("vip_time", 7800000, true)) {
                        ling_vip();
                    }
                }

                if (AtFairyConfig.getOption("mrth").equals("1") && onceJudge("mrth")) {//每日特惠
                    mrth();
                }

                if (AtFairyConfig.getOption("yz").equals("1") && onceJudge("yz")) {//远征领取
                    yz();
                }

                if (AtFairyConfig.getOption("email").equals("1") && onceJudge("email")) {//email领取
                    email();
                }

                if (AtFairyConfig.getOption("lmlw").equals("1") && LM) {//联盟礼物
                    if (timeMap("lmlw_time", 600000, true)) {
                        lmbx();
                    }
                }

                if (AtFairyConfig.getOption("lmjx").equals("1") && LM) {//联盟捐献
                    if (timeMap("lmjx_time", 600000, true)) {
                        lmjx();
                    }
                }

                if (AtFairyConfig.getOption("jijie").equals("1") && LM) {//集结派兵
                    if (timeMap("jijie_time", 300000, true)) {
                        jiJie(false);
                    }
                }

                if (AtFairyConfig.getOption("tong").equals("1") && onceJudge("tong")) {//使用5次经验书
                    gradetl();
                }

                if (AtFairyConfig.getOption("rlxg").equals("1") && onceJudge("rlxg")) {//日落峡谷
                    rlxg();
                }

                if (AtFairyConfig.getOption("7772").equals("1")) {//城市增益
                    if (timeMap("cpro", 7200000, true)) {
                        cpro();
                    }
                }

                /**
                 * 战斗 */
                if (AtFairyConfig.getOption("7724").equals("1")) {
                    if (ymr_bool) {
                        gamePublicFuntion.init(3);
                        Thread.sleep(1000);

                        if (!AtFairyConfig.getOption("ymr_ydzs").equals("1")) {
                            FindResult fi1 = mFairy.findPic(1122, 124, 1221, 231, "caiji1.png");
                            FindResult fi2 = mFairy.findPic(1219, 139, 1261, 457, "zhuzha.png");
                            if (fi1.sim > 0.85f && fi2.sim < 0.8f) {
                                Mat mat = mFairy.getScreenMat(1230, 161, 7, 15, 1, 0, 0, 1);
                                Mat mat1 = mFairy.getScreenMat(1197, 139, 30, 51, 1, 0, 0, 1);
                                result = mFairy.matchMat(0, 0, mat, mat1);
                                LtLog.e(mFairy.getLineInfo("相似度为：" + result.sim));
                                if (result.sim > 0.9f) {

                                    if (hyJudge) {
                                        hyJudge = false;

                                        mFairy.ranSwipe(1222, 217, 1222, 440, 500, 1000);

                                        result = mFairy.findPic(1219, 139, 1261, 457, "zhuzha.png");
                                        if (result.sim > 0.8f) {
                                            huiying();
                                            gamePublicFuntion.init(3);
                                        }

                                        mFairy.ranSwipe(1224, 239, 1224, 20, 500, 1000);

                                        result = mFairy.findPic(1219, 139, 1261, 457, "zhuzha.png");
                                        if (result.sim > 0.8f) {
                                            huiying();
                                            gamePublicFuntion.init(3);
                                        }

                                        return;
                                    }

                                    LtLog.e(mFairy.getLineInfo("已经派出所有 的部队,end!"));
                                    return;
                                }
                            }
                        }
                        daye_ymr();
                    }
                } else {
                    ymr_bool = false;
                }

                if (AtFairyConfig.getOption("7748").equals("1")) {

                    int low = getNumberAssembly(AtFairyConfig.getOption("low_grade")) == -1 ? 1 : getNumberAssembly(AtFairyConfig.getOption("low_grade"));
                    int high = getNumberAssembly(AtFairyConfig.getOption("high_grade")) == -1 ? 0 : getNumberAssembly(AtFairyConfig.getOption("high_grade"));

                    int ymt = !AtFairyConfig.getOption("ymt").equals("") ? Integer.parseInt(AtFairyConfig.getOption("ymt")) : 0;
                    int mct = !AtFairyConfig.getOption("mct").equals("") ? Integer.parseInt(AtFairyConfig.getOption("mct")) : 0;
                    int skt = !AtFairyConfig.getOption("skt").equals("") ? Integer.parseInt(AtFairyConfig.getOption("skt")) : 0;
                    int jkt = !AtFairyConfig.getOption("jkt").equals("") ? Integer.parseInt(AtFairyConfig.getOption("jkt")) : 0;

                    if (zy1_switch == false && zy2_switch == false && zy3_switch == false && zy4_switch == false) {
                        zy1_switch = true;
                        zy2_switch = true;
                        zy3_switch = true;
                        zy4_switch = true;
                    }

                    control_zy:
                    while (mFairy.condit()) {

                        gamePublicFuntion.init(3);
                        Thread.sleep(1000);

                        if (ymr_bool == false) {
                            result = mFairy.findPic(1219, 139, 1261, 457, "zhuzha.png");
                            if (result.sim > 0.8f) {
                                huiying();
                                gamePublicFuntion.init(3);
                            }
                        }

                        FindResult fi1 = mFairy.findPic(1122, 124, 1221, 231, "caiji1.png");
                        if (fi1.sim > 0.85f) {
                            Mat mat = mFairy.getScreenMat(1230, 161, 7, 15, 1, 0, 0, 1);
                            Mat mat1 = mFairy.getScreenMat(1197, 139, 30, 51, 1, 0, 0, 1);
                            result = mFairy.matchMat(0, 0, mat, mat1);
                            LtLog.e(mFairy.getLineInfo("相似度为：" + result.sim));
                            if (result.sim > 0.9f) {

                                if (hyJudge) {
                                    hyJudge = false;

                                    mFairy.ranSwipe(1222, 217, 1222, 440, 500, 1000);

                                    result = mFairy.findPic(1219, 139, 1261, 457, "zhuzha.png");
                                    if (result.sim > 0.8f) {
                                        huiying();
                                        gamePublicFuntion.init(3);
                                    }

                                    mFairy.ranSwipe(1224, 239, 1224, 20, 500, 1000);

                                    result = mFairy.findPic(1219, 139, 1261, 457, "zhuzha.png");
                                    if (result.sim > 0.8f) {
                                        huiying();
                                        gamePublicFuntion.init(3);
                                    }
                                    return;
                                }

                                LtLog.e(mFairy.getLineInfo("已经派出所有 的部队,end!"));
                                return;
                            }
                        }

                        if (AtFairyConfig.getOption("lmk").equals("1") && LM) {//联盟矿
                            if (timeMap("lmk", 300000, true)) {
                                lmKuang();
                                continue control_zy;
                            }
                        }

                        if (AtFairyConfig.getOption("zy1").equals("1")) {
                            if (zy1_switch) {
                                if (daye_cj(1, low, high, ymt)) {
                                    zy1_switch = false;
                                    continue control_zy;
                                } else {
                                    break control_zy;
                                }
                            }
                        } else {
                            zy1_switch = false;
                        }

                        if (AtFairyConfig.getOption("zy2").equals("1")) {
                            if (zy2_switch) {
                                if (daye_cj(2, low, high, mct)) {
                                    zy2_switch = false;
                                    continue control_zy;
                                } else {
                                    break control_zy;
                                }
                            }
                        } else {
                            zy2_switch = false;
                        }


                        if (AtFairyConfig.getOption("zy3").equals("1")) {
                            if (zy3_switch) {
                                if (daye_cj(3, low, high, skt)) {
                                    zy3_switch = false;
                                    continue control_zy;
                                } else {
                                    break control_zy;
                                }
                            }
                        } else {
                            zy3_switch = false;
                        }

                        if (AtFairyConfig.getOption("zy4").equals("1")) {
                            if (zy4_switch) {
                                if (daye_cj(4, low, high, jkt)) {
                                    zy4_switch = false;
                                    continue control_zy;
                                } else {
                                    break control_zy;
                                }
                            }
                        } else {
                            zy4_switch = false;
                        }

                        break control_zy;
                    }
                }
            }

            void cpro() throws Exception {

                if (AtFairyConfig.getOption("ait1").equals("1")) {//攻击强化
                    cityPropUse("ai1.png");

                } else {
                    if (AtFairyConfig.getOption("ait2").equals("1")) {//防御强化
                        cityPropUse("ai2.png");
                    }
                }
                if (AtFairyConfig.getOption("ait3").equals("1")) {//部队扩编
                    cityPropUse("ai3.png");
                }
                if (AtFairyConfig.getOption("ait4").equals("1")) {//采集强化
                    cityPropUse("ai4.png");
                }
                if (AtFairyConfig.getOption("ait5").equals("1")) {//玉米增产
                    cityPropUse("ai5.png");
                }
                if (AtFairyConfig.getOption("ait6").equals("1")) {//木材增产
                    cityPropUse("ai6.png");
                }
                if (AtFairyConfig.getOption("ait7").equals("1")) {//石矿增产
                    cityPropUse("ai7.png");
                }
                if (AtFairyConfig.getOption("ait8").equals("1")) {//金币增产
                    cityPropUse("ai8.png");
                }
            }
        };
    }//挂机

    public void set() throws Exception {
        new sing(mFairy, "设置") {

            void init() throws Exception {
                gamePublicFuntion.init(3);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("set2.png");
                if (result.sim > 0.8f) {
                    frequencyInit("cl");
                    err = 0;

                    result = mFairy.findPic(194, 69, 1076, 678, "set4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "通用设置", 1000);
                        return;
                    }

                    if (onceJudge("set_one")) {
                        mFairy.onTap(299, 218, 310, 235, "", 500);
                        mFairy.onTap(299, 402, 310, 416, "", 500);

                        for (int i = 0; i < 2; i++) {
                            mFairy.ranSwipe(910, 569, 910, 222, 500, 500);
                        }
                    }

                    result = mFairy.findPic(201, 94, 307, 693, "set5.png");
                    if (result.sim > 0.85f) {

                        result = mFairy.findPic(result.x + 650, result.y - 40, result.x + 920, result.y + 120, "set6.png");
                        mFairy.onTap(0.98f, result, result.x + 30, result.y + 15, result.x + 40, result.y + 20, "打开快捷帮助", 500);

                        mFairy.onTap(1108, 34, 1122, 53, "", 500);
                        mFairy.onTap(1106, 38, 1127, 53, "", 500);
                        mFairy.onTap(1082, 75, 1097, 89, "", 500);
                        setTaskEnd();
                        return;
                    } else {
                        mFairy.ranSwipe(910, 569, 910, 222, 500, 500);
                    }

                    if (frequencyMap("set_num", 5)) {
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("set1.png");
                if (result.sim > 0.8f) {
                    frequencyInit("cl");
                    result = mFairy.findPic(220, 455, 1086, 634, "set3.png");
                    mFairy.onTap(0.8f, result, "设置", 1000);

                    return;
                }

                if (frequencyMap("cl", 2)) {
                    mFairy.onTap(32, 18, 54, 39, "点击头像", 1000);
                }
            }

        };
    }//设置

    public void beBattle() throws Exception {
        new sing(mFairy, "检测被攻击:") {

            boolean beBattle1;
            boolean beBattle2;

            void create() throws Exception {
                setTaskName(1);

                beBattle1 = false;
                beBattle2 = false;

                if (AtFairyConfig.getOption("zhao").equals("1")) {
                    beBattle1 = true;
                }

                if (AtFairyConfig.getOption("zhao1").equals("1")) {
                    beBattle2 = true;
                }

            }

            void init() throws Exception {
                gamePublicFuntion.init(2);
                setTaskName(1);

                /*result = mFairy.findPic(226, 34, 649, 159, "zhao5.png");
                if (result.sim > 0.8f) {
                    zhao = true;
                }*/
            }

            void inOperation() throws Exception {
                gamePublicFuntion.yanzheng();
                result = mFairy.findPic("chat.png");
                mFairy.onTap(0.85f, result, "聊天", 1000);

                FindResult quxiao = mFairy.findPic(686, 409, 935, 636, new String[]{"quxiao1.png", "quxiao2.png"});
                if (quxiao.sim > 0.8f) {

                    result = mFairy.findPic("fangqi.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(450, 492, 498, 518, "确定放弃当前挑战", 1000);
                        return;
                    }

                    if (quxiao()) {
                        return;
                    }

                    if (frequencyMap("quxiao", 1)) {
                        mFairy.onTap(0.8f, quxiao, "否", 1000);
                    }
                } else {
                    frequencyInit("quxiao");
                }
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("beiBattle2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (modularLookup > 3) {
                        setTaskEnd();
                        return;
                    }

                    result = modularLookup(839, 174, 1007, 273, "be1.png");
                    if (result.sim > 0.85f && (System.currentTimeMillis() - zhao_time > 28800000) && beBattle1) {
                        modularLookup++;
                        mFairy.onTap(1080, 75, 1098, 90, "城镇被攻击", 1000);
                        zhao();
                        setTaskName(0);
                        zhao_time = System.currentTimeMillis();
                        return;
                    }

                    result = modularLookup(839, 174, 1007, 273, "be2.png");
                    if (result.sim > 0.85f && beBattle2) {
                        mFairy.onTap(0.8f, result, "资源点被进攻", 5000);

                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(500);
                            result = mFairy.findPic(474, 233, 800, 475, "be3.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, result.x, result.y + 25, result.x + 1, result.y + 35, "点击自己得资源点", 500);
                                break;
                            }
                        }
                        return;
                    }

                    if (frequencyMap("bei_err", 1)) {
                        modularLookup++;
                        return;
                    }
                }

                result = mFairy.findPic(327, 101, 1144, 587, "be4.png");
                if (result.sim > 0.8f) {
                    modularLookup++;
                    mFairy.onTap(0.8f, result, "召回", 1000);
                } else {
                    result = mFairy.findPic(1006, 363, 1274, 712, new String[]{"beiBattle.png", "beiBattle1.png"});
                    mFairy.onTap(0.8f, result, "被攻击提醒", 1000);
                }
            }
        };

    }//检测被攻击

    long dzTime = System.currentTimeMillis() - 3600000;
    long jzTime = System.currentTimeMillis() - 60000;
    boolean yjBool = true;

    public void judgeTask() throws Exception {
        shoubing();
        shoukeji();

        ImageJudgeThread.TASK = "";
        ImageJudgeThread.EXIT = false;
        int countNum = 0;

        //训兵
        if (AtFairyConfig.getOption("7752").equals("1")) {
            countNum++;
            new Thread(new ImageJudgeThread() {
                void threadMethod() throws Exception {
                    result = mFairy.findPic(2, 45, 1278, 638, new String[]{"z1.png", "z2.png"});
                    LtLog.e(mFairy.getLineInfo("【ZZZ：" + result.sim + "】"));
                    if (result.sim > 0.75f) {
                        synchronized (this) {
                            ImageJudgeThread.TASK = "zaobing";
                            Thread.sleep(3000);
                        }
                    }
                }
            }).start();
        }

        // 研究
        if (AtFairyConfig.getOption("7762").equals("1") && yjBool) {
            countNum++;
            new Thread(new ImageJudgeThread() {
                void threadMethod() throws Exception {
                    result = mFairy.findPic(2, 45, 1278, 638, "yanjiu.png");
                    LtLog.e(mFairy.getLineInfo("【研究：" + result.sim + "】"));
                    if (result.sim > 0.7f) {
                        synchronized (this) {
                            ImageJudgeThread.TASK = "yanjiu";
                            Thread.sleep(3000);
                        }
                    }
                }
            }).start();
        }

        //建造
        if (AtFairyConfig.getOption("jianzao").equals("1") && ((System.currentTimeMillis() - jzTime) > 60000)) {
            countNum++;
            new Thread(new ImageJudgeThread() {
                void threadMethod() throws Exception {
                    result = mFairy.findPic(2, 45, 1278, 638, "jianzao1.png");
                    LtLog.e(mFairy.getLineInfo("【建造：" + result.sim + "】"));
                    if (result.sim > 0.7f) {
                        synchronized (this) {
                            ImageJudgeThread.TASK = "jianzao";
                            Thread.sleep(3000);
                        }
                    }
                }
            }).start();
        }


        //酒馆
        if (AtFairyConfig.getOption("7756").equals("1")) {
            countNum++;
            new Thread(new ImageJudgeThread() {
                void threadMethod() throws Exception {
                    result = mFairy.findPic(2, 45, 1278, 638, new String[]{"zhaomu.png", "zhaomu5.png"});
                    LtLog.e(mFairy.getLineInfo("【招募 ：" + result.sim + "】"));
                    if (result.sim > 0.7f) {
                        synchronized (this) {
                            ImageJudgeThread.TASK = "jiuguan";
                            Thread.sleep(3000);
                        }
                    }
                }
            }).start();
        }


        //商店
        if (AtFairyConfig.getOption("7770").equals("1")) {
            countNum++;
            new Thread(new ImageJudgeThread() {
                void threadMethod() throws Exception {
                    result = mFairy.findPic(2, 45, 1278, 638, "sd1.png");
                    LtLog.e(mFairy.getLineInfo("【黑女人 ：" + result.sim + "】"));
                    if (result.sim > 0.7f) {
                        synchronized (this) {
                            ImageJudgeThread.TASK = "shangdain";
                            Thread.sleep(3000);
                        }
                    }
                }
            }).start();
        }


        //答题
        if (AtFairyConfig.getOption("dt").equals("1")) {
            countNum++;
            new Thread(new ImageJudgeThread() {
                void threadMethod() throws Exception {
                    result = mFairy.findPic(2, 45, 1278, 638, "dt1.png");
                    LtLog.e(mFairy.getLineInfo("【答题 ：" + result.sim + "】"));
                    if (result.sim > 0.7f) {
                        synchronized (this) {
                            ImageJudgeThread.TASK = "dati";
                            Thread.sleep(3000);
                        }
                    }
                }
            }).start();
        }


        //探索
        if (AtFairyConfig.getOption("new_ts").equals("1")) {
            countNum++;
            new Thread(new ImageJudgeThread() {
                void threadMethod() throws Exception {
                    result = mFairy.findPic(2, 45, 1278, 638, "tansuo.png");
                    LtLog.e(mFairy.getLineInfo("【探索：" + result.sim + "】"));
                    if (result.sim > 0.7f) {
                        synchronized (this) {
                            ImageJudgeThread.TASK = "tansuo";
                            Thread.sleep(3000);
                        }
                    }
                }
            }).start();
        }

        //锻造
        if (AtFairyConfig.getOption("7784").equals("1") && ((System.currentTimeMillis() - dzTime) > 3600000)) {
            if (!(AtFairyConfig.getOption("sheng1").equals("") &&
                    AtFairyConfig.getOption("sheng2").equals("") &&
                    AtFairyConfig.getOption("sheng3").equals("") &&
                    AtFairyConfig.getOption("sheng4").equals(""))) {
                countNum++;
                new Thread(new ImageJudgeThread() {
                    void threadMethod() throws Exception {
                        result = mFairy.findPic(2, 45, 1278, 638, new String[]{"duanzao1.png", "duanzao5.png"});
                        LtLog.e(mFairy.getLineInfo("【锻造：" + result.sim + "】"));
                        if (result.sim > 0.7f) {
                            synchronized (this) {
                                ImageJudgeThread.TASK = "duanzao";
                                Thread.sleep(3000);
                            }
                        }
                    }
                }).start();
            }
        }

        if (countNum == 0) {
            return;
        }

        int countTime = 0;
        while (true) {
            switch (ImageJudgeThread.TASK) {
                case "zaobing":
                    resetPositionRest = true;
                    ImageJudgeThread.EXIT = true;
                    zb();
                    return;
                case "yanjiu":
                    resetPositionRest = true;
                    ImageJudgeThread.EXIT = true;
                    yj();
                    return;
                case "jianzao":
                    resetPositionRest = true;
                    ImageJudgeThread.EXIT = true;
                    jianzao();

                    return;
                case "jiuguan":
                    resetPositionRest = true;
                    ImageJudgeThread.EXIT = true;
                    jg();
                    return;
                case "shangdain":
                    resetPositionRest = true;
                    ImageJudgeThread.EXIT = true;
                    shandian();
                    return;
                case "dati":
                    resetPositionRest = true;
                    ImageJudgeThread.EXIT = true;
                    dati(false);
                    return;
                case "tansuo":
                    resetPositionRest = true;
                    ImageJudgeThread.EXIT = true;
                    new_ts();
                    return;
                case "duanzao":
                    resetPositionRest = true;
                    ImageJudgeThread.EXIT = true;
                    duanzao();
                    dzTime = System.currentTimeMillis();
                    return;
                default:
                    countTime++;
                    Thread.sleep(1000);
                    if (countTime > 5) {
                        LtLog.e(mFairy.getLineInfo("没有识别到任何建筑！"));
                        ImageJudgeThread.EXIT = true;
                        return;
                    }
                    break;
            }
        }
    }//判断

    public void use(final int type, final String name) throws Exception {
        new sing(mFairy, "使用道具:" + name) {

            void init() throws Exception {
                gamePublicFuntion.init(3);
                setTaskName(1);
            }

            void inOperation() throws Exception {
                gamePublicFuntion.yanzheng();
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(784, 619, 1194, 719, "package.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "道具", 1000);
                } else {
                    result = mFairy.findPic(new String[]{"lm2.png", "lm8.png"});
                    mFairy.onTap(0.8f, result, "缩放栏", 1000);
                }

                result = mFairy.findPic(new String[]{"package1.png", "package2.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    switch (type) {
                        case 1:
                            mFairy.onTap(231, 70, 268, 87, "资源", 1000);
                            break;
                        case 2:
                            mFairy.onTap(407, 74, 455, 90, "加速", 1000);
                            break;
                        case 3:
                            mFairy.onTap(594, 73, 627, 88, "增益", 1000);
                            break;
                        case 4:
                            mFairy.onTap(770, 72, 804, 89, "装备", 1000);
                            break;
                        case 5:
                            mFairy.onTap(959, 74, 985, 86, "其他", 1000);
                            break;
                    }

                    result = mFairy.findPic(161, 131, 825, 688, name);
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "点击道具", 2000);
                        mFairy.onTap(963, 592, 993, 604, "使用", 1000);
                        setTaskEnd();
                        return;
                    }

                    if (frequencyMap("slide", 3)) {
                        mFairy.ranSwipe(611, 595, 611, 174, 500, 1000);
                    }

                    if (frequencyMap("esc", 5)) {
                        mFairy.onTap(1102, 72, 1119, 93, "没有发现该道具", 1000);
                        setTaskEnd();
                        return;
                    }
                }
            }

        };
    }//使用道具

    public void ling_vip() throws Exception {
        new sing(mFairy, "vip") {

            void init() throws Exception {
                gamePublicFuntion.init(3);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("vip1.png");
                if (result.sim > 0.8f) {
                    frequencyInit("cl");
                    err = 0;

                    mFairy.onTap(1001, 161, 1025, 184, "宝箱", 1000);

                    result = mFairy.findPic("vip2.png");
                    mFairy.onTap(0.8f, result, "每日免费", 2000);

                    if (mFairy.findPic("vip2.png").sim < 0.8f && mFairy.findPic("vip1.png").sim > 0.8f) {
                        if (frequencyMap("vip", 1)) {
                            mFairy.onTap(1080, 75, 1097, 91, "", 500);
                            setTaskEnd();
                            return;
                        }
                    }
                    return;
                }

                result = mFairy.findPic("vip3.png");
                mFairy.onTap(0.8f, result, 871, 50, 884, 62, "特权升级", 1000);

                mFairy.onTap(130, 56, 164, 69, "点击vip", 1000);
            }

        };

    }//vip领取

    public void szy() throws Exception {

        result = mFairy.findPic("home.png");
        if (result.sim < 0.8f) {
            return;
        }

        Thread zy1 = new Thread() {
            public void run() {
                try {
                    result = mFairy.findPic(2, 80, 1278, 638, new String[]{"yumi.png"});
                    mFairy.onTap(0.8f, result, "收资源", 200);

                    result = mFairy.findPic(2, 80, 1278, 638, new String[]{"mucai.png"});
                    mFairy.onTap(0.8f, result, "收资源", 200);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread zy2 = new Thread() {
            public void run() {
                try {
                    result = mFairy.findPic(2, 80, 1278, 638, new String[]{"shitou.png"});
                    mFairy.onTap(0.8f, result, "收资源", 200);

                    result = mFairy.findPic(2, 80, 1278, 638, new String[]{"jinbi.png"});
                    mFairy.onTap(0.8f, result, "收资源", 200);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        zy1.start();
        zy2.start();

        zy1.join();
        zy2.join();
    }//收资源

    public void shou() throws Exception {
        result = mFairy.findPic("home.png");
        if (result.sim > 0.8f) {
            result = mFairy.findPic(2, 45, 1278, 638, "bangzhu1.png"/*"bangzhu2.png"*/);
            mFairy.onTap(0.8f, result, "手", 1000);
        }
    }//帮助手

    public void zl() throws Exception {
        shoubing();
        ImageJudgeThread.EXIT = false;

        Thread t1 = new Thread(new ImageJudgeThread() {
            void threadMethod() throws Exception {
                result = mFairy.findPic(2, 45, 1278, 638, "zhiliao.png");
                mFairy.onTap(0.8f, result, "【点击】", 1000);
            }
        });

        Thread t2 = new Thread(new ImageJudgeThread() {
            void threadMethod() throws Exception {
                result = mFairy.findPic(2, 45, 1278, 638, "zhiliao3.png");
                mFairy.onTap(0.8f, result, "【点击】", 1000);
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            Thread.sleep(300);

            result = mFairy.findPic("zhiliao1.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "治疗伤员", 500);
                break;
            }

            result = mFairy.findPic("zl2.png");
            if (result.sim > 0.8f) {
                ImageJudgeThread.EXIT = true;
                t1.join();
                t2.join();
                mFairy.onTap(0.8f, result, 1084, 72, 1101, 89, "资源不足", 1000);
                return;
            }
        }
        ImageJudgeThread.EXIT = true;
        t1.join();
        t2.join();
    }//治疗伤员

    public void shoubing() throws Exception {
        result = mFairy.findPic("home.png");
        if (result.sim > 0.8f) {
            for (int i = 0; i < 2; i++) {
                //result = mFairy.findPic(2, 45, 1278, 638, new String[]{"zhiliao2.png", "zhiliao4.png"});
                result = mFairy.findPic(2, 45, 1278, 638, new String[]{"shoubing1.png", "shoubing.png"});
                LtLog.e(mFairy.getLineInfo("【兵 ：" + result.sim + "】"));
                mFairy.onTap(0.71f, result, result.x + 25, result.y + 19, result.x + 30, result.y + 22, "【点击】", 2000);
            }
        }
    }

    public void shoukeji() throws Exception {
        result = mFairy.findPic("home.png");
        if (result.sim > 0.8f) {
            for (int i = 0; i < 2; i++) {
                result = mFairy.findPic(2, 45, 1180, 638, "yj2.png");
                LtLog.e(mFairy.getLineInfo("【收获：" + result.sim + "】"));
                mFairy.onTap(0.75f, result, result.x, result.y + 65, result.x + 1, result.y + 70, "【收获科技】", 1000);
            }
        }
    }

    public void huoyue() throws Exception {
        new sing(mFairy, "领取活跃") {

            void create() throws Exception {
                super.create();
                setTaskName(1);
            }

            void init() throws Exception {
                result = mFairy.findPic(new String[]{"home.png", "home1.png"});
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("huoyue1.png");
                    if (result.sim < 0.9f) {
                        setTaskEnd();
                        return;
                    }
                } else {
                    gamePublicFuntion.init(3);
                }
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("huoyue3.png");
                mFairy.onTap(0.8f, result, "活跃", 1000);

                result = mFairy.findPic("huoyue2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (!AtFairyConfig.getOption("ling_zx").equals("1")) {
                        result = mFairy.findPic(41, 241, 97, 307, "huoyue5.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(95, 305, 111, 331, "切换到活跃栏", 1000);
                        } else {
                            mFairy.onTap(1082, 77, 1102, 90, "", 500);
                            setTaskEnd();
                        }
                        return;
                    }


                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(200);
                        result = mFairy.findPic(912, 96, 1102, 600, "huoyue4.png");
                        if (result.sim > 0.8f) {
                            i = 0;
                            mFairy.onTap(0.8f, result, "领取奖励", 1000);
                        }
                    }

                    result = mFairy.findPic(41, 241, 97, 307, "huoyue5.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(95, 305, 111, 331, "切换到活跃栏", 1000);
                    } else {
                        mFairy.onTap(1082, 77, 1102, 90, "", 500);
                        setTaskEnd();
                    }
                    return;
                }

                result = mFairy.findPic("huoyue6.png");
                if (result.sim > 0.8f) {

                    if (!AtFairyConfig.getOption("ling_hy").equals("1")) {
                        mFairy.onTap(1082, 77, 1102, 90, "", 500);
                        setTaskEnd();
                        return;
                    }

                    err = 0;
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(300);
                        result = mFairy.findPic(912, 96, 1102, 600, "huoyue4.png");
                        if (result.sim > 0.8f) {
                            i = 0;
                            mFairy.onTap(0.8f, result, "领取奖励", 1000);
                        }
                    }

                    for (int i = 0; i < 2; i++) {
                        mFairy.onTap(360, 182, 381, 198, "活跃1", 500);
                        mFairy.onTap(515, 183, 532, 203, "活跃2", 500);
                        mFairy.onTap(698, 185, 715, 200, "活跃3", 500);
                        mFairy.onTap(868, 180, 890, 203, "活跃4", 500);
                        mFairy.onTap(1040, 190, 1055, 200, "活跃5", 500);
                    }
                    mFairy.onTap(1084, 75, 1097, 88, "关闭", 500);
                    setTaskName(0);
                    return;
                }
            }
        };

    }//活跃

    public void mrth() throws Exception {
        new sing(mFairy, "每日特惠") {

            void init() throws Exception {
                gamePublicFuntion.init(3);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(5, 99);

                result = mFairy.findPic(13, 79, 193, 709, "th1.png");
                mFairy.onTap(0.8f, result, "每日特惠", 1500);

                result = mFairy.findPic("th2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1188,145,1210,174, "每日宝箱", 1500);
                    mFairy.onTap(34, 29, 47, 42, "", 500);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("home.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(1019, 62, 1272, 159, "th3.png");
                    mFairy.onTap(0.8f, result, result.x - 70, result.y, result.x - 69, result.y + 1, "点击特惠", 1000);
                }
            }
        };
    }//每日特惠

    public void email() throws Exception {
        new sing(mFairy, "邮箱领取") {

            void init() throws Exception {
                gamePublicFuntion.init(3);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("email.png");
                mFairy.onTap(0.8f, result, "邮箱", 500);


                result = mFairy.findPic(1008, 17, 1185, 69, "emailScene.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    mFairy.onTap(131, 661, 149, 668, "一键领取", 1000);

                    result = mFairy.findPic(12, 8, 1036, 48, "email1.png");
                    if (result.sim > 0.85f) {
                        frequencyInit("email_ling");
                        mFairy.onTap(result.x - 50, result.y + 8, result.x - 40, result.y + 12, "点击红标", 1000);
                    } else {
                        if (frequencyMap("email_ling", 1)) {
                            mFairy.onTap(1224, 36, 1238, 52, "", 500);
                            setTaskEnd();
                            return;
                        }
                    }
                }
                result = mFairy.findPic("dinghao.png");
                if (result.sim > 0.8f) {
                    mFairy.finish(AtFairyConfig.getTaskID(), 99);
                } else {
                    result = mFairy.findPic(349, 407, 962, 666, "ok.png");
                    mFairy.onTap(0.8f, result, "确定", 1000);
                }
            }

        };
    }//邮箱领取

    public void yz() throws Exception {
        new sing(mFairy, "远征领奖") {

            void init() throws Exception {
                gamePublicFuntion.init(3);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(8, 0);

                result = mFairy.findPic(784, 619, 1194, 719, "yz1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "战役", 1000);
                } else {
                    result = mFairy.findPic(new String[]{"lm2.png", "lm8.png"});
                    mFairy.onTap(0.8f, result, "缩放栏", 1000);
                }

                result = mFairy.findPic("yz2.png");
                mFairy.onTap(0.8f, result, "远征", 1000);

                result = mFairy.findPic("yz3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(113, 214, 140, 242, "", 1500);

                    for (int i = 0; i < 3; i++) {
                        Thread.sleep(300);
                        result = mFairy.findPic("yz4.png");
                        mFairy.onTap(0.8f, result, "确定", 1000);
                    }
                    setTaskEnd();
                    return;
                }
            }
        };
    }//远征领奖

    public void test() throws Exception {
        new sing(mFairy, "Test") {
            long l1 = 0;

            void init() throws Exception {
                setTaskName(1);
            }

            void content_01() throws Exception {
                LtLog.e(mFairy.getLineInfo("开始" + (System.currentTimeMillis() - l1)));
                long l = System.currentTimeMillis();


                for (int i = 0; i < 5; i++) {
                    result = mFairy.findPic(2, 45, 1278, 638, new String[]{"z1.png", "z2.png"});
                    LtLog.e(mFairy.getLineInfo("zzz:" + result.sim));
                }

                LtLog.e(mFairy.getLineInfo("结束：" + (System.currentTimeMillis() - l)));
                l1 = System.currentTimeMillis();
            }
        };
    }

    /*
        Up Class */
    abstract class Up extends sing {

        public Up(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);
        }

        void init() throws Exception {
            gamePublicFuntion.init(3);
            setTaskName(1);
        }

        void inOperation() throws Exception {
            gamePublicFuntion.yanzheng();

            result = mFairy.findPic("chat.png");
            mFairy.onTap(0.85f, result, "聊天", 1000);

            FindResult quxiao = mFairy.findPic(686, 409, 935, 636, new String[]{"quxiao1.png", "quxiao2.png"});
            if (quxiao.sim > 0.8f) {

                result = mFairy.findPic("fangqi.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(450, 492, 498, 518, "确定放弃当前挑战", 1000);
                    return;
                }

                if (quxiao()) {
                    return;
                }

                if (frequencyMap("quxiao", 1)) {
                    mFairy.onTap(0.8f, quxiao, "否", 1000);
                }
            } else {
                frequencyInit("quxiao");
            }
        }

        void cityAdministration(String name) throws Exception {
            result = mFairy.findPic("buff2.png");
            if (result.sim > 0.8f) {
                err = 0;

                result = mFairy.findPic("zeng1.png");
                mFairy.onTap(0.85f, result, "返回", 1000);

                result = mFairy.findPic(302, 101, 450, 666, name);
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "【" + name + "】", 1000);
                    setTaskName(2);
                    return;
                } else {
                    if (frequencyMap("citySlide", 1)) {
                        mFairy.ranSwipe(614, 562, 614, 227, 500, 1000);
                    }

                    if (frequencyMap("cityEnd", 11)) {
                        setTaskName(0);
                        return;
                    }
                }
            } else {
                result = mFairy.findPic("buff1.png");
                mFairy.onTap(0.85f, result, "buff", 1000);
            }
        }
    }

    public void zhao() throws Exception {
        new Up(mFairy, "开罩") {

            void content_01() throws Exception {
                timeCount(10, 0);
                cityAdministration("zhao1.png");
            }

            void content_02() throws Exception {
                timeCount(5, 0);

                result = mFairy.findPic(317, 98, 559, 646, "zhao3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(886, 103, 1086, 668, "use1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "使用", 1000);
                        setTaskEnd();
                        return;
                    } else {
                        if (frequencyMap("upSlide", 1)) {

                            if (onceJudge("oneUp")) {
                                mFairy.ranSwipe(635, 180, 635, 543, 500, 500);
                            } else {
                                if (frequencyMap("errSlide", 1)) {

                                    if ((AtFairyConfig.getOption("gmZhao").equals("1"))
                                            /*|| (AtFairyConfig.getOption("task_id").equals("2468"))
                                            || (AtFairyConfig.getOption("task_id").equals("2474"))*/) {
                                        setTaskName(3);
                                        return;
                                    } else {
                                        setTaskEnd();
                                        return;
                                    }
                                }
                                mFairy.ranSwipe(635, 543, 635, 180, 500, 500);
                            }
                        }
                    }
                }
            }

            void content_03() throws Exception {
                timeCount(5, 0);

                result = mFairy.findPic(317, 98, 559, 646, "zhao3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(258, 85, 462, 248, "zhao2.png");
                    if (result.sim > 0.8f) {

                        mFairy.onTap(961, 176, 1001, 197, "购买护罩", 2000);

                        result = mFairy.findPic(268, 229, 614, 436, "zhao4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(799, 503, 820, 514, "已经激活该道具了", 1000);
                        } else {
                            mFairy.onTap(455, 505, 485, 521, "确定购买", 1000);
                        }
                        setTaskEnd();
                        return;
                    } else {
                        if (frequencyMap("upSlide", 1)) {
                            mFairy.ranSwipe(635, 180, 635, 543, 500, 500);
                            LtLog.e(mFairy.getLineInfo("没有找到8小时护罩,滑"));
                        }
                    }
                }
            }
        };
    }//开罩

    public void cityPropUse(final String str) throws Exception {
        new Up(mFairy, "城市增益：" + str) {

            void content_01() throws Exception {
                timeCount(10, 0);
                cityAdministration(str);
            }

            void content_02() throws Exception {
                timeCount(5, 0);

                result = mFairy.findPic("buff2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(886, 103, 1086, 668, "use1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "使用", 2000);

                        result = mFairy.findPic(268, 229, 614, 436, "zhao4.png");
                        mFairy.onTap(0.8f, result, 799, 503, 820, 514, "已经激活该道具了", 1000);

                        setTaskEnd();
                        return;
                    } else {
                        if (frequencyMap("upSlide", 1)) {
                            setTaskEnd();
                            return;
                        }
                    }
                }
            }
        };
    }//城市增益

    public void rlxg() throws Exception {
        new sing(mFairy, "日落峡谷") {

            void init() throws Exception {
                gamePublicFuntion.init(3);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(784, 619, 1194, 719, "yz1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "战役", 1000);
                } else {
                    result = mFairy.findPic(new String[]{"lm2.png", "lm8.png"});
                    mFairy.onTap(0.8f, result, "缩放栏", 1000);
                }

                result = mFairy.findPic("rlxg1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "日落峡谷", 5000);

                    result = mFairy.findPic("rlxg1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(30, 22, 54, 53, "", 500);
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("rlxg3.png");
                mFairy.onTap(0.8f, result, "挑战", 1000);

                result = mFairy.findPic("rlxg6.png");
                mFairy.onTap(0.8f, result, "准备", 1000);

                result = mFairy.findPic(594, 68, 969, 135, "rlxg11.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(892, 197, 971, 640, "rlxg19.png");
                    if (result.sim > 0.92f) {
                        mFairy.onTap(1101, 29, 1117, 44, "", 500);
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(831, 424, 1050, 631, "rlxg10.png");
                    mFairy.onTap(0.8f, result, "选择目标", 2000);
                }

                result = mFairy.findPic(427, 221, 646, 301, "rlxg8.png");
                mFairy.onTap(0.8f, result, 600, 650, 674, 665, "点击屏幕继续", 1000);

                result = mFairy.findPic(new String[]{"rlxg4.png", "rlxg14.png"});
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("rlxg20.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "跳过战斗", 2000);
                        return;
                    }

                    result = mFairy.findPic(140, 87, 504, 610, new String[]{"rlxg5.png", "rlxg15.png", "rlxg16.png", "rlxg17.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "挑战", 1000);
                    } else {
                        result = mFairy.findPic("rlxg7.png");
                        mFairy.onTap(0.8f, result, "准备", 1000);

                        mFairy.onTap(619, 644, 661, 660, "确定", 3000);
                    }
                }

            }
        };
    }//日落峡谷

    public void lmbz() throws Exception {
        result = mFairy.findPic(997, 347, 1276, 713, "lmbz.png");
        mFairy.onTap(0.8f, result, "联盟帮助", 1000);

    }//联盟帮助

    public void lmbx() throws Exception {
        new sing(mFairy, "联盟宝箱") {

            void init() throws Exception {
                result = mFairy.findPic("lm3.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.init(3);
                }
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(784, 619, 1194, 719, "lm1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "联盟", 1000);
                } else {
                    result = mFairy.findPic(new String[]{"lm2.png", "lm8.png"});
                    mFairy.onTap(0.8f, result, "缩放栏", 1000);
                }

                result = mFairy.findPic("lm5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1108, 37, 1121, 50, "未加入联盟", 500);
                    LM = false;
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("lm3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    for (int i = 0; i < 2; i++) {
                        Thread.sleep(500);
                        result = mFairy.findPic("lm6.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(869, 548, 901, 580, "礼物", 1000);
                            return;
                        }
                    }

                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("lm4.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    result = mFairy.findPic(703, 162, 1095, 214, "lmlw1.png");
                    if (result.sim > 0.8f) {
                        frequencyInit("hongdian");
                        mFairy.onTap(result.x - 80, result.y + 12, result.x - 70, result.y + 15, "发现红点", 1500);

                        result = mFairy.findPic("lmlw3.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "一键领取", 2000);
                        } else {
                            for (int i = 0; i < 2; i++) {
                                Thread.sleep(300);
                                result = mFairy.findPic(909, 181, 1042, 687, "lmlw4.png");
                                if (result.sim > 0.8f) {
                                    i = 0;
                                    mFairy.onTap(0.8f, result, "领取", 1000);
                                }
                            }
                        }
                    } else {
                        if (frequencyMap("hongdian", 2)) {
                            mFairy.onTap(1103, 30, 1119, 49, "", 500);
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic("dinghao.png");
                if (result.sim > 0.8f) {
                    mFairy.finish(AtFairyConfig.getTaskID(), 99);
                } else {
                    result = mFairy.findPic(364, 379, 762, 682, "lmlw2.png");
                    mFairy.onTap(0.8f, result, "ok", 1000);
                }
            }

        };
    }//联盟宝箱

    public void jiJie(final boolean bool) throws Exception {
        new sing(mFairy, "联盟集结") {

            void init() throws Exception {
                result = mFairy.findPic("lm3.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.init(3);
                }
                setTaskName(1);
            }

            boolean quxiao() throws Exception {
                result = mFairy.findPic("jijie1.png");
                if (result.sim > 0.85) {
                    mFairy.onTap(464, 498, 487, 520, "", 1000);
                    return true;
                }
                return false;
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(784, 619, 1194, 719, "lm1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "联盟", 1000);
                } else {
                    result = mFairy.findPic(new String[]{"lm2.png", "lm8.png"});
                    mFairy.onTap(0.8f, result, "缩放栏", 1000);
                }

                result = mFairy.findPic("lm5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1108, 37, 1121, 50, "未加入联盟", 500);
                    LM = false;
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("lm3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    for (int i = 0; i < 2; i++) {
                        Thread.sleep(300);
                        result = mFairy.findPic("jijie2.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(608, 392, 621, 407, "领土", 1000);
                            return;
                        }
                    }
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("jijie3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    result = mFairy.findPic("jijie4.png");
                    if (result.sim > 0.85f) {
                        frequencyInit("jijie");
                        mFairy.onTap(0.8f, result, "加入", 1000);
                        return;
                    }

                    if (frequencyMap("jijie", 2)) {
                        mFairy.onTap(1106, 39, 1122, 53, "", 1000);
                        mFairy.onTap(1106, 39, 1122, 53, "", 500);
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("battle9.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("派遣界面"));
                    Thread.sleep(500);
                    err = 0;

                    result = mFairy.findPic(1216, 85, 1258, 661, new String[]{"baty1.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "点击休息的部队", 2500);

                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(200);
                            FindResult xing = mFairy.findPic(888, 6, 1135, 704, "battle6.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, xing, "行军", 1000);
                                break;
                            }
                        }

                        setTaskName(2);
                        return;
                    } else {
                        for (int i = 0; i < 2; i++) {
                            Thread.sleep(300);
                            result = mFairy.findPic("battle2.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "创建部队", 1000);
                                return;
                            }
                        }

                        mFairy.onTap(42, 639, 69, 665, "init_err", 500);
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("battle3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, 903, 611, 956, 639, "行军", 2000);
                    setTaskName(2);
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(5, 99);

                result = mFairy.findPic("battle8.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(899, 169, 1052, 647, "battle7.png");
                    if (result.sim > 0.8f) {
                        if (AtFairyConfig.getOption("ymr_yao").equals("1") || bool) {
                            mFairy.onTap(0.8f, result, "使用", 500);
                        } else {
                            mFairy.onTap(1082, 70, 1096, 92, "", 500);
                            setTaskEnd();
                            return;
                        }
                    } else {
                        mFairy.onTap(1082, 70, 1096, 92, "", 500);
                        setTaskEnd();
                        return;
                    }
                    mFairy.onTap(1082, 70, 1096, 92, "", 2000);
                    setTaskName(1);
                    return;
                }
            }
        };
    }//联盟集结

    public void lmjx() throws Exception {
        new sing(mFairy, "联盟捐献") {

            int lmjx = 0;

            void init() throws Exception {
                result = mFairy.findPic("lm3.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.init(3);
                }
                lmjx = 0;
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(784, 619, 1194, 719, "lm1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "联盟", 1000);
                } else {
                    result = mFairy.findPic(new String[]{"lm2.png", "lm8.png"});
                    mFairy.onTap(0.8f, result, "缩放栏", 1000);
                }

                result = mFairy.findPic("lm5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1108, 37, 1121, 50, "未加入联盟", 500);
                    LM = false;
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("lm3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    for (int i = 0; i < 2; i++) {
                        Thread.sleep(500);
                        result = mFairy.findPic("lm7.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(740, 543, 763, 565, "联盟捐献", 1000);
                            return;
                        }
                    }

                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(new String[]{"lmjx1.png", "lmjx8.png"});
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(137, 101, 1144, 694, "lmjx2.png");
                    if (result.sim > 0.85f && lmjx == 0) {
                        frequencyInit("lmjx");
                        mFairy.onTap(result.x, result.y + 70, result.x + 1, result.y + 75, "官员推荐", 2000);
                        lmjx++;
                        return;
                    } else {
                        lmjx++;
                        if (lmjx > 4) {
                            mFairy.onTap(1107, 64, 1122, 77, "", 1000);
                            setTaskEnd();
                            return;
                        }

                        switch (lmjx) {
                            case 1:
                                mFairy.onTap(65, 167, 88, 189, "发展", 2000);
                                break;
                            case 2:
                                mFairy.onTap(76, 294, 97, 321, "领土", 2000);
                                break;
                            case 3:
                                mFairy.onTap(74, 419, 93, 442, "战争", 2000);
                                break;
                            case 4:
                                mFairy.onTap(78, 541, 92, 564, "联盟技能", 2000);
                                break;
                        }

                        result = mFairy.findPic(137, 101, 1144, 694, "lmjx3.png");
                        if (result.sim > 0.95f) {
                            frequencyInit("lmjx");
                            mFairy.onTap(0.95f, result, "发现科技", 2000);
                            return;
                        }
                    }
                }

                result = mFairy.findPic("lmjx7.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("捐献界面"));
                    result = mFairy.findPic("lmjx6.png");
                    if (result.sim > 0.85f) {
                        frequencyInit("jx");

                        for (int i = 0; i < 5; i++) {
                            mFairy.onTap(961, 553, 962, 554, "", 200);
                        }
                        result = mFairy.findPic("lmjx5.png");
                        if (result.sim > 0.92f) {
                            mFairy.onTap(1081, 75, 1097, 89, "", 500);
                            mFairy.onTap(1114, 65, 1123, 73, "", 500);
                            setTaskEnd();
                            return;
                        }
                    } else {
                        if (frequencyMap("jx", 2)) {
                            mFairy.onTap(1084, 73, 1100, 92, "", 1000);
                            return;
                        }
                    }
                }
            }

        };
    }//联盟捐献

    public void jg() throws Exception {
        new sing(mFairy, "酒馆") {

            boolean yin;
            boolean jin;
            boolean zhuang;

            void create() throws Exception {
                yin = true;
                jin = true;
                zhuang = true;

                if (!AtFairyConfig.getOption("use_yin").equals("1")) {
                    yin = false;
                }

                if (!AtFairyConfig.getOption("use_jin").equals("1")) {
                    jin = false;
                }

                if (!AtFairyConfig.getOption("use_zhuang").equals("1")) {
                    zhuang = false;
                }
                setTaskName(1);

            }

            void init() throws Exception {
                gamePublicFuntion.init(1);
                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic(2, 45, 1278, 638, new String[]{"zhaomu.png", "zhaomu5.png"});
                LtLog.e(mFairy.getLineInfo("【招募 ：" + result.sim + "】"));
                mFairy.onTap(0.7f, result, result.x, result.y + 65, result.x + 1, result.y + 70, "【招募 - 帐篷】", 2500);

                result = mFairy.findPic(2, 2, 1278, 638, "zhaomu1.png");
                mFairy.onTap(0.8f, result, "招募", 1500);

                result = mFairy.findPic(89, 64, 1204, 171, "jiu1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    setTaskName(2);
                    return;
                }
                timeCount(2, 99);

            }

            boolean quxiao() throws Exception {

                result = mFairy.findPic(277, 238, 540, 409, "jiu6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(462, 501, 482, 525, "确定开启", 1000);
                    return true;
                }

                result = mFairy.findPic(274, 208, 615, 406, "jiu8.png");
                if (result.sim > 0.8f) {
                    yin = false;
                    mFairy.onTap(803, 503, 824, 521, "银钥匙没有了", 1000);
                    return true;
                }


                result = mFairy.findPic(274, 208, 615, 406, "jiu9.png");
                if (result.sim > 0.8f) {
                    jin = false;
                    mFairy.onTap(803, 503, 824, 521, "金钥匙没有了", 1000);
                    return true;
                }

                result = mFairy.findPic(274, 208, 615, 406, "zhuang3.png");
                if (result.sim > 0.8f) {
                    zhuang = false;
                    mFairy.onTap(803, 503, 824, 521, "装备钥匙没有了", 1000);
                    return true;
                }

                return false;
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("ok1.png");
                mFairy.onTap(0.8f, result, "确定", 500);

                result = mFairy.findPic(488, 519, 741, 694, "ok2.png");
                mFairy.onTap(0.8f, result, "确定", 500);

                result = mFairy.findPic(89, 64, 1204, 171, "jiu1.png");
                if (result.sim > 0.8f) {

                    //583,105
                    // 583,605,633,615

                    FindResult result_yin = mFairy.findPic(89, 64, 1204, 171, "jiu10.png");
                    LtLog.e(mFairy.getLineInfo("银宝箱sim:" + result_yin.sim));
                    if (result_yin.sim > 0.8f) {
                        result = mFairy.findPic(result_yin.x - 100, result_yin.y + 340, result_yin.x + 220, result_yin.y + 565, "jg.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "开启银宝箱", 2000);
                            return;
                        } else {
                            if (yin) {
                                mFairy.onTap(result_yin.x + 80, result_yin.y + 500, result_yin.x + 130, result_yin.y + 510, "开启银宝箱", 2000);
                                return;
                            }
                        }
                    }

                    FindResult result_jin = mFairy.findPic(89, 64, 1204, 171, "jiu11.png");
                    LtLog.e(mFairy.getLineInfo("金宝箱sim:" + result_jin.sim));
                    if (result_jin.sim > 0.8f) {
                        result = mFairy.findPic(result_jin.x - 100, result_jin.y + 340, result_jin.x + 220, result_jin.y + 565, "jg.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "开启金宝箱", 2000);
                            return;
                        } else {
                            if (jin) {
                                mFairy.onTap(result_jin.x + 80, result_jin.y + 500, result_jin.x + 130, result_jin.y + 510, "开启金宝箱", 2000);
                                return;
                            }
                        }
                    }


                    FindResult result_zhuang = mFairy.findPic(89, 64, 1204, 171, "zhuang1.png");
                    LtLog.e(mFairy.getLineInfo("装备宝箱sim:" + result_zhuang.sim));
                    if (result_zhuang.sim > 0.8f) {
                        result = mFairy.findPic(result_zhuang.x - 100, result_zhuang.y + 340, result_zhuang.x + 220, result_zhuang.y + 565, "jg.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "开启装备宝箱", 2000);
                            return;
                        } else {
                            if (zhuang) {
                                mFairy.onTap(result_zhuang.x + 80, result_zhuang.y + 500, result_zhuang.x + 130, result_zhuang.y + 510, "开启装备宝箱", 2000);
                                return;
                            }
                        }
                    }


                    if (frequencyMap("jiu", 2)) {
                        mFairy.onTap(30, 28, 46, 54, "", 500);
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(375, 653, 970, 714, "jiu4.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (yin) {
                        result = mFairy.findPic(914, 593, 993, 667, "yin.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "银钥匙-全部开启", 3000);
                            return;
                        } else {
                            result = mFairy.findPic(833, 600, 899, 654, "yin.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "银钥匙-开启", 1500);
                                return;
                            }
                        }
                    }

                    if (jin) {
                        result = mFairy.findPic(914, 593, 993, 667, "jin.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "金钥匙-全部开启", 3000);
                            return;
                        } else {
                            result = mFairy.findPic(833, 600, 899, 654, "jin.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "金钥匙-开启", 1500);
                                return;
                            }
                        }
                    }

                    if (zhuang) {
                        result = mFairy.findPic(914, 593, 993, 667, "zhuang2.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "装备钥匙-全部开启", 3000);
                            return;
                        } else {
                            result = mFairy.findPic(833, 600, 899, 654, "zhuang2.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "装备钥匙-开启", 1500);
                                return;
                            }
                        }
                    }

                    if (frequencyMap("yanshi", 1)) {
                        mFairy.onTap(276, 610, 320, 628, "确定", 1000);
                    }
                }


            }
        };
    }//酒馆

    public void zb() throws Exception {
        new sing(mFairy, "训练兵") {

            void create() throws Exception {
                super.create();
                setTaskName(1);
            }

            void init() throws Exception {
                gamePublicFuntion.init(1);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(3, 0);

                result = mFairy.findPic("home.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(2, 45, 1278, 638, new String[]{"zb1.png", "zb4.png", "zb3.png", "zb5.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "兵营", 1000);
                        return;
                    }

                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(200);

                        result = mFairy.findPic(2, 45, 1278, 638, new String[]{"z1.png", "z2.png"});
                        LtLog.e(mFairy.getLineInfo("zzz:" + result.sim));
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(result.x - 20, result.y + 30, result.x - 10, result.y + 40, "发现ZZZ", 3000);
                            return;
                        }
                    }

                    result = mFairy.findPic(611, 75, 788, 110, "zb2.png");
                    if (result.sim < 0.8f) {
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(611, 75, 788, 110, "zb2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("jiasu.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1092, 73, 1102, 87, "", 500);
                        setTaskName(0);
                        return;
                    }

                    result = mFairy.findPic(351, 58, 877, 153, "bing1.png");
                    if (result.sim > 0.8f) {
                        bing_type(AtFairyConfig.getOption("bing1_grade").equals("") ? 5 : Integer.parseInt(AtFairyConfig.getOption("bing1_grade")));
                    }

                    result = mFairy.findPic(351, 58, 877, 153, "bing2.png");
                    if (result.sim > 0.8f) {
                        bing_type(AtFairyConfig.getOption("bing2_grade").equals("") ? 5 : Integer.parseInt(AtFairyConfig.getOption("bing2_grade")));
                    }

                    result = mFairy.findPic(351, 58, 877, 153, "bing3.png");
                    if (result.sim > 0.8f) {
                        bing_type(AtFairyConfig.getOption("bing3_grade").equals("") ? 5 : Integer.parseInt(AtFairyConfig.getOption("bing3_grade")));
                    }

                    result = mFairy.findPic(351, 58, 877, 153, "bing4.png");
                    if (result.sim > 0.8f) {
                        bing_type(AtFairyConfig.getOption("bing4_grade").equals("") ? 5 : Integer.parseInt(AtFairyConfig.getOption("bing4_grade")));
                    }
                }
            }

            public void bing_type(int type) throws Exception {
                int spacing = 100;

                int[] range = {593 + ((type - 1) * spacing), 186, 663 + ((type - 1) * spacing), 232};

                long color = mFairy.getColorNum(range[0], range[1], range[2], range[3], "156,69,0", 0.9f);

                if (color > 50) {
                    mFairy.onTap(range[0] + 50, range[1], range[0] + 51, range[1] + 1, "选择：" + type + "级兵", 1000);
                } else {
                    for (int i = 5; i > 0; i--) {
                        Thread.sleep(300);
                        int[] newRange = {593 + ((i - 1) * spacing), 186, 663 + ((i - 1) * spacing), 232};

                        color = mFairy.getColorNum(newRange[0], newRange[1], newRange[2], newRange[3], "156,69,0", 0.9f);
                        if (color > 50) {
                            mFairy.onTap(newRange[0] + 50, newRange[1], newRange[0] + 51, newRange[1] + 1, "选择：" + i + "级兵", 1000);
                            break;
                        }
                    }
                }

                mFairy.onTap(970, 573, 1007, 595, "训练", 1000);
            }
        };
    }//造兵

    public void shandian() throws Exception {
        new sing(mFairy, "商店") {

            void create() throws Exception {
                super.create();
                setTaskName(1);
            }

            void init() throws Exception {
                gamePublicFuntion.init(1);
                setTaskName(1);
                sdCount = 1;
            }

            void content_01() throws Exception {

                result = mFairy.findPic(2, 45, 1278, 638, "sd1.png");
                mFairy.onTap(0.7f, result, "黑女人", 2500);

                result = mFairy.findPic("sd2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    setTaskName(2);
                    return;
                }
                timeCount(3, 99);
            }

            boolean zhekou(int x, int y, int x1, int y1) throws Exception {
                switch (AtFairyConfig.getOption("sd_zhekou")) {
                    case "9":
                        result = mFairy.findPic(x, y, x1, y1, "zhe9.png");
                        if (result.sim > 0.92f) {
                            return true;
                        }
                    case "8":
                        result = mFairy.findPic(x, y, x1, y1, "zhe8.png");
                        if (result.sim > 0.92f) {
                            return true;
                        }
                    case "7":
                        result = mFairy.findPic(x, y, x1, y1, "zhe7.png");
                        if (result.sim > 0.92f) {
                            return true;
                        }
                    case "6":
                        result = mFairy.findPic(x, y, x1, y1, "zhe6.png");
                        if (result.sim > 0.92f) {
                            return true;
                        }
                    case "5":
                        result = mFairy.findPic(x, y, x1, y1, "zhe5.png");
                        if (result.sim > 0.92f) {
                            return true;
                        }
                    case "4":
                        result = mFairy.findPic(x, y, x1, y1, "zhe4.png");
                        if (result.sim > 0.92f) {
                            return true;
                        }
                    case "3":
                        result = mFairy.findPic(x, y, x1, y1, "zhe3.png");
                        if (result.sim > 0.92f) {
                            return true;
                        }

                    case "2":
                        result = mFairy.findPic(x, y, x1, y1, "zhe2.png");
                        if (result.sim > 0.92f) {
                            return true;
                        }

                    case "1":
                        result = mFairy.findPic(x, y, x1, y1, "zhe1.png");
                        if (result.sim > 0.92f) {
                            return true;
                        }
                        break;
                    default:
                        return true;
                }
                return false;
            }//折扣

            /**
             *  玉米，木材，石块，金币
             *  通用加速,建造加速,研究加速,训练加速,治疗加速
             *  玉米增产,木材增产,石矿增产，金币增产，采集强化,部队扩编
             *  黑色星光雕塑  棕色星光雕塑 紫色星光雕塑 金色星光雕塑 经验书 和平护罩  领土迁移  随机迁移 银钥匙
             *  359, 175, 544, 421
             *
             */
            boolean shangpin(int x, int y, int x1, int y1) throws Exception {
                if (sdCount <= 4) {
                    for (int i = 1; i <= 4; i++) {
                        if (AtFairyConfig.getOption("a" + i).equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "a" + i + ".png");
                            if (result.sim > 0.9f) {
                                return true;
                            }
                        }
                    }

                } else if (sdCount > 4 && sdCount <= 8) {
                    for (int i = 1; i <= 5; i++) {
                        if (AtFairyConfig.getOption("b" + i).equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "b" + i + ".png");
                            if (result.sim > 0.9f) {
                                return true;
                            }
                        }
                    }

                } else if (sdCount > 8 && sdCount <= 12) {
                    for (int i = 1; i <= 6; i++) {
                        if (AtFairyConfig.getOption("c" + i).equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "c" + i + ".png");
                            if (result.sim > 0.9f) {
                                return true;
                            }
                        }
                    }
                } else {
                    for (int i = 1; i <= 10; i++) {
                        if (AtFairyConfig.getOption("d" + i).equals("1")) {
                            result = mFairy.findPic(x, y, x1, y1, "d" + i + ".png");
                            if (result.sim > 0.93f) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }//商品

            void money(int x, int y, int x1, int y1) throws Exception {
                if (zhekou(x, y, x1, y1)) {
                    if (shangpin(x, y, x1, y1)) {

                        result = mFairy.findPic(x, y, x1, y1, "sd3.png");
                        if (result.sim > 0.8f) {
                            if (!AtFairyConfig.getOption("sd_bao").equals("2")) {
                                return;
                            }
                        }
                        mFairy.tap(x + 90, y1 - 60);
                        Thread.sleep(1500);
                    }
                }
            }//买

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("sd2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(326, 236, 722, 382, "sd4.png");
                    mFairy.onTap(0.8f, result, 588, 440, 594, 446, "不在提醒", 1000);
                    mFairy.onTap(0.8f, result, 459, 508, 486, 523, "确定购买", 1000);

                    if (onceJudge("onesd")) {
                        mFairy.touchDown(1, 727, 455);
                        mFairy.touchMove(1, 727, 390, 500);
                        Thread.sleep(1000);
                        mFairy.touchUp(1);
                    }

                    int r;
                    int l;

                    LtLog.e(mFairy.getLineInfo("正在查看第" + sdCount + "个物品"));

                    if (sdCount <= 8) {
                        r = sdCount <= 4 ? sdCount : sdCount - 4;
                        l = sdCount <= 4 ? 0 : 1;
                        money(359 + (180 * (r - 1)), 175 + (l * 250), 544 + (180 * (r - 1)), 421 + (l * 250));
                    } else if (sdCount > 8 && sdCount <= 16) {
                        if (onceJudge("sd")) {
                            mFairy.ranSwipe(726, 611, 726, 239, 500, 500);
                            mFairy.ranSwipe(726, 611, 726, 239, 500, 500);
                        }
                        r = sdCount <= 12 ? sdCount - 8 : sdCount - 12;
                        l = sdCount <= 12 ? 0 : 1;
                        money(359 + (180 * (r - 1)), 175 + (l * 250), 544 + (180 * (r - 1)), 421 + (l * 250));
                    } else {
                        result = mFairy.findPic(894, 109, 1101, 211, "sd5.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "免费刷新", 1000);
                            sdCount = 1;
                            initOnceJudge("onesd");
                            initOnceJudge("sd");
                            return;
                        }
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(326, 236, 722, 382, "sd4.png");
                    mFairy.onTap(0.8f, result, 588, 440, 594, 446, "不在提醒", 1000);
                    mFairy.onTap(0.8f, result, 459, 508, 486, 523, "确定购买", 1000);

                    sdCount++;
                }
            }
        };
    }//商店

    public void dati(final boolean init) throws Exception {
        new sing(mFairy, "答题") {

            void create() throws Exception {
                super.create();
                if (init) {
                    setTaskName(0);
                } else {
                    setTaskName(1);
                }
            }

            void init() throws Exception {
                gamePublicFuntion.init(1);
                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic(2, 45, 1278, 638, "dt1.png");
                mFairy.onTap(0.7f, result, "答题", 1000);

                result = mFairy.findPic("dt2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    setTaskName(2);
                    return;
                }
                //timeCount(3, 99);
            }

            boolean quxiao() throws Exception {

                return false;
            }

            void content_02() throws Exception {
                timeCount(5, 0);

                result = mFairy.findPic("dt7.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "确定", 1000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("dt2.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(25, 481, 1256, 632, new String[]{"dt8.png", "dt9.png"});
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "开始", 1000);
                        return;
                    }

                    result = mFairy.findPic(new String[]{"dt3.png", "dt6.png"});
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "开始", 1000);
                        return;
                    }
                    if (frequencyMap("dt", 2)) {
                        mFairy.onTap(25, 23, 41, 45, "", 500);
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("dt4.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    //answer.AIAnswerWGJX();
                    Thread.sleep(2000);
                }
            }
        };

    }//答题

    public void jianzao() throws Exception {
        new sing(mFairy, "建造") {
            boolean jianzao = true;

            void create() throws Exception {
                super.create();
                jianzao = true;
                setTaskName(1);
            }

            void init() throws Exception {
                gamePublicFuntion.init(1);
                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic(2, 45, 1278, 638, "jianzao1.png");
                LtLog.e(mFairy.getLineInfo("【建造：" + result.sim + "】"));
                mFairy.onTap(0.7f, result, result.x, result.y + 65, result.x + 1, result.y + 70, "【建造 - 帐篷】", 2500);

                result = mFairy.findPic(2, 2, 1278, 638, "jiasu1.png");
                mFairy.onTap(0.8f, result, "工具", 1500);


                result = mFairy.findPic("jianzao3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    setTaskName(2);
                    return;
                }

                timeCount(2, 99);
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("jianzao3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(895, 95, 1069, 657, "jianzao2.png");
                    if (result.sim > 0.8f) {
                        frequencyInit("jianzao");
                        mFairy.onTap(0.8f, result, "建造", 1000);
                        setTaskName(3);
                        return;
                    }

                    if (AtFairyConfig.getOption("jianzao2").equals("1") && jianzao) {
                        result = mFairy.findPic(895, 95, 1069, 657, "jianzao4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "开启第二部队", 1000);

                            if (frequencyMap("guyong", 2)) {
                                jianzao = false;
                                setTaskEnd();
                                return;
                            }
                            return;
                        }
                    }

                    if (frequencyMap("jianzao", 1)) {
                        mFairy.onTap(1081, 75, 1100, 88, "", 500);
                        setTaskEnd();
                        return;
                    }
                }
            }

            void content_03() throws Exception {
                timeCount(8, 0);

                result = mFairy.findPic(2, 45, 1278, 638, new String[]{"jianzao5.png", "jianzao9.png"});
                mFairy.onTap(0.8f, result, "绿色升级", 1000);

                result = mFairy.findPic(2, 45, 1278, 638, "jianzao10.png");
                if (result.sim > 0.8f) {
                    jzTime = System.currentTimeMillis() + 600000;
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(2, 45, 1278, 638, "jianzao9.png");
                if (result.sim > 0.8f) {
                    setTaskName(4);
                    return;
                }

                result = mFairy.findPic(458, 66, 827, 119, "jianzao6.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(628, 504, 1100, 637, "jianzao7.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "前往", 1000);
                        return;
                    }

                    result = mFairy.findPic("jianzao8.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "升级", 1000);
                        setTaskName(4);
                        return;
                    }
                }

                result = mFairy.findPic("err1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(259, 504, 293, 547, "建造新建筑", 1000);
                }
            }

            void content_04() throws Exception {
                timeCount(2, 0);
                shou();
            }
        };
    }//建造

    public void lmKuang() throws Exception {
        new sing(mFairy, "联盟矿") {

            void init() throws Exception {
                result = mFairy.findPic("lm3.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.init(3);
                }
                initOnceJudge("lt");
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic(784, 619, 1194, 719, "lm1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "联盟", 1000);
                } else {
                    result = mFairy.findPic(new String[]{"lm2.png", "lm8.png"});
                    mFairy.onTap(0.8f, result, "缩放栏", 1000);
                }

                result = mFairy.findPic("lm5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1108, 37, 1121, 50, "未加入联盟", 500);
                    LM = false;
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("lm3.png");
                mFairy.onTap(0.85f, result, 866, 402, 891, 425, "领土", 1000);

                result = mFairy.findPic("lm9.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    result = mFairy.findPic("lmk6.png");
                    mFairy.onTap(0.9f, result, 999, 133, 1029, 143, "领取领土奖励", 1000);


                    result = mFairy.findPic("lmk1.png");
                    if (result.sim > 0.85f) {
                        result = mFairy.findPic("lmk2.png");
                        if (result.sim > 0.85f) {
                            result = mFairy.findPic(162, 568, 961, 611, "lmk3.png");
                            if (result.sim > 0.9f) {
                                mFairy.onTap(0.9f, result, "点击开采", 4000);
                                mFairy.onTap(625, 347, 636, 357, "点击中心位置", 500);
                                setTaskName(2);
                                return;
                            } else {
                                if (frequencyMap("lt_xy", 1)) {
                                    setTaskEnd();
                                    return;
                                }
                            }
                        } else {
                            mFairy.onTap(1053, 309, 1077, 323, "打开联盟资源中心", 500);
                        }
                    } else {

                        if (onceJudge("lt")) {
                            mFairy.ranSwipe(645, 243, 645, 548, 500, 1000);
                        } else {
                            mFairy.onTap(1036, 219, 1067, 234, "收回领土建筑栏", 1000);
                        }

                        if (frequencyMap("lt_err", 3)) {
                            setTaskEnd();
                            return;
                        }
                    }
                }
            }

            void content_02() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("lmk4.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(148, 196, 294, 478, "lmk5.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "加入", 1000);
                        return;
                    }

                    if (frequencyMap("lmk", 2)) {
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("battle3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (!AtFairyConfig.getOption("lmkt").equals("")) {
                        switch (AtFairyConfig.getOption("lmkt")) {
                            case "1":
                                mFairy.onTap(1097, 260, 1104, 270, "编队1", 1000);
                                break;
                            case "2":
                                mFairy.onTap(1100, 318, 1107, 329, "编队2", 1000);
                                break;
                            case "3":
                                mFairy.onTap(1097, 379, 1106, 389, "编队3", 1000);
                                break;
                            case "4":
                                mFairy.onTap(1096, 434, 1103, 443, "编队4", 1000);
                                break;
                            case "5":
                                mFairy.onTap(1097, 492, 1105, 502, "编队5", 1000);
                                break;
                            default:
                                break;
                        }
                    }

                    mFairy.onTap(903, 611, 956, 639, "行军", 2000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(230, 43, 1184, 705, "caiji.png");
                mFairy.onTap(0.8f, result, "采集", 1000);

                result = mFairy.findPic("battle9.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("派遣界面"));
                    err = 0;
                    for (int i = 0; i < 3; i++) {
                        Thread.sleep(300);
                        result = mFairy.findPic("battle2.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "创建部队", 1000);
                            return;
                        }
                    }

                    mFairy.onTap(42, 639, 69, 665, "init_err", 500);
                    setTaskEnd();
                }
            }
        };
    }//联盟矿

    public void daye_ymr() throws Exception {
        new sing(mFairy, "打野") {

            void init() throws Exception {
                gamePublicFuntion.init(2);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("home.png");
                mFairy.onTap(0.8f, result, 49, 642, 68, 662, "出城", 1000);

                result = mFairy.findPic("sousuo.png");
                mFairy.onTap(0.8f, result, "搜索", 500);

                result = mFairy.findPic(123, 401, 1243, 592, "sousuo1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(237, 622, 262, 648, "野蛮人", 1000);

                    while (mFairy.condit()) {
                        result = mFairy.findPic(5, 345, 1250, 468, "sousuo2.png");
                        if (result.sim > 0.8f) {
                            mFairy.touchDown(1, result.x + 5, result.y + 15);
                            mFairy.touchMove(1, 0, result.y + 15, 200, 100);
                            mFairy.touchUp(1);
                        }

                        result = mFairy.findPic(5, 345, 1250, 468, "sousuo3.png");
                        if (result.sim > 0.8f) {
                            frequencyInit("err_zy_init");
                            break;
                        }

                        if (frequencyMap("err_zy_init", 10)) {
                            setTaskName(0);
                            return;
                        }
                    }

                    result = mFairy.findPic(5, 345, 1250, 468, "sousuo4.png");
                    if (result.sim > 0.8f) {

                        int num = getNumberAssembly(AtFairyConfig.getOption("ymr_grade"));
                        LtLog.e(mFairy.getLineInfo("【用户勾选：" + num + "级】"));

                        for (int i = 0; i < num - 1; i++) {
                            mFairy.tap(result.x, result.y);
                            Thread.sleep(100);
                        }

                        Thread.sleep(200);
                    }
                    result = mFairy.findPic(123, 401, 1243, 592, "sousuo1.png");
                    mFairy.onTap(0.8f, result, "搜索", 1500);

                    for (int i = 0; i < 20; i++) {
                        Thread.sleep(200);
                        result = mFairy.findPic(549, 61, 746, 499, "sousuo5.png");
                        if (result.sim > 0.75f) {
                            mFairy.onTap(result.x, result.y + 190, result.x + 1, result.y + 195, "发现箭头", 1000);
                            break;
                        }
                    }

                    setTaskName(2);//进攻野蛮人
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(5, 0);

               /* result = mFairy.findPic(549, 61, 746, 499, "sousuo5.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(result.x, result.y + 190, result.x + 1, result.y + 195, "发现箭头", 1000);
                }*/

                result = mFairy.findPic("battle3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (!AtFairyConfig.getOption("ymr_ydzs").equals("1")) {
                        switch (AtFairyConfig.getOption("bd")) {
                            case "1":
                                mFairy.onTap(1097, 260, 1104, 270, "编队1", 1000);
                                break;
                            case "2":
                                mFairy.onTap(1100, 318, 1107, 329, "编队2", 1000);
                                break;
                            case "3":
                                mFairy.onTap(1097, 379, 1106, 389, "编队3", 1000);
                                break;
                            case "4":
                                mFairy.onTap(1096, 434, 1103, 443, "编队4", 1000);
                                break;
                            case "5":
                                mFairy.onTap(1097, 492, 1105, 502, "编队5", 1000);
                                break;
                            default:

                                break;
                        }
                       /* if (mapCount(0.8f, 662, 45, 818, 315, "biandui.png")) {
                            return;
                        }*/
                    }


                    result = mFairy.findPic("battle5.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(1104, 30, 1127, 57, "胜算较低", 500);
                        setTaskEnd();
                        return;
                    }

                    /*  switch (AtFairyConfig.getOption("battle_type")) {
                        case "1":
                            break;
                        default:
                            break;
                    }*/

                    result = mFairy.findPic("battle3.png");
                    mFairy.onTap(0.8f, result, 903, 611, 956, 639, "行军", 2000);

                    setTaskName(3);

                    if (!AtFairyConfig.getOption("ymr_wx").equals("1")) {
                        ymr_count++;
                        if (ymr_count >= getNumberAssembly(AtFairyConfig.getOption("ymr_num"))) {
                            ymr_bool = false;
                            setTaskEnd();
                            return;
                        }
                    }
                    return;
                }


                result = mFairy.findPic(230, 43, 1184, 705, "sousuo6.png");
                mFairy.onTap(0.8f, result, "搜索", 2000);

                result = mFairy.findPic(123, 401, 1243, 592, "sousuo1.png");
                mFairy.onTap(0.8f, result, "搜索", 1000);

                FindResult ba = mFairy.findPic(230, 43, 1184, 705, "battle1.png");
                if (ba.sim > 0.8f) {

                    result = mFairy.findPic(100, 43, 1184, 705, "zhencha.png");
                    if (result.sim > 0.8f) {
                        setTaskName(0);
                        return;
                    }

                    //893,468
                    //762,478,882,601
                    if (AtFairyConfig.getOption("ymr_ydzs").equals("1")) {
                        result = mFairy.findPic(ba.x - 131, ba.y + 10, ba.x - 10, ba.y + 140, "ydzs1.png");
                        mFairy.onTap(0.8f, result, "开启原地驻守", 100);
                    } else {
                        result = mFairy.findPic(ba.x - 131, ba.y + 10, ba.x - 10, ba.y + 140, "ydzs2.png");
                        mFairy.onTap(0.8f, result, "关闭原地驻守", 100);
                    }

                    mFairy.onTap(0.8f, ba, "进攻", 1000);
                }

                result = mFairy.findPic("battle9.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("派遣界面"));
                    Thread.sleep(500);
                    err = 0;
                    /**
                     *  进攻野蛮人
                     */
                    for (int i = 0; i < 2; i++) {
                        Thread.sleep(300);
                        result = mFairy.findPic(1216, 85, 1258, 661, new String[]{"battle10.png", "zou.png"});
                        if (result.sim > 0.8f) {
                            mFairy.onTap(43, 522, 70, 552, "", 500);
                            setTaskEnd();
                            return;
                        }
                    }

                    result = mFairy.findPic(1216, 85, 1258, 661, new String[]{"baty1.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "点击休息的部队", 2500);

                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(200);
                            FindResult xing = mFairy.findPic(888, 6, 1135, 704, "battle6.png");
                            if (result.sim > 0.8f) {

                                result = mFairy.findPic(881, 10, 980, 702, "battle11.png");
                                if (result.sim > 0.8f) {
                                    mFairy.onTap(42, 639, 69, 665, "", 500);
                                    huiying();
                                    setTaskEnd();
                                    return;
                                }

                                mFairy.onTap(0.8f, xing, "行军", 1000);
                                break;
                            }
                        }

                        setTaskName(3);

                        if (!AtFairyConfig.getOption("ymr_wx").equals("1")) {
                            ymr_count++;
                            LtLog.e(mFairy.getLineInfo("用户勾选次数：" + getNumberAssembly(AtFairyConfig.getOption("ymr_num")) + "  以进攻次数" + ymr_count));
                            if (ymr_count >= getNumberAssembly(AtFairyConfig.getOption("ymr_num"))) {
                                ymr_bool = false;
                                setTaskEnd();
                                return;
                            }
                        }
                    } else {

                        for (int i = 0; i < 2; i++) {
                            Thread.sleep(300);
                            result = mFairy.findPic("battle2.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "创建部队", 1000);
                                return;
                            }
                        }

                        mFairy.onTap(42, 639, 69, 665, "init_err", 500);
                        setTaskEnd();
                        return;
                    }
                }

            }

            void content_03() throws Exception {
                timeCount(3, 99);

                result = mFairy.findPic("battle8.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(899, 169, 1052, 647, "battle7.png");
                    if (result.sim > 0.8f) {
                        if (AtFairyConfig.getOption("ymr_yao").equals("1")) {
                            mFairy.onTap(0.8f, result, "使用", 500);
                        } else {
                            mFairy.onTap(1082, 70, 1096, 92, "", 500);
                            ymr_bool = false;
                            setTaskEnd();
                            return;
                        }
                    } else {
                        mFairy.onTap(1082, 70, 1096, 92, "", 500);
                        ymr_bool = false;
                        setTaskEnd();
                        return;
                    }

                    mFairy.onTap(1082, 70, 1096, 92, "", 2000);

                    result = mFairy.findPic("battle3.png");
                    mFairy.onTap(0.8f, result, 903, 611, 956, 639, "行军", 2000);

                    result = mFairy.findPic(888, 6, 1135, 704, "battle6.png");
                    mFairy.onTap(0.8f, result, "行军", 1000);

                    setTaskEnd();
                    return;
                }

            }

            boolean quxiao() throws Exception {
                result = mFairy.findPic("zhuyi1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(805, 500, 820, 515, "要进攻别人的田-否", 1000);
                    setTaskEnd();
                    return true;
                }
                return false;
            }
        };
    }//打野

    public void huiying() throws Exception {
        new sing(mFairy, "回营") {

            void init() throws Exception {
                gamePublicFuntion.init(2);
                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic("home.png");
                mFairy.onTap(0.8f, result, 49, 642, 68, 662, "出城", 1000);

                result = mFairy.findPic(1218, 154, 1268, 531, "huiying1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "回营", 2000);
                    setTaskName(2);
                }


                timeCount(5, 99);
            }

            void content_02() throws Exception {
                timeCount(5, 0);

                result = mFairy.findPic(260, 60, 1150, 628, "huiying2.png");
                mFairy.onTap(0.8f, result, "返回", 1000);
            }
        };
    }//回营

    public int yj = 1;

    public void yj() throws Exception {
        new sing(mFairy, "研究") {

            void create() throws Exception {
                super.create();
                yj = 1;
                initOnceJudge("yanjiu");
                setTaskName(1);
            }

            void init() throws Exception {
                result = mFairy.findPic("lmjx7.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(963, 521, 990, 541, "研究", 2000);

                    result = mFairy.findPic("bangzhu3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "联盟帮助", 1000);
                        setTaskEnd();
                        return;
                    }
                }
                gamePublicFuntion.init(1);
                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic(2, 45, 1278, 638, "yanjiu.png");
                LtLog.e(mFairy.getLineInfo("【研究：" + result.sim + "】"));
                mFairy.onTap(0.7f, result, result.x, result.y + 65, result.x + 1, result.y + 70, "【研究 - 帐篷】", 2500);

                result = mFairy.findPic(2, 45, 1278, 638, "yanjiu1.png");
                mFairy.onTap(0.8f, result, "药瓶", 1500);

                result = mFairy.findPic("yanjiu2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    initOnceJudge("yanjiu");
                    setTaskName(2);
                    return;
                }

                /*result = mFairy.findPic(2, 45, 1278, 638, "yanjiu.png");
                LtLog.e(mFairy.getLineInfo("研究sim:" + result.sim));
                mFairy.onTap(0.75f, result, result.x, result.y + 65, result.x + 1, result.y + 70, "研究 - 帐篷", 500);*/
                timeCount(2, 99);
            }

            void content_02() throws Exception {
                timeCount(8, 0);

                result = mFairy.findPic("yanjiu2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (onceJudge("yanjiu")) {
                        switch (AtFairyConfig.getOption("yj")) {
                            case "1":
                                mFairy.onTap(59, 169, 84, 190, "经济科技", 1000);
                                break;
                            case "2":
                                mFairy.onTap(70, 293, 89, 314, "军事科技", 1000);
                                break;
                        }
                    }

                    int[] array = {125, 189, 1142, 250};
                    switch (yj) {
                        case 1:
                            array = new int[]{125, 189, 1142, 250};
                            break;
                        case 2:
                            array = new int[]{135, 245, 1140, 324};
                            break;
                        case 3:
                            array = new int[]{140, 317, 1145, 386};
                            break;
                        case 4:
                            array = new int[]{129, 388, 1145, 447};
                            break;
                        case 5:
                            array = new int[]{125, 437, 1148, 512};
                            break;
                        case 6:
                            array = new int[]{122, 493, 1139, 575};
                            break;
                        case 7:
                            array = new int[]{125, 572, 1139, 645};
                            break;
                    }

                    result = mFairy.findPic(array[0], array[1], array[2], array[3], "yj3.png");
                    if (result.sim > 0.95f) {
                        mFairy.onTap(0.95f, result, "发现科技:" + array[0] + "," + array[1] + "," + array[2] + "," + array[3], 1500);
                        onceJudge("keji");
                    } else {
                        yj++;
                        if (yj > 7) {
                            if (onceJudge("keji")) {
                                result = mFairy.findPic(128, 86, 770, 696, "yj4.png");
                                if (result.sim > 0.92f) {
                                    yjBool = false;
                                    LtLog.e(mFairy.getLineInfo("没有科技可研究,end!"));
                                    setTaskEnd();
                                    return;
                                }
                            }

                            initOnceJudge("keji");

                            yj = 1;
                            if (frequencyMap("slip", 10)) {
                                setTaskEnd();
                                return;
                            }
                            mFairy.ranSwipe(865, 399, 334, 399, 500, 1000);
                        }
                    }
                    return;
                }

                result = mFairy.findPic("lmjx7.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic("yj1.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(1105, 62, 1122, 77, "", 500);
                        yj++;
                        return;
                    }
                    mFairy.onTap(963, 521, 990, 541, "研究", 1000);
                    setTaskName(3);
                    return;
                }
            }

            void content_03() throws Exception {
                timeCount(3, 0);
            }
        };
    }//研究

    public void new_ts() throws Exception {
        new sing(mFairy, "new探索") {

            void create() throws Exception {
                super.create();
                setTaskName(1);
            }

            void init() throws Exception {
                gamePublicFuntion.init(1);
                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic(2, 45, 1278, 638, "tansuo.png");
                LtLog.e(mFairy.getLineInfo("【探索：" + result.sim + "】"));
                mFairy.onTap(0.7f, result, result.x, result.y + 65, result.x + 1, result.y + 70, "【探索 - 帐篷】", 2500);

                result = mFairy.findPic(2, 16, 1267, 709, "tansuo1.png");//2, 45, 1278, 638,
                LtLog.e(mFairy.getLineInfo("【望远镜：" + result.sim + "】"));
                mFairy.onTap(0.8f, result, "望远镜", 1500);

                result = mFairy.findPic("chihou.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    setTaskName(2);

                    result = mFairy.findPic(907, 267, 1092, 645, "tansuo3.png");
                    mFairy.onTap(0.8f, result, "探索", 1000);

                    return;
                }

                timeCount(2, 99);
            }

            void content_02() throws Exception {
                timeCount(5, 0);

                result = mFairy.findPic(907, 267, 1092, 645, "tansuo3.png");
                mFairy.onTap(0.8f, result, "探索", 1000);

                result = mFairy.findPic(201, 46, 1191, 637, "tansuo4.png");
                mFairy.onTap(0.8f, result, "探索", 1000);

                result = mFairy.findPic(888, 6, 1132, 623, "tansuo5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "派遣", 1000);

                    result = mFairy.findPic(888, 6, 1132, 623, "tansuo5.png");
                    mFairy.onTap(0.8f, result, "派遣", 1000);

                    setTaskName(0);
                    return;
                }
            }
        };
    }//new探索

    public int low_count = 0;

    Map<String, Integer[]> mPosition = new HashMap<>();

    public boolean daye_cj(final int id, final int low_grade, final int high_grade, final int team) throws Exception {
        final boolean[] b = {true};
        new sing(mFairy, "采集：" + id + " 等级：" + low_grade + "-" + high_grade) {

            void create() throws Exception {
                low_count = 0;
                initOnceJudge("grade_init");

                for (String key : mPosition.keySet()) {
                    LtLog.e(mFairy.getLineInfo("【" + key + "】 的位置坐标:" + mPosition.get(key)[0] + "," + mPosition.get(key)[1]));
                }
            }

            void init() throws Exception {
                gamePublicFuntion.init(2);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("home.png");
                mFairy.onTap(0.8f, result, 49, 642, 68, 662, "出城", 1000);

                result = mFairy.findPic("sousuo.png");
                mFairy.onTap(0.8f, result, "搜索", 500);

                result = mFairy.findPic(123, 401, 1243, 592, "sousuo1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    switch (id) {
                        case 1:
                            mFairy.onTap(429, 617, 456, 648, "农田", 1000);
                            break;
                        case 2:
                            mFairy.onTap(627, 617, 648, 642, "伐木场", 1000);
                            break;
                        case 3:
                            mFairy.onTap(810, 610, 844, 647, "石矿床", 1000);
                            break;
                        case 4:
                            mFairy.onTap(1004, 609, 1029, 639, "金矿床", 1000);
                            break;
                    }

                    if (onceJudge("grade_init")) {
                        while (mFairy.condit()) {

                            result = mFairy.findPic(5, 345, 1250, 468, "sousuo2.png");
                            if (result.sim > 0.8f) {
                                mFairy.touchDown(1, result.x + 5, result.y + 15);
                                mFairy.touchMove(1, 0, result.y + 15, 200, 100);
                                mFairy.touchUp(1);
                            }

                            if (high_grade == 0) {
                                break;
                            }
                            Thread.sleep(200);

                            result = mFairy.findPic(5, 345, 1250, 468, "sousuo3.png");
                            if (result.sim > 0.8f) {
                                frequencyInit("err_zy_init");

                                result = mFairy.findPic(5, 345, 1250, 468, "sousuo4.png");
                                if (result.sim > 0.75f) {
                                    mFairy.touchUp(1);

                                    for (int i = 0; i < high_grade - 1; i++) {
                                        LtLog.e(mFairy.getLineInfo("+"));
                                        mFairy.tap(result.x, result.y);
                                        Thread.sleep(50);
                                    }
                                    Thread.sleep(200);
                                }
                                break;
                            }

                            if (frequencyMap("err_zy_init", 10)) {
                                setTaskName(0);
                                return;
                            }
                        }
                    } else {
                        if (low_judge()) {
                            setTaskEnd();
                            return;
                        }
                    }

                    while (mFairy.condit()) {

                        result = mFairy.findPic(123, 401, 1243, 592, "sousuo1.png");
                        mFairy.onTap(0.8f, result, "搜索", 800);

                        boolean bool = false;

                        if (mapCount(0.75f, 542, 188, 750, 488, "cj2.png")) {
                            bool = true;
                        } else {
                            result = mFairy.findPic(123, 401, 1243, 592, "sousuo1.png");
                            if (result.sim > 0.8f) {
                                bool = true;
                            }
                        }

                        if (bool) {
                            if (low_judge()) {
                                setTaskEnd();
                                return;
                            }
                            continue;
                        } else {
                            break;
                        }
                    }


                    for (int i = 0; i < 20; i++) {
                        Thread.sleep(200);
                        FindResult jian = mFairy.findPic(549, 61, 746, 499, "sousuo5.png");
                        if (jian.sim > 0.75f) {

                            for (int j = 0; j < 3; j++) {
                                result = mFairy.findPic(3, 4, 1272, 715, "position.png");
                                if (result.sim > 0.85f) {

                                    LtLog.e(mFairy.getLineInfo("位置的坐标：" + result.x + "," + result.y));
                                    LtLog.e(mFairy.getLineInfo("位置的坐标：" + mPosition.get(String.valueOf(id))[0] + "," + mPosition.get(String.valueOf(id))[1]));
                                    if (Math.abs(mPosition.get(String.valueOf(id))[0] - result.x) < 10 && Math.abs(mPosition.get(String.valueOf(id))[1] - result.y) < 10) {
                                        LtLog.e(mFairy.getLineInfo("该资源的位置坐标重复"));
                                        return;
                                    } else {
                                        mPosition.put(String.valueOf(id), new Integer[]{result.x, result.y});
                                    }
                                    break;
                                }
                            }

                            mFairy.onTap(jian.x, jian.y + 190, jian.x + 1, jian.y + 195, "发现箭头", 1000);
                            break;
                        }
                    }

                    setTaskName(2);
                    return;
                }
            }

            boolean low_judge() throws Exception {
                low_count++;
                if ((high_grade - low_count) < low_grade) {
                    return true;
                }

                result = mFairy.findPic("sousuo.png");
                mFairy.onTap(0.8f, result, "搜索", 1500);

                result = mFairy.findPic(5, 345, 1250, 468, "jian.png");
                mFairy.onTap(0.8f, result, "附近没有资源", 500);

                return false;
            }

            boolean quxiao() throws Exception {
                result = mFairy.findPic("zhuyi1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(805, 500, 820, 515, "要进攻别人的田-否", 1000);
                    setTaskEnd();
                    return true;
                }
                return false;
            }

            void content_02() throws Exception {
                timeCount(3, 0);

                result = mFairy.findPic("battle3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    switch (team) {
                        case 1:
                            mFairy.onTap(1097, 260, 1104, 270, "编队1", 1000);
                            break;
                        case 2:
                            mFairy.onTap(1100, 318, 1107, 329, "编队2", 1000);
                            break;
                        case 3:
                            mFairy.onTap(1097, 379, 1106, 389, "编队3", 1000);
                            break;
                        case 4:
                            mFairy.onTap(1096, 434, 1103, 443, "编队4", 1000);
                            break;
                        case 5:
                            mFairy.onTap(1097, 492, 1105, 502, "编队5", 1000);
                            break;
                        default:
                            break;
                    }

                       /* if (mapCount(0.8f, 662, 45, 818, 315, "biandui.png")) {
                            return;
                        }*/

                    mFairy.onTap(903, 611, 956, 639, "行军", 2000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(230, 43, 1184, 705, "sousuo6.png");
                mFairy.onTap(0.8f, result, "搜索", 2000);

                result = mFairy.findPic(123, 401, 1243, 592, "sousuo1.png");
                mFairy.onTap(0.8f, result, "搜索", 1000);

                result = mFairy.findPic(230, 43, 1184, 705, "caiji.png");
                mFairy.onTap(0.8f, result, "采集", 1000);

                result = mFairy.findPic("zhuyi1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(805, 500, 820, 515, "要进攻别人的田-否", 1000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("battle9.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("派遣界面"));
                    err = 0;

                    for (int i = 0; i < 3; i++) {
                        Thread.sleep(300);
                        result = mFairy.findPic("battle2.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "创建部队", 1000);
                            return;
                        }
                    }

                    mFairy.onTap(42, 639, 69, 665, "init_err", 500);
                    b[0] = false;
                    setTaskEnd();
                    return;
                }
            }

        };
        return b[0];
    }//采集

    public void ts() throws Exception {
        new sing(mFairy, "探索") {

            void init() throws Exception {
                gamePublicFuntion.init(1);
                setTaskName(1);
            }

            void inOperation() throws Exception {
                gamePublicFuntion.yanzheng();

                result = mFairy.findPic("chat.png");
                mFairy.onTap(0.85f, result, "聊天", 1000);

                FindResult quxiao = mFairy.findPic(686, 409, 935, 636, new String[]{"quxiao1.png", "quxiao2.png"});
                if (quxiao.sim > 0.8f) {

                    result = mFairy.findPic("fangqi.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(450, 492, 498, 518, "确定放弃当前挑战", 1000);
                        return;
                    }

                    if (quxiao()) {
                        return;
                    }

                    if (frequencyMap("quxiao", 1)) {
                        mFairy.onTap(0.8f, quxiao, "否", 1000);
                    }
                } else {
                    frequencyInit("quxiao");
                }


                result = mFairy.findPic(new String[]{"home.png", "home1.png"});
                if (result.sim > 0.8f) {
                    lmbz();

                    for (int i = 0; i < 3; i++) {
                        Thread.sleep(100);
                        result = mFairy.findPic(1114, 401, 1273, 604, new String[]{"beiBattle.png", "beiBattle1.png"});
                        if (result.sim > 0.8f) {
                            if (System.currentTimeMillis() - jiance_time > 600000) {
                                beBattle();
                                jiance_time = System.currentTimeMillis();
                                setTaskName(0);
                                return;
                            }
                        }
                    }
                }
            }

            void content_01() throws Exception {
                timeCount(30, 0);

                result = mFairy.findPic("chihou.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(907, 267, 1092, 645, "tansuo3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "探索", 1000);
                        setTaskName(2);
                        return;
                    }
                }

                result = mFairy.findPic(2, 45, 1278, 638, "tansuo1.png");
                mFairy.onTap(0.8f, result, "望远镜", 1000);

                result = mFairy.findPic(2, 45, 1278, 638, "tansuo.png");
                LtLog.e(mFairy.getLineInfo("探索sim:" + result.sim));
                mFairy.onTap(0.7f, result, result.x, result.y + 65, result.x + 1, result.y + 70, "探索 - 帐篷", 500);
            }

            void content_02() throws Exception {
                timeCount(5, 0);

                result = mFairy.findPic(201, 46, 1191, 637, "tansuo4.png");
                mFairy.onTap(0.8f, result, "探索", 1000);

                result = mFairy.findPic(888, 6, 1132, 623, "tansuo5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "派遣", 1000);

                    result = mFairy.findPic(888, 6, 1132, 623, "tansuo5.png");
                    mFairy.onTap(0.8f, result, "派遣", 1000);

                    setTaskName(0);
                    return;
                }
            }
        };
    }//探索

    public void ts_ling() throws Exception {
        new sing(mFairy, "探索 - 侦查报告") {

            void create() throws Exception {
                super.create();
                modularLookup = 0;
            }

            void init() throws Exception {
                gamePublicFuntion.init(3);
                setTaskName(1);
            }

            void inOperation() throws Exception {

                gamePublicFuntion.yanzheng();

                result = mFairy.findPic("chat.png");
                mFairy.onTap(0.85f, result, "聊天", 1000);

                FindResult quxiao = mFairy.findPic(686, 409, 935, 636, new String[]{"quxiao1.png", "quxiao2.png"});
                if (quxiao.sim > 0.8f) {

                    result = mFairy.findPic("fangqi.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(450, 492, 498, 518, "确定放弃当前挑战", 1000);
                        return;
                    }

                    if (quxiao()) {
                        return;
                    }

                    if (frequencyMap("quxiao", 1)) {
                        mFairy.onTap(0.8f, quxiao, "否", 1000);
                    }
                } else {
                    frequencyInit("quxiao");
                }


                result = mFairy.findPic(new String[]{"home.png", "home1.png"});
                if (result.sim > 0.8f) {
                    lmbz();

                    for (int i = 0; i < 3; i++) {
                        Thread.sleep(100);
                        result = mFairy.findPic(1114, 401, 1273, 604, new String[]{"beiBattle.png", "beiBattle1.png"});
                        if (result.sim > 0.8f) {
                            if (System.currentTimeMillis() - jiance_time > 600000) {
                                beBattle();
                                jiance_time = System.currentTimeMillis();
                                setTaskName(0);
                                return;
                            }
                        }
                    }
                }
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("email.png");
                mFairy.onTap(0.8f, result, "邮箱", 2500);

                result = mFairy.findPic(1008, 17, 1185, 69, "emailScene.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    LtLog.e(mFairy.getLineInfo("emailScene"));

                    result = mFairy.findPic("baogao1.png");
                    mFairy.onTap(0.85f, result, "报告", 1000);

                    result = mFairy.findPic(675, 481, 998, 631, "baogao8.png");
                    mFairy.onTap(0.85f, result, "领取奖励", 500);

                    result = mFairy.findPic(26, 68, 367, 641, "baogao2.png");
                    mFairy.onTap(0.92f, result, "探索报告", 1000);

                    result = mFairy.findPic(26, 68, 367, 641, "baogao10.png");
                    if (result.sim > 0.92f) {
                        err = 0;

                        frequencyInit("baogao");
                        frequencyInit("baogao_count");

                        if (modularLookup > 2) {
                            gamePublicFuntion.init(3);
                            setTaskName(3);//全部斥候在探洞，去3等待100秒
                            modularLookup = 0;
                            return;
                        }

                        result = modularLookup(1063, 237, 1209, 363, "baogao3.png");
                        if (result.sim > 0.8f) {
                            frequencyInit("slide_m");
                            mFairy.onTap(0.8f, result, "报告", 500);
                            setTaskName(2);
                        } else {
                            result = mFairy.findPic(1017, 108, 1249, 669, "baogao3.png");
                            if (result.sim > 0.8f) {
                                modularLookup++;
                                return;
                            } else {
                                if (frequencyMap("slide_m", 2)) {
                                    setTaskEnd();
                                    return;
                                }
                            }
                        }
                    } else {
                        if (frequencyMap("baogao", 3)) {
                            mFairy.ranSwipe(205, 400, 205, 200, 500, 1500);
                        }

                        if (frequencyMap("baogao_count", 15)) {
                            setTaskName(0);
                            if (frequencyMap("baogao_end", 1)) {
                                setTaskEnd();
                            }
                            return;
                        }
                    }
                }
            }

            void content_02() throws Exception {
                if (timeCount(5, 0)) {
                    result = mFairy.findPic("home1.png");
                    mFairy.onTap(0.8f, result, 49, 642, 68, 662, "回城", 1000);
                }

                result = mFairy.findPic(468, 166, 819, 464, new String[]{"baogao4.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, result.x, result.y + 58, result.x + 5, result.y + 65, "叹号", 1000);
                }

                result = mFairy.findPic(468, 166, 819, 464, "baogao6.png");
                mFairy.onTap(0.85f, result, "部落宝箱", 1000);

                result = mFairy.findPic(186, 37, 1186, 627, "baogao5.png");
                mFairy.onTap(0.85f, result, "调查", 1000);

                result = mFairy.findPic(186, 37, 1186, 627, "baogao7.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, result.x + 150, result.y + 280, result.x + 151, result.y + 282, "部落村庄", 500);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(888, 6, 1132, 623, "tansuo5.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(1194, 74, 1269, 673, new String[]{"pai1.png", "pai2.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "派遣", 2500);

                        result = mFairy.findPic(888, 6, 1132, 623, "tansuo5.png");
                        mFairy.onTap(0.8f, result, "派遣", 1000);
                    }

                    modularLookup++;
                    setTaskName(0);
                    return;
                }
            }

            void content_03() throws Exception {
                timeCount(35, 0);

                Thread.sleep(1000);
            }
        };
    }//侦查报告

    private static int duanzao = 1;
    private static int sheng1 = 0;
    private static int sheng2 = 0;
    private static int sheng3 = 0;
    private static int sheng4 = 0;

    public void duanzao() throws Exception {
        new sing(mFairy, "锻造") {

            void create() throws Exception {
                super.create();
                setTaskName(1);
            }

            void init() throws Exception {
                gamePublicFuntion.init(1);
                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic(2, 45, 1278, 638, new String[]{"duanzao1.png", "duanzao5.png"});
                LtLog.e(mFairy.getLineInfo("【锻造：" + result.sim + "】"));
                mFairy.onTap(0.7f, result, result.x, result.y + 155, result.x + 1, result.y + 120, "【锻造 - 帐篷】", 2500);

                result = mFairy.findPic(2, 45, 1278, 638, "duanzao2.png");
                mFairy.onTap(0.8f, result, "点击锻造", 1500);

                result = mFairy.findPic("duanzao6.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    setTaskName(2);
                    return;
                }

                timeCount(2, 99);
            }

            void content_02() throws Exception {
                timeCount(5, 0);

                result = mFairy.findPic("duanzao6.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("duanzao7.png");
                    mFairy.onTap(0.93f, result, "生产界面", 1000);

                    result = mFairy.findPic("duanzao8.png");
                    if (result.sim < 0.8f) {
                        mFairy.onTap(1083, 76, 1098, 90, "", 500);
                        LtLog.e(mFairy.getLineInfo("材料满了"));
                        setTaskEnd();
                        return;
                    }

                    switch (duanzao) {
                        case 1:
                            sheng1++;
                            if (sheng1 > getNumberAssembly(AtFairyConfig.getOption("sheng1"))) {
                                duanzao++;
                            } else {
                                mFairy.onTap(756, 223, 774, 242, "皮革", 1500);
                            }
                            break;
                        case 2:
                            sheng2++;
                            if (sheng2 > getNumberAssembly(AtFairyConfig.getOption("sheng2"))) {
                                duanzao++;
                            } else {
                                mFairy.onTap(845, 223, 863, 242, "石矿", 1500);
                            }
                            break;
                        case 3:
                            sheng3++;
                            if (sheng3 > getNumberAssembly(AtFairyConfig.getOption("sheng3"))) {
                                duanzao++;
                            } else {
                                mFairy.onTap(938, 223, 964, 248, "兽骨", 1500);
                            }
                            break;
                        case 4:
                            sheng4++;
                            if (sheng4 > getNumberAssembly(AtFairyConfig.getOption("sheng4"))) {
                                duanzao++;
                            } else {
                                mFairy.onTap(1033, 224, 1051, 245, "乌木", 1500);
                            }
                            break;
                        default:
                            duanzao = 1;
                            sheng1 = 0;
                            sheng2 = 0;
                            sheng3 = 0;
                            sheng4 = 0;
                            break;
                    }
                }
            }
        };
    }//锻造

    public void gradetl() throws Exception {

        new sing(mFairy, "升级统领") {

            int tong_x;
            int tong_y;

            void init() throws Exception {
                tong_x = 0;
                tong_y = 0;
                gamePublicFuntion.init(3);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("ok1.png");
                mFairy.onTap(0.8f, result, "确定", 500);

                result = mFairy.findPic(784, 619, 1194, 719, "tong1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "统领", 3000);
                } else {
                    result = mFairy.findPic(new String[]{"lm2.png", "lm8.png"});
                    mFairy.onTap(0.8f, result, "缩放栏", 1000);
                }

                result = mFairy.findPic("tong6.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    result = mFairy.findPic("use2.png");
                    if (result.sim > 0.85f) {
                        for (int i = 0; i < 5; i++) {
                            mFairy.onTap(0.85f, result, "使用", 1500);
                        }
                    }
                    mFairy.onTap(1080, 72, 1098, 94, "", 500);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("tong2.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    result = mFairy.findPic("tong3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "召唤", 3000);
                        return;
                    }

                    result = mFairy.findPic("tong4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "+", 1000);
                        if (mapCount(0.8f, 268, 154, 460, 631, "tong5.png")) {
                            tong_x++;

                            if (tong_x % 2 == 0) {
                                tong_y++;
                                mFairy.onTap(67, 181 + (tong_y * 140), 87, 195 + (tong_y * 140), "切换统帅", 1000);
                            } else {
                                mFairy.onTap(194, 185 + (tong_y * 140), 195, 190 + (tong_y * 140), "切换统帅", 1000);
                            }
                        }
                        Thread.sleep(1000);
                        return;
                    }
                    return;
                }

            }

        };
    }//升级统领

    public void battle() throws Exception {
        new sing(mFairy, "野蛮人 Battle") {

            void create() throws Exception {
                super.create();
                ymr_bool = true;
            }

            void init() throws Exception {
                gamePublicFuntion.init(1);
                setTaskName(1);
            }

            void content_01() throws Exception {

                if (AtFairyConfig.getOption("zl").equals("1")) {//治疗
                    zl();
                }

                shou();

                if (ymr_bool) {
                    if (!AtFairyConfig.getOption("ymr_ydzs").equals("1")) {
                        FindResult fi1 = mFairy.findPic(1122, 124, 1221, 231, "caiji1.png");
                        FindResult fi2 = mFairy.findPic(1219, 139, 1261, 457, "zhuzha.png");
                        if (fi1.sim > 0.85f && fi2.sim < 0.8f) {
                            Mat mat = mFairy.getScreenMat(1230, 161, 7, 15, 1, 0, 0, 1);
                            Mat mat1 = mFairy.getScreenMat(1197, 139, 30, 51, 1, 0, 0, 1);
                            result = mFairy.matchMat(0, 0, mat, mat1);
                            LtLog.e(mFairy.getLineInfo("相似度为：" + result.sim));
                            if (result.sim > 0.9f) {

                                if (hyJudge) {
                                    hyJudge = false;

                                    mFairy.ranSwipe(1222, 217, 1222, 440, 500, 1000);

                                    result = mFairy.findPic(1219, 139, 1261, 457, "zhuzha.png");
                                    if (result.sim > 0.8f) {
                                        huiying();
                                        gamePublicFuntion.init(3);
                                    }

                                    mFairy.ranSwipe(1224, 239, 1224, 20, 500, 1000);

                                    result = mFairy.findPic(1219, 139, 1261, 457, "zhuzha.png");
                                    if (result.sim > 0.8f) {
                                        huiying();
                                        gamePublicFuntion.init(3);
                                    }

                                    return;
                                }


                                LtLog.e(mFairy.getLineInfo("已经派出所有 的部队,end!"));
                                return;
                            }
                        }
                    }

                    daye_ymr();

                    Thread.sleep(15000);

                    setTaskName(0);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }

            }
        };
    }//进攻野蛮人

    public static String getRandomList(List<String> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        int result = new Random().nextInt(list.size() - 1);
        return list.get(result);
    }

    ArrayList<String> list = null;
    ArrayList<Mat> list_mat = null;

    public int mlzl = 1;

    public void hanhua() throws Exception {
        new TaskContent(mFairy, "喊话") {

            void create() throws Exception {
                super.create();
                mlzl = 1;
                list = new ArrayList<>();
                list_mat = new ArrayList<>();
                if (!AtFairyConfig.getOption("h1").equals("")) {
                    list.add(AtFairyConfig.getOption("h1"));
                }
                if (!AtFairyConfig.getOption("h2").equals("")) {
                    list.add(AtFairyConfig.getOption("h2"));
                }
                if (!AtFairyConfig.getOption("h3").equals("")) {
                    list.add(AtFairyConfig.getOption("h3"));
                }
                if (!AtFairyConfig.getOption("h4").equals("")) {
                    list.add(AtFairyConfig.getOption("h4"));
                }
                if (!AtFairyConfig.getOption("h5").equals("")) {
                    list.add(AtFairyConfig.getOption("h5"));
                }
                if (!AtFairyConfig.getOption("h6").equals("")) {
                    list.add(AtFairyConfig.getOption("h6"));
                }
                if (!AtFairyConfig.getOption("h7").equals("")) {
                    list.add(AtFairyConfig.getOption("h7"));
                }
                if (!AtFairyConfig.getOption("h8").equals("")) {
                    list.add(AtFairyConfig.getOption("h8"));
                }

                if (list.size() == 0) {
                    LtLog.e(mFairy.getLineInfo("list 为空"));
                    setTaskEnd();
                    return;
                }
            }

            void inOperation() throws Exception {
                gamePublicFuntion.yanzheng();

                FindResult quxiao = mFairy.findPic(686, 409, 935, 636, new String[]{"quxiao1.png", "quxiao2.png"});
                mFairy.onTap(0.8f, quxiao, "否", 1000);

            }

            void init() throws Exception {
                gamePublicFuntion.init(3);

                if (AtFairyConfig.getOption("hh").equals("1")) {//王国频道喊话
                    setTaskName(1);
                    return;
                }
                if (AtFairyConfig.getOption("hh").equals("2")) {//联盟频道喊话
                    setTaskName(2);
                    return;
                }
                if (AtFairyConfig.getOption("hh").equals("3")) {//王国频道发言的玩家
                    setTaskName(3);
                    return;
                }
                if (AtFairyConfig.getOption("hh").equals("4")) {//排行榜战力玩家
                    setTaskName(4);
                    return;
                }
                if (AtFairyConfig.getOption("hh").equals("5")) {
                    setTaskName(5);
                    return;
                }
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("chat1.png");
                mFairy.onTap(0.85f, result, "点开聊天窗口", 1000);

                result = mFairy.findPic("chat2.png");
                mFairy.onTap(0.85f, result, "点开分类", 1000);

                FindResult chat = mFairy.findPic(1050, 71, 1276, 713, "chat6.png");
                if (chat.sim > 0.8f) {
                    mFairy.inputText(getRandomList(list));
                    mFairy.onTap(0.8f, chat, "发送消息", 1000);

                    long sheep = getTimeStamp(AtFairyConfig.getOption("sheep"));

                    mFairy.onTap(147, 20, 179, 38, "", 1000);

                    Thread.sleep(sheep > 0 ? sheep : 3000);

                } else {
                    result = mFairy.findPic(39, 6, 317, 66, "chat3.png");
                    if (result.sim > 0.8f) {
                        err = 0;

                        result = mFairy.findPic(8, 42, 340, 703, "chat4.png");
                        mFairy.onTap(0.8f, result, "王国", 1000);

                        result = mFairy.findPic("chat5.png");
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic(316, 244, 462, 713, "chat13.png");
                            mFairy.onTap(0.8f, result, result.x + 180, result.y + 19, result.x + 200, result.y + 22, "点击聊天框", 3500);
                        }
                    }
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("chat1.png");
                mFairy.onTap(0.85f, result, "点开聊天窗口", 1000);

                result = mFairy.findPic("chat2.png");
                mFairy.onTap(0.85f, result, "点开分类", 1000);

                FindResult chat = mFairy.findPic(1050, 71, 1276, 713, "chat6.png");
                if (chat.sim > 0.8f) {
                    mFairy.inputText(getRandomList(list));
                    mFairy.onTap(0.8f, chat, "发送消息", 1000);

                    long sheep = getTimeStamp(AtFairyConfig.getOption("sheep"));

                    mFairy.onTap(147, 20, 179, 38, "", 1000);

                    Thread.sleep(sheep > 0 ? sheep : 3000);

                } else {
                    result = mFairy.findPic(39, 6, 317, 66, "chat3.png");
                    if (result.sim > 0.8f) {
                        err = 0;

                        result = mFairy.findPic(8, 42, 340, 703, "chat7.png");
                        mFairy.onTap(0.8f, result, "联盟", 1000);

                        result = mFairy.findPic(441, 6, 745, 56, "chat8.png");
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic(316, 244, 462, 713, "chat13.png");
                            mFairy.onTap(0.8f, result, result.x + 180, result.y + 19, result.x + 200, result.y + 22, "点击聊天框", 3500);
                        }
                    }
                }
            }

            void content_03() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("chat1.png");
                mFairy.onTap(0.85f, result, "点开聊天窗口", 1000);

                result = mFairy.findPic("chat2.png");
                mFairy.onTap(0.85f, result, "点开分类", 1000);

                FindResult chat = mFairy.findPic(1050, 71, 1276, 713, "chat6.png");
                if (chat.sim > 0.8f) {
                    err = 0;
                    mFairy.inputText(getRandomList(list));
                    mFairy.onTap(0.8f, chat, "发送消息", 1000);

                    mFairy.onTap(147, 20, 179, 38, "", 1000);

                    long sheep = getTimeStamp(AtFairyConfig.getOption("sheep"));
                    Thread.sleep(sheep > 0 ? sheep : 3000);

                } else {
                    result = mFairy.findPic(39, 6, 317, 66, "chat3.png");
                    if (result.sim > 0.8f) {
                        err = 0;

                        result = mFairy.findPic(8, 42, 340, 703, "chat4.png");
                        mFairy.onTap(0.8f, result, "王国", 3500);

                        result = mFairy.findPic("chat5.png");
                        if (result.sim > 0.8f) {
                            for (int i = 0; i < 5; i++) {
                                mFairy.onTap(382, 574 - (116 * i), 405, 604 - (116 * i), "随机选人", 5000);

                                result = mFairy.findPic("chat9.png");
                                if (result.sim > 0.8f) {

                                    Mat mat = mFairy.getScreenMat(396, 181, 49, 41, 1, 0, 0, 1);

                                    boolean m = false;
                                    for (int j = 0; j < list_mat.size(); j++) {
                                        result = mFairy.matchMat(0, 0, mat, list_mat.get(j));
                                        if (result.sim > 0.8f) {
                                            m = true;
                                            break;
                                        }
                                    }

                                    if (m) {
                                        mFairy.onTap(400, 90, 413, 104, "发现重复用户，返回", 1000);
                                    } else {
                                        list_mat.add(mat);

                                        mFairy.onTap(751, 506, 791, 522, "发送消息", 5000);

                                        result = mFairy.findPic(316, 244, 462, 713, "chat13.png");
                                        mFairy.onTap(0.8f, result, result.x + 180, result.y + 19, result.x + 200, result.y + 22, "点击聊天框", 3500);
                                        return;
                                    }
                                }
                            }
                            Thread.sleep(30000);
                        }

                    }
                }
            }

            void content_04() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(new String[]{"home.png", "home1.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(37, 26, 59, 50, "头像", 2000);
                }

                result = mFairy.findPic(197, 420, 1055, 637, "chat10.png");
                mFairy.onTap(0.8f, result, "排行榜", 3000);

                result = mFairy.findPic("chat11.png");
                mFairy.onTap(0.8f, result, 290, 390, 341, 424, "个人战力", 3000);

                result = mFairy.findPic("chat12.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    switch (mlzl) {
                        case 1:
                            mFairy.onTap(610, 220, 645, 233, "选择玩家", 3000);
                            break;
                        case 2:
                            mFairy.onTap(611, 307, 632, 319, "选择玩家", 3000);
                            break;
                        case 3:
                            mFairy.onTap(611, 385, 626, 393, "选择玩家", 3000);
                            break;
                        default:
                            mFairy.onTap(309, 478, 334, 492, "选择玩家", 3000);
                            break;
                    }

                    result = mFairy.findPic("chat14.png");
                    if (result.sim > 0.8f) {
                        mlzl++;
                        mFairy.onTap(962, 516, 984, 541, "聊天", 3500);
                    }
                }

                FindResult chat = mFairy.findPic(1050, 71, 1276, 713, "chat6.png");
                if (chat.sim > 0.8f) {
                    mFairy.inputText(getRandomList(list));
                    mFairy.onTap(0.8f, chat, "发送消息", 1000);

                    long sheep = getTimeStamp(AtFairyConfig.getOption("sheep"));

                    mFairy.onTap(147, 20, 179, 38, "", 1000);

                    Thread.sleep(sheep > 0 ? sheep : 3000);

                    result = mFairy.findPic("chat.png");
                    mFairy.onTap(0.85f, result, "聊天", 1000);

                } else {

                    result = mFairy.findPic(39, 6, 317, 66, "chat3.png");
                    if (result.sim > 0.8f) {
                        err = 0;

                        result = mFairy.findPic(316, 244, 462, 713, "chat13.png");
                        mFairy.onTap(0.8f, result, result.x + 180, result.y + 19, result.x + 200, result.y + 22, "点击聊天框", 3500);
                    }
                }
            }

            void content_05() throws Exception {
                timeCount(10, 0);

            }
        };
    }//喊话

    ArrayList<String> list_dhm;

    public void dhm() throws Exception {
        new sing(mFairy, "兑换码") {


            void create() throws Exception {
                super.create();
                list_dhm = new ArrayList<>();

                list_dhm.add("CHVSZZ1030");
                list_dhm.add("chenhe8888");
                list_dhm.add("tricktreat");
                list_dhm.add("ROK0923ROK");
                list_dhm.add("XYGTNBL688");
                list_dhm.add("IDAXIAN688");
                list_dhm.add("ROKANDROLL");
                list_dhm.add("ZHANGGUOLI");
                list_dhm.add("WANGGANG88");
                list_dhm.add("ZHANGTL923");
                list_dhm.add("DAXIAN6888");
                list_dhm.add("2GOUZDETXA");
                list_dhm.add("2020DZQ816");
                list_dhm.add("2020DZQ777");
                list_dhm.add("2020DZQ666");

            }

            void init() throws Exception {
                gamePublicFuntion.init(3);
                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic(160, 294, 1089, 668, "dhm.png");
                mFairy.onTap(0.85f, result, "兑换码", 1000);

                result = mFairy.findPic("dhm1.png");
                if (result.sim > 0.8f) {
                    frequencyInit("dhm");
                    err = 0;

                    if (list_dhm.size() > 0) {
                        mFairy.onTap(584, 344, 656, 362, "点击输入框", 2000);
                    } else {
                        mFairy.onTap(907, 187, 923, 207, "", 1000);
                        setTaskEnd();
                        return;
                    }

                    mFairy.inputText(list_dhm.get(0));
                    Thread.sleep(1000);
                    mFairy.onTap(445, 36, 589, 65, "", 1000);
                    mFairy.onTap(608, 444, 648, 466, "兑换", 500);

                    list_dhm.remove(0);
                    return;
                }

                result = mFairy.findPic("set1.png");
                if (result.sim > 0.8f) {
                    frequencyInit("dhm");
                    result = mFairy.findPic(220, 455, 1086, 634, "set3.png");
                    mFairy.onTap(0.8f, result, "设置", 1000);

                    return;
                }

                if (frequencyMap("dhm", 2)) {
                    mFairy.onTap(32, 18, 54, 39, "点击头像", 1000);
                }

                timeCount(10, 99);
            }

        };
    }//兑换码
}




