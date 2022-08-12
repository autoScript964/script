package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.TaskContent;
import com.script.framework.AtFairyImpl;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;


public class Abnormal extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;

    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
    }

    String task_id = AtFairyConfig.getOption("task_id");

    int sign = 0;
    //全局处理
    public void erro() throws Exception {

        for (int i = 0; i < 10; i++) {
            result = mFairy.findPic(new String[]{"dinghao.png","ding.png"});
            if(result.sim>0.8f){
                mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
                Thread.sleep(2000);
                break;
            }
        }


        result = mFairy.findPic("hdclose2.png");
        mFairy.onTap(0.8f, result, "err hdclose2", 2000);


        result = mFairy.findPic("hdclose5.png");
        mFairy.onTap(0.8f, result, "err hdclose5", 2000);

        result = mFairy.findPic("hdclose4.png");
        mFairy.onTap(0.8f, result, 919,41,938,59,"err hdclose4", 2000);

        result = mFairy.findPic(621,5,1271,210,"hdclose6.png");
        mFairy.onTap(0.8f, result, "err hdclose6", 2000);

        result = mFairy.findPic(621,5,1271,210,"hdclose7.png");
        mFairy.onTap(0.8f, result, "err hdclose7", 2000);

        result = mFairy.findPic("jiayuan.png");
        mFairy.onTap(0.8f, result, "err 退出装修", 2000);


        result = mFairy.findPic("xiazai.png");
        mFairy.onTap(0.8f, result, 480,476,507,483,"err 取消下载", 2000);

        result = mFairy.findPic(651,469,1060,672,"tongyi.png");
        mFairy.onTap(0.8f, result, "err 同意", 2000);


        result = mFairy.findPic(919,153,1074,237,"zuoqi3.png");
        mFairy.onTap(0.8f, result,1151,207,1164,231, "err 坐骑点包裹", 5000);


        result = mFairy.findPic(367,238,679,354,"chonglian.png");
        mFairy.onTap(0.8f, result,770,425,807,437, "err 游戏客户端更新完成，重启", 5000);

        result = mFairy.findPic("zuoqi4.png");
        if(result.sim>0.8f) {

            result = mFairy.findPic(396, 84, 1025, 614, "zuoqi2.png");
            mFairy.onTap(0.8f, result, result.x + 220, result.y + 5, result.x + 230, result.y + 10, "err 坐骑看看它", 3000);

            result = mFairy.findPic(623, 145, 819, 550, "zuoqi1.png");
            mFairy.onTap(0.8f, result, result.x - 144, result.y - 5, result.x - 140, result.y - 10, "err 坐骑使用", 1000);

        }

        qudao();

        result = mFairy.findPic("new sure.png");
        mFairy.onTap(0.8f, result, "err新版qq隐私政策同意", 1000);

        result = mFairy.findPic("new authorization.png");
        mFairy.onTap(0.8f, result, "err新版qq授权", 1000);

        result = mFairy.findPic("hdclose.png");
        mFairy.onTap(0.8f, result, 893,117,916,135,"err 图书馆计划活动弹框", 1000);

        result = mFairy.findPic("hdclose1.png");
        mFairy.onTap(0.8f, result, 902,121,912,130,"err 端午节活动弹框", 1000);

        result = mFairy.findPic(new String[]{"new login.png","new login2.png","new login3.png"});
        mFairy.onTap(0.8f, result, "err新版qq登陆", 1000);

        result = mFairy.findPic("shimen.png");
        mFairy.onTap(0.8f, result, "err显示界面", Sleep);

        result = mFairy.findPic("dangle.png");
        mFairy.onTap(0.8f, result, 917, 88, 935, 103, "err当乐", Sleep);

        result = mFairy.findPic(new String[]{"tianyu.png", "huaweiother.png", "dangle1.png"});
        mFairy.onTap(0.8f, result, "err华为广告图天宇登录", Sleep);

        result = mFairy.findPic("hongbao.png");
        mFairy.onTap(0.8f, result, "err红包", Sleep);

        result = mFairy.findPic("hbfork.png");
        mFairy.onTap(0.8f, result, "err红包叉", Sleep);

        result = mFairy.findPic("Determineentry.png");
        mFairy.onTap(0.8f, result, "err确定进入", Sleep);

        result = mFairy.findPic(new String[]{"startgames.png", "startgames1.png"});
        mFairy.onTap(0.8f, result, 631, 645, 632, 646, "err启动游戏", Sleep);

        result = mFairy.findPic("Signin.png");
        if(result.sim>0.9f) {
            sign++;

            if(sign>=5){
                sign=0;
                mFairy.killUserGame();
                Thread.sleep(10000);
                return;
            }

            mFairy.onTap(0.9f, result, "err登录游戏", Sleep);

        }else{
            sign=0;
        }


        result = mFairy.findPic(790, 482, 1270, 706, "Rolelogon.png");
        if (picCount(0.8f, result, "err角色登录") > 30) {
            mFairy.onTap(0.8f, result, "err角色登录", Sleep);
        }

        result = mFairy.findPic(345, 222, 944, 501, "gxzy.png");
        mFairy.onTap(0.8f, result, 759, 428, 760, 429, "err更新资源", Sleep);

        result = mFairy.findPic("On-line.png");
        mFairy.onTap(0.8f, result, 801, 426, 802, 427, "err账号在线", Sleep);

        result = mFairy.findPic("OhIsee.png");
        mFairy.onTap(0.8f, result, "err我知道", Sleep);

        result = mFairy.findPic("arrge.png");
        mFairy.onTap(0.8f, result, "err同意", Sleep);

        result = mFairy.findPic(new String[]{"jieshou.png","jieshou1.png","jieshou2.png"});
        mFairy.onTap(0.8f, result, "err接受", Sleep);


        result = mFairy.findPic(572,411,1170,679,"jieshou3.png");
        mFairy.onTap(0.8f, result, "err接受", Sleep);


        if (task_id.equals("2454")) {
            return;
        }

        if (task_id.equals("1809")) {

            result = mFairy.findPic(988, 4, 1276, 59, "tiaoguodh.png");
            mFairy.onTap(0.7f, result, "err跳过动画", Sleep);
        }

        for (int i = 0; i < 10; i++) {
            result = mFairy.findPic(1169, 653, 1263, 705, "duihua.png");
            mFairy.onTap(0.8f, result, "err对话2", Sleep);
            if (result.sim < 0.8f) {
                break;
            }
        }
        result = mFairy.findPic("erggy.png");
        if (picCount(0.8f, result, "err异常广告页") > 3) {
            mFairy.onTap(0.8f, result, 1132, 117, 1144, 125, "err异常广告页", Sleep);
        }

        result = mFairy.findPic("noApprentice.png");
        mFairy.onTap(0.8f, result, 525, 354, 526, 355, "err不拜师", Sleep);
        mFairy.onTap(0.8f, result, "err不拜师", Sleep);

        result = mFairy.findPic("Ahorse.png");
        mFairy.onTap(0.8f, result, 813, 113, 814, 114, "err竟马冠军", Sleep);


        result = mFairy.findPic("Oldhand.png");
        mFairy.onTap(0.8f, result, "err我是老手", Sleep);

        result = mFairy.findPic("lingyushop.png");
        mFairy.onTap(0.8f, result, 1184, 38, 1194, 47, "err灵玉商城", Sleep);


        result = mFairy.findPic(405, 272, 867, 384, "sqdz.png");
        if (result.sim > 0.8f && AtFairyConfig.getOption("jjsq").equals("1")) {
            mFairy.onTap(0.8f, result, 468, 420, 500, 441, "err拒绝队长申请", Sleep);
        } else if (result.sim > 0.8f && AtFairyConfig.getOption("jjsq").equals("1")) {
            mFairy.onTap(0.8f, result, 747, 420, 789, 438, "err同意队长申请", Sleep);
        }

        result = mFairy.findPic("Rightrefusal.png");
        if (result.sim > 0.8f && AtFairyConfig.getOption("jjsq").equals("1")) {
            mFairy.onTap(0.8f, result, "err左侧拒绝", Sleep);
        } else if (result.sim > 0.8f && AtFairyConfig.getOption("jjsq").equals("")) {
            mFairy.onTap(0.8f, result, 1164, 323, 1195, 340, "err左侧同意", Sleep);
        }


        result = mFairy.findPic(new String[]{"Giveuptask.png", "Giveuptask1.png"});
        if (picCount(0.8f, result, "err任务放弃") > 10) {
            mFairy.onTap(0.8f, result, "任务失败放弃", Sleep);
            mFairy.onTap(0.8f, result, 796, 424, 797, 425, "任务失败放弃", Sleep);
        }

        result = mFairy.findPic("Taskfailure.png");
        mFairy.onTap(0.8f, result, 642, 428, 643, 429, "一条任务失败", Sleep);

        result = mFairy.findPic("openkzl.png");
        mFairy.onTap(0.8f, result, "err打开左侧扩展栏", Sleep);

        result = mFairy.findPic("jujuest.png");
        mFairy.onTap(0.8f, result, "err拒绝收听", Sleep);

        FindResult result1 = mFairy.findPic("use1.png");
        if(result1.sim>0.8f) {
            result = mFairy.findPic("bb.png");
            if(result.sim<0.8f) {
                mFairy.onTap(0.8f, result1, "err使用经验道具", Sleep);
            }
        }

        result = mFairy.findPic("guanningstart.png");
        mFairy.onTap(0.8f, result, 764, 422, 790, 435, "err关宁确定进入", Sleep);


        result = mFairy.findPic("xzlp.png");
        mFairy.onTap(0.8f, result, 636, 427, 637, 428, "err新资料片提示", Sleep);

        result = mFairy.findPic("hmms.png");
        mFairy.onTap(0.8f, result, 798, 319, 799, 320, "err画面模式", Sleep);
        mFairy.onTap(0.8f, result, 646, 423, 647, 424, "err画面模式", Sleep);

        result = mFairy.findPic("Anovice.png");
        mFairy.onTap(0.8f, result, 915, 550, 956, 563, "err新手好礼界面", Sleep);
        mFairy.onTap(0.8f, result, 1188, 52, 1202, 66, "", 500);

        result = mFairy.findPic("Welfareinterface.png");
        if (result.sim > 0.8f && OtherGame.fuli) {
            LtLog.e(mFairy.getLineInfo("err福利界面"));
            Thread.sleep(2000);
            for (int i = 0; i < 10; i++) {
                mFairy.condit();
                result = mFairy.findPic(781, 88, 995, 650, "fllq.png");
                mFairy.onTap(0.8f, result, "err福利领取", Sleep);

                result = mFairy.findPic(1103, 173, 1266, 501, "flred.png");
                mFairy.onTap(0.9f, result, "err福利红点", Sleep);

            }
            mFairy.onTap(1187,49,1198,60,"",1000);
        }

        result = mFairy.findPic("Backflow.png");
        mFairy.onTap(0.8f, result, 1075, 153, 1076, 154, "err回流好礼", Sleep);

        result = mFairy.findPic("Copyleave.png");
        if (picCount(0.8f, result, "err确认离开副本") > 5) {
            mFairy.onTap(0.8f, result, 797, 424, 798, 425, "err确认离开副本", Sleep);
        }

      /*  result = mFairy.findPic("kjPopup.png");
        mFairy.onTap(0.8f, result,   1090,93,1091,94,"err科举弹窗", Sleep);*/
        result = mFairy.findPic("errclose.png");
        mFairy.onTap(0.8f, result, "err关闭", Sleep);

        result = mFairy.findPic("kjPopup2.png");
        mFairy.onTap(0.8f, result, 482, 424, 483, 425, "err科举弹窗", Sleep);

        result = mFairy.findPic("kejustop.png");
        mFairy.onTap(0.8f, result, 624, 418, 656, 432, "err科举结束", Sleep);

        result = mFairy.findPic("Therobber.png");
        mFairy.onTap(0.8f, result, 648, 506, 649, 507, "err大盗妙手空", Sleep);

        result = mFairy.findPic("xiaoyao.png");
        mFairy.onTap(0.8f, result, 625, 524, 655, 535, "err小妖闹三界", Sleep);

        result = mFairy.findPic("chat.png");
        if (picCount(0.8f, result, "聊天框") > 20) {
            mFairy.onTap(0.8f, result, "err收起聊天框", Sleep);
        }

        result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
        if (picCount(0.8f, result, "err默认复活") > 10) {
            mFairy.onTap(0.8f, result, "默认复活", Sleep);
        }
        result = mFairy.findPic("GaoChang.png");
        mFairy.onTap(0.8f, result, 487, 422, 488, 423, "err高昌", Sleep);

        result = mFairy.findPic(new String[]{"Prepare.png", "Prepare1.png"});
        mFairy.onTap(0.8f, result, 638, 417, 639, 418, "err联赛备战提醒", Sleep);

        result = mFairy.findPic("Off-line.png");
        mFairy.onTap(0.8f, result, 641, 413, 642, 414, "err离线经验", Sleep);

        result = mFairy.findPic("retry.png");
        mFairy.onTap(0.8f, result, "err重试", Sleep);

        result = mFairy.findPic(399,234,867,372,"fuhuo.png");
        if(result.sim>0.8f){
            mFairy.onTap(753,426,788,438,"",1000);
        }else {
            result = mFairy.findPic(260, 129, 1012, 626, new String[]{"errsure.png", "errsure1.png"});
            if (picCount(0.8f, result, "err取消确定") > 2) {
                mFairy.onTap(0.8f, result, "err取消确定", Sleep);
            }
        }

        result = mFairy.findPic("xzlp.png");
        mFairy.onTap(0.8f, result, 636, 427, 637, 428, "err新资料片提示", Sleep);

        result = mFairy.findPic("errshimen.png");
        mFairy.onTap(0.8f, result, 769, 424, 799, 436, "err师门进阶确定", 100);
        mFairy.onTap(0.8f, result, 617, 418, 656, 435, "err师门进阶确定", 100);


        result = mFairy.findPic("pingzheng.png");
        mFairy.onTap(0.8f, result, 610, 412, 654, 431, "err凭证失效", Sleep);


        result = mFairy.findPic(585, 269, 875, 364, "command.png");
        mFairy.onTap(0.8f, result, 484, 423, 485, 424, "err指挥", Sleep);

    }

    public void qudao() throws Exception {

        result = mFairy.findPic("qd.png");
        mFairy.onTap(0.8f, result, "进入游戏", Sleep);

        result = mFairy.findPic("yp.png");
        mFairy.onTap(0.8f, result, "启动游戏", Sleep);

        result = mFairy.findPic("qq.png");
        mFairy.onTap(0.8f, result, "qq", Sleep);

        result = mFairy.findPic(79,435,613,1244, "qqlogin1.png");
        mFairy.onTap(0.75f, result, "errQQ登录重新获取", 1000);

        result = mFairy.findPic2(79,435,613,1244, "qqlogin.png");
        mFairy.onTap(0.75f, result, "errQQ登录", 1000);

        result = mFairy.findPic(134, 742, 572, 1129, "qqlogin2.png");
        mFairy.onTap(0.75f, result, "errQQ登录授权登录", 2000);

        result = mFairy.findPic(98,475,640,952,"wx1.png");
        mFairy.onTap(0.75f, result, "err微信同意", 1000);

        result = mFairy.findPic(134, 742, 572, 1129, "qqShouQuan.png");
        mFairy.onTap(0.75f, result, "errQQ登录授权登录", 2000);

        result = mFairy.findPic(134, 742, 572, 1129, "qqlogin3.png");
        mFairy.onTap(0.75f, result, "errQQ登录授权登录", 2000);

        result = mFairy.findPic(496, 342, 777, 667, "qd1.png");
        mFairy.onTap(0.8f, result, "渠道-游团登录", Sleep);

        result = mFairy.findPic("qd2.png");
        mFairy.onTap(0.8f, result, 871, 122, 885, 135, "渠道-游团-关闭实名注册", Sleep);

        result = mFairy.findPic("huawei3.png");
        mFairy.onTap(0.8f,result,443,443,473,456,"渠道-华为-取消安装商店",1000);

        result = mFairy.findPic(576,392,1172,698,new String[]{"huawei4.png","huawei5.png"});
        mFairy.onTap(0.8f, result,  "渠道-华为-立即更新", Sleep);

        result = mFairy.findPic("huawei1.png");
        mFairy.onTap(0.8f, result,  "渠道-华为-关闭提示更新窗口", Sleep);

        result = mFairy.findPic("huawei2.png");
        mFairy.onTap(0.8f, result,  "渠道-华为-继续root", Sleep);

        result = mFairy.findPic("qd3.png");
        mFairy.onTap(0.8f, result, 883, 92, 904, 112, "渠道-oppo-关闭实名注册", Sleep);

        result = mFairy.findPic("qd4.png");
        mFairy.onTap(0.8f, result, 909, 83, 918, 100, "渠道-oppo-关闭公告", Sleep);

        result = mFairy.findPic("qd5.png");
        mFairy.onTap(0.8f, result, 859, 393, 881, 413, "渠道-oppo-关闭信息界面", Sleep);

        result = mFairy.findPic(549, 393, 730, 595, "qd6.png");
        if (result.sim > 0.8f) {
            mFairy.ranSwipe(404, 400, 1100, 400, 500, 1000);
            mFairy.onTap(0.8f, result, "渠道-pptv登录", Sleep);
        }

        result = mFairy.findPic("qd7.png");
        mFairy.onTap(0.8f, result, "暂不开启", Sleep);

        result = mFairy.findPic("qd8.png");
        mFairy.onTap(0.8f, result, "渠道-vivo-登录", Sleep);

        result = mFairy.findPic(768, 16, 1109, 189, "qd9.png");
        mFairy.onTap(0.95f, result, "渠道-oppo-关闭广告", Sleep);

    }//渠道


}
