package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Administrator on 2018/5/21.
 */

public class GamePublicFunction {
    FindResult findResult;
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    FindResult result2;
    CommonFunction commonFunction;
    MatTime matTime;
    public GamePublicFunction(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        commonFunction = new CommonFunction(mFairy);
        matTime=new MatTime(mFairy);
    }
    //不需要取消挂机初始化
    public void init() throws Exception {
        int js_1=0,js_2=0;
        for (int i=0;i<150;i++){
            LtLog.e(mFairy.getLineInfo("初始化中"));

            findResult = mFairy.findPic(968,46,1053,117,"close4.png");
            mFairy.onTap(0.8f,findResult,"关闭",1000);


            findResult = mFairy.findPic(963,31,1262,126,"close5.png");
            mFairy.onTap(0.8f,findResult,"关闭",1000);

            findResult = mFairy.findPic(753,421,994,560,new String[]{"quxiao.png","quxiao1.png"});
            mFairy.onTap(0.7f,findResult,"取消",1000);

            Thread.sleep(10);
            result = mFairy.findPic2(217, 4, 628, 85,commonFunction.setImg("Nricheng1.png"));
            //   LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
            result1 = mFairy.findPic2(1188, 574, 1273, 621,commonFunction.setImg("Nwupin.png"));
            //  LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "物品"));
            if (result.sim>0.8f  && result1.sim>0.8f){
                long fdtime= matTime.mMatTime(120,60,56,18,0.9f);
                Thread.sleep(1000);
                fdtime= matTime.mMatTime(120,60,56,18,0.9f);
                if (fdtime>0){
                }else {
                    commonFunction.RndCompare(768,302,"点一下停止移动");
                }

                //  "Nfork3.png", "Nfork4.png", "Nfork5.png","Nfork1.png","Nfork2.png","Nfork7.png",,"Nfork8.png","Nfork7.png"

                result = commonFunction.FindManyPic(2,4,1275,431, new String[]{
                        "Nfork6.png","Nfork5.png","Nfork3.png","Nfork9.png","Nfork10.png","fork11.png","fork12.png","close3.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "发现一个叉子"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());


                result = mFairy.findPic2(1012, 185, 1207, 483,commonFunction.setImg("Bzzzb.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "在做准备"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                LtLog.e(commonFunction.getLineInfo("结束初始化"));
                break;
            }


            result = mFairy.findPic2(59, 32, 137, 88,commonFunction.setImg("round1.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中"));
            if (result.sim > 0.8f) {
                i = 0;
                js_2++;
            }else {
                if (js_2>=1){
                    commonFunction.RndCompare(768,302,"点一下停止移动");
                    js_2=0;
                }
            }

            result = mFairy.findPic2(commonFunction.setImg("xwm.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "地图玄武门"));
            if (result.sim>0.8f){
                Signout1();
            }


            for (int j = 0; j < 10;j++) {
                result = mFairy.findPic(207,633,1213,717,"duihua4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f,result,"对话按钮",1000);
                } else {
                    break;
                }
            }


            result = mFairy.findPic2(1012, 185, 1207, 483,commonFunction.setImg("Bzzzb.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "在做准备"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());



            result = mFairy.findPic2(1201, 641, 1263, 678,commonFunction.setImg("Bauto.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());

            result = mFairy.findPic2(44, 2, 657, 80,commonFunction.setImg("yxsldt1.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "试炼界面"));
            commonFunction.RndCompare(0.8f, 1223, 664,result, commonFunction.getImg());

            result = mFairy.findPic2(1201, 652, 1268, 719,commonFunction.setImg("Signout.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.7f, "右下角退出"));
            commonFunction.Compare(0.7f, result, commonFunction.getImg());

            result = mFairy.findPic2(44, 2, 657, 80,commonFunction.setImg("yxsldt11.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "试炼界面1"));
            commonFunction.RndCompare(0.8f, 1223, 664,result, commonFunction.getImg());

            result = mFairy.findPic2(22, 158, 87, 215,commonFunction.setImg("Signout1.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "钓鱼退出退出"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());


            result = mFairy.findPic2(554, 228, 726, 298,commonFunction.setImg("SignoutDy.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "退出钓鱼"));
            commonFunction.RndCompare(0.8f, 385,475,result, commonFunction.getImg());


            // "Nfork3.png", "Nfork4.png", "Nfork5.png","Nfork8.png","Nfork7.png",
            result = commonFunction.FindManyPic(2,4,1275,431, new String[]{ "Nfork6.png","Nfork5.png","Nfork3.png","Nfork9.png","Nfork10.png","fork11.png","fork12.png"
                    ,"close1.png","close2.png"}, 0);
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "发现一个叉子"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());

        }
    }
    //不需要取消挂机初始化
    public void init1() throws Exception {
        int js_1=0,js_2=0;
        for (int i=0;i<150;i++){
            LtLog.e(mFairy.getLineInfo("初始化中"));
            Thread.sleep(10);
            result = mFairy.findPic2(217, 4, 628, 85,commonFunction.setImg("Nricheng1.png"));
            //   LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
            result1 = mFairy.findPic2(1188, 574, 1273, 621,commonFunction.setImg("Nwupin.png"));
            //  LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "物品"));
            if (result.sim>0.8f  && result1.sim>0.8f){
                //  "Nfork3.png", "Nfork4.png", "Nfork5.png","Nfork1.png","Nfork2.png","Nfork7.png",,"Nfork8.png","Nfork7.png"
                result = commonFunction.FindManyPic(2,4,1275,431, new String[]{ "Nfork6.png","Nfork5.png","Nfork3.png","Nfork10.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "发现一个叉子"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());

                result = mFairy.findPic2(1012, 185, 1207, 483,commonFunction.setImg("Bzzzb.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "在做准备"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                LtLog.e(commonFunction.getLineInfo("结束初始化"));
                break;
            }
            result = mFairy.findPic2(59, 32, 137, 88,commonFunction.setImg("round1.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "战斗中"));
            if (result.sim > 0.8f) {
                i = 0;
                js_2++;
            }else {
                if (js_2>=1){
                    commonFunction.RndCompare(768,302,"点一下停止移动");
                    js_2=0;
                }
            }

            result = mFairy.findPic2(commonFunction.setImg("xwm.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "地图玄武门"));
            if (result.sim>0.8f){
                Signout1();
            }

            result = mFairy.findPic2(1012, 185, 1207, 483,commonFunction.setImg("Bzzzb.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "在做准备"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());

            result = mFairy.findPic2(554, 228, 726, 298,commonFunction.setImg("SignoutDy.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "退出钓鱼"));
            commonFunction.RndCompare(0.8f, 410, 493,result, commonFunction.getImg());

            result = mFairy.findPic2(1201, 641, 1263, 678,commonFunction.setImg("Bauto.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击自动"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());

            result = mFairy.findPic2(44, 2, 657, 80,commonFunction.setImg("yxsldt1.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "试炼界面"));
            commonFunction.RndCompare(0.8f, 1223, 664,result, commonFunction.getImg());

            result = mFairy.findPic2(1201, 652, 1268, 719,commonFunction.setImg("Signout.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.7f, "右下角退出"));
            commonFunction.Compare(0.7f, result, commonFunction.getImg());

            result = mFairy.findPic2(44, 2, 657, 80,commonFunction.setImg("yxsldt11.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "试炼界面1"));
            commonFunction.RndCompare(0.8f, 1223, 664,result, commonFunction.getImg());

            result = mFairy.findPic2(22, 158, 87, 215,commonFunction.setImg("Signout1.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "钓鱼退出退出"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());

           /* // "Nfork3.png", "Nfork4.png", "Nfork5.png","Nfork8.png","Nfork7.png",
            result = commonFunction.FindManyPic(2,4,1275,431, new String[]{ "Nfork6.png","Nfork5.png","Nfork3.png","Nfork10.png","fork11.png","fork12.png"}, 0);
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "发现一个叉子"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());*/

            result = commonFunction.FindManyPic(2,4,1275,431, new String[]{ "Nfork6.png","Nfork5.png","Nfork3.png","Nfork9.png","Nfork10.png","fork11.png","fork12.png"
                    ,"close1.png","close2.png"}, 0);
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "发现一个叉子"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());


        }
    }
    //设置快捷栏位置
    public void SetJudgeTask(String x) throws Exception {

        result = mFairy.findPic2(1243, 132, 1277, 178,commonFunction.setImg("TaskOpen.png"));
        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "展开任务栏"));
        commonFunction.Compare(0.8f, result, commonFunction.getImg());


        result2 = mFairy.findPic2(59, 32, 137, 88,commonFunction.setImg("round1.png"));
        result = mFairy.findPic2(217, 4, 628, 85,commonFunction.setImg("Nricheng1.png"));
        result1 = mFairy.findPic2(1188, 574, 1273, 621,commonFunction.setImg("Nwupin.png"));
        if ((result.sim>0.8f || result2.sim>0.8f )  && result1.sim>0.8f) {
            LtLog.e("判断成功");
            if (x.equals("task")) {
                result = mFairy.findPic2(1172, 127, 1238, 188, commonFunction.setImg("nowdw1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "在队伍框,点击任务框."));
                commonFunction.RndCompare(0.8f, 1091, 155, result, commonFunction.getImg());

                result = mFairy.findPic2(593, 578, 757, 653, commonFunction.setImg("Bfqtask.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "放弃任务，点击关闭..."));
                commonFunction.RndCompare(0.8f, 1124, 42, result, commonFunction.getImg());
            } else {
                result = commonFunction.FindManyPic(3,4,1267,689, new String[]{"nowdw1.png", "nowdw4.png"}, 0);
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "打开队伍界面"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
            }
        }
    }
    //开始任务
    public int mission(String str) throws Exception {
        int bj = 0, js_2 = 0,js_3=0;
        int cs_1 = 0;
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("接取任务中bj="+bj));
            if (bj == 0) {
                init();
                SetJudgeTask("task");
                js_2=0;
                bj = 1;
            }
            if (bj == 1) {
                result = mFairy.findPic2(217, 4, 628, 85,commonFunction.setImg("Nricheng1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                commonFunction.delays(0.8f,result,3000);
                result = mFairy.findPic2(222, 95, 1060, 413,commonFunction.setImg("cgldl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "炼丹炉"));
                if (result.sim>0.8f){
                    result = mFairy.findPic2(result.x, result.y - 70, result.x + 100, result.y,commonFunction.setImg("ldlhd.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "炼丹炉红点"));
                    if (result.sim > 0.8f) {
                        commonFunction.RndCompare(result.x - 30, result.y + 30, "炼丹炉");
                        for (int i=0;i<2;i++){
                            result = mFairy.findPic2(449, 596, 611, 649,commonFunction.setImg("yjsq.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一键收取"));
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());

                            result = mFairy.findPic2(197, 590, 351, 648,commonFunction.setImg("yjlz.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一键炼制"));
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        }
                        result = mFairy.findPic2(197, 590, 351, 648,commonFunction.setImg("yjlz.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "关闭炼丹"));
                        commonFunction.RndCompare(0.8f, 1123, 44, result, commonFunction.getImg());
                    }
                }
                bj=2;
            }
            if (bj == 2) {
                result = mFairy.findPic2(217, 4, 628, 85,commonFunction.setImg("Nricheng1.png"));
                if (result.sim>0.8f) {
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    Thread.sleep(2000);
                }
                result = mFairy.findPic2(550, 50, 734, 108,commonFunction.setImg("cghd.png"));
                if (result.sim>0.8f){

                    LtLog.e(mFairy.getLineInfo("活动界面"));


                    result = mFairy.findPic2(211,92,1086,692,commonFunction.setImg(str));
                    LtLog.e(commonFunction.getLineInfo(result, 0.7f, "找到任务"+str));
                    if (result.sim>0.96f){

                        if (AtFairyConfig.getOption("zgsl").equals("1") && str.equals("Ghost Chase.png")){
                            //696,151  692,187,710,224

                            result1 = mFairy.findPic2(result.x +30, result.y+1, result.x + 108, result.y + 127,commonFunction.setImg("zghy1.png"));
                            LtLog.e(commonFunction.getLineInfo(result1, 0.1f, "活跃鬼"));
                            if (result1.sim>0.8f){
                                result1 = mFairy.findPic2(result.x -2, result.y+36, result.x + 14, result.y + 73,commonFunction.setImg("zghy2.png"));
                                LtLog.e(commonFunction.getLineInfo(result1, 0.1f, "数量的十位数是不是1"));
                                if (result1.sim>0.8f){

                                }else {
                                    return 0;
                                }
                            }
                        }
                        if (AtFairyConfig.getOption("fbone").equals("1")  && str.equals("cghdfb.png") ){
                            result1 = mFairy.findPic2(result.x +30, result.y+56, result.x + 108, result.y + 127,commonFunction.setImg("zghy1.png"));
                            LtLog.e(commonFunction.getLineInfo(result1, 0.1f, "跟队副本活跃"));
                            if (result1.sim>0.8f){
                                commonFunction.RndCompare(0.8f, result.x+85, result.y+97, result, commonFunction.getImg());
                                Thread.sleep(1000);
                                result1 = mFairy.findPic2(result.x -80, result.y+30, result.x -51, result.y +62,commonFunction.setImg("fbyl.png"));
                                LtLog.e(commonFunction.getLineInfo(result1, 0.1f, "是不是5"));
                                if (result1.sim>0.8f){
                                    Signout();
                                    return 0;
                                }
                            }
                        }

                        if (AtFairyConfig.getOption("yl").equals("1")  && str.equals("cghdfb.png") ){
                            result1 = mFairy.findPic2(result.x +30, result.y+56, result.x + 108, result.y + 127,commonFunction.setImg("zghy1.png"));
                            LtLog.e(commonFunction.getLineInfo(result1, 0.1f, "带队副本活跃"));
                            if (result1.sim>0.8f){
                                commonFunction.RndCompare(0.8f, result.x+85, result.y+97, result, commonFunction.getImg());
                                Thread.sleep(1000);
                                result1 = mFairy.findPic2(result.x -80, result.y+30, result.x -51, result.y +62,commonFunction.setImg("fbyl.png"));
                                LtLog.e(commonFunction.getLineInfo(result1, 0.1f, "是不是5"));
                                if (result1.sim>0.8f){
                                    return 0;
                                }
                            }
                        }





                        commonFunction.Compare(0.96f, result, commonFunction.getImg());
                        return 1;
                    }else if (result.sim>0.7f){
                        LtLog.e(commonFunction.getLineInfo("任务完成"));
                        return 0;
                    }
                }

                js_2++;
                LtLog.e(commonFunction.getLineInfo("js_2=" + js_2));
                if (js_2 >= 5 ) {
                    LtLog.e(commonFunction.getLineInfo("任务没有找到"));
                    return 0;
                }
            }
        }
        return 0;
    }
    //开始任务1
    public int mission1(String str,String str1) throws Exception {
        int bj = 0, js_2 = 0,js_3=0;
        int cs_1 = 0;
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("接取任务中bj="+bj));
            if (bj == 0) {
                init();
                SetJudgeTask("task");
                js_2=0;
                js_3=0;
                bj = 1;
            }
            if (bj == 1) {
                result = mFairy.findPic2(217, 4, 628, 85,commonFunction.setImg("Nricheng1.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    commonFunction.delays(0.8f, result, 3000);

                result = mFairy.findPic2(222, 95, 1060, 413,commonFunction.setImg("cgldl.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "炼丹炉"));
                if (result.sim>0.8f){
                    result = mFairy.findPic2(result.x, result.y - 70, result.x + 100, result.y,commonFunction.setImg("ldlhd.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "炼丹炉红点"));
                    if (result.sim > 0.8f) {
                        commonFunction.RndCompare(result.x - 30, result.y + 30, "炼丹炉");
                        for (int i=0;i<2;i++){
                            result = mFairy.findPic2(449, 596, 611, 649,commonFunction.setImg("yjsq.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一键收取"));
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());

                            result = mFairy.findPic2(197, 590, 351, 648,commonFunction.setImg("yjlz.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一键炼制"));
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        }
                        result = mFairy.findPic2(197, 590, 351, 648,commonFunction.setImg("yjlz.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "关闭炼丹"));
                        commonFunction.RndCompare(0.8f, 1123, 44, result, commonFunction.getImg());
                    }
                }


                bj=2;
            }


            if (bj == 2) {


                result = mFairy.findPic2(217, 4, 628, 85,commonFunction.setImg("Nricheng1.png"));
                if (result.sim>0.8f) {
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    Thread.sleep(2000);
                }
                result = mFairy.findPic2(550, 50, 734, 108,commonFunction.setImg("cghd.png"));

                if (result.sim>0.8f){

                    LtLog.e(mFairy.getLineInfo("活动界面"));

                    result = mFairy.findPic2(227, 437, 1048, 592,commonFunction.setImg(str));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "找到任务"+str));
                    if (result.sim>0.8f){
                        result = mFairy.findPic2(result.x - 20, result.y, result.x + 100, result.y + 120,commonFunction.setImg("hbjoin.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "前往参加"));
                        commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        if (result.sim>0.8f){
                            return 1;
                        }
                    }
                    result = mFairy.findPic2(227, 437, 1048, 592,commonFunction.setImg(str1));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "任务完成"+str1));
                    if (result.sim>0.8f){
                        return 0;
                    }
                    js_3=0;
                }else {
                    js_3++;
                    if (js_3>5){
                        bj=0;
                    }
                }
                js_2++;
                LtLog.e(commonFunction.getLineInfo("js_2=" + js_2));
                if (js_2 == 3  || js_2==6  || js_2==9 ) {
                    commonFunction.RanSwipe(263, 489, 853, 500, 3, 1000);
                    LtLog.e(commonFunction.getLineInfo("滑动一下"));
                }
                if (js_2>=12){
                    LtLog.e(commonFunction.getLineInfo("任务没有找到"));
                    return 0;
                }
            }
        }
        return 0;
    }
    //开始任务
    public int mission2(String str) throws Exception {
        int bj = 0, js_2 = 0,js_3=0;
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("接取任务中bj="+bj));
            if (bj == 0) {
                init();
                SetJudgeTask("task");
                js_2=0;
                bj = 2;
            }
            /*if (bj == 1) {
                result = mFairy.findPic2(217, 4, 628, 85,commonFunction.setImg("Nricheng1.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                commonFunction.delays(0.8f,result,3000);




                result = mFairy.findPic(343,593,528,688,"activity.png");
                if (result.sim>0.8f){
                    LtLog.e(commonFunction.getLineInfo("日程界面"));






                    *//*result = mFairy.findPic2(result.x, result.y - 70, result.x + 100, result.y,commonFunction.setImg("ldlhd.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "炼丹炉红点"));
                    if (result.sim > 0.8f) {
                        commonFunction.RndCompare(result.x - 30, result.y + 30, "炼丹炉");
                        Thread.sleep(2000);
                        for (int i=0;i<2;i++){
                            result = mFairy.findPic2(449, 596, 611, 649,commonFunction.setImg("yjsq.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一键收取"));
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());

                            result = mFairy.findPic2(197, 590, 351, 648,commonFunction.setImg("yjlz.png"));
                            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一键炼制"));
                            commonFunction.Compare(0.8f, result, commonFunction.getImg());
                        }
                        result = mFairy.findPic2(197, 590, 351, 648,commonFunction.setImg("yjlz.png"));
                        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "关闭炼丹"));
                        commonFunction.RndCompare(0.8f, 1123, 44, result, commonFunction.getImg());
                    }*//*
                }
                bj=2;
            }*/



            if (bj == 2) {
                result = mFairy.findPic2(217, 4, 628, 85,commonFunction.setImg("Nricheng1.png"));
                if (result.sim>0.8f) {
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "日常"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                    Thread.sleep(2000);
                }
                result = mFairy.findPic2(550, 50, 734, 108,commonFunction.setImg("cghd.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "常规活动"));
                if (result.sim>0.8f){

                    if(str.equals("Hanging.png")){
                        mFairy.onTap(944,419,962,428,"打怪双倍",2500);
                        return 1;
                    }else {
                        result = mFairy.findPic(211, 92, 1086, 692, str);
                        LtLog.e(commonFunction.getLineInfo(result, 0.1f, "找到任务" + str));
                        if (result.sim > 0.7f) {
                            commonFunction.Compare(0.7f, result, commonFunction.getImg());
                            return 1;
                        }
                    }
                }






                js_2++;
                LtLog.e(commonFunction.getLineInfo("js_2=" + js_2));
                if (js_2 >= 5 ) {
                    LtLog.e(commonFunction.getLineInfo("任务没有找到"));
                    bj=0;
                    js_3++;
                    if (js_3>1){
                        return 0;
                    }
                }




            }
        }
        return 0;
    }
    //退队
    public void Signout() throws Exception {
        int js_1=0,js_2=0;
        init();
        for (int i=0;i<150;i++){
            Thread.sleep(100);

            LtLog.e(mFairy.getLineInfo("退队中"));

            SetJudgeTask("team");
            result = mFairy.findPic2(201, 68, 352, 127,commonFunction.setImg("Bcreateteam.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "创建队伍"));
            if (result.sim>0.8f){
                break;
            }
            result = mFairy.findPic2(182,59,370,137,commonFunction.setImg("Blkdw.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());

            result = mFairy.findPic2(498, 194, 797, 330,commonFunction.setImg("ldqr1.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离队确认"));
            commonFunction.RndCompare(0.8f, 391, 489,result, commonFunction.getImg());


            result = mFairy.findPic2(314,298,963,443,commonFunction.setImg("yin_ld.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离队扣活跃"));
            commonFunction.RndCompare(0.8f, 391, 489,result, commonFunction.getImg());

        }
    }

    public void Signout1() throws Exception {
        int js_1=0,js_2=0;
        for (int i=0;i<150;i++){
            Thread.sleep(100);
            LtLog.e(mFairy.getLineInfo("退队中"));
            SetJudgeTask("team");


            result = mFairy.findPic2(201, 68, 352, 127,commonFunction.setImg("Bcreateteam.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "创建队伍"));
            if (result.sim>0.8f){
                break;
            }
            result = mFairy.findPic2(182,59,370,137,commonFunction.setImg("Blkdw.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());

            result = mFairy.findPic2(498, 194, 797, 330,commonFunction.setImg("ldqr1.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离队确认"));
            commonFunction.RndCompare(0.8f, 391, 489,result, commonFunction.getImg());


            result = mFairy.findPic2(314,298,963,443,commonFunction.setImg("yin_ld.png"));
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离队扣活跃"));
            commonFunction.RndCompare(0.8f, 391, 489,result, commonFunction.getImg());

        }
    }

    //回城
    public void BackCity(int x,int y,String str) throws Exception {
        init();
        LtLog.e(mFairy.getLineInfo("回城中"));
        result = mFairy.findPic2(217, 4, 628, 85,commonFunction.setImg("Nricheng1.png"));
        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击地图"));
        commonFunction.RndCompare(0.8f, 46, 44,result, commonFunction.getImg());
        Thread.sleep(1500);
        commonFunction.RndCompare(0.8f, x, y,result, str);
    }

    //判断队伍并回原服
    public void Hyf() throws Exception {
        int bj = 0;
        for (int i1=0;i1<7;i1++){
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("判断队伍并回原服中bj=" + bj));
            if (bj == 0) {

                init();
                result = mFairy.findPic2(commonFunction.setImg("yuanfu.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "在跨服了回原服"));
                if (result.sim>0.8f){
                    bj = 1;
                }else {
                    LtLog.e(mFairy.getLineInfo("不在跨服"));
                    break;
                }

            }
            if (bj == 1) {
                SetJudgeTask("team");
                result = mFairy.findPic2(201, 68, 352, 127, commonFunction.setImg("Bcreateteam.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "一个人"));
                if (result.sim > 0.8f) {
                    bj = 2;
                }
                result = mFairy.findPic2(182, 59, 370, 137, commonFunction.setImg("Blkdw.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "离开队伍"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic2(787, 594, 928, 651, commonFunction.setImg("Bsqdz.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "是队员"));
                    if (result.sim > 0.8f) {
                        Signout();
                    } else {
                        LtLog.e(commonFunction.getLineInfo("是队长"));
                        for (int i=0; i<1;i++){
                            result = mFairy.findPic2(356,306,446,351,commonFunction.setImg("kfdren.png"));
                            if (result.sim>0.9f){
                                LtLog.e(commonFunction.getLineInfo("1个人"));
                                commonFunction.RndCompare(0.8f, 318, 288,result, commonFunction.getImg());
                                Thread.sleep(2000);
                                result = mFairy.findPic2(477, 5, 631, 711,commonFunction.setImg("qldy.png"));
                                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员"));
                                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                                result = mFairy.findPic2(314,298,963,443,commonFunction.setImg("yin_qldy.png"));
                                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员扣活跃"));
                                commonFunction.RndCompare(0.8f, 395, 489,result, commonFunction.getImg());
                                i=0;
                            }
                            result = mFairy.findPic2(358,410,451,453,commonFunction.setImg("kfdren.png"));
                            if (result.sim>0.9f){
                                LtLog.e(mFairy.getLineInfo("2个人"));
                                commonFunction.RndCompare(0.8f, 323, 398,result, commonFunction.getImg());
                                Thread.sleep(2000);
                                result = mFairy.findPic2(477, 5, 631, 711,commonFunction.setImg("qldy.png"));
                                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员"));
                                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                                result = mFairy.findPic2(314,298,963,443,commonFunction.setImg("yin_qldy.png"));
                                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员扣活跃"));
                                commonFunction.RndCompare(0.8f, 395, 489,result, commonFunction.getImg());
                                i=0;
                            }
                            result = mFairy.findPic2(360,518,447,555,commonFunction.setImg("kfdren.png"));
                            if (result.sim>0.9f){
                                LtLog.e(commonFunction.getLineInfo("3个人"));

                                commonFunction.RndCompare(0.8f, 315, 502,result, commonFunction.getImg());

                                Thread.sleep(2000);
                                result = mFairy.findPic2(477, 5, 631, 711,commonFunction.setImg("qldy.png"));
                                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员"));
                                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                                result = mFairy.findPic2(314,298,963,443,commonFunction.setImg("yin_qldy.png"));
                                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员扣活跃"));
                                commonFunction.RndCompare(0.8f, 395, 489,result, commonFunction.getImg());
                                i=0;
                            }
                            result = mFairy.findPic2(361,623,448,661,commonFunction.setImg("kfdren.png"));
                            if (result.sim>0.9f){
                                LtLog.e(commonFunction.getLineInfo("4个人"));
                                commonFunction.RndCompare(0.8f, 307, 604,result, commonFunction.getImg());
                                Thread.sleep(2000);
                                result = mFairy.findPic2(477, 5, 631, 711,commonFunction.setImg("qldy.png"));
                                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员"));
                                commonFunction.Compare(0.8f, result, commonFunction.getImg());
                                result = mFairy.findPic2(314,298,963,443,commonFunction.setImg("yin_qldy.png"));
                                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "请离队员扣活跃"));
                                commonFunction.RndCompare(0.8f, 395, 489,result, commonFunction.getImg());
                                i=0;
                            }
                        }
                    }
                    bj = 2;
                }
            }
            if (bj==2) {
                BackCity(698,497,"长安");
                for (int i=0; i<2;i++){
                    result = mFairy.findPic2(commonFunction.setImg("yuanfu.png"));
                    LtLog.e(commonFunction.getLineInfo(result, 0.8f, "点击了原服"));
                    commonFunction.Compare(0.8f, result, commonFunction.getImg());
                }
                break;
            }
        }
    }

    public String testchange(int x1, int y1, int width, int height) throws Exception {

//        result=publicFunction.localFindPic(537, 368, 651, 409, "actViolently1.png");
        Core.MinMaxLocResult mmr;

        Mat mat1 = mFairy.getScreenMat(x1, y1, width, height, 1, 0, 0, 1);

//        Thread.sleep(200);

        for (int i = 0; i < 10; i++) {
            Mat mat2 = mFairy.getScreenMat(x1, y1, width, height, 1, 0, 0, 1);
//            LtLog.i(publicFunction.getLineInfo() + "----------------------------mat2=" +mat2.size()+ "," + mat2.channels());
//        Imgcodecs.imwrite("/sdcard/2.png",mat2);

            Mat dst = new Mat();
            Core.absdiff(mat1, mat2, dst);
            Imgproc.cvtColor(dst, dst, Imgproc.COLOR_RGB2GRAY);
//            LtLog.i(publicFunction.getLineInfo() + "----------------------------answerStr=" +dst.size()+ "," + dst.channels());
            Imgproc.threshold(dst, dst, 0, 0, Imgproc.THRESH_TOZERO);

//            LtLog.i(publicFunction.getLineInfo() + "----------------------------answerStr=" +dst.size()+ "," + dst.channels());

            mmr = Core.minMaxLoc(dst);
            if (mmr.maxLoc.x > 0) {
                return ((int) mmr.maxLoc.x + x1) + "," + ((int) mmr.maxLoc.y + y1);
            }
//            LtLog.i(publicFunction.getLineInfo() + "----------------------------answerStr=" + mmr.maxVal  + "," + mmr.maxLoc.x + "," + mmr.maxLoc.y);
        }


        return "0,0";

    }



}









