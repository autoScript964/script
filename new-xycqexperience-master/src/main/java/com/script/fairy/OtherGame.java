package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.Random;

/**
 * Created by Administrator on 2019/11/5 0005.
 */

public class OtherGame  extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    FindResult result2;
    GameUtil gameUtil;
    public OtherGame(AtFairyImpl ATFairy) throws Exception {
        mFairy = ATFairy;
        gameUtil = new GameUtil(mFairy);
    }
    public void inOperation() throws Exception {
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
        result = mFairy.findPic(188, 533, 369, 592, "accept.png");
        mFairy.onTap(0.8f, result, "接受", Sleep);
        if (result.sim > 0.8f) {
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
    }

    public  void  nongchang()throws Exception{
        new OtherGame(mFairy){
            @Override
            public void content_0() throws Exception {
                setTaskName(1);return;
            }
            public void content_1() throws Exception {
                int ret=gameUtil.mission("Farm.png",1);
                if (ret==1){
                    setTaskName(2);return;
                }else {
                    setTaskEnd();return;
                }
            }
            int food=Integer.parseInt(AtFairyConfig.getOption("food"));
            public void content_2() throws Exception {
                if (overtime(30,99))return;
                Thread.sleep(1000);

                /*result =mFairy.findPic(265,249,1002,526,"wdcd.png");
                if (result.sim>0.8f){
                    mFairy.onTap(0.8f,result,"我的菜地",Sleep);
                }else{*/
                    result =mFairy.findPic(265,249,1002,526,"VacantLand.png");
                    mFairy.onTap(0.8f,result,"种菜",Sleep);


                result =mFairy.findPic(686,405,868,478,"scSure.png");
                mFairy.onTap(0.8f,result,"去地块确定",Sleep);
                if (result.sim>0.8f){
                    setTaskName(3);return;
                }
            }
            public void content_3() throws Exception {
                if (overtime(60,0)){
                    mFairy.ranSwipe(172,570,273,571,1500, (long) 1500,2);
                    return;
                }
                result =mFairy.findPic("zcFood.png");
                mFairy.onTap(0.8f,result,"种菜选菜",Sleep);
                mFairy.onTap(0.8f,result,139,114,171,129,"选个菜",Sleep);

                result =mFairy.findPic(new String[]{"zhongzhi.png","Sow.png"});
                mFairy.onTap(0.8f,result,"播种",Sleep);
                if (result.sim>0.8f){
                    result =mFairy.findPic(420,102,886,208,"zdsx.png");
                    if (result.sim>0.7f) {
                        LtLog.e(mFairy.getLineInfo("种菜达到上限"));
                        gameUtil.close(0);
                        setTaskEnd();return;
                    }
                }

                result =mFairy.findPic(776,458,998,579,"Harvest.png");
                mFairy.onTap(0.8f,result,"收获",Sleep);

                result =mFairy.findPic("SowSure1.png");
                mFairy.onTap(0.8f,result,"播种确定",6000);


                result =mFairy.findPic(535,86,744,192,"tlbz.png");
                if (result.sim>0.8f){
                    LtLog.e(mFairy.getLineInfo("体力不足"));
                    gameUtil.close(0);
                    setTaskEnd();return;
                }

                result =mFairy.findPic(106,11,256,65,"zcShop.png");
                if (result.sim>0.8f) {
                    LtLog.e(mFairy.getLineInfo("商店没菜了要买菜"));
                    if (food == 1) {
                        mFairy.tap(358, 186);
                    }
                    if (food == 2) {
                        mFairy.tap(693, 186);
                    }
                    if (food == 3) {
                        mFairy.tap(358, 296);
                    }
                    if (food == 4) {
                        mFairy.tap(325, 100);
                        Thread.sleep(2000);
                        mFairy.tap(358, 186);
                    }
                    if (food == 5) {
                        mFairy.tap(325, 100);
                        Thread.sleep(2000);
                        mFairy.tap(693, 186);
                    }
                    if (food == 6) {
                        mFairy.tap(325, 100);
                        Thread.sleep(2000);
                        mFairy.tap(358, 296);
                    }
                    if (food == 7) {
                        mFairy.tap(325, 100);
                        Thread.sleep(2000);
                        mFairy.tap(693, 298);
                    }
                    if (food == 8) {
                        mFairy.tap(325, 100);
                        Thread.sleep(2000);
                        mFairy.tap(358, 404);
                    }
                    if (food == 9) {
                        mFairy.tap(325, 100);
                        Thread.sleep(2000);
                        mFairy.tap(693, 404);
                    }
                    if (food == 10) {
                        mFairy.tap(465, 98);
                        Thread.sleep(2000);
                        mFairy.tap(358, 186);
                    }
                    if (food == 11) {
                        mFairy.tap(465, 98);
                        Thread.sleep(2000);
                        mFairy.tap(693, 186);
                    }
                    if (food == 12) {
                        mFairy.tap(465, 98);
                        Thread.sleep(2000);
                        mFairy.tap(358, 296);
                    }
                    if (food == 13) {
                        mFairy.tap(465, 98);
                        Thread.sleep(2000);
                        mFairy.tap(693, 298);
                    }
                    if (food == 14) {
                        mFairy.tap(465, 98);
                        Thread.sleep(2000);
                        mFairy.tap(358, 404);
                    }
                    if (food == 15) {
                        mFairy.tap(465, 98);
                        Thread.sleep(2000);
                        mFairy.tap(693, 404);
                    }
                    Thread.sleep(2000);
                    mFairy.onTap(1043,489,1053,503,"买三个",Sleep);
                    mFairy.onTap(1043,489,1053,503,"买三个",Sleep);
                    mFairy.onTap(1043,489,1053,503,"买三个",Sleep);
                    mFairy.onTap(974,623,1001,641,"立即购买",Sleep);
                    mFairy.onTap(757,430,788,451,"立即购买",Sleep);
                    mFairy.onTap(1147,52,1165,64,"立即购买",Sleep);
                }
                result =mFairy.findPic("eradicate.png");
                if (result.sim>0.8f) {
                    LtLog.e(mFairy.getLineInfo("已经种了菜了"));
                    gameUtil.close(0);
                    setTaskName(0);return;
                }
            }
        }.taskContent(mFairy,"血盟农场任务中");
    }//血盟农场

    public void dxz() throws Exception {
        new OtherGame(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                if (mFairy.dateHour()<10){
                    setTaskEnd();return;
                }
            }
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }
            int rsnum=0;
            public void content_1() throws Exception {
                result = mFairy.findPic("liaotiankuang.png");
                mFairy.onTap( 0.8f, result, "收起聊天框" , Sleep);

                result = mFairy.findPic(0,165,33,400,new String[]{"LeftCaptain.png","LeftCaptain3.png"});
                mFairy.onTap( 0.9f, result, "左侧队伍", Sleep);

                result = mFairy.findPic("CreateTeam.png");
                mFairy.onTap( 0.8f, result, "创建队伍", Sleep);

                result = mFairy.findPic("CreateTeam1.png");
                mFairy.onTap( 0.8f, result, 606,236,607,237,"目标", Sleep);
                mFairy.onTap( 0.8f, result, 517,671,547,676,"无目标", Sleep);
                mFairy.onTap( 0.8f, result, "创建队伍", Sleep);


                result = mFairy.findPic("jindui.png");
                mFairy.onTap( 0.8f, result, "有人进队", Sleep);

                result = mFairy.findPic(891, 205, 1015, 564,"agree.png");
                mFairy.onTap( 0.8f, result, "同意别人进队", Sleep);
                rsnum=0;
                result = mFairy.findPic(341, 257, 602, 336,"ytlPeople.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进来1个人"));
                    rsnum++;
                }

                result = mFairy.findPic(339, 350, 602, 432, "ytlPeople.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进来2个人"));
                    rsnum++;
                }
                result = mFairy.findPic(336, 447, 602, 527, "ytlPeople.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进来3个人"));
                    rsnum++;
                }
                result = mFairy.findPic(333, 541, 603, 625, "ytlPeople.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进来4个人"));
                    rsnum++;
                }

                if (rsnum > 1) {
                    mFairy.onTap( 707, 313,708, 314,"收起组队框", Sleep);
                    gameUtil.close(1);
                    setTaskName(3);return;
                }else {
                    if (timekeep(0,120000,"2分钟喊下话")){
                             setTaskName(2);return;
                    }
                }
                result = mFairy.findPic("RecruitM.png");
                if (result.sim > 0.8f) {
                    if (timekeep(1,120000,"2分钟招募一下")){
                        LtLog.e(mFairy.getLineInfo("--------2分钟招募一下"));
                        mFairy.onTap( 0.8f, result, "招募成员", Sleep);
                        mFairy.onTap( 0.8f, result, 653, 120,654, 121,"招募频道", Sleep);
                        mFairy.onTap( 0.8f, result, "招募成员", Sleep);
                        mFairy.onTap( 0.8f, result, 657, 165,658, 166,"血盟频道", Sleep);
                        mFairy.onTap( 0.8f, result, "招募成员", Sleep);
                        mFairy.onTap( 0.8f, result, 650, 213,651, 214,"附近频道", Sleep);
                    }
                }
                if (timekeep(0,600000,"超过10分钟没组到人")){
                    LtLog.e(mFairy.getLineInfo("--------超过10分钟没组到人"));
                    setTaskName(0);
                    return;
                }
                Thread.sleep(2000);
            }
            public void content_2() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(876, 5, 1120, 146, "daily.png");
                mFairy.onTap(0.8f, result, 813,606,814,607, "日常", Sleep);

                result = mFairy.findPic(5,9,103,621, "hhshijie.png");
                mFairy.onTap(0.8f, result, "世界", Sleep);

                result = mFairy.findPic(5,9,103,621, "hhshijie1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 189,660,209,670,"打开输入框", 3000);
                    mFairy.inputText("打雪仗来人+++++");
                    mFairy.condit();
                    result = mFairy.findPic(1071,68,1274,292,new String[]{"new_textsure.png","new_textsure1.png","new_textsure2.png","new_textsure3.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.7f, result, "确定文本", 2000);
                        mFairy.onTap(0.7f, result, 544, 657, 583, 671, "发送", 2000);
                        mFairy.onTap(0.7f, result, 622, 311, 632, 329, "收起聊天框", 2000);
                        setTaskName(0);
                        return;
                    }
                }
            }
            public void content_3() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(876, 5, 1120, 146, "daily.png");
                mFairy.onTap(0.8f, result, 1074,34,1085,49, "日常", Sleep);

                result = mFairy.findPic("dxztcfb.png");
                mFairy.onTap(0.8f, result, 507,430,542,444, "取消退出副本", Sleep);

                result = mFairy.findPic("fenjie.png");
                if (result.sim>0.8f){
                    result = mFairy.findPic(442, 1, 1277, 513, new String[]{"fork.png","fork1.png"});
                    mFairy.onTap(0.8f, result, "关叉", 2000);
                }

                result = mFairy.findPic(451,78,1116,240, new String[]{"hdcj.png","hdcj1.png"});
                mFairy.onTap(0.8f, result, "欢度春节", 2000);
                mFairy.onTap(0.8f, result, 60,171,89,194,"欢度春节", 2000);

                result = mFairy.findPic( 399,171,521,249,"dxzqw.png");
                mFairy.onTap(0.8f, result, "前往", Sleep);

                result = mFairy.findPic(605,19,1115,278, "daxuezhang.png");
                mFairy.onTap(0.8f, result, "打雪仗", Sleep);

                result = mFairy.findPic( "xzqd.png");
                mFairy.onTap(0.8f, result, "取消战袍", Sleep);

                result = mFairy.findPic( 302,172,1037,576,"xueren.png");
                mFairy.onTap(0.8f, result, "打雪 人", Sleep);

                result = mFairy.findPic(724,518,1090,641,"dxzpp.png");
                mFairy.onTap(0.8f, result, "组队匹配", Sleep);
                if (result.sim > 0.8f) {
                    err=0;
                }

                result = mFairy.findPic(429,39,824,218,"dxzrbgou.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("人不够"));
                    setTaskName(0);return;
                }

                result = mFairy.findPic(727,470,1088,641,"ppz.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                    err=0;
                }

                result =mFairy.findPic("jybattle.png");
                mFairy.onTap(0.8f,result,"加入战斗",Sleep);


                result =mFairy.findPic(1115,2,1280,32,"xqdzz.png");
                if (result.sim > 0.8f) {
                    err=0;
                    LtLog.e(mFairy.getLineInfo("冰雪大作战内"));
                    result =mFairy.findPic(469,4,811,77,"miao.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("冰雪大作读秒中"));
                    }else{
                        int x = new Random().nextInt(170) + 87;
                        int y = new Random().nextInt(164) + 493;
                        mFairy.ranSwipe(172, 570, x, y, 2000, (long) 1000, 2);

                        mFairy.onTap(0.8f, result, 1177, 608, 1197, 624, "普工一下", 100);
                        mFairy.onTap(0.8f, result, 1177, 608, 1197, 624, "普工一下", 100);
                        mFairy.onTap(0.8f, result, 1177, 608, 1197, 624, "普工一下", 100);
                        mFairy.onTap(0.8f, result, 1049,653,1058,664, "技能攻击", 100);


                        result = mFairy.findPic("Openbox.png");
                        mFairy.onTap(0.8f, result, "开箱", 5000);

                        result = mFairy.findPic( 302,172,1037,576,"xueren.png");
                        mFairy.onTap(0.8f, result, "打雪 人", Sleep);
                    }
                }
                result =mFairy.findPic(507,525,773,636,"xqlikai.png");
                mFairy.onTap(0.8f,result,"离开",Sleep);
            }

        }.taskContent(mFairy, "打雪仗中");
    }//打雪仗

    public void sgmc() throws Exception {
        new OtherGame(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();
                int w = mFairy.week();
                int s=(h*60+m)*60;
                if (s>61200 && s<82800){
                }else{
                    LtLog.e("活动时间已过");
                    setTaskEnd();return;
                }
            }
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }
            int rsnum=0;
            @Override
            public void content_1() throws Exception {
                if (overtime(30,0))return;

                result = mFairy.findPic("fenjie.png");
                if (result.sim>0.8f){
                    result = mFairy.findPic(442, 1, 1277, 513, new String[]{"fork.png","fork1.png"});
                    mFairy.onTap(0.8f, result, "关叉", 2000);
                }


                result = mFairy.findPic(368,105,928,195, "yjcj.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("已经参加"));
                    mFairy.onTap(0.8f, result, 1204,40,1212,56,"关闭", 2000);
                }

                result = mFairy.findPic(500,608,749,697, "ppz1.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                }

                result =mFairy.findPic(669,387,893,487,"jybattle.png");
                mFairy.onTap(0.8f,result,"加入战斗",Sleep);

                result = mFairy.findPic(1105,2,1280,40, "hqxlz.png");
                if (result.sim > 0.8f) {
                    err=0;
                    LtLog.e(mFairy.getLineInfo("浩气修炼中"));
                    result = mFairy.findPic(771,222,955,425,"Openbox.png");
                    if (result.sim > 0.8f || rsnum==1) {
                        rsnum=1;
                        mFairy.onTap(858,307,874,324, "开箱", 5000);
                        LtLog.e(mFairy.getLineInfo("浩气修炼收集中"));
                    }else{
                        int x = new Random().nextInt(170) + 87;
                        int y = new Random().nextInt(164) + 493;
                        mFairy.ranSwipe(172, 570, x, y, 2000, (long) 1000, 2);
                    }
                }else{
                    rsnum=0;
                    result = mFairy.findPic(467,587,816,708, "grpp1.png");
                    mFairy.onTap(0.8f, result, "个人匹配", 2000);

                    result = mFairy.findPic(1040,8,1119,81, "lb.png");
                    mFairy.onTap(0.8f, result,"列表", Sleep);

                    result = mFairy.findPic(598,150,1080,241, "sgmc.png");
                    mFairy.onTap(0.8f, result,"上古迷城", Sleep);
                }
                result =mFairy.findPic(507,525,773,636,"xqlikai.png");
                mFairy.onTap(0.8f,result,"离开",Sleep);
            }
        }.taskContent(mFairy, "浩气修炼中");
    }//浩气修炼中

    public void dxz1() throws Exception {
        new OtherGame(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                if (mFairy.dateHour()<10){
                    setTaskEnd();return;
                }
            }
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }
            int rsnum=0;
            public void content_1() throws Exception {
                Thread.sleep(1000);
                result = mFairy.findPic("dxzzhidaole.png");
                mFairy.onTap( 0.8f, result, "知道了" , Sleep);

                result =mFairy.findPic("jybattle.png");
                mFairy.onTap(0.8f,result,"加入战斗",Sleep);

                result = mFairy.findPic(727,470,1088,641,"ppz.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("匹配中"));
                    err=0;
                }

                result =mFairy.findPic("jybattle.png");
                mFairy.onTap(0.8f,result,"加入战斗",Sleep);


                result =mFairy.findPic(1115,2,1280,32,"xqdzz.png");
                if (result.sim > 0.8f) {
                    err=0;
                    LtLog.e(mFairy.getLineInfo("冰雪大作战内"));
                    result =mFairy.findPic(469,4,811,77,"miao.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("冰雪大作读秒中"));
                    }else{
                        int x = new Random().nextInt(170) + 87;
                        int y = new Random().nextInt(164) + 493;
                        mFairy.ranSwipe(172, 570, x, y, 2000, (long) 1000, 2);

                        mFairy.onTap(0.8f, result, 1177, 608, 1197, 624, "普工一下", 100);
                        mFairy.onTap(0.8f, result, 1177, 608, 1197, 624, "普工一下", 100);
                        mFairy.onTap(0.8f, result, 1177, 608, 1197, 624, "普工一下", 100);
                        mFairy.onTap(0.8f, result, 1049,653,1058,664, "技能攻击", 100);


                        result = mFairy.findPic("Openbox.png");
                        mFairy.onTap(0.8f, result, "开箱", 5000);

                        result = mFairy.findPic( 302,172,1037,576,"xueren.png");
                        mFairy.onTap(0.8f, result, "打雪 人", Sleep);
                    }
                }
                result =mFairy.findPic(507,525,773,636,"xqlikai.png");
                mFairy.onTap(0.8f,result,"离开",Sleep);

                result = mFairy.findPic("dxztcfb.png");
                mFairy.onTap(0.8f, result, 507,430,542,444, "取消退出副本", Sleep);
            }
        }.taskContent(mFairy, "跟队打雪仗中");
    }//跟队打雪仗

    public void diaoyu() throws Exception {
        new OtherGame(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();
                int w = mFairy.week();
                int s=(h*60+m)*60;

            }
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }
            int rsnum=0;
            @Override
            public void content_1() throws Exception {
                if (overtime(30,0))return;

                result =mFairy.findPic(1040,11,1112,80,"lb1.png");
                mFairy.onTap(0.8f,result,"列表",Sleep);

                result =mFairy.findPic(470,150,1085,232,"bxjnh.png");
                mFairy.onTap(0.8f,result,"冰雪嘉年华",Sleep);

                result =mFairy.findPic(432,278,1262,631,"ddds.png");
                mFairy.onTap(0.8f,result,"冬钓大赛",Sleep);

                result =mFairy.findPic(741,121,844,601,"lingqu1.png");
                mFairy.onTap(0.8f,result,"领取",Sleep);

                result =mFairy.findPic(741,121,844,601,"lingqu1.png");
                mFairy.onTap(0.8f,result,"领取",Sleep);

                result =mFairy.findPic(1,42,81,363,"ddds1.png");
                if (result.sim>0.8f){
                    result =mFairy.findPic(871,585,1181,703,"qwtz.png");
                    mFairy.onTap(0.8f,result,"前往挑战",500);
                    for (int x=0;x<=10;x++){
                        LtLog.e("判断");
                        result =mFairy.findPic(533,76,764,210,new String[]{"tzcs.png","tzcs1.png"});
                        if (result.sim > 0.8f) {
                            LtLog.e("无挑战次数0");
                            setTaskEnd();return;
                        }
                    }
                }else{
                    result =mFairy.findPic(1,42,81,363,"ddds2.png");
                    mFairy.onTap(0.8f,result,"冬钓大赛1",Sleep);
                }
                    result =mFairy.findPic(533,76,764,210,new String[]{"tzcs.png","tzcs1.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e("无挑战次数1");
                        setTaskEnd();return;
                    }
                result =mFairy.findPic(1108,3,1277,34,"ddds3.png");
                if (result.sim>0.8f){
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(30,0))return;

                result =mFairy.findPic(520,176,719,228,"xz.png");
                mFairy.onTap(0.8f,result,1047,558,1058,565,"向左",500);

                result =mFairy.findPic(520,176,719,228,"you.png");
                mFairy.onTap(0.8f,result,1124,490,1132,499,"向右",500);

                result =mFairy.findPic(520,176,719,228,"hou.png");
                mFairy.onTap(0.8f,result,1224,477,1232,484,"向后",500);

                result =mFairy.findPic(520,176,719,228,"shang.png");
                mFairy.onTap(0.9f,result,1048,651,1063,660,"向上",500);

                result =mFairy.findPic(789,247,893,520,"cz.png");
                mFairy.onTap(0.8f,result,"操作",Sleep);

                result =mFairy.findPic(396,320,873,491,"zpqd.png");
                mFairy.onTap(0.8f,result,637,434,652,444,"战袍确定",Sleep);

                result =mFairy.findPic(1108,3,1277,34,"xyc.png");
                if (result.sim>0.8f){
                    setTaskName(0);
                    return;
                }

                result =mFairy.findPic(888,84,1128,220,"shitou.png");
                if (result.sim>0.8f){
                    mFairy.ranSwipe(173,573, 248,509, 1500, (long) 1500);
                    mFairy.ranSwipe(173,573, 159,640, 2300, (long) 2000);
                }
                result =mFairy.findPic(520,176,719,228,"xz.png");
                mFairy.onTap(0.8f,result,1047,558,1058,565,"向左",500);

                result =mFairy.findPic(520,176,719,228,"you.png");
                mFairy.onTap(0.8f,result,1124,490,1132,499,"向右",500);

                result =mFairy.findPic(520,176,719,228,"hou.png");
                mFairy.onTap(0.8f,result,1224,477,1232,484,"向后",500);

                result =mFairy.findPic(520,176,719,228,"shang.png");
                mFairy.onTap(0.9f,result,1048,651,1063,660,"向上",500);

                result =mFairy.findPic(1108,3,1277,34,"ddds3.png");
                if (result.sim>0.8f){
                    err=0;
                }
            }
        }.taskContent(mFairy, "冬钓大赛中");
    }//冬钓大赛

    public void cyrb() throws Exception {
        new OtherGame(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();
                int w = mFairy.week();
                int s=(h*60+m)*60;

            }
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }
            int rsnum=0;
            @Override
            public void content_1() throws Exception {
                if (overtime(30,0))return;

                result =mFairy.findPic(1040,11,1112,80,"lb1.png");
                mFairy.onTap(0.8f,result,"列表",Sleep);

                result =mFairy.findPic(255,130,1158,258,"bxjnh.png");
                mFairy.onTap(0.8f,result,"冰雪嘉年华",Sleep);

                result =mFairy.findPic(432,278,1262,631,"cyrb.png");
                mFairy.onTap(0.8f,result,"炽焰融冰",Sleep);

                result =mFairy.findPic(741,121,844,601,"lingqu1.png");
                mFairy.onTap(0.8f,result,"领取",Sleep);

                result =mFairy.findPic(741,121,844,601,"lingqu1.png");
                mFairy.onTap(0.8f,result,"领取",Sleep);

                result =mFairy.findPic(1,42,81,363,"cyrb2.png");
                if (result.sim>0.8f){
                    result =mFairy.findPic(871,585,1181,703,"qwtz.png");
                    mFairy.onTap(0.8f,result,"前往挑战",500);
                    for (int x=0;x<=10;x++){
                        LtLog.e("判断");
                        result =mFairy.findPic(533,76,764,210,new String[]{"tzcs.png","tzcs1.png"});
                        if (result.sim > 0.8f) {
                            LtLog.e("无挑战次数0");
                            setTaskEnd();return;
                        }
                    }
                }else{
                    result =mFairy.findPic(1,42,81,363,"cyrb1.png");
                    mFairy.onTap(0.8f,result,"炽焰融冰1",Sleep);
                }
                    result =mFairy.findPic(533,76,764,210,"tzcs.png");
                    if (result.sim>0.8f) {
                        LtLog.e("无挑战次数");
                        setTaskEnd();return;
                    }
                result =mFairy.findPic(1108,3,1277,34,"cyrb3.png");
                if (result.sim>0.8f){
                    setTaskName(2);
                    return;
                }
            }
