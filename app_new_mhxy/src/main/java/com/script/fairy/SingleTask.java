

package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

public class SingleTask {

    public GamePublicFuntion gamePublicFuntion;
    public AtFairyImpl mFairy;
    private String activityName = "";
    private int activityType = 1;
    FindResult result;

    public SingleTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }

    class SingleContent extends TaskContent {

        public SingleContent(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);
        }

        void create() throws Exception {
            super.create();
        }

        void init() throws Exception {
            gamePublicFuntion.taskInit(1);
            setTaskName(1);
            oneSecond = 0;
        }

        void content_01() throws Exception {
            if (timeCount(7, 0)) {
                if (frequencyMap("actcount", 1)) {
                    setTaskEnd();
                    return;
                }
            }

            result = mFairy.findPic(228, 5, 747, 99, "activity.png");
            if (result.sim > 0.8f) {
                err = 0;
                mFairy.onTap(0.8f, result, "点击活动按钮", 800);
            }

            result = mFairy.findPic(new String[]{"activity1.png","activity2.png"});
            if (result.sim > 0.8f) {

                if (oneSecond()) {
                    gamePublicFuntion.activityType(activityType);
                    gamePublicFuntion.actLing();
                }

                FindResult act = mFairy.findPic(304, 80, 1144, 458, activityName);
                if (act.sim > 0.8f) {

                    if (actEnd(act)) {
                        return;
                    }

                    result = mFairy.findPic(act.x + 180, act.y - 20, act.x + 300, act.y + 70, "can.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "参加", 1000);

                        if (mapCount(0.8f, 427, 129, 657, 457, "bperr.png")) {
                            setTaskEnd();
                            return;
                        }

                        setTaskName(2);
                        oneSecond = 0;
                        frequencyInit("actcount");
                        return;
                    }

                    result = mFairy.findPic(act.x + 180, act.y - 20, act.x + 300, act.y + 70, "wan.png");
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return;
                    }
                }
                activitySlide.slideRange(new int[]{3, 4, 5}, 2, 0);
            }
        }

        void inOperation() throws Exception {
            gamePublicFuntion.auto();
            gamePublicFuntion.rightZoom();
            gamePublicFuntion.task();
            gamePublicFuntion.skip();

            if(!getName().equals("打工赚钱") && !getName().equals("储备")){
                result = mFairy.findPic("renwu.png");
                mFairy.onTap(0.8f,result,1090,41,1107,59,"关闭头像界面",1000);
            }

            gamePublicFuntion.battle();

            for (int i = 0; i < 3; i++) {
                use();
            }

            quxiao();

            result = mFairy.findPic(767, 101, 841, 176, "jia3.png");
            mFairy.onTap(0.8f, result, "关闭小弹框", 500);

            result = mFairy.findPic("nn5.png");
            mFairy.onTap(0.8f, result, "奖励", 500);

            result = mFairy.findPic("fls.png");
            mFairy.onTap(0.8f, result, 1068, 65, 1082, 82, "福利界面-关闭", 500);

            result = mFairy.findPic("nn6.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, 702, 537, 766, 551, "查看", 3000);
                setTaskName(0);
                return;
            }
        }

        void use() throws Exception {
            gamePublicFuntion.useClose();
        }//使用

        void quxiao() throws Exception {
            FindResult qx = gamePublicFuntion.qx();
            if (qx.sim > 0.8f) {

                result = mFairy.findPic(393, 251, 682, 414, "gmerr1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(725, 426, 761, 440, "确定", 500);
                    return;
                }

                result = mFairy.findPic(393, 251, 682, 414, "sm15.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(725, 426, 761, 440, "确定", 500);
                    return;
                }

                mFairy.onTap(0.8f, qx, "取消", 500);
            }
        }//取消

        boolean actEnd(FindResult act) throws Exception {

            return false;
        }//活动结束
    }

    public void dg() throws Exception {
        new SingleContent(mFairy, "打工赚钱") {

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;

            }

            void content_01() throws Exception {
                timeCount(6, 0);


                result = mFairy.findPic("dg2.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    result = mFairy.findPic("dg3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "打工", 100);

                        if (mapCount(0.8f, 632, 189, 687, 454, "dg4.png")) {
                            mFairy.onTap(1028, 80, 1048, 96, "", 300);
                            mFairy.onTap(1095, 42, 1115, 59, "", 300);
                            setTaskEnd();
                            return;
                        }
                    } else {
                        mFairy.ranSwipe(776, 257, 815, 534, 0, 500, (long) 800);
                    }
                } else {
                    result = mFairy.findPic("dg1.png");
                    if (result.sim > 0.85f) {
                        err = 0;
                        result = mFairy.findPic("dg5.png");
                        mFairy.onTap(0.8f, result, "使用", 500);

                    } else {
                        mFairy.onTap(1226, 25, 1252, 45, "", 1000);
                    }
                }
            }
        };
    }//打工赚钱

    public void shcs() throws Exception {
        new SingleContent(mFairy, "商会出售") {

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;

            }

            void content_01() throws Exception {
                timeCount(6, 99);

                result = mFairy.findPic("shcs1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(633, 41, 1033, 383, "close7.png");
                    mFairy.onTap(0.8f, result, "guanbi", 500);

                    result = mFairy.findPic("shcs3.png");
                    if (result.sim > 0.8f) {

                        result = mFairy.findPic(161, 246, 287, 375, "shcs6.png");
                        if (result.sim > 0.8f) {
                            frequencyInit("shou");
                            mFairy.onTap(250, 214, 317, 294, "", 500);
                            mFairy.onTap(965, 636, 1053, 666, "", 500);
                            return;
                        }

                        if (frequencyMap("shou", 3)) {
                            setTaskEnd();
                            return;
                        }

                    } else {
                        result = mFairy.findPic(1108, 57, 1198, 485, "shcs4.png");
                        mFairy.onTap(0.8f, result, "商会", 500);

                        result = mFairy.findPic("shcs5.png");
                        mFairy.onTap(0.8f, result, "我要出售", 500);
                    }
                }

                result = mFairy.findPic(4, 84, 97, 376, "shcs2.png");
                mFairy.onTap(0.8f, result, "商城", 500);

            }


        };
    }//商会出售

    public void cb() throws Exception {
        new SingleContent(mFairy, "储备") {

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                timeCount(6, 0);

                gamePublicFuntion.use();

                result = mFairy.findPic(706, 346, 1258, 709, new String[]{"gm.png", "gm2.png"});
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "购买", 500);
                    err = 0;
                    for (int i = 0; i < 8; i++) {
                        result = mFairy.findPic(657, 243, 742, 425, new String[]{"err1.png", "err2.png", "err3.png"});
                        if (result.sim > 0.8f) {
                            mFairy.finish(AtFairyConfig.getTaskID(), 1301);
                            setTaskEnd();
                        }
                    }

                    result = mFairy.findPic("err4.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(866, 244, 880, 260, "银币不足", 500);
                        mFairy.onTap(1135, 41, 1149, 54, "", 500);
                        setTaskEnd();
                        return;
                    }

                    for (int i = 0; i < 3; i++) {
                        gamePublicFuntion.use();
                    }

                    mFairy.onTap(1135, 41, 1149, 54, "", 500);
                    return;
                }

                result = mFairy.findPic("dg1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(1139, 292, 1153, 327, "信息", 200);
                } else {
                    mFairy.onTap(1226, 25, 1252, 45, "", 1000);
                }

                result = mFairy.findPic("cb.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    long a = mFairy.getColorNum(247, 362, 257, 380, 0.95f, 0, "228,74,33");
                    if (a < 30) {
                        mFairy.onTap(507, 363, 526, 376, "血量不足", 500);
                        return;
                    }

                    long b = mFairy.getColorNum(249, 613, 261, 630, 0.95f, 0, "67,164,239");
                    if (b < 30) {
                        mFairy.onTap(513, 615, 529, 631, "蓝量不足", 500);
                        return;
                    }
                    mFairy.onTap(1095, 43, 1108, 56, "", 500);
                    setTaskEnd();
                    return;
                }
            }
        };
    }//储备

    public void sm() throws Exception {
        new SingleContent(mFairy, "师门任务") {

            void create() throws Exception {
                super.create();
                activityName = "sm.png";
                activityType = 1;
            }

            void use() throws Exception {
                gamePublicFuntion.use();
            }

            void content_02() throws Exception {
                timeCount(8, 0);
                result = mFairy.findPic("jia8.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("package.png");
                    if (result.sim < 0.8f) {
                        if (err > 3) {
                            if (frequencyMap("e", 1)) {
                                mFairy.onTap(821, 560, 860, 580, "", 500);
                            }
                        }
                    }
                }

                gamePublicFuntion.gm();

                result = mFairy.findPic(912, 5, 1242, 624, "sm6.png");
                if (result.sim > 0.85f) {
                    mFairy.initMatTime();
                    mFairy.onTap(0.85f, result, "师门任务", 1000);
                } else {
                    if (gamePublicFuntion.shi()) {
                        mFairy.initMatTime();
                    }
                }

                if (gamePublicFuntion.zheng()) {
                    mFairy.initMatTime();
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("fail", 3)) {
                        setTaskEnd();
                        /**
                         * 任务出现多次战斗失败!
                         */
                        return;
                    }
                }

                result = mFairy.findPic(430,488,876,588,new String[]{"sm1.png","sm14.png","wanc2.png"});
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("sm2.png");
                    mFairy.onTap(0.9f, result, "自动选择", 500);

                    result = mFairy.findPic(338, 390, 1163, 540, "sm3.png");
                    mFairy.onTap(0.7f, result, "选择", 500);

                    result = mFairy.findPic(328, 404, 1164, 528, new String[]{"sm10.png","sm13.png","wanc1.png"});
                    mFairy.onTap(0.7f, result, "去完成", 2000);

                    result = mFairy.findPic(328, 404, 1164, 528, new String[]{"sm11.png","jixu.png"});
                    mFairy.onTap(0.7f, result, "继续任务", 2000);

                    result = mFairy.findPic(338, 390, 1163, 540, new String[]{"sm7.png","wanc.png"});
                    if (result.sim > 0.85f) {
                        if (frequencyMap("smend", 3)) {
                            mFairy.onTap(371, 564, 391, 583, "", 500);
                            mFairy.onTap(1147, 162, 1160, 176, "", 500);
                            setTaskEnd();
                            return;
                        }
                    } else {
                        frequencyInit("smend");
                    }
                }else{
                    frequencyInit("smend");
                }

                result = mFairy.findPic(522, 138, 720, 330, "jia5.png");
                mFairy.onTap(0.85f, result, "猪八戒", 500);

                result = mFairy.findPic("sm8.png");
                mFairy.onTap(0.85f, result, "交付宠物", 500);

                result = mFairy.findPic(647, 329, 1254, 672, "sm5.png");
                mFairy.onTap(0.8f, result, "上交", 500);

                result = mFairy.findPic(492, 312, 754, 427, new String[]{"sm9.png", "sm12.png"});
                mFairy.onTap(0.75f, result, 601, 505, 647, 523, "获取图签", 1500);

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(2)) {
                        result = mFairy.findPic(1032, 169, 1102, 519, new String[]{"nn14.png", "sm4.png"});
                        if (result.sim > 0.7f) {
                            err = 0;
                            mFairy.onTap(0.7f, result, "师门", 1000);
                            return;
                        }
                        taskSlide.slideRange(new int[]{3, 4, 5}, 2, 0);
                    } else {
                        err = 0;
                    }
                }


            }
        };
    }//师门

    public void bt() throws Exception {
        new SingleContent(mFairy, "宝图任务") {

            void create() throws Exception {
                super.create();
                activityName = "bt.png";
                activityType = 1;
            }

            void content_02() throws Exception {
                timeCount(5, 0);

                gamePublicFuntion.close();

                if (gamePublicFuntion.shi()) {
                    mFairy.initMatTime();
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("fail", 3)) {
                        setTaskEnd();
                        return;
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(2)) {
                        result = mFairy.findPic(1031, 176, 1103, 461, new String[]{"bt1.png"});
                        if (result.sim > 0.7f) {
                            err = 0;
                            mFairy.initMatTime();
                            mFairy.onTap(0.7f, result, "任务click", 2000);
                            return;
                        }
                        taskSlide.slideRange(new int[]{2, 3, 4}, 2, 0);
                    } else {
                        err = 0;
                    }
                }
            }
        };
    }//宝图

    boolean mj = false;
    boolean mj_bool = false;
    int mj_user_count = 99;

    public void mj() throws Exception {
        new SingleContent(mFairy, "秘境降妖") {

            void quxiao() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic(375,251,681,436, "mm1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(726,421,761,441, "确定", 500);
                        return;
                    }

                    mFairy.onTap(0.8f, qx, "取消", 500);
                }
            }//取消

            void create() throws Exception {
                super.create();
                activityName = "mj.png";
                activityType = 1;
                mj = false;
                mj_bool = false;

                if (!AtFairyConfig.getOption("mm").equals("")) {
                    mj_user_count = Integer.parseInt(AtFairyConfig.getOption("mm"));
                }
            }

            void content_02() throws Exception {
                if (timeCount(8, 2)) {
                    if (mj) {
                        gamePublicFuntion.close();
                        result = mFairy.findPic(1129,160,1271,549, "mj5.png");
                        if (result.sim < 0.8f) {
                            setTaskName(0);
                            return;
                        }
                    } else {
                        setTaskName(0);
                        return;
                    }
                }

                result = mFairy.findPic(955, 4, 1211, 529, "mj1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "秘境降妖", 500);
                } else {
                    if (gamePublicFuntion.shi()) {
                        mFairy.initMatTime();
                    }
                }

                result = mFairy.findPic("battle.png");
                if (result.sim > 0.8f) {
                    mj_bool = true;
                } else {
                    if (mj_bool) {
                        mj_user_count--;
                        if (mj_user_count <= 0) {
                            for (int i = 0; i < 5; i++) {
                                mFairy.onTap(596, 328, 628, 347, "任务结束点击地面", 200);
                            }
                            mFairy.onTap(0.8f, result, 1196, 298, 1219, 307, "end", 2000);
                            gamePublicFuntion.taskInit(1);
                            setTaskEnd();
                            return;
                        }
                        mj_bool = false;
                    }
                }

                result = mFairy.findPic("lb.png");
                mFairy.onTap(0.85f, result, "礼包", 3000);

                result = mFairy.findPic(442,26,765,105,"mj12.png");
                if (result.sim > 0.8f) {
                    err=0;

                    switch (AtFairyConfig.getOption("mjtype")){
                        case "2":
                            mFairy.onTap(848,622,903,640,"日月之井",1000);
                            break;
                            default:
                                mFairy.onTap(347,627,398,643,"海底秘境",1000);
                                break;
                    }
                }else{
                    result = mFairy.findPic(new String[]{"mj2.png","mj10.png"});
                    if (result.sim > 0.8f) {
                        err = 0;
                        result = mFairy.findPic(new String[]{"mj6.png","mm3.png"});
                        if (result.sim > 0.95f) {
                            mFairy.onTap(1090, 31, 1106, 50, "", 500);
                            setTaskEnd();
                            return;
                        }

                        result = mFairy.findPic(140, 152, 693, 685, "mj3.png");
                        if(result.sim>0.92f) {
                            mFairy.onTap(0.92f, result, result.x + 60, result.y - 45, result.x + 65, result.y - 43, "第一关", 500);
                        }else{
                            mFairy.ranSwipe(176,473, 1004,414, 500, 1000);
                        }
                    }
                }

                result = mFairy.findPic(502, 407, 846, 609, new String[]{"mj4.png", "mj9.png"});
                mFairy.onTap(0.8f, result, "挑战", 3000);

                if (gamePublicFuntion.fail()) {
                    mFairy.onTap(1233,658,1243,681, "", 1000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(365, 3, 638, 96, "mj8.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic(1129,160,1271,549, "mj5.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mj = true;
                    result = mFairy.findPic("mj11.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 1196, 298, 1219, 307, "秘境通关", 2000);
                        setTaskEnd();
                        return;
                    }
                    if (gamePublicFuntion.judgeStop(2)) {
                        mFairy.initMatTime();
                        mFairy.onTap(1094, 212, 1163, 243, "秘境", 2000);
                    }
                }

                result = mFairy.findPic(376,571,736,672, "guo.png");
                if (result.sim > 0.7f) {
                    for (int i = 0; i < 3; i++) {
                        mFairy.onTap(1187,339,1211,360,"过期了",1000);
                    }
                    setTaskEnd();
                    return;
                }

            }
        };
    }//秘境降妖

    public void bp() throws Exception {
        new SingleContent(mFairy, "帮派任务") {

            void create() throws Exception {
                super.create();
                activityName = "bp.png";
                activityType = 1;
            }

            void use() throws Exception {
                gamePublicFuntion.use();
            }

            void content_02() throws Exception {
                timeCount(6, 0);

                gamePublicFuntion.gm();

                result = mFairy.findPic(912, 5, 1242, 624, "bp1.png");
                if (result.sim > 0.85f) {
                    mFairy.initMatTime();
                    mFairy.onTap(0.85f, result, "帮派任务", 1000);
                } else {
                    result = mFairy.findPic(916, 125, 1222, 541, "bp5.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(1157, 571, 1172, 591, "", 500);
                    } else {
                        if (gamePublicFuntion.shi()) {
                            mFairy.initMatTime();
                        }
                    }
                }


                if (gamePublicFuntion.zheng()) {
                    mFairy.initMatTime();
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("fail", 3)) {
                        setTaskEnd();
                        /**
                         * 任务出现多次战斗失败!
                         */
                        return;
                    }
                }

                result = mFairy.findPic("sm8.png");
                mFairy.onTap(0.85f, result, "交付宠物", 500);

                result = mFairy.findPic(647, 329, 1254, 672, "sm5.png");
                mFairy.onTap(0.8f, result, "上交", 500);


                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(2)) {
                        result = mFairy.findPic(1032, 169, 1102, 519, new String[]{"bp2.png", "bp3.png", "bp4.png"});
                        if (result.sim > 0.7f) {
                            err = 0;
                            mFairy.onTap(0.7f, result, "帮派", 1000);
                            return;
                        }
                        taskSlide.slideRange(new int[]{2, 3, 4}, 2, 0);
                    } else {
                        err = 0;
                    }
                }

            }
        };
    }//帮派任务

    private boolean jyl = false;

    public void jyl() throws Exception {
        new SingleContent(mFairy, "经验链") {

            void create() throws Exception {
                super.create();
                activityName = "jyl.png";
                activityType = 1;
            }

            boolean actEnd(FindResult act) throws Exception {

                result = mFairy.findPic(act.x + 180, act.y - 20, act.x + 300, act.y + 70, "wan.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1125, 38, 1150, 54, "", 500);
                    setTaskName(2);
                    oneSecond = 0;
                    frequencyInit("actcount");
                    return true;
                }
                return false;
            }

            void use() throws Exception {
                gamePublicFuntion.use();
            }

            void content_02() throws Exception {
                timeCount(12, 99);

                FindResult gmr = mFairy.findPic(706, 346, 1258, 709, new String[]{"gm.png", "gm2.png"});
                if (gmr.sim > 0.85f) {
                    result = mFairy.findPic("jyl6.png");
                    if (result.sim > 0.8f) {
                        if (frequencyMap("gmr", 2) == false) {
                            gamePublicFuntion.close();
                            return;
                        }
                    }

                    result = mFairy.findPic("gmerr.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("暂时没有商品,End!"));
                        setTaskEnd();
                        return;
                    } else {
                        mFairy.onTap(0.85f, gmr, "购买", 500);

                        err = 0;
                        for (int i = 0; i < 8; i++) {
                            result = mFairy.findPic(657, 243, 742, 425, new String[]{"err1.png", "err2.png", "err3.png"});
                            if (result.sim > 0.8f) {
                                mFairy.finish(AtFairyConfig.getTaskID(), 1301);
                                setTaskEnd();
                                return;
                            }
                        }

                        result = mFairy.findPic(858, 1, 1274, 434, "close1.png");
                        mFairy.onTap(0.8f, result, "", 500);
                    }

                    result = mFairy.findPic("err4.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(866, 244, 880, 260, "银币不足", 500);
                        mFairy.finish(AtFairyConfig.getTaskID(), 4596);
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(912, 5, 1242, 624, "jyl2.png");
                if (result.sim > 0.85f) {
                    mFairy.initMatTime();
                    mFairy.onTap(0.85f, result, "领取经验链任务", 1000);
                } else {
                    result = mFairy.findPic(912, 5, 1242, 624, "jyl1.png");
                    if (result.sim > 0.85f) {
                        mFairy.initMatTime();
                        mFairy.onTap(0.85f, result, "经验链任务", 1000);
                    } else {
                        if (gamePublicFuntion.shi()) {
                            mFairy.initMatTime();
                        }
                    }
                }

                result = mFairy.findPic("sm8.png");
                mFairy.onTap(0.85f, result, "交付宠物", 500);

                result = mFairy.findPic(647, 329, 1254, 672, "sm5.png");
                mFairy.onTap(0.8f, result, "上交", 500);

                result = mFairy.findPic("jyl3.png");
                mFairy.onTap(0.85f, result, 750, 537, 790, 554, "确定开启", 500);

                if (gamePublicFuntion.zheng()) {
                    mFairy.initMatTime();
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("fail", 3)) {
                        setTaskEnd();
                        /**
                         * 任务出现多次战斗失败!
                         */
                        return;
                    }
                }

                result = mFairy.findPic("guaji1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    long num;
                    for (int i = 1; i <= 4; i++) {
                        if (i == 1) {
                            num = mFairy.getColorNum(172, 121, 203, 150, 0.99f, 0, "253,216,113");
                            if (num > 100) {
                                mFairy.onTap(172, 121, 203, 150, "", 500);
                                break;
                            }
                        }
                        if (i == 2) {
                            num = mFairy.getColorNum(421, 125, 450, 149, 0.99f, 0, "253,216,113");
                            if (num > 100) {
                                mFairy.onTap(421, 125, 450, 149, "", 500);
                                break;
                            }
                        }

                        if (i == 3) {
                            num = mFairy.getColorNum(659, 112, 691, 145, 0.99f, 0, "253,216,113");
                            if (num > 100) {
                                mFairy.onTap(659, 112, 691, 145, "", 500);
                                break;
                            }
                        }

                        if (i == 4) {
                            num = mFairy.getColorNum(912, 112, 944, 137, 0.99f, 0, "253,216,113");
                            if (num > 100) {
                                mFairy.onTap(912, 112, 944, 137, "", 500);
                                break;
                            }
                        }
                    }
                }

                if (jyl) {
                    FindResult rn = mFairy.findPic(1026, 168, 1277, 414, new String[]{"jyl4.png"});
                    if (rn.sim > 0.7f) {
                        err = 0;
                        result = mFairy.findPic(rn.x - 30, rn.y + 20, rn.x + 212, rn.y + 125, "jyl5.png");
                        if (result.sim < 0.7f) {
                            gamePublicFuntion.stopBattle();
                            jyl = false;
                            mFairy.onTap(0.7f, rn, "经验链任务", 1000);
                            frequencyInit("gmr");
                            return;
                        }

                        if (timeMap("stopBattle", 300000)) {
                            gamePublicFuntion.stopBattle();
                            err = 0;
                            jyl = false;
                        }
                    }
                }



                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(4)) {

                        FindResult r = mFairy.findPic(1026, 168, 1277, 414, new String[]{"jyl4.png"});
                        if (r.sim > 0.7f) {
                            err = 0;

                            result = mFairy.findPic(r.x - 30, r.y + 20, r.x + 212, r.y + 125, "jyl5.png");
                            if (result.sim > 0.7f) {
                                jyl = true;
                                frequencyInit("gmr");

                                gamePublicFuntion.taskInit(0);

                                result = mFairy.findPic(236, 6, 783, 100, "guaji.png");
                                mFairy.onTap(0.8f, result, "挂机", 500);
                                return;
                            }

                            if (frequencyMap("g", 2)) {
                                jyl = false;
                                timeMapInit("stopBattle");
                                mFairy.onTap(0.7f, r, "经验链任务", 1000);
                            }
                            return;
                        }
                        taskSlide.slideRange(new int[]{3, 5, 7, 8, 9}, 2, 0);
                    } else {
                        err = 0;
                    }
                } else {
                    mFairy.initMatTime();
                }


            }
        };
    }//经验链

    @Deprecated
    public void cjmy_t(final int type) throws Exception {
        new SingleContent(mFairy, "贸易" + type) {

            void create() throws Exception {
                super.create();

                switch (type) {
                    case 1:
                        activityName = "cjmy.png";
                        break;
                    case 2:
                        activityName = "gjmy.png";
                        break;
                }

                activityType = 3;

            }

            boolean actEnd(FindResult act) throws Exception {

                result = mFairy.findPic(act.x + 180, act.y - 20, act.x + 300, act.y + 70, "wan.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1125, 38, 1150, 54, "", 500);
                    setTaskName(2);
                    oneSecond = 0;
                    frequencyInit("actcount");
                    return true;
                }
                return false;
            }

            void quxiao() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic(383, 238, 935, 401, "my14.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(715, 422, 775, 451, "确定", 500);
                        return;
                    }


                    result = mFairy.findPic(386, 258, 739, 412, "my17.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(715, 422, 775, 451, "确定", 500);
                        return;
                    }


                    result = mFairy.findPic(386, 258, 739, 412, "my16.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(715, 422, 775, 451, "确定", 500);
                        return;
                    }


                    result = mFairy.findPic("my6.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("my22.png");
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic(322, 177, 762, 311, "my7.png");
                            mFairy.onTap(0.8f, result, "购买", 1000);
                            return;
                        }
                        mFairy.onTap(614, 514, 677, 537, "确定", 1000);
                        return;
                    }

                    mFairy.onTap(0.8f, qx, "取消", 500);
                }
            }//取消

            void use() throws Exception {

            }

            void content_02() throws Exception {
                timeCount(12, 99);

                result = mFairy.findPic(915, 63, 1226, 539, "my1.png");
                mFairy.onTap(0.85f, result, "帮派贸易", 500);

                result = mFairy.findPic("my2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (oneSecond()) {

                        switch (type) {
                            case 1:
                                mFairy.onTap(222, 600, 291, 614, "", 500);
                                break;
                            case 2:
                                mFairy.onTap(216, 663, 283, 681, "", 500);
                                break;
                        }

                        result = mFairy.findPic("my11.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "继续贸易", 800);
                            if (mapCount(0.8f, 470, 157, 687, 421, "my26.png")) {
                                mFairy.onTap(1121, 43, 1138, 57, "", 500);
                                setTaskEnd();
                                return;
                            }
                        }
                    }

                    FindResult start_result = mFairy.findPic("my15.png");
                    if (start_result.sim > 0.8f) {
                        for (int i = 1; i <= 18; i++) {
                            switch (i) {
                                case 1:
                                    if (AtFairyConfig.getOption("cjm1").equals("1")) {
                                        mFairy.onTap(158, 419, 179, 442, "宝象国", 1000);
                                    }
                                    break;
                                case 2:
                                    if (AtFairyConfig.getOption("cjm2").equals("1")) {
                                        mFairy.onTap(176, 293, 202, 312, "长寿村", 1000);
                                    }
                                    break;
                                case 3:
                                    if (AtFairyConfig.getOption("cjm3").equals("1")) {
                                        mFairy.onTap(366, 141, 382, 160, "凌霄宝殿", 1000);
                                    }
                                    break;
                                case 4:
                                    if (AtFairyConfig.getOption("cjm4").equals("1")) {
                                        mFairy.onTap(608, 120, 624, 137, "大雪山", 1000);
                                    }
                                    break;
                                case 5:
                                    if (AtFairyConfig.getOption("cjm5").equals("1")) {
                                        mFairy.onTap(297, 363, 318, 384, "两界山", 1000);
                                    }
                                    break;
                                case 6:
                                    if (AtFairyConfig.getOption("cjm6").equals("1")) {
                                        mFairy.onTap(783, 255, 796, 277, "洛阳城", 1000);
                                    }
                                    break;
                                case 7:
                                    if (AtFairyConfig.getOption("cjm7").equals("1")) {
                                        mFairy.onTap(851, 385, 873, 405, "建邺城", 1000);
                                    }
                                    break;
                                case 8:
                                    if (AtFairyConfig.getOption("cjm8").equals("1")) {
                                        mFairy.onTap(1055, 311, 1071, 329, "傲来渔港", 1000);
                                    }
                                    break;
                                case 9:
                                    if (AtFairyConfig.getOption("cjm9").equals("1")) {
                                        mFairy.onTap(1077, 201, 1098, 219, "花果山", 1000);
                                    }
                                    break;
                                case 10:
                                    if (AtFairyConfig.getOption("cjm10").equals("1")) {
                                        mFairy.onTap(238, 191, 268, 214, "方寸山", 1000);
                                    }
                                    break;
                                case 11:
                                    if (AtFairyConfig.getOption("cjm11").equals("1")) {
                                        mFairy.onTap(409, 240, 434, 264, "化生寺", 1000);
                                    }
                                    break;
                                case 12:
                                    if (AtFairyConfig.getOption("cjm12").equals("1")) {
                                        mFairy.onTap(395, 459, 413, 473, "魔王寨", 1000);
                                    }
                                    break;
                                case 13:
                                    if (AtFairyConfig.getOption("cjm13").equals("1")) {
                                        mFairy.onTap(547, 201, 579, 231, "大唐官府", 1000);
                                    }
                                    break;
                                case 14:
                                    if (AtFairyConfig.getOption("cjm14").equals("1")) {
                                        mFairy.onTap(649, 510, 680, 538, "狮驼岭", 1000);
                                    }
                                    break;
                                case 15:
                                    if (AtFairyConfig.getOption("cjm15").equals("1")) {
                                        mFairy.onTap(807, 163, 829, 186, "龙宫", 1000);
                                    }
                                    break;
                                case 16:
                                    if (AtFairyConfig.getOption("cjm16").equals("1")) {
                                        mFairy.onTap(948, 238, 965, 280, "女儿村", 1000);
                                    }
                                    break;
                                case 17:
                                    if (AtFairyConfig.getOption("cjm17").equals("1")) {
                                        mFairy.onTap(1025, 381, 1058, 414, "普陀山", 1000);
                                    }
                                    break;
                                case 18:
                                    if (AtFairyConfig.getOption("cjm18").equals("1")) {
                                        mFairy.onTap(1035, 509, 1061, 533, "阴曹地府", 1000);
                                    }
                                    break;
                            }

                            result = mFairy.findPic("my18.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "选择", 500);
                                if (mapCount(0.8f, 460, 158, 691, 504, "my19.png")) {
                                    setTaskEnd();
                                    return;
                                }
                            }

                            result = mFairy.findPic("my20.png");
                            mFairy.onTap(0.8f, result, 1225, 649, 1243, 671, "已勾选该城市", 500);

                        }
                        mFairy.onTap(0.8f, start_result, "开始贸易", 1000);
                    }

                } else {
                    oneSecond = 0;
                }


                result = mFairy.findPic("battle.png");
                if (result.sim > 0.8f) {
                    while (mFairy.condit()) {
                        Thread.sleep(100);

                        result = mFairy.findPic("battle.png");
                        if (result.sim < 0.8f) {
                            Thread.sleep(200);
                            break;
                        }
                    }

                    for (int i = 0; i < 20; i++) {
                        result = mFairy.findPic(431, 192, 630, 439, "my23.png");
                        if (result.sim > 0.8f) {
                            if (frequencyMap("fail", 2)) {
                                setTaskEnd();
                                return;
                            }
                        }
                    }
                }

                result = mFairy.findPic(460, 500, 809, 703, "my25.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    long t = mFairy.mMatTime(463, 393, 366, 201, 0.9f);
                    if (t > 30) {
                        mFairy.onTap(1073, 43, 1090, 65, "", 500);
                        mFairy.onTap(1185, 44, 1199, 58, "", 500);
                    }
                } else {
                    mFairy.initMatTime();
                }

                result = mFairy.findPic(410, 351, 926, 447, new String[]{"my12.png", "my24.png"});
                if (result.sim > 0.8f) {

                    if (AtFairyConfig.getOption("cjm_skill").equals("1")) {
                        mFairy.onTap(1073, 43, 1090, 65, "", 500);
                        mFairy.onTap(1185, 44, 1199, 58, "", 500);
                        setTaskEnd();
                        return;
                    }

                    if (AtFairyConfig.getOption("cjm_skill").equals("2")) {
                        result = mFairy.findPic(801, 111, 1027, 386, "my13.png");
                        mFairy.onTap(0.8f, result, "帮派行商令", 1000);
                        return;
                    }

                } else {
                    result = mFairy.findPic("my5.png");
                    mFairy.onTap(0.8f, result, "上交物品", 1000);
                }

                result = mFairy.findPic("my4.png");
                mFairy.onTap(0.8f, result, "开始战斗", 2000);

                result = mFairy.findPic("my8.png");
                mFairy.onTap(0.8f, result, "上交宠物", 1000);

                result = mFairy.findPic("my9.png");
                mFairy.onTap(0.8f, result, "上交", 500);

                FindResult gmr = mFairy.findPic(706, 346, 1258, 709, new String[]{"gm.png", "gm2.png"});
                if (gmr.sim > 0.85f) {

                    result = mFairy.findPic("jyl6.png");
                    if (result.sim > 0.8f) {
                        if (frequencyMap("gmr", 1) == false) {
                            mFairy.onTap(1087, 34, 1115, 54, "", 500);
                            return;
                        }
                    }

                    result = mFairy.findPic("gmerr.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("暂时没有商品,End!"));
                        setTaskEnd();
                        return;
                    } else {
                        mFairy.onTap(0.85f, gmr, "购买", 500);

                        err = 0;
                        for (int i = 0; i < 8; i++) {
                            result = mFairy.findPic(657, 243, 742, 425, new String[]{"err1.png", "err2.png", "err3.png"});
                            if (result.sim > 0.8f) {
                                mFairy.finish(AtFairyConfig.getTaskID(), 1301);
                                setTaskEnd();
                                return;
                            }
                        }

                        result = mFairy.findPic(858, 1, 1274, 434, "close1.png");
                        mFairy.onTap(0.8f, result, "", 500);
                    }

                    result = mFairy.findPic("err4.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(866, 244, 880, 260, "银币不足", 500);
                        mFairy.finish(AtFairyConfig.getTaskID(), 4596);
                        setTaskEnd();
                        return;
                    }
                }

               /* if (gamePublicFuntion.fail()) {
                    if (frequencyMap("fail", 3)) {
                        setTaskEnd();
                        *//**
                 * 任务出现多次战斗失败!
                 *//*
                        return;
                    }
                }*/

                result = mFairy.findPic("my10.png");
                mFairy.onTap(0.8f, result, "聊天框", 500);

                if (gamePublicFuntion.mainScene()) {


                    switch (type) {
                        case 1:

                            result = mFairy.findPic(1014, 152, 1215, 528, new String[]{"cjmy1.png"});
                            if (result.sim > 0.7f) {
                                err = 0;
                                mFairy.onTap(0.7f, result, "初级贸易", 3000);
                                return;
                            }

                            break;
                        case 2:

                            result = mFairy.findPic(1014, 152, 1215, 528, new String[]{"gjmy1.png"});
                            if (result.sim > 0.7f) {
                                err = 0;
                                mFairy.onTap(0.7f, result, "高级贸易", 3000);
                                return;
                            }

                            break;
                    }


                    taskSlide.slideRange(new int[]{3, 5, 7, 8, 9}, 2, 0);
                }


            }
        };

    }//贸易

    public void cjmy() throws Exception {
        new SingleContent(mFairy, "贸易") {

            void create() throws Exception {
                super.create();

                activityName = "bpmy.png";

                activityType = 3;

            }

            boolean actEnd(FindResult act) throws Exception {

                result = mFairy.findPic(act.x + 180, act.y - 20, act.x + 300, act.y + 70, "wan.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1125, 38, 1150, 54, "", 500);
                    setTaskName(2);
                    oneSecond = 0;
                    frequencyInit("actcount");
                    return true;
                }
                return false;
            }

            void quxiao() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic(383, 238, 935, 401, "my14.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(715, 422, 775, 451, "确定", 500);
                        return;
                    }


                    result = mFairy.findPic(386, 258, 739, 412, "my17.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(715, 422, 775, 451, "确定", 500);
                        return;
                    }


                    result = mFairy.findPic(386, 258, 739, 412, "my16.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(715, 422, 775, 451, "确定", 500);
                        return;
                    }


                    result = mFairy.findPic("my6.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("my22.png");
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic(322, 177, 762, 311, "my7.png");
                            mFairy.onTap(0.8f, result, "购买", 1000);
                            return;
                        }
                        mFairy.onTap(614, 514, 677, 537, "确定", 1000);
                        return;
                    }

                    mFairy.onTap(0.8f, qx, "取消", 500);
                }
            }//取消

            void use() throws Exception {

            }

            void content_02() throws Exception {
                timeCount(12, 99);

                result = mFairy.findPic(915, 63, 1226, 539, "my1.png");
                mFairy.onTap(0.85f, result, "帮派贸易", 500);

                result = mFairy.findPic("my2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (oneSecond()) {

                        /*switch (type) {
                            case 1:
                                mFairy.onTap(222, 600, 291, 614, "", 500);
                                break;
                            case 2:
                                mFairy.onTap(216, 663, 283, 681, "", 500);
                                break;
                        }*/

                        result = mFairy.findPic("my11.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "继续贸易", 800);
                            if (mapCount(0.8f, 470, 157, 687, 421, "my26.png")) {
                                mFairy.onTap(1121, 43, 1138, 57, "", 500);
                                setTaskEnd();
                                return;
                            }
                        }
                    }

                    FindResult start_result = mFairy.findPic("my15.png");
                    if (start_result.sim > 0.8f) {
                        for (int i = 1; i <= 18; i++) {
                            switch (i) {
                                case 1:
                                    if (AtFairyConfig.getOption("cjm1").equals("1")) {
                                        mFairy.onTap(158, 419, 179, 442, "宝象国", 1000);
                                    }
                                    break;
                                case 2:
                                    if (AtFairyConfig.getOption("cjm2").equals("1")) {
                                        mFairy.onTap(176, 293, 202, 312, "长寿村", 1000);
                                    }
                                    break;
                                case 3:
                                    if (AtFairyConfig.getOption("cjm3").equals("1")) {
                                        mFairy.onTap(366, 141, 382, 160, "凌霄宝殿", 1000);
                                    }
                                    break;
                                case 4:
                                    if (AtFairyConfig.getOption("cjm4").equals("1")) {
                                        mFairy.onTap(608, 120, 624, 137, "大雪山", 1000);
                                    }
                                    break;
                                case 5:
                                    if (AtFairyConfig.getOption("cjm5").equals("1")) {
                                        mFairy.onTap(297, 363, 318, 384, "两界山", 1000);
                                    }
                                    break;
                                case 6:
                                    if (AtFairyConfig.getOption("cjm6").equals("1")) {
                                        mFairy.onTap(783, 255, 796, 277, "洛阳城", 1000);
                                    }
                                    break;
                                case 7:
                                    if (AtFairyConfig.getOption("cjm7").equals("1")) {
                                        mFairy.onTap(851, 385, 873, 405, "建邺城", 1000);
                                    }
                                    break;
                                case 8:
                                    if (AtFairyConfig.getOption("cjm8").equals("1")) {
                                        mFairy.onTap(1055, 311, 1071, 329, "傲来渔港", 1000);
                                    }
                                    break;
                                case 9:
                                    if (AtFairyConfig.getOption("cjm9").equals("1")) {
                                        mFairy.onTap(1077, 201, 1098, 219, "花果山", 1000);
                                    }
                                    break;
                                case 10:
                                    if (AtFairyConfig.getOption("cjm10").equals("1")) {
                                        mFairy.onTap(238, 191, 268, 214, "方寸山", 1000);
                                    }
                                    break;
                                case 11:
                                    if (AtFairyConfig.getOption("cjm11").equals("1")) {
                                        mFairy.onTap(409, 240, 434, 264, "化生寺", 1000);
                                    }
                                    break;
                                case 12:
                                    if (AtFairyConfig.getOption("cjm12").equals("1")) {
                                        mFairy.onTap(395, 459, 413, 473, "魔王寨", 1000);
                                    }
                                    break;
                                case 13:
                                    if (AtFairyConfig.getOption("cjm13").equals("1")) {
                                        mFairy.onTap(547, 201, 579, 231, "大唐官府", 1000);
                                    }
                                    break;
                                case 14:
                                    if (AtFairyConfig.getOption("cjm14").equals("1")) {
                                        mFairy.onTap(649, 510, 680, 538, "狮驼岭", 1000);
                                    }
                                    break;
                                case 15:
                                    if (AtFairyConfig.getOption("cjm15").equals("1")) {
                                        mFairy.onTap(807, 163, 829, 186, "龙宫", 1000);
                                    }
                                    break;
                                case 16:
                                    if (AtFairyConfig.getOption("cjm16").equals("1")) {
                                        mFairy.onTap(948, 238, 965, 280, "女儿村", 1000);
                                    }
                                    break;
                                case 17:
                                    if (AtFairyConfig.getOption("cjm17").equals("1")) {
                                        mFairy.onTap(1025, 381, 1058, 414, "普陀山", 1000);
                                    }
                                    break;
                                case 18:
                                    if (AtFairyConfig.getOption("cjm18").equals("1")) {
                                        mFairy.onTap(1035, 509, 1061, 533, "阴曹地府", 1000);
                                    }
                                    break;
                            }

                            result = mFairy.findPic("my18.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "选择", 500);
                                if (mapCount(0.8f, 460, 158, 691, 504, "my19.png")) {
                                    setTaskEnd();
                                    return;
                                }
                            }

                            result = mFairy.findPic("my20.png");
                            mFairy.onTap(0.8f, result, 1225, 649, 1243, 671, "已勾选该城市", 500);

                        }
                        mFairy.onTap(0.8f, start_result, "开始贸易", 1000);
                    }

                } else {
                    oneSecond = 0;
                }


                result = mFairy.findPic("battle.png");
                if (result.sim > 0.8f) {
                    while (mFairy.condit()) {
                        Thread.sleep(100);

                        result = mFairy.findPic("battle.png");
                        if (result.sim < 0.8f) {
                            Thread.sleep(200);
                            break;
                        }
                    }

                    for (int i = 0; i < 20; i++) {
                        result = mFairy.findPic(431, 192, 630, 439, "my23.png");
                        if (result.sim > 0.8f) {
                            if (frequencyMap("fail", 2)) {
                                setTaskEnd();
                                return;
                            }
                        }
                    }
                }

                result = mFairy.findPic(460, 500, 809, 703, "my25.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    long t = mFairy.mMatTime(463, 393, 366, 201, 0.9f);
                    if (t > 30) {
                        mFairy.onTap(1073, 43, 1090, 65, "", 500);
                        mFairy.onTap(1185, 44, 1199, 58, "", 500);
                    }
                } else {
                    mFairy.initMatTime();
                }

                result = mFairy.findPic(410, 351, 926, 447, new String[]{"my12.png", "my24.png"});
                if (result.sim > 0.8f) {

                    if (AtFairyConfig.getOption("cjm_skill").equals("1")) {
                        mFairy.onTap(1073, 43, 1090, 65, "", 500);
                        mFairy.onTap(1185, 44, 1199, 58, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("my5.png");
                    mFairy.onTap(0.8f, result, "上交物品", 1000);

                } else {
                    result = mFairy.findPic("my5.png");
                    mFairy.onTap(0.8f, result, "上交物品", 1000);
                }

                result = mFairy.findPic("my4.png");
                mFairy.onTap(0.8f, result, "开始战斗", 2000);

                result = mFairy.findPic("my8.png");
                mFairy.onTap(0.8f, result, "上交宠物", 1000);

                result = mFairy.findPic("my9.png");
                mFairy.onTap(0.8f, result, "上交", 500);

                FindResult gmr = mFairy.findPic(706, 346, 1258, 709, new String[]{"gm.png", "gm2.png"});
                if (gmr.sim > 0.85f) {

                    result = mFairy.findPic("jyl6.png");
                    if (result.sim > 0.8f) {
                        if (frequencyMap("gmr", 1) == false) {
                            mFairy.onTap(1087, 34, 1115, 54, "", 500);
                            return;
                        }
                    }

                    result = mFairy.findPic("gmerr.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("暂时没有商品,End!"));
                        setTaskEnd();
                        return;
                    } else {
                        mFairy.onTap(0.85f, gmr, "购买", 500);

                        err = 0;
                        for (int i = 0; i < 8; i++) {
                            result = mFairy.findPic(657, 243, 742, 425, new String[]{"err1.png", "err2.png", "err3.png"});
                            if (result.sim > 0.8f) {
                                mFairy.finish(AtFairyConfig.getTaskID(), 1301);
                                setTaskEnd();
                                return;
                            }
                        }

                        result = mFairy.findPic(858, 1, 1274, 434, "close1.png");
                        mFairy.onTap(0.8f, result, "", 500);
                    }

                    result = mFairy.findPic("err4.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(866, 244, 880, 260, "银币不足", 500);
                        mFairy.finish(AtFairyConfig.getTaskID(), 4596);
                        setTaskEnd();
                        return;
                    }
                }

               /* if (gamePublicFuntion.fail()) {
                    if (frequencyMap("fail", 3)) {
                        setTaskEnd();
                        *//**
                 * 任务出现多次战斗失败!
                 *//*
                        return;
                    }
                }*/

                result = mFairy.findPic("my10.png");
                mFairy.onTap(0.8f, result, "聊天框", 500);

                if (gamePublicFuntion.mainScene()) {

                    result = mFairy.findPic(1014, 152, 1215, 528, new String[]{"bpmy1.png"});
                    if (result.sim > 0.7f) {
                        err = 0;
                        mFairy.onTap(0.7f, result, "帮派贸易", 3000);
                        return;
                    }
                   /* switch (type){
                        case 1:

                            result = mFairy.findPic(1014, 152, 1215, 528, new String[]{"cjmy1.png"});
                            if (result.sim > 0.7f) {
                                err = 0;
                                mFairy.onTap(0.7f, result, "初级贸易", 3000);
                                return;
                            }

                            break;
                        case 2:

                            result = mFairy.findPic(1014, 152, 1215, 528, new String[]{"gjmy1.png"});
                            if (result.sim > 0.7f) {
                                err = 0;
                                mFairy.onTap(0.7f, result, "高级贸易", 3000);
                                return;
                            }

                            break;
                    }*/

                    taskSlide.slideRange(new int[]{3, 5, 7, 8, 9}, 2, 0);
                }


            }
        };

    }//贸易

    public void sybt() throws Exception {
        new SingleContent(mFairy, "使用宝图") {

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                timeCount(6, 0);

                result = mFairy.findPic(1181, 545, 1276, 661, "package.png");
                mFairy.onTap(0.8f, result, "背包", 500);

                result = mFairy.findPic("package2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (oneSecond()) {
                        mFairy.onTap(717, 107, 767, 123, "", 500);
                        for (int i = 0; i < 3; i++) {
                            mFairy.ranSwipe(853, 205, 903, 543, 0, 500, (long) 500);
                        }
                    }

                    result = mFairy.findPic(433, 385, 573, 527, "sybt1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "使用", 500);
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic(636, 151, 1100, 617, "sybt.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "发现宝图", 500);
                        frequencyInit("sybt");
                        return;
                    }

                    mFairy.ranSwipe(859, 252, 898, 505, 2, 500, (long) 1000);

                    if (frequencyMap("sybt", 3)) {
                        setTaskEnd();
                        mFairy.onTap(1089, 41, 1111, 57, "", 500);
                        return;
                    }
                }
            }

            void use() throws Exception {
                gamePublicFuntion.use();
            }

            void content_02() throws Exception {
                timeCount(5, 0);

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("fail", 3)) {
                        setTaskEnd();
                        /**
                         * 一键27级任务出现多次战斗失败!
                         */
                        return;
                    }
                }

                result = mFairy.findPic(558, 398, 647, 487, "sybt2.png");
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    err = 0;
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3)) {

                    } else {
                        err = 0;
                    }
                }

            }
        };
    }//使用宝图

    private int yb = 1;
    private boolean ybool = false;

    public void yb() throws Exception {
        new SingleContent(mFairy, "运镖") {

            void create() throws Exception {
                super.create();
                yb = 1;
                ybool = false;
            }

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                ybool = false;
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.mainScene()) {
                    result = mFairy.findPic(41, 4, 265, 70, "yb1.png");
                    if (result.sim > 0.73f) {
                        mFairy.onTap(0.73f, result, "已达到长安城 点击小地图", 1500);
                        return;
                    } else {
                        mFairy.onTap(27, 34, 45, 53, "", 1000);
                    }
                }

                result = mFairy.findPic("yb2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(494, 340, 1112, 529, "yb3.png");
                    mFairy.onTap(0.8f, result, "点击长安城", 1500);
                }

                result = mFairy.findPic("yb4.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(104,69,202,460,"jian.png");
                    mFairy.onTap(0.8f,result,146,30,160,48,"关掉npc栏",3000);

                    mFairy.onTap(272,420,283,424, "郑捕头", 1500);
                    gamePublicFuntion.close();
                    setTaskName(2);
                    return;
                }

            }

            void quxiao() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {
                    result = mFairy.findPic(368, 237, 875, 446, "yb7.png");
                    if (result.sim > 0.8f) {
                        frequencyInit("qx");
                        mFairy.onTap(0.8f, result, 731, 428, 764, 440, "确定", 1000);
                        return;
                    }

                    if (frequencyMap("qx", 1)) {
                        mFairy.onTap(0.8f, qx, "取消", 500);
                    }
                }
            }//取消

            void content_02() throws Exception {
                if (timeCount(6, 0)) {
                    if (ybool) {
                        switch (yb) {
                            case 1:
                                if (AtFairyConfig.getOption("yb2").equals("1")) {
                                    yb = 2;
                                } else {
                                    setTaskEnd();
                                }
                                break;
                            case 2:
                                setTaskEnd();
                                return;
                        }
                    }
                }

                gamePublicFuntion.fail();

                result = mFairy.findPic(890, 167, 1270, 536, new String[]{"yb5.png", "yin_yb5.png"});
                if (result.sim > 0.85f) {
                    ybool = true;
                    switch (yb) {
                        case 1:
                            if (AtFairyConfig.getOption("yb1").equals("1")) {
                                result = mFairy.findPic(890, 167, 1270, 536, new String[]{"yb5.png", "yin_yb5.png"});
                                mFairy.onTap(0.85f, result, "普通运镖", 3000);
                            } else {
                                yb = 2;
                            }
                            break;
                        case 2:
                            if (AtFairyConfig.getOption("yb2").equals("1")) {
                                result = mFairy.findPic(890, 167, 1270, 536, new String[]{"yb6.png", "yin_yb6.png"});
                                mFairy.onTap(0.85f, result, "高级运镖", 3000);
                            } else {
                                setTaskEnd();
                                return;
                            }
                            break;
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3) == false) {
                        err = 0;
                    }
                }

                result = mFairy.findPic("yb8.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    ybool = false;
                }


                result = mFairy.findPic(new String[]{"yb9.png", "jia2.png"});
                if (result.sim > 0.85f) {
                    mFairy.onTap(858, 244, 879, 263, "银币不足", 500);
                    setTaskEnd();
                    return;
                }
            }
        };
    }//运镖

    public void sjqy() throws Exception {
        new SingleContent(mFairy, "三界奇缘") {

            void create() throws Exception {
                super.create();
                activityName = "sjqy.png";
                activityType = 1;
            }

            void content_02() throws Exception {
                timeCount(5, 0);

                result = mFairy.findPic(new  String[]{"sjqy1.png","sjqy3.png","sjqy4.png"});
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("dt.png");
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return;
                    }

                    int rand = (int) Math.random() * 3 + 1;
                    switch (rand) {
                        case 1:
                            mFairy.onTap(551, 234, 615, 333, "A", 800);
                            break;
                        case 2:
                            mFairy.onTap(766, 232, 826, 319, "B", 800);
                            break;
                        case 3:
                            mFairy.onTap(979, 246, 1043, 329, "C", 800);
                            break;
                    }
                }
            }
        };
    }//三界奇缘

    private boolean kjxs = false;

    public void kjxs() throws Exception {
        new SingleContent(mFairy, "科举乡试") {

            void create() throws Exception {
                super.create();
                activityName = "kjxs.png";
                activityType = 1;
                kjxs = false;
            }

            void content_02() throws Exception {
                if (timeCount(5, 0)) {
                    if (kjxs) {
                        setTaskEnd();
                        gamePublicFuntion.close();
                        return;
                    }
                }

                result = mFairy.findPic(425, 88, 785, 230, "kjxs1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    kjxs = true;
                    mFairy.onTap(553, 354, 702, 379, "A", 800);
                }
            }
        };
    }//科举乡试

    public void ggl() throws Exception {
        new SingleContent(mFairy, "刮刮乐") {

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;
            }

            void inOperation() throws Exception {
                gamePublicFuntion.auto();
                gamePublicFuntion.rightZoom();
                gamePublicFuntion.task();
                gamePublicFuntion.battle();

                for (int i = 0; i < 3; i++) {
                    use();
                }

                quxiao();

                result = mFairy.findPic("nn5.png");
                mFairy.onTap(0.8f, result, "奖励", 500);

                result = mFairy.findPic("nn6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 702, 537, 766, 551, "查看", 3000);
                    setTaskName(0);
                    return;
                }
            }

            void use() throws Exception {
                gamePublicFuntion.use();
            }

            void content_01() throws Exception {
                timeCount(6, 0);

                result = mFairy.findPic("fl.png");
                mFairy.onTap(0.8f, result, "福利", 500);

                result = mFairy.findPic(new String[]{"fl1.png","fl8.png"});
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(new String[]{"fl7.png","fl9.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 1068, 66, 1089, 83, "已获得奖励", 500);
                        setTaskEnd();
                        return;
                    } else {
                        result = mFairy.findPic(new String[]{"fl3.png","fl10.png"});
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "打开刮刮乐", 500);
                            return;
                        }
                    }

                    result = mFairy.findPic(198, 86, 374, 383, "fl2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "每日福利", 500);
                    } else {
                        mFairy.ranSwipe(251, 235, 287, 372, 0, 500, (long) 500);
                    }
                }

                result = mFairy.findPic(new String[]{"fl4.png","ggl.png","ggl1.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.ranSwipe(660, 370, 1024, 390, 1, 500, (long) 500);
                    mFairy.ranSwipe(660, 380, 1030, 400, 1, 500, (long) 500);
                    mFairy.ranSwipe(660, 390, 1027, 410, 1, 500, (long) 500);
                    mFairy.ranSwipe(660, 400, 1028, 420, 1, 500, (long) 500);
                    mFairy.ranSwipe(661, 410, 1028, 430, 1, 500, (long) 500);
                    mFairy.ranSwipe(661, 420, 1028, 440, 1, 500, (long) 500);
                    mFairy.ranSwipe(661, 430, 1028, 450, 1, 500, (long) 500);
                    mFairy.ranSwipe(661, 440, 1028, 460, 1, 500, (long) 500);
                    mFairy.ranSwipe(661, 450, 1028, 470, 1, 500, (long) 500);

                    Thread.sleep(1000);

                    for (int i = 1; i <= 3; i++) {
                        if (i == 1) {
                            result = mFairy.findPic(831, 590, 896, 642, "fl6.png");
                            if (result.sim < 0.8f) {
                                mFairy.onTap(830, 593, 846, 609, "奖励 " + i, 500);
                                break;
                            }
                        }
                        if (i == 2) {
                            result = mFairy.findPic(952, 594, 1018, 654, "fl6.png");
                            if (result.sim < 0.8f) {
                                mFairy.onTap(943, 592, 965, 613, "奖励 " + i, 500);
                                break;
                            }
                        }
                        if (i == 3) {
                            result = mFairy.findPic(1065, 593, 1136, 663, "fl6.png");
                            if (result.sim < 0.8f) {
                                mFairy.onTap(1058, 593, 1082, 612, "奖励 " + i, 500);
                                break;
                            }
                        }
                    }
                    mFairy.onTap(1136,82,1148,97, "", 500);
                    return;
                }

            }
        };
    }//刮刮乐

    public void acte() throws Exception {
        new SingleContent(mFairy, "领活跃") {

            void content_01() throws Exception {
                timeCount(6, 0);

                result = mFairy.findPic(228, 5, 747, 99, "activity.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "点击活动按钮", 800);
                }

                result = mFairy.findPic(new String[]{"activity1.png","activity2.png"});
                if (result.sim > 0.8f) {
                    for (int i = 1; i <= 5; i++) {
                        switch (i) {
                            case 1:
                                mFairy.onTap(430, 548, 475, 588, "1", 200);
                                mFairy.onTap(430, 548, 475, 588, "1", 500);
                                break;
                            case 2:
                                mFairy.onTap(588, 552, 620, 578, "2", 200);
                                mFairy.onTap(588, 552, 620, 578, "2", 500);
                                break;
                            case 3:
                                mFairy.onTap(735, 547, 775, 584, "3", 200);
                                mFairy.onTap(735, 547, 775, 584, "3", 500);
                                break;
                            case 4:
                                mFairy.onTap(880, 548, 923, 579, "4", 200);
                                mFairy.onTap(880, 548, 923, 579, "4", 500);
                                break;
                            case 5:
                                mFairy.onTap(1030, 549, 1075, 584, "5", 200);
                                mFairy.onTap(1030, 549, 1075, 584, "5", 500);
                                break;
                        }
                    }
                    mFairy.onTap(1124, 33, 1151, 60, "", 500);
                    setTaskEnd();
                    return;
                }
            }
        };
    }//

    public void ls() throws Exception {
        new SingleContent(mFairy, "领双") {

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                timeCount(6, 0);

                result = mFairy.findPic(236, 6, 783, 100, "guaji.png");
                mFairy.onTap(0.8f, result, "挂机", 500);

                result = mFairy.findPic("guaji1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    int num = getNumberAssembly(AtFairyConfig.getOption("ls"));
                    if (num > 20) {
                        num = 20;
                    }
                    for (int i = 0; i < num; i++) {
                        mFairy.onTap(1004, 636, 1042, 651, "领取", 500);

                        result = mFairy.findPic(new String[]{"guaji3.png", "guaji5.png"});
                        if (result.sim > 0.75f) {
                            mFairy.onTap(508, 410, 555, 424, "双倍点数不足", 500);
                            break;
                        }
                    }

                    mFairy.onTap(1124, 44, 1147, 58, "", 500);
                    setTaskEnd();
                    return;
                }
            }
        };
    }//领双

    public void bpqd() throws Exception {
        new SingleContent(mFairy, "帮派签到") {

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;
            }

            void use() throws Exception {
                gamePublicFuntion.use();
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("bpqd1.png");
                mFairy.onTap(0.8f, result, "加号", 1500);

                result = mFairy.findPic(522, 548, 1258, 714, "bpqd2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "帮派", 1000);
                    err = 0;
                }

                result = mFairy.findPic(1070, 58, 1209, 663, "bpqd3.png");
                mFairy.onTap(0.8f, result, "福利", 1000);

                result = mFairy.findPic(466, 12, 795, 75, "bpqd5.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(836, 64, 1139, 204, "bpqd4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "签到", 1000);
                        mFairy.onTap(1093, 38, 1110, 56, "", 500);
                        setTaskEnd();
                        return;
                    }
                }
            }
        };
    }//帮派签到

    public void bpfh() throws Exception {
        new SingleContent(mFairy, "帮派分红") {

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;
            }

            void use() throws Exception {
                gamePublicFuntion.use();
            }

            void content_01() throws Exception {
                timeCount(8, 99);

                result = mFairy.findPic("bpqd1.png");
                mFairy.onTap(0.8f, result, "加号", 1500);

                result = mFairy.findPic(522, 548, 1258, 714, "bpqd2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "帮派", 1000);
                    err = 0;
                }
                result = mFairy.findPic(1070, 58, 1209, 663, "bpqd3.png");
                mFairy.onTap(0.8f, result, "福利", 1000);

                result = mFairy.findPic(466, 12, 795, 75, "bpqd5.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(823, 39, 1124, 684, "bpqd6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "领取", 1000);
                        mFairy.onTap(1093, 38, 1110, 56, "", 500);
                        setTaskEnd();
                        return;
                    } else {
                        mFairy.ranSwipe(785, 561, 785, 350, 500, 1000);
                    }
                }
            }
        };
    }//帮派分红

    public void kg() throws Exception {
        new SingleContent(mFairy, "考古") {

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;
            }

            void use() throws Exception {
                gamePublicFuntion.use();
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("bpqd1.png");
                mFairy.onTap(0.8f, result, "加号", 1500);

                result = mFairy.findPic(522, 548, 1258, 714, "kg1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "技能", 1000);
                    err = 0;
                }
                result = mFairy.findPic(1070, 58, 1209, 663, "kg2.png");
                mFairy.onTap(0.8f, result, "辅助", 1000);

                result = mFairy.findPic(466, 12, 795, 75, "kg3.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("kg5.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(478, 116, 1120, 305, "kg6.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "考古", 500);
                            err = 0;
                        } else {
                            if (frequencyMap("zhao_kg", 2)) {
                                mFairy.ranSwipe(968, 385, 592, 385, 500, 1000);
                            }
                        }
                        return;
                    } else {
                        result = mFairy.findPic(124, 31, 378, 494, "kg4.png");
                        if (result.sim > 0.8f) {
                            err = 0;
                            mFairy.onTap(0.8f, result, "工坊技能", 500);
                        } else {
                            mFairy.ranSwipe(253, 159, 256, 480, 500, 1000);
                            return;
                        }
                    }
                }

                result = mFairy.findPic("kg8.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("kg7.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1114, 32, 1129, 45, "", 500);
                        mFairy.onTap(1099, 43, 1115, 53, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("kg13.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("kg14.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "添加", 500);
                            return;
                        } else {
                            mFairy.onTap(394, 230, 425, 264, "", 500);
                            mFairy.onTap(775, 524, 812, 540, "使用", 500);
                        }
                        return;
                    }

                    result = mFairy.findPic(1067, 23, 1181, 608, "kg9.png");
                    mFairy.onTap(0.8f, result, "技能生产", 1000);


                    result = mFairy.findPic("kg12.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "添加洛阳铲", 500);
                        return;
                    }


                    if (frequencyMap("kg", 2)) {
                        result = mFairy.findPic("kg10.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "考古", 500);
                            if (mapCount(0.8f, 586, 253, 658, 510, "kg11.png")) {
                                mFairy.onTap(1114, 32, 1129, 45, "", 500);
                                mFairy.onTap(1099, 43, 1115, 53, "", 500);
                                setTaskEnd();
                                return;
                            }
                            setTaskName(2);
                            return;
                        }
                    }
                }

                result = mFairy.findPic(821, 490, 1088, 688, "kg15.png");
                if (result.sim > 0.8f) {
                    gamePublicFuntion.gm();
                    mFairy.onTap(1141, 147, 1160, 169, "", 500);
                }

                result = mFairy.findPic("err4.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(866, 244, 880, 260, "银币不足", 500);
                    mFairy.finish(AtFairyConfig.getTaskID(), 4596);
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(8, 0);

                result = mFairy.findPic(990, 493, 1168, 649, "kg16.png");
                mFairy.onTap(0.8f, result, "挖掘", 3500);

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3)) {

                    } else {
                        err = 0;
                    }
                }
            }
        };
    }//考古

    public void ck(final int map) throws Exception {
        new SingleContent(mFairy, "采矿") {

            int position;

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;
                position = 1;
            }

            void use() throws Exception {
                result = mFairy.findPic(1023, 527, 1117, 637, "use.png");
                if(result.sim>0.8f){
                    gamePublicFuntion.useClose();
                }
            }

            void content_01() throws Exception {
                timeCount(10, 0);
                switch (map) {
                    case 1:
                        result = mFairy.findPic(41, 4, 265, 70, "kuang1.png");
                        if (result.sim > 0.73f) {
                            setTaskName(2);
                            return;
                        }

                        result = mFairy.findPic("kuang2.png");
                        if (result.sim > 0.8f) {
                            err = 0;
                            mFairy.onTap(1146, 345, 1156, 373, "", 1000);
                            mFairy.onTap(925, 370, 940, 386, "东海湾", 2000);
                            return;
                        }

                        break;
                    case 2:
                        result = mFairy.findPic(41, 4, 265, 70, "kuang3.png");
                        if (result.sim > 0.73f) {
                            setTaskName(2);
                            return;
                        }

                        result = mFairy.findPic("kuang2.png");
                        if (result.sim > 0.8f) {
                            err = 0;
                            mFairy.onTap(1146, 345, 1156, 373, "", 1000);
                            mFairy.onTap(219, 367, 234, 386, "长寿村", 2000);
                            return;
                        }

                        break;
                    case 3:
                        result = mFairy.findPic(41, 4, 265, 70, "kuang9.png");
                        if (result.sim > 0.73f) {
                            setTaskName(2);
                            return;
                        }

                        result = mFairy.findPic("kuang2.png");
                        if (result.sim > 0.8f) {
                            err = 0;
                            mFairy.onTap(1146, 345, 1156, 373, "", 1000);
                            mFairy.onTap(1078,235,1097,263, "花果山", 2000);
                            return;
                        }

                        break;
                    case 4:
                        result = mFairy.findPic(41, 4, 265, 70, "kuang10.png");
                        if (result.sim > 0.73f) {
                            setTaskName(2);
                            return;
                        }

                        result = mFairy.findPic("kuang2.png");
                        if (result.sim > 0.8f) {
                            err = 0;
                            mFairy.onTap(1146, 345, 1156, 373, "", 1000);
                            mFairy.onTap(581,129,601,153, "大雪山", 2000);
                            return;
                        }

                        break;

                    case 5:
                        result = mFairy.findPic(41, 4, 265, 70, "kuang11.png");
                        if (result.sim > 0.73f) {
                            setTaskName(2);
                            return;
                        }

                        result = mFairy.findPic("kuang2.png");
                        if (result.sim > 0.8f) {
                            err = 0;
                            mFairy.onTap(1146, 345, 1156, 373, "", 1000);
                            mFairy.onTap(393,421,401,434, "两界山", 2000);
                            return;
                        }

                        break;

                }

                if (gamePublicFuntion.mainScene()) {
                    if (frequencyMap("click", 3)) {
                        mFairy.onTap(24, 34, 48, 52, "", 1500);
                    }
                }
            }

            void inOperation() throws Exception {
                gamePublicFuntion.skip();

                result = mFairy.findPic("renwu.png");
                mFairy.onTap(0.8f,result,1090,41,1107,59,"关闭头像界面",1000);

                for (int i = 0; i < 3; i++) {
                    use();
                }

                quxiao();

                result = mFairy.findPic(767, 101, 841, 176, "jia3.png");
                mFairy.onTap(0.8f, result, "关闭小弹框", 500);

                result = mFairy.findPic("nn5.png");
                mFairy.onTap(0.8f, result, "奖励", 500);

                result = mFairy.findPic("jia1.png");
                mFairy.onTap(0.8f, result, 1068, 65, 1082, 82, "福利界面-关闭", 500);

                result = mFairy.findPic("nn6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 702, 537, 766, 551, "查看", 3000);
                    setTaskName(0);
                    return;
                }
            }

            void content_02() throws Exception {
                if (timeCount(10, 3)) {
                    gamePublicFuntion.close();
                    if (position > 3) {
                        position = 1;
                    }
                    return;
                }

                result = mFairy.findPic("kuang7.png");
                mFairy.onTap(0.8f, result, "挖掘", 8000);

                result = mFairy.findPic("kuang6.png");
                mFairy.onTap(0.8f, result, "栏", 1000);

                if (gamePublicFuntion.mainScene()) {

                    boolean bool=false;

                    if(((result = mFairy.findPic(419, 120, 1274, 709, new String[]{"kuang4.png", "kuang5.png"})).sim > 0.73f) ||
                            ((result = mFairy.findPic(168, 120, 562, 600, new String[]{"kuang4.png", "kuang5.png"})).sim > 0.73f)){
                        mFairy.onTap(result.x + 15, result.y - 30, result.x + 25, result.y - 25, "点击矿石", 500);
                        bool=true;
                    }

                    if((result = mFairy.findPic(0, 0, 1279, 719, "wk2.png")).sim > 0.8f){
                        mFairy.onTap(result.x , result.y , result.x , result.y , "点击矿石", 500);
                        bool=true;
                    }


                    if (bool){
                        err = 0;
                        for (int i = 0; i < 15; i++) {
                            Thread.sleep(500);

                            result = mFairy.findPic("kuang7.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "挖掘", 8000);
                                break;
                            }

                            result = mFairy.findPic(561, 148, 693, 492, "kaung8.png");
                            if (result.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("活力不足"));
                                setTaskEnd();
                                return;
                            }
                            result = mFairy.findPic(430, 117, 608, 503, "kaung9.png");
                            if (result.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("等级不足"));
                                setTaskEnd();
                                return;
                            }
                        }
                    }
                }
            }

            void content_03() throws Exception {
                timeCount(10,0);

                result = mFairy.findPic(9, 11, 654, 307, "kuang8.png");
                if (result.sim > 0.8f) {
                    err=0;
                    switch (map) {
                        case 1://东海湾
                            switch (position) {
                                case 1:
                                    mFairy.onTap(469,214,480,224,"",1000);
                                    break;
                                case 2:
                                    mFairy.onTap(690,278,700,290,"",1000);
                                    break;
                                case 3:
                                    mFairy.onTap(503,437,512,445,"",1000);
                                    break;
                            }
                            break;


                        case 2://长寿村
                            switch (position) {
                                case 1:
                                    mFairy.onTap(578,260,589,270,"",1000);
                                    break;
                                case 2:
                                    mFairy.onTap(526,423,538,432,"",1000);
                                    break;
                                case 3:
                                    mFairy.onTap(788,461,792,468,"",1000);
                                    break;
                            }
                            break;


                        case 3://花果山
                            switch (position) {
                                case 1:
                                    mFairy.onTap(793,326,800,335,"",1000);
                                    break;
                                case 2:
                                    mFairy.onTap(546,293,554,300,"",1000);
                                    break;
                                case 3:
                                    mFairy.onTap(473,496,484,501,"",1000);
                                    break;
                            }
                            break;

                        case 4://大雪山
                            switch (position) {
                                case 1:
                                    mFairy.onTap(490,297,498,305,"",1000);
                                    break;
                                case 2:
                                    mFairy.onTap(624,344,632,356,"",1000);
                                    break;
                                case 3:
                                    mFairy.onTap(786,521,797,527,"",1000);
                                    break;
                            }
                            break;

                        case 5://两界山
                            switch (position) {
                                case 1:
                                    mFairy.onTap(398,387,408,398,"",1000);
                                    break;
                                case 2:
                                    mFairy.onTap(649,398,663,406,"",1000);
                                    break;
                                case 3:
                                    mFairy.onTap(900,423,911,433,"",1000);
                                    break;
                            }
                            break;
                    }

                    position++;

                    gamePublicFuntion.close();

                    Thread.sleep(5000);

                    setTaskName(2);
                    return;

                } else {
                    if(frequencyMap("a",2)){
                        mFairy.onTap(142,33,168,51,"点开小地图",2000);
                    }
                }

            }
        };
    }//采矿

    public void kgcs() throws Exception {
        new SingleContent(mFairy, "考古出售") {

            void create() throws Exception {
                super.create();
            }

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.mainScene()) {
                    result = mFairy.findPic(41, 4, 265, 70, "kgcs1.png");
                    if (result.sim > 0.73f) {
                        mFairy.onTap(0.73f, result, "已达到洛阳城 点击小地图", 1500);
                        return;
                    } else {
                        mFairy.onTap(27, 34, 45, 53, "", 1000);
                    }
                }

                result = mFairy.findPic("yb2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.ranSwipe(889, 350, 500, 350, 500, 1000);


                    result = mFairy.findPic(341, 193, 1139, 400, "kgcs2.png");
                    mFairy.onTap(0.8f, result, "点击洛阳城", 1500);
                }

                result = mFairy.findPic("kgcs6.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(430, 541, 438, 548, "杂货商人", 1500);
                    gamePublicFuntion.close();
                    setTaskName(2);
                    return;
                }

            }

            void quxiao() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic(368, 237, 875, 446, "yb7.png");
                    if (result.sim > 0.8f) {
                        frequencyInit("qx");
                        mFairy.onTap(0.8f, result, 731, 428, 764, 440, "确定", 1000);
                        return;
                    }

                    result = mFairy.findPic(395, 256, 888, 466, "jia6.png");
                    if (result.sim > 0.8f) {
                        frequencyInit("qx");
                        mFairy.onTap(0.8f, result, 726, 414, 772, 431, "确定", 1000);
                        return;
                    }

                    if (frequencyMap("qx", 1)) {
                        mFairy.onTap(0.8f, qx, "取消", 500);
                    }
                }
            }//取消

            void content_02() throws Exception {
                timeCount(10, 0);


                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3) == false) {
                        err = 0;
                    }
                }

                result = mFairy.findPic(883, 145, 1247, 537, "kgcs3.png");
                mFairy.onTap(0.8f, result, "出售古董", 1000);

                result = mFairy.findPic("kgcs4.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("kgcs7.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1144, 146, 1167, 168, "", 500);
                        setTaskEnd();
                        return;
                    }

                    mFairy.onTap(916, 610, 983, 627, "售出", 500);

                }
            }
        };
    }//考古出售

    public void nn() throws Exception {
        new TaskContent(mFairy, "新手引导") {

            void init() throws Exception {
                setTaskName(1);
                gamePublicFuntion.close();
            }

            void nn() throws Exception {

                result = mFairy.findPic("nn9.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("nn18.png");
                if (result.sim > 0.8f) {
                    if (frequencyMap("huajia", 10)) {
                        if (oneSecond()) {
                            mFairy.onTap(1038, 637, 1061, 662, "", 1000);
                            mFairy.onTap(974, 481, 1004, 509, "", 1000);
                        }
                    }
                }

                result = mFairy.findPic("nn12.png");
                mFairy.onTap(0.8f, result, 1146, 644, 1162, 670, "技能", 500);

                result = mFairy.findPic("nn19.png");
                mFairy.onTap(0.8f, result, 1112, 37, 1134, 57, "宠物-关闭", 500);

                result = mFairy.findPic("nn13.png");
                mFairy.onTap(0.8f, result, 1143, 651, 1162, 667, "助战", 500);

                result = mFairy.findPic("nn8.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "一键升级", 300);
                    mFairy.onTap(1094, 42, 1114, 60, "", 500);
                }
                result = mFairy.findPic("auto.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "自动", 1500);

                    result = mFairy.findPic("nn4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(659, 636, 674, 653, "", 500);
                        mFairy.onTap(338, 274, 363, 300, "", 500);

                        return;
                    }

                    result = mFairy.findPic("auto.png");
                    if (result.sim > 0.85f) {
                        result = mFairy.findPic("nn1.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "法术", 300);
                            mFairy.onTap(954, 340, 979, 366, "", 300);
                            mFairy.onTap(833, 338, 857, 365, "", 1000);
                            mFairy.onTap(340, 235, 360, 260, "", 300);
                            mFairy.onTap(227, 320, 245, 342, "", 300);
                            mFairy.onTap(938, 392, 964, 418, "", 300);
                        }

                        result = mFairy.findPic("nn7.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(1220, 438, 1245, 467, "", 1000);
                            mFairy.onTap(330, 256, 348, 278, "", 500);
                        }
                    }
                }

                result = mFairy.findPic("nn2.png");
                mFairy.onTap(0.8f, result, "我有经验", 300);

                result = mFairy.findPic("nn10.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1224, 575, 1245, 600, "换上高等装备", 1000);
                    err = 0;
                }

                result = mFairy.findPic("nn21.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(846, 111, 1058, 435, "nn3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "领取", 300);
                        mFairy.onTap(1065, 65, 1083, 81, "", 500);
                    }
                }

                result = mFairy.findPic("nn5.png");
                mFairy.onTap(0.8f, result, "奖励", 500);

                result = mFairy.findPic("nn6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 702, 537, 766, 551, "查看", 3000);

                    result = mFairy.findPic("nn16.png");
                    mFairy.onTap(0.85f, result, "", 500);
                }
                result = mFairy.findPic("package1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(435, 100, 1087, 610, "nn20.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, result.x + 135, result.y + 25, result.x + 140, result.y + 27, "装备提示", 1000);
                    } else {
                        mFairy.onTap(1032, 181, 1060, 214, "点击装备", 1000);
                    }

                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic(797, 401, 1027, 673, "nn11.png");
                        if (result.sim > 0.8f) {
                            i = 0;
                            mFairy.onTap(0.8f, result, "装备", 500);
                        }
                    }
                    mFairy.onTap(1098, 40, 1112, 57, "", 500);
                }

                result = mFairy.findPic("zhuzhan.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(147, 152, 270, 631, "zhuzhan2.png");
                    mFairy.onTap(0.8f, result, "助战", 500);

                    result = mFairy.findPic(666, 150, 1111, 288, "zhuzhan1.png");
                    if (result.sim > 0.8f) {
                        frequencyInit("zhuzhan");
                        mFairy.onTap(0.8f, result, "加号", 500);
                        return;
                    }

                    if (frequencyMap("zhuzhan", 3)) {
                        mFairy.onTap(1125, 41, 1146, 62, "", 500);
                    }
                }
            }

            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic("renwu.png");
                mFairy.onTap(0.8f,result,1090,41,1107,59,"关闭头像界面",1000);
            }

            void content_01() throws Exception {
                timeCount(15, 0);

                nn();

                result = mFairy.findPic(916, 125, 1222, 541, "sm6.png");
                if (result.sim > 0.85f) {
                    mFairy.initMatTime();
                    mFairy.onTap(0.85f, result, "师门任务", 1000);
                } else {
                    if (gamePublicFuntion.shi()) {
                        mFairy.initMatTime();
                    }
                }

                gamePublicFuntion.battle();
                gamePublicFuntion.skip();
                gamePublicFuntion.gm();
                gamePublicFuntion.zheng();
                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("fail", 3)) {
                        setTaskEnd();
                        /**
                         * 任务出现多次战斗失败!
                         */
                        return;
                    }
                }
                gamePublicFuntion.task();
                gamePublicFuntion.rightZoom();
                gamePublicFuntion.use();

                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.85f) {
                    mFairy.onTap(0.85f, qx, "取消", 500);
                }

                result = mFairy.findPic("close2.png");
                mFairy.onTap(0.8f, result, "x", 300);

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3)) {
                        mFairy.initMatTime();

                        result = mFairy.findPic(new String[]{"nn17.png","nn22.png"});
                        if (result.sim > 0.75f) {
                            setTaskEnd();
                            return;
                        }

                        result = mFairy.findPic(1032, 278, 1257, 359, new String[]{"nn14.png", "sm4.png"});
                        if (result.sim > 0.75f) {
                            mFairy.onTap(0.75f, result, "师门", 2000);
                            return;
                        }

                        result = mFairy.findPic(1032, 278, 1257, 359, "nn15.png");
                        if (result.sim > 0.75f) {
                            mFairy.onTap(0.75f, result, "红尘试炼", 2000);
                            return;
                        }

                        mFairy.onTap(1074, 210, 1152, 229, "点击主线", 2000);
                    } else {
                        err = 0;
                    }
                }

                result = mFairy.findPic(647, 329, 1254, 672, "sm5.png");
                mFairy.onTap(0.8f, result, "上交", 500);


                result = mFairy.findPic(430,488,876,588,new String[]{"sm1.png","sm14.png","wanc2.png"});
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("sm2.png");
                    mFairy.onTap(0.9f, result, "自动选择", 500);

                    result = mFairy.findPic(338, 390, 1163, 540, "sm3.png");
                    mFairy.onTap(0.7f, result, "选择", 500);

                    result = mFairy.findPic(328, 404, 1164, 528, new String[]{"sm10.png","sm13.png","wanc1.png"});
                    mFairy.onTap(0.7f, result, "去完成", 2000);

                    result = mFairy.findPic(328, 404, 1164, 528, new String[]{"sm11.png","jixu.png"});
                    mFairy.onTap(0.7f, result, "继续任务", 2000);

                    result = mFairy.findPic(338, 390, 1163, 540, new String[]{"sm7.png","wanc.png"});
                    mFairy.onTap(0.85f, result, 1147, 162, 1160, 176, "", 500);

                }

                result = mFairy.findPic("sm8.png");
                mFairy.onTap(0.85f, result, "交付宠物", 500);

                result = mFairy.findPic(492, 312, 754, 427, "sm9.png");
                mFairy.onTap(0.85f, result, 1085, 389, 1134, 427, "获取图签", 500);


            }
        };
    }//新手引导

}