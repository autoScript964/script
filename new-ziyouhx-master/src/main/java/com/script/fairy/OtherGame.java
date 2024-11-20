package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

/**
 * Created by Administrator on 2019/11/5 0005.
 */

public class OtherGame extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    GameUtil gameUtil;

    public OtherGame(AtFairyImpl ATFairy) throws Exception {
        mFairy = ATFairy;
        gameUtil = new GameUtil(mFairy);
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

    //家园收菜
    public void jysc() throws Exception {
        new OtherGame(mFairy) {
            int zw = 0, zw1 = 0, zw2 = 0, zw3 = 0, zw4 = 0, zw5 = 0, zw6 = 0, mp = 1;

            @Override
            public void create() throws Exception {
                if (!AtFairyConfig.getOption("zw").equals("")) {
                    zw1 = Integer.parseInt(AtFairyConfig.getOption("zw"));
                }
                if (!AtFairyConfig.getOption("zw1").equals("")) {
                    zw2 = Integer.parseInt(AtFairyConfig.getOption("zw1"));
                }
                if (!AtFairyConfig.getOption("zw2").equals("")) {
                    zw3 = Integer.parseInt(AtFairyConfig.getOption("zw2"));
                }
                if (!AtFairyConfig.getOption("zw3").equals("")) {
                    zw4 = Integer.parseInt(AtFairyConfig.getOption("zw3"));
                }
                if (!AtFairyConfig.getOption("zw4").equals("")) {
                    zw5 = Integer.parseInt(AtFairyConfig.getOption("zw4"));
                }
                if (!AtFairyConfig.getOption("zw5").equals("")) {
                    zw6 = Integer.parseInt(AtFairyConfig.getOption("zw5"));
                }
                if (zw1 == 0 && zw2 == 0 && zw3 == 0 && zw4 == 0 && zw5 == 0 && zw6 == 0) {
                    LtLog.e(mFairy.getLineInfo("没有选择苗圃结束"));
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(5, 2)) return;
                result = mFairy.findPic("qiehuanlan.png");
                mFairy.onTap(0.8f, result, "切换栏", Sleep);

                result = mFairy.findPic(866, 311, 1279, 712, "jiayuan.png");
                mFairy.onTap(0.8f, result, "家园", Sleep);


                result = mFairy.findPic("Home interface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("家园界面"));
                    result = mFairy.findPic(95, 340, 515, 625, "Collect vegetables.png");
                    result1 = mFairy.findPic(95, 340, 515, 625, "No time.png");
                    if (result.sim < 0.8f && result1.sim < 0.8f) {
                        LtLog.e(mFairy.getLineInfo("菜没有熟"));
                        setTaskEnd();
                        return;
                    } else {
                        gameUtil.goCity("家园");
                        setTaskName(2);
                        return;
                    }
                }
            }

            public void content_2() throws Exception {
                if (overtime(10, 99)) return;
                result = mFairy.findPic("Renovation.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "家园地图", Sleep);

                result = mFairy.findPic("ctiyinface.png");
                mFairy.onTap(0.8f, result, "切换到地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    if (mp == 1 && zw1 != 0) {
                        mFairy.onTap(0.8f, result, 648, 448, 649, 449, "第1个苗圃", Sleep);
                    } else if (mp == 2 && zw2 != 0) {
                        mFairy.onTap(0.8f, result, 719, 475, 720, 476, "第2个苗圃", Sleep);
                    } else if (mp == 3 && zw3 != 0) {
                        mFairy.onTap(0.8f, result, 793, 511, 794, 512, "第3个苗圃", Sleep);
                    } else if (mp == 4 && zw4 != 0) {
                        mFairy.onTap(0.8f, result, 544, 476, 545, 477, "第4个苗圃", Sleep);
                    } else if (mp == 5 && zw5 != 0) {
                        mFairy.onTap(0.8f, result, 624, 513, 625, 514, "第5个苗圃", Sleep);
                    } else if (mp == 6 && zw6 != 0) {
                        mFairy.onTap(0.8f, result, 698, 553, 699, 554, "第6个苗圃", Sleep);
                    } else if (mp >= 7) {
                        LtLog.e(mFairy.getLineInfo("种植完毕"));
                        setTaskEnd();
                        return;
                    }
                    gameUtil.close(1);
                    Thread.sleep(7000);
                    mFairy.onTap(649, 442, 650, 443, "点击地面", Sleep);

                    result = mFairy.findPic(198, 33, 1192, 562, "remove.png");
                    mFairy.onTap(0.8f, result, 649, 442, 650, 443, "移除", Sleep);

                    result = mFairy.findPic(198, 33, 1192, 562, "Harvest.png");
                    mFairy.onTap(0.8f, result, "收获", 2000);
                    if (result.sim > 0.8f) {
                        if (gameUtil.man() == 1) {
                            setTaskEnd();
                            return;
                        }
                    }

                    result = mFairy.findPic("shou1.png");
                    mFairy.onTap(0.8f,result,745,445,765,456,"超出上限",1000);

                    result = mFairy.findPic(198, 33, 1192, 562, "Plant1.png");
                    mFairy.onTap(0.8f, result, "种植地上", Sleep);

                    result = mFairy.findPic("Plant.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("种植界面界面"));
                        if (mp == 1) {
                            zw = zw1;
                        } else if (mp == 2) {
                            zw = zw2;
                        } else if (mp == 3) {
                            zw = zw3;
                        } else if (mp == 4) {
                            zw = zw4;
                        } else if (mp == 5) {
                            zw = zw5;
                        } else if (mp == 6) {
                            zw = zw6;
                        }
                        if (zw <= 10) {
                            mFairy.onTap(259, 131, 260, 132, "蔬果", Sleep);
                        } else if (zw > 10 && zw <= 20) {
                            mFairy.onTap(379, 130, 380, 131, "树木", Sleep);
                            zw = zw - 10;
                        } else if (zw > 20 && zw <= 30) {
                            mFairy.onTap(499, 133, 500, 134, "花卉", Sleep);
                            zw = zw - 20;
                        }
                        if (zw <= 3) {
                            mFairy.onTap(0.8f, result, 162 + (85 * zw), 197, 163 + (85 * zw), 198, "粮食", Sleep);
                        }
                        if (zw > 3 && zw <= 6) {
                            mFairy.onTap(0.8f, result, 162 + (85 * (zw - 3)), 197 + 105, 163 + (85 * (zw - 3)), 198 + 105, "粮食", Sleep);
                        }
                        if (zw > 6 && zw <= 9) {
                            mFairy.onTap(0.8f, result, 162 + (85 * (zw - 6)), 197 + 105 + 105, 163 + (85 * (zw - 6)), 198 + 105 + 105, "粮食", Sleep);
                        }
                        if (zw > 9 && zw <= 10) {
                            mFairy.onTap(0.8f, result, 162 + (85 * (zw - 9)), 197 + 105 + 105 + 105, 163 + (85 * (zw - 9)), 198 + 105 + 105 + 105, "粮食", Sleep);
                        }
                        mFairy.onTap(0.8f, result, "种植", 2000);
                    }
                    mp++;
                }
            }

        }.taskContent(mFairy, "家园收菜中");

    }

    //家园挖矿
    public void jywk() throws Exception {
        new OtherGame(mFairy) {
            int mp = 1;

            @Override
            public void content_0() throws Exception {
                gameUtil.goCity("家园");
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(10, 0)) return;

                result = mFairy.findPic("Renovation.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (mp == 1) {
                        gameUtil.coordinate("家园", 51, 52);
                    } else if (mp == 2) {
                        gameUtil.coordinate("家园", 68, 54);
                    } else if (mp == 3) {
                        gameUtil.coordinate("家园", 82, 59);
                    } else if (mp == 4) {
                        gameUtil.coordinate("家园", 79, 71);
                    } else if (mp == 5) {
                        gameUtil.coordinate("家园", 64, 63);
                    } else if (mp == 6) {
                        gameUtil.coordinate("家园", 49, 60);
                    } else if (mp == 7) {
                        gameUtil.coordinate("家园", 31, 75);
                    } else if (mp == 8) {
                        gameUtil.coordinate("家园", 35, 69);
                    } else if (mp == 9) {
                        gameUtil.coordinate("家园", 21, 72);
                    } else if (mp == 10) {
                        gameUtil.coordinate("家园", 29, 62);
                    } else if (mp == 11) {
                        LtLog.e(mFairy.getLineInfo("家园挖矿完毕"));
                        setTaskEnd();
                        return;
                    }

                    for (int i = 0; i < 10; i++) {
                        mFairy.onTap(0.8f, result, 574,348,688,461, "血精石", 100);
                    }
                    result = mFairy.findPic(433,389,838,522,"jlbz.png");
                    if ((gameUtil.man() == 1)||result.sim > 0.8f) {
                        setTaskEnd();
                        return;
                    } else {
                        Thread.sleep(5000);
                    }
                    mp++;
                }

            }

        }.taskContent(mFairy, "家园挖矿中");

    }


    public int em = 0;

    //恶魔令
    public void eml() throws Exception {
        new OtherGame(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("duiwulan.png");
                mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);
                result = mFairy.findPic(45,301,321,372,"dwkz.png");
                result1 = mFairy.findPic(45,301,321,372,"dyxt.png");
                if (result.sim > 0.8f && result1.sim > 0.8f) {
                    LtLog.e("快捷出发！");
                    setTaskName(4);
                    return;
                }
                if (gameUtil.duizhang() == 1) {
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(7, 0)) {
                    return;
                }
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("mbeml.png");
                    if (true) {//result.sim > 0.8f 目标恶魔令
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("目标正确"));
                            result = mFairy.findPic("dzqxpp.png");
                            mFairy.onTap(0.8f, result, "先取消一下自动匹配", Sleep);

                            result = mFairy.findPic("dzzdpp.png");
                            mFairy.onTap(0.8f, result, "开启自动匹配", Sleep);

                            setTaskName(3);
                            return;
                        } else {
                            result = mFairy.findPic(79, 107, 270, 599, "eml.png");
                            mFairy.onTap(0.8f, result, "找到恶魔令", Sleep);
                            mFairy.onTap(0.8f, result, 162, 615, 191, 628, "设为目标", Sleep);
                            if (result.sim < 0.8f) {
                                mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5}, 0, 171, 541, 173, 160, 200, 1500, 2);
                            }
                        }
                    }
                }
            }
            public void content_3() throws Exception {
                if (overtime(8, 0)) {
                    return;
                }
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    int mannum = 0;
                    result = mFairy.findPic(461, 485, 645, 520, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(648, 484, 828, 518, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(832, 486, 1010, 521, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }
                    result = mFairy.findPic(1012, 485, 1190, 520, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }
                    if (mannum > Integer.parseInt(AtFairyConfig.getOption("kaidui"))) {
                        gameUtil.callToFollow();
                        LtLog.e(mFairy.getLineInfo("人满了出发"));
                        setTaskName(4);
                        return;
                    }
                    gameUtil.shenqing();
                    if (timekeep(1, 120000, "2分钟招募一下")) {
                        LtLog.e(mFairy.getLineInfo("--------2分钟招募一下"));
                        gameUtil.yjhh();
                    }
                    if (timekeep(0, 600000, "恶魔令超过10分钟没组到人")) {
                        LtLog.e(mFairy.getLineInfo("--------超过10分钟没组到人"));
                        setTaskName(0);
                        return;
                    }
                }
            }

            int noUser = 0;

            public void content_4() throws Exception {
                if (overtime(8, 5)) {
                    gameUtil.close(0);
                    return;
                }
                result = mFairy.findPic(1126,2,1273,29, "emgc.png");
                if (result.sim > 0.8f) {
                    setTaskName(6);
                    return;
                }
                result = mFairy.findPic("bag.png");
                mFairy.onTap(0.8f, result, "包裹", Sleep);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面中"));

                    result = mFairy.findPic(761, 177, 1147, 570, "emldaoju.png");
                    result = mFairy.findPic(761, 177, 1147, 570, new String[]{"emldaoju.png"});
                    mFairy.onTap(0.9f, result, "恶魔令", 3000);

                    result = mFairy.findPic(1008,232,1171,382,"shouli1.png");
                    mFairy.onTap(0.8f, result, "关闭物品弹框", 1000);

                    result = mFairy.findPic(224, 189, 736, 652, "sywp.png");
                    mFairy.onTap(0.8f, result, "物品使用", 1000);
                    if (result.sim > 0.8f) {
                        if (gameUtil.man() == 1) {
                            setTaskEnd();
                        } else {
                            noUser = 0;
                            setTaskName(6);
                        }
                        return;
                    } else{
                        mFairy.taskSlid(err, new int[]{2, 3, 4, 5, 6}, 0, 918, 538, 917, 225, 500, 1500, 2);
                    }
                    }
                    mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5, 6}, 0, 918, 538, 917, 225, 500, 1500, 2);
                }

            public void content_5() throws Exception {
                if (overtime(8, 4)) {
                    noUser++;
                    if (noUser > 2) {
                        setTaskEnd();
                        return;
                    }
                    gameUtil.close(0);
                    return;
                }
                result = mFairy.findPic("bag.png");
                mFairy.onTap(0.8f, result, "包裹", Sleep);

                result = mFairy.findPic("baginface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面中"));

                    result = mFairy.findPic(1008,232,1171,382,"shouli1.png");
                    mFairy.onTap(0.8f, result, "关闭物品弹框", 1000);

                    result = mFairy.findPic(761, 177, 1147, 570, "emldaoju.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.9f, result, "恶魔令", 3000);
                    }else{
                        result = mFairy.findPic(761, 177, 1147, 570, new String[]{"emldaoju1.png"});
                        mFairy.onTap(0.9f, result, "恶魔令道具包裹", Sleep);
                    }
                    result = mFairy.findPic(761, 177, 1147, 570, new String[]{"emldaoju1.png"});
                    mFairy.onTap(0.9f, result, "恶魔令道具包裹", Sleep);

                    result = mFairy.findPic(224, 189, 736, 652, "sywp.png");
                    mFairy.onTap(0.8f, result, "物品使用", 1000);
                    if (result.sim > 0.8f) {
                        if (gameUtil.man() == 1) {
                            setTaskEnd();
                            return;
                        } else {
                            gameUtil.close(0);
                            setTaskName(4);
                            return;
                        }
                    }else{
                        mFairy.taskSlid(err, new int[]{2, 3, 4, 5, 6}, 0, 918, 538, 917, 225, 500, 2500, 2);
                    }
                    }
                    mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5, 6}, 0, 918, 538, 917, 225, 500, 2500, 2);
                }

            public void content_6() throws Exception {
                gameUtil.fuhuo();
                result = mFairy.findPic(886,76,1121,163, "goaway.png");
                result = mFairy.findPic(1032, 103, 1114, 160, "goaway.png");
                if (result.sim > 0.8f) {
                    err=0;
                    result = mFairy.findPic(895, 462, 1097, 548, "used.png");
                    if (result.sim > 0.8f) {
                        gameUtil.zidong();
                    } else {
                        setTaskName(4);
                        return;
                    }
                } else {
                    result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                    if (result.sim > 0.8f) {

                        result = mFairy.findPic(892, 292, 1099, 547, "shouli.png");
                        if (result.sim > 0.75f) {
                            result = mFairy.findPic(1008,232,1171,382,"shouli1.png");
                            mFairy.onTap(0.8f, result, "关闭手礼", 1000);
                        } else {
                            result = mFairy.findPic(895, 462, 1097, 548, "used.png");
                            if(result.sim>0.8f) {
                                err=0;
                                mFairy.onTap(0.8f, result, "使用恶魔令", 500);
                                em = 0;
                            }

                            if (picCount(0.8f, result, "多次使用恶魔令") > 15) {

                                result = mFairy.findPic(449, 436, 814, 578, "julitaiyuan.png");
                                if (result.sim > 0.8f) {
                                    LtLog.e(mFairy.getLineInfo("距离太远"));
                                    gameUtil.callToFollow();
                                }

                                result = mFairy.findPic(508, 469, 816, 535, "eml_csbz.png");
                                if (result.sim > 0.8f) {
                                    LtLog.e(mFairy.getLineInfo("使用恶魔令没有次数了"));
                                    setTaskEnd();
                                    return;
                                }

                                result = mFairy.findPic(449, 436, 814, 578, "rsbuzu.png");
                                if (result.sim > 0.8f) {
                                    LtLog.e(mFairy.getLineInfo("--------人数不足"));
                                    setTaskName(0);
                                    return;
                                }

                            } else {
                                if (result.sim < 0.8f) {
                                    em++;
                                    if(em>2) {
                                        setTaskName(0);
                                    }
                                    Thread.sleep(1000);
                                    return;
                                } else {
                                    Thread.sleep(7000);
                                }
                            }
                        }
                    }else{
                        gameUtil.close(0);
                    }
                }

                if (overtime(8, 4)) {
                    gameUtil.close(0);
                    return;
                }
            }
        }.taskContent(mFairy, "恶魔令中");
    }

    //放逐之地
    public void fzzd() throws Exception {
        new OtherGame(mFairy) {

            ControlSplit optime1 = null, optime2 = null,optime5 = null;

            public void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic(491, 133, 784, 539, "fuhuo1.png");
                mFairy.onTap(0.8f, result, "默认复活", Sleep);
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("ycfh").equals("1")) {
                        Thread.sleep(120000);
                    }
                }
            }

            @Override
            public void content_0() throws Exception {
                LtLog.e("开头");
                if (!AtFairyConfig.getOption("fzoptime1").equals("")) {
                    optime1 = strSplit(AtFairyConfig.getOption("fzoptime1"));
                }

                if (!AtFairyConfig.getOption("fzoptime2").equals("")) {
                    optime2 = strSplit(AtFairyConfig.getOption("fzoptime2"));
                }

                if (!AtFairyConfig.getOption("fzoptime5").equals("")) {
                    optime5 = strSplit(AtFairyConfig.getOption("fzoptime5"));
                }

                String[] arr = AtFairyConfig.getOption("xystr").split(",");
                gmx = Integer.parseInt(arr[0]);
                gmy = Integer.parseInt(arr[1]);
                gameUtil.close(1);
                result = mFairy.findPic(1144, 5, 1252, 24, "fz_map.png");
                if (result.sim > 0.7) {
                    LtLog.e(mFairy.getLineInfo(0.7f, result, "在放逐之地"));
                    setTaskName(2);
                    return;
                } else {
                    gameUtil.goCity("蓬莱仙境");
                    gameUtil.callToFollow();
                    setTaskName(1);
                    return;
                }
            }

            public void content_1() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic("ctiyinface.png");
                mFairy.onTap(0.8f, result, "切换到地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
                mFairy.onTap(0.8f, result, 491, 387, 492, 388, "点击传送使者", Sleep);
                if (result.sim > 0.8f) {
                    gameUtil.close(1);
                    Thread.sleep(6000);
                    mFairy.onTap(650, 317, 651, 318, "点击npc", 3000);
                    result = mFairy.findPic(1017, 175, 1261, 515, "fz_dtxz.png");
                    mFairy.onTap(0.8f, result, "进入放逐之地", 10000);
                    if (result.sim > 0.8f) {
                        setTaskName(2);
                        return;
                    }
                }
            }

            String strmap = "";

            public void content_2() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic("fz_sz.png");
                mFairy.onTap(0.8f, result, 678, 392, 679, 393, "放逐使者", Sleep);
                if (result.sim > 0.8f) {
                    gameUtil.callToFollow();
                    Thread.sleep(6000);
                    mFairy.onTap(665, 288, 666, 289, "点传送使者", 3000);
                    result = mFairy.findPic(1017, 175, 1261, 515, "fz_dtxz.png");
                    if (result.sim > 0.8f) {
                        if (AtFairyConfig.getOption("map").equals("1")) {
                            mFairy.onTap(0.8f, result, 1155, 249, 1156, 260, "金戈山", 7000);
                            strmap = "fzmap1.png";
                        }
                        if (AtFairyConfig.getOption("map").equals("2")) {
                            mFairy.onTap(0.8f, result, 1141, 305, 1142, 306, "翡翠林", 7000);
                            strmap = "fzmap2.png";
                        }
                        if (AtFairyConfig.getOption("map").equals("3")) {
                            mFairy.onTap(0.8f, result, 1142, 364, 1143, 365, "雨滴糊", 7000);
                            strmap = "fzmap3.png";
                        }
                        if (AtFairyConfig.getOption("map").equals("4")) {
                            mFairy.onTap(0.8f, result, 1137, 420, 1138, 421, "炎上谷", 7000);
                            strmap = "fzmap4.png";
                        }
                        if (AtFairyConfig.getOption("map").equals("5")) {
                            mFairy.onTap(0.8f, result, 1136, 476, 1137, 477, "绅士原", 7000);
                            strmap = "fzmap5.png";
                        }
                        setTaskName(3);
                        return;
                    }
                }
            }

            int map = 0, gmx = 0, gmy = 0;
            double x = 0, y = 0;

            public void content_3() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic("fz_sz.png");
                if (result.sim > 0.8f) {
                    x = gmx * 7.7988 + gmy * 0.0453 + 141.0126;
                    y = gmx * -0.0042 + gmy * 7.8759 + 58.1878;
                    LtLog.e(mFairy.getLineInfo("x=" + (int) x + ",y=" + (int) y));
                    mFairy.tap((int) x, (int) y);
                    Thread.sleep(15000);
                    gameUtil.cancelFollowing();
                    setTaskName(4);
                    return;
                }
            }

            public void content_4() throws Exception {
                if (overtime(8, 0)) return;
                if (gameUtil.duizhang() == 1) {
                    setTaskName(5);
                    return;
                }
            }

            public void content_5() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("mbfzzd.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("目标正确"));
                        result = mFairy.findPic("dzqxpp.png");
                        mFairy.onTap(0.8f, result, "先取消一下自动匹配", Sleep);
                        result = mFairy.findPic("dzzdpp.png");
                        mFairy.onTap(0.8f, result, "开启自动匹配", Sleep);
                        gameUtil.close(0);
                        setTaskName(6);
                        return;
                    } else {
                        result = mFairy.findPic(79, 107, 270, 599, "dwfzzd.png");
                        mFairy.onTap(0.8f, result, "找到目标放逐之地", Sleep);
                        mFairy.onTap(0.8f, result, 162, 615, 191, 628, "设为目标", Sleep);

                    }
                }
            }

            public void content_6() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(1144, 5, 1252, 24, strmap);
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo(0.8f, result, "在目标地图"));
                    err = 0;
                }

                if (optime1 != null && optime1.choice == 1 && timekeep(1, optime1.timeMillis, "野外挂机召唤结拜")) {
                    gameUtil.zhjb();
                    if (optime5 != null && optime5.choice == 1) {
                        Thread.sleep(optime5.timeMillis);
                        gameUtil.cancelFollowing();
                    }
                }
                if (optime2 != null && optime2.choice == 1 && timekeep(1, optime2.timeMillis, "野外挂机召唤跟随")) {
                    gameUtil.callToFollow();
                    if (optime5 != null && optime5.choice == 1) {
                        Thread.sleep(optime5.timeMillis);
                        gameUtil.cancelFollowing();
                    }
                }


                gameUtil.zidong();
            }
        }.taskContent(mFairy, "放逐之地中");

    }

}
