package com.script.fairy;

import android.graphics.Bitmap;
import android.util.Base64;

import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class GameUtil extends TaskContent {
    private static AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    FindResult result2;
    FindResult result3;
    TimingActivity timingActivity;

    public GameUtil(AtFairyImpl ATFairy) throws Exception {
        mFairy = ATFairy;
        Sleep=1500;
        SleepWhile=1000;
    }
    public void inOperation() throws Exception {
        result = mFairy.findPic(1111,3,1274,41,new String[]{"tiaoguo4.png","tiaoguo2.png","tiaoguo3.png"});
        mFairy.onTap(0.8f, result, "跳过", 1000);
        result = mFairy.findPic("smOverGraph.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("过图中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic("In transmission.png");
        if (result.sim > 0.7f) {
            LtLog.e(mFairy.getLineInfo("传送中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic("road.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("寻路中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic(188, 533, 369, 592, "complete.png");
        mFairy.onTap(0.8f, result, "完成", Sleep);
        if (result.sim > 0.8f) {
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic(188, 533, 369, 592, "accept.png");
        mFairy.onTap(0.8f, result, "接受", Sleep);
        if (result.sim > 0.8f) {
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
    }

    int tastState = 0;
    int rwsum=0;
    public int mission(final String str, final int option) throws Exception {
        new GameUtil(mFairy) {
            int findtask = 0,sum=0,zl=0;

            public void content_0() throws Exception {
                if(str.equals("3v3Arena.png") || str=="3v3Arena.png")
                {
                    findtask+=2;
                }
                if (findtask >= 3 || rwsum>=3) {
                    LtLog.e("没有这个任务");
                    GameUtil.this.tastState = 0;
                    setTaskEnd();
                }
/*
                if (str.equals("qxTreasure1.png") || str.equals("qxTreasure.png")){
                    close(0);
                }else{
                    close(1);
                }
*/
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) {
                    close(1);
                    return;
                }
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();

                result1 = mFairy.findPic(883,21,1127,219,"bb.png");
                result = mFairy.findPic(796,8,1115,157,"daily.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "日常", Sleep);
                }else if (result1.sim>0.8f){
                    sum++;
                }

                if (sum>=5) {
                    LtLog.e("没有日常");
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(1116,4,1279,33,"cfd2.png");
                if (result.sim > 0.8f) {
                    //千里良驹 12:00-15 18:00-15
                    if (AtFairyConfig.getOption("qllj").equals("1") && timingActivity.qllj && (h == 12 || h == 18) && m < 13) {
                        timingActivity.paoma();
                        timingActivity.paoma();
                        timingActivity.qllj = false;
                    }else{
                        result = mFairy.findPic(839, 5, 1277, 125, new String[]{"3v3leave.png", "3v3leave1.png"});
                        mFairy.onTap(0.8f, result, "退出副本", Sleep);
                    }
                    return;
                }

                result = mFairy.findPic(749,66,1171,158,new String[]{"Activeinterface.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常界面"));
                if (result.sim > 0.8f) {
                    Thread.sleep(2000);
                    result = mFairy.findPic("jlred.png");
                    mFairy.onTap(0.8f, result, 378,100,409,111,"领取活跃奖励栏", Sleep);
                    if (result.sim>0.8f){
                        mFairy.onTap(881,288,895,304,"领取活跃奖励1", Sleep);
                        for (int i=0;i<5;i++){
                            result =mFairy.findPic(340,223,927,624,"smSure.png");
                            mFairy.onTap(0.8f,result,"奖励确认",Sleep);
                            result = mFairy.findPic(799,184,1179,659,"jlred1.png");
                            mFairy.onTap(0.8f, result, result.x-30,result.y+20,result.x-29,result.y+21,"领取活跃奖励2", Sleep);
                            if (result.sim>0.8f){
                                i=0;
                            }
                        }
                    }
                    mFairy.condit();
                    result = mFairy.findPic(209,132,862,592, str);
                    if (result.sim > 0.8f) {
                    } else {
                        if (option == 1) {
                            mFairy.onTap(528, 99, 563, 113, "日常活动", Sleep);
                        }
                        if (option == 2) {
                            mFairy.onTap(691, 100, 722, 114, "限时活动", Sleep);
                        }
                        if (option == 3) {
                            mFairy.onTap(1005, 96, 1044, 117, "周历", Sleep);
                            findtask++;
                            setTaskName(3);
                            return;
                        }
                        if (option == 4) {
                            mFairy.onTap(375, 101, 410, 117, "活跃奖励", Sleep);
                        }
                    }
                    findtask++;
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(15, 0)){
                    rwsum++;
                    close(1);
                    return;
                }
                result = mFairy.findPic(749,66,1171,158,new String[]{"Activeinterface.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常界面"));
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic(208,139,858,591, str);
                    LtLog.e("找到了任务=" + str + "," + result1.sim);
                    if (result1.sim > 0.8f) {
                        mFairy.onTap(0.8f, result1, "任务点击", Sleep);


                        result = mFairy.findPic(853,553,1205,663, "smGoTo3.png");
                        /* result = mFairy.findPic(result1.x + 100, result1.y + 36, result1.x + 200, result1.y + 80, new String[]{"smGoTo.png","smGoTo1.png"});*/
                        if (str.equals("chirdsl.png")){
                            mFairy.onTap(0.8f, result, "日常前往", 5000);
                        }else {
                            mFairy.onTap(0.8f, result, "日常前往", Sleep);
                        }
                        if (result.sim >= 0.8f) {
                            if (!str.equals("Farm.png") && !str.equals("Praying.png")&& !str.equals("kjExamination.png")){
                                result = mFairy.findPic(749,66,1171,158,new String[]{"Activeinterface.png"});
                                mFairy.onTap(0.8f, result, 1191, 92, 1209, 108, "前往关闭", Sleep);
                            }
                            GameUtil.this.tastState = 1;
                            setTaskEnd();
                            return;
                        }
                        result = mFairy.findPic(result1.x + 97, result1.y + 36, result1.x + 200, result1.y + 80, new  String[]{"rcComplete.png","kqsj.png","rcComplete1.png"});
                        mFairy.onTap(0.8f, result, 1191, 92, 1209, 108, "日常完成", Sleep);
                        if (result.sim >= 0.8f) {
                            GameUtil.this.tastState = 0;
                            setTaskEnd();
                            return;
                        }
                    }
                    mFairy.taskSlid(err, new int[]{0, 2, 4, 6, 8 , 10}, 0, 531, 586, 532, 185, 1000, 1500,2);
                }
            }
            public void content_3() throws Exception {

                result = mFairy.findPic(749,66,1171,158,new String[]{"Activeinterface.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常界面"));
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(227,573,1195,650, new String[]{"smGoTo.png","smGoTo1.png","zlqw.png",});
                    mFairy.onTap(0.8f, result, "周历前往", Sleep);
                    if (result.sim >= 0.8f) {
                        result = mFairy.findPic(749,66,1171,158,new String[]{"Activeinterface.png"});
                        mFairy.onTap(0.8f, result, 1191, 92, 1209, 108, "前往关闭", Sleep);
                        GameUtil.this.tastState = 1;
                        zl=0;
                        setTaskEnd();
                        return;
                    }else {
                        zl++;
                    }
                    if (zl>=10){
                        GameUtil.this.tastState = 0;
                        setTaskEnd();
                        return;
                    }
                }
            }
        }.taskContent(mFairy, "找任务中");
        return tastState;
    }//开始任务

    public int tymission(final String str) throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png","bahuang.png","bh.png"});
                mFairy.onTap(0.8f, result, "天外山海图标", Sleep);
                result=mFairy.findPic(239,452,1075,659,"smzd2.png");
                mFairy.onTap(0.8f,result,"神魔之巅",1000);
                result=mFairy.findPic(239,452,1075,659,"smzd3.png");
                mFairy.onTap(0.8f,result,"神魔之巅",1000);

                result = mFairy.findPic("twsh_inface.png");
                mFairy.onTap(0.8f, result, 1133,343,1152,360, "天外山海界面切换到天外活动", 3000);

                result = mFairy.findPic("twsh_inface.png");
                mFairy.onTap(0.8f, result, 1133,343,1152,360, "天外山海界面切换到天外活动", 3000);

                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic(70,208,278,648, str);
                    LtLog.e("找到了任务=" + str + "," + result1.sim);
                    if (result1.sim > 0.8f) {
                        result = mFairy.findPic(result1.x + 776, result1.y + -20, result1.x + 928, result1.y + 52, "twqw.png");
                        mFairy.onTap(0.8f, result, "天外前往", Sleep);
                        if (result.sim >= 0.8f) {
                            GameUtil.this.tastState = 1;
                            setTaskEnd();
                            return;
                        }
                    }else {
                        GameUtil.this.tastState = 0;
                        setTaskEnd();
                        return;
                    }
                }
            }
        }.taskContent(mFairy, "天外找任务中");
        return tastState;
    }//天外找任务中

    public void close(final int close) throws Exception {
        new GameUtil(mFairy) {
            public void inOperation() throws Exception {
                super.inOperation();
            }
            int j = 1;
            public void content_0() throws Exception {
                /*if (Thread.currentThread().isInterrupted()){
                    throw new InterruptedException();
                }*/

                result = mFairy.findPic(427,269,854,490,"sfcl.png");
                if(result.sim > 0.8f) {
                    while(true) {
                        LtLog.e("账号被顶  等待。。。。。。。。。。");
                        mFairy.sleep(300000);
                        result = mFairy.findPic(427,269,854,490,"sfcl.png");
                        if(result.sim < 0.8f) {
                            LtLog.e("恢复。。。。。。。。。。");
                            setTaskEnd();
                            return;
                        }
                    }
                }

                for (int i = 0; i < j; i++) {
                    /*mFairy.condit();*/
                    if (close == 1) {

                        result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle3.png", "NoBattle4.png"});
                        if (result.sim > 0.8f){
                            mFairy.onTap(1123,327,1134,339, "关闭自动战斗", Sleep);
                        }
                    }

                    result = mFairy.findPic("shouhui.png");
                    mFairy.onTap(0.8f, result, "收回扩展列表", Sleep);

                    result = mFairy.findPic(3,632,67,708,"kzl.png");
                    mFairy.onTap(0.8f, result, "收回下扩展列表", Sleep);

                    result = mFairy.findPic("leave.png");
                    mFairy.onTap(0.8f, result, "离开", 500);

                    result =mFairy.findPic(611,234,1271,572,"pipeizhong.png");
                    mFairy.onTap(0.8f, result, "匹配中", Sleep);

                    result =mFairy.findPic("matching.png");
                    mFairy.onTap(0.8f, result, "匹配中", Sleep);

                    result =mFairy.findPic(372,591,559,663,"tcyx.png");
                    mFairy.onTap(0.8f, result, "退出游戏", Sleep);

                    result =mFairy.findPic(515,323,795,380,"tcbc.png");
                    mFairy.onTap(0.8f, result, 771,435,782,442,"退出游戏", Sleep);

                    result =mFairy.findPic("jybattle.png");
                    mFairy.onTap(0.8f,result,489,430,520,443,"离开队列",Sleep);

                    result=mFairy.findPic("zdpt.png");
                    mFairy.onTap(0.8f,result,626,582,632,587,"组队平台收回",500);

                    result=mFairy.findPic(573,235,688,423,"shouhui.png");
                    mFairy.onTap(0.8f,result,"收回",500);

                    result = mFairy.findPic(1161,295,1276,403, new String[]{"lk.png","lk2.png"});
                    mFairy.onTap(0.8f, result, 1220,337,1231,347,"离开庄园", Sleep);
                    mFairy.onTap(0.8f, result, 767,432,782,444,"确定离开", Sleep);

                    result=mFairy.findPic(644,322,897,484,"lkzy1.png");
                    mFairy.onTap(0.8f,result,771,438,781,442,"离开庄园",2000);
                    mFairy.onTap(0.8f,result,770,441,780,446,"离开庄园确定",500);

                    result=mFairy.findPic(858,99,1050,266,"chacha.png");
                    mFairy.onTap(0.8f,result,"关前叉",300);

                    result=mFairy.findPic("fork2.png");
                    mFairy.onTap(0.8f,result,"fork2关叉",300);

                    result=mFairy.findPic(86,77,187,176,"cha7.png");
                    mFairy.onTap(0.8f,result,"叉7关叉",300);

                    result = mFairy.findPic(919,73,1073,202, new String[]{"fork.png","fork1.png"});
                    mFairy.onTap(0.8f, result, "关叉1", 300);

                    result1=mFairy.findPic(1031,4,1275,171,new String[]{"cha21.png","cha10.png","bxcha.png","bxcha1.png","fxcha.png","shcha.png","rxcha.png","rxcha1.png"});
                    mFairy.onTap(0.9f,result1,"关叉2",300);

                    result = mFairy.findPic(443,224,1108,533, new String[]{"cha16.png","zuocecha.png","cha3.png","cha4.png","cha6.png","cha2.png","cha.png","quxiao.png","cha5.png"});//"ymzjcha.png",
                    mFairy.onTap(0.8f, result, "关叉3", 300);

                    result2 = mFairy.findPic(427,6,1275,311, new String[]{"cha01.png","hdcha1.png","cha20.png","cha19.png","cha18.png","cha17.png","cha8.png","chawq.png"});//"ymzjcha.png",
                    mFairy.onTap(0.8f, result2, "关叉4", 300);


                    if (result.sim > 0.8f || result1.sim > 0.8f || result2.sim > 0.8f) {
                        j = 5;
                    } else {
                        j = 1;
                    }
                    result =mFairy.findPic("xiulian.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("挂机修炼了移动一下"));
                        mFairy.ranSwipe(173,569,176,493,100, (long) 100,2);
                    }
                }
                setTaskEnd();
                return;
            }
        }.taskContent(mFairy, "------------------------------------------------------------------------关叉中");
    }//关叉

    public void setUp() throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic("visualAngle.png");
                mFairy.onTap(0.8f, result, "锁定视角", Sleep);

                result = mFairy.findPic(1,635,65,698,"Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result = mFairy.findPic(1086,628,1161,708, "Set up.png");
                mFairy.onTap(0.8f, result, "设置", Sleep);


                result = mFairy.findPic(60,3,318,86,"Set up inface.png");
                mFairy.onTap(0.8f, result, 1211,140,1224,152, "设置界面", Sleep);
                if (result.sim>=0.8f){
                 /*   result = mFairy.findPic("jineng.png");
                    mFairy.onTap(0.8f, result, "技能", Sleep);*/
                    result = mFairy.findPic("zhengdian.png");
                    mFairy.onTap(0.8f, result, "挣点", Sleep);
                    setTaskName(2);return;
                }

            }

            public void content_2() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(new String[]{"Set up inface.png","set up2.png"});
                mFairy.onTap(0.8f, result, 1210,230,1218,237, "设置界面", Sleep);
                if (result.sim > 0.75f) {
                    mFairy.onTap(0.8f, result, 766, 164, 799, 172, "性能优先", Sleep);
                }
                result1 = mFairy.findPic(709,284,774,336,"wkqzp.png");
                if (result1.sim > 0.75f) {
                    mFairy.onTap(0.75f,result1,738,306,746,312,"开启战袍模式",2000);

                   /* result1 = mFairy.findPic("kqzp.png");
                    mFairy.onTap(0.8f, result1,  "开启战袍", Sleep);*/
                    result1 = mFairy.findPic(377,272,905,480,"zpms.png");
                    if (result1.sim > 0.75f) {
                        mFairy.onTap(0.75f, result1, 775,435,783,441, "开启战袍确认", Sleep);
                    }
                }
                if (result.sim > 0.8f) {
                    close(0);
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy, "设置中");
    }//设置

    public void huimeng() throws Exception {
        new GameUtil(mFairy) {
            int num =4;
            public void content_0() throws Exception {
                close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic("visualAngle.png");
                mFairy.onTap(0.8f, result, "锁定视角", Sleep);

                result = mFairy.findPic(1,631,91,712,"Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result = mFairy.findPic(1087,431,1272,608, "tuteng.png");
                mFairy.onTap(0.8f, result, "点图腾回盟", Sleep);
                result1 = mFairy.findPic(1099, 277, 1173, 348, "NoBattle.png");
                if (result1.sim > 0.8f && result.sim < 0.8f){
                    mFairy.onTap(27,663,37,670,"下面扩展栏",2000);
                }
                if (result.sim > 0.8f){
                    num--;
                }

                result = mFairy.findPic("yes.png");
                mFairy.onTap(0.8f, result, "确定", Sleep);

                result = mFairy.findPic("tuteng2.png");
                mFairy.onTap(0.8f, result, 990,125,1001,132,"关闭", Sleep);

                result = mFairy.findPic("xmld.png");
                if (result.sim > 0.8f || num <=0) {
                    LtLog.e("再血盟里了");
                    setTaskEnd();
                    return;
                }
                result2 = mFairy.findPic(1087,431,1272,608, "tuteng.png");
                result = mFairy.findPic( "city.png");
                result1 = mFairy.findPic(93, 537, 1269, 713, "Set up.png");
                if (result.sim > 0.8f && result1.sim > 0.8f && result2.sim < 0.8f){
                    LtLog.e("没用加入血盟回轩辕城");
                    goCity("轩辕");
                    setTaskEnd();
                    return;
                }

            }

        }.taskContent(mFairy, "回盟");
    }//回盟

    int cityCount;
    int x;

public int goCity(final String str) throws Exception {
    new GameUtil(mFairy) {
        int shjdt=0;

        public void content_0() throws Exception {
            close(1);
            if (str.equals("轩辕")) {
                result =mFairy.findPic(1108,3,1277,34,new String[]{"xmld.png","xyc.png"});
                if (result.sim>0.8f){
                    LtLog.e(mFairy.getLineInfo("轩辕或血盟中"));
                    GameUtil.this.cityCount = 0;
                    setTaskEnd();
                    return;
                }
            }
            if (str.equals("天外噬灵渊") || str.equals("天外霜火岭") || str.equals("天外焚天台")) {
                setTaskName(7);
                return;
            }
            if (str.equals("氏族地宫")) {
                setTaskName(7);return;
            }
            if (str.equals("山海界")) {
                setTaskName(2);return;
            }
            setTaskName(5);
            return;
        }

        public void content_1() throws Exception {
            if (overtime(4, 2)) return;
            long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
            LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

            if (dazeTime > 5) {
                result = mFairy.findPic(1112,2,1280,37,new String[]{"panguld.png","panguld.png","panguld.png","panguld.png","panguld.png"});
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(1062,121,1280,326,"huidaobf.png");
                    mFairy.onTap(0.8f, result, "回到本服", Sleep);
                }else{
                    result = mFairy.findPic("Lower expansion.png");
                    mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                    result = mFairy.findPic(816,444,1163,546,new String[]{"qhTotems.png", "tuteng.png","backcity.png"});
                    mFairy.onTap(0.8f, result, "图腾", Sleep);

                    result = mFairy.findPic("Deliverysure.png");
                    mFairy.onTap(0.8f, result, "传送确定", 10000);
                    if (result.sim > 0.8f) {
                        setTaskName(6);return;
                    }
                }
            }
        }

        public void content_2() throws Exception {
            if (overtime(15, 0)) return;
            result = mFairy.findPic( "tut.png");
            mFairy.onTap(0.8f, result, 990,121,1001,130, "关闭图腾", Sleep);

            if (str.equals("氏族地宫")) {
                close(1);
                setTaskName(7);return;
            }

            if (str.equals("山海界")) {
                result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png","bahuang.png","bh.png"});
                mFairy.onTap(0.8f, result,  "八荒", 3500);

                result = mFairy.findPic(51,537,243,696, "xysh1.png");
                mFairy.onTap(0.8f, result,  "轩辕山海", 3000);
            }

            result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
            mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);

            result1 = mFairy.findPic("twsh_inface.png");
            result = mFairy.findPic(new String[]{"hcWorld.png"});
            mFairy.onTap(0.8f, result, "切换到世界", Sleep);
            if (result.sim > 0.8f || result1.sim > 0.8f) {
                switch (str) {
                    case "轩辕":
                        //mFairy.onTap(533, 292, 548, 318, str, Sleep);
                        break;
                    case "青木":
                        mFairy.onTap( 1039, 342, 1057, 360, str, Sleep);
                        break;
                    case "苍穹海":
                        mFairy.onTap(779, 585, 795, 599, str, Sleep);
                        break;
                    case "昆仑墟":
                        mFairy.onTap(376, 272, 398, 287, str, Sleep);
                        break;
                    case "孽龙囚":
                        mFairy.onTap( 501, 132, 519, 145, str, Sleep);
                        break;
                    case "葬魂谷":
                        mFairy.onTap( 1024, 204, 1038, 219, str, Sleep);
                        break;
                    case "霜火岭":
                        mFairy.onTap(1076, 497, 1091, 511, str, Sleep);
                        break;
                    case "噬灵渊":
                        mFairy.onTap( 1183, 254, 1198, 268, str, Sleep);
                        break;
                    case "天外噬灵渊":
                        mFairy.onTap(902, 370, 903, 371, str, Sleep);
                        break;
                    case "天外霜火岭":
                        mFairy.onTap( 722, 433, 723, 434, str, Sleep);
                        break;
                    case "焚天台":
                        mFairy.onTap(341, 107, 360, 124, str, Sleep);
                        break;
                    case "天外焚天台":
                        mFairy.onTap( 948, 459, 949, 460, str, Sleep);
                        break;
                    case "山海界":
                        switch (Integer.parseInt(AtFairyConfig.getOption("xzdt"))) {
                            case 121:
                                mFairy.onTap(335,533,340,541, "甘洲", Sleep);
                                break;
                            case 122:
                                mFairy.onTap(578,479,586,487, "神墟", Sleep);
                                break;
                            case 123:
                                mFairy.onTap(725,430,735,438, "大塾", Sleep);
                                break;
                            case 124:
                                mFairy.onTap(526,155,537,168, "炎居", Sleep);
                                break;
                            case 125:
                                mFairy.onTap(854,242,866,259, "焰狱", Sleep);
                                break;
                            case 126:
                                mFairy.onTap(980,460,989,470, "白宫", Sleep);
                                break;
                            case 127:
                                mFairy.onTap(937,591,943,597, "彼岸", Sleep);
                                break;
                            case 128:
                                mFairy.onTap(366,213,379,228, "光阴", Sleep);
                                break;
                            case 129:
                                mFairy.onTap(273,352,279,361, "春祭", Sleep);
                                break;
                            case 130:
                                mFairy.onTap(1028,289,1036,299, "雷鸣", Sleep);
                                break;
                            case 131:
                                mFairy.onTap(718,171,721,181, "黑白", Sleep);
                                break;
                            case 132:
                                mFairy.onTap(1025,159,1029,171, "战殇", Sleep);
                                break;
                            case 133:
                                mFairy.onTap(202,229,206,236, "银杏", Sleep);
                                break;
                            case 134:
                                mFairy.onTap(1074,585,1094,600, "苗疆", Sleep);
                                break;
                            case 135:
                                mFairy.onTap(622,592,640,615, "镜花", Sleep);
                                break;
                        }
                        break;
                }
                mFairy.sleep(1000);
                if (str.equals("轩辕")){

                }else {
                    mFairy.onTap(759, 431, 795, 448, "确定传送", 2000);
                }



                result = mFairy.findPic(new  String[]{"szdgzhong.png","weimianzhong.png","weimianzhong1.png"});
                if (result.sim>0.8f) {
                    close(1);
                    LtLog.e(mFairy.getLineInfo("位面中"));
                    setTaskName(5);return;
                }

                result = mFairy.findPic(46,370,494,473,"yanju.png");
                mFairy.onTap(0.8f, result, "前往炎居", Sleep);

                result = mFairy.findPic(57,377,489,505,"qwdt.png");
                mFairy.onTap(0.8f, result, "前往地图", Sleep);
                setTaskName(3);
                return;
            }
        }

        public void content_3() throws Exception {
            result = mFairy.findPic(199,67,1229,272, new String[]{"fenxian.png","fenxian1.png"});
            result1 = mFairy.findPic(564,521,900,646,"fenxian2.png");
            if (result.sim > 0.8f || result1.sim > 0.8f){
                mFairy.initMatTime();
                result = mFairy.findPic(374,166,841,538, new String[]{"liuchang.png","fanmang.png"});
                mFairy.onTap(0.9f, result,  "选择分线", 1000);
                mFairy.onTap(0.9f, result, 740,584,750,596, "进入地图", 3000);
            }else {
                result1 = mFairy.findPic("twsh_inface.png");
                result = mFairy.findPic("quyu.png");
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    close(1);
                    LtLog.e(mFairy.getLineInfo("已经在目标地图了或没有目标地图"));
                    GameUtil.this.cityCount = 0;
                    setTaskEnd();
                    return;
                }
            }



            result = mFairy.findPic(57,377,489,505,"qwdt.png");
            if (result.sim > 0.8f) {
                mFairy.initMatTime();
                mFairy.onTap(0.8f, result, "前往地图", 3000);
            }

            result = mFairy.findPic(57,377,489,505,"qwdt.png");
            if (result.sim > 0.8f) {
                mFairy.initMatTime();
                mFairy.onTap(0.8f, result, "前往地图", 3000);
            }


            long dazeTime = mFairy.mMatTime(1212,129,67,16, 0.9f);
            if (dazeTime > 5) {
                mFairy.initMatTime();
                LtLog.e(mFairy.getLineInfo("到达目的地"));
                GameUtil.this.cityCount = 1;
                setTaskEnd();
                return;
            }
            Thread.sleep(3000);
        }

        public void content_4() throws Exception {
            if (overtime(15, 0)) return;
            result = mFairy.findPic("twsh_inface.png");
            mFairy.onTap(0.8f, result, 1143, 138, 1161, 152, "天外山海界面切换到天外地图", Sleep);
            if (result.sim > 0.8f) {
                setTaskName(2);
                return;
            }
            result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png","bahuang.png","bh.png"});
            mFairy.onTap(0.8f, result, "天外山海图标", Sleep);
            result=mFairy.findPic(239,452,1075,659,"smzd2.png");
            mFairy.onTap(0.8f,result,"神魔之巅",1000);
            result=mFairy.findPic(239,452,1075,659,"smzd3.png");
            mFairy.onTap(0.8f,result,"神魔之巅",1000);
            result=mFairy.findPic(444,112,821,228,"zwkq.png");
            if (result.sim>0.8f || result1.sim > 0.8f){
                LtLog.e("未开启");
                setTaskEnd();
                return;
            }
        }
        //处于位面
        public void content_5() throws Exception {
            boolean a = false;
            if (overtime(15, 0)) {
                return;
            }

            result = mFairy.findPic( "tut.png");
            result1 = mFairy.findPic( 733,293,920,376,"tut1.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, 990,121,1001,130, "关闭图腾", Sleep);
                mFairy.initMatTime();
                LtLog.e(mFairy.getLineInfo("回到血盟"));
                setTaskName(2);
                return;
            }else if (result1.sim > 0.8f){
                mFairy.initMatTime();
                LtLog.e(mFairy.getLineInfo("回到血盟"));
                setTaskName(2);
                return;
            }

            result = mFairy.findPic(470,30,825,237, "bsyn1.png");
            if(AtFairyConfig.getOption("zhsz").equals("1") || result.sim > 0.8f){
                result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);
                result1 = mFairy.findPic(57, 44, 176, 254, new String[]{"sz1.png", "sz2.png", "sz3.png", "sz4.png", "sz5.png"});
                if (result1.sim > 0.8f) {
                    LtLog.e("氏族内");
                    result2 = mFairy.findPic(73,70,171,409, new String[]{"gjdigong5.png","gjdigong6.png","gjdigong9.png"});
                    if (result2.sim > 0.8f) {
                        LtLog.e("氏族地宫内");
                        a=true;
                    }
                }
                if (a) {
                    result = mFairy.findPic("quyu.png");
                    mFairy.onTap(0.8f, result, "区域", Sleep);

                    result = mFairy.findPic("chuansong.png");
                    mFairy.onTap(0.8f, result, "传送", Sleep);

                    result = mFairy.findPic(747, 135, 1153, 591, "lingdi.png");
                    mFairy.onTap(0.8f, result, "领地", Sleep);

                    result1 = mFairy.findPic("hcWorld.png");
                    if (result1.sim > 0.8f) {
                        result = mFairy.findPic("errszftt.png");
                        if (result.sim > 0.95f) {
                            mFairy.onTap(0.8f, result, 250, 145, 251, 146, "氏族焚天台二层去一层", Sleep);
                        } else {
                            mFairy.onTap(0.8f, result1, 766, 153, 771, 167, "氏族下一层", Sleep);
                        }
                        close(1);
                        setTaskName(1);
                        return;
                    }
                } else {
                    mFairy.onTap(1240, 44, 1252, 55, "关叉", 2000);

                    result = mFairy.findPic("Lower expansion.png");
                    mFairy.onTap(0.8f, result, "打开下扩展栏", 2000);

                    result = mFairy.findPic(816,444,1163,546,new String[]{"qhTotems.png", "tuteng.png"});
                    if(result.sim > 0.8f){
                        mFairy.onTap(0.8f, result, "图腾", Sleep);
                        x=0;
                    }else if(x>=4){
                        result = mFairy.findPic(914,462,992,534,"backcity.png");
                        mFairy.onTap(0.8f, result, "回城", Sleep);
                        x=0;
                    }

                    result = mFairy.findPic("Deliverysure.png");
                    mFairy.onTap(0.8f, result, "传送确定", 10000);
                    if (result.sim > 0.8f) {
                        setTaskName(6);
                        return;
                    }
                }
            }else {
                result = mFairy.findPic("Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result = mFairy.findPic(816,444,1163,546,new String[]{"qhTotems.png", "tuteng.png"});
                LtLog.e("x="+x++);
                if(result.sim > 0.8f){
                    mFairy.onTap(0.8f, result, "图腾", Sleep);
                    x=0;
                }else if(x>=6){
                    result = mFairy.findPic(914,462,992,534,"backcity.png");
                    mFairy.onTap(0.8f, result, "回城", Sleep);
                    x=0;
                }

                result = mFairy.findPic(420,72,824,242,"bsyn.png");
                if (result.sim > 0.8f) {
                    setTaskName(11);return;
                }

                result2 = mFairy.findPic("Deliverysure.png");
                mFairy.onTap(0.8f, result2, "传送确定", 1000);
                for (int y=0;y<=20;y++) {
                    result = mFairy.findPic(470,30,825,237, "bsyn1.png");
                    if(result.sim > 0.8f){
                        LtLog.e("不属于您的血盟！");
                        mFairy.initMatTime();
                        result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                        mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);
                        result1 = mFairy.findPic(57, 44, 176, 254, new String[]{"sz1.png", "sz2.png", "sz3.png", "sz4.png", "sz5.png"});
                        if (result1.sim > 0.8f) {
                            LtLog.e("氏族内");
                            result2 = mFairy.findPic(73,70,171,409, new String[]{"gjdigong5.png","gjdigong6.png","gjdigong9.png"});
                            if (result2.sim > 0.8f) {
                                LtLog.e("氏族地宫内");
                                a=true;
                            }
                        }
                        if (a) {
                            result = mFairy.findPic("quyu.png");
                            mFairy.onTap(0.8f, result, "区域", Sleep);

                            result = mFairy.findPic("chuansong.png");
                            mFairy.onTap(0.8f, result, "传送", Sleep);

                            result = mFairy.findPic(747, 135, 1153, 591, "lingdi.png");
                            mFairy.onTap(0.8f, result, "领地", Sleep);

                            result1 = mFairy.findPic("hcWorld.png");
                            if (result1.sim > 0.8f) {
                                result = mFairy.findPic("errszftt.png");
                                if (result.sim > 0.95f) {
                                    mFairy.onTap(0.8f, result, 250, 145, 251, 146, "氏族焚天台二层去一层", Sleep);
                                } else {
                                    mFairy.onTap(0.8f, result1, 766, 153, 771, 167, "氏族下一层", Sleep);
                                }
                                close(1);
                                setTaskName(1);
                                return;
                            }
                        } else {
                            mFairy.onTap(1240, 44, 1252, 55, "关叉", 2000);

                            result = mFairy.findPic("Lower expansion.png");
                            mFairy.onTap(0.8f, result, "打开下扩展栏", 2000);

                            result = mFairy.findPic(816,444,1163,546,new String[]{"qhTotems.png", "tuteng.png"});
                            if(result.sim > 0.8f){
                                mFairy.onTap(0.8f, result, "图腾", Sleep);
                                x=0;
                            }else if(x>=8){
                                result = mFairy.findPic(914,462,992,534,"backcity.png");
                                mFairy.onTap(0.8f, result, "回城", Sleep);
                                x=0;
                            }

                            result = mFairy.findPic("Deliverysure.png");
                            mFairy.onTap(0.8f, result, "传送确定", 10000);
                            if (result.sim > 0.8f) {
                                setTaskName(6);
                                return;
                            }
                        }
                    }
                }
                /*if(result.sim < 0.8f){
                    LtLog.e("传送中  请等待！");
                    mFairy.sleep(8000);
                }*/

                if (result2.sim > 0.8f) {
                    setTaskName(6);return;
                }

            }
        }

        public void content_6() throws Exception {
            boolean a = false;
            if (overtime(30, 0)) return;
            long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
            LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

            if (dazeTime > 30) {
                mFairy.initMatTime();
                setTaskName(0);return;
            }

            result = mFairy.findPic(470,30,825,237, "bsyn1.png");
            if(result.sim > 0.8f){
                mFairy.initMatTime();
                result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);
                result1 = mFairy.findPic(57, 44, 176, 254, new String[]{"sz1.png", "sz2.png", "sz3.png", "sz4.png", "sz5.png"});
                if (result1.sim > 0.8f) {
                    LtLog.e("氏族内");
                    result2 = mFairy.findPic(73,70,171,409, new String[]{"gjdigong5.png","gjdigong6.png","gjdigong9.png"});
                    if (result2.sim > 0.8f) {
                        LtLog.e("氏族地宫内");
                        a=true;
                    }
                }
                if (a) {
                    result = mFairy.findPic("quyu.png");
                    mFairy.onTap(0.8f, result, "区域", Sleep);

                    result = mFairy.findPic("chuansong.png");
                    mFairy.onTap(0.8f, result, "传送", Sleep);

                    result = mFairy.findPic(747, 135, 1153, 591, "lingdi.png");
                    mFairy.onTap(0.8f, result, "领地", Sleep);

                    result1 = mFairy.findPic("hcWorld.png");
                    if (result1.sim > 0.8f) {
                        result = mFairy.findPic("errszftt.png");
                        if (result.sim > 0.95f) {
                            mFairy.onTap(0.8f, result, 250, 145, 251, 146, "氏族焚天台二层去一层", Sleep);
                        } else {
                            mFairy.onTap(0.8f, result1, 766, 153, 771, 167, "氏族下一层", Sleep);
                        }
                        close(1);
                        setTaskName(1);
                        return;
                    }
                } else {
                    mFairy.onTap(1240, 44, 1252, 55, "关叉", 2000);

                    result = mFairy.findPic("Lower expansion.png");
                    mFairy.onTap(0.8f, result, "打开下扩展栏", 2000);

                    result = mFairy.findPic(816,444,1163,546,new String[]{"qhTotems.png", "tuteng.png"});
                    if(result.sim > 0.8f){
                        mFairy.onTap(0.8f, result, "图腾", Sleep);
                        x=0;
                    }else if(x>=8){
                        result = mFairy.findPic(914,462,992,534,"backcity.png");
                        mFairy.onTap(0.8f, result, "回城", Sleep);
                        x=0;
                    }

                    result = mFairy.findPic("Deliverysure.png");
                    mFairy.onTap(0.8f, result, "传送确定", 10000);
                    if (result.sim > 0.8f) {
                        setTaskName(6);
                        return;
                    }
                }
            }

            result = mFairy.findPic( "tut.png");
            result1 = mFairy.findPic( 733,293,920,376,"tut1.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, 990,121,1001,130, "关闭图腾", Sleep);
                mFairy.initMatTime();
                LtLog.e(mFairy.getLineInfo("回到血盟"));
                setTaskName(2);
                return;
            }else if (result1.sim > 0.8f){
                mFairy.initMatTime();
                LtLog.e(mFairy.getLineInfo("回到血盟"));
                setTaskName(2);
                return;
            }

            result = mFairy.findPic( 1118,2,1276,33,"xyc.png");
            if (result.sim > 0.8f) {
                mFairy.initMatTime();
                LtLog.e(mFairy.getLineInfo("回到轩辕城"));
                setTaskName(2);
                return;
            }

            result2=mFairy.findPic("bianjing3.png");
            if (result2.sim > 0.8f && dazeTime > 10){
                mFairy.onTap(0.8f,result2,1237,112,1241,114,"打开地图",2500);

                result  = mFairy.findPic(574,486,595,503,new String[]{"zszb.png","zszb1.png","zszb2.png"});
                result1  = mFairy.findPic(560,495,599,522,new String[]{"zszb.png","zszb1.png","zszb2.png"});
                if (result.sim > 0.8f || result1.sim > 0.8f){
                    LtLog.e("天外边境坐牢");
                    mFairy.onTap(429,339,437,350,"地图",2000);
                    mFairy.onTap(581,504,582,508,"地图",2000);
                    mFairy.onTap(1238,36,1251,57,"地图",2000);

                    result  = mFairy.findPic(52,18,1225,670,"duihua3.png");
                    if (result.sim > 0.8f){
                        mFairy.onTap(0.8f,result,"传送使者对话",Sleep);
                    }else {
                        mFairy.onTap(0.8f, result, result.x + 2, result.y + 153, result.x + 3, result.y + 154, "点击传送使者", Sleep);
                    }
                }else {
                    result  = mFairy.findPic(1205,9,1275,97,"dtgc.png");
                    mFairy.onTap(0.8f,result1,"关闭地图",2000);
                }

                result = mFairy.findPic("deliveryyes.png");
                mFairy.onTap(0.8f,result,"传送",Sleep);
            }

        }

        public void content_7() throws Exception {
            if (overtime(15, 5)) return;

            result = mFairy.findPic(new String[]{"twsh_inface.png","shenmo.png"});
            mFairy.onTap(0.8f, result, 1159,231,1168,240, "天外山海界面切换到氏族", Sleep);
            mFairy.onTap(0.8f, result, 292,95,325,113, "天外山海界面切换到我的氏族", Sleep);
            mFairy.onTap(0.8f, result, 988,639,1014,651, "回到氏族领地",Sleep);

            result1 = mFairy.findPic(242,38,1055,234,new String[]{"weimianzhong.png","weimianzhong1.png"});
            if (result1.sim>0.8f) {
                close(1);
                LtLog.e(mFairy.getLineInfo("位面中"));
                setTaskName(5);return;
            }

            if (result.sim > 0.8f) {
                result = mFairy.findPic(242,38,1055,234,new String[]{"weimianzhong.png","weimianzhong1.png"});
                if (result.sim>0.8f) {
                    close(1);
                    LtLog.e(mFairy.getLineInfo("位面中"));
                    setTaskName(5);return;
                }
                Thread.sleep(10000);
                setTaskName(8);
                return;
            }

            result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png","bahuang.png","bh.png"});
            mFairy.onTap(0.8f, result, "天外山海图标", Sleep);

            result=mFairy.findPic(239,452,1075,659,"smzd2.png");
            mFairy.onTap(0.8f,result,"神魔之巅",1000);
            result=mFairy.findPic(239,452,1075,659,"smzd3.png");
            mFairy.onTap(0.8f,result,"神魔之巅",1000);

            result1 = mFairy.findPic(472,118,816,251,new String[]{"sjzwkq.png"});
            if (result1.sim>0.8f|| result1.sim > 0.8f) {
                close(1);
                LtLog.e(mFairy.getLineInfo("未开启地图"));
                setTaskName(5);return;
            }
        }

        public void content_8() throws Exception {
            if (overtime(15, 0)) return;
            result=mFairy.findPic(239,452,1075,659,"smzd2.png");
            mFairy.onTap(0.8f,result,1226,29,1233,42,"关闭神魔之巅",Sleep);
            result=mFairy.findPic(239,452,1075,659,"smzd3.png");
            mFairy.onTap(0.8f,result,1226,29,1233,42,"关闭神魔之巅",Sleep);

            result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
            mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);

            result = mFairy.findPic("quyu.png");
            mFairy.onTap(0.8f, result, "区域", Sleep);

            result = mFairy.findPic("chuansong.png");
            mFairy.onTap(0.8f, result, "传送", Sleep);


            result = mFairy.findPic(747,135,1153,591,"digong.png");
            mFairy.onTap(0.8f, result, "前往氏族地宫", Sleep);
            if (result.sim>0.8f){
                close(1);
                setTaskName(9);return;
            }
        }

        public void content_9() throws Exception {
            long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
            if (dazeTime > 3) {
                mFairy.initMatTime();
                LtLog.e(mFairy.getLineInfo("到达氏族地宫地点开始传送"));
                setTaskName(10);
                return;
            }
        }

        public void content_10() throws Exception {
            if (overtime(10, 0)) return;
            result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
            mFairy.onTap(0.8f, result, 851, 594, 866, 604, "下车", Sleep);
            mFairy.onTap(0.8f, result,  559,346,583,403, "点击传送npc", 2000);
            if (result.sim>0.8f){
                result = mFairy.findPic(710,123,1068,510, "digongsw.png");
                mFairy.onTap(0.8f, result,  "地宫守卫", 3000);

                result =mFairy.findPic(18,116,532,607,"jinrushizu.png");
                mFairy.onTap(0.8f,result,"进入氏族地宫",5000);
                if (result.sim>0.8f){
                    result =mFairy.findPic(18,116,532,607,"jinrushizu.png");
                    mFairy.onTap(0.8f,result,"进入氏族地宫",5000);
                    setTaskEnd();return;
                }else {
                    mFairy.onTap(633,328,653,361, "点击传送npc2", 2000);
                    result = mFairy.findPic(710,123,1068,510, "digongsw.png");
                    mFairy.onTap(0.8f, result,  "地宫守卫", 3000);

                    result =mFairy.findPic(18,116,532,607,"jinrushizu.png");
                    mFairy.onTap(0.8f,result,"进入氏族地宫",5000);
                    if (result.sim>0.8f) {
                        result =mFairy.findPic(18,116,532,607,"jinrushizu.png");
                        mFairy.onTap(0.8f,result,"进入氏族地宫",5000);
                        setTaskEnd();
                        return;
                    }
                }
            }
        }

        public void content_11() throws Exception {
            long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
            LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
            if (dazeTime > 30){
                setTaskName(0);
                return;
            }

            result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
            mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);

            result = mFairy.findPic(57, 44, 176, 254, new String[]{"sz1.png", "sz2.png", "sz3.png", "sz4.png", "sz5.png"});
            if (result.sim > 0.8f) {
                result = mFairy.findPic(76,92,168,337, new String[]{"gjdigong5.png","gjdigong6.png","gjdigong9.png"});
            }
            if (result.sim > 0.8f) {
                result = mFairy.findPic("quyu.png");
                mFairy.onTap(0.8f, result, "区域", Sleep);

                result = mFairy.findPic("chuansong.png");
                mFairy.onTap(0.8f, result, "传送", Sleep);

                result = mFairy.findPic(747, 135, 1153, 591, "lingdi.png");
                mFairy.onTap(0.8f, result, "领地", Sleep);

                result1 = mFairy.findPic("hcWorld.png");
                if (result1.sim > 0.8f) {
                    result = mFairy.findPic("errszftt.png");
                    if (result.sim > 0.95f) {
                        mFairy.onTap(0.8f, result, 250, 145, 251, 146, "氏族焚天台二层去一层", Sleep);
                    } else {
                        mFairy.onTap(0.8f, result1, 766, 153, 771, 167, "氏族下一层", Sleep);
                    }
                    close(1);
                    setTaskName(1);
                    return;
                }
            } else {
                mFairy.onTap(1240, 44, 1252, 55, "关叉", 2000);

                result = mFairy.findPic("Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result = mFairy.findPic(816,444,1163,546,new String[]{"qhTotems.png", "tuteng.png"});
                if(result.sim <0.8f){
                    mFairy.onTap(0.8f, result, "图腾", Sleep);
                }else{
                    result = mFairy.findPic(825,443,1194,545,"backcity.png");
                }


                result = mFairy.findPic("Deliverysure.png");
                mFairy.onTap(0.8f, result, "传送确定", 10000);
                if (result.sim > 0.8f) {
                    setTaskName(6);
                    return;
                }
            }
        }

    }.taskContent(mFairy, "传送城市中");
    return cityCount;
}//传送城市

    public int goCity1(final String str) throws Exception {
        new GameUtil(mFairy) {
            int shjdt=0;

            public void content_0() throws Exception {
                close(1);
                if (str.equals("轩辕")) {
                    result =mFairy.findPic(1108,3,1277,34,new String[]{"xmld1.png","xyc.png"});
                    if (result.sim>0.8f){
                        LtLog.e(mFairy.getLineInfo("轩辕或血盟中"));
                        GameUtil.this.cityCount = 0;
                        setTaskEnd();
                        return;
                    }
                }
                if (str.equals("天外噬灵渊") || str.equals("天外霜火岭") || str.equals("天外焚天台")) {
                    setTaskName(7);
                    return;
                }
                if (str.equals("氏族地宫")) {
                    setTaskName(7);return;
                }
                if (str.equals("山海界")) {
                    setTaskName(2);return;
                }
                setTaskName(5);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(4, 2)) return;
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                if (dazeTime > 5) {
                    result = mFairy.findPic(1112,2,1280,37,new String[]{"panguld.png","panguld.png","panguld.png","panguld.png","panguld.png"});
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(1062,121,1280,326,"huidaobf.png");
                        mFairy.onTap(0.8f, result, "回到本服", Sleep);
                    }else{
                        result = mFairy.findPic("Lower expansion.png");
                        mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                        result = mFairy.findPic(816,444,1163,546,new String[]{"qhTotems.png", "tuteng.png","backcity.png"});
                        mFairy.onTap(0.8f, result, "图腾", Sleep);

                        result = mFairy.findPic("Deliverysure.png");
                        mFairy.onTap(0.8f, result, "传送确定", 10000);
                        if (result.sim > 0.8f) {
                            setTaskName(6);return;
                        }
                    }
                }
            }

            public void content_2() throws Exception {
                if (overtime(15, 0)) return;
                result = mFairy.findPic( "tut.png");
                mFairy.onTap(0.8f, result, 990,121,1001,130, "关闭图腾", Sleep);

                if (str.equals("氏族地宫")) {
                    close(1);
                    setTaskName(7);return;
                }

                if (str.equals("山海界")) {
                    result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png","bahuang.png","bh.png"});
                    mFairy.onTap(0.8f, result,  "八荒", Sleep);

                    result = mFairy.findPic(51,537,243,696, "xysh.png");
                    mFairy.onTap(0.8f, result,  "轩辕山海", Sleep);
                }

                result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);

                result1 = mFairy.findPic("twsh_inface.png");
                result = mFairy.findPic(new String[]{"hcWorld.png","hcWorld1.png","hcWorld2.png","hcWorld3.png"});
                mFairy.onTap(0.8f, result, "切换到世界", Sleep);
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    switch (str) {
                        case "轩辕":
                            //mFairy.onTap(533, 292, 548, 318, str, Sleep);
                            break;
                        case "青木":
                            mFairy.onTap( 1039, 342, 1057, 360, str, Sleep);
                            break;
                        case "苍穹海":
                            mFairy.onTap(779, 585, 795, 599, str, Sleep);
                            break;
                        case "昆仑墟":
                            mFairy.onTap(376, 272, 398, 287, str, Sleep);
                            break;
                        case "孽龙囚":
                            mFairy.onTap( 501, 132, 519, 145, str, Sleep);
                            break;
                        case "葬魂谷":
                            mFairy.onTap( 1024, 204, 1038, 219, str, Sleep);
                            break;
                        case "霜火岭":
                            mFairy.onTap(1076, 497, 1091, 511, str, Sleep);
                            break;
                        case "噬灵渊":
                            mFairy.onTap( 1183, 254, 1198, 268, str, Sleep);
                            break;
                        case "天外噬灵渊":
                            mFairy.onTap(902, 370, 903, 371, str, Sleep);
                            break;
                        case "天外霜火岭":
                            mFairy.onTap( 722, 433, 723, 434, str, Sleep);
                            break;
                        case "焚天台":
                            mFairy.onTap(341, 107, 360, 124, str, Sleep);
                            break;
                        case "天外焚天台":
                            mFairy.onTap( 948, 459, 949, 460, str, Sleep);
                            break;
                        case "山海界":
                            switch (Integer.parseInt(AtFairyConfig.getOption("xzdt"))) {
                                case 121:
                                    mFairy.onTap(335,533,340,541, "甘洲", Sleep);
                                    break;
                                case 122:
                                    mFairy.onTap(578,479,586,487, "神墟", Sleep);
                                    break;
                                case 123:
                                    mFairy.onTap(725,430,735,438, "大塾", Sleep);
                                    break;
                                case 124:
                                    mFairy.onTap(526,155,537,168, "炎居", Sleep);
                                    break;
                                case 125:
                                    mFairy.onTap(854,242,866,259, "焰狱", Sleep);
                                    break;
                                case 126:
                                    mFairy.onTap(980,460,989,470, "白宫", Sleep);
                                    break;
                                case 127:
                                    mFairy.onTap(937,591,943,597, "彼岸", Sleep);
                                    break;
                                case 128:
                                    mFairy.onTap(366,213,379,228, "光阴", Sleep);
                                    break;
                                case 129:
                                    mFairy.onTap(270,362,281,370, "春祭", Sleep);
                                    break;
                                case 130:
                                    mFairy.onTap(1004,298,1009,314, "雷鸣", Sleep);
                                    break;
                                case 131:
                                    mFairy.onTap(718,171,721,181, "黑白", Sleep);
                                    break;
                                case 132:
                                    mFairy.onTap(1025,159,1029,171, "战殇", Sleep);
                                    break;
                                case 133:
                                    mFairy.onTap(202,229,206,236, "银杏", Sleep);
                                    break;
                            }
                            break;
                    }
                    mFairy.sleep(1000);
                    if (str.equals("轩辕")){

                    }else {
                        mFairy.onTap(759, 431, 795, 448, "确定传送", 2000);
                    }



                    result = mFairy.findPic(new  String[]{"szdgzhong.png","weimianzhong.png","weimianzhong1.png"});
                    if (result.sim>0.8f) {
                        close(1);
                        LtLog.e(mFairy.getLineInfo("位面中"));
                        setTaskName(5);return;
                    }

                    result = mFairy.findPic(46,370,494,473,"yanju.png");
                    mFairy.onTap(0.8f, result, "前往炎居", Sleep);

                    result = mFairy.findPic(65,384,490,459,"qwdt.png");
                    mFairy.onTap(0.8f, result, "前往地图", Sleep);
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {
                result = mFairy.findPic(199,67,1229,272, new String[]{"fenxian.png","fenxian1.png"});
                result1 = mFairy.findPic(564,521,900,646,"fenxian2.png");
                if (result.sim > 0.8f || result1.sim > 0.8f){
                    mFairy.initMatTime();
                    result = mFairy.findPic(374,166,841,538, new String[]{"liuchang.png","fanmang.png"});
                    mFairy.onTap(0.9f, result,  "选择分线", 1000);
                    mFairy.onTap(0.9f, result, 740,584,750,596, "进入地图", 3000);
                }else {
                    result1 = mFairy.findPic("twsh_inface.png");
                    result = mFairy.findPic("quyu.png");
                    if (result.sim > 0.8f || result1.sim > 0.8f) {
                        close(1);
                        LtLog.e(mFairy.getLineInfo("已经在目标地图了或没有目标地图"));
                        GameUtil.this.cityCount = 0;
                        setTaskEnd();
                        return;
                    }
                }
                result = mFairy.findPic(515,72,740,191,"yzdqdt.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("已在当前地图"));
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(65,384,490,459,"qwdt.png");
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    mFairy.onTap(0.8f, result, "前往地图", 3000);
                }

                result = mFairy.findPic(65,384,490,459,"qwdt.png");
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    mFairy.onTap(0.8f, result, "前往地图", 3000);
                }



                long dazeTime = mFairy.mMatTime(1212,129,67,16, 0.9f);
                if (dazeTime > 5) {
                    mFairy.initMatTime();
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    GameUtil.this.cityCount = 1;
                    setTaskEnd();
                    return;
                }
                Thread.sleep(3000);
            }

            public void content_4() throws Exception {
                if (overtime(15, 0)) return;
                result = mFairy.findPic("twsh_inface.png");
                mFairy.onTap(0.8f, result, 1143, 138, 1161, 152, "天外山海界面切换到天外地图", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png","bahuang.png","bh.png"});
                mFairy.onTap(0.8f, result, "天外山海图标", Sleep);
                result=mFairy.findPic(239,452,1075,659,"smzd2.png");
                mFairy.onTap(0.8f,result,"神魔之巅",1000);
                result=mFairy.findPic(239,452,1075,659,"smzd3.png");
                mFairy.onTap(0.8f,result,"神魔之巅",1000);
                result=mFairy.findPic(444,112,821,228,"zwkq.png");
                if (result.sim>0.8f || result1.sim > 0.8f){
                    LtLog.e("未开启");
                    setTaskEnd();
                    return;
                }
            }
            //处于位面
            public void content_5() throws Exception {
                boolean a = false;
                if (overtime(15, 0)) {
                    return;
                }

                result = mFairy.findPic( "tut.png");
                result1 = mFairy.findPic( 733,293,920,376,"tut1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 990,121,1001,130, "关闭图腾", Sleep);
                    mFairy.initMatTime();
                    LtLog.e(mFairy.getLineInfo("回到血盟"));
                    setTaskName(2);
                    return;
                }else if (result1.sim > 0.8f){
                    mFairy.initMatTime();
                    LtLog.e(mFairy.getLineInfo("回到血盟"));
                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic(470,30,825,237, "bsyn1.png");
                if(AtFairyConfig.getOption("zhsz").equals("1") || result.sim > 0.8f){
                    result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                    mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);
                    result1 = mFairy.findPic(57, 44, 176, 254, new String[]{"sz1.png", "sz2.png", "sz3.png", "sz4.png", "sz5.png"});
                    if (result1.sim > 0.8f) {
                        LtLog.e("氏族内");
                        result2 = mFairy.findPic(73,70,171,409, new String[]{"gjdigong5.png","gjdigong6.png","gjdigong9.png"});
                        if (result2.sim > 0.8f) {
                            LtLog.e("氏族地宫内");
                            a=true;
                        }
                    }
                    if (a) {
                        result = mFairy.findPic("quyu.png");
                        mFairy.onTap(0.8f, result, "区域", Sleep);

                        result = mFairy.findPic("chuansong.png");
                        mFairy.onTap(0.8f, result, "传送", Sleep);

                        result = mFairy.findPic(747, 135, 1153, 591, "lingdi.png");
                        mFairy.onTap(0.8f, result, "领地", Sleep);

                        result1 = mFairy.findPic("hcWorld.png");
                        if (result1.sim > 0.8f) {
                            result = mFairy.findPic("errszftt.png");
                            if (result.sim > 0.95f) {
                                mFairy.onTap(0.8f, result, 250, 145, 251, 146, "氏族焚天台二层去一层", Sleep);
                            } else {
                                mFairy.onTap(0.8f, result1, 766, 153, 771, 167, "氏族下一层", Sleep);
                            }
                            close(1);
                            setTaskName(1);
                            return;
                        }
                    } else {
                        mFairy.onTap(1240, 44, 1252, 55, "关叉", 2000);

                        result = mFairy.findPic("Lower expansion.png");
                        mFairy.onTap(0.8f, result, "打开下扩展栏", 2000);

                        result = mFairy.findPic(816,444,1163,546,new String[]{"qhTotems.png", "tuteng.png"});
                        if(result.sim > 0.8f){
                            mFairy.onTap(0.8f, result, "图腾", Sleep);
                            x=0;
                        }else if(x>=4){
                            result = mFairy.findPic(914,462,992,534,"backcity.png");
                            mFairy.onTap(0.8f, result, "回城", Sleep);
                            x=0;
                        }

                        result = mFairy.findPic("Deliverysure.png");
                        mFairy.onTap(0.8f, result, "传送确定", 10000);
                        if (result.sim > 0.8f) {
                            setTaskName(6);
                            return;
                        }
                    }
                }else {
                    result = mFairy.findPic("Lower expansion.png");
                    mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                    result = mFairy.findPic(816,444,1163,546,new String[]{"qhTotems.png", "tuteng.png"});
                    LtLog.e("x="+x++);
                    if(result.sim > 0.8f){
                        mFairy.onTap(0.8f, result, "图腾", Sleep);
                        x=0;
                    }else if(x>=6){
                        result = mFairy.findPic(914,462,992,534,"backcity.png");
                        mFairy.onTap(0.8f, result, "回城", Sleep);
                        x=0;
                    }

                    result = mFairy.findPic(420,72,824,242,"bsyn.png");
                    if (result.sim > 0.8f) {
                        setTaskName(11);return;
                    }

                    result2 = mFairy.findPic("Deliverysure.png");
                    mFairy.onTap(0.8f, result2, "传送确定", 1000);
                    for (int y=0;y<=20;y++) {
                        result = mFairy.findPic(470,30,825,237, "bsyn1.png");
                        if(result.sim > 0.8f){
                            LtLog.e("不属于您的血盟！");
                            mFairy.initMatTime();
                            result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                            mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);
                            result1 = mFairy.findPic(57, 44, 176, 254, new String[]{"sz1.png", "sz2.png", "sz3.png", "sz4.png", "sz5.png"});
                            if (result1.sim > 0.8f) {
                                LtLog.e("氏族内");
                                result2 = mFairy.findPic(73,70,171,409, new String[]{"gjdigong5.png","gjdigong6.png","gjdigong9.png"});
                                if (result2.sim > 0.8f) {
                                    LtLog.e("氏族地宫内");
                                    a=true;
                                }
                            }
                            if (a) {
                                result = mFairy.findPic("quyu.png");
                                mFairy.onTap(0.8f, result, "区域", Sleep);

                                result = mFairy.findPic("chuansong.png");
                                mFairy.onTap(0.8f, result, "传送", Sleep);

                                result = mFairy.findPic(747, 135, 1153, 591, "lingdi.png");
                                mFairy.onTap(0.8f, result, "领地", Sleep);

                                result1 = mFairy.findPic("hcWorld.png");
                                if (result1.sim > 0.8f) {
                                    result = mFairy.findPic("errszftt.png");
                                    if (result.sim > 0.95f) {
                                        mFairy.onTap(0.8f, result, 250, 145, 251, 146, "氏族焚天台二层去一层", Sleep);
                                    } else {
                                        mFairy.onTap(0.8f, result1, 766, 153, 771, 167, "氏族下一层", Sleep);
                                    }
                                    close(1);
                                    setTaskName(1);
                                    return;
                                }
                            } else {
                                mFairy.onTap(1240, 44, 1252, 55, "关叉", 2000);

                                result = mFairy.findPic("Lower expansion.png");
                                mFairy.onTap(0.8f, result, "打开下扩展栏", 2000);

                                result = mFairy.findPic(816,444,1163,546,new String[]{"qhTotems.png", "tuteng.png"});
                                if(result.sim > 0.8f){
                                    mFairy.onTap(0.8f, result, "图腾", Sleep);
                                    x=0;
                                }else if(x>=8){
                                    result = mFairy.findPic(914,462,992,534,"backcity.png");
                                    mFairy.onTap(0.8f, result, "回城", Sleep);
                                    x=0;
                                }

                                result = mFairy.findPic("Deliverysure.png");
                                mFairy.onTap(0.8f, result, "传送确定", 10000);
                                if (result.sim > 0.8f) {
                                    setTaskName(6);
                                    return;
                                }
                            }
                        }
                    }
                /*if(result.sim < 0.8f){
                    LtLog.e("传送中  请等待！");
                    mFairy.sleep(8000);
                }*/

                    if (result2.sim > 0.8f) {
                        setTaskName(6);return;
                    }

                }
            }

            public void content_6() throws Exception {
                boolean a = false;
                if (overtime(30, 0)) return;
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                if (dazeTime > 30) {
                    mFairy.initMatTime();
                    setTaskName(0);return;
                }

                result = mFairy.findPic(470,30,825,237, "bsyn1.png");
                if(result.sim > 0.8f){
                    mFairy.initMatTime();
                    result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                    mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);
                    result1 = mFairy.findPic(57, 44, 176, 254, new String[]{"sz1.png", "sz2.png", "sz3.png", "sz4.png", "sz5.png"});
                    if (result1.sim > 0.8f) {
                        LtLog.e("氏族内");
                        result2 = mFairy.findPic(73,70,171,409, new String[]{"gjdigong5.png","gjdigong6.png","gjdigong9.png"});
                        if (result2.sim > 0.8f) {
                            LtLog.e("氏族地宫内");
                            a=true;
                        }
                    }
                    if (a) {
                        result = mFairy.findPic("quyu.png");
                        mFairy.onTap(0.8f, result, "区域", Sleep);

                        result = mFairy.findPic("chuansong.png");
                        mFairy.onTap(0.8f, result, "传送", Sleep);

                        result = mFairy.findPic(747, 135, 1153, 591, "lingdi.png");
                        mFairy.onTap(0.8f, result, "领地", Sleep);

                        result1 = mFairy.findPic("hcWorld.png");
                        if (result1.sim > 0.8f) {
                            result = mFairy.findPic("errszftt.png");
                            if (result.sim > 0.95f) {
                                mFairy.onTap(0.8f, result, 250, 145, 251, 146, "氏族焚天台二层去一层", Sleep);
                            } else {
                                mFairy.onTap(0.8f, result1, 766, 153, 771, 167, "氏族下一层", Sleep);
                            }
                            close(1);
                            setTaskName(1);
                            return;
                        }
                    } else {
                        mFairy.onTap(1240, 44, 1252, 55, "关叉", 2000);

                        result = mFairy.findPic("Lower expansion.png");
                        mFairy.onTap(0.8f, result, "打开下扩展栏", 2000);

                        result = mFairy.findPic(816,444,1163,546,new String[]{"qhTotems.png", "tuteng.png"});
                        if(result.sim > 0.8f){
                            mFairy.onTap(0.8f, result, "图腾", Sleep);
                            x=0;
                        }else if(x>=8){
                            result = mFairy.findPic(914,462,992,534,"backcity.png");
                            mFairy.onTap(0.8f, result, "回城", Sleep);
                            x=0;
                        }

                        result = mFairy.findPic("Deliverysure.png");
                        mFairy.onTap(0.8f, result, "传送确定", 10000);
                        if (result.sim > 0.8f) {
                            setTaskName(6);
                            return;
                        }
                    }
                }

                result = mFairy.findPic( "tut.png");
                result1 = mFairy.findPic( 733,293,920,376,"tut1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 990,121,1001,130, "关闭图腾", Sleep);
                    mFairy.initMatTime();
                    LtLog.e(mFairy.getLineInfo("回到血盟"));
                    setTaskName(2);
                    return;
                }else if (result1.sim > 0.8f){
                    mFairy.initMatTime();
                    LtLog.e(mFairy.getLineInfo("回到血盟"));
                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic( 1118,2,1276,33,"xyc.png");
                if (result.sim > 0.8f) {
                    mFairy.initMatTime();
                    LtLog.e(mFairy.getLineInfo("回到轩辕城"));
                    setTaskName(2);
                    return;
                }

                result2=mFairy.findPic("bianjing3.png");
                if (result2.sim > 0.8f && dazeTime > 10){
                    mFairy.onTap(0.8f,result2,1237,112,1241,114,"打开地图",2500);

                    result  = mFairy.findPic(574,486,595,503,new String[]{"zszb.png","zszb1.png","zszb2.png"});
                    result1  = mFairy.findPic(560,495,599,522,new String[]{"zszb.png","zszb1.png","zszb2.png"});
                    if (result.sim > 0.8f || result1.sim > 0.8f){
                        LtLog.e("天外边境坐牢");
                        mFairy.onTap(429,339,437,350,"地图",2000);
                        mFairy.onTap(581,504,582,508,"地图",2000);
                        mFairy.onTap(1238,36,1251,57,"地图",2000);

                        result  = mFairy.findPic(52,18,1225,670,"duihua3.png");
                        if (result.sim > 0.8f){
                            mFairy.onTap(0.8f,result,"传送使者对话",Sleep);
                        }else {
                            mFairy.onTap(0.8f, result, result.x + 2, result.y + 153, result.x + 3, result.y + 154, "点击传送使者", Sleep);
                        }
                    }else {
                        result  = mFairy.findPic(1205,9,1275,97,"dtgc.png");
                        mFairy.onTap(0.8f,result1,"关闭地图",2000);
                    }

                    result = mFairy.findPic("deliveryyes.png");
                    mFairy.onTap(0.8f,result,"传送",Sleep);
                }

            }

            public void content_7() throws Exception {
                if (overtime(15, 5)) return;

                result = mFairy.findPic(new String[]{"twsh_inface.png","shenmo.png"});
                mFairy.onTap(0.8f, result, 1159,231,1168,240, "天外山海界面切换到氏族", Sleep);
                mFairy.onTap(0.8f, result, 292,95,325,113, "天外山海界面切换到我的氏族", Sleep);
                mFairy.onTap(0.8f, result, 988,639,1014,651, "回到氏族领地",Sleep);

                result1 = mFairy.findPic(242,38,1055,234,new String[]{"weimianzhong.png","weimianzhong1.png"});
                if (result1.sim>0.8f) {
                    close(1);
                    LtLog.e(mFairy.getLineInfo("位面中"));
                    setTaskName(5);return;
                }

                if (result.sim > 0.8f) {
                    result = mFairy.findPic(242,38,1055,234,new String[]{"weimianzhong.png","weimianzhong1.png"});
                    if (result.sim>0.8f) {
                        close(1);
                        LtLog.e(mFairy.getLineInfo("位面中"));
                        setTaskName(5);return;
                    }
                    Thread.sleep(10000);
                    setTaskName(8);
                    return;
                }
                result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png","bahuang.png","bh.png"});
                mFairy.onTap(0.8f, result, "天外山海图标", Sleep);

                result=mFairy.findPic(239,452,1075,659,"smzd2.png");
                mFairy.onTap(0.8f,result,"神魔之巅",1000);
                result=mFairy.findPic(239,452,1075,659,"smzd3.png");
                mFairy.onTap(0.8f,result,"神魔之巅",1000);

                result1 = mFairy.findPic(472,118,816,251,new String[]{"sjzwkq.png"});
                if (result1.sim>0.8f|| result1.sim > 0.8f) {
                    close(1);
                    LtLog.e(mFairy.getLineInfo("未开启地图"));
                    setTaskName(5);return;
                }
            }

            public void content_8() throws Exception {
                if (overtime(10, 0)) return;
                result=mFairy.findPic(239,452,1075,659,"smzd2.png");
                mFairy.onTap(0.8f,result,1226,29,1233,42,"关闭神魔之巅",Sleep);
                result=mFairy.findPic(239,452,1075,659,"smzd3.png");
                mFairy.onTap(0.8f,result,1226,29,1233,42,"关闭神魔之巅",Sleep);

                result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);

                result = mFairy.findPic("quyu.png");
                mFairy.onTap(0.8f, result, "区域", Sleep);

                result = mFairy.findPic("chuansong.png");
                mFairy.onTap(0.8f, result, "传送", Sleep);


                result = mFairy.findPic(747,135,1153,591,"digong.png");
                mFairy.onTap(0.8f, result, "前往氏族地宫", Sleep);
                if (result.sim>0.8f){
                    close(1);
                    setTaskName(9);return;
                }
            }

            public void content_9() throws Exception {
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 3) {
                    mFairy.initMatTime();
                    LtLog.e(mFairy.getLineInfo("到达氏族地宫地点开始传送"));
                    setTaskName(10);
                    return;
                }
            }

            public void content_10() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                mFairy.onTap(0.8f, result, 851, 594, 866, 604, "下车", Sleep);
                mFairy.onTap(0.8f, result,  559,346,583,403, "点击传送npc", 2000);
                if (result.sim>0.8f){
                    result = mFairy.findPic(710,123,1068,510, "digongsw.png");
                    mFairy.onTap(0.8f, result,  "地宫守卫", 3000);

                    result =mFairy.findPic(18,116,532,607,"jinrushizu.png");
                    mFairy.onTap(0.8f,result,"进入氏族地宫",5000);
                    if (result.sim>0.8f){
                        result =mFairy.findPic(18,116,532,607,"jinrushizu.png");
                        mFairy.onTap(0.8f,result,"进入氏族地宫",5000);
                        setTaskEnd();return;
                    }else {
                        mFairy.onTap(633,328,653,361, "点击传送npc2", 2000);
                        result = mFairy.findPic(710,123,1068,510, "digongsw.png");
                        mFairy.onTap(0.8f, result,  "地宫守卫", 3000);

                        result =mFairy.findPic(18,116,532,607,"jinrushizu.png");
                        mFairy.onTap(0.8f,result,"进入氏族地宫",5000);
                        if (result.sim>0.8f) {
                            result =mFairy.findPic(18,116,532,607,"jinrushizu.png");
                            mFairy.onTap(0.8f,result,"进入氏族地宫",5000);
                            setTaskEnd();
                            return;
                        }
                    }
                }
            }

            public void content_11() throws Exception {
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 30){
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);

                result = mFairy.findPic(57, 44, 176, 254, new String[]{"sz1.png", "sz2.png", "sz3.png", "sz4.png", "sz5.png"});
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(76,92,168,337, new String[]{"gjdigong5.png","gjdigong6.png","gjdigong9.png"});
                }
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("quyu.png");
                    mFairy.onTap(0.8f, result, "区域", Sleep);

                    result = mFairy.findPic("chuansong.png");
                    mFairy.onTap(0.8f, result, "传送", Sleep);

                    result = mFairy.findPic(747, 135, 1153, 591, "lingdi.png");
                    mFairy.onTap(0.8f, result, "领地", Sleep);

                    result1 = mFairy.findPic("hcWorld.png");
                    if (result1.sim > 0.8f) {
                        result = mFairy.findPic("errszftt.png");
                        if (result.sim > 0.95f) {
                            mFairy.onTap(0.8f, result, 250, 145, 251, 146, "氏族焚天台二层去一层", Sleep);
                        } else {
                            mFairy.onTap(0.8f, result1, 766, 153, 771, 167, "氏族下一层", Sleep);
                        }
                        close(1);
                        setTaskName(1);
                        return;
                    }
                } else {
                    mFairy.onTap(1240, 44, 1252, 55, "关叉", 2000);

                    result = mFairy.findPic("Lower expansion.png");
                    mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                    result = mFairy.findPic(816,444,1163,546,new String[]{"qhTotems.png", "tuteng.png"});
                    if(result.sim <0.8f){
                        mFairy.onTap(0.8f, result, "图腾", Sleep);
                    }else{
                        result = mFairy.findPic(825,443,1194,545,"backcity.png");
                    }


                    result = mFairy.findPic("Deliverysure.png");
                    mFairy.onTap(0.8f, result, "传送确定", 10000);
                    if (result.sim > 0.8f) {
                        setTaskName(6);
                        return;
                    }
                }
            }

        }.taskContent(mFairy, "传送城市中111");
        return cityCount;
    }//传送城市

    public void twsh_back()throws Exception {
        new GameUtil(mFairy){
            public void content_0() throws Exception {
                close(1);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;

                result=mFairy.findPic(1109,3,1278,35,"ld3.png");
                if (result.sim > 0.8f) {

                }else{
                    result=mFairy.findPic(1109,3,1278,35,"ld3.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("回到自己领地"));
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(699, 11, 1180, 264, new String[]{"bahuang1.png","bahuang.png","bh.png"});
                mFairy.onTap(0.8f, result, "天外山海图标", Sleep);

                result1=mFairy.findPic(239,452,1075,659,"smzd2.png");
                mFairy.onTap(0.8f,result1,"神魔之巅",1000);

                result=mFairy.findPic(239,452,1075,659,"smzd3.png");
                mFairy.onTap(0.8f,result,"神魔之巅",1000);

                result=mFairy.findPic(473,74,809,215,"sjwkq1.png");
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("赛季未开启！！"));
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(61,6,363,120,new String[]{"twsh_inface.png","shenmo.png"});
                if(result.sim>0.8f){
                    mFairy.onTap(0.8f, result, 1159,231,1168,240, "天外山海界面切换到氏族", 3000);
                    mFairy.onTap(0.8f, result, 292,95,325,113, "天外山海界面切换到我的氏族", 3000);
                    mFairy.onTap(0.8f, result, 988,639,1014,651, "回到氏族领地",3000);
                    mFairy.onTap(0.8f,result,1144,45,1171,73,"天外山海关闭",3000);
                    if(result.sim>0.8f){
                        setTaskName(2);
                        return;
                    }
                }
            }

            public void content_2() throws Exception{
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);

                result=mFairy.findPic(1182,5,1274,69,"guan.png");
                mFairy.onTap(0.8f,result,"神魔关",1000);

                result=mFairy.findPic(1109,3,1278,35,"ld3.png");
                if (dazeTime > 20 || result.sim > 0.8f) {
                    mFairy.initMatTime();
                    LtLog.e(mFairy.getLineInfo("回到自己领地"));
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy,"天外山海-回城");
    }//天外山海回城

    public void coordinate(final String str, final int gmx, final int gmy) throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                result = mFairy.findPic(new String[]{"hcWorld.png","hcWorld1.png","hcWorld2.png","hcWorld3.png"});
                if (result.sim > 0.8f) {
                }else {
                    close(1);
                }
                setTaskName(1);
                return;
            }

            double x;
            double y;

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;
                result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);
                if (result.sim < 0.8f){
                    result =mFairy.findPic(1108,3,1277,34,new String[]{"xmld.png","xyc.png"});
                    mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);
                }



                result = mFairy.findPic(new String[]{"hcWorld.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("地图界面=" + str));
                    LtLog.e(mFairy.getLineInfo("游戏坐标=" + gmx+"----"+gmy));
                    Thread.sleep(2000);
                    switch (str) {
                        case "轩辕":
                            x = gmx *0.0014+gmy *-1.0669+688.7355;
                            y = gmx *1.065+gmy *-0.0029+82.7958;
                            x++;y++;
                            break;
                        case "11": // 苍穹海一层
                            x = gmx * 1.5934 + gmy * -0.0003 + 153.2737;
                            y = gmx * -0.0032 + gmy * 1.592 + 82.5643;
                            break;
                        case "12": //苍穹海二层
                            x = gmx * 1.5904 + gmy * -0.0001 + 153.8818;
                            y = gmx * 0.0016 + gmy * 1.5964 + 81.3154;
                            break;
                        case "13":   // 苍穹海三层
                            x = gmx * 0.0052 + gmy * -1.5949 + 702.2972;
                            y = gmx * 1.5951 + gmy * 0.0071 + 74.2386;
                            break;
                        case "14":  // 苍穹海四层
                            x = gmx * -1.5912 + gmy * -0.0007 + 709.4776;
                            y = gmx * 0.0072 + gmy * -1.5857 + 622.4391;
                            break;
                        case "15":   // 苍穹海五层
                            x = gmx * 0.0038 + gmy * 1.5944 + 158.4757;
                            y = gmx * -1.5964 + gmy * 0.0008 + 632.1015;
                            break;
                        case "16":  //苍穹海六层
                            x = gmx * 1.6193 + gmy * -0.0048 + 138.5786;
                            y = gmx * -0.0006 + gmy * 1.6138 + 82.7988;
                            break;
                        case "21":     //昆仑墟一层
                            x = gmx * 2.6547 + gmy * 0.004 + 171.0579;
                            y = gmx * 0.0079 + gmy * 2.6665 + 86.6366;
                            break;
                        case "22":     //昆仑墟二层
                            x = gmx * 0.001 + gmy * -2.6549 + 695.6343;
                            y = gmx * 2.6683 + gmy * -0.0019 + 93.8995;
                            break;
                        case "23":      //昆仑墟三层
                            x = gmx * -2.6632 + gmy * -0.0064 + 692.8985;
                            y = gmx * 0.0082 + gmy * -2.658 + 617.975;
                            break;
                        case "24":         //昆仑墟四层
                            x = gmx * 0.0002 + gmy * 2.6617 + 165.1822;
                            y = gmx * -2.657 + gmy * -0.0074 + 614.282;
                            break;
                        case "25":         // 昆仑墟五层
                            x = gmx * 2.718 + gmy * 0.0123 + 168.3061;
                            y = gmx * 0.0034 + gmy * 2.7155 + 79.0227;
                            break;
                        case "31":        //   孽龙囚一层
                            x = gmx * 2.667 + gmy * -0.0059 + 162.8442;
                            y = gmx * 0.007 + gmy * 2.6588 + 79.1648;
                            break;
                        case "32":       // 孽龙囚二层
                            x = gmx * 0.0044 + gmy * -2.6556 + 705.2523;
                            y = gmx * 2.6605 + gmy * -0.0091 + 85.8399;
                            break;
                        case "33":        //孽龙囚三层
                            x = gmx * -2.6742 + gmy * 0.0073 + 700.1739;
                            y = gmx * 0.0096 + gmy * -2.6657 + 627.5776;
                            break;
                        case "34":        //孽龙囚四层
                            x = gmx * -0.005 + gmy * 2.652 + 158.8456;
                            y = gmx * -2.6477 + gmy * 0.0189 + 618.8376;
                            break;
                        case "35":         //孽龙囚五层
                            x = gmx * 2.5664 + gmy * -0.003 + 174.8193;
                            y = gmx * -0.0025 + gmy * 2.5807 + 95.5823;
                            break;
                        case "41":         // 葬魂谷一层
                            x = gmx * 2.6423 + gmy * 0.0021 + 160.4783;
                            y = gmx * 0.0007 + gmy * 2.6464 + 89.0966;
                            break;
                        case "42":         // 葬魂谷二层
                            x = gmx * 0.0002 + gmy * -2.6502 + 694.9322;
                            y = gmx * 2.6484 + gmy * -0.0096 + 84.4862;
                            break;
                        case "43":           // 葬魂谷三层
                            x = gmx * -2.6469 + gmy * -0.0039 + 701.1267;
                            y = gmx * -0.0006 + gmy * -2.65 + 617.4616;
                            break;
                        case "44":           // 葬魂谷四层
                            x = gmx * -0.0014 + gmy * 2.6503 + 166.3397;
                            y = gmx * -2.6523 + gmy * 0.0008 + 624.0532;
                            break;
                        case "45":         // 葬魂谷五层
                            x = gmx * 2.6766 + gmy * -0.0056 + 170.5767;
                            y = gmx * -0.0003 + gmy * 2.6793 + 80.1594;
                            break;
                        case "51":           // 霜火岭一层
                            x = gmx * 1.5339 + gmy * -0.0007 + 158.3344;
                            y = gmx * -0.0018 + gmy * 1.5406 + 92.0027;
                            break;
                        case "52":           // 霜火岭二层
                            x = gmx * -0.0023 + gmy * -1.5362 + 693.0572;
                            y = gmx * 1.5345 + gmy * -0.0007 + 81.5459;
                            break;
                        case "53":           //霜火岭三层
                            x = gmx * -1.5346 + gmy * -0.0037 + 704.1056;
                            y = gmx * -0.0011 + gmy * -1.5331 + 614.866;
                            break;
                        case "54":              //霜火岭四层
                            x = gmx * -0.0008 + gmy * 1.5377 + 170.3071;
                            y = gmx * -1.5391 + gmy * -0.001 + 627.8695;
                            break;
                        case "55":               //  霜火岭五层
                            x = gmx * 2.75 + gmy * -0.0 + 157.75;
                            y = gmx * 0.0062 + gmy * 2.7422 + 78.7247;
                        case "56":               //  霜火岭六层
                            x = gmx * 2.75 + gmy * -0.0 + 157.75;
                            y = gmx * 0.0062 + gmy * 2.7422 + 78.7247;
                            break;
                        case "81":           // t霜火岭一层
                            x = gmx * 1.5339 + gmy * -0.0007 + 158.3344;
                            y = gmx * -0.0018 + gmy * 1.5406 + 92.0027;
                            break;
                        case "82":           // t霜火岭二层
                            x = gmx * -0.0023 + gmy * -1.5362 + 693.0572;
                            y = gmx * 1.5345 + gmy * -0.0007 + 81.5459;
                            break;
                        case "83":           //t霜火岭三层
                            x = gmx * -1.5346 + gmy * -0.0037 + 704.1056;
                            y = gmx * -0.0011 + gmy * -1.5331 + 614.866;
                            break;
                        case "84":              //t霜火岭四层
                            x = gmx * -0.0008 + gmy * 1.5377 + 170.3071;
                            y = gmx * -1.5391 + gmy * -0.001 + 627.8695;
                            break;
                        case "85":               //  t霜火岭五层
                            x = gmx * 2.75 + gmy * -0.0 + 157.75;
                            y = gmx * 0.0062 + gmy * 2.7422 + 78.7247;
                            break;
                        case "61":              //噬灵渊一层
                            x = gmx * 2.3668 + gmy * -0.003 + 173.6987;
                            y = gmx * 0.0096 + gmy * 2.3445 + 87.5362;
                            break;
                        case "62":             //噬灵渊二层
                            x = gmx * -0.0044 + gmy * -2.365 + 699.2008;
                            y = gmx * 2.364 + gmy * -0.0005 + 96.7057;
                            break;
                        case "63":             //噬灵渊三层
                            x = gmx * -2.3614 + gmy * -0.0102 + 689.0948;
                            y = gmx * -0.0109 + gmy * -2.3531 + 621.0272;
                            break;
                        case "64":              //噬灵渊四层
                            x = gmx * 0.0003 + gmy * 2.3596 + 163.6336;
                            y = gmx * -2.3506 + gmy * -0.0093 + 609.7954;
                            break;
                        case "65":              //噬灵渊五层
                            x = gmx * 2.2561 + gmy * 0.0029 + 150.8067;
                            y = gmx * -0.0104 + gmy * 2.2572 + 75.5995;
                            break;
                        case "71":              //天外噬灵渊一层
                            x = gmx * 2.3668 + gmy * -0.003 + 173.6987;
                            y = gmx * 0.0096 + gmy * 2.3445 + 87.5362;
                            break;
                        case "72":             //天外噬灵渊二层
                            x = gmx * -0.0044 + gmy * -2.365 + 699.2008;
                            y = gmx * 2.364 + gmy * -0.0005 + 96.7057;
                            break;
                        case "73":             //天外噬灵渊三层
                            x = gmx * -2.3614 + gmy * -0.0102 + 689.0948;
                            y = gmx * -0.0109 + gmy * -2.3531 + 621.0272;
                            break;
                        case "74":              //天外噬灵渊四层
                            x = gmx * 0.0003 + gmy * 2.3596 + 163.6336;
                            y = gmx * -2.3506 + gmy * -0.0093 + 609.7954;
                            break;
                        case "75":              //天外噬灵渊五层
                            x = gmx * 2.3668 + gmy * -0.003 + 173.6987;
                            y = gmx * 0.0096 + gmy * 2.3445 + 87.5362;
                            break;
                        case "91":              //焚天台一层
                            x = gmx * 1.5212 + gmy * -0.006 + 174.9125;
                            y = gmx * 0.0027 + gmy * 1.5122 + 83.1501;
                            break;
                        case "92":             //焚天台二层
                            x=gmx *-0.0008+gmy *-1.5389+705.459;
                            y=gmx *1.5288+gmy *0.0019+92.3948;
                            break;
                        case "93":           //焚天台三层
                            x = gmx * -1.5165 + gmy * 0.0034 + 687.1302;
                            y = gmx * -0.003 + gmy * -1.5118 + 624.5683;
                            break;
                        case "94":            //焚天台四层
                            x = gmx * 0.0051 + gmy * 1.5123 + 159.8901;
                            y = gmx * -1.5193 + gmy * 0.0003 + 611.0996;
                            break;
                        case "95":             //焚天台五层
                            x = gmx * 1.6216 + gmy * -0.0381 + 186.8292;
                            y = gmx * -0.2162 + gmy * 1.2899 + 148.5258;
                            break;
                        case "101":              //天外焚天台一层
                            x=gmx *1.522+gmy *-0.0011+173.9675;
                            y=gmx *-0.0115+gmy *1.5315+80.9977;
                            break;
                        case "102":               //天外焚天台二层
                           /* x = gmx * -0.0029 + gmy * -1.5218 + 703.4934;
                            y = gmx * 1.5162 + gmy * -0.0011 + 97.8166;*/
                            x=gmx *0.0449+gmy *-1.5139+692.2997;
                            y=gmx *1.518+gmy *-0.0051+97.9785;
                            break;
                        case "103":                 //天外焚天台三层
                            /*x = gmx * -1.5165 + gmy * 0.0034 + 687.1302;
                            y = gmx * -0.003 + gmy * -1.5118 + 624.5683;*/
                            x=gmx *-1.5076+gmy *-0.0157+692.6053;
                            y=gmx *-0.0134+gmy *-1.5053+625.9975;
                            break;
                        case "104":                  //天外焚天台四层
                            /*x = gmx * 0.0051 + gmy * 1.5123 + 159.8901;
                            y = gmx * -1.5193 + gmy * 0.0003 + 611.0996;*/
                            x=gmx *0.003+gmy *1.4944+165.1303;
                            y=gmx *-1.5073+gmy *0.0152+609.0867;
                            break;
                        case "105":                  //天外焚天台五层
                            x = gmx * 1.6216 + gmy * -0.0381 + 186.8292;
                            y = gmx * -0.2162 + gmy * 1.2899 + 148.5258;
                            break;
                        case "106":                 //天外焚天台六层
                            x = gmx * -1.5165 + gmy * 0.0034 + 687.1302;
                            y = gmx * -0.003 + gmy * -1.5118 + 624.5683;
                            break;
                        case "107":                     //天外焚天台七层
                            x = gmx * 1.6578 + gmy * 0.0144 + 170.4418;
                            y = gmx * 0.012 + gmy * 1.6627 + 32.759;
                            break;
                        case "111":                     //氏族焚天台1层
                            if (LimitlessTask.szmap==1){
                                // t霜火岭一层
                                x=x=gmx *1.5254+gmy *-0.0019+159.1066;
                                y=y=gmx *0.0036+gmy *1.5408+87.852;
                            }
                            if (LimitlessTask.szmap==2){
                                //天外噬灵渊一层
                                x=gmx *2.3897+gmy *-0.0+171.4118;
                                y=gmx *0.0197+gmy *2.3228+87.9468;
                            }
                            if (LimitlessTask.szmap==3){
                                //天外焚天台一层
                                x=gmx *1.5025+gmy *0.008+178.9841;
                                y=gmx *0.0252+gmy *1.4942+81.4844;
                            }
                            break;
                        case "112":                     //氏族焚天台2层
                            if (LimitlessTask.szmap==1){
                                // t霜火岭二层
                                x=gmx *-1.5286+gmy *-0.0119+703.8489;
                                y=gmx *0.0104+gmy *-1.5088+608.1282;
                            }
                            if (LimitlessTask.szmap==2){
                                //天外噬灵渊二层
                                LtLog.e("天外噬灵渊二层");
                                x=gmx *-2.3804+gmy *0.006+688.0664;
                                y=gmx *0.0155+gmy *-2.393+622.206;
                            }
                            if (LimitlessTask.szmap==3){
                                //天外焚天台二层
                                LtLog.e("天外焚天台二层");
                                x=gmx *0.0449+gmy *-1.5139+692.2997;
                                y=gmx *1.518+gmy *-0.0051+97.9785;
                            }
                            break;
                        case "113":                     //氏族焚天台三层
                            if (LimitlessTask.szmap==1){
                                // t霜火岭三层
                                x = gmx * -1.5346 + gmy * -0.0037 + 704.1056;
                                y = gmx * -0.0011 + gmy * -1.5331 + 614.866;
                            }
                            if (LimitlessTask.szmap==2){
                                //天外噬灵渊三层
                                x = gmx * -2.3614 + gmy * -0.0102 + 689.0948;
                                y = gmx * -0.0109 + gmy * -2.3531 + 621.0272;
                            }
                            if (LimitlessTask.szmap==3){
                                //天外焚天台三层
                                x=gmx *-1.5076+gmy *-0.0157+692.6053;
                                y=gmx *-0.0134+gmy *-1.5053+625.9975;
                            }
                            break;
                        case "114":                     //氏族焚天台四层
                            if (LimitlessTask.szmap==1){
                                // t霜火岭四层
                                x=gmx *2.246+gmy *40.8597+-2580.4091;
                                y=gmx *-0.8666+gmy *11.1107+-164.8182;
                            }
                            if (LimitlessTask.szmap==2){
                                //天外噬灵渊四层
                                x=gmx *-0.0128+gmy *2.298+174.8578;
                                y=gmx *-2.3424+gmy *-0.0167+609.5309;
                            }
                            if (LimitlessTask.szmap==3){
                                //天外焚天台四层
                                x=gmx *0.003+gmy *1.4944+165.1303;
                                y=gmx *-1.5073+gmy *0.0152+609.0867;
                            }
                            break;
                        case "115":                     //氏族焚天台五层
                            if (LimitlessTask.szmap==1){
                                // t霜火岭五层
                                x=gmx *-1.5375+gmy *0.0089+692.6524;
                                y=gmx *0.0102+gmy *-1.5013+621.6982;
                            }
                            if (LimitlessTask.szmap==2){
                                //天外噬灵渊五层
                                x=gmx *2.3897+gmy *-0.0+171.4118;
                                y=gmx *0.0197+gmy *2.3228+87.9468;
                            }
                            if (LimitlessTask.szmap==3){
                                //天外焚天台五层
                                x=gmx *1.5014+gmy *-0.0033+179.4989;
                                y=gmx *0.0159+gmy *1.497+85.2302;
                            }
                            break;
                        case "116":                     //氏族焚天台六层
                            if (LimitlessTask.szmap==1){
                                // t霜火岭六层
                                x=gmx *-1.5046+gmy *-0.0096+690.2399;
                                y=gmx *0.0808+gmy *-1.5644+618.4503;
                            }
                            if (LimitlessTask.szmap==2){
                                //天外噬灵渊六层
                                x=gmx *2.3897+gmy *-0.0+171.4118;
                                y=gmx *0.0197+gmy *2.3228+87.9468;
                            }
                            if (LimitlessTask.szmap==3){
                                //天外焚天台六层
                                x=gmx *-1.5046+gmy *-0.0096+690.2399;
                                y=gmx *0.0808+gmy *-1.5644+618.4503;
                            }
                            break;
                        case "117":                     //氏族焚天台七层
                            if (LimitlessTask.szmap==1){
                                // t霜火岭七层
                                x=gmx *1.7924+gmy *0.1941+117.2445;
                                y=gmx *-0.0142+gmy *1.62+48.7285;
                            }
                            if (LimitlessTask.szmap==2){
                                //天外噬灵渊七层
                                x=gmx *2.3897+gmy *-0.0+171.4118;
                                y=gmx *0.0197+gmy *2.3228+87.9468;
                            }
                            if (LimitlessTask.szmap==3){
                                //天外焚天台七层
                                x=gmx *1.7924+gmy *0.1941+117.2445;
                                y=gmx *-0.0142+gmy *1.62+48.7285;
                            }
                            break;
                        case "121":                     //山海界
                            x=gmx *1.0985+gmy *0.0011+89.3976;
                            y=gmx *0.0003+gmy *1.0989+98.6534;
                            break;
                        case "122":                     //山海界
                            x=gmx *1.0985+gmy *0.0011+89.3976;
                            y=gmx *0.0003+gmy *1.0989+98.6534;
                            break;
                        case "123":                     //山海界
                            x=gmx *1.0985+gmy *0.0011+89.3976;
                            y=gmx *0.0003+gmy *1.0989+98.6534;
                            break;
                        case "124":                     //炎居
                            x=gmx *1.4453+gmy *0.0206+145.102;
                            y=gmx *-0.0104+gmy *1.443+66.5393;
                            break;
                        case "125":                     //焰狱
                            x=gmx *1.0399+gmy *-0.0067+171.9585;
                            y=gmx *0.0073+gmy *1.0211+93.9884;
                            break;
                        case "126":                     //白宫
                            x=gmx *1.0091+gmy *-0.0081+173.3923;
                            y=gmx *-0.2948+gmy *1.261+84.3167;
                            break;
                        case "127":                     //彼岸
                            x=gmx *1.4802+gmy *0.0067+58.1713;
                            y=gmx *0.0003+gmy *1.4407+3.9497;
                            break;
                        case "128":                     //光阴
                            x=gmx *1.3275+gmy *0.0341+151.2855;
                            y=gmx *-0.013+gmy *1.3362+77.3632;
                            break;
                        case "129":                     //春祭
                            x=gmx *1.4179+gmy *-0.0077+172.9467;
                            y=gmx *0.0091+gmy *1.4284+91.8436;
                            break;
                        case "130":                     //雷鸣
                            x=gmx *0.0043+gmy *-1.0204+688.3809;
                            y=gmx *1.0255+gmy *-0.0215+105.2796;
                            break;
                        case "131":                     //黑白
                            x=gmx *1.2113+gmy *0.0082+119.4055;
                            y=gmx *-0.0007+gmy *1.2213+42.3979;
                            break;
                        case "132":                     //战殇
                            x=gmx *1.8102+gmy *-0.0089+164.8439;
                            y=gmx *0.0158+gmy *1.8277+91.1791;
                            break;
                        case "133":                     //银杏
                            x=gmx *1.4122+gmy *-0.0072+176.2793;
                            y=gmx *-0.0093+gmy *1.4244+95.8071;
                            break;
                        case "134":                     //苗疆
                            x=gmx *1.8592+gmy *0.0271+125.5754;
                            y=gmx *-0.0496+gmy *1.8928+55.231;
                            break;
                        case "135":                     //镜花
                            x=gmx *1.1498+gmy *0.0123+133.2012;
                            y=gmx *-0.0035+gmy *1.144+61.5077;
                            break;
                    }

                    LtLog.e("szmap=="+ LimitlessTask.szmap);
                    mFairy.tap((int) x, (int) y);
                    LtLog.e(mFairy.getLineInfo("点击屏幕坐标x=" + (int) x + ",y=" + (int) y));
                    mFairy.tap((int) x, (int) y);
                    Thread.sleep(1000);
                    mFairy.onTap(0.8f, result, 1240,42,1253,54, "关闭地图", Sleep);
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                //坐标三秒没变,则到达坐标点
                long dazeTime = mFairy.mMatTime(1222, 130, 50, 10, 0.9f);
                if (dazeTime > 2) {
                    mFairy.initMatTime();
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "定位坐标中");
    }//定位坐标

    public  void  baoshi()throws Exception{
        new GameUtil(mFairy){
            @Override
            public void content_0() throws Exception {
                close(0);
                setTaskName(1);return;
            }
            public void content_1() throws Exception {
                if (overtime(15,99)){
                    result = mFairy.findPic("zhuangbei.png");
                    mFairy.onTap(0.8f, result, 23,662,38,672,"装备", Sleep);
                    close(0);return;
                }
                result = mFairy.findPic(2,623,65,699,"Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result = mFairy.findPic(441,626,533,714,"zhuangbei.png");
                mFairy.onTap(0.8f, result, "装备", Sleep);

                result = mFairy.findPic(1151,167,1279,340,"baoshi.png");
                mFairy.onTap(0.8f, result, "宝石", Sleep);

                result = mFairy.findPic(883,510,1126,682,"xuanzebaoshi.png");
                mFairy.onTap(0.8f, result, "自动选择", Sleep);
                mFairy.onTap(0.8f, result, 981,636,1020,652,"升级", Sleep);

                result = mFairy.findPic(397,297,902,479,"bssj.png");
                mFairy.onTap(0.8f, result, 768,442,779,447,"宝石升级确定", Sleep);
            }
        }.taskContent(mFairy,"升级宝石任务中");
    }//升级宝石

    //防止扩展背包
    public  void  goods()throws Exception{
        new GameUtil(mFairy){
            @Override
            public void content_0() throws Exception {
                close(0);
                setTaskName(1);return;
            }
            int wpCount=0;
            public void content_1() throws Exception {
                if (overtime(9,99)){close(0);return;}
                result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                mFairy.onTap(0.8f, result, 1071,110,1087,121,"打开背包", 3000);

                result = mFairy.findPic(600,146,1137,529,"buluobi.png");
                LtLog.e("*********"+result.sim);

                result = mFairy.findPic(88,17,204,76,"baginface.png");
                if (result.sim>0.8f){
                    mFairy.onTap(0.8f, result, 632,96,663,111,"背包界面内", Sleep);
                    result = mFairy.findPic(600,146,1137,529,new  String[]{"yinpiao.png","jinpiao.png","jinpiao2.png","buluobi.png","buluo bi2.png"});
                    mFairy.onTap(0.8f, result, "物品", 2000);
                    if (result.sim>0.8f){
                        err--;
                        wpCount++;
                        if (wpCount>20){
                            setTaskEnd();return;
                        }
                    }else {
                        mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4,5,6,7}, 0, 1106,309, 629,301, 1000, 1500,2);
                    }

                    result=mFairy.findPic(646,328,765,366,"word hint lanwei.png");
                    mFairy.onTap(0.8f,result,508,435,516,443,"取消解锁栏位",1000);

                    result = mFairy.findPic(23, 31, 1248, 690, new String[]{"xmqjUse.png","shiyong1.png","shiyong2.png"});
                    mFairy.onTap(0.8f, result, "使用", Sleep);

                    result = mFairy.findPic(23, 31, 1248, 690, "duihuan.png");
                    mFairy.onTap(0.8f, result, "兑换", Sleep);

                    result = mFairy.findPic(281,198,1036,684,"qluse1.png");
                    result1=mFairy.findPic(646,328,765,366,"word hint lanwei.png");
                    if (result.sim>0.8f && result1.sim <0.8f){
                        mFairy.onTap(0.8f, result, "批量确定", Sleep);
                        result = mFairy.findPic(538,96,752,195,"cishuxianzhi.png");
                        mFairy.onTap(0.8f, result, 915,153,934,167,"次数限制",Sleep);
                        if (result.sim>0.8f){
                            mFairy.ranSwipe(1106,309, 629,301, 1000, (long) 1500,2);
                        }else {
                            Thread.sleep(4000);
                        }
                    }else {
                        mFairy.onTap(0.8f, result, 504,427,518,435,"取消222", Sleep);
                    }

                    result = mFairy.findPic("shanhaishop.png");
                    if (result.sim>0.8f){
                        LtLog.e(mFairy.getLineInfo("山海商店中"));
                        mFairy.onTap(0.8f, result, 173,153,188,164,"坡介石", Sleep);
                        mFairy.onTap(0.8f, result, 1091,463,1108,476,"最大", Sleep);
                        mFairy.onTap(0.8f, result, 968,597,989,611,"购买", Sleep);
                        mFairy.onTap(0.8f, result, 772,433,806,448,"确定", 5000);

                        mFairy.onTap(0.8f, result, 630,161,661,176,"精髓", Sleep);
                        mFairy.onTap(0.8f, result, 1091,463,1108,476,"最大", Sleep);
                        mFairy.onTap(0.8f, result, 968,597,989,611,"购买", Sleep);
                        mFairy.onTap(0.8f, result, 772,433,806,448,"确定", 5000);

                        mFairy.onTap(0.8f, result, 268,261,287,270,"神器", Sleep);
                        mFairy.onTap(0.8f, result, 1091,463,1108,476,"最大", Sleep);
                        mFairy.onTap(0.8f, result, 968,597,989,611,"购买", Sleep);
                        mFairy.onTap(0.8f, result, 772,433,806,448,"确定", 5000);

                        mFairy.onTap(0.8f, result, 584,245,614,259,"精魂", Sleep);
                        mFairy.onTap(0.8f, result, 1091,463,1108,476,"最大", Sleep);
                        mFairy.onTap(0.8f, result, 968,597,989,611,"购买", Sleep);
                        mFairy.onTap(0.8f, result, 772,433,806,448,"确定", 5000);

                        mFairy.onTap(0.8f, result, 154,368,169,380,"远古升级石", Sleep);
                        mFairy.onTap(0.8f, result, 1091,463,1108,476,"最大", Sleep);
                        mFairy.onTap(0.8f, result, 968,597,989,611,"购买", Sleep);
                        mFairy.onTap(0.8f, result, 772,433,806,448,"确定", 5000);

                        mFairy.onTap(0.8f, result, 510,369,523,380,"氏族藏宝图", Sleep);
                        mFairy.onTap(0.8f, result, 1091,463,1108,476,"最大", Sleep);
                        mFairy.onTap(0.8f, result, 968,597,989,611,"购买", Sleep);
                        mFairy.onTap(0.8f, result, 772,433,806,448,"确定", 5000);

                        mFairy.onTap(0.8f, result, 151,469,167,484,"高级氏族藏宝图", Sleep);
                        mFairy.onTap(0.8f, result, 1091,463,1108,476,"最大", Sleep);
                        mFairy.onTap(0.8f, result, 968,597,989,611,"购买", Sleep);
                        mFairy.onTap(0.8f, result, 772,433,806,448,"确定", 5000);

                        mFairy.onTap(0.8f, result, 505,474,519,488,"玲珑玉下品", Sleep);
                        mFairy.onTap(0.8f, result, 1091,463,1108,476,"最大", Sleep);
                        mFairy.onTap(0.8f, result, 968,597,989,611,"购买", Sleep);
                        mFairy.onTap(0.8f, result, 772,433,806,448,"确定", 5000);

                        mFairy.onTap(0.8f, result, 159,584,173,595,"温魂碎片", Sleep);
                        mFairy.onTap(0.8f, result, 1091,463,1108,476,"最大", Sleep);
                        mFairy.onTap(0.8f, result, 968,597,989,611,"购买", Sleep);
                        mFairy.onTap(0.8f, result, 772,433,806,448,"确定", 5000);

                        mFairy.onTap(0.8f, result, 1143,53,1162,64,"关闭", Sleep);

                        result = mFairy.findPic("dhjsure.png");
                        mFairy.onTap(0.8f, result, 639,434,669,446,"确定", 5000);
                        mFairy.onTap(0.8f, result, 1143,53,1162,64,"关闭", Sleep);

                        result = mFairy.findPic("quehuo.png");
                        mFairy.onTap(0.8f, result, 633,429,671,445,"确定", 5000);
                        mFairy.onTap(0.8f, result, 1143,53,1162,64,"关闭", Sleep);

                        mFairy.ranSwipe(1106,309, 629,301, 1000, (long) 1500,2);
                    }
                    for (int i=0;i<2;i++){
                        mFairy.condit();
                        result = mFairy.findPic(281,198,1036,684,"plquxiao.png");
                        if (result.sim>0.8f){
                            LtLog.e(mFairy.getLineInfo("使用中"));
                            i=0;
                        }
                    }
                }
            }
        }.taskContent(mFairy,"物品的使用中");
    } //物品的使用

    public  void  goods2()throws Exception{
        new GameUtil(mFairy){
            @Override
            public void content_0() throws Exception {
                close(0);
                if (AtFairyConfig.getOption("qtwp").equals("1")){
                    close(0);
                    setTaskName(1);return;
                }else if (AtFairyConfig.getOption("twshj").equals("1")){
                    close(0);
                    setTaskName(2);return;
                }else{
                    setTaskEnd();
                    return;
                }
            }
            int wpCount=0;
            public void content_1() throws Exception {
                if (overtime(10,99)){close(0);return;}
                result1 = mFairy.findPic(883,21,1127,219,"bb.png");
                mFairy.onTap(0.8f, result1, "打开背包", 3000);
                result = mFairy.findPic(9,6,326,107,"baginface.png");
                if (result.sim>0.8f){
                    mFairy.onTap(0.8f, result, 632,96,663,111,"背包界面内", Sleep);//472,380  637，380

                    result = mFairy.findPic(1023,423,1138,537,new String[]{"kong.png","cezhan.png"});
                    if (result.sim>0.9f){
                        err=6;
                    }

                    result = mFairy.findPic(586,229,667,422,"cezhan.png");
                    mFairy.onTap(0.8f, result, "收回侧展", 2000);

                    result = mFairy.findPic(600,146,1137,529,new String[]{"box3.png","box5.png"});
                    mFairy.onTap(0.8f, result, "箱子", 2000);


                    result = mFairy.findPic(23, 31, 1248, 690, new String[]{"xmqjUse.png","shiyong1.png","shiyong2.png"});
                    mFairy.onTap(0.8f, result, "使用1", 500);
                    result = mFairy.findPic(524,443,747,551,"qluse1.png");
                    result1=mFairy.findPic(566,134,731,211,"plsy.png");
                    if (result.sim>0.8f && result1.sim >0.8f){
                        mFairy.onTap(0.8f, result, "批量确定", 1000);

                        result = mFairy.findPic(459,9,820,252,new String[]{"cishuxianzhi.png"});
                        if (result.sim>0.8f){
                            result = mFairy.findPic(849,102,1053,259, "cha11.png");
                            mFairy.onTap(0.8f, result, "次数限制关闭", Sleep);

                            mFairy.ranSwipe(1106,309, 629,301, 1000, (long) 1500,2);
                            err++;
                        }else {
                            Thread.sleep(1000);
                        }
                    }

                    result = mFairy.findPic(37,54,785,587,"box4.png");
                    mFairy.onTap(0.8f, result, result.x+255,result.y+36,result.x+257,result.y+39,"开起箱子", 1000);//676,385,684,392    421 349

                    result2 = mFairy.findPic(600,146,1137,529,new  String[]{"yinpiao.png","buluo bi2.png","daizi.png"});
                    result3 = mFairy.findPic(600,146,1137,529,new  String[]{"jinpiao.png","jinpiao2.png"});
                    mFairy.onTap(0.8f, result2, "物品1", 1000);
                    result = mFairy.findPic(23, 31, 1248, 690, new String[]{"xmqjUse.png","shiyong1.png","shiyong2.png"});
                    if (result.sim>0.8f){
                        mFairy.onTap(0.8f, result, "使用", Sleep);
                    }else{
                        mFairy.onTap(0.8f, result2, result2.x-165,result2.y-2,result2.x-164,result2.y-1,"使用", 1000);
                    }
                    if (result2.sim>0.8f){
                        err=0;
                        result = mFairy.findPic(281,198,1036,684,"qluse1.png");
                        result1=mFairy.findPic(646,328,765,366,"word hint lanwei.png");
                        if (result.sim>0.8f && result1.sim <0.8f){
                            mFairy.onTap(0.8f, result, "批量确定", 1000);

                            result = mFairy.findPic(459,9,820,252,new String[]{"cishuxianzhi.png","wfsy.png"});
                            if (result.sim>0.8f){
                                result = mFairy.findPic(849,102,1053,259, "cha11.png");
                                mFairy.onTap(0.8f, result, "次数限制关闭", Sleep);
                                mFairy.ranSwipe(1106,309, 629,301, 1000, (long) 1500,2);
                                err++;
                            }else {
                                Thread.sleep(1000);
                            }
                        }else {
                            mFairy.onTap(0.8f, result, 504,427,518,435,"取消222", Sleep);
                        }

                    }else if (result3.sim>0.8f){
                        mFairy.onTap(0.8f, result3, "物品2", 1000);
                        mFairy.onTap(0.8f, result3, result3.x-165,result3.y-2,result3.x-164,result3.y-1,"使用3", 1000);

                        result = mFairy.findPic(524,443,747,551,"qluse1.png");
                        result1=mFairy.findPic(566,134,731,211,"plsy.png");
                        if (result.sim>0.8f && result1.sim >0.8f){
                            mFairy.onTap(0.8f, result, "批量确定", 1000);

                            result = mFairy.findPic(459,9,820,252,new String[]{"cishuxianzhi.png"});
                            if (result.sim>0.8f){
                                result = mFairy.findPic(849,102,1053,259, "cha11.png");
                                mFairy.onTap(0.8f, result, "次数限制关闭", Sleep);

                                mFairy.ranSwipe(1106,309, 629,301, 1000, (long) 1500,2);
                                err++;
                            }else {
                                Thread.sleep(1000);
                            }
                        }

                        result = mFairy.findPic(459,9,820,252,"cishuxianzhi.png");
                        if (result.sim>0.8f){
                            mFairy.ranSwipe(1106,309, 629,301, 1000, (long) 1500,2);
                            err++;
                        }
                    }else{
                        mFairy.taskSlid(err, new int[]{0, 1, 2, 3, 4,5,6,7}, 0, 1106,309, 629,301, 1000, 1500,2);
                        result=mFairy.findPic(952,530,994,570,new String[]{"zuihou.png","suo.png"});
                        result1=mFairy.findPic(1026,431,1126,527,"kong.png");
                        if (result.sim > 0.8f ||result1.sim > 0.8f ){
                            err=7;
                            LtLog.e("最后一页");
                        }
                        if (err >= 7){
                                if (AtFairyConfig.getOption("twshj").equals("1")){
                                    close(0);
                                    setTaskName(2);return;
                                }else {
                                    setTaskEnd();
                                    return;
                                }
                            }
                    }

                    result=mFairy.findPic(646,328,765,366,"word hint lanwei.png");
                    mFairy.onTap(0.8f,result,508,435,516,443,"取消解锁栏位",1000);

                    result = mFairy.findPic(23, 31, 1248, 690, new String[]{"xmqjUse.png","shiyong1.png","shiyong2.png"});
                    mFairy.onTap(0.8f, result, "使用2", 500);

                    result = mFairy.findPic(23, 31, 1248, 690, "duihuan.png");
                    mFairy.onTap(0.8f, result, "兑换", Sleep);

                    result = mFairy.findPic(444,113,703,677, "bhdhj.png");
                    mFairy.onTap(0.8f, result, result.x+364,result.y+25,result.x+373,result.y+33,"兑换1", Sleep);

                    for (int i=0;i<2;i++){
                        mFairy.condit();
                        result = mFairy.findPic(281,198,1036,684,"plquxiao.png");
                        if (result.sim>0.8f){
                            LtLog.e(mFairy.getLineInfo("使用中"));
                            i=0;
                        }
                    }
                }

            }
            int sum=0;
            public void content_2() throws Exception {
                if (overtime(9,99)){close(0);return;}
                result = mFairy.findPic(389,288,892,468,new String[]{"juanbuzu.png","bsslbz.png"});
                mFairy.onTap(0.8f, result, 641,435,649,446,"卷不足确定结束", 3000);
                if (result.sim > 0.8f){
                    LtLog.e(mFairy.getLineInfo("数量不足"));
                    close(0);return;
                }


                result = mFairy.findPic(883,21,1127,219,"bb.png");
                if (result.sim > 0.8f){
                    result1 = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});
                    if (result1.sim > 0.8f){
                            mFairy.onTap(1123, 327, 1134, 339, "关闭自动战斗", 500);
                    }
                    mFairy.onTap(0.8f, result,"打开背包", 2000);
                }

                result = mFairy.findPic(65,1,239,93,"baginface.png");
                if (result.sim > 0.8f){
                    mFairy.onTap(0.8f, result, 632,96,663,111,"背包界面内", Sleep);
                    LtLog.e(mFairy.getLineInfo("err====="+err));
                    result = mFairy.findPic(600,146,1137,529,"bhsms.png");
                    result1 = mFairy.findPic(600,146,1137,529,"bhshj.png");
                    if (result.sim > 0.8f){
                        mFairy.onTap(0.8f, result, "兑换券0", 1000);
                    }else if (result1.sim > 0.8f){
                        mFairy.onTap(0.8f, result1, "兑换券1", 1000);
                    }else{
                        result=mFairy.findPic(952,530,994,570,new String[]{"zuihou.png","suo.png"});
                        result1=mFairy.findPic(1026,431,1126,527,"kong.png");
                        if (result.sim > 0.8f ||result1.sim > 0.8f ){
                            LtLog.e("最后一页");
                            close(0);return;
                        }
                        mFairy.taskSlid(err, new int[]{0,1,2,3,4,5,6,7,8}, 0, 1106,309, 629,301, 1000, 1500,3);
                    }
                    LtLog.e(mFairy.getLineInfo("err====="+err));
                    result=mFairy.findPic(646,328,765,366,"word hint lanwei.png");
                    mFairy.onTap(0.8f,result,508,435,516,443,"取消解锁栏位",1000);


                    result1 = mFairy.findPic(444,113,703,677, "bhdhj.png");
                    mFairy.onTap(0.8f, result1, result1.x+364,result1.y+25,result1.x+373,result1.y+33,"兑换1", Sleep);

                    result = mFairy.findPic(23, 31, 1248, 690, new String[]{"duihuan1.png","duihuan.png","dh.png"});
                    mFairy.onTap(0.8f, result, "兑换", Sleep);


                    if (result.sim > 0.8f||result1.sim > 0.8f){
                        err = 0;
                    }
                }


                result = mFairy.findPic(101,97,834,660,new String[]{"hy.png","tsww.png","sys.png"});
                if (result.sim>0.8f) {
                    LtLog.e(mFairy.getLineInfo("山海商店中"));
                    sum++;
                    if (AtFairyConfig.getOption("sys").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"sys.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "神原石", 1000);
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){
                            }else {
                                mFairy.onTap(0.8f, result1, 1085,460,1123,484,"最大", 1000);
                                mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                                mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 3000);
                            }
                        }
                    }//神原石
                    if (AtFairyConfig.getOption("hy").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"hy.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "荒玉", 1000);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1085,460,1123,484,"最大", 1000);
                                mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                                mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 5000);
                            }
                        }
                    }//荒玉
                    if (AtFairyConfig.getOption("pjsrc").equals("1")){//171,162   332,107,470,215
                        result1 = mFairy.findPic(107,106,832,661,"shanhaishop.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "坡介石", Sleep);
                            /*result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");*/
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {
                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }

                    }//坡介石
                    if (AtFairyConfig.getOption("jsrc").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"jingcui.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "精髓", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }

                    }//精髓
                    if (AtFairyConfig.getOption("sqrc").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"shenqiyg.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "神器", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//神器
                    if (AtFairyConfig.getOption("jhec").equals("1")){
                        result1 = mFairy.findPic(110,104,831,432,"jinghun.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "精魂", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {
                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//精魂
                    if (AtFairyConfig.getOption("tgssd").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"tgssd.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "太古神石（低级）", 1000);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1092,469,1116,479,"最大", 1000);
                                mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                                mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 5000);
                            }
                        }
                    }//太古神石低
                    if (AtFairyConfig.getOption("ygsjsrc").equals("1")){
                        result1 = mFairy.findPic(106,424,823,644,"jinghun.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "远古升级石", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {
                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//远古升级石
                    if (AtFairyConfig.getOption("szbztrc").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"baozanghls.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "氏族藏宝图", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {
                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//氏族藏宝图
                    if (AtFairyConfig.getOption("gjszbztrc").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"baozang2hls.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "高级氏族藏宝图", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//高级氏族
                    if (AtFairyConfig.getOption("llyxprc").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"linglong.png");
                        if (result1.sim > 0.7f){
                            mFairy.onTap(0.8f, result1, "玲珑玉下品", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//玲珑玉下
                    if (AtFairyConfig.getOption("whsprc").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"wenhun.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "温魂碎片", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//温魂碎片
                    if (AtFairyConfig.getOption("hyjqd").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"hunyuan.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "混元聚气丹", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//混元聚气丹
                    if (AtFairyConfig.getOption("tsww").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"tsww.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "替身娃娃", 1000);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1085,460,1123,484,"最大", 1000);
                                mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                                mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 5000);
                            }
                        }
                    }//替身娃娃
                    result1 = mFairy.findPic(107,106,832,661,"ygsjjs.png");
                    if (result1.sim > 0.8f){
                        mFairy.onTap(0.8f, result1, "神器瑰宝", 1000);
                        //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                        result = mFairy.findPic(966,166,1096,446,"0.png");
                        if (result.sim < 0.8f){
                            mFairy.onTap(0.8f, result1, 1085,460,1123,484,"最大", 1000);
                            mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                            mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 5000);
                        }
                    }//远古神级精髓
                    result1 = mFairy.findPic(107,106,832,661,"bhjxlb.png");
                    if (result1.sim > 0.8f){
                        mFairy.onTap(0.8f, result1, "惊喜礼包", 1000);
                        //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                        result = mFairy.findPic(966,166,1096,446,"0.png");
                        if (result.sim < 0.8f){
                            mFairy.onTap(0.8f, result1, 1085,460,1123,484,"最大", 1000);
                            mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                            mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 5000);
                        }
                    }//惊喜礼包
                    result1 = mFairy.findPic(107,106,832,661,"sjs.png");
                    if (result1.sim > 0.8f){
                        mFairy.onTap(0.8f, result1, "神器瑰宝", 1000);
                        //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                        result = mFairy.findPic(966,166,1096,446,"0.png");
                        if (result.sim < 0.8f){
                            mFairy.onTap(0.8f, result1, 1085,460,1123,484,"最大", 1000);
                            mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                            mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 5000);
                        }
                    }//神器瑰宝
                    result = mFairy.findPic(389,288,892,468,new String[]{"juanbuzu.png","bsslbz.png"});
                    mFairy.onTap(0.8f, result, 641,435,649,446,"卷不足确定结束", 3000);
                    if (result.sim > 0.8f){
                        LtLog.e(mFairy.getLineInfo("数量不足"));
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(381,273,904,489,"quehuo.png");
                    mFairy.onTap(0.8f, result, 640,435,645,446,"确定", 5000);
                    LtLog.e("滑动=====");
                    mFairy.ranSwipe(500,567,501,200,300,Sleep);
                    LtLog.e("滑动=====");
                    mFairy.ranSwipe(500,567,501,200,300,Sleep);
                    if (AtFairyConfig.getOption("sys").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"sys.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "神原石", 1000);
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){
                            }else {
                                mFairy.onTap(0.8f, result1, 1085,460,1123,484,"最大", 1000);
                                mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                                mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 3000);
                            }
                        }
                    }//神原石
                    if (AtFairyConfig.getOption("hy").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"hy.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "荒玉", 1000);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1085,460,1123,484,"最大", 1000);
                                mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                                mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 5000);
                            }
                        }
                    }//荒玉
                    if (AtFairyConfig.getOption("pjsrc").equals("1")){//171,162   332,107,470,215
                        result1 = mFairy.findPic(107,106,832,661,"shanhaishop.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "坡介石", Sleep);
                            /*result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");*/
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {
                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }

                    }//坡介石
                    if (AtFairyConfig.getOption("jsrc").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"jingcui.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "精髓", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }

                    }//精髓
                    if (AtFairyConfig.getOption("sqrc").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"shenqiyg.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "神器", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//神器
                    if (AtFairyConfig.getOption("jhec").equals("1")){
                        result1 = mFairy.findPic(110,104,831,432,"jinghun.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "精魂", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {
                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//精魂
                    if (AtFairyConfig.getOption("tgssd").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"tgssd.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "太古神石（低级）", 1000);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1092,469,1116,479,"最大", 1000);
                                mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                                mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 5000);
                            }
                        }
                    }//太古神石低
                    if (AtFairyConfig.getOption("ygsjsrc").equals("1")){
                        result1 = mFairy.findPic(106,424,823,644,"jinghun.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "远古升级石", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {
                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//远古升级石
                    if (AtFairyConfig.getOption("szbztrc").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"baozanghls.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "氏族藏宝图", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {
                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//氏族藏宝图
                    if (AtFairyConfig.getOption("gjszbztrc").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"baozang2hls.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "高级氏族藏宝图", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//高级氏族
                    if (AtFairyConfig.getOption("llyxprc").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"linglong.png");
                        if (result1.sim > 0.7f){
                            mFairy.onTap(0.8f, result1, "玲珑玉下品", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//玲珑玉下
                    if (AtFairyConfig.getOption("whsprc").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"wenhun.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "温魂碎片", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//温魂碎片
                    if (AtFairyConfig.getOption("hyjqd").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"hunyuan.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "混元聚气丹", Sleep);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1091,463,1108,476,"最大", Sleep);
                                mFairy.onTap(0.8f, result1, 968,597,989,611,"购买", Sleep);
                                mFairy.onTap(0.8f, result1, 772,433,806,448,"确定", 5000);
                            }
                        }
                    }//混元聚气丹
                    if (AtFairyConfig.getOption("tsww").equals("1")){
                        result1 = mFairy.findPic(107,106,832,661,"tsww.png");
                        if (result1.sim > 0.8f){
                            mFairy.onTap(0.8f, result1, "替身娃娃", 1000);
                            //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                            result = mFairy.findPic(966,166,1096,446,"0.png");
                            if (result.sim > 0.8f){

                            }else {

                                mFairy.onTap(0.8f, result1, 1085,460,1123,484,"最大", 1000);
                                mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                                mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 5000);
                            }
                        }
                    }//替身娃娃
                    result1 = mFairy.findPic(107,106,832,661,"ygsjjs.png");
                    if (result1.sim > 0.8f){
                        mFairy.onTap(0.8f, result1, "神器瑰宝", 1000);
                        //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                        result = mFairy.findPic(966,166,1096,446,"0.png");
                        if (result.sim < 0.8f){
                            mFairy.onTap(0.8f, result1, 1085,460,1123,484,"最大", 1000);
                            mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                            mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 5000);
                        }
                    }//远古神级精髓
                    result1 = mFairy.findPic(107,106,832,661,"bhjxlb.png");
                    if (result1.sim > 0.8f){
                        mFairy.onTap(0.8f, result1, "惊喜礼包", 1000);
                        //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                        result = mFairy.findPic(966,166,1096,446,"0.png");
                        if (result.sim < 0.8f){
                            mFairy.onTap(0.8f, result1, 1085,460,1123,484,"最大", 1000);
                            mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                            mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 5000);
                        }
                    }//惊喜礼包
                    result1 = mFairy.findPic(107,106,832,661,"sjs.png");
                    if (result1.sim > 0.8f){
                        mFairy.onTap(0.8f, result1, "神器瑰宝", 1000);
                        //result = mFairy.findPic(result1.x+161,result1.y-55,result1.x+299,result1.y+53,"shouwan.png");
                        result = mFairy.findPic(966,166,1096,446,"0.png");
                        if (result.sim < 0.8f){
                            mFairy.onTap(0.8f, result1, 1085,460,1123,484,"最大", 1000);
                            mFairy.onTap(0.8f, result1, 992,596,1022,616,"购买", 1000);
                            mFairy.onTap(0.8f, result1, 757,429,801,454,"确定", 5000);
                        }
                    }//神器瑰宝
                    result = mFairy.findPic(389,288,892,468,new String[]{"juanbuzu.png","bsslbz.png"});
                    mFairy.onTap(0.8f, result, 641,435,649,446,"卷不足确定结束", 3000);
                    if (result.sim > 0.8f){
                        LtLog.e(mFairy.getLineInfo("数量不足"));
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(381,273,904,489,"quehuo.png");
                    mFairy.onTap(0.8f, result, 640,435,645,446,"确定", 5000);
                    mFairy.ranSwipe(1106,309, 629,301, 1000, (long) 1500,2);
                }result=mFairy.findPic(952,530,994,570,new String[]{"zuihou.png","suo.png"});
                result1=mFairy.findPic(1026,431,1126,527,"kong.png");
                if (result.sim > 0.8f ||result1.sim > 0.8f ){
                    err=7;
                    LtLog.e("最后一页");
                }

                if (sum>=1){
                    sum=0;
                    setTaskEnd();
                    close(0);
                    return;
                }
            }
        }.taskContent(mFairy,"物品的使用中2222");
    }//使用物品222

    public void szcbt() throws  Exception{
        new GameUtil(mFairy){
            @Override
            public void content_0() throws Exception {
                close(0);
                twsh_back();
                setTaskName(1);
                return;
            }
            int wpCount=0;
            public void content_1() throws Exception {
                if (overtime(50, 0)) {
                    close(0);
                    return;
                }
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                result = mFairy.findPic(56,7,297,90, "smzd1.png");
                mFairy.onTap(0.8f, result, 1231,62,1238,74,"关闭神魔之巅", Sleep);

                result = mFairy.findPic(362, 163, 1084, 612, "qr.png");
                mFairy.onTap(0.8f, result, "确认", Sleep);

                result = mFairy.findPic(362, 163, 1084, 612, new String[]{"ljsy.png","ljsy1.png"});
                mFairy.onTap(0.8f, result, "立即使用", Sleep);

                result = mFairy.findPic(65,1,239,93,"baginface.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 632, 96, 663, 111, "背包界面内", Sleep);
                    mFairy.initMatTime();

                    result = mFairy.findPic(600, 146, 1137, 529, new String[]{"szbz.png","szbz1.png","szbz2.png","szbz3.png"});
                    mFairy.onTap(0.8f, result, "宝图", 1000);

                    result2 = mFairy.findPic(23, 31, 1248, 690, new String[]{"xmqjUse.png", "shiyong1.png", "shiyong2.png", "shiyong3.png"});
                    mFairy.onTap(0.8f, result2, "宝图使用", 500);
                    if (result2.sim > 0.8f) {
                        wpCount=0;
                    }

                    if (result.sim > 0.8f) {
                        err--;
                    } else {
                        mFairy.ranSwipe(1106, 309, 629, 301, 1000, (long) 1500, 1);
                        wpCount++;
                    }

                    result = mFairy.findPic(9,8,1271,711, new String[]{"diuqi.png"});
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(9,8,1271,711, new String[]{"szbz5.png"});
                        if (result.sim > 0.8f) {
                            result2 = mFairy.findPic(23, 31, 1248, 690, new String[]{"xmqjUse.png", "shiyong1.png", "shiyong2.png", "shiyong3.png"});
                            mFairy.onTap(0.8f, result2, "宝图使用", 500);
                            if (result2.sim > 0.8f) {
                                wpCount=0;
                            }
                        }else{
                            mFairy.onTap(127,36,138,44,"点击背包", 1000);
                        }
                    }
                }

                result1=mFairy.findPic(239,452,1075,659,"smzd2.png");
                result=mFairy.findPic(239,452,1075,659,"smzd3.png");
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    mFairy.onTap(1196,53,1207,63,"guanbi", 1000);
                }

                result = mFairy.findPic(1119,130,1274,408, new String[]{"3v3leave.png","3v3leave1.png"});
                if (result.sim > 0.9f) {
                    result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});
                    if (result.sim < 0.8f) {
                        mFairy.onTap(1123, 327, 1134, 339, "开启自动战斗", Sleep);
                    }
                }else if (dazeTime > 15) {
                    result = mFairy.findPic(883,21,1127,219,"bb.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "打开背包", 2000);
                        mFairy.initMatTime();
                    }
                }
                    result = mFairy.findPic(362, 163, 1084, 612, "qr.png");
                    mFairy.onTap(0.8f, result, "确认", Sleep);

                    result = mFairy.findPic(362, 163, 1084, 612, new String[]{"ljsy.png","ljsy1.png"});
                    mFairy.onTap(0.8f, result, "立即使用", Sleep);

                    result = mFairy.findPic(9, 6, 326, 107, "baginface.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, 632, 96, 663, 111, "背包界面内", Sleep);//472,380  637，380

                        result2 = mFairy.findPic(23, 31, 1248, 690, new String[]{"xmqjUse.png", "shiyong1.png", "shiyong2.png", "shiyong3.png"});
                        mFairy.onTap(0.8f, result2, "使用1", 500);
                        if (result.sim > 0.8f) {
                            wpCount=0;
                        }

                        result = mFairy.findPic(586, 229, 667, 422, "cezhan.png");
                        mFairy.onTap(0.8f, result, "收回侧展", 2000);

                        result=mFairy.findPic(952,530,994,570,new String[]{"zuihou.png","suo.png"});
                        result1=mFairy.findPic(1026,431,1126,527,"kong.png");
                        if (result.sim > 0.8f ||result1.sim > 0.8f ){
                            err=7;
                            LtLog.e("最后一页");
                            wpCount=11;
                        }
                        if (wpCount > 10) {
                            setTaskEnd();
                            return;
                        }
                        result = mFairy.findPic(362, 163, 1084, 612, new String[]{"ljsy.png","ljsy1.png"});
                        mFairy.onTap(0.8f, result, "立即使用", Sleep);

                        result = mFairy.findPic(646, 328, 765, 366, "word hint lanwei.png");
                        mFairy.onTap(0.8f, result, 508, 435, 516, 443, "取消解锁栏位", 1000);

                        result = mFairy.findPic(281, 198, 1036, 684, "qluse1.png");
                        result1 = mFairy.findPic(646, 328, 765, 366, "word hint lanwei.png");
                        if (result.sim > 0.8f && result1.sim < 0.8f) {
                            mFairy.onTap(0.8f, result, "批量确定", 500);

                            result = mFairy.findPic(459, 9, 820, 252, new String[]{"cishuxianzhi.png", "wfsy.png"});
                            if (result.sim > 0.8f) {
                                result = mFairy.findPic(849, 102, 1053, 259, "cha11.png");
                                mFairy.onTap(0.8f, result, "次数限制关闭", Sleep);
                            }
                            if (result.sim > 0.8f) {
                                mFairy.ranSwipe(1106, 309, 629, 301, 1000, (long) 1500, 1);
                            } else {
                                Thread.sleep(4000);
                            }
                        } else {
                            mFairy.onTap(0.8f, result, 504, 427, 518, 435, "取消222", Sleep);
                        }
                    }

            }

        }.taskContent(mFairy,"氏族宝图");
    }//氏族藏宝图


    public  void  clearBag()throws Exception{
        new GameUtil(mFairy){
            @Override
            public void content_0() throws Exception {
                close(0);
                setTaskName(1);
                return;
            }
            public void content_1() throws Exception {
                if (overtime(15,99)){close(0);return;}
                result = mFairy.findPic(883,21,1127,219,"bb.png");
                mFairy.onTap(0.8f, result, "打开背包", 3000);

                result = mFairy.findPic(9,6,326,107,"baginface.png");
                if (result.sim>0.8f){
                    mFairy.onTap(0.8f, result, 977,628,1017,643,"分解", Sleep);
                    mFairy.onTap(0.8f, result, 825,630,892,650,"自动选择", Sleep);
                    mFairy.onTap(0.8f, result, 736,506,789,525,"确认选择", Sleep);
                    result1 = mFairy.findPic("zise.png");
                    if (result1.sim>0.8f) {
                        mFairy.onTap(0.8f, result1, 744, 423, 818, 458, "紫色确认分解", Sleep);
                    }
                    result2 = mFairy.findPic(586,256,724,384,"chengse.png");
                    if (result2.sim>0.8f) {
                        mFairy.onTap(0.8f, result2, 489,425,546,456, "有橙色取消分解", Sleep);
                    }
                    mFairy.onTap(0.8f, result, 305,619,341,636,"确认分解", Sleep);
                    result = mFairy.findPic("baginface.png");
                    for (int i=0;i<3;i++){
                        mFairy.condit();
                        result = mFairy.findPic(258,241,1010,612,"bagquxiao.png");
                        mFairy.onTap(0.8f, result, "清背包err取消", Sleep);
                    }
                    close(0);
                    setTaskEnd();return;
                }
            }
        }.taskContent(mFairy,"分解清包中");
    }//分解清包

    public  void  cifu()throws Exception{
        new GameUtil(mFairy){
            @Override
            public void content_0() throws Exception {
                 close(0);
                setTaskName(1);return;
            }
            public void content_1() throws Exception {
                if (overtime(10,0))return;
                result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                mFairy.onTap(0.8f, result, 1074,34,1085,49, "日常", Sleep);


                result = mFairy.findPic(876, 5, 1120, 146, "welfare.png");
                mFairy.onTap(0.8f, result, "福利", Sleep);

                result = mFairy.findPic(104,21,277,85,new String[]{"WelfareInterface.png","WelfareInterface1.png","WelfareInterface2.png","WelfareInterface3.png" });
                if (result.sim>0.8f){
                    setTaskName(2);return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(10,99)){close(0);return;}

                result = mFairy.findPic(13,16,505,710,"cifu.png");
                mFairy.onTap(0.8f, result, "赐福", Sleep);

                result = mFairy.findPic(619,604,851,684,new  String[]{"jpReceive.png","jpReceive1.png" });
                mFairy.onTap(0.8f, result, "领取", Sleep);
            }
        }.taskContent(mFairy,"赐福中");
    }//赐福

    public  void  tansuo()throws Exception{
        new GameUtil(mFairy){
            @Override
            public void content_0() throws Exception {
                close(0);
                setTaskName(1);return;
            }
            public void content_1() throws Exception {
                if (overtime(6,99))return;

                result = mFairy.findPic("Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result = mFairy.findPic("Spoil.png");
                mFairy.onTap(0.8f, result, "灵宠", Sleep);

                result = mFairy.findPic("SpiritInterface.png");
                mFairy.onTap(0.8f, result, 1139,350,1159,368,"灵宠界面", Sleep);

                result = mFairy.findPic(155, 552, 1085, 681,"FreeCharge.png");
                mFairy.onTap(0.8f, result,  result.x, result.y - 30, result.x+1, result.y - 29,"灵宠免费", 7000);

                result = mFairy.findPic("initFork8.png");
                mFairy.onTap(0.8f, result, "叉", Sleep);
            }
        }.taskContent(mFairy,"赐福中");
    }//赐福

    public  void  tanxian()throws Exception{
        new TimingActivity(mFairy){
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);return;
            }
            public void content_1() throws Exception {
                if (overtime(10,0))return;

                result = mFairy.findPic("Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result=mFairy.findPic(530,103,767,242,new String[]{"pic lock.png","35ji.png"});
                if(result.sim>0.8f){
                    LtLog.e(mFairy.getLineInfo("灵宠没有解锁，任务结束"));
                    close(0);
                    setTaskEnd();return;
                }

                result = mFairy.findPic("Spoil.png");
                mFairy.onTap(0.8f, result, "灵宠", Sleep);

                result = mFairy.findPic(83,11,216,79,"SpiritInterface.png");
                int picCount=picCount(0.8f,result,"pet tanxian");
                if(picCount>=4){
                    LtLog.e(mFairy.getLineInfo("灵宠探险没有解锁，任务结束"));
                    mFairy.onTap(1214,43,1222,53,"关闭",1000);
                    close(0);
                    setTaskEnd();return;
                }
                mFairy.onTap(0.8f, result, 1212,508,1222,522,"灵宠界面切探险", Sleep);

                result=mFairy.findPic(1155,480,1279,575,"pic pet tanxian interface.png");
                if (result.sim>0.8f){
                    setTaskName(2);return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(8,3))return;

                result = mFairy.findPic(67,81,335,670, "txwc.png");
                mFairy.onTap(0.8f, result, "已完成", Sleep);

                result = mFairy.findPic(631,539,900,638,"dkbx.png");
                mFairy.onTap(0.8f, result, "打开宝箱", Sleep);
                if (result.sim>0.8f){
                    err=0;
                }

                result =mFairy.findPic(335,203,952,650,"tssure.png");
                mFairy.onTap(0.8f,result,"探索奖励确认",Sleep);
            }
            int rwCount=0,xzCount=0,num =0;
            public void content_3() throws Exception {
                result = mFairy.findPic("SpiritInterface.png");
                mFairy.onTap(0.8f, result, 1213,508,1219,519,"灵宠界面切探险", Sleep);
                if (result.sim>0.8f){
                    result1 = mFairy.findPic("notanxian.png");
                    if (result1.sim>0.8f){
                        LtLog.e(mFairy.getLineInfo("没有探险任务了结束"));
                        close(0);
                        setTaskEnd();return;
                    }
                    if (rwCount==0){
                        mFairy.onTap(0.8f, result, 180,127,212,146,"第1个探险", Sleep);
                    }
                    if (rwCount==1){
                        mFairy.onTap(0.8f, result, 182,236,204,250,"第2个探险", Sleep);
                    }
                    if (rwCount==2){
                        mFairy.onTap(0.8f, result, 185,334,209,353,"第3个探险", Sleep);
                    }
                    if (rwCount==3){
                        mFairy.onTap(0.8f, result, 176,430,204,443,"第4个探险", Sleep);
                    }
                    if (rwCount==4){
                        mFairy.onTap(0.8f, result, 167,530,194,548,"第5个探险", Sleep);
                    }
                    if (rwCount==5){
                        close(0);
                        setTaskEnd();return;
                    }
                    xzCount=0;
                    setTaskName(4);return;
                }else {
                    setTaskName(0);return;
                }
            }
            public void content_4() throws Exception {
                if (overtime(10,3))return;
                result = mFairy.findPic("lijits.png");
                if (result.sim>0.8f){
                    List<FindResult> listResult = mFairy.findPic(689,86,751,300, 0.8f,"tsNO1.png");
                    if (listResult.size() != 0) {
                        if (xzCount==0){
                            mFairy.onTap(0.8f, result, 454,550,474,566,"第1个选择框", Sleep);
                        }
                        if (xzCount==1){
                            mFairy.onTap(0.8f, result, 632,550,649,567,"第2个选择框", Sleep);
                        }
                        if (xzCount==2){
                            mFairy.onTap(0.8f, result, 807,552,827,572,"第3个选择框", Sleep);
                        }
                        if (xzCount==3){
                            mFairy.onTap(0.8f, result, 979,548,998,566,"第4个选择框", Sleep);
                        }
                        mFairy.onTap(0.8f, result, 436,384,459,403,"选一个灵兽", Sleep);
                        xzCount++;
                        /*if (xzCount>=4){
                            mFairy.onTap(0.8f, result, "立即探索", Sleep);
                            rwCount++;
                            xzCount=0;
                            setTaskName(3);return;
                        }*/
                        result = mFairy.findPic(474,213,870,463,"tansuostop.png");
                        if (result.sim>0.8f && xzCount>=4){
                            mFairy.onTap(0.8f, result, 736,631,767,647,"立即探索", Sleep);
                            mFairy.onTap(0.8f, result, 736,631,767,647,"立即探索", Sleep);
                            close(0);
                            setTaskEnd();return;
                        }
                    }else{
                        mFairy.onTap(0.8f, result, 736,631,767,647,"立即探索", Sleep);
                        rwCount++;
                        if (rwCount>=5){
                            LtLog.e(mFairy.getLineInfo("5个图都探险完了结束"));
                            close(0);
                            setTaskEnd();return;
                        }
                        setTaskName(3);return;
                    }
                }
                result = mFairy.findPic(762,585,1090,702,new String[]{"Immediate.png","kewan.png"});
                if (result.sim>=0.8f){
                    rwCount++;
                    setTaskName(3);return;
                }
                if (err==9){
                    num++;
                }
                if (num >= 10){
                    close(0);
                    setTaskEnd();return;
                }
            }
        }.taskContent(mFairy,"探险中");
    }//灵宠探险

    public  void  pullHeel()throws Exception{
        new GameUtil(mFairy){
            @Override
            public void content_0() throws Exception {
                close(0);
                setTaskName(1);return;
            }
            public void content_1() throws Exception {
                if (overtime(15,2))return;

                result = mFairy.findPic(0,176,38,386,new String[]{"LeftCaptain.png","team.png"});
                mFairy.onTap( 0.9f, result, "左侧队伍", Sleep);

                result = mFairy.findPic("CreateTeam.png");
                mFairy.onTap( 0.8f, result, 766, 282,767, 283,"创建队伍", Sleep);
                if (result.sim > 0.8f) {
                   setTaskName(2);return;
                }

                result = mFairy.findPic(38,349,263,452,"HeelStation.png");
                mFairy.onTap( 0.8f, result, "发起跟站", Sleep);

                /*result = mFairy.findPic(new String[]{"gzSure.png","by following.png"});
                mFairy.onTap( 0.8f, result, "跟站确定", Sleep);*/
                result = mFairy.findPic(507,315,769,390,"by following1.png");
                mFairy.onTap( 0.8f, result, 762,434,774,444,"跟站确定", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(10,99)){close(0);return;}
                result =mFairy.findPic("task1.png");
                mFairy.onTap(0.9f,result,"任务栏暗点切换到任务栏",Sleep);

                result = mFairy.findPic(0,255,39,462,"team.png");
                mFairy.onTap(0.95f, result, 15, 234, 29, 262, "在队伍栏切换到任务栏", Sleep);

                result=mFairy.findPic("my team.png");
                mFairy.onTap(0.8f,result,766, 282,767, 283,"关闭队伍界面",1000);

                result =mFairy.findPic("task.png");
                if (result.sim < 0.9f){
                    mFairy.onTap( 0.8f, result, 766, 282,767, 283,"收一下任务框", Sleep);
                }else {
                    setTaskEnd();return;
                }
            }
        }.taskContent(mFairy,"拉跟站中");
    }//拉跟站

    public void jdplx() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
                return;
            }
            public void content_1() throws Exception {
                result = mFairy.findPic(1,631,91,712,"Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result = mFairy.findPic(370,534,625,634, "juedoupai.png");
                mFairy.onTap(0.8f, result, "打开决斗牌", Sleep);

                result = mFairy.findPic("jingji.png");
                mFairy.onTap(0.8f, result, "竞技", Sleep);

                result = mFairy.findPic("off-line.png");
                mFairy.onTap(0.8f, result, "匹配", Sleep);

                result = mFairy.findPic("tiaoz.png");
                mFairy.onTap(0.8f, result, "挑战", Sleep);

                result = mFairy.findPic("tiaoz2.png");
                mFairy.onTap(0.8f, result, "挑战2", Sleep);

                result = mFairy.findPic("no.png");
                if (result.sim > 0.8f){
                    LtLog.e("挑战冷却结束");
                    setTaskEnd();return;
                }

              /*  result1 = mFairy.findPic("mianfei.png");
                if (result1.sim > 0.8f) {
                    result = mFairy.findPic(424, 86, 1057, 554, "box.png");
                    mFairy.onTap(0.8f, result, "挖矿2", Sleep);
                }*/
                result1 = mFairy.findPic("null.png");
                if (result1.sim > 0.8f){
                    LtLog.e("没有牌结束");
                    setTaskEnd();return;
                }

                result = mFairy.findPic( "jixu.png");
                mFairy.onTap(0.8f, result, "继续", Sleep);

                result = mFairy.findPic(437,529,556,628,"suo.png");
                if (result.sim > 0.8f){
                    LtLog.e("还没开启");
                    setTaskEnd();return;
                }
            }

        }.taskContent(mFairy, "决斗牌");
    } //决斗牌离线

    public  void  fuli()throws Exception{
        new SingleTask(mFairy){
            int i=0;
            @Override
            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);return;
            }
            public void content_1() throws Exception {
                if (overtime(10,0))return;
                result = mFairy.findPic(796,8,1115,157,new  String[]{"daily.png","fuli.png"});
                mFairy.onTap(0.8f, result, 1074,34,1085,49, "日常", Sleep);

                result = mFairy.findPic(876, 5, 1120, 146, "welfare.png");
                mFairy.onTap(0.8f, result, "福利", Sleep);

                result = mFairy.findPic(104,12,286,76,new String[]{"WelfareInterface.png","WelfareInterface1.png","WelfareInterface2.png","WelfareInterface3.png" });
                if (result.sim>0.7f){
                    setTaskName(2);return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(4,3))return;
                result = mFairy.findPic(66,56,425,422, new  String[]{"qdlj.png","qdlj1.png"});
                mFairy.onTap(0.8f, result, "签到领奖", Sleep);

                result = mFairy.findPic( 6,4,1274,707,new  String[]{"qiandao.png","qiandao1.png"});
                mFairy.onTap(0.8f, result, "签到", Sleep);

                result = mFairy.findPic("buqian.png");
                if (result.sim > 0.8f){
                    setTaskName(3);return;
                }
            }
            public void content_3() throws Exception {
                if (overtime(3,4))return;

                result = mFairy.findPic(66,56,425,422,  new  String[]{"zhaocai.png","zhaocai1.png"});
                mFairy.onTap(0.8f, result, "招财", Sleep);

                result = mFairy.findPic(947,296,1196,472,new  String[]{"Freefeed.png","Freefeed1.png"});
                mFairy.onTap(0.8f, result,   1111,315,1116,320,"免费招财", Sleep);

                result = mFairy.findPic(997,355,1102,398,"jinpiao1.png");
                if (result.sim > 0.8f){
                    setTaskName(4);return;
                }

            }
            public void content_4() throws Exception {
                if (overtime(3, 6)) {
                    gameUtil.close(1);
                    return;
                }
                result = mFairy.findPic(89,77,295,697, "cifu.png");
                mFairy.onTap(0.8f, result, "赐福", Sleep);

                result = mFairy.findPic(458, 262, 1092, 644, new String[]{"jpReceive.png", "jpReceive1.png"});
                mFairy.onTap(0.8f, result, "领取", Sleep);

                result = mFairy.findPic(89,77,295,697, "cifu.png");

            }
            public void content_5() throws Exception {
                i++;
                if (overtime(10,4))return;
                result = mFairy.findPic(86,74,298,699, new  String[]{"zyzh.png","zyzh1.png"});
                mFairy.onTap(0.8f, result, "资源追回", Sleep);

                if (i>=5){
                    LtLog.e("滑动=====");
                    mFairy.ranSwipe(200, 460, 200, 200, 300, Sleep);
                    i=0;
                }

                result = mFairy.findPic(873,173,1012,233,"FullRecovery2.png");
                mFairy.onTap(0.8f, result, "普通全追回", Sleep);

                result = mFairy.findPic("FullRecovery1.png");
                mFairy.onTap(0.8f, result, "普通全追回", 30000);

                result =mFairy.findPic(340,223,927,624,"smSure.png");
                mFairy.onTap(0.8f,result,"奖励确认",Sleep);
                if (result.sim>0.8f){
                    err=0;
                }


            }
            public void content_6() throws Exception {
                if (overtime(5,99)){
                    gameUtil.close(0);
                    return;
                }

                result = mFairy.findPic("Lower expansion.png");
                mFairy.onTap(0.8f, result, "打开下扩展栏", Sleep);

                result = mFairy.findPic("Spoil.png");
                mFairy.onTap(0.8f, result, "灵宠", Sleep);

                result = mFairy.findPic("SpiritInterface.png");
                mFairy.onTap(0.8f, result, 1208,319,1218,339,"灵宠界面", Sleep);

                result = mFairy.findPic(138,542,1130,714,"FreeCharge.png");
                mFairy.onTap(0.8f, result,  result.x, result.y - 30, result.x+1, result.y - 29,"灵宠免费", 7000);

                result = mFairy.findPic("initFork8.png");
                mFairy.onTap(0.8f, result, "叉", Sleep);


            }
        }.taskContent(mFairy,"福利任务中");
    }//福利

    public  void  moshi()throws Exception{
        if (AtFairyConfig.getOption("hpms").equals("1")) {
            result = mFairy.findPic(61,82,147,117,"moshi.png");
            result1 = mFairy.findPic(56,85,147,117,"peace.png");
            if (result.sim > 0.8f && result1.sim < 0.8f) {
                mFairy.onTap(0.8f, result, "切换下模式", Sleep);
                mFairy.onTap(0.8f, result, 517, 148, 529, 155, "和平模式", Sleep);
            }
        }
        if (AtFairyConfig.getOption("hpms").equals("2")) {
            result = mFairy.findPic(61,82,147,117,"moshi.png");
            result1 = mFairy.findPic(56,85,147,117,"peace.png");
            if (result.sim > 0.8f && result1.sim < 0.8f) {
                mFairy.onTap(0.8f, result, "切换下模式", Sleep);
                mFairy.onTap(0.8f, result, 518,203,531,212, "善恶模式", Sleep);
            }
        }

        if (AtFairyConfig.getOption("hpms").equals("3")) {
            result = mFairy.findPic(61,82,147,117,"moshi.png");
            result1 = mFairy.findPic(56,85,147,117,"peace.png");
            if (result.sim > 0.8f && result1.sim < 0.8f) {
                mFairy.onTap(0.8f, result, "切换下模式", Sleep);
                mFairy.onTap(0.8f, result, 512,309,531,326, "血盟模式", Sleep);
            }
        }

        if (AtFairyConfig.getOption("hpms").equals("4")) {
            result = mFairy.findPic(61,82,147,117,"moshi.png");
            result1 = mFairy.findPic(56,85,147,117,"peace.png");
            if (result.sim > 0.8f && result1.sim < 0.8f) {
                mFairy.onTap(0.8f, result, "切换下模式", Sleep);
                mFairy.onTap(0.8f, result, 515,362,531,381, "部落模式", Sleep);
            }
        }
    }//切换模式


    public void srAIAnswer() throws Exception {
        List<String> answerStrABCD = new ArrayList();

        String mStr = getPictureText(220,100,848,110);//题目范围x,y,w,h
        LtLog.e(this.mFairy.getLineInfo("题目是=" + mStr));
        if (mStr == null || mStr.equals("")) {
            this.mFairy.onTap(597,266,606,274, "没有识别到题目,默认选A", 1000);
            //mFairy.onTap(895, 579, 935, 588, "", 3000);
        } else {
            answerStrABCD.add(getPictureText(581,250,279,72));//A范围x,y,w,h
            answerStrABCD.add(getPictureText(882,250,275,70));//B范围x,y,w,h

            String c = getPictureText(580,342,280,72);
            if (!c.equals("")) {
                answerStrABCD.add(c);//C范围x,y,w,h
            }
            String d = getPictureText(883,344,276,63);
            if (!d.equals("")) {
                answerStrABCD.add(d);//C范围x,y,w,h
            }

            String[] answer = this.findAnswer(mStr, AtFairyConfig.getGameID());
            if (answer != null) {
                LtLog.e(mFairy.getLineInfo("开始匹配答案："));
                for (int j = 0; j < answerStrABCD.size(); ++j) {
                    LtLog.e(j + " : " + answerStrABCD.get(j));

                    for (int i = 0; i < answer.length; ++i) {
                        if (answerStrABCD.get(j).equals(answer[i])) {
                            switch (j) {
                                case 0:
                                    mFairy.onTap(600,271,620,289, "匹配到正确答案A", 1000);
                                    break;
                                case 1:
                                    mFairy.onTap(1107,282,1117,288, "匹配到正确答案B", 1000);
                                    break;
                                case 2:
                                    mFairy.onTap(613,365,637,386, "匹配到正确答案C", 1000);
                                    break;
                                case 3:
                                    mFairy.onTap(915,365,939,383, "匹配到正确答案D", 1000);
                                    break;
                            }
                            mFairy.onTap(604,278,611,287, "", 3000);
                            return;
                        }
                    }
                }
            }

            LtLog.e(this.mFairy.getLineInfo("没有匹配到,开始上传"));
            LtLog.i(this.mFairy.getLineInfo("----------------------------upDown>"));
            String answerStr = "";
            this.mFairy.onTap(604,278,611,287, "A", 1000);
            for (int i = 0; i < 10; ++i) {
                result = this.mFairy.findPic(762,244,862,328, "str1.png");
                if (result.sim > 0.88f) {
                    answerStr = answerStrABCD.get(0);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---A---" + answerStr));
                    break;
                }

                result = this.mFairy.findPic(1064,247,1160,331, "str1.png");
                if (result.sim > 0.88f) {
                    answerStr = answerStrABCD.get(1);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---B---" + answerStr));
                    break;
                }

                result = this.mFairy.findPic(758,333,866,421, "str1.png");
                if (result.sim > 0.88f) {
                    answerStr = answerStrABCD.get(2);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---C---" + answerStr));
                    break;
                }

                result = this.mFairy.findPic(1059,336,1163,414, "str1.png");
                if (result.sim > 0.88f) {
                    answerStr = answerStrABCD.get(3);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---D---" + answerStr));
                    break;
                }
                Thread.sleep(200);
            }

            if (answerStr != "") {
                this.UpAnswerAndTitle(mStr, answerStr, AtFairyConfig.getGameID());
            }
        }
    }


    public String bitmapToBase64(Bitmap bitmap, int quality) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String trWebOCR(String url, Bitmap bitmap) {
        String result = null;
        String imgBase64 = bitmapToBase64(bitmap, 50);
        try {
            FormBody.Builder builder = new FormBody.Builder();

            builder.add("img", imgBase64);
            RequestBody formBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            OkHttpClient client = new OkHttpClient();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getPictureText(int x, int y, int width, int height) {
        //331, 165, 811, 109
        Mat mat = mFairy.getScreenMat(x, y, width, height, 1, 0, 0, 1);
        Bitmap bmpCanny = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat, bmpCanny);

        String str = trWebOCR("http://192.168.1.254:8089/api/tr-run/", bmpCanny);
        if (str == null || str.equals("")) {
            return "";
        }

        LtLog.e("trWebOCR :" + str);

        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) new JSONObject(new JSONObject(str).get("data").toString()).get("raw_out");
            String mStr = new JSONArray(jsonArray.get(0).toString()).get(1).toString();
            return mStr;
        } catch (JSONException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "";
    }

    private String[] findAnswer(String title, String game_id) throws Exception {
        OkHttpClient client = new OkHttpClient();
        String resultStr = null;
        Request request = (new Request.Builder()).url("http://API.padyun.com/Yunpai/V1/IntelligentAnswer/FindTheAnswer?title=" + title + "&game_id=" + game_id).get().build();
        Response response = client.newCall(request).execute();
        resultStr = response.body().string();
        resultStr = (new JSONTokener(resultStr)).nextValue().toString();
        JSONObject jsonObject = new JSONObject(resultStr);
        if (jsonObject.getString("data").equals("false")) {
            LtLog.i(this.mFairy.getLineInfo("-----------+++---------not title"));
            return null;
        } else {
            String arr = jsonObject.getString("data").replaceAll("\\[", "");
            arr = arr.replaceAll("\\]", "");
            arr = arr.replaceAll("\"", "");
            String[] array = arr.split(",");
            LtLog.i(this.mFairy.getLineInfo("-----------+++---------array=" + array));
            return array;
        }
    }

    private void UpAnswerAndTitle(String title, String answer, String game_id) throws InterruptedException {
        String resultStr = null;
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "title=" + title + "&game_id=" + game_id + "&answer=" + answer);
        Request request = (new Request.Builder()).url("http://API.padyun.com/Yunpai/V1/IntelligentAnswer/AddTitle").post(body).build();

        try {
            Response response = client.newCall(request).execute();
            resultStr = response.body().string();
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        Thread.sleep(100L);
    }

    /*public static void main(String[] args) {
        int b = decrf(4);
        System.out.println(b);
    }

    public static int decrf(int n){
        if (n<=1){
            return n;
        }
        else {
            return decrf(n-1)+decrf(n-2);
        }
    }*/
}//物品的使用2
