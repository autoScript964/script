package com.script.fairy;


import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import java.util.List;

/**
 * Created by user on 2019/2/15.
 */

public class GamePublicFuntion<T> {
    public AtFairyImpl mFairy;
    public FindResult result;

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
    }

    public  FindResult qx() throws Exception {

        result = mFairy.findPic("llg.png");
        if(result.sim>0.8f){
            mFairy.onTap(732,431,752,440,"",1500);
            return new FindResult();
        }


        result = mFairy.findPic(389, 224, 639, 375, "xinbai.png");
        if (result.sim < 0.85f) {
            return mFairy.findPic(285, 271, 698, 664, new String[]{"qx.png","jujue.png"});
        }


        return new FindResult();
    }

    private int judgeStop = 0;
    public Boolean judgeStop(int m) throws Exception {
        //long num = mFairy.dazeTime(139,61,210,78, 1f, 0, "218,206,190");
        long num = mFairy.mMatTime(139, 61, 71, 17, 0.95f);
        if (num <= 1) {
            judgeStop++;
            if (judgeStop >= 20) {
                judgeStop = 0;
                return true;
            }
        }

        if (num >= m) {
            judgeStop = 0;
            return true;
        }
        return false;
    }//发呆判断

    public boolean mainScene() throws Exception {
        result = mFairy.findPic(new String[]{"package.png", "main1.png","yask.png"});
        if (result.sim > 0.8f) {
            return true;
        }
        return false;
    }//主场景

    public void task() throws Exception {
        result = mFairy.findPic(996,85,1185,191,"task.png");
        mFairy.onTap(0.85f, result, "任务", 1000);

        //result = mFairy.findPic(".png")
    }//任务

    public void close() throws Exception {

        result = mFairy.findPic(750, 1, 1274, 434,"close1.png");
        if(result.sim>0.8f) {
            List<FindResult> listResult = mFairy.findPic(858, 1, 1274, 434, 0.8f, "close1.png");
            for (int i = (listResult.size() - 1); i >= 0; i--) {
                mFairy.onTap(0.8f, listResult.get(i), listResult.get(i).x, listResult.get(i).y, listResult.get(i).x + 1, listResult.get(i).y + 1, "close", 1500);
            }
        }

        result = mFairy.findPic(555,15,1271,331,"close12.png");
        mFairy.onTap(0.8f, result, "close12", 1500);

        result = mFairy.findPic(958,4,1273,373,"close2.png");
        mFairy.onTap(0.8f, result, "close2", 1500);

        result = mFairy.findPic("close3.png");
        mFairy.onTap(0.8f, result, "", 1500);

        result = mFairy.findPic("close4.png");
        mFairy.onTap(0.8f, result, "", 1500);

        result = mFairy.findPic("close5.png");
        mFairy.onTap(0.8f, result, "", 1500);

        result = mFairy.findPic(958,4,1273,373,"close6.png");
        mFairy.onTap(0.8f, result, "close6", 1500);

        result = mFairy.findPic("close8.png");
        mFairy.onTap(0.8f, result, "close8", 1500);

        result = mFairy.findPic("close10.png");
        mFairy.onTap(0.8f, result, "close10", 1500);

        result = mFairy.findPic(948,11,1270,136,"close16.png");
        mFairy.onTap(0.8f, result, "close11", 1500);

        result = mFairy.findPic(948,11,1270,136,"close11.png");
        mFairy.onTap(0.8f, result, "close11", 1500);

        result = mFairy.findPic("close14.png");
        mFairy.onTap(0.8f, result, "close14", 1500);

    }//关闭

    public void rightZoom() throws Exception {
        result = mFairy.findPic("rightZoom.png");
        mFairy.onTap(0.85f, result, "右侧缩放栏", 500);
    }//右侧缩放栏

    public void rank() throws Exception {
        result = mFairy.findPic("rank.png");
        mFairy.onTap(0.9f, result, "队伍", 500);
    }//队伍

    public void ranks() throws Exception {
        for (int i = 0; i < 3; i++) {
            result = mFairy.findPic(new String[]{"rank1.png","ra.png"});
            mFairy.onTap(0.8f, result, "队伍", 500);
        }
    }//队伍

    public void use() throws Exception {

        result = mFairy.findPic(1026,481,1170,572,"tang.png");
        if(result.sim>0.8f){
            useClose();
        }else{
            result = mFairy.findPic(1023, 527, 1117, 637, new String[]{"use.png","use2.png"});
            mFairy.onTap(0.8f, result, "使用", 1000);
        }



    }//使用

    public void useClose() throws Exception {
        result = mFairy.findPic("use1.png");
        mFairy.onTap(0.8f, result, "useClose", 500);
    }//使用

    public void auto() throws Exception {
        result = mFairy.findPic("auto.png");
        mFairy.onTap(0.8f, result, "自动", 500);
    }//自动

    static int ACTLING = 1;

    public void actLing() throws Exception {
        long c;
        boolean bool;
        while (mFairy.condit()) {
            bool = false;
            switch (ACTLING) {
                case 1:
                    c = mFairy.getColorNum(440, 624, 460, 636, 0.96f, 0, "86,180,15");
                    LtLog.e("" + c);
                    if (c > 10) {
                        mFairy.onTap(440, 555, 459, 571, "活跃1", 200);
                        mFairy.onTap(440, 555, 459, 571, "", 500);
                        ACTLING++;
                        bool = true;
                    }
                    break;
                case 2:
                    c = mFairy.getColorNum(590, 623, 613, 637, 0.96f, 0, "86,180,15");
                    if (c > 10) {
                        mFairy.onTap(591, 553, 617, 578, "活跃2", 200);
                        mFairy.onTap(591, 553, 617, 578, "", 500);
                        ACTLING++;
                        bool = true;
                    }
                    break;
                case 3:
                    c = mFairy.getColorNum(740, 623, 762, 639, 0.96f, 0, "86,180,15");
                    if (c > 10) {
                        mFairy.onTap(739, 552, 766, 583, "活跃3", 200);
                        mFairy.onTap(739, 552, 766, 583, "", 500);
                        ACTLING++;
                        bool = true;
                    }
                    break;
                case 4:
                    c = mFairy.getColorNum(890, 621, 912, 637, 0.96f, 0, "86,180,15");
                    if (c > 10) {
                        mFairy.onTap(889, 551, 908, 572, "活跃4", 200);
                        mFairy.onTap(889, 551, 908, 572, "", 500);
                        ACTLING++;
                        bool = true;
                    }
                    break;
                case 5:
                    c = mFairy.getColorNum(1032,622,1048,636, 0.96f, 0, "86,180,15");
                    if (c > 10) {
                        mFairy.onTap(1045, 553, 1063, 573, "活跃5", 200);
                        mFairy.onTap(1045, 553, 1063, 573, "", 500);
                        ACTLING++;
                        bool = true;
                    }
                    break;
            }
            mFairy.onTap(343,578,350,581, "", 500);

            if (bool) {
                continue;
            }

            mFairy.onTap(343,578,350,581, "", 500);
            return;
        }
    }//

    public void activityType(int type) throws Exception {
        switch (type) {
            case 1:
                mFairy.onTap(192, 115, 260, 134, "日常活动", 300);
                break;
            case 2:
                mFairy.onTap(188, 194, 257, 213, "挑战活动", 300);
                break;
            case 3:
                mFairy.onTap(185, 279, 268, 296, "竞技休闲", 300);
                break;
        }
    }//活动类型

    public void rankinit(int type) throws Exception {
        int err = 10;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("rankinit >>>"));


            result = mFairy.findPic("rank15.png");
            mFairy.onTap(0.8f,result,"队伍箭头",500);

            result = mFairy.findPic(531,9,724,85,"rankScene.png");
            if (result.sim > 0.8f) {
                err = 0;

                LtLog.e(mFairy.getLineInfo("队伍界面"));
                result = mFairy.findPic("rank4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1100, 37, 1113, 48, "", 300);
                    return;
                }

                switch (type) {
                    case 1:
                        result = mFairy.findPic("rank3.png");
                        mFairy.onTap(0.8f, result, "暂离", 500);

                        result = mFairy.findPic("rank2.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(1100, 37, 1113, 48, "", 300);
                            return;
                        }

                        result = mFairy.findPic("rank5.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(1100, 37, 1113, 48, "", 300);
                            return;
                        }

                        break;
                    default:
                        result = mFairy.findPic("rank6.png");
                        mFairy.onTap(0.8f, result, "退出队伍", 3000);
                        break;
                }
            } else {
                ranks();
            }
            qx();

            err++;
            if (err > 10) {
                taskInit(0);
                if (mainScene()) {
                    err = 0;
                }
            }
        }
    }//

    public void taskInit(int type) throws Exception {
        int err = 0;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("init >>> "));

            switch (type) {
                case 1:
                    result = mFairy.findPic(228, 5, 747, 99, "activity.png");
                    if (result.sim > 0.8f) {
                        TaskContent.err = 0;
                        mFairy.onTap(0.8f, result, "活动", 3000);
                    }
                    result = mFairy.findPic(new String[]{"activity1.png","activity2.png"});
                    if (result.sim > 0.8f) {
                        return;
                    }

                    break;
                default:
                    if (mainScene()) {
                        err++;
                        if(err>1){
                            return;
                        }
                    }
                    break;
            }

            close();

            fail();

            result = mFairy.findPic(492, 312, 754, 427, new String[]{"sm9.png","sm12.png"});
            mFairy.onTap(0.75f, result, 601,505,647,523, "获取图签", 1500);

            result=qx();
            mFairy.onTap(0.8f,result,"取消",500);

            result = mFairy.findPic( "qd2.png");
            mFairy.onTap(0.8f, result, "确定", 1000);

            result = mFairy.findPic( "bangpai.png");
            mFairy.onTap(0.8f, result, "确定", 1000);

            result = mFairy.findPic(1129,160,1271,549, "mj5.png");
            mFairy.onTap(0.8f, result, "秘境离开", 500);

            result = mFairy.findPic("jia7.png");
            mFairy.onTap(0.8f, result, "聊天框", 1000);

            result = mFairy.findPic("jia9.png");
            mFairy.onTap(0.8f, result, "聊天框", 1000);

            result = mFairy.findPic("battle.png");
            if (result.sim > 0.8f) {
                stopBattle();
            }



            result = mFairy.findPic(915, 4, 1155, 429, new String[]{"shi.png", "shi1.png"});
            mFairy.onTap(0.8f,result,673,313,698,335,"取消做事情",1000);

        }
    }//任务初始化

    int shin = 0;
    public boolean shi() throws Exception {
        result = mFairy.findPic(915, 4, 1155, 429, new String[]{"shi.png", "shi1.png"});
        if (result.sim > 0.8f) {
            TaskContent.err = 0;
            mFairy.onTap(0.8f, result, result.x, result.y + 100, result.x + 1, result.y + 108, "做事情", 500);
            return true;
        }
        return false;
    }//做事情

    public boolean zheng() throws Exception {
        result = mFairy.findPic(980, 11, 1098, 484, new String[]{"zheng.png"});
        if (result.sim > 0.8f) {
            TaskContent.err = 0;
            mFairy.onTap(0.8f, result, result.x, result.y + 100, result.x + 1, result.y + 108, "选择答案", 500);
            return true;
        }
        return false;
    }//选择答案

    public boolean fail() throws Exception {
        result = mFairy.findPic(539, 356, 831, 515, new String[]{"fail.png", "fail1.png"});
        if (result.sim > 0.85f) {
            mFairy.onTap(0.85f, result, 613, 560, 647, 586, "战斗失败", 1000);
            return true;
        }
        return false;
    }//战斗失败

    public boolean home()throws Exception{
        int err = 0;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("回到长安城"));
            err++;
            if(err>10){
                taskInit(0);
                err=0;
            }

            if (mainScene()) {
                result = mFairy.findPic(41, 4, 265, 70, "yb1.png");
                if (result.sim > 0.73f) {
                    return true;
                } else {
                    mFairy.onTap(27, 34, 45, 53, "", 1000);
                }
            }

            result = mFairy.findPic(64,10,249,71,new String[]{"gl.png","gl2.png"});
            if(result.sim>0.8f){
                return false;
            }

            result = mFairy.findPic(new String[]{"yb2.png","home1.png"});
            if (result.sim > 0.8f) {
                err = 0;
                result = mFairy.findPic(311,293,1128,592, new String[]{"yb3.png","home2.png"});
                mFairy.onTap(0.8f, result, "点击长安城", 3000);
            }
        }
        return true;
    }//回家

    public boolean battle() throws Exception {
        result = mFairy.findPic("battle.png");
        if (result.sim > 0.8f) {
            TaskContent.err = 0;
            return true;
        }
        return false;
    }//战斗

    public void skip() throws Exception {
        boolean s = false;
        while (mFairy.condit()) {
            result = mFairy.findPic(new String[]{"skip.png", "skip1.png"});
            if (result.sim > 0.8f) {
                s=true;
                mFairy.onTap(0.8f, result, "skip", 1000);
            }else{
                break;
            }
        }

        if(s){
            TaskContent.err = 0;
            mFairy.initDaze();
        }
    }//skip

    public boolean gm() throws Exception {

        result = mFairy.findPic("gmerr.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("暂时没有商品,End!"));
            return true;
        }else {
            result = mFairy.findPic(706, 346, 1258, 709, new String[]{"gm.png", "gm2.png","kg15.png"});
            if (result.sim > 0.85f) {
                mFairy.onTap(0.85f, result, "购买", 500);
                TaskContent.err = 0;
                for (int i = 0; i < 8; i++) {
                    result = mFairy.findPic(657, 243, 742, 425, new String[]{"err1.png","err2.png", "err3.png"});
                    if (result.sim > 0.8f) {
                        mFairy.finish(AtFairyConfig.getTaskID(), 1301);
                        return true;
                    }
                }

                result = mFairy.findPic(858, 1, 1274, 434,"close1.png");
                mFairy.onTap(0.8f,result,"",500);

            }

            result = mFairy.findPic(new String[]{"err4.png","jia2.png"});
            if (result.sim > 0.85f) {
                mFairy.onTap(866,244,880,260,"银币不足",500);
                mFairy.finish(AtFairyConfig.getTaskID(), 4596);
                return true;
            }
        }
        return false;
    }//购买

    public int rankNum()throws Exception{
        result = mFairy.findPic(338,152,454,220,"rank10.png");
        if(result.sim>0.85f){
            LtLog.e(mFairy.getLineInfo("目前队伍人数：1人"));
            return 1;
        }
        result = mFairy.findPic(529,155,663,220,"rank10.png");
        if(result.sim>0.85f){
            LtLog.e(mFairy.getLineInfo("目前队伍人数：2人"));
            return 2;
        }
        result = mFairy.findPic(722,154,851,219,"rank10.png");
        if(result.sim>0.85f){
            LtLog.e(mFairy.getLineInfo("目前队伍人数：3人"));
            return 3;
        }
        result = mFairy.findPic(917,155,1043,213,"rank10.png");
        if(result.sim>0.85f){
            LtLog.e(mFairy.getLineInfo("目前队伍人数：4人"));
            return 4;
        }
        LtLog.e(mFairy.getLineInfo("目前队伍人数：满人"));
        return 5;
    }//队伍人数、

    public void stopBattle()throws Exception{
        int num = 0;
        int err=0;
        while (mFairy.condit()){
            LtLog.e(mFairy.getLineInfo("停止  >>>"));
            if(mainScene()){
                err=0;
                if(num==0) {
                    mFairy.onTap(654, 316, 683, 340, "", 300);
                    mFairy.onTap(654, 316, 683, 340, "", 300);
                    mFairy.onTap(654, 316, 683, 340, "", 1000);
                    num=1;
                }

                if(judgeStop(3)){
                    return;
                }
            }else{

                result = mFairy.findPic("battle.png");
                if (result.sim > 0.8f) {
                    err=0;
                    num=0;
                }else {
                    result = qx();
                    mFairy.onTap(0.8f, result, "取消", 500);
                    fail();
                    close();

                    err++;
                    if(err>20){
                        return;
                    }
                }
            }



        }

    }//

}
