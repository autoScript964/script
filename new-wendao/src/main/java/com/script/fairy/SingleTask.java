package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;
import java.util.ArrayList;
import java.util.List;

import static com.script.opencvapi.AtFairy2.FAIRY_TYPE_TASK;
import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

/**
 * Created by Administrator on 2019/3/25 0025.
 */

public class SingleTask {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    Util util;
    List<String> list = new ArrayList<>();

    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        util = new Util(mFairy);
    }

    //单人日常
    FindResult dazeResult;
    public void singleDaily() throws Exception {
        //师门
        if (AtFairyConfig.getOption("sm").equals("1")) {
            list.add("master_task.png");
        }
        //随机师门
        if (AtFairyConfig.getOption("rndsm").equals("1")) {
            list.add("rndmaster_task");
        }
        //除暴
        if (AtFairyConfig.getOption("2582").equals("1")) {
            list.add("cbtask.png");
        }
        //帮派任务
        if (AtFairyConfig.getOption("bprw").equals("1")) {
            list.add("bprwtask.png");
        }
        //助人为乐
        if (AtFairyConfig.getOption("1106").equals("1")) {
            list.add("zrwltask.png");
        }

        //竞技场
        if (AtFairyConfig.getOption("2432").equals("1")) {
            list.add("jjctask.png");
        }
        //帮派日常挑战
        if (AtFairyConfig.getOption("2434").equals("1")) {
            list.add("bprctzxx.png");
        }

        if (list.size() == 0) {
            LtLog.e(mFairy.getLineInfo("没有任务了结束"));
            return;
        }

        int bj = 0;
        int cbCount = 0;
        int ret;
        int cbsb = 0,jsgm=0,sjsm=0;
        long dazeTime = 15;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("单人日常中bj==" + bj + "dazetime=" + dazeTime));
            LtLog.e(mFairy.getLineInfo("list===" + list.toString()));
            if (list.size() == 0) {
                LtLog.e(mFairy.getLineInfo("没有任务了结束"));
                return;
            }
            if (bj == 0) {
                result = mFairy.findPic(974, 91, 1280, 233, "right_task1.png");
                mFairy.onTap(0.9f, result, "右侧任务暗", 2000);

                if (list.get(0).equals("master_task.png")) {
                    dazeResult = mFairy.findPic(1018, 169, 1123, 517, "master.png");
                    mFairy.onTap(0.7f, dazeResult, "右侧任务栏" + list.get(0), 1000);

                    result = mFairy.findPic(766, 18, 1265, 702, "master1.png");
                    mFairy.onTap(0.8f, result, "对话栏师门", 1000);

                    result = mFairy.findPic(766, 18, 1265, 702, "rock.png");
                    mFairy.onTap(0.8f, result, "石头", 1000);
                    //看起来不错  离开
                    mFairy.findPic(766, 18, 1265, 702, 0.8f, new String[]{"look.png", "tmbl.png", "msyk.png", "yuanyi.png", "goaway.png"});

                    result = mFairy.findPic(766, 18, 1265, 702, "tieshui.png");
                    mFairy.onTap(0.8f, result, 991, 564, 1036, 581, "铸模", 1000);

                    result = mFairy.findPic(766, 18, 1265, 702, "zazhi.png");
                    mFairy.onTap(0.8f, result, 991, 634, 1032, 653, "锻造", 1000);

                    result = mFairy.findPic(766, 18, 1265, 702, "hx.png");
                    mFairy.onTap(0.8f, result, 988, 490, 1037, 511, "鼓风", 1000);

                    result = mFairy.findPic("donation1.png");
                    if (result.sim > 0.9f) {
                        for (int i = 0; i < 6; i++) {
                            result = mFairy.findPic("donation1.png");
                            mFairy.onTap(0.9f, result, "捐助", 4000);
                            mFairy.tap(753, 334);
                            Thread.sleep(1500);
                            mFairy.condit();
                        }
                        for (int i = 0; i < 6; i++) {
                            result = mFairy.findPic("donation1.png");
                            if (result.sim > 0.9f) {
                                mFairy.tap(521, 333);
                                mFairy.condit();
                            }
                        }
                    }
                }

                if (list.get(0).equals("rndmaster_task")) {
                    dazeResult = mFairy.findPic(1009,168,1260,520, "rndmaster.png");
                    mFairy.onTap(0.7f, dazeResult, "右侧任务栏" + list.get(0), 1000);
                    if (dazeResult.sim > 0.7f ) {
                        sjsm=0;
                    }else {
                        sjsm++;
                        if (sjsm>30){
                            sjsm=0;
                            list.remove(0);
                            continue;
                        }
                    }

                    result = mFairy.findPic(766, 18, 1265, 702, new String[]{"rndmaster1.png","rndmaster2.png","rndmaster3.png"});
                    mFairy.onTap(0.8f, result, "对话栏随机师门", 1000);

                    result = mFairy.findPic(766, 18, 1265, 702, "no_goods.png");
                    mFairy.onTap(0.8f, result, 962,488,1014,505,"没有物品去集市购买", 2000);

                    result = mFairy.findPic("market.png");
                    if (result.sim > 0.8f ) {
                        if (jsgm==0){
                            jsgm=1;
                            mFairy.onTap(0.8f, result, 473,211,500,235,"集市界面选第一个种类", 2000);
                            mFairy.onTap(0.8f, result, 582,176,617,196,"集市界面选第一个物品", 2000);
                            mFairy.onTap(0.8f, result, 1001,642,1046,662,"购买", 1000);
                            result = mFairy.findPic("NOmoney.png");
                            mFairy.onTap(0.8f, result, 501,419,559,441,"没有钱了", 2000);
                            if (result.sim > 0.8f) {
                                list.remove(0);
                                continue;
                            }
                            result = mFairy.findPic(419,452,888,683,"sure.png");
                            mFairy.onTap(0.8f, result, "确定", 1000);
                            util.close();
                        }
                        if (jsgm==1){
                            list.remove(0);
                            continue;
                        }
                    }
                }

                if (list.get(0).equals("cbtask.png")) {
                    if (AtFairyConfig.getOption("cbsb").equals("1") && cbsb == 0) {
                        util.receiveDouble();
                        cbsb = 1;
                    }
                    if (AtFairyConfig.getOption("cbsb").equals("") && cbsb == 0) {
                        util.closeDouble();
                        cbsb = 1;
                    }
                    dazeResult = mFairy.findPic(1010, 165, 1278, 512, "rwlwmcb.png");
                    if (dazeResult.sim > 0.7f) {
                        cbCount++;
                        if (cbCount > 40) {
                            list.remove(0);
                            continue;
                        }
                        mFairy.onTap(0.7f, dazeResult, "右侧任务栏" + list.get(0), 1000);
                    }

                    result = mFairy.findPic(766, 18, 1265, 702, "cbxx.png");
                    mFairy.onTap(0.8f, result, "除暴任务", 1000);
                }

                if (list.get(0).equals("bprwtask.png")) {
                    dazeResult = mFairy.findPic(1010, 165, 1278, 512, "rwlbprw.png");
                    mFairy.onTap(0.7f, dazeResult, "右侧任务栏" + list.get(0), 1000);

                    result = mFairy.findPic(766, 18, 1265, 702, "bprwxx.png");
                    mFairy.onTap(0.8f, result, "帮派任务", 1000);
                }

                if (list.get(0).equals("jjctask.png")) {
                    //只是为了给dazeResult赋值防止下面报空
                    dazeResult = mFairy.findPic(1010, 165, 1278, 512, "zero0.png");

                    result = mFairy.findPic(766, 18, 1265, 702, "jjcxx.png");
                    mFairy.onTap(0.8f, result, "竞技场", 1000);

                    result = mFairy.findPic("shuaxin.png");
                    mFairy.onTap(0.8f, result, "竞技场刷新", 1000);

                    result = mFairy.findPic(434, 506, 1095, 602, "tiaozhan.png");
                    mFairy.onTap(0.8f, result, "竞技场挑战", 1000);

                    result = mFairy.findPic(395, 232, 885, 482, "fqtz.png");
                    mFairy.onTap(0.8f, result, 716, 416, 783, 442, "发起挑战", 1000);

                    result = mFairy.findPic(965, 65, 1112, 136, "zero0.png");
                    if (result.sim > 0.95f) {
                        util.close();
                        list.remove(0);
                        continue;
                    }

                    //竞技场逃跑
                    result = mFairy.findPic("combat.png");
                    LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗场景"));
                    if (result.sim > 0.8f) {
                        if (AtFairyConfig.getOption("jjctp").equals("1")) {
                            result = mFairy.findPic("cancel_auto.png");
                            mFairy.onTap(0.8f, result, "取消自动战斗", 1000);

                            result = mFairy.findPic(533, 593, 1265, 714, "tp.png");
                            mFairy.onTap(0.8f, result, "逃跑了", 1000);

                            result = mFairy.findPic("defense.png");
                            if (result.sim > 0.8f) {
                                Thread.sleep(25000);
                            }
                        }
                    }
                }

                if (list.get(0).equals("zrwltask.png")) {
                    dazeResult = mFairy.findPic(1010, 165, 1278, 512, "rwlzrwl.png");
                    mFairy.onTap(0.7f, dazeResult, "右侧任务栏" + list.get(0), 1000);

                    if (AtFairyConfig.getOption("zrsd").equals("1")) {
                        result = mFairy.findPic(766, 18, 1265, 702, "saodang.png");
                        mFairy.onTap(0.8f, result, "扫荡助人为乐", 1000);
                    } else {
                        result = mFairy.findPic(766, 18, 1265, 702, "Ipleasure_receive_help.png");
                        mFairy.onTap(0.8f, result, "领取助人为乐", 1000);
                    }


                    result = mFairy.findPic(766, 18, 1265, 702, "wlxb.png");
                    LtLog.e(mFairy.getLineInfo(0.8f, result, "没有位列仙班 无法扫荡助人为乐"));
                    if (result.sim > 0.8f) {
                        //  listRemoveFinish();
                        list.remove(0);
                        continue;
                    }
                    result = mFairy.findPic(766, 18, 1265, 702, "give.png");
                    mFairy.onTap(0.8f, result, "怎么可怜给他吧", 1000);

                    result = mFairy.findPic(766, 18, 1265, 702, "yes_shouxia.png");
                    mFairy.onTap(0.8f, result, "是的你收下吧", 1000);

                    result = mFairy.findPic(766, 18, 1265, 702, "qiaozha.png");
                    mFairy.onTap(0.8f, result, "敲诈", 1000);

                    result = mFairy.findPic(766, 18, 1265, 702, "bangzhu.png");
                    mFairy.onTap(0.8f, result, "帮助", 1000);

                    result = mFairy.findPic(766, 18, 1265, 702, "zrwlxx.png");
                    if (result.sim>0.8f){
                        if (AtFairyConfig.getOption("zrjl").equals("1")) {
                            result = mFairy.findPic(766, 18, 1265, 702, "dhxx.png");
                            mFairy.onTap(0.8f, result, "道行奖励", 1000);
                        }

                        if (AtFairyConfig.getOption("zrjl").equals("2")) {
                            result = mFairy.findPic(766, 18, 1265, 702, "jyxx.png");
                            mFairy.onTap(0.8f, result, "经验奖励", 1000);
                        }
                        if (AtFairyConfig.getOption("zrjl").equals("3")) {
                            result = mFairy.findPic(766, 18, 1265, 702, "qnxx.png");
                            mFairy.onTap(0.8f, result, "潜能奖励", 1000);
                        }
                        result = mFairy.findPic(766, 18, 1265, 702, "zrwlxx.png");
                        mFairy.onTap(0.8f, result, "助人", 1000);
                    }



                    if (AtFairyConfig.getOption("zrjz").equals("1")) {
                        result = mFairy.findPic(766, 18, 1265, 702, "juan.png");
                        mFairy.onTap(0.8f, result, "捐助穷人奖励", 1000);

                        result = mFairy.findPic(395, 233, 883, 484, "jzsure.png");
                        mFairy.onTap(0.8f, result, 724, 422, 780, 440, "确定捐助", 1000);
                    } else {
                        result = mFairy.findPic(766, 18, 1265, 702, "unjuan.png");
                        mFairy.onTap(0.8f, result, "领取原有奖励", 1000);
                    }
                }

                if (list.get(0).equals("bprctzxx.png")) {
                    dazeResult = mFairy.findPic(1010, 165, 1278, 512, "rwlbprctz.png");
                    mFairy.onTap(0.7f, dazeResult, "右侧任务栏" + list.get(0), 1000);

                    if (AtFairyConfig.getOption("bprctzsd").equals("1")) {
                        result = mFairy.findPic(766, 18, 1265, 702, "sdbptz.png");
                        mFairy.onTap(0.8f, result, "扫荡帮派日常挑战", 1000);
                    } else {
                        result = mFairy.findPic(766, 18, 1265, 702, "yinbprctz.png");
                        mFairy.onTap(0.8f, result, "帮派日常挑战", 1000);
                    }
                    //前往挑战   继续挑战 重新挑战
                    mFairy.findPic(766, 18, 1265, 702, 0.8f, new String[]{"qwtz.png", "jxtz.png", "cxtz.png"});

                    result = mFairy.findPic(766, 18, 1265, 702, "goaway.png");
                    mFairy.onTap(0.8f, result, "离开", 1000);
                }
                result = mFairy.findPic(914, 617, 1103, 676, "cwshop.png");
                mFairy.onTap(0.8f, result, "宠物购买", 2000);
                if (result.sim > 0.8f) {
                    util.close();
                }
                result = mFairy.findPic(833, 594, 921, 680, "ydshop.png");
                mFairy.onTap(0.8f, result, "药店点卷", 1000);

                result = mFairy.findPic2(419,452,888,683,"tijiao.png");
                mFairy.onTap(0.8f, result, "提交", 1000);

                result = mFairy.findPic2(419,452,888,683,"zbtj.png");
                mFairy.onTap(0.8f, result, "装备提交", 1000);

                result = mFairy.findPic("sure.png");
                mFairy.onTap(0.8f, result, "提交确定", 1000);


                result = mFairy.findPic(564, 4, 708, 73, "activity1.png");
                LtLog.e(mFairy.getLineInfo(0.8f,result,"活动界面场景"));
                if (result.sim>0.8f){
                   util.close();
                }

                if (dazeTime >= 12) {
                    mFairy.initMatTime();
                    if (dazeResult.sim > 0.7f) {
                    } else {
                        bj = 1;
                    }
                }
                dazeTime = mFairy.mMatTime(24,537,20,14, 0.9f);
                if (util.combat() >= 30) {
                    LtLog.e(mFairy.getLineInfo("移除一个任务"));
                    list.remove(0);
                    if (list.size() == 0) {
                        LtLog.e(mFairy.getLineInfo("没有任务了结束"));
                        return;
                    }
                    bj=1;
                }
                result = mFairy.findPic("combat.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "战斗中"));
                if (result.sim > 0.8f) {
                    sjsm=0;
                    mFairy.initMatTime();
                }
            }
            if (bj == 1) {
                if (list.get(0).equals("rndmaster_task")) {
                    mFairy.initMatTime();
                    bj=0;
                    continue;
                }
                if (list.get(0).equals("cbtask.png")) {
                    if (AtFairyConfig.getOption("cbsb").equals("1") && cbsb == 0) {
                        util.receiveDouble();
                        cbsb = 1;
                    }
                    if (AtFairyConfig.getOption("cbsb").equals("") && cbsb == 0) {
                        util.closeDouble();
                        cbsb = 1;
                    }
                }
                ret = util.findTask("日常",list.get(0),"前往");
                if (ret == 1) {
                    Thread.sleep(5000);
                    mFairy.initMatTime();
                    dazeTime=0;
                    bj = 0;
                } else if (ret == 0) {
                    LtLog.e(mFairy.getLineInfo("移除一个任务"));
                    mFairy.initMatTime();
                    util.battleCount = 0;
                    list.remove(0);
                    if (list.size() == 0) {
                        LtLog.e(mFairy.getLineInfo("没有任务了结束"));
                        return;
                    }
                }
            }
        }
    }

    //新手
    int snakeCount = 0;//打蟒精失败计次
    public void novice() throws Exception {
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("新手引导中"));
            result = mFairy.findPic(4, 7, 1269, 709, "click_here.png");
            if (result.sim > 0.8f) {
                LtLog.e(mFairy.getLineInfo("指引点击"));
                mFairy.tap(result.x - 75, result.y + 25);
                mFairy.tap(result.x + 231, result.y + 23);
                mFairy.tap(result.x + 248, result.y + 98);
            }
            result = mFairy.findPic("open_list_right.png");
            mFairy.onTap(0.8f, result, "打开右侧列表", 1000);

            result = mFairy.findPic("open.png");
            mFairy.onTap(0.8f, result, 253, 25, 267, 39, "打开活动", 1000);

            result = mFairy.findPic(1008, 117, 1218, 271, "need_upgrade.png");
            LtLog.e(mFairy.getLineInfo(0.7f, result, "努力修炼结束"));
            if (result.sim > 0.7f) {
                return;
            }

            result = mFairy.findPic(new String[]{"nn1.png"});
            mFairy.onTap(0.8f, result, 252,29,277,59,"点击活动", 1000);


            result = mFairy.findPic(997, 120, 1276, 314, "snake.png");
            LtLog.e(mFairy.getLineInfo(0.7f, result, "铲除莽精snakeCount=" + snakeCount));
            if (result.sim > 0.7f) {
                snakeCount++;
                if (snakeCount > 3) {
                    return;
                }
            }
            result = mFairy.findPic(1008, 117, 1218, 271, "thread.png");
            mFairy.onTap(0.7f, result, "主线任务", 1000);

            result = mFairy.findPic("upgrade.png");
            mFairy.onTap(0.8f, result, "升级10次", 1000);
            mFairy.onTap(0.8f, result, "升级10次", 1000);
            mFairy.onTap(0.8f, result, 1075, 33, 1090, 47, "角色打开界面关闭", 1000);


            result = mFairy.findPic(777, 436, 1254, 677, "thread1.png");
            mFairy.onTap(0.7f, result, "右侧主线任务1", 1000);

            result = mFairy.findPic(780, 323, 1259, 697, "master1.png");
            mFairy.onTap(0.8f, result, "师门", 1000);


            result = mFairy.findPic(432, 6, 849, 134, "select_PS.png");
            LtLog.e(mFairy.getLineInfo(0.8f, result, "选择模式"));
            mFairy.onTap(0.8f, result, 343, 329, 395, 349, "自定义", 1);
            mFairy.onTap(0.8f, result, 738, 601, 804, 616, "确定选择", 1);


            result = mFairy.findPic(393, 236, 892, 484, "sure.png");
            mFairy.onTap(0.8f, result, "确定", 1000);

            result = mFairy.findPic(473, 530, 832, 648, "tijiao.png");
            mFairy.onTap(0.8f, result, "提交", 1000);

            result = mFairy.findPic(522, 539, 754, 623, "receive.png");
            mFairy.onTap(0.8f, result, "领取", 1000);

            result = mFairy.findPic("adding_point.png");
            mFairy.onTap(0.8f, result, "自动加点", 1000);
            mFairy.onTap(0.8f, result, 467, 591, 492, 598, "自动加点", 1000);
            mFairy.onTap(0.8f, result, 733, 588, 774, 604, "自动加点", 1000);
            mFairy.onTap(0.8f, result, 974, 644, 1012, 659, "自动加点", 1000);
            mFairy.onTap(0.8f, result, 1075, 33, 1090, 47, "角色打开界面关闭", 1000);

            util.closeCount();
        }
    }

    //测试
    public void ceshi() throws Exception {
        while (mFairy.condit()) {
            Thread.sleep(2000);
            if (AtFairyConfig.getOption("ymd").equals("1")){
                dazeResult = mFairy.findPic(1018, 169, 1123, 517, "yaomodao.png");
                mFairy.onTap(0.7f, dazeResult, "右侧任务栏妖魔道", 1000);
            }
            if (AtFairyConfig.getOption("xml").equals("1")){
                dazeResult = mFairy.findPic(1018, 169, 1123, 517, "xianmolu.png");
                mFairy.onTap(0.7f, dazeResult, "右侧任务仙魔录", 1000);
            }

            if (AtFairyConfig.getOption("zy").equals("1")){
                dazeResult = mFairy.findPic(1018, 169, 1123, 517, "zhiyin.png");
                mFairy.onTap(0.7f, dazeResult, "右侧任务指引", 1000);
            }
            if (AtFairyConfig.getOption("ll").equals("1")){
                dazeResult = mFairy.findPic(1018, 169, 1123, 517, "lilian.png");
                mFairy.onTap(0.7f, dazeResult, "右侧任务历练", 1000);
            }

        }
    }


}
