package com.script.fairy;


import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class LimitlessTask {
    public GamePublicFuntion gamePublicFuntion;
    public FindResult result;
    public AtFairyImpl mFairy;
    public SingleTask singleTask;
    public TeamTask teamTask;
    public int week = 0;
    public int hour = 0;
    public int minute = 0;

    public LimitlessTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        singleTask = new SingleTask(ypFairy);
        teamTask = new TeamTask(ypFairy);
    }

    private int go1,go2,go3,go4=0,go5=0,go6=0;
    private boolean ls = true;

    public void guaji() throws Exception {
        new TaskContent(mFairy, "挂机") {

            void create() throws Exception {
                super.create();
                xianshi();
                setTaskName(0);
            }

            void init() throws Exception {
                gamePublicFuntion.taskInit(0);
                setTaskName(1);
                oneSecond = 0;
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

                    result = mFairy.findPic(393, 251, 682, 414, "gmerr1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(725, 426, 761, 440, "确定", 500);
                        return;
                    }

                    mFairy.onTap(0.8f, qx, "取消", 500);
                }
            }//取消

            void gua() throws Exception {
                if (AtFairyConfig.getOption("gtype").equals("1")) {
                    mFairy.onTap(256,552,340,568,"原地挂机",1200);

                    if(mapCount(0.8f,620,158,723,471,"guaji4.png")){
                        setTaskEnd();
                        return;
                    }
                } else {
                    LtLog.e(mFairy.getLineInfo("等级挂机"));
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
            }//挂机选择

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(236, 6, 783, 100, new String[]{"guaji.png","guaji6.png"});
                mFairy.onTap(0.72f, result, "挂机", 1000);

                result = mFairy.findPic("guaji1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    int num = getNumberAssembly(AtFairyConfig.getOption("ls"));
                    if(num!=-1 && ls) {
                        ls=false;
                        if (num > 20) {
                            num = 20;
                        }
                        for (int i = 0; i < num; i++) {
                            mFairy.onTap(1004, 636, 1042, 651, "领取", 500);

                            result = mFairy.findPic("guaji3.png");
                            if (result.sim > 0.75f) {
                                mFairy.onTap(508, 410, 555, 424, "双倍点数不足", 500);
                                break;
                            }
                        }
                        Thread.sleep(1000);
                        gamePublicFuntion.qx();
                    }
                    gua();
                    setTaskName(2);
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(10,0);

                if(xianshi()){
                    create();
                    setTaskName(0);
                    return;
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3)==false) {
                        err = 0;
                    }
                }else{
                    mFairy.initMatTime();
                }

            }

            boolean xianshi() throws Exception{
                boolean bool = false;
                while (true) {
                    week = mFairy.week();
                    hour = mFairy.dateHour();
                    minute = mFairy.dateMinute();

                    if (AtFairyConfig.getOption("sjqy").equals("1") && go1 == 0) {
                        if (hour >= 11) {
                            gamePublicFuntion.stopBattle();
                            singleTask.sjqy();
                            go1=1;
                            bool=true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("kjxs").equals("1") && go2 == 0) {
                        if (week >= 1 && week <= 5 && hour >= 17) {
                            gamePublicFuntion.stopBattle();
                            singleTask.kjxs();
                            go2=1;
                            bool=true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("ycmht").equals("1") && go3 == 0) {
                        if (week ==5 && hour>=19 && hour < 22) {
                            gamePublicFuntion.stopBattle();
                            teamTask.ycmht();
                            gamePublicFuntion.home();
                            go3=1;
                            bool=true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("mpcg").equals("1") && go4 == 0) {
                        if (week == 1 && hour==21 && minute<40) {
                            gamePublicFuntion.stopBattle();
                            teamTask.mpcg();
                            gamePublicFuntion.home();
                            go4=1;
                            bool=true;
                            continue;
                        }
                    }

                    if (!AtFairyConfig.getOption("hy").equals("") && go5 == 0) {
                        if (week == 3 && hour==21 && minute<40) {
                            gamePublicFuntion.stopBattle();
                            teamTask.hdxb();
                            gamePublicFuntion.home();
                            go5=1;
                            bool=true;
                            continue;
                        }
                    }


                    if (AtFairyConfig.getOption("9053").equals("1") && go6 == 0) {
                        if (week == 3 && hour==21) {
                            gamePublicFuntion.stopBattle();


                            switch (AtFairyConfig.getOption("zhlly")){
                                case "1":
                                    singleTask.zhlly();
                                    break;
                                case "2":
                                    teamTask.lmy();
                                    break;
                            }

                            gamePublicFuntion.home();
                            go6=1;
                            bool=true;
                            continue;
                        }
                    }

                    return bool;
                }
            }//限时
        };
    }//挂机

}