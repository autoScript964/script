package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

public class GamePublicFuntion {
    public AtFairyImpl mFairy;
    public FindResult result;
    public FindResult result1;
    public long time = System.currentTimeMillis();

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
    }

    public void init(int num) throws Exception {
        int a = 0;
        int b = 0;
         while (mFairy.condit()) {
            result = mFairy.findPic(512, 0, 1274, 539,
                    new String[]{"close.png", "tux.png", "cjx.png", "meet.png",
                            "close1.png", "close2.png", "close3.png", "close4.png",
                            "close5.png","close6.png"});
            mFairy.onTap(0.7f, result,  "关闭窗口",1000);

            result = mFairy.findPic("ftw.png");
            mFairy.onTap(0.7f, result, 1202, 296,1203,297, "离开",1000);

            result = mFairy.findPic("signOut1.png");
            if(result.sim>0.7f) {
                mFairy.onTap(0.7f, result,  "退出",1000);
                Thread.sleep(3000);
                mFairy.onTap(753, 432,754,433, "确定",1000);
            }

            result = mFairy.findPic("tip.png");
            mFairy.onTap(0.8f, result,  "Tip",1000);

            result = mFairy.findPic("rightTip.png");
            mFairy.onTap(0.8f, result,  "Tip",1000);

             result = mFairy.findPic("jia3.png");
             mFairy.onTap(0.8f, result,  "挖宝图-确定",1000);

            result = mFairy.findPic("jia.png");
            mFairy.onTap(0.9f,result,1092,89,1093,90,"奖励",1000);

            result = mFairy.findPic("click.png");
            mFairy.onTap(0.9f, result,  "点击任意屏幕继续",1000);

            result = mFairy.findPic("click1.png");
            mFairy.onTap(0.9f, result,  "点击任意屏幕跳过",1000);

            result = mFairy.findPic("xkzcEnd.png");
            mFairy.onTap(0.8f,result,638,662,639,663,"确定",1000);

            result = mFairy.findPic("auto.png");
            if (result.sim > 0.7f) {
                for (int i = 0; i < 3; i++) {
                    result = mFairy.findPic("rightTip.png");
                    mFairy.onTap(0.8f, result,  "Tip",1000);
                }
            }

            if (a == 0) {
                switch (num) {
                    case 0:
                        result = mFairy.findPic("ranksButton.png");
                        if (result.sim > 0.6f) {
                            a = 1;
                            mFairy.onTap(0.6f, result,  "点击队伍",1000);
                            mFairy.onTap(0.6f, result,  "点击队伍",1000);
                            Thread.sleep(2000);
                            result = mFairy.findPic("signOut.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result,  "退出",1000);
                                mFairy.onTap(758, 426, 759,427,"确定退队",1000);
                            }
                        }
                        break;


                    case 1:

                        result = mFairy.findPic("ranksButton.png");
                        if (result.sim > 0.6f) {
                            a = 1;
                            mFairy.onTap(0.6f, result,  "点击队伍",1000);
                            mFairy.onTap(0.6f, result,  "点击队伍",1000);
                            Thread.sleep(2000);

                            result = mFairy.findPic("ranks1.png");
                            mFairy.onTap(0.8f, result,  "点队伍",1000);
                            result = mFairy.findPic("zanli.png");
                            mFairy.onTap(0.8f, result,  "暂离",1000);
                        }
                        break;


                    case 2:

                        result = mFairy.findPic("ranksButton.png");
                        if (result.sim > 0.6f) {
                            a = 1;
                            mFairy.onTap(0.6f, result,  "点击队伍",1000);
                            mFairy.onTap(0.6f, result,  "点击队伍",1000);
                            Thread.sleep(2000);
                            result = mFairy.findPic("ranks1.png");
                            mFairy.onTap(0.8f, result,  "点队伍",1000);
                            result = mFairy.findPic("duizhang.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result,  "我是队长",1000);
                            } else {
                                result = mFairy.findPic("signOut.png");
                                if (result.sim > 0.8f) {
                                    mFairy.onTap(0.8f, result,  "退出",1000);
                                    mFairy.onTap(758, 426,759,427, "确定退队",1000);
                                }

                            }

                        }
                        break;

                    case 3:

                        result = mFairy.findPic("ranksButton.png");
                        if (result.sim > 0.6f) {
                            a = 1;
                            mFairy.onTap(0.6f, result,  "点击队伍",1000);
                            mFairy.onTap(0.6f, result,  "点击队伍",1000);
                            Thread.sleep(2000);
                            result = mFairy.findPic("ranks1.png");
                            mFairy.onTap(0.8f, result,  "点队伍",1000);

                            result = mFairy.findPic("guidui.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result,  "归队",1000);
                            } else {
                                result = mFairy.findPic("duizhang.png");
                                if (result.sim > 0.8f) {
                                    result = mFairy.findPic("signOut.png");
                                    if (result.sim > 0.8f) {
                                        mFairy.onTap(0.8f, result,  "退出",1000);
                                        mFairy.onTap(758, 426,759,427, "确定退队",1000);
                                    }
                                }
                            }

                        }
                        break;
                    default:
                        break;
                }
                result = mFairy.findPic("auto.png");
                if (result.sim > 0.7f) {
                    int i=0;
                     while (mFairy.condit()) {
                        LtLog.e("-----   有任务要执行，找地图");
                        result = mFairy.findPic("mapButton.png");
                        LtLog.e("地图sim  " + result.sim);
                        mFairy.onTap(0.7f, result,  "地图",1000);
                        Thread.sleep(1000);
                        result = mFairy.findPic(512, 0, 1274, 539, "close.png");
                        mFairy.onTap(0.7f, result,  "关闭",1000);

                        result = mFairy.findPic("jingcheng.png");
                        mFairy.onTap(0.7f, result,  "京城",1000);

                        result = mFairy.findPic("qwe9.png");
                        if (result.sim > 0.8f) {
                            break;
                        }

                        result = mFairy.findPic("auto.png");
                        if(result.sim > 0.7f){
                            i=0;
                        }else{
                            i++;
                        }

                        if(i>30){
                            break;
                        }
                    }

                }
            }

            result=mFairy.findPic("likai.png");
            mFairy.onTap(0.85f,result,763,425,764,426,"确定离开",1000);

            result = mFairy.findPic("activity.png");
            if (result.sim > 0.8f) {

                result = mFairy.findPic("task1.png");
                LtLog.e(mFairy.getLineInfo("task1:"+result.sim));
                mFairy.onTap(0.7f,result, 1064, 125,1065,126,  "任务",1000);

                break;
            }
            //}

        }
    }//初始化设置

    public int initTools(int bj, String name,int bool) throws Exception {
        int num;
        String str;
        int cs = 0, cs1 = 0;
        int count = 0, count1 = 0,count2=0;
        //bool=1（必须退队进行任务）  bool=2（自己必须是队长） bool=3(跟队进行)
        if (bool == 1) {
            num = 0;
        } else if (bool == 2) {
            num = 2;
        } else if (bool == 3) {
            num = 3;
        } else {
            str = AtFairyConfig.getOption("zdjx");
            if (str.equals("")) {
                num = 0;
            } else {
                num = Integer.parseInt(str);
            }
        }


         while (mFairy.condit()) {
            LtLog.e("----------------------" + name + " 任务bj=" + bj);
            LtLog.e("----------------------" + count1);
            if (bj == 0) {
                init(num);
                count = 0;
                count2=0;
                cs = 0;
                bj = 1;
            }
            if (bj == 1) {
                cs++;
                if (cs > 10) {
                    bj = 0;
                }
                result = mFairy.findPic("activity.png");
                if(result.sim>0.8f) {
                    mFairy.onTap(0.8f, result,  "活动",2500);
                }

                result = mFairy.findPic("activity1.png");
                if(result.sim>0.8f) {
                    bj=2;
                }
            }
            if (bj == 2) {
                result = mFairy.findPic(853,152,1166,518, name);
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic(result.x+109, result.y-15, result.x+251, result.y + 100, "canjia.png");//未完成
                    if (result1.sim > 0.8f) {
                        mFairy.onTap(0.8f, result1,  "参加",1000);
                        bj = 3;
                        Thread.sleep(8000);
                    }else{
                        count2++;
                        if(count2>10){
                            return -1;
                        }
                    }
                    result = mFairy.findPic(result.x+109, result.y-15, result.x+251, result.y + 110, "wancheng1.png");//已完成
                    if (result.sim > 0.9f) {
                        LtLog.e("------已完成");
                        return -1;
                    }
                } else {
                    count++;
                }
                if (count == 3 || count == 5 || count==7 || count==9) {
                    mFairy.ranSwipe(1058,467,1058,280,1000,2000); //上滑
                    LtLog.e("--------------------任务没有找到上滑");
                } else if (count > 12) {
                    bj = 0;
                    count1++;
                    if (count1 > 1) {
                        return -1;
                    }
                }
            }

            if (bj == 3) {
                break;
            }
        }
        return 3;
    }//寻找传入任务

    public int initTools(int bj, String name,int type, int bool) throws Exception {
        int num;
        String str;
        int cs = 0, cs1 = 0;
        int count = 0, count1 = 0,count2=0;
        //bool=1（必须退队进行任务）  bool=2（自己必须是队长） bool=3(跟队进行)
        if (bool == 1) {
            num = 0;
        } else if (bool == 2) {
            num = 2;
        } else if (bool == 3) {
            num = 3;
        } else {
            str = AtFairyConfig.getOption("zdjx");
            if (str.equals("")) {
                num = 0;
            } else {
                num = Integer.parseInt(str);
            }
        }


         while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("" + name + " 任务bj=" + bj));
            LtLog.e(mFairy.getLineInfo("" + count1));
            if (bj == 0) {
                init(num);
                count = 0;
                count2=0;
                cs = 0;
                bj = 1;
            }
            if (bj == 1) {
                cs++;
                if (cs > 10) {
                    bj = 0;
                }
                result = mFairy.findPic("activity.png");
                mFairy.onTap(0.8f, result,  "活动",2500);

                result = mFairy.findPic("activity1.png");
                if(result.sim>0.8f) {
                    switch (type){
                        case 1:
                            result = mFairy.findPic(106,112,317,552,"danren.png");
                            mFairy.onTap(0.8f, result,  "单人活动",1000);
                            break;
                        case 2:
                            result = mFairy.findPic(106,112,317,552,"zudui.png");
                            mFairy.onTap(0.8f, result,  "组队活动",1000);
                            break;
                        case 3:
                            result = mFairy.findPic(106,112,317,552,"bangpai.png");
                            mFairy.onTap(0.8f, result,  "帮派活动",1000);
                            break;
                    }
                    bj=2;
                }
            }

            if (bj == 2) {
                result = mFairy.findPic(323,108,844,374, name);
                LtLog.e("任务 sim:"+result.sim);
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic(result.x -50, result.y +50, result.x + 150, result.y + 230, new String[]{"canjia.png","canjia1.png"});//未完成

                    LtLog.e((result.x -50)+","+(result.y +50)+","+(result.x + 150)+","+(result.y + 230)+" 参加 sim:"+result1.sim);
//08-05 15:54:09.771 28167 28219 W yp      : 08/05 15:54:09 ypfairy-->36.1: 参加 sim:0.7299056
                    if (result1.sim > 0.8f) {
                        mFairy.onTap(0.8f, result1,  "参加",9000);
                        bj = 3;
                    }else{
                        count2++;
                        if(count2>10){
                            return -1;
                        }
                    }
                    result = mFairy.findPic(result.x -30, result.y +60, result.x + 130, result.y + 200, "wancheng.png");//已完成
                    if (result.sim > 0.9f) {
                        LtLog.e("已完成");
                        return -1;
                    }
                } else {
                    count++;
                }
                if (count == 3 || count == 5 || count == 7 || count == 9 || count == 11 || count == 13) {
                    mFairy.ranSwipe(709,200,748,450, 2, 1000,(long)2000); //上滑
                    LtLog.e("任务没有找到上滑");
                } else if (count > 15) {
                    bj = 0;
                    count1++;
                    if (count1 > 1) {
                        return -1;
                    }
                }
            }

            if (bj == 3) {
                break;
            }
        }
        return 3;
    }//寻找传入任务

    public void xl()throws Exception{
         while (mFairy.condit()) {
            result = mFairy.findPic("je.png");
            if (result.sim > 0.85f) {
                result = mFairy.findPic("xinxi.png");
                mFairy.onTap(0.85f, result,  "信息",1000);

                result = mFairy.findPic("ck.png");
                if(result.sim>0.85f) {
                    mFairy.onTap(0.85f, result, 564, 225,565,226, "查看",1000);
                    Thread.sleep(2000);
                    result = mFairy.findPic(885,180,979,633,"bu.png");
                    if(result.sim>0.85f){
                        mFairy.onTap(0.85f, result,  "补充",1000);
                        Thread.sleep(1000);
                    }else{
                        break;
                    }
                }
                result = mFairy.findPic("bu1.png");
                mFairy.onTap(0.85f, result,  "饱食补充",1000);

                result = mFairy.findPic(512, 0, 1274, 539, new String[]{"close.png", "tux.png", "cjx.png", "meet.png", "close1.png", "close2.png", "close3.png", "close4.png"});
                mFairy.onTap(0.7f, result,  "关闭窗口",1000);
                Thread.sleep(1000);
            } else {
                mFairy.onTap(1157, 45,1158,46, "点击头像",1000);
                Thread.sleep(2000);
            }


        }
    }//修理

    public int hhmsTool(int count) throws Exception {


        for(int i =0;i<10;i++) {
            result = mFairy.findPic("obtain.png");
            if (result.sim > 0.9f) {
                mFairy.onTap(0.8f, result, "获取", 1000);

                result = mFairy.findPic(407, 151, 870, 577, "obtaintj.png");
                mFairy.onTap(0.7f, result, "获取途径", 6000);

                if (count > 0) {
                    result = mFairy.findPic(514, 122, 1120, 609, "ca.png");
                    if (result.sim > 0.7f) {
                        mFairy.onTap(0.7f, result, "商人", 1000);
                        Thread.sleep(10000);

                        result = mFairy.findPic(692, 384, 1239, 690,
                                new String[]{"gm3.png", "gm4.png", "gm5.png"});
                        mFairy.onTap(0.7f, result, "购买", 1000);

                        return 0;
                    }
                } else {
                    result = mFairy.findPic(514, 122, 1120, 609, "stall.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("摆摊"));
                        mFairy.onTap(0.7f, result, "", 5000);

                        result = mFairy.findPic("no.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("没有"));
                            mFairy.onTap(1097, 64, 1098, 65, "关闭", 1000);
                            return -1;

                        } else {

                            result = mFairy.findPic("sell.png");
                            if (result.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("售出"));
                                mFairy.onTap(970, 219, 971, 220, "点", 1000);
                            }

                            result = mFairy.findPic(638, 375, 1239, 699, new String[]{"gm1.png", "jia2.png"});
                            if (result.sim > 0.7f) {
                                mFairy.onTap(0.7f, result, "购买", 1000);
                                mFairy.onTap(1097, 64, 1098, 65, "关闭", 1000);
                                return 4;

                            }
                        }
                    } else {
                        result = mFairy.findPic(514, 122, 1120, 609, "ca.png");
                        if (result.sim > 0.7f) {
                            LtLog.e(mFairy.getLineInfo("商人"));
                            mFairy.onTap(0.7f, result, "", 1000);
                            Thread.sleep(10000);

                            result = mFairy.findPic(692, 384, 1239, 690, new String[]{"gm3.png", "gm4.png", "gm5.png"});
                            mFairy.onTap(0.7f, result, "购买", 1000);
                            return 0;
                        }

                    }
                }
            }
        }
        return 0;
    }//还魂秘术方法

    public void zyTool(String img, String img2) throws Exception {
        result = mFairy.findPic(img);
        LtLog.e("***领双判断***" + result.sim);
        Thread.sleep(2000);
        if (result.sim > 0.85f) {
            for (int i = 0; i < 5; i++) {
                result = mFairy.findPic("catch5.png");
                mFairy.onTap(0.7f, result,  "展开",1000);
                Thread.sleep(1000);
                result = mFairy.findPic("gj.png");
                mFairy.onTap(0.7f, result,  "挂机图标",1000);
            }
            Thread.sleep(2000);
            result = mFairy.findPic("hdskUi.png");
            mFairy.onTap(0.9f, result,912, 138,913,139,  "领双",1000);
            mFairy.onTap(1099, 92, 1100,93,"关闭",1000);
        }
        result = mFairy.findPic(img2);
        LtLog.e("***冻结判断***" + result.sim);
        Thread.sleep(2000);
        if (result.sim > 0.8f) {
            for (int i = 0; i < 5; i++) {
                result = mFairy.findPic("catch5.png");
                mFairy.onTap(0.7f, result,  "展开",1000);
                Thread.sleep(1000);
                result = mFairy.findPic("gj.png");
                mFairy.onTap(0.7f, result,  "挂机图标",1000);
            }
            Thread.sleep(2000);
            result = mFairy.findPic("hdskUi.png");
            mFairy.onTap(0.9f, result, 619, 135, 620,136,"冻结",1000);
            mFairy.onTap(1099, 92,1100,93, "关闭",1000);
        }
    }//镇妖的方法

    public void inBattle() throws Exception {
        result = mFairy.findPic("auto1.png");
        if (result.sim > 0.7f) {
            mFairy.onTap(0.7f, result,  "自动战斗",1000);
        } else {
             while (mFairy.condit()) {
                result = mFairy.findPic("auto.png");
                if (result.sim > 0.7f) {
                    LtLog.e("战斗中....");
                    Thread.sleep(3000);
                } else {
                    break;
                }
            }
        }
    }//自动战斗

    public void guide() throws Exception {
        result = mFairy.findPic(3, 4, 1276, 717, "sheep1.png");
        if (result.sim > 0.7f) {
            for (int i = 0; i <= 19; i++) {
                result = mFairy.findPic("bjl" + i + ".png");
                mFairy.onTap(0.7f, result,"新手点击",1000 );
            }
        }
        result = mFairy.findPic(3, 4, 1276, 717, "sheep.png");
        if (result.sim > 0.7f) {
            for (int i = 0; i <= 30; i++) {
                result = mFairy.findPic("bjr" + i + ".png");
                mFairy.onTap(0.7f, result,"新手点击",1000);
            }
        }
    }//新手点击

    public void newGuide()throws Exception{
        result=mFairy.findPic(9,11,1270,709,"xs1.png");
        if(result.sim >0.85f){
            mFairy.onTap(result.x+80, result.y,result.x+81,result.y+1,"发现右手指",1000);
        }
        result=mFairy.findPic(9,11,1270,709,"xs2.png");
        if(result.sim >0.85f){
            mFairy.onTap(result.x-80, result.y,result.x-79,result.y+1,"发现左手指",1000);
        }
        result=mFairy.findPic(9,11,1270,709,"xs3.png");
        if(result.sim >0.85f){
            mFairy.onTap(result.x, result.y+80,result.x+1,result.y+81,"发现下手指",1000);
        }
        result=mFairy.findPic(9,11,1270,709,"xs4.png");
        if(result.sim >0.85f){
            mFairy.onTap(result.x, result.y-80,result.x+1,result.y-79,"发现上手指",1000);
        }

    }//新手引导（手指）

    public void matchingTarget() throws Exception {

        result = mFairy.findPic("matchingTarget.png");
        if (result.sim > 0.8f) {
            if (AtFairyConfig.getOption("pp").equals("1")) {
                mFairy.onTap(918, 348, 919,349,"带新人",1000);
            } else if (AtFairyConfig.getOption("pp").equals("5")) {
                mFairy.onTap(914, 243,915,244, "匹配5级内",1000);
            } else if (AtFairyConfig.getOption("pp").equals("10")) {
                mFairy.onTap(915, 299, 916,300,"匹配10级内",1000);
            }
            result = mFairy.findPic(519,576,1074,683,"matching1.png");
            mFairy.onTap(0.8f, result,  "自动匹配",1000);
            Thread.sleep(2000);
            for (int i = 0; i < 3; i++) {
                result = mFairy.findPic(884, 17, 1246, 358, "close.png");
                mFairy.onTap(0.7f, result,  "关闭",1000);
            }
            result = mFairy.findPic("ranksButton.png");
            mFairy.onTap(0.7f, result,  "点击队伍",1000);
            mFairy.onTap(0.7f, result,  "点击队伍",1000);
        }

    }//匹配目标

    public int organizeATeam() throws Exception {
        int a = 0;
        int cs = 0;
        int count = 0;
         while (mFairy.condit()) {
            cs++;
            if (cs > 20) {
                return -1;
            }


            result = mFairy.findPic("qwe7.png");
            if (result.sim > 0.7f) {
                cs = 0;
            }


            result = mFairy.findPic("ranksButton.png");
            mFairy.onTap(0.7f, result,  "点击队伍",1000);
            mFairy.onTap(0.7f, result,  "点击队伍",1000);


            result = mFairy.findPic("Invitation1.png");
            if (result.sim > 0.8f) {
                for (int i = 0; i < 4; i++) {
                    result = mFairy.findPic(190, 89, 1067, 582, "agree.png");
                    mFairy.onTap(0.7f, result,  "同意",1000);
                    Thread.sleep(2000);
                    if (i == 3) {
                        result = mFairy.findPic("ranks1.png");
                        mFairy.onTap(0.9f, result,  "点队伍",1000);
                    }
                }
            }


            if (AtFairyConfig.getOption("yjhh").equals("1")) {
                if (count % 9 == 0) {
                    result = mFairy.findPic("hh.png");
                    mFairy.onTap(0.9f, result,  "一键喊话",1000);
                }
            }


            result = mFairy.findPic(205, 361, 1074, 490, "zl.png");
            if (result.sim > 0.7f) {
                a++;
                if (a > 10) {
                    result = mFairy.findPic(205, 361, 1074, 490, "zl.png");
                    mFairy.onTap(0.7f, result,  "发现暂离 踢队",1000);
                    result = mFairy.findPic(226, 229, 1276, 707, "ql.png");
                    mFairy.onTap(0.7f, result,  "踢队",1000);
                    Thread.sleep(2000);
                    mFairy.onTap(763, 425,764,426, "确定",1000);
                    a = 0;
                }
            }


            result = mFairy.findPic(205, 361, 1074, 490, "lixian.png");
            if (result.sim > 0.7f) {
                mFairy.onTap(0.7f, result,  "发现离线 踢队",1000);
                result = mFairy.findPic(226, 229, 1276, 707, "ql.png");
                mFairy.onTap(0.7f, result,  "踢队",1000);
                Thread.sleep(2000);
                mFairy.onTap(763, 425, 764,426,"确定",1000);

            }


            if (count % 8 == 0) {
                result = mFairy.findPic("duizhang.png");
                mFairy.onTap(0.8f, result,  "召回",1000);
                Thread.sleep(5000);
            }


            if (AtFairyConfig.getOption("three").equals("1") ){

                result = mFairy.findPic("xian1.png");
                LtLog.e("三人满不满？" + result.sim);
                if (result.sim < 0.7f) {
                    result = mFairy.findPic("mu.png");
                    if (result.sim > 0.8f) {
                        LtLog.e("人员满了,开始执行任务");
                        return 1;

                    }
                }
            } else {
                result = mFairy.findPic("xian2.png");
                LtLog.e("五人满不满？" + result.sim);
                if (result.sim < 0.7f) {
                    result = mFairy.findPic("mu.png");
                    if (result.sim > 0.8f) {
                        LtLog.e("人员满了,开始执行任务");
                        return 1;

                    }
                }


            }
            result = mFairy.findPic("matching.png");
            mFairy.onTap(0.9f, result,  "开始匹配",1000);
        }

        return -1;
    }//组队方法

    public int see(String img) throws Exception {
         while (mFairy.condit()) {

            result = mFairy.findPic(512, 0, 1274, 539, new String[]{"close.png", "tux.png", "cjx.png", "meet.png", "close1.png", "close2.png", "close3.png", "close4.png"});
            mFairy.onTap(0.7f, result,  "关闭窗口",1000);

            result = mFairy.findPic("auto.png");
            if (result.sim > 0.7f) {
                for (int i = 0; i < 3; i++) {
                    result = mFairy.findPic("rightTip.png");
                    mFairy.onTap(0.8f, result,  "Tip",1000);

                }

            }

            result = mFairy.findPic("ranksButton.png");
            if (result.sim > 0.6f) {
                mFairy.onTap(0.6f, result,  "点击队伍",1000);
                mFairy.onTap(0.6f, result,  "点击队伍",1000);
                Thread.sleep(2000);

                result = mFairy.findPic(img);
                if (result.sim > 0.8f) {
                    return 3;
                } else {
                    return 1;
                }
            }
        }
        return -1;
    }//跟队副本init查看

    public int gg(String img, int type) throws Exception {
        int count2 = 0;

         while (mFairy.condit()){
            result = mFairy.findPic(512, 0, 1274, 539, "close.png");
            mFairy.onTap(0.7f, result,  "关闭",1000);
            result = mFairy.findPic("activity.png");
            if(result.sim > 0.7f){
                break;
            }
        }

         while (mFairy.condit()) {
            result = mFairy.findPic("activity.png");
            mFairy.onTap(0.8f, result,  "活动",1000);
            Thread.sleep(2000);

            result = mFairy.findPic("activity1.png");
            if(result.sim>0.8f) {
                switch (type){
                    case 1:
                        result = mFairy.findPic(106,112,317,552,"danren.png");
                        mFairy.onTap(0.8f, result,  "单人活动",1000);
                        break;
                    case 2:
                        result = mFairy.findPic(106,112,317,552,"zudui.png");
                        mFairy.onTap(0.8f, result,  "组队活动",1000);
                        break;
                    case 3:
                        result = mFairy.findPic(106,112,317,552,"bangpai.png");
                        mFairy.onTap(0.8f, result,  "帮派活动",1000);
                        break;
                }
            }

            result = mFairy.findPic(320,103,821,433, img);
            LtLog.e("-----选择任务的位置");
            if (result.sim > 0.8f) {
                result1 = mFairy.findPic(result.x -30, result.y +60, result.x + 130, result.y + 200, "canjia.png");//未完成
                if (result1.sim > 0.8f) {
                    mFairy.onTap(0.8f, result1,  "参加",6000);
                    return 3;
                }
            }else{
                count2++;
            }

            if (count2 == 3 || count2 == 6) {
                mFairy.ranSwipe(711, 182, 729, 534, 2, 1000,(long)2000); //上滑
                LtLog.e("--------------------任务没有找到上滑");
            } else if (count2 > 12) {
                return 0;
            }
        }
        return -1;
    }//gg

    public void vtt() throws Exception {

        LtLog.e("----------开始查看队伍");

        for (int i = 0; i < 3; i++) {
            result = mFairy.findPic("rightTip.png");
            mFairy.onTap(0.8f, result,  "Tip",1000);

            result = mFairy.findPic("task1.png");
            mFairy.onTap(0.7f,result,  1064, 125, 1065,126,"任务",1000);
        }


        result = mFairy.findPic("ranksButton.png");
        mFairy.onTap(0.7f, result,  "点击队伍",1000);
        mFairy.onTap(0.7f, result,  "点击队伍",1000);

        result = mFairy.findPic("Invitation1.png");
        if (result.sim > 0.8f) {
            for (int i = 0; i < 4; i++) {
                result = mFairy.findPic(190, 89, 1067, 582, "agree.png");
                mFairy.onTap(0.7f, result,  "同意",1000);
                Thread.sleep(2000);
                if (i == 3) {
                    result = mFairy.findPic("ranks1.png");
                    mFairy.onTap(0.9f, result,  "点队伍",1000);
                }
            }
        }

        result = mFairy.findPic("mu.png");
        if (result.sim > 0.7f) {
            result = mFairy.findPic("xian2.png");
            LtLog.e("-----满不满？" + result.sim);
            if (result.sim > 0.6f) {
                LtLog.e("人员未满");
                result = mFairy.findPic("matching.png");
                mFairy.onTap(0.8f, result,  "开始匹配",1000);
            }
            result = mFairy.findPic(205, 361, 1074, 490, "lixian.png");
            if (result.sim > 0.7f) {
                mFairy.onTap(0.7f, result,  "发现离线 踢队",1000);
                result = mFairy.findPic(226, 229, 1276, 707, "ql.png");
                mFairy.onTap(0.7f, result,  "踢队",1000);
                Thread.sleep(2000);
                mFairy.onTap(763, 425,764,426, "确定",1000);
            }

            mFairy.onTap(1058, 64,1059,65, "关闭",1000);
        }

         while (mFairy.condit()) {
            result = mFairy.findPic("auto.png");
            if (result.sim > 0.7f) {
                result = mFairy.findPic(512, 0, 1274, 539, "close.png");
                mFairy.onTap(0.7f, result,  "关闭",1000);

                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.7f,result,  1064, 125,1065,126, "任务",1000);

                result = mFairy.findPic("qwe5.png");
                mFairy.onTap(0.8f, result, 762, 426,763,427, "确定",1000);
                Thread.sleep(2000);
            } else {
                break;
            }
        }

    }//查看队伍

    public void mapButton() throws Exception {
         while (mFairy.condit()) {
            LtLog.e("-----   有任务要执行，找地图");
            result = mFairy.findPic("mapButton.png");
            if(result.sim>0.7f) {
                LtLog.e("地图sim  " + result.sim);
                mFairy.onTap(499,450,500,451,"点",1000);
                mFairy.onTap(499,450,500,451,"点",1000);
                mFairy.onTap(0.7f, result,  "地图",1000);
            }

            result = mFairy.findPic("jingcheng.png");
            mFairy.onTap(0.7f, result,  "京城",1000);

            result = mFairy.findPic(512, 0, 1274, 539, "close.png");
            mFairy.onTap(0.7f, result,  "关闭",1000);

            result = mFairy.findPic("qwe9.png");
            if (result.sim > 0.8f) {
                break;
            }
        }

    }//点击地图

    public void hangUpTool99() throws Exception {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            mFairy.ranSwipe(254, 176, 284, 600, 0, 1000,(long)100); //下滑
        }
        Thread.sleep(3000);
         while (mFairy.condit()) {
            result = mFairy.findPic(237, 109, 379, 675, "chaos99.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result,  "推荐挂机地图",1000);
                break;
            } else {
                count++;
            }

            if (count > 5) {
                mFairy.ranSwipe(255, 213, 295, 541, 2, 1000,(long)3000); //上滑
                count = 0;

            }
        }
    }//挂机方法99

    public void hangUpTool(int num, int type) throws Exception {

        if (num <= 6) {
            for (int i = 0; i < 3; i++) {
                mFairy.ranSwipe(254, 176, 284, 600, 0, 1000,(long)100); //下滑
            }
            Thread.sleep(3000);
            switch (num) {
                case 1:
                    mFairy.onTap(300, 181, 301,182,"混沌" + num + "层",1000);
                    break;
                case 2:
                    mFairy.onTap(300, 288,301,289, "混沌" + num + "层",1000);
                    break;
                case 3:
                    mFairy.onTap(300, 375, 301,376,"混沌" + num + "层",1000);
                    break;
                case 4:
                    mFairy.onTap(300, 473,301,474, "混沌" + num + "层",1000);
                    break;
                case 5:
                    mFairy.onTap(300, 568, 301,569,"混沌" + num + "层",1000);
                    break;
                case 6:
                    mFairy.onTap(300, 646, 301,647,"混沌" + num + "层",1000);
                    break;
                default:
                    break;
            }
        }
        if (num == 7 || num==8) {
            for (int i = 0; i < 2; i++) {
                mFairy.ranSwipe(254, 176, 284, 600, 0, 1000,(long)100); //下滑
            }
            Thread.sleep(3000);
            mFairy.ranSwipe(254, 176, 284, 600, 2, 1000,(long)3000); //上滑
            for (int i = 0; i < 3; i++) {
                result = mFairy.findPic(237, 109, 379, 675, "chaos"+num+".png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result,  "混沌" + num + "层",1000);
                    break;
                }
            }

        }


        if (num >= 9 && num < 99) {
            for (int i = 0; i < 3; i++) {
                mFairy.ranSwipe(254, 176, 284, 600, 2, 1000,(long)100); //下滑
            }
            Thread.sleep(3000);
            switch (num) {
                case 9:
                    mFairy.onTap(300, 144, 301,145,"混沌" + num + "层",1000);
                    break;
                case 10:
                    mFairy.onTap(300, 233, 301,234,"混沌" + num + "层",1000);
                    break;
                case 11:
                    mFairy.onTap(300, 326, 301,327,"混沌" + num + "层",1000);
                    break;
                case 12:
                    mFairy.onTap(300, 420, 301,421,"混沌" + num + "层",1000);
                    break;
                case 13:
                    mFairy.onTap(300, 518, 301,519,"混沌" + num + "层",1000);
                    break;
                case 14:
                    mFairy.onTap(300, 609, 301,610,"混沌" + num + "层",1000);
                    break;
                default:
                    break;
            }
        }

        if (num == 99) {

            hangUpTool99();

        }

        for (int i = 0; i < 2; i++) {
            if (type == 1) {
                result = mFairy.findPic("off.png");
                mFairy.onTap(0.9f, result,  "点开自动战斗",1000);
            } else {
                result = mFairy.findPic("catch1.png");
                mFairy.onTap(0.9f, result,  "关闭自动战斗",1000);

            }
            result = mFairy.findPic("on.png");
            mFairy.onTap(0.9f, result,  "关闭双倍",1000);
        }

        result = mFairy.findPic("patrol.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result,  "开始巡逻",1000);
            Thread.sleep(15000);
        }


    }//挂机方法

    public void copyTools(int type, int p) throws Exception {
        switch (type) {
            case 1:
                mFairy.onTap(1122, 154,1123,155, "普通副本",1000);
                break;
            case 2:
                mFairy.onTap(1122, 255,1123,256,  "精英副本",1000);
                break;
            case 3:
                mFairy.onTap(1122, 369, 1123,370, "精英副本",1000);
                break;
            default:
                break;
        }

        Thread.sleep(3000);
        switch (p) {
            case 1:
                mFairy.onTap(304, 280,305,281, "黑石问情",1000);
                break;
            case 2:
                mFairy.onTap(536, 280,537,281, "古窟惊魂",1000);
                break;
            case 3:
                mFairy.onTap(780, 280, 781,281,"修罗浩劫",1000);
                break;
            case 4:
                mFairy.onTap(297, 280, 298,281,"正魔大战",1000);
                break;
            case 5:
                mFairy.onTap(540, 280, 541,281,"兽神灭世",1000);
                break;
            default:
                break;

        }


    }//Tools

    public int festivalTools(String img) throws Exception {

        result = mFairy.findPic(160, 198, 1079, 527, img);
        LtLog.e("-----选择任务的位置" + result.x + "    " + result.y);
        if (result.sim > 0.8f) {
            result1 = mFairy.findPic(result.x + 200, result.y - 17, result.x + 326, result.y + 74, "pi1.png");//未完成
            if (result1.sim > 0.8f) {
                mFairy.onTap(0.8f, result1,  "参加",6000);
            }
            result = mFairy.findPic(result.x + 200, result.y - 17, result.x + 326, result.y + 74, "completed1.png");//已完成
            if (result.sim > 0.9f) {
                LtLog.e("------已完成");
                return -1;
            }
        }
        return 3;
    }//gg


}
