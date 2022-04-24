package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairy2;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/22 0022.
 */
public class Abnormal {
    private static final int MIN_TIME = 200;
    //异常处理
    AtFairyImpl mFairy;
    AtFairy2.OpencvResult result;

    FindResult findResult;

    private static long mLastCaptureTime;
    private long time = System.currentTimeMillis() / 1000, timex;
    private long time1 = System.currentTimeMillis() / 1000, time1x;
    private long time2 = System.currentTimeMillis() / 1000, time2x;
    private long time3 = System.currentTimeMillis() / 1000, time3x;
    private long time4 = System.currentTimeMillis() / 1000, time4x;
    private PublicFunction publicFunction;
    private GamePublicFunction gamePublicFunction;

    PicTime picTime;
    PicTime fork5_picTime;
    PicTime fork6_picTime;
    PicTime return_picTime;
    PicTime err5_picTime;

    long time5 = System.currentTimeMillis() / 1000, time5x;
    public Abnormal(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);

         picTime = new PicTime(876, 2, 989, 99, "home4.png", 0.8, mFairy);

         fork5_picTime = new PicTime(1150, 11, 1266, 105, "fork5.png", 0.8, mFairy);

         fork6_picTime = new PicTime(917, 85, 1066, 180, "fork6.png", 0.8, mFairy);

         return_picTime = new PicTime(1116, 0, 1280, 99, "return1.png|return.png", 0.8, mFairy);

