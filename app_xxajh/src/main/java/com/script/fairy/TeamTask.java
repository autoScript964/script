package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

/**
 * Created by user on 2019/6/3.
 */

public class TeamTask {
    public GamePublicFuntion gamePublicFuntion;
    public FindResult result;
    public AtFairyImpl mFairy;
    public SingleTask singleTask;
    private int hour;
    private int minute;
    private int week;
    public String activity_name;
    public int activity_type;
    public int ranks_num = 3;

    public AtFairyImpl getmFairy() {
        return mFairy;
    }

    public TeamTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        singleTask = new SingleTask(ypFairy);
    }

    abstract class TeamTask_Content extends TaskContent {

        public TeamTask_Content(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);
        }

        void create() throws Exception {
            super.create();
            f=false;
            gamePublicFuntion.activity_bool = true;
            if (!AtFairyConfig.getOption("ranks_num").equals("")) {
                ranks_num = Integer.parseInt(AtFairyConfig.getOption("ranks_num"));
            }
        }

        void init() throws Exception {
            gamePublicFuntion.init(true);
            setTaskName(1);
        }

        boolean activity_end(FindResult act) throws Exception {
            result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233,
                    new String[]{"wan.png", "wan1.png", "wan2.png", "wan3.png", "wan4.png"});
            if (result.sim > 0.8f) {
                setTaskEnd();
                return true;
            }

            result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233, "qian.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(28, 8, 62, 32, "任务可以执行>>>", 1000);
                oneSecond = 0;
                frequencyInit("actError");
                setTaskName(2);
                return true;
            }
            return false;
        }

        void content_01() throws Exception {
            if (timeCount(10, 0)) {
                gamePublicFuntion.close(3);
                if (activity_name == "yzmx.png") {
                    return;
                }
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

                    result = mFairy.findPic("activity2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "", 800);

                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(300);

                            result = mFairy.findPic(884, 226, 1019, 630, "activity3.png");
                            mFairy.onTap(0.8f, result, 731, 661, 785, 698, "关闭活动奖励界面", 500);
                        }
                    }
                }

                FindResult act = mFairy.findPic(326, 97, 1231, 420, activity_name);
                if (act.sim > 0.85f) {

                    if (activity_end(act)) {
                        oneSecond = 0;
                        return;
                    }
                }

                if (GamePublicFuntion.activity_bool == false) {
                    activitySlide.slideRange(new int[]{3, 5, 6, 7}, 3);
                } else {
                    activitySlide.slideRange(new int[]{3, 5, 6, 7, 8}, 2, 0);
                }
            }
        }

        boolean cancel() throws Exception {

            result = mFairy.findPic(359, 181, 743, 420, "jhl.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(560, 368, 572, 375, "", 500);
                mFairy.onTap(795, 418, 841, 438, "", 500);
                return true;
            }

            return false;
        }

        void inOperation() throws Exception {
            result = mFairy.findPic(1005, 641, 1263, 710, "ban.png");
            if (result.sim > 0.8f) {
                err = 0;
            }

            result = mFairy.findPic(462, 388, 870, 593, "queding.png");
            if (result.sim > 0.8f) {
                err = 0;
            }

            FindResult qx = gamePublicFuntion.cancel();
            if (qx.sim > 0.75f) {

                result = mFairy.findPic(384, 214, 894, 381, "rank23.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(785, 411, 841, 443, "队伍人数不足", 500);
                    gamePublicFuntion.init(false);
                    setTaskName(2);
                    return;
                }

                if (cancel()) {
                    return;
                }

                if (frequencyMap("qx", 1)) {
                    mFairy.onTap(0.75f, qx, "取消", 500);
                }

            } else {
                frequencyInit("qx");
            }

            result = mFairy.findPic("zoom2.png");
            mFairy.onTap(0.8f, result, "左侧任务栏", 500);

            result = mFairy.findPic("fh.png");
            mFairy.onTap(0.8f, result, "安全区复活", 500);

            gamePublicFuntion.use();

            if (gamePublicFuntion.mainScene()) {
                if (timeMap("zhao", 120000, true)) {

                    for (int i = 0; i < 3; i++) {
                        Thread.sleep(300);

                        result = mFairy.findPic(2, 119, 48, 403, "rank2.png");
                        mFairy.onTap(0.92f, result, "组队", 1000);

                        result = mFairy.findPic(2, 119, 48, 403, "rank1.png");
                        if (result.sim > 0.92f) {
                            break;
                        }
                    }

                    result = mFairy.findPic(49, 127, 271, 447, "rank21.png");
                    mFairy.onTap(0.92f, result, "自动匹配", 1000);

                    result = mFairy.findPic(83, 341, 229, 448, "rank22.png");
                    mFairy.onTap(0.92f, result, "召集", 500);
                }
            }
            result = mFairy.findPic(new String[]{"tiao.png", "tiao1.png"});
            mFairy.onTap(0.8f, result, "跳过", 500);
        }

        void ranks(String mb, String mb1, String mb2) throws Exception {
            timeCount(10, 0);

            result = mFairy.findPic(new String[]{"rank5.png", "rank8.png"});
            if (result.sim > 0.8f) {
                err = 0;

                result = mFairy.findPic("rank6.png");
                mFairy.onTap(0.8f, result, "创建队伍", 500);

                result = mFairy.findPic("rank10.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(450, 600, 595, 709, "rank9.png");
                    mFairy.onTap(0.8f, result, "离队", 500);
                    return;
                }

                result = mFairy.findPic("rank19.png");
                if (result.sim > 0.8f) {
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(300);

                        result = mFairy.findPic("rank20.png");
                        mFairy.onTap(0.8f, result, "同意", 500);
                    }
                    mFairy.onTap(117, 32, 165, 46, "关闭申请页面", 1500);
                }

                FindResult go = mFairy.findPic(new String[]{"rank11.png", "rank25.png"});
                if (go.sim > 0.8f) {

                    if(getName().equals("风云录") && f==false){
                        mFairy.onTap(160, 32, 176, 47, "目标不匹配,更改目标", 500);
                        return;
                    }

                    result = mFairy.findPic(143, 6, 475, 71, mb);
                    if (result.sim > 0.8f || f) {

                        result = mFairy.findPic("rank12.png");
                        mFairy.onTap(0.8f, result, "开始匹配", 500);


                        if (gamePublicFuntion.ranks_num() >= ranks_num) {

                            result = mFairy.findPic("rank16.png");
                            mFairy.onTap(0.8f, result, "召唤跟随", 500);

                            mFairy.onTap(0.8f, go, "前往活动", 1000);
                            f=false;
                            setTaskName(3);
                            return;
                        }


                        if (timeMap("han", 30000, true)) {
                            result = mFairy.findPic("rank14.png");
                            mFairy.onTap(0.8f, result, "喊话", 500);
                        }

                        result = mFairy.findPic("rank18.png");
                        mFairy.onTap(0.92f, result, result.x - 10, result.y + 10, result.x - 5, result.y + 15, "申请", 500);

                    } else {
                        mFairy.onTap(160, 32, 176, 47, "目标不匹配,更改目标", 500);
                    }
                }

                result = mFairy.findPic("rank7.png");
                if (result.sim > 0.8f) {

                    if(getName().equals("风云录")){
                        f=true;
                    }

                    if (frequencyMap("mbError", 6)) {
                        mFairy.onTap(911, 661, 948, 685, "", 500);
                        return;
                    }

                    result = mFairy.findPic("rank24.png");
                    if (result.sim > 0.8f) {
                        singleTask.li_ranks();
                        setTaskName(0);
                        return;
                    }

                    result = mFairy.findPic(2, 23, 201, 711, mb1);
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "点击 mb1", 1000);

                        if (mb2 == null) {
                            result = mFairy.findPic("rank17.png");
                            mFairy.onTap(0.9f, result, "自动匹配", 500);

                            mFairy.onTap(398, 657, 448, 680, "确定", 1000);
                            return;
                        }
                    }

                    if (mb2 != null) {
                        result = mFairy.findPic(2, 23, 201, 711, mb2);
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "点击 mb2", 1000);

                            result = mFairy.findPic("rank17.png");
                            mFairy.onTap(0.9f, result, "自动匹配", 500);

                            mFairy.onTap(398, 657, 448, 680, "确定", 1000);
                            return;
                        }
                    }

                    if (frequencyMap("mb_slide", 2)) {
                        mFairy.ranSwipe(94, 344, 111, 556, 2, 500, (long) 1000);
                    }
                    return;
                }
            } else {
                result = mFairy.findPic("rank15.png");
                mFairy.onTap(0.8f, result, "招募频道", 500);

                gamePublicFuntion.clickRanks();
            }
        }

    }

    public void yry() throws Exception {
        new TeamTask_Content(mFairy, "一日游") {

            void create() throws Exception {
                super.create();
                activity_name = "yry.png";
                activity_type = 1;
            }

            boolean activity_end(FindResult act) throws Exception {

                if (AtFairyConfig.getOption("yry").equals("2")) {

                    result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233,
                            new String[]{"wan.png", "wan1.png", "wan2.png",});
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return true;
                    }

                    result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233, new String[]{"qian.png", "wan3.png", "wan4.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(28, 8, 62, 32, "任务可以执行>>>", 1000);
                        oneSecond = 0;
                        frequencyInit("actError");
                        setTaskName(2);
                        return true;
                    }

                } else {

                    result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233,
                            new String[]{"wan.png", "wan1.png", "wan2.png", "wan3.png", "wan4.png"});
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return true;
                    }

                    result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233, "qian.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(28, 8, 62, 32, "任务可以执行>>>", 1000);
                        oneSecond = 0;
                        frequencyInit("actError");
                        setTaskName(2);
                        return true;
                    }
                }
                return false;
            }

            void content_02() throws Exception {
                ranks("yry2.png", "yry1.png", null);
            }

            void content_03() throws Exception {
                timeCount(10, 0);
                gamePublicFuntion.task();
                result = mFairy.findPic("rank11.png");
                mFairy.onTap(0.8f, result, "前往活动", 1000);

                result = mFairy.findPic("yry3.png");
                mFairy.onTap(0.8f, result, "前往", 2000);

                result = mFairy.findPic(923, 489, 1071, 588, "yry6.png");
                mFairy.onTap(0.8f, result, "继续", 2000);

                result = mFairy.findPic(900, 205, 1196, 570, "yry4.png");
                mFairy.onTap(0.8f, result, "参加一日游", 1000);

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                        result = mFairy.findPic(47, 91, 114, 458, "yry5.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            mFairy.onTap(0.75f, result, "一日游", 1000);
                            mFairy.initMatTime();
                            return;
                        }
                        taskSlide.slideRange(new int[]{3, 5, 7}, 2, 0);
                    }
                }
            }
        };
    }//一日游

    public void jhhb() throws Exception {
        new TeamTask_Content(mFairy, "江湖话本") {
            void create() throws Exception {
                super.create();
                activity_name = "jhhb.png";
                activity_type = 1;
            }

            void content_02() throws Exception {
                ranks("jhhb2.png", "jhhb1.png", null);
            }

            void content_03() throws Exception {
                timeCount(10, 0);
                gamePublicFuntion.task();
                result = mFairy.findPic("rank11.png");
                mFairy.onTap(0.8f, result, "前往活动", 1000);

                result = mFairy.findPic("jhhb6.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("jhhb7.png");
                    if (result.sim > 0.92f) {
                        mFairy.onTap(1171, 122, 1189, 134, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("jhhb3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "进入副本", 1000);
                        if (frequencyMap("fb_err", 10)) {
                            singleTask.li_ranks();
                            gamePublicFuntion.init(false);
                            setTaskName(2);
                            return;
                        }
                    }
                }

                result = mFairy.findPic(531, 642, 756, 699, "jhhb5.png");
                mFairy.onTap(0.8f, result, "点击离开副本", 1000);

               /* result = mFairy.findPic(493,139,810,213, "jhhb4.png");
                if(result.sim>0.8f){
                    err=0;
                }*/

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    frequencyInit("fb_err");
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                if (gamePublicFuntion.mainScene() == false) {
                    Thread.sleep(2000);
                }

            }
        };
    }//江湖话本

    public void wbgcm() throws Exception {
        new TeamTask_Content(mFairy, "五霸冈除魔") {

            void create() throws Exception {
                super.create();
                activity_name = "wbgcm.png";
                activity_type = 1;
            }

            void content_02() throws Exception {
                ranks("wbgcm2.png", "wbgcm1.png", null);
            }

            void content_03() throws Exception {
                timeCount(10, 0);
                gamePublicFuntion.task();
                result = mFairy.findPic("rank11.png");
                mFairy.onTap(0.8f, result, "前往活动", 1000);

                result = mFairy.findPic(893, 120, 1202, 575, "wbgcm3.png");
                mFairy.onTap(0.8f, result, "挑战", 1000);

             /*   result = mFairy.findPic(450,131,826,241, "wbgcm4.png");
                if(result.sim>0.8f){
                    err=0;
                }*/

                result = mFairy.findPic("wbgcm5.png");
                mFairy.onTap(0.8f, result, 896, 642, 923, 683, "完成", 1000);

                result = mFairy.findPic("wbgcm6.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(421, 524, 440, 546, "除魔奖励", 1000);
                    mFairy.onTap(628, 421, 678, 435, "", 500);
                    mFairy.onTap(1171, 119, 1188, 137, "", 500);
                    setTaskEnd();
                    return;
                }

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                    }
                } else {
                    Thread.sleep(2000);
                }
            }
        };
    }//五霸冈除魔


    public boolean f = false;
    public void fyl() throws Exception {
        new TeamTask_Content(mFairy, "风云录") {

            void create() throws Exception {
                super.create();
                activity_name = "fyl.png";
                activity_type = 3;
            }

            void init() throws Exception {
                super.init();
                f=false;
            }

            void content_02() throws Exception {
                switch (AtFairyConfig.getOption("fyl")) {
                    case "1":
                        ranks("fyla1.png", "fyl1.png", "fyla.png");//福威往事
                        break;
                    case "2":
                        ranks("fylb1.png", "fyl1.png", "fylb.png");//华山密洞
                        break;
                    case "3":
                        ranks("fylc1.png", "fyl1.png", "fylc.png");//华山密洞(宗)
                        break;
                    case "4":
                        ranks("fyld1.png", "fyl1.png", "fyld.png");//竹林秘泾
                        break;
                    case "5":
                        ranks("fyle1.png", "fyl1.png", "fyle.png");//竹林秘泾(宗)
                        break;
                    case "6":
                        ranks("fylf1.png", "fyl1.png", "fylf.png");//五霸冈
                        break;
                    case "7":
                        ranks("fylg1.png", "fyl1.png", "fylg.png");//五霸冈(宗)
                        break;

                    case "8":
                        ranks("fylg1.png", "fyl1.png", "f1.png");
                        break;
                    case "9":
                        ranks("fylg1.png", "fyl1.png", "f2.png");
                        break;
                    case "10":
                        ranks("fylg1.png", "fyl1.png", "f3.png");
                        break;
                    case "11":
                        ranks("fylg1.png", "fyl1.png", "f4.png");
                        break;
                    case "12":
                        ranks("fylg1.png", "fyl1.png", "f5.png");
                        break;
                    case "13":
                        ranks("fylg1.png", "fyl1.png", "f6.png");
                        break;
                    case "14":
                        ranks("fylg1.png", "fyl1.png", "f7.png");
                        break;
                    case "15":
                        ranks("fylg1.png", "fyl1.png", "f8.png");
                        break;
                    case "16":
                        ranks("fylg1.png", "fyl1.png", "f9.png");
                        break;
                    case "17":
                        ranks("fylg1.png", "fyl1.png", "f10.png");
                        break;
                    case "18":
                        ranks("fylg1.png", "fyl1.png", "f11.png");
                        break;
                    case "19":
                        ranks("fylg1.png", "fyl1.png", "f12.png");
                        break;





                        default:
                            ranks("fyla1.png", "fyl1.png", "fyla.png");//福威往事
                            break;
                }
            }

            void content_03() throws Exception {
                timeCount(15, 0);
                gamePublicFuntion.task();

                result = mFairy.findPic("rank11.png");
                mFairy.onTap(0.8f, result, "前往活动", 1000);

                result = mFairy.findPic("fyl2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("fyl8.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1164, 93, 1185, 111, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("fyl4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1164, 93, 1185, 111, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("fyl3.png");
                    mFairy.onTap(0.8f, result, "进入副本", 1000);
                }

                result = mFairy.findPic(514, 527, 846, 651, "fyl6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(849, 594, 885, 618, "", 500);
                    setTaskName(0);
                    return;
                }

                if (gamePublicFuntion.fuben()) {
                    err = 0;

                    result = mFairy.findPic("fberr.png");
                    if (result.sim > 0.85f) {
                        gamePublicFuntion.likai();
                        setTaskName(0);
                        return;
                    }
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

                if (gamePublicFuntion.mainScene() == false) {
                    Thread.sleep(2000);
                }
            }
        };
    }//风云录

    /**
     * 限时任务
     */

    int go1, go2, go3, go4, go5, go6, go7, go8, go9, go10, go11, go12, go13, go14, go15,go16=0;
    int go = 1;

    public void limitless() throws Exception {
        new TeamTask_Content(mFairy, "无限挂机") {

            void create() throws Exception {
                super.create();
                ranks_num = 1;
                activity_name = "yzmx.png";
                activity_type = 2;
                go=1;
                go1 = 0;
                go2 = 0;
                go3 = 0;
                go4 = 0;
                go5 = 0;
                go6 = 0;
                go7 = 0;
                go8 = 0;
                go9 = 0;
                go10 = 0;
                go11 = 0;
                go12 = 0;
                go13 = 0;
                go14 = 0;
                go15 = 0;
                go16 = 0;
            }

            void content_02() throws Exception {
                ranks("yzmx2.png", "yzmx1.png", null);
            }

            void inOperation() throws Exception {
                super.inOperation();
                if (xianshi()) {
                    setTaskName(0);
                    return;
                }
            }

            boolean activity_end(FindResult act) throws Exception {

                frequencyInit("actError");

                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233,
                        new String[]{"wan.png", "wan1.png", "wan2.png", "wan4.png"});
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return true;
                }

                result = mFairy.findPic(act.x - 10, act.y + 120, act.x + 169, act.y + 233, new String[]{"qian.png", "wan3.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(28, 8, 62, 32, "任务可以执行>>>", 1000);
                    oneSecond = 0;
                    frequencyInit("actError");
                    setTaskName(2);
                    return true;
                }
                return false;
            }

            boolean xianshi() throws Exception {
                boolean bool = false;
                while (true) {
                    week = mFairy.week();
                    hour = mFairy.dateHour();
                    minute = mFairy.dateMinute();
                    if (hour == 0 || hour == 24) {
                        go=0;
                        go1 = 0;
                        go2 = 0;
                        go3 = 0;
                        go4 = 0;
                        go5 = 0;
                        go6 = 0;
                        go7 = 0;
                        go8 = 0;
                        go9 = 0;
                        go10 = 0;
                        go11 = 0;
                        go12 = 0;
                        go13 = 0;
                        go14 = 0;
                        go15 = 0;
                        go16 = 0;
                    }

                    if (AtFairyConfig.getOption("chongqi").equals("1") && go == 0) {

                        if (hour == 5 && minute > 10) {
                            singleTask.setUp();
                            singleTask.li_ranks();
                            singleTask.mpxx();
                            singleTask.ywc();
                            singleTask.shyx();
                            singleTask.jgdt();
                            singleTask.mxsl();
                            singleTask.xktz();
                            singleTask.jhpk();
                            singleTask.bhqd();
                            singleTask.bhfc();
                            singleTask.bhrc();
                            singleTask.bhjs();
                            singleTask.yzxs();
                            singleTask.xkwp();
                            singleTask.sljs();
                            singleTask.ssqy();
                            singleTask.shxz();
                            singleTask.lm();
                            singleTask.bc();
                            singleTask.ling1();
                            singleTask.ling2();
                            jhhb();
                            fyl();
                            yry();
                            create();
                            bool = true;
                            break;
                        }
                    }

                    if (AtFairyConfig.getOption("mpsy").equals("1") && go1 == 0) {//门派授业
                        if (hour >= 18) {
                            singleTask.mpsy();
                            go1 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("hslj").equals("1") && go2 == 0) {//华山论剑
                        if (hour >= 12) {
                            singleTask.hslj();
                            go2 = 1;
                            bool = true;
                            continue;
                        }
                    }


                    if (AtFairyConfig.getOption("hszm").equals("1") && go3 == 0) {//华山争鸣
                        if (hour >= 12) {
                            singleTask.hszm();
                            go3 = 1;
                            bool = true;
                            continue;
                        }


                    }

                    if (!AtFairyConfig.getOption("cszb").equals("") && go16 == 0) {//厨神争霸
                        if (hour >= 12) {
                            singleTask.cszb();
                            go16 = 1;
                            bool = true;
                            continue;
                        }
                    }


                    if (AtFairyConfig.getOption("sjsl").equals("1") && go4 == 0) {//世界首领
                        if (hour == 19 && minute > 30) {
                            singleTask.sjsl();
                            go4 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("dmm").equals("1") && go5 == 0) {//躲猫猫
                        if (week == 6 &&
                                (((hour == 12 && minute > 30) || (hour == 13) || (hour == 14 && minute < 30))
                                        ||
                                        (hour == 21 || hour == 22))) {
                            singleTask.dmm();
                            go5 = 1;
                            bool = true;
                            continue;
                        }
                    }


                    if (AtFairyConfig.getOption("ssfy").equals("1") && go6 == 0) {//少室风云
                        if (hour == 13 || hour == 14 || hour == 15 || hour == 20 || hour == 21 || hour == 22) {
                            singleTask.ssfy();
                            go6 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("xsjj").equals("1") && go7 == 0) {//虚生绝境
                        if (
                                (week == 7 && ((hour == 12 && minute > 30) || (hour == 13) || (hour == 14 && minute < 30))) ||
                                        ((week == 3 || week == 7) && (hour == 21 || hour == 22))
                                ) {

                            singleTask.xsjj();
                            go7 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("5995").equals("1") && go8 == 0) {//帮会温泉
                        if (hour == 19) {
                            singleTask.bhwq();
                            go8 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("djrm").equals("1") && go9 == 0) {//刀剑如梦
                        if (
                                (week == 5 && ((hour == 12 && minute > 30) || (hour == 13) || (hour == 14 && minute < 30))) ||
                                        ((week == 1 || week == 5) && (hour == 21 || hour == 22))
                                ) {

                            singleTask.djrm();
                            go9 = 1;
                            bool = true;
                            continue;
                        }
                    }


                    if (AtFairyConfig.getOption("ddqx").equals("1") && go10 == 0) {//岛防前线
                        if ((week == 1 || week == 4) && hour == 20) {
                            singleTask.ddqx();
                            go10 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("shzf").equals("1") && go11 == 0) {//沙海争锋
                        if ((week == 4 && (hour == 21 || hour == 22))) {
                            singleTask.shzf();
                            go11 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("wdlmt").equals("1") && go12 == 0) {//五毒岭探秘
                        if ((hour == 11 || hour == 14 || hour == 17 || hour == 23) && minute < 30) {
                            singleTask.wdlmt();
                            go12 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    break;
                }
                return bool;
            }//限时任务

            void content_03() throws Exception {
                timeCount(20, 0);
                gamePublicFuntion.task();

                result = mFairy.findPic("rank11.png");
                mFairy.onTap(0.8f, result, "前往活动", 1000);

                if (gamePublicFuntion.fuben()) {
                    err = 0;
                    gamePublicFuntion.jie();
                    gamePublicFuntion.auto_battle();
                }

            }
        };
    }//


}