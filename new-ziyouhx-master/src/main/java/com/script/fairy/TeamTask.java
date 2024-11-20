package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class TeamTask  extends TaskContent {
    AtFairyImpl mFairy;
    AtFairyImpl mFairy1;
    FindResult result;
    FindResult result1;
    GameUtil gameUtil;
    List<String> list = new ArrayList<>();
    OtherGame otherGame;
    public TeamTask(AtFairyImpl ATFairy) throws Exception {
        mFairy = ATFairy;
        gameUtil = new GameUtil(mFairy);
        mFairy1 = ATFairy;
        otherGame=new OtherGame(mFairy);
    }

    public void inOperation() throws Exception {
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
        result = mFairy.findPic(new String[]{"In picture.png", "In picture1.png"});
        LtLog.e(mFairy.getLineInfo("过图："+result.sim));
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
            setTaskEnd();return;
        }
    }

    //带队 经验洗刷刷
    public void jyxss() throws Exception {
        new TeamTask(mFairy) {

            public void content_0() throws Exception {
                gameUtil.close(0);

                result = mFairy.findPic(886,79,1144,221, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("洗刷刷副本中"));
                    err=0;
                    mFairy.initMatTime();
                    juliCount=0;
                    setTaskName(6);
                    return;
                }

                setTaskName(1);
                return;
            }
            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                if (gameUtil.duizhang()==1){
                    setTaskName(2);return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    int mannum = 0;
                    result = mFairy.findPic(461,485,645,520,"ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(648,484,828,518, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(832,486,1010,521, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }
                    result = mFairy.findPic(1012,485,1190,520, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    if (mannum >Integer.parseInt(AtFairyConfig.getOption("kaidui"))) {
                        gameUtil.callToFollow();
                        LtLog.e(mFairy.getLineInfo("人满了出发"));
                        setTaskName(5);
                        return;
                    }else {
                        if (err==7){
                            setTaskName(3);return;
                        }
                    }
                }
            }
            public void content_3() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("mbjyxss.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("目标正确"));
                        result = mFairy.findPic("dzqxpp.png");
                        mFairy.onTap(0.8f, result, "先取消一下自动匹配", Sleep);
                        result = mFairy.findPic("dzzdpp.png");
                        mFairy.onTap(0.8f, result, "开启自动匹配", Sleep);
                        setTaskName(4);return;
                    }else {
                        result = mFairy.findPic(79,107,270,599,"dwljyxss.png");
                        mFairy.onTap(0.8f, result, "找到目标经验洗刷刷", Sleep);
                        mFairy.onTap(0.8f, result, 162,615,191,628,"设为目标", Sleep);
                    }
                }
            }
            public void content_4() throws Exception {
                if (overtime(8, 0)) { return; }
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    err=0;
                    int mannum = 0;
                    result = mFairy.findPic(461,485,645,520,"ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(648,484,828,518, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(832,486,1010,521, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }
                    result = mFairy.findPic(1012,485,1190,520, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    if (mannum >Integer.parseInt(AtFairyConfig.getOption("kaidui"))) {
                        gameUtil.callToFollow();
                        LtLog.e(mFairy.getLineInfo("人满了出发"));
                        setTaskName(5);
                        return;
                    }
                    gameUtil.shenqing();
                    if (timekeep(1, 120000, "2分钟招募一下")) {
                        LtLog.e(mFairy.getLineInfo("--------2分钟招募一下"));
                        gameUtil.yjhh();
                    }
                    if (timekeep(0,600000,"超过10分钟没组到人")){
                        LtLog.e(mFairy.getLineInfo("--------超过10分钟没组到人"));
                        setTaskName(0);
                        return;
                    }
                }
            }

            public void content_5() throws Exception {
                int ret = gameUtil.mission("jyxss.png", 1);
                if (ret == 1) {
                    setTaskName(6);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }
            int  juliCount=0 ,sum=0;
            public void content_6() throws Exception {
                if (overtime(15, 0))return;
                gameUtil.fuhuo();

                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightjyxss.png","rightjyxss1.png","rightjyxss2.png"});
                if (result.sim>0.8f){
                    Thread.sleep(4000);
                    mFairy.condit();

                    result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightjyxss.png","rightjyxss1.png","rightjyxss2.png"});
                    mFairy.onTap(0.8f, result, "右侧经验洗刷刷", 1500);

                    result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightjyxss.png","rightjyxss1.png","rightjyxss2.png"});
                    mFairy.onTap(0.8f, result, "右侧经验洗刷刷", 1500);

                    sum=0;
                }

                if (result.sim<0.8f){
                    long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                    if (dazeTime > 5) {
                        result = mFairy.findPic(1128,0,1273,30,"lc.png");
                        if (result.sim>0.8f) {
                            sum++;
                        }
                        mFairy.initMatTime();

                        result = mFairy.findPic(40, 124, 282, 411, "leftjyxss.png");
                        mFairy.onTap(0.7f, result, "左侧经验洗刷刷", Sleep);
                        if (result.sim>0.7f){
                            err=0;
                        }

                        if (sum > 5){
                            gameUtil.huanxian();
                        }

                    }
                }

                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);


                result = mFairy.findPic(517,16,765,120,"rw.png");
                mFairy.onTap(0.8f, result, 1174,86,1183,97,"关闭任务", Sleep);

                result = mFairy.findPic(449,436,814,578,"julitaiyuan.png");
                if (result.sim>0.8f){
                    LtLog.e(mFairy.getLineInfo("距离太远"));
                    juliCount++;
                    if (juliCount>3){
                        gameUtil.cancelFollowing();
                        gameUtil.callToFollow();
                        Thread.sleep(10000);
                    }
                    if (juliCount>5) {
                        gameUtil.callToFollow();
                    }
                    if (juliCount>10) {
                        gameUtil.kicking();
                        juliCount=0;
                    }
                }

                result = mFairy.findPic(449,436,814,578,"rsbuzu.png");
                if (result.sim>0.8f){
                    LtLog.e(mFairy.getLineInfo("--------人数不足"));
                    setTaskName(0);return;
                }

                result = mFairy.findPic(886,79,1144,221, "copy.png");
                if (result.sim > 0.8f) {

                    LtLog.e(mFairy.getLineInfo("洗刷刷副本中"));
                    err=0;
                    mFairy.initMatTime();
                    juliCount=0;
                }
                result = mFairy.findPic("jyxssyilun.png");
               // mFairy.onTap(0.8f, result, "经验洗刷刷一轮结束", 8000);
                mFairy.onTap(0.8f, result, 739,669,766,679,"经验洗刷刷一轮结束", 8000);
                if (result.sim > 0.8f) {
                    setTaskName(5);return;
                }

                /*result = mFairy.findPic("Abandon mission.png");
                mFairy.onTap(0.8f, result, "放弃任务", Sleep);

                result = mFairy.findPic("Abandon sure.png");
                mFairy.onTap(0.8f, result, 743,441,775,457,"放弃任务确定", Sleep);
                mFairy.onTap(0.8f, result, 1166,90,1187,101,"放弃任务结束", Sleep);
                if (result.sim>0.8f){
                    setTaskName(5);return;
                }*/
                gameUtil.shenqing();
            }
        }.taskContent(mFairy, "经验洗刷刷带队中");
    }

    //固定队 带队 经验洗刷刷
    public void jyxss1() throws Exception {
        new TeamTask(mFairy) {
            int cs_count=0;
            @Override
            public void create() throws Exception {
                cs_count=Integer.parseInt(AtFairyConfig.getOption("gddcs"));//固定队次数
            }

            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);

                result = mFairy.findPic(886,79,1144,221, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("洗刷刷副本中"));
                    err=0;
                    mFairy.initMatTime();
                    juliCount=0;
                    setTaskName(6);
                    return;
                }

                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                if (gameUtil.duizhang()==1){
                    setTaskName(2);return;
                }
            }

            public void content_2() throws Exception {

                if (overtime(8, 0)) return;
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    int mannum = 0;
                    result = mFairy.findPic(461,485,645,520,"ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(648,484,828,518, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(832,486,1010,521, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }
                    result = mFairy.findPic(1012,485,1190,520, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    if (mannum >Integer.parseInt(AtFairyConfig.getOption("kaidui"))) {
                        gameUtil.callToFollow();
                        LtLog.e(mFairy.getLineInfo("人满了出发"));
                        setTaskName(5);
                        return;
                    }else {
                        if (err==7){
                            setTaskName(3);return;
                        }
                    }
                }
            }

            public void content_3() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("mbjyxss.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("目标正确"));
                        result = mFairy.findPic("dzqxpp.png");
                        mFairy.onTap(0.8f, result, "先取消一下自动匹配", Sleep);
                        result = mFairy.findPic("dzzdpp.png");
                        mFairy.onTap(0.8f, result, "开启自动匹配", Sleep);
                        setTaskName(4);return;
                    }else {
                        result = mFairy.findPic(79,107,270,599,"dwljyxss.png");
                        mFairy.onTap(0.8f, result, "找到目标经验洗刷刷", Sleep);
                        mFairy.onTap(0.8f, result, 162,615,191,628,"设为目标", Sleep);
                    }
                }
            }

            public void content_4() throws Exception {
                if (overtime(8, 0)) { return; }
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    err=0;
                    int mannum = 0;
                    result = mFairy.findPic(461,485,645,520,"ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(648,484,828,518, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(832,486,1010,521, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }
                    result = mFairy.findPic(1012,485,1190,520, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    if (mannum >Integer.parseInt(AtFairyConfig.getOption("kaidui"))) {
                        gameUtil.callToFollow();
                        LtLog.e(mFairy.getLineInfo("人满了出发"));
                        setTaskName(5);
                        return;
                    }else{
                        if (AtFairyConfig.getOption("zjjb").equals("1")) {
                            gameUtil.zhjb();
                        }
                    }
                    gameUtil.shenqing();
                    if (timekeep(1, 120000, "2分钟招募一下")) {
                        LtLog.e(mFairy.getLineInfo("--------2分钟招募一下"));
                        gameUtil.yjhh();
                    }
                    if (timekeep(0,600000,"超过10分钟没组到人")){
                        LtLog.e(mFairy.getLineInfo("--------超过10分钟没组到人"));
                        setTaskName(0);
                        return;
                    }
                }
            }

            public void content_5() throws Exception {
                int ret = gameUtil.mission("jyxss.png", 1);
                if (ret == 1) {
                    setTaskName(6);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            int  juliCount=0;
            int  sum=0;
            public void content_6() throws Exception {
                if (overtime(8, 5)) return;
                LtLog.e("sum="+sum);
                gameUtil.fuhuo();
                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightjyxss.png","rightjyxss1.png","rightjyxss2.png"});
                if (result.sim>0.8f){
                    Thread.sleep(4000);
                    mFairy.condit();
                    result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightjyxss.png","rightjyxss1.png","rightjyxss2.png"});
                    mFairy.onTap(0.8f, result, "右侧经验洗刷刷", 1500);

                    result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightjyxss.png","rightjyxss1.png","rightjyxss2.png"});
                    mFairy.onTap(0.8f, result, "右侧经验洗刷刷", 500);

                    sum=0;
                }

                if (result.sim<0.8f){
                    long dazeTime = mFairy.mMatTime(1181, 38, 37, 15, 0.9f);
                    if (dazeTime > 5) {
                        result = mFairy.findPic(1128,0,1273,30,"lc.png");
                        if (result.sim>0.8f) {
                            sum++;
                        }

                        mFairy.initMatTime();
                        result = mFairy.findPic("task1.png");
                        mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);

                        result = mFairy.findPic(40, 124, 282, 411, "leftjyxss.png");
                        mFairy.onTap(0.7f, result, "左侧经验洗刷刷", Sleep);
                        if (result.sim>0.7f){
                            err=0;
                        }

                        if (sum > 5){
                            gameUtil.huanxian();
                        }
                    }
                }
                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.9f, result, "任务栏暗点切换到任务栏", Sleep);

                result = mFairy.findPic(449,436,814,578,"julitaiyuan.png");
                if (result.sim>0.8f){
                    LtLog.e(mFairy.getLineInfo("距离太远"));
                    juliCount++;
                    if (juliCount>3){
                        gameUtil.cancelFollowing();
                        gameUtil.callToFollow();
                        Thread.sleep(10000);
                    }
                    if (juliCount>5) {
                        gameUtil.kicking();
                        juliCount=0;
                    }
                }

                result = mFairy.findPic(449,436,814,578,"rsbuzu.png");
                if (result.sim>0.8f){
                    if (AtFairyConfig.getOption("zjjb").equals("1")) {
                        gameUtil.zhjb();
                    }
                    LtLog.e(mFairy.getLineInfo("--------人数不足"));
                    setTaskName(0);return;
                }

                result = mFairy.findPic(886,79,1144,221, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("洗刷刷副本中"));
                    err=0;
                    mFairy.initMatTime();
                    juliCount=0;
                }

                result = mFairy.findPic("jyxssyilun.png");
                // mFairy.onTap(0.8f, result, "经验洗刷刷一轮结束", 8000);
                mFairy.onTap(0.8f, result, 739,669,766,679,"经验洗刷刷一轮结束", 8000);
                if (result.sim >= 0.8f) {
                     cs_count--;
                    LtLog.e(mFairy.getLineInfo("固定队剩余几轮"+cs_count));
                     if (cs_count<=0){
                         setTaskEnd();return;
                     }
                    setTaskName(5);return;
                }

                result = mFairy.findPic("Abandon mission.png");
                mFairy.onTap(0.8f, result, "放弃任务", Sleep);

                result = mFairy.findPic("Abandon sure.png");
                mFairy.onTap(0.8f, result, 743,441,775,457,"放弃任务确定", Sleep);
                mFairy.onTap(0.8f, result, 1166,90,1187,101,"放弃任务结束", Sleep);
                if (result.sim>0.8f){
                    setTaskName(5);return;
                }

                gameUtil.shenqing();
            }
        }.taskContent(mFairy, "经验洗刷刷带队中");
    }

    //跟队经验洗刷刷
    public void gdjyxss() throws Exception {
        new TeamTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("jyxss.png", 1);
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
                if (gameUtil.duiyuan() == 1) {
                    setTaskName(3);
                    return;
                }
            }
            public void content_3() throws Exception {
                if (overtime(7, 0)) {return;}

                result = mFairy.findPic("dyqxpp.png");
                mFairy.onTap(0.8f, result, "先取消匹配从新定义活动", Sleep);

                result = mFairy.findPic(79, 107, 270, 599, "dwljyxss.png");
                mFairy.onTap(0.8f, result, "跟队经验洗刷刷", Sleep);
                mFairy.onTap(0.8f, result, 1047, 631, 1085, 643, "开启自动匹配", Sleep);
                if (result.sim < 0.8f) {
                    mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5}, 0, 171, 541, 173, 160, 200, 1500, 2);
                } else {
                    setTaskName(4);
                    return;
                }
            }

            public void content_4() throws Exception {
                if (overtime(15, 0)) return;
                if (timekeep(0, 600000, "超过10分钟没组到人")) {
                    LtLog.e(mFairy.getLineInfo("--------超过10分钟没组到人"));
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("dyqxpp.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "匹配中"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("shenqing.png");
                    mFairy.onTap(0.8f, result, 957,103,1151,588,"申请进队", Sleep);
                    err=0;
                }

                result = mFairy.findPic(855, 248, 1116, 410, "youceyaoqing.png");
                mFairy.onTap(0.8f, result, 1030, 370, 1058, 384, "右侧同意邀请", Sleep);

                result = mFairy.findPic("yryaoqing.png");
                mFairy.onTap(0.8f, result, "有人邀请点开", Sleep);

                result = mFairy.findPic(879, 101, 1092, 581, "jiaru.png");
                mFairy.onTap(0.8f, result, "邀请加入", 100);
                mFairy.onTap(0.8f, result, 438, 613, 477, 625, "邀请列表关闭", Sleep);

                result = mFairy.findPic("beitile.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("qxgs.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进入队伍成功跟随队伍"));
                    gameUtil.close(0);
                    setTaskName(5);
                    return;
                }
                result = mFairy.findPic("gensuiduiwu.png");
                mFairy.onTap(0.8f, result, "进入队伍成功跟随队伍", Sleep);
                if (result.sim > 0.8f) {
                    gameUtil.close(0);
                    setTaskName(5);
                    return;
                }
            }
            public void content_5() throws Exception {
                result = mFairy.findPic(0, 0, 30, 27,"hg.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("成了队长了从来"));
                    gameUtil.retire();
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("jyxssyilun.png");
                mFairy.onTap(0.8f, result, "经验洗刷刷一轮结束", 8000);
                if (result.sim > 0.8f) {
                    int ret = gameUtil.mission("jyxss.png", 1);
                    if (ret == 1) {
                        setTaskName(2);
                        return;
                    } else {
                        gameUtil.retire();
                        setTaskEnd();
                        return;
                    }
                }
                result = mFairy.findPic("duiwulan.png");
                mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("chuanjianduiwu.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(886,79,1144,221, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("洗刷刷副本中"));
                    err=0;
                    timekeepInit("超过5分钟没有在副本");
                    gameUtil.zidong();
                }

                if (timekeep(0, 300000, "超过5分钟没有在副本")) {
                    LtLog.e(mFairy.getLineInfo("--------超过5分钟没有在副本"));
                    gameUtil.retire();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("beitile.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    gameUtil.lkfb();
                    setTaskName(0);
                    return;
                }

                gameUtil.fuhuo();

            }
        }.taskContent(mFairy, "经验洗刷刷跟队中");
    }

    //带队副本中
    public void dlfb1(final String  task,final String mbstr,final String mbstr1,final int  type) throws Exception {
        new TeamTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                jrcount=0;
                setTaskName(1);
                return;
            }
            public void content_1() throws Exception {
                int ret = gameUtil.mission(task, 1);
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
                if (gameUtil.duizhang()==1){
                    setTaskName(3);return;
                }
            }
            public void content_3() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(mbstr);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("目标正确"));
                        result = mFairy.findPic("dzqxpp.png");
                        mFairy.onTap(0.8f, result, "先取消一下自动匹配", Sleep);
                        result = mFairy.findPic("dzzdpp.png");
                        mFairy.onTap(0.8f, result, "开启自动匹配", Sleep);
                        setTaskName(4);
                        return;
                    } else {
                        if (type==0){
                            result = mFairy.findPic(79, 107, 270, 599, "ptfb.png");
                            mFairy.onTap(0.95f, result, "找到普通副本", Sleep);
                        }
                        if (type==1){
                            result = mFairy.findPic(79, 107, 270, 599, "tzfb.png");
                            mFairy.onTap(0.95f, result, "找到挑战副本", Sleep);
                        }

                        result = mFairy.findPic(79, 107, 270, 599, mbstr1);
                        mFairy.onTap(0.9f, result, "找到副本", Sleep);
                        mFairy.onTap(0.9f, result, 162, 615, 191, 628, "设为目标", Sleep);
                        if (result.sim < 0.9f) {
                            mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5}, 0, 171, 541, 173, 160, 200, 1500, 2);
                        }
                    }
                }
            }
            public void content_4() throws Exception {
                if (overtime(8, 0)) { return; }
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    err=0;
                    int mannum = 0;
                    result = mFairy.findPic(461,485,645,520,"ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(648,484,828,518, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(832,486,1010,521, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }
                    result = mFairy.findPic(1012,485,1190,520, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    if (mannum >Integer.parseInt(AtFairyConfig.getOption("kaidui"))) {
                        gameUtil.callToFollow();
                        LtLog.e(mFairy.getLineInfo("人满了出发"));
                        setTaskName(5);
                        return;
                    }
                    gameUtil.shenqing();
                    if (timekeep(1, 120000, "2分钟招募一下")) {
                        LtLog.e(mFairy.getLineInfo("--------2分钟招募一下"));
                        gameUtil.yjhh();
                    }
                    if (timekeep(0,600000,"超过10分钟没组到人")){
                        LtLog.e(mFairy.getLineInfo("--------超过10分钟没组到人"));
                        setTaskName(0);
                        return;
                    }
                }
            }
            public void content_5() throws Exception {
                int ret = gameUtil.mission(task, 1);
                if (ret == 1) {
                    setTaskName(6);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }
            int  jrcount=0;
            public void content_6() throws Exception {
                if (overtime(20, 0)) return;
                gameUtil.fuhuo();
                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightsnfb.png"});
                if (result.sim>0.8f){
                    Thread.sleep(7000);
                    mFairy.condit();
                    result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightsnfb.png"});
                    mFairy.onTap(0.8f, result, "右侧三界副本", 500);
                }
                result = mFairy.findPic("sjfbinface.png");
                if (result.sim>0.8f){
                    err=0;
                    if (type==0){
                        mFairy.onTap(0.8f, result, 160,178,184,198,"普通副本", 500);
                    }
                    if (type==1){
                        mFairy.onTap(0.8f, result, 165,349,187,368,"挑战副本", 500);
                    }
                    result = mFairy.findPic(349,537,1097,624,"fbjinru.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("多次进入失败"));
                        jrcount++;
                        if (jrcount>3){
                            jrcount=0;
                            setTaskName(0);return;
                        }
                        mFairy.onTap(0.9f, result, "副本进入", Sleep);
                    }
                }

                result = mFairy.findPic(886,79,1144,221, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    jrcount=0;
                    err=0;
                    gameUtil.zidong();
                }
                result = mFairy.findPic("fbcj.png");
                mFairy.onTap(0.8f, result,  637,343,664,358,"副本抽奖", 5000);
                gameUtil.shenqing();
            }
        }.taskContent(mFairy, "带队副本中");
    }

    //带队副本中
    public void dlfb(final String  task,final String mbstr,final String mbstr1,final int  type) throws Exception {
        new TeamTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                jrcount=0;
                gameUtil.close(1);
                setTaskName(1);
                return;
            }
            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                if (gameUtil.duizhang()==1){
                    setTaskName(3);return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(8, 0)) { return; }
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    err=0;
                    int mannum = 0;
                    result = mFairy.findPic(461,485,645,520,"ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(648,484,828,518, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(832,486,1010,521, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }
                    result = mFairy.findPic(1012,485,1190,520, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    if (mannum >Integer.parseInt(AtFairyConfig.getOption("kaidui"))) {
                        gameUtil.callToFollow();
                        LtLog.e(mFairy.getLineInfo("人满了出发"));
                        setTaskName(5);
                        return;
                    }else {
                        setTaskName(3);return;
                    }
                }
            }
            public void content_3() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(mbstr);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("目标正确"));
                        result = mFairy.findPic("dzqxpp.png");
                        mFairy.onTap(0.8f, result, "先取消一下自动匹配", Sleep);
                        result = mFairy.findPic("dzzdpp.png");
                        mFairy.onTap(0.8f, result, "开启自动匹配", Sleep);
                        setTaskName(4);
                        return;
                    } else {
                        if (type==0){
                            result = mFairy.findPic(79, 107, 270, 599, "ptfb.png");
                            mFairy.onTap(0.95f, result, "找到普通副本", Sleep);
                        }
                        if (type==1){
                            result = mFairy.findPic(79, 107, 270, 599, "tzfb.png");
                            mFairy.onTap(0.95f, result, "找到挑战副本", Sleep);
                        }

                        result = mFairy.findPic(79, 107, 270, 599, mbstr1);
                        mFairy.onTap(0.9f, result, "找到副本", Sleep);
                        mFairy.onTap(0.9f, result, 162, 615, 191, 628, "设为目标", Sleep);
                        if (result.sim < 0.9f) {
                            mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5}, 0, 171, 541, 173, 160, 200, 1500, 2);
                        }
                    }
                }
            }
            public void content_4() throws Exception {
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim < 0.8f) {
                    setTaskName(0);return;
                }
                result = mFairy.findPic("yijianhanhua.png");
                if (result.sim > 0.8f) {
                    int mannum = 0;
                    result = mFairy.findPic(461,485,645,520,"ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(648,484,828,518, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    result = mFairy.findPic(832,486,1010,521, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }
                    result = mFairy.findPic(1012,485,1190,520, "ppnum.png");
                    if (result.sim > 0.8f) {
                        mannum++;
                        LtLog.e(mFairy.getLineInfo(mannum + "个人了"));
                    }

                    if (mannum >Integer.parseInt(AtFairyConfig.getOption("kaidui"))) {
                        gameUtil.callToFollow();
                        LtLog.e(mFairy.getLineInfo("人满了出发"));
                        setTaskName(5);
                        return;
                    }
                    if (timekeep(1, 120000, "2分钟招募一下")) {
                        LtLog.e(mFairy.getLineInfo("--------2分钟招募一下"));
                        gameUtil.yjhh();
                    }
                    if (timekeep(0,300000,"超过5分钟没组到人")){
                        LtLog.e(mFairy.getLineInfo("--------超过5分钟没组到人"));
                        setTaskName(0);
                        return;
                    }
                }
            }
            public void content_5() throws Exception {
                int ret = gameUtil.mission(task, 1);
                if (ret == 1) {
                    setTaskName(6);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }
            int  jrcount=0;
            public void content_6() throws Exception {
                if (overtime(20, 5)) return;
                gameUtil.fuhuo();
                result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightsnfb.png"});
                if (result.sim>0.8f){
                    Thread.sleep(7000);
                    mFairy.condit();
                    result = mFairy.findPic(1008, 13, 1266, 601, new String[]{"rightsnfb.png"});
                    mFairy.onTap(0.8f, result, "右侧三界副本", 500);
                }
                result = mFairy.findPic("sjfbinface.png");
                if (result.sim>0.8f){
                    err=0;
                    if (type==0){
                        mFairy.onTap(0.8f, result, 160,178,184,198,"普通副本", 500);
                    }
                    if (type==1){
                        mFairy.onTap(0.8f, result, 165,349,187,368,"挑战副本", 500);
                    }
                    result = mFairy.findPic(349,537,1097,624,"fbjinru.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("多次进入失败"));
                        jrcount++;
                        if (jrcount>3){
                            jrcount=0;
                            setTaskName(0);return;
                        }
                        mFairy.onTap(0.9f, result, "副本进入", Sleep);
                    }
                }

                result = mFairy.findPic(886,79,1144,221, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    jrcount=0;
                    err=0;
                    gameUtil.zidong();
                }
                result = mFairy.findPic("fbcj.png");
                mFairy.onTap(0.8f, result,  637,343,664,358,"副本抽奖", 5000);
                gameUtil.shenqing();
            }
        }.taskContent(mFairy, "带队副本中");
    }

    //跟队副本中
    public void gdfb(final String  task,final String mbstr,final String mbstr1,final int  type) throws Exception {
        new TeamTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission(task, 1);
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
                if (gameUtil.duiyuan() == 1) {
                    setTaskName(3);
                    return;
                }
            }
            public void content_3() throws Exception {
                if (overtime(7, 0)) {return;}

                result = mFairy.findPic("dyqxpp.png");
                mFairy.onTap(0.8f, result, "先取消匹配从新定义活动", Sleep);

                if (type==0){
                    result = mFairy.findPic(79, 107, 270, 599, "ptfb.png");
                    mFairy.onTap(0.95f, result, "找到普通副本", Sleep);
                }
                if (type==1){
                    result = mFairy.findPic(79, 107, 270, 599, "tzfb.png");
                    mFairy.onTap(0.95f, result, "找到挑战副本", Sleep);
                }

                result = mFairy.findPic(79, 107, 270, 599, mbstr1);
                mFairy.onTap(0.9f, result, "找到副本", Sleep);
                mFairy.onTap(0.8f, result, 1047, 631, 1085, 643, "开启自动匹配", Sleep);
                if (result.sim < 0.9f) {
                    mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4, 5}, 0, 171, 541, 173, 160, 200, 1500, 2);
                }else {
                    setTaskName(4);return;
                }
            }

            public void content_4() throws Exception {
                if (overtime(15, 0)) return;
                if (timekeep(0, 600000, "超过10分钟没组到人")) {
                    LtLog.e(mFairy.getLineInfo("--------超过10分钟没组到人"));
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("dyqxpp.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "匹配中"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("shenqing.png");
                    mFairy.onTap(0.8f, result, 957,103,1151,588,"申请进队", Sleep);
                    err=0;
                }

                result = mFairy.findPic(855, 248, 1116, 410, "youceyaoqing.png");
                mFairy.onTap(0.8f, result, 1030, 370, 1058, 384, "右侧同意邀请", Sleep);

                result = mFairy.findPic("yryaoqing.png");
                mFairy.onTap(0.8f, result, "有人邀请点开", Sleep);

                result = mFairy.findPic(879, 101, 1092, 581, "jiaru.png");
                mFairy.onTap(0.8f, result, "邀请加入", 100);
                mFairy.onTap(0.8f, result, 438, 613, 477, 625, "邀请列表关闭", Sleep);

                result = mFairy.findPic("beitile.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("qxgs.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进入队伍成功跟随队伍"));
                    gameUtil.close(0);
                    setTaskName(5);
                    return;
                }
                result = mFairy.findPic("gensuiduiwu.png");
                mFairy.onTap(0.8f, result, "进入队伍成功跟随队伍", Sleep);
                if (result.sim > 0.8f) {
                    gameUtil.close(0);
                    setTaskName(5);
                    return;
                }
            }
            public void content_5() throws Exception {
                result = mFairy.findPic(0, 0, 30, 27,"hg.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("成了队长了从来"));
                    gameUtil.retire();
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("fbcj.png");
                mFairy.onTap(0.8f, result,  637,343,664,358,"副本抽奖", 5000);

                result = mFairy.findPic("duiwulan.png");
                mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("chuanjianduiwu.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(886,79,1144,221, "copy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    err=0;
                    timekeepInit("超过5分钟没有在副本");
                    gameUtil.zidong();
                }

                if (timekeep(0, 300000, "超过5分钟没有在副本")) {
                    LtLog.e(mFairy.getLineInfo("--------超过5分钟没有在副本"));
                    gameUtil.retire();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("beitile.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    gameUtil.lkfb();
                    setTaskName(0);
                    return;
                }
                gameUtil.fuhuo();
            }
        }.taskContent(mFairy, "跟队副本中");
    }

    //狭义副本
    public void xyfb() throws Exception {
        new TeamTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.retire();
                gameUtil.goCity("龙城");
                setTaskName(1);
                timekeepInit("超过5分钟没有在副本");
                return;
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
                mFairy.onTap(0.8f, result,  38,224,65,237,"对话栏切换到组队频道", Sleep);
                if (result.sim>0.8f){
                    err=0;
                    result = mFairy.findPic(184,145,541,619, new String[]{"putong.png","putong1.png","tiaozhan.png"});
                    if (result.sim>0.8f){
                        result = mFairy.findPic(result.x -86, result.y - 6, result.x + 209, result.y + 46, "sqrd.png");
                        mFairy.onTap(0.8f, result,   "申请入队", Sleep);
                    }

                    result = mFairy.findPic(200,548,506,617, "sqrd.png");
                    mFairy.onTap(0.8f, result,   "申请入队", Sleep);

                    result = mFairy.findPic(3,135,107,366, "duiwu1.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("队伍中 "));
                        gameUtil.close(0);
                        setTaskName(2);
                        return;
                    }

                }else {
                    result = mFairy.findPic(676, 7, 1152, 170, "daily.png");
                    mFairy.onTap(0.8f, result,  393,673, 394,674,"活动", Sleep);
                }

                result = mFairy.findPic(855, 248, 1116, 410, "youceyaoqing.png");
                mFairy.onTap(0.8f, result, 1030, 370, 1058, 384, "右侧同意邀请", Sleep);

                result = mFairy.findPic(530,37,730,110,"gwsll.png");
                mFairy.onTap(0.8f, result, 1183,96,1191,104,"关闭", Sleep);

                result = mFairy.findPic("yryaoqing.png");
                mFairy.onTap(0.8f, result, "有人邀请点开", Sleep);

                result = mFairy.findPic(879, 101, 1092, 581, "jiaru.png");
                mFairy.onTap(0.8f, result, "邀请加入", 100);
                mFairy.onTap(0.8f, result, 438, 613, 477, 625, "邀请列表关闭", Sleep);

                result = mFairy.findPic("beitile.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("qxgs.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进入队伍成功跟随队伍"));
                    gameUtil.close(0);
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("gensuiduiwu.png");
                mFairy.onTap(0.8f, result, "进入队伍成功跟随队伍", Sleep);
                if (result.sim > 0.8f) {
                    gameUtil.close(0);
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("agreefb.png");
                mFairy.onTap(0.8f, result, 753,529,798,546,"err同意进入副本", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic(664,403,953,574, "xyzman.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("侠义值满了"));
                    for (int i=0;i<10;i++){
                        result = mFairy.findPic(886,79,1144,221, "copy.png");
                        if (result.sim > 0.7f) {
                            LtLog.e(mFairy.getLineInfo("副本中"));
                            err=0;
                            timekeepInit("超过5分钟没有在副本");
                            gameUtil.zidong();
                            i=0;
                        }
                        gameUtil.fuhuo();
                    }
                    result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
                    mFairy.onTap(0.8f, result, "收起对话栏", Sleep);
                    setTaskEnd();return;
                }
            }
            public void content_2() throws Exception {

                result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
                mFairy.onTap(0.8f, result, "收起对话栏", Sleep);

                result = mFairy.findPic(664,403,953,574, "xyzman.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("侠义值满了"));
                    for (int i=0;i<10;i++){
                        mFairy.condit();
                        Thread.sleep(2000);
                        result = mFairy.findPic(886,79,1144,221, "copy.png");
                        if (result.sim > 0.7f) {
                            LtLog.e(mFairy.getLineInfo("副本中"));
                            err=0;
                            timekeepInit("超过5分钟没有在副本");
                            gameUtil.zidong();
                            i=0;
                        }
                        gameUtil.fuhuo();
                    }
                    result = mFairy.findPic(546, 129, 591, 232, "dhl.png");
                    mFairy.onTap(0.8f, result, "收起对话栏", Sleep);
                    setTaskEnd();return;
                }
                result = mFairy.findPic(0, 0, 30, 27,"hg.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("成了队长了从来"));
                    gameUtil.retire();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("duiwulan.png");
                mFairy.onTap(0.8f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("chuanjianduiwu.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(886,79,1144,221, "copy.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("副本中"));
                    err=0;
                    timekeepInit("超过5分钟没有在副本");
                    gameUtil.zidong();
                }

                if (timekeep(0, 300000, "超过5分钟没有在副本")) {
                    LtLog.e(mFairy.getLineInfo("--------超过5分钟没有在副本"));
                    gameUtil.retire();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("beitile.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("应该被踢了从来"));
                    gameUtil.lkfb();
                    setTaskName(0);
                    return;
                }
                gameUtil.fuhuo();
            }
        }.taskContent(mFairy, "狭义副本中");
    }
}
