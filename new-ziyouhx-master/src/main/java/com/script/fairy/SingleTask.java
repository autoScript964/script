package com.script.fairy;


import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.List;
import java.util.Random;


/**
 * Created by Administrator on 2019/3/25 0025.
 */
public class SingleTask extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    FindResult result2;
    GameUtil gameUtil;

    public SingleTask(AtFairyImpl ATFairy) throws Exception {
        mFairy = ATFairy;
        gameUtil = new GameUtil(mFairy);
    }











    //新手引导
    public void novice() throws Exception {
        new SingleTask(mFairy) {
            public void inOperation() throws Exception {
                if (!AtFairyConfig.getTaskID().equals("2033") && !AtFairyConfig.getTaskID().equals("2035")) {
                    result = mFairy.findPic(1144, 1, 1240, 31, new String[]{"llhj.png", "huanjing.png"});
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 10; i++) {
                            result = mFairy.findPic(1144, 1, 1240, 31, new String[]{"llhj.png", "huanjing.png"});
                            if (result.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("洗刷刷中暂停别的任务"));
                                i = 0;
                            }
                            Thread.sleep(2000);
                        }
                    }
                }

                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);

                result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork.png", "fork1.png"});
                if (picCount(0.8f, result, "叉") > 5) {
                    mFairy.onTap(0.9f, result, "关叉", Sleep);
                }

                result = mFairy.findPic(1040, 6, 1270, 36, "jx.png");
                mFairy.onTap(0.8f, result, "对话", Sleep);


                result = mFairy.findPic("equipment.png");
                mFairy.onTap(0.8f, result, "装备", Sleep);

                result = mFairy.findPic("npcpurchase.png");
                mFairy.onTap(0.8f, result, "npc商店购买", Sleep);

                result = mFairy.findPic(new String[]{"In picture.png", "In picture1.png"});
                LtLog.e(mFairy.getLineInfo("过图：" + result.sim));
                if (result.sim > 0.92f) {
                    LtLog.e(mFairy.getLineInfo("过图中"));
                    mFairy.initMatTime();
                    err = 0;
                    picCountMapS.clear();
                    picCountMap.clear();
                }
                result = mFairy.findPic(511, 6, 805, 437, "Pathfinding.png");
                if (result.sim > 0.9f) {
                    LtLog.e(mFairy.getLineInfo("寻路中"));
                    mFairy.initMatTime();
                    err = 0;
                    picCountMapS.clear();
                    picCountMap.clear();
                }
                result = mFairy.findPic("dutiao.png");
                if (result.sim > 0.9f) {
                    LtLog.e(mFairy.getLineInfo("读条中"));
                    mFairy.initMatTime();
                    err = 0;
                    picCountMapS.clear();
                    picCountMap.clear();
                }
            }

            int m;

            //主线
            @Override
            public void content_0() throws Exception {
                long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                if (dazeTime > 3) {
                    mFairy.initMatTime();
                    m++;
                    result = mFairy.findPic(40, 124, 282, 411, "main.png");
                    if (result.sim > 0.7f) {
                        m = 0;
                        mFairy.onTap(0.7f, result, "左侧主线", Sleep);
                    }

                    if (m >= 5) {
                        result = mFairy.findPic(40, 124, 282, 411, "jingyan.png");
                        mFairy.onTap(0.7f, result, "左侧经验任务", Sleep);
                        if (result.sim > 0.7f) {
                            setTaskName(1);
                            return;
                        }
                    }
                }

                result = mFairy.findPic(1008, 172, 1160, 636, "zhuxianj.png");
                mFairy.onTap(0.8f, result, "右侧主线", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                }

                result = mFairy.findPic("other.png");
                mFairy.onTap(0.8f, result, 376, 400, 409, 427, "金蛋砸开", Sleep);

                result = mFairy.findPic("dialogue.png");
                mFairy.onTap(0.8f, result, "对话", Sleep);

                result = mFairy.findPic("other1.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("连接脉络"));
                    mFairy.touchDown(2, 653, 143);
                    mFairy.touchMove(2, 513, 308, 500);
                    mFairy.touchMove(2, 737, 270, 500);
                    mFairy.touchMove(2, 781, 411, 500);
                    mFairy.touchMove(2, 627, 518, 500);
                    mFairy.touchUp(2);
                }

                result = mFairy.findPic("other9.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("玉石碎片"));
                    mFairy.ranSwipe(311, 186, 731, 220, 500, (long) 500, 2);
                    mFairy.ranSwipe(921, 461, 507, 224, 500, (long) 500, 2);
                    mFairy.ranSwipe(275, 488, 612, 294, 500, (long) 500, 2);
                    mFairy.ranSwipe(1051, 157, 611, 530, 500, (long) 500, 2);
                }
                result = mFairy.findPic("Imperial examination interface.png");
                mFairy.onTap(0.8f, result, 598, 393, 639, 411, "科举界面选A", Sleep);


                result = mFairy.findPic("other2.png");
                mFairy.onTap(0.8f, result, "装备并出站", Sleep);

                result = mFairy.findPic("other3.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("移入凹槽"));
                    mFairy.ranSwipe(988, 56, 532, 399, 500, (long) 500, 2);
                    mFairy.ranSwipe(988, 197, 740, 393, 500, (long) 500, 2);
                    mFairy.ranSwipe(985, 347, 635, 229, 500, (long) 500, 2);
                }

                result = mFairy.findPic("other4.png");
                mFairy.onTap(0.8f, result, "酷比小课堂", Sleep);

                result = mFairy.findPic("other5.png");
                mFairy.onTap(0.8f, result, "前往挑战", Sleep);

                result = mFairy.findPic("copy.png");
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    gameUtil.zidong();
                }

                result = mFairy.findPic("other6.png");
                mFairy.onTap(0.8f, result, "进入冲锋战场", Sleep);

                result = mFairy.findPic("performance model.png");
                mFairy.onTap(0.8f, result, 627, 477, 646, 490, "选择性能模式", 1000);

                result = mFairy.findPic("chongfengmap.png");
                mFairy.onTap(0.8f, result, 1189, 88, 1205, 102, "打开地图", Sleep);
                mFairy.onTap(0.8f, result, 546, 327, 558, 337, "去地图中间", Sleep);
                mFairy.onTap(0.8f, result, 1229, 40, 1244, 53, "关闭地图", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 200);
                    mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 200);
                    mFairy.onTap(0.8f, result, 1042, 483, 1057, 497, "技能3", 200);
                    mFairy.onTap(0.8f, result, 1008, 568, 1024, 585, "技能4", 200);
                    mFairy.onTap(0.8f, result, 1049, 655, 1064, 671, "技能5", 200);
                    mFairy.onTap(0.8f, result, 959, 656, 972, 663, "技能6", 200);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                }
                result = mFairy.findPic("Off the battlefield.png");
                mFairy.onTap(0.8f, result, "离开战场", Sleep);
            }

            //经验
            public void content_1() throws Exception {
                long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                if (dazeTime > 3) {
                    mFairy.initMatTime();
                    result = mFairy.findPic(40, 124, 282, 411, "jingyan.png");
                    mFairy.onTap(0.7f, result, "左侧经验任务", Sleep);
                    if (picCountS(0.7f, result, "新手经验任务") > 5) {
                        setTaskName(0);
                        return;
                    }

                    result = mFairy.findPic(40, 124, 282, 411, "leftxianyuan.png");
                    mFairy.onTap(0.8f, result, "左侧仙缘", Sleep);
                    if (result.sim > 0.8f) {
                        setTaskName(2);
                        return;
                    }
                }


                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"Rightjingyan.png", "Rightjingyan1.png", "Rightjingyan2.png", "Rightjingyan3.png", "Rightjingyan4.png", "Rightjingyan5.png", "Rightjingyan6.png", "Rightjingyan7.png", "Rightxianyuan.png"});
                mFairy.onTap(0.8f, result, "右侧经验", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                }

                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"Rightxsstop.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("新手任务结束"));
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("Learning skills.png");
                mFairy.onTap(0.7f, result, "经验学习技能", Sleep);
                mFairy.onTap(0.8f, result, 1162, 88, 1177, 102, "经验学习技能关闭", Sleep);

                result = mFairy.findPic("Writing mood.png");
                mFairy.onTap(0.8f, result, "写心情", Sleep);
                mFairy.onTap(0.8f, result, 339, 222, 359, 234, "点击输入框", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.inputText("我爱云派");
                    Thread.sleep(2000);
                    mFairy.condit();
                    result = mFairy.findPic(1185, 130, 1276, 212, new String[]{"new_textsure.png", "new_textsure1.png", "new_textsure2.png"});
                    mFairy.onTap(0.8f, result, "确定文本", 2000);
                    mFairy.onTap(0.8f, result, 772, 533, 809, 552, "发表", 10000);
                    result = mFairy.findPic("Writing mood.png");
                    mFairy.onTap(0.8f, result, 560, 130, 575, 142, "购买礼物", 3000);
                    mFairy.onTap(0.8f, result, 573, 600, 611, 616, "购买", 3000);
                    mFairy.onTap(0.8f, result, 1213, 97, 1227, 109, "关闭空间", 3000);
                }
                result = mFairy.findPic(845, 163, 1168, 658, "tapzan.png");
                mFairy.onTap(0.8f, result, "经验点赞", Sleep);
                mFairy.onTap(0.8f, result, 1213, 97, 1227, 109, "经验点赞关闭", Sleep);

                result = mFairy.findPic("other7.png");
                mFairy.onTap(0.8f, result, 1133, 474, 1154, 485, "经验光武问答", Sleep);

                result = mFairy.findPic("Strengthen.png");
                mFairy.onTap(0.8f, result, "经验强化", Sleep);

                result = mFairy.findPic("Strengthen1.png");
                mFairy.onTap(0.8f, result, "经验强化1", Sleep);
                mFairy.onTap(0.8f, result, 1162, 88, 1177, 102, "经验强化关闭", Sleep);
                mFairy.onTap(0.8f, result, 1162, 88, 1177, 102, "经验强化关闭", Sleep);

                result = mFairy.findPic("other8.png");
                mFairy.onTap(0.8f, result, "经验一键申请", Sleep);
                mFairy.onTap(0.8f, result, 1167, 84, 1183, 97, "经验一键申请关闭", Sleep);
            }

            //仙缘
            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                if (dazeTime > 3) {
//                    mFairy.initMatTime();
//                    result = mFairy.findPic(40, 124, 282, 411, "leftxianyuan.png");
//                    mFairy.onTap(0.8f, result, "左侧仙缘", Sleep);
//                    if (picCountS(0.8f, result, "新手仙缘") > 5) {
//                        setTaskName(0);
//                        return;
//                    }

                    mFairy.initMatTime();
                    List<FindResult> list =
                            mFairy.findPic(40, 124, 282, 411, 0.8f, "leftxianyuan.png");
                    if (list.size() != 0) {
                        result = list.get(0);
                        mFairy.onTap(0.8f, result, "左侧仙缘", Sleep);
                        if (picCountS(0.8f, result, "新手仙缘") > 5) {
                            setTaskName(0);
                            return;
                        }
                    } else {
                        result = mFairy.findPic(40, 124, 282, 411, "jingyan.png");
                        mFairy.onTap(0.7f, result, "左侧经验任务", Sleep);
                        if (result.sim > 0.7f) {
                            setTaskName(1);
                            return;
                        }
                    }
                }


             /*   result = mFairy.findPic("closehand.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("仙缘打怪中"));
                    mFairy.initMatTime();
                }*/

                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"Rightxianyuan.png", "Rightxianyuan1.png"});
                mFairy.onTap(0.8f, result, "右侧仙缘", Sleep);

                result = mFairy.findPic("xianyuancj.png");
                mFairy.onTap(0.8f, result, "仙缘抽奖", Sleep);
            }
        }.taskContent(mFairy, "新手引导");
    }

    public void inOperation() throws Exception {
        result = mFairy.findPic(483,19,767,123,"cysc.png");
        mFairy.onTap(0.8f, result, 1202,58,1213,70,"err商城退出", Sleep);

        if (!AtFairyConfig.getTaskID().equals("2033") && !AtFairyConfig.getTaskID().equals("2035")) {
            result = mFairy.findPic(1144, 1, 1240, 31, new String[]{"llhj.png", "huanjing.png"});
            if (result.sim > 0.8f) {
                for (int i = 0; i < 10; i++) {
                    result = mFairy.findPic(1144, 1, 1240, 31, new String[]{"llhj.png", "huanjing.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("洗刷刷中暂停别的任务"));
                        i = 0;
                    }
                    mFairy.condit();
                    Thread.sleep(2000);
                }
            }
        }
        result = mFairy.findPic(new String[]{"In picture.png", "In picture1.png"});
        LtLog.e(mFairy.getLineInfo("过图：" + result.sim));
        if (result.sim > 0.92f) {
            LtLog.e(mFairy.getLineInfo("过图中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic(511, 6, 805, 437, "Pathfinding.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("寻路中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic("dutiao.png");
        LtLog.e(mFairy.getLineInfo("读条 simg ： " + result.sim));
        if (result.sim > 0.92f) {
            LtLog.e(mFairy.getLineInfo("读条中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic("Join the family.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("没有家族结束当前任务"));
            setTaskEnd();
            return;
        }
    }


    //轻松玩
    public void qsw() throws Exception {
        new SingleTask(mFairy) {

            int qsw;

            public void create() throws Exception {
                super.create();
                qsw=0;
            }

            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, "活动", Sleep);

                result = mFairy.findPic(749,66,1171,158,"Activeinterface.png");
                if (result.sim > 0.8f) {
                    err=0;
                    LtLog.e(mFairy.getLineInfo(0.8f, result, "活动界面"));

                    result = mFairy.findPic("qsw.png");
                    mFairy.onTap(0.8f, result, "轻松玩", Sleep);
                }

                result = mFairy.findPic("qsw2.png");
                if (result.sim > 0.8f) {
                    err=0;

                    switch (qsw){
                        case 0:
                            mFairy.onTap(201,110,220,117,"1",1500);
                            break;
                        case 1:
                            mFairy.onTap(321,105,354,115,"2",1500);
                            break;
                        case 2:
                            mFairy.onTap(472,110,503,118,"3",1500);
                            break;
                        default:
                            mFairy.onTap(1167,79,1186,90,"",1000);
                            setTaskEnd();
                            return;
                    }


                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(500);

                        result = mFairy.findPic(1023,139,1194,679,"qsw1.png");
                        LtLog.e(mFairy.getLineInfo("找回sim:"+result.sim));
                        if (result.sim > 0.9f) {
                            mFairy.onTap(0.9f, result, "找回", Sleep);
                        }else{
                            qsw++;
                            return;
                        }
                    }

                    qsw++;
                }


               overtime(10, 0);
            }

        }.taskContent(mFairy, "轻松玩");
    }



    //仙缘任务
    public int xy = 0;

    public void xyrw() throws Exception {
        new SingleTask(mFairy) {
            public void create() throws Exception {
                super.create();
                time_re = System.currentTimeMillis();
            }

            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("xyrw.png", 1);
                if (ret == 1) {
                    setTaskName(3);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {


                result = mFairy.findPic("duihua.png");
                mFairy.onTap(0.8f, result, 254, 11, 267, 17, "err对话", Sleep);

                result = mFairy.findPic(40, 124, 282, 411, "leftxianyuan.png");
                mFairy.onTap(0.8f, result, "左侧仙缘", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);

                mFairy.taskSlid(err, new int[]{0, 2, 4}, 3, 85, 340, 85, 178, 500, 1500, 2);
                if (overtime(8, 0)) return;
            }

            int kjcount = 0;

            public void content_3() throws Exception {
                if (overtime(7, 0)) {
                    result = mFairy.findPic(40, 124, 282, 411, "leftxianyuan.png");
                    mFairy.onTap(0.8f, result, "左侧仙缘", Sleep);
                    if (result.sim > 0.8f) {
                        setTaskName(3);
                    } else {
                        return;
                    }
                }
                gameUtil.fuhuo();
                gameUtil.fqtask();

                long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                if (dazeTime > 3) {
                    mFairy.initMatTime();
                    result = mFairy.findPic(40, 124, 282, 411, "leftxianyuan.png");
                    mFairy.onTap(0.8f, result, "左侧仙缘", Sleep);
                    if (result.sim > 0.8f) {
                        err = 0;
                    }
                }


                if (System.currentTimeMillis() - time_re > 20000) {
                    result = mFairy.findPic(1166, 317, 1272, 411, "auto1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1157, 592, 1179, 615, "点击普通攻击", 200);
                        mFairy.onTap(1157, 592, 1179, 615, "点击普通攻击", 200);
                        time_re = System.currentTimeMillis();
                    }
                }

                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);

                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"Rightxianyuan.png", "Rightxianyuan1.png"});
                mFairy.onTap(0.8f, result, "右侧仙缘", Sleep);

                result = mFairy.findPic("jixu.png");
                mFairy.onTap(0.8f, result, 747, 446, 778, 460, "继续下一轮", Sleep);

                result = mFairy.findPic("xytask.png");
                mFairy.onTap(0.8f, result, "仙缘界面任务", Sleep);

            /*    result = mFairy.findPic("closehand.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("仙缘打怪中"));
                    mFairy.initMatTime();
                    err = 0;
                }*/
                result2 = mFairy.findPic(22, 491, 1227, 665, "xyjiequle.png");
                result1 = mFairy.findPic(538, 6, 779, 74, "xy.png");
                if (result1.sim > 0.8f) {
                    LtLog.e("仙缘界面中");
                    result = mFairy.findPic(21, 521, 1251, 648, "xytask.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "仙缘界面任务", Sleep);
                    } else if (result2.sim > 0.8f) {
                        mFairy.onTap(0.8f, result2, 1227, 28, 1245, 43, "仙缘已接取了", Sleep);
                        setTaskName(2);
                        return;
                    } else {
                        mFairy.onTap(0.8f, result1, 1234, 36, 1239, 42, "关闭", 2000);
                        result = mFairy.findPic("task1.png");
                        mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);
                        mFairy.ranSwipe(134, 135, 130, 342, 100, (long) 100, 2);
                        mFairy.ranSwipe(134, 135, 130, 342, 100, (long) 100, 2);
                        mFairy.ranSwipe(134, 135, 130, 342, 100, (long) 100, 2);
                    }

                }

                result = mFairy.findPic("xianyuancj.png");
                mFairy.onTap(0.8f, result, "仙缘抽奖", Sleep);

                result = mFairy.findPic("dongzuo.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("动作"));
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic(428, 399, 865, 560, "zhidingdongzuo.png");
                        mFairy.onTap(0.85f, result, "指定动作", 2000);
                        if (result.sim > 0.85f) {
                            result = mFairy.findPic("dutiao.png");
                            if (result.sim > 0.8f) {
                                break;
                            } else {
                                int x = new Random().nextInt(143) + 106;
                                int y = new Random().nextInt(145) + 249;
                                mFairy.ranSwipe(175, 570, x, y, 100, (long) 100, 2);
                            }
                        }

                        Thread.sleep(100);
                    }
                }


                result = mFairy.findPic(447, 231, 511, 667, "songhua.png");
                mFairy.onTap(0.8f, result, "选择好友送花", Sleep);

                result = mFairy.findPic("yousonghua.png");
                mFairy.onTap(0.8f, result, "有花就送花", Sleep);

                result = mFairy.findPic("nosonghua.png");
                mFairy.onTap(0.8f, result, 708, 252, 729, 273, "没有花去买", Sleep);

                result = mFairy.findPic(805, 114, 1167, 578, "songhuabaitan.png");
                mFairy.onTap(0.8f, result, "没有花摆摊购买", Sleep);

                result = mFairy.findPic(572,163,944,370, "kj.png");
                mFairy.onTap(0.8f, result, "空间", Sleep);

                result = mFairy.findPic(1080,269,1178,601, "cyc.png");
                mFairy.onTap(0.8f, result, "踩一踩", 2000);
                mFairy.onTap(0.8f, result, 1217,98,1229,111,"关闭", 2000);
                mFairy.onTap(0.8f, result, 1217,98,1229,111,"关闭2", 2000);

                result = mFairy.findPic(1008, 13, 1266, 601, "xzdaan.png");
                mFairy.onTap(0.8f, result, result.x, result.y + 60, result.x + 1, result.y + 61, "选择正确答案", Sleep);

                result = mFairy.findPic(new String[]{"haoyoudongtai.png", "haoyoudongtai1.png"});
                if (result.sim > 0.8f) {
                    //caiyicai();
                    //xyrankingList(6, 1);
                    xy++;
                }


                result = mFairy.findPic("tancha.png");
                if (result.sim > 0.8f) {
                    for (int i = 0; i <= 20; i++) {
                        result = mFairy.findPic("tancha.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("探查1"));
                            mFairy.ranSwipe(729, 456, 729, 333, 50, (long) 50, 2);
                        } else {
                            break;
                        }
                    }
                    for (int i = 0; i <= 30; i++) {
                        result = mFairy.findPic("tancha.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("探查2"));
                            mFairy.ranSwipe(998, 333, 998, 460, 50, (long) 50, 2);
                        } else {
                            break;
                        }
                    }
                }

                result = mFairy.findPic("npcpurchase.png");
                mFairy.onTap(0.8f, result, "npc商店购买", Sleep);
                if (result.sim > 0.8f) {
                    if (gameUtil.man() == 1) {
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork.png", "fork1.png"});
                    mFairy.onTap(0.9f, result, "关叉", Sleep);
                }

                result = mFairy.findPic("shpurchase.png");
                mFairy.onTap(0.8f, result, "商会购买", Sleep);
                if (result.sim > 0.8f) {
                    if (gameUtil.man() == 1) {
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork.png", "fork1.png"});
                    mFairy.onTap(0.9f, result, "关叉", Sleep);
                }

                result = mFairy.findPic("wjpurchase.png");
                mFairy.onTap(0.8f, result, "玩家商店购买", Sleep);

                result = mFairy.findPic(401, 223, 887, 700, "wjpurchase1.png");
                mFairy.onTap(0.8f, result, "玩家商店购买1", Sleep);
                if (result.sim > 0.8f) {
                    if (gameUtil.man() == 1) {
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork.png", "fork1.png"});
                    mFairy.onTap(0.9f, result, "关叉", Sleep);
                }


                result = mFairy.findPic("shangjiao.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("上交"));
                    result1 = mFairy.findPic(394, 26, 777, 694, "suiji.png");
                    if (result1.sim > 0.8f) {
                        mFairy.onTap(0.8f, result1, 890, 591, 928, 605, "有随机选择购买其他", Sleep);
                    } else {
                        mFairy.onTap(0.8f, result, "上交", Sleep);
                    }
                }
            }
        }.taskContent(mFairy, "仙缘任务中");
    }




    //灵气任务
    public void lqrw() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("lqrw.png", 1);
                if (ret == 1) {
                    setTaskName(3);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {

                result = mFairy.findPic("duihua.png");
                mFairy.onTap(0.8f, result, 254, 11, 267, 17, "err对话", Sleep);

                result = mFairy.findPic(40, 124, 282, 411, new String[]{"leftlingqi.png", "leftlingqi1.png"});
                mFairy.onTap(0.7f, result, "左侧灵气任务", Sleep);
                if (result.sim > 0.7f) {
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);

                mFairy.taskSlid(err, new int[]{0, 2, 4}, 3, 85, 340, 85, 178, 500, 1500, 2);
                if (overtime(8, 0)) return;
            }

            public void content_3() throws Exception {
                if (overtime(7, 2)) {
                    result = mFairy.findPic(40, 124, 282, 411, new String[]{"leftlingqi.png", "leftlingqi1.png"});
                    mFairy.onTap(0.8f, result, "左侧灵气任务", Sleep);
                    if (result.sim > 0.8f) {
                        setTaskName(3);
                    } else {
                        return;
                    }
                }
                gameUtil.fuhuo();
                gameUtil.fqtask();
                long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                if (dazeTime > 3) {
                    mFairy.initMatTime();
                    result = mFairy.findPic(40, 124, 282, 411, new String[]{"leftlingqi.png", "leftlingqi1.png"});
                    mFairy.onTap(0.8f, result, "左侧灵气任务", Sleep);
                    if (result.sim > 0.8f) {
                        err = 0;
                    }
                }
                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"Rightlingqi.png", "Rightxianyuan1.png"});
                mFairy.onTap(0.8f, result, "右侧灵气任务", Sleep);

/*
                result = mFairy.findPic("closehand.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("灵气打怪中"));
                    mFairy.initMatTime();
                    err = 0;
                }*/

                result = mFairy.findPic("dongzuo.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("动作"));
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic(428, 399, 865, 560, "zhidingdongzuo.png");
                        mFairy.onTap(0.85f, result, "指定动作", 2000);
                        if (result.sim > 0.85f) {
                            result = mFairy.findPic("dutiao.png");
                            if (result.sim > 0.8f) {
                                break;
                            } else {
                                int x = new Random().nextInt(143) + 106;
                                int y = new Random().nextInt(145) + 249;
                                mFairy.ranSwipe(175, 570, x, y, 100, (long) 100, 2);
                            }
                        }
                        Thread.sleep(100);
                        mFairy.condit();
                    }
                }

                result = mFairy.findPic(1008, 13, 1266, 601, "xzdaan.png");
                mFairy.onTap(0.8f, result, result.x, result.y + 60, result.x + 1, result.y + 61, "选择正确答案", Sleep);

                result = mFairy.findPic("haoyoudongtai.png");
                if (result.sim > 0.8f) {
                    //caiyicai();
                }

                result = mFairy.findPic("tancha.png");
                if (result.sim > 0.8f) {
                    for (int i = 0; i <= 20; i++) {
                        result = mFairy.findPic("tancha.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("探查1"));
                            mFairy.ranSwipe(729, 456, 729, 333, 50, (long) 50, 2);
                        } else {
                            break;
                        }
                    }
                    for (int i = 0; i <= 30; i++) {
                        result = mFairy.findPic("tancha.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("探查2"));
                            mFairy.ranSwipe(998, 333, 998, 460, 50, (long) 50, 2);
                        } else {
                            break;
                        }
                    }
                }


                result = mFairy.findPic("npcpurchase.png");
                mFairy.onTap(0.8f, result, "npc商店购买", Sleep);
                if (result.sim > 0.8f) {
                    if (gameUtil.man() == 1) {
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork.png", "fork1.png"});
                    mFairy.onTap(0.9f, result, "关叉", Sleep);
                }
                result = mFairy.findPic("shpurchase.png");
                mFairy.onTap(0.8f, result, "商会购买", Sleep);
                if (result.sim > 0.8f) {
                    if (gameUtil.man() == 1) {
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork.png", "fork1.png"});
                    mFairy.onTap(0.9f, result, "关叉", Sleep);
                }
                result = mFairy.findPic("wjpurchase.png");
                mFairy.onTap(0.8f, result, "玩家商店购买", Sleep);

                result = mFairy.findPic(401, 223, 887, 700, "wjpurchase1.png");
                mFairy.onTap(0.8f, result, "玩家商店购买1", Sleep);
                if (result.sim > 0.8f) {
                    if (gameUtil.man() == 1) {
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork.png", "fork1.png"});
                    mFairy.onTap(0.9f, result, "关叉", Sleep);
                }

                result = mFairy.findPic("shangjiao.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("上交"));
                    result1 = mFairy.findPic(394, 26, 777, 694, "suiji.png");
                    if (result1.sim > 0.8f) {
                        mFairy.onTap(0.8f, result1, 890, 591, 928, 605, "有随机选择购买其他", Sleep);
                    } else {
                        mFairy.onTap(0.8f, result, "上交", Sleep);
                    }
                }
            }
        }.taskContent(mFairy, "灵气任务中");
    }

    long time_re = System.currentTimeMillis();

    //宝图任务
    public void baotu() throws Exception {
        new SingleTask(mFairy) {

            public void create() throws Exception {
                super.create();
                time_re = System.currentTimeMillis();
            }

            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("baotutask.png", 1);
                if (ret == 1) {
                    setTaskName(3);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {

                result = mFairy.findPic("duihua.png");
                mFairy.onTap(0.8f, result, 254, 11, 267, 17, "err对话", Sleep);

                result = mFairy.findPic(40, 124, 282, 411, "leftbaotu.png");
                mFairy.onTap(0.7f, result, "左侧宝图", Sleep);
                if (result.sim > 0.7f) {
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);

                mFairy.taskSlid(err, new int[]{0, 2, 4}, 3, 85, 340, 85, 178, 500, 1500, 2);
                if (overtime(8, 0)) return;
            }

            public void content_3() throws Exception {
                if (overtime(7, 2)) return;
                gameUtil.fuhuo();
                gameUtil.fqtask();
                long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                if (dazeTime > 3) {
                    mFairy.initMatTime();
                    result = mFairy.findPic("baotuguai.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("宝图打怪中"));
                        err = 0;
                    } else {
                        result = mFairy.findPic(40, 124, 282, 411, "leftbaotu.png");
                        mFairy.onTap(0.7f, result, "左侧宝图", Sleep);
                        if (result.sim > 0.7f) {
                            err = 0;
                        }
                    }
                }
                if (dazeTime > 30) {
                    result = mFairy.findPic(40, 124, 282, 411, "leftbaotu.png");
                    mFairy.onTap(0.7f, result, "左侧宝图", Sleep);
                }

                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightbaotu.png", "rightbaotu1.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("移动一下"));
                    mFairy.ranSwipe(173, 569, 100, 569, 100, (long) 100, 2);
                    mFairy.onTap(0.8f, result, "右侧宝图", Sleep);
                }

                if (System.currentTimeMillis() - time_re > 20000) {
                    result = mFairy.findPic(1166, 317, 1272, 411, "auto1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1157, 592, 1179, 615, "点击普通攻击", 200);
                        mFairy.onTap(1157, 592, 1179, 615, "点击普通攻击", 200);
                        time_re = System.currentTimeMillis();
                    }
                }


            }
        }.taskContent(mFairy, "宝图任务中");
    }

    //家族任务
    public void jzrw() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("jzrw.png", 1);
                if (ret == 1) {
                    setTaskName(3);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {

                result = mFairy.findPic("duihua.png");
                mFairy.onTap(0.8f, result, 254, 11, 267, 17, "err对话", Sleep);

                result = mFairy.findPic(40, 124, 282, 411, "leftjzrw.png");
                mFairy.onTap(0.7f, result, "左侧家族任务", Sleep);
                if (result.sim > 0.7f) {
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);

                mFairy.taskSlid(err, new int[]{0, 2, 4}, 3, 85, 340, 85, 178, 500, 1500, 2);

                if (overtime(8, 0)) return;
            }

            public void content_3() throws Exception {
                if (overtime(15, 1)) return;
                gameUtil.fuhuo();
                gameUtil.fqtask();
                long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                if (dazeTime > 3) {
                    mFairy.initMatTime();
                    result = mFairy.findPic(40, 124, 282, 411, "leftjzrw.png");
                    mFairy.onTap(0.7f, result, "左侧家族任务", Sleep);
                    if (result.sim > 0.7f) {
                        err = 0;
                    }
                }

                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);

                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightjzrw.png", "rightbaotu1.png"});
                mFairy.onTap(0.8f, result, "右侧家族任务", Sleep);

                result = mFairy.findPic(1011, 334, 1264, 528, "cxrw.png");
                result1 = mFairy.findPic(1011, 334, 1264, 528, "jzrwstop.png");
                mFairy.onTap(0.8f, result, 620, 446, 657, 462, "家族任务结束确定", Sleep);
                if (result.sim > 0.8f && result1.sim < 0.8f) {
                    LtLog.e("任务结束");
                    result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightjzrw.png", "rightbaotu1.png"});
                    mFairy.onTap(0.8f, result, "右侧家族任务", Sleep);

             /*   result = mFairy.findPic("closehand.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("家族任务打怪中"));
                    mFairy.initMatTime();
                    err = 0;
                }*/
                    result = mFairy.findPic("jzrwstop.png");
                    mFairy.onTap(0.8f, result, 620, 446, 657, 462, "家族任务结束确定", Sleep);
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                        return;
                    }
                }

            }
        }.taskContent(mFairy, "家族任务中");
    }

    //门派挑战
    public void mptz() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("mptz.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            boolean yidong = true;

            public void content_2() throws Exception {
                if (overtime(30, 0)) return;
                Thread.sleep(1000);
                result = mFairy.findPic("mptiaozhan.png");
                mFairy.onTap(0.8f, result, "门派挑战", Sleep);
                mFairy.onTap(0.8f, result, 743, 447, 781, 459, "门派挑战确定", 10000);
                if (result.sim > 0.8f) {
                    yidong = true;
                }

                result = mFairy.findPic(1036, 76, 1114, 155, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("门派挑战中"));
                    err = 0;
                    if (yidong) {
                        Thread.sleep(5000);
                        mFairy.ranSwipe(177, 576, 240, 537, 1000, (long) 100, 2);
                        yidong = false;
                    }
                    mFairy.onTap(0.8f, result, 1217, 459, 1234, 470, "技能1", 200);
                    mFairy.onTap(0.8f, result, 1106, 457, 1122, 467, "技能2", 200);
                    mFairy.onTap(0.8f, result, 1042, 483, 1057, 497, "技能3", 200);
                    mFairy.onTap(0.8f, result, 1008, 568, 1024, 585, "技能4", 200);
                    mFairy.onTap(0.8f, result, 1049, 655, 1064, 671, "技能5", 200);
                    mFairy.onTap(0.8f, result, 959, 656, 972, 663, "技能6", 200);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                    mFairy.onTap(0.8f, result, 1166, 591, 1185, 605, "普工", 100);
                }

                result = mFairy.findPic(275, 409, 379, 468, new String[]{"mptzstop.png", "mptzstop2.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("门派挑战结束"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("tiaozhan1.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("门派挑战结束"));
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy, "门派挑战中");
    }

    public void lqhx() throws Exception {
        new SingleTask(mFairy) {
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(10, 2)) {
                    gameUtil.close(1);
                    return;
                }

                result = mFairy.findPic("hui1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(929, 511, 1201, 619, "yuer1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "育儿", 500);
                        for (int i = 0; i < 15; i++) {
                            result = mFairy.findPic(510, 413, 775, 604, "lybb.png");
                            if (result.sim > 0.8f) {
                                setTaskName(2);
                                return;
                            }
                        }

                    } else {
                        mFairy.onTap(1226, 207, 1251, 223, "", 1500);
                    }
                }

                result = mFairy.findPic(419, 311, 590, 621, "hong.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(495, 531, 514, 543, "领取", 1000);
                    mFairy.onTap(1163, 82, 1184, 102, "关", 500);
                    gameUtil.close(0);
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                overtime(5, 99);

                result = mFairy.findPic("hui1.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic("hui1.png");
                    if (result.sim > 0.8f) {


                    }

                }


                result = mFairy.findPic(754, 550, 1041, 707, "hui2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "征战九天", 1000);
                    for (int i = 0; i < 5; i++) {
                        result = mFairy.findPic(419, 311, 590, 621, "xuanzhan.png");
                        if (result.sim > 0.8f) {
                            setTaskEnd();
                            return;
                        }
                    }

                } else {
                    mFairy.onTap(1226, 207, 1251, 223, "", 1500);
                }





                result = mFairy.findPic(1169, 279, 1274, 633, new String[]{"hui3.png", "jtsz.png", "zk1.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(1112, 668, 1128, 679, "领取回响", 1000);
                    mFairy.onTap(1218, 36, 1232, 51, "", 500);
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "领取回响育儿奖励");

    }//领取回响育儿


    //家族炼器
    public void jzlq() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("jzlq.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }


            public void content_2() throws Exception {
                if (overtime(10, 0)) return;
                gameUtil.fuhuo();
                gameUtil.fqtask();
                long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                if (dazeTime > 3) {
                    mFairy.initMatTime();
                    result = mFairy.findPic(40, 124, 282, 411, "leftjzlq.png");
                    mFairy.onTap(0.7f, result, "左侧家族炼器", Sleep);
                    if (result.sim > 0.7f) {
                        err = 0;
                    }
                }

                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightjzlq.png"});
                mFairy.onTap(0.8f, result, "右侧家族炼器", Sleep);

                result = mFairy.findPic("jzlqinface.png");
                if (result.sim > 0.8f) {

                    err = 0;
                    mFairy.initMatTime();
                    LtLog.e(mFairy.getLineInfo("家族炼器界面"));

                    result1 = mFairy.findPic("gmbzt.png");
                    if (result1.sim > 0.8f) {
                        result = mFairy.findPic("qiuzhu.png");
                        mFairy.onTap(0.96f, result, "求助", Sleep);

                        if (result.sim > 0.96f) {

                            for (int i = 0; i < 60; i++) {
                                Thread.sleep(1000);
                                mFairy.condit();
                                result = mFairy.findPic("qiuzhuzhong.png");
                                LtLog.e(mFairy.getLineInfo(0.8f, result, "求助中"));
                                if (result.sim < 0.8f) {
                                    break;
                                }
                            }

                            result1 = mFairy.findPic("gmbzt.png");
                            mFairy.onTap(0.8f, result1, "购买并装填", Sleep);
                            if (gameUtil.man() == 1) {
                                setTaskEnd();
                                return;
                            }

                        } else {
                            mFairy.onTap(0.8f, result1, "购买并装填", Sleep);
                            if (gameUtil.man() == 1) {
                                setTaskEnd();
                                return;
                            }
                        }
                    }

                    result = mFairy.findPic(925, 311, 1059, 374, new String[]{"zhuangman.png", "zhuantianstop.png"});
                    if (picCount(0.8f, result, "材料已装填") > 10) {
                        result = mFairy.findPic("wanchengzhuangtian.png");
                        mFairy.onTap(0.9f, result, "完成装填", Sleep);
                    }

                    result = mFairy.findPic("zhuangtian.png");
                    mFairy.onTap(0.85f, result, "装填", Sleep);

                    result = mFairy.findPic("nozhuangtian.png");
                    mFairy.onTap(0.85f, result, 901, 222, 917, 238, "不能装填选择物品", Sleep);
                    mFairy.onTap(0.85f, result, 587, 209, 620, 217, "选择物品", Sleep);

                }

            }
        }.taskContent(mFairy, "家族炼器中");
    }

    //领地战投票
    public void ldztp() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("ldztp.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(20, 99)) return;
                Thread.sleep(1000);
                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightldztp.png"});
                mFairy.onTap(0.8f, result, "右侧领地战投票", Sleep);

                result = mFairy.findPic("toupiao.png");
                mFairy.onTap(0.8f, result, "投票", 10);
                mFairy.onTap(0.8f, result, "投票", 10);
                mFairy.onTap(0.8f, result, "投票", 10);
                mFairy.onTap(0.8f, result, "投票", 10);
                mFairy.onTap(0.8f, result, "投票", 10);
                mFairy.onTap(0.8f, result, "投票", 10);
                mFairy.onTap(0.8f, result, "投票", 10);
                mFairy.onTap(0.8f, result, 180, 261, 216, 286, "选择家族", Sleep);
                mFairy.onTap(0.8f, result, 1025, 623, 1057, 636, "投票完成", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "领地战投票中");
    }

    //开工福利
    public void kgfl() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("kgfl.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(20, 99)) return;
                Thread.sleep(1000);
                result = mFairy.findPic("kgflinface.png");
                mFairy.onTap(0.8f, result, 465, 372, 489, 389, "开工福利界面抽奖", 5000);

                result = mFairy.findPic("kgflstop.png");
                mFairy.onTap(0.8f, result, 820, 35, 832, 47, "开工福利结束", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "开工福利中");
    }

    //大师兄投票
    public void dsxtp() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("dsxtp.png", 2);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(20, 99)) return;
                Thread.sleep(1000);
                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightdsxtp.png"});
                mFairy.onTap(0.8f, result, "右侧大师兄投票", Sleep);


                result = mFairy.findPic("menpaiinface.png");
                mFairy.onTap(0.8f, result, 1093, 242, 1103, 253, "投票", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "大师兄投票中");
    }

    //幻想宾果
    public void hxbg() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(5, 2)) return;
                result = mFairy.findPic(676, 7, 1152, 170, "hxbg.png");
                mFairy.onTap(0.8f, result, "幻想宾果", 5000);

                result = mFairy.findPic(67, 494, 753, 627, "kaiqipanmian.png");
                mFairy.onTap(0.8f, result, "开启盘面", Sleep);

                result = mFairy.findPic(67, 494, 753, 627, "kaiqipanmian.png");
                mFairy.onTap(0.8f, result, "开启盘面", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                result = mFairy.findPic("hxbgfork.png");
                mFairy.onTap(0.8f, result, "关闭幻想宾果", Sleep);
                gameUtil.close(0);
                setTaskEnd();
                return;
            }
        }.taskContent(mFairy, "幻想宾果中");
    }

    //挖宝图 (新版 背包里没藏宝图后再去开箱子)
    public void wabaotu() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                gameUtil.close(0);
                setTaskName(2);
                return;
            }

            public void content_2() throws Exception {
                overtime(15, 3);
                result = mFairy.findPic("bag.png");
                mFairy.onTap(0.8f, result, "包裹", Sleep);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面中"));

                    result = mFairy.findPic(761, 177, 1147, 570, new String[]{"cangbaotu.png"});
                    mFairy.onTap(0.8f, result, "宝图", 2000);

                    result = mFairy.findPic(224, 189, 736, 652, "sywp.png");
                    mFairy.onTap(0.8f, result, "物品使用", Sleep);
                    if (result.sim > 0.8f) {
                        if (gameUtil.man() == 1) {
                            setTaskEnd();
                            return;
                        } else {
                            wabaotu1_test();
                            setTaskName(3);
                            LtLog.e(mFairy.getLineInfo("背包里藏宝图寻完"));
                            return;
                        }
                    }
                    mFairy.taskSlid(err, new int[]{1, 3, 5, 7, 9, 11, 13}, 0, 918, 538, 917, 225, 500, 1500, 2);
                }
            }

            @Override
            public void content_3() throws Exception {
                if (overtime(16, 99)) {
                    LtLog.e(mFairy.getLineInfo("寻找藏宝图宝箱结束"));
                    return;
                }
                result = mFairy.findPic("bag.png");
                mFairy.onTap(0.8f, result, "包裹", Sleep);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面中"));

                    result = mFairy.findPic(761, 177, 1147, 570, new String[]{"btxiangzi.png", "btxiangzi1.png"});
                    mFairy.onTap(0.75f, result, "宝图箱子", Sleep);

                    result = mFairy.findPic(224, 189, 736, 652, "gengduo.png");
                    mFairy.onTap(0.8f, result, "更多", Sleep);

                    result = mFairy.findPic(224, 189, 736, 652, "piliangsy.png");
                    mFairy.onTap(0.8f, result, "批量使用", Sleep);

                    //批量使用界面
                    result = mFairy.findPic("piliangsy1.png");
                    if (result.sim > 0.8f) {
                        //数量 减到0
//                        for (int i = 0; i < 100; i++) {
//                            mFairy.tap(550, 333);
//                        }

                        //数量 加到5
//                        for (int i = 1; i < 5; i++) {
//                            mFairy.tap(824, 336);
//                        }

                        //数量 减到1
                        mFairy.onTap(0.8f, result, 755, 328, 770, 343, "打开数字键", 1000);
                        mFairy.onTap(0.8f, result, 926, 236, 941, 251, "输入5", 1000);
                        mFairy.onTap(0.8f, result, 1043, 383, 1057, 395, "确定", 1000);

                        mFairy.onTap(0.8f, result, "批量使用", 1000);
                        if (gameUtil.man() == 1) {
                            setTaskEnd();
                            return;
                        } else {
                            //wabaotu1();
                            setTaskName(0);
                            return;
                        }
                    }

                    result = mFairy.findPic(224, 189, 736, 652, "sywp.png");
                    mFairy.onTap(0.8f, result, "物品使用", 1000);
                    if (result.sim > 0.8f) {
                        if (gameUtil.man() == 1) {
                            setTaskEnd();
                            return;
                        } else {
                            setTaskName(2);
                            return;
                        }
                    }
                    mFairy.taskSlid(err, new int[]{2, 4, 6, 8, 10, 12, 14}, 5, 918, 538, 917, 225, 500, 1500, 2);
                }
            }
        }.taskContent(mFairy, "挖宝图使用箱子中");
    }

    //挖宝图1 (新版 背包里没藏宝图后再去开箱子)
    public void wabaotu1() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void create() throws Exception {
                setTaskName(3);
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(6, 99)) return;
                result = mFairy.findPic("rightsybt.png");
                mFairy.onTap(0.8f, result, "右侧还有宝图", 5000);
                if (result.sim > 0.8f) {
                    err = 0;
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic("bag.png");
                mFairy.onTap(0.8f, result, "包裹", Sleep);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面中"));
                    result = mFairy.findPic(761, 177, 1147, 570, new String[]{"cangbaotu.png"});
                    mFairy.onTap(0.9f, result, "宝图", Sleep);

                    result = mFairy.findPic("rightsybt.png");
                    mFairy.onTap(0.8f, result, "右侧还有宝图", Sleep);
                    if (result.sim > 0.8f) {
                        if (gameUtil.man() == 1) {
                            setTaskEnd();
                            return;
                        } else {
                            setTaskName(2);
                            return;
                        }
                    }

                    result = mFairy.findPic(224, 189, 736, 652, "sywp.png");
                    mFairy.onTap(0.8f, result, "物品使用", Sleep);
                    if (result.sim > 0.8f) {
                        if (gameUtil.man() == 1) {
                            setTaskEnd();
                            return;
                        } else {
                            setTaskName(2);
                            return;
                        }
                    }
                    mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5, 6}, 0, 918, 538, 917, 225, 500, 1500, 2);
                }
            }

            public void content_2() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic("rightsybt.png");
                mFairy.onTap(0.8f, result, "右侧还有宝图", 5000);
                if (result.sim > 0.8f) {
                    err = 0;
                }
                result = mFairy.findPic("baginface.png");
                mFairy.onTap(0.8f, result, 1161, 88, 1180, 100, "关闭包裹", Sleep);

                result = mFairy.findPic(1032, 103, 1114, 160, "copy.png");
                mFairy.onTap(0.8f, result, 1196, 98, 1211, 111, "副本中打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 642, 318, 643, 319, "去副本地图中间", Sleep);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", 5000);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(10, 4)) {
                    if (AtFairyConfig.getOption("bkxz").equals("1")) {
                        gameUtil.lkfb();
                        setTaskName(0);
                        return;
                    }
                    return;
                }
                gameUtil.fuhuo();

                result = mFairy.findPic(1032, 103, 1114, 160, "copy.png");
                if (result.sim > 0.8f) {
                    gameUtil.zidong();

                    result = mFairy.findPic("baotufubenguai.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                    }
                }
            }

            public void content_4() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(1032, 103, 1114, 160, "copy.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic(1032, 103, 1114, 160, "copy.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, 1196, 98, 1211, 111, "副本中打开地图", Sleep);
                            if (i == 0) {
                                mFairy.onTap(0.8f, result, 802, 485, 803, 486, "第一个宝箱", Sleep);
                            }
                            if (i == 1) {
                                mFairy.onTap(0.8f, result, 857, 397, 858, 398, "第2个宝箱", Sleep);
                            }
                            if (i == 2) {
                                mFairy.onTap(0.8f, result, 805, 321, 806, 322, "第3个宝箱", Sleep);
                            }
                            if (i == 3) {
                                mFairy.onTap(0.8f, result, 623, 288, 624, 289, "第4个宝箱", Sleep);
                            }
                            if (i == 4) {
                                mFairy.onTap(0.8f, result, 483, 326, 484, 327, "第5个宝箱", Sleep);
                            }

                            if (i == 5) {
                                mFairy.onTap(0.8f, result, 399, 406, 400, 407, "第6个宝箱", Sleep);
                            }
                            if (i == 6) {
                                mFairy.onTap(0.8f, result, 463, 490, 464, 491, "第7个宝箱", Sleep);
                            }
                            mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", 5000);
                            if (i == 7) {
                                gameUtil.lkfb();
                                setTaskName(0);
                                return;
                            }
                            result = mFairy.findPic(279, 171, 999, 599, "baoxiang.png");
                            mFairy.onTap(0.75f, result, result.x - 20, result.y + 10, result.x - 19, result.y + 11, "哒鸭宝箱", 5000);
                        }

                    }
                }
            }
        }.taskContent(mFairy, "挖宝图中");
    }

    //挖宝图 (旧版)
    public void wabaotu_test() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                gameUtil.close(0);
                setTaskName(2);
                return;
            }

            public void content_2() throws Exception {
                if (overtime(6, 99)) {
                    // wabaotu1();
                    LtLog.e(mFairy.getLineInfo("使用藏宝图结束"));
                    return;
                }
                result = mFairy.findPic("bag.png");
                mFairy.onTap(0.8f, result, "包裹", Sleep);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面中"));
                    result = mFairy.findPic(761, 177, 1147, 570, new String[]{"btxiangzi.png", "btxiangzi1.png"});
                    mFairy.onTap(0.9f, result, "宝图箱子", Sleep);

                    result = mFairy.findPic(224, 189, 736, 652, "gengduo.png");
                    mFairy.onTap(0.8f, result, "更多", Sleep);

                    result = mFairy.findPic(224, 189, 736, 652, "piliangsy.png");
                    mFairy.onTap(0.8f, result, "批量使用", Sleep);

                    //批量使用界面
                    result = mFairy.findPic("piliangsy1.png");
                    if (result.sim > 0.8f) {
                        //数量 减到0
                        for (int i = 0; i < 100; i++) {
                            mFairy.tap(550, 333);
                        }
                        //数量 加到5
                        for (int i = 0; i < 5; i++) {
                            mFairy.tap(824, 336);
                        }
                        mFairy.onTap(0.8f, result, "批量使用", 1000);
                        if (gameUtil.man() == 1) {
                            setTaskEnd();
                            return;
                        } else {
                            //wabaotu1();
                            setTaskName(0);
                            return;
                        }
                    }

                    result = mFairy.findPic(224, 189, 736, 652, "sywp.png");
                    mFairy.onTap(0.8f, result, "物品使用", 1000);
                    if (result.sim > 0.8f) {
                        if (gameUtil.man() == 1) {
                            setTaskEnd();
                            return;
                        } else {
                            setTaskName(2);
                            return;
                        }
                    }
                    mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5, 6}, 0, 918, 538, 917, 225, 500, 1500, 2);
                }
            }
        }.taskContent(mFairy, "挖宝图使用箱子中");
    }

    //挖宝图1 (旧版)
    public void wabaotu1_test() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(15, 99)) return;
                result = mFairy.findPic("bag.png");
                mFairy.onTap(0.8f, result, "包裹", Sleep);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面中"));
                    result = mFairy.findPic(761, 177, 1147, 570, new String[]{"cangbaotu.png"});
                    mFairy.onTap(0.9f, result, "宝图", Sleep);

                    result = mFairy.findPic("rightsybt.png");
                    mFairy.onTap(0.8f, result, "右侧还有宝图", Sleep);
                    if (result.sim > 0.8f) {
                        if (gameUtil.man() == 1) {
                            setTaskEnd();
                            return;
                        } else {
                            setTaskName(2);
                            return;
                        }
                    }

                    result = mFairy.findPic(224, 189, 736, 652, "sywp.png");
                    mFairy.onTap(0.8f, result, "物品使用", Sleep);
                    if (result.sim > 0.8f) {
                        if (gameUtil.man() == 1) {
                            setTaskEnd();
                            return;
                        } else {
                            setTaskName(2);
                            return;
                        }
                    }
                    mFairy.taskSlid(err, new int[]{1, 3, 5, 7, 9, 11, 13}, 0, 918, 538, 917, 225, 500, 1500, 2);
                }
            }

            public void content_2() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic("rightsybt.png");
                mFairy.onTap(0.8f, result, "右侧还有宝图", 5000);
                if (result.sim > 0.8f) {
                    err = 0;
                }
                result = mFairy.findPic("baginface.png");
                mFairy.onTap(0.8f, result, 1161, 88, 1180, 100, "关闭包裹", Sleep);

                result = mFairy.findPic(1032, 103, 1114, 160, "copy.png");
                mFairy.onTap(0.8f, result, 1196, 98, 1211, 111, "副本中打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 642, 318, 643, 319, "去副本地图中间", Sleep);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", 5000);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(10, 4)) {
                    if (AtFairyConfig.getOption("bkxz").equals("1")) {
                        gameUtil.lkfb();
                        setTaskName(0);
                        return;
                    }
                    return;
                }
                gameUtil.fuhuo();
                result = mFairy.findPic(1032, 103, 1114, 160, "copy.png");
                if (result.sim > 0.8f) {
                    gameUtil.zidong();

                    result = mFairy.findPic("baotufubenguai.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                    }
                }
            }

            public void content_4() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(1032, 103, 1114, 160, "copy.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic(1032, 103, 1114, 160, "copy.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, 1196, 98, 1211, 111, "副本中打开地图", Sleep);
                            if (i == 0) {
                                mFairy.onTap(0.8f, result, 802, 485, 803, 486, "第一个宝箱", Sleep);
                            }
                            if (i == 1) {
                                mFairy.onTap(0.8f, result, 857, 397, 858, 398, "第2个宝箱", Sleep);
                            }
                            if (i == 2) {
                                mFairy.onTap(0.8f, result, 805, 321, 806, 322, "第3个宝箱", Sleep);
                            }
                            if (i == 3) {
                                mFairy.onTap(0.8f, result, 623, 288, 624, 289, "第4个宝箱", Sleep);
                            }
                            if (i == 4) {
                                mFairy.onTap(0.8f, result, 483, 326, 484, 327, "第5个宝箱", Sleep);
                            }

                            if (i == 5) {
                                mFairy.onTap(0.8f, result, 399, 406, 400, 407, "第6个宝箱", Sleep);
                            }
                            if (i == 6) {
                                mFairy.onTap(0.8f, result, 463, 490, 464, 491, "第7个宝箱", Sleep);
                            }
                            mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", 5000);
                            if (i == 7) {
                                gameUtil.lkfb();
                                setTaskName(0);
                                return;
                            }
                            result = mFairy.findPic(279, 171, 999, 599, "baoxiang.png");
                            mFairy.onTap(0.75f, result, result.x - 20, result.y + 10, result.x - 19, result.y + 11, "哒鸭宝箱", 5000);
                        }

                    }
                }
            }
        }.taskContent(mFairy, "挖宝图中");
    }

    //对酒当歌
    public void jyxss() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("djdg.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("zuoxia.png");
                mFairy.onTap(0.8f, result, "坐下", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }

            }

            public void content_3() throws Exception {
                if (overtime(8, 0)) return;
                if (AtFairyConfig.getOption("zhuxing").equals("1")) {
                    result = mFairy.findPic("zhuxing.png");
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 1; i++) {
                            mFairy.onTap(0.8f, result, "助兴", Sleep);
                            mFairy.onTap(0.8f, result, "助兴", Sleep);
                        }
                    }
                }
                result = mFairy.findPic("djdgtimu.png");
                mFairy.onTap(0.8f, result, 431, 348, 457, 364, "答题", Sleep);
                mFairy.onTap(0.8f, result, 941, 178, 951, 190, "答题", Sleep);

            }
        }.taskContent(mFairy, "对酒当歌中");
    }

    //踩梦岛世界
    public void rankingList1() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
                mFairy.onTap(0.8f, result, 51, 28, 52, 29, "对话栏切换到世界", Sleep);
                if (result.sim > 0.8f) {
                    err = 0;
                    long dazeTime = mFairy.mMatTime(114, 69, 59, 540, 0.9f);
                    if (dazeTime == 0) {
                        result = mFairy.findPic(192, 517, 262, 620, "yin_shijie.png");
                        mFairy.onTap(0.8f, result, result.x - 52, result.y + 30, result.x - 51, result.y + 31, "识别到世界", Sleep);
                    }
                } else {
                    result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                    mFairy.onTap(0.8f, result, 393, 673, 394, 674, "活动", Sleep);
                }
                result = mFairy.findPic(277, 67, 1183, 633, "fwkj.png");
                mFairy.onTap(0.8f, result, "访问空间", 5000);

                result = mFairy.findPic("caiyicai.png");
                mFairy.onTap(0.8f, result, "踩一踩", 3000);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("lw.png");
                    mFairy.onTap(0.8f, result, "礼物", 3000);
                    mFairy.onTap(1224, 106, 1225, 107, "关叉", 10000);
                    //  gameUtil.close(1);
                }
            }
        }.taskContent(mFairy, "踩梦岛世界中");
    }

    //踩一踩灵气任务
    public void caiyicai() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
                mFairy.onTap(0.8f, result, 51, 28, 52, 29, "对话栏切换到世界", Sleep);
                if (result.sim > 0.8f) {
                    err = 0;
                    long dazeTime = mFairy.mMatTime(114, 69, 59, 540, 0.9f);
                    if (dazeTime == 0) {
                        result = mFairy.findPic(192, 517, 262, 620, "yin_shijie.png");
                        mFairy.onTap(0.8f, result, result.x - 52, result.y + 30, result.x - 51, result.y + 31, "识别到世界", Sleep);
                    }
                } else {
                    result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                    mFairy.onTap(0.8f, result, 393, 673, 394, 674, "活动", Sleep);
                }
                result = mFairy.findPic(277, 67, 1183, 633, "fwkj.png");
                mFairy.onTap(0.8f, result, "访问空间", 5000);

                result = mFairy.findPic("caiyicai.png");
                mFairy.onTap(0.8f, result, "踩一踩", 3000);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("lw.png");
                    mFairy.onTap(0.8f, result, "礼物", 3000);
                    mFairy.onTap(1224, 106, 1225, 107, "关叉", 3000);
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "踩一踩中");
    }

    //踩一踩排行榜
    public void rankingList(final int start, final int cai) throws Exception {
        new SingleTask(mFairy) {
            int y = 248, jccount = 0;
            boolean bj = false;

            public void create() throws Exception {
                super.create();
                y = (start * 66) + 248;
            }

            public void inOperation() throws Exception {
                if (!AtFairyConfig.getTaskID().equals("2033") && !AtFairyConfig.getTaskID().equals("2035")) {
                    result = mFairy.findPic(1144, 1, 1240, 31, new String[]{"llhj.png", "huanjing.png"});
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 10; i++) {
                            result = mFairy.findPic(1144, 1, 1240, 31, new String[]{"llhj.png", "huanjing.png"});
                            if (result.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("洗刷刷中暂停别的任务"));
                                i = 0;
                            }
                            mFairy.condit();
                            Thread.sleep(2000);
                        }
                    }
                }
                result = mFairy.findPic(new String[]{"In picture.png", "In picture1.png"});
                LtLog.e(mFairy.getLineInfo("过图：" + result.sim));
                if (result.sim > 0.92f) {
                    LtLog.e(mFairy.getLineInfo("过图中"));
                    mFairy.initMatTime();
                    err = 0;
                    picCountMapS.clear();
                    picCountMap.clear();
                }
                result = mFairy.findPic(511, 6, 805, 437, "Pathfinding.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("寻路中"));
                    mFairy.initMatTime();
                    err = 0;
                    picCountMapS.clear();
                    picCountMap.clear();
                }
            }

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic("qiehuanlan.png");
                mFairy.onTap(0.8f, result, "切换栏", Sleep);


                result = mFairy.findPic(866, 311, 1279, 712, "paihangbang.png");
                mFairy.onTap(0.8f, result, "排行榜", Sleep);

                result = mFairy.findPic("phb.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("排行榜界面"));
                    if (AtFairyConfig.getOption("fzph").equals("1")) {
                        setTaskName(2);
                        return;
                    } else {
                        for (int i = 0; i < 4; i++) {
                            mFairy.ranSwipe(157, 149, 175, 626, 500, (long) 500, 2);
                        }
                        for (int i = 0; i < 3; i++) {
                            result = mFairy.findPic(203, 106, 285, 665, "sq.png");
                            mFairy.onTap(0.8f, result, "收起列表", Sleep);
                        }

                        result = mFairy.findPic(79, 103, 293, 672, "renqibang.png");
                        mFairy.onTap(0.8f, result, "人气榜", Sleep);
                        /*mFairy.onTap(0.8f, result, 192, 626, 193, 627, "人气榜周", Sleep);*/

                        result = mFairy.findPic(84, 329, 287, 518, "rqb.png");
                        if (result.sim > 0.8f) {
                            mFairy.ranSwipe(180, 607, 179, 279, 500, (long) 2000, 2);
                        }

                        result = mFairy.findPic(75, 96, 288, 675, "rqz.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "人气周", Sleep);
                            setTaskName(2);
                            return;
                        }
                    }
                }
            }

            public void content_2() throws Exception {
                if (overtime(20, 0)) return;
                /*result = mFairy.findPic(75,96,288,675, "rqz.png");
                mFairy.onTap(0.8f, result, "人气周", Sleep);*/



                result = mFairy.findPic(277, 67, 1183, 633, "fwkj.png");
                mFairy.onTap(0.8f, result, "访问空间", 5000);

                if (bj) {
                    result = mFairy.findPic("caiyicai.png");
                    mFairy.onTap(0.8f, result, "踩一踩", 5000);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("lw.png");
                        mFairy.onTap(0.8f, result, "礼物", 5000);
                        mFairy.onTap(1224, 106, 1225, 107, "关叉", 10000);
                        bj = false;
                    }
                }


                result = mFairy.findPic("phb.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(61,78,292,600, "rqz1.png");
                    if (result.sim < 0.8f) {
                        err+=3;
                    }else{
                        err = 0;
                    }
                    LtLog.e(mFairy.getLineInfo("排行榜界面"));
                    if (y > 550) {
                        y = 248;
                        LtLog.e(mFairy.getLineInfo("滑动翻页"));
                        mFairy.ranSwipe(749, 590, 749, 188, 500, (long) 1500, 2);
                    }
                    mFairy.onTap(684, y, 685, y + 1, "选个角色", 5000);
                    bj = true;
                    y = y + 67;
                    jccount++;
                    LtLog.e(mFairy.getLineInfo("踩了" + jccount + "次"));
                    if (jccount > cai) {
                        LtLog.e(mFairy.getLineInfo("踩够了结束"));
                        setTaskEnd();
                        return;
                    }
                }
            }

        }.taskContent(mFairy, "排行榜踩梦岛任务中");
    }

    //仙缘踩属性空间
    public void xyrankingList(final int start, final int cai) throws Exception {
        new SingleTask(mFairy) {

            public void create() throws Exception {
                super.create();
                y = (start * 66) + 248;
            }

            public void inOperation() throws Exception {
                if (!AtFairyConfig.getTaskID().equals("2033") && !AtFairyConfig.getTaskID().equals("2035")) {
                    result = mFairy.findPic(1144, 1, 1240, 31, new String[]{"llhj.png", "huanjing.png"});
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 10; i++) {
                            result = mFairy.findPic(1144, 1, 1240, 31, new String[]{"llhj.png", "huanjing.png"});
                            if (result.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("洗刷刷中暂停别的任务"));
                                i = 0;
                            }
                            mFairy.condit();
                            Thread.sleep(2000);
                        }
                    }
                }
                result = mFairy.findPic(new String[]{"In picture.png", "In picture1.png"});
                LtLog.e(mFairy.getLineInfo("过图：" + result.sim));
                if (result.sim > 0.92f) {
                    LtLog.e(mFairy.getLineInfo("过图中"));
                    mFairy.initMatTime();
                    err = 0;
                    picCountMapS.clear();
                    picCountMap.clear();
                }
                result = mFairy.findPic(511, 6, 805, 437, "Pathfinding.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("寻路中"));
                    mFairy.initMatTime();
                    err = 0;
                    picCountMapS.clear();
                    picCountMap.clear();
                }
            }

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic("qiehuanlan.png");
                mFairy.onTap(0.8f, result, "切换栏", Sleep);


                result = mFairy.findPic(866, 311, 1279, 712, "paihangbang.png");
                mFairy.onTap(0.8f, result, "排行榜", Sleep);

                result = mFairy.findPic(543, 29, 735, 106, "phb.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("排行榜界面"));
                    for (int i = 0; i < 4; i++) {
                        mFairy.ranSwipe(157, 149, 175, 626, 500, (long) 500, 2);
                    }
                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic(203, 106, 285, 665, "sq.png");
                        mFairy.onTap(0.8f, result, "收起列表", Sleep);
                    }

                    result = mFairy.findPic(79, 103, 293, 672, "shuxing.png");
                    mFairy.onTap(0.8f, result, "属性榜", Sleep);
                    if (result.sim > 0.8f) {
                        setTaskName(2);
                        return;
                    }

                }
            }

            int y = 248, jccount = 0;
            boolean bj = false;

            public void content_2() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(277, 67, 1183, 633, "fwkj.png");
                mFairy.onTap(0.8f, result, "访问空间", 5000);

                if (bj) {
                    LtLog.e("寻找踩一踩");
                    result = mFairy.findPic(1062, 233, 1203, 576, "caiyicai.png");
                    mFairy.onTap(0.8f, result, "踩一踩", 5000);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("lw.png");
                        mFairy.onTap(0.8f, result, "礼物", 5000);
                        mFairy.onTap(1224, 106, 1225, 107, "关叉", 10000);
                        bj = false;
                    }
                }

                result = mFairy.findPic(543, 29, 735, 106, "phb.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("排行榜界面"));
                    if (y > 550) {
                        y = 248;
                        LtLog.e(mFairy.getLineInfo("滑动翻页"));
                        mFairy.ranSwipe(749, 590, 749, 220, 500, (long) 1500, 2);
                        mFairy.ranSwipe(749, 590, 749, 220, 500, (long) 1500, 2);
                        mFairy.ranSwipe(749, 590, 749, 220, 500, (long) 1500, 2);
                    }
                    mFairy.onTap(684, y, 685, y + 1, "选个角色", 5000);
                    bj = true;
                    y = y + 66;
                    jccount++;
                    LtLog.e(mFairy.getLineInfo("踩了" + jccount + "次"));
                    if (jccount > cai) {
                        LtLog.e(mFairy.getLineInfo("踩够了结束"));
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(498, 448, 785, 558, "fgkwj.png");
                if (result.sim > 0.8f) {
                    LtLog.e("非公开玩家滑动");
                    mFairy.ranSwipe(749, 590, 749, 220, 500, (long) 1500, 2);
                }
            }
        }.taskContent(mFairy, "仙缘踩属性空间任务中");
    }

    public void mzrw() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(7, 0)) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(1, 120, 42, 240, "task2.png");
                mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);

                if (err >= 3) {
                    result = mFairy.findPic(1019, 223, 1260, 525, new String[]{"mzxx1.png", "mzxx2.png", "mzxx3.png", "mzxx4.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "每周任务对话", Sleep);
                    } else {
                        result = mFairy.findPic(42, 123, 107, 360, "meizhou.png");
                        mFairy.onTap(0.8f, result, "每周任务", Sleep);
                    }
                }
            }
        }.taskContent(mFairy, "每周任务");
    } //每周任务

    //单人完成回家族挂机
    public void drhjz() throws Exception {
        new TimingActivity(mFairy) {

            @Override
            public void content_0() throws Exception {
                gameUtil.goCity("家族");
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 544, 511, 545, 512, "在家族去中心点打怪", 5000);
                mFairy.onTap(0.8f, result, 1231, 39, 1246, 54, "关闭地图界面", Sleep);
                if (result.sim > 0.8f) {
                    gameUtil.zidong();
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "单人完成回家族挂机中");
    }

    public void test() throws Exception {
        while (mFairy.condit()) {
            mFairy.sleep(1000);
            LtLog.e(mFairy.getLineInfo("test while...........\n"));
        }
    }
}

