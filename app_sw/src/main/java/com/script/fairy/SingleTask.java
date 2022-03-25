package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/13.
 */

public class SingleTask {

    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    FindResult result2;
    FindResult result3;
    FindResult result4;
    CommonFunction commonFunction;
    GamePublicFunction gamePublicFunction;
    MatTime matTime;
    int bj = 0, bj_1;
    int cs_1 = 0;
    int ret;
    int numcolor, numcolor1;
    int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;

    public SingleTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        commonFunction = new CommonFunction(mFairy);
        gamePublicFunction = new GamePublicFunction(mFairy);
        matTime = new MatTime(mFairy);
    }

    //主线用的师门
    public int ShimenTask1() throws Exception {
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("师门中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }
            if (bj == 1) {
                ret = gamePublicFunction.mission("smTask.png");
                if (ret == 0) {
                    return 0;
                } else if (ret == 1) {
                    bj = 2;
                }
            }
            if (bj == 2) {
                result = mFairy.findPic2(1014, 183, 1093, 534, commonFunction.setImg("rwlsm.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "任务栏师门"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 10) {
                        bj = 0;
                    }
                }
                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_1 = 0;
                }

                for (int i = 0; i < 5; i++) {
                    result = mFairy.findPic(207, 633, 1213, 717, "duihua4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "对话按钮", 1000);
                    } else {
                        break;
                    }
                }


                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(798, 394, 925, 669, commonFunction.setImg("Bsm1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "师门按钮"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(1028, 209, 1194, 463, commonFunction.setImg("Bjoinzd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "进入战斗"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(806, 348, 965, 522, commonFunction.setImg("Bwyzrw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "我要做任务"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = commonFunction.FindManyPic(865, 577, 1090, 647, new String[]{"Bshop.png", "Bsdshop.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购买"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8) {
                    gamePublicFunction.init();
                }

                result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                }


                result = mFairy.findPic2(1118, 217, 1174, 349, commonFunction.setImg("Stalls.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "右侧逛摊"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(245, 121, 482, 578, commonFunction.setImg("gtneed.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "需求"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic2(638, 600, 781, 657, commonFunction.setImg("Byjsj.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.1f, "切换到摊位列表"));
                        commonFunction.RndCompare(0.8f, 302, 95, result, commonFunction.getImg());

                        result1 = mFairy.findPic2(505, 98, 860, 574, commonFunction.setImg("need.png"));
                        LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "物品需求"));
                        commonFunction.RndCompare(0.8f, result1.x + 139, result1.y + 18, result1, commonFunction.getImg());
                        commonFunction.RndCompare(0.8f, 785, 636, result1, commonFunction.getImg());

                        result = mFairy.findPic2(439, 124, 488, 578, commonFunction.setImg("nowshop.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购物当前栏"));
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic2(result.x - 60, result.y - 10, result.x + 5, result.y + 40, commonFunction.setImg("gtneed.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "摊位列表需求"));
                        }

                        if (result.sim > 0.8f && result1.sim < 0.8f) {
                            commonFunction.RndCompare(992, 636, "没有需求物品下一页");
                            Thread.sleep(1500);
                        } else if (result.sim < 0.8f && result1.sim < 0.8f) {
                            break;
                        }

                        result = mFairy.findPic2(905, 46, 1059, 100, commonFunction.setImg("Bshuaxin.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "刷新"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    }
                }
            }
        }
        return 0;
    }

    //师门
    public void ShimenTask() throws Exception {
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("师门中bj=" + bj));
            if (bj == 0) {

                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }
            if (bj == 1) {
                bj = 2;
            }
            if (bj == 2) {
                result = mFairy.findPic2(1014, 183, 1093, 534, commonFunction.setImg("rwlsm.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "任务栏师门"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 10) {
                        break;
                    }
                }
                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_1 = 0;
                }

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(798, 394, 925, 669, commonFunction.setImg("Bsm1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "师门按钮"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(1028, 209, 1194, 463, commonFunction.setImg("Bjoinzd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "进入战斗"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(806, 348, 965, 522, commonFunction.setImg("Bwyzrw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "我要做任务"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = commonFunction.FindManyPic(865, 577, 1090, 647, new String[]{"Bshop.png", "Bsdshop.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购买"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8) {
                    gamePublicFunction.init();
                }

                result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                }

                result = mFairy.findPic2(1118, 217, 1174, 349, commonFunction.setImg("Stalls.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "右侧逛摊"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(245, 121, 482, 578, commonFunction.setImg("gtneed.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "需求"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic2(638, 600, 781, 657, commonFunction.setImg("Byjsj.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.1f, "切换到摊位列表"));
                        commonFunction.RndCompare(0.8f, 302, 95, result, commonFunction.getImg());

                        result1 = mFairy.findPic2(505, 98, 860, 574, commonFunction.setImg("need.png"));
                        LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "物品需求"));
                        commonFunction.RndCompare(0.8f, result1.x + 139, result1.y + 18, result1, commonFunction.getImg());
                        commonFunction.RndCompare(0.8f, 785, 636, result1, commonFunction.getImg());

                        result = mFairy.findPic2(439, 124, 488, 578, commonFunction.setImg("nowshop.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购物当前栏"));
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic2(result.x - 60, result.y - 10, result.x + 5, result.y + 40, commonFunction.setImg("gtneed.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "摊位列表需求"));
                        }

                        if (result.sim > 0.8f && result1.sim < 0.8f) {
                            commonFunction.RndCompare(992, 636, "没有需求物品下一页");
                            Thread.sleep(1500);
                        } else if (result.sim < 0.8f && result1.sim < 0.8f) {
                            break;
                        }

                        result = mFairy.findPic2(905, 46, 1059, 100, commonFunction.setImg("Bshuaxin.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "刷新"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    }
                }
            }
        }
    }

    //师门2
    public void ShimenTask2() throws Exception {
        long fdtime = 0;
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("师门中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }
            if (bj == 1) {
                bj = 2;
            }
            if (bj == 2) {
                if (fdtime > 2) {
                    result = mFairy.findPic2(1014, 183, 1093, 534, commonFunction.setImg("rwlsm.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "任务栏师门"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    if (result.sim > 0.8f) {
                        js_1 = 0;
                    } else {
                        js_1++;
                        if (js_1 > 10) {
                            break;
                        }
                    }
                }
                fdtime = matTime.mMatTime(120, 60, 56, 18, 0.9f);

                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    matTime.resetTime();
                    js_1 = 0;
                }

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(806, 365, 945, 563, commonFunction.setImg("Bswhq.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "送我回去"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(798, 394, 925, 669, commonFunction.setImg("Bsm1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "师门按钮"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(1028, 209, 1194, 463, commonFunction.setImg("Bjoinzd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "进入战斗"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(806, 348, 965, 522, commonFunction.setImg("Bwyzrw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "我要做任务"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = commonFunction.FindManyPic(865, 577, 1090, 647, new String[]{"Bshop.png", "Bsdshop.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购买"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8) {
                    gamePublicFunction.init1();
                }

                result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                }

                result = mFairy.findPic2(1118, 217, 1174, 349, commonFunction.setImg("Stalls.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "右侧逛摊"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(245, 121, 482, 578, commonFunction.setImg("gtneed.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "需求"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic2(638, 600, 781, 657, commonFunction.setImg("Byjsj.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.1f, "切换到摊位列表"));
                        commonFunction.RndCompare(0.8f, 302, 95, result, commonFunction.getImg());

                        result1 = mFairy.findPic2(505, 98, 860, 574, commonFunction.setImg("need.png"));
                        LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "物品需求"));
                        commonFunction.RndCompare(0.8f, result1.x + 139, result1.y + 18, result1, commonFunction.getImg());
                        commonFunction.RndCompare(0.8f, 785, 636, result1, commonFunction.getImg());

                        result = mFairy.findPic2(439, 124, 488, 578, commonFunction.setImg("nowshop.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购物当前栏"));
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic2(result.x - 60, result.y - 10, result.x + 5, result.y + 40, commonFunction.setImg("gtneed.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "摊位列表需求"));
                        }

                        if (result.sim > 0.8f && result1.sim < 0.8f) {
                            commonFunction.RndCompare(992, 636, "没有需求物品下一页");
                            Thread.sleep(1500);
                        } else if (result.sim < 0.8f && result1.sim < 0.8f) {
                            break;
                        }

                        result = mFairy.findPic2(905, 46, 1059, 100, commonFunction.setImg("Bshuaxin.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "刷新"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    }
                }
            }
        }
    }

    //宝图
    public void Baotu() throws Exception {
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("宝图中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }
            if (bj == 1) {
                result = mFairy.findPic2(52, 78, 1030, 572, commonFunction.setImg("zhanggui.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.1f, "掌柜的"));
                commonFunction.RndCompare(0.7f, result.x + 23, result.y + 114, result, commonFunction.getImg());

                result = commonFunction.FindManyPic(798, 389, 983, 705, new String[]{"dtcbt.png", "dtcbt1.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "打听藏宝图"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    bj = 2;
                }
                js_2++;
                if (js_2 > 10) {
                    bj = 2;
                }
            }
            if (bj == 2) {

                result = mFairy.findPic2(1014, 183, 1093, 534, commonFunction.setImg("rwbaotu.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "任务栏宝图"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 10) {
                        break;
                    }
                }

                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_1 = 0;
                }

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(796, 315, 952, 714, commonFunction.setImg("lsbt.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "蓝色宝图"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(795, 386, 957, 590, commonFunction.setImg("yaonie.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "关闭妖孽"));
                commonFunction.RndCompare(0.8f, 1246, 60, result, commonFunction.getImg());


                result = commonFunction.FindManyPic(865, 577, 1090, 647, new String[]{"Bshop.png", "Bsdshop.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购买"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8) {
                    gamePublicFunction.init();
                }

                result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                }

                result = mFairy.findPic2(1118, 217, 1174, 349, commonFunction.setImg("Stalls.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "右侧逛摊"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(245, 121, 482, 578, commonFunction.setImg("gtneed.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "需求"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic2(638, 600, 781, 657, commonFunction.setImg("Byjsj.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.1f, "切换到摊位列表"));
                        commonFunction.RndCompare(0.8f, 302, 95, result, commonFunction.getImg());

                        result1 = mFairy.findPic2(505, 98, 860, 574, commonFunction.setImg("need.png"));
                        LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "物品需求"));
                        commonFunction.RndCompare(0.8f, result1.x + 139, result1.y + 18, result1, commonFunction.getImg());
                        commonFunction.RndCompare(0.8f, 785, 636, result1, commonFunction.getImg());

                        result = mFairy.findPic2(439, 124, 488, 578, commonFunction.setImg("nowshop.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购物当前栏"));
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic2(result.x - 60, result.y - 10, result.x + 5, result.y + 40, commonFunction.setImg("gtneed.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "摊位列表需求"));
                        }

                        if (result.sim > 0.8f && result1.sim < 0.8f) {
                            commonFunction.RndCompare(992, 636, "没有需求物品下一页");
                            Thread.sleep(1500);
                        } else if (result.sim < 0.8f && result1.sim < 0.8f) {
                            break;
                        }

                        result = mFairy.findPic2(905, 46, 1059, 100, commonFunction.setImg("Bshuaxin.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "刷新"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    }
                }

                result = commonFunction.FindManyPic(798, 389, 983, 705, new String[]{"dtcbt.png", "dtcbt1.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "打听藏宝图"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(802, 259, 1039, 313, commonFunction.setImg("btgood.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "宝图是个好东西"));
                if (result.sim > 0.8f) {
                    break;
                }

            }
        }
    }

    //丝绸之路
    public void Silkroad() throws Exception {
        String start, sub;
        int[] xy;
        int errsi = 0;

        js_1 = 0;
        js_2 = 0;
        js_3 = 0;
        js_4 = 0;
        js_5 = 0;
        js_6 = 0;
        bj = 1;

        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("丝绸之路中bj=" + bj));
            if (bj == 0) {

                gamePublicFunction.mission2("scroad.png");

                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }


            if (bj == 1) {
                errsi++;
                if (errsi > 20) {
                    bj = 0;
                    errsi = 0;
                }
                /*result = mFairy.findPic2(52, 78, 1030, 572, commonFunction.setImg("scchanlaoda.png"));
                commonFunction.RndCompare(0.7f, result.x + 25, result.y - 60, result, commonFunction.getImg());*/

                result = mFairy.findPic2(commonFunction.setImg("supply.png"));
                result1 = mFairy.findPic2(commonFunction.setImg("feedback.png"));
                if (result.sim > 0.8 || result1.sim > 0.8) {
                    LtLog.i(mFairy.getLineInfo("丝绸之路界面"));
                    errsi = 0;
                       /* start = "77,159,72";
                        sub = "11|14|77,159,72&11|45|77,159,72&11|58|77,159,72&11|64|77,159,72&1|83|77,159,72";
                        xy = functionClass.multipointFindColorEx(180, 88, 1059, 603, start, sub, 0.90);
                        LtLog.i("===选择目的地 :" + Arrays.toString(xy));
                        if (xy[0] > 0) {
                            mFairy.tap(xy[0] + 10, xy[1] + 10);
                            Thread.sleep(500);
                        }*/

                    for (int m = 0; m < 14; m++) {
                        switch (m) {
                            case 0:
                                mFairy.onTap(982, 137, 993, 178, "傲来国", 1000);
                                break;
                            case 1:
                                mFairy.onTap(863, 191, 873, 226, "广州", 1000);
                                break;
                            case 2:
                                mFairy.onTap(952, 302, 960, 336, "不老山", 1000);
                                break;
                            case 3:
                                mFairy.onTap(755, 284, 761, 334, "脚趾", 1000);
                                break;
                            case 4:
                                mFairy.onTap(891, 402, 902, 436, "门毒", 1000);
                                break;
                            case 5:
                                mFairy.onTap(613, 353, 620, 383, "罗越国", 1000);
                                break;
                            case 6:
                                mFairy.onTap(867, 509, 876, 570, "笨陀浪洲", 1000);
                                break;
                            case 7:
                                mFairy.onTap(463, 313, 470, 351, "狮子国", 1000);
                                break;
                            case 8:
                                mFairy.onTap(698, 528, 704, 563, "海峡", 1000);
                                break;
                            case 9:
                                mFairy.onTap(469, 161, 477, 197, "没来国", 1000);
                                break;
                            case 10:
                                mFairy.onTap(574, 503, 589, 546, "佛逝国", 1000);
                                break;
                            case 11:
                                mFairy.onTap(290, 246, 295, 294, "罗和岛国", 1000);
                                break;
                            case 12:
                                mFairy.onTap(415, 464, 426, 509, "珈蓝洲", 1000);
                                break;
                            case 13:
                                mFairy.onTap(229, 510, 240, 542, "邬刺国", 1000);
                                break;
                        }


                        result = mFairy.findPic2(51, 17, 1187, 717, commonFunction.setImg("goto1.png"));
                        if (result.sim > 0.7f) {
                            commonFunction.RndCompare(0.7f, result.x + 10, result.y + 10, result, commonFunction.getImg());
                            break;
                        }

                        result = mFairy.findPic(711, 139, 923, 280, "chaun.png");
                        if (result.sim > 0.8f) {
                            break;
                        }
                    }

                    result = mFairy.findPic(981, 260, 1246, 658, new String[]{"giveup.png", "sai.png"});
                    mFairy.onTap(0.7f, result, "置之不理", 1000);
                }


                result = mFairy.findPic("chaun.png");
                if (result.sim > 0.8f) {
                    bj = 2;
                    continue;
                }
            }


            if (bj == 2) {
                LtLog.e(commonFunction.getLineInfo("丝绸之路中bj=" + bj));

                result = mFairy.findPic2(1020, 192, 1150, 528, commonFunction.setImg("sczl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "任务栏丝绸之路"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 10) {
                        break;
                    }
                }


                result = mFairy.findPic2(981, 260, 1246, 658, commonFunction.setImg("giveup.png"));
                commonFunction.RndCompare(0.7f, result.x + 10, result.y + 10, result, commonFunction.getImg());


                result = mFairy.findPic2(commonFunction.setImg("kszh.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "开始装货"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result1 = mFairy.findPic2(766, 160, 1099, 559, commonFunction.setImg("sczh.png"));
                LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "装货"));
                if (result1.sim > 0.8f) {
                    result = mFairy.findPic2(736, 266, 1126, 380, commonFunction.setImg("xldjm.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "修炼等级满"));
                    if (result.sim > 0.8f) {
                        TaskMain.task = 4585;
                        return;
                    }
                    commonFunction.Compare(0.8f, result1, commonFunction.getImg());
                    js_1 = 0;
                }


                result1 = mFairy.findPic2(766, 160, 1099, 559, commonFunction.setImg("gm.png"));
                LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "购买"));
                if (result1.sim > 0.8f) {
                    result = mFairy.findPic2(736, 266, 1126, 380, commonFunction.setImg("xldjm.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "修炼等级满"));
                    if (result.sim > 0.8f) {
                        TaskMain.task = 4585;
                        return;
                    }
                    commonFunction.Compare(0.8f, result1, commonFunction.getImg());
                    js_1 = 0;
                }


                result = mFairy.findPic2(commonFunction.setImg("supply.png"));
                result1 = mFairy.findPic2(commonFunction.setImg("feedback.png"));
                if (result.sim > 0.8 || result1.sim > 0.8) {
                   /* //bj =2 在选择路线界面，关闭窗口
                    LtLog.e(mFairy.getLineInfo("丝绸之路界面"));
                    result = mFairy.findPic2(182, 79, 1095, 605, commonFunction.setImg("sczlOp.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "sczlOp"));
                    if (result.sim > 0.65f) {
                        commonFunction.Compare(0.65f, result, commonFunction.getImg());
                        Thread.sleep(2000);
                        result = mFairy.findPic2(166, 57, 1121, 720, commonFunction.setImg("goto.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "goto"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    }

                    mFairy.tap(1114, 38);
                    Thread.sleep(1000);*/
                    bj = 1;
                    continue;
                }


                result = mFairy.findPic2(172, 54, 1115, 667, commonFunction.setImg("sczlgm.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "npc店购买"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(530, 400, 695, 550, commonFunction.setImg("scgtshop.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "逛摊购买"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(1118, 217, 1174, 349, commonFunction.setImg("Stalls.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "右侧逛摊"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(245, 121, 482, 578, commonFunction.setImg("gtneed.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "需求"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    Thread.sleep(1500);
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic2(638, 600, 781, 657, commonFunction.setImg("Byjsj.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.1f, "切换到摊位列表"));
                        commonFunction.RndCompare(0.8f, 302, 95, result, commonFunction.getImg());

                        result1 = mFairy.findPic2(505, 98, 860, 574, commonFunction.setImg("need.png"));
                        LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "物品需求"));
                        commonFunction.RndCompare(0.8f, result1.x + 139, result1.y + 18, result1, commonFunction.getImg());
                        commonFunction.RndCompare(0.8f, 785, 636, result1, commonFunction.getImg());

                        result = mFairy.findPic2(439, 124, 488, 578, commonFunction.setImg("nowshop.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购物当前栏"));
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic2(result.x - 60, result.y - 10, result.x + 5, result.y + 40, commonFunction.setImg("gtneed.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "摊位列表需求"));
                        }

                        if (result.sim > 0.8f && result1.sim < 0.8f) {
                            commonFunction.RndCompare(992, 636, "没有需求物品下一页");
                            Thread.sleep(1500);
                        } else if (result.sim < 0.8f && result1.sim < 0.8f) {
                            commonFunction.RndCompare(1124, 47, "关闭界面");
                            break;
                        }

                        result = mFairy.findPic2(905, 46, 1059, 100, commonFunction.setImg("Bshuaxin.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "刷新"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    }
                }

                result = commonFunction.FindManyPic(865, 577, 1090, 647, new String[]{"Bshop.png", "Bsdshop.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购买"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8) {
                    //  gamePublicFunction.init();
                }

                result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                }

                result = mFairy.findPic2(768, 188, 1128, 408, commonFunction.setImg("scover1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "该货物装满"));
                commonFunction.RndCompare(0.8f, 937, 628, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    for (int i = 0; i < 40; i++) {


                        result = mFairy.findPic(981, 260, 1246, 658, new String[]{"giveup.png", "sai.png"});
                        mFairy.onTap(0.7f, result, "置之不理", 1000);
                        if (result.sim >= 0.7f) {
                            break;
                        }

                        result = mFairy.findPic2(1009, 223, 1240, 509, commonFunction.setImg("zhandou.png"));
                        if (result.sim > 0.8f) {
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "进入战斗"));
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());
                            Thread.sleep(2000);
                            continue;
                        }

                        result = mFairy.findPic2(311, 594, 1144, 710, commonFunction.setImg("duihua2.png"));
                        if (result.sim > 0.8f) {
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err++对话栏"));
                            commonFunction.RndCompare(0.8f, 620, 714, result, commonFunction.getImg());
                            Thread.sleep(1500);
                        }

                        Thread.sleep(1000);
                    }

                    break;
                }


            }
        }
    }

    //竞技场
    public void Arena() throws Exception {
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("竞技场中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }
            if (bj == 1) {
                bj = 2;
            }
            if (bj == 2) {

                result = mFairy.findPic2(544, 58, 734, 144, commonFunction.setImg("tishengzz.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "提升再战"));
                commonFunction.RndCompare(0.8f, 972, 100, result, commonFunction.getImg());


                result = mFairy.findPic2(945, 602, 1110, 674, commonFunction.setImg("jjcsxds.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "刷新对手"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(988, 89, 1109, 599, commonFunction.setImg("jjctz.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "竞技场挑战"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(1128, 95, 1176, 207, commonFunction.setImg("cwsx.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "属性"));
                if (result.sim > 0.8f) {
                    TaskMain.task = 4;
                    return;
                }
                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 10) {
                        break;
                    }
                }
            }
        }
    }

    //英雄试炼
    public void HeroFa() throws Exception {
        int ditu = 0, gw = 1;
        w:
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("英雄试炼中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }
            if (bj == 1) {
                for (int i = 1; i < 10; i++) {
                    result = mFairy.findPic2(807, 418, 1027, 594, commonFunction.setImg("jssl.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "我愿接受试炼"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    if (result.sim > 0.8f) {
                        break;
                    }
                    Thread.sleep(2000);
                }
                Thread.sleep(5000);
                bj = 3;
            }

            int num = 0;
            if (bj == 2) {

                for (int i = 1; i < 8; i++) {
                    result = mFairy.findPic2(44, 2, 657, 80, commonFunction.setImg("yxsldt" + i + ".png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "地图" + i));
                    commonFunction.Compare(0.92f, result, commonFunction.getImg());
                    if (result.sim > 0.92f) {
                        Thread.sleep(10000);
                        commonFunction.RndCompare(669, 369, "点开地图");
                        Thread.sleep(3000);
                        num = i;
                        break;
                    }
                }

                for (int i = 1; i < 8; i++) {

                    result = mFairy.findPic2(44, 2, 657, 80, commonFunction.setImg("yxsldt" + i + ".png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "地图" + i));
                    if (result.sim > 0.92f) {
                        if (num == i) {
                            break;
                        }
                        continue w;
                    }
                }


                result = mFairy.findPic2(507, 343, 739, 672, commonFunction.setImg("choice.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "-选择"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8) {
                } else {
                    commonFunction.RndCompare(793, 26, "取消点开的地图");
                }

                for (int i = 0; i < 5; i++) {
                    result = mFairy.findPic2(351, 240, 922, 621, commonFunction.setImg("yxbx.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.75f, "宝箱"));
                    commonFunction.Compare(0.75f, result, commonFunction.getImg());
                    if (result.sim > 0.75) {
                        return;
                    } else {
                        bj = 3;
                    }
                }
            }
            if (bj == 3) {
                for (int i1 = 0; i1 < 2; i1++) {
                    result = mFairy.findPic2(75, 3, 1270, 700, commonFunction.setImg("qtds1.png"));
                    LtLog.e("===result" + result);
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "花果山" + gw));
                    if (result.sim > 0.8f) {
                        if (gw == 1) {
                            commonFunction.RndCompare(0.8f, result.x - 570, result.y + 154, result, commonFunction.getImg());
                        } else if (gw == 2) {
                            commonFunction.RndCompare(0.8f, result.x - 389, result.y + 224, result, commonFunction.getImg());
                        } else if (gw == 3) {
                            commonFunction.RndCompare(0.8f, result.x - 390, result.y + 33, result, commonFunction.getImg());
                        } else if (gw == 4) {
                            commonFunction.RndCompare(0.8f, result.x - 121, result.y + 188, result, commonFunction.getImg());
                        } else if (gw == 5) {
                            commonFunction.RndCompare(0.8f, result.x, result.y, result, commonFunction.getImg());
                        } else if (gw == 6) {
                            LtLog.e(commonFunction.getLineInfo("没有关了结束"));
                            return;
                        }
                        break;
                    }
                    result = mFairy.findPic2(75, 3, 1270, 700, commonFunction.setImg("zbj.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "猪八戒" + gw));
                    if (result.sim > 0.8f) {
                        if (gw == 1) {
                            commonFunction.RndCompare(0.8f, result.x - 511, result.y - 208, result, commonFunction.getImg());
                        } else if (gw == 2) {
                            commonFunction.RndCompare(0.8f, result.x - 40, result.y - 257, result, commonFunction.getImg());
                        } else if (gw == 3) {
                            commonFunction.RndCompare(0.8f, result.x + 97, result.y - 115, result, commonFunction.getImg());
                        } else if (gw == 4) {
                            commonFunction.RndCompare(0.8f, result.x - 383, result.y - 56, result, commonFunction.getImg());
                        } else if (gw == 5) {
                            commonFunction.RndCompare(0.8f, result.x, result.y, result, commonFunction.getImg());
                        } else if (gw == 6) {
                            LtLog.e(commonFunction.getLineInfo("没有关了结束"));
                            return;
                        }
                        break;
                    }
                    result = mFairy.findPic2(75, 3, 1270, 700, commonFunction.setImg("shs.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "沙和尚" + gw));
                    if (result.sim > 0.8f) {
                        if (gw == 1) {
                            commonFunction.RndCompare(0.8f, result.x - 34, result.y - 216, result, commonFunction.getImg());
                        } else if (gw == 2) {
                            commonFunction.RndCompare(0.8f, result.x + 342, result.y - 125, result, commonFunction.getImg());
                        } else if (gw == 3) {
                            commonFunction.RndCompare(0.8f, result.x - 131, result.y - 79, result, commonFunction.getImg());
                        } else if (gw == 4) {
                            commonFunction.RndCompare(0.8f, result.x + 178, result.y + 84, result, commonFunction.getImg());
                        } else if (gw == 5) {
                            commonFunction.RndCompare(0.8f, result.x, result.y, result, commonFunction.getImg());
                        } else if (gw == 6) {
                            LtLog.e(commonFunction.getLineInfo("没有关了结束"));
                            return;
                        }
                        break;
                    }
                    result = mFairy.findPic(75, 3, 1270, 700, new String[]{"dhlw.png", "dhlw1.png"});
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "东海龙王" + gw));
                    if (result.sim > 0.8f) {
                        if (gw == 1) {
                            commonFunction.RndCompare(0.8f, result.x + 37, result.y - 128, result, commonFunction.getImg());
                        } else if (gw == 2) {
                            commonFunction.RndCompare(0.8f, result.x + 360, result.y - 219, result, commonFunction.getImg());
                        } else if (gw == 3) {
                            commonFunction.RndCompare(0.8f, result.x + 268, result.y + 79, result, commonFunction.getImg());
                        } else if (gw == 4) {
                            commonFunction.RndCompare(0.8f, result.x + 499, result.y - 23, result, commonFunction.getImg());
                        } else if (gw == 5) {
                            commonFunction.RndCompare(0.8f, result.x, result.y, result, commonFunction.getImg());
                        } else if (gw == 6) {
                            LtLog.e(commonFunction.getLineInfo("没有关了结束"));
                            return;
                        }
                        break;
                    }
                    result = mFairy.findPic2(75, 3, 1270, 700, commonFunction.setImg("tttw.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "托塔天王" + gw));
                    if (result.sim > 0.8f) {
                        if (gw == 1) {
                            commonFunction.RndCompare(0.8f, result.x - 116, result.y - 246, result, commonFunction.getImg());
                        } else if (gw == 2) {
                            commonFunction.RndCompare(0.8f, result.x + 298, result.y - 281, result, commonFunction.getImg());
                        } else if (gw == 3) {
                            commonFunction.RndCompare(0.8f, result.x + 540, result.y - 210, result, commonFunction.getImg());
                        } else if (gw == 4) {
                            commonFunction.RndCompare(0.8f, result.x - 157, result.y - 54, result, commonFunction.getImg());
                        } else if (gw == 5) {
                            commonFunction.RndCompare(0.8f, result.x, result.y, result, commonFunction.getImg());
                        } else if (gw == 6) {
                            LtLog.e(commonFunction.getLineInfo("没有关了结束"));
                            return;
                        }
                        break;
                    }
                    result = mFairy.findPic2(75, 3, 1270, 700, commonFunction.setImg("dp.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "大鹏" + gw));
                    if (result.sim > 0.8f) {
                        if (gw == 1) {
                            commonFunction.RndCompare(0.8f, result.x - 461, result.y - 258, result, commonFunction.getImg());
                        } else if (gw == 2) {
                            commonFunction.RndCompare(0.8f, result.x - 25, result.y - 263, result, commonFunction.getImg());
                        } else if (gw == 3) {
                            commonFunction.RndCompare(0.8f, result.x - 368, result.y - 58, result, commonFunction.getImg());
                        } else if (gw == 4) {
                            commonFunction.RndCompare(0.8f, result.x + 68, result.y - 106, result, commonFunction.getImg());
                        } else if (gw == 5) {
                            commonFunction.RndCompare(0.8f, result.x, result.y, result, commonFunction.getImg());
                        } else if (gw == 6) {
                            LtLog.e(commonFunction.getLineInfo("没有关了结束"));
                            return;
                        }
                        break;
                    }
                    result = mFairy.findPic2(75, 3, 1270, 700, commonFunction.setImg("nmw.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "牛魔王" + gw));
                    if (result.sim > 0.8f) {
                        if (gw == 1) {
                            commonFunction.RndCompare(0.8f, result.x - 540, result.y - 140, result, commonFunction.getImg());
                        } else if (gw == 2) {
                            commonFunction.RndCompare(0.8f, result.x - 10, result.y - 114, result, commonFunction.getImg());
                        } else if (gw == 3) {
                            commonFunction.RndCompare(0.8f, result.x - 424, result.y + 11, result, commonFunction.getImg());
                        } else if (gw == 4) {
                            commonFunction.RndCompare(0.8f, result.x - 226, result.y + 124, result, commonFunction.getImg());
                        } else if (gw == 5) {
                            commonFunction.RndCompare(0.8f, result.x, result.y, result, commonFunction.getImg());
                        } else if (gw == 6) {
                            LtLog.e(commonFunction.getLineInfo("没有关了结束"));
                            for (int i = 0; i < 5; i++) {
                                result = mFairy.findPic2(613, 2, 700, 74, commonFunction.setImg("slbx.png"));
                                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "宝箱"));
                                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                            }
                            return;
                        }
                        break;
                    }
                }
                if (result.sim < 0.8f) {
                    js_5 = 1;
                }
                Thread.sleep(3000);

                result = mFairy.findPic(514, 558, 738, 682, "tz.png");
                mFairy.onTap(0.8f, result, "挑战", 5000);

                result = mFairy.findPic2(514, 558, 738, 682, commonFunction.setImg("yxtz.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "--挑战"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                Thread.sleep(3000);
                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    gw++;
                    js_2 = 0;
                    bj = 4;
                } else {
                    js_2++;
                    if (js_2 > 1) {
                        gw++;
                        bj = 3;
                        js_2 = 0;
                    }
                    result = mFairy.findPic2(217, 4, 628, 85, commonFunction.setImg("Nricheng1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
                    if (result.sim > 0.8f) {
                        break;
                    }
                }
                if (js_5 == 1) {
                    bj = 2;
                    js_5 = 0;
                }
                js_4++;
                if (js_4 > 40) {
                    return;
                }
            }
            if (bj == 4) {
                js_4 = 0;
                result = mFairy.findPic2(529, 72, 748, 127, commonFunction.setImg("yin_tszz.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "提升再战"));
                commonFunction.RndCompare(0.8f, 970, 104, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_3++;
                    if (js_3 > 5) {
                        break;
                    }
                    gw--;
                }

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_1 = 0;
                } else {
                    LtLog.e(commonFunction.getLineInfo("js_1=" + js_1));
                    js_1++;
                    if (js_1 > 5) {
                        for (int i = 0; i < 5; i++) {
                            result = mFairy.findPic2(351, 240, 922, 621, commonFunction.setImg("yxbx.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.75f, "宝箱"));
                            commonFunction.Compare(0.75f, result, commonFunction.getImg());
                            if (result.sim > 0.75) {
                                LtLog.e(commonFunction.getLineInfo("任务完成"));
                                return;
                            }
                        }
                        bj = 3;
                    }
                }
            }
        }
    }

    //玲珑宝塔
    public void Pagoda() throws Exception {
        int bj = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("玲珑宝塔中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }
            if (bj == 1) {
                ret = gamePublicFunction.mission("llbt.png");
                if (ret == 0) {
                    break;
                } else if (ret == 1) {
                    Thread.sleep(5000);
                    bj = 2;
                }
            }
            if (bj == 2) {


               /* result = mFairy.findPic2(1201, 641, 1263, 678,commonFunction.setImg("llttg.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "通关了"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());*/
                Pagoda_select_monster(); //通过判断固定位置是否有 星星 来决定是否要打此位置的怪

                result = mFairy.findPic2(384, 410, 896, 580, commonFunction.setImg("lltkn2.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "困难"));


                result1 = mFairy.findPic2(384, 410, 896, 580, commonFunction.setImg("lltjy2.png"));
                LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "精英"));

                result2 = mFairy.findPic2(384, 410, 896, 580, commonFunction.setImg("lltpt2.png"));
                LtLog.e(commonFunction.getLineInfo(result2, 0.8f, "普通"));
                if (AtFairyConfig.getOption("nd").equals("1")) {
                    commonFunction.Compare(0.8f, result2, commonFunction.getImg());
                }
                if (AtFairyConfig.getOption("nd").equals("2")) {
                    commonFunction.Compare(0.8f, result1, commonFunction.getImg());
                    commonFunction.Compare(0.8f, result2, commonFunction.getImg());
                }
                if (AtFairyConfig.getOption("nd").equals("3")) {
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    commonFunction.Compare(0.8f, result1, commonFunction.getImg());
                    commonFunction.Compare(0.8f, result2, commonFunction.getImg());

                }

                Thread.sleep(2000);
                result2 = mFairy.findPic2(384, 410, 896, 580, commonFunction.setImg("lltpt2.png"));
                LtLog.e(commonFunction.getLineInfo(result2, 0.8f, "普通"));
                if (result2.sim > 0.8f) {
                    commonFunction.RndCompare(895, 136, "");
                    LtLog.e(commonFunction.getLineInfo("没有次数了"));
                    return;
                }
                result = mFairy.findPic2(commonFunction.setImg("lltfy.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "这层锁住了"));
                if (result.sim > 0.8f) {
                    commonFunction.RndCompare(549, 677, "往上翻一页");
                    bj = 2;
                } else {
                    bj = 3;
                }
            }
            if (bj == 3) {
                result = mFairy.findPic2(1000, 247, 1252, 451, commonFunction.setImg("goaction.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "进入战斗"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(529, 72, 748, 127, commonFunction.setImg("yin_tszz.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "提升再战"));
                commonFunction.RndCompare(0.8f, 970, 104, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_2++;
                    bj = 2;
                }


                result = mFairy.findPic2(660, 286, 798, 442, commonFunction.setImg("lltlx.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "录像失败"));
                commonFunction.RndCompare(0.8f, 849, 178, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_2++;
                    bj = 2;
                }
                if (js_2 > 2) {
                    LtLog.e(commonFunction.getLineInfo("失败次数太多"));
                    break;
                }

                result = mFairy.findPic2(361, 1, 758, 74, commonFunction.setImg("yin_Take the prize1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领奖"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2("yin_Take the prize.png");
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领奖1"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 5) {
                        js_1 = 0;
                        bj = 2;
                    }
                }
            }
        }
    }

    /**
     * 选择玲珑宝塔的怪物 ，通过判断固定位置是否有 星星 来决定是否要打此位置的怪
     */
    public void Pagoda_select_monster() throws Exception {
        int select = 0; // 每层塔怪的固定位置不是一定的，目前看有两种
        result = mFairy.findPic2(932, 437, 991, 509, commonFunction.setImg("lantern.png"));
        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "lantern"));
        if (result.sim > 0.8) {
            select = 1;
        }
        for (int i = 0; i < 1 && select == 1; i++) {
            result = mFairy.findPic2(248, 497, 369, 577, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星1"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(311, 540, "星星1");
                break;
            }
            result = mFairy.findPic2(183, 371, 327, 456, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星2"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(247, 414, "星星2");
                break;
            }
            result = mFairy.findPic2(171, 256, 327, 341, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星3"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(249, 301, "星星3");
                break;
            }
            result = mFairy.findPic2(285, 123, 465, 219, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星4"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(380, 174, "星星4");
                break;
            }
            result = mFairy.findPic2(489, 76, 653, 169, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星5"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(574, 126, "星星5");
                break;
            }

            result = mFairy.findPic2(660, 74, 833, 175, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星6"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(733, 130, "星星6");
                break;
            }

            result = mFairy.findPic2(757, 209, 935, 312, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星7"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(849, 272, "星星7");
                break;
            }

            result = mFairy.findPic2(630, 312, 795, 378, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星8"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(724, 332, "星星8");
                break;
            }

            result = mFairy.findPic2(797, 372, 958, 454, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星9"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(877, 406, "星星9");
                break;
            }

            result = mFairy.findPic2(645, 486, 862, 572, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星10"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(756, 518, "星星10");
                break;
            } else {
                result = mFairy.findPic2(commonFunction.setImg("lltfy1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "下一页"));
                if (result.sim > 0.8f) {
                    commonFunction.RndCompare(729, 681, "下一页");
                } else {
                    LtLog.e(commonFunction.getLineInfo("没有页了结束"));
                    return;
                }
                i = 0;
            }
        }


        for (int i = 0; i < 1 && select == 0; i++) {
            result = mFairy.findPic2(248, 497, 369, 577, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星1"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(311, 540, "星星1");
                break;
            }
            result = mFairy.findPic2(183, 371, 327, 456, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星2"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(247, 414, "星星2");
                break;
            }
            result = mFairy.findPic2(171, 256, 327, 341, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星3"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(249, 301, "星星3");
                break;
            }
            result = mFairy.findPic2(285, 123, 465, 219, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星4"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(380, 174, "星星4");
                break;
            }
            result = mFairy.findPic2(489, 76, 653, 169, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星5"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(574, 126, "星星5");
                break;
            }

            result = mFairy.findPic2(660, 74, 833, 175, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星6"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(733, 130, "星星6");
                break;
            }

            result = mFairy.findPic2(757, 209, 935, 312, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星7"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(849, 272, "星星7");
                break;
            }

            result = mFairy.findPic2(630, 312, 795, 378, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星8"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(724, 332, "星星8");
                break;
            }

            result = mFairy.findPic2(797, 372, 958, 454, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星9"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(877, 406, "星星9");
                break;
            }

            result = mFairy.findPic2(645, 486, 862, 572, commonFunction.setImg("lltxx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.9f, "星星10"));
            if (result.sim < 0.9f) {
                commonFunction.RndCompare(756, 518, "星星10");
                break;
            } else {
                result = mFairy.findPic2(commonFunction.setImg("lltfy1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "下一页"));
                if (result.sim > 0.8f) {
                    commonFunction.RndCompare(729, 681, "下一页");
                } else {
                    LtLog.e(commonFunction.getLineInfo("没有页了结束"));
                    return;
                }
                i = 0;
            }
        }


    }

    //玲珑宝塔周挑战
    public void Pagoda1() throws Exception {
        int bj = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 1, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("玲珑宝塔周挑战中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }
            if (bj == 1) {
                ret = gamePublicFunction.mission("llbt.png");
                if (ret == 0) {
                    break;
                } else if (ret == 1) {
                    Thread.sleep(5000);
                    bj = 2;
                }
            }
            if (bj == 2) {
                result = mFairy.findPic2(commonFunction.setImg("llztz.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "周挑战"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(commonFunction.setImg("zjl.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "周奖励"));
                    if (result.sim < 0.8f) {
                        LtLog.e(commonFunction.getLineInfo("没有开放周挑战"));
                        break;
                    }
                }
                result = mFairy.findPic2(commonFunction.setImg("ztzfinish.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "周挑战完成"));
                if (result.sim > 0.8f) {
                    break;
                }
                result = mFairy.findPic2(commonFunction.setImg("zjl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "周奖励"));
                if (result.sim > 0.8f) {
                    if (js_3 == 1) {
                        commonFunction.RndCompare(331, 502, "第一关");
                    } else if (js_3 == 2) {
                        commonFunction.RndCompare(487, 327, "第2关");
                    } else if (js_3 == 3) {
                        commonFunction.RndCompare(789, 327, "第3关");
                    } else if (js_3 == 4) {
                        commonFunction.RndCompare(628, 502, "第4关");
                    } else if (js_3 == 5) {
                        commonFunction.RndCompare(929, 499, "第5关");
                    } else if (js_3 == 6) {
                        LtLog.e(commonFunction.getLineInfo("没有了结束"));
                        for (int i = 0; i < 5; i++) {
                            result = mFairy.findPic2(266, 80, 426, 154, commonFunction.setImg("yin_Take the prize1.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领奖"));
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        }
                        break;
                    }
                    js_3++;
                    Thread.sleep(2000);

                    result = mFairy.findPic2(384, 410, 896, 580, commonFunction.setImg("lltkn2.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "困难"));


                    result1 = mFairy.findPic2(384, 410, 896, 580, commonFunction.setImg("lltjy2.png"));
                    LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "精英"));

                    result2 = mFairy.findPic2(384, 410, 896, 580, commonFunction.setImg("lltpt2.png"));
                    LtLog.e(commonFunction.getLineInfo(result2, 0.8f, "普通"));
                    if (AtFairyConfig.getOption("nd").equals("1")) {
                        commonFunction.Compare(0.8f, result2, commonFunction.getImg());
                    }
                    if (AtFairyConfig.getOption("nd").equals("2")) {
                        commonFunction.Compare(0.8f, result1, commonFunction.getImg());
                        commonFunction.Compare(0.8f, result2, commonFunction.getImg());
                    }
                    if (AtFairyConfig.getOption("nd").equals("3")) {
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        commonFunction.Compare(0.8f, result1, commonFunction.getImg());
                        commonFunction.Compare(0.8f, result2, commonFunction.getImg());

                    }
                    Thread.sleep(2000);
                    result2 = mFairy.findPic2(384, 410, 896, 580, commonFunction.setImg("lltpt2.png"));
                    LtLog.e(commonFunction.getLineInfo(result2, 0.8f, "普通"));
                    if (result2.sim > 0.8f) {
                        commonFunction.RndCompare(895, 136, "");
                        LtLog.e(commonFunction.getLineInfo("没有次数了"));
                        bj = 2;
                    } else {
                        bj = 3;
                    }
                    result = mFairy.findPic2(commonFunction.setImg("zjl.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "周奖励"));
                    if (result.sim > 0.8f) {
                        bj = 2;
                    }
                }

            }
            if (bj == 3) {
                result = mFairy.findPic2(1000, 247, 1252, 451, commonFunction.setImg("goaction.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "进入战斗"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(529, 72, 748, 127, commonFunction.setImg("yin_tszz.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "提升再战"));
                commonFunction.RndCompare(0.8f, 970, 104, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_2++;
                    bj = 2;
                }


                result = mFairy.findPic2(660, 286, 798, 442, commonFunction.setImg("lltlx.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "录像失败"));
                commonFunction.RndCompare(0.8f, 849, 178, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_2++;
                    bj = 2;
                }
                if (js_2 > 2) {
                    LtLog.e(commonFunction.getLineInfo("失败次数太多"));
                    break;
                }

                result = mFairy.findPic2(361, 1, 758, 74, commonFunction.setImg("yin_Take the prize1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领奖"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(517, 399, 768, 483, "yin_Take the prize.png");
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领奖1"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 5) {
                        js_1 = 0;
                        bj = 2;
                    }
                }
            }
        }
    }

    //挖宝图
    public void Usemap() throws Exception {
        int bj = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        int cs_1 = 0;
        while (mFairy.condit()) {
            Thread.sleep(1000);
            LtLog.e(commonFunction.getLineInfo("挖宝图中bj=" + bj));
            if (bj == 0) {
                gamePublicFunction.init();
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }
            if (bj == 1) {
                result = mFairy.findPic2(1188, 574, 1273, 621, commonFunction.setImg("Nwupin.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "物品"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                Thread.sleep(2000);
                result = mFairy.findPic2(934, 585, 1098, 648, commonFunction.setImg("bgzl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "整理"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    Thread.sleep(3000);
                    bj = 2;
                }
                cs_1++;
                if (cs_1 > 20) {
                    cs_1 = 0;
                    bj = 0;
                }
            }
            if (bj == 2) {
                result = mFairy.findPic2(934, 585, 1098, 648, commonFunction.setImg("bgzl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "整理"));
                if (result.sim > 0.8f) {
                    if (js_1 == 1) {
                        commonFunction.RndCompare(720, 90, "第一页");
                    } else if (js_1 == 2) {
                        mFairy.touchDown(2, 1066, 305);
                        mFairy.touchMove(2, 822, 335, 1000);
                        mFairy.touchUp(2);
                        //  commonFunction.RanSwipe(698,297,1068,309,3,1000);
                        //  commonFunction.RndCompare(816,90,"第2页");
                    } else if (js_1 == 3) {
                        mFairy.touchDown(2, 1066, 305);
                        mFairy.touchMove(2, 822, 335, 1000);
                        mFairy.touchUp(2);
                        //  commonFunction.RanSwipe(698,297,1068,309,3,1000);
                        //  commonFunction.RndCompare(916,90,"第3页");
                    } else if (js_1 == 4) {
                        mFairy.touchDown(2, 1066, 305);
                        mFairy.touchMove(2, 822, 335, 1000);
                        mFairy.touchUp(2);
                        //  commonFunction.RanSwipe(698,297,1068,309,3,1000);
                        // commonFunction.RndCompare(916,90,"第3页");
                    } else if (js_1 == 5) {
                        mFairy.touchDown(2, 1066, 305);
                        mFairy.touchMove(2, 822, 335, 1000);
                        mFairy.touchUp(2);
                        //  commonFunction.RanSwipe(698,297,1068,309,3,1000);
                        //  commonFunction.RndCompare(916,90,"第3页");
                    } else if (js_1 > 5) {
                        LtLog.e(commonFunction.getLineInfo("没有了结束"));
                        break;
                    }
                    Thread.sleep(3000);
                    result = mFairy.findPic2(663, 109, 1107, 561, commonFunction.setImg("bgbt.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "宝图"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    if (result.sim > 0.8f) {
                        bj = 3;
                    } else {
                        js_1++;
                    }
                } else {
                    bj = 0;
                }
            }
            if (bj == 3) {
                result = mFairy.findPic2(443, 10, 1263, 704, commonFunction.setImg("sybt.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "使用宝图"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(979, 529, 1204, 630, commonFunction.setImg("usebt.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "使用宝图1"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 10) {
                        bj = 0;
                    }
                }

                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_1 = 0;
                }

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

            }
        }
    }

    //挖罗盘
    public void Usemap1() throws Exception {
        int bj = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        int cs_1 = 0;
        while (mFairy.condit()) {
            Thread.sleep(1000);
            LtLog.e(commonFunction.getLineInfo("挖罗盘中bj=" + bj));
            if (bj == 0) {
                gamePublicFunction.init();
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }
            if (bj == 1) {
                result = mFairy.findPic2(1188, 574, 1273, 621, commonFunction.setImg("Nwupin.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "物品"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(934, 585, 1098, 648, commonFunction.setImg("bgzl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "整理"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    bj = 2;
                }
                cs_1++;
                if (cs_1 > 20) {
                    cs_1 = 0;
                    bj = 0;
                }
            }
            if (bj == 2) {
                result = mFairy.findPic2(934, 585, 1098, 648, commonFunction.setImg("bgzl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "整理"));
                if (result.sim > 0.8f) {
                    if (js_1 == 1) {
                        commonFunction.RndCompare(720, 90, "第一页");
                    } else if (js_1 == 2) {
                        mFairy.touchDown(2, 1066, 305);
                        mFairy.touchMove(2, 822, 335, 1000);
                        mFairy.touchUp(2);
                        //  commonFunction.RanSwipe(698,297,1068,309,3,1000);
                        //  commonFunction.RndCompare(816,90,"第2页");
                    } else if (js_1 == 3) {
                        mFairy.touchDown(2, 1066, 305);
                        mFairy.touchMove(2, 822, 335, 1000);
                        mFairy.touchUp(2);
                        //  commonFunction.RanSwipe(698,297,1068,309,3,1000);
                        //  commonFunction.RndCompare(916,90,"第3页");
                    } else if (js_1 == 4) {
                        mFairy.touchDown(2, 1066, 305);
                        mFairy.touchMove(2, 822, 335, 1000);
                        mFairy.touchUp(2);
                        //  commonFunction.RanSwipe(698,297,1068,309,3,1000);
                        // commonFunction.RndCompare(916,90,"第3页");
                    } else if (js_1 == 5) {
                        mFairy.touchDown(2, 1066, 305);
                        mFairy.touchMove(2, 822, 335, 1000);
                        mFairy.touchUp(2);
                        //  commonFunction.RanSwipe(698,297,1068,309,3,1000);
                        //  commonFunction.RndCompare(916,90,"第3页");
                    } else if (js_1 > 5) {
                        LtLog.e(commonFunction.getLineInfo("没有了结束"));
                        break;
                    }
                    Thread.sleep(3000);
                    result = mFairy.findPic2(663, 109, 1107, 561, commonFunction.setImg("bzlp.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "罗盘"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    if (result.sim > 0.8f) {
                        bj = 3;
                    } else {
                        js_1++;
                    }
                } else {
                    bj = 0;
                }

            }
            if (bj == 3) {
                result = mFairy.findPic2(443, 10, 1263, 704, commonFunction.setImg("sybt.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "使用罗盘"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(83, 37, 1234, 703, commonFunction.setImg("lpbx.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.9f, "罗盘宝箱"));
                commonFunction.Compare(0.9f, result, commonFunction.getImg());


                result = mFairy.findPic2(707, 3, 1243, 323, commonFunction.setImg("btcz.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "铲子"));
                if (result.sim > 0.8f) {
                    String string = commonFunction.testchange(240, 21, 800, 673);
                    String[] strarr = string.split(",");
                    LtLog.e(commonFunction.getLineInfo("strarr=" + string));
                    commonFunction.RndCompare(Integer.parseInt(strarr[0]), Integer.parseInt(strarr[1]), "铲子");
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 10) {
                        bj = 0;
                    }
                }
                result = mFairy.findPic2(934, 585, 1098, 648, commonFunction.setImg("bgzl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "整理"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    bj = 2;
                }
            }
        }
    }

    //伙伴任务
    public void Partner() throws Exception {
        int bj = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        int hb150 = 0;
        if (AtFairyConfig.getOption("150tf").equals("1")) {
            hb150 = 1;
        }
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("伙伴中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }
            if (bj == 1) {
                ret = gamePublicFunction.mission1("hbrw.png", "wchbrw.png");
                if (ret == 0) {
                    break;
                } else if (ret == 1) {
                    bj = 2;
                }
            }
            if (bj == 2) {
                result = mFairy.findPic2(1022, 189, 1096, 504, commonFunction.setImg("rwhb.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "任务栏伙伴"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 10) {
                        bj = 0;
                    }
                }

                result = mFairy.findPic2(803, 408, 1022, 579, commonFunction.setImg("lqhbrw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领取伙伴任务"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(454, 4, 832, 108, commonFunction.setImg("hbjm.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "伙伴界面"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(288, 253, 1003, 703, commonFunction.setImg("hlq.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "150天赋任务"));

                    result1 = mFairy.findPic2(288, 253, 1003, 703, commonFunction.setImg("llq.png"));
                    LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "50天赋任务"));
                    if (result.sim > 0.8f && hb150 == 1) {
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    } else if (result.sim < 0.8f && hb150 == 1) {
                        commonFunction.RndCompare(929, 670, "刷新一下");
                        js_2++;
                    } else if (result.sim > 0.8f && result1.sim > 0.8f) {
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    } else if (result.sim < 0.8f && result1.sim > 0.8f) {
                        commonFunction.Compare(0.8f, result1, commonFunction.getImg());
                    } else if (result.sim > 0.8f && result1.sim > 0.8f) {
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    } else if (result.sim < 0.8f && result1.sim < 0.8f) {
                        commonFunction.RndCompare(929, 670, "刷新一下");
                        js_2++;
                    }
                    if (js_2 > 5) {
                        LtLog.e(commonFunction.getLineInfo("没有任务了，结束"));
                    }
                }
                result = mFairy.findPic2(796, 312, 1025, 712, commonFunction.setImg("lshb.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "蓝色伙伴"));
                commonFunction.RndCompare(0.8f, result.x, result.y + 20, result, commonFunction.getImg());


                result = mFairy.findPic2(796, 312, 1025, 712, commonFunction.setImg("geiyusw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予食物"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(796, 312, 1025, 712, commonFunction.setImg("give2.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予礼物"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(796, 312, 1025, 712, commonFunction.setImg("ezxdck.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "恶贼"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(796, 312, 1025, 712, commonFunction.setImg("jc.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "捐献"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(796, 403, 977, 501, commonFunction.setImg("jxrw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "进行任务"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(802, 342, 959, 623, commonFunction.setImg("wybs.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "我要变身"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(1028, 209, 1194, 463, commonFunction.setImg("Bjoinzd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "进入战斗"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(796, 312, 1025, 712, commonFunction.setImg("gycw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予宠物"));
                commonFunction.RndCompare(0.8f, result.x, result.y + 20, result, commonFunction.getImg());

                result = mFairy.findPic2(794, 337, 1006, 631, commonFunction.setImg("yzzl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "药在这里"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(797, 364, 999, 618, commonFunction.setImg("ydll.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "药已经带来了"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(commonFunction.setImg("ysc.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "袁守城"));
                if (result.sim > 0.8f) {
                    commonFunction.RanSwipe(895, 435, 909, 622, 2, 2000);
                }

                result = mFairy.findPic2(808, 373, 1070, 601, commonFunction.setImg("shouyi.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "收益良多"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                }

                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_1 = 0;
                }

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(commonFunction.setImg("hbjrzd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "蓝色进入战斗"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = commonFunction.FindManyPic(865, 577, 1090, 647, new String[]{"Bshop.png", "Bsdshop.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购买"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8) {
                    gamePublicFunction.init();
                }

                result = mFairy.findPic2(1118, 217, 1174, 349, commonFunction.setImg("Stalls.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "右侧逛摊"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(245, 121, 482, 578, commonFunction.setImg("gtneed.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "需求"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic2(638, 600, 781, 657, commonFunction.setImg("Byjsj.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.1f, "切换到摊位列表"));
                        commonFunction.RndCompare(0.8f, 302, 95, result, commonFunction.getImg());

                        result1 = mFairy.findPic2(505, 98, 860, 574, commonFunction.setImg("need.png"));
                        LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "物品需求"));
                        commonFunction.RndCompare(0.8f, result1.x + 139, result1.y + 18, result1, commonFunction.getImg());
                        commonFunction.RndCompare(0.8f, 785, 636, result1, commonFunction.getImg());

                        result = mFairy.findPic2(439, 124, 488, 578, commonFunction.setImg("nowshop.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购物当前栏"));
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic2(result.x - 60, result.y - 10, result.x + 5, result.y + 40, commonFunction.setImg("gtneed.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "摊位列表需求"));
                        }

                        if (result.sim > 0.8f && result1.sim < 0.8f) {
                            commonFunction.RndCompare(992, 636, "没有需求物品下一页");
                            Thread.sleep(1500);
                        } else if (result.sim < 0.8f && result1.sim < 0.8f) {
                            break;
                        }

                        result = mFairy.findPic2(905, 46, 1059, 100, commonFunction.setImg("Bshuaxin.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "刷新"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    }
                }
            }
        }
    }

    //一键32级
    public void MainThredTask() throws Exception {
        int bj = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        while (mFairy.condit()) {
            Thread.sleep(10);
            LtLog.e(commonFunction.getLineInfo("一键32级中bj=" + bj));


            result = mFairy.findPic(342, 101, 847, 246, "nnend.png");
            if (result.sim > 0.8f) {
                LtLog.e(mFairy.getLineInfo("主线结束"));
                return;
            }

            result = mFairy.findPic("yc.png");
            if (result.sim > 0.8f) {
                mFairy.touchDown(786, 166);
                mFairy.touchMove(482, 321, 200);
                mFairy.touchMove(934, 428, 200);
                mFairy.touchMove(711, 621, 200);
                mFairy.touchMove(414, 524, 200);
                mFairy.touchUp();
            }

            result = mFairy.findPic("nn1.png");
            mFairy.onTap(0.8f, result, "我有经验", 1000);

            result = mFairy.findPic(850, 459, 1167, 533, "yjsq2.png");
            mFairy.onTap(0.8f, result, "一键申请", 1000);

            result = mFairy.findPic(850, 459, 1167, 533, "nn13.png");
            mFairy.onTap(0.8f, result, "前往", 1000);

           /* result = mFairy.findPic(1004,253,1224,531,"nn4.png");
            mFairy.onTap(0.8f,result,"上交物品",1000);

            result = mFairy.findPic(1004,253,1224,531,"nn5.png");
            mFairy.onTap(0.8f,result,"我要领养",1000);*/

            result = mFairy.findPic(964, 200, 1062, 501, "duihua3.png");
            mFairy.onTap(0.8f, result, "对话栏", 1000);

            result = mFairy.findPic("nn7.png");
            mFairy.onTap(0.9f, result, "招募", 1000);

            result = mFairy.findPic(1018, 537, 1192, 611, "nn2.png");
            mFairy.onTap(0.8f, result, "使用", 1000);

            result = mFairy.findPic(1018, 537, 1192, 611, "zhuangbei.png");
            mFairy.onTap(0.8f, result, "装备", 1000);

            result = mFairy.findPic(476, 435, 805, 672, "nn3.png");
            mFairy.onTap(0.9f, result, 1218, 647, 1242, 666, "点击空白区域", 1000);

            result = mFairy.findPic("nn9.png");
            mFairy.onTap(0.8f, result, 911, 82, 932, 97, "查看资质", 1000);

            result = mFairy.findPic("nn15.png");
            mFairy.onTap(0.8f, result, 1022, 78, 1052, 88, "查看技能", 1000);

            result = mFairy.findPic(197, 268, 387, 385, "nn10.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, 772, 427, 787, 445, "", 2000);

                result = mFairy.findPic("nn16.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "装备", 1000);
                } else {
                    mFairy.onTap(616, 310, 648, 329, "装备项圈", 1000);
                }
            }

            result = mFairy.findPic(777, 377, 1004, 623, "nn11.png");
            mFairy.onTap(0.8f, result, "修行", 1000);

            result = mFairy.findPic(1106, 4, 1267, 68, "tg1.png");
            mFairy.onTap(0.8f, result, "跳过", 1000);

            result = mFairy.findPic(1013, 162, 1106, 451, "nn8.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "修行", 1000);
            } else {
                result = mFairy.findPic(1014, 183, 1093, 534, "zhuxian.png");
                LtLog.e(mFairy.getLineInfo("主线sim:" + result.sim));
                if (result.sim > 0.75f) {
                    mFairy.onTap(0.75f, result, "点击主线", 5000);
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 15) {
                        gamePublicFunction.init();
                        js_1 = 0;
                    }
                }
            }

            for (int i = 0; i < 5; i++) {
                result = mFairy.findPic(207, 633, 1213, 717, "duihua4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "对话按钮", 1000);
                } else {
                    break;
                }
            }

            result = mFairy.findPic(950, 7, 1276, 206, new String[]{"fork9.png", "nn6.png", "nn12.png", "nn14.png"});
            if (result.sim > 0.8f) {
                js_1 = 0;
                js_2++;
                if (js_2 > 3) {
                    mFairy.onTap(0.8f, result, "关闭叉子", 1000);
                    js_2 = 0;
                }
            } else {
                js_2 = 0;
            }


            while (mFairy.condit()) {
                result = mFairy.findPic2(919, 194, 1064, 677, commonFunction.setImg("jlq.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "级领取"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                } else {
                    result = mFairy.findPic2(commonFunction.setImg("sjjl.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "升级奖励"));
                    commonFunction.RndCompare(0.8f, 1108, 70, result, commonFunction.getImg());
                    break;
                }
            }


          /*  result = mFairy.findPic2(320, 459, 471, 509, commonFunction.setImg("jrlsf.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "进入临时服"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());

            result = mFairy.findPic2(544, 225, 734, 318, commonFunction.setImg("dlgz.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "登录管制"));
            commonFunction.RndCompare(0.8f, 639, 486, result, commonFunction.getImg());


            result = mFairy.findPic2(448, 87, 856, 211, commonFunction.setImg("xsdc.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "新手调查"));
            commonFunction.RndCompare(0.8f, 798, 511, result, commonFunction.getImg());


            result = mFairy.findPic2(537, 229, 742, 304, commonFunction.setImg("hdyf.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "回到原服"));
            commonFunction.RndCompare(0.8f, 400, 487, result, commonFunction.getImg());
            if (result.sim > 0.8f) {
                js_3 = 1;
            }


            if (js_3 == 1) {
                result = mFairy.findPic2(1, 82, 72, 331, commonFunction.setImg("yuanfu.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "回到原服"));
                commonFunction.RndCompare(0.8f, result.x + 5, result.y - 10, result, commonFunction.getImg());
            }*/


            result = mFairy.findPic2(804, 335, 912, 660, commonFunction.setImg("lszx.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "主线"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());

           /* result = mFairy.findPic2(94, 337, 1006, 631, commonFunction.setImg("yzzl.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "药在这里"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());

            result = mFairy.findPic2(644, 211, 780, 259, commonFunction.setImg("qxcz.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "取消操作"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());

            result = mFairy.findPic2(798, 394, 925, 669, commonFunction.setImg("Bsm1.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "师门按钮"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());*/

        /*    result = mFairy.findPic2(1028, 209, 1194, 463, commonFunction.setImg("Bjoinzd.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "进入战斗"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());

            result = mFairy.findPic2(806, 348, 965, 522, commonFunction.setImg("Bwyzrw.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "我要做任务"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());*/


            result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
            if (result.sim > 0.8f) {
                js_1 = 0;
            }

            result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());

            result = mFairy.findPic2(1028, 184, 1279, 419, commonFunction.setImg("kdj.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "卡等级了"));
            if (result.sim > 0.8f) {
                js_5++;
                if (js_5 > 5) {
                    ShimenTask1();
                    gamePublicFunction.init();
                }
            } else {
                js_5 = 0;
            }


            result = mFairy.findPic2(548, 66, 738, 126, commonFunction.setImg("tszz3.png"));
            if (result.sim > 0.8f) {
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "提升再战3"));
                commonFunction.RndCompare(0.8f, 966, 102, result, commonFunction.getImg());
                js_6++;
                if (js_6 > 3) {
                    break;
                }
            }


            result = commonFunction.FindManyPic(865, 577, 1090, 647, new String[]{"Bshop.png", "Bsdshop.png"}, 0);
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购买"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
            if (result.sim > 0.8) {
                gamePublicFunction.init();
            }

            result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
            commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
            commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
            commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
            if (result.sim > 0.8f) {
                result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
            }

            result = mFairy.findPic2(1118, 217, 1174, 349, commonFunction.setImg("Stalls.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "右侧逛摊"));
            if (result.sim > 0.8f) {
                result = mFairy.findPic2(245, 121, 482, 578, commonFunction.setImg("gtneed.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.1f, "需求"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                for (int i = 0; i < 10; i++) {
                    result = mFairy.findPic2(638, 600, 781, 657, commonFunction.setImg("Byjsj.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "切换到摊位列表"));
                    commonFunction.RndCompare(0.8f, 302, 95, result, commonFunction.getImg());

                    result1 = mFairy.findPic2(505, 98, 860, 574, commonFunction.setImg("need.png"));
                    LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "物品需求"));
                    commonFunction.RndCompare(0.8f, result1.x + 139, result1.y + 18, result1, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 785, 636, result1, commonFunction.getImg());

                    result = mFairy.findPic2(439, 124, 488, 578, commonFunction.setImg("nowshop.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购物当前栏"));
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic2(result.x - 60, result.y - 10, result.x + 5, result.y + 40, commonFunction.setImg("gtneed.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "摊位列表需求"));
                    }

                    if (result.sim > 0.8f && result1.sim < 0.8f) {
                        commonFunction.RndCompare(992, 636, "没有需求物品下一页");
                        Thread.sleep(1500);
                    } else if (result.sim < 0.8f && result1.sim < 0.8f) {
                        break;
                    }

                    result = mFairy.findPic2(905, 46, 1059, 100, commonFunction.setImg("Bshuaxin.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "刷新"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                }
            }
        }
    }

    //历练任务
    public void Practice() throws Exception {
        int bj = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        int llnd = 0;
        if (AtFairyConfig.getOption("llnd").equals("1")) {
            llnd = 1;
        }
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("历练任务bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }
            if (bj == 1) {
                ret = gamePublicFunction.mission("llrw.png");
                if (ret == 0) {
                    break;
                } else if (ret == 1) {
                    bj = 2;
                }
            }
            if (bj == 2) {

                result = mFairy.findPicRange(commonFunction.setImg("llpt1.png"), 30);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "在历练界面"));
                if (llnd == 1) {
                    commonFunction.RndCompare(0.8f, 265, 210, result, commonFunction.getImg());
                } else {
                    commonFunction.RndCompare(0.8f, 430, 208, result, commonFunction.getImg());
                }

                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(190, 396, 1127, 592, commonFunction.setImg("llwt3.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "历练舞台"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    if (result.sim > 0.8f) {
                        js_2 = 0;
                    } else {
                        js_2++;
                        if (js_2 > 10) {
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "没有历练舞台了"));
                            break;
                        }
                    }
                }
                result = mFairy.findPic2(1021, 191, 1111, 537, commonFunction.setImg("rwll.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "任务历练..."));
                commonFunction.RndCompare(0.8f, result.x + 20, result.y + 10, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_1 = 0;
                }

                result = mFairy.findPic2(529, 72, 748, 127, commonFunction.setImg("yin_tszz.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err提升再战"));
                commonFunction.RndCompare(0.8f, 970, 104, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_3++;
                    if (js_3 > 3) {
                        break;
                    }
                }

                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 20) {
                        bj = 0;
                    }
                }
                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(311, 594, 1144, 710, commonFunction.setImg("duihua2.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "对话栏"));
                commonFunction.RndCompare(0.8f, 620, 714, result, commonFunction.getImg());


            }
        }
    }

    //任务链普通
    public void taskChain() throws Exception {
        int bj = 0, bj_1;
        int cs_1 = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        int ditu = 0;
        String string = "";
        ditu = Integer.parseInt(AtFairyConfig.getOption("ditu"));
        if (ditu == 1) {
            string = "514.png";
        } else if (ditu == 2) {
            string = "1524.png";
        } else if (ditu == 3) {
            string = "2534.png";
        } else if (ditu == 4) {
            string = "3544.png";
        } else if (ditu == 5) {
            string = "4554.png";
        } else if (ditu == 6) {
            string = "5564.png";
        } else if (ditu == 7) {
            string = "6574.png";
        } else if (ditu == 8) {
            string = "7584.png";
        } else if (ditu == 9) {
            string = "8594.png";
        } else if (ditu == 10) {
            string = "95104.png";
        } else if (ditu == 11) {
            string = "100109.png";
        } else if (ditu == 12) {
            string = "110119.png";
        } else if (ditu == 13) {
            string = "120129.png";
        } else if (ditu == 14) {
            string = "130139.png";
        } else if (ditu == 15) {
            string = "135144.png"; //有疑问 没现场，等级不够
        }
        bj_1 = bj;
        while (mFairy.condit()) {
            if (bj_1 == bj && (bj != 4 && bj != 5)) {
                cs_1++;
            } else if (bj_1 != bj) {
                cs_1 = 0;
                bj_1 = bj;
                LtLog.e(commonFunction.getLineInfo("标记发生变化"));
            }
            if (cs_1 > 150) {
                cs_1 = 0;
                bj = 0;
                LtLog.e(commonFunction.getLineInfo("标记长时间未发生变化"));
            }
            Thread.sleep(1500);

            result = mFairy.findPic(780, 310, 964, 676, "yl.png");
            mFairy.onTap(0.8f, result, "游厉", 1000);


            LtLog.e(commonFunction.getLineInfo("任务链中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                gamePublicFunction.init();
                gamePublicFunction.SetJudgeTask("task");
                bj = 1;
            }
            if (bj == 1) {
                result = mFairy.findPic2(1017, 186, 1104, 510, commonFunction.setImg("yin_rwl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.7f, "任务链"));
                commonFunction.Compare(0.7f, result, commonFunction.getImg());
                if (result.sim > 0.7f) {
                    bj = 5;
                } else {
                    gamePublicFunction.BackCity(278, 385, "临仙镇");
                    Thread.sleep(2000);
                    result = mFairy.findPic2(107, 22, 187, 54, commonFunction.setImg("lxz.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "临仙"));
                    commonFunction.RndCompare(0.8f, result.x, result.y + 10, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 455, 206, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 1151, 24, result, commonFunction.getImg());
                    bj = 5;
                }
            }
            if (bj == 2) {
                gamePublicFunction.mission2("Hanging.png");
                Thread.sleep(2000);
                bj = 3;
            }
            if (bj == 3) {
                result = mFairy.findPic2(commonFunction.setImg("zdset.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.86f, "打怪双倍界面"));
                if (result.sim > 0.8) {
                    if (ditu == 14) {
                        result = mFairy.findPic2(234, 130, 1056, 243, commonFunction.setImg("tuijian.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "推荐"));
                        commonFunction.RndCompare(0.8f, result.x + 90, result.y + 80, result, commonFunction.getImg());
                        if (result.sim > 0.8f) {
                            bj = 4;
                        }
                    }

                    result = mFairy.findPic2(246, 403, 1070, 460, commonFunction.setImg(string));
                    LtLog.e(commonFunction.getLineInfo(result, 0.86f, "地图"));
                    commonFunction.RndCompare(0.86f, result.x + 20, result.y - 200, result, commonFunction.getImg());
                    if (result.sim > 0.86f) {
                        bj = 4;
                    } else {
                        js_7++;
                        if (js_7 == 2 || js_7 == 4 || js_7 == 6 || js_7 == 8 || js_7 == 10 || js_7 == 12) {
                            commonFunction.RanSwipe(338, 254, 769, 264, 1, 1500);
                        }
                        if (js_7 > 14) {
                            bj = 0;
                        }
                    }
                } else {
                    bj = 0;
                }

            }
            if (bj == 4) {
                result = mFairy.findPic2(316, 288, 792, 443, commonFunction.setImg("yin_gdj.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "场景等级高确定"));
                commonFunction.RndCompare(0.8f, 401, 487, result, commonFunction.getImg());

                result = mFairy.findPic2(1017, 186, 1104, 510, commonFunction.setImg("yin_rwl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.7f, "任务链"));
                if (result.sim > 0.7f) {
                    result1 = mFairy.findPic2(result.x, result.y + 18, result.x + 248, result.y + 118, commonFunction.setImg("rwlwancheng.png"));
                    LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "完成"));

                    result2 = mFairy.findPic2(result.x, result.y + 18, result.x + 248, result.y + 118, commonFunction.setImg("rwlfz.png"));
                    LtLog.e(commonFunction.getLineInfo(result2, 0.8f, "分钟"));

                    result3 = mFairy.findPic2(result.x, result.y + 18, result.x + 248, result.y + 118, commonFunction.setImg("rwldg.png"));
                    LtLog.e(commonFunction.getLineInfo(result3, 0.8f, "打怪"));

                    result4 = mFairy.findPic2(1017, 186, 1104, 510, commonFunction.setImg("rwlzc.png"));
                    LtLog.e(commonFunction.getLineInfo(result4, 0.8f, "捉宠"));
                    if (result4.sim > 0.8f) {
                        gamePublicFunction.init();
                        bj = 5;
                    } else if (result1.sim > 0.8f || result2.sim > 0.8f && result3.sim < 0.8f) {
                        gamePublicFunction.init();
                        bj = 5;
                    }
                    bj = 5;
                    js_6 = 0;
                } else {
                    js_6++;
                    if (js_6 > 70) {
                        bj = 0;
                    }
                }

                result = mFairy.findPic2(commonFunction.setImg("shijianbugoule.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "前往完成"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    gamePublicFunction.init();
                    js_3 = 1;
                    bj = 5;
                }

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 30) {
                        js_1 = 0;
                        bj = 2;
                    }
                }
            }
            if (bj == 5) {
                result = mFairy.findPic2(1017, 186, 1104, 510, commonFunction.setImg("yin_rwl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.7f, "任务链"));
                commonFunction.Compare(0.7f, result, commonFunction.getImg());
                if (result.sim > 0.7f) {
                    js_2 = 0;
                } else {
                    js_2++;
                    if (js_2 > 30) {
                        bj = 0;
                    }
                }
                result = mFairy.findPic2(commonFunction.setImg("shijianbugoule.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "前往完成"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    js_3 = 1;
                }

                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_2 = 0;
                }

                result = mFairy.findPic2(1073, 7, 1276, 206, commonFunction.setImg("fork9.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "叉9"));
                if (result.sim > 0.8f) {
                    js_4++;
                    if (js_4 > 5) {
                        result1 = mFairy.findPic2(commonFunction.setImg("gybs.png"));
                        LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "雇佣帮手"));
                        if (result1.sim > 0.8f) {
                            js_5++;
                            if (js_5 > 3) {
                                TaskMain.task = 2042;
                                break;
                            }
                        }
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        js_4 = 0;
                    }
                } else {
                    js_4 = 0;
                }

                result = mFairy.findPic2(commonFunction.setImg("ysc.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "袁守城"));
                if (result.sim > 0.8f) {
                    commonFunction.RanSwipe(895, 435, 909, 622, 2, 2000);
                }

                result = mFairy.findPic2(commonFunction.setImg("zckq.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "再次开启任务链"));
                commonFunction.RndCompare(0.8f, 402, 491, result, commonFunction.getImg());

               /* result = mFairy.findPic2(785, 278, 1253, 717, commonFunction.setImg("lsrwl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "蓝色任务链"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());*/

                result = mFairy.findPic2(788, 370, 1250, 711, commonFunction.setImg("rwllrw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "蓝色领任务"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(779, 56, 1257, 692, commonFunction.setImg("wflq.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "无法领取"));
                if (result.sim > 0.8f) {
                    break;
                }


                result = commonFunction.FindManyPic(865, 577, 1090, 647, new String[]{"Bshop.png", "Bsdshop.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购买"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8) {
                    gamePublicFunction.init();
                }

                result = mFairy.findPic2(commonFunction.setImg("cwgy.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "宠物给予"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                }


                result = mFairy.findPic2(1118, 217, 1174, 349, commonFunction.setImg("Stalls.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "右侧逛摊"));
                if (result.sim > 0.8f && js_3 == 0) {
                    bj = 2;
                } else if (result.sim > 0.8f && js_3 == 1) {
                    result = mFairy.findPic2(245, 121, 482, 578, commonFunction.setImg("gtneed.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "需求"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic2(638, 600, 781, 657, commonFunction.setImg("Byjsj.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.1f, "切换到摊位列表"));
                        commonFunction.RndCompare(0.8f, 302, 95, result, commonFunction.getImg());

                        result1 = mFairy.findPic2(505, 98, 860, 574, commonFunction.setImg("need.png"));
                        LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "物品需求"));
                        commonFunction.RndCompare(0.8f, result1.x + 139, result1.y + 18, result1, commonFunction.getImg());
                        commonFunction.RndCompare(0.8f, 785, 636, result1, commonFunction.getImg());
                        if (result1.sim > 0.8f) {
                            js_3 = 0;
                        }

                        result = mFairy.findPic2(439, 124, 488, 578, commonFunction.setImg("nowshop.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购物当前栏"));
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic2(result.x - 60, result.y - 10, result.x + 5, result.y + 40, commonFunction.setImg("gtneed.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "摊位列表需求"));
                        }

                        if (result.sim > 0.8f && result1.sim < 0.8f) {
                            commonFunction.RndCompare(992, 636, "没有需求物品下一页");
                            Thread.sleep(1500);
                        } else if (result.sim < 0.8f && result1.sim < 0.8f) {
                            break;
                        }

                        result = mFairy.findPic2(905, 46, 1059, 100, commonFunction.setImg("Bshuaxin.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "刷新"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());

                    }
                }
            }
            result = mFairy.findPic2(commonFunction.setImg("hfrwl.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "恢复任务链"));
            commonFunction.RndCompare(0.8f, 891, 489, result, commonFunction.getImg());
            if (result.sim > 0.8f) {
                break;
            }
        }
    }

    //任务链1土豪
    public void taskChain1() throws Exception {
        int bj = 0, bj_1;
        int cs_1 = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
        bj_1 = bj;
        while (mFairy.condit()) {
            if (bj_1 == bj && (bj != 4)) {
                cs_1++;
            } else if (bj_1 != bj) {
                cs_1 = 0;
                bj_1 = bj;
                LtLog.e(commonFunction.getLineInfo("标记发生变化"));
            }
            if (cs_1 > 150) {
                cs_1 = 0;
                bj = 0;
                LtLog.e(commonFunction.getLineInfo("标记长时间未发生变化"));
            }
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("土豪任务链中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                gamePublicFunction.init();
                gamePublicFunction.SetJudgeTask("task");
                bj = 1;
            }
            if (bj == 1) {
                result = mFairy.findPic2(1017, 186, 1104, 510, commonFunction.setImg("yin_rwl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.7f, "任务链"));
                commonFunction.Compare(0.7f, result, commonFunction.getImg());
                if (result.sim > 0.7f) {
                    bj = 4;
                } else {
                    gamePublicFunction.BackCity(278, 385, "临仙镇");
                    Thread.sleep(2000);
                    result = mFairy.findPic2(107, 22, 187, 54, commonFunction.setImg("lxz.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "临仙"));
                    commonFunction.RndCompare(0.8f, result.x, result.y + 10, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 455, 206, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 1151, 24, result, commonFunction.getImg());
                    bj = 4;
                }
            }
            if (bj == 4) {
                result = mFairy.findPic2(1017, 186, 1104, 510, commonFunction.setImg("yin_rwl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.7f, "任务链"));
                if (result.sim > 0.7f) {
                    commonFunction.Compare(0.7f, result, commonFunction.getImg());
                    js_2 = 0;
                } else {
                    js_2++;
                    if (js_2 > 20) {
                        bj = 0;
                    }
                }

                result = mFairy.findPic2(59, 32, 137, 88, commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim > 0.8f) {
                    js_2 = 0;
                }

                result = mFairy.findPic2(1073, 7, 1276, 206, commonFunction.setImg("fork9.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "叉9"));
                if (result.sim > 0.8f) {
                    js_1++;
                    if (js_1 > 5) {
                        result1 = mFairy.findPic2(commonFunction.setImg("gybs.png"));
                        LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "雇佣帮手"));
                        if (result1.sim > 0.8f) {
                            js_5++;
                            if (js_5 > 3) {
                                TaskMain.task = 2042;
                                break;
                            }
                        }
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        js_1 = 0;
                    }
                } else {
                    js_1 = 0;
                }
                result = mFairy.findPic2(commonFunction.setImg("ysc.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "袁守城"));
                if (result.sim > 0.8f) {
                    commonFunction.RanSwipe(895, 435, 909, 622, 2, 2000);
                }

                result = mFairy.findPic2(785, 278, 1253, 717, commonFunction.setImg("lsrwl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "蓝色任务链"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(779, 56, 1257, 692, commonFunction.setImg("wflq.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "无法领取"));
                if (result.sim > 0.8f) {
                    break;
                }
                result = mFairy.findPic2(commonFunction.setImg("shijianbugoule.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "前往完成"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(788, 370, 1250, 711, commonFunction.setImg("rwllrw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "蓝色领任务"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(1201, 641, 1263, 678, commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(commonFunction.setImg("cwgy.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "宠物给予"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(commonFunction.setImg("zckq.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "再次开启任务链"));
                commonFunction.RndCompare(0.8f, 402, 491, result, commonFunction.getImg());

                result = commonFunction.FindManyPic(865, 577, 1090, 647, new String[]{"Bshop.png", "Bsdshop.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购买"));
                if (result.sim > 0.8f) {
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    gamePublicFunction.init();
                }

                result = mFairy.findPic2(commonFunction.setImg("MrLv.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "MrLv.png"));
                if (result.sim > 0.8f) {
                    commonFunction.RanSwipe(870, 417, 1107, 649, 2, 3000);
                }

                result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(395, 378, 1229, 682, commonFunction.setImg("give1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "给予"));
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 712, 178, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 801, 185, result, commonFunction.getImg());
                    commonFunction.RndCompare(0.8f, 883, 185, result, commonFunction.getImg());

                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                }

                result = mFairy.findPic2(1118, 217, 1174, 349, commonFunction.setImg("Stalls.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "右侧逛摊"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(245, 121, 482, 578, commonFunction.setImg("gtneed.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "需求"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic2(638, 600, 781, 657, commonFunction.setImg("Byjsj.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.1f, "切换到摊位列表"));
                        commonFunction.RndCompare(0.8f, 302, 95, result, commonFunction.getImg());

                        result1 = mFairy.findPic2(505, 98, 860, 574, commonFunction.setImg("need.png"));
                        LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "物品需求"));
                        commonFunction.RndCompare(0.8f, result1.x + 139, result1.y + 18, result1, commonFunction.getImg());
                        commonFunction.RndCompare(0.8f, 785, 636, result1, commonFunction.getImg());

                        result = mFairy.findPic2(439, 124, 488, 578, commonFunction.setImg("nowshop.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购物当前栏"));
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic2(result.x - 60, result.y - 10, result.x + 5, result.y + 40, commonFunction.setImg("gtneed.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "摊位列表需求"));
                        }

                        if (result.sim > 0.8f && result1.sim < 0.8f) {
                            commonFunction.RndCompare(992, 636, "没有需求物品下一页");
                            Thread.sleep(1500);
                        } else if (result.sim < 0.8f && result1.sim < 0.8f) {
                            gamePublicFunction.init();
                            result = mFairy.findPic2(1028, 186, 1275, 310, commonFunction.setImg("msyj.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.1f, "一本魔兽要诀"));
                            if (result.sim > 0.8f) {
                                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                                for (int j = 0; j < 10; j++) {
                                    result = mFairy.findPic2(1118, 217, 1174, 349, commonFunction.setImg("Stalls.png"));
                                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "右侧逛摊点市场"));
                                    commonFunction.RndCompare(0.8f, 1143, 141, result, commonFunction.getImg());

                                    result = mFairy.findPic2(162, 63, 355, 418, commonFunction.setImg("shichangcw.png"));
                                    LtLog.e(commonFunction.getLineInfo(result, 0.9f, "市场宠物"));
                                    commonFunction.Compare(0.9f, result, commonFunction.getImg());

                                    result = mFairy.findPic2(162, 63, 355, 418, commonFunction.setImg("dijiyaojue.png"));
                                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "低级要诀"));
                                    commonFunction.Compare(0.8f, result, commonFunction.getImg());

                                    result = mFairy.findPic2(348, 95, 685, 672, commonFunction.setImg("shoujuebs.png"));
                                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "兽决必杀"));
                                    if (result.sim > 0.8f) {
                                        commonFunction.RanSwipe(460, 128, 482, 608, 2, 1000);
                                        commonFunction.RanSwipe(460, 128, 482, 608, 2, 1000);
                                        commonFunction.RanSwipe(460, 128, 482, 608, 2, 1000);
                                        commonFunction.RanSwipe(460, 128, 482, 608, 2, 1000);
                                        commonFunction.RanSwipe(460, 128, 482, 608, 2, 1000);
                                        commonFunction.RanSwipe(460, 128, 482, 608, 2, 1000);
                                        commonFunction.RanSwipe(460, 128, 482, 608, 2, 1000);
                                        Thread.sleep(10000);
                                        result = mFairy.findPic2(348, 95, 685, 672, commonFunction.setImg("shoujuebs.png"));
                                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "兽决洪流"));
                                        commonFunction.RndCompare(0.8f, result.x + 155, result.y + 9, result1, commonFunction.getImg());
                                        commonFunction.RndCompare(0.8f, 1026, 610, result1, commonFunction.getImg());
                                        gamePublicFunction.init();
                                        break;
                                    }
                                }
                            } else {
                                break;
                            }
                        }

                        result = mFairy.findPic2(905, 46, 1059, 100, commonFunction.setImg("Bshuaxin.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "刷新"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());

                    }
                }


            }
            result = mFairy.findPic2(commonFunction.setImg("hfrwl.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "恢复任务链"));
            commonFunction.RndCompare(0.8f, 891, 489, result, commonFunction.getImg());
            if (result.sim > 0.8f) {
                break;
            }
        }
    }

    //总的任务
    public void ViewTask() throws Exception {
        int mbj = 0, mjs_2 = 0, mjs_3 = 0;

        List<String> list = new ArrayList<>();

        if (AtFairyConfig.getOption("sm").equals("1")) {
            list.add("smTask.png");
        }
        if (AtFairyConfig.getOption("bt").equals("1")) {
            list.add("rcbaotu.png");
        }
        if (AtFairyConfig.getOption("jjc").equals("1")) {
            list.add("rcarena.png");
        }
        if (AtFairyConfig.getOption("yxsl").equals("1")) {
            list.add("rchero.png");
        }
        if (AtFairyConfig.getOption("sczl").equals("1")) {
            list.add("scroad.png");
        }
        if (list.size() == 0) {
            LtLog.e(commonFunction.getLineInfo("没有任务了结束"));
            return;
        }
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("接取任务中mbj=" + mbj));
            if (mbj == 0) {
                gamePublicFunction.init();
                gamePublicFunction.SetJudgeTask("task");
                mjs_2 = 0;
                mjs_3 = 0;
                mbj = 1;
            }
            if (mbj == 1) {
                result = mFairy.findPic2(217, 4, 628, 85, commonFunction.setImg("Nricheng1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                commonFunction.delays(0.8f, result, 3000);
                result = mFairy.findPic2(222, 95, 1060, 413, commonFunction.setImg("cgldl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "炼丹炉"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(result.x, result.y - 70, result.x + 100, result.y, commonFunction.setImg("ldlhd.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "炼丹炉红点"));
                    if (result.sim > 0.8f) {
                        commonFunction.RndCompare(result.x - 30, result.y + 30, "炼丹炉");
                        for (int i = 0; i < 3; i++) {
                            result = mFairy.findPic2(449, 596, 611, 649, commonFunction.setImg("yjsq.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一键收取"));
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());

                            result = mFairy.findPic2(197, 590, 351, 648, commonFunction.setImg("yjlz.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一键炼制"));
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        }
                        result = mFairy.findPic2(197, 590, 351, 648, commonFunction.setImg("yjlz.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "关闭炼丹"));
                        commonFunction.RndCompare(0.8f, 1123, 44, result, commonFunction.getImg());
                    }
                }
                mbj = 2;
            }
            if (mbj == 2) {
                result = mFairy.findPic2(217, 4, 628, 85, commonFunction.setImg("Nricheng1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(550, 50, 734, 108, commonFunction.setImg("cghd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "常规活动"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(211, 92, 1086, 692, commonFunction.setImg(list.get(0)));
                    LtLog.e(commonFunction.getLineInfo(result, 0.7f, "找到任务" + list.get(0)));
                    if (result.sim > 0.96f) {
                        commonFunction.Compare(0.96f, result, commonFunction.getImg());
                        mbj = 3;
                    } else if (result.sim > 0.7f) {
                        LtLog.e(commonFunction.getLineInfo("任务完成"));
                        list.remove(0);
                        mjs_2 = 0;
                        if (list.size() == 0) {
                            LtLog.e(commonFunction.getLineInfo("没有任务了结束"));
                            break;
                        }
                    }
                    mjs_3 = 0;
                } else {
                    mjs_3++;
                    if (mjs_3 > 2) {
                        mbj = 0;
                    }
                }
                mjs_2++;
                LtLog.e(commonFunction.getLineInfo("js_2=" + mjs_2));
                if (mjs_2 >= 5) {
                    LtLog.e(commonFunction.getLineInfo("任务没有找到"));
                    list.remove(0);
                    mjs_2 = 0;
                    if (list.size() == 0) {
                        LtLog.e(commonFunction.getLineInfo("没有任务了结束"));
                        break;
                    }
                }
            }
            if (mbj == 3) {
                bj = 0;
                if (list.get(0).equals("smTask.png")) {
                    ShimenTask2();
                } else if (list.get(0).equals("rcbaotu.png")) {
                    Baotu();
                } else if (list.get(0).equals("rcarena.png")) {
                    Arena();
                } else if (list.get(0).equals("rchero.png")) {
                    HeroFa();
                    if (js_3 > 5) {
                        list.remove(0);
                        if (list.size() == 0) {
                            LtLog.e(commonFunction.getLineInfo("没有任务了结束"));
                            break;
                        }
                    }
                } else if (list.get(0).equals("scroad.png")) {
                    Silkroad();
                    if (TaskMain.task != 0) {
                        list.remove(0);
                        if (list.size() == 0) {
                            LtLog.e(commonFunction.getLineInfo("没有任务了结束"));
                            break;
                        }
                    }
                }
                mbj = 0;
            }
        }
    }
}