         err5_picTime = new PicTime(560, 190, 718, 244, "err5.png", 0.8, mFairy);
    }


    public void erro() throws Exception {

        findResult =mFairy.findPic(384,252,752,428,"err6.png");
        mFairy.onTap(0.8f,findResult,484,459,527,481,"err6",1000);

        findResult =mFairy.findPic("ganxie.png");
        mFairy.onTap(0.8f,findResult,1166,93,1191,109,"ganxie",1000);

        findResult =mFairy.findPic("hdclose7.png");
        mFairy.onTap(0.8f,findResult,1222,74,1241,95,"hdclose7",1000);

        findResult =mFairy.findPic("hdclose12.png");
        mFairy.onTap(0.8f,findResult,1109,76,1118,91,"hdclose12",1000);

        findResult =mFairy.findPic(668,501,831,606,"hdclose11.png");
        mFairy.onTap(0.8f,findResult,1126,94,1142,110,"hdclose7",1000);

        findResult  = mFairy.findPic("hdclose9.png");
        mFairy.onTap(0.8f,findResult,1241,91,1262,115,"广告图9",1000);

        findResult  = mFairy.findPic("hdclose10.png");
        mFairy.onTap(0.8f,findResult,1066,104,1078,122,"广告图10",1000);

        findResult =mFairy.findPic(1174,27,1271,106,"hdclose8.png");
        mFairy.onTap(0.8f,findResult,"hdclose8",1000);


        findResult =mFairy.findPic(434,612,764,697,"hdclose13.png");
        mFairy.onTap(0.8f,findResult,1074,67,1093,85,"hdclose13",1000);

        findResult =mFairy.findPic(504,125,711,265,"bx.png");
        mFairy.onTap(0.8f,findResult,683,321,712,349,"",1000);
        mFairy.onTap(0.8f,findResult,803,35,819,53,"关闭宝箱弹框",5000);

        findResult = mFairy.findPic("zn.png");
        mFairy.onTap(0.8f,findResult,1148,23,1172,29,"子女 - 关闭",1000);

        findResult = mFairy.findPic("zn1.png");
        mFairy.onTap(0.8f,findResult,1089,60,1101,75,"子女 - 关闭1",1000);

        findResult = mFairy.findPic("qian1.png");
        mFairy.onTap(0.8f,findResult,950,70,962,89,"要签",1000);

        findResult = mFairy.findPic("jaingli.png");
        if(findResult.sim>0.8f){
            mFairy.onTap(0.8f,findResult,601,350,685,399,"",1000);
            mFairy.onTap(0.8f,findResult,803,35,819,53,"关闭宝箱弹框",5000);
        }

        findResult =mFairy.findPic("close2.png");
        mFairy.onTap(0.8f,findResult,1204,44,1227,62,"close2",1000);

        result = publicFunction.localToValueFindPic(1031, 240, 1095, 306, 120.0, 255.0, 0, "fork.png");
        if (result.sim >= 0.8) {


            timex = System.currentTimeMillis() / 1000 - time;

            if(timex%8==0) {
                LtLog.i(publicFunction.getLineInfo() + "异常关叉计时(1) ：" + timex);
            }

            int outTime = 60;
            //在仓库 储存 需要2分钟，超时加长
            AtFairy2.OpencvResult result1 = publicFunction.localFindPic(283, 64, 488, 149, "package2.png");
            if (result1.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------package2>" + result1);
                outTime = 120;
            }

            result = publicFunction.localToValueFindPic(1031, 240, 1095, 306, 120.0, 255.0, 0, "fork.png");
            LtLog.i(publicFunction.getLineInfo() + "******Abnormal--fork>" + result + ",timex=" + timex + ",outTime=" + outTime);
            if (timex >= outTime && result.sim >= 0.8) {
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(200);
                time = System.currentTimeMillis() / 1000;
            }

        } else {
            time = System.currentTimeMillis() / 1000;
        }

        if (picTime.getPicTime() >= 60) {
            publicFunction.rndTap(917, 34, 946, 55);
            Thread.sleep(1000);
        }


        result = publicFunction.localFindPic(415, 296, 856, 375, "voice.png");
        if (result.sim >= 0.8) {
            publicFunction.rndTap(483, 462, 529, 482);//点取消
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(1157, 30, 1238, 157, "fork1.png");
        if (result.sim >= 0.8) {
            time1x = System.currentTimeMillis() / 1000 - time1;

            if(time1x%8==0) {

                LtLog.i(publicFunction.getLineInfo() + "异常关叉计时(2) ：" + time1x);
            }

            long maxtime1 = 30;
            AtFairy2.OpencvResult result1 = publicFunction.localFindPic(582, 8, 721, 80, "activitiesWindow.png"+"|"+"activitiesWindow1.png|activitiesWindow2.png");
            if (result1.sim >= 0.8) {
                maxtime1 = 230;
                LtLog.i(publicFunction.getLineInfo() + "******Abnormal--activitiesWindow>" + result1 + "maxtime1=" + maxtime1);
                //如果在活动窗口，330S后再关闭
            }
            result1 = publicFunction.localFindPic(617, 13, 702, 71, "team.png");
            if (result1.sim >= 0.8) {
                maxtime1 = 230;
                LtLog.i(publicFunction.getLineInfo() + "******Abnormal--activitiesWindow>" + result1 + "maxtime1=" + maxtime1);
                //如果在组队窗口，330S后再关闭
            }
            result1 = publicFunction.localFindPic(618, 17, 693, 61, "examination.png");
            if (result1.sim >= 0.8) {
                maxtime1 = 230;
                LtLog.i(publicFunction.getLineInfo() + "******Abnormal--activitiesWindow>" + result1 + ",maxtime1=" + maxtime1);
                //如果在科举窗口，330S后再关闭
            }
            if (time1x >= maxtime1) {
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                time1 = System.currentTimeMillis() / 1000;
                Thread.sleep(200);
            }
        } else {
            time1 = System.currentTimeMillis() / 1000;
        }

        result = publicFunction.localToValueFindPic(408, 295, 583, 334, 60, 255, 1, "accumulativeOnline.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******Abnormal--accumulativeOnline>" + result);
            publicFunction.rndTap(616, 459, 662, 481);
            Thread.sleep(500);
        }

        result = publicFunction.localToValueFindPic(408, 295, 583, 334, 60, 255, 1, "accumulativeOnline1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******Abnormal--accumulativeOnline1>" + result);
            publicFunction.rndTap(564, 447, 708, 503);
            Thread.sleep(500);
        }


        //860,491, 928,537
        result = publicFunction.localFindPic(860, 491, 928, 537, "use.png");
        if (result.sim >= 0.8) {
            //使用道具
            LtLog.i(publicFunction.getLineInfo() + "*******************Abnormal--use>" + result);
            for (int i = 0; i <= 10; i++) {
                String[] picstr = new String[29];
                for (int j = 0; j < picstr.length; j++) {
                    //LtLog.i("******Abnormal--openBox1,,j=>" + j);
                    picstr[j] = "openBox" + (j + 1) + ".png";
                    //    LtLog.i("******Abnormal--openBox1>" + picstr[j]);
                }

                AtFairy2.OpencvResult result1 = publicFunction.localFindManyPic(814, 406, 978, 459, picstr);
                if (result1.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "******Abnormal--use>" + result);
                    publicFunction.rndTapWH(result.x, result.y, 43, 22);
                    Thread.sleep(2000);
                    time2 = System.currentTimeMillis() / 1000;
                }
                result1 = publicFunction.localFindPic(519, 200, 759, 249, "inputQuantity.png");
                if (result1.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "******Abnormal--inputQuantity>" + result);
                    publicFunction.rndTap(615, 466, 665, 488);
                    Thread.sleep(2000);
                }
                result = publicFunction.localFindPic(860, 491, 928, 537, "use.png");
                if (result.sim < 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "******Abnormal--use>" + result);
                    break;
                }
            }
            result = publicFunction.localFindPic(860, 491, 928, 537, "use.png");
            if (result.sim >= 0.8) {
                publicFunction.rndTap(976, 279, 997, 299); //不在列可使用道具的表内 关闭
                Thread.sleep(1000);
            }
        }
        result = publicFunction.localFindPic(519, 200, 759, 249, "inputQuantity.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******Abnormal--inputQuantity>" + result);
            publicFunction.rndTap(615, 466, 665, 488);
            Thread.sleep(2000);
        }
        result = publicFunction.localFindPic(534, 122, 730, 198, "chat.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******Abnormal--chat>" + result);
            publicFunction.rndTap(930, 153, 949, 174);
            Thread.sleep(2000);
        }
        result = publicFunction.localToValueFindPic(967, 269, 1007, 316, 120.0, 255.0, 0, "fork2.png");
        //  LtLog.i("******Abnormal--fork2>=" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******Abnormal--fork2>" + result);
            time2x = System.currentTimeMillis() / 1000 - time2;
            LtLog.i(publicFunction.getLineInfo() + "******Abnormal--time2x>" + time2x);
            if (time2x >= 60) {
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(200);
                time2 = System.currentTimeMillis() / 1000;
            }
        } else {
            time2 = System.currentTimeMillis() / 1000;
        }
        result = publicFunction.localFindPic(660, 108, 743, 159, "condition.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******Abnormal--condition>" + result);
            time4x = System.currentTimeMillis() / 1000 - time4;
            if (time4x >= 60) {
                publicFunction.rndTap(952, 127, 964, 138);
                Thread.sleep(200);
            }
        } else {
            time4 = System.currentTimeMillis() / 1000;
        }
