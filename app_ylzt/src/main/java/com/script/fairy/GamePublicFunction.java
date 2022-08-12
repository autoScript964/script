package com.script.fairy;

import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairy2;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/21.
 */

public class GamePublicFunction {

    private AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    private boolean mTaskStart=false;
    public static final int openActivity_dailt = 1;//日常
    public static final int openActivity_copy = 2;// 副本
    public static final int openActivity_family = 3;//家族
    public static final int openActivity_battlefront = 4;//战场
    public static final int openActivity_other = 5;//其他
    public static final int openActivity_reward = 6;//悬赏
    public static final int openActivity_retrieve = 7;//找回


    public GamePublicFunction(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(ypFairy);

        LtLog.i(publicFunction.getLineInfo() + "------GamePublicFunction-mFairy->" + mFairy);
    }

    public void openMap(String str) throws Exception {
        //打开世界地图 world
        //打开当前地图 current
        AtFairy2.OpencvResult result;
        LtLog.i(publicFunction.getLineInfo() + "------openMap->");
        for (int i = 0; i < 3; i++) {
            closeWindow();
        }

        result = publicFunction.localFindPic(890, 0, 1020, 123, "activity.png");
        LtLog.i(publicFunction.getLineInfo() + "------activity->" + result);
        if (result.sim < 0.8) {
            return;
        }

        for (int i = 0; i < 5; i++) {

            result = publicFunction.localFindPic(1197, 0, 1280, 80, "down.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------down->" + result);
                publicFunction.rndTapWH(result.x, result.y, 25, 16);
                Thread.sleep(500);
            }

            result = publicFunction.localFindPic(1184, 13, 1280, 135, "videoCamera.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------videoCamera->" + result);
                publicFunction.rndTap(1143, 104, 1191, 164);
                Thread.sleep(500);
            }
//            result = publicFunction.localFindPic(961, 606, 1167, 720, "nationalGeography.png");
//            if (result.sim >= 0.8) {
//                LtLog.i(publicFunction.getLineInfo() + "------nationalGeography->" + result);
//                if (str.equals("current")) {
//                    break;
//                }
//                publicFunction.rndTapWH(result.x, result.y, 106, 25);
//                Thread.sleep(500);
//            }
            result = publicFunction.localFindPic(961, 606, 1167, 720, "nationalGeography.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------nationalGeography->" + result);
                if (str.equals("current")) {
                    break;
                }
                publicFunction.rndTapWH(result.x, result.y, 106, 25);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(1069, 610, 1276, 720, "sceneMap.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------sceneMap->" + result);
                if (str.equals("world")) {
                    break;
                }
                publicFunction.rndTapWH(result.x, result.y, 107, 27);
                Thread.sleep(500);
            }
            Thread.sleep(1500);
        }
    }

    //查看任务状态和参加任务-坠星屿
    public List<String> lookupTaskZXY(List<String> list) throws Exception {
        List<String> list1 = list;
        if(list1.size()<=0){
            return list1;
        }
        AtFairy2.OpencvResult result;
        AtFairy2.OpencvResult result1;
        int second = 0;
        long time = System.currentTimeMillis() / 1000, timex = 0;
        LtLog.i(publicFunction.getLineInfo() + "-------list1>" + list1);
        result = publicFunction.localFindPic(0, 78, 166, 197, "goto4.png");
        if (result.sim < 0.8) {
            return list1;
        }
        int mTime=publicFunction.getMinuteNumber();

        while (mFairy.condit()) {

            result = publicFunction.localFindPic(292,49,574,190, "avtivity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------avtivity=" + result);
                publicFunction.rndTapWH(result.x,result.y,46,21);
                Thread.sleep(500);
            }

            result = publicFunction.localFindPic(266,203,400,554, list1.get(0) + ".png");
            LtLog.i(publicFunction.getLineInfo() + "-------lookupTask--list1.get(0)>" + list1.get(0) + "=" + result);

            if (result.sim >= 0.8) {
                result1 = publicFunction.localFindPic(result.x + 793, result.y, result.x + 977, result.y + 84, "goto5.png|attend3.png");
                //接任务
                if (result1.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------goto5>" + result1);
                    publicFunction.rndTap(result1.x, result1.y, result1.x + 50, result1.y + 10);
                    Thread.sleep(1000);
//                    closeWindow();
                    mTaskStart=false;
                    return list1;
                } else {
                    result1 = publicFunction.localFindPic(result.x + 793, result.y, result.x + 977, result.y + 84, "complete1.png");
                    //任务完成
                    if (result1.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "-------complete1>" + result1 + ",list1=" + list1 + list1.size() + list1.get(0));
                        list1.remove(0);
                        if (list1.size() == 0) {
                            closeWindow(1075, 0, 1260, 89);
                        }
                        mTaskStart=true;
                        return list1;
                    }
                }
            }

            result = publicFunction.localFindPic(0, 78, 166, 197, "goto4.png");
            if (result.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------goto4=" + result);
                return list1;
            }
            if (second >= 5) {
                LtLog.i(publicFunction.getLineInfo() + "---------从上往下滑动=" + result);
                publicFunction.RanSwipe(389, 213, 965, 400, 0, 500);
            } else {
                LtLog.i(publicFunction.getLineInfo() + "---------从下往上滑动=" + result);
                publicFunction.RanSwipe(389, 213, 965, 400, 2, 500);
            }
            Thread.sleep(1000);
            second = second + 1;
            if (second >= 10) {
                second = 0;
            }
            timex = System.currentTimeMillis() / 1000 - time;
            LtLog.i(publicFunction.getLineInfo() + "---------timex=" + timex);
            if (timex >= 300) {
                LtLog.i(publicFunction.getLineInfo() + "---------长时间未找到任务");
                list1.remove(0);
                break;
            } else if (timex >= 120) {
                LtLog.i(publicFunction.getLineInfo() + "---------长时间未找到任务删除list1（0）" + list1.get(0));
                list1.remove(0);
                return list1;
            }

            Thread.sleep(100);
        }
        return list1;
    }

    //查看任务状态和参加任务
    public List<String> lookupTask(List<String> list,int TaskType) throws Exception {
        List<String> list1 = list;
        if(list1.size()<=0){
            return list1;
        }
        int mType=TaskType;
        AtFairy2.OpencvResult result;
        AtFairy2.OpencvResult result1;
        int second = 0;
        long time = System.currentTimeMillis() / 1000, timex = 0;
        LtLog.i(publicFunction.getLineInfo() + "-------list1>" + list1);
        result = publicFunction.localFindPic(121, 0, 249, 115, "dailt.png");
        if (result.sim < 0.8) {
            return list1;
        }
        rewardReceive();//领取奖励
        int mTime=publicFunction.getMinuteNumber();
        if(list1.get(0).equals("anchor")){
            if(mTime<=600 && mTime>=0){//在0：00 点到 10:00 不能做 主播任务
                list1.remove("anchor");
            }
        }
        if(list1.get(0).equals("feather")){
            if(mTime<=480 && mTime>=0){//在0：00 点到 8:00 不能做 运镖任务
                list1.remove("feather");
            }
        }
        if(list1.get(0).equals("learn")){
            if(mTime<=720 && mTime>=0){//在0：00 点到 8:00 不能做 护国寺
                list1.remove("learn");
            }
        }
        if (list1.get(0).equals("travel") || list1.get(0).equals("anchor")){
            //周游列国和主播陪你玩,这两个任务是在其他任务里面，但是我把他归类到日常，所以这两个任务做特殊处理
            mType=openActivity_other;
            for (int i = 1; i <=7 ; i++) {
                int colorNunber= publicFunction.getColorNunber2(175, 96 + ((i - 1) * 72), 89, 56,"125,6,0",0.9);
                //判断当前窗口类型是否是传入的类型，如果不是就重新定位
                LtLog.i(publicFunction.getLineInfo() + "------colorNunber->" + colorNunber);
                if (colorNunber >= 100) {
                    LtLog.i(publicFunction.getLineInfo() + "------mType->" + mType);
                    if(i!=mType){
                        openActivity(mType);
                        Thread.sleep(2000);
                        break;
                    }
                }
            }
        }
        LtLog.i(publicFunction.getLineInfo() + "-------mType>" + mType);
        Thread.sleep(1000);
        while (mFairy.condit()) {
            result = publicFunction.localFindPic(365, 138, 498, 582, list1.get(0) + ".png");
            LtLog.i(publicFunction.getLineInfo() + "-------lookupTask--list1.get(0)>" + list1.get(0) + "=" + result);
            if (result.sim >= 0.8) {
                result1 = publicFunction.localFindPic(result.x + 609, result.y, result.x + 763, result.y + 71, "attend.png|attend1.png|attend2.png");
                //接任务
                if (result1.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------attend>" + result1);
                    publicFunction.rndTap(result1.x, result1.y, result1.x + 50, result1.y + 10);
                    Thread.sleep(1000);
//                    closeWindow();

                    if(list1.get(0).equals("visit")){
                        closeWindow();
                    }

                    mTaskStart=false;
                    return list1;
                } else {
                    result1 = publicFunction.localFindPic(result.x + 609, result.y, result.x + 763, result.y + 71, "complete1.png");
                    //任务完成
                    if (result1.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "-------complete1>" + result1 + ",list1=" + list1 + list1.size() + list1.get(0));
                        if(list1.get(0).equals("visit")){
                            //中立区参拜，完成此任务后回到主城
                            goMianCity();
                        }
                        list1.remove(0);
                        if (list1.size() == 0) {
                            closeWindow(1075, 27, 1138, 89);
                        }
                        mTaskStart=true;
                        return list1;
                    }
                    result1 = publicFunction.localFindPic(result.x + 609, result.y-20, result.x + 763, result.y + 71, "conduct.png");
                    LtLog.i(publicFunction.getLineInfo() + "-------conduct>" + result1);
                    if (result1.sim >= 0.8) {
                        //御龙温泉或运镖，正在进行中
                        LtLog.i(publicFunction.getLineInfo() + "-------conduct>" + result1);
                        closeWindow(1075, 27, 1138, 89);
                        Thread.sleep(30000);
                        mTaskStart=true;
                        return list1;
                    }
                }
            }
            result = publicFunction.localFindPic(121, 0, 249, 115, "dailt.png");
            if (result.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------不在日常窗口，退出=");
                return list1;
            }
            if (second >= 6) {
                LtLog.i(publicFunction.getLineInfo() + "---------从上往下滑动=" + result);
                publicFunction.RanSwipe(389, 213, 965, 400, 0, 1000);
            } else {
                LtLog.i(publicFunction.getLineInfo() + "---------从下往上滑动=" + result);
                publicFunction.RanSwipe(389, 213, 965, 400, 2, 1000);
            }
            Thread.sleep(1000);
            second = second + 1;
            if (second >= 12) {
                second = 0;
            }
            timex = System.currentTimeMillis() / 1000 - time;
            LtLog.i(publicFunction.getLineInfo() + "---------timex=" + timex);
            if (timex >= 300) {
                LtLog.i(publicFunction.getLineInfo() + "---------长时间未找到任务");
                list1.remove(0);
                break;
            } else if (timex >= 120) {
                LtLog.i(publicFunction.getLineInfo() + "---------长时间未找到任务删除list1（0）" + list1.get(0));
                list1.remove(0);
                return list1;
            }
            for (int i = 1; i <=7 ; i++) {
                int colorNunber= publicFunction.getColorNunber2(175, 96 + ((i - 1) * 72), 89, 56,"125,6,0",0.9);
                //判断当前窗口类型是否是传入的类型，如果不是就重新定位
                LtLog.i(publicFunction.getLineInfo() + "------colorNunber->" + colorNunber);
                if (colorNunber >= 100) {
                    LtLog.i(publicFunction.getLineInfo() + "------mType->" + mType);
                    if(i!=mType){
                        openActivity(mType);
                        Thread.sleep(2000);
                        break;
                    }
                }
            }
            Thread.sleep(100);
        }
        return list1;
    }
    //返回任务的状态，如果任务执行中 mTaskStart返回false 如果任务完成 mTaskStart返回true
    public boolean taskStart(){
        LtLog.i(publicFunction.getLineInfo() + "---------taskStart=" + mTaskStart);
        return mTaskStart;
    }
    //领取活跃奖励
    public void rewardReceive() throws Exception {
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            list.add(0, 235 + (i * 110) + ",617");
            list1.add(publicFunction.getColor(list).get(0));
        }
        Thread.sleep(500);
        for (int i = 0; i <= 4; i++) {
            list.add(0, 235 + (i * 110) + ",617");
            if ((publicFunction.getColor(list).get(0)).equals(list1.get(i)) == false) {
                mFairy.tap(250 + (i * 110), 617);
            }
        }
    }

    public void openActivity(int windowType) throws Exception {
        // 1daily 日常  // 2copy 副本 // 3家族 battlefield // 4 战场 battlefront  //5其他 other // 找回 retrieve
        AtFairy2.OpencvResult result,result1;

        switchDaily();
        result = publicFunction.localFindPic(890, 0, 1020, 123, "activity.png");
        result1 = publicFunction.localFindPic(121, 0, 249, 115, "dailt.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------activity->" + result);
            publicFunction.rndTapWH(result.x, result.y, 30, 31);
            Thread.sleep(500);
        } else {
            if(result1.sim<0.8){
                return;
            }

        }

        for (int i = 0; i < 3; i++) {
            result = publicFunction.localFindPic(890, 0, 1020, 123, "activity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------activity->" + result);
                publicFunction.rndTapWH(result.x, result.y, 30, 31);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(121, 0, 249, 115, "dailt.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------dailt->" + result);
                //publicFunction.rndTapWH(187, 113 + ((windowType - 1) * 84), 54, 30);

                publicFunction.rndTapWH(181, 105 + ((windowType - 1) * 72), 54, 30);
                Thread.sleep(500);
                return;
            }
            Thread.sleep(500);
        }
        Thread.sleep(1000);
    }

    public void openZXY() throws Exception {
        AtFairy2.OpencvResult result;
        for (int i = 0; i <5 ; i++) {
            result = publicFunction.localFindPic(676,5,1273,345, "ZXY.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------ZXY=" + result);
                publicFunction.rndTapWH(result.x,result.y,41,44);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(455, 55, 601, 176, "avtivity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------avtivity=" + result);
                publicFunction.rndTapWH(result.x,result.y,46,21);
                Thread.sleep(500);
                break;
            }
            Thread.sleep(1000);
        }


    }

    //复活
    public void Resurrection() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "------Resurrection->");
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(520, 297, 758, 390, "Resurrection1.png|Resurrection2.png|Resurrection3.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------Resurrection1->" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(284, 295, 498, 393, "Resurrection.png|Resurrection4.png");
        if (result.sim < 0.8) {
            return;
        }else if (TaskMain.invisible){
            //勾选了使用隐身符
            result = publicFunction.localFindPic(520, 297, 758, 390, "hook.png");
            if(result.sim<0.8){
                LtLog.i(publicFunction.getLineInfo() + "------hook->" + result);
                publicFunction.rndTap(633,496,664,521);
                Thread.sleep(1000);
            }
        }
        result = publicFunction.localFindPic(842, 376, 962, 447, "free.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "------free->" + result);
            publicFunction.rndTap(842, 325, 960, 358);
            Thread.sleep(1000);
            return;
        }
        result = publicFunction.localFindPic(589, 389, 699, 440, "free.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "------free->" + result);
            publicFunction.rndTap(595, 325, 703, 358);
            Thread.sleep(1000);
            return;
        } else {
            result = publicFunction.localFindPic(520, 297, 758, 390, "hook.png");
            if(result.sim>=0.8){
                LtLog.i(publicFunction.getLineInfo() + "------hook->" + result);
                publicFunction.rndTap(633,496,664,521);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(284, 295, 498, 393, "Resurrection.png|Resurrection4.png");
            LtLog.i(publicFunction.getLineInfo() + "------Resurrection->" + result);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------Resurrection->" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
                Thread.sleep(2000);
            }
        }
    }

    //车夫，传送
    public void car() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(552, 40, 686, 174, "car.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------car->" + result);
            result = publicFunction.localFindPic(642, 171, 766, 575, "green.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------green->" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(1000);
                return;
            }
            result = publicFunction.localFindPic(642, 171, 766, 575, "red.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------red->" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(1000);
                return;
            }

        }

    }

    public void hand() throws Exception {

        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(532, 457, 771, 588, "hand.png");
        LtLog.i(publicFunction.getLineInfo() + "------hand->" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------hand->" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(1000);
        }

    }

    public void exitTeam() throws Exception {


    }

    public void closeWindow() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(719,0,1280,437, "fork.png");
        LtLog.i(publicFunction.getLineInfo() + "------fork->" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------fork->" + result);
            publicFunction.rndTapWH(result.x, result.y, 29, 29);
            Thread.sleep(2000);
        }
    }

    public void closeWindow(int x1, int y1, int x2, int y2) throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(x1, y1, x2, y2, "fork.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------fork->" + result);
            publicFunction.rndTapWH(result.x, result.y, 29, 29);
            Thread.sleep(500);
        }
    }

    public void clickDetermine(int x1, int y1, int x2, int y2) throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(x1, y1, x2, y2, "determine.png"+"|"+"determine1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------determine->" + result);
            publicFunction.rndTapWH(result.x,result.y,27,13);
            Thread.sleep(500);
        }
    }

    public void goMianCity() throws Exception {
        MatTime matTime=new MatTime(mFairy);
        long sleepTime=0;
        AtFairy2.OpencvResult result;
        LtLog.i(publicFunction.getLineInfo() + "去主城");

        result = publicFunction.localFindPic(1024,3,1274,71, "mianCity.png");
        LtLog.i(publicFunction.getLineInfo() + "------mianCity->" + result);
        if (result.sim >= 0.75) {
            return;
        }
        openMap("world");

        for (int i = 0; i < 3; i++) {
            result = publicFunction.localFindPic(185,237,534,517, "king.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------king->" + result);
                publicFunction.rndTapWH(result.x+45,result.y-64,10,10);
                Thread.sleep(2000);
            }
            for (int j = 0; j <3 ; j++) {
                clickDetermine(531,448,834,500);
                Thread.sleep(1000);
            }
        }
        for (int i = 0; i < 3; i++) {
            closeWindow();
        }
        LtLog.i(publicFunction.getLineInfo() + "------wait goto main City->" );
        matTime.resetTime();
        for (int i = 0; i <300 ; i++) {
            sleepTime = matTime.mMatTime(1119, 54, 71, 15, 0.8);
            if(sleepTime>=30){
                break;
            }
            result = publicFunction.localFindPic(1024,3,1274,71, "mianCity.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "------mianCity->" + result);
                break;
            }
            Thread.sleep(1000);
        }
    }

    public void automaticCombat(int Combat_state) throws Exception {
        //自动战斗 0 取消 1开启
        PublicFunction publicFunction = new PublicFunction(mFairy);
        AtFairy2.OpencvResult result, result1;
        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPic(1087,346,1180,436, "automaticCombat1.png");
//            LtLog.i(publicFunction.getLineInfo() + "-------------------------automaticCombat1--->" + result );
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------------------------automaticCombat1--->" + result );
                if (Combat_state == 0) {
                    return;
                } else {
                    publicFunction.rndTap(1119,372,1151,404);
                    Thread.sleep(300);
                }
            }
            result = publicFunction.localFindPic(1087,346,1180,436, "automaticCombat1-1.png");
