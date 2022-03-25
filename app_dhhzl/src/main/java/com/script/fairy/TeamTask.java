package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class TeamTask {//组队任务  带队 跟队
    AtFairyImpl mFairy;
    FindResult result;
    GamePublicFuntion gamePublicFuntion;
    SingleTask singleTask;

    public int teamNum = 3;

    public TeamTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(mFairy);
        singleTask = new SingleTask(mFairy);
    }

    abstract class team extends TaskContent {
        public team(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);
        }

        void create() throws Exception {
            super.create();
            TaskMain.actitityInitSildeSwitch = true;

            teamNum = !AtFairyConfig.getOption("ranknum").equals("")?Integer.parseInt(AtFairyConfig.getOption("ranknum")):3;

            LtLog.e(mFairy.getLineInfo("【用户要求人数："+teamNum+"】"));

            mFairy.initMatTime();
        }

        void init() throws Exception {
            TaskMain.XUNHANG = false;
            gamePublicFuntion.init(true);
            frequencyInit("actEndCount");
            frequencyInit("actErr");
            TaskMain.actitityInitSildeSwitch = true;
            initOnceJudge("rankmb");
            setTaskName(1);
        }

        void activity(int type, String taskName) throws Exception {

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
                if (act.sim > 0.62f) {

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
                        setTaskName(3);
                        return;
                    }

                    result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, new String[]{"actend1.png", "actend2.png"});
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


                if(timeCount(7, 99)){
                    LtLog.e(mFairy.getLineInfo("活动没有找到"));
                    return;
                }
            } else{
                if(frequencyMap("actEndCount",5)){
                    setTaskName(0);
                    return;
                }
            }
        }

        public void zact(String taskName) throws Exception {
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
                    LtLog.e(mFairy.getLineInfo("没有发现参加,End!"));
                    setTaskEnd();
                }
            }
            mFairy.touchUp();
        }//避免喇叭

        public void rank(String rankName, String rankName1) throws Exception {

            result = mFairy.findPic("activityScene.png");
            mFairy.onTap(0.8f, result, 1230, 18, 1247, 32, "关闭活动界面", 500);

            result = mFairy.findPic("rank5.png");
            mFairy.onTap(0.7f, result, "创建队伍", 1000);

            result = mFairy.findPic("rank13.png");
            mFairy.onTap(0.8f, result, "发送", 1000);

            result = mFairy.findPic("rank7.png");
            if (result.sim > 0.8f) {
                LtLog.e(mFairy.getLineInfo("【组队界面】"));
                err = 0;

                result = mFairy.findPic("rank14.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("【切换目标界面】"));

                    onceJudge("rankmb");

                    result = mFairy.findPic(357, 135, 573, 470, rankName1);
                    if (result.sim > 0.72f) {
                        mFairy.onTap(0.72f, result, "发现目标", 1000);

                       /* if (!getName().equals("巡逻舰队")) {
                            mFairy.onTap(610,176,616,187,"",500);
                            mFairy.onTap(602,237,614,245,"",500);
                            mFairy.onTap(607,302,614,307,"",500);
                        }*/

                        if (getName().equals("霸主挑战")) {
                            bz:
                            for (int i = 0; i < 3; i++) {
                                switch (AtFairyConfig.getOption("bztz")) {
                                    case "2":
                                        result = mFairy.findPic(357, 135, 573, 470, "bztz4.png");
                                        break;
                                    case "3":
                                        result = mFairy.findPic(357, 135, 573, 470, "bztz5.png");
                                        break;
                                    default:
                                        result = mFairy.findPic(357, 135, 573, 470, "bztz3.png");
                                        break;
                                }

                                if (result.sim > 0.8f) {
                                    mFairy.onTap(0.8f, result, "发现目标", 500);
                                    break bz;
                                } else {
                                    mFairy.ranSwipe(486, 414, 486, 211, 500, 1000);
                                }
                            }
                        }

                        if (getName().equals("近卫舰队")) {
                            bz:
                            for (int i = 0; i < 3; i++) {
                                switch (AtFairyConfig.getOption("jw")) {
                                    case "1":
                                    case "2":
                                    case "3":
                                    case "4":
                                    case "5":
                                        result = mFairy.findPic(357, 135, 573, 470, "jw3.png");
                                        break;
                                    case "6":
                                    case "7":
                                    case "8":
                                    case "9":
                                    case "10":
                                        result = mFairy.findPic(357, 135, 573, 470, "jw4.png");
                                        break;
                                    default:
                                        result = mFairy.findPic(357, 135, 573, 470, "jw5.png");
                                        break;
                                }

                                if (result.sim > 0.8f) {
                                    mFairy.onTap(0.8f, result, "发现目标", 500);
                                    break bz;
                                } else {
                                    mFairy.ranSwipe(486, 414, 486, 211, 500, 1000);
                                }
                            }
                        }

                        mFairy.onTap(542, 653, 593, 673, "", 1000);
                        frequencyInit("mb");
                        frequencyInit("mberr");
                        frequencyInit("actErr");
                        return;
                    }

                    if (frequencyMap("mb", 1)) {
                        mFairy.ranSwipe(486, 414, 486, 211, 500, 1000);
                    }

                    if (frequencyMap("mberr", 10)) {

                        if(frequencyMap("actErr",1)){
                            setTaskEnd();
                            return;
                        }
                        mFairy.onTap(542, 653, 593, 673, "目标场景异常", 1000);
                    }

                    return;
                }

                int rankNum = gamePublicFuntion.rankNum();
                LtLog.e(mFairy.getLineInfo("当前队伍人数："+rankNum));

                if (rankNum == 5) {//满人直接开始任务
                    TaskMain.rankBool = false;
                    setTaskName(2);
                    mFairy.onTap(1223, 15, 1256, 37, "", 500);
                    return;
                }

                result = mFairy.findPic(87, 55, 282, 134, rankName);
                if (result.sim > 0.8f) {

                   /* if (!getName().equals("巡逻舰队")) {
                        if(onceJudge("rankmb")){
                            mFairy.onTap(591, 81, 601, 93, "【rankmb,切换目标】", 1500);
                            return;
                        }
                    }
*/
                    result = mFairy.findPic("rank9.png");
                    if (result.sim > 0.9f) {
                        mFairy.onTap(0.9f, result, "有人申请队伍", 1500);

                        result = mFairy.findPic("rank28.png");
                        if (result.sim > 0.8f) {
                            for (int i = 0; i < 5; i++) {
                                result = mFairy.findPic("rank10.png");
                                mFairy.onTap(0.8f, result, "同意", 2000);
                            }
                            mFairy.onTap(1115,61,1129,70,"",1000);
                        }
                    }

                    result = mFairy.findPic("rank11.png");
                    mFairy.onTap(0.8f, result, "自动匹配", 1000);

                    result = mFairy.findPic("rank12.png");
                    mFairy.onTap(0.8f, result, "喊话", 1000);
                    mFairy.onTap(0.8f, result, 617, 460, 652, 478, "发送", 1000);

                    if (rankNum >= teamNum) {//未满但达到组队要求，查看是否有玩家申请队伍 并喊话一次
                        if (frequencyMap("ren", 1)) {
                            TaskMain.rankBool = false;
                            setTaskName(2);
                            mFairy.onTap(1223, 15, 1256, 37, "", 500);
                            return;
                        }
                    } else {
                        frequencyInit("ren");
                    }
                } else {
                    mFairy.onTap(591, 81, 601, 93, "【目标不正确,切换目标】", 1500);
                }
            }

            result = mFairy.findPic("package.png");
            if (result.sim > 0.8f) {

                result = mFairy.findPic(257,12,903,103,"rank4.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("【无队状态】"));
                    mFairy.onTap(0.7f, result, "", 1000);
                    return;
                }

                result = mFairy.findPic(403, 177, 1039, 331, "rank2.png");
                if (result.sim > 0.75f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("【队员状态】"));

                    result = mFairy.findPic(428, 120, 1039, 244, "rank3.png");
                    if (result.sim > 0.75f) {
                        mFairy.onTap(0.75f, result, "退出队伍", 1000);
                        mFairy.onTap(733, 453, 763, 466, "", 1000);
                    }
                    return;
                }

                result = mFairy.findPic(433, 198, 1054, 358, "rank6.png");
                if (result.sim > 0.75f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("【队长状态】"));
                    mFairy.onTap(0.75f, result, "", 1000);
                    return;
                }

                result = mFairy.findPic(443, 13, 971, 124, "rank1.png");
                mFairy.onTap(0.7f, result, "队伍操作", 1000);
            }


            timeCount(10, 0);
        }

        void inOperation() throws Exception {


            FindResult c = gamePublicFuntion.cancel();
            if (c.sim > 0.8f) {

                result = mFairy.findPic("tuohang1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(739, 456, 755, 468, "紧急拖航", 1000);
                    return;
                }
                result = mFairy.findPic(168, 443, 383, 550, "caozuo.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(866, 591, 891, 604, "", 5000);
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

            if (gamePublicFuntion.shibai()) {
                teamNum++;
                if(getName().equals("霸主挑战")){
                    if(frequencyMap("bazhu",5)) {
                        mFairy.onTap(947, 24, 971, 37, "离开", 1000);
                        mFairy.onTap(727, 454, 756, 462, "确定离开", 1000);
                        TaskMain.rankBool = true;
                        setTaskName(0);
                    }
                }else {
                    TaskMain.rankBool = true;
                    setTaskName(0);
                }
                return;
            }

            gamePublicFuntion.guotu();

            gamePublicFuntion.yanzheng();

            closeUseOrUser();

            result = mFairy.findPic("kanjia.png");
            mFairy.onTap(0.8f, result, 623, 469, 653, 486, "砍价", 1000);

            result = mFairy.findPic(974, 223, 1222, 706, "bztz8.png");
            if (result.sim > 0.8f) {

                result = mFairy.findPic(1008, 504, 1188, 694, "rank17.png");
                if (result.sim > 0.8f) {
                    TaskMain.rankBool = true;
                    setTaskName(0);
                    return;
                }

                if (shi()) {
                    return;
                }

                if (frequencyMap("shi", 1)) {
                    mFairy.onTap(886, 669, 889, 679, "", 500);
                }

            } else {
                frequencyInit("shi");
                gamePublicFuntion.chat();
            }

            result = mFairy.findPic(529, 109, 748, 201, new String[]{"yuji.png", "yuji1.png"});
            if (result.sim > 0.8f) {

                mFairy.initMatTime();
                err = 0;
            }

            result = mFairy.findPic("tuohang.png");
            mFairy.onTap(0.8f, result, 1226, 671, 1244, 686, "", 1000);

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
                mFairy.onTap(1175, 589, 1193, 608, "出航", 5000);
            }

            result = mFairy.findPic(new String[]{"battle.png", "battle1.png"});
            if (result.sim > 0.7f) {
                LtLog.e(mFairy.getLineInfo("【战斗中】" + result.sim));
                err = 0;

                result = mFairy.findPic("auto1.png");
                mFairy.onTap(0.8f,result,result.x,result.y+15,result.x+1,result.y+20,"副官技能",1000);

                mFairy.initMatTime();
                gamePublicFuntion.auto();
                Thread.sleep(2000);
            }

            task();

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

        void content_01(String rankName,String rankName1) throws Exception {
            if (TaskMain.rankBool) {
                rank(rankName,rankName1);
            } else {
                result = mFairy.findPic("rank28.png");
                if (result.sim > 0.8f) {
                    err=0;
                    for (int i = 0; i < 5; i++) {
                        result = mFairy.findPic("rank10.png");
                        mFairy.onTap(0.8f, result, "同意", 2000);
                    }
                    mFairy.onTap(1115,61,1129,70,"",2000);
                }

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(257,12,903,103,"rank4.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("【无队状态】"));
                        TaskMain.rankBool=true;
                        return;
                    }

                    result = mFairy.findPic(403, 177, 1039, 331, "rank2.png");
                    if (result.sim > 0.75f) {
                        LtLog.e(mFairy.getLineInfo("【队员状态】"));
                        TaskMain.rankBool=true;
                        return;
                    }

                    result = mFairy.findPic(433, 198, 1054, 358, "rank6.png");
                    if (result.sim > 0.75f) {
                        err = 0;

                        FindResult sqsq = mFairy.findPic(430,72,1242,190,"rank25.png");
                        if(sqsq.sim>0.8f){
                            //660,123
                            //739,100,828,134
                            result = mFairy.findPic(sqsq.x+79,100,sqsq.x+168,135,"rank26.png");
                            if(result.sim>0.75f){
                                mFairy.onTap(0.75f,sqsq,"有人申请队伍",2000);
                                return;
                            }
                        }

                        result = mFairy.findPic(430,72,1242,190,"rank27.png");
                        mFairy.onTap(0.8f,result,"自动匹配",1000);
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic(443, 13, 971, 124, "rank1.png");
                    mFairy.onTap(0.7f, result, "队伍操作", 1000);
                }
            }

            timeCount(10,0);
        }

        boolean shi() throws Exception {


            return false;

        }//事

        public void task() throws Exception {
            result = mFairy.findPic("zoom2.png");
            mFairy.onTap(0.8f, result, "打开任务栏", 500);
        }

        boolean cancel() throws Exception {

            return false;
        }

        boolean activityEnd() throws Exception {
            return false;
        }

        boolean activityJuage(FindResult result) throws Exception {

            return false;
        }

        public void closeUseOrUser() throws Exception {
            result = mFairy.findPic("closeUse.png");
            mFairy.onTap(0.8f, result, "closeUse", 1000);

            /*
                result = mFairy.findPic(892,481,1051,602,"use.png");
            mFairy.onTap(0.8f,result,"use",1000);
             */
        }

        void content_03() throws Exception {
            result = mFairy.findPic("fb.png");
            if (result.sim > 0.8f) {
                err = 0;
                result = mFairy.findPic(1013, 575, 1255, 697, "hjsl3.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "组队挑战", 500);
                    if (mapCount(0.8f, 616,247,782,546, "renshu1.png","fb2.png")) {
                        TaskMain.rankBool = true;
                        setTaskName(0);
                        return;
                    } else {
                        Thread.sleep(3000);
                    }

                    result = mFairy.findPic(1013, 575, 1255, 697, "hjsl3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "组队挑战", 500);
                        if (mapCount(0.8f, 430,247,700,546, "dengji.png")) {
                            singleTask.tuidui();
                            TaskMain.rankBool = true;
                            setTaskName(0);
                            return;
                        }
                    }
                }
            }

            result = mFairy.findPic("fb1.png");
            if (result.sim > 0.8f) {
                LtLog.e(mFairy.getLineInfo("数据"));
                setTaskEnd();
                return;
            }
            timeCount(15, 0);

        }
    }//组队抽象类 整理所有单人任务需要的点击

    public void xljd() throws Exception {
        new team(mFairy, "巡逻舰队") {

            void init() throws Exception {
                super.init();
                initOnceJudge("lgs");
            }

            void content_01() throws Exception {
                content_01("xljd1.png","xljd2.png");
            }

            void content_02() throws Exception {
                if (onceJudge("lgs")) {
                    gamePublicFuntion.lgs();
                }
                activity(2, "xljd.png");
            }

            void content_03() throws Exception {

                if (timeMap("lgs_time", 120000, true)) {
                    gamePublicFuntion.lgs();
                }

                //gamePublicFuntion.chat();

                result = mFairy.findPic(973, 307, 1230, 712, "xljd3.png");
                mFairy.onTap(0.8f, result, "巡逻舰队", 1000);


                result = mFairy.findPic(973, 307, 1230, 712, "xljd4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "接受任务", 500);

                    if (mapCount(0.8f, 484, 263, 649, 540, "renshu.png")) {
                        TaskMain.rankBool = true;
                        setTaskName(0);
                        return;
                    }
                }

                gamePublicFuntion.taskClick("xljd5.png");

                timeCount(10, 0);
            }
        };
    }//巡逻舰队

    public void hjsl() throws Exception {
        new team(mFairy, "海军试炼") {

            //rank("hjsl1.png", "hjsl2.png");

            void content_01() throws Exception {
                content_01("hjsl1.png","hjsl2.png");
            }

            void content_02() throws Exception {
                activity(2, "hjsl.png");
            }
        };
    }//海军试炼

    public void txbfj() throws Exception {
        new team(mFairy, "突袭暴风角") {
            //rank("txbfj1.png", "txbfj2.png");

            void content_01() throws Exception {
                content_01("txbfj1.png","txbfj2.png");
            }

            void content_02() throws Exception {
                activity(2, "txbfj.png");
            }
        };
    }//突袭暴风角

    public void jjnlh() throws Exception {
        new team(mFairy, "进军尼罗河") {

            //rank("jjnlh1.png", "jjnlh2.png");

            void content_01() throws Exception {
                content_01("jjnlh1.png","jjnlh2.png");
            }

            void content_02() throws Exception {
                activity(2, "jjnlh.png");
            }

        };
    }//进军尼罗河

    public void bztz() throws Exception {
        new team(mFairy, "霸主挑战") {

            //rank("bztz1.png", "bztz2.png");

            void content_01() throws Exception {
                content_01("bztz1.png","bztz2.png");
            }

            void content_02() throws Exception {
                activity(2, "bztz.png");
            }

            void content_03() throws Exception {

                result = mFairy.findPic(974, 223, 1222, 706, "bztz8.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    switch (AtFairyConfig.getOption("bztz")) {
                        case "2":
                            result = mFairy.findPic(1090, 332, 1238, 710, "bztz10.png");
                            break;
                        case "3":
                            result = mFairy.findPic(1090, 332, 1238, 710, "bztz11.png");
                            break;
                        default:
                            result = mFairy.findPic(1090, 332, 1238, 710, "bztz9.png");
                            break;
                       /* case "4":
                            result = mFairy.findPic(1090, 332, 1238, 710, "bztz12.png");
                            break;*/
                    }

                    mFairy.onTap(0.8f, result, "选难度", 5000);
                }

                result = mFairy.findPic("bztz6.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    FindResult bz = mFairy.findPic(1062, 231, 1252, 517, "bztz7.png");
                    if (bz.sim > 0.8f) {

                        result = mFairy.findPic(1055,285,1279,514, new String[]{"bztz13.png", "bztz14.png"});
                        if (result.sim > 0.72f) {
                            mFairy.onTap(946, 25, 977, 38, "离开", 1000);
                            setTaskEnd();
                            return;
                        }

                        mFairy.onTap(0.8f, bz, "每日霸主挑战", 3000);
                    } else {
                        mFairy.ranSwipe(1149, 330, 1149, 487, 500, 1000);
                    }
                }


                result = mFairy.findPic("fb.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    frequencyInit("bazhu");

                    result = mFairy.findPic(1013, 575, 1255, 697, "hjsl3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "组队挑战", 500);
                        if (mapCount(0.8f, 616,247,782,546, "renshu1.png","fb2.png")) {
                            TaskMain.rankBool = true;
                            setTaskName(0);
                            return;
                        } else {
                            Thread.sleep(3000);
                        }

                        result = mFairy.findPic(1013, 575, 1255, 697, "hjsl3.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "组队挑战", 500);
                            if (mapCount(0.8f, 430,247,700,546, "dengji.png")) {
                                singleTask.tuidui();
                                TaskMain.rankBool = true;
                                setTaskName(0);
                                return;
                            }
                        }
                    }
                }
                timeCount(15,0);
            }
        };
    }//霸主挑战

    public void bfjd() throws Exception {
        new team(mFairy, "北非舰队") {
            int zb;
            int end = 0;

            void create() throws Exception {
                super.create();
                zb = 1;
                end = 0;
            }

            //rank("bf1.png", "bf2.png");

            void content_01() throws Exception {
                content_01("bf1.png","bf2.png");
            }

            boolean activityJuage(FindResult act) throws Exception {

                result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "wancheng.png");
                if (result.sim > 0.88f) {
                    LtLog.e(mFairy.getLineInfo("活动已完成"));
                    setTaskEnd();
                    return true;
                }
                setTaskName(3);
                return true;
            }

            public void task() throws Exception {
                result = mFairy.findPic(986,254,1081,389,"zoom.png");
                mFairy.onTap(0.72f, result, "右 关掉箭头", 1000);

                result = mFairy.findPic("zoom1.png");
                mFairy.onTap(0.72f, result, "左 关掉箭头", 1000);
            }

            boolean shi() throws Exception {
                result = mFairy.findPic(990, 473, 1206, 700, "bf4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "剿灭", 500);
                    if (mapCount(0.8f, 430, 216, 578, 481, "bf5.png")) {
                        setTaskEnd();
                        return true;
                    } else {
                        Thread.sleep(2500);
                    }
                }
                return false;
            }

            void content_02() throws Exception {
                activity(2, "bf.png");
            }

            void content_03() throws Exception {

                LtLog.e(mFairy.getLineInfo("【前往北非近卫 坐标 " + zb + "】"));

                switch (zb) {
                    case 1:
                        singleTask.map(false, "rny4.png", 472, 667);
                        zb = 2;
                        break;
                    case 2:
                        singleTask.map( false, "rny4.png", 630, 375);
                        zb = 1;
                        break;
                }

                gamePublicFuntion.init(false);
                gamePublicFuntion.lgs();
                setTaskName(4);
            }

            void content_04() throws Exception {

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("chuan1.png");
                    if (result.sim < 0.7f) {
                        mFairy.onTap(29, 486, 47, 508, "点击停船", 1000);
                    }

                    if (end >= 3) {
                        end--;
                        gamePublicFuntion.init(false);
                        content_02();
                        setTaskName(4);
                        return;
                    }

                    result = mFairy.findPic(86, 181, 1236, 541, "bf3.png");
                    if (result.sim > 0.72f) {
                        initOnceJudge("ba");
                        frequencyInit("bfSildeErr");
                        mFairy.onTap(result.x + 12, result.y + 30, result.x + 25, result.y + 50, "发现北非舰队", 2000);
                    } else {
                        if (frequencyMap("bfSilde", 1)) {
                            mFairy.ranSwipe(700, 353, 550, 328, 300, 200);
                        }

                        if (frequencyMap("bfSildeErr", 20)) {
                            TaskMain.rankBool =true;
                            setTaskName(0);
                            return;
                        }
                    }
                }

                result = mFairy.findPic("battle.png");
                if (result.sim > 0.8f) {
                    if (onceJudge("ba")) {
                        end++;
                    }
                }

                timeCount(10, 0);
            }
        };
    }//北非舰队

    public void jwjd() throws Exception {
        new team(mFairy, "近卫舰队") {
            //rank("jw1.png", "jw2.png");
            int end;

            void create() throws Exception {
                super.create();
                end = 0;
            }

            boolean activityJuage(FindResult act) throws Exception {

                result = mFairy.findPic(act.x + 222, act.y - 27, act.x + 353, act.y + 87, "wancheng.png");
                if (result.sim > 0.88f) {
                    LtLog.e(mFairy.getLineInfo("活动已完成"));
                    setTaskEnd();
                    return true;
                }
                setTaskName(3);
                return true;
            }

            void content_01() throws Exception {
                content_01("jw1.png","jw2.png");
            }

            void content_02() throws Exception {
                activity(2, "jw.png");
            }

            public void task() throws Exception {

                result = mFairy.findPic(986,254,1081,389,"zoom.png");
                mFairy.onTap(0.72f, result, "右 关掉箭头", 1000);

                result = mFairy.findPic("zoom1.png");
                mFairy.onTap(0.72f, result, "左 关掉箭头", 1000);
            }

            boolean shi() throws Exception {
                result = mFairy.findPic(990, 473, 1206, 700, "jw6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "留下财宝", 3000);
                }
                return false;
            }

            void content_03() throws Exception {

                String str = AtFairyConfig.getOption("jw");

                LtLog.e(mFairy.getLineInfo("【前往近卫舰队 难度 " + str + "】"));

                switch (str) {
                    case "1":
                        singleTask.map( false, "rny4.png", 338, 599);
                        break;
                    case "2":
                        singleTask.map( false, "rny4.png", 481, 672);
                        break;
                    case "3":
                        singleTask.map( false, "blm.png", 458,290);
                        break;
                    case "4":
                        singleTask.map( false, "blm.png", 779,312);
                        break;
                    case "5":
                        singleTask.map( false, "bjx.png", 437, 272);
                        break;
                    case "6":
                        singleTask.map( false, "bjx.png", 450, 4);
                        break;
                    case "7":
                        singleTask.map( false, "blt.png", 235,436);
                        break;
                    case "8":
                        singleTask.map( false, "ystbe.png", 748, 316);
                        break;
                    case "9":
                        singleTask.map( false, "napd.png", 509, 493);
                        break;
                    case "10":
                        singleTask.map( false, "swly.png", 541, 457);
                        break;
                    case "11":
                        singleTask.map( false, "adb.png", 763, 199);
                        break;
                    case "12":
                        singleTask.map( false, "nbls.png", 895, 191);
                        break;
                    case "13":
                        singleTask.map( false, "adb.png", 304, 355);
                        break;
                    case "14":
                        singleTask.map( false, "aeje.png", 603, 240);
                        break;
                    case "15":
                        singleTask.map( false, "xd.png", 727, 324);
                        break;
                }
                gamePublicFuntion.init(false);
                gamePublicFuntion.lgs();
                onceJudge("ba");
                setTaskName(4);
            }

            void content_04() throws Exception {

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("chuan1.png");
                    if (result.sim < 0.7f) {
                        mFairy.onTap(29, 486, 47, 508, "点击停船", 1000);
                    }

                    if (end >= 3) {
                        end--;
                        gamePublicFuntion.init(false);
                        content_02();
                        setTaskName(4);
                        return;
                    }

                    result = mFairy.findPic(86, 181, 1236, 541, new String[]{"jw7.png", "jw8.png"});
                    if (result.sim > 0.72f) {
                        initOnceJudge("ba");
                        frequencyInit("bfSildeErr");
                        mFairy.onTap(result.x + 40, result.y + 30, result.x + 70, result.y + 50, "发现近卫舰队", 2000);
                    } else {
                        if (frequencyMap("bfSilde", 1)) {
                            mFairy.ranSwipe(700, 353, 550, 328, 300, 200);
                        }

                        if (frequencyMap("bfSildeErr", 20)) {
                            TaskMain.rankBool =true;
                            setTaskName(0);
                            return;
                        }
                    }
                }

                result = mFairy.findPic("battle.png");
                if (result.sim > 0.8f) {
                    if (onceJudge("ba")) {
                        end++;
                    }
                }




                timeCount(10, 0);
            }
        };
    }//近卫舰队

    public void shzg() throws Exception {
        new team(mFairy, "深海战歌") {

            void create() throws Exception {
                super.create();
                singleTask.rny("rny1.png", "rny3.png");//先回热亚
            }

            void init() throws Exception {
                super.init();
                TaskMain.rankBool=true;
            }

            //rank("shzg2.png", "shzg1.png");

            void content_01() throws Exception {
                content_01("shzg2.png","shzg1.png");
            }

            boolean cancel() throws Exception {

                result =  rangeFindPic(50,"shzg5.png");
                if(result.sim>0.7f){
                    mFairy.onTap(732,452,759,467,"队伍小于3人",3000);
                    TaskMain.rankBool=true;
                    setTaskName(1);
                    return true;
                }

                result =  rangeFindPic(50,"shzg6.png");
                if(result.sim>0.7f){
                    mFairy.onTap(740,459,764,470,"不足5人 确定匹配",3000);
                    return true;
                }



                return false;
            }

            void content_02() throws Exception {
                activity(4, "shzg.png");
            }

            void content_03() throws Exception {

                result =  rangeFindPic(50,"shzg3.png");
                if (result.sim > 0.7f) {
                    err = 0;
                   /* result = mFairy.findPic("shzg11.png");
                    if (result.sim > 0.92f) {
                        mFairy.onTap(1240,12,1257,33,"",1000);
                        setTaskEnd();
                        return;
                    }*/

                    result =  rangeFindPic(50,"shzg4.png");
                    mFairy.onTap(0.7f,result,"开始匹配",3000);
                }


                result =  rangeFindPic(50,"shzg9.png");
                mFairy.onTap(0.7f,result,"继续排队",1000);

                result = rangeFindPic(50,"shzg8.png");
                if (result.sim > 0.7f) {
                    err=0;
                    mFairy.onTap(0.7f,result,"继续游戏",3000);
                    return;
                }

                result =  rangeFindPic(50,"shzg7.png","shzg10.png");
                if (result.sim > 0.7f) {
                    err=0;
                    Thread.sleep(3000);
                }


                timeCount(15, 0);
            }
        };
    }//深海战歌****

    public void klsg() throws Exception {
        new team(mFairy, "科林斯桂冠竞技") {

            void create() throws Exception {
                super.create();
                singleTask.rny("rny1.png", "rny3.png");//先回热亚
            }

            void init() throws Exception {
                super.init();
                TaskMain.rankBool=true;
            }

            //rank("klsg2.png", "klsg1.png");

            void content_01() throws Exception {
                content_01("klsg2.png","klsg1.png");
            }

            boolean cancel() throws Exception {

                result =  rangeFindPic(50,"shzg5.png");
                if(result.sim>0.7f){
                    mFairy.onTap(732,452,759,467,"队伍小于3人",3000);
                    TaskMain.rankBool=true;
                    setTaskName(1);
                    return true;
                }

                result =  rangeFindPic(50,"shzg6.png");
                if(result.sim>0.7f){
                    mFairy.onTap(740,459,764,470,"不足5人 确定匹配",3000);
                    return true;
                }

                return false;
            }

            void inOperation() throws Exception {
                super.inOperation();

                if(mFairy.dateHour()==22){
                    setTaskEnd();
                    return;
                }
            }

            void content_02() throws Exception {
                activity(4, "klsg.png");
            }

            void content_03() throws Exception {

                result = rangeFindPic(50,"klsg3.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    result = rangeFindPic(50,"klsg4.png");
                    mFairy.onTap(0.7f,result,"开始匹配",3000);
                }

                result = rangeFindPic(50,"shzg9.png");
                mFairy.onTap(0.7f,result,"继续排队",1000);

                result = rangeFindPic(50,"shzg7.png","shzg10.png");
                if (result.sim > 0.7f) {
                    err=0;
                    Thread.sleep(3000);
                }

                timeCount(15, 0);
            }
        };
    }//科林斯桂冠竞技****

}

