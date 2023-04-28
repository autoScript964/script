package com.script.fairy;

import com.script.content.ScProxy;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.List;

public class SingleTask {
    public GamePublicFuntion gamePublicFuntion;
    public AtFairyImpl mFairy;
    private int hour;
    private int minute;
    private int week;

    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }


    private int nn = 0;
    public void nn() throws Exception {
        new TaskContent(mFairy, "新手引导") {

            void create() throws Exception {
                super.create();
                ScProxy.config().Level().capturing(5);
            }

            void init() throws Exception {
                for (int i = 0; i < 3; i++) {
                    gamePublicFuntion.close(1);
                    result = gamePublicFuntion.qx();
                    mFairy.onTap(0.8f, result, "", 500);
                }

                setTaskName(1);
                judgeOneSecond = 1;
            }

            void nn() throws Exception {
                result = mFairy.findPic("nn1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "跳过剧情", 800);
                    mFairy.onTap(412, 435, 483, 450, "", 300);
                    err = 0;
                }

                result = mFairy.findPic("nn33.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("金水火"));
                    mFairy.onTap(974, 638, 992, 658, "", 500);
                    mFairy.onTap(1085, 641, 1103, 659, "", 500);
                    mFairy.onTap(1186, 640, 1212, 664, "", 500);
                    return;
                }

                result = mFairy.findPic(963, 187, 1207, 412, "nn5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1213, 294, 1247, 319, "点击跳跃", 300);
                    mFairy.initMatTime();
                    return;
                }

                result = mFairy.findPic("nn36.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "采取强硬手段", 500);
                } else {
                    result = mFairy.findPic("nn26.png");
                    mFairy.onTap(0.8f, result, "甲", 500);
                }

                result = mFairy.findPic("nn25.png");
                mFairy.onTap(0.8f, result, "叉 - 添加好友", 500);

                result = mFairy.findPic("nn53.png");
                mFairy.onTap(0.8f, result, "我是大神", 500);

                /*result = mFairy.findPic("nn45.png");
                mFairy.onTap(0.8f, result, 1209, 57, 1226, 68, "叉 - 创建帮派", 500);
*/
                result = mFairy.findPic("nn37.png");
                mFairy.onTap(0.8f, result, "叉 - 图书", 500);

                result = mFairy.findPic("nn12.png");
                mFairy.onTap(0.8f, result, "叉 - 成就", 500);

                /*result = mFairy.findPic("nn24.png");
                mFairy.onTap(0.8f, result, 807, 432, 851, 445, "暂时不加入帮派", 500);
*/
                result = mFairy.findPic("nn35.png");
                mFairy.onTap(0.8f, result, 815, 435, 841, 448, "暂时不添加好友", 500);

                result = mFairy.findPic(1045, 3, 1265, 50, "nn2.png");
                mFairy.onTap(0.85f, result, "跳过教学", 500);

                result = mFairy.findPic("nn10.png");
                mFairy.onTap(0.8f, result, "跳过动画", 500);

                result = mFairy.findPic("nn38.png");
                mFairy.onTap(0.8f, result, 974, 303, 994, 318, "使用药品", 1000);

                result = mFairy.findPic("nn44.png");
                mFairy.onTap(0.8f, result, 1199, 434, 1221, 460, "更换目标", 500);

                result = mFairy.findPic("nn46.png");
                mFairy.onTap(0.8f, result, 1229, 29, 1248, 42, "叉 - 宠物界面", 2000);

                result = mFairy.findPic(new String[]{"nn3.png", "nn4.png", "nn6.png"});
                if (result.sim > 0.8f) {
                    mFairy.ranSwipe(152, 587, 269, 463, 200, (long) 500, 1);
                }

                result = mFairy.findPic("nn13.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("泡泡选择"));
                    mFairy.onTap(805, 416, 848, 455, "", 500);
                    mFairy.onTap(608, 565, 662, 580, "", 500);
                    mFairy.onTap(799, 433, 860, 454, "", 500);
                    return;
                }

                result = mFairy.findPic("nn16.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1013, 321, 1058, 346, "", 500);
                    mFairy.onTap(1183, 69, 1201, 85, "", 500);
                }

                result = mFairy.findPic("nn20.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("技能界面"));
                    Thread.sleep(1500);
                    mFairy.onTap(448, 97, 507, 115, "", 500);
                    mFairy.onTap(970, 624, 1028, 634, "", 500);
                    return;
                }

                result = mFairy.findPic("nn39.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(1000);
                    LtLog.e(mFairy.getLineInfo("炼丹界面"));
                    for (int i = 0; i < 4; i++) {
                        mFairy.onTap(504, 631, 532, 653, "", 500);
                    }
                    for (int i = 0; i < 4; i++) {
                        mFairy.onTap(736, 630, 760, 656, "", 500);
                    }
                    mFairy.onTap(1112, 636, 1138, 659, "炼制", 6000);
                    return;
                }

                result = mFairy.findPic("nn40.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("诗词填写"));
                    result = mFairy.findPic(783, 349, 1180, 635, "nn41.png");
                    if (result.sim > 0.8f) {
                        mFairy.ranSwipe(result.x, result.y, 930, 130, 500, (long) 500, 6);
                    }
                    return;
                }

                result = mFairy.findPic("nn31.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("装备药剂>>>"));
                    mFairy.onTap(1210, 296, 1234, 345, "", 500);
                    mFairy.onTap(910, 105, 970, 121, "", 500);
                    mFairy.onTap(797, 194, 813, 215, "", 500);
                    mFairy.onTap(783, 408, 814, 433, "", 1000);
                    mFairy.onTap(638, 468, 680, 485, "", 1000);
                    setTaskName(0);
                    return;
                }
            }

            void dakai() throws Exception {
                result = mFairy.findPic("nn14.png");
                mFairy.onTap(0.8f, result, 222, 30, 242, 46, "打开召唤兽", 500);

                result = mFairy.findPic(843, 44, 989, 153, "nn15.png");
                mFairy.onTap(0.8f, result, 894, 24, 919, 47, "打开福利", 500);

                result = mFairy.findPic(1036, 150, 1195, 256, "nn18.png");
                mFairy.onTap(0.8f, result, 1220, 192, 1244, 209, "加开缩放栏", 500);

                result = mFairy.findPic(1046, 231, 1143, 334, "nn19.png");
                mFairy.onTap(0.8f, result, 1221, 264, 1243, 287, "打开技能", 500);

                result = mFairy.findPic(new String[]{"nn22.png"});
                mFairy.onTap(0.8f, result, 1217, 189, 1238, 211, "打开缩放栏", 500);

                /*result = mFairy.findPic("nn23.png");
                mFairy.onTap(0.8f, result, 1219, 342, 1237, 368, "打开帮派", 500);
*/
                result = mFairy.findPic("nn30.png");
                mFairy.onTap(0.8f, result, 1143, 191, 1163, 212, "打开背包", 500);

            }//打开

            void battle() throws Exception {
                boolean ba = false;

                long color = mFairy.getColorNum(1135, 74, 1162, 98, 1f, 0, "250,77,77");
                if (color > 10) {
                    LtLog.e(mFairy.getLineInfo("开始战斗"));
                    ba = true;
                }

                if (gamePublicFuntion.fb()) {
                    Thread.sleep(1000);
                    if (frequencyMap("nnb", 1)) {
                        for (int i = 0; i < 3; i++) {
                            result = mFairy.findPic(572, 365, 656, 523, "lv.png");
                            if (result.sim > 0.8f) {
                                ba = false;
                                break;
                            }
                            ba = true;
                        }
                    }
                }

                result = mFairy.findPic(2, 123, 193, 284, new String[]{"battle.png", "nn8.png", "nn9.png", "jitui.png"});
                if (result.sim > 0.72f) {
                    LtLog.e(mFairy.getLineInfo("开始战斗"));
                    ba = true;
                }

                if (ba) {
                    mFairy.onTap(1210, 649, 1225, 661, "攻击", 200);
                    mFairy.onTap(1210, 649, 1225, 661, "攻击", 200);
                    mFairy.onTap(1210, 649, 1225, 661, "攻击", 200);
                    mFairy.onTap(1220, 560, 1233, 571, "", 200);
                    mFairy.onTap(1106, 523, 1137, 552, "", 200);
                    mFairy.onTap(1106, 523, 1137, 552, "", 200);
                    mFairy.onTap(1106, 523, 1137, 552, "", 200);
                    mFairy.onTap(1011, 627, 1042, 659, "", 200);
                    mFairy.onTap(1031, 553, 1051, 567, "", 200);
                    mFairy.onTap(999, 455, 1023, 477, "", 200);
                    mFairy.onTap(1076, 412, 1098, 435, "", 200);
                }
            }//battle

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("nn7.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "躲", 1000);
                    err = 0;
                    return;
                }

                result = mFairy.findPic("nn27.png");
                mFairy.onTap(0.8f, result, "叉 - 公告", 500);

                nn();

                gamePublicFuntion.chat();
                gamePublicFuntion.errClick();
                gamePublicFuntion.yan();
                gamePublicFuntion.zui();
                gamePublicFuntion.du();
                gamePublicFuntion.shou();
                gamePublicFuntion.quan();
                gamePublicFuntion.lian();
                gamePublicFuntion.fu();
                gamePublicFuntion.fh();
                //gamePublicFuntion.tong();
                gamePublicFuntion.use();
                gamePublicFuntion.lv();


                result = mFairy.findPic(18, 159, 110, 272, "nn51.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(268, 215, 979, 455, "nn52.png");
                    mFairy.onTap(0.8f, result, "关掉纸人", 500);
                }

                result = mFairy.findPic(517, 133, 761, 460, "nn47.png");
                mFairy.onTap(0.8f, result, "原地复活", 500);

                if (gamePublicFuntion.mainScene()) {

                    frequencyInit("c");
                    frequencyInit("cha");

                    result = mFairy.findPic(8, 157, 109, 357, "nn32.png");
                    if (result.sim > 0.8f) {

                        result = mFairy.findPic(846, 42, 984, 167, "nn50.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(900, 23, 919, 44, "", 1000);
                            gamePublicFuntion.close(2);
                        }
                        setTaskEnd();
                        return;
                    }

                    nn++;
                    if (nn > 5) {
                        mFairy.onTap(1106, 523, 1137, 552, "", 800);
                        nn = 0;
                        return;
                    }

                    battle();

                    if (gamePublicFuntion.judgeStop(10, false, 0.9f)) {
                        mFairy.initMatTime();
                        setTaskName(0);
                        return;
                    }

                    if (gamePublicFuntion.judgeStop(3, true, 0.9f)) {

                        result = mFairy.findPic(99, 218, 178, 285, "nn49.png");
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic("nn42.png");
                            mFairy.onTap(0.8f, result, "谈话", 500);
                            return;
                        }

                        if (judgeOneSecond()) {
                            LtLog.e(mFairy.getLineInfo("开始滑动>>>"));
                            for (int i = 0; i < 3; i++) {
                                mFairy.ranSwipe(90, 180, 90, 312, 500, (long) 500, 5);
                            }
                            judgeOneSecond = 1;
                        }

                        mFairy.initMatTime();
                        mFairy.onTap(55, 190, 100, 208, "主线", 1000);
                    } else {
                        nn = 0;
                        err = 0;
                    }

                    result = mFairy.findPic("nn29.png");
                    if (result.sim > 0.8f) {
                        mFairy.ranSwipe(804, 428, 519, 372, 500, (long) 500, 2);
                        mFairy.ranSwipe(499, 222, 769, 280, 1, 500, (long) 500);
                        judgeOneSecond = 0;
                        return;
                    }
                    dakai();
                } else {
                    mFairy.initMatTime();

                    result = mFairy.findPic("nn43.png");
                    if (result.sim > 0.8f) {
                        int n = (int) (Math.random() * 2) + 1;
                        switch (n) {
                            case 1:
                                mFairy.onTap(1054, 329, 1117, 344, "", 500);
                                break;
                            default:
                                mFairy.onTap(1049, 397, 1117, 409, "", 500);
                                break;
                        }
                        return;
                    }

                    result = mFairy.findPic(962, 174, 1210, 599, new String[]{"nn.png", "nn28.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "主线剧情", 1500);
                    } else {
                        if (frequencyMap("c", 1)) {

                            result = mFairy.findPic(new String[]{"chat1.png", "nn11.png"});
                            if (result.sim > 0.8f) {
                                err = 0;
                                if (frequencyMap("cha", 6)) {
                                    result = mFairy.findPic("nn11.png");
                                    mFairy.onTap(0.8f, result, "叉 - 剧情", 500);
                                    return;
                                }
                                mFairy.onTap(1053, 458, 1111, 475, "chat", 500);
                                return;
                            }

                            result = mFairy.findPic("chat2.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, 1053, 458, 1111, 475, "chat2", 500);
                                gamePublicFuntion.errClick = 0;
                            }
                        }
                    }
                }
            }
        };
    }//新手引导


    /*** 单人任务 */
    private int activity_type = 1;
    private String activity_name;
    class Single_Content extends TaskContent {

        public Single_Content(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);
        }

        void create() throws Exception {
            super.create();
        }

        void init() throws Exception {
            gamePublicFuntion.init(1);
            setTaskName(1);
            oneSecond = 0;
        }

        void inOperation() throws Exception {
            gamePublicFuntion.lv();
            gamePublicFuntion.skipScene();
            gamePublicFuntion.chat();
            gamePublicFuntion.fh();
            gamePublicFuntion.deng();
            gamePublicFuntion.fhs(2);
            gamePublicFuntion.task();

            result = mFairy.findPic(1045, 3, 1265, 50, "nn2.png");
            mFairy.onTap(0.85f, result, "跳过教学", 500);

            result = mFairy.findPic(962, 562, 1077, 636, "map.png");
            mFairy.onTap(0.75f, result, 1123, 67, 1147, 86, "", 500);

            result = mFairy.findPic("bang.png");
            if (result.sim > 0.8f) {
                TaskMain.BANG = false;
                mFairy.onTap(1208, 48, 1222, 62, "", 500);
                setTaskEnd();
                return;
            }

            cancel();

            use();
        }

        void cancel() throws Exception {
            result = gamePublicFuntion.qx();
            mFairy.onTap(0.8f, result, "取消", 1000);
        }

        void use() throws Exception {
            gamePublicFuntion.use();
        }//使用

        boolean activity_err() throws Exception {
            if (mapCount(0.7f, 575, 109, 685, 429, "bpzc2.png")) {
                setTaskEnd();
                return true;
            }

            return false;
        }

        boolean activity_end(FindResult act) throws Exception {

            return false;
        }

        void content_01() throws Exception {
            if (timeCount(20, 0)) {
                if (frequencyMap("actError", 1)) {
                    setTaskEnd();
                    return;
                }
            }
            result = mFairy.findPic("activity.png");
            if (result.sim > 0.75f) {
                GamePublicFuntion.activity_bool = false;
                mFairy.onTap(0.75f, result, "活动按钮", 800);
            }

            result = mFairy.findPic("activity1.png");
            if (result.sim > 0.8f) {
                if (oneSecond()) {
                    gamePublicFuntion.activity_type(activity_type);

                    result = mFairy.findPic("activity3.png");
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 5; i++) {
                            result = mFairy.findPic(76,53,192,659, "activity2.png");
                            mFairy.onTap(0.75f, result, result.x + 30, result.y + 0, result.x + 40, result.y + 1, "领取活跃", 500);
                        }
                    }
                }

                FindResult act = mFairy.findPic(205,134,1168,510, activity_name);
                LtLog.i("activity_name:"+act.sim);
                if (act.sim > 0.75f) {

                    if (activity_end(act)) {
                        oneSecond = 0;
                        return;
                    }

                    result = mFairy.findPic(act.x + 223, act.y - 30, act.x + 380, act.y + 71, "wan.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("完成"));
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(act.x + 223, act.y - 10, act.x + 380, act.y + 71, "qian.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "前往", 500);
                        oneSecond = 0;
                        if (activity_err()) {
                            return;
                        }
                        frequencyInit("actError");
                        setTaskName(2);
                        return;
                    }
                }

                LtLog.i(mFairy.getLineInfo("开始滑动"));

                if (GamePublicFuntion.activity_bool == false) {
                    activitySlide.slideRange(new int[]{3, 5, 7, 9, 10, 12, 14, 16, 18}, 2);
                } else {
                    activitySlide.slideRange(new int[]{3, 5, 7, 9, 10, 12, 14, 16, 18}, 2, 0);
                }
            }
        }
    }

    public void setUp() throws Exception {
        new TaskContent(mFairy, "设置") {

            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }

            void inOperation() throws Exception {
                result = gamePublicFuntion.qx();
                mFairy.onTap(0.8f, result, "取消", 1000);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.mainScene()) {
                    result = mFairy.findPic(1014, 227, 1263, 705, "set.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "设置", 1500);
                    } else {
                        mFairy.onTap(1221, 191, 1235, 208, "点击缩放栏", 2000);
                    }
                }
                result = mFairy.findPic("set1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    for (int i = 1; i <= 3; i++) {
                        result = gamePublicFuntion.qx();
                        mFairy.onTap(0.8f, result, "取消", 500);

                        switch (i) {
                            case 1:
                                result = mFairy.findPic(183, 76, 445, 322, "set2.png");
                                mFairy.onTap(0.8f, result, "镜头设置", 1500);

                                result = mFairy.findPic("set3.png");
                                mFairy.onTap(0.8f, result, 806, 229, 807, 230, "镜头", 500);
                                break;
                            case 2:
                                result = mFairy.findPic(183, 158, 447, 399, "set4.png");
                                mFairy.onTap(0.8f, result, "画面设置", 1500);

                                result = mFairy.findPic("set5.png");
                                mFairy.onTap(0.8f, result, 473, 162, 482, 167, "流畅", 500);

                                break;
                            case 3:
                                result = mFairy.findPic(182, 236, 445, 482, "set6.png");
                                mFairy.onTap(0.8f, result, "界面设置", 1500);

                                result = mFairy.findPic("set7.png");
                                mFairy.onTap(0.8f, result, 474, 162, 480, 168, "经典布局", 500);
                                mFairy.onTap(0.8f, result, 806, 325, 811, 329, "棋盘", 500);
                                break;
                        }
                    }
                    mFairy.onTap(1135, 77, 1159, 95, "", 500);
                    setTaskEnd();
                    return;
                }
            }
        };
    }//设置***

    public void skill() throws Exception {
        new TaskContent(mFairy, "技能") {

            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }

            void inOperation() throws Exception {
                result = gamePublicFuntion.qx();
                mFairy.onTap(0.8f, result, "取消", 500);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.mainScene()) {
                    result = mFairy.findPic(1014, 227, 1263, 705, "skill1.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "技能", 1500);
                    } else {
                        mFairy.onTap(1221, 191, 1235, 208, "点击缩放栏", 1000);
                    }
                }

                result = mFairy.findPic("skill2.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic("skill3.png");
                    mFairy.onTap(0.85f, result, "经脉", 500);

                    result = mFairy.findPic("skill2.png");
                    if (result.sim > 0.85f) {
                        switch (AtFairyConfig.getOption("skill")) {
                            case "1":
                                mFairy.onTap(383, 525, 410, 539, "左侧技能激活", 500);
                                break;
                            case "2":
                                mFairy.onTap(898, 520, 935, 529, "右侧技能激活", 500);
                                break;
                        }
                        mFairy.onTap(1132, 74, 1159, 96, "", 500);
                        setTaskEnd();
                    }
                    return;
                }
            }
        };
    }//技能***

    public void li_rank() throws Exception {
        new TaskContent(mFairy, "离队") {
            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }

            void inOperation() throws Exception {
                result = gamePublicFuntion.qx();
                mFairy.onTap(0.8f, result, "取消", 500);
            }

            void content_01() throws Exception {
                if (timeCount(10, 99)) {
                    gamePublicFuntion.close(1);
                }

                result = mFairy.findPic(1, 1, 85, 214, "rankScene.png");
                LtLog.e(mFairy.getLineInfo("组队场景 sim:" + result.sim));
                if (result.sim > 0.85f) {
                    //err = 0;

                    for (int i = 0; i < 5; i++) {
                        result = mFairy.findPic("rank5.png");
                        mFairy.onTap(0.85f, result, "退出队伍", 500);
                    }

                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(23, 79, 210, 146, new String[]{"task.png", "rank.png"});
                mFairy.onTap(0.8f, result, 132, 107, 162, 118, "点击队伍", 500);
            }
        };
    }//离队

    private int control_gm = 1;

    public void chubei() throws Exception {
        new TaskContent(mFairy, "补充储备") {
            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }

            void inOperation() throws Exception {
                result = gamePublicFuntion.qx();
                mFairy.onTap(0.8f, result, "取消", 500);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("chubei1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    mFairy.ranSwipe(626, 577, 626, 300, 500, (long) 1000, 5);

                    switch (control_gm) {
                        case 1:
                            mFairy.onTap(652, 494, 733, 512, "购买血药", 500);
                            break;
                        case 2:
                            mFairy.onTap(643, 601, 734, 619, "购买蓝药", 500);
                            break;
                    }

                    if (gamePublicFuntion.gm()) {
                        err = 0;
                        mFairy.onTap(1213, 27, 1238, 46, "", 1500);
                    }
                }

                result = mFairy.findPic("package3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("package4.png");
                    if (result.sim > 0.8f) {

                        long xue = mFairy.getColorNum(1060, 217, 1091, 228, 0.9f, 0, "126,182,45");
                        if (xue < 50) {
                            if (frequencyMap("xue", 2)) {
                                mFairy.onTap(1145, 203, 1162, 221, "血量不足", 500);
                                control_gm = 1;
                            }
                            return;
                        }

                        long lan = mFairy.getColorNum(1067, 298, 1099, 304, 0.9f, 0, "45,201,181");
                        if (lan < 50) {
                            if (frequencyMap("lan", 2)) {
                                mFairy.onTap(1143, 284, 1162, 301, "蓝量不足", 500);
                                control_gm = 2;
                            }
                            return;
                        }

                        if (frequencyMap("buchong", 2)) {
                            mFairy.onTap(1184, 58, 1201, 70, "", 500);
                            setTaskEnd();
                        }

                    } else {
                        for (int i = 0; i < 2; i++) {
                            mFairy.onTap(780, 407, 810, 432, "", 100);
                        }
                    }

                } else {
                    result = mFairy.findPic("package.png");
                    mFairy.onTap(0.8f, result, "背包", 3000);

                    result = mFairy.findPic("package1.png");
                    mFairy.onTap(0.8f, result, "药物", 500);

                    result = mFairy.findPic("package2.png");
                    mFairy.onTap(0.8f, result, 804, 101, 846, 116, "储备", 500);


                }
            }
        };
    }//补充储备***

    private boolean sm = false;
    public void sm() throws Exception {
        new Single_Content(mFairy, "师门任务") {

            void create() throws Exception {
                super.create();
                activity_name = "sm.png";
                activity_type = 1;
                sm = false;
            }

            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
                oneSecond = 0;
            }

            boolean activity_end(FindResult act) throws Exception {
                result = mFairy.findPic(act.x + 223, act.y - 30, act.x + 350, act.y + 71, "wan.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1222, 35, 1242, 52, "", 500);
                    setTaskName(2);
                    sm = true;
                    return true;
                }
                return false;
            }

            void content_02() throws Exception {
                if (timeCount(8, 0)) {
                    if (gamePublicFuntion.mainScene()) {
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("sm3.png");
                mFairy.onTap(0.8f, result, "继续", 1000);

                result = mFairy.findPic(982, 150, 1200, 522, "sm7.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "领取师门任务", 500);
                } else {
                    result = mFairy.findPic(787, 4, 1271, 363, "close1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1063, 324, 1105, 340, "对话1", 1000);
                        result = mFairy.findPic(787, 4, 1271, 363, "close1.png");
                        mFairy.onTap(0.8f, result, 1212, 52, 1228, 66, "对话2", 1000);
                    } else {
                        gamePublicFuntion.chat2();
                    }
                }

                result = mFairy.findPic("sm8.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1111, 117, 1130, 134, "师门密令", 1000);
                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic("sm5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 619, 437, 652, 449, "", 500);
                    sm = true;
                }

                if (gamePublicFuntion.gm()) {
                    gamePublicFuntion.close(3);
                } else {
                    result = mFairy.findPic(314, 126, 415, 218, "gm1.png");
                    mFairy.onTap(0.8f, result, 555, 175, 589, 202, "需求", 1000);
                }

                result = mFairy.findPic(955, 518, 1078, 626, "sm2.png");
                mFairy.onTap(0.8f, result, "上交", 500);

                if (gamePublicFuntion.fb()) {
                    err = 0;
                    gamePublicFuntion.yan();
                    gamePublicFuntion.zui();
                    gamePublicFuntion.du();
                    gamePublicFuntion.shou();
                    gamePublicFuntion.quan();
                    gamePublicFuntion.lian();
                    gamePublicFuntion.fu();

                    if (gamePublicFuntion.deng() == false && gamePublicFuntion.lv() == false) {
                        gamePublicFuntion.auto();
                        Thread.sleep(2000);
                    }

                    if (gamePublicFuntion.judgeBattle()) {
                        mFairy.initMatTime();
                        return;
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(6, true, 0.85f) || sm) {

                        FindResult task = mFairy.findPic(20, 127, 62, 400, "sm1.png");
                        if (task.sim > 0.7f) {
                            err = 0;
                            mFairy.onTap(0.7f, task, "taskClick", 3000);
                            mFairy.initMatTime();
                            return;
                        }

                        result = mFairy.findPic(7, 128, 121, 400, "sm4.png");
                        if (result.sim > 0.7f) {
                            err = 0;
                            sm = false;
                            mFairy.onTap(0.7f, result, "特殊师门", 3000);
                            mFairy.initMatTime();
                            return;
                        }
                        taskSlide.slideRange(new int[]{5, 6, 7}, 2, 0);
                    }
                }
            }
        };
    }//师门***

    public void bt() throws Exception {
        new Single_Content(mFairy, "宝图任务") {

            void create() throws Exception {
                super.create();
                activity_name = "bt.png";
                activity_type = 4;
            }

            void content_02() throws Exception {
                timeCount(8, 0);

                result = mFairy.findPic(966, 197, 1186, 600, "bt1.png");
                mFairy.onTap(0.8f, result, "领取宝图任务", 1000);

                result = mFairy.findPic(966, 197, 1186, 600, "bt2.png");
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    err = 0;

                    frequencyInit("chat");

                    mFairy.onTap(0.8f, result, "领取所有宝图任务", 500);
                    /*if (mapCount(0.72f, 585, 87, 674, 441, "bt7.png")) {
                        mFairy.onTap(1049, 485, 1105, 517, "", 500);
                        setTaskEnd();
                        return;
                    }*/
                }

                result = mFairy.findPic(63, 569, 467, 698, "bt8.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1056, 440, 1094, 482, "", 500);
                    setTaskEnd();
                    return;
                }

                if (frequencyMap("chat", 2)) {
                    gamePublicFuntion.chat2();
                }

                if (gamePublicFuntion.mainScene()) {
                    frequencyInit("chat");

                    if (err >= 3) {
                        FindResult task = mFairy.findPic(20, 127, 62, 400, "bt3.png");
                        if (task.sim > 0.7f) {
                            err = 0;
                            mFairy.onTap(0.7f, task, "taskClick", 3000);
                            mFairy.initMatTime();
                            return;
                        }
                        taskSlide.slideRange(new int[]{5, 6, 7}, 2, 0);
                    }
                }
            }
        };
    }//宝图***



    public void use_bt() throws Exception {
        new Single_Content(mFairy, "使用宝图") {
            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }

            void use() throws Exception {

            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, "背包", 3000);

                result = mFairy.findPic(952, 534, 1198, 701, "package5.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(758, 145, 1188, 500, "bt4.png");
                    if (result.sim > 0.8f) {
                        frequencyInit("bao_end");
                        mFairy.onTap(0.8f, result, "发现宝图", 100);
                        mFairy.onTap(0.8f, result, "发现宝图", 100);
                        mFairy.onTap(0.8f, result, "发现宝图", 1000);
                        gamePublicFuntion.close(2);
                        setTaskName(2);
                        return;
                    } else {
                        mFairy.ranSwipe(970, 485, 970, 179, 500, (long) 500, 1);
                        if (frequencyMap("bao_end", 2)) {
                            setTaskEnd();
                            return;
                        }
                    }
                }
            }

            void content_02() throws Exception {
                timeCount(5, 0);

                result = mFairy.findPic("bt5.png");
                mFairy.onTap(0.8f, result, "此地无银三百两", 500);

                if (gamePublicFuntion.judgeBattle()) {
                    Thread.sleep(3000);
                    mFairy.initMatTime();
                    err = 0;
                    return;
                }

                result = mFairy.findPic(945, 450, 1082, 591, "bt6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "使用", 500);
                    Thread.sleep(3000);
                    mFairy.initMatTime();
                    err = 0;
                    return;
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(5, false, 0.85f)) {

                    }
                }
            }
        };
    }//使用宝图***



    public void sssw() throws Exception {
        new Single_Content(mFairy, "三市庶务") {

            void create() throws Exception {
                super.create();
                activity_name = "sssw.png";
                activity_type = 4;
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("sssw6.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    switch (AtFairyConfig.getOption("sssw")) {
                        case "1":

                            mFairy.onTap(0.8f, result, 219, 583, 281, 596, "鬼市", 500);
                            break;
                        case "2":

                            mFairy.onTap(0.8f, result, 607, 581, 654, 593, "海市", 500);
                            break;
                        case "3":

                            mFairy.onTap(0.8f, result, 1008, 579, 1072, 592, "沙市", 500);
                            break;
                    }


                }


                if (frequencyMap("chat", 1)) {
                    gamePublicFuntion.chat2();
                }

                result = mFairy.findPic("sssw5.png");
                mFairy.onTap(0.8f, result, 807, 432, 840, 447, "确定前往", 500);

                if (gamePublicFuntion.gm()) {
                    gamePublicFuntion.close(3);
                } else {
                    result = mFairy.findPic(314, 126, 415, 218, "gm1.png");
                    mFairy.onTap(0.8f, result, 555, 175, 589, 202, "需求", 1000);
                }

                result = mFairy.findPic(955, 518, 1078, 626, "sm2.png");
                mFairy.onTap(0.8f, result, "上交", 500);

                if (gamePublicFuntion.mainScene()) {
                    frequencyInit("chat");
                    if (gamePublicFuntion.judgeStop(6, true, 0.85f)) {

                        FindResult task = mFairy.findPic(20, 127, 62, 400, "sssw4.png");
                        if (task.sim > 0.7f) {
                            mFairy.onTap(0.7f, task, "taskClick", 3000);
                            mFairy.initMatTime();

                            if (gamePublicFuntion.fb()) {
                                gamePublicFuntion.auto();
                                Thread.sleep(3000);
                            }
                            return;
                        }

                        taskSlide.slideRange(new int[]{3, 4, 5}, 2, 0);
                    }
                } else {
                    Thread.sleep(2000);
                }
            }
        };
    }//三市庶务***



    public void yb() throws Exception {
        new Single_Content(mFairy, "运镖任务(单人)") {

            void create() throws Exception {
                super.create();
                activity_name = "yb.png";
                activity_type = 4;
            }

            void cancel() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic("yb2.png");
                    mFairy.onTap(0.8f, result, 814, 436, 860, 450, "离镖车太远 - 同意", 500);

                    if (frequencyMap("qx", 1)) {
                        mFairy.onTap(0.8f, qx, "取消", 500);
                    }
                } else {
                    frequencyInit("qx");
                }

            }

            void content_02() throws Exception {
                timeCount(6, 0);

                result = mFairy.findPic("chat2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic(966, 197, 1186, 600, "yb1.png");
                mFairy.onTap(0.8f, result, "固守镖", 500);

                result = mFairy.findPic(966, 197, 1186, 600, "yb3.png");
                mFairy.onTap(0.8f, result, "确定前往下一镖", 500);

                result = mFairy.findPic("yb11.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "活跃不足", 500);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("yb4.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(2000);
                    err = 0;
                }

                if (gamePublicFuntion.fb()) {
                    gamePublicFuntion.auto();
                    mFairy.initMatTime();
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(6, true, 0.85f)) {
                        FindResult task = mFairy.findPic(17, 117, 83, 398, "yb5.png");
                        if (task.sim > 0.7f) {
                            mFairy.onTap(0.7f, task, "taskClick", 3000);
                            mFairy.initMatTime();
                            return;
                        }
                        taskSlide.slideRange(new int[]{3, 4, 5}, 2, 0);
                    }
                }
            }
        };
    }//运镖任务(单人)***



    public void bpzc() throws Exception {
        new Single_Content(mFairy, "帮派周常") {

            void create() throws Exception {
                super.create();
                activity_name = "bpzc.png";
                activity_type = 1;
            }

            boolean activity_err() throws Exception {


                return super.activity_err();
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                gamePublicFuntion.chat2();

                if (gamePublicFuntion.gm()) {
                    gamePublicFuntion.close(3);
                } else {
                    result = mFairy.findPic(314, 126, 415, 218, "gm1.png");
                    mFairy.onTap(0.8f, result, 555, 175, 589, 202, "需求", 1000);
                }

                result = mFairy.findPic(955, 518, 1078, 626, "sm2.png");
                mFairy.onTap(0.8f, result, "上交", 500);

                if (gamePublicFuntion.fb()) {
                    gamePublicFuntion.auto();
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(6, true, 0.85f)) {

                        FindResult task = mFairy.findPic(20, 127, 62, 400, "bpzc1.png");
                        if (task.sim > 0.7f) {
                            mFairy.onTap(0.7f, task, "taskClick", 3000);
                            mFairy.initMatTime();
                            return;
                        }

                        taskSlide.slideRange(new int[]{3, 4, 5, 6, 7, 8}, 2, 0);
                    }
                }
            }
        };
    }//帮派周常***


    private int xlyy = -1;
    private boolean user_choice = true;
    private boolean skillshu = false;
    public void xlyy() throws Exception {
        new Single_Content(mFairy, "仙灵邮驿") {

            void create() throws Exception {
                super.create();

                activity_name = "xlyy.png";
                activity_type = 4;

                if (AtFairyConfig.getOption("xlyy").equals("2")) {
                    user_choice = false;
                } else {
                    user_choice = true;
                }
            }

            void content_01() throws Exception {
                super.content_01();
                xlyy = -1;
                judgeOneSecond = 0;
                oneSecond = 0;
                skillshu = false;
            }

            boolean activity_end(FindResult act) throws Exception {
                result = mFairy.findPic(act.x + 223, act.y - 30, act.x + 350, act.y + 71, "wan.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1222, 35, 1242, 52, "", 500);
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void baoshi() throws Exception {
                result = mFairy.findPic("xlyy4.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    frequencyInit("baoshi");

                    if (xlyy == 0 || xlyy == -1) {
                        xlyy = 0;
                        mFairy.onTap(1028, 88, 1046, 105, "", 300);
                        mFairy.onTap(1138, 71, 1164, 96, "", 500);
                        return;
                    }

                    result = mFairy.findPic("xlyy10.png");
                    mFairy.onTap(0.8f, result, "快捷合成", 500);

                    result = mFairy.findPic("xlyy15.png");
                    mFairy.onTap(0.8f, result, "快捷合成", 500);

                    FindResult he = mFairy.findPic("xlyy12.png");
                    if (he.sim > 0.8f) {

                        result = mFairy.findPic("xlyy13.png");
                        mFairy.onTap(0.8f, result, "自动购买", 500);

                        mFairy.onTap(0.8f, he, "合成", 500);
                        mFairy.onTap(1024, 87, 1043, 102, "", 500);
                        mFairy.onTap(1139, 76, 1157, 89, "", 500);
                        xlyy = -1;
                        return;
                    }

                    result = mFairy.findPic("xlyy11.png");
                    if (result.sim > 0.8f) {
                        for (int i = 1; i <= 9; i++) {
                            result = mFairy.findPic("b" + xlyy + ".png");
                            if (result.sim > 0.8f) {
                                mFairy.ranSwipe(541, 332, 538, 567, 500, (long) 500, 2);
                                mFairy.onTap(800, 593, 850, 602, "确定", 1000);
                                return;
                            }
                            mFairy.ranSwipe(376, 493, 380, 437, 500, (long) 800, 1);
                        }
                        mFairy.onTap(1026, 81, 1041, 101, "", 500);
                    }

                } else {
                    if (frequencyMap("baoshi", 3)) {
                        xlyy = -1;
                    }
                }
            }//宝石

            void baoshi_judge() throws Exception {
                result = mFairy.findPic("bao1.png");
                if (result.sim > 0.8f) {
                    xlyy = 1;
                    return;
                }

                result = mFairy.findPic("bao2.png");
                if (result.sim > 0.8f) {
                    xlyy = 2;
                    return;
                }

                result = mFairy.findPic("bao3.png");
                if (result.sim > 0.8f) {
                    xlyy = 3;
                    return;
                }

                result = mFairy.findPic("bao5.png");
                if (result.sim > 0.8f) {
                    xlyy = 5;
                    return;
                }

                result = mFairy.findPic("bao6.png");
                if (result.sim > 0.8f) {
                    xlyy = 6;
                    return;
                }

                result = mFairy.findPic("bao8.png");
                if (result.sim > 0.8f) {
                    xlyy = 8;
                    return;
                }
            }//宝石判断

            void content_02() throws Exception {
                if (timeCount(8, 0)) {
                    if (gamePublicFuntion.mainScene()) {
                        setTaskEnd();
                        return;
                    }
                }

                baoshi();

                result = mFairy.findPic(1002, 13, 1153, 457, "xlyy1.png");
                if (result.sim > 0.8f) {
                    if (oneSecond()) {
                        mFairy.onTap(0.8f, result, "我要领取", 500);
                    } else {
                        result = mFairy.findPic(992, 15, 1173, 500, "xlyy9.png");
                        mFairy.onTap(0.8f, result, "前往仓库", 2000);
                    }
                }

                if (frequencyMap("chat", 1)) {
                    gamePublicFuntion.chat2();
                }

                result = mFairy.findPic(955, 518, 1078, 626, "sm2.png");
                mFairy.onTap(0.8f, result, "上交", 500);

                result = mFairy.findPic(902, 485, 1084, 648, "xlyy3.png");
                mFairy.onTap(0.8f, result, "确认", 500);

                result = mFairy.findPic(1198, 64, 1272, 668, "xlyy8.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("gm5.png");
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("xlyy14.png");
                    if (result.sim > 0.8f) {
                        if (user_choice == false && skillshu == false) {
                            skillshu = true;
                            gamePublicFuntion.close(3);
                            return;
                        }
                        LtLog.e(mFairy.getLineInfo("发现兽决,无法购买 End!"));
                        setTaskEnd();
                        return;
                    }

                    if (user_choice) {
                        if (judgeOneSecond()) {
                            judgeOneSecond = 1;
                            mFairy.onTap(1215, 26, 1240, 49, "关闭购买界面", 500);
                            return;
                        }
                    }

                    if (gamePublicFuntion.gm()) {
                        gamePublicFuntion.close(3);
                    } else {
                        result = mFairy.findPic(314, 126, 415, 218, "gm1.png");
                        mFairy.onTap(0.8f, result, 555, 175, 589, 202, "需求", 1000);
                    }
                }

                result = mFairy.findPic("xlyy6.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (user_choice || skillshu) {
                        result = mFairy.findPic("xlyy7.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "前往仓库", 2000);
                            return;
                        }
                    }
                    baoshi_judge();
                    mFairy.onTap(1099, 104, 1115, 118, "", 500);
                }

                if (gamePublicFuntion.mainScene()) {
                    frequencyInit("chat");
                    if (gamePublicFuntion.judgeStop(3, true, 0.85f)) {

                        taskSlide.slideRange(new int[]{3, 4, 6}, 2, 0);

                        FindResult task = mFairy.findPic(2, 119, 194, 313, "xlyy2.png");
                        if (task.sim > 0.7f) {
                            oneSecond = 1;

                            if (gamePublicFuntion.fb()) {
                                gamePublicFuntion.auto();
                                mFairy.onTap(0.7f, task, "taskClick", 5000);
                                mFairy.initMatTime();
                                err = 0;
                                return;
                            }

                            if (user_choice || skillshu) {
                                result = mFairy.findPic(task.x - 19, task.y + 5, task.x + 48, task.y + 102, "cang.png");
                                if (result.sim > 0.7f) {
                                    result = mFairy.findPic(182, 109, 270, 389, "xlyy5.png");
                                    mFairy.onTap(0.8f, result, "书", 500);
                                    judgeOneSecond = 0;
                                    return;
                                }
                            }

                            if (xlyy == 0) {
                                result = mFairy.findPic(182, 109, 270, 389, "xlyy5.png");
                                mFairy.onTap(0.8f, result, "书", 500);
                                return;
                            }

                            mFairy.onTap(0.7f, task, "taskClick", 3000);

                            if (judgeOneSecond == 0) {
                                mFairy.initMatTime();
                            }
                            return;
                        }
                    }
                }
            }
        };
    }//仙灵邮驿***

    private boolean bpps = true;
    private int bpps_num = 0;

    public void bpps(final int count) throws Exception {
        new Single_Content(mFairy, "帮派跑商") {

            void create() throws Exception {
                super.create();
                activity_name = "bpps.png";
                activity_type = 4;
                bpps_num = 0;
            }

            FindResult judge_qs(boolean bool) throws Exception {
                mFairy.ranSwipe(410, 294, 731, 322, 1, 500, (long) 500);
                List<FindResult> left = mFairy.findPic(351, 158, 779, 481, 0.85f, "bpps8.png");
                mFairy.ranSwipe(410, 294, 731, 322, 3, 500, (long) 500);
                List<FindResult> right = mFairy.findPic(689, 158, 817, 493, 0.85f, "bpps8.png");

                if (left.size() != 0 && right.size() != 0) {
                    if (bool) {
                        if (left.get(left.size() - 1).y > right.get(right.size() - 1).y) {
                            mFairy.ranSwipe(410, 294, 731, 322, 1, 500, (long) 500);
                            return left.get(left.size() - 1);
                        } else {
                            return right.get(right.size() - 1);
                        }
                    } else {
                        if (left.get(0).y < right.get(0).y) {
                            mFairy.ranSwipe(410, 294, 731, 322, 1, 500, (long) 500);
                            return left.get(0);
                        } else {
                            return right.get(0);
                        }
                    }
                } else {
                    LtLog.e(mFairy.getLineInfo("Error : err in judge_qs"));
                }
                return null;
            }

            void cancel() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic(419, 301, 837, 377, "bpps9.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 812, 433, 840, 455, "确定 - 前往", 500);
                        return;
                    }
                    if (frequencyMap("qx", 1)) {
                        mFairy.onTap(0.8f, result, "取消", 500);
                    }

                } else {
                    frequencyInit("qx");
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(968, 148, 1202, 527, "bpps1.png");
                mFairy.onTap(0.8f, result, "帮派跑商", 1500);

                result = mFairy.findPic(968, 148, 1202, 527, "bpps2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "这就出发", 500);
                    frequencyInit("chat");
                    bpps = true;
                    err = 5;
                }

                result = mFairy.findPic("bpps17.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1119, 599, 1144, 624, "白花花的银子", 1500);

                    bpps_num++;

                    if (bpps_num >= count) {
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("bpps13.png");
                    mFairy.onTap(0.8f, result, "谈话", 500);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(968, 300, 1202, 527, new String[]{"bpps10.png", "jy.png"});
                mFairy.onTap(0.8f, result, "与你交易", 500);

                if (frequencyMap("chat", 2)) {
                    gamePublicFuntion.chat2();
                }

                result = mFairy.findPic(856, 51, 959, 127, "bpps5.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("bpps7.png");
                    if (result.sim > 0.8f) {

                        result = mFairy.findPic(917, 203, 992, 267, "bpps12.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(331, 113, 406, 129, "出售价格", 500);

                            result = judge_qs(false);

                            if (result != null) {
                                mFairy.onTap(0.8f, result, "前往出售", 500);
                                bpps = false;
                            }

                        } else if (mFairy.findPic("bpps6.png").sim > 0.85f) {
                            mFairy.onTap(479, 113, 545, 125, "收购价格", 500);

                            result = judge_qs(true);

                            if (result != null) {
                                mFairy.onTap(0.8f, result, "前往收购", 500);
                            }
                        }
                        return;
                    }

                    result = mFairy.findPic(917, 203, 992, 267, "bpps12.png");
                    if (result.sim > 0.8f) {
                        if (bpps) {
                            mFairy.onTap(0.8f, result, "商品趋势", 500);
                        } else {
                            for (int i = 0; i < 25; i++) {
                                long n = mFairy.getColorNum(1204, 215, 1216, 228, 0.98f, 0, "188,142,109");
                                if (n > 20) {
                                    mFairy.onTap(1210, 226, 1228, 245, "卖", 200);
                                } else {
                                    i = i + 5;
                                }
                            }

                            result = mFairy.findPic("bpps14.png");
                            mFairy.onTap(0.8f, result, "出售", 1500);

                            bpps = true;

                            gamePublicFuntion.init(0);
                            err = 5;
                        }
                        return;
                    }

                    result = mFairy.findPic(new String[]{"bpps11.png"});
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 25; i++) {
                            long n = mFairy.getColorNum(734, 227, 747, 236, 0.98f, 0, "188,142,109");
                            if (n > 20) {
                                mFairy.onTap(742, 233, 757, 247, "买", 200);
                            } else {
                                i = i + 5;
                            }
                        }
                        result = mFairy.findPic("bpps15.png");
                        mFairy.onTap(0.8f, result, "购买", 1500);
                        return;
                    }

                    result = mFairy.findPic("bpps6.png");
                    mFairy.onTap(0.8f, result, "趋势", 500);
                }


                if (gamePublicFuntion.mainScene()) {
                    frequencyInit("chat");
                    if (err >= 5) {

                        FindResult bang = mFairy.findPic(15, 111, 75, 391, "bpps3.png");
                        if (bang.sim > 0.7f) {
                            result = mFairy.findPic(bang.x + 115, bang.y - 30, bang.x + 174, bang.y + 40, "bpps16.png");
                            if (result.sim > 0.75f) {
                                mFairy.onTap(0.75f, result, "完成", 500);
                                err = 0;
                                mFairy.initMatTime();
                                return;
                            }
                        }

                        FindResult task = mFairy.findPic(182, 85, 274, 384, "bpps4.png");
                        if (task.sim > 0.7f) {
                            err = 0;
                            mFairy.onTap(0.7f, task, "taskClick", 3000);
                            mFairy.initMatTime();
                            return;
                        }

                        taskSlide.slideRange(new int[]{6, 7, 8}, 2, 0);

                    } else {
                        gamePublicFuntion.judgeStop(6, true, 0.85f);
                    }
                }
            }
        };
    }//帮派跑商***

    private int bpjs_num = 0;

    public void bpjs(final int count) throws Exception {
        new Single_Content(mFairy, "帮派建设") {

            void create() throws Exception {
                super.create();
                activity_name = "bpjs.png";
                activity_type = 1;
                bpjs_num = 0;
            }

            boolean activity_err() throws Exception {

                if (mapCount(0.8f, 711, 94, 783, 452, "bpjs2.png")) {
                    setTaskEnd();
                    return true;
                }
                return false;
            }

            void content_02() throws Exception {
                timeCount(6, 0);

                gamePublicFuntion.yan();

                result = mFairy.findPic("gm3.png");
                if (result.sim > 0.75f) {
                    mFairy.finish(AtFairyConfig.getTaskID(), 10301);
                }

                result = mFairy.findPic("chat2.png");
                mFairy.onTap(0.7f, result, 1064, 396, 1099, 410, "chat2", 1000);

                if (gamePublicFuntion.gm()) {
                    gamePublicFuntion.close(3);
                } else {
                    result = mFairy.findPic(314, 126, 415, 218, "gm1.png");
                    mFairy.onTap(0.8f, result, 555, 175, 589, 202, "需求", 1000);
                }
                result = mFairy.findPic("bpjs6.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(955, 518, 1078, 626, "sm2.png");
                    mFairy.onTap(0.8f, result, "上交", 1500);
                    bpjs_num = bpjs_num + 5;
                    if (bpjs_num >= count) {
                        mFairy.ranSwipe(163, 591, 163, 500, 500, (long) 1000, 2);
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("bpjs5.png");
                if (result.sim > 0.8f) {
                    switch (AtFairyConfig.getOption("bpjs")) {
                        case "1":
                            mFairy.onTap(550, 326, 580, 433, "夺取物资", 500);
                            break;
                        case "2":
                            mFairy.onTap(998, 336, 1021, 435, "后勤补给", 500);
                            break;
                    }
                }

                result = mFairy.findPic(471, 266, 788, 376, "bpjs4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(418, 430, 481, 448, "", 500);
                    setTaskName(0);
                    return;
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(6, true, 0.85f)) {

                        FindResult task = mFairy.findPic(20, 127, 62, 400, "bpjs3.png");
                        if (task.sim > 0.7f) {
                            mFairy.onTap(0.7f, task, "taskClick", 3000);
                            mFairy.initMatTime();
                            return;
                        }

                        taskSlide.slideRange(new int[]{3, 4, 5}, 2, 0);
                    }
                }
            }
        };
    }//帮派建设

    public void bpyx() throws Exception {
        new Single_Content(mFairy, "帮派宴席") {

            void create() throws Exception {
                super.create();
                activity_name = "bpyx.png";
                activity_type = 1;
            }

            void inOperation() throws Exception {
                super.inOperation();
                minute = mFairy.dateMinute();
                if (minute > 25) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(6, 0);

                result = mFairy.findPic(960, 159, 1217, 573, "bpyx1.png");
                mFairy.onTap(0.8f, result, "帮派宴席", 500);

                result = mFairy.findPic(960, 159, 1217, 573, "bpyx3.png");
                mFairy.onTap(0.8f, result, "交给我吧", 500);

                result = mFairy.findPic(960, 159, 1217, 573, "bpyx6.png");
                mFairy.onTap(0.8f, result, "选择菜品", 500);

                result = mFairy.findPic(265, 88, 996, 222, "bpyx4.png");
                mFairy.onTap(0.8f, result, 899, 320, 934, 353, "选择", 500);

                result = mFairy.findPic(746, 97, 1029, 273, "bpyx5.png");
                mFairy.onTap(0.8f, result, 899, 320, 934, 353, "关", 500);

                if (frequencyMap("chat2", 3)) {
                    gamePublicFuntion.chat2();
                }

                if (gamePublicFuntion.mainScene()) {
                    frequencyInit("chat2");
                    if (gamePublicFuntion.judgeStop(6, true, 0.85f)) {

                        FindResult task = mFairy.findPic(10, 127, 62, 400, "bpyx2.png");
                        if (task.sim > 0.7f) {
                            mFairy.onTap(0.7f, task, "taskClick", 3000);
                            mFairy.initMatTime();
                            return;
                        }

                        taskSlide.slideRange(new int[]{3, 4, 5}, 2, 0);
                    }
                }
            }
        };
    }//帮派宴席

    public void jzhs() throws Exception {
        new Single_Content(mFairy, "决战华山") {

            void create() throws Exception {
                super.create();
                activity_name = "jzhs.png";
                activity_type = 3;
            }

            void inOperation() throws Exception {
                super.inOperation();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (hour == 21 && minute > 30) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(960, 159, 1217, 573, "jzhs1.png");
                mFairy.onTap(0.8f, result, "进入场地", 500);

                result = mFairy.findPic("jzhs2.png");
                mFairy.onTap(0.8f, result, "匹配", 500);

                result = mFairy.findPic("jzhs6.png");
                mFairy.onTap(0.8f, result, "离开", 3000);

                result = mFairy.findPic(491, 1, 792, 51, "jzhs3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                if (frequencyMap("chat2", 3)) {
                    gamePublicFuntion.chat2();
                }

                if (gamePublicFuntion.fb()) {
                    err = 0;
                    gamePublicFuntion.auto();
                }


                result = mFairy.findPic(632, 2, 855, 42, "jzhs4.png");
                if (result.sim > 0.8f) {
                    if (oneSecond()) {
                        mFairy.onTap(1181, 58, 1215, 90, "点开地图", 2000);
                    }
                } else {
                    oneSecond = 0;
                }

                result = mFairy.findPic("jzhs5.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(625, 346, 642, 359, "中心点", 500);
                    mFairy.onTap(625, 346, 642, 359, "中心点", 500);
                    gamePublicFuntion.close(3);
                    Thread.sleep(5000);
                }

                if (gamePublicFuntion.mainScene()) {
                    frequencyInit("chat2");
                    if (gamePublicFuntion.judgeStop(6, true, 0.85f)) {

                    }
                }
            }
        };
    }//决战华山

    private int bpjx = 1;

    public void bpjx() throws Exception {
        new Single_Content(mFairy, "帮派捐献") {
            void create() throws Exception {
                super.create();

                bpjx = Integer.parseInt(AtFairyConfig.getOption("bpjx"));
            }

            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.mainScene()) {
                    result = mFairy.findPic(1126, 161, 1274, 708, "bang1.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "帮派", 1500);
                    } else {
                        mFairy.onTap(1221, 191, 1235, 208, "点击缩放栏", 1000);
                    }
                }


                result = mFairy.findPic("bang4.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    result = mFairy.findPic("bang2.png");
                    mFairy.onTap(0.8f, result, "信息", 500);

                    result = mFairy.findPic(427, 63, 1005, 170, "bang3.png");
                    mFairy.onTap(0.8f, result, "福利", 500);

                    result = mFairy.findPic(679, 111, 803, 668, "bpjx.png");
                    mFairy.onTap(0.8f, result, result.x + 316, result.y + 18, result.x + 349, result.y + 33, "帮派捐献", 500);
                }

                result = mFairy.findPic("bpjx1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    switch (bpjx) {
                        case 1:
                            for (int i = 0; i < 3; i++) {

                                mFairy.onTap(306, 563, 370, 576, "捐献1", 1000);

                                result = mFairy.findPic("gm3.png");
                                if (result.sim > 0.75f) {
                                    mFairy.onTap(1182, 68, 1198, 80, "", 500);
                                    break;
                                }
                            }
                            break;
                        case 2:
                            for (int i = 0; i < 3; i++) {

                                mFairy.onTap(613, 565, 665, 581, "捐献2", 1000);

                                result = mFairy.findPic("gm3.png");
                                if (result.sim > 0.75f) {
                                    mFairy.onTap(1182, 68, 1198, 80, "", 500);
                                    bpjx--;
                                    return;
                                }
                            }
                            break;
                        case 3:
                            for (int i = 0; i < 3; i++) {

                                mFairy.onTap(920, 567, 967, 581, "捐献3", 1000);

                                result = mFairy.findPic("bpjx2.png");
                                if (result.sim > 0.8f) {
                                    mFairy.onTap(415, 436, 477, 448, "", 500);
                                    bpjx--;
                                    return;
                                }
                            }
                            break;
                    }

                    mFairy.onTap(1101, 108, 1114, 124, "", 500);

                    mFairy.onTap(1140, 78, 1158, 94, "", 500);

                    setTaskEnd();
                }


            }
        };
    }//帮派捐献***

    public void kjxs() throws Exception {
        new Single_Content(mFairy, "科举乡试") {

            void create() throws Exception {
                super.create();
                activity_name = "kjxs.png";
                activity_type = 1;
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                result = mFairy.findPic("kjxs3.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("kjxs1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "天文地理", 500);
                    } else {
                        if (frequencyMap("kj", 2)) {
                            mFairy.onTap(1185, 28, 1204, 44, "", 500);
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic("kjxs2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(926, 227, 1030, 250, "A", 1500);
                }
            }
        };
    }//科举乡试***

    private int xiang = 1;

    public void shaox() throws Exception {
        new Single_Content(mFairy, "烧香") {
            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, "背包", 3000);

                result = mFairy.findPic("package5.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(758, 145, 1188, 500, new String[]{"xiang1.png", "xiang2.png"});
                    if (result.sim > 0.8f) {
                        frequencyInit("bao_end");
                        mFairy.onTap(0.8f, result, "发现香", 100);
                        mFairy.onTap(0.8f, result, "发现香", 100);
                        mFairy.onTap(0.8f, result, "发现香", 1000);
                        setTaskName(2);
                        return;
                    } else {
                        mFairy.ranSwipe(970, 485, 970, 179, 500, (long) 500, 1);
                        if (frequencyMap("bao_end", 2)) {
                            setTaskEnd();
                            return;
                        }
                    }
                }
            }

            void content_02() throws Exception {
                timeCount(5, 0);

                gamePublicFuntion.yan();

                gamePublicFuntion.errClick();

                result = mFairy.findPic("xiang3.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(1000);
                    gamePublicFuntion.errClick = 0;


                    for (int i = 0; i < 7; i++) {
                        mFairy.onTap(1110, 478, 1127, 494, "+", 200);
                    }

                    mFairy.onTap(992, 568, 1050, 582, "上交", 5000);
                    setTaskEnd();
                    return;

                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(5, false, 0.85f)) {

                    }
                }
            }
        };
    }//烧香***

    private String zyp_name = "z1.png";

    public void zyp() throws Exception {
        new Single_Content(mFairy, "知音谱") {

            void create() throws Exception {
                super.create();
                zyp_name = "z" + AtFairyConfig.getOption("zyp") + ".png";
            }

            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.mainScene()) {

                    if (oneSecond()) {
                        mFairy.ranSwipe(197, 512, 465, 451, 1000, 2000);
                        return;
                    }

                    result = mFairy.findPic(917, 149, 1266, 700, "zyp2.png");
                    if (result.sim > 0.85f) {
                        err = 0;
                        mFairy.onTap(0.85f, result, "知交", 1000);
                        return;
                    }

                    result = mFairy.findPic(917, 149, 1266, 700, "zyp1.png");
                    if (result.sim > 0.93f) {
                        mFairy.onTap(0.93f, result, "行游录", 1000);
                        return;
                    } else {
                        mFairy.onTap(1221, 191, 1235, 208, "点击缩放栏", 1000);
                    }

                }

                result = mFairy.findPic("zyp4.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    result = mFairy.findPic("zyp8.png");
                    if (result.sim > 0.95f) {
                        mFairy.onTap(1118, 84, 1137, 97, "", 500);
                        setTaskEnd();
                        return;
                    }
                    if (oneSecond()) {
                        result = mFairy.findPic("zyp3.png");
                        mFairy.onTap(0.85f, result, "知音谱", 1000);
                    }

                    FindResult zj = mFairy.findPic(226, 149, 1104, 192, zyp_name);
                    if (zj.sim > 0.8f) {

                        result = mFairy.findPic(zj.x - 37, zj.y + 328, zj.x + 54, zj.y + 417, "zyp5.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(1118, 84, 1137, 97, "", 500);
                            setTaskEnd();
                            return;
                        }
                        mFairy.onTap(zj.x - 35, zj.y + 376, zj.x - 34, zj.y + 377, "", 2000);
                        setTaskName(2);
                        return;
                    }

                    if (frequencyMap("ran", 1)) {
                        mFairy.ranSwipe(859, 331, 436, 331, 500, (long) 1000, 1);
                        if (frequencyMap("ran_err", 5)) {
                            mFairy.onTap(1118, 84, 1137, 97, "", 500);
                            setTaskEnd();
                            return;
                        }
                    }
                }


            }

            void content_02() throws Exception {
                if (timeCount(5, 0)) {
                    mFairy.ranSwipe(187, 548, 457, 509, 1000, 1000);
                    return;
                }

                FindResult wh = mFairy.findPic(954, 140, 1059, 247, "zyp6.png");
                if (wh.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("zyp7.png");
                    if (result.sim > 0.9f) {
                        mFairy.onTap(1211, 52, 1229, 65, "", 500);
                        setTaskEnd();
                        return;
                    }

                    mFairy.onTap(0.8f, wh, "问候", 500);
                    mFairy.onTap(0.8f, wh, "问候", 500);

                    if (mapCount(0.8f, 601, 161, 659, 453, "zyp9.png")) {
                        mFairy.onTap(1211, 52, 1229, 65, "", 500);
                        setTaskEnd();
                    }
                    return;
                }

                gamePublicFuntion.chat2();

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(6, true, 0.85f)) {

                    }
                }

            }
        };
    }//知音谱***

    private int ling = 1;

    public void ling() throws Exception {
        new TaskContent(mFairy, "签到领奖") {
            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
                ling = 1;
            }

            void inOperation() throws Exception {
                result = gamePublicFuntion.qx();
                mFairy.onTap(0.8f, result, "取消", 500);
            }

            void lings() throws Exception {
                result = mFairy.findPic("ling2.png");
                if (result.sim > 0.85f) {
                    result = mFairy.findPic("ling15.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(717, 240, 745, 261, "每日运势", 2500);

                        for (int i = 0; i < 10; i++) {
                            mFairy.onTap(640, 392, 673, 421, "摇", 300);
                        }


                        for (int i = 0; i < 20; i++) {
                            Thread.sleep(1000);

                            result = mFairy.findPic("ling3.png");
                            if (result.sim > 0.85f) {
                                mFairy.onTap(1213, 52, 1232, 70, "", 1000);
                                break;
                            }
                        }
                    }

                    mFairy.onTap(368, 559, 390, 574, "", 500);
                    mFairy.onTap(636, 559, 657, 578, "", 500);
                    mFairy.onTap(936, 560, 952, 575, "", 500);
                }

                result = mFairy.findPic("ling4.png");
                if (result.sim > 0.8f) {
                    for (int i = 0; i < 120; i++) {
                        Thread.sleep(500);

                        result = mFairy.findPic(594, 536, 873, 643, "ling5.png");
                        mFairy.onTap(0.85f, result, "一键领取", 500);

                        result = mFairy.findPic(594, 536, 873, 643, "ling16.png");
                        mFairy.onTap(0.75f, result, "抽取", 2000);

                        result = mFairy.findPic(624, 554, 829, 633, "ling14.png");
                        if (result.sim > 0.8f) {
                            break;
                        }

                        result = mFairy.findPic(534, 557, 923, 630, "ling17.png");
                        if (result.sim > 0.85f) {
                            break;
                        }

                        result = mFairy.findPic(558, 529, 920, 632, "ling18.png");
                        if (result.sim > 0.85f) {
                            break;
                        }
                    }
                }
            }//领

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(599, 4, 1006, 81, "ling.png");
                mFairy.onTap(0.8f, result, "福利", 500);

                result = mFairy.findPic("ling1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    for (int i = 0; i < 7; i++) {
                        mFairy.onTap(183, 152 + (i * 65), 220, 172 + (i * 65), "", 2000);

                        lings();
                    }
                    mFairy.onTap(1176, 68, 1206, 87, "", 500);
                    setTaskName(2);
                    gamePublicFuntion.init(0);
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic(818, 4, 1079, 71, "ling6.png");
                mFairy.onTap(0.8f, result, "商城", 500);

                result = mFairy.findPic(1198, 64, 1272, 668, "xlyy8.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    switch (ling) {
                        case 1:
                            result = mFairy.findPic(897, 440, 1160, 658, "lj1.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "领取", 500);
                                ling = 2;
                                return;
                            } else {
                                result = mFairy.findPic("ling7.png");
                                mFairy.onTap(0.8f, result, "商", 1000);

                                result = mFairy.findPic(89, 42, 775, 128, "ling19.png");
                                mFairy.onTap(0.8f, result, "特惠促销", 1000);

                                result = mFairy.findPic(31, 105, 313, 593, "ling8.png");
                                mFairy.onTap(0.8f, result, "每日礼包", 1000);

                                result = mFairy.findPic(758, 529, 1255, 702, "lj2.png");
                                mFairy.onTap(0.8f, result, "免费领取", 1000);

                                result = mFairy.findPic("lj3.png");
                                mFairy.onTap(0.8f, result, "领取", 1000);

                            }

                            result = mFairy.findPic("ling11.png");
                            if (result.sim > 0.8f) {
                                ling = 2;
                                return;
                            }

                            break;
                        case 2:
                            result = mFairy.findPic(21, 36, 1225, 121, "ling12.png");
                            mFairy.onTap(0.8f, result, "福源金册", 500);

                            result = mFairy.findPic(345, 558, 931, 703, "ling13.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "一键领取", 500);
                                mFairy.onTap(1215, 26, 1237, 46, "", 500);
                                setTaskEnd();
                                return;
                            }
                            break;
                    }
                }
            }
        };

    }//签到领奖***

    public void cj() throws Exception {
        new Single_Content(mFairy, "采集") {

            void create() throws Exception {
                mFairy.initMatTime();
            }

            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }

            void inOperation() throws Exception {
                gamePublicFuntion.lv();
                gamePublicFuntion.skipScene();
                gamePublicFuntion.deng();
                cancel();
                use();

                if (gamePublicFuntion.fhs()) {
                    setTaskName(0);
                    return;
                }
            }

            void content_01() throws Exception {
                timeCount(60, 0);

                if (gamePublicFuntion.gm()) {
                    gamePublicFuntion.close(3);
                }

                result = mFairy.findPic(949, 381, 1039, 474, "cy1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "道具不足", 1500);
                    return;
                } else {
                    result = mFairy.findPic(873, 432, 938, 482, new String[]{"cy.png", "cy2.png","cy6.png","cy7.png","cy8.png"});
                    if (result.sim > 0.7f) {
                        err = 0;
                        mFairy.initMatTime();
                        mFairy.onTap(0.7f, result, "采集", 500);

                        if (mapCount(0.8f, "cy5.png")) {
                            LtLog.e(mFairy.getLineInfo("体力不足"));
                            setTaskEnd();
                            return;
                        } else {

                            Thread.sleep(1000);
                            result = mFairy.findPic(949, 381, 1039, 474, "cy1.png");
                            if (result.sim > 0.8f) {
                                return;
                            }


                            Thread.sleep(6000);
                        }

                        result = mFairy.findPic(1075, 22, 1124, 98, "cy3.png");
                        mFairy.onTap(0.72f, result, "开始切线", 1000);
                    }
                }

                gamePublicFuntion.huanxian();

                gamePublicFuntion.auto1(1);

                if (gamePublicFuntion.mainScene()) {
                    err = 0;
                    if (gamePublicFuntion.judgeStop(120, false, 0.85f)) {
                        setTaskEnd();
                        return;
                    }
                }
            }
        };
    }//采集

    public void mdsy() throws Exception {
        new Single_Content(mFairy, "迷碟深渊") {

            void create() throws Exception {
                super.create();
                activity_name = "mdsy.png";
                activity_type = 1;
            }

            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
                oneSecond = 0;
            }

            void content_02() throws Exception {
               timeCount(8, 0);


                result = mFairy.findPic("mdsy3.png");
                if(result.sim>0.8f) {
                    mFairy.onTap(0.8f, result, "发起挑战1", 1000);
                    if(frequencyMap("tz",5)){
                        setTaskEnd();
                        return;
                    }

                }else{
                    frequencyInit("tz");
                }

                result = mFairy.findPic("mdsy4.png");
                mFairy.onTap(0.8f,result,"直接挑战1",5000);

                result = mFairy.findPic("mdsy5.png");
                mFairy.onTap(0.8f,result,"同意",1000);


                result = mFairy.findPic("mdsy6.png");
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("mdsy1.png");
                if (result.sim > 0.8f) {
                    err=0;

                    result = mFairy.findPic("mdsy7.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1020,577,1053,586,"",1500);
                        mFairy.onTap(813,435,847,445,"",1000);
                        return;
                    }

                    result = mFairy.findPic("mdsy9.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1020,577,1053,586,"",1500);
                        mFairy.onTap(813,435,847,445,"",1000);
                        return;
                    }

                    result = mFairy.findPic(122,113,640,634,"mdsy2.png");
                    mFairy.onTap(0.8f,result,"发起挑战",1000);

                }


                result = mFairy.findPic("mdsy8.png");
                if (result.sim > 0.8f) {
                    if(AtFairyConfig.getOption("mdsysb").equals("1")){
                        mFairy.onTap( 807,436,841,447,"消耗",1000);
                    }else{
                        mFairy.onTap( 807,436,841,447,"不消耗",1000);
                    }

                }


                if (gamePublicFuntion.fb()) {
                    err = 0;
                    gamePublicFuntion.autoNew();
                    Thread.sleep(2000);
                }

            }
        };
    }//迷碟深渊***

    public void lczb() throws Exception {
        new Single_Content(mFairy, "灵兽争霸") {

            void create() throws Exception {
                super.create();
                activity_name = "lczb.png";
                activity_type = 1;
            }

            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }

            void content_02() throws Exception {
                timeCount(8, 0);


                result = mFairy.findPic("lczb2.png");
                if(result.sim>0.8f) {
                    err=0;
                    result = mFairy.findPic("lczb5.png");
                    if(result.sim>0.92f) {
                        mFairy.onTap(1215,102,1241,121,"",1000);
                        setTaskEnd();
                        return;
                    }


                    result = mFairy.findPic(668,499,833,576,"lczb3.png");
                    mFairy.onTap(0.8f,result,"挑战",5000);


                }


                result = mFairy.findPic("lczb6.png");
                if(result.sim>0.8f){
                    err=0;
                }

                result = mFairy.findPic(799,505,1074,604,"lczb4.png");
                mFairy.onTap(0.8f,result,"挑战",5000);

                result = mFairy.findPic(515,621,760,692,"kong.png");
                mFairy.onTap(0.8f,result,"点击空白",1000);

                result = mFairy.findPic(997,240,1145,566,"lczb1.png");
                mFairy.onTap(0.8f,result,"灵兽争霸",1000);

            }
        };
    }//灵兽争霸***

    public void xysl() throws Exception {
        new Single_Content(mFairy, "降妖试炼") {

            void create() throws Exception {
                super.create();
                activity_name = "xysl.png";
                activity_type = 1;
            }

            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }


            void cancel() throws Exception {
                result = mFairy.findPic(368,274,893,365,"xysl2.png");
                if(result.sim>0.8f) {
                    mFairy.onTap(620,437,643,447,"",1000);
                    gamePublicFuntion.init(0);
                    setTaskEnd();
                }else {
                    result = gamePublicFuntion.qx();
                    mFairy.onTap(0.8f, result, "取消", 1000);
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("xysl1.png");
                mFairy.onTap(0.8f,result,"前往修炼",5000);


                if (gamePublicFuntion.fb()) {
                    err = 0;
                    gamePublicFuntion.autoNew();
                    Thread.sleep(2000);
                }

            }
        };
    }//降妖试炼***

    public void hjxx() throws Exception {
        new Single_Content(mFairy, "回家休息") {

            void create() throws Exception {
                super.create();
                activity_name = "hjxx.png";
                activity_type = 4;
            }

            void init() throws Exception {
                gamePublicFuntion.init(0);
                setTaskName(1);
            }



            void content_02() throws Exception {
                if(timeCount(10, 99)){
                    gamePublicFuntion.close(1);
                    gamePublicFuntion.init(0);
                }

                Thread.sleep(500);

                result = mFairy.findPic("hjxx1.png");
                mFairy.onTap(0.8f,result,1129,580,1153,601,"回家",1000);
            }
        };
    }//回家休息***




}

