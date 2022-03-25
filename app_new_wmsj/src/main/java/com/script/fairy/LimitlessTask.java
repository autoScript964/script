package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;



public class LimitlessTask extends Task {//无限任务
    private int ww = 0, wwType = 1;
    private int battleFailure = 0;
    private int activity = 0;
    private int can = 0;
    private long ranksTime = System.currentTimeMillis();
    private long time = System.currentTimeMillis();
    private int infinite = 0;
    private int wxzx = 2;//五行之序
    private int kjdt = 0;
    private int ranksErr = 0;
    private int wwCount = 0;
    private int gs = 0;
    private int bpss = 0;
    private int s = 0;
    private boolean bools = false;

    /**
     * 限时任务
     */
    private int go1 = 0, go2 = 0, go3 = 0, go4 = 0, go5 = 0, go6 = 0,
            go7 = 0, go8 = 0, go9 = 0, go10 = 0, go11 = 0, go12 = 0, go13 = 0, go14 = 0, go15 = 0;

    public LimitlessTask(AtFairyImpl ypFairy) throws Exception {
        super(ypFairy);
        init();
    }

    private void init() throws Exception {

        if (AtFairyConfig.getTaskID().equals("1438")) {

            list = new ArrayList<>();

            if (!AtFairyConfig.getOption("ww").equals("")) {
                list.add("ww");
                ww = Integer.parseInt(AtFairyConfig.getOption("ww"));
                if (ww <= 4) {
                    LtLog.e(mFairy.getLineInfo("【蟠龙旅】"));
                } else if (ww <= 8) {
                    LtLog.e(mFairy.getLineInfo("【中州商会】"));
                } else if (ww <= 12) {
                    LtLog.e(mFairy.getLineInfo("【建木之民】"));
                }
            }


            if (AtFairyConfig.getOption("dw").equals("1")) {
                wwType = 1;
            } else {
                wwType = 2;
            }

            if (!AtFairyConfig.getOption("wxzx").equals("")) {
                int num = Integer.parseInt(AtFairyConfig.getOption("wxzx"));
                wxzx = num + 1;
            }

            battleFailureCount = 0;
            mainScene = 18;
            ranks = 0;
            unexecuted = 0;
            supplement = 0;
            setUp = 0;
            err = 10;

            if (AtFairyConfig.getOption("pl").equals("1")) {
                LtLog.e(mFairy.getLineInfo("【勾选清理背包】"));
                pl = 0;
            } else {
                pl = 1;
            }

            hong = 0;
            lan = 0;
            timeHong = System.currentTimeMillis() - 90000;
            timeLan = System.currentTimeMillis() - 90000;
            if (!AtFairyConfig.getOption("hong").equals("")) {
                hong = Integer.parseInt(AtFairyConfig.getOption("hong"));
                LtLog.e(mFairy.getLineInfo("【勾选加红:】" + hong));
            }
            if (!AtFairyConfig.getOption("lan").equals("")) {
                lan = Integer.parseInt(AtFairyConfig.getOption("lan"));
                LtLog.e(mFairy.getLineInfo("【勾选加蓝:】" + lan));
            }

            if (AtFairyConfig.getOption("gua").equals("1")) {
                ranks = 0;
                mainScene = 0;
                bools = true;
            }


        }
        if (AtFairyConfig.getTaskID().equals("1464")) {
            list = new ArrayList<>();
            list.add("gd");
            battleFailureCount = 0;
            unexecuted = 0;
            supplement = 1;
            setUp = 1;
            mainScene = 0;
            ranks = 0;
            err = 0;

            hong = 0;
            lan = 0;
            timeHong = System.currentTimeMillis() - 90000;
            timeLan = System.currentTimeMillis() - 90000;
            if (!AtFairyConfig.getOption("hong").equals("")) {
                hong = Integer.parseInt(AtFairyConfig.getOption("hong"));
                LtLog.e(mFairy.getLineInfo("【勾选加红:】" + hong));
            }
            if (!AtFairyConfig.getOption("lan").equals("")) {
                lan = Integer.parseInt(AtFairyConfig.getOption("lan"));
                LtLog.e(mFairy.getLineInfo("【勾选加蓝:】" + lan));
            }
        }

        if (AtFairyConfig.getTaskID().equals("1500")) {
            list = new ArrayList<>();
            list.add("ct");
            battleFailureCount = 0;
            unexecuted = 0;
            supplement = 0;
            setUp = 0;
            ranks = 10;
            mainScene = 0;
            err = 0;

            hong = 0;
            lan = 0;
            timeHong = System.currentTimeMillis() - 90000;
            timeLan = System.currentTimeMillis() - 90000;
            if (!AtFairyConfig.getOption("hong").equals("")) {
                hong = Integer.parseInt(AtFairyConfig.getOption("hong"));
                LtLog.e(mFairy.getLineInfo("【勾选加红:】" + hong));
            }
            if (!AtFairyConfig.getOption("lan").equals("")) {
                lan = Integer.parseInt(AtFairyConfig.getOption("lan"));
                LtLog.e(mFairy.getLineInfo("【勾选加蓝:】" + lan));
            }

        }

    }

    public void unexecuted() throws Exception {
        /***
         *  task
         */
        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        switch (list.get(0)) {
            case "ylsg":
                result = mFairy.findPic("ylsg1.png");
                if (result.sim > 0.8f) {
                    minute = mFairy.dateMinute();
                    if (minute < 15) {
                        for (int i = 0; i < 10; i++) {
                            result = mFairy.findPic("ylsg2.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "第一场报名", 1000);
                            } else {
                                break;
                            }
                            
                        }
                    } else if (minute < 30) {
                        for (int i = 0; i < 10; i++) {
                            result = mFairy.findPic("ylsg3.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "第二场报名", 1000);
                            } else {
                                break;
                            }
                            
                        }
                    } else if (minute < 45) {
                        for (int i = 0; i < 10; i++) {
                            result = mFairy.findPic("ylsg4.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "第三场报名", 1000);
                            } else {
                                break;
                            }
                            
                        }
                    }
                    gamePublicFuntion.close();
                }

                result = mFairy.findPic("ylsg7.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "逸灵山谷,【完成】", 1000);
                    list.remove(0);
                    go1 = 1;
                    gamePublicFuntion.EndTask(this);
                    initPrestige();
                }
                break;
            case "kjdt":
                result = mFairy.findPic("kjdt2.png");
                mFairy.onTap(0.8f, result, "饮食", 1000);
                break;
            case "bpls":
                result = mFairy.findPic(993, 312, 1177, 644, "bpls1.png");
                mFairy.onTap(0.8f, result, "联赛报名", 1000);

                result = mFairy.findPic("bpls2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "自动匹配", 1000);
                } else {
                    result = mFairy.findPic("bpls7.png");
                    mFairy.onTap(0.8f, result, 847, 232, 863, 250, "关闭联赛匹配界面", 1000);
                }
                break;

            case "gdc":
                result = mFairy.findPic(1014, 377, 1183, 704, "gdc2.png");
                mFairy.onTap(0.8f, result, "单人报名", 1000);
                break;
            case "xwj":
                result = mFairy.findPic("bangpai.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("没有加入帮派不能执行此任务,End!"));
                    setUpGo();
                    list.remove(0);
                    initPrestige();
                    gamePublicFuntion.EndTask(this);
                    mainScene = 15;

                    return;
                }
                break;
            case "xxbzl":
                result = mFairy.findPic("bangpai.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("没有加入帮派不能执行此任务,End!"));
                    setUpGo();
                    list.remove(0);
                    gamePublicFuntion.EndTask(this);
                    initPrestige();
                    mainScene = 15;
                    return;
                }
                break;
            case "bpss":
                result = mFairy.findPic("bangpai.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("没有加入帮派不能执行此任务,End!"));
                    setUpGo();
                    list.remove(0);
                    gamePublicFuntion.EndTask(this);
                    initPrestige();
                    mainScene = 15;
                    return;
                }
                result = mFairy.findPic(983, 368, 1208, 658, "bpss1.png");
                mFairy.onTap(0.8f, result, "前往收取贡品", 1000);


                result = mFairy.findPic("bpss2.png");
                mFairy.onTap(0.8f, result, "跳过", 1000);


                result = mFairy.findPic(896, 436, 1275, 715, "likaifb.png");
                if (result.sim > 0.8f) {
                    mainScene = 15;
                    return;
                }

                break;
            case "xld":
                result = mFairy.findPic("xld4.png");
                if (result.sim > 0.8f) {
                    setUpGo();
                    list.remove(0);
                    gamePublicFuntion.EndTask(this);
                    initPrestige();
                    mainScene = 15;
                    return;
                }
                break;
        }

        super.unexecutedTask();

    }//未执行场景

