

package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SingleTask {

    public GamePublicFuntion gamePublicFuntion;
    public AtFairyImpl mFairy;
    private String activityName = "";
    private int activityType = 1;
    FindResult result;


    public SingleTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }


    public void setUp() throws Exception {
        new TaskContent(mFairy, "设置") {

            int num;

            void init() throws Exception {

                num = 0;

                gamePublicFuntion.close(1);

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }


            }

            void content_01() throws Exception {


                result = mFairy.findPic("zoom.png");
                mFairy.onTap(0.8f, result, "扩展栏", 1000);

                result = mFairy.findPic("set1.png");
                mFairy.onTap(0.8f, result, "设置", 1000);

                result = mFairy.findPic("set2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    switch (num) {
                        case 0:
                            mFairy.onTap(70, 107, 103, 118, "基础", 1000);

                            result = mFairy.findPic(347, 71, 581, 513, "set3.png");
                            if (result.sim > 0.8f && AtFairyConfig.getOption("8877").equals("1") && oneSecond()) {

                                //427,409  846,450,949,528 //900,488

                                //白
                                result = mFairy.findPic(result.x + 419, result.y + 30, result.x + 522, result.y + 120, "bai.png");
                                if (result.sim > 0.8f) {

                                    if (!AtFairyConfig.getOption("autoZb1").equals("1")) {
                                        mFairy.tap(result.x + 473, result.y + 79);
                                        Thread.sleep(300);
                                    }

                                } else {
                                    if (AtFairyConfig.getOption("autoZb1").equals("1")) {
                                        mFairy.tap(result.x + 473, result.y + 79);
                                        Thread.sleep(300);
                                    }
                                }


                                //蓝  //427,409  1014,442,1115,525  1069,487
                                result = mFairy.findPic(result.x + 587, result.y + 30, result.x + 688, result.y + 120, "lan.png");
                                if (result.sim > 0.8f) {

                                    if (!AtFairyConfig.getOption("autoZb2").equals("1")) {
                                        mFairy.tap(result.x + 642, result.y + 79);
                                        Thread.sleep(300);
                                    }

                                } else {
                                    if (AtFairyConfig.getOption("autoZb2").equals("1")) {
                                        mFairy.tap(result.x + 642, result.y + 79);
                                        Thread.sleep(300);
                                    }
                                }


                                //金  //427,409  1195,448,1273,525  1236,487
                                result = mFairy.findPic(result.x + 768, result.y + 30, result.x + 843, result.y + 120, "jin.png");
                                if (result.sim > 0.8f) {

                                    if (!AtFairyConfig.getOption("autoZb3").equals("1")) {
                                        mFairy.tap(result.x + 809, result.y + 79);
                                        Thread.sleep(300);
                                    }

                                } else {
                                    if (AtFairyConfig.getOption("autoZb3").equals("1")) {
                                        mFairy.tap(result.x + 809, result.y + 79);
                                        Thread.sleep(300);
                                    }
                                }

                            }

                            // 425,243 1037,280,1272,360
                            result = mFairy.findPic(347, 71, 581, 513, "set5.png");
                            if (result.sim > 0.8f) {


                                result = mFairy.findPic(result.x + 612, result.y + 37, result.x + 845, result.y + 117, "set4.png");
                                mFairy.onTap(0.8f, result, "自动寻路", 1000);

                                num = 1;
                                return;
                            }


                            mFairy.ranSwipe(730, 400, 730, 270, 500, 1000);

                            break;
                        case 1:
                            mFairy.onTap(74, 338, 104, 352, "画质", 1000);

                            result = mFairy.findPic(353, 59, 576, 393, "set6.png");
                            if (result.sim > 0.8f) {

                                mFairy.onTap(996, 170, 1017, 181, "", 500);
                                mFairy.onTap(827, 351, 849, 361, "", 500);

                                setTaskEnd();
                                return;

                            }
                            break;
                    }

                }


                timeCount(10, 0);
            }
        };


    }


    List<Integer> skill_list;

    public static boolean SWITCH_control = false;
    class Battle extends Thread {

        final int MAXXUE = 1800;

        public void run() {

            while (System.currentTimeMillis() - Abnormal.thTime < 10000) {

                if (SWITCH_control) {

                    try {
                        long wx = mFairy.getColorNum(13, 92, 133, 107, "33,19,11", 0.95f);
                        long xue = mFairy.getColorNum(13, 92, 133, 107, "0,0,0", 0.9f);

                        if (wx > 500) {
                            LtLog.e(mFairy.getLineInfo("生命条爆红"));
                            mFairy.onTap(1170, 322, 1183, 336, "生命瓶", 2000);
                            continue;
                        }

                        boolean bool = false;
                        switch (AtFairyConfig.getOption("bv")) {
                            case "1":
                                //400
                                if ((MAXXUE - xue) / MAXXUE < 0.8) {
                                    bool = true;
                                }

                                break;
                            case "2":
                                if ((MAXXUE - xue) / MAXXUE < 0.6) {
                                    bool = true;
                                }

                                break;
                            case "3":
                                if ((MAXXUE - xue) / MAXXUE < 0.4) {
                                    bool = true;
                                }

                                break;
                        }

                        if (bool) {
                            mFairy.onTap(1170, 322, 1183, 336, "生命瓶", 2000);
                        }


                    } catch (Exception e) {


                    }
                }
            }

        }
    }

    public void guaji() throws Exception {
        new TaskContent(mFairy, "挂机") {

            Skill_Click skill_click;

            void create() throws Exception {
                super.create();

                skill_list = new ArrayList<>();

                for (int i = 1; i <= 4; i++) {
                    if (AtFairyConfig.getOption("skill" + i).equals("1")) {
                        skill_list.add(i);
                    }
                }

                if (AtFairyConfig.getOption("skillnb").equals("1")) {
                    skill_list.add(5);
                }

                SWITCH_control=false;

                new Battle().start();

            }


            void init() throws Exception {
                SWITCH_control = false;

                gamePublicFuntion.close(1);

                result = mFairy.findPic("email.png");
                mFairy.onTap(0.8f,result,607,364,631,398,"活动窗口",1000);

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }
            }


            boolean matchingUserMap() throws Exception {

                String strMap = AtFairyConfig.getOption("map");

                if (strMap.equals("") || strMap.equals("99")) {
                    LtLog.e(mFairy.getLineInfo("没有勾选地图或者勾选当前地图"));
                    return true;
                }

                result = mFairy.findPic("m" + strMap + ".png");
                if (result.sim > 0.8f) {
                    return true;

                }

                return false;
            }


            class Skill_Click extends Thread{

                public void run() {
                    try {

                        for (int j = 0; j < 10; j++) {
                            Thread.sleep(150);

                            long color = mFairy.getColorNum(539,43,568,54,"142,32,10",0.95f );

                            //result = mFairy.findPic(691, 22, 831, 73, new String[]{"boss.png","boss1.png"});
                            if (color>50) {
                                err = 0;
                                for (int i = 0; i < skill_list.size(); i++) {

                                    switch (skill_list.get(i)) {
                                        case 1:
                                            mFairy.onTap(980, 601, 998, 616, "", 10);
                                            break;
                                        case 2:
                                            mFairy.onTap(976, 492, 996, 5176, "", 10);
                                            break;
                                        case 3:
                                            mFairy.onTap(1056, 420, 1072, 436, "", 10);
                                            break;
                                        case 4:
                                            mFairy.onTap(1160, 421, 1179, 439, "", 10);
                                            break;
                                        case 5:
                                            mFairy.onTap(877,500,897,516, "", 10);
                                            break;
                                    }

                                }

                            }


                        }


                        Thread.sleep(100);
                    }catch (Exception e){
                        LtLog.e(e.getMessage());
                    }
                }
            }

            void content_01() throws Exception {

                result = mFairy.findPic("map.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("地图界面"));
                    err = 0;

                    if(matchingUserMap()==false){
                        choiceMap();
                        return;
                    }


                    result = mFairy.findPic(15, 35, 1253, 680, "map2.png");
                    if (result.sim > 0.75f) {
                        mFairy.onTap(0.75f, result, result.x, result.y + 30, result.x + 1, result.y + 31, "点击坐标", 1500);

                        result = mFairy.findPic(15, 35, 1253, 680, "map3.png");
                        if (result.sim > 0.75f) {
                            mFairy.onTap(0.75f, result, "前往此处", 3000);
                            SWITCH_control = true;
                            frequencyInit("boss");


                            setTaskName(2);
                            return;
                        }
                    }

                    result = mFairy.findPic(15, 35, 1253, 680, "map1.png");
                    if (result.sim > 0.8f) {
                        mFairy.ranSwipe(result.x, result.y, 638, 358, 500, 1000);

                    }


                    if (frequencyMap("xun", 10)) {
                        LtLog.e(mFairy.getLineInfo("查找坐标异常!!!"));
                        LtLog.e(mFairy.getLineInfo("查找坐标异常!!!"));
                        LtLog.e(mFairy.getLineInfo("查找坐标异常!!!"));
                    }


                } else {

                    frequencyInit("xun");

                    result = mFairy.findPic("package.png");
                    mFairy.onTap(0.8f, result, 1126, 45, 1161, 62, "点击地图", 2000);
                }

                timeCount(10, 0);
            }

            void content_02() throws Exception {


                result = mFairy.findPic(430, 415, 835, 501, new String[]{"xun.png", "song.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("寻路中"));



                    frequencyInit("boss");

                }


                if (frequencyMap("boss", 2)) {

                    skill_click = new Skill_Click();

                    skill_click.start();

                    for (int i = 0; i < 20; i++) {
                        mFairy.onTap(1110, 547, 1126, 566, "", 15);
                    }

                    skill_click.join();

                    switch (new Random().nextInt(4)){

                        case 0:
                            mFairy.ranSwipe(133,580,137,570,100,10);
                            break;
                        case 1:
                            mFairy.ranSwipe(133,580,143,580,100,10);
                            break;
                        case 2:
                            mFairy.ranSwipe(133,580,133,590,100,10);
                            break;
                        case 3:
                            mFairy.ranSwipe(133,580,123,580,100,10);
                            break;

                    }




                }


                timeCount(500, 0);
            }

            void inOperation() throws Exception {

                result = mFairy.findPic("qx1.png");
                mFairy.onTap(0.8f,result,"拒绝入队",1000);

                result = mFairy.findPic(429,601,837,662,"bfb.png");
                if(result.sim>0.8f){
                    LtLog.e(mFairy.getLineInfo("地图传送中"));
                    err=0;
                }

                if (timeMap("chears", getTimeStamp(AtFairyConfig.getOption("chears")))) {

                    //清包
                    SWITCH_control = false;
                    chearPackage();
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(574, 143, 727, 277, "sb.png");
                if (result.sim > 0.8f) {

                    if (AtFairyConfig.getOption("fh").equals("1")) {

                        result = mFairy.findPic("sb1.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(316, 518, 389, 534, "城中复活", 1000);
                            setTaskName(0);
                        } else {
                            mFairy.onTap(886, 518, 961, 535, "原地复活", 1000);
                        }
                    } else {
                        mFairy.onTap(316, 518, 389, 534, "城中复活", 1000);
                        setTaskName(0);
                    }

                    return;
                }


            }
        };

    }//挂机

    public void chearPackage() throws Exception {
        new TaskContent(mFairy, "清包") {

            void init() throws Exception {


                gamePublicFuntion.close(1);
                result = mFairy.findPic("email.png");
                mFairy.onTap(0.8f,result,607,364,631,398,"活动窗口",1000);
                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }
            }

            void content_01() throws Exception {

                result = mFairy.findPic(1134,539,1251,659,"map4.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("地图界面"));
                    err = 0;
                    result = mFairy.findPic("map.png");
                    mFairy.onTap(0.8f, result, "搜索", 1500);


                    result = mFairy.findPic(375, 41, 1254, 662, "qw.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "前往", 2000);

                        setTaskName(2);

                        return;
                    }


                    result = mFairy.findPic(32, 154, 356, 626, "tj.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "铁匠", 1000);

                        return;
                    } else {

                        mFairy.ranSwipe(211, 533, 211, 265, 500, 1000);

                    }


                    if (frequencyMap("xun", 5)) {
                        LtLog.e(mFairy.getLineInfo("查找铁匠异常!!!"));
                        LtLog.e(mFairy.getLineInfo("查找铁匠异常!!!"));
                        LtLog.e(mFairy.getLineInfo("查找铁匠异常!!!"));
                        gamePublicFuntion.close(3);
                        return;
                    }


                } else {
                    result = mFairy.findPic("package.png");
                    mFairy.onTap(0.8f, result, 1126, 45, 1161, 62, "点击地图", 2000);

                    frequencyInit("xun");

                }

                timeCount(10, 0);
            }

            void content_02() throws Exception {

                result = mFairy.findPic(430, 415, 835, 501, new String[]{"xun.png", "song.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("寻路中"));

                    frequencyInit("hui");

                }


                if (frequencyMap("hui", 3)) {
                    result = mFairy.findPic(826, 291, 999, 434, "chat.png");
                    mFairy.onTap(0.8f, result, "聊天", 1500);
                }


                result = mFairy.findPic(879, 75, 1154, 503, "fen.png");
                mFairy.onTap(0.8f, result, "分解", 1500);


                result = mFairy.findPic("tie.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (AtFairyConfig.getOption("fen1").equals("1")) {
                        mFairy.onTap(522, 644, 528, 650, "白", 1000);
                    }

                    result = mFairy.findPic("quxiao.png");
                    mFairy.onTap(0.8f, result, "取消", 500);

                    if (AtFairyConfig.getOption("fen2").equals("1")) {
                        mFairy.onTap(579, 645, 586, 652, "蓝", 1000);
                    }

                    result = mFairy.findPic("quxiao.png");
                    mFairy.onTap(0.8f, result, "取消", 500);

                    if (AtFairyConfig.getOption("fen3").equals("1")) {
                        mFairy.onTap(639, 642, 643, 651, "金", 1000);
                    }

                    result = mFairy.findPic("quxiao.png");
                    mFairy.onTap(0.8f, result, "取消", 500);


                    mFairy.onTap(987, 623, 1010, 639, "分解", 4000);

                    gamePublicFuntion.close(2);
                    setTaskEnd();
                    return;
                }


                Thread.sleep(1000);

                timeCount(10, 0);
            }

            void inOperation() throws Exception {


                result = mFairy.findPic(574, 143, 727, 277, "sb.png");
                if (result.sim > 0.8f) {

                    mFairy.onTap(316, 518, 389, 534, "城中复活", 1000);
                    setTaskName(0);


                    return;
                }


            }


        };
    }

    public void choiceMap() throws Exception {
        new TaskContent(mFairy, "选择地图："+AtFairyConfig.getOption("map")) {

            void init() throws Exception {

                result = mFairy.findPic("map.png");
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }


                gamePublicFuntion.close(1);
                result = mFairy.findPic("email.png");
                mFairy.onTap(0.8f,result,607,364,631,398,"活动窗口",1000);
                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }
            }

            void content_01() throws Exception {//回主城

                result = mFairy.findPic(430, 415, 835, 501, new String[]{"xun.png", "song.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("寻路中"));

                }



                result = mFairy.findPic("map.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("地图界面"));
                    err = 0;


                    result = mFairy.findPic("m99.png");
                    if(result.sim>0.8f){


                        if(!AtFairyConfig.getOption("ms").equals("99")  || !AtFairyConfig.getOption("ms").equals("")){


                            for (int i = 0; i < 10; i++) {

                                result = mFairy.findPic("map7.png");
                                if (result.sim > 0.8f) {

                                    int ms  = Integer.parseInt(AtFairyConfig.getOption("ms"));
                                    if(ms>3){
                                        mFairy.ranSwipe(287,472,287,202,500,1000);
                                    }else{
                                        mFairy.ranSwipe(287,202,287,472,500,1000);
                                    }


                                    switch (ms){
                                        case 1:
                                            mFairy.onTap(263,216,302,234,"模式："+ms,500);
                                            break;
                                        case 2:
                                            mFairy.onTap(270,314,294,325,"模式："+ms,500);
                                            break;
                                        case 3:
                                            mFairy.onTap(268,411,296,431,"模式："+ms,500);
                                            break;
                                        case 4:
                                            mFairy.onTap(281,253,306,261,"模式："+ms,500);
                                            break;
                                        case 5:
                                            mFairy.onTap(277,352,301,361,"模式："+ms,500);
                                            break;
                                        case 6:
                                            mFairy.onTap(278,448,296,457,"模式："+ms,500);
                                            break;
                                    }

                                    mFairy.onTap(602,561,646,566,"",2000);

                                    result = mFairy.findPic(648,325,835,518,"ok1.png");
                                    mFairy.onTap(0.8f,result,"确定",1000);

                                }else {


                                    result = mFairy.findPic("map.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(1078, 171, 1090, 174, "", 300);
                                        mFairy.onTap(1168, 173, 1177, 179, "", 1000);
                                        mFairy.onTap(1078, 171, 1090, 174, "", 1500);
                                    }
                                }


                            }


                            gamePublicFuntion.close(3);
                        }else{
                            mFairy.onTap(36,53,59,74,"",1500);
                        }

                        //前往世界地图
                        setTaskName(2);
                        return;
                    }


                    result = mFairy.findPic(874,394,1127,643,"map5.png");
                    if(result.sim>0.8f) {
                        mFairy.onTap(0.8f, result, "回主城", 9000);
                        return;
                    }

                    result = mFairy.findPic(1110,539,1257,657,"map4.png");
                    mFairy.onTap(0.8f,result,"传送门",1000);

                } else {
                    result = mFairy.findPic("package.png");
                    mFairy.onTap(0.8f, result, 1126, 45, 1161, 62, "点击地图", 2000);


                }

                Thread.sleep(200);

                timeCount(10, 0);
            }


            void content_02() throws Exception {

                result = mFairy.findPic("map6.png");
                if (result.sim > 0.8f) {
                    err=0;
                    LtLog.e(mFairy.getLineInfo("世界地图"));


                    switch (AtFairyConfig.getOption("map")){

                        case "1":
                            result = mFairy.findPic(80,74,1258,661,"mt1.png");
                            break;
                        case "2":
                            result = mFairy.findPic(80,74,1258,661,"mt2.png");
                            break;
                        case "3":
                            result = mFairy.findPic(80,74,1258,661,"mt3.png");
                            break;
                        case "4":
                            mFairy.touchDown(1093,94);
                            mFairy.touchMove(309,520,500);
                            Thread.sleep(500);
                            mFairy.touchUp();
                            result = mFairy.findPic(80,74,1258,661,"mt4.png");

                            break;
                        case "5":

                            mFairy.touchDown(1093,94);
                            mFairy.touchMove(309,520,500);
                            Thread.sleep(500);
                            mFairy.touchUp();
                            result = mFairy.findPic(80,74,1258,661,"mt4.png");
                            break;
                        case "6":
                            result = mFairy.findPic(80,74,1258,661,"mt6.png");
                            break;
                        case "7":

                            mFairy.touchDown(1093,94);
                            mFairy.touchMove(309,520,500);
                            Thread.sleep(500);
                            mFairy.touchUp();
                            result = mFairy.findPic(80,74,1258,661,"mt7.png");

                            break;
                        case "8":
                            mFairy.touchDown(570,147);
                            mFairy.touchMove(570,634,500);
                            Thread.sleep(500);
                            mFairy.touchUp();
                            result = mFairy.findPic(80,74,1258,661,"mt8.png");
                            break;
                        case "9":

                            mFairy.touchDown(1216,539);
                            mFairy.touchMove(40,345,500);
                            Thread.sleep(500);
                            mFairy.touchUp();
                            result = mFairy.findPic(80,74,1258,661,"mt9.png");
                            break;
                    }


                    mFairy.onTap(0.75f,result,"发现地图",1500);


                    result = mFairy.findPic("map6.png");
                    if (result.sim > 0.8f) {
                        gamePublicFuntion.close(3);
                    }else{
                        setTaskEnd();
                        return;
                    }


                }else {

                    result = mFairy.findPic("map.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 43, 50, 60, 68, "", 1000);
                    } else {
                        result = mFairy.findPic("package.png");
                        mFairy.onTap(0.8f, result, 1126, 45, 1161, 62, "点击地图", 2000);
                    }
                }


                Thread.sleep(200);

                timeCount(10, 0);
            }

            void inOperation() throws Exception {


                result = mFairy.findPic(429,601,837,662,"bfb.png");
                if(result.sim>0.8f){
                    LtLog.e(mFairy.getLineInfo("地图传送中"));
                    err=0;
                }


                result = mFairy.findPic(574, 143, 727, 277, "sb.png");
                if (result.sim > 0.8f) {

                    mFairy.onTap(316, 518, 389, 534, "城中复活", 1000);
                    setTaskName(0);


                    return;
                }


            }


        };
    }
}