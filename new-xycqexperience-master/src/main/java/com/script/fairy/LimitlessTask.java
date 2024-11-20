package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class LimitlessTask extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    FindResult result2;
    TimingActivity timingActivity;
    GameUtil gameUtil;
    OtherGame otherGame;
    SingleTask singleTask;
    TeamTask teamTask;

    public LimitlessTask(AtFairyImpl ATFairy) throws Exception {
        mFairy = ATFairy;
        gameUtil = new GameUtil(mFairy);
        timingActivity = new TimingActivity(mFairy);
        otherGame = new OtherGame(mFairy);
        singleTask = new SingleTask(mFairy);
        teamTask = new TeamTask(mFairy);
    }

    public void inOperation() throws Exception {
        result = mFairy.findPic("smOverGraph.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("过图中"));
            mFairy.initMatTime();
            err = 0;
            picCountMapS.clear();
            picCountMap.clear();
        }
        result = mFairy.findPic(447,493,855,566,new String[]{"In transmission.png","In transmission1.png"});
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

    static int szmap = 0;
    ControlSplit scsj = null;
    public void fieldHangMachine() throws Exception {
        new LimitlessTask(mFairy) {

            int gmx = 0, gmy = 0, num = 1, yhsr = 0,rc=0;//地图
            String img = "";
            ControlSplit back =null,qb = null, sjbs = null, sywp = null, lctx = null, lgz = null;
            List<String> list = new ArrayList<>();
            boolean jdplx = false;
            //xzdt 选择地图 totem图腾 zb坐标 ditunum地图NO
            @Override
            public void create() throws Exception {
                timingActivity.timingActivity();
                //定位坐标
                if (!AtFairyConfig.getOption("back").equals("") || AtFairyConfig.getOption("back") != null || !AtFairyConfig.getOption("back").equals("")) {
                    back = strSplit(AtFairyConfig.getOption("back"));
                    LtLog.e("时间"+back);
                }
                //清包
                if (!AtFairyConfig.getOption("optime1").equals("") && !AtFairyConfig.getOption("optime1").equals("0")) {
                    qb = strSplit(AtFairyConfig.getOption("optime1"));
                }
                //升级宝石
                if (!AtFairyConfig.getOption("optime2").equals("")) {
                    sjbs = strSplit(AtFairyConfig.getOption("optime2"));
                }
                //使用物品
                if (AtFairyConfig.getOption("7876").equals("1")) {
                    if (!AtFairyConfig.getOption("shijian").equals("")) {
                        sywp = strSplit(AtFairyConfig.getOption("shijian"));
                    }
                }
                //灵宠探险
                if (!AtFairyConfig.getOption("optime4").equals("")) {
                    lctx = strSplit(AtFairyConfig.getOption("optime4"));
                }
                //队长拉跟随
                if (!AtFairyConfig.getOption("optime5").equals("")) {
                    lgz = strSplit(AtFairyConfig.getOption("optime5"));
                }

                if (!AtFairyConfig.getOption("scsj").equals("")) {
                    scsj = strSplit(AtFairyConfig.getOption("scsj"));
                }
                /*//七星炼宝
                if (AtFairyConfig.getOption("qxlb").equals("1")) {
                    gameUtil.goCity("轩辕");
                    sevenStar();
                }*/
                //血盟清剿
                if (AtFairyConfig.getOption("xmqj").equals("1")) {
                    eliminate();
                }
                //决斗牌离线
                if (AtFairyConfig.getOption("jdplx").equals("1") && AtFairyConfig.getOption("7764").equals("1")) {
                    jdplx=true;
                }
                //地图 1 2 3
                if (AtFairyConfig.getOption("ditunum").equals("1")) {
                    list.add("1");
                }
                if (AtFairyConfig.getOption("ditunum1").equals("1")) {
                    list.add("2");
                }
                if (AtFairyConfig.getOption("ditunum2").equals("1")) {
                    list.add("3");
                }
                if (list.size() == 0) {
                    gameUtil.close(0);
                    LtLog.e(mFairy.getLineInfo("没有选择坐标地图，去等待限时活动"));
                    setTaskName(9);
                    return;
                }
            }

            public void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic(91,234,1038,551,new String[]{"death.png","death2.png"});
                result1 = mFairy.findPic(415,333,577,419,"ky.png");
                if(result.sim > 0.8f && result1.sim < 0.8f){
                    mFairy.onTap(0.8f, result, "复活", Sleep);
                    setTaskName(0);
                    return;
                }
            }

            public void content_0() throws Exception {

                timingActivity.timingActivity();

                while (mFairy.condit()){
                    //地图 1 2 3
                    if (list.size() == 0) {
                        if (AtFairyConfig.getOption("ditunum").equals("1")) {
                            list.add("1");
                        }
                        if (AtFairyConfig.getOption("ditunum1").equals("1")) {
                            list.add("2");
                        }
                        if (AtFairyConfig.getOption("ditunum2").equals("1")) {
                            list.add("3");
                        }
                    }

                    LtLog.e(mFairy.getLineInfo("选择地图中list="+list.toString()));

                    if (list.contains("1")) {
                        //大地/云天图腾
                        if (AtFairyConfig.getOption("Totem").equals("1")) {
                            img = "dadiTotems.png";
                        }
                        if (AtFairyConfig.getOption("Totem1").equals("1")) {
                            img = "yuntianTotems.png";
                        }
                        if (AtFairyConfig.getOption("xzdt").equals("") || AtFairyConfig.getOption("zb1").equals("") || AtFairyConfig.getOption("xzdt").equals("0")) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                if ("1".equals(it.next())) {
                                    it.remove();
                                }
                            }
                            continue;
                        } else {
                            yhsr = Integer.parseInt(AtFairyConfig.getOption("xzdt"));
                            String[] arr = AtFairyConfig.getOption("zb1").split(",");
                            gmx = Integer.parseInt(arr[0]);
                            gmy = Integer.parseInt(arr[1]);
                            LtLog.e(mFairy.getLineInfo("第一个点图腾入口=" + img + ",哪个地图=" + yhsr + ",坐标=" + gmx + "," + gmy));
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                if ("1".equals(it.next())) {
                                    it.remove();
                                }
                            }
                            break;
                        }
                    }

                    if (list.contains("2")) {
                        if (AtFairyConfig.getOption("Totem3").equals("1") == true) {
                            img = "dadiTotems.png";
                        }
                        if (AtFairyConfig.getOption("Totem4").equals("1") == true) {
                            img = "yuntianTotems.png";
                        }

                        if (AtFairyConfig.getOption("xzdt1").equals("") || AtFairyConfig.getOption("zb2").equals("") || AtFairyConfig.getOption("xzdt1").equals("0")) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                if ("2".equals(it.next())) {
                                    it.remove();
                                }
                            }
                            continue;
                        } else {
                            yhsr = Integer.parseInt(AtFairyConfig.getOption("xzdt1"));
                            String[] arr = AtFairyConfig.getOption("zb2").split(",");
                            gmx = Integer.parseInt(arr[0]);
                            gmy = Integer.parseInt(arr[1]);
                            LtLog.e(mFairy.getLineInfo("第二个点图腾入口=" + img + ",哪个地图=" + yhsr + ",坐标=" + gmx + "," + gmy));
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                if ("2".equals(it.next())) {
                                    it.remove();
                                }
                            }
                            break;
                        }
                    }
                    if (list.contains("3")) {
                        if (AtFairyConfig.getOption("Totem6").equals("1") == true) {
                            img = "dadiTotems.png";
                        }
                        if (AtFairyConfig.getOption("Totem7").equals("1") == true) {
                            img = "yuntianTotems.png";
                        }
                        if (AtFairyConfig.getOption("xzdt2").equals("") || AtFairyConfig.getOption("zb3").equals("") || AtFairyConfig.getOption("xzdt2").equals("0")) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                if ("3".equals(it.next())) {
                                    it.remove();
                                }
                            }
                            continue;
                        } else {
                            yhsr = Integer.parseInt(AtFairyConfig.getOption("xzdt2"));
                            String[] arr = AtFairyConfig.getOption("zb3").split(",");
                            gmx = Integer.parseInt(arr[0]);
                            gmy = Integer.parseInt(arr[1]);
                            LtLog.e(mFairy.getLineInfo("第三个点图腾入口=" + img + ",哪个地图=" + yhsr + ",坐标=" + gmx + "," + gmy));
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                if ("3".equals(it.next())) {
                                    it.remove();
                                }
                            }
                            break;
                        }
                    }
                    Thread.sleep(2000);
                }

                gameUtil.close(0);
                if (img.equals("")) {
                    setTaskName(3);
                    return;
                } else {
                    setTaskName(1);
                    return;
                }
            }

            public void content_1() throws Exception {
                boolean a = false;
                if (overtime(10, 0)) return;

                result1=mFairy.findPic("bianjing3.png");
                if (result1.sim > 0.8f){
                    mFairy.onTap(0.8f,result1,1237,112,1241,114,"打开地图",2500);

                    result  = mFairy.findPic(574,486,595,503,new String[]{"zszb.png","zszb1.png","zszb2.png"});
                    result1  = mFairy.findPic(560,495,599,522,new String[]{"zszb.png","zszb1.png","zszb2.png"});
                    if (result.sim > 0.8f || result1.sim > 0.8f){
                        LtLog.e("天外边境坐牢");
                        mFairy.onTap(429,339,437,350,"地图",2000);
                        mFairy.onTap(581,504,582,508,"地图",2000);
                        mFairy.onTap(1238,36,1251,57,"地图",2000);
                    }else {
                        mFairy.sleep(1000);
                        result  = mFairy.findPic(1205,9,1275,97,"dtgc.png");
                        mFairy.onTap(0.8f,result1,"关闭地图",1000);
                    }

                    result  = mFairy.findPic(52,18,1225,670,"delivery2.png");
                    if (result.sim > 0.8f){
                        result  = mFairy.findPic(52,18,1225,670,"duihua3.png");
                        if (result.sim > 0.8f){
                            mFairy.onTap(0.8f,result,"传送使者对话",Sleep);
                        }else {
                            mFairy.onTap(0.8f, result, result.x + 2, result.y + 153, result.x + 3, result.y + 154, "点击传送使者", Sleep);
                        }
                    }
                    result = mFairy.findPic("deliveryyes.png");
                    mFairy.onTap(0.8f,result,"传送",Sleep);
                }

                result = mFairy.findPic("team2.png");
                mFairy.onTap(0.8f, result, 689,373,698,380,"关掉退伍", Sleep);

                result = mFairy.findPic(new String[]{"Lower expansion.png","xiala.png"});
                mFairy.onTap(0.7f, result, "打开下扩展栏", 2000);

                result = mFairy.findPic(816,444,1163,546,new String[]{"qhTotems.png","tuteng.png"});
                mFairy.onTap(0.8f, result, "图腾", 1000);

                result = mFairy.findPic("Deliverysure.png");
                mFairy.onTap(0.8f, result, "传送确定", 10000);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(30, 0)) return;

                result = mFairy.findPic(img);
                if (result.sim > 0.8f) {
                    if ((yhsr > 70 && yhsr < 90) || yhsr > 100) {
                        mFairy.onTap(0.8f, result, 534, 164, 561, 180, "天外图腾传送", Sleep);
                    }
                    mFairy.onTap(0.8f, result, "图腾传送", 500);
                    mFairy.sleep(10000);

                    setTaskName(3);
                    return;
                }
            }

            int tu1 = 0, ceng1 = 0;

            public void content_3() throws Exception {
                if (overtime(10, 4)) return;
                timingActivity.timingActivity();
                mFairy.sleep(1000);
                tu1 = 0;
                ceng1 = 0;

                result = mFairy.findPic(876, 5, 1120, 146, "daily.png");
                mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", 3000);

                result = mFairy.findPic(749,66,1171,158,new String[]{"Activeinterface.png"});
                mFairy.onTap(0.8f, result, 1229,77,1248,91, "前往关闭", Sleep);


                //查看当前的位置
                result = mFairy.findPic(1213,247,1265,496,new String[]{"hcWorld.png"});
                if (result.sim > 0.8f) {
                    Thread.sleep(2000);
                    mFairy.condit();

                    for (int i = 1; i < 12; i++) {
                        result = mFairy.findPic(63,41,178,346,"gjdigong" + i + ".png");
                        if (result.sim > 0.9f) {
                            switch (i) {
                                case 1:
                                    LtLog.e(mFairy.getLineInfo("苍穹海"));
                                    tu1 = 1;
                                    break;
                                case 2:
                                    LtLog.e(mFairy.getLineInfo("昆仑墟"));
                                    tu1 = 2;
                                    break;
                                case 3:
                                    LtLog.e(mFairy.getLineInfo("孽龙囚"));
                                    tu1 = 3;
                                    break;
                                case 4:
                                    LtLog.e(mFairy.getLineInfo("葬魂谷"));
                                    tu1 = 4;
                                    break;
                                case 5:
                                    LtLog.e(mFairy.getLineInfo("霜火岭"));
                                    tu1 = 5;
                                    break;
                                case 6:
                                    LtLog.e(mFairy.getLineInfo("噬灵渊"));
                                    tu1 = 6;
                                    break;
                                case 7:
                                    LtLog.e(mFairy.getLineInfo("天外噬灵渊"));
                                    tu1 = 7;
                                    break;
                                case 8:
                                    LtLog.e(mFairy.getLineInfo("天外霜火岭"));
                                    tu1 = 8;
                                    break;
                                case 9:
                                    LtLog.e(mFairy.getLineInfo("焚天台"));
                                    tu1 = 9;
                                    break;
                                case 10:
                                    LtLog.e(mFairy.getLineInfo("天外焚天台"));
                                    tu1 = 10;
                                    break;
                                case 11:
                                    LtLog.e(mFairy.getLineInfo("氏族地宫"));
                                    tu1 = 11;
                                    break;
                            }
                            break;
                        }

                    }

                    for (int i = 5; i < 13; i++) {
                        result = mFairy.findPic(86,171,146,291,"gjdigong" + i + ".png");
                        if (result.sim > 0.9f) {
                            switch (i) {
                                case 5:
                                    LtLog.e(mFairy.getLineInfo("霜火岭"));
                                    tu1 = 11;
                                    break;
                                case 6:
                                    LtLog.e(mFairy.getLineInfo("噬灵渊"));
                                    tu1 = 11;
                                    break;
                                case 9:
                                    LtLog.e(mFairy.getLineInfo("焚天台"));
                                    tu1 = 11;
                                    break;
                                case 11:
                                    LtLog.e(mFairy.getLineInfo("氏族地宫"));
                                    tu1 = 11;
                                    break;
                            }
                            break;
                        }
                    }


                    result = mFairy.findPic(1216,344,1264,481,"gjdigong12.png");
                    if (result.sim > 0.9f) {
                        LtLog.e(mFairy.getLineInfo("山海"));
                        tu1 = 12;
                    }


                    if (tu1==12){


                    }else{

                        for (int i1 = 1; i1 < 8; i1++) {
                            result = mFairy.findPic(59, 46, 179, 478, "gjFloor" + i1 + ".png");
                            LtLog.e(mFairy.getLineInfo("选层1    "+result.sim));
                            if (result.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("选层2"));
                                switch (i1) {
                                    case 1:
                                        LtLog.e(mFairy.getLineInfo("一层"));
                                        ceng1 = 1;
                                        break;
                                    case 2:
                                        LtLog.e(mFairy.getLineInfo("二层"));
                                        ceng1 = 2;
                                        break;
                                    case 3:
                                        LtLog.e(mFairy.getLineInfo("三层"));
                                        ceng1 = 3;
                                        break;
                                    case 4:
                                        LtLog.e(mFairy.getLineInfo("四层"));
                                        ceng1 = 4;
                                        break;
                                    case 5:
                                        LtLog.e(mFairy.getLineInfo("五层"));
                                        ceng1 = 5;
                                        break;
                                    case 6:
                                        LtLog.e(mFairy.getLineInfo("六层"));
                                        ceng1 = 6;
                                        break;
                                    case 7:
                                        LtLog.e(mFairy.getLineInfo("七层"));
                                        ceng1 = 7;
                                        break;
                                }
                                break;
                            }
                            Thread.sleep(100);
                        }

                        if (ceng1 != 0) {
                            result = mFairy.findPic(57, 44, 176, 254, new String[]{"sz1.png", "sz2.png", "sz3.png", "sz4.png", "sz5.png", "sz6.png"});
                            LtLog.e("氏族内");
                            if (result.sim > 0.8f) {
                                tu1 = 11;
                                result = mFairy.findPic(78, 60, 143, 377, "gjdigong5.png");
                                if (result.sim > 0.85f) {
                                    LtLog.e("霜火岭");
                                    szmap = 1;
                                }
                                result = mFairy.findPic(76,90,158,320, "gjdigong6.png");
                                if (result.sim > 0.85f) {
                                    LtLog.e("噬");
                                    szmap = 2;
                                }
                                result = mFairy.findPic(78, 60, 143, 377, "gjdigong9.png");
                                if (result.sim > 0.85f) {
                                    LtLog.e("焚天台");
                                    szmap = 3;
                                }
                            }
                        }
                    }

                    setTaskName(4);
                }
            }//查看当前图(tu1) 层(ceng1)

            int ceng = 0, tu = 0, ceng2 = 0;
            String strpng = "";

            public void content_4() throws Exception {
                if (yhsr == 11 || yhsr == 12 || yhsr == 13 || yhsr == 14 || yhsr == 15 || yhsr == 16) {
                    ceng = yhsr - 10;
                    tu = 1;
                    strpng = "苍穹海";
                } else if (yhsr == 21 || yhsr == 22 || yhsr == 23 || yhsr == 24 || yhsr == 25) {
                    ceng = yhsr - 20;
                    tu = 2;
                    strpng = "昆仑墟";
                } else if (yhsr == 31 || yhsr == 32 || yhsr == 33 || yhsr == 34 || yhsr == 35) {
                    ceng = yhsr - 30;
                    tu = 3;
                    strpng = "孽龙囚";
                } else if (yhsr == 41 || yhsr == 42 || yhsr == 43 || yhsr == 44 || yhsr == 45) {
                    ceng = yhsr - 40;
                    tu = 4;
                    strpng = "葬魂谷";
                } else if (yhsr == 51 || yhsr == 52 || yhsr == 53 || yhsr == 54 || yhsr == 55 || yhsr == 56) {
                    ceng = yhsr - 50;
                    tu = 5;
                    strpng = "霜火岭";
                } else if (yhsr == 61 || yhsr == 62 || yhsr == 63 || yhsr == 64 || yhsr == 65) {
                    ceng = yhsr - 60;
                    tu = 6;
                    strpng = "噬灵渊";
                } else if (yhsr == 71 || yhsr == 72 || yhsr == 73 || yhsr == 74 || yhsr == 75) {
                    ceng = yhsr - 70;
                    tu = 7;
                    strpng = "天外噬灵渊";
                } else if (yhsr == 81 || yhsr == 82 || yhsr == 83 || yhsr == 84 || yhsr == 85 || yhsr == 86) {
                    ceng = yhsr - 80;
                    tu = 8;
                    strpng = "天外霜火岭";
                } else if (yhsr == 91 || yhsr == 92 || yhsr == 93 || yhsr == 94 || yhsr == 95) {
                    ceng = yhsr - 90;
                    tu = 9;
                    strpng = "焚天台";
                } else if (yhsr == 101 || yhsr == 102 || yhsr == 103 || yhsr == 104 || yhsr == 105 || yhsr == 106 || yhsr == 107) {
                    ceng = yhsr - 100;
                    tu = 10;
                    strpng = "天外焚天台";
                } else if (yhsr >= 111 && yhsr <= 117) {
                    ceng = yhsr - 110;
                    tu = 11;
                    strpng = "氏族地宫";
                } else if ( yhsr >=120 ) {
                    ceng = yhsr - 120;;
                    tu = 12;
                    strpng = "山海界";

                    LtLog.e(mFairy.getLineInfo("山海界进地图"));
                    gameUtil.goCity(strpng);
                    setTaskName(7);
                    return;
                }

                LtLog.e(mFairy.getLineInfo("tu=" + tu + ",tu1=" + tu1 + ",ceng=" + ceng + ",ceng1=" + ceng1));
                if (tu == tu1 && ceng == ceng1) {
                    LtLog.e(mFairy.getLineInfo("在同个地图同一层直接去"));
                    setTaskName(7);
                    return;
                } else if (tu == tu1 && ceng != ceng1) {

                    LtLog.e(mFairy.getLineInfo("在同个地图不同层"));
                    // gameUtil.close(1);
                    ceng2 = ceng1 - ceng;
                    setTaskName(5);
                    return;
                } else if (tu != tu1) {
                    LtLog.e(mFairy.getLineInfo("不在一个地图"));
                    gameUtil.goCity(strpng);
                    setTaskName(3);
                    return;

                }
            }//计算目标图(tu) 层(ceng)，同层图到7、同图不同层到5、不同图到3

            public void content_5() throws Exception {
                if (overtime(60, 0)) return;
                result = mFairy.findPic(876, 5, 1120, 146, "daily.png");
                mFairy.onTap(0.8f, result, 1237,112,1241,114, "打开地图", Sleep);

                result = mFairy.findPic("hcWorld.png");
                if (result.sim > 0.8f) {
                    if (ceng2 == 0) {
                        setTaskName(7);
                        return;
                    }
                    if (ceng2 > 0) {
                        result = mFairy.findPic("errszftt.png");
                        if (result.sim > 0.95f) {
                            mFairy.onTap(0.8f, result, 250, 145, 251, 146, "氏族焚天台二层去一层", Sleep);
                        } else {
                            mFairy.tap(779, 176);
                            LtLog.e(mFairy.getLineInfo("往下走"));
                        }
                        ceng2 = ceng2 - 1;
                    } else if (ceng2 < 0) {
                        if ((tu == 1 || tu == 2 || tu == 3 || tu == 6 || tu == 7 || tu == 8 || tu == 10 || tu == 11|| tu == 12) && ceng1 == 1) {
                            mFairy.tap(779, 176);
                            LtLog.e(mFairy.getLineInfo("往上走"));
                            ceng1 = 2;
                        } else {
                            mFairy.tap(781, 252);
                            LtLog.e(mFairy.getLineInfo("往上走"));
                        }
                        ceng2 = ceng2 + 1;
                    }
                    gameUtil.close(1);
                    setTaskName(6);
                    return;
                }
            }//同图不同层  ceng  tu 目标图和层 tu ceng1当前所在图和层 ceng2=所在层-目标层

            public void content_6() throws Exception {
                if (overtime(30, 0)) return;
/*                result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png"});
                if (result.sim < 0.8f){
                    mFairy.onTap(1123,327,1134,339, "开启自动战斗", Sleep);
                }*/
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 20) {
                    if (tu == 11) {
                        result = mFairy.findPic(876, 5, 1120, 146, "daily.png");
                        mFairy.onTap(0.8f, result, 851, 594, 866, 604, "下车", Sleep);
                        mFairy.onTap(0.8f, result, 645, 354, 668, 391, "点击传送npc", 2000);
                        result = mFairy.findPic(710, 123, 1068, 510, "digongsw.png");
                        mFairy.onTap(0.8f, result, "地宫守卫", 3000);
                        result = mFairy.findPic(22, 96, 530, 607, "shenru.png");
                        mFairy.onTap(0.8f, result, "深入氏族地宫", 5000);
                        result = mFairy.findPic(22, 96, 530, 607, "shenru.png");
                        mFairy.onTap(0.8f, result, "深入氏族地宫", 5000);
                        result = mFairy.findPic(22, 96, 530, 607, "jinru.png");
                        mFairy.onTap(0.8f, result, "进入氏族地宫", 5000);
                        result = mFairy.findPic(22, 96, 530, 607, "jinru.png");
                        mFairy.onTap(0.8f, result, "进入氏族地宫", 5000);
                    }
                    mFairy.initMatTime();
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic("smOverGraph.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("过图中"));
                    setTaskName(3);
                    return;
                }
            }//

            public void content_7() throws Exception {
                if(!AtFairyConfig.getOption("hpms").equals("")){
                    mFairy.onTap(1237,39,1251,60,"关闭挂机content7",1000);
                    gameUtil.moshi();
                    result = mFairy.findPic(876, 5, 1120, 146, "daily.png");
                    mFairy.onTap(0.8f, result, 1181, 71, 1203, 90, "打开地图", Sleep);
                }
                gameUtil.coordinate(String.valueOf(yhsr), gmx, gmy);
                timekeepInit("定位坐标");
                mFairy.initMatTime();
                setTaskName(8);
                return;
            }//定位到坐标 到8开始挂机

            //1、在在血盟领地立马回挂机点
            //2、发呆30秒以上 并且 没有正在攻击的敌人,会回挂机点

            public void content_8() throws Exception {
                int h = mFairy.dateHour();
                int m = mFairy.dateMinute();
                int j=0;
                result = mFairy.findPic(389, 458, 876, 526, new String[]{"NoBattle.png", "NoBattle3.png", "NoBattle4.png"});
                if (result.sim < 0.8f){
                    result1 = mFairy.findPic("daily.png");
                    if (result1.sim > 0.8f) {
                        mFairy.onTap(1123, 327, 1134, 339, "开启自动战斗", Sleep);
                    }
                 }

                result = mFairy.findPic(1213,197,1275,498,  "shijie.png");
                if (result.sim > 0.8f) {
                    j++;
                    if (j > 10) {
                        mFairy.onTap(0.8f, result, 1239,42,1251,52, "关闭世界地图", Sleep);
                    }
                }else{
                    j=0;
                }


                result = mFairy.findPic(1112,1,1267,32,new String[]{"xyc.png","xmld.png","ld.png"});
                if (result.sim > 0.8f) {
                    LtLog.e("在轩辕城或血盟领地");
                    setTaskName(0);
                    return;
                }

                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 30) {
                    mFairy.initMatTime();
                    for (int i = 0; i < 20; i++) {
                        mFairy.condit();
                        result = mFairy.findPic(379,97,560,142,"xuetiao.png");
                        mFairy.onTap(0.9f, result, 1220, 301, 1237, 318, "有敌人切下怪", Sleep);
                        if (result.sim > 0.9f) {
                            break;
                        }
                        if (i == 19) {
                            LtLog.e(mFairy.getLineInfo("发呆30秒了重置"));

                            setTaskName(0);
                            return;
                        }
                        Thread.sleep(1000);
                    }
                }
                //限时任务


                //5点重置宝|剿

                if (AtFairyConfig.getOption("gxrc").equals("1")  && h==5  && m==1){
                    timingActivity.zchd=true;timingActivity.hejiu=true;timingActivity.ylwq=true;timingActivity.qllj=true;timingActivity.jiuyousan=true;timingActivity.ljp=true;
                    timingActivity.ljp1=true;timingActivity.xhyx=true;timingActivity.jzwz=true;timingActivity.jdp=true;timingActivity.bwzq=true;timingActivity.sjhd=true;timingActivity.sjjz=true;
                    eliminate = false;
                    sevenStar = false;
                    LtLog.e("5点重置任务" );
                    create();
                    setTaskName(0);
                    return;
                }

                //限时任务
                if (timingActivity.timingActivity() == 1) {
                    setTaskName(0);
                    return;
                }

                /*result1 = mFairy.findPic(430,8,880,238, "bbm2.png");
                if (result1.sim > 0.8f) {
                    gameUtil.clearBag();
                }*/
                result = mFairy.findPic(188, 533, 369, 592, "complete.png");
                mFairy.onTap(0.8f, result, "完成", Sleep);
                result1 = mFairy.findPic(188, 533, 369, 592, "accept.png");
                mFairy.onTap(0.8f, result1, "接受", Sleep);
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    mFairy.initMatTime();
                    err = 0;
                }
                result = mFairy.findPic(823,545,1270,716,"qxComplete.png");
                mFairy.onTap(0.8f, result, 1057,638,1065,644,"err七星完成任务", Sleep);

                result = mFairy.findPic(469,177,806,350,"qr1.png");
                mFairy.onTap(0.8f, result, 369,361,902,665,"err七星奖励确定", Sleep);

                /*

                if (timingActivity.timingActivity() == 1) {
                    setTaskName(0);
                    return;
                }*/
                if (mFairy.dateHour()==0 && mFairy.dateMinute()==1){
                    timingActivity = new TimingActivity(mFairy);
                    Thread.sleep(60000);
                }
                LtLog.e("定位坐标时间"+back);
                if (back != null && back.choice == 1 && timekeep(0, back.timeMillis, "定位坐标")) {
                    LtLog.e(mFairy.getLineInfo("定位坐标一次"));
                    setTaskName(7);//每隔多少分定位坐标一次
                    return;
                }

                if (qb != null && qb.choice == 1 && timekeep(0, qb.timeMillis, "野外挂机清理背包")) {
                    LtLog.e("野外挂机清理背包！");
                    gameUtil.clearBag();
                }
                if (sjbs != null && sjbs.choice == 1 && timekeep(0, sjbs.timeMillis, "野外挂机升级宝石")) {
                    gameUtil.baoshi();
                }
                if (sywp != null  && timekeep(0, sywp.timeMillis, "野外挂机使用物品")) {
                   /* gameUtil.goods();*/
                    gameUtil.goods2();//使用物品

                }
                LtLog.e("使用物品"+(sywp != null  && timekeep(0, sywp.timeMillis, "野外挂机使用物品")));
                LtLog.e("清理背包"+(qb != null && qb.choice == 1 && timekeep(0, qb.timeMillis, "野外挂机清理背包")));
                if (lctx != null && lctx.choice == 1 && timekeep(0, lctx.timeMillis, "野外挂机灵宠探险")) {
                    gameUtil.tanxian();
                }

                result = mFairy.findPic(858, 480, 1035, 539, "use.png");
                mFairy.onTap(0.8f, result, "err自动使用", Sleep);

                if (lgz != null && lgz.choice == 1 && timekeep(0, lgz.timeMillis, "野外挂机队长拉跟站")) {
                    gameUtil.pullHeel();
                }
                if (timekeep(0,1200000, "离线决斗牌") && jdplx) {
                   gameUtil.jdplx();
                }
                if (AtFairyConfig.getOption("5217").equals("1") && scsj != null && timekeep(0, scsj.timeMillis, "野外挂机收菜种菜")) {
                    gameUtil.goCity("轩辕");
                    otherGame.nongchang();
                    setTaskName(0);
                    return;
                }

                if (taskand() == 1) {
                    setTaskName(3);
                    return;
                }

                /*result = mFairy.findPic(550,43,724,236, "man.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("背包满了分解下"));
                    gameUtil.clearBag();
                }*/
                result = mFairy.findPic("xmld.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("在血盟领地"));
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic(749,66,1171,158,new String[]{"Activeinterface.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常界面"));
                if (result.sim > 0.8f) {
                    rc++;
                    if (rc>=5){
                        rc=0;
                        result = mFairy.findPic(749,66,1171,158,new String[]{"Activeinterface.png"});
                        mFairy.onTap(0.8f, result, 1228,31,1240,47,"关闭日常", Sleep);
                    }
                }
                result = mFairy.findPic("SpiritInterface.png");
                mFairy.onTap(0.8f, result, 1155,57,1164,66,"关闭灵宠页面", Sleep);
                result = mFairy.findPic("huifu.png");
                mFairy.onTap(0.8f, result, "恢复", Sleep);
            }

            public void content_9() throws Exception {
                gameUtil.moshi();

                if (timingActivity.timingActivity() == 1) {
                    setTaskName(0);
                    return;
                }

                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 40) {
                    mFairy.initMatTime();
                    for (int i = 0; i < 20; i++) {
                        mFairy.condit();
                        result = mFairy.findPic(1112,1,1267,32,"xmld.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("在血盟领地"));
                            setTaskName(0);
                            return;
                        }
                        if (i == 19) {
                            LtLog.e(mFairy.getLineInfo("发呆40秒了重置"));
                            setTaskName(0);
                            return;
                        }
                        Thread.sleep(1000);
                    }
                }


                if (back != null && qb.choice == 1 && timekeep(0, back.timeMillis, "定位坐标")) {
                    setTaskName(7);//每隔多少分定位坐标一次
                    return;
                }

                if (qb != null && qb.choice == 1 && timekeep(0, qb.timeMillis, "野外挂机清理背包")) {
                    LtLog.e("野外挂机清理背包！");
                    gameUtil.clearBag();

                }
                if (sjbs != null && sjbs.choice == 1 && timekeep(0, sjbs.timeMillis, "野外挂机升级宝石")) {
                    gameUtil.baoshi();
                }
                if (sywp != null && timekeep(0, sywp.timeMillis, "野外挂机使用物品")) {
                    gameUtil.goods2();//使用物品
                }
                if (lctx != null && lctx.choice == 1 && timekeep(0, lctx.timeMillis, "野外挂机灵宠探险")) {
                    gameUtil.tanxian();
                }
                if (lgz != null && lgz.choice == 1 && timekeep(0, lgz.timeMillis, "野外挂机队长拉跟站")) {
                    gameUtil.pullHeel();
                }
                if (timekeep(0,1200000, "离线决斗牌") && jdplx) {
                    gameUtil.jdplx();
                }
                if (AtFairyConfig.getOption("5217").equals("1") && scsj != null && timekeep(0, scsj.timeMillis, "野外挂机收菜种菜")) {
                    gameUtil.goCity("轩辕");
                    otherGame.nongchang();
                    setTaskName(0);
                    return;
                }
                if (mFairy.dateHour()==0 && mFairy.dateMinute()==1){
                    timingActivity = new TimingActivity(mFairy);
                    Thread.sleep(60000);
                }
                if (taskand() == 1) {
                    setTaskName(0);
                    return;
                }
                result1 = mFairy.findPic(1043,73,1122,157,"bagM1.png");
                result = mFairy.findPic(567,24,707,224, "bagM.png");
                if (result1.sim > 0.8f && result.sim > 0.8f) {
                    /*LtLog.e(mFairy.getLineInfo("背包满了炼宝清剿结束"));
                    LimitlessTask.this.eliminate = true;
                    LimitlessTask.this.sevenStar = true;*/
                    LtLog.e(mFairy.getLineInfo("炼宝清剿背包满清理一次"));
                    gameUtil.clearBag();
                }
            }
        }.taskContent(mFairy, "定点挂机中");
    }//定点挂机

    boolean eliminate = false;
    public void eliminate() throws Exception {
        new LimitlessTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("eliminate.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    LimitlessTask.this.eliminate = true;
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(25, 99)) return;
                List<FindResult> listResult = mFairy.findPic(601, 145, 1133, 530, 0.8f, "xmqjOrder.png");
                if (listResult.size() != 0) {//1076,465   888,448
                    mFairy.onTap(0.8f, listResult.get(listResult.size() - 1), listResult.get(listResult.size() - 1).x, listResult.get(listResult.size() - 1).y, listResult.get(listResult.size() - 1).x + 1, listResult.get(listResult.size() - 1).y + 1, "找到清剿令", Sleep);
                    mFairy.onTap(0.8f, listResult.get(listResult.size() - 1), listResult.get(listResult.size() - 1).x-180, listResult.get(listResult.size() - 1).y-13, listResult.get(listResult.size() - 1).x-179 , listResult.get(listResult.size() - 1).y -12, "找到清剿令", Sleep);
                }

             /*   result = mFairy.findPic( 389,55,1260,701,"shiyong.png");
                mFairy.onTap(0.8f,result,"使用",Sleep);*/

                result = mFairy.findPic( 486,139,795,191,"only.png");
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic( "num.png");
                if (result.sim > 0.8f) {
                    LimitlessTask.this.eliminate = true;
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "血盟清剿任务中");
    }//血盟清剿

    public void eliminate22() throws Exception {
        new LimitlessTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = gameUtil.mission("eliminate.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    LimitlessTask.this.eliminate = true;
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                if (overtime(5, 99)) return;
                List<FindResult> listResult = mFairy.findPic(601, 145, 1133, 530, 0.8f, "xmqjOrder.png");
                if (listResult.size() != 0) {//1076,465   888,448
                    mFairy.onTap(0.8f, listResult.get(listResult.size() - 1), listResult.get(listResult.size() - 1).x, listResult.get(listResult.size() - 1).y, listResult.get(listResult.size() - 1).x + 1, listResult.get(listResult.size() - 1).y + 1, "找到清剿令", Sleep);
                    mFairy.onTap(0.8f, listResult.get(listResult.size() - 1), listResult.get(listResult.size() - 1).x-180, listResult.get(listResult.size() - 1).y-13, listResult.get(listResult.size() - 1).x-179 , listResult.get(listResult.size() - 1).y -12, "找到清剿令", Sleep);
                }
                result = mFairy.findPic( 389,55,1260,701,"shiyong.png");
                mFairy.onTap(0.8f,result,"使用",Sleep);

                result = mFairy.findPic( "only.png");
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy, "血盟清剿任务中");
    }//血盟清剿

    boolean sevenStar = false;
    public void sevenStar() throws Exception {
        new LimitlessTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                result = mFairy.findPic(0,255,39,462,"team.png");
                mFairy.onTap(0.95f, result, 15, 234, 29, 262, "在队伍栏切换到任务栏", Sleep);

                result = mFairy.findPic("shizu3.png");
                mFairy.onTap(0.95f, result, 117,177,126,184, "在氏族栏切到普通栏", Sleep);

                result = mFairy.findPic("beibao2.png");
                mFairy.onTap(0.8f, result, 1194,39,1202,52, "cha", Sleep);

                result = mFairy.findPic(0,161,42,431,"taskseven.png");
                mFairy.onTap(0.8f, result, "打开任务栏", Sleep);

                result = mFairy.findPic(1093,84,1223,313,"yijie1.png");
                mFairy.onTap(0.8f, result, "已接", 1500);
                //mFairy.onTap(0.8f, result, 219,123,229,132,"收纳主线", Sleep);

                result = mFairy.findPic(64,95,383,240,"huan.png");
                result1 = mFairy.findPic(102,24,255,108,"intask1.png");
                if (result.sim < 0.8f && result1.sim > 0.8f){
                    mFairy.onTap(0.8f, result1, 219,123,229,132,"收纳主线", 3000);
                }


                result = mFairy.findPic(77,217,387,585, new String[]{ "zuoceTreasure2.png","zuoceTreasure3.png"});
                if (result.sim > 0.7f) {
                    mFairy.onTap(0.8f, result, 1156,66,1167,78,"关闭任务", Sleep);
                    LtLog.e(mFairy.getLineInfo("已经接取了炼宝任务"));
                    setTaskEnd();
                    return;
                }


                if (result1.sim > 0.8f) {
                    mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 0, 228, 400, 228, 126, 500, 1500, 2);
                }

                if (overtime(5, 2)) return;

            }

            public void content_2() throws Exception {

                result1=mFairy.findPic(1031,4,1275,171,new String[]{"cha10.png","bxcha.png","bxcha1.png","fxcha.png","shcha.png","rxcha.png","rxcha1.png"});
                mFairy.onTap(0.9f,result1,"关叉2",300);

                int ret = gameUtil.mission("qxTreasure1.png", 1);
                if (ret == 1) {
                    LtLog.i(mFairy.getLineInfo("低星练宝开始"));

                    setTaskName(3);
                    return;
                } else {
                    ret = gameUtil.mission("qxTreasure.png", 1);
                    if (ret == 1) {
                        LtLog.i(mFairy.getLineInfo("高星练宝开始"));
                        setTaskName(3);
                        return;
                    } else {

                        result = mFairy.findPic(749,66,1171,158,new String[]{"Activeinterface.png"});
                        mFairy.onTap(0.8f, result, 1229,77,1248,91, "前往关闭", Sleep);

                        LimitlessTask.this.sevenStar = true;
                        setTaskEnd();
                        return;
                    }
                }
            }

            public void content_3() throws Exception {
                if (overtime(12, 1)) return;

                result = mFairy.findPic(500,106,801,249, "bbm1.png");
                if (result.sim > 0.7f) {
                    LtLog.e("背包满清包");
                    gameUtil.clearBag();
                }

                result = mFairy.findPic(749,66,1171,158,new String[]{"Activeinterface.png"});
                mFairy.onTap(0.8f, result, 1229,77,1248,91, "前往关闭", Sleep);

                result = mFairy.findPic( "tuteng3.png");
                mFairy.onTap(0.8f, result, 988,120,1000,131, "关闭", Sleep);

                result = mFairy.findPic(749,66,1171,158,new String[]{"Activeinterface.png"});
                mFairy.onTap(0.8f, result, 1222,81,1227,88,"日常界面关闭", Sleep);

                result = mFairy.findPic(91,234,1038,551,new String[]{"death.png","death2.png"});
                result1 = mFairy.findPic(415,333,577,419,"ky.png");
                if(result.sim > 0.8f && result1.sim < 0.8f){
                    mFairy.onTap(0.8f, result, "复活", Sleep);
                    mFairy.sleep(30000);
                }

                result = mFairy.findPic(1099, 277, 1173, 348,"Hangup1.png");
                mFairy.onTap(0.8f, result, "关闭挂机", Sleep);

                result = mFairy.findPic(823,545,1270,716,"qxComplete.png");
                mFairy.onTap(0.8f, result, 1057,638,1065,644,"err七星完成任务", Sleep);

                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 5) {
                    setTaskName(1);return;
                   // mFairy.initMatTime();
/*                    LtLog.e(mFairy.getLineInfo("炼宝接取任务完毕"));
                    setTaskEnd();
                    return;*/
/*                    result = mFairy.findPic(18, 116, 532, 607, "zuoceTreasure.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("炼宝接取任务完毕"));
                        setTaskEnd();
                        return;
                    }
                    mFairy.taskSlid(err, new int[]{0, 2, 4, 6,8,10,12,14,16}, 0, 116, 426, 116, 245, 500, 1500, 2);
                    if (overtime(16, 2)) return;*/
                }

            }

        }.taskContent(mFairy, "七星炼宝任务中");

    }//七星炼宝

    int lbcount = 0;
    int l = 0;
    int x = 0;

    boolean newLB = false;
    boolean newQJ = false;
    public int taskand() throws Exception {
        lbcount++;

        if (lbcount > 10000) {
            lbcount = 0;
        }

        newQJ = (AtFairyConfig.getOption("xmqj").equals("1") && !LimitlessTask.this.eliminate);
        newLB = (AtFairyConfig.getOption("qxlb").equals("1") && !LimitlessTask.this.sevenStar);

        LtLog.e(mFairy.getLineInfo("血盟清剿=" + newQJ + ",七星炼宝=" + newLB));


        result = mFairy.findPic("beibao2.png");
        mFairy.onTap(0.8f, result, 1194,39,1202,52, "cha", Sleep);


        result = mFairy.findPic(0,255,39,462,"team.png");
        mFairy.onTap(0.95f, result, 15, 234, 29, 262, "在队伍栏切换到任务栏", Sleep);

        result=mFairy.findPic("put.png");
        mFairy.onTap(0.8f,result,"普通",Sleep);


        result = mFairy.findPic(46, 177, 274, 469, "zuoceEliminate.png");
        if (result.sim > 0.7f && newQJ) {
            LtLog.e(mFairy.getLineInfo("血盟清剿检测中"));
            x=0;
            lbcount = 0;
            result1 = mFairy.findPic(result.x + 171, result.y - 15, result.x + 372, result.y + 53, "lbLeftCompletion.png");
            if (result1.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "左侧血盟清剿完成", Sleep);
            }
        }


        result = mFairy.findPic(46, 177, 274, 469, "zuoceTreasure.png");
        if (result.sim > 0.65f && newLB) {
            LtLog.e(mFairy.getLineInfo("七星炼宝检测中"));
            l=0;
            lbcount = 0;
            result1 = mFairy.findPic(result.x + 171, result.y - 15, result.x + 372, result.y + 53, "lbLeftCompletion.png");
            if (result1.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "左侧炼宝完成", Sleep);
            }
        }


        if( newQJ||newLB){

            result = mFairy.findPic2(0,164,36,389,"qxlbtask.png");
            LtLog.e("locount=="+lbcount);
            if (result.sim > 0.8f) {
                l+=1;
                x+=1;
                mFairy.taskSlid(lbcount, new int[]{0, 2, 4, 6 ,8}, 1, 76, 400, 76, 220, 500, 1000, 2);
                LtLog.e("76, 400, 76, 220,");
            } else {
                lbcount = 0;
            }

            if (lbcount>=8){
                lbcount = 0;
                LtLog.e("76,220 , 76, 400,");
                mFairy.taskSlid(lbcount, new int[]{0}, 1, 76,220 , 76, 400, 500, 1500, 2);
                mFairy.taskSlid(lbcount, new int[]{0}, 1, 76,220 , 76, 400, 500, 1500, 2);
            }



            LtLog.i(mFairy.getLineInfo("l:"+l+"x:"+x));

            if ( x > 6 || l > 6) {
                l=0;
                x=0;

                LtLog.e(mFairy.getLineInfo("----------------超时----------------"));


                result = mFairy.findPic(0,161,42,431,"taskseven.png");
                mFairy.onTap(0.8f, result, "打开任务栏", Sleep);

                result = mFairy.findPic(1093,84,1223,313,"yijie1.png");
                mFairy.onTap(0.8f, result, "已接", 1500);

                result = mFairy.findPic("huan.png");
                result1 = mFairy.findPic(102,24,255,108,"intask1.png");
                if (result.sim <0.8f && result1.sim > 0.8f){
                    mFairy.onTap(0.8f, result1, 219,123,229,132,"收纳主线", Sleep);
                }


                if (newQJ) {

                    result = mFairy.findPic(77,217,387,585, "zuoceEliminate2.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("已经接取了清剿任务"));
                        mFairy.onTap(0.7f, result, 1155,58,1165,65,"cha", Sleep);
                    }else {
                        eliminate();
                    }

                }

                if (newLB) {
                    result = mFairy.findPic(77,217,387,585, "zuoceTreasure2.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("已经接取了炼宝任务"));
                        mFairy.onTap(0.8f, result, "点击", Sleep);
                        result = mFairy.findPic(899,134,1040,184,"54.png");
                        if (result.sim > 0.7f) {
                            mFairy.onTap(0.8f, result, 977,623,987,628,"前往", 1500);
                        }else{
                            mFairy.onTap(0.7f, result1, 1155,58,1165,65,"cha", Sleep);
                        }
                    }else {
                        sevenStar();
                        return 1;
                    }
                }
            }
        }


