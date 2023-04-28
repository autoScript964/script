package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

public class SingleTask {
    public GamePublicFuntion gamePublicFuntion;
    public FindResult result;
    public AtFairyImpl mFairy;

    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }

    public void ThreadTask() throws Exception {
        gamePublicFuntion.init();

        while (mFairy.condit()) {
            for (int i = 0; i < 1; i++) {

                result = mFairy.findPic(2, 5, 1268, 719, "novice22.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(result.x, result.y - 40, result.x + 1, result.y - 39, "清除弹框!", 3000);
                    break;
                }
                result = mFairy.findPic(2, 5, 1268, 719, "novice23.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(result.x + 80, result.y, result.x + 81, result.y + 1, "清除弹框!", 3000);
                    break;
                }
                result = mFairy.findPic(2, 5, 1268, 719, "novice24.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(result.x - 40, result.y, result.x - 39, result.y + 1, "清除弹框!", 3000);
                    break;
                }
                result = mFairy.findPic(2, 5, 1268, 719, "jt.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(result.x, result.y + 90, result.x + 1, result.y + 91, "清除弹框!", 3000);
                    break;
                }
            }

            gamePublicFuntion.close();
            gamePublicFuntion.skip();
            gamePublicFuntion.chat();
            gamePublicFuntion.task();

            result = mFairy.findPic("zx4.png");
            if (result.sim > 0.85f) {
                gamePublicFuntion.battle(1);
                Thread.sleep(10000);
            }

            result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
            if (result.sim > 0.8f) {
                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.85f, result, 70, 176, 71, 177, "任务", 5000);
            }

            FindResult result1 = mFairy.findPic(32, 128, 126, 334, "thread.png");
            LtLog.e(mFairy.getLineInfo("主线："+result1.sim));
            if (result1.sim > 0.75f) {
                result = mFairy.findPic(result1.x, result1.y - 30, result1.x + 200, result1.y + 50,
                        new String[]{"zxEnd.png", "zxEnd1.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("主线任务结束,End!");
                    return;
                }
                mFairy.onTap(0.75f, result1, result1.x + 10, result1.y + 5, result1.x + 20, result1.y + 10, "主线", 300);

                if (gamePublicFuntion.unableFindWay()) {
                    gamePublicFuntion.err();
                } else {
                    Thread.sleep(6000);
                }
            }

            result = mFairy.findPic(new String[]{
                    "zx1.png", "zx2.png", "zx3.png", "determine.png", "cy3.png", "fdz2.png", "yj.png"});
            mFairy.onTap(0.85f, result, "按钮", 6000);
        }

    }//主线任务

    public void fanWai() throws Exception {
        gamePublicFuntion.init();
        int count = 0;
        while (mFairy.condit()) {

            for (int i = 0; i < 1; i++) {
                result = mFairy.findPic(2, 5, 1268, 719, "novice22.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(result.x, result.y - 40, result.x + 1, result.y - 39, "清除弹框!", 3000);
                    break;
                }

                result = mFairy.findPic(2, 5, 1268, 719, "novice23.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(result.x + 80, result.y, result.x + 81, result.y + 1, "清除弹框!", 3000);
                    break;
                }
                result = mFairy.findPic(2, 5, 1268, 719, "novice24.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(result.x - 40, result.y, result.x - 39, result.y + 1, "清除弹框!", 3000);
                    break;
                }
                result = mFairy.findPic(2, 5, 1268, 719, "jt.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(result.x, result.y + 90, result.x + 1, result.y + 91, "清除弹框!", 3000);
                    break;
                }
            }

            gamePublicFuntion.close();
            gamePublicFuntion.skip();
            gamePublicFuntion.chat();
            gamePublicFuntion.task();

            result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
            if (result.sim > 0.8f) {
                count = 0;
                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.85f, result, 70, 176, 71, 177, "任务", 5000);
            }


            FindResult result1 = mFairy.findPic(30, 126, 142, 435, "fanwai.png");
            if (result1.sim > 0.85f) {
                count = 0;
                mFairy.onTap(0.85f, result1, result1.x + 10, result1.y + 5, result1.x + 20, result1.y + 10, "番外", 300);

                if (gamePublicFuntion.unableFindWay()) {
                    gamePublicFuntion.err();
                } else {
                    Thread.sleep(6000);
                }
            }

            result = mFairy.findPic(new String[]{
                    "zx1.png", "zx2.png", "zx3.png", "determine.png", "cy3.png", "fdz2.png", "yj.png"});
            mFairy.onTap(0.85f, result, "按钮", 6000);

            result = mFairy.findPic(818, 608, 951, 717, "main.png");
            if (result.sim > 0.85f) {
                count++;
                if (count == 3) {
                    for (int i = 0; i < 3; i++) {
                        mFairy.ranSwipe(132, 275, 167, 394, 0, 1000);
                    }
                    Thread.sleep(1000);
                } else if (count == 6 || count == 9) {
                    mFairy.ranSwipe(132, 275, 167, 394, 2, 2000);
                } else if (count > 10) {
                    LtLog.e(mFairy.getLineInfo("番外任务完成,End!"));
                    return;
                }

            }
        }

    }//番外任务

    public void novice() throws Exception {
        int bj = 0;
        int count1 = 0, count2 = 0;
        while (true) {
            result = mFairy.findPic(26, 461, 176, 561, "novice24.png");
            if (result.sim > 0.85f) {
                mFairy.onTap(result.x - 40, result.y, result.x - 39, result.y + 1, "左侧弹框!", 3000);
            }
            gamePublicFuntion.close();

            result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
            if (result.sim > 0.8f) {
                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.85f, result, 70, 176, 71, 177, "任务", 5000);
            }

            result = mFairy.findPic("resurrection.png");
            mFairy.onTap(0.9f, result, "安全复活", 1000);

            LtLog.e("新手引导,bj:" + bj);

            if (bj == 0) {
                gamePublicFuntion.skip();
                gamePublicFuntion.battle(0);
                gamePublicFuntion.chat();
                gamePublicFuntion.task();

                mFairy.findPic(0.9f, new String[]{
                        "novice1.png", "novice2.png", "novice3.png", "novice4.png",
                        "novice6.png", "n.png", "determine.png", "novice7.png",
                        "novice9.png", "novice10.png", "novice12.png", "novice13.png",
                        "novice19.png", "novice21.png", "novice25.png", "novice26.png"});

                result = mFairy.findPic(702, 475, 845, 591, "n1.png");
                mFairy.onTap(0.9f, result, "拳头", 6000);

                result = mFairy.findPic(34, 89, 301, 345, "novice20.png");
                mFairy.onTap(0.9f, result, "刺杀柳席", 6000);

                result = mFairy.findPic(34, 89, 301, 345, "jia3.png");
                mFairy.onTap(0.9f, result, "刺杀柳席", 6000);

                result = mFairy.findPic(91, 206, 280, 418, new String[]{
                        "novice8.png", "novice14.png"});
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "引导", 300);

                    if (gamePublicFuntion.unableFindWay()) {
                        gamePublicFuntion.err();
                    } else {
                        Thread.sleep(6000);
                    }
                } else {

                    result = mFairy.findPic(49, 132, 118, 292, "thread.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "主线", 300);
                        if (gamePublicFuntion.unableFindWay()) {
                            gamePublicFuntion.err();
                        } else {
                            Thread.sleep(6000);
                        }
                    }

                }

                result = mFairy.findPic("novice5.png");
                mFairy.onTap(0.95f, result, 1242, 399, 1243, 400, "点击这里", 1000);

                result = mFairy.findPic("novice11.png");
                mFairy.onTap(0.9f, result, "开始炼药", 6000);

                result = mFairy.findPic("practice.png");
                if (result.sim > 0.85f) {
                    LtLog.e("斗气入体");
                    for (int i = 0; i < 30; i++) {
                        mFairy.onTap(885, 538, 886, 540, "点", 100);
                    }
                }

                result = mFairy.findPic("novice15.png");
                if (result.sim > 0.8f) {
                    LtLog.e("斗圣遗迹");
                    bj = 1;
                }

                result = mFairy.findPic("noviceEnd.png");
                if (result.sim > 0.9f) {
                    LtLog.e("开始清除弹框");
                    bj = 2;
                }

            }

            if (bj == 1) {
                gamePublicFuntion.battle(1);

                result = mFairy.findPic(new String[]{
                        "novice16.png"});
                if (result.sim > 0.9f) {
                    mFairy.onTap(0.9f, result, "塔", 3000);
                    gamePublicFuntion.battle(1);
                    Thread.sleep(5000);
                } else {
                    result = mFairy.findPic("novice18.png");
                    mFairy.onTap(0.9f, result, "首领", 6000);
                }


                result = mFairy.findPic(680, 11, 737, 73, "novice15.png");
                if (result.sim > 0.8f) {
                    count1 = 0;
                }
                count1++;
                if (count1 > 8) {
                    bj = 0;
                    count1 = 0;
                }

            }

            if (bj == 2) {
                result = mFairy.findPic("novice27.png");
                if (result.sim < 0.8f) {
                    result = mFairy.findPic("rightZoom.png");
                    mFairy.onTap(0.9f, result, "右侧缩放栏", 1000);
                }

                for (int i = 0; i < 1; i++) {
                    result = mFairy.findPic(2, 5, 1268, 719, "novice22.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(result.x, result.y - 40, result.x + 1, result.y - 39, "清除弹框!", 3000);
                        break;
                    }

                    result = mFairy.findPic(2, 5, 1268, 719, "novice23.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(result.x + 80, result.y, result.x + 81, result.y + 1, "清除弹框!", 3000);
                        break;
                    }
                    result = mFairy.findPic(2, 5, 1268, 719, "novice24.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(result.x - 40, result.y, result.x - 39, result.y + 1, "清除弹框!", 3000);
                        break;
                    }
                    result = mFairy.findPic(2, 5, 1268, 719, "jt.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(result.x - 40, result.y + 90, result.x - 39, result.y + 91, "清除弹框!", 3000);
                        break;
                    }
                }
                count2++;
                if (count2 > 3) {
                    LtLog.e("新手引导任务完成,End!");
                    break;
                }
            }
        }
    }//一键24级

    private int taskType = 1;
    private String taskName = "";

    TaskContent.Slide slideActivity;
    TaskContent.Slide slideTask;

    private int taskNum = 0;

    abstract class singleTask extends TaskContent {

        public singleTask(AtFairyImpl mFairy) throws Exception {
            super(mFairy);
        }

        @Override
        void create() throws Exception {
            slideActivity = new Slide(499, 235, 543, 430);
            slideTask = new Slide(132, 275, 167, 394);
        }

        @Override
        void init() throws Exception {
            gamePublicFuntion.init();
            gamePublicFuntion.ranksSetUp(3);
            setTaskName(1);
            taskNum = 0;
        }

        @Override
        void inOperation() throws Exception {
            result = mFairy.findPic("findWay.png");
            if (result.sim > 0.8f) {
                err = 0;
            }

            long l = mFairy.getColorNum(86, 661, 237, 668, "170,187,255", 0.95f);
            if (l > 30) {
                err = 0;
            }

            gamePublicFuntion.task();
            gamePublicFuntion.ranksSetUp(3);

            result = mFairy.findPic(782, 394, 1001, 570, "shiyong.png");
            mFairy.onTap(0.8f, result, "使用", 1000);

            result = mFairy.findPic(563, 602, 680, 693, "dian.png");
            mFairy.onTap(0.8f, result, 954, 585, 972, 599, "点击空白处关闭", 1000);

            result = mFairy.findPic(573, 71, 822, 230, "err4.png");
            if (result.sim > 0.85f) {
                mFairy.onTap(261, 107, 269, 121, "发现err4", 2000);
                mFairy.onTap(261, 107, 269, 121, "发现err4", 1000);
            }
        }

        @Override
        void content_01() throws Exception {
            if (timeCount(13, 0)) {
                if (activityJudgeCount(2)) {
                    setTaskEnd();
                }
                return;
            }

            result = mFairy.findPic("activity.png");
            if (result.sim > 0.8f) {
                oneJudgeCount = 0;
                mFairy.onTap(0.8f, result, "活动", 2000);
            }

            result = mFairy.findPic(new String[]{"UIactivity.png", "UIactivity1.png"});
            if (result.sim > 0.8f) {
                switch (taskType) {
                    case 1:
                        result = mFairy.findPic("activities.png");
                        mFairy.onTap(0.9f, result, "全天活动", 1500);
                        break;
                    case 2:
                        result = mFairy.findPic("timeLimit.png");
                        mFairy.onTap(0.9f, result, "限时活动", 1500);
                        break;
                }

                result = mFairy.findPic(270, 548, 1145, 649, "huoyue.png");
                mFairy.onTap(0.92f, result, result.x + 25, result.y + 25, result.x + 35, result.y + 35, "领取活跃", 1000);

                FindResult result1 = mFairy.findPic(117, 172, 842, 547, taskName);
                if (result1.sim > 0.82f) {
                    result = mFairy.findPic(result1.x + 133, result1.y + 22,
                            result1.x + 259, result1.y + 110, "pp.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "参加", 500);
                        if (gamePublicFuntion.unableFindWay()) {
                            gamePublicFuntion.err();
                        } else {
                            Thread.sleep(1000);
                            setTaskName(2);
                        }
                        activityJudgeCount = 0;
                        return;
                    }

                    result = mFairy.findPic(result1.x + 133, result1.y + 22,
                            result1.x + 259, result1.y + 110, "complete.png");
                    if (result.sim > 0.85f) {
                        LtLog.e(mFairy.getLineInfo("任务已完成"));
                        setTaskEnd();
                    }

                    result = mFairy.findPic(result1.x + 133, result1.y + 22,
                            result1.x + 259, result1.y + 110, "kai.png");
                    if (result.sim > 0.85f) {
                        LtLog.e(mFairy.getLineInfo("任务未开启"));
                        setTaskEnd();
                    }

                }

                slideActivity.slideRange(new int[]{4, 6, 8, 10, 11}, 2);
            } else {
                if (oneJudgeCount(3)) {
                    setTaskName(0);
                    return;
                }
            }
        }
    }

    public void setup() throws Exception {
        new singleTask(mFairy) {
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "设置";
            }

            void content_01() throws Exception {

                result = mFairy.findPic("setup.png");
                if(result.sim>0.8f){

                    result = mFairy.findPic(73,130,342,677,"setup2.png");
                    if(result.sim>0.8f){

                        for (int i = 0; i < 3; i++) {
                            Thread.sleep(1000);

                            result = mFairy.findPic(73,130,342,677,"setup2.png");
                            mFairy.onTap(0.8f,result,result.x+33,result.y+77,result.x+34,result.y+78,"",1000);
                        }



                        mFairy.onTap(1221,76,1232,86,"",1000);
                        setTaskEnd();
                        return;

                    }else{
                        mFairy.touchUp();
                        mFairy.ranSwipe(670,390,670,250,500,1500);
                    }


                }else {

                    result = mFairy.findPic("main.png");
                    mFairy.onTap(0.8f, result, 32, 37, 61, 59, "点击头像", 2000);

                    result = mFairy.findPic(1102,337,1252,663,"setup1.png");
                    mFairy.onTap(0.8f,result,"设置",1000);

                }


            timeCount(12,0);

            }


            void content_02() throws Exception {

            }
        };

    }//设置


    public void hwzj() throws Exception {
        new singleTask(mFairy) {

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    taskNum = 20;
                }
                gamePublicFuntion.qx();
                gamePublicFuntion.chat();
            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("activity.png");
                mFairy.onTap(0.8f, result, "活动", 2000);

                result = mFairy.findPic("hw.png");
                mFairy.onTap(0.8f, result, "化外", 2000);

            }


            @Override
            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                gamePublicFuntion.whileContent();

                result = mFairy.findPic(47, 133, 124, 417, "xyxx1.png");
                if (result.sim > 0.82f) {
                    err = 0;
                }

                result = mFairy.findPic(271, 64, 459, 217, "gj2.png");
                if (result.sim > 0.85f) {
                    taskNum = 0;
                }

                result = mFairy.findPic(new String[]{"zx1.png", "cy3.png", "fdz2.png", "yj.png"});
                if (result.sim > 0.85f) {
                    taskNum = 0;
                }

                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {
                    taskNum++;
                    slideTask.slideRange(new int[]{6, 8}, 2, 0);
                    if (taskNum > 18) {
                        result = mFairy.findPic(47, 133, 124, 417, "xyxx1.png");
                        if (result.sim > 0.82f) {
                            mFairy.onTap(0.82f, result, "左侧学院修行", 1000);
                            err = 0;
                            return;
                        }
                    }
                }
            }
        };

    }//化外之境

    public void xyxx() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "学院修行";
                taskName = "xyxx.png";
                taskType = 1;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    taskNum = 20;
                }
                gamePublicFuntion.qx();
                gamePublicFuntion.chat();
            }

            @Override
            void content_01() throws Exception {
                super.content_01();
                taskNum = 20;
            }

            @Override
            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                gamePublicFuntion.whileContent();

                result = mFairy.findPic(47, 133, 124, 417, "xyxx1.png");
                if (result.sim > 0.82f) {
                    err = 0;
                }

                result = mFairy.findPic(271, 64, 459, 217, "gj2.png");
                if (result.sim > 0.85f) {
                    taskNum = 0;
                }

                result = mFairy.findPic(new String[]{"zx1.png", "cy3.png", "fdz2.png", "yj.png"});
                if (result.sim > 0.85f) {
                    taskNum = 0;
                }

                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {
                    taskNum++;
                    slideTask.slideRange(new int[]{6, 8}, 2, 0);
                    if (taskNum > 18) {
                        result = mFairy.findPic(47, 133, 124, 417, "xyxx1.png");
                        if (result.sim > 0.82f) {
                            mFairy.onTap(0.82f, result, "左侧学院修行", 1000);
                            err = 0;
                            return;
                        }
                    }
                }
            }
        };

    }//学院修行

    public void wzhc() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "万丈红尘";
                taskName = "wzhc.png";
                taskType = 1;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                gamePublicFuntion.qx();
                gamePublicFuntion.chat();
            }

            @Override
            void content_02() throws Exception {
                timeCount(7, 99);
                Thread.sleep(1000);

                gamePublicFuntion.close();

                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {
                    if (err > 2) {
                        result = mFairy.findPic(47, 133, 124, 417, "wzhc1.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "左侧尘缘", 1000);
                            err = 0;
                            return;
                        }
                        slideTask.slideRange(new int[]{3, 5}, 2, 0);
                    }
                }
            }
        };

    }//万丈红尘

    public void zsxl() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "驻守修炼";
                taskName = "zsxl.png";
                taskType = 1;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(327, 236, 946, 444, "zsxl12.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 841, 496, 875, 508, "确定", 1000);
                } else {
                    gamePublicFuntion.qx();
                }
                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);
            }

            @Override
            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("zsxl9.png");
                mFairy.onTap(0.85f, result, "驻守修炼", 1000);


                result = mFairy.findPic("zsxl5.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    gamePublicFuntion.battle(1);
                }

                result = mFairy.findPic("zsxl14.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(676, 505, 680, 510, "确定领取", 1000);
                    mFairy.onTap(1002, 135, 1022, 151, "关闭收益", 1000);
                    judgeOneSecond = 1;
                }

                result = mFairy.findPic("zsxl10.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    if (judgeOneSecond()) {
                        result = mFairy.findPic("zsxl6.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "领取收益", 2000);
                            return;
                        }
                    }

                    result = mFairy.findPic("zsxlEnd1.png");
                    if (result.sim > 0.9f) {
                        LtLog.e(mFairy.getLineInfo("驻守修炼需要通关后再打,End!"));
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(121, 190, 736, 544, "zsxl2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "可抢占", 1500);
                        setTaskName(3);
                        return;
                    } else {

                        if (oneJudgeCount(2)) {

                            result = mFairy.findPic(111, 120, 742, 556, "zsxl13.png");
                            if (result.sim > 0.8f) {
                                setTaskName(3);
                                return;
                            }

                            mFairy.ranSwipe(479, 272, 515, 508, 2, 1000, (long) 2000);
                        }
                    }
                }
            }

            @Override
            void content_03() throws Exception {
                result = mFairy.findPic(758, 257, 1150, 548, "zsxl3.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "虚位以待", 1500);
                    mFairy.onTap(939, 610, 940, 612, "确定驻守", 1500);
                    mFairy.onTap(855, 503, 860, 505, "确定", 1000);
                } else {
                    int num = (int) (Math.random() * 10) + 1;
                    if (num % 2 == 0) {
                        mFairy.onTap(981, 280 + 55 * (num / 2 - 1), 982, 281 + 55 * (num / 2 - 1), "随机选择玩家", 1000);
                    } else {
                        mFairy.onTap(785, 280 + 55 * ((num + 1) / 2 - 1), 790, 281 + 55 * ((num + 1) / 2 - 1), "随机选择玩家", 1000);
                    }

                    FindResult tiaozhan = mFairy.findPic("zsxl4.png");
                    if (tiaozhan.sim > 0.9f) {
                        frequencyInit("zsx_end");

                        result = mFairy.findPic("zsxlEnd.png");
                        if (result.sim > 0.97f) {
                            LtLog.e(mFairy.getLineInfo("驻守修炼没有次数,End!"));
                            setTaskEnd();
                            return;
                        }


                        mFairy.onTap(0.9f, tiaozhan, "开始挑战", 1500);
                        mFairy.onTap(855, 502, 856, 505, "确定", 10000);
                    }
                }

                result = mFairy.findPic(758,500,1157,669, "jia5.png");
                if (result.sim > 0.85f) {
                    if(frequencyMap("zsx_end",10)){
                        setTaskEnd();
                        return;
                    }
                }
                setTaskName(2);
            }
        };

    }//驻守修炼

    public void jzsy() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "家族饲养";
                taskName = "jzsy.png";
                taskType = 1;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("jzsy1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(834, 498, 868, 512, "前往家族庄园", 5000);
                } else {
                    gamePublicFuntion.qx();
                }
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);
            }

            @Override
            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e("玩家没有加入家族,End!");
                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {

                    if (err > 3) {
                        result = mFairy.findPic(91, 120, 215, 444, new String[]{"jzsy3.png", "jzsy4.png"});
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "右侧家族饲养", 2000);
                            err = 0;
                            return;
                        }
                        slideTask.slideRange(new int[]{5, 7}, 2, 0);
                    }
                }

            }
        };

    }//家族饲养

    public void jzxklc() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "虚空雷池试炼";
                taskName = "lei1.png";
                taskType = 1;
            }

            @Override
            void content_01() throws Exception {
                super.content_01();
                taskNum = 20;
                oneSecond = 0;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                gamePublicFuntion.qx();

                result = mFairy.findPic(968, 244, 1236, 595, "lei3.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "虚空雷池试炼", 1000);
                } else {
                    gamePublicFuntion.chat();
                }
                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    taskNum = 20;
                }

            }

            @Override
            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e("玩家没有加入家族,End!");
                    setTaskEnd();
                    return;
                }

                gamePublicFuntion.whileContent();

                result = mFairy.findPic(79, 141, 204, 420, "lei5.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic(271, 64, 459, 217, "gj2.png");
                if (result.sim > 0.85f) {
                    taskNum = 0;
                }

                result = mFairy.findPic(new String[]{"zx1.png", "cy3.png", "fdz2.png", "yj.png"});
                if (result.sim > 0.85f) {
                    taskNum = 0;
                }

                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {
                    taskNum++;
                    slideTask.slideRange(new int[]{6, 8}, 2, 0);

                    if (taskNum > 15) {
                        result = mFairy.findPic(79, 141, 204, 420, "lei5.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "左侧虚空雷池任务", 1000);
                            err = 0;
                            taskNum = 0;
                            return;
                        }
                    }

                    result = mFairy.findPic(712, 471, 829, 592, "lei2.png");
                    if (result.sim > 0.85f) {
                        if (oneSecond()) {
                            mFairy.onTap(0.85f, result, "天火尊者", 1000);
                            taskNum = 20;
                        }
                    }
                }
            }
        };

    }//虚空雷池试炼

    public void xz() throws Exception {
        new singleTask(mFairy) {

            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "赎罪";
            }

            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "安全复活", 1000);
                    taskNum = 20;
                }
                gamePublicFuntion.qx();
                gamePublicFuntion.chat();
            }

            void content_01() throws Exception {
                timeCount(10, 99);
                result = mFairy.findPic("xz2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(755, 479, 968, 620, "xz3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "赎罪", 500);
                        mFairy.onTap(919, 178, 936, 193, "", 500);
                        setTaskName(2);
                        return;
                    } else {
                        if (twoJudgeCount(3)) {
                            mFairy.onTap(919, 178, 936, 193, "", 500);
                            setTaskEnd();
                            return;
                        }
                    }
                } else {
                    result = mFairy.findPic(91, 5, 252, 41, "xz1.png");
                    mFairy.onTap(0.8f, result, "点击三角", 1000);
                }
            }

            void content_02() throws Exception {
                timeCount(6, 0);
                Thread.sleep(1000);

                gamePublicFuntion.whileContent();

                result = mFairy.findPic(271, 64, 459, 217, "gj2.png");
                if (result.sim > 0.85f) {
                    err = 0;
                }

                result = mFairy.findPic(new String[]{"zx1.png", "cy3.png", "fdz2.png", "yj.png"});
                if (result.sim > 0.85f) {
                    err = 0;
                }
            }
        };

    }//赎罪

    private long jzsxTime = System.currentTimeMillis();
    private int jzsxNum = 90;
    private int jzsx = 0;

    public void jzsx() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "家族双修";
                taskName = "jzsx.png";
                taskType = 1;

            }

            @Override
            void init() throws Exception {
                super.init();
                jzsx = 0;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                gamePublicFuntion.qx();
                gamePublicFuntion.chat();

                if (System.currentTimeMillis() - jzsxTime > 200000) {
                    LtLog.e(mFairy.getLineInfo("超过五分钟没有进入传功!"));
                    setTaskEnd();
                    return;
                }
            }

            @Override
            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e("玩家没有加入家族,End!");
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("jz1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic(989, 152, 1062, 617, "jzsxEnd.png");
                    if (result.sim > 0.98f) {
                        LtLog.e(mFairy.getLineInfo("家族双修没有次数,End!"));
                        setTaskEnd();
                        return;
                    }

                    for (int i = 0; i < 4; i++) {
                        result = mFairy.findPic(998, 222 + (i * jzsxNum), 1056, 306 + (i * jzsxNum), "jzsx1.png");
                        if (result.sim > 0.9f) {
                            mFairy.tap(result.x + 5, result.y + 10);
                            Thread.sleep(1000);
                            LtLog.e(mFairy.getLineInfo("传功"));
                            jzsx = 1;
                        }
                    }

                    result = mFairy.findPic(991, 162, 1064, 610, "jzsx1.png");
                    if (result.sim > 0.82f) {
                        mFairy.ranSwipe(909, 266, 971, 531, 2, 1000, (long) 2000);
                        LtLog.e(mFairy.getLineInfo("找传功,滑动<<"));
                    } else {
                        if (jzsx == 0) {
                            LtLog.e(mFairy.getLineInfo("家族没有可以传功的成员,End!"));
                            setTaskEnd();
                            return;
                        } else {
                            for (int i = 0; i < 6; i++) {
                                mFairy.ranSwipe(909, 266, 971, 531, 0, 500, (long) 1000);
                            }
                            Thread.sleep(2000);
                        }
                    }
                }

                result = mFairy.findPic("jzsx2.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("传功 ing..."));
                    Thread.sleep(10000);
                    jzsxTime = System.currentTimeMillis();
                    return;
                }
            }
        };
    }//家族双修

    private TaskContent.Slide jzpsSlide;
    private boolean jzps = true;

    public void jzps() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "家族跑商";
                taskName = "ps.png";
                jzpsSlide = new Slide(659, 314, 703, 584);

                if (AtFairyConfig.getOption("jzps").equals("2")) {
                    jzps = false;
                }
                taskType = 1;
            }

            @Override
            void init() throws Exception {
                super.init();
                gamePublicFuntion.err();
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("jzsy1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(834, 498, 868, 512, "前往家族庄园", 5000);
                } else {
                    gamePublicFuntion.qx();
                }
                gamePublicFuntion.chat();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);
            }

            @Override
            void content_02() throws Exception {
                timeCount(12, 0);
                Thread.sleep(500);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e("玩家没有加入家族,End!");
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("ps1.png");
                mFairy.onTap(0.85f, result, "接取任务", 8000);

                result = mFairy.findPic("ps2.png");
                if (result.sim > 0.85f) {

                    result = mFairy.findPic("ps9.png");
                    if (result.sim > 0.95f) {
                        mFairy.onTap(272, 632, 275, 635, "货物达到10次领取奖励,End!", 3000);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(808, 180, 1176, 665, new String[]{"ps5.png", "ps4.png"});
                    if (result.sim > 0.85f) {
                        err = 0;
                        mFairy.onTap(0.85f, result, "跑商任务", 1000);
                    }

                    result = mFairy.findPic(808, 180, 1176, 665, "ps3.png");
                    if (result.sim > 0.98f) {
                        err = 0;
                        mFairy.onTap(0.98f, result, "提交", 1000);
                    }

                    jzpsSlide.slideRange(new int[]{4, 6, 8}, 2, 0);

                    if (err >= 10) {
                        LtLog.e(mFairy.getLineInfo("没有货物需要操作"));
                        if (jzps) {
                            mFairy.onTap(272, 632, 275, 635, "领取奖励", 2000);
                            mFairy.onTap(854, 505, 855, 507, "确定领取", 1000);
                        }
                        setTaskEnd();
                        return;
                    }
                }
            }
        };

    }//家族跑商

    public void ykzl() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "妖傀招雷";
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("jzsy1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(834, 498, 868, 512, "前往家族庄园", 5000);
                } else {
                    gamePublicFuntion.qx();
                }

                result = mFairy.findPic(129, 74, 201, 395, "ykzl5.png");
                if (result.sim > 0.85f) {
                    result = mFairy.findPic(1012, 190, 1199, 596, "ykzl6.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "招雷", 1000);
                        setTaskEnd();
                        return;
                    } else {
                        mFairy.onTap(1026, 620, 1111, 644, "聊天", 1000);
                    }
                } else {
                    gamePublicFuntion.chat();
                }
                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);
            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 99);
                Thread.sleep(1000);
                result = mFairy.findPic(740, 4, 1027, 75, "jzlj.png");
                mFairy.onTap(0.85f, result, "家族", 1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e("玩家没有加入家族,End!");
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("jz2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(1119, 103, 1220, 599, "ykzl1.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "点击妖傀", 1000);
                    } else {
                        if (oneJudgeCount(3)) {
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic(147, 560, 331, 646, "ykzl2.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic(587, 426, 826, 614, "ykzl3.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "前往傀儡", 2500);
                        setTaskName(2);
                        return;
                    }
                }
            }

            @Override
            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic(664, 433, 885, 642, "ykzl4.png");
                mFairy.onTap(0.85f, result, "对话 >傀儡", 1000);
            }
        };

    }//妖傀招雷


    private int yhm = 1;
    private int yhs = 1;
    private int yhl = 1;
    private int yhErr = 1;
    private int mapErr = 0;

    public void slyh() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "狩猎异火";
                if (!AtFairyConfig.getOption("yhm").equals("")) {
                    yhm = 1;
                }
                if (!AtFairyConfig.getOption("yhs").equals("")) {
                    yhs = 1;
                }
                if (!AtFairyConfig.getOption("yhl").equals("")) {
                    yhl = 1;
                }
                slyhMap(yhm);
            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("yh.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "异火", 1500);
                } else {
                    mFairy.onTap(1240, 401, 1245, 405, "右侧缩放栏", 2000);
                }
                result = mFairy.findPic("UIyh.png");
                if (result.sim > 0.85f) {
                    mapErr = 0;
                    setTaskName(2);
                    return;
                }
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("yh6.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(395, 495, 463, 513, "灵魂值不足,End!", 1000);
                    setTaskEnd();
                    return;
                }
                gamePublicFuntion.qx();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);
            }

            @Override
            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                result = mFairy.findPic(836, 505, 1257, 708, "shoulei.png");
                mFairy.onTap(0.85f, result, "狩猎异火", 1500);

                result = mFairy.findPic(840, 266, 1040, 319, "yh1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(0.85f, result, "普通狩猎", 300);
                    mapErr++;
                    if (mapErr > 6) {
                        slyhMap(yhm);
                        setTaskName(0);
                        return;
                    }

                    switch (yhErr) {
                        case 1:
                            if (mapCount(0.8f, 388, 229, 427, 337, "yh2.png")) {
                                mFairy.onTap(34, 512, 35, 515, "下马", 1000);
                                return;
                            }
                            yhErr = 2;
                            break;
                        case 2:
                            if (mapCount(0.8f, 800, 206, 864, 387, "yhEnd1.png")) {
                                LtLog.e(mFairy.getLineInfo("灵魂值不足,End!"));
                                mFairy.onTap(803, 254, 805, 255, "关闭", 1000);
                                setTaskEnd();
                                return;
                            }
                            yhErr = 1;
                            break;
                    }

                    Thread.sleep(3000);
                }

                result = mFairy.findPic(1180, 221, 1278, 310, "yh10.png");
                mFairy.onTap(0.85f, result, "开启感知", 1000);

                result = mFairy.findPic(1130, 184, 1266, 266, "yh3.png");
                if (result.sim > 0.85f) {
                    err = 0;

                    result = mFairy.findPic(805, 229, 1203, 328, "color" + yhs + ".png");
                    mFairy.onTap(0.8f, result, "异火颜色", 5000);
                }

                result = mFairy.findPic("tackChat.png");
                mFairy.onTap(0.85f, result, "异火气息", 1500);

                result = mFairy.findPic("yh4.png");
                if (result.sim > 0.9f) {
                    err = 0;
                    switch (yhl) {
                        case 1:
                            mFairy.onTap(1113, 304, 1115, 305, "降服火焰", 3000);
                            break;
                        case 2:
                            mFairy.onTap(1113, 391, 1115, 395, "驯化火焰", 1000);
                            break;
                        case 3:
                            mFairy.onTap(1113, 481, 1115, 485, "吸收火焰", 15000);
                            break;
                    }
                } else {
                    gamePublicFuntion.chat();
                }

                result = mFairy.findPic(28, 114, 207, 274, "yh9.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(1149, 599, 1179, 634, "a", 200);
                    gamePublicFuntion.battle(1);
                }
                result = mFairy.findPic(556, 500, 727, 582, "yh8.png");
                mFairy.onTap(0.85f, result, "继续狩猎", 1000);
            }
        };
    }//狩猎异火

    public void slyhMap(int yhm) throws Exception {
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("狩猎异火,Map"));
            result = mFairy.findPic("UImap.png");
            if (result.sim > 0.85f) {
                switch (yhm) {
                    case 1:
                        if (gamePublicFuntion.map(1)) {
                            mFairy.onTap(559, 495, 560, 500, "确定", 10000);
                            return;
                        }
                        break;
                    case 2:
                        if (gamePublicFuntion.map(2)) {
                            mFairy.onTap(672, 376, 675, 380, "确定", 10000);
                            return;
                        }
                        break;
                    case 3:
                        if (gamePublicFuntion.map(3)) {
                            mFairy.onTap(523, 387, 525, 390, "确定", 10000);
                            return;
                        }
                        break;
                    case 4:
                        if (gamePublicFuntion.map(4)) {
                            mFairy.onTap(416, 461, 418, 465, "确定", 10000);
                            return;
                        }
                        break;
                    case 5:
                        if (gamePublicFuntion.map(5)) {
                            mFairy.onTap(452, 251, 455, 255, "确定", 10000);
                            return;
                        }
                        break;
                    case 6:
                        if (gamePublicFuntion.map(6)) {
                            mFairy.onTap(549, 376, 550, 380, "确定", 10000);
                            return;
                        }
                        break;
                }
            } else {
                gamePublicFuntion.init();
                result = mFairy.findPic("package.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(1245, 31, 1257, 45, "点击地图", 3000);
                }
            }
        }
    }//狩猎异火地图选择

    /*** 签到*/
    private int signIn = 0;

    public void signIn() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                TaskMain.TASKNAME = "签到";
            }

            @Override
            void init() throws Exception {
                super.init();
                signIn = 0;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic("zhaohui2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "确定找回", 1000);
                } else {
                    gamePublicFuntion.qx();
                }

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);
            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(593, 8, 1097, 147, "fl.png");
                mFairy.onTap(0.9f, result, "福利", 2000);

                result = mFairy.findPic(34, 46, 699, 135, "fuli.png");
                mFairy.onTap(0.9f, result, "最新活动", 500);

                result = mFairy.findPic("fl1.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    if (signIn == 0) {
                        for (int i = 0; i < 6; i++) {
                            mFairy.onTap(1071, 633, 1126, 647, "在线奖励", 500);
                            if (mapCount(0.83f, 758, 162, 875, 418, "qian2.png")) {
                                break;
                            }
                            result = mFairy.findPic("zai.png");
                            if (result.sim > 0.85f) {
                                break;
                            }
                        }
                        signIn = 1;
                    } else if (signIn == 1) {
                        mFairy.onTap(1010, 187, 1020, 237, "风铃签", 1500);
                    } /*else if (signIn == 2) {
                        result = mFairy.findPic(48, 138, 863, 670, "kuang4.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "狂欢庆典", 1000);
                        } else {
                            mFairy.ranSwipe(565, 328, 592, 554, 2, 1000, (long)2000);
                        }
                    } */ else {
                        for (int i = 0; i < 5; i++) {
                            result = mFairy.findPic(48, 138, 863, 670, new String[]{"qd.png", "zhaohui.png"});
                            if (result.sim > 0.85f) {
                                mFairy.onTap(0.85f, result, "灵丹修炼", 1000);
                                break;
                            } else {
                                if (i == 1) {
                                    mFairy.ranSwipe(565, 328, 592, 554, 0, 500, (long) 1000);
                                }
                                if (i == 3) {
                                    mFairy.ranSwipe(565, 328, 592, 554, 2, 500, (long) 1000);
                                }
                            }
                        }
                    }
                }


               /* result = mFairy.findPic(770, 84, 984, 231, "kuang3.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(0.85f, result, 828, 519, 903, 538, "每日签到", 500);
                    mFairy.onTap(1171, 82, 1190, 102, "关闭", 1000);
                    signIn = 3;
                    return;
                }*/

                result = mFairy.findPic("jia2.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    for (int j = 0; j < 10; j++) {
                        int week = mFairy.week();

                        switch (week) {
                            case 1:
                                mFairy.onTap(460, 437, 465, 440, "星期一签到奖励", 1000);
                                break;
                            case 2:
                                mFairy.onTap(573, 400, 575, 402, "星期二签到奖励", 1000);
                                break;
                            case 3:
                                mFairy.onTap(696, 398, 698, 400, "星期三签到奖励", 1000);
                                break;
                            case 4:
                                mFairy.onTap(824, 405, 825, 407, "星期四签到奖励", 1000);
                                break;
                            case 5:
                                mFairy.onTap(944, 403, 945, 405, "星期五签到奖励", 1000);
                                break;
                            case 6:
                                mFairy.onTap(1071, 426, 1075, 430, "星期六签到奖励", 1000);
                                break;
                            case 7:
                                mFairy.onTap(1197, 460, 1200, 462, "星期日签到奖励", 1000);
                                break;
                        }

                        mFairy.onTap(962, 595, 965, 600, "返回", 2000);
                        mFairy.onTap(1247, 40, 1250, 42, "关闭", 1000);
                        result = mFairy.findPic("fl1.png");
                        if (result.sim > 0.85f) {
                            signIn = 2;
                            break;
                        }
                    }
                }

                result = mFairy.findPic("qian.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    switch (signIn) {
                        case 2:
                            if (oneSecond()) {
                                mFairy.onTap(246, 141, 332, 158, "昨日找回", 500);
                                mFairy.onTap(246, 141, 332, 158, "昨日找回", 1000);
                            }

                            result = mFairy.findPic(782, 394, 1001, 570, "shiyong.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "使用", 1300);
                                oneJudgeCount = 0;
                            }
                            result = mFairy.findPic(563, 602, 680, 693, "dian.png");
                            mFairy.onTap(0.8f, result, 954, 585, 972, 599, "点击空白处关闭", 1000);

                           /* result = mFairy.findPic(772, 300, 944, 626, "qd3.png");
                            if (result.sim > 0.8f) {
                                oneJudgeCount=0;
                                mFairy.onTap(0.8f, result, "银币找回", 1000);
                            } else {
                                if (oneJudgeCount(3)) {
                                    signIn = 4;
                                    oneJudgeCount = 0;
                                    oneSecond = 0;
                                    return;
                                }
                            }*/


                            result = mFairy.findPic("zhaohui1.png");
                            if (result.sim > 0.8f) {
                                oneJudgeCount = 0;
                                mFairy.onTap(0.8f, result, "一键找回", 500);

                                if (mapCount(0.85f, 596, 197, 707, 406, "beibao.png")) {
                                    setTaskEnd();
                                    return;
                                }
                            } else {
                                if (oneJudgeCount(3)) {
                                    signIn = 3;
                                    oneJudgeCount = 0;
                                    oneSecond = 0;
                                    return;
                                }
                            }
                            break;
                        case 3:
                            if (oneSecond()) {
                                mFairy.onTap(480, 140, 559, 158, "灵丹修炼", 500);
                                mFairy.onTap(480, 140, 559, 158, "灵丹修炼", 1000);
                            }
                            mFairy.onTap(297, 581, 334, 596, "领取", 1000);
                            signIn = 4;
                            oneJudgeCount = 0;
                            oneSecond = 0;
                            return;
                        case 4:
                            if (oneSecond()) {
                                mFairy.onTap(710, 140, 787, 157, "上周找回", 500);
                                mFairy.onTap(710, 140, 787, 157, "上周找回", 1000);
                            }

                            result = mFairy.findPic(760, 322, 994, 647, "qd4.png");
                            if (result.sim > 0.8f) {
                                oneJudgeCount = 0;
                                mFairy.onTap(0.8f, result, "领取", 500);

                                if (mapCount(0.85f, 596, 197, 707, 406, "beibao.png")) {
                                    setTaskEnd();
                                    return;
                                }

                            } else {
                                if (oneJudgeCount(3)) {
                                    oneSecond = 0;
                                    oneJudgeCount = 0;
                                    setTaskName(2);
                                    return;
                                }
                            }

                            break;
                    }
                }

                result = mFairy.findPic("fl3.png");
                mFairy.onTap(0.85f, result, 870, 513, 875, 515, "确定", 2000);
            }

            @Override
            void content_02() throws Exception {
                timeCount(10, 0);
                result = mFairy.findPic("activity.png");
                mFairy.onTap(0.8f, result, "活动", 2000);

                result = mFairy.findPic(new String[]{"UIactivity.png", "UIactivity1.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    for (int i = 0; i < 7; i++) {
                        mFairy.onTap(335 + (i * 130), 597, 338 + (i * 130), 600, "点", 800);
                        mFairy.onTap(335 + (i * 130), 597, 338 + (i * 130), 600, "点", 800);
                    }
                    setTaskName(3);
                    return;
                } else {
                    gamePublicFuntion.close();
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 99);
                result = mFairy.findPic(740, 4, 1027, 75, "jzlj.png");
                mFairy.onTap(0.85f, result, "家族", 1000);

                result = mFairy.findPic("jz.png");
                if (result.sim > 0.85f) {
                    LtLog.e("玩家没有加入家族,End!");
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("jz2.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(1119, 103, 1220, 599, "lingdi.png");
                    mFairy.onTap(0.85f, result, "点击领地", 1000);

                    result = mFairy.findPic(942, 314, 1137, 701, "jzlj2.png");
                    mFairy.onTap(0.85f, result, "家族领地", 1000);

                    result = mFairy.findPic(861, 430, 1128, 620, "jzlj3.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "领取家族领地奖励,End!", 1000);
                        setTaskEnd();
                        return;
                    }
                } else {
                    gamePublicFuntion.close();
                }
            }
        };
    }//签到

    int index = 1;

    public void shangjia() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                TaskMain.TASKNAME = "上架";
                index = 1;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);
            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic(450, 5, 1115, 257, "sj1.png");
                mFairy.onTap(0.9f, result, "上架", 500);

                result = mFairy.findPic("sj2.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    switch (index) {
                        case 1:
                            if (AtFairyConfig.getOption("shangchu").equals("1")) {
                                for (int i = 0; i < 3; i++) {

                                    result = mFairy.findPic("sj3.png");
                                    if (result.sim > 0.85f) {
                                        i = 0;
                                        mFairy.onTap(0.85f, result, "出售", 100);
                                    } else {
                                        mFairy.onTap(1156, 224, 1204, 259, "商会", 500);
                                        mFairy.onTap(520, 135, 600, 158, "出售", 1000);
                                    }
                                }
                            }
                            index = 2;
                            break;
                        case 2:
                            if (AtFairyConfig.getOption("jichu").equals("1")) {
                                for (int i = 0; i < 6; i++) {

                                    result = mFairy.findPic("sj6.png");
                                    mFairy.onTap(0.85f, result, "重新上架", 1000);

                                    result = mFairy.findPic("sj7.png");
                                    mFairy.onTap(0.85f, result, "确定", 1000);

                                    result = mFairy.findPic("sj4.png");
                                    if (result.sim > 0.85f) {

                                        result = mFairy.findPic(71, 224, 738, 669, "sj5.png");
                                        if (result.sim > 0.85f) {
                                            i = 0;
                                            mFairy.onTap(0.85f, result, result.x + 150, result.y + 0, result.x + 160, result.y + 1, "过期", 1000);
                                        } else {
                                            if (i == 3) {
                                                mFairy.ranSwipe(512, 307, 566, 594, 2, 500, (long) 1000);
                                            }
                                        }

                                    } else {
                                        mFairy.onTap(1156, 320, 1190, 354, "寄售", 500);
                                        mFairy.onTap(536, 137, 592, 155, "出售", 1000);
                                    }
                                }
                            }
                            setTaskEnd();
                            return;
                    }
                }
            }

            @Override
            void content_02() throws Exception {

            }
        };
    }//上架

    public void email() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                TaskMain.TASKNAME = "邮件领取";
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);
            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(163, 613, 301, 711, "email1.png");
                mFairy.onTap(0.85f, result, "邮件", 1000);

                result = mFairy.findPic("email4.png");
                mFairy.onTap(0.85f, result, 1158, 476, 1196, 509, "邮件", 1000);

                result = mFairy.findPic("email2.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(276, 644, 353, 655, "一键领取", 3000);

                    for (int i = 0; i < 5; i++) {
                        result = mFairy.findPic(732, 387, 1069, 555, new String[]{"email3.png", "shiyong.png"});
                        if (result.sim > 0.85f) {
                            i = 0;
                            mFairy.onTap(0.85f, result, "使用", 1000);
                        }
                    }

                    mFairy.onTap(114, 641, 177, 658, "一键删除", 1000);

                    setTaskEnd();
                    return;
                }
            }

            @Override
            void content_02() throws Exception {

            }
        };
    }//邮件领取

    public void zhuang() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                TaskMain.TASKNAME = "装备分解";
                index = 1;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);
            }

            @Override
            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.85f, result, "背包", 1000);

                result = mFairy.findPic("bei1.png");
                if (result.sim > 0.85f) {
                    switch (index) {
                        case 1:
                            if (AtFairyConfig.getOption("qingfen").equals("1")) {
                                result = mFairy.findPic("bei6.png");
                                mFairy.onTap(0.85f, result, "一键清理", 1500);
                            }
                            index = 2;
                            break;
                        case 2:
                            if (AtFairyConfig.getOption("zhuangfen").equals("1")) {
                                mFairy.onTap(795, 124, 850, 145, "装备", 1000);
                                mFairy.onTap(673, 207, 703, 236, "装备", 1000);
                                if (mapCount(0.85f, 904, 323, 1193, 680, "bei2.png")) {
                                    result = mFairy.findPic(904, 323, 1193, 680, "bei2.png");
                                    mFairy.onTap(0.85f, result, "拆解", 1000);
                                    return;
                                }
                            }
                            setTaskEnd();
                            return;
                    }
                }

                result = mFairy.findPic("bei7.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(615, 614, 661, 635, "确定清理", 2000);
                    return;
                }

                result = mFairy.findPic("bei3.png");
                if (result.sim > 0.85f) {
                    for (int i = 0; i < 3; i++) {

                        switch (i) {
                            case 0:
                                mFairy.onTap(136, 622, 154, 637, "白装", 1000);
                                break;
                            case 1:
                                mFairy.onTap(269, 620, 281, 633, "绿装", 1000);
                                break;
                            case 2:
                                mFairy.onTap(384, 623, 400, 635, "蓝装", 1000);
                                break;
                        }

                        result = mFairy.findPic("bei4.png");
                        mFairy.onTap(0.85f, result, "确定", 500);
                    }

                    result = mFairy.findPic("bei5.png");
                    mFairy.onTap(0.85f, result, "确定拆解", 1000);

                    result = mFairy.findPic("bei4.png");
                    mFairy.onTap(0.85f, result, "确定", 500);


                    result = mFairy.findPic("bei8.png");
                    mFairy.onTap(0.85f, result, 395, 494, 445, 510, "存在高品质装备", 500);

                    gamePublicFuntion.init();
                    setTaskEnd();
                    return;
                }


            }

            @Override
            void content_02() throws Exception {

            }
        };
    }//装备分解

    private int pg = 0;
    private boolean bools = false;

    public boolean pg() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                TaskMain.TASKNAME = "使用精力丹";
                bools = false;
                pg = 0;
            }

            @Override
            void init() throws Exception {
                super.init();
            }

            @Override
            void inOperation() throws Exception {
                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                result = mFairy.findPic("qian1.png");
                mFairy.onTap(0.85f, result, "关闭使用小窗口", 1000);
            }

            @Override
            void content_01() throws Exception {
                if (timeCount(10, 99)) {
                    if (pg == 0) {
                        bools = true;
                    } else {
                        bools = false;
                    }
                }

                result = mFairy.findPic(812, 610, 957, 717, "main.png");
                mFairy.onTap(0.9f, result, "背包", 2000);

                result = mFairy.findPic("pd8.png");
                mFairy.onTap(0.85f, result, "点击关闭", 1000);

                result = mFairy.findPic("pd1.png");
                if (result.sim > 0.85f) {

                    result = mFairy.findPic("pd6.png");
                    mFairy.onTap(0.85f, result, "道具", 1000);

                    result = mFairy.findPic(763, 154, 1051, 677, "pd4.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "使用", 1000);
                        pg++;
                        if (pg >= 3) {
                            setTaskEnd();
                            return;
                        }
                    }

                    result = mFairy.findPic(751, 413, 1009, 603, "pd5.png");
                    mFairy.onTap(0.85f, result, "确定", 1500);

                    result = mFairy.findPic(637, 169, 1115, 632, "jing.png");
                    if (result.sim > 0.85f) {
                        err = 0;
                        mFairy.onTap(0.85f, result, "Fa", 1000);
                        return;
                    }


                    if (err % 3 == 0 && err != 0) {
                        mFairy.ranSwipe(958, 216, 988, 515, 2, 1000, (long) 2000);
                        return;
                    }
                }

            }

            @Override
            void content_02() throws Exception {

            }

            @Override
            void content_03() throws Exception {
            }
        };
        gamePublicFuntion.close();
        return bools;
    }//使用精力丹

    /*** 联盟任务*/
    public void lmyhxs() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "异火线索";
                taskName = "yhxs.png";
                taskType = 2;
            }

            @Override
            void inOperation() throws Exception {
                result = mFairy.findPic("findWay.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (gamePublicFuntion.fad() > 6) {
                        result = mFairy.findPic(86, 137, 229, 436, "yhxs2.png");
                        mFairy.onTap(0.82f, result, "异火线索", 1000);
                    }
                }
                long l = mFairy.getColorNum(86, 661, 237, 668, "170,187,255", 0.98f);
                if (l > 50) {
                    err = 0;
                }
                gamePublicFuntion.task();
                gamePublicFuntion.ranksSetUp(3);

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                result = mFairy.findPic("qian1.png");
                mFairy.onTap(0.85f, result, "关闭使用小窗口", 1000);

                result = mFairy.findPic(573, 71, 822, 230, "err4.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(261, 107, 269, 121, "发现err4", 2000);
                    mFairy.onTap(261, 107, 269, 121, "发现err4", 1000);
                }

                gamePublicFuntion.qx();

                result = mFairy.findPic("ai.png");
                if (result.sim > 0.85f) {
                    setTaskName(3);
                    return;
                } else {
                    result = mFairy.findPic(962, 114, 1251, 575, "yhxs1.png");
                    if (result.sim > 0.85f) {
                        err = 0;
                        mFairy.onTap(0.85f, result, "异火线索", 1000);
                    } else {
                        gamePublicFuntion.chat();
                    }
                }

                result = mFairy.findPic("lm1.png");
                mFairy.onTap(0.85f, result, "关闭联盟分支小窗口", 1000);
            }

            @Override
            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(500);

                result = mFairy.findPic("lmEnd.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("还没有加入联盟,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(512, 85, 660, 178, "nong.png");
                mFairy.onTap(0.85f, result, 1168, 606, 1170, 610, "弄火石不足", 3000);

                result = mFairy.findPic("ylEnd.png");
                if (result.sim > 0.85f) {
                    setTaskName(1);
                    return;
                }

                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {
                    if (err > 3) {
                        result = mFairy.findPic(86, 137, 229, 436, "yhxs2.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.82f, result, "异火线索", 1000);
                            err = 0;
                        }

                        slideTask.slideRange(new int[]{5, 7, 9}, 2, 0);

                        for (int i = 0; i < 4; i++) {
                            result = mFairy.findPic("tackChat.png");
                            if (result.sim > 0.85f) {
                                mFairy.onTap(0.85f, result, "任务聊天", 2000);
                            } else {
                                break;
                            }
                        }
                    }
                }


            }

            @Override
            void content_03() throws Exception {
                gamePublicFuntion.init();
                gamePublicFuntion.battleEnd();
                gamePublicFuntion.err();
                setTaskName(0);
            }
        };
    }//异火线索

    public void lmdsmw() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "斗圣秘闻";
                taskName = "dsmw.png";
                taskType = 2;
            }

            @Override
            void inOperation() throws Exception {
                result = mFairy.findPic("findWay.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (gamePublicFuntion.fad() > 6) {
                        result = mFairy.findPic(86, 137, 229, 436, "dsmw2.png");
                        mFairy.onTap(0.82f, result, "斗圣秘闻", 1000);
                    }
                }
                long l = mFairy.getColorNum(86, 661, 237, 668, "170,187,255", 0.98f);
                if (l > 50) {
                    err = 0;
                }
                gamePublicFuntion.task();
                gamePublicFuntion.ranksSetUp(3);

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                result = mFairy.findPic("qian1.png");
                mFairy.onTap(0.85f, result, "关闭使用小窗口", 1000);

                result = mFairy.findPic(573, 71, 822, 230, "err4.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(261, 107, 269, 121, "发现err4", 2000);
                    mFairy.onTap(261, 107, 269, 121, "发现err4", 1000);
                }

                gamePublicFuntion.qx();

                result = mFairy.findPic("ai.png");
                if (result.sim > 0.85f) {
                    setTaskName(3);
                    return;
                } else {
                    result = mFairy.findPic(962, 114, 1251, 575, "dsmw1.png");
                    if (result.sim > 0.85f) {
                        err = 0;
                        mFairy.onTap(0.85f, result, "斗圣秘闻聊天", 1000);
                    } else {
                        gamePublicFuntion.chat();
                    }
                }
                result = mFairy.findPic("lm1.png");
                mFairy.onTap(0.85f, result, "关闭联盟分支小窗口", 1000);
            }

            @Override
            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(500);

                result = mFairy.findPic("lmEnd.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("还没有加入联盟,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("ylEnd.png");
                if (result.sim > 0.85f) {
                    setTaskName(1);
                    return;
                }

                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {
                    if (err > 3) {

                        result = mFairy.findPic("tackS.png");
                        mFairy.onTap(0.85f, result, "手", 8000);

                        result = mFairy.findPic(86, 137, 229, 436, "dsmw2.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.82f, result, "斗圣秘闻", 1000);
                            err = 0;
                        }

                        slideTask.slideRange(new int[]{5, 7, 9}, 2, 0);

                        result = mFairy.findPic("tackChat.png");
                        mFairy.onTap(0.85f, result, "任务聊天", 2000);


                    }
                }
            }

            @Override
            void content_03() throws Exception {
                gamePublicFuntion.init();
                gamePublicFuntion.battleEnd();
                gamePublicFuntion.err();
                setTaskName(0);
            }
        };
    }//斗圣秘闻

    public void lmlmct() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "联盟刺探";
                taskName = "lmct.png";
                taskType = 2;
            }

            @Override
            void inOperation() throws Exception {
                result = mFairy.findPic("findWay.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (gamePublicFuntion.fad() > 6) {
                        result = mFairy.findPic(125, 135, 234, 352, "lmct2.png");
                        mFairy.onTap(0.82f, result, "联盟刺探", 2000);
                    }
                }
                long l = mFairy.getColorNum(86, 661, 237, 668, "170,187,255", 0.98f);
                if (l > 50) {
                    err = 0;
                }
                gamePublicFuntion.task();
                gamePublicFuntion.ranksSetUp(3);

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                result = mFairy.findPic("qian1.png");
                mFairy.onTap(0.85f, result, "关闭使用小窗口", 1000);

                result = mFairy.findPic(573, 71, 822, 230, "err4.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(261, 107, 269, 121, "发现err4", 2000);
                    mFairy.onTap(261, 107, 269, 121, "发现err4", 1000);
                }

                gamePublicFuntion.qx();

                result = mFairy.findPic("ai.png");
                if (result.sim > 0.85f) {
                    setTaskName(3);
                    return;
                } else {
                    result = mFairy.findPic(962, 114, 1251, 575, "lmct1.png");
                    if (result.sim > 0.85f) {
                        err = 0;
                        mFairy.onTap(0.85f, result, "联盟刺探聊天", 1000);
                    } else {
                        gamePublicFuntion.chat();
                    }
                }

                result = mFairy.findPic("lm1.png");
                mFairy.onTap(0.85f, result, "关闭联盟分支小窗口", 1000);
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(500);

                result = mFairy.findPic("lmEnd.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("还没有加入联盟,End!"));
                    setTaskEnd();
                    return;
                }

                gamePublicFuntion.battle(0);

                result = mFairy.findPic("ylEnd.png");
                if (result.sim > 0.85f) {
                    setTaskName(1);
                    return;
                }

                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {

                    if (err > 3) {
                        FindResult result1 = mFairy.findPic(125, 135, 234, 352, "lmct2.png");
                        if (result1.sim > 0.85f) {
                            err = 0;
                            result = mFairy.findPic(271, 64, 459, 217, "lmct3.png");
                            if (result.sim > 0.85f) {
                                oneJudgeCount = 0;
                                LtLog.e(mFairy.getLineInfo("正在战斗中...."));

                                result = mFairy.findPic(50, 146, 284, 411, "lmctEnd.png");
                                if (result.sim > 0.8f) {
                                    err = 0;
                                    mFairy.onTap(0.8f, result, "汇报药老", 3000);
                                    return;
                                }
                                Thread.sleep(3000);
                            } else {
                                if (oneJudgeCount(3)) {
                                    mFairy.onTap(0.82f, result1, "联盟刺探", 2000);
                                }
                            }
                            return;
                        }

                        slideTask.slideRange(new int[]{5, 7, 9}, 2, 0);

                        result = mFairy.findPic("tackChat.png");
                        mFairy.onTap(0.85f, result, "任务聊天", 2000);


                    }

                }
            }

            @Override
            void content_03() throws Exception {
                gamePublicFuntion.init();
                gamePublicFuntion.battleEnd();
                gamePublicFuntion.err();
                setTaskName(0);
            }
        };
    }//联盟刺探

    private int lmhb = 1;

    public void lmhb() throws Exception {
        new singleTask(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "联盟日常合并";
            }

            @Override
            void content_01() throws Exception {
                lmhb = 1;
                timeCount(13, 99);

                result = mFairy.findPic("activity.png");
                if (result.sim > 0.8f) {
                    oneJudgeCount = 0;
                    mFairy.onTap(0.8f, result, "活动", 2000);
                }

                result = mFairy.findPic(new String[]{"UIactivity.png", "UIactivity1.png"});
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("timeLimit.png");
                    mFairy.onTap(0.9f, result, "限时活动", 1500);

                    result = mFairy.findPic(270, 548, 1145, 649, "huoyue.png");
                    mFairy.onTap(0.92f, result, result.x + 25, result.y + 25, result.x + 35, result.y + 35, "领取活跃", 1000);

                    FindResult result1 = mFairy.findPic(117, 172, 842, 547, "yhxs.png");
                    if (result1.sim > 0.82f) {
                        result = mFairy.findPic(result1.x + 133, result1.y + 22,
                                result1.x + 259, result1.y + 110, "pp.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "参加", 500);
                            if (gamePublicFuntion.unableFindWay()) {
                                gamePublicFuntion.err();
                            } else {
                                Thread.sleep(1000);
                                setTaskName(2);
                            }
                            activityJudgeCount = 0;
                            return;
                        }

                        result = mFairy.findPic(result1.x + 133, result1.y + 22,
                                result1.x + 259, result1.y + 110, "kai.png");
                        if (result.sim > 0.85f) {
                            LtLog.e(mFairy.getLineInfo("任务未开启"));
                            setTaskEnd();
                        }
                    }

                    FindResult result2 = mFairy.findPic(117, 172, 842, 547, "dsmw.png");
                    if (result2.sim > 0.82f) {
                        result = mFairy.findPic(result2.x + 133, result2.y + 22,
                                result2.x + 259, result2.y + 110, "pp.png");
                        if (result.sim > 0.8f) {

                            mFairy.onTap(0.8f, result, "参加", 500);
                            if (gamePublicFuntion.unableFindWay()) {
                                gamePublicFuntion.err();
                            } else {
                                Thread.sleep(1000);
                                setTaskName(2);
                            }
                            activityJudgeCount = 0;
                            return;
                        }

                        result = mFairy.findPic(result2.x + 133, result2.y + 22,
                                result2.x + 259, result2.y + 110, "kai.png");
                        if (result.sim > 0.85f) {
                            LtLog.e(mFairy.getLineInfo("任务未开启"));
                            setTaskEnd();
                        }

                    }


                    FindResult result3 = mFairy.findPic(117, 172, 842, 547, "lmct.png");
                    if (result3.sim > 0.82f) {
                        result = mFairy.findPic(result3.x + 133, result3.y + 22,
                                result3.x + 259, result3.y + 110, "pp.png");
                        if (result.sim > 0.8f) {

                            mFairy.onTap(0.8f, result, "参加", 500);
                            if (gamePublicFuntion.unableFindWay()) {
                                gamePublicFuntion.err();
                            } else {
                                Thread.sleep(1000);
                                setTaskName(2);
                            }
                            activityJudgeCount = 0;
                            return;
                        }

                        result = mFairy.findPic(result3.x + 133, result3.y + 22,
                                result3.x + 259, result3.y + 110, "kai.png");
                        if (result.sim > 0.85f) {
                            LtLog.e(mFairy.getLineInfo("任务未开启"));
                            setTaskEnd();
                        }

                    }

                    slideActivity.slideRange(new int[]{4, 6, 8, 10, 11}, 2);
                } else {
                    if (oneJudgeCount(3)) {
                        setTaskName(0);
                        return;
                    }
                }
            }

            @Override
            void inOperation() throws Exception {
                result = mFairy.findPic("findWay.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (gamePublicFuntion.fad() > 6) {
                        mFairy.ranSwipe(149, 465, 177, 595, 2, 1000, (long) 1000);
                    }
                }

                long l = mFairy.getColorNum(86, 661, 237, 668, "170,187,255", 0.98f);
                if (l > 50) {
                    err = 0;
                }

                gamePublicFuntion.task();
                gamePublicFuntion.ranksSetUp(3);

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "安全复活", 1000);

                result = mFairy.findPic("qian1.png");
                mFairy.onTap(0.85f, result, "关闭使用小窗口", 1000);

                result = mFairy.findPic(573, 71, 822, 230, "err4.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(261, 107, 269, 121, "发现err4", 2000);
                    mFairy.onTap(261, 107, 269, 121, "发现err4", 1000);
                }

                gamePublicFuntion.qx();

                boolean b = false;

                for (int i = 0; i < 5; i++) {
                    FindResult resultchat = mFairy.findPic("chat.png");
                    if (resultchat.sim > 0.92f) {

                        result = mFairy.findPic("ai.png");
                        if (result.sim > 0.85f) {
                            setTaskName(3);
                            return;
                        }

                        i = 0;

                        result = mFairy.findPic(962, 114, 1251, 575, "lmct1.png");
                        if (result.sim > 0.85f) {
                            err = 0;
                            b = true;
                            mFairy.onTap(0.85f, result, "联盟刺探聊天", 1000);
                        }

                        result = mFairy.findPic(962, 114, 1251, 575, "dsmw1.png");
                        if (result.sim > 0.85f) {
                            err = 0;
                            b = true;
                            mFairy.onTap(0.85f, result, "斗圣秘闻聊天", 1000);
                        }

                        result = mFairy.findPic(962, 114, 1251, 575, "yhxs1.png");
                        if (result.sim > 0.85f) {
                            err = 0;
                            b = true;
                            mFairy.onTap(0.85f, result, "异火线索", 1000);
                        }

                        if (b == false) {

                            result = mFairy.findPic("lmhb3.png");
                            if (result.sim > 0.85f) {
                                setTaskName(0);
                                return;
                            }

                            mFairy.onTap(0.92f, resultchat, 1169, 613, 1170, 614, "聊天中...", 2200);
                            break;
                        }

                        mFairy.onTap(0.92f, resultchat, 1169, 613, 1170, 614, "聊天中...", 2200);
                    }else{
                        break;
                    }

                    result = mFairy.findPic("tackChat.png");
                    if (result.sim > 0.85f) {
                        b = false;
                        mFairy.onTap(0.85f, result, "任务聊天", 1500);
                    }
                }

                result = mFairy.findPic("lm1.png");
                mFairy.onTap(0.85f, result, "关闭联盟分支小窗口", 1000);
            }

            @Override
            void content_02() throws Exception {
                if (timeCount(10, 2)) {
                    lmhb++;
                    mFairy.ranSwipe(25, 549, 203, 589, 3, 1000, (long) 1000);
                    if (lmhb > 3) {
                        setTaskName(0);
                        return;
                    }
                    for (int i = 0; i < 3; i++) {
                        mFairy.ranSwipe(131, 224, 164, 382, 0, 500, (long) 200);
                    }

                    Thread.sleep(1000);
                }
                Thread.sleep(500);

                result = mFairy.findPic("lmEnd.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("还没有加入联盟,End!"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(512, 85, 660, 178, "nong.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 1168, 606, 1170, 610, "弄火石不足", 3000);
                    mFairy.ranSwipe(25, 549, 203, 589, 3, 1000, (long) 1000);
                    err = 4;
                }

                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {
                    if (err > 1) {
                        switch (lmhb) {
                            case 1:
                                LtLog.e(mFairy.getLineInfo("异火线索>>>"));
                                FindResult res1 = mFairy.findPic(86, 137, 229, 436, "yhxs2.png");
                                if (res1.sim > 0.8f) {
                                    err = 0;

                                    result = mFairy.findPic(res1.x + 50, res1.y - 20, res1.x + 250, res1.y + 30, "lmhb2.png");
                                    if (result.sim > 0.8f) {
                                        lmhb++;
                                        mFairy.ranSwipe(25, 549, 203, 589, 3, 1000, (long) 1000);
                                        for (int i = 0; i < 3; i++) {
                                            mFairy.ranSwipe(131, 224, 164, 382, 0, 500, (long) 200);
                                        }
                                        return;
                                    }

                                    mFairy.onTap(0.8f, res1, "异火线索", 1000);
                                }

                                break;
                            case 2:
                                LtLog.e(mFairy.getLineInfo("斗圣秘闻>>>"));

                                result = mFairy.findPic("tackS.png");
                                mFairy.onTap(0.8f, result, "手", 8000);

                                FindResult res2 = mFairy.findPic(86, 137, 229, 436, "dsmw2.png");
                                if (res2.sim > 0.8f) {
                                    err = 0;
                                    result = mFairy.findPic(res2.x + 50, res2.y - 20, res2.x + 250, res2.y + 30, "lmhb2.png");
                                    if (result.sim > 0.8f) {
                                        lmhb++;
                                        mFairy.ranSwipe(25, 549, 203, 589, 3, 1000, (long) 1000);
                                        for (int i = 0; i < 3; i++) {
                                            mFairy.ranSwipe(131, 224, 164, 382, 0, 500, (long) 200);
                                        }
                                        return;
                                    }

                                    mFairy.onTap(0.8f, res2, "斗圣秘闻", 1000);
                                }
                                break;
                            case 3:
                                LtLog.e(mFairy.getLineInfo("联盟刺探>>>"));

                                FindResult result1 = mFairy.findPic(125, 135, 234, 352, "lmct2.png");
                                if (result1.sim > 0.8f) {
                                    err = 0;

                                    gamePublicFuntion.battle(0);

                                    result = mFairy.findPic(result1.x + 50, result1.y - 20, result1.x + 250, result1.y + 30, "lmhb2.png");
                                    if (result.sim > 0.8f) {
                                        setTaskName(0);
                                        return;
                                    }

                                    result = mFairy.findPic(271, 64, 459, 217, "lmct3.png");
                                    if (result.sim > 0.85f) {
                                        oneJudgeCount = 0;
                                        LtLog.e(mFairy.getLineInfo("正在战斗中...."));

                                        Thread.sleep(3000);

                                    } else {
                                        if (oneJudgeCount(3)) {
                                            mFairy.onTap(0.8f, result1, "联盟刺探", 2000);
                                        }
                                    }
                                    return;
                                }

                                break;

                        }

                        slideTask.slideRange(new int[]{5, 6,7,8}, 2, 0);

                        result = mFairy.findPic("tackChat.png");
                        mFairy.onTap(0.85f, result, "任务聊天", 2000);

                    }
                }
            }

            @Override
            void content_03() throws Exception {
                gamePublicFuntion.init();
                gamePublicFuntion.battleEnd();
                gamePublicFuntion.err();
                setTaskName(0);
            }
        };
    }//联盟日常合并

    /***采集*/
    public void lifeCy() throws Exception {

        int grade = 1;//当前等级
        int weizhi1 = 1;
        int weizhi2 = 1;


        if (AtFairyConfig.getOption("1828").equals("1")) {
            TaskMain.TASKNAME = "采药>>>";
            if (!AtFairyConfig.getOption("setcy").equals("")) {
                grade = Integer.parseInt(AtFairyConfig.getOption("setcy"));
            }
            if (!AtFairyConfig.getOption("weizhi").equals("")) {
                int wei = Integer.parseInt(AtFairyConfig.getOption("weizhi"));
                if (wei <= 3) {
                    weizhi1 = 1;
                    weizhi2 = wei;
                } else {
                    weizhi1 = 2;
                    weizhi2 = wei - 3;
                }
            }
            cy(grade, weizhi1, weizhi2);
        }



       /* *//**1级*//*
        if (AtFairyConfig.getOption("cy1").equals("1")) {
            TaskMain.TASKNAME = "采药>>>青灵草";
            cy(1, 1, 1);
        }

        *//**2级*//*
        if (AtFairyConfig.getOption("cy2").equals("1")) {
            TaskMain.TASKNAME = "采药>>>寒霜花霖";
            cy(2, 1, 1);
        }
        if (AtFairyConfig.getOption("cy3").equals("1")) {
            TaskMain.TASKNAME = "采药>>>修魔果";
            cy(2, 1, 2);
        }
        if (AtFairyConfig.getOption("cy4").equals("1")) {
            TaskMain.TASKNAME = "采药>>>流光花";
            cy(2, 1, 3);
        }
        if (AtFairyConfig.getOption("cy5").equals("1")) {
            TaskMain.TASKNAME = "采药>>>玉杏根";
            cy(2, 2, 1);
        }

        *//**3级*//*
        if (AtFairyConfig.getOption("cy6").equals("1")) {
            TaskMain.TASKNAME = "采药>>>刺魂叶";
            cy(3, 1, 1);
        }
        if (AtFairyConfig.getOption("cy7").equals("1")) {
            TaskMain.TASKNAME = "采药>>>地妖魔根";
            cy(3, 1, 2);
        }
        if (AtFairyConfig.getOption("cy8").equals("1")) {
            TaskMain.TASKNAME = "采药>>>天琼树枝";
            cy(3, 1, 3);
        }

        *//**4级*//*
        if (AtFairyConfig.getOption("cy9").equals("1")) {
            TaskMain.TASKNAME = "采药>>>厄血魔叶";
            cy(4, 1, 1);
        }
        if (AtFairyConfig.getOption("cy10").equals("1")) {
            TaskMain.TASKNAME = "采药>>>轮回草";
            cy(4, 1, 2);
        }
        if (AtFairyConfig.getOption("cy11").equals("1")) {
            TaskMain.TASKNAME = "采药>>>紫轩草";
            cy(4, 1, 3);
        }

        *//**5级*//*
        if (AtFairyConfig.getOption("cy12").equals("1")) {
            TaskMain.TASKNAME = "采药>>>火魔叶";
            cy(5, 1, 1);
        }
        if (AtFairyConfig.getOption("cy13").equals("1")) {
            TaskMain.TASKNAME = "采药>>>育灵树枝";
            cy(5, 1, 2);
        }
        if (AtFairyConfig.getOption("cy14").equals("1")) {
            TaskMain.TASKNAME = "采药>>>幽冥草";
            cy(5, 1, 3);
        }

        *//**6级*//*
        if (AtFairyConfig.getOption("cy15").equals("1")) {
            TaskMain.TASKNAME = "采药>>>蟒魂魔叶";
            cy(6, 1, 1);
        }
        if (AtFairyConfig.getOption("cy16").equals("1")) {
            TaskMain.TASKNAME = "采药>>>噬魔花";
            cy(6, 1, 2);
        }
        if (AtFairyConfig.getOption("cy17").equals("1")) {
            TaskMain.TASKNAME = "采药>>>恶魔厥";
            cy(6, 1, 3);
        }

        *//**7级*//*
        if (AtFairyConfig.getOption("cy18").equals("1")) {
            TaskMain.TASKNAME = "采药>>>万年青灵藤";
            cy(7, 1, 1);
        }
        if (AtFairyConfig.getOption("cy19").equals("1")) {
            TaskMain.TASKNAME = "采药>>>雪骨参";
            cy(7, 1, 2);
        }
        if (AtFairyConfig.getOption("cy20").equals("1")) {
            TaskMain.TASKNAME = "采药>>>血精妖果";
            cy(7, 1, 3);
        }

        *//**8级*//*
        if (AtFairyConfig.getOption("cy21").equals("1")) {
            TaskMain.TASKNAME = "采药>>>青焰草";
            cy(8, 1, 1);
        }
        if (AtFairyConfig.getOption("cy22").equals("1")) {
            TaskMain.TASKNAME = "采药>>>三叶青芝";
            cy(8, 1, 2);
        }
        if (AtFairyConfig.getOption("cy23").equals("1")) {
            TaskMain.TASKNAME = "采药>>>千灵草";
            cy(8, 1, 3);
        }

        *//**9级*//*
        if (AtFairyConfig.getOption("cy24").equals("1")) {
            TaskMain.TASKNAME = "采药>>>夜灵叶";
            cy(9, 1, 1);
        }
        if (AtFairyConfig.getOption("cy25").equals("1")) {
            TaskMain.TASKNAME = "采药>>>龙血芝";
            cy(9, 1, 2);
        }
        if (AtFairyConfig.getOption("cy26").equals("1")) {
            TaskMain.TASKNAME = "采药>>>骨灵果";
            cy(9, 1, 3);
        }


        *//**10级*//*
        if (AtFairyConfig.getOption("cy27").equals("1")) {
            TaskMain.TASKNAME = "采药>>>清体草";
            cy(10, 1, 1);
        }
        if (AtFairyConfig.getOption("cy28").equals("1")) {
            TaskMain.TASKNAME = "采药>>>水灵莲子";
            cy(10, 1, 2);
        }
        if (AtFairyConfig.getOption("cy29").equals("1")) {
            TaskMain.TASKNAME = "采药>>>阴神花";
            cy(10, 1, 3);
        }

        *//**11级*//*
        if (AtFairyConfig.getOption("cy30").equals("1")) {
            TaskMain.TASKNAME = "采药>>>水阳灵叶";
            cy(11, 1, 1);
        }
        if (AtFairyConfig.getOption("cy31").equals("1")) {
            TaskMain.TASKNAME = "采药>>>兰馨草";
            cy(11, 1, 2);
        }
        if (AtFairyConfig.getOption("cy32").equals("1")) {
            TaskMain.TASKNAME = "采药>>>音灵叶";
            cy(11, 1, 3);
        }*/

    }//采药

    public void lifeCk() throws Exception {
        int grade = 1;

        if (!AtFairyConfig.getOption("setck").equals("")) {
            grade = Integer.parseInt(AtFairyConfig.getOption("setck"));
        }

        if (AtFairyConfig.getOption("ak").equals("1")) {
            TaskMain.TASKNAME = grade + "级金矿";
            ck(grade, 1, 1);
        }
        if (AtFairyConfig.getOption("bk").equals("1")) {
            TaskMain.TASKNAME = grade + "级银矿";
            ck(grade, 1, 2);
        }

        if (AtFairyConfig.getOption("dk").equals("1")) {
            TaskMain.TASKNAME = grade + "级铁矿";
            ck(grade, 1, 3);
        }

        if (AtFairyConfig.getOption("ck").equals("1")) {
            TaskMain.TASKNAME = grade + "级铜矿";
            ck(grade, 2, 1);
        }


    }//采矿

    abstract class lifeSkills extends TaskContent {
        public lifeSkills(AtFairyImpl mFairy) throws Exception {
            super(mFairy);
        }

        @Override
        void init() throws Exception {
            gamePublicFuntion.init();
            gamePublicFuntion.ranksSetUp(3);
            setTaskName(1);
        }

        @Override
        void inOperation() throws Exception {
            result = mFairy.findPic("findWay.png");
            if (result.sim > 0.8f) {
                err = 0;
            }
            long l = mFairy.getColorNum(86, 661, 237, 668, "170,187,255", 0.95f);
            if (l > 30) {
                err = 0;
            }
            gamePublicFuntion.task();
            gamePublicFuntion.ranksSetUp(3);

            result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
            mFairy.onTap(0.8f, result, "安全复活", 1000);

            result = mFairy.findPic("qian1.png");
            mFairy.onTap(0.85f, result, "关闭使用小窗口", 1000);
        }

        @Override
        void content_01() throws Exception {
            timeCount(10, 0);

            result = mFairy.findPic("UIcj.png");
            if (result.sim > 0.85f) {
                setTaskName(2);
                return;
            } else {
                result = mFairy.findPic(1004, 633, 1274, 719, new String[]{"cj.png", "cj1.png"});
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.85f, result, "生活", 1500);
                } else {
                    mFairy.onTap(1240, 401, 1241, 402, "右侧缩放栏", 1500);
                }
            }
        }

    }

    private int userCyNum = 9999;
    private int num = 0;

    public void cy(final int num1, final int num2, final int num3) throws Exception {
        new lifeSkills(mFairy) {

            @Override
            void create() throws Exception {
                if (!AtFairyConfig.getOption("cs").equals("")) {
                    userCyNum = Integer.parseInt(AtFairyConfig.getOption("cs"));
                }
                num = 0;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                gamePublicFuntion.qx();
            }

            @Override
            void content_02() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("cy.png");
                if (result.sim > 0.85f) {

                    result = mFairy.findPic(857, 180, 1215, 307, "cy1.png");
                    if (result.sim > 0.8f) {

                        result = mFairy.findPic("caiji.png");
                        if (result.sim > 0.85f) {
                            switch (num1) {
                                case 1:
                                    mFairy.onTap(1097, 204, 1131, 221, "分类栏:" + num1 + "级", 2000);
                                    break;
                                case 2:
                                    mFairy.onTap(938, 252, 971, 262, "分类栏:" + num1 + "级", 2000);
                                    break;
                                case 3:
                                    mFairy.onTap(1092, 249, 1130, 264, "分类栏:" + num1 + "级", 2000);
                                    break;
                                case 4:
                                    mFairy.onTap(933, 293, 965, 310, "分类栏:" + num1 + "级", 2000);
                                    break;
                            }

                        } else {
                            result = mFairy.findPic("caiji1.png");
                            if (result.sim > 0.85f) {
                                mFairy.onTap(1033, 259 + ((num1 - 1) * 45), 1035, 260 + ((num1 - 1) * 45), "分类栏:" + num1 + "级", 2000);
                            }
                        }

                        result = mFairy.findPic(857, 180, 1215, 307, "cy1.png");
                        if (result.sim < 0.85f) {
                            mFairy.onTap(445 + ((num3 - 1) * 270), 330 + ((num2 - 1) * 170), 448 + ((num3 - 1) * 270), 332 + ((num2 - 1) * 170), "前往采集", 2000);
                            return;
                        }

                    } else {
                        mFairy.onTap(998, 165, 1046, 179, "缩放栏", 2000);
                    }

                } else {
                    result = mFairy.findPic(143, 221, 222, 572, "cy4.png");
                    mFairy.onTap(0.85f, result, "采药", 1000);
                }


                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 1);
                Thread.sleep(1000);
                result = mFairy.findPic("cy3.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(0.85f, result, "采药中..." + num, 500);

                    if (mapCount(0.8f, 518, 210, 557, 339, "cjEnd.png")) {
                        LtLog.e(mFairy.getLineInfo("精力不足,End!"));

                        if (AtFairyConfig.getOption("jingli").equals("1")) {
                            if (pg()) {
                                setTaskEnd();
                            }
                            TaskMain.TASKNAME = "采药";
                        } else {
                            setTaskEnd();
                        }

                        return;
                    }
                    num++;
                    if (num >= userCyNum * 10) {
                        LtLog.e(mFairy.getLineInfo("采集次数已达到限制,End!"));
                        setTaskEnd();
                        return;
                    }
                }
            }
        };
    }//采药

    private int userCkNum = 99;

    public void ck(final int num1, final int num2, final int num3) throws Exception {
        new lifeSkills(mFairy) {
            @Override
            void create() throws Exception {
                if (!AtFairyConfig.getOption("ks").equals("")) {
                    userCkNum = Integer.parseInt(AtFairyConfig.getOption("ks"));
                }
                num = 0;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                gamePublicFuntion.qx();
            }

            @Override
            void content_02() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic("ck.png");
                if (result.sim > 0.85f) {
                    result = mFairy.findPic(857, 180, 1215, 307, "cy1.png");
                    if (result.sim > 0.85f) {

                        result = mFairy.findPic("caiji.png");
                        if (result.sim > 0.85f) {
                            switch (num1) {
                                case 1:
                                    mFairy.onTap(1097, 204, 1131, 221, "分类栏:" + num1 + "级", 2000);
                                    break;
                                case 2:
                                    mFairy.onTap(938, 252, 971, 262, "分类栏:" + num1 + "级", 2000);
                                    break;
                                case 3:
                                    mFairy.onTap(1092, 249, 1130, 264, "分类栏:" + num1 + "级", 2000);
                                    break;
                                case 4:
                                    mFairy.onTap(933, 293, 965, 310, "分类栏:" + num1 + "级", 2000);
                                    break;
                            }

                        } else {
                            result = mFairy.findPic("caiji1.png");
                            if (result.sim > 0.85f) {
                                mFairy.onTap(1033, 259 + ((num1 - 1) * 45), 1035, 260 + ((num1 - 1) * 45), "分类栏:" + num1 + "级", 2000);
                            }
                        }

                        result = mFairy.findPic(857, 180, 1215, 307, "cy1.png");
                        if (result.sim < 0.85f) {
                            mFairy.onTap(445 + ((num3 - 1) * 270), 330 + ((num2 - 1) * 170), 448 + ((num3 - 1) * 270), 332 + ((num2 - 1) * 170), "前往采集", 2000);
                        }
                    } else {
                        mFairy.onTap(998, 165, 1046, 179, "缩放栏", 2000);
                    }
                } else {
                    result = mFairy.findPic(143, 221, 222, 572, "ck1.png");
                    mFairy.onTap(0.85f, result, "采矿", 1000);
                }

                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 1);
                Thread.sleep(1000);
                result = mFairy.findPic("fdz2.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    mFairy.onTap(0.85f, result, "采矿中..." + num, 500);

                    if (mapCount(0.8f, 518, 210, 557, 339, "cjEnd.png")) {
                        LtLog.e(mFairy.getLineInfo("精力不足,End!"));
                        if (AtFairyConfig.getOption("jingli").equals("1")) {
                            if (pg()) {
                                setTaskEnd();
                            }
                            TaskMain.TASKNAME = "挖矿";
                        } else {
                            setTaskEnd();
                        }
                        return;
                    }
                    num++;
                    if (num >= userCkNum * 5) {
                        LtLog.e(mFairy.getLineInfo("采集次数已达到限制,End!"));
                        setTaskEnd();
                        return;
                    }
                }
            }
        };
    }//采矿

    private int userXbNum = 99;
    private int xb = 1;
    private boolean xbBools = false;

    public void xb() throws Exception {
        new lifeSkills(mFairy) {
            @Override
            void create() throws Exception {
                TaskMain.TASKNAME = "生活寻宝";
                if (!AtFairyConfig.getOption("xb").equals("")) {
                    userXbNum = Integer.parseInt(AtFairyConfig.getOption("xb"));
                }

                if (!AtFairyConfig.getOption("bz").equals("")) {
                    xb = Integer.parseInt(AtFairyConfig.getOption("bz"));
                }
                num = 0;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic("xb6.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(839, 497, 880, 508, "前往地图", 500);
                } else {
                    result = mFairy.findPic(416, 243, 952, 450, "xb5.png");
                    if (result.sim > 0.8f) {
                        xbBools = true;
                        mFairy.onTap(0.8f, result, 844, 494, 845, 495, "开启自动", 1000);

                        setTaskName(3);
                        return;
                    } else {
                        gamePublicFuntion.qx();
                    }
                }
            }

            @Override
            void content_01() throws Exception {
                super.content_01();
                xbBools = false;
            }

            @Override
            void content_02() throws Exception {
                timeCount(10, 99);


                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic("xb3.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    if (num >= userXbNum) {
                        LtLog.e(mFairy.getLineInfo("寻宝次数已达到限制,End!"));
                        setTaskEnd();
                        return;
                    }
                    num++;
                    mFairy.onTap(0.85f, result, "搜索", 2000);

                    while (mFairy.condit()) {
                        result = mFairy.findPic(622, 405, 764, 457, "xb11.png");
                        if (result.sim < 0.85f) {
                            break;
                        }
                    }

                    if (mapCount(0.8f, 330, 218, 708, 380, "xb12.png")) {
                        LtLog.e(mFairy.getLineInfo("精力不足,End!"));
                        if (AtFairyConfig.getOption("jingli").equals("1")) {
                            if (pg()) {
                                setTaskEnd();
                            }
                            TaskMain.TASKNAME = "寻宝";
                            setTaskName(0);
                        } else {
                            setTaskEnd();
                        }
                        return;
                    }
                    return;
                }

                result = mFairy.findPic("xb4.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic(380, 136, 1048, 598, new String[]{"bx1.png", "bx2.png", "bx3.png"});
                    mFairy.onTap(0.85f, result, "找到宝箱", 1000);
                    setTaskName(3);
                    return;

                } else {
                    result = mFairy.findPic("xb.png");
                    if (result.sim > 0.85f) {
                        switch (xb) {
                            case 1:
                                mFairy.onTap(440, 281, 445, 282, "当前等级", 1000);
                                break;
                            case 2:
                                mFairy.onTap(710, 281, 712, 282, "当前等级-1", 1000);
                                break;
                            case 3:
                                mFairy.onTap(983, 281, 985, 282, "当前等级-2", 1000);
                                break;
                            case 4:
                                mFairy.onTap(438, 457, 440, 460, "当前等级-3", 1000);
                                break;
                        }

                    } else {
                        result = mFairy.findPic(143, 221, 222, 572, "xb1.png");
                        mFairy.onTap(0.85f, result, "寻宝", 2000);

                        result = mFairy.findPic("xb2.png");
                        mFairy.onTap(0.85f, result, "返回", 1000);
                    }
                }
            }

            @Override
            void content_03() throws Exception {
                timeCount(5, 1);
                Thread.sleep(200);

                result = mFairy.findPic("xb7.png");
                if (result.sim > 0.85f) {
                    setTaskName(1);
                    mFairy.onTap(602, 502, 668, 514, "开始寻宝", 500);
                    return;
                }

                result = mFairy.findPic("xb7.png");
                if (result.sim > 0.85f) {
                    setTaskName(1);
                    mFairy.onTap(608, 495, 648, 509, "继续寻宝", 500);
                    return;
                }

                result = mFairy.findPic(668, 4, 1278, 479, "close1.png");
                mFairy.onTap(0.8f, result, "关闭", 500);

                if (xbBools == false) {
                    result = mFairy.findPic("fdz2.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "开启宝箱", 3500);
                        setTaskName(1);
                        return;
                    }
                } else {
                    result = mFairy.findPic("fdz2.png");
                    if (result.sim > 0.85f) {
                        err = 0;
                    }
                }
            }
        };
    }//寻宝
}
