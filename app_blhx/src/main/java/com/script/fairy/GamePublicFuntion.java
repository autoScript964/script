package com.script.fairy;


import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

/**
 * Created by user on 2019/2/15.
 */

public class GamePublicFuntion {
    public AtFairyImpl mFairy;
    public FindResult result;
    public long time = System.currentTimeMillis();

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
    }
    int t =0;

    private int zhang=7;

    public  boolean timeJudge(long s) {
        if(t==0){
            t=1;
            return true;
        }

        if (System.currentTimeMillis() - time > s) {
            time = System.currentTimeMillis();
            return true;
        }
        return false;
    }//time判断

    public int getNumber()throws Exception{
        for(int i=1;i<=zhang;i++) {
            result = mFairy.findPic(92,32,559,142,"zhang" + i + ".png");
            if (result.sim > 0.8f) {
                return i;
            }
        }
        return -1;
    }//获取当前章节

    public void last_chapter(int x)throws Exception{
        for(int i=0;i<x;i++) {
            mFairy.onTap(44,360,61,383,"上一章",1200);
        }

    }

    public void next_chapter(int x)throws Exception{
        for(int i=0;i<x;i++) {
            mFairy.onTap(1214,365,1230,381,"下一章",1200);
        }
    }

    public void click_chapter(int x, int y) throws Exception {

        if (x == 1) {
            if (y == 1) {
                mFairy.onTap(125,480,150,509,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 2) {
                mFairy.onTap(430,295,459,323,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 3) {
                mFairy.onTap(636,555,663,576,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 4) {
                mFairy.onTap(760,203,788,225,"选择第" + x + "章 - 第" + y + "节",2000);
            }
        }
        if (x == 2) {
            if (y == 1) {
                mFairy.onTap(786,478,815,506,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 2) {
                mFairy.onTap(721,210,749,236,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 3) {
                mFairy.onTap(263,294,294,319,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 4) {
                mFairy.onTap(357,572,392,594,"选择第" + x + "章 - 第" + y + "节",2000);
            }
        }
        if (x == 3) {
            if (y == 1) {
                mFairy.onTap(378,243,402,268,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 2) {
                mFairy.onTap(201,521,231,548,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 3) {
                mFairy.onTap(758,160,790,180,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 4) {
                mFairy.onTap(587,386,616,408,"选择第" + x + "章 - 第" + y + "节",2000);
            }
        }
        if (x == 4) {
            if (y == 1) {
                mFairy.onTap(214,325,236,352,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 2) {
                mFairy.onTap(391,491,424,522,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 3) {
                mFairy.onTap(799,569,827,596,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 4) {
                mFairy.onTap(751,313,785,339,"选择第" + x + "章 - 第" + y + "节",2000);
            }
        }
        if (x == 5) {
            if (y == 1) {
                mFairy.onTap(211,389,247,416,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 2) {
                mFairy.onTap(830,558,864,597,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 3) {
                mFairy.onTap(714,390,744,416,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 4) {
                mFairy.onTap(567,239,600,263,"选择第" + x + "章 - 第" + y + "节",2000);
            }
        }
        if (x == 6) {
            if (y == 1) {
                mFairy.onTap(890,519,916,551,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 2) {
                mFairy.onTap(678,365,709,396,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 3) {
                mFairy.onTap(395,240,433,260,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 4) {
                mFairy.onTap(268,452,304,475,"选择第" + x + "章 - 第" + y + "节",2000);
            }
        }
        if (x == 7) {
            if (y == 1) {
                mFairy.onTap(179,503,218,536,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 2) {
                mFairy.onTap(431,202,466,235,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            /*if (y == 3) {
                mFairy.onTap(567,239,600,263,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 4) {
                mFairy.onTap(567,239,600,263,"选择第" + x + "章 - 第" + y + "节",2000);
            }*/
        }

        if (x == 8) {
           /* if (y == 1) {
                mFairy.onTap(567,239,600,263,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 2) {
                mFairy.onTap(567,239,600,263,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 3) {
                mFairy.onTap(567,239,600,263,"选择第" + x + "章 - 第" + y + "节",2000);
            }
            if (y == 4) {
                mFairy.onTap(567,239,600,263,"选择第" + x + "章 - 第" + y + "节",2000);
            }*/
        }
    }

    public void EndTask() throws Exception {
        switch (TaskMain.FINISH) {
            case 0:
                LtLog.e(mFairy.getLineInfo("勾选任务已经全部完成,End!" + AtFairyConfig.getTaskID()));
                mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
                break;
            case 1301:
                LtLog.e(mFairy.getLineInfo("您的背包没有足够的空间，已给您停止当前任务,End!" + AtFairyConfig.getTaskID()));
                mFairy.finish(AtFairyConfig.getTaskID(), TaskMain.FINISH);
                break;
        }
        mFairy.sleep(3000);
    }
}
