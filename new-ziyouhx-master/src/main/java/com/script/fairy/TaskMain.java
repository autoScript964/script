package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;


/**
 * Created by Administrator on 2019/1/24 0024.
 */

public class TaskMain extends TaskContent {
    AtFairyImpl mFairy;
    GameUtil gameUtil;
    TeamTask teamTask;
    SingleTask singleTask;
    FindResult result;
    LimitlessTask limitlessTask;
    TimingActivity timingActivity;
    OtherGame otherGame;
    int w;
    int h;
    int m;
    static List<String> list = new ArrayList<>();
    public TaskMain(AtFairyImpl ATFairy) throws Exception {
        mFairy = ATFairy;
        mFairy.setGameName("新自由幻想");
        mFairy.setGameVersion(719);
        init();
        gameUtil = new GameUtil(mFairy);
        teamTask = new TeamTask(mFairy);
        singleTask = new SingleTask(mFairy);
        limitlessTask = new LimitlessTask(mFairy);
        timingActivity = new TimingActivity(mFairy);
        otherGame = new OtherGame(mFairy);
        mFairy.initMatTime();
        list.clear();
    }

    public void main() throws Exception {
        if (!AtFairyConfig.getOption("task_id").equals("")) {
            task_id = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }

        if (task_id != 2029 && task_id != 100) {
            timingActivity.timingActivity();
            gameUtil.lkfb();
            /*result = mFairy.findPic(new String[]{"mapinface.png", "mapinface1.png"});
            mFairy.onTap(1194,97,1201,117,"关闭地图界面", Sleep);*/
            gameUtil.setUp();
        }

        qh_init();

        switch (task_id) {
            //单人任务
            case 2023:
                while (mFairy.condit()) {

                    boolean re = false;

                    if (AtFairyConfig.getOption("5579").equals("1")) {
                        gameUtil.setjn();
                    }

                    if (AtFairyConfig.getOption("5583").equals("1")) {
                        gameUtil.clearBag();
                    }
                    //领邮件
                    if (AtFairyConfig.getOption("singlelq").equals("1")) {

                        gameUtil.lyj();
                    }
                    if (AtFairyConfig.getOption("5583").equals("1")) {
                        gameUtil.clearBag();
                    }
                    //使用活力
                    if (AtFairyConfig.getOption("5585").equals("1")) {
                        gameUtil.syhl();
                    }
                    //使用药品
                    if (AtFairyConfig.getOption("5587").equals("1")) {
                        gameUtil.syyp();
                    }

                    gameUtil.cancelFollowing();

                    //签到
                    if (AtFairyConfig.getOption("singleqd").equals("1")) {
                        gameUtil.qiandao();
                    }

                    //轻松玩
                    if(AtFairyConfig.getOption("qsw").equals("1")){
                        singleTask.qsw();
                    }

                    //领取回响育儿
                    if (AtFairyConfig.getOption("lqhx").equals("1")) {
                        singleTask.lqhx();
                    }

                    //每日饲料
                    if (AtFairyConfig.getOption("mrsl").equals("1")) {
                        gameUtil.mrsl();
                    }

                    //幻想宾果
                    if (AtFairyConfig.getOption("hxbg").equals("1")) {
                        singleTask.hxbg();
                    }
                    //开工福利
                    if (AtFairyConfig.getOption("kgfl").equals("1") && (mFairy.week() == 1 || mFairy.week() == 2)) {
                        singleTask.kgfl();
                    }
                    //大师兄投票
                    if (AtFairyConfig.getOption("dsxtp").equals("1") && (mFairy.week() == 7 || mFairy.week() == 1)) {
                        singleTask.dsxtp();
                    }
                    //领地战投票
                    if (AtFairyConfig.getOption("ldztp").equals("1") && (mFairy.week() != 6 && mFairy.week() != 7)) {
                        singleTask.ldztp();
                    }
                    //宝图
                    if (AtFairyConfig.getOption("bt").equals("1")) {
                        singleTask.baotu();
                    }
                    //门派挑战
                    if (AtFairyConfig.getOption("mptz").equals("1")) {
                        singleTask.mptz();
                    }
                    //仙缘任务
                    if (AtFairyConfig.getOption("xyrw").equals("1")) {
                        singleTask.xyrw();
                    }
                    //家族任务
                    if (AtFairyConfig.getOption("jzrw").equals("1")) {
                        singleTask.jzrw();
                    }
                    //家族炼器
                    if (AtFairyConfig.getOption("jzlq").equals("1")) {
                        singleTask.jzlq();
                    }
                    //灵气任务
                    if (AtFairyConfig.getOption("lqrw").equals("1")) {
                        singleTask.lqrw();
                    }
                    //家族矿洞
                    if (AtFairyConfig.getOption("jzwk").equals("1") && (mFairy.week() == 1 || mFairy.week() == 3 || mFairy.week() == 5) && ((mFairy.dateHour() >= 12 && mFairy.dateMinute() >=30) || mFairy.dateHour() >=13)) {
                        timingActivity.jzkd();
                    }
                   /* //家族温泉
                    if (AtFairyConfig.getOption("jzwq").equals("1")) {
                        timingActivity.jzwq();
                    }*/
                    //法器护送
                    if (AtFairyConfig.getOption("fqhs").equals("1") && (mFairy.week() == 2 || mFairy.week() == 4 || mFairy.week() == 6) && ((mFairy.dateHour() >= 12 && mFairy.dateMinute() >=30) || mFairy.dateHour() >=13)) {
                        timingActivity.fqhs();
                    }
                    //家谱活跃奖励
                    if (AtFairyConfig.getOption("jpjl").equals("1")) {
                        gameUtil.jpjl();
                    }
                    //使用宝图
                    if (AtFairyConfig.getOption("5581").equals("1")) {
                        singleTask.wabaotu();
                    }
                    if (AtFairyConfig.getOption("xyfb").equals("1")) {
                        teamTask.xyfb();
                    }


                    w = mFairy.week();
                    h = mFairy.dateHour();
                    m = mFairy.dateMinute();

                    if (AtFairyConfig.getOption("jzjh").equals("1") && h == 19 && (mFairy.dateMinute() >15 && mFairy.dateMinute() <25)) {
                        gameUtil.lkfb();
                        gameUtil.cancelFollowing();
                        if (w == 6 || w == 7) {
                            timingActivity.qh_jzwh();
                        } else {
                            timingActivity.qh_jzjh();
                        }
                    }


                    w = mFairy.week();
                    h = mFairy.dateHour();
                    m = mFairy.dateMinute();
                    int s = (h * 60 + m) * 60;

                    if (AtFairyConfig.getOption("kjxs").equals("1") && w != 6 && w != 7 && ((h == 19 && m>5 ) || (h>=20 && h < 23))) {
                        gameUtil.lkfb();
                        gameUtil.cancelFollowing();
                        timingActivity.kjxs();
                    }


                    w = mFairy.week();
                    h = mFairy.dateHour();
                    m = mFairy.dateMinute();

                    if (!AtFairyConfig.getOption("ryjjc").equals("") && (w == 1 || w == 3) && h == 20 && m < 30) {
                        gameUtil.lkfb();
                        timingActivity.ryjjc();
                    }



                    if (AtFairyConfig.getOption("cfjj").equals("1") && w == 4 &&( h==12 && m>=30) || ( h==20 && m<30)) {
                        gameUtil.lkfb();
                        gameUtil.cancelFollowing();
                        timingActivity.cfjj();
                    }

/*                    if (AtFairyConfig.getOption("pwzz").equals("1") && w == 1 && h==20) {
                        gameUtil.lkfb();
                        gameUtil.cancelFollowing();
                        timingActivity.pwzz();
                    }*/
                    singleTask.mzrw();//每周任务

                    if (AtFairyConfig.getOption("jzwq").equals("1")) {
                        //   LtLog.e(mFairy.getLineInfo("家族温泉"));

                        gameUtil.cancelFollowing();
                        timingActivity.jzwq();
                        gameUtil.lkfb();
                    }

                    if (AtFairyConfig.getOption("jshjz").equals("1")) {
                        singleTask.drhjz();
                    }

                    if(AtFairyConfig.getOption("qhjs0").equals("1")){
                        if(xhqh>=user_xhqh){
                            re = true;
                        }else{
                            gameUtil.newqiehuanjuese();
                            if(xhqh>=user_xhqh){
                                re =true;
                            }
                        }
                    }else {
                        int index = qh_bool();
                        if (index != 0) {
                            if (gameUtil.qiehuanjuese(index)) {
                                re = true;
                            }
                        } else {
                            re = true;
                        }
                    }

                    if(re){
                        if(AtFairyConfig.getOption("re0").equals("1")){
                            LtLog.e(mFairy.getLineInfo("开始等待重启"));
                            while (mFairy.condit()){
                                int hour = mFairy.dateHour();
                                int minute = mFairy.dateMinute();
                                if((hour==24 || hour==0) && minute<2){
                                    qh_init();
                                    break;
                                }
                            }
                        }else{
                            break;
                        }
                    }
                }
                break;
            case 2029:
                singleTask.novice();
                break;
            //主线任务
            case 2027:
                singleTask.novice();
                break;
            //野外挂机
            case 2025:
                timingActivity.timingActivity();
                if (AtFairyConfig.getOption("5597").equals("1")) {
                    gameUtil.setjn();
                }
                if (AtFairyConfig.getOption("5613").equals("1")) {
                    limitlessTask.goFishing();
                }
                limitlessTask.fieldHangMachine();
                break;

            //洗刷刷
            case 2033:
                gameUtil.setjn();
                //	召集结拜
                if (AtFairyConfig.getOption("zjjb").equals("1")) {
                    gameUtil.zhjb();
                }
                //带队
                if (AtFairyConfig.getOption("dw").equals("1")) {
                    teamTask.jyxss();
                }
                //跟队
                if (AtFairyConfig.getOption("dw").equals("2")) {
                    teamTask.gdjyxss();
                }
                //固定队带队
                if (AtFairyConfig.getOption("dw").equals("3")) {
                    teamTask.jyxss1();
                }
                break;

            //放逐之地
            case 2039:
                gameUtil.setjn();
                if (AtFairyConfig.getOption("zhjb").equals("1")) {
                    gameUtil.zhjb();
                }
                //otherGame.fzzd();
                break;

            //恶魔令
            case 2037:
                gameUtil.setjn();
                if (AtFairyConfig.getOption("zhjb").equals("1")) {
                    gameUtil.zhjb();
                }
                otherGame.eml();
                break;
            //踩一踩
            case 2035:

                if (AtFairyConfig.getOption("phb").equals("1") || AtFairyConfig.getOption("fzph").equals("1")) {
                    singleTask.rankingList(0,100);
                }

                if (AtFairyConfig.getOption("sj").equals("1")) {
                    singleTask.rankingList1();
                }

                break;
            //侠义副本
            case 2041:
                teamTask.xyfb();
                break;
            //副本任务
            case 2031:
                gameUtil.setjn();
                gameUtil.zhjb();
                if (AtFairyConfig.getOption("xtd").equals("1")) {
                    gameUtil.retire();
                }
                //	召集结拜
                if (AtFairyConfig.getOption("zjjb").equals("1")) {
                    gameUtil.zhjb();
                }
                gameUtil.goCity("龙城");
                if (AtFairyConfig.getOption("dw").equals("1")) {
                    if (AtFairyConfig.getOption("fb1").equals("1")) {
                        teamTask.dlfb("CybtPTtask.png", "ptcybt1.png", "ptcybt.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb2").equals("1")) {
                        teamTask.dlfb("CybtTZtask.png", "tzcybt1.png", "tzcybt.png", 1);
                    }
                    if (AtFairyConfig.getOption("fb3").equals("1")) {
                        teamTask.dlfb("TkzqPTtask.png", "pttkzq1.png", "pttkzq.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb4").equals("1")) {
                        teamTask.dlfb("TkzqTZtask.png", "tztkzq1.png", "tztkzq.png", 1);
                    }
                    if (AtFairyConfig.getOption("fb5").equals("1")) {
                        teamTask.dlfb("TqzyPTtask.png", "pttqzy1.png", "pttqzy.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb6").equals("1")) {
                        teamTask.dlfb("TqzyTZtask.png", "tztqzy1.png", "tztqzy.png", 1);
                    }
                    if (AtFairyConfig.getOption("fb7").equals("1")) {
                        teamTask.dlfb("SlsdPTtask.png", "ptslsd1.png", "ptslsd.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb8").equals("1")) {
                        teamTask.dlfb("SlsdTZtask.png", "tzslsd1.png", "tzslsd.png", 1);
                    }
                    if (AtFairyConfig.getOption("fb9").equals("1")) {
                        teamTask.dlfb("YycPTtask.png", "ptyyc1.png", "ptyyc.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb10").equals("1")) {
                        teamTask.dlfb("YycTZtask.png", "tzyyc1.png", "tzyyc.png", 1);
                    }
                    if (AtFairyConfig.getOption("fb11").equals("1")) {
                        teamTask.dlfb("HycxPTtask.png", "pthycx1.png", "pthycx.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb12").equals("1")) {
                        teamTask.dlfb("HycxTZtask.png", "tzhycx1.png", "tzhycx.png", 1);
                    }
                    if (AtFairyConfig.getOption("fb13").equals("1")) {
                        teamTask.dlfb("MwzzPTtask.png", "ptmwzz1.png", "ptmwzz.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb14").equals("1")) {
                        teamTask.dlfb("MwzzTZtask.png", "tzmwzz1.png", "tzmwzz.png", 1);
                    }
                    if (AtFairyConfig.getOption("fb15").equals("1")) {
                        teamTask.dlfb("lzdtask.png", "ptlzd1.png", "ptlzd.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb16").equals("1")) {
                        teamTask.dlfb("lzdtztask.png", "tzlzd1.png", "tzldz.png", 1);
                    }
                    if (AtFairyConfig.getOption("fb17").equals("1")) {
                        teamTask.dlfb("ptqystask.png", "ptqys1.png", "ptqys.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb19").equals("1")) {
                        teamTask.dlfb("ptklxtask.png", "ptklx1.png", "ptklx.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb21").equals("1")) {
                        teamTask.dlfb("ptbhsltask.png", "ptbhsl1.png", "ptbhsl.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb22").equals("1")) {
                        teamTask.dlfb("tzbhsltask.png", "tzbhsl1.png", "tzbhsl.png", 1);
                    }
                    if (AtFairyConfig.getOption("fb23").equals("1")) {
                        teamTask.dlfb("ptslgtask.png", "ptslg1.png", "ptslg.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb24").equals("1")) {
                        teamTask.dlfb("tzslgtask.png", "tzslg1.png", "tzslg.png", 1);
                    }
                    if (AtFairyConfig.getOption("fb2").equals("1")) {
                        teamTask.dlfb("CybtTZtask.png", "tzcybt1.png", "tzcybt.png", 1);
                    }
                    if (AtFairyConfig.getOption("fb4").equals("1")) {
                        teamTask.dlfb("TkzqTZtask.png", "tztkzq1.png", "tztkzq.png", 1);
                    }
                    if (AtFairyConfig.getOption("fb6").equals("1")) {
                        teamTask.dlfb("TqzyTZtask.png", "tztqzy1.png", "tztqzy.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb8").equals("1")) {
                        teamTask.dlfb("SlsdTZtask.png", "tzslsd1.png", "tzslsd.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb10").equals("1")) {
                        teamTask.dlfb("YycTZtask.png", "tzyyc1.png", "tzyyc.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb12").equals("1")) {
                        teamTask.dlfb("HycxTZtask.png", "tzhycx1.png", "tzhycx.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb14").equals("1")) {
                        teamTask.dlfb("MwzzTZtask.png", "tzmwzz1.png", "tzmwzz.png", 1);
                    }
                }
                if (AtFairyConfig.getOption("dw").equals("2")) {
                    if (AtFairyConfig.getOption("fb1").equals("1")) {
                        teamTask.gdfb("CybtPTtask.png", "ptcybt1.png", "ptcybt.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb3").equals("1")) {
                        teamTask.gdfb("TkzqPTtask.png", "pttkzq1.png", "pttkzq.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb5").equals("1")) {
                        teamTask.gdfb("TqzyPTtask.png", "pttqzy1.png", "pttqzy.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb7").equals("1")) {
                        teamTask.gdfb("SlsdPTtask.png", "ptslsd1.png", "ptslsd.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb9").equals("1")) {
                        teamTask.gdfb("YycPTtask.png", "ptyyc1.png", "ptyyc.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb11").equals("1")) {
                        teamTask.gdfb("HycxPTtask.png", "pthycx1.png", "pthycx.png", 0);
                    }

                    if (AtFairyConfig.getOption("fb13").equals("1")) {
                        teamTask.gdfb("MwzzPTtask.png", "ptmwzz1.png", "ptmwzz.png", 1);
                    }
                    if (AtFairyConfig.getOption("fb15").equals("1")) {
                        teamTask.gdfb("lzdtask.png", "ptlzd1.png", "ptlzd.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb17").equals("1")) {
                        teamTask.gdfb("ptqystask.png", "ptqys1.png", "ptqys.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb19").equals("1")) {
                        teamTask.gdfb("ptklxtask.png", "ptklx1.png", "ptklx.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb21").equals("1")) {
                        teamTask.gdfb("ptbhsltask.png", "ptbhsl1.png", "ptbhsl.png", 0);
                    }
                    if (AtFairyConfig.getOption("fb23").equals("1")) {
                        teamTask.gdfb("ptslgtask.png", "ptslg1.png", "ptslg.png", 0);
                    }


                    if (AtFairyConfig.getOption("fb2").equals("1")) {
                        teamTask.gdfb("CybtTZtask.png", "tzcybt1.png", "tzcybt.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb4").equals("1")) {
                        teamTask.gdfb("TkzqTZtask.png", "tztkzq1.png", "tztkzq.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb6").equals("1")) {
                        teamTask.gdfb("TqzyTZtask.png", "tztqzy1.png", "tztqzy.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb8").equals("1")) {
                        teamTask.gdfb("SlsdTZtask.png", "tzslsd1.png", "tzslsd.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb10").equals("1")) {
                        teamTask.gdfb("YycTZtask.png", "tzyyc1.png", "tzyyc.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb12").equals("1")) {
                        teamTask.gdfb("HycxTZtask.png", "tzhycx1.png", "tzhycx.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb14").equals("1")) {
                        teamTask.gdfb("MwzzTZtask.png", "tzmwzz1.png", "tzmwzz.png", 0);
                    }

                    if (AtFairyConfig.getOption("fb16").equals("1")) {
                        teamTask.gdfb("lzdtztask.png", "tzlzd1.png", "tzlzd.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb18").equals("1")) {
                        teamTask.gdfb("tzqystask.png", "tzqys1.png", "tzqys.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb20").equals("1")) {
                        teamTask.gdfb("tzklxtask.png", "tzklx1.png", "tzklx.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb22").equals("1")) {
                        teamTask.gdfb("tzbhsltask.png", "tzbhsl1.png", "tzbhsl.png", 1);
                    }

                    if (AtFairyConfig.getOption("fb24").equals("1")) {
                        teamTask.gdfb("tzslgtask.png", "tzslg1.png", "tzslg.png", 1);
                    }
                }
                break;

            //测试
            case 100:
                singleTask.test();
                break;

        }
        mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
        Thread.sleep(2000);
    }