    public void mainScene() throws Exception {

        result = mFairy.findPic(1107, 228, 1212, 335, new String[]{"home.png", "home2.png","bao.png"});
        Task.task_err(0.7f,result);
        if (result.sim > 0.7f) {
            LtLog.e(mFairy.getLineInfo("【主场景>>>" + gamePublicFuntion.getTime()) + "  mainScene:" + mainScene + "】");

            if (gamePublicFuntion.EndTask(this)) {
                return;
            }
            result = mFairy.findPic(553, 16, 929, 85, "right1.png");
            mFairy.onTap(0.7f, result, "关闭右侧缩放栏", 1000);
            /**
             * Task
             */
            if (super.mainSceneTask()) {
                return;
            }

            /**
             *  mainScene未操作超时时间*/
            if (gamePublicFuntion.getTime() > 30) {
                mainScene = 10;
                infinite = 99;
                gamePublicFuntion.initTime();
            }

            /**
             *  点击>>>activity  */
            if (mainScene > 9) {
                super.unexecutedTask();

                if (mainScene == 0) {
                    return;
                }

                gamePublicFuntion.start();

                result = mFairy.findPic("zhaoji.png");
                mFairy.onTap(0.8f, result, "召集", 1000);

                result = mFairy.findPic(698, 14, 1092, 191, "a.png");
                mFairy.onTap(0.8f, result, "活动", 3000);
                return;
            }
        } else {
            gamePublicFuntion.initTime();
            return;
        }

        /**
         *  状态
         */
        switch (list.get(0)) {
            case "ww":
                if (bools) {
                    mainScene = 0;
                    gamePublicFuntion.initTime();
                } else {
                    if (wwCount < 10) {
                        result = mFairy.findPic("ranks24.png");
                        mFairy.onTap(0.8f, result, "跟随", 1000);
                    } else {
                        result = mFairy.findPic("tuoli.png");
                        mFairy.onTap(0.8f, result, "脱离", 1000);
                    }

                    result = mFairy.findPic(591, 45, 762, 204, "ww1.png");
                    if (result.sim > 0.8f) {
                        ranksErr = 0;
                        wwCount++;
                        gamePublicFuntion.initTime();
                    }

                    if (System.currentTimeMillis() - ranksTime > 300000) {
                        ranks = 8;
                        ranksTime = System.currentTimeMillis();
                    }

                    if (wwType == 1) {
                        gamePublicFuntion.zhTime(300);
                    }
                }
                break;
            case "xld":
                result = mFairy.findPic(929, 80, 1062, 143, "xld3.png");
                if (result.sim > 0.8f) {
                    gamePublicFuntion.initTime();
                }

                result = mFairy.findPic("xld4.png");
                if (result.sim > 0.8f) {
                    setUpGo();
                    list.remove(0);
                    gamePublicFuntion.EndTask(this);
                    initPrestige();
                    mainScene = 15;
                    return;
                }
                break;

        }

        if (wwType == 3) {
            if (gs == 0) {
                for (int i = 0; i < 3; i++) {
                    result = mFairy.findPic(921, 68, 1090, 164, new String[]{"fb.png", "fb1.png"});
                    if (result.sim < 0.8f) {

                        result = mFairy.findPic("ranks24.png");
                        mFairy.onTap(0.8f, result, "跟随", 1000);
                    }
                    
                }
                gs = 1;
            }

            result = mFairy.findPic(0, 206, 45, 359, "ranks11.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.85f, result, "队伍", 3000);

                result = mFairy.findPic(new String[]{"ranks.png", "ranks0.png"});
                if (result.sim > 0.8f) {
                    ranks = 8;
                    return;
                }
            }
        }

        /**
         *   副本  */
        result = mFairy.findPic(921, 68, 1090, 164, new String[]{"fb.png", "fb1.png"});
        LtLog.e(mFairy.getLineInfo("副本：" + result.sim));
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【副本场景】"));
            gamePublicFuntion.initTime();

            super.autoUse();

            if (list.get(0).equals("bpss")) {
                bpss++;
                if (bpss == 5) {
                    mFairy.ranSwipe(136, 411, 180, 578, 2, 8000, (long)2000);

                    mFairy.onTap(1138, 577, 1177, 613, "A", 1000);
                    mFairy.onTap(1036, 654, 1067, 684, "放网子", 1000);

                    result = mFairy.findPic(396, 4, 547, 80, new String[]{"boss.png", "boss1.png"});
                    if (result.sim < 0.85f) {
                        bpss = 0;
                    }
                }

                if (bpss > 5) {
                    for (int i = 0; i < 5; i++) {
                        mFairy.onTap(1138, 577, 1177, 613, "A", 500);
                        mFairy.onTap(1036, 654, 1067, 684, "放网子", 500);
                    }
                }
            }
        }

        result = mFairy.findPic(555, 122, 706, 166, "gen.png");
        if (result.sim < 0.8f) {
            result = mFairy.findPic(492, 547, 620, 629, "auto.png");
            mFairy.onTap(0.92f, result, "自动战斗", 1000);
        }

