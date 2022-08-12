package com.script.fairy;

import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.LtLog;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class Abnormal  {
    AtFairyImpl mFairy;
    FindResult result;
    GamePublicFuntion gamePublicFuntion;

    static long thTime =System.currentTimeMillis();

    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion=new GamePublicFuntion(mFairy);
    }
    //全局处理

    long qdTime = System.currentTimeMillis();
    long closeTime = System.currentTimeMillis();
    long rightTime = System.currentTimeMillis();
    public void erro() throws Exception {

        thTime =System.currentTimeMillis();

        result = mFairy.findPic("fh1.png");
        mFairy.onTap(0.85f, result, "err免费复活", 1000);


        result = mFairy.findPic(642,353,967,501,"queding.png");
        if(result.sim>0.8f) {
            if(System.currentTimeMillis()-qdTime >10000){
                mFairy.onTap(0.85f, result, "err确定", 1000);
                qdTime = System.currentTimeMillis();
            }
        }else{
            qdTime= System.currentTimeMillis();
        }

        result = mFairy.findPic(437, 0, 1279, 319, "close1.png");
        if(result.sim>0.75f){
            if(System.currentTimeMillis() - closeTime > 60000){
                closeTime = System.currentTimeMillis();
                gamePublicFuntion.close(3);
            }


        }else{
            closeTime=System.currentTimeMillis();
        }


        result = mFairy.findPic("erryp.png");
        mFairy.onTap(0.85f, result, "err启动", 1000);

        result = mFairy.findPic("login.png");
        mFairy.onTap(0.85f, result, "err进入游戏", 1000);

        result = mFairy.findPic("map.png");
        mFairy.onTap(0.85f, result, "err展开地图", 1000);

        result = mFairy.findPic("tongyi.png");
        mFairy.onTap(0.85f, result, "err同意", 1000);

        result = mFairy.findPic("pao.png");
        mFairy.onTap(0.8f, result, "err跑", 1000);


        if(!AtFairyConfig.getOption("task_id").equals("2737")) {
            result = mFairy.findPic(849, 471, 1014, 544, "use.png");
            if(result.sim>0.8f) {
                if (AtFairyConfig.getOption("qxuse").equals("1")) {
                    mFairy.onTap(0.8f, result, "使用", 3000);
                }else{
                    mFairy.onTap(1010,350,1021,367,"关闭使用弹窗",1000);
                }
            }
        }
        result = mFairy.findPic(426, 306, 682, 385, "package4.png");
        mFairy.onTap(0.8f, result, 506, 415, 559, 427, "确定回收", 3000);


        result = mFairy.findPic("left.png");
        mFairy.onTap(0.85f, result, "err展开任务栏", 1000);

        result = mFairy.findPic("login2.png");
        mFairy.onTap(0.85f, result, "err返回登录", 1000);

        result = mFairy.findPic("login3.png");
        mFairy.onTap(0.85f, result, "err登录", 1000);

        result = mFairy.findPic("kd.png");
        mFairy.onTap(0.85f, result, 678,410,699,417,"err关闭高帧", 1000);

        result = mFairy.findPic("right.png");
        if(result.sim>0.9f){

            if(System.currentTimeMillis() - rightTime >10000) {
                rightTime = System.currentTimeMillis();

                mFairy.onTap(0.9f, result, "err关闭活动栏", 1000);
            }
        }else{
            rightTime = System.currentTimeMillis();
        }


        result = mFairy.findPic(0,0,50,500,"hy.png");
        if(result.sim>0.8f){
            LtLog.e(mFairy.getLineInfo("移动欢娱图标"));
            mFairy.touchDown(result.x, result.y);
            mFairy.touchMove(11,553,1000);
            mFairy.touchUp();
        }


        result = mFairy.findPic("login1.png");
        if(result.sim>0.8f){
            LtLog.e(mFairy.getLineInfo("选角色界面"));

            if(AtFairyConfig.getOption("js").equals("2")){
                mFairy.onTap(985,557,1019,569,"选择角色2",1000);
            }else{
                mFairy.onTap(341,557,384,570,"选择角色1",1000);
            }

            mFairy.onTap(0.85f, result, "err开始", 3000);
        }





    }


}
