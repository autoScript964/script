package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

public class GamePublicFuntion {
    public AtFairyImpl mFairy;
    public FindResult result;
    public long time = System.currentTimeMillis();

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
    }

    public void zhizuo() throws Exception {
        result = mFairy.findPic(445, 510, 566, 609, "zhizuo1.png");
        mFairy.onTap(0.8f, result, "制作", 500);
    }//制作

    public boolean fh() throws Exception {
        result = mFairy.findPic("jia3.png");
        mFairy.onTap(0.85f,result,"打开复活兰",500);

        result = mFairy.findPic("fh1.png");
        if (result.sim > 0.8f) {
            TaskContent.err = 0;
            mFairy.onTap(0.8f, result, "复活", 500);
            return true;
        }
        return false;
    }//复活

    public void skip()throws Exception{
        result = mFairy.findPic("skip.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "使用", 500);
            mFairy.initMatTime();
            TaskContent.err = 0;
        }
    }//跳过

    public void chat_sub() throws Exception {
        result = mFairy.findPic(849, 3, 1246, 526, "chat_sub.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "chat_sub", 500);
            mFairy.initMatTime();
            TaskContent.err = 0;
        }
    }//聊天

    public void chat_skip() throws Exception {
        result = mFairy.findPic(1045, 455, 1142, 696, "chat1.png");
        if (result.sim > 0.75f) {
            mFairy.onTap(0.75f, result, "chat", 500);
            mFairy.initMatTime();
            TaskContent.err = 0;
        }
    }//聊天

    public void sub() throws Exception {
        result = mFairy.findPic(961, 345, 1152, 483, "sub1.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "提交", 500);
            mFairy.initMatTime();
            TaskContent.err = 0;
        }
    }//提交

    public void use() throws Exception {
        result = mFairy.findPic(985, 376, 1102, 444, new String[]{"use.png", "use1.png"});
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "使用", 500);
            mFairy.initMatTime();
            TaskContent.err = 0;
        }
    }//使用

    public void close_use() throws Exception {
        result = mFairy.findPic(1105, 140, 1180, 221, "close_use.png");
        mFairy.onTap(0.8f, result, "close_use", 500);
    }//使用

    public void click_ta(String s[]) throws Exception {
        result = mFairy.findPic(849, 3, 1246, 526, s);
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "click_ta", 800);
            mFairy.initMatTime();
            TaskContent.err = 0;
        }
    }//点击

    private int judgeStop = 0;

    public Boolean judgeStop(int m, boolean bool) throws Exception {

        long num = mFairy.mMatTime(63, 155, 58, 10, 0.95f);
        if (num <= 1 && bool) {
            judgeStop++;
            if (judgeStop >= 50) {
                judgeStop = 0;
                return true;
            }
        }

        if (num > 1) {
            LtLog.e(mFairy.getLineInfo("发呆计次 【" + num + "】"));
        }

        if (num >= m) {
            judgeStop = 0;
            return true;
        } else {
            TaskContent.err = 0;
        }
        return false;
    }//发呆判断

    public void chat_start() throws Exception {
        result = mFairy.findPic(1156, 602, 1266, 693, "chat2.png");
        mFairy.onTap(0.8f, result, "chat_start", 1500);
    }//聊天_start

    public void fuzi_start() throws Exception {


    }//斧子——start

    public void auto_battle(long s) throws Exception {
       /* result = mFairy.findPic("battle1.png");
        if (result.sim > 0.85f) {
            mFairy.onTap(0.85f, result, "点击自动战斗", 1000);
            long l = mFairy.getColorNum(1237, 557, 1250, 570, "239,233,127", 0.95f);
            if (l < 30) {
                result = mFairy.findPic("battle1.png");
                mFairy.onTap(0.85f, result, "", 1000);
            }
            long now = System.currentTimeMillis();
            while (System.currentTimeMillis() - now < s) {
                if (mainScene() == false) {
                    return;
                }
                mFairy.onTap(1115, 554, 1142, 580, "A", 3000);
            }

            result = mFairy.findPic("battle1.png");
            mFairy.onTap(0.85f, result, "", 1000);

        } else {*/

        result = mFairy.findPic("battle2.png");
        mFairy.onTap(0.8f, result, "", 500);

        long now = System.currentTimeMillis();
        while (System.currentTimeMillis() - now < s) {
            if (mainScene() == false) {
                return;
            }
            for (int i = 0; i < 5; i++) {
                mFairy.onTap(1115, 554, 1142, 580, "", 200);
            }
            mFairy.onTap(1011, 642, 1035, 666, "1 skill", 500);

            for (int i = 0; i < 5; i++) {
                mFairy.onTap(1115, 554, 1142, 580, "", 200);
            }
            mFairy.onTap(982, 518, 1009, 544, "2 skill", 500);

            for (int i = 0; i < 5; i++) {
                mFairy.onTap(1115, 554, 1142, 580, "", 200);
            }
            mFairy.onTap(1084, 414, 1114, 447, "3 skill", 500);
            for (int i = 0; i < 5; i++) {
                mFairy.onTap(1115, 554, 1142, 580, "", 200);
            }
            mFairy.onTap(1212, 440, 1236, 468, "4 skill", 500);
        }
        //}
    }//自动战斗

    public void auto_battle() throws Exception {

        result = mFairy.findPic(605, 501, 671, 579, new String[]{"battle3.png"});
        if (result.sim > 0.8f) {
            return;
        }else{
            result = mFairy.findPic("cj3.png");
            mFairy.onTap(0.8f, result, "返回战斗界面", 500);
        }

        result = mFairy.findPic("battle1.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.85f, result, "点击自动战斗", 1000);
            long l = mFairy.getColorNum(1237, 557, 1250, 570, "239,233,127", 0.95f);
            if (l < 30) {
                result = mFairy.findPic("battle1.png");
                mFairy.onTap(0.85f, result, "", 1000);
            }
        }
    }//自动战斗


    private int cj = 0;
    public void auto_cj() throws Exception {

        result = mFairy.findPic("cj2.png");
        if (result.sim > 0.8f) {
            long l = mFairy.getColorNum(1237, 557, 1250, 570, "239,233,127", 0.95f);
            if (l > 30) {
                cj=0;
                return;
            }else{
                cj++;
                if(cj>3) {
                    mFairy.onTap(0.85f, result, "点击采集", 1000);
                }
            }
        }
    }//自动采集

    public void stop_auto() throws Exception {
        for (int i = 0; i < 6; i++) {
            result = mFairy.findPic(538, 489, 765, 593, new String[]{"cj1.png"});
            mFairy.onTap(0.8f,result,1233, 554, 1254, 569, "", 1000);

            result = mFairy.findPic("cj3.png");
            mFairy.onTap(0.8f, result, "返回战斗界面", 500);
        }
    }//停止战斗

    public boolean fbScene() throws Exception {
        result = mFairy.findPic("fb.png");
        if (result.sim > 0.8f) {
            return true;
        }
        return false;

    }//副本场景

    public void auto_prop(String str, long s) throws Exception {
        int err = 0;
        long now = System.currentTimeMillis();
        while (System.currentTimeMillis() - now < s) {
            if (mainScene() == false) {
                return;
            }
            result = mFairy.findPic(1049, 495, 1203, 630, str);
            if (result.sim > 0.8f) {
                err = 0;
                mFairy.onTap(1115, 554, 1142, 580, "A", 200);
            } else {
                err++;
                if (err > 10) {
                    break;
                }
            }
        }

    }//自动道具

    public boolean gmScene() throws Exception {
        result = mFairy.findPic(new String[]{"gmScene.png", "gmScene1.png"});
        if (result.sim > 0.8f) {
            return true;
        }
        return false;
    }//购买场景

    public boolean gm() throws Exception {
        result = mFairy.findPic(900, 516, 1244, 663, new String[]{"gm1.png", "gm3.png"});
        if (result.sim > 0.85f) {
            mFairy.onTap(0.85f, result, "购买", 1000);

            result = mFairy.findPic("gm2.png");
            if (result.sim > 0.85f) {
                mFairy.onTap(0.85f, result, "购买 inner", 1000);
            }

            result = mFairy.findPic(394, 178, 850, 333, "gm4.png");
            if (result.sim > 0.85f) {
                mFairy.onTap(862, 186, 881, 197, "贝币不足", 500);
                mFairy.finish(AtFairyConfig.getTaskID(), 531001);
                return true;
            }

            return true;
        }

        return false;
    }//购买

    public void close(int num) throws Exception {
        for (int i = 1; i <= num; i++) {

            result = mFairy.findPic(5, 3, 101, 59, new String[]{"esc.png", "esc1.png"});
            mFairy.onTap(0.8f, result, "返回", 500);

            result = mFairy.findPic(844, 6, 1273, 332, new String[]{"close1.png"});
            mFairy.onTap(0.8f, result, "关闭", 500);
        }
    }//关闭

    public boolean mainScene() throws Exception {
        result = mFairy.findPic("main1.png");
        if (result.sim > 0.8f) {
            return true;
        }
        return false;

    }//主场景

    public void init(boolean activity_click) throws Exception {
        int err = 0;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("init >>>>>"));

            if (activity_click) {

                result = mFairy.findPic(677, 74, 1215, 253, "activity.png");
                mFairy.onTap(0.8f, result, "活动", 1000);

                result = mFairy.findPic("activity1.png");
                if (result.sim > 0.75f) {
                    return;
                }
            }

            result = mFairy.findPic("right.png");
            mFairy.onTap(0.8f, result, "右", 1000);

            close(1);

            fh();

            result = cancel();
            mFairy.onTap(0.8f, result, "取消 | 拒绝", 500);

            err++;
            if (mainScene()) {
                if (activity_click == false) {
                    return;
                }
            } else {
                if (err > 2) {
                    mFairy.onTap(599, 529, 662, 557, "err", 500);
                    err = 0;
                }
            }
        }


    }

    public void activity_type(int type) throws Exception {
        switch (type) {
            case 1:
                mFairy.onTap(251, 673, 279, 685, "日常", 200);
                mFairy.onTap(251, 673, 279, 685, "日常", 500);
                break;
            case 2:
                mFairy.onTap(437, 674, 477, 688, "限时", 200);
                mFairy.onTap(437, 674, 477, 688, "限时", 500);
                break;
            case 3:
                mFairy.onTap(623, 676, 653, 687, "家园", 200);
                mFairy.onTap(623, 676, 653, 687, "家园", 500);
                break;
            case 4:
                mFairy.onTap(808, 675, 846, 691, "部落", 200);
                mFairy.onTap(808, 675, 846, 691, "部落", 500);
                break;
            case 5:
                mFairy.onTap(985, 678, 1021, 692, "副本", 200);
                mFairy.onTap(985, 678, 1021, 692, "副本", 500);
                break;
        }

        for (int i = 0; i < 5; i++) {
            result = mFairy.findPic(15, 298, 85, 367, "activity2.png");
            if (result.sim > 0.8f) {
                i = 0;
                mFairy.onTap(0.8f, result, "返回", 500);
            }
        }

    }//活动分类

    public FindResult cancel() throws Exception {
        return mFairy.findPic(355, 293, 642, 596, new String[]{"jujue.png", "quxiao1.png"});
    }//取消拒绝

    public void click_rank() throws Exception {
        result = mFairy.findPic(new String[]{"rank1.png", "rank2.png"});
        mFairy.onTap(0.8f, result, "点击队伍", 1000);
    }//点击队伍

    public void click_task() throws Exception {
        result = mFairy.findPic("rank3.png");
        mFairy.onTap(0.8f, result, 85, 212, 119, 223, "点击任务", 1000);
    }//点击任务
}
