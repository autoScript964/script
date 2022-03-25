package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;


/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class LimitlessTask {

    AtFairyImpl mFairy;
    FindResult result;

    FindResult findResult;
    CommonFunction commonFunction;
    GamePublicFunction gamePublicFunction;
    TimingActivity timingActivity;
    Other other;
    TeamTask teamTask;
    public LimitlessTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        commonFunction = new CommonFunction(mFairy);
        gamePublicFunction=new GamePublicFunction(mFairy);
        timingActivity= new TimingActivity(mFairy);
        other=new Other(mFairy);
        teamTask=new TeamTask(mFairy);
    }
    //带队挂野
    public void  Hanging()throws Exception{
        int bj = 0;
        int ret,ret1;
        int  numcolor,numcolor1;
        int js_1=0,js_2=0,js_3=0,js_4=0,js_5=0,js_6=0,js_7=0;
        int ditu=0,gysb=0,kf=0;
         String string="";
        int h, m, w;
        ditu=Integer.parseInt(AtFairyConfig.getOption("ditu"));
        if (AtFairyConfig.getOption("ls").equals("1")){
            gysb=1;
        }
        if (AtFairyConfig.getOption("kf").equals("1") ){
            kf=1;
        }
        if (ditu==1){
            string="514.png";
        }else if (ditu==2){
            string="1524.png";
        }else if (ditu==3){
            string="2534.png";
        }else if (ditu==4){
            string="3544.png";
        }else if (ditu==5){
            string="4554.png";
        }else if (ditu==6){
            string="5564.png";
        }else if (ditu==7){
            string="6574.png";
        }else if (ditu==8){
            string="7584.png";
        }else if (ditu==9){
            string="8594.png";
        }else if (ditu==10){
            string="95104.png";
        }else if (ditu==11){
            string="100109.png";
        }else if (ditu==12){
            string="110119.png";
        }else if (ditu==13){
            string="120129.png";
        }else if (ditu==14){
            string="130139.png";
        }
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("带队挂野中bj="+bj));
            if (bj == 0) {
                js_1=0;
                js_2=0;
                js_3=0;
                js_4=0;
                js_5=0;
                js_6=0;
                gamePublicFunction.init();
                if (kf==1){
                   teamTask.kf();
                }else {
                    gamePublicFunction.Hyf();
                }
                bj = 1;
            }
            if (bj==1){
                gamePublicFunction.SetJudgeTask("team");

                result = mFairy.findPic2(201, 68, 352, 127,commonFunction.setImg("Bcreateteam.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "创建队伍"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(182,59,370,137,commonFunction.setImg("Blkdw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
                if (result.sim>0.8f){

                    mFairy.onTap(1132,120,1152,171,"",2000);

                    result = mFairy.findPic2(174, 556, 280, 653, commonFunction.setImg("1people.png"));
                    if (result.sim < 0.8f) {
                        LtLog.e(commonFunction.getLineInfo("4个人"));
                        bj = 2;
                    }

                    findResult = mFairy.findPic(1075,49,1222,480,"pi.png");
                    mFairy.onTap(0.8f,findResult,"匹配",2000);

                    result = mFairy.findPic2(179,133,449,672,commonFunction.setImg("Target attack1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "野外打怪"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    if (result.sim<0.8f){
                        gamePublicFunction.BackCity(698,497,"长安");
                    }

                    findResult = mFairy.findPic(929,541,1115,676,"pi1.png");
                    mFairy.onTap(0.8f,findResult,"发布",2000);

                    mFairy.onTap(1132,120,1152,171,"",2000);

                }
            }
            if (bj==2){
                     gamePublicFunction.mission2("Hanging.png");
                    Thread.sleep(2000);
                    if (gysb==1){
                        result = mFairy.findPic2(855,568,1058,667,commonFunction.setImg("gysb.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领取双倍"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    }
                    bj = 3;
            }
            if (bj==3){
                result = mFairy.findPic(178,5,512,68,"zdset.png");
                LtLog.e("打怪双倍界面:"+result);
                if (result.sim > 0.8) {

                    result = mFairy.findPic2(246, 403, 1070, 460,commonFunction.setImg(string));
                    LtLog.e(commonFunction.getLineInfo(result, 0.86f, "地图"));
                    commonFunction.RndCompare(0.86f, result.x + 20, result.y - 200, result, commonFunction.getImg());

                    if (result.sim>0.86f){

                        Thread.sleep(2000);

                        result = mFairy.findPic2(246, 403, 1070, 460,commonFunction.setImg(string));
                        LtLog.e(commonFunction.getLineInfo(result, 0.86f, "地图"));
                        commonFunction.RndCompare(0.86f, result.x + 20, result.y - 200, result, commonFunction.getImg());


                        bj=4;
                    }else {
                        js_1++;
                        if (js_1==2 || js_1==4 || js_1==6  || js_1==8  || js_1==10   || js_1==12 || js_1==14 || js_1==16){

                            mFairy.touchDown(300, 270);
                            mFairy.touchMove(700, 270,1000);
                            Thread.sleep(2000);
                            mFairy.touchUp();
                        }
                        if (js_1>18){
                            bj=0;
                        }
                    }
                }else {
                    bj=0;
                }
            }

            if (bj==4){
                js_2++;
                if (js_2>2000){
                    js_2=0;
                    bj=0;
                }
                result = mFairy.findPic2(316,288,792,443,commonFunction.setImg("yin_gdj.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "场景等级高确定"));
                commonFunction.RndCompare(0.8f, 401,487, result, commonFunction.getImg());

                result = mFairy.findPic2(1201, 641, 1263, 678,commonFunction.setImg("Bauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(471, 32, 811, 154,commonFunction.setImg("tszz1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "提示再战"));
                commonFunction.RndCompare(0.8f, 966, 101, result, commonFunction.getImg());


                result = mFairy.findPic2(59, 32, 137, 88,commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim>0.8f){
                    js_3=0;
                }else {
                    js_3++;
                    if (js_3>30){
                        bj=0;
                    }
                }
                ret=timingActivity.timeLimitActivity();
                if (ret==1){
                    bj=0;
                }
            }
        }
    }

    /**
     * 补充饱食度
     */
    public void satiate() throws Exception {
        while (mFairy.condit()){
            result = mFairy.findPic2(454,216,736,340,commonFunction.setImg("bs.png"));
            if (result.sim > 0.8f) {
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err饱食"));
                commonFunction.RndCompare(0.8f, 818,378, result, commonFunction.getImg());
            }

            result = mFairy.findPic2(commonFunction.setImg("baoshi.png"));
            if (result.sim > 0.8f) {
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "饱食确定"));
                commonFunction.RndCompare(0.8f, 636,485, result, commonFunction.getImg());
            }
            Thread.sleep(2000);
        }
    }

}
