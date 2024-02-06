package com.script.fairy;

import com.example.publicfunctionlibrary.FunctionClass;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/5/21.
 */

public class LimitlessTask {
    private GamePublicFunction gamePublicFunction;
    private PublicFunction publicFunction;
    private FindResult findResult;
    //    private long time = System.currentTimeMillis() / 1000, timex = 0;
    private AtFairyImpl mFairy;
    //    private boolean location = true;
    private AtFairy2.OpencvResult result;
    private TimingActivity timingActivity;
    private SingleTask singleTask;
    private int QSHL_index = 0;
    private final int MAP_YLFX = 7;
    public int[] map_xy; //小地图在大地图中的坐标,用这个变量记住,之后点大地图的固定位置即可
    private List<String> map_name_list = new ArrayList<>();
    private List<String> current_map_name_list = new ArrayList<>();
    protected FunctionClass functionClass;
    public LimitlessTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
//        publicFunction=TaskMain.publicFunction;
        gamePublicFunction = new GamePublicFunction(mFairy);
        timingActivity = new TimingActivity(mFairy);
        singleTask = new SingleTask(mFairy);
        initMapNameList();
        functionClass = new FunctionClass(mFairy,mFairy.getContext());
    }

    private void initMapNameList() {
        //在大地图中的地图
        map_name_list.add("MAP_YDS.png");//0雁荡山,
        map_name_list.add("MAP_WYS.png");//1武夷山
        map_name_list.add("MAP_SYY.png");//2锁云渊,
        map_name_list.add("MAP_DTHP.png");//3洞庭湖畔
        map_name_list.add("MAP_ML.png");//4苗岭
        map_name_list.add("MAP_DCS.png");//5点苍山
        map_name_list.add("MAP_YLFX.png");//6夜郎废墟
        map_name_list.add("MAP_XSD.png");//7响水洞
        map_name_list.add("MAP_JMG.png");//8剑门关
        map_name_list.add("MAP_JXF.png");//9见性峰
        map_name_list.add("MAP_FLD.png");//10风陵渡
        map_name_list.add("MAP_JJD.png");//11荐菊洞
        map_name_list.add("MAP_GZC.png");//12古战场
        map_name_list.add("MAP_FNS.png");//13伏牛山
        map_name_list.add("MAP_THGJ.png");//14太行古径
        map_name_list.add("MAP_QLS.png");//15祁连山
        map_name_list.add("MAP_SMYJ.png");//16沙漠遗迹
        map_name_list.add("MAP_DHGC.png");//17敦煌古城
        map_name_list.add("MAP_KXMZ.png");//18昆虚脉藏
        map_name_list.add("MAP_YWG.png");//19药王谷
        map_name_list.add("MAP_MBCY.png");//20漠北草原
        map_name_list.add("MAP_CBS.png");//21长白山
        map_name_list.add("MAP_CHTC.png");//22残桓铁城
        map_name_list.add("MAP_XXHL.png");//23西夏皇陵
        map_name_list.add("MAP_JYZ.png");//24居延泽
        map_name_list.add("MAP_LMKZ.png");//25龙门客栈
        map_name_list.add("MAP_PQDY.png");//26蓬丘岱屿
        map_name_list.add("MAP_WSXL.png");//27雾凇雪岭
        map_name_list.add("MAP_JYG.png");//28居庸关
        map_name_list.add("MAP_CMSD.png");//29茶马商道
        map_name_list.add("MAP_ZZDK.png");//30震泽渡口
        map_name_list.add("MAP_LLGG.png");//31楼兰古国
        map_name_list.add("MAP_SBXY.png");//32塑北雪原
        map_name_list.add("MAP_PL_ZS.png");//33蓬莱-宗师
        map_name_list.add("MAP_YZ_ZS.png");//34瀛洲-宗师
        map_name_list.add("MAP_FH_ZS.png");//35方壶-宗师
        map_name_list.add("MAP_YQ_ZS.png");//36员峤-宗师
        map_name_list.add("MAP_TY_ZS.png");//37泰远-宗师
        map_name_list.add("MAP_YY_ZS.png");//38炎州-宗师
        map_name_list.add("MAP_CZ_ZS.png");//39长洲-宗师
        map_name_list.add("MAP_LZ_ZS.png");//40流洲-宗师
        map_name_list.add("MAP_JKZ_ZS.png");//41聚窟州-宗师


        map_name_list.add("MAP_CZZS_ZS.png");//42沧州-宗师
        map_name_list.add("MAP_BZS.png");//43不周山
        map_name_list.add("MAP_RYS.png");//44日月山
        map_name_list.add("MAP_YMS.png");//45玉门山



        //右上角的地图名称 图
        current_map_name_list.add("CURRENT_MAP_YDS.png");//0雁荡山,
        current_map_name_list.add("CURRENT_MAP_WYS.png");//1武夷山
        current_map_name_list.add("CURRENT_MAP_SYY.png");//2锁云渊,
        current_map_name_list.add("CURRENT_MAP_DTHP.png");//3洞庭湖畔
        current_map_name_list.add("CURRENT_MAP_ML.png");//4苗岭
        current_map_name_list.add("CURRENT_MAP_DCS.png");//5点苍山
        current_map_name_list.add("CURRENT_MAP_YLFX.png");//6夜郎废墟
        current_map_name_list.add("CURRENT_MAP_XSD.png");//7响水洞
        current_map_name_list.add("CURRENT_MAP_JMG.png");//8剑门关
        current_map_name_list.add("CURRENT_MAP_JXF.png");//9见性峰
        current_map_name_list.add("CURRENT_MAP_FLD.png");//10风陵渡
        current_map_name_list.add("CURRENT_MAP_JJD.png");//11荐菊洞
        current_map_name_list.add("CURRENT_MAP_GZC.png");//12古战场
        current_map_name_list.add("CURRENT_MAP_FNS.png");//13伏牛山
        current_map_name_list.add("CURRENT_MAP_THGJ.png");//14太行古径
        current_map_name_list.add("CURRENT_MAP_QLS.png");//15祁连山
        current_map_name_list.add("CURRENT_MAP_SMYJ.png");//16沙漠遗迹
        current_map_name_list.add("CURRENT_MAP_DHGC.png");//17敦煌古城
        current_map_name_list.add("CURRENT_MAP_KXMZ.png");//18昆虚脉藏
        current_map_name_list.add("CURRENT_MAP_YWG.png");//19药王谷
        current_map_name_list.add("CURRENT_MAP_MBCY.png");//20漠北草原
        current_map_name_list.add("CURRENT_MAP_CBS.png");//21长白山
        current_map_name_list.add("CURRENT_MAP_CHTC.png");//22残桓铁城
        current_map_name_list.add("CURRENT_MAP_XXHL.png");//23西夏皇陵
        current_map_name_list.add("CURRENT_MAP_JYZ.png");//24居延泽
        current_map_name_list.add("CURRENT_MAP_LMKZ.png");//25龙门客栈
        current_map_name_list.add("CURRENT_MAP_PQDY.png");//26蓬丘岱屿
        current_map_name_list.add("CURRENT_MAP_WSXL.png");//27雾凇雪岭
        current_map_name_list.add("CURRENT_MAP_JYG.png");//28居庸关
        current_map_name_list.add("CURRENT_MAP_CMSD.png");//29茶马商道
        current_map_name_list.add("CURRENT_MAP_ZZDK.png");//30震泽渡口
        current_map_name_list.add("CURRENT_MAP_LLGG.png");//31楼兰古国
        current_map_name_list.add("CURRENT_MAP_SBXY.png");//32塑北雪原
        current_map_name_list.add("CURRENT_MAP_PL_ZS.png");//33蓬莱-宗师
        current_map_name_list.add("CURRENT_MAP_YZ_ZS.png");//34瀛洲-宗师
        current_map_name_list.add("CURRENT_MAP_FH_ZS.png");//35方壶-宗师
        current_map_name_list.add("CURRENT_MAP_YQ_ZS.png");//36员峤-宗师
        current_map_name_list.add("CURRENT_MAP_TY_ZS.png");//37泰远-宗师
        current_map_name_list.add("CURRENT_MAP_YY_ZS.png");//38炎州-宗师
        current_map_name_list.add("CURRENT_MAP_CZ_ZS.png");//39长洲-宗师
        current_map_name_list.add("CURRENT_MAP_LZ_ZS.png");//40流洲-宗师
        current_map_name_list.add("CURRENT_MAP_JKZ_ZS.png");//41聚窟州-宗师
        current_map_name_list.add("CURRENT_MAP_CZZS_ZS.png");//42沧州-宗师
        current_map_name_list.add("CURRENT_MAP_BZS.png");//43不周山
        current_map_name_list.add("CURRENT_MAP_RYS.png");//44日月山
        current_map_name_list.add("CURRENT_MAP_YMS.png");//45玉门山
        current_map_name_list.add("mainCity1.png");//襄阳
        current_map_name_list.add("mainCity.png");//临安
    }

    public int outdoorsOnHook() throws Exception {

        if (TaskMain.taskMap.get("OnHookMap") == 999) {
            LtLog.i(publicFunction.getLineInfo() + "【勾选的侠客岛挂机】");
            //侠客岛挂机的无限任务
            result = publicFunction.localFindPic(1164, 0, 1279, 36, "xiakeIsland.png");
            if (result.sim < 0.8) {
                gamePublicFunction.goMainCity();//在家园或者在家族是不能进入侠客岛的,所以先回到主城.
            }

            XiakeIsland();//侠客岛挂机
        }

        AtFairy2.OpencvResult result;
        long time = System.currentTimeMillis() / 1000, timex = 0;
        long time1 = System.currentTimeMillis() / 1000, time1x = 0;
        long time2 = (System.currentTimeMillis() / 1000) - 3600, time2x = 0;
        long recall_time = (System.currentTimeMillis() / 1000 / 60) - TaskMain.taskMap.get("recall_time"), recall_timex = 0;
        long deliverTargetMap_time = System.currentTimeMillis() / 1000, deliverTargetMap_timex = 0;//记录两次定位的间隔  ，如果连续1分钟内连续调用，则 更新识别坐标。
        boolean location = true;

        LtLog.i(publicFunction.getLineInfo() + "setSkill=" + TaskMain.taskMap.get("setSkill"));// 1为 不勾选技能 0为默认

        if (TaskMain.taskMap.get("setSkill") == 0) {
            gamePublicFunction.setSkill();
        }

        boolean discriminate = true;

        int[] xy = {Integer.parseInt(TaskMain.xystr.split(",")[0]), Integer.parseInt(TaskMain.xystr.split(",")[1])};

        while (mFairy.condit()) {

            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 300) {
//              location = true;
                time = System.currentTimeMillis() / 1000;
            } else if (timex % 60 >= 0 && timex % 60 <= 3) {
                LtLog.i(publicFunction.getLineInfo() + "无限挂机中：" + "定位时间：" + time1x + ",当前时间为：" + publicFunction.getMinuteNumber() + "星期：" + publicFunction.getCurrSun() + ",Data:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                LtLog.i(publicFunction.getLineInfo() + "--------->>" + ",TaskMain.taskMap=" + TaskMain.taskMap);
                LtLog.i(publicFunction.getLineInfo() + "---------time1x=" + time1x + ",location=" + location);
            }

            mAimingActivity();//定时活动

            time1x = System.currentTimeMillis() / 1000 - time1;
            if (time1x >= 300) {
                location = true;//重新定位
                time1 = System.currentTimeMillis() / 1000;
            }

//            result = publicFunction.localFindPic(1138, 0, 1280, 80, "mainCity1.png|mainCity.png");
//            if (result.sim >= 0.8) {
//                LtLog.i(publicFunction.getLineInfo() + "---------time1x=" + time1x + ",location=" + location);
//                location = true;
//            }

            if (!judgeMap(TaskMain.taskMap.get("OnHookMap"))) {
                LtLog.i(publicFunction.getLineInfo() + "---------time1x=" + time1x + ",location=" + location);
                location = true;
            }

            if (location && TaskMain.taskMap.get("OnHookMap") > 0 && xy[0] >= -1) {

                LtLog.i(publicFunction.getLineInfo() + "【开始定位】");

                location = false;

                result = publicFunction.localFindPic(1112, 171, 1233, 244, "current.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【在秘境或者凌绝峰】");
                    continue;
                }

                result = publicFunction.localFindPic(1140, 1, 1279, 44, "wsmk1.png");
                if (result.sim >= 0.8) {
                    //在山贼秘窟
                    LtLog.i(publicFunction.getLineInfo() + "【五色秘窟】");
                    continue;
                }

                if (!judgeMap(TaskMain.taskMap.get("OnHookMap"))) {

                    deliverTargetMap_timex = System.currentTimeMillis() / 1000 - deliverTargetMap_time;
                    LtLog.i(publicFunction.getLineInfo() + "---------deliverTargetMap_timex=" + deliverTargetMap_timex);
                    if (deliverTargetMap_timex < 120) {
                        LtLog.i(publicFunction.getLineInfo() + "【两次定位的间隔小于120s，重新 获取目标地图在世界地图中的位置】");
                        //两次定位的间隔小于120s，重新 获取目标地图在世界地图中的位置
                        map_xy = null;
                    }

                    deliverTargetMap_time = System.currentTimeMillis() / 1000;

                    deliverTargetMap();//传送到目标地图
                }

                gamePublicFunction.openMapWorldOrCurrent("current");//打开世界地图还是当前地图

                int[] click_screen_xy = screenXY(xy[0], xy[1], TaskMain.taskMap.get("OnHookMap"));//点击地图坐标

                gamePublicFunction.closeWindow();

                for (int i = 0; i < 10; i++) {
                    if (publicFunction.judgeMatChange(1177, 136, 70, 21, 0.95, 2000) == false) {
                        LtLog.i(publicFunction.getLineInfo() + "【到达坐标地点,开始挂机】");
                        break;
                    }
                    Thread.sleep(1000);
                }

//                if (discriminate) {
//                    //识别当前坐标与用户设置坐标是否匹配
//                    discriminateNumber(click_screen_xy);
//                    discriminate=false;
//                }
                gamePublicFunction.manualOrAutomatic("automatic");//自动战斗

                gamePublicFunction.taskOrTeam("team");//切换到队伍栏

                Thread.sleep(1000);

                if (TaskMain.taskMap.get("captain") == 1) {

                    gamePublicFunction.createTeam();
//                    if (TaskMain.taskMap.get("timing") == 1) {
//                        gamePublicFunction.recall(true);
//                    }
                } else if (TaskMain.taskMap.get("team") == 1) {

                    result = publicFunction.localFindPic(24, 198, 77, 250, "flag.png");

                    LtLog.i(publicFunction.getLineInfo() + "-------------------- flag  =" + result);

                    if (TaskMain.taskMap.get("retreat") == 1 && result.sim >= 0.72) {

                        LtLog.i(publicFunction.getLineInfo() + "-------------------- flag  =" + result);

                        gamePublicFunction.exitTeam();
                    }
                    //加入队伍
                    joinRanks();
                }

                if (TaskMain.taskMap.get("peace") == 1) {
                    //切换到和平模式

                    LtLog.i(publicFunction.getLineInfo() + "【切换和平模式】");

                    result = publicFunction.localFindPic(428, 16, 518, 109, "peace-error1.png"+"|"+"peace-error.png");
                    LtLog.i(publicFunction.getLineInfo() + "-------------------- peace-error  =" + result);
                    if (result.sim >= 0.8) {
                        publicFunction.rndTap(457, 46, 472, 58);
                        Thread.sleep(2000);

                        findResult = mFairy.findPic(477,90,561,162,"heping.png");
                        if(findResult.sim>0.8f){
                            mFairy.onTap(524,159,526,165,"和平模式",1000);
                        }else{
                            mFairy.onTap(420,160,423,169,"和平模式",1000);
                        }

                        /*result = publicFunction.localFindPic(371, 61, 572, 191, "peace.png");
                        LtLog.i(publicFunction.getLineInfo() + "-------------------- peace  =" + result);
                        if (result.sim >= 0.8) {
                            publicFunction.rndTap(result.x + 20, result.y + 20, result.x + 25, result.y + 25);
                            Thread.sleep(1000);
                        }*/
                    }
                }
            }

            result = publicFunction.localFindPic(900, 618, 1018, 720, "tong.png" + "|" + "tong2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------------切换到自动-----------------tong.png=" + result);
                publicFunction.rndTap(1222, 303, 1240, 317);
                Thread.sleep(500);
            }

            if (TaskMain.taskMap.get("assist") == 1) {
                //商会协助
                gamePublicFunction.assist();
            }

            if (TaskMain.taskMap.get("timing") == 1 && TaskMain.taskMap.get("captain") == 1) {

                //如果勾选定时召回并且是队长
                recall_timex = (System.currentTimeMillis() / 1000 / 60) - recall_time;

                LtLog.i(publicFunction.getLineInfo() + "---------recall_timex=" + recall_timex);

                if (TaskMain.taskMap.get("recall_time") <= recall_timex && TaskMain.taskMap.get("recall_time") > 0) {

                    LtLog.i(publicFunction.getLineInfo()+"【开始召回队员】");

                    recall_time = System.currentTimeMillis() / 1000 / 60;

                    gamePublicFunction.recall(true);
                }
                result = publicFunction.localFindPic(155, 239, 318, 495, "followCombat.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "---------followCombat.png=" + result);
                    gamePublicFunction.recall(false);
                }
            }
            if (TaskMain.taskMap.get("timeoffline") == 1) {
                //勾选了防沉迷
                boolean resetTime = gamePublicFunction.determineResetTime();
                if (resetTime) {
                    LtLog.i(publicFunction.getLineInfo() + "-----*************----killGame---->=");
                    mFairy.killUserGame();
                    gamePublicFunction.gameSleep();
                    //等待3分钟
                    for (int i = 0; i < 180; i++) {
                        LtLog.i(publicFunction.getLineInfo() + "---------sleep 3 =");
                        Thread.sleep(1000);
                    }
                }
            }

            time2x = System.currentTimeMillis() / 1000 - time2;
            if (time2x >= 3600) {
                //间隔60分钟 ,进行同伴招募和家族宝箱
                if (TaskMain.taskMap.get("companion") == 1) {
                    //同伴招募
                    gamePublicFunction.companion();
                    gamePublicFunction.switchSkillOrTong("skill");
                }
                if (TaskMain.taskMap.get("chest") == 1) {
                    //家族宝箱
                    gamePublicFunction.chest();
                    gamePublicFunction.switchSkillOrTong("skill");
                }
                time2 = System.currentTimeMillis() / 1000;
            }
            Thread.sleep(1000);
        }
        return 0;
    }

    // 侠客岛挂机
    private void XiakeIsland() throws Exception {
        AtFairy2.OpencvResult result;

        LtLog.i(publicFunction.getLineInfo() + "setSkill=" + TaskMain.taskMap.get("setSkill"));// 1为 不勾选技能 0为默认

        if (TaskMain.taskMap.get("setSkill") == 0) {
            gamePublicFunction.setSkill();//设置自动技能
        }

        boolean location = true;
        long time = System.currentTimeMillis() / 1000, timex = 0;
        long time1 = System.currentTimeMillis() / 1000, time1x = 0;
        long recall_time = (System.currentTimeMillis() / 1000 / 60) - TaskMain.taskMap.get("recall_time"), recall_timex = 0;
        int[] xy = {Integer.parseInt(TaskMain.xystr.split(",")[0]), Integer.parseInt(TaskMain.xystr.split(",")[1])};

        while (mFairy.condit()) {
//            LtLog.i(publicFunction.getLineInfo() + "----------outdoorsOnHook ING...." + "time1x=" + time1x + ",timex=" + timex + ",currentlyTIME=" + publicFunction.getMinuteNumber() + ",sun=" + publicFunction.getCurrSun() + ",currentTime=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 300) {
//              location = true;
                time = System.currentTimeMillis() / 1000;
            } else if (timex % 60 >= 0 && timex % 60 <= 3) {
                LtLog.i(publicFunction.getLineInfo() + "侠客岛挂机中 " + "定位时间：" + time1x + ",当前时间为：" + publicFunction.getMinuteNumber() + "星期：" + publicFunction.getCurrSun() + ",Data:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                LtLog.i(publicFunction.getLineInfo() + "--------->>" + ",TaskMain.taskMap=" + TaskMain.taskMap);
            }

            mAimingActivity();//定时活动

            time1x = System.currentTimeMillis() / 1000 - time1;//每隔300秒
            if (time1x >= 300) {
                location = true;
                time1 = System.currentTimeMillis() / 1000;
            }

            result = publicFunction.localFindPic(509, 406, 770, 500, "goHome.png");
            if (result.sim >= 0.8) {
                //被其他玩家击杀，重新定位
                LtLog.i(publicFunction.getLineInfo() + "-----*************-------->goHome=" + result);
                publicFunction.rndTapWH(result.x, result.y, 43, 19);
                Thread.sleep(500);
                location = true;
            }

            if (location && TaskMain.taskMap.get("OnHookMap") > 0 && xy[0] >= -1) {
                location = false;
                LtLog.i(publicFunction.getLineInfo() + "【开始定位】");

                GoXiakeIsland();
                // 999=侠客岛
                gamePublicFunction.openMapWorldOrCurrent("current");//是打开世界地图 还是 当前地图

                int[] click_screen_xy = screenXY(xy[0], xy[1], TaskMain.taskMap.get("OnHookMap"));//点击地图坐标

                gamePublicFunction.closeWindow();
                for (int i = 0; i < 10; i++) {
                    if (publicFunction.judgeMatChange(1177, 136, 70, 21, 0.95, 1000) == false) {
                        //判断坐标没有变动 退出
                        break;
                    }
                    Thread.sleep(1000);
                }

                gamePublicFunction.manualOrAutomatic("automatic");
                gamePublicFunction.taskOrTeam("team");
                Thread.sleep(1000);
                if (TaskMain.taskMap.get("captain") == 1) {
                    gamePublicFunction.createTeam();
                } else if (TaskMain.taskMap.get("team") == 1) {
                    result = publicFunction.localFindPic(24, 198, 77, 250, "flag.png");
                    LtLog.i(publicFunction.getLineInfo() + "-------------------- flag  =" + result);
                    if (TaskMain.taskMap.get("retreat") == 1 && result.sim >= 0.72) {
                        LtLog.i(publicFunction.getLineInfo() + "-------------------- flag  =" + result);
                        gamePublicFunction.exitTeam();
                    }
                    //加入队伍
                    joinRanks();
                }
            }
            if (TaskMain.taskMap.get("assist") == 1) {
                //商会协助
                gamePublicFunction.assist();
            }
            if (TaskMain.taskMap.get("timing") == 1 && TaskMain.taskMap.get("captain") == 1) {
                //如果勾选定时召回并且是队长
                recall_timex = (System.currentTimeMillis() / 1000 / 60) - recall_time;
                LtLog.i(publicFunction.getLineInfo() + "---------recall_timex=" + recall_timex);
                if (TaskMain.taskMap.get("recall_time") <= recall_timex && TaskMain.taskMap.get("recall_time") > 0) {
                    recall_time = System.currentTimeMillis() / 1000 / 60;
                    gamePublicFunction.recall(true);
                }
                result = publicFunction.localFindPic(155, 239, 318, 495, "followCombat.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "---------followCombat.png=" + result);
                    gamePublicFunction.recall(false);
                }
            }
            Thread.sleep(1000);
        }
    }

    /**
     * 去到侠客岛
     */
    private void GoXiakeIsland() throws Exception {
        result = publicFunction.localFindPic(1164, 0, 1279, 36, "xiakeIsland.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------当前在侠客岛 xiakeIsland=" + result);
            return;
        }

        List<String> list = new ArrayList<>();

        list.add("xiakeIsland1");

        while (mFairy.condit()) {
            gamePublicFunction.openActivity();

            gamePublicFunction.lookupTask(list);

            result = publicFunction.localFindPic(954, 577, 1200, 691, "attend.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------attend--->=" + result);
                publicFunction.rndTapWH(result.x, result.y, 10, 10);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(1164, 0, 1279, 36, "xiakeIsland.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【已到侠客岛】");
                return;
            }
            Thread.sleep(1000);
        }
    }

    private void deliverTargetMap() throws Exception {

        gamePublicFunction.openMapWorldOrCurrent("world");

        selectMap2(TaskMain.taskMap.get("OnHookMap"));

        gamePublicFunction.closeWindow();

        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(526, 459, 666, 509, "delivery.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【传送中】");
                Thread.sleep(4000);
            } else {
                result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
                if (result.sim >= 0.8 && i > 5) {
                    //没有在传送读条 并且 有活动按钮 证明传送完毕 ，退出循环
                    LtLog.i(publicFunction.getLineInfo() + "---------activity.png=" + result);
                    break;
                }
            }
            Thread.sleep(500);
        }
    }

    /*
     * 武林盟主  19:30-19:42
     * 家族烤火  19:15-19:25
     * 白虎堂    11:00 13:00 15:00 17:00 20:00 24:00
     * 宋金
     * 白虎堂
     * 名将 22:00 - 23:00
     * 达摩洞 22:00 - 23:00  达摩洞与跨服名将互斥.
     * 鏖战襄阳 22:00 - 23:00
     * 始皇降临
     * 家族秘境
     * 女帝
     * 规避防沉迷
     * 华山论剑
     * 遗迹寻宝
     * 南海钩沉 19:50
     * */
    public int mAimingActivity() throws Exception {

        LtLog.i(publicFunction.getLineInfo() + "【检测限时任务】");

        int currentlyTIME = publicFunction.getMinuteNumber();

        String currentSun = publicFunction.getCurrSun();

        //ElseIf ((time_2 >= 749 and Time_2 < 760) or (time_2 >= 1169 and Time_2 < 1180)) and sel5<>0 Then

        if ((currentlyTIME >= 745 && currentlyTIME <= 760) || (currentlyTIME >= 1165 && currentlyTIME < 1180)) {
            //武林盟主
            if (TaskMain.taskMap.get("WLMZ") == 1) {
                LtLog.i(publicFunction.getLineInfo() + "开始执行武林盟主");
                LtLog.i(publicFunction.getLineInfo() + "开始执行武林盟主");
                LtLog.i(publicFunction.getLineInfo() + "开始执行武林盟主");
                timingActivity.timingTask("WLMZ");
                return 1;
            }
        }


        if (currentlyTIME >= 1154 && currentlyTIME <= 1164) {
            //家族烤火 19:15-19:25
            if (TaskMain.taskMap.get("JZKH") == 1) {
                LtLog.i(publicFunction.getLineInfo() + "开始执行家族烤火");
                LtLog.i(publicFunction.getLineInfo() + "开始执行家族烤火");
                LtLog.i(publicFunction.getLineInfo() + "开始执行家族烤火");
                timingActivity.timingTask("JZKH");
                gamePublicFunction.goMainCity();
                return 1;
            }
        }


        if (currentlyTIME >= 1189 && currentlyTIME <= 1193 && currentSun.equals("星期三")) {
            //南海钩沉 19:50 - 19 ：53 星期三
            if (TaskMain.taskMap.get("nhgc") == 1) {
                LtLog.i(publicFunction.getLineInfo() + "开始执行南海钩沉");
                LtLog.i(publicFunction.getLineInfo() + "开始执行南海钩沉");
                LtLog.i(publicFunction.getLineInfo() + "开始执行南海钩沉");
                timingActivity.timingTask("NHGC");
            }
        }

        if (currentlyTIME >= TaskMain.taskMap.get("hsgMinTime") && currentlyTIME <= TaskMain.taskMap.get("hsgMaxTime")
                &&(currentSun.equals("星期一")|| currentSun.equals("星期三")|| currentSun.equals("星期五")) ){

            if (!AtFairyConfig.getOption("hsg").equals("")) {
                LtLog.i(publicFunction.getLineInfo() + "开始执行智取花石纲");
                LtLog.i(publicFunction.getLineInfo() + "开始执行智取花石纲");
                LtLog.i(publicFunction.getLineInfo() + "开始执行智取花石纲");
                timingActivity.hsg();
                return 1;
            }
        }

        if (currentlyTIME >= TaskMain.taskMap.get("BHTminTime") && currentlyTIME <= TaskMain.taskMap.get("BHTmaxTime")
            && (AtFairyConfig.getOption("zhou"+mFairy.week()).equals("1"))){
            //白虎堂
            if (TaskMain.taskMap.get("sel7") > 0) {
                LtLog.i(publicFunction.getLineInfo() + "开始执行白虎堂");
                LtLog.i(publicFunction.getLineInfo() + "开始执行白虎堂");
                LtLog.i(publicFunction.getLineInfo() + "开始执行白虎堂");
                timingActivity.timingTask("BHT");
                return 1;
            }
        }

        if (currentSun.equals("星期二") || currentSun.equals("星期四") || currentSun.equals("星期六")) {
            //心魔幻境
            if (currentlyTIME >= TaskMain.taskMap.get("XMHJminTime") && currentlyTIME <= TaskMain.taskMap.get("XMHJmaxTime")) {
                if (TaskMain.taskMap.get("XMHJ") > 0) {
                    LtLog.i(publicFunction.getLineInfo() + "开始执行心魔幻境");
                    LtLog.i(publicFunction.getLineInfo() + "开始执行心魔幻境");
                    LtLog.i(publicFunction.getLineInfo() + "开始执行心魔幻境");
                    timingActivity.timingTask("XMHJ");
                    return 1;
                }
            }
        }

        if (currentSun.equals("星期三")/* || currentSun.equals("星期一")*/) {
            //遗迹寻宝
            if (currentlyTIME >= 1189 && currentlyTIME <= 1195) {
                if (TaskMain.taskMap.get("ruins") > 0) {
                    LtLog.i(publicFunction.getLineInfo() + "开始执行遗迹寻宝");
                    LtLog.i(publicFunction.getLineInfo() + "开始执行遗迹寻宝");
                    LtLog.i(publicFunction.getLineInfo() + "开始执行遗迹寻宝");
                    timingActivity.timingTask("ruins");
                    return 1;
                }
            }
        }

        //山河战境
        if (currentSun.equals("星期二") || currentSun.equals("星期六")) {
            if(currentlyTIME >= 1259 && currentlyTIME <= 1290){
                if(AtFairyConfig.getOption("8669").equals("1")){
                    LtLog.i(publicFunction.getLineInfo() + "开始山河战境");
                    LtLog.i(publicFunction.getLineInfo() + "开始山河战境");
                    LtLog.i(publicFunction.getLineInfo() + "开始山河战境");
                    timingActivity.shzj();
                    return 1;
                }
            }
        }

      /*

      //灰谷矿脉
        if (currentSun.equals("星期一")) {
            if((currentlyTIME >= 989 && currentlyTIME <= 995) ||  (currentlyTIME >= 1229 && currentlyTIME <= 1235)){

                if(AtFairyConfig.getOption("hgkm").equals("1")){

                    LtLog.i(publicFunction.getLineInfo() + "开始灰谷矿脉");
                    LtLog.i(publicFunction.getLineInfo() + "开始灰谷矿脉");
                    LtLog.i(publicFunction.getLineInfo() + "开始灰谷矿脉");
                    timingActivity.hgkm();
                    return 1;
                }
            }
        }*/

        //青丘疑阵
        if (currentSun.equals("星期一")) {
            if(currentlyTIME >= 1189 && currentlyTIME <= 1200){

                if(AtFairyConfig.getOption("qqyz").equals("1")){

                    LtLog.i(publicFunction.getLineInfo() + "开始青丘疑阵");
                    LtLog.i(publicFunction.getLineInfo() + "开始青丘疑阵");
                    LtLog.i(publicFunction.getLineInfo() + "开始青丘疑阵");
                    timingActivity.qqyz();
                    return 1;
                }
            }
        }



        //粮秣行
        if (currentSun.equals("星期日")) {
            if(currentlyTIME >= 1200 && currentlyTIME <= 1230){

                if(AtFairyConfig.getOption("lmx").equals("1")){

                    LtLog.i(publicFunction.getLineInfo() + "开始粮秣行");
                    LtLog.i(publicFunction.getLineInfo() + "开始粮秣行");
                    LtLog.i(publicFunction.getLineInfo() + "开始粮秣行");
                    timingActivity.lmx();
                    return 1;
                }
            }
        }

        if (currentlyTIME >= TaskMain.taskMap.get("SJZCminTime") && currentlyTIME <= TaskMain.taskMap.get("SJZCmaxTime")) {
            //宋金战场
            LtLog.i(publicFunction.getLineInfo() + "---------currentlyTIME=" + currentlyTIME + ",SJZCminTime=" + TaskMain.taskMap.get("SJZCminTime") + ",SJZCmaxTime" + TaskMain.taskMap.get("SJZCmaxTime"));
            if (TaskMain.taskMap.get("sel8") > 0) {
                LtLog.i(publicFunction.getLineInfo() + "开始执行宋金战场");
                LtLog.i(publicFunction.getLineInfo() + "开始执行宋金战场");
                LtLog.i(publicFunction.getLineInfo() + "开始执行宋金战场");

                timingActivity.timingTask("SJZC");
                return 1;
            }
        }


        if (currentSun.equals("星期一") || currentSun.equals("星期三") || currentSun.equals("星期五") || currentSun.equals("星期日")) {
            //华山论剑
            if (currentlyTIME >= 1289 && currentlyTIME <= 1310 && TaskMain.taskMap.get("huashan") == 1) {
                LtLog.i(publicFunction.getLineInfo() + "开始执行华山论剑");
                LtLog.i(publicFunction.getLineInfo() + "开始执行华山论剑");
                LtLog.i(publicFunction.getLineInfo() + "开始执行华山论剑");
                timingActivity.timingTask("huashan");
                return 1;
            }
        }

        //家族试炼 21.00 //家族秘境 familyMJ
        //攻城战 21.00
        //门派竞技 21.00
        //通天塔 21.00  23.00都有
        //家族保卫战-每隔2周 周五，
        // 领土战 每周2   21:05
        if ((currentSun.equals("星期三") && currentlyTIME >= 1378 && currentlyTIME <= 1400)|| (currentSun.equals("星期日")&& currentlyTIME >= 1348 && currentlyTIME <= 1370)  )  {
            //11点通天塔
            if (TaskMain.taskMap.get("Ninehour") == 1) {

                LtLog.i(publicFunction.getLineInfo() + "开始执行通天塔");
                LtLog.i(publicFunction.getLineInfo() + "开始执行通天塔");
                LtLog.i(publicFunction.getLineInfo() + "开始执行通天塔");

                LtLog.i(publicFunction.getLineInfo() + "------------------------ 通天塔 23点 调用9点活动------=");
                timingActivity.activityNiceOrTen("nice");
                return 1;
            }
        }


        //龙门之争
        if (currentSun.equals("星期五")) {
            if(currentlyTIME >= 1259 && currentlyTIME <= 1261){

                if(AtFairyConfig.getOption("lmzz").equals("1")){

                    LtLog.i(publicFunction.getLineInfo() + "开始龙门之争");
                    LtLog.i(publicFunction.getLineInfo() + "开始龙门之争");
                    LtLog.i(publicFunction.getLineInfo() + "开始龙门之争");
                    timingActivity.lmzz();
                    return 1;
                }
            }
        }



        if (currentlyTIME >= 1259 && currentlyTIME <= 1270) {
            //if (currentlyTIME >= 1259 && currentlyTIME <= 1290) {
            //九点活动
            LtLog.i(publicFunction.getLineInfo() + "------------------------Ninehour------=currentlyTIME=" + currentlyTIME);
            if (TaskMain.taskMap.get("Ninehour") == 1) {
                LtLog.i(publicFunction.getLineInfo() + "------------------------Ninehour------=");
                timingActivity.activityNiceOrTen("nice");
                return 1;
            }
        }


        if (currentlyTIME >= 1316 && currentlyTIME <= 1335) {
            //22:00-22:30
            //名将 或者始皇  女帝
            //达摩洞 名将 鏖战襄樊 互斥,只能做其中一个
            //周三 鏖战襄樊 达摩洞
            //周四 始皇
            //周天 女帝

            //  wednesdaytensetmj - 名将任务
            //  wednesdayAZXYandDM - 达摩洞 and 鏖战襄樊
            //  queen - 女帝
            //  sh - 始皇
            //  time_mj2 - 表示晚上10点活动

            LtLog.i(publicFunction.getLineInfo() + "------wednesdaytensetmj=" + TaskMain.taskMap.get("wednesdaytensetmj") + " , wednesdayAZXYandDM : " + TaskMain.taskMap.get("wednesdayAZXYandDM"));

            LtLog.i(publicFunction.getLineInfo() + "------time_mj2=" + TaskMain.taskMap.get("time_mj2") + " , sh : " + TaskMain.taskMap.get("sh") + " , queen : " + TaskMain.taskMap.get("queen"));

            if (TaskMain.taskMap.get("time_mj2") == 1 || TaskMain.taskMap.get("sh") > 0 || TaskMain.taskMap.get("queen") > 0) {

                LtLog.i(publicFunction.getLineInfo() + "开始执行10点活动");
                LtLog.i(publicFunction.getLineInfo() + "开始执行10点活动");
                LtLog.i(publicFunction.getLineInfo() + "开始执行10点活动");

                timingActivity.activityNiceOrTen("ten");

                return 1;
            }
        }

        if (currentlyTIME >= 929 && currentlyTIME <= 940) {

            //3点名将 一般只有新服有

            if (TaskMain.taskMap.get("time_mj1") == 1) {

                LtLog.i(publicFunction.getLineInfo() + "开始执行3点名将");
                LtLog.i(publicFunction.getLineInfo() + "开始执行3点名将");
                LtLog.i(publicFunction.getLineInfo() + "开始执行3点名将");

                timingActivity.activityNiceOrTen("ten");
                return 1;
            }
        }


        if (TaskMain.taskMap.get("outdoorsOnHook_commerce") > 0) {
            if (TaskMain.taskMap.get("outdoorsOnHook_commerce") <= currentlyTIME && currentlyTIME < TaskMain.taskMap.get("outdoorsOnHook_commerce") + 2) {
                //'定时交商会任务
                LtLog.i(publicFunction.getLineInfo() + "开始执行定时交商会任务");
                LtLog.i(publicFunction.getLineInfo() + "开始执行定时交商会任务");
                LtLog.i(publicFunction.getLineInfo() + "开始执行定时交商会任务");
                TaskMain.taskMap.put("commerce", 1);
                TaskMain.taskMap.put(TaskMain.SUBMISSION, 1);
                new TimingActivity(mFairy).GoSecurityXiakeIsland();//如果在侠客岛,需要先回到安全区,,初始设计脚本时没有考虑到侠客岛这个奇葩,所以这个地方还要调用一次。
                singleTask.mSingleTask();
            }
        }

        if (currentSun.equals("星期二") || currentSun.equals("星期六") || (currentSun.equals("星期四")&& (AtFairyConfig.getOption("zsyb").equals("1")))) {

            //'运镖
            if (TaskMain.taskMap.get(TaskMain.YunBiao_Time) > -1) {

                if (TaskMain.taskMap.get(TaskMain.YunBiao_Time) - 2 <= currentlyTIME && currentlyTIME < TaskMain.taskMap.get(TaskMain.YunBiao_Time) + 7) {
                    LtLog.i(publicFunction.getLineInfo() + "开始执行运镖任务");
                    LtLog.i(publicFunction.getLineInfo() + "开始执行运镖任务");
                    LtLog.i(publicFunction.getLineInfo() + "开始执行运镖任务");
                    cargoPulling();
                }
            }
        }






        return 0;
    }

    //移动到夜郎废墟
    public void cargoPulling() throws Exception {
        long start_time = publicFunction.getMinuteNumber();
        long timex = 0;

        gamePublicFunction.closeWindow();

        new TimingActivity(mFairy).GoSecurityXiakeIsland();//如果在侠客岛,需要先回到安全区,,初始设计脚本时没有考虑到侠客岛这个奇葩,所以这个地方还要调用一次。

        gamePublicFunction.goSecurity();

        gamePublicFunction.closeWindow();

        gamePublicFunction.openActivity();//打开活动

        Boolean bool = false;

        for (int i = 0; i < 10; i++) {
            findResult = mFairy.findPic(878,58,1079,583,"kfyb.png");
            if(findResult.sim>0.8f){
                bool=true;
                mFairy.onTap(findResult.x+50,findResult.y+30,findResult.x+60,findResult.y+35,"跨服运镖",5000);
                break;
            }
        }

        gamePublicFunction.closeWindow();

        if(bool){
            while (mFairy.condit()){

                findResult = mFairy.findPic(294,33,484,188,"ditu.png");
                if (findResult.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "当前地图界面");
                    result = publicFunction.localFindPic(451, 142, 845, 562, "carriage.png");
                    if (result.sim >= 0.75) {
                        LtLog.i(publicFunction.getLineInfo() + "找到镖车");
                        mFairy.tap(result.x - 10, result.y);
                        Thread.sleep(1000);
                    }
                } else {
                    gamePublicFunction.openMapWorldOrCurrent("current");
                }

                timex = publicFunction.getMinuteNumber() - start_time;
                LtLog.i(publicFunction.getLineInfo() + "------->timex=" + timex + ", current time  :" + publicFunction.getMinuteNumber());

                if (timex > 6 || publicFunction.getMinuteNumber() >= 1320) {

                    //运镖只需要3分钟就结束了
                    //1320 是22:00  ，运镖22:00后，不能开启

                    LtLog.i(publicFunction.getLineInfo() + "运镖时间到了,end!");

                    gamePublicFunction.closeWindow();

                    for (int i = 0; i < 10; i++) {
                        result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
                        if (result.sim >= 0.8) {
                            mFairy.onTap(1105,38,1117,53,"",2000);
                        }

                        result = gamePublicFunction.leave();
                        if (result.sim >= 0.8) {
                            LtLog.i(publicFunction.getLineInfo() + "离开");
                            publicFunction.rndTapWH(result.x, result.y, 10, 10);
                            Thread.sleep(2000);
                        }

                        gamePublicFunction.clickDetermine();
                    }

                    gamePublicFunction.closeWindow();

                    for (int i = 0; i < 5; i++) {
                        result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
                        if (result.sim >= 0.8) {
                            break;
                        }else{
                            mFairy.onTap(1107,25,1123,42,"",2000);
                        }
                    }

                    return;
                }
                Thread.sleep(1000);
            }
            return;
        }

        while (mFairy.condit()) {
            result = publicFunction.localFindPic(547, 58, 679, 116, "YLFX-1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "夜郎废墟 - 当前地图界面");
                result = publicFunction.localFindPic(451, 142, 845, 562, "carriage.png");
                if (result.sim >= 0.75) {
                    LtLog.i(publicFunction.getLineInfo() + "找到镖车");
                    mFairy.tap(result.x - 10, result.y);
                    Thread.sleep(1000);
                }
            } else {
                result = publicFunction.localFindPic(1146, 0, 1274, 38, "YLFX.png");
                LtLog.i(publicFunction.getLineInfo() + "------->YLFX=" + result);
                if (result.sim < 0.8) {
                    gamePublicFunction.goSecurity();
                    gamePublicFunction.closeWindow();
                    gamePublicFunction.openMapWorldOrCurrent("world");
                    map_xy = null;
                    selectMap2(MAP_YLFX);
                    map_xy = null;
                    Thread.sleep(5000);
                } else {
                    gamePublicFunction.openMapWorldOrCurrent("current");
                }
            }

            timex = publicFunction.getMinuteNumber() - start_time;

            LtLog.i(publicFunction.getLineInfo() + "------->timex=" + timex + ", current time  :" + publicFunction.getMinuteNumber());

            if (timex > 6 || publicFunction.getMinuteNumber() >= 1320) {
                //运镖只需要3分钟就结束了
                //1320 是22:00  ，运镖22:00后，不能开启
                LtLog.i(publicFunction.getLineInfo() + "运镖时间到了,end!");
                gamePublicFunction.closeWindow();
                return;
            }
            Thread.sleep(1000);
        }
    }

    /**
     * 选择地图页 1  或者地图页 2
     * map 地图编号
     */
    private void switchMap(int map) throws Exception {

        LtLog.i(publicFunction.getLineInfo() + "【开始切换地图页："+map+"】");


        for (int i = 0; i < 4; i++) {
            publicFunction.RanSwipe(372, 85, 950, 606, 2, 300);
            Thread.sleep(100);
        }
        for (int i = 0; i < 5; i++) {

            AtFairy2.OpencvResult result1 = publicFunction.localFindPic(1010, 195, 1097, 291, "mainCity3.png");//识别临安

            result = publicFunction.localFindPic(195, 275, 321, 528, "map2Features.png");//

            findResult = mFairy.findPic(1026,438,1192,662,"MAP_BZS.png");

            LtLog.i(publicFunction.getLineInfo() + "宗师地图：" + result + ", 普通地图：" + result1 +",泰斗地图："+findResult+ ",map=" + map);

            if(result1.sim > 0.8){
                LtLog.e(mFairy.getLineInfo("当前在普通地图"));
                if(map<=33){
                    return;
                }else if(map>33 && map<=43){
                    mFairy.tap(result1.x + 91, result1.y); //切换到宗师地图
                }else if(map>43){
                    mFairy.tap(229,350); //切换到泰斗地图
                }
                Thread.sleep(2000);
                return;
            }

            if(result.sim>0.8){
                LtLog.e(mFairy.getLineInfo("当前在宗师地图"));
                if(map<=33){
                    mFairy.tap(231,324); //切换到普通地图
                }else if(map>33 && map<=43){
                    return;
                }else if(map>43){
                    mFairy.tap(231,324); //切换到普通地图
                    Thread.sleep(2000);

                    for (int j = 0; j < 4; j++) {
                        publicFunction.RanSwipe(372, 85, 950, 606, 2, 300);
                        Thread.sleep(100);
                    }
                    mFairy.tap(229,350); //切换到泰斗地图
                }
                Thread.sleep(2000);
                return;
            }

            if(findResult.sim>0.8f){
                LtLog.e(mFairy.getLineInfo("当前在泰斗地图"));
                if(map<=33){
                    mFairy.tap(1073,265); //切换到普通地图
                }else if(map>33 && map<=43){

                    mFairy.tap(1073,265); //切换到普通地图
                    Thread.sleep(2000);

                    for (int j = 0; j < 4; j++) {
                        publicFunction.RanSwipe(372, 85, 950, 606, 2, 300);
                        Thread.sleep(100);
                    }

                    mFairy.tap(result1.x + 91, result1.y); //切换到宗师地图

                }else if(map>43){
                    return;
                }
                Thread.sleep(2000);
                return;
            }
        }
    }

    public void selectMap2(int targetMap) throws Exception {
        //选择地图

        LtLog.i(publicFunction.getLineInfo() + "【开始选择地图："+targetMap+"】" );
        int map = targetMap;
        result = publicFunction.localFindPic(0, 95, 130, 283, "map.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "【没有在地图界面】");
            return;
        }

        switchMap(map);//选择 地图有两个页面需要切换 地图 1 或者 2

        for (int i = 0; i < 4 && map <= 33; i++) {
            if (map >= 11 && map <= 33) {
                publicFunction.RanSwipe(372, 85, 950, 606, 0, 300);
                //x,y,x1,y1 这是范围
                //dir = 0从上往下滑动，dir = 1从左往右滑动，dir = 2从下往上滑动，dir = 3从右往左滑动
                //sleep 滑动延时
            } else {
                publicFunction.RanSwipe(372, 85, 950, 606, 2, 300);
            }
            Thread.sleep(100);
        }

        Thread.sleep(1000);

        if (map_xy == null) {
            for (int i = 0; i < 30; i++) {

                result = publicFunction.localFindPic(127, 40, 1175, 667, map_name_list.get(map - 1));
                LtLog.i(publicFunction.getLineInfo() + "【地图list: " + map_name_list.get(map - 1)+" , sim:"+result.sim+"】");
                //查找目标地图
                if (result.sim > 0.8) {
                    map_xy = new int[2];
                    //给目标地图的固定位置赋值
                    map_xy[0] = result.x - 34;
                    map_xy[1] = result.y + 30;
                    break;
                }


                result = publicFunction.localFindPic(0, 95, 130, 283, "map.png");
                if (result.sim < 0.8) {
                    //不在世界地图，返回
                    LtLog.i(publicFunction.getLineInfo() + "【没有在地图界面】");
                    return;
                }
                Thread.sleep(900);
            }
        }
        if (map_xy != null) {
            LtLog.i(publicFunction.getLineInfo() + "【点击地图 " + map + ",x=" + map_xy[0] + ",y=" + map_xy[1]+"】");
            mFairy.tap(map_xy[0], map_xy[1]);
            Thread.sleep(6000);
        }
    }

    /**
     * 判断当前角色所在地图是否与用户设置的目标地图一致
     *
     * @param targetMap 目标地图编号
     * @return 地图与选择的一致 返回true ,否则返回false
     */
    public Boolean judgeMap(int targetMap) {


        //LtLog.i(publicFunction.getLineInfo()+"【判断当前角色所在地图是否与用户设置的目标地图一致】");

        AtFairy2.OpencvResult result;
//        LtLog.i(publicFunction.getLineInfo() + "------->targetMap=" + targetMap);
        result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->activity=" + result);
            return false;
        }


        result = publicFunction.localFindPic(1127,2,1280,58, current_map_name_list.get(targetMap - 1));
        LtLog.e("地图标号"+targetMap+"    result:"+result.sim);
        if(result.sim>0.8){

            return true;
        }

   /*     mat1.release();
        mat2.release();
        result = null;
        resultVal = null;*/
        LtLog.i(publicFunction.getLineInfo() + "目标地图不正确,开始重新定位");
        return false;
    }

    public int[] screenXY(int x_1, int y_1, int targetMap) throws Exception {
        //点击地图坐标

        int map = targetMap;

        LtLog.i(publicFunction.getLineInfo() + "【开始点击地图坐标 map:"+map+"】");

        int[] xy = new int[2];
        double x = 0, y = 0;
//        result = publicFunction.localFindPic(827, 542, 954, 669, "smallMap.png");
        result = publicFunction.localFindPic(342, 57, 933, 649, "smallMap.png"+"|"+"smallMap1.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "【没有在当前地图】");
            xy[0] = -1;
            xy[1] = -1;
            return xy;
        }
        if (map == 1) {
            //雁荡山-10级
            x = x_1 * 5.0473 + y_1 * 5.1224 + 279.8095;
            y = x_1 * 5.1134 + y_1 * -5.2949 + 363.3227;
        } else if (map == 2) {
            //武夷山-10级
            x = x_1 * 4.0474 + y_1 * -4.1562 + 643.2224;
            y = x_1 * -4.1823 + y_1 * -4.072 + 645.9035;
        } else if (map == 3) {
            //锁云渊-10级
            x = x_1 * 3.9859 + y_1 * -4.1596 + 650.4742;
            y = x_1 * -4.2676 + y_1 * -4.1757 + 712.8665;
        } else if (map == 4) {
            //洞庭湖畔-20级
            x = x_1 * 4.1014 + y_1 * -4.0257 + 637.1304;
            y = x_1 * -4.1014 + y_1 * -4.0652 + 731.8696;
        } else if (map == 5) {
            //苗岭-20级
            x = x_1 * 4.1014 + y_1 * -4.0257 + 625.2049;
            y = x_1 * -4.1014 + y_1 * -4.0652 + 703.3406;
        } else if (map == 6) {
            //点苍山-20级
            x = x_1 * 4.1014 + y_1 * -4.0257 + 637.3577;
            y = x_1 * -4.1014 + y_1 * -4.0652 + 707.3696;
        } else if (map == 7) {
            //夜郎废墟-30级
            x = x_1 * 4.0998 + y_1 * -4.2138 + 644.0407;
            y = x_1 * -4.2206 + y_1 * -4.0034 + 630.461;
        } else if (map == 8) {
            //响水洞-40级
            x = x_1 * 4.0929 + y_1 * -4.2103 + 644.3159;
            y = x_1 * -4.1139 + y_1 * -4.058 + 646.1768;
        } else if (map == 9) {
            //剑门关-40级
            x = x_1 * 3.8527 + y_1 * -3.8679 + 639.5976;
            y = x_1 * -3.7582 + y_1 * -3.863 + 703.9406;
        } else if (map == 10) {
            //见性峰-40级
            x = x_1 * 4.133 + y_1 * -4.0808 + 637.8462;
            y = x_1 * -4.1245 + y_1 * -4.1609 + 693.1009;
        } else if (map == 11) {
            //风陵渡-50级
            x = x_1 * 4.133 + y_1 * -4.0808 + 637.5851;
            y = x_1 * -4.1245 + y_1 * -4.1609 + 734.5279;
        } else if (map == 12) {
            //荐菊洞-60级
            x = x_1 * 4.1416 + y_1 * -4.0726 + 636.9725;
            y = x_1 * -4.0529 + y_1 * -4.0923 + 694.5476;
        } else if (map == 13) {
            //古战场-60级
            x = x_1 * 5.0752 + y_1 * -5.0827 + 641.797;
            y = x_1 * -4.8883 + y_1 * -5.1515 + 799.3555;
        } else if (map == 14) {
            //伏牛山-60级
            x = x_1 * 4.0704 + y_1 * -4.1408 + 639.7042;
            y = x_1 * -4.2706 + y_1 * -4.301 + 719.6101;
        } else if (map == 15) {
            //太行古径-70级
            x = x_1 * 3.8964 + y_1 * -3.9794 + 632.5761;
            y = x_1 * -3.7826 + y_1 * -3.8313 + 645.7243;
        } else if (map == 16) {
            //祁连山-80级
            x = x_1 * 3.8313 + y_1 * -3.8933 + 629.6781;
            y = x_1 * -3.9679 + y_1 * -3.755 + 707.245;
        } else if (map == 17) {
            //沙漠遗迹-80级
            x = x_1 * 3.7266 + y_1 * -3.827 + 634.4654;
            y = x_1 * -3.8594 + y_1 * -3.8237 + 655.5647;
        } else if (map == 18) {
            //敦煌古城-80级
            x = x_1 * 3.7551 + y_1 * -3.8451 + 646.1784;
            y = x_1 * -3.7631 + y_1 * -3.8846 + 711.8846;
        } else if (map == 19) {
            //昆虚脉藏-90级
            x = x_1 * 4.1625 + y_1 * -4.1003 + 636.1816;
            y = x_1 * -4.1268 + y_1 * -3.9856 + 791.9222;
        } else if (map == 20) {
            //药王谷-100级
            x = x_1 * 4.0626 + y_1 * -4.1265 + 643.3898;
            y = x_1 * -4.2239 + y_1 * -4.011 + 668.1624;

        } else if (map == 21) {
            //漠北草原-100级
            x = x_1 * 3.2568 + y_1 * -3.1915 + 635.5617;
            y = x_1 * -3.1891 + y_1 * -3.2045 + 684.6591;
        } else if (map == 22) {
            //长白山-100级
            x = x_1 * 3.3213 + y_1 * -3.3648 + 641.1036;
            y = x_1 * -3.3597 + y_1 * -3.4535 + 663.2783;
        } else if (map == 23) {
            //残桓铁城-110级
            x = x_1 * 3.7907 + y_1 * -3.82 + 641.4651;
            y = x_1 * -3.8372 + y_1 * -3.7366 + 714.3605;
        } else if (map == 24) {
            //西夏皇陵-120级
            x = x_1 * 4.075 + y_1 * -4.0953 + 641.5634;
            y = x_1 * -4.125 + y_1 * -3.9884 + 734.5959;
        } else if (map == 25) {
            //居延泽-120
            x = x_1 * 4.0755 + y_1 * -4.0962 + 629.1587;
            y = x_1 * -3.9209 + y_1 * -4.2413 + 674.2755;
        } else if (map == 26) {
            //龙门客栈-130级
            x = x_1 * 4.1824 + y_1 * -4.068 + 614.2577;
            y = x_1 * -4.1836 + y_1 * -3.9777 + 695.4997;
        } else if (map == 27) {
            //蓬丘岱屿-140级
            x = x_1 * 5.128 + y_1 * -5.123 + 638.9205;
            y = x_1 * -5.0683 + y_1 * -5.0994 + 702.6832;
        } else if (map == 28) {
            //雾凇雪岭-140级
            x = x_1 * 4.4997 + y_1 * -4.3876 + 624.2065;
            y = x_1 * -4.3684 + y_1 * -4.4619 + 731.2849;
        } else if (map == 29) {
            //居庸关
            x = x_1 * 4.4777 + y_1 * -4.5516 + 642.6091;
            y = x_1 * -4.6395 + y_1 * -4.6526 + 738.1596;
        } else if (map == 30) {
            //茶马商道
            x = x_1 * 4.729 + y_1 * -4.7927 + 642.5919;
            y = x_1 * -4.8187 + y_1 * -4.7622 + 786.5224;
        } else if (map == 31) {
            //震泽渡口
            x = x_1 * 4.5045 + y_1 * -4.4585 + 629.2649;
            y = x_1 * -4.5058 + y_1 * -4.6039 + 725.7556;
        } else if (map == 32) {
            //楼兰古国
            x = x_1 * 4.8544 + y_1 * -4.7408 + 635.3072;
            y = x_1 * -4.7376 + y_1 * -4.7968 + 841.6112;
        } else if (map == 33) {
            //塑北雪原
            x = x_1 * 4.8192 + y_1 * -4.7388 + 636.5848;
            y = x_1 * -4.8183 + y_1 * -4.7513 + 796.6098;
        } else if (map == 34) {
            //蓬莱-宗师
            x = x_1 * 4.8247 + y_1 * -4.7616 + 637.7341;
            y = x_1 * -4.8545 + y_1 * -4.8403 + 770.0119;
        } else if (map == 35) {
            //瀛洲
            x = x_1 * 5.0932 + y_1 * -5.0853 + 638.3745;
            y = x_1 * -5.1426 + y_1 * -5.1602 + 767.7689;
        } else if (map == 36) {
            //方壶
            x = x_1 * 3.8543 + y_1 * -3.9303 + 640.7773;
            y = x_1 * -3.8087 + y_1 * -3.9508 + 667.9016;
        } else if (map == 37) {
            //员峤
            x = x_1 * 4.4908 + y_1 * -4.5215 + 642.3374;
            y = x_1 * -4.624 + y_1 * -4.6941 + 975.9281;
        } else if (map == 999) {
            //侠客岛
            x = x_1 * 3.2875 + y_1 * -3.2986 + 639.9228;
            y = x_1 * -3.2556 + y_1 * -3.2651 + 910.2519;
        }else if(map == 38){
            x=x_1 *4.1306+y_1 *-4.1121+642.6867;
            y=x_1 *-4.2169+y_1 *-4.1614+694.8638;
        }else if(map == 39){
            x=x_1 *4.35+y_1 *-4.133+629.192;
            y=x_1 *-4.0+y_1 *-4.1136+703.3864;
        }else if(map == 40){
            x=x_1 *4.3951+y_1 *-4.4521+630.9668;
            y=x_1 *-4.3857+y_1 *-4.4477+708.5585;
        }else if(map == 41){
            x=x_1 *4.9228+y_1 *-4.8024+634.4325;
            y=x_1 *-4.8252+y_1 *-4.8366+745.6211;
        }else if(map == 42){
            x=x_1 *-4.9167+y_1 *-4.8838+1141.2197;
            y=x_1 *-4.7083+y_1 *4.846+346.6326;
        }else if(map == 43){
            x=x_1 *4.1373+y_1 *-4.0172+634.7382;
            y=x_1 *-4.1052+y_1 *-4.0494+727.6223;
        }else if(map == 44){
            x=x_1 *4.2762+y_1 *-4.2667+638.4381;
            y=x_1 *-4.2133+y_1 *-4.2733+709.2133;
        }else if(map == 45){
            x=x_1 *3.9931+y_1 *-4.2207+650.1172;
            y=x_1 *-3.9264+y_1 *-3.9793+662.7494;
        }else if(map == 46){
            x=x_1 *4.125+y_1 *-4.4052+650.819;
            y=x_1 *-4.3125+y_1 *-4.3319+662.8836;
        }


        LtLog.i(publicFunction.getLineInfo() + "【坐标为："+x+","+y+"】");
        xy[0] = (int) x;
        xy[1] = (int) y;
        mFairy.tap(xy[0], xy[1]);
        Thread.sleep(500);
        LtLog.i(publicFunction.getLineInfo() + "【开始点击地图坐标 -结束】");

        return xy;
    }

    public void screenXY_QSHL(int x_1, int y_1, int targetMap) throws Exception {
        int map = targetMap;
        double x = 0, y = 0;
//        result = publicFunction.localFindPic(827, 542, 954, 669, "smallMap.png");
        result = publicFunction.localFindPic(342, 57, 933, 649, "smallMap.png|smallMap1.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->smallMap=" + result);
            return;
        }
        LtLog.i(publicFunction.getLineInfo() + "*******************--------------time =" + System.currentTimeMillis());
        if (map == 1) {
            x = x_1 * 4.1344 + y_1 * -4.0606 + 639.2966;
            y = x_1 * -4.0553 + y_1 * -4.0339 + 643.172;
        } else if (map == 2) {
            x = x_1 * 5.2214 + y_1 * -5.0147 + 624.154;
            y = x_1 * -5.0029 + y_1 * -5.0528 + 654.5543;
        } else if (map == 3) {
            x = x_1 * 6.4368 + y_1 * -6.3295 + 636.6858;
            y = x_1 * -6.3046 + y_1 * -6.3755 + 667.4559;
        }
        LtLog.i(publicFunction.getLineInfo() + "------->x=" + x + ",y=" + y);
        LtLog.i(publicFunction.getLineInfo() + "*******************--------------time =" + System.currentTimeMillis());
        mFairy.tap((int) x, (int) y);
        Thread.sleep(500);
    }

    //加入附近队伍
    public void joinRanks() throws Exception {
        AtFairy2.OpencvResult result;
        LtLog.i(publicFunction.getLineInfo() + "------->joinRanks=");

        gamePublicFunction.taskOrTeam("team");

        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPic(36, 174, 188, 297, "createTeam.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->createTeam=" + result);
                publicFunction.rndTapWH(result.x, result.y + 66, 52, 23);//点查看附近队伍
                Thread.sleep(1500);
            }
            result = publicFunction.localFindPic(849, 0, 984, 122, "fork.png" + "|" + "fork2.png" + "|" + "fork3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->fork=" + result);
                publicFunction.rndTap(865, 145, 877, 162);//勾选第一个队伍
                Thread.sleep(500);
                publicFunction.rndTap(584, 622, 672, 644);//申请加入
                Thread.sleep(2000);
            }

            Thread.sleep(1000);
        }

        gamePublicFunction.closeWindow();
    }

    //皇陵挂机
    public int QSHLOnHook() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "------->QSHLOnHook=");
        int position = 0;
        MatTime matTime = new MatTime(mFairy);
        long sleepTime = 0;
        int interval = 30;
        if (TaskMain.opcountList.size() > 0) {
            interval = (int) TaskMain.opcountList.get(0);
        }
        List<String> taskList = new ArrayList<>();
        taskList.add("QSHL");
        long timex = 0, time = System.currentTimeMillis() / 1000;
        long transpositionx = 0, transposition = System.currentTimeMillis() / 1000;

        gamePublicFunction.closeWindow();

        result = publicFunction.localFindPic(1107, 0, 1277, 77, "QSHL.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "【没有在皇陵】");
            gamePublicFunction.goSecurity();
            gamePublicFunction.openActivity();
        }

        gamePublicFunction.recall(true);

        while (mFairy.condit()) {
            result = publicFunction.localFindPic(1107, 0, 1277, 77, "QSHL.png");
            if (result.sim >= 0.8) {
                //如果在皇陵中
                LtLog.i(publicFunction.getLineInfo() + "【在皇陵中】");
                Thread.sleep(3000);
                position = getCurrentPosition();
                LtLog.i(publicFunction.getLineInfo() + "---------->position=" + position + ",layer=" + TaskMain.taskMap.get("layer") + ",xystr=" + TaskMain.xyList + ",sleepTime=" + sleepTime + ",interval:" + interval);

                if (position != TaskMain.taskMap.get("layer") && position > 0) {
                    goTarget(position, TaskMain.taskMap.get("layer"));
                    Thread.sleep(2000);
                }
            } else {
                result = gamePublicFunction.leave();
                if (result.sim < 0.8) {
                    gamePublicFunction.openActivity();
                }
            }


            result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
            if (result.sim >= 0.8) {
                //如果在活动选择窗口
                LtLog.i(publicFunction.getLineInfo() + "【活动界面】");
                for (int i = 0; i < 2; i++) {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                }

                Thread.sleep(2000);

                result = publicFunction.localFindPic(907, 133, 986, 316, "QSHL1.png");
                if (result.sim >= 0.8) {

                    LtLog.i(publicFunction.getLineInfo() + "找到秦始皇陵任务");
                    publicFunction.rndTap(result.x + 19, result.y + 33, result.x + 89, result.y + 59);
                    Thread.sleep(2000);
                    gamePublicFunction.clickDetermine();
                    gamePublicFunction.closeWindow();
                    gamePublicFunction.recall(true);
                } else {
                    publicFunction.RanSwipe(916, 153, 1038, 489, 0, 500);
                    Thread.sleep(500);
                }
            } else {
                time = System.currentTimeMillis() / 1000;
            }

            result = publicFunction.localFindPic(509, 406, 770, 500, "goHome.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "回城疗伤");
                publicFunction.rndTapWH(result.x, result.y, 43, 19);
                Thread.sleep(3000);
            }

            /**
             * 切换位置
             */


            sleepTime = matTime.mMatTime(1182,137,63,14, 0.95f);

            transpositionx = System.currentTimeMillis() / 1000 - transposition;

            LtLog.i(publicFunction.getLineInfo() + "---------->position=" + position + ",layer=" + TaskMain.taskMap.get("layer") + ",sleepTime=" + sleepTime + ",上一次定位的时间:" + interval, ",换位间隔计时=" + transpositionx);


            if (transpositionx >= interval && TaskMain.opcountList.size() > 0) {
                // transpositionx ,换位间隔计时
                //  transpositionx>interval  即当前时间 减 上一次定位的时间   大于 用户设置的间隔，  设置sleepTime=interval+1  ，则满足重新跑位置的条件
                sleepTime = interval + 1;
            }

            if (sleepTime > interval || result.sim >= 0.8) {
                //sleepTime>30 坐标未变化，重新定位。被打死了 重新定位
                LtLog.i(publicFunction.getLineInfo() + "开始重新定位");

                goTargetXY();

                for (int i = 0; i < 3; i++) {
                    result = publicFunction.localFindPic(770, 2, 1278, 454, "fork.png"+"|"+"fork1.png" + "|" + "fork2.png" + "|" + "fork3.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------------------------closeWindow---->fork=" + result + ",  time =" + System.currentTimeMillis());
                        publicFunction.rndTapWH(result.x, result.y, 35, 35);
                        Thread.sleep(1000);
                    }
                    gamePublicFunction.manualOrAutomatic("automatic");
                    Thread.sleep(100);
                }

                matTime.resetTime();

                if (TaskMain.opcountList.size() > 0) {
                    transposition = System.currentTimeMillis() / 1000;
                    //transposition 当更换位置时，QSHL_index+1，变换坐标
                    QSHL_index = QSHL_index + 1;
                }
            }


            gamePublicFunction.switchSkillOrTong("skill");


            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 120) {
                LtLog.i(publicFunction.getLineInfo() + "------->秦始皇陵挂机完成=");
                return 99;
            }
            result = publicFunction.localFindPic(795, 423, 908, 647, "mark1.png"+"|"+"mark.png"+"|"+"mark2.png");

            LtLog.i(publicFunction.getLineInfo() + "------->QSHL_into=" + TaskMain.taskMap.get("QSHL_into")+" sim:"+result.sim);

            if (result.sim >= 0.83 && TaskMain.taskMap.get("QSHL_into") == 1) {
                //点击问号
                LtLog.i(publicFunction.getLineInfo() + "------->mark=" + result);
                publicFunction.rndTapWH(result.x, result.y, 13, 38);
                Thread.sleep(500);
                for (int i = 0; i < 8; i++) {
                    result = publicFunction.localFindPic(343, 48, 553, 508, "secretChamber.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------->secretChamber=" + result);
                        mFairy.tap(result.x + 487, result.y + 37);
                        Thread.sleep(1500);
                        break;
                    }
                    Thread.sleep(300);
                }

            }

            result = publicFunction.localFindPic(343, 48, 553, 508, "secretChamber.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->secretChamber=" + result);
                mFairy.tap(result.x + 487, result.y + 37);
                Thread.sleep(1500);
            } else {

                result = publicFunction.localFindPic(501, 586, 772, 706, "reset.png");
                if (result.sim >= 0.8) {
                    publicFunction.rndTapWH(result.x, result.y, 13, 38);
                    Thread.sleep(1500);
                }
            }
            result = publicFunction.localFindPic(487, 7, 667, 70, "remainingTime.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【剩余时间】");
                secretChamber();
            }
            Thread.sleep(100);
        }
        return 0;
    }

    private void secretChamber() throws Exception {
        boolean move = false;
        while (mFairy.condit()) {
            if (!move) {
                //点地板移动到中间的位置
                for (int i = 0; i < 7; i++) {
                    mFairy.tap(901, 255);
                    Thread.sleep(1000);
                }
                move = true;
            }
            gamePublicFunction.manualOrAutomatic("automatic");
            result = publicFunction.localFindPic(1107, 0, 1277, 77, "QSHL.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->QSHL=" + result);
                gamePublicFunction.recall(true);
                break;
            }
            Thread.sleep(100);
        }
    }

    //移动到皇陵指定坐标
    public void goTargetXY() throws Exception {
        gamePublicFunction.openMapWorldOrCurrent("current");
//        int a=((int[]) TaskMain.xyList.get(QSHL_index))[0];
        //LtLog.i(publicFunction.getLineInfo() + "------->QSHL_index=" + QSHL_index + ",xyList.size:" + TaskMain.xyList.size());

        if (QSHL_index >= TaskMain.xyList.size()) {
            QSHL_index = 0;
        }
        //LtLog.i(publicFunction.getLineInfo() + "------->QSHL_index=" + QSHL_index + ",xyList.size:" + TaskMain.xyList.size());


        LtLog.i(publicFunction.getLineInfo() + "点击坐标："+((int[]) TaskMain.xyList.get(QSHL_index))[0]+","+((int[]) TaskMain.xyList.get(QSHL_index))[1]);

        //LtLog.i(publicFunction.getLineInfo() + "*******************--------------time =" + System.currentTimeMillis());
        screenXY_QSHL(((int[]) TaskMain.xyList.get(QSHL_index))[0], ((int[]) TaskMain.xyList.get(QSHL_index))[1], TaskMain.taskMap.get("layer"));//点击地图坐标
        //LtLog.i(publicFunction.getLineInfo() + "*******************--------------time =" + System.currentTimeMillis());
        //screenXY_QSHL(Integer.parseInt(TaskMain.xystr.split(",")[0]), Integer.parseInt(TaskMain.xystr.split(",")[1]), TaskMain.taskMap.get("layer"));//点击地图坐标
    }

    //得到当前在皇陵中的位置
    public int getCurrentPosition() {
        TreeMap<Double, Integer> positionList = new TreeMap<>();
        result = publicFunction.localFindPic(1170, 1, 1277, 39, "one.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->one=" + result);
            positionList.put((double) result.sim, 1);
        }
        result = publicFunction.localFindPic(1170, 1, 1277, 39, "two.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->two=" + result);
            positionList.put((double) result.sim, 2);
        }
        result = publicFunction.localFindPic(1170, 1, 1277, 39, "three.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->three=" + result);
            positionList.put((double) result.sim, 3);
        }
        double sim = 0.001;
        int position = 0;
        for (double key : positionList.keySet()) {
            if (key > sim) {
                sim = key;
                position = positionList.get(key);
            }
        }
        LtLog.i(publicFunction.getLineInfo() + "------->position=" + position);
        return position;
    }

    //移动到目标地图   传入参数 当前位置 ，目标位置
    public void goTarget(int current, int target) throws Exception {
        String mapName = "";
        LtLog.i(publicFunction.getLineInfo() + "------->current=" + current + ",target=" + target);
        if (current < target) {
            //当前位置小于目标位置，往下走
            switch (current) {
                case 1:
                    mapName = "two1.png";
                    break;
                case 2:
                    mapName = "three1.png";
                    break;
            }
        } else if (current > target) {
            //当前位置大于目标位置，往上走
            switch (current) {
                case 3:
                    mapName = "two1.png";
                    break;
                case 2:
                    mapName = "one1.png";
                    break;
            }
        }

        gamePublicFunction.openMapWorldOrCurrent("current");
        for (int i = 0; i < 4; i++) {
            result = publicFunction.localFindPic(303, 21, 471, 154, "list.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->list=" + result + "mapName=" + mapName);
                result = publicFunction.localFindPic(257, 128, 341, 422, mapName);
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------->" + mapName + "=" + result);
                    publicFunction.rndTap(result.x, result.y, result.x, result.y);
                    Thread.sleep(1000);
                    break;
                }
            } else {
                result = publicFunction.localFindPic(312, 108, 462, 255, "smallMap1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------->list=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 50, 47);
                    Thread.sleep(500);
                }
            }
            Thread.sleep(100);
        }
        gamePublicFunction.closeWindow();
    }



}
