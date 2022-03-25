package com.script.fairy;

import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import java.util.ArrayList;

/**
 * Created by user on 2020/8/18.
 */

public class OtherTask extends Task {
    private int dy = 0;
    private int sc = 0;
    private int cy = 0;
    private int count = 0;
    private int dynum = 0;
    private int scnum = 0;
    private int cynum = 0;

    public OtherTask(AtFairyImpl ypFairy)throws Exception {
        super(ypFairy);
        init();
    }

    private void init()throws Exception {
        if (AtFairyConfig.getTaskID().equals("1466")) {
            list = new ArrayList<>();
            supplement = 1;
            setUp = 1;
            ranks = 8;
            err = 10;
            count=0;
            battleFailureCount = 0;
            mainScene = 18;
            unexecuted = 0;
            hong=0;
            lan=0;

            if (AtFairyConfig.getOption("pl").equals("1")) {
                LtLog.e(mFairy.getLineInfo("【勾选清理背包】"));
                pl=0;
            }else{
                pl=1;
            }

            if (!AtFairyConfig.getOption("dy").equals("")) {
                list.add("dy");
                dy = Integer.parseInt(AtFairyConfig.getOption("dy"));
                LtLog.e(mFairy.getLineInfo("【钓鱼 " + dy + "】"));
            }
            if (!AtFairyConfig.getOption("sc").equals("")) {
                list.add("sc");
                sc = Integer.parseInt(AtFairyConfig.getOption("sc"));
                LtLog.e(mFairy.getLineInfo("【蔬菜 " + sc + "】"));
            }
            if (!AtFairyConfig.getOption("cy").equals("")) {
                list.add("cy");
                cy = Integer.parseInt(AtFairyConfig.getOption("cy"));
                LtLog.e(mFairy.getLineInfo("【草药 " + cy + "】"));
            }

            if (!AtFairyConfig.getOption("dynum").equals("")) {
                dynum = Integer.parseInt(AtFairyConfig.getOption("dynum"));
                LtLog.e(mFairy.getLineInfo("【钓鱼勾选数量：】" + dynum));
            }

            if (!AtFairyConfig.getOption("scnum").equals("")) {
                scnum = Integer.parseInt(AtFairyConfig.getOption("scnum"));
                LtLog.e(mFairy.getLineInfo("【蔬菜勾选数量：】" + scnum));
            }

            if (!AtFairyConfig.getOption("cynum").equals("")) {
                cynum = Integer.parseInt(AtFairyConfig.getOption("cynum"));
                LtLog.e(mFairy.getLineInfo("【草药勾选数量：】" + cynum));
            }
        }

        if (AtFairyConfig.getTaskID().equals("1468")) {
            list = new ArrayList<>();
            list.add("ling");
            list.add("linghd");
            err = 10;
            supplement = 1;
            setUp = 1;
            ranks = 1;
            unexecuted = 0;
            jia=0;
        }
    }

    public void unexecuted() throws Exception {
        /***
         *  task
         */
        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        result = mFairy.findPic(1113, 211, 1219, 637, "sh2.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "生活", 1000);
            return;
        }

        super.unexecutedTask();

    }//未执行场景

    public void mainScene() throws Exception {
        result = mFairy.findPic(1107,228,1212,335,new String[]{"home.png","home2.png","bao.png"});
        Task.task_err(0.7f,result);
        if (result.sim > 0.7f) {
            LtLog.e(mFairy.getLineInfo("【主场景>>>" + gamePublicFuntion.getTime()) + "  mainScene:" + mainScene + "】");

            if (gamePublicFuntion.EndTask(this)) {
                return;
            }

            /**
             *  mainScene未操作超时时间*/
            if (gamePublicFuntion.getTime() > 25) {
                mainScene = 10;
                gamePublicFuntion.initTime();
                return;
            }

            /**
             *  Task */
            if (mainSceneTask()) {
                return;
            }
            result = mFairy.findPic(716, 448, 814, 533, "sh4.png");
            if (result.sim > 0.8f) {
                count = 9;
                gamePublicFuntion.initTime();
            }

            /**
             *  点击>>>activity*/
            if (mainScene > 9) {
                if (count == 9) {
                    LtLog.e("count = 9 ,当前生活技能完成!");
                    list.remove(0);
                    gamePublicFuntion.EndTask(this);
                    count = 0;
                }
                super.unexecutedTask();

                for (int i = 0; i < 3; i++) {
                    result = mFairy.findPic(1049, 332, 1264, 550, "sh1.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "技能", 1000);
                    } else {
                        mFairy.onTap(1225, 262, 1248, 292, "缩放栏", 1000);
                    }
                    
                }
                return;
            }

        } else {
            gamePublicFuntion.initTime();
            return;
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

        } else {
            return;
        }

        /***
         *  单人
         */
        result = mFairy.findPic("ranks6.png");
        mFairy.onTap(0.8f, result, "我的队伍", 2000);


        result = mFairy.findPic("ranks20.png");
        mFairy.onTap(0.8f, result, 955, 108, 972, 122, "关闭", 1000);

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

    public void cancel() throws Exception {
        result1 = mFairy.findPic(347, 312, 661, 634, "cancel.png");
        Task.task_err(0.8f,result1);
        if (result1.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【取消场景】"));
            gamePublicFuntion.initTime();
        } else {
            return;
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
            if(fbEnd==0) {
                battleFailureCount++;
                fbEnd=1;
            }
            LtLog.e(mFairy.getLineInfo("【副本结束】"));
        } else {
            fbEnd=0;
            return;
        }

        result = mFairy.findPic(890, 163, 1174, 649, new String[]{"fh.png", "fh1.png"});
        mFairy.onTap(0.8f, result, "返回复活点", 3000);


    }//副本结束

    public void sh() throws Exception {
        result = mFairy.findPic("shScene.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【生活场景】"));
        } else {
            return;
        }