int i=0;
            public void content_2() throws Exception {
                if (overtime(30,0))return;

                result =mFairy.findPic(467,464,467,464,"cz1.png");
                if (result.sim>0.8f){
                    switch (i) {
                        case 0:
                            mFairy.ranSwipe(101,574, 174,574, 500, (long) 900);
                            break;
                        case 1:
                            mFairy.ranSwipe(174,504, 174,574, 500, (long) 900);
                            break;
                        case 2:
                            mFairy.ranSwipe(245,574, 174,574, 500, (long) 900);
                            break;
                        case 3:
                            mFairy.ranSwipe(174,645, 174,574, 500, (long) 900);
                            break;
                    }
                    if (i++>=3){
                        i=0;
                    }
                    mFairy.onTap(0.8f,result,"操作1",2000);
                }


                result1 =mFairy.findPic(621,563,1111,676,"lk1.png");
                result =mFairy.findPic(1108,3,1277,34,"xyc.png");
                if (result.sim>0.8f || result1.sim>0.8f){
                    mFairy.onTap(0.8f,result1,"离开",Sleep);
                    setTaskName(0);
                    return;
                }

                result =mFairy.findPic(1108,3,1277,34,"cyrb4.png");
                if (result.sim>0.8f){
                   err=0;
                }
            }
        }.taskContent(mFairy, "炽焰融冰中");
    }//炽焰融冰

    public void cfdzz() throws Exception {
        new OtherGame(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();
                int w = mFairy.week();
                int s=(h*60+m)*60;

            }
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }
            int rsnum=0;
            @Override
            public void content_1() throws Exception {
                if (overtime(30,0))return;

                result =mFairy.findPic(1040,11,1112,80,"lb1.png");
                mFairy.onTap(0.8f,result,"列表",Sleep);

                result =mFairy.findPic(351,148,1106,243,"cfdzz.png");
                mFairy.onTap(0.8f,result,"乘风大作战",Sleep);

                result =mFairy.findPic(210,490,507,645,"kstz.png");
                mFairy.onTap(0.8f,result,"开始挑战",Sleep);

                result =mFairy.findPic(529,134,760,209,"yxgz.png");
                mFairy.onTap(0.8f,result,961,142,968,156,"游戏规则",Sleep);

                result =mFairy.findPic(502,472,757,546,"djks.png");
                mFairy.onTap(0.8f,result,"点击开始",Sleep);

                result1 =mFairy.findPic(841,370,915,405,"2000.png");
                if (result1.sim>0.8f){
                    LtLog.e("记分已满");
                    setTaskEnd();return;
                }

                result1 =mFairy.findPic(907,4,1277,65,"sysc.png");
                if (result1.sim>0.8f){
                    LtLog.e("游戏中");
                    err=1;
                }

                result =mFairy.findPic(745,547,982,675,"zlyj.png");
                mFairy.onTap(0.8f,result,"再来一局",Sleep);

                result =mFairy.findPic(144,129,1134,472,"rwyj.png");
                if (result.sim>0.8f){
                    result2 =mFairy.findPic(result.x-351,result.y+105,result.x-49,result.y+203,".png");
                    result1 =mFairy.findPic(result.x+49,result.y+105,result.x+351,result.y+203,"qwtz.png");
                    if (result2.sim>0.8f){
                        mFairy.ranSwipe(61,604,84,611, 500, (long) 700);
                    }else if (result1.sim>0.8f){
                        mFairy.ranSwipe(1144,608, 1165,608, 500, (long) 700);
                    }

                }
            }

        }.taskContent(mFairy, "乘风大作战中");
    }//乘风大作战

    public void yytp() throws Exception {
        new OtherGame(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();
                int w = mFairy.week();
                int s=(h*60+m)*60;

            }
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }
            int ydcount=0;
            int sum=0,x=0;
            @Override
            public void content_1() throws Exception {
                if (overtime(30,0))return;

                result =mFairy.findPic(1040,11,1112,80,"lb1.png");
                mFairy.onTap(0.8f,result,"列表",Sleep);

                result =mFairy.findPic(470,150,1085,232,"bxjnh.png");
                mFairy.onTap(0.8f,result,"冰雪嘉年华",Sleep);

                result =mFairy.findPic(432,278,1262,631,"yytp.png");
                mFairy.onTap(0.8f,result,"异元突破",Sleep);

                result =mFairy.findPic(741,121,844,601,"lingqu1.png");
                mFairy.onTap(0.8f,result,"领取",Sleep);

                result =mFairy.findPic(741,121,844,601,"lingqu1.png");
                mFairy.onTap(0.8f,result,"领取",Sleep);

                result =mFairy.findPic(1,42,81,363,"yytp1.png");
                if (result.sim>0.8f){
                    result =mFairy.findPic(871,585,1181,703,"qwtz.png");
                    mFairy.onTap(0.8f,result,"前往挑战",500);
                    for (int x=0;x<=10;x++){
                        LtLog.e("判断");
                        result =mFairy.findPic(533,76,764,210,new String[]{"tzcs.png","tzcs1.png"});
                        if (result.sim > 0.8f) {
                            LtLog.e("无挑战次数0");
                            setTaskEnd();return;
                        }
                    }
                }else{
                    result =mFairy.findPic(1,42,81,363,"yytp2.png");
                    mFairy.onTap(0.8f,result,"冬钓大赛1",Sleep);
                }
                result =mFairy.findPic(533,76,764,210,new String[]{"tzcs.png","tzcs1.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("无挑战次数1");
                    setTaskEnd();return;
                }
                result =mFairy.findPic(1108,3,1277,34,"yytz3.png");
                if (result.sim>0.8f){
                    ydcount=0;
                    sum=0;
                    setTaskName(2);
                    return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(30,0))return;
                result =mFairy.findPic(479,0,858,75,new String[]{"cs.png"});
                if (result.sim > 0.8f) {
                    sum=0;
                }else{
                    sum++;
                }
                result = mFairy.findPic("smOverGraph.png");
                if (result.sim > 0.8f) {
                    sum=0;
                }
                if (sum>=8) {
                    result =mFairy.findPic(1174,148,1273,275,new String[]{"3v3leave.png","3v3leave1.png"});
                    mFairy.onTap(0.8f,result,"退出副本",Sleep);
                    result =mFairy.findPic(562,323,749,380,"tcfb.png");
                    mFairy.onTap(0.8f,result,763,433,779,450,"退出战场",Sleep);
                    setTaskName(0);
                    return;
                }

                result2 =mFairy.findPic(1108,3,1277,34,"yytz3.png");
                if (result2.sim>0.8f){
                    err=0;
                    LtLog.e(mFairy.getLineInfo("竞技场中"));
                    result1 = mFairy.findPic(312,81,582,162,"dr.png");
                    if (result1.sim>0.8f) {
                        x=0;
                        LtLog.e(mFairy.getLineInfo("有敌人"));
                        mFairy.onTap(1172,610,1191,622,"普攻下",100);
                        mFairy.onTap(1172,610,1191,622,"普攻下",100);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 50);
                        mFairy.onTap(1172, 610, 1191, 622, "普攻下", 50);
                        mFairy.onTap(1118, 483, 1138, 497, "技能1", 50);
                        mFairy.onTap(1223, 473, 1242, 489, "技能2", 50);
                        mFairy.onTap(1039, 552, 1057, 569, "技能3", 50);
                        mFairy.onTap(1049, 646, 1065, 663, "技能4", 50);
                        mFairy.onTap(1172,610,1191,622,"普攻下",100);
                        mFairy.onTap(1172,610,1191,622,"普攻下",100);
                    }else {
                        mFairy.onTap(1172,610,1191,622,"普攻下",100);
                        mFairy.onTap(1172,610,1191,622,"普攻下",100);
                        mFairy.onTap(1172,610,1191,622,"普攻下",100);
                        mFairy.onTap(1039, 552, 1057, 569, "技能3", 50);
                        x++;
                        if (x>=3){
                            LtLog.e(mFairy.getLineInfo("没有敌人，移动一下"));
                            ydcount++;
                            if (ydcount<2){
                                mFairy.ranSwipe(174,572,76,643,1500, (long) 50,1);
                            }else if (ydcount<4){
                                mFairy.ranSwipe(174,572,247,492,1500, (long) 50,1);
                            }
                            mFairy.onTap(1172,610,1191,622,"普攻下",100);
                            mFairy.onTap(1172,610,1191,622,"普攻下",100);
                            mFairy.onTap(1172,610,1191,622,"普攻下",100);
                            mFairy.onTap(1039, 552, 1057, 569, "技能3", 50);
                            if (ydcount>=4){
                                ydcount=0;
                            }
                        }
                    }
                }

                result =mFairy.findPic(787,226,948,403,"cz.png");
                mFairy.onTap(0.8f,result,"操作",Sleep);

                result1 =mFairy.findPic(383,161,869,235,"jieshu.png");
                if (result.sim>0.8f || result1.sim>0.8f) {
                    mFairy.onTap(0.8f,result,"离开",Sleep);
                }

            }
        }.taskContent(mFairy, "异元突破");
    }//异元突破
}
