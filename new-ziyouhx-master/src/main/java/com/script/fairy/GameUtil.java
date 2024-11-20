package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;


/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class GameUtil extends TaskContent {
    private static AtFairyImpl mFairy;

    FindResult result;
    FindResult result1;
    FindResult result2;
    FindResult result3;

    public GameUtil(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        Sleep = 1500;
        SleepWhile = 300;
    }

    public void inOperation() throws Exception {
        if (!AtFairyConfig.getTaskID().equals("2033") && !AtFairyConfig.getTaskID().equals("2035")) {
            result = mFairy.findPic(1144, 1, 1240, 31, new String[]{"llhj.png", "huanjing.png"});
            if (result.sim > 0.8f) {
                for (int i = 0; i < 10; i++) {
                    result = mFairy.findPic(1144, 1, 1240, 31, new String[]{"llhj.png", "huanjing.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("洗刷刷中或幻境中暂停别的任务"));
                        i = 0;
                    }
                    mFairy.condit();
                    Thread.sleep(8000);
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
        if (result.sim > 0.8f) {
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

    //开始任务
    int tastState = 0;

    public int mission(final String str, final int option) throws Exception {
        new GameUtil(mFairy) {
            int findtask = 0;

            public void create() throws Exception {
                super.create();
            }

            public void content_0() throws Exception {
                result = mFairy.findPic(11,80,112,478, "dq.png");
                mFairy.onTap(0.8f, result, 1232,43,1240,54,"地图关闭", Sleep);

                if (findtask >= 3) {
                    LtLog.e("没有这个任务");
                    GameUtil.this.tastState = 0;
                    setTaskEnd();
                }
                if (str.equals("kjxs.png")){
                    close(0);
                }else{
                    close(1);
                }
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic(1068,16,1122,68,new String[]{"zoom1.png","zoom3.png"});
                mFairy.onTap(0.8f, result, "点开缩放栏", Sleep);

                result = mFairy.findPic(951,71,1122,201,"ryjjcnei.png");
                if (result.sim > 0.8f) {
                    GameUtil.this.tastState = 1;
                    LtLog.e("活动中退出找任务");
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, "活动", Sleep);

                result = mFairy.findPic(749,66,1171,158,"Activeinterface.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "活动界面"));
                if (result.sim > 0.8f) {
                    Thread.sleep(2000);
                    mFairy.condit();
                    for (int i = 0; i <= 5; i++) {
                        if (mFairy.getColorNum(416, 632, 419, 634, 0.8f, 0, "255,255,106") >= 3) {
                            mFairy.onTap(380, 601, 399, 611, "活跃1", Sleep);
                        }
                        if (mFairy.getColorNum(610, 632, 613, 634, 0.8f, 0, "255,255,106") >= 3) {
                            mFairy.onTap(569, 601, 580, 611, "活跃2", Sleep);
                        }
                        if (mFairy.getColorNum(799, 632, 802, 634, 0.8f, 0, "255,255,106") >= 3) {
                            mFairy.onTap(762, 601, 778, 613, "活跃3", Sleep);
                        }
                        if (mFairy.getColorNum(989, 632, 992, 634, 0.8f, 0, "255,255,106") >= 3) {
                            mFairy.onTap(943, 592, 963, 611, "活跃4", Sleep);
                        }
                        if (mFairy.getColorNum(1173, 632, 1176, 634, 0.8f, 0, "255,255,106") >= 3) {
                            mFairy.onTap(1131, 593, 1149, 607, "活跃5", Sleep);
                        }
                    }
                    result = mFairy.findPic(242, 161, 1186, 551, str);
                    if (result.sim > 0.8f) {
                    } else {
                        if (option == 1) {
                            mFairy.onTap(125, 140, 161, 151, "日常活动", Sleep);
                        }
                        if (option == 2) {
                            mFairy.onTap(132, 206, 168, 219, "限时活动", Sleep);
                        }
                        if (option == 3) {
                            mFairy.onTap(150,279,158,285, "赛季活动", Sleep);
                        }
                    }
                    findtask++;
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                /**
                 * 343,299
                 * 495,327,538,364
                 */

                if (overtime(6, 0)) return;
                result = mFairy.findPic(749,66,1171,158,"Activeinterface.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "活动界面"));
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic(242, 161, 1186, 551, str);
                    LtLog.e("找到了任务=" + str + "," + result1.sim);
                    if (result1.sim > 0.8f) {
                        if (str.equals("jyxss.png") && !AtFairyConfig.getOption("dw").equals("3")) {
                            for (int i = 0; i < 5; i++) {
                                mFairy.capture();
                                //result = mFairy.findPic(result1.x + 152, result1.y + 10, result1.x + 250, result1.y + 80, "xss.png");
                                result = mFairy.findPic(result1.x, result1.y + 30, result1.x + 144, result1.y + 60, "xss1.png");
                                LtLog.e(mFairy.getLineInfo("洗刷刷活跃度满的相似度：" + result.sim));
                                if (result.sim >= 0.92f) {
                                    LtLog.e("经验洗刷刷活跃满");
                                    GameUtil.this.tastState = 0;
                                    setTaskEnd();
                                    return;
                                }
                            }
                        }
                        if (str.equals("cfzc.png")) {
                            for (int i = 0; i < 5; i++) {
                                mFairy.condit();
                                result = mFairy.findPic(result1.x + 147, result1.y - 19, result1.x + 322, result1.y + 71, "goto.png");
                                mFairy.onTap(0.8f, result, "参加", Sleep);
                                if (result.sim >= 0.8f) {
                                    GameUtil.this.tastState = 1;
                                    setTaskEnd();
                                    return;
                                }
                                result = mFairy.findPic(result1.x + 147, result1.y - 19, result1.x + 322, result1.y + 71, "wancheng.png");
                                mFairy.onTap(0.8f, result, 1164, 89, 1180, 100, "完成", Sleep);
                                if (result.sim >= 0.8f) {
                                    GameUtil.this.tastState = 0;
                                    setTaskEnd();
                                    return;
                                }
                            }
                        }
                        result = mFairy.findPic(557,317,847,413, "myrhhd.png");
                        mFairy.onTap(0.8f, result, 1168,89,1175,102,"没有任何活动退出", Sleep);
                        if (result.sim >= 0.8f) {
                            GameUtil.this.tastState = 0;
                            setTaskEnd();
                            return;
                        }
                        for (int i = 0; i < 5; i++) {
                            mFairy.condit();
                            result = mFairy.findPic(result1.x + 224, result1.y - 1, result1.x + 366, result1.y + 60, "goto.png");
                            mFairy.onTap(0.8f, result, "参加", Sleep);
                            if (result.sim >= 0.8f) {
                                GameUtil.this.tastState = 1;
                                setTaskEnd();
                                return;
                            }
                            result = mFairy.findPic(result1.x + 224, result1.y - 1, result1.x + 366, result1.y + 60, "wancheng.png");
                            mFairy.onTap(0.8f, result, 1164, 89, 1180, 100, "退出", Sleep);
                            if (result.sim >= 0.8f) {
                                GameUtil.this.tastState = 0;
                                setTaskEnd();
                                return;
                            }
                            result = mFairy.findPic(result1.x + 224, result1.y - 1, result1.x + 366, result1.y + 60, "xswkq.png");
                            mFairy.onTap(0.8f, result, 1164, 89, 1180, 100, "退出", Sleep);
                            if (result.sim >= 0.8f) {
                                LtLog.e("未开启");
                                GameUtil.this.tastState = 0;
                                setTaskEnd();
                                return;
                            }
                        }
                    }
                    mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4}, 0, 711,479, 716, 185, 1000, 1500, 2);
                }
            }
        }.taskContent(mFairy, "找任务中");
        return tastState;
    }

    public void close(final int close) throws Exception {
        int j = 2;

        for (int i = 0; i < j; i++) {
            mFairy.condit();

            result = mFairy.findPic("qiehuan.png");
            mFairy.onTap(0.8f, result, "切换", Sleep);

            result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
            mFairy.onTap(0.8f, result, "收起对话栏", Sleep);

            if (close == 1) {
                result = mFairy.findPic("closehand.png");
                mFairy.onTap(0.8f, result, "关闭挂机", Sleep);
            }

            result = mFairy.findPic(478,7,1274,373, new String[]{"fork.png", "fork3.png","cha1.png","cha3.png","fork4.png"});
            mFairy.onTap(0.85f, result, "关叉", Sleep);
            if (result.sim > 0.85f) {
                j = 4;
            }

            result = mFairy.findPic(864, 81, 1139, 237, "jia4.png");
            if (result.sim < 0.8f) {
                /*result = mFairy.findPic(492, 12, 1272, 334, new String[]{"close4.png"});
                mFairy.onTap(0.85f, result, "关叉3", Sleep);*/

                result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork2.png"});
                mFairy.onTap(0.85f, result, "关叉2", Sleep);

                result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork1.png"});
                mFairy.onTap(0.9f, result, "关叉1", Sleep);
            }

            result = mFairy.findPic(154, 345, 1110, 671, "kjclose.png");
            mFairy.onTap(0.8f, result, "科举界面关闭", Sleep);

            result = mFairy.findPic("jjc1.png");
            mFairy.onTap(0.8f, result, "竞技场", Sleep);
        }

    }

    public void close1(final int close) throws Exception {
        new GameUtil(mFairy) {
            int j = 1;

            public void content_0() throws Exception {
                for (int i = 0; i < j; i++) {
                    mFairy.condit();

                    result = mFairy.findPic("qiehuan.png");
                    mFairy.onTap(0.8f, result, "切换", Sleep);

                    result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
                    mFairy.onTap(0.8f, result, "收起对话栏", Sleep);

                    if (close == 1) {
                        result = mFairy.findPic("closehand.png");
                        mFairy.onTap(0.8f, result, "关闭挂机", Sleep);
                    }

                    result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork.png", "fork3.png"});
                    mFairy.onTap(0.85f, result, "关叉", Sleep);
                    if (result.sim > 0.85f) {
                        j = 20;
                    } else {
                        j = 2;
                    }

                    result = mFairy.findPic(864, 81, 1139, 237, "jia4.png");
                    if (result.sim < 0.8f) {
                        result = mFairy.findPic(492, 12, 1272, 334, new String[]{"close4.png"});
                        mFairy.onTap(0.85f, result, "关叉3", Sleep);

                        result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork2.png"});
                        mFairy.onTap(0.85f, result, "关叉2", Sleep);

                        result = mFairy.findPic(492, 12, 1272, 334, new String[]{"fork1.png"});
                        mFairy.onTap(0.85f, result, "关叉1", Sleep);
                    }

                    result = mFairy.findPic(154, 345, 1110, 671, "kjclose.png");
                    mFairy.onTap(0.8f, result, "科举界面关闭", Sleep);

                    result = mFairy.findPic("jjc1.png");
                    mFairy.onTap(0.8f, result, "竞技场", Sleep);

                }

                setTaskEnd();
            }
        }.taskContent(mFairy, "------------------------------------------------------------------------关叉中");
    }

    //召唤跟随中
    public void callToFollow() throws Exception {
        new GameUtil(mFairy) {

            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(5, 2)) return;

                result = mFairy.findPic("duiwulan.png");
                mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("sqlb.png");
                mFairy.onTap(0.8f, result, 775, 612, 850, 633, "申请列表清空", Sleep);
                mFairy.onTap(0.8f, result, 430, 606, 496, 633, "关闭申请列表", Sleep);
                result = mFairy.findPic("yaoqingliebiao.png");
                mFairy.onTap(0.8f, result, 775, 612, 850, 633, "邀请列表清空", Sleep);
                mFairy.onTap(0.8f, result, 438, 613, 477, 625, "邀请列表关闭", Sleep);

                result = mFairy.findPic(39, 121, 298, 428, "call.png");
                mFairy.onTap(0.8f, result, "召唤跟随", Sleep);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(386,222,888,492,"rylq.png");
                    mFairy.onTap(0.8f, result, 755,449,767,456,"仍要拉取确定", Sleep);
                    setTaskName(2);
                    return;
                }

            }

            public void content_2() throws Exception {
                result = mFairy.findPic(386,222,888,492,"rylq.png");
                mFairy.onTap(0.8f, result, 755,449,767,456,"仍要拉取确定", Sleep);
                close(0);
                setTaskEnd();
            }

        }.taskContent(mFairy, "召唤跟随中");
    }

    //取消跟随中
    public void cancelFollowing() throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(3, 0)){
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("duiwulan.png");
                mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic(39, 121, 298, 428, "cancel.png");
                mFairy.onTap(0.9f, result, "取消跟随", Sleep);
                if (result.sim > 0.9f) {
                    close(0);
                    setTaskEnd();
                }
                result = mFairy.findPic("sqlb.png");
                mFairy.onTap(0.8f, result, 775, 612, 850, 633, "申请列表清空", Sleep);
                mFairy.onTap(0.8f, result, 430, 606, 496, 633, "关闭申请列表", Sleep);
                result = mFairy.findPic("yaoqingliebiao.png");
                mFairy.onTap(0.8f, result, 775, 612, 850, 633, "邀请列表清空", Sleep);
                mFairy.onTap(0.8f, result, 438, 613, 477, 625, "邀请列表关闭", Sleep);
            }
        }.taskContent(mFairy, "取消跟随中");
    }

    //包满或者钱不够
    public int man() throws Exception {
        result = mFairy.findPic("baoman.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("包满了"));
            return 1;
        }
        result = mFairy.findPic("baoman1.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("背包空间不够"));
            return 1;
        }
        result = mFairy.findPic("bagman1.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("背包空间不足两格"));
            return 1;
        }
        result = mFairy.findPic("baoman2.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("背包空间不足1格"));
            return 1;
        }
        result = mFairy.findPic("qianNo.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("钱不够"));
            return 1;
        }
        return 0;
    }

    //复活
    public void fuhuo() throws Exception {
        if (AtFairyConfig.getOption("fuhuo").equals("1")) {
            result = mFairy.findPic(491, 133, 784, 539, "fuhuo2.png");
            mFairy.onTap(0.8f, result, "原地复活", Sleep);
        } else {
            result = mFairy.findPic(491, 133, 784, 539, "fuhuo1.png");
            mFairy.onTap(0.8f, result, "默认复活", Sleep);
        }
    }

    //放弃任务
    public void fqtask() throws Exception {
        result = mFairy.findPic("Abandon mission.png");
        mFairy.onTap(0.8f, result, "放弃任务", Sleep);

        result = mFairy.findPic("Abandon sure.png");
        mFairy.onTap(0.8f, result, 743, 441, 775, 457, "放弃任务确定", Sleep);
        mFairy.onTap(0.8f, result, 1166, 90, 1187, 101, "放弃任务结束", Sleep);
    }

    //申请
    public void shenqing() throws Exception {
        result = mFairy.findPic("yijianhanhua.png");
        if (result.sim > 0.8f) {
            result = mFairy.findPic("youshenqing.png");
            mFairy.onTap(0.8f, result, "有人申请", Sleep);
        }

        result = mFairy.findPic("yaoqingliebiao.png");
        mFairy.onTap(0.8f, result, 775, 612, 850, 633, "邀请列表清空", Sleep);
        mFairy.onTap(0.8f, result, 438, 613, 477, 625, "邀请列表关闭", Sleep);

        result = mFairy.findPic("yourenshenqing.png");
        mFairy.onTap(0.8f, result, "有人申请", Sleep);

        result1 = mFairy.findPic("sqlb.png");
        if (result1.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("申请列表中"));
            for (int i = 0; i < 5; i++) {
                result = mFairy.findPic(874, 105, 1097, 582, "shenqingjieshou.png");
                mFairy.onTap(0.8f, result, "申请接受", Sleep);
            }
            mFairy.onTap(0.8f, result, 430, 606, 496, 633, "关闭申请列表", Sleep);
        }

    }

    //设置
    public void setUp() throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(5, 2)) return;
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 32, 46, 55, 65, "打开角色", Sleep);

                result = mFairy.findPic("jueseinface.png");
                mFairy.onTap(0.8f, result, 1209, 582, 1221, 605, "切换到设置", Sleep);

                result = mFairy.findPic("shezhi.png");
                mFairy.onTap(0.8f, result, 229, 353, 241, 362, "画质流程", 100);
                mFairy.onTap(0.8f, result, 229, 409, 242, 422, "分辨率低", 100);
                mFairy.onTap(0.8f, result, 812, 416, 813, 417, "同屏人数", 100);
                mFairy.onTap(0.8f, result, "其他设置", Sleep);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(630, 172, 741, 229, "kai.png");
                    mFairy.onTap(0.8f, result, "关闭屏蔽名字", 100);

                    result = mFairy.findPic(373, 377, 468, 424, "kai.png");
                    mFairy.onTap(0.8f, result, "关闭地面移动", 100);

                    result = mFairy.findPic(955, 377, 1052, 423, "guan.png");
                    mFairy.onTap(0.8f, result, "打开响应跟随", 100);
                    mFairy.onTap(1160, 92, 1179, 100, "关闭其他设置", 100);
                    setTaskName(2);
                    return;
                }

            }

            public void content_2() throws Exception {
                close(0);
                setTaskEnd();
                return;
            }

        }.taskContent(mFairy, "设置中");
    }

    //切换技能
    public void setjn() throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(5, 2)) return;
                result = mFairy.findPic("qiehuanlan.png");
                mFairy.onTap(0.8f, result, "切换栏", Sleep);


                result = mFairy.findPic(866, 311, 1279, 712, "jineng.png");
                mFairy.onTap(0.8f, result, "技能", Sleep);


                result = mFairy.findPic("jinenginface.png");
                mFairy.onTap(0.8f, result, 1208, 320, 1216, 344, "切换到配置", Sleep);
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("jn").equals("1") || AtFairyConfig.getOption("fbjn").equals("1")) {
                        mFairy.onTap(0.8f, result, 771, 143, 791, 156, "切换到技能1", Sleep);
                    }
                    if (AtFairyConfig.getOption("jn").equals("2") || AtFairyConfig.getOption("fbjn").equals("2")) {
                        mFairy.onTap(0.8f, result, 850, 144, 873, 160, "切换到技能2", Sleep);
                    }
                    if (AtFairyConfig.getOption("jn").equals("3") || AtFairyConfig.getOption("fbjn").equals("3")) {
                        mFairy.onTap(0.8f, result, 928, 147, 952, 156, "切换到技能3", Sleep);
                    }
                    if (AtFairyConfig.getOption("jn").equals("4") || AtFairyConfig.getOption("fbjn").equals("4")) {
                        mFairy.onTap(0.8f, result, 1011, 146, 1032, 162, "切换到技能4", Sleep);
                    }
                    if (AtFairyConfig.getOption("jn").equals("5") || AtFairyConfig.getOption("fbjn").equals("5")) {
                        mFairy.onTap(0.8f, result, 1096, 150, 1118, 160, "切换到技能5", Sleep);
                    }
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                close(1);
                setTaskEnd();
                return;
            }

        }.taskContent(mFairy, "切换技能中");
    }

    //传送城市
    public void goCity(final String str) throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                close(0);
                result = mFairy.findPic(724, 63, 1272, 446, "copy.png");
                if(result.sim>0.8f){
                    lkfb();
                }
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;

                result = mFairy.findPic("zoom1.png");
                mFairy.onTap(0.7f, result, "点开缩放栏", Sleep);

                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", 3500);

                result = mFairy.findPic("Renovation.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "家园地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {
                    if (str.equals("家族") || str.equals("家园")) {
                        switch (str) {
                            case "家族":
                                mFairy.onTap(0.8f, result, 43, 496, 63, 510, str, Sleep);
                                break;
                            case "家园":
                                mFairy.onTap(0.8f, result, 43, 373, 66, 385, str, Sleep);
                                break;
                        }
                        Thread.sleep(5000);
                        setTaskName(2);
                        return;
                    }
                    mFairy.onTap(0.8f, result, "切换到世界", Sleep);
                }

                result = mFairy.findPic("ctiyinface.png");
                if (result.sim > 0.8f) {
                    switch (str) {
                        case "龙城":
                            mFairy.onTap(0.8f, result, 528, 210, 538, 248, str, Sleep);
                            break;
                        case "桃源村":
                            mFairy.onTap(0.8f, result, 822, 307, 835, 354, str, Sleep);
                            break;
                        case "古月平原":
                            mFairy.onTap(0.8f, result, 622, 345, 635, 412, str, Sleep);
                            break;
                        case "八王府":
                            mFairy.onTap(0.8f, result, 728, 348, 739, 406, str, Sleep);
                            break;
                        case "蓬莱仙境":
                            mFairy.onTap(0.8f, result, 1035, 498, 1047, 567, str, Sleep);
                            break;
                        case "华山":
                            mFairy.onTap(0.8f, result, 345, 303, 360, 358, str, Sleep);
                            break;
                        case "出云":
                            mFairy.onTap(0.8f, result, 920, 214, 932, 263, str, Sleep);
                            break;
                        case "幻莹谷":
                            mFairy.onTap(0.8f, result, 803, 142, 815, 192, str, Sleep);
                            break;
                        case "天都":
                            mFairy.onTap(0.8f, result, 956, 53, 967, 101, str, Sleep);
                            break;
                        case "千年之境":
                            mFairy.onTap(0.8f, result, 1034, 151, 1047, 213, str, Sleep);
                            break;
                        case "楼兰":
                            mFairy.onTap(0.8f, result, 408, 436, 418, 495, str, Sleep);
                            break;
                        case "流沙洞":
                            mFairy.onTap(0.8f, result, 460, 557, 469, 615, str, Sleep);
                            break;
                        case "比翼城":
                            mFairy.onTap(0.8f, result, 401, 202, 409, 249, str, Sleep);
                            break;
                        case "秋风原":
                            mFairy.onTap(0.8f, result, 252, 198, 265, 256, str, Sleep);
                            break;
                        case "银月湖":
                            mFairy.onTap(0.8f, result, 178, 313, 189, 362, str, Sleep);
                            break;
                        case "水晶宫":
                            mFairy.onTap(0.8f, result, 999, 381, 1009, 437, str, Sleep);
                            break;
                        case "幽灵洞":
                            mFairy.onTap(0.8f, result, 756, 570, 768, 620, str, Sleep);
                            break;
                        case "冰封雪原":
                            mFairy.onTap(0.8f, result, 469, 119, 478, 166, str, Sleep);
                            break;
                        case "雪原之巅":
                            mFairy.onTap(0.8f, result, 321, 65, 328, 128, str, Sleep);
                            break;
                        case "猫猫城":
                            mFairy.onTap(0.8f, result, 211, 482, 224, 545, str, Sleep);
                            break;
                        case "夜幽谷":
                            mFairy.onTap(0.8f, result, 194,109,209,146, str, Sleep);
                            break;
                    }
                    Thread.sleep(5000);

                    result = mFairy.findPic("ctiyinface.png");
                    mFairy.onTap(0.8f, result, 1233,40,1242,55, "家园地图", Sleep);

                    result = mFairy.findPic("ctiyinface.png");
                    mFairy.onTap(0.8f, result, 1233,40,1242,55, "家园地图", Sleep);

                    result = mFairy.findPic(11,80,112,478, "dq.png");
                    mFairy.onTap(0.8f, result, 1232,43,1240,54,"地图关闭", Sleep);

                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                close(0);
                setTaskEnd();
                return;
            }
        }.taskContent(mFairy, "传送城市中");
    }

    //定位坐标
    public void coordinate(final String str, final int gmx, final int gmy) throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                close(1);
                setTaskName(1);
            }

            double x;
            double y;

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;

                result = mFairy.findPic("zoom1.png");
                mFairy.onTap(0.8f, result, "点开缩放栏", Sleep);

                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic("ctiyinface.png");
                mFairy.onTap(0.8f, result, "切换到地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("地图界面=" + str));
                    switch (str) {
                        case "家园":
                            x = gmx * 7.199 + gmy * 0.0099 + 231.6656;
                            y = gmx * 0.0281 + gmy * 7.2289 + 55.1968;
                            break;
                        case "龙城":
                            mFairy.onTap(0.8f, result, 528, 210, 538, 248, str, Sleep);
                            break;
                        case "桃源村":
                            x = gmx * 12.3113 + gmy * -0.1366 + 170.7225;
                            y = gmx * 0.0308 + gmy * 11.9912 + 66.7885;
                            break;
                        case "古月平原":
                            x = gmx * 12.0868 + gmy * -0.4302 + 179.6189;
                            y = gmx * -0.2679 + gmy * 12.0019 + 79.6113;
                            break;
                        case "八王府":
                            mFairy.onTap(0.8f, result, 728, 348, 739, 406, str, Sleep);
                            break;
                        case "蓬莱仙境":
                            mFairy.onTap(0.8f, result, 1035, 498, 1047, 567, str, Sleep);
                            break;
                        case "华山":
                            x = gmx * 8.477 + gmy * 0.0512 + 216.5712;
                            y = gmx * -0.0401 + gmy * 8.4763 + 65.5757;
                            break;
                        case "出云":
                            x = gmx * 9.5264 + gmy * 0.0254 + 202.1002;
                            y = gmx * -0.0046 + gmy * 9.6252 + 58.674;
                            break;
                        case "幻莹谷":
                            x = gmx * 12.4884 + gmy * 0.0814 + 202.6163;
                            y = gmx * 0.1705 + gmy * 12.2229 + 57.7112;
                            break;
                        case "天都":
                            x = gmx * 10.5924 + gmy * -0.245 + 201.3534;
                            y = gmx * -0.3705 + gmy * 10.7108 + 83.3434;
                            break;
                        case "千年之境":
                            x = gmx * 12.0615 + gmy * 0.0653 + 85.9501;
                            y = gmx * -0.0819 + gmy * 11.9129 + 75.0666;
                            break;
                        case "楼兰":
                            x = gmx * 10.7826 + gmy * 0.0538 + 223.6738;
                            y = gmx * 0.0884 + gmy * 10.4946 + 67.1326;
                            break;
                        case "流沙洞":
                            x = gmx * 12.2908 + gmy * 0.0993 + 158.773;
                            y = gmx * 0.117 + gmy * 12.3936 + 58.9574;
                            break;
                        case "比翼城":
                            x = gmx * 10.7861 + gmy * 0.1565 + 178.3389;
                            y = gmx * 0.1167 + gmy * 10.6722 + 60.6333;
                            break;
                        case "秋风原":
                            x = gmx * 12.1573 + gmy * -0.1411 + 170.8266;
                            y = gmx * 0.1613 + gmy * 12.4194 + 54.2581;
                            break;
                        case "银月湖":
                            x = gmx * 12.3102 + gmy * -0.1105 + 168.098;
                            y = gmx * -0.0107 + gmy * 12.9234 + 42.0196;
                            break;
                        case "水晶宫":
                            x = gmx * 10.7029 + gmy * -0.0318 + 183.1592;
                            y = gmx * -0.0623 + gmy * 10.7255 + 63.8727;
                            break;
                        case "幽灵洞":
                            x = gmx * 10.8106 + gmy * 0.083 + 182.2067;
                            y = gmx * 0.0307 + gmy * 10.7188 + 63.4107;
                            break;
                        case "冰封雪原":
                            x = gmx * 10.7267 + gmy * 0.0267 + 186.9467;
                            y = gmx * -0.0538 + gmy * 10.9077 + 58.9538;
                            break;
                        case "雪原之巅":
                            x = gmx * 7.8192 + gmy * 0.0508 + 192.5198;
                            y = gmx * -0.0795 + gmy * 7.7171 + 69.0652;
                            break;
                        case "猫猫城":
                            x = gmx * 7.0678 + gmy * 0.0968 + 286.0025;
                            y = gmx * -0.0579 + gmy * 7.1613 + 61.8759;
                            break;
                        case "夜幽谷":
                            x= gmx * 7.825 + gmy * 0.3095 + 317.4536;
                            y= gmx * 0.025 + gmy * 7.4286 + 78.7107;
                            break;
                    }
                    mFairy.tap((int) x, (int) y);
                    LtLog.e(mFairy.getLineInfo("坐标x=" + (int) x + ",y=" + (int) y));
                    close(1);
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                if (dazeTime > 2) {
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    setTaskEnd();
                    return;
                }
                Thread.sleep(1000);
            }
        }.taskContent(mFairy, "定位坐标中");
    }

    //物品的使用
    public void goods() throws Exception {
        new GameUtil(mFairy) {
            @Override
            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
                return;
            }

            int wpCount = 0;

            public void content_1() throws Exception {
                if (overtime(9, 99)) {
                    close(0);
                    return;
                }
                result = mFairy.findPic("daily.png");
                mFairy.onTap(0.8f, result, 1071, 110, 1087, 121, "打开背包", 3000);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 632, 96, 663, 111, "背包界面内", Sleep);
                    result = mFairy.findPic(600, 146, 1137, 529, new String[]{"shanhai.png", "yinpiao.png", "jinpiao.png"});
                    mFairy.onTap(0.9f, result, "物品", Sleep);
                    if (result.sim > 0.9f) {
                        err--;
                        wpCount++;
                        if (wpCount > 20) {
                            setTaskEnd();
                            return;
                        }
                    } else {
                        mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5, 6, 7}, 0, 1106, 309, 629, 301, 1000, 1500, 2);
                    }

                    result = mFairy.findPic(23, 31, 1248, 690, "xmqjUse.png");
                    mFairy.onTap(0.8f, result, "使用", Sleep);

                    result = mFairy.findPic(23, 31, 1248, 690, "duihuan.png");
                    mFairy.onTap(0.8f, result, "兑换", Sleep);

                    result = mFairy.findPic(281, 198, 1036, 684, "qluse.png");
                    mFairy.onTap(0.8f, result, "批量确定", Sleep);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(538, 96, 752, 195, "cishuxianzhi.png");
                        mFairy.onTap(0.8f, result, 915, 153, 934, 167, "次数限制", Sleep);
                        if (result.sim > 0.8f) {
                            mFairy.ranSwipe(1106, 309, 629, 301, 1000, (long) 1500, 2);
                        } else {
                            Thread.sleep(4000);
                        }
                    }

                    result = mFairy.findPic("shanhaishop.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("山海商店中"));
                        mFairy.onTap(0.8f, result, 173, 153, 188, 164, "坡介石", Sleep);
                        mFairy.onTap(0.8f, result, 1091, 463, 1108, 476, "最大", Sleep);
                        mFairy.onTap(0.8f, result, 968, 597, 989, 611, "购买", Sleep);
                        mFairy.onTap(0.8f, result, 772, 433, 806, 448, "确定", 5000);
                        mFairy.onTap(0.8f, result, 630, 161, 661, 176, "精髓", Sleep);
                        mFairy.onTap(0.8f, result, 1091, 463, 1108, 476, "最大", Sleep);
                        mFairy.onTap(0.8f, result, 968, 597, 989, 611, "购买", Sleep);
                        mFairy.onTap(0.8f, result, 772, 433, 806, 448, "确定", 5000);
                        mFairy.onTap(0.8f, result, 268, 261, 287, 270, "神器", Sleep);
                        mFairy.onTap(0.8f, result, 1091, 463, 1108, 476, "最大", Sleep);
                        mFairy.onTap(0.8f, result, 968, 597, 989, 611, "购买", Sleep);
                        mFairy.onTap(0.8f, result, 772, 433, 806, 448, "确定", 5000);
                        mFairy.onTap(0.8f, result, 584, 245, 614, 259, "精魂", Sleep);
                        mFairy.onTap(0.8f, result, 1091, 463, 1108, 476, "最大", Sleep);
                        mFairy.onTap(0.8f, result, 968, 597, 989, 611, "购买", Sleep);
                        mFairy.onTap(0.8f, result, 772, 433, 806, 448, "确定", 5000);
                        mFairy.onTap(0.8f, result, 1143, 53, 1162, 64, "关闭", Sleep);
                        result = mFairy.findPic("dhjsure.png");
                        mFairy.onTap(0.8f, result, 639, 434, 669, 446, "确定", 5000);
                        mFairy.onTap(0.8f, result, 1143, 53, 1162, 64, "关闭", Sleep);
                        result = mFairy.findPic("quehuo.png");
                        mFairy.onTap(0.8f, result, 633, 429, 671, 445, "确定", 5000);
                        mFairy.onTap(0.8f, result, 1143, 53, 1162, 64, "关闭", Sleep);
                        mFairy.ranSwipe(1106, 309, 629, 301, 1000, (long) 1500, 2);
                    }
                    for (int i = 0; i < 2; i++) {
                        mFairy.condit();
                        result = mFairy.findPic(281, 198, 1036, 684, "plquxiao.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("使用中"));
                            i = 0;
                        }
                    }
                }
            }
        }.taskContent(mFairy, "物品的使用中");
    }

    //清包
    public void clearBag() throws Exception {
        new GameUtil(mFairy) {

            public void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic("qingbao.png");
                if(result.sim>0.8f){
                    mFairy.onTap(771,584,799,596,"清包出现异常",1000);
                    setTaskName(0);
                    return;
                }
            }

            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(6, 0)) return;
                result = mFairy.findPic("bag.png");
                mFairy.onTap(0.8f, result, "包裹", Sleep);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面中"));
                    mFairy.onTap(0.8f, result, 1053, 611, 1083, 625, "整理", Sleep);
                    if (AtFairyConfig.getOption("dhxy").equals("1")) {
                        result = mFairy.findPic(756, 172, 1160, 572, "lanzhuang.png");
                        mFairy.onTap(0.9f, result, 795, 215, 813, 239, "选择一个蓝装", 3000);
                        if (result.sim > 0.9f) {
                            result = mFairy.findPic(596, 46, 1226, 709, "gengduo.png");
                            mFairy.onTap(0.8f, result, "更多", Sleep);

                            result = mFairy.findPic(596, 46, 1226, 709, "duihuanxy.png");
                            mFairy.onTap(0.8f, result, "兑换幸运", 3000);

                            result = mFairy.findPic("duihuanxy1.png");
                            mFairy.onTap(0.8f, result, 699, 504, 720, 517, "勾选蓝装", Sleep);
                            mFairy.onTap(0.8f, result, "兑换", 2000);

                            result = mFairy.findPic("xingyunman.png");
                            mFairy.onTap(0.8f, result, "幸运值满取消", 2000);

                            result = mFairy.findPic("duihuanxy1.png");
                            mFairy.onTap(0.8f, result, 778, 582, 817, 595, "取消", Sleep);

                            result = mFairy.findPic("duihuanqxqx.png");
                            mFairy.onTap(0.8f, result, 778, 582, 817, 595, "取消", Sleep);

                            result = mFairy.findPic("baginface.png");
                            mFairy.onTap(0.8f, result, "包裹界面", 2000);
                        }
                        setTaskName(2);
                        return;
                    } else {
                        setTaskName(2);
                        return;
                    }
                }
            }

            public void content_2() throws Exception {
                if (overtime(6, 3)) return;
                if (!AtFairyConfig.getOption("hslz").equals("1")) {
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic("bag.png");
                mFairy.onTap(0.8f, result, "包裹", Sleep);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(765,581,946,653,"huishou.png");
                    mFairy.onTap(0.8f, result, "回收", 3000);

                    result = mFairy.findPic(978,572,1177,664,"huishou1.png");
                    mFairy.onTap(0.8f, result, 921, 618, 939, 630, "勾选蓝装", Sleep);
                    mFairy.onTap(0.8f, result, "回收1", Sleep);
                    if (result.sim > 0.8f) {
                        close(0);
                        setTaskName(3);
                        return;
                    }
                }
            }

            public void content_3() throws Exception {
                if (overtime(6, 4)) return;
                if (timekeep(0, 300000, "清包超时")) {
                    LtLog.e(mFairy.getLineInfo("清包超时"));
                    setTaskName(4);
                    return;
                }
                result = mFairy.findPic("duihuanxy1.png");
                mFairy.onTap(0.8f, result, 778, 582, 817, 595, "取消", Sleep);

                result = mFairy.findPic("bag.png");
                mFairy.onTap(0.8f, result, "包裹", Sleep);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面中"));
                    result = mFairy.findPic(761, 177, 1147, 570, new String[]{"wupin1.png", "wupin2.png", "wupin3.png", "wupin4.png", "wupin5.png", "wupin7.png", "wupin8.png", "xyd1.png", "xyd3.png"});
                    mFairy.onTap(0.9f, result, "物品", 2000);
                    if (result.sim > 0.9f) {
                        err = 0;
                    }

                    result = mFairy.findPic(224, 189, 736, 652, "gengduo.png");
                    mFairy.onTap(0.8f, result, "更多", Sleep);

                    result = mFairy.findPic(224, 189, 736, 652, "piliangsy.png");
                    mFairy.onTap(0.8f, result, "批量使用", Sleep);

                    result = mFairy.findPic("piliangsy1.png");
                    mFairy.onTap(0.8f, result, "批量使用", 1000);
                    if (result.sim > 0.8f) {
                        if (man() == 1) {
                            setTaskEnd();
                            return;
                        }
                    }
                    result = mFairy.findPic(224, 189, 736, 652, "sywp.png");
                    mFairy.onTap(0.8f, result, "物品使用", 1000);
                    if (result.sim > 0.8f) {
                        if (man() == 1) {
                            setTaskEnd();
                            return;
                        }
                    }
                    result = mFairy.findPic("wupinfs.png");
                    mFairy.onTap(0.8f, result, "发送", 1000);

                    mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5, 6}, 0, 918, 538, 917, 225, 500, 1500, 2);
                }
            }

            public void content_4() throws Exception {
                close(0);
                setTaskEnd();
                return;
            }
        }.taskContent(mFairy, "清包中");
    }

    //退队
    public void retire() throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;

                result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
                mFairy.onTap(0.8f, result,   "频道收回", Sleep);

                result = mFairy.findPic("duiwulan.png");
                mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("duiwulan1.png");
                mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("sqlb.png");
                mFairy.onTap(0.8f, result, 775, 612, 850, 633, "申请列表清空", Sleep);
                mFairy.onTap(0.8f, result, 430, 606, 496, 633, "关闭申请列表", Sleep);

                result = mFairy.findPic("yaoqingliebiao.png");
                mFairy.onTap(0.8f, result, 775, 612, 850, 633, "邀请列表清空", Sleep);
                mFairy.onTap(0.8f, result, 438, 613, 477, 625, "邀请列表关闭", Sleep);

                result = mFairy.findPic(275,587,501,687,"chuanjianduiwu.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("没有队伍退队结束"));
                    mFairy.onTap(0.8f, result, 1180,88,1193,101, "关闭", Sleep);
                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic(708,572,1216,693,"yijianhanhua.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("是队长退队"));
                    mFairy.onTap(0.8f, result, 409, 627, 439, 642, "是队长退队", Sleep);

                    result = mFairy.findPic("tuiduisure.png");
                    mFairy.onTap(0.8f, result, 743, 445, 781, 462, "确定离开队伍", Sleep);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(770,577,1218,696,new String[]{"gensuiduiwu.png", "qxgs.png"});
                mFairy.onTap(0.8f, result, 415, 623, 447, 639, "是队员退队", Sleep);


                result = mFairy.findPic("tuiduisure.png");
                mFairy.onTap(0.8f, result, 743, 445, 781, 462, "确定离开队伍", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                close(0);
                setTaskEnd();
                return;
            }

        }.taskContent(mFairy, "退队中");
    }

    //一键喊话
    public void yjhh() throws Exception {
        LtLog.e(mFairy.getLineInfo("一键喊话中"));
        result = mFairy.findPic("yijianhanhua.png");
        mFairy.onTap(0.8f, result, "一键喊话", Sleep);

        result = mFairy.findPic(965, 418, 1127, 599, "hhfj.png");
        mFairy.onTap(0.8f, result, "附近频道", Sleep);

        result = mFairy.findPic("yijianhanhua.png");
        mFairy.onTap(0.8f, result, "一键喊话", Sleep);

        result = mFairy.findPic(965, 418, 1127, 599, "hhjz.png");
        mFairy.onTap(0.8f, result, "家族频道", Sleep);

        result = mFairy.findPic("yijianhanhua.png");
        mFairy.onTap(0.8f, result, "一键喊话", Sleep);

        result = mFairy.findPic(965, 418, 1127, 599, "hhzd.png");
        mFairy.onTap(0.8f, result, "组队频道", Sleep);
    }

    //踢人
    public void kicking() throws Exception {
        new GameUtil(mFairy) {

            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(5, 2)) return;
                result = mFairy.findPic("duiwulan.png");
                mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("sqlb.png");
                mFairy.onTap(0.8f, result, 775, 612, 850, 633, "申请列表清空", Sleep);
                mFairy.onTap(0.8f, result, 430, 606, 496, 633, "关闭申请列表", Sleep);

                result = mFairy.findPic("yaoqingliebiao.png");
                mFairy.onTap(0.8f, result, 775, 612, 850, 633, "邀请列表清空", Sleep);
                mFairy.onTap(0.8f, result, 438, 613, 477, 625, "邀请列表关闭", Sleep);

                result = mFairy.findPic("duiwulan1.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(265, 130, 314, 170, "Numberpeople.png");
                    if (result.sim > 0.7f) {
                        int numcolor = mFairy.getColorNum(91, 152, 235, 171, 0.9f, 0, "103,202,46");
                        LtLog.e(mFairy.getLineInfo("num=" + numcolor));
                        if (numcolor < 10) {
                            mFairy.onTap(0.7f, result, "第1个人踢出去", 2000);
                            result = mFairy.findPic(331, 12, 581, 691, "qingliduiwu.png");
                            mFairy.onTap(0.8f, result, "请离队员", Sleep);
                        }
                    }
                    result = mFairy.findPic(270, 190, 310, 229, "Numberpeople.png");
                    if (result.sim > 0.7f) {
                        int numcolor = mFairy.getColorNum(90, 213, 236, 235, 0.9f, 0, "103,202,46");
                        LtLog.e(mFairy.getLineInfo("num=" + numcolor));
                        if (numcolor < 10) {
                            mFairy.onTap(0.7f, result, "第2个人踢出去", 2000);
                            result = mFairy.findPic(331, 12, 581, 691, "qingliduiwu.png");
                            mFairy.onTap(0.8f, result, "请离队员", Sleep);
                        }
                    }
                    result = mFairy.findPic(267, 251, 316, 300, "Numberpeople.png");
                    if (result.sim > 0.7f) {
                        int numcolor = mFairy.getColorNum(92, 276, 232, 295, 0.9f, 0, "103,202,46");
                        LtLog.e(mFairy.getLineInfo("num=" + numcolor));
                        if (numcolor < 10) {
                            mFairy.onTap(0.7f, result, "第3个人踢出去", 2000);
                            result = mFairy.findPic(331, 12, 581, 691, "qingliduiwu.png");
                            mFairy.onTap(0.8f, result, "请离队员", Sleep);
                        }
                    }
                    result = mFairy.findPic(267, 308, 314, 360, "Numberpeople.png");
                    if (result.sim > 0.7f) {
                        int numcolor = mFairy.getColorNum(92, 334, 235, 361, 0.9f, 0, "103,202,46");
                        LtLog.e(mFairy.getLineInfo("num=" + numcolor));
                        if (numcolor < 10) {
                            mFairy.onTap(0.7f, result, "第4个人踢出去", 2000);
                            result = mFairy.findPic(331, 12, 581, 691, "qingliduiwu.png");
                            mFairy.onTap(0.8f, result, "请离队员", Sleep);
                        }
                    }
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                close(0);
                setTaskEnd();
            }
        }.taskContent(mFairy, "踢人中");
    }

    //签到
    public void  qiandao() throws Exception {
        new GameUtil(mFairy) {

            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(15, 2)) return;
                result = mFairy.findPic(676, 7, 1152, 170, "fuli.png");
                mFairy.onTap(0.8f, result, "福利", Sleep);

                result = mFairy.findPic("fuliinface.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "福利界面"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(76, 107, 281, 661, "mrqd.png");
                    mFairy.onTap(0.8f, result, "每日签到", Sleep);

                    result = mFairy.findPic(1037,287,1186,431, "24.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(1038, 290, 1181, 427, "yqd.png");
                        if (result.sim > 0.8f) {
                            mFairy.ranSwipe(1165, 473, 1162, 319, 1000, (long) 1500, 1);
                        }
                    }
                    result = mFairy.findPic(556, 111, 912, 180, new String[]{"qdinface.png", "qdinface1.png"});
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 15; i++) {
                            mFairy.condit();
                            result = mFairy.findPic(1037,287,1186,431, "24.png");
                            if (result.sim > 0.8f) {

                                result = mFairy.findPic(1038, 290, 1181, 427, "yqd.png");
                                if (result.sim > 0.8f) {
                                    mFairy.ranSwipe(1165, 473, 1162, 319, 1000, (long) 1500, 1);
                                }
                                result = mFairy.findPic(1031,418,1173,527, "yqd.png");
                                if (result.sim > 0.8f) {
                                    mFairy.ranSwipe(1165, 473, 1162, 319, 1000, (long) 1500, 1);
                                }
                            }
                            for (int j = 1; j < 5; j++) {
                                result = mFairy.findPic(301,177,1181,542, "Sign in"+j+".png");
                                mFairy.onTap(0.8f, result, "签到", 1000);
                                mFairy.onTap(0.8f, result, 1083, 136, 1101, 148, "签到", 1000);
                                result = mFairy.findPic(568,395,719,587, "qdcg.png");
                                if (result.sim > 0.8f) {
                                    setTaskName(2);
                                    return;
                                }
                                if (result.sim < 0.8f) {
                                    result = mFairy.findPic(893,271,1183,524, "Sign in1"+j+".png");
                                    mFairy.onTap(0.8f, result, "签到黄", 1000);
                                    mFairy.onTap(0.8f, result, 1083, 136, 1101, 148, "签到黄", 1000);
                                }
                                result = mFairy.findPic(568,395,719,587, "qdcg.png");
                                if (result.sim > 0.8f) {
                                    setTaskName(2);
                                    return;
                                }
                            }
                        }
                        setTaskName(2);
                        return;
                    }
                }
            }

            public void content_2() throws Exception {

                if (overtime(20, 3)) return;

                result = mFairy.findPic("fuliinface.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "福利界面"));
                if (result.sim > 0.8f) {

                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic(862, 308, 1104, 641, "use1.png");
                        if (result.sim > 0.8f) {
                            i = 0;
                            mFairy.onTap(0.8f, result, "使用", 1000);
                        }
                    }

                    result = mFairy.findPic(76, 107, 281, 661, "yktq.png");
                    mFairy.onTap(0.8f, result, "月卡特权", Sleep);

                    /*result = mFairy.findPic(303, 106, 1201, 690, "jia1.png");
                    if (result.sim > 0.75f) {
                        LtLog.e(mFairy.getLineInfo("充值购买"));
                        setTaskName(3);
                        return;
                    }*/

                    result = mFairy.findPic(939,538,1090,599, "yklq.png");
                    mFairy.onTap(0.7f, result, "月卡领取", Sleep);

                    result = mFairy.findPic(650,529,807,599, "yklq.png");
                    mFairy.onTap(0.7f, result, "月卡领取", Sleep);

                    result = mFairy.findPic(360,536,527,603, "yklq.png");
                    mFairy.onTap(0.7f, result, "月卡领取", Sleep);

                    if (result.sim > 0.7f) {
                        setTaskName(3);
                        return;
                    }
                }

            }

            public void content_3() throws Exception {
                close(0);
                setTaskEnd();
                return;
            }
        }.taskContent(mFairy, "签到中");
    }

    //每日饲料
    public void  mrsl() throws Exception {
        new GameUtil(mFairy) {

            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;

                result = mFairy.findPic(1202,173,1271,261,"caidan.png");
                mFairy.onTap(0.8f,result,"展开",1000);

                result = mFairy.findPic(1013,430,1263,698,"jiayuan.png");
                mFairy.onTap(0.8f,result,"家园",1000);

                result = mFairy.findPic(70,81,1199,658,"muchang.png");
                mFairy.onTap(0.8f,result,"牧场",1000);

                result = mFairy.findPic(732,476,1053,577,"lqmrsl.png");
                if(result.sim>0.8f){
                    mFairy.onTap(0.8f,result,"领取每日饲料",1000);

                }
                result = mFairy.findPic(732,476,1053,577,"gmsl.png");
                if(result.sim>0.8f){
                    setTaskEnd();
                    return;
                }

            }
        }.taskContent(mFairy, "每日饲料中");
    }

    //家谱奖励
    public void jpjl() throws Exception {
        new GameUtil(mFairy) {

            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(5, 2)) return;
                result = mFairy.findPic("suojinduihuakuang.png");
                mFairy.onTap(0.8f, result, "缩进对话框", Sleep);


                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 507, 591, 533, 600, "点击家谱", Sleep);


                result = mFairy.findPic("jiapuinface.png");
                mFairy.onTap(0.8f, result, 1218, 202, 1229, 229, "家谱界面羽毛", Sleep);


                result = mFairy.findPic("jiapupiliang.png");
                mFairy.onTap(0.8f, result, 1218, 202, 1229, 229, "家谱批量领取", 3000);
                mFairy.onTap(0.8f, result, 600, 588, 669, 604, "家谱一键领取", Sleep);
                mFairy.onTap(0.8f, result, 1213, 328, 1227, 345, "家谱一键领取关闭", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(5, 3)) return;
                result = mFairy.findPic("jiapuinface.png");
                mFairy.onTap(0.8f, result, 1218, 202, 1229, 229, "家谱界面结拜", Sleep);


                result = mFairy.findPic("jbzhaoji.png");
                mFairy.onTap(0.8f, result, 692, 551, 709, 567, "结拜奖励1", Sleep);
                mFairy.onTap(0.8f, result, 830, 549, 848, 566, "结拜奖励2", Sleep);
                mFairy.onTap(0.8f, result, 972, 550, 988, 562, "结拜奖励3", Sleep);
                mFairy.onTap(0.8f, result, 1106, 554, 1127, 567, "结拜奖励4", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {
                close(0);
                setTaskEnd();
                return;
            }
        }.taskContent(mFairy, "家谱奖励中");
    }

    //领邮件
    public void lyj() throws Exception {
        new GameUtil(mFairy) {

            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(5, 2)) return;
                result = mFairy.findPic("suojinduihuakuang.png");
                mFairy.onTap(0.8f, result, "缩进对话框", Sleep);


                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 416, 593, 438, 600, "点击好友", Sleep);


                result = mFairy.findPic("youjianinface.png");
                mFairy.onTap(0.8f, result, "切换到邮件", Sleep);


                result = mFairy.findPic("yjyjlq.png");
                mFairy.onTap(0.8f, result, "一键领取", Sleep);
                mFairy.onTap(0.8f, result, 749, 447, 778, 461, "一键领取确定", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                close(0);
                setTaskEnd();
                return;
            }
        }.taskContent(mFairy, "领邮件中");
    }

    //离开副本
    public static void lkfb() throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(3, 2)) return;

                result = mFairy.findPic(724, 63, 1272, 446, "copy.png");
                result1 = mFairy.findPic(1136,2,1262,29, "sjzz.png");
                if (result.sim>0.8f && result1.sim>0.8f){
                    LtLog.e("三军之战中！");
                    mFairy.sleep(50000);
                    err=0;
                }else if (result.sim>0.8f){
                    mFairy.onTap(0.8f, result, "离开副本", Sleep);
                }


                result = mFairy.findPic(491, 225, 1099, 549, "tcfbsure.png");
                mFairy.onTap(0.8f, result, "离开副本确定", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }

                result1 = mFairy.findPic(1129,4,1263,25,new String[]{"fz.png","zhu.png"});
                if (result1.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }

                result = mFairy.findPic(491, 225, 1099, 549, "likai.png");
                mFairy.onTap(0.8f, result, "离开", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                close(0);
                setTaskEnd();
                return;
            }
            public void content_3() throws Exception {
                if (overtime(8, 1)) return;
                result1 = mFairy.findPic(660,7,1271,186,"hd.png");
                if (result1.sim > 0.8f) {
                    mFairy.onTap(0.8f, result1, 1191,93,1210,116,"打开地图", Sleep);
                    mFairy.onTap(0.8f, result1, 617,316,622,321,"npc", Sleep);
                    mFairy.onTap(0.8f, result1, 1230,48,1243,60,"关闭地图", Sleep);
                    mFairy.onTap(0.8f, result1, 662,345,696,396,"npc点击", Sleep);
                }

                result = mFairy.findPic(999,262,1272,540, "fhpl.png");
                mFairy.onTap(0.8f, result, "返回蓬莱", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
            }

        }.taskContent(mFairy, "离开副本中");
    }

    public void cs() throws Exception {
        new GameUtil(mFairy) {

            public void content_0() throws Exception {
                result = mFairy.findPic(749,66,1171,158,"Activeinterface.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "活动界面"));

                mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4}, 0, 716, 491, 716, 185, 1000, 1500, 0);
            }

        }.taskContent(mFairy, "测试");
    }

    //使用活力
    public void syhl() throws Exception {

        new GameUtil(mFairy) {
            ControlSplit dhhc = new ControlSplit();
            ControlSplit dhlc = new ControlSplit();
            ControlSplit pr = new ControlSplit();
            ControlSplit ly = new ControlSplit();

            public void create() throws Exception {
                dhhc.count = 0;
                dhlc.count = 0;
                pr.count = 0;
                if (!AtFairyConfig.getOption("opcount").equals("")) {
                    dhhc = strSplit(AtFairyConfig.getOption("opcount"));
                }
                if (!AtFairyConfig.getOption("opcount1").equals("")) {
                    dhlc = strSplit(AtFairyConfig.getOption("opcount1"));
                }
                if (!AtFairyConfig.getOption("opcount2").equals("")) {
                    pr = strSplit(AtFairyConfig.getOption("opcount2"));
                }
                if (!AtFairyConfig.getOption("opcount3").equals("")) {
                    ly = strSplit(AtFairyConfig.getOption("opcount3"));
                }
            }

            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(5, 2)) return;
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 32, 46, 55, 65, "打开角色", Sleep);

                result = mFairy.findPic("jueseinface.png");
                mFairy.onTap(0.8f, result, 1109, 297, 1132, 305, "活力使用", Sleep);

                result = mFairy.findPic("huoliinface.png");
                if (result.sim > 0.8f) {
                    for (int i = 0; i < dhhc.count; i++) {
                        mFairy.onTap(0.8f, result, 975, 403, 998, 415, "兑换红储", 100);
                    }
                    for (int i = 0; i < dhlc.count; i++) {
                        mFairy.onTap(0.8f, result, 971, 499, 1007, 513, "兑换蓝储", 100);
                    }
                    if (pr.count != 0) {
                        mFairy.ranSwipe(823, 563, 823, 294, 500, (long) 2000, 2);
                        result = mFairy.findPic(739, 249, 866, 598, "pengren.png");
                        mFairy.onTap(0.8f, result, result.x + 228, result.y + 26, result.x + 229, result.y + 27, "烹饪", 2000);
                        if (!AtFairyConfig.getOption("mt").equals("")) {
                            mFairy.onTap(0.8f, result, 904,271,913,272,"馒头", 100);
                        }
                        if (!AtFairyConfig.getOption("dn").equals("")) {
                            mFairy.onTap(0.8f, result, 989,272,993,276,"豆奶", 100);
                        }
                        if (!AtFairyConfig.getOption("nrm").equals("")) {
                            mFairy.onTap(0.8f, result, 1078,269,1084,273,"牛肉面", 100);
                        }
                        if (!AtFairyConfig.getOption("gj").equals("")) {
                            mFairy.onTap(0.8f, result, 908,344,914,347,"果酒", 100);
                        }
                        if (!AtFairyConfig.getOption("hsr").equals("")) {
                            mFairy.onTap(0.8f, result, 993,341,996,344,"红烧肉", 100);
                        }
                        if (!AtFairyConfig.getOption("csm").equals("")) {
                            mFairy.onTap(0.8f, result, 1073,342,1077,348,"长寿面", 100);
                        }
                        result = mFairy.findPic("pengren1.png");
                        for (int i = 0; i < pr.count; i++) {
                            mFairy.onTap(0.8f, result, "烹饪", 100);
                        }
                    }
                    if (ly.count != 0) {
                        result = mFairy.findPic("jinenginface.png");
                        mFairy.onTap(0.8f, result, 256,495,267,503, "切换到炼药", Sleep);
                        if (result.sim < 0.8f) {
                            mFairy.ranSwipe(823, 563, 823, 294, 500, (long) 2000, 2);
                            result = mFairy.findPic(739, 249, 866, 598, "pengren.png");
                            mFairy.onTap(0.8f, result, result.x + 228, result.y + 26, result.x + 229, result.y + 27, "烹饪", 2000);
                            result = mFairy.findPic("jinenginface.png");
                            mFairy.onTap(0.8f, result, 256,495,267,503, "切换到炼药", Sleep);
                        }

                        result = mFairy.findPic(404,116,654,190,"lianyao1.png");
                        if (result.sim < 0.8f) {
                            mFairy.ranSwipe(1081,270, 1084,432, 500, (long) 2000, 2);
                            if (!AtFairyConfig.getOption("zxy").equals("")) {
                                mFairy.onTap(0.8f, result, 878,293,884,300,"止血药", 100);
                            }
                            if (!AtFairyConfig.getOption("bqw").equals("")) {
                                mFairy.onTap(0.8f, result, 954,289,961,296,"补气丸", 100);
                            }
                            if (!AtFairyConfig.getOption("jcy").equals("")) {
                                mFairy.onTap(0.8f, result, 1039,290,1044,295,"金疮药", 100);
                            }
                            if (!AtFairyConfig.getOption("hss").equals("")) {
                                mFairy.onTap(0.8f, result, 1129,293,1135,297,"还神散", 100);
                            }

                            if (!AtFairyConfig.getOption("xjd").equals("")) {
                                mFairy.onTap(0.8f, result, 879,375,883,380,"行军丹", 100);
                            }
                            if (!AtFairyConfig.getOption("bhl").equals("")) {
                                mFairy.onTap(0.8f, result, 955,374,961,378,"百花露", 100);
                            }
                            if (!AtFairyConfig.getOption("tjxd").equals("")) {
                                mFairy.onTap(0.8f, result, 1038,374,1042,380,"太极仙丹", 100);
                            }
                            if (!AtFairyConfig.getOption("qjyl").equals("")) {
                                mFairy.onTap(0.8f, result, 1119,373,1127,377,"琼浆玉露", 100);
                            }

                            mFairy.ranSwipe(1084,367, 1084,210, 500, (long) 2000, 2);
                            if (!AtFairyConfig.getOption("xmw").equals("")) {
                                mFairy.onTap(0.8f, result, 879,375,883,380,"续命丸", 100);
                            }
                            if (!AtFairyConfig.getOption("dhd").equals("")) {
                                mFairy.onTap(0.8f, result, 955,374,961,378,"大还丹", 100);
                            }
                            if (!AtFairyConfig.getOption("jhyl").equals("")) {
                                mFairy.onTap(0.8f, result, 1038,374,1042,380,"九花玉露", 100);
                            }
                            if (!AtFairyConfig.getOption("jzhhd").equals("")) {
                                mFairy.onTap(0.8f, result, 1119,373,1127,377,"九转还魂丹", 100);
                            }

                            for (int i = 0; i < ly.count; i++) {
                                mFairy.onTap(0.8f, result, 975, 599, 1014, 615, "炼药", 100);
                            }

                        }
                    }
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                close(0);
                setTaskEnd();
                return;
            }

        }.taskContent(mFairy, "使用活力中");
    }

    //使用药品
    public void syyp() throws Exception {
        new GameUtil(mFairy) {
            ControlSplit yp1 = new ControlSplit();
            ControlSplit yp2 = new ControlSplit();
            ControlSplit yp3 = new ControlSplit();

            public void create() throws Exception {
                yp1.count = 0;
                yp2.count = 0;
                yp3.count = 0;
                if (!AtFairyConfig.getOption("syypcount").equals("")) {
                    yp1 = strSplit(AtFairyConfig.getOption("syypcount"));
                }
                if (!AtFairyConfig.getOption("syypcount1").equals("")) {
                    yp2 = strSplit(AtFairyConfig.getOption("syypcount1"));
                }
                if (!AtFairyConfig.getOption("syypcount2").equals("")) {
                    yp3 = strSplit(AtFairyConfig.getOption("syypcount2"));
                }
                result1 = new FindResult();
                result2 = new FindResult();
                result3 = new FindResult();
                result1.sim = 0.1f;
                result2.sim = 0.1f;
                result3.sim = 0.1f;
            }

            @Override
            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(6, 0)) return;
                result = mFairy.findPic("bag.png");
                mFairy.onTap(0.8f, result, "包裹", Sleep);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面中"));
                    mFairy.onTap(0.8f, result, 1053, 611, 1083, 625, "整理", Sleep);
                    setTaskName(2);
                    return;
                }
            }

            boolean yp11 = true, yp22 = true, yp33 = true;

            public void content_2() throws Exception {
                if (overtime(6, 3)) return;
                if (timekeep(0, 300000, "清包超时")) {
                    LtLog.e(mFairy.getLineInfo("清包超时"));
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("bag.png");
                mFairy.onTap(0.8f, result, "包裹", Sleep);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面中"));
                    for (int i = 0; i < 1; i++) {
                        if (yp11) {
                            if (yp1.count != 0) {
                                result1 = mFairy.findPic(761, 177, 1147, 570, new String[]{"niuroumian.png"});
                                mFairy.onTap(0.9f, result1, "牛肉面", 2500);
                                if (result1.sim > 0.9f) {
                                    err = 0;
                                    yp11 = false;
                                }
                            } else {
                                err = 0;
                                yp11 = false;
                            }
                            break;
                        }
                        if (yp22) {
                            if (yp2.count != 0) {
                                result2 = mFairy.findPic(761, 177, 1147, 570, new String[]{"guojiu.png"});
                                mFairy.onTap(0.9f, result2, "果酒", 2500);
                                if (result2.sim > 0.9f) {
                                    err = 0;
                                    yp22 = false;
                                }
                            } else {
                                err = 0;
                                yp22 = false;
                            }
                            break;
                        }
                        if (yp33) {
                            if (yp3.count != 0) {
                                result3 = mFairy.findPic(761, 177, 1147, 570, new String[]{"hongshaorou.png"});
                                mFairy.onTap(0.9f, result3, "红烧肉", 2500);
                                if (result3.sim > 0.9f) {
                                    err = 0;
                                    yp33 = false;
                                }
                            } else {
                                err = 0;
                                yp33 = false;
                            }
                            break;
                        }
                    }


                    result = mFairy.findPic(224, 189, 736, 652, "gengduo.png");
                    mFairy.onTap(0.8f, result, "更多", Sleep);

                    result = mFairy.findPic(224, 189, 736, 652, "piliangsy.png");
                    mFairy.onTap(0.8f, result, "批量使用", Sleep);

                    result = mFairy.findPic("piliangsy1.png");
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 100; i++) {
                            mFairy.tap(550, 333);
                        }
                        if (result1.sim > 0.9f) {
                            for (int i = 0; i < yp1.count; i++) {
                                mFairy.tap(824, 336);
                            }
                            result1.sim = 0.1f;
                        }
                        if (result2.sim > 0.9f) {
                            for (int i = 0; i < yp2.count; i++) {
                                mFairy.tap(824, 336);
                            }
                            result2.sim = 0.1f;
                        }
                        if (result3.sim > 0.9f) {
                            for (int i = 0; i < yp3.count; i++) {
                                mFairy.tap(824, 336);
                            }
                            result3.sim = 0.1f;
                        }
                        mFairy.onTap(0.8f, result, "批量使用", 1000);
                        if (man() == 1) {
                            setTaskEnd();
                            return;
                        }
                    }
                    if (yp11 == false && yp22 == false && yp33 == false) {
                        setTaskName(3);
                        return;
                    }
                    mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5, 6}, 0, 918, 538, 917, 225, 500, 1500, 2);
                }
            }

            public void content_3() throws Exception {
                close(0);
                setTaskEnd();
                return;
            }
        }.taskContent(mFairy, "使用药品中");
    }

    //队长
    public int duizhang() throws Exception {
        result = mFairy.findPic("duiwulan.png");
        mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

        result = mFairy.findPic("sqlb.png");
        mFairy.onTap(0.8f, result, 775, 612, 850, 633, "申请列表清空", Sleep);
        mFairy.onTap(0.8f, result, 430, 606, 496, 633, "关闭申请列表", Sleep);

        result = mFairy.findPic("yaoqingliebiao.png");
        mFairy.onTap(0.8f, result, 775, 612, 850, 633, "邀请列表清空", Sleep);
        mFairy.onTap(0.8f, result, 438, 613, 477, 625, "邀请列表关闭", Sleep);

        result = mFairy.findPic("duiwulan1.png");
        mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

        result = mFairy.findPic("chuanjianduiwu.png");
        mFairy.onTap(0.8f, result, "没有队伍创建队伍", 3000);

        result = mFairy.findPic("yijianhanhua.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("是队长去设置目标"));
            return 1;
        }
        result = mFairy.findPic(new String[]{"gensuiduiwu.png", "qxgs.png"});
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("是队员退队"));
            retire();
        }
        return 0;
    }

    //队员
    public int duiyuan() throws Exception {
        result = mFairy.findPic("duiwulan.png");
        mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

        result = mFairy.findPic("sqlb.png");
        mFairy.onTap(0.8f, result, 775, 612, 850, 633, "申请列表清空", Sleep);
        mFairy.onTap(0.8f, result, 430, 606, 496, 633, "关闭申请列表", Sleep);


        result = mFairy.findPic("yaoqingliebiao.png");
        mFairy.onTap(0.8f, result, 775, 612, 850, 633, "邀请列表清空", Sleep);
        mFairy.onTap(0.8f, result, 438, 613, 477, 625, "邀请列表关闭", Sleep);

        result = mFairy.findPic("duiwulan1.png");
        mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

        result = mFairy.findPic("chuanjianduiwu.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("创建队伍是队员"));
            return 1;
        }
        result = mFairy.findPic("yijianhanhua.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("是队长退队"));
            retire();
        }
        result = mFairy.findPic(new String[]{"gensuiduiwu.png", "qxgs.png"});
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("是队员"));
            return 1;
        }
        return 0;
    }

    //召唤结拜
    public void zhjb() throws Exception {
        new GameUtil(mFairy) {

            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(5, 0)) return;
                result = mFairy.findPic("suojinduihuakuang.png");
                mFairy.onTap(0.8f, result, "缩进对话框", Sleep);

                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 507, 591, 533, 600, "点击家谱", Sleep);


                result = mFairy.findPic(527,32,726,114,"jiapuinface.png");
                mFairy.onTap(0.8f, result, 1211, 328, 1229, 352, "家谱界面结拜", Sleep);


                result = mFairy.findPic(116,585,557,697,"jbzhaoji.png");
                mFairy.onTap(0.8f, result, "召集成员", Sleep);
                if (result.sim > 0.8f) {
                    close(0);
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy, "召唤结拜中");
    }

    //开启自动
    public void zidong() throws Exception {
        result = mFairy.findPic("qiehuan.png");
        mFairy.onTap(0.8f, result, "切换", Sleep);

        result = mFairy.findPic("hand.png");
        mFairy.onTap(0.8f, result, "开启自动", Sleep);
    }

    //换线
    public void huanxian() throws Exception {
        close(1);
        result = mFairy.findPic(1125,0,1278,37,new String[]{"hx1.png","hx2.png"});
        mFairy.onTap(0.8f, result, "换线", Sleep);

        result = mFairy.findPic(259,112,1022,571,new String[]{"fluent.png"});
        if (result.sim > 0.8f){
            mFairy.onTap(0.8f, result, "流畅", Sleep);
        }else{
            result = mFairy.findPic(259,112,1022,571,new String[]{"fluent.png"});
            mFairy.onTap(0.8f, result, "分线", Sleep);
        }
    }

    public boolean qh = false;

    //切换角色
    public boolean qiehuanjuese(final int index) throws Exception {

        new GameUtil(mFairy) {

            public void content_0() throws Exception {
                qh = false;
                close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(5, 0)) return;
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 32, 46, 55, 65, "打开角色", Sleep);

                result = mFairy.findPic("jueseinface.png");
                mFairy.onTap(0.8f, result, 1209, 582, 1221, 605, "切换到设置", Sleep);

                result = mFairy.findPic(381,505,658,605,"change role.png");
                mFairy.onTap(0.8f, result, "更换角色", 1000);
                mFairy.onTap(0.8f, result, 747, 444, 776, 464, "更换角色-确定", 1000);

                FindResult login_game = mFairy.findPic("joingame.png");
                if (login_game.sim > 0.8f) {
                    err = 0;
                    switch (index) {
                        case 1:
                            result = mFairy.findPic(46, 94, 105, 153, "qhjs add.png");
                            if (result.sim > 0.8f) {
                                qh = true;
                            } else {
                                mFairy.onTap(64, 116, 90, 138, "选择角色1", 1000);
                            }

                            TaskMain.qh1 = 0;
                            break;
                        case 2:
                            result = mFairy.findPic(46, 199, 108, 254, "qhjs add.png");
                            if (result.sim > 0.8f) {
                                qh = true;
                            } else {
                                mFairy.onTap(65, 215, 92, 240, "选择角色2", 1000);
                            }
                            TaskMain.qh2 = 0;
                            break;
                        case 3:

                            result = mFairy.findPic(46, 300, 105, 357, "qhjs add.png");
                            if (result.sim > 0.8f) {
                                qh = true;
                            } else {
                                mFairy.onTap(62, 315, 91, 344, "选择角色3", 1000);
                            }
                            TaskMain.qh3 = 0;

                            break;
                        case 4:
                            result = mFairy.findPic(48, 402, 105, 458, "qhjs add.png");
                            if (result.sim > 0.8f) {
                                qh = true;
                            } else {
                                mFairy.onTap(59, 412, 91, 446, "选择角色4", 1000);
                            }
                            TaskMain.qh4 = 0;
                            break;
                        case 5:
                            result = mFairy.findPic(46, 506, 106, 555, "qhjs add.png");
                            if (result.sim > 0.8f) {
                                qh = true;
                            } else {
                                mFairy.onTap(59, 516, 93, 548, "选择角色5", 1000);
                            }
                            TaskMain.qh5 = 0;
                            break;
                    }

                    mFairy.onTap(0.8f, login_game, "进入游戏", Sleep);
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "切换角色" + index);

        return qh;
    }

    public void newqiehuanjuese() throws Exception {
        new GameUtil(mFairy) {

            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(5, 0)) return;

                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 32, 46, 55, 65, "打开角色", Sleep);

                result = mFairy.findPic("jueseinface.png");
                mFairy.onTap(0.8f, result, 1209, 582, 1221, 605, "切换到设置", Sleep);

                result = mFairy.findPic("change role.png");
                mFairy.onTap(0.8f, result, "更换角色", 1000);
                mFairy.onTap(0.8f, result, 747, 444, 776, 464, "更换角色-确定", 1000);

                FindResult login_game = mFairy.findPic("joingame.png");
                if (login_game.sim > 0.8f) {
                    err = 0;

                    if (mFairy.findPic(9, 170, 141, 287, "qh1.png").sim > 0.8f) {

                        mFairy.onTap(61, 112, 84, 134, "切换", 1000);
                        mFairy.onTap(61, 112, 84, 134, "切换", 1000);

                    } else if (mFairy.findPic(8, 270, 139, 380, "qh1.png").sim > 0.8f) {

                        mFairy.onTap(61, 213, 83, 234, "切换", 1000);
                        mFairy.onTap(61, 213, 83, 234, "切换", 1000);

                    } else if (mFairy.findPic(9, 387, 154, 479, "qh1.png").sim > 0.8f) {

                        mFairy.onTap(69, 316, 84, 333, "切换", 1000);
                        mFairy.onTap(69, 316, 84, 333, "切换", 1000);

                    } else if (mFairy.findPic(11, 485, 151, 577, "qh1.png").sim > 0.8f) {

                        mFairy.onTap(66, 414, 82, 437, "切换", 1000);
                        mFairy.onTap(66, 414, 82, 437, "切换", 1000);

                    } else {
                        mFairy.onTap(63, 524, 83, 540, "切换", 1000);
                        mFairy.onTap(63, 524, 83, 540, "切换", 1000);
                    }

                    TaskMain.xhqh++;

                    mFairy.onTap(0.8f, login_game, "进入游戏", Sleep);
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy, "切换角色 切换次数：" + (TaskMain.xhqh + 1));
    }

}
