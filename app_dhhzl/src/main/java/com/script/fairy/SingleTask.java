package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class SingleTask {
    public GamePublicFuntion gamePublicFuntion;
    public AtFairyImpl mFairy;
    private FindResult result;
    public TeamTask teamTask;
    public boolean pipei = false;

    public SingleTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }

    public void setUp() throws Exception {
        new TaskContent(mFairy, "设置") {
            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1248, 17, 1265, 34, "点击设置", 1000);

                result = mFairy.findPic("set3.png");
                mFairy.onTap(0.8f, result, "基本设置", 1000);

                result = mFairy.findPic("set2.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(314, 117, 1100, 223, "set4.png");
                    if (result.sim > 0.8f) {
                        mFairy.ranSwipe(result.x + 5, result.y + 5, 60, result.y + 5, 500, 1000);
                    }
                    mFairy.onTap(519, 479, 531, 495, "同屏人数 - 少", 500);
                    mFairy.onTap(1108, 65, 1122, 80, "", 500);
                    setTaskEnd();
                    return;
                }
                timeCount(10, 0);
            }
        };
    }//设置

    abstract class single extends TaskContent {

        public single(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);
        }

        void create() throws Exception {
            super.create();
            mFairy.initMatTime();
            TaskMain.actitityInitSildeSwitch = true;
            TaskMain.XUNHANG = true;
            GamePublicFuntion.genhujiu = true;
        }

        void init() throws Exception {
            result = mFairy.findPic("activityScene.png");
            if (result.sim < 0.8f) {
                gamePublicFuntion.init(true);
            }
            frequencyInit("actEndCount");
            TaskMain.actitityInitSildeSwitch = true;
            setTaskName(1);
        }

        void activity(final int type, String... taskName) throws Exception {

            result = mFairy.findPic(6, 109, 179, 420, "activity.png");
            if (result.sim > 0.8f) {
                err = 0;
                mFairy.onTap(0.8f, result, "活动", 1000);
                TaskMain.actitityInitSildeSwitch = false;
                initOnceJudge("lhy");
            }

            result = mFairy.findPic("activityScene.png");
            if (result.sim > 0.8f) {
                if (onceJudge("lhy")) {
                    gamePublicFuntion.lhy();
                }

                LtLog.e(mFairy.getLineInfo("【活动界面】"));
                switch (type) {
                    case 1:
                        mFairy.onTap(72, 110, 106, 126, "单人日常", 1000);
                        break;
                    case 2:
                        mFairy.onTap(72, 207, 111, 221, "组队日常", 1000);
                        break;
                    case 3:
                        mFairy.onTap(81, 292, 129, 317, "每周活动", 1000);
                        break;
                    case 4:
                        mFairy.onTap(71, 384, 120, 400, "限时活动", 1000);
                        break;
                }

                FindResult act = mFairy.findPic(213, 110, 1197, 523, taskName);
                LtLog.e(mFairy.getLineInfo("act:"+act.sim));
                if (act.sim > 0.72f) {
                    frequencyInit("actEndCount");

                    if (activityJuage(act)) {//用来解决  参加活动前 部分任务判断是否次数已满
                        return;
                    }

                    result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "can.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "参加", 500);

                        if (activityEnd()) {//用来解决点击参加后 部分任务会提示无法执行的判断
                            setTaskEnd();
                            return;
                        }

                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, new String[]{"actend1.png", "actend2.png", "cha.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("活动无法开启"));
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "wancheng.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("活动已完成"));
                        gamePublicFuntion.lhy();
                        setTaskEnd();
                        return;
                    }
                }

                if (err == 2) {
                    zact(taskName);
                }

                if (TaskMain.actitityInitSildeSwitch) {
                    activitySlide.slideRange(err, new int[]{1, 3, 4, 5}, 0);
                } else {
                    activitySlide.slideRange(err, new int[]{3, 4, 5});
                }


                if (timeCount(8, 99)) {
                    LtLog.e(mFairy.getLineInfo("活动没找到"));
                    return;
                }
            } else {
                if (frequencyMap("actEndCount", 5)) {
                    setTaskName(0);
                    return;
                }
            }


        }

        public void zact(String... taskName) throws Exception {
            mFairy.touchDown(709, 152);
            mFairy.touchMove(706, 600, 1000);
            FindResult act = mFairy.findPic(311, 263, 1072, 325, taskName);
            if (act.sim > 0.8f) {

                if (activityJuage(act)) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "can.png");
                if (result.sim > 0.8f) {
                    mFairy.touchUp();
                    Thread.sleep(1000);

                    if (result.x > 900) {
                        mFairy.onTap(1107, 180, 1143, 190, "参加", 500);
                    } else {
                        mFairy.onTap(615, 179, 641, 191, "参加", 500);
                    }

                    if (activityEnd()) {//用来解决点击参加后 部分任务会提示无法执行的判断
                        setTaskEnd();
                        return;
                    } else {
                        setTaskName(2);
                    }
                } else {
/*
                    result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, new String[]{"actend1.png", "actend2.png", "cha.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("活动无法开启"));
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "wancheng.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("活动已完成"));
                        gamePublicFuntion.lhy();
                        setTaskEnd();
                        return;
                    }*/

                    LtLog.e(mFairy.getLineInfo("没有发现参加,End!"));
                    setTaskEnd();
                }
            }
            mFairy.touchUp();
        }//避免喇叭

        void inOperation() throws Exception {

            FindResult c = gamePublicFuntion.cancel();
            if (c.sim > 0.8f) {

                result = mFairy.findPic(168, 443, 383, 550, "caozuo.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(866, 591, 891, 604, "", 5000);
                    return;
                }

                result = mFairy.findPic(356, 190, 885, 406, "song3.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(734, 452, 753, 472, "确定拖航", 20000);
                    return;
                }

                if (cancel()) {

                    return;
                }

                if (frequencyMap("cancel", 1)) {
                    mFairy.onTap(0.8f, c, "取消 | 拒绝", 1000);
                }
            } else {
                frequencyInit("cancel");
            }

            /*if (gamePublicFuntion.chat(false)) {
                err = 0;
            }*/

            gamePublicFuntion.guotu();

            if (!getName().equals("商会委托")) {
                shibai();
            }

            gamePublicFuntion.yanzheng();

            closeUseOrUser();

            result = mFairy.findPic("kanjia.png");
            mFairy.onTap(0.8f, result, 623, 469, 653, 486, "砍价", 1000);

            result = mFairy.findPic(529, 109, 748, 201, new String[]{"yuji.png", "yuji1.png"});
            if (result.sim > 0.8f) {
                if (TaskMain.XUNHANG) {
                    long s = mFairy.getColorNum(547, 160, 573, 187, "201,184,126", 0.95f);
                    if (s > 20) {
                        if (timeMap("chuansong", 60000, true)) {
                            mFairy.onTap(562, 173, 563, 174, "查看是否可以免费传送", 2000);
                        }
                    }
                }
                mFairy.initMatTime();
                err = 0;
            }

            result = mFairy.findPic("tuohang.png");
            if (result.sim > 0.8f) {

                if (getName().equals("跑商")) {
                    if (onceJudge("opscs") && AtFairyConfig.getOption("pscs").equals("1")) {
                        if (mFairy.getColorNum(166, 134, 298, 166, "242,244,245", 0.9f) > 30) {
                            mFairy.onTap(211, 148, 255, 164, "消费传送", 1000);
                            mFairy.onTap(733, 458, 757, 467, "", 20000);
                            return;
                        }
                    }
                }

                result = mFairy.findPic(318, 119, 968, 584, "map6.png");
                if (result.sim > 0.8f && TaskMain.XUNHANG) {
                    mFairy.onTap(result.x - 50, result.y, result.x - 49, result.y + 1, "发现可以免费传送", 1000);
                    mFairy.onTap(736, 454, 762, 470, "", 20000);

                    result = mFairy.findPic("tuohang.png");
                    mFairy.onTap(0.8f, result, 1226, 671, 1244, 686, "", 1000);
                    return;
                }

                if (frequencyMap("tuoScene", 2)) {
                    mFairy.onTap(1226, 671, 1244, 686, "", 1000);
                }
            } else {
                frequencyInit("tuoScene");
            }

            result = mFairy.findPic("matou.png");
            if (result.sim > 0.8f) {
                LtLog.e(mFairy.getLineInfo("【码头界面】"));
                err = 0;
                if (mFairy.getColorNum(900, 139, 920, 157, "208,150,35", 0.9f) > 50 && GamePublicFuntion.hejiu) {
                    mFairy.onTap(1226, 24, 1241, 43, "", 500);
                    setTaskName(0);
                    return;
                }

                mFairy.onTap(987, 592, 1045, 606, "一键补给", 500);

                if (getName().equals("黄金航路")) {
                    mFairy.onTap(1221, 30, 1236, 43, "", 1000);
                    return;
                }

                mFairy.onTap(1175, 589, 1193, 608, "出航", 5000);
            }

            result = mFairy.findPic(new String[]{"battle.png", "battle1.png"});
            if (result.sim > 0.7f) {
                LtLog.e(mFairy.getLineInfo("【战斗中】" + result.sim));
                err = 0;

                result = mFairy.findPic("auto1.png");
                mFairy.onTap(0.8f, result, result.x, result.y + 15, result.x + 1, result.y + 20, "副官技能", 1000);

                timeInit("timefb");
                mFairy.initMatTime();
                gamePublicFuntion.auto();
                Thread.sleep(2000);
            }

            result = mFairy.findPic("zoom2.png");
            mFairy.onTap(0.8f, result, "打开任务栏", 500);

            result = mFairy.findPic("task.png");
            mFairy.onTap(0.92f, result, "切换到任务栏", 500);

            result = mFairy.findPic("yunan1.png");
            mFairy.onTap(0.92f, result, "白旗", 2000);

            result = mFairy.findPic("yunan.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "遇难回港", 500);
                setTaskName(0);
                return;
            }
        }

        void shibai() throws Exception {
            gamePublicFuntion.shibai();
        }//失败

        boolean cancel() throws Exception {

            return false;
        }

        boolean activityEnd() throws Exception {
            return false;
        }

        boolean activityJuage(FindResult result) throws Exception {

            return false;
        }

        void rank(String rankName, String activityName) throws Exception {

            result = mFairy.findPic("package.png");
            if (result.sim > 0.8f) {

                result = mFairy.findPic(257, 12, 903, 103, "rank4.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("【无队状态】"));
                    mFairy.onTap(0.7f, result, "", 1000);
                    pipei = false;
                    return;
                }

                result = mFairy.findPic(403, 177, 1039, 331, "rank2.png");
                if (result.sim > 0.75f) {
                    LtLog.e(mFairy.getLineInfo("【队员状态】"));

                    result = mFairy.findPic(237, 171, 1097, 301, "rank22.png");
                    mFairy.onTap(0.75f, result, "查看队伍", 1000);

                    return;
                }

                result = mFairy.findPic(433, 198, 1054, 358, "rank6.png");
                if (result.sim > 0.75f) {
                    LtLog.e(mFairy.getLineInfo("【队长状态】"));
                    err = 0;
                    result = mFairy.findPic(428, 120, 1039, 244, "rank3.png");
                    if (result.sim > 0.75f) {
                        mFairy.onTap(0.75f, result, "退出队伍", 1000);
                        mFairy.onTap(733, 453, 763, 466, "", 1000);
                    }
                    return;
                }

                result = mFairy.findPic(443, 13, 971, 124, "rank1.png");
                mFairy.onTap(0.7f, result, "队伍操作", 1000);
            }

            result = mFairy.findPic(new String[]{"rank7.png", "rank24.png"});
            if (result.sim > 0.8f) {
                LtLog.e(mFairy.getLineInfo("【组队界面】"));
                err = 0;

                result = mFairy.findPic("rank5.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(1001, 629, 1258, 713, "rank21.png");
                    if (result.sim > 0.8f && pipei) {
                        frequencyInit("pipei");
                        frequencyInit("actErr");
                        timeInit("timefb");
                        if (timeMap("刷新", 30000, true)) {
                            result = mFairy.findPic(609, 624, 704, 707, "sq1.png");
                            mFairy.onTap(0.8f, result, "刷新", 1000);

                            for (modularLookup = 0; modularLookup < 5; modularLookup++) {
                                result = modularLookup(1067, 78, 1212, 194, "sq.png");
                                mFairy.onTap(0.8f, result, "点申请", 1000);
                            }
                        }

                        LtLog.e(mFairy.getLineInfo("【匹配中 " + activityName + "】"));
                        return;
                    }

                    if (frequencyMap("pipei", 1) == false) {
                        return;
                    }

                    result = mFairy.findPic(11, 62, 239, 640, activityName);
                    if (result.sim > 0.8f) {
                        pipei = true;
                        frequencyInit("actErr");
                        frequencyInit("rErr");
                        mFairy.onTap(0.8f, result, "找到需要匹配的活动", 1000);

                        if (getName().equals("利维坦的咆哮")) {

                            mFairy.ranSwipe(118, 491, 115, 377, 500, 500);
                            switch (AtFairyConfig.getOption("lwtdpx")) {
                                case "1":
                                    result = mFairy.findPic(11, 62, 239, 640, "lwtdpx3.png");
                                    break;
                                case "2":
                                    result = mFairy.findPic(11, 62, 239, 640, "lwtdpx4.png");
                                    break;
                                case "3":
                                    result = mFairy.findPic(11, 62, 239, 640, "lwtdpx5.png");
                                    break;
                                case "4":
                                    result = mFairy.findPic(11, 62, 239, 640, "lwtdpx6.png");
                                    break;
                                default:
                                    result = mFairy.findPic(11, 62, 239, 640, "lwtdpx3.png");
                                    break;
                            }

                            mFairy.onTap(0.8f, result, "选择难度", 1000);

                        }

                        result = mFairy.findPic(1001, 629, 1258, 713, "rank21.png");
                        mFairy.onTap(0.8f, result, "先取消匹配", 2000);

                        result = mFairy.findPic("rank20.png");
                        mFairy.onTap(0.8f, result, "自动匹配", 1000);

                    } else {
                        mFairy.ranSwipe(112, 499, 118, 302, 500, 1000);
                    }

                    if (frequencyMap("rErr", 10)) {
                        LtLog.e(mFairy.getLineInfo("没有找到活动"));

                        if (frequencyMap("actErr", 1)) {
                            setTaskEnd();
                        }
                        mFairy.onTap(1236, 14, 1254, 29, "", 500);
                    }
                    return;
                }


                result = mFairy.findPic(87, 55, 282, 134, rankName);
                if (result.sim > 0.8f) {
                    frequencyInit("rankpi");

                    result = mFairy.findPic(376, 595, 1255, 713, "rank19.png");
                    mFairy.onTap(0.8f, result, "回归队伍", 1000);

                    mFairy.onTap(1236, 16, 1260, 28, "", 500);

                    setTaskName(3);
                    return;
                } else {
                    if (frequencyMap("rankpi", 2)) {
                        result = mFairy.findPic(376, 595, 1255, 713, "rank18.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "退出队伍", 1000);
                            mFairy.onTap(733, 453, 763, 466, "", 1000);
                            return;
                        }
                    }
                }
            }
            timeCount(10, 0);
        }

        public void closeUseOrUser() throws Exception {
            result = mFairy.findPic("closeUse.png");
            mFairy.onTap(0.8f, result, "closeUse", 1000);

            /*
                result = mFairy.findPic(892,481,1051,602,"use.png");
            mFairy.onTap(0.8f,result,"use",1000);
             */
        }

    }//单人任务抽象类 整理所有单人任务需要的点击

    public void map(final boolean song, final String map, final int... xy) throws Exception {
        new single(mFairy, "前往指定港口") {

            void create() throws Exception {
                super.create();
                TaskMain.XUNHANG = song;
            }

            boolean cancel() throws Exception {

                result = mFairy.findPic(406, 243, 689, 384, "map5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(728, 453, 763, 468, "确定", 1000);
                    setTaskName(2);
                    return true;
                }

                return false;
            }

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1131, 18, 1162, 31, "点击地图", 5000);

                result = mFairy.findPic("map4.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "前往", 3000);

                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic("map.png");
                if (result.sim > 0.8f) {
                    err = 0;


                    result = mFairy.findPic(11,4,123,65,new String[]{"map2.png","map7.png"});
                    if (result.sim > 0.9f) {

                        /*if (onceJudge("slide")) {
                            for (int i = 0; i < slide; i++) {
                                mFairy.ranSwipe(136, 620, 136, 200, 200, 300);
                            }
                            Thread.sleep(1500);
                        }*/

                        result = mFairy.findPic(46, 72, 209, 715, map);
                        LtLog.e(mFairy.getLineInfo("左侧地图名称："+result.sim+","+map));
                        if (result.sim > 0.7f) {
                            mFairy.onTap(0.7f, result, "找到目标港口", 2000);

                            if (xy.length == 0) {
                                mFairy.onTap(639, 348, 646, 355, "点击城镇", 500);
                                mFairy.onTap(639, 348, 646, 355, "点击城镇", 3000);
                            } else {

                                mFairy.onTap(592, 312, 602, 321, "位置", 1000);
                                mFairy.onTap(xy[0], xy[1], xy[0] + 1, xy[1] + 1, "位置", 3000);

                                result = mFairy.findPic(349, 222, 606, 362, "chuanzhang.png");
                                if (result.sim > 0.8f) {
                                    mFairy.onTap(732, 452, 772, 472, "确定自动巡航", 3000);
                                    setTaskName(2);
                                    return;
                                }

                                result = mFairy.findPic(406, 243, 689, 384, "map5.png");
                                if (result.sim > 0.7f) {
                                    mFairy.onTap(728, 453, 763, 468, "确定", 1000);
                                    setTaskName(2);
                                    return;
                                } else {

                                    result = mFairy.findPic("map4.png");
                                    if (result.sim < 0.8f) {
                                        setTaskEnd();
                                        return;
                                    }
                                }
                            }

                            frequencyInit("map");
                            frequencyInit("map_err");
                            return;
                        }

                        mFairy.ranSwipe(136, 620, 136, 200, 500, 2000);

                        if (frequencyMap("map_err", 16)) {
                            setTaskName(0);
                            return;
                        }
                    } else {

                        if (map.equals("rny4.png") && xy.length == 0) {
                            result = mFairy.findPic(79, 11, 1219, 701, "rny5.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "点击城镇", 3000);
                                return;
                            }
                        }

                        mFairy.onTap(50, 37, 62, 47, "点击搜索", 1500);

                        result = mFairy.findPic("map3.png");
                        mFairy.onTap(0.9f, result, "港口", 1000);
                    }
                }
            }

            void content_02() throws Exception {

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    if (gamePublicFuntion.judgeStop(5)) {
                        if (frequencyMap("stop", 2)) {
                            LtLog.e(mFairy.getLineInfo("到达目的地"));
                            setTaskEnd();
                            return;
                        }

                    } else {
                        frequencyInit("stop");
                        err = 0;
                    }
                }

                Thread.sleep(1000);

                timeCount(10, 0);
            }
        };
    }//前往指定港口

    public void tuidui() throws Exception {
        new TaskContent(mFairy, "退队") {

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(532, 19, 1214, 102, new String[]{"qx.png", "qx1.png"});
                    mFairy.onTap(0.8f, result, "取消匹配", 1000);

                    result = mFairy.findPic(257, 12, 903, 103, "rank4.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("【无队状态】"));
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(403, 177, 1039, 331, new String[]{"rank2.png", "rank6.png"});
                    if (result.sim > 0.75f) {
                        err = 0;
                        LtLog.e(mFairy.getLineInfo("【组队状态】"));

                        result = mFairy.findPic(428, 120, 1039, 244, "rank3.png");
                        if (result.sim > 0.75f) {
                            mFairy.onTap(0.75f, result, "退出队伍", 1000);
                            mFairy.onTap(733, 453, 763, 466, "", 1000);
                        }
                        return;
                    }

                    result = mFairy.findPic(443, 13, 971, 124, "rank1.png");
                    mFairy.onTap(0.7f, result, "队伍操作", 1000);
                }
                timeCount(10, 0);
            }
        };
    }//退队

    public void rny(final String tmap, final String map) throws Exception {
        new single(mFairy, "拖航到 " + tmap) {

            void create() throws Exception {
                super.create();
                TaskMain.XUNHANG = false;
            }

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
                TaskMain.XUNHANG = false;
            }


            boolean cancel() throws Exception {

                result = mFairy.findPic(412,226,860,376,"th2.png");
                if(result.sim>0.8f){
                    mFairy.onTap(743,459,759,471,"",1000);
                    return true;
                }


                return super.cancel();
            }

            void content_01() throws Exception {

                result = mFairy.findPic(928,3,1269,85,tmap);
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("【到达" + tmap + "】"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, "背包", 2500);

                result = mFairy.findPic("packageScene.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (onceJudge("pack")) {
                        mFairy.onTap(55, 118, 78, 129, "背包", 500);
                        mFairy.onTap(802, 86, 822, 97, "综合", 1000);
                    }

                    result = mFairy.findPic(407, 376, 711, 647, "song2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "使用", 2000);
                        return;
                    }

                    result = mFairy.findPic(749, 125, 1272, 693, "song1.png");
                    if (result.sim > 0.8f) {
                        frequencyInit("song");
                        frequencyInit("songEnd");
                        mFairy.onTap(0.8f, result, "点击传送卷轴", 2000);

                    } else {
                        if (frequencyMap("song", 2)) {
                            if (frequencyMap("songEnd", 1)) {
                                LtLog.e(mFairy.getLineInfo("没有找到传送卷轴"));
                                setTaskEnd();
                                return;
                            }
                            mFairy.ranSwipe(1021, 541, 1021, 154, 500, 1000);
                        }
                    }
                }

                result = mFairy.findPic("tuohang.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(309, 111, 1128, 609, map);
                    LtLog.e(mFairy.getLineInfo("home地图名称："+result.sim));
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "点击目标地图", 1500);

                        result = mFairy.findPic(612,228,930,348, "zanli5.png");
                        mFairy.onTap(0.8f, result, 737,454,757,464, "紧急拖航", 1500);

                        result = mFairy.findPic(379, 243, 750, 331, "jinji.png");
                        mFairy.onTap(0.8f, result, 740, 448, 752, 462, "紧急拖航", 1500);

                        result = mFairy.findPic(417,241,855,400,"th1.png");
                        mFairy.onTap(0.8f, result, 740, 448, 752, 462, "紧急拖航", 1500);

                        mFairy.onTap(734, 452, 753, 472, "确定拖航", 20000);
                        return;
                    }

                    if (frequencyMap("tmap", 5)) {
                        LtLog.e(mFairy.getLineInfo("目标地图出现异常!!!"));
                        setTaskName(0);
                        return;
                    }

                }
                timeCount(10, 0);
            }

        };
    }//回热那亚

    public void fle() throws Exception {
        new single(mFairy, "芙洛儿的烦恼") {

            void content_01() throws Exception {
                activity(1, "fle.png");
            }

            void content_02() throws Exception {
                gamePublicFuntion.chat();
                timeCount(2, 99);
            }
        };
    }//芙洛儿的烦恼

    public void tp() throws Exception {
        new single(mFairy, "首席大臣竞选") {

            void content_01() throws Exception {

                result = mFairy.findPic("zhujue.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("t1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1225, 14, 1249, 31, "", 1000);
                        rny("tp2.png", "tp1.png");//伦敦
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic("t2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1225, 14, 1249, 31, "", 1000);
                        rny("tp4.png", "tp3.png");//塞维利亚
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic("t3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1225, 14, 1249, 31, "", 1000);
                        rny("tp6.png", "tp5.png");//马赛
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic("t4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1225, 14, 1249, 31, "", 1000);
                        rny("tp8.png", "tp7.png");//阿姆斯特丹
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic("t5.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1225, 14, 1249, 31, "", 1000);
                        rny("tp10.png", "tp9.png");//里斯本
                        setTaskName(2);
                        return;
                    }
                } else {
                    result = mFairy.findPic("package.png");
                    mFairy.onTap(0.8f, result, 47, 52, 62, 68, "点击头像", 3000);
                }

            }

            void content_02() throws Exception {

                result = mFairy.findPic("tp15.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    frequencyInit("huanggong");

                    for (int i = 0; i < 2; i++) {
                        mFairy.onTap(1011, 189, 1046, 198, "支持", 1000);
                        mFairy.onTap(732, 461, 763, 470, "", 500);
                    }
                    mFairy.onTap(1128, 45, 1142, 57, "", 500);
                    mFairy.onTap(1207, 26, 1234, 39, "", 1000);
                    setTaskEnd();
                }

                result = mFairy.findPic("tp12.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    frequencyInit("huanggong");

                    result = mFairy.findPic("tp13.png");
                    mFairy.onTap(0.8f, result, "找首席大臣", 2000);

                    result = mFairy.findPic(944, 280, 1251, 698, "tp14.png");
                    mFairy.onTap(0.8f, result, "竞选", 2000);

                } else {
                    if (frequencyMap("huanggong", 2)) {
                        gamePublicFuntion.sildeArchitecture("tp11.png", "gjf.png", "gongjue.png");
                    }
                }

                timeCount(5, 99);
            }
        };
    }//首席大臣竞选

    public void guojia() throws Exception {
        new single(mFairy, "国家任务") {

            void content_01() throws Exception {
                activity(1, "guojia.png");
            }

            void content_02() throws Exception {

                result = mFairy.findPic(425, 130, 597, 255, "guojia3.png");
                mFairy.onTap(0.8f, result, "需求", 1500);

                gamePublicFuntion.gm();

                if (frequencyMap("chat", 1)) {
                    gamePublicFuntion.chat();
                }

                result = mFairy.findPic("sh1.png");
                mFairy.onTap(0.8f, result, 897, 25, 937, 37, "商会", 1000);

                result = mFairy.findPic(956, 363, 1254, 632, "guojia2.png");
                mFairy.onTap(0.8f, result, "国家任务", 1000);

                if (gamePublicFuntion.taskClick("guojia1.png")) {
                    setTaskName(0);
                    return;
                }

               /* result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    FindResult task = mFairy.findPic(1053, 280, 1200, 522, "guojia1.png");
                    if (task.sim > 0.75f) {
                        err = 0;
                        //1068,416  1228,393,1277,456
                        result = mFairy.findPic(task.x + 160, task.y - 32, task.x + 209, task.y + 40, "task2.png");
                        if (result.sim < 0.8f) {
                            mFairy.onTap(0.75f, task, "点击右侧任务", 1000);
                            return;
                        }
                    } else {
                        taskSlide.slideRange(err, new int[]{4, 6, 8});
                    }
                }*/

                if (timeCount(8, 0)) {
                    result = mFairy.findPic("package.png");
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 3; i++) {
                            mFairy.ranSwipe(1165, 313, 1164, 500, 300, 500);
                        }
                    }
                }
            }
        };
    }//国家任务

    public void ts() throws Exception {
        new single(mFairy, "消耗探索点") {

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
            }

            void content_01() throws Exception {
                result = mFairy.findPic("ts1.png");
                if (result.sim > 0.8f) {
                    frequencyInit("shibie");
                    initOnceJudge("ts_silde");
                    setTaskName(2);
                } else {
                    if (frequencyMap("shibie", 2)) {
                        LtLog.e(mFairy.getLineInfo("【没有在指定港口,开始前往】"));
                        map(true, "ld.png", 541, 487);
                    }
                }
            }

            void content_02() throws Exception {

                result = mFairy.findPic("ts1.png");
                mFairy.onTap(0.8f, result, "进入地下城", 1000);

                gamePublicFuntion.chat();

                result = mFairy.findPic("ts3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(846, 57, 1085, 156, "ts2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1221, 19, 1234, 38, "", 500);
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic("ts4.png");
                    mFairy.onTap(0.8f, result, "进入", 3000);
                }

                result = mFairy.findPic("ts6.png");
                if (result.sim > 0.72f) {

                    err = 0;

                    if (onceJudge("ts_close")) {
                        for (int i = 0; i < 5; i++) {
                            result = mFairy.findPic(0, 0, 1269, 719, "ts5.png");
                            mFairy.onTap(0.8f, result, "知道了", 2000);
                        }
                    }

                    if (onceJudge("ts_silde")) {
                        Thread.sleep(6000);
                        LtLog.e(mFairy.getLineInfo("滑动"));
                        mFairy.touchDown(2, 162, 585);
                        mFairy.touchMove(2, 162, 430, 2000);
                        mFairy.touchUp(2);
                    }

                    result = mFairy.findPic("ts7.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "挖掘", 1000);

                        frequencyInit("wj");

                        if (mapCount(0.8f, 611, 209, 785, 581, "ts8.png")) {
                            setTaskName(3);
                            return;
                        } else {
                            Thread.sleep(4000);
                        }
                    }

                    mFairy.onTap(1129, 16, 1143, 37, "刷新", 800);
                    mFairy.onTap(736, 454, 757, 467, "", 300);

                    if (mapCount(0.75f, 611, 209, 785, 581, "ts8.png")) {
                        setTaskName(3);
                        return;
                    }

                    if (frequencyMap("wj", 5)) {
                        result = mFairy.findPic("ts6.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "退出副本", 1000);
                            mFairy.onTap(739, 453, 756, 472, "结束探险", 3000);
                            setTaskName(0);
                            return;
                        }
                    }
                }

                timeCount(10, 0);
            }

            void content_03() throws Exception {

                result = mFairy.findPic("ts6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "退出副本", 1000);
                    mFairy.onTap(739, 453, 756, 472, "结束探险", 3000);
                }

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    /*result = mFairy.findPic("rny2.png");
                    if (result.sim > 0.8f) {
                        frequencyInit("fry");
                        setTaskEnd();
                    } else {
                        if (frequencyMap("fry", 2)) {
                            rny();
                        }
                    }*/
                    setTaskEnd();
                }

                timeCount(10, 0);
            }
        };
    }//消耗探索点

    public void touzi() throws Exception {
        new single(mFairy, "投资任务") {

            void content_01() throws Exception {
                activity(1, "touzi.png");
            }

            void content_02() throws Exception {

                result = mFairy.findPic("touzi1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (AtFairyConfig.getOption("touzi").equals("2")) {
                        LtLog.e(mFairy.getLineInfo("【用户勾选100万投资】"));
                        for (int i = 0; i < 12; i++) {
                            mFairy.onTap(1238, 593, 1253, 607, "", 200);
                        }
                    } else {
                        for (int i = 0; i < 5; i++) {
                            mFairy.onTap(1238, 593, 1253, 607, "", 200);
                        }
                    }

                    mFairy.onTap(0.8f, result, "投资", 1000);

                    if (mapCount(0.8f, 646, 210, 817, 565, "touzi2.png")) {
                        rny("rny1.png", "rny3.png");
                        setTaskName(0);
                    } else {
                        setTaskEnd();
                    }
                    return;
                }

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    if (gamePublicFuntion.judgeStop(5) == false) {
                        err = 0;
                    }
                }

                timeCount(10, 0);
            }
        };
    }//投资任务

    public void shanghui() throws Exception {
        new single(mFairy, "商会委托") {
            int position;
            int channel;

            void create() throws Exception {
                super.create();
                position = 1;
                channel = 1;
            }

            void content_01() throws Exception {
                activity(1, "shanghui.png");

            }

            boolean activityEnd() throws Exception {
                if (mapCount(0.8f, 566, 215, 732, 525, "shanghui1.png")) {
                    TaskMain.SHANGHUI = false;
                    return true;
                }
                return false;
            }

            void content_02() throws Exception {

                result = mFairy.findPic("shanghui2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("shwt.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1241, 16, 1252, 32, "", 1000);
                        setTaskEnd();
                        return;
                    }

                    int[] range;

                    switch (position) {
                        case 1:
                            range = new int[]{258, 176, 327, 257};
                            break;
                        case 2:
                            range = new int[]{424, 178, 487, 255};
                            break;
                        case 3:
                            range = new int[]{576, 177, 654, 248};
                            break;
                        case 4:
                            range = new int[]{740, 180, 817, 254};
                            break;
                        case 5:
                            range = new int[]{248, 363, 327, 431};
                            break;
                        case 6:
                            range = new int[]{417, 357, 491, 429};
                            break;
                        case 7:
                            range = new int[]{585, 362, 647, 434};
                            break;
                        case 8:
                            range = new int[]{742, 355, 814, 433};
                            break;
                        default:
                            result = mFairy.findPic("shanghui10.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, 366, 561, 388, 580, "领取额外奖励", 5000);

                                mFairy.onTap(897, 673, 919, 691, "", 1000);

                                setTaskEnd();
                            } else {
                                if (AtFairyConfig.getOption("shwtqz").equals("1")) {
                                    setTaskEnd();
                                    return;
                                }
                                position = 1;
                                setTaskName(0);
                            }
                            return;
                    }

                    result = mFairy.findPic(range[0], range[1], range[2], range[3], "shanghui12.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1212, 669, 1233, 689, "", 500);
                        position++;
                        return;
                    }

                    mFairy.onTap(range[0], range[1], range[2], range[3], "【第" + position + "件商品】", 1000);

                    mFairy.onTap(1049, 606, 1084, 623, "上交", 1500);

                    result = mFairy.findPic(489,62,948,618, "shanghui3.png");
                    if (result.sim > 0.8f /*&& channel != 3*/) {
                        frequencyInit("judge");
                        setTaskName(3);
                        return;
                    } else {
                        mFairy.onTap(1212, 669, 1233, 689, "", 500);
                        channel = 1;
                        position++;
                    }
                }

                timeCount(8, 0);
            }

            void content_03() throws Exception {

                result = mFairy.findPic("shanghui2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    switch (channel) {
                        case 1://先生产
                            result = mFairy.findPic(489,62,948,618, "shanghui4.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "生产", 2000);
                            } else {
                                if (frequencyMap("judge", 2)) {
                                    channel = 2;
                                }
                            }
                            break;
                        case 2://摆摊

                            if (AtFairyConfig.getOption("shwtqz").equals("1")) {
                                result = mFairy.findPic(489,62,948,618, "shanghui5.png");
                                if (result.sim > 0.8f) {
                                    for (int i = 0; i < 3; i++) {
                                        mFairy.onTap(1123, 530, 1159, 539, "求助", 1000);
                                        setTaskName(2);
                                        position++;
                                        return;
                                    }
                                }
                            }

                            result = mFairy.findPic(489,62,948,618, new String[]{"shanghui5.png", "shanghui8.png"});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "摆摊 | 海市", 3000);
                            } else {
                                if (frequencyMap("judge", 2)) {
                                    setTaskName(2);
                                    mFairy.onTap(1212, 669, 1233, 689, "", 500);
                                    position++;
                                    return;
                                }
                            }
                            break;
                    }
                }

                result = mFairy.findPic("dazao.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(1000, 648, 1039, 661, "制作", 500);

                    if (mapCount(0.8f, 500, 252, 712, 526, "shanghui7.png", "shanghui11.png")) {
                        channel = 2;
                    }

                    mFairy.onTap(1230, 11, 1256, 34, "", 1000);
                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic("shanghui9.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    Thread.sleep(3000);

                    result = mFairy.findPic(425, 130, 597, 255, "guojia3.png");
                    mFairy.onTap(0.8f, result, "需求", 1500);

                    gamePublicFuntion.gm();
                    mFairy.onTap(1230, 11, 1256, 34, "", 1000);
                    setTaskName(2);

                    return;
                }

                timeCount(5, 0);
            }
        };
    }//商会委托

    public void wujin() throws Exception {
        new single(mFairy, "无尽的航路") {

            boolean activityJuage(FindResult result) throws Exception {
                FindResult resulte = mFairy.findPic(result.x + 120, result.y - 2, result.x + 249, result.y + 94, "wujin5.png");
                if (resulte.sim > 0.88f) {
                    LtLog.e(mFairy.getLineInfo("次数打完"));
                    setTaskEnd();
                    return true;
                }
                return false;
            }

            void content_01() throws Exception {
                activity(1, "wujin.png");
                initOnceJudge("wujin");
            }

            void content_02() throws Exception {

               /* result = mFairy.findPic("wujin2.png");
                if (result.sim > 0.9f) {
                    mFairy.onTap(1226, 11, 1250, 31, "", 500);
                    mFairy.onTap(1226, 11, 1250, 31, "", 500);
                    setTaskEnd();
                    return;
                }*/

                result = mFairy.findPic("wujin4.png");
                if (result.sim > 0.9f) {
                    mFairy.onTap(1226, 11, 1250, 31, "", 500);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("wujin1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (onceJudge("wujin")) {
                        mFairy.ranSwipe(500 - (new Random().nextInt(50)), 351, 600 + (new Random().nextInt(50)), 351, 500, 1000);
                    }
                    //115, 151, 190, 160
                    for (int i = 0; i < 5; i++) {
                        mFairy.onTap(115, 151 + (i * 84), 190, 160 + (i * 84), "", 200);

                        result = mFairy.findPic(297, 473, 523, 627, "wujin3.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "扫荡", 3000);
                            mFairy.onTap(733, 673, 759, 686, "", 2000);
                            return;
                        }
                    }

                    for (int i = 0; i < 5; i++) {
                        mFairy.onTap(400, 151 + (i * 84), 460, 160 + (i * 84), "", 200);

                        result = mFairy.findPic(297, 473, 523, 627, "wujin3.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "扫荡", 3000);
                            mFairy.onTap(733, 673, 759, 686, "", 2000);
                            return;
                        }
                    }
                }

                timeCount(10, 0);
            }
        };
    }//无尽的航路

    public void wenda() throws Exception {
        new single(mFairy, "航海问答") {

            void content_01() throws Exception {
                activity(1, "wenda.png");
            }

            void content_02() throws Exception {

                result = mFairy.findPic("wenda1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    gamePublicFuntion.wendaAIAnswer();
                    Thread.sleep(3000);
                }
                timeCount(3, 0);
            }
        };
    }//航海问答

    public void bssxxs() throws Exception {
        new single(mFairy, "比萨首席学士") {

            void content_01() throws Exception {
                activity(1, "bssxxs.png");
            }

            void content_02() throws Exception {

                result = mFairy.findPic(1016, 322, 1209, 669, "bssxxs1.png");
                mFairy.onTap(0.75f, result, "主教考核", 1000);

                result = mFairy.findPic("bssxxs2.png");
                mFairy.onTap(0.8f, result, 1020, 271, 1051, 302, "选择科目", 1000);

                result = mFairy.findPic(131, 139, 375, 388, "bssxxs4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1235, 39, 1250, 58, "", 500);
                    mFairy.onTap(1235, 39, 1250, 58, "", 500);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("bdt.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "我在想想", 1000);
                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic("bssxxs3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    gamePublicFuntion.wendaAIAnswer();
                    Thread.sleep(3000);
                }

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    if (gamePublicFuntion.judgeStop(3) == false) {
                        err = 0;
                    }
                }

                timeCount(5, 0);
            }
        };
    }//比萨首席学士

    public void baozang() throws Exception {
        new single(mFairy, "宝藏委托") {

            void create() throws Exception {
                super.create();
                TaskMain.XUNHANG = false;
            }

            void content_01() throws Exception {
                activity(1, "baozang.png");
            }

            void content_02() throws Exception {

                gamePublicFuntion.chat();

                result = mFairy.findPic(425, 130, 597, 255, "guojia3.png");
                mFairy.onTap(0.8f, result, "需求", 1500);

                result = mFairy.findPic("touzi1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "投资", 1500);
                    mFairy.onTap(1219, 32, 1243, 51, "", 500);
                    mFairy.onTap(1219, 32, 1243, 51, "", 1000);
                }

                gamePublicFuntion.gm();
                gamePublicFuntion.jygm();

                result = mFairy.findPic("baozang2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    frequencyInit("close");
                    frequencyInit("baoEnd");
                    if ((result = mFairy.findPic("baozang3.png")).sim > 0.9f) {
                        mFairy.onTap(result.x, result.y + 320, result.x + 1, result.y + 321, "3星", 2000);
                    } else if ((result = mFairy.findPic("baozang4.png")).sim > 0.9f) {
                        mFairy.onTap(result.x, result.y + 320, result.x + 1, result.y + 321, "2星", 2000);
                    } else {
                        mFairy.onTap(311, 547, 337, 561, "没有判断出星级,选第一个", 2000);
                    }
                }

                result = mFairy.findPic(new String[]{"jiuba1.png", "xiuxi1.png", "xiuxi2.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(980, 289, 1219, 654, "baozang1.png");
                    if (result.sim > 0.8f) {
                        frequencyInit("baoEnd");
                        mFairy.onTap(0.8f, result, "宝藏委托", 1000);
                        return;
                    }

                    if (frequencyMap("baoEnd", 2)) {
                        mFairy.onTap(1219, 32, 1243, 51, "", 500);
                        setTaskEnd();
                        return;
                    }
                }


                if (gamePublicFuntion.taskClick("baozang6.png")) {
                    setTaskName(0);
                    return;

                }


                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    frequencyInit("close");
                } else {
                    if (frequencyMap("close", 3)) {
                        gamePublicFuntion.close();
                    }
                }
                timeCount(8, 0);
            }
        };
    }//宝藏委托

    public void banv() throws Exception {
        new single(mFairy, "吧女送礼") {
            int count;

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
            }

            void content_01() throws Exception {
                activity(1, "banv.png");
                count = 1;
            }

            void content_02() throws Exception {
                result = mFairy.findPic(new String[]{"jiuba1.png", "banv11.png", "xiuxi2.png"});
                if (result.sim > 0.8f) {
                    err = 0;

                    switch (count) {
                        case 1:
                            Thread.sleep(1500);
                            result = mFairy.findPic(968, 315, 1232, 706, "banv1.png");
                            mFairy.onTap(0.8f, result, "请吧女喝一杯", 1500);
                            break;
                        case 2:
                            result = mFairy.findPic(968, 315, 1232, 706, "banv3.png");
                            mFairy.onTap(0.85f, result, "聊天", 1500);
                            mFairy.onTap(0.85f, result, "聊天", 1500);

                            break;
                       /* case 3:
                            result = mFairy.findPic(968, 315, 1232, 706, "banv5.png");
                            mFairy.onTap(0.85f, result, "讲故事", 1500);
                            break;*/
                        case 3:
                            result = mFairy.findPic(968, 315, 1232, 706, "banv8.png");
                            mFairy.onTap(0.85f, result, "送礼物", 6000);
                            break;
                        default:
                            mFairy.onTap(1223, 38, 1239, 60, "", 500);
                            setTaskEnd();
                            return;
                    }

                    result = mFairy.findPic("banv2.png");
                    if (result.sim > 0.85f) {
                        frequencyInit("banvs");
                        mFairy.onTap(0.85f, result, "喝一杯", 1500);
                        return;
                    }

                    result = mFairy.findPic("banv4.png");
                    if (result.sim > 0.85f) {
                        frequencyInit("banvs");
                        mFairy.onTap(0.85f, result, "继续聊", 1000);
                        return;
                    }


                    /*result = mFairy.findPic("banv6.png");
                    if (result.sim > 0.85f) {
                        frequencyInit("banvs");
                        //mFairy.onTap(0.85f, result, 991, 136, 1044, 151, "", 500);
                        mFairy.onTap(0.85f, result, 1023, 297, 1047, 318, "", 500);
                        return;
                    }*/


                    result = mFairy.findPic("banv9.png");
                    if (result.sim > 0.85f) {
                        frequencyInit("banvs");
                        /*result = mFairy.findPic("banv10.png");
                        mFairy.onTap(0.85f, result, "显示已拥有", 1000);*/

                        int count = 0;
                        while (mFairy.condit()) {
                            LtLog.e(mFairy.getLineInfo("【寻找皮绳】"));

                            result = mFairy.findPic("dazao.png");
                            if (result.sim > 0.8f) {
                                err = 0;
                                mFairy.onTap(1000, 648, 1039, 661, "制作", 500);
                                mFairy.onTap(1000, 648, 1039, 661, "制作", 500);
                                mFairy.onTap(1000, 648, 1039, 661, "制作", 500);

                                if (mapCount(0.8f, 500, 252, 712, 526, "shanghui7.png", "shanghui11.png")) {
                                    setTaskEnd();
                                    return;
                                }

                                mFairy.onTap(1230, 11, 1256, 34, "", 3000);
                                continue;
                            }

                            FindResult pi = mFairy.findPic(949, 163, 1240, 510, new String[]{"pi4.png", "pi1.png", "pi6.png", "pi7.png"});
                            if (pi.sim > 0.7f) {
                                mFairy.onTap(0.7f, pi, "点击皮绳", 1000);

                                mFairy.onTap(1084, 575, 1116, 586, "赠送", 700);

                                if (mapCount(0.8f, 608, 268, 685, 562, "pi5.png")) {
                                    mFairy.onTap(0.8f, pi, "皮绳不足", 2000);

                                    result = mFairy.findPic(688, 422, 880, 530, "pi3.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(0.8f, result, "获得途径", 2500);

                                        result = mFairy.findPic(826, 90, 1260, 487, "shanghui4.png");
                                        mFairy.onTap(0.8f, result, "生产", 5000);
                                        continue;
                                    }
                                }

                                count++;
                                if (count >= 3) {
                                    mFairy.onTap(1223, 35, 1240, 62, "", 500);
                                    setTaskEnd();
                                    return;
                                }
                                continue;
                            }


                            if (frequencyMap("p", 1)) {
                                mFairy.ranSwipe(1099, 400, 1104, 250, 500, 1000);
                            }

                            if (frequencyMap("pi", 8)) {
                                setTaskEnd();
                                return;
                            }
                        }
                    }
                    count++;
                }

                /*result = mFairy.findPic("banv7.png");
                mFairy.onTap(0.8f, result, "讲", 2000);*/

                timeCount(10, 0);
            }
        };
    }//吧女送礼

    public void banvs() throws Exception {
        new single(mFairy, "吧女送礼S") {
            int count;
            int poisiton;
            boolean song;
            boolean songli;
            boolean banv;
            ArrayList<int[]> l;

            void create() throws Exception {
                super.create();
                poisiton = 0;
                gamePublicFuntion.init(true);
                song = true;
                TaskMain.XUNHANG = false;

               /* if (AtFairyConfig.getOption("banvmm").equals("1")) {

                }*/

                l = new ArrayList();
                l.add(new int[]{388, 213});
                l.add(new int[]{573, 210});
                l.add(new int[]{752, 210});
                l.add(new int[]{382, 444});
                l.add(new int[]{570, 448});
                l.add(new int[]{756, 449});

                setTaskName(2);
            }

            void content_01() throws Exception {

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1131, 18, 1162, 31, "点击地图", 3500);

                result = mFairy.findPic("map4.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "前往", 3000);
                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic("map.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (poisiton > 12) {
                        mFairy.onTap(1227, 28, 1250, 41, "", 1000);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(11,4,123,65,new String[]{"map2.png","map7.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(321, 24, 347, 35, "", 1000);
                        if (onceJudge("bsl")) {
                            mFairy.ranSwipe(170, 93, 170, 616, 200, 300);
                        }

                        if (poisiton == 12) {
                            mFairy.ranSwipe(170, 616, 173, 93, 200, 500);

                            result = mFairy.findPic(239, 60, 295, 120, "banv14.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "点击第12个位置", 2000);
                                mFairy.onTap(639, 348, 646, 355, "点击城镇", 500);
                                mFairy.onTap(639, 348, 646, 355, "点击城镇", 3000);
                            } else {
                                if (frequencyMap("bazhao", 1)) {
                                    poisiton++;
                                }
                            }

                        } else {
                            result = mFairy.findPic(239, 60 + (poisiton * 54), 295, 120 + (poisiton * 54), "banv14.png");
                            if (result.sim > 0.8f) {
                                frequencyInit("bazhao");
                                mFairy.onTap(0.8f, result, "点击第" + (poisiton + 1) + "个位置", 2000);
                                mFairy.onTap(639, 348, 646, 355, "点击城镇", 500);
                                mFairy.onTap(639, 348, 646, 355, "点击城镇", 3000);

                            } else {
                                if (frequencyMap("bazhao", 1)) {
                                    poisiton++;
                                }
                            }
                        }

                    } else {
                        initOnceJudge("bsl");
                        mFairy.onTap(50, 37, 62, 47, "点击搜索", 1500);
                    }
                }
            }

            void content_02() throws Exception {
                FindResult hai = mFairy.findPic("hai.png");
                FindResult pac = mFairy.findPic("package.png");
                if (hai.sim < 0.8f && pac.sim > 0.8f) {//在主城
                    if (gamePublicFuntion.judgeStop(5)) {
                        if (frequencyMap("stop", 2)) {
                            LtLog.e(mFairy.getLineInfo("到达目的地"));
                            gamePublicFuntion.sildeArchitecture("jys.png");
                            banv = false;
                            setTaskName(3);
                            return;

                        }

                    } else {
                        frequencyInit("stop");
                        err = 0;
                    }
                }

                Thread.sleep(1000);

                timeCount(10, 0);
            }

            void content_03() throws Exception {

                if (banv) {
                    gamePublicFuntion.init(false);
                    gamePublicFuntion.sildeArchitecture("jiuba.png", "xiuxi.png");
                    setTaskName(4);
                    count = 1;
                    songli = false;
                    initOnceJudge("paihang");
                    return;
                }

                gamePublicFuntion.chat();

                //if (AtFairyConfig.getOption("banvmm").equals("1")) {

                result = mFairy.findPic(1023, 399, 1208, 683, "jy2.png");
                mFairy.onTap(0.8f, result, "卖出", 1000);

                result = mFairy.findPic("jys2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    FindResult w = mFairy.findPic(878, 592, 1259, 694, "ps8.png");
                    if (w.sim > 0.8f) {

                        result = mFairy.findPic("ps9.png");
                        mFairy.onTap(0.8f, result, "全卖", 2000);

                        mFairy.onTap(0.8f, w, "", 500);
                        mFairy.onTap(0.8f, w, "确定卖出", 1500);

                        result = mFairy.findPic("bj1.png");
                        mFairy.onTap(0.8f, result, 53, 112, 81, 126, "", 2000);

                    }

                    FindResult m = mFairy.findPic(878, 592, 1259, 694, "ps6.png");
                    if (m.sim > 0.8f) {

                        if (poisiton >= 12) {
                            banv = true;
                            return;
                        }

                        mFairy.onTap(188, 674, 212, 685, "最大", 1000);

                        for (int i = 0; i < l.size(); i++) {

                            result = mFairy.findPic(657, 141, 1000, 558, "ps7.png");
                            mFairy.onTap(0.8f, result, 880, 684, 894, 692, "", 1000);

                            mFairy.onTap(l.get(i)[0], l.get(i)[1], l.get(i)[0] + 1, l.get(i)[1] + 1, "点击第" + (i + 1) + "个", 1000);
                        }

                        mFairy.onTap(0.8f, m, "", 1000);
                        mFairy.onTap(0.8f, m, "", 1000);
                        mFairy.onTap(0.8f, m, "确定买入", 1000);
                        banv = true;
                    }
                }
               /* } else {
                    banv=true;
                }*/


                timeCount(10, 0);
            }

            void content_04() throws Exception {

                result = mFairy.findPic("banvs3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    LtLog.e(mFairy.getLineInfo("排行界面"));

                    result = mFairy.findPic("banvs4.png");
                    if (result.sim > 0.8f) {
                        songli = true;
                    } else {
                        mFairy.onTap(1000, 632, 1008, 641, "", 500);
                    }

                    mFairy.onTap(1161, 187, 1173, 205, "", 1000);
                }


                result = mFairy.findPic(new String[]{"jiuba1.png", "banv11.png", "xiuxi2.png"});
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(965, 507, 1244, 696, "banv13.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1106, 659, 1143, 672, "", 1000);
                        return;
                    }

                    if (onceJudge("paihang")) {
                        result = mFairy.findPic("banvs2.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "点击排行", 1000);
                            return;
                        }
                    }

                    switch (count) {
                        case 1:
                            Thread.sleep(1500);
                            result = mFairy.findPic(968, 315, 1232, 706, "banv1.png");
                            mFairy.onTap(0.8f, result, "请吧女喝一杯", 1500);
                            break;
                        case 2:
                            result = mFairy.findPic(968, 315, 1232, 706, "banv3.png");
                            mFairy.onTap(0.85f, result, "聊天", 1500);
                            mFairy.onTap(0.85f, result, "聊天", 1500);
                            break;
                        case 3:
                            if (songli || AtFairyConfig.getOption("hg2").equals("1")) {
                                result = mFairy.findPic(968, 315, 1232, 706, "banv5.png");
                                mFairy.onTap(0.85f, result, "讲故事", 6000);
                            }
                            break;
                        case 4:
                            if (song == false) {
                                mFairy.onTap(1230, 38, 1245, 55, "", 500);
                                poisiton++;
                                setTaskName(0);
                                return;
                            }

                            if (songli || AtFairyConfig.getOption("hg1").equals("1")) {
                                result = mFairy.findPic(968, 315, 1232, 706, "banv8.png");
                                mFairy.onTap(0.85f, result, "送礼物", 6000);
                            }

                            break;
                        default:
                            mFairy.onTap(1223, 38, 1239, 60, "", 500);
                            setTaskName(0);
                            return;
                    }

                    result = mFairy.findPic("banv2.png");
                    if (result.sim > 0.85f) {
                        frequencyInit("banvs");
                        mFairy.onTap(0.85f, result, "喝一杯", 1500);
                        return;
                    }

                    result = mFairy.findPic("banv4.png");
                    if (result.sim > 0.85f) {
                        frequencyInit("banvs");
                        mFairy.onTap(0.85f, result, "继续聊", 1000);
                        return;
                    }


                    result = mFairy.findPic("banv6.png");
                    if (result.sim > 0.85f) {
                        frequencyInit("banvs");
                        result = mFairy.findPic(932, 293, 1163, 412, "jiang.png");
                        if (result.sim > 0.8f) {
                            count = 4;
                            mFairy.onTap(1098, 659, 1128, 672, "", 3000);
                            return;
                        }
                        //mFairy.onTap(0.85f, result, 991, 136, 1044, 151, "", 500);
                        mFairy.onTap(1023, 297, 1047, 318, "", 500);
                        return;
                    }

                    result = mFairy.findPic("banv9.png");
                    if (result.sim > 0.85f) {
                        frequencyInit("banvs");
                        /*result = mFairy.findPic("banv10.png");
                        mFairy.onTap(0.85f, result, "显示已拥有", 1000);*/

                        int count = 0;
                        while (mFairy.condit()) {
                            LtLog.e(mFairy.getLineInfo("【寻找皮绳】"));

                            result = mFairy.findPic(968, 315, 1232, 706, "banv8.png");
                            if (result.sim > 0.8f) {
                                poisiton++;
                                setTaskName(0);
                                return;
                            }

                            result = mFairy.findPic("banvs1.png");
                            if (result.sim > 0.92f) {
                                poisiton++;
                                song = false;
                                setTaskName(0);
                                return;
                            }

                            result = mFairy.findPic("dazao.png");
                            if (result.sim > 0.8f) {
                                err = 0;
                                mFairy.onTap(1000, 648, 1039, 661, "制作", 500);
                                mFairy.onTap(1000, 648, 1039, 661, "制作", 500);
                                mFairy.onTap(1000, 648, 1039, 661, "制作", 500);

                                if (mapCount(0.8f, 500, 252, 712, 526, "shanghui7.png", "shanghui11.png")) {
                                    poisiton++;
                                    setTaskName(0);
                                    return;
                                }

                                mFairy.onTap(1230, 11, 1256, 34, "", 3000);
                                continue;
                            }

                            FindResult pi = mFairy.findPic(949, 163, 1240, 510, new String[]{"pi4.png", "pi1.png", "pi6.png", "pi7.png"});
                            if (pi.sim > 0.7f) {
                                mFairy.onTap(0.7f, pi, "点击皮绳", 1000);

                                mFairy.onTap(1084, 575, 1116, 586, "赠送", 700);

                                if (mapCount(0.8f, 608, 268, 685, 562, "pi5.png")) {
                                    mFairy.onTap(0.8f, pi, "皮绳不足", 2000);

                                    result = mFairy.findPic(688, 422, 880, 530, "pi3.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(0.8f, result, "获得途径", 1500);
                                        result = mFairy.findPic(826, 90, 1260, 487, "shanghui4.png");
                                        mFairy.onTap(0.8f, result, "生产", 5000);
                                        continue;
                                    }
                                }

                                count++;
                                if (count >= 3) {
                                    mFairy.onTap(1223, 35, 1240, 62, "", 500);
                                    poisiton++;
                                    setTaskName(0);
                                    return;
                                }
                                continue;
                            }


                            if (frequencyMap("p", 1)) {
                                mFairy.ranSwipe(1099, 400, 1104, 250, 500, 1000);
                            }

                            if (frequencyMap("pi", 8)) {
                                gamePublicFuntion.init(false);
                                setTaskName(2);
                                return;
                            }
                        }
                    }
                    count++;
                }

                result = mFairy.findPic("banv7.png");
                mFairy.onTap(0.8f, result, "讲", 2000);

                timeCount(10, 0);
            }
        };
    }//吧女送礼s

    public void shangtuan() throws Exception {
        new single(mFairy, "贸易商团") {

            void create() throws Exception {
                super.create();
                TaskMain.XUNHANG = false;
            }

            boolean activityEnd() throws Exception {
                if (mapCount(0.8f, 426, 260, 599, 532, "myst7.png")) {
                    TaskMain.SHANGHUI = false;
                    return true;
                }
                return false;
            }

            void content_01() throws Exception {
                activity(1, "myst.png");
            }

            void content_02() throws Exception {

                gamePublicFuntion.jygm();

                gamePublicFuntion.chat();

                result = mFairy.findPic("myst5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1227, 37, 1246, 58, "", 500);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("myst1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("myst3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "执行任务", 1000);
                        return;
                    }

                    result = mFairy.findPic("myst6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "建立普通商船", 1000);
                        return;
                    }

                    result = mFairy.findPic(272, 213, 1256, 714, "myst2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "加入", 1000);
                        if (frequencyMap("jiaru", 5)) {
                            mFairy.onTap(48, 201, 130, 227, "建立商团", 1000);
                        }
                        return;
                    }

                    if (frequencyMap("shangtuan", 1)) {
                        mFairy.onTap(48, 201, 130, 227, "建立商团", 1000);
                    }
                }

                if (gamePublicFuntion.taskClick("myst4.png")) {
                    setTaskName(0);
                    return;
                }

                timeCount(8, 0);
            }
        };
    }//贸易商团

    public void slsbw() throws Exception {
        new single(mFairy, "色雷斯捕网") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
                TaskMain.XUNHANG = false;
            }

            void content_01() throws Exception {

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, "背包", 2500);

                result = mFairy.findPic("packageScene.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (onceJudge("pack")) {
                        mFairy.onTap(55, 118, 78, 129, "背包", 500);
                        mFairy.onTap(802, 86, 822, 97, "综合", 1000);
                    }

                    result = mFairy.findPic(407, 376, 800, 647, "song2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "使用", 2000);
                        mFairy.initMatTime();
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic(749, 125, 1272, 693, "slsbw.png");
                    if (result.sim > 0.8f) {
                        frequencyInit("song");
                        frequencyInit("songEnd");
                        mFairy.onTap(0.8f, result, "点击网", 2000);
                    } else {
                        if (frequencyMap("song", 2)) {
                            if (frequencyMap("songEnd", 1)) {
                                LtLog.e(mFairy.getLineInfo("没有找到传送卷轴"));
                                setTaskEnd();
                                return;
                            }
                            mFairy.ranSwipe(1021, 541, 1021, 154, 500, 1000);
                        }
                    }
                }
                timeCount(10, 0);
            }

            public void closeUseOrUser() throws Exception {
                result = mFairy.findPic(892, 481, 1051, 602, "use.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "use", 5000);
                    mFairy.initMatTime();
                }
            }

            void content_02() throws Exception {

                result = mFairy.findPic(566, 480, 654, 533, "slsbw1.png");
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    err = 0;
                }

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (gamePublicFuntion.judgeStop(5)) {
                        mFairy.touchDown(299, 633);
                        Thread.sleep(2000);
                        mFairy.touchUp();
                        mFairy.initMatTime();
                        setTaskName(0);
                        return;
                    }
                }

                timeCount(10, 0);
            }
        };
    }//色雷斯捕网

    public void qd() throws Exception {
        new single(mFairy, "签到") {

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
                TaskMain.XUNHANG = false;
            }

            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic("zoom3.png");
                mFairy.onTap(0.8f, result, "zoom", 500);
            }

            void content_01() throws Exception {

                result = mFairy.findPic(89, 226, 312, 514, "qd1.png");
                mFairy.onTap(0.8f, result, "奖励", 1000);

                result = mFairy.findPic(148, 103, 337, 598, "qd9.png");
                mFairy.onTap(0.8f, result, "每日签到", 1000);

                result = mFairy.findPic("qd3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("qd2.png");
                    mFairy.onTap(0.8f, result, "签", 500);
                    mFairy.onTap(0.8f, result, "签", 500);
                    mFairy.onTap(0.8f, result, "签", 1000);

                    if (frequencyMap("qd", 1)) {
                        for (int i = 0; i < 2; i++) {
                            mFairy.onTap(573, 606, 589, 621, "", 500);
                            mFairy.onTap(770, 601, 795, 618, "", 500);
                            mFairy.onTap(573, 606, 589, 621, "", 500);
                            mFairy.onTap(770, 601, 795, 618, "", 500);
                        }

                        mFairy.onTap(239, 643, 255, 649, "", 500);

                        //mFairy.onTap(1117, 58, 1132, 84, "", 500);
                        setTaskName(2);
                        return;
                    }
                }

                timeCount(5, 0);
            }


            void content_02() throws Exception {
                result = mFairy.findPic(89, 226, 312, 514, "qd1.png");
                mFairy.onTap(0.8f, result, "奖励", 1000);

                result = mFairy.findPic(148, 103, 337, 598, "yueka1.png");
                mFairy.onTap(0.8f, result, "月卡", 1000);

                result = mFairy.findPic(392, 128, 660, 388, "yueka2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("yueka.png");
                    mFairy.onTap(0.7f, result, "领取", 1500);

                    if (frequencyMap("yueka", 1)) {
                        mFairy.onTap(1117, 58, 1132, 84, "", 500);
                        setTaskName(3);
                        return;
                    }
                }

                timeCount(5, 0);
            }

            void content_03() throws Exception {

                result = mFairy.findPic("qd4.png");
                mFairy.onTap(0.8f, result, "海市", 1000);

                result = mFairy.findPic("gmScene.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    Thread.sleep(3000);

                    result = mFairy.findPic(21, 64, 124, 645, "qd5.png");
                    mFairy.onTap(0.8f, result, "商城", 7000);

                    result = mFairy.findPic(167, 41, 1122, 124, "qd6.png");
                    mFairy.onTap(0.8f, result, "每周限购", 3000);

                    result = mFairy.findPic(178, 115, 905, 694, "qd7.png");
                    mFairy.onTap(0.8f, result, "祝福运势", 1000);

                    result = mFairy.findPic(879, 82, 1095, 215, "qd8.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1073, 651, 1107, 668, "", 500);
                        mFairy.onTap(1073, 651, 1107, 668, "", 500);
                        mFairy.onTap(1234, 17, 1250, 33, "", 1000);
                        setTaskEnd();
                        return;
                    }
                }

                timeCount(5, 0);
            }
        };
    }//签到

    public void zb() throws Exception {
        new single(mFairy, "免费占卜") {

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
                TaskMain.XUNHANG = false;
            }

            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic("zoom3.png");
                mFairy.onTap(0.8f, result, "zoom", 500);
            }

            void content_01() throws Exception {

                result = mFairy.findPic(89, 226, 312, 514, "zb1.png");
                mFairy.onTap(0.8f, result, "占卜", 1000);

                result = mFairy.findPic("zb5.png");
                mFairy.onTap(0.8f, result, "占卜成功", 1000);

                result = mFairy.findPic("zb2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(237, 326, 254, 360, "", 5000);
                }

                result = mFairy.findPic(260, 527, 563, 695, "zb3.png");
                if (result.sim > 0.8f) {

                    err = 0;

                    result = mFairy.findPic(281, 634, 594, 713, "zb4.png");
                    if (result.sim > 0.9f) {
                        mFairy.onTap(0.8f, result, 409, 622, 445, 636, "本次免费", 10000);
                        return;
                    }

                    if (frequencyMap("qd", 2)) {
                        mFairy.onTap(1202, 60, 1211, 73, "", 1500);
                        mFairy.onTap(1202, 60, 1211, 73, "", 500);
                        setTaskEnd();
                        return;
                    }
                }

                timeCount(5, 0);
            }

        };
    }//免费占卜

    public void jyzh() throws Exception {
        new single(mFairy, "经验找回") {

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
                TaskMain.XUNHANG = false;
            }

            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic("zoom3.png");
                mFairy.onTap(0.8f, result, "zoom", 500);
            }

            void content_01() throws Exception {

                result = mFairy.findPic(89, 226, 312, 514, "zh1.png");
                mFairy.onTap(0.8f, result, "找回", 1000);

                result = mFairy.findPic("zh2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    switch (AtFairyConfig.getOption("zh")) {
                        case "2":
                            mFairy.onTap(905, 619, 937, 633, "", 1000);
                            mFairy.onTap(905, 619, 937, 633, "钻石找回", 1000);
                            break;
                        default:
                            mFairy.onTap(617, 621, 669, 635, "", 1000);
                            mFairy.onTap(617, 621, 669, 635, "航海币找回", 1000);
                            break;
                    }

                    mFairy.onTap(1010, 87, 1023, 100, "", 1000);
                    setTaskEnd();
                    return;
                }

                timeCount(5, 99);
            }

        };
    }//经验找回

    public void xg() throws Exception {
        new single(mFairy, "限购") {

            int xg;

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
                TaskMain.XUNHANG = false;
                xg = 1;
            }

            void content_01() throws Exception {

                Thread.sleep(500);

                result = mFairy.findPic("qd4.png");
                mFairy.onTap(0.8f, result, "海市", 1000);

                result = mFairy.findPic("gmScene.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(532, 142, 693, 688, "xg4.png");
                    if (result.sim > 0.8f) {

                        if (onceJudge("xg")) {
                            xg = 1;
                            mFairy.ranSwipe(760, 274, 760, 500, 500, 1000);
                        }

                        boolean x = false;

                        switch (xg) {
                            case 1:
                                if (AtFairyConfig.getOption("xg1").equals("1")) {
                                    mFairy.onTap(664, 215, 697, 233, "巴库斯之水", 500);
                                    mFairy.onTap(664, 215, 697, 233, "", 1000);
                                    x = true;
                                } else {
                                    xg = 2;
                                }
                                break;
                            case 2:
                                if (AtFairyConfig.getOption("xg2").equals("1")) {
                                    mFairy.onTap(659, 339, 682, 356, "战斗天赋书", 500);
                                    mFairy.onTap(659, 339, 682, 356, "", 1000);
                                    mFairy.touchDown(1225, 469);
                                    Thread.sleep(3000);
                                    mFairy.touchUp();
                                    x = true;
                                } else {
                                    xg = 3;
                                }
                                break;
                            case 3:
                                if (AtFairyConfig.getOption("xg3").equals("1")) {
                                    mFairy.onTap(644, 452, 667, 469, "贸易天赋书", 500);
                                    mFairy.onTap(644, 452, 667, 469, "", 1000);
                                    mFairy.touchDown(1225, 469);
                                    Thread.sleep(3000);
                                    mFairy.touchUp();
                                    x = true;
                                } else {
                                    xg = 4;
                                }
                                break;
                            case 4:
                                if (AtFairyConfig.getOption("xg4").equals("1")) {
                                    mFairy.onTap(627, 569, 656, 584, "一捆资材", 500);
                                    mFairy.onTap(627, 569, 656, 584, "", 1000);
                                    for (int i1 = 0; i1 < 3; i1++) {
                                        mFairy.tap(1225, 469);
                                        Thread.sleep(100);
                                    }
                                    x = true;
                                } else {
                                    xg = 5;
                                }
                                break;
                            case 5:
                                if (AtFairyConfig.getOption("xg5").equals("1")) {
                                    mFairy.onTap(625, 667, 651, 678, "粗糙原石", 500);
                                    mFairy.onTap(625, 667, 651, 678, "", 1000);
                                    x = true;
                                } else {
                                    xg = 6;
                                }
                                break;
                            default:
                                mFairy.onTap(1228, 18, 1252, 32, "", 1000);
                                setTaskEnd();
                                return;
                        }

                        if (x) {
                            Thread.sleep(500);

                            gamePublicFuntion.gm();

                            Thread.sleep(1000);

                            result = mFairy.findPic("xg5.png");
                            if (result.sim > 0.92f) {
                                xg++;
                            }
                        }
                        return;

                    } else {
                        result = mFairy.findPic(21, 64, 124, 645, "xg1.png");
                        mFairy.onTap(0.9f, result, "海市", 1000);

                        result = mFairy.findPic(216, 133, 396, 652, "xg2.png");
                        mFairy.onTap(0.8f, result, "海市限购", 1200);

                        result = mFairy.findPic(216, 133, 396, 652, "xg3.png");
                        mFairy.onTap(0.8f, result, "每日限购", 3000);
                    }
                }


                timeCount(10, 0);
            }
        };
    }//限购

    public void shcj() throws Exception {
        new single(mFairy, "商会抽奖") {

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
                TaskMain.XUNHANG = false;
            }

            void content_01() throws Exception {

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic("shcj1.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        mFairy.onTap(0.8f, result, "商会", 2000);
                    } else {
                        mFairy.onTap(1221, 654, 1242, 673, "点击切换", 2000);
                    }
                }

                result = mFairy.findPic("shcj2.png");
                if (result.sim > 0.7f) {
                    err = 0;

                    result = mFairy.findPic(780, 502, 1268, 662, "shcj3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "商会战", 2000);
                    } else {
                        for (int i = 0; i < 3; i++) {
                            mFairy.ranSwipe(1159, 380, 709, 383, 500, 500);
                        }
                    }
                }

                result = mFairy.findPic("shcj4.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("sh2.png");
                    mFairy.onTap(0.9f, result, "每周奖励", 1000);

                    result = mFairy.findPic("shcj5.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(955,631,1019,645, "奖励兑换", 1000);

                        mFairy.onTap(1167, 266, 1179, 278, "最大", 1000);
                        mFairy.onTap(1125, 633, 1145, 640, "确定", 1000);

                        mFairy.onTap(1239, 22, 1252, 33, "", 500);
                        mFairy.onTap(1239, 22, 1252, 33, "", 1000);
                        setTaskEnd();
                        return;
                    }
                }

                timeCount(5, 0);
            }
        };
    }//商会抽奖

    public void hhj() throws Exception {
        new single(mFairy, "航海纪") {

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
                TaskMain.XUNHANG = false;
            }

            void content_01() throws Exception {

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(810, 466, 1272, 669, "hhj1.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        mFairy.onTap(0.8f, result, "航海纪", 3000);
                    } else {
                        mFairy.onTap(1221, 654, 1242, 673, "点击切换", 2000);
                    }
                }

                result = mFairy.findPic("hhj2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    Thread.sleep(1000);

                    mFairy.onTap(73, 270, 118, 285, "任务", 1500);

                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(500);
                        result = mFairy.findPic(1022, 170, 1184, 311, "jiangli2.png");
                        if (result.sim > 0.8f) {
                            i = 0;
                            mFairy.onTap(0.8f, result, "宝箱", 3000);
                        }
                    }

                    mFairy.onTap(85, 194, 109, 202, "奖励", 1500);
                    mFairy.onTap(563, 652, 597, 658, "一键领取", 1000);
                    mFairy.onTap(1214, 31, 1238, 50, "", 1000);
                    setTaskEnd();
                }


                timeCount(10, 99);
            }
        };
    }//航海纪

    public void qh() throws Exception {
        new TaskContent(mFairy, "切换角色") {

            void init() throws Exception {
                gamePublicFuntion.init(true);


                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1248, 17, 1265, 34, "点击设置", 1000);

                result = mFairy.findPic("set3.png");
                mFairy.onTap(0.8f, result, "基本设置", 1000);

                result = mFairy.findPic("set5.png");
                mFairy.onTap(0.8f, result, "返回登录", 10000);

                result = mFairy.findPic(739,386,920,476,"set6.png");
                mFairy.onTap(0.8f, result, "点击换区", 1000);

                result = mFairy.findPic(552, 409, 794, 481, "set8.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }


                result = mFairy.findPic(new String[]{"set7.png","set9.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "已有角色", 1500);

                    switch (TaskMain.qhList.get(0)) {
                        case 1:
                            mFairy.onTap(465, 237, 484, 256, "第1个角色", 500);
                            mFairy.onTap(465, 237, 484, 256, "第1个角色", 500);
                            mFairy.onTap(465, 237, 484, 256, "第1个角色", 10000);
                            break;
                        case 2:
                            mFairy.onTap(732, 228, 756, 263, "第2个角色", 500);
                            mFairy.onTap(732, 228, 756, 263, "第2个角色", 500);
                            mFairy.onTap(732, 228, 756, 263, "第2个角色", 10000);
                            break;
                        case 3:
                            mFairy.onTap(997, 227, 1024, 259, "第3个角色", 500);
                            mFairy.onTap(997, 227, 1024, 259, "第3个角色", 500);
                            mFairy.onTap(997, 227, 1024, 259, "第3个角色", 10000);
                            break;
                        case 4:
                            mFairy.onTap(457, 498, 483, 530, "第4个角色", 500);
                            mFairy.onTap(457, 498, 483, 530, "第4个角色", 500);
                            mFairy.onTap(457, 498, 483, 530, "第4个角色", 10000);
                            break;
                        case 5:
                            mFairy.onTap(728, 502, 746, 529, "第5个角色", 500);
                            mFairy.onTap(728, 502, 746, 529, "第5个角色", 500);
                            mFairy.onTap(728, 502, 746, 529, "第5个角色", 10000);
                            break;
                        case 6:
                            mFairy.onTap(984, 502, 1007, 523, "第6个角色", 500);
                            mFairy.onTap(984, 502, 1007, 523, "第6个角色", 500);
                            mFairy.onTap(984, 502, 1007, 523, "第6个角色", 10000);
                            break;
                    }

                    TaskMain.qhList.remove(0);
                    setTaskEnd();
                    return;
                }

                Thread.sleep(1000);
                timeCount(30, 0);
            }
        };
    }//切换角色

    /**
     * 无限任务
     */
    public static boolean xianshiJugde = true;

    public int gkwy = 0;

    public void zbtf() throws Exception {
        new single(mFairy, "追捕逃犯") {

            void create() throws Exception {
                super.create();
                sign = new ArrayList<>();
                tuidui();//退队
                xianshi();
                teamTask = new TeamTask(mFairy);
            }

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
            }

            public boolean xianshi() throws Exception {
                boolean xsBool = false;

                while (true) {

                    int week = mFairy.week();
                    int hour = mFairy.dateHour();
                    int minute = mFairy.dateMinute();

                    xianshiJugde = true;

                    if (hour == 18) {
                        initSignJudge("黄金航路");
                        initSignJudge("船长对决");
                    }

                    if (hour == 0 || hour == 24) {
                        if (signJudge("0点重置任务")) {
                            sign.clear();
                            setSign("0点重置任务");
                            return true;
                        }
                    } else {
                        initSignJudge("0点重置任务");
                    }

                    if (AtFairyConfig.getOption("8020").equals("1")) {//怒海争锋

                        long i = getTimeStamp(AtFairyConfig.getOption("nhzf"));

                        if (timeMap("nhzftime", i != -1 ? i : 1800000, true)) {
                            nhzf();
                            xsBool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("8018").equals("1") && signJudge("黄金航路")) {

                        if (hour == 12 || hour == 13 || hour == 19 || hour == 20) {

                            int[] time = getTime(AtFairyConfig.getOption("hjhl_time"));
                            if (time != null) {
                                if (hour > time[0] || (hour == time[0] && minute >= time[1])) {
                                    hjhl();
                                    setSign("黄金航路");
                                    xsBool = true;
                                    continue;
                                }
                            } else {
                                hjhl();
                                setSign("黄金航路");
                                xsBool = true;
                                continue;
                            }
                        }
                    }

                    if (AtFairyConfig.getOption("8016").equals("1") && signJudge("寒冰决战")) {
                        if (hour == 12 || hour == 13) {
                            hbjz();
                            setSign("寒冰决战");
                            xsBool = true;
                            continue;
                        }
                    }


                    if (AtFairyConfig.getOption("jmsr").equals("1") && signJudge("精明商人的挑战")) {

                        if (((week == 1 || week == 3 || week == 6) && hour >= 12)) {
                            jmsr();
                            setSign("精明商人的挑战");
                            xsBool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("hsqsbw").equals("1") && signJudge("海上骑士比武")) {
                        if (hour == 21) {
                            hsqsbw();
                            setSign("海上骑士比武");
                            xsBool = true;
                            continue;
                        }
                    }


                    if (AtFairyConfig.getOption("hayc").equals("1") && signJudge("海岸渔场")) {
                        if (hour == 19 || hour == 20) {
                            hayc();
                            tuidui();
                            setSign("海岸渔场");
                            xsBool = true;
                            continue;
                        }
                    }


                    if (AtFairyConfig.getOption("yljd").equals("1") && signJudge("幽灵舰队")) {

                        if (week >= 1 && week <= 5 && hour == 12 && minute >= 13 && minute < 45) {
                            yljd();
                            tuidui();
                            if (xianshiJugde) {
                                setSign("幽灵舰队");
                            }
                            xsBool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("shz").equals("1")) {

                        boolean hy = false;

                        if (week == 2 && hour == 20 && minute >= 20 && signJudge("shz周2_20点")) {
                            hy = true;
                            setSign("shz周2_20点");
                        }

                        if (week == 2 && hour == 21 && signJudge("shz周2_21点")) {
                            hy = true;
                            setSign("shz周2_21点");
                        }

                        if (week == 4 && hour == 21 && signJudge("shz周4_21点")) {
                            hy = true;
                            setSign("shz周4_21点");
                        }

                        if (hy) {
                            shz();
                            xsBool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("xyczb").equals("1") && signJudge("小渔船争霸")) {
                        if (week == 4 && (hour == 19 || hour == 20 || hour == 21 || hour == 22)) {
                            xyczb();
                            setSign("小渔船争霸");
                            xsBool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("hyzw").equals("1")) {//海域之王

                        if (week != 7) {

                            boolean hy = false;

                            if (hour == 18 && signJudge("海域之王18点")) {
                                hy = true;
                                setSign("海域之王18点");
                            }

                            if (hour == 20 && signJudge("海域之王20点")) {
                                hy = true;
                                setSign("海域之王20点");
                            }

                            if (hour == 22 && signJudge("海域之王22点")) {
                                hy = true;
                                setSign("海域之王22点");
                            }

                            if (hour == 23 && signJudge("海域之王23点")) {
                                hy = true;
                                setSign("海域之王23点");
                            }

                            if (hy) {
                                hyzw();
                                xsBool = true;
                                continue;
                            }
                        }
                    }


                    if (AtFairyConfig.getOption("hyzb").equals("1")) {//海域争霸

                        if (week == 7) {

                            boolean hy = false;

                            if (hour == 18 && signJudge("海域争霸18点")) {
                                hy = true;
                                setSign("海域争霸18点");
                            }

                            if (hour == 20 && signJudge("海域争霸20点")) {
                                hy = true;
                                setSign("海域争霸20点");
                            }

                            if (hour == 22 && signJudge("海域争霸22点")) {
                                hy = true;
                                setSign("海域争霸22点");
                            }

                            if (hour == 23 && signJudge("海域争霸23点")) {
                                hy = true;
                                setSign("海域争霸23点");
                            }

                            if (hy) {
                                hyzb();
                                xsBool = true;
                                continue;
                            }
                        }
                    }


                    if (AtFairyConfig.getOption("gkwy").equals("1") && gkwy < 2) {//港口威压

                        boolean hy = false;


                        if ((week == 2 || week == 4) && hour == 20 && AtFairyConfig.getOption("shz").equals("1")) {

                        } else {

                            if (hour >= 16 && hour <= 23) {

                                if (minute >= 3 && minute < 16) {
                                    if (signJudge("gk5分钟")) {
                                        hy = true;
                                        setSign("gk5分钟");
                                    }
                                } else {
                                    initSignJudge("gk5分钟");
                                }

                                if (minute >= 33 && minute < 46) {
                                    if (signJudge("gk35分钟")) {
                                        hy = true;
                                        setSign("gk35分钟");
                                    }
                                } else {
                                    initSignJudge("gk35分钟");
                                }

                                if (hy) {
                                    gkwy();
                                    LtLog.e(mFairy.getLineInfo("任务执行完毕"));
                                    xsBool = true;
                                    continue;
                                }
                            }
                        }
                    }

                    if (AtFairyConfig.getOption("czdj").equals("1") && signJudge("船长对决")) {

                        if (week == 7 && (hour == 12 || hour == 22)) {
                            czdj();
                            setSign("船长对决");
                            xsBool = true;
                            continue;
                        }
                    }


                    if (AtFairyConfig.getOption("klsg").equals("1") && signJudge("科林斯桂冠竞技")) {
                        if (week == 6 && hour == 21) {
                            klsg();
                            tuidui();
                            setSign("科林斯桂冠竞技");
                            xsBool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("shzg").equals("1") && signJudge("深海战歌")) {

                        if (week == 7 && hour == 21) {
                            shzg();
                            tuidui();
                            if (mFairy.dateHour() == 21) {
                                shzgling();
                            }
                            setSign("深海战歌");
                            xsBool = true;
                            continue;
                        }
                    }


                    if (AtFairyConfig.getOption("hxmc").equals("1") && signJudge("欢喜迷藏")) {

                        if (week == 6 && hour >= 8) {
                            hxmc();
                            setSign("欢喜迷藏");
                            xsBool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("hdw").equals("1") && signJudge("海盗王之怒")) {

                        if ((week == 5 || week == 3 || week == 1) && hour == 20 && minute >= 14 && minute < 50) {
                            hdw();
                            tuidui();
                            setSign("海盗王之怒");
                            xsBool = true;
                            continue;
                        }
                    }


                    if (AtFairyConfig.getOption("hjjdll").equals("1") && signJudge("皇家舰队历练")) {

                        if (week == 7 && hour >= 12 && hour < 21) {
                            hjjdll();
                            tuidui();
                            setSign("皇家舰队历练");
                            xsBool = true;
                            continue;
                        }
                    }


                    if (AtFairyConfig.getOption("8022").equals("1") && signJudge("利维坦的咆哮")) {

                        if (hour % 2 == 0 && minute >= 13 && minute < 45) {

                            if (!(hour == 4 || hour == 6 || (week >= 1 && week <= 5 && hour == 12) || (week == 2 && hour == 20))) {
                                lwtdpx();
                                tuidui();
                                if (xianshiJugde) {
                                    setSign("利维坦的咆哮");
                                }
                                xsBool = true;
                                continue;
                            }
                        }
                    }
                    return xsBool;
                }
            }

            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(532, 19, 1214, 102, new String[]{"qx.png", "qx1.png"});
                mFairy.onTap(0.8f, result, "取消匹配", 1000);

                if (xianshi()) {
                    LtLog.e(mFairy.getLineInfo("执行过限时任务,开始初始化操作"));

                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("gkwy7.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(229, 301, 274, 356, "", 1000);
                    mFairy.onTap(621, 623, 653, 637, "", 1500);
                }
            }

            void content_01() throws Exception {

                result = mFairy.findPic(939, 40, 1269, 224, new String[]{"zbtf1.png", "zbtf2.png"});
                mFairy.onTap(0.7f, result, "特", 1000);

                result = mFairy.findPic("tx.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(320, 548, 337, 560, "立即前往", 3000);
                    setTaskName(2);
                    return;
                }

                timeCount(5, 0);
            }

            void content_02() throws Exception {
                result = mFairy.findPic("zbtf5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1222, 665, 1235, 682, "", 500);
                    setTaskName(1);
                    return;
                } else {
                    if (frequencyMap("chat", 1)) {
                        gamePublicFuntion.chat();
                    }
                }

                result = mFairy.findPic(537, 96, 731, 152, new String[]{"zbtf3.png", "zbtf4.png"});
                if (result.sim > 0.7f) {
                    err = 0;
                }

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(84, 9, 311, 68, "pilao.png");
                    int pilao = mFairy.getColorNum(result.x + 137, result.y + 18, result.x + 152, result.y + 22, "230,114,24", 0.9f);
                    LtLog.e(mFairy.getLineInfo("【疲劳值：" + (result.x + 137) + "," + (result.y + 18) + "," + (result.x + 152) + "," + (result.y + 22) + "  " + pilao + "】"));
                    if (pilao > 20) {
                        rny("rny1.png", "rny3.png");//飞热亚
                        setTaskName(0);
                        return;
                    }
                }
                Thread.sleep(1000);

                timeCount(15, 0);
            }
        };
    }//追捕逃犯

    public void nhzf() throws Exception {
        new single(mFairy, "怒海争锋") {

            void content_01() throws Exception {
                activity(1, "nhzf.png");
                frequencyInit("nhzfsilde");
                frequencyInit("nhzfsilde_err");
            }

            void content_02() throws Exception {

                result = mFairy.findPic("nhzf7.png");
                mFairy.onTap(0.8f, result, "单人进入", 1000);

                result = mFairy.findPic("nhzf1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(602, 267, 990, 497, "nhzf2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "建设", 2000);
                    } else {
                        mFairy.onTap(1241, 140, 1254, 154, "", 1000);
                        mFairy.onTap(625, 341, 637, 353, "主城", 1500);
                    }
                }


                result = mFairy.findPic("nhzf3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    List<FindResult> listResult = mFairy.findPic(8, 62, 758, 709, 0.75f, "nhzf4.png");
                    for (int i = 0; i < listResult.size(); i++) {
                        mFairy.onTap(0.8f, listResult.get(i), listResult.get(i).x, listResult.get(i).y, listResult.get(i).x + 1, listResult.get(i).y + 1, "绿箭头", 1500);

                        result = mFairy.findPic("nhzf5.png");
                        mFairy.onTap(0.8f, result, "升级", 2000);

                            /*if (mFairy.getColorNum(1186, 545, 1227, 560, "223,11,14", 0.9f) > 12) {
                                mFairy.onTap(1232, 15, 1247, 33, "", 1000);
                                mFairy.onTap(1232, 15, 1247, 33, "", 1000);
                            }*/

                    }

                    result = mFairy.findPic("nhzf8.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1232, 15, 1247, 33, "", 500);
                        mFairy.onTap(1232, 15, 1247, 33, "", 1000);
                        setTaskEnd();
                        return;
                    }

                    if (frequencyMap("nhzfsilde", 1)) {
                        mFairy.ranSwipe(395, 600, 400, 250, 300, 1000);
                    }

                    if (frequencyMap("nhzfsilde_err", 5)) {
                        mFairy.onTap(1232, 15, 1247, 33, "", 500);
                        mFairy.onTap(1232, 15, 1247, 33, "", 1000);
                        setTaskEnd();
                        return;
                    }

                   /* result = mFairy.findPic(8, 62, 758, 709, "nhzf4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "绿箭头", 1000);
                        return;
                    }*/


                }
            }
        };
    }//怒海争锋

    public void hjhl() throws Exception {
        new single(mFairy, "黄金航路") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
            }

            boolean cancel() throws Exception {

                result = mFairy.findPic("hjhl4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(737, 469, 776, 482, "确定领取", 1000);
                    return true;
                }

                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();

                if (mFairy.dateHour() == 14 || mFairy.dateHour() == 21) {
                    setTaskEnd();
                    return;
                }
            }

            void content_01() throws Exception {
                activity(4, "hjhl.png");
            }

            void content_02() throws Exception {

                result = mFairy.findPic("hjhl1.png");
                mFairy.onTap(0.8f, result, "前往热那亚", 1500);

                if (frequencyMap("chat", 1)) {
                    gamePublicFuntion.chat();
                }

                result = mFairy.findPic(956, 363, 1254, 632, new String[]{"hjhl2.png", "xljd4.png"});
                mFairy.onTap(0.8f, result, "黄金航路", 1000);

                result = mFairy.findPic("hjhl3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    switch (AtFairyConfig.getOption("hjhl")) {
                        case "2":
                            mFairy.onTap(842, 584, 926, 595, "高级护送", 1000);
                            break;
                        default:
                            mFairy.onTap(359, 579, 417, 598, "普通护送", 1000);
                            break;
                    }
                }

                result = mFairy.findPic("hjhl7.png");
                mFairy.onTap(0.8f, result, "领取奖励", 3000);

                result = mFairy.findPic("hjhl8.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1113, 66, 1125, 81, "", 500);
                    setTaskName(1);
                    return;
                }

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(1034, 264, 1132, 471, "hjhl5.png");
                    if (result.sim > 0.75f) {
                        err = 0;
                        frequencyInit("huang");


                        result = mFairy.findPic(1087, 300, 1209, 418, new String[]{"hjhl6.png", "jiangli.png"});
                        mFairy.onTap(0.75f, result, "领取奖励", 1000);

                        return;
                    }

                    if (frequencyMap("huang", 2)) {
                        mFairy.ranSwipe(1157, 344, 1158, 486, 500, 1000);
                    }
                }

                timeCount(10, 0);
            }
        };
    }//黄金航路

    public void hbjz() throws Exception {
        new single(mFairy, "寒冰诀战") {

            int endCount;

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
            }

            boolean activityJuage(FindResult act) throws Exception {
                if (!AtFairyConfig.getOption("hbjz").equals("2")) {
                    result = mFairy.findPic(act.x + 111, act.y + 4, act.x + 260, act.y + 87, "hbjz7.png");
                    if (result.sim > 0.88f) {
                        LtLog.e(mFairy.getLineInfo("次数打完"));
                        setTaskEnd();
                        return true;
                    }
                }
                return false;
            }

            void content_01() throws Exception {
                activity(3, "hbjz.png");
            }

            void inOperation() throws Exception {
                super.inOperation();

                if (mFairy.dateHour() == 14) {
                    setTaskEnd();
                    return;
                }

            }

            void content_02() throws Exception {
                FindResult pipei = rangeFindPic(50,"hbjz1.png");
                if (pipei.sim > 0.7f) {

                    if (AtFairyConfig.getOption("7932").equals("1") && signJudge("黄金航路")) {
                        int[] time = getTime(AtFairyConfig.getOption("hjhl_time"));
                        if (time != null) {
                            if (mFairy.dateHour() == time[0] && mFairy.dateMinute() >= time[1]) {
                                hjhl();
                                setSign("黄金航路");
                                setTaskName(0);
                                return;
                            }
                        }
                    }


                    mFairy.onTap(0.7f, pipei, "自动匹配", 500);
                    if (mapCount(0.7f, 429, 205, 650, 535, "hbjz6.png")) {
                        mFairy.onTap(1234, 18, 1252, 25, "", 500);
                        setTaskEnd();
                        return;
                    }
                }
                if (frequencyMap("chat", 1)) {
                    gamePublicFuntion.chat();
                }

                result = rangeFindPic(50,"hbjz3.png");
                mFairy.onTap(0.7f, result, 770, 352, 782, 361, "", 5000);

                result = mFairy.findPic(457, 535, 764, 586, "hbjz2.png");
                if (result.sim > 0.7f) {
                    err = 0;

                    while (mFairy.condit()) {
                        LtLog.e(mFairy.getLineInfo("【匹配中】"));

                        result = mFairy.findPic(457, 535, 764, 586, "hbjz2.png");
                        if (result.sim < 0.7f) {
                            Thread.sleep(20000);
                            break;
                        }
                    }
                }


                result = rangeFindPic(20,"hbjz4.png");
                if (result.sim > 0.7f) {
                    err = 0;

                    if (timeMap("zou", 5000, true)) {
                        switch (new Random().nextInt(2)) {
                            case 0:
                                mFairy.touchDown(297, 635);
                                Thread.sleep(2000);
                                mFairy.touchUp();
                                break;
                            default:
                                mFairy.touchDown(86, 641);
                                Thread.sleep(2000);
                                mFairy.touchUp();
                                break;
                        }
                    }
                }

                result = rangeFindPic(50,"hbjz5.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    mFairy.onTap(0.7f, result, 1150, 15, 1172, 34, "获取奖励", 2000);

                    if (!AtFairyConfig.getOption("hbjz").equals("2")) {
                        endCount++;
                        if (endCount >= 3) {
                            setTaskEnd();
                            return;
                        }
                    }
                }

                timeCount(15, 0);
            }
        };
    }//寒冰诀战 ***

    public void hsqsbw() throws Exception {
        new single(mFairy, "海上骑士比武") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
            }

            void content_01() throws Exception {
                activity(4, "hsqsbw.png");
            }

            void inOperation() throws Exception {
                super.inOperation();

                if (mFairy.dateHour() == 22) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {

                result = rangeFindPic(50,"hsqsbw1.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    result = rangeFindPic(2,"hsqsbw4.png");
                    if (result.sim > 0.92f) {
                        LtLog.e(mFairy.getLineInfo("次数已满"));
                        setTaskEnd();
                        return;
                    }

                    result = rangeFindPic(50,"hsqsbw2.png");
                    mFairy.onTap(0.7f, result, "开始匹配", 1000);
                }

                result = rangeFindPic(50,"hsqsbw3.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    Thread.sleep(5000);
                }

                timeCount(15, 0);
            }
        };
    }//海上骑士比武

    public void hyzw() throws Exception {
        new single(mFairy, "海域之王") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
            }

            void content_01() throws Exception {
                activity(4, "hyzw.png");
            }

            boolean cancel() throws Exception {

                result = mFairy.findPic(404, 205, 864, 413, "hyzw5.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(727, 456, 755, 463, "前往助威", 3000);
                    return true;
                }

                return false;
            }

            void content_02() throws Exception {

                result = mFairy.findPic(147, 244, 1121, 381, "hyzw7.png");
                if (result.sim > 0.7f) {
                    Thread.sleep(3000);
                    result = mFairy.findPic(147, 244, 1121, 381, "hyzw7.png");
                    if (result.sim > 0.7f) {
                        mFairy.onTap(0.7f, result, "点击助威", 1000);

                        mFairy.onTap(1117, 62, 1128, 78, "", 1500);
                        mFairy.onTap(1231, 13, 1247, 24, "", 1000);
                        setTaskEnd();
                    }
                    return;
                }

                result = mFairy.findPic("hyzw1.png");
                if (result.sim > 0.75f) {

                    result = mFairy.findPic(453, 380, 781, 534, "hyzw8.png");
                    if (result.sim > 0.8f) {
                        err = 0;

                        frequencyInit("herr");

                        if (timeMap("hyTime", 50000, false)) {
                            mFairy.onTap(1075, 373, 1095, 388, "刷新", 5000);
                            return;
                        }

                        result = mFairy.findPic(434,358,828,601,"hyzw9.png");
                        if (result.sim > 0.7f) {
                            frequencyInit("hyzwErr");
                            return;
                        }

                        result = mFairy.findPic(434,358,828,601,"hyzw6.png");
                        if (result.sim > 0.7f) {

                            frequencyInit("hyzwErr");
                            mFairy.onTap(0.8f, result, "助威", 1000);
                            return;
                        }

                        result = mFairy.findPic(434,358,828,601,"hyzw4.png");
                        if (result.sim > 0.7f) {
                            frequencyInit("hyzwErr");
                            mFairy.onTap(0.8f, result, "报名", 1000);
                            return;
                        }


                        result = mFairy.findPic(434,358,828,601,"hyzw10.png");
                        if (result.sim > 0.7f) {
                            frequencyInit("hyzwErr");
                            mFairy.onTap(0.8f, result, "准备战斗", 5000);
                            return;
                        }

                        if (frequencyMap("hyzwErr", 2)) {
                            mFairy.onTap(1232, 19, 1251, 34, "", 1000);
                            setTaskEnd();
                            return;
                        }

                    } else {
                        result = mFairy.findPic(98, 614, 1275, 712, "hyzw2.png");
                        mFairy.onTap(0.7f, result, result.x - 100, result.y + 5, result.x - 50, result.y + 10, "进行中", 5000);
                    }


                    if (frequencyMap("herr", 5)) {
                        setTaskEnd();
                        return;
                    }

                }

                result = rangeFindPic(50,"hyzw11.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    Thread.sleep(5000);
                }


                result = mFairy.findPic(954, 149, 1094, 274, "hyzw13.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    Thread.sleep(2000);
                }

                result = rangeFindPic(50,"hyzw12.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.7f, result, "海域之王结束", 1000);
                    setTaskEnd();
                    return;
                }

                timeCount(15, 0);
            }
        };
    }//海域之王

    public void hyzb() throws Exception {
        new single(mFairy, "海域争霸") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
            }

            void content_01() throws Exception {
                activity(4, "hyzb.png");
            }

            boolean cancel() throws Exception {

                result = mFairy.findPic(404, 205, 864, 413, "hyzw5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(727, 456, 755, 463, "前往助威", 3000);
                    return true;
                }
                return false;
            }

            void content_02() throws Exception {

                result = mFairy.findPic(147, 244, 1121, 381, "hyzw7.png");
                if (result.sim > 0.7f) {
                    Thread.sleep(3000);
                    result = mFairy.findPic(147, 244, 1121, 381, "hyzw7.png");
                    if (result.sim > 0.7f) {
                        mFairy.onTap(0.7f, result, "点击助威", 1000);

                        mFairy.onTap(1117, 62, 1128, 78, "", 1500);
                        mFairy.onTap(1231, 13, 1247, 24, "", 1000);
                        setTaskEnd();
                    }
                    return;
                }

                result = rangeFindPic(50,"hyzb2.png");
                if (result.sim > 0.75f) {

                    result = mFairy.findPic(453, 380, 781, 534, "hyzw8.png");
                    if (result.sim > 0.75f) {
                        err = 0;

                        if (timeMap("hyTime", 20000, false)) {
                            mFairy.onTap(1075, 373, 1095, 388, "刷新", 5000);
                            return;
                        }

                        result = mFairy.findPic(434,358,828,601,"hyzw9.png");
                        if (result.sim > 0.7f) {
                            frequencyInit("hyzwErr");
                            return;
                        }

                        result = mFairy.findPic(434,358,828,601,"hyzw6.png");
                        if (result.sim > 0.7f) {

                            frequencyInit("hyzwErr");
                            mFairy.onTap(0.7f, result, "助威", 1000);
                            return;
                        }
                        result = mFairy.findPic(434,358,828,601,"hyzw4.png");
                        if (result.sim > 0.7f) {
                            frequencyInit("hyzwErr");
                            mFairy.onTap(0.7f, result, "报名", 1000);
                            return;
                        }

                        result = mFairy.findPic(434,358,828,601,"hyzw10.png");
                        if (result.sim > 0.7f) {
                            frequencyInit("hyzwErr");
                            mFairy.onTap(0.7f, result, "准备战斗", 5000);
                            return;
                        }

                        if (frequencyMap("hyzwErr", 2)) {
                            mFairy.onTap(1232, 19, 1251, 34, "", 1000);
                            setTaskEnd();
                            return;
                        }

                    } else {
                        result = mFairy.findPic(98, 614, 1275, 712, "hyzw2.png");
                        mFairy.onTap(0.75f, result, result.x - 100, result.y + 5, result.x - 50, result.y + 10, "进行中", 5000);
                    }
                }

                result = rangeFindPic(50,"hyzw11.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    Thread.sleep(5000);
                }


                result = mFairy.findPic(954, 149, 1094, 274, "hyzw13.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    Thread.sleep(2000);
                }

                result = rangeFindPic(50,"hyzw12.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.7f, result, "海域之王结束", 1000);
                    setTaskEnd();
                    return;
                }

                timeCount(15, 0);
            }
        };
    }//海域争霸****

    public void xyczb() throws Exception {
        new single(mFairy, "小渔船争霸") {

            int endCount;

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
                endCount = 0;
            }

            boolean activityJuage(FindResult act) throws Exception {
                result = mFairy.findPic(act.x + 111, act.y + 4, act.x + 260, act.y + 87, "hxmc6.png");
                if (result.sim > 0.88f) {
                    LtLog.e(mFairy.getLineInfo("次数打完"));
                    setTaskEnd();
                    return true;
                }
                return false;
            }

            boolean activityEnd() throws Exception {
                if (mapCount(0.7f, 503, 165, 675, 460, "xyczb5.png")) {
                    return true;
                }
                return false;
            }

            void content_01() throws Exception {
                activity(4, "xyczb.png");
            }

            void inOperation() throws Exception {
                super.inOperation();

                int hour = mFairy.dateHour();

                if (hour == 23) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {

                result = rangeFindPic(50,"xyczb1.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    if (endCount >= 3) {
                        mFairy.onTap(1230, 17, 1247, 29, "", 1000);
                        setTaskName(0);
                        return;
                    }

                    result = rangeFindPic(50,"xyczb2.png");
                    mFairy.onTap(0.7f, result, "开始匹配", 1000);

                    initOnceJudge("记录进入副本次数");
                }

                result = mFairy.findPic(950, 137, 1165, 320, "xyczb6.png");
                if (result.sim > 0.75f) {
                    err = 0;
                }

                result = rangeFindPic(50,"xyczb4.png");
                if (result.sim > 0.7f) {

                    result = rangeFindPic(50,"xyczb7.png");
                    if (result.sim > 0.7f) {
                        if (onceJudge("记录进入副本次数")) {
                            endCount++;
                        }
                    }

                    mFairy.onTap(628, 673, 659, 686, "点击返回", 1000);
                }

                result =rangeFindPic(50,"xyczb3.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    Thread.sleep(5000);
                }

                timeCount(15, 0);
            }
        };
    }//小渔船争霸

    public void hxmc() throws Exception {
        new single(mFairy, "欢喜迷藏") {

            int end;

            void create() throws Exception {
                super.create();
                end = 0;
                rny("rny1.png", "rny3.png");//先回热亚
            }

            boolean activityJuage(FindResult act) throws Exception {
                result = mFairy.findPic(act.x + 111, act.y + 4, act.x + 260, act.y + 87, "hxmc6.png");
                if (result.sim > 0.88f) {
                    LtLog.e(mFairy.getLineInfo("次数打完"));
                    setTaskEnd();
                    return true;
                }
                return false;
            }

            void content_01() throws Exception {
                activity(4, "hxmc.png");
            }

            void inOperation() throws Exception {
                super.inOperation();

                if (mFairy.dateHour() == 24 || mFairy.dateHour() == 0) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {

                result = rangeFindPic(50,"hxmc5.png");
                if (result.sim > 0.7f) {
                    err = 0;

                    if (end >= 3) {
                        setTaskEnd();
                        return;
                    }

                    initOnceJudge("hxone");

                    result = rangeFindPic(50,"hxmc1.png");
                    mFairy.onTap(0.7f, result, "开始匹配", 1000);
                }

                result = rangeFindPic(50,"hxmc2.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    mFairy.onTap(815, 583, 825, 592, "不在提示", 500);
                    mFairy.onTap(933, 121, 945, 131, "", 1000);
                }

                result = rangeFindPic(20,"hxmc3.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    if (onceJudge("hxone")) {
                        end++;
                    }
                    Thread.sleep(5000);
                }

                result = mFairy.findPic(537, 255, 746, 364, "hxmc4.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(1223, 655, 1241, 672, "", 1000);
                    setTaskName(0);
                    return;
                }

                timeCount(15, 0);
            }
        };
    }//欢喜迷藏****

    public void shz1() throws Exception {
        new single(mFairy, "商会战") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
            }

            boolean activityEnd() throws Exception {
                if (mapCount(0.8f, 426, 260, 599, 532, "myst7.png")) {
                    TaskMain.SHANGHUI = false;
                    return true;
                }
                return false;
            }

            void content_01() throws Exception {
                activity(4, "shz.png");
            }

            void content_02() throws Exception {

                result = rangeFindPic(50,"shz1.png");
                if (result.sim > 0.7f) {
                    err = 0;

                    result = rangeFindPic(50,"shz2.png");
                    if (result.sim > 0.7f) {
                        frequencyInit("shz");
                        mFairy.onTap(0.7f, result, "进入战场", 3000);
                    } else {
                        Thread.sleep(1000);
                    }

                    if (frequencyMap("shz", 5)) {
                        mFairy.onTap(1240, 13, 1251, 26, "", 500);
                        setTaskEnd();
                        return;
                    }
                }

                result = rangeFindPic(50,"shz3.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    Thread.sleep(5000);
                }

                result = rangeFindPic(50,"shz4.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(1227, 655, 1237, 667, "", 500);
                    setTaskEnd();
                    return;
                }


                timeCount(10, 0);
            }
        };
    }//shanghuizhan****

    public void gkwy() throws Exception {
        new single(mFairy, "港口威压") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
                initOnceJudge("fubengk");
            }

            void shibai() throws Exception {
                if (gamePublicFuntion.shibai()) {
                    setTaskEnd();
                    return;
                }
            }

            boolean activityEnd() throws Exception {
                if (mapCount(0.8f, 426, 260, 599, 532, "myst7.png")) {
                    TaskMain.SHANGHUI = false;
                    return true;
                }
                return false;
            }

            void init() throws Exception {
                int m = mFairy.dateMinute();
                if ((m >= 5 && m <= 17) || (m >= 35 && m <= 47)) {
                    super.init();
                } else {
                    LtLog.e(mFairy.getLineInfo("还没到活动时间，等待中"));
                }
            }

            void content_01() throws Exception {
                activity(3, "gkwy.png");
            }

            boolean cancel() throws Exception {

                result = mFairy.findPic(383, 267, 586, 357, "gkwy6.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(737, 468, 779, 487, "进入等待场景", 10000);
                    return true;
                }

                result = mFairy.findPic(343, 230, 714, 428, "gkwy11.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(502, 465, 536, 482, "没有选上", 3000);
                    setTaskEnd();
                    return true;
                }

                return false;
            }

            void content_02() throws Exception {

                result = mFairy.findPic("gkwy2.png");
                mFairy.onTap(0.8f, result, "今日赛程", 1000);

                result = mFairy.findPic("gkwy1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    FindResult bm = mFairy.findPic("gkwy3.png");
                    if (bm.sim > 0.8f) {

                        result = mFairy.findPic("gkwy8.png");
                        if (result.sim > 0.92f) {
                            gkwy = 3;
                            mFairy.onTap(1218, 42, 1230, 56, "", 1000);
                            mFairy.onTap(1218, 42, 1230, 56, "", 1000);
                            setTaskEnd();
                            return;
                        }

                        if (timeMap("bm", 30000, true)) {
                            for (int i = 0; i < 2; i++) {
                                mFairy.onTap(0.8f, bm, "报名", 300);
                            }

                            if (mapCount(0.7f, 290, 156, 543, 558, "gkwy4.png")) {
                                mFairy.onTap(1218, 42, 1230, 56, "", 1000);
                                mFairy.onTap(1218, 42, 1230, 56, "", 1000);
                                setTaskEnd();
                                return;
                            }
                        }
                    }
/*
                    int m = mFairy.dateMinute();

                    if (m == 20 || m == 50) {
                        mFairy.onTap(1218, 42, 1230, 56, "", 1000);
                        mFairy.onTap(1218, 42, 1230, 56, "", 1000);
                        setTaskEnd();
                        return;
                    }*/
                }

                result = mFairy.findPic("gkwy5.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (onceJudge("fubengk")) {
                        gkwy++;
                    }
                    LtLog.e(mFairy.getLineInfo("在战斗界面"));
                }

                result = rangeFindPic(50,"gkwy7.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    Thread.sleep(5000);

                    result =  rangeFindPic(50,"gkwy7.png");
                    if (result.sim < 0.7f) {
                        return;
                    }
                    mFairy.onTap(229, 301, 274, 356, "", 1000);
                    mFairy.onTap(621, 623, 653, 637, "", 1500);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(300, 498, 667, 592, "gkwy9.png");
                if (result.sim > 0.7f) {
                    err = 0;

                    result = rangeFindPic(50,"gkwy10.png");
                    mFairy.onTap(0.7f, result, "进入战场", 3000);

                    //if (result.sim > 0.8f) {

                    /*} else {
                        mFairy.onTap(1218, 42, 1230, 56, "", 1000);
                        mFairy.onTap(1218, 42, 1230, 56, "", 1000);
                        setTaskEnd();
                        return;
                    }*/
                    return;
                }

                Thread.sleep(1000);

                timeCount(15, 0);
            }
        };
    }//港口威压

    public void czdj() throws Exception {
        new single(mFairy, "船长对决") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
            }

            boolean activityJuage(FindResult act) throws Exception {
                result = mFairy.findPic(act.x + 111, act.y + 4, act.x + 260, act.y + 87, "czdj8.png");
                if (result.sim > 0.88f) {
                    LtLog.e(mFairy.getLineInfo("次数打完"));
                    setTaskEnd();
                    return true;
                }
                return false;
            }

            void content_01() throws Exception {
                activity(4, "czdj.png");
            }

            void inOperation() throws Exception {
                super.inOperation();

                if (mFairy.dateHour() == 13 || mFairy.dateHour() == 23) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {

                result = rangeFindPic(50,"czdj1.png");
                if (result.sim > 0.7f) {
                    err = 0;

                    result =  rangeFindPic(50,"czdj2.png");
                    mFairy.onTap(0.7f, result, "开始匹配", 1000);
                }

                result =  rangeFindPic(50,"czdj3.png", "czdj5.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    Thread.sleep(5000);
                }


                result =  rangeFindPic(50,"czdj7.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(1228, 684, 1240, 692, "", 1000);
                    setTaskEnd();
                    return;
                } else {

                    result =  rangeFindPic(50,"czdj4.png");
                    if (result.sim > 0.7f) {

                        result =  rangeFindPic(50,"czdj6.png");
                        if (result.sim > 0.7f) {
                            mFairy.onTap(1050, 205, 1065, 216, "", 3000);
                            return;
                        }

                        mFairy.onTap(1050, 205, 1065, 216, "", 1000);
                        mFairy.onTap(1050, 205, 1065, 216, "", 1000);
                        setTaskName(0);
                        return;
                    }
                }
                timeCount(15, 0);
            }
        };
    }//船长对决****

    public void jmsr() throws Exception {
        new single(mFairy, "精明商人的挑战") {

            void content_01() throws Exception {
                result = mFairy.findPic("jmsr2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    setTaskName(2);
                } else {
                    result = mFairy.findPic(958,3,1275,102,"ld1.png");
                    if (result.sim > 0.8f) {
                        gamePublicFuntion.sildeArchitecture("jmsr1.png");
                    } else {
                        map(true, "ld.png");
                        gamePublicFuntion.init(false);
                    }
                }
            }

            void content_02() throws Exception {

                result = mFairy.findPic("jmsr5.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(327, 332, 622, 414, "jmsr6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(628, 575, 655, 585, "放弃", 1000);
                        mFairy.onTap(724, 452, 756, 466, "确定放弃", 1000);
                        return;
                    }

                    gamePublicFuntion.srAIAnswer();
                    Thread.sleep(2000);

                } else {
                    result = mFairy.findPic("jmsr2.png");
                    if (result.sim > 0.8f) {
                        err = 0;

                        result = mFairy.findPic(981, 330, 1232, 671, "jmsr3.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "精明商人", 500);
                            if (mapCount(0.8f, 505, 204, 618, 538, "jmsr12.png")) {
                                mFairy.onTap(1221, 38, 1235, 52, "", 500);
                                setTaskEnd();
                                return;
                            }
                        }
                    }
                }

                result = mFairy.findPic("jmsr10.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    /*result = mFairy.findPic(556,557,895,630,"jmsr11.png");
                    if(result.sim>0.8f) {
                        mFairy.onTap(724,562,759,574,"再次挑战",1000);
                    }else{
                        mFairy.onTap(887,561,915,572,"没有机会了",1000);
                        setTaskEnd();
                        return;
                    }*/

                    mFairy.onTap(887, 561, 915, 572, "答题完成", 1000);
                    setTaskEnd();
                }

                result = mFairy.findPic("jmsr9.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 503, 466, 529, 479, "离开", 1000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("jmsr4.png");
                mFairy.onTap(0.8f, result, "准备好了", 1000);

                result = mFairy.findPic("jmsr8.png");
                mFairy.onTap(0.8f, result, "放弃", 1000);

                timeCount(10, 0);
            }
        };
    }//精明商人的挑战

    public void hayc() throws Exception {
        new single(mFairy, "海岸渔场") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
                frequencyInit("actErr");
                GamePublicFuntion.genhujiu = false;
            }

            void content_01() throws Exception {
                activity(4, "hayc.png");
            }

            boolean cancel() throws Exception {

                result =  rangeFindPic(50,"gen2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(737, 469, 771, 478, "", 500);
                    return true;
                }

                return false;
            }

            boolean activityJuage(FindResult act) throws Exception {

                result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "can.png");
                if (result.sim > 0.88f) {
                    mFairy.onTap(1242, 11, 1259, 30, "", 500);
                    pipei = false;
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();
                if (mFairy.dateHour() == 21) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                rank("hayc2.png", "hayc1.png");

                if (AtFairyConfig.getOption("shz").equals("1")) {

                    int week = mFairy.week();
                    int hour = mFairy.dateHour();
                    int minute = mFairy.dateMinute();

                    boolean hy = false;

                    if (week == 2 && hour == 20 && minute >= 20 && signJudge("shz周2_20点")) {
                        hy = true;
                        setSign("shz周2_20点");
                    }

                    if (week == 2 && hour == 21 && signJudge("shz周2_21点")) {
                        hy = true;
                        setSign("shz周2_21点");
                    }

                    if (week == 4 && hour == 21 && signJudge("shz周4_21点")) {
                        hy = true;
                        setSign("shz周4_21点");
                    }

                    if (hy) {
                        shz();
                        setTaskName(0);
                        return;
                    }
                }

            }

            void content_03() throws Exception {

                result = mFairy.findPic(93, 563, 309, 684, "gen1.png");
                if (result.sim > 0.72f) {
                    err = 0;
                }

                result =  rangeFindPic(5,"dui.png");
                if (result.sim > 0.8f) {
                    tuidui();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(257, 12, 903, 103, "rank4.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("【无队状态】"));
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(557, 289, 710, 429, "hayc3.png");
                mFairy.onTap(0.8f, result, "瓶子", 3000);

                Thread.sleep(1000);

                result = mFairy.findPic(41, 28, 92, 79, new String[]{"zanli1.png", "zanli2.png", "zanli3.png", "zanli4.png"});
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(new String[]{"rank7.png", "rank24.png"});
                mFairy.onTap(0.8f, result, 1236, 16, 1260, 28, "关掉组队界面", 1000);

                result = rangeFindPic(50,"bztz6.png");
                if (result.sim > 0.7f) {
                    err = 0;
                }

                timeCount(30, 0);
            }
        };
    }//海岸渔场

    public void shz() throws Exception {
        new single(mFairy, "商会战") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
                frequencyInit("actErr");
                GamePublicFuntion.genhujiu = false;
            }

            void content_01() throws Exception {
                activity(4, "shz.png");
                pipei = false;
            }

            boolean cancel() throws Exception {

                result =  rangeFindPic(50,"gen2.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(737, 469, 771, 478, "", 500);
                    return true;
                }

                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();
                if (mFairy.dateHour() == 20 && mFairy.dateMinute() > 58) {
                    setTaskEnd();
                    return;
                }

                if (mFairy.dateHour() == 21 && mFairy.dateMinute() > 40) {
                    setTaskEnd();
                    return;
                }

            }

            void content_02() throws Exception {

                result = rangeFindPic(50,"shz1.png");
                if (result.sim > 0.7f) {
                    err = 0;

                    result =  rangeFindPic(50,"shz2.png");
                    if (result.sim > 0.7f) {
                        frequencyInit("shz");
                        mFairy.onTap(0.7f, result, "进入战场", 3000);
                    } else {
                        Thread.sleep(1000);
                    }

                    if (frequencyMap("shz", 5)) {
                        mFairy.onTap(1240, 13, 1251, 26, "", 500);
                        setTaskEnd();
                        return;
                    }
                } else {
                    rank("shz6.png", "shz5.png");
                }
            }

            void content_03() throws Exception {

                result = mFairy.findPic(93, 563, 309, 684, "gen1.png");
                if (result.sim > 0.72f) {
                    err = 0;
                }

                result = mFairy.findPic(557, 289, 710, 429, "hayc3.png");
                mFairy.onTap(0.8f, result, "瓶子", 3000);

                Thread.sleep(1000);

                result =  rangeFindPic(5,"dui.png");
                if (result.sim > 0.7f) {
                    tuidui();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(41, 28, 92, 79, new String[]{"zanli1.png", "zanli2.png", "zanli3.png", "zanli4.png"});
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(257, 12, 903, 103, "rank4.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("【无队状态】"));
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(new String[]{"rank7.png", "rank24.png"});
                mFairy.onTap(0.8f, result, 1236, 16, 1260, 28, "关掉组队界面", 1000);

                result = rangeFindPic(50,"bztz6.png");
                if (result.sim > 0.7f) {
                    err = 0;
                }

                timeCount(30, 0);
            }
        };
    }//商会战****

    public void lwtdpx() throws Exception {
        new single(mFairy, "利维坦的咆哮") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
                frequencyInit("actErr");
                GamePublicFuntion.genhujiu = false;
            }

            void content_01() throws Exception {
                activity(3, "lwtdpx.png");
            }

            boolean cancel() throws Exception {
                result = mFairy.findPic("gen2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(737, 469, 771, 478, "回归队伍", 500);
                    return true;
                }

                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();
                if (mFairy.dateMinute() > 45) {
                    xianshiJugde = false;
                    setTaskEnd();
                    return;
                }
            }

            boolean activityJuage(FindResult act) throws Exception {

                result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "can.png");
                if (result.sim > 0.88f) {
                    mFairy.onTap(1242, 11, 1259, 30, "", 500);
                    pipei = false;
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void content_02() throws Exception {
                rank("lwtdpx2.png", "lwtdpx1.png");
            }

            void content_03() throws Exception {

                result = mFairy.findPic(93, 563, 309, 684, "gen1.png");
                if (result.sim > 0.72f) {
                    err = 0;
                }

                result =  rangeFindPic(5,"dui.png");
                if (result.sim > 0.7f) {
                    tuidui();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(257, 12, 903, 103, "rank4.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("【无队状态】"));
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(41, 28, 92, 79, new String[]{"zanli1.png", "zanli2.png", "zanli3.png", "zanli4.png"});
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(new String[]{"rank7.png", "rank24.png"});
                mFairy.onTap(0.8f, result, 1236, 16, 1260, 28, "关掉组队界面", 1000);

                Thread.sleep(1000);

                timeCount(30, 0);
            }
        };
    }//利维坦的咆哮

    public void yljd() throws Exception {
        new single(mFairy, "幽灵舰队") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
                frequencyInit("actErr");
                GamePublicFuntion.genhujiu = false;
            }

            void content_01() throws Exception {
                activity(3, "yljd.png");
            }

            boolean cancel() throws Exception {

                result = mFairy.findPic("gen2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(737, 469, 771, 478, "", 500);
                    return true;
                }

                return false;
            }

            boolean activityJuage(FindResult act) throws Exception {

                result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "can.png");
                if (result.sim > 0.88f) {
                    mFairy.onTap(1242, 11, 1259, 30, "", 500);
                    pipei = false;
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();
                if (mFairy.dateMinute() > 45) {
                    xianshiJugde = false;
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                rank("yljd2.png", "yljd1.png");
            }

            void content_03() throws Exception {

                result = mFairy.findPic(93, 563, 309, 684, "gen1.png");
                if (result.sim > 0.72f) {
                    err = 0;

                }

                result =  rangeFindPic(5,"dui.png");
                if (result.sim > 0.7f) {
                    tuidui();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(257, 12, 903, 103, "rank4.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("【无队状态】"));
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(41, 28, 92, 79, new String[]{"zanli1.png", "zanli2.png", "zanli3.png", "zanli4.png"});
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(new String[]{"rank7.png", "rank24.png"});
                mFairy.onTap(0.8f, result, 1236, 16, 1260, 28, "关掉组队界面", 1000);

                Thread.sleep(1000);

                timeCount(30, 0);
            }
        };
    }//幽灵舰队

    public void hdw() throws Exception {
        new single(mFairy, "海盗王之怒") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
                frequencyInit("actErr");
                GamePublicFuntion.genhujiu = false;
            }

            void content_01() throws Exception {
                activity(3, "hdw.png");
            }

            boolean cancel() throws Exception {

                result = mFairy.findPic("gen2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(737, 469, 771, 478, "", 500);
                    return true;
                }

                return false;
            }

            boolean activityJuage(FindResult act) throws Exception {

                result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "can.png");
                if (result.sim > 0.88f) {
                    mFairy.onTap(1242, 11, 1259, 30, "", 500);
                    pipei = false;
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();
                if (mFairy.dateMinute() > 50) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                rank("hdw2.png", "hdw1.png");
            }

            void content_03() throws Exception {

                result = mFairy.findPic(93, 563, 309, 684, "gen1.png");
                if (result.sim > 0.72f) {
                    err = 0;
                }

                result = rangeFindPic(5,"dui.png");
                if (result.sim > 0.7f) {
                    tuidui();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(41, 28, 92, 79, new String[]{"zanli1.png", "zanli2.png", "zanli3.png", "zanli4.png"});
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(257, 12, 903, 103, "rank4.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("【无队状态】"));
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(new String[]{"rank7.png", "rank24.png"});
                mFairy.onTap(0.8f, result, 1236, 16, 1260, 28, "关掉组队界面", 1000);

                Thread.sleep(1000);

                timeCount(30, 0);
            }
        };
    }//海盗王之怒

    public void hjjdll() throws Exception {
        new single(mFairy, "皇家舰队历练") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
                frequencyInit("actErr");
                GamePublicFuntion.genhujiu = false;
            }

            void content_01() throws Exception {
                activity(4, "hjjdll.png");
            }

            boolean cancel() throws Exception {

                result = mFairy.findPic("gen2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(737, 469, 771, 478, "", 500);
                    return true;
                }

                return false;
            }

            boolean activityJuage(FindResult act) throws Exception {

                result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "can.png");
                if (result.sim > 0.88f) {
                    mFairy.onTap(1242, 11, 1259, 30, "", 500);
                    pipei = false;
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();
                if (mFairy.dateHour() == 21) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                rank("hjjdll2.png", "hjjdll1.png");
            }

            void content_03() throws Exception {

                result = mFairy.findPic(93, 563, 309, 684, "gen1.png");
                if (result.sim > 0.72f) {
                    err = 0;
                }

                result = mFairy.findPic(41, 28, 92, 79, new String[]{"zanli1.png", "zanli2.png", "zanli3.png", "zanli4.png"});
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }

                result =  rangeFindPic(5,"dui.png");
                if (result.sim > 0.8f) {
                    tuidui();
                    setTaskEnd();
                    return;
                }

                if (timeMap("timefb", 600000, false)) {
                    tuidui();
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(257, 12, 903, 103, "rank4.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("【无队状态】"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(new String[]{"rank7.png", "rank24.png"});
                mFairy.onTap(0.8f, result, 1236, 16, 1260, 28, "关掉组队界面", 1000);

                Thread.sleep(1000);

                timeCount(30, 0);
            }
        };
    }//皇家舰队历练****

    public void shzg() throws Exception {
        new single(mFairy, "深海战歌") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
                frequencyInit("actErr");
                GamePublicFuntion.genhujiu = false;
            }

            void content_01() throws Exception {
                activity(4, "shzg.png");
            }

            boolean cancel() throws Exception {
                result = mFairy.findPic("gen2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(737, 469, 771, 478, "", 500);
                    return true;
                }
                return false;
            }

            boolean activityJuage(FindResult act) throws Exception {
                result = mFairy.findPic(act.x + 111, act.y + 4, act.x + 260, act.y + 87, "shzg13.png");
                if (result.sim > 0.88f) {
                    LtLog.e(mFairy.getLineInfo("次数打完"));
                    setTaskEnd();
                    return true;
                }

                result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "can.png");
                if (result.sim > 0.88f) {
                    mFairy.onTap(1242, 11, 1259, 30, "", 500);
                    pipei = false;
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();
                if (mFairy.dateHour() == 22) {
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                rank("shzg2.png", "shzg12.png");
            }

            void content_03() throws Exception {

                result = mFairy.findPic(93, 563, 309, 684, "gen1.png");
                if (result.sim > 0.72f) {
                    err = 0;
                }

                result = rangeFindPic(50,"shzg8.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "继续游戏", 3000);
                }

                result =  rangeFindPic(50,"shzg7.png", "shzg10.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    Thread.sleep(3000);
                }

                result = mFairy.findPic(41, 28, 92, 79, new String[]{"zanli1.png", "zanli2.png", "zanli3.png", "zanli4.png"});
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }

                result =  rangeFindPic(5,"dui.png");
                if (result.sim > 0.7f) {
                    tuidui();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(257, 12, 903, 103, "rank4.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("【无队状态】"));
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(new String[]{"rank7.png", "rank24.png"});
                mFairy.onTap(0.8f, result, 1236, 16, 1260, 28, "关掉组队界面", 1000);

                Thread.sleep(1000);

                timeCount(30, 0);
            }
        };
    }//深海战歌****

    public void klsg() throws Exception {
        new single(mFairy, "科林斯桂冠竞技") {

            void create() throws Exception {
                super.create();
                rny("rny1.png", "rny3.png");//先回热亚
                frequencyInit("actErr");
                GamePublicFuntion.genhujiu = false;
            }

            void content_01() throws Exception {
                activity(4, "klsg.png");
            }

            boolean cancel() throws Exception {
                result = mFairy.findPic("gen2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(737, 469, 771, 478, "", 500);
                    return true;
                }

                return false;
            }

            boolean activityJuage(FindResult act) throws Exception {

                result = mFairy.findPic(act.x + 111, act.y + 4, act.x + 260, act.y + 87, "shzg13.png");
                if (result.sim > 0.88f) {
                    LtLog.e(mFairy.getLineInfo("次数打完"));
                    setTaskEnd();
                    return true;
                }

                result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "can.png");
                if (result.sim > 0.88f) {
                    mFairy.onTap(1242, 11, 1259, 30, "", 500);
                    pipei = false;
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();
                if (mFairy.dateHour() == 22) {
                    setTaskEnd();
                    return;
                }

            }

            void content_02() throws Exception {
                rank("klsg2.png", "klsg5.png");
            }

            void content_03() throws Exception {

                result = mFairy.findPic(93, 563, 309, 684, "gen1.png");
                if (result.sim > 0.72f) {
                    err = 0;
                }

                result =  rangeFindPic(50,"shzg7.png", "shzg10.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    Thread.sleep(3000);
                }

                result = mFairy.findPic(41, 28, 92, 79, new String[]{"zanli1.png", "zanli2.png", "zanli3.png", "zanli4.png"});
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }

                result =  rangeFindPic(5,"dui.png");
                if (result.sim > 0.7f) {
                    tuidui();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(257, 12, 903, 103, "rank4.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("【无队状态】"));
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(new String[]{"rank7.png", "rank24.png"});
                mFairy.onTap(0.8f, result, 1236, 16, 1260, 28, "关掉组队界面", 1000);

                Thread.sleep(1000);

                timeCount(30, 0);
            }
        };
    }//科林斯桂冠竞技****


    public void shzgling() throws Exception {
        new single(mFairy, "深海战歌领奖") {

            void content_01() throws Exception {
                activity(4, "shzg.png");
            }

            void content_02() throws Exception {

                result = mFairy.findPic("shzg3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(57, 585, 70, 595, "奖励1", 1000);
                    mFairy.onTap(382, 665, 409, 683, "", 1000);
                    mFairy.onTap(118, 585, 132, 593, "奖励2", 1000);
                    mFairy.onTap(382, 665, 409, 683, "", 1000);
                    mFairy.onTap(182, 583, 195, 594, "奖励3", 1000);
                    mFairy.onTap(382, 665, 409, 683, "", 1000);
                    mFairy.onTap(1246, 23, 1254, 35, "", 1000);
                    setTaskEnd();
                    return;
                }

                timeCount(10, 99);
            }
        };
    }//深海战歌领奖****

    /**
     * 固定队任务
     */
    public void ttmy() throws Exception {
        new single(mFairy, "泰坦 命运") {

            boolean activityJuage(FindResult act) throws Exception {
                if (!AtFairyConfig.getOption("ttmy").equals("2")) {
                    result = mFairy.findPic(act.x + 111, act.y + 4, act.x + 260, act.y + 87, "ttmy3.png");
                    if (result.sim > 0.88f) {
                        setTaskEnd();
                        return true;
                    }
                }

                return false;
            }

            void content_01() throws Exception {
                activity(3, "ttmy.png", "myzl.png", "sszh.png");
            }

            void content_02() throws Exception {

                if (gamePublicFuntion.shibai()) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(425, 253, 740, 442, "mshy2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1227, 657, 1245, 671, "完成", 500);
                    if (!AtFairyConfig.getOption("ttmy").equals("2")) {
                        setTaskEnd();
                        return;
                    }
                    setTaskName(0);
                    return;
                }

                result =  rangeFindPic(20,new String[]{"ttmy1.png", "myzl1.png", "sszh1.png"});
                if (result.sim > 0.7f) {
                    err = 0;

                    result = mFairy.findPic(168, 443, 383, 550, "caozuo.png");
                    if (result.sim > 0.7f) {
                        return;
                    }

                    switch (AtFairyConfig.getOption("ttmyCeng")) {
                        case "1":
                            mFairy.onTap(224, 117, 262, 135, "第一层", 1000);
                            break;
                        case "2":
                            mFairy.onTap(224, 217, 276, 241, "第二层", 1000);
                            break;
                        case "3":
                            mFairy.onTap(218, 310, 281, 331, "第三层", 1000);
                            break;
                        case "4":
                            mFairy.onTap(219, 405, 277, 424, "第四层", 1000);
                            break;
                        case "5":
                            mFairy.ranSwipe(242, 466, 251, 21, 500, 500);
                            mFairy.ranSwipe(242, 466, 251, 21, 500, 1000);
                            mFairy.onTap(256, 472, 270, 475, "第五层", 1000);
                            break;
                        case "6":
                            mFairy.ranSwipe(242, 466, 251, 21, 500, 500);
                            mFairy.ranSwipe(242, 466, 251, 21, 500, 1000);
                            mFairy.onTap(220, 84, 228, 86, "第六层", 1000);
                            break;
                        case "7":
                            mFairy.ranSwipe(242, 466, 251, 21, 500, 500);
                            mFairy.ranSwipe(242, 466, 251, 21, 500, 1000);
                            mFairy.onTap(252, 138, 273, 146, "第七层", 1000);
                            break;
                        case "8":
                            mFairy.ranSwipe(242, 466, 251, 21, 500, 500);
                            mFairy.ranSwipe(242, 466, 251, 21, 500, 1000);
                            mFairy.onTap(246, 236, 269, 246, "第八层", 1000);
                            break;
                        case "9":
                            mFairy.ranSwipe(242, 466, 251, 21, 500, 500);
                            mFairy.ranSwipe(242, 466, 251, 21, 500, 1000);
                            mFairy.onTap(235, 331, 263, 347, "第九层", 1000);
                            break;
                        case "10":
                            mFairy.ranSwipe(242, 466, 251, 21, 500, 500);
                            mFairy.ranSwipe(242, 466, 251, 21, 500, 1000);
                            mFairy.onTap(2252, 424, 282, 445, "第十层", 1000);
                            break;
                    }

                    int l = mFairy.getColorNum(1166, 624, 1196, 654, "103,103,103", 0.95f);
                    if (l > 500) {
                        LtLog.e(mFairy.getLineInfo("队伍人数没有满"));
                        setTaskEnd();
                        return;
                    } else {
                        mFairy.onTap(1043, 630, 1098, 642, "组队挑战", 3000);
                    }
                }

                if (frequencyMap("chat", 1)) {
                    gamePublicFuntion.chat();
                }

                timeCount(8, 0);
            }
        };
    }//泰坦 命运 ****  myzl****  sszh****

    public void mshy() throws Exception {
        new single(mFairy, "迷失的海域") {

            int endCount;

            boolean activityJuage(FindResult act) throws Exception {
                if (!AtFairyConfig.getOption("mshy").equals("2")) {
                    result = mFairy.findPic(act.x + 111, act.y + 4, act.x + 260, act.y + 87, "mshy3.png");
                    if (result.sim > 0.88f) {
                        LtLog.e(mFairy.getLineInfo("次数打完"));
                        setTaskEnd();
                        return true;
                    }
                }

                return false;
            }

            void content_01() throws Exception {
                activity(3, "mshy.png");
            }

            void content_02() throws Exception {

                if (gamePublicFuntion.shibai()) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(425, 253, 740, 442, "mshy2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1227, 657, 1245, 671, "", 500);
                    if (!AtFairyConfig.getOption("mshy").equals("2")) {
                        endCount++;
                        if (endCount >= 3) {
                            setTaskEnd();
                            return;
                        }
                    }
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("mshy1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(168, 443, 383, 550, "caozuo.png");
                    if (result.sim > 0.8f) {
                        return;
                    }

                    switch (AtFairyConfig.getOption("mshyNd")) {
                        case "1":
                            mFairy.onTap(54, 113, 80, 126, "简单", 1000);
                            break;
                        case "2":
                            mFairy.onTap(54, 206, 75, 217, "困难", 1000);
                            break;
                    }


                    mFairy.onTap(1163, 84, 1177, 93, "", 500);
                    mFairy.onTap(1021, 64, 1031, 75, "", 1500);

                    mFairy.ranSwipe(947, 181, 947, 346, 500, 1000);




                    switch (AtFairyConfig.getOption("mshyCeng")) {
                        case "1":
                            mFairy.onTap(885, 145, 886, 146, "第一层", 1000);
                            break;
                        case "2":
                            mFairy.onTap(885, 212, 886, 213, "第二层", 1000);
                            break;
                        case "3":
                            mFairy.onTap(885, 279, 886, 280, "第三层", 1000);
                            break;
                        case "4":
                            mFairy.onTap(885, 346, 886, 347, "第四层", 1000);
                            break;
                        case "5":
                            mFairy.onTap(885, 413, 886, 414, "第五层", 1000);
                            break;
                        case "6":
                            mFairy.ranSwipe(935,356,935,150,500,500);
                            mFairy.ranSwipe(935,356,935,150,500,1500);

                            mFairy.onTap(945,299,966,309, "倒数第三层", 1000);
                            break;
                        case "7":
                            mFairy.ranSwipe(935,356,935,150,500,500);
                            mFairy.ranSwipe(935,356,935,150,500,1500);

                            mFairy.onTap(925,368,944,375, "倒数第二层", 1000);
                            break;
                        case "8":
                            mFairy.ranSwipe(935,356,935,150,500,500);
                            mFairy.ranSwipe(935,356,935,150,500,1500);

                            mFairy.onTap(939,435,958,443, "最高层", 1000);
                            break;

                        default:
                            mFairy.onTap(885, 145, 886, 146, "第一层", 1000);
                            break;
                    }

                    mFairy.onTap(713, 327, 734, 374, "", 1000);
                    mFairy.onTap(937, 312, 949, 336, "", 1000);
                    mFairy.onTap(1153, 347, 1166, 375, "", 1000);
                }

                if (frequencyMap("chat", 1)) {
                    gamePublicFuntion.chat();
                }

                timeCount(8, 0);
            }
        };

    }//迷失的海域

    public void js() throws Exception {
        new single(mFairy, "竞速活动") {

            void create() throws Exception {
                super.create();
                tuidui();
            }

            void init() throws Exception {
                gamePublicFuntion.init(true);
                TaskMain.XUNHANG = true;
                setTaskName(1);
            }

            boolean activityJuage(FindResult act) throws Exception {
                result = mFairy.findPic(act.x + 100, act.y - 15, act.x + 230, act.y + 40, "js5.png");
                if (result.sim > 0.88f) {
                    LtLog.e(mFairy.getLineInfo("次数打完"));
                    setTaskEnd();
                    return true;
                }
                return false;
            }

            void content_01() throws Exception {
                activity(3, "js.png");
            }

            void content_02() throws Exception {
                result = mFairy.findPic(1001, 456, 1210, 704, "js2.png");
                if (result.sim > 0.8f) {
                    frequencyInit("js");
                    mFairy.onTap(0.8f, result, "竞速玩法", 1000);
                    TaskMain.XUNHANG = false;
                    err = 3;
                } else {
                    if (frequencyMap("js", 2)) {
                        gamePublicFuntion.chat();
                    }
                }

                result = mFairy.findPic(299, 93, 609, 276, "js4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1226, 656, 1240, 673, "", 500);
                    setTaskEnd();
                    return;
                }

                if (gamePublicFuntion.taskClick("js3.png")) {
                    gamePublicFuntion.init(false);
                    setTaskName(3);
                    return;
                }

                Thread.sleep(500);

                timeCount(15, 0);
            }

            void content_03() throws Exception {

                result = mFairy.findPic(1126, 234, 1257, 292, "js6.png");
                mFairy.onTap(0.8f, result, "任务", 1000);

                result =  rangeFindPic(50,"js7.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    result = mFairy.findPic(164, 107, 422, 667, "js8.png");
                    if (result.sim > 0.7f) {
                        mFairy.onTap(0.8f, result, "竞速玩法", 1500);

                        mFairy.onTap(624, 621, 709, 634, "", 1000);
                        mFairy.onTap(624, 621, 709, 634, "取消任务", 1000);

                        mFairy.onTap(1117, 68, 1130, 75, "关闭", 1000);
                        setTaskEnd();
                        return;
                    } else {
                        mFairy.ranSwipe(304, 555, 306, 330, 500, 1000);
                        if (frequencyMap("jserr", 5)) {
                            setTaskName(0);
                            return;
                        }
                    }
                }
                timeCount(10, 0);

            }
        };
    }//竞速活动

    public void gd() throws Exception {
        new single(mFairy, "跟队") {

            void create() throws Exception {
                super.create();
                GamePublicFuntion.genhujiu = false;
            }

            void init() throws Exception {
                for (int i = 0; i < 3; i++) {
                    gamePublicFuntion.close();
                    gamePublicFuntion.chat();

                }
                TaskMain.XUNHANG = true;
                setTaskName(1);
            }

            void inOperation() throws Exception {
                super.inOperation();
                shibai();
            }

            boolean cancel() throws Exception {

                result = mFairy.findPic("gen2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(737, 469, 771, 478, "", 1000);
                    return true;
                }

                result = mFairy.findPic(526, 259, 800, 414, "gen3.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(743, 468, 772, 487, "", 18000);
                    return true;
                }

                result = mFairy.findPic("gen4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(743, 469, 771, 482, "", 1000);
                    return true;
                }

                return false;
            }

            void content_01() throws Exception {

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(257, 12, 903, 103, "rank4.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("【无队状态】"));
                        return;
                    }

                    result = mFairy.findPic(403, 177, 1039, 331, "rank2.png");
                    if (result.sim > 0.75f) {
                        LtLog.e(mFairy.getLineInfo("【队员状态】"));


                        result = mFairy.findPic(309, 67, 1209, 192, "rank23.png");
                        if (result.sim > 0.75f) {
                            mFairy.onTap(0.75f, result, "回归队伍", 1000);
                        } else {
                            mFairy.onTap(1228, 657, 1238, 668, "", 1000);
                        }
                        /*result = mFairy.findPic(237, 171, 1097, 301, "rank22.png");
                        mFairy.onTap(0.75f, result, "查看队伍", 1000);*/
                        setTaskName(2);

                        return;
                    }

                    result = mFairy.findPic(433, 198, 1054, 358, "rank6.png");
                    if (result.sim > 0.75f) {
                        LtLog.e(mFairy.getLineInfo("【队长状态】"));
                        if (AtFairyConfig.getOption("dhdui").equals("1")) {
                            tuidui();
                            setTaskEnd();
                        } else {
                            setTaskName(2);
                        }
                        return;
                    }

                    result = mFairy.findPic(443, 13, 971, 124, "rank1.png");
                    mFairy.onTap(0.7f, result, "队伍操作", 1000);
                }
                timeCount(10, 0);
            }

            void content_02() throws Exception {

                result = mFairy.findPic(93, 563, 309, 684, "gen1.png");
                if (result.sim > 0.72f) {
                    err = 0;
                }

                result = mFairy.findPic(425, 253, 740, 442, "mshy2.png");
                mFairy.onTap(0.8f, result, 1227, 657, 1245, 671, "完成", 1500);

                gamePublicFuntion.shibai();

                result = mFairy.findPic(557, 289, 710, 429, "hayc3.png");
                mFairy.onTap(0.8f, result, "瓶子", 3000);

                result =  rangeFindPic(50,"hjhl7.png");
                mFairy.onTap(0.7f, result, "领取奖励", 3000);

                result =  rangeFindPic(50,"hjhl8.png");
                mFairy.onTap(0.7f, result, 1113, 66, 1125, 81, "", 1500);

                result = mFairy.findPic(1087, 300, 1209, 418, new String[]{"hjhl6.png", "jiangli.png"});
                mFairy.onTap(0.8f, result, "领取奖励", 1000);

                result = mFairy.findPic(41, 28, 92, 79, new String[]{"zanli1.png", "zanli2.png", "zanli3.png", "zanli4.png"});
                if (result.sim > 0.7f) {
                    setTaskName(0);
                    return;
                }

               /* result = mFairy.findPic(257, 12, 903, 103, "rank4.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("【无队状态】"));
                    setTaskName(0);
                    return;
                }*/

                result =  rangeFindPic(5,"dui.png");
                if (result.sim > 0.7f) {
                    if (AtFairyConfig.getOption("dhdui").equals("1")) {
                        tuidui();
                        setTaskEnd();
                    }/*else{
                        setTaskName(0);
                    }*/
                    //return;
                }

                Thread.sleep(1000);

                timeCount(300, 0);
            }
        };
    }//跟队

    static class TradeLabel {

        public TradeLabel(String name, int user, int xing, int portLevel, int[] goodsPos, int[] portPos) {
            this.name = name;
            this.user = user;
            this.xing = xing;
            this.portLevel = portLevel;
            this.goodsPos = goodsPos;
            this.portPos = portPos;
        }

        public String name;

        public int user;// 是否为用户勾选 0为否 1为是

        public int xing; //星级

        public int portLevel; //距离级别

        public int[] goodsPos;//商品位置

        public int[] portPos; //港口位置

        @Override
        public String toString() {
            return "TradeLabel{" +
                    "name='" + name + '\'' +
                    ", user=" + user +
                    ", xing=" + xing +
                    ", portLevel=" + portLevel +
                    ", goodsPos=" + Arrays.toString(goodsPos) +
                    ", portPos=" + Arrays.toString(portPos) +
                    '}';
        }

        public static TradeLabel compare(List<TradeLabel> tradeLabelList) {


            /**
             *1.港口是否开放
             * 2.用户选择
             * 3.优先低星
             *
             */
            if (tradeLabelList.size() == 0) {
                LtLog.e("tradeLabelList 为空");
                return null;
            }

            TradeLabel tradeLabel = tradeLabelList.get(0);

            for (int i = 1; i < tradeLabelList.size(); i++) {

                if (tradeLabel.name.equals("")) {
                    tradeLabel = tradeLabelList.get(i);
                    continue;
                }

                if (tradeLabel.portLevel == 4) {
                    tradeLabel = tradeLabelList.get(i);
                    continue;
                }

                if (tradeLabelList.get(i).portLevel == 4) {
                    continue;
                }

                if (tradeLabel.user < tradeLabelList.get(i).user) {
                    tradeLabel = tradeLabelList.get(i);
                    continue;
                }

                if (tradeLabel.portLevel > tradeLabelList.get(i).portLevel) {
                    tradeLabel = tradeLabelList.get(i);
                    continue;
                }

                if (tradeLabel.user == 0) {
                    if (tradeLabel.xing > tradeLabelList.get(i).xing) {
                        tradeLabel = tradeLabelList.get(i);
                        continue;
                    }
                }
            }

            if (tradeLabel.portLevel == 4) {
                LtLog.e("tradeLabel.portLevel  err...");
                return null;
            }

            return tradeLabel;
        }


    }

    public void ps() throws Exception {
        new single(mFairy, "跑商") {

            int[] po;
            List<int[]> psRange; //储存 判断货物的范围
            List<TradeLabel> psJudge;
            boolean buy;
            TradeLabel tradeLabel;

            void create() throws Exception {
                super.create();
                tuidui();
                po = null;
                tradeLabel = null;
                psJudge = new ArrayList<>();
                psRange = new ArrayList<>();
                psRange.add(new int[]{469, 375, 777, 453});
                psRange.add(new int[]{469, 443, 775, 517});
                psRange.add(new int[]{469, 510, 787, 583});
                if (mFairy.dateHour() % 2 == 1 && mFairy.dateMinute() >= 10 && mFairy.dateMinute() <= 30) {
                    gamePublicFuntion.init(false);
                    while (mFairy.condit()) {
                        if (timeMap("huowu", 10000, true)) {
                            LtLog.e(mFairy.getLineInfo("等待货物刷新"));
                        }

                        result =  rangeFindPic(50,"yunan.png");
                        mFairy.onTap(0.7f, result, "遇难回港", 5000);

                        if (mFairy.dateMinute() > 30) {
                            po = null;
                            initOnceJudge("opscs");
                            break;
                        }
                    }
                }
                buy = true;
            }

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
            }

            void judgeCommodity() throws Exception {

                for (int i = 0; i < psRange.size(); i++) {

                    result = mFairy.findPic(729, 142, 1032, 600, "ps1.png");
                    mFairy.onTap(0.8f, result, 912, 623, 933, 637, "", 1000);

                    int a[] = psRange.get(i);

                    result = mFairy.findPic(a[0], a[1], a[2], a[3], "ps2.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("位置 " + (i + 1) + "不符合要求"));
                        continue;
                    }

                    result = mFairy.findPic(a[0], a[1], a[2], a[3], "ps4.png");
                    if (result.sim < 0.8f) {
                        LtLog.e(mFairy.getLineInfo("位置 " + (i + 1) + "没有显示货物"));
                        continue;
                    }

                    String name = "";

                    switch (i) {
                        case 0:
                            name = gamePublicFuntion.getPictureText(569, 399, 152, 40);
                            break;
                        case 1:
                            name = gamePublicFuntion.getPictureText(568, 465, 155, 34);
                            break;
                        case 2:
                            name = gamePublicFuntion.getPictureText(569, 531, 155, 35);
                            break;
                    }

                    LtLog.e("识别出来的name:" + name);

                    int user = 0;
                    int xing = 3;
                    int porLevel = 4;
                    int[] goodsPos = new int[]{a[0] + 165, a[1] + 35};
                    int[] portPos = new int[]{892, 263};

                    for (int j = 1; j <= 3; j++) {
                        result = mFairy.findPic(a[0], a[1], a[2], a[3], "ps" + j + "x.png");
                        if (result.sim > 0.8f) {
                            if (AtFairyConfig.getOption("psxing" + j).equals("1")) {
                                user = 1;
                            }
                            xing = j;
                            break;
                        }
                    }

                    mFairy.onTap(goodsPos[0], goodsPos[1], goodsPos[0] + 1, goodsPos[1] + 1, "点击一下商品位置", 2500);

                    /**
                     * 计算距离 判断每隔港口的远近 是否可以入港
                     */
                    FindResult juli = mFairy.findPic(729, 142, 1032, 600, "ps1.png");
                    if (juli.sim > 0.8f) {

                        LtLog.e(mFairy.getLineInfo("港口界面"));

                        int[] fanwei = new int[]{971, juli.y, 1116, 691};

                        result = mFairy.findPic(873, 268, 1024, 621, "ps3.png");
                        if (result.sim > 0.8f) {
                            fanwei[3] = result.y;
                        }

                        nn:
                        for (int n = 0; n <= 3; n++) {
                            List<FindResult> g = mFairy.findPic(fanwei[0], fanwei[1], fanwei[2], fanwei[3], 0.85f, "g" + n + ".png");
                            if (g.size() != 0) {
                                for (FindResult r :
                                        g) {
                                    result = mFairy.findPic(784, r.y, 1094, r.y + 60, "ps12.png");
                                    if (result.sim < 0.8f) {
                                        porLevel = n;
                                        portPos = new int[]{r.x - 120, r.y};
                                        break nn;
                                    }
                                }
                            }
                        }


                        /**
                         * 目前分析 流行港口每次只有一个
                         */

                        nx:
                        for (int ii = 0; ii < 5; ii++) {
                            FindResult liu = mFairy.findPic(873, 268, 1024, 567, "ps3.png");
                            if (liu.sim > 0.8f) {

                                nn:
                                for (int n = 0; n <= 3; n++) {
                                    List<FindResult> g = mFairy.findPic(1017, liu.y, 1082, 700, 0.85f, "g" + n + ".png");
                                    if (g.size() != 0) {
                                        for (FindResult r :
                                                g) {
                                            result = mFairy.findPic(784, r.y, 1094, r.y + 60, "ps12.png");
                                            if (result.sim < 0.8f) {
                                                break nx;
                                            }
                                        }
                                    }
                                }
                                porLevel = 4;// 设置为不可前往的港口
                                break nx;
                            } else {
                                mFairy.ranSwipe(955, 554, 955, 223, 500, 1000);
                            }
                        }

                    } else {
                        LtLog.e(mFairy.getLineInfo("没有显示港口界面"));
                        i--;
                        continue;
                    }

                    mFairy.onTap(886, 638, 921, 653, "", 500);
                    mFairy.onTap(886, 638, 921, 653, "", 1000);
                    psJudge.add(new TradeLabel(name, user, xing, porLevel, goodsPos, portPos));
                }

                for (TradeLabel tradeLabel : psJudge
                        ) {
                    LtLog.e(tradeLabel.toString());
                }
            }

            void inOperation() throws Exception {

                if (buy) {
                    TaskMain.XUNHANG = true;
                } else {
                    TaskMain.XUNHANG = false;
                }

                super.inOperation();

                gamePublicFuntion.chat();
            }

            void content_01() throws Exception {

                result = mFairy.findPic(939, 40, 1269, 224, new String[]{"zbtf1.png", "zbtf2.png"});
                mFairy.onTap(0.7f, result, "特", 1000);

                result = mFairy.findPic("tx.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (buy) {

                        psJudge.clear();

                        judgeCommodity();//生成货物标签

                        TradeLabel nt = tradeLabel != null ? tradeLabel : null;

                        tradeLabel = TradeLabel.compare(psJudge);

                        if (!(nt != null && tradeLabel.name.equals(nt.name))) {
                            po = null;
                        }

                        if (tradeLabel != null) {

                            frequencyInit("tradeLabel");

                            mFairy.onTap(tradeLabel.goodsPos[0], tradeLabel.goodsPos[1], tradeLabel.goodsPos[0] + 1, tradeLabel.goodsPos[1] + 1, "点击货物位置", 2000);
                            mFairy.onTap(tradeLabel.portPos[0], tradeLabel.portPos[1], tradeLabel.portPos[0], tradeLabel.portPos[1] + 1, "点击货物港口位置", 2500);

                            mFairy.onTap(732, 453, 763, 463, "前往目标港口购买", 3000);
                            setTaskName(2);
                            return;
                        } else {
                            mFairy.onTap(921, 642, 936, 653, "", 1000);
                            if (frequencyMap("tradeLabel", 2)) {

                                gamePublicFuntion.init(false);

                                Thread.sleep(60000);

                                while (mFairy.condit()) {

                                    if (timeMap("huowu", 10000, true)) {
                                        LtLog.e(mFairy.getLineInfo("等待货物刷新"));
                                    }
                                    result = mFairy.findPic("yunan.png");
                                    mFairy.onTap(0.8f, result, "遇难回港", 5000);

                                    if (mFairy.dateMinute() == 31 && mFairy.dateHour() % 2 == 1) {
                                        po = null;
                                        tradeLabel = null;
                                        initOnceJudge("opscs");
                                        break;
                                    }
                                }

                                setTaskName(0);
                                return;
                            }
                        }
                    } else {
                        mFairy.onTap(tradeLabel.goodsPos[0], tradeLabel.goodsPos[1], tradeLabel.goodsPos[0] + 1, tradeLabel.goodsPos[1] + 1, "点击货物位置", 2500);

                        for (int i = 0; i < 5; i++) {
                            FindResult liu = mFairy.findPic(873, 268, 1024, 567, "ps3.png");
                            if (liu.sim > 0.8f) {

                                nn:
                                for (int n = 0; n <= 3; n++) {
                                    List<FindResult> g = mFairy.findPic(1017, liu.y, 1082, 700, 0.85f, "g" + n + ".png");
                                    if (g.size() != 0) {
                                        for (FindResult r :
                                                g) {
                                            result = mFairy.findPic(784, r.y, 1094, r.y + 60, "ps12.png");
                                            if (result.sim < 0.8f) {
                                                mFairy.onTap(0.8f, r, r.x - 120, r.y, r.x - 119, r.y + 1, "点击最近的港口", 2500);
                                                mFairy.onTap(732, 453, 763, 463, "前往目标港口卖出", 3000);
                                                setTaskName(2);
                                                return;
                                            }
                                        }
                                    }
                                }

                                LtLog.e(mFairy.getLineInfo("【没有识别出最近的港口！】"));
                                break;
                              /*  mFairy.onTap(liu.x, liu.y + 40, liu.x + 1, liu.y + 45, "没有识别出最近港口,默认点击第一个", 2500);
                                mFairy.onTap(732, 453, 763, 463, "前往目标港口卖出", 3000);
                                setTaskName(2);*/
                            } else {
                                mFairy.ranSwipe(955, 554, 955, 223, 500, 1000);
                            }
                        }

                        /**
                         * 识别出异常的处理
                         */
                        LtLog.e(mFairy.getLineInfo("【没有识别的卖出的港口！！！】"));

                        gamePublicFuntion.init(false);

                        FindResult hai = mFairy.findPic("hai.png");
                        FindResult pac = mFairy.findPic("package.png");
                        if (hai.sim < 0.8f && pac.sim > 0.8f) {//在主城
                            LtLog.e(mFairy.getLineInfo("在主城,开始卖货物"));
                        } else {
                            map(false, "rny4.png");
                        }
                        setTaskName(2);
                        return;
                    }
                }

                timeCount(5, 0);
            }

            void content_02() throws Exception {
                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    if (gamePublicFuntion.judgeStop(5)) {
                        if (frequencyMap("stop", 1)) {
                            LtLog.e(mFairy.getLineInfo("到达目的地"));
                            gamePublicFuntion.sildeArchitecture("jys.png");
                            setTaskName(3);
                            return;
                        }

                    } else {
                        frequencyInit("stop");
                        err = 0;
                    }
                }

                Thread.sleep(1000);
                timeCount(8, 0);
            }

            void content_03() throws Exception {

                result = mFairy.findPic("ps10.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(869, 683, 878, 692, "【卖出结算界面】", 1000);
                    buy = true;

                    if (mFairy.dateHour() % 2 == 1 && mFairy.dateMinute() >= 10 && mFairy.dateMinute() <= 30) {
                        gamePublicFuntion.init(false);
                        while (mFairy.condit()) {

                            if (timeMap("huowu", 10000, true)) {
                                LtLog.e(mFairy.getLineInfo("等待货物刷新"));
                            }

                            if (mFairy.dateMinute() > 30) {
                                po = null;
                                buy = true;
                                initOnceJudge("opscs");
                                break;
                            }
                        }
                    }
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(new String[]{"jys1.png", "jys3.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    if (buy) {
                        result = mFairy.findPic(1023, 399, 1208, 683, "jy1.png");
                        mFairy.onTap(0.8f, result, "买入", 1000);
                    } else {
                        result = mFairy.findPic(1023, 399, 1208, 683, "jy2.png");
                        mFairy.onTap(0.8f, result, "卖出", 1000);
                    }
                }

                result = mFairy.findPic("jys2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    FindResult m = mFairy.findPic(878, 592, 1259, 694, "ps6.png");
                    if (m.sim > 0.8f) {

                        if (po == null) {

                            mFairy.onTap(188, 674, 212, 685, "最大", 1000);


                            result = mFairy.findPic(312, 144, 827, 525, "ps16.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "发现补货", 1500);

                                if (frequencyMap("buhuo", 7)) {
                                    setTaskEnd();
                                    return;
                                }

                                result = mFairy.findPic(657, 141, 1000, 558, "ps7.png");
                                mFairy.onTap(0.8f, result, "使用", 300);
                                mFairy.onTap(0.8f, result, 905, 684, 927, 697, "", 200);
                                return;
                            } else {
                                frequencyInit("buhuo");
                            }

                            /**
                             * 匹配货物
                             */

                            ArrayList<int[]> l = new ArrayList();
                            l.add(new int[]{334, 295, 475, 341});
                            l.add(new int[]{503, 292, 657, 342});
                            l.add(new int[]{683, 290, 826, 342});
                            l.add(new int[]{330, 515, 474, 566});
                            l.add(new int[]{500, 515, 656, 565});
                            l.add(new int[]{683, 513, 830, 564});


                            LtLog.e(mFairy.getLineInfo("【开始识别：" + tradeLabel.name + "】"));

                            pphw:
                            for (int i = 0; i < l.size(); i++) {

                                int[] in = l.get(i);

                                result = mFairy.findPic("ps15.png");
                                mFairy.onTap(0.8f, result, 494, 589, 526, 599, "返回买入界面", 1000);

                                result = mFairy.findPic(in[0], in[1], in[2], in[3], "ps14.png");
                                if (result.sim > 0.8f) {
                                    mFairy.touchDown(in[0] + 60, in[1] - 80);
                                    Thread.sleep(3000);
                                    mFairy.touchUp();

                                    result = mFairy.findPic("ps15.png");
                                    if (result.sim > 0.8f) {
                                        Thread.sleep(500);
                                        String name = gamePublicFuntion.getPictureText(537, 67, 208, 39);
                                        LtLog.e(mFairy.getLineInfo("匹配到：" + name));
                                        if (name.equals(tradeLabel.name)) {
                                            mFairy.onTap(484, 588, 522, 599, "", 500);
                                            mFairy.onTap(484, 588, 522, 599, "", 500);
                                            po = new int[]{in[0] + 60, in[1] - 80};
                                            mFairy.onTap(265, 672, 287, 684, "批量", 1000);
                                            break pphw;
                                        } else {
                                            mFairy.onTap(484, 588, 522, 599, "取消", 1000);
                                        }
                                    }
                                }
                            }

                            if (po == null) {
                                LtLog.e(mFairy.getLineInfo("没有识别出来可以匹配的货物"));
                                setTaskName(0);
                                return;
                            }
                        } else {
                            mFairy.onTap(265, 672, 287, 684, "批量", 1000);
                        }

                        mFairy.onTap(po[0], po[1], po[0] + 1, po[1] + 1, "", 500);
                        mFairy.onTap(po[0], po[1], po[0] + 1, po[1] + 1, "点击匹配的货物", 800);

                        mFairy.onTap(0.8f, m, "", 1000);
                        mFairy.onTap(0.8f, m, "", 1000);
                        mFairy.onTap(0.8f, m, "确定买入", 1000);
                        buy = false;
                        setTaskName(0);

                       /* frequencyInit("mairuerr");

                        while (mFairy.condit()){

                            boolean bool =false;

                            result = mFairy.findPic(878, 592, 1259, 694, "ps6.png");
                            if (result.sim < 0.8f) {
                                if(frequencyMap("mairuerr",2)){
                                    return;
                                }
                            }


                            result = mFairy.findPic("ps15.png");
                            mFairy.onTap(0.8f,result,494,589,526,599,"返回买入界面",1000);

                            result = mFairy.findPic(862,476,1259,720,"ps13.png");
                            if(result.sim>0.8f){
                                for (int j = 0; j < 3; j++) {
                                    mFairy.onTap(940,82,960,87,"",500);
                                }
                                bool=true;
                            }

                            if (mFairy.getColorNum(749, 672, 759, 690, "52,99,131", 0.9f) > 100) {
                                bool=true;
                            }

                            if(bool){
                                mFairy.onTap(0.8f, m, "", 1000);
                                mFairy.onTap(0.8f, m, "", 1000);
                                mFairy.onTap(0.8f, m, "确定买入", 1000);
                                buy = false;
                                setTaskName(0);
                                return;
                            }

                            mFairy.onTap(po[0],po[1],po[0]+1,po[1]+1,"点击匹配的货物",1000);

                            result = mFairy.findPic(657, 141, 1000, 558, "ps7.png");
                            mFairy.onTap(0.8f, result, "使用", 300);
                            mFairy.onTap(0.8f, result, 905, 684, 927, 697, "", 200);
                        }*/

                        return;
                    }

                    FindResult w = mFairy.findPic(878, 592, 1259, 694, "ps8.png");
                    if (w.sim > 0.8f) {

                        result = mFairy.findPic("ps9.png");
                        mFairy.onTap(0.8f, result, "全卖", 2000);

                        mFairy.onTap(0.8f, w, "确定卖出", 500);
                        if (mapCount(0.8f, 518, 243, 706, 591, "ps11.png")) {
                            buy = true;
                            if (mFairy.dateHour() % 2 == 1 && mFairy.dateMinute() >= 10 && mFairy.dateMinute() <= 30) {
                                gamePublicFuntion.init(false);

                                while (mFairy.condit()) {

                                    if (timeMap("huowu", 10000, true)) {
                                        LtLog.e(mFairy.getLineInfo("等待货物刷新"));
                                    }

                                    if (mFairy.dateMinute() > 30) {
                                        po = null;
                                        buy = true;
                                        initOnceJudge("opscs");
                                        break;
                                    }
                                }
                            }
                            setTaskName(0);
                            return;
                        }
                        return;
                    }
                }

                timeCount(5, 0);
            }
        };
    }//跑商

    public void ktgps() throws Exception {
        new single(mFairy, "开拓港跑商") {

            int psc = 0;
            boolean buy;

            void create() throws Exception {
                super.create();
                buy = true;
                tuidui();
                TaskMain.XUNHANG = false;
                setTaskName(5);//开始先执行05 判断自身的国家 (只执行一次)
                gamePublicFuntion.init(true);
            }

            void init() throws Exception {
                gamePublicFuntion.init(true);
                setTaskName(1);
            }

            void inOperation() throws Exception {
                super.inOperation();
                gamePublicFuntion.chat();
            }

            void content_01() throws Exception {

                if (buy) {//买
                    switch (psc) {
                        case 1:
                            map(false, "ld.png");//伦敦
                            break;
                        case 2:
                            map(false, "lsb.png", 834, 464);//塞维利亚
                            break;
                        case 3:
                            map(false, "ms.png");//马赛
                            break;
                        case 4:
                            map(false, "amstd.png");//阿姆斯特丹
                            break;
                        case 5:
                            map(false, "lsb.png");//里斯本
                            break;
                    }

                } else {//卖

                    switch (psc) {
                        case 1:
                            map(false, "km.png");// 伦敦 - 开曼
                            break;
                        case 2:
                            map(false, "tlxl.png");//塞维利亚-特鲁希略
                            break;
                        case 3:
                            map(false, "ky.png");//马赛 - 卡宴
                            break;
                        case 4:
                            map(false, "wlstd.png");//阿姆斯特丹 - 威廉斯塔德
                            break;
                        case 5:
                            map(false, "bl.png");//里斯本 - 贝伦
                            break;
                    }
                }

                gamePublicFuntion.sildeArchitecture("jys.png");

                setTaskName(2);
            }

            void content_02() throws Exception {


                result = mFairy.findPic("ps10.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(869, 683, 878, 692, "【卖出结算界面】", 1000);
                    if (buy) {
                        mFairy.onTap(54, 122, 85, 133, "买入", 1500);
                    } else {
                        buy = true;
                        gamePublicFuntion.init(true);
                        gamePublicFuntion.sildeArchitecture("lss.png");
                        setTaskName(3);
                    }
                    return;
                }

                result = mFairy.findPic(new String[]{"jys1.png", "jys3.png"});
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(1023, 399, 1208, 683, "jy2.png");
                    mFairy.onTap(0.8f, result, "卖出", 1000);


                    /*if (buy) {
                        result = mFairy.findPic(1023, 399, 1208, 683, "jy1.png");
                        mFairy.onTap(0.8f, result, "买入", 1000);
                    } else {

                    }*/
                }

                result = mFairy.findPic("jys2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    FindResult m = mFairy.findPic(878, 592, 1259, 694, "ps6.png");
                    if (m.sim > 0.8f) {


                        for (int i = 0; i < 6; i++) {

                            switch (i) {
                                case 0:
                                    mFairy.onTap(378, 201, 391, 221, "", 1000);
                                    break;
                                case 1:
                                    mFairy.onTap(565, 204, 589, 226, "", 1000);
                                    break;
                                case 2:
                                    mFairy.onTap(741, 203, 759, 219, "", 1000);
                                    break;
                                case 3:
                                    mFairy.onTap(379, 422, 396, 439, "", 1000);
                                    break;
                                case 4:
                                    mFairy.onTap(564, 424, 582, 440, "", 1000);
                                    break;
                                case 5:
                                    mFairy.onTap(745, 428, 760, 440, "", 1000);
                                    break;
                            }

                            result = mFairy.findPic(775,280,945,448,"ktg.png");
                            mFairy.onTap(0.8f,result,"使用",1000);
                            mFairy.onTap(0.8f,result,904,673,913,691,"",500);
                        }

                        mFairy.onTap(0.8f, m, "", 1000);
                        mFairy.onTap(0.8f, m, "", 1000);
                        mFairy.onTap(0.8f, m, "确定买入", 1000);
                        buy = false;
                        setTaskName(0);
                        return;
                    }

                    FindResult w = mFairy.findPic(878, 592, 1259, 694, "ps8.png");
                    if (w.sim > 0.8f) {

                        result = mFairy.findPic("ps9.png");
                        mFairy.onTap(0.8f, result, "全卖", 2000);

                        mFairy.onTap(0.8f, w, "确定卖出", 500);
                        if (mapCount(0.8f, 518, 243, 706, 591, "ps11.png")) {
                            if (buy) {
                                mFairy.onTap(54, 122, 85, 133, "买入", 1500);
                            } else {
                                buy = true;
                                setTaskName(3);
                                gamePublicFuntion.init(true);
                                gamePublicFuntion.sildeArchitecture("lss.png");
                                return;
                            }
                        }
                        return;
                    }
                }

                timeCount(5, 0);
            }

            void content_03() throws Exception {

                result = mFairy.findPic("lss1.png");
                mFairy.onTap(0.8f, result, "特殊产物", 1000);

                result = mFairy.findPic("lss2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "兑换", 1000);
                    setTaskName(0);
                    return;
                }

                if (timeCount(10, 3)) {
                    gamePublicFuntion.init(true);
                    gamePublicFuntion.sildeArchitecture("lss.png");
                }
            }

            void content_05() throws Exception {

                result = mFairy.findPic("zhujue.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("t1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1225, 14, 1249, 31, "", 1000);
                        rny("tp2.png", "tp1.png");//伦敦
                        gamePublicFuntion.sildeArchitecture("jys.png");
                        psc = 1;
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic("t2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1225, 14, 1249, 31, "", 1000);
                        rny("tp4.png", "tp3.png");//塞维利亚
                        gamePublicFuntion.sildeArchitecture("jys.png");
                        psc = 2;
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic("t3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1225, 14, 1249, 31, "", 1000);
                        rny("tp6.png", "tp5.png");//马赛
                        gamePublicFuntion.sildeArchitecture("jys.png");
                        psc = 3;
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic("t4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1225, 14, 1249, 31, "", 1000);
                        rny("tp8.png", "tp7.png");//阿姆斯特丹
                        gamePublicFuntion.sildeArchitecture("jys.png");
                        psc = 4;
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic("t5.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1225, 14, 1249, 31, "", 1000);
                        rny("tp10.png", "tp9.png");//里斯本
                        gamePublicFuntion.sildeArchitecture("jys.png");
                        psc = 5;
                        setTaskName(2);
                        return;
                    }
                } else {
                    result = mFairy.findPic("package.png");
                    mFairy.onTap(0.8f, result, 47, 52, 62, 68, "点击头像", 3000);
                }


                if (timeCount(10, 5)) {
                    gamePublicFuntion.init(true);
                }
            }
        };


    }//开拓港跑商


    public void test() throws Exception {
        new single(mFairy, "test") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                setSign("test");

                setTaskEnd();
                return;
            }
        };
    }//test

    /**
     * 跟队副本
     */
    public void gdfb(final String str) throws Exception {
        new single(mFairy, "跟队副本:" + str) {

            void create() throws Exception {
                super.create();
                frequencyInit("actErr");
                GamePublicFuntion.genhujiu = false;

            }

            void content_01() throws Exception {
                activity(2, str);
            }

            boolean cancel() throws Exception {

                result = mFairy.findPic("gen2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(737, 469, 771, 478, "", 500);
                    return true;
                }
                return false;
            }

            boolean activityJuage(FindResult act) throws Exception {
                if (str.equals("bf.png") || str.equals("jw.png")) {
                    result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "wancheng.png");
                    if (result.sim > 0.88f) {
                        LtLog.e(mFairy.getLineInfo("活动已完成"));
                        setTaskEnd();
                        return true;
                    }
                    mFairy.onTap(1242, 11, 1259, 30, "", 500);
                    setTaskName(2);
                    return true;
                } else {
                    result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "can.png");
                    if (result.sim > 0.88f) {
                        mFairy.onTap(1242, 11, 1259, 30, "", 500);
                        pipei = false;
                        setTaskName(2);
                        return true;
                    }
                    return false;
                }
            }

            void content_02() throws Exception {
                rank("xljd1.png", "xljd6.png");
            }

            void content_03() throws Exception {

                result = mFairy.findPic(93, 563, 309, 684, "gen1.png");
                if (result.sim > 0.72f) {
                    err = 0;
                }

                result =  rangeFindPic(5,"dui.png");
                if (result.sim > 0.8f) {
                    tuidui();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(41, 28, 92, 79, new String[]{"zanli1.png", "zanli2.png", "zanli3.png", "zanli4.png"});
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(new String[]{"rank7.png", "rank24.png"});
                mFairy.onTap(0.8f, result, 1236, 16, 1260, 28, "关掉组队界面", 1000);

                if (timeMap("timefb", 600000, false)) {
                    tuidui();
                    setTaskName(0);
                    return;
                }


                result = mFairy.findPic(257, 12, 903, 103, "rank4.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("【无队状态】"));
                    setTaskName(0);
                    return;
                }

                Thread.sleep(1000);

                timeCount(30, 0);
            }
        };

    }//跟队副本


}




