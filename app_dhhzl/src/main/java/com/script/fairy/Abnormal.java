package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;

/**
 * Created by Administrator on 2019/3/22 0022.
 */
public class Abnormal {
    AtFairyImpl mFairy;
    FindResult result;
    GamePublicFuntion gamePublicFuntion;
    int index=0;

    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(mFairy);
    }

    public void erro() throws Exception {

        taskEvent();//任务点击

        result = mFairy.findPic("new sure.png");
        mFairy.onTap(0.8f, result, "err新版qq隐私政策同意", 1000);

        result = mFairy.findPic("jiangli1.png");
        mFairy.onTap(0.8f, result,621,568,654,594, "err获得奖励", 1000);

        result = mFairy.findPic("new authorization.png");
        mFairy.onTap(0.8f, result, "err新版qq授权", 1000);

        result = mFairy.findPic(new String[]{"new login.png","new login2.png","new login3.png"});
        mFairy.onTap(0.8f, result, "err新版qq登陆", 1000);

        result = mFairy.findPic("quanjing.png");
        mFairy.onTap(0.8f, result, "err全景模式", 1000);

        result = mFairy.findPic("shengdian.png");
        mFairy.onTap(0.8f, result, "err 省电模式", 3000);

        result = mFairy.findPic("tongyi.png");
        mFairy.onTap(0.8f, result, "err 同意", 1000);

        result = mFairy.findPic("new sure.png");
        mFairy.onTap(0.8f, result, "err新版qq隐私政策同意", 1000);

        result = mFairy.findPic("gongao.png");
        mFairy.onTap(0.8f,result,613,601,653,617,"err 公告点击确定",1000);

        if(TaskMain.qhBool==false) {
            result = mFairy.findPic("login.png");
            mFairy.onTap(0.8f, result, "err 登录", 1000);
        }

        result = mFairy.findPic("cnam.png");
        mFairy.onTap(0.8f, result, "err桌面", 1000);

        result = mFairy.findPic("hl.png");
        mFairy.onTap(0.8f, result, "err华丽退出", 1000);

        result = mFairy.findPic("hao.png");
        mFairy.onTap(0.8f, result, "err 好", 1000);

        result = mFairy.findPic("gk1.png");
        mFairy.onTap(0.8f, result, 1224,36,1232,44,"err 港口威压邀请函", 1000);

        result = mFairy.findPic("zhu.png");
        if(result.sim>0.8f){
            mFairy.killUserGame();
            Thread.sleep(5000);
        }

       /* result = mFairy.findPic("liaotian.png");
        mFairy.onTap(0.8f, result, "err 关闭聊天框", 1000);*/

        result = mFairy.findPic("chongxin.png");
        mFairy.onTap(0.8f, result, "err 重新连接", 1000);

        result = mFairy.findPic("lsclose.png");
        mFairy.onTap(0.9f, result, "err 关闭活动弹框", 1000);
    }

    public void taskEvent()throws Exception{
        /**
         * 点击海上漂浮物 */
        result = mFairy.findPic(146,117,947,450,new String[]{"tong.png","ren.png"});
        mFairy.onTap(0.8f,result,"海上漂浮物",1500);

        result = mFairy.findPic(790,350,978,503,"jiasu.png");
        mFairy.onTap(0.8f,result,"加速",1000);

        result = mFairy.findPic("wyj.png");
        mFairy.onTap(0.8f,result,"望远镜",1000);

        result = mFairy.findPic(393,236,604,330,"wxhy.png");
        mFairy.onTap(0.8f, result, 734,466,756,479,"err 前方是危险海域", 1000);

        result = mFairy.findPic(569,305,704,408,"jsq.png");
        if(result.sim>0.8f){
            mFairy.onTap(0.8f,result,"救生圈",6000);
            TaskContent.err=0;
        }

        result = mFairy.findPic("err1.png");
        mFairy.onTap(0.8f,result,1222,650,1237,668,"err 查看详情",1000);

    }




}