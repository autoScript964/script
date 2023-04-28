package com.script.fairy;

import com.script.opencvapi.FindResult;
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
    private AtFairy2.OpencvResult result;
    private List<String> mapName;
    private FindResult findResult;
    private List<int[]> XY;

    public GamePublicFunction(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
//        publicFunction=mFairy.publicFunction;
        initGoSecurity();
    }



    public void goLA() throws Exception {

        LtLog.i(publicFunction.getLineInfo() + "回主城临安");

        AtFairy2.OpencvResult result;

        result = publicFunction.localFindPic(1138, 0, 1280, 80, "mainCity.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "临安");
            return;
        }

        openMapWorldOrCurrent("world");

        findResult = mFairy.findPic(461, 95, 527, 683, "penglai.png");
        if (findResult.sim > 0.8f) {
            LtLog.e(publicFunction.getLineInfo() + "在宗师地图,开始切换到普通地图.");
            publicFunction.RanSwipe(372, 85, 950, 606, 2, 500);
            publicFunction.RanSwipe(372, 85, 950, 606, 2, 500);
            mFairy.onTap(224, 321, 237, 335, "", 2000);
        }

        findResult = mFairy.findPic(0, 95, 130, 283, "map.png");
        if (findResult.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->map=" + result);

            openMapWorldOrCurrent("world");

            findResult = mFairy.findPic(461, 95, 527, 683, "penglai.png");
            if (findResult.sim > 0.8f) {
                LtLog.e(publicFunction.getLineInfo() + "在宗师地图,开始切换到普通地图.");
                publicFunction.RanSwipe(372, 85, 950, 606, 2, 500);
                publicFunction.RanSwipe(372, 85, 950, 606, 2, 500);
                mFairy.onTap(224, 321, 237, 335, "", 2000);
            }

            findResult = mFairy.findPic(0, 95, 130, 283, "map.png");
            if (findResult.sim < 0.8) {
                return;
            }
        }

        for (int i = 0; i < 2; i++) {
            publicFunction.RanSwipe(372, 85, 950, 606, 2, 500);
        }

        Thread.sleep(1000);

        for (int i = 0; i < 60; i++) {
            result = publicFunction.localFindPic(0, 95, 130, 283, "map.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->map=" + result);
                publicFunction.rndTap(985, 254, 1002, 266);
                Thread.sleep(2000);
                closeWindow();
            }
            result = publicFunction.localFindPic(1138, 0, 1280, 80, "mainCity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "临安");
                return;
            }
            Thread.sleep(1000);
        }
        closeWindow();
        LtLog.i(publicFunction.getLineInfo() + "回主城 - 结束");
    }//

    public void openMapWorldOrCurrent(String str) throws Exception {
        //打开世界地图 world
        //打开当前地图 current
//        result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
//        if (result.sim < 0.8) {
//            LtLog.i(publicFunction.getLineInfo() + "------->activity=" + result);
//            return;
//        }

        LtLog.i(publicFunction.getLineInfo() + "【开始切换地图 ： " + (str.equals("world") ? "世界地图" : "当前地图") + "】");


        result = publicFunction.localFindPic(528, 427, 661, 659, "login.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "异常：进1" + result);
            return;
        }
        result = publicFunction.localFindPic(1014, 587, 1147, 719, "login1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "异常：进2" + result);
            return;
        }


        for (int i = 0; i < 4; i++) {

            result = publicFunction.localFindPic(303, 21, 471, 154, "list.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "异常 - 列表：" + result);
                publicFunction.rndTap(126, 68, 144, 91);
                Thread.sleep(500);
            }

            if (str.equals("world")) {
                LtLog.i(publicFunction.getLineInfo() + "【点开地图】");
                publicFunction.rndTap(1191, 10, 1233, 29);
                Thread.sleep(3000);

                result = publicFunction.localFindPic(0, 95, 130, 283, "map.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【世界地图界面 end!】" + result);
                    break;
                }

//                result = publicFunction.localFindPic(827, 542, 954, 669, "smallMap.png");
                result = publicFunction.localFindPic(342, 57, 933, 649, "smallMap.png" + "|" + "smallMap1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【打开世界地图】");
                    publicFunction.rndTap(372, 87, 398, 116);
                    Thread.sleep(1000);
                }


            } else if (str.equals("current")) {
                LtLog.i(publicFunction.getLineInfo() + "【点开地图】");
                publicFunction.rndTap(1194, 63, 1241, 104);
                Thread.sleep(1000);
//                result = publicFunction.localFindPic(827, 542, 954, 669, "smallMap.png");
                result = publicFunction.localFindPic(342, 57, 933, 649, "smallMap.png" + "|" + "smallMap1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【当前地图界面 end!】" + result);
                    break;
                }
            }
            Thread.sleep(500);
        }


        result = publicFunction.localFindPic(303, 21, 471, 154, "list.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "异常 - 列表：" + result);
            publicFunction.rndTap(126, 68, 144, 91);
            Thread.sleep(500);
        }

        LtLog.i(publicFunction.getLineInfo() + "【开始切换地图 ： " + (str.equals("world") ? "世界地图" : "当前地图") + " - 结束】");
    }

    public void closeWindow() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "【关叉】");

        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPic(770, 2, 1278, 454, "fork.png" + "|" + "fork1.png" + "|" + "fork2.png" + "|" + "fork3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------------------closeWindow---->fork=" + result);
                publicFunction.rndTapWH(result.x, result.y, 10, 10);
                Thread.sleep(2000);
            }
            Thread.sleep(300);
        }
        LtLog.i(publicFunction.getLineInfo() + "【关叉 - 结束】");
    }

    public AtFairy2.OpencvResult close() throws Exception {
        for (int i = 0; i < 6; i++) {
            result = publicFunction.localFindPic(770, 2, 1278, 454, "fork.png|fork1.png" + "|" + "fork2.png" + "|" + "fork3.png");
            if (result.sim >= 0.8) {
                return result;
            }
            Thread.sleep(300);
        }
        return result;
    }

    //automatic自动 ,,manual手动
    public void manualOrAutomatic(String str) throws Exception {

        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPic(1038, 251, 1160, 374, "manual.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->manual=" + result);
                if (str.equals("manual")) {
                    return;
                }
                publicFunction.rndTap(1099, 300, 1128, 325);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(1039, 252, 1160, 375, "automatic.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->automatic=" + result);
                if (str.equals("automatic")) {
                    return;
                }
                publicFunction.rndTap(1099, 300, 1128, 325);
                Thread.sleep(1000);
            }
            Thread.sleep(500);
        }

    }

    public void taskOrTeam(String str) throws Exception {

        LtLog.i(publicFunction.getLineInfo() + "【开始切换到：" + (str.equals("task") ? "任务栏" : "队伍栏") + "】");

        result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "【没有在主场景】");
            return;
        }

        AtFairy2.OpencvResult resultTask, resultTeam;
        for (int i = 0; i < 10; i++) {
            resultTeam = publicFunction.localFindPicHLS(18, 130, 293, 235, "team.png");
            LtLog.i(publicFunction.getLineInfo() + "------->team=" + resultTeam);

            resultTask = publicFunction.localFindPicHLS(18, 130, 293, 235, "task.png");
            LtLog.i(publicFunction.getLineInfo() + "------->task=" + resultTask);

            if (str.equals("task") && resultTask.sim > resultTeam.sim) {
                LtLog.i(publicFunction.getLineInfo() + "【任务栏】");
                return;
            }
            if (str.equals("team") && resultTeam.sim > resultTask.sim) {
                LtLog.i(publicFunction.getLineInfo() + "【队伍栏】");
                return;
            }

            if (i % 2 == 0) {
                resultTask = publicFunction.localFindPicHLS(18, 130, 293, 235, "task.png");
                if (resultTask.sim >= 0.73) {
                    LtLog.i(publicFunction.getLineInfo() + "【点击队伍栏】");
                    publicFunction.rndTap(183, 167, 227, 189);
                    Thread.sleep(1500);
                }
            } else {
                resultTeam = publicFunction.localFindPicHLS(18, 130, 293, 235, "team.png");
                if (resultTeam.sim >= 0.75) {
                    LtLog.i(publicFunction.getLineInfo() + "【点击任务栏】");
                    publicFunction.rndTap(72, 166, 115, 189);
                    Thread.sleep(1500);
                }
            }
            closeWindow();
            Thread.sleep(1000);
        }

        closeWindow();

    }

    public void createTeam() throws Exception {

        LtLog.i(publicFunction.getLineInfo() + "【开始创建队伍】");

        result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->activity=" + result);
            return;
        }

        taskOrTeam("team");

        result = publicFunction.localFindPic(0, 156, 116, 274, "flag.png" + "|" + "flag2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->flag=" + result);
            return;
        }

        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(36, 174, 188, 297, "createTeam.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【创建队伍】");
                publicFunction.rndTapWH(result.x, result.y, 52, 23);
                Thread.sleep(500);
            }
            if (clickDetermine()) {
                return;
            }
            Thread.sleep(300);
        }
    }

    public void exitTeam() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "【开始执行退队】");
        for (int i = 0; i < 5; i++) {
            taskOrTeam("team");
            result = publicFunction.localFindPicHLS(18, 130, 293, 235, "team.png");
            if (result.sim >= 0.85) {
                LtLog.i(publicFunction.getLineInfo() + "【队伍】" + result);
                publicFunction.rndTapWH(result.x, result.y, 98, 24);
                Thread.sleep(2000);
            }

            result = publicFunction.localFindPic(356, 551, 488, 681, "exit.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【退队】");
                publicFunction.rndTapWH(result.x, result.y, 32, 30);
                Thread.sleep(500);
                closeWindow();
            }
            result = publicFunction.localFindPic(36, 174, 188, 297, "createTeam.png");
            if (result.sim >= 0.8) {
                closeWindow();
                break;
            }
            result = publicFunction.localFindPic(967, 553, 1183, 678, "createTeam1.png");
            if (result.sim >= 0.8) {
                closeWindow();
                break;
            }

            Thread.sleep(500);
        }
        LtLog.i(publicFunction.getLineInfo() + "【开始执行退队 - 结束】");
    }

    public void openTeam() throws Exception {
        //打开组队界面
        result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->activity=" + result);
            return;
        }
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPicHLS(18, 130, 293, 235, "team.png");
            LtLog.i(publicFunction.getLineInfo() + "------->team=" + result);
            if (result.sim >= 0.9) {
                LtLog.i(publicFunction.getLineInfo() + "------->team=" + result);
                publicFunction.rndTap(183, 167, 227, 189);
                Thread.sleep(500);
            } else {
                taskOrTeam("team");
            }
            result = publicFunction.localFindPic(0, 76, 116, 222, "teamCopy1.png");
            if (result.sim >= 0.8) {
                Thread.sleep(500);
                return;

            }
            Thread.sleep(300);
        }

    }

    public boolean clickDetermine() throws Exception {
        //点击确认
        boolean state = false;
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(557, 388, 899, 660, "determine.png" + "|" + "determine1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【确定】" + result);
                publicFunction.rndTapWH(result.x, result.y, 28, 25);
                Thread.sleep(500);
                state = true;
                return state;
            }
            result = publicFunction.localFindPic(1035, 605, 1275, 716, "determine.png|determine1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【确定】" + result);
                publicFunction.rndTapWH(result.x, result.y, 28, 25);
                Thread.sleep(500);
                state = true;
                return state;
            }
            Thread.sleep(100);
        }
        return state;
    }

    public boolean clickDetermine(int x_1, int y_1, int x_2, int y_2) throws Exception {
        //点击确认
        boolean state = false;
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(x_1, y_1, x_2, y_2, "determine.png" + "|" + "determine1.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "确定" + result);
                publicFunction.rndTapWH(result.x, result.y, 28, 25);
                Thread.sleep(500);
                state = true;
                break;
            }
            Thread.sleep(100);
        }
        return state;
    }

    //以下地图没有安全区3个字作为识别，所以用小地图上方的地图名作为识别，然后点击固定的安全区位置。
    private void initGoSecurity() {

        mapName = new ArrayList<>();
        mapName.add("CHTC.png");//1
        mapName.add("LMKZ.png");///2
        mapName.add("WSXL.png");//3
        mapName.add("PQDY.png");//4
        mapName.add("JYG.png");//5
        mapName.add("CMSD.png");//6
        mapName.add("ZZDK.png");//7
        mapName.add("LLGG.png");//8
        mapName.add("SBXY.png");//9
        mapName.add("PLZZ.png");//10
        mapName.add("YZZZ.png");//11
        mapName.add("FHZZ.png");//12
        mapName.add("YQZZ.png");//13
        mapName.add("XKD.png");//14
        mapName.add("TY.png");//15
        mapName.add("YY.png");//16
        mapName.add("CZ.png");//17
        mapName.add("LZ.png");//18
        mapName.add("JKZ.png");//19
        mapName.add("CZZS.png");//20
        XY = new ArrayList<>();
        XY.add(new int[]{649, 364});//1
        XY.add(new int[]{644, 397});//2
        XY.add(new int[]{617, 366});//3
        XY.add(new int[]{643, 338});//4
        XY.add(new int[]{565, 260});//5
        XY.add(new int[]{644, 335});//6
        XY.add(new int[]{626, 340});//7
        XY.add(new int[]{652, 353});//8
        XY.add(new int[]{631, 358});//9
        XY.add(new int[]{653, 334});//10
        XY.add(new int[]{667, 343});//11
        XY.add(new int[]{659, 355});//12
        XY.add(new int[]{637, 420});//13
        XY.add(new int[]{634, 365});//14
        XY.add(new int[]{640, 331});//15
        XY.add(new int[]{644, 349});//16
        XY.add(new int[]{639, 378});//17
        XY.add(new int[]{584, 366});//18
        XY.add(new int[]{632, 397});//19
        XY.add(new int[]{636, 364});//20
    }

    //回安全区
    public void goSecurity() throws Exception {


        for (int w = 0; w < 10; w++) {

            closeWindow();

            LtLog.i(publicFunction.getLineInfo() + "【开始回安全区】");

            result = publicFunction.localFindPic(1138, 0, 1280, 80, "mainCity1.png" + "|" + "mainCity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【在安全区】" + result);
                return;
            }

            openMapWorldOrCurrent("current");  //打开世界地图 world , 打开当前地图 current

            result = publicFunction.localFindPic(342, 57, 933, 649, "smallMap.png" + "|" + "smallMap1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【当前地图界面】");
            } else {
                continue;
            }


            boolean bool = false;

            for (int i = 0; i < 4; i++) {

                result = publicFunction.localFindPic(475, 216, 796, 531, "safetyZone1.png");
                if (result.sim >= 0.75) {
                    LtLog.i(publicFunction.getLineInfo() + "【点击安全区】");
                    mFairy.tap(result.x + 25, result.y + 10);
                    Thread.sleep(500);
                    bool = true;
                }

                result = publicFunction.localFindPic(475, 216, 796, 531, "safetyZone.png");
                if (result.sim >= 0.75) {
                    LtLog.i(publicFunction.getLineInfo() + "【点击安全区】");
                    mFairy.tap(result.x + 5, result.y + 5);
                    Thread.sleep(500);
                    bool = true;
                } else {
                    //没有安全区标识
                    for (int j = 0; j < mapName.size(); j++) {

                        result = publicFunction.localFindPic(518, 29, 706, 149, mapName.get(j));
                        LtLog.i(publicFunction.getLineInfo() + "------->" + mapName.get(j) + "=" + result);
                        if (result.sim >= 0.9) {
                            LtLog.i(publicFunction.getLineInfo() + "【没有识别出安全区 - 点击已设置好的各场景坐标】");
                            mFairy.tap(XY.get(j)[0], XY.get(j)[1]);
                            Thread.sleep(500);
                            bool = true;
                            break;
                        }
                    }
                }
                Thread.sleep(500);
            }

            if (bool) {
                break;
            }

        }


        closeWindow();

        for (int i = 0; i < 10; i++) {
            boolean state = publicFunction.judgeMatChange(1177, 136, 70, 21, 0.95, 1000);
            LtLog.i(publicFunction.getLineInfo() + "坐标状态：" + state);
            if (!state) {
                LtLog.i(publicFunction.getLineInfo() + "【判断坐标没有变动 - 退出 】");
                //判断坐标没有变动 退出
                break;
            }
            Thread.sleep(1000);
        }
    }

    /**
     * 回到主城
     *
     * @throws Exception
     */
    public void goMainCity() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "【回主城 (临安或者襄阳) - 结束】");

        result = publicFunction.localFindPic(1138, 0, 1280, 80, "mainCity1.png" + "|" + "mainCity.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->mainCity1=" + result);
            return;
        }

        openMapWorldOrCurrent("world");


        findResult = mFairy.findPic(461, 95, 527, 683, "penglai.png");
        if (findResult.sim > 0.8f) {
            LtLog.e(publicFunction.getLineInfo() + "在宗师地图,开始切换到普通地图.");
            publicFunction.RanSwipe(372, 85, 950, 606, 2, 500);
            publicFunction.RanSwipe(372, 85, 950, 606, 2, 500);
            mFairy.onTap(224, 321, 237, 335, "", 2000);
        }


        findResult = mFairy.findPic(0, 95, 130, 283, "map.png");
        if (findResult.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->map=" + result);

            openMapWorldOrCurrent("world");

            findResult = mFairy.findPic(461, 95, 527, 683, "penglai.png");
            if (findResult.sim > 0.8f) {
                LtLog.e(publicFunction.getLineInfo() + "在宗师地图,开始切换到普通地图.");
                publicFunction.RanSwipe(372, 85, 950, 606, 2, 500);
                publicFunction.RanSwipe(372, 85, 950, 606, 2, 500);
                mFairy.onTap(224, 321, 237, 335, "", 2000);
            }

            findResult = mFairy.findPic(0, 95, 130, 283, "map.png");
            if (findResult.sim < 0.8) {
                return;
            }
        }

        for (int i = 0; i < 2; i++) {
            publicFunction.RanSwipe(372, 85, 950, 606, 2, 500);
        }

        Thread.sleep(1000);

        for (int i = 0; i < 60; i++) {
            result = publicFunction.localFindPic(0, 95, 130, 283, "map.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->map=" + result);
                publicFunction.rndTap(713, 160, 724, 169);
                Thread.sleep(2000);
                closeWindow();
            }
            result = publicFunction.localFindPic(1138, 0, 1280, 80, "mainCity1.png" + "|" + "mainCity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->mainCity1=" + result);
                return;
            }
            Thread.sleep(1000);
        }
        closeWindow();
        LtLog.i(publicFunction.getLineInfo() + "【回主城 - 结束】");
    }

    public void openActivity() throws Exception {
        //打开活动
        result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "【活动】");
            publicFunction.rndTapWH(result.x, result.y, 26, 17);
            Thread.sleep(500);
        } else {
            return;
        }

        int err = 0;

        for (int i = 0; i < 30; i++) {
            result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
            LtLog.i(publicFunction.getLineInfo() + "要打开活动,相似度为：" + result.sim);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【活动】");
                publicFunction.rndTapWH(result.x, result.y, 26, 17);
                Thread.sleep(2000);
            } else {
                findResult = mFairy.findPic(520, 17, 1134, 88, "jiayuan.png");
                mFairy.onTap(0.8f, findResult, 1106, 34, 1120, 43, "在家园,切换活动", 3000);
            }

            result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
            if (result.sim >= 0.8) {
                err = 0;
                LtLog.i(publicFunction.getLineInfo() + "【活动界面】");
                result = publicFunction.localFindPic(1212, 358, 1272, 417, "redDot.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【领取活跃奖励】");
                    rewardReceive();//领取活跃奖励
                }

                result = publicFunction.localFindPic(1156, 84, 1280, 241, "everyday1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【日常】");
                    publicFunction.rndTapWH(result.x, result.y, 24, 57);
                    Thread.sleep(500);
                }
                result = publicFunction.localFindPic(1157, 85, 1277, 240, "everyday.png");
                if (result.sim >= 0.8) {
                    return;
                }
            } else {

                /*err++;
                if(err>5) {
                    err=0;
                    closeWindow();
                }*/

            }

            Thread.sleep(500);
        }
    }

    private void rewardReceive() throws Exception {
        //领取活跃奖励

        LtLog.i(publicFunction.getLineInfo() + "【开始执行领活跃任务】");
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(1212, 358, 1272, 417, "redDot.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->redDot=" + result);
                mFairy.tap(result.x, result.y);
                Thread.sleep(1000);
            } else {
                result = publicFunction.localFindPic(1156, 84, 1280, 241, "everyday1.png");
                if (result.sim >= 0.8) {
                    publicFunction.rndTapWH(result.x, result.y, 24, 57);
                    Thread.sleep(500);
                }
                result = publicFunction.localFindPic(1157, 85, 1277, 240, "everyday.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------->everyday=" + result);
                    break;
                }
            }

            result = publicFunction.localFindPic(239, 59, 1130, 204, "receive.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "------->receive=" + result);
                publicFunction.rndTapWH(result.x, result.y + 49, 40, 17);
                Thread.sleep(1000);
            }
            Thread.sleep(500);
        }

        LtLog.i(publicFunction.getLineInfo() + "【开始执行领活跃任务 - 结束】");
    }

    public void switchSkillOrTong(String str) throws Exception {

        LtLog.i(publicFunction.getLineInfo() + "【切换右侧栏为：" + (str.equals("tong") ? "家族" : "技能") + "】");
        //切换家族 或者技能
        for (int i = 0; i < 5; i++) {
            result = publicFunction.localFindPic(900, 618, 1018, 720, "tong.png" + "|" + "tong2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->tong=" + result);
                if (str.equals("tong")) {
                    return;
                }
                publicFunction.rndTap(1225, 298, 1245, 320);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(887, 539, 1013, 665, "jump.png" + "|" + "jump1.png");
            if (result.sim >= 0.8) {
                if (str.equals("skill")) {
                    return;
                }
                publicFunction.rndTap(1225, 298, 1245, 320);
                Thread.sleep(500);
            }


            Thread.sleep(500);
        }
        LtLog.i(publicFunction.getLineInfo() + "【切换右侧栏为：" + (str.equals("tong") ? "家族" : "技能") + " - 结束】");
    }

    public AtFairy2.OpencvResult leave() {
        return publicFunction.localFindPic(814, 1, 1279, 309, "leave.png" + "|" + "leave-1.png" + "|" + "leave-3.png" + "|" + "leave-5.png" + "|" + "leave-6.png");
    }

    public void setSkill() throws Exception {

        LtLog.i(publicFunction.getLineInfo() + "【设置自动技能】");

        switchSkillOrTong("skill");//切换到技能栏

        manualOrAutomatic("manual");//切换到手动

        result = publicFunction.localFindPic(1038, 251, 1160, 374, "manual.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "【点击自动】");
            mFairy.touchDown(result.x, result.y);
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                result = publicFunction.localFindPic(273, 32, 429, 94, "set.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【托管界面】");
                    break;
                }
            }
            mFairy.touchUp();
        } else {
            return;
        }

        for (int i = 0; i <= 4; i++) {
            result = publicFunction.localFindPic(315 + (169 * i), 295, 394 + (169 * i), 367, "tick.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "skill" + result);
            } else {
                publicFunction.rndTap(345 + (169 * i), 319, 352 + (169 * i), 332);
                Thread.sleep(500);
            }
        }

        result = publicFunction.localFindPic(582, 561, 654, 628, "tick.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "skill" + result);
            publicFunction.rndTapWH(result.x, result.y, 5, 5);
            Thread.sleep(1000);
        }

        LtLog.i(publicFunction.getLineInfo() + "【设置自动技能 - 结束】");

        closeWindow();
    }

    public void companion() throws Exception {
        AtFairy2.OpencvResult result1;
        switchSkillOrTong("tong");
        result = publicFunction.localFindPic(1082, 616, 1206, 720, "companion.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->companion=" + result);
            publicFunction.rndTapWH(result.x, result.y, 24, 26);
            Thread.sleep(2000);
        }
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(1204, 202, 1265, 253, "redDot.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->redDot=" + result);
                publicFunction.rndTapWH(result.x, result.y, 5, 5);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(494, 490, 607, 553, "redDot.png");
            result1 = publicFunction.localFindPic(993, 483, 1088, 555, "redDot.png");
            LtLog.i(publicFunction.getLineInfo() + "------->redDot ---result1=" + result1 + ",result=" + result);
            if (result1.sim >= 0.8 || result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->redDot ---result1=" + result1 + ",result=" + result);
                if (result1.sim >= 0.8) {
                    mFairy.tap(result1.x, result1.y);
                } else {
                    mFairy.tap(result.x, result.y);
                }
                Thread.sleep(1000);
            } else {
                break;
            }
            for (int j = 0; j < 10; j++) {
                result = publicFunction.localFindPic(547, 521, 673, 646, "complete.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------->complete=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 26, 25);
                    Thread.sleep(500);
                }
                result = publicFunction.localFindPic(547, 521, 673, 646, "companion2.png");
                LtLog.i(publicFunction.getLineInfo() + "------->companion2=" + result);
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------->companion2=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 26, 25);
                    Thread.sleep(500);
                }
                clickDetermine();
                clickDetermine(1078, 643, 1268, 710);
                LtLog.i(publicFunction.getLineInfo() + "------->clickDetermine=");
                Thread.sleep(1000);
                result = publicFunction.localFindPic(0, 30, 120, 173, "companion4.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------->companion4=" + result);
                    break;
                }
            }
            Thread.sleep(1000);
        }
        LtLog.i(publicFunction.getLineInfo() + "-------companion>closeWindow");
        closeWindow();
    }

    public void chest() throws Exception {
        //家族宝箱
        switchSkillOrTong("tong");
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(900, 618, 1018, 720, "tong.png" + "|" + "tong2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->tong=" + result);
                publicFunction.rndTapWH(result.x, result.y, 26, 25);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(0, 31, 108, 165, "tong1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------------------------tong1--->=" + result);
                publicFunction.rndTap(1204, 423, 1230, 466);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(892, 387, 1029, 506, "gold.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------------------------gold--->=" + result);
                publicFunction.rndTap(952, 389, 975, 421);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(0, 29, 112, 174, "gold1.png");
            if (result.sim >= 0.8) {

                LtLog.i(publicFunction.getLineInfo() + "-------------------------gold1--->=" + result);
                result = publicFunction.localFindPicHLS(797, 282, 939, 404, "04.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "-------------------------04--->=" + result);
                    TaskMain.taskMap.put("chest", 0);
                } else {
                    LtLog.i(publicFunction.getLineInfo() + "-------------------------04--->=" + result);
                    publicFunction.rndTap(1045, 405, 1108, 435);
                    Thread.sleep(1000);
                    closeWindow();

                    break;
                }
            }
        }
        closeWindow();

    }

    public void assist() throws Exception {

        findResult = mFairy.findPic("suo.png");
        mFairy.onTap(0.9f, findResult, "suo", 1000);

        result = publicFunction.localFindPic(459, 550, 608, 710, "assist.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------------------------assist--->=" + result);
            publicFunction.rndTapWH(result.x + 10, result.y + 5, 10, 1);
            Thread.sleep(500);
        } else {
            return;
        }

        result = publicFunction.localFindPic(329, 134, 514, 206, "item.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------------------------item--->=" + result);
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i <= 2; i++) {
                    result = publicFunction.localFindPic(874, 189 + (i * 96), 1034, 290 + (i * 96), "up-1.png");
                    LtLog.i(publicFunction.getLineInfo() + "-------------------------up--->=" + result);
                    if (result.sim >= 0.8) {

                        LtLog.i(publicFunction.getLineInfo() + "-------------------------up--->=" + result);
                        int colorNum;
                        colorNum = publicFunction.getColorNumber(result.x - 325, result.y, result.x - 267, result.y + 32, "73,254,73", 0.9);

                        LtLog.i(publicFunction.getLineInfo() + "-------------------------colorNum--->=" + colorNum);
                        if (colorNum >= 100) {
                            mFairy.tap(result.x, result.y);
                            Thread.sleep(1000);
                        }
                    }
                }
            }
        }
        closeWindow();
    }

    public List<String> lookupTask(List<String> list) throws Exception {

        LtLog.i(publicFunction.getLineInfo() + "【开始查找活动】");

        long time = System.currentTimeMillis() / 1000, timex = 0;
        int second = 0;
        result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "【活动界面】");
        } else {
            return list;
        }

        while (mFairy.condit()) {

            //瓦舍小游戏
            if (list.get(0).equals("wsxyx")) {
                closeWindow();
                return list;
            }


            AtFairy2.OpencvResult result1 = publicFunction.localFindPic(226, 87, 722, 414, list.get(0) + ".png");
            LtLog.i(publicFunction.getLineInfo() + "【活动 sim: " + list.get(0) + " 】");
            if (result1.sim >= 0.8) {

                LtLog.i(publicFunction.getLineInfo() + "【找到活动】");

                result = publicFunction.localFindPic(result1.x - 10, result1.y + 72, result1.x + 111, result1.y + 129, "attend1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【参加】");
                    publicFunction.rndTapWH(result.x, result.y, 21, 21);
                    Thread.sleep(3500);
                    return list;
                }
                result = publicFunction.localFindPic(result1.x - 10, result1.y + 72, result1.x + 111, result1.y + 129, "complete1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【已完成,移除当前任务】");
                    list.remove(0);
                    LtLog.i(publicFunction.getLineInfo() + "【任务list: " + list + "】");
                    if (list.size() == 0) {
                        return list;
                    }
                    time = System.currentTimeMillis() / 1000;
                    continue;
                }
            }


            findResult = mFairy.findPic("sz.png");
            mFairy.onTap(0.8f, findResult, 497, 441, 531, 457, "取消 - 活动界面弹框", 1000);


            if (second >= 4) {
                LtLog.i(publicFunction.getLineInfo() + "---------从上往下滑动=" + result);
                publicFunction.RanSwipe(233, 108, 719, 393, 0, 500);
            } else {
                LtLog.i(publicFunction.getLineInfo() + "---------从下往上滑动=" + result);
                publicFunction.RanSwipe(233, 108, 719, 393, 2, 500);
            }
            second = second + 1;
            if (second > 8) {
                second = 0;
            }

            Thread.sleep(2000);

            result = publicFunction.localFindPic(0, 28, 113, 179, "activity1.png");
            if (result.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【不在活动界面】");
                return list;
            }

            timex = System.currentTimeMillis() / 1000 - time;

            if (timex >= 120) {
                LtLog.i(publicFunction.getLineInfo() + "【查找超时】");
                list.remove(0);
                time = System.currentTimeMillis() / 1000;
                if (list.size() == 0) {
                    return list;
                }
                continue;
            }

            if (list.size() == 0) {
                LtLog.i(publicFunction.getLineInfo() + "【任务list为空】");
                return list;
            }
            Thread.sleep(100);
        }
        return list;
    }

    public void recall(boolean state) throws Exception {
        //召回队员
        // 召回 true
        //取消召回 false

        taskOrTeam("team");

        LtLog.i(mFairy.getLineInfo("【开始召回队员】"));

        result = publicFunction.localFindPic(0, 156, 116, 274, "flag.png" + "|" + "flag2.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "flag");
            publicFunction.rndTapWH(result.x, result.y, 21, 18);
            Thread.sleep(1500);
        } else {
            return;
        }

        result = publicFunction.localFindPic(264, 142, 469, 267, "teamRecall.png");
        if (result.sim >= 0.8 && state) {
            LtLog.i(publicFunction.getLineInfo() + "队伍召回");
            publicFunction.rndTapWH(result.x, result.y, 105, 25);
            Thread.sleep(500);
            return;
        }
        result = publicFunction.localFindPic(263, 203, 469, 327, "teamRecallCancel.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "取消召回");
            publicFunction.rndTapWH(result.x, result.y, 106, 24);
            Thread.sleep(2000);
            clickDetermine();
        }
        Thread.sleep(100);

        LtLog.i(mFairy.getLineInfo("【开始召回队员 - 结束】"));

    }

    public void openPackage() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "------->openPackage=");
        for (int i = 0; i < 3; i++) {
            result = publicFunction.localFindPic(914, 0, 1040, 100, "activity.png" + "|" + "activity2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->activity=" + result);
                mFairy.tap(result.x + 93, result.y + 10);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(0, 0, 133, 129, "package.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------->activity=" + result);
                return;
            }
            Thread.sleep(200);
        }
    }

    //判断是否在定时下线的时间段
    public boolean determineResetTime() {
        boolean start = false;
        int time = publicFunction.getMinuteNumber();
        LtLog.i(publicFunction.getLineInfo() + "------->time=" + time);
        if ((time >= 690 && time <= 700) || (time >= 1050 && time <= 1060) || (time >= 1410 && time <= 1420)) {
            start = true;
        }
        return start;
    }

    public void gameSleep() throws Exception {
        int mTime = publicFunction.getMinuteNumber();
        int mTime1 = 0;
        while (mFairy.condit()) {
            mTime1 = publicFunction.getMinuteNumber() - mTime;
            if (mTime1 >= 15) {
                break;
            }
            LtLog.i(publicFunction.getLineInfo() + "------->time=" + mTime1);
            Thread.sleep(3000);
        }
    }

}









