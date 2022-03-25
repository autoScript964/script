package com.script.fairy;

import android.content.Context;
import android.renderscript.Matrix2f;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ThemedSpinnerAdapter;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.ScreenInfo;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.utils.RemoteInput;
import com.script.framework.AtFairyImpl;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.features2d.Feature2D;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

import javax.xml.transform.sax.TemplatesHandler;

public class SingleTask {
    public GamePublicFuntion gamePublicFuntion;
    public AtFairyImpl mFairy;
    private FindResult result;
    public static int qh=0;
    private FindResult result1;

    public static int FT=0;
    int hour;
    int minute;
    int week;

    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        FT=0;
    }

    public void novice() throws Exception {
        int count = 5, count1 = 0, count2 = 0;
         while (mFairy.condit()) {
            LtLog.e("-----新手引导");
            if (count > 2) {
                for (int i = 0; i < 3; i++) {
                    result = mFairy.findPic("task1.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("队伍"));
                        mFairy.onTap(1068, 129,1069,130, "点任务",1000);
                    }
                    result = mFairy.findPic("quxiao1.png");
                    if (result.sim > 0.9f) {
                      mFairy.onTap(0.9f, result, "队伍取消",1000);
                    }

                    result = mFairy.findPic(512, 0, 1274, 539,
                            new String[]{"close.png", "tux.png", "cjx.png", "meet.png", "close1.png", "close2.png"});
                    mFairy.onTap(0.7f, result,  "关闭窗口",1000);

                    result = mFairy.findPic("quxiao.png");
                    mFairy.onTap(0.85f, result, "取消",1000);

                    result = mFairy.findPic("extend1.png");
                    mFairy.onTap(0.9f, result, "",1000);

                    result = mFairy.findPic("extend2.png");
                    mFairy.onTap(0.9f, result, "",1000);

                    result = mFairy.findPic("got.png");
                    mFairy.onTap(0.8f, result, "",1000);

                    result = mFairy.findPic("got1.png");
                    mFairy.onTap(0.8f, result, "",1000);

                    result = mFairy.findPic("rightTip.png");
                    mFairy.onTap(0.7f, result, "",1000);
                }
                count = 0;
            }

            result = mFairy.findPic(709, 5, 1270, 391, "close.png");
            mFairy.onTap(0.7f, result, "关闭",1000);


             while (mFairy.condit()) {
                result = mFairy.findPic("click.png");
                if (result.sim > 0.9f) {
                    mFairy.onTap(0.9f, result, "点击任意屏幕继续",1000);
                    count2 = 0;
                } else {
                    break;
                }
            }
             while (mFairy.condit()) {
                result = mFairy.findPic("click1.png");
                if (result.sim > 0.9f) {
                    mFairy.onTap(0.9f, result, "点击任意屏幕跳过",1000);
                    count2 = 0;
                } else {
                    break;
                }
            }


            result = mFairy.findPic(1027, 159, 1101, 567, "zhixian.png");
            if (result.sim > 0.7f) {
                mFairy.onTap(0.7f, result, "支线",1000);
                count = 0;
                count1 = 0;
                Thread.sleep(2000);

            } else {
                result = mFairy.findPic(1027, 159, 1101, 567, "zhuxian.png");
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic(1024,152,1280,498,"end.png");
                    if (result1.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("主线已经结束，请先提升等级再进行"));
                        break;
                    } else {
                        count = 0;
                        count1 = 0;
                        LtLog.e(mFairy.getLineInfo( "主线"));
                        mFairy.onTap(0.7f, result, "",1000);
                        count2++;
                        Thread.sleep(2000);
                    }
                } else {
                    count++;
                }
            }

            result = mFairy.findPic(780, 357, 1018, 682, "thread.png");
            mFairy.onTap(0.7f, result, "主线",1000);

            result = mFairy.findPic(780, 357, 1018, 682, "branchLine.png");
            mFairy.onTap(0.7f, result, "支线",1000);


            result = mFairy.findPic(780, 357, 1018, 682, "battle.png");
            if (result.sim > 0.7f) {
                mFairy.onTap(0.7f, result, "战斗",1000);
                count2 = 0;
                Thread.sleep(5000);
            }
            result = mFairy.findPic(891, 411, 1261, 699, "use.png");
            if (result.sim > 0.7f) {
                mFairy.onTap(0.7f, result, "使用",1000);
                count2 = 0;
                Thread.sleep(2000);
            }
            result = mFairy.findPic(891, 411, 1261, 699, "equipment.png");
            mFairy.onTap(0.7f, result, "装备",1000);

            result = mFairy.findPic("ljty.png");
            if (result.sim > 0.9f) {
                mFairy.onTap(0.9f, result, "浪迹天涯",1000);
                Thread.sleep(2000);
                mFairy.onTap(766, 429,767,430, "点击确定",1000);
                count2 = 0;
            }


            result = mFairy.findPic("1.png");
            mFairy.onTap(0.9f, result, "我玩过",1000);

            result = mFairy.findPic("2.png");
            if (result.sim > 0.9f) {
                LtLog.e(mFairy.getLineInfo("获得宠物"));
                mFairy.onTap(640, 547,641,548,"确定",1000);
            }
            gamePublicFuntion.newGuide();
            gamePublicFuntion.guide();

            count1++;
            if (count1 == 10 || count1 == 12 || count1 == 14 || count1 == 16 || count1 == 18) {
                mFairy.ranSwipe(1118, 203, 1135, 437, 2, 1000,(long)3000); //上滑
                LtLog.e("--------------------主线没有找到上滑");
                count2 = 0;
            }
            if (count2 > 50) {
                result = mFairy.findPic("mapButton.png");
                mFairy.onTap(0.8f, result, "点地图",1000);
                mFairy.onTap(831, 242,832,243, "点京城",1000);
                count2 = 0;
            }
        }
    }//新手引导

    public void thread() throws Exception {
        int count = 5, count1 = 0, count2 = 0;
         while (mFairy.condit()) {
            LtLog.e("-----主线任务");
            for (int i = 0; i < 3; i++) {
                result = mFairy.findPic("task1.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(1068, 129, 1069,130,"点任务",1000);
                }


                result = mFairy.findPic(512, 0, 1274, 539,
                        new String[]{"close.png", "tux.png", "cjx.png", "meet.png", "close1.png", "close2.png"});
                mFairy.onTap(0.7f, result, "关闭窗口",1000);

                result = mFairy.findPic("rightTip.png");
                mFairy.onTap(0.7f, result, "Tip",1000);
            }

            result = mFairy.findPic(709, 5, 1270, 391, "close.png");
            mFairy.onTap(0.7f, result, "关闭",1000);


             while (mFairy.condit()) {
                result = mFairy.findPic("click.png");
                if (result.sim > 0.9f) {
                    mFairy.onTap(0.9f, result, "点击任意屏幕继续",1000);
                    count2 = 0;
                } else {
                    break;
                }
            }
             while (mFairy.condit()) {
                result = mFairy.findPic("click1.png");
                if (result.sim > 0.9f) {
                    mFairy.onTap(0.9f, result, "点击任意屏幕跳过",1000);
                    count2 = 0;
                } else {
                    break;
                }
            }

            result = mFairy.findPic(1027, 159, 1101, 567, "zhuxian.png");
            if (result.sim > 0.8f) {
                result1 = mFairy.findPic(1024,152,1280,498,"end.png");
                if (result1.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("主线已经结束，请先提升等级再进行"));
                    break;
                } else {
                    count1 = 0;
                    mFairy.onTap(0.7f, result, "主线",1000);
                    count2++;
                    Thread.sleep(2000);
                }
            }

            result = mFairy.findPic(780, 357, 1018, 682, "thread.png");
            mFairy.onTap(0.7f, result, "主线",1000);

            result = mFairy.findPic(780, 357, 1018, 682, "battle.png");
            if (result.sim > 0.7f) {
                mFairy.onTap(0.7f, result, "战斗",1000);
                count2 = 0;
                Thread.sleep(5000);
            }

            result = mFairy.findPic("end2.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(970, 260,971,261,"关闭",1000);
                break;
            }


            result = mFairy.findPic(891, 411, 1261, 699, "use.png");
            if (result.sim > 0.7f) {
                mFairy.onTap(0.7f, result, "使用",1000);
                count2 = 0;
                Thread.sleep(2000);
            }
            result = mFairy.findPic(891, 411, 1261, 699, "equipment.png");
            mFairy.onTap(0.7f, result, "装备",1000);

            gamePublicFuntion.guide();

            count1++;
            if (count1 == 10 || count1 == 12 || count1 == 14 || count1 == 16 || count1 == 18) {
                mFairy.ranSwipe(1118, 203, 1135, 437, 2, 1000,(long)3000); //上滑
                LtLog.e("--------------------主线没有找到上滑");
                count2 = 0;
            } else if (count1 > 20) {
                break;
            }

            if (count2 > 30) {
                result = mFairy.findPic("mapButton.png");
                mFairy.onTap(0.8f, result, "点地图",1000);
                mFairy.onTap(831, 242,832,243, "点京城",1000);
                count2 = 0;
            }
        }


    }//主线

    public void branchLine() throws Exception {
        int count = 5, count1 = 0, count2 = 0;
         while (mFairy.condit()) {
            LtLog.e("-----支线任务");
            if (count > 2) {
                for (int i = 0; i < 3; i++) {
                    result = mFairy.findPic("task1.png");
                    if (result.sim > 0.7f) {
                        mFairy.onTap(1068, 129,1069,130, "点任务",1000);
                    }


                    result = mFairy.findPic(512, 0, 1274, 539,
                            new String[]{"close.png", "tux.png", "cjx.png", "meet.png", "close1.png", "close2.png"});
                    mFairy.onTap(0.7f, result, "关闭窗口",1000);

                    result = mFairy.findPic("quxiao.png");
                    mFairy.onTap(0.9f, result, "取消",1000);

                    result = mFairy.findPic("extend1.png");
                    mFairy.onTap(0.9f, result, "关闭扩展1",1000);

                    result = mFairy.findPic("extend2.png");
                    mFairy.onTap(0.9f, result, "关闭扩展2",1000);

                    result = mFairy.findPic("got.png");
                    mFairy.onTap(0.8f, result, "知道了",1000);

                    result = mFairy.findPic("got1.png");
                    mFairy.onTap(0.8f, result, "知道了",1000);

                    result = mFairy.findPic("rightTip.png");
                    mFairy.onTap(0.7f, result, "Tip",1000);
                }
                count = 0;
            }

            result = mFairy.findPic(709, 5, 1270, 391, "close.png");
            mFairy.onTap(0.7f, result, "关闭",1000);


             while (mFairy.condit()) {
                result = mFairy.findPic("click.png");
                if (result.sim > 0.9f) {
                    mFairy.onTap(0.9f, result, "点击任意屏幕继续",1000);
                    count2 = 0;
                } else {
                    break;
                }
            }
             while (mFairy.condit()) {
                result = mFairy.findPic("click1.png");
                if (result.sim > 0.9f) {
                    mFairy.onTap(0.9f, result, "点击任意屏幕跳过",1000);
                    count2 = 0;
                } else {
                    break;
                }
            }

            result = mFairy.findPic(1027, 159, 1101, 567, "zhixian.png");
            LtLog.e("---------------支线 ***" + result.sim);
            if (result.sim > 0.7f) {
                mFairy.onTap(0.7f, result, "支线",1000);
                Thread.sleep(3000);
                count = 0;
                count1 = 0;
                count2++;

            } else {
                count++;
            }


            result = mFairy.findPic(780, 357, 1018, 682, "branchLine.png");
            mFairy.onTap(0.7f, result, "支线",1000);

            result = mFairy.findPic(780, 357, 1018, 682, "battle.png");
            LtLog.e("-----------战斗 ***" + result.sim);
            if (result.sim > 0.7f) {
                mFairy.onTap(0.7f, result, "战斗",1000);
                count2 = 0;
                Thread.sleep(5000);
            }


            result = mFairy.findPic("end2.png");
            if (result.sim > 0.7f) {
                mFairy.onTap(970, 260,971,261, "关闭",1000);
                Thread.sleep(4000);
                mFairy.onTap(1202, 296,1203,297, "离开",1000);
                break;
            }


            result = mFairy.findPic("zhixian1.png");
            mFairy.onTap(0.7f,result, 641, 429, 642,430,"生气动作",1000);


            result = mFairy.findPic(891, 411, 1261, 699, "use.png");
            if (result.sim > 0.7f) {
                mFairy.onTap(0.7f, result, "使用",1000);
                count2 = 0;
                Thread.sleep(5000);
            }

            result = mFairy.findPic(891, 411, 1261, 699, "equipment.png");
            mFairy.onTap(0.7f, result, "装备",1000);


            gamePublicFuntion.guide();

            count1++;

            if (count1 == 10 || count1 == 12 || count1 == 14 || count1 == 16 || count1 == 18) {
                mFairy.ranSwipe(1118, 203, 1135, 437, 2, 1000,(long)3000); //上滑
                LtLog.e("--------------------支线没有找到上滑");
            } else if (count1 > 20) {
                break;
            }

            if (count2 > 50) {
                result = mFairy.findPic("mapButton.png");
                mFairy.onTap(0.8f, result, "点地图",1000);
                mFairy.onTap(831, 242,832,243, "点京城",1000);
                count2 = 0;
            }


        }

    }//支线

    public void master() throws Exception {
        int bj = 0;
        int count = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------师门任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "masterTu.png", 1,0);
                if (bj == -1) {
                    break;
                }
                count = 0;
            }
            if (bj == 3) {
                gamePublicFuntion.guide();

                result = mFairy.findPic(1022, 151, 1095, 535, "Master1.png");
                if (result.sim > 0.7f) {
                    count = 0;
                    mFairy.onTap(0.7f, result, "",1000);
                    Thread.sleep(4000);
                    LtLog.e(mFairy.getLineInfo("点击了右侧师门"));
                } else {
                    result = mFairy.findPic(1022, 151, 1095, 535, "Master.png");
                    if (result.sim > 0.7f) {
                        count = 0;
                        mFairy.onTap(0.7f, result, "",1000);
                        Thread.sleep(4000);
                        LtLog.e(mFairy.getLineInfo("点击了右侧师门"));
                    }
                }

                result = mFairy.findPic("sell.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(970, 219,971,220, "售出-点",1000);
                }

                result = mFairy.findPic("purchase.png");
                if (result.sim > 0.9f) {
                    count = 0;
                    mFairy.onTap(0.9f, result, "购买东西",1000);
                }


                result = mFairy.findPic("end0.png");
                if (result.sim > 0.8f) {
                    FT=0;
                    break;
                }


                result = mFairy.findPic(955, 452, 1032, 701, "Master2.png");
                if (result.sim > 0.9f) {
                    count = 0;
                    mFairy.onTap(0.9f, result, "寻物",1000);
                }


                result = mFairy.findPic("purchase1.png");
                if (result.sim > 0.9f) {
                    count = 0;
                    mFairy.onTap(0.9f, result, "购买药品",1000);
                }

                result = mFairy.findPic("purchase2.png");
                if (result.sim > 0.9f) {
                    count = 0;
                    mFairy.onTap(0.9f, result, "购买宠物",1000);
                }

                result = mFairy.findPic("confirm1.png");
                if (result.sim > 0.7f) {
                    count = 0;
                    mFairy.onTap(0.9f, result, "确定提交",1000);
                }

                result = mFairy.findPic("confirm.png");
                if (result.sim > 0.7f) {
                    count = 0;
                    mFairy.onTap(0.7f, result, "确定提交",1000);
                }

                result = mFairy.findPic(811, 322, 1171, 702, "battle.png");
                if (result.sim > 0.7f) {
                    count = 0;
                    mFairy.onTap(0.9f, result, "战斗",1000);
                    Thread.sleep(30000);
                }

                result = mFairy.findPic("click.png");
                mFairy.onTap(0.9f, result, "点击任意屏幕继续",1000);

                gamePublicFuntion.inBattle();//（自动）战斗中

                if (count == 6 || count == 9) {
                    mFairy.ranSwipe(1103, 177, 1125, 478, 2, 1000,(long)3000); //上滑
                    LtLog.e("--------------------师门没有找到上滑");
                } else if (count > 12) {
                    bj = 0;
                }
                count++;
            }

        }
    }//师门任务

    public void paoshang() throws Exception {
        int bj = 0;
        int count = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------跑商任务bj=" + bj);
            if (bj == 0) {
                gamePublicFuntion.init(0);
                bj=1;
            }
            if(bj==1){
                result = mFairy.findPic(548,489,1267,710,"ps.png");
                if(result.sim>0.85f){
                    mFairy.onTap(0.85f, result, "帮派",1000);
                }else{
                    result = mFairy.findPic("ps1.png");
                    if(result.sim>0.8f) {
                        mFairy.onTap(0.8f, result, "展开",1000);
                        Thread.sleep(1000);
                    }
                }

                result = mFairy.findPic("ps2.png");
                if(result.sim>0.85f){
                    result = mFairy.findPic("ps3.png");
                    mFairy.onTap(0.85f, result, "帮务",1000);

                    result = mFairy.findPic("ps4.png");
                    mFairy.onTap(0.85f, result, "商事",1000);

                    result = mFairy.findPic("ps5.png");
                    mFairy.onTap(0.85f, result, "帮派跑商",1000);
                }

                result = mFairy.findPic("ps6.png");
                if(result.sim>0.85f){
                    result = mFairy.findPic("ps7.png");
                    mFairy.onTap(0.85f, result, "接取跑商",1000);
                }


            }

        }
    }//师门任务

    public void escort() throws Exception {
        int bj = 0;
        int count = 0, count1 = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------护送任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "escortTu.png", 1, 1);
                if (bj == -1) {
                    break;
                }
                count1 = 0;

            }
            if (bj == 3) {
                gamePublicFuntion.guide();
                result = mFairy.findPic(929, 463, 1080, 699, "escortTack1.png");
                if (result.sim > 0.8f) {
                    count1 = 0;
                    mFairy.onTap(0.8f, result, "接取特殊护送任务",1000);
                }
                result = mFairy.findPic(929, 463, 1080, 699, "escortTack.png");
                if (result.sim > 0.8f) {
                    count1 = 0;
                    mFairy.onTap(0.8f, result, "接取护送任务",1000);
                }

                result = mFairy.findPic("escortConfirm.png");
                if (result.sim > 0.8f) {
                    count1 = 0;
                    mFairy.onTap(0.8f, result, "确定消费",1000);
                    Thread.sleep(25000);
                }
                result = mFairy.findPic("click.png");
                mFairy.onTap(0.8f, result, "点击任意屏幕继续",1000);

                result = mFairy.findPic("escortYes.png");
                if (result.sim > 0.8f) {
                    count1 = 0;
                    mFairy.onTap(0.8f, result, "是 （继续下一个护送任务）",1000);
                    Thread.sleep(10000);
                }
                result = mFairy.findPic("escortYes1.png");
                if (result.sim > 0.8f) {
                    count1 = 0;
                    mFairy.onTap(0.8f, result, "是（额外的护送任务",1000);
                    Thread.sleep(10000);
                }
                gamePublicFuntion.inBattle();//（自动）战斗中

                if (count1 == 3) {
                    mFairy.ranSwipe(1006, 518, 1037, 638, 2, 1000,(long)3000); //上滑
                    LtLog.e("--------------------师门没有找到上滑");
                } else if (count1 > 5) {
                    bj = 0;
                }

                count1++;
            }

        }
    }//护送任务

    public void treasure() throws Exception {
        int bj = 0;
        int count = 0, count1 = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------宝图任务bj=" + bj);

            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "treasureTu.png", 1,  0);
                if (bj == -1) {
                    break;
                }
                count1 = 0;
            }

            if (bj == 3) {

                result = mFairy.findPic(1020, 152, 1104, 522, "treasure.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.7f, result, "宝图任务",1000);
                    Thread.sleep(5000);
                    count1 = 0;
                }
                result = mFairy.findPic(793, 399, 1221, 677, "treasureTack.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "宝图任务",1000);
                    Thread.sleep(5000);
                    count1 = 0;
                }
                result = mFairy.findPic(810, 392, 1223, 680, "duihuan.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1239, 234,1240,235, "关",1000);
                    count1 = 0;
                }

                 while (mFairy.condit()) {
                    result = mFairy.findPic("click.png");
                    if (result.sim > 0.9f) {
                        mFairy.onTap(0.9f, result, "点击任意屏幕继续",1000);
                    } else {
                        break;
                    }
                }


                result = mFairy.findPic("auto1.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.9f, result, "自动战斗",1000);
                } else {
                     while (mFairy.condit()) {
                        result = mFairy.findPic("auto.png");
                        if (result.sim > 0.7f) {
                            count1 = 0;
                            LtLog.e("战斗中....");
                            Thread.sleep(3000);
                        } else {
                            break;
                        }
                    }
                }


                if (count1 == 6 || count1 == 9) {
                    mFairy.ranSwipe(1103, 177, 1125, 478, 2, 3000,(long)3000); //上滑
                    LtLog.e("--------------------宝图没有找到上滑");
                } else if (count1 > 9) {
                    bj = 0;
                }
                count1++;
            }
        }
    }//宝图任务

    public void money() throws Exception {
        int bj = 0;
        int count = 0, count1 = 0;
        int num = 1;
         while (mFairy.condit()) {
            LtLog.e("----------------------赏金任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "moneyTu.png", 1, 0);
                if (bj == -1) {
                    break;
                }
                count1 = 0;
            }

            if (bj == 3) {
                gamePublicFuntion.guide();

                result = mFairy.findPic(848, 383, 1198, 670, "moneyTack.png");
                mFairy.onTap(0.8f, result, "接取赏金任务",1000);

                result = mFairy.findPic(622, 150, 736, 482, "yjq.png");
                if (result.sim > 0.9f) {
                    mFairy.onTap(1068, 176,1069,177, "关闭赏金窗口",1000);
                     while (mFairy.condit()) {
                        result = mFairy.findPic(1024, 157, 1274, 507, "xing.png");
                        if (result.sim > 0.7f) {
                            mFairy.onTap(0.7f, result, "右侧任务栏",1000);
                            break;
                        } else {
                            count++;
                        }
                        if (count == 10 || count == 12 || count == 14 || count == 16 || count == 18) {
                            mFairy.ranSwipe(1118, 203, 1135, 437, 2, 1000,(long)3000); //上滑
                            LtLog.e("--------------------赏金没有没有找到上滑");
                        }

                    }
                }
                result = mFairy.findPic(1024, 157, 1274, 507, "xing.png");
                mFairy.onTap(0.7f, result, "右侧任务栏",1000);

                result = mFairy.findPic(866, 450, 1211, 686, "xing1.png");
                mFairy.onTap(0.7f, result, "星星",1000);

                result = mFairy.findPic("moneyPurchase.png");
                if (result.sim > 0.9f) {
                    mFairy.onTap(0.9f, result, "赏金任务（购买药品）",1000);
                    count1 = 0;
                }
                result = mFairy.findPic("click.png");
                mFairy.onTap(0.9f, result, "点击任意屏幕继续",1000);

                gamePublicFuntion.inBattle();//（自动）战斗中

                LtLog.e("--------------------num的值：" + num);
                switch (num) {
                    case 1:
                        result = mFairy.findPic("switch1.png");
                        LtLog.e(mFairy.getLineInfo("已经完成"));
                        if (result.sim > 0.7f) {
                            num++;
                            break;
                        }
                        result = mFairy.findPic("moneyOne.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "第一个任务",1000);
                        }
                        result = mFairy.findPic("pu.png");
                        if (result.sim > 0.9f) {
                            count1 = 0;
                            mFairy.onTap(0.9f, result, "接取第一个任务",1000);
                            num++;
                            Thread.sleep(30000);
                        }
                        break;
                    case 2:
                        result = mFairy.findPic("switch2.png");
                        if (result.sim > 0.7f) {
                            
                            num++;
                            break;
                        }
                        result = mFairy.findPic("moneyTwo.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "第二个任务",1000);
                        }
                        result = mFairy.findPic("pu.png");
                        if (result.sim > 0.9f) {
                            count1 = 0;
                            mFairy.onTap(0.9f, result, "接取第二个任务",1000);
                            num++;
                            Thread.sleep(30000);
                        }
                        break;
                    case 3:
                        result = mFairy.findPic("switch3.png");
                        if (result.sim > 0.7f) {
                            LtLog.e(mFairy.getLineInfo("已完成"));
                            num++;
                            break;
                        }
                        result = mFairy.findPic("moneyThree.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "第三个任务",1000);
                        }
                        result = mFairy.findPic("pu.png");
                        if (result.sim > 0.9f) {
                            count1 = 0;
                            mFairy.onTap(0.9f, result, "接取第三个任务",1000);
                            num++;
                            Thread.sleep(30000);
                        }
                        break;
                    case 4:
                        result = mFairy.findPic("switch4.png");
                        if (result.sim > 0.7f) {
                            LtLog.e(mFairy.getLineInfo("已完成"));
                            result = mFairy.findPic("free.png");
                            mFairy.onTap(0.8f, result, "免费刷新",1000);
                            num = 1;
                            break;
                        }
                        result = mFairy.findPic("moneyFour.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "第四个任务",1000);
                        }
                        result = mFairy.findPic("pu.png");
                        if (result.sim > 0.9f) {
                            count1 = 0;
                            mFairy.onTap(0.9f, result, "接取第四个任务",1000);
                            num = 1;
                            Thread.sleep(30000);
                        }
                        break;
                }
                if (count1 > 5) {
                    bj = 0;
                }
                count1++;
            }

        }
    }//赏金任务

    public void arena() throws Exception {
        int bj = 0;
        int count = 0, count1 = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------竞技场任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "arenaTu.png", 1, 0);
                if (bj == -1) {
                    break;
                }
                count1 = 0;
            }
            if (bj == 3) {
                result = mFairy.findPic("arenaTack.png");
                if (result.sim > 0.7f) {
                    count1 = 0;
                    result = mFairy.findPic("challenge.png");
                    mFairy.onTap(0.9f, result, "挑战",1000);
                }

                result = mFairy.findPic("auto1.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.9f, result, "自动战斗",1000);
                } else {
                     while (mFairy.condit()) {
                        result = mFairy.findPic("auto.png");
                        if (result.sim > 0.7f) {
                            count1 = 0;
                            Thread.sleep(3000);
                        } else {
                            break;
                        }
                    }
                }
                result = mFairy.findPic("0.png");
                if (result.sim > 0.9f) {
                    LtLog.e(mFairy.getLineInfo("没有次数了"));
                    mFairy.onTap(775, 346,776,347, "每日首胜奖励",1000);
                    Thread.sleep(2000);
                    mFairy.onTap(962, 343,963,344, "每日五站奖励",1000);
                    Thread.sleep(2000);
                    result = mFairy.findPic("xiaci.png");
                    mFairy.onTap(0.8f, result, "下次吧",1000);
                    break;
                }

                if (count1 > 20) {
                    bj = 0;
                }
                count1++;
            }
        }

    }//竞技场

    public void lsym() throws Exception {
        int bj = 0;
        int count = 0, count1 = 0, count2 = 0;
        int cs_0 = 0, cs_1 = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------乱世妖魔任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "lsymTu.png", 2, 2);
                if (bj == -1) {
                    break;
                }
                count1 = 0;
                count2 = 0;
            }
            if (bj == 3) {
                gamePublicFuntion.guide();
                result = mFairy.findPic(842, 397, 1192, 636, "ymBattle.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "妖魔战斗",1000);
                     while (mFairy.condit()) {
                        result = mFairy.findPic("activity.png");
                        if (result.sim > 0.9f) {
                            LtLog.e(mFairy.getLineInfo("发现活动"));
                            Thread.sleep(2000);
                            count2++;
                        } else {
                            LtLog.e("睡......");
                            Thread.sleep(5000);
                        }

                        if (count2 > 5) {
                            break;
                        }
                    }
                }
                if (count1 > 10) {
                    bj = 0;
                }
                count1++;
            }
        }
    }//乱世妖魔

    public void hhms() throws Exception {
        int bj = 0;
        int count = 0, count1 = 0, count2 = 0;
        int cs_0 = 0, cs_1 = 0, cs_2 = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------还魂秘术任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "hhmsTu.png", 1, 0);
                if (bj == -1) {
                    break;
                }
                count1 = 0;
                cs_2 = 0;
            }
            if (bj == 3) {
                cs_2++;
                if (cs_2 > 20) {
                    bj = 0;
                }
                result = mFairy.findPic(993,445,1059,690,"hhmsEnd.png");
                if(result.sim>0.9f) {
                    LtLog.e(mFairy.getLineInfo("还魂秘术结束,End!"));
                    break;
                }

                result = mFairy.findPic("hhmslq.png");
                if(result.sim>0.9f) {
                    mFairy.onTap(0.9f, result, "领取",1000);
                    LtLog.e(mFairy.getLineInfo("还魂秘术结束,End!"));
                    break;
                }
                result = mFairy.findPic(806, 357, 1135, 684, "hhmsTack2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "查看任务",1000);
                } else {
                    result = mFairy.findPic(953, 438, 1024, 683, "hhmsTack.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "还魂秘术",1000);
                        Thread.sleep(2000);
                        mFairy.onTap(764, 479,765,480, "领取",1000);
                    }
                }

                result = mFairy.findPic("hhmsIcn.png");
                if (result.sim > 0.9f) {
                    LtLog.e(mFairy.getLineInfo("还魂秘术界面"));
                    bj = 4;
                }
            }

            if (bj == 4) {
                result = mFairy.findPic(183, 164, 834, 612, "dlone.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.7f, result, "丹炉",1000);

                    result = mFairy.findPic("add.png");
                    if (result.sim > 0.9f) {
                        mFairy.onTap(0.7f, result, "添加",1000);
                    } else {
                        int num = gamePublicFuntion.hhmsTool(count2);
                        if (num == -1) {
                            count2++;
                        } else if (num == 4) {

                        } else {
                            gamePublicFuntion.gg("hhmsTu.png",1);
                            count2 = 0;
                        }
                    }
                } else {
                    count1++;
                }
                result = mFairy.findPic("end1.png");
                if (result.sim > 0.8) {
                    result = mFairy.findPic("receive1.png");
                    mFairy.onTap(0.9f, result, "领取",1000);
                    bj = 0;
                }
                if (count1 > 20) {
                    bj = 0;
                }

            }
        }
    }//还魂秘术

    public void hlhj() throws Exception {
        int bj = 0;
        int count = 0, count1 = 0;
        int cs_0 = 0, cs_1 = 0, cs_2 = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------黑龙浩劫任务bj=" + bj);

            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "hlhjTu.png", 1, 1);
                if (bj == -1) {
                    break;
                }
                count=0;
                count1 = 0;
                cs_0 = 0;
                cs_2 = 0;
            }

            if (bj == 3) {
                cs_2++;
                if (cs_2 > 20) {
                    bj = 0;
                }
                result = mFairy.findPic(953, 438, 1024, 683, "hlhjTack.png");
                mFairy.onTap(0.7f, result, "黑龙浩劫",1000);


                result = mFairy.findPic("hlhjUi.png");
                if (result.sim > 0.9f) {
                    LtLog.e(mFairy.getLineInfo("黑龙浩劫界面"));
                    bj = 4;

                }
            }

            if (bj == 4) {
                result = mFairy.findPic("end3.png");
                if (result.sim > 0.9f) {
                    LtLog.e(mFairy.getLineInfo("次数已经用完,结束！"));
                    break;
                }

                result = mFairy.findPic(182, 127, 1100, 566, "qwe.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.7f, result, "找到",1000);

                    result = mFairy.findPic("challenge1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "挑战",1000);
                        Thread.sleep(3000);
                        bj = 5;
                    }
                }
                if (count1 == 10 || count1 == 15) {
                    mFairy.ranSwipe(495, 295, 915, 330, 3, 1000,(long)2000); //左滑
                } else if (count1 > 15) {
                    bj = 0;
                }
                count1++;
            }
            if (bj == 5) {
                cs_0++;
                if (cs_0 > 30) {
                    bj = 0;
                }


                if (count > 30) {
                    bj = 0;
                }

                result = mFairy.findPic("end2.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(970, 260,971,261, "关闭",1000);
                    Thread.sleep(4000);
                    mFairy.onTap(1202, 296,1203,297, "离开",1000);
                    break;
                }
                result = mFairy.findPic("ftw.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.7f, result, "点击寻路",1000);
                    cs_0 = 0;
                    count++;
                }


                result = mFairy.findPic(871,410,1161,650,"goBoss.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.7f, result, "进入Boss战",1000);
                    cs_0 = 0;
                    count++;
                }

                result = mFairy.findPic("auto1.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.9f, result, "自动战斗",1000);
                }
                result = mFairy.findPic("auto.png");
                if (result.sim > 0.7f) {
                    cs_0 = 0;
                    count = 0;
                    Thread.sleep(3000);
                }
            }
        }
    }//黑龙浩劫

    public void moon() throws Exception {
        int bj = 0;
        int count = 0;
        long time = System.currentTimeMillis();
         while (mFairy.condit()) {

            if ((System.currentTimeMillis() - time) > 600000) {
                LtLog.e("-----        10分钟>>>>>没有人和你赏月,End!");
                break;
            }


            LtLog.e("----------------------赏月任务任务bj=" + bj);

            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "moonTu.png", 1, 1);
                if (bj == -1) {
                    break;
                }
            }

            if (bj == 3) {

                result = mFairy.findPic("moon1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "一键邀请",1000);
                    bj = 4;
                } else {
                    LtLog.e("没有帮派赏月结束,End!");
                    break;
                }
            }


            if (bj == 4) {
                count++;
                if (count > 50) {
                    break;
                }
                Thread.sleep(2000);
                result = mFairy.findPic("moon2.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.7f, result, "赏月中",1000);
                    count = 0;
                    Thread.sleep(8000);
                    bj = 0;
                }


            }


        }
    }//赏月

    public void qyzpt() throws Exception {
        int bj = 0;
        int cs_0 = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------青云志-普通任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "qyzptTu.png", 1, 1);
                if (bj == -1) {
                    break;
                }
                cs_0 = 0;
            }
            if (bj == 3) {
                cs_0++;
                if (cs_0 > 80) {
                    break;
                }

                result = mFairy.findPic("end2.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(970, 260,971,261, "关闭",1000);
                    break;
                }
                result = mFairy.findPic(1004, 291, 1186, 707, "qyzptTack.png");
                mFairy.onTap(0.8f, result, "青云志-普通",1000);

                result = mFairy.findPic("challenge2.png");
                mFairy.onTap(0.8f, result, "挑战",1000);

                result = mFairy.findPic("ok.png");
                mFairy.onTap(0.8f, result, "确定",1000);


                result = mFairy.findPic("auto1.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.9f, result, "自动战斗",1000);
                } else {
                     while (mFairy.condit()) {
                        result = mFairy.findPic("auto.png");
                        if (result.sim > 0.7f) {
                            cs_0 = 0;
                            LtLog.e("战斗中....");
                            Thread.sleep(3000);
                        } else {
                            break;
                        }
                    }
                }


            }
        }
    }//青云志-普通

    public void wbt() throws Exception {
        int bj = 0;
        int count = 0, count1 = 0;
        int cs = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------挖宝图任务bj=" + bj);
            if (bj == 0) {
                result = mFairy.findPic(512, 0, 1274, 539,
                        new String[]{"close.png", "tux.png", "cjx.png", "meet.png", "close1.png", "close2.png","close5.png"});
                mFairy.onTap(0.7f, result, "关闭窗口",1000);

                result = mFairy.findPic("extend1.png");
                mFairy.onTap(0.9f, result, "关闭扩展1",1000);

                result = mFairy.findPic("extend2.png");
                mFairy.onTap(0.9f, result, "关闭扩展2",1000);

                result = mFairy.findPic("click.png");
                mFairy.onTap(0.9f, result, "点击任意屏幕继续",1000);

                result = mFairy.findPic("click1.png");
                mFairy.onTap(0.9f, result, "点击任意屏幕跳过",1000);

                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "包裹",1000);
                    bj = 1;
                }
                count = 0;
                count1 = 0;
                cs = 0;
            }
            if (bj == 1) {
                result = mFairy.findPic("mapUi.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面"));

                    result = mFairy.findPic(640, 142, 1091, 626, "map.png");
                    if (result.sim > 0.7f) {
                        mFairy.onTap(0.7f, result, "点击宝图",1000);
                        Thread.sleep(2000);
                        result = mFairy.findPic(399,274,648,469,"use1.png");
                        if(result.sim>0.8f) {
                            mFairy.onTap(0.8f, result, "使用",1000);
                            bj = 2;
                        }
                        count = 0;
                    } else {
                        count++;
                    }

                    if (count == 5 || count == 10 || count == 15) {
                        mFairy.ranSwipe(814, 266, 855, 543, 2, 1000,(long)5000); //上滑
                    } else if (count > 15) {
                        LtLog.e("-----宝图已经没有了，end！");
                        break;
                    }
                } else {
                    cs++;
                    if (cs > 30) {
                        bj = 0;
                        cs = 0;
                    }
                }
            }
            if (bj == 2) {
                if (count1 > 15) {
                    bj = 0;
                }

                result = mFairy.findPic("wb1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "挖宝",500);

                    for (int i = 0; i < 10; i++) {
                        result = mFairy.findPic(498,92,683,492,"wbt2.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("宝图数量不足!"));
                            return;
                        }
                    }

                    count1 = 0;
                }

                result = mFairy.findPic("wb2.png");
                mFairy.onTap(0.8f, result, "挖宝确定",3000);


                result = mFairy.findPic("wb2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "挖宝完成,End!",3000);
                    //break;
                }

                result = mFairy.findPic("wbz.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(981, 178,982,179, "关闭",1000);
                } else {
                    Thread.sleep(3000);
                    count1++;
                }
            }
        }
    }//挖宝图

    public void gangs() throws Exception {
        int bj = 0;
        int cs = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------帮派任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "gangsTu.png", 3, 0);
                if (bj == -1) {
                    break;
                }
                cs = 0;
            }
            if (bj == 3) {
                cs++;
                if (cs > 8) {
                    bj = 0;
                }

                gamePublicFuntion.guide();

                result = mFairy.findPic("qwe1.png");
                if (result.sim > 0.9f) {
                    result = mFairy.findPic(971, 472, 1047, 694, "gangsTack.png");
                    if (result.sim > 0.9f) {
                        mFairy.onTap(0.9f, result, "帮派任务",1000);
                        Thread.sleep(5000);
                    } else {
                        mFairy.onTap(1242, 231,1243,232, "关闭",1000);
                    }
                }

                result = mFairy.findPic("sell.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("售出"));
                    mFairy.onTap(970, 219,971,220, "点",1000);
                }


                result = mFairy.findPic(684, 387, 1258, 698,
                        new String[]{"gm1.png", "gm2.png", "gm3.png", "gm4.png", "gm5.png"});
                mFairy.onTap(0.8f, result, "购买",1000);

                result = mFairy.findPic(804, 201, 1266, 706,
                        new String[]{"confirm.png", "confirm1.png", "ok.png", "ok1.png"});
                mFairy.onTap(0.7f, result, "确定",1000);

                result = mFairy.findPic(1020, 143, 1092, 518, "gangs.png");
                if (result.sim > 0.7f) {
                    cs = 0;
                    mFairy.onTap(0.7f, result, "右侧帮派任务",1000);

                } else {
                    result = mFairy.findPic(1020, 143, 1092, 518, "gangs1.png");
                    mFairy.onTap(0.7f, result, "右侧帮派任务",1000);
                }

                gamePublicFuntion.inBattle();


                result = mFairy.findPic("click.png");
                if (result.sim > 0.8f) {
                    cs = 0;
                    mFairy.onTap(0.8f, result, "点击任意屏幕继续",1000);
                }
                result = mFairy.findPic(780, 357, 1018, 682, "battle.png");
                mFairy.onTap(0.7f, result, "战斗",1000);

                result = mFairy.findPic(891, 411, 1261, 699, "use.png");
                mFairy.onTap(0.7f, result, "使用",1000);

                result = mFairy.findPic(986, 403, 1164, 693, "qwe2.png");
                mFairy.onTap(0.7f, result, "访贤纳士",1000);
            }

        }

    }//帮派任务


    private int sl =0;
    public void sl() throws Exception {

        long time = System.currentTimeMillis();

        int bj = 0, bj1 = 0;
        int count = 0, count1 = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------试炼任务任务bj=" + bj);


            if ((System.currentTimeMillis() - time) > 600000) {
                LtLog.e("-----        10分钟>>>>>重置" + time);
                gamePublicFuntion.mapButton();
                time = System.currentTimeMillis();
            }


            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "slTu.png", 1, 2);
                if (bj == -1) {
                    break;
                }
                count = 0;
                bj1 = 0;
            }
            if (bj == 3) {

                result = mFairy.findPic(929, 442, 1049, 699, "slTack.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "试炼任务",1000);
                    Thread.sleep(2000);
                    result = mFairy.findPic("slUi.png");
                    mFairy.onTap(0.8f,result, 756, 427,757 ,428, "确定",1000);
                }

                result = mFairy.findPic(1017, 131, 1120, 521,
                        new String[]{"slRight.png", "slRight1.png", "slRight2.png","sl1.png"});
                LtLog.e("------------右侧试炼任务" + result.sim);
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.7f, result, "找到右侧试炼任务",1000);
                    Thread.sleep(5000);
                    count = 0;
                    count1 = 0;
                }

                if (AtFairyConfig.getOption("slth").equals("1") ){
                    result = mFairy.findPic("slgm.png");
                    mFairy.onTap(0.7f, result, "购买",1000);
                }

                if (AtFairyConfig.getOption("slpt").equals("1") ){
                    if (bj1 < 3) {
                        result = mFairy.findPic("zygj.png");
                        if (result.sim > 0.7f) {
                            mFairy.onTap(0.7f, result, "挂机",1000);
                            Thread.sleep(5000);

                            bj1++;
                        }
                    } else {
                        result = mFairy.findPic("slgm.png");
                        mFairy.onTap(0.7f, result, "购买",1000);
                        bj1 = 0;
                    }
                }


                if (AtFairyConfig.getOption("gmzt").equals("1") ){
                    if (bj1 < 3) {
                        result = mFairy.findPic("zygj.png");
                        if (result.sim > 0.7f) {
                            mFairy.onTap(0.7f, result, "挂机",1000);
                            Thread.sleep(5000);
                            bj1++;
                        }
                    } else {
                        break;
                    }
                }

                result = mFairy.findPic("end0.png");
                if (result.sim > 0.8f) {
                    break;
                }


                result = mFairy.findPic("shop.png");
                if (result.sim > 0.8f) {
                     while (mFairy.condit()) {
                        result = mFairy.findPic("jn.png");
                        if (result.sim > 0.8f) {

                            result = mFairy.findPic(710, 161, 754, 584, "lv.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "lv",1000);
                                result = mFairy.findPic("shopOne.png");
                                mFairy.onTap(0.8f, result, "买一个",1000);
                                count = 0;
                                break;
                            } else {
                                result = mFairy.findPic("xia.png");
                                mFairy.onTap(0.8f, result, "下一页",1000);
                                count = 0;
                                count1++;
                                if (count1 > 18) {
                                    mFairy.onTap(623, 206,624,207, "点第一个",1000);
                                    result = mFairy.findPic("shopOne.png");
                                    mFairy.onTap(0.8f, result, "买一个",1000);
                                    count1 = 0;
                                    break;
                                }
                            }
                        } else {
                            result = mFairy.findPic("shopOne.png");
                            mFairy.onTap(0.8f, result, "买一个",1000);
                            break;
                        }
                    }
                }


                result = mFairy.findPic(881, 408, 1192, 709, "sl.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.7f, result, "试炼",1000);
                } else {
                    result = mFairy.findPic(659, 375, 1225, 670,
                            new String[]{"gm1.png", "gm2.png", "gm3.png", "gm4.png", "gm5.png"});
                    if(result.sim > 0.9f) {
                        mFairy.onTap(0.9f, result, "购买",1000);
                        Thread.sleep(2000);
                        result = mFairy.findPic(512, 0, 1274, 539,"close.png");
                        mFairy.onTap(0.8f,result,"关闭",1000);
                    }
                }


                result = mFairy.findPic("auto1.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.9f, result, "自动战斗",1000);
                } else {
                     while (mFairy.condit()) {
                        result = mFairy.findPic("auto.png");
                        if (result.sim > 0.7f) {
                            bj1 = 0;
                            count = 0;
                            LtLog.e("战斗中....");
                            Thread.sleep(3000);
                        } else {
                            break;
                        }
                    }
                }

                 while (mFairy.condit()) {
                    result = mFairy.findPic("click.png");
                    if (result.sim > 0.9f) {
                        mFairy.onTap(0.9f, result, "点击任意屏幕继续",1000);
                        count = 0;
                    } else {
                        break;
                    }
                }

                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.7f,result, 1064, 125, 1065,126, "任务",1000);

                result = mFairy.findPic(780, 357, 1018, 682, "battle.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.7f, result, "战斗",1000);
                    count = 0;
                }
                result = mFairy.findPic(891, 411, 1261, 699, "use.png");
                mFairy.onTap(0.7f, result, "使用",1000);

                result = mFairy.findPic(804, 201, 1266, 706,
                        new String[]{"confirm.png", "confirm1.png", "ok.png", "ok1.png"});
                mFairy.onTap(0.7f, result, "确定",1000);

                if (count == 9 || count == 12 || count == 15) {
                    result = mFairy.findPic(512, 0, 1274, 539,
                            new String[]{"close.png", "tux.png", "cjx.png", "meet.png", "close1.png", "close2.png"});
                    mFairy.onTap(0.7f, result, "关闭窗口",1000);
                    mFairy.ranSwipe(1114, 191, 1153, 438, 2, 1000,(long)2000); //上滑
                    LtLog.e("--------------------试炼没有找到上滑");
                } else if (count >18) {
                    bj=0;
                    sl++;
                    if(sl>2){
                        return;
                    }
                }
                count++;
            }
        }
    }//试炼

    public void catchPet() throws Exception {
        int chaos;
        String str = AtFairyConfig.getOption("chaos");
        if (str.equals("")) {
            chaos = -1;
        } else {
            chaos = Integer.parseInt(str);
        }

        int bj = 0, bj1 = 0;
        int count = 0, count1 = 0;
        int cs = 0, cs1 = 0;
         while (mFairy.condit()) {
            LtLog.e("捉宠卖钱 bj= " + bj);
            if (bj == 0) {
                result = mFairy.findPic(512, 0, 1274, 539,
                        new String[]{"close.png", "tux.png", "cjx.png", "meet.png", "close1.png", "close2.png"});
                mFairy.onTap(0.7f, result, "关闭窗口",1000);

                result = mFairy.findPic("tip.png");
                mFairy.onTap(0.8f, result, "Tip",1000);

                result = mFairy.findPic("rightTip.png");
                mFairy.onTap(0.8f, result, "Tip",1000);

                result = mFairy.findPic("click.png");
                mFairy.onTap(0.9f, result, "点击任意屏幕继续",1000);

                result = mFairy.findPic("click1.png");
                mFairy.onTap(0.9f, result, "点击任意屏幕跳过",1000);

                result = mFairy.findPic("task1.png");
                mFairy.onTap(0.7f,result,  1064, 125,1065,126, "任务",1000);

                result = mFairy.findPic("hangUp.png");
                if (result.sim > 0.8f) {
                    bj = 1;
                }

            }
            if (bj == 1) {

                result = mFairy.findPic("hangUp.png");
                mFairy.onTap(0.8f, result, "挂机",1000);

                result = mFairy.findPic("hdskUi.png");
                if (result.sim > 0.8f) {

                    if (chaos == -1) {
                        break;
                    } else {
                        gamePublicFuntion.hangUpTool(chaos, 0);
                    }
                }

                bj = 2;
            }


            if (bj == 2) {
                cs++;
                if (cs > 30) {
                    bj = 0;
                }

                Thread.sleep(1000);

                result = mFairy.findPic("auto.png");
                mFairy.onTap(0.7f, result, "取消自动",1000);
                 while (mFairy.condit()) {
                    result = mFairy.findPic("catch5.png");
                    if (result.sim > 0.8f) {
                        cs = 0;
                        result = mFairy.findPic("catch.png");
                        if (result.sim > 0.7f) {
                            mFairy.onTap(0.7f, result, "捕捉",1000);
                            Thread.sleep(1000);
                            mFairy.onTap(73, 334,74,335, "捉",1000);
                            mFairy.onTap(174, 302,175,303, "捉",1000);
                            mFairy.onTap(265, 245,266,246, "捉",1000);
                            mFairy.onTap(366, 202, 367,203,"捉",1000);
                            mFairy.onTap(472, 154,473,155, "捉",1000);
                            mFairy.onTap(202, 442,203,443, "捉",1000);
                            mFairy.onTap(326, 382, 327,383,"捉",1000);
                            mFairy.onTap(415, 337,416,338, "捉",1000);
                            mFairy.onTap(517, 290,518,291, "捉",1000);
                            mFairy.onTap(601, 240,602,241, "捉",1000);


                            Thread.sleep(2000);
                            count++;
                        } else {
                            result = mFairy.findPic("catch2.png");
                            mFairy.onTap(0.8f, result, "宠物防御",1000);
                        }

                        LtLog.e("      count :  " + count);

                    } else {

                        break;
                    }
                }
                if (count >= 10) {
                    gamePublicFuntion.mapButton();
                    bj = 3;
                }
            }

            if (bj == 3) {
                cs1++;
                if (cs1 > 50) {
                    bj = 0;
                }
                Thread.sleep(1000);

                result = mFairy.findPic("mapButton.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.7f, result, "地图",1000);
                    Thread.sleep(2000);
                    mFairy.onTap(142, 81,143,82, "当前地图",1000);
                    Thread.sleep(2000);
                    mFairy.onTap(905, 500,906,501, "宠物买卖",1000);
                    Thread.sleep(10000);
                }
                result = mFairy.findPic(959, 451, 1088, 673, "catch3.png");
                if (result.sim > 0.7f) {
                    cs1 = 0;
                    mFairy.onTap(0.7f, result, "宠物出售",1000);
                    Thread.sleep(3000);
                    for (int i = 0; i < 7; i++) {
                        result = mFairy.findPic(762, 135, 979, 579, "catch4.png");
                        mFairy.onTap(0.7f, result, "出售",1000);
                    }

                    result = mFairy.findPic("end6.png");
                    if (result.sim > 0.9f) {
                        LtLog.e("------   没有出售次数了，End!");
                        break;
                    }


                    count = 0;
                    bj = 0;
                }

            }

        }

    }//捉宠卖钱

    public void race() throws Exception {
        int bj = 0;
        int count = 0, count1 = 0;
         while (mFairy.condit()) {

            minute = mFairy.dateMinute();
            if (minute > 55) {
                break;
            }
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "bpspTu.png", 3, 2);
                if (bj == -1) {
                    break;
                }
                count = 0;
            }
            if (bj == 3) {
                count1++;
                if (count1 > 20) {
                    bj = 0;
                }
                result = mFairy.findPic(967, 456, 1077, 684, "bpspTack.png");
                mFairy.onTap(0.8f, result, "帮派赛跑",1000);

                result = mFairy.findPic("bpspUi.png");
                if (result.sim > 0.8f) {
                    count1 = 0;
                    if (count == 0) {
                        for (int i = 0; i < 3; i++) {
                            result = mFairy.findPic("bpspOne.png");
                            mFairy.onTap(0.9f, result, "第一个",1000);
                            result = mFairy.findPic("zhichi.png");
                            mFairy.onTap(0.9f, result, "支持",1000);
                        }
                        count = 1;
                    }
                }

            }
        }
    }//帮派赛跑

    public void answerZXQY() throws Exception {
        int bj = 0;
        int count = 0;

         while (mFairy.condit()) {
            LtLog.e("----------------------诛仙奇缘任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "zxqyTu.png", 0);
                if (bj == -1) {
                    break;
                }
                count = 0;

            }
            if (bj == 3) {
                result = mFairy.findPic("zxqyTack.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(268, 450,269,451,"点击",1000);
                    Thread.sleep(1000);
                }

                count++;
                if (count > 30) {
                    bj = 0;
                }
            }


        }
    }//诛仙奇缘

    public void answerQYXT() throws Exception {
        int bj = 0;
        int count = 0;


         while (mFairy.condit()) {
            LtLog.e("----------------------青云学堂任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "schoolTu.png", 0);
                if (bj == -1) {
                    break;
                }
                count = 0;
            }
            if (bj == 3) {
                result = mFairy.findPic("schoolUi.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(533, 412,534,413,"点",1000);
                    Thread.sleep(1000);
                }
                count++;
                if (count > 20) {
                    bj = 0;
                }
            }
        }


    }//青云学堂


    /**没有更新
     */
    public void answerKJ() throws Exception {
        int bj = 0;
        int count = 0;
        int count1 = 0;


         while (mFairy.condit()) {
            LtLog.e("----------------------科举考试任务bj=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "kjTu.png", 1);
                if (bj == -1) {
                    break;
                }
                count = 0;
            }
            if (bj == 3) {

                result = mFairy.findPic(222, 468, 1092, 586, "kjpi.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "参加",1000);
                } else {
                    count1++;
                    if (count1 > 1) {
                        break;
                    }
                }
                result = mFairy.findPic(1011, 448, 1099, 689, "kjTack.png");
                mFairy.onTap(0.8f, result, "答题",1000);

                result = mFairy.findPic("kjdt.png");
                if (result.sim > 0.8f) {
                    count = 0;
                    mFairy.onTap(384, 414,385,415,"点",1000);

                }
                count++;
                Thread.sleep(2000);
                if (count > 10) {
                    bj = 0;
                }

            }


        }
    }//科举考试



    public boolean qh()throws Exception{
        Abnormal.i=1;
        int bj=0;
         while (mFairy.condit()){
            if(bj==0){
                gamePublicFuntion.init(99);
                bj=1;
            }
            if(bj==1) {
                if (qh >= 2) {
                    LtLog.e("已经超过切换次数,End!");
                    qh = 0;
                    Abnormal.i = 0;
                    return false;
                }

                result = mFairy.findPic("qh2.png");
               mFairy.onTap(0.8f, result, "展开",1000);

                result = mFairy.findPic(607, 503, 1269, 707, "qh3.png");
                mFairy.onTap(0.8f, result, "设置",1000);

                result = mFairy.findPic("qh4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "更换角色",1000);
                    Thread.sleep(5000);
                }

                result = mFairy.findPic("qh5.png");
                LtLog.e(mFairy.getLineInfo("切换角色场景"));
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(783, 559, 923, 663, "qh1.png");
                    if (result.sim > 0.8f) {
                        LtLog.e("切换>>>>>");
                        mFairy.onTap(0.8f, result, result.x + 20, result.y - 100, result.x+21,result.y-99,"",1000);
                        Thread.sleep(3000);
                        result = mFairy.findPic("l.png");
                        mFairy.onTap(0.8f, result, "err登录游戏2",1000);
                        Abnormal.i = 0;
                        qh++;
                        return true;
                    } else {
                        LtLog.e("没有角色可以切换...");
                        result = mFairy.findPic("l.png");
                        mFairy.onTap(0.8f, result, "err登录游戏2",1000);
                        Abnormal.i = 0;
                        qh = 0;
                        return false;
                    }

                }
            }
        }
        return false;
    }//切换角色

    /*
        限时任务
     */
    public void hydf() throws Exception {
        int bj = 0;
        int count = 0;
        int bool;
         while (mFairy.condit()) {
            hour = mFairy.dateHour();
            minute = mFairy.dateMinute();
            if (hour == 22 && minute > 35) {
                break;
            }

            LtLog.e("----------------------幻月洞府=" + bj);
            if (bj == 0) {

                bj = gamePublicFuntion.initTools(bj, "hydfTu.png", 1, 3);
                if (bj == -1) {
                    break;
                }
                count=0;
            }
            if (bj == 3) {
                LtLog.e("count="+count);

                result = mFairy.findPic("zd.png");
                if (result.sim > 0.8f) {
                    count = 0;
                }
                result = mFairy.findPic(953, 432, 1080, 682,"hydfTack.png");
                mFairy.onTap(0.8f, result, "便捷组队",1000);

                result = mFairy.findPic(519, 576, 1074, 683,"zdpp.png");
                mFairy.onTap(0.8f, result, "自动匹配",1000);

                result = mFairy.findPic("ranksButton.png");
                mFairy.onTap(0.6f, result, "点击队伍",1000);
                mFairy.onTap(0.6f, result, "点击队伍",1000);



                result = mFairy.findPic("hydfUi.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("matching.png");
                    mFairy.onTap(0.9f, result, "开始匹配",1000);
                    count = 0;
                }

                result = mFairy.findPic("ranks.png");
                mFairy.onTap(0.8f,result, 1082,68,1083,69,"关闭",1000);

                result = mFairy.findPic(100, 3, 517, 126,"signOut1.png");
                if (result.sim > 0.7f) {
                    bj=4;
                }

                count++;
                if (count > 30) {
                    bj=0;
                    count=0;
                }


            }


            if(bj==4){


                result = mFairy.findPic("hydfdt.png");
                mFairy.onTap(0.8f,  result,470, 431, 471,432,"答题",1000);


                result = mFairy.findPic("hydfEnd.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(1236, 33,1237,34, "幻月洞府任务完成,End!",1000);
                    break;
                }

                result = mFairy.findPic("qwe9.png");
                if (result.sim > 0.8f) {
                    break;
                }

                gamePublicFuntion.inBattle();

            }


        }
    }//幻月洞府

    public void mptz() throws Exception {
        int bj = 0;
        int count = 0,count1=0;
         while (mFairy.condit()) {
            hour = mFairy.dateHour();
            if (hour == 21) {
                break;
            }


            LtLog.e("----------------------门派挑战任务=" + bj);
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "mpTu.png", 3);
                if (bj == -1) {
                    break;
                }
                count1=0;

            }
            if (bj == 3) {
                result = mFairy.findPic("zd.png");
                if (result.sim > 0.8f) {
                    count = 0;
                }

                result = mFairy.findPic(953, 432, 1080, 682,"mpzd.png");
                mFairy.onTap(0.8f, result, "便捷组队",1000);

                result = mFairy.findPic(519,576,1074,683,"zdpp.png");
                mFairy.onTap(0.8f, result, "自动匹配",1000);

                if(count1==0) {

                    result = mFairy.findPic("ranksButton.png");
                    mFairy.onTap(0.6f, result, "点击队伍",1000);
                    mFairy.onTap(0.6f, result, "点击队伍",1000);

                    result = mFairy.findPic("mpUi.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("matching.png");
                        mFairy.onTap(0.9f, result, "开始匹配",1000);
                        count = 0;
                    }
                }

                result = mFairy.findPic("ranks.png");
                mFairy.onTap(0.8f, result,1082,68,1083,69, "关闭",1000);

                result = mFairy.findPic("auto1.png");
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.7f, result, "自动战斗",1000);
                } else {
                     while (mFairy.condit()) {
                        result = mFairy.findPic("auto.png");
                        if (result.sim > 0.7f) {
                            count1=1;
                            count = 0;
                            LtLog.e("战斗中....");
                            Thread.sleep(3000);
                        } else {
                            break;
                        }
                    }
                }

                count++;
                if (count > 66) {
                    bj = 0;
                    count = 0;
                }

            }


        }
    }//门派挑战

    public int xkzc() throws Exception {
        int bj = 0;
        int count = 0;
         while (mFairy.condit()) {
            if (bj == 0) {
                result = mFairy.findPic("xkzcEnd.png");
                mFairy.onTap(0.8f,result, 638, 662,639,663,  "战斗评分显示,确定",1000);


                bj = gamePublicFuntion.initTools(bj, "xkzcTu.png", 1);
                if (bj == -1) {
                    return -1;
                }
                count = 0;
            }

            if (bj == 3) {
                count++;
                if (count > 60) {
                    bj = 0;
                }
                result = mFairy.findPic(930, 439, 1108, 692,"xkzcTack.png");
                mFairy.onTap(0.8f, result, "虚空战场",1000);
                Thread.sleep(2000);
                result = mFairy.findPic("xkzcPP.png");
                mFairy.onTap(0.8f, result, "单人匹配",1000);

                gamePublicFuntion.inBattle();

                 while (mFairy.condit()) {
                    result = mFairy.findPic("xkzcPP1.png");
                    if (result.sim > 0.8f) {
                        Thread.sleep(3000);
                    } else {
                        break;
                    }
                }
                result = mFairy.findPic("xkzctk.png");
                if (result.sim > 0.8f) {
                    count = 0;
                    mFairy.onTap(444, 685,445,686, "点",1000);
                    Thread.sleep(3000);
                    mFairy.onTap(984, 104,985,105, "点",1000);
                    Thread.sleep(3000);
                }


                result = mFairy.findPic("xkzcVictory.png");
                mFairy.onTap(0.8f, result,630, 658, 631,659, "战斗结束",1000);

                result = mFairy.findPic("xkzcEnd.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(638, 662, 639,663,"确定",1000);
                    LtLog.e("虚空战场结束,End!");
                    return 0;

                }

            }


        }
        return 0;
    }//虚空战场

    public void qmhw() throws Exception {
        int bj = 0;
         while (mFairy.condit()) {
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "qmhwTu.png", 1);
                if (bj == -1) {
                    break;
                }
            }
            if (bj == 3) {
                result = mFairy.findPic(922, 436, 1123, 685,"qmhw1.png");
                mFairy.onTap(0.8f, result, "我要去参加",1000);

                result = mFairy.findPic("qmhw2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "七脉会武",1000);
                    mFairy.onTap(313, 452, 314,453,"首胜奖励",1000);
                    mFairy.onTap(483, 463, 494,464,"五战奖励",1000);
                }


                gamePublicFuntion.inBattle();

                int hour = mFairy.dateHour();
                int minute = mFairy.dateMinute();
                if (hour == 21 && minute > 25) {

                    break;
                }

            }


        }
    }//七脉会武

    public void jyjx() throws Exception {
        int bj = 0;
        int count = 0, count1 = 0;
        int bool;
        int a = 117;
        int hour;
        int minute;
         while (mFairy.condit()) {
            hour = mFairy.dateHour();
            minute = mFairy.dateMinute();
            if (hour == 15 && minute > 30) {
                break;
            }


            LtLog.e("----------------------精英九霄=" + bj);
            if (bj == 0) {
                bool = gamePublicFuntion.see("jyjx1.png");

                bj = gamePublicFuntion.initTools(bj, "jyjxTu.png", bool);
                if (bj == -1) {
                    break;
                }
                count1 = 0;
            }

            if (bj == 3) {
                count++;
                if (count > 20) {
                    bj = 0;
                }
                result = mFairy.findPic(907, 451, 982, 686,"jyjx2.png");
                mFairy.onTap(0.8f, result, "参加",1000);

                result = mFairy.findPic("xszd.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "组队",1000);
                    count = 0;
                }
                result = mFairy.findPic("hdzd.png");
                if (result.sim > 0.7f) {
                    LtLog.e("活动组队");
                    count = 0;
                    result = mFairy.findPic("xsdw.png");
                    mFairy.onTap(0.8f, result, "找队伍",1000);

                    for (int i = 0; i < 4; i++) {
                        mFairy.onTap(531, 150 + (i * a), 532,151 + (i * a),"申请",1000);
                        mFairy.onTap(975, 150 + (i * a), 976,151 + (i * a),"申请",1000);
                    }
                    result = mFairy.findPic("sx.png");
                    mFairy.onTap(0.8f, result, "刷新",1000);
                    count1++;
                    if (count1 > 20) {
                        result = mFairy.findPic(512, 0, 1274, 539,"close.png");
                        mFairy.onTap(0.7f, result, "关闭",1000);
                        count1 = 0;
                    }
                }

                result = mFairy.findPic("dwyq.png");
                mFairy.onTap(0.8f, result, 762, 422, 763,423,"err队伍邀请-点击同意",1000);

                result = mFairy.findPic(148, 81, 336, 172,"xsUi.png");
                if (result.sim > 0.8f) {
                    bj = 4;
                }
            }


            if (bj == 4) {
                gamePublicFuntion.inBattle();
                result = mFairy.findPic("qwe9.png");
                if (result.sim > 0.8f) {
                    LtLog.e("精英九霄完成,End!");
                    break;
                }
            }

        }
    }//精英九霄

    public void jzjx() throws Exception {
        int bj = 0;
        int count = 0, count1 = 0;
        int bool;
        int a = 117;
        int week;
        int hour;
        int minute;
         while (mFairy.condit()) {
            week = mFairy.week();
            hour = mFairy.dateHour();
            minute = mFairy.dateMinute();
            if (week == 1) {
                if (hour == 21 && minute > 5) {
                    break;
                }
            } else {
                if (hour == 22) {
                    break;
                }


            }

            LtLog.e("----------------------决战九霄=" + bj);
            if (bj == 0) {
                bool = gamePublicFuntion.see("jzjx1.png");

                bj = gamePublicFuntion.initTools(bj, "jzjxTu.png", bool);
                if (bj == -1) {
                    break;
                }
                count1 = 0;
            }

            if (bj == 3) {
                count++;
                if (count > 20) {
                    bj = 0;
                }
                result = mFairy.findPic(926, 449, 1014, 679,"jyjx2.png");
                mFairy.onTap(0.8f, result, "参加",1000);

                result = mFairy.findPic("xszd.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "组队",1000);
                    count = 0;
                }
                result = mFairy.findPic("hdzd.png");
                if (result.sim > 0.7f) {
                    LtLog.e("活动组队");
                    count = 0;
                    result = mFairy.findPic("xsdw.png");
                    mFairy.onTap(0.8f, result, "找队伍",1000);

                    for (int i = 0; i < 4; i++) {
                        mFairy.onTap(531, 150 + (i * a), 532, 151 + (i * a),"申请",1000);
                        mFairy.onTap(531, 150 + (i * a), 532, 151 + (i * a),"申请",1000);
                    }
                    result = mFairy.findPic("sx.png");
                    mFairy.onTap(0.8f, result, "刷新",1000);
                    count1++;
                    if (count1 > 20) {
                        result = mFairy.findPic(512, 0, 1274, 539,"close.png");
                        mFairy.onTap(0.7f, result, "关闭",1000);
                        count1 = 0;
                    }
                }

                result = mFairy.findPic("dwyq.png");
                mFairy.onTap(0.8f, result,762, 422,  763,423,"err队伍邀请-点击同意",1000);

                result = mFairy.findPic(148, 81, 336, 172,"xsUi.png");
                if (result.sim > 0.8f) {
                    bj = 4;
                }
            }


            if (bj == 4) {
                gamePublicFuntion.inBattle();
                result = mFairy.findPic("qwe9.png");
                if (result.sim > 0.8f) {
                    LtLog.e("决战九霄完成,End!");
                    break;
                }
            }

        }
    }//决战九霄

    /***
     *
     * @throws Exception
     */
    public void xzcs() throws Exception {
        int bj = 0;
        int count = 0;
         while (mFairy.condit()) {
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "xzcsTu.png", 0);
                if (bj == -1) {
                    break;
                }
            }
            if (bj == 3) {
                result = mFairy.findPic("xzcs1.png");
                mFairy.onTap(0.9f,result,  439, 259, 440,260,"自动战斗",1000);
                mFairy.onTap(0.9f, result, "确定更换",1000);

                int minute = mFairy.dateMinute();
                if (minute > 5) {
                    break;
                }


            }

        }
    }//十二星座传说

    public void boss() throws Exception {
        int bj = 0;
        int count = 0;
         while (mFairy.condit()) {
            LtLog.e("----------------------世界BOSS任务bj=" + bj);

            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "bossTu.png", 0);
                if (bj == -1) {
                    break;
                }
            }

            if (bj == 3) {
                if (count > 50) {
                    bj = 0;
                }

                result = mFairy.findPic("bossUi.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f,result, 949, 630,  950,631,"挑战",1000);

                    result = mFairy.findPic("end4.png");
                    if (result.sim > 0.9f) {
                        LtLog.e("-----    没有次数了,End!");
                        break;
                    }

                } else {
                    count++;
                }

                gamePublicFuntion.inBattle();


            }
        }
    }//世界Boss

    public void zb()throws Exception{

        int bj = 0;int count=0;
         while (mFairy.condit()) {
            if (bj == 0) {
                bj = gamePublicFuntion.initTools(bj, "zb.png", 1);
                if (bj == -1) {
                    break;
                }
            }
            if (bj == 3) {
                result = mFairy.findPic(872,351,1153,660,"zb1.png");
                mFairy.onTap(0.8f, result, "我要去参加",1000);

                result = mFairy.findPic("zb1.png");
                if (result.sim > 0.8f) {
                    count=0;
                }
                result = mFairy.findPic("auto1.png");
                if (result.sim > 0.7f) {
                    count=0;
                    mFairy.onTap(0.7f, result, "自动战斗",1000);
                } else {
                     while (mFairy.condit()) {
                        result = mFairy.findPic("auto.png");
                        if (result.sim > 0.7f) {
                            count=0;
                            LtLog.e("战斗中....");
                            Thread.sleep(3000);
                        } else {
                            break;
                        }
                    }
                }

                int hour = mFairy.dateHour();
                int minute = mFairy.dateMinute();
                if (hour == 10 && minute > 5) {
                    break;
                }
                count++;
                Thread.sleep(1000);
                if(count>30){
                    bj=0;
                    count=0;
                }
            }


        }



    }//首席争霸



}





