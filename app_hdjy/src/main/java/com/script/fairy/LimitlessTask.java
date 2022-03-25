package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;


/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class LimitlessTask {//无限任务

    private AtFairyImpl mFairy;
    private FindResult result;
    private SingleTask singleTask;
    private GamePublicFuntion gamePublicFuntion;
    private TeamTask teamTask;
    private int hour;
    private int minute;
    private int week;

    public LimitlessTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(mFairy);
        teamTask = new TeamTask(mFairy);
        singleTask = new SingleTask(mFairy);
    }

    public int go1, go2, go3, go4, go5, go6, go7, go8, go9, go10, go11, go12, go13 = 0;

    private int control_a, control_b, control_c, control_d = 1;
    private long linitless_time = 0;
    private long time = 0;
    private boolean start_task =false;
    public void linitless() throws Exception {
        new TaskContent(mFairy, "无限任务") {

            void create() throws Exception {
                super.create();
                linitless_time = 0;
                control_a = 0;
                control_b = 0;
                control_c = 0;
                control_d = 1;
                go1 = 0;
                go2 = 0;
                go3 = 0;
                go4 = 0;
                go5 = 0;
                go6 = 0;
                go7 = 0;
                go8 = 0;
                go9 = 0;
                go10 = 0;
                go11 = 0;
                go12 = 0;
                go13 = 0;
            }

            void init() throws Exception {
                singleTask.li_rank();
                gamePublicFuntion.init(false);
                setTaskName(1);
                gamePublicFuntion.stop_auto();
                oneSecond = 0;
                control_d = 1;
            }

            void click_map(int i, int gmx, int gmy) throws Exception {
                int x = 0;
                int y = 0;
                switch (i) {
                    case 1:
                        x = (int) (gmx * -1.3268 + gmy * -0.0093 + 980.6012);
                        y = (int) (gmx * 0.0017 + gmy * -1.3383 + 701.4322);
                        break;
                    case 2:
                        x = (int) (gmx * -1.1196 + gmy * -0.1822 + 966.8374);
                        y = (int) (gmx * 0.1613 + gmy * -1.4335 + 672.058);
                        break;
                    case 3:
                        x = (int) (383 * -1.3268 + 322 * -0.0093 + 980.6012);
                        y = (int) (383 * 0.0017 + 322 * -1.3383 + 701.4322);
                        break;
                    case 4:
                        x = (int) (gmx * -1.1009 + gmy * 0.0059 + 910.401);
                        y = (int) (gmx * 0.0026 + gmy * -1.1171 + 642.8295);
                        break;
                    case 5:
                        x = (int) (gmx * -1.3268 + gmy * -0.0093 + 980.6012);
                        y = (int) (gmx * 0.0017 + gmy * -1.3383 + 701.4322);
                        break;
                    case 6:
                        x = (int) (gmx * -1.3268 + gmy * -0.0093 + 980.6012);
                        y = (int) (gmx * 0.0017 + gmy * -1.3383 + 701.4322);
                        break;
                }
                mFairy.onTap(x, y, x + 2, y + 2, "用户输入（" + gmx + "," + gmy + "），点击的坐标（" + x + "," + y + "）", 500);
                mFairy.onTap(x, y, x + 2, y + 2, "用户输入（" + gmx + "," + gmy + "），点击的坐标（" + x + "," + y + "）", 500);
            }//点击地图

            void choice_map(String map, String x, String y) throws Exception {

                result = mFairy.findPic("map.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (oneSecond()) {
                        result = mFairy.findPic("map5.png");
                        mFairy.onTap(0.8f, result, "世界", 500);
                    }

                    result = mFairy.findPic("map1.png");
                    if (result.sim > 0.8f) {

                        switch (map) {
                            case "1":
                                mFairy.onTap(275, 422, 306, 461, "缠风峡湾", 1000);
                                break;
                            case "2":
                                mFairy.onTap(645, 590, 685, 614, "阿鲁巴岛", 1000);
                                break;
                            case "3":
                                mFairy.onTap(1034, 532, 1069, 557, "珊瑚王座", 1000);
                                break;
                            case "4":
                                mFairy.onTap(1085, 236, 1123, 277, "晶木岛", 1000);
                                break;
                            case "5":
                                mFairy.onTap(701, 213, 759, 256, "龙焰平原", 1000);
                                break;
                            case "6":
                                mFairy.onTap(404, 218, 445, 261, "圣山卡琅古", 1000);
                                break;
                            default:
                                mFairy.onTap(275, 422, 306, 461, "缠风峡湾", 1000);
                                break;
                        }

                        if (mapCount(0.8f, 560, 94, 833, 216, "map2.png")) {
                            mFairy.onTap(29, 18, 50, 37, "", 500);
                            setTaskEnd();
                            return;
                        }
                    }

                    result = mFairy.findPic("map3.png");
                    if (result.sim > 0.8f) {

                        result = mFairy.findPic(1187, 134, 1262, 585, "map4.png");
                        if (result.sim > 0.95f) {
                            mFairy.ranSwipe(result.x, result.y, 1221, 578, 1000, 1000);
                        }
                        click_map(Integer.parseInt(map), Integer.parseInt(x), Integer.parseInt(y));
                        gamePublicFuntion.close(3);
                        setTaskName(2);
                        start_task = false;
                        return;
                    }

                }

                if (gamePublicFuntion.mainScene()) {
                    mFairy.onTap(85, 78, 106, 108, "点击地图", 500);
                }


            }//选择地图

            /**
             * 找地图
             * @throws Exception
             */
            void content_01() throws Exception {
                timeCount(10, 0);

                String zb1 = AtFairyConfig.getOption("zb1");

                if (AtFairyConfig.getOption("6267").equals("1") && control_a == 0 && !zb1.equals("")) {
                    choice_map(AtFairyConfig.getOption("map1"), zb1.split(",")[0], zb1.split(",")[1]);
                    return;
                } else {
                    control_a = 1;
                }

                String zb2 = AtFairyConfig.getOption("zb2");

                if (AtFairyConfig.getOption("6273").equals("1") && control_b == 0 && !zb2.equals("")) {
                    choice_map(AtFairyConfig.getOption("map2"), zb2.split(",")[0], zb2.split(",")[1]);
                    return;
                } else {
                    control_b = 1;
                }

                String zb3 = AtFairyConfig.getOption("zb3");

                if (AtFairyConfig.getOption("6277").equals("1") && control_c == 0 && !zb3.equals("")) {
                    choice_map(AtFairyConfig.getOption("map3"), zb3.split(",")[0], zb3.split(",")[1]);
                    return;
                } else {
                    control_c = 1;
                }

                String zb4 = AtFairyConfig.getOption("zb4");

                if (!AtFairyConfig.getOption("map4").equals("") && !AtFairyConfig.getOption("map4").equals("0") && !zb4.equals("")) {
                    choice_map(AtFairyConfig.getOption("map4"), zb4.split(",")[0], zb4.split(",")[1]);
                    return;
                } else {
                    setTaskName(3);
                }
            }

            void cancel() throws Exception {
                FindResult cancel = gamePublicFuntion.cancel();
                mFairy.onTap(0.8f, cancel, "取消 | 拒绝", 500);
            }//取消 | 拒绝

            void inOperation() throws Exception {
                cancel();

                result = mFairy.findPic(470, 495, 641, 600, "song.png");
                if (result.sim > 0.75f) {
                    err = 0;
                }

                long l = mFairy.getColorNum(92, 712, 198, 718, "237,194,0", 0.95f);
                if (l > 100) {
                    LtLog.e(mFairy.getLineInfo("过图中 >>>"));
                    err = 0;
                }

                gamePublicFuntion.fh();
                gamePublicFuntion.close_use();

                if (xianshi()) {
                    setTaskName(0);
                    return;
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);
                /**
                 * 采集控制
                 */

                if(timeMap("cb",3600000,false)){
                    singleTask.cb();
                    setTaskName(0);
                    return;
                }

                if(start_task) {
                    if (control_a == 0) {

                        result = mFairy.findPic(1049, 495, 1203, 630, "prop1.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            frequencyInit("err");
                            mFairy.initMatTime();
                            gamePublicFuntion.auto_cj();
                        } else {
                            if (frequencyMap("err", 3)) {
                                gamePublicFuntion.stop_auto();
                            }
                        }

                        if (timeMap("control_a", getTimeStamp(AtFairyConfig.getOption("cj1")) + time, false)) {
                            LtLog.e(mFairy.getLineInfo("时间到了,切换其他任务>>>>>>"));
                            control_a = 1;
                            time = 0;
                            gamePublicFuntion.stop_auto();
                            setTaskName(0);
                            return;
                        }

                    } else if (control_b == 0) {
                        result = mFairy.findPic(1049, 495, 1203, 630, "prop2.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            frequencyInit("err");
                            mFairy.initMatTime();
                            gamePublicFuntion.auto_cj();
                        } else {
                            if (frequencyMap("err", 3)) {
                                gamePublicFuntion.stop_auto();
                            }
                        }

                        if (timeMap("control_b", getTimeStamp(AtFairyConfig.getOption("cj2")) + time, false)) {
                            LtLog.e(mFairy.getLineInfo("时间到了,切换其他任务>>>>>>"));
                            control_b = 1;
                            time = 0;
                            gamePublicFuntion.stop_auto();
                            setTaskName(0);
                            return;
                        }

                    } else if (control_c == 0) {
                        result = mFairy.findPic(1049, 495, 1203, 630, "prop3.png");
                        if (result.sim > 0.75f) {
                            err = 0;
                            frequencyInit("err");
                            mFairy.initMatTime();
                            gamePublicFuntion.auto_cj();
                        } else {
                            if (frequencyMap("err", 3)) {
                                gamePublicFuntion.stop_auto();
                            }
                        }

                        if (timeMap("control_c", getTimeStamp(AtFairyConfig.getOption("cj3")) + time, false)) {
                            LtLog.e(mFairy.getLineInfo("时间到了,切换其他任务>>>>>>"));
                            control_c = 1;
                            time = 0;
                            gamePublicFuntion.stop_auto();
                            setTaskName(0);
                            return;
                        }
                    } else if (control_d == 0) {
                        result = mFairy.findPic(1049, 495, 1203, 630, "prop5.png");
                        if (result.sim > 0.75f) {
                            frequencyInit("err");
                            err = 0;
                            mFairy.initMatTime();
                            gamePublicFuntion.auto_cj();
                        } else {
                            if (frequencyMap("err", 3)) {
                                gamePublicFuntion.stop_auto();
                            }
                        }

                        if (timeMap("control_d", getTimeStamp(AtFairyConfig.getOption("bp_time")), false)) {
                            LtLog.e(mFairy.getLineInfo("时间到了,切换其他任务>>>>>>"));
                            control_d = 1;
                            gamePublicFuntion.stop_auto();
                            return;
                        }
                    } else {
                        gamePublicFuntion.auto_battle();
                        if (timeMap("battle", getTimeStamp(AtFairyConfig.getOption("battle_time")), false)) {
                            LtLog.e(mFairy.getLineInfo("时间到了,切换其他任务>>>>>>"));
                            gamePublicFuntion.stop_auto();
                            singleTask.use_prop("prop5.png", AtFairyConfig.getOption("grade4"));
                            control_d = 0;
                            return;
                        }

                        if(gamePublicFuntion.mainScene()){
                            err=0;
                        }
                    }
                }


                if (gamePublicFuntion.mainScene()) {
                    if (timeMap("err_click", 10000, false)) {

                        String str = "";
                        if (control_a == 0) {
                            str = "砍树中>>>>>";
                        } else if (control_b == 0) {
                            str = "挖矿中>>>>>";
                        } else if (control_c == 0) {
                            str = "割草中>>>>>";
                        } else if (control_d == 0) {
                            str = "剥皮中>>>>>";
                        }else{
                            str = "战斗中>>>>>";
                        }

                        mFairy.onTap(599, 529, 662, 557, str, 500);
                    }

                    if (gamePublicFuntion.judgeStop(3, true)) {
                        start_task=true;
                        if (control_a == 0) {
                            singleTask.use_prop("prop1.png", AtFairyConfig.getOption("grade1"));
                            mFairy.initMatTime();
                            err=0;
                        } else if (control_b == 0) {
                            singleTask.use_prop("prop2.png", AtFairyConfig.getOption("grade2"));
                            mFairy.initMatTime();
                            err=0;
                        } else if (control_c == 0) {
                            singleTask.use_prop("prop3.png", AtFairyConfig.getOption("grade3"));
                            mFairy.initMatTime();
                            err=0;
                        } else if (control_d == 0) {
                            singleTask.use_prop("prop5.png", AtFairyConfig.getOption("grade4"));
                            mFairy.initMatTime();
                            err=0;
                        }else{
                            if (gamePublicFuntion.judgeStop(120, true)) {
                                setTaskName(0);
                                return;
                            }
                        }
                    }
                }
            }

            void content_03() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.mainScene()) {
                    err = 0;
                }
            }

            boolean xianshi() throws Exception {
                boolean bool = false;
                linitless_time = System.currentTimeMillis();
                while (true) {
                    week = mFairy.week();
                    hour = mFairy.dateHour();
                    minute = mFairy.dateMinute();
                    if (AtFairyConfig.getOption("ljlx").equals("1") && go1 == 0) {
                        if (week == 6 && (hour == 15 || hour == 16)) {
                            singleTask.li_rank();
                            gamePublicFuntion.stop_auto();
                            singleTask.ljlx();
                            go1 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("xhkg").equals("1") && go2 == 0) {
                        if ((week == 2 || week == 6) && ((hour == 12 && minute > 15) || hour == 13)) {
                            singleTask.li_rank();
                            gamePublicFuntion.stop_auto();
                            singleTask.xhkg();
                            go2 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("dld").equals("1") && go3 == 0) {
                        if ((week == 4 || week == 7) && ((hour == 12 && minute > 15) || hour == 13)) {
                            singleTask.li_rank();
                            gamePublicFuntion.stop_auto();
                            singleTask.dld();
                            go3 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("jdls").equals("1") && go4 == 0) {
                        if ((week == 1 || week == 3 || week == 5) && (((hour == 12 && minute > 15) || hour == 13) || ((hour == 20 && minute > 15) || hour == 21))) {
                            singleTask.li_rank();
                            gamePublicFuntion.stop_auto();
                            singleTask.jdls();
                            go4 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("gsdzz").equals("1") && go5 == 0) {
                        if ((week == 1 || week == 4) && (hour == 20 || (hour == 21 && minute < 30))) {
                            singleTask.li_rank();
                            gamePublicFuntion.stop_auto();
                            singleTask.gsdzz();
                            go5 = 1;
                            bool = true;
                            continue;
                        }
                    }


                    if (hour == 13 || hour == 19) {
                        go6 = 0;
                    }
                    if (AtFairyConfig.getOption("sjsl").equals("1") && go6 == 0) {
                        if ((hour == 12 || hour == 18 || hour == 22) && minute<10 ) {
                            singleTask.li_rank();
                            gamePublicFuntion.stop_auto();
                            teamTask.sjsl();
                            go6 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("jybwz").equals("1") && go7 == 0) {
                        if ((week == 1 || week == 5) && hour == 21) {
                            singleTask.li_rank();
                            gamePublicFuntion.stop_auto();
                            teamTask.jybwz();
                            go7 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("mt10").equals("1") && go8 == 0) {
                        if (hour >= 10 && hour < 17) {
                            singleTask.li_rank();
                            gamePublicFuntion.stop_auto();
                            singleTask.mtzc();
                            go8 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("mt05").equals("1") && go9 == 0) {
                        if (hour >= 17) {
                            singleTask.li_rank();
                            gamePublicFuntion.stop_auto();
                            singleTask.mtzc();
                            go9 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("xxgj").equals("1") && go10 == 0) {
                        if ((week == 2 || week == 5) && (hour == 20 || (hour == 21 && minute < 30))) {
                            singleTask.li_rank();
                            gamePublicFuntion.stop_auto();
                            singleTask.xxgj();
                            go10 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("yqdj").equals("1") && go11 == 0) {
                        if (week == 2 && hour == 21 && minute < 45) {
                            singleTask.li_rank();
                            gamePublicFuntion.stop_auto();
                            singleTask.yqdj();
                            go11 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("mftt").equals("1") && go12 == 0) {
                        if ((week==3 && hour==21) || (week==6 && (hour==20 || (hour==21 && minute<30) ))) {
                            singleTask.li_rank();
                            gamePublicFuntion.stop_auto();
                            singleTask.mftt();
                            go12 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    if (AtFairyConfig.getOption("pfsd").equals("1") && go13 == 0) {
                        if ((week==4 || week==7) && hour==21 && minute<45) {
                            singleTask.li_rank();
                            gamePublicFuntion.stop_auto();
                            singleTask.pfsd();
                            go13 = 1;
                            bool = true;
                            continue;
                        }
                    }

                    break;
                }

                linitless_time = System.currentTimeMillis() - linitless_time;
                time = time + linitless_time;
                return bool;
            }
        };
    }//无限任务

}