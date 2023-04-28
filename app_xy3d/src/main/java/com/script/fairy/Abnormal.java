package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;

/**
 * Created by Administrator on 2019/3/22 0022.
 */
public class Abnormal {
    AtFairyImpl mFairy;
    FindResult result;
    GamePublicFuntion gamePublicFuntion;
    private int login = 1;
    public static boolean HONG = false;
    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(mFairy);

        if(!AtFairyConfig.getOption("login").equals("")){
            login=Integer.parseInt(AtFairyConfig.getOption("login"));
        }

        if(AtFairyConfig.getOption("hong").equals("1")){
            HONG=true;
        }
    }

    public void erro() throws Exception {

        result= mFairy.findPic("yunxu.png");
        mFairy.onTap(0.8f,result,"允许",500);

        result= mFairy.findPic("add.png");
        if(result.sim>0.8f) {
            result= mFairy.findPic("add1.png");
            mFairy.onTap(0.8f, result, "不再提示", 500);
            mFairy.onTap(970,182,987,194,"关闭添加好友弹框",500);
        }
        result= mFairy.findPic("autogm.png");
        mFairy.onTap(0.8f,result,806,433,846,447,"取消自动购买",500);

        result = mFairy.findPic("erryp.png");
        mFairy.onTap(0.85f, result, "err启动", 1000);

        result = mFairy.findPic(new String[]{"shengddian.png","sd.png","sd1.png","sd2.png","sd3.png"});
        mFairy.onTap(0.8f, result, "省电", 1000);

        result = mFairy.findPic("err2.png");
        mFairy.onTap(0.85f, result, "err2", 1000);

        result = mFairy.findPic("liao.png");
        mFairy.onTap(0.85f, result, "聊天", 1000);

        result= mFairy.findPic("leftzoom.png");
        mFairy.onTap(0.8f,result,"左侧缩放栏",1000);

        result= mFairy.findPic("jieshou.png");
        mFairy.onTap(0.8f,result,"接受",1000);

        result= mFairy.findPic("jinru.png");
        mFairy.onTap(0.8f,result,"进入游戏",500);

        result= mFairy.findPic("jinru1.png");
        mFairy.onTap(0.8f,result,"进入角色",1000);

        result= mFairy.findPic("jixugj.png");
        mFairy.onTap(0.8f,result,"继续挂机",1000);

        result= mFairy.findPic("create.png");
        mFairy.onTap(0.8f,result,"创建角色",1000);
    }
}