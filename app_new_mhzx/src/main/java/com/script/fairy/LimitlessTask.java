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
    
    public void hangUp() throws Exception {
        int chaos;
        String str = AtFairyConfig.getOption("chaos");
        if (str.equals("")) {
            chaos = -1;
        } else {
            chaos = Integer.parseInt(str);
        }

        int bj = 2, bj1 = 0, bj2 = 0, bj3 = 0, bj4 = 0, bj5 = 0, bj6 = 0, bj7 = 0, bj8 = 0, bj9 = 0,bj10=0;
        int count = 0, count1 = 0, count2 = 0;
         while (mFairy.condit()) {
            LtLog.e("--------挂机 bj= " + bj);
            if (bj == 0) {
                gamePublicFuntion.init(2);
                bj = 1;
                count1 = 0;
            }


            if (bj == 1) {
                if (AtFairyConfig.getOption("ddgj").equals("1")){
                    result = mFairy.findPic("ranksButton.png");
                    mFairy.onTap(0.6f, result, "点击队伍",1000);
                    mFairy.onTap(0.6f, result, "点击队伍",1000);
                    Thread.sleep(2000);
                    result = mFairy.findPic("create1.png");
                    mFairy.onTap(0.8f, result, "创建队伍",1000);
                    Thread.sleep(3000);
                    result = mFairy.findPic("change.png");
                    mFairy.onTap(0.9f, result, "更改",1000);
                    Thread.sleep(3000);


                    for (int i = 0; i < 3; i++) {
                        mFairy.ranSwipe(310, 172, 355, 614, 0, 1000,(long)100); //下滑
                    }

                    for (int j = 0; j < 5; j++) {
                        result = mFairy.findPic(269, 143, 408, 670, "gj1.png");
                        LtLog.e("--------  找混沌时空" + result.sim);
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "混沌时空",1000);
                            Thread.sleep(3000);
                            count1 = 1;
                            break;
                        } else {
                            mFairy.ranSwipe(338, 239, 383, 536, 2, 1000,(long)2000); //上滑
                        }
                    }

                    if (count1 == 0) {
                        for (int i = 0; i < 3; i++) {
                            mFairy.ranSwipe(310, 172, 355, 614, 0, 1000,(long)100); //下滑
                        }
                        for (int j = 0; j < 4; j++) {
                            result = mFairy.findPic(269, 143, 408, 670, "gj2.png");
                            LtLog.e("--------  找日常活动" + result.sim);
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "日常活动",1000);
                                break;
                            } else {
                                mFairy.ranSwipe(338, 239, 383, 536, 2, 1000,(long)2000); //上滑
                            }
                        }
                        for (int j = 0; j < 3; j++) {

                            result = mFairy.findPic(269, 143, 408, 670, "gj1.png");
                            LtLog.e("--------  找混沌时空" + result.sim);
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "混沌时空",1000);
                                Thread.sleep(3000);
                                count1 = 1;
                                break;
                            } else {
                                mFairy.ranSwipe(338, 239, 383, 536, 2, 1000,(long)2000); //上滑
                            }
                        }
                    }

                    result = mFairy.findPic("matchingTarget.png");
                    if (result.sim > 0.8f) {
                        if (AtFairyConfig.getOption("pp").equals("1")) {
                            mFairy.onTap(918, 348,919,349, "带新人",1000);
                        } else if (AtFairyConfig.getOption("pp").equals("5")) {
                            mFairy.onTap(914, 243, 915,244,"匹配5级内",1000);
                        } else if (AtFairyConfig.getOption("pp").equals("10") ){
                            mFairy.onTap(915, 299, 916,300,"匹配10级内",1000);
                        }

                       /* result = mFairy.findPic("qw2.png");
                        if(result.sim> 0.9f) {
                            mFairy.onTap(0.9f, result, "智能组队");
                            Thread.sleep(2000);
                            comm.spot(765, 430, "智能组队,同意");
                            Thread.sleep(2000);
                        }*/
                        result = mFairy.findPic(713, 548, 1071, 676, "matching1.png");
                        mFairy.onTap(0.8f, result, "自动匹配",1000);
                        Thread.sleep(2000);
                        for (int i = 0; i < 3; i++) {
                            result = mFairy.findPic(884, 17, 1246, 358, "close.png");
                            mFairy.onTap(0.7f, result, "关闭",1000);
                        }

                    }
                }

                result = mFairy.findPic("hangUp.png");
                mFairy.onTap(0.8f, result, "挂机",1000);

                result = mFairy.findPic("hdskUi.png");
                if (result.sim > 0.8f) {

                    if (chaos == -1) {
                        break;
                    } else {
                        gamePublicFuntion.hangUpTool(chaos, 1);
                    }
                }

                bj = 2;
            }
            if (bj == 2) {
                result = mFairy.findPic("auto1.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.9f, result, "自动战斗",1000);
                } else {
                    result = mFairy.findPic("auto.png");
                    if (result.sim > 0.7f) {
                        count = 0;
                        if (AtFairyConfig.getOption("ddgj").equals("1")) {
                            gamePublicFuntion.vtt();
                        } else {
                            Thread.sleep(5000);
                        }
                    }
                }


                result = mFairy.findPic(512, 0, 1274, 539, "close.png");
                mFairy.onTap(0.7f, result, "关闭",1000);


                //诛仙奇缘
                if (AtFairyConfig.getOption("zxqy").equals("1")){
                    if (bj1 == 0) {
                        hour = mFairy.dateHour();
                        if (hour >= 10) {
                            LtLog.e("-----开始诛仙奇缘");
                            gamePublicFuntion.mapButton();
                            singleTask.answerZXQY();
                            count2 = 0;
                            bj1 = 1;
                        }
                    }
                }

                //青云学堂
                if (AtFairyConfig.getOption("qyxt").equals("1")) {
                    if (bj2 == 0) {
                        week = mFairy.week();
                        hour = mFairy.dateHour();
                        minute = mFairy.dateMinute();
                        if (week != 6 && hour >= 17) {
                            if (hour == 17 && minute < 31) {
                            } else {
                                LtLog.e("-----开始青云学堂任务");
                                gamePublicFuntion.mapButton();
                                singleTask.answerQYXT();
                                count2 = 0;
                                bj2 = 1;
                            }
                        }
                    }
                }

                //帮派赛跑
                if (AtFairyConfig.getOption("bpsp").equals("1")) {
                    hour = mFairy.dateHour();
                    minute = mFairy.dateMinute();
                    if (hour == 12 || hour == 19) {
                        if (minute >= 40 && minute < 55) {
                            LtLog.e("-----开始帮派赛跑任务");
                            gamePublicFuntion.mapButton();
                            singleTask.race();
                            count2 = 0;
                        }
                    }
                }

                //幻月洞府
                if (AtFairyConfig.getOption("hydf").equals("1")) {
                    if (bj4 == 0) {
                        week = mFairy.week();
                        hour = mFairy.dateHour();
                        minute = mFairy.dateMinute();

                        if (week == 4) {
                            if (hour == 21) {
                                if (minute >= 35) {
                                    LtLog.e("-----开始幻月洞府任务");
                                    gamePublicFuntion.mapButton();
                                    singleTask.hydf();
                                    bj4 = 1;
                                    count2 = 0;
                                }
                            } else if (hour == 22) {
                                if (minute < 35) {
                                    LtLog.e("-----开始幻月洞府任务");
                                    gamePublicFuntion.mapButton();
                                    singleTask.hydf();
                                    bj4 = 1;
                                    count2 = 0;
                                }
                            }
                        }
                    }
                }


                //门派挑战
                if (AtFairyConfig.getOption("mptz").equals("1")) {
                    if (bj7 == 0) {
                        week = mFairy.week();
                        hour = mFairy.dateHour();
                        minute = mFairy.dateMinute();
                        if (week == 3) {
                            if (hour == 20 && minute >= 5) {
                                LtLog.e("-----开始门派挑战任务");
                                gamePublicFuntion.mapButton();
                                singleTask.mptz();
                                bj7 = 1;
                                count2 = 0;
                            }
                        }
                    }
                }


                //科举考试
                if (AtFairyConfig.getOption("kjks").equals("1")) {
                    if (bj3 == 0) {
                        week = mFairy.week();
                        hour = mFairy.dateHour();
                        minute = mFairy.dateMinute();
                        if (week == 6) {
                            switch (hour) {
                                case 17:
                                    if (minute > 30) {
                                        LtLog.e("-----科举考试");
                                        gamePublicFuntion.mapButton();
                                        singleTask.answerKJ();
                                        bj3 = 1;
                                        count2 = 0;
                                    }
                                    break;
                                case 18:
                                    LtLog.e("-----科举考试");
                                    gamePublicFuntion.mapButton();
                                    singleTask.answerKJ();
                                    bj3 = 1;
                                    count2 = 0;
                                    break;
                                case 19:
                                    if (minute < 50) {
                                        LtLog.e("-----科举考试");
                                        gamePublicFuntion.mapButton();
                                        singleTask.answerKJ();
                                        bj3 = 1;
                                        count2 = 0;
                                    }
                                    break;
                                default:
                                    break;


                            }


                        }
                    }
                }


                //世界boss
                if (AtFairyConfig.getOption("boss").equals("1")){
                    if (bj5 == 0) {
                        week = mFairy.week();
                        hour = mFairy.dateHour();
                        if (week == 5 && hour >= 18) {
                            LtLog.e("-----开始世界boss任务");
                            gamePublicFuntion.mapButton();
                            singleTask.boss();
                            bj5 = 1;
                            count2 = 0;
                        }

                    }
                }


                //虚空战场
                if (AtFairyConfig.getOption("xkzc").equals("1")){
                    if(bj10==0) {
                        week = mFairy.week();
                        hour = mFairy.dateHour();
                        if (week == 3 || week == 6) {
                            if (hour >= 12) {
                                LtLog.e("-----开始虚空战场任务");
                                gamePublicFuntion.mapButton();
                                if(singleTask.xkzc()==-1){
                                    bj10=1;
                                }
                                count2 = 0;
                            }

                        }
                    }
                }


                //十二星座传说
                if (AtFairyConfig.getOption("xzcs").equals("1")) {
                    hour = mFairy.dateHour();
                    minute = mFairy.dateMinute();
                    if (hour == 20 && minute <= 5) {
                        LtLog.e("-----开始十二星座传说");
                        gamePublicFuntion.mapButton();
                        singleTask.xzcs();
                        count2 = 0;

                    }
                }

                //七脉会武
                if (AtFairyConfig.getOption("qmhw").equals("1")) {
                    week = mFairy.week();
                    hour = mFairy.dateHour();
                    minute = mFairy.dateMinute();
                    if (week == 6) {
                        if (hour == 20) {
                            if (minute > 5) {
                                LtLog.e("-----开始七脉会武");
                                gamePublicFuntion.mapButton();
                                singleTask.qmhw();
                                count2 = 0;
                            }
                        } else if (hour == 21) {
                            if (minute < 25) {
                                LtLog.e("-----开始七脉会武");
                                gamePublicFuntion.mapButton();
                                singleTask.qmhw();
                                count2 = 0;
                            }
                        }
                    }
                }
                //首席争霸
                if (AtFairyConfig.getOption("sxzb").equals("1")) {
                    week = mFairy.week();
                    hour = mFairy.dateHour();
                    minute = mFairy.dateMinute();
                    if (week == 1) {
                        if (hour == 21) {
                            if (minute > 5) {
                                LtLog.e("-----开始首席争霸");
                                gamePublicFuntion.mapButton();
                                singleTask.zb();
                                count2 = 0;
                            }
                        }
                    }
                }

                //精英九霄
                if (AtFairyConfig.getOption("jyjx").equals("1")) {
                    if (bj8 == 0) {
                        week = mFairy.week();
                        hour = mFairy.dateHour();
                        minute = mFairy.dateMinute();
                        if (week == 6) {

                            if (hour == 14) {
                                LtLog.e("-----开始精英九霄");
                                gamePublicFuntion.mapButton();
                                singleTask.jyjx();
                                count2 = 0;
                                bj8 = 1;

                            } else if (hour == 15) {
                                if (minute < 30) {
                                    LtLog.e("-----开始精英九霄");
                                    gamePublicFuntion.mapButton();
                                    singleTask.jyjx();
                                    count2 = 0;
                                    bj8 = 1;
                                }
                            }
                        }
                    }
                }


                //决战九霄
                if (AtFairyConfig.getOption("jzjx").equals("1")) {
                    if (bj9 == 0) {
                        week = mFairy.week();
                        hour = mFairy.dateHour();
                        minute = mFairy.dateMinute();
                        if (week == 1) {

                            if (hour == 20 && minute >= 5) {
                                LtLog.e("-----开始决战九霄");
                                gamePublicFuntion.mapButton();
                                singleTask.jzjx();
                                count2 = 0;
                                bj9 = 1;
                            }
                        } else if (week == 7) {

                            if (hour == 21) {
                                LtLog.e("-----开始决战九霄");
                                gamePublicFuntion.mapButton();
                                singleTask.jzjx();
                                count2 = 0;
                                bj9 = 1;
                            }
                        }
                    }
                }


                //零点结束
                if (AtFairyConfig.getOption("ldjs").equals("1")) {
                    int hour = mFairy.dateHour();
                    if (hour == 0) {
                        break;
                    }
                }

                //五点结束
                if (AtFairyConfig.getOption("wdjs").equals("1")) {
                    int hour = mFairy.dateHour();
                    if (hour == 5) {
                        break;
                    }
                }


                count++;
                count2++;

                if (count2 == 2) {
                    bj = 0;
                }

                if (count > 50) {
                    bj = 0;
                    count = 0;
                }

            }
        }
    }//挂机


}