//            LtLog.i(publicFunction.getLineInfo() + "-------------------------automaticCombat1-1--->" + result );
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------------------------automaticCombat1-1--->" + result);
                if (Combat_state == 1) {
                    return;
                } else {
                    publicFunction.rndTap(1119,372,1151,404);
                    Thread.sleep(300);
                }
            }
            Thread.sleep(300);
        }
    }

    public void gameInitializationSet() throws Exception {
        switchDaily();
        switchMember();

    }
    //切换到日常
    public void switchDaily() throws Exception {
        AtFairy2.OpencvResult result;
        LtLog.i(publicFunction.getLineInfo() + "------switchDaily->" );
        for (int i = 0; i <5 ; i++) {
            result = publicFunction.localFindPic(890, 0, 1020, 123, "activity.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "------activity->" + result);
                break;
            }
            result = publicFunction.localFindPic(972, 0, 1093, 81, "switch.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------switch->" + result);
                publicFunction.rndTapWH(result.x,result.y,21,20);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(973, 0, 1094, 79, "switch1.png" + "|" + "switch2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------switch1->" + result);
                publicFunction.rndTapWH(result.x,result.y,21,17);
                Thread.sleep(500);
            }


            result = publicFunction.localFindPic(272, 40, 403, 173, "system.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------system->" + result);
                publicFunction.rndTapWH(result.x,result.y,31,33);
                Thread.sleep(500);
            }
            Thread.sleep(2000);
        }
    }


    public void switchMember() throws Exception {
        AtFairy2.OpencvResult result;
        LtLog.i(publicFunction.getLineInfo() + "------switchMember->" );
        for (int i = 0; i < 3; i++) {
            result = publicFunction.localFindPic(28, 235, 220, 356, "member.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------member->" + result);
                publicFunction.rndTap(35,227,74,239);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(0, 169, 82, 296, "right.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------right->" + result);
                publicFunction.rndTapWH(result.x,result.y,17,27);
                Thread.sleep(500);
            }
            Thread.sleep(1000);
        }





    }


}









