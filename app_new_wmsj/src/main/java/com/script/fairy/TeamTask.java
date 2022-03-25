package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;

/**
 * Created by user on 2019/6/3.
 */

public class TeamTask extends Task{
    private int ranksJc = 0, ranksNum = 4;//组队
    private long ranksHanTime = System.currentTimeMillis();
    private int activity = 0, act = 0, activityTools = 0;//活动计次
    private String hh = "1";//黄昏圣殿
    private String fss = "1";//覆霜城 · 上
    private String zx = "1";//修真副本
    private int zxNum = 0;
    private int errCount = 0;
    private int battleFailure = 0;
    private int s=0;
    private int huan=1;
    private int huanTool=0;
    public TeamTask(AtFairyImpl ypFairy) throws Exception {
        super(ypFairy);
        init();
    }

    private void init() throws Exception {
        if (AtFairyConfig.getTaskID().equals("1436")) {
            list = new ArrayList<>();
            activityTools = 1;
            battleFailureCount = 0;
            mainScene = 18;
            ranks = 0;
            err = 0;
            unexecuted = 0;
            supplement = 0;
            setUp = 0;
            zxNum = 0;
            s=0;
            if (AtFairyConfig.getOption("pl").equals("1")) {
                LtLog.e(mFairy.getLineInfo("【勾选清理背包】"));
                pl = 0;
            } else {
                pl = 1;
            }

            hong=0;
            lan=0;
            timeHong =System.currentTimeMillis()-90000;
            timeLan =System.currentTimeMillis()-90000;
            if (!AtFairyConfig.getOption("hong").equals("")) {
                hong=Integer.parseInt(AtFairyConfig.getOption("hong"));
                LtLog.e(mFairy.getLineInfo("【勾选加红:】"+hong));
            }
            if (!AtFairyConfig.getOption("lan").equals("")) {
                lan=Integer.parseInt(AtFairyConfig.getOption("lan"));
                LtLog.e(mFairy.getLineInfo("【勾选加蓝:】"+lan));
            }

            if (AtFairyConfig.getOption("ssfe").equals("1")) {
                LtLog.e(mFairy.getLineInfo("【赏善罚恶】"));
                list.add("ssfe");
            }

            if (!AtFairyConfig.getOption("huan").equals("")) {
                LtLog.e(mFairy.getLineInfo("【幻境】"));
                huan=Integer.parseInt(AtFairyConfig.getOption("huan"));
                list.add("huan");
            }

            if (AtFairyConfig.getOption("fmg").equals("1")) {
                week = mFairy.week();
                if (week == 1 || week == 3 || week == 5 || week == 7) {
                    LtLog.e(mFairy.getLineInfo("【凤鸣谷】"));
                    list.add("fmg");
                }
            }
            if (AtFairyConfig.getOption("ftj").equals("1")) {
                week = mFairy.week();
                if (week == 2 || week == 4 || week == 6) {
                    LtLog.e(mFairy.getLineInfo("【凤啼涧】"));
                    list.add("ftj");
                }
            }

            if (!AtFairyConfig.getOption("zx").equals("")) {
                LtLog.e(mFairy.getLineInfo("【修真副本】"));
                list.add("zx");
                zx = AtFairyConfig.getOption("zx");
            }

            if (!AtFairyConfig.getOption("hh").equals("")) {
                LtLog.e(mFairy.getLineInfo("【黄昏圣殿 : " + AtFairyConfig.getOption("hh") + "】"));
                list.add("hh");
                hh = AtFairyConfig.getOption("hh");
            }
            if (!AtFairyConfig.getOption("fss").equals("")) {
                LtLog.e(mFairy.getLineInfo("【覆霜· 上 : " + AtFairyConfig.getOption("fss") + "】"));
                list.add("fss");
                fss = AtFairyConfig.getOption("fss");
            }
        }
    }

