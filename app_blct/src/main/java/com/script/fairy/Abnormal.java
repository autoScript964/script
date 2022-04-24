package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;


public class Abnormal {
    AtFairyImpl mFairy;
    FindResult result;
    GamePublicFuntion gamePublicFuntion;
    private int login = 1;
    public static boolean HONG = false;

    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(mFairy);
    }

    public void erro() throws Exception {
        result = mFairy.findPic("shi.png");
        mFairy.onTap(0.85f, result, "err是", 1000);

        result = mFairy.findPic("fan.png");
        mFairy.onTap(0.75f, result, "err返回游戏", 1000);


        result = mFairy.findPic("fan1.png");
        mFairy.onTap(0.75f, result, "err返回游戏", 1000);

        result = mFairy.findPic(578,432,945,669,"login1.png");
        mFairy.onTap(0.85f, result, "err同意", 1000);

        result = mFairy.findPic(464,317,773,524,"login2.png");
        mFairy.onTap(0.85f, result, "err登录", 1000);

        result = mFairy.findPic(596,298,962,554,"login3.png");
        if(result.sim>0.85f){
            result = mFairy.findPic("login4.png");
            mFairy.onTap(0.9f,result,"勾选",1000);
        }

        result = mFairy.findPic(590,271,965,567,"login5.png");
        mFairy.onTap(0.85f, result, "err QQ登录", 1000);


        result = mFairy.findPic("erryp.png");
        mFairy.onTap(0.85f, result, "err启动", 1000);

        result = mFairy.findPic("ok1.png");
        mFairy.onTap(0.85f, result, "err首领，欢迎回来!", 1000);

        FindResult result1 = mFairy.findPic("penguin qq.png");
        if(result1.sim>0.85f) {

            result = mFairy.findPic("kuang.png");
            mFairy.onTap(0.8f, result, "err 勾选指引!", 1000);

            mFairy.onTap(0.85f, result1, "qq登录!", 1000);

        }
        result = mFairy.findPic(214,359,482,615,new String[]{"qq1.png","qq2.png"});
        mFairy.onTap(0.75f, result, "重要提醒 - 确定", 1000);

        result = mFairy.findPic(79,435,613,1244, "qqlogin1.png");
        mFairy.onTap(0.85f, result, "errQQ登录重新获取", 1000);

        result = mFairy.findPic2(79,435,613,1244, "qqlogin.png");
        mFairy.onTap(0.85f, result, "errQQ登录", 1000);

        result = mFairy.findPic(134, 742, 572, 1129, "qqlogin2.png");
        mFairy.onTap(0.85f, result, "errQQ登录授权登录", 2000);

        result = mFairy.findPic(98,475,640,952,"wx1.png");
        mFairy.onTap(0.85f, result, "err微信同意", 1000);

        result = mFairy.findPic(134, 742, 572, 1129, "qqShouQuan.png");
        mFairy.onTap(0.85f, result, "errQQ登录授权登录", 2000);

        result = mFairy.findPic(134, 742, 572, 1129, "qqlogin3.png");
        mFairy.onTap(0.75f, result, "errQQ登录授权登录", 2000);

        result = mFairy.findPic(343,772,630,1053,"new sure.png");
        mFairy.onTap(0.8f, result, "err新版qq隐私政策同意", 1000);

        result = mFairy.findPic("new authorization.png");
        mFairy.onTap(0.8f, result, "err新版qq授权", 1000);

        result = mFairy.findPic("new login.png");
        mFairy.onTap(0.8f, result, "err新版qq登陆", 1000);

        result = mFairy.findPic(new String[]{"new login2.png","new login vip.png","new login down.png"});
        mFairy.onTap(0.8f, result, "err新版qq登陆", 1000);

        result = mFairy.findPic(221,1040,517,1214,"new login3.png");
        mFairy.onTap(0.8f, result, "err新版qq登录", 1000);

        result = mFairy.findPic(221,1040,517,1214,new String[]{"new shou.png","new shou2.png","new shou3.png"});
        mFairy.onTap(0.8f, result, "err新版qq授权", 1000);

        result = mFairy.findPic("zai1.png");
        mFairy.onTap(0.85f, result, 357, 449, 420, 466, "err还在吗？", 1000);

        result = mFairy.findPic(218, 160, 520, 662, "chongshi1.png");
        mFairy.onTap(0.8f, result, "err重试", 1000);

        result = mFairy.findPic(218, 160, 520, 662, "rest.png");
        if (result.sim > 0.75f) {
            Thread.sleep(10000);
            result = mFairy.findPic(218, 160, 520, 662, "rest.png");
            mFairy.onTap(0.75f, result, "err重新载入", 1000);
        }

        FindResult chongxin = mFairy.findPic(857,597,1182,708,"chongxin.png");
        if(chongxin.sim>0.8f){
            result = mFairy.findPic(179,625,487,697, "chongxin1.png");
            if (result.sim < 0.85f) {
                mFairy.onTap(0.8f,chongxin,"维护-重新载入",1000);
            }
        }

    }
}