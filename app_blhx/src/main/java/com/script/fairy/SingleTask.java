

package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

public class SingleTask {
    public GamePublicFuntion gamePublicFuntion;
    public FindResult result;
    public AtFairyImpl mFairy;

    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }

    public void minit() throws Exception {
        result = mFairy.findPic("new.png");
        mFairy.onTap(0.8f, result, 1146, 518, 1183, 551, "获得新英雄", 1000);

        result = mFairy.findPic("new1.png");
        mFairy.onTap(0.8f, result, 1146, 518, 1183, 551, "获得新英雄", 1000);

        result = mFairy.findPic("jx1.png");
        mFairy.onTap(0.8f, result, "战斗结束,继续", 1000);

        result = mFairy.findPic("jx2.png");
        mFairy.onTap(0.8f, result, 1043, 648, 1092, 676, "战斗结束,继续", 1000);

        result = mFairy.findPic(106,541,1197,587,"e.png");
        mFairy.onTap(0.8f, result, 1043, 648, 1092, 676, "战斗结束,继续", 1000);

        result = mFairy.findPic("ok.png");
        mFairy.onTap(0.8f, result, "ok", 1000);

       /* result = mFairy.findPic(280, 390, 654, 672, "qx.png");
        mFairy.onTap(0.8f, result, "取消", 1000);*/

        result = mFairy.findPic(720, 416, 1134, 674, "ok2.png");
        mFairy.onTap(0.8f, result, "ok", 1000);

        result = mFairy.findPic("close1.png");
        mFairy.onTap(0.8f, result, "返回", 1000);

        result = mFairy.findPic("close3.png");
        mFairy.onTap(0.8f, result, "返回", 1000);

        result = mFairy.findPic(755,8,1275,439, new String[]{"close2.png","close4.png"});
        mFairy.onTap(0.8f, result, "关闭", 1000);

        result = mFairy.findPic("wt.png");
        mFairy.onTap(0.8f, result, 1051, 334, 1073, 359, "委托", 1000);


    }

    public void wxwt()throws Exception {
        new TaskContent(mFairy) {
            private int num = 1;
            private int nerr = 0;

            @Override
            void create() throws Exception {
                TaskMain.TASKNAME = "无限委托";
                num = 1;
                nerr = 0;
            }

            @Override
            void init() throws Exception {
                minit();
                result = mFairy.findPic("home.png");
                if (result.sim > 0.8f) {
                    setTaskName(1);
                }
            }


            @Override
            void inOperation() throws Exception {
                result = mFairy.findPic(74, 467, 1235, 523, "exp.png");
                mFairy.onTap(0.8f, result, 1158, 574, 1202, 616, "委托完成", 2000);

                result = mFairy.findPic(537, 248, 728, 704, "jx.png");
                mFairy.onTap(0.8f, result, "点击继续", 1000);

                result = mFairy.findPic("new.png");
                mFairy.onTap(0.8f, result, 1146, 518, 1183, 551, "获得新英雄", 1000);

                result = mFairy.findPic("zhidao.png");
                mFairy.onTap(0.8f, result, "知道了", 1000);

                result = mFairy.findPic("ok.png");
                mFairy.onTap(0.8f, result, "ok", 1000);
            }

            @Override
            void content_01() throws Exception {
                overtime(30, 0);

                result = mFairy.findPic("home.png");
                mFairy.onTap(0.8f, result, 12, 152, 26, 171, "缩放栏", 2000);

                result = mFairy.findPic("wt.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (gamePublicFuntion.timeJudge(60000)) {
                        result = mFairy.findPic(32,19,497,145,"wt1.png");
                        mFairy.onTap(0.8f, result, "金币", 1000);

                        result = mFairy.findPic(32,19,497,145,"wt2.png");
                        mFairy.onTap(0.8f, result, "石油", 1000);
                    }

                    result = mFairy.findPic(94,216,170,293, "wt6.png");
                    if (result.sim < 0.8f) {
                        mFairy.ranSwipe(240,286,284,492,0,1000,(long)2000);
                        return;
                    }

                    result = mFairy.findPic(379, 228, 515, 338, "wan.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "完成", 1500);
                        return;
                    }

                    result = mFairy.findPic("qu2.png");
                    mFairy.onTap(0.8f,result,464,506,493,528,"quxiao",1100);

                    result = mFairy.findPic(215, 245, 284, 342, "wtend.png");
                    if (result.sim < 0.9f) {
                        LtLog.e(mFairy.getLineInfo("暂无空闲舰队!"));
                        return;
                    }

                    result = mFairy.findPic(379, 228, 515, 338, "wan1.png");
                    mFairy.onTap(0.8f, result, "前往", 2500);

                }

                result = mFairy.findPic(186,213,332,282,"wt3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    nerr++;
                    if (nerr > 5) {
                        num++;
                        minit();
                        nerr = 0;
                    }
                    result = mFairy.findPic(976,245,1194,424,new String[]{"qu.png", "qu1.png"});
                    if (result.sim > 0.75f) {
                        num++;
                        mFairy.onTap(42, 45, 70, 73, "返回", 2500);
                    } else {
                        mFairy.onTap(917, 335, 959, 359, "推荐", 1500);
                        mFairy.onTap(1075, 339, 1112, 362, "开始", 3000);
                        mFairy.onTap(42, 45, 70, 73, "返回", 2500);
                    }
                    if (num > 5) {
                        num = 1;
                    }
                } else {
                    result = mFairy.findPic("wtScene.png");
                    if (result.sim > 0.8f) {
                        LtLog.e("num" + num);
                        nerr = 0;
                        err = 0;
                        switch (num) {
                            case 1:
                                mFairy.onTap(259, 134, 285, 153, "第" + num + "个", 3000);
                                break;
                            case 2:
                                mFairy.onTap(257, 280, 287, 303, "第" + num + "个", 3000);
                                break;
                            case 3:
                                mFairy.onTap(263, 423, 292, 452, "第" + num + "个", 3000);
                                break;
                            case 4:
                                mFairy.onTap(275, 571, 302, 592, "第" + num + "个", 3000);
                                break;
                            case 5:
                                mFairy.onTap(270, 676, 304, 685, "第" + num + "个", 3000);
                                break;
                        }

                    }
                }

            }

            @Override
            void content_02() throws Exception {

            }
        };

    }//无限委托

    private int x = 3;
    private int y = 4;
    private int count = 0;
    private boolean tui = false;
    private boolean bool = true;
    private int errCount = 0;
    private int numCount = 1;
    private int position = 0;

    public void battle() throws Exception {
        new TaskContent(mFairy) {
            void create() throws Exception {
                TaskMain.TASKNAME = "副本任务";
                String str = AtFairyConfig.getOption("zj");
                if (str != "" || !str.equals("")) {
                    String[] arr = str.split(",");
                    if (arr[0] != "" || !arr[0].equals("")) {
                        x = Integer.parseInt(arr[0]);
                    }
                    if (arr[1] != "" || !arr[1].equals("")) {
                        y = Integer.parseInt(arr[1]);
                    }
                }

                if (x == 0 || y == 0) {
                    LtLog.e(mFairy.getLineInfo("玩家未勾选副本章节,End!"));
                    setTaskEnd();
                    return;
                }
                if (AtFairyConfig.getOption("ty").equals("1")) {
                    bool = true;
                }
            }

            void inOperation() throws Exception {
                result = mFairy.findPic("auto.png");
                mFairy.onTap(0.8f, result, "自律", 1000);

                result = mFairy.findPic("new.png");
                mFairy.onTap(0.8f, result, 1146, 518, 1183, 551, "获得新英雄", 1000);

                result = mFairy.findPic(537, 248, 728, 704, "jx.png");
                mFairy.onTap(0.8f, result, "点击继续", 1000);

                result = mFairy.findPic("gb.png");
                mFairy.onTap(0.8f, result, "规避", 1000);

                result = mFairy.findPic("zhidao.png");
                mFairy.onTap(0.8f, result, "知道了", 1000);

                result = mFairy.findPic("zhidao1.png");
                mFairy.onTap(0.8f, result, "知道了", 1000);

                result = mFairy.findPic("ok.png");
                mFairy.onTap(0.8f, result, "ok", 1000);

                result = mFairy.findPic("ok4.png");
                if(result.sim>0.8f) {
                    mFairy.onTap(0.8f, result, "ok", 500);
                    for (int i = 0; i < 6; i++) {
                        result = mFairy.findPic(535, 185, 654, 472, "cjend.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("石油不足"));
                            setTaskEnd();
                            return;
                        }
                    }
                }
                result = mFairy.findPic("bai.png");
                if(result.sim>0.8f){
                    err=0;
                }

            }

            void init() throws Exception {
                minit();
                result = mFairy.findPic("home.png");
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    tui = false;
                    count = 0;
                    errCount = 0;
                    numCount = 1;
                    position = 0;
                }
            }

            void content_01() throws Exception {
                overtime(60, 0);

                result = mFairy.findPic(235,385,627,625,"zheng.png");
                if (result.sim > 0.8f) {
                    if (bool) {
                        mFairy.onTap(0.8f, result, "整理", 2000);
                        setTaskName(2);
                        tui = false;
                    } else {
                        minit();
                        setTaskEnd();
                    }
                    return;
                }

                result = mFairy.findPic("home.png");
                mFairy.onTap(0.8f, result, "出击", 2000);

                result = mFairy.findPic("zhuxian.png");
                mFairy.onTap(0.8f, result, "主线", 3000);

                result = mFairy.findPic("jx1.png");
                mFairy.onTap(0.8f, result, "战斗结束,继续", 1000);

                result = mFairy.findPic("jx2.png");
                mFairy.onTap(0.8f, result, 1043, 648, 1092, 676, "战斗结束,继续", 1000);

                result = mFairy.findPic(106,541,1197,587,"e.png");
                mFairy.onTap(0.8f, result, 1043, 648, 1092, 676, "战斗结束,继续", 1000);

                result = mFairy.findPic(570,294,1274,713,new String[]{"go.png", "go1.png", "go2.png","jia.png","go3.png"});
                if (result.sim > 0.75f) {
                    err=0;
                    mFairy.onTap(0.8f, result, "立刻前往", 500);
                    for (int i = 0; i < 6; i++) {
                        result = mFairy.findPic(535, 185, 654, 472, "cjend.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("石油不足"));
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic("cj.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    Thread.sleep(3500);

                    result = mFairy.findPic("cj.png");
                    if (result.sim < 0.8f) {
                        return;
                    }

                    result = mFairy.findPic("pt.png");
                    mFairy.onTap(0.8f, result, "普通", 2000);

                    int num = gamePublicFuntion.getNumber();
                    LtLog.e(mFairy.getLineInfo("当前章节:"+num));

                    if (num < x) {
                        gamePublicFuntion.next_chapter(x - num);
                    }
                    if (num > x) {
                        gamePublicFuntion.last_chapter(num - x);
                    }

                    if (num == x) {
                        gamePublicFuntion.click_chapter(x, y);
                    }
                }

                result = mFairy.findPic("ying.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(170, 61, 714, 502, "b.png");
                    LtLog.e(mFairy.getLineInfo("x轴sim:" + result.sim));
                    if (result.sim > 0.92f) {
                        mFairy.ranSwipe(155, result.y, result.x + 50, result.y + 5, 3, 1000, (long)2000);
                        LtLog.e(mFairy.getLineInfo("调整x轴的位置"));
                        return;
                    }
                    result = mFairy.findPic(122,250,421,712, "b.png");
                    LtLog.e(mFairy.getLineInfo("y轴sim:" + result.sim));
                    if (result.sim > 0.92f) {
                        mFairy.ranSwipe(result.x + 50, 180, result.x + 55, result.y+30, 2, 1000, (long)2000);
                        LtLog.e(mFairy.getLineInfo("调整y轴的位置"));
                        return;
                    }
                    errCount++;

                    result = mFairy.findPic(144,114,1184,634, "boss.png");
                    LtLog.e(mFairy.getLineInfo("boss sim:" + result.sim));
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "boss", 3500);
                        count++;
                    } else {
                        count = 3;
                    }

                    if (count >= 3) {
                        FindResult result1 = mFairy.findPic(144,114,1184,634,new String[]{
                                "ship.png","ship1.png", "ship2.png", "ship3.png","lv.png"});
                        LtLog.e(mFairy.getLineInfo("船sim:" + result1.sim));
                        if (result1.sim > 0.72f) {
                            position++;
                            mFairy.onTap(0.72f, result1, "船", 3500);
                            count = 0;
                            if(position>3){
                                result = mFairy.findPic(143, 106, result1.x, 634,new String[]{
                                        "ship.png","ship1.png", "ship2.png", "ship3.png","lv.png"});
                                mFairy.onTap(0.72f, result, "船", 3000);

                                result = mFairy.findPic(result1.x+80, 106, 1276, 634,new String[]{
                                        "ship.png","ship1.png", "ship2.png", "ship3.png","lv.png"});
                                mFairy.onTap(0.72f, result, "船", 3000);

                                result = mFairy.findPic(143, 106, 1276, result1.y,new String[]{
                                        "ship.png","ship1.png", "ship2.png", "ship3.png","lv.png"});
                                mFairy.onTap(0.72f, result, "船", 3000);

                                result = mFairy.findPic(143, result1.y+35, 1276, 634,new String[]{
                                        "ship.png","ship1.png", "ship2.png", "ship3.png","lv.png"});
                                mFairy.onTap(0.72f, result, "船", 3000);

                            }
                        }
                    }

                    result = mFairy.findPic(144,114,1184,634, "wenhao.png");
                    mFairy.onTap(0.8f, result,result.x,result.y+75,result.x+5,result.y+85, "问号", 3500);

                    if (errCount > 2) {
                        if (numCount > 4) {
                            setTaskName(0);
                            return;
                        }
                        switch (numCount) {
                            case 1:
                                mFairy.ranSwipe(623, 316, 670, 537, 0, 1000, (long)2000);
                                LtLog.e(mFairy.getLineInfo("查看上方舰队"));
                                break;
                            case 2:
                                mFairy.ranSwipe(623, 316, 670, 537, 2, 1000, (long)2000);
                                mFairy.ranSwipe(623, 316, 670, 537, 2, 1000, (long)2000);
                                LtLog.e(mFairy.getLineInfo("查看下方舰队"));
                                break;
                            case 3:
                                mFairy.ranSwipe(623, 316, 670, 537, 0, 1000, (long)2000);
                                mFairy.ranSwipe(611, 316, 860, 367, 3,1000,(long)2000);
                                LtLog.e(mFairy.getLineInfo("查看左方舰队"));
                                break;
                            case 4:
                                mFairy.ranSwipe(611, 316, 860, 367, 1,1000, (long)2000);
                                mFairy.ranSwipe(611, 316, 860, 367, 1, 1000, (long)2000);
                                LtLog.e(mFairy.getLineInfo("查看右方舰队"));
                                break;
                        }
                        numCount++;
                        errCount = 0;
                    }
                }

                result = mFairy.findPic(1079,16,1273,84,new String[]{"battleing.png","battleing1.png"});
                if (result.sim > 0.8f) {
                    position=0;
                    err = 0;
                }
            }

            void content_02() throws Exception {
                overtime(30, 0);

                result = mFairy.findPic(235,385,627,625,"zheng.png");
                mFairy.onTap(0.8f, result, "整理", 1000);

                result = mFairy.findPic("chuan.png");
                if (result.sim > 0.8f) {
                    if (tui == false) {
                        mFairy.onTap(1125, 15, 1164, 37, "筛选", 2000);
                    } else {
                        for (int i = 0; i < 7; i++) {
                            mFairy.onTap(160 + (i * 160), 180, 165 + (i * 160), 185, "点", 300);
                        }
                        for (int i = 0; i < 7; i++) {
                            mFairy.onTap(160 + (i * 160), 400, 165 + (i * 160), 405, "点", 300);
                        }

                        mFairy.onTap(1079, 660, 1119, 684, "确定", 2000);

                        for (int i = 0; i < 5; i++) {
                            result = mFairy.findPic(720, 416, 1134, 674, "ok2.png");
                            mFairy.onTap(0.8f, result, "ok", 1000);
                        }
                        setTaskName(0);
                        return;
                    }
                }

                result = mFairy.findPic(7,70,147,177,"tui.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(340,122,362,130, "初始化排序", 500);
                    mFairy.onTap(807,120,833,129, "入手时间", 500);
                    mFairy.onTap(335,461,363,474, "初始化品质", 500);
                    mFairy.onTap(494,460,513,467, "普通", 500);
                    mFairy.onTap(646,462,666,468, "稀有", 500);
                    result = mFairy.findPic("ok1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "确定", 1000);
                        tui = true;
                    }
                }
            }
        };
    }//出击

    /**
     * 每日
     * @throws Exception
     */
    abstract class daily extends TaskContent{
        public daily(AtFairyImpl mFairy) throws Exception {
            super(mFairy);
        }

        @Override
        void inOperation() throws Exception {
            super.inOperation();
            result = mFairy.findPic("auto.png");
            mFairy.onTap(0.8f, result, "自律", 1000);

            result = mFairy.findPic("jx1.png");
            mFairy.onTap(0.8f, result, "战斗结束,继续", 1000);

            result = mFairy.findPic("new.png");
            mFairy.onTap(0.8f, result, 1146, 518, 1183, 551, "获得新英雄", 1000);


            result = mFairy.findPic(537, 248, 728, 704, "jx.png");
            mFairy.onTap(0.8f, result, "点击继续", 1000);

            result = mFairy.findPic("zhidao.png");
            mFairy.onTap(0.8f, result, "知道了", 1000);

            result = mFairy.findPic("ok.png");
            mFairy.onTap(0.8f, result, "ok", 1000);

            result = mFairy.findPic("jx2.png");
            mFairy.onTap(0.8f, result, 1043, 648, 1092, 676, "战斗结束,继续", 1000);

            result = mFairy.findPic(106,541,1197,587,"e.png");
            mFairy.onTap(0.8f, result, 1043, 648, 1092, 676, "战斗结束,继续", 1000);

            result = mFairy.findPic(1079,16,1273,84,new String[]{"battleing.png","battleing1.png"});
            if (result.sim > 0.8f) {
                position=0;
                err = 0;
            }

            result = mFairy.findPic("bai.png");
            if(result.sim>0.8f){
                err=0;
            }

        }

        @Override
        void init() throws Exception {
            minit();
            result = mFairy.findPic("home.png");
            if (result.sim > 0.8f) {
                setTaskName(1);
            }
            nu=0;
        }

        @Override
        void content_01() throws Exception {
            result = mFairy.findPic("home.png");
            mFairy.onTap(0.8f,result,"出击",2000);

            result = mFairy.findPic("zhuxian.png");
            mFairy.onTap(0.8f, result, "主线", 3000);

            result = mFairy.findPic("che.png");
            mFairy.onTap(0.8f,result,"撤退",1000);

            result = mFairy.findPic("che1.png");
            mFairy.onTap(0.8f,result,779,497,815,521,"撤退-确定",1000);

            content_02();
        }

    }

    public void yx()throws Exception{
        new daily(mFairy){

            void create() throws Exception {
                TaskMain.TASKNAME="演习";
            }

            void content_02() throws Exception {
                overtime(50,0);

                result = mFairy.findPic("jx1.png");
                mFairy.onTap(0.8f, result, "战斗结束,继续", 2000);

                result = mFairy.findPic(new String[]{"yx.png","yx2.png"});
                mFairy.onTap(0.8f,result,"演习",2000);

                result = mFairy.findPic("yxScene.png");
                if(result.sim>0.8f){
                    err=0;
                    mFairy.onTap(0.8f,result,936,262,978,314,"点击对手",2000);
                }

                result = mFairy.findPic("chu.png");
                if(result.sim>0.8f) {
                    mFairy.onTap(0.8f, result, "出击", 500);
                    for(int i=0;i<10;i++){
                        result = mFairy.findPic(597,181,677,413,"yxend.png");
                        if(result.sim>0.8f) {
                            setTaskEnd();
                            LtLog.e(mFairy.getLineInfo("次数不足,演习结束!"));
                            init();
                            return;
                        }
                    }
                }
                result = mFairy.findPic("yx1.png");
                mFairy.onTap(0.8f,result,"开始对战",2000);
            }
        };
    }//演习

    private int nu=0;

    public void shangchuan(final int i)throws Exception{
        new daily(mFairy){

            @Override
            void create() throws Exception {
                TaskMain.TASKNAME="商船";
            }

            @Override
            void content_02() throws Exception {
                overtime(50,0);

                result = mFairy.findPic(new String[]{"mr.png","mr1.png"});
                mFairy.onTap(0.8f,result,"每日",1000);

                result = mFairy.findPic("mrScene.png");
                if(result.sim>0.8f){

                    Thread.sleep(3500);

                    result = mFairy.findPic("mrScene.png");
                    if(result.sim<0.8f){
                        return;
                    }
                    err=0;
                    result = mFairy.findPic("sc.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(0.8f, result, "商船", 5000);
                    }else{
                        mFairy.onTap(900,363,934,414,"切换查看",1000);
                    }
                }

                result = mFairy.findPic("tz.png");
                if(result.sim>0.8f) {
                    result = mFairy.findPic("end1.png");
                    if(result.sim>0.95f){
                        LtLog.e(mFairy.getLineInfo("次数不足,End!  "+result.sim));
                        setTaskEnd();
                        init();
                        return;
                    }
                    nu++;
                    err=0;
                    switch (i){
                        case 1:
                            mFairy.onTap(574,190,638,210,""+i+"",2000);
                            break;
                        case 2:
                            mFairy.onTap(537,328,597,348,""+i+"",2000);
                            break;
                        case 3:
                            mFairy.onTap(533,495,596,516,""+i+"",2000);
                            break;
                        case 4:
                            mFairy.onTap(511,625,598,639,""+i+"",2000);
                            break;
                    }

                    if(nu>3){
                        setTaskEnd();
                        init();
                        return;
                    }

                    result = mFairy.findPic(946,560,1256,693,new String[]{"chu.png","chu1.png"});
                    mFairy.onTap(0.8f, result, "出击", 1000);

                }
            }
        };


    }//商船

    public void zhanshu(final int i)throws Exception{
        new daily(mFairy){

        @Override
        void create() throws Exception {
            TaskMain.TASKNAME="战术";
        }

        @Override
        void content_02() throws Exception {
            overtime(50,0);

            result = mFairy.findPic(new String[]{"mr.png","mr1.png"});
            mFairy.onTap(0.8f,result,"每日",1000);

            result = mFairy.findPic("mrScene.png");
            if(result.sim>0.8f){
                err=0;

                Thread.sleep(3500);

                result = mFairy.findPic("mrScene.png");
                if(result.sim<0.8f){
                    return;
                }

                result = mFairy.findPic("zs.png");
                if(result.sim>0.8f){
                    mFairy.onTap(0.8f, result, "战术", 5000);
                }else{
                    mFairy.onTap(900,363,934,414,"切换查看",1000);
                }
            }

            result = mFairy.findPic("tz.png");
            if(result.sim>0.8f) {
                result = mFairy.findPic("end1.png");
                if(result.sim>0.95f){
                    LtLog.e(mFairy.getLineInfo("次数不足,End!  "+result.sim));
                    setTaskEnd();
                    init();
                    return;
                }
                err=0;
                nu++;
                switch (i){
                    case 1:
                        mFairy.onTap(574,190,638,210,""+i+"",2000);
                        break;
                    case 2:
                        mFairy.onTap(537,328,597,348,""+i+"",2000);
                        break;
                    case 3:
                        mFairy.onTap(533,495,596,516,""+i+"",2000);
                        break;
                }
                if(nu>3){
                    setTaskEnd();
                    init();
                    return;
                }
                result = mFairy.findPic(946,560,1256,693,new String[]{"chu.png","chu1.png"});
                mFairy.onTap(0.8f, result, "出击", 1000);
            }
        }
    };

}//战术

    public void haiyu(final int i)throws Exception{
        new daily(mFairy){

            void create() throws Exception {
                TaskMain.TASKNAME="海域";
            }

            void content_02() throws Exception {
                overtime(50,0);

                result = mFairy.findPic(new String[]{"mr.png","mr1.png"});
                mFairy.onTap(0.8f,result,"每日",1000);

                result = mFairy.findPic("mrScene.png");
                if(result.sim>0.8f){
                    err=0;

                    Thread.sleep(3500);

                    result = mFairy.findPic("mrScene.png");
                    if(result.sim<0.8f){
                        return;
                    }

                    result = mFairy.findPic("hy.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(0.8f, result, "海域", 5000);
                    }else{
                        mFairy.onTap(900,363,934,414,"切换查看",1000);
                    }
                }

                result = mFairy.findPic("tz.png");
                if(result.sim>0.95f) {
                    result = mFairy.findPic("end1.png");
                    if(result.sim>0.95f){
                        LtLog.e(mFairy.getLineInfo("次数不足,End!  "+result.sim));
                        setTaskEnd();
                        init();
                        return;
                    }
                    err=0;
                    nu++;
                    switch (i){
                        case 1:
                            mFairy.onTap(574,190,638,210,""+i+"",2000);
                            break;
                        case 2:
                            mFairy.onTap(537,328,597,348,""+i+"",2000);
                            break;
                        case 3:
                            mFairy.onTap(533,495,596,516,""+i+"",2000);
                            break;
                    }

                    if(nu>3){
                        setTaskEnd();
                        init();
                        return;
                    }


                    result = mFairy.findPic(946,560,1256,693,new String[]{"chu.png","chu1.png"});
                    mFairy.onTap(0.8f, result, "出击", 1000);
                }
            }
        };
    }//海域

    public void zhanshou(final int i)throws Exception{
        new daily(mFairy){

            @Override
            void create() throws Exception {
                TaskMain.TASKNAME="斩首";
            }

            @Override
            void content_02() throws Exception {
                overtime(50,0);

                result = mFairy.findPic(new String[]{"mr.png","mr1.png"});
                mFairy.onTap(0.8f,result,"每日",1000);

                result = mFairy.findPic("mrScene.png");
                if(result.sim>0.8f){
                    err=0;

                    Thread.sleep(3500);

                    result = mFairy.findPic("mrScene.png");
                    if(result.sim<0.8f){
                        return;
                    }

                    result = mFairy.findPic("xd.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(0.8f, result, "斩首", 5000);
                    }else{
                        mFairy.onTap(900,363,934,414,"切换查看",1000);
                    }
                }

                result = mFairy.findPic("tz.png");
                if(result.sim>0.8f) {
                    result = mFairy.findPic("end1.png");
                    if(result.sim>0.95f){
                        LtLog.e(mFairy.getLineInfo("次数不足,End!  "+result.sim));
                        setTaskEnd();
                        init();
                        return;
                    }
                    err=0;
                    nu++;
                    switch (i){
                        case 1:
                            mFairy.onTap(574,190,638,210,""+i+"",2000);
                            break;
                        case 2:
                            mFairy.onTap(537,328,597,348,""+i+"",2000);
                            break;
                        case 3:
                            mFairy.onTap(533,495,596,516,""+i+"",2000);
                            break;
                    }

                    if(nu>3){
                        setTaskEnd();
                        init();
                        return;
                    }


                    result = mFairy.findPic(946,560,1256,693,new String[]{"chu.png","chu1.png"});
                    mFairy.onTap(0.8f, result, "出击", 1000);
                }
            }
        };


    }//斩首
}

