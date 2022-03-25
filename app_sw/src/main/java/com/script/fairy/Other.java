package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import static com.script.opencvapi.AtFairy2.FAIRY_TYPE_TASK;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class Other {
    AtFairyImpl mFairy;
    FindResult result;
    CommonFunction commonFunction;
    GamePublicFunction gamePublicFunction;
    public Other(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        commonFunction = new CommonFunction(mFairy);
        gamePublicFunction=new GamePublicFunction(mFairy);
    }
    //休闲钓鱼
    public void  Gofishing() throws Exception {
        int bj = 0,bj_1;
        int ret;
        int  numcolor,numcolor1;
        int js_1=0,js_2=0,js_3=0,js_4=0,js_5=0,js_6=0,js_7=0;
        int cs_1=0;
        int ditu,yu=0;
        if (AtFairyConfig.getOption("yu").equals("1")){
            yu=1;
        }
        ditu=Integer.parseInt(AtFairyConfig.getOption("ditu1"));
        bj_1 = bj;
        while (mFairy.condit()) {
            if (bj_1 == bj && bj != 5) {
                cs_1++;
            } else if (bj_1 != bj) {
                cs_1 = 0;
                bj_1 = bj;
                LtLog.e(commonFunction.getLineInfo("标记发生变化"));
            }
            if (cs_1 > 150) {
                cs_1 = 0;
                bj = 0;
                LtLog.e(commonFunction.getLineInfo("标记长时间未发生变化"));
            }
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("休闲钓鱼中bj="+bj));

            result = mFairy.findPic("dy1.png");
            mFairy.onTap(0.8f,result,924,78,939,87,"点雨介绍",1000);

            if (bj == 0) {
                gamePublicFunction.Hyf();
                if (ditu==1){
                    gamePublicFunction.BackCity(1015,390,"傲来");
                }else if (ditu==2){
                    gamePublicFunction.BackCity(698,497,"长安");
                }else if (ditu==3){
                    gamePublicFunction.BackCity(278, 385,"临仙镇");
                }else if (ditu==4){
                    gamePublicFunction.BackCity(561, 372,"大唐国境");
                }
                js_1=0;
                js_2=0;
                js_3=0;
                js_4=0;
                js_5=0;
                js_6=0;
                bj = 1;
            }
            if (bj == 1) {
                result = mFairy.findPic2(1188, 574, 1273, 621,commonFunction.setImg("Nwupin.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "物品"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(934, 585, 1098, 648,commonFunction.setImg("bgzl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "整理"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim>0.8f){
                    bj=2;
                }
            }
            if (bj == 2) {
                result = mFairy.findPic2(934, 585, 1098, 648,commonFunction.setImg("bgzl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "整理"));
                if (result.sim>0.8f){
                    if (js_1==1){
                        commonFunction.RndCompare(720,90,"第一页");
                    } else if (js_1==2){
                        commonFunction.RndCompare(816,90,"第2页");
                    }else if (js_1==3){
                        commonFunction.RndCompare(916,90,"第3页");
                    }else if (js_1>4){
                        LtLog.e(commonFunction.getLineInfo("没有了结束"));
                        bj=3;
                    }
                    Thread.sleep(2000);
                    result = mFairy.findPic2(663, 109, 1107, 561,commonFunction.setImg("Fishingrod.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "钓鱼"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    if (result.sim>0.8f){
                        bj=5;
                    }else {
                        js_1++;
                    }
                }else {
                    bj=0;
                }
            }
            if (bj == 3) {
                gamePublicFunction.BackCity(1015,390,"傲来");
                Thread.sleep(2000);
                result = mFairy.findPic2(107, 22, 187, 54,commonFunction.setImg("aolaiguo.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "傲来国"));
                commonFunction.RndCompare(0.8f, result.x + 10, result.y + 10,result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 900, 520,result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 1100, 66,result, commonFunction.getImg());
                bj=4;
            }
            if (bj == 4) {
                result = mFairy.findPic2(802, 360, 932, 662,commonFunction.setImg("myg.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "买鱼竿"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim>0.8f){
                    bj=0;
                }
                result = mFairy.findPic2(447, 332, 831, 409,commonFunction.setImg("yyg.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "有鱼竿"));
                commonFunction.RndCompare(0.8f, 878, 490,result, commonFunction.getImg());
                if (result.sim>0.8f){
                    bj=0;
                }
            }
            if (bj == 5) {

                result = mFairy.findPic2(443, 10, 1263, 704,commonFunction.setImg("sybt.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "使用鱼竿"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(699, 434, 759, 488,commonFunction.setImg("yu.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "渔"));
                if (result.sim>0.8f && js_3==0){
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    Thread.sleep(5000);
                    if (yu==0){
                        result = mFairy.findPic2(568, 204, 683, 272,commonFunction.setImg("ysy.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "野生鱼"));
                        commonFunction.RndCompare(0.8f, 691, 239,result, commonFunction.getImg());

                        result = mFairy.findPic2(568, 204, 683, 272,commonFunction.setImg("ybb.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "鱼宝宝"));
                        commonFunction.RndCompare(0.8f, 922, 314,result, commonFunction.getImg());
                    }else {
                        result = mFairy.findPic2(568, 204, 683, 272,commonFunction.setImg("ysy.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "野生鱼"));
                        commonFunction.RndCompare(0.8f, 922, 314,result, commonFunction.getImg());

                        result = mFairy.findPic2(568, 204, 683, 272,commonFunction.setImg("ybb.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "鱼宝宝"));
                        commonFunction.RndCompare(0.8f, 691, 239,result, commonFunction.getImg());
                    }
                   js_3=1;
                }
                result = mFairy.findPic2(581, 505, 692, 576,commonFunction.setImg("dyyq.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "钓鱼邀请"));
                commonFunction.RndCompare(0.8f, 808, 169,result, commonFunction.getImg());

                result = mFairy.findPic2(commonFunction.setImg("mg.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "买干"));
                commonFunction.RndCompare(0.8f, 392, 490,result, commonFunction.getImg());

                result = mFairy.findPic2(commonFunction.setImg("paogan.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "抛竿"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                if (result.sim>0.8f){
                    js_2=0;
                }else {
                    js_2++;
                    if (js_2>30){
                        bj=0;
                    }
                }

                result = mFairy.findPic2(commonFunction.setImg("sure.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "确定"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(419, 294, 617, 440,commonFunction.setImg("dhsz.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "丢到水中"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

              /*  result = mFairy.findPic2(commonFunction.setImg("xxdy2.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "没鱼竿了"));
                commonFunction.RndCompare(0.8f, 864, 496,result, commonFunction.getImg());
                if (result.sim>0.8f){
                      gamePublicFunction.init();
                      break;
                }*/

                result = mFairy.findPic2(commonFunction.setImg("shougan.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "收杆"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(536, 512, 726, 587,commonFunction.setImg("fjst1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "放进水桶"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(322,298,1078,687,commonFunction.setImg("fryl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "放进鱼篓"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(595,120, 628, 158,commonFunction.setImg("yygl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "养鱼管理鱼宝宝完"));
                if (result.sim>0.8f){
                    gamePublicFunction.init();
                    break;
                }
                result=commonFunction.FindManyPic(559, 47, 706, 99,new String[]{"xxdy.png","xxdy1.png"},1);
              //  result = mFairy.findPic2(559, 47, 706, 99,commonFunction.setImg("xxdy.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "休闲钓鱼"));
                if (result.sim>0.8f){
                    result = mFairy.findPic2(323, 103, 375, 141,commonFunction.setImg("shoulu.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "收录"));
                    if (result.sim>0.8f){
                        result = mFairy.findPic2(808, 484, 978, 616,commonFunction.setImg("sltj.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "收录图鉴"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    }else {

                        result = mFairy.findPic2(595,120, 628, 158,commonFunction.setImg("yiyu.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一个兑换币"));
                        commonFunction.RndCompare(0.8f, 744, 586,result, commonFunction.getImg());
                        if (result.sim<0.8f){
                            result = mFairy.findPic2(808, 484, 978, 616,commonFunction.setImg("maichu.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "卖出"));
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());

                            result = mFairy.findPic2(691,178,817,253,commonFunction.setImg("noyu.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "卖完了"));
                            if (result.sim>0.8f){
                                gamePublicFunction.init();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    //捉宠卖钱
    public void  ZhuoGuiMaiQ()throws Exception{
        int bj = 0;
        int ret,ret1;
        int  numcolor,numcolor1;
        int js_1=0,js_2=0,js_3=0,js_4=0,js_5=0,js_6=0,js_7=0;
        int one=0;
        int ditu=0;
        String string="";
        ditu=Integer.parseInt(AtFairyConfig.getOption("ditu"));
        if (AtFairyConfig.getOption("one").equals("1")){
            one=1;
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
            LtLog.e(commonFunction.getLineInfo("捉宠卖钱中bj="+bj));
            if (bj == 0) {
                js_1=0;
                js_2=0;
                js_3=0;
                js_4=0;
                js_5=0;
                js_6=0;
                gamePublicFunction.Hyf();
                bj = 1;
            }
            if (bj==1){
                ret = gamePublicFunction.mission2("Hanging.png");
                    Thread.sleep(2000);
                    bj = 2;
                result = mFairy.findPic2(commonFunction.setImg("zdset.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "打怪双倍界面"));
                if (result.sim > 0.8) {
                    for (int i=0;i<3;i++){
                        mFairy.touchDown(2,278,264);
                        mFairy.touchMove(2,842,264,2000);
                        mFairy.touchUp(2);
                    }
                }
            }
            if (bj==2){
                result = mFairy.findPic2(commonFunction.setImg("zdset.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "打怪双倍界面"));
                if (result.sim > 0.8) {
                    result = mFairy.findPic2(246, 403, 1070, 460,commonFunction.setImg(string));
                    LtLog.e(commonFunction.getLineInfo(result, 0.86f, "地图"));
                    commonFunction.RndCompare(0.86f, result.x + 20, result.y - 200, result, commonFunction.getImg());
                    if (result.sim>0.86f){
                        bj=3;
                    }else {
                        js_5++;
                        if (js_5==2 || js_5==4 || js_5==6  || js_5==8  || js_5==10   || js_5==12 ){
                            mFairy.touchDown(2,842,264);
                            mFairy.touchMove(2, 278,264,2000);
                            mFairy.touchUp(2);
                            Thread.sleep(2000);
                        }
                        if (js_5>14){
                            bj=0;
                        }
                    }
                }else {
                    bj=0;
                }
            }
            if (bj==3){
                result = mFairy.findPic2(316,288,792,443,commonFunction.setImg("yin_gdj.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "场景等级高确定"));
                commonFunction.RndCompare(0.8f, 401,487, result, commonFunction.getImg());


                result = mFairy.findPic2(1195, 621, 1273, 707,commonFunction.setImg("unauto.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "自动中取消自动"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(754, 612, 1221, 713,commonFunction.setImg("buzhuo.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "捕捉"));
                commonFunction.RndCompare(0.8f, result.x + 10, result.y - 10, result, commonFunction.getImg());
                if (result.sim>0.8f){
                    Thread.sleep(2000);
                    result = mFairy.findPic2(110, 67, 644, 659,commonFunction.setImg("guangquan1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "宠物光圈"));
                    commonFunction.RndCompare(0.5f, result.x + 10, result.y - 10, result, commonFunction.getImg());

                    result = mFairy.findPic2(750, 609, 1192, 715,commonFunction.setImg("fangyu.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "宠物防御"));
                    commonFunction.RndCompare(0.8f, result.x + 10, result.y +10, result, commonFunction.getImg());

                    result = mFairy.findPic2(463, 157, 820, 290,commonFunction.setImg("cwman1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.1f, "身上宠物满了"));
                    if (result.sim>0.8f){
                        bj=4;
                    }
                }

                result = mFairy.findPic2(commonFunction.setImg("roundnum15.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "15回合了结束"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(1201, 641, 1263, 678,commonFunction.setImg("Bauto.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    Thread.sleep(15000);
                }

                result = mFairy.findPic2(1201, 641, 1263, 678,commonFunction.setImg("Bauto.png"));
                if (result.sim>0.8f && one==1 && js_4==0){
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    js_4=1;
                }

                result = mFairy.findPic2(59, 32, 137, 88,commonFunction.setImg("round1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中..."));
                if (result.sim>0.8f){
                    js_3=0;
                }else {
                    if (one==1){
                        js_4=0;
                    }
                    js_3++;
                    if (js_3>30){
                        bj=0;
                    }
                }
            }
            if (bj==4){
                gamePublicFunction.BackCity(698,497,"长安");
                Thread.sleep(2000);
                result = mFairy.findPic2(107, 22, 187, 54,commonFunction.setImg("cac.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "长安"));
                commonFunction.RndCompare(0.8f, result.x , result.y + 10,result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 542, 450,result, commonFunction.getImg());
                commonFunction.RndCompare(0.8f, 1124, 33,result, commonFunction.getImg());
                bj=5;
            }
            if (bj==5){
                result = mFairy.findPic2(52,78,1030,572,commonFunction.setImg("chongwujy.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.1f, "宠物交易中心"));
                commonFunction.RndCompare(0.7f, result.x+77, result.y-65, result, commonFunction.getImg());

                result = commonFunction.FindManyPic(865, 577, 1090, 647, new String[]{"Bshop.png", "Bsdshop.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "购买"));
                if (result.sim > 0.8) {
                    result = mFairy.findPic2(1004, 113, 1109, 483,commonFunction.setImg("chushou.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "宠物出售"));
                    if (result.sim<0.8f){
                        mFairy.finish(FAIRY_TYPE_TASK, "", 2043);
                        return;
                    }
                    for ( int i=0;i<10;i++){
                        result = mFairy.findPic2(1004, 113, 1109, 483,commonFunction.setImg("chushou.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "宠物出售"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        if (result.sim>0.8f){
                            result = mFairy.findPic2(491, 179, 792, 268,commonFunction.setImg("cwmax.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "30个卖完了"));
                            commonFunction.RndCompare(0.8f,1120, 47,result, commonFunction.getImg());
                            if (result.sim>0.8f){
                                return;
                            }
                            result = mFairy.findPic2(442, 138, 836, 323,commonFunction.setImg("kcmax.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "存量太高无法卖出"));
                            if (result.sim>0.8f){
                                TaskMain.task=7;
                                return;
                            }
                        }
                    }
                    bj=0;
                }
                js_2++;
                if (js_2>20){
                    bj=4;
                    js_2=0;
                }
            }
        }
    }


}
