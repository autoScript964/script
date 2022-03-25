package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.TaskContent;
import com.script.framework.AtFairyImpl;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class GameUtil extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    FindResult result2;

    public GameUtil(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
    }

    public void inOperation() throws Exception {
        result = mFairy.findPic("Over drawing.png");
        if (result.sim > 0.85f) {
            LtLog.e(mFairy.getLineInfo("过图中"));
            mFairy.initMatTime();
            err = 0;
        }
        zh();
        gdFBTeam();
    }

    public void close(final int close) throws Exception {
        int j = 1;

        for (int i = 0; i < j; i++) {
            mFairy.condit();
            result = mFairy.findPic("initTakeup.png");
            mFairy.onTap(0.8f, result, "收起设置", Sleep);

            result = mFairy.findPic("chat.png");
            mFairy.onTap(0.8f, result, "收起聊天框", Sleep);

            result = mFairy.findPic("Home opens.png");
            result1 = mFairy.findPic("shequ.png");
            if (result1.sim > 0.8f && OtherGame.homefood) {
                mFairy.onTap(0.8f, result, "在家园打开扩展栏", Sleep);
            }/*else if (result1.sim < 0.8f){
                        Thread.sleep(3000);
                        mFairy.condit();
                        result = mFairy.findPic("Home opens.png");
                        result1 = mFairy.findPic("shequ.png");
                        mFairy.onTap(0.8f, result, "收回扩展栏", Sleep);
                    }*/

            result = mFairy.findPic("Coordinatetravel.png");
            mFairy.onTap(0.8f, result, 468, 408, 494, 418, "前往取消", Sleep);

            result = mFairy.findPic(1096, 240, 1221, 343, new String[]{"fork5.png", "fork6.png"});
            mFairy.onTap(0.8f, result, "物品叉", Sleep);

            result = mFairy.findPic(433, 130, 858, 374, "jia6.png");
            if (result.sim < 0.8f) {
                result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png", "x1.png"});
                mFairy.onTap(0.7f, result, "叉子2", 2000);
                if (result.sim > 0.7f) {
                    j = 20;
                } else {
                    if (i != 0) {
                        j = 2;
                    }
                }

                result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork3.png", "fork4.png"});
                mFairy.onTap(0.8f, result, "叉子1", 2000);
                if (result.sim > 0.8f) {
                    j = 20;
                } else {
                    if (i != 0) {
                        j = 2;
                    }
                }
            }

            if (close == 1) {
                result = mFairy.findPic("Hangup1.png");
                mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", Sleep);
            }
        }
    }

    int tastState = 0;

    public int mission(final String str, final int option) throws Exception {
        new GameUtil(mFairy) {
            int findtask = 0;

            public void create() throws Exception {
                super.create();
                findtask = 0;

                if(str.equals("Teacherstask1")){
                    findtask=2;
                }
            }

            public void content_0() throws Exception {
                close(1);
                setTaskName(1);

                if (!str.equals("aDragon.png")) {
                    findtask++;

                    if (findtask > 3) {
                        LtLog.e("没有这个任务");
                        GameUtil.this.tastState = 0;
                        setTaskEnd();
                    }
                }
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) {

                    return;
                }
                Thread.sleep(500);

                result = mFairy.findPic(639, 6, 1126, 176, "activity.png");
                mFairy.onTap(0.8f, result, "活动", 3000);

                result = mFairy.findPic("Activeinterface.png");
                if (result.sim > 0.7f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo(0.7f, result, "活动界面"));

                    Thread.sleep(5000);

                    result = mFairy.findPic(99, 8, 1178, 586, str);
                    if (result.sim > 0.75f) {

                    } else {

                        result = mFairy.findPic(60, 2, 296, 551, new String[]{"rc1.png", "rc2.png"});
                        mFairy.onTap(0.8f, result, "日常", 1000);

                        result = mFairy.findPic(new String[]{"rc3.png", "rc4.png"});
                        if (result.sim > 0.8f) {
                            if (option == 1) {
                                mFairy.onTap(309, 33, 364, 46, "所有活动", 3000);
                            }
                            if (option == 2) {
                                //我要装备
                                mFairy.onTap(487, 29, 529, 48, "我要装备", 3000);
                            }
                            if (option == 3) {
                                //我要经验
                                mFairy.onTap(620, 32, 662, 47, "我要经验", 3000);
                            }
                            if (option == 4) {
                                //我要帮贡
                                mFairy.onTap(780, 31, 830, 49, "我要帮贡", 3000);
                            }

                        } else if (mFairy.findPic(262, 94, 447, 193, new String[]{"rc5.png", "rc6.png"}).sim > 0.8f) {

                            if (option == 1) {
                                mFairy.onTap(337, 134, 367, 150, "所有活动", 3000);
                            }
                            if (option == 2) {
                                //我要装备
                                mFairy.onTap(495, 136, 521, 149, "我要装备", 3000);
                            }
                            if (option == 3) {
                                //我要经验
                                mFairy.onTap(648, 136, 674, 146, "我要经验", 3000);
                            }
                            if (option == 4) {
                                //我要帮贡
                                mFairy.onTap(797, 131, 826, 147, "我要帮贡", 3000);
                            }
                        }
                    }
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(8, 0)) return;

                result = mFairy.findPic(83, 94, 272, 611, new String[]{"xiuxian.png", "xiuxian1.png"});
                if (result.sim < 0.8f) {
                    Thread.sleep(8000);
                    return;
                }

                result = mFairy.findPic(202, 5, 629, 466, new String[]{"laba.png", "laba1.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    Thread.sleep(2000);
                    LtLog.e(mFairy.getLineInfo("发现喇叭"));
                    return;
                }

                result1 = mFairy.findPic(99, 8, 1178, 586, str);
                LtLog.e(mFairy.getLineInfo("活动的相似度：" + result1.sim));
                if (result1.sim > 0.75f) {
                    if (AtFairyConfig.getOption("20c").equals("1") && str.equals("aDragon.png")) {
                        result = mFairy.findPic(result1.x + 170, result1.y + 45, result1.x + 202, result1.y + 72, "twentieth.png");
                        LtLog.e(mFairy.getLineInfo(0.7f, result, "一条龙20次80活跃"));
                        if (result.sim > 0.72f) {
                            result = mFairy.findPic(result1.x + 137, result1.y + 44, result1.x + 172, result1.y + 79, "twentieth.png");
                            LtLog.e(mFairy.getLineInfo(0.7f, result, "一条龙20次80活跃满-----------------"));
                            if (result.sim > 0.72f) {
                                GameUtil.this.tastState = 0;
                                setTaskEnd();
                                return;
                            }
                        }

                        result = mFairy.findPic(result1.x + 170, result1.y + 45, result1.x + 202, result1.y + 72, "twentieth1.png");
                        LtLog.e(mFairy.getLineInfo(0.7f, result, "一条龙20次60活跃"));
                        if (result.sim > 0.72f) {
                            result = mFairy.findPic(result1.x + 137, result1.y + 44, result1.x + 172, result1.y + 79, "twentieth1.png");
                            LtLog.e(mFairy.getLineInfo(0.7f, result, "一条龙20次60活跃满-----------------"));
                            if (result.sim > 0.72f) {
                                GameUtil.this.tastState = 0;
                                setTaskEnd();
                                return;
                            }
                        }
                    }

                    result = mFairy.findPic(result1.x + 235, result1.y + 2, result1.x + 335, result1.y + 69, "participatein.png");
                    mFairy.onTap(0.8f, result, "活动参加", Sleep);
                    if (result.sim >= 0.8f) {
                        GameUtil.this.tastState = 1;
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(result1.x + 235, result1.y + 2, result1.x + 335, result1.y + 69, "Already.png");
                    mFairy.onTap(0.8f, result, 1220, 28, 1231, 37, "活动已接", Sleep);
                    if (result.sim >= 0.8f) {
                        GameUtil.this.tastState = 2;
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(result1.x + 235, result1.y + 2, result1.x + 335, result1.y + 69, "Missionaccomp.png");
                    mFairy.onTap(0.8f, result, 1220, 28, 1231, 37, "活动完成", Sleep);
                    if (result.sim >= 0.8f) {
                        GameUtil.this.tastState = 0;
                        setTaskEnd();
                        return;
                    }
                } else {
                    if (str.equals("Teacherstask.png")) {
                        result1 = mFairy.findPic(99, 8, 1178, 586, "jia3.png");
                        if (result1.sim > 0.8f) {
                            result = mFairy.findPic(result1.x + 235, result1.y + 2, result1.x + 335, result1.y + 69, "participatein.png");
                            mFairy.onTap(0.8f, result, "活动参加", Sleep);
                            if (result.sim >= 0.8f) {
                                GameUtil.this.tastState = 1;
                                setTaskEnd();
                                return;
                            }
                        }

                        result = mFairy.findPic(result1.x + 235, result1.y + 2, result1.x + 335, result1.y + 69, "Already.png");
                        mFairy.onTap(0.8f, result, 1220, 28, 1231, 37, "活动已接", Sleep);
                        if (result.sim >= 0.8f) {
                            GameUtil.this.tastState = 2;
                            setTaskEnd();
                            return;
                        }

                        result = mFairy.findPic(result1.x + 235, result1.y + 2, result1.x + 335, result1.y + 69, "Missionaccomp.png");
                        mFairy.onTap(0.8f, result, 1220, 28, 1231, 37, "活动完成", Sleep);
                        if (result.sim >= 0.8f) {
                            GameUtil.this.tastState = 0;
                            setTaskEnd();
                            return;
                        }

                    }

                    if (str.equals("Teacherstask1.png")) {
                        result1 = mFairy.findPic(99, 8, 1178, 586, "sm1.png");
                        if (result1.sim > 0.8f) {
                            result = mFairy.findPic(result1.x + 235, result1.y + 2, result1.x + 335, result1.y + 69, "participatein.png");
                            mFairy.onTap(0.8f, result, "活动参加", Sleep);
                            if (result.sim >= 0.8f) {
                                GameUtil.this.tastState = 1;
                                setTaskEnd();
                                return;
                            }
                        }

                        result = mFairy.findPic(result1.x + 235, result1.y + 2, result1.x + 335, result1.y + 69, "Already.png");
                        mFairy.onTap(0.8f, result, 1220, 28, 1231, 37, "活动已接", Sleep);
                        if (result.sim >= 0.8f) {
                            GameUtil.this.tastState = 2;
                            setTaskEnd();
                            return;
                        }

                        result = mFairy.findPic(result1.x + 235, result1.y + 2, result1.x + 335, result1.y + 69, "Missionaccomp.png");
                        mFairy.onTap(0.8f, result, 1220, 28, 1231, 37, "活动完成", Sleep);
                        if (result.sim >= 0.8f) {
                            GameUtil.this.tastState = 0;
                            setTaskEnd();
                            return;
                        }
                    }

                    if (str.equals("aDragon.png")) {

                        result1 = mFairy.findPic(99, 8, 1178, 586, "tl1.png");
                        if (result1.sim > 0.8f) {
                            result = mFairy.findPic(result1.x + 160, result1.y - 20, result1.x + 335, result1.y + 110, "participatein.png");
                            mFairy.onTap(0.8f, result, "活动参加", Sleep);
                            if (result.sim >= 0.8f) {
                                GameUtil.this.tastState = 1;
                                setTaskEnd();
                                return;
                            }
                        }
                    }

                }

                result = mFairy.findPic(202, 5, 629, 466, new String[]{"laba.png", "laba1.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("发现喇叭"));
                    int er = 0;
                    while (true) {
                        result = mFairy.findPic(202, 5, 629, 466, new String[]{"laba.png", "laba1.png"});
                        if (result.sim < 0.8f) {
                            Thread.sleep(1000);
                            er++;
                            if (er > 2) {
                                break;
                            }
                        }
                        Thread.sleep(2000);
                    }
                    err = 0;
                    return;
                } else {
                    mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 0, 525, 611, 525, 183, 1500, 1000, 2);
                }
            }

        }.taskContent(mFairy, "找任务中 : " + str);
        return tastState;
    }//开始任务

    public void giveUpTask(String[] str) throws Exception {
        new GameUtil(mFairy) {

            public void content_0() throws Exception {
                close(1);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(20, 99)) return;
                result = mFairy.findPic("taskbar.png");
                mFairy.onTap(0.85f, result, "任务栏", Sleep);

                result = mFairy.findPic("taskbar1.png");
                mFairy.onTap(0.9f, result, "任务栏", Sleep);

                result = mFairy.findPic("Taskinterface.png");
                mFairy.onTap(0.8f, result, 1184,156,1189,182, "任务界面", Sleep);

                result = mFairy.findPic(107, 27, 353, 615, "Daytodaytask.png");
                mFairy.onTap(0.75f, result, "日常任务", Sleep);

                result = mFairy.findPic(107, 27, 353, 615, str);
                mFairy.onTap(0.7f, result, "战龙任务", Sleep);
                if (result.sim > 0.7f) {
                    setTaskName(2);
                }

            }

            public void content_2() throws Exception {
                if (overtime(10, 99)) return;

                result = mFairy.findPic(new String[]{"Giveuptask.png", "Giveuptask1.png", "fangqi.png"});
                mFairy.onTap(0.8f, result, "任务失败放弃", 3000);
                mFairy.onTap(0.8f, result, 796, 424, 797, 425, "任务失败放弃", Sleep);
                if (result.sim > 0.8f) {
                    close(1);
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "任务失败放弃任务");
    }//任务失败放弃任务

    int cityCount;

    public int goCity(final String str) throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                close(1);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;

                result = mFairy.findPic(new String[]{"activity.png", "package.png"});
                mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "包裹", 2000);

                result = mFairy.findPic("Mapinterface.png");
                mFairy.onTap(0.8f, result, "地图界面", Sleep);

                result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                if (result.sim > 0.8f) {
                    switch (str) {
                        case "帮会":
                            mFairy.onTap(0.8f, result, 142, 631, 153, 640, str, Sleep);
                            break;
                        case "家园":
                            mFairy.onTap(0.8f, result, 136, 539, 156, 551, str, Sleep);
                            break;
                        case "金陵":
                            mFairy.onTap(0.8f, result, 850, 280, 861, 298, str, Sleep);
                            break;
                        case "杭州":
                            mFairy.onTap(0.8f, result, 849, 485, 864, 500, str, Sleep);
                            break;
                        case "杨家镇":
                            mFairy.onTap(0.8f, result, 926, 363, 937, 381, str, Sleep);
                            break;
                        case "蒲家村":
                            mFairy.onTap(0.8f, result, 1011, 305, 1021, 325, str, Sleep);
                            break;
                        case "神机营":
                            mFairy.onTap(0.8f, result, 902, 171, 914, 185, str, Sleep);
                            break;
                        case "青丘":
                            mFairy.onTap(0.8f, result, 997, 533, 1007, 553, str, Sleep);
                            break;
                        case "逍遥观":
                            mFairy.onTap(0.8f, result, 1160, 546, 1171, 563, str, Sleep);
                            break;
                        case "古墓":
                            mFairy.onTap(0.8f, result, 742, 167, 755, 181, str, Sleep);
                            break;
                        case "镇郊荒野":
                            mFairy.onTap(0.8f, result, 726, 328, 740, 344, str, Sleep);
                            break;
                        case "黑风洞":
                            mFairy.onTap(0.8f, result, 668, 533, 683, 548, str, Sleep);
                            break;
                        case "兰若寺":
                            mFairy.onTap(0.8f, result, 611, 354, 625, 371, str, Sleep);
                            break;
                        case "万妖宫":
                            mFairy.onTap(0.8f, result, 639, 237, 650, 253, str, Sleep);
                            break;
                        case "忘川":
                            mFairy.onTap(0.8f, result, 533, 515, 546, 530, str, Sleep);
                            break;
                        case "兰若地宫":
                            mFairy.onTap(0.8f, result, 472, 329, 487, 347, str, Sleep);
                            break;
                        case "黄泉":
                            mFairy.onTap(0.8f, result, 394, 433, 406, 447, str, Sleep);
                            break;
                        case "酆都":
                            mFairy.onTap(0.8f, result, 360, 577, 375, 597, str, Sleep);
                            break;
                        case "地狱":
                            mFairy.onTap(0.8f, result, 261, 523, 277, 543, str, Sleep);
                            break;
                        case "丝路古道":
                            mFairy.onTap(0.8f, result, 320, 290, 336, 309, str, Sleep);
                            break;
                        case "天工阁":
                            mFairy.onTap(0.8f, result, 290, 159, 303, 178, str, Sleep);
                            break;
                        case "昆仑山":
                            mFairy.onTap(0.8f, result, 215, 339, 231, 354, str, Sleep);
                            break;
                        case "天宫":
                            mFairy.onTap(0.8f, result, 539, 207, 553, 221, str, Sleep);
                            break;
                        case "兜率宫":
                            mFairy.onTap(0.8f, result, 452, 143, 468, 161, str, Sleep);
                            break;
                    }
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                if (result.sim > 0.8f) {
                    close(1);
                    LtLog.e(mFairy.getLineInfo("没有目标地图"));
                    GameUtil.this.cityCount = 0;
                    setTaskEnd();
                    return;
                }
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 3) {
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    GameUtil.this.cityCount = 1;
                    setTaskEnd();
                    return;
                }
                Thread.sleep(3000);
            }
        }.taskContent(mFairy, "传送城市中");


        if (str.equals("帮会") || str.equals("家园")) {
            GameUtil.this.cityCount = 1;
        }
        return cityCount;
    } //传送城市

    public void coordinate(final String str, final int gmx, final int gmy) throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                close(1);
                if (str.equals("家园") || str.equals("兜率宫") || str.equals("酆都")) {
                    if (coordinate1(gmx, gmy) == false) {
                        setTaskEnd();
                        return;
                    }
                }
                setTaskName(1);
            }

            double x;
            double y;

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "包裹", Sleep);


                result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                mFairy.onTap(0.8f, result, "选择城市", Sleep);
                //518,545


                result = mFairy.findPic("Mapinterface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("地图界面=" + str));
                    Thread.sleep(2000);
                    switch (str) {
                        case "帮会":
                            x = gmx * 6.893 + gmy * -8.991 + 929.8802;
                            y = gmx * -6.6991 + gmy * -4.8808 + 1065.4878;
                            break;
                        case "杭州":
                            x = gmx * 2.0477 + gmy * -3.1156 + 924.304;
                            y = gmx * -2.0629 + gmy * -1.1914 + 775.1723;
                            break;
                        case "镇郊荒野":
                            x = gmx * 5.3039 + gmy * -6.3397 + 694.6715;
                            y = gmx * -4.4413 + gmy * -3.595 + 810.5726;
                            break;
                        case "蒲家村":
                            x = gmx * 1.9571 + gmy * -2.7289 + 789.0142;
                            y = gmx * -2.3719 + gmy * -1.7514 + 836.8621;
                            break;
                        case "杨家镇":
                            x = gmx * 7.5141 + gmy * -8.1211 + 637.7886;
                            y = gmx * -5.2506 + gmy * -4.9639 + 900.361;
                            break;
                        case "逍遥观":
                            x = gmx * 12.2663 + gmy * -10.6391 + 193.5444;
                            y = gmx * -7.6124 + gmy * -8.1302 + 1196.1479;
                            break;
                        case "黑风洞":
                            x = gmx * 4.2135 + gmy * -5.6336 + 808.4403;
                            y = gmx * -4.9062 + gmy * -3.0515 + 772.704;
                            break;
                        case "兰若寺":
                            x = gmx * 4.0024 + gmy * -6.2759 + 744.9139;
                            y = gmx * -5.2257 + gmy * -3.0781 + 844.6171;
                            break;
                        case "忘川":
                            x = gmx * 2.6824 + gmy * -5.9495 + 1125.7752;
                            y = gmx * -3.4933 + gmy * -1.5806 + 1107.7872;
                            break;
                        case "兰若地宫":
                            x = gmx * 4.9931 + gmy * -5.2846 + 613.8227;
                            y = gmx * -3.6782 + gmy * -3.4743 + 1239.3712;
                            break;
                        case "天宫":
                            x = gmx * 2.2473 + gmy * -3.8293 + 966.0569;
                            y = gmx * -3.0155 + gmy * -1.9505 + 969.3832;
                            break;
                        case "黄泉":
                            x = gmx * 5.8072 + gmy * -6.9406 + 697.1149;
                            y = gmx * -4.9206 + gmy * -4.7325 + 1134.5567;
                            break;
                        case "酆都":
                            x = gmx * 4.3807 + gmy * -4.4886 + 664.3352;
                            y = gmx * -4.4781 + gmy * -3.4919 + 914.418;
                            break;
                        case "地狱":
                            x = gmx * 6.9845 + gmy * -7.1219 + 684.137;
                            y = gmx * -4.6685 + gmy * -5.2739 + 914.1739;
                            break;
                        case "丝路古道":
                            x = gmx * 5.7449 + gmy * -7.1721 + 720.2733;
                            y = gmx * -5.1134 + gmy * -4.8543 + 887.1215;
                            break;
                        case "天工阁":
                            x = gmx * 2.1161 + gmy * -4.9381 + 988.0545;
                            y = gmx * -4.1313 + gmy * -1.2426 + 1080.523;
                            break;
                        case "兜率宫":
                            x = gmx * 2.893 + gmy * -2.6115 + 528.3787;
                            y = gmx * -2.4704 + gmy * -2.6487 + 1094.1867;
                            break;
                        case "家园":
                            x = gmx * 3.5088 + gmy * -9.9649 + 1001.4737;
                            y = gmx * -6.0439 + gmy * -2.7469 + 903.2744;
                            break;
                    }
                    mFairy.tap((int) x, (int) y);
                    LtLog.e(mFairy.getLineInfo("坐标x=" + (int) x + ",y=" + (int) y));
                    close(1);
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 3) {
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    setTaskEnd();
                    return;
                }
                Thread.sleep(1000);
            }
        }.taskContent(mFairy, "定位坐标中");
    }//定位坐标

    public boolean coordinate1(final int gmx, final int gmy) throws Exception {

        final boolean b[] = new boolean[1];
        b[0] = false;

        new GameUtil(mFairy) {
            int b_x, s_x, g_x, b_y, s_y, g_y;

            public void content_0() throws Exception {
                b_x = gmx / 100;  //百位
                s_x = gmx % 100 / 10;  //十位
                g_x = gmx % 10; //各位
                b_y = gmy / 100;  //百位
                s_y = gmy % 100 / 10;  //十位
                g_y = gmy % 10; //各位
                LtLog.e(mFairy.getLineInfo("x=" + b_x + s_x + g_x + ",y=" + b_y + s_y + g_y));
                close(1);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "包裹", 2000);

                result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                mFairy.onTap(0.8f, result, "选择城市", Sleep);

                result = mFairy.findPic("Mapinterface.png");
                mFairy.onTap(0.8f, result, 366, 80, 367, 81, "点开系统坐标", Sleep);

                FindResult resultxy = mFairy.findPic("Coordinatetravel.png");
                if (resultxy.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("坐标前往"));
                    mFairy.onTap(0.8f, resultxy, 546, 345, 571, 360, "点开x", 3000);
                    coordinate1(b_x);
                    coordinate1(s_x);
                    coordinate1(g_x);
                    mFairy.onTap(0.8f, resultxy, 904, 468, 933, 485, "x确定", Sleep);
                    mFairy.onTap(0.8f, resultxy, 727, 343, 755, 360, "点开y", 3000);
                    coordinate1(b_y);
                    coordinate1(s_y);
                    coordinate1(g_y);
                    mFairy.onTap(0.8f, resultxy, 1076, 466, 1110, 489, "y确定", Sleep);
                    mFairy.onTap(0.8f, resultxy, 747, 419, 778, 432, "前往", 2000);

                    result = mFairy.findPic("Coordinatetravel.png");
                    mFairy.onTap(0.8f, result, 515, 420, 557, 434, "前往失败", Sleep);
                    if (result.sim > 0.8f) {
                        b[0] = true;
                        setTaskEnd();
                        return;
                    }
                    mFairy.onTap(1201, 33, 1227, 45, "", 300);
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 1) {
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    setTaskEnd();
                    return;
                }
                Thread.sleep(1000);
            }
        }.taskContent(mFairy, "系统定位坐标");

        return b[0];
    } //系统定位坐标

    public void coordinate1(int x) throws Exception {
        result = mFairy.findPic(584, 116, 1237, 251, "Inputcolumn.png");
        if (x == 1) {
            mFairy.onTap(0.8f, result, result.x - 245, result.y, result.x - 244, result.y + 1, "输入1", 100);
        }
        if (x == 2) {
            mFairy.onTap(0.8f, result, result.x - 154, result.y, result.x - 153, result.y + 1, "输入2", 100);
        }
        if (x == 3) {
            mFairy.onTap(0.8f, result, result.x - 61, result.y, result.x - 60, result.y + 1, "输入3", 100);
        }
        if (x == 4) {
            mFairy.onTap(0.8f, result, result.x - 245, result.y + 86, result.x - 244, result.y + 87, "输入4", 100);

        }
        if (x == 5) {
            mFairy.onTap(0.8f, result, result.x - 154, result.y + 86, result.x - 153, result.y + 87, "输入5", 100);

        }
        if (x == 6) {
            mFairy.onTap(0.8f, result, result.x - 61, result.y + 86, result.x - 60, result.y + 87, "输入6", 100);

        }
        if (x == 7) {
            mFairy.onTap(0.8f, result, result.x - 245, result.y + 171, result.x - 244, result.y + 172, "输入7", 100);

        }
        if (x == 8) {
            mFairy.onTap(0.8f, result, result.x - 154, result.y + 171, result.x - 153, result.y + 172, "输入8", 100);

        }
        if (x == 9) {
            mFairy.onTap(0.8f, result, result.x - 61, result.y + 171, result.x - 60, result.y + 172, "输入9", 100);

        }
        if (x == 0) {
            mFairy.onTap(0.8f, result, result.x + 25, result.y + 109, result.x + 26, result.y + 110, "输入0", 100);
        }
    }

    public void dividing() throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                close(1);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(15, 3)) return;
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1255, 20, 1256, 21, "打开分线", Sleep);

                result = mFairy.findPic("fx.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("分线界面"));
                    if (Integer.parseInt(AtFairyConfig.getOption("fx")) == 1) {
                        mFairy.onTap(392, 161, 393, 162, "选择分线1", Sleep);
                    }
                    if (Integer.parseInt(AtFairyConfig.getOption("fx")) == 2) {
                        mFairy.onTap(799, 162, 800, 163, "选择分线2", Sleep);
                    }
                    if (Integer.parseInt(AtFairyConfig.getOption("fx")) == 3) {
                        mFairy.onTap(445, 232, 446, 233, "选择分线3", Sleep);
                    }
                    if (Integer.parseInt(AtFairyConfig.getOption("fx")) == 4) {
                        mFairy.onTap(862, 227, 863, 228, "选择分线4", Sleep);
                    }
                    if (Integer.parseInt(AtFairyConfig.getOption("fx")) == 5) {
                        mFairy.onTap(382, 322, 414, 340, "选择分线5", Sleep);
                    }
                    if (Integer.parseInt(AtFairyConfig.getOption("fx")) == 6) {
                        mFairy.onTap(824, 325, 844, 333, "选择分线6", Sleep);
                    }

                    close(0);
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 3) {
                    LtLog.e(mFairy.getLineInfo("切换分线完成"));
                    setTaskEnd();
                    return;
                }
                Thread.sleep(3000);
            }

            public void content_3() throws Exception {
                close(0);
                setTaskEnd();
                return;
            }
        }.taskContent(mFairy, "切换分线中");
    }//切换分线

    public void callToFollow() throws Exception {
        close(0);
        LtLog.e(mFairy.getLineInfo("【召唤跟随】"));
        for (int i =0; i<10;i++){

            result = mFairy.findPic("Contingenthurdles.png");
            mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

            result = mFairy.findPic("Contingenthurdles1.png");
            mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);


            result = mFairy.findPic(4, 191, 252, 493, "call.png");
            mFairy.onTap(0.75f, result, "召唤跟随", Sleep);
            if (result.sim > 0.75f) {
                break;
            }
        }
        close(0);
    }//召唤跟随中

    public void cancelFollowing() throws Exception {
        close(0);
        LtLog.e(mFairy.getLineInfo("【取消跟随】"));
        for (int i =0; i<10;i++){
            result = mFairy.findPic("Contingenthurdles.png");
            mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

            result = mFairy.findPic("Contingenthurdles1.png");
            mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

            result = mFairy.findPic(1, 190, 239, 559, "Cancelfollowing.png");
            mFairy.onTap(0.75f, result, "左侧取消跟随", Sleep);
            if (result.sim > 0.75f) {
                break;
            }
        }
        close(0);
    }//取消跟随中

    public void callToFollowduizhang() throws Exception {
        close(0);
        LtLog.e(mFairy.getLineInfo("【跟随队长】"));
        for (int i =0; i<10;i++){
            result = mFairy.findPic("Contingenthurdles.png");
            mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

            result = mFairy.findPic("Contingenthurdles1.png");
            mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

            result = mFairy.findPic(1, 190, 239, 559, "gsdz.png");
            mFairy.onTap(0.75f, result, "左侧跟随队长", Sleep);
            if (result.sim > 0.75f) {
                break;
            }
        }
        close(0);
    }//跟随队长


    int zmcount = 0;

    public void recruit() throws Exception {
        new GameUtil(mFairy) {


            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(15, 2)) return;

                result = mFairy.findPic("Openteam.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Openteam1.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("zdpp.png");
                mFairy.onTap(0.8f, result, "自动匹配", Sleep);

                result = mFairy.findPic("sqlb.png");
                mFairy.onTap(0.8f, result, 1163, 167, 1164, 168, "申请列表", Sleep);

                result = mFairy.findPic("yjhh.png");
                mFairy.onTap(0.8f, result, "一键喊话", 2000);

                if (GameUtil.this.zmcount == 0) {
                    result = mFairy.findPic(822, 132, 1122, 351, "bhpd.png");
                    mFairy.onTap(0.8f, result, "帮会频道", Sleep);
                    GameUtil.this.zmcount = 1;
                } else {
                    result = mFairy.findPic(822, 132, 1122, 351, "duiwupindao.png");
                    mFairy.onTap(0.8f, result, "队伍频道", Sleep);
                    GameUtil.this.zmcount = 0;
                }

                result = mFairy.findPic("zmfs.png");
                mFairy.onTap(0.8f, result, "发送", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                close(0);
                setTaskEnd();
            }
        }.taskContent(mFairy, "招募喊话中");
    }//招募喊话中

    int outManNum;

    public int kicking(final String str) throws Exception {
        outManNum = 0;
        new GameUtil(mFairy) {


            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(5, 2)) return;

                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);


                result = mFairy.findPic(4, 191, 252, 493, "call.png");
                mFairy.onTap(0.75f, result, "召唤跟随", Sleep);
                if (result.sim > 0.75f) {
                    result = mFairy.findPic(196, 197, 242, 253, "Numberpeople.png");
                    if (result.sim > 0.8f) {
                        int numcolor = mFairy.getColorNum(61, 226, 101, 230, "255,89,74", 0.9f);
                        LtLog.e(mFairy.getLineInfo("num=" + numcolor));
                        if (numcolor < 10) {
                            GameUtil.this.cityCount++;
                            if (str.equals("踢人")) {
                                mFairy.onTap(0.75f, result, "第2个人踢出去", 2000);
                                result = mFairy.findPic(236, 3, 533, 686, "Pleaseleave.png");
                                mFairy.onTap(0.8f, result, "请离队员", Sleep);
                            }
                        }
                    }

                    result = mFairy.findPic(195, 257, 242, 315, "Numberpeople.png");
                    if (result.sim > 0.8f) {
                        int numcolor = mFairy.getColorNum(63, 285, 81, 292, "255,89,74", 0.9f);
                        if (numcolor < 10) {
                            GameUtil.this.cityCount++;
                            if (str.equals("踢人")) {
                                mFairy.onTap(0.75f, result, "第3个人踢出去", 2000);
                                result = mFairy.findPic(236, 3, 533, 686, "Pleaseleave.png");
                                mFairy.onTap(0.8f, result, "请离队员", Sleep);
                            }
                        }
                    }

                    result = mFairy.findPic(194, 315, 241, 374, "Numberpeople.png");
                    if (result.sim > 0.8f) {

                        int numcolor = mFairy.getColorNum(64, 345, 83, 351, "255,89,74", 0.9f);
                        if (numcolor < 10) {
                            GameUtil.this.cityCount++;
                            if (str.equals("踢人")) {
                                mFairy.onTap(0.75f, result, "第4个人踢出去", 2000);
                                result = mFairy.findPic(236, 3, 533, 686, "Pleaseleave.png");
                                mFairy.onTap(0.8f, result, "请离队员", Sleep);
                            }
                        }
                    }
                    result = mFairy.findPic(194, 376, 243, 432, "Numberpeople.png");
                    if (result.sim > 0.8f) {
                        int numcolor = mFairy.getColorNum(63, 407, 79, 414, "255,89,74", 0.9f);
                        if (numcolor < 10) {
                            GameUtil.this.cityCount++;
                            if (str.equals("踢人")) {
                                mFairy.onTap(0.75f, result, "第5个人踢出去", 2000);
                                result = mFairy.findPic(236, 3, 533, 686, "Pleaseleave.png");
                                mFairy.onTap(0.8f, result, "请离队员", Sleep);
                            }
                        }
                    }
                    setTaskName(2);
                }

            }

            public void content_2() throws Exception {
                close(0);
                setTaskEnd();
            }
        }.taskContent(mFairy, "踢人中");
        return outManNum;
    } //踢人

    public void clearbag() throws Exception {
        new GameUtil(mFairy) {


            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(15, 2)) {
                    close(0);
                    return;
                }
                if (AtFairyConfig.getOption("cs").equals("1")) {
                    result = mFairy.findPic(639, 6, 1126, 176, "ys.png");
                    mFairy.onTap(0.8f, result, "易市", 3000);

                    result = mFairy.findPic("jiaoyishichang.png");
                    mFairy.onTap(0.8f, result, "交易市场", Sleep);

                    result = mFairy.findPic("jxinface.png");
                    mFairy.onTap(0.8f, result, 379, 97, 400, 108, "交易市场切换到卖东西", Sleep);

                    result = mFairy.findPic("chushou.png");
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 10; i++) {
                            mFairy.onTap(0.8f, result, "出售", 10);
                        }
                        close(0);
                        setTaskName(2);
                        return;
                    }
                } else {
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(30, 0)) return;
                Thread.sleep(1000);
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, "包裹", 12000);

                result = mFairy.findPic("recovery.png");
                mFairy.onTap(0.8f, result, "回收", 5000);

                result = mFairy.findPic("recovery1.png");
                mFairy.onTap(0.8f, result, 766,91,776,99, "选择蓝装以下", 3000);
                mFairy.onTap(0.8f, result, "回收", 3000);
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic("Recoveryfailure.png");
                    mFairy.onTap(0.8f, result1, 640, 423, 641, 424, "回收失败", Sleep);
                    mFairy.onTap(0.8f, result, 680, 92, 718, 107, "返回", Sleep);
                    setTaskName(3);
                }
            }

            public void content_3() throws Exception {

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, "包裹", 3000);

                result = mFairy.findPic(630, 123, 1126, 618, new String[]{/*"Goods.png",*/ "Goods1.png", "Goods2.png", "Goods3.png", "Goods4.png", "Goods5.png"});
                mFairy.onTap(0.9f, result, "物品", Sleep);
                if (result.sim > 0.9f) {
                    //   err--;
                } else {
                    result = mFairy.findPic("recovery.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("包裹界面"));
                        mFairy.taskSlid(err, new int[]{0, 2, 3, 4}, 0, 922, 593, 922, 172, 500, 2000);
                    }
                }
                for (int i = 0; i < 10; i++) {
                    mFairy.condit();
                    result = mFairy.findPic(320, 117, 775, 533, "Useprops.png");
                    mFairy.onTap(0.85f, result, "使用道具", Sleep);
                    if (result.sim < 0.85f) {
                        break;
                    }
                }

                result = mFairy.findPic("qbfs.png");
                mFairy.onTap(0.8f, result, 624, 225, 670, 249, "发送", 2000);
                if (result1.sim > 0.8f) {
                    result1 = mFairy.findPic("fasongsure.png");
                    mFairy.onTap(0.8f, result1, "发送确定", 2000);
                }
                mFairy.onTap(0.8f, result, "发送", Sleep);

                for (int i = 0; i < 10; i++) {
                    mFairy.condit();
                    result = mFairy.findPic("use1.png");
                    mFairy.onTap(0.8f, result, "使用经验道具", Sleep);
                    if (result.sim < 0.8f) {
                        break;
                    }
                }

                result1 = mFairy.findPic(452, 495, 830, 573, "Coverup.png");
                if (result1.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                    setTaskName(4);
                    return;
                }
                result = mFairy.findPic("mapsure.png");
                mFairy.onTap(0.8f, result, "确定", Sleep);
                if (overtime(10, 4)) return;
            }

            public void content_4() throws Exception {
                close(0);
                setTaskEnd();
            }

        }.taskContent(mFairy, "清包中");
    }//清包

    public void setUp() throws Exception {
        new GameUtil(mFairy) {


            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1239, 206, 1240, 207, "切换栏", 3000);

                result = mFairy.findPic(1164, 242, 1273, 699, new String[]{"Settingbutton.png","shezhi.png","shezhi1.png"});
                mFairy.onTap(0.8f, result, "设置", Sleep);

                result = mFairy.findPic("Settinginterface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("设置界面"));
                    mFairy.onTap(946, 108, 963, 117, "流畅模式", Sleep);
                    mFairy.onTap(611,371,612,372, "显示", 100);
                    mFairy.onTap(999,419,1004,424, "分辨率", 100);
                    mFairy.onTap(995,471,1005,478, "精细度", 100);
                    mFairy.onTap(610,529,628,536, "视角", 100);
                    mFairy.onTap(219, 153, 238, 166, "画面设置", Sleep);
                    mFairy.onTap(583,109,601,115, "画面设置默认", 1500);
                    mFairy.condit();

                    result = mFairy.findPic(829,342,875,379, "Settingselection.png");
                    if (result.sim < 0.8f) {
                        mFairy.onTap(848,357,854,364, "屏蔽技能", Sleep);
                    }
                    result = mFairy.findPic(822,389,882,438, "Settingselection.png");
                    if (result.sim < 0.8f) {
                        mFairy.onTap(851,409,855,413, "屏蔽灵兽召唤物", Sleep);
                    }


                    close(0);
                    setTaskEnd();
                   /* result = mFairy.findPic(563, 340, 750, 410, "Settingselection.png");
                    mFairy.onTap(0.9f, result, "画面设置勾选", Sleep);
                    result = mFairy.findPic(396, 234, 882, 465, "xiugai.png");
                    mFairy.onTap(0.8f, result, 763, 423, 801, 438, "确认修改贴图", Sleep);*/
                    /*if (result.sim > 0.8f) {
                        mFairy.killUserGame();
                    } else {
                        mFairy.condit();

                    }*/
                }
            }

        }.taskContent(mFairy, "设置中");
    }//设置

    public void retire() throws Exception {
        new GameUtil(mFairy) {


            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(15, 99)) return;
                result = mFairy.findPic("Openteam.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Contingenthurdles1.png");
                mFairy.onTap(0.9f, result, "切换到队伍栏", Sleep);

                result = mFairy.findPic("Openteam1.png");
                mFairy.onTap(0.9f, result, "打开队伍栏", Sleep);

                result = mFairy.findPic("teaminterface.png");
                mFairy.onTap(0.8f, result, 189, 627, 214, 644, "队伍界面离开队伍", Sleep);

                result = mFairy.findPic("Decideleave.png");
                mFairy.onTap(0.8f, result, 793, 421, 794, 422, "确定离开队伍", Sleep);
                if (result.sim > 0.8f) {
                    close(0);
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy, "退队中");
    }//退队

    public void zhaohun() throws Exception {
        new GameUtil(mFairy) {

            public void inOperation() throws Exception {
                result = mFairy.findPic("Over drawing.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("过图中"));
                    mFairy.initMatTime();
                    err = 0;
                }
                result = mFairy.findPic("taskbar.png");
                mFairy.onTap(0.8f, result, "任务栏", Sleep);
            }

            public void content_0() throws Exception {
                for (int i = 0; i < 2; i++) {
                    mFairy.condit();
                    result = mFairy.findPic(1096, 240, 1221, 343, new String[]{"fork5.png", "fork6.png"});
                    mFairy.onTap(0.8f, result, "物品叉", Sleep);

                    result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png"});
                    mFairy.onTap(0.7f, result, "叉子2", 2000);

                    result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork3.png", "fork4.png"});
                    mFairy.onTap(0.8f, result, "叉子1", 2000);
                }
                close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(10, 99)) return;


                result = mFairy.findPic(7, 185, 74, 454, "zhaohun.png");
                mFairy.onTap(0.7f, result, result.x + 20, result.y + 20, result.x + 21, result.y + 21, "err招魂", Sleep);
                if (result.sim > 0.7f) {
                    err = 0;
                }

                result = mFairy.findPic("Mapinterface.png");
                mFairy.onTap(0.8f, result, 1214, 37, 1215, 38, "地图界面", Sleep);

                result = mFairy.findPic("ganmaofengxian.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("敢冒风险"));
                    result1 = mFairy.findPic("zhaohunjiaoqian.png");
                    if (result1.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("招魂交钱"));
                        result = mFairy.findPic("ganmaofengxian.png");
                        mFairy.onTap(0.8f, result, 951, 103, 952, 104, "敢冒风险", Sleep);

                        result = mFairy.findPic(7, 185, 74, 454, "zhaohun.png");
                        mFairy.onTap(0.8f, result, 1197, 84, 1198, 85, "到招魂了", Sleep);
                        setTaskName(2);
                        return;
                    } else {
                        mFairy.onTap(0.8f, result, "敢冒风险刷新任务", 11000);
                    }
                }
                result = mFairy.findPic("shuaxin.png");
                mFairy.onTap(0.8f, result, 742, 425, 767, 436, "刷新任务", 11000);

            }

            public void content_2() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                mFairy.onTap(0.8f, result, "选择城市", Sleep);

                result = mFairy.findPic("Mapinterface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("地图界面"));
                    mFairy.onTap(153, 82, 154, 83, "选npc", 2000);
                    mFairy.onTap(166, 133, 167, 134, "选npc", 5000);
                }

                result = mFairy.findPic(923, 241, 1275, 719, "maitongwuchang.png");
                mFairy.onTap(0.8f, result, "买通无常", 5000);

                result = mFairy.findPic("huaqian.png");
                mFairy.onTap(0.8f, result, 793, 423, 794, 424, "确定花钱", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }

            }

        }.taskContent(mFairy, "土豪招魂中");
    }//土豪招魂

    public void zhaohun1() throws Exception {
        new GameUtil(mFairy) {

            public void inOperation() throws Exception {
                result = mFairy.findPic("Over drawing.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("过图中"));
                    mFairy.initMatTime();
                    err = 0;
                }
                result = mFairy.findPic("taskbar.png");
                mFairy.onTap(0.8f, result, "任务栏", Sleep);
            }

            public void content_0() throws Exception {
                for (int i = 0; i < 2; i++) {
                    mFairy.condit();
                    result = mFairy.findPic(1096, 240, 1221, 343, new String[]{"fork5.png", "fork6.png"});
                    mFairy.onTap(0.8f, result, "物品叉", Sleep);

                    result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png"});
                    mFairy.onTap(0.7f, result, "叉子2", 2000);

                    result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork3.png", "fork4.png"});
                    mFairy.onTap(0.8f, result, "叉子1", 2000);
                }
                result = mFairy.findPic("Hangup1.png");
                mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "关闭挂机", Sleep);
                setTaskName(1);
            }

            String str;

            public void content_1() throws Exception {
                if (overtime(10, 99)) return;

                result = mFairy.findPic(7, 185, 74, 454, "zhaohun.png");
                mFairy.onTap(0.7f, result, result.x + 20, result.y + 20, result.x + 21, result.y + 21, "err招魂", Sleep);

                if (result.sim > 0.7f) {
                    err = 0;
                }

                result = mFairy.findPic("Mapinterface.png");
                mFairy.onTap(0.8f, result, 1214, 37, 1215, 38, "地图界面", Sleep);

                result = mFairy.findPic("ganmaofengxian.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("普通招魂"));

                    for (int i = 0; i < 3; i++) {
                      /*  result1 = mFairy.findPic(465,438,801,480,"rendao.png");
                        if (result1.sim>0.8f){
                            LtLog.e(mFairy.getLineInfo("人道"));
                            str="人道";
                            break;
                        }*/
                        result1 = mFairy.findPic(465, 438, 801, 480, "chushengdao.png");
                        if (result1.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("畜生道"));
                            str = "畜生道";
                            break;
                        }

                        result1 = mFairy.findPic(465, 438, 801, 480, "diyudao.png");
                        if (result1.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("地狱道"));
                            str = "地狱道";
                            break;
                        }
                    }

                    if (result1.sim > 0.8f) {
                        result = mFairy.findPic("ganmaofengxian.png");
                        mFairy.onTap(0.8f, result, 951, 103, 952, 104, "敢冒风险", 2000);

                        result = mFairy.findPic(1127, 1, 1279, 33, "ymj.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("幽冥界"));

                            result = mFairy.findPic(7, 185, 74, 454, "zhaohun.png");
                            mFairy.onTap(0.7f, result, 1197, 84, 1198, 85, "招魂", Sleep);

                            setTaskName(2);
                            return;
                        } else {
                            setTaskName(3);
                            return;
                        }

                    } else {
                        mFairy.onTap(489, 540, 490, 541, "刷新任务", 15000);
                    }
                }

                result = mFairy.findPic("shuaxin.png");
                mFairy.onTap(0.8f, result, 742, 425, 767, 436, "刷新任务", 11000);
            }



            public void content_2() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(112, 35, 185, 115, "Mapinterface.png");
                mFairy.onTap(0.8f, result, "选择城市", Sleep);

                result = mFairy.findPic("Mapinterface.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("地图界面"));
                    mFairy.onTap(153, 82, 154, 83, "选npc", 2000);
                    mFairy.onTap(166, 133, 167, 134, "选npc", 5000);
                }

                result = mFairy.findPic(923, 241, 1275, 719, "maitongwuchang.png");
                mFairy.onTap(0.8f, result, 1116, 359, 1117, 360, "买通无常", 5000);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 387, 682, 388, 683, "点击好友", 3000);
                mFairy.onTap(0.8f, result, 1164, 167, 1165, 168, "切换到好友", Sleep);
                mFairy.onTap(0.8f, result, 1164, 167, 1165, 168, "切换到好友", Sleep);

                result = mFairy.findPic("haoyou.png");
                mFairy.onTap(0.9f, result, "切换到好友界面", Sleep);

                result = mFairy.findPic(103, 58, 516, 681, "qvElves.png");
                mFairy.onTap(0.8f, result, "倩女小精灵", Sleep);


                result = mFairy.findPic("qvrd.png");
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic("qvknow.png");
                    mFairy.onTap(0.8f, result1, 616, 411, 649, 422, "倩女知道", 3000);
                    mFairy.onTap(0.8f, result, 199, 636, 215, 642, "打开输入框", 5000);
                    mFairy.inputText(str);
                    mFairy.condit();
                    result = mFairy.findPic(1070, 68, 1274, 719, new String[]{"new_textsure.png", "new_textsure1.png"});
                    mFairy.onTap(0.8f, result, "确定文本", 2000);
                    mFairy.onTap(0.8f, result, 876, 635, 901, 646, "发送", Sleep);
                    setTaskName(4);
                }
            }

            public void content_4() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(347, 254, 1045, 540, new String[]{"zhpujiacun.png", "zhpujiacun1.png"});
                mFairy.onTap(0.8f, result, "蒲家村", Sleep);
                if (result.sim > 0.8f) {
                    for (int i = 0; i < 2; i++) {
                        mFairy.condit();
                        result = mFairy.findPic(1096, 240, 1221, 343, new String[]{"fork5.png", "fork6.png"});
                        mFairy.onTap(0.8f, result, "物品叉", Sleep);

                        result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png"});
                        mFairy.onTap(0.7f, result, "叉子2", 2000);

                        result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork3.png", "fork4.png"});
                        mFairy.onTap(0.8f, result, "叉子1", 2000);
                    }
                    setTaskName(5);
                }
            }

            public void content_5() throws Exception {
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 3) {
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    setTaskName(6);
                    close(0);
                    return;
                }
            }

            public void content_6() throws Exception {
                if (overtime(20, 0)) return;
                result = mFairy.findPic(1127, 1, 1279, 33, "ymj.png");
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("Hangup.png");
                mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "开启挂机", Sleep);

                result = mFairy.findPic(9, 189, 148, 446, "swlrw.png");
                mFairy.onTap(0.7f, result, "尚未领任务", Sleep);
                if (result.sim > 0.7f) {
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(7, 185, 74, 454, "zhaohun.png");
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("招魂中"));
                    err = 0;
                }
                if (timekeep(0, 1800000, "半个小时招魂重置")) {
                    setTaskName(0);
                    return;
                }
                Thread.sleep(3000);
            }
        }.taskContent(mFairy, "普通招魂中");
    }//普通招魂

    public void zh() throws Exception {
        if (AtFairyConfig.getOption("zh").equals("1")) {
            result = mFairy.findPic(1127, 1, 1279, 33, "ymj.png");
            result1 = mFairy.findPic(7, 185, 74, 454, "zhaohun.png");
            if (result.sim > 0.8f || result1.sim > 0.7f) {
                LtLog.e(mFairy.getLineInfo("幽冥界"));
                zhaohun1();
            }
        }
        if (AtFairyConfig.getOption("zh").equals("2")) {
            result = mFairy.findPic(1127, 1, 1279, 33, "ymj.png");
            result1 = mFairy.findPic(7, 185, 74, 454, "zhaohun.png");
            if (result.sim > 0.8f || result1.sim > 0.7f) {
                LtLog.e(mFairy.getLineInfo("幽冥界"));
                zhaohun();
            }
        }
    } //招魂

    public void apply() throws Exception {
        result = mFairy.findPic(new String[]{"Someone.png","Someone3.png"});
        mFairy.onTap(0.85f, result, 174, 150, 199, 167, "有人申请进队", 5000);

        result = mFairy.findPic("qklb.png");
        if (result.sim > 0.9f) {
            for (int i = 0; i < 5; i++) {
                result = mFairy.findPic("Pointpeople.png");
                mFairy.onTap(0.8f, result, "点人", Sleep);

                result = mFairy.findPic("Acceptteam.png");
                mFairy.onTap(0.8f, result, "接受", Sleep);
            }
            close(0);
        }
        result = mFairy.findPic("Pointpeople.png");
        mFairy.onTap(0.8f, result, "点人", Sleep);

        result = mFairy.findPic("Acceptteam.png");
        mFairy.onTap(0.8f, result, "接受", Sleep);
        if (result.sim > 0.8f) {
            close(0);
        }
    } //申请进队

    static boolean sb = true;

    public void gdFBTeam() throws Exception {
        if (AtFairyConfig.getTaskID().equals("1829")) {
            while (mFairy.condit()) {
                result = mFairy.findPic(813, 97, 1275, 709, new String[]{"Replica.png", "fuben.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("固定队跟队副本中"));
                    mFairy.initMatTime();
                    err = 0;
                    result = mFairy.findPic("Hangup.png");
                    mFairy.onTap(0.7f, result, 1236, 335, 1237, 336, "副本中开启挂机", Sleep);
                    result = mFairy.findPic(3, 184, 246, 447, new String[]{"Leftone.png", "Leftone1.png", "Leftone2.png", "Leftone3.png", "Leftone4.png"});
                    if (result.sim > 0.8f) {
                        if (!AtFairyConfig.getOption("ls").equals("") && GameUtil.sb) {
                            collarDouble();
                            GameUtil.sb = false;
                        }
                    }
                } else {
                    result = mFairy.findPic("Over drawing.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("过图中"));
                        mFairy.initMatTime();
                        err = 0;
                    } else {
                        break;
                    }
                }
            }
        }
    }// 固定队跟队进入副本的处理

    public void collarDouble() throws Exception {
        OtherGame.fuli = false;
        new TeamTask(mFairy) {
            public  void inOperation() throws Exception {
                result = mFairy.findPic("Over drawing.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("过图中"));
                    mFairy.initMatTime();
                    err = 0;
                }
                result = mFairy.findPic("taskbar.png");
                mFairy.onTap(0.8f, result, "任务栏", Sleep);
            }

            public void content_0() throws Exception {
                for (int i = 0; i < 2; i++) {
                    mFairy.condit();
                    result = mFairy.findPic(1096, 240, 1221, 343, new String[]{"fork5.png", "fork6.png"});
                    mFairy.onTap(0.8f, result, "物品叉", Sleep);

                    result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png"});
                    mFairy.onTap(0.7f, result, "叉子2", 2000);

                    result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork3.png", "fork4.png"});
                    mFairy.onTap(0.8f, result, "叉子1", 2000);
                }
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(10, 2)) return;

                result = mFairy.findPic(639, 6, 1126, 176, "fl.png");
                mFairy.onTap(0.8f, result, "福利", 3000);

                result = mFairy.findPic("rightdouble.png");
                mFairy.onTap(0.8f, result, "双倍经验", Sleep);

                result = mFairy.findPic("doubleinterface.png");
                mFairy.onTap(0.8f, result, 446, 93, 465, 104, "打开设置", 3000);
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("双倍经验界面"));
                    result = mFairy.findPic(370, 97, 634, 264, "NOgou.png");
                    mFairy.onTap(0.8f, result, 455, 178, 468, 186, "没有勾选", Sleep);
                    mFairy.onTap(446, 93, 465, 104, "勾选了关闭", Sleep);
                    result = mFairy.findPic("Doublecollection.png");
                    mFairy.onTap(0.8f, result, "领取", Sleep);
                    result = mFairy.findPic("shengyu.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("有剩余双倍结束"));
                    } else {
                        mFairy.onTap(516, 470, 550, 479, "没有剩余双倍使用一个", Sleep);
                    }
                    setTaskName(2);
                    return;
                } else {
                    result = mFairy.findPic("sanbei.png");
                    mFairy.onTap(0.8f, result, 446, 93, 465, 104, "打开设置", 3000);
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("三倍经验界面"));
                        if (AtFairyConfig.getOption("ls").equals("1")) {
                            result = mFairy.findPic(437, 174, 601, 239, "NOgou.png");
                            mFairy.onTap(0.8f, result, 458, 204, 470, 214, "没有勾选", Sleep);
                            mFairy.onTap(446, 93, 465, 104, "勾选了关闭", Sleep);
                            result = mFairy.findPic(434, 273, 829, 589, "Doublecollection.png");
                            mFairy.onTap(0.8f, result, "领取", Sleep);
                            result = mFairy.findPic(451, 148, 741, 234, "shengyu.png");
                            if (result.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("有剩余双倍结束"));
                            } else {
                                mFairy.onTap(516, 470, 550, 479, "没有剩余双倍使用一个", Sleep);
                            }
                        }
                        if (AtFairyConfig.getOption("ls").equals("2")) {
                            result = mFairy.findPic(436, 128, 605, 184, "NOgou.png");
                            mFairy.onTap(0.8f, result, 455, 152, 469, 163, "没有勾选", Sleep);
                            mFairy.onTap(446, 93, 465, 104, "勾选了关闭", Sleep);
                            mFairy.onTap(700, 89, 731, 104, "切换到3倍", Sleep);
                            result = mFairy.findPic(434, 273, 829, 589, "Doublecollection.png");
                            mFairy.onTap(0.8f, result, "领取", Sleep);
                            result = mFairy.findPic(451, 148, 741, 234, "shengyu.png");
                            if (result.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("有剩余双倍结束"));
                            } else {
                                mFairy.onTap(516, 470, 550, 479, "没有剩余双倍使用一个", Sleep);
                            }
                        }
                        setTaskName(2);
                        return;
                    }
                }
            }

            public void content_2() throws Exception {
                for (int i = 0; i < 2; i++) {
                    mFairy.condit();
                    result = mFairy.findPic(1096, 240, 1221, 343, new String[]{"fork5.png", "fork6.png"});
                    mFairy.onTap(0.8f, result, "物品叉", Sleep);

                    result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork2.png", "fork1.png"});
                    mFairy.onTap(0.7f, result, "叉子2", 2000);

                    result = mFairy.findPic(294, 5, 1275, 608, new String[]{"fork3.png", "fork4.png"});
                    mFairy.onTap(0.8f, result, "叉子1", 2000);
                }
                setTaskEnd();
            }
        }.taskContent(mFairy, "领双中");
        OtherGame.fuli = true;
    }//领双
}
