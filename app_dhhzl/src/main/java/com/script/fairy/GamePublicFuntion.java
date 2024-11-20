package com.script.fairy;

import com.example.Answer;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

public class GamePublicFuntion extends TaskContent {
    public Answer answer;
    public AtFairyImpl mFairy;
    public FindResult result;

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        super(ypFairy);
        mFairy = ypFairy;
        answer = new Answer(ypFairy);
    }

    public static boolean genhujiu = true;

    public void sildeArchitecture(String... str) throws Exception {
        int silde = 10;
        while (mFairy.condit()) {

            LtLog.e(mFairy.getLineInfo("【寻找建筑】" + str[0]));

            result = mFairy.findPic(290, 672, 290, 720, str);
            mFairy.onTap(0.7f, result, "发现建筑", 3000);

            FindResult hai = mFairy.findPic("hai.png");
            FindResult pac = mFairy.findPic("package.png");
            if (hai.sim > 0.8f || pac.sim < 0.8f) {
                return;
            }

            result = mFairy.findPic(986, 254, 1081, 389, "zoom.png");
            mFairy.onTap(0.72f, result, "右 关掉箭头", 1000);

            result = mFairy.findPic("zoom1.png");
            mFairy.onTap(0.72f, result, "左 关掉箭头", 1000);

            LtLog.e(mFairy.getLineInfo("【寻找建筑】"));
            if (silde < 3) {
                mFairy.ranSwipe(597, 319, 206, 319, 500, 1000);
            } else if (silde < 6) {
                mFairy.ranSwipe(206, 319, 597, 319, 500, 1000);
            } else {
                for (int i = 0; i < 3; i++) {
                    mFairy.tap(929, 41);
                }
                silde = 0;
                continue;
            }

            silde++;
        }
    }

    static boolean hejiu = true;

    public void init(boolean bool) throws Exception {
        hejiu = true;
        frequencyInit("home");
        onceJudge("ting");

        int err = 0;

        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("GamePublicFuntion init >>>"));

            result = mFairy.findPic(739,386,920,476,"set6.png");
            if (result.sim > 0.8f) {
                LtLog.e(mFairy.getLineInfo("【登录界面】"));

                return;

            }



            err++;

            if (err > 30) {
                err = 0;
                mFairy.onTap(1224, 660, 1239, 669, "", 1000);
            }

            result = mFairy.findPic(new String[]{"jiuba1.png", "xiuxi1.png", "xiuxi2.png"});
            if (result.sim > 0.8f) {
                LtLog.e(mFairy.getLineInfo("【酒吧界面】"));

                result = mFairy.findPic(934, 273, 1271, 699, "jiuba3.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "喝一杯", 500);
                    mFairy.onTap(0.8f, result, "喝一杯", 500);
                    mFairy.onTap(0.8f, result, "喝一杯", 500);
                    mFairy.onTap(0.8f, result, "喝一杯", 500);
                    Thread.sleep(5000);
                    mFairy.onTap(1221, 33, 1235, 51, "关闭", 1500);
                    continue;
                }

                result = mFairy.findPic(934, 273, 1271, 699, "jiuba2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "喝点东西", 2000);


                    result = mFairy.findPic(320, 87, 616, 287, "jiuba4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1221, 33, 1235, 51, "喝多了", 1500);
                        hejiu = false;
                        continue;
                    }

                    result = mFairy.findPic(934, 273, 1271, 699, "jiuba2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1221, 33, 1235, 51, "不需要喝酒了", 1500);

                    }
                    continue;
                }

                if (frequencyMap("jiuba", 10)) {
                    mFairy.onTap(1223, 35, 1240, 62, "酒吧界面异常", 1500);
                }
                continue;
            }

            xClose();

            result = cancel();
            mFairy.onTap(0.8f, result, "取消 | 拒绝", 1000);

            result = mFairy.findPic("tsqx.png");
            mFairy.onTap(0.8f, result, "进入地下城取消", 1000);

            result = mFairy.findPic("liaotian.png");
            mFairy.onTap(0.8f, result, "err 关闭聊天框", 1000);

            shibai();

            result = mFairy.findPic("yunan.png");
            mFairy.onTap(0.8f, result, "遇难回港", 1000);

            result = mFairy.findPic("sbdt.png");
            mFairy.onTap(0.8f, result, "退出比萨考核", 1000);

            result = mFairy.findPic("hbjz5.png");
            mFairy.onTap(0.8f, result, 1150, 15, 1172, 34, "获取奖励", 2000);


            result = mFairy.findPic("sh.png");
            mFairy.onTap(0.8f, result, 909, 24, 944, 37, "商会退出", 2000);


            FindResult hai = mFairy.findPic("hai.png");
            FindResult pac = mFairy.findPic("package.png");
            if (hai.sim < 0.8f && pac.sim > 0.8f) {//在主城
                LtLog.e(mFairy.getLineInfo("【在主城】"));

                result = mFairy.findPic(84, 9, 311, 68, "pilao.png");
                if (result.sim > 0.8f && hejiu && genhujiu) {
                    //200,30
                    //+30,+20,+45,+25
                    int pilao = mFairy.getColorNum(result.x + 30, result.y + 18, result.x + 45, result.y + 22, "246,156,32", 0.9f);
                    LtLog.e(mFairy.getLineInfo("【疲劳值：" + (result.x + 30) + "," + (result.y + 18) + "," + (result.x + 45) + "," + (result.y + 22) + "  " + pilao + "】"));
                    if (pilao > 20) {
                        sildeArchitecture("jiuba.png", "xiuxi.png");
                        continue;
                    }
                }

                if (frequencyMap("home", 1)) {
                    return;
                }
            } else if (hai.sim > 0.8f && pac.sim > 0.8f) {//在海上
                LtLog.e(mFairy.getLineInfo("【在海上】"));

                result = mFairy.findPic(1067, 262, 1182, 362, "bazhu.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(949, 28, 977, 37, "离开", 1000);
                    mFairy.onTap(735, 454, 761, 468, "", 3000);
                    return;
                }

                result = mFairy.findPic(93, 563, 309, 684, "gen1.png");
                if (result.sim > 0.72f) {
                    LtLog.e(mFairy.getLineInfo("【在跟随呢】"));
                    return;
                }

                if (onceJudge("ting")) {
                    for (int i = 0; i < 20; i++) {
                        result = mFairy.findPic("chuan1.png");
                        if (result.sim > 0.7f) {
                            if (frequencyMap("home", 5)) {
                                return;
                            }
                        } else {
                            mFairy.onTap(29, 486, 47, 508, "点击停船", 3000);
                        }
                        Thread.sleep(2000);
                    }
                    continue;
                }

                result = mFairy.findPic("chuan1.png");//停船中 判断结束init
                if (result.sim > 0.7f) {
                    LtLog.e(mFairy.getLineInfo("发现停船"));
                    if (frequencyMap("home", 1)) {
                        return;
                    }
                    continue;
                }

                if (bool) {
                    LtLog.e(mFairy.getLineInfo("在海上,开始停船"));
                    mFairy.onTap(29, 486, 47, 508, "点击停船", 2000);
                } else {
                    if (frequencyMap("home", 1)) {
                        return;
                    }
                }
            } else {
                result = mFairy.findPic(new String[]{"battle.png", "hbjz4.png", "battle1.png"});
                if (result.sim > 0.72f) {
                    LtLog.e(mFairy.getLineInfo("【战斗中】"));
                    auto();
                    initOnceJudge("ting");
                    Thread.sleep(5000);

                    if (AtFairyConfig.getOption("2540").equals("1")) {
                        return;
                    }
                } else {
                    result = mFairy.findPic("bztz6.png");
                    mFairy.onTap(0.8f, result, "离开", 1000);
                    mFairy.onTap(0.8f, result, 735, 454, 761, 468, "", 1000);

                    chat();

                    close();

                    result = mFairy.findPic("jmsrclose.png");
                    mFairy.onTap(0.8f, result, "关闭精明商人", 1000);

                    result = mFairy.findPic("tuohang.png");
                    mFairy.onTap(0.8f, result, 1226, 671, 1244, 686, "", 1000);

                    result = mFairy.findPic("hyzw12.png");
                    mFairy.onTap(0.8f, result, "海域之王结束", 1000);

                    result = mFairy.findPic("bdt.png");
                    mFairy.onTap(0.8f, result, "我在想想", 1000);

                    result = mFairy.findPic("esc2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "在探索点击退出", 1500);
                        mFairy.onTap(740, 453, 756, 469, "", 2000);
                    }

                }
            }
        }
    }

    public void auto() throws Exception {
        result = mFairy.findPic("auto.png");
        mFairy.onTap(0.8f, result, "点击自动战斗", 500);

    }//自动战斗

    public void close() throws Exception {

        result = mFairy.findPic(952, 3, 1278, 152, new String[]{"esc1.png", "esc3.png", "esc4.png", "esc5.png", "esc6.png", "esc7.png", "esc8.png", "esc9.png","esc10.png"});
        mFairy.onTap(0.88f, result, "返回", 1500);

        result = mFairy.findPic(963, 7, 1275, 183, new String[]{"close1.png", "close2.png"});
        mFairy.onTap(0.88f, result, "关闭", 1500);

        result = mFairy.findPic("close3.png");
        mFairy.onTap(0.88f, result, "关闭", 1500);
    }//关闭

    public void xClose() throws Exception {

        result = mFairy.findPic(963, 7, 1275, 183, new String[]{"close1.png", "close2.png"});
        mFairy.onTap(0.88f, result, "关闭", 1500);

        result = mFairy.findPic("close3.png");
        mFairy.onTap(0.88f, result, "关闭", 1500);

    }//关闭

    public void chat() throws Exception {
        result = mFairy.findPic("package.png");
        if (result.sim < 0.8f) {
            result = mFairy.findPic(253, 175, 1231, 426, new String[]{"chat.png", "chat1.png"});
            mFairy.onTap(0.72f, result, "点击聊天框", 500);
        }
    }

    public void lhy() throws Exception {

        FindResult lhy = mFairy.findPic(203, 665, 1223, 713, new String[]{"lhy1.png", "lhy2.png"});
        if (lhy.sim > 0.72f) {

            result = mFairy.findPic(351,551,423,625, "lhy.png");
            if (result.sim < 0.75f && lhy.x > 355) {
                mFairy.onTap(375,586,387,595, "", 500);
            }

            result = mFairy.findPic(483,549,559,625, "lhy.png");
            if (result.sim < 0.75f && lhy.x > 494) {
                mFairy.onTap(515,581,528,592, "", 500);
            }

            result = mFairy.findPic(619,551,696,623, "lhy.png");
            if (result.sim < 0.75f && lhy.x > 625) {
                mFairy.onTap(655,582,663,594, "", 500);
            }

            result = mFairy.findPic(754,550,824,624, "lhy.png");
            if (result.sim < 0.75f && lhy.x > 763) {
                mFairy.onTap(780,583,792,599, "", 500);
            }

            result = mFairy.findPic(886,549,964,628, "lhy.png");
            if (result.sim < 0.75f && lhy.x > 900) {
                mFairy.onTap(922,584,928,595, "", 1000);
                mFairy.onTap(1129,637,1148,658,"里程奖励",2500);
                mFairy.onTap(632,636,672,647,"",1000);
                mFairy.onTap(1220,42,1231,57,"",1000);
            }
        }
    }

    public int rankNum() throws Exception {

        result = mFairy.findPic(1028, 521, 1257, 603, "rank8.png");
        if (result.sim > 0.8f) {
            return 5;
        }

        result = mFairy.findPic(779, 518, 996, 605, "rank8.png");
        if (result.sim > 0.8f) {
            return 4;
        }

        result = mFairy.findPic(529, 518, 744, 605, "rank8.png");
        if (result.sim > 0.8f) {
            return 3;
        }

        result = mFairy.findPic(274, 528, 495, 608, "rank8.png");
        if (result.sim > 0.8f) {
            return 2;
        }
        return 1;
    }

    public void lgs() throws Exception {

        result = mFairy.findPic("package.png");
        if (result.sim > 0.8f) {
            for (int i = 0; i < 4; i++) {
                result = mFairy.findPic(470 + (i * 85), 10, 555 + (i * 85), 100, "rank15.png");
                mFairy.onTap(0.8f, result, "发现暂离", 1500);

                result = mFairy.findPic(388, 422, 1052, 670, "rank16.png");
                mFairy.onTap(0.8f, result, "邀请回归", 1000);
            }
        }
    }

    public boolean shibai() throws Exception {
        result = mFairy.findPic(352, 307, 729, 535, "shibai.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(1049, 201, 1067, 218, "【失败】", 1500);
            return true;
        }
        return false;
    }

    int judgeStop = 0;

    public boolean taskClick(String taskName) throws Exception {
        result = mFairy.findPic("package.png");
        if (result.sim > 0.8f) {
            Thread.sleep(500);
            if (TaskContent.err > 2) {

                FindResult task = mFairy.findPic(1053, 280, 1200, 522, taskName);
                if (task.sim > 0.72f) {
                    TaskContent.err = 0;
                    initOnceJudge("第一次滑动");
                    mFairy.onTap(0.72f, task, "点击右侧任务", 500);

                    if(taskName.equals("js3.png")){
                        if (mapCount(0.8f, 681,229,863,602, "xunl.png")) {
                            return true;
                        }
                    }

                    if (mapCount(0.8f, 462, 183, 750, 523, "pilao1.png","he.png")) {
                        return true;
                    }
                    return false;
                } else {
                    if(err>=7){
                        initOnceJudge("第一次滑动");
                    }else {
                        if (onceJudge("第一次滑动")) {
                            for (int i = 0; i < 3; i++) {
                                mFairy.ranSwipe(1184, 317, 1154, 591, 500, 500);
                            }
                        }
                    }
                    taskSlide.slideRange(err, new int[]{4, 5, 6, 7});

                }
            }
        }
        return false;
    }

    public Boolean judgeStop(int m) throws Exception {

        result = mFairy.findPic("package.png");
        if (result.sim > 0.8f) {
            if (mFairy.findPic("hai.png").sim < 0.8f) {
                return true;
            }
        }

        long num = mFairy.mMatTime(1193, 228, 75, 10, 0.9f);
        if (num <= 1) {
            judgeStop++;
            if (judgeStop >= 20) {
                judgeStop = 0;
                return true;
            }
        }
        if (num >= m) {
            judgeStop = 0;
            return true;
        }
        return false;
    }//发呆判断

    public FindResult cancel() throws Exception {
        return mFairy.findPic(267, 300, 620, 671, new String[]{"jujue.png", "quxiao.png", "quxiao1.png"});
    }

    public void jygm() throws Exception {

        result = mFairy.findPic("gmScene1.png");
        if (result.sim > 0.8f) {
            result = mFairy.findPic(993, 570, 1170, 701, "gm3.png");
            if (result.sim > 0.8f) {
                TaskContent.err = 0;
                mFairy.onTap(0.8f, result, "确认买入", 1000);
            }
        }
    }

    int gmerr = 0;

    public void gm() throws Exception {
        /**
         * gm1 海市购买
         * gm2 摆摊购买
         * gm3 交易所买入
         */
        result = mFairy.findPic("gmScene.png");
        if (result.sim > 0.8f) {
            TaskContent.err = 0;
            gmerr++;
            if (gmerr > 10) {
                gmerr = 0;
                mFairy.onTap(1240, 10, 1253, 29, "", 1500);
                return;
            }
            if ((result = mFairy.findPic("gm1.png")).sim > 0.8f) {
            } else if ((result = mFairy.findPic(509, 470, 815, 639, "gm2.png")).sim > 0.8f) {
            }

            mFairy.onTap(0.8f, result, "购买", 500);
            if (mapCount(0.8f, 501, 231, 630, 608, "bm.png")) {
                LtLog.e(mFairy.getLineInfo("包满啦"));
                //mFairy.finish(AtFairyConfig.getTaskID(), 7999);
                //包满
            }
        } else {
            gmerr = 0;
        }

    }//购买

    public void guotu() throws Exception {
        if (mFairy.getColorNum(118, 698, 143, 707, "86,161,218", 0.9f) > 50) {
            LtLog.e(mFairy.getLineInfo("【过图中】"));
            TaskContent.err = 0;
            Thread.sleep(2000);
        }
    }

    

    public void yanzheng() throws Exception {
        result = mFairy.findPic("yanzheng.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("验证中>>>"));
            TaskContent.err = 0;
            Thread.sleep(1000);
            
        //好爱网址 http://feng.suanst.com/
            String result_ha = answer.newHaoai(382, 77, 505, 640,"6001");

            if (!result_ha.equals("") && result_ha != null) {
                try {
                    String[] aa = result_ha.split("\\|");

                    for (String st : aa) {
                        mFairy.tap(Integer.parseInt(st.split(",")[0]) + 256, Integer.parseInt(st.split(",")[1]) + 107);
                        Thread.sleep(500);
                    }

                    mFairy.onTap(767, 670, 802, 690, "确定", 3000);

                    result = mFairy.findPic("yanzheng.png");
                    mFairy.onTap(0.8f, result, "没有验证出来,刷新", 5000);

                } catch (Exception e) {
                    LtLog.e(mFairy.getLineInfo("验证报错!!!"));
                    LtLog.e(mFairy.getLineInfo("验证报错!!!"));
                    LtLog.e(mFairy.getLineInfo("验证报错!!!"));
                }
            } else {
                LtLog.e(mFairy.getLineInfo("验证为空!!!"));
                LtLog.e(mFairy.getLineInfo("验证为空!!!"));
                LtLog.e(mFairy.getLineInfo("验证为空!!!"));
            }
        }
    }//验证

    /*
        以下功能不能用
     */
    public void wendaAIAnswer() throws Exception {
        answer.wendaAIAnswer();
    }
    public void srAIAnswer() throws Exception {
        answer.srAIAnswer();
    }
    public String getPictureText(int x, int y, int width, int height){
        return  answer.getPictureText(x,y,width,height);
    }
}
