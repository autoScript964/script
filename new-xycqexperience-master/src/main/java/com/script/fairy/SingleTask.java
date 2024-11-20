package com.script.fairy;


import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2019/3/25 0025.
 */

public class SingleTask extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    FindResult result2;
    FindResult result3;
    GameUtil gameUtil;

    public SingleTask(AtFairyImpl ATFairy) throws Exception {
        mFairy = ATFairy;
        gameUtil = new GameUtil(mFairy);
    }

    public void novice() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(1111, 3, 1274, 41, new String[]{"tiaoguo2.png", "tiaoguo3.png"});
                mFairy.onTap(0.8f, result, "跳过", 1000);
            }

            @Override
            public void content_0() throws Exception {
                result1 = mFairy.findPic(5, 4, 202, 62, "rxflhd.png");
                result = mFairy.findPic("tuzhi.png");
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("主线结束"));
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(956, 6, 1275, 149, "dtqh.png");
                mFairy.onTap(0.9f, result, "地图切换", Sleep);

                result = mFairy.findPic(4, 5, 200, 72, "zdpt1.png");
                mFairy.onTap(0.9f, result, 806, 257, 813, 266, "退出组队", Sleep);

                long dazeTime = mFairy.mMatTime(1109, 118, 52, 10, 0.9f);
                if (dazeTime > 3) {
                    mFairy.initMatTime();
                    result = mFairy.findPic(0, 255, 39, 462, "team.png");
                    mFairy.onTap(0.95f, result, 13, 233, 27, 257, "任务栏暗点切换到任务栏", Sleep);

                    result = mFairy.findPic(0, 166, 34, 386, "task.png");
                    mFairy.onTap(0.9f, result, 84, 195, 108, 209, "任务栏切换到普通", Sleep);

                    result = mFairy.findPic(44, 178, 254, 318, "main.png");
                    mFairy.onTap(0.7f, result, "左侧主线", Sleep);
                    if (result.sim < 0.8f) {
                        result = mFairy.findPic(44, 178, 254, 318, "zhi.png");
                        mFairy.onTap(0.7f, result, "左侧支线", Sleep);
                    }
                    if (picCountS(0.7f, result, "左侧主线") > 30) {
                        LtLog.e(mFairy.getLineInfo("主线结束"));
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(43, 215, 156, 243, "zbmj.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(813, 285, 958, 331, "duihua4.png");
                    if (result.sim > 0.8f) {
                        mFairy.ranSwipe(150, 550, 224, 590, 1500, (long) 500, 1);
                    } else {
                        result = mFairy.findPic(817, 264, 931, 347, "tansuo.png");
                        mFairy.onTap(0.7f, result, "探索", Sleep);
                    }
                }

                result = mFairy.findPic(43, 215, 156, 243, "zbqh.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.7f, result, 1245, 238, 1249, 244, "菜单", Sleep);
                    result = mFairy.findPic(911, 173, 1006, 267, "zhuangbei1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.7f, result, "装备", Sleep);
                        mFairy.onTap(0.7f, result, 874, 656, 884, 660, "装备", Sleep);
                        mFairy.onTap(0.7f, result, 1245, 34, 1253, 43, "装备", Sleep);
                    }
                }

                result = mFairy.findPic(46, 193, 164, 225, "kp.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.7f, result, 1245, 238, 1249, 244, "菜单", Sleep);
                    result = mFairy.findPic(1004, 264, 1103, 354, "shj1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.7f, result, "山海经", Sleep);
                        mFairy.onTap(0.7f, result, 1224, 619, 1228, 626, "探索", Sleep);
                        mFairy.onTap(0.7f, result, 1224, 619, 1228, 626, "探索", Sleep);
                        mFairy.onTap(0.7f, result, 1224, 619, 1228, 626, "探索", Sleep);
                        mFairy.onTap(0.7f, result, 1181, 386, 1191, 395, "探索一次", Sleep);
                        mFairy.onTap(0.7f, result, 1181, 386, 1191, 395, "探索一次", Sleep);
                        mFairy.onTap(0.7f, result, 1246, 31, 1250, 37, "关闭", Sleep);
                        mFairy.onTap(0.7f, result, 1246, 31, 1250, 37, "关闭", Sleep);
                    }

                }
                result = mFairy.findPic(695, 190, 1038, 287, "shangzhen.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.7f, result, 1201, 439, 1206, 447, "上阵", Sleep);
                    mFairy.onTap(0.7f, result, 229, 521, 232, 526, "选择", Sleep);
                    mFairy.onTap(0.7f, result, 1246, 31, 1250, 37, "关闭", Sleep);
                    mFairy.onTap(0.7f, result, 1246, 31, 1250, 37, "关闭", Sleep);
                    mFairy.onTap(0.7f, result, 1246, 31, 1250, 37, "关闭", Sleep);
                }
                result = mFairy.findPic(1111, 3, 1274, 41, new String[]{"tiaoguo2.png", "tiaoguo5.png"});
                mFairy.onTap(0.8f, result, "跳过", 1000);

                result = mFairy.findPic(842, 222, 1127, 572, "duihua2.png");
                mFairy.onTap(0.8f, result, "对话选择", 1000);

                result = mFairy.findPic(756, 243, 972, 535, "hand.png");
                mFairy.onTap(0.8f, result, "手", 4000);

                result = mFairy.findPic("Automatically put on.png");
                mFairy.onTap(0.8f, result, "自动穿上", Sleep);

                result = mFairy.findPic(1191, 123, 1275, 205, "Replica2.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(33, 240, 199, 293, "fbmb.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(103, 272, 109, 279, "副本中点左侧任务", Sleep);
                    }
                }

                result = mFairy.findPic("Replica.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("In combat.png");
                    if (result.sim < 0.8f) {
                        mFairy.onTap(148, 215, 164, 224, "副本中点左侧任务", Sleep);
                    }
                }
                result = mFairy.findPic("anchor.png");
                mFairy.onTap(0.8f, result, 16, 154, 30, 163, "主播", Sleep);

                result = mFairy.findPic("ttt.png");
                mFairy.onTap(0.8f, result, 1040, 621, 1063, 632, "通天塔", Sleep);


                result = mFairy.findPic("iknow.png");
                mFairy.onTap(0.8f, result, "知道了", 2000);

                result = mFairy.findPic(982, 589, 1197, 691, "tczc2.png");
                mFairy.onTap(0.8f, result, "退出战场", 2000);

                result = mFairy.findPic("Departure.png");
                mFairy.onTap(0.8f, result, "坐骑出站", Sleep);
                mFairy.onTap(0.8f, result, 1153, 55, 1167, 66, "关闭", Sleep);
                mFairy.onTap(0.8f, result, 851, 592, 868, 604, "骑乘", Sleep);


                result = mFairy.findPic(new String[]{"other.png", "other8.png"});
                mFairy.onTap(0.8f, result, 1225, 194, 1239, 206, "需要离开副本", Sleep);
                mFairy.onTap(0.8f, result, 753, 427, 802, 446, "离开确定", Sleep);

                result = mFairy.findPic("other7.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1225, 194, 1239, 206, "需要离开副本", Sleep);
                    mFairy.onTap(0.8f, result, 753, 427, 802, 446, "离开确定", Sleep);
                    if (result.sim > 0.8f) {
                        mFairy.sleep(100000);
                    }

                    result = mFairy.findPic(44, 178, 254, 318, "main.png");
                    mFairy.onTap(0.7f, result, "左侧主线", Sleep);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("主线结束"));
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(new String[]{"Enter the battlefield.png", "other1.png", "know.png", "other2.png", "other3.png", "other4.png", "other6.png"});
                mFairy.onTap(0.8f, result, "other", Sleep);

                result1 = mFairy.findPic(487, 612, 693, 696, "other4.png");
                if (result1.sim > 0.8f) {
                    result = mFairy.findPic(422, 545, 745, 713, "other9.png");
                    if (result.sim > 0.9f) {
                        mFairy.onTap(0.9f, result, 1213, 39, 1225, 52, "搜索关", Sleep);
                    } else {
                        mFairy.onTap(0.8f, result1, 576, 621, 600, 637, "搜索", Sleep);
                    }
                }

                result1 = mFairy.findPic(171, 162, 243, 249, "huangzhe.png");
                if (result1.sim > 0.8f) {
                    result = mFairy.findPic(319, 17, 395, 97, "shendi.png");
                    mFairy.onTap(0.8f, result, "神邸", Sleep);
                    result = mFairy.findPic(157, 99, 362, 305, "huangzhe1.png");
                    mFairy.onTap(0.8f, result, "黄者", Sleep);
                    mFairy.onTap(0.8f, result, 967, 633, 979, 645, "突破", Sleep);
                    mFairy.onTap(0.8f, result, 741, 437, 751, 444, "确定", Sleep);
                    mFairy.onTap(0.8f, result, 1245, 28, 1249, 32, "退出", Sleep);
                    mFairy.onTap(0.8f, result, 1245, 28, 1249, 32, "退出", Sleep);
                }

                result = mFairy.findPic(340, 592, 901, 710, "other5.png");
                mFairy.onTap(0.8f, result, "进入捕获", Sleep);
                mFairy.onTap(0.8f, result, 749, 435, 786, 452, "进入捕获确定", Sleep);

                result = mFairy.findPic("arms.png");
                mFairy.onTap(0.8f, result, 251, 126, 266, 141, "选择武器", Sleep);
                mFairy.onTap(0.8f, result, 967, 608, 977, 618, "强化", Sleep);
                mFairy.onTap(0.8f, result, 1214, 42, 1222, 54, "关闭强化", Sleep);

                result = mFairy.findPic(442, 3, 1269, 513, new String[]{"fork.png", "fork3.png"});
                LtLog.e("********" + picCount(0.8f, result, "叉"));
                if (picCount(0.8f, result, "叉") > 5) {
                    mFairy.onTap(0.8f, result, "关叉", Sleep);
                }
            }
        }.taskContent(mFairy, "新手引导");
    }

    public static int getAppCpuUsedPercent() {
        String[] cpuInfos = null;
        int AppCpuUsedPercent = -1;
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                int uid = android.os.Process.myUid();
                int pid = android.os.Process.myPid();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        Runtime.getRuntime().exec("top -b -u " + uid + " -n 1 ").getInputStream()), 500);
                String load = reader.readLine();
                while (load != null) {
                    if (load.contains(String.valueOf(pid))) {
                        break;
                    }
                    load = reader.readLine();
                    //   LtLog.e("load111====="+load);
                }
                //    LtLog.e("load2222====="+load);
                reader.close();
                cpuInfos = load.split("\\s+");
              /*  for (int i=0;i<cpuInfos.length;i++){
                    LtLog.e("cpuInfos====="+cpuInfos[i]);
                }*/
                AppCpuUsedPercent = Double.valueOf(cpuInfos[9]).intValue();
                // LtLog.e("AppCpuUsedPercent====="+AppCpuUsedPercent);
                AppCpuUsedPercent = ((AppCpuUsedPercent * 100) / 600);
            } else {
                int pid = android.os.Process.myPid();
                int uid = android.os.Process.myUid();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        Runtime.getRuntime().exec("top -n 1").getInputStream()), 500);
                String load = reader.readLine();
                while (load != null) {
                    if (load.contains(String.valueOf(pid))) {
                        break;
                    }
                    load = reader.readLine();
                }
                reader.close();
                cpuInfos = load.split("%");
                AppCpuUsedPercent = Integer.parseInt(cpuInfos[0].substring(cpuInfos[0].length() - 3).trim());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return AppCpuUsedPercent;
    }

    public void inOperation() throws Exception {
        result = mFairy.findPic(1111, 3, 1274, 41, new String[]{"tiaoguo4.png", "tiaoguo2.png", "tiaoguo3.png"});
        mFairy.onTap(0.8f, result, "跳过", 1000);
        result = mFairy.findPic("smOverGraph.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("过图中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic("In transmission.png");
        if (result.sim > 0.7f) {
            LtLog.e(mFairy.getLineInfo("传送中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic("road.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("寻路中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic(188, 533, 369, 592, "complete.png");
        mFairy.onTap(0.8f, result, "完成", Sleep);
        if (result.sim > 0.8f) {
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic(546, 125, 731, 201, "errBackpack1.png");
        result1 = mFairy.findPic(475, 114, 795, 218, "full2.png");
        if (result.sim > 0.8f && result1.sim < 0.8f) {
            mFairy.initMatTime();
            gameUtil.clearBag();

        }
        result = mFairy.findPic(188, 533, 369, 592, "accept.png");
        mFairy.onTap(0.8f, result, "接受", Sleep);
        if (result.sim > 0.8f) {
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic(188, 533, 369, 592, "complete.png");
        mFairy.onTap(0.8f, result, "完成", Sleep);
    }

    public void teacher() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("smExperience.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    gameUtil.close(0);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 15) {
                    mFairy.initMatTime();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(3, 124, 37, 253, "rwlzk.png");
                mFairy.onTap(0.8f, result, "展开任务栏", Sleep);

                result = mFairy.findPic("put.png");
                mFairy.onTap(0.8f, result, "普通", Sleep);
                result = mFairy.findPic(36, 169, 249, 441, "leftshimen.png");
                mFairy.onTap(0.8f, result, "左侧师门", Sleep);

                result = mFairy.findPic("hand.png");
                mFairy.onTap(0.8f, result, "手", 5000);

                result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("战斗中"));
                    mFairy.initMatTime();
                    err = 0;
                }


             /*   result =mFairy.findPic(340,223,927,624,"smSure.png");
                mFairy.onTap(0.8f,result,"师门确认",Sleep);*/
            }
        }.taskContent(mFairy, "师门任务中");
    }//师门

    public void tianming() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("tmChallenge.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                long dazeTime1 = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
             /*  long dazeTime=mFairy.mMatTime(1215,128,61,16,0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间="+dazeTime));
                if (dazeTime>10){
                    result =mFairy.findPic("Replica.png");
                    mFairy.onTap(148,215,164,224,"副本中点左侧任务",3000);

                    result =mFairy.findPic(1099,277,1173,348,"NoBattle.png");
                    mFairy.onTap(0.8f,result,"开启自动战斗",Sleep);
                }*/

                if (dazeTime1 > 50) {
                    setTaskName(1);
                    return;
                }

                result = mFairy.findPic("Replica.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("xuetiao.png");
                        if (picCountS(0.8f, result, "血条") > 5) {
                            mFairy.onTap(148, 215, 164, 224, "副本中点左侧任务", 3000);
                        }
                    } else {
                        mFairy.onTap(148, 215, 164, 224, "副本中点左侧任务", 3000);
                        while (mFairy.condit()) {
                            super.inOperation();
                            long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                            LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                            if (dazeTime > 3) {
                                mFairy.initMatTime();
                                result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});
                                if (result.sim < 0.8f) {
                                    mFairy.onTap(1133, 329, 1141, 337, "开启自动战斗", Sleep);
                                }
                                break;
                            }
                        }
                    }
                }

                result = mFairy.findPic("tminface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("天命界面"));

                    if (AtFairyConfig.getOption("tmtz").equals("1")) {
                        mFairy.onTap(214, 320, 240, 332, "万劫窟", Sleep);
                    }
                    if (AtFairyConfig.getOption("tmtz").equals("2")) {
                        result = mFairy.findPic(394, 266, 580, 461, "suo1.png");
                        if (result.sim < 0.8f) {
                            mFairy.onTap(464, 329, 492, 346, "封魔阵", Sleep);
                        } else {
                            LtLog.e(mFairy.getLineInfo("未开启"));
                            setTaskEnd();
                            return;
                        }
                    }
                    if (AtFairyConfig.getOption("tmtz").equals("3")) {
                        result = mFairy.findPic(668, 289, 845, 429, "suo1.png");
                        if (result.sim < 0.8f) {
                            mFairy.onTap(714, 325, 748, 355, "厉山丘", Sleep);
                        } else {
                            LtLog.e(mFairy.getLineInfo("未开启"));
                            setTaskEnd();
                            return;
                        }
                    }
                    if (AtFairyConfig.getOption("tmtz").equals("4")) {
                        result = mFairy.findPic(953, 285, 1093, 430, "suo1.png");
                        if (result.sim < 0.8f) {
                            mFairy.onTap(1033, 328, 1070, 353, "共工雏", Sleep);
                        } else {
                            LtLog.e(mFairy.getLineInfo("未开启"));
                            setTaskEnd();
                            return;
                        }
                    }
                }
                result = mFairy.findPic("tmEnterCopy.png");
                mFairy.onTap(0.8f, result, "进入副本", Sleep);


                result = mFairy.findPic(665, 596, 837, 656, "tmleave.png");
                mFairy.onTap(0.8f, result, "天命离开", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(340, 223, 927, 624, "smSure.png");
                mFairy.onTap(0.8f, result, "天命确认", Sleep);
            }
        }.taskContent(mFairy, "天命挑战任务中");
    } //天命

    public void tongtianta() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("ttPagoda.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                result = mFairy.findPic(5, 314, 417, 517, "tttdr.png");
                mFairy.onTap(0.8f, result, "通天塔单人", Sleep);

                result = mFairy.findPic(696, 537, 1196, 691, "zddzgc.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(696, 537, 1196, 691, "ttSweep.png");
                    mFairy.onTap(0.8f, result, "一键扫荡", Sleep);
                    mFairy.onTap(0.8f, result, 753, 431, 790, 446, "一键扫荡确定", 500);
                    setTaskEnd();
                    return;
                } else {
                    result = mFairy.findPic(696, 537, 1196, 691, "ttSweep.png");
                    mFairy.onTap(0.8f, result, "一键扫荡", Sleep);
                    mFairy.onTap(0.8f, result, 753, 431, 790, 446, "一键扫荡确定", 10000);
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                result = mFairy.findPic(696, 537, 1196, 691, "zddzgc.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("已达到最高层"));
                    setTaskEnd();
                }
                result1 = mFairy.findPic(206, 117, 583, 660, "sdstop0.png");
                if (result1.sim > 0.8f) {
                    result = mFairy.findPic("ttReset.png");
                    if (result.sim < 0.8f) {
                        mFairy.onTap(0.8f, result1, 1013, 617, 1066, 639, "扫荡完毕开始挑战", Sleep);
                    } else {
                        LtLog.e(mFairy.getLineInfo("重置本层通天塔结束"));
                        setTaskEnd();
                    }
                }

                if (dazeTime > 5 && dazeTime < 10) {
                    result = mFairy.findPic("Replica.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});
                        if (result.sim < 0.8f) {
                            mFairy.onTap(1133, 329, 1141, 337, "开启自动战斗", Sleep);
                        }
                    }
                }
                result = mFairy.findPic(876, 601, 1040, 652, "Continue.png");
                mFairy.onTap(0.8f, result, "继续挑战", Sleep);

                result = mFairy.findPic(5, 314, 417, 517, "tttdr.png");
                mFairy.onTap(0.8f, result, "通天塔单人", Sleep);

                result = mFairy.findPic(644, 618, 818, 681, "Continue1.png");
                mFairy.onTap(0.8f, result, "继续挑战", Sleep);

                result = mFairy.findPic("zsyc.png");
                mFairy.onTap(0.8f, result, 773, 436, 785, 445, "只剩一次机会确定", Sleep);

                result = mFairy.findPic(856, 596, 1094, 653, "ttChallengeAgain.png");
                mFairy.onTap(0.8f, result, "再次挑战", Sleep);

                result = mFairy.findPic(856, 596, 1094, 653, "ttChallengeAgain2.png");
                mFairy.onTap(0.9f, result, 716, 619, 748, 633, "没有再次挑战了离开", Sleep);
                if (result.sim > 0.9f) {
                    setTaskEnd();
                }
                if (dazeTime > 100) {
                    setTaskName(0);
                }
            }

        }.taskContent(mFairy, "通天塔任务中");
    }//通天塔

    public void keju() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("kjExamination.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic("kjStartAnswer.png");
                mFairy.onTap(0.8f, result, "开始答题", Sleep);

                result = mFairy.findPic(new String[]{"kjAnswerAA.png", "kjAnswerAA1.png"});
                mFairy.onTap(0.8f, result, 632, 279, 664, 290, "选A", 3000);

                result = mFairy.findPic("kjSignOut.png");
                mFairy.onTap(0.8f, result, "答题退出", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                }
            }
        }.taskContent(mFairy, "科举乡试任务中");
    }//科举乡试

    public void caiji() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(1, 631, 91, 712, "Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result = mFairy.findPic(186, 434, 387, 548, "zhuangyuan.png");
                mFairy.onTap(0.8f, result, "打开庄园", Sleep);

                result1 = mFairy.findPic(1121, 5, 1237, 33, "wdzy.png");
                result = mFairy.findPic(650, 577, 864, 657, "hdzy.png");
                mFairy.onTap(0.8f, result, "回到庄园", Sleep);
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic(407, 49, 868, 183, "jszy.png");
                if (result.sim > 0.8f) {
                    LtLog.e("庄园未开启");
                    gameUtil.close(1);
                    result = mFairy.findPic(1161, 295, 1276, 403, new String[]{"lk.png", "lk2.png"});
                    mFairy.onTap(0.8f, result, 1220, 337, 1231, 347, "离开庄园", Sleep);
                    mFairy.onTap(0.8f, result, 767, 432, 782, 444, "确定离开", Sleep);
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(373, 635, 967, 717, "cj.png");
                mFairy.onTap(0.8f, result, "caiji", Sleep);

                result = mFairy.findPic(108, 80, 383, 649, "cj2.png");
                mFairy.onTap(0.8f, result, "caiji2", 2000);

                result = mFairy.findPic(532, 36, 733, 124, "xzgr.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(706, 230, 1033, 390, "dq.png");
                    if (result.sim > 0.8f) {
                        LtLog.e("没有工人");
                        gameUtil.close(1);
                        result = mFairy.findPic(1161, 295, 1276, 403, new String[]{"lk.png", "lk2.png"});
                        mFairy.onTap(0.8f, result, 1220, 337, 1231, 347, "离开庄园", Sleep);
                        mFairy.onTap(0.8f, result, 767, 432, 782, 444, "确定离开", Sleep);
                        setTaskEnd();
                        return;
                    } else {
                        result = mFairy.findPic(325, 527, 936, 666, "zdxz.png");
                        mFairy.onTap(0.8f, result, "自动选择", Sleep);
                        mFairy.onTap(0.8f, result, 771, 599, 795, 614, "开始作业", Sleep);
                        result = mFairy.findPic(519, 110, 757, 218, "lsbz.png");
                        if (result.sim > 0.8f) {
                            LtLog.e("没有粮食");
                            gameUtil.close(1);
                            result = mFairy.findPic(1161, 295, 1276, 403, new String[]{"lk.png", "lk2.png"});
                            mFairy.onTap(0.8f, result, 1220, 337, 1231, 347, "离开庄园", 1000);
                            mFairy.onTap(0.8f, result, 767, 432, 782, 444, "确定离开", Sleep);
                            setTaskEnd();
                            return;
                        }
                        result = mFairy.findPic(1005, 35, 1103, 114, "cha1.png");
                        mFairy.onTap(0.8f, result, "关闭", Sleep);
                        setTaskName(3);
                        return;
                    }
                }
                result = mFairy.findPic(706, 230, 1033, 390, "dq.png");
                if (result.sim > 0.8f) {
                    LtLog.e("没有工人");
                    gameUtil.close(1);
                    result = mFairy.findPic(1161, 295, 1276, 403, new String[]{"lk.png", "lk2.png"});
                    mFairy.onTap(0.8f, result, 1220, 337, 1231, 347, "离开庄园", Sleep);
                    mFairy.onTap(0.8f, result, 767, 432, 782, 444, "确定离开", Sleep);
                    setTaskEnd();
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(373, 635, 967, 717, "cj.png");
                mFairy.onTap(0.8f, result, "caiji", Sleep);

                result = mFairy.findPic(383, 107, 632, 633, "wk.png");
                mFairy.onTap(0.8f, result, "挖掘", Sleep);

                result = mFairy.findPic(532, 36, 733, 124, "xzgr.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(706, 230, 1033, 390, "dq.png");
                    if (result.sim > 0.8f) {
                        LtLog.e("没有工人");
                        gameUtil.close(1);
                        result = mFairy.findPic(1161, 295, 1276, 403, new String[]{"lk.png", "lk2.png"});
                        mFairy.onTap(0.8f, result, 1220, 337, 1231, 347, "离开庄园", Sleep);
                        mFairy.onTap(0.8f, result, 767, 432, 782, 444, "确定离开", Sleep);
                        setTaskEnd();
                        return;
                    } else {
                        result = mFairy.findPic(325, 527, 936, 666, "zdxz.png");
                        mFairy.onTap(0.8f, result, "自动选择", Sleep);
                        mFairy.onTap(0.8f, result, 771, 599, 795, 614, "开始作业", Sleep);
                        result = mFairy.findPic(519, 110, 757, 218, "lsbz.png");
                        if (result.sim > 0.8f) {
                            LtLog.e("没有粮食");
                            gameUtil.close(1);
                            result = mFairy.findPic(1161, 295, 1276, 403, new String[]{"lk.png", "lk2.png"});
                            mFairy.onTap(0.8f, result, 1220, 337, 1231, 347, "离开庄园", 1000);
                            mFairy.onTap(0.8f, result, 767, 432, 782, 444, "确定离开", Sleep);
                            setTaskEnd();
                            return;
                        }
                        result = mFairy.findPic(1005, 35, 1103, 114, "cha1.png");
                        mFairy.onTap(0.8f, result, "关闭", Sleep);
                        setTaskName(4);
                        return;
                    }
                }
                result = mFairy.findPic(706, 230, 1033, 390, "dq.png");
                if (result.sim > 0.8f) {
                    LtLog.e("没有工人");
                    gameUtil.close(1);
                    result = mFairy.findPic(1161, 295, 1276, 403, new String[]{"lk.png", "lk2.png"});
                    mFairy.onTap(0.8f, result, 1220, 337, 1231, 347, "离开庄园", Sleep);
                    mFairy.onTap(0.8f, result, 767, 432, 782, 444, "确定离开", Sleep);
                    setTaskEnd();
                    return;
                }
            }

            public void content_4() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(373, 635, 967, 717, "cj.png");
                mFairy.onTap(0.8f, result, "caiji", Sleep);

                result = mFairy.findPic(637, 119, 895, 625, "fm.png");
                mFairy.onTap(0.8f, result, "砍伐", Sleep);

                result = mFairy.findPic(532, 36, 733, 124, "xzgr.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(706, 230, 1033, 390, "dq.png");
                    if (result.sim > 0.8f) {
                        LtLog.e("没有工人");
                        gameUtil.close(1);
                        result = mFairy.findPic(1161, 295, 1276, 403, new String[]{"lk.png", "lk2.png"});
                        mFairy.onTap(0.8f, result, 1220, 337, 1231, 347, "离开庄园", Sleep);
                        mFairy.onTap(0.8f, result, 767, 432, 782, 444, "确定离开", Sleep);
                        setTaskEnd();
                        return;
                    } else {
                        result = mFairy.findPic(325, 527, 936, 666, "zdxz.png");
                        mFairy.onTap(0.8f, result, "自动选择", Sleep);
                        mFairy.onTap(0.8f, result, 771, 599, 795, 614, "开始作业", Sleep);
                        result = mFairy.findPic(519, 110, 757, 218, "lsbz.png");
                        if (result.sim > 0.8f) {
                            LtLog.e("没有粮食");
                            gameUtil.close(1);
                            result = mFairy.findPic(1161, 295, 1276, 403, new String[]{"lk.png", "lk2.png"});
                            mFairy.onTap(0.8f, result, 1220, 337, 1231, 347, "离开庄园", 1000);
                            mFairy.onTap(0.8f, result, 767, 432, 782, 444, "确定离开", Sleep);
                            setTaskEnd();
                            return;
                        } else {
                            LtLog.e("完成结束");
                        }
                        gameUtil.close(1);
                        result = mFairy.findPic(1161, 295, 1276, 403, new String[]{"lk.png", "lk2.png"});
                        mFairy.onTap(0.8f, result, 1220, 337, 1231, 347, "离开庄园", 1000);
                        mFairy.onTap(0.8f, result, 767, 432, 782, 444, "确定离开", Sleep);
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(706, 230, 1033, 390, "dq.png");
                if (result.sim > 0.8f) {
                    LtLog.e("没有工人");
                    gameUtil.close(1);
                    result = mFairy.findPic(1161, 295, 1276, 403, new String[]{"lk.png", "lk2.png"});
                    mFairy.onTap(0.8f, result, 1220, 337, 1231, 347, "离开庄园", Sleep);
                    mFairy.onTap(0.8f, result, 767, 432, 782, 444, "确定离开", Sleep);
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "庄园采集");
    }//采集

    public void visit() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("Visit.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            int count = 0;

            /*
             * 1、71,264   2、78,267  3、90,269 4、97,269 5、104,269 6、112,269
             * 7、112,245  8、105,245  9、98,245 10、90,246 11、78,248 12、70,250
             * */
            public void content_2() throws Exception {
                if (count == 0) {
                    gameUtil.coordinate("轩辕", 71, 264);
                    result = mFairy.findPic("visualAngle.png");
                    mFairy.onTap(0.8f, result, "锁定视角", Sleep);
                    mFairy.onTap(419, 177, 431, 189, "选雕像", 2000);
                }
                if (count == 1) {
                    gameUtil.coordinate("轩辕", 78, 267);
                    result = mFairy.findPic("visualAngle.png");
                    mFairy.onTap(0.8f, result, "锁定视角", Sleep);
                    mFairy.onTap(445, 167, 460, 179, "选雕像1", 2000);
                }
                if (count == 2) {
                    gameUtil.coordinate("轩辕", 90, 269);
                    result = mFairy.findPic("visualAngle.png");
                    mFairy.onTap(0.8f, result, "锁定视角", Sleep);
                    mFairy.onTap(520, 177, 531, 186, "选雕像2", 2000);
                }
                if (count == 3) {
                    gameUtil.coordinate("轩辕", 97, 269);
                    result = mFairy.findPic("visualAngle.png");
                    mFairy.onTap(0.8f, result, "锁定视角", Sleep);
                    mFairy.onTap(532, 175, 549, 192, "选雕像3", 2000);
                }
                if (count == 4) {
                    gameUtil.coordinate("轩辕", 104, 269);
                    result = mFairy.findPic("visualAngle.png");
                    mFairy.onTap(0.8f, result, "锁定视角", Sleep);
                    mFairy.onTap(500, 174, 518, 188, "选雕像4", 2000);
                }
                if (count == 5) {
                    gameUtil.coordinate("轩辕", 112, 269);
                    result = mFairy.findPic("visualAngle.png");
                    mFairy.onTap(0.8f, result, "锁定视角", Sleep);
                    mFairy.onTap(538, 163, 555, 176, "选雕像5", 2000);
                }
                if (count == 6) {
                    gameUtil.coordinate("轩辕", 112, 245);
                    result = mFairy.findPic("visualAngle.png");
                    mFairy.onTap(0.8f, result, "锁定视角", Sleep);
                    mFairy.onTap(731, 209, 741, 218, "选雕像6", 2000);
                }
                if (count == 7) {
                    gameUtil.coordinate("轩辕", 105, 245);
                    result = mFairy.findPic("visualAngle.png");
                    mFairy.onTap(0.8f, result, "锁定视角", Sleep);
                    mFairy.onTap(742, 184, 751, 193, "选雕像7", 2000);
                }
                if (count == 8) {
                    gameUtil.coordinate("轩辕", 98, 245);
                    result = mFairy.findPic("visualAngle.png");
                    mFairy.onTap(0.8f, result, "锁定视角", Sleep);
                    mFairy.onTap(723, 170, 734, 181, "选雕像8", 1000);
                }
                if (count == 9) {
                    gameUtil.coordinate("轩辕", 90, 246);
                    result = mFairy.findPic("visualAngle.png");
                    mFairy.onTap(0.8f, result, "锁定视角", Sleep);
                    mFairy.onTap(784, 221, 792, 232, "选雕像9", 1000);
                }
                if (count == 10) {
                    gameUtil.coordinate("轩辕", 78, 248);
                    result = mFairy.findPic("visualAngle.png");
                    mFairy.onTap(0.8f, result, "锁定视角", Sleep);
                    mFairy.onTap(799, 205, 810, 217, "选雕像10", 1000);
                }
                if (count == 11) {
                    gameUtil.coordinate("轩辕", 70, 250);
                    result = mFairy.findPic("visualAngle.png");
                    mFairy.onTap(0.8f, result, "锁定视角", Sleep);
                    mFairy.onTap(791, 243, 805, 254, "选雕像11", 1000);
                }
                if (count == 12) {
                    LtLog.e(mFairy.getLineInfo("参拜完毕"));
                    setTaskEnd();
                    return;
                }
                count++;
                setTaskName(3);
                return;
            }

            int stopCount = 0;

            public void content_3() throws Exception {
                if (overtime(6, 2)) return;
                result = mFairy.findPic(38, 361, 361, 593, "leftVisit.png");
                mFairy.onTap(0.8f, result, "左侧参拜一下", Sleep);
                result1 = mFairy.findPic(479, 95, 486, 102, "cxrq.png");
                if (result.sim > 0.8f) {
                    stopCount++;
                    if (stopCount >= 3) {
                        LtLog.e(mFairy.getLineInfo("参拜完毕"));
                        setTaskEnd();
                        return;
                    } else {
                        setTaskName(2);
                        return;
                    }
                } else if (result1.sim > 0.8f) {
                    LtLog.e("没有参拜");
                    mFairy.onTap(0.8f, result1, 479, 95, 486, 102, "关闭", Sleep);
                    setTaskName(2);
                    return;
                }
            }
        }.taskContent(mFairy, "雕像参拜任务中");
    }//雕像参拜

    public void jgly() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                result = mFairy.findPic(0, 255, 39, 462, "team.png");
                mFairy.onTap(0.95f, result, 15, 234, 29, 262, "在队伍栏切换到任务栏", Sleep);

                result = mFairy.findPic("put.png");
                mFairy.onTap(0.8f, result, "普通", Sleep);

                result = mFairy.findPic(18, 116, 532, 607, "Leftjg.png");
                mFairy.onTap(0.8f, result, "左侧建功", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 3, 76, 400, 76, 216, 500, 1500, 2);
                if (overtime(8, 2)) return;
            }

            public void content_2() throws Exception {
                int ret = gameUtil.mission("jgbuy.png", 1);
                if (ret == 1) {
                    setTaskName(3);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_3() throws Exception {
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 30) {
                    mFairy.initMatTime();
                    setTaskName(1);
                    return;
                }
                result = mFairy.findPic(575, 98, 711, 233, "bagM.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("背包满了结束"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(58, 376, 496, 460, "qxyc.png");
                mFairy.onTap(0.8f, result, 477, 90, 490, 105, "关闭", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(4);
                    return;

                }

                result = mFairy.findPic(51, 372, 497, 583, "jgly.png");
                mFairy.onTap(0.8f, result, "左侧建功立业", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                }
                Thread.sleep(2000);
            }

            public void content_4() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(0, 255, 39, 462, "team.png");
                mFairy.onTap(0.95f, result, 15, 234, 29, 262, "在队伍栏切换到任务栏", Sleep);

                result = mFairy.findPic(0, 166, 34, 386, "task.png");
                mFairy.onTap(0.9f, result, 15, 234, 29, 262, "在任务栏打开任务框", Sleep);

                result = mFairy.findPic(1110, 97, 1212, 193, "yijie1.png");
                mFairy.onTap(0.9f, result, "打开已接任务", Sleep);

                result = mFairy.findPic(81, 23, 283, 102, "renwu.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(74, 162, 376, 259, "hrw.png");
                    if (result.sim < 0.8f) {
                        mFairy.onTap(218, 131, 234, 144, "主线收回", Sleep);
                    }
                }

                result = mFairy.findPic(75, 92, 403, 586, "jgly1.png");
                mFairy.onTap(0.9f, result, "打开建功立业", Sleep);
                if (result.sim < 0.8f) {
                    result = mFairy.findPic(671, 562, 1167, 689, "qw1.png");
                    mFairy.onTap(0.8f, result, "前往", Sleep);
                    setTaskName(3);
                    return;
                }


            }
        }.taskContent(mFairy, "建工立业任务中");
    }//建工立业

    public void xmqf() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void inOperation() throws Exception {
                super.inOperation();
            }

            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("Praying.png", 1);
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
                result = mFairy.findPic("xmqfLower.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("祈福界面"));
                  /*  String string =AtFairyConfig.getOption("qifu");
                    String string1 =AtFairyConfig.getOption("tm");
                    LtLog.e(mFairy.getLineInfo("string="+string));
                    LtLog.e(mFairy.getLineInfo("string1="+string1));
                    LtLog.e(mFairy.getLineInfo("string="+string.equals("1")));
                    LtLog.e(mFairy.getLineInfo("string1="+string1.equals("2")));*/
                    if (AtFairyConfig.getOption("qifu").equals("1")) {
                        mFairy.onTap(0.8f, result, 560, 513, 568, 522, "低级祈福", Sleep);
                    }
                    if (AtFairyConfig.getOption("qifu").equals("2")) {
                        mFairy.onTap(0.8f, result, 724, 516, 741, 521, "中级祈福", Sleep);
                    }
                    if (AtFairyConfig.getOption("qifu").equals("3")) {
                        mFairy.onTap(0.8f, result, 906, 514, 928, 523, "高级祈福", Sleep);
                    }
                }
                result = mFairy.findPic(365, 266, 923, 500, "qbg.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("钱不够！！"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(461, 472, 1001, 605, "xmqfCompleted.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("祈福完成"));
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "血盟祈福任务中");
    } //血盟祈福

    public void cbt() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("btMap.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                overtime(10, 0);
               /* result = mFairy.findPic(23, 31, 1248, 690, "xmqjUse.png");
                mFairy.onTap(0.8f, result, "使用", Sleep);*/

                result = mFairy.findPic(15, 8, 329, 162, "noMap.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("没有藏宝图了"));
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(665, 599, 839, 654, "cbtfblk.png");
                mFairy.onTap(0.8f, result, "藏宝图副本离开", Sleep);

                result = mFairy.findPic(846, 450, 1060, 548, "btNowUse.png");
                mFairy.onTap(0.8f, result, "立即使用", Sleep);
                if (result.sim < 0.8f) {
                    result = mFairy.findPic(601, 145, 1133, 530, new String[]{"luo.png", "cbt.png", "cbt1.png", "cbt2.png"});// 1065 257   895,351
                    mFairy.onTap(0.8f, result, "藏宝图", Sleep);
                    //mFairy.onTap(0.8f, result, result.x-170,result.y+94,result.x-169,result.y+95,"藏宝图", Sleep);
                    if (result.sim > 0.8f) {
                        err = 0;
                    }
                } else {
                    err = 0;
                }
                result = mFairy.findPic(43, 116, 830, 625, new String[]{"seniorcb.png", "seniorcb2.png"});//537,259   900,351
                mFairy.onTap(0.8f, result, result.x + 363, result.y + 92, result.x + 364, result.y + 93, "使用", Sleep);


                result = mFairy.findPic("Replica.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("Hangup1.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("xuetiao.png");
                        if (picCountS(0.8f, result, "血条") > 5) {
                            mFairy.onTap(148, 215, 164, 224, "副本中点左侧任务", 3000);
                        }
                    } else {
                        mFairy.onTap(148, 215, 164, 224, "副本中点左侧任务", 3000);
                        while (mFairy.condit()) {
                            super.inOperation();
                            long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                            LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                            if (dazeTime > 3) {
                                mFairy.initMatTime();
                                result = mFairy.findPic(1099, 277, 1173, 348, "NoBattle.png");
                                mFairy.onTap(0.8f, result, "开启自动战斗", Sleep);
                                break;
                            }
                        }
                    }
                }
            }
        }.taskContent(mFairy, "藏宝图任务中");
    }//藏宝图

    public void xmrw() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                result = mFairy.findPic(0, 255, 39, 462, "team.png");
                mFairy.onTap(0.95f, result, 15, 234, 29, 262, "在队伍栏切换到任务栏", Sleep);

                result = mFairy.findPic("put2.png");
                mFairy.onTap(0.95f, result, 89, 178, 98, 182, "普通栏暗点", Sleep);

                result = mFairy.findPic(18, 116, 532, 607, "leftxmrw.png");
                mFairy.onTap(0.95f, result, "左侧血盟任务", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6, 8, 10}, 0, 119, 423, 116, 245, 500, 1500, 2);
                if (overtime(10, 2)) return;
            }

            public void content_2() throws Exception {
                int ret = gameUtil.mission("xmrw.png", 1);
                if (ret == 1) {
                    setTaskName(3);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_3() throws Exception {
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 10) {
                    mFairy.initMatTime();
                    gameUtil.close(1);
                    setTaskName(4);
                    return;
                }
              /*  result = mFairy.findPic(18, 116, 532, 607, "leftxmrw.png");
                if (result.sim > 0.8f){

                }else {
                    setTaskName(1);return;
                }*/
                result1 = mFairy.findPic(517, 82, 746, 203, "myxm.png");
                result = mFairy.findPic("Nosdl.png");
                mFairy.onTap(0.8f, result, 491, 424, 545, 451, "没有扫荡令了", Sleep);
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("xmrwwc.png");
                mFairy.onTap(0.8f, result, "完成", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    err = 0;
                }
                result = mFairy.findPic(new String[]{"xmrwjs.png", "jieshou.png"});
                mFairy.onTap(0.8f, result, "接受", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    err = 0;
                }
                result = mFairy.findPic(499, 561, 1179, 699, "qwhq.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("前往获取没有材料了跳过"));
                    result = mFairy.findPic(499, 561, 1179, 699, "tiaoguo.png");
                    mFairy.onTap(0.8f, result, "跳过本环", Sleep);
                    mFairy.onTap(0.8f, result, 362, 364, 376, 376, "跳过本环", Sleep);
                    mFairy.onTap(0.8f, result, 758, 455, 792, 476, "跳过本环", Sleep);
                }

                result = mFairy.findPic("tjcl.png");
                mFairy.onTap(0.8f, result, 668, 612, 687, 628, "提交材料", Sleep);
                mFairy.onTap(0.8f, result, 168, 108, 191, 125, "提交材料", Sleep);
                mFairy.onTap(0.8f, result, "提交材料", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    err = 0;
                }

                result = mFairy.findPic("Lazy.png");
                mFairy.onTap(0.8f, result, 636, 430, 648, 439, "想偷懒", Sleep);
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("新号做联盟任务不能跳过导致卡任务给停止"));
                    setTaskEnd();
                    return;
                }
            }

            public void content_4() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(0, 255, 39, 462, "team.png");
                mFairy.onTap(0.95f, result, 15, 234, 29, 262, "在队伍栏切换到任务栏", Sleep);

                result = mFairy.findPic(0, 166, 34, 386, "task.png");
                mFairy.onTap(0.9f, result, 15, 234, 29, 262, "在任务栏打开任务框", Sleep);

                result = mFairy.findPic(1110, 97, 1212, 193, "yijie1.png");
                mFairy.onTap(0.9f, result, "打开已接任务", Sleep);

                result = mFairy.findPic(81, 23, 283, 102, "renwu.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(74, 162, 376, 259, "hrw.png");
                    if (result.sim < 0.8f) {
                        mFairy.onTap(218, 131, 234, 144, "主线收回", Sleep);
                    }
                }


                result = mFairy.findPic(75, 92, 403, 586, "xmrw1.png");
                mFairy.onTap(0.9f, result, "打开血盟任务", Sleep);

                result = mFairy.findPic(497, 569, 1134, 689, new String[]{"tiaoguo.png", "tiaoguo1.png"});
                mFairy.onTap(0.8f, result, "跳过本环", Sleep);
                mFairy.onTap(0.8f, result, 362, 364, 376, 376, "跳过本环", Sleep);
                mFairy.onTap(0.8f, result, 758, 455, 792, 476, "跳过本环", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }


            }

        }.taskContent(mFairy, "血盟任务中");
    } //血盟任务

    public void szmt() throws Exception {
        new SingleTask(mFairy) {
            int mtCount = 0;

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                result = mFairy.findPic("task common.png");
                mFairy.onTap(0.95f, result, "任务栏初始化", Sleep);

                result = mFairy.findPic(796, 8, 1115, 157, new String[]{"daily.png", "fuli.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png", "bahuang.png", "bh.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("有天外山海图标"));
                        mFairy.initMatTime();
                        setTaskName(1);
                        return;
                    } else {
                        LtLog.e(mFairy.getLineInfo("没有天外山海图标"));
                        setTaskEnd();
                        return;
                    }
                }
            }

            int x = 0;
            int y = 0;

            public void content_1() throws Exception {

                result = mFairy.findPic("bag2.png");
                mFairy.onTap(0.9f, result, 1213, 42, 1225, 48, "背包关闭", Sleep);
                /*result=mFairy.findPic("me.png");
                mFairy.onTap(0.9f,result,1109,45,1116,58,"关闭",Sleep);*/

                LtLog.e("-----------==" + mtCount);
                result = mFairy.findPic(3, 281, 28, 380, "baise.png");
                mFairy.onTap(0.9f, result, 13, 233, 27, 257, "任务栏暗点切换到任务栏", Sleep);

                /*result=mFairy.findPic("shizu.png");
                mFairy.onTap(0.95f, result, 178,195,201,211,"氏族栏暗点切换到氏族栏", Sleep);
                if (result.sim > 0.95){
                    mFairy.initMatTime();
                }*/


                result = mFairy.findPic("jqmt.png");
                mFairy.onTap(0.8f, result, "接取密探任务", Sleep);

                result = mFairy.findPic(52, 375, 497, 585, "jqmt1.png");
                mFairy.onTap(0.8f, result, "接取密探任务", Sleep);

                result = mFairy.findPic(52, 375, 497, 585, "mtjf.png");
                mFairy.onTap(0.8f, result, "交付密探任务", Sleep);
              /*  if (result.sim > 0.8f){
                    mFairy.initMatTime();
                }*/

                result = mFairy.findPic(6, 245, 1194, 484, "death.png");
                mFairy.onTap(0.8f, result, "复活", Sleep);

                result = mFairy.findPic(61, 377, 492, 557, "sqqb.png");
                mFairy.onTap(0.8f, result, "索取情报", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                }
                result = mFairy.findPic("skip.png");
                mFairy.onTap(0.8f, result, "err跳过", Sleep);

                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                if (dazeTime > 5 && dazeTime < 50) {

                    result = mFairy.findPic("shizu.png");
                    mFairy.onTap(0.95f, result, 192, 177, 207, 181, "氏族栏暗点切换到氏族栏", Sleep);

                    /*result = mFairy.findPic(37,200,262,315, new String[]{"yxmt.png","yxmt9.png", "yxmt1.png", "yxmt4.png", "yxmt3.png", "yxmt6.png", "yxmt5.png", "yxmt7.png", "yxmt8.png", "yxmt9.png"});
                    LtLog.e("左侧密探任务滑动前相似度" + result.sim);*/
                    result2 = mFairy.findPic(37, 193, 262, 261, new String[]{"yxmt.png", "yxmt1.png"});
                    result1 = mFairy.findPic("shizu3.png");
                    if (result.sim < 0.7f && result1.sim > 0.8f && result2.sim < 0.8f) {
                        LtLog.e("上滑滑动=====");
                        mFairy.ranSwipe(190, 361, 200, 115, 300, Sleep);
                        x++;

                    }
                    LtLog.e("滑动识别---" + result.sim);

                    result2 = mFairy.findPic(211, 282, 277, 436, new String[]{"wc.png", "jf.png"});

                    result1 = mFairy.findPic(36, 198, 256, 445, new String[]{"mi.png", "mi2.png", "mi3.png", "mi4.png"});

                    if (result1.sim < 0.8f && result2.sim < 0.8f) {
                        result2 = mFairy.findPic(38, 198, 262, 388, new String[]{"yxmt.png", "yxmt9.png", "yxmt1.png", "yxmt10.png", "yxmt3.png", "yxmt4.png", "yxmt5.png", "yxmt6.png", "yxmt7.png", "yxmt8.png", "yxmt9.png"});
                        LtLog.e("左侧密探任务相似度" + result2.sim);
                        if (result2.sim > 0.7f) {

                            mFairy.onTap(0.7f, result2, "左侧隐形密探", 500);

                            for (int i = 0; i < 20; i++) {
                                result1 = mFairy.findPic(500, 85, 769, 210, new String[]{"smwfjr.png", "wfjrsm.png"});
                                if (result1.sim > 0.75f) {
                                    LtLog.i(mFairy.getLineInfo("神魔未开启"));
                                    setTaskEnd();
                                    return;
                                }
                                LtLog.e("查看是否开启");
                            }
                        }

                    }

                    result3 = mFairy.findPic(40, 199, 111, 362, new String[]{"4.png", "rwqd.png"});
                    if (result3.sim > 0.7f) {
                        LtLog.e("下滑滑动=====");
                        mFairy.ranSwipe(200, 200, 200, 400, 300, Sleep);
                        if (x > 4) {
                            LtLog.e("下滑滑动到顶=====");
                            mFairy.ranSwipe(200, 200, 200, 450, 300, Sleep);
                            mFairy.ranSwipe(200, 200, 200, 450, 300, Sleep);
                            x = 0;
                        }
                    }
                    if (y >= 10) {
                        y = 0;

                    }
                    result = mFairy.findPic(38, 199, 220, 329, new String[]{"yxmt.png", "yxmt1.png"});//47, 221, 274, 399
                    LtLog.e("前往识别---" + result.sim);

                    if (result.sim > 0.7f) {
                        result1 = mFairy.findPic(36, 198, 256, 445, new String[]{"mi.png", "mi2.png", "mi3.png", "mi4.png"});
                        if (result1.sim > 0.7f) {
                            y++;
                            //84,277   246,366,286,391  242,389,289,418                               243,439,290,468
                            result2 = mFairy.findPic(result.x + 160, result.y + 89, result.x + 205, result.y + 114, "wc.png");
                            if (result2.sim > 0.8f && mtCount == 0) {
                                mtCount++;
                            }
                            result2 = mFairy.findPic(result.x + 160, result.y + 112, result.x + 205, result.y + 141, "wc.png");
                            if (result2.sim > 0.8f && mtCount == 1) {
                                mtCount++;
                            }
                            result2 = mFairy.findPic(result.x + 160, result.y + 138, result.x + 205, result.y + 168, "wc.png");
                            if (result2.sim > 0.8f && mtCount == 2) {
                                mtCount++;
                            }
                            result2 = mFairy.findPic(result.x + 160, result.y + 162, result.x + 205, result.y + 191, "wc.png");
                            if (result2.sim > 0.8f && mtCount == 3) {
                                mtCount++;
                            }

                            if (mtCount >= 4) {
                                mtCount = 0;
                            }

                            if (mtCount == 0) {
                                mFairy.onTap(0.7f, result, result.x + 170, result.y + 102, result.x + 171, result.y + 103, "第1个前往", Sleep);
                                mFairy.initMatTime();
                            }
                            if (mtCount == 1) {
                                mFairy.onTap(0.7f, result, result.x + 170, result.y + 125, result.x + 171, result.y + 128, "第2个前往", Sleep);
                                mFairy.initMatTime();
                            }
                            if (mtCount == 2) {
                                mFairy.onTap(0.7f, result, result.x + 170, result.y + 151, result.x + 171, result.y + 152, "第3个前往", Sleep);
                                mFairy.initMatTime();
                            }
                            if (mtCount == 3) {
                                mFairy.onTap(0.7f, result, result.x + 170, result.y + 173, result.x + 171, result.y + 174, "第4个前往", Sleep);
                                mFairy.initMatTime();
                            }
                            if (result.sim > 0.8f) {
                                result2 = mFairy.findPic("bianjing3.png");
                                if (result2.sim > 0.8f) {
                                    dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                                    mFairy.sleep(10000);
                                    dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                                    if (result.sim > 0.8f && dazeTime >= 3) {
                                        mFairy.initMatTime();
                                        setTaskName(2);
                                        return;
                                    }
                                }
                            }


                            result = mFairy.findPic(61, 377, 492, 557, "sqqb.png");
                            mFairy.onTap(0.8f, result, "索取情报", Sleep);
                            if (result.sim > 0.8f) {
                                mFairy.initMatTime();
                            }
                        }
                    }
                }
                if (dazeTime > 50) {
                    mtCount++;
                    if (mtCount >= 4) {
                        mtCount = 0;
                    }
                }

                if (dazeTime > 60) {
                    mFairy.initMatTime();
                    gameUtil.close(0);
                    result = mFairy.findPic(37, 196, 268, 466, new String[]{"yxmt.png", "yxmt1.png", "yxmt4.png", "yxmt10.png", "yxmt3.png", "yxmt6.png", "yxmt5.png", "yxmt7.png", "yxmt8.png", "yxmt9.png"});
                    LtLog.e("结束识别---" + result.sim);
                    result1 = mFairy.findPic(149, 164, 266, 200, "shizu4.png");
                    result2 = mFairy.findPic("bianjing3.png");
                    result3 = mFairy.findPic(38, 201, 247, 292, new String[]{"jiancai.png", "jiancai3.png", "jiancai4.png"});
                    LtLog.e("/*result=/" + result.sim + ",result1" + result1 + "result3" + result3);
                    if (result.sim < 0.7f && result1.sim > 0.8f && result3.sim < 0.7f) {
                        LtLog.e(mFairy.getLineInfo("左侧没有密探结束"));
                        // setTaskEnd();
                        setTaskName(3);
                        return;
                    } else if (result1.sim < 0.8f) {
                        LtLog.e(mFairy.getLineInfo("左侧没有密探结束"));
                        // setTaskEnd();
                        setTaskName(3);
                        return;
                    } else if (result2.sim > 0.8f) {
                        setTaskName(2);
                        return;
                        //setTaskName(0);return;
                    }
                } else {

                }
            }

            public void content_2() throws Exception {
                if (overtime(10, 0)) return;
                result2 = mFairy.findPic("bianjing3.png");
                if (result2.sim > 0.8f) {
                    mFairy.onTap(0.8f, result2, 1199, 89, 1206, 99, "打开地图", 2500);

                    result = mFairy.findPic(574, 486, 595, 503, new String[]{"zszb.png", "zszb1.png", "zszb2.png"});
                    result1 = mFairy.findPic(560, 495, 599, 522, new String[]{"zszb.png", "zszb1.png", "zszb2.png"});
                    if (result.sim > 0.8f || result1.sim > 0.8f) {
                        LtLog.e("天外边境坐牢");
                        mFairy.onTap(429, 339, 437, 350, "地图", 2000);
                        mFairy.onTap(581, 504, 582, 508, "地图", 2000);
                        mFairy.onTap(1238, 36, 1251, 57, "关闭地图", 2000);

                        result = mFairy.findPic(52, 18, 1225, 670, "duihua3.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "传送使者对话", Sleep);
                        } else {
                            //mFairy.onTap(0.8f, result, result.x + 2, result.y + 153, result.x + 3, result.y + 154, "点击传送使者", Sleep);
                        }

                    } else {
                        result = mFairy.findPic(1205, 9, 1275, 97, "dtgc.png");
                        mFairy.onTap(0.8f, result1, "关闭地图", 2000);
                    }
                }
                result = mFairy.findPic(52, 376, 491, 578, "deliveryyes.png");
                mFairy.onTap(0.8f, result, "传送", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    setTaskName(1);
                    return;
                }
            }

            public void content_3() throws Exception {
                int ret = gameUtil.mission("yxmt2.png", 1);
                if (ret == 1) {
                    setTaskName(0);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "氏族密探任务中");
    } //氏族密探

    public void mqtc() throws Exception {
        new SingleTask(mFairy) {
            int mtCount = 0;
            int i = 0;

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                result = mFairy.findPic("task common.png");
                mFairy.onTap(0.95f, result, "任务栏初始化", Sleep);

                result = mFairy.findPic(796, 8, 1115, 157, new String[]{"daily.png", "fuli.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png", "bahuang.png", "bh.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("有天外山海图标"));
                        mFairy.initMatTime();
                        setTaskName(1);
                        return;
                    } else {
                        LtLog.e(mFairy.getLineInfo("没有天外山海图标"));
                        setTaskEnd();
                        return;
                    }
                }
            }

            public void content_1() throws Exception {
                result = mFairy.findPic("bag2.png");
                mFairy.onTap(0.9f, result, 1189, 44, 1203, 51, "背包关闭", Sleep);
                result = mFairy.findPic("me.png");
                mFairy.onTap(0.9f, result, 1109, 45, 1116, 58, "关闭", Sleep);

                LtLog.e("-----------==" + mtCount);

                result = mFairy.findPic(0, 255, 39, 462, "team.png");
                mFairy.onTap(0.95f, result, 13, 233, 27, 257, "任务栏暗点切换到任务栏", Sleep);

                if (result.sim > 0.95) {
                    mFairy.initMatTime();
                }

                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                if (dazeTime > 5) {

                    result = mFairy.findPic("shizu.png");
                    mFairy.onTap(0.95f, result, 185, 174, 206, 183, "氏族栏暗点切换到氏族栏", Sleep);

                    result = mFairy.findPic("jqmq.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "接取魔气探查任务", Sleep);

                    }
                    result1 = mFairy.findPic(36, 198, 267, 443, new String[]{"moqi.png", "moqi2.png", "mq.png", "jhhc.png", "yz.png", "mqdctj2.png"});
                    if (result1.sim > 0.8f) {
                        mFairy.initMatTime();
                        setTaskName(2);
                        return;
                    }


                    result = mFairy.findPic(36, 197, 271, 355, new String[]{"mqtc.png", "mqtc1.png"});
                    result1 = mFairy.findPic(37, 199, 258, 450, new String[]{"mqdctj2.png", "mqdctj3.png"});
                    if (result.sim > 0.75f && result1.sim < 0.8f) {
                        mFairy.onTap(0.75f, result, "左侧魔气探查", 3000);
                        mFairy.initMatTime();
                    }
                }
                if (dazeTime > 40 && dazeTime < 50) {
                    result = mFairy.findPic(1059, 17, 1218, 114, "gc.png");
                    mFairy.onTap(0.95f, result, "不知道什么遮挡", Sleep);

                }
                if (dazeTime > 50) {
                    setTaskName(2);
                }

            }

            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                /*result = mFairy.findPic("mtjf.png");
                mFairy.onTap(0.8f, result, "交付密探任务", Sleep);
                if (result.sim > 0.8f){
                    mFairy.initMatTime();
                }*/

                result = mFairy.findPic(6, 245, 1194, 484, "death.png");
                mFairy.onTap(0.8f, result, "复活", Sleep);

                result = mFairy.findPic("skip.png");
                mFairy.onTap(0.8f, result, "err跳过", Sleep);

                result = mFairy.findPic(44, 227, 261, 494, "mqdctj2.png");
                mFairy.onTap(0.7f, result, "任务栏魔气调查提交", 5000);

                result = mFairy.findPic(42, 359, 506, 498, "mqdctj.png");
                mFairy.onTap(0.7f, result, "魔气调查提交", 5000);

                result = mFairy.findPic(557, 531, 726, 591, "shangjiao01.png");
                mFairy.onTap(0.7f, result, 630, 555, 657, 566, "上缴晶核", 5000);

   /*             if (dazeTime > 5 && dazeTime < 10) {

                }*/
                result2 = mFairy.findPic(810, 260, 913, 377, "cz.png");
                mFairy.onTap(0.7f, result2, 867, 306, 878, 323, "操作", 5000);

                if (dazeTime > 10) {
                    result = mFairy.findPic("shizu.png");
                    mFairy.onTap(0.95f, result, 178, 195, 201, 211, "氏族栏暗点切换到氏族栏", Sleep);

                    result = mFairy.findPic(36, 198, 265, 362, new String[]{"mqtc.png", "mqtc1.png", "mqtc3.png"});
                    if (result.sim > 0.7f) {

                        result1 = mFairy.findPic(34, 198, 265, 446, new String[]{"moqi.png", "moqi2.png", "mq.png", "jhqw.png", "jhhc.png", "yz.png"});
                        if (result1.sim > 0.7f) {

                            result2 = mFairy.findPic(1117, 3, 1279, 269, new String[]{"hdbf.png", "ld.png"});
                            if (result2.sim < 0.7f) {
                                result2 = mFairy.findPic(209, 204, 266, 433, "qw3.png");
                                if (result2.sim > 0.7f) {
                                    mFairy.onTap(0.7f, result2, "前往", 3000);
                                }
                            }

                            result2 = mFairy.findPic(1125, 0, 1278, 145, new String[]{"dt.png", "ld.png"});
                            mFairy.onTap(0.7f, result2, 1188, 76, 1214, 101, "打开地图", 3000);


                            result2 = mFairy.findPic(271, 117, 613, 569, "dtmq.png");
                            if (result2.sim > 0.7f) {
                                mFairy.onTap(0.7f, result2, "寻找魔气", 3000);
                                mFairy.initMatTime();
                            }

                            result2 = mFairy.findPic(1234, 34, 1262, 64, "gc.png");
                            mFairy.onTap(0.7f, result2, "关闭地图", 3000);

                        }
                    }
                }
                if (dazeTime > 60) {
                    mFairy.initMatTime();
                    gameUtil.close(0);

                    result = mFairy.findPic("rwl.png");
                    mFairy.onTap(0.9f, result, 13, 233, 27, 257, "任务栏暗点切换到任务栏", Sleep);

                    result = mFairy.findPic("shizu.png");
                    mFairy.onTap(0.95f, result, 178, 195, 201, 211, "氏族栏暗点切换到氏族栏", Sleep);

                    result = mFairy.findPic(32, 194, 291, 450, new String[]{"mqtc.png", "mqtc1.png"});
                    LtLog.e("结束识别---" + result.sim);
                    if (result.sim < 0.7f) {
                        i++;
                        if (i > 3) {
                            LtLog.e(mFairy.getLineInfo("左侧没有魔气结束"));
                            setTaskEnd();
                            return;
                        }
                    } else {
                        i = 0;
                        setTaskName(1);
                        return;
                    }
                }
            }
        }.taskContent(mFairy, "魔气探查任务中");
    } //魔气探查

    public void jlzm() throws Exception {
        new SingleTask(mFairy) {
            int mtCount = 0;
            int sum = 0;

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                result = mFairy.findPic("task common.png");
                mFairy.onTap(0.95f, result, "任务栏初始化", Sleep);

                result = mFairy.findPic(796, 8, 1115, 157, new String[]{"daily.png", "fuli.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png", "bahuang.png", "bh.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("有天外山海图标"));
                        mFairy.initMatTime();
                        setTaskName(1);
                        return;
                    } else {
                        LtLog.e(mFairy.getLineInfo("没有天外山海图标"));
                        setTaskEnd();
                        return;
                    }
                }
            }

            public void content_1() throws Exception {

                /*result1 = mFairy.findPic(366,64,960,219,"cncj.png");
                if (result.sim > 0.8f){
                    LtLog.e(mFairy.getLineInfo("没有九黎任务"));
                    setTaskEnd();
                    return;
                }*/

                result = mFairy.findPic(6, 245, 1194, 484, "death.png");
                mFairy.onTap(0.8f, result, "复活", Sleep);

                result = mFairy.findPic("bag2.png");
                mFairy.onTap(0.9f, result, 1189, 44, 1203, 51, "背包关闭", Sleep);

                result = mFairy.findPic(1190, 4, 1269, 68, "cha15.png");
                mFairy.onTap(0.9f, result, "关闭", Sleep);

                result = mFairy.findPic("me.png");
                mFairy.onTap(0.9f, result, 1109, 45, 1116, 58, "关闭", Sleep);

                LtLog.e("-----------==" + mtCount);

              /*  result = mFairy.findPic("task team.png");
                mFairy.onTap(0.9f, result, 13, 233, 27, 257, "任务栏暗点切换到任务栏", Sleep);*/

                if (result.sim > 0.95) {
                    mFairy.initMatTime();
                }

                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                if (dazeTime > 5) {

                    result = mFairy.findPic(35, 197, 272, 334, new String[]{"jlzm1.png"});
                    result1 = mFairy.findPic(30, 193, 269, 441, new String[]{"zctj.png"});
                    if (result.sim > 0.75f && result1.sim < 0.75f) {
                        mFairy.onTap(0.75f, result, "左侧九黎之谜", 3000);

                        result = mFairy.findPic(332, 142, 922, 190, "bnxl.png");
                        if (result.sim > 0.75f) {
                            result = mFairy.findPic(202, 251, 278, 417, "qianwang.png");
                            mFairy.onTap(0.7f, result, "九黎之谜任务前往", 1000);
                        }


                        result = mFairy.findPic("jlzmtj.png");
                        mFairy.onTap(0.7f, result, 228, 409, 304, 433, "九黎之谜任务完成", 5000);

                        result = mFairy.findPic("wcrw.png");
                        if (result.sim > 0.75f) {
                            mFairy.onTap(0.7f, result, 244, 554, 286, 571, "九黎之谜任务完成", 5000);
                            setTaskEnd();
                            return;
                        }
                    }

                    result = mFairy.findPic("jlzmtj.png");
                    mFairy.onTap(0.7f, result, 228, 409, 304, 433, "九黎之谜任务完成", 5000);

                    result = mFairy.findPic("wcrw.png");
                    if (result.sim > 0.75f) {
                        mFairy.onTap(0.7f, result, 244, 554, 286, 571, "九黎之谜任务完成", 5000);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(35, 197, 272, 334, new String[]{"jlzm1.png"});
                    if (result.sim > 0.75f) {
                        result = mFairy.findPic(192, 338, 292, 459, new String[]{"jlzmqw1.png", "jlzmqw2.png"});
                        mFairy.onTap(0.9f, result, "前往接取任务", 1000);
                        mFairy.initMatTime();
                        result = mFairy.findPic(331, 78, 553, 210, new String[]{"yddjlc.png"});
                        if (result.sim > 0.75f) {
                            LtLog.e("已到达九黎城");
                            setTaskName(2);
                            return;
                        }

                    } else {
                        LtLog.e("上滑滑动=====");
                        mFairy.ranSwipe(200, 201, 210, 375, 2, 1000, (long) 1000);
                        sum++;
                    }

                    result = mFairy.findPic(1112, 6, 1280, 35, new String[]{"jlc1.png"});
                    if (result.sim > 0.75f) {
                        LtLog.e("已到达九黎城");
                        setTaskName(2);
                        return;
                    }

                    result = mFairy.findPic(57, 380, 491, 586, "jlzmjq.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "接取九黎之谜任务", Sleep);

                        result = mFairy.findPic("jsrw.png");
                        mFairy.onTap(0.8f, result, "接受任务", Sleep);

                    }

                    result = mFairy.findPic(57, 380, 491, 586, "qwjlc.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "前往九黎城", Sleep);
                        setTaskName(2);
                        return;
                    }
                    if (sum >= 5) {
                        sum = 0;
                        LtLog.e("滑动=====");
                        mFairy.ranSwipe(123, 210, 123, 410, 300, Sleep);
                        LtLog.e("滑动=====");
                        mFairy.ranSwipe(123, 210, 123, 410, 300, Sleep);
                    }

                }
                if (dazeTime > 60) {
                    setTaskName(4);
                }
            }

            public void content_2() throws Exception {

                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.95f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                /*result = mFairy.findPic("mtjf.png");
                mFairy.onTap(0.8f, result, "交付密探任务", Sleep);
                if (result.sim > 0.8f){
                    mFairy.initMatTime();
                }*/

                result = mFairy.findPic(51, 374, 498, 486, "qwjlc.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "前往九黎城", Sleep);
                }

                result = mFairy.findPic(6, 245, 1194, 484, "death.png");
                mFairy.onTap(0.8f, result, "复活", Sleep);

                result = mFairy.findPic("skip.png");
                mFairy.onTap(0.8f, result, "err跳过", Sleep);

                result2 = mFairy.findPic(810, 260, 913, 377, "cz.png");
                mFairy.onTap(0.7f, result2, 867, 306, 878, 323, "操作", 5000);

                result = mFairy.findPic(31, 194, 282, 451, "jlwc1.png");
                mFairy.onTap(0.8f, result, "九黎之谜提交前往", 5000);

                /**
                 *
                 */
                //result = mFairy.findPic(61, 378, 491, 582, "zctj.png");
                result = mFairy.findPic(31, 194, 282, 451, "zctj.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.7f, result, "九黎之谜提交前往", 8000);
                    setTaskName(3);
                    return;
                }
                result2 = mFairy.findPic(308, 73, 515, 164, "dhd.png");
                result3 = mFairy.findPic(308, 73, 515, 164, "eh.png");
                if (result2.sim > 0.7f || result3.sim > 0.7f) {
                    mFairy.initMatTime();
                }
                if (dazeTime > 4 && dazeTime < 10) {

                    result = mFairy.findPic(796, 8, 1115, 157, new String[]{"daily.png", "fuli.png"});
                    mFairy.onTap(0.8f, result, 1181, 71, 1203, 90, "打开地图", Sleep);

                    mFairy.onTap(0.7f, result, 384, 355, 386, 357, "九黎城中心", 3000);

                    mFairy.onTap(0.7f, result, 1241, 39, 1249, 54, "关闭地图", 10000);

                    result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});
                    if (result.sim < 0.8f) {
                        result1 = mFairy.findPic(796, 8, 1115, 157, new String[]{"daily.png", "fuli.png"});
                        if (result1.sim > 0.8f) {
                            mFairy.onTap(1123, 327, 1134, 339, "开启自动战斗", Sleep);
                        }
                    }
                }

                if (dazeTime > 40) {
                    setTaskName(1);
                    return;
                }
            }

            public void content_3() throws Exception {

                result = mFairy.findPic(31, 191, 286, 449, "zctj.png");
                mFairy.onTap(0.7f, result, "九黎之谜提交前往", 5000);

                result = mFairy.findPic("jlzmtj.png");
                mFairy.onTap(0.7f, result, 228, 409, 304, 433, "九黎之谜任务完成", 5000);

                result = mFairy.findPic("wcrw.png");
                mFairy.onTap(0.7f, result, 244, 554, 286, 571, "九黎之谜任务完成", 5000);

                Thread.sleep(2000);

                overtime(10, 99);
            }

            public void content_4() throws Exception {
                if (overtime(20, 0)) {
                    gameUtil.close(1);
                    return;
                }
                int i = 0;
                result = mFairy.findPic("task common.png");
                mFairy.onTap(0.95f, result, "任务栏初始化", Sleep);

                result = mFairy.findPic(796, 8, 1115, 157, new String[]{"daily.png", "fuli.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png", "bahuang.png", "bh.png"});
                    mFairy.onTap(0.8f, result, "有天外山海图标", 1500);

                    result = mFairy.findPic(239, 452, 1075, 659, "smzd2.png");
                    mFairy.onTap(0.8f, result, "神魔之巅", 1000);
                    result = mFairy.findPic(239, 452, 1075, 659, "smzd3.png");
                    mFairy.onTap(0.8f, result, "神魔之巅", 1000);
                    result = mFairy.findPic(444, 112, 821, 228, "zwkq.png");
                    if (result.sim > 0.8f) {
                        LtLog.e("未开启");
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(45, 3, 374, 132, "smzd1.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("有神魔之巅图标"));

                        result1 = mFairy.findPic(1136, 67, 1261, 575, "shijian.png");
                        mFairy.onTap(0.8f, result1, "事件", 5000);

                        result = mFairy.findPic(513, 549, 1046, 674, "yiwancheng.png");
                        if (result.sim < 0.8f) {
                            i++;
                        }
                        if (result.sim < 0.8f && i >= 3) {
                            LtLog.e(mFairy.getLineInfo("没有九黎任务"));
                            setTaskEnd();
                            return;
                        } else {
                            i = 0;
                            mFairy.onTap(0.8f, result, 619, 600, 637, 615, "九黎", Sleep);
                            result1 = mFairy.findPic(366, 64, 960, 219, "cncj.png");
                            if (result1.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("不能参加九黎任务！"));
                                setTaskEnd();
                                return;
                            }
                            mFairy.onTap(0.8f, result, 1233, 57, 1243, 73, "关闭", Sleep);
                            setTaskName(1);
                            return;
                        }
                    }
                }
            }
        }.taskContent(mFairy, "九黎之谜任务中");
    } //九黎之谜

    public void szzyzd() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic(58, 378, 489, 580, "jfzy.png");
                mFairy.onTap(0.8f, result, "交付资源争夺任务", Sleep);
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("szzy").equals("1")) {
                        stopCount++;
                        if (stopCount >= 1) {
                            LtLog.e(mFairy.getLineInfo("完成1次结束"));
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic(7, 202, 1266, 491, new String[]{"death.png", "death2.png"});
                mFairy.onTap(0.85f, result, "复活", Sleep);
                if (result.sim > 0.8f) {
                    zyCount++;
                    if (zyCount > 4) {
                        zyCount = 0;
                    }
                    setTaskName(3);
                }
            }

            public void content_0() throws Exception {
                gameUtil.close(1);
                result = mFairy.findPic(796, 8, 1115, 157, new String[]{"daily.png", "fuli.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png", "bahuang.png", "bh.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("有天外山海图标"));
                        setTaskName(1);
                        return;
                    } else {
                        LtLog.e(mFairy.getLineInfo("没有天外山海图标"));
                        setTaskEnd();
                        return;
                    }
                }
            }

            int zyCount = 0, stopCount = 0, js = 0;

            public void content_1() throws Exception {
                if (overtime(20, 0)) return;
                LtLog.e("第——" + stopCount + "次");

                result = mFairy.findPic(512, 82, 742, 183, "xmtt.png");
                result1 = mFairy.findPic(1108, 0, 1277, 37, "xmld1.png");
                if (result.sim > 0.7f || result1.sim > 0.7f) {
                    gameUtil.close(0);
                    gameUtil.twsh_back();
                }

                result = mFairy.findPic("bag2.png");
                mFairy.onTap(0.9f, result, 1192, 39, 1203, 53, "关闭背包", Sleep);

                result = mFairy.findPic(0, 255, 39, 462, "team.png");
                mFairy.onTap(0.95f, result, 7, 202, 24, 224, "任务栏暗点切换到任务栏", 3000);

                result = mFairy.findPic("task common.png");
                mFairy.onTap(0.95f, result, 192, 176, 211, 186, "氏族栏暗点切换到氏族栏", 3000);

                result1 = mFairy.findPic(new String[]{"shizu.png", "shizu2.png"});
                mFairy.onTap(0.8f, result1, "氏族栏", Sleep);

                result = mFairy.findPic("anquan.png");
                result2 = mFairy.findPic("bianjing3.png");
                if (result2.sim > 0.8f && result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }


                result = mFairy.findPic(796, 8, 1115, 157, new String[]{"daily.png", "fuli.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常"));
                if (result.sim > 0.8f) {
                    err=0;
                    result = mFairy.findPic(37, 198, 279, 343, new String[]{"zyzd.png", "zyzd1.png"});
                    if (result.sim > 0.7f) {
                        mFairy.onTap(0.7f, result, result.x + 172, result.y + 115, result.x + 173, result.y + 116, "左侧资源争夺", 1000);
                        return;
                    }

                    result = mFairy.findPic(34, 198, 281, 429, new String[]{"zyzd.png", "zyzd1.png"});
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("资源争夺往上滑"));
                        mFairy.ranSwipe(200, 204, 210, 401, 2, 1000, (long) 3000);

                    } else {
                        LtLog.e(mFairy.getLineInfo("没有资源争夺滑动"));
                        mFairy.ranSwipe(200, 204, 210, 401, 2, 1000, (long) 2000);
                        js++;
                        if (js >= 20) {
                            LtLog.e(mFairy.getLineInfo("没有资源争夺结束"));
                            js = 0;
                            setTaskEnd();
                            return;
                        }
                    }
                }



                result = mFairy.findPic(377, 205, 444, 460, "zyzdqw.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("金库界面"));
                    LtLog.e(mFairy.getLineInfo("金库界面zyCount=" + zyCount));
                    if (zyCount == 0) {
                        result = mFairy.findPic(379, 214, 450, 259, "qwsz.png");
                        result2 = mFairy.findPic(266, 215, 326, 260, "zd.png");
                        LtLog.e(mFairy.getLineInfo("if zyCount=0 result。sim=" + result.sim));
                        if (result.sim > 0.8f && result2.sim < 0.8f) {
                            mFairy.onTap(401, 229, 420, 239, "前往第一个", Sleep);
                            mFairy.onTap(1019, 130, 1036, 146, "关闭", Sleep);
                            setTaskName(2);
                            return;
                        } else {
                            zyCount++;
                        }
                    }
                    if (zyCount == 1) {
                        result = mFairy.findPic(382, 269, 440, 302, "qwsz.png");
                        result2 = mFairy.findPic(264, 263, 325, 309, "zd.png");
                        if (result.sim > 0.8f && result2.sim < 0.8f) {
                            mFairy.onTap(404, 278, 422, 290, "前往第2个", Sleep);
                            mFairy.onTap(1019, 130, 1036, 146, "关闭", Sleep);
                            setTaskName(2);
                            return;
                        } else {
                            zyCount++;
                        }
                    }
                    if (zyCount == 2) {
                        result = mFairy.findPic(385, 319, 438, 350, "qwsz.png");
                        result2 = mFairy.findPic(262, 310, 325, 357, "zd.png");
                        if (result.sim > 0.8f && result2.sim < 0.8f) {
                            mFairy.onTap(405, 331, 420, 342, "前往第3个", Sleep);
                            mFairy.onTap(1019, 130, 1036, 146, "关闭", Sleep);
                            setTaskName(2);
                            return;
                        } else {
                            zyCount++;
                        }
                    }
                    if (zyCount == 3) {
                        result = mFairy.findPic(384, 366, 438, 401, "qwsz.png");
                        result2 = mFairy.findPic(262, 360, 325, 405, "zd.png");
                        if (result.sim > 0.8f && result2.sim < 0.8f) {
                            mFairy.onTap(404, 377, 422, 389, "前往第4个", Sleep);
                            mFairy.onTap(1019, 130, 1036, 146, "关闭", Sleep);
                            setTaskName(2);
                            return;
                        } else {
                            zyCount++;
                        }
                    }
                    if (zyCount == 4) {
                        result = mFairy.findPic(386, 416, 442, 447, "qwsz.png");
                        result2 = mFairy.findPic(262, 408, 326, 455, "zd.png");
                        if (result.sim > 0.8f && result2.sim < 0.8f) {
                            mFairy.onTap(401, 427, 423, 437, "前往第5个", Sleep);
                            mFairy.onTap(1019, 130, 1036, 146, "关闭", Sleep);
                            setTaskName(2);
                            return;
                        } else {
                            zyCount = 0;
                        }
                    }
                }
            }

            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 3) {
                    mFairy.initMatTime();
                    mFairy.ranSwipe(174, 572, 235, 545, 800, (long) 1000, 2);
                    setTaskName(4);
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(3, 1)) return;
                result2 = mFairy.findPic("bianjing3.png");
                if (result2.sim > 0.8f) {
                    result = mFairy.findPic(52, 18, 1225, 670, "delivery2.png");
                    mFairy.onTap(0.8f, result, result.x + 2, result.y + 153, result.x + 3, result.y + 154, "点击传送使者", Sleep);
                    if (result.sim < 0.8f) {
                        mFairy.ranSwipe(167, 571, 176, 684, 1500, 2000);
                    }

                    result = mFairy.findPic(67, 383, 491, 576, "deliveryyes.png");
                    mFairy.onTap(0.8f, result, "传送", 5000);
                    if (result.sim > 0.8f) {
                        mFairy.initMatTime();
                    }
                }
            }

            public void content_4() throws Exception {
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                result = mFairy.findPic(749, 301, 1050, 566, "zys.png");
                mFairy.onTap(0.8f, result, "采集资源", Sleep);
                if (result.sim > 0.8f) {
                    result2 = mFairy.findPic(372, 113, 571, 208, "zyjs.png");
                    result1 = mFairy.findPic(480, 72, 804, 201, "zjsz.png");
                    if (result1.sim > 0.8f || result2.sim > 0.8f) {
                        zyCount++;
                        if (zyCount > 4) {
                            zyCount = 0;
                        }
                        LtLog.e(mFairy.getLineInfo("无法采集"));
                        setTaskName(1);
                    } else {
                        Thread.sleep(8000);
                    }
                    mFairy.initMatTime();
                    if (AtFairyConfig.getOption("tie").equals("1") || (!AtFairyConfig.getOption("tie").equals("1") && !AtFairyConfig.getOption("tong").equals("1") && !AtFairyConfig.getOption("yin").equals("1") && !AtFairyConfig.getOption("jin").equals("1"))) {
                        setTaskName(1);
                        return;
                    }
                    if (AtFairyConfig.getOption("tong").equals("1")) {
                        result = mFairy.findPic(new String[]{"tong.png", "yin.png", "jin.png"});
                        if (result.sim > 0.8f) {
                            setTaskName(1);
                            return;
                        }
                    }
                    if (AtFairyConfig.getOption("yin").equals("1")) {
                        result = mFairy.findPic(new String[]{"yin.png", "jin.png"});
                        if (result.sim > 0.8f) {
                            setTaskName(1);
                            return;
                        }
                    }
                    if (AtFairyConfig.getOption("jin").equals("1")) {
                        result = mFairy.findPic("jin.png");
                        if (result.sim > 0.8f) {
                            setTaskName(1);
                            return;
                        }
                    }
                } else if (dazeTime > 30) {
                    setTaskName(1);
                    return;
                }


            }
        }.taskContent(mFairy, "氏族资源争夺中");
    } //氏族资源争夺

    public void mrjc() throws Exception {
        new SingleTask(mFairy) {
            int mtCount = 0;
            int stopCount = 0;

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                result = mFairy.findPic(796, 8, 1115, 157, new String[]{"daily.png", "fuli.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png", "bahuang.png", "bh.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("有天外山海图标"));
                        mFairy.initMatTime();
                        result = mFairy.findPic("put.png");
                        mFairy.onTap(0.8f, result, "普通", Sleep);
                        setTaskName(1);
                        return;
                    } else {
                        LtLog.e(mFairy.getLineInfo("没有天外山海图标"));
                        setTaskEnd();
                        return;
                    }
                }
            }

            public void content_1() throws Exception {
                result = mFairy.findPic("bag2.png");
                mFairy.onTap(0.9f, result, 1189, 44, 1203, 51, "背包关闭", Sleep);

                LtLog.e("-----------==" + mtCount);
                result = mFairy.findPic(0, 255, 39, 462, "team.png");
                mFairy.onTap(0.95f, result, 13, 233, 27, 257, "任务栏暗点切换到任务栏", Sleep);

                result = mFairy.findPic("task common.png");
                mFairy.onTap(0.95f, result, 186, 175, 217, 185, "氏族栏暗点切换到氏族栏", Sleep);

                result = mFairy.findPic(new String[]{"shizu.png", "shizu2.png"});
                mFairy.onTap(0.8f, result, "氏族栏", Sleep);

                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 10) {
                    if (mtCount == 2) {

                        result = mFairy.findPic(54, 71, 159, 135, "moshi.png");
                        result1 = mFairy.findPic(28, 53, 197, 153, "peace.png");
                        if (result.sim > 0.8f && result1.sim < 0.8f) {
                            mFairy.onTap(0.8f, result, "切换下模式", Sleep);
                            mFairy.onTap(0.8f, result, 517, 148, 529, 155, "和平模式", Sleep);
                        }
                        result = mFairy.findPic(1080, 278, 1195, 390, "NoBattle.png");
                        mFairy.onTap(0.8f, result, 1136, 331, 1141, 335, "开启自动战斗", Sleep);
                    }
                }/*else {
                    result = mFairy.findPic("jiancai.png");
                    result1=mFairy.findPic("shizu.png");
                    if (result.sim < 0.7f && result1.sim >0.8f){
                        mFairy.ranSwipe(90,434,129,268,1000,1000);
                    }
                }*/

                result = mFairy.findPic(36, 196, 277, 348, new String[]{"jiancai.png", "jiancai3.png", "jiancai4.png"});
                LtLog.e("/*******asdf*********/" + result.sim);
                if (mtCount == 2) {

                } else {
                    mFairy.onTap(0.7f, result, "左侧建材密探", Sleep);
                }
                if (result.sim > 0.7f) {
                    // mFairy.initMatTime();
                    if (mtCount == 0) {
                        result = mFairy.findPic(230, 298, 280, 331, new String[]{"go.png", "go2.png"});
                        mFairy.onTap(0.7f, result, "第1个前往", Sleep);
                        result = mFairy.findPic(243, 297, 284, 328, "ok.png");
                        if (result.sim > 0.7f) {
                            mtCount++;
                        }
                    }
                    if (mtCount == 1) {
                        result = mFairy.findPic(225, 322, 281, 355, new String[]{"go.png", "go2.png"});
                        mFairy.onTap(0.7f, result, "第2个前往", Sleep);
                        result = mFairy.findPic(240, 321, 281, 349, "ok.png");
                        if (result.sim > 0.7f) {
                            mtCount++;
                        }
                    }
                    if (mtCount == 2) {
                        result2 = mFairy.findPic(1099, 277, 1173, 348, "Hangup1.png");
                        if (result2.sim > 0.8f) {

                        } else {
                            result = mFairy.findPic(230, 343, 282, 378, new String[]{"go.png", "go2.png"});
                            mFairy.onTap(0.7f, result, "第3个前往", Sleep);
                        }
                        result = mFairy.findPic(244, 342, 279, 373, "ok.png");
                        if (result.sim > 0.7f) {
                            mtCount = 0;
                        }
                    }

                } else {
                    LtLog.e(mFairy.getLineInfo("上滑"));
                    mFairy.ranSwipe(200, 204, 210, 401, 2, 1000, (long) 1000);
                }

                result = mFairy.findPic("zys.png");
                //mFairy.onTap(0.8f, result, "采集资源", Sleep);
                if (result.sim > 0.8f && dazeTime > 2) {
                    //mFairy.initMatTime();
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic("jiancai2.png");
                mFairy.onTap(0.8f, result, "接取任务", Sleep);

                result = mFairy.findPic("shangjiao2.png");
                mFairy.onTap(0.8f, result, "交付任务", Sleep);

                result = mFairy.findPic("shangjiao3.png");
                mFairy.onTap(0.8f, result, "上缴", Sleep);
                if (result.sim > 0.8f) {
                    mtCount = 0;
                    if (AtFairyConfig.getOption("two").equals("1")) {
                        stopCount++;
                        if (stopCount >= 2) {
                            LtLog.e(mFairy.getLineInfo("完成2次结束"));
                            setTaskEnd();
                            return;
                        }
                    }
                    if (AtFairyConfig.getOption("ten").equals("1")) {
                        stopCount++;
                        if (stopCount >= 10) {
                            LtLog.e(mFairy.getLineInfo("完成10次结束"));
                            setTaskEnd();
                            return;
                        }
                    }
                }
                result = mFairy.findPic(new String[]{"shangjiao.png", "jiao.png"});
                mFairy.onTap(0.68f, result, "上缴建材", Sleep);
                if (result.sim > 0.8f) {
                    mtCount = 10;
                }

                result = mFairy.findPic(6, 245, 1194, 484, "death.png");
                mFairy.onTap(0.8f, result, "复活", Sleep);


                if (dazeTime > 35) {
                    if (mtCount == 10) {
                        mtCount = 0;
                    }
                    mFairy.initMatTime();
                    result = mFairy.findPic(37, 202, 270, 321, new String[]{"jiancai.png", "jiancai3.png", "jiancai4.png"});
                    result1 = mFairy.findPic("shizu.png");
                    result2 = mFairy.findPic("bianjing3.png");
                    LtLog.e("/****************/" + result.sim);
                    if (result.sim < 0.7f && result1.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("左侧没有建材结束"));
                        setTaskEnd();
                        return;
                    } else if (result2.sim > 0.8f) {
                        setTaskName(2);
                        return;
                        //setTaskName(0);return;
                    }
                }

                result1 = mFairy.findPic(476, 110, 817, 231, "mddyq.png");
                if (result1.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(4, 0)) return;
                result = mFairy.findPic(52, 18, 1225, 670, "delivery2.png");
                mFairy.onTap(0.8f, result, result.x + 2, result.y + 153, result.x + 3, result.y + 154, "点击传送使者", Sleep);
                if (result.sim < 0.8f) {
                    if (result.sim < 0.8f) {
                        mFairy.onTap(1199, 89, 1206, 99, "地图", Sleep);
                        mFairy.onTap(579, 505, 582, 510, "地图", Sleep);
                        mFairy.onTap(1243, 46, 1250, 54, "地图", Sleep);
                    }
                }

                result = mFairy.findPic(61, 381, 492, 580, "deliveryyes.png");
                mFairy.onTap(0.8f, result, "传送", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                }
            }

            public void content_3() throws Exception {
                result = mFairy.findPic("zys.png");
                mFairy.onTap(0.8f, result, "采集资源", 5000);
                if (result.sim < 0.8f) {
                    setTaskName(1);
                    if (mtCount < 2) {
                        mtCount++;
                    }
                    mFairy.initMatTime();
                    return;
                }

            }
        }.taskContent(mFairy, "每日建材");
    } //每日建材

    public void wk() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(1, 631, 91, 712, "Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result = mFairy.findPic("shenqi.png");
                mFairy.onTap(0.8f, result, "打开神器", Sleep);


                result = mFairy.findPic(1130, 67, 1270, 305, "wakuang.png");
                mFairy.onTap(0.8f, result, "挖矿", Sleep);

                result1 = mFairy.findPic(854, 72, 1081, 105, "mianfei.png");
                if (result1.sim > 0.8f) {
                    result = mFairy.findPic(424, 86, 1057, 554, "box.png");
                    mFairy.onTap(0.8f, result, "挖矿2", 2000);
                }

                result1 = mFairy.findPic(789, 56, 1123, 113, "jinbi.png");
                if (result1.sim > 0.8f) {
                    LtLog.e("要花钱了结束");
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(551, 89, 747, 233, "sqwkq.png");
                if (result.sim > 0.8f) {
                    LtLog.e("还没开启神器");
                    setTaskEnd();
                    return;
                }

            }
        }.taskContent(mFairy, "挖矿");
    } //神器挖矿

    public void zhzs() throws Exception {
        new SingleTask(mFairy) {
            int num = 0;
            int jc = 0;

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                result = mFairy.findPic(0, 255, 39, 462, "team.png");
                mFairy.onTap(0.95f, result, 15, 234, 29, 262, "在队伍栏切换到任务栏", Sleep);

                result = mFairy.findPic("put.png");
                mFairy.onTap(0.8f, result, "普通", Sleep);

                result = mFairy.findPic(35, 167, 262, 445, new String[]{"zhanhuo.png", "zhanhuo2.png"});
                mFairy.onTap(0.85f, result, "左侧战火正盛", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                } else if (num >= 0 && num < 2) {
                    LtLog.e("777777");
                    num++;
                    mFairy.ranSwipe(200, 205, 200, 401, 500, 1500);
                } else {
                    LtLog.e("----");
                    num++;

                    mFairy.ranSwipe(200, 401, 200, 204, 500, 1500);
                }

                result = mFairy.findPic("smOverGraph.png");
                if (result.sim > 0.8f) {
                    num = 0;
                }
                if (num > 7) {
                    setTaskEnd();
                    return;
                }

            }

            public void content_3() throws Exception {
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 10) {
                    mFairy.initMatTime();
                    setTaskName(0);
                    num = 0;
                    return;
                }

                result = mFairy.findPic(141, 516, 446, 620, "xmrwjs.png");
                mFairy.onTap(0.8f, result, "接受", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    err = 0;
                }

                result1 = mFairy.findPic(164, 508, 404, 622, "xmrwwc.png");
                mFairy.onTap(0.8f, result1, "完成", Sleep);
                jc = picCount(0.8f, result1, "完成");
                if (jc >= 10) {
                    mFairy.initMatTime();
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy, "战火正盛");
    }//战火正盛

    public void cw() throws Exception {
        new SingleTask(mFairy) {
            int num = 0;

            @Override
            public void create() throws Exception {
                if (!AtFairyConfig.getOption("opcount1").equals("")) {
                    if (strSplit(AtFairyConfig.getOption("opcount1")).choice == 1) {
                        num = strSplit(AtFairyConfig.getOption("opcount1")).count;
                    } else {
                        LtLog.e("没有设定次数结束");
                        setTaskEnd();
                        return;
                    }
                }

            }

            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {

                result = mFairy.findPic(1, 631, 91, 712, "Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result = mFairy.findPic(34, 6, 304, 89, "baginface.png");
                if (result.sim > 0.8f) {
                    gameUtil.close(1);
                }
                result = mFairy.findPic("pic lock.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("灵宠没有解锁，任务结束"));
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("Spoil.png");
                mFairy.onTap(0.8f, result, "灵宠", Sleep);

                result = mFairy.findPic(33, 570, 153, 695, "Spoilshop.png");
                mFairy.onTap(0.8f, result, "灵宠商店", Sleep);

                result = mFairy.findPic(104, 100, 826, 557, "Wolf baby.png");
                mFairy.onTap(0.8f, result, "狼宝宝", 500);

                result = mFairy.findPic(849, 550, 1138, 664, "buy.png");

                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "购买", 500);
                    mFairy.onTap(0.8f, result, 769, 432, 779, 438, "购买确定", 500);
                    LtLog.e("还剩" + --num + "个");
                }
                if (num <= 0) {
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic(475, 114, 795, 218, "full2.png");
                if (result.sim > 0.8f) {
                    LtLog.e("买满了");
                    gameUtil.close(1);
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {

                if (overtime(15, 1)) {
                    gameUtil.close(0);
                    return;
                }

                result = mFairy.findPic(796, 8, 1115, 157, new String[]{"daily.png", "fuli.png"});
                mFairy.onTap(0.8f, result, 1071, 110, 1087, 121, "打开背包", 3000);

                result = mFairy.findPic(34, 6, 304, 89, "baginface.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(1078, 75, 1135, 132, "zuozuo.png");
                    mFairy.onTap(0.8f, result, "宠物", Sleep);

                    result = mFairy.findPic(899, 56, 1101, 142, "Pets.png");
                    mFairy.onTap(0.8f, result, "宠物", Sleep);
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 977, 628, 1017, 643, "分解", Sleep);
                        mFairy.onTap(0.8f, result, 825, 630, 892, 650, "自动选择", Sleep);
                        result1 = mFairy.findPic(719, 259, 857, 326, "gouxuan.png");
                        mFairy.onTap(0.8f, result1, 750, 285, 761, 300, "蓝色取消", Sleep);

                        result1 = mFairy.findPic(719, 259, 857, 326, "gouxuan.png");
                        mFairy.onTap(0.8f, result1, 750, 285, 761, 300, "蓝色取消", Sleep);

                        mFairy.onTap(0.8f, result, 736, 506, 789, 525, "确认选择", Sleep);
                        result1 = mFairy.findPic("zise.png");
                        if (result1.sim > 0.8f) {
                            mFairy.onTap(0.8f, result1, 744, 423, 818, 458, "紫色确认分解", Sleep);
                        }
                        result2 = mFairy.findPic(586, 256, 724, 384, "chengse.png");
                        if (result2.sim > 0.8f) {
                            mFairy.onTap(0.8f, result2, 489, 425, 546, 456, "有橙色取消分解", Sleep);
                        }
                        mFairy.onTap(0.8f, result, 305, 619, 341, 636, "确认分解", Sleep);
                        result = mFairy.findPic("baginface.png");
                        for (int i = 0; i < 3; i++) {
                            mFairy.condit();
                            result = mFairy.findPic(258, 241, 1010, 612, "bagquxiao.png");
                            mFairy.onTap(0.8f, result, "清背包err取消", Sleep);
                        }
                        gameUtil.close(0);
                        if (num <= 0) {
                            setTaskEnd();
                            return;
                        }
                        setTaskName(1);
                        return;
                    }
                }
            }
        }.taskContent(mFairy, "宠物材料");
    } //宠物材料

    void test() throws Exception {
        while (mFairy.condit()) {
            mFairy.sleep(1000);
            LtLog.e(mFairy.getLineInfo("\nwhile。。。。"));

            result = mFairy.findPic("task common.png");
            LtLog.e(mFairy.getLineInfo("普通 hls=" + result.toString()));


            LtLog.e(mFairy.getLineInfo("队伍 hls=" + result.toString()));
//            result=mFairy.findPic("pic pet tanxian interface.png");
//            LtLog.e(mFairy.getLineInfo("灵宠探险界面 result="+result.toString()));
        }
    }

    void lineTest() throws Exception {
        while (mFairy.condit()) {
            mFairy.sleep(1000);
            LtLog.e(mFairy.getLineInfo("\n\ntest while..........."));
//            result = mFairy.findPic(281,198,1036,684,"qluse.png");
//            LtLog.e(mFairy.getLineInfo("result="+result.toString()));

        }
    }


    public void keju2() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("kjExamination.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            int x = 0;

            public void content_2() throws Exception {
                LtLog.e("e=" + x);
                result = mFairy.findPic(619, 546, 1178, 675, "kjStartAnswer.png");
                mFairy.onTap(0.8f, result, "开始答题", Sleep);
                result = mFairy.findPic(796, 8, 1115, 157, new String[]{"daily.png", "fuli.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("在桌面  重进");
                    setTaskName(1);
                }
                gameUtil.srAIAnswer();
                result = mFairy.findPic("kjSignOut.png");
                mFairy.onTap(0.8f, result, "答题退出", Sleep);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                }

                result = mFairy.findPic(220, 59, 764, 462, new String[]{"wfsm.png", "zql.png"});
                if (result.sim > 0.8f) {
                    x = 0;
                } else {
                    x++;
                }
                if (x >= 20) {
                    gameUtil.close(1);
                    setTaskName(1);
                }
            }
        }.taskContent(mFairy, "科举乡试任务中");
    }//科举乡试

    public void ymzj() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("ymzj.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                result = mFairy.findPic("tz.png");
                mFairy.onTap(0.8f, result, "挑战", Sleep);

                result = mFairy.findPic("begintz.png");
                mFairy.onTap(0.8f, result, "开始挑战", Sleep);

                result = mFairy.findPic(1099, 277, 1173, 348, "NoBattle.png");
                mFairy.onTap(0.8f, result, "开启自动战斗", Sleep);

                result = mFairy.findPic(874, 593, 1035, 641, "tzfive.png");
                mFairy.onTap(0.8f, result, "挑战多层", Sleep);

                result = mFairy.findPic("yesjl.png");
                mFairy.onTap(0.8f, result, "确认奖励", Sleep);

                result = mFairy.findPic("again.png");
                mFairy.onTap(0.8f, result, "再次挑战", Sleep);

                result = mFairy.findPic("onlyone.png");
                mFairy.onTap(0.8f, result, 771, 425, 778, 441, "确定最后一次", Sleep);

                result = mFairy.findPic(new String[]{"over2.png", "over3.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("每次数了结束");
                    setTaskEnd();
                }
            }
        }.taskContent(mFairy, "遗民战境");
    }//遗民战境

    public void buhuo() throws Exception {
        new TimingActivity(mFairy) {
            public void inOperation() throws Exception {
                result = mFairy.findPic("word hint enter capture.png");
                mFairy.onTap(0.8f, result, 502, 438, 510, 447, "消耗次数，进入捕获界面，确定", 1000);
                super.inOperation();
            }

            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                overtime(30, 0);

                result = mFairy.findPic("Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result = mFairy.findPic("pic horse.png");
                mFairy.onTap(0.8f, result, "进入坐骑", Sleep);

                //
                result = mFairy.findPic("word horse interface.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("坐骑界面"));

                    result = mFairy.findPic("pic horse buhuo.png");
                    mFairy.onTap(0.8f, result, "切换捕获分页", Sleep);

                    result = mFairy.findPic("word first free.png");
                    mFairy.onTap(0.8f, result, 589, 626, 594, 634, "探索，首次免费", 2000);

                    result = mFairy.findPic("word btn enter capture.png");
                    mFairy.onTap(0.8f, result, "进入捕获", 1000);

                    result = mFairy.findPic("word horse 100 jinpiao.png");
                    mFairy.onTap(0.8f, result, 1154, 58, 1163, 65, "捕获完成，关闭", 1000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("任务完成"));
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("pic copy leave.png");
                mFairy.onTap(0.8f, result, "离开副本", 1000);
            }

        }.taskContent(mFairy, "捕获坐骑");
    }//捕获坐骑

    public void chired() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("chirdsl.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                result = mFairy.findPic(739, 597, 886, 663, "ttSweep.png");
                mFairy.onTap(0.8f, result, "一键扫荡", Sleep);
                mFairy.onTap(0.8f, result, 753, 431, 790, 446, "一键扫荡确定", 10000);
                setTaskName(3);
                return;
            }

            public void content_3() throws Exception {
                overtime(30, 0);

                result = mFairy.findPic("zddzgc.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("已达到最高层"));
                    setTaskEnd();
                }

                result = mFairy.findPic("begintz2.png");
                mFairy.onTap(0.8f, result, "开始挑战", Sleep);

                result = mFairy.findPic("need2.png");
                if (result.sim > 0.8f) {
                    LtLog.e("没有子女");
                    setTaskEnd();
                }

                result = mFairy.findPic("Replica.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(1099, 277, 1173, 348, "NoBattle.png");
                    mFairy.onTap(0.8f, result, "开启自动战斗", Sleep);
                }
                result = mFairy.findPic(876, 601, 1040, 652, "Continue.png");
                mFairy.onTap(0.8f, result, "继续挑战", Sleep);

              /*  result = mFairy.findPic( "quer.png");
                mFairy.onTap(0.8f, result, "确认", Sleep);*/

                result = mFairy.findPic(613, 189, 839, 236, "buff.png");
                mFairy.onTap(0.8f, result, 448, 461, 453, 465, "buff", Sleep);
                mFairy.onTap(0.8f, result, 625, 530, 638, 541, "buff确认", Sleep);

             /*   result = mFairy.findPic("ttSure.png");
                mFairy.onTap(0.8f, result, "只剩一次机会确定", Sleep);*/

                result = mFairy.findPic("iknow2.png");
                mFairy.onTap(0.8f, result, "知道了", Sleep);
                if (result.sim > 0.8f) {
                    mFairy.ranSwipe(173, 583, 76, 455, 3000, 1200);
                }
                result = mFairy.findPic("leave.png");
                mFairy.onTap(0.8f, result, "离开", 1000);
                if (result.sim > 0.8f) {
                    setTaskEnd();
                }
            }
        }.taskContent(mFairy, "子女试炼任务中");
    }//子女试炼

}
