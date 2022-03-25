package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.TaskContent;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class TeamTask extends TaskContent {
    AtFairyImpl mFairy;
    AtFairyImpl mFairy1;
    FindResult result;
    GameUtil gameUtil;
    List<String> list = new ArrayList<>();
    OtherGame otherGame;

    public TeamTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gameUtil = new GameUtil(mFairy);
        mFairy1 = ypFairy;
        otherGame = new OtherGame(mFairy);
    }

    public void inOperation() throws Exception {
        result = mFairy.findPic("Over drawing.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("过图中"));
            mFairy.initMatTime();
            err = 0;
        }
        gameUtil.zh();
        gameUtil.gdFBTeam();
    }

    public void content_2() throws Exception {
        if (overtime(8, 99)) return;
        result = mFairy.findPic("taskbar.png");
        mFairy.onTap(0.85f, result, "切换到任务栏", Sleep);

        mFairy.taskSlid(err, new int[]{0, 2, 4}, 2, 88, 400, 88, 216, 1500, 1000);
    }

    private int yterr = 0;

    //一条龙带队
    public void aDragon() throws Exception {
        if (AtFairyConfig.getOption("yc10").equals("1")) {
            for (int i = 0; i < 100; i++) {
                LtLog.e(mFairy.getLineInfo("延迟10分钟"));
                Thread.sleep(6000);
            }
        }

        new TeamTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("aDragon.png", 2);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(3);
                } else if (ret == 2) {
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                result = mFairy.findPic(3, 184, 246, 447, new String[]{"Leftone.png", "Leftone1.png", "Leftone2.png", "Leftone3.png", "Leftone4.png"});
                mFairy.onTap(0.7f, result, "左侧一条龙", Sleep);
                if (result.sim > 0.7f) {
                    gameUtil.callToFollow();
                    setTaskName(7);
                    return;
                }
                super.content_2();
            }

            public void content_3() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(927, 177, 1276, 708, "Convenientteam.png");
                mFairy.onTap(0.8f, result, "右侧便捷组队", Sleep);

                result = mFairy.findPic("Openteam.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Openteam1.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Convenientinterface.png");
                mFairy.onTap(0.8f, result, 885, 624, 905, 638, "便捷组队界面,是一个人开始创建队伍匹配", 2000);

                result = mFairy.findPic("teaminterface.png");
                mFairy.onTap(0.8f, result, 1163, 167, 1164, 168, "队伍界面已经有队伍了开始切换目标", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(4);
                }
            }

            public void content_4() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(252, 84, 705, 120, "target.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("目标为一条龙进行下一步"));
                    result = mFairy.findPic("zdpp.png");
                    mFairy.onTap(0.8f, result, "自动匹配", Sleep);
                    gameUtil.close(1);
                    setTaskName(5);
                    return;
                } else {
                    result = mFairy.findPic("Cancelingmatch.png");
                    mFairy.onTap(0.8f, result, "需要切换目标,取消匹配", Sleep);

                    result = mFairy.findPic("zd1.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("调整目标界面"));
                        result = mFairy.findPic(334, 132, 639, 545, "ytl.png");
                        mFairy.onTap(0.8f, result, "目标为一条龙进行下一步", Sleep);
                        if (AtFairyConfig.getOption("dxr").equals("1")) {
                            mFairy.onTap(0.8f, result, 379, 559, 394, 568, "带新人", Sleep);
                        }
                        if (result.sim > 0.8f) {
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.onTap(0.8f, result, 786, 561, 787, 562, "确定", 2000);
                            result = mFairy.findPic("zdpp.png");
                            mFairy.onTap(0.8f, result, "自动匹配", Sleep);
                            gameUtil.close(1);
                            setTaskName(5);
                            return;
                        } else {
                            mFairy.taskSlid(err, new int[]{4, 6, 8}, 3, 452, 490, 471, 182, 200, 1000);
                            Thread.sleep(2000);
                        }
                    } else {
                        result = mFairy.findPic("teaminterface.png");
                        mFairy.onTap(0.8f, result, 1163, 167, 1164, 168, "目标不对，切换目标", Sleep);
                        mFairy.onTap(0.8f, result, 165, 116, 166, 117, "目标不对，切换目标", 2000);
                    }
                }
            }

            public void content_5() throws Exception {
                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);
                gameUtil.apply();
                int mannum = 0;
                result = mFairy.findPic(196, 197, 242, 253, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }

                result = mFairy.findPic(195, 257, 242, 315, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }

                result = mFairy.findPic(194, 315, 241, 374, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }
                result = mFairy.findPic(194, 376, 243, 432, "Numberpeople.png");
                if (result.sim > 0.8f) {
                    mannum++;
                    LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                }
                if (mannum > 1) {
                    gameUtil.callToFollow();
                    if (gameUtil.kicking("看人") > 0) {
                        LtLog.e(mFairy.getLineInfo("等待队员过来"));
                        Thread.sleep(10000);
                    }
                    setTaskName(6);
                    return;
                }

                if (timekeep(0, 600000, "超过10分钟没组到人")) {
                    LtLog.e(mFairy.getLineInfo("--------超过10分钟没组到人"));
                    setTaskName(0);
                    return;
                }

                if (timekeep(1, 120000, "2分钟招募一下")) {
                    LtLog.e(mFairy.getLineInfo("--------2分钟招募一下"));
                    gameUtil.recruit();
                }

                LtLog.e(mFairy.getLineInfo("等待人进队"));
                Thread.sleep(3000);
            }

            public void content_6() throws Exception {
                int ret = gameUtil.mission("aDragon.png", 2);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(7);
                } else if (ret == 2) {
                    setTaskName(7);
                }
                gameUtil.close(1);
            }

            boolean sb = true;

            public void content_7() throws Exception {
                if (overtime(10, 6)) return;
                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);

                result = mFairy.findPic(new String[]{"Giveuptask.png", "Giveuptask1.png"});
                mFairy.onTap(0.8f, result, "任务失败放弃", Sleep);
                mFairy.onTap(0.8f, result, 796, 424, 797, 425, "任务失败放弃", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                if (result.sim > 0.85f) {
                    LtLog.e(mFairy.getLineInfo("--------副本中"));
                    if (!AtFairyConfig.getOption("ls").equals("") && sb) {
                        gameUtil.collarDouble();
                        sb = false;
                    }
                    gameUtil.apply();
                    mFairy.initMatTime();
                    err = 0;
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "副本中开启挂机", Sleep);
                }

                result = mFairy.findPic(927, 177, 1276, 708, new String[]{"Getinto.png", "Pickupdragon.png"});
                mFairy.onTap(0.8f, result, "右侧一条龙", Sleep);

                result = mFairy.findPic(308, 499, 984, 564, "manNo.png");
                //LtLog.e(mFairy.getLineInfo(0.1f,result,"人没过来"));
                if (picCount(0.7f, result, "人没过来") > 7) {
                    gameUtil.kicking("踢人");
                } else {
                    if (result.sim > 0.7f) {
                        gameUtil.callToFollow();
                        Thread.sleep(15000);
                    }
                }
                result = mFairy.findPic(308, 499, 984, 564, "manNONO.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("人不够"));
                    setTaskName(3);
                    return;
                }


                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 5) {
                    result = mFairy.findPic("taskbar.png");
                    mFairy.onTap(0.8f, result, "任务栏", Sleep);
                    result = mFairy.findPic(3, 184, 246, 447, new String[]{"Leftone.png", "Leftone1.png", "Leftone2.png", "Leftone3.png", "Leftone4.png"});
                    mFairy.onTap(0.7f, result, "左侧一条龙", Sleep);
                }

                result = mFairy.findPic("Endround.png");
                mFairy.onTap(0.8f, result, 489, 418, 490, 419, "一轮一条龙完成", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(6);
                    return;
                }
            }

        }.taskContent(mFairy, "一条龙带队任务中");
    }

    //一条龙跟队
    public void aDragon1() throws Exception {
        new TeamTask(mFairy) {

            @Override
            public void create() throws Exception {

            }

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic("Openteam.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Openteam1.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic(1, 190, 239, 559, "Cancelfollowing.png");
                if (result.sim > 0.75f) {
                    LtLog.e(mFairy.getLineInfo("左侧取消跟随"));
                    setTaskName(3);
                }
                result = mFairy.findPic(1, 190, 239, 559, "follow.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("左侧跟随"));
                    setTaskName(3);
                }

                result = mFairy.findPic("It'sthecaptain.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("是队长，要退队"));
                    gameUtil.retire();
                    setTaskName(2);
                }

                result = mFairy.findPic("Convenientinterface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("便捷组队界面,是一个人开始匹配"));
                    setTaskName(2);
                }
                result = mFairy.findPic("Convenientleft.png");
                mFairy.onTap(0.8f, result, "左侧便捷组队,一个人", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                result = mFairy.findPic("Convenientinterface.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("gdytl.png");
                    mFairy.onTap(0.8f, result, "跟队一条龙", Sleep);
                }
                result = mFairy.findPic("Openteam.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Openteam1.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("automatic1.png");
                mFairy.onTap(0.8f, result, "自动匹配", Sleep);

                result = mFairy.findPic(995, 75, 1152, 591, "shenqing.png");
                mFairy.onTap(0.8f, result, "申请", Sleep);

                result = mFairy.findPic("Match.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "匹配中"));

                result = mFairy.findPic("teaminterface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("队伍界面已经有队伍了"));
                    gameUtil.close(0);
                    setTaskName(3);
                    return;
                }
                if (timekeep(0, 600000, "跟队超过10分钟没进入队伍")) {
                    LtLog.e(mFairy.getLineInfo("--------跟队超过10分钟没进入队伍"));
                    setTaskName(0);
                    return;
                }
              /*  result = mFairy.findPic("Convenientinterface.png");
                mFairy.onTap(0.8f, result, 236, 199,237, 200,"便捷组队左侧一条龙", 2000);*/
            }

            boolean sb = true;

            public void content_3() throws Exception {
                if (timekeep(0, 600000, "跟队超过10分钟队长没进入副本")) {
                    LtLog.e(mFairy.getLineInfo("--------跟队超过10分钟队长没进入副本"));
                    gameUtil.retire();
                    int ret = gameUtil.mission("aDragon.png", 2);
                    if (ret == 0) {
                        setTaskEnd();
                        return;
                    }
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                if (result.sim > 0.8f) {
                    timekeepInit("跟队超过10分钟队长没进入副本");
                    LtLog.e(mFairy.getLineInfo("--------副本中"));
                    if (!AtFairyConfig.getOption("ls").equals("") && sb) {
                        gameUtil.collarDouble();
                        sb = false;
                    }
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "副本中开启挂机", Sleep);
                }
                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);
            }
        }.taskContent(mFairy, "一条龙跟队");
    }


}
