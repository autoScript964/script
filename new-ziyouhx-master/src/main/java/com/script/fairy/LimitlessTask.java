package com.script.fairy;


import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class LimitlessTask extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    TimingActivity timingActivity;
    GameUtil gameUtil;
    OtherGame otherGame;
    SingleTask singleTask;
    TeamTask teamTask;

    public LimitlessTask(AtFairyImpl ATFairy) throws Exception {
        mFairy = ATFairy;
        gameUtil = new GameUtil(mFairy);
        timingActivity = new TimingActivity(mFairy);
        otherGame = new OtherGame(mFairy);
        singleTask = new SingleTask(mFairy);
        teamTask = new TeamTask(mFairy);
    }

    public void inOperation() throws Exception {
        int h = mFairy.dateHour();
        int m = mFairy.dateMinute();
        int w = mFairy.week();

        if (!AtFairyConfig.getTaskID().equals("2033") && !AtFairyConfig.getTaskID().equals("2035") ) {
            result = mFairy.findPic(1144,1,1240,31,new  String[]{"llhj.png","huanjing.png"});
            if (result.sim > 0.8f) {
                for (int i=0;i<10;i++){
                    result = mFairy.findPic(1144,1,1240,31,new  String[]{"llhj.png","huanjing.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("洗刷刷中暂停别的任务"));
                        i=0;
                    }
                    mFairy.condit();
                    Thread.sleep(2000);
                }
            }
        }
        if ((AtFairyConfig.getOption("cfjj").equals("1")||AtFairyConfig.getOption("zzjt").equals("1")||!AtFairyConfig.getOption("jzls").equals("")||AtFairyConfig.getOption("5599").equals("1")||
                AtFairyConfig.getOption("mphw").equals("1")||AtFairyConfig.getOption("ygbh").equals("1")||AtFairyConfig.getOption("pwzz").equals("1")) && (h==19 && m>25 && m<32)) {
                    LtLog.e(mFairy.getLineInfo("19点30分等待任务"));
                    timingActivity.timingActivity();
                    Thread.sleep(1000);
        }

        result = mFairy.findPic(new String[]{"In picture.png", "In picture1.png"});
        LtLog.e(mFairy.getLineInfo("过图："+result.sim));
        if (result.sim > 0.92f) {
            LtLog.e(mFairy.getLineInfo("过图中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }

        result = mFairy.findPic(483,19,767,123,"cysc.png");
        mFairy.onTap(0.8f, result, 1202,58,1213,70,"err商城退出", Sleep);

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

    //  野外挂机
    public void fieldHangMachine() throws Exception {
        new LimitlessTask(mFairy) {
            int ditu, gmx, gmy;
            String inputStr;
            ControlSplit qbtime = null, syyptime = null, optime1 = null, optime2 = null, optime3 = null, optime4 = null, optime5 = null,rePosition = null;

            boolean rePositionBool = true;

            public void create() throws Exception {

                if(!AtFairyConfig.getOption("rePosition").equals("")){
                    rePosition = strSplit(AtFairyConfig.getOption("rePosition"));
                }

                if (!AtFairyConfig.getOption("qbtime").equals("")) {
                    qbtime = strSplit(AtFairyConfig.getOption("qbtime"));
                }

                if (!AtFairyConfig.getOption("syyptime").equals("")) {
                    syyptime = strSplit(AtFairyConfig.getOption("syyptime"));
                }

                if (!AtFairyConfig.getOption("optime1").equals("")) {
                    optime1 = strSplit(AtFairyConfig.getOption("optime1"));
                }

                if (!AtFairyConfig.getOption("optime2").equals("")) {
                    optime2 = strSplit(AtFairyConfig.getOption("optime2"));
                }

                if (!AtFairyConfig.getOption("optime3").equals("")) {
                    optime3 = strSplit(AtFairyConfig.getOption("optime3"));
                }
                if (!AtFairyConfig.getOption("optime4").equals("")) {
                    optime4 = strSplit(AtFairyConfig.getOption("optime4"));
                }

                if (!AtFairyConfig.getOption("optime5").equals("")) {
                    optime5 = strSplit(AtFairyConfig.getOption("optime5"));
                }

                if (AtFairyConfig.getOption("xystr").equals("") || AtFairyConfig.getOption("map").equals("")) {
                    setTaskName(2);
                    return;
                }

                String[] arr = AtFairyConfig.getOption("xystr").split(",");
                gmx = Integer.parseInt(arr[0]);
                gmy = Integer.parseInt(arr[1]);

                ditu = Integer.parseInt(AtFairyConfig.getOption("map"));
                if (ditu == 1) {
                    inputStr = "桃源村";
                }
                if (ditu == 2) {
                    inputStr = "古月平原";
                }
                if (ditu == 3) {
                    inputStr = "华山";
                }
                if (ditu == 4) {
                    inputStr = "出云";
                }
                if (ditu == 5) {
                    inputStr = "幻莹谷";
                }
                if (ditu == 6) {
                    inputStr = "天都";
                }
                if (ditu == 7) {
                    inputStr = "千年之境";
                }
                if (ditu == 8) {
                    inputStr = "楼兰";
                }
                if (ditu == 9) {
                    inputStr = "流沙洞";
                }
                if (ditu == 10) {
                    inputStr = "比翼城";
                }
                if (ditu == 11) {
                    inputStr = "秋风原";
                }
                if (ditu == 12) {
                    inputStr = "银月湖";
                }
                if (ditu == 13) {
                    inputStr = "水晶宫";
                }
                if (ditu == 14) {
                    inputStr = "幽灵洞";
                }
                if (ditu == 15) {
                    inputStr = "冰封雪原";
                }
                if (ditu == 16) {
                    inputStr = "雪原之巅";
                }
                if (ditu == 17) {
                    inputStr = "猫猫城";
                }
                if (ditu == 18) {
                    inputStr = "夜幽谷";
                }
                timingActivity.timingActivity();

                if (optime3 != null && optime3.choice == 1 && timekeep(1, optime3.timeMillis, "野外挂机家园采矿")) {
                    gameUtil.cancelFollowing();
                    otherGame.jywk();
                }
                if (optime4 != null && optime4.choice == 1 && timekeep(1, optime4.timeMillis, "野外挂机家园种菜收菜")) {
                    gameUtil.cancelFollowing();
                    otherGame.jysc();
                }
            }

            public void content_0() throws Exception {
                if(inputStr!=null&&!inputStr.equals("")){
                    gameUtil.goCity(inputStr);
                    gameUtil.coordinate(inputStr,gmx,gmy);
                }

                //gameUtil.callToFollow();
                Thread.sleep(1000);
                //gameUtil.cancelFollowing();
                gameUtil.callToFollow();
                Thread.sleep(1000);
                gameUtil.cancelFollowing();
                setTaskName(1);return;
            }

            public  void content_1() throws Exception {
                timingActivity.timingActivity();

                gameUtil.zidong();

                if (AtFairyConfig.getOption("fuhuo").equals("1")) {
                    result = mFairy.findPic(491, 133, 784, 539, "fuhuo2.png");
                    mFairy.onTap(0.8f, result, "原地复活", Sleep);
                } else {
                    result = mFairy.findPic(491, 133, 784, 539, "fuhuo1.png");
                    mFairy.onTap(0.8f, result, "默认复活", Sleep);
                    if (result.sim>0.8f){
                        setTaskName(0);return;
                    }
                }

                if(rePosition!=null && rePosition.choice==1 ){
                    if (timekeep(0,rePosition.timeMillis, "野外挂机每隔半个小时重新确定一次位置")) {
                        LtLog.e(mFairy.getLineInfo("开始重新定位"));
                        setTaskName(0);
                        return;
                    }
                }else{
                    int m = mFairy.dateMinute();
                    if(m==10 || m ==30 || m==55){
                        if(rePositionBool){
                            LtLog.e(mFairy.getLineInfo("默认重新定位"));

                            rePositionBool=false;
                            setTaskName(0);
                            return;
                        }
                    }else{
                        rePositionBool=true;
                    }
                }



                if (AtFairyConfig.getOption("5619").equals("1") &&qbtime != null && timekeep(0, qbtime.timeMillis, "野外挂机清包")) {
                    gameUtil.clearBag();
                }
                if (AtFairyConfig.getOption("5617").equals("1") &&syyptime != null  && timekeep(0, syyptime.timeMillis, "野外挂机使用药品")) {
                    gameUtil.syyp();
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

                if (optime3 != null && optime3.choice == 1 && timekeep(0, optime3.timeMillis, "野外挂机家园采矿")) {
                    gameUtil.cancelFollowing();
                    otherGame.jywk();
                    setTaskName(0);return;
                }
                if (optime4 != null && optime4.choice == 1 && timekeep(0, optime4.timeMillis, "野外挂机家园种菜收菜")) {
                    gameUtil.cancelFollowing();
                    otherGame.jysc();
                    setTaskName(0);return;
                }
                if (mFairy.dateHour()==0 && mFairy.dateMinute()==0){
                    mFairy.restart();
                }
                if (timingActivity.timingActivity()==1){ //限时任务
                    setTaskName(0);
                    return;
                }
            }

            boolean reopen=true ,timingInit=true;

            public  void content_2() throws Exception {
                gameUtil.fuhuo();
                if (AtFairyConfig.getOption("5619").equals("1") &&qbtime != null  && timekeep(0, qbtime.timeMillis, "野外挂机清包")) {
                    gameUtil.clearBag();
                }
                if (AtFairyConfig.getOption("5617").equals("1") &&syyptime != null  && timekeep(0, syyptime.timeMillis, "野外挂机使用药品")) {
                    gameUtil.syyp();
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

                /*if (optime2 != null && optime2.choice == 1 && timekeep(1, optime2.timeMillis, "野外挂机召唤跟随")) {
                    gameUtil.callToFollow();
                    Thread.sleep(60000);
                    gameUtil.cancelFollowing();
                }*/


                if (optime3 != null && optime3.choice == 1 && timekeep(0, optime3.timeMillis, "野外挂机家园采矿")) {
                    gameUtil.cancelFollowing();
                    otherGame.jywk();
                    setTaskName(0);return;
                }
                if (optime4 != null && optime4.choice == 1 && timekeep(0, optime4.timeMillis, "野外挂机家园种菜收菜")) {
                    gameUtil.cancelFollowing();
                    otherGame.jysc();
                    setTaskName(0);return;
                }
                if (mFairy.dateHour()==0 && mFairy.dateMinute()==0){
                    mFairy.restart();
                }
                if (timingActivity.timingActivity()==1){
                    setTaskName(0);
                    return;
                }
            }

        }.taskContent(mFairy, "野外挂机中");
    }

    //  钓鱼挂机
    public void goFishing() throws Exception {
        new LimitlessTask(mFairy) {
            ControlSplit  optime3 = null, optime4 = null;
            public void create() throws Exception {
                if (!AtFairyConfig.getOption("optime3").equals("")) {
                    optime3 = strSplit(AtFairyConfig.getOption("optime3"));
                }
                if (!AtFairyConfig.getOption("optime4").equals("")) {
                    optime4 = strSplit(AtFairyConfig.getOption("optime4"));
                }
                timingActivity.timingActivity();
            }
            public void content_0() throws Exception {
                gameUtil.lkfb();
                gameUtil.goCity("家族");
                setTaskName(1);
            }
            public  void content_1() throws Exception {
                if (overtime(15, 0)) return;
                result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                mFairy.onTap(0.8f, result, 1189, 91, 1209, 105, "找到活动打开地图", Sleep);

                result = mFairy.findPic("ctiyinface.png");
                mFairy.onTap(0.8f, result, "切换到地图", Sleep);

                result = mFairy.findPic(new String[]{"mapinface.png","mapinface1.png"});
                mFairy.onTap(0.8f, result,  917, 110, 918, 111,"去钓鱼点", Sleep);
                if (result.sim > 0.8f) {
                    gameUtil.close(1);
                    Thread.sleep(30000);
                    result = mFairy.findPic(957, 171, 1122, 248,"wydy.png");
                    mFairy.onTap(0.8f, result, "我要钓鱼", Sleep);
                    if (result.sim > 0.8f) {
                        setTaskName(2);return;
                    }
                }
            }
            public  void content_2() throws Exception {
                if (overtime(15, 0)) return;
                result = mFairy.findPic("Fishing gear interface.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(492, 225, 584, 316,"have fishing.png");
                    mFairy.onTap(0.8f, result, "没有鱼竿", Sleep);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("yjgm.png");
                        if (result.sim > 0.8f) {
                            if (AtFairyConfig.getOption("yg").equals("1")) {
                                mFairy.onTap(0.8f, result,  178,188,201,208,"绿色钓竿", Sleep);
                            } else if (AtFairyConfig.getOption("yg").equals("2")) {
                                mFairy.onTap(0.8f, result,  557,189,584,201,"蓝色钓竿", Sleep);
                            } else if (AtFairyConfig.getOption("yg").equals("3")) {
                                mFairy.onTap(0.8f, result,   184,292,201,306,"紫色钓竿", Sleep);
                            } else if (AtFairyConfig.getOption("yg").equals("4")) {
                                mFairy.onTap(0.8f, result,   559,295,577,312,"超级钓竿", Sleep);
                            }
                            mFairy.onTap(0.8f, result, "购买", Sleep);
                            mFairy.onTap(1179,86,1194,100,"关闭", Sleep);
                        }
                    }
                    result = mFairy.findPic(496, 328, 582, 418,"have fishing.png");
                    mFairy.onTap(0.8f, result, "没有鱼饵", Sleep);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("yjgm.png");
                        if (result.sim > 0.8f) {
                            if (AtFairyConfig.getOption("yr").equals("1")) {
                                mFairy.onTap(0.8f, result,  183,253,205,272,"蚯蚓", Sleep);
                            } else if (AtFairyConfig.getOption("yr").equals("2")) {
                                mFairy.onTap(0.8f, result,  556,259,577,273,"红虫", Sleep);
                            } else if (AtFairyConfig.getOption("yr").equals("3")) {
                                mFairy.onTap(0.8f, result,   183,366,204,380,"封肉", Sleep);
                            } else if (AtFairyConfig.getOption("yr").equals("4")) {
                                mFairy.onTap(0.8f, result,   552,363,575,377,"虾", Sleep);
                            }
                            for (int i = 0; i < 99; i++) {
                                mFairy.tap(1029, 616);
                            }
                            mFairy.onTap(0.8f, result, "购买", Sleep);
                            mFairy.onTap(1179,86,1194,100,"关闭", Sleep);
                        }
                    }
                    result = mFairy.findPic("Fishing gear interface.png");
                    mFairy.onTap(0.8f, result, 738,502,762,515,"渔具界面", Sleep);
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("Fishing gear interface.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("包满了钓鱼结束"));
                            setTaskEnd();return;
                        }else {
                            setTaskName(3);return;
                        }
                    }

                }
            }
            public  void content_3() throws Exception {
                if (overtime(15, 0)){
                    result = mFairy.findPic(957, 171, 1122, 248,"wydy.png");
                    mFairy.onTap(0.8f, result, "我要钓鱼", Sleep);
                    if (result.sim > 0.8f) {
                        setTaskName(2);return;
                    }
                    return;
                }
                result = mFairy.findPic("Fishing.png");
                mFairy.onTap(0.8f, result, "钓鱼中", Sleep);
                if (result.sim > 0.8f) {
                    err=0;
                }
                if (optime3 != null && optime3.choice == 1 && timekeep(0, optime3.timeMillis, "野外挂机家园采矿")) {
                    gameUtil.lkfb();
                    gameUtil.cancelFollowing();
                    otherGame.jywk();
                    setTaskName(0);return;
                }
                if (optime4 != null && optime4.choice == 1 && timekeep(0, optime4.timeMillis, "野外挂机家园种菜收菜")) {
                    gameUtil.lkfb();
                    gameUtil.cancelFollowing();
                    otherGame.jysc();
                    setTaskName(0);return;
                }
                if (mFairy.dateHour()==0 && mFairy.dateMinute()==0){
                    mFairy.restart();
                }
                if (timingActivity.timingActivity()==1){
                    setTaskName(0);
                    return;
                }
            }
        }.taskContent(mFairy, "钓鱼中");
    }
}
