package com.script.fairy;

import android.renderscript.Matrix2f;
import android.support.v4.app.INotificationSideChannel;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
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

    public TeamTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        singleTask = new SingleTask(ypFairy);
    }

    public void copyTask() throws Exception {


        if (AtFairyConfig.getOption("pthswq").equals("1")) {
            LtLog.e("-----开始黑石问情副本（普通）");
            copy(1, 1);
        }

        if (AtFairyConfig.getOption("ptgkjh").equals("1")) {
            LtLog.e("-----开始古窟惊魂副本(普通)");
            copy(1, 2);
        }

        if (AtFairyConfig.getOption("ptxlhj").equals("1")) {
            LtLog.e("-----开始修罗浩劫副本（普通）");
            copy(1, 3);
        }


        if (AtFairyConfig.getOption("jyhswq").equals("1")) {
            LtLog.e("-----开始黑石问情副本（精英）");
            copy(2, 1);
        }

        if (AtFairyConfig.getOption("jygkjh").equals("1")) {
            LtLog.e("-----开始古窟惊魂副本(精英)");
            copy(2, 2);
        }

        if (AtFairyConfig.getOption("jyxlhj").equals("1")) {
            LtLog.e("-----开始修罗浩劫副本（精英）");
            copy(2, 3);
        }


        if (AtFairyConfig.getOption("yxzmdz").equals("1")) {
            LtLog.e("-----开始正魔大战（英雄）");
            copy(3, 4);
        }

        if (AtFairyConfig.getOption("yxssms").equals("1")) {
            LtLog.e("-----开始兽神灭世（英雄）");
            copy(3, 5);
        }


    }//剧情分类

    public void teamOfDragon() throws Exception {


        if (AtFairyConfig.getOption("jqfb").equals("1")) {
            LtLog.e("-----开始剧情副本任务");
            copyTask();
        }

        if (AtFairyConfig.getOption("lsym").equals("1")) {
            LtLog.e("-----开始乱世妖魔任务");
            singleTask.lsym();
        }

        if (AtFairyConfig.getOption("zy").equals("1")) {
            LtLog.e("-----开始镇妖任务");
            zy();
        }


    }//一条龙

    public void zy() throws Exception {
        int bj = 0, bj1 = 0, bj2 = 0, bj3 = 0, bj4 = 0;
        int count = 0, count1 = 0, count2 = 0;
        int cs = 0, cs1 = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------镇妖任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "zyTu.png",2, 2);
                if (bj == -1) {
                    break;
                }
                cs1 = 0;
                bj3 = 0;
            }
            if (bj == 3) {
                cs1++;
                if (cs1 > 30) {
                    bj = 0;
                    bj1 = 0;
                    cs1 = 0;
                }


                if (bj1 == 1) {
                    result = mFairy.findPic(939, 454, 1113, 699, "zyTack.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "镇妖活动",1000);
                        bj = 5;
                        Thread.sleep(5000);
                    }
                } else {
                    result = mFairy.findPic(939, 454, 1113, 699, "jbzd.png");
                    if (result.sim > 0.8f) {
                         mFairy.onTap(0.8f, result, "便捷组队",1000);
                        bj = 4;
                    }
                }


                result = mFairy.findPic("create.png");
                if (result.sim > 0.8f) {
                     mFairy.onTap(0.8f, result, "创建队伍",1000);
                    Thread.sleep(2000);
                    bj2 = 1;
                    bj = 4;
                }


                if (bj2 == 1) {
                    result = mFairy.findPic("change.png");
                    if (result.sim > 0.9f) {
                         mFairy.onTap(0.9f, result, "更改",1000);
                        Thread.sleep(5000);
                        bj2 = 0;
                    }
                }


                gamePublicFuntion.matchingTarget();//匹配目标


            }
            if (bj == 4) {

                int n = gamePublicFuntion.organizeATeam();

                if (n == 1) {
                    bj = gamePublicFuntion.gg("zyTu.png", 2);

                    if (bj == 3) {
                        bj1 = 1;
                    }

                } else {
                    bj = 0;
                }
            }


            if (bj == 5) {

                result = mFairy.findPic("qwe6.png");
                mFairy.onTap(0.8f, result, 517, 423, 518,424,"取消",1000);


                result = mFairy.findPic("qwe5.png");
                mFairy.onTap(0.8f,  result,762, 426,763,427, "确定",1000);


                result = mFairy.findPic("auto1.png");
                 mFairy.onTap(0.7f, result, "自动战斗",1000);


                result = mFairy.findPic("Invitation1.png");
                if (result.sim > 0.8f) {
                    for (int i = 0; i < 4; i++) {
                        result = mFairy.findPic(190, 89, 1067, 582, "agree.png");
                         mFairy.onTap(0.7f, result, "同意",1000);
                        Thread.sleep(2000);
                        if (i == 3) {
                            result = mFairy.findPic("ranks1.png");
                             mFairy.onTap(0.9f, result, "点队伍",1000);
                        }
                    }
                }


                result = mFairy.findPic("auto.png");
                if (result.sim > 0.7f) {
                    count = 0;
                    count1 = 0;
                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic("rightTip.png");
                         mFairy.onTap(0.8f, result, "Tip",1000);

                        result = mFairy.findPic("task1.png");
                        mFairy.onTap(0.7f,result, 1064, 125, 1065,126, "任务",1000);
                    }
                    if (AtFairyConfig.getOption("cd").equals("1")) {
                        if (bj3 == 0) {
                            for (int i = 0; i < 5; i++) {
                                result = mFairy.findPic("catch5.png");
                                 mFairy.onTap(0.7f, result, "展开",1000);
                                Thread.sleep(1000);
                                result = mFairy.findPic("gj.png");
                                 mFairy.onTap(0.7f, result, "挂机图标",1000);
                            }
                            Thread.sleep(2000);
                            result = mFairy.findPic("hdskUi.png");
                            if (result.sim > 0.8f) {
                                for (int i = 0; i < 2; i++) {
                                    mFairy.onTap(912, 138,913,139, "领双",1000);
                                    Thread.sleep(2000);
                                }
                            }
                            mFairy.onTap(1099, 92,1100,93, "关闭",1000);
                            bj3 = 1;
                        }
                    } else if (AtFairyConfig.getOption("cd").equals("5")){
                        gamePublicFuntion.zyTool("five.png", "one.png");
                    } else if (AtFairyConfig.getOption("cd").equals("6")) {
                        gamePublicFuntion.zyTool("six.png", "one.png");
                    } else if (AtFairyConfig.getOption("cd").equals("7")){
                        gamePublicFuntion.zyTool("seven.png", "one.png");
                    }


                    gamePublicFuntion.vtt();


                } else {

                    result = mFairy.findPic(1026, 155, 1136, 517, new String[]{"zy.png", "zy1.png"});
                    if (result.sim > 0.7f) {
                         mFairy.onTap(0.7f, result, "镇妖",1000);
                        count = 0;
                        count1++;
                    }


                    if (count1 > 5) {

                        result = mFairy.findPic("ranksButton.png");
                         mFairy.onTap(0.7f, result, "点击队伍",1000);
                         mFairy.onTap(0.7f, result, "点击队伍",1000);
                        Thread.sleep(3000);
                        result = mFairy.findPic("matching.png");
                         mFairy.onTap(0.8f, result, "开始匹配",1000);
                        bj = 4;
                    }

                    result = mFairy.findPic("task1.png");
                    mFairy.onTap(0.7f,result,  1064, 125, 1065,1226,"任务",1000);


                    result = mFairy.findPic("qwe8.png");
                    if (result.sim > 0.9f) {
                        mFairy.onTap(746, 429,747,430, "继续镇妖",1000 );
                        Thread.sleep(5000);

                        bj = gamePublicFuntion.gg("zyTu.png", 2);

                        if (bj == 3) {
                            bj1 = 1;
                        } else {
                            LtLog.e("镇妖任务完成,End!");
                            break;
                        }
                    }


                    count++;
                    if (count > 30) {
                        bj = 0;
                        bj1 = 0;
                    }
                }
            }
        }
    }//镇妖

    public void copy(int type, int p) throws Exception {
        int bj = 0, bj1 = 0;
        int cs = 0, cs1 = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------剧情副本bj=" + bj);
            if (bj == 0) {
                result = mFairy.findPic(100, 3, 517, 126, "signOut1.png");
                LtLog.e("判断是否进入副本" + result.sim);
                if (result.sim > 0.7f) {
                    bj = 5;
                    continue;
                }
                gamePublicFuntion.init(2);
                result = mFairy.findPic("mapButton.png");
                if (result.sim > 0.7f) {
                     mFairy.onTap(0.7f, result, "地图",1000);
                    Thread.sleep(2000);
                    mFairy.onTap(837, 236,838,237, "点击地图",1000);
                }
                bj = 1;
                cs1 = 0;
                cs = 0;


            }


             int num= 0;

            if (bj == 1) {

                result = mFairy.findPic("qwe9.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("ranksButton.png");
                     mFairy.onTap(0.6f, result, "点击队伍",1000);
                     mFairy.onTap(0.6f, result, "点击队伍",1000);
                    Thread.sleep(2000);
                    result = mFairy.findPic("create1.png");
                     mFairy.onTap(0.8f, result, "创建队伍",1000);
                    Thread.sleep(3000);
                    result = mFairy.findPic(512, 0, 1274, 539, "close.png");
                     mFairy.onTap(0.7f, result, "关闭窗口",1000);

                    result = mFairy.findPic("mapButton.png");
                     mFairy.onTap(0.7f, result, "地图",1000);

                    Thread.sleep(2000);
                    mFairy.onTap(142, 81,143,82, "当前地图",1000);
                    Thread.sleep(3000);
                    mFairy.onTap(684, 484,685,485, "剧情副本",1000);
                    Thread.sleep(10000);
                }

                bj = 3;
                num = 0;
            }


            if (bj == 3) {

                result = mFairy.findPic(964, 444, 1085, 682, "copyTack.png");
                mFairy.onTap(0.8f, result, "剧情副本",1000);

                result = mFairy.findPic("fb1.png");
                if (result.sim > 0.8f) {
                    if(num==0) {
                        gamePublicFuntion.copyTools(type, p);
                        num=1;
                    }
                    cs1=0;
                }


                if (bj1 == 1) {
                    result = mFairy.findPic("copyjr.png");
                    if (result.sim > 0.8f) {
                         mFairy.onTap(0.8f, result, "进入",1000);
                    }
                    result = mFairy.findPic(438, 81, 608, 315, "end5.png");
                    if (result.sim > 0.7f) {
                        LtLog.e("--------已经完成了，End!");
                        break;
                    }
                    result = mFairy.findPic(100, 3, 517, 126, "signOut1.png");
                    LtLog.e("判断是否进入副本" + result.sim);
                    if (result.sim > 0.7f) {
                        bj = 5;
                    }
                    result = mFairy.findPic("click.png");
                    if (result.sim > 0.9f) {
                         mFairy.onTap(0.9f, result, "点击任意屏幕继续",1000);
                        cs1 = 0;
                    }
                } else {
                    result = mFairy.findPic("copyzd.png");
                     mFairy.onTap(0.8f, result, "便捷组队",1000);

                }

                result = mFairy.findPic("matchingTarget.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("start.png");
                    if (result.sim > 0.8f) {
                        bj = 4;
                        gamePublicFuntion.matchingTarget();//匹配目标

                    } else {
                        LtLog.e("----------副本不匹配，End!");
                        break;
                    }
                }

                cs1++;
                if (cs1 > 20) {
                    bj = 0;
                }
            }

            if (bj == 4) {
                int n = gamePublicFuntion.organizeATeam();

                if (n == 1) {
                    bj1 = 1;
                    bj = 3;
                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic(512, 0, 1274, 539, "close.png");
                         mFairy.onTap(0.7f, result, "关闭窗口",1000);
                    }
                    result = mFairy.findPic("mapButton.png");
                     mFairy.onTap(0.7f, result, "地图",1000);

                    Thread.sleep(2000);
                    mFairy.onTap(142, 81, 143,82,"当前地图",1000);
                    Thread.sleep(3000);
                    mFairy.onTap(684, 484,685,485, "剧情副本",1000);
                    Thread.sleep(8000);

                } else {
                    bj = 0;
                }

            }
            if (bj == 5) {
                cs++;
                if (cs > 80) {
                    result = mFairy.findPic("signOut1.png");
                     mFairy.onTap(0.7f, result, "退出",1000);
                    bj = 0;
                    bj1 = 0;
                }

                result = mFairy.findPic("end2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(970, 260, 971,261,"关闭",1000);
                    Thread.sleep(4000);
                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic("signOut1.png");
                         mFairy.onTap(0.7f, result, "退出",1000);
                        Thread.sleep(2000);
                        mFairy.onTap(753, 432,754,433, "确定",1000);
                        Thread.sleep(2000);
                    }
                    LtLog.e("------- 战斗失败，End!");
                    break;
                }

                result = mFairy.findPic("copyEnd.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(4000);
                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic("signOut1.png");
                        if (result.sim > 0.7f) {
                             mFairy.onTap(0.7f, result, "退出",1000);
                            Thread.sleep(2000);
                            mFairy.onTap(753, 432, 754,433,"确定",1000);
                            Thread.sleep(2000);
                        }
                    }
                    LtLog.e("------- 副本完成，End!");
                    break;
                }


                result = mFairy.findPic(1024, 160, 1091, 439, "copyRight.png");
                 mFairy.onTap(0.7f, result, "右侧副本",1000);

                result = mFairy.findPic(811, 322, 1171, 702, "battle.png");
                 mFairy.onTap(0.7f, result, "战斗",1000);

                result = mFairy.findPic("auto1.png");
                if (result.sim > 0.7f) {
                     mFairy.onTap(0.9f, result, "自动战斗",1000);
                } else {
                     while (mFairy.condit()) {
                        result = mFairy.findPic("auto.png");
                        if (result.sim > 0.7f) {
                            LtLog.e("战斗中....");
                            Thread.sleep(3000);
                        } else {
                            break;
                        }
                    }
                }//自动战斗

                result = mFairy.findPic("click.png");
                if (result.sim > 0.9f) {
                     mFairy.onTap(0.9f, result, "点击任意屏幕继续",1000);
                    cs = 0;
                }
                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.7f, result,1064, 125, 1065,126, "任务",1000);

                if (AtFairyConfig.getOption("xq").equals("1")) {
                    result = mFairy.findPic("xq.png");
                     mFairy.onTap(0.8f, result, "需求",1000);
                }
            }
        }

    }//sevice

    public void qyzjy() throws Exception {
        int bj = 0, bj1 = 0, bj2 = 0;
        int cs = 0, cs1 = 0;
        int count2 = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------青云志-精英任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "qyzjjTu.png",2, 2);
                if (bj == -1) {
                    break;
                }
                cs = 0;
                cs1 = 0;

            }
            if (bj == 3) {

                cs1++;
                if (cs1 > 30) {
                    bj = 0;
                }


                if (bj1 == 1) {
                    result = mFairy.findPic(1004, 291, 1186, 707, "qyzjjTack.png");
                    if (result.sim > 0.8f) {
                         mFairy.onTap(0.8f, result, "青云志精英",1000);
                        bj = 5;
                        Thread.sleep(5000);
                    }


                } else {
                    result = mFairy.findPic("qw.png");
                    if (result.sim > 0.7f) {

                        mFairy.ranSwipe(1009, 517, 1035, 661, 2, 1000,(long)2000); //上滑
                        result = mFairy.findPic(930, 478, 1068, 699, "jjzd.png");
                        if (result.sim > 0.8f) {
                             mFairy.onTap(0.8f, result, "便捷组队",1000);
                            bj = 4;
                        }
                    }
                }


                result = mFairy.findPic("create.png");
                if (result.sim > 0.8f) {
                     mFairy.onTap(0.8f, result, "创建队伍",1000);
                    Thread.sleep(2000);
                    bj2 = 1;
                    bj = 4;
                }


                if (bj2 == 1) {
                    result = mFairy.findPic("change.png");
                    if (result.sim > 0.9f) {
                         mFairy.onTap(0.9f, result, "更改",1000);
                        Thread.sleep(5000);
                        bj2 = 0;
                    }
                }


                gamePublicFuntion.matchingTarget();//匹配目标

            }

            if (bj == 4) {


                int n = gamePublicFuntion.organizeATeam();

                if (n == 1) {

                    bj = gamePublicFuntion.gg("qyzjjTu.png", 2);

                    if (bj == 3) {
                        bj1 = 1;
                    }


                } else {
                    bj = 0;
                }


            }

            if (bj == 5) {

                cs++;
                if (cs > 80) {
                    break;
                }

                result = mFairy.findPic("end2.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(970, 260, 971,261,"关闭",1000);
                    break;
                }

                result = mFairy.findPic("qwe5.png");
                mFairy.onTap(0.8f, result, 762, 426, 763,427,"确定",1000);


                result = mFairy.findPic("challenge2.png");
                 mFairy.onTap(0.8f, result, "挑战",1000);

                result = mFairy.findPic("ok.png");
                 mFairy.onTap(0.8f, result, "确定",1000);


                result = mFairy.findPic("auto1.png");
                 mFairy.onTap(0.7f, result, "自动战斗",1000);

                result = mFairy.findPic("auto.png");
                if (result.sim > 0.7f) {
                    cs = 0;
                    gamePublicFuntion.vtt();
                }
            }
        }
    }//青云志-精英

    public void qyzyx() throws Exception {
        int bj = 0, bj1 = 0, bj2 = 0;
        int cs = 0, cs1 = 0;
        int count2 = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------青云志-英雄任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "qyzyxTu.png", 2, 2);
                if (bj == -1) {
                    break;
                }
                cs = 0;
                cs1 = 0;

            }
            if (bj == 3) {

                cs1++;
                if (cs1 > 30) {
                    bj = 0;
                }


                if (bj1 == 1) {
                    result = mFairy.findPic(1004, 291, 1186, 707, "qyzyxTack1.png");
                    if (result.sim > 0.8f) {
                         mFairy.onTap(0.8f, result, "青云志英雄",1000);
                        bj = 5;
                        Thread.sleep(5000);
                    }


                } else {
                    result = mFairy.findPic("qw.png");
                    if (result.sim > 0.7f) {

                        mFairy.ranSwipe(1009, 517, 1035, 661, 2, 1000,(long)2000); //上滑
                        result = mFairy.findPic(930, 478, 1068, 699, "qyzyxTack.png");
                        if (result.sim > 0.8f) {
                             mFairy.onTap(0.8f, result, "便捷组队",1000);
                            bj = 4;
                        }


                    }

                }


                result = mFairy.findPic("create.png");
                if (result.sim > 0.8f) {
                     mFairy.onTap(0.8f, result, "创建队伍",1000);
                    Thread.sleep(2000);
                    bj2 = 1;
                    bj = 4;
                }


                if (bj2 == 1) {
                    result = mFairy.findPic("change.png");
                    if (result.sim > 0.9f) {
                         mFairy.onTap(0.9f, result, "更改",1000);
                        Thread.sleep(5000);
                        bj2 = 0;
                    }
                }


                gamePublicFuntion.matchingTarget();//匹配目标

            }

            if (bj == 4) {


                int n = gamePublicFuntion.organizeATeam();

                if (n == 1) {

                    bj = gamePublicFuntion.gg("qyzyxTu.png", 2);

                    if (bj == 3) {
                        bj1 = 1;
                    }


                } else {
                    bj = 0;
                }


            }

            if (bj == 5) {

                cs++;
                if (cs > 80) {
                    break;
                }

                result = mFairy.findPic("end2.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(970, 260, 971,261,"关闭",1000);
                    break;
                }

                result = mFairy.findPic("qwe5.png");
                mFairy.onTap(0.8f, result, 762, 426,763,427, "确定",1000);

                result = mFairy.findPic("challenge2.png");
                 mFairy.onTap(0.8f, result, "挑战",1000);

                result = mFairy.findPic("ok.png");
                 mFairy.onTap(0.8f, result, "确定",1000);


                result = mFairy.findPic("auto1.png");
                 mFairy.onTap(0.7f, result, "自动战斗",1000);


                result = mFairy.findPic("auto.png");
                if (result.sim > 0.7f) {
                    cs = 0;
                    gamePublicFuntion.vtt();
                }


            }


        }

    }//青云志-英雄

    public int ftt() throws Exception {
        int count = 51, count1 = 0, count2 = 0, count3 = 0;
         while (mFairy.condit()) {
            if (count % 9 == 0) {
                LtLog.e("跟队中...");
            }
            Thread.sleep(1000);

            if (count > 20) {
                result =  mFairy.findPic(512, 0, 1274, 539,
                        new String[]{"close.png", "tux.png", "cjx.png", "meet.png", "close1.png", "close2.png"});
                 mFairy.onTap(0.7f, result, "关闭窗口",1000);

                result = mFairy.findPic("ranksButton.png");
                if (result.sim > 0.6f) {
                     mFairy.onTap(0.6f, result, "点击队伍",1000);
                     mFairy.onTap(0.6f, result, "点击队伍",1000);
                    Thread.sleep(2000);
                    result = mFairy.findPic("ranks1.png");
                     mFairy.onTap(0.8f, result, "点队伍",1000);

                    result = mFairy.findPic("duizhang.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("signOut.png");
                        if (result.sim > 0.8f) {
                             mFairy.onTap(0.8f, result, "退出",1000);
                            mFairy.onTap(758, 426,759,427, "确定退队",1000);
                        }
                        break;
                    }

                    result = mFairy.findPic("create1.png");
                    if (result.sim > 0.8f) {
                        break;
                    }
                    result = mFairy.findPic("guidui.png");
                     mFairy.onTap(0.8f, result, "归队",1000);
                    result = mFairy.findPic(512, 0, 1274, 539, "close.png");
                     mFairy.onTap(0.7f, result, "关闭",1000);

                }


                //领双倍
                /*if (AtFairyConfig.getOption("cdb").equals("1") {
                    result = mFairy.findPic("hangUp.png");
                    if (result.sim > 0.8f) {
                        if (count1 == 0) {

                             mFairy.onTap(0.8f, result, "挂机");
                            result = mFairy.findPic("hdskUi.png");
                            if (result.sim > 0.8f) {
                                for (int j = 0; j < 15; j++) {
                                    mFairy.onTap(912, 138, "领双");
                                    mFairy.onTap(912, 138, "领双");
                                    Thread.sleep(2000);
                                }
                            }
                            count1 = 1;
                        }
                    }
                }*/


                count = 0;
            }

            count++;

            result = mFairy.findPic("auto1.png");
             mFairy.onTap(0.7f, result, "自动战斗",1000);


            result = mFairy.findPic("gd.png");
            mFairy.onTap(0.8f, result,774, 422,775,423,  "确定",1000);

            result = mFairy.findPic("end2.png");
            mFairy.onTap(0.8f,result,  970, 260, 971,261,"关闭",1000);


            if (AtFairyConfig.getOption("xq").equals("1")) {
                result = mFairy.findPic("xq.png");
                 mFairy.onTap(0.8f, result, "需求",1000);

            }


            result = mFairy.findPic("auto.png");
            if (result.sim > 0.7f) {
                count = 0;
                for (int i = 0; i < 3; i++) {
                    result = mFairy.findPic("rightTip.png");
                     mFairy.onTap(0.8f, result, "Tip",1000);

                    result = mFairy.findPic("task1.png");
                    mFairy.onTap(0.7f, result, 1064, 125,1065,126, "任务",1000);
                }


                if (AtFairyConfig.getOption("cd").equals("1") ){
                    if (count3 == 0) {
                        for (int i = 0; i < 5; i++) {
                            result = mFairy.findPic("catch5.png");
                             mFairy.onTap(0.7f, result, "展开",1000);
                            Thread.sleep(1000);
                            result = mFairy.findPic("gj.png");
                             mFairy.onTap(0.7f, result, "挂机图标",1000);
                        }
                        Thread.sleep(2000);
                        result = mFairy.findPic("hdskUi.png");
                        if (result.sim > 0.8f) {
                            for (int i = 0; i < 2; i++) {
                                mFairy.onTap(912, 138, 913,139,"领双",1000);
                                Thread.sleep(2000);
                            }
                        }

                        mFairy.onTap(1099, 92,1100,93, "关闭",1000);
                        count3 = 1;
                    }
                } else if (AtFairyConfig.getOption("cd").equals("5")) {
                    gamePublicFuntion.zyTool("five.png", "one.png");
                } else if (AtFairyConfig.getOption("cd").equals("6")) {
                    gamePublicFuntion.zyTool("six.png", "one.png");
                } else if (AtFairyConfig.getOption("cd").equals("7")){
                    gamePublicFuntion.zyTool("seven.png", "one.png");
                }


                 while (mFairy.condit()) {
                    result = mFairy.findPic("auto.png");
                    if (result.sim > 0.7f) {
                        result = mFairy.findPic(512, 0, 1274, 539, "close.png");
                         mFairy.onTap(0.7f, result, "关闭",1000);
                        Thread.sleep(2000);
                    } else {
                        break;
                    }
                }
            }

            //零点结束
            if (AtFairyConfig.getOption("gdldjs").equals("1")) {
                int hour = mFairy.dateHour();
                if (hour == 0) {
                    break;
                }
            }

            //五点结束
            if (AtFairyConfig.getOption("gdwdjs").equals("1")) {
                int hour = mFairy.dateHour();
                if (hour == 5) {
                    break;
                }
            }
        }
        return 8401;
    }//跟队
    
}