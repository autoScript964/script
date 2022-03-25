package com.script.fairy;

import com.example.Answer;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class Util {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    FindResult result2;
    private Answer answer;
    public Util(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        answer = new Answer(ypFairy);
    }


    public  void Laojun() throws Exception {

//好爱网址 http://feng.suanst.com/

        String answerhui = answer.haoai(214,200,853,397,"5001");;

        LtLog.i(mFairy.getLineInfo("") + "答案是" + answerhui);
        if (answerhui.equals("1")) {
            LtLog.i(mFairy.getLineInfo("") + "答案是A");
            mFairy.onTap(327,523,367,548, "A", 1000);
        }
        if (answerhui.equals("2")) {
            LtLog.i(mFairy.getLineInfo("") + "答案是B");
            mFairy.onTap(528,528,561,549, "B", 1000);
        }
        LtLog.i(mFairy.getLineInfo("") + "答案是=" + answerhui);
        if (answerhui.equals("3")) {
            LtLog.i(mFairy.getLineInfo("") + "答案是c");
            mFairy.onTap(727,524,775,551, "C", 1000);
        }
        if (answerhui.equals("4")) {
            LtLog.i(mFairy.getLineInfo("") + "答案是d");
            mFairy.onTap(909,528,955,551, "d", 1000);
        }

    }

    //计次关闭
    int fork = 0;

    public void closeCount() throws Exception {
        result = mFairy.findPic(369, 10, 1268, 588, new String[]{"errorfork1.png", "errorfork2.png"});
        if (result.sim > 0.8f) {
            fork++;
            if (fork > 4) {
                mFairy.onTap(0.8f, result, "关闭", 1000);
                fork = 0;
            }
        } else {
            fork = 0;
        }
    }


    //直接关闭
    public void close() throws Exception {
        mFairy.findPic(369, 10, 1268, 588, 0.8f, new String[]{"errorfork1.png", "errorfork2.png"});
        mFairy.findPic(369, 10, 1268, 588, 0.8f, new String[]{"errorfork1.png", "errorfork2.png"});
    }

    //找任务

    public int findTask(String type, String strpic, String look) throws Exception {
        int bj = 0;
        int hdcount = 0;
        while (mFairy.condit()) {
            if (bj == 0) {
                result = mFairy.findPic(564, 4, 708, 73, "activity1.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "活动界面场景"));
                if (result.sim > 0.8f) {
                    if (type.equals("日常")) {
                        mFairy.onTap(0.8f, result, 230, 146, 267, 160, "日常", 1000);
                    }
                    if (type.equals("限时")) {
                        mFairy.onTap(0.8f, result, 220, 210, 266, 225, "限时", 1000);
                    }
                    if (type.equals("福利")) {
                        mFairy.onTap(0.8f, result, 223, 274, 276, 291, "福利", 1000);
                    }
                    if (type.equals("其他")) {
                        mFairy.onTap(0.8f, result, 224, 341, 264, 355, "其他", 1000);
                    }
                    result = mFairy.findPic(428, 525, 457, 618, "active_reward.png");
                    if (result.sim < 0.8f) {
                        mFairy.onTap(463, 558, 489, 586, "有奖励", 1000);
                    }
                    result = mFairy.findPic(572, 521, 594, 618, "active_reward.png");
                    if (result.sim < 0.8f) {
                        mFairy.onTap(612, 561, 632, 577, "有奖励", 1000);
                    }
                    result = mFairy.findPic(711, 524, 740, 617, "active_reward.png");
                    if (result.sim < 0.8f) {
                        mFairy.onTap(744, 564, 775, 585, "有奖励", 1000);
                    }
                    result = mFairy.findPic(856, 520, 881, 619, "active_reward.png");
                    if (result.sim < 0.8f) {
                        mFairy.onTap(891, 563, 911, 582, "有奖励", 1000);
                    }
                    result = mFairy.findPic(996, 522, 1017, 620, "active_reward.png");
                    if (result.sim < 0.8f) {
                        mFairy.onTap(1021, 564, 1044, 585, "有奖励", 1000);
                    }
                    for (int i = 0; i < 5; i++) {
                        result = mFairy.findPic("used.png");
                        mFairy.onTap(0.8f, result, "使用", 1000);
                    }
                    bj = 1;
                } else {
                    close();
                    result = mFairy.findPic(214, 5, 534, 82, "activity.png");
                    mFairy.onTap(0.8f, result, "主界面活动", 1000);
                }

            }
            if (bj == 1) {
                result = mFairy.findPic(214, 5, 534, 82, "activity.png");
                mFairy.onTap(0.8f, result, "主界面活动", 1000);

                result = mFairy.findPic(564, 4, 708, 73, "activity1.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "活动界面场景"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(336, 123, 1102, 449, strpic);
                    LtLog.e(mFairy.getLineInfo(0.8f, result, "找到任务" + strpic));
                    if (result.sim > 0.8f) {
                        if (strpic.equals("rwsd.png")) {
                            result1 = mFairy.findPic(result.x + 196, result.y + 35, result.x + 242, result.y + 75, "sdhy.png");
                            LtLog.e(mFairy.getLineInfo(0.9f, result1, "刷道活跃满"));
                            if (result1.sim > 0.9f) {
                                close();
                                return 0;
                            }
                        }
                        result = mFairy.findPic(result.x + 175, result.y - 10, result.x + 275, result.y + 50, "go.png");
                        if (look.equals("看任务")) {

                        } else if (look.equals("前往")) {
                            mFairy.onTap(0.8f, result, "前往", 1000);
                        }
                        if (result.sim > 0.8f) {
                            return 1;
                        } else {
                            close();
                            return 0;
                        }
                    } else {
                        hdcount++;
                        if (hdcount == 2 || hdcount == 4 || hdcount == 6 || hdcount == 8) {
                            mFairy.ranSwipe(534, 376,522, 158,  1000, (long)3000,2);
                            LtLog.e(mFairy.getLineInfo("没有找到滑动一下"));
                            Thread.sleep(1000);
                        }
                        if (hdcount >= 10) {
                            result = mFairy.findPic(564, 4, 708, 73, "activity1.png");
                            mFairy.onTap(0.8f, result, 1092, 38, 1107, 50, "没有找到当前活动关闭活动界面", 1000);
                            return 0;
                        }
                    }
                } else {
                    close();
                }
            }
        }
        return 0;
    }


    //战斗场景
    int battleCount = 0;
    boolean battleSign = false;

    public int combat() throws Exception {
        result = mFairy.findPic("combat.png");
        LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中,battleCount=" + battleCount));
        if (result.sim > 0.8f) {
            Thread.sleep(5000);
            mFairy.initMatTime();
            battleSign = true;
        } else {
            if (battleSign == true) {
                battleCount++;
                battleSign = false;
            }
        }
        return battleCount;
    }

    //退队
    public void teamRanks(String string) throws Exception {
        LtLog.e(mFairy.getLineInfo("执行队伍操作"));
        mFairy.condit();
        result = mFairy.findPic(555, 1, 726, 66, "Tduiwu.png");
        LtLog.e(mFairy.getLineInfo(0.8f, result, "队伍界面"));
        if (result.sim > 0.8f) {
        } else {
            close();
        }
        for (int i = 0; i < 5; i++) {
            mFairy.condit();
            result = mFairy.findPic("combat.png");
            mFairy.onTap(0.8f, result, 22,87,29,97,"战斗打开扩展栏", 1000);

            result = mFairy.findPic(2,61,320,328,"zdduiwu.png");
            mFairy.onTap(0.8f, result, 22,87,29,97,"战斗队伍", 1000);

            result = mFairy.findPic(974, 91, 1280, 233, "right_team.png");
            mFairy.onTap(0.9f, result, "右侧组队亮", 1000);
            result = mFairy.findPic(974, 91, 1280, 233, "right_team1.png");
            mFairy.onTap(0.9f, result, "右侧组队暗", 1000);

            if (string.equals("退队")) {
                result = mFairy.findPic(170, 615, 359, 674, "lkdw.png");
                mFairy.onTap(0.8f, result, "离开队伍", 2000);


                result = mFairy.findPic(178, 604, 365, 673, "ancjdw.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "一个人"));
                if (result.sim > 0.8f) {
                    return;
                }
            }
            if (string.equals("队长")) {
                result = mFairy.findPic(178, 604, 365, 673, "ancjdw.png");
                mFairy.onTap(0.8f, result, "创建队伍", 2000);

                result = mFairy.findPic(170, 615, 359, 674, "lkdw.png");
                result1 = mFairy.findPic(924, 618, 1111, 669, "zldw.png");
                result2 = mFairy.findPic("hddw.png");
                if (result.sim > 0.8f && result1.sim < 0.8f && result2.sim < 0.8f) {
                    LtLog.e(mFairy.getLineInfo("是队长"));
                    return;
                } else if (result.sim > 0.8f && (result1.sim > 0.8f || result2.sim > 0.8f)) {
                    LtLog.e(mFairy.getLineInfo("是队员"));
                    result = mFairy.findPic(170, 615, 359, 674, "lkdw.png");
                    mFairy.onTap(0.8f, result, "离开队伍", 2000);
                }
            }
            if (string.equals("队员")) {
                result = mFairy.findPic(178, 604, 365, 673, "ancjdw.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "一个人"));
                if (result.sim > 0.8f) {
                    return;
                }

                result = mFairy.findPic(170, 615, 359, 674, "lkdw.png");
                result1 = mFairy.findPic(924, 618, 1111, 669, "zldw.png");
                result2 = mFairy.findPic("hddw.png");
                if (result.sim > 0.8f && result1.sim < 0.8f && result2.sim < 0.8f) {
                    LtLog.e(mFairy.getLineInfo("是队长"));
                    result = mFairy.findPic(170, 615, 359, 674, "lkdw.png");
                    mFairy.onTap(0.8f, result, "离开队伍", 2000);
                } else if (result.sim > 0.8f && (result1.sim > 0.8f || result2.sim > 0.8f)) {
                    LtLog.e(mFairy.getLineInfo("是队员"));
                    return;
                }
            }
            if (string.equals("暂离")) {
                result = mFairy.findPic(924, 618, 1111, 669, "zldw.png");
                mFairy.onTap(0.8f, result, "暂离队伍", 2000);
                if (result.sim > 0.8f) {
                    close();
                    return;
                }
            }
            if (string.equals("回归")) {
                result = mFairy.findPic(924, 618, 1111, 669, "hddw.png");
                mFairy.onTap(0.8f, result, "回归队伍", 2000);
                if (result.sim > 0.8f) {
                    close();
                    return;
                }
            }
            result = mFairy.findPic(396, 231, 886, 481, "sflkdw.png");
            mFairy.onTap(0.8f, result, 744, 432, 745, 433, "确认离开队伍....", 1000);
        }
    }

    //暂离回归队伍
    public void teamleave(String string) throws Exception {
        LtLog.e(mFairy.getLineInfo("执行暂离回归队伍操作"));
        for (int i = 0; i < 5; i++) {
            mFairy.condit();
            result = mFairy.findPic("combat.png");
            mFairy.onTap(0.8f, result, 22,87,29,97,"战斗打开扩展栏", 1000);
            if (result.sim > 0.8f) {
                i=0;
            }

            mFairy.condit();
            result = mFairy.findPic(974, 91, 1280, 233, "right_team.png");
            mFairy.onTap(0.9f, result, "右侧组队亮", 1000);
            result = mFairy.findPic(974, 91, 1280, 233, "right_team1.png");
            mFairy.onTap(0.9f, result, "右侧组队暗", 1000);

            if (string.equals("退队")) {
                result = mFairy.findPic(170, 615, 359, 674, "lkdw.png");
                mFairy.onTap(0.8f, result, "离开队伍", 2000);


                result = mFairy.findPic(178, 604, 365, 673, "ancjdw.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "一个人"));
                if (result.sim > 0.8f) {
                    return;
                }
            }
            if (string.equals("队长")) {
                result = mFairy.findPic(178, 604, 365, 673, "ancjdw.png");
                mFairy.onTap(0.8f, result, "创建队伍", 2000);

                result = mFairy.findPic(170, 615, 359, 674, "lkdw.png");
                result1 = mFairy.findPic(924, 618, 1111, 669, "zldw.png");
                result2 = mFairy.findPic("hddw.png");
                if (result.sim > 0.8f && result1.sim < 0.8f && result2.sim < 0.8f) {
                    LtLog.e(mFairy.getLineInfo("是队长"));
                    return;
                } else if (result.sim > 0.8f && (result1.sim > 0.8f || result2.sim > 0.8f)) {
                    LtLog.e(mFairy.getLineInfo("是队员"));
                    result = mFairy.findPic(170, 615, 359, 674, "lkdw.png");
                    mFairy.onTap(0.8f, result, "离开队伍", 2000);
                }
            }
            if (string.equals("队员")) {
                result = mFairy.findPic(178, 604, 365, 673, "ancjdw.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "一个人"));
                if (result.sim > 0.8f) {
                    return;
                }

                result = mFairy.findPic(170, 615, 359, 674, "lkdw.png");
                result1 = mFairy.findPic(924, 618, 1111, 669, "zldw.png");
                result2 = mFairy.findPic("hddw.png");
                if (result.sim > 0.8f && result1.sim < 0.8f && result2.sim < 0.8f) {
                    LtLog.e(mFairy.getLineInfo("是队长"));
                    result = mFairy.findPic(170, 615, 359, 674, "lkdw.png");
                    mFairy.onTap(0.8f, result, "离开队伍", 2000);
                } else if (result.sim > 0.8f && (result1.sim > 0.8f || result2.sim > 0.8f)) {
                    LtLog.e(mFairy.getLineInfo("是队员"));
                    return;
                }
            }

            result = mFairy.findPic(396, 231, 886, 481, "sflkdw.png");
            mFairy.onTap(0.8f, result, 744, 432, 745, 433, "确认离开队伍....", 1000);
        }
    }
    //领取双倍
    public void receiveDouble() throws Exception {
        int bj = 0;
        for (int i = 0; i < 10; i++) {
            LtLog.e(mFairy.getLineInfo("领取双倍中"));
            mFairy.condit();
            if (bj == 0) {
                result = mFairy.findPic("patrol.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "巡逻界面"));
                if (result.sim > 0.8f) {
                    bj = 1;
                } else {
                    close();
                }
                result = mFairy.findPic(214, 5, 534, 82, "patrol1.png");
                mFairy.onTap(0.8f, result, "点开巡逻图标", 1000);
            }
            if (bj == 1) {
                result = mFairy.findPic("patrol.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "巡逻界面"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(347, 622, 431, 665, "unkaiqi.png");
                    mFairy.onTap(0.8f, result, "开启双倍", 1000);
                    close();
                    break;
                }
            }

        }
    }

    //关闭双倍
    public void closeDouble() throws Exception {
        int bj = 0;
        for (int i = 0; i < 10; i++) {
            LtLog.e(mFairy.getLineInfo("关闭双倍中"));
            mFairy.condit();
            if (bj == 0) {
                result = mFairy.findPic("patrol.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "巡逻界面"));
                if (result.sim > 0.8f) {
                    bj = 1;
                } else {
                    close();
                }
                result = mFairy.findPic(214, 5, 534, 82, "patrol1.png");
                mFairy.onTap(0.8f, result, "点开巡逻图标", 1000);
            }
            if (bj == 1) {
                result = mFairy.findPic("patrol.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "巡逻界面"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(347, 622, 431, 665, "unkaiqi.png");
                    if (result.sim > 0.8f) {
                    } else {
                        mFairy.onTap(371, 640, 403, 653, "关闭双倍", 1000);
                    }
                    break;

                }
            }

        }
        close();
    }

    //加点设置
    public void addLittleSet() throws Exception {
        int bj = 0;
        for (int i = 0; i < 20; i++) {
            mFairy.condit();
            if (bj == 0) {
                result = mFairy.findPic("add_little.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "属性加点界面"));
                if (result.sim > 0.8f) {
                    bj = 1;
                } else {
                    close();
                }

                result = mFairy.findPic(214, 5, 534, 82, "activity.png");
                mFairy.onTap(0.8f, result, 1132, 38, 1156, 61, "主界面活动点击角色", 1000);

                result = mFairy.findPic("add_spot.png");
                mFairy.onTap(0.8f, result, "点击加点", 1000);

            }
            if (bj == 1) {
                result = mFairy.findPic(592, 77, 782, 126, new String[]{"attribute1.png", "attribute.png"});
                mFairy.onTap(0.8f, result, "切换到属性点", 2000);
                mFairy.onTap(0.8f, result, 984, 174, 1022, 191, "自动加点", 1000);

                result = mFairy.findPic("add_plan.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 640, 139, 675, 153, "方案", 1000);
                    if (AtFairyConfig.getOption("jdfa").equals("1")) {
                        mFairy.onTap(0.8f, result, 626, 208, 676, 224, "自定义", 1000);
                        //初始化加点
                        for (int i1 = 0; i1 < 5; i1++) {
                            mFairy.tap(558, 223);
                            mFairy.tap(562, 319);
                            mFairy.tap(558, 410);
                            mFairy.tap(563, 501);
                        }
                        if (!AtFairyConfig.getOption("tz").equals("")) {
                            for (int i1 = 0; i1 < Integer.parseInt(AtFairyConfig.getOption("tz")); i1++) {
                                mFairy.onTap(0.8f, result, 756, 215, 771, 227, "体质", 1000);
                            }
                        }
                        if (!AtFairyConfig.getOption("lingl").equals("")) {
                            for (int i1 = 0; i1 < Integer.parseInt(AtFairyConfig.getOption("lingl")); i1++) {
                                mFairy.onTap(0.8f, result, 754, 310, 772, 324, "灵力", 1000);
                            }
                        }
                        if (!AtFairyConfig.getOption("ll").equals("")) {
                            for (int i1 = 0; i1 < Integer.parseInt(AtFairyConfig.getOption("ll")); i1++) {
                                mFairy.onTap(0.8f, result, 755, 403, 768, 420, "力量", 1000);
                            }
                        }
                        if (!AtFairyConfig.getOption("mj").equals("")) {
                            for (int i1 = 0; i1 < Integer.parseInt(AtFairyConfig.getOption("mj")); i1++) {
                                mFairy.onTap(0.8f, result, 757, 496, 772, 512, "敏捷", 1000);
                            }
                        }
                    }
                    if (AtFairyConfig.getOption("jdfa").equals("2")) {
                        mFairy.onTap(0.8f, result, 635, 279, 694, 300, "高物伤", 1000);
                    }
                    if (AtFairyConfig.getOption("jdfa").equals("3")) {
                        mFairy.onTap(0.8f, result, 642, 352, 705, 372, "高法伤", 1000);
                    }
                }
                result = mFairy.findPic(428, 568, 542, 628, "auto_close.png");
                mFairy.onTap(0.8f, result, "加点没有开启自动", 1000);

                result = mFairy.findPic("add_plan.png");
                mFairy.onTap(0.8f, result, 745, 586, 794, 604, "保存", 1000);
                mFairy.onTap(0.8f, result, 848, 70, 869, 89, "关闭", 1000);

                result = mFairy.findPic(592, 77, 782, 126, new String[]{"attribute1.png", "attribute.png"});
                mFairy.onTap(0.8f, result, 841, 94, 877, 108, "切换到相性点", 2000);
                mFairy.onTap(0.8f, result, 984, 174, 1022, 191, "自动加点", 1000);
                result = mFairy.findPic("add_plan.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 640, 139, 675, 153, "方案", 1000);
                    if (AtFairyConfig.getOption("xxjdfa").equals("1")) {
                        mFairy.onTap(0.8f, result, 626, 208, 676, 224, "自定义", 1000);
                        if (!AtFairyConfig.getOption("yxjm").equals("")) {
                            mFairy.onTap(0.8f, result, 799, 232, 813, 250, "优先加满下拉", 1000);
                            if (AtFairyConfig.getOption("yxjm").equals("1")) {
                                mFairy.onTap(0.8f, result, 663, 297, 692, 311, "金", 1000);
                            }
                            if (AtFairyConfig.getOption("yxjm").equals("2")) {
                                mFairy.onTap(0.8f, result, 661, 358, 700, 373, "木", 1000);
                            }
                            if (AtFairyConfig.getOption("yxjm").equals("3")) {
                                mFairy.onTap(0.8f, result, 664, 424, 694, 442, "水", 1000);
                            }
                            if (AtFairyConfig.getOption("yxjm").equals("4")) {
                                mFairy.onTap(0.8f, result, 666, 488, 703, 506, "火", 1000);
                            }
                            if (AtFairyConfig.getOption("yxjm").equals("5")) {
                                mFairy.onTap(0.8f, result, 660, 551, 700, 568, "土", 1000);
                            }
                        }
                        if (!AtFairyConfig.getOption("rhjm").equals("")) {
                            mFairy.onTap(0.8f, result, 799, 334, 814, 348, "然后加满下拉", 1000);
                            if (AtFairyConfig.getOption("rhjm").equals("1")) {
                                mFairy.onTap(0.8f, result, 671, 398, 705, 415, "金", 1000);
                            }
                            if (AtFairyConfig.getOption("rhjm").equals("2")) {
                                mFairy.onTap(0.8f, result, 671, 458, 706, 475, "木", 1000);
                            }
                            if (AtFairyConfig.getOption("rhjm").equals("3")) {
                                mFairy.onTap(0.8f, result, 667, 516, 713, 541, "水", 1000);
                            }
                            if (AtFairyConfig.getOption("rhjm").equals("4")) {
                                mFairy.onTap(0.8f, result, 670, 589, 700, 605, "火", 1000);
                            }
                            if (AtFairyConfig.getOption("rhjm").equals("5")) {
                                mFairy.onTap(0.8f, result, 668, 651, 695, 670, "土", 1000);
                            }
                        }
                        if (!AtFairyConfig.getOption("zhjm").equals("")) {
                            mFairy.onTap(0.8f, result, 794, 435, 813, 454, "最后再加下拉", 1000);
                            if (AtFairyConfig.getOption("zhjm").equals("1")) {
                                mFairy.onTap(0.8f, result, 663, 124, 699, 142, "金", 1000);
                            }
                            if (AtFairyConfig.getOption("zhjm").equals("2")) {
                                mFairy.onTap(0.8f, result, 669, 187, 703, 204, "木", 1000);
                            }
                            if (AtFairyConfig.getOption("zhjm").equals("3")) {
                                mFairy.onTap(0.8f, result, 675, 249, 703, 269, "水", 1000);
                            }
                            if (AtFairyConfig.getOption("zhjm").equals("4")) {
                                mFairy.onTap(0.8f, result, 665, 313, 693, 334, "火", 1000);
                            }
                            if (AtFairyConfig.getOption("zhjm").equals("5")) {
                                mFairy.onTap(0.8f, result, 668, 377, 693, 394, "土", 1000);
                            }
                        }
                    }
                    if (AtFairyConfig.getOption("xxjdfa").equals("2")) {
                        mFairy.onTap(0.8f, result, 635, 279, 694, 300, "高物伤", 1000);
                    }
                    if (AtFairyConfig.getOption("xxjdfa").equals("3")) {
                        mFairy.onTap(0.8f, result, 642, 352, 705, 372, "高法伤", 1000);
                    }
                }

                result = mFairy.findPic(428, 568, 542, 628, "auto_close.png");
                mFairy.onTap(0.8f, result, "加点没有开启自动", 1000);

                result = mFairy.findPic("add_plan.png");
                mFairy.onTap(0.8f, result, 745, 586, 794, 604, "保存", 1000);
                mFairy.onTap(0.8f, result, 848, 70, 869, 89, "关闭", 1000);
                close();
                break;
            }

        }
    }

    //技能设置
    public void skillSet() throws Exception {
        int bj = 0;
        for (int i = 0; i < 20; i++) {
            mFairy.condit();
            if (bj == 0) {
                result = mFairy.findPic("patrol.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "巡逻界面"));
                if (result.sim > 0.8f) {
                    bj = 1;
                } else {
                    close();
                }
                result = mFairy.findPic(214,5,534,82, "patrol1.png");
                mFairy.onTap(0.8f, result, "点开巡逻图标", 1000);
            }
            if (bj == 1) {
                result = mFairy.findPic("patrol.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "巡逻界面"));
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 601, 558, 642, 574, "人物技能设置", 1000);
                    //技能种类
                    if (AtFairyConfig.getOption("jnzl").equals("1")) {
                        //哪个技能
                        if (AtFairyConfig.getOption("jineng").equals("1")) {
                            mFairy.onTap(0.8f, result, 788,361,804,375, "力1", 1000);
                        }
                        if (AtFairyConfig.getOption("jineng").equals("2")) {
                            mFairy.onTap(0.8f, result, 875,357,895,375, "力2", 1000);
                        }
                        if (AtFairyConfig.getOption("jineng").equals("3")) {
                            mFairy.onTap(0.8f, result, 966,365,985,378, "力3", 1000);
                        }
                    }
                    if (AtFairyConfig.getOption("jnzl").equals("2")) {
                        //哪个技能
                        if (AtFairyConfig.getOption("jineng").equals("4")) {
                            mFairy.onTap(0.8f, result, 777,274,803,289, "法1", 1000);
                        }
                        if (AtFairyConfig.getOption("jineng").equals("5")) {
                            mFairy.onTap(0.8f, result, 873,269,894,292, "法2", 1000);
                        }
                        if (AtFairyConfig.getOption("jineng").equals("6")) {
                            mFairy.onTap(0.8f, result, 961,270,984,289, "法3", 1000);
                        }
                        if (AtFairyConfig.getOption("jineng").equals("7")) {
                            mFairy.onTap(0.8f, result, 1046,268,1072,285, "法4", 1000);
                        }
                    }
                    if (AtFairyConfig.getOption("jnzl").equals("3")) {
                        //哪个技能
                        if (AtFairyConfig.getOption("jineng").equals("4")) {
                            mFairy.onTap(0.8f, result, 775,183,798,198, "障1", 1000);
                        }
                        if (AtFairyConfig.getOption("jineng").equals("5")) {
                            mFairy.onTap(0.8f, result, 874,178,896,190, "障2", 1000);
                        }
                        if (AtFairyConfig.getOption("jineng").equals("6")) {
                            mFairy.onTap(0.8f, result, 975,183,996,198, "障3", 1000);
                        }
                        if (AtFairyConfig.getOption("jineng").equals("7")) {
                            mFairy.onTap(0.8f, result, 1058,181,1081,195, "障4", 1000);
                        }
                    }
                    if (AtFairyConfig.getOption("jnzl").equals("4")) {
                        //哪个技能
                        if (AtFairyConfig.getOption("jineng").equals("4")) {
                            mFairy.onTap(0.8f, result, 783,88,805,106, "辅1", 1000);
                        }
                        if (AtFairyConfig.getOption("jineng").equals("5")) {
                            mFairy.onTap(0.8f, result, 869,88,889,103, "辅2", 1000);
                        }
                        if (AtFairyConfig.getOption("jineng").equals("6")) {
                            mFairy.onTap(0.8f, result, 956,81,978,100, "辅3", 1000);
                        }
                        if (AtFairyConfig.getOption("jineng").equals("7")) {
                            mFairy.onTap(0.8f, result, 1057,88,1075,105, "辅4", 1000);
                        }
                    }
                    mFairy.onTap(0.8f, result, 895, 554, 952, 575, "宠物技能设置", 1000);
                    if (AtFairyConfig.getOption("cwjnzl").equals("1")) {
                        //哪个技能
                        if (AtFairyConfig.getOption("cwjineng").equals("1")) {
                            mFairy.onTap(0.8f, result, 697,302,715,323, "宠物力1", 1000);
                        }
                        if (AtFairyConfig.getOption("cwjineng").equals("2")) {
                            mFairy.onTap(0.8f, result, 785,303,804,320, "宠物力2", 1000);
                        }
                    }
                    if (AtFairyConfig.getOption("cwjnzl").equals("2")) {
                        //哪个技能
                        if (AtFairyConfig.getOption("cwjineng").equals("3")) {
                            mFairy.onTap(0.8f, result, 701,213,722,228, "宠物法1", 1000);
                        }
                        if (AtFairyConfig.getOption("cwjineng").equals("4")) {
                            mFairy.onTap(0.8f, result, 787,217,808,230, "宠物法2", 1000);
                        }
                        if (AtFairyConfig.getOption("cwjineng").equals("5")) {
                            mFairy.onTap(0.8f, result, 882,216,899,230, "宠物法3", 1000);
                        }
                    }
                    break;
                }

            }
        }
        close();
    }

    //回城
    public void backCity() throws Exception {
        int bj = 0;
        for (int i = 0; i < 10; i++) {
            LtLog.e(mFairy.getLineInfo("回城中"));
            mFairy.condit();
            result = mFairy.findPic("combat.png");
            LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
            if (result.sim > 0.8f) {
                Thread.sleep(5000);
                 i=0;
            }
            if (bj == 0) {
                result = mFairy.findPic(214, 5, 534, 82, "activity.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "活动"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("tianyongcheng.png");
                    LtLog.e(mFairy.getLineInfo(0.8f, result, "天墉城"));
                    if (result.sim > 0.8f) {
                        mFairy.tap(597,378);
                        close();
                        return;
                    }
                    bj = 1;
                } else {
                    close();
                }
            }
            if (bj == 1) {
                result = mFairy.findPic(214, 5, 534, 82, "activity.png");
                mFairy.onTap(0.8f, result, 29, 37, 55, 53, "打开地图", 1000);

                result = mFairy.findPic("ditujm.png");
                mFairy.onTap(0.8f, result, 480, 413, 515, 437, "地图界面去天墉城", 1000);
                if (result.sim > 0.8f) {
                    close();
                    close();
                    bj=0;
                }
            }

        }
    }

    //带队或者跟队
    public int  leadFollowTeam(String string, String strTarget, String strParent, String strSub) throws Exception {
        long hhtime = 0;
        for (int i=0;i<100;i++){
            mFairy.condit();
            result = mFairy.findPic(555, 1, 726, 66, "Tduiwu.png");
            LtLog.e(mFairy.getLineInfo(0.8f, result, "队伍界面"));
            if (result.sim > 0.8f) {
                teamActivities(strTarget, strParent, strSub);
            } else {
                close();
                result = mFairy.findPic(974, 91, 1280, 233, "right_team.png");
                mFairy.onTap(0.9f, result, "右侧组队亮", 1000);

                result = mFairy.findPic(974, 91, 1280, 233, "right_team1.png");
                mFairy.onTap(0.9f, result, "右侧组队暗", 1000);
            }
            if (string.equals("带队")) {

                //一键喊话快速找人
                if (System.currentTimeMillis() - hhtime > 120000) {
                    result = mFairy.findPic(291, 58, 843, 131, "yjhh.png");
                    mFairy.onTap(0.8f, result, "一键喊话", 1000);
                    hhtime = System.currentTimeMillis();
                }
                result = mFairy.findPic(587, 63, 649, 108, "point.png");
                mFairy.onTap(0.8f, result, 548, 100, 549, 101, "点击申请表", 1000);
                if (result.sim > 0.8f) {
                    for (int i1 = 0; i1 < 5; i1++) {
                        result = mFairy.findPic(508, 217, 1096, 304, "need.png");
                        mFairy.onTap(0.8f, result, "点击对勾", 1000);
                    }
                    result = mFairy.findPic(555, 1, 726, 66, "Tduiwu.png");
                    mFairy.onTap(0.8f, result, 192, 90, 283, 107, "切换我的队伍", 1000);
                }
                int peop = pNumTeam();
                if (peop >= 3) {
                    close();
                    return 1;
                }
            }
            if (string.equals("跟队")) {
                result = mFairy.findPic("combat.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                if (result.sim > 0.8f) {
                    close();
                    return 1;
                }
                result = mFairy.findPic("hddw.png");
                mFairy.onTap(0.8f, result, "回到队伍", 1000);
                if (result.sim > 0.8f) {
                   close();
                    return 1;
                }

                result = mFairy.findPic(924, 618, 1111, 669, "zldw.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "跟队成功"));
                if (result.sim > 0.8f) {
                    close();
                    return 1;
                }
            }
        }
        return 0;
    }

    //带队或者跟队不需要判定组队人数
    public int  leadFollowTeam1(String string, String strTarget, String strParent, String strSub) throws Exception {
        long hhtime = 0;
        for (int i=0;i<100;i++){
            mFairy.condit();
            result = mFairy.findPic(555, 1, 726, 66, "Tduiwu.png");
            LtLog.e(mFairy.getLineInfo(0.8f, result, "队伍界面"));
            if (result.sim > 0.8f) {
                teamActivities(strTarget, strParent, strSub);
            } else {
                close();
                result = mFairy.findPic(974, 91, 1280, 233, "right_team.png");
                mFairy.onTap(0.9f, result, "右侧组队亮", 1000);

                result = mFairy.findPic(974, 91, 1280, 233, "right_team1.png");
                mFairy.onTap(0.9f, result, "右侧组队暗", 1000);
            }
            if (string.equals("带队")) {

                //一键喊话快速找人
                if (System.currentTimeMillis() - hhtime > 120000) {
                    result = mFairy.findPic(291, 58, 843, 131, "yjhh.png");
                    mFairy.onTap(0.8f, result, "一键喊话", 1000);
                    hhtime = System.currentTimeMillis();
                }
                result = mFairy.findPic(587, 63, 649, 108, "point.png");
                mFairy.onTap(0.8f, result, 548, 100, 549, 101, "点击申请表", 1000);
                if (result.sim > 0.8f) {
                    for (int i1 = 0; i1 < 5; i1++) {
                        result = mFairy.findPic(508, 217, 1096, 304, "need.png");
                        mFairy.onTap(0.8f, result, "点击对勾", 1000);
                    }
                    result = mFairy.findPic(555, 1, 726, 66, "Tduiwu.png");
                    mFairy.onTap(0.8f, result, 192, 90, 283, 107, "切换我的队伍", 1000);
                }
                int peop = pNumTeam();
                if (peop >= 0) {
                    close();
                    return 1;
                }
            }
            if (string.equals("跟队")) {
                result = mFairy.findPic("combat.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                if (result.sim > 0.8f) {
                    close();
                    return 1;
                }
                result = mFairy.findPic("hddw.png");
                mFairy.onTap(0.8f, result, "回到队伍", 1000);
                if (result.sim > 0.8f) {
                    close();
                    return 1;
                }

                result = mFairy.findPic(924, 618, 1111, 669, "zldw.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "跟队成功"));
                if (result.sim > 0.8f) {
                    close();
                    return 1;
                }
            }
        }
        return 0;
    }


    //队伍活动选择
    public void teamActivities(String strTarget, String strParent, String strSub) throws Exception {
        //"target_xy.png""shuadao.png""xiangyao.png"
        result = mFairy.findPic(164, 140, 414, 194, strTarget);
        LtLog.e(mFairy.getLineInfo(0.9f, result, "目标活动存在"));
        if (result.sim < 0.9f) {
            result = mFairy.findPic(905, 132, 1108, 194, "cancel_match.png");
            mFairy.onTap(0.8f, result, "队伍界面取消匹配", 1000);

            result = mFairy.findPic("activity2.png");
            mFairy.onTap(0.8f, result, "打开活动选择界面", 2000);
            if (result.sim > 0.8f) {
                result = mFairy.findPic(155, 36, 610, 687, strParent);
                mFairy.onTap(0.8f, result, "找到活动父类", 2000);
                if (result.sim>0.8f){
                    if (strSub.equals("bxwl1.png") ||  strSub.equals("hdrq1.png")||  strSub.equals("fxdx1j.png")||  strSub.equals("fxdx2j.png")){
                        result = mFairy.findPic(926, 617, 1098, 673, "autopp.png");
                        if (result.sim>0.8f){
                            mFairy.ranSwipe(280,524,261,210, 1500, (long) 4000,2);
                        }else {
                            mFairy.ranSwipe(463,525,447,201, 1500, (long) 4000,2);
                        }
                    }
                }
                if (!strSub.equals("")){
                    result = mFairy.findPic(155, 36, 610, 687, strSub);
                    mFairy.onTap(0.9f, result, "找到活动子类", 1000);
                }

                result = mFairy.findPic(926, 617, 1098, 673, "autopp.png");
                mFairy.onTap(0.8f, result, "跟队自动匹配", 1000);

                result = mFairy.findPic(653, 615, 917, 665, "kspp.png");
                mFairy.onTap(0.8f, result, "队长队伍信息开始匹配", 1000);
            }
        } else {
            result = mFairy.findPic(906, 137, 1102, 196, "kspp.png");
            mFairy.onTap(0.8f, result, "活动目标正确开始匹配", 1000);
            LtLog.e(mFairy.getLineInfo("队伍匹配中"));
            Thread.sleep(5000);
        }

    }

    //队伍人数判断
    long zlTime1 = 0, zlTime2 = 0, zlTime3 = 0, zlTime4 = 0;
    int zlSign1 = 0, zlSign2 = 0, zlSign3 = 0, zlSign4 = 0;

    public int pNumTeam() throws Exception {
        int num = 1;
        //result = mFairy.findPic(349, 212, 535, 590, new String[]{"peoplenum.png","peoplenum1.png","peoplenum2.png"});
        result = mFairy.findPic(349, 212, 535, 590,"sh1.png");
        result1 = mFairy.findPic(349, 212, 535, 590, "teamzl.png");
        LtLog.e(mFairy.getLineInfo(0.9f, result, "1人"));
        if (result.sim < 0.8f) {
            num++;
        }
        if (result1.sim > 0.9f) {
            if (zlSign1 == 0) {
                zlTime1 = System.currentTimeMillis();
                zlSign1 = 1;
                mFairy.onTap(0.8f, result1, "暂离了邀请回归一下", 1000);
                result = mFairy.findPic(142, 42, 1109, 690, "call_together.png");
                mFairy.onTap(0.8f, result, "邀请回归", 1000);
            }
            if (System.currentTimeMillis() - zlTime1 > 60000 && zlSign1 == 1) {
                mFairy.onTap(0.8f, result1, "暂离时间过长", 1000);
                result = mFairy.findPic(142, 42, 1109, 690, "please_leave.png");
                mFairy.onTap(0.8f, result, "请离队友", 1000);
                zlSign1 = 0;
            }
        }

        //result = mFairy.findPic(535, 213, 722, 593,  new String[]{"peoplenum.png","peoplenum1.png","peoplenum2.png"});
        result = mFairy.findPic(535, 213, 722, 593,"sh1.png");
        result1 = mFairy.findPic(535, 213, 722, 593, "teamzl.png");
        LtLog.e(mFairy.getLineInfo(0.9f, result, "2人"));
        if (result.sim < 0.8f) {
            num++;
        }
        if (result1.sim > 0.9f) {
            if (zlSign2 == 0) {
                zlTime2 = System.currentTimeMillis();
                zlSign2 = 1;
                mFairy.onTap(0.8f, result1, "暂离了邀请回归一下", 1000);
                result = mFairy.findPic(142, 42, 1109, 690, "call_together.png");
                mFairy.onTap(0.8f, result, "邀请回归", 1000);
            }
            if (System.currentTimeMillis() - zlTime2 > 60000 && zlSign2 == 1) {
                mFairy.onTap(0.8f, result1, "暂离时间过长", 1000);
                result = mFairy.findPic(142, 42, 1109, 690, "please_leave.png");
                mFairy.onTap(0.8f, result, "请离队友", 1000);
                zlSign2 = 0;
            }
        }
        //result = mFairy.findPic(727, 210, 909, 588,  new String[]{"peoplenum.png","peoplenum1.png","peoplenum2.png"});

        result = mFairy.findPic(727, 210, 909, 588,"sh1.png");
        result1 = mFairy.findPic(727, 210, 909, 588, "teamzl.png");
        LtLog.e(mFairy.getLineInfo(0.9f, result, "3人") );
        if (result.sim < 0.8f) {
            num++;
        }
        if (result1.sim > 0.9f) {
            if (zlSign3 == 0) {
                zlTime3 = System.currentTimeMillis();
                zlSign3 = 1;
                mFairy.onTap(0.8f, result1, "暂离了邀请回归一下", 1000);
                result = mFairy.findPic(142, 42, 1109, 690, "call_together.png");
                mFairy.onTap(0.8f, result, "邀请回归", 1000);
            }
            if (System.currentTimeMillis() - zlTime3 > 60000 && zlSign3 == 1) {
                mFairy.onTap(0.8f, result1, "暂离时间过长", 1000);
                result = mFairy.findPic(142, 42, 1109, 690, "please_leave.png");
                mFairy.onTap(0.8f, result, "请离队友", 1000);
                zlSign3 = 0;
            }
        }

        //result = mFairy.findPic(913, 211, 1101, 588,  new String[]{"peoplenum.png","peoplenum1.png","peoplenum2.png"});
        result = mFairy.findPic(913, 211, 1101, 588, "sh1.png");
        result1 = mFairy.findPic(913, 211, 1101, 588, "teamzl.png");
        LtLog.e(mFairy.getLineInfo(0.9f, result, "4人"));
        if (result.sim <0.8f) {
            num++;
        }
        if (result1.sim > 0.9f) {
            if (zlSign4 == 0) {
                zlTime4 = System.currentTimeMillis();
                zlSign4 = 1;
                mFairy.onTap(0.8f, result1, "暂离了邀请回归一下", 1000);
                result = mFairy.findPic(142, 42, 1109, 690, "call_together.png");
                mFairy.onTap(0.8f, result, "邀请回归", 1000);
            }
            if (System.currentTimeMillis() - zlTime4 > 60000 && zlSign4 == 1) {
                mFairy.onTap(0.8f, result1, "暂离时间过长", 1000);
                result = mFairy.findPic(142, 42, 1109, 690, "please_leave.png");
                mFairy.onTap(0.8f, result, "请离队友", 1000);
                zlSign4 = 0;
            }
        }
        result = mFairy.findPic(413, 249, 869, 399, "please_leave_sure.png");
        mFairy.onTap(0.8f, result, 716, 417, 778, 441, "请离确认", 1000);
        if (num >= 3) {
            close();
        }
        return num;
    }

    //签到领奖
    public void signIn() throws Exception {
        close();
        for (int i=0;i<5;i++){
            mFairy.condit();
            result = mFairy.findPic(214, 5, 534, 82, "activity.png");
            mFairy.onTap(0.8f, result, 27,361,43,374,"打开福利", 1000);

            result = mFairy.findPic("welfare_interface.png");
            LtLog.e(mFairy.getLineInfo(0.8f,result,"福利界面"));
            if (result.sim>0.8f){
                result = mFairy.findPic(172,81,433,652,"reward.png");
                mFairy.onTap(0.9f, result, "红点奖励", 1000);

                result = mFairy.findPic(422,76,1105,653,"all_z.png");
                mFairy.onTap(0.8f, result, "全部砸开", 2000);

                result = mFairy.findPic(422,76,1105,653,"absorb.png");
                mFairy.onTap(0.8f, result, "吸收", 2000);

                result = mFairy.findPic(635,399,862,474,"sure.png");
                mFairy.onTap(0.8f, result, "确定吸收", 1000);

                result = mFairy.findPic(422,76,1105,653,"lq.png");
                mFairy.onTap(0.8f, result, "领取7日登陆", 2000);

                result = mFairy.findPic(422,76,1105,653,"lq2.png");
                mFairy.onTap(0.8f, result, "领取新手礼包", 2000);

                result = mFairy.findPic(422,76,1105,653,"qdjm.png");
                if (result.sim>0.8f){
                    String string= mFairy.change(448,142,1087,643);
                    String[] strarr = string.split(",");
                    LtLog.e(mFairy.getLineInfo("strarr="+string));
                    mFairy.onTap(Integer.parseInt(strarr[0]),Integer.parseInt(strarr[1]),Integer.parseInt(strarr[0])+1,Integer.parseInt(strarr[1])+1,"签到",2000);
                }
            }
        }
        close();
        for (int i=0;i<5;i++){
            result = mFairy.findPic(214, 5, 534, 82, "activity.png");
            mFairy.onTap(0.8f, result, "主界面活动", 1000);

            result = mFairy.findPic(564, 4, 708, 73, "activity1.png");
            LtLog.e(mFairy.getLineInfo(0.8f, result, "活动界面场景"));
            if (result.sim > 0.8f) {
                result = mFairy.findPic(428, 525, 457, 618, "active_reward.png");
                if (result.sim < 0.8f) {
                    mFairy.onTap(463, 558, 489, 586, "有奖励", 1000);
                }
                result = mFairy.findPic(572, 521, 594, 618, "active_reward.png");
                if (result.sim < 0.8f) {
                    mFairy.onTap(612, 561, 632, 577, "有奖励", 1000);
                }
                result = mFairy.findPic(711, 524, 740, 617, "active_reward.png");
                if (result.sim < 0.8f) {
                    mFairy.onTap(744, 564, 775, 585, "有奖励", 1000);
                }
                result = mFairy.findPic(856, 520, 881, 619, "active_reward.png");
                if (result.sim < 0.8f) {
                    mFairy.onTap(891, 563, 911, 582, "有奖励", 1000);
                }
                result = mFairy.findPic(996, 522, 1017, 620, "active_reward.png");
                if (result.sim < 0.8f) {
                    mFairy.onTap(1021, 564, 1044, 585, "有奖励", 1000);
                }
                for (int i1 = 0; i1 < 5; i1++) {
                    result = mFairy.findPic("used.png");
                    mFairy.onTap(0.8f, result, "使用", 1000);
                }
            }
        }
    }
}
