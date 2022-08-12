

package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

public class SingleTask {

    public GamePublicFuntion gamePublicFuntion;
    public AtFairyImpl mFairy;
    private String activityName = "";
    private int activityType = 1;
    FindResult result;

    boolean mainScene_timeBool = false;


    public SingleTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }

    public void setUp() throws Exception {
        new TaskContent(mFairy, "设置") {

            void init() throws Exception {
                gamePublicFuntion.close(3);
                result = mFairy.findPic(256, 26, 575, 120, "nn3.png");
                mFairy.onTap(0.8f, result, 90, 49, 115, 74, "接受挑战吧", 1000);

                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic(954, 360, 1274, 685, "set1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "设置", 1000);
                } else {
                    result = mFairy.findPic("set2.png");
                    mFairy.onTap(0.8f, result, "扩展栏", 1000);
                }

                result = mFairy.findPic("set3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    mFairy.onTap(266, 413, 267, 414, "0.7地图", 300);
                    mFairy.onTap(266, 413, 267, 414, "0.7地图", 300);

                    for (int i = 0; i < 4; i++) {
                        result = mFairy.findPic(242, 153, 705, 209, "s1.png");
                        mFairy.onTap(0.8f, result, "取消勾选", 500);
                    }


                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic(252, 222, 307, 268, "s1.png");
                        if(result.sim>0.8f){
                            if (!AtFairyConfig.getOption("qxuse").equals("1") || !AtFairyConfig.getOption("task_id").equals("2737")) {
                                mFairy.onTap(0.8f, result, "取消自动装备", 1000);

                            }
                        }else{
                            if (AtFairyConfig.getOption("qxuse").equals("1") || AtFairyConfig.getOption("task_id").equals("2737")) {
                                mFairy.onTap(275,242,282,250,"自动穿装备",1000);
                            }
                        }
                    }

                    mFairy.onTap(1082, 333, 1092, 361, "战斗", 1000);
                }


                result = mFairy.findPic("set4.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic(247, 158, 332, 212, "s1.png");
                        mFairy.onTap(0.8f, result, "取消勾选", 500);
                    }


                    mFairy.onTap(1082, 76, 1094, 88, "", 1000);
                    setTaskEnd();
                    return;
                }

                timeCount(5, 0);
            }
        };

    }//设置

    public void nn() throws Exception {

        new TaskContent(mFairy, "新手主线任务") {

            void create() throws Exception {
                super.create();
                gamePublicFuntion.nnBool = true;
            }

            void init() throws Exception {
                gamePublicFuntion.close(3);

                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.7f, result, 15, 137, 31, 151, "任务", 500);
                mFairy.onTap(0.7f, result, 19, 68, 26, 90, "任务", 1000);

                result = mFairy.findPic("task2.png");
                mFairy.onTap(0.7f, result, 19, 68, 26, 90, "任务", 500);

                setTaskName(1);
            }

            void content_01() throws Exception {

                FindResult zhuxian = mFairy.findPic(71, 33, 112, 139, "zhu.png");
                if (zhuxian.sim > 0.8f) {
                    err = 0;

                    if (gamePublicFuntion.mainScene()) {
                        if (mainScene_timeBool) {
                            Thread.sleep(6000);
                            mainScene_timeBool = false;
                        }
                    } else {
                        gamePublicFuntion.autoBattle();
                        mainScene_timeBool = true;
                    }

                    mFairy.onTap(0.8f, zhuxian, "主线", 3000);

                }

                result = mFairy.findPic(907, 290, 1179, 416, "fh.png");
                mFairy.onTap(0.8f, result, "免费复活", 3000);

                result = mFairy.findPic(782, 496, 946, 627, "chuan.png");
                mFairy.onTap(0.8f, result, "传送", 3000);

                result = mFairy.findPic(849, 471, 1014, 544, "use.png");
                mFairy.onTap(0.8f, result, "使用", 3000);

                result = mFairy.findPic(498, 536, 775, 616, "nn1.png");
                mFairy.onTap(0.8f, result, "免费激活", 2000);
                mFairy.onTap(0.8f, result, 1086, 92, 1098, 105, "", 1000);

                if (timeMap("pack", 60000)) {
                    gamePublicFuntion.clearPackage();
                }

                result = mFairy.findPic("nn2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "新手任务结束", 1000);
                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic(487, 528, 801, 640, "zhuansheng.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "我要转生", 2000);

                    result = mFairy.findPic(487, 528, 801, 640, "nn4.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("等级不足结束任务！"));
                        setTaskEnd();
                    }


                    mFairy.onTap(0.8f, result, 811, 409, 825, 419, "我要转生", 500);
                    mFairy.onTap(0.8f, result, 1085, 95, 1094, 109, "我要转生", 1000);
                }


                timeCount(10, 0);
            }
        };


    }//新手任务


}