    private int task_id;

    public static int qh1 = 0, qh2 = 0, qh3 = 0, qh4 = 0, qh5 = 0;

    public static int xhqh = 0;
    public int user_xhqh = 0;


    public int getNumberAssembly(String string) throws Exception {
        if(string.equals("")){
            return -1;
        }

        String[] arrstr = string.split("\\|\\|");
        if (arrstr.length == 1) {
            if(arrstr[0].equals("0")){
                return -1;
            }
            return Integer.parseInt(arrstr[0]);
        } else {
            if(!arrstr[0].equals("1")){
                return -1;
            }
            return Integer.parseInt(arrstr[1]);
        }
    }

    public void qh_init() throws Exception {
        xhqh = 0;
        if (AtFairyConfig.getOption("qhjs0").equals("1")) {
            user_xhqh = getNumberAssembly(AtFairyConfig.getOption("new_qh"));
            LtLog.e(mFairy.getLineInfo("用户勾选循环切换角色："+user_xhqh));
            return;
        }

        qh1 = 0;
        qh2 = 0;
        qh3 = 0;
        qh4 = 0;
        qh5 = 0;

        if (AtFairyConfig.getOption("qhjs1").equals("1")) {
            qh1 = 1;
        }
        if (AtFairyConfig.getOption("qhjs2").equals("1")) {
            qh2 = 1;
        }
        if (AtFairyConfig.getOption("qhjs3").equals("1")) {
            qh3 = 1;
        }
        if (AtFairyConfig.getOption("qhjs4").equals("1")) {
            qh4 = 1;
        }
        if (AtFairyConfig.getOption("qhjs5").equals("1")) {
            qh5 = 1;
        }
    }//切换角色

    public int qh_bool() throws Exception {

        if (qh1 == 1) {
            return 1;
        }
        if (qh2 == 1) {
            return 2;
        }
        if (qh3 == 1) {
            return 3;
        }
        if (qh4 == 1) {
            return 4;
        }
        if (qh5 == 1) {
            return 5;
        }
        return 0;
    }//切换

    public void init() throws Exception {
        task_id = 0;
        try {
            JSONObject optionJson = new JSONObject(AtFairyConfig.getUserTaskConfig());
            LtLog.e(mFairy.getLineInfo("选项列表" + optionJson));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!AtFairyConfig.getOption("task_id").equals("")) {
            task_id = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }
    }

}
