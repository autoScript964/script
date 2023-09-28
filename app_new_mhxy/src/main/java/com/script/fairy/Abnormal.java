package com.script.fairy;

import com.example.Answer;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

/**
 * Created by Administrator on 2019/3/22 0022.
 */
public class Abnormal  {
    AtFairyImpl mFairy;
    FindResult result;
    private Answer answer;
    GamePublicFuntion gamePublicFuntion;
    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion=new GamePublicFuntion(mFairy);
        answer = new Answer(ypFairy);

    }
    //全局处理
    public void erro() throws Exception {
        /**
         * 临时
         *
         */

        result = mFairy.findPic("hdclose51.png");
        mFairy.onTap(0.8f,result,1043,84,1060,112,"err hdclose51",1000);

        result = mFairy.findPic(366,246,940,401,"sm16.png");
        mFairy.onTap(0.8f,result,608,572,646,587,"err sm完成弹框",1000);


        result = mFairy.findPic(834,397,938,469,"xscl.png");
        mFairy.onTap(0.8f,result,"err 限时活动下方弹框",1000);

        result = mFairy.findPic(567,555,692,593,"cha.png");
        mFairy.onTap(0.8f,result,990,183,1014,199,"err 立即查看",1000);

        result = mFairy.findPic("hdclose50.png");
        mFairy.onTap(0.8f,result,"err hdclose50",1000);

        result = mFairy.findPic("shizhuang.png");
        mFairy.onTap(0.8f,result,1101,86,1122,109,"err 时装关闭",1000);

        result = mFairy.findPic(529,570,667,665,"hdclose49.png");
        mFairy.onTap(0.8f,result,948,109,964,120,"err hdclose49",1000);

        result = mFairy.findPic("hdclose48.png");
        mFairy.onTap(0.8f,result,"err hdclose48",1000);

        result = mFairy.findPic("hdclose47.png");
        mFairy.onTap(0.8f,result,"err hdclose47",1000);

        result = mFairy.findPic("hdclose46.png");
        mFairy.onTap(0.8f,result,"err hdclose46",1000);


        result = mFairy.findPic("hdclose45.png");
        mFairy.onTap(0.8f,result,1136,44,1164,68,"err hdclose45",1000);

        result = mFairy.findPic("hdclose44.png");
        mFairy.onTap(0.8f,result,"err hdclose44",1000);


        result = mFairy.findPic("hdclose43.png");
        mFairy.onTap(0.8f,result,1057,121,1078,145,"err hdclose43",1000);





        result = mFairy.findPic(473,281,605,473,"hdclose42.png");
        mFairy.onTap(0.8f,result,"err hdclose42",1000);


        result = mFairy.findPic("hdclose41.png");
        mFairy.onTap(0.8f,result,1106,39,1122,58,"err hdclose41",1000);

        result = mFairy.findPic("hdclose40.png");
        mFairy.onTap(0.8f,result,1135,29,1161,61,"err hdclose40",1000);



        result = mFairy.findPic(431,256,835,600,"login1.png");
        mFairy.onTap(0.8f,result,"err 豌豆荚",1000);


        result = mFairy.findPic("hdclose29.png");
        mFairy.onTap(0.8f,result,1132,22,1150,39,"err hdclose29",1000);

        result = mFairy.findPic("hdclose30.png");
        mFairy.onTap(0.8f,result,971,108,993,121,"err hdclose30",1000);


        result = mFairy.findPic("hdclose31.png");
        mFairy.onTap(0.8f,result,"err hdclose31",1000);


        result = mFairy.findPic("hdclose32.png");
        mFairy.onTap(0.8f,result,"err hdclose32",1000);

        result = mFairy.findPic("hdclose33.png");
        mFairy.onTap(0.8f,result,1017,122,1043,144,"err hdclose33",1000);


        result = mFairy.findPic("hdclose34.png");
        mFairy.onTap(0.8f,result,"err hdclose34",1000);


        result = mFairy.findPic("hdclose35.png");
        mFairy.onTap(0.8f,result,721,629,757,642,"err hdclose35",1000);

        result = mFairy.findPic(358,527,902,668,"hdclose36.png");
        mFairy.onTap(0.8f,result,1042,81,1092,120,"err hdclose36",1000);


        result = mFairy.findPic(420,556,839,676,"hdclose37.png");
        mFairy.onTap(0.8f,result,1014,124,1043,143,"err hdclose37",1000);

        result = mFairy.findPic("hdclose38.png");
        mFairy.onTap(0.85f,result,"err hdclose38",1000);

        result = mFairy.findPic("hdclose39.png");
        mFairy.onTap(0.85f,result,"err hdclose39",1000);

        result = mFairy.findPic("fen.png");
        mFairy.onTap(0.8f,result,1098,87,1127,108,"err 分享",1000);


        result = mFairy.findPic("hdclose25.png");
        mFairy.onTap(0.8f,result,1132,21,1155,40,"err hdclose25",1000);

        result = mFairy.findPic("hdclose26.png");
        mFairy.onTap(0.8f,result,"err hdclose26",1000);

        result = mFairy.findPic("hdclose24.png");
        mFairy.onTap(0.8f,result,1102,86,1125,102,"err hdclose24",1000);

        result = mFairy.findPic(387,587,892,710,"dianji.png");
        mFairy.onTap(0.8f,result,980,632,1031,655,"err 点击下方按钮",1000);

        result = mFairy.findPic("guan.png");
        mFairy.onTap(0.8f,result,"err 确定关闭",1000);

        result = mFairy.findPic(456,42,870,165,"hdclose23.png");
        mFairy.onTap(0.9f,result,968,127,979,142,"err 关闭 活动",1000);

        result = mFairy.findPic(644,11,1255,240,"hdclose22.png");
        mFairy.onTap(0.9f,result,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose20.png");
        mFairy.onTap(0.8f,result,681,677,704,695,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose21.png");
        mFairy.onTap(0.8f,result,"err 关闭 活动",1000);


        result = mFairy.findPic(new String[]{"hdclose27.png","hdclose28.png"});
        mFairy.onTap(0.8f,result,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose19.png");
        mFairy.onTap(0.8f,result,909,99,929,114,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose15.png");
        mFairy.onTap(0.8f,result,1094,99,1112,122,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose16.png");
        mFairy.onTap(0.8f,result,1070,75,1090,92,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose17.png");
        mFairy.onTap(0.8f,result,"err 关闭 活动",1000);

        result = mFairy.findPic("hdclose18.png");
        mFairy.onTap(0.8f,result,1110,76,1134,99,"err 关闭 活动",1000);

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



        yanzheng();

        qudao();
    }


    private final int x = 282;
    private final int y = 180;

    public void yanzheng()throws Exception{
        result = mFairy.findPic("yanzheng.png");
        if(result.sim>0.8f){
            LtLog.e(mFairy.getLineInfo("验证界面"));
            String position = answer.haoai(x,y,712,343,"8006");
            if(!position.equals("") && position!=null){

                String[] xy = position.split(",");

                int px = new Integer(xy[0]);
                int py = new Integer(xy[1]);

                mFairy.ranSwipe(360,y+py,x+px,y+py,500,2000);
                Thread.sleep(1000);
            }
        }
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
