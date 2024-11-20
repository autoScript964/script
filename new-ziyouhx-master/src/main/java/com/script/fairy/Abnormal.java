package com.script.fairy;


import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;


/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class Abnormal extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;

    public Abnormal(AtFairyImpl ATFairy) throws Exception {
        mFairy = ATFairy;
    }

    int count_1 = 0;
    String task_id = AtFairyConfig.getOption("task_id");

    //全局处理
    public void erro() throws Exception {
        result = mFairy.findPic(483,19,767,123,"xcmb.png");
        mFairy.onTap(0.8f, result, 1173,82,1181,92,"err星辰秘宝退出", Sleep);

        result = mFairy.findPic(491,11,810,104,"cysc.png");
        mFairy.onTap(0.8f, result, 1202,58,1213,70,"err商城退出", Sleep);

        result = mFairy.findPic(537,38,738,105,"gwsll1.png");
        mFairy.onTap(0.8f, result, 1186,97,1192,102,"err怪物狩猎叉", Sleep);

        result = mFairy.findPic(483, 300, 787, 419, "Slide lock.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("err解锁中"));
            mFairy.ranSwipe(543, 358, 764, 360, 500, (long) 500, 0);
        }
       /* long fada=yxdUtil.mMatTime(616,408,38,23,0.9f);
        if (fada>300){
            LtLog.e(mFairy.getLineInfo("重启游戏"));
            yxdUtil.initMatTime();
            mFairy.killUserGame();
        }*/

        result = mFairy.findPic(386,222,888,492,"rylq.png");
        mFairy.onTap(0.8f, result, 755,449,767,456,"仍要拉取确定", Sleep);

        result = mFairy.findPic(648, 312, 1040, 595, "tongyi.png");
        mFairy.onTap(0.8f, result, "err同意", Sleep);

        result = mFairy.findPic(392, 267, 884, 505, "sszx.png");
        mFairy.onTap(0.8f, result, 513, 452, 520, 463, "err赛事中心取消前往", Sleep);

        result = mFairy.findPic("dialogue.png");
        mFairy.onTap(0.8f, result, "err对话", Sleep);

        result = mFairy.findPic("close5.png");
        mFairy.onTap(0.8f, result, "err 活动弹框 关闭", Sleep);

        result = mFairy.findPic("zhibo.png");
        mFairy.onTap(0.8f, result, "err 直播 关闭", Sleep);

        result = mFairy.findPic("esc.png");
        mFairy.onTap(0.8f, result, "err 返回游戏", Sleep);

        result = mFairy.findPic("close.png");
        if (picCount(0.8f, result, "err guanbi") > 2) {
            mFairy.onTap(0.8f, result, "guanbi", Sleep);
        }

        result = mFairy.findPic("zc1.png");
        if (picCount(0.8f, result, "err zc") > 5) {
            mFairy.onTap(0.8f, result, "战场领双", Sleep);
        }


        result = mFairy.findPic("htmlreturn.png");
        mFairy.onTap(0.8f, result, "err误点到网页返回", Sleep);

        result = mFairy.findPic(664, 475, 1003, 669, "tongy.png");
        mFairy.onTap(0.8f, result, "err同意", 1000);

        result = mFairy.findPic(545, 584, 731, 675, "xyzdl.png");
        mFairy.onTap(0.8f, result, "err知道了", 1000);

        result = mFairy.findPic(548, 420, 732, 488, "queding.png");
        mFairy.onTap(0.8f, result, "err确定", 1000);

        result = mFairy.findPic("new sure.png");
        mFairy.onTap(0.8f, result, "err新版qq隐私政策同意", 1000);

        result = mFairy.findPic("new authorization.png");
        mFairy.onTap(0.8f, result, "err新版qq授权", 1000);

        result = mFairy.findPic(new String[]{"new login.png", "new login2.png", "new login3.png"});
        mFairy.onTap(0.8f, result, "err新版qq登陆", 1000);

        result = mFairy.findPic(149, 935, 548, 1173, "tongyiqq.png");
        mFairy.onTap(0.8f, result, "err新版qq授权", 1000);

        result = mFairy.findPic(166, 934, 543, 1080, "quedingqq.png");
        mFairy.onTap(0.8f, result, "err确定", 1000);

        result = mFairy.findPic(149, 935, 548, 1173, "tongyiqq1.png");
        mFairy.onTap(0.8f, result, "err同意", 1000);

        result = mFairy.findPic("chengjiu.png");
        mFairy.onTap(0.8f, result, "err成就", Sleep);

        result = mFairy.findPic(483, 300, 787, 419, "Slide lock.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("err解锁中"));
            mFairy.ranSwipe(543, 358, 764, 360, 500, (long) 500, 0);
        }

        result = mFairy.findPic("ly1.png");
        mFairy.onTap(0.8f, result, 501, 586, 551, 602, "err良缘界面", Sleep);
        mFairy.onTap(0.8f, result, 1215, 90, 1227, 104, "err良缘界面", Sleep);

        result = mFairy.findPic("huaping.png");
        mFairy.onTap(0.9f, result, "err花屏了点一下", Sleep);

        result = mFairy.findPic("dialogue.png");
        mFairy.onTap(0.8f, result, "err对话", Sleep);

        result = mFairy.findPic("lingqu.png");
        if (picCount(0.8f, result, "err领取") > 2) {
            mFairy.onTap(0.8f, result, "err领取", Sleep);
        }

        result = mFairy.findPic("dutiao.png");
        if (result.sim < 0.9f) {
            result = mFairy.findPic("Use of articles.png");
            if (result.sim > 0.8f) {
                result1 = mFairy.findPic(new String[]{"errbaotu.png", "erreml.png"});
                if (result1.sim < 0.8f) {
                    mFairy.onTap(0.8f, result, "err物品使用", Sleep);
                }
            }
        }

        result = mFairy.findPic("skip.png");
        if (picCount(0.8f, result, "err跳过") > 2) {
            mFairy.onTap(0.8f, result, "err跳过", Sleep);
        }
        result = mFairy.findPic("shengjiyugao.png");
        mFairy.onTap(0.8f, result, 925, 199, 942, 215, "err升级预告", Sleep);

        result1 = mFairy.findPic(1008, 13, 1266, 601, new String[]{"youxuanze.png", "xzdaan.png"});
        result = mFairy.findPic("duihua.png");
        if (picCount(0.8f, result, "errNpc对话") > 2 && result1.sim < 0.7f) {
            mFairy.onTap(0.8f, result, 254, 11, 267, 17, "err对话", Sleep);
        }

        result = mFairy.findPic("baishi.png");
        mFairy.onTap(0.8f, result, 558, 402, 570, 414, "err不在提示", Sleep);
        mFairy.onTap(0.8f, result, 470, 457, 516, 472, "err找徒弟取消", Sleep);

        result = mFairy.findPic("liangyuan.png");
        mFairy.onTap(0.8f, result, 558, 402, 570, 414, "err不在提示", Sleep);
        mFairy.onTap(0.8f, result, 470, 457, 516, 472, "err良缘取消", Sleep);

        result = mFairy.findPic("errkeju.png");
        mFairy.onTap(0.8f, result, 488, 441, 537, 458, "err科举取消", Sleep);

        result = mFairy.findPic("agreefb.png");
        mFairy.onTap(0.8f, result, 753, 529, 798, 546, "err同意进入副本", Sleep);

      /*  result = mFairy.findPic("errzhanchang.png");
        mFairy.onTap(0.8f, result, 490,443,540,458,"err战场取消", Sleep);*/

        result = mFairy.findPic(491, 133, 784, 539, "ssxyfh.png");
        mFairy.onTap(0.8f, result, "err生死相依复活", Sleep);

    /*    result = mFairy.findPic("errlldb.png");
        mFairy.onTap(0.8f, result, 481,446,518,459,"err楼兰夺宝提醒", Sleep);*/

        result = mFairy.findPic("errjrjz.png");
        mFairy.onTap(0.8f, result, 411, 465, 424, 479, "err加入家族弹框", Sleep);
        mFairy.onTap(0.8f, result, 492, 417, 532, 427, "err加入家族弹框", Sleep);

        result = mFairy.findPic(388, 219, 884, 495, "qiudaidui.png");
        mFairy.onTap(0.8f, result, 471, 436, 526, 459, "err请求带队取消", Sleep);

        result = mFairy.findPic("errkjstop.png");
        mFairy.onTap(0.8f, result, 619, 440, 655, 456, "err科举结束确定", Sleep);

        result = mFairy.findPic(418, 302, 876, 398, "jsfh.png");
        mFairy.onTap(0.8f, result, 739, 439, 788, 460, "err接受复活", Sleep);

        result = mFairy.findPic("errxzquxiao.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "err道具选择取消", 500);
            result = mFairy.findPic("jia2.png");
            mFairy.onTap(0.8f, result, "", 500);
        }

        result = mFairy.findPic("login2.png");
        if (result.sim > 0.8f) {
            long l = mFairy.getColorNum(272, 662, 279, 670, "151,151,152", 0.98f);
            LtLog.e(mFairy.getLineInfo("同意:" + l));
            if (l > 2) {
                mFairy.onTap(273, 663, 276, 672, "点同意", 2000);
            }
        }
        result = mFairy.findPic(483, 300, 787, 419, "Slide lock.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("err解锁中"));
            mFairy.ranSwipe(543, 358, 764, 360, 500, (long) 500, 0);
        }

        result = mFairy.findPic("errjlfork.png");
        mFairy.onTap(0.8f, result, "err奖励叉", Sleep);

        result = mFairy.findPic("zhzx.png");
        mFairy.onTap(0.8f, result, 770, 452, 771, 453, "err账号在线", Sleep);

        result = mFairy.findPic("login1.png");
        mFairy.onTap(0.9f, result, "errqq登录", Sleep);

        result = mFairy.findPic(625,112,868,282, new String[]{"wenquan1.png","xq.png"});
        if (result.sim < 0.8f) {
            result = mFairy.findPic(1004, 9, 1144, 78, "zoom1.png");
            mFairy.onTap(0.7f, result, "err打开上边扩展栏", Sleep);
        }

        result = mFairy.findPic("close2.png");
        mFairy.onTap(0.8f, result, "err打开左边扩展栏", Sleep);

        result = mFairy.findPic(393, 223, 886, 493, "qqdd.png");
        mFairy.onTap(0.9f, result, 519, 459, 520, 460, "err有人请求带队", Sleep);

        result = mFairy.findPic("fqkl.png");
        mFairy.onTap(0.8f, result, "err发送口令", Sleep);

        result = mFairy.findPic(549, 536, 735, 599, "shouxia.png");
        mFairy.onTap(0.8f, result, "err红包开", Sleep);

        result = mFairy.findPic(new String[]{"openhb.png", "openhb1.png"});
        mFairy.onTap(0.8f, result, "err红包开", Sleep);

        result = mFairy.findPic(378, 33, 865, 338, new String[]{"hongbao.png", "daxie.png", "jzhb.png", "hongbao1.png"});
        if (picCount(0.8f, result, "err红包关闭") > 1) {
            mFairy.onTap(0.9f, result, 862, 403, 863, 404, "err红包关闭", Sleep);
        }

        result = mFairy.findPic(750, 366, 920, 528, new String[]{"hb.png", "hb1.png"});
        mFairy.onTap(0.8f, result, "err红包开", Sleep);

        result = mFairy.findPic("notice interface.png");
        mFairy.onTap(0.8f, result, 620, 555, 662, 578, "err公告确定", 1000);

        result = mFairy.findPic(new String[]{"ksyx.png", "ksyx1.png"});
        mFairy.onTap(0.8f, result, "err开始游戏", Sleep);


//        result = mFairy.findPic("joingame.png");
//        mFairy.onTap(0.8f, result,    "err进入游戏", Sleep);

        result = mFairy.findPic("dialogue.png");
        mFairy.onTap(0.8f, result, "err对话", Sleep);

        result = mFairy.findPic(962, 605, 1265, 713, "joingame.png");
        LtLog.e("/*/" + picCount(0.8f, result, "err进入游戏"));
        if (picCount(0.8f, result, "err进入游戏") > 5) {
            result = mFairy.findPic("joingame.png");
            LtLog.e("/*/" + picCount(0.8f, result, "err进入游戏"));
            if (picCount(0.8f, result, "err进入游戏") > 10) {
                mFairy.onTap(0.8f, result, "err进入游戏", Sleep);
            }
//        result = mFairy.findPic(268, 877, 438, 998,"qqdd1.png");
//        mFairy.onTap(0.8f, result,    "errQQ登录按钮2", Sleep);
//
//        result = mFairy.findPic(24,549,714,1155,"checkvx4.png");
//        mFairy.onTap(0.8f, result,    "err登录角色1", Sleep);
//
//        result = mFairy.findPic(48,263,672,1148,"denglu.png");
//        mFairy.onTap(0.8f, result,    "err登录角色2", Sleep);

            result = mFairy.findPic(24, 549, 714, 1155, "checkvx3.png");
            mFairy.onTap(0.75f, result, "errqq登录角色", Sleep);

            result = mFairy.findPic(24, 549, 714, 1155, "checkvx4_1.png");
            mFairy.onTap(0.75f, result, "errqq登录角色", Sleep);

            result = mFairy.findPic(24, 549, 714, 1155, new String[]{"checkvx5.png", "checkvx5_1.png"});
            mFairy.onTap(0.75f, result, "err完成qq授权", Sleep);

            result = mFairy.findPic("sygj.png");
            mFairy.onTap(0.8f, result, "err手游管家", Sleep);

            result = mFairy.findPic("dlsb.png");
            mFairy.onTap(0.8f, result, 625, 450, 626, 451, "err登录失败", Sleep);


            result = mFairy.findPic(561, 232, 723, 309, "updatets.png");
            if (result.sim > 0.8f) {
                result = mFairy.findPic(684, 422, 861, 486, "updatesure.png");
                mFairy.onTap(0.8f, result, "err更新提示点击确定", Sleep);
            }

            result = mFairy.findPic("djmax.png");
            mFairy.onTap(0.8f, result, 765, 455, 766, 456, "err等级上限", Sleep);

            result = mFairy.findPic(483, 300, 787, 419, "Slide lock.png");
            if (result.sim > 0.8f) {
                LtLog.e(mFairy.getLineInfo("err解锁中"));
                mFairy.ranSwipe(543, 358, 764, 360, 500, (long) 500, 0);
            }

            result = mFairy.findPic("jsdh.png");
            if (result.sim > 0.8f) {
                LtLog.e(mFairy.getLineInfo("顶号了"));
                mFairy.sleep(300000);
            }
            result = mFairy.findPic("gengxin.png");
            mFairy.onTap(0.8f, result, 613, 506, 661, 516, "err强制更新", Sleep);

            result = mFairy.findPic(736, 18, 1266, 398, new String[]{"forkhd.png", "forkhd1.png", "forkhd2.png"});
            mFairy.onTap(0.8f, result, "err活动叉", Sleep);

            result = mFairy.findPic("forkhd3.png");
            mFairy.onTap(0.8f, result, "err活动叉", Sleep);
    /*    result = mFairy.findPic("errjiuhui.png");
        mFairy.onTap(0.8f, result,  495,443,536,465,  "err酒会开启取消", Sleep);*/

            result = mFairy.findPic("gbdm.png");
            mFairy.onTap(0.8f, result, "关闭弹幕", Sleep);

            result = mFairy.findPic(983, 548, 1274, 714, new String[]{"fh.png", "fanhui.png"});
            mFairy.onTap(0.8f, result, "err返回游戏", 1000);

            result = mFairy.findPic("hszg.png");
            mFairy.onTap(0.8f, result, 623, 442, 658, 459, "会试资格", Sleep);

            result = mFairy.findPic(325, 296, 698, 560, "quxiao.png");
            if (picCount(0.8f, result, "err倒计时取消") > 2) {
                mFairy.onTap(0.8f, result, "err倒计时取消", Sleep);
            }
        }

    }
}
