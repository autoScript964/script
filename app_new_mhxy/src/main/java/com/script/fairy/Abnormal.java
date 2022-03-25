package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.framework.AtFairyImpl;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class Abnormal  {
    AtFairyImpl mFairy;
    FindResult result;
    GamePublicFuntion gamePublicFuntion;
    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion=new GamePublicFuntion(mFairy);
    }
    //全局处理
    public void erro() throws Exception {
        /**
         * 临时
         */

        result = mFairy.findPic("hdclose1.png");
        mFairy.onTap(0.8f,result,958,157,974,174,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose2.png");
        mFairy.onTap(0.8f,result,908,100,929,117,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose3.png");
        mFairy.onTap(0.8f,result,1021,115,1035,135,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose4.png");
        mFairy.onTap(0.8f,result,1096,70,1108,80,"err 关闭 活动",1000);

        result = mFairy.findPic(398,176,590,312,"hdclose5.png");
        mFairy.onTap(0.8f,result,861,200,873,218,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose6.png");
        mFairy.onTap(0.8f,result,1089,41,1101,54,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose7.png");
        mFairy.onTap(0.8f,result,1128,55,1149,69,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose8.png");
        mFairy.onTap(0.8f,result,1100,65,1113,78,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose9.png");
        mFairy.onTap(0.8f,result,910,94,930,114,"err 关闭 活动",1000);

        result = mFairy.findPic(852,36,971,156,"hdclose10.png");
        mFairy.onTap(0.8f,result,"err 关闭 活动",1000);

        result = mFairy.findPic(485,25,794,123,"hdclose11.png");
        mFairy.onTap(0.8f,result,1100,50,1131,81,"err 关闭 活动",1000);

        result = mFairy.findPic(485,25,794,123,"hdclose12.png");
        mFairy.onTap(0.8f,result,1103,54,1129,72,"err 关闭 活动",1000);

        result = mFairy.findPic(160,82,311,305,"hdclose13.png");
        mFairy.onTap(0.8f,result,1094,123,1116,143,"err 关闭 活动",1000);


        result = mFairy.findPic("hdui1.png");
        mFairy.onTap(0.8f,result,1105,92,1123,105,"err 关闭 活动",1000);

        result = mFairy.findPic(589,15,1254,380,new String[]{"close13.png"});
        mFairy.onTap(0.8f,result,"err 关闭 活动",1000);

        result = mFairy.findPic("nclose.png");
        mFairy.onTap(0.8f,result,"err 关闭 活动",1000);

        result = mFairy.findPic("package3.png");
        mFairy.onTap(0.8f,result,858,168,876,184,"err 关闭临时背包",500);

        result = mFairy.findPic(702,353,1004,561,"err6.png");
        mFairy.onTap(0.8f,result,934,234,954,251,"err 关闭争霸赛",500);

        result = mFairy.findPic("mhmc.png");
        mFairy.onTap(0.8f,result,831,52,850,69,"err 梦幻迷城",500);

        result = mFairy.findPic("err7.png");
        mFairy.onTap(0.8f,result,966,144,985,164,"err 梦幻西游秀资",500);

        result = mFairy.findPic(389,224,639,375,"xinbai.png");
        mFairy.onTap(0.8f,result,731,459,761,479,"更新新版",500);

        result = mFairy.findPic("err5.png");
        mFairy.onTap(0.8f,result,1020,163,1036,180,"限时折扣",500);

        result = mFairy.findPic("yunxu.png");
        mFairy.onTap(0.8f,result,"允许",500);

        result = mFairy.findPic(427,336,643,529,"suanle.png");
        mFairy.onTap(0.8f,result,"suanle",500);

        result = mFairy.findPic("bjbs.png");
        mFairy.onTap(0.8f,result,"不见不散",500);

        result = mFairy.findPic("err8.png");
        mFairy.onTap(0.8f,result,"err 关闭聊天窗口",500);

        result = mFairy.findPic(622,415,955,666,"jieshou.png");
        mFairy.onTap(0.8f,result,"接受",500);

        result = mFairy.findPic("gonggao.png");
        mFairy.onTap(0.8f,result,"公告",500);

        result = mFairy.findPic("task2.png");
        mFairy.onTap(0.8f,result,1094,43,1116,60,"任务-close",500);

        result = mFairy.findPic(460,321,860,573,new String[]{"set12.png","set13.png"});
        mFairy.onTap(0.72f,result,"网易-登录",500);

        if(TaskMain.QH==false) {
            result = mFairy.findPic("login.png");
            mFairy.onTap(0.8f, result, "登录游戏", 500);
        }

        qudao();
    }

    public void qudao()throws Exception{

        result = mFairy.findPic(768,16,1109,189,new String[]{"qd9.png","qd6.png"});
        mFairy.onTap(0.95f, result,"渠道-oppo-关闭广告", 2000);

        result = mFairy.findPic("qd3.png");
        mFairy.onTap(0.8f, result,883,92,904,112,"渠道-oppo-关闭实名注册", 2000);

        result = mFairy.findPic("qd4.png");
        mFairy.onTap(0.8f, result,909,83,918,100,"渠道-oppo-关闭公告", 2000);

        result = mFairy.findPic("qd5.png");
        mFairy.onTap(0.8f, result,859,393,881,413,"渠道-oppo-关闭信息界面", 2000);

        result = mFairy.findPic("qd10.png");
        mFairy.onTap(0.8f, result,519,194,568,220,"渠道-九游-选择账号", 2000);

        result = mFairy.findPic("qd11.png");
        mFairy.onTap(0.8f, result,"渠道-九游-暂不认证", 2000);

        result = mFairy.findPic("qd12.png");
        mFairy.onTap(0.8f, result,"渠道-九游-知道了", 2000);

        result = mFairy.findPic("qd1.png");
        mFairy.onTap(0.8f, result,"渠道-九游-进入游戏", 2000);


    }


}
