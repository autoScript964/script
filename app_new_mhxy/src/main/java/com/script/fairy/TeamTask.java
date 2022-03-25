package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;



public class TeamTask {
    public GamePublicFuntion gamePublicFuntion;
    public AtFairyImpl mFairy;
    public FindResult result;
    private String activityName = "";
    private String rankName = "";
    private int activityType = 1;
    private int rankNum = 3;

    public TeamTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }

    class TeamContent extends TaskContent {

        public TeamContent(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);
        }

        void create() throws Exception {
            super.create();
            rankNum = 5;
            if (!AtFairyConfig.getOption("ranks_num").equals("")) {
                rankNum = Integer.parseInt(AtFairyConfig.getOption("ranks_num"));
                LtLog.e(mFairy.getLineInfo("用户勾选了：" + rankNum + "人开始"));
            }
        }

        void init() throws Exception {
            gamePublicFuntion.taskInit(1);
            setTaskName(1);
            oneSecond = 0;
        }

        void content_01() throws Exception {
            if (timeCount(7, 0)) {
                gamePublicFuntion.close();
                if (frequencyMap("actcount", 2)) {
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
                LtLog.e(mFairy.getLineInfo("activityName :" + result.sim));
                if (act.sim > 0.8f) {

                    if (actEnd(act)) {
                        mFairy.onTap(1128, 37, 1151, 55, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(act.x + 180, act.y - 20, act.x + 300, act.y + 70, "can.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1128, 37, 1151, 55, "任务未完成 >>>", 500);
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

        void content_02() throws Exception {
            timeCount(10, 0);

            result = mFairy.findPic("rank7.png");
            if (result.sim > 0.8f) {
                err = 0;

                result = mFairy.findPic("rank8.png");
                mFairy.onTap(0.99f, result, "队伍", 500);

                result = mFairy.findPic("rank4.png");
                mFairy.onTap(0.8f, result, "创建", 500);

                result = mFairy.findPic(new String[]{"rank3.png", "rank2.png"});
                mFairy.onTap(0.8f, result, 193, 650, 280, 665, "不是队长 退出队伍", 500);

                result = mFairy.findPic("rank5.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic("rank12.png");
                    mFairy.onTap(0.8f, result, 681, 632, 742, 658, "", 500);

                    result = mFairy.findPic(327, 206, 1267, 472, "lixian1.png");
                    mFairy.onTap(0.8f, result, "请离队伍", 1000);

                    result = mFairy.findPic(336, 391, 1112, 484, "lixian.png");
                    if (result.sim > 0.92f) {
                        mFairy.onTap(0.92f, result, "发现离线", 1000);
                        return;
                    }

                    result = mFairy.findPic(rankName);
                    if (result.sim > 0.8f) {
                        if (gamePublicFuntion.rankNum() >= rankNum) {
                            mFairy.onTap(1096, 35, 1114, 52, "", 500);
                            setTaskName(3);
                            return;
                        }

                        result = mFairy.findPic("rank11.png");
                        mFairy.onTap(0.8f, result, "自动匹配", 500);

                        if (frequencyMap("err_han", 3)) {
                            if (timeMap("hantime", getTimeStamp(AtFairyConfig.getOption("hantime")))) {
                                han();
                            }
                        }

                    } else {
                        if (frequencyMap("rankname", 2)) {
                            mFairy.onTap(844, 104, 863, 122, "修改目标", 500);
                        }
                    }
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    result = mFairy.findPic("rank1.png");
                    mFairy.onTap(0.8f, result, "右侧队伍", 500);
                }
            }
        }

        void content_03() throws Exception {
            if (timeCount(7, 3)) {
                gamePublicFuntion.taskInit(1);
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
                LtLog.e(mFairy.getLineInfo("activityName :" + result.sim));
                if (act.sim > 0.8f) {

                    if (actEnd(act)) {
                        mFairy.onTap(1128, 37, 1151, 55, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(act.x + 180, act.y - 20, act.x + 300, act.y + 70, "can.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "参加", 500);
                        setTaskName(4);
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
            gamePublicFuntion.battle();
            use();
            quxiao();

            result = mFairy.findPic("renwu.png");
            mFairy.onTap(0.8f,result,1090,41,1107,59,"关闭头像界面",1000);

            result = mFairy.findPic(767, 101, 841, 176, "jia3.png");
            mFairy.onTap(0.8f, result, "关闭小弹框", 500);

            result = mFairy.findPic("jia1.png");
            mFairy.onTap(0.8f, result, 1068, 65, 1082, 82, "福利界面-关闭", 500);

            result = mFairy.findPic("nn5.png");
            mFairy.onTap(0.8f, result, "奖励", 500);

            result = mFairy.findPic("fb6.png");
            mFairy.onTap(0.8f, result, "继续游戏", 500);

            result = mFairy.findPic("nn6.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, 702, 537, 766, 551, "查看", 3000);
                setTaskName(0);
                return;
            }
        }

        void han() throws Exception {

            mFairy.onTap(974, 649, 1044, 668, "一键喊话", 2000);

            result = mFairy.findPic("rank12.png");
            if (result.sim > 0.8f) {

                for (int i = 1; i <= 4; i++) {
                    switch (i) {
                        case 1:
                            if (AtFairyConfig.getOption("han1").equals("1")) {
                                mFairy.onTap(958, 315, 1050, 329, "帮派频道", 1000);
                                mFairy.onTap(974, 649, 1044, 668, "一键喊话", 2000);
                            }
                            break;
                        case 2:
                            if (AtFairyConfig.getOption("han2").equals("1")) {
                                mFairy.onTap(970, 395, 1048, 410, "当前频道", 1000);
                                mFairy.onTap(974, 649, 1044, 668, "一键喊话", 2000);
                            }
                            break;
                        case 3:
                            if (AtFairyConfig.getOption("han3").equals("1")) {
                                mFairy.onTap(979, 477, 1059, 498, "世界频道", 1000);
                                mFairy.onTap(974, 649, 1044, 668, "一键喊话", 2000);
                            }
                            break;
                        case 4:
                            if (AtFairyConfig.getOption("han4").equals("1")) {
                                mFairy.onTap(971, 556, 1045, 569, "组队频道", 1000);
                                mFairy.onTap(974, 649, 1044, 668, "一键喊话", 2000);
                            }
                            break;
                    }
                }
            }

        }//喊话

        void use() throws Exception {
            gamePublicFuntion.useClose();
        }//使用

        void quxiao() throws Exception {
            FindResult qx = gamePublicFuntion.qx();
            if (qx.sim > 0.8f) {

                mFairy.onTap(0.8f, qx, "取消", 500);
            }
        }//取消

        void rank(String str1, String str2) throws Exception {
            result = mFairy.findPic("rank9.png");
            if (result.sim > 0.8f) {
                err = 0;
                oneSecond = 0;
                while (mFairy.condit()) {

                    if (frequencyMap("mberr", 8)) {
                        mFairy.onTap(924, 25, 947, 42, "", 500);
                        return;
                    }

                    result = mFairy.findPic(358, 131, 630, 614, str1);
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "str1", 1000);

                        if (str2 != null) {
                            result = mFairy.findPic(358, 131, 630, 614, str2);
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "str2", 500);
                                break;
                            }

                        } else {
                            break;
                        }
                        continue;
                    }
                    if (oneSecond()) {
                        for (int i = 0; i < 3; i++) {
                            mFairy.ranSwipe(490, 166, 518, 568, 0, 300, (long) 200);
                        }
                        Thread.sleep(1000);
                    } else {
                        mFairy.ranSwipe(470, 296, 496, 506, 2, 500, (long) 800);
                    }
                }

                for (int i = 0; i < 6; i++) {
                    mFairy.ranSwipe(803, 172, 816, 336, 2, 100, (long) 500);
                }
                mFairy.onTap(746, 648, 820, 670, "确定", 500);
            }
        }

        void see() throws Exception {
            while (mFairy.condit()) {
                if (frequencyMap("seerr", 10)) {
                    gamePublicFuntion.close();
                    return;
                }

                result = mFairy.findPic("nn9.png");
                mFairy.onTap(0.8f, result, "leftzoom", 500);

                result = mFairy.findPic(8, 10, 444, 434, "rank13.png");
                mFairy.onTap(0.8f, result, "队伍", 500);

                result = mFairy.findPic("rank5.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(rankName);
                    if (result.sim > 0.8f) {

                        result = mFairy.findPic(327, 206, 1267, 472, "lixian1.png");
                        mFairy.onTap(0.8f, result, "请离队伍", 1000);

                        result = mFairy.findPic(336, 391, 1112, 484, "lixian.png");
                        if (result.sim > 0.92f) {
                            mFairy.onTap(0.92f, result, "发现离线", 1000);
                            continue;
                        }

                        if (gamePublicFuntion.rankNum() == 5) {
                            mFairy.onTap(1096, 35, 1114, 52, "", 500);
                            gamePublicFuntion.close();
                            return;
                        }

                        result = mFairy.findPic("rank11.png");
                        mFairy.onTap(0.8f, result, "自动匹配", 500);

                        han();


                        result = mFairy.findPic("rank12.png");
                        mFairy.onTap(0.8f, result, 681, 632, 742, 658, "", 500);

                        mFairy.onTap(1096, 35, 1114, 52, "", 500);
                        gamePublicFuntion.close();
                        return;
                    } else {
                        if (frequencyMap("rankname", 2)) {
                            mFairy.onTap(844, 104, 863, 122, "修改目标", 500);
                        }
                    }
                }

            }

        }//see

        boolean actEnd(FindResult act) throws Exception {

            return false;
        }//活动结束

        /**
         * 副本
         */

        void fb(int type, String name, boolean jin) throws Exception {
            if(timeCount(10, 0)){
                if (frequencyMap("fb_end_count", 2)) {
                    setTaskEnd();
                    return;
                }
            }

            if (gamePublicFuntion.mainScene()) {

                if (gamePublicFuntion.judgeStop(2)) {
                    result = mFairy.findPic(41, 4, 265, 70, "yb1.png");
                    if (result.sim > 0.73f) {
                        mFairy.onTap(0.73f, result, "已达到长安城 点击小地图", 1500);
                        mFairy.initMatTime();
                    } else {
                        mFairy.onTap(27, 34, 45, 53, "", 1000);
                    }
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
                mFairy.onTap(455,372,469,379, "百晓仙子", 1500);
                gamePublicFuntion.close();
                mFairy.initMatTime();
            }

            result = mFairy.findPic(902, 50, 1240, 543, "fb1.png");
            if (result.sim > 0.8f) {
                err = 0;
                mFairy.onTap(0.8f, result, "选择副本", 500);
            }

            result = mFairy.findPic("fb2.png");
            if (result.sim > 0.8f) {
                err=0;
                switch (type) {
                    case 1:
                        mFairy.onTap(278, 119, 333, 131, "普通", 1000);
                        break;
                    case 2:
                        mFairy.onTap(442, 118, 495, 135, "侠士", 1000);
                        break;
                }

                FindResult fbName = mFairy.findPic(211, 157, 1057, 401, name);
                if (fbName.sim > 0.8f) {

                    result = mFairy.findPic(fbName.x + 128, fbName.y + 276, fbName.x + 288, fbName.y + 401, "fb7.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("该副本已通关"));
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(fbName.x + 26, fbName.y + 370, fbName.x + 240, fbName.y + 481, "fb8.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("等级不足"));
                        setTaskEnd();
                        return;
                    }

                    if (jin) {
                        mFairy.onTap(fbName.x + 100, fbName.y + 422, fbName.x + 141, fbName.y + 436, "进入", 3000);
                        setTaskName(4);
                        return;
                    } else {
                        mFairy.onTap(1039, 97, 1059, 108, "", 500);
                        setTaskName(2);
                        return;
                    }
                } else {
                    if (frequencyMap("fbcount", 3)) {
                        setTaskEnd();
                        return;
                    }
                }


            }


        }
    }



    int zg_count = 0;
    public void ddzg() throws Exception {
        new TeamContent(mFairy, "带队捉鬼") {

            void create() throws Exception {
                super.create();
                activityName = "ddzg.png";
                activityType = 1;
                rankName = "ddzg1.png";
                zg_count = 0;
                tm.put("seeRank", System.currentTimeMillis());
            }

            void content_02() throws Exception {
                super.content_02();
                rank("ddzg2.png","zg.png");
            }

            void content_04() throws Exception {
                if (timeCount(8, 0)) {
                    gamePublicFuntion.home();
                    return;
                }

                result = mFairy.findPic(898, 4, 1258, 536, "ddzg3.png");
                if (result.sim > 0.85f) {
                    mFairy.initMatTime();
                    mFairy.onTap(0.85f, result, "抓鬼任务", 1000);
                } else {
                    if (gamePublicFuntion.shi()) {
                        mFairy.initMatTime();
                    } else {
                        result = mFairy.findPic("jia7.png");
                        mFairy.onTap(0.8f, result, "聊天框", 1000);
                    }
                }

                if (gamePublicFuntion.battle()) {
                    if (timeMap("seeRank", 180000)) {
                        see();
                    }
                }

                gamePublicFuntion.fail();

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3)) {
                        result = mFairy.findPic(1017, 151, 1179, 540, "ddzg4.png");
                        if (result.sim > 0.7f) {
                            err = 0;
                            mFairy.onTap(0.7f, result, "捉鬼", 500);
                            if (mapCount(0.75f, 462, 232, 591, 520, "jia4.png")) {
                                gamePublicFuntion.home();
                                setTaskName(2);
                            }
                            return;
                        }

                        taskSlide.slideRange(new int[]{4, 5, 6}, 2, 0);
                    } else {
                        err = 0;
                    }

                } else {
                    mFairy.initMatTime();
                }
            }

            void quxiao() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {
                    result = mFairy.findPic(565, 265, 917, 401, "ddzg5.png");
                    if (result.sim > 0.8f) {
                        int zg_num = getNumberAssembly(AtFairyConfig.getOption("zg_num"));
                        if(zg_num!=-1){
                            zg_count++;

                            LtLog.e(mFairy.getLineInfo("第"+zg_count+"轮"));

                            if(zg_count>=zg_num){
                                mFairy.onTap(0.8f, qx, "取消", 1000);
                                setTaskEnd();
                                return;
                            }
                        }else{
                            if (AtFairyConfig.getOption("zg").equals("2")) {
                                mFairy.onTap(0.8f, qx, "取消", 1000);
                                setTaskName(0);
                                return;
                            }
                        }

                        mFairy.onTap(729, 416, 763, 428, "继续抓鬼", 1000);
                        err = 0;
                        return;
                    }

                    if (frequencyMap("qxCount", 3)) {
                        mFairy.onTap(0.8f, qx, "取消", 1000);
                    }
                }
            }//取消

            boolean actEnd(FindResult act) throws Exception {
                int zg_num = getNumberAssembly(AtFairyConfig.getOption("zg_num"));
                if(zg_num!=-1){
                    return false;
                }

                if (AtFairyConfig.getOption("zg").equals("2")) {
                    FindResult result = mFairy.findPic(act.x + 100, act.y + 16, act.x + 215, act.y + 76,
                            new String[]{"ddzg6.png", "ddzg7.png"});
                    if (result.sim > 0.85f) {

                        return true;
                    }
                }
                return false;
            }
        };
    }//带队捉鬼

    public void lyrm() throws Exception {
        new TeamContent(mFairy, "绿烟如梦-普通") {

            void create() throws Exception {
                super.create();
                rankName = "lyrm2.png";
            }

            void init() throws Exception {
                result = mFairy.findPic("fb2.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.taskInit(0);
                }
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                fb(1, "lyrm3.png", false);
            }

            void content_02() throws Exception {
                super.content_02();
                rank("fb.png", "lyrm1.png");
            }

            void content_03() throws Exception {
                fb(1, "lyrm3.png", true);
            }

            void content_04() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(248, 1, 668, 93, "fb3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    if (gamePublicFuntion.mainScene()) {

                        result = mFairy.findPic(1013, 127, 1223, 514, "fb4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "跳过剧情", 1000);
                            return;
                        }

                        if (gamePublicFuntion.judgeStop(3)) {
                            mFairy.onTap(1098, 193, 1147, 217, "副本任务", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                    }

                } else {
                    result = mFairy.findPic(41, 4, 265, 70, "yb1.png");
                    if (result.sim > 0.73f) {
                        setTaskName(0);
                        return;
                    }

                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("failcount", 2)) {
                        gamePublicFuntion.home();
                        setTaskEnd();
                        return;
                    }
                }

                gamePublicFuntion.shi();
            }
        };
    }//绿烟如梦-普通

    public void lyrms() throws Exception {
        new TeamContent(mFairy, "绿烟如梦-侠士") {

            void create() throws Exception {
                super.create();
                rankName = "lyrm2.png";
            }

            void init() throws Exception {
                result = mFairy.findPic("fb2.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.taskInit(0);
                }
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                fb(2, "lyrm3.png", false);
            }

            void content_02() throws Exception {
                super.content_02();
                rank("fb.png", "lyrms1.png");
            }

            void content_03() throws Exception {
                fb(2, "lyrm3.png", true);
            }

            void content_04() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(248, 1, 668, 93, "fb3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    if (gamePublicFuntion.mainScene()) {

                        result = mFairy.findPic(1013, 127, 1223, 514, "fb4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "跳过剧情", 1000);
                            return;
                        }

                        if (gamePublicFuntion.judgeStop(3)) {
                            mFairy.onTap(1098, 193, 1147, 217, "副本任务", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                    }
                } else {
                    result = mFairy.findPic(41, 4, 265, 70, "yb1.png");
                    if (result.sim > 0.73f) {
                        setTaskName(0);
                        return;
                    }

                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("failcount", 2)) {
                        gamePublicFuntion.home();
                        setTaskEnd();
                        return;
                    }
                }

                gamePublicFuntion.shi();
            }
        };
    }//绿烟如梦-侠士

    public void lls() throws Exception {
        new TeamContent(mFairy, "琉璃碎-普通") {

            void create() throws Exception {
                super.create();
                rankName = "lls1.png";
            }

            void init() throws Exception {
                result = mFairy.findPic("fb2.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.taskInit(0);
                }
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                fb(1, "lls3.png", false);
            }

            void content_02() throws Exception {
                super.content_02();
                rank("fb.png", "lls2.png");
            }

            void content_03() throws Exception {
                fb(1, "lls3.png", true);
            }

            void content_04() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(248, 1, 668, 93, "fb3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    if (gamePublicFuntion.mainScene()) {

                        result = mFairy.findPic(1013, 127, 1223, 514, "fb4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "跳过剧情", 1000);
                            return;
                        }

                        if (gamePublicFuntion.judgeStop(3)) {
                            mFairy.onTap(1098, 193, 1147, 217, "副本任务", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                    }
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("failcount", 2)) {
                        gamePublicFuntion.home();
                        setTaskEnd();
                        return;
                    }
                }

                gamePublicFuntion.shi();
            }
        };
    }//琉璃碎-普通

    public void llss() throws Exception {
        new TeamContent(mFairy, "琉璃碎-侠士") {

            void create() throws Exception {
                super.create();
                rankName = "llss1.png";
            }

            void init() throws Exception {
                result = mFairy.findPic("fb2.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.taskInit(0);
                }
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                fb(2, "lls3.png", false);
            }

            void content_02() throws Exception {
                super.content_02();
                rank("fb.png", "llss2.png");
            }

            void content_03() throws Exception {
                fb(2, "lls3.png", true);
            }

            void content_04() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(248, 1, 668, 93, "fb3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    if (gamePublicFuntion.mainScene()) {

                        result = mFairy.findPic(1013, 127, 1223, 514, "fb4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "跳过剧情", 1000);
                            return;
                        }

                        if (gamePublicFuntion.judgeStop(3)) {
                            mFairy.onTap(1098, 193, 1147, 217, "副本任务", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                    }
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("failcount", 2)) {
                        gamePublicFuntion.home();
                        setTaskEnd();
                        return;
                    }
                }

                gamePublicFuntion.shi();
            }
        };
    }//琉璃碎-侠士

    public void ecy() throws Exception {
        new TeamContent(mFairy, "二重影-普通") {

            void create() throws Exception {
                super.create();
                rankName = "ecy1.png";
            }

            void init() throws Exception {
                result = mFairy.findPic("fb2.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.taskInit(0);
                }
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                fb(1, "ecy3.png", false);
            }

            void content_02() throws Exception {
                super.content_02();
                rank("fb.png", "ecy2.png");
            }

            void content_03() throws Exception {
                fb(1, "ecy3.png", true);
            }

            void content_04() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(248, 1, 668, 93, "fb3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    if (gamePublicFuntion.mainScene()) {

                        result = mFairy.findPic(1013, 127, 1223, 514, "fb4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "跳过剧情", 1000);
                            return;
                        }

                        if (gamePublicFuntion.judgeStop(3)) {
                            mFairy.onTap(1098, 193, 1147, 217, "副本任务", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                    }
                }


                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("failcount", 2)) {
                        gamePublicFuntion.home();
                        setTaskEnd();
                        return;
                    }
                }

                gamePublicFuntion.shi();
            }
        };
    }//二重影-普通

    public void ecys() throws Exception {
        new TeamContent(mFairy, "二重影-侠士") {

            void create() throws Exception {
                super.create();
                rankName = "ecys1.png";
            }

            void init() throws Exception {
                result = mFairy.findPic("fb2.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.taskInit(0);
                }
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                fb(2, "ecy3.png", false);
            }

            void content_02() throws Exception {
                super.content_02();
                rank("fb.png", "ecys2.png");
            }

            void content_03() throws Exception {
                fb(2, "ecy3.png", true);
            }

            void content_04() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(248, 1, 668, 93, "fb3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    if (gamePublicFuntion.mainScene()) {

                        result = mFairy.findPic(1013, 127, 1223, 514, "fb4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "跳过剧情", 1000);
                            return;
                        }

                        if (gamePublicFuntion.judgeStop(3)) {
                            mFairy.onTap(1098, 193, 1147, 217, "副本任务", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                    }
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("failcount", 2)) {
                        gamePublicFuntion.home();
                        setTaskEnd();
                        return;
                    }
                }

                gamePublicFuntion.shi();
            }
        };
    }//二重影-侠士

    public void wzs() throws Exception {
        new TeamContent(mFairy, "万丈山-普通") {

            void create() throws Exception {
                super.create();
                rankName = "wzs1.png";
            }

            void init() throws Exception {
                result = mFairy.findPic("fb2.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.taskInit(0);
                }
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                fb(1, "wzs3.png", false);
            }

            void content_02() throws Exception {
                super.content_02();
                rank("fb.png", "wzs2.png");
            }

            void content_03() throws Exception {
                fb(1, "wzs3.png", true);
            }

            void content_04() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(248, 1, 668, 93, "fb3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    if (gamePublicFuntion.mainScene()) {

                        result = mFairy.findPic(1013, 127, 1223, 514, "fb4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "跳过剧情", 1000);
                            return;
                        }

                        if (gamePublicFuntion.judgeStop(3)) {
                            mFairy.onTap(1098, 193, 1147, 217, "副本任务", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                    }
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("failcount", 2)) {
                        gamePublicFuntion.home();
                        setTaskEnd();
                        return;
                    }
                }

                gamePublicFuntion.shi();
            }
        };
    }//万丈山-普通

    public void wzss() throws Exception {
        new TeamContent(mFairy, "万丈山-侠士") {

            void create() throws Exception {
                super.create();
                rankName = "wzss1.png";
            }

            void init() throws Exception {
                result = mFairy.findPic("fb2.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.taskInit(0);
                }
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                fb(1, "wzs3.png", false);
            }

            void content_02() throws Exception {
                super.content_02();
                rank("fb.png", "wzss2.png");
            }

            void content_03() throws Exception {
                fb(1, "wzs3.png", true);
            }

            void content_04() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(248, 1, 668, 93, "fb3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    if (gamePublicFuntion.mainScene()) {

                        result = mFairy.findPic(1013, 127, 1223, 514, "fb4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "跳过剧情", 1000);
                            return;
                        }

                        if (gamePublicFuntion.judgeStop(3)) {
                            mFairy.onTap(1098, 193, 1147, 217, "副本任务", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                    }
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("failcount", 2)) {
                        gamePublicFuntion.home();
                        setTaskEnd();
                        return;
                    }
                }

                gamePublicFuntion.shi();
            }
        };
    }//万丈山-侠士

    public void lsj() throws Exception {
        new TeamContent(mFairy, "流沙净-普通") {

            void create() throws Exception {
                super.create();
                rankName = "lsj1.png";
            }

            void init() throws Exception {
                result = mFairy.findPic("fb2.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.taskInit(0);
                }
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                fb(1, "lsjs3.png", false);
            }

            void content_02() throws Exception {
                super.content_02();
                rank("fb.png", "lsj2.png");
            }

            void content_03() throws Exception {
                fb(1, "lsjs3.png", true);
            }

            void content_04() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(248, 1, 668, 93, "fb3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    if (gamePublicFuntion.mainScene()) {

                        result = mFairy.findPic(1013, 127, 1223, 514, "fb4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "跳过剧情", 1000);
                            return;
                        }

                        if (gamePublicFuntion.judgeStop(3)) {
                            mFairy.onTap(1098, 193, 1147, 217, "副本任务", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                    }
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("failcount", 2)) {
                        gamePublicFuntion.home();
                        setTaskEnd();
                        return;
                    }
                }

                gamePublicFuntion.shi();
            }
        };
    }//流沙净-普通

    public void lsjs() throws Exception {
        new TeamContent(mFairy, "流沙净-侠士") {

            void create() throws Exception {
                super.create();
                rankName = "lsjs1.png";
            }

            void init() throws Exception {
                result = mFairy.findPic("fb2.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.taskInit(0);
                }
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                fb(2, "lsjs3.png", false);
            }

            void content_02() throws Exception {
                super.content_02();
                rank("fb.png", "lsjs2.png");
            }

            void content_03() throws Exception {
                fb(2, "lsjs3.png", true);
            }

            void content_04() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(248, 1, 668, 93, "fb3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    if (gamePublicFuntion.mainScene()) {

                        result = mFairy.findPic(1013, 127, 1223, 514, "fb4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "跳过剧情", 1000);
                            return;
                        }

                        if (gamePublicFuntion.judgeStop(3)) {
                            mFairy.onTap(1098, 193, 1147, 217, "副本任务", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                    }
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("failcount", 2)) {
                        gamePublicFuntion.home();
                        setTaskEnd();
                        return;
                    }
                }

                gamePublicFuntion.shi();
            }
        };
    }//流沙净-侠士

    public void thdq() throws Exception {
        new TeamContent(mFairy, "桃花定情-普通") {

            void create() throws Exception {
                super.create();
                rankName = "thdq1.png";
            }

            void init() throws Exception {
                result = mFairy.findPic("fb2.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.taskInit(0);
                }
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                fb(1, "thdq3.png", false);
            }

            void content_02() throws Exception {
                super.content_02();
                rank("fb.png", "thdq2.png");
            }

            void content_03() throws Exception {
                fb(1, "thdq3.png", true);
            }

            void content_04() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(248, 1, 668, 93, "fb3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    if (gamePublicFuntion.mainScene()) {

                        result = mFairy.findPic(1013, 127, 1223, 514, "fb4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "跳过剧情", 1000);
                            return;
                        }

                        if (gamePublicFuntion.judgeStop(3)) {
                            mFairy.onTap(1098, 193, 1147, 217, "副本任务", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                    }
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("failcount", 2)) {
                        gamePublicFuntion.home();
                        setTaskEnd();
                        return;
                    }
                }

                gamePublicFuntion.shi();
            }
        };
    }//桃花定情-普通

    public void thdqs() throws Exception {
        new TeamContent(mFairy, "桃花定情-侠士") {

            void create() throws Exception {
                super.create();
                rankName = "thdq1.png";
            }

            void init() throws Exception {
                result = mFairy.findPic("fb2.png");
                if (result.sim < 0.8f) {
                    gamePublicFuntion.taskInit(0);
                }
                setTaskName(1);
                oneSecond = 0;
            }

            void content_01() throws Exception {
                fb(2, "thdq3.png", false);
            }

            void content_02() throws Exception {
                super.content_02();
                rank("fb.png", "thdqs1.png");
            }

            void content_03() throws Exception {
                fb(2, "thdq3.png", true);
            }

            void content_04() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(248, 1, 668, 93, "fb3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    if (gamePublicFuntion.mainScene()) {

                        result = mFairy.findPic(1013, 127, 1223, 514, "fb4.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "跳过剧情", 1000);
                            return;
                        }

                        if (gamePublicFuntion.judgeStop(3)) {
                            mFairy.onTap(1098, 193, 1147, 217, "副本任务", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                    }
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("failcount", 2)) {
                        gamePublicFuntion.home();
                        setTaskEnd();
                        return;
                    }
                }

                gamePublicFuntion.shi();
            }
        };
    }//桃花定情-侠士

    public void gen() throws Exception {
        new TaskContent(mFairy, "跟队") {

            void inOperation() throws Exception {
                gamePublicFuntion.auto();
                gamePublicFuntion.rightZoom();
                gamePublicFuntion.task();
                gamePublicFuntion.skip();
                gamePublicFuntion.battle();
                gamePublicFuntion.fail();
                use();
                quxiao();

                result = mFairy.findPic("renwu.png");
                mFairy.onTap(0.8f,result,1090,41,1107,59,"关闭头像界面",1000);

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
                gamePublicFuntion.useClose();
            }//使用

            void quxiao() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {

                    result = mFairy.findPic(400, 239, 875, 403, "gen1.png");
                    mFairy.onTap(0.75f, result, 732, 429, 765, 439, "接受邀请", 500);

                    if (frequencyMap("qxCount", 3)) {
                        mFairy.onTap(0.8f, qx, "取消", 500);
                    }
                } else {
                    frequencyInit("qxCount");
                }
            }//取消

            void see() throws Exception {
                while (mFairy.condit()) {
                    if (frequencyMap("seerr", 10)) {
                        gamePublicFuntion.close();
                        return;
                    }

                    LtLog.e(mFairy.getLineInfo("rankinit >>>"));

                    result = mFairy.findPic("rankScene.png");
                    if (result.sim > 0.8f) {

                        if (AtFairyConfig.getOption("generr").equals("1")) {
                            result = mFairy.findPic("rank4.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(1100, 37, 1113, 48, "", 300);
                                setTaskEnd();
                                return;
                            }
                        }
                        result = mFairy.findPic("rank3.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(1100, 37, 1113, 48, "", 300);
                            return;
                        }

                        result = mFairy.findPic("rank2.png");
                        mFairy.onTap(0.8f, result, "回归", 1000);

                        result = mFairy.findPic("rank5.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(1100, 37, 1113, 48, "", 300);
                            setTaskEnd();
                            return;
                        }
                    } else {
                        gamePublicFuntion.ranks();
                    }
                    gamePublicFuntion.qx();
                }
            }//see

            void init() throws Exception {
                LtLog.e(mFairy.getLineInfo("init >>> "));

                if (gamePublicFuntion.mainScene()) {
                    if (frequencyMap("main", 1)) {
                        setTaskName(1);
                        return;
                    }
                }

                gamePublicFuntion.close();

                quxiao();

                result = mFairy.findPic(1122, 171, 1274, 465, "mj5.png");
                mFairy.onTap(0.8f, result, "秘境离开", 500);

                gamePublicFuntion.fail();
                gamePublicFuntion.shi();

            }

            void content_01() throws Exception {
                if (timeCount(10, 0)) {
                    if (timeMap("genSeeRank", 120000)) {
                        see();
                    }
                }

                result = mFairy.findPic(678, 543, 759, 622, "gen3.png");
                mFairy.onTap(0.85f, result, "跟队-确定进入副本", 1000);

                result = mFairy.findPic("gen2.png");
                mFairy.onTap(0.8f, result, 722, 581, 765, 593, "前尘旧梦", 500);

                Thread.sleep(1000);

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(5)) {

                    } else {
                        err = 0;
                    }
                } else {
                    mFairy.initMatTime();
                }


            }
        };
    }//跟队

    public void ycmht() throws Exception {
        new TeamContent(mFairy, "勇闯迷魂塔") {

            void create() throws Exception {
                super.create();
                activityName = "ycmht.png";
                activityType = 1;
                rankName = "ycmht1.png";
                tm.put("seeRank", System.currentTimeMillis());
            }

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;
            }

            void content_02() throws Exception {
                super.content_02();
                rank("dshd.png", "ycmht2.png");
            }

            void content_04() throws Exception {
                if (timeCount(10, 4)) {
                    result = mFairy.findPic(228, 5, 747, 99, "activity.png");
                    if (result.sim > 0.8f) {
                        setTaskName(0);
                        return;
                    } else {
                        gamePublicFuntion.close();
                    }
                }

                result = mFairy.findPic(898, 4, 1258, 536, new String[]{"ycmht3.png", "ycmht5.png"});
                if (result.sim > 0.85f) {
                    mFairy.initMatTime();
                    mFairy.onTap(0.85f, result, "前往迷魂塔", 1000);
                } else {
                    if (gamePublicFuntion.shi()) {
                        mFairy.initMatTime();
                    }
                }

                if (gamePublicFuntion.battle()) {
                    if (timeMap("seeRank", 180000)) {
                        see();
                    }
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("fail_count", 3)) {
                        setTaskEnd();
                        return;
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3)) {
                        result = mFairy.findPic(1017, 151, 1179, 540, "ycmht4.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(0.75f, result, "迷魂塔", 1000);
                            return;
                        }
                        taskSlide.slideRange(new int[]{4, 5, 6}, 2, 0);
                    } else {
                        err = 0;
                    }
                } else {
                    mFairy.initMatTime();
                }
            }

            void quxiao() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {
                    if (frequencyMap("qxCount", 0)) {
                        mFairy.onTap(0.8f, qx, "取消", 1000);
                    }
                }
            }//取消
        };
    }//勇闯迷魂塔

    public void mpcg() throws Exception {
        new TeamContent(mFairy, "门派闯关") {

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;
            }

            void create() throws Exception {
                super.create();
                activityName = "mpcg.png";
                activityType = 1;
                rankName = "mpcg1.png";
                tm.put("seeRank", System.currentTimeMillis());
            }

            void content_02() throws Exception {
                super.content_02();
                rank("dshd.png", "mpcg2.png");
            }

            void content_04() throws Exception {
                if (timeCount(10, 4)) {
                    result = mFairy.findPic(228, 5, 747, 99, "activity.png");
                    if (result.sim > 0.8f) {
                        setTaskName(0);
                        return;
                    } else {
                        gamePublicFuntion.close();
                    }
                }

                result = mFairy.findPic(898, 4, 1258, 536, new String[]{"mpcg3.png", "mpcg5.png"});
                if (result.sim > 0.85f) {
                    mFairy.initMatTime();
                    mFairy.onTap(0.85f, result, "领取任务或进入战斗", 1000);
                } else {
                    if (gamePublicFuntion.shi()) {
                        mFairy.initMatTime();
                    }
                }

                if (gamePublicFuntion.battle()) {
                    if (timeMap("seeRank", 180000)) {
                        see();
                    }
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("fail_count", 3)) {
                        setTaskEnd();
                        return;
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3)) {
                        result = mFairy.findPic(1017, 151, 1179, 540, "mpcg4.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(0.75f, result, "门派闯关", 1000);
                            return;
                        }
                        taskSlide.slideRange(new int[]{4, 5, 6}, 2, 0);
                    } else {
                        err = 0;
                    }
                } else {
                    mFairy.initMatTime();
                }
            }

            void quxiao() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {
                    if (frequencyMap("qxCount", 0)) {
                        mFairy.onTap(0.8f, qx, "取消", 1000);
                    }
                }
            }//取消
        };
    }//门派闯关

    public void hdxb() throws Exception {
        new TeamContent(mFairy, "海底寻宝") {

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;
            }

            void create() throws Exception {
                super.create();
                activityName = "hdxb.png";
                activityType = 1;
                rankName = "hdxb1.png";
                tm.put("seeRank", System.currentTimeMillis());
            }

            void content_02() throws Exception {
                super.content_02();
                rank("dshd.png", "hdxb2.png");
            }

            void content_04() throws Exception {
                if (timeCount(10, 4)) {
                    result = mFairy.findPic(228, 5, 747, 99, "activity.png");
                    if (result.sim > 0.8f) {
                        setTaskName(0);
                        return;
                    } else {
                        gamePublicFuntion.close();
                    }
                }


                result = mFairy.findPic(898, 4, 1258, 536, new String[]{"hdxb3.png", "hdxb8.png"});
                if (result.sim > 0.85f) {
                    mFairy.initMatTime();
                    mFairy.onTap(0.85f, result, "领取任务或进入战斗", 1000);
                } else {

                    switch (AtFairyConfig.getOption("hy")) {
                        case "1":
                            result = mFairy.findPic(898, 4, 1258, 536, "hdxb5.png");
                            mFairy.onTap(0.85f, result, "普通难度", 1500);
                            break;
                        case "2":
                            result = mFairy.findPic(898, 4, 1258, 536, "hdxb6.png");
                            mFairy.onTap(0.85f, result, "普通难度", 1500);
                            break;
                        case "3":
                            result = mFairy.findPic(898, 4, 1258, 536, "hdxb7.png");
                            mFairy.onTap(0.85f, result, "普通难度", 1500);
                            break;
                    }


                    if (frequencyMap("shi", 1)) {
                        if (gamePublicFuntion.shi()) {
                            mFairy.initMatTime();
                        }
                    }
                }

                if (gamePublicFuntion.battle()) {
                    if (timeMap("seeRank", 180000)) {
                        see();
                    }
                }

                if (gamePublicFuntion.fail()) {
                    if (frequencyMap("fail_count", 3)) {
                        setTaskEnd();
                        return;
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3)) {
                        result = mFairy.findPic(1017, 151, 1179, 540, "hdxb4.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(0.75f, result, "海底寻宝", 1000);
                            return;
                        }
                        taskSlide.slideRange(new int[]{4, 5, 6}, 2, 0);
                    } else {
                        err = 0;
                    }
                } else {
                    mFairy.initMatTime();
                }
            }

            void quxiao() throws Exception {
                FindResult qx = gamePublicFuntion.qx();
                if (qx.sim > 0.8f) {
                    if (frequencyMap("qxCount", 0)) {
                        mFairy.onTap(0.8f, qx, "取消", 1000);
                    }
                }
            }//取消
        };
    }//海底寻宝
}
