package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class GamePublicFuntion {
    public AtFairyImpl mFairy;
    public FindResult result;
    public long time = System.currentTimeMillis();
    public static boolean activity_bool = false;

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
    }

    public int judgeStop = 0;

    public Boolean judgeStop(int m, boolean bool) throws Exception {
        long num = mFairy.mMatTime(1136, 118, 61, 17, 0.98f);
        if (num <= 1 && bool) {
            judgeStop++;
            if (judgeStop >= 50) {
                judgeStop = 0;
                return true;
            }
        }

        if (num > 1) {
            LtLog.e(mFairy.getLineInfo("发呆计次" + num));
        }

        if (num >= m) {
            judgeStop = 0;
            return true;
        } else {
            TaskContent.err = 0;
        }
        return false;
    }//发呆判断

    public boolean mainScene() throws Exception {
        result = mFairy.findPic("package.png");
        if (result.sim > 0.8f) {
            return true;
        }

        result = mFairy.findPic("main.png");
        if (result.sim > 0.8f) {
            return true;
        }
        return false;
    }

    public boolean fuben() throws Exception {
        result = mFairy.findPic(882,4,1276,200,"fb1.png");
        if (result.sim > 0.8f) {
            return true;
        }

        return false;
    }

    public void task() throws Exception {
        result = mFairy.findPic(2,119,48,403,new String[]{"task.png"/*,"task1.png"*/});
        mFairy.onTap(0.9f, result, "task", 500);

    }//点击任务

    public void jie() throws Exception {
        result = mFairy.findPic(804,441,873,500,"jie.png");
        mFairy.onTap(0.85f, result, "解", 500);
    }//解

    public void clickRanks()throws Exception{
        result = mFairy.findPic(2,119,48,403,new String[]{"rank1.png","rank2.png"});
        mFairy.onTap(0.9f, result, "点击队伍", 1500);
    }//

    public void use() throws Exception {
        result = mFairy.findPic(934,338,1080,412,"use.png");
        mFairy.onTap(0.85f, result, "u使用", 500);

    }//使用

    public void close(int num) throws Exception {

        result = mFairy.findPic2("loginGame3.png");
        if(result.sim<0.8f){
            for (int i = 0; i < num; i++) {
                result = mFairy.findPic("close1.png");
                mFairy.onTap(0.8f, result, "close1.png", 500);

                result = mFairy.findPic(754,37,914,175,"close2.png");
                mFairy.onTap(0.75f, result, "close2.png", 500);

                result = mFairy.findPic("close3.png");
                mFairy.onTap(0.8f, result, "close3.png", 500);

                result = mFairy.findPic(925,7,1265,315,"close4.png");
                mFairy.onTap(0.8f, result, "close4.png", 500);

                result = mFairy.findPic("close5.png");
                mFairy.onTap(0.8f, result, "close5.png", 500);

                result = mFairy.findPic(800,11,1267,298,"close6.png");
                mFairy.onTap(0.8f, result, "close6.png", 500);

                result = mFairy.findPic("close7.png");
                mFairy.onTap(0.8f, result, "close7.png", 500);

                result = mFairy.findPic("close9.png");
                mFairy.onTap(0.8f, result, "close9.png", 500);

                result = mFairy.findPic("jia1.png");
                mFairy.onTap(0.8f, result, "close10.png", 500);

                result = mFairy.findPic("close10.png");
                mFairy.onTap(0.8f, result, "close10.png", 500);

                result = mFairy.findPic(860,8,1275,238,"close10.png");
                mFairy.onTap(0.8f, result, "close10.png", 500);

            }
        }
    }

    public void esc(int num)throws Exception{
        for (int i = 0; i < num; i++) {
            result = mFairy.findPic(8,3,90,51,"esc.png");
            mFairy.onTap(0.8f, result, "esc.png", 500);
        }
    }//返回

    public void likai()throws Exception{

        for (int i =0;i<3;i++){
            Thread.sleep(300);
            result = mFairy.findPic(1034,72,1159,198,"esc1.png");
            if(result.sim>0.8f){
                mFairy.onTap(0.8f,result,"离开岛屿",1000);
                mFairy.onTap(787,418,833,435,"",1500);
            }

            result = mFairy.findPic(882,4,1276,200,"fb1.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f,result,"离开副本",1000);
                mFairy.onTap(789,409,844,438,"",1500);
            }

        }
    }

    public void init(boolean activity_click) throws Exception {
        int err=0;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("init >>>>>"));

            if (activity_click) {

                FindResult h = mFairy.findPic(697, 12, 1106, 159, "activity.png");
                if (h.sim > 0.75f) {
                    stop_battle();
                    likai();
                    mFairy.onTap(0.75f, h, "活动", 2000);
                    activity_bool = false;
                }

                result = mFairy.findPic("activity1.png");
                if (result.sim > 0.8f) {
                    activity_bool = true;
                    return;
                }
            }

            result = mFairy.findPic(941, 486, 1256, 706, "fh.png");
            mFairy.onTap(0.8f, result, "安全区复活", 2000);

            result = mFairy.findPic("zoom1.png");
            mFairy.onTap(0.75f, result, "zoom", 1000);

            result = mFairy.findPic("err1.png");
            mFairy.onTap(0.75f, result,793,416,841,484, "聊天-设置", 1000);

            result = mFairy.findPic("err2.png");
            mFairy.onTap(0.75f, result, "关闭聊天界面", 1000);

            result = mFairy.findPic(623,9,1124,153,"jiaoyi.png");
            mFairy.onTap(0.75f, result,1086,28,1105,48, "发现交易图标-切回", 1000);


            close(1);
            esc(1);

            result = mFairy.findPic(new String[]{"rank5.png", "rank8.png"});
            mFairy.onTap(0.8f,result,756,290,799,324,"关闭组队界面",500);

            result = cancel();
            mFairy.onTap(0.8f,result,"取消",500);

            if (mainScene()) {
                if (activity_click == false) {
                    stop_battle();
                    likai();
                    return;
                }
            } else {
                if(err>2) {
                    mFairy.onTap(814, 644, 842, 675, "err", 500);
                    err=0;
                }
            }
        }


    }

    public void stop_battle()throws Exception{
        for(int i =0;i<3;i++){
            Thread.sleep(300);
            result = mFairy.findPic(446,472,706,614,"auto1.png");
            mFairy.onTap(0.8f,result,764,608,773,620,"停止战斗",1500);
        }
    }//停止战斗

    public void activity_type(int type) throws Exception {
        switch (type) {
            case 1:
                mFairy.onTap(70, 111, 104, 147, "必做", 200);
                mFairy.onTap(70, 111, 104, 147, "必做", 500);
                break;
            case 2:
                mFairy.onTap(138, 320, 178, 355, "选做", 200);
                mFairy.onTap(138, 320, 178, 355, "选做", 500);
                mFairy.onTap(379,108,422,121,"全部",500);
                break;
            case 3:
                mFairy.onTap(71, 514, 96, 543, "挑战", 200);
                mFairy.onTap(71, 514, 96, 543, "挑战", 500);
                break;
        }

        result = mFairy.findPic(1190,81,1267,164,"activity5.png");
        mFairy.onTap(0.85f,result,"切换图标",500);
    }

    public void auto_battle()throws Exception{
        result = mFairy.findPic("auto.png");
        mFairy.onTap(0.92f,result,"自动战斗 sim "+result.sim,500);


    }

    public FindResult cancel()throws Exception{
        return mFairy.findPic(216,325,675,699,new String[]{"qx.png"});

    }//取消

    public int ranks_num()throws Exception{

        result = mFairy.findPic(174,207,416,276,"rank13.png");
        if(result.sim>0.8f){
            return 1;
        }
        result = mFairy.findPic(166,299,419,388,"rank13.png");
        if(result.sim>0.8f){
            return 2;
        }
        result = mFairy.findPic(184,422,404,493,"rank13.png");
        if(result.sim>0.8f){
            return 3;
        }
        result = mFairy.findPic(204,531,408,593,"rank13.png");
        if(result.sim>0.8f){
            return 4;
        }

        return 5;
    }//队伍人数
}