        /***
         *  任务分类 */
        switch (list.get(0)) {
            case "ylsg":
                result = mFairy.findPic(726, 193, 960, 478, "ylsg5.png");
                mFairy.onTap(0.8f, result, "进入战场", 1000);

                result = mFairy.findPic("ylsg6.png");
                mFairy.onTap(0.8f, result, "石阵", 3000);
                break;
            case "bpls":
                result = mFairy.findPic(new String[]{"bpls4.png", "bpls5.png"});
                if (result.sim > 0.8f) {
                    gamePublicFuntion.initTime();
                }
                break;
            case "xmj":
                result = mFairy.findPic(414, 2, 525, 87, "xmj1.png");
                if (result.sim > 0.8f) {
                    gamePublicFuntion.initTime();
                }
                break;
            case "gdc":
                result = mFairy.findPic(1002, 78, 1103, 152, "gdc3.png");
                if (result.sim > 0.8f) {
                    gamePublicFuntion.initTime();
                }
                result = mFairy.findPic(865, 70, 1085, 164, "gdc1.png");
                mFairy.onTap(0.8f, result, "点击匹配", 1000);
                break;
            case "xwj":
                result = mFairy.findPic(414, 2, 525, 87, "xmj1.png");
                if (result.sim > 0.8f) {
                    gamePublicFuntion.initTime();
                }
                break;
            case "xxbzl":
                result = mFairy.findPic(1101, 1, 1161, 33, "ssbzl1.png");
                if (result.sim > 0.8f) {
                    gamePublicFuntion.initTime();
                }
                break;
        }
        gamePublicFuntion.close();

    }//主场景

    public void ranks() throws Exception {
        result = mFairy.findPic(new String[]{"ranks.png", "ranks0.png"});
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【组队场景】"));
            gamePublicFuntion.initTime();

            result = mFairy.findPic("ranks20.png");
            mFairy.onTap(0.8f, result, 955, 108, 972, 122, "关闭", 1000);

            if (ranks == 0) {
                mFairy.onTap(1179, 70, 1201, 91, "关闭", 1000);
                return;
            }

            result = mFairy.findPic("ranksmb.png");
            if (result.sim > 0.8f) {
                return;
            }

        } else {
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        result = mFairy.findPic("ranks20.png");
        mFairy.onTap(0.8f, result, 955, 108, 972, 122, "关闭", 1000);

        result = mFairy.findPic(1105,395,1224,715,"ranks6.png");
        mFairy.onTap(0.8f, result, "我的队伍", 2000);

        LtLog.e(mFairy.getLineInfo("wwType : " + wwType));

        switch (wwType) {
            case 1:
                result = mFairy.findPic("ranks12.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "退出团队", 1500);
                    mFairy.onTap(721, 466, 761, 488, "确定退出", 1000);
                }

                result = mFairy.findPic("ranks1.png");
                mFairy.onTap(0.8f, result, "创建队伍", 1000);

                result = mFairy.findPic(new String[]{"ranks3.png", "ranks4.png"});
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("ranks5.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "退出队伍", 2000);
                        mFairy.onTap(725, 471, 749, 487, "确定", 1000);
                    }
                }

                result = mFairy.findPic(428, 308, 868, 466, "ranks9.png");
                mFairy.onTap(0.8f, result, 720, 469, 748, 487, "同意", 1000);


                /***
                 *  目标 */
                result = mFairy.findPic("ranks13.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(241, 530, 1110, 577, "ranks14.png");
                    if (result.sim > 0.93f) {
                        mFairy.onTap(0.93f, result, "发现离线队员", 2000);
                        ranks = 8;
                        result = mFairy.findPic(456, 378, 1244, 588, "ranks17.png");
                        mFairy.onTap(0.8f, result, "请离队伍", 1000);
                    }

                    FindResult resultRanks = null;
                    switch (list.get(0)) {
                        case "ww":
                            if (ww <= 4) {
                                resultRanks = mFairy.findPic("wwone2.png");
                            } else if (ww <= 8) {
                                resultRanks = mFairy.findPic("wwtwo2.png");
                            } else if (ww <= 12) {
                                resultRanks = mFairy.findPic("wwthree2.png");
                            }
                            break;
                    }
                    if (resultRanks != null) {
                        if (resultRanks.sim < 0.8f) {
                            mFairy.onTap(280, 119, 321, 134, "目标不匹配,重新匹配", 1000);
                            return;
                        }
                    }

                    result = mFairy.findPic(704, 549, 982, 689, "ranks8.png");
                    mFairy.onTap(0.8f, result, "自动匹配", 1000);
                    ranks++;
                }

                break;
            case 2:
                if (ranksErr > 2) {
                    wwType = 4;
                    return;
                }
                result = mFairy.findPic(new String[]{"ranks3.png", "ranks4.png", "ranks21.png", "ranks23.png"});
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("ranks5.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "退出队伍", 2000);
                        mFairy.onTap(725, 471, 749, 487, "确定", 1000);
                    }
                } else {
                    mFairy.onTap(1148, 454, 1185, 484, "附近团队", 1500);

                    result = mFairy.findPic("shenqing.png");
                    mFairy.onTap(0.8f, result, 999, 642, 1034, 658, "一键申请", 1000);

                    ranks++;
                }
                break;
            case 3:
                switch (list.get(0)) {
                    case "xxb":
                        ranksGdTools("mb1.png", new String[]{"xxb2.png"}, "xxb3.png");
                        break;
                    /*case "xxbzl":
                        ranksGdTools("mb1.png", "xxb2.png", "xxb3.png");
                        break;*/
                    case "wxzx":
                        ranksGdTools("wxzx1.png", new String[]{"wxzx" + wxzx + ".png"}, "wxzx99.png");
                        break;
                    case "zmel":
                        ranksGdTools("richang.png", new String[]{"zmel1.png","zmel3.png"}, "zmel2.png");
                        break;
                    default:

                        break;
                }
                break;
            case 4:
                result = mFairy.findPic("ranks5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "退出队伍", 2000);
                    mFairy.onTap(725, 471, 749, 487, "确定", 1000);
                } else {
                    ranks++;
                }
                break;
        }

        if (ranks > 9) {
            LtLog.e(mFairy.getLineInfo("达到要求开始任务"));
            mFairy.onTap(1179, 70, 1201, 91, "关闭", 1000);
            ranks = 0;
            wwCount = 0;
            ranksErr++;
        }
    }//组队

    public void ranksGdTools(String type, String []str, String str1) throws Exception {

        result = mFairy.findPic(1105,395,1224,715,"ranks6.png");
        mFairy.onTap(0.8f, result, "我的队伍", 3000);

        result = mFairy.findPic("ranks26.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("组队平台场景"));

            if (System.currentTimeMillis() - time > 600000) {
                list.remove(0);
                gamePublicFuntion.EndTask(this);
                initPrestige();
            }
            /**
             *  匹配跟队目标 */
            if (ranksGdTools2(type, str)) {
                LtLog.e(mFairy.getLineInfo("没有找到跟队目标0.0"));
                return;
            }
            result = mFairy.findPic(704, 549, 982, 689, "ranks8.png");
            mFairy.onTap(0.8f, result, "自动匹配", 1000);

            result = mFairy.findPic(936, 112, 1060, 605, "ranks25.png");
            mFairy.onTap(0.8f, result, "申请入队", 1000);

            gs = 0;
        }


        result = mFairy.findPic("ranks.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("目标队伍场景"));

            result = mFairy.findPic(new String[]{"ranks3.png", "ranks4.png"});
            if (result.sim > 0.8f) {
                result = mFairy.findPic(str1);
                if (result.sim > 0.8f) {
                    ranks = 0;
                    time = System.currentTimeMillis();
                    return;
                }
            }

            result = mFairy.findPic("ranks5.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "退出队伍", 2000);
                mFairy.onTap(725, 471, 749, 487, "确定", 1000);
            }
        }
    }//跟队工具

    public boolean ranksGdTools2(String type, String []str) throws Exception {

        for (int i = 0; i < 6; i++) {
            result = mFairy.findPic(118, 123, 295, 682, str);
            if (result.sim > 0.75f) {
                mFairy.onTap(0.75f, result, "找到目标>>>", 1000);
                return false;
            }else{
                result = mFairy.findPic(118, 123, 295, 682, type);
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "目标分类", 1500);
                    i = 0;
                }
            }

            if (i == 1) {
                LtLog.e(mFairy.getLineInfo("初始化匹配目标"));
                for (int j = 0; j < 3; j++) {
                    mFairy.ranSwipe(209, 227, 264, 548, 0, 500, (long)100);
                }
                mFairy.sleep(1500);
                
            }

            if (i % 2 == 0 && i != 0) {
                mFairy.ranSwipe(209, 227, 264, 548, 2, 1000, (long)3000);
            }
        }
        return true;
    }//

    public void ranksmb() throws Exception {
        result = mFairy.findPic("ranksmb.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【组队目标】"));
            gamePublicFuntion.initTime();
        } else {
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }
        switch (list.get(0)) {

            case "ww":
                if (ww <= 4) {
                    ranksmbTools("ranksmb3.png", "wwone1.png");
                } else if (ww <= 8) {
                    ranksmbTools("ranksmb3.png", "wwtwo1.png");
                } else if (ww <= 12) {
                    ranksmbTools("ranksmb3.png", "wwthree1.png");
                }
                break;
        }

    }//组队目标

    public void activity() throws Exception {

        result = mFairy.findPic("activity.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【活动场景】"));

            result = mFairy.findPic(586, 185, 696, 235, "xld2.png");
            if (result.sim > 0.85f) {
                activity = 0;
                result = mFairy.findPic(568, 469, 738, 545, "xld1.png");
                mFairy.onTap(0.8f, result, "报名", 1000);
                return;
            }
            unexecuted = 0;
            battleFailureCount = 0;
            gamePublicFuntion.initTime();
            gamePublicFuntion.bossTimeInit();
            activity++;
            mainScene = 0;

            result = mFairy.findPic("activity1.png");
            mFairy.onTap(0.8f, result, 1001, 101, 1020, 123, "关闭活动介绍", 1000);

            if (ranks == 8) {
                mFairy.onTap(1204, 71, 1225, 90, "关闭活动", 1000);
                activity = 0;
                return;
            }

            if (gamePublicFuntion.EndTask(this)) {
                return;
            }

            if (activity > 11) {
                LtLog.e("活动已经完成,或者未发现,移除当前任务。");
                setUpGo();
                list.remove(0);
                initPrestige();
                gamePublicFuntion.EndTask(this);
                activity = 0;
                return;
            }
        } else {
            activity = 0;
            return;
        }

        if (list.get(0).equals("ww")) {
            result = mFairy.findPic(1123, 218, 1204, 663, "ww2.png");
            mFairy.onTap(0.8f, result, "威望", 2000);
            activity = 0;
            return;
        }

        result1 = mFairy.findPic(96, 165, 1108, 490, list.get(0) + ".png");
        if (result1.sim > 0.8f) {
            result = mFairy.findPic(result1.x + 264, result1.y - 8, result1.x + 360, result1.y + 63, "can.png");
            if (result.sim > 0.75f) {
                LtLog.e(mFairy.getLineInfo("找到任务,开始执行>>>>>"));
                if (can == 1) {
                    mFairy.onTap(0.8f, result, "前往 :" + list.get(0), 2000);

                    if (list.get(0).equals("xld")) {
                        return;
                    }

                    if (list.get(0).equals("kjdt") || list.get(0).equals("kjdtzs")) {
                        kjdt++;
                        if (kjdt > 5) {
                            activity = 13;
                            return;
                        }
                    }

                } else {
                    ranks = 8;
                }
                activity = 0;
                return;
            }

            result = mFairy.findPic(result1.x + 264, result1.y - 8, result1.x + 352, result1.y + 63, "can1.png");
            if (result.sim > 0.8f) {
                activity = 13;
                return;
            }
        }
        if (activity == 1) {
            LtLog.e(mFairy.getLineInfo("活动任务初始化"));
            for (int i = 0; i < 6; i++) {
                mFairy.ranSwipe(637, 233, 684, 470, 0, 500, (long)200);
            }
            Thread.sleep(2000);
            
            return;
        }

        if (activity % 2 == 0) {
            mFairy.ranSwipe(637, 250, 684, 516, 2, 1000, (long)2000);
            LtLog.e(mFairy.getLineInfo("滑动"));
            return;
        }

    }

    public void cancel() throws Exception {
        result1 = mFairy.findPic(347, 312, 661, 634, "cancel.png");
        Task.task_err(0.8f,result1);
        if (result1.sim > 0.72f) {
            LtLog.e(mFairy.getLineInfo("【取消场景】"));
            gamePublicFuntion.initTime();
        } else {
            return;
        }


        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        result = mFairy.findPic(435, 336, 866, 442, "yq.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, 715, 466, 751, 483, "同意跟随", 1000);
            return;
        }

        result = mFairy.findPic("zlc.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, 725, 471, 754, 489, "确定传送", 1000);
            return;
        }

        result = mFairy.findPic("yuan1.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, 719, 467, 753, 488, "返回原服", 1000);
            return;
        }


        result = mFairy.findPic("ranks7.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "前往组队", 1000);
            ranks = 8;
            return;
        }

        mFairy.onTap(0.72f, result1, "取消", 1000);


    }//取消

    public void fbShiBai() throws Exception {
        result1 = mFairy.findPic(985, 47, 1101, 393, "fbEnd.png");
        Task.task_err(0.8f,result1);
        if (result1.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【副本结束】"));

            battleFailure = -1;
            if (fbEnd == 0) {
                battleFailureCount++;
                fbEnd = 1;
            }

        } else {
            fbEnd = 0;
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        result = mFairy.findPic("likai.png");
        if (result.sim > 0.8f) {
            unexecuted();
        }
        result = mFairy.findPic(890, 163, 1174, 649, new String[]{"fh.png", "fh1.png"});
        mFairy.onTap(0.8f, result, "返回复活点", 3000);

    }//副本结束

    public void wwScene() throws Exception {
        result = mFairy.findPic("wwScene.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【威望场景】"));
            gamePublicFuntion.initTime();
        } else {
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        String w = "";

        if (ww <= 4) {
            LtLog.e(mFairy.getLineInfo("【蟠龙旅】"));
            w = "wwone";
        } else if (ww <= 8) {
            LtLog.e(mFairy.getLineInfo("【中州商会】"));
            w = "wwtwo";
        } else if (ww <= 12) {
            LtLog.e(mFairy.getLineInfo("【建木之民】"));
            w = "wwthree";
        }

        result = mFairy.findPic(120, 94, 294, 675, w + ".png");
        mFairy.onTap(0.8f, result, "威望地图", 2000);

        switch (ww) {
            case 1:
                mFairy.onTap(555, 295, 567, 308, "士兵枯骨", 1500);
                break;
            case 2:
                mFairy.onTap(414, 415, 429, 431, "无名箭手", 1500);
                break;
            case 3:
                mFairy.onTap(679, 440, 692, 453, "无脑残躯", 1500);
                break;
            case 4:
                mFairy.onTap(542, 554, 559, 567, "海贼枯骨", 1500);
                break;
            case 5:
                mFairy.onTap(382, 335, 399, 351, "地鬼", 1500);
                break;
            case 6:
                mFairy.onTap(540, 300, 557, 314, "野人战士", 1500);
                break;
            case 7:
                mFairy.onTap(668, 271, 682, 285, "暴地源兽", 1500);
                break;
            case 8:
                mFairy.onTap(648, 453, 666, 468, "横行蟹", 1500);
                break;
            case 9:
                mFairy.onTap(418, 241, 432, 256, "荆蝎", 1500);
                break;
            case 10:
                mFairy.onTap(577, 319, 592, 333, "仙人刺球", 1500);
                break;
            case 11:
                mFairy.onTap(682, 352, 694, 365, "长蛮探子", 1500);
                break;
            case 12:
                mFairy.onTap(464, 554, 477, 566, "嚎啕蛊", 1500);
                break;
            default:
                gamePublicFuntion.close();
                break;
        }
        mFairy.onTap(1006, 632, 1040, 656, "前往活动", 6000);
        ranks = 8;
    }//威望

    public void dtScene() throws Exception {
        result = mFairy.findPic(498, 12, 779, 128, new String[]{"dtScene.png", "kjScene.png"});
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【答题场景】"));
            gamePublicFuntion.initTime();
        } else {
            return;
        }

        result = mFairy.findPic("answer1.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "答题活动结束,关闭页面", 1000);
            mainScene = 15;
            return;
        }

        result = mFairy.findPic("kjdt1.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "参加复试", 1000);
            return;
        }
        mFairy.onTap( 662, 527,665,530,"",3000);

    }//每日答题

    /**
     * 限时任务
     */
    public void limitedTime() throws Exception {
        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        boolean bool = false;

        if (list.get(0).equals("ww")) {
            bool = true;
        }

        /**逸灵山谷*/
        if (go1 == 0) {
            if (AtFairyConfig.getOption("ylsg").equals("1")) {
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((hour == 12 || hour == 21) && minute < 45) {
                    infinite = 0;
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【逸灵山谷】"));
                        list.add(0, "ylsg");
                        mainScene = 15;
                        can = 1;
                        if (AtFairyConfig.getOption("autos").equals("1")) {
                            supplement = 0;
                        }
                        return;
                    }
                } else {
                    if (list.get(0).equals("ylsg")) {
                        list.remove(0);
                        initPrestige();
                    }
                }
            }
        }

        /**每日答题*/
        if (go6 == 0) {
            if (AtFairyConfig.getOption("mrdt").equals("1")) {
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if (hour >= 18 && hour < 21) {
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【每日答题】"));
                        list.add(0, "mrdt");
                        super.answer();
                        mainScene = 15;
                        can = 1;
                        err = 10;
                        return;
                    }
                }
            }
        }

        /**科举答题*/
        if (go7 == 0) {
            if (AtFairyConfig.getOption("kjdt").equals("1")) {
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();

                if (week == 5 &&
                        (hour >= 4 && hour < 19)) {
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【科举答题】"));
                        list.add(0, "kjdt");
                        super.answer();
                        mainScene = 15;
                        kjdt = 0;
                        can = 1;
                        err = 10;
                        return;
                    }
                }
            }
        }

        /**科举答题(终试)*/
        if (go8 == 0) {
            if (AtFairyConfig.getOption("kjdt").equals("1")) {
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();

                if (week == 5 &&
                        (hour >= 20 && hour < 23)) {
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【科举答题】"));
                        list.add(0, "kjdtzs");
                        super.answer();
                        mainScene = 15;
                        kjdt = 0;
                        can = 1;
                        err = 10;
                        return;
                    }
                }
            }
        }

        /** 帮派联赛 */
        if (go9 == 0) {
            if (AtFairyConfig.getOption("bpls").equals("1")) {
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();

                if ((week == 3 || week == 4) &&
                        (hour >= 20) &&
                        (minute < 25)) {
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【帮派联赛】"));
                        list.add(0, "bpls");
                        mainScene = 15;
                        ranks = 8;
                        wwType = 4;
                        can = 1;
                        err = 10;
                        if (AtFairyConfig.getOption("autos").equals("1")) {
                            supplement = 0;
                        }
                        return;
                    }
                } else {
                    if (list.get(0).equals("bpls") && hour != 20) {
                        result = mFairy.findPic("err8.png");
                        mFairy.onTap(0.8f, result, 848, 233, 864, 250, "联盟联赛任务结束,", 1000);
                        list.remove(0);
                        go9 = 1;
                        initPrestige();
                    }
                }
            }
        }

        /** 仙魔劫 */
        if (go10 == 0) {
            if (AtFairyConfig.getOption("xmj").equals("1")) {
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();

                if ((week == 1 || week == 5) &&
                        (hour >= 19) &&
                        (minute >= 30 && minute < 40)) {
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【仙魔劫】"));
                        list.add(0, "xmj");
                        mainScene = 15;
                        ranks = 8;
                        wwType = 4;
                        can = 1;
                        err = 10;
                        if (AtFairyConfig.getOption("autos").equals("1")) {
                            supplement = 0;
                        }
                        return;
                    }
                } else {
                    if (list.get(0).equals("xmj") && (hour != 19 || (hour == 19 && minute > 40))) {
                        list.remove(0);
                        go10 = 1;
                        initPrestige();
                    }
                }
            }
        }

        /** 格斗场 */
        if (go11 == 0) {
            if (AtFairyConfig.getOption("gdc").equals("1")) {
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();

                if ((week == 1) &&
                        (hour >= 20)) {
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【格斗场】"));
                        list.add(0, "gdc");
                        mainScene = 15;
                        ranks = 8;
                        wwType = 4;
                        err = 10;
                        can = 1;
                        if (AtFairyConfig.getOption("autos").equals("1")) {
                            supplement = 0;
                        }
                        return;
                    }
                } else {
                    if (list.get(0).equals("gdc") && hour != 20) {
                        list.remove(0);
                        go11 = 1;
                        initPrestige();
                    }
                }
            }
        }


        /** 熊万军的春天 */

        if (go13 == 0) {
            if (AtFairyConfig.getOption("xwj").equals("1")) {
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();

                if ((week == 7) &&
                        (hour == 19) &&
                        (minute >= 30)) {
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【熊万军的春天】"));
                        list.add(0, "xwj");
                        mainScene = 15;
                        ranks = 8;
                        wwType = 4;
                        can = 1;
                        err = 10;
                        if (AtFairyConfig.getOption("autos").equals("1")) {
                            supplement = 0;
                        }
                        return;
                    }
                } else {
                    if (list.get(0).equals("xwj")) {
                        list.remove(0);
                        go13 = 1;
                        initPrestige();
                    }
                }
            }
        }

        /** 帮派神兽 */
        if (go14 == 0) {
            if (AtFairyConfig.getOption("bpss").equals("1")) {
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();

                if ((week == 3 || week == 6) &&
                        (hour == 19) &&
                        (minute >= 30 && minute <= 45)) {
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【帮派神兽】"));
                        list.add(0, "bpss");
                        mainScene = 15;
                        ranks = 8;
                        wwType = 4;
                        can = 1;
                        err = 10;
                        if (AtFairyConfig.getOption("autos").equals("1")) {
                            supplement = 0;
                        }
                        return;
                    }
                } else {
                    if (list.get(0).equals("bpss")) {
                        list.remove(0);
                        go14 = 1;
                        initPrestige();
                    }
                }
            }
        }

        /** 修罗道 */
        if (go15 == 0) {
            if (AtFairyConfig.getOption("xld").equals("1")) {
                week = mFairy.week();
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();

                if ((week == 2) &&
                        (hour == 20) &&
                        (minute <= 45)) {
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【修罗道】"));
                        list.add(0, "xld");
                        mainScene = 15;
                        ranks = 8;
                        wwType = 4;
                        can = 1;
                        err = 10;
                        if (AtFairyConfig.getOption("autos").equals("1")) {
                            supplement = 0;
                        }
                        return;
                    }
                } else {
                    if (list.get(0).equals("xld") && infinite == 99) {
                        list.remove(0);
                        go15 = 1;
                        initPrestige();
                    }
                }
            }
        }


        /**决战修仙帮*/
        if (go2 == 0) {
            if (AtFairyConfig.getOption("xxb").equals("1")) {
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                week = mFairy.week();
                if ((week == 2 || week == 4) &&
                        (hour >= 13 && hour < 19) &&
                        (minute >= 59 || minute < 15)) {
                    infinite = 0;
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【决战修仙帮】"));
                        list.add(0, "xxb");
                        err = 10;
                        mainScene = 15;
                        wwType = 3;
                        time = System.currentTimeMillis();
                        if (AtFairyConfig.getOption("autos").equals("1")) {
                            supplement = 0;
                        }
                        return;
                    }
                } else {
                    if (list.get(0).equals("xxb") && minute > 15 && infinite == 99) {
                        list.remove(0);
                        initPrestige();
                    }
                }
            }
        }


        /**决战修仙帮长老*/
        if (go3 == 0) {
            if (AtFairyConfig.getOption("xxbzl").equals("1")) {
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                week = mFairy.week();
                if ((week == 2 || week == 4) &&
                        (hour == 19) &&
                        (minute >= 30 && minute < 45)) {
                    infinite = 0;
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【决战修仙帮长老】"));
                        list.add(0, "xxbzl");
                        mainScene = 15;
                        ranks = 8;
                        wwType = 4;
                        can = 1;
                        err = 10;
                        if (AtFairyConfig.getOption("autos").equals("1")) {
                            supplement = 0;
                        }
                        return;
                    }
                } else {
                    if (list.get(0).equals("xxbzl")) {
                        list.remove(0);
                        initPrestige();
                        go3 = 1;
                    }
                }
            }
        }

        /**五行之序*/
        if (go4 == 0) {
            if (!AtFairyConfig.getOption("wxzx").equals("")) {
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                week = mFairy.week();
                if ((week == 1 || week == 3 || week == 5 || week == 6) &&
                        (hour >= 10 && hour <= 22) &&
                        (minute >= 29 && minute < 50)) {
                    infinite = 0;
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【五行之序】"));
                        list.add(0, "wxzx");
                        err = 10;
                        mainScene = 15;
                        wwType = 3;
                        time = System.currentTimeMillis();
                        if (AtFairyConfig.getOption("autos").equals("1")) {
                            supplement = 0;
                        }
                        return;
                    }
                } else {
                    if (list.get(0).equals("wxzx") && infinite == 99) {
                        list.remove(0);
                        initPrestige();
                    }
                }
            }
        }


        /**逐灭恶灵*/
        if (go5 == 0) {
            if (AtFairyConfig.getOption("zmel").equals("1")) {
                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();
                if ((hour >= 12 && hour < 23) &&
                        ((minute > 29 && minute < 45) || (minute >= 59 || minute < 15))) {
                    infinite = 0;
                    if (bool) {
                        LtLog.e(mFairy.getLineInfo("【逐灭恶灵】"));
                        list.add(0, "zmel");
                        err = 10;
                        mainScene = 15;
                        wwType = 3;
                        time = System.currentTimeMillis();
                        if (AtFairyConfig.getOption("autos").equals("1")) {
                            supplement = 0;
                        }
                        return;
                    }
                } else {
                    if (list.get(0).equals("zmel") && infinite == 99) {
                        list.remove(0);
                        initPrestige();
                    }
                }
            }
        }

        if (go12 == 0) {
            if (AtFairyConfig.getOption("ll").equals("1")) {
                if (hour == 23 && minute > 50) {
                    list.add(0, "linghd");
                    list.add(0, "ling");
                    go12 = 1;
                }
            }
        }

    }//限时

    public void initPrestige() throws Exception {
        can = 0;

        if (bools) {
            mainScene = 0;
        } else {
            mainScene = 15;
        }

        err = 10;
        ranks = 0;
        supplement = 0;
        if (AtFairyConfig.getOption("dw").equals("1")) {
            wwType = 1;
        } else {
            wwType = 2;
        }
    }//

    public void setUpGo() throws Exception {
        switch (list.get(0)) {
            case "ylsg":
                go1 = 1;
                break;
            case "xxb":
                go2 = 1;
                break;
            case "xxbzl":
                go3 = 1;
                break;
            case "wxzx":
                go4 = 1;
                break;
            case "zmel":
                go5 = 1;
                break;
            case "mrdt":
                go6 = 1;
                break;
            case "kjdt":
                go7 = 1;
                break;
            case "kjdtzs":
                go8 = 1;
                break;
            case "bpls":
                go9 = 1;
                break;
            case "xmj":
                go10 = 1;
                break;
            case "gdc":
                go11 = 1;
                break;
            case "xwj":
                go13 = 1;
                break;
            case "bpss":
                go14 = 1;
                break;
            case "xld":
                go15 = 1;
                break;
        }


    }//设置显示标识

    /** 跟队 **/
    public long timeGD = System.currentTimeMillis() - 120000;

    public void mainSceneGD() throws Exception {

        result = mFairy.findPic(1107, 228, 1212, 335, new String[]{"home.png", "home2.png","bao.png"});
        Task.task_err(0.7f,result);
        if (result.sim > 0.7f) {

        } else {
            gamePublicFuntion.initTime();
            return;
        }


        result = mFairy.findPic(921, 68, 1090, 164, new String[]{"fb.png", "fb1.png"});
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【副本场景】"));

            result = mFairy.findPic(855, 417, 998, 537, "ct1.png");
            mFairy.onTap(0.8f, result, "聊天", 1000);


            result = mFairy.findPic("ct2.png");
            mFairy.onTap(0.8f, result, "前往命运之轮", 1000);

            super.autoUse();


            result = mFairy.findPic(492, 547, 620, 629, new String[]{"auto.png"/*,"auto6.png"*/});
            LtLog.e(mFairy.getLineInfo(""+result.sim));
            mFairy.onTap(0.9f, result, "自动战斗", 1000);

           /* result = mFairy.findPic(492, 547, 620, 629, new String[]{"zidong.png","zidong1.png"});
            LtLog.e(mFairy.getLineInfo("自动战斗:"+result.sim));
            mFairy.onTap(0.95f, result, "自动战斗", 1000);*/

            result = mFairy.findPic(291, 109, 351, 398, "fh2.png");
            mFairy.onTap(0.8f, result, "复活队友", 15000);

            result = mFairy.findPic(396, 4, 547, 80, new String[]{"boss.png", "boss1.png","boss3.png"});
            if (result.sim > 0.85f) {
                fbErr = 0;
                s = 0;
            } else {
                fbErr++;
            }

            if (fbErr > 5) {
                if (s == 3) {
                    LtLog.e(mFairy.getLineInfo("多次前滑未找到,right"));
                    mFairy.ranSwipe(339, 250, 742, 278, 1, 1500, (long)2000);
                }

                if (s == 6) {
                    for (int i = 0; i < 3; i++) {
                        mFairy.ranSwipe(136, 511, 180, 678, 2, 4000, (long)1000);
                    }
                    mFairy.ranSwipe(339, 250, 742, 278, 1, 1500, (long)1000);
                    mFairy.ranSwipe(339, 250, 742, 278, 1, 1500, (long)2000);
                } else {
                    LtLog.e(mFairy.getLineInfo("未找到目标,往前滑"));
                    mFairy.ranSwipe(136, 411, 180, 578, 2, 4000, (long)2000);
                    fbErr = fbErr - 2;
                }
                s++;
                if(s>=9){
                    s=0;
                }
            }
        } else {
            result = mFairy.findPic("ranks24.png");
            mFairy.onTap(0.8f, result, "跟随", 2000);


            if (AtFairyConfig.getOption("li").equals("1")) {

                result = mFairy.findPic("zhaoji.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("发现已经离队,end!"));
                    mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
                }

                result = mFairy.findPic("tuoli.png");
                if (result.sim > 0.8f) {
                    timeGD = System.currentTimeMillis();
                } else {
                    if (System.currentTimeMillis() - timeGD > 15000) {
                        for (int i = 0; i < 3; i++) {
                            mFairy.onTap(11, 273, 33, 300, "队伍", 500);
                        }
                        timeGD = System.currentTimeMillis();
                    }
                }
            }

            result = mFairy.findPic(555, 122, 706, 166, "gen.png");
            if (result.sim < 0.8f) {
                result = mFairy.findPic(492, 547, 620, 629, new String[]{"zidong.png","zidong1.png","auto.png"});
                LtLog.e(mFairy.getLineInfo("自动战斗 ："+result.sim));
                mFairy.onTap(0.9f, result, "自动战斗", 5000);
            }
        }

        result = mFairy.findPic(596,17,935,86, "jian.png");
        mFairy.onTap(0.7f, result, "关闭右侧缩放栏", 1000);

        result = mFairy.findPic(801, 215, 1057, 475, "use.png");
        mFairy.onTap(0.8f, result, "使用", 1000);

        result = mFairy.findPic(801, 215, 1057, 475, "equipment.png");
        mFairy.onTap(0.8f, result, "装备", 1000);

    }//主场景

    int j = 0;

    public void serviceGD() throws Exception {

        super.overallSituation();

        result = mFairy.findPic(435, 336, 866, 442, "yq.png");
        mFairy.onTap(0.72f, result, 715, 466, 751, 483, "同意跟随", 1000);

        result = mFairy.findPic(397, 333, 854, 423, "fh3.png");
        if (result.sim > 0.72f) {
            mFairy.onTap(0.72f, result, 729, 473, 757, 486, "接受队友复活", 1000);
            return;
        } else {
            result1 = mFairy.findPic(347, 312, 661, 634, "cancel.png");
            mFairy.onTap(0.72f, result1, "取消", 1000);
        }
        result = mFairy.findPic("tiao.png");
        mFairy.onTap(0.85f, result, "跳过", 1000);

        result = mFairy.findPic("jx.png");
        mFairy.onTap(0.85f, result, "继续", 1000);

        result = mFairy.findPic(34, 507, 387, 598, "chat.png");
        mFairy.onTap(0.7f, result, 1188, 670, 1231, 697, "聊天", 1000);

        result = mFairy.findPic("likaifb.png");
        mFairy.onTap(0.8f, result, "离开副本", 1000);

        result = mFairy.findPic("ok1.png");
        mFairy.onTap(0.8f, result, "确定", 1000);

        result = mFairy.findPic("nn27.png");
        mFairy.onTap(0.8f, result, 97, 64, 134, 93, "查看世界地图", 1000);

        result = mFairy.findPic(890, 163, 1174, 649, new String[]{"fh.png", "fh1.png"});
        mFairy.onTap(0.8f, result, "返回复活点", 3000);

        result = mFairy.findPic(738,475,1046,663,new String[]{"gd1.png","jieshou1.png"});
        if (result.sim > 0.72f) {
            mFairy.onTap(0.72f, result, "接受", 1000);
        } else {
            result = mFairy.findPic(622, 360, 883, 603, "gd2.png");
            if (result.sim > 0.72f) {
                mFairy.onTap(0.72f, result, "同意", 3000);
            } else {
                result1 = mFairy.findPic(399, 393, 658, 589, "jj.png");
                if (result1.sim > 0.7f) {
                    result = mFairy.findPic(384, 347, 650, 543, "jj1.png");
                    mFairy.onTap(0.85f, result, result.x + 28, result.y + 28, result.x + 32, result.y + 32, "勾选弹框", 2000);

                    mFairy.onTap(0.7f, result1, "拒绝", 1000);
                }
            }
        }

        result = mFairy.findPic(new String[]{"ranks.png", "ranks0.png"});
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【组队场景】"));

            if (!AtFairyConfig.getOption("li").equals("1")) {
                gamePublicFuntion.close();
                return;
            }
            result = mFairy.findPic("ranks20.png");
            mFairy.onTap(0.8f, result, 955, 108, 972, 122, "关闭", 1000);

            result = mFairy.findPic(1105,395,1224,715,"ranks6.png");
            mFairy.onTap(0.8f, result, "我的队伍", 2000);

            result = mFairy.findPic(new String[]{"ranks3.png", "ranks4.png", "ranks12.png"});
            if (result.sim > 0.8f) {
                j = 0;
                gamePublicFuntion.close();
            } else {
                j++;
                if (j > 3) {
                    j = 0;
                    LtLog.e(mFairy.getLineInfo("发现已经离队,end!"));
                    mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
                    gamePublicFuntion.close();
                }
            }
            return;
        } else {
            j = 0;
        }
        gamePublicFuntion.close();
    }//

    private long ranksHanTime = System.currentTimeMillis();

    public void wxqy_mainScene() throws Exception {
        result = mFairy.findPic(1107, 228, 1212, 335, new String[]{"home.png", "home2.png","bao.png"});
        Task.task_err(0.7f,result);
        if (result.sim > 0.7f) {
            LtLog.e(mFairy.getLineInfo("【主场景>>>" + gamePublicFuntion.getTime()) + "  mainScene:" + mainScene + "】");

            if (mainScene == 10) {
                ranks = 10;
                mainScene = 0;
            }

            if (gamePublicFuntion.EndTask(this)) {
                return;
            }

            result = mFairy.findPic(553, 16, 929, 85, "right1.png");
            mFairy.onTap(0.7f, result, "关闭右侧缩放栏", 1000);

            /**
             *  Task */
            if (mainSceneTask()) {
                return;
            }
            /**
             *  mainScene未操作超时时间*/
            if (gamePublicFuntion.getTime() > 20) {
                ranks = 8;
                gamePublicFuntion.initTime();
                return;
            }

        } else {
            gamePublicFuntion.initTime();
            return;
        }

        result = mFairy.findPic(921, 68, 1090, 164, new String[]{"fb.png", "fb1.png"});
        LtLog.e(mFairy.getLineInfo("副本：" + result.sim));
        if (result.sim > 0.8f) {

            LtLog.e(mFairy.getLineInfo("【副本场景】"));
            gamePublicFuntion.initTime();

            result = mFairy.findPic(855, 417, 998, 537, "ct1.png");
            mFairy.onTap(0.8f, result, "聊天", 1000);


            result = mFairy.findPic("ct2.png");
            mFairy.onTap(0.8f, result, "前往命运之轮", 1000);

            if (battleFailure == -1) {
                battleFailure = 0;
            }

            result = mFairy.findPic(492, 547, 620, 629, "auto.png");
            mFairy.onTap(0.92f, result, "自动战斗", 1000);

            result = mFairy.findPic(396, 4, 547, 80, new String[]{"boss.png", "boss1.png"});
            if (result.sim > 0.85f) {
                fbErr = 0;
                s = 0;
            }
            fbErr++;
            if (fbErr > 5) {
                s++;
                if (s >= 4) {
                    LtLog.e(mFairy.getLineInfo("多次前滑未找到,right"));
                    mFairy.ranSwipe(339, 250, 742, 278, 1, 1500, (long)2000);
                    s = 0;
                }
                LtLog.e(mFairy.getLineInfo("未找到目标,往前滑"));
                mFairy.ranSwipe(136, 411, 180, 578, 2, 5000, (long)500);
                fbErr = 0;
            }
        } else {
            if (battleFailure == -1) {/**战斗失败,没有在副本场景。*/
                mainScene = 15;
            }
        }
        gamePublicFuntion.close();

    }//无限情义

    public void wxqy_ranks() throws Exception {
        result = mFairy.findPic(new String[]{"ranks.png", "ranks0.png"});
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【组队场景】"));
            gamePublicFuntion.initTime();

            result = mFairy.findPic("ranks20.png");
            mFairy.onTap(0.8f, result, 955, 108, 972, 122, "关闭", 1000);

            if (ranks == 0) {
                mFairy.onTap(1179, 70, 1201, 91, "关闭", 1000);
                return;
            }

            result = mFairy.findPic("ranksmb.png");
            if (result.sim > 0.8f) {
                return;
            }

        } else {
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }
        /**
         *  带队*/
        result = mFairy.findPic(1105,395,1224,715,"ranks6.png");
        mFairy.onTap(0.8f, result, "我的队伍", 2000);


        result = mFairy.findPic("ranks12.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "退出团队", 1500);
            mFairy.onTap(721, 466, 761, 488, "确定退出", 1000);
        }

        result = mFairy.findPic("ranks1.png");
        mFairy.onTap(0.8f, result, "创建队伍", 1000);

        result = mFairy.findPic(new String[]{"ranks3.png", "ranks4.png"});
        if (result.sim > 0.8f) {
            result = mFairy.findPic("ranks5.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "退出队伍", 2000);
                mFairy.onTap(725, 471, 749, 487, "确定", 1000);
            }
        }

        result = mFairy.findPic(428, 308, 868, 466, "ranks9.png");
        mFairy.onTap(0.8f, result, 720, 469, 748, 487, "同意", 1000);

        /**
         *  ranks
         */
        result = mFairy.findPic("ranks13.png");
        if (result.sim > 0.8f) {

            result = mFairy.findPic("ranks20.png");
            mFairy.onTap(0.8f, result, 955, 108, 972, 122, "关闭", 1500);

            result = mFairy.findPic(241, 530, 1110, 577, "ranks14.png");
            if (result.sim > 0.93f) {
                mFairy.onTap(0.93f, result, "发现离线队员", 2000);
                ranks = 8;
                result = mFairy.findPic(456, 378, 1244, 588, "ranks17.png");
                mFairy.onTap(0.8f, result, "请离队伍", 1000);
            }

            result = mFairy.findPic("ct3.png");
            if (result.sim < 0.8f) {
                mFairy.onTap(280, 119, 321, 134, "目标不匹配,重新匹配", 1000);
                return;
            }

            int count = gamePublicFuntion.ranksNum();
            if (count >= 3) {
                ranks++;
            }

            /**
             *  满人直接开始任务 */
            if (count == 6) {
                ranks = 10;
            } else {
                /**
                 *  喊话*/
                if (System.currentTimeMillis() - ranksHanTime > 15000) {
                    result = mFairy.findPic("zhao.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "招募", 1000);
                    } else {
                        mFairy.onTap(100, 116, 119, 134, "喊话", 1000);
                        mFairy.onTap(174, 236, 200, 253, "招募", 1000);
                    }


                    result = mFairy.findPic("ranks18.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "申请", 1500);
                        for (int i = 0; i < 3; i++) {
                            result = mFairy.findPic("ranks19.png");
                            mFairy.onTap(0.8f, result, "接受", 2000);
                        }
                        mFairy.onTap(955, 108, 972, 122, "关闭", 1000);
                    }

                    ranksHanTime = System.currentTimeMillis();
                }

                result = mFairy.findPic(704, 549, 982, 689, "ranks8.png");
                mFairy.onTap(0.8f, result, "自动匹配", 1000);
            }
        }

        if (ranks > 9) {
            LtLog.e(mFairy.getLineInfo("达到要求开始任务"));
            result = mFairy.findPic("ct4.png");
            if (result.sim > 0.8f) {
                ranks = 0;
                mFairy.onTap(0.8f, result, "前往活动", 1000);
            }

        }

    }//队伍

    public void wxqy_dwmb() throws Exception {

        result = mFairy.findPic("ranksmb.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【组队目标】"));
            gamePublicFuntion.initTime();
            ranksmbTools("ranksmb4.png", "ct5.png");
        } else {
            return;
        }


    }//
    

}