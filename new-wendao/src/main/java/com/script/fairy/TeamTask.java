package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class TeamTask {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    Util util;
    TimingActivity timingActivity;

    public TeamTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        util = new Util(mFairy);
    }


    //修行
    public void practice() throws Exception {
        long time = 0;
        String strTarget = "target_xx.png";
        String strParent = "xiulian1.png";
        String strSub = "xiuxing.png";
        int bj = 0;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("修行中bj=" + bj));
            if (bj == 0) {
                util.close();
                bj = 1;
            }
            if (bj == 1) {
                int ret = util.findTask("日常", "practice1.png", "看任务");
                if (ret == 1) {
                    bj = 3;
                } else if (ret == 0) {
                    return;
                }
                if (AtFairyConfig.getOption("zdrw").equals("1")) {
                    util.teamRanks("队长");
                    int  teamRet =util.leadFollowTeam("带队", strTarget, strParent, strSub);
                    if (teamRet==1){
                        bj = 2;
                    }
                } else {
                    util.teamRanks("队员");
                    int  teamRet=  util.leadFollowTeam("跟队", strTarget, strParent, strSub);
                    if (teamRet==1){
                        time=System.currentTimeMillis();
                        bj=4;
                    }
                }
            }
            if (bj == 2) {
                result = mFairy.findPic(1010, 165, 1278, 512, new String[]{"xiulian.png"});
                mFairy.onTap(0.7f, result, "右侧任务栏修炼", 1000);
                if (result.sim > 0.7f) {
                    bj = 3;
                } else {
                    int ret = util.findTask("日常", "practice1.png", "前往");
                    if (ret == 1) {
                        Thread.sleep(5000);
                        bj = 3;
                    } else if (ret == 0) {
                        return;
                    }
                }

            }
            if (bj == 3) {
                result = mFairy.findPic(766, 18, 1265, 702, new String[]{"practice.png"});
                mFairy.onTap(0.8f, result, "修行接到任务", 2000);

                result = mFairy.findPic(766, 18, 1265, 702, new String[]{"min3r.png", "djbg.png","min3r2.png","min3r5.png","min3r4.png","min3r3.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "人不满从新开始组队，或有人等级不够"));
                if (result.sim > 0.8f) {
                    bj = 0;
                } else {
                    result = mFairy.findPic(376, 210, 916, 514, "qx.png");
                    mFairy.onTap(0.8f, result, "取消", 1000);

                    result = mFairy.findPic(974, 91, 1280, 233, "right_task1.png");
                    mFairy.onTap(0.9f, result, "右侧任务暗", 1000);


                    result = mFairy.findPic("someone_applied.png");
                    mFairy.onTap(0.8f, result, "有人申请", 1000);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(587, 63, 649, 108, "point.png");
                        mFairy.onTap(0.8f, result, 548, 100, 549, 101, "点击申请表", 1000);
                        if (result.sim > 0.8f) {
                            for (int i = 0; i < 3; i++) {
                                result = mFairy.findPic(508, 217, 1096, 304, "need.png");
                                mFairy.onTap(0.8f, result, "点击对勾", 1000);
                            }
                        }
                        util.close();
                    }

                    long dazeTime = mFairy.mMatTime(24,537,20,14, 0.9f);
                    if (dazeTime >= 20) {
                        mFairy.initMatTime();
                        result = mFairy.findPic(1010, 165, 1278, 512, new String[]{"xiulian.png"});
                        mFairy.onTap(0.7f, result, "右侧任务栏修行", 1000);
                        if (result.sim > 0.7f) {
                        } else {
                            bj = 2;
                        }

                    }

                    result = mFairy.findPic("combat.png");
                    LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                    if (result.sim > 0.8f) {
                        Thread.sleep(5000);
                        mFairy.initMatTime();
                    }
                }
            }
            if (bj == 4) {
                Thread.sleep(5000);
                result = mFairy.findPic("combat.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                if (result.sim > 0.8f) {
                    Thread.sleep(5000);
                    time = System.currentTimeMillis();

                } else {
                    if (System.currentTimeMillis() - time > 180000) {
                        util.teamRanks("退队");
                        bj = 0;
                    }
                }
            }
        }
    }

    //除暴
    public void eliminate() throws Exception {
        long time = 0;
        String strTarget = "target_cb.png";
        String strParent = "cbtask1.png";
        String strSub = "";
        int bj = 0;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("除暴中bj=" + bj));
            if (bj == 0) {
                util.close();
                bj = 1;
            }
            if (bj == 1) {
                int ret = util.findTask("日常", "cbtask.png", "看任务");
                if (ret == 1) {
                    util.close();
                } else if (ret == 0) {
                    return;
                }
                if (AtFairyConfig.getOption("zdrw").equals("1")) {
                    util.teamRanks("队长");
                    int  teamRet =util.leadFollowTeam("带队", strTarget, strParent, strSub);
                    if (teamRet==1){
                        bj = 2;
                    }
                } else {
                    util.teamRanks("队员");
                    int  teamRet=  util.leadFollowTeam("跟队", strTarget, strParent, strSub);
                    if (teamRet==1){
                        time=System.currentTimeMillis();
                        bj=4;
                    }
                }
            }
            if (bj == 2) {
                result = mFairy.findPic(1010, 165, 1278, 512, new String[]{"rwlwmcb.png"});
                mFairy.onTap(0.7f, result, "右侧任务栏除暴", 1000);
                if (result.sim > 0.7f) {
                    bj = 3;
                } else {
                    int ret = util.findTask("日常", "cbtask.png", "前往");
                    if (ret == 1) {
                        Thread.sleep(5000);
                        bj = 3;
                    } else if (ret == 0) {
                        return;
                    }
                }
            }
            if (bj == 3) {
                result = mFairy.findPic(766, 18, 1265, 702, new String[]{"cbxx.png"});
                mFairy.onTap(0.8f, result, "除暴接到任务", 2000);

                result = mFairy.findPic(766, 18, 1265, 702, new String[]{"min3r.png", "djbg.png","min3r2.png","min3r5.png","min3r4.png","min3r3.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "人不满从新开始组队，或有人等级不够"));
                if (result.sim > 0.8f) {
                    bj = 0;
                } else {
                    result = mFairy.findPic(376, 210, 916, 514, "qx.png");
                    mFairy.onTap(0.8f, result, "取消", 1000);

                    result = mFairy.findPic(974, 91, 1280, 233, "right_task1.png");
                    mFairy.onTap(0.9f, result, "右侧任务暗", 1000);


                    result = mFairy.findPic("someone_applied.png");
                    mFairy.onTap(0.8f, result, "有人申请", 1000);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(587, 63, 649, 108, "point.png");
                        mFairy.onTap(0.8f, result, 548, 100, 549, 101, "点击申请表", 1000);
                        if (result.sim > 0.8f) {
                            for (int i = 0; i < 3; i++) {
                                result = mFairy.findPic(508, 217, 1096, 304, "need.png");
                                mFairy.onTap(0.8f, result, "点击对勾", 1000);
                            }
                        }
                        util.close();
                    }


                    long dazeTime = mFairy.mMatTime(24,537,20,14, 0.9f);
                    if (dazeTime >= 20) {
                        mFairy.initMatTime();
                        result = mFairy.findPic(1010, 165, 1278, 512, new String[]{"rwlwmcb.png"});
                        mFairy.onTap(0.7f, result, "右侧任务栏除暴", 1000);
                        if (result.sim > 0.7f) {
                        } else {
                            bj = 2;
                        }

                    }

                    result = mFairy.findPic("combat.png");
                    LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                    if (result.sim > 0.8f) {
                        Thread.sleep(5000);
                        mFairy.initMatTime();
                    }
                }
            }
            if (bj == 4) {
                Thread.sleep(5000);
                result = mFairy.findPic("combat.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                if (result.sim > 0.8f) {
                    Thread.sleep(5000);
                    time = System.currentTimeMillis();

                } else {
                    if (System.currentTimeMillis() - time > 180000) {
                        util.teamRanks("退队");
                        bj = 0;
                    }
                }
            }
        }
    }

    //同甘共苦
    public void tggk() throws Exception {
        long time = 0;
        int bj = 0;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("同甘共苦中bj=" + bj));
            if (bj == 0) {
                util.close();
                bj = 1;
            }
            if (bj == 1) {
                int ret = util.findTask("其他", "tggk.png", "看任务");
                if (ret == 1) {
                    util.close();
                } else if (ret == 0) {
                    return;
                }
                if (AtFairyConfig.getOption("zdrw").equals("1")) {
                    util.teamRanks("队长");
                    bj = 2;
                } else {
                    return;
                }
            }
            if (bj == 2) {
                result = mFairy.findPic(1010, 165, 1278, 512, new String[]{"tggk2.png"});
                mFairy.onTap(0.7f, result, "右侧任务栏同甘共苦", 1000);
                if (result.sim > 0.7f) {
                    Thread.sleep(5000);
                    bj = 3;
                } else {
                    int ret = util.findTask("其他", "tggk.png", "前往");
                    if (ret == 1) {
                        bj = 3;
                    } else if (ret == 0) {
                        return;
                    }
                }
            }
            if (bj == 3) {

                result = mFairy.findPic(766, 18, 1265, 702, new String[]{"kybmd.png", "tggk1.png", "gn.png"});
                mFairy.onTap(0.8f, result, "同甘共苦对话", 2000);
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                }

                //一个人恐怕难以办到  必须所有人互为好友
                result = mFairy.findPic(766, 18, 1265, 702, new String[]{"min3r.png", "djbg.png","min3r2.png","min3r5.png","min3r4.png","min3r3.png", "hwhy.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "人不满从新开始组队，或有人等级不够"));
                if (result.sim > 0.8f) {
                    return;
                } else {
                    result = mFairy.findPic(376, 210, 916, 514, "qx.png");
                    mFairy.onTap(0.8f, result, "取消", 1000);

                    result = mFairy.findPic(974, 91, 1280, 233, "right_task1.png");
                    mFairy.onTap(0.9f, result, "右侧任务暗", 1000);


                    result = mFairy.findPic("someone_applied.png");
                    mFairy.onTap(0.8f, result, "有人申请", 1000);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(587, 63, 649, 108, "point.png");
                        mFairy.onTap(0.8f, result, 548, 100, 549, 101, "点击申请表", 1000);
                        if (result.sim > 0.8f) {
                            for (int i = 0; i < 3; i++) {
                                result = mFairy.findPic(508, 217, 1096, 304, "need.png");
                                mFairy.onTap(0.8f, result, "点击对勾", 1000);
                            }
                        }
                        util.close();
                    }


                    long dazeTime = mFairy.mMatTime(24,537,20,14, 0.9f);
                    if (dazeTime >= 20) {
                        mFairy.initMatTime();
                        result = mFairy.findPic(1010, 165, 1278, 512, new String[]{"tggk2.png"});
                        mFairy.onTap(0.7f, result, "右侧任务栏同甘共苦", 1000);
                        if (result.sim > 0.8f) {
                        } else {
                            bj = 2;
                        }

                    }

                    result = mFairy.findPic("combat.png");
                    LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                    if (result.sim > 0.8f) {
                        Thread.sleep(5000);
                        mFairy.initMatTime();
                    }
                }
            }
        }
    }


    //刷道设置
    public void sdSetUp() throws Exception {
        mFairy.condit();
        util.close();
        result = mFairy.findPic(210, 21, 703, 98, "sdtask.png");
        mFairy.onTap(0.8f, result, "刷道", 1000);

        result = mFairy.findPic(553, 5, 725, 60, "Tshuadao.png");
        mFairy.onTap(0.8f, result, 1083, 123, 1101, 150, "刷道界面切换到刷道", 1000);
        if (result.sim > 0.8f) {
            if (AtFairyConfig.getOption("zqhm").equals("1")) {
                result = mFairy.findPic(218, 548, 338, 621, "unkaiqi.png");
                mFairy.onTap(0.8f, result, "开启紫气鸿蒙", 1000);
            } else {
                result = mFairy.findPic(218, 548, 338, 621, "unkaiqi.png");
                if (result.sim < 0.8f) {
                    mFairy.onTap(271, 580, 304, 592, "关闭紫气鸿蒙", 1000);
                }
            }
            if (AtFairyConfig.getOption("rysd").equals("1")) {
                result = mFairy.findPic(657, 555, 782, 617, "unkaiqi.png");
                mFairy.onTap(0.8f, result, "开启如意刷道", 1000);
            } else {
                result = mFairy.findPic(657, 555, 782, 617, "unkaiqi.png");
                if (result.sim < 0.8f) {
                    mFairy.onTap(699, 582, 732, 594, "关闭如意刷道", 1000);
                }
            }
            if (AtFairyConfig.getOption("jjrll").equals("1")) {
                result = mFairy.findPic(232, 627, 340, 687, "unkaiqi.png");
                mFairy.onTap(0.8f, result, "开启急急如律令", 1000);
            } else {
                result = mFairy.findPic(232, 627, 340, 687, "unkaiqi.png");
                if (result.sim < 0.8f) {
                    mFairy.onTap(263, 648, 299, 664, "关闭急急如律令", 1000);
                }
            }
            if (AtFairyConfig.getOption("cfs").equals("1")) {
                result = mFairy.findPic(660, 627, 766, 684, "unkaiqi.png");
                mFairy.onTap(0.8f, result, "开启宠风散", 1000);
            } else {
                result = mFairy.findPic(660, 627, 766, 684, "unkaiqi.png");
                if (result.sim < 0.8f) {
                    mFairy.onTap(691, 646, 736, 663, "关闭宠风散", 1000);
                }
            }
            util.close();
        }
    }


}
