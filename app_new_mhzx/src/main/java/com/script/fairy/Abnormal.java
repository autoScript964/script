package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

/**
 * Created by Administrator on 2019/3/22 0022.
 */
public class Abnormal {
    AtFairyImpl mFairy;
    FindResult result;
    GamePublicFuntion gamePublicFuntion;
    public static int i=0;
    long time_1 = System.currentTimeMillis();


    String task_id;
    int task_id1;
    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(mFairy);

        task_id = AtFairyConfig.getOption("task_id");
        if (task_id.equals("")) {
            task_id1 = 0;
        } else {
            task_id1 = Integer.parseInt(task_id);
        }

    }
    public void erro() throws Exception {
        Thread.sleep(100);

        if ((System.currentTimeMillis() - time_1) > 180000) {
            long time = mFairy.mMatTime(198,89,800,500,0.95f);
            if(time%60==0) {
                LtLog.e(mFairy.getLineInfo("游戏卡屏计时>>> :" + time));
            }
            if(time>=1200){
                LtLog.e(mFairy.getLineInfo("游戏遇到卡屏<<<<重启游戏"));
                mFairy.killUserGame();
                mFairy.initMatTime();
            }
        }


        result = mFairy.findPic(472,449,631,520,"jia1.png");
        mFairy.onTap(0.8f, result,  "err 母亲节活动",1000);



//                result = mFairy.findPic(392,52,1258,685,"qiang1.png");
//               mFairy.onTap(0.7f, result, "抢红包");

        if(AtFairyConfig.getOption("jyjx").equals("1") || AtFairyConfig.getOption("jzjx").equals("1")){

        }else {
            result = mFairy.findPic("dwyq.png");
            mFairy.onTap(0.8f, result, 503, 422,504,423, "err队伍邀请-点击拒绝",1000);
        }

            /*hour = mFairy.DateHour();
            minute = mFairy.DateMinute();
            if(hour==0 && minute==0){
                Thread.sleep(60000);
                mFairy.killUserGame();
                Thread.sleep(10000);
            }*/

        result = mFairy.findPic(388,213,892,385,"zx.png");
        if(result.sim>0.8f) {
            mFairy.onTap(0.8f,result,  636,426,637,427,"err在线时间监管",1000);
            Thread.sleep(30000);
        }

        result = mFairy.findPic("yunpadLogin.png");
       mFairy.onTap(0.8f, result,  "err启动游戏",1000);

        result = mFairy.findPic("remind.png");
        mFairy.onTap(0.9f, result, 611, 482, 612,483,"err活跃度提醒-知道了",1000);

        result = mFairy.findPic("qw1.png");
        mFairy.onTap(0.9f,result, 903, 194,904,195,  "err召回老朋友",1000);

        result = mFairy.findPic("jia.png");
        mFairy.onTap(0.9f,result,1092,89,1093,90,"奖励",1000);

        result = mFairy.findPic("got.png");
       mFairy.onTap(0.7f, result,  "err知道了",1000);

        result = mFairy.findPic("got1.png");
       mFairy.onTap(0.8f, result,  "err知道了",1000);

        result = mFairy.findPic("apprentice.png");
        mFairy.onTap(0.8f, result, 503, 422,504,423, "err拜师请求-点击拒绝",1000);

            /*result = mFairy.findPic(400,248,772,318,"rest.png");
            mFairy.onTap(0.8f, 635, 422, result, "err强制休息-确定");

            result = mFairy.findPic(400,248,772,318,"rest1.png");
            mFairy.onTap(0.8f, 635, 422, result, "err强制休息-确定1");*/

        result = mFairy.findPic(391,242,729,331,"errzx.png");
        mFairy.onTap(0.8f,result, 635, 422, 636,423, "err强制休息-确定1",1000);

        result = mFairy.findPic("qwe6.png");
        mFairy.onTap(0.9f,  result,517, 423,518,424, "err取消",1000);

        result = mFairy.findPic("notice.png");
        mFairy.onTap(0.9f,result, 633, 666,634,667,  "err公告-确定",1000);

        result = mFairy.findPic("errts.png");
        mFairy.onTap(0.9f,result, 635, 416, 636,417, "err系统提示",1000);

        result = mFairy.findPic("qw3.png");
        mFairy.onTap(0.9f, result,  524, 425,525,426,"err不买双倍点数",1000);

        result = mFairy.findPic("userxy.png");
        mFairy.onTap(0.9f,result, 769, 453,770,454,  "err用户协议,同意",1000);

        result = mFairy.findPic("errdk.png");
        mFairy.onTap(0.9f,result, 638, 416, 639,417, "err断开连接 点击确定",1000);

        result = mFairy.findPic("errlj.png");
        mFairy.onTap(0.9f, result,753, 427,754,428,  "err断开连接，重连",1000);

        result = mFairy.findPic("qqLogin.png");
       mFairy.onTap(0.8f, result,  "qq登录",1000);

        result = mFairy.findPic(94,565,611,1177,"qqLogin1.png");
       mFairy.onTap(0.8f, result,  "qq登录1",1000);

        if(i==0) {
            result = mFairy.findPic("l.png");
           mFairy.onTap(0.8f, result,  "err登录游戏2",1000);

            result = mFairy.findPic(1051,532,1278,708,"l1.png");
           mFairy.onTap(0.8f, result,  "err登录游戏3",1000);
        }

            /*result = mFairy.findPic("qwe11.png");
            mFairy.onTap(0.9f, 762, 426, result, "err智能组队确定");*/

        result = mFairy.findPic("sd.png");
        mFairy.onTap(0.8f,result,762,424, 763,425, "err省电模式-确定",1000);

        result = mFairy.findPic("extend1.png");
       mFairy.onTap(0.9f, result,  "err关闭扩展1",1000);

        result = mFairy.findPic("extend2.png");
       mFairy.onTap(0.9f, result,  "关闭扩展2",1000);

        result = mFairy.findPic("qwe5.png");
        mFairy.onTap(0.9f, result,  762, 426,763,427,"err匹配时间过长确定",1000);

        result = mFairy.findPic("qiang.png");
       mFairy.onTap(0.8f, result,  "err抢红包",1000);

        result = mFairy.findPic("errhb.png");
        mFairy.onTap(0.8f,result,971,260, 972,261, "err帮派红包",1000);

        result = mFairy.findPic("errbc.png");
        mFairy.onTap(0.8f,result, 519,426, 520,427,"err取消补充",1000);

        result = mFairy.findPic("tq.png");
       mFairy.onTap(0.8f, result,  "err普天同庆",1000);

        result = mFairy.findPic(332, 127, 907, 616, "quxiao.png");
       mFairy.onTap(0.9f, result,  "err取消",1000);

        result = mFairy.findPic("cw.png");
        mFairy.onTap(0.8f, result, 490, 423, 491,424,"err不成为队长",1000);

        if (task_id1 == 1158) {
            result = mFairy.findPic("copyUi.png");
            mFairy.onTap(0.8f, result, 895, 583,896,584, "err同意",1000);

            result = mFairy.findPic("hg.png");
            mFairy.onTap(0.8f, result, 764, 429, 765,430,"err回归队伍",1000);

            result = mFairy.findPic("errrw.png");
            mFairy.onTap(0.8f,result, 758, 424, 759,425, "err任务同意",1000);
        }

        //世界答题
//            if (AtFairyConfig.getOption("sjdt").equals("1") {
//                if ((System.currentTimeMillis() - time_2) > 3000) {
//                    for(int i=0 ; i<3 ; i++) {
//                        result = mFairy.findPic("qd.png");
//                       mFairy.onTap(0.8f, result,  "抢答");
//                    }
//
//                    time_2 = System.currentTimeMillis();
//                }
//            }

        result = mFairy.findPic(576,433,901,594,"sjdt.png");
        mFairy.onTap(0.8f,result,628,649,629,650,"世界窗口,关闭!",1000);
    }

}