        if (gamePublicFuntion.EndTask(this)) {
            return;
        }

        switch (list.get(0)) {
            case "dy":
                result = mFairy.findPic(370,60,646,243,"dy1.png");
                if (result.sim > 0.8f) {
                    shTools(dy);
                    mFairy.onTap(885, 650, 935, 665, "前往", 1000);
                } else {
                    mFairy.onTap(826, 123, 841, 137, "", 1000);
                    mFairy.onTap(116, 143, 150, 171, "选择钓鱼", 1000);
                    return;
                }
                break;
            case "sc":
                result = mFairy.findPic(370,60,646,243,"cj1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(804, 118, 834, 134, "蔬菜", 1000);
                    shTools(sc);
                    mFairy.onTap(885, 650, 935, 665, "前往", 1000);
                } else {
                    mFairy.onTap(826, 123, 841, 137, "", 1000);
                    mFairy.onTap(237, 140, 278, 170, "选择采集", 1000);
                    return;
                }
                break;
            case "cy":
                result = mFairy.findPic(370,60,646,243,"cj1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(966, 118, 998, 134, "草药", 1000);
                    shTools(cy);
                    mFairy.onTap(885, 650, 935, 665, "前往", 1000);
                } else {
                    mFairy.onTap(826, 123, 841, 137, "", 1000);
                    mFairy.onTap(237, 140, 278, 170, "选择采集", 1000);
                    return;
                }
                break;
            default:
                gamePublicFuntion.close();
                return;
        }

        for (int i = 0; i < 5; i++) {
            result = mFairy.findPic(528, 67, 631, 163, "sh3.png");
            if (result.sim > 0.8f) {
                gamePublicFuntion.FINISH = 16905;
                list.remove(0);
                gamePublicFuntion.EndTask(this);
                return;
            }
            
        }

