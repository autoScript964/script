package com.script.fairy;


import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.TaskContent;
import com.script.framework.AtFairyImpl;

/**
 * Created by Administrator on 2019/3/25 0025.
 */
@SuppressWarnings("all")
public class SingleTask extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    GameUtil gameUtil;

    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gameUtil = new GameUtil(mFairy);
        task_err = 0;
    }

    public int task_err = 0;
    public int nn_err = 0;

    public void content_2() throws Exception {
        if (overtime(8, 0)) {
            task_err++;
            if (task_err > 1) {
                setTaskEnd();
            }
            return;
        }

        result = mFairy.findPic("taskbar.png");
        mFairy.onTap(0.85f, result, "切换到任务栏", Sleep);

        mFairy.taskSlid(err, new int[]{2, 4, 6}, 3, 88, 400, 88, 216, 1500, 1000);
    }

    public void content_3() throws Exception {

        result = mFairy.findPic("taskbar.png");
        mFairy.onTap(0.85f, result, "切换到任务栏", Sleep);

        result = mFairy.findPic("Copyleave.png");
        mFairy.onTap(0.8f, result, 797, 424, 798, 425, "确认离开副本", Sleep);
    }

    public void inOperation() throws Exception {
        result = mFairy.findPic("Over drawing.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("过图中"));
            mFairy.initMatTime();
            err = 0;
        }
        result = mFairy.findPic(new String[]{"nogang.png", "nogang1.png"});
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("没有帮会"));
            Thread.sleep(2000);
            setTaskEnd();
            return;
        }

        gameUtil.zh();
        gameUtil.gdFBTeam();
    }

    public void test() throws Exception {
        while (mFairy.condit()) {
            Thread.sleep(3000);
            result = mFairy.findPic("Openteam.png");
            LtLog.e(mFairy.getLineInfo(0.9f, result, "打开队伍栏"));

            result = mFairy.findPic("Contingenthurdles.png");
            LtLog.e(mFairy.getLineInfo(0.9f, result, "切换到队伍栏"));


            result = mFairy.findPic("Contingenthurdles1.png");
            LtLog.e(mFairy.getLineInfo(0.9f, result, "切换到队伍栏1"));


            result = mFairy.findPic("Openteam1.png");
            LtLog.e(mFairy.getLineInfo(0.9f, result, "打开队伍栏1"));
        }
    }

    int n = 0;

    public void novice() throws Exception {
        new SingleTask(mFairy) {

            public void create() throws Exception {
                super.create();
                n = 0;
            }

            public void content_0() throws Exception {
                nn_err = 0;

                result = mFairy.findPic(new String[]{"haoyou1.png", "Takewalk.png"});
                if (result.sim < 0.75f) {
                    n++;
                    Thread.sleep(1000);
                    if (n > 2) {
                        int num1 = mFairy.getColorNum(189, 488, 530, 609, 0.9, 0, "255,251,206");
                        LtLog.e(mFairy.getLineInfo("左边num==" + num1));
                        if (num1 > 50) {
                            mFairy.onTap(1256, 698, 1267, 709, "新手对话", Sleep);
                        }
                        int num = mFairy.getColorNum(840, 488, 1027, 609, 0.9, 0, "255,251,206");
                        LtLog.e(mFairy.getLineInfo("右边num==" + num));
                        if (num > 50) {
                            mFairy.onTap(1256, 698, 1267, 709, "新手对话", Sleep);
                        }
                    }
                } else {
                    n = 0;
                }


                result = mFairy.findPic("Clickground.png");
                mFairy.onTap(0.8f, result, 968, 427, 969, 428, "点击地面", Sleep);

                result = mFairy.findPic("xssf.png");
                mFairy.onTap(0.8f, result, 1147, 573, 1162, 587, "技能5", 500);

                result = mFairy.findPic("Takewalk.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("拖动虚拟摇杆"));
                    mFairy.ranSwipe(200, 575, 273, 550, 500, 500);
                }

                result = mFairy.findPic("gj.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("新手需要打怪"));
                    for (int i = 0; i < 10; i++) {

                        result = mFairy.findPic("gj.png");
                        mFairy.onTap(0.8f, result, 1147, 573, 1162, 587, "技能5", 500);
                        if (result.sim < 0.8f) {
                            break;
                        }
                        result = mFairy.findPic("taskbar2.png");
                        if (result.sim > 0.9f) {
                            setTaskName(1);
                            return;
                        }
                    }
                    for (int i = 0; i < 10; i++) {

                        result = mFairy.findPic("gj.png");
                        mFairy.onTap(0.8f, result, 1147, 573, 1162, 587, "", 10);
                        mFairy.onTap(0.8f, result, 1225, 462, 1239, 473, "技能1", 10);
                        mFairy.onTap(0.8f, result, 1108, 459, 1132, 477, "技能2", 10);
                        mFairy.onTap(0.8f, result, 1025, 535, 1043, 548, "技能3", 10);
                        mFairy.onTap(0.8f, result, 1019, 643, 1036, 661, "技能4", 10);

                        if (result.sim < 0.8f) {
                            break;
                        }
                        result = mFairy.findPic("taskbar2.png");
                        if (result.sim > 0.9f) {
                            setTaskName(1);
                            return;
                        }
                    }
                }

                result = mFairy.findPic("taskbar2.png");
                if (result.sim > 0.9f) {
                    setTaskName(1);
                    return;
                } else {
                    long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                    if (dazeTime > 3) {
                        result = mFairy.findPic(8, 165, 242, 287, "ssj.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(36, 264, 49, 281, "主线", 2000);
                        } else {
                            mFairy.onTap(21, 241, 30, 250, "主线", 2000);
                        }
                    }
                }

                result = mFairy.findPic("equipment.png");
                mFairy.onTap(0.8f, result, "新手装备", Sleep);

                result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork4.png", "x1.png"});
                if (picCount(0.73f, result, "新手叉2") >= 3) {
                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork4.png", "x1.png"});
                        mFairy.onTap(0.73f, result, "叉子2", Sleep);
                    }
                }

            }

            boolean other = true;
            int taskCount = 0;

            public void content_1() throws Exception {

                super.content_3();


                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 3) {

                    result = mFairy.findPic("other4.png");
                    if (result.sim > 0.8f) {

                        result = mFairy.findPic(8, 165, 242, 287, "ssj.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(36, 264, 49, 281, "主线", 3500);
                        } else {
                            mFairy.onTap(21, 241, 30, 250, "主线", 3500);
                        }

                        result = mFairy.findPic("other4.png");
                        mFairy.onTap(0.8f, result, 1201, 92, 1202, 93, "神秘梦境打开地图", 5000);

                        result = mFairy.findPic(342, 180, 1047, 606, "other5.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("石灯"));
                            for (int i = 0; i < 10; i++) {
                                mFairy.onTap(0.8f, result, 612, 470, 613, 471, "石灯1", 1000);
                            }
                            for (int i = 0; i < 10; i++) {
                                mFairy.onTap(0.8f, result, 556, 532, 557, 533, "石灯2", 1000);
                            }
                            for (int i = 0; i < 10; i++) {
                                mFairy.onTap(0.8f, result, 661, 539, 662, 540, "石灯3", 1000);
                            }

                            for (int i = 0; i < 3; i++) {
                                result = mFairy.findPic(1050, 46, 1126, 112, new String[]{"fork1.png", "fork2.png"});
                                mFairy.onTap(0.75f, result, "叉子2", Sleep);
                            }
                        } else {
                            mFairy.onTap(488, 160, 489, 161, "蝴蝶", Sleep);
                        }
                    }

                    result = mFairy.findPic("taskbar2.png");
                    if (result.sim > 0.9f) {

                        taskCount = 0;
                        result1 = mFairy.findPic(4, 191, 237, 449, "mainstop.png");
                        if (result1.sim > 0.7f) {
                            LtLog.e(mFairy.getLineInfo("主线完了"));
                            setTaskEnd();
                            return;
                        }

                        result = mFairy.findPic(2, 187, 99, 509, new String[]{"Novice2.png", "nn1.png"});
                        LtLog.e("主线 sim:" + result.sim);
                        if (result.sim > 0.7f) {
                            nn_err = 0;
                            mFairy.onTap(0.7f, result, result.x + 20, result.y + 10, result.x + 21, result.y + 20, "主线", 2000);
                        } else {
                            nn_err++;
                            if (nn_err > 2) {
                                result = mFairy.findPic(8, 165, 242, 287, "ssj.png");
                                if (result.sim > 0.8f) {
                                    mFairy.onTap(36, 264, 49, 281, "主线", 2000);
                                } else {
                                    mFairy.onTap(21, 241, 30, 250, "主线", 2000);
                                }
                                nn_err = 0;
                            }
                        }
                    } else {
                        taskCount++;
                        if (taskCount > 20) {
                            mFairy.onTap(128, 13, 129, 14, "新手对话", Sleep);
                            taskCount = 0;
                        }
                    }
                }


                result = mFairy.findPic("other2.png");
                mFairy.onTap(0.8f, result, "玉佩", Sleep);
                if (result.sim > 0.8f) {
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic("other2.png");
                        mFairy.onTap(0.8f, result, "玉佩", 500);
                        mFairy.onTap(0.8f, result, "玉佩", 500);
                        if (result.sim > 0.8f) {
                            i--;
                        }
                        result = mFairy.findPic("taskbar1.png");
                        if (result.sim > 0.9f) {

                            result = mFairy.findPic(8, 165, 242, 287, "ssj.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(42, 252, 87, 276, "主线", 1000);
                                mFairy.onTap(42, 252, 87, 276, "主线", 2000);
                            } else {
                                mFairy.onTap(53, 202, 80, 217, "主线", 1000);
                                mFairy.onTap(53, 202, 80, 217, "主线", 2000);
                            }
                        }
                        result = mFairy.findPic("Copyleave.png");
                        mFairy.onTap(0.8f, result, 797, 424, 798, 425, "确认离开副本", Sleep);
                    }
                }

                result = mFairy.findPic("Rightrefusal.png");
                mFairy.onTap(0.8f, result, "左侧拒绝", Sleep);

                result = mFairy.findPic("equipment.png");
                mFairy.onTap(0.8f, result, "新手装备", Sleep);

                result = mFairy.findPic(new String[]{"other6.png", "other7.png", "other8.png", "duihua1.png"});
                mFairy.onTap(0.8f, result, "特殊对话", Sleep);

                result = mFairy.findPic(919, 111, 1276, 706, new String[]{"Righthandpass.png", "Getinto.png", "Righthandpass1.png"});
                mFairy.onTap(0.8f, result, "右侧任务", Sleep);

                result = mFairy.findPic(919, 111, 1276, 706, new String[]{"Rightmaster.png", "Rightmaster2.png"});
                mFairy.onTap(0.8f, result, "右侧任务", Sleep);

                result = mFairy.findPic("Use.png");
                mFairy.onTap(0.8f, result, "使用", 5000);

                result = mFairy.findPic(813, 97, 1275, 709, new String[]{"Tailor.png", "Blacksmith.png", "Drugstore.png", "zahuopu.png"});
                mFairy.onTap(0.8f, result, "右侧npc铺子", Sleep);

                result = mFairy.findPic("purchase.png");
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic(112, 65, 840, 690, "npcdemand.png");
                    if (result1.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "npc商店的购买需求", Sleep);

                    }
                    result1 = mFairy.findPic(452, 495, 830, 573, "Coverup.png");
                    if (result1.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                        setTaskEnd();
                        return;
                    }
                    for (int i = 0; i < 2; i++) {

                        result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png"});
                        mFairy.onTap(0.73f, result, "叉子2", 2000);

                        result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork3.png", "fork4.png"});
                        mFairy.onTap(0.73f, result, "叉子1", 2000);
                    }
                }

                if (other) {

                    result = mFairy.findPic("jia4.png");
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 5; i++) {
                            result = mFairy.findPic("jia4.png");
                            if (result.sim < 0.8f) {
                                break;
                            }
                            mFairy.onTap(1135, 569, 1159, 598, "", 500);
                            mFairy.onTap(1021, 653, 1043, 672, "", 500);
                            mFairy.onTap(1025, 530, 1048, 550, "", 500);
                            mFairy.onTap(1115, 456, 1141, 478, "", 500);
                        }
                    }

                    result = mFairy.findPic("other.png");
                    mFairy.onTap(0.8f, result, 641, 415, 642, 416, "新的轮回", Sleep);

                    result = mFairy.findPic("other1.png");
                    mFairy.onTap(0.8f, result, "障眼法", Sleep);

                    result = mFairy.findPic(489, 564, 781, 693, "other3.png");
                    mFairy.onTap(0.8f, result, 655, 327, 656, 328, "推开房门", Sleep);

                    result = mFairy.findPic("nn4.png");
                    mFairy.onTap(0.8f, result, 475, 362, 523, 415, "选择灵兽", Sleep);

                    result = mFairy.findPic("zyother.png");
                    if (result.sim > 0.7f) {
                        mFairy.onTap(0.7f, result, 1152, 200, 1166, 209, "打开包裹", 15000);
                        mFairy.onTap(683, 201, 684, 202, "", 500);
                        mFairy.onTap(778, 200, 779, 201, "", 500);
                        mFairy.onTap(875, 205, 876, 206, "", 500);
                        mFairy.onTap(968, 198, 969, 199, "", 500);
                        mFairy.onTap(1057, 201, 1058, 202, "", 500);
                        mFairy.onTap(685, 300, 686, 301, "", 500);
                        mFairy.onTap(781, 292, 782, 293, "", 500);
                        mFairy.onTap(876, 297, 877, 298, "", 500);
                        mFairy.onTap(972, 295, 973, 296, "", 500);
                        mFairy.onTap(1058, 295, 1059, 296, "", 500);
                        Thread.sleep(3000);
                        mFairy.onTap(527, 345, 559, 359, "自动", 3000);
                        mFairy.onTap(420, 266, 439, 284, "自动", 3000);
                        mFairy.onTap(99, 219, 114, 233, "药品", 3000);
                        mFairy.onTap(412, 457, 437, 477, "药品", 3000);
                        mFairy.onTap(114, 233, 135, 248, "药品", 3000);
                        mFairy.onTap(947, 112, 960, 127, "关闭", Sleep);
                        mFairy.onTap(1176, 56, 1193, 68, "关闭", Sleep);
                    }

                    result = mFairy.findPic("zyother1.png");
                    if (result.sim > 0.7f) {

                       /* sslCount++;
                        if (sslCount > 5) {
                            // other = false;
                        }*/


                        mFairy.onTap(0.7f, result, 1234, 195, 1248, 211, "打开伸缩栏", 3000);
                    }


                    result = mFairy.findPic("zyother2.png");
                    if (result.sim > 0.7f) {
                        mFairy.onTap(0.7f, result, 1234, 275, 1251, 289, "打造装备", 5000);
                        return;
                    }


                    result = mFairy.findPic("zyother3.png");
                    if (result.sim > 0.7f) {
                        mFairy.onTap(0.7f, result, 1152, 200, 1166, 209, "打开包裹", 20000);
                    }


                    result = mFairy.findPic(168, 8, 1183, 401, "zyother4.png");
                    if (result.sim > 0.7f) {
                        mFairy.onTap(683, 201, 684, 202, "", 100);
                        mFairy.onTap(778, 200, 779, 201, "", 100);
                        mFairy.onTap(875, 205, 876, 206, "", 100);
                        mFairy.onTap(968, 198, 969, 199, "", 100);
                        mFairy.onTap(1057, 201, 1058, 202, "", 100);
                        mFairy.onTap(685, 300, 686, 301, "", 100);
                        mFairy.onTap(781, 292, 782, 293, "", 100);
                        mFairy.onTap(876, 297, 877, 298, "", 100);
                        mFairy.onTap(972, 295, 973, 296, "", 100);
                        mFairy.onTap(1058, 295, 1059, 296, "", 500);
                        Thread.sleep(3000);
                        mFairy.onTap(0.7f, result, 1027, 116, 1046, 130, "点装备", 3000);
                        mFairy.onTap(0.7f, result, 1032, 90, 1050, 103, "点装备", 3000);
                        mFairy.onTap(0.7f, result, 1131, 28, 1143, 38, "关叉", 3000);
                    }


                    result = mFairy.findPic(168, 8, 1183, 401, "zyother5.png");
                    if (result.sim > 0.7f) {
                        mFairy.onTap(683, 201, 684, 202, "", 500);
                        mFairy.onTap(778, 200, 779, 201, "", 500);
                        mFairy.onTap(875, 205, 876, 206, "", 500);
                        mFairy.onTap(968, 198, 969, 199, "", 500);
                        mFairy.onTap(1057, 201, 1058, 202, "", 500);
                        mFairy.onTap(685, 300, 686, 301, "", 500);
                        mFairy.onTap(781, 292, 782, 293, "", 500);
                        mFairy.onTap(876, 297, 877, 298, "", 500);
                        mFairy.onTap(972, 295, 973, 296, "", 500);
                        mFairy.onTap(1058, 295, 1059, 296, "", 500);
                        Thread.sleep(3000);
                        mFairy.onTap(0.7f, result, 511, 313, 540, 331, "装备", 3000);
                    }


                    result = mFairy.findPic("zyother6.png");
                    if (result.sim > 0.7f) {
                        mFairy.onTap(0.7f, result, 1152, 200, 1166, 209, "打开包裹", 20000);
                        mFairy.onTap(0.7f, result, 669, 92, 684, 102, "设置", 3000);
                        return;
                    }

                    result = mFairy.findPic("zyother7.png");
                    if (result.sim > 0.7f) {
                        mFairy.onTap(0.7f, result, 1152, 200, 1166, 209, "打开包裹", 10000);
                        return;
                      /*  mFairy.onTap(683, 201, 684, 202, "", 500);
                        mFairy.onTap(778, 200, 779, 201, "", 500);
                        mFairy.onTap(875, 205, 876, 206, "", 500);
                        mFairy.onTap(968, 198, 969, 199, "", 500);
                        mFairy.onTap(1057, 201, 1058, 202, "", 500);
                        mFairy.onTap(685, 300, 686, 301, "", 500);
                        mFairy.onTap(781, 292, 782, 293, "", 500);
                        mFairy.onTap(876, 297, 877, 298, "", 500);
                        mFairy.onTap(972, 295, 973, 296, "", 500);
                        mFairy.onTap(1058, 295, 1059, 296, "", 500);
                        Thread.sleep(3000);
                        mFairy.onTap(0.7f, result, 498, 253, 540, 269, "召唤灵兽", 3000);*/
                    }
                }

                result = mFairy.findPic(661, 420, 923, 562, "yao.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(413, 263, 440, 292, "", 500);
                    mFairy.onTap(184, 206, 261, 239, "", 500);
                    mFairy.onTap(415, 442, 442, 470, "", 500);
                    mFairy.onTap(184, 206, 261, 239, "", 500);
                }

                result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork4.png", "x1.png"});
                if (picCount(0.73f, result, "新手叉2") >= 2) {
                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork4.png", "x1.png"});
                        mFairy.onTap(0.73f, result, "叉子2", 1000);
                    }
                }


            /*    result = mFairy.findPic("huapi.png");
                if (picCount(0.8f, result, "新手画皮") >= 5) {
                    mFairy.killUserGame();
                }*/
                result = mFairy.findPic(new String[]{"xsfb.png", "xsfb1.png"});
                mFairy.onTap(0.8f, result, 1021, 653, 1037, 665, "副本普工", Sleep);

                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);

                for (int i = 0; i < 7; i++) {

                    result = mFairy.findPic("Over drawing.png");
                    if (result.sim > 0.8f) {
                        break;
                    }


                    result = mFairy.findPic(392, 517, 581, 627, "huatong.png");
                    mFairy.onTap(0.8f, result, 743, 542, 762, 567, "对话喇叭", 500);


                    result = mFairy.findPic(new String[]{"haoyou1.png", "Takewalk.png"});
                    if (result.sim < 0.75f) {
                        int num1 = mFairy.getColorNum(189, 488, 530, 609, 0.9, 0, "255,251,206");
                        LtLog.e(mFairy.getLineInfo("左边num==" + num1));
                        if (num1 > 50) {
                            mFairy.onTap(1256, 698, 1267, 709, "新手对话", Sleep);
                        }
                        int num = mFairy.getColorNum(840, 488, 1027, 609, 0.9, 0, "255,251,206");
                        LtLog.e(mFairy.getLineInfo("右边num==" + num));
                        if (num > 50) {
                            mFairy.onTap(1256, 698, 1267, 709, "新手对话", Sleep);
                        }
                        if (num1 > 50 || num > 50) {
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }

            public void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic("bao.png");
                if (result.sim > 0.8f) {

                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic(18, 334, 208, 459, "bai.png");
                        mFairy.onTap(0.8f, result, 395, 393, 400, 399, "取消勾选白装", 3000);

                        result = mFairy.findPic(393, 252, 546, 361, "huang.png");
                        mFairy.onTap(0.8f, result, 773, 331, 784, 341, "取消勾选黄装", 3000);

                        result = mFairy.findPic(187, 266, 340, 349, "cheng.png");
                        mFairy.onTap(0.8f, result, 583, 333, 594, 339, "取消勾选橙装", 3000);
                    }
                    mFairy.onTap(967, 118, 981, 128, "", 1000);
                }


                result = mFairy.findPic("zuoqi4.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(200, 80, 1021, 710, "lingshou1.png");
                    mFairy.onTap(0.8f, result, result.x + 300, result.y + 10, result.x + 305, result.y + 20, "点击灵兽", 1000);

                    result = mFairy.findPic(408, 95, 659, 632, "lingshou2.png");
                    mFairy.onTap(0.8f, result, "召唤灵兽", 1000);

                    result = mFairy.findPic(200, 80, 1021, 710, "zhuangbei1.png");
                    mFairy.onTap(0.8f, result, "装备", 1000);

                    result = mFairy.findPic(200, 80, 1021, 710, "ab1.png");
                    mFairy.onTap(0.8f, result, result.x + 362, result.y, result.x + 363, result.y + 1, "使用药品", 1000);

                    result = mFairy.findPic(200, 80, 1021, 710, "ab2.png");
                    mFairy.onTap(0.8f, result, "自动", 1000);

                }

                result = mFairy.findPic(838, 187, 1106, 438, "qiang2.png");
                mFairy.onTap(0.8f, result, 1180, 275, 1198, 309, "强化", 1000);

                result = mFairy.findPic(458, 21, 803, 679, "qiang3.png");
                mFairy.onTap(0.8f, result, result.x - 300, result.y + 10, result.x - 280, result.y + 20, "选择手套", 1000);

                result = mFairy.findPic(451, 512, 717, 649, "qiang4.png");
                mFairy.onTap(0.8f, result, 910, 621, 946, 630, "强化", 1000);


                result = mFairy.findPic("qiang5.png");
                mFairy.onTap(0.8f, result, "强化", 1000);

                result = mFairy.findPic(230, 112, 530, 386, "nn2.png");
                mFairy.onTap(0.8f, result, 42, 221, 83, 246, "点击任务", Sleep);

                result = mFairy.findPic("nn3.png");
                mFairy.onTap(0.8f, result, 616, 416, 652, 426, "新的冒险", Sleep);

                result = mFairy.findPic("z0.png");
                if (result.sim > 0.8f) {

                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic("z1.png");
                        mFairy.onTap(0.89f, result, "z1", 1000);

                        result = mFairy.findPic("z2.png");
                        mFairy.onTap(0.89f, result, "z2", 1000);

                        result = mFairy.findPic("z3.png");
                        mFairy.onTap(0.89f, result, "z3", 1000);
                    }
                    mFairy.onTap(934, 137, 953, 153, "", 1000);
                }

            }

        }.taskContent(mFairy, "一键29级中");
    } //一键29级

    public void master() throws Exception {
        new SingleTask(mFairy) {
            int fhcount = 0, smcount = 0;

            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("Teacherstask.png", 3);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(3);
                } else if (ret == 2) {
                    setTaskName(2);
                    Thread.sleep(5000);
                    mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1);
                }

                smcount++;
                if (smcount > 3) {
                    LtLog.e("师门异常无法前往结束");
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {

                result = mFairy.findPic(6, 179, 95, 429, new String[]{"jia10.png", "jia7.png", "jia8.png", "Leftmaster.png", "Leftmaster1.png", "Leftmaster2.png", "Leftmaster3.png", "Leftmaster4.png"});
                mFairy.onTap(0.7f, result, result.x + 20, result.y + 20, result.x + 21, result.y + 21, "左侧师门", 2500);
                if (result.sim > 0.7f) {
                    smcount = 0;
                    task_err = 0;
                    setTaskName(3);
                    return;
                }
                super.content_2();
            }

            public void content_3() throws Exception {
                super.content_3();

                if (overtime(10, 0)) return;

                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);

                result = mFairy.findPic(813, 97, 1275, 709, new String[]{"Rightmaster.png", "Rightmaster1.png"});
                mFairy.onTap(0.8f, result, "右侧师门", 2000);
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.initMatTime();
                }

                result = mFairy.findPic(642, 8, 1273, 318, new String[]{"Replica.png", "Replica1.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("副本中"));
                }

                result = mFairy.findPic("complete.png");
                mFairy.onTap(0.8f, result, 642, 417, 643, 418, "师门完成", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("onecomplete.png");
                mFairy.onTap(0.8f, result, 791, 419, 792, 420, "师门完成一轮", Sleep);

                result = mFairy.findPic("Use.png");
                mFairy.onTap(0.8f, result, "使用", 1000);
                if (result.sim > 0.8f) {
                    for (int i = 0; i < 20; i++) {
                        Thread.sleep(200);
                        result = mFairy.findPic(638, 414, 721, 597, "ma.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(42, 616, 53, 628, "", 3000);
                            break;
                        }
                    }
                    dazeTime = 10;
                }

                result = mFairy.findPic("Magicweapon.png");
                mFairy.onTap(0.8f, result, "法宝搜索", Sleep);

                result = mFairy.findPic(813, 97, 1275, 709, new String[]{"Tailor.png", "Blacksmith.png", "Drugstore.png", "zahuopu.png"});
                mFairy.onTap(0.8f, result, "右侧npc铺子", Sleep);


                result = mFairy.findPic("purchase.png");
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic(112, 65, 840, 690, "npcdemand.png");
                    if (result1.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "npc商店的购买需求", Sleep);
                    }
                    result1 = mFairy.findPic(452, 495, 830, 573, "Coverup.png");
                    if (result1.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                        setTaskEnd();
                        return;
                    }
                    for (int i = 0; i < 2; i++) {

                        result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png"});
                        mFairy.onTap(0.7f, result, "叉子2", 2000);

                        result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork3.png", "fork4.png"});
                        mFairy.onTap(0.8f, result, "叉子1", 2000);
                    }
                    dazeTime = 10;
                }

                // 7+6+2+2+5+4+4+6+6+6
                result = mFairy.findPic(new String[]{"purchase1.png", "purchas  e2.png"});
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("gmfq").equals("1")) {
                        gameUtil.giveUpTask(new String[]{"Giveupteacher.png", "Giveupteacher1.png", "shimen1.png"});
                        setTaskName(0);
                        return;
                    } else {
                        mFairy.onTap(0.8f, result, "玩家商店的购买", Sleep);
                        result1 = mFairy.findPic(452, 495, 830, 573, "Coverup.png");
                        if (result1.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                            setTaskEnd();
                            return;
                        }

                        for (int i = 0; i < 2; i++) {

                            result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png"});
                            mFairy.onTap(0.7f, result, "叉子2", 2000);

                            result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork3.png", "fork4.png"});
                            mFairy.onTap(0.8f, result, "叉子1", 2000);
                        }
                        dazeTime = 10;
                    }
                }

                result = mFairy.findPic("wjsdsure.png");
                mFairy.onTap(0.8f, result, "玩家商店的确认", Sleep);
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic("bgym.png");
                    if (result1.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                        setTaskEnd();
                        return;
                    }
                    gameUtil.close(0);
                    dazeTime = 10;
                }

                result = mFairy.findPic("Handin.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("上交"));
                    Thread.sleep(5000);

                    result = mFairy.findPic(638, 10, 1278, 717, "attribute.png");
                    mFairy.onTap(0.7f, result, 552, 565, 553, 566, "有词条属性,购买其他", Sleep);
                    mFairy.onTap(0.7f, result, 547, 472, 548, 473, "npc商店", Sleep);
                    if (result.sim < 0.7f) {
                        mFairy.onTap(389, 567, 390, 568, "点击上交上交", Sleep);
                    }
                }

                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);
                if (result.sim > 0.8f) {
                    fhcount++;
                    if (fhcount > 2) {
                        LtLog.e(mFairy.getLineInfo("这个任务打不过放弃"));
                        gameUtil.giveUpTask(new String[]{"Giveupteacher.png", "Giveupteacher1.png", "shimen1.png"});
                        setTaskName(0);
                        return;
                    }
                }


                result = mFairy.findPic(443, 531, 842, 705, "sm2.png");
                mFairy.onTap(0.8f, result, "做动作", 3000);


                if (dazeTime > 8) {
                    result = mFairy.findPic(7, 185, 85, 454, new String[]{"jia7.png", "jia8.png", "Leftmaster.png", "Leftmaster1.png", "Leftmaster2.png", "Leftmaster3.png", "Leftmaster4.png"});
                    mFairy.onTap(0.7f, result, result.x + 20, result.y + 20, result.x + 21, result.y + 21, "左侧师门", 2500);
                    if (result.sim > 0.7f) {

                        long s = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                        if (s > 9) {
                            mFairy.ranSwipe(193, 570, 394, 450, 500, 1000);
                            mFairy.onTap(0.7f, result, result.x + 20, result.y + 20, result.x + 21, result.y + 21, "左侧师门", 2500);
                        }
                        smcount = 0;
                    }
                }/* else {
                    err = 0;
                }*/
            }
        }.taskContent(mFairy, "师门任务中");
    }//师门

    public void master1() throws Exception {
        new SingleTask(mFairy) {
            int fhcount = 0, smcount = 0;

            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("Teacherstask1.png", 3);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(3);
                } else if (ret == 2) {
                    setTaskName(2);
                }
                smcount++;
                if (smcount > 2) {
                    LtLog.e("师门课业无法前往结束");
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {

                result = mFairy.findPic(4, 188, 233, 429, new String[]{"jia7.png", "jia8.png", "jia9.png", "leftsouye.png",
                        "leftsouye1.png", "leftsouye2.png", "leftsouye3.png", "Leftmaster.png", "Leftmaster1.png", "Leftmaster2.png",
                        "Leftmaster3.png"});
                mFairy.onTap(0.7f, result, result.x + 20, result.y + 20, result.x + 21, result.y + 21, "左侧师门课业", 2500);
                if (result.sim > 0.7f) {
                    setTaskName(3);
                    return;
                }

                super.content_2();
            }

            public void content_3() throws Exception {

                super.content_3();

                if (overtime(10, 0)) {
                    mFairy.ranSwipe(202, 572, 363, 482, 500, 1000);
                    return;
                }

                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);

                result = mFairy.findPic(813, 97, 1275, 709, new String[]{"Rightmaster.png", "Getinto.png", "rightkeye1.png", "rightkeye2.png",
                        "rightkeye3.png", "rightkeye4.png", "rightkeye5.png", "sm.png", "smyk1.png", "smyk2.png", "smyk3.png"});
                mFairy.onTap(0.8f, result, "右侧师门课业", 2000);
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.initMatTime();
                }

                result = mFairy.findPic("ketou.png");
                mFairy.onTap(0.8f, result, "磕头", 5000);

                result = mFairy.findPic("xuyuan.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 496, 346, 513, 354, "许愿输入框", 2000);
                    mFairy.inputText("渣男");

                    result1 = mFairy.findPic(1070, 68, 1274, 719, new String[]{"new_textsure.png", "new_textsure1.png"});
                    mFairy.onTap(0.8f, result1, "确定文本", 2000);
                    mFairy.onTap(0.8f, result, "许愿", Sleep);
                }

                result = mFairy.findPic("zhaoxiang.png");
                mFairy.onTap(0.8f, result, "照相", Sleep);

                for (int i = 0; i < 1; i++) {

                    result = mFairy.findPic("keyedati.png");
                    mFairy.onTap(0.8f, result, 452, 411, 473, 427, "课业答题", Sleep);
                    if (result.sim > 0.8f) {
                        i = 0;
                    }
                }
                result = mFairy.findPic("an3miao.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("长安3秒"));
                    mFairy.touchDown(2, 739, 369);
                    Thread.sleep(4000);
                    mFairy.touchUp(2);
                }
                if (picCount(0.8f, result, "长安3秒") > 3) {
                    result = mFairy.findPic("an3miao.png");
                    mFairy.onTap(0.8f, result, "长安3秒", Sleep);
                    gameUtil.giveUpTask(new String[]{"Giveupteacher.png", "Giveupteacher1.png", "shimen1.png"});
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("keyestop.png");
                mFairy.onTap(0.8f, result, 628, 419, 653, 432, "课业修满结束", Sleep);
                mFairy.onTap(0.8f, result, 1069, 124, 1084, 132, "课业修满结束", Sleep);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("Copyleave.png");
                    mFairy.onTap(0.8f, result, 797, 424, 798, 425, "确认离开副本", Sleep);
                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic("mengdao.png");
                mFairy.onTap(0.8f, result, 1233, 35, 1247, 49, "照相完毕", Sleep);
                mFairy.onTap(0.8f, result, 1233, 35, 1247, 49, "照相完毕", Sleep);

                result = mFairy.findPic("keye.png");
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("keye").equals("1")) {
                        mFairy.onTap(0.8f, result, 354, 350, 374, 366, "课业选择游历", Sleep);
                    } else if (AtFairyConfig.getOption("keye").equals("2")) {
                        mFairy.onTap(0.8f, result, 619, 346, 642, 362, "课业选择术法", Sleep);
                    } else {
                        mFairy.onTap(0.8f, result, 873, 344, 901, 356, "课业选择功业", Sleep);
                    }
                }
                mFairy.onTap(0.8f, result, "课业选择", Sleep);

                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                mFairy.onTap(0.8f, result, 1160, 598, 1179, 615, "副本中放个技能", Sleep);

                result = mFairy.findPic("Use.png");
                mFairy.onTap(0.8f, result, "使用", 5000);
                if (result.sim > 0.8f) {
                    dazeTime = 10;
                }

                result = mFairy.findPic("Magicweapon.png");
                mFairy.onTap(0.8f, result, "法宝搜索", Sleep);


                result = mFairy.findPic(813, 97, 1275, 709, new String[]{"Tailor.png", "Blacksmith.png", "Drugstore.png", "zahuopu.png"});
                mFairy.onTap(0.8f, result, "右侧npc铺子", Sleep);


                result = mFairy.findPic("purchase.png");
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic(112, 65, 840, 690, "npcdemand.png");
                    if (result1.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "npc商店的购买需求", Sleep);
                    }
                    result1 = mFairy.findPic(452, 495, 830, 573, "Coverup.png");
                    if (result1.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                        setTaskEnd();
                        return;
                    }

                    for (int i = 0; i < 2; i++) {

                        result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png"});
                        mFairy.onTap(0.7f, result, "叉子2", 2000);

                        result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork3.png", "fork4.png"});
                        mFairy.onTap(0.8f, result, "叉子1", 2000);
                    }
                    dazeTime = 10;
                }

                result = mFairy.findPic(new String[]{"purchase1.png", "purchase2.png"});
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("gmfq").equals("1")) {
                        gameUtil.giveUpTask(new String[]{"Giveupteacher.png", "Giveupteacher1.png", "shimen1.png"});
                        setTaskName(0);
                        return;
                    } else {
                        mFairy.onTap(0.8f, result, "玩家商店的购买", Sleep);
                        result1 = mFairy.findPic(452, 495, 830, 573, "Coverup.png");
                        if (result1.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                            setTaskEnd();
                            return;
                        }
                        for (int i = 0; i < 2; i++) {

                            result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png"});
                            mFairy.onTap(0.7f, result, "叉子2", 2000);

                            result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork3.png", "fork4.png"});
                            mFairy.onTap(0.8f, result, "叉子1", 2000);
                        }
                        dazeTime = 10;
                    }
                }

                result = mFairy.findPic("wjsdsure.png");
                mFairy.onTap(0.8f, result, "玩家商店的确认", Sleep);
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic("bgym.png");
                    if (result1.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                        setTaskEnd();
                        return;
                    }
                    gameUtil.close(0);
                    dazeTime = 10;
                }

                result = mFairy.findPic("Handin.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("上交"));
                    Thread.sleep(5000);

                    result = mFairy.findPic(638, 10, 1278, 717, "attribute.png");
                    mFairy.onTap(0.7f, result, 552, 565, 553, 566, "有词条属性,购买其他", Sleep);
                    mFairy.onTap(0.7f, result, 547, 472, 548, 473, "npc商店", Sleep);
                    if (result.sim < 0.7f) {
                        mFairy.onTap(389, 567, 390, 568, "点击上交上交", Sleep);
                    }
                }


                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);
                if (result.sim > 0.8f) {
                    fhcount++;
                    if (fhcount > 2) {
                        LtLog.e(mFairy.getLineInfo("这个任务打不过放弃"));
                        gameUtil.giveUpTask(new String[]{"Giveupteacher.png", "Giveupteacher1.png", "shimen1.png"});
                        setTaskName(0);
                        return;
                    }
                }

                if (dazeTime > 8) {
                    result = mFairy.findPic(4, 188, 233, 447, new String[]{"jia7.png", "jia8.png", "jia9.png", "leftsouye.png",
                            "leftsouye1.png", "leftsouye2.png", "leftsouye3.png", "Leftmaster.png", "Leftmaster1.png", "Leftmaster2.png",
                            "Leftmaster3.png"});
                    mFairy.onTap(0.7f, result, result.x + 20, result.y + 20, result.x + 21, result.y + 21, "左侧师门课业", 2500);
                    if (result.sim > 0.7f) {
                        smcount = 0;
                    }
                } else {
                    err = 0;
                }
            }

        }.taskContent(mFairy, "师门课业中");
    } //师门课业

    public void map() throws Exception {
        new SingleTask(mFairy) {
            public void create() throws Exception {
                int w = mFairy.week();
                if (w != 2 && w != 4 && w != 6) {
                    setTaskEnd();
                    return;
                }
            }

            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("Treasuremap.png", 1);
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
                result = mFairy.findPic(4, 191, 237, 429, new String[]{"Lefthandmap.png", "Lefthandmap1.png"});
                mFairy.onTap(0.7f, result, "左侧宝图", 2500);
                if (result.sim > 0.7f) {
                    setTaskName(3);
                    return;
                }
                super.content_2();
            }

            public void content_3() throws Exception {
                super.content_3();
                if (overtime(8, 0)) return;
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 7) {
                    result = mFairy.findPic(4, 191, 237, 449, new String[]{"Lefthandmap.png", "Lefthandmap1.png"});
                    mFairy.onTap(0.7f, result, "左侧宝图", 2500);
                } else {
                    err = 0;
                }

                result = mFairy.findPic(813, 97, 1275, 709, "rightmap.png");
                mFairy.onTap(0.8f, result, "右侧宝图", Sleep);

                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    err = 0;
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "副本中开启挂机", Sleep);
                }
                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);
            }
        }.taskContent(mFairy, "宝图任务中");
    }//宝图

    public void freight() throws Exception {
        new SingleTask(mFairy) {
            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("Freight.png", 4);
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


                result = mFairy.findPic(4, 191, 237, 429, "Leftfreight.png");
                mFairy.onTap(0.7f, result, "左侧货运", 2500);

                if (result.sim > 0.7f) {
                    setTaskName(3);
                    return;
                }
                super.content_2();
            }

            int hwcount = 0;

            public void content_3() throws Exception {
                super.content_3();

                result = mFairy.findPic(4, 191, 237, 449, "Leftfreight.png");
                mFairy.onTap(0.7f, result, "左侧货运", 2500);

                result = mFairy.findPic(894, 8, 1273, 710, new String[]{"Rightfreight.png"});
                mFairy.onTap(0.8f, result, "右侧货运", 2500);

                result = mFairy.findPic("loading.png");
                mFairy.onTap(0.8f, result, "装填", 2500);


                result = mFairy.findPic("byhw.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("loading1.png");
                mFairy.onTap(0.8f, result, "购买并装填", Sleep);
                if (result.sim > 0.8f) {
                    hwcount++;
                    if (hwcount > 20) {
                        LtLog.e(mFairy.getLineInfo("没有货物"));
                        setTaskEnd();
                        return;
                    }
                } else {
                    hwcount = 0;
                }


                result = mFairy.findPic("Fullgoods.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("货满"));
                    err++;
                    if (err > 5) {
                        mFairy.onTap(0.8f, result, 647, 592, 648, 593, "货满", Sleep);
                        setTaskName(4);
                        return;
                    }
                }

                result = mFairy.findPic("hywb.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("货运满了"));
                    setTaskName(4);
                    return;
                }

                /*result = mFairy.findPic("fullcomplete.png");
                if(result.sim>0.8f){
                    setTaskName(4);
                    return;
                }*/

                overtime(20, 0);
            }

            public void content_4() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(894, 8, 1273, 710, "Rightfreight.png");
                mFairy.onTap(0.8f, result, "右侧货运", 2500);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(4, 191, 237, 449, "Leftfreight.png");
                mFairy.onTap(0.7f, result, "左侧货运", 2500);

                result = mFairy.findPic("fullcomplete.png");
                mFairy.onTap(0.8f, result, 647, 592, 648, 593, "完成装填", 3000);
            }
        }.taskContent(mFairy, "货运任务中");
    }//货运

    public void digMap() throws Exception {
        new SingleTask(mFairy) {

            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(5, 2)) return;
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, "包裹", 5000);

                result = mFairy.findPic("recovery.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面"));
                    mFairy.taskSlid(err, new int[]{0, 2, 3, 4}, 0, 922, 593, 922, 172, 500, 2000);


                    result = mFairy.findPic(630, 123, 1126, 618, "digMap.png");
                    mFairy.onTap(0.8f, result, "未开封的藏宝图", 2000);
                    if (result.sim > 0.8f) {
                        err = 0;
                    }


                    if (AtFairyConfig.getOption("cbtpt").equals("1")) {
                        result = mFairy.findPic(630, 123, 1126, 618, "digMap1.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo(0.8f, result, "开封的普通藏宝图"));
                            setTaskName(3);
                            return;
                        }
                    }
                    if (AtFairyConfig.getOption("cbtzj").equals("1")) {
                        result = mFairy.findPic(630, 123, 1126, 618, "digMap2.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo(0.8f, result, "开封的中级藏宝图"));
                            setTaskName(3);
                            return;
                        }
                    }
                    if (AtFairyConfig.getOption("cbtgj").equals("1")) {
                        result = mFairy.findPic(630, 123, 1126, 618, "digMap3.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo(0.8f, result, "开封的高级藏宝图"));
                            setTaskName(3);
                            return;
                        }
                    }
                    if (AtFairyConfig.getOption("cbthj").equals("1")) {
                        result = mFairy.findPic(630, 123, 1126, 618, "digMap4.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo(0.8f, result, "开封的黄金藏宝图"));
                            setTaskName(3);
                            return;
                        }
                    }
                }

                result = mFairy.findPic(320, 117, 775, 533, "Useprops.png");
                mFairy.onTap(0.85f, result, "使用道具", 2000);

                result1 = mFairy.findPic(452, 495, 830, 573, "Coverup.png");
                if (result1.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("mapsure.png");
                mFairy.onTap(0.8f, result, "确定", 2000);
            }

            int wbcount1 = 0;

            public void content_2() throws Exception {
                gameUtil.close(0);
                setTaskName(3);
                wbcount1++;
                if (wbcount1 > 2) {
                    setTaskEnd();
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(6, 0)) return;
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, "包裹", 5000);

                result = mFairy.findPic(67, 3, 1279, 717, "Useprops.png");
                mFairy.onTap(0.85f, result, "使用道具", 2000);

                result1 = mFairy.findPic(452, 495, 830, 573, "Coverup.png");
                if (result1.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("mapsure.png");
                mFairy.onTap(0.8f, result, "确定", 2000);

                result = mFairy.findPic("Digtreasure.png");
                mFairy.onTap(0.8f, result, "挖宝", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(4);
                }


                result = mFairy.findPic("recovery.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面"));
                    mFairy.taskSlid(err, new int[]{0, 2, 3, 4}, 0, 922, 593, 922, 172, 1500, 2000);

                    if (AtFairyConfig.getOption("cbtpt").equals("1")) {
                        result = mFairy.findPic(630, 123, 1126, 618, "digMap1.png");
                        LtLog.e(mFairy.getLineInfo(0.85f, result, "开封的普通藏宝图"));
                        if (result.sim > 0.8f) {
                            wbcount1 = 0;
                            err = 0;
                            mFairy.onTap(0.8f, result, "点开宝图", 2000);
                            return;
                        }
                    }

                    if (AtFairyConfig.getOption("cbtzj").equals("1")) {
                        result = mFairy.findPic(630, 123, 1126, 618, "digMap2.png");
                        LtLog.e(mFairy.getLineInfo(0.85f, result, "开封的中级藏宝图"));
                        if (result.sim > 0.8f) {
                            wbcount1 = 0;
                            err = 0;
                            mFairy.onTap(0.8f, result, "点开宝图", 2000);
                            return;
                        }
                    }


                    if (AtFairyConfig.getOption("cbtgj").equals("1")) {
                        result = mFairy.findPic(630, 123, 1126, 618, "digMap3.png");
                        LtLog.e(mFairy.getLineInfo(0.85f, result, "开封的高级藏宝图"));
                        if (result.sim > 0.8f) {
                            wbcount1 = 0;
                            err = 0;
                            mFairy.onTap(0.8f, result, "点开宝图", 2000);
                            return;
                        }
                    }


                    if (AtFairyConfig.getOption("cbthj").equals("1")) {
                        result = mFairy.findPic(630, 123, 1126, 618, "digMap4.png");
                        LtLog.e(mFairy.getLineInfo(0.85f, result, "开封的黄金藏宝图"));
                        if (result.sim > 0.8f) {
                            wbcount1 = 0;
                            err = 0;
                            mFairy.onTap(0.8f, result, "点开宝图", 2000);
                            return;
                        }
                    }
                }
            }

            int wbcount = 0;

            public void content_4() throws Exception {
                if (overtime(15, 0)) return;
                result1 = mFairy.findPic("Digtreasure.png");
                if (result1.sim > 0.8f) {

                    boolean bool = false;
                    while (mFairy.condit()) {
                        if (AtFairyConfig.getOption("cbtpt").equals("1")) {
                            result = mFairy.findPic(941, 291, 1154, 435, "digMap1.png");
                            LtLog.e(mFairy.getLineInfo(0.8f, result, "开封的普通藏宝图"));
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result1, "挖图", 3000);
                                bool = true;
                                break;
                            }
                        }
                        if (AtFairyConfig.getOption("cbtzj").equals("1")) {
                            result = mFairy.findPic(941, 291, 1154, 435, "digMap2.png");
                            LtLog.e(mFairy.getLineInfo(0.8f, result, "开封的中级藏宝图"));
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result1, "挖图", 3000);
                                bool = true;
                                break;
                            }
                        }
                        if (AtFairyConfig.getOption("cbtgj").equals("1")) {
                            result = mFairy.findPic(941, 291, 1154, 435, "digMap3.png");
                            LtLog.e(mFairy.getLineInfo(0.8f, result, "开封的高级藏宝图"));
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result1, "挖图", 3000);
                                bool = true;
                                break;
                            }
                        }
                        if (AtFairyConfig.getOption("cbthj").equals("1")) {
                            result = mFairy.findPic(941, 291, 1154, 435, "digMap4.png");
                            LtLog.e(mFairy.getLineInfo(0.8f, result, "开封的黄金藏宝图"));
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result1, "挖图", 3000);
                                bool = true;
                                break;
                            }
                        }
                        break;
                    }

                    if (bool) {
                        err = 0;
                        wbcount++;
                        if (wbcount > 30) {
                            LtLog.e(mFairy.getLineInfo("可能挖宝图卡住了移动一下"));
                            mFairy.ranSwipe(202, 574, 206, 498, 200, 1000);
                            wbcount = 0;
                        }
                    } else {
                        wbcount = 0;
                    }
                } else {
                    wbcount = 0;
                }
                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    err = 0;
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "副本中开启挂机", Sleep);
                }

                if (AtFairyConfig.getOption("cs").equals("1")) {
                    result = mFairy.findPic("mapCoverup.png");
                    mFairy.onTap(0.8f, result, 790, 423, 791, 424, "宝图包满", Sleep);

                    result = mFairy.findPic("chushou.png");
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 20; i++) {
                            mFairy.onTap(0.8f, result, "出售", Sleep);
                        }
                        setTaskName(0);
                    }
                } else {
                    result = mFairy.findPic("mapCoverup.png");
                    mFairy.onTap(0.8f, result, 484, 426, 485, 427, "宝图包满", Sleep);
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return;
                    }
                }
                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);
            }
        }.taskContent(mFairy, "挖宝图任务中");
    }//挖宝图

    public void sports() throws Exception {

        new SingleTask(mFairy) {

            int erri;

            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("Gangsports.png", 4);
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

                result = mFairy.findPic(7, 185, 74, 454, "Leftsports.png");
                mFairy.onTap(0.7f, result, "左侧跑商", 2500);

                if (result.sim > 0.7f) {
                    setTaskName(3);
                    return;
                }
                super.content_2();
            }

            int lujing = 0;

            public void content_3() throws Exception {
                if (overtime(200, 0)) return;
                super.content_3();
                result = mFairy.findPic(894, 8, 1273, 710, "Rightsports.png");
                mFairy.onTap(0.8f, result, "右侧跑商", Sleep);

                result = mFairy.findPic(7, 185, 74, 454, "Leftsports.png");
                LtLog.e(mFairy.getLineInfo(0.7f, result, "左侧跑商"));
                if (result.sim > 0.7f) {
                    result = mFairy.findPic(1, 186, 242, 460, new String[]{"Leftsidegold.png", "bs1.png"});
                    LtLog.e(mFairy.getLineInfo(0.7f, result, "左侧金老板"));
                    if (result.sim > 0.7f) {
                        setTaskName(4);
                        return;
                    }
                    result = mFairy.findPic(1, 186, 242, 460, "Leftqianboss.png");
                    LtLog.e(mFairy.getLineInfo(0.7f, result, "左侧钱老板"));
                    if (result.sim > 0.7f) {
                        lujing = 1;
                        setTaskName(4);
                        return;
                    }
                }

            }

            public void content_4() throws Exception {
                LtLog.e(mFairy.getLineInfo("从帮会去杭州"));
                gameUtil.coordinate("帮会", 23, 66);
                setTaskName(5);
            }

            public void content_5() throws Exception {
                if (overtime(200, 6)) return;
                result = mFairy.findPic(1127, 1, 1279, 33, "hangzhou.png");
                if (result.sim > 0.9f) {
                    LtLog.e(mFairy.getLineInfo("从杭州去镇郊荒野"));
                    gameUtil.coordinate("杭州", 153, 235);
                    setTaskName(6);
                }
            }

            public void content_6() throws Exception {
                if (overtime(200, 6)) {
                    gameUtil.coordinate("杭州", 153, 235);
                    return;
                }

                result = mFairy.findPic(1127, 1, 1279, 40, "zhenjiaohuangye.png");
                LtLog.e(mFairy.getLineInfo(0.82f, result, "到镇郊荒野了"));
                if (result.sim > 0.82f) {
                    err = 0;
                    for (int i = 0; i < 300; i++) {

                        result = mFairy.findPic("Hangup1.png");
                        mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", Sleep);

                        result = mFairy.findPic("Lookgoods.png");
                        mFairy.onTap(0.8f, result, "查看货物", Sleep);

                        result = mFairy.findPic(423, 500, 866, 565, "Thieves1.png");
                        LtLog.e(mFairy.getLineInfo(0.8f, result, "山贼被驱逐"));
                        if (result.sim > 0.8f) {
                            i = 250;
                        }

                        result = mFairy.findPic(423, 500, 866, 565, "Thieves2.png");
                        LtLog.e(mFairy.getLineInfo(0.8f, result, "山贼弄坏了车"));
                        if (result.sim > 0.8f) {
                            i = 250;
                        }

                        result = mFairy.findPic(423, 500, 866, 565, "Thieves3.png");
                        LtLog.e(mFairy.getLineInfo(0.8f, result, "山贼出现了"));
                        if (result.sim > 0.8f) {
                            i = 200;
                        }

                        result = mFairy.findPic(423, 500, 866, 565, "Thieves4.png");
                        LtLog.e(mFairy.getLineInfo(0.8f, result, "偷跑的老板出现了"));
                        if (result.sim > 0.8f) {
                            i = 200;
                        }

                        LtLog.e(mFairy.getLineInfo("等待被打烂中"));
                        Thread.sleep(1000);

                    }

                    for (int i = 0; i < 200; i++) {

                        LtLog.e(mFairy.getLineInfo("等待杀了强盗中"));

                        result = mFairy.findPic("Hangup.png");
                        mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", 3000);

                        result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                        mFairy.onTap(0.8f, result, "默认复活", Sleep);
                        if (result.sim > 0.8f) {
                            i = 0;
                        }

                        result = mFairy.findPic(423, 500, 866, 565, "Thieves1.png");
                        LtLog.e(mFairy.getLineInfo(0.8f, result, "山贼被驱逐"));
                        if (result.sim > 0.8f) {
                            i = 150;
                        }

                        Thread.sleep(1000);
                    }
                    result = mFairy.findPic("Hangup1.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", 3000);

                    if (lujing == 1) {
                        //兰若寺
                        gameUtil.coordinate("镇郊荒野", 75, 16);
                    } else {
                        gameUtil.coordinate("镇郊荒野", 17, 22);
                        //杨家镇
                    }
                    erri = 0;

                    setTaskName(7);
                    huoche = 0;
                }
            }


            int huoche = 0;

            public void content_7() throws Exception {
                if (overtime(20, 7)) {
                    erri++;
                    if (erri > 2) {
                        setTaskEnd();
                    } else {
                        huoche = 0;
                        gameUtil.close(1);
                    }
                    return;
                }

                result = mFairy.findPic(1127, 1, 1279, 33, "yangjiazhen.png");
                mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "到杨家镇了", Sleep);

                result = mFairy.findPic(1127, 1, 1279, 33, "30level.png");
                mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "到兰若寺了", Sleep);

                result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                mFairy.onTap(0.8f, result, "选择城市", Sleep);

                result = mFairy.findPic("Mapinterface.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("地图界面"));
                    Thread.sleep(5000);
                    huoche++;
                    if (huoche > 3) {

                        result = mFairy.findPic(874, 77, 1120, 349, "huoche1.png");
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic(111, 42, 791, 557, "huoche.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "点击货车", 3000);
                                mFairy.onTap(1202, 28, 1221, 43, "", 12000);

                                result = mFairy.findPic("Hangup.png");
                                mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", 10000);

                                result = mFairy.findPic("Hangup1.png");
                                mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", Sleep);

                            }
                        }
                        huoche = 0;
                        return;
                    }

                    mFairy.onTap(0.8f, result, 153, 82, 154, 83, "地图界面打开npc列表", 5000);
                    if (result.sim > 0.8f) {

                        for (int i = 0; i < 10; i++) {
                            result = mFairy.findPic(108, 111, 282, 411, new String[]{"jinlaoban.png", "qianlaoban.png"});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "金老板，钱老板", 2000);

                                result = mFairy.findPic(new String[]{"hw.png", "bs2.png"});
                                if (result.sim > 0.8f) {
                                    setTaskEnd();
                                    return;
                                }

                                setTaskName(8);
                                Thread.sleep(20000);

                                return;
                            } else {

                                mFairy.ranSwipe(185, 360, 185, 146, 1000, 1000);
                            }
                        }
                    }
                }
            }

            public void content_8() throws Exception {
                if (overtime(15, 7)) return;


                result = mFairy.findPic(894, 8, 1273, 710, "Rightsports.png");
                mFairy.onTap(0.8f, result, "右侧跑商", Sleep);
                if (result.sim > 0.8f) {
                    Thread.sleep(10000);
                    setTaskEnd();
                    return;
                } else {
                    result = mFairy.findPic("dt.png");
                    mFairy.onTap(0.8f, result, 1208, 29, 1222, 47, "关闭地图", 1000);
                }

                /*result = mFairy.findPic(7, 185, 74, 454, "Leftsports.png");
                mFairy.onTap(0.7f, result, "左侧跑商", 2500);*/


                result = mFairy.findPic(new String[]{"Giveuptask.png", "Giveuptask1.png", "fangqi.png"});
                mFairy.onTap(0.8f, result, "任务失败放弃", 3000);
                mFairy.onTap(0.8f, result, 796, 424, 797, 425, "任务失败放弃", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }
            }

        }.taskContent(mFairy, "帮会跑商任务中");
    }//帮会跑商

    public void plot() throws Exception {
        new SingleTask(mFairy) {
            int jqdj = 0, jqdj1 = 0;
            int erri;
            ControlSplit controlSplit = new ControlSplit();

            public void create() throws Exception {
                if (AtFairyConfig.getOption("opcount1").equals("")) {
                    setTaskEnd();
                    return;
                } else {
                    controlSplit = strSplit(AtFairyConfig.getOption("opcount1"));
                    if (controlSplit.choice == 1) {
                        jqdj = (controlSplit.count / 5) - 1;
                        jqdj1 = (controlSplit.count / 5) - 1;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }

                //  gameUtil.retire();

            }

            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                gameUtil.callToFollow();
                int ret = gameUtil.mission("Plot.png", 2);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(3);
                }
            }

            public void content_3() throws Exception {
                if (overtime(10, 4)) return;
                Thread.sleep(500);

                result = mFairy.findPic("Plotinterface.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic("plotover.png");
                    if (result.sim > 0.75f) {
                        LtLog.e(mFairy.getLineInfo("副本完了"));
                        setTaskEnd();
                        return;
                    }


                    LtLog.e(mFairy.getLineInfo("剧情界面"));
                    mFairy.onTap(882, 141, 883, 142, "普通", Sleep);
                    mFairy.onTap(882, 141, 883, 142, "普通", Sleep);
                    setTaskName(4);
                    return;
                }


                result = mFairy.findPic(102, 22, 1210, 711, "boss.png");
                mFairy.onTap(0.8f, result, result.x - 50, result.y + 30, result.x - 49, result.y + 31, "收起boss栏", Sleep);
            }

            public void content_4() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic("Plotinterface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("剧情界面"));

                    mFairy.onTap(275, 149, 276, 150, "等级栏", 2000);

                    LtLog.e(mFairy.getLineInfo("当前等级级别：" + jqdj));

                    if (jqdj > 5 && jqdj <= 10) {
                        mFairy.onTap(181, 206, 237, 212, "35-55等级范围", Sleep);
                    }
                    if (jqdj > 10 && jqdj <= 15) {
                        mFairy.onTap(183, 233, 243, 239, "60-80等级范围", Sleep);
                    }
                    if (jqdj > 15 && jqdj <= 20) {
                        mFairy.onTap(189, 256, 245, 264, "85-105等级范围", Sleep);
                    }
                    if (jqdj > 20 && jqdj <= 25) {
                        mFairy.onTap(187, 278, 261, 289, "110-130等级范围", Sleep);
                    }
                    if (jqdj > 25 && jqdj <= 30) {
                        mFairy.onTap(189, 310, 263, 314, "135-150等级范围", Sleep);
                    }

                    Thread.sleep(2000);

                    result = mFairy.findPic(102, 22, 1210, 711, "boss.png");
                    mFairy.onTap(0.8f, result, result.x - 50, result.y + 30, result.x - 49, result.y + 31, "收起boss栏", Sleep);
                    erri = 0;
                    setTaskName(5);
                }
            }

            public void content_5() throws Exception {
                if (overtime(100, 99)) return;
                result = mFairy.findPic("Plotinterface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("剧情界面"));

                    erri++;
                    if (erri > 2) {
                        setTaskName(4);
                        return;
                    }
                    int type = 1;
                    if (jqdj > 5 && jqdj <= 10) {
                        type = jqdj - 5;
                    }

                    if (jqdj > 10 && jqdj <= 15) {
                        type = jqdj - 10;
                    }

                    if (jqdj > 15 && jqdj <= 20) {
                        type = jqdj - 15;
                    }

                    if (jqdj > 20 && jqdj <= 25) {
                        type = jqdj - 20;
                    }

                    if (jqdj > 25 && jqdj <= 30) {
                        type = jqdj - 25;
                    }

                    if (type == 1) {
                        mFairy.onTap(291, 545, 292, 546, "重温", Sleep);
                    }
                    if (type == 2) {
                        mFairy.onTap(488, 545, 489, 546, "重温", Sleep);
                    }
                    if (type == 3) {
                        mFairy.onTap(685, 545, 686, 546, "重温", Sleep);
                    }
                    if (type == 4) {
                        mFairy.onTap(878, 542, 879, 543, "重温", Sleep);
                    }
                    if (type == 5) {
                        mFairy.onTap(1074, 542, 1075, 543, "重温", Sleep);
                    }
                    Thread.sleep(5000);

                }


                result = mFairy.findPic(373, 203, 906, 483, "Copydetermination.png");
                mFairy.onTap(0.8f, result, 800, 422, 801, 423, "确定进副本", 3000);

                result = mFairy.findPic("nocopy.png");
                mFairy.onTap(0.8f, result, 485, 425, 486, 426, "副本少人", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(373, 203, 906, 483, "yigeren.png");
                mFairy.onTap(0.8f, result, 466, 431, 504, 448, "副本少人", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(389, 227, 894, 386, "zsqw.png");
                mFairy.onTap(0.8f, result, 793, 422, 794, 423, "只身前往", Sleep);

                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    setTaskName(6);
                }
            }

            int fbX = 0, fbY = 0;

            public void content_6() throws Exception {
                if (overtime(30, 0)) return;

                if (jqdj1 == 6) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", Sleep);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 594;
                        fbY = 572;
                    }
                }

                if (jqdj1 == 8) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", Sleep);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 556;
                        fbY = 443;
                    }
                }

                if (jqdj1 == 12) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 725;
                        fbY = 377;
                    }
                }

                if (jqdj1 == 17) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 800;
                        fbY = 379;
                    }
                }

                if (jqdj1 == 15) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 609;
                        fbY = 126;
                    }
                }

                if (jqdj1 == 19) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 683;
                        fbY = 485;
                    }
                }
                if (jqdj1 == 23) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 506;
                        fbY = 243;
                    }
                }

                if (jqdj1 == 24) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 674;
                        fbY = 257;
                    }
                }

                if (jqdj1 == 28) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 467;
                        fbY = 294;
                    }
                }

                Thread.sleep(3000);

                result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                mFairy.onTap(0.8f, result, "选择城市", Sleep);
                //518,545

                result = mFairy.findPic("Mapinterface.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(2000);
                    mFairy.onTap(0.8f, result, fbX, fbY, fbX + 1, fbY + 1, "地图界面", Sleep);
                    gameUtil.close(0);
                }
                setTaskName(7);
            }

            public void content_7() throws Exception {
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 3) {
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    setTaskName(8);
                }
            }

            public void content_8() throws Exception {
                if (overtime(5, 0)) return;
                result = mFairy.findPic(389, 227, 894, 386, "zsqw.png");
                mFairy.onTap(0.8f, result, 793, 422, 794, 423, "只身前往", Sleep);

                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "副本中开启挂机", Sleep);
                }
                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("ydfh").equals("1")) {
                        result = mFairy.findPic(445, 134, 827, 579, "ydfh.png");
                        mFairy.onTap(0.8f, result, "原地复活", Sleep);
                    } else {
                        mFairy.onTap(0.8f, result, "默认复活", Sleep);
                    }
                }
                result = mFairy.findPic("plotover.png");
                if (result.sim > 0.75f) {
                    LtLog.e(mFairy.getLineInfo("副本完了"));
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "普通剧情重温中");
    }//普通剧情重温

    public void plot1() throws Exception {
        new SingleTask(mFairy) {
            int jqdj = 0, jqdj1 = 0;
            int erri = 0;
            ControlSplit controlSplit = new ControlSplit();

            public void content_0() throws Exception {
                if (AtFairyConfig.getOption("opcount1").equals("")) {
                    setTaskEnd();
                    return;
                } else {
                    controlSplit = strSplit(AtFairyConfig.getOption("opcount1"));
                    if (controlSplit.choice == 1) {
                        jqdj = (controlSplit.count / 5) - 1;
                        jqdj1 = (controlSplit.count / 5) - 1;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
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
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(252, 84, 705, 120, "hero1.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("目标为精英剧情进行下一步"));
                    result = mFairy.findPic("zdpp.png");
                    mFairy.onTap(0.8f, result, "自动匹配", Sleep);
                    gameUtil.close(1);
                    setTaskName(3);
                    return;
                } else {
                    result = mFairy.findPic("Cancelingmatch.png");
                    mFairy.onTap(0.8f, result, "需要切换目标,取消匹配", Sleep);

                    result = mFairy.findPic("zd1.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("调整目标界面"));
                        result = mFairy.findPic(334, 132, 639, 545, "hero.png");
                        mFairy.onTap(0.8f, result, "目标为精英剧情进行下一步", Sleep);
                        if (result.sim > 0.8f) {
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.ranSwipe(714, 314, 715, 451, 200, 10);
                            mFairy.onTap(0.8f, result, 786, 561, 787, 562, "确定", 2000);
                            result = mFairy.findPic("zdpp.png");
                            mFairy.onTap(0.8f, result, "自动匹配", Sleep);
                            gameUtil.close(1);
                            setTaskName(3);
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

            public void content_3() throws Exception {
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
                    LtLog.e(mFairy.getLineInfo("人满了出发"));
                    setTaskName(4);
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
                Thread.sleep(3000);
            }

            public void content_4() throws Exception {
                gameUtil.callToFollow();
                int ret = gameUtil.mission("Plot.png", 2);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(5);
                }
            }

            public void content_5() throws Exception {
                if (overtime(10, 6)) return;
                Thread.sleep(500);
                result = mFairy.findPic("Plotinterface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("剧情界面"));
                    mFairy.onTap(1027, 144, 1028, 145, "英雄", Sleep);
                    mFairy.onTap(1027, 144, 1028, 145, "英雄", Sleep);
                    setTaskName(6);
                    return;
                }

                result = mFairy.findPic("plotover.png");
                if (result.sim > 0.75f) {
                    LtLog.e(mFairy.getLineInfo("副本完了"));
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(102, 22, 1210, 711, "boss.png");
                mFairy.onTap(0.8f, result, result.x - 50, result.y + 30, result.x - 49, result.y + 31, "收起boss栏", Sleep);

            }

            public void content_6() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic("Plotinterface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("剧情界面"));
                    mFairy.onTap(275, 149, 276, 150, "等级栏", 2000);
                    if (jqdj > 5 && jqdj <= 10) {
                        mFairy.onTap(181, 206, 237, 212, "35-55等级范围", Sleep);
                    }
                    if (jqdj > 10 && jqdj <= 15) {
                        mFairy.onTap(183, 233, 243, 239, "60-80等级范围", Sleep);

                    }
                    if (jqdj > 15 && jqdj <= 20) {
                        mFairy.onTap(189, 256, 245, 264, "85-105等级范围", Sleep);
                    }
                    if (jqdj > 20 && jqdj <= 25) {
                        mFairy.onTap(187, 278, 261, 289, "110-130等级范围", Sleep);
                    }
                    if (jqdj > 25 && jqdj <= 30) {
                        mFairy.onTap(189, 310, 263, 314, "135-150等级范围", Sleep);
                    }
                    erri = 0;
                    setTaskName(7);
                }
            }

            public void content_7() throws Exception {
                if (overtime(100, 99)) return;
                result = mFairy.findPic("Plotinterface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("剧情界面"));

                    erri++;
                    if (erri > 2) {
                        setTaskName(6);
                        return;
                    }

                    if (jqdj > 5 && jqdj <= 10) {
                        jqdj = jqdj - 5;
                    }
                    if (jqdj > 10 && jqdj <= 15) {
                        jqdj = jqdj - 10;
                    }
                    if (jqdj > 15 && jqdj <= 20) {
                        jqdj = jqdj - 15;
                    }
                    if (jqdj > 20 && jqdj <= 25) {
                        jqdj = jqdj - 20;
                    }
                    if (jqdj > 25 && jqdj <= 30) {
                        jqdj = jqdj - 25;
                    }
                    if (jqdj == 1) {
                        mFairy.onTap(291, 545, 292, 546, "重温", Sleep);
                    }
                    if (jqdj == 2) {
                        mFairy.onTap(488, 545, 489, 546, "重温", Sleep);
                    }
                    if (jqdj == 3) {
                        mFairy.onTap(685, 545, 686, 546, "重温", Sleep);
                    }
                    if (jqdj == 4) {
                        mFairy.onTap(878, 542, 879, 543, "重温", Sleep);
                    }
                    if (jqdj == 5) {
                        mFairy.onTap(1074, 542, 1075, 543, "重温", Sleep);
                    }
                    Thread.sleep(5000);
                }
                result = mFairy.findPic(373, 203, 906, 483, "Copydetermination.png");
                mFairy.onTap(0.8f, result, 800, 422, 801, 423, "确定进副本", 3000);

                result = mFairy.findPic(373, 203, 906, 483, "nocopy.png");
                mFairy.onTap(0.8f, result, 485, 425, 486, 426, "副本少人", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(373, 203, 906, 483, "yigeren.png");
                mFairy.onTap(0.8f, result, 466, 431, 504, 448, "副本少人", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic(389, 227, 894, 386, "zsqw.png");
                mFairy.onTap(0.8f, result, 793, 422, 794, 423, "只身前往", Sleep);

                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    setTaskName(8);
                }
            }

            int fbX = 0, fbY = 0;

            public void content_8() throws Exception {
                if (overtime(30, 0)) return;

                if (jqdj1 == 6) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 594;
                        fbY = 572;
                    }
                }

                if (jqdj1 == 8) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 556;
                        fbY = 443;
                    }
                }

                if (jqdj1 == 12) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 725;
                        fbY = 377;
                    }
                }

                if (jqdj1 == 17) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 800;
                        fbY = 379;
                    }
                }

                if (jqdj1 == 15) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 609;
                        fbY = 126;
                    }
                }

                if (jqdj1 == 19) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 683;
                        fbY = 485;
                    }
                }
                if (jqdj1 == 23) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 506;
                        fbY = 243;
                    }
                }
                if (jqdj1 == 24) {
                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    mFairy.onTap(0.8f, result, 1197, 104, 1198, 105, "打开地图", 2000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("副本中"));
                        fbX = 674;
                        fbY = 257;
                    }
                }
                Thread.sleep(3000);

                result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                mFairy.onTap(0.8f, result, "选择城市", Sleep);
                //518,545


                result = mFairy.findPic("Mapinterface.png");
                if (result.sim > 0.8f) {
                    Thread.sleep(2000);
                    mFairy.onTap(0.8f, result, fbX, fbY, fbX + 1, fbY + 1, "地图界面", Sleep);
                    gameUtil.close(0);
                    setTaskName(9);
                }
            }

            public void content_9() throws Exception {
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 3) {
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    setTaskName(10);
                }
            }

            public void content_10() throws Exception {
                if (overtime(5, 0)) return;
                result = mFairy.findPic(389, 227, 894, 386, "zsqw.png");
                mFairy.onTap(0.8f, result, 793, 422, 794, 423, "只身前往", Sleep);

                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "副本中开启挂机", Sleep);
                }
                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("ydfh").equals("1")) {
                        result = mFairy.findPic(445, 134, 827, 579, "ydfh.png");
                        mFairy.onTap(0.8f, result, "原地复活", Sleep);
                    } else {
                        mFairy.onTap(0.8f, result, "默认复活", Sleep);
                    }
                }
                result = mFairy.findPic("plotover.png");
                if (result.sim > 0.75f) {
                    LtLog.e(mFairy.getLineInfo("副本完了"));
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "精英剧情重温中");
    }//精英剧情重温

    public void lsWar() throws Exception {
        new SingleTask(mFairy) {

            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("lswar.png", 3);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(3);
                }
            }

            public void content_3() throws Exception {
                if (overtime(60, 0)) return;
                super.content_3();

                result = mFairy.findPic(894, 8, 1273, 710, new String[]{"lsbz.png"});
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(894, 8, 1273, 710, new String[]{"Rightlswar.png"});
                    mFairy.onTap(0.8f, result, "右侧联赛战龙", Sleep);
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "联赛战龙任务中中");
    }//联赛备战战龙任务

    public void lsFactions() throws Exception {
        new SingleTask(mFairy) {

            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("lswar.png", 3);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(3);
                }
            }

            public void content_3() throws Exception {
                if (overtime(200, 0)) return;
                super.content_3();
                result = mFairy.findPic(894, 8, 1273, 710, new String[]{"lsbz.png"});
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(894, 8, 1273, 710, new String[]{"RightlsFactions.png"});
                    mFairy.onTap(0.8f, result, "右侧联赛门派挑战", Sleep);
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "联赛门派任务中");
    }//联赛备战门派任务

    public void lsroutine() throws Exception {
        new SingleTask(mFairy) {

            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("lswar.png", 3);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(3);
                }
            }

            public void content_2() throws Exception {
                result = mFairy.findPic(894, 8, 1273, 710, new String[]{"lsbz.png"});
                mFairy.onTap(0.8f, result, 722, 314, 746, 329, "点下地板", Sleep);
                result = mFairy.findPic(108, 191, 240, 429, new String[]{"Leftlsroutine.png", "Leftlsroutine1.png", "Leftlsroutine2.png"});
                mFairy.onTap(0.7f, result, result.x + 20, result.y + 20, result.x + 21, result.y + 21, "左侧联赛常规", 2500);
                if (result.sim > 0.7f) {
                    setTaskName(3);
                    return;
                }
                super.content_2();
            }

            public void content_3() throws Exception {
                super.content_3();
                if (overtime(10, 2)) return;
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                result = mFairy.findPic(894, 8, 1273, 710, new String[]{"Rightlsroutine.png", "Rightlsroutine1.png", "Rightlsroutine2.png", "Rightlsroutine3.png", "Rightlsroutine4.png"});
                mFairy.onTap(0.8f, result, "右侧联赛常规", 2000);

                result = mFairy.findPic(894, 8, 1273, 710, new String[]{"lsbz.png"});
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(894, 8, 1273, 710, new String[]{"Rightlsroutine.png", "Rightlsroutine1.png", "Rightlsroutine2.png", "Rightlsroutine3.png", "Rightlsroutine4.png"});
                    mFairy.onTap(0.8f, result, "右侧联赛常规", 2000);
                    if (result.sim > 0.8f) {
                        mFairy.initMatTime();
                    } else {
                        LtLog.e(mFairy.getLineInfo("没有联赛备战常规任务了"));
                        setTaskName(2);
                        return;
                    }
                }


                result = mFairy.findPic("Use.png");
                mFairy.onTap(0.8f, result, "使用", 5000);
                if (result.sim > 0.8f) {
                    dazeTime = 10;
                }

                result = mFairy.findPic("Magicweapon.png");
                mFairy.onTap(0.8f, result, "法宝搜索", Sleep);


                result = mFairy.findPic(813, 97, 1275, 709, new String[]{"Tailor.png", "Blacksmith.png", "Drugstore.png", "zahuopu.png"});
                mFairy.onTap(0.8f, result, "右侧npc铺子", Sleep);


                result = mFairy.findPic("purchase.png");
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic(112, 65, 840, 690, "npcdemand.png");
                    if (result1.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "npc商店的购买需求", Sleep);
                    }
                    result1 = mFairy.findPic(452, 495, 830, 573, "Coverup.png");
                    if (result1.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                        setTaskEnd();
                        return;
                    }
                    for (int i = 0; i < 2; i++) {

                        result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png"});
                        mFairy.onTap(0.7f, result, "叉子2", 2000);

                        result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork3.png", "fork4.png"});
                        mFairy.onTap(0.8f, result, "叉子1", 2000);
                    }
                    dazeTime = 10;
                }

                result = mFairy.findPic(new String[]{"purchase1.png", "purchase2.png"});
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("gmfq").equals("1")) {
                        gameUtil.giveUpTask(new String[]{"Giveupchanggui.png", "Giveupteacher1.png"});
                        setTaskName(0);
                        return;
                    } else {
                        mFairy.onTap(0.8f, result, "玩家商店的购买", Sleep);
                        result1 = mFairy.findPic(452, 495, 830, 573, "Coverup.png");
                        if (result1.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                            setTaskEnd();
                            return;
                        }
                        for (int i = 0; i < 2; i++) {

                            result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png"});
                            mFairy.onTap(0.7f, result, "叉子2", 2000);

                            result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork3.png", "fork4.png"});
                            mFairy.onTap(0.8f, result, "叉子1", 2000);
                        }
                        dazeTime = 10;
                    }
                }

                result = mFairy.findPic("wjsdsure.png");
                mFairy.onTap(0.8f, result, "玩家商店的确认", Sleep);
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic("bgym.png");
                    if (result1.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                        setTaskEnd();
                        return;
                    }
                    gameUtil.close(0);
                    dazeTime = 10;
                }

                result = mFairy.findPic("Handin.png");
                LtLog.e(mFairy.getLineInfo(0.1f, result, "上交"));
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("上交"));
                    Thread.sleep(5000);

                    result = mFairy.findPic(638, 10, 1278, 717, "attribute.png");
                    mFairy.onTap(0.7f, result, 552, 565, 553, 566, "有词条属性,购买其他", Sleep);
                    mFairy.onTap(0.7f, result, 547, 472, 548, 473, "npc商店", Sleep);
                    if (result.sim < 0.7f) {
                        mFairy.onTap(389, 567, 390, 568, "点击上交上交", Sleep);
                    }
                }

                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);

                if (dazeTime > 6) {
                    result = mFairy.findPic(108, 191, 240, 449, new String[]{"Leftlsroutine.png", "Leftlsroutine1.png", "Leftlsroutine2.png"});
                    mFairy.onTap(0.7f, result, result.x + 20, result.y + 20, result.x + 21, result.y + 21, "左侧联赛常规", 2500);
                } else {
                    err = 0;
                }
            }

        }.taskContent(mFairy, "联赛备战常规任务中");
    }//联赛备战常规任务

    public void war() throws Exception {
        new SingleTask(mFairy) {
            public void create() throws Exception {
            }

            public void content_0() throws Exception {
                gameUtil.goCity("帮会");
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("WardragonHall.png", 4);
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
                result = mFairy.findPic(8, 183, 132, 429, new String[]{"Leftbattle.png", "Leftbattle1.png", "zhan1.png"});
                mFairy.onTap(0.7f, result, "左侧战龙", 2500);

                if (result.sim > 0.7f) {
                    setTaskName(3);
                    return;
                }
                super.content_2();
            }

            public void content_3() throws Exception {
                if (overtime(15, 0)) return;
                super.content_3();

                long dazeTime = mFairy.mMatTime(1171, 28, 54, 22, 0.82f);

                result = mFairy.findPic(959, 45, 1063, 598, "zhan2.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(result.x, result.y - 20, result.x + 223, result.y + 35, "zhan3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "点击右侧战龙任务", 1000);
                        mFairy.initMatTime();
                    }
                } else {
                    LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                    result = mFairy.findPic(894, 8, 1273, 710, new String[]{"Rightbattle.png", "Rightlswar.png"});
                    mFairy.onTap(0.8f, result, "右侧战龙", Sleep);
                    if (result.sim > 0.8f) {
                        mFairy.initMatTime();
                    }
                }


                result = mFairy.findPic("Use.png");
                mFairy.onTap(0.8f, result, "使用", 5000);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);
                }

                result = mFairy.findPic("Battlecompleted.png");
                mFairy.onTap(0.8f, result, 640, 415, 641, 416, "战龙完成", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }

                if (dazeTime > 3) {
                    result = mFairy.findPic(8, 183, 132, 481, new String[]{"Leftbattle.png", "Leftbattle1.png", "zhan1.png"});
                    mFairy.onTap(0.7f, result, "左侧战龙", 2500);
                    if (result.sim > 0.7f) {
                        err = 0;
                    }
                } else {
                    err = 0;
                }

                result = mFairy.findPic(new String[]{"Giveuptask.png", "Giveuptask1.png", "fangqi.png"});
                mFairy.onTap(0.8f, result, "任务失败放弃", 3000);
                mFairy.onTap(0.8f, result, 796, 424, 797, 425, "任务失败放弃", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(12, 193, 214, 473, "zlt1.png");
                if (result.sim > 0.7f) {
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);
                }

                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("这个任务打不过放弃"));
                    gameUtil.giveUpTask(new String[]{"DailybattleDragon.png", "DailybattleDragon1.png"});
                    setTaskName(0);
                    return;
                }
            }

        }.taskContent(mFairy, "战龙堂任务中");
    }//战龙堂

    public void factions() throws Exception {
        new SingleTask(mFairy) {

            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("Factions.png", 1);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(3);
                }
            }

            public void content_3() throws Exception {
                super.content_3();
                if (overtime(10, 4)) return;
                result = mFairy.findPic("schoolfull.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("门派没次数了"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(440, 590, 1163, 671, "Challenge.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    long color = mFairy.getColorNum(124, 390, 416, 441, "255,0,0", 0.98f);
                    if (color < 30) {
                        mFairy.onTap(0.8f, result, "挑战", 3000);
                        mFairy.onTap(0.8f, result, 766, 440, 798, 452, "确定", 5000);
                    }
                }

                result = mFairy.findPic(260, 129, 1012, 626, new String[]{"errsure.png"});
                mFairy.onTap(0.8f, result, "挑战确定", 5000);
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    mFairy.onTap(0.8f, result, 1225, 462, 1239, 473, "技能1", 10);
                    mFairy.onTap(0.8f, result, 1108, 459, 1132, 477, "技能2", 10);
                    mFairy.onTap(0.8f, result, 1025, 535, 1043, 548, "技能3", 10);
                    mFairy.onTap(0.8f, result, 1019, 643, 1036, 661, "技能4", 10);
                    mFairy.onTap(0.8f, result, 1147, 573, 1162, 587, "技能5", 10);
                    mFairy.onTap(0.8f, result, 1238, 590, 1254, 600, "绝技1", 10);
                    mFairy.onTap(0.8f, result, 1164, 664, 1175, 681, "绝技2", 10);
                }
            }

            public void content_4() throws Exception {
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 6) {
                    result = mFairy.findPic(108, 191, 240, 449, new String[]{"leftlsFactions.png"});
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("左侧门派联赛挑战"));
                        lsFactions();
                    }
                    setTaskName(5);
                }
            }

            public void content_5() throws Exception {
                if (AtFairyConfig.getOption("mptz1").equals("1")) {
                    LtLog.e(mFairy.getLineInfo("门派挑战1次完成"));
                    setTaskEnd();
                    return;
                }
                int ret = gameUtil.mission("Factions.png", 1);
                if (ret == 0) {
                    setTaskEnd();
                    return;
                } else if (ret == 1) {
                    setTaskName(6);
                }
            }

            public void content_6() throws Exception {
                super.content_3();
                if (overtime(5, 5)) return;
                result = mFairy.findPic("schoolfull.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("门派没次数了"));
                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic(440, 590, 1163, 671, "Challenge.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    long color = mFairy.getColorNum(124, 390, 416, 441, "255,0,0", 0.98f);
                    if (color < 30) {
                        mFairy.onTap(0.8f, result, "挑战", 3000);
                        mFairy.onTap(0.8f, result, 766, 440, 798, 452, "确定", 5000);
                    }
                }

                result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    mFairy.onTap(0.8f, result, 1225, 462, 1239, 473, "技能1", 10);
                    mFairy.onTap(0.8f, result, 1108, 459, 1132, 477, "技能2", 10);
                    mFairy.onTap(0.8f, result, 1025, 535, 1043, 548, "技能3", 10);
                    mFairy.onTap(0.8f, result, 1019, 643, 1036, 661, "技能4", 10);
                    mFairy.onTap(0.8f, result, 1147, 573, 1162, 587, "技能5", 10);
                    mFairy.onTap(0.8f, result, 1238, 590, 1254, 600, "绝技1", 10);
                    mFairy.onTap(0.8f, result, 1164, 664, 1175, 681, "绝技2", 10);
                }
            }

        }.taskContent(mFairy, "门派任务中中");
    }//门派任务

    public void rankingList() throws Exception {
        new SingleTask(mFairy) {


            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1239, 206, 1240, 207, "包裹", Sleep);


                result = mFairy.findPic(1022, 431, 1276, 712, "paihangbang.png");
                mFairy.onTap(0.8f, result, "排行榜", Sleep);

                result = mFairy.findPic(137, 81, 346, 652, "grxx.png");
                mFairy.onTap(0.8f, result, "个人信息", Sleep);


                result = mFairy.findPic(137, 81, 346, 652, "Rankingsinterface.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("排行榜界面"));
                    if (AtFairyConfig.getOption("phb").equals("1")) {
                        result = mFairy.findPic(137, 81, 346, 652, "caifu.png");
                        mFairy.onTap(0.8f, result, "财富排行榜", 10000);
                    }
                    if (AtFairyConfig.getOption("phb").equals("2")) {
                        result = mFairy.findPic(137, 81, 346, 652, "zhuangbei.png");
                        mFairy.onTap(0.8f, result, "装备排行榜", 10000);

                    }
                    if (AtFairyConfig.getOption("phb").equals("3")) {
                        result = mFairy.findPic(137, 81, 346, 652, "zdl.png");
                        mFairy.onTap(0.8f, result, "战斗力排行榜", 10000);
                    }
                    bjcount = 0;
                    setTaskName(2);
                }
            }

            int x = 274, mdcount = 0, bjcount = 0;

            public void content_2() throws Exception {
                if (overtime(10, 0)) return;
                Thread.sleep(2000);
                result = mFairy.findPic(137, 81, 346, 652, "Rankingsinterface.png");
                if (result.sim > 0.8f && bjcount == 0) {
                    result = mFairy.findPic(1050, 46, 1126, 112, new String[]{"fork1.png", "fork2.png", "fork3.png", "fork4.png"});
                    mFairy.onTap(0.7f, result, "叉子", 2000);
                    bjcount = 1;
                    err = 0;
                    mdcount++;
                    if (mdcount > 100) {
                        gameUtil.close(0);
                        setTaskEnd();
                        return;
                    }
                    LtLog.e(mFairy.getLineInfo("排行榜界面"));
                    mFairy.onTap(728, x, 729, x + 1, "选个角色", Sleep);
                    x = x + 74;
                    if (x > 660) {
                        bjcount = 0;
                        x = 274;
                        mFairy.ranSwipe(1058, 649, 1058, 245, 1000, 1500);
                        LtLog.e(mFairy.getLineInfo("下一页"));
                    }
                }
                if (bjcount == 1) {
                    result = mFairy.findPic(307, 65, 1249, 704, "DreamIsland.png");
                    mFairy.onTap(0.8f, result, "梦岛", 3000);
                    if (result.sim > 0.8f) {
                        err = 0;
                    }
                    result = mFairy.findPic(307, 65, 1249, 704, "Woo.png");
                    mFairy.onTap(0.8f, result, "踩一踩", 2000);
                    mFairy.onTap(0.8f, result, 1181, 55, 1187, 62, "踩完了关闭下一个", 2000);
                    if (result.sim > 0.8f) {
                        bjcount = 0;
                        err = 0;
                    }
                }
            }
        }.taskContent(mFairy, "踩梦岛任务中");
    }//踩梦岛

    public void switchedRoles() throws Exception {
        new SingleTask(mFairy) {

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1239, 206, 1240, 207, "活动", Sleep);

                result = mFairy.findPic(1178, 357, 1276, 714, "Settingbutton.png");
                mFairy.onTap(0.8f, result, "设置按钮", Sleep);

                result = mFairy.findPic(514, 574, 1149, 670, "qhjs.png");
                mFairy.onTap(0.8f, result, "切换角色", Sleep);


                result = mFairy.findPic("qhjs1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1079, 519, 1089, 526, "", 1000);
                    mFairy.onTap(1231, 468, 1239, 485, "", 1000);
                    mFairy.onTap(1122, 629, 1160, 644, "", 3000);
                    result = mFairy.findPic("qhjs1.png");
                    if (result.sim < 0.8f) {
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(790, 482, 1270, 706, "Rolelogon.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("角色登录:"));

                    if (TaskMain.list.get(0).equals("1")) {
                        mFairy.onTap(198, 156, 214, 170, "第1个角色", Sleep);
                    }
                    if (TaskMain.list.get(0).equals("2")) {
                        mFairy.onTap(121, 267, 141, 282, "第2个角色", Sleep);
                    }
                    if (TaskMain.list.get(0).equals("3")) {
                        mFairy.onTap(124, 414, 143, 427, "第3个角色", Sleep);
                    }
                    if (TaskMain.list.get(0).equals("4")) {
                        mFairy.onTap(194, 555, 213, 576, "第4个角色", Sleep);
                    }

                    result = mFairy.findPic(790, 482, 1270, 706, "Rolelogon.png");
                    mFairy.onTap(0.8f, result, "角色登录", Sleep);

                    Thread.sleep(3000);

                    result = mFairy.findPic("Role next.png");
                    if (result.sim > 0.8f) {

                        if (AtFairyConfig.getOption("task_id").equals("1809")) {
                            mFairy.onTap(0.8f, result, "发现创建角色", 2000);
                        } else {
                            mFairy.onTap(45, 40, 70, 54, "返回", 1000);
                            TaskMain.list.remove(0);
                            if (TaskMain.list.size() == 0) {
                                LtLog.e(mFairy.getLineInfo("没有角色了结束任务"));
                                setTaskEnd();
                                return;
                            }
                        }

                    } else {
                        result = mFairy.findPic(790, 482, 1270, 706, "Rolelogon.png");
                        if (result.sim < 0.8f) {
                            LtLog.e(mFairy.getLineInfo("切换成功"));
                            setTaskEnd();
                            return;
                        }

                    }
                }
            }
        }.taskContent(mFairy, "切换角色中");
    }//切换角色

    public void makeHome() throws Exception {
        new SingleTask(mFairy) {
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic(1127, 1, 1279, 33, "baoensi.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("到报恩寺了"));
                    setTaskName(4);
                } else {
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                if (overtime(15, 0)) return;

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 387, 682, 388, 683, "点击好友", 3000);
                mFairy.onTap(0.8f, result, 1164, 167, 1165, 168, "切换到好友", Sleep);
                mFairy.onTap(0.8f, result, 1164, 167, 1165, 168, "切换到好友", Sleep);

                result = mFairy.findPic(103, 58, 516, 681, "qvElves.png");
                mFairy.onTap(0.8f, result, "倩女小精灵", Sleep);

                result = mFairy.findPic("qvrd.png");
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic("qvknow.png");
                    mFairy.onTap(0.8f, result1, 616, 411, 649, 422, "倩女知道", Sleep);
                    mFairy.onTap(0.8f, result, 239, 653, 240, 654, "打开输入框", 2000);
                    mFairy.inputText("出家");

                    result1 = mFairy.findPic(1070, 68, 1274, 719, new String[]{"new_textsure.png", "new_textsure1.png"});
                    mFairy.onTap(0.8f, result1, "确定文本", 2000);
                    mFairy.onTap(0.8f, result, "发送", Sleep);
                    setTaskName(3);
                }
            }

            public void content_3() throws Exception {
                if (overtime(50, 0)) return;
                result = mFairy.findPic(243, 86, 1036, 415, "dengqing.png");
                mFairy.onTap(0.8f, result, "德清", Sleep);

                result = mFairy.findPic(813, 97, 1275, 709, "Be cynical.png");
                mFairy.onTap(0.8f, result, "看破红尘", Sleep);

                result = mFairy.findPic(370, 204, 910, 397, "surehome.png");
                mFairy.onTap(0.8f, result, 800, 427, 801, 428, "确定出家", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(4);
                }
            }

            boolean kestop = true;

            public void content_4() throws Exception {
                if (overtime(20, 0)) return;

                super.content_3();

                int h = mFairy.dateHour();
                if (((h >= 15 && h < 17) || (h >= 6 && h < 8)) && kestop) {
                    result = mFairy.findPic(1127, 1, 1279, 33, "baoensi.png");
                    mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "到报恩寺了", Sleep);

                    result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                    mFairy.onTap(0.8f, result, "选择城市", Sleep);

                    result = mFairy.findPic("Mapinterface.png");
                    mFairy.onTap(0.8f, result, 153, 82, 154, 83, "地图界面", 2000);
                    mFairy.onTap(0.8f, result, 172, 128, 202, 145, "主念僧", 10000);
                    if (result.sim > 0.8f) {
                        h = mFairy.dateHour();
                        if (h >= 15 && h < 17) {
                            result = mFairy.findPic(813, 97, 1275, 709, new String[]{"wankexiushen.png"});
                            mFairy.onTap(0.8f, result, "右侧晚课修身", Sleep);
                            gameUtil.close(0);
                            setTaskName(6);
                            kestop = false;
                            return;
                        }
                        if (h >= 6 && h < 8) {
                            result = mFairy.findPic(813, 97, 1275, 709, new String[]{"zaokexiushen.png"});
                            mFairy.onTap(0.8f, result, "右侧早课修身", Sleep);
                            gameUtil.close(0);
                            setTaskName(6);
                            kestop = false;
                            return;
                        }
                    }
                } else {

                    result = mFairy.findPic(4, 187, 100, 274, new String[]{"Left home.png"});
                    mFairy.onTap(0.7f, result, result.x + 20, result.y + 20, result.x + 21, result.y + 21, "左侧出家", Sleep);
                    if (result.sim > 0.7f) {
                        setTaskName(5);
                        return;
                    }
                    mFairy.taskSlid(err, new int[]{0, 2, 4}, 2, 88, 400, 88, 216, 1500, 1000);
                    mFairy.taskSlid(err, new int[]{6}, 3, 88, 400, 88, 216, 1500, 1000);
                    if (err > 6) {
                        result = mFairy.findPic(1127, 1, 1279, 33, "baoensi.png");
                        mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "到报恩寺了", Sleep);

                        result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                        mFairy.onTap(0.8f, result, "选择城市", Sleep);


                        result = mFairy.findPic("Mapinterface.png");
                        mFairy.onTap(0.8f, result, 153, 82, 154, 83, "地图界面", 2000);
                        mFairy.onTap(0.8f, result, 162, 180, 189, 190, "扫地僧", 10000);
                        if (result.sim > 0.8f) {
                            result = mFairy.findPic(813, 97, 1275, 709, new String[]{"fufawubian.png", "chujiajingxin.png"});
                            mFairy.onTap(0.8f, result, "右侧佛法无边", Sleep);
                            if (result.sim > 0.8f) {
                                gameUtil.close(1);
                            } else {
                                setTaskEnd();
                                return;
                            }
                        }
                    }
                }
            }

            public void content_5() throws Exception {
                if (overtime(5, 4)) return;
                super.content_3();
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 5) {
                    result = mFairy.findPic(4, 187, 100, 274, new String[]{"Left home.png"});
                    mFairy.onTap(0.7f, result, result.x + 20, result.y + 20, result.x + 21, result.y + 21, "左侧出家", Sleep);
                } else {
                    err = 0;
                }
                result1 = mFairy.findPic(813, 97, 1275, 709, new String[]{"Right out of the house.png", "Righthouse.png"});
                if (result1.sim > 0.8f) {
                    mFairy.onTap(0.8f, result1, "右侧出家", Sleep);
                    mFairy.initMatTime();
                }
                result = mFairy.findPic(813, 97, 1275, 709, new String[]{"special task.png"});
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("ts").equals("1")) {
                        setTaskEnd();
                        return;
                    }
                    if (AtFairyConfig.getOption("ts").equals("2")) {
                        mFairy.onTap(0.8f, result, 1119, 478, 1120, 479, "右侧特殊任务", Sleep);
                    }
                }
                result = mFairy.findPic("Use.png");
                mFairy.onTap(0.8f, result, "使用", 5000);
                if (result.sim > 0.8f) {
                    err = 0;
                }
            }

            public void content_6() throws Exception {
                if (overtime(10, 4)) return;
                int h = mFairy.dateHour();
                if ((h >= 15 && h < 17) || (h >= 6 && h < 8)) {
                } else {
                    setTaskName(0);
                }
                super.content_3();
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 5) {
                    result = mFairy.findPic(4, 187, 100, 274, new String[]{"Left home.png"});
                    mFairy.onTap(0.7f, result, result.x + 20, result.y + 20, result.x + 21, result.y + 21, "左侧出家", Sleep);
                } else {
                    err = 0;
                }
                result = mFairy.findPic(813, 97, 1275, 709, new String[]{"Righthouse.png"});
                mFairy.onTap(0.8f, result, "右侧出家", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    setTaskName(4);
                }

                result = mFairy.findPic("Use.png");
                mFairy.onTap(0.8f, result, "使用", 5000);
                if (result.sim > 0.8f) {
                    err = 0;
                }
            }
        }.taskContent(mFairy, "出家任务中");
    } //出家

    public void test1() throws Exception {
        while (true) {
            LtLog.e("延时100秒");
            Thread.sleep(100000);
        }
    }//测试


    public void diaoyu() throws Exception {

        int err=20;
        while (mFairy.condit()) {

            Thread.sleep(1000);
            err++;
            if(err>20){
                gameUtil.close(0);
                err=0;
            }


            result = mFairy.findPic(1088, 294, 1190, 381, "dy1.png");
            mFairy.onTap(0.8f, result, "钓鱼按钮", 1000);

            result = mFairy.findPic(1104, 246, 1275, 494, "dy2.png");
            if (result.sim > 0.8f) {
                err=0;
                LtLog.e(mFairy.getLineInfo("钓鱼界面"));
                mFairy.onTap(1087, 560, 1117, 589, "抛竿", 2000);

                int count = 0;
                long newColor=0;
                for (int i = 0; i < 60; i++) {

                    result = mFairy.findPic(955, 416, 1140, 537, "dy3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "装备", 3000);
                        break;
                    }

                    long color = mFairy.getColorNum(1108,615,1136,627,"99,162,197",0.92f);
                    LtLog.e("color:"+color);

                    if(count == 0){
                        newColor = color;
                        count++;
                    }

                    if(color!=newColor){
                        count++;
                        if(count>3){
                            mFairy.onTap(1087, 560, 1117, 589, "收杆", 8000);
                            break;
                        }
                    }
                    Thread.sleep(300);
                }
            }

        }
    }

}
