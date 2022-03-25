package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

public class GamePublicFuntion {

    public AtFairyImpl mFairy;
    public FindResult result;
    public FindResult result1;
    public FindResult result2;
    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
    }

    /*
           公共方法
        */
    public void close() throws Exception {
        result = mFairy.findPic(1115, 5, 1277, 155,"close1.png");
        mFairy.onTap(0.9f, result, "关闭", 1000);
    }//关闭

    public void init() throws Exception {
        while (true) {
            close();
            battleEnd();
            chat();
            result = mFairy.findPic("activity.png");
            if (result.sim > 0.85f) {
                LtLog.e("活动");
                break;
            }else{
                 mFairy.onTap(1235,43,1236,44,"点击头像",2000);
            }

        }
    }//初始化

    public void chat()throws Exception{
        for(int i =0;i<10;i++) {
            result =  mFairy.findPic(new String[]{"chat.png", "chat1.png"});
            if (result.sim > 0.85f) {
                Thread.sleep(1000);
                 mFairy.onTap(0.85f,result,"聊天",1500);

                i=0;
            }
        }
    }//聊天

    public int activity(int num,int type,String img)throws Exception{
        int o=0;
        int count=0;
        int err = 0;
        while (true) {
            result = mFairy.findPic("activity.png");
             mFairy.onTap(0.85f, result, "活动", 2000);

            result = mFairy.findPic("UIactivity.png");
            if (result.sim > 0.85f) {
                if(o==0){
                    switch (num){
                        case 1:
                             mFairy.onTap(1163,169,1164,170,"日常",2000);
                            break;
                        case 2:
                             mFairy.onTap(1163,245,1164,246,"限时",2000);
                            break;
                    }
                    activityType(type);
                    o=1;
                }

                result1 = mFairy.findPic(76,188,1096,686,img);
                if(result1.sim>0.85f){
                    result = mFairy.findPic(result1.x+211,result1.y-7,
                            result1.x+365,result1.y+77,"pp.png");
                    if(result.sim>0.85f){
                         mFairy.onTap(0.85f,result,"前往",6000);
                        return 0;
                    }else{
                        count++;
                        result = mFairy.findPic(result1.x+211,result1.y-7,
                                result1.x+365,result1.y+77,"activityEnd.png");
                        if(result.sim >0.85f){
                            LtLog.e("任务已经完成,End!");
                            return 1;
                        }

                    }

                }else{
                    count++;
                }

                if(count==2 || count==4 || count==6 || count==8){
                    mFairy.ranSwipe(644, 576, 644, 450, 1500, 1000);
                    Thread.sleep(2000);
                }
            }else{
                chat();
                close();
                err++;
            }

            if(count>10){
                LtLog.e("任务没有找到,End!");
                return 1;
            }
            if(err>10){
                LtLog.e("活动异常,End!");
                return 2;
            }

        }
    }//活动

    public void activityType(int type)throws Exception{
        switch (type){
            case 1:
                 mFairy.onTap(105,155,106,156,"全部",2000);
                break;
            case 2:
                 mFairy.onTap(211,155,212,156,"高收益",2000);
                break;
            case 3:
                 mFairy.onTap(327,155,329,156,"经验",2000);
                break;
            case 4:
                 mFairy.onTap(442,155,443,156,"金钱",2000);
                break;
            case 5:
                 mFairy.onTap(555,155,556,156,"材料",2000);
                break;
            case 6:
                 mFairy.onTap(673,155,674,156,"道具",2000);
                break;
            case 7:
                 mFairy.onTap(783,155,784,156,"其他",2000);
                break;

        }
    }//选择活动类型

    public void rank()throws Exception{
        init();
        while (true){
            result =  mFairy.findPic( new String[]{"ranks.png", "ranks1.png"});
             mFairy.onTap(0.85f, result, "队伍", 3000);

            result = mFairy.findPic(711,588,1019,707,"ranks2.png");
            if(result.sim >0.85f) {
                 mFairy.onTap(0.85f, result, "创建队伍", 3000);
                break;
            }
            result = mFairy.findPic(713,595,1229,713,"ranks4.png");
            if(result.sim >0.85f) {
                result = mFairy.findPic(711,588,1019,707,"ranks3.png");
                 mFairy.onTap(0.85f, result, "离开队伍", 2000);
                 mFairy.onTap(730,401,731,402,"确定离开队伍",1000);
            }else{
                result = mFairy.findPic(911,597,1205,699,"ranks5.png");
                if(result.sim >0.85f) {
                    break;
                }
            }
        }
    }//创建队伍

    public void rankT()throws Exception{
        init();
        while (true){
            result =  mFairy.findPic(new String[]{
                    "ranks.png", "ranks1.png"});
             mFairy.onTap(0.85f, result, "队伍", 3000);

            result = mFairy.findPic(711,588,1019,707,"ranks2.png");
            if(result.sim >0.85f) {
                break;
            }

            result = mFairy.findPic(711,588,1019,707,"ranks3.png");
            if(result.sim>0.85f) {
                 mFairy.onTap(0.85f, result, "离开队伍", 2000);
                 mFairy.onTap(730, 401,731,402, "确定离开队伍", 1000);
                break;
            }


        }
    }//退出队伍

    public void battleEnd()throws Exception{
        int count=0;
        result = mFairy.findPic("ba.png");
        if(result.sim >0.85f){
            while (true){
                LtLog.e("停止战斗...");
                result = mFairy.findPic("packages.png");
                if(result.sim >0.85f){
                    Thread.sleep(1000);
                    count++;
                    if(count>1){
                        return;
                    }
                    for(int i = 0;i<2;i++) {
                         mFairy.onTap(576, 329,577,330, "战斗已停止!", 500);
                        if(i==2){
                            Thread.sleep(5000);
                        }
                    }
                }else{
                    count=0;
                }
            }
        }



    }//战斗结束

    public int stopJudge()throws Exception{
        FindResult result = mFairy.findPic("packages.png");
        if (result.sim > 0.85f) {
            int colorNum = mFairy.getColorNum(93, 43, 152, 64, "254,238,0", 1);
            for(int i =0;i<=11;i++){
                Thread.sleep(1000);
                int NewColorNum =mFairy.getColorNum(93, 43, 152, 64, "254,238,0", 1);
                if(NewColorNum!=colorNum){
                    return 0;
                }
                if(i==10){
                    return 1;
                }
            }
            result = mFairy.findPic("packages.png");
            if(result.sim < 0.85f){
                return 0;
            }
        }
        return 0;
    }//暂停判断

    public int seekTask(String img)throws Exception{
        int num=0;
        while (true) {
            result = mFairy.findPic(1043,175,1213,471,"sw7.png");
            if (result.sim > 0.75f) {
                 mFairy.onTap(0.85f, result, "任务", 6000);
                return 0;
            }
            result = mFairy.findPic(1043,175,1213,471,img);
            if (result.sim > 0.85f) {
                 mFairy.onTap(0.85f, result, "任务", 6000);
                return 0;
            } else {
                num++;
                if (num == 3 || num == 6 || num == 9 || num == 12) {
                    mFairy.ranSwipe(1133, 199, 1188, 416, 2, 1000);
                    LtLog.e("找任务上滑");
                    Thread.sleep(3000);
                } else if (num > 15) {
                    return 1;
                }
            }
        }

    }//寻找任务

}
