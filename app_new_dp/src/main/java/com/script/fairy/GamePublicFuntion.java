package com.script.fairy;

import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.framework.AtFairyImpl;

public class GamePublicFuntion {
    public AtFairyImpl mFairy;
    public FindResult result;
    public long time = System.currentTimeMillis();

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        sbyh =0;
        mFairy = ypFairy;
    }

    //入队申请
    void rdsq() throws Exception {


        if (AtFairyConfig.getOption("rd1").equals("1")) {

            result = mFairy.findPic(114, 192, 216, 288, "gou.png");
            if (result.sim > 0.8f) {
                return;
            } else {
                mFairy.onTap(151, 237, 157, 242, "所有人", 1000);
            }

        }

        if (AtFairyConfig.getOption("rd2").equals("1")) {
            result = mFairy.findPic(287, 211, 351, 268, "gou.png");
            if (result.sim < 0.8f) {
                mFairy.onTap(314, 237, 326, 247, "队长好友", 1000);
            }
        } else {
            result = mFairy.findPic(287, 211, 351, 268, "gou.png");
            mFairy.onTap(0.8f, result, "关闭队长好友", 1000);
        }

        if (AtFairyConfig.getOption("rd3").equals("1")) {
            result = mFairy.findPic(467, 208, 529, 272, "gou.png");
            if (result.sim < 0.8f) {
                mFairy.onTap(495, 240, 501, 245, "队员好友", 1000);
            }
        } else {
            result = mFairy.findPic(467, 208, 529, 272, "gou.png");
            mFairy.onTap(0.8f, result, "关闭队员好友", 1000);
        }


        if (AtFairyConfig.getOption("rd4").equals("1")) {
            result = mFairy.findPic(644, 205, 720, 270, "gou.png");
            if (result.sim < 0.8f) {
                mFairy.onTap(676, 236, 684, 242, "队长家族", 1000);
            }
        } else {
            result = mFairy.findPic(644, 205, 720, 270, "gou.png");
            mFairy.onTap(0.8f, result, "关闭队长家族", 1000);
        }


        if (AtFairyConfig.getOption("rd5").equals("1")) {
            result = mFairy.findPic(872, 203, 937, 271, "gou.png");
            if (result.sim < 0.8f) {
                mFairy.onTap(899, 236, 908, 243, "队员家族", 1000);
            }
        } else {
            result = mFairy.findPic(872, 203, 937, 271, "gou.png");
            mFairy.onTap(0.8f, result, "关闭队员家族", 1000);
        }

    }

    //转让队长
    void zrdz() throws Exception {


        if (AtFairyConfig.getOption("zr1").equals("1")) {
            result = mFairy.findPic(123,348,199,427, "gou.png");
            if (result.sim <0.8f) {
                mFairy.onTap(150,385,157,391, "转让所有人", 1000);
            }

        } else {
            result = mFairy.findPic(123,348,199,427, "gou.png");
            mFairy.onTap(0.8f, result,  "关闭转让所有人", 1000);
        }

        if (AtFairyConfig.getOption("zr2").equals("1")) {
            result = mFairy.findPic(282,345,364,423, "gou.png");
            if (result.sim < 0.8f) {
                mFairy.onTap(315,382,323,393, "陌生人", 1000);
            }
        } else {
            result = mFairy.findPic(282,345,364,423, "gou.png");
            mFairy.onTap(0.8f, result, "关闭陌生人", 1000);
        }
    }


    public void skip() throws Exception {
        result = mFairy.findPic("skip.png");
        mFairy.onTap(0.85f, result, "跳过", 1000);

        result = mFairy.findPic("skip1.png");
        mFairy.onTap(0.85f, result, 879, 507, 880, 510, "跳过-确定", 1000);
    }//跳过

    public void ranksSetUp(int type) throws Exception {
        switch (type) {
            case 1:
                result = mFairy.findPic("ranks2.png");
                mFairy.onTap(0.9f, result, "召集", 1000);
                break;
            case 2:
                result = mFairy.findPic("ranks3.png");
                mFairy.onTap(0.9f, result, "跟随", 1000);
                break;
            case 3:
                result = mFairy.findPic("ranks4.png");
                mFairy.onTap(0.9f, result, "取消跟随", 1000);
                break;
        }
    }//队伍设置

    public boolean isJiaZu() throws Exception {
        result = mFairy.findPic(1094, 0, 1273, 29, "jiazu.png");
        if (result.sim > 0.85f) {
            return true;
        }
        return false;
    }

    public void goSecurity()throws Exception {
        while (mFairy.condit()) {
            result = mFairy.findPic("UImap.png");
            if (result.sim > 0.85f) {
                mFairy.onTap(1139, 635, 1152, 651, "回城", 5000);
                init();
                return;
            } else {
                init();
                result = mFairy.findPic("package.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(1245, 31, 1257, 45, "点击地图", 3000);
                }
            }

        }
    }

    public void initGo() throws Exception {
        while (mFairy.condit()) {
            result = mFairy.findPic("UImap.png");
            if (result.sim > 0.85f) {
                if(map(1)){
                    mFairy.onTap(559, 495, 560, 500, "确定", 10000);
                    return;
                }
            } else {
                init();

                result = mFairy.findPic("package.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(1245, 31, 1257, 45, "点击地图", 3000);
                }
            }

        }

    }

    public void qx() throws Exception {
        result = mFairy.findPic(303, 365, 525, 619, "errNo.png");
        mFairy.onTap(0.85f, result, "取消", 1000);
    }//取消

    public void battle(int type) throws Exception {
        for (int i = 0; i < 2; i++) {

            switch (type) {
                case 0:
                    result = mFairy.findPic("findWay.png");
                    if (result.sim > 0.8f) {
                        return;
                    }

                    if (i == 1) {
                        result = mFairy.findPic("auto.png");
                        mFairy.onTap(0.95f, result, "自动战斗", 3000);

                    }
                    break;
                case 1:
                    result = mFairy.findPic("auto.png");
                    mFairy.onTap(0.95f, result, "自动战斗", 1000);
                    return;
            }
        }
    }//自动战斗

    public void battleEnd() throws Exception {
        init();
        for (int i = 0; i < 20; i++) {
            result = mFairy.findPic("battleH.png");
            mFairy.onTap(0.94f, result, "关闭自动战斗", 1500);
        }
    }//结束战斗

    public void task() throws Exception {

        result = mFairy.findPic("task.png");
        mFairy.onTap(0.96f, result, "任务", 1500);

    }//点击任务

    public void chat() throws Exception {

        for (int i = 0; i < 10; i++) {
            result = mFairy.findPic("chat.png");
            if (result.sim > 0.92f) {
                mFairy.onTap(0.92f, result, 1169, 613, 1170, 614, "聊天中...", 2200);
            } else {
                break;
            }
        }

        result = mFairy.findPic("novice3.png");
        mFairy.onTap(0.9f, result, "聊天中...", 1000);

    }//聊天

    public void close() throws Exception {
        int x = 40;
        int close = 1;
        int err = 0;

        while (mFairy.condit()) {

            result = mFairy.findPic("close4.png");
            mFairy.onTap(0.85f, result, 890, 227, 891, 228, "close4", 1000);

            result = mFairy.findPic("chatBox.png");
            mFairy.onTap(0.9f, result, "err聊天框", 1000);

            result = mFairy.findPic(668, 4, 1278, 479, new String[]{"close1.png", "close2.png", "close3.png", "close5.png", "close6.png", "close8.png"});
            if (result.sim > 0.88f) {
                switch (err) {
                    case 0:
                        mFairy.onTap(0.88f, result, "关闭窗口", 1000);
                        err++;
                        break;

                    default:
                        mFairy.onTap(0.88f, result, "关闭窗口", 1000);

                        result = mFairy.findPic(700, 0, 879 + (close * x), 400, new String[]{"close1.png", "close2.png"});
                        if (result.sim > 0.88f) {
                            mFairy.onTap(0.88f, result, "关闭窗口", 1500);
                            close = 1;
                        } else {
                            close++;
                            if (close > 10) {
                                close = 1;
                            }
                        }

                        break;
                }

            } else {
                break;
            }


        }
    }//关闭窗口

    public boolean unableFindWay() throws Exception {
        for (int i = 0; i < 10; i++) {
            result = mFairy.findPic(567, 246, 608, 350, "err.png");
            if (result.sim > 0.72f) {
                return true;
            }
        }
        return false;
    }//无法自动寻路

    public void err() throws Exception {
        for (int i = 0; i < 20; i++) {
            result = mFairy.findPic("ka1.png");
            if (result.sim > 0.85f) {
                mFairy.onTap(0.85f, result, "脱离卡死", 1500);
            } else {
                result = mFairy.findPic("ka.png");
                mFairy.onTap(0.9f, result, "下方缩放栏", 2000);
            }

            result = mFairy.findPic(399, 261, 863, 392, "gui.png");
            if (result.sim > 0.85f) {
                mFairy.onTap(840, 491, 873, 516, "确定", 3500);
                return;
            }
        }
    }//脱离卡死

    static int sbyh = 0;

    public void init() throws Exception {
        while (mFairy.condit()) {
            LtLog.e("init");
            close();
            chat();
            qx();

            result = mFairy.findPic("chatBox.png");
            mFairy.onTap(0.9f, result, "err聊天框", 1000);

            result = mFairy.findPic("yhEnd.png");
            mFairy.onTap(0.9f, result, "暂且休息", 1000);

            result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
            mFairy.onTap(0.8f, result, "安全复活", 1000);

            result = mFairy.findPic("msxsEnd1.png");
            mFairy.onTap(0.85f, result, 1148, 594, 1150, 600, "挑战成功!", 1000);

            result = mFairy.findPic("blsEnd.png");
            mFairy.onTap(0.9f, result, 626, 686, 628, 688, "火能捕猎赛init1", 1000);

            result = mFairy.findPic("blsEnd1.png");
            mFairy.onTap(0.9f, result, 1162, 605, 1165, 607, "火能捕猎赛init2", 1000);

            result = mFairy.findPic("dsyjEnd.png");
            mFairy.onTap(0.85f, result, 1166, 610, 1168, 612, "斗圣遗迹init", 1000);

            result = mFairy.findPic("fdzEnd.png");
            mFairy.onTap(0.85f, result, 1167, 677, 1168, 680, "中州浮岛战init", 1000);

            result = mFairy.findPic("gb.png");
            mFairy.onTap(0.9f, result, 1148, 588, 1150, 590, "点击空白处关闭", 1000);

            result = mFairy.findPic(563, 602, 680, 693, "dian.png");
            mFairy.onTap(0.8f, result, 954, 585, 972, 599, "点击空白处关闭", 1000);

            result = mFairy.findPic(518, 570, 769, 714, "myqqEnd.png");
            mFairy.onTap(0.85f, result, "魔猿抢亲init1", 1000);

            result = mFairy.findPic("jzlyEnd.png");
            mFairy.onTap(0.85f, result, 1171, 597, 1173, 600, "家族炼药init1", 1000);

            result = mFairy.findPic("jzlyEnd1.png");
            mFairy.onTap(0.85f, result, 1164, 606, 1165, 608, "家族炼药init2", 1000);

            result = mFairy.findPic("jzfzEnd.png");
            mFairy.onTap(0.85f, result, 1171, 597, 1175, 600, "家族纷争init", 1000);

            result = mFairy.findPic("jzfzEnd.png");
            mFairy.onTap(0.85f, result, 1171, 597, 1175, 600, "盟主争霸init", 1000);

            result = mFairy.findPic("tmslEnd.png");
            mFairy.onTap(0.85f, result, 1171, 597, 1175, 600, "天墓试炼init", 1000);

            result = mFairy.findPic(new String[]{
                    "sszEnd.png", "sszEnd1.png"});
            mFairy.onTap(0.85f, result, 1172, 691, 1175, 700, "联盟圣兽战init", 1000);

            result = mFairy.findPic("dqcEnd.png");
            mFairy.onTap(0.85f, result, 1158, 617, 1160, 620, "联盟斗气车init", 1000);

            result = mFairy.findPic(489, 601, 789, 716, new String[]{
                    "jx.png", "jx1.png"
            });
            mFairy.onTap(0.82f, result, 1148, 588, 1150, 590, "继续", 1000);

            result = mFairy.findPic("chatBox.png");
            mFairy.onTap(0.9f, result, "err聊天框", 1000);

            result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
            if (result.sim > 0.85f) {
                if(sbyh==1){
                    LtLog.e(mFairy.getLineInfo("在副本中呢"));
                    battle(1);
                    result = mFairy.findPic("task1.png");
                    mFairy.onTap(0.85f, result, 55, 210, 126, 219, "副本点击任务", 10000);
                }else{
                    mFairy.onTap(0.85f, result, "离开", 3000);
                    mFairy.onTap(857, 510, 858, 511, "确定离开", 1000);
                }
            }

            result = mFairy.findPic("battleH.png");
            mFairy.onTap(0.95f, result, "关闭自动战斗", 500);


            result = mFairy.findPic("jia.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(1179, 70, 1180, 71, "关闭", 1000);
            } else {
                result = mFairy.findPic("activity.png");
                if (result.sim > 0.82f) {
                    break;
                } else {
                    result = mFairy.findPic("topZoom.png");
                    mFairy.onTap(0.9f, result, "上方缩放栏", 1000);
                }
            }
        }
    }//初始化

    public void goWTC()throws Exception{
        while (mFairy.condit()) {

            LtLog.e(mFairy.getLineInfo("goWTC"));

            result = mFairy.findPic("UImap.png");
            if (result.sim > 0.85f) {
                if (map(1)) {
                    mFairy.onTap(352, 387, 384, 419, "确定", 10000);
                    init();
                    return;
                }
            } else {
                init();
                battleEnd();
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f,result,1245, 31, 1257, 45, "点击地图", 3000);
            }
        }
    }

    public boolean map(int map) throws Exception {
        for (int i = 0; i < 3; i++) {
            switch (map) {
                case 1:
                    result = mFairy.findPic("g1.png");
                    if (result.sim > 0.8f) {
                        return true;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(360, 575, 365, 580, "乌坦城", 1000);
                    break;

                case 2:
                    result = mFairy.findPic("g2.png");
                    if (result.sim > 0.8f) {
                        return true;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(654, 585, 655, 590, "魔兽山脉", 2000);
                    break;

                case 3:
                    result = mFairy.findPic("g3.png");
                    if (result.sim > 0.8f) {
                        return true;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(606, 361, 610, 365, "大沙漠", 2000);
                    break;

                case 4:
                    result = mFairy.findPic("g9.png");
                    if (result.sim > 0.8f) {
                        return true;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(570, 465, 575, 466, "帝都", 2000);
                    break;
                case 5:
                    result = mFairy.findPic("g10.png");
                    if (result.sim > 0.8f) {
                        return true;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(289, 491, 290, 495, "云岚宗", 2000);

                    break;
                case 6:
                    result = mFairy.findPic("g11.png");
                    if (result.sim > 0.8f) {
                        return true;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(361, 402, 365, 405, "迦南学院", 2000);
                    break;

                case 7:
                    result = mFairy.findPic("g14.png");
                    if (result.sim > 0.8f) {
                        return true;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(468, 385, 493, 395, "地心岩洞", 2000);
                    break;

                case 8:
                    result = mFairy.findPic("g17.png");
                    if (result.sim > 0.92f) {
                        return true;
                    }

                    result = mFairy.findPic("g14.png");
                    if (result.sim > 0.85f) {
                        result = mFairy.findPic(1008, 218, 1175, 651, "g15.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "前往二层", 5000);
                        } else {
                            result = mFairy.findPic(960, 84, 1142, 536, "g16.png");
                            mFairy.onTap(0.85f, result, "传送门", 1000);
                        }
                        continue;
                    }

                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(468, 385, 493, 395, "地心岩洞", 2000);
                    break;
                case 9:
                    result = mFairy.findPic("g19.png");
                    if (result.sim > 0.92f) {
                        return true;
                    }

                    result = mFairy.findPic("g17.png");
                    if (result.sim > 0.92f) {
                        result = mFairy.findPic(1008, 218, 1175, 651, "g18.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "前往三层", 5000);
                        } else {
                            result = mFairy.findPic(960, 84, 1142, 536, "g16.png");
                            mFairy.onTap(0.85f, result, "传送门", 1000);
                        }
                        continue;
                    }

                    result = mFairy.findPic("g14.png");
                    if (result.sim > 0.85f) {
                        result = mFairy.findPic(1008, 218, 1175, 651, "g15.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "前往二层", 5000);
                        } else {
                            result = mFairy.findPic(960, 84, 1142, 536, "g16.png");
                            mFairy.onTap(0.85f, result, "传送门", 1000);
                        }
                        continue;
                    }

                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(468, 385, 493, 395, "地心岩洞", 2000);
                    break;


                case 10:
                    result = mFairy.findPic("g20.png");
                    if (result.sim > 0.8f) {
                        return true;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(367, 336, 403, 350, "天焚炼气塔", 2000);
                    break;

                case 11:
                    result = mFairy.findPic("g21.png");
                    if (result.sim > 0.92f) {
                        return true;
                    }

                    result = mFairy.findPic("g20.png");
                    if (result.sim > 0.92f) {
                        result = mFairy.findPic(1008, 218, 1175, 651, "g15.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "前往二层", 5000);
                        } else {
                            result = mFairy.findPic(960, 84, 1142, 536, "g16.png");
                            mFairy.onTap(0.85f, result, "传送门", 1000);
                        }
                        continue;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(367, 336, 403, 350, "天焚炼气塔", 2000);
                    break;


                case 12:
                    result = mFairy.findPic("g22.png");
                    if (result.sim > 0.85f) {
                        return true;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(437, 490, 475, 504, "黑角域", 2000);
                    break;

                case 13:
                    result = mFairy.findPic("g23.png");
                    if (result.sim > 0.85f) {
                        return true;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(227, 252, 249, 288, "花宗", 2000);
                    break;
                case 14:
                    result = mFairy.findPic("g24.png");
                    if (result.sim > 0.85f) {
                        return true;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(697, 485, 719, 502, "虚空雷池", 2000);
                    break;
                case 15:
                    result = mFairy.findPic("g25.png");
                    if (result.sim > 0.85f) {
                        return true;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(536,216,567,234, "出云帝国", 2000);
                    break;

                case 16:
                    result = mFairy.findPic("g26.png");
                    if (result.sim > 0.85f) {
                        return true;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(1113,150,1129,164, "魂天界", 2000);
                    break;
                case 17:


                    result = mFairy.findPic("g28.png");
                    if (result.sim > 0.92f) {
                        return true;
                    }

                    result = mFairy.findPic("g21.png");
                    if (result.sim > 0.92f) {
                        result = mFairy.findPic(1008, 218, 1175, 651, "g27.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "前往二层", 5000);
                        } else {
                            result = mFairy.findPic(960, 84, 1142, 536, "g16.png");
                            mFairy.onTap(0.85f, result, "传送门", 1000);
                        }
                        continue;
                    }

                    result = mFairy.findPic("g20.png");
                    if (result.sim > 0.92f) {
                        result = mFairy.findPic(1008, 218, 1175, 651, "g15.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "前往二层", 5000);
                        } else {
                            result = mFairy.findPic(960, 84, 1142, 536, "g16.png");
                            mFairy.onTap(0.85f, result, "传送门", 1000);
                        }
                        continue;
                    }
                    mFairy.onTap(272, 646, 273, 650, "斗气大陆", 1500);
                    mFairy.onTap(367, 336, 403, 350, "天焚炼气塔", 2000);

                    break;

            }
        }
        return false;

    }//选择地图

    public void pleaseLeaveRanks() throws Exception {
        result = mFairy.findPic(60, 171, 1103, 585, "pleaseLeave.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "请离队伍", 2000);
            mFairy.onTap(858, 504, 860, 505, "确定", 1000);
        }
    }

    public boolean limitNumber(int num) throws Exception {
        switch (num) {
            case 2:
                result = mFairy.findPic(new String[]{"rank1.png", "rank6.png"});
                if (result.sim > 0.9f) {
                    return true;
                } else {
                    mFairy.onTap(571, 331, 605, 369, "点击第3位置", 2000);
                    pleaseLeaveRanks();
                }
                break;
            case 3:
                result = mFairy.findPic(new String[]{"rank2.png", "rank4.png"});
                if (result.sim > 0.9f) {
                    return true;
                } else {
                    mFairy.onTap(775, 324, 824, 369, "点击第4位置", 2000);
                    pleaseLeaveRanks();
                }
                break;
            case 4:
                result = mFairy.findPic(new String[]{"rank3.png", "rank8.png"});
                if (result.sim > 0.9f) {
                    return true;
                } else {
                    mFairy.onTap(985, 328, 1028, 364, "点击第5位置", 2000);
                    pleaseLeaveRanks();
                }
                break;
            case 5:
                break;
        }
        return false;
    }//数量

    public int ranksGetNum() throws Exception {
        result = mFairy.findPic("rank0.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("当前队伍人数 【1】"));
            return 1;
        }
        result = mFairy.findPic(new String[]{"rank1.png", "rank6.png"});
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("当前队伍人数 【2】"));
            return 2;
        }
        result = mFairy.findPic(new String[]{"rank2.png", "rank7.png"});
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("当前队伍人数 【3】"));
            return 3;
        }
        result = mFairy.findPic(new String[]{"rank3.png", "rank8.png"});
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("当前队伍人数 【4】"));
            return 4;
        }
        LtLog.e(mFairy.getLineInfo("当前队伍人数 【5】"));
        return 5;
    }//返回队伍人数

    public int mainRanksGetNum() throws Exception {
        result = mFairy.findPic(new String[]{
                "ranks.png", "ranks1.png"});
        LtLog.e("队伍图标sim"+result.sim);
        if (result.sim > 0.7f) {
            if (mFairy.findPic(new String[]{"one.png", "one1.png"}).sim > 0.8f) {
                LtLog.e("队伍人数为【 1 】人");
                return 1;
            } else if (mFairy.findPic(new String[]{"two.png", "two1.png"}).sim > 0.8f) {
                LtLog.e("队伍人数为【 2 】人");
                return 2;
            } else if (mFairy.findPic(new String[]{"three.png", "three1.png"}).sim > 0.8f) {
                LtLog.e("队伍人数为【 3 】人");
                return 3;
            } else if (mFairy.findPic(new String[]{"four.png", "four1.png"}).sim > 0.8f) {
                LtLog.e("队伍人数为【 4 】人");
                return 4;
            } else if (mFairy.findPic(new String[]{"five.png", "five1.png"}).sim > 0.8f) {
                LtLog.e("队伍人数为【 5 】人");
                return 5;
            } else {
                LtLog.e("队伍人数为【 0 】人");
                return 0;
            }
        }
        return 0;
    }//返回队伍数量

    public void selectionTask(String img1, String img2) throws Exception {
        int err = 0;
        int select = 1;
        while (mFairy.condit()) {
            err++;
            if (err > 20) {
                close();
                return;
            }
            qx();
            result = mFairy.findPic("tz1.png");
            if (result.sim > 0.85f) {
                switch (select) {
                    case 1:
                        LtLog.e(mFairy.getLineInfo("调整目标>>>寻找类型"));
                        result = mFairy.findPic(401, 216, 456, 581, "target1.png");
                        mFairy.onTap(0.85f, result, "关闭所有目标任务", 1000);

                        result = mFairy.findPic(277, 212, 461, 588, img1);
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "任务类型", 1000);
                            select = 2;
                            continue;
                        }
                        break;
                    case 2:
                        LtLog.e(mFairy.getLineInfo("调整目标>>>寻找任务"));
                        result = mFairy.findPic(277, 212, 461, 588, img2);
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "任务", 1500);
                            mFairy.onTap(734, 533, 735, 535, "确定", 1000);
                            return;
                        }
                        break;
                }

                mFairy.ranSwipe(362, 358, 398, 513, 2, 1000, (long)2000);
                LtLog.e(mFairy.getLineInfo("调整目标>>>滑动"));

            } else {
                break;
            }
        }
    }

    public boolean boss() throws Exception {
        result = mFairy.findPic(271, 64, 459, 217, new String[]{"gj2.png", "msxs3.png", "boss1.png", "lmct3.png"});
        if (result.sim > 0.85f) {
            return true;
        }
        return false;
    }//

    public void whileContent() throws Exception {
        int num = 0;
        int n = 0;
        while (mFairy.condit()) {

            result = mFairy.findPic("findWay.png");
            if (result.sim > 0.8f) {
                Thread.sleep(500);

                if (n == 0) {
                    LtLog.e(mFairy.getLineInfo("寻路中,停止其他操作"));
                }
                n++;
                if (n > 3) {
                    n = 0;
                }

                num = 0;
            } else {
                num++;
                if (num > 2) {
                    return;
                }
            }
        }
    }//


    private long fad = 0;
    private long fadTime = 0;

    public long fad() throws Exception {
        if (fad == 0) {
            LtLog.e("kong");
            fad = mFairy.getColorNum(1174, 121, 1243, 139, "209,210,219", 0.95f);
            fadTime = System.currentTimeMillis() / 1000;
            return 0;
        } else {
            int i = mFairy.getColorNum(1174, 121, 1243, 139, "209,210,219", 0.95f);

            if (i == fad) {
                return (System.currentTimeMillis() / 1000) - fadTime;
            } else {
                fad = i;
                fadTime = System.currentTimeMillis() / 1000;
                return 0;
            }
        }
    }//发呆判断

    int o = 79;

    public boolean yaoqing() throws Exception {
        for (int q = 0; q < 2; q++) {
            qx();
            result = mFairy.findPic("UIinvitation.png");
            if (result.sim > 0.88f) {

                for (int i = 0; i < 4; i++) {
                    result = mFairy.findPic(698, 259 + (o * i), 821, 337 + (o * i), "invitation.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "邀请", 500);
                        for (int j = 0; j < 5; j++) {
                            result = mFairy.findPic(640, 235, 681, 338, "invitation1.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(801, 135, 802, 136, "关闭", 1000);
                                LtLog.e("队伍已经满了,停止邀请");
                                return true;
                            }
                        }
                    }
                }

                if (q == 0) {
                    mFairy.touchDown(1, 650, 570);
                    mFairy.touchMove(1, 650, 210, 500);
                    Thread.sleep(2000);
                    mFairy.touchUp(1);
                }
                LtLog.e("滑");
            } else {
                break;
            }
        }
        return false;
    }

}