    public void unexecuted() throws Exception {
        /***
         *  task */
        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        switch (list.get(0)) {
            case "ssfe":
                result = mFairy.findPic(946, 221, 1216, 642, "ssfe3.png");
                mFairy.onTap(0.8f, result, "赏善罚恶", 2000);
                break;
        }

        result = mFairy.findPic("fb2.png");
        if (result.sim > 0.8f) {
            result = mFairy.findPic(334, 384, 1096, 456, "fb3.png");
            if (result.sim > 0.8f) {
                ranks = 8;
                mFairy.onTap(606, 558, 670, 585, "退出副本>>> 有队员拒绝", 5000);
            } else {
                ranks = 0;
            }
            return;
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

            /**
             *  点击>>>activity*/
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

        result = mFairy.findPic(921, 68, 1090, 164, new String[]{"fb.png", "fb1.png"});
        LtLog.e(mFairy.getLineInfo("副本：" + result.sim));
        if (result.sim > 0.8f) {

            LtLog.e(mFairy.getLineInfo("【副本场景】"));
            gamePublicFuntion.initTime();


            result = mFairy.findPic(291,109,351,398, "fh2.png");
            mFairy.onTap(0.8f,result,"复活队友",15000);

            super.autoUse();

            ranksJc = 0;

            if (battleFailure == -1) {
                battleFailure = 0;
            }

            result = mFairy.findPic(492, 547, 620, 629, new String[]{"auto.png"/*,"auto6.png"*/});
            LtLog.e(mFairy.getLineInfo(""+result.sim));
            mFairy.onTap(0.9f, result, "自动战斗", 1000);

            result = mFairy.findPic(396, 4, 547, 80, new String[]{"boss.png", "boss1.png","boss3.png"});
            if (result.sim > 0.85f) {
                fbErr = 0;
                s=0;
            }else{
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
                    mFairy.ranSwipe(136, 411, 180, 578, 2, 4000,(long) 2000);
                    fbErr = fbErr - 2;
                }
                s++;
                if(s>=9){
                    s=0;
                }
            }
        } else {
            if (battleFailure == -1) {/**战斗失败,没有在副本场景。*/
                mainScene = 15;
            }
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
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "创建队伍", 1000);
            zxNum = 0;
        }

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


            FindResult resultRanks = null;
            switch (list.get(0)) {
                case "ssfe":
                    if (ranksJc > 2) {
                        gamePublicFuntion.ranksTT();
                    }
                    ranksNum=4;
                    resultRanks = mFairy.findPic("ssfe2.png");
                    break;
                case "fmg":
                    ranksNum=4;
                    resultRanks = mFairy.findPic("fmg2.png");
                    break;
                case "ftj":
                    ranksNum=4;
                    resultRanks = mFairy.findPic("ftj2.png");
                    break;
                case "hh":
                    if (!AtFairyConfig.getOption("ranksNum").equals("")) {
                        ranksNum = Integer.parseInt(AtFairyConfig.getOption("ranksNum"));
                    }
                    resultRanks = mFairy.findPic("hh2.png");
                    break;
                case "zx":
                    if (zxNum == 0) {
                        if (!AtFairyConfig.getOption("ranksNum").equals("")) {
                            ranksNum = Integer.parseInt(AtFairyConfig.getOption("ranksNum"));
                        }
                        resultRanks = new FindResult();
                    }
                    break;
                case "huan":
                    if (!AtFairyConfig.getOption("ranksNum").equals("")) {
                        ranksNum = Integer.parseInt(AtFairyConfig.getOption("ranksNum"));
                    }
                    resultRanks = mFairy.findPic("hj3.png");
                    break;


            }
            if (resultRanks != null) {
                if (resultRanks.sim < 0.8f) {
                    mFairy.onTap(280, 119, 321, 134, "目标不匹配,重新匹配", 1000);
                    return;
                }
            }

