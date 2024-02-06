package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.ScreenInfo;
import com.script.opencvapi.AtFairyConfig;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class SingleTask {
    public GamePublicFuntion gamePublicFuntion;
    public AtFairyImpl mFairy;
    private FindResult result;

    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }

    public long free = 300000;//等待时长
    boolean bool_battle = false;//兵力是否达到要求
    public int leftTroops = 0;//兵力左侧数量
    public int rightTroops = 0;//兵力右侧数量
    public int count_end = 0;
    public int count_xiuxi = 0;
    public int num_xiuxi = 10;

    public boolean zy_bool = false;//是否满足资源
    public boolean sy_battle = false;//是否为死鱼
    public boolean man = false;
    public int nb1;//金币
    public int nb2;//圣水
    public int nb3;//黑油

    public final int DE_X = 39;
    public final int DE_Y = 29;


    public int cjq_a = 0;
    public int cjq_b = 0;
    public int cjq_c = 0;
    public int cjq_d = 0;

    public List<int[]> list_up_left;
    public List<int[]> list_up_right;
    public List<int[]> list_down_left;
    public List<int[]> list_down_right;

    public ScreenInfo screen_up_screen = null;//上边场景截图
    public ScreenInfo screen_down_screen = null;//下边场景截图

    public boolean jb_bool = true;
    public boolean ybt_bool = true;

    public int end_num = 10;

    public boolean xunLianSwitch = false;

    public boolean xunLian1 = false;
    public boolean xunLian2 = false;
    public boolean xunLian3 = false;
    public boolean xunLian4 = false;

    public int cxunCount = 0;

    public void szy() throws Exception {

        int j = 0;

        for (int i = 0; i < 2; i++) {
            Thread.sleep(500);
            result = mFairy.findPic(235, 71, 1127, 695, new String[]{"zi1.png", "zi2.png", "zi3.png"});
            if (result.sim > 0.86f) {
                i = 0;
                j++;
                if (j > 30) {
                    break;
                }
                mFairy.onTap(0.86f, result, "收资源", 300);
            }
        }
    }//收资源

    abstract class sing extends TaskContent {

        public sing(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);
        }

        void init() throws Exception {
            gamePublicFuntion.init(false);

            result = mFairy.findPic(128, 504, 425, 665, "js.png");
            mFairy.onTap(0.8f, result, 975, 31, 991, 52, "", 1000);

            setTaskName(1);
        }

    }

    public int grade = 1;

    public void grade() throws Exception {
        new sing(mFairy, "升级建筑") {

            void create() throws Exception {
                super.create();
                grade = 1;
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic(330, 230, 914, 472, new String[]{"g6.png", "zybz.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(891, 210, 911, 230, "", 500);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("grade2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(476, 445, 521, 472, "该建筑正在加速中,跳过！", 500);
                    grade++;
                    return;
                }

                result = mFairy.findPic(485, 26, 775, 120, "yj4.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 855, 615, 892, 633, "确定升级", 1000);
                    mFairy.onTap(1124, 52, 1136, 66, "", 1000);
                } else {

                    result = mFairy.findPic("shangdian.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        mFairy.onTap(0.8f, result, "该建筑需要先建造，跳过！", 500);
                        grade++;
                        return;
                    }

                }

                if (gamePublicFuntion.home()) {

                    result = mFairy.findPic(415, 18, 706, 74, new String[]{"g5.png", "grade5.png"});
                    if (result.sim > 0.8f) {
                        err = 0;
                        frequencyInit("grade_err");
                        mFairy.onTap(0.8f, result, 621, 630, 683, 642, "确定升级", 500);

                        result = mFairy.findPic(957, 550, 1135, 717, "grade4.png");
                        mFairy.onTap(0.8f, result, "升级", 1000);

                        return;
                    }


                    result = mFairy.findPic(415, 18, 706, 74, "chongjian1.png");
                    if (result.sim > 0.8f) {
                        frequencyInit("grade_err");
                        mFairy.onTap(0.8f, result, 869, 551, 952, 569, "确定升级", 500);
                        return;
                    }

                    if (AtFairyConfig.getOption("grade").equals("2")) {
                        result = mFairy.findPic(627, 27, 661, 54, "grade3.png");
                        if (result.sim > 0.8f) {
                            setTaskEnd();
                            return;
                        }
                    }

                    result = mFairy.findPic(624, 20, 660, 63, "grade1.png");
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return;
                    }

                    mFairy.onTap(1073, 709, 1082, 713, "", 1000);
                    mFairy.onTap(604, 40, 618, 50, "", 1500);

                    FindResult grage = mFairy.findPic(503, 109, 662, 512, "g2.png");
                    if (grage.sim > 0.8f) {
                        long l;

                        while (mFairy.condit()) {

                            LtLog.e(mFairy.getLineInfo("grade:" + grade));

                            l = mFairy.getColorNum(grage.x + 165, grage.y + 29, grage.x + 294, grage.y + 62, "247,247,247", 0.92f);
                            if (l > 100 && grade == 1) {
                                mFairy.onTap(grage.x + 50, grage.y + 33, grage.x + 55, grage.y + 34, "升级", 1000);
                                break;
                            } else {
                                if (grade == 1) {
                                    grade = 2;
                                }
                            }

                            l = mFairy.getColorNum(grage.x + 165, grage.y + 65, grage.x + 294, grage.y + 100, "247,247,247", 0.92f);
                            if (l > 100 && grade == 2) {
                                mFairy.onTap(grage.x + 50, grage.y + 75, grage.x + 55, grage.y + 76, "升级", 1000);
                                break;
                            } else {
                                if (grade == 2) {
                                    grade = 3;
                                }
                            }

                            l = mFairy.getColorNum(grage.x + 165, grage.y + 105, grage.x + 294, grage.y + 140, "247,247,247", 0.92f);
                            if (l > 100 && grade == 3) {
                                mFairy.onTap(grage.x + 50, grage.y + 110, grage.x + 55, grage.y + 111, "升级", 1000);
                                break;
                            }

                            if (frequencyMap("grade_err", 5)) {
                                mFairy.onTap(447, 34, 463, 47, "", 500);
                                setTaskEnd();
                                return;
                            }
                        }

                        result = mFairy.findPic(282, 473, 1000, 693, new String[]{"g4.png", "chongjian.png"});
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "升级", 300);
                            err = 0;
                        }

                    } else {
                        mFairy.ranSwipe(526, 234, 530, 445, 500, 500);
                    }
                }
            }
        };
    }//升级

    public void zeng() throws Exception {
        new sing(mFairy, "增援") {

            void content_01() throws Exception {
                timeCount(5, 99);

                if (gamePublicFuntion.home()) {
                    err = 0;
                    mFairy.onTap(16, 289, 23, 324, "打开聊天框", 500);
                }

                result = mFairy.findPic(19, 630, 149, 720, new String[]{"zeng1.png", "zeng7.png"});
                if (result.sim > 0.8f) {
                    err = 0;

                    if (AtFairyConfig.getOption("juan").equals("1")) {

                        result = mFairy.findPic(506, 4, 645, 426, "zeng4.png");
                        if (result.sim > 0.8f) {
                            boolean boolEnd = true;
                            frequencyInit("zeng");
                            int x = result.x + 6;
                            int x1 = result.x + 22;
                            int y = result.y + 108;
                            int y1 = result.y + 125;

                            List<int[]> l = new ArrayList();

                            for (int i = 0; i < 2; i++) {
                                for (int j = 0; j < 6; j++) {
                                    long c = mFairy.getColorNum(x + (j * 90), y + (115 * i), x1 + (j * 90), y1 + (115 * i), "61,121,181", 0.93f);
                                    if (c > 10) {
                                        l.add(new int[]{x + (j * 90), y + (115 * i)});
                                    }
                                }
                            }

                            if (l.size() > 0) {
                                for (int i = 0; i < 7; i++) {
                                    Random random = new Random();
                                    int n = random.nextInt(l.size());
                                    int[] r = l.get(n);
                                    mFairy.onTap(r[0], r[1], r[0] + 1, r[1] + 1, "捐兵", 500);
                                }
                                boolEnd = false;
                            }

                            for (int j = 0; j < 6; j++) {
                                long c = mFairy.getColorNum(x + (j * 90), y + (282), x1 + (j * 90), y1 + (297), "109,69,189", 0.93f);
                                if (c > 10) {
                                    LtLog.e(mFairy.getLineInfo("捐法术"));
                                    mFairy.tap(x + (j * 90), (y + 282));
                                    boolEnd = false;
                                }
                            }

                            if (boolEnd) {
                                gamePublicFuntion.close();

                                if (frequencyMap("juan", 1)) {
                                    setTaskEnd();
                                    return;
                                } else {
                                    result = mFairy.findPic(413, 51, 516, 139, "zeng3.png");
                                    if (result.sim > 0.9f) {
                                        mFairy.onTap(0.9f, result, "寻找增援", 1000);
                                        return;
                                    }

                                    result = mFairy.findPic(392, 538, 532, 664, "zeng3.png");
                                    if (result.sim > 0.9f) {
                                        mFairy.onTap(0.9f, result, "寻找增援", 1000);
                                        return;
                                    }
                                }
                            }
                            return;
                        }

                        result = mFairy.findPic(331, 58, 497, 644, "zeng2.png");
                        if (result.sim > 0.9f) {
                            frequencyInit("zeng");
                            mFairy.onTap(0.9f, result, "增援", 1200);
                            return;
                        }

                        result = mFairy.findPic(413, 51, 516, 139, "zeng3.png");
                        if (result.sim > 0.9f) {
                            mFairy.onTap(0.9f, result, "寻找增援", 1000);
                            return;
                        }

                        result = mFairy.findPic(392, 538, 532, 664, "zeng3.png");
                        if (result.sim > 0.9f) {
                            mFairy.onTap(0.9f, result, "寻找增援", 1000);
                            return;
                        }
                    }

                    if (frequencyMap("zeng", 1)) {

                        if (AtFairyConfig.getOption("qiu").equals("1")) {
                            result = mFairy.findPic("zeng5.png");
                            if (result.sim > 0.92f) {
                                mFairy.onTap(0.8f, result, "发送增援", 500);
                                mFairy.onTap(772, 555, 809, 573, "发送", 1000);

                                result = mFairy.findPic("zeng6.png");
                                mFairy.onTap(0.8f, result, "", 500);
                            }
                        }
                        setTaskEnd();
                        return;
                    }
                }
            }

        };

    }//增援

    public void ling() throws Exception {
        new sing(mFairy, "领奖励") {
            void content_01() throws Exception {
                timeCount(2, 99);

                if (gamePublicFuntion.home()) {
                    err = 0;
                    result = mFairy.findPic("jiang.png");
                    if (result.sim > 0.8f) {
                        frequencyInit("jiang");
                        mFairy.onTap(0.8f, result, "发现有奖励可以领取", 2000);
                        return;
                    }

                    if (frequencyMap("jiang", 1)) {
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(918, 78, 1157, 693, "jiang1.png");
                mFairy.onTap(0.8f, result, "领取", 500);
            }
        };
    }//领奖励

    int js = 1;

    public void js() throws Exception {
        new sing(mFairy, "加速") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
                js = 1;
            }

            void content_01() throws Exception {
                timeCount(5, 99);

                if (gamePublicFuntion.home()) {

                    result = mFairy.findPic(128, 504, 425, 665, "js.png");
                    mFairy.onTap(0.8f, result, 1230, 34, 1240, 50, "", 1000);

                    FindResult rejs = mFairy.findPic(214, 603, 389, 719, "jiasu.png");
                    LtLog.e("石头 :" + rejs.sim);
                    if (rejs.sim > 0.75f) {
                        err = 0;
                        /**
                         * 271,655
                         * 155,458      -116 -197
                         * 186,482      -85  -173
                         * 221,508      -50  -147
                         * 253,535      -18 -120
                         * 288,562      +17 -93
                         * 323,586      +50 -69
                         * 352,610      +81  -45
                         * 386,635      +115 -20
                         */
                        switch (js) {
                            case 1:
                                if (AtFairyConfig.getOption("js1").equals("1")) {
                                    mFairy.onTap(rejs.x - 116, rejs.y - 197, rejs.x - 115, rejs.y - 196, "蛮人基座 坐标：" + (rejs.x - 121) + "," + (rejs.y + 312), 1000);
                                } else {
                                    js++;
                                }
                                break;
                            case 2:
                                if (AtFairyConfig.getOption("js2").equals("1")) {
                                    mFairy.onTap(rejs.x - 85, rejs.y - 173, rejs.x - 84, rejs.y - 172, "守护基座 坐标：" + (rejs.x - 84) + "," + (rejs.y + 339), 1000);
                                } else {
                                    js++;
                                }
                                break;
                            case 3:
                                if (AtFairyConfig.getOption("js3").equals("1")) {
                                    mFairy.onTap(rejs.x - 50, rejs.y - 147, rejs.x - 49, rejs.y - 146, "弓箭基座 坐标：" + (rejs.x - 49) + "," + (rejs.y + 368), 1000);
                                } else {
                                    js++;
                                }
                                break;
                            case 4:
                                if (AtFairyConfig.getOption("js4").equals("1")) {
                                    mFairy.onTap(rejs.x - 18, rejs.y - 120, rejs.x - 17, rejs.y - 119, "战神基座 坐标：" + (rejs.x - 12) + "," + (rejs.y + 394), 1000);
                                } else {
                                    js++;
                                }
                                break;
                            case 5:
                                if (AtFairyConfig.getOption("js5").equals("1")) {
                                    mFairy.onTap(rejs.x + 17, rejs.y - 93, rejs.x + 18, rejs.y - 92, "金矿收集器 坐标：" + (rejs.x + 22) + "," + (rejs.y + 429), 1000);
                                } else {
                                    js++;
                                }
                                break;
                            case 6:
                                if (AtFairyConfig.getOption("js6").equals("1")) {
                                    mFairy.onTap(rejs.x + 50, rejs.y - 69, rejs.x + 51, rejs.y - 68, "黑油收集器 坐标：" + (rejs.x + 53) + "," + (rejs.y + 453), 1000);
                                } else {
                                    js++;
                                }
                                break;
                            case 7:
                                if (AtFairyConfig.getOption("js7").equals("1")) {
                                    mFairy.onTap(rejs.x + 81, rejs.y - 45, rejs.x + 82, rejs.y - 44, "圣水收集器 坐标：" + (rejs.x + 91) + "," + (rejs.y + 481), 1000);
                                } else {
                                    js++;
                                }
                                break;
                            default:
                                setTaskEnd();
                                return;
                        }

                    }


                    result = mFairy.findPic(286, 515, 1034, 674, "js1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "提速所有同类项目", 800);
                        mFairy.onTap(624, 478, 638, 492, "", 500);
                        frequencyInit("js");
                        js++;
                        return;
                    }

                    result = mFairy.findPic(286, 515, 1034, 674, "js2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "提速", 800);
                        mFairy.onTap(624, 478, 638, 492, "", 500);
                        frequencyInit("js");
                        js++;
                        return;
                    }

                    if (frequencyMap("js", 1)) {
                        js++;
                        return;
                    }
                }
            }
        };
    }//加速

    boolean qiang1 = false;
    boolean qiang2 = false;

    public void qiang() throws Exception {
        new sing(mFairy, "刷墙") {
            void create() throws Exception {
                super.create();

                if (AtFairyConfig.getOption("qiang1").equals("1")) {
                    qiang1 = true;
                }
                if (AtFairyConfig.getOption("qiang2").equals("1")) {
                    qiang2 = true;
                }
            }

            void inOperation() throws Exception {
                result = mFairy.findPic(624, 20, 660, 63, "grade1.png");
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(330, 230, 914, 472, new String[]{"g6.png", "zybz.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(891, 210, 911, 230, "", 500);
                    setTaskEnd();
                    return;
                }
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.home()) {

                    result = mFairy.findPic(624, 20, 660, 63, "grade1.png");
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return;
                    }


                    long a = gamePublicFuntion.get_number(0.8f, 1197, 26, 1208, 56, -5, 20, 40, "n", null);
                    long b = gamePublicFuntion.get_number(0.8f, 1197, 88, 1208, 124, -5, 20, 40, "n", null);

                    LtLog.e(mFairy.getLineInfo("检测金币数量为：" + a));
                    LtLog.e(mFairy.getLineInfo("检测圣水数量为：" + b));

                    if (a < getNumberAssembly(AtFairyConfig.getOption("jbshop")) * 1000) {
                        qiang1 = false;
                    }

                    if (b < getNumberAssembly(AtFairyConfig.getOption("ssshop")) * 1000) {
                        qiang2 = false;
                    }

                    if (qiang1 == false && qiang2 == false) {
                        setTaskEnd();
                        return;
                    }

                    mFairy.onTap(1073, 709, 1082, 713, "", 300);
                    mFairy.onTap(588, 32, 608, 46, "", 200);
                    initOnceJudge("oneq");
                    frequencyInit("qiang_judge");
                    frequencyInit("qiang");
                    setTaskName(2);
                }
            }

            void content_02() throws Exception {
                timeCount(25, 99);


                result = mFairy.findPic(485, 26, 775, 120, "yj4.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 855, 615, 892, 633, "确定升级", 1000);
                    mFairy.onTap(1124, 52, 1136, 66, "", 1000);
                    setTaskName(1);
                    return;
                }


                result = mFairy.findPic(392, 13, 751, 91, "qiang2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 621, 618, 657, 639, "确定升级", 1000);
                    setTaskName(1);
                    return;
                }

                if (gamePublicFuntion.home()) {
                    result = mFairy.findPic("qiang1.png");
                    if (result.sim > 0.8f) {

                        LtLog.e(mFairy.getLineInfo("城墙界面"));


                        result = mFairy.findPic(213, 513, 1132, 673, "q1.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("找到升级按钮"));
                            err = 0;
                            if (AtFairyConfig.getOption("qiang1").equals("1") && qiang1) {
                                LtLog.e(mFairy.getLineInfo("开始验证金币"));
                                result = mFairy.findPic(215, 517, 1120, 579, "q2.png");
                                if (result.sim > 0.85f) {
                                    long number = mFairy.getColorNum(result.x - 100, 532, result.x, 563, "255,136,127", 0.88f);
                                    if (number > 100) {
                                        LtLog.e(mFairy.getLineInfo("金币资源不足"));
                                        qiang1 = false;
                                    } else {
                                        mFairy.onTap(result.x - 50, result.y + 50, result.x - 40, result.y + 60, "资源足够,升级", 1000);
                                    }
                                    return;
                                }

                                if (frequencyMap("qiang", 1)) {
                                    qiang1 = false;
                                }
                                return;
                            }

                            if (AtFairyConfig.getOption("qiang2").equals("1") && qiang2) {
                                LtLog.e(mFairy.getLineInfo("开始验证圣水"));
                                result = mFairy.findPic(215, 517, 1120, 579, "q3.png");
                                if (result.sim > 0.85f) {
                                    long number = mFairy.getColorNum(result.x - 100, 532, result.x, 563, "255,136,127", 0.88f);
                                    if (number > 100) {
                                        mFairy.onTap(975, 31, 991, 52, "", 500);
                                        LtLog.e(mFairy.getLineInfo("圣水资源不足"));
                                        setTaskEnd();
                                    } else {
                                        mFairy.onTap(result.x - 50, result.y + 50, result.x - 40, result.y + 60, "资源足够,升级", 1000);
                                    }
                                    return;
                                }

                                if (frequencyMap("qiang", 1)) {
                                    setTaskEnd();
                                }
                                return;
                            }
                            setTaskEnd();
                            return;
                        }

                    } else {
                        result = mFairy.findPic(355, 89, 462, 496, "qiang.png");
                        if (result.sim > 0.8f) {
                            frequencyInit("qiang_judge");
                            mFairy.onTap(0.8f, result, "点击城墙", 1000);
                            return;
                        } else {
                            if (frequencyMap("qiang_judge", 1)) {
                                if (onceJudge("oneq")) {
                                    result = mFairy.findPic(345, 77, 492, 429, "shua.png");
                                    if (result.sim < 0.8f) {
                                        for (int i = 0; i < 5; i++) {
                                            mFairy.ranSwipe(610, 190, 611, 400, 500, 500);
                                        }
                                        Thread.sleep(1000);
                                    }
                                } else {
                                    mFairy.ranSwipe(610, 400, 611, 190, 500, 1500);
                                }
                            }
                        }
                    }
                }

            }
        };
        mFairy.onTap(1224, 33, 1243, 50, "", 200);
    }//刷墙

    public void ql() throws Exception {
        new sing(mFairy, "清理杂草") {

            void content_01() throws Exception {
                setTaskName(2);
                return;
            }

            void content_02() throws Exception {
                timeCount(6, 99);

                result = mFairy.findPic(624, 20, 660, 63, "grade1.png");
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(84, 64, 1192, 712, new String[]{"z1.png", "z2.png", "z3.png", "z4.png", "z5.png", "z6.png"
                        /* , "z7.png", "z8.png", "z9.png", "z10.png", "z11.png", "z12.png"*/, "z13.png"});
                mFairy.onTap(0.8f, result, "发现杂草", 1000);

                result = mFairy.findPic(428, 590, 841, 668, "yc.png");
                mFairy.onTap(0.8f, result, "移除", 1000);

                result = mFairy.findPic(330, 230, 914, 472, new String[]{"g6.png", "zybz.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(891, 210, 911, 230, "", 500);
                    setTaskEnd();
                    return;
                }
            }
        };
    }//清理杂草

    public void yjs() throws Exception {
        new sing(mFairy, "随机研究") {

            void content_01() throws Exception {
                setTaskName(2);
                return;
            }

            void content_02() throws Exception {
                timeCount(5, 99);

                result = mFairy.findPic(485, 26, 775, 120, "yj4.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 855, 615, 892, 633, "确定升级", 1000);
                    mFairy.onTap(1124, 52, 1136, 66, "", 1000);
                }
                result = mFairy.findPic("yj5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(892, 210, 910, 234, "", 500);
                    mFairy.onTap(1059, 34, 1076, 49, "", 500);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(524, 14, 729, 77, "yj6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1065, 34, 1082, 48, "", 500);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(441, 19, 715, 75, "yj1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    List<FindResult> ss = mFairy.findPic(246, 417, 1093, 654, 0.85f, "yj2.png");
                    for (int i = 0; i < ss.size(); i++) {
                        long color = mFairy.getColorNum(ss.get(i).x - 100, ss.get(i).y - 10, ss.get(i).x, ss.get(i).y + 15, "240,240,240", 0.92f);
                        if (color > 100) {
                            mFairy.onTap(ss.get(i).x - 50, ss.get(i).y - 50, ss.get(i).x - 49, ss.get(i).y - 49, "发现可研究目标", 1000);
                            return;
                        }
                    }

                    List<FindResult> hy = mFairy.findPic(246, 417, 1093, 654, 0.85f, "yj3.png");
                    for (int i = 0; i < hy.size(); i++) {
                        long color = mFairy.getColorNum(hy.get(i).x - 100, hy.get(i).y - 10, hy.get(i).x, hy.get(i).y + 15, "240,240,240", 0.92f);
                        if (color > 100) {
                            mFairy.onTap(hy.get(i).x - 50, hy.get(i).y - 50, hy.get(i).x - 49, hy.get(i).y - 49, "发现可研究目标", 1000);
                            return;
                        }
                    }

                    if (frequencyMap("huadong", 3)) {
                        mFairy.onTap(1065, 34, 1082, 48, "", 500);
                        setTaskEnd();
                        return;
                    } else {
                        mFairy.ranSwipe(923, 497, 446, 505, 500, 2000);
                    }

                } else {
                    FindResult rejs = mFairy.findPic(214, 603, 389, 714, "jiasu.png");
                    LtLog.e("石头 :" + rejs.sim);
                    mFairy.onTap(0.75f, rejs, rejs.x + 120, rejs.y - 20, rejs.x + 121, rejs.y - 19, "研究所 坐标：" + (rejs.x + 91) + "," + (rejs.y + 481), 2000);
                }

                result = mFairy.findPic(557, 476, 978, 697, "yj.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "研究", 1000);
                } else {
                    result = mFairy.findPic(128, 504, 425, 665, "js.png");
                    mFairy.onTap(0.8f, result, 975, 31, 991, 52, "", 1000);
                }
            }
        };

    }//随机研究


    public void cjb() throws Exception {
        new sing(mFairy, "超级兵") {

            void content_01() throws Exception {
                setTaskName(2);
                return;
            }

            void content_02() throws Exception {
                if (timeCount(10, 99)) {

                    LtLog.e(mFairy.getLineInfo("超级兵训练异常！！！"));

                    cjbList.clear();
                }


                result = mFairy.findPic(133, 200, 346, 372, "cjb4.png");
                mFairy.onTap(0.8f, result, "强化兵营", 2500);


                result = mFairy.findPic("cjb2.png");
                if (result.sim > 0.8f) {
                    err = 0;


                    if (cjbList.size() == 0) {
                        mFairy.onTap(1080, 57, 1104, 74, "", 2000);
                        setTaskEnd();
                        return;
                    }

                    LtLog.e(mFairy.getLineInfo("超级兵界面 :" + cjbList.get(0)));

                    if (cjbList.get(0) < 9) {
                        mFairy.ranSwipe(686, 305, 686, 645, 300, 2000);
                    } else {
                        mFairy.ranSwipe(686, 645, 686, 305, 300, 2000);
                    }

                    switch (cjbList.get(0)) {
                        case 1:
                            mFairy.onTap(269, 388, 287, 396, "", 1500);
                            break;
                        case 2:
                            mFairy.onTap(494, 388, 516, 394, "", 1500);
                            break;
                        case 3:
                            mFairy.onTap(758, 394, 777, 399, "", 1500);
                            break;
                        case 4:
                            mFairy.onTap(967, 394, 987, 403, "", 1500);
                            break;
                        case 5:
                            mFairy.onTap(266, 644, 276, 648, "", 1500);
                            break;
                        case 6:
                            mFairy.onTap(508, 643, 523, 649, "", 1500);
                            break;
                        case 7:
                            mFairy.onTap(739, 641, 751, 649, "", 1500);
                            break;
                        case 8:
                            mFairy.onTap(984, 644, 1004, 653, "", 1500);
                            break;
                        case 9:
                            mFairy.onTap(259, 243, 276, 255, "", 1500);
                            break;
                        case 10:
                            mFairy.onTap(484, 237, 512, 258, "", 1500);
                            break;
                        case 11:
                            mFairy.onTap(760, 242, 775, 252, "", 1500);
                            break;
                        case 12:
                            mFairy.onTap(975, 250, 994, 260, "", 1500);
                            break;
                        case 13:
                            mFairy.onTap(266, 539, 288, 556, "", 1500);
                            break;
                        case 14:
                            mFairy.onTap(493, 528, 512, 548, "", 1500);
                            break;
                    }

                    return;
                }


                result = mFairy.findPic(594, 490, 736, 566, "cjb3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("训练超级兵界面"));

                    result = mFairy.findPic("cjb5.png");
                    if (result.sim < 0.8f) {

                        if (AtFairyConfig.getOption("cjbqh").equals("1")) {
                            long color1 = mFairy.getColorNum(615, 598, 654, 616, "130,189,253", 0.95f);
                            if (color1 > 10) {
                                mFairy.onTap(679, 618, 701, 633, "道具训练", 1500);
                                mFairy.onTap(626, 527, 651, 541, "", 1000);
                                return;
                            }
                        }

                        long color2 = mFairy.getColorNum(843, 630, 928, 645, "255,136,127", 0.95f);
                        if (color2 < 10) {
                            mFairy.onTap(861, 612, 892, 630, "黑水训练", 1500);
                            mFairy.onTap(626, 527, 651, 541, "", 1000);
                            return;
                        }

                    }


                    mFairy.onTap(1116, 52, 1141, 64, "", 1500);

                    if (cjbList.size() != 0) {
                        cjbList.remove(0);
                    }
                }
            }
        };

    }//超级兵


    public int y_grade = 1;

    public void ysj() throws Exception {
        new sing(mFairy, "夜世界") {

            void init() throws Exception {
                gamePublicFuntion.init(true);

                result = mFairy.findPic(128, 504, 425, 665, "js.png");
                mFairy.onTap(0.8f, result, 975, 31, 991, 52, "", 1000);
                initOnceJudge("szy");

                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(20, 99);

                if (gamePublicFuntion.ysj_home()) {
                    setTaskName(2);
                    return;
                } else {
                    gamePublicFuntion.narrow();
                    Thread.sleep(1000);


                    LtLog.e(mFairy.getLineInfo("滑动找船"));

                    for (int i = 0; i < 2; i++) {
                        mFairy.ranSwipe(800, 437, 481, 145, 200, 1000);
                    }


                    result = mFairy.findPic(100, 329, 617, 611, new String[]{"chuan.png", "chuan2.png"});
                    mFairy.onTap(0.7f, result, "船", 3000);
                }
            }

            void content_02() throws Exception {
                timeCount(10, 99);

                if (AtFairyConfig.getOption("ys_szy").equals("1")) {
                    if (onceJudge("szy")) {

                        for (int i = 0; i < 2; i++) {
                            Thread.sleep(500);

                            result = mFairy.findPic(235, 71, 1127, 695, new String[]{"y1.png", "y2.png", "zy1.png"});
                            if (result.sim > 0.8f) {
                                i = 0;
                                mFairy.onTap(0.8f, result, "收资源", 300);
                            }
                        }
                    }
                }

                if (AtFairyConfig.getOption("ys_grade").equals("1")) {

                    result = mFairy.findPic(330, 230, 914, 472, new String[]{"yg1.png", "zybz.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(891, 210, 911, 230, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("shangdian.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "该建筑需要先建造，跳过！", 500);
                        y_grade++;
                        return;
                    }

                    if (gamePublicFuntion.ysj_home()) {
                        err = 0;

                        result = mFairy.findPic(415, 18, 706, 74, new String[]{"g5.png", "grade6.png"});
                        if (result.sim > 0.8f) {
                            frequencyInit("grade_err");
                            mFairy.onTap(0.8f, result, 621, 630, 683, 642, "确定升级", 200);
                            mFairy.onTap(0.8f, result, 954, 631, 986, 646, "确定升级", 200);
                            return;
                        }

                        result = mFairy.findPic(488, 64, 728, 154, "g7.png");
                        if (result.sim > 0.8f) {
                            frequencyInit("grade_err");
                            mFairy.onTap(0.8f, result, 643, 562, 675, 584, "确定升级", 500);
                            return;
                        }

                        result = mFairy.findPic("yg2.png");
                        if (result.sim > 0.8f) {
                            setTaskEnd();
                            return;
                        }

                        mFairy.onTap(1073, 709, 1082, 713, "", 1000);
                        mFairy.onTap(715, 33, 735, 50, "", 1000);

                        FindResult grage = mFairy.findPic(611, 113, 749, 514, "g2.png");
                        if (grage.sim > 0.8f) {

                            long l;

                            while (mFairy.condit()) {

                                /**
                                 * 509,119
                                 * 672,183,801,219
                                 *
                                 */

                                LtLog.e(mFairy.getLineInfo("grade:" + y_grade));

                                l = mFairy.getColorNum(grage.x + 165, grage.y + 29, grage.x + 294, grage.y + 62, "247,247,247", 0.92f);
                                if (l > 100 && y_grade == 1) {
                                    mFairy.onTap(grage.x + 50, grage.y + 33, grage.x + 55, grage.y + 34, "升级", 2500);
                                    break;
                                } else {
                                    if (y_grade == 1) {
                                        y_grade = 2;
                                        break;
                                    }
                                }

                                l = mFairy.getColorNum(grage.x + 165, grage.y + 65, grage.x + 294, grage.y + 100, "247,247,247", 0.92f);
                                if (l > 100 && y_grade == 2) {
                                    mFairy.onTap(grage.x + 50, grage.y + 75, grage.x + 55, grage.y + 76, "升级", 2500);
                                    break;
                                } else {
                                    if (y_grade == 2) {
                                        y_grade = 3;
                                        break;
                                    }
                                }

                                l = mFairy.getColorNum(grage.x + 165, grage.y + 105, grage.x + 294, grage.y + 140, "247,247,247", 0.92f);
                                if (l > 100 && y_grade == 3) {
                                    mFairy.onTap(grage.x + 50, grage.y + 110, grage.x + 55, grage.y + 111, "升级", 2500);
                                    break;
                                }

                                if (frequencyMap("grade_err", 5)) {
                                    mFairy.onTap(570, 31, 584, 51, "", 500);
                                    setTaskEnd();
                                    return;
                                }
                            }

                            result = mFairy.findPic(282, 473, 1000, 693, new String[]{"g4.png", "chongjian.png"});
                            mFairy.onTap(0.8f, result, "升级", 300);

                        } else {
                            mFairy.ranSwipe(723, 223, 723, 413, 500, 500);
                        }
                    }
                }

            }
        };
    }//夜世界

    int y_count = 0;
    int y_num = 1;
    public boolean y_battle_bool = true;

    public void y_battle() throws Exception {
        new sing(mFairy, "战斗") {

            void create() throws Exception {
                y_count = 0;
                y_num = getNumberAssembly(AtFairyConfig.getOption("y_bt"));
            }

            void init() throws Exception {
                gamePublicFuntion.init(true);

                result = mFairy.findPic(128, 504, 425, 665, "js.png");
                mFairy.onTap(0.8f, result, 975, 31, 991, 52, "", 1000);

                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                if (gamePublicFuntion.ysj_home()) {
                    setTaskName(2);
                    return;
                } else {
                    gamePublicFuntion.narrow();
                    Thread.sleep(1000);
                    LtLog.e(mFairy.getLineInfo("滑动找船"));

                    for (int i = 0; i < 2; i++) {
                        mFairy.ranSwipe(800, 437, 481, 145, 200, 1000);
                    }


                    result = mFairy.findPic(100, 329, 617, 611, new String[]{"chuan.png", "chuan2.png"});
                    LtLog.e(mFairy.getLineInfo("船：" + result.sim));
                    mFairy.onTap(0.7f, result, "船", 2000);
                }
            }

            void content_02() throws Exception {
                timeCount(20, 0);
                Thread.sleep(1000);

                result = mFairy.findPic(11, 526, 162, 684, new String[]{"ysj.png", "ysj0.png"});
                mFairy.onTap(0.8f, result, "进攻", 1000);

                result = mFairy.findPic("ysj1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "立即寻找", 1000);
                    if (mapCount(0.8f, 602, 93, 832, 312, "ysj11.png")) {
                        mFairy.onTap(42, 514, 61, 532, "兵没有满，去训练兵！", 1000);
                        return;
                    } else {
                        Thread.sleep(3000);
                    }
                }

                result = mFairy.findPic("ysj12.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    for (int i = 0; i < 5; i++) {
                        mFairy.onTap(163, 541, 217, 591, "", 200);
                    }
                    mFairy.onTap(1134, 60, 1154, 81, "", 500);
                    return;
                }

                result = mFairy.findPic("ysj14.png");
                mFairy.onTap(0.8f, result, "确定", 1000);

                result = mFairy.findPic("ysj3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (timeMap("pp", 10000, false)) {
                        result = mFairy.findPic("ysj3.png");
                        mFairy.onTap(0.8f, result, "取消", 1000);
                    }

                }
                result = mFairy.findPic("ysj5.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("ysj7.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("ysj9.png");
                if (result.sim > 0.8f) {

                    long sum = mFairy.getColorNum(591, 581, 1199, 630, "200,64,254", 0.9f);
                    if (sum > 20) {
                        for (int i = 0; i < 10; i++) {//27,596,51,611
                            long color = mFairy.getColorNum(650 + (40 * i), 596, 655 + (40 * i), 611, "200,64,254", 0.9f);
                            if (color > 20) {
                                mFairy.onTap(650 + (40 * i), 596, 655 + (40 * i), 611, "【发现其他部队】", 200);
                                i--;
                                mFairy.tap(301, 263);
                                mFairy.tap(380, 326);
                                mFairy.tap(513, 417);
                                mFairy.tap(212, 184);
                                mFairy.tap(630, 503);
                            }
                        }
                    }

                    err = 0;
                }

                result = mFairy.findPic(10, 541, 784, 708, new String[]{"hui.png", "hui1.png"});
                mFairy.onTap(0.75f, result, "回营", 3000);

                FindResult result1 = mFairy.findPic("ysj6.png");
                if (result1.sim > 0.8f) {

                    result = mFairy.findPic("ysj10.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(66, 628, 88, 659, "已赢得全部的战利品,end!", 500);
                        y_battle_bool = false;
                        setTaskEnd();
                        return;
                    }
                    mFairy.onTap(0.8f, result1, "结果-确定", 3000);
                    y_count++;
                    if (y_count >= y_num) {
                        mFairy.onTap(66, 628, 88, 659, "达到次数要求,end!", 500);
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(new String[]{"ysj2.png", "ysj13.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    gamePublicFuntion.narrow();

                    mFairy.ranSwipe(256, 316, 576, 181, 200, 100);
                    mFairy.ranSwipe(256, 316, 576, 181, 200, 100);

                    List<int[]> list = new ArrayList<>();

                    for (int i = 0; i < 10; i++) {
                        list.add(new int[]{496 - (DE_X * i), 491 - (DE_Y * i)});
                    }

                    Thread.sleep(2000);

                    Mat new_screen = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1);

                    Mat bz_screen = binaryzationMat(new_screen, new int[]{50, 65, 160}, new int[]{90, 105, 190});

                    for (int i = 0; i < list.size(); i++) {
                        long n = gamePublicFuntion.getColorNum(bz_screen, list.get(i)[0], list.get(i)[1] - 10, list.get(i)[0] + 30, list.get(i)[1] + 10, false);
                        if (n == 0) {
                            list.set(i, new int[]{list.get(i)[0] + 29, list.get(i)[1]});
                            if (list.get(i)[0] > 1200) {
                                list.remove(i);
                            }
                            i--;
                        }
                    }

                    for (int i = 0; i < 20; i++) {//27,596,51,611
                        long color = mFairy.getColorNum(27 + (40 * i), 596, 51 + (40 * i), 611, "58,118,255", 0.9f);
                        long color1 = mFairy.getColorNum(27 + (40 * i), 596, 51 + (40 * i), 611, "200,69,255", 0.9f);
                        if (color > 20 || color1 > 20) {
                            mFairy.onTap(27 + (40 * i), 596, 51 + (40 * i), 611, "【发现其他部队】", 200);
                            i--;
                            for (int j = 0; j < list.size(); j++) {
                                mFairy.tap(list.get(j)[0], list.get(j)[1]);
                            }
                        }
                    }

                    result = mFairy.findPic(108, 564, 1260, 710, new String[]{"yb.png", "yb1.png", "yb2.png"});
                    if (result.sim > 0.88f) {
                        mFairy.onTap(0.88f, result, "点击机器人", 1000);
                        for (int j = 0; j < list.size(); j++) {
                            mFairy.tap(list.get(j)[0], list.get(j)[1]);
                        }
                    }


                }
            }
        };
    }//进攻

    int jb = 1;
    int count_jb = 0;

    public void jb() throws Exception {
        new TaskContent(mFairy, "降杯") {

            void create() throws Exception {
                count_jb = 0;
                jb = getNumberAssembly(AtFairyConfig.getOption("jb"));
            }

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void inOperation() throws Exception {
                result = mFairy.findPic("chonglian.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "重连", 1000);
                    setTaskName(0);
                    return;
                }
            }

            void content_01() throws Exception {
                setTaskName(3);
            }

            void content_02() throws Exception {
                if (timeCount(10, 2)) {
                    gamePublicFuntion.init(false);
                }

                result = mFairy.findPic("xun1.png");
                mFairy.onTap(0.8f, result, "训练按钮", 2000);

                result = mFairy.findPic(114, 23, 301, 94, new String[]{"xun2.png", "xun3.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    /**
                     *  概括 */
                    result = mFairy.findPic(112, 96, 194, 135, "budui1.png");
                    if (result.sim > 0.8f) {

                        LtLog.e(mFairy.getLineInfo("概括界面"));

                        leftTroops = gamePublicFuntion.get_number(0.85f, 180, 100, 193, 130, 4, 10, 25, "m", "budui.png");

                        if (leftTroops > 0) {
                            setTaskName(3);
                            gamePublicFuntion.init(false);
                        } else {
                            mFairy.onTap(346, 36, 394, 47, "概括分析完毕", 500);
                        }
                        return;
                    }

                    /**
                     * 训练部队
                     */
                    result = mFairy.findPic("budui2.png");
                    if (result.sim > 0.8f) {

                        LtLog.e(mFairy.getLineInfo("训练界面"));

                        for (int i = 0; i < jb; i++) {
                            mFairy.tap(108, 554);
                        }

                        gamePublicFuntion.close();

                        setTaskName(3);
                        return;
                    }
                }
            }

            void content_03() throws Exception {
                Thread.sleep(500);
                if (timeCount(20, 3)) {
                    gamePublicFuntion.init(false);
                }


                result = mFairy.findPic(new String[]{"sou.png", "sou1.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "搜索对手", 2000);

                    if (mapCount(0.8f, 634, 60, 749, 279, "xiuxi.png")) {
                        setTaskEnd();
                        return;
                    }
                    if (mapCount(0.8f, 430, 95, 573, 315, "sousuo1.png")) {
                        setTaskEnd();
                        return;
                    }

                    if (mapCount(0.8f, 492, 41, 679, 378, "jb1.png")) {
                        setTaskName(2);
                        gamePublicFuntion.init(false);
                        return;
                    }

                    result = mFairy.findPic("lianjie.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(328, 451, 359, 467, "", 500);
                        setTaskName(0);
                        return;
                    }
                } else {
                    result = mFairy.findPic(new String[]{"home.png", "home1.png"});
                    mFairy.onTap(0.8f, result, "进攻", 1000);
                }

                result = mFairy.findPic("sousuo.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("battle1.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(500);
                    err = 0;

                    gamePublicFuntion.narrow();
                    Thread.sleep(100);
                    mFairy.ranSwipe(748, 200, 279, 460, 200, 300);

                    result = mFairy.findPic(88, 569, 1269, 714, new String[]{"w1.png", "w3.png", "w5.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "发现 王", 500);

                    } else {
                        for (int i = 0; i < 15; i++) {
                            long color = mFairy.getColorNum(27 + (40 * i), 596, 51 + (40 * i), 611, "75,144,208", 0.92f);
                            if (color > 20) {
                                mFairy.onTap(27 + (40 * i), 596, 51 + (40 * i), 611, "【发现其他部队】", 500);
                                break;
                            }

                            if (i == 9) {
                                setTaskName(2);
                                gamePublicFuntion.init(false);
                                return;
                            }
                        }
                    }

                    mFairy.onTap(1150, 435, 1170, 446, "", 2000);

                    count_jb++;

                    if (count_jb >= jb) {
                        gamePublicFuntion.init(false);
                        setTaskEnd();
                        return;
                    }

                    setTaskName(0);
                    return;
                }
            }

        }
        ;
    }//降杯

    private static class clickTask implements Runnable {
        private final long count;
        private final CountDownLatch latch;
        private AtFairyImpl mFairy;
        private List<int[]> list;

        private clickTask(long count, List<int[]> list, AtFairyImpl mFairy, CountDownLatch latch) {
            this.count = count;
            this.latch = latch;
            this.mFairy = mFairy;
            this.list = list;
        }

        public void run() {
            try {
                latch.countDown();
                latch.await();

                for (int j = 0; j < count; j++) {
                    mFairy.onTap(list.get(j)[0], list.get(j)[1], list.get(j)[0] + 1, list.get(j)[1] + 1, "", 10);
                }
                Thread.sleep(10);
            } catch (Exception e) {

            }
        }
    }


    Map<String, Integer> mXLB;
    Map<String, Integer> mXLFS;
    List<Integer> cjbList;

    public void wxBattle() throws Exception {
        new TaskContent(mFairy, "无限战斗") {

            void create() throws Exception {
                super.create();
                count_end = 0;
                num_xiuxi = 10;
                y_battle_bool = true;
                end_num = 10;

                mXLB = new HashMap();
                mXLFS = new HashMap();
                cjbList = new ArrayList<>();

                xunLian1 = false;
                xunLian2 = false;
                xunLian3 = false;
                xunLian4 = false;
                cxunCount = 0;
                if (getNumberAssembly(AtFairyConfig.getOption("count_xiuxi")) != -1 && (AtFairyConfig.getOption("7664").equals("1"))) {
                    num_xiuxi = getNumberAssembly(AtFairyConfig.getOption("count_xiuxi"));
                }

                if (getNumberAssembly(AtFairyConfig.getOption("end_miao")) != -1 && (AtFairyConfig.getOption("7658").equals("1"))) {
                    end_num = getNumberAssembly(AtFairyConfig.getOption("end_miao"));
                }


                if (AtFairyConfig.getOption("custom").equals("1")) {
                    xunLianSwitch = true;
                }

                for (int i = 1; i <= 8; i++) {
                    if (!AtFairyConfig.getOption("xlbName" + i).equals("") && getNumberAssembly(AtFairyConfig.getOption("xlbNum" + i)) != -1) {
                        mXLB.put(AtFairyConfig.getOption("xlbName" + i), getNumberAssembly(AtFairyConfig.getOption("xlbNum" + i)));
                    }
                }

                for (int i = 1; i <= 3; i++) {
                    if (!AtFairyConfig.getOption("fsName" + i).equals("") && getNumberAssembly(AtFairyConfig.getOption("fsNum" + i)) != -1) {
                        mXLFS.put(AtFairyConfig.getOption("fsName" + i), getNumberAssembly(AtFairyConfig.getOption("fsNum" + i)));
                    }
                }

                for (int i = 1; i <= 14; i++) {
                    if (AtFairyConfig.getOption("cjb" + i).equals("1")) {
                        cjbList.add(i);
                        if (cjbList.size() == 2) {
                            break;
                        }
                    }
                }


            }

            void init() throws Exception {
                gamePublicFuntion.init(false);
                gamePublicFuntion.reset();
                setTaskName(1);
                count_xiuxi = 0;
                bool_battle = false;
            }

            void inOperation() throws Exception {
                result = mFairy.findPic("chonglian.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "重连", 1000);
                    setTaskName(0);
                    free = getTimeStamp(AtFairyConfig.getOption("count_time"));
                    return;
                }
            }

            void content_01() throws Exception {

               /* if (onceJudge("ling")) {
                    if (AtFairyConfig.getOption("lcj").equals("1")) {
                        ling();//领奖励
                    }
                }*/

                if (timeMap("yjs_time", 10800000, true)) {
                    if (AtFairyConfig.getOption("yjs").equals("1")) {
                        yjs();//研究所
                    }
                }

                if (cjbList.size() != 0) {
                    cjb();
                }

                if (timeMap("jz_time", 3610000, true)) {
                    for (int i = 1; i <= 4; i++) {
                        if (AtFairyConfig.getOption("js" + i).equals("1")) {
                            js();
                            break;
                        }
                    }
                }

                if (timeMap("jz_time", 86410000, true)) {
                    for (int i = 5; i <= 7; i++) {
                        if (AtFairyConfig.getOption("js" + i).equals("1")) {
                            js();
                            break;
                        }
                    }
                }

                if (AtFairyConfig.getOption("szy").equals("1")) {
                    szy();//收资源
                }

                if (timeMap("juan_bing", 1800000, true)) {
                    if (AtFairyConfig.getOption("juan").equals("1") || AtFairyConfig.getOption("qiu").equals("1")) {
                        zeng();//捐兵
                        gamePublicFuntion.init(false);
                    }
                }

                result = mFairy.findPic(624, 20, 660, 63, "grade1.png");
                if (result.sim < 0.8f) {
                    if (!AtFairyConfig.getOption("grade").equals("") && AtFairyConfig.getOption("7662").equals("1")) {

                        if (AtFairyConfig.getOption("grade").equals("2")) {
                            result = mFairy.findPic("grade3.png");
                            if (result.sim < 0.8f) {
                                grade();//升级建筑
                            }
                        } else {
                            grade();//升级建筑
                        }
                    }

                    if (AtFairyConfig.getOption("7642").equals("1") && (AtFairyConfig.getOption("qiang1").equals("1") || AtFairyConfig.getOption("qiang2").equals("2"))) {
                        qiang();
                    }

                    if (AtFairyConfig.getOption("ql").equals("1")) {
                        ql();//清理杂草
                    }
                }

                long ysj_time = getTimeStamp(AtFairyConfig.getOption("ysj_time"));
                if (timeMap("ysj", ysj_time == -1 ? 3600000 : ysj_time, true)) {
                    if (AtFairyConfig.getOption("7668").equals("1")) {
                        ysj();//夜世界
                    }
                }

                if (AtFairyConfig.getOption("nob").equals("1")) {
                    if (timeMap("nob", 60000, true)) {
                        LtLog.e(mFairy.getLineInfo("用户勾选不战斗"));
                    }
                    Thread.sleep(1000);
                } else {

                    LtLog.e(mFairy.getLineInfo("等待时间为：" + free));

                    if (timeMap("free", free, true)) {
                        gamePublicFuntion.init(false);
                        setTaskName(2);
                        return;
                    }
                }
            }

            void content_02() throws Exception {
                if (timeCount(10, 2)) {
                    gamePublicFuntion.init(false);

                    if (bool_battle) {
                        jb_bool = true;
                        setTaskName(3);
                    } else {

                        /**
                         * 兵力不足,回到主场景等待30秒
                         */

                        timeInit("free");

                        if (jb_bool) {
                            if (getNumberAssembly(AtFairyConfig.getOption("jb")) != -1) {
                                jb();
                            }
                            jb_bool = false;
                        }

                        if (ybt_bool && y_battle_bool) {
                            if (getNumberAssembly(AtFairyConfig.getOption("y_bt")) != -1) {
                                y_battle();
                            }
                            ybt_bool = false;
                        }

                        free = 30000;
                        setTaskName(0);
                    }
                }


                result = mFairy.findPic(114, 23, 301, 94, new String[]{"xun2.png", "xun3.png"});
                if (result.sim > 0.8f) {

                    /**
                     *  概括 */

                    //boolean berr = false;


                    result = mFairy.findPic("cxunlian1.png");
                    if (result.sim < 0.85f && AtFairyConfig.getOption("cxunlian1").equals("1")) {
                        xunLian1 = true;
                    }
                    result = mFairy.findPic("cxunlian2.png");
                    if (result.sim < 0.85f && AtFairyConfig.getOption("cxunlian2").equals("1")) {
                        xunLian2 = true;
                    }
                    result = mFairy.findPic("cxunlian3.png");
                    if (result.sim < 0.85f && AtFairyConfig.getOption("cxunlian3").equals("1")) {
                        xunLian3 = true;
                    }
                    result = mFairy.findPic("cxunlian4.png");
                    if (result.sim < 0.85f && AtFairyConfig.getOption("cxunlian4").equals("1")) {
                        xunLian4 = true;
                    }


                    int user_bl = getNumberAssembly(AtFairyConfig.getOption("user_bl"));

                    result = mFairy.findPic(112, 96, 194, 135, "budui1.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        LtLog.e(mFairy.getLineInfo("概括界面"));

                        leftTroops = gamePublicFuntion.get_number(0.85f, 170, 100, 183, 130, 2, 10, 25, "m", "budui.png");

                        if (user_bl != -1) {
                            rightTroops = user_bl;
                        } else {
                            result = mFairy.findPic(195, 100, 258, 126, "budui.png");
                            if (result.sim > 0.9f) {
                                rightTroops = gamePublicFuntion.get_number(0.85f, result.x + (result.width / 2), 100, result.x + result.width + 6, 130, 2, 10, 25, "m", null);
                            }
                        }


                        LtLog.e(mFairy.getLineInfo("当前兵力: " + leftTroops + "/" + rightTroops));

                        if (leftTroops > 1000 && rightTroops > 1000) {
                            LtLog.e(mFairy.getLineInfo("数字验证异常"));
                            LtLog.e(mFairy.getLineInfo("数字验证异常"));
                            LtLog.e(mFairy.getLineInfo("数字验证异常"));
                            return;
                        }


                        double c = rightTroops;
                        double num = leftTroops / c;

                        switch (AtFairyConfig.getOption("bl")) {
                            case "1":
                                if (num >= 0.8) {
                                    bool_battle = true;
                                }
                                break;
                            case "2":
                                if (num >= 0.85) {
                                    bool_battle = true;
                                }
                                break;
                            case "3":
                                if (num >= 0.9) {
                                    bool_battle = true;
                                }
                                break;
                            case "4":
                                if (num >= 0.95) {
                                    bool_battle = true;
                                }
                                break;
                            case "5":
                                if (num >= 1) {
                                    bool_battle = true;
                                }
                                break;
                            default:
                                if (num >= 0.9) {
                                    bool_battle = true;
                                }
                                break;
                        }

                        mFairy.onTap(346, 36, 394, 47, "概括分析完毕", 500);
                        return;
                    }

                    /**
                     * 训练部队
                     */
                    result = mFairy.findPic(22, 39, 149, 134, "budui2.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        LtLog.e(mFairy.getLineInfo("训练界面"));

                        if (AtFairyConfig.getOption("jss1").equals("1")) {

                            result = mFairy.findPic("huifu.png");
                            mFairy.onTap(0.8f, result, "恢复", 500);

                            result = mFairy.findPic("tisu.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "兵营加速", 500);
                                mFairy.onTap(638, 479, 656, 495, "", 500);
                            }
                        }


                        int new_leftTroops = gamePublicFuntion.get_number(0.8f, 132, 87, 141, 120, 4, 13, 60, "k", "bl1.png");
                        int new_rightTroops = 0;

                        if (user_bl != -1) {
                            new_rightTroops = user_bl * 2;
                        } else {
                            result = mFairy.findPic(156, 91, 209, 116, "bl1.png");
                            if (result.sim > 0.9f) {
                                new_rightTroops = gamePublicFuntion.get_number(0.85f, result.x + result.width, 90, result.x + (result.width * 2), 120, 3, 13, 30, "k", null);
                            }
                        }

                        LtLog.e(mFairy.getLineInfo("训练兵力: " + new_leftTroops + "/" + new_rightTroops));


                        if (new_rightTroops > 1000 && new_leftTroops > 1000) {
                            LtLog.e(mFairy.getLineInfo("数字验证异常"));
                            LtLog.e(mFairy.getLineInfo("数字验证异常"));
                            LtLog.e(mFairy.getLineInfo("数字验证异常"));
                            return;
                        }

                        if (new_rightTroops == new_leftTroops) {
                            mFairy.onTap(565, 35, 635, 52, "部队训练完毕", 500);
                            return;
                        }


                        /**
                         * 用户自定义训练
                         */

                        FindResult manren = mFairy.findPic(55, 361, 1229, 677, "bz1.png");
                        FindResult juren = mFairy.findPic(55, 361, 1229, 677, "bz2.png");
                        FindResult gongjian = mFairy.findPic(55, 361, 1229, 677, "bz10.png");


                        int t = rightTroops - leftTroops;
                        if (new_leftTroops < rightTroops) {

                            if (t > 0) {
                                if (xunLianSwitch && xunLian1 == false && xunLian2 == false && xunLian3 == false && xunLian4 == false) {

                                    customXLB();

                                    mFairy.ranSwipe(221, 511, 1026, 511, 500, 1000);

                                    result = mFairy.findPic(47, 187, 1223, 252, "custom1.png");
                                    if (result.sim < 0.8f) {

                                        for (int i = 0; i < 300; i++) {

                                            if (gamePublicFuntion.judge_zy()) {
                                                setTaskEnd();
                                                return;
                                            }

                                            if (gamePublicFuntion.judge_js()) {
                                                break;
                                            }

                                            result = mFairy.findPic(47, 187, 1223, 252, "custom1.png");
                                            if (result.sim > 0.8f) {
                                                break;
                                            } else {
                                                if (i % 3 == 0) {
                                                    mFairy.tap(juren.x, juren.y);
                                                } else {
                                                    mFairy.tap(gongjian.x, gongjian.y);
                                                }
                                                Thread.sleep(100);
                                            }
                                        }
                                    }


                                } else {


                                    LtLog.e(mFairy.getLineInfo("训练野蛮人：" + Math.ceil(Math.abs(t) * 0.15)));

                                    if (gamePublicFuntion.judge_zy()) {
                                        setTaskEnd();
                                        return;
                                    }

                                    for (int i = 0; i < Math.ceil(Math.abs(t) * 0.15); i++) {//计算兵力的20%来训练野蛮人
                                        mFairy.tap(manren.x, manren.y);
                                    }


                                    LtLog.e(mFairy.getLineInfo("训练巨人：" + Math.ceil(Math.abs(t) * 0.4 / 5)));

                                    if (gamePublicFuntion.judge_zy()) {
                                        setTaskEnd();
                                        return;
                                    }
                                    for (int i = 0; i < Math.ceil(Math.abs(t) * 0.4 / 5); i++) {//计算兵力的40%来训练巨人
                                        mFairy.tap(juren.x, juren.y);
                                    }


                                    LtLog.e(mFairy.getLineInfo("训练弓箭手：" + Math.ceil(Math.abs(t) * 0.55)));

                                    if (gamePublicFuntion.judge_zy()) {
                                        setTaskEnd();
                                        return;
                                    }

                                    for (int i = 0; i < Math.ceil(Math.abs(t) * 0.55); i++) {//计算兵力的50%来训练弓箭手
                                        mFairy.tap(gongjian.x, gongjian.y);
                                    }
                                }
                                Thread.sleep(500);
                            }
                            new_leftTroops = rightTroops;
                        }


                        if (xunLianSwitch && xunLian1 == false && xunLian2 == false && xunLian3 == false && xunLian4 == false) {

                            customXLB();

                            mFairy.ranSwipe(221, 511, 1026, 511, 500, 1000);

                            if (gamePublicFuntion.judge_zy()) {
                                setTaskEnd();
                                return;
                            }

                            for (int i = 0; i < 30; i++) {

                                if (gamePublicFuntion.judge_js()) {
                                    break;
                                }


                                mFairy.tap(juren.x, juren.y);
                                mFairy.tap(gongjian.x, gongjian.y);
                                mFairy.tap(gongjian.x, gongjian.y);
                                mFairy.tap(gongjian.x, gongjian.y);
                                mFairy.tap(gongjian.x, gongjian.y);
                                mFairy.tap(gongjian.x, gongjian.y);
                                Thread.sleep(100);
                            }

                          /*  mFairy.touchDown(132, 437);//野蛮人
                            Thread.sleep(5000);
                            mFairy.touchUp();*/
                        } else {

                            t = new_rightTroops - new_leftTroops;


                            LtLog.e(mFairy.getLineInfo("训练野蛮人：" + Math.ceil(Math.abs(t) * 0.2)));
                            if (gamePublicFuntion.judge_zy()) {
                                setTaskEnd();
                                return;
                            }

                            for (int i = 0; i < Math.ceil(Math.abs(t) * 0.15); i++) {//计算兵力的20%来训练野蛮人
                                mFairy.tap(manren.x, manren.y);
                            }


                            LtLog.e(mFairy.getLineInfo("训练巨人：" + Math.ceil(Math.abs(t) * 0.4 / 5)));

                            if (gamePublicFuntion.judge_zy()) {
                                setTaskEnd();
                                return;
                            }

                            for (int i = 0; i < Math.ceil(Math.abs(t) * 0.4 / 5); i++) {//计算兵力的30%来训练巨人
                                mFairy.tap(juren.x, juren.y);
                            }


                            if (gamePublicFuntion.judge_zy()) {
                                setTaskEnd();
                                return;
                            }
                            mFairy.touchDown(gongjian.x, gongjian.y);//弓箭手
                            Thread.sleep(5000);
                            mFairy.touchUp();
                        }


                        mFairy.onTap(565, 35, 635, 52, "部队训练完毕", 500);
                        return;
                    }

                    /**
                     * 配置法术
                     */

                  /*  result = mFairy.findPic(10, new String[]{"fashu.png",""});
                    if (result.sim > 0.8f) {

                    }*/

                    result = mFairy.findPic(78, 64, 176, 143, "budui3.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        LtLog.e(mFairy.getLineInfo("法术界面"));

                        if (AtFairyConfig.getOption("jss2").equals("1")) {

                            result = mFairy.findPic("huifu.png");
                            mFairy.onTap(0.8f, result, "恢复", 500);

                            result = mFairy.findPic("tisu.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "法术加速", 500);
                                mFairy.onTap(638, 479, 656, 495, "", 500);
                            }
                        }

                        mFairy.touchDown(132, 445);//闪电
                        Thread.sleep(2000);
                        mFairy.touchUp();

                        mFairy.onTap(1146, 41, 1157, 54, "", 500);

                        if (bool_battle) {
                            jb_bool = true;
                            ybt_bool = true;
                            setTaskName(3);
                        } else {
                            /**
                             * 兵力不足,回到主场景等待30秒
                             */
                            timeInit("free");
                            if (jb_bool) {
                                if (getNumberAssembly(AtFairyConfig.getOption("jb")) != -1) {
                                    jb();
                                }
                                jb_bool = false;
                            }
                            if (ybt_bool && y_battle_bool) {
                                if (getNumberAssembly(AtFairyConfig.getOption("y_bt")) != -1) {
                                    y_battle();
                                }
                                ybt_bool = false;
                            }
                            free = 30000;
                            setTaskName(0);
                        }
                        return;
                    }
                } else {
                    result = mFairy.findPic("xun1.png");
                    mFairy.onTap(0.8f, result, "训练按钮", 3000);
                }
            }

            void content_03() throws Exception {
                Thread.sleep(500);
                if (timeCount(20, 3)) {
                    gamePublicFuntion.init(false);
                }


                result = mFairy.findPic(new String[]{"sou.png", "sou1.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "搜索对手", 1000);

                    if (mapCount(0.8f, 634, 60, 749, 279, "xiuxi.png")) {
                        setTaskName(0);
                        free = getTimeStamp(AtFairyConfig.getOption("count_time"));
                        return;
                    }

                    if (mapCount(0.8f, 430, 95, 573, 315, "sousuo1.png")) {
                        setTaskName(0);
                        free = getTimeStamp(AtFairyConfig.getOption("count_time"));
                        return;
                    }


                    result = mFairy.findPic("lianjie.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(328, 451, 359, 467, "", 500);
                        setTaskName(0);
                        free = getTimeStamp(AtFairyConfig.getOption("count_time"));
                        return;
                    }
                } else {
                    result = mFairy.findPic(new String[]{"home.png", "home1.png"});
                    mFairy.onTap(0.8f, result, "进攻", 1000);
                }

                result = mFairy.findPic(330, 230, 914, 472, "zybz.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(891, 210, 911, 230, "", 500);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("sousuo.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("battle1.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(1000);
                    err = 0;
                    sy_battle = false;
                    zy_bool = false;
                    man = false;

                    list_up_left = new ArrayList();
                    list_up_right = new ArrayList();
                    list_down_left = new ArrayList();
                    list_down_right = new ArrayList();

                    screen_up_screen = null;
                    screen_down_screen = null;

                    for (int i = 0; i < 10; i++) {
                        list_up_left.add(new int[]{106 + (DE_X * i), 401 - (DE_Y * i)});
                        list_up_right.add(new int[]{1098 - (DE_X * i), 430 - (DE_Y * i)});
                        list_down_left.add(new int[]{496 - (DE_X * i), 491 - (DE_Y * i)});
                        list_down_right.add(new int[]{687 + (DE_X * i), 507 - (DE_Y * i)});
                    }

                    cjq_a = 0;
                    cjq_b = 0;
                    cjq_c = 0;
                    cjq_d = 0;

                    //死鱼判断 1.无奖杯 2.半满采集器外置 3.资源满足就打
                    if (AtFairyConfig.getOption("ct").equals("2")) {

                        LtLog.e(mFairy.getLineInfo("用户开启：无奖杯"));

                        long number = mFairy.getColorNum(14, 15, 58, 65, "96,100,97", 0.92f);
                        if (number > 80) {
                            LtLog.e(mFairy.getLineInfo("检测到没有奖杯!"));
                            sy_battle = true;
                        } else {
                            xia();
                            return;
                        }
                    }

                    //启动资源判断线程
                    zy_judge zy = new zy_judge();
                    hx_up_judge hx_up = new hx_up_judge();
                    hx_down_judge hx_down = new hx_down_judge();

                    zy.start();

                    gamePublicFuntion.narrow();

                    leftSlide();
                    while (true) {
                        screen_up_screen = mFairy.capture();
                        if (screen_up_screen != null && screen_up_screen.raw != null) {
                            break;
                        }
                        Thread.sleep(200);
                    }
                    rightSlide();
                    while (true) {
                        screen_down_screen = mFairy.capture();
                        if (screen_down_screen != null && screen_down_screen.raw != null) {
                            break;
                        }
                        Thread.sleep(200);
                    }
                    hx_up.start();
                    hx_down.start();

                    zy.join();
                    /**
                     * 资源判断
                     */

                    if (zy_bool) {
                        hx_up.join();
                        hx_down.join();
                    } else {
                        LtLog.e("【资源没有达到要求】");
                        hx_up.interrupt();
                        hx_down.interrupt();
                        xia();
                        return;
                    }


                    result = mFairy.findPic("battle1.png");
                    if (result.sim < 0.8f) {
                        return;
                    }

                    if (man) {
                        LtLog.e(mFairy.getLineInfo("man"));
                        xia();
                        return;
                    }

                    if (AtFairyConfig.getOption("ct").equals("1")) {
                        LtLog.e(mFairy.getLineInfo("用户开启：采集器外置"));

                        if (cjq_a + cjq_b + cjq_c + cjq_d >= 2) {
                            LtLog.e(mFairy.getLineInfo("发现大于2个外置采集器"));
                            LtLog.e(mFairy.getLineInfo("上左：" + cjq_a));
                            LtLog.e(mFairy.getLineInfo("上右：" + cjq_b));
                            LtLog.e(mFairy.getLineInfo("下左：" + cjq_c));
                            LtLog.e(mFairy.getLineInfo("下右：" + cjq_d));
                            sy_battle = true;
                        }
                    } else if (AtFairyConfig.getOption("ct").equals("2")) {

                    } else {
                        LtLog.e(mFairy.getLineInfo("用户开启：资源够就打"));
                        cjq_c++;
                        sy_battle = true;
                    }

                    if (sy_battle) {

                        LtLog.e(mFairy.getLineInfo("开始出兵"));
                        count_xiuxi = 0;
                        List<int[]> list = pos(cjq_a, cjq_b, cjq_c, cjq_d);
                        //resethxlist();
                        switch (list.get(0)[0]) {
                            case 1:
                            case 2:
                                leftSlide();

                                battle(list_up_left, list_up_right);

                                rightSlide();
                                battle(list_down_left, list_down_right);
                                chu(list_down_left);


                                break;
                            case 3:
                            case 4:
                                rightSlide();
                                battle(list_down_left, list_down_right);

                                leftSlide();
                                battle(list_up_left, list_up_right);
                                chu(list_up_left);
                                break;
                        }

                        /**
                         * 判断
                         */

                        if (AtFairyConfig.getOption("wang1").equals("1") /*&& wang1 == 0*/) {
                            result = mFairy.findPic(88, 569, 1269, 714, new String[]{"w2.png"/*, "w2.png"*/});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "", 500);
                            }
                        }

                        if (AtFairyConfig.getOption("wang2").equals("1")/* && wang2 == 0*/) {
                            result = mFairy.findPic(88, 569, 1269, 714, new String[]{"w4.png"/*, "w4.png"*/});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "女王", 500);
                            }
                        }

                        if (AtFairyConfig.getOption("wang3").equals("1")/* && wang3 == 0*/) {
                            result = mFairy.findPic(88, 569, 1269, 714, new String[]{"w5.png"/*, "w6.png"*/});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "守护", 500);
                            }
                        }

                        LtLog.e(mFairy.getLineInfo("放兵结束>>>"));

                        sdt1 st1 = new sdt1();
                        sdt2 st2 = new sdt2();
                        sdd1 sd1 = new sdd1();
                        sdd2 sd2 = new sdd2();

                        while (mFairy.condit()) {
                            Thread.sleep(2000);
                            long number = mFairy.mMatTime(1078, 30, 163, 107, 0.98f);
                            LtLog.e(mFairy.getLineInfo("【判断金水是否有变化】" + number));
                            if (number > end_num) {
                                result = mFairy.findPic(14, 583, 1271, 710, new String[]{"shandian.png", "shandian1.png"});
                                if (result.sim > 0.92f && (AtFairyConfig.getOption("shanhei").equals("1")) && (AtFairyConfig.getOption("7658").equals("1"))) {
                                    mFairy.onTap(0.8f, result, "闪电", 500);
                                    leftSlide();
                                    st1.start();
                                    st2.start();
                                    st1.join();
                                    st2.join();

                                    rightSlide();
                                    sd1.start();
                                    sd2.start();
                                    sd1.join();
                                    sd2.join();
                                }

                                result = mFairy.findPic(new String[]{"jieshu.png", "jieshu1.png", "fangqi.png"});
                                mFairy.onTap(0.8f, result, "结束战斗", 1000);

                                result = mFairy.findPic(new String[]{"jieshu2.png", "jieshu3.png"});
                                mFairy.onTap(0.8f, result, 758, 459, 798, 472, "确定结束战斗", 1000);

                                break;
                            }

                            result = mFairy.findPic("jieshu1.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "回营", 1000);
                                break;
                            }
                        }

                        free = getTimeStamp(AtFairyConfig.getOption("end_time"));

                        count_end++;

                        cxunCount++;//自定义训练次数判断

                        if (getNumberAssembly(AtFairyConfig.getOption("count_end")) != -1 && (AtFairyConfig.getOption("7664").equals("1"))) {
                            if (count_end >= getNumberAssembly(AtFairyConfig.getOption("count_end"))) {
                                gamePublicFuntion.init(false);
                                setTaskEnd();
                            }
                        }
                        if (getNumberAssembly(AtFairyConfig.getOption("cxunCount")) != -1 && (AtFairyConfig.getOption("8859").equals("1"))) {
                            if (cxunCount >= getNumberAssembly(AtFairyConfig.getOption("cxunCount"))) {
                                xunLianSwitch = false;
                            }
                        }

                        setTaskName(0);

                        return;
                    } else {
                        xia();
                    }
                }
            }

            void customXLB() throws Exception {


                for (String s :
                        mXLB.keySet()) {


                    if (gamePublicFuntion.judge_zy()) {
                        setTaskEnd();
                        return;
                    }

                    /*
                        bz8  龙骑士



                     */


                    for (int o = 0; o < 4; o++) {
                        result = mFairy.findPic(55, 361, 1229, 677, "bz" + s + ".png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mXLB.toString() + ", " + "bz" + s + ".png");
                            for (Integer i = 0; i < mXLB.get(s); i++) {
                                mFairy.tap(result.x, result.y);
                                Thread.sleep(100);
                            }
                            break;
                        } else {
                            if (o % 2 == 0) {
                                mFairy.ranSwipe(221, 511, 1026, 511, 500, 1000);
                            } else {
                                mFairy.ranSwipe(1026, 511, 221, 511, 500, 1000);
                            }
                        }
                    }
                }
            }

            void xia() throws Exception {
                result = widthfindPic(10, "battle2.png");
                if (result.sim > 0.8f) {
                    count_xiuxi++;
                    if (count_xiuxi >= num_xiuxi) {
                        LtLog.e(mFairy.getLineInfo("【已搜索 " + count_xiuxi + "次,达到上限啦!】"));
                        setTaskName(0);
                        free = getTimeStamp(AtFairyConfig.getOption("count_time"));
                        return;
                    }
                    mFairy.onTap(0.8f, result, "下一个,连续搜索 " + count_xiuxi + "次啦!", 2000);

                    result = mFairy.findPic("lianjie.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(328, 451, 359, 467, "", 500);
                        setTaskName(0);
                        free = getTimeStamp(AtFairyConfig.getOption("count_time"));
                        return;
                    }
                } else {
                    result = mFairy.findPic("battle1.png");
                    mFairy.onTap(0.8f, result, "没有发现下一个,结束战斗", 2000);
                }

                result = mFairy.findPic(330, 230, 914, 472, "zybz.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(891, 210, 911, 230, "", 500);
                    setTaskEnd();
                    return;
                }
            }//下一个

            public void chu(List<int[]> lw) throws Exception {

                if (AtFairyConfig.getOption("wang1").equals("1") /*&& wang1 == 0*/) {
                    result = mFairy.findPic(88, 569, 1269, 714, new String[]{"w1.png"/*, "w2.png"*/});
                    if (result.sim > 0.92f) {
                        mFairy.onTap(0.92f, result, "蛮王出战", 200);
                        for (int j = 0; j < lw.size(); j++) {
                            mFairy.tap(lw.get(j)[0], lw.get(j)[1]);
                        }
                    }
                }

                if (AtFairyConfig.getOption("wang2").equals("1")/* && wang2 == 0*/) {
                    result = mFairy.findPic(88, 569, 1269, 714, new String[]{"w3.png"/*, "w4.png"*/});
                    if (result.sim > 0.92f) {
                        mFairy.onTap(0.92f, result, "女王出战", 200);
                        for (int j = 0; j < lw.size(); j++) {
                            mFairy.tap(lw.get(j)[0], lw.get(j)[1]);
                        }
                    }
                }

                if (AtFairyConfig.getOption("wang3").equals("1")/* && wang3 == 0*/) {
                    result = mFairy.findPic(88, 569, 1269, 714, new String[]{"w5.png"/*, "w6.png"*/});
                    if (result.sim > 0.92f) {
                        mFairy.onTap(0.92f, result, "守护出战", 200);
                        for (int j = 0; j < lw.size(); j++) {
                            mFairy.tap(lw.get(j)[0], lw.get(j)[1]);
                        }
                    }
                }


                if (AtFairyConfig.getOption("wang4").equals("1")/* && wang3 == 0*/) {
                    result = mFairy.findPic(88, 569, 1269, 714, new String[]{"w7.png"/*, "w6.png"*/});
                    if (result.sim > 0.92f) {
                        mFairy.onTap(0.92f, result, "战神出战", 200);
                        for (int j = 0; j < lw.size(); j++) {
                            mFairy.tap(lw.get(j)[0], lw.get(j)[1]);
                        }
                    }
                }

                for (int i = 0; i < 30; i++) {
                    //
                    long color = mFairy.getColorNum(20 + (40 * i), 595, 40 + (40 * i), 610, "81,152,216", 0.9f);
                    long color1 = mFairy.getColorNum(20 + (40 * i), 595, 40 + (40 * i), 610, "207,56,61", 0.9f);
                    LtLog.e(mFairy.getLineInfo("color:" + color));
                    LtLog.e(mFairy.getLineInfo("color1:" + color1));
                    if (color > 60 || color1 > 60) {
                        mFairy.onTap(20 + (40 * i), 595, 50 + (40 * i), 610, "【发现其他部队】", 500);
                        i--;
                        for (int j = 0; j < lw.size(); j++) {
                            mFairy.tap(lw.get(j)[0], lw.get(j)[1]);
                        }
                    }
                }


            }

            List<int[]> pos(int a, int b, int c, int d) throws Exception {
                List<int[]> list = new ArrayList<>();
                list.add(new int[]{1, a});
                list.add(new int[]{2, b});
                list.add(new int[]{3, c});
                list.add(new int[]{4, d});

                for (int i = 0; i < list.size(); ) {
                    if (list.get(i)[1] == 0) {
                        list.remove(i);
                    } else {
                        i++;
                    }
                }

                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.size() - i - 1; j++) {
                        if (list.get(j)[1] < list.get(j + 1)[1]) {
                            int[] er = list.get(j);
                            list.set(j, list.get(j + 1));
                            list.set((j + 1), er);
                        }

                    }
                }
                return list;
            }//判断哪边的采集器最多

            void battle(final List<int[]> list_left, final List<int[]> list_right) throws Exception {
                result = mFairy.findPic(88, 569, 1269, 714, new String[]{"juren.png", "juren1.png"});
                if (result.sim > 0.95f) {

                    mFairy.onTap(0.95f, result, "", 100);

                    final int number = (int) Math.ceil(leftTroops * 0.4 / 5 / 4 / 3);
                    LtLog.e(mFairy.getLineInfo("巨人 每个坐标下兵的数量为:" + number));

                    Thread a = new Thread() {
                        public void run() {
                            try {

                                for (int x = 0; x < number; x++) {
                                    mFairy.tap(list_left.get((int) (list_left.size() / 4))[0], list_left.get((int) (list_left.size() / 4))[1]);
                                }

                                if ((leftTroops * 0.4 / 5) >= 8) {
                                    for (int x = 0; x < number; x++) {
                                        mFairy.tap(list_left.get((int) (list_left.size() / 2))[0], list_left.get((int) (list_left.size() / 2))[1]);
                                    }
                                }

                                for (int x = 0; x < number; x++) {
                                    mFairy.tap(list_left.get((int) (list_left.size() / 1.3))[0], list_left.get((int) (list_left.size() / 1.3))[1]);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    Thread b = new Thread() {
                        public void run() {
                            try {
                                for (int x = 0; x < number; x++) {
                                    mFairy.tap(list_right.get((int) (list_right.size() / 4))[0], list_right.get((int) (list_right.size() / 4))[1]);
                                }

                                if ((leftTroops * 0.4 / 5) >= 8) {
                                    for (int x = 0; x < number; x++) {
                                        mFairy.tap(list_right.get((int) (list_right.size() / 2))[0], list_right.get((int) (list_right.size() / 2))[1]);
                                    }
                                }

                                for (int x = 0; x < number; x++) {
                                    mFairy.tap(list_right.get((int) (list_right.size() / 1.3))[0], list_right.get((int) (list_right.size() / 1.3))[1]);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    a.start();
                    b.start();

                    a.join();
                    b.join();
                }

                result = mFairy.findPic(88, 569, 1269, 714, new String[]{"manren.png", "manren1.png"});
                if (result.sim > 0.95f) {
                    mFairy.onTap(0.95f, result, "", 100);

                    final int number1 = (int) Math.ceil(leftTroops * 0.15 / 4 / list_left.size());

                    LtLog.e(mFairy.getLineInfo("左侧 蛮人 每个坐标下兵的数量为:" + number1));

                    final int number2 = (int) Math.ceil(leftTroops * 0.15 / 4 / list_right.size());

                    LtLog.e(mFairy.getLineInfo("右侧 蛮人 每个坐标下兵的数量为:" + number2));

                    Thread a = new Thread() {
                        public void run() {
                            try {
                                for (int i = 0; i < (int) Math.ceil(list_left.size() / 2); i++) {
                                    for (int x = 0; x < number1; x++) {
                                        mFairy.tap(list_left.get(i)[0], list_left.get(i)[1]);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    Thread b = new Thread() {
                        public void run() {
                            try {
                                for (int i = (int) Math.ceil(list_left.size() / 2); i < list_left.size(); i++) {
                                    for (int x = 0; x < number1; x++) {
                                        mFairy.tap(list_left.get(i)[0], list_left.get(i)[1]);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };


                    Thread c = new Thread() {
                        public void run() {
                            try {
                                for (int i = 0; i < (int) Math.ceil(list_right.size()); i++) {
                                    for (int x = 0; x < number2; x++) {
                                        mFairy.tap(list_right.get(i)[0], list_right.get(i)[1]);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    Thread d = new Thread() {
                        public void run() {
                            try {
                                for (int i = (int) Math.ceil(list_right.size()); i < list_right.size(); i++) {
                                    for (int x = 0; x < number2; x++) {
                                        mFairy.tap(list_right.get(i)[0], list_right.get(i)[1]);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    a.start();
                    b.start();
                    c.start();
                    d.start();

                    a.join();
                    b.join();
                    c.join();
                    d.join();
                }

                result = mFairy.findPic(88, 569, 1269, 714, new String[]{"nvren.png", "nvren1.png"});
                if (result.sim > 0.95f) {
                    mFairy.onTap(0.95f, result, "", 100);

                    final int number1 = (int) Math.floor(leftTroops * 0.55 / 4 / list_left.size());

                    LtLog.e(mFairy.getLineInfo("左侧 女人 每个坐标下兵的数量为:" + number1));

                    final int number2 = (int) Math.ceil(leftTroops * 0.55 / 4 / list_right.size());

                    LtLog.e(mFairy.getLineInfo("右侧 女人 每个坐标下兵的数量为:" + number1));

                    Thread a = new Thread() {
                        public void run() {
                            try {
                                for (int i = 0; i < (int) Math.ceil(list_left.size() / 2); i++) {
                                    for (int x = 0; x < number1; x++) {
                                        mFairy.tap(list_left.get(i)[0], list_left.get(i)[1]);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    Thread b = new Thread() {
                        public void run() {
                            try {
                                for (int i = (int) Math.ceil(list_left.size() / 2); i < list_left.size(); i++) {
                                    for (int x = 0; x < number1; x++) {
                                        mFairy.tap(list_left.get(i)[0], list_left.get(i)[1]);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };


                    Thread c = new Thread() {
                        public void run() {
                            try {
                                for (int i = 0; i < (int) Math.ceil(list_right.size()); i++) {
                                    for (int x = 0; x < number2; x++) {
                                        mFairy.tap(list_right.get(i)[0], list_right.get(i)[1]);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    Thread d = new Thread() {
                        public void run() {
                            try {
                                for (int i = (int) Math.ceil(list_right.size()); i < list_right.size(); i++) {
                                    for (int x = 0; x < number2; x++) {
                                        mFairy.tap(list_right.get(i)[0], list_right.get(i)[1]);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    a.start();
                    b.start();
                    c.start();
                    d.start();

                    a.join();
                    b.join();
                    c.join();
                    d.join();
                }
            }//战斗下兵

            void leftSlide() throws Exception {
                Thread.sleep(100);
                mFairy.ranSwipe(748, 200, 279, 460, 200, 10);
                //mFairy.ranSwipe(748, 200, 279, 460, 200, 10);
                Thread.sleep(200);

            }//左边

            void rightSlide() throws Exception {
                Thread.sleep(100);
                mFairy.ranSwipe(824, 437, 481, 145, 200, 10);
                //mFairy.ranSwipe(824, 437, 481, 145, 200, 10);
                Thread.sleep(200);
            }//左边

            void resethxlist() throws Exception {
                /**
                 *  判断红线的范围  */

                List<int[]> new_list_up_right = list_up_right;

                for (int i = 0; i < list_up_left.size(); i++) {
                    for (int j = 0; j < list_up_right.size(); j++) {
                        if (list_up_left.get(i)[0] > list_up_right.get(j)[0]) {
                            LtLog.e(mFairy.getLineInfo("remove list_up_right :" + list_up_right.get(j)[0] + "," + list_up_right.get(j)[1]));
                            list_up_right.remove(j);
                            j--;
                        }
                    }
                }

                for (int i = 0; i < new_list_up_right.size(); i++) {
                    for (int j = 0; j < list_up_left.size(); j++) {
                        if (new_list_up_right.get(i)[0] < list_up_left.get(j)[0]) {
                            LtLog.e(mFairy.getLineInfo("remove list_up_left :" + list_up_left.get(j)[0] + "," + list_up_left.get(j)[1]));
                            list_up_left.remove(j);
                            j--;
                        }
                    }
                }

                List<int[]> new_list_down_right = list_down_right;
                for (int i = list_down_left.size() - 1; i >= 0; i--) {

                    for (int j = list_down_right.size() - 1; j >= 0; j--) {

                        if (list_down_left.get(i)[0] > list_down_right.get(j)[0]) {
                            LtLog.e(mFairy.getLineInfo("remove list_down_right :" + list_down_right.get(j)[0] + "," + list_down_right.get(j)[1]));
                            list_down_right.remove(j);
                            j--;
                        }
                    }
                }

                for (int i = new_list_down_right.size() - 1; i >= 0; i--) {

                    for (int j = list_down_left.size() - 1; j >= 0; j--) {

                        if (new_list_down_right.get(i)[0] < list_down_left.get(j)[0]) {
                            LtLog.e(mFairy.getLineInfo("remove list_down_left :" + list_down_left.get(j)[0] + "," + list_down_left.get(j)[1]));
                            list_down_left.remove(j);
                            j--;
                        }
                    }
                }
            }

            /**
             * 线程-判断资源数量
             * @throws Exception
             */
            class zy_judge extends Thread {
                public void run() {
                    LtLog.e("【资源检测线程启动】");
                    try {

                        int new_nb1 = 0;
                        int new_nb2 = 0;

                        zy3 r3 = new zy3();
                        r3.start();

                        for (int i = 0; i < 10; i++) {
                            zy1 r1 = new zy1();
                            zy2 r2 = new zy2();

                            r1.start();
                            r2.start();

                            r1.join();
                            r2.join();

                            if (new_nb1 != 0 && new_nb2 != 0) {
                                if (new_nb1 == nb1 && new_nb2 == nb2) {
                                    break;
                                } else {
                                    new_nb1 = 0;
                                    new_nb2 = 0;
                                }
                            } else {
                                new_nb1 = nb1;
                                new_nb2 = nb2;
                            }
                        }

                        LtLog.e(mFairy.getLineInfo("检测金币数量为：" + nb1));
                        LtLog.e(mFairy.getLineInfo("检测圣水数量为：" + nb2));
                        if ((nb1 >= getNumberAssembly(AtFairyConfig.getOption("nb1")) * 1000) && (nb2 >= getNumberAssembly(AtFairyConfig.getOption("nb2")) * 1000)) {
                            zy_bool = true;
                        } else {

                            if (getNumberAssembly(AtFairyConfig.getOption("nb4")) != -1) {
                                if ((nb1 + nb2) >= getNumberAssembly(AtFairyConfig.getOption("nb4")) * 1000) {
                                    zy_bool = true;
                                }
                            }
                        }

                        if (zy_bool) {
                            r3.join();
                            LtLog.e(mFairy.getLineInfo("检测黑油数量为：" + nb3));
                            if (nb3 >= getNumberAssembly(AtFairyConfig.getOption("nb3")) * 1000) {
                                zy_bool = true;
                            } else {
                                zy_bool = false;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getMessage();
                    }
                }
            }

            class zy1 extends Thread {
                public void run() {
                    try {
                        nb1 = gamePublicFuntion.get_number(0.9f, 59, 94, 70, 125, 6, 13, 35, "hs", null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            class zy2 extends Thread {
                public void run() {
                    try {
                        nb2 = gamePublicFuntion.get_number(0.9f, 60, 132, 70, 163, 6, 13, 35, "zs", null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            class zy3 extends Thread {
                public void run() {
                    try {
                        nb3 = gamePublicFuntion.get_number(0.8f, 62, 171, 70, 201, 6, 13, 35, "n", null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            /**
             * 线程-上红线
             * @throws Exception
             */
            class hx_up_judge extends Thread {
                public void run() {
                    LtLog.e("【上方检测线程启动】");
                    Mat new_screen_up = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1, screen_up_screen);
                    Mat bz_screen_up = binaryzationMat(new_screen_up, new int[]{40, 100, 175}, new int[]{55, 140, 210});

                    for (int i = 0; i < list_up_left.size(); i++) {
                        long n = gamePublicFuntion.getColorNum(bz_screen_up, list_up_left.get(i)[0], list_up_left.get(i)[1] - 10, list_up_left.get(i)[0] + 30, list_up_left.get(i)[1] + 10, false);
                        if (n == 0) {
                            list_up_left.set(i, new int[]{list_up_left.get(i)[0] + 29, list_up_left.get(i)[1]});

                            if (list_up_left.get(i)[0] < 650) {
                                i--;
                            }
                        }
                    }

                    for (int i = 0; i < list_up_right.size(); i++) {
                        long n = gamePublicFuntion.getColorNum(bz_screen_up, list_up_right.get(i)[0] - 30, list_up_right.get(i)[1] - 10, list_up_right.get(i)[0], list_up_right.get(i)[1] + 10, false);

                        if (n == 0) {
                            list_up_right.set(i, new int[]{list_up_right.get(i)[0] - 29, list_up_right.get(i)[1]});

                            if (list_up_right.get(i)[0] > 550) {
                                i--;
                            }
                        }
                    }

                    int leftx = list_up_left.get(0)[0];
                    int lefty = list_up_left.get(0)[1];

                    int rightx = list_up_right.get(0)[0];
                    int righty = list_up_right.get(0)[1];

                    int upx = list_up_left.get(list_up_left.size() - 1)[0];
                    int upy = list_up_left.get(list_up_left.size() - 1)[1];


                    Mat mat_up = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1, screen_up_screen);
                    Mat bz_cjq_up = binaryzationMat(mat_up, new int[]{220, 79, 220}, new int[]{255, 140, 255});

                    Mat mat_up_man = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1, screen_up_screen);
                    Mat bz_cjq_man_up = binaryzationMat(mat_up_man, new int[]{135, 50, 130}, new int[]{215, 95, 195});


                    Mat mat_up_jianzhu = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1, screen_up_screen);
                    Mat bz_jianzhu = binaryzationMat(mat_up_jianzhu, new int[]{195, 200, 220}, new int[]{220, 220, 230});

                    Mat mat_up_jianzhu2 = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1, screen_up_screen);
                    Mat bz_jianzhu2 = binaryzationMat(mat_up_jianzhu2, new int[]{20, 125, 240}, new int[]{40, 190, 255});


                    try {
                        int ccq_x = leftx + 150;
                        int ccq_y = upy + 150;
                        int ccq_x1 = rightx - 150;

                        if ((ccq_x < ccq_x1) && (ccq_y < 579)) {
                            long mat_judge = gamePublicFuntion.getColorNum(bz_cjq_up, ccq_x, ccq_y, ccq_x1, 579, false);
                            LtLog.e("【储存器的颜色数量 】：" + ccq_x + "," + ccq_y + "," + ccq_x1 + "," + 579 + " 颜色数量:" + mat_judge);
                            if (mat_judge > 1000) {
                                LtLog.e("【储存器太满啦】");
                                man = true;
                                return;
                            }
                        }

                        long mat_jianzhu = gamePublicFuntion.getColorNum(bz_jianzhu, leftx, upy, rightx, 579, false);
                        LtLog.e("【12级建筑的颜色数量】：" + mat_jianzhu);
                        if (mat_jianzhu > 1500) {
                            LtLog.e("【12级建筑等级太高!】");
                            man = true;
                            return;
                        }

                        long mat_jianzhu2 = gamePublicFuntion.getColorNum(bz_jianzhu2, leftx, upy, rightx, 579, false);
                        LtLog.e("【11级建筑的颜色数量】：" + mat_jianzhu2);
                        if (mat_jianzhu2 > 1500) {
                            LtLog.e("【11级建筑等级太高!】");
                            man = true;
                            return;
                        }

                    } catch (
                            Exception e) {
                        e.printStackTrace();
                        man = true;
                        return;
                    }

                    for (
                            int i = 0; i < list_up_left.size(); i++) {
                        if (list_up_left.get(i)[0] > list_up_right.get(i)[0]) {
                            LtLog.e("remove list_up_left :" + list_up_left.get(i)[0] + "," + list_up_left.get(i)[1]);
                            LtLog.e("remove list_up_right :" + list_up_right.get(i)[0] + "," + list_up_right.get(i)[1]);
                            list_up_left.remove(i);
                            list_up_right.remove(i);
                            i--;
                        }
                    }


                   /* List<int[]> new_list_up_right = list_up_right;

                    for (int i = 0; i < list_up_left.size(); i++) {
                        for (int j = 0; j < list_up_right.size(); j++) {
                            if (list_up_left.get(i)[0] > list_up_right.get(j)[0]) {
                                LtLog.e("remove list_up_right :" + list_up_right.get(j)[0] + "," + list_up_right.get(j)[1]);
                                list_up_right.remove(j);
                                j--;
                            }
                        }
                    }

                    for (int i = 0; i < new_list_up_right.size(); i++) {
                        for (int j = 0; j < list_up_left.size(); j++) {
                            if (new_list_up_right.get(i)[0] < list_up_left.get(j)[0]) {
                                LtLog.e("remove list_up_left :" + list_up_left.get(j)[0] + "," + list_up_left.get(j)[1]);
                                list_up_left.remove(j);
                                j--;
                            }
                        }
                    }
*/


                    for (
                            int i = 0; i < list_up_left.size(); i++) {
                        int x = list_up_left.get(i)[0];
                        int y = list_up_left.get(i)[1];
                        int x1 = list_up_left.get(i)[0] + 150;
                        int y1 = list_up_left.get(i)[1] + 32;

                        long n = gamePublicFuntion.getColorNum(bz_cjq_up, x, y, x1, y1, false);
                        if (n > 40) {
                            LtLog.e("上 - 左" + x + "," + y + "," + x1 + "," + y1 + " 颜色数量:" + n + "    发现采集器");
                            cjq_a++;
                        } else {
                            n = gamePublicFuntion.getColorNum(bz_cjq_man_up, x, y, x1, y1, true);
                            if (n > 20) {
                                LtLog.e("上 - 左" + x + "," + y + "," + x1 + "," + y1 + " 颜色数量:" + n + "    发现采集器(满)");
                                cjq_a++;
                                cjq_a++;
                            }
                        }
                        //LtLog.e("上 - 左" + x + "," + y + "," + x1 + "," + y1 + " 颜色数量:" + n);
                    }


                    for (
                            int i = 0; i < list_up_right.size() - 1; i++) {

                        int x = list_up_right.get(i)[0] - 150;
                        int y = list_up_right.get(i)[1];
                        int x1 = list_up_right.get(i)[0];
                        int y1 = list_up_right.get(i)[1] + 32;

                        long n = gamePublicFuntion.getColorNum(bz_cjq_up, x, y, x1, y1, false);
                        if (n > 40) {
                            LtLog.e("上 - 右" + x + "," + y + "," + x1 + "," + y1 + " 颜色数量:" + n + "    发现采集器");
                            cjq_b++;
                        } else {
                            n = gamePublicFuntion.getColorNum(bz_cjq_man_up, x, y, x1, y1, true);
                            if (n > 20) {
                                LtLog.e("上 - 右" + x + "," + y + "," + x1 + "," + y1 + " 颜色数量:" + n + "    发现采集器(满)");
                                cjq_b++;
                                cjq_b++;
                            }
                        }
                        //LtLog.e("上 - 右" + x + "," + y + "," + x1 + "," + y1 + "颜色数量:" + n);
                    }

                }
            }

            /**
             * 线程-下红线
             * @throws Exception
             */
            class hx_down_judge extends Thread {
                public void run() {

                    LtLog.e("【下方检测线程启动】");

                    Mat new_screen_down = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1, screen_down_screen);
                    Mat bz_screen_down = binaryzationMat(new_screen_down, new int[]{40, 100, 175}, new int[]{55, 140, 210});

                    for (int i = 0; i < list_down_left.size(); i++) {
                        long n = gamePublicFuntion.getColorNum(bz_screen_down, list_down_left.get(i)[0], list_down_left.get(i)[1] - 10, list_down_left.get(i)[0] + 30, list_down_left.get(i)[1] + 10, false);

                        if (n == 0) {
                            list_down_left.set(i, new int[]{list_down_left.get(i)[0] + 29, list_down_left.get(i)[1]});

                            if (list_down_left.get(i)[0] < 650) {
                                i--;
                            }
                        }
                    }

                    for (int i = 0; i < list_down_right.size(); i++) {
                        long n = gamePublicFuntion.getColorNum(bz_screen_down, list_down_right.get(i)[0] - 30, list_down_right.get(i)[1] - 10, list_down_right.get(i)[0], list_down_right.get(i)[1] + 10, false);
                        if (n == 0) {
                            list_down_right.set(i, new int[]{list_down_right.get(i)[0] - 29, list_down_right.get(i)[1]});
                            if (list_down_right.get(i)[0] > 550) {
                                i--;
                            }
                        }
                    }

                    int leftx = list_down_left.get(list_down_left.size() - 1)[0];
                    int lefty = list_down_left.get(list_down_left.size() - 1)[1];
                    int rightx = list_down_right.get(list_down_right.size() - 1)[0];
                    int righty = list_down_right.get(list_down_right.size() - 1)[1];
                    int downx = list_down_left.get(0)[0];
                    int downy = list_down_left.get(0)[1];

                    Mat mat_down = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1, screen_down_screen);
                    Mat bz_cjq_down = binaryzationMat(mat_down, new int[]{220, 79, 220}, new int[]{255, 140, 255});

                    Mat mat_down_man = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1, screen_down_screen);
                    Mat bz_cjq_man_down = binaryzationMat(mat_down_man, new int[]{135, 50, 130}, new int[]{215, 95, 195});

                    Mat mat_down_jianzhu = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1, screen_down_screen);
                    Mat bz_jianzhu = binaryzationMat(mat_down_jianzhu, new int[]{215, 190, 120}, new int[]{255, 240, 180});

                    Mat mat_down_jianzhu2 = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1, screen_down_screen);
                    Mat bz_jianzhu2 = binaryzationMat(mat_down_jianzhu2, new int[]{140, 70, 1}, new int[]{210, 120, 45});

                    try {

                        long mat_jianzhu = gamePublicFuntion.getColorNum(bz_jianzhu, leftx, 0, rightx, downx, false);
                        LtLog.e("【14级建筑的颜色数量】：" + mat_jianzhu);
                        if (mat_jianzhu > 1000) {
                            LtLog.e("【14级建筑等级太高!】");
                            man = true;
                            return;
                        }

                        long mat_jianzhu2 = gamePublicFuntion.getColorNum(bz_jianzhu2, leftx, 0, rightx, downx, false);
                        LtLog.e("【13级建筑的颜色数量】：" + mat_jianzhu2);
                        if (mat_jianzhu2 > 600) {
                            LtLog.e("【13级建筑等级太高!】");
                            man = true;
                            return;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        man = true;
                        return;
                    }


                    for (int i = list_down_left.size() - 1; i >= 0; i--) {
                        if (list_down_left.get(i)[0] > list_down_right.get(i)[0]) {
                            LtLog.e("remove list_down_left :" + list_down_left.get(i)[0] + "," + list_down_left.get(i)[1]);
                            LtLog.e("remove list_down_right :" + list_down_right.get(i)[0] + "," + list_down_right.get(i)[1]);
                            list_down_left.remove(i);
                            list_down_right.remove(i);
                        }
                    }

                   /* List<int[]> new_list_down_right = list_down_right;
                    for (int i = list_down_left.size() - 1; i >= 0; i--) {

                        for (int j = list_down_right.size() - 1; j >= 0; j--) {

                            if (list_down_left.get(i)[0] > list_down_right.get(j)[0]) {
                                LtLog.e("remove list_down_right :" + list_down_right.get(j)[0] + "," + list_down_right.get(j)[1]);
                                list_down_right.remove(j);
                                j--;
                            }
                        }
                    }

                    for (int i = new_list_down_right.size() - 1; i >= 0; i--) {

                        for (int j = list_down_left.size() - 1; j >= 0; j--) {

                            if (new_list_down_right.get(i)[0] < list_down_left.get(j)[0]) {
                                LtLog.e("remove list_down_left :" + list_down_left.get(j)[0] + "," + list_down_left.get(j)[1]);
                                list_down_left.remove(j);
                                j--;
                            }
                        }
                    }*/

                    for (int i = 0; i < list_down_left.size(); i++) {
                        int x = list_down_left.get(i)[0];
                        int y = list_down_left.get(i)[1] - 32;
                        int x1 = list_down_left.get(i)[0] + 150;
                        int y1 = list_down_left.get(i)[1];

                        long n = gamePublicFuntion.getColorNum(bz_cjq_down, x, y, x1, y1, false);
                        if (n > 40) {
                            LtLog.e("下 - 左" + x + "," + y + "," + x1 + "," + y1 + " 颜色数量:" + n + "    发现采集器");
                            cjq_c++;
                        } else {
                            n = gamePublicFuntion.getColorNum(bz_cjq_man_down, x, y, x1, y1, true);
                            if (n > 20) {
                                LtLog.e("下 - 左" + x + "," + y + "," + x1 + "," + y1 + " 颜色数量:" + n + "    发现采集器(满)");
                                cjq_c++;
                                cjq_c++;
                            }
                        }
                        //LtLog.e("下 - 左" + x + "," + y + "," + x1 + "," + y1 + "颜色数量:" + n);
                    }

                    for (int i = 1; i < list_down_right.size(); i++) {

                        int x = list_down_right.get(i)[0] - 150;
                        int y = list_down_right.get(i)[1] - 32;
                        int x1 = list_down_right.get(i)[0];
                        int y1 = list_down_right.get(i)[1];

                        long n = gamePublicFuntion.getColorNum(bz_cjq_down, x, y, x1, y1, false);
                        if (n > 40) {
                            LtLog.e("下 - 右" + x + "," + y + "," + x1 + "," + y1 + " 颜色数量:" + n + "    发现采集器");
                            cjq_d++;
                        } else {
                            n = gamePublicFuntion.getColorNum(bz_cjq_man_down, x, y, x1, y1, true);
                            if (n > 20) {
                                LtLog.e("下 - 右" + x + "," + y + "," + x1 + "," + y1 + " 颜色数量:" + n + "    发现采集器(满)");
                                cjq_d++;
                                cjq_d++;
                            }
                        }
                        //LtLog.e("下 - 右" + x + "," + y + "," + x1 + "," + y1 + "颜色数量:" + n);
                    }
                }
            }

            /**
             * 闪黑
             */
            class sdt1 extends Thread {
                public void run() {
                    try {
                        for (int i = 0; i < 2; i++) {
                            Thread.sleep(500);

                            result = mFairy.findPic(14, 583, 1271, 710, new String[]{"shandian.png", "shandian1.png"});
                            if (result.sim < 0.92f) {
                                break;
                            }
                            result = mFairy.findPic(64, 30, 1123, 535, new String[]{"you1.png", "you2.png", "you3.png", "you4.png"});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(result.x, result.y, result.x + 1, result.y + 1, "发现钻油机", 5000);
                                i--;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            class sdt2 extends Thread {
                public void run() {
                    try {
                        for (int i = 0; i < 2; i++) {
                            Thread.sleep(500);

                            result = mFairy.findPic(14, 583, 1271, 710, new String[]{"shandian.png", "shandian1.png"});
                            if (result.sim < 0.92f) {
                                break;
                            }
                            result = mFairy.findPic(64, 30, 1123, 535, new String[]{"you6.png", "you5.png", "you7.png", "you8.png"});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(result.x, result.y, result.x + 1, result.y + 1, "发现钻油机", 5000);
                                i--;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            class sdd1 extends Thread {
                public void run() {
                    try {
                        for (int i = 0; i < 2; i++) {
                            Thread.sleep(500);

                            result = mFairy.findPic(14, 583, 1271, 710, new String[]{"shandian.png", "shandian1.png"});
                            if (result.sim < 0.92f) {
                                break;
                            }
                            result = mFairy.findPic(37, 0, 1217, 548, new String[]{"you1.png", "you2.png", "you3.png", "you4.png"});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(result.x, result.y, result.x + 1, result.y + 1, "发现钻油机", 5000);
                                i--;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            class sdd2 extends Thread {
                public void run() {
                    try {
                        for (int i = 0; i < 2; i++) {
                            Thread.sleep(500);

                            result = mFairy.findPic(14, 583, 1271, 710, new String[]{"shandian.png", "shandian1.png"});
                            if (result.sim < 0.92f) {
                                break;
                            }
                            result = mFairy.findPic(37, 0, 1217, 548, new String[]{"you6.png", "you5.png", "you7.png", "you8.png"});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(result.x, result.y, result.x + 1, result.y + 1, "发现钻油机", 5000);
                                i--;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }//无限

    public Mat binaryzationMat(Mat mat, int[] minRange, int[] maxRange) {
        Scalar minValues = new Scalar(minRange[0], minRange[1], minRange[2]);
        Scalar maxValues = new Scalar(maxRange[0], maxRange[1], maxRange[2]);
        Core.inRange(mat, minValues, maxValues, mat);
        minValues = null;
        maxValues = null;
        return mat;
    }
}




