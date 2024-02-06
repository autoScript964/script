package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;


public class Abnormal {

    AtFairyImpl mFairy;
    FindResult result;
    PublicFunction publicFunction;
    GamePublicFuntion gamePublicFuntion;

    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
        gamePublicFuntion = new GamePublicFuntion(mFairy);
    }

    int err;
    long time_1 = System.currentTimeMillis();
    public void erro() throws Exception {

        if ((System.currentTimeMillis() - time_1) > 20000) {
            result = mFairy.findPic("dsyj2.png");
            if (result.sim > 0.85f) {
                mFairy.initMatTime();
            }

            LtLog.e(mFairy.getLineInfo("【寻路异常超时:" + err + "】"));
            long time = mFairy.mMatTime(1, 1, 1270, 710, 0.99f);
            LtLog.e(mFairy.getLineInfo("【游戏卡屏计时:" + time + "】"));
            if (time >= 300) {
                LtLog.e(mFairy.getLineInfo("游戏遇到卡屏<<<<重启游戏"));
                LtLog.e(mFairy.getLineInfo("游戏遇到卡屏<<<<重启游戏"));
                LtLog.e(mFairy.getLineInfo("游戏遇到卡屏<<<<重启游戏"));
                mFairy.killUserGame();
                mFairy.initMatTime();
            }
            time_1 = System.currentTimeMillis();
        }

        result = mFairy.findPic("hb.png");
        mFairy.onTap(0.85f, result, "err红包", 1000);

        result = mFairy.findPic("hb2.png");
        mFairy.onTap(0.85f, result, "err黄包", 1000);

        result = mFairy.findPic("new sure.png");
        mFairy.onTap(0.8f, result, "err新版qq隐私政策同意", 1000);

        result = mFairy.findPic("new authorization.png");
        mFairy.onTap(0.8f, result, "err新版qq授权", 1000);

        result = mFairy.findPic(new String[]{"new login.png","new login2.png","new login3.png"});
        mFairy.onTap(0.8f, result, "err新版qq登陆", 1000);



        result = mFairy.findPic("jia4.png");
        mFairy.onTap(0.9f, result, 1206,41,1234,66,"err 关闭 分享有礼", 1000);

        /*result = mFairy.findPic("wang.png");
        mFairy.onTap(0.9f, result,607,492,660,514, "err网络超时", 1000);
*/
        result = mFairy.findPic("emailerr.png");
        mFairy.onTap(0.9f, result, 614,496,658,508,"err 邮箱满了", 1000);

        result = mFairy.findPic("tyf.png");
        mFairy.onTap(0.8f, result, 517, 485, 518, 486, "err取消更新体验服", 1000);

        result = mFairy.findPic("leftZoom.png");
        mFairy.onTap(0.9f, result, "err左侧缩放栏", 1000);

        result = mFairy.findPic("yy1.png");
        mFairy.onTap(0.85f, result, "err关闭语音", 1000);

        result = mFairy.findPic("err7.png");
        mFairy.onTap(0.85f, result, "err 知道了", 500);

        result = mFairy.findPic(428,527,827,714,"jixu.png");
        mFairy.onTap(0.85f, result, "点击继续  ", 1000);

        result = mFairy.findPic(399, 205, 871, 416, "fz15.png");
        if (result.sim > 0.85f) {
            mFairy.killUserGame();
            publicFunction.waitGameReset(1000);
        }

        /*result = mFairy.findPic("dinghao.png");
        if (result.sim > 0.85f) {
            mFairy.finish(AtFairyConfig.getTaskID(), 6);
        }*/

        result = mFairy.findPic("erryp.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "err启动", 10000);
            mFairy.onTap(652, 310, 655, 315, "跳过动画", 1000);
            mFairy.onTap(652, 310, 655, 315, "跳过动画", 1000);
        }

        result = mFairy.findPic("yszc.png");
        mFairy.onTap(0.9f, result, 859, 500, 860, 502, "隐私政策", 1000);

        result = mFairy.findPic("err6.png");
        mFairy.onTap(0.85f, result, 622,496,658,510, "网络超时", 1000);

        result = mFairy.findPic(659,532,1001,683,new String[]{"qq.png","qq1.png"});
        mFairy.onTap(0.75f, result, "errQQ登录", 2000);

        result = mFairy.findPic(24, 549, 714, 1155, "checkvx3.png");
        mFairy.onTap(0.8f, result, "errqq登录角色", 1000);

        result = mFairy.findPic(24, 549, 714, 1155, "checkvx4_1.png");
        mFairy.onTap(0.8f, result, "errqq登录角色", 1000);

        result = mFairy.findPic( new String[]{"checkvx5.png","checkvx5_1.png"});
        mFairy.onTap(0.8f, result, "err完成qq授权", 1000);

        result = mFairy.findPic(439,379,887,625,new String[]{"login.png"});
        mFairy.onTap(0.75f, result, "err开始游戏", 1000);

        result = mFairy.findPic(1001,618,1250,713,"login1.png");
        mFairy.onTap(0.75f, result, "err进入游戏", 1000);

        result = mFairy.findPic(915, 362, 1145, 468, "no.png");
        mFairy.onTap(0.8f, result, "err取消队伍邀请", 1000);

        result = mFairy.findPic(332, 203, 948, 450, "guan.png");
        if (result.sim > 0.8f) {
            mFairy.finish(AtFairyConfig.getTaskID(), 7701);
        } else {
            result = mFairy.findPic(500, 402, 775, 585, "ok3.png");
            mFairy.onTap(0.8f, result, "err确认", 1000);
        }

        result = mFairy.findPic(439, 523, 633, 683, "err1.png");
        mFairy.onTap(0.85f, result, "err关闭", 1000);

        result = mFairy.findPic("err5.png");
        mFairy.onTap(0.85f, result, 735, 478, 778, 497, "err关闭", 1000);

        result = mFairy.findPic(497,414,883,652,"songchu.png");
        mFairy.onTap(0.85f, result, "err送出", 1000);

        result = mFairy.findPic("hb1.png");
        mFairy.onTap(0.9f, result, 1148, 588, 1150, 590, "err点击屏幕继续", 1000);

        result = mFairy.findPic("fh.png");
        mFairy.onTap(0.85f, result, "err返回游戏", 1000);

        result = mFairy.findPic("close7.png");
        mFairy.onTap(0.85f, result, "err关闭", 1000);

        result = mFairy.findPic(1081, 347, 1204, 443, "jia1.png");
        if (result.sim > 0.8f) {
            result = mFairy.findPic("hhdh5.png");
            mFairy.onTap(0.85f, result, "恭喜", 1000);

            mFairy.onTap(1254, 379, 1255, 380, "关闭花神宝箱", 1000);
        }

        result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
        if (result.sim >0.85f) {
            err=0;
        }

        result = mFairy.findPic("findWay.png");
        if (result.sim > 0.8f) {
            err++;
        } else {
            err = 0;
        }

        if (err > 100) {
            mFairy.ranSwipe(1058, 526, 1079, 548, 1, 1000, (long)2000);
            gamePublicFuntion.err();
            err = 0;
        }

    }
}