        result = mFairy.findPic(551, 127, 731, 195, "cj2.png");
        if (result.sim > 0.8f) {
            mainScene = 0;
            mFairy.onTap(676, 328, 713, 346, "设置数量", 1000);
            shTools2();
            mFairy.onTap(604, 322, 619, 343, "", 500);
            mFairy.onTap(620, 497, 673, 525, "前往", 3000);
        }

    }//生活

    public void shTools(int num) throws Exception {

        switch (num) {
            case 1:
                mFairy.onTap(759, 222, 783, 250, "等级1", 1000);
                break;
            case 2:
                mFairy.onTap(850, 227, 879, 251, "等级10", 1000);
                break;
            case 3:
                mFairy.onTap(936, 228, 966, 251, "等级20", 1000);
                break;
            case 4:
                mFairy.onTap(1018, 230, 1050, 253, "等级30", 1000);
                break;
            case 5:
                mFairy.onTap(756, 317, 786, 334, "等级40", 1000);
                break;
            case 6:
                mFairy.onTap(845, 316, 873, 337, "等级50", 1000);
                break;
            case 7:
                mFairy.onTap(939, 315, 967, 344, "等级60", 1000);
                break;
            case 8:
                mFairy.onTap(1024, 317, 1048, 340, "等级70", 1000);
                break;
            default:
                LtLog.e(mFairy.getLineInfo("钓鱼出现异常!"));
                break;
        }
    }//选择

    public void shTools2() throws Exception {
        int num = 0;
        switch (list.get(0)) {
            case "dy":
                num = dynum;
                break;
            case "sc":
                num = scnum;
                break;
            case "cy":
                num = cynum;
                break;
        }

        result1 = mFairy.findPic("cj3.png");
        if (result1.sim > 0.8f) {
            switch (num) {
                case 10:
                    mFairy.onTap(358, 464, 392, 495, num + "个", 500);
                    mFairy.onTap(828, 311, 860, 339, "", 500);
                    break;
                case 30:
                    mFairy.onTap(680, 469, 707, 495, num + "个", 500);
                    mFairy.onTap(828, 311, 860, 339, "", 500);
                    break;
                case 60:
                    mFairy.onTap(678, 389, 705, 409, num + "个", 500);
                    mFairy.onTap(828, 311, 860, 339, "", 500);
                    break;
                case 99:
                    mFairy.onTap(825, 230, 876, 256, "最大", 1000);
                    break;
            }
            mFairy.onTap(0.8f, result1, "确定", 1000);

        } else {
            mFairy.onTap(676, 328, 713, 346, "设置数量", 1000);
        }

    }

    /**
     *  领奖>>> 福利 */
    int num = 0;int jia=0;

    public void ling() throws Exception {

        result = mFairy.findPic(1107,228,1212,335,new String[]{"home.png","home2.png","bao.png"});
        if (result.sim > 0.8f) {
            gamePublicFuntion.start();
            if(jia==0){
                result = mFairy.findPic(663,10,1079,107, "ling10.png");
                mFairy.onTap(0.8f, result, "特惠", 1000);

            }else {
                result = mFairy.findPic(666, 21, 1089, 111, "ling1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "福利", 1000);
                    num = 1;
                }
            }
        }

        result = mFairy.findPic("l.png");
        if(result.sim>0.8f) {
            mFairy.onTap(0.8f, result, 1198, 219, 1230, 248, "领取宝箱", 1000);
            mFairy.onTap(1213,105,1231,121,"关闭",1000);
            jia=1;
            return;
        }else{
            if(jia==0) {
                unexecuted();
            }
        }

        result = mFairy.findPic("ling.png");
        if (result.sim > 0.8f && num == 1) {

            for (int i = 0; i < 7; i++) {

                for (int p = 0; p < 3; p++) {
                    result = mFairy.findPic(801, 215, 1057, 475, "use.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "使用", 1000);
                        p = 0;
                    }
                    
                }

                mFairy.onTap(180, 150 + (i * 80), 185, 155 + (i * 80), "第" + (i + 1) + "个模块", 2000);


                result = mFairy.findPic("ling2.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "开服豪礼", 1000);
                    continue;
                }

                result = mFairy.findPic("ling9.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(802,156,981,443, "ling4.png");
                    mFairy.onTap(0.8f, result, "领取", 1000);
                    continue;
                }

                result = mFairy.findPic("ling8.png");
                if (result.sim > 0.95f) {
                    mFairy.onTap(0.95f, result, "抽奖", 1500);

                    for(int u =0;u<3;u++){
                        mFairy.onTap(463+(u*150),556,465+(u*150),558,"开工奖励",1000);
                    }

                    Thread.sleep(5000);
                    i--;
                    continue;
                }


                result = mFairy.findPic("ling3.png");
                if (result.sim > 0.8f) {
                    for (int o = 0; o < 2; o++) {
                        for (int j = 0; j < 4; j++) {
                            result = mFairy.findPic(794, 129, 935, 621, "ling4.png");
                            if (result.sim > 0.8f) {
                                mFairy.onTap(0.8f, result, "领取", 1000);
                                mFairy.onTap(1005,209,1024,226,"",1000);
                                j = 0;
                                o=0;
                            }
                            
                        }
                        mFairy.ranSwipe(744, 227, 791, 456, 2, 1000,(long)3000);
                    }
                    continue;
                }

                result = mFairy.findPic("ling5.png");
                if (result.sim > 0.8f) {

                    for (int j = 0; j < 3; j++) {
                        result = mFairy.findPic(291, 152, 984, 624, "ling6.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, result.x, result.y + 50, result.x + 5, result.y + 55, "领取", 1000);
                            j = 0;
                        }
                        
                    }
                    continue;
                }
            }


            LtLog.e(mFairy.getLineInfo("领奖任务完成"));
            list.remove(0);
            gamePublicFuntion.EndTask(this);
            num = 0;
            jia=0;
            return;

        } else {
            unexecuted();
        }
    }//领奖

    /**
     *  领奖>>> 活动 */
    public void linghd() throws Exception {

        result = mFairy.findPic(1107,228,1212,335,new String[]{"home.png","home2.png","bao.png"});
        if (result.sim > 0.8f) {
            gamePublicFuntion.start();

            result = mFairy.findPic(698, 14, 1092, 191, "a.png");
            mFairy.onTap(0.8f, result, "活动", 3000);
        }

        result = mFairy.findPic("activity.png");
        if (result.sim > 0.8f) {
            Thread.sleep(1000);
            for (int i = 0; i < 7; i++) {
                result = mFairy.findPic("activity1.png");
                mFairy.onTap(0.8f, result, 1001, 101, 1020, 123, "关闭活动介绍", 1000);

                for (int p = 0; p < 3; p++) {
                    
                    result = mFairy.findPic(801, 215, 1057, 475, "use.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "使用", 1000);
                        p = 0;
                    }
                }
                mFairy.onTap(464 + (i * 100), 600, 470 + (i * 100), 610, "点击第" + (i + 1) + "个", 500);
                mFairy.onTap(464 + (i * 100), 600, 470 + (i * 100), 610, "", 500);
                mFairy.onTap(464 + (i * 100), 600, 470 + (i * 100), 610, "", 500);
            }
            list.remove(0);
            gamePublicFuntion.EndTask(this);
            gamePublicFuntion.close();
        } else {
            unexecuted();
        }

    }//
}