//            result = publicFunction.localToValueFindPic(1039, 2, 1120, 42, 220, 255, 0, "arrow.png");
//            if (result.sim >= 0.8) {
//                LtLog.i(publicFunction.getLineInfo() + "******Abnormal--arrow>" + result);
//                publicFunction.rndTapWH(result.x, result.y, 5, 5);
//                Thread.sleep(500);
//            }

        result = publicFunction.localFindPic(466, 103, 836, 231, "dsp_set.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******Abnormal--dsp_set>" + result);
            publicFunction.rndTap(960, 164, 977, 184);
            Thread.sleep(2000);
        }


        result = publicFunction.localFindPic(1051, 103, 1115, 158, "displaySet.png");
        if (result.sim >= 0.8) {
            //显示设置存在，并且没有活动按钮，点击箭头
            result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
            if (result.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "显示设置存在，并且没有活动按钮，点击箭头");
                publicFunction.rndTap(1070, 12, 1089, 25);
                Thread.sleep(2000);
            }
        }

        result = publicFunction.localToValueFindPic(4, 183, 46, 246, 220, 255, 0, "arrow1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******Abnormal--arrow1>" + result);
            publicFunction.rndTapWH(result.x, result.y, 5, 5);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(593, 264, 733, 333, "exceed3000.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******Abnormal--exceed3000>" + result + ",time3x=" + time3x);
            time3x = System.currentTimeMillis() / 1000 - time3;
            if (time3x >= 30) {
                publicFunction.rndTap(609, 454, 671, 490);
                Thread.sleep(500);
            }
        } else {
            time3 = System.currentTimeMillis() / 1000;
        }
        result = publicFunction.localFindPic(561, 92, 730, 151, "invitingFriends.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******invitingFriends=" + result);
            publicFunction.rndTap(923, 112, 942, 135);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(462, 276, 602, 341, "lazyWeight.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******lazyWeight=" + result);
            publicFunction.rndTap(567, 418, 579, 432);
            Thread.sleep(1000);
            publicFunction.rndTap(880, 219, 902, 236);
            Thread.sleep(1000);
        }

        result = publicFunction.localFindPic(550, 102, 728, 161, "system.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******system=" + result);
            publicFunction.rndTap(1021, 130, 1038, 147);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(594, 74, 719, 129, "book1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******book1=" + result);
            publicFunction.rndTap(1098, 98, 1114, 118);
            Thread.sleep(3000);
        }

        result = publicFunction.localFindPic(9, 7, 129, 587, "world.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******world=" + result);
            publicFunction.rndTap(575, 316, 585, 340);
            Thread.sleep(500);
        }
        List<String> listRegion = new ArrayList<>();
        List<String> listPic = new ArrayList<>();
        listRegion.add("769,450,850,496");
        listPic.add("out.png");
        publicFunction.localMultiRegionFindManyPic(listRegion, listPic);
        result = publicFunction.localFindPic(1092, 358, 1163, 432, "fullPackage.png");
        if (result.sim >= 0.8) {
            //背包已满
            //return 639;
            LtLog.i(publicFunction.getLineInfo() + "******fullPackage=" + result);
            new TaskMain(mFairy).UpState(639);
            Thread.sleep(20000);
        }
        result = publicFunction.localFindPic(503, 299, 634, 374, "experience2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******experience2=" + result);
            publicFunction.rndTap(490, 462, 522, 482);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(428, 268, 822, 405, "newCaptain.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******newCaptain=" + result);
            publicFunction.rndTap(483, 458, 523, 482);//点取消
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(408, 278, 805, 391, "pop1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******pop1=" + result);
            publicFunction.rndTap(752, 462, 789, 484);//点重试
            Thread.sleep(1000);
        }
        ///登陆相关
        result = publicFunction.localFindPic(18, 74, 156, 223, "system1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******system1=" + result);
            publicFunction.rndTap(598, 571, 687, 607);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(371,537,904,673, "login2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "登录界面 进入游戏" + result);

            findResult = mFairy.findPic("gou.png");
            mFairy.onTap(0.8f,findResult,"打勾",1000);

            findResult = mFairy.findPic("gou1.png");
            mFairy.onTap(0.8f,findResult,"打勾",1000);

            findResult = mFairy.findPic(153,651,316,712,"gou2.png");
            mFairy.onTap(0.8f,findResult,"打勾",1000);

            publicFunction.rndTapWH(result.x, result.y, 116, 27);
            Thread.sleep(500);
        }


        result = publicFunction.localFindPic(1000, 614, 1269, 698, "login1.png");
        if (result.sim >= 0.8) {
            time5x = System.currentTimeMillis() / 1000 - time5;
            LtLog.i(publicFunction.getLineInfo() + "选角色界面 进入游戏:" + time5);
            if (time5x >= 60) {
                publicFunction.rndTapWH(result.x, result.y, 116, 27);
                Thread.sleep(500);
            }
        } else {
            time5 = System.currentTimeMillis() / 1000;
        }



        result = publicFunction.localFindPic(514, 274, 757, 374, "patriarch.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******patriarch=" + result);
            publicFunction.rndTap(936, 101, 959, 125); //关闭
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(706, 538, 910, 678, "QQ.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******QQ=" + result);
            publicFunction.rndTapWH(result.x, result.y, 34, 40);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(538, 49, 658, 116, "notice.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******notice=" + result);
            publicFunction.rndTap(1215, 146, 1230, 158);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(476, 285, 644, 340, "update.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******update=" + result);
            publicFunction.rndTap(747, 461, 796, 486);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(383, 281, 647, 384, "offline1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******offline1=" + result);
            publicFunction.rndTap(616, 462, 667, 490);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(447, 284, 765, 384, "offline2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******offline2=" + result);
            publicFunction.rndTap(477, 456, 538, 485);//点取消
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(547, 133, 731, 201, "uiset.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******uiset=" + result);//
            publicFunction.rndTap(476, 486, 503, 495);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(416, 594, 581, 650, "hd1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******hd1=" + result);
            publicFunction.rndTap(1004, 54, 1011, 64);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(505, 93, 751, 282, "tishi.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******hd1=" + result);
            result = publicFunction.localFindPic(567, 400, 709, 510, "ok.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "******ok=" + result);
                publicFunction.rndTapWH(result.x, result.y, 5, 5);
                Thread.sleep(500);
            }
        }


        result = publicFunction.localFindPic(656, 198, 859, 285, "nn.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******nn=" + result);
            publicFunction.rndTap(964, 470, 988, 482);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(56, 395, 299, 557, "nn1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******nn1=" + result);//
            publicFunction.rndTap(614, 468, 650, 481);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(602, 318, 746, 407, "nn2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******nn2=" + result);//
            publicFunction.rndTap(610, 466, 650, 477);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(602, 318, 746, 407, "nn2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******nn2=" + result);//
            publicFunction.rndTap(610, 466, 650, 477);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(624, 567, 726, 630, "yiyuan.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******yiyuan=" + result);//
            publicFunction.rndTap(1139, 97, 1152, 112);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(725, 488, 881, 564, "jiang.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******jiang=" + result);//
            publicFunction.rndTap(1033, 174, 1052, 183);
            Thread.sleep(500);
        }


        result = publicFunction.localFindPic(388, 276, 684, 373, "networkError.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******networkError=" + result);
            publicFunction.rndTap(610, 461, 657, 484);//点确认
            Thread.sleep(1000);
        }

        result = publicFunction.localFindPic(286, 744, 500, 1076, "Land.png|Land1.png|Land2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******Land=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(491, 614, 757, 691, "startUp1.png|startUp.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******startUp=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(617, 184, 801, 255, "agreement.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******agreement=" + result);
            publicFunction.rndTap(747, 463, 798, 482);//点同意
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(371, 264, 601, 345, "noDrug.png");
        if (result.sim >= 0.75) {
            LtLog.i(publicFunction.getLineInfo() + "******noDrug=" + result);
            publicFunction.rndTap(555, 408, 32, 34);
            Thread.sleep(1000);
            publicFunction.rndTap(876, 209, 30, 36);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(18, 74, 156, 223, "system1-1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******system1-1=" + result);
            publicFunction.rndTap(618, 577, 663, 602);//点确认
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(536, 318, 621, 374, "onLine1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******onLine1=" + result);
            publicFunction.rndTap(751, 460, 788, 482);//点确认
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(392, 279, 888, 419, "security.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******security=" + result);
            publicFunction.rndTap(753, 463, 783, 480);//点确认
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(392, 279, 888, 419, "NetworkAnomaly.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******NetworkAnomaly=" + result);
            publicFunction.rndTap(742, 455, 796, 492);//点重试
            Thread.sleep(500);
        }


        result = publicFunction.localFindPic(546, 92, 739, 180, "activity_remind.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******activity_remind=" + result);
            publicFunction.rndTap(1024, 129, 1035, 147);////关闭活动提醒
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(256, 14, 349, 99, "fork4.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******fork4=" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 20, result.y + 10);////关闭活动提醒
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(611, 292, 848, 371, "err1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******err1=" + result);
            publicFunction.rndTap(471, 460, 534, 486);////取消
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(611, 292, 848, 371, "err1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******err1=" + result);
            publicFunction.rndTap(471, 460, 534, 486);////取消
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(390, 274, 571, 334, "err2.png|err3.png");
        if (result.sim >= 0.75) {
            LtLog.i(publicFunction.getLineInfo() + "******err2=" + result);
            publicFunction.rndTap(567, 419, 577, 428);////打钩
            Thread.sleep(1000);
            publicFunction.rndTap(883, 213, 902, 235);////点叉
            Thread.sleep(1000);
        }

        result = publicFunction.localFindPic(490, 14, 797, 120, "err4.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******err2=" + result);
            publicFunction.rndTap(575, 603, 677, 631);////点击确认
            Thread.sleep(1000);

        }
        result = publicFunction.localFindPic(389, 296, 603, 375, "error1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******error1=" + result);
            publicFunction.rndTap(609, 461, 664, 484);////点击确认
            Thread.sleep(1000);

        }
        result = publicFunction.localFindPic(608, 78, 696, 123, "hero.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "******error1=" + result);
            publicFunction.rndTap(1100, 95, 1114, 114);////关闭弹出，由于卡引起的bug
            Thread.sleep(20000);//没错，就是20s
        }

        result = publicFunction.localFindPic(1163, 15, 1273, 103, "yin_receivefork.png");
        if (result.sim >= 0.8) {
            result = publicFunction.localFindPic(228, 387, 877, 464, "yin_receive.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "******yin_receive=" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 2, result.y + 2);////7天领取 2月28号结束可以删掉
                Thread.sleep(500);
            } else {
                LtLog.i(publicFunction.getLineInfo() + "******yin_receive=" + result);
                publicFunction.rndTap(1226, 49, 1237, 59);////7天领取 2月28号结束可以删掉
                Thread.sleep(500);
            }
        }
        if (fork5_picTime.getPicTime() > 120) {
            LtLog.i(    publicFunction.getLineInfo() + "**********fork5_picTime > 120->");
            int x = fork5_picTime.getPicX();
            int y = fork5_picTime.getPicY();
            LtLog.i(publicFunction.getLineInfo() + "**********fork5_picTime > 120->" + ",x=" + x + ",y=" + y);
            publicFunction.rndTap(x, y, x + 10, y + 10);
            Thread.sleep(1000);
            fork5_picTime.resetTime();
        }
        if (fork6_picTime.getPicTime() > 120) {
            LtLog.i(publicFunction.getLineInfo() + "**********fork6_picTime > 120->");
            int x = fork6_picTime.getPicX();
            int y = fork6_picTime.getPicY();
            LtLog.i(publicFunction.getLineInfo() + "**********fork6_picTime > 120->" + ",x=" + x + ",y=" + y);
            publicFunction.rndTap(x, y, x + 10, y + 10);
            Thread.sleep(1000);
            fork6_picTime.resetTime();
        }
        if (return_picTime.getPicTime() > 30) {
            LtLog.i(publicFunction.getLineInfo() + "**********return_picTime > 30->");
            int x = return_picTime.getPicX();
            int y = return_picTime.getPicY();
            LtLog.i(publicFunction.getLineInfo() + "**********return_picTime > 30->" + ",x=" + x + ",y=" + y);
            publicFunction.rndTap(x, y, x + 10, y + 10);
            Thread.sleep(1000);
            return_picTime.resetTime();
        }
        if (err5_picTime.getPicTime() > 30) {
            LtLog.i(publicFunction.getLineInfo() + "**********err5_picTime > 30->");
            publicFunction.rndTap(881, 212, 903, 237);
            Thread.sleep(1000);
            err5_picTime.resetTime();
        }

        Thread.sleep(1000);

        findResult = mFairy.findPic("fanhui.png");
        mFairy.onTap(0.8f,findResult,"返回游戏",1000);

        findResult = mFairy.findPic("jiazhang.png");
        mFairy.onTap(0.8f,findResult,1014,66,1036,88,"家长模式",1000);

        findResult = mFairy.findPic(new String[]{"close1.png","hdclose1.png","hdclose2.png"});
        mFairy.onTap(0.8f,findResult,"活动弹框- 关闭",1000);

        findResult = mFairy.findPic("hdclose3.png");
        mFairy.onTap(0.8f,findResult,1140,80,1158,97,"幸运商店 - 关闭",1000);

        findResult = mFairy.findPic("hdclose4.png");
        mFairy.onTap(0.8f,findResult,"活动 - 关闭",1000);

        findResult = mFairy.findPic("hdclose5.png");
        mFairy.onTap(0.8f,findResult,1145,59,1160,80,"活动 - 关闭",1000);

        findResult = mFairy.findPic(new String[]{"hdok.png","hdok1.png"});
        mFairy.onTap(0.8f,findResult,"活动 - 确定",1000);

        findResult = mFairy.findPic("hdqx.png");
        mFairy.onTap(0.8f,findResult,"活动 - 取消",1000);

        findResult = mFairy.findPic("hdclose.png");
        mFairy.onTap(0.8f,findResult,"活动 - 关闭",1000);

        findResult = mFairy.findPic("hdclose6.png");
        mFairy.onTap(0.8f,findResult,1224,40,1242,61,"活动 - 关闭",1000);

    }


}