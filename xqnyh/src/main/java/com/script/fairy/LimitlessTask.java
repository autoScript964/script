package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.TaskContent;
import com.script.framework.AtFairyImpl;


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

    public LimitlessTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gameUtil = new GameUtil(mFairy);
        timingActivity = new TimingActivity(mFairy);
        otherGame = new OtherGame(mFairy);
        singleTask = new SingleTask(mFairy);
        teamTask = new TeamTask(mFairy);
    }

    public void inOperation() throws Exception {
        result = mFairy.findPic("Over drawing.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("过图中"));
            mFairy.initMatTime();
            err = 0;
        }
        result = mFairy.findPic(new String[]{"nogang.png", "nogang1.png"});
        if (result.sim > 0.7f) {
            LtLog.e(mFairy.getLineInfo("没有帮会"));
            Thread.sleep(2000);
            setTaskEnd();
            return;
        }
        gameUtil.zh();
        gameUtil.gdFBTeam();
    }

    //  野外挂机
    public void fieldHangMachine() throws Exception {
        new TeamTask(mFairy) {
            int ditu, ditu1, gmx, gmy, ret;
            String str, str1, inputStr;
            ControlSplit zhgs = null, sydh = null, syyp = null, qxgs = null, sfjn = null, fxmd = null, zdhh = null, qb = null, zcsc = null, cwjq = null, zcjc = null, cxdw = null;


            public void create() throws Exception {


                if (!AtFairyConfig.getOption("optime6").equals("")) {
                    zhgs = strSplit(AtFairyConfig.getOption("optime6"));
                }
                if (!AtFairyConfig.getOption("optime2").equals("")) {
                    sydh = strSplit(AtFairyConfig.getOption("optime2"));
                }

                if (!AtFairyConfig.getOption("optime1").equals("")) {
                    syyp = strSplit(AtFairyConfig.getOption("optime1"));
                }

                if (!AtFairyConfig.getOption("optime7").equals("")) {
                    qxgs = strSplit(AtFairyConfig.getOption("optime7"));
                }
                if (!AtFairyConfig.getOption("optime8").equals("")) {
                    sfjn = strSplit(AtFairyConfig.getOption("optime8"));
                }
                if (!AtFairyConfig.getOption("optime9").equals("")) {
                    cxdw = strSplit(AtFairyConfig.getOption("optime9"));
                }
                if (!AtFairyConfig.getOption("optime5").equals("")) {
                    fxmd = strSplit(AtFairyConfig.getOption("optime5"));
                }
                if (!AtFairyConfig.getOption("optime3").equals("")) {
                    zdhh = strSplit(AtFairyConfig.getOption("optime3"));
                }

                if (!AtFairyConfig.getOption("qbtime").equals("")) {
                    qb = strSplit(AtFairyConfig.getOption("qbtime"));
                }

                if (!AtFairyConfig.getOption("sctime").equals("")) {
                    zcsc = strSplit(AtFairyConfig.getOption("sctime"));
                }
                if (!AtFairyConfig.getOption("opcount1").equals("")) {
                    cwjq = strSplit(AtFairyConfig.getOption("opcount1"));
                }
                if (!AtFairyConfig.getOption("zcjc").equals("")) {
                    zcjc = strSplit(AtFairyConfig.getOption("zcjc"));
                }
                if (AtFairyConfig.getOption("xystr").equals("") || AtFairyConfig.getOption("ditu").equals("")) {
                    LtLog.e(mFairy.getLineInfo("没有勾选地图或者坐标"));
                    setTaskName(6);
                    return;
                }
                String[] arr = AtFairyConfig.getOption("xystr").split(",");
                gmx = Integer.parseInt(arr[0]);
                gmy = Integer.parseInt(arr[1]);
                ditu = Integer.parseInt(!AtFairyConfig.getOption("ditu").equals("") ? AtFairyConfig.getOption("ditu") : "0");
                if (ditu == 1) {
                    str = "30level.png";
                    inputStr = "兰若寺";
                }
                if (ditu == 2) {
                    str = "40level.png";
                    inputStr = "黑风洞";
                }
                if (ditu == 3) {
                    str = "50level.png";
                    inputStr = "兰若地宫";
                }
                if (ditu == 4) {
                    str = "70level.png";
                    inputStr = "丝路古道";
                }

                if (ditu == 5) {
                    str = "60level.png";
                    inputStr = "忘川";
                }

                if (ditu == 6) {
                    str = "80level.png";
                    inputStr = "黄泉";
                }

                if (ditu == 7) {
                    str = "90level.png";
                    inputStr = "酆都";
                }

                if (ditu == 8) {
                    str = "100level.png";
                    inputStr = "地狱";
                }

                if (ditu == 9) {
                    str = "110level.png";
                    inputStr = "天宫";
                }

                if (ditu == 10) {
                    str = "120level.png";
                    inputStr = "兜率宫";
                }

                if (ditu == 11) {
                    str = "hangzhou.png";
                    inputStr = "杭州";
                }

                if (ditu == 12) {
                    str = "yangjiazhen.png";
                    inputStr = "杨家镇";
                }

                if (ditu == 13) {
                    str = "zhenjiaohuangye.png";
                    inputStr = "镇郊荒野";
                }

                if (ditu == 14) {
                    str = "shequ.png";
                    inputStr = "家园";
                }

                if (ditu == 15) {
                    str = "pujiacun.png";
                    inputStr = "蒲家村";
                }

                if (ditu == 16) {
                    inputStr = "帮会";
                }

                timingActivity.timingActivity();
            }

            public void content_0() throws Exception {
                create();
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {

                LtLog.e(mFairy.getLineInfo("str :"+str));
                LtLog.e(mFairy.getLineInfo("inputStr :"+inputStr));

                if (str != null) {
                    result = mFairy.findPic(str);
                    if (result.sim > 0.8f && ditu != 16) {
                        LtLog.e(mFairy.getLineInfo("已到达指定地图"));
                        setTaskName(4);
                        return;
                    }
                }

                ret = gameUtil.goCity(inputStr);
                if (ret == 1) {
                    LtLog.e(mFairy.getLineInfo("已到达指定地图"));
                    setTaskName(4);
                } else {
                    ditu1 = ditu;
                    setTaskName(2);
                }

            }

            public void content_2() throws Exception {
                if (ditu1 <= 3) {
                    ret = gameUtil.goCity("兰若寺");
                }
                if (ditu1 == 4) {
                    ret = gameUtil.goCity("天工阁");
                }
                if (ditu1 == 5) {
                    ret = gameUtil.goCity("天工阁");
                }
                if (ditu1 == 6) {
                    ret = gameUtil.goCity("忘川");
                }
                if (ditu1 == 7) {
                    ret = gameUtil.goCity("黄泉");
                }
                if (ditu1 == 8) {
                    ret = gameUtil.goCity("酆都");
                }
                if (ditu1 == 9) {
                    ret = gameUtil.goCity("逍遥观");
                }
                if (ditu1 == 10) {
                    ret = gameUtil.goCity("天宫");
                }
                if (ret == 1) {
                    setTaskName(3);
                } else {
                    ditu1 = ditu1 - 1;
                }
            }

            public void content_3() throws Exception {
                if (ditu1 == 2) {
                    //兰若寺-黑风洞
                    gameUtil.coordinate("兰若寺", 20, 41);
                    str1 = "40level.png";
                }
                if (ditu1 == 3) {
                    //兰若寺-兰若寺地宫
                    gameUtil.coordinate("兰若地宫", 110, 49);
                    str1 = "50level.png";
                }
                if (ditu1 == 4) {
                    //天工阁-丝绸古道
                    gameUtil.coordinate("天工阁", 67, 117);
                    str1 = "70level.png";
                }
                if (ditu1 == 5) {
                    //丝绸古道-忘川
                    gameUtil.coordinate("天工阁", 67, 117);
                    gameUtil.coordinate("丝绸古道", 43, 14);
                    str1 = "60level.png";
                }
                if (ditu1 == 6) {
                    //忘川-黄泉
                    gameUtil.coordinate("忘川", 177, 72);
                    str1 = "80level.png";
                }
                if (ditu1 == 7) {
                    //黄泉-酆都
                    gameUtil.coordinate("黄泉", 94, 95);
                    str1 = "90level.png";
                }
                if (ditu1 == 8) {
                    //酆都-地狱
                    gameUtil.coordinate("酆都", 94, 41);
                    str1 = "100level.png";
                }
                if (ditu1 == 9) {
                    //逍遥观-天宫
                    gameUtil.coordinate("逍遥观", 69, 10);
                    str1 = "110level.png";
                }
                if (ditu1 == 10) {
                    //天宫-兜率宫
                    gameUtil.coordinate("天宫", 134, 177);
                    str1 = "120level.png";
                }

                if (ditu == ditu1) {
                    result = mFairy.findPic(str);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("已到达指定地图"));
                        setTaskName(4);
                        return;
                    } else {
                        setTaskName(0);
                        return;
                    }
                } else {
                    result = mFairy.findPic(str1);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("已到达跑路地图"));
                        ditu1 = ditu1 + 1;
                    } else {
                        setTaskName(0);
                        return;
                    }
                }
            }

            public void content_4() throws Exception {
                gameUtil.coordinate(inputStr, gmx, gmy);
                result = mFairy.findPic(str);
                LtLog.e(mFairy.getLineInfo("此地图的相似度为：" + result.sim));
                if (result.sim < 0.7f) {
                    LtLog.e(mFairy.getLineInfo("到达坐标检测地图发现不对从来"));
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("Hangup.png");
                mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);

                if (!AtFairyConfig.getOption("fx").equals("")) {
                    gameUtil.dividing();
                }
                gameUtil.callToFollow();
                Thread.sleep(5000);
                gameUtil.cancelFollowing();

                setTaskName(5);
            }

            boolean reopen = true, timingInit = true;

            public void content_5() throws Exception {

                gameUtil.apply();

                if (!str.equals("shequ.png") && timekeep(1, 30000, "野外挂机开启挂机")) {
                    if (AtFairyConfig.getOption("gbgj").equals("1")) {
                        result = mFairy.findPic("Hangup1.png");
                        mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", Sleep);
                    } else {
                        result = mFairy.findPic("Hangup.png");
                        mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);
                    }
                }

                if (cxdw != null && cxdw.choice == 1 && timekeep(0, cxdw.timeMillis, "野外挂机每隔半个小时重新确定一次位置")) {
                    setTaskName(0);
                    return;
                }

                if (zhgs != null && zhgs.choice == 1 && (timekeep(1, zhgs.timeMillis, "野外挂机拉跟随"))) {
                    gameUtil.callToFollow();
                    if (qxgs != null && qxgs.choice == 1) {
                        Thread.sleep(qxgs.timeMillis);
                        gameUtil.cancelFollowing();
                    }
                }

                if (timekeep(0, 100000, "cancel")) {
                    gameUtil.cancelFollowing();
                }

                if (AtFairyConfig.getOption("4689").equals("1") && qb != null && timekeep(0, qb.timeMillis, "野外挂机清包")) {
                    gameUtil.clearbag();
                }
                if (sydh != null && sydh.choice == 1 && timekeep(0, sydh.timeMillis, "野外挂机定魂")) {
                    otherGame.dinghun();
                }
                if (syyp != null && syyp.choice == 1 && timekeep(0, syyp.timeMillis, "野外挂机使用蓝药")) {
                    otherGame.eatLan();
                }
                if (sfjn != null && sfjn.choice == 1 && timekeep(0, sfjn.timeMillis, "野外挂机释放技能")) {
                    otherGame.sfjn();
                }
                if (fxmd != null && fxmd.choice == 1 && timekeep(0, fxmd.timeMillis, "野外挂机分享梦岛")) {
                    otherGame.fxkj();
                }
                if (zdhh != null && zdhh.choice == 1 && timekeep(0, zdhh.timeMillis, "野外挂机喊话")) {
                    if (!AtFairyConfig.getOption("hanhua").equals("")) {
                        otherGame.hanhua(AtFairyConfig.getOption("hanhua"));
                    }
                }
                if (AtFairyConfig.getOption("4637").equals("1") && zcjc != null && AtFairyConfig.getOption("zcsfjc").equals("1") && timekeep(0, zcjc.timeMillis, "野外挂机收菜种菜检测")) {
                    otherGame.homefoodjc();
                }

                if (AtFairyConfig.getOption("4637").equals("1") && zcsc != null && timekeep(0, zcsc.timeMillis, "野外挂机收菜种菜")) {
                    otherGame.homefood();
                    if (AtFairyConfig.getOption("jzc").equals("1")) {
                        otherGame.homebag();
                    }
                    setTaskName(0);
                    return;
                }


                if (AtFairyConfig.getOption("0t").equals("1") && reopen && mFairy.dateHour() == 0 && mFairy.dateMinute() > 1) {
                    timingActivity = new TimingActivity(mFairy);
                    reopen = false;
                    reopen();
                    setTaskName(0);
                    return;
                }


                if (mFairy.dateHour() != 0) {
                    reopen = true;
                    timingInit = true;
                }


                if (mFairy.dateHour() == 0 && timingInit) {
                    timingActivity = new TimingActivity(mFairy);
                    timingInit = false;
                }

                if (timingActivity.timingActivity() == 1) {
                    setTaskName(0);
                    return;
                }


                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("ydfh").equals("1")) {
                        result = mFairy.findPic(445, 134, 827, 579, "ydfh.png");
                        mFairy.onTap(0.8f, result, "原地复活", Sleep);
                    } else {
                        mFairy.onTap(0.8f, result, "默认复活", Sleep);
                    }
                    setTaskName(0);
                    return;
                }
                Thread.sleep(3000);
            }

            public void content_6() throws Exception {

                if (zhgs != null && zhgs.choice == 1 && timekeep(1, zhgs.timeMillis, "野外挂机拉跟随")) {
                    gameUtil.callToFollow();
                    if (qxgs != null && qxgs.choice == 1) {
                        Thread.sleep(qxgs.timeMillis);
                        gameUtil.cancelFollowing();
                    }
                }

                if (timekeep(0, 100000, "cancel")) {
                    gameUtil.cancelFollowing();
                }

                if (AtFairyConfig.getOption("4689").equals("1") && qb != null && timekeep(0, qb.timeMillis, "野外挂机清包")) {
                    gameUtil.clearbag();
                }
                if (sydh != null && sydh.choice == 1 && timekeep(0, sydh.timeMillis, "野外挂机定魂")) {
                    otherGame.dinghun();
                }
                if (syyp != null && syyp.choice == 1 && timekeep(0, syyp.timeMillis, "野外挂机使用蓝药")) {
                    otherGame.eatLan();
                }
                if (sfjn != null && sfjn.choice == 1 && timekeep(0, sfjn.timeMillis, "野外挂机释放技能")) {
                    otherGame.sfjn();
                }
                if (fxmd != null && fxmd.choice == 1 && timekeep(0, fxmd.timeMillis, "野外挂机分享梦岛")) {
                    otherGame.fxkj();
                }
                if (zdhh != null && zdhh.choice == 1 && timekeep(0, zdhh.timeMillis, "野外挂机喊话")) {
                    if (!AtFairyConfig.getOption("hanhua").equals("")) {
                        otherGame.hanhua(AtFairyConfig.getOption("hanhua"));
                    }
                }
                if (AtFairyConfig.getOption("4637").equals("1") && zcjc != null && AtFairyConfig.getOption("zcsfjc").equals("1") && timekeep(0, zcjc.timeMillis, "野外挂机收菜种菜检测")) {
                    otherGame.homefoodjc();
                }
                if (AtFairyConfig.getOption("4637").equals("1") && zcsc != null && timekeep(0, zcsc.timeMillis, "野外挂机收菜种菜")) {
                    otherGame.homefood();
                    if (AtFairyConfig.getOption("jzc").equals("1")) {
                        otherGame.homebag();
                    }
                }

                if (AtFairyConfig.getOption("0t").equals("1") && reopen && mFairy.dateHour() == 0 && mFairy.dateMinute() > 1) {
                    timingActivity = new TimingActivity(mFairy);
                    reopen = false;
                    reopen();
                }

                if (mFairy.dateHour() != 0) {
                    reopen = true;
                    timingInit = true;
                }

                if (mFairy.dateHour() == 0 && timingInit) {
                    timingActivity = new TimingActivity(mFairy);
                    timingInit = false;
                }
                if (timingActivity.timingActivity() == 1) {
                    create();
                    return;
                }
                gameUtil.apply();
                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("ydfh").equals("1")) {
                        result = mFairy.findPic(445, 134, 827, 579, "ydfh.png");
                        mFairy.onTap(0.8f, result, "原地复活", Sleep);
                    } else {
                        mFairy.onTap(0.8f, result, "默认复活", Sleep);
                    }
                }
                Thread.sleep(3000);
            }

        }.taskContent(mFairy, "野外挂机中");
    }

    long time_color = System.currentTimeMillis();

    // 固定队跟队
    public void fixedTeam() throws Exception {
        new TeamTask(mFairy) {
            ControlSplit zhgs = null, sydh = null, syyp = null, qxgs = null, sfjn = null, fxmd = null, zdhh = null, qb = null, zcsc = null, cwjq = null, zcjc = null, gsdz = null;
            ControlSplit diy_time = null;
            public void create() throws Exception {
             /*   if (!AtFairyConfig.getOption("optime6").equals("")) {
                    zhgs = strSplit(AtFairyConfig.getOption("optime6"));
                }*/

                time_color = System.currentTimeMillis();

                if (!AtFairyConfig.getOption("diy_time").equals("")) {
                    diy_time = strSplit(AtFairyConfig.getOption("diy_time"));
                }

                if (!AtFairyConfig.getOption("optime2").equals("")) {
                    sydh = strSplit(AtFairyConfig.getOption("optime2"));
                }

                if (!AtFairyConfig.getOption("optime1").equals("")) {
                    syyp = strSplit(AtFairyConfig.getOption("optime1"));
                }
                if (!AtFairyConfig.getOption("optime6").equals("")) {
                    gsdz = strSplit(AtFairyConfig.getOption("optime6"));
                }
                if (!AtFairyConfig.getOption("optime7").equals("")) {
                    qxgs = strSplit(AtFairyConfig.getOption("optime7"));
                }
                if (!AtFairyConfig.getOption("optime8").equals("")) {
                    sfjn = strSplit(AtFairyConfig.getOption("optime8"));
                }
                if (!AtFairyConfig.getOption("optime5").equals("")) {
                    fxmd = strSplit(AtFairyConfig.getOption("optime5"));
                }
                if (!AtFairyConfig.getOption("optime3").equals("")) {
                    zdhh = strSplit(AtFairyConfig.getOption("optime3"));
                }

                if (!AtFairyConfig.getOption("qbtime").equals("")) {
                    qb = strSplit(AtFairyConfig.getOption("qbtime"));
                }

                if (!AtFairyConfig.getOption("sctime").equals("")) {
                    zcsc = strSplit(AtFairyConfig.getOption("sctime"));
                }
                if (!AtFairyConfig.getOption("opcount1").equals("")) {
                    cwjq = strSplit(AtFairyConfig.getOption("opcount1"));
                }
                if (!AtFairyConfig.getOption("zcjc").equals("")) {
                    zcjc = strSplit(AtFairyConfig.getOption("zcjc"));
                }

                timingActivity.timingActivity1();
            }

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            boolean reopen = true, timingInit = true;

            @Override
            public void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(1071,326,1260,429,"tongyi1.png");
                mFairy.onTap(0.8f,result,"同意",1000);


            }

            public void content_1() throws Exception {

                if (AtFairyConfig.getOption("gbgj").equals("1")) {
                    result = mFairy.findPic("Hangup1.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", Sleep);
                } else {
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);
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


                result = mFairy.findPic("pac.png");
                if(result.sim>0.8f){

                    result = mFairy.findPic(745, 68, 1265, 333, new String[]{"Replica.png", "fuben.png"});
                    long color = mFairy.getColorNum(521,24,689,44,"255,239,140",0.95f);
                    if(color>10 || result.sim>0.8f){
                        time_color = System.currentTimeMillis();
                    }else{
                        if(System.currentTimeMillis() - time_color>240000){
                            gameUtil.close(0);

                            Boolean gsdzb = false;

                            LtLog.e(mFairy.getLineInfo("【跟随队长】"));
                            for (int i =0; i<10;i++){
                                result = mFairy.findPic("Contingenthurdles.png");
                                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                                result = mFairy.findPic("Contingenthurdles1.png");
                                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                                result = mFairy.findPic(1, 190, 239, 559, "gsdz.png");
                                mFairy.onTap(0.75f, result, "左侧跟随队长", Sleep);
                                if (result.sim > 0.75f) {
                                    gsdzb=true;
                                    break;
                                }
                            }
                            gameUtil.close(0);
                            if(gsdzb) {
                                Thread.sleep(3000);
                                if (AtFairyConfig.getOption("gbgj").equals("1")) {
                                    result = mFairy.findPic("Hangup1.png");
                                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", Sleep);
                                } else {
                                    result = mFairy.findPic("Hangup.png");
                                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);
                                }

                                gameUtil.cancelFollowing();
                            }

                            time_color = System.currentTimeMillis();
                        }
                    }
                }

                /*   if (zhgs != null && zhgs.choice == 1 && timekeep(0, zhgs.timeMillis, "固定队跟队取消跟随")) {
                    gameUtil.cancelFollowing();
                }*/

                /*if (timekeep(0, 100000, "cancel")) {
                    gameUtil.cancelFollowing();
                }*/

                if (AtFairyConfig.getOption("4775").equals("1") && qb != null && timekeep(0, qb.timeMillis, "固定队跟队清包")) {
                    gameUtil.clearbag();
                }

                if (sydh != null && sydh.choice == 1 && timekeep(0, sydh.timeMillis, "固定队跟队定魂")) {
                    otherGame.dinghun();
                }
                if (gsdz != null && gsdz.choice == 1 && timekeep(1, gsdz.timeMillis, "固定队跟队跟随队长")) {
                    gameUtil.callToFollowduizhang();
                    Thread.sleep(5000);

                    if(AtFairyConfig.getOption("gqx").equals("1")) {
                        gameUtil.cancelFollowing();
                    }

                    if (AtFairyConfig.getOption("gbgj").equals("1")) {
                        result = mFairy.findPic("Hangup1.png");
                        mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", Sleep);
                    } else {
                        result = mFairy.findPic("Hangup.png");
                        mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);
                    }
                }


                if (syyp != null && syyp.choice == 1 && timekeep(0, syyp.timeMillis, "固定队跟队使用蓝药")) {
                    otherGame.eatLan();
                }
                if (sfjn != null && sfjn.choice == 1 && timekeep(0, sfjn.timeMillis, "固定队跟队释放技能")) {
                    otherGame.sfjn();
                }
                if (fxmd != null && fxmd.choice == 1 && timekeep(0, fxmd.timeMillis, "固定队跟队分享梦岛")) {
                    otherGame.fxkj();
                }
                if (zdhh != null && zdhh.choice == 1 && timekeep(0, zdhh.timeMillis, "固定队跟队喊话")) {
                    if (!AtFairyConfig.getOption("hanhua").equals("")) {
                        otherGame.hanhua(AtFairyConfig.getOption("hanhua"));
                    }
                }
                if (AtFairyConfig.getOption("4731").equals("1") && zcjc != null && AtFairyConfig.getOption("zcsfjc").equals("1") && timekeep(0, zcjc.timeMillis, "固定队跟队收菜种菜检测")) {
                    otherGame.homefoodjc();

                    gameUtil.close(1);

                    result = mFairy.findPic(806, 597, 1275, 710, "qhhome.png");
                    if (result.sim > 0.85f) {
                        timeKeepMap.remove("固定队跟队跟随队长");
                    }
                }
                if (AtFairyConfig.getOption("4731").equals("1") && zcsc != null && timekeep(0, zcsc.timeMillis, "固定队跟队收菜种菜")) {
                    otherGame.homefood();
                    if (AtFairyConfig.getOption("jzc").equals("1")) {
                        otherGame.homebag();
                    }
                    gameUtil.close(1);

                    result = mFairy.findPic(806, 597, 1275, 710, "qhhome.png");
                    if (result.sim > 0.85f) {
                        timeKeepMap.remove("固定队跟队跟随队长");
                    }
                }

                if (AtFairyConfig.getOption("0t").equals("1") && reopen && mFairy.dateHour() == 0 && mFairy.dateMinute() > 1) {
                    timingActivity = new TimingActivity(mFairy);
                    GameUtil.sb = true;
                    reopen = false;
                    reopen1();
                    setTaskName(0);
                    return;
                }else {
                    if (AtFairyConfig.getOption("diy").equals("1") && reopen && diy_time != null) {

                        if (diy_time.h == mFairy.dateHour() && diy_time.m == mFairy.dateMinute()) {
                            timingActivity = new TimingActivity(mFairy);
                            GameUtil.sb = true;
                            reopen = false;
                            reopen1();
                            setTaskName(0);
                            return;
                        }

                    } else {
                        reopen = true;
                        timingInit = true;
                    }
                }

                if (mFairy.dateHour() == 0 && timingInit) {
                    GameUtil.sb = true;
                    timingActivity = new TimingActivity(mFairy);
                    timingInit = false;
                }
                if (timingActivity.timingActivity1() == 1) {
                    setTaskName(0);
                    return;
                }
                Thread.sleep(3000);
            }
        }.taskContent(mFairy, "固定队跟队中");
    }

    //0点从开
    public void reopen() throws Exception {

        TaskMain.list.clear();

        if (AtFairyConfig.getOption("juese_1").equals("1")) {
            TaskMain.list.add("1");
        }
        if (AtFairyConfig.getOption("juese_2").equals("1")) {
            TaskMain.list.add("2");
        }
        if (AtFairyConfig.getOption("juese_3").equals("1")) {
            TaskMain.list.add("3");
        }
        if (AtFairyConfig.getOption("juese_4").equals("1")) {
            TaskMain.list.add("4");
        }
        if (AtFairyConfig.getOption("jues_1").equals("1")) {
            TaskMain.list.add("1");
        }
        if (AtFairyConfig.getOption("jues_2").equals("1")) {
            TaskMain.list.add("2");
        }
        if (AtFairyConfig.getOption("jues_3").equals("1")) {
            TaskMain.list.add("3");
        }
        if (AtFairyConfig.getOption("jues_4").equals("1")) {
            TaskMain.list.add("4");
        }
        for (int i = 0; i < 10; i++) {


            if (AtFairyConfig.getOption("4689").equals("1")) {
                gameUtil.clearbag();
            }
            //带队一条龙
            if (AtFairyConfig.getOption("dd").equals("1")) {
                teamTask.aDragon();
            }
            //跟队一条龙
            if (AtFairyConfig.getOption("gd").equals("1")) {
                teamTask.aDragon1();
            }

            gameUtil.cancelFollowing();

            if (AtFairyConfig.getOption("4689").equals("1")) {
                gameUtil.clearbag();
            }
            //签到
            if (AtFairyConfig.getOption("qd").equals("1")) {
                otherGame.welfare();
            }

            //师门
            if (AtFairyConfig.getOption("sm").equals("1")) {
                singleTask.master();
            }
            //师门课业
            if (AtFairyConfig.getOption("5037").equals("1")) {
                singleTask.master1();
            }
            //宝图
            if (AtFairyConfig.getOption("bt").equals("1")) {
                singleTask.map();
            }
            if ((AtFairyConfig.getOption("cbtpt").equals("1") || AtFairyConfig.getOption("cbtzj").equals("1") || AtFairyConfig.getOption("cbtgj").equals("1") || AtFairyConfig.getOption("cbthj").equals("1"))) {
                singleTask.digMap();
            }
            //联赛战龙
            if (AtFairyConfig.getOption("lszl").equals("1")) {
                singleTask.lsWar();
            }
            //战龙
            if (AtFairyConfig.getOption("zlt").equals("1")) {
                singleTask.war();
            }
            //联赛备战
            if (AtFairyConfig.getOption("lscg").equals("1")) {
                singleTask.lsroutine();
            }
            //联赛门派挑战
            if (AtFairyConfig.getOption("lsmptz").equals("1")) {
                singleTask.lsFactions();
            }
            //门派挑战
            if ((AtFairyConfig.getOption("mptz").equals("1") || AtFairyConfig.getOption("mptz1").equals("1"))) {
                singleTask.factions();
            }
            //货运
            if (AtFairyConfig.getOption("hyrw").equals("1")) {
                singleTask.freight();
            }
            //重温剧情
            if (AtFairyConfig.getOption("jqnd").equals("1")) {
                singleTask.plot();
            }

            if (AtFairyConfig.getOption("jqnd").equals("2")) {
                singleTask.plot1();
            }

            if (AtFairyConfig.getOption("4689").equals("1")) {
                gameUtil.clearbag();
            }
            if (!(AtFairyConfig.getOption("phb").equals(""))) {
                singleTask.rankingList();
            }
            if (AtFairyConfig.getOption("4689").equals("1")) {
                gameUtil.clearbag();
            }

            otherGame.collection();
            otherGame.collection1();
            otherGame.collection2();

            LtLog.e(mFairy.getLineInfo("list===" + TaskMain.list.toString()));
            if (TaskMain.list.size() != 0) {
                singleTask.switchedRoles();
                TaskMain.list.remove(0);
            } else {
                break;
            }
        }
    }

    //固定队跟队0点从开
    public void reopen1() throws Exception {
        gameUtil.cancelFollowing();
        if (AtFairyConfig.getOption("4775").equals("1")) {
            gameUtil.clearbag();
        }
        //签到
        if (AtFairyConfig.getOption("qd").equals("1")) {
            otherGame.welfare();
        }
        //师门
        if (AtFairyConfig.getOption("sm").equals("1")) {
            singleTask.master();
        }
        //师门课业
        if (AtFairyConfig.getOption("5035").equals("1")) {
            singleTask.master1();
        }
        //宝图
        if (AtFairyConfig.getOption("bt").equals("1")) {
            singleTask.map();
        }
        if ((AtFairyConfig.getOption("cbtpt").equals("1") || AtFairyConfig.getOption("cbtzj").equals("1") || AtFairyConfig.getOption("cbtgj").equals("1") || AtFairyConfig.getOption("cbthj").equals("1"))) {
            singleTask.digMap();
        }
        //联赛战龙
        if (AtFairyConfig.getOption("lszl").equals("1")) {
            singleTask.lsWar();
        }
        //战龙
        if (AtFairyConfig.getOption("zlt").equals("1")) {
            singleTask.war();
        }
        //联赛备战
        if (AtFairyConfig.getOption("lscg").equals("1")) {
            singleTask.lsroutine();
        }
        //联赛门派挑战
        if (AtFairyConfig.getOption("lsmptz").equals("1")) {
            singleTask.lsFactions();
        }
        //门派挑战
        if ((AtFairyConfig.getOption("mptz").equals("1") || AtFairyConfig.getOption("mptz1").equals("1"))) {
            singleTask.factions();
        }

        //货运
        if (AtFairyConfig.getOption("hyrw").equals("1")) {
            singleTask.freight();
        }
        if (AtFairyConfig.getOption("4775").equals("1")) {
            gameUtil.clearbag();
        }
        if (!(AtFairyConfig.getOption("phb").equals(""))) {
            singleTask.rankingList();
        }
        if (AtFairyConfig.getOption("4775").equals("1")) {
            gameUtil.clearbag();
        }


        otherGame.collection();
        otherGame.collection1();
        otherGame.collection2();
    }


}