            int count = gamePublicFuntion.ranksNum();
            if (count >= ranksNum) {
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
            mFairy.onTap(1179, 70, 1201, 91, "关闭", 1000);
            ranks = 0;
            mainScene = 15;
            ranksJc = 0;
            if (activityTools == 1) {
                activityTools = 2;
            }
        }
    }//组队

    public void ranksmb() throws Exception {
        result = mFairy.findPic(528,472,809,632,"ranksmb.png");
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
            case "ssfe":
                ranksmbTools("ranksmb2.png", "ssfe1.png");
                break;
            case "fmg":
                ranksmbTools("ranksmb1.png", "fmg1.png");
                break;
            case "ftj":
                ranksmbTools("ranksmb1.png", "ftj1.png");
                break;
            case "hh":
                ranksmbTools("ranksmb4.png", "hhtype" + hh + ".png");
                break;
            case "zx":
                zxNum = 1;
                ranksmbTools("ranksmb5.png", "zx" + zx + "g1.png");
                break;
            case "huan":
                ranksmbTools("hj4.png", "hj5.png");
                break;
        }
    }//组队目标

    public void activity() throws Exception {
        result = mFairy.findPic("activity.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            unexecuted = 0;
            battleFailureCount = 0;
            LtLog.e(mFairy.getLineInfo("【活动场景】"));
            gamePublicFuntion.initTime();
            activity++;
            mainScene = 0;

            result = mFairy.findPic("activity1.png");
            mFairy.onTap(0.8f, result, 1001, 101, 1020, 123, "关闭活动介绍", 1000);

            if (ranksJc > 2) {
                ranks = 8;
                gamePublicFuntion.close();
                return;
            }

            if (ranks == 8) {
                mFairy.onTap(1204, 71, 1225, 90, "关闭活动", 1000);
                return;
            }

            if (activity > 11) {
                LtLog.e("活动已经完成,或者未发现,移除当前任务。");
                list.remove(0);
                gamePublicFuntion.EndTask(this);
                activity = 0;
                act = 0;
                if (activityTools == 2) {
                    activityTools = 1;
                }
                return;
            }
        } else {
            activity = 0;
            act = 0;
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }


        if (list.get(0).equals("zx")) {
            mFairy.onTap(1148, 352, 1180, 389, "修真", 2000);
            activity = 0;
            return;
        }

        if (list.get(0).equals("huan")) {
            result = mFairy.findPic(1104,112,1226,637,"hj1.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "幻境", 2000);
            }
            activity = 0;
            return;
        }

        if (list.get(0).equals("hh")) {
            mFairy.onTap(1142, 246, 1176, 281, "挑战", 2000);
            activity = 0;
            return;
        }

        if (activityTools == 1) {
            if (activityTools(list.get(0))) {
                return;
            }
        } else {
            result1 = mFairy.findPic(96, 165, 1108, 490, list.get(0) + ".png");
            if (result1.sim > 0.8f) {
                result = mFairy.findPic(result1.x + 264, result1.y - 8, result1.x + 352, result1.y + 63, "can.png");
                if (result.sim > 0.75f) {
                    mFairy.onTap(0.8f, result, "前往 :" + list.get(0), 1000);
                    ranks = 0;
                    err = 0;
                    mainScene = 0;
                    for (int i = 0; i < 6; i++) {
                        result = mFairy.findPic(595, 21, 864, 199, "ranks16.png");
                        if (result.sim > 0.72f) {
                            LtLog.e(mFairy.getLineInfo("无法开启任务,ranksJc++"));
                            ranksJc++;
                            mainScene = 8;
                            return;
                        }
                        
                    }
                    act++;
                    if (act > 5) {
                        gamePublicFuntion.close();
                    }
                    return;
                } else {
                    act = 0;
                }

                result = mFairy.findPic(result1.x + 264, result1.y - 8, result1.x + 352, result1.y + 63, "can1.png");
                if (result.sim > 0.8f) {
                    activity = 13;
                    return;
                }
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
            mFairy.ranSwipe(637, 250, 684, 516, 2, 1000, (long)2500);
            LtLog.e(mFairy.getLineInfo("滑动"));
            return;
        }
    }

    public boolean activityTools(String str) throws Exception {
        result1 = mFairy.findPic(96, 165, 1108, 490, str + ".png");
        if (result1.sim > 0.8f) {
            result = mFairy.findPic(result1.x + 264, result1.y - 8, result1.x + 352, result1.y + 63, "can.png");
            if (result.sim > 0.8f) {
                LtLog.e(mFairy.getLineInfo("发现活动activityTools"));
                ranks = 8;
                activity = 0;
                err = 10;
                gamePublicFuntion.close();
                return true;
            }

            result = mFairy.findPic(result1.x + 264, result1.y - 8, result1.x + 352, result1.y + 63, "can1.png");
            if (result.sim > 0.8f) {
                activity = 13;
                return true;
            }

        }
        return false;
    }

    public void cancel() throws Exception {
        result1 = mFairy.findPic(347, 312, 661, 634, "cancel.png");
        Task.task_err(0.8f,result1);
        if (result1.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【取消场景】"));
            gamePublicFuntion.initTime();
            activity = 0;
        } else {
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        result = mFairy.findPic(397,333,854,423,"fh3.png");
        if (result.sim > 0.72f) {
            mFairy.onTap(0.72f, result, 729,473,757,486, "接受队友复活", 1000);
            return;
        }

        result = mFairy.findPic("yq.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, 715, 466, 751, 483, "同意跟随", 1000);
            return;
        }

        result = mFairy.findPic("ssfe4.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result1, "取消", 1000);
            ranks = 8;
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

        result = mFairy.findPic("pp.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, 729, 471, 755, 484, "确定跨服匹配", 1000);
            return;
        }
        mFairy.onTap(0.8f, result1, "取消", 1000);
    }//取消

    public void fbShiBai() throws Exception {
        result1 = mFairy.findPic(985, 47, 1101, 393, "fbEnd.png");
        Task.task_err(0.8f,result1);
        if (result1.sim > 0.8f) {
            battleFailure = -1;
            if(fbEnd==0) {
                battleFailureCount++;
                fbEnd=1;
            }
            LtLog.e(mFairy.getLineInfo("【副本结束】"));
        } else {
            fbEnd=0;
            return;
        }

        result = mFairy.findPic("likai.png");
        if (result.sim > 0.8f) {
            unexecuted();
        }

        result = mFairy.findPic(890, 163, 1174, 649, new String[]{"fh.png", "fh1.png"});
        mFairy.onTap(0.8f, result, "返回复活点", 3000);

    }//副本结束

    public void zxScene() throws Exception {
        result = mFairy.findPic("zxScene.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            errCount++;
            if (errCount > 10) {
                gamePublicFuntion.close();
                errCount = 0;
            }
            LtLog.e(mFairy.getLineInfo("【修真场景】"));
        } else {
            errCount = 0;
            return;
        }

        if (ranks == 8 && activityTools != 1) {
            mFairy.onTap(1204, 71, 1225, 90, "关闭活动", 1000);
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        if (list.get(0).equals("zx")) {

            result = mFairy.findPic(92, 117, 333, 689, new String[]{"zx" + zx + "g.png", "zx" + zx + "g0.png"});
            if (result.sim > 0.8f) {

                mFairy.onTap(0.8f, result, "副本", 2000);

                result = mFairy.findPic("zx3.png");
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("奖励次数已经用完,End!"));
                    list.remove(0);
                    gamePublicFuntion.EndTask(this);
                    return;
                }

                result = mFairy.findPic(800,572,1085,705,"zx2.png");
                if (result.sim > 0.8f) {

                    if (activityTools == 1) {
                        ranks = 8;
                        err = 10;
                        mFairy.onTap(1206, 70, 1227, 89, "关闭", 3000);

                    } else {
                        mFairy.onTap(0.8f, result, "前往副本", 2000);
                    }

                } else {
                    LtLog.e(mFairy.getLineInfo("玩家勾选的副本还未开放。"));
                    list.remove(0);
                    gamePublicFuntion.EndTask(this);
                }
            }else{
                if(errCount%3==0 && errCount!=0){
                    mFairy.ranSwipe(183, 233, 225, 481, 2, 1000, (long)2000);
                    
                }
            }


        } else {
            mFairy.onTap(1206, 70, 1227, 89, "关闭", 3000);
        }

    }//修真

    public void tzScene() throws Exception {
        result = mFairy.findPic("tzScene.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            errCount++;
            if (errCount > 10) {
                gamePublicFuntion.close();
                errCount = 0;
            }
            LtLog.e(mFairy.getLineInfo("【挑战场景】"));
        } else {
            errCount = 0;
            return;
        }

        if (ranks == 8 && activityTools != 1) {
            mFairy.onTap(1204, 71, 1225, 90, "关闭活动", 1000);
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        int num = 0;
        switch (list.get(0)) {
            case "hh":
                result = mFairy.findPic(99, 126, 313, 672, "hh.png");
                mFairy.onTap(0.8f, result, "黄昏副本", 2000);

                switch (hh) {
                    case "1":
                        result = mFairy.findPic(193,88,302,661, "hh3.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "黄昏· 空", 2000);
                            num = 1;
                        }
                        break;
                    case "2":
                        result = mFairy.findPic(193,88,302,661, "hh4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "黄昏· 破", 2000);
                            num = 1;
                        }
                        break;
                    case "3":
                        result = mFairy.findPic(193,88,302,661, "hh5.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "黄昏· 灭", 2000);
                            num = 1;
                        }
                        break;
                }

                if (num == 1) {
                    result = mFairy.findPic("hh6.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("黄昏副本已经完成,End!"));
                        list.remove(0);
                        gamePublicFuntion.EndTask(this);
                    }

                    result = mFairy.findPic(802,601,1092,696,"hh1.png");
                    if (result.sim > 0.75f) {
                        if (activityTools == 1) {
                            ranks = 8;
                            err = 10;
                            mFairy.onTap(1206, 70, 1227, 89, "关闭", 3000);
                        } else {
                            mFairy.onTap(0.8f, result, "前往副本", 2000);
                        }
                    } else {
                        LtLog.e(mFairy.getLineInfo("玩家勾选的副本还未开放。"));
                        list.remove(0);
                        gamePublicFuntion.EndTask(this);
                    }
                }

                break;
            default:
                gamePublicFuntion.close();
                break;
        }

    }//挑战

    public void hjScene()throws Exception{
        result = mFairy.findPic("hjScene.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            errCount++;
            if (errCount > 10) {
                gamePublicFuntion.close();
                errCount = 0;
            }
            LtLog.e(mFairy.getLineInfo("【幻境场景,用户勾选"+huan+"层】"));
        } else {
            errCount = 0;
            return;
        }

        if(gamePublicFuntion.EndTask(this)){
            return;
        }

        if(huanTool==0) {
            for (int i = 0; i < 15; i++) {
                mFairy.onTap(798, 524, 816, 544, "初始化层数", 200);
            }
            for (int i = 0; i < (huan - 1); i++) {
                mFairy.onTap(1058, 526, 1079, 548, "", 300);
            }
            huanTool=1;
        }
        result = mFairy.findPic("hj2.png");
        mFairy.onTap(0.8f, result, "幻境-进入副本", 1000);

    }//幻境
}