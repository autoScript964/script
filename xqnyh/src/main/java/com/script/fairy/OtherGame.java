package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.TaskContent;
import com.script.framework.AtFairyImpl;

import java.util.List;

/**
 * Created by Administrator on 2019/11/5 0005.
 */

public class OtherGame extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    GameUtil gameUtil;

    public OtherGame(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gameUtil = new GameUtil(mFairy);
    }

    public void inOperation() throws Exception {
        result = mFairy.findPic("Over drawing.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("过图中"));
            mFairy.initMatTime();
            err = 0;
        }
        gameUtil.zh();
        gameUtil.gdFBTeam();
    }
    //福利签到
    static boolean fuli = true;

    public void welfare() throws Exception {
        fuli = false;
        new OtherGame(mFairy) {

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(10, 99)) return;

                result = mFairy.findPic(639, 6, 1126, 176, "fl.png");
                mFairy.onTap(0.8f, result, "福利", 8000);

                result = mFairy.findPic("qian2.png");
                if (result.sim > 0.8f) {

                    Thread.sleep(8000);

                    result = mFairy.findPic("qian2.png");
                    if (result.sim > 0.8f) {


                        List<FindResult> resultList = mFairy.findPic(112,93,989,594, 0.75f, "qiangou.png");
                        if (resultList.size() == 0) {
                            mFairy.onTap(162,149,170,167, "第一次签到", 1000);
                        } else {
                            result = resultList.get(resultList.size() - 1);
                            if (result.x > 855) {
                                mFairy.onTap(169, result.y +100 , 170, result.y +101, "签到", 1000);
                            } else {
                                mFairy.onTap(result.x + 150, result.y, result.x + 151, result.y + 1, "签到", 1000);
                            }
                        }


                       /* for (int i = 164; i < 975; i = i + 162) {

                            for (int j = 151; j < 585; j = j + 100) {

                                mFairy.tap( i, j);

                                mFairy.onTap(0.8f, result, 1084, 191, 1085, 192, "签到11", 10);
                            }
                        }*/

                        gameUtil.close(0);
                        setTaskEnd();
                        return;
                    }
                }


                result = mFairy.findPic(308,70,978,193,"qian.png");
                if (result.sim > 0.8f) {

                    Thread.sleep(8000);

                    LtLog.e(mFairy.getLineInfo("福利界面"));

                    for (int i = 0; i < 3; i++) {
                        Thread.sleep(500);
                        result = mFairy.findPic(133, 398, 910, 648, new String[]{"fl1.png", "fl2.png"});
                        mFairy.onTap(0.8f, result, "月卡返利领取", 1000);
                    }


                    result = mFairy.findPic(913,463,1077,659,"qian1.png");
                    mFairy.onTap(0.8f, result, "签到", 3000);
                    if (result.sim > 0.8f) {
                        return;
                    }

                    result = mFairy.findPic(308,70,978,193,"qian.png");
                    if (result.sim > 0.8f) {
                        gameUtil.close(0);
                        setTaskEnd();
                        return;
                    }

                }


            }
        }.taskContent(mFairy, "福利签到");
        fuli = true;
    }
    //分享空间

    int fen = 0;
    public void fxkj() throws Exception {
        new OtherGame(mFairy) {


            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }


            public void content_1() throws Exception {
                if (overtime(15, 2)) return;
                Thread.sleep(1000);
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 387, 682, 388, 683, "点击好友", 3000);
                mFairy.onTap(0.8f, result, 1164, 167, 1165, 168, "切换到好友", Sleep);
                mFairy.onTap(0.8f, result, 1164, 167, 1165, 168, "切换到好友", Sleep);

                result = mFairy.findPic("haoyou.png");
                mFairy.onTap(0.9f, result, "切换到好友界面", 3000);

                result = mFairy.findPic("hy.png");
                mFairy.onTap(0.8f, result, 462, 94, 481, 112, "好友界面", 18000);

                result = mFairy.findPic("ycmd.png");
                mFairy.onTap(0.9f, result, "右侧梦岛", 18000);

                result = mFairy.findPic("fxmd.png");
                mFairy.onTap(0.8f, result, "分享梦岛", 2000);

                result = mFairy.findPic(708, 324, 878, 513, "hu1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "世界频道分享", 2000);
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                if (AtFairyConfig.getOption("fxlj").equals("1")) {
                    Thread.sleep(3000);
                    fen = 0;
                    setTaskName(3);
                    return;
                } else {
                    gameUtil.close(0);
                    setTaskEnd();
                }
            }

            public void content_3() throws Exception {

                if (overtime(10, 99)) return;
                Thread.sleep(1000);

                result = mFairy.findPic("wodemengdao.png");
                mFairy.onTap(0.8f, result, "我的梦岛", 18000);

                result = mFairy.findPic("haoyoumengdao.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(432, 117, 1134, 409, new String[]{"touqiao.png"});
                    if (result.sim > 0.8f) {
                        err = 0;
                        mFairy.onTap(0.8f, result, "分享链接", 2000);
                        return;
                    }

                    result = mFairy.findPic(410, 27, 1155, 704, "touqiao2.png");
                    if (result.sim > 0.8f && fen == 0) {
                        mFairy.onTap(0.8f, result, "世界分享", 5000);
                        fen = 1;
                        return;
                    }

                    result = mFairy.findPic(410, 27, 1155, 704, "touqiao3.png");
                    if (result.sim > 0.8f && fen == 1) {
                        mFairy.onTap(0.8f, result, "帮会分享", Sleep);
                        gameUtil.close(0);
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic(9, 5, 208, 124, new String[]{"touqiao1.png", "touqiao4.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "分享", 2000);
                    return;
                }
            }

        }.taskContent(mFairy, "分享空间中");
    }

    //关宁积分换宝图
    public void gnjfhbt() throws Exception {
        new OtherGame(mFairy) {

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(15, 2)) return;

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 59, 53, 74, 60, "点击头像", 3000);


                result = mFairy.findPic("jsjf.png");
                mFairy.onTap(0.8f, result, "切换到角色积分", 3000);

                result = mFairy.findPic("gnjf.png");
                mFairy.onTap(0.8f, result, "打开关宁积分界面", 3000);


                result = mFairy.findPic(107,53,841,613,"gnjfcbt.png");
                mFairy.onTap(0.8f, result, "关宁积分藏宝图", Sleep);
                mFairy.onTap(0.8f, result, 1106, 460, 1119, 473, "关宁积分藏宝图", 100);
                mFairy.onTap(0.8f, result, 1106, 460, 1119, 473, "关宁积分藏宝图", 100);
                mFairy.onTap(0.8f, result, 1106, 460, 1119, 473, "关宁积分藏宝图", 100);
                mFairy.onTap(0.8f, result, 1106, 460, 1119, 473, "关宁积分藏宝图", 100);
                mFairy.onTap(0.8f, result, 1106, 460, 1119, 473, "关宁积分藏宝图", 100);
                mFairy.onTap(0.8f, result, 985, 619, 1014, 633, "关宁积分藏宝图兑换", Sleep);

                result = mFairy.findPic("jfhbtsure.png");
                mFairy.onTap(0.8f, result, "关宁积分藏宝图确定", 2000);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                gameUtil.close(0);
                setTaskEnd();
            }
        }.taskContent(mFairy, "关宁积分换宝图中");
    }

    //喊话
    public void hanhua(final String string) throws Exception {
        new OtherGame(mFairy) {
            public void content_0() throws Exception {
                result = mFairy.findPic("chat.png");
                if (result.sim < 0.8f) {
                    gameUtil.close(0);
                }
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(10, 2)) return;

                result = mFairy.findPic("chat.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 36, 23, 50, 33, "聊天框切换到世界频道", 2000);
                    mFairy.onTap(0.8f, result, 183, 683, 205, 696, "打开输入框", 5000);
                }else{
                    result = mFairy.findPic("package.png");
                    mFairy.onTap(0.8f, result, 684, 689, 685, 690, "打开聊天框", Sleep);
                }

                FindResult resultchat = mFairy.findPic(1070,68,1274,719,new String[]{"new_textsure.png", "new_textsure1.png"});
                if(resultchat.sim>0.8f){
                    mFairy.inputText(string);
                    mFairy.condit();

                    Thread.sleep(2000);

                    mFairy.onTap(0.8f, resultchat, "确定文本", 3000);

                    mFairy.onTap(0.8f, resultchat, 460, 683, 484, 695, "发送", 1000);
                    mFairy.onTap(0.8f, resultchat, 460, 683, 484, 695, "发送", 1000);
                    mFairy.onTap(0.8f, resultchat, 460, 683, 484, 695, "发送", 1000);

                    mFairy.onTap(0.8f, resultchat, 556, 353, 570, 371, "收起聊天框", 3000);
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                for (int i = 0; i < 5; i++) {
                    FindResult resultchat = mFairy.findPic(1070,68,1274,719,new String[]{"new_textsure.png", "new_textsure1.png"});
                    if(resultchat.sim>0.8f){
                        setTaskName(1);
                        return;
                    }
                    Thread.sleep(1000);
                }
                gameUtil.close(0);
                setTaskEnd();
            }

        }.taskContent(mFairy, "喊话中");
    }

    //释放技能
    public void sfjn() throws Exception {
        new OtherGame(mFairy) {

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(5, 2)) return;
                result = mFairy.findPic("gj.png");
                mFairy.onTap(0.8f, result, 1225, 462, 1239, 473, "技能1", Sleep);
                result = mFairy.findPic("gj.png");
                mFairy.onTap(0.8f, result, 1108, 459, 1132, 477, "技能2", Sleep);
                result = mFairy.findPic("gj.png");
                mFairy.onTap(0.8f, result, 1025, 535, 1043, 548, "技能3", Sleep);
                result = mFairy.findPic("gj.png");
                mFairy.onTap(0.8f, result, 1019, 643, 1036, 661, "技能4", Sleep);
                result = mFairy.findPic("gj.png");
                mFairy.onTap(0.8f, result, 1147, 573, 1162, 587, "技能5", Sleep);
                result = mFairy.findPic("gj.png");
                mFairy.onTap(0.8f, result, 1238, 590, 1254, 600, "绝技1", Sleep);
                result = mFairy.findPic("gj.png");
                mFairy.onTap(0.8f, result, 1164, 664, 1175, 681, "绝技2", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                gameUtil.close(0);
                setTaskEnd();
            }

        }.taskContent(mFairy, "释放技能中");
    }

    //释放技能while
    public void sfjnWhile() throws Exception {
        new OtherGame(mFairy) {
            ControlSplit syyp = null;

            public void create() throws Exception {
                if (!AtFairyConfig.getOption("optime1").equals("")) {
                    syyp = strSplit(AtFairyConfig.getOption("optime1"));
                }
            }

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(30, 1)) return;
                if (syyp != null && syyp.choice == 1 && timekeep(0, syyp.timeMillis, "释放技能while使用蓝药")) {
                    eatLan();
                }

                result = mFairy.findPic("hbScence.png");
                mFairy.onTap(0.8f,result,1162,27,1183,49,"关闭红包界面",1000);

                result = mFairy.findPic(new String[]{"gj.png", "guanningwithin.png"});
                mFairy.onTap(0.8f, result, 1225, 462, 1239, 473, "技能1", Sleep);
                result = mFairy.findPic(new String[]{"gj.png", "guanningwithin.png"});
                mFairy.onTap(0.8f, result, 1108, 459, 1132, 477, "技能2", Sleep);
                result = mFairy.findPic(new String[]{"gj.png", "guanningwithin.png"});
                mFairy.onTap(0.8f, result, 1025, 535, 1043, 548, "技能3", Sleep);
                result = mFairy.findPic(new String[]{"gj.png", "guanningwithin.png"});
                mFairy.onTap(0.8f, result, 1019, 643, 1036, 661, "技能4", Sleep);
                result = mFairy.findPic(new String[]{"gj.png", "guanningwithin.png"});
                mFairy.onTap(0.8f, result, 1147, 573, 1162, 587, "技能5", Sleep);
                result = mFairy.findPic(new String[]{"gj.png", "guanningwithin.png"});
                mFairy.onTap(0.8f, result, 1238, 590, 1254, 600, "绝技1", Sleep);
                result = mFairy.findPic(new String[]{"gj.png", "guanningwithin.png"});
                mFairy.onTap(0.8f, result, 1164, 664, 1175, 681, "绝技2", Sleep);
                if (result.sim > 0.8f) {
                    err = 0;
                }
                result = mFairy.findPic("guanningwithin.png");
                if (picCount(0.8f, result, "关宁上高地") > 7) {
                    mFairy.onTap(1197, 84, 1198, 85, "点开地图上高地", 10);
                }

                result = mFairy.findPic(198, 39, 304, 120, "Mapinterface.png");
                mFairy.onTap(0.8f, result, 666, 343, 667, 344, "选择城市", 5000);
                if (result.sim > 0.8f) {
                    gameUtil.close(0);
                }
                result = mFairy.findPic("guanningstop.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("关宁结束了"));
                    if (AtFairyConfig.getOption("gnsb").equals("1")) {
                        mFairy.onTap(1078, 657, 1079, 658, "关宁领双", 2000);
                        mFairy.onTap(799, 425, 800, 426, "确定关宁领双", 2000);
                    }
                    gameUtil.close(0);
                }

                result = mFairy.findPic("guanningintask.png");
                mFairy.onTap(0.8f, result, 1090, 82, 1091, 83, "关宁任务", Sleep);

                result = mFairy.findPic(445, 134, 827, 579, "DefaultResurrection.png");
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("ydfh").equals("1")) {
                        result = mFairy.findPic(445, 134, 827, 579, "ydfh.png");
                        mFairy.onTap(0.8f, result, "原地复活", Sleep);
                    } else {
                        mFairy.onTap(0.8f, result, "默认复活", Sleep);
                    }
                }
                Thread.sleep(2000);
            }
        }.taskContent(mFairy, "释放技能while中");
    }

    //吃蓝药
    public void eatLan() throws Exception {
        new OtherGame(mFairy) {

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(5, 2)) return;
                result = mFairy.findPic("gj.png");
                if (result.sim > 0.8f) {
                    mFairy.touchDown(2, 927, 672);
                    mFairy.touchUp(2);
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                gameUtil.close(0);
                setTaskEnd();
            }
        }.taskContent(mFairy, "吃蓝药中");
    }

    //家园种菜
    static boolean homefood = true;
    public void homefood() throws Exception {
        homefood = false;
        new OtherGame(mFairy) {
            public void content_0() throws Exception {
                gameUtil.goCity("家园");
                setTaskName(1);
            }

            int gmx, gmy, num = 1, zw;

            public void content_1() throws Exception {


                result = mFairy.findPic(1018,161,1264,305,"jiayuan2.png");
                if(result.sim>0.8f){
                    result = mFairy.findPic(952,98,1167,191,"jiayuan1.png");
                    if(result.sim<0.8f){
                        setTaskName(0);
                        return;
                    }
                }


                LtLog.e(mFairy.getLineInfo("获取坐标菜地坐标中"));
                if (num == 1) {
                    if (AtFairyConfig.getOption("xystr1").equals("") || AtFairyConfig.getOption("zw1").equals("")) {
                        num = 2;
                    } else {
                        String[] arr = AtFairyConfig.getOption("xystr1").split(",");
                        gmx = Integer.parseInt(arr[0]);
                        gmy = Integer.parseInt(arr[1]);
                        zw = Integer.parseInt(AtFairyConfig.getOption("zw1"));
                        LtLog.e(mFairy.getLineInfo("苗圃1,坐标=" + gmx + "," + gmy));
                    }
                }

                if (num == 2) {
                    if (AtFairyConfig.getOption("xystr2").equals("") || AtFairyConfig.getOption("zw2").equals("")) {
                        num = 3;
                    } else {
                        String[] arr = AtFairyConfig.getOption("xystr2").split(",");
                        gmx = Integer.parseInt(arr[0]);
                        gmy = Integer.parseInt(arr[1]);
                        zw = Integer.parseInt(AtFairyConfig.getOption("zw2"));
                        LtLog.e(mFairy.getLineInfo("苗圃2,坐标=" + gmx + "," + gmy));
                    }
                }
                if (num == 3) {
                    if (AtFairyConfig.getOption("xystr3").equals("") || AtFairyConfig.getOption("zw3").equals("")) {
                        num = 4;
                    } else {
                        String[] arr = AtFairyConfig.getOption("xystr3").split(",");
                        gmx = Integer.parseInt(arr[0]);
                        gmy = Integer.parseInt(arr[1]);
                        zw = Integer.parseInt(AtFairyConfig.getOption("zw3"));
                        LtLog.e(mFairy.getLineInfo("苗圃3,坐标=" + gmx + "," + gmy));
                    }
                }

                if (num == 4) {
                    if (AtFairyConfig.getOption("xystr4").equals("") || AtFairyConfig.getOption("zw4").equals("")) {
                        num = 5;
                    } else {
                        String[] arr = AtFairyConfig.getOption("xystr4").split(",");
                        gmx = Integer.parseInt(arr[0]);
                        gmy = Integer.parseInt(arr[1]);
                        zw = Integer.parseInt(AtFairyConfig.getOption("zw4"));
                        LtLog.e(mFairy.getLineInfo("苗圃4,坐标=" + gmx + "," + gmy));
                    }
                }

                if (num == 5) {
                    if (AtFairyConfig.getOption("xystr5").equals("") || AtFairyConfig.getOption("zw5").equals("")) {
                        num = 6;
                    } else {
                        String[] arr = AtFairyConfig.getOption("xystr5").split(",");
                        gmx = Integer.parseInt(arr[0]);
                        gmy = Integer.parseInt(arr[1]);
                        zw = Integer.parseInt(AtFairyConfig.getOption("zw5"));
                        LtLog.e(mFairy.getLineInfo("苗圃5,坐标=" + gmx + "," + gmy));
                    }
                }

                if (num == 6) {
                    if (AtFairyConfig.getOption("xystr6").equals("") || AtFairyConfig.getOption("zw6").equals("")) {
                        num = 7;
                    } else {
                        String[] arr = AtFairyConfig.getOption("xystr6").split(",");
                        gmx = Integer.parseInt(arr[0]);
                        gmy = Integer.parseInt(arr[1]);
                        zw = Integer.parseInt(AtFairyConfig.getOption("zw6"));
                        LtLog.e(mFairy.getLineInfo("苗圃6,坐标=" + gmx + "," + gmy));
                    }
                }

                if (num >= 7) {
                    setTaskEnd();
                    return;
                }
                setTaskName(2);
            }

            public void content_2() throws Exception {
                num++;
                mFairy.onTap(1231,201,1245,211,"界面异常点击",1000);

                gameUtil.coordinate("家园", gmx, gmy);
                setTaskName(3);
            }

            public void content_3() throws Exception {
                if (overtime(20, 1)){
                    num--;
                    gameUtil.close(0);
                    return;
                }

                result = mFairy.findPic(760,27,1130,187,"jun.png");
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }


                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 590, 280, 680, 450, "点击苗圃", 2000);
                if (result.sim > 0.8f) {


                    result = mFairy.findPic(254, 29, 1102, 625, "guoqi.png");
                    mFairy.onTap(0.72f, result, result.x + 100, result.y + 10, result.x + 101, result.y + 11, "作物过期了", Sleep);
                    mFairy.onTap(0.72f, result, 775, 423, 802, 438, "作物过期了", Sleep);

                    result = mFairy.findPic(254, 29, 1102, 625, "species.png");
                    mFairy.onTap(0.72f, result, "种菜", Sleep);


                    result = mFairy.findPic(254, 29, 1102, 625, "Unripe.png");
                    if (result.sim > 0.72f) {
                        LtLog.e(mFairy.getLineInfo("没有熟"));
                        setTaskName(1);
                        return;
                    }


                    result = mFairy.findPic(254, 29, 1102, 625, "Harvest.png");
                    mFairy.onTap(0.72f, result, "收获", Sleep);
                    if (result.sim > 0.72f) {
                        result = mFairy.findPic(308, 499, 984, 564, "foodbagman.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("清理包裹"));
                            if (AtFairyConfig.getOption("jzc").equals("1")) {
                                homebag();
                            } else {
                                setTaskName(1);
                                return;
                            }
                        }
                    }
                }

                result = mFairy.findPic(new String[]{"speciesinterface.png", "jia1.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("种菜界面"));
                    if (zw <= 18) {
                        mFairy.onTap(193, 98, 194, 99, "蔬果", Sleep);
                        if (zw <= 5) {
                            mFairy.onTap(73 + (110 * zw), 210, 74 + (110 * zw), 211, "粮食", Sleep);
                        }
                        if (zw > 5 && zw <= 10) {
                            mFairy.onTap(73 + (110 * (zw - 5)), 210 + 140, 74 + (110 * (zw - 5)), 211 + 140, "粮食", Sleep);
                        }
                        if (zw > 10 && zw <= 15) {
                            mFairy.onTap(73 + (110 * (zw - 10)), 210 + 140 + 140, 74 + (110 * (zw - 10)), 211 + 140 + 140, "粮食", Sleep);

                        }
                        if (zw > 15 && zw <= 18) {
                            mFairy.onTap(73 + (110 * (zw - 15)), 210 + 140 + 140 + 140, 74 + (110 * (zw - 15)), 211 + 140 + 140 + 140, "粮食", Sleep);
                        }
                    }
                    if (zw > 18 && zw <= 36) {
                        mFairy.onTap(338, 98, 339, 99, "树木", Sleep);
                        if ((zw - 18) <= 5) {
                            mFairy.onTap(73 + (110 * (zw - 18)), 210, 74 + (110 * (zw - 18)), 211, "木材", Sleep);
                        }
                        if ((zw - 18) > 5 && (zw - 18) <= 10) {
                            mFairy.onTap(73 + (110 * (zw - 18 - 5)), 210 + 140, 74 + (110 * (zw - 18 - 5)), 211 + 140, "木材", Sleep);

                        }
                        if ((zw - 18) > 10 && (zw - 18) <= 15) {
                            mFairy.onTap(73 + (110 * (zw - 18 - 10)), 210 + 140 + 140, 74 + (110 * (zw - 18 - 10)), 211 + 140 + 140, "木材", Sleep);
                        }
                        if ((zw - 18) > 15 && (zw - 18) <= 18) {
                            mFairy.onTap(73 + (110 * (zw - 18 - 15)), 210 + 140 + 140 + 140, 74 + (110 * (zw - 18 - 15)), 211 + 140 + 140 + 140, "木材", Sleep);
                        }
                    }
                    if (zw > 36) {
                        mFairy.onTap(484, 98, 485, 99, "花卉", Sleep);
                        if ((zw - 36) <= 5) {
                            mFairy.onTap(73 + (110 * (zw - 36)), 210, 74 + (110 * (zw - 36)), 211, "石材", Sleep);
                        }
                        if ((zw - 36) > 5 && (zw - 36) <= 10) {
                            mFairy.onTap(73 + (110 * (zw - 36 - 5)), 210 + 140, 74 + (110 * (zw - 36 - 5)), 211 + 140, "石材", Sleep);
                        }
                        if ((zw - 36) > 10 && (zw - 36) <= 15) {
                            mFairy.onTap(73 + (110 * (zw - 36 - 10)), 210 + 140 + 140, 74 + (110 * (zw - 36 - 10)), 211 + 140 + 140, "石材", Sleep);
                        }
                        if ((zw - 36) > 15 && (zw - 36) <= 20) {
                            mFairy.onTap(73 + (110 * (zw - 36 - 15)), 210 + 140 + 140 + 140, 74 + (110 * (zw - 36 - 15)), 211 + 140 + 140 + 140, "石材", Sleep);
                        }
                        if (zw > 56) {
                            mFairy.ranSwipe(252, 563, 252, 205, 500, 500);
                            mFairy.ranSwipe(252, 563, 252, 205, 500, 500);
                            mFairy.ranSwipe(252, 563, 252, 205, 500, 500);
                        }
                        if ((zw - 36) > 20 && (zw - 36) <= 25) {
                            mFairy.onTap(73 + (110 * (zw - 36 - 20)), 310 + 140, 74 + (110 * (zw - 36 - 20)), 311 + 140, "石材", Sleep);
                        }
                        if ((zw - 36) > 25 && (zw - 36) <= 30) {
                            mFairy.onTap(73 + (110 * (zw - 36 - 25)), 310 + 140 + 140, 74 + (110 * (zw - 36 - 25)), 311 + 140 + 140, "石材", Sleep);
                        }
                    }
                    mFairy.onTap(0.8f, result, 915, 639, 973, 655, "种植", 2000);
                    result = mFairy.findPic(new String[]{"speciesinterface.png", "jia1.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(1178,57,1192,71,"",2000);
                        LtLog.e(mFairy.getLineInfo("此作物不能种植下一个"));
                        setTaskName(1);
                        return;
                    }
                }
            }
        }.taskContent(mFairy, "家园种菜中");
        homefood = true;
    }

    //家园种菜检测
    public void homefoodjc() throws Exception {
        new OtherGame(mFairy) {

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(10, 99)) return;

                result = mFairy.findPic(806, 597, 1275, 710, "qhhome.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "家园", 2000);
                } else {
                    result = mFairy.findPic("package.png");
                    mFairy.onTap(0.8f, result, 1239, 206, 1240, 207, "切换栏", Sleep);
                }

                result = mFairy.findPic("jia2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 1152, 154, 1174, 192, "家园界面", 1000);

                    result = mFairy.findPic(1071, 147, 1127, 550, "havefood.png");
                    if (result.sim > 0.9f) {
                        homefood();
                        if (AtFairyConfig.getOption("jzc").equals("1")) {
                            homebag();
                        }
                    }
                    gameUtil.close(0);
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy, "家园种菜检测中");
    }

    public void newhomefood() throws Exception {
        homefood = false;
        new OtherGame(mFairy) {
            public void content_0() throws Exception {
                //gameUtil.close(0);
                gameUtil.goCity("家园");


                setTaskName(1);
            }

            int gmx, gmy, num = 1, zw;

            public void content_1() throws Exception {

                result = mFairy.findPic(1018,161,1264,305,"jiayuan2.png");
                if(result.sim>0.8f){
                    result = mFairy.findPic(952,98,1167,191,"jiayuan1.png");
                    if(result.sim<0.8f){
                        setTaskName(0);
                        return;
                    }
                }



                LtLog.e(mFairy.getLineInfo("获取坐标菜地坐标中"));
                if (num == 1) {
                    if (AtFairyConfig.getOption("xystr1").equals("") || AtFairyConfig.getOption("zw1").equals("")) {
                        num = 2;
                    } else {
                        String[] arr = AtFairyConfig.getOption("xystr1").split(",");
                        gmx = Integer.parseInt(arr[0]);
                        gmy = Integer.parseInt(arr[1]);
                        zw = Integer.parseInt(AtFairyConfig.getOption("zw1"));
                        LtLog.e(mFairy.getLineInfo("苗圃1,坐标=" + gmx + "," + gmy));
                    }
                }
                if (num == 2) {
                    if (AtFairyConfig.getOption("xystr2").equals("") || AtFairyConfig.getOption("zw2").equals("")) {
                        num = 3;
                    } else {
                        String[] arr = AtFairyConfig.getOption("xystr2").split(",");
                        gmx = Integer.parseInt(arr[0]);
                        gmy = Integer.parseInt(arr[1]);
                        zw = Integer.parseInt(AtFairyConfig.getOption("zw2"));
                        LtLog.e(mFairy.getLineInfo("苗圃2,坐标=" + gmx + "," + gmy));
                    }
                }
                if (num == 3) {
                    if (AtFairyConfig.getOption("xystr3").equals("") || AtFairyConfig.getOption("zw3").equals("")) {
                        num = 4;
                    } else {
                        String[] arr = AtFairyConfig.getOption("xystr3").split(",");
                        gmx = Integer.parseInt(arr[0]);
                        gmy = Integer.parseInt(arr[1]);
                        zw = Integer.parseInt(AtFairyConfig.getOption("zw3"));
                        LtLog.e(mFairy.getLineInfo("苗圃3,坐标=" + gmx + "," + gmy));
                    }
                }
                if (num == 4) {
                    if (AtFairyConfig.getOption("xystr4").equals("") || AtFairyConfig.getOption("zw4").equals("")) {
                        num = 5;
                    } else {
                        String[] arr = AtFairyConfig.getOption("xystr4").split(",");
                        gmx = Integer.parseInt(arr[0]);
                        gmy = Integer.parseInt(arr[1]);
                        zw = Integer.parseInt(AtFairyConfig.getOption("zw4"));
                        LtLog.e(mFairy.getLineInfo("苗圃4,坐标=" + gmx + "," + gmy));
                    }
                }

                if (num == 5) {
                    if (AtFairyConfig.getOption("xystr5").equals("") || AtFairyConfig.getOption("zw5").equals("")) {
                        num = 6;
                    } else {
                        String[] arr = AtFairyConfig.getOption("xystr5").split(",");
                        gmx = Integer.parseInt(arr[0]);
                        gmy = Integer.parseInt(arr[1]);
                        zw = Integer.parseInt(AtFairyConfig.getOption("zw5"));
                        LtLog.e(mFairy.getLineInfo("苗圃5,坐标=" + gmx + "," + gmy));
                    }
                }
                if (num == 6) {
                    if (AtFairyConfig.getOption("xystr6").equals("") || AtFairyConfig.getOption("zw6").equals("")) {
                        num = 7;
                    } else {
                        String[] arr = AtFairyConfig.getOption("xystr6").split(",");
                        gmx = Integer.parseInt(arr[0]);
                        gmy = Integer.parseInt(arr[1]);
                        zw = Integer.parseInt(AtFairyConfig.getOption("zw6"));
                        LtLog.e(mFairy.getLineInfo("苗圃6,坐标=" + gmx + "," + gmy));
                    }
                }
                if (num >= 7) {
                    setTaskEnd();
                    return;
                }
                setTaskName(2);
            }

            public void content_2() throws Exception {
                num++;

                mFairy.onTap(1231,201,1245,211,"界面异常点击",1000);

                gameUtil.coordinate1(gmx, gmy);
                setTaskName(3);
            }

            public void content_3() throws Exception {
                if (overtime(10, 1)){
                    num--;
                    gameUtil.close(0);
                    return;
                }

                result = mFairy.findPic(760,27,1130,187,"jun.png");
                if (result.sim > 0.8f) {
                    setTaskName(0);
                    return;
                }


                result = mFairy.findPic("package.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 609, 306, 670, 401, "点击苗圃", 1500);

                    result = mFairy.findPic(254, 29, 1102, 625, "guoqi.png");
                    mFairy.onTap(0.72f, result, result.x + 100, result.y + 10, result.x + 101, result.y + 11, "作物过期了", Sleep);
                    mFairy.onTap(0.72f, result, 775, 423, 802, 438, "作物过期了", Sleep);

                    result = mFairy.findPic(254, 29, 1102, 625, "species.png");
                    mFairy.onTap(0.72f, result, "种菜", Sleep);

                    result = mFairy.findPic(254, 29, 1102, 625, "Unripe.png");
                    if (result.sim > 0.72f) {
                        LtLog.e(mFairy.getLineInfo("没有熟"));
                        setTaskName(1);
                        return;
                    }

                    result = mFairy.findPic(254, 29, 1102, 625, "Harvest.png");
                    if (result.sim > 0.72f) {
                        mFairy.onTap(0.72f, result, "收获", Sleep);

                        result = mFairy.findPic(308, 499, 984, 564, "foodbagman.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("清理包裹"));
                            if (AtFairyConfig.getOption("jzc").equals("1")) {
                                homebag();
                            } else {
                                setTaskName(1);
                                return;
                            }
                        }
                    }
                }


                result = mFairy.findPic("jia1.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("种菜界面"));

                    if (zw <= 18) {
                        mFairy.onTap(193, 98, 194, 99, "蔬果", 1000);
                        if (zw <= 5) {
                            mFairy.onTap(73 + (110 * zw), 210, 74 + (110 * zw), 211, "粮食", 500);
                        }
                        if (zw > 5 && zw <= 10) {
                            mFairy.onTap(73 + (110 * (zw - 5)), 210 + 140, 74 + (110 * (zw - 5)), 211 + 140, "粮食", 500);
                        }
                        if (zw > 10 && zw <= 15) {
                            mFairy.onTap(73 + (110 * (zw - 10)), 210 + 140 + 140, 74 + (110 * (zw - 10)), 211 + 140 + 140, "粮食", 500);

                        }
                        if (zw > 15 && zw <= 18) {
                            mFairy.onTap(73 + (110 * (zw - 15)), 210 + 140 + 140 + 140, 74 + (110 * (zw - 15)), 211 + 140 + 140 + 140, "粮食", 500);
                        }
                    }
                    if (zw > 18 && zw <= 36) {
                        mFairy.onTap(338, 98, 339, 99, "树木", 1000);
                        if ((zw - 18) <= 5) {
                            mFairy.onTap(73 + (110 * (zw - 18)), 210, 74 + (110 * (zw - 18)), 211, "木材", 500);
                        }
                        if ((zw - 18) > 5 && (zw - 18) <= 10) {
                            mFairy.onTap(73 + (110 * (zw - 18 - 5)), 210 + 140, 74 + (110 * (zw - 18 - 5)), 211 + 140, "木材", 500);

                        }
                        if ((zw - 18) > 10 && (zw - 18) <= 15) {
                            mFairy.onTap(73 + (110 * (zw - 18 - 10)), 210 + 140 + 140, 74 + (110 * (zw - 18 - 10)), 211 + 140 + 140, "木材", 500);
                        }
                        if ((zw - 18) > 15 && (zw - 18) <= 18) {
                            mFairy.onTap(73 + (110 * (zw - 18 - 15)), 210 + 140 + 140 + 140, 74 + (110 * (zw - 18 - 15)), 211 + 140 + 140 + 140, "木材", 500);
                        }
                    }
                    if (zw > 36) {
                        mFairy.onTap(484, 98, 485, 99, "花卉", 1000);
                        if ((zw - 36) <= 5) {
                            mFairy.onTap(73 + (110 * (zw - 36)), 210, 74 + (110 * (zw - 36)), 211, "石材", 500);
                        }
                        if ((zw - 36) > 5 && (zw - 36) <= 10) {
                            mFairy.onTap(73 + (110 * (zw - 36 - 5)), 210 + 140, 74 + (110 * (zw - 36 - 5)), 211 + 140, "石材", 500);
                        }
                        if ((zw - 36) > 10 && (zw - 36) <= 15) {
                            mFairy.onTap(73 + (110 * (zw - 36 - 10)), 210 + 140 + 140, 74 + (110 * (zw - 36 - 10)), 211 + 140 + 140, "石材", 500);
                        }
                        if ((zw - 36) > 15 && (zw - 36) <= 20) {
                            mFairy.onTap(73 + (110 * (zw - 36 - 15)), 210 + 140 + 140 + 140, 74 + (110 * (zw - 36 - 15)), 211 + 140 + 140 + 140, "石材", 500);
                        }
                        if (zw > 56) {
                            mFairy.ranSwipe(252, 563, 252, 205, 500, 500);
                            mFairy.ranSwipe(252, 563, 252, 205, 500, 500);
                            mFairy.ranSwipe(252, 563, 252, 205, 500, 500);
                        }
                        if ((zw - 36) > 20 && (zw - 36) <= 25) {
                            mFairy.onTap(73 + (110 * (zw - 36 - 20)), 310 + 140, 74 + (110 * (zw - 36 - 20)), 311 + 140, "石材", 500);
                        }
                        if ((zw - 36) > 25 && (zw - 36) <= 30) {
                            mFairy.onTap(73 + (110 * (zw - 36 - 25)), 310 + 140 + 140, 74 + (110 * (zw - 36 - 25)), 311 + 140 + 140, "石材", 5000);
                        }
                    }

                    mFairy.onTap(0.8f, result, 915, 639, 973, 655, "种植", 2000);

                    result = mFairy.findPic("jia1.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("此作物不能种植下一个"));
                        setTaskName(1);
                        return;
                    }
                }
            }
        }.taskContent(mFairy, "家园种菜中");
        homefood = true;
    }

    ControlSplit zdhh=null;
    long hb_time;
    public void newhomefoodjc() throws Exception {
        new OtherGame(mFairy) {

            public void create() throws Exception {
                super.create();
                if (!AtFairyConfig.getOption("hh_time").equals("")) {
                    zdhh = strSplit(AtFairyConfig.getOption("hh_time"));
                }
                hb_time= System.currentTimeMillis()-300001;
            }

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {

                result = mFairy.findPic(806, 597, 1275, 710, "qhhome.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "家园", 2000);
                } else {
                    result = mFairy.findPic("package.png");
                    mFairy.onTap(0.8f, result, 1239, 206, 1240, 207, "切换栏", Sleep);
                }

                result = mFairy.findPic("jia2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(1071, 147, 1127, 550, "havefood.png");
                    if (result.sim > 0.9f) {
                        mFairy.onTap(1175, 53, 1190, 68, "", 500);
                        newhomefood();
                        if (AtFairyConfig.getOption("jzc").equals("1")) {
                            homebag();
                        }
                        gameUtil.close(0);
                    } else {

                        if (AtFairyConfig.getOption("qianghb").equals("1")) {
                            grabRed(10);
                            setTaskName(0);
                            return;
                        }

                       /* if(System.currentTimeMillis()- hb_time >300000) {

                            hb_time = System.currentTimeMillis();
                        }
*/
                        if (zdhh != null && zdhh.choice == 1 && timekeep(1, zdhh.timeMillis, "喊话")) {
                            if (!AtFairyConfig.getOption("hanhua").equals("")) {
                                hanhua(AtFairyConfig.getOption("hanhua"));
                                setTaskName(0);
                                return;
                            }
                        }
                    }
                    return;
                }
                overtime(10, 0);
            }

        }.taskContent(mFairy, "家园种菜检测中");
    }


    //家园种菜 !!!!!!!!!!!!!!!!
    public void homefoodWile() throws Exception {
        ControlSplit zcjc = null;
       /* if (!AtFairyConfig.getOption("optime1").equals("")) {
            zcsc = strSplit(AtFairyConfig.getOption("optime1"));
        }*/
        if (!AtFairyConfig.getOption("optime2").equals("")) {
            zcjc = strSplit(AtFairyConfig.getOption("optime2"));
        }
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("【收菜种菜检测的时间：" + zcjc.h + ":" + zcjc.m + ":" + zcjc.s + "】"));

            LtLog.e(mFairy.getLineInfo(timeKeepMap.containsKey("收菜种菜检测") ?
                    "【开始收菜检测倒计时 : " + ((zcjc.timeMillis - (System.currentTimeMillis() - timeKeepMap.get("收菜种菜检测"))) / 1000) + "】" :
                    "why?"));

            if (zcjc != null && zcjc.choice == 1 && timekeep(1, zcjc.timeMillis, "收菜种菜检测")) {
                homefoodjc();
            }

            Thread.sleep(5000);
        }
    }

    //家园清包
    public void homebag() throws Exception {
        homefood = false;
        new OtherGame(mFairy) {

            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, "包裹", 10000);

                result = mFairy.findPic("recovery.png");
                mFairy.onTap(0.8f, result, "回收", Sleep);

                result = mFairy.findPic("recovery1.png");
                mFairy.onTap(0.75f, result, 795, 95, 808, 103, "选择蓝装以下", Sleep);
                mFairy.onTap(0.75f, result, "回收", 2000);
                if (result.sim >= 0.75f) {
                    result1 = mFairy.findPic("Recoveryfailure.png");
                    mFairy.onTap(0.8f, result1, 640, 423, 641, 424, "回收失败", Sleep);
                    mFairy.onTap(0.8f, result, 680, 92, 718, 107, "返回", Sleep);
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, "包裹", 3000);

                result = mFairy.findPic("recovery.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面"));
                    mFairy.taskSlid(err, new int[]{0, 2, 3, 4}, 0, 922, 400, 922, 172, 500, 2000);
                }

                result = mFairy.findPic(639, 152, 1116, 630, new String[]{"bagfood.png"});
                mFairy.onTap(0.7f, result, "包裹里的菜", 2000);

                result = mFairy.findPic(93, 21, 1235, 703, "fooddh.png");
                mFairy.onTap(0.8f, result, "兑换资材", Sleep);

                result = mFairy.findPic("foodbagdh.png");
                mFairy.onTap(0.8f, result, "整包兑换", Sleep);

                result = mFairy.findPic("fooddhsure.png");
                mFairy.onTap(0.8f, result, "确定", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
                if (overtime(6, 3)) return;
            }

            public void content_3() throws Exception {
                gameUtil.close(0);
                setTaskEnd();
                return;
            }

        }.taskContent(mFairy, "家园清包中");
        homefood = true;
    }

    //吃定魂
    public void dinghun() throws Exception {
        new OtherGame(mFairy) {


            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, "包裹", 3000);

                result = mFairy.findPic("recovery.png");
                mFairy.onTap(0.8f, result, "回收", Sleep);

                result = mFairy.findPic("recovery1.png");
                mFairy.onTap(0.8f, result, 795, 95, 808, 103, "选择蓝装以下", Sleep);
                mFairy.onTap(0.8f, result, "回收", 2000);
                if (result.sim > 0.8f) {
                    result1 = mFairy.findPic("Recoveryfailure.png");
                    mFairy.onTap(0.8f, result1, 640, 423, 641, 424, "回收失败", Sleep);
                    mFairy.onTap(0.8f, result, 680, 92, 718, 107, "返回", Sleep);
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, "包裹", 3000);

                result = mFairy.findPic("recovery.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("包裹界面"));
                    mFairy.taskSlid(err, new int[]{0, 2, 3, 4}, 0, 922, 593, 922, 172, 500, 2000);
                }

                result = mFairy.findPic(639, 152, 1116, 630, new String[]{"dinghun.png"});
                mFairy.onTap(0.8f, result, "定魂", 2000);
                for (int i = 0; i < 10; i++) {
                    mFairy.condit();
                    result = mFairy.findPic(320, 117, 775, 533, "Useprops.png");
                    mFairy.onTap(0.85f, result, "使用道具", Sleep);
                    if (result.sim < 0.8f) {
                        break;
                    }
                }

                result = mFairy.findPic("qbfs.png");
                mFairy.onTap(0.8f, result, 624, 225, 670, 249, "发送", Sleep);
                mFairy.onTap(0.8f, result, 785, 416, 819, 433, "发送", Sleep);
                mFairy.onTap(0.8f, result, "发送", Sleep);

                for (int i = 0; i < 10; i++) {
                    mFairy.condit();
                    result = mFairy.findPic("use1.png");
                    mFairy.onTap(0.8f, result, "使用经验道具", Sleep);
                    if (result.sim < 0.8f) {
                        break;
                    }
                }
                result1 = mFairy.findPic(452, 495, 830, 573, "Coverup.png");
                if (result1.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("背包满,跳过任务"));
                    setTaskName(3);
                    return;
                }
                result = mFairy.findPic("mapsure.png");
                mFairy.onTap(0.8f, result, "确定", Sleep);
                if (result.sim > 0.8f) {
                    setTaskName(3);
                    return;
                }
                if (overtime(6, 3)) return;
            }

            public void content_3() throws Exception {
                gameUtil.close(0);
                setTaskEnd();
                return;
            }

        }.taskContent(mFairy, "吃定魂中");
    }

    //采集
    public void collection() throws Exception {
        new OtherGame(mFairy) {
            int jncj = 0, num = 0;
            ControlSplit controlSplit = new ControlSplit();

            public void create() throws Exception {
                if (AtFairyConfig.getOption("jncj").equals("") || AtFairyConfig.getOption("jncj").equals("0")) {
                    setTaskEnd();
                    return;
                }
                jncj = Integer.parseInt(AtFairyConfig.getOption("jncj"));
                if (AtFairyConfig.getOption("opcount2").equals("")) {
                    setTaskEnd();
                    return;
                } else {
                    controlSplit = strSplit(AtFairyConfig.getOption("opcount2"));
                    if (controlSplit.choice == 1) {
                        num = controlSplit.count;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
            }

            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;

                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1239, 206, 1240, 207, "切换栏", Sleep);

                result = mFairy.findPic(1164, 242, 1273, 699, "jineng.png");
                mFairy.onTap(0.8f, result, "技能按钮", Sleep);

                result = mFairy.findPic("Skillinterface.png");
                mFairy.onTap(0.8f, result, 1164, 460, 1165, 461, "生活技能", 3000);
                mFairy.onTap(0.8f, result, 1164, 460, 1165, 461, "生活技能", 7000);
                if (result.sim > 0.8f) {
                    if (jncj <= 16) {
                        mFairy.onTap(0.8f, result, 187, 136, 188, 137, "采集", 2000);
                    } else {
                        mFairy.onTap(0.8f, result, 324, 138, 325, 139, "钓鱼", 2000);
                    }

                    if (jncj == 15 || jncj == 16) {
                        mFairy.ranSwipe(511, 594, 486, 165, 1000, (long) 1000, 2);
                        mFairy.ranSwipe(511, 594, 486, 165, 1000, (long) 1000, 2);
                    }

                    if (jncj == 1) {
                        mFairy.onTap(464, 181, 465, 182, "密蒙", Sleep);
                        mFairy.onTap(464, 181, 465, 182, "密蒙", Sleep);
                    }

                    if (jncj == 2) {
                        mFairy.onTap(555, 177, 556, 178, "贝母", Sleep);
                        mFairy.onTap(555, 177, 556, 178, "贝母", Sleep);
                    }

                    if (jncj == 3) {
                        mFairy.onTap(646, 180, 647, 181, "三七", Sleep);
                        mFairy.onTap(646, 180, 647, 181, "三七", Sleep);
                    }
                    if (jncj == 4) {
                        mFairy.onTap(469, 265, 470, 266, "当归", Sleep);
                        mFairy.onTap(469, 265, 470, 266, "当归", Sleep);
                    }
                    if (jncj == 5) {
                        mFairy.onTap(565, 266, 566, 267, "红花", Sleep);
                        mFairy.onTap(565, 266, 566, 267, "红花", Sleep);
                    }
                    if (jncj == 6) {
                        mFairy.onTap(650, 268, 651, 269, "黄芩", Sleep);
                        mFairy.onTap(650, 268, 651, 269, "黄芩", Sleep);
                    }
                    if (jncj == 7) {
                        mFairy.onTap(462, 357, 463, 358, "赤芍", Sleep);
                        mFairy.onTap(462, 357, 463, 358, "赤芍", Sleep);
                    }
                    if (jncj == 8) {
                        mFairy.onTap(567, 357, 568, 358, "凌霄", Sleep);
                        mFairy.onTap(567, 357, 568, 358, "凌霄", Sleep);
                    }
                    if (jncj == 9) {
                        mFairy.onTap(452, 514, 474, 531, "青椒", Sleep);
                        mFairy.onTap(452, 514, 474, 531, "青椒", Sleep);
                    }
                    if (jncj == 10) {
                        mFairy.onTap(544, 519, 568, 537, "土豆", Sleep);
                        mFairy.onTap(544, 519, 568, 537, "土豆", Sleep);

                    }
                    if (jncj == 11) {
                        mFairy.onTap(643, 525, 661, 538, "黄豆", Sleep);
                        mFairy.onTap(643, 525, 661, 538, "黄豆", Sleep);
                    }
                    if (jncj == 12) {
                        mFairy.onTap(444, 603, 467, 616, "竹笋", Sleep);
                        mFairy.onTap(444, 603, 467, 616, "竹笋", Sleep);
                    }
                    if (jncj == 13) {
                        mFairy.onTap(541, 611, 564, 622, "青菜", Sleep);
                        mFairy.onTap(541, 611, 564, 622, "青菜", Sleep);
                    }
                    if (jncj == 14) {
                        mFairy.onTap(624, 605, 652, 617, "糯米", Sleep);
                        mFairy.onTap(624, 605, 652, 617, "糯米", Sleep);
                    }
                    if (jncj == 15) {
                        mFairy.onTap(455, 594, 478, 608, "花生", Sleep);
                        mFairy.onTap(455, 594, 478, 608, "花生", Sleep);
                    }
                    if (jncj == 16) {
                        mFairy.onTap(542, 599, 567, 613, "冬瓜", Sleep);
                        mFairy.onTap(542, 599, 567, 613, "冬瓜", Sleep);
                    }
                    if (jncj == 17) {
                        mFairy.onTap(464, 181, 465, 182, "鲫鱼", Sleep);
                        mFairy.onTap(464, 181, 465, 182, "鲫鱼", Sleep);
                    }
                    if (jncj == 18) {
                        mFairy.onTap(555, 177, 556, 178, "白虾", Sleep);
                        mFairy.onTap(555, 177, 556, 178, "白虾", Sleep);
                    }
                    if (jncj == 19) {

                        mFairy.onTap(646, 180, 647, 181, "大鲵", Sleep);
                        mFairy.onTap(646, 180, 647, 181, "大鲵", Sleep);
                    }
                    if (jncj == 20) {
                        mFairy.onTap(469, 265, 470, 266, "鲈鱼", Sleep);
                        mFairy.onTap(469, 265, 470, 266, "鲈鱼", Sleep);
                    }
                    if (jncj == 21) {
                        mFairy.onTap(565, 266, 566, 267, "紫蟹", Sleep);
                        mFairy.onTap(565, 266, 566, 267, "紫蟹", Sleep);
                    }
                    if (jncj == 22) {
                        mFairy.onTap(650, 268, 651, 269, "八代鱼", Sleep);
                        mFairy.onTap(650, 268, 651, 269, "八代鱼", Sleep);
                    }
                    if (jncj == 23) {
                        mFairy.onTap(462, 357, 463, 358, "鳜鱼", Sleep);
                        mFairy.onTap(462, 357, 463, 358, "鳜鱼", Sleep);
                    }
                    if (jncj == 24) {
                        mFairy.onTap(567, 357, 568, 358, "乌龟", Sleep);
                        mFairy.onTap(567, 357, 568, 358, "乌龟", Sleep);
                    }

                    mFairy.onTap(905, 606, 933, 619, "采集", 3000);
                    result = mFairy.findPic("Number.png");
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < num; i++) {
                            mFairy.tap(727, 364);
                            //  mFairy.onTap(0.8f, result, "加号", 1);
                        }
                        mFairy.onTap(752, 456, 788, 473, "采集", Sleep);
                        gameUtil.close(0);
                        setTaskName(2);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }

            }

            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(1144, 30, 55, 19, 0.9f);
                if (dazeTime > 4) {
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    for (int i = 0; i < 6; i++) {
                        mFairy.condit();
                        result = mFairy.findPic("Inprocess.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("正在采集中"));
                            setTaskName(3);
                            return;
                        }

                        Thread.sleep(2000);
                    }

                    result = mFairy.findPic("start.png");
                    mFairy.onTap(0.8f, result, "开始", Sleep);

                    if (result.sim > 0.8f) {
                        setTaskName(3);
                        return;
                    } else {
                        setTaskName(0);
                        return;
                    }
                }

                Thread.sleep(2000);
            }

            public void content_3() throws Exception {
                if (overtime(6, 99)) return;
                result = mFairy.findPic("Inprocess.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("正在采集中"));
                    err = 0;
                }
                Thread.sleep(2000);
            }
        }.taskContent(mFairy, "采集中");
    }

    //生产半成品
    public void collection1() throws Exception {
        new OtherGame(mFairy) {
            int jnbcp = 0;

            public void create() throws Exception {
                if (AtFairyConfig.getOption("jnbcp").equals("") || AtFairyConfig.getOption("jnbcp").equals("0")) {
                    setTaskEnd();
                    return;
                }
                jnbcp = Integer.parseInt(AtFairyConfig.getOption("jnbcp"));
            }

            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1239, 206, 1240, 207, "切换栏", Sleep);

                result = mFairy.findPic(1164, 242, 1273, 699, "jineng.png");
                mFairy.onTap(0.8f, result, "技能按钮", Sleep);

                result = mFairy.findPic("Skillinterface.png");
                mFairy.onTap(0.8f, result, 1164, 460, 1165, 461, "生活技能", 2000);
                mFairy.onTap(0.8f, result, 1164, 460, 1165, 461, "生活技能", 2000);
                if (result.sim > 0.8f) {
                    if (jnbcp <= 8) {
                        mFairy.onTap(0.8f, result, 326, 285, 327, 286, "烹饪", 2000);
                    } else {
                        mFairy.onTap(0.8f, result, 190, 285, 191, 286, "炼药", 2000);
                    }
                  /*  for (int i = 0; i < 5; i++) {
                        result = mFairy.findPic(592, 73, 689, 688, "Takeup.png");
                        mFairy.onTap(0.9f, result,  "收起", Sleep);
                    }*/
                    if (jnbcp == 1) {
                        mFairy.onTap(464, 181, 465, 182, "初级净菜", Sleep);
                        mFairy.onTap(464, 181, 465, 182, "初级净菜", Sleep);
                    }
                    if (jnbcp == 2) {
                        mFairy.onTap(555, 177, 556, 178, "初级肉品", Sleep);
                        mFairy.onTap(555, 177, 556, 178, "初级肉品", Sleep);
                    }
                    if (jnbcp == 3) {
                        mFairy.onTap(646, 180, 647, 181, "中级净菜", Sleep);
                        mFairy.onTap(646, 180, 647, 181, "中级净菜", Sleep);
                    }
                    if (jnbcp == 4) {
                        mFairy.onTap(469, 265, 470, 266, "中级肉品", Sleep);
                        mFairy.onTap(469, 265, 470, 266, "中级肉品", Sleep);
                    }
                    if (jnbcp == 5) {
                        mFairy.onTap(565, 266, 566, 267, "高级净菜", Sleep);
                        mFairy.onTap(565, 266, 566, 267, "高级净菜", Sleep);
                    }
                    if (jnbcp == 6) {
                        mFairy.onTap(650, 268, 651, 269, "高级肉品", Sleep);
                        mFairy.onTap(650, 268, 651, 269, "高级肉品", Sleep);
                    }
                    if (jnbcp == 7) {
                        mFairy.onTap(462, 357, 463, 358, "终级净菜", Sleep);
                        mFairy.onTap(462, 357, 463, 358, "终级净菜", Sleep);
                    }
                    if (jnbcp == 8) {
                        mFairy.onTap(567, 357, 568, 358, "终级肉品", Sleep);
                        mFairy.onTap(567, 357, 568, 358, "终级肉品", Sleep);
                    }
                    if (jnbcp == 9) {
                        mFairy.onTap(464, 496, 465, 497, "初级药材", Sleep);
                        mFairy.onTap(464, 496, 465, 497, "初级药材", Sleep);
                    }
                    if (jnbcp == 10) {
                        mFairy.onTap(555, 501, 556, 502, "中级药材", Sleep);
                        mFairy.onTap(555, 501, 556, 502, "中级药材", Sleep);
                    }
                    if (jnbcp == 11) {
                        mFairy.onTap(646, 500, 647, 501, "高级药材", Sleep);
                        mFairy.onTap(646, 500, 647, 501, "高级药材", Sleep);
                    }
                    if (jnbcp == 12) {
                        mFairy.onTap(469, 595, 470, 596, "终级药材", Sleep);
                        mFairy.onTap(469, 595, 470, 596, "终级药材", Sleep);
                    }
                    mFairy.onTap(897, 606, 938, 619, "开始烹饪", Sleep);
                    setTaskEnd();
                    return;
                }

            }
        }.taskContent(mFairy, "生产半成品中");
    }

    //烹饪
    public void collection2() throws Exception {
        new OtherGame(mFairy) {
            int jnpr = 0, numpr = 0;
            ControlSplit controlSplit = new ControlSplit();

            public void create() throws Exception {
                if (AtFairyConfig.getOption("jnpr").equals("") || AtFairyConfig.getOption("jnpr").equals("0")) {
                    setTaskEnd();
                    return;
                }
                if (AtFairyConfig.getOption("opcount3").equals("")) {
                    setTaskEnd();
                    return;
                } else {
                    controlSplit = strSplit(AtFairyConfig.getOption("opcount3"));
                    if (controlSplit.choice == 1) {
                        numpr = controlSplit.count;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                jnpr = Integer.parseInt(AtFairyConfig.getOption("jnpr"));
            }

            public void content_0() throws Exception {
                gameUtil.close(1);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 1239, 206, 1240, 207, "切换栏", Sleep);

                result = mFairy.findPic(1164, 242, 1273, 699, "jineng.png");
                mFairy.onTap(0.8f, result, "技能按钮", Sleep);

                result = mFairy.findPic("Skillinterface.png");
                mFairy.onTap(0.8f, result, 1164, 460, 1165, 461, "生活技能", 2000);
                mFairy.onTap(0.8f, result, 1164, 460, 1165, 461, "生活技能", 2000);
                if (result.sim > 0.8f) {
                    if (jnpr <= 18) {

                        mFairy.onTap(0.8f, result, 326, 285, 327, 286, "烹饪", 2000);
                    } else {
                        mFairy.onTap(0.8f, result, 190, 285, 191, 286, "炼药", 2000);

                    }
/*
                    for (int i = 0; i < 5; i++) {
                        result = mFairy.findPic(592, 73, 689, 688, "Takeup.png");
                        mFairy.onTap(0.9f, result,  "收起", Sleep);
                    }*/
                    if (jnpr >= 9 && jnpr <= 18) {
                        mFairy.ranSwipe(511, 594, 486, 165, 1000, (long) 1000, 2);
                        mFairy.ranSwipe(511, 594, 486, 165, 1000, (long) 1000, 2);
                       /* for (int i = 0; i < 5; i++) {
                            result = mFairy.findPic(592, 73, 689, 688, "Takeup.png");
                            mFairy.onTap(0.9f, result,  "收起", Sleep);
                        }*/
                    }
                    if (jnpr >= 27) {
                        mFairy.ranSwipe(511, 594, 486, 165, 1000, (long) 1000, 2);
                        mFairy.ranSwipe(511, 594, 486, 165, 1000, (long) 1000, 2);
                      /*  for (int i = 0; i < 5; i++) {
                            result = mFairy.findPic(592, 73, 689, 688, "Takeup.png");
                            mFairy.onTap(0.9f, result,  "收起", Sleep);
                        }*/
                    }
                    if (jnpr == 1) {
                        mFairy.onTap(458, 521, 479, 534, "地三鲜", Sleep);
                        mFairy.onTap(458, 521, 479, 534, "地三鲜", Sleep);
                    }
                    if (jnpr == 2) {
                        mFairy.onTap(543, 515, 568, 531, "四喜土豆", Sleep);
                        mFairy.onTap(543, 515, 568, 531, "四喜土豆", Sleep);
                    }
                    if (jnpr == 3) {
                        mFairy.onTap(632, 518, 655, 531, "五四洋粉", Sleep);
                        mFairy.onTap(632, 518, 655, 531, "五四洋粉", Sleep);
                    }
                    if (jnpr == 4) {
                        mFairy.onTap(447, 604, 468, 621, "六一菜", Sleep);
                        mFairy.onTap(447, 604, 468, 621, "六一菜", Sleep);

                    }
                    if (jnpr == 5) {
                        mFairy.onTap(546, 601, 569, 618, "七星豌豆", Sleep);
                        mFairy.onTap(546, 601, 569, 618, "七星豌豆", Sleep);
                    }
                    if (jnpr == 6) {
                        mFairy.onTap(635, 604, 651, 617, "八宝酱", Sleep);
                        mFairy.onTap(635, 604, 651, 617, "八宝酱", Sleep);
                    }
                    if (jnpr == 7) {
                        mFairy.onTap(455, 106, 475, 122, "九黄饼", Sleep);
                        mFairy.onTap(455, 106, 475, 122, "九黄饼", Sleep);

                    }
                    if (jnpr == 8) {
                        mFairy.onTap(545, 107, 563, 119, "十远羹", Sleep);
                        mFairy.onTap(545, 107, 563, 119, "十远羹", Sleep);
                    }

                    if (jnpr == 9) {
                        mFairy.onTap(456, 269, 478, 281, "三河鲈鱼", Sleep);
                        mFairy.onTap(456, 269, 478, 281, "三河鲈鱼", Sleep);
                    }
                    if (jnpr == 10) {
                        mFairy.onTap(546, 257, 567, 271, "四圆扒鸭", Sleep);
                        mFairy.onTap(546, 257, 567, 271, "四圆扒鸭", Sleep);
                    }
                    if (jnpr == 11) {
                        mFairy.onTap(635, 261, 657, 273, "五香酱牛肉", Sleep);
                        mFairy.onTap(635, 261, 657, 273, "五香酱牛肉", Sleep);
                    }
                    if (jnpr == 12) {
                        mFairy.onTap(464, 350, 484, 364, "六合猪肝", Sleep);
                        mFairy.onTap(464, 350, 484, 364, "六合猪肝", Sleep);
                    }
                    if (jnpr == 13) {
                        mFairy.onTap(541, 353, 566, 367, "七星紫蟹", Sleep);
                        mFairy.onTap(541, 353, 566, 367, "七星紫蟹", Sleep);
                    }
                    if (jnpr == 14) {
                        mFairy.onTap(626, 352, 649, 362, "八代鱼", Sleep);
                        mFairy.onTap(626, 352, 649, 362, "八代鱼", Sleep);
                    }
                    if (jnpr == 15) {
                        mFairy.onTap(460, 437, 484, 455, "九转大肠", Sleep);
                        mFairy.onTap(460, 437, 484, 455, "九转大肠", Sleep);

                    }
                    if (jnpr == 16) {
                        mFairy.onTap(542, 436, 563, 450, "十全大补汤", Sleep);
                        mFairy.onTap(542, 436, 563, 450, "十全大补汤", Sleep);
                    }
                    if (jnpr == 17) {
                        mFairy.onTap(442, 596, 468, 614, "许逊寿元", Sleep);
                        mFairy.onTap(442, 596, 468, 614, "许逊寿元", Sleep);
                    }
                    if (jnpr == 18) {
                        mFairy.onTap(543, 596, 564, 611, "洞宾寿元", Sleep);
                        mFairy.onTap(543, 596, 564, 611, "洞宾寿元", Sleep);
                    }


                    if (jnpr == 19) {
                        mFairy.onTap(455, 426, 474, 437, "密蒙丸", Sleep);
                        mFairy.onTap(455, 426, 474, 437, "密蒙丸", Sleep);
                    }
                    if (jnpr == 20) {
                        mFairy.onTap(545, 432, 566, 446, "贝母丸", Sleep);
                        mFairy.onTap(545, 432, 566, 446, "贝母丸", Sleep);
                    }
                    if (jnpr == 21) {
                        mFairy.onTap(628, 428, 650, 440, "三七丸", Sleep);
                        mFairy.onTap(628, 428, 650, 440, "三七丸", Sleep);
                    }
                    if (jnpr == 22) {
                        mFairy.onTap(454, 510, 476, 529, "当归丸", Sleep);
                        mFairy.onTap(454, 510, 476, 529, "当归丸", Sleep);
                    }
                    if (jnpr == 23) {
                        mFairy.onTap(548, 520, 565, 534, "红花丸", Sleep);
                        mFairy.onTap(548, 520, 565, 534, "红花丸", Sleep);
                    }
                    if (jnpr == 24) {
                        mFairy.onTap(632, 528, 658, 541, "黄芩丸", Sleep);
                        mFairy.onTap(632, 528, 658, 541, "黄芩丸", Sleep);
                    }
                    if (jnpr == 25) {
                        mFairy.onTap(457, 606, 474, 617, "赤芍丸", Sleep);
                        mFairy.onTap(457, 606, 474, 617, "赤芍丸", Sleep);
                    }
                    if (jnpr == 26) {
                        mFairy.onTap(542, 600, 566, 616, "凌霄丸", Sleep);
                        mFairy.onTap(542, 600, 566, 616, "凌霄丸", Sleep);
                    }


                    if (jnpr == 27) {
                        mFairy.onTap(454, 412, 476, 431, "龙肝散", Sleep);
                        mFairy.onTap(454, 412, 476, 431, "龙肝散", Sleep);
                    }
                    if (jnpr == 28) {
                        mFairy.onTap(536, 417, 564, 432, "肉芝散", Sleep);
                        mFairy.onTap(536, 417, 564, 432, "肉芝散", Sleep);
                    }
                    if (jnpr == 29) {
                        mFairy.onTap(632, 419, 657, 436, "冥灵散", Sleep);
                        mFairy.onTap(632, 419, 657, 436, "冥灵散", Sleep);
                    }
                    if (jnpr == 30) {
                        mFairy.onTap(460, 507, 480, 520, "黄精散", Sleep);
                        mFairy.onTap(460, 507, 480, 520, "黄精散", Sleep);

                    }
                    if (jnpr == 31) {
                        mFairy.onTap(541, 511, 563, 522, "扶桑散", Sleep);
                        mFairy.onTap(541, 511, 563, 522, "扶桑散", Sleep);
                    }
                    if (jnpr == 32) {
                        mFairy.onTap(635, 506, 659, 525, "玉桃散", Sleep);
                        mFairy.onTap(635, 506, 659, 525, "玉桃散", Sleep);
                    }
                    if (jnpr == 33) {
                        mFairy.onTap(453, 597, 472, 612, "三珠散", Sleep);
                        mFairy.onTap(453, 597, 472, 612, "三珠散", Sleep);
                    }
                    if (jnpr == 34) {
                        mFairy.onTap(544, 598, 563, 618, "地日散", Sleep);
                        mFairy.onTap(544, 598, 563, 618, "地日散", Sleep);
                    }

                    mFairy.onTap(897, 606, 938, 619, "开始烹饪", 2000);

                    result = mFairy.findPic(810, 275, 921, 411, "Number1.png");
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < numpr; i++) {
                            mFairy.tap(866, 353);
                            Thread.sleep(10);
                            //  mFairy.onTap(0.8f, result, "加号", 1);
                        }
                        mFairy.onTap(757, 456, 781, 468, "烹饪", Sleep);
                        setTaskName(2);
                    }
                }
            }

            public void content_2() throws Exception {
                overtime(6, 99);

                result = mFairy.findPic("pr1.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("正在烹饪中"));
                    err = 0;
                }
                Thread.sleep(2000);
            }
        }.taskContent(mFairy, "烹饪中");
    }

    //抢红包
    public void grabRed(final int i) throws Exception {
        new OtherGame(mFairy) {

            int num;
            int qh;
            public void create() throws Exception {
                super.create();
                num = 0;
                qh=0;
            }

            public void content_0() throws Exception {

                result = mFairy.findPic(521,11,626,702,"chat.png");
                mFairy.onTap(0.8f,result,1159,56,1180,72,"",1000);

                result = mFairy.findPic(340,96,730,395,"zb.png");
                mFairy.onTap(0.8f,result,1143,578,1152,587,"装备详细关掉",1000);


                gameUtil.close(0);
                setTaskName(1);
            }

            public void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("package.png");
                mFairy.onTap(0.8f, result, 684, 689, 685, 690, "打开聊天框", Sleep);

                result = mFairy.findPic("chat.png");
                mFairy.onTap(0.8f, result, 36, 23, 50, 33, "聊天框切换到世界频道", 2000);
                mFairy.onTap(0.8f, result, 386, 682, 397, 694, "打开表情", 2000);

                result = mFairy.findPic("worldred.png");
                mFairy.onTap(0.8f, result, "世界红包", Sleep);

                result = mFairy.findPic("Refreshred.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("红包界面"));
                    setTaskName(2);
                }
            }

            public void content_2() throws Exception {
                if (overtime(15, 0)) return;

                result = mFairy.findPic("Refreshred.png");
                if (result.sim > 0.8f) {

                    switch (qh){
                        case 0:
                            mFairy.onTap(1165,149,1185,172,"",1000);
                            qh=1;
                            break;
                        case 1:
                            mFairy.onTap(1156,249,1183,273,"",1000);
                            qh=0;
                            break;
                    }

                    mFairy.onTap(0.8f, result, "刷新", 100);

                    err = 0;
                    num++;
                    if (i != 0) {
                        if (num >= i) {
                            mFairy.onTap(1164, 31, 1179, 42, "", 1000);
                            setTaskEnd();
                            return;
                        }
                    }
                }

                result = mFairy.findPic(163, 212, 403, 375, new String[]{"Havesnatchedlight.png", "hb2.png"});
                if (result.sim > 0.8f) {
                } else {
                    mFairy.onTap(272, 216, 299, 237, "拆红包", 100);
                }

                /*result = mFairy.findPic(232, 15, 1089, 377, new String[]{"fork1.png", "fork2.png", "hbfork.png"});
                mFairy.onTap(0.7f, result, "叉子", 1000);*/

                result = mFairy.findPic(808,31,958,130, "hb1.png");
                mFairy.onTap(0.7f, result, "叉子", 1000);
            }


        }.taskContent(mFairy, "抢红包中");
    }
}
