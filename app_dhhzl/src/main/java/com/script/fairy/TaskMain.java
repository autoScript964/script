package com.script.fairy;

import com.script.content.ScProxy;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class TaskMain extends TaskContent {
    AtFairyImpl mFairy;
    SingleTask singleTask;
    FindResult result;
    JSONObject optionJson = null;
    GamePublicFuntion gamePublicFunction;
    LimitlessTask limitlessTask;
    TeamTask teamTask;

    private int taskId = 0;

    public static boolean SHANGHUI = true;
    public static boolean XUNHANG = true;
    public static boolean actitityInitSildeSwitch = true;
    public static List<Integer> qhList;
    public static boolean qhBool = false;
    public static boolean rankBool = true;//组队状态

    public TaskMain(AtFairyImpl ypFairy) throws Exception {
        super(ypFairy);
        singleTask = new SingleTask(ypFairy);
        gamePublicFunction = new GamePublicFuntion(ypFairy);
        limitlessTask = new LimitlessTask(ypFairy);
        teamTask = new TeamTask(ypFairy);
        mFairy = ypFairy;
        mFairy.setGameName("大航海之路");
        mFairy.setGameVersion(329);
        qhBool = false;
        SHANGHUI = true;
        rankBool = true;
        init();
    }

    private int week;
    private int hour;
    private int minute;

    public void main() throws Exception {
        ScProxy.config().Level().capturing(5);

        taskStartTime(AtFairyConfig.getOption("start_time"));

        if(!(taskId==2564 || taskId==2566)) {
            singleTask.setUp();
        }

        setQhList();

        switch (taskId) {
            case 2719:
                singleTask.guojia();//国家任务
                break;
            case 2550:
                while (mFairy.condit()) {

                    singleTask.tuidui();

                    /*if (AtFairyConfig.getOption("fle").equals("1")) {*/
                    singleTask.fle();//芙洛儿的烦恼
                    //}

                    if (AtFairyConfig.getOption("guojia").equals("1")) {
                        singleTask.guojia();//国家任务
                    }

                    if (AtFairyConfig.getOption("8130").equals("1") && SHANGHUI) {
                        singleTask.shanghui();//商会委托
                    }

                    if (AtFairyConfig.getOption("8000").equals("1")) {
                        singleTask.touzi();//投资任务
                    }

                    if (AtFairyConfig.getOption("wujin").equals("1")) {
                        singleTask.wujin();//无尽的航路
                    }

                    if (AtFairyConfig.getOption("wenda").equals("1")) {
                        if (mFairy.week() != 1) {
                            singleTask.wenda();//航海问答
                        }
                    }

                    if (AtFairyConfig.getOption("bssxxs").equals("1")) {
                        if (mFairy.week() == 1 && mFairy.dateHour() >= 5 && mFairy.dateHour() < 23) {
                            singleTask.bssxxs();//比萨首席学士
                        }
                    }

                    if (AtFairyConfig.getOption("shangtuan").equals("1") && SHANGHUI) {
                        singleTask.shangtuan();//商团贸易
                    }

                    if (AtFairyConfig.getOption("baozang").equals("1")) {
                        singleTask.baozang();//宝藏委托
                    }

                    if(AtFairyConfig.getOption("8006").equals("1")) {
                        if (AtFairyConfig.getOption("banv").equals("1")) {
                            singleTask.banv();//吧女送礼
                        } else {
                            singleTask.rny("sdgem1.png", "sdgem.png");
                            singleTask.banvs();
                            singleTask.rny("rny1.png", "rny3.png");
                        }
                    }

                    if (AtFairyConfig.getOption("slsbw").equals("1")) {
                        singleTask.slsbw();//色雷斯捕网
                    }

                    if (AtFairyConfig.getOption("ts").equals("1")) {
                        singleTask.ts();//消耗探索点
                    }

                    if (AtFairyConfig.getOption("tp").equals("1")) {
                        if ((mFairy.week() == 5 && mFairy.dateHour() >= 12) || mFairy.week() > 5) {
                            singleTask.tp();//首席大臣竞选
                        }
                    }


                    if (AtFairyConfig.getOption("shcj").equals("1")) {
                        singleTask.shcj();//商会抽奖
                    }

                    if (AtFairyConfig.getOption("8004").equals("1")) {
                        singleTask.xg();//限购
                    }

                    if (AtFairyConfig.getOption("zb").equals("1")) {
                        singleTask.zb();//占卜
                    }

                    if (AtFairyConfig.getOption("qd").equals("1")) {
                        singleTask.qd();//签到
                    }

                    if(AtFairyConfig.getOption("hhj").equals("1")){
                        singleTask.hhj();//航海纪
                    }

                    if (AtFairyConfig.getOption("8008").equals("1")) {
                        singleTask.jyzh();//经验找回
                    }

                    if (AtFairyConfig.getOption("end").equals("1")) {
                        singleTask.rny("rny1.png","rny3.png");//飞热亚
                    }

                    if (AtFairyConfig.getOption("shwtqz").equals("1")&& SHANGHUI) {
                        singleTask.shanghui();//商会委托
                    }

                    if (qhList.size() != 0) {
                        qhBool = true;
                        singleTask.qh();//执行切换角色
                        singleTask.setUp();
                        qhBool = false;
                    } else {
                        break;
                    }
                }
                break;
            case 2613:
                singleTask.ktgps();
                break;
            case 2552:
                int week = mFairy.week();
                boolean home = false;
                singleTask.rny("rny1.png", "rny3.png");

                if(AtFairyConfig.getOption("rankd").equals("1")) {

                    if (AtFairyConfig.getOption("xljd").equals("1")) {
                        teamTask.xljd();//巡逻舰队
                        singleTask.map(false, "rny4.png");
                        gamePublicFunction.init(false);
                        gamePublicFunction.lgs();
                    }
                    if (AtFairyConfig.getOption("hjsl").equals("1")) {
                        teamTask.hjsl();//海军试炼
                    }
                    if (AtFairyConfig.getOption("txbfj").equals("1")) {
                        teamTask.txbfj();//突袭暴风角
                    }
                    if (AtFairyConfig.getOption("jjnlh").equals("1")) {
                        teamTask.jjnlh();//进军尼罗河
                    }
                    if (AtFairyConfig.getOption("8012").equals("1")) {
                        teamTask.bztz();//霸主挑战
                    }

                    if (AtFairyConfig.getOption("bf").equals("1") && (week != 3 || week != 4)) {
                        home = true;
                        teamTask.bfjd();//北非舰队
                    }

                    if (AtFairyConfig.getOption("8014").equals("1")) {
                        home = true;
                        teamTask.jwjd();//近卫舰队
                    }

                    if (home) {
                        singleTask.map(false, "rny4.png");
                        gamePublicFunction.init(false);
                        gamePublicFunction.lgs();
                    }

                }else{

                    ArrayList<String> list;

                    list=new ArrayList<>();

                    if(AtFairyConfig.getOption("xljd").equals("1")){
                        list.add("xljd.png");
                    }
                    if(AtFairyConfig.getOption("hjsl").equals("1")){
                        list.add("hjsl.png");
                    }
                    if(AtFairyConfig.getOption("txbfj").equals("1")){
                        list.add("txbfj.png");
                    }
                    if(AtFairyConfig.getOption("jjnlh").equals("1")){
                        list.add("jjnlh.png");
                    }
                    if(AtFairyConfig.getOption("8012").equals("1")){
                        list.add("bztz.png");
                    }
                    if (AtFairyConfig.getOption("bf").equals("1") && (week != 3 || week != 4)) {
                        list.add("bf.png");
                    }

                    if(AtFairyConfig.getOption("8014").equals("1")){
                        list.add("jw.png");
                    }

                    while (mFairy.condit()){

                            if(list.size()==0){
                            break;
                        }

                        singleTask.gdfb(list.get(0));

                        list.remove(0);
                    }

                    singleTask.rny("rny1.png", "rny3.png");
                    gamePublicFunction.init(false);
                }

                break;
            case 2560:
                singleTask.js();//竞速活动
                break;
            case 2558:
                singleTask.mshy();
                break;
            case 2556:
                singleTask.ttmy();
                break;
            case 2554:
                singleTask.zbtf();//追捕逃犯
                break;
            case 2564:
                singleTask.gd();
                break;
            case 2562:
                singleTask.ps();
                break;
            case 2566:
                long l = System.currentTimeMillis();
                while (mFairy.condit()){

                    mFairy.finish(AtFairyConfig.getTaskID(), 7999);

                    LtLog.e(mFairy.getLineInfo("测试"));

                    Thread.sleep(5000);
                }

                break;
        }

        LtLog.e(mFairy.getLineInfo("勾选任务已经全部完成,End!" + AtFairyConfig.getTaskID()));
        mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
    }

    public void taskStartTime(String string) throws Exception {

        if (string.equals("")) {
            return;
        }

        String[] arrstr = string.split("\\|\\|");

        if (arrstr.length < 2) {
            return;
        } else {

            if (!arrstr[0].equals("1")) {
                return;
            }

            String[] arrstr1 = arrstr[1].split(":");
            int h = Integer.parseInt(arrstr1[0]);
            int m = Integer.parseInt(arrstr1[1]);

            long time = System.currentTimeMillis();
            while (mFairy.condit()){

                if(System.currentTimeMillis() - time >30000) {
                    LtLog.e(mFairy.getLineInfo("等待任务开启 >>>"));
                    time = System.currentTimeMillis();
                }

                int hour = mFairy.dateHour();
                int minute =mFairy.dateMinute();

                if(hour==h && minute==m){
                    return;
                }
            }

        }
    }

    public void setQhList() {
        qhList = new ArrayList<>();
        if (AtFairyConfig.getOption("8002").equals("1")) {

            if (AtFairyConfig.getOption("qh1").equals("1")) {
                qhList.add(1);
            }
            if (AtFairyConfig.getOption("qh2").equals("1")) {
                qhList.add(2);
            }
            if (AtFairyConfig.getOption("qh3").equals("1")) {
                qhList.add(3);
            }
            if (AtFairyConfig.getOption("qh4").equals("1")) {
                qhList.add(4);
            }
            if (AtFairyConfig.getOption("qh5").equals("1")) {
                qhList.add(5);
            }
            if (AtFairyConfig.getOption("qh6").equals("1")) {
                qhList.add(6);
            }
        }
    }

    public void init() throws Exception {
        AtFairyConfig.initConfig();

        try {
            optionJson = new JSONObject(AtFairyConfig.getUserTaskConfig());
            LtLog.e("选项列表" + optionJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (!AtFairyConfig.getOption("task_id").equals("")) {
            taskId = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }
    }
}