/*
        //七星炼宝  && 血盟清剿 && (sevenStar==false || eliminate==false)
        if (AtFairyConfig.getOption("qxlb").equals("1") && AtFairyConfig.getOption("xmqj").equals("1") &&
                (!LimitlessTask.this.eliminate && !LimitlessTask.this.sevenStar)) {

            LtLog.e("lbcount"+lbcount++ +"  炼宝："+l+"  清剿："+x);

            LtLog.e(mFairy.getLineInfo("炼宝和清剿任务中"));

        }
        //七星炼宝  && 血盟清剿 && (sevenStar==false || eliminate==true)
        if (AtFairyConfig.getOption("qxlb").equals("1") && AtFairyConfig.getOption("xmqj").equals("1") &&
                (LimitlessTask.this.eliminate && !LimitlessTask.this.sevenStar)) {
            LtLog.e("lbcount"+lbcount++ +"  炼宝："+l+"  清剿：已完成");
            LtLog.e(mFairy.getLineInfo("炼宝任务清剿完成任务中"));

            result = mFairy.findPic(0,255,39,462,"team.png");
            mFairy.onTap(0.95f, result, 15, 234, 29, 262, "在队伍栏切换到任务栏", Sleep);

            result=mFairy.findPic("put.png");
            mFairy.onTap(0.8f,result,"普通",Sleep);

            result = mFairy.findPic(46, 177, 274, 469, "zuoceTreasure.png");
            if (result.sim > 0.65f) {
                l=0;
                lbcount = 0;
                result1 = mFairy.findPic(result.x + 171, result.y - 15, result.x + 372, result.y + 53, "lbLeftCompletion.png");
                if (result1.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "左侧炼宝完成", Sleep);
                }
            }

            result = mFairy.findPic(46, 177, 274, 469, "zuoceEliminate.png");
            if (result.sim > 0.7f) {
                x=0;
                lbcount = 0;
                result1 = mFairy.findPic(result.x + 171, result.y - 15, result.x + 372, result.y + 53, "lbLeftCompletion.png");
                if (result1.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "左侧血盟清剿完成", Sleep);
                }
            }

            result = mFairy.findPic2(0,164,36,389,"qxlbtask.png");
            LtLog.e("locount=="+lbcount);
            if (result.sim > 0.8f) {
                l+=1;
                x+=1;
                mFairy.taskSlid(lbcount, new int[]{0, 2, 4, 6 ,8}, 1, 76, 400, 76, 220, 500, 1000, 2);
                LtLog.e("76, 400, 76, 220,");
            } else {
                lbcount = 0;
            }

            if (lbcount>=8){
                lbcount = 0;
                LtLog.e("76,220 , 76, 400,");
                mFairy.taskSlid(lbcount, new int[]{0}, 1, 76,220 , 76, 400, 500, 1500, 2);
                mFairy.taskSlid(lbcount, new int[]{0}, 1, 76,220 , 76, 400, 500, 1500, 2);
            }

            if ( l > 15) {
                l=0;
                if (!LimitlessTask.this.sevenStar) {
                    result = mFairy.findPic(0,161,42,431,"taskseven.png");
                    mFairy.onTap(0.8f, result, "打开任务栏", Sleep);

                    result = mFairy.findPic(1093,84,1223,313,"yijie1.png");
                    mFairy.onTap(0.8f, result, "已接", 1500);
                    //mFairy.onTap(0.8f, result, 219,123,229,132,"收纳主线", Sleep);
                    result = mFairy.findPic("huan.png");
                    result1 = mFairy.findPic(102,24,255,108,"intask1.png");
                    if (result.sim <0.8f && result1.sim > 0.8f){
                        mFairy.onTap(0.8f, result1, 219,123,229,132,"收纳主线", Sleep);
                    }

                    result = mFairy.findPic(77,217,387,585, "zuoceTreasure2.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("已经接取了炼宝任务"));
                        mFairy.onTap(0.8f, result, "点击", Sleep);
                        result = mFairy.findPic(899,134,1040,184,"54.png");
                        if (result.sim > 0.7f) {
                            mFairy.onTap(0.8f, result, 977,623,987,628,"前往", 1500);
                        }else{
                            mFairy.onTap(0.7f, result1, 1155,58,1165,65,"cha", Sleep);
                        }
                    }else {
                        sevenStar();
                        return 1;
                    }
                }
            }
        }
        //七星炼宝  && 血盟清剿 && sevenStar==false
        if (AtFairyConfig.getOption("qxlb").equals("1") && !AtFairyConfig.getOption("xmqj").equals("1") && !LimitlessTask.this.sevenStar) {
            LtLog.e(mFairy.getLineInfo("炼宝任务中"));
            result = mFairy.findPic(0,255,39,462,"team.png");
            mFairy.onTap(0.95f, result, 15, 234, 29, 262, "在队伍栏切换到任务栏", Sleep);
            result=mFairy.findPic("put.png");
            mFairy.onTap(0.8f,result,"普通",Sleep);

            result = mFairy.findPic(46, 177, 274, 469, "zuoceTreasure.png");
            if (result.sim > 0.7f) {
                lbcount = 0;
                result1 = mFairy.findPic(result.x + 171, result.y - 15, result.x + 372, result.y + 53, "lbLeftCompletion.png");
                if (result1.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "左侧炼宝完成", Sleep);
                }
            }
            result = mFairy.findPic2(0,164,36,389,"qxlbtask.png");
            if (result.sim > 0.8f) {
                mFairy.taskSlid(lbcount, new int[]{0, 2, 4, 6 ,8,10,12,14}, 0, 76, 400, 76, 220, 500, 1500, 2);
            } else {
                lbcount = 0;
            }
            if (lbcount > 8) {
                lbcount = 0;
                if (!LimitlessTask.this.sevenStar) {
                    result = mFairy.findPic(0,161,42,431,"taskseven.png");
                    mFairy.onTap(0.8f, result, "打开任务栏", Sleep);

                    result = mFairy.findPic(1093,84,1223,313,"yijie1.png");
                    mFairy.onTap(0.8f, result, "已接", 1500);
                    //mFairy.onTap(0.8f, result, 219,123,229,132,"收纳主线", Sleep);
                    result = mFairy.findPic("huan.png");
                    result1 = mFairy.findPic(102,24,255,108,"intask1.png");
                    if (result.sim <0.8f && result1.sim > 0.8f){
                        mFairy.onTap(0.8f, result1, 219,123,229,132,"收纳主线", Sleep);
                    }

                    result = mFairy.findPic(77,217,387,585, "zuoceTreasure2.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("已经接取了炼宝任务"));
                        mFairy.onTap(0.8f, result, "点击", Sleep);
                        result = mFairy.findPic(899,134,1040,184,"54.png");
                        if (result.sim > 0.7f) {
                            mFairy.onTap(0.8f, result, 977,623,987,628,"前往", 1500);
                        }else{
                            mFairy.onTap(0.7f, result1, 1155,58,1165,65,"cha", Sleep);
                        }
                    }else {
                        sevenStar();
                        return 1;
                    }

                }
            }
        }
        //七星炼宝  && 血盟清剿 && eliminate==false
        if (!AtFairyConfig.getOption("qxlb").equals("1") && AtFairyConfig.getOption("xmqj").equals("1") && !LimitlessTask.this.eliminate) {
            LtLog.e(mFairy.getLineInfo("清剿任务中"));
            result = mFairy.findPic(0,255,39,462,"team.png");
            mFairy.onTap(0.95f, result, 15, 234, 29, 262, "在队伍栏切换到任务栏", Sleep);
            result=mFairy.findPic("put.png");
            mFairy.onTap(0.8f,result,"普通",Sleep);

            result = mFairy.findPic(46, 177, 274, 469, "zuoceEliminate.png");
            if (result.sim > 0.7f) {
                lbcount = 0;
                result1 = mFairy.findPic(result.x + 171, result.y - 15, result.x + 372, result.y + 53, "lbLeftCompletion.png");
                if (result1.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "左侧血盟清剿完成", Sleep);
                }
            }
            result = mFairy.findPic2(0,164,36,389,"qxlbtask.png");
            if (result.sim > 0.8f) {
                mFairy.taskSlid(lbcount, new int[]{0, 2, 4, 6 ,8,10,12,14}, 0, 76, 400, 76, 220, 500, 1500, 2);
            } else {
                lbcount = 0;
            }
            if (lbcount > 8) {
                if (!LimitlessTask.this.eliminate) {
                    result = mFairy.findPic(0,161,42,431,"taskseven.png");
                    mFairy.onTap(0.8f, result, "打开任务栏", Sleep);

                    result = mFairy.findPic(1093,84,1223,313,"yijie1.png");
                    mFairy.onTap(0.8f, result, "已接", 1500);
                    //mFairy.onTap(0.8f, result, 219,123,229,132,"收纳主线", Sleep);
                    result = mFairy.findPic("huan.png");
                    result1 = mFairy.findPic(102,24,255,108,"intask1.png");
                    if (result.sim <0.8f && result1.sim > 0.8f){
                        mFairy.onTap(0.8f, result1, 219,123,229,132,"收纳主线", Sleep);
                    }

                    result = mFairy.findPic(77,217,387,585, "zuoceEliminate2.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("已经接取了清剿任务"));
                        mFairy.onTap(0.8f, result1, 1155,58,1165,65,"cha", Sleep);

                    }else {
                        eliminate();
                    }
                }
                lbcount = 0;
            }
        }*/

        return 0;
    }//野外挂机中的七星与清剿

}
