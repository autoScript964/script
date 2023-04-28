package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

public class GamePublicFuntion {
    public AtFairyImpl mFairy;
    public FindResult result;
    public long time = System.currentTimeMillis();
    public static boolean activity_bool = false;

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
    }

    public int judgeStop = 0;

    public Boolean judgeStop(int m, boolean bool, float sim) throws Exception {
        long num = mFairy.mMatTime(1216, 124, 59, 13, sim);
        if (num <= 1 && bool) {
            judgeStop++;
            if (judgeStop >= 50) {
                judgeStop = 0;
                return true;
            }
        }

        if (num > 1) {
            LtLog.e(mFairy.getLineInfo("发呆计次" + num));
        }

        if (num >= m) {
            judgeStop = 0;
            return true;
        } else {
            TaskContent.err = 0;
        }
        return false;
    }//发呆判断

    public boolean judgeBattle() throws Exception {
        if (mFairy.getColorNum(384, 24, 392, 33, 0.95f, 0, "242,66,66") > 30) {
            return true;
        }
        return false;
    }//判断战斗

    public void close(int num) throws Exception {
        for (int i = 0; i < num; i++) {

            result = mFairy.findPic(787, 4, 1271, 363, "close1.png");
            mFairy.onTap(0.9f, result, "close1", 1500);

            result = mFairy.findPic(910, 2, 1275, 387, "close2.png");
            mFairy.onTap(0.9f, result, "close2", 1500);

            result = mFairy.findPic("close3.png");
            mFairy.onTap(0.9f, result, "close3", 1500);

            result = mFairy.findPic("close3.png");
            mFairy.onTap(0.9f, result, "close3", 1500);

            result = mFairy.findPic(972, 2, 1272, 345, "close4.png");
            mFairy.onTap(0.9f, result, "close4", 1500);

            result = mFairy.findPic("close6.png");
            mFairy.onTap(0.9f, result, "close6", 1500);

            result = mFairy.findPic(745,8,1270,270, "close7.png");
            mFairy.onTap(0.9f, result, "close7", 1500);

            result = mFairy.findPic( "close8.png");
            mFairy.onTap(0.9f, result, "close8", 1500);
        }
    }//关闭

    public Boolean fb() throws Exception {
        result = mFairy.findPic(938, 182, 992, 241, new String[]{"fb.png", "fb1.png"});
        if (result.sim > 0.72f) {
            return true;
        }
        return false;
    }//副本

    public boolean mainScene() throws Exception {
        result = mFairy.findPic(9, 77, 224, 146, new String[]{"task.png", "rank.png"});
        if (result.sim > 0.8f) {
            return true;
        } else if (mFairy.findPic("package.png").sim > 0.8f) {
            return true;
        }
        return false;
    }//主场景

    public int rank_num() throws Exception {
        result = mFairy.findPic(320, 274, 451, 417, "rank17.png");
        if (result.sim > 0.8f) {
            return 1;
        }
        result = mFairy.findPic(576, 274, 705, 416, "rank17.png");
        if (result.sim > 0.8f) {
            return 2;
        }
        result = mFairy.findPic(832, 274, 959, 408, "rank17.png");
        if (result.sim > 0.8f) {
            return 3;
        }
        result = mFairy.findPic(950, 283, 1201, 411, "rank17.png");
        if (result.sim > 0.8f) {
            return 4;
        }
        return 5;
    }//返回队伍人数


    /**
     * @throws Exception
     */
    public void yan() throws Exception {
        result = mFairy.findPic("yanjing.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "眼", 2000);
            mFairy.initMatTime();
        }
    }

    public void zui() throws Exception {
        result = mFairy.findPic("zui.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "嘴", 2000);
            mFairy.initMatTime();
        }
    }

    public void shou() throws Exception {
        result = mFairy.findPic("shou.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "手", 2000);
            mFairy.initMatTime();
        }
    }

    public void quan() throws Exception {
        result = mFairy.findPic("quan.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "拳", 2000);
            mFairy.initMatTime();
        }
    }

    public void fu() throws Exception {
        result = mFairy.findPic("fu.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "符", 2000);
            mFairy.initMatTime();
        }
    }

    public void du() throws Exception {
        result = mFairy.findPic("du.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "阅读", 2000);
            mFairy.initMatTime();
        }
    }

    public void lian() throws Exception {
        result = mFairy.findPic("lian.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "炼", 2000);
            mFairy.initMatTime();
        }
    }

    public void tong() throws Exception {
        result = mFairy.findPic("tong.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, 836, 364, 862, 387, "通", 2000);
            mFairy.initMatTime();
        }
    }

    public boolean gm() throws Exception {
        result = mFairy.findPic(901, 466, 1164, 702, new String[]{"gm4.png"});
        if (result.sim > 0.75f) {
            mFairy.onTap(0.75f, result, "购买", 500);

            for (int i = 0; i < 5; i++) {

                result = mFairy.findPic(395, 178, 727, 493, "bao9.png");
                if (result.sim > 0.8f) {
                    close(3);
                    mFairy.finish(AtFairyConfig.getTaskID(), 1301);
                }
            }

            result = mFairy.findPic("gm3.png");
            if (result.sim > 0.75f) {
                mFairy.finish(AtFairyConfig.getTaskID(), 10301);
            }

            return true;
        }

        return false;
    }//购买

    public boolean lv() throws Exception {
        result = mFairy.findPic(572, 365, 656, 523, new String[]{"lv.png", "xun.png"});
        if (result.sim > 0.72f) {
            TaskContent.err = 0;
            judgeStop = 0;
            mFairy.initMatTime();
            return true;
        }
        return false;
    }

    public void auto() throws Exception {
        if (fhs() == false) {
            result = mFairy.findPic("auto.png");
            mFairy.onTap(0.93f, result, "自动战斗", 1000);
        }

    }//自动战斗

    public void autoNew() throws Exception {
        if (fhs() == false) {
            result = mFairy.findPic("auto.png");
            if(result.sim>0.7f){
                long l = mFairy.getColorNum(577,592,598,604,"255,183,63",0.9f);
                if(l<50){
                    mFairy.onTap(0.7f, result, "自动战斗", 1000);
                }
            }
        }
    }//自动战斗

    public void auto1(int num) throws Exception {
        for (int i = 0; i < num; i++) {

            result = mFairy.findPic("auto1.png");
            mFairy.onTap(0.93f, result, "关闭-自动战斗", 500);
        }
    }//自动战斗

    public void use() throws Exception {
        result = mFairy.findPic(827, 345, 1165, 609, "use.png");
        mFairy.onTap(0.8f, result, "使用", 500);

    }

    public void skipScene() throws Exception {
        long c = mFairy.getColorNum(4, 714, 122, 718, 0.98f, 0, "255,160,74");
        if (c > 200) {
            TaskContent.err = 0;
            mFairy.initMatTime();
        }
    }

    public boolean deng() throws Exception {
        long c = mFairy.getColorNum(524, 492, 571, 498, 0.95f, 0, "255,219,75");
        if (c > 50) {
            TaskContent.err = 0;
            judgeStop = 0;
            Thread.sleep(1000);
            mFairy.initMatTime();
            return true;
        }
        return false;
    }

    public FindResult qx() throws Exception {
        return mFairy.findPic(233, 187, 660, 628, "qx.png");
    }//取消

    public void fh() throws Exception {
        result = mFairy.findPic("fh.png");
        mFairy.onTap(0.8f, result, "复活", 1000);
    }//复活

    public boolean choice_map(int i) throws Exception {
        String map;
        int[] position;
        switch (i) {
            case 1:
                map = "m1.png";//长安
                position = new int[]{790, 171, 815, 195};
                break;
            case 2:
                map = "m2.png";//大唐国境
                position = new int[]{606, 304, 628, 325};
                break;
            case 3:
                map = "m3.png";//江南野外
                position = new int[]{864, 296, 888, 320};
                break;
            case 4:
                map = "m4.png";//东海海滨
                position = new int[]{1012, 343, 1034, 374};
                break;
            case 5:
                map = "m5.png";//大唐境外
                position = new int[]{423, 206, 447, 222};
                break;
            case 6:
                map = "m6.png";//花果山
                position = new int[]{1205, 380, 1225, 399};
                break;
            default:
                return false;
        }

        result = mFairy.findPic(map);
        if (result.sim > 0.8f) {
            return true;
        } else {
            result = mFairy.findPic(1034, 552, 1153, 657, "map2.png");
            mFairy.onTap(0.8f, result, "世界地图", 500);
        }
        result = mFairy.findPic("map1.png");
        mFairy.onTap(0.85f, result, position[0], position[1], position[2], position[3], "长安城", 800);
        return false;

    }//选择地图

    public boolean fhs() throws Exception {
        result = mFairy.findPic(new String[]{"fu1.png", "fh.png", "fh4.png"});
        if (result.sim > 0.8f) {
            return true;
        }
        return false;
    }//复活

    public void fhs(int i) throws Exception {
        switch (i) {
            case 1:
                result = mFairy.findPic(517, 133, 761, 460, "nn47.png");
                mFairy.onTap(0.8f, result, "原地复活", 500);
                break;
            case 2:
                result = mFairy.findPic(517, 133, 761, 460, "fh1.png");
                mFairy.onTap(0.8f, result, "就近复活", 500);
                for (int j = 0; j < 3; j++) {

                    result = mFairy.findPic(548, 156, 619, 366, "fh2.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(517, 133, 761, 460, "fh3.png");
                        mFairy.onTap(0.8f, result, "等待救援", 500);
                        return;
                    }
                }
                break;
            case 3:
                break;
        }

    }//复活类型

    public void chat() throws Exception {
        while (mFairy.condit()) {
            result = mFairy.findPic(1192, 549, 1262, 705, "chat.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "聊天", 500);
            } else {
                break;
            }
        }
    }//聊天

    public void chat2() throws Exception {
        result = mFairy.findPic("chat2.png");
        mFairy.onTap(0.7f, result, 1212, 52, 1228, 66, "chat2", 1000);
    }

    public void task() throws Exception {
        result = mFairy.findPic(97, 72, 206, 142, new String[]{"rank.png","rank29.png"});
        mFairy.onTap(0.92f, result, 56, 106, 85, 116, "任务", 500);

    }//任务

    public void rank() throws Exception {
        result = mFairy.findPic(35, 87, 106, 135, new String[]{"task.png","task2.png"});
        mFairy.onTap(0.96f, result, 136, 108, 170, 116, "队伍", 3000);

    }//队伍

    public void clickRank() throws Exception {
        result = mFairy.findPic(109, 85, 195, 134, new String[]{"rank.png", "rank4.png","rank26.png"});
        mFairy.onTap(0.75f, result, "clickRank", 1000);
    }//点击队伍

    public boolean in_rank() throws Exception {
        result = mFairy.findPic(13, 427, 52, 470, "rank8.png");
        if (result.sim > 0.8f) {
            return true;
        }
        return false;
    }//在队伍中

    public int xian = 2;

    public void huanxian() throws Exception {
        result = mFairy.findPic("cy4.png");
        if (result.sim > 0.8f) {
            if (114 + ((xian - 1) * 75) > 719) {
                xian = 2;
                return;
            }
            long i = mFairy.getColorNum(1027, 42 + ((xian - 1) * 75), 1107, 114 + ((xian - 1) * 75), 0.95f, 0, "58,54,53");
            LtLog.e("颜色数量" + i);
            if (i > 1000) {
                mFairy.onTap(1027, 42 + ((xian - 1) * 75) + 30, 1107, 114 + ((xian - 1) * 75) - 30, "换线", 1000);
                xian++;
            } else {
                mFairy.onTap(1029, 74, 1068, 89, "", 500);
                xian = 2;
            }
        }

    }//换线

    public int errClick = 0;

    public void errClick() throws Exception {
        if (mainScene() == false) {
            errClick++;
            if (errClick > 3) {
                errClick = 0;
                mFairy.onTap(582, 323, 622, 375, "errClick", 1000);
            }
        } else {
            errClick = 0;
        }
    }//异常点击

    private int init_count = 0;

    public void init(int type) throws Exception {
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("init >>>"));




            result = mFairy.findPic(938, 182, 992, 241, new String[]{"fb.png", "fb1.png"});
            if (result.sim > 0.72f) {
                mFairy.onTap(0.72f, result, "副本中 退出副本", 1500);
                mFairy.onTap(803, 437, 855, 450, "退出副本", 2000);

                result = mFairy.findPic(341, 260, 899, 397, "ptfb12.png");
                mFairy.onTap(0.8f, result, 814, 437, 853, 454, "放弃副本", 2000);

            } else {
                if (type == 1) {

                    auto1(3);

                    result = mFairy.findPic("jihe.png");
                    mFairy.onTap(0.72f, result, "集合", 500);

                    result = mFairy.findPic("activity.png");
                    if (result.sim > 0.75f) {
                        mFairy.onTap(0.75f, result, "活动", 2000);
                        activity_bool = false;
                    }

                    result = mFairy.findPic("activity1.png");
                    if (result.sim > 0.8f) {
                        activity_bool = true;
                        return;
                    }
                }
            }

            close(2);

            fh();

            fhs(2);

            chat2();

            result = mFairy.findPic(577, 275, 683, 649, "ok.png");
            mFairy.onTap(0.8f, result, "ok", 500);

            result = mFairy.findPic("ptfb18.png");
            mFairy.onTap(0.8f, result, "继续", 500);

            result = mFairy.findPic("input3.png");
            mFairy.onTap(0.8f, result, "取消输入框", 500);

            result = mFairy.findPic("zoom1.png");
            mFairy.onTap(0.85f, result, "zoom1", 500);

            FindResult qx = qx();
            mFairy.onTap(0.8f, qx, "取消", 500);

            if (type != 1) {
                if (mainScene()) {
                    result = mFairy.findPic("jihe.png");
                    mFairy.onTap(0.72f, result, "集合", 500);
                    auto1(3);
                    return;
                }
            }
        }
    }//

    public void activity_type(int type) throws Exception {
        switch (type) {
            case 1:
                mFairy.onTap(1177,160,1195,200, "", 500);
                break;
            case 2:
                mFairy.onTap(1178,276,1201,325, "", 500);
                break;
            case 3:
                mFairy.onTap(1195,416,1200,433, "", 500);
                break;
            case 4:
                mFairy.onTap(1180,509,1194,548, "", 500);
                break;
        }


    }//活动类型

}
