package com.script.fairy;


import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.Random;

/**
 * Created by user on 2019/2/15.
 */

public class GamePublicFuntion {
    public AtFairyImpl mFairy;
    public FindResult result;

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
    }

    public static boolean nnBool = true;

    public void clearPackage() throws Exception {
        new TaskContent(mFairy, "清理背包") {

            void init() throws Exception {
                gamePublicFuntion.close(1);
                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic(1135, 167, 1278, 313, "package.png");
                mFairy.onTap(0.8f, result, "背包", 1000);

                result = mFairy.findPic(782, 496, 946, 627, "chuan.png");
                mFairy.onTap(0.8f, result, "传送", 3000);

                result = mFairy.findPic(350, 272, 1225, 664, "package1.png");
                if (result.sim > 0.8f) {
                    err = 0;





                    result = mFairy.findPic(320, 272, 1225, 664, "package2.png");
                    mFairy.onTap(0.85f, result, "回收", 1500);

                }

                FindResult huishou = mFairy.findPic(485, 533, 835, 664, "package3.png");
                if (huishou.sim > 0.8f) {
                    err = 0;

                    if (nnBool) {

                        if (AtFairyConfig.getOption("task_id").equals("2737")) {


                            result = mFairy.findPic(328,292,390,340,"gou.png");
                            if(result.sim<0.7f){
                                mFairy.onTap(358, 311, 365, 322, "", 500);
                            }
                            result = mFairy.findPic(539,293,617,332,"gou.png");
                            if(result.sim<0.7f){
                                mFairy.onTap(571, 312, 580, 323, "", 500);
                            }
                            result = mFairy.findPic(759,296,846,338,"gou.png");
                            if(result.sim<0.7f){
                                mFairy.onTap(797, 312, 803, 322, "", 500);
                            }
                            result = mFairy.findPic(330,338,388,383,"gou.png");
                            if(result.sim<0.7f){
                                mFairy.onTap(357, 356, 365, 367, "", 500);
                            }

                            nnBool = false;
                            return;
                        }

                    }
                    int row = 5;//行
                    int col = 3;//列
                    int x = 360;
                    int y = 270;
                    //装备类
                    //359,272   575,272  359,317

                    if (nnBool) {

                        int cou = 1;
                        for (int r = 0; r < row; r++) {

                            for (int c = 0; c < col; c++) {


                                result = mFairy.findPic(x + (c * 215) - 20, y + (r * 45) - 20, x + (c * 215) + 20, y + (r * 45) + 20, "gou.png");

                                if (result.sim > 0.7f) {
                                    if (!AtFairyConfig.getOption("zhs" + cou).equals("1")) {
                                        mFairy.onTap(0.7f, result, "取消勾选：" + cou, 1000);
                                    }
                                } else {
                                    if (AtFairyConfig.getOption("zhs" + cou).equals("1")) {
                                        mFairy.tap(x + (c * 215), y + (r * 45));
                                        Thread.sleep(1000);
                                    }

                                }
                                cou++;
                            }
                        }

                        mFairy.onTap(0.8f, huishou, "我要回收", 1000);

                        //防具类
                        row = 7;//行


                        if (AtFairyConfig.getOption("fang").equals("1")) {
                            mFairy.onTap(491, 197, 531, 209, "防具类", 500);
                            mFairy.onTap(491, 197, 531, 209, "防具类", 1000);
// int x = 360;
//                        int y = 270;


                            for (int r = 0; r < row; r++) {
                                for (int c = 0; c < col; c++) {

                                    result = mFairy.findPic(x + (c * 215) - 20, y + (r * 45) - 20, x + (c * 215) + 20, y + (r * 45) + 20, "gou.png");
                                    if (result.sim < 0.7f) {
                                        mFairy.tap(x + (c * 215), y + (r * 45));
                                        Thread.sleep(1000);
                                    }

                                }
                            }

                            mFairy.onTap(0.8f, huishou, "我要回收", 1000);
                        }


                        if (AtFairyConfig.getOption("baowu").equals("1")) {

                            //宝物类
                            row = 5;//行

                            mFairy.onTap(637, 198, 677, 209, "宝物类", 500);
                            mFairy.onTap(637, 198, 677, 209, "宝物类", 1000);


                            for (int r = 0; r < row; r++) {
                                for (int c = 0; c < col; c++) {

                                    result = mFairy.findPic(x + (c * 215) - 20, y + (r * 45) - 20, x + (c * 215) + 20, y + (r * 45) + 20, "gou.png");
                                    if (result.sim < 0.7f) {
                                        mFairy.tap(x + (c * 215), y + (r * 45));
                                        Thread.sleep(1000);
                                    }

                                }
                            }

                            mFairy.onTap(0.8f, huishou, "我要回收", 1000);
                        }


                        if (AtFairyConfig.getOption("baowu").equals("1")) {
                            //印记类
                            row = 4;//行

                            mFairy.onTap(790, 198, 827, 210, "印记类", 500);
                            mFairy.onTap(790, 198, 827, 210, "印记类", 1000);


                            for (int r = 0; r < row; r++) {
                                for (int c = 0; c < col; c++) {

                                    result = mFairy.findPic(x + (c * 215) - 20, y + (r * 45) - 20, x + (c * 215) + 20, y + (r * 45) + 20, "gou.png");
                                    if (result.sim < 0.7f) {
                                        mFairy.tap(x + (c * 215), y + (r * 45));
                                        Thread.sleep(1000);
                                    }

                                }
                            }

                            mFairy.onTap(0.8f, huishou, "我要回收", 1000);

                        }

                        row = 7;

                        mFairy.onTap(926, 197, 987, 212, "转生石类", 500);
                        mFairy.onTap(926, 197, 987, 212, "转生石类", 1000);


                        cou = 1;
                        for (int r = 0; r < row; r++) {

                            for (int c = 0; c < col; c++) {


                                result = mFairy.findPic(x + (c * 215) - 20, y + (r * 45) - 20, x + (c * 215) + 20, y + (r * 45) + 20, "gou.png");

                                if (result.sim > 0.7f) {
                                    if (!AtFairyConfig.getOption("zhuanhs" + cou).equals("1")) {
                                        mFairy.onTap(0.7f, result, "取消勾选：" + cou, 1000);
                                    }
                                } else {
                                    if (AtFairyConfig.getOption("zhuanhs" + cou).equals("1")) {
                                        mFairy.tap(x + (c * 215), y + (r * 45));
                                        Thread.sleep(1000);
                                    }

                                }
                                cou++;
                            }

                        }

                        nnBool = false;
                    }

                    mFairy.onTap(0.8f, huishou, "我要回收", 1000);

                    mFairy.onTap(0.8f, huishou, 1060, 139, 1067, 153, "", 1000);

                    result = mFairy.findPic(350, 272, 1225, 664, "package1.png");
                    mFairy.onTap(0.8f, result, "整理", 2000);

                    if (AtFairyConfig.getOption("task_id").equals("2737") || AtFairyConfig.getOption("jyuse").equals("1")) {

                        for (int i = 0; i < 5; i++) {
                            result = mFairy.findPic(115, 51, 699, 556, new String[]{"jing.png", "jing1.png"});
                            if (result.sim > 0.85f) {
                                mFairy.onTap(0.85f, result, "使用经验券", 800);
                                result = mFairy.findPic(111, 48, 934, 411, "use1.png");
                                mFairy.onTap(0.8f, result, "使用", 800);

                            }
                        }
                    }

                    if (AtFairyConfig.getOption("zhuuse").equals("1")) {
                        for (int i = 0; i < 3; i++) {
                            result = mFairy.findPic(115, 51, 699, 556, new String[]{"jyzhu.png"});
                            if (result.sim > 0.85f) {
                                mFairy.onTap(0.85f, result, "使用经验珠", 800);
                                result = mFairy.findPic(111, 48, 934, 411, "use1.png");
                                mFairy.onTap(0.8f, result, "使用", 800);

                                Thread.sleep(1000);
                            }
                        }

                    }

                    gamePublicFuntion.close(3);
                    setTaskEnd();
                    return;
                }

                timeCount(10, 0);
            }
        };

    }//清理背包

    public boolean goHome() throws Exception {
        result = mFairy.findPic(587, 509, 950, 567, "hui.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.85f, result, "回城", 2000);
            return true;
        } else {
            LtLog.e(mFairy.getLineInfo("没有回城石"));

            if (AtFairyConfig.getOption("gmhc").equals("1")) {
                gmhc();
                result = mFairy.findPic(587, 509, 950, 567, "hui.png");
                mFairy.onTap(0.85f, result, "回城", 2000);
            }

            return false;
        }
    }

    public void delivery() throws Exception {
        result = mFairy.findPic(587, 509, 950, 567, "delivery.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.85f, result, "传送", 2000);
        } else {
            LtLog.e(mFairy.getLineInfo("没有传送石"));
        }
    }

    float s = 0.7f;

    public void seekNPC(int x, int y, int x1, int y1, String npc) throws Exception {
        new TaskContent(mFairy, "seekNPC") {

            void create() throws Exception {
                super.create();
                s = 0.7f;
            }

            void init() throws Exception {

                result = mFairy.findPic("mapScene.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(x, y, x1, y1, "点击坐标", 1000);
                    mFairy.onTap(x, y, x1, y1, "点击坐标", 500);
                    mFairy.onTap(x, y, x1, y1, "点击坐标", 500);
                    mFairy.onTap(1047, 64, 1048, 65, "", 4000);
                    setTaskName(1);
                    s = s - 0.3f;
                    return;
                } else {
                    close(1);
                    mFairy.onTap(1202, 117, 1216, 136, "点开地图", 1500);

                }

            }

            void content_01() throws Exception {
                Thread.sleep(2000);

                result = mFairy.findPic(210, 125, 1070, 504, npc);
                LtLog.e(mFairy.getLineInfo("npc sim: " + result.sim + " x,y: " + result.x + "," + result.y));
                mFairy.onTap(s, result, "点击转npc", 2000);

                result = mFairy.findPic(22, 54, 611, 350, "zj.png");
                if (result.sim > 0.8f && npc.equals("ma1.png")) {
                    mFairy.onTap(0.8f, result, "进入终极之城", 3000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(22, 54, 611, 350, "sldg.png");
                if (result.sim > 0.8f && npc.equals("ma2.png")) {
                    mFairy.onTap(0.8f, result, "进入神龙帝国", 3000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(22, 54, 611, 350, "chuantou.png");
                if (result.sim > 0.8f && npc.equals("ma3.png")) {
                    mFairy.onTap(0.8f, result, "进入船头", 3000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(22, 54, 611, 350, "chuanwei.png");
                if (result.sim > 0.8f && npc.equals("ma4.png")) {
                    mFairy.onTap(0.8f, result, "进入船尾", 3000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(22, 54, 611, 350, "slzy.png");
                if (result.sim > 0.8f && npc.equals("ma5.png")) {
                    mFairy.onTap(0.8f, result, "进入神龙庄园", 3000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(722, 399, 895, 476, "manxue1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "加满", 3000);
                    gamePublicFuntion.close(3);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(782, 496, 946, 627, "chuan.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "传送", 3000);


                    result = mFairy.findPic(782, 496, 946, 627, "chuan.png");
                    mFairy.onTap(0.8f, result, "传送", 3000);

                    setTaskEnd();
                    return;
                }


                result = mFairy.findPic(487, 528, 801, 640, "zhuansheng.png");
                if (result.sim > 0.75f) {
                    mFairy.onTap(0.8f, result, "我要转生", 1000);
                    mFairy.onTap(0.8f, result, 811, 409, 825, 419, "我要转生", 500);
                    mFairy.onTap(0.8f, result, 1085, 95, 1094, 109, "我要转生", 1000);
                    gamePublicFuntion.close(3);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(789, 528, 970, 700, "ronglian1.png");
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }


                if (timeCount(5, 0)) {
                    if (frequencyMap("err", 3)) {
                        setTaskEnd();
                        return;
                    }
                }
            }

        };
    }//npc

    public void cMap(int m) throws Exception {
        if (m >= 18 && m <= 27) {
            LtLog.e(mFairy.getLineInfo("前往终极之城"));
            seekNPC(518, 263, 519, 264, "ma1.png");
        }

        if (m >= 28 && m <= 40) {
            LtLog.e(mFairy.getLineInfo("前往神龙帝国"));
            seekNPC(518, 263, 519, 264, "ma2.png");
        }

        if((m>=41 && m <=58) || (m>=77 && m<=82) ){
            LtLog.e(mFairy.getLineInfo("前往船头"));
            seekNPC(518, 263, 519, 264, "ma3.png");
        }

        if((m>=59&& m <=76) || (m>=83 && m<=88) ){
            LtLog.e(mFairy.getLineInfo("前往船尾"));
            seekNPC(518, 263, 519, 264, "ma4.png");
        }

        switch (m) {
            case 1:
                LtLog.e(mFairy.getLineInfo("前往比起矿洞"));
                seekNPC(518, 267, 519, 268, "m1.png");
                break;
            case 2:
                LtLog.e(mFairy.getLineInfo("前往秘境通道"));
                seekNPC(518, 267, 519, 268, "m2.png");
                break;
            case 3:
                LtLog.e(mFairy.getLineInfo("前往峡谷密道"));
                seekNPC(518, 267, 519, 268, "m3.png");
                break;
            case 4:
                LtLog.e(mFairy.getLineInfo("前往桃园之门"));
                seekNPC(518, 267, 519, 268, "m4.png");
                break;
            case 5:
                LtLog.e(mFairy.getLineInfo("前往雷炎洞穴"));
                seekNPC(511, 267, 512, 268, "m5.png");
                break;
            case 6:
                LtLog.e(mFairy.getLineInfo("前往尸魔洞"));
                seekNPC(511, 267, 512, 268, "m6.png");
                break;
            case 7:
                LtLog.e(mFairy.getLineInfo("前往堕落坟场"));
                seekNPC(511, 267, 512, 268, "m7.png");
                break;
            case 8:
                LtLog.e(mFairy.getLineInfo("前往死亡通道"));
                seekNPC(511, 267, 512, 268, "m8.png");
                break;
            case 9:
                LtLog.e(mFairy.getLineInfo("前往要塞之地"));
                seekNPC(511, 266, 512, 267, "m9.png");
                break;
            case 10:
                LtLog.e(mFairy.getLineInfo("前往兽人巢穴"));
                seekNPC(511, 259, 512, 260, "m10.png");

                break;
            case 11:
                LtLog.e(mFairy.getLineInfo("前往失落之岛"));
                seekNPC(511, 259, 512, 260, "m11.png");
                break;
            case 12:
                LtLog.e(mFairy.getLineInfo("前往石墓迷窟"));
                seekNPC(511, 259, 512, 260, "m12.png");
                break;
            case 13:
                LtLog.e(mFairy.getLineInfo("前往迷之森林"));
                seekNPC(513, 258, 514, 259, "m13.png");
                break;
            case 14:
                LtLog.e(mFairy.getLineInfo("前往祖玛寺庙"));
                seekNPC(516, 258, 517, 259, "m14.png");
                break;
            case 15:
                LtLog.e(mFairy.getLineInfo("前往远古洞穴"));
                seekNPC(518, 258, 519, 259, "m15.png");
                break;
            case 16:
                LtLog.e(mFairy.getLineInfo("前往创世龙域"));
                seekNPC(506, 267, 507, 268, "m16.png");
                break;
            case 17:
                LtLog.e(mFairy.getLineInfo("前往创世圣域"));
                seekNPC(516, 254, 517, 255, "m17.png");

                break;
            case 18:
                LtLog.e(mFairy.getLineInfo("前往沃玛森林"));
                seekNPC(896, 164, 897, 165, "m18.png");
                break;
            case 19:
                LtLog.e(mFairy.getLineInfo("前往龙域山谷"));
                seekNPC(896, 162, 897, 163, "m19.png");
                break;
            case 20:
                LtLog.e(mFairy.getLineInfo("前往祖玛神教"));
                seekNPC(899, 158, 900, 159, "m20.png");
                break;
            case 21:
                LtLog.e(mFairy.getLineInfo("前往魔龙岭"));
                seekNPC(903, 163, 904, 164, "m21.png");
                break;
            case 22:
                LtLog.e(mFairy.getLineInfo("前往魔龙血域"));
                seekNPC(903, 163, 904, 164, "m22.png");
                break;
            case 23:
                LtLog.e(mFairy.getLineInfo("前往牛魔遗迹"));
                seekNPC(909, 168, 910, 169, "m23.png");
                break;
            case 24:
                LtLog.e(mFairy.getLineInfo("前往未知暗殿"));
                seekNPC(909, 168, 910, 169, "m24.png");
                break;
            case 25:
                LtLog.e(mFairy.getLineInfo("前往寒冰圣地"));
                seekNPC(904, 173, 905, 174, "m25.png");
                break;
            case 26:
                LtLog.e(mFairy.getLineInfo("前往冰谷秘境"));
                seekNPC(904, 173, 905, 174, "m26.png");
                break;
            case 27:
                LtLog.e(mFairy.getLineInfo("前往创世神域"));
                seekNPC(900, 165, 901, 166, "m27.png");
                break;
            case 28:
                LtLog.e(mFairy.getLineInfo("前往龙城边境"));
                seekNPC(595, 322, 596, 323, "m28.png");
                break;
            case 29:
                LtLog.e(mFairy.getLineInfo("前往苍井岛"));
                seekNPC(595, 322, 596, 323, "m29.png");
                break;
            case 30:
                LtLog.e(mFairy.getLineInfo("前往毒龙山谷"));
                seekNPC(607, 328, 608, 329, "m30.png");
                break;
            case 31:
                LtLog.e(mFairy.getLineInfo("前往龙珠峡谷"));
                seekNPC(607, 328, 608, 329, "m31.png");
                break;
            case 32:
                LtLog.e(mFairy.getLineInfo("前往龙城荒野"));
                seekNPC(618, 336, 619, 337, "m32.png");
                break;
            case 33:
                LtLog.e(mFairy.getLineInfo("前往魔龙戈壁"));
                seekNPC(618, 336, 619, 337, "m33.png");
                break;
            case 34:
                LtLog.e(mFairy.getLineInfo("前往狐妖福地"));
                seekNPC(608, 313, 609, 314, "m34.png");
                break;
            case 35:
                LtLog.e(mFairy.getLineInfo("前往绿茂森林"));
                seekNPC(608, 313, 609, 314, "m35.png");
                break;
            case 36:
                LtLog.e(mFairy.getLineInfo("前往雷龙洞"));
                seekNPC(618, 320, 619, 321, "m36.png");
                break;
            case 37:
                LtLog.e(mFairy.getLineInfo("前往屠龙殿"));
                seekNPC(618, 320, 619, 321, "m37.png");
                break;
            case 38:
                LtLog.e(mFairy.getLineInfo("前往龙魂迷宫"));
                seekNPC(626, 328, 627, 329, "m38.png");
                break;
            case 39:
                LtLog.e(mFairy.getLineInfo("前往雪山之巅"));
                seekNPC(626, 328, 627, 329, "m39.png");
                break;
            case 40:
                LtLog.e(mFairy.getLineInfo("前往冰封王座"));
                seekNPC(587, 306, 588, 307, "m40.png");
                break;



            case 41:
                LtLog.e(mFairy.getLineInfo("前往仙竹林"));
                seekNPC(389, 287, 390, 288, "m41.png");
                break;
            case 42:
                LtLog.e(mFairy.getLineInfo("前往森林海"));
                seekNPC(389, 287, 390, 288, "m42.png");
                break;
            case 43:
                LtLog.e(mFairy.getLineInfo("前往荒神界"));
                seekNPC(389, 287, 390, 288, "m43.png");
                break;



            case 44:
                LtLog.e(mFairy.getLineInfo("前往]百陵洲"));
                seekNPC(4469, 343, 470, 344, "m44.png");
                break;
            case 45:
                LtLog.e(mFairy.getLineInfo("前往玄灵峡谷"));
                seekNPC(469, 343, 470, 344, "m45.png");
                break;
            case 46:
                LtLog.e(mFairy.getLineInfo("前往暗月幽林"));
                seekNPC(469, 343, 470, 344, "m46.png");
                break;


            case 47:
                LtLog.e(mFairy.getLineInfo("前往断魂荒野"));
                seekNPC(503, 221, 504, 222,"m47.png");
                break;
            case 48:
                LtLog.e(mFairy.getLineInfo("前往莹焰雨林"));
                seekNPC(503, 221, 504, 222,"m48.png");
                break;
            case 49:
                LtLog.e(mFairy.getLineInfo("前往清风洞"));
                seekNPC(503, 221, 504, 222, "m49.png");
                break;


            case 50:
                LtLog.e(mFairy.getLineInfo("前往封神陵"));
                seekNPC(593, 278, 594, 279, "m50.png");
                break;
            case 51:
                LtLog.e(mFairy.getLineInfo("前往诸星原"));
                seekNPC(593, 278, 594, 279, "m51.png");
                break;
            case 52:
                LtLog.e(mFairy.getLineInfo("前往余晖岛"));
                seekNPC(593, 278, 594, 279, "m52.png");
                break;


            case 53:
                LtLog.e(mFairy.getLineInfo("前往苍穹岛"));
                seekNPC(620, 144, 621, 145, "m53.png");
                break;
            case 54:
                LtLog.e(mFairy.getLineInfo("前往远古战场"));
                seekNPC(620, 144, 621, 145, "m54.png");
                break;
            case 55:
                LtLog.e(mFairy.getLineInfo("前往洪荒古城"));
                seekNPC(620, 144, 621, 145, "m55.png");
                break;

            case 56:
                LtLog.e(mFairy.getLineInfo("前往魔龙山谷"));
                seekNPC(700, 193, 700, 194, "m56.png");
                break;
            case 57:
                LtLog.e(mFairy.getLineInfo("前往]封魔秘境"));
                seekNPC(700, 193, 700, 194, "m57.png");
                break;
            case 58:
                LtLog.e(mFairy.getLineInfo("前往溶洞矿地"));
                seekNPC(700, 193, 700, 194, "m58.png");
                break;


            case 59:
                LtLog.e(mFairy.getLineInfo("前往寒云谷"));
                seekNPC(666, 342, 667, 343, "m59.png");
                break;
            case 60:
                LtLog.e(mFairy.getLineInfo("前往雷州半岛"));
                seekNPC(666, 342, 667, 343, "m60.png");
                break;
            case 61:
                LtLog.e(mFairy.getLineInfo("前往天木森林"));
                seekNPC(666, 342, 667, 343, "m61.png");
                break;


            case 62:
                LtLog.e(mFairy.getLineInfo("前往琉璃幻境"));
                seekNPC(759, 403, 760, 404, "m62.png");
                break;
            case 63:
                LtLog.e(mFairy.getLineInfo("前往昆天域"));
                seekNPC(759, 403, 760, 404, "m63.png");
                break;
            case 64:
                LtLog.e(mFairy.getLineInfo("前往寰宇岛"));
                seekNPC(759, 403, 760, 404, "m64.png");
                break;



            case 65:
                LtLog.e(mFairy.getLineInfo("前往流沙绿洲"));
                seekNPC(652, 478, 653, 479, "m65.png");
                break;
            case 66:
                LtLog.e(mFairy.getLineInfo("前往瘟疫沼泽"));
                seekNPC(652, 478, 653, 479, "m66.png");
                break;
            case 67:
                LtLog.e(mFairy.getLineInfo("前往惊龙域"));
                seekNPC(652, 478, 653, 479,"m67.png");
                break;


            case 68:
                LtLog.e(mFairy.getLineInfo("前往沉星雨林"));
                seekNPC(558, 417, 559, 418, "m68.png");
                break;
            case 69:
                LtLog.e(mFairy.getLineInfo("前往神鬼岭"));
                seekNPC(558, 417, 559, 418, "m69.png");
                break;
            case 70:
                LtLog.e(mFairy.getLineInfo("前往雷州岛"));
                seekNPC(558, 417, 559, 418,"m70.png");
                break;




            case 71:
                LtLog.e(mFairy.getLineInfo("前往沉星雨林"));
                seekNPC(884, 329, 885, 330, "m71.png");
                break;
            case 72:
                LtLog.e(mFairy.getLineInfo("前往神鬼岭"));
                seekNPC(884, 329, 885, 330, "m72.png");
                break;
            case 73:
                LtLog.e(mFairy.getLineInfo("前往雷州岛"));
                seekNPC(884, 329, 885, 330,"m73.png");
                break;


            case 74:
                LtLog.e(mFairy.getLineInfo("前往遗迹森林"));
                seekNPC(785, 265, 786, 266, "m74.png");
                break;
            case 75:
                LtLog.e(mFairy.getLineInfo("前往龙陨宫殿"));
                seekNPC(785, 265, 786, 266, "m75.png");
                break;
            case 76:
                LtLog.e(mFairy.getLineInfo("前往地狱魔宫"));
                seekNPC(785, 265, 786, 266,"m76.png");
                break;

            case 77:
                LtLog.e(mFairy.getLineInfo("前往魔草场"));
                seekNPC(389, 289, 390, 290, "m77.png");
                break;

            case 78:
                LtLog.e(mFairy.getLineInfo("前往魔物殿"));
                seekNPC(469, 345, 470, 346, "m78.png");
                break;
            case 79:
                LtLog.e(mFairy.getLineInfo("前往龙之领域"));
                seekNPC(503, 223, 504, 224, "m79.png");
                break;
            case 80:
                LtLog.e(mFairy.getLineInfo("前往魔龙洞"));
                seekNPC(593, 280, 594, 281, "m80.png");
                break;
            case 81:
                LtLog.e(mFairy.getLineInfo("前往魔王洞穴"));
                seekNPC(620, 146, 621, 147, "m81.png");
                break;
            case 82:
                LtLog.e(mFairy.getLineInfo("前往古藤林"));
                seekNPC(700, 191, 700, 192, "m82.png");
                break;

            case 83:
                LtLog.e(mFairy.getLineInfo("前往炼狱回廊"));
                seekNPC(669, 331, 670, 332, "m83.png");
                break;

            case 84:
                LtLog.e(mFairy.getLineInfo("前往藏幽洞"));
                seekNPC(766, 392, 767, 393, "m84.png");
                break;

            case 85:
                LtLog.e(mFairy.getLineInfo("前往熔岩洞"));
                seekNPC(660, 463, 661, 464,"m85.png");
                break;

            case 86:
                LtLog.e(mFairy.getLineInfo("前往远古墓穴"));
                seekNPC(570, 399, 571, 400,"m86.png");
                break;

            case 87:
                LtLog.e(mFairy.getLineInfo("前往无常殿"));
                seekNPC(892, 313, 893, 314,"m87.png");
                break;

            case 88:
                LtLog.e(mFairy.getLineInfo("前往神之矿区"));
                seekNPC(892, 313, 893, 314,"m88.png");
                break;

        }
    }

    public void close(int count) throws Exception {

        for (int i = 0; i < count; i++) {

            result = mFairy.findPic(437, 0, 1279, 319, "close1.png");
            mFairy.onTap(0.75f, result, "close", 1000);


        }

    }

    public void cunc() throws Exception {
        new TaskContent(mFairy, "存仓") {

            void init() throws Exception {
                gamePublicFuntion.close(3);
                setTaskName(1);
            }

            void content_01() throws Exception {

                result = mFairy.findPic(954, 360, 1274, 685, "cang.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "仓库", 1000);
                } else {
                    result = mFairy.findPic("set2.png");
                    mFairy.onTap(0.8f, result, "扩展栏", 1000);
                }


                result = mFairy.findPic(373, 415, 568, 574, "cang1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("仓库界面"));


                    int c = 0;
                    boolean cBol  = true;
                    oneSecond=0;

                    for (int i = 0; i < 50; i++) {


                        if(cBol==false){//没有需要存的物品结束循环

                            if(oneSecond()) {
                                mFairy.onTap(1245, 211, 1259, 230, "点击第二页", 2000);
                                i=0;
                                continue;
                            }

                            break;
                        }

                        if(i>8){
                            cBol = false;
                        }


                        result = mFairy.findPic("cc.png");
                        if(result.sim<0.8f){//  634,195   634,274

                            if(c>4){
                                LtLog.e(mFairy.getLineInfo("仓库满了"));
                                break;
                            }

                            mFairy.onTap(616,177+(c*80),617,178+(c*80),"",1500);
                            c++;

                        }

                        result = mFairy.findPic(30, 436, 188, 558, "cun1.png");
                        mFairy.onTap(0.8f, result, "快速存储", 1000);

                        if (AtFairyConfig.getOption("cuncang1").equals("1")) {//经验珠子
                            result = mFairy.findPic(654, 24, 1224, 537, "jyzhu.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "经验珠子", 2000);
                                cBol=true;
                            }
                        }

                        if (AtFairyConfig.getOption("cuncang2").equals("1")) {//结晶
                            result = mFairy.findPic(654, 24, 1224, 537, new String[]{"jj1.png", "jj2.png", "jj3.png", "jj4.png"});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "结晶", 2000);
                                cBol=true;
                            }
                        }

                        if (AtFairyConfig.getOption("cuncang3").equals("1")) {//弑神令牌
                            result = mFairy.findPic(654, 24, 1224, 537, new String[]{"shis.png", "shis1.png"});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "弑神令牌", 2000);
                                cBol=true;
                            }
                        }


                        if (AtFairyConfig.getOption("cuncang4").equals("1")) {//兵魂
                            result = mFairy.findPic(654, 24, 1224, 537, new String[]{"bing.png"});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "兵魂", 2000);
                                cBol=true;
                            }
                        }

                        if (AtFairyConfig.getOption("cuncang5").equals("1")) {//生肖碎片
                            result = mFairy.findPic(654, 24, 1224, 537, new String[]{"sx.png"});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "生肖碎片", 2000);
                                cBol=true;
                            }
                        }

                        //  650,24   724,25   652,97
                        // 75  75

                        if (AtFairyConfig.getOption("cuncang6").equals("1")) {//红闪装备
                            for (int i1 = 0; i1 < 5; i1++) {
                                for (int i2 = 0; i2 < 8; i2++) {//1176 16 1250 35   650,23 724,26   651,16,725,35
                                    //1147,22,1215,38

                                    long color = mFairy.getColorNum(651 + (i2 * 71), 16 + (i1 * 72), 723 + (i2 * 71), 35 + (i1 * 72), "255,133,127", 0.9f);
                                    // LtLog.e((651+(i2*71))+","+(16+(i1*72))+","+(723+(i2*71))+","+(35+(i1*72)));

                                    if (color > 25) {
                                        mFairy.tap(691 + (i2 * 71), 62 + (i1 * 72));
                                        cBol = true;
                                    }
                                }

                            }
                        }

                        if (AtFairyConfig.getOption("cuncang7").equals("1")) {//神龙币

                            result = mFairy.findPic(654, 24, 1224, 537, new String[]{"shenlong.png"});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "神龙币", 2000);
                                cBol=true;
                            }

                        }

                        if (AtFairyConfig.getOption("cuncang8").equals("1")) {//铸造图纸

                            result = mFairy.findPic(654, 24, 1224, 537, new String[]{"tuzhi.png"});
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "铸造图纸", 2000);
                                cBol=true;
                            }
                        }

                        result = mFairy.findPic(1029, 366, 1220, 457, "zheng.png");
                        mFairy.onTap(0.8f, result, "整理", 1000);



                    }
                    gamePublicFuntion.close(3);
                    setTaskEnd();
                    return;
                }

                timeCount(5, 0);
            }
        };

    }//存仓

    public void gmhc() throws Exception {

        for (int i = 0; i < 30; i++) {

            LtLog.e(mFairy.getLineInfo("购买回城"));

            result = mFairy.findPic(979, 23, 1168, 671, "sd.png");
            if (result.sim > 0.8f) {
                result = mFairy.findPic(979, 23, 1168, 671, "sd2.png");
                mFairy.onTap(0.8f, result, "补给", 1000);

                result = mFairy.findPic(231, 126, 1044, 537, "sd3.png");
                mFairy.onTap(0.8f, result, "回城石", 1000);

                result = mFairy.findPic(732, 305, 1001, 563, "gm.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "购买", 1000);
                    close(3);
                    return;
                }
            } else {

                close(3);

                result = mFairy.findPic(964, 383, 1273, 681, "sd1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "商店", 1500);
                } else {
                    mFairy.onTap(1231, 322, 1245, 334, "", 1500);
                }

            }

        }

    }

    private long limErrClose_time = System.currentTimeMillis();

    public void limErrClose() throws Exception {

        result = mFairy.findPic(437, 0, 1279, 319, "close1.png");
        if (result.sim > 0.8f) {
            if (System.currentTimeMillis() - limErrClose_time > 20000) {
                mFairy.onTap(0.85f, result, "close", 1000);
                limErrClose_time = System.currentTimeMillis();
            }

        } else {
            limErrClose_time = System.currentTimeMillis();
        }


    }

    public boolean mainScene() throws Exception {
        result = mFairy.findPic(1126, 68, 1273, 210, "ms1.png");
        if (result.sim > 0.8f) {
            return true;
        }

        return false;
    }

    public boolean lmiMainScenes() throws Exception {
        result = mFairy.findPic(1097, 23, 1279, 75, new String[]{"lmiMain1.png","slzy1.png","lmiMain2.png","lmiMain3png","lmiMain4.png","lmiMain5.png"});
        if (result.sim > 0.8f) {
            return true;
        }

        return false;
    }

    public void autoBattle() throws Exception {
        result = mFairy.findPic("autoBattle.png");
        mFairy.onTap(0.8f, result, "自动战斗", 1000);

    }

    public void quxiaoBattle() throws Exception {
        result = mFairy.findPic("battle1.png");
        mFairy.onTap(0.8f, result, "取消", 1000);

    }

    public void rany() throws Exception {

        Random m = new Random();

        mFairy.onTap(1197, 126, 1209, 149, "", 1500);
        //468,239,541,266  496,270
        for (int i = 0; i < 10; i++) {
            int x = m.nextInt(500) + 330;
            int y = m.nextInt(200) + 250;
            mFairy.onTap(x, y, x + 1, y + 1, "随机移动", 1000);

            long l = mFairy.getColorNum(x - 20, y - 30, x + 35, y - 10, "5,235,5", 0.9f);
            if (l > 5) {
                Thread.sleep(10000);
                return;
            }
            Thread.sleep(100);
        }
    }//随机移动

    public void ranytou() throws Exception {


        mFairy.touchDown(170, 541);

        Random random = new Random();

        switch (random.nextInt(4)) {

            case 0:
                mFairy.touchMove(random.nextInt(200) + 50, 441, 5000);
                break;
            case 1:
                mFairy.touchMove(random.nextInt(200) + 50, 643, 5000);
                break;
            case 2:
                mFairy.touchMove(43, random.nextInt(200) + 400, 5000);
                break;
            case 3:
                mFairy.touchMove(282, random.nextInt(200) + 400, 5000);
                break;
        }

        mFairy.touchUp();

    }//随机跑位

    public void test() throws Exception {




            result = mFairy.findPic(954, 360, 1274, 685, "cang.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "仓库", 1000);
            } else {
                result = mFairy.findPic("set2.png");
                mFairy.onTap(0.8f, result, "扩展栏", 1000);
            }


            result = mFairy.findPic(373, 415, 568, 574, "cang1.png");
            if (result.sim > 0.8f) {

                LtLog.e(mFairy.getLineInfo("仓库界面"));


                int c = 0;
                boolean cBol  = true;


                for (int i = 0; i < 50; i++) {


                    if(cBol==false){//没有需要存的物品结束循环




                        break;
                    }

                    if(i>8){
                        cBol = false;
                    }


                    result = mFairy.findPic("cc.png");
                    if(result.sim<0.8f){//  634,195   634,274

                        if(c>4){
                            LtLog.e(mFairy.getLineInfo("仓库满了"));
                            break;
                        }

                        mFairy.onTap(616,177+(c*80),617,178+(c*80),"",1500);
                        c++;

                    }

                    result = mFairy.findPic(30, 436, 188, 558, "cun1.png");
                    mFairy.onTap(0.8f, result, "快速存储", 1000);


                        result = mFairy.findPic(654, 24, 1224, 537, "jyzhu.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "经验珠子", 2000);
                            cBol=true;
                        }


                        result = mFairy.findPic(654, 24, 1224, 537, new String[]{"jj1.png", "jj2.png", "jj3.png", "jj4.png"});
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "结晶", 2000);
                            cBol=true;
                        }



                        result = mFairy.findPic(654, 24, 1224, 537, new String[]{"shis.png", "shis1.png"});
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "弑神令牌", 2000);
                            cBol=true;
                        }

                        result = mFairy.findPic(654, 24, 1224, 537, new String[]{"bing.png"});
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "兵魂", 2000);
                            cBol=true;
                        }



                        result = mFairy.findPic(654, 24, 1224, 537, new String[]{"sx.png"});
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "生肖碎片", 2000);
                            cBol=true;
                        }


                    //  650,24   724,25   652,97
                    // 75  75


                        for (int i1 = 0; i1 < 5; i1++) {
                            for (int i2 = 0; i2 < 8; i2++) {//1176 16 1250 35   650,23 724,26   651,16,725,35
                                //1147,22,1215,38

                                long color = mFairy.getColorNum(651 + (i2 * 71), 16 + (i1 * 72), 723 + (i2 * 71), 35 + (i1 * 72), "255,133,127", 0.9f);
                                // LtLog.e((651+(i2*71))+","+(16+(i1*72))+","+(723+(i2*71))+","+(35+(i1*72)));

                                if (color > 25) {
                                    mFairy.tap(691 + (i2 * 71), 62 + (i1 * 72));
                                    cBol = true;
                                }
                            }

                        }




                        result = mFairy.findPic(654, 24, 1224, 537, new String[]{"shenlong.png"});
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "神龙币", 2000);
                            cBol=true;
                        }





                        result = mFairy.findPic(654, 24, 1224, 537, new String[]{"tuzhi.png"});
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "铸造图纸", 2000);
                            cBol=true;
                        }


                    result = mFairy.findPic(1029, 366, 1220, 457, "zheng.png");
                    mFairy.onTap(0.8f, result, "整理", 1000);



                }

                return;
            }


        }


    final long MAXXUE = 1500;

    public int currentXue() throws Exception {
        result = mFairy.findPic("xue.png");
        if (result.sim > 0.8f) {
            long xg = mFairy.getColorNum(470, 567, 484, 675, "71,72,72", 0.8f);
            if (xg > (MAXXUE * 0.9)) {
                return 1;
            } else if (xg > (MAXXUE * 0.8)) {
                return 2;
            } else if (xg > (MAXXUE * 0.7)) {
                return 3;
            } else if (xg > (MAXXUE * 0.6)) {
                return 4;
            } else if (xg > (MAXXUE * 0.5)) {
                return 5;
            } else if (xg > (MAXXUE * 0.4)) {
                return 6;
            } else if (xg > (MAXXUE * 0.3)) {
                return 7;
            } else if (xg > (MAXXUE * 0.2)) {
                return 8;
            } else if (xg > (MAXXUE * 0.1)) {
                return 9;
            }
        }
        return 10;
    }

}
