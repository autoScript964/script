package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
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
    GameUtil yxdUtil ;
    OtherGame otherGame;
    public TeamTask(AtFairyImpl ATFairy) throws Exception {
        mFairy = ATFairy;
        gameUtil = new GameUtil(mFairy);
        mFairy1 = ATFairy;
        yxdUtil=new GameUtil(mFairy);
        otherGame=new OtherGame(mFairy);
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
    //一条龙
    public  void  aDragon(final String str)throws Exception{
        new TeamTask(mFairy){
            @Override
            public void content_0() throws Exception {
                setTaskName(1);return;
            }
            public void content_1() throws Exception {
                int ret=gameUtil.mission(str,1);
                if (ret==1){
                    setTaskName(2);return;
                }else {
                    setTaskEnd();return;
                }
            }
            int js_1 = 0;
            int tcy = 201, tcy1 = 248;
            public void content_2() throws Exception {
                if (overtime(30,0))return;
                result = mFairy.findPic(49, 376, 495, 586, new  String[]{"LeftCopy.png","LeftCopy1.png","LeftPoyougu.png","LeftZhuRong.png"});
                mFairy.onTap(0.8f, result, "左侧副本", Sleep);

                result = mFairy.findPic(383, 564, 1143, 654,"ytlEnterCopy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进入副本"));
                    Thread.sleep(2000);
                    result = mFairy.findPic("NoCaptain.png");
                    if (result.sim > 0.8f) {
                        gameUtil.close(1);
                        LtLog.e("关叉完毕");
                        while (mFairy.condit()){
                            result = mFairy.findPic("LeftCaptain.png");
                            mFairy.onTap(0.9f, result, "左侧队伍", 2000);

                            result = mFairy.findPic(new  String[]{"ytlLeaveTheTeam.png"});
                            mFairy.onTap(0.8f, result, "离队", Sleep);

                            result = mFairy.findPic("ldSure.png");
                            mFairy.onTap(0.8f, result, "离队确定", Sleep);
                            if (result.sim > 0.8f) {
                                break;
                            }
                            result = mFairy.findPic("qwzd.png");
                            mFairy.onTap(0.8f, result, "前往组队", Sleep);


                            result = mFairy.findPic("CreateTeam.png");
                            if (result.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("创建队伍"));
                                break;
                            }
                            result = mFairy.findPic("ytlLeaveTheTeam1.png");
                            mFairy.onTap(0.8f, result, "离队", Sleep);
                            if (result.sim > 0.8f) {
                                break;
                            }
                            Thread.sleep(2000);
                        }
                        LtLog.e("——————————");
                        organizeTeam();
                        setTaskName(0);return;
                    }
                    /*result = mFairy.findPic(384, 411, 1152, 547,"People.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("有几个人"));
                        if (result.x > 840) {
                            LtLog.e(mFairy.getLineInfo("人够了"));
                            mFairy.onTap(992, 608,993, 609,"队伍召集", Sleep);
                            mFairy.onTap(748, 613, 749, 614,"进入副本", 10000);
                        }else {
                            gameUtil.close(1);
                            organizeTeam();
                            setTaskName(0);return;
                        }
                    }*/

                    result = mFairy.findPic("fuben.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("有几个人"));
                        result = mFairy.findPic(740,400,853,532,"People.png");
                        if (result.sim < 0.8f) {
                            LtLog.e(mFairy.getLineInfo("人够了"));
                            result = mFairy.findPic(383, 564, 1143, 654,"zhaoji.png");
                            mFairy.onTap(0.8f, result, "队伍召集", 2000);
                            result = mFairy.findPic(383, 564, 1143, 654,"ytlEnterCopy.png");
                            mFairy.onTap(0.8f, result, "进入副本", 10000);
                        }else {
                            gameUtil.close(1);
                            organizeTeam();
                            setTaskName(0);return;
                        }
                    }

                }

                result = mFairy.findPic("FailureCopy.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("副本失败"));
                    if (js_1>1){
                        result = mFairy.findPic(861, tcy, 961, tcy1,"KickOut.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("踢出去"));
                            result1 = mFairy.findPic(result.x - 430, result.y - 6, result.x - 306, result.y + 33,"Ready.png");
                            if (result1.sim > 0.8f) {

                            }else {
                                mFairy.onTap(0.8f, result, "踢出去", 2000);
                                result = mFairy.findPic("tcSure.png");
                                mFairy.onTap(0.8f, result, "踢出确定", Sleep);
                            }
                            tcy = tcy + 73;
                            tcy1 = tcy1 + 68;
                            js_1 = 0;
                            if (tcy > 500) {
                                tcy = 201;
                                tcy1 = 248;
                            }
                        }
                    }else {
                        mFairy.onTap(1004, 149,1005, 150,"副本失败叉", 2000);
                        mFairy.onTap(992, 608,993, 609,"队伍召集", 10000);
                        mFairy.onTap(748, 613,749, 614,"进入副本", 2000);
                        js_1++;
                    }
                }

                result = mFairy.findPic("tcSure.png");
                mFairy.onTap(0.8f, result, "踢出确定", Sleep);

                result = mFairy.findPic("qsknow.png");
                mFairy.onTap(0.8f, result, 1081, 644,1082, 645,"七杀我知道了", Sleep);
                mFairy.onTap(0.8f, result, "七杀我知道了", Sleep);


                result =mFairy.findPic("Replica.png");
                if (picCountS(0.8f,result,"一条龙不在副本中")>20){
                    LtLog.e(mFairy.getLineInfo("长时间没有找到副本"));
                    gameUtil.pullHeel();
                    setTaskName(0);return;
                }
                if (result.sim>0.8f){
                    result = mFairy.findPic("Hangup1.png");
                    if (result.sim>0.8f){
                        result = mFairy.findPic("xuetiao.png");
                        if (picCountS(0.8f,result,"血条")>5){
                            mFairy.onTap(148,215,164,224,"副本中点左侧任务",3000);
                        }
                    }else {
                        mFairy.onTap(148,215,164,224,"副本中点左侧任务",3000);
                        while (mFairy.condit()){
                            super.inOperation();
                            long dazeTime=mFairy.mMatTime(1215,128,61,16,0.9f);
                            LtLog.e(mFairy.getLineInfo("发呆时间="+dazeTime));
                            if (dazeTime>3){
                                mFairy.initMatTime();
                                result =mFairy.findPic(1099,277,1173,348,"NoBattle.png");
                                mFairy.onTap(0.8f,result,"开启自动战斗",Sleep);
                                break;
                            }
                        }
                    }
                }

                result =mFairy.findPic("CopyLeave.png");
                mFairy.onTap(0.8f, result, "副本离开", 20000);
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("这个副本结束了"));
                    gameUtil.pullHeel();
                    setTaskName(0);return;
                }
            }
        }.taskContent(mFairy,"一条龙任务中");
    }

    //一条龙扫荡
    public  void  aDragon1(final String str)throws Exception{
        new TeamTask(mFairy){
            @Override
            public void content_0() throws Exception {
                setTaskName(1);return;
            }
            public void content_1() throws Exception {
                int ret=gameUtil.mission(str,1);
                if (ret==1){
                    setTaskName(2);return;
                }else {
                    setTaskEnd();return;
                }
            }
            public void content_2() throws Exception {
                result = mFairy.findPic(49, 376, 495, 586, new  String[]{"LeftCopy.png","LeftCopy1.png","LeftPoyougu.png","LeftZhuRong.png"});
                mFairy.onTap(0.8f, result, "左侧副本", Sleep);

                result =mFairy.findPic(365, 521, 1117, 604,"sdldengji.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("使用等级不足"));
                    setTaskEnd();return;
                }
                result =mFairy.findPic("sdlinsufficient.png");
                mFairy.onTap(0.8f, result,  514, 443, 515, 444,"扫荡令不足", Sleep);
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("扫荡令不足"));
                    setTaskEnd();return;
                }
                result =mFairy.findPic("ytlEnterCopy1.png");
                mFairy.onTap(0.8f, result,   630, 614, 631, 615,"进入副本1", Sleep);

                result =mFairy.findPic(583,575,910,655,"brcsyyw.png");
                if (result.sim > 0.8f){
                    LtLog.e("没有次数");
                    setTaskEnd();return;
                }

                result1 =mFairy.findPic(417,563,1088,649,"sdlInterface.png");
                result =mFairy.findPic(578,563,951,657,"ytlEnterCopy.png");
                /*mFairy.onTap(0.8f, result,    588,604,616,621,"进入副本", Sleep);*/
                if (result.sim > 0.8f && result1.sim <0.8f){
                    LtLog.e("没有扫荡");
                    setTaskEnd();return;
                }

                result =mFairy.findPic(453,557,1034,667,"sdlInterface.png");
                mFairy.onTap(0.8f, result,    583,608,590,611,"扫荡副本", Sleep);
                result =mFairy.findPic(302,179,961,255,"sdlInterface1.png");
                mFairy.onTap(0.8f, result,    371, 373,372, 374,"扫荡令界面", Sleep);
                mFairy.onTap(0.8f, result,    772, 467,773, 468,"扫荡令界面", 2000);
                if (result.sim > 0.8f) {
                    result =mFairy.findPic("sdlinsufficient.png");
                    mFairy.onTap(0.8f, result,  514, 443, 515, 444,"扫荡令不足", Sleep);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("扫荡令不足"));
                        setTaskEnd();return;
                    }
                }
            }
        }.taskContent(mFairy,"一条龙扫荡中");
    }

    //组人
    public  void  organizeTeam()throws Exception{
        new TeamTask(mFairy){
            @Override
            public void content_0() throws Exception {
                 gameUtil.close(1);
                 setTaskName(1);return;
            }
            int rsnum=0;
            public void content_1() throws Exception {
                result = mFairy.findPic("LeftCaptain.png");
                mFairy.onTap( 0.9f, result, "左侧队伍", Sleep);

                result = mFairy.findPic("qwzd.png");
                mFairy.onTap( 0.8f, result, "前往组队", Sleep);

                result = mFairy.findPic("CreateTeam.png");
                mFairy.onTap( 0.8f, result, "创建队伍", Sleep);

                result = mFairy.findPic("CreateTeam1.png");
                mFairy.onTap( 0.8f, result, 606,236,607,237,"目标", Sleep);
                mFairy.onTap( 0.8f, result, 531,300,547,307,"一条龙", Sleep);
                mFairy.onTap( 0.8f, result, "创建队伍", Sleep);

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
                result = mFairy.findPic("jindui.png");
                mFairy.onTap( 0.8f, result, "有人进队", Sleep);

                result = mFairy.findPic(891, 205, 1015, 564,"agree.png");
                mFairy.onTap( 0.8f, result, "同意别人进队", Sleep);
                rsnum=0;
                result = mFairy.findPic(341, 257, 602, 336,"ytlPeople.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进来个人"));
                    rsnum++;
                }

                result = mFairy.findPic(339, 350, 602, 432, "ytlPeople.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进来个人"));
                    rsnum++;
                }
                result = mFairy.findPic(336, 447, 602, 527, "ytlPeople.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进来个人"));
                    rsnum++;
                }
                result = mFairy.findPic(333, 541, 603, 625, "ytlPeople.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("进来个人"));
                    rsnum++;
                }

                if (rsnum > 1) {
                    Thread.sleep(2000);
                    result = mFairy.findPic("HeelStation.png");
                    mFairy.onTap( 0.8f, result, "发起跟站", Sleep);
                    Thread.sleep(2000);

                    result = mFairy.findPic("gzSure.png");
                    mFairy.onTap( 0.8f, result, "跟站确定", Sleep);

                    mFairy.onTap( 707, 313,708, 314,"收起组队框", Sleep);
                    gameUtil.close(1);
                    setTaskEnd();return;
                }
                if (timekeep(0,600000,"超过10分钟没组到人")){
                    LtLog.e(mFairy.getLineInfo("--------超过10分钟没组到人"));
                    setTaskName(0);
                    return;
                }
                Thread.sleep(2000);
            }
        }.taskContent(mFairy,"组人任务中");
    }

}
