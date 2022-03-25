package com.script.fairy;

import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;

public class SingleTask extends Task {
    private boolean fkst = false;//疯狂石头计数
    private int btjs = 0;
    private int activity = 0, act = 0;
    private int battleFailure = 0;//战斗
    private int wb = 0;//挖宝
    private int tmsy = 0;

    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        super(ypFairy);
        init();
    }

    private void init() throws Exception {
        if (AtFairyConfig.getTaskID().equals("1424")) {
            LtLog.e(mFairy.getLineInfo("【一键多少级】"));
        }

        if (AtFairyConfig.getTaskID().equals("1426")) {
            list = new ArrayList<>();
            if (AtFairyConfig.getOption("pl").equals("1")) {
                LtLog.e(mFairy.getLineInfo("【勾选清理背包】"));
                pl = 0;
            } else {
                pl = 1;
            }
            hong=0;
            lan=0;
            timeHong =System.currentTimeMillis()-90000;
            timeLan =System.currentTimeMillis()-90000;
            if (!AtFairyConfig.getOption("hong").equals("")) {
                hong=Integer.parseInt(AtFairyConfig.getOption("hong"));
                LtLog.e(mFairy.getLineInfo("【勾选加红:】"+hong));
            }
            if (!AtFairyConfig.getOption("lan").equals("")) {
                lan=Integer.parseInt(AtFairyConfig.getOption("lan"));
                LtLog.e(mFairy.getLineInfo("【勾选加蓝:】"+lan));
            }

            if (AtFairyConfig.getOption("hbs").equals("1")) {
                LtLog.e(mFairy.getLineInfo("【曲别针换别墅】"));
                list.add("hbs");
            }
            if (AtFairyConfig.getOption("bp").equals("1")) {
                LtLog.e(mFairy.getLineInfo("【帮派任务】"));
                list.add("bp");
            }
            if (AtFairyConfig.getOption("ztt").equals("1")) {
                LtLog.e(mFairy.getLineInfo("【诛天浮屠塔】"));
                list.add("ztt");
            }
            if (AtFairyConfig.getOption("dfdj").equals("1")) {
                LtLog.e(mFairy.getLineInfo("【巅峰对决】"));
                list.add("dfdj");
            }
            if (AtFairyConfig.getOption("2484").equals("1")) {
                LtLog.e(mFairy.getLineInfo("【疯狂的石头】"));
                list.add("fkst");
            }
            if (AtFairyConfig.getOption("wb").equals("1")) {
                LtLog.e(mFairy.getLineInfo("【挖宝】"));
                list.add("wb");
            }
            ranks = 10;
            battleFailureCount = 0;
            mainScene = 18;//主场景计次
            unexecuted = 0;
            lu = 0;
            supplement = 0;//自动补充
            setUp = 0;
            err = 10;
        }
    }

    /***
     *  新手引导 */
    private int nn = -1, nn1 = 0, xl = 0, gzt = 0, xiang = 0,nu=0;

    public void novice() throws Exception {
        while (mFairy.condit()) {
            result = mFairy.findPic("yj1.png");
            if (result.sim > 0.85f) {
                mFairy.onTap(0.85f, result, "2.5模式", 1000);
                mFairy.onTap(614, 524, 659, 545, "确定选择", 1000);
            }

            result = mFairy.findPic("tiao.png");
            mFairy.onTap(0.85f, result, "跳过", 1000);

            result1 = mFairy.findPic(399, 393, 658, 589, "jj.png");
            if (result1.sim > 0.7f) {
                result = mFairy.findPic(384, 347, 650, 543, "jj1.png");
                mFairy.onTap(0.85f, result, result.x + 28, result.y + 28, result.x + 32, result.y + 32, "勾选弹框", 1000);

                mFairy.onTap(0.7f, result1, "拒绝", 1000);
            }

            for (int i = 0; i < 10; i++) {
                result = mFairy.findPic(1020, 585, 1207, 670, "jx.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "继续", 1000);
                } else {
                    break;
                }
                
            }

            result = mFairy.findPic(0, 105, 64, 395, "task.png");
            mFairy.onTap(0.85f, result, "任务", 2000);
            /***
             *  新手提示
             */
            if(nu>2) {
                nu=0;
                noviceToolsOne();
            }
            nu++;

            result = mFairy.findPic("nn14.png");
            mFairy.onTap(0.85f, result, "二人组", 1000);

            result = mFairy.findPic("wenxin.png");
            mFairy.onTap(0.92f, result, 624, 422, 656, 449, "温馨提示", 1000);

            result = mFairy.findPic("nn8.png");
            mFairy.onTap(0.85f, result, "升级", 1000);

            result = mFairy.findPic("nn19.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(552, 278, 596, 314, "突破", 3000);
                mFairy.onTap(472, 173, 501, 199, "1", 1000);
                mFairy.onTap(817, 510, 851, 530, "2", 1000);
            }

            result = mFairy.findPic("gzt1.png");
            if (result.sim > 0.8f) {
                gzt++;
                if (gzt > 3) {
                    result = mFairy.findPic(921, 68, 1090, 164, new String[]{"fb.png", "fb1.png"});
                    mFairy.onTap(0.8f, result, 1050, 108, 1071, 125, "退出副本", 1000);
                }
            } else {
                gzt = 0;
            }

            result = mFairy.findPic("nn15.png");
            mFairy.onTap(0.85f, result, "前往凌渡定州", 1000);

            result = mFairy.findPic("nn2.png");
            mFairy.onTap(0.8f, result, "手套", 1000);

            result = mFairy.findPic("shangma.png");
            mFairy.onTap(0.85f, result, "马", 1000);

            result = mFairy.findPic("jn.png");
            mFairy.onTap(0.8f, result, "技能", 1000);

            result = mFairy.findPic("nn27.png");
            mFairy.onTap(0.8f, result, 97, 64, 134, 93, "查看世界地图", 1000);

            result = mFairy.findPic("nn3.png");
            mFairy.onTap(0.8f, result, 150, 565, 174, 588, "上马", 1000);

            result = mFairy.findPic(843, 621, 1175, 702, "nn5.png");
            mFairy.onTap(0.8f, result, 1215, 659, 1239, 680, "跳", 1000);

            result = mFairy.findPic(843, 621, 1175, 702, "nn7.png");
            mFairy.onTap(0.8f, result, 1215, 659, 1239, 680, "跳", 1000);

            result = mFairy.findPic("powerSaving.png");
            mFairy.onTap(0.85f, result, "省电模式", 1000);

            result = mFairy.findPic(399, 393, 658, 589, "jj.png");
            mFairy.onTap(0.8f, result, "拒绝", 1000);

            result = mFairy.findPic("gzt.png");
            mFairy.onTap(0.85f, result, "归宗塔", 1000);

            result = mFairy.findPic("nn25.png");
            mFairy.onTap(0.85f, result, "祖龙城", 1000);

            result = mFairy.findPic("nn26.png");
            mFairy.onTap(0.85f, result, "联军", 1000);

            result = mFairy.findPic("chat.png");
            mFairy.onTap(0.8f, result, 1188, 670, 1231, 697, "聊天", 1000);

            result = mFairy.findPic("nn30.png");
            mFairy.onTap(0.8f, result, "后会无期", 1000);

            result = mFairy.findPic("nn17.png");
            if (result.sim > 0.85f && xl < 3) {
                mFairy.onTap(0.85f, result, 532, 465, 557, 485, "取消", 1000);
                xl++;
            } else {
                result = mFairy.findPic(582, 345, 892, 567, "ok.png");
                mFairy.onTap(0.85f, result, "确定", 1000);
                xl = 0;
            }

            result = mFairy.findPic(new String[]{"nn1.png","nnfb.png"});
            if (result.sim > 0.8f) {

                result = mFairy.findPic("nnfb1.png");
                if(result.sim>0.85f){
                    gamePublicFuntion.skills();
                }else{
                    mFairy.onTap(1228,265,1247,283,"切换 技能",1000);
                }

            }

            result = mFairy.findPic("tishi.png");
            if (result.sim > 0.85f) {
                mFairy.onTap(291, 618, 342, 658, "提示", 200);
                mFairy.onTap(257, 505, 296, 530, "提示", 600);
            }
            result = mFairy.findPic("left.png");
            mFairy.onTap(0.85f, result, "左侧缩放栏", 1000);

            result = mFairy.findPic(801, 215, 1057, 475, "use.png");
            mFairy.onTap(0.8f, result, "使用", 1000);

            result = mFairy.findPic("nn31.png");
            mFairy.onTap(0.8f, result, "驯服", 5000);

            result = mFairy.findPic(801, 215, 1057, 475, "equipment.png");
            mFairy.onTap(0.8f, result, "装备", 1000);

        /*result = mFairy.findPic(1021, 226, 1246, 451, "nn6.png");
        if (result.sim > 0.75f) {
            mFairy.onTap(result.x + 20, result.y + 20, result.x + 25, result.y + 25, "新手提示", 3000);
        }*/

            result = mFairy.findPic("nn9.png");
            mFairy.onTap(0.8f, result, 1148, 429, 1168, 450, "技能", 1000);

            result = mFairy.findPic("nn16.png");
            mFairy.onTap(0.8f, result, 1218, 483, 1240, 504, "切换", 1000);

            result = mFairy.findPic(396, 4, 547, 80, new String[]{"boss.png", "boss1.png"});
            if (result.sim > 0.85f) {
                gamePublicFuntion.skills();
            }

            result = mFairy.findPic("jinglian.png");
            mFairy.onTap(0.85f, result, "精炼", 1000);

            result1 = mFairy.findPic(0, 105, 64, 395, "task1.png");
            if (result1.sim > 0.85f) {
                nn1++;
                result = mFairy.findPic(103, 109, 192, 232, "nnEnd.png");
                if (result.sim > 0.85f) {

                    return;
                } else {
                    if (nn == -1) {
                        for (int i = 0; i < 3; i++) {
                            mFairy.ranSwipe(142, 140, 191, 377, 0, 500, (long)100);
                        }
                        Thread.sleep(3500);
                        
                        nn = 0;
                    }

                    result = mFairy.findPic("lu.png");
                    if (result.sim < 0.8f) {
                        int m = mFairy.getColorNum(88, 146, 127, 169, "181,174,173", 0.95f);
                        result = mFairy.findPic(44,107,263,205,new String[]{"nn11.png","dadao.png"});
                        result1 = mFairy.findPic(119, 107, 188, 150, "nn24.png");
                        LtLog.e("------" + result.sim);
                        LtLog.e("------" + result1.sim);
                        LtLog.e("--------" + m);
                        if ((result.sim > 0.75f || result1.sim > 0.72f) && m > 10) {
                            mFairy.onTap(106, 193, 132, 218, "修为", 1000);
                        } else {
                            mFairy.onTap(71, 134, 133, 154, "主线", 3000);
                            noviceToolTwo();
                        }

                    /*result = mFairy.findPic(45, 103, 98, 242, "xiu.png");
                    LtLog.e(mFairy.getLineInfo("修为:" + result.sim));
                    if (result.sim > 0.8f) {
                        mFairy.onTap(106, 193, 132, 218, "修为", 1000);
                    } else {
                        mFairy.onTap(71, 134, 133, 154, "主线", 3000);
                    }*/
                    }
                }
            } else {
                nn1 = 0;
                gamePublicFuntion.close();
            }

            if (nn1 > 10) {
                gamePublicFuntion.close();
                nn1 = 0;
            }

            result = mFairy.findPic("nn4.png");
            if (result.sim > 0.85f) {
                for (int i = 0; i < 4; i++) {
                    mFairy.ranSwipe(165, 122 + (70 * i), 1117, 172 + (70 * i), 1, 1000, (long)100);
                }
            }
        }
    }

    public void noviceToolsOne() throws Exception {
        /**
         *  精炼  */
        result = mFairy.findPic(50, 97, 221, 193, new String[]{"nn10.png", "nn12.png"});
        if (result.sim > 0.8f) {
            mFairy.onTap(1227, 265, 1246, 285, "第一步", 2000);
            mFairy.onTap(1071, 345, 1092, 369, "第二步", 3000);
            result = mFairy.findPic("jlScene.png");
            if (result.sim < 0.8f) {
                return;
            }
            mFairy.onTap(255, 206, 301, 241, "第三步", 1000);
        }

        /**
         * 技能*/
        result = mFairy.findPic(50, 97, 221, 193, "nn21.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(1227, 265, 1246, 285, "第一步", 2000);
            mFairy.onTap(1151, 342, 1170, 368, "第二步", 3000);
        }

        /**
         * 镶嵌
         */
        result = mFairy.findPic(50, 97, 221, 193, "nn22.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(1227, 265, 1246, 285, "第一步", 2000);
            mFairy.onTap(1071, 345, 1092, 369, "第二步", 3000);

            result = mFairy.findPic("jlScene.png");
            if (result.sim < 0.8f) {
                return;
            }

            mFairy.onTap(1142, 249, 1177, 285, "第三步", 2000);
            mFairy.onTap(255, 206, 301, 241, "第四步", 2000);
            mFairy.onTap(564, 143, 594, 173, "第五步", 2000);
            mFairy.onTap(921, 198, 967, 227, "第六步", 2000);
        }
    }

    public void noviceToolTwo() throws Exception {

        /**
         * 任务点击镶嵌
         */
        result = mFairy.findPic(57, 105, 271, 194, "nn23.png");
        LtLog.e("镶嵌:" + result.sim);
        if (result.sim > 0.75f && xiang < 3) {
            xiang++;
            mFairy.onTap(1227, 265, 1246, 285, "第一步", 2000);
            mFairy.onTap(1071, 345, 1092, 369, "第二步", 3000);
            result = mFairy.findPic("jlScene.png");
            if (result.sim < 0.8f) {
                return;
            }
            mFairy.onTap(1142, 249, 1177, 285, "第三步", 2000);
            mFairy.onTap(255, 206, 301, 241, "第四步", 2000);
            mFairy.onTap(564, 143, 594, 173, "第五步", 1000);
            mFairy.onTap(432, 265, 471, 302, "第五步", 1000);
            mFairy.onTap(921, 198, 967, 227, "第六步", 2000);
        }

        if (xiang >= 3) {
            result = mFairy.findPic(135, 122, 245, 186, "nn28.png");
            if (result.sim > 0.8f) {
                result = mFairy.findPic("nn29.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(1071, 345, 1092, 369, "第二步", 3000);

                } else {
                    mFairy.onTap(1227, 265, 1246, 285, "第一步", 2000);
                    mFairy.onTap(1071, 345, 1092, 369, "第二步", 3000);
                }
                result = mFairy.findPic("jlScene.png");
                if (result.sim < 0.8f) {
                    return;
                }
                mFairy.onTap(1142, 249, 1177, 285, "第三步", 2000);

                for (int i = 0; i < 3; i++) {
                    mFairy.ranSwipe(217, 245, 263, 568, 2, 500, (long)100);
                }
                mFairy.sleep(2000);
                

                mFairy.onTap(294, 349, 326, 386, "第四步", 2000);
                mFairy.onTap(938, 203, 979, 240, "第五步", 2000);
            }
            xiang = 0;
        }
    }

    public void noviceEndTask(int num) throws Exception {
        int bj = 0;
        int count = 0;
        while (mFairy.condit()) {
            result1 = mFairy.findPic(399, 393, 658, 589, "jj.png");
            if (result1.sim > 0.7f) {
                result = mFairy.findPic(384, 347, 650, 543, "jj1.png");
                mFairy.onTap(0.85f, result, result.x + 28, result.y + 28, result.x + 32, result.y + 32, "勾选弹框", 2000);

                mFairy.onTap(0.7f, result1, "拒绝", 1000);
            }

            if (bj == 0) {
                super.unexecutedTask();

                result = mFairy.findPic(1107, 228, 1212, 335, new String[]{"home.png", "home2.png","bao.png"});
                if (result.sim > 0.7f) {
                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic(1031, 293, 1132, 412, "ne1.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "强化", 3000);
                            bj = 1;
                            break;
                        } else {
                            mFairy.onTap(1225, 262, 1248, 292, "缩放栏", 2000);
                        }
                        
                    }
                }


            }

            if (bj == 1) {
                count++;
                if(count>10){
                    count=0;
                    bj=0;
                    continue;
                }
                result = mFairy.findPic("ne2.png");
                if (result.sim > 0.8f) {
                    count=0;
                    mFairy.onTap(184,225,185,226,"武器",2000);
                    mFairy.onTap(741,647,797,667,"自动精炼",1000);
                }

                result = mFairy.findPic("ne3.png");
                mFairy.onTap(0.8f,result,402,361,438,375,"输入强化等级",1000);

                result = mFairy.findPic("ne4.png");
                if (result.sim > 0.8f) {
                    switch (num){
                        case 5:
                            mFairy.onTap(520,390,543,417,num+"级",1000);
                            break;
                        case 6:
                            mFairy.onTap(680,385,699,411,num+"级",1000);
                            break;
                        case 7:
                            mFairy.onTap(368,307,402,327,num+"级",1000);
                            break;
                        case 8:
                            mFairy.onTap(521,307,550,332,num+"级",1000);
                            break;
                    }
                    mFairy.onTap(825,470,865,491,"确定",1000);
                    mFairy.onTap(893,588,941,606,"自动精炼",2000*(num));
                    gamePublicFuntion.close();
                    return;
                }
            }
        }

    }//

    /***
     *  单人任务 */
    public void unexecuted() throws Exception {
        /***
         *  task
         */
        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        result = mFairy.findPic("jia3.png");
        mFairy.onTap(0.8f,result,1206,69,1227,84,"商会关闭",500);

        switch (list.get(0)) {
            case "hbs":
                result = mFairy.findPic(941, 289, 1214, 685, "hbs1.png");
                mFairy.onTap(0.8f, result, "换别墅", 2000);
                break;
            case "bp":
                result = mFairy.findPic("bangpai.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("没有加入帮派不能执行此任务,End!"));
                    list.remove(0);
                    gamePublicFuntion.EndTask(this);
                    mainScene = 15;
                    return;
                }
                result = mFairy.findPic(941, 289, 1214, 685, new String[]{"bp1.png", "bp2.png"});
                mFairy.onTap(0.8f, result, "帮派任务,任务选择", 2000);
                break;
            case "ztt":
                result1 = mFairy.findPic(470, 455, 1071, 590, "ztt1.png");
                if (result1.sim > 0.8f) {
                    mFairy.onTap(762, 536, 791, 558, "扫荡", 2000);
                    mFairy.onTap(848, 92, 873, 113, "关", 1000);
                    mFairy.onTap(0.8f, result1, "诛天浮屠塔-挑战", 2000);
                }

                result = mFairy.findPic("likai.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "诛天浮屠塔-离开", 1000);
                    list.remove(0);
                    gamePublicFuntion.EndTask(this);
                    mainScene = 15;
                }

                result = mFairy.findPic(778, 551, 1016, 616, "ztt2.png");
                mFairy.onTap(0.8f, result, "诛天浮屠塔-前往下一层", 1000);

                break;
            case "dfdj":
                result = mFairy.findPic("dfdj3.png");
                if (result.sim > 0.76f) {
                    list.remove(0);
                    gamePublicFuntion.EndTask(this);
                    LtLog.e(mFairy.getLineInfo("巅峰对决没有次数,End!"));
                    mainScene = 15;
                    return;
                }
                result = mFairy.findPic(90,290,1112,525, "dfdj1.png");
                mFairy.onTap(0.8f, result, "巅峰对决-挑战", 5000);

                result = mFairy.findPic("dfdj2.png");
                mFairy.onTap(0.8f, result, "巅峰对决-再次挑战", 5000);

                unexecuted = 0;
                break;
            case "fkst":
                result = mFairy.findPic("bangpai.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("没有加入帮派不能执行此任务,End!"));
                    list.remove(0);
                    gamePublicFuntion.EndTask(this);
                    mainScene = 15;
                    return;
                }
                result = mFairy.findPic(954, 336, 1231, 665, "fkst1.png");
                mFairy.onTap(0.8f, result, "疯狂的石头", 2000);

                result = mFairy.findPic("fkst2.png");
                if (result.sim > 0.8f) {
                    int num;
                    for (int i = 1; i <= 8; ) {
                        

                        result = mFairy.findPic("fkst2.png");
                        if (result.sim > 0.8f) {

                            result = mFairy.findPic("fkst4.png");
                            mFairy.onTap(0.8f, result, 721, 470, 749, 489, "确定求助", 2000);
                            switch (i) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                    num = ((i - 1) * 130);
                                    for (int c = 0; c < 2; c++) {
                                        mFairy.onTap(189 + num, 193, 217 + num, 219, "第" + i + "个物品", 500);
                                    }
                                    break;
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                    num = ((i - 5) * 130);
                                    for (int c = 0; c < 2; c++) {
                                        mFairy.onTap(186 + num, 309, 216 + num, 334, "第" + i + "个物品", 500);
                                    }
                                    break;
                            }
                        }else{
                            mFairy.onTap(1177, 70, 1197, 88, "", 1000);
                        }
                        mFairy.onTap(1041, 644, 1067, 663, "装填", 500);
                        for (int j = 0; j < 5; j++) {
                            result = mFairy.findPic("fkst3.png");
                            if (result.sim > 0.8f) {
                                LtLog.e(mFairy.getLineInfo("没有足够数量的物品"));
                                fkst = true;
                                break;
                            }
                            
                        }

                        if (fkst) {
                            fkst = false;
                            mFairy.onTap(914, 239, 944, 267, "点击添加", 2000);

                            result = mFairy.findPic(822, 142, 1142, 465, "fkst5.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "系统商店", 3000);

                                /***
                                 *  系统商店*/
                                result = mFairy.findPic(764, 500, 1215, 703, "gm.png");
                                if (result.sim > 0.8f) {
                                    mFairy.onTap(0.8f, result, "购买", 1000);
                                    for (int m = 0; m < 5; m++) {
                                        result = mFairy.findPic(461, 74, 611, 160, "err2.png");
                                        if (result.sim > 0.75f) {
                                            LtLog.e(mFairy.getLineInfo("背包不足"));
                                            /*GamePublicFuntion.FINISH=16902;*/
                                            list.remove(0);
                                            gamePublicFuntion.EndTask(this);
                                            return;
                                        }
                                        
                                    }
                                    mFairy.onTap(1177, 70, 1197, 88, "", 1000);
                                }
                                continue;
                            } else {

                                if (AtFairyConfig.getOption("bt").equals("1")) {
                                    result = mFairy.findPic(822, 142, 1142, 465, "fkst8.png");
                                    if (result.sim > 0.7f) {

                                        if (btjs > 2) {
                                            list.remove(0);
                                            GamePublicFuntion.FINISH = 16903;
                                            gamePublicFuntion.EndTask(this);
                                            return;
                                        }

                                        mFairy.onTap(0.7f, result, "摆摊", 3000);

                                        result1 = mFairy.findPic(764, 500, 1215, 703, "gm1.png");
                                        if (result1.sim > 0.8f) {
                                            mFairy.onTap(0.8f, result1, 366, 248, 417, 288, "选择摆摊第一个商品", 1000);
                                            mFairy.onTap(0.8f, result1, "购买", 1500);
                                            mFairy.onTap(0.8f, result1, 798, 527, 824, 545, "确定购买", 1000);

                                            result = mFairy.findPic("gm2.png");
                                            if (result.sim > 0.8f) {
                                                list.remove(0);
                                                mFairy.onTap(820, 221, 839, 240, "金币不足,疯狂的石头任务结束!", 1000);
                                                mFairy.onTap(824, 188, 839, 200, "", 1000);
                                                mFairy.onTap(1174, 70, 1196, 90, "", 1000);
                                                gamePublicFuntion.EndTask(this);
                                            }
                                            mFairy.onTap(1174, 70, 1196, 90, "", 1000);
                                            btjs++;
                                        }
                                        continue;
                                    }
                                }
                                mFairy.onTap(796, 640, 830, 667, "求助", 500);
                                mFairy.onTap(796, 640, 830, 667, "求助", 500);
                                mFairy.onTap(796, 640, 830, 667, "求助", 1000);

                                result = mFairy.findPic("fkst4.png");
                                mFairy.onTap(0.8f, result, 721, 470, 749, 489, "确定求助", 2000);

                            }
                        }
                        i++;
                        btjs = 0;
                        mFairy.sleep(1000);
                    }

                    mFairy.onTap(198, 643, 234, 664, "领取奖励", 1000);
                    list.remove(0);
                    gamePublicFuntion.EndTask(this);
                    mainScene = 15;
                }
                break;
        }

        super.unexecutedTask();

    }//未执行场景

    int i =0;
    public void mainScene() throws Exception {
        result = mFairy.findPic(1107, 228, 1212, 335, new String[]{"home.png", "home2.png","bao.png"});
        LtLog.e(mFairy.getLineInfo("主场景 sim: " + result.sim));
        Task.task_err(0.7f,result);
        if (result.sim > 0.7f) {
            LtLog.e(mFairy.getLineInfo("【主场景>>>" + gamePublicFuntion.getTime()) + "  mainScene:" + mainScene + "】");

            if (gamePublicFuntion.EndTask(this)) {
                return;
            }

            i++;
            if(i>2) {
                result = mFairy.findPic(553, 16, 929, 85, "right1.png");
                mFairy.onTap(0.7f, result, "关闭右侧缩放栏", 1000);
                i=0;
            }

            /**
             *  Task */
            if (mainSceneTask()) {
                return;
            }

            /**
             *  mainScene未操作超时时间*/
            if (gamePublicFuntion.getTime() > 20) {
                mainScene = 10;
                gamePublicFuntion.initTime();
                return;
            }

            /**
             *  点击>>>activity*/
            if (mainScene > 9) {

                LtLog.e(mFairy.getLineInfo("start"));

                super.unexecutedTask();

                if (list.get(0).equals("wb")) {
                    result = mFairy.findPic(1107, 228, 1212, 335, new String[]{"home.png", "home2.png","bao.png"});
                    mFairy.onTap(0.7f, result, "挖宝任务开始,点击背包", 1000);
                    return;
                }

                if (list.get(0).equals("ssfe_branch")) {
                    for (int i = 0; i < 3; i++) {
                        mFairy.onTap(10, 160, 32, 188, "点击任务", 500);
                    }
                    return;
                }

                if (mainScene == 0) {
                    return;
                }

                gamePublicFuntion.start();

                result = mFairy.findPic(698, 14, 1092, 191, "a.png");
                mFairy.onTap(0.8f, result, "活动", 3000);
                return;
            }

        } else {
            gamePublicFuntion.initTime();
            return;
        }

        result = mFairy.findPic("tuoli.png");
        mFairy.onTap(0.8f, result, "脱离", 1000);

        result = mFairy.findPic(553, 16, 929, 85, "right1.png");
        mFairy.onTap(0.7f, result, "关闭右侧缩放栏", 1000);



        /**
         * mainScene计数控制
         */
        if (!list.get(0).equals("wb")) {
            result = mFairy.findPic(396, 4, 547, 80, new String[]{"boss.png", "boss1.png"/*,"boss2.png"*/});
            if (result.sim > 0.82f) {
                gamePublicFuntion.initTime();
                if (gamePublicFuntion.bossTime() > 300) {
                    LtLog.e("战斗时间过长,查看任务>>>>");
                    mainScene = 15;
                    return;
                }
            }
        }


        result = mFairy.findPic(921, 68, 1090, 164, new String[]{"fb.png", "fb1.png"});
        LtLog.e(mFairy.getLineInfo("副本：" + result.sim));
        if (result.sim > 0.8f) {
            super.autoUse();
            LtLog.e(mFairy.getLineInfo("【副本场景】"));
            if (lu > 1) {
                result = mFairy.findPic(510, 516, 600, 616, "auto.png");
                LtLog.e(mFairy.getLineInfo("自动开关相似度," + result.sim));
                mFairy.onTap(0.9f, result, "自动战斗", 1000);
            }

            result = mFairy.findPic("jia2.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(1042, 656, 1066, 677, "1", 1000);
                mFairy.onTap(1013, 575, 1039, 597, "2", 1000);
                mFairy.onTap(1042, 493, 1066, 519, "3", 1000);
                mFairy.onTap(1124, 457, 1154, 481, "4", 1000);
            } else {
                mFairy.onTap(1227, 267, 1253, 287, "切换", 1000);
            }

            for (int i = 0; i < 3; i++) {
                mFairy.onTap(1131, 580, 1176, 605, "a", 500);
            }

            if (battleFailure == -1) {
                battleFailure = 0;
            }

            gamePublicFuntion.bossTimeInit();
            gamePublicFuntion.initTime();

            if (battleFailure == 1) {
                mFairy.onTap(1051, 111, 1075, 134, "离开副本", 2000);
                mFairy.onTap(722, 469, 753, 490, "确定", 1000);
                list.remove(0);
                mainScene = 15;
                battleFailure = 0;
                gamePublicFuntion.EndTask(this);
                return;
            }
        } else {
            if (battleFailure == -1) {/**战斗失败,没有在副本场景。*/
                mainScene = 15;
            }
        }

        /***
         *  任务分类
         */
        switch (list.get(0)) {
            case "wb":
                result = mFairy.findPic(609, 510, 722, 573, "lu.png");
                if (result.sim < 0.8f) {
                    result = mFairy.findPic("wb6.png");
                    mFairy.onTap(0.8f, result, "手", 3000);
                }
                break;
        }

        gamePublicFuntion.close();
    }//主场景

    public void ranks() throws Exception {
        result = mFairy.findPic(new String[]{"ranks.png", "ranks0.png"});
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【组队场景】"));
            gamePublicFuntion.initTime();

            if (ranks == 0) {
                mFairy.onTap(1179, 70, 1201, 91, "关闭", 1000);
                return;
            }

            result = mFairy.findPic("ranksmb.png");
            if (result.sim > 0.8f) {
                return;
            }
        } else {
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        /***
         *  单人
         */
        result = mFairy.findPic(1105,395,1224,715,"ranks6.png");
        mFairy.onTap(0.8f, result, "我的队伍", 2000);

        result = mFairy.findPic("ranks20.png");
        mFairy.onTap(0.8f, result, 955, 108, 972, 122, "关闭", 1000);


       /* if(AtFairyConfig.getOption("bu").equals("1")){
            result = mFairy.findPic("tuo.png");
            mFairy.onTap(0.8f, result, "脱离队伍", 1000);
        }else{*/
        result = mFairy.findPic("ranks5.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "退出队伍", 2000);
            mFairy.onTap(725, 471, 749, 487, "确定", 1000);
        }

        ranks++;
        if (ranks > 9) {
            LtLog.e(mFairy.getLineInfo("达到要求开始任务"));
            mFairy.onTap(1179, 70, 1201, 91, "关闭", 1000);
            ranks = 0;
            mainScene = 15;
        }

    }//组队

    public void activity() throws Exception {

        result = mFairy.findPic("activity.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【活动场景】"));
            unexecuted = 0;
            battleFailureCount = 0;
            gamePublicFuntion.initTime();
            activity++;
            mainScene = 0;

            result = mFairy.findPic("activity1.png");
            mFairy.onTap(0.8f, result, 1001, 101, 1020, 123, "关闭活动介绍", 1000);

            if (ranks == 8) {
                mFairy.onTap(1204, 71, 1225, 90, "关闭活动", 1000);
                return;
            }

            if (activity > 11) {

                if (list.get(0).equals("ssfe")) {
                    list.add(1, "ssfe_branch");
                }
                LtLog.e("活动已经完成,或者未发现,移除当前任务。");
                list.remove(0);
                gamePublicFuntion.EndTask(this);
                activity = 0;
                act = 0;
                return;
            }
        } else {
            activity = 0;
            act = 0;
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }


        /**
         * 赏善罚恶奖励任务
         */
        if (list.get(0).equals("ssfe_branch")) {
            mFairy.onTap(1204, 71, 1225, 90, "关闭活动", 1000);
            activity = 0;
            mainScene = 10;
            return;
        }


        /***
         *  挖宝 */
        if (list.get(0).equals("wb")) {
            mFairy.onTap(1204, 71, 1225, 90, "关闭活动", 1000);
            activity = 0;
            mainScene = 10;
            return;
        }

        /***
         *  天命神谕 *//*
        if (list.get(0).equals("tmsy")) {
            mFairy.onTap(1204, 71, 1225, 90, "关闭活动", 1000);
            activity = 0;
            return;
        }*/

        /**
         *  找任务 */
        result1 = mFairy.findPic(96, 165, 1108, 490, list.get(0) + ".png");
        if (result1.sim > 0.8f) {
            result = mFairy.findPic(result1.x + 264, result1.y - 8, result1.x + 352, result1.y + 63, "can.png");
            if (result.sim > 0.75f) {
                mFairy.onTap(0.8f, result, "前往 :" + list.get(0), 800);

                for (int i = 0; i < 5; i++) {
                    result = mFairy.findPic(593, 63, 708, 161, "err5.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("无法参与此活动"));
                        activity = 13;
                        return;
                    }
                    
                }

                ranks = 0;
                err = 0;
                mainScene = 0;
                act++;

                if (act > 5) {
                    gamePublicFuntion.close();
                }
                return;
            } else {
                act = 0;
            }


            result = mFairy.findPic(result1.x + 264, result1.y - 8, result1.x + 352, result1.y + 63, "can1.png");
            if (result.sim > 0.8f) {
                activity = 13;
                return;
            }
        }


        if (activity == 1) {
            LtLog.e(mFairy.getLineInfo("活动任务初始化"));
            for (int i = 0; i < 6; i++) {
                mFairy.ranSwipe(637, 233, 684, 470, 0, 500, (long)200);
            }
            Thread.sleep(2000);
            
            return;
        }

        if (activity % 2 == 0) {
            mFairy.ranSwipe(637, 250, 684, 516, 2, 1000, (long)2500);
            LtLog.e(mFairy.getLineInfo("滑动"));
            return;
        }

    }//活动

    public void cancel() throws Exception {
        result1 = mFairy.findPic(347, 312, 661, 634, "cancel.png");
        Task.task_err(0.8f,result1);
        if (result1.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【取消场景】"));
            gamePublicFuntion.initTime();
        } else {
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        if (list.get(0).equals("dfdj")) {
            result = mFairy.findPic("dfdj4.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result1, "取消", 1000);
                list.remove(0);
                gamePublicFuntion.EndTask(this);

            }
        }

        result = mFairy.findPic("zlc.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, 725, 471, 754, 489, "确定传送", 1000);
            return;
        }

        result = mFairy.findPic("yuan1.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, 719, 467, 753, 488, "返回原服", 1000);
            return;
        }


        mFairy.onTap(0.8f, result1, "取消", 1000);
    }//取消

    public void fbShiBai() throws Exception {
        result1 = mFairy.findPic(985, 47, 1101, 393, "fbEnd.png");
        Task.task_err(0.8f,result1);
        if (result1.sim > 0.8f) {
            battleFailure = -1;
            if (fbEnd == 0) {
                battleFailureCount++;
                fbEnd = 1;
            }
            LtLog.e(mFairy.getLineInfo("【副本结束】"));
        } else {
            fbEnd = 0;
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        result = mFairy.findPic("likai.png");
        if (result.sim > 0.8f) {
            unexecuted();
        }

        switch (list.get(0)) {
            case "bp":
                battleFailure = 1;
                gamePublicFuntion.FINISH = 16901;
                break;
        }
        result = mFairy.findPic(890, 163, 1174, 649, new String[]{"fh.png", "fh1.png"});
        mFairy.onTap(0.8f, result, "返回复活点", 3000);
    }//副本结束

    public void packages() throws Exception {
        result = mFairy.findPic("packages.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【背包场景】"));
            gamePublicFuntion.initTime();
            mainScene = 0;
            wb++;
            if (wb > 11) {
                LtLog.e(mFairy.getLineInfo("没有找到宝图,End!"));
                list.remove(0);
                gamePublicFuntion.EndTask(this);
                wb = 0;
            }
        } else {
            wb = 0;
            return;
        }

        if (pl == 0) {
            wb = 0;
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        if (list.get(0).equals("wb")) {

            if (wb < 3) {
                mFairy.onTap(1045, 124, 1079, 143, "其他", 1000);
            }

            result = mFairy.findPic(686, 152, 1104, 590,
                    new String[]{"wb2.png", "wb7.png", "wb8.png"});
            if (result.sim > 0.85f) {
                wb = 0;
                mFairy.onTap(0.85f, result, "点击宝图", 1000);
            } else {
                result = mFairy.findPic(686, 152, 1104, 590, "wb1.png");
                if (result.sim > 0.8f) {
                    wb = 0;
                    mFairy.onTap(0.8f, result, "点击未鉴定宝图", 1000);
                }
            }

            result = mFairy.findPic(736, 247, 1093, 616, "wb3.png");
            mFairy.onTap(0.8f, result, "使用", 2500);

            result = mFairy.findPic(713, 150, 962, 338, "wb4.png");
            mFairy.onTap(0.8f, result, "最大", 1000);

            result = mFairy.findPic(738, 419, 961, 550, "wb5.png");
            mFairy.onTap(0.8f, result, "确定", 1500);

            if (wb % 3 == 0 && wb != 0) {
                mFairy.ranSwipe(958, 258, 1001, 495, 2, 1000, (long)3000);
                LtLog.e(mFairy.getLineInfo("滑动找宝图"));
            }

        } else {
            gamePublicFuntion.close();
        }


    }//背包界面

    public void taskScene() throws Exception {
        result = mFairy.findPic("taskScene.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【任务场景】"));
            gamePublicFuntion.initTime();
            mainScene = 0;
            tmsy++;
        } else {
            tmsy = 0;
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        if (list.get(0).equals("ssfe_branch")) {

            result = mFairy.findPic(145, 93, 254, 674, "ssfe_branch1.png");
            mFairy.onTap(0.8f, result, "赏善罚恶", 1500);


            result = mFairy.findPic(66, 92, 205, 682, "ssfe_branch2.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "赏善罚恶", 1500);

                result = mFairy.findPic(346, 149, 1083, 229, "tmsy2.png");
                mFairy.onTap(0.8f, result, 999, 636, 1025, 659, "前往", 2000);

                tmsy = 0;
            }

            if (tmsy % 2 == 0) {
                mFairy.ranSwipe(175, 257, 215, 514, 2, 1000, (long)2500);
            }
            if (tmsy > 7) {
                LtLog.e(mFairy.getLineInfo("赏善罚恶已经完成，没有奖励任务,End!"));
                list.remove(0);
                gamePublicFuntion.EndTask(this);
            }
        } else {
            gamePublicFuntion.close();
        }

    }//任务场景


}





