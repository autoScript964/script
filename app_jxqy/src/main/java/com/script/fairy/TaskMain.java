package com.script.fairy;

import com.example.publicfunctionlibrary.FunctionClass;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class TaskMain {
    AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    private LimitlessTask limitlessTask;
    private TeamTask teamTask;
    private GamePublicFunction gamePublicFunction;
    private SingleTask singleTask;
    private TimingActivity timingActivity;
    public static Map<String, Integer> taskMap;
    public static String mTask;
    public static String xystr;
    private RedPackage redPackage;
    protected static List xyList;
    protected static List<Integer> optimeList;
    protected static List opcountList;
    protected static List taskList;
    private String taskID;
    protected final static String YunBiao_Time = "YunBiao_Time";
    protected final static String WLMZ_time1 = "WLMZ_time1";
    protected final static String WLMZ_time2 = "WLMZ_time2";
    protected final static String WLMZ_time3 = "WLMZ_time3";
    protected final static String WLMZ_time4 = "WLMZ_time4";
    protected final static String SUBMISSION = "submission";

    protected FunctionClass functionClass;
    public TaskMain(AtFairyImpl ypFairy) {
        mFairy = ypFairy;

        mTask = "";
        xyList = new ArrayList();
        optimeList = new ArrayList();
        opcountList = new ArrayList();
        taskList = new ArrayList();

        taskMap = new HashMap<String, Integer>();
        mFairy.setGameName("新剑侠情缘");
        mFairy.setGameVersion(658);
        publicFunction = new PublicFunction(mFairy);
//      publicFunction=mFairy.publicFunction;
        limitlessTask = new LimitlessTask(mFairy);
        teamTask = new TeamTask(mFairy);
        singleTask = new SingleTask(mFairy);
        timingActivity = new TimingActivity(mFairy);
        gamePublicFunction = new GamePublicFunction(mFairy);
        redPackage = new RedPackage(mFairy);
        functionClass = new FunctionClass(mFairy, mFairy.getContext());
    }





    private int week;
    private int hour;
    private int minute;
    public void main() throws Exception {

        taskStartTime(AtFairyConfig.getOption("start_time"));

        LtLog.e(mFairy.getLineInfo("start:"+AtFairyConfig.getOption("task_id")));

        if(AtFairyConfig.getOption("task_id").equals("2751")){
            mTask = "sgame";
        }else if(AtFairyConfig.getOption("task_id").equals("2715")){





            mTask = "juan";
            taskMap = new HashMap<>();



            taskMap.put("five",0);
            taskMap.put("ten",0);

            if(AtFairyConfig.getOption("five").equals("1")){
                taskMap.put("five",1);
            }

            if(AtFairyConfig.getOption("ten").equals("1")){
                taskMap.put("ten",1);
            }

        }else {
            setTaskList();
        }



        gamePublicFunction.closeWindow();

        int taskState = 0;

        switch (mTask) {

            case "juan":
                LtLog.i(publicFunction.getLineInfo() + "开始家族捐献任务");
                gamePublicFunction.goSecurity();//回安全区

                FindResult findResult = mFairy.findPic(991,29,1122,97,"jiayuan1.png");
                mFairy.onTap(0.8f,findResult,1007,222,1024,234,"离开家园",5000);

                singleTask.familyDonate();

                break;
            case "sgame":


                LtLog.i(publicFunction.getLineInfo() + "开始小游戏任务");
                singleTask.sgame();



                break;
            case "outdoorsOnHook":

                LtLog.i(publicFunction.getLineInfo() + "开始野外挂机任务");
                limitlessTask.map_xy = null;
                limitlessTask.outdoorsOnHook();

                break;
            case "teamTask":
                LtLog.i(publicFunction.getLineInfo() + "开始组队任务");
                new TimingActivity(mFairy).GoSecurityXiakeIsland();//如果在侠客岛,需要先回到安全区,,初始设计脚本时没有考虑到侠客岛这个奇葩,所以这个地方还要调用一次。
                if (taskMap.get("captain") == 1) {
                    taskState = teamTask.leadTeam();
                } else if (taskMap.get("member") == 1) {
                    taskState = teamTask.followTeam();
                }
                break;

            case "SingleTask":
                LtLog.i(publicFunction.getLineInfo() + "开始单人任务");
                new TimingActivity(mFairy).GoSecurityXiakeIsland();//如果在侠客岛,需要先回到安全区,,初始设计脚本时没有考虑到侠客岛这个奇葩,所以这个地方还要调用一次。
                taskState = singleTask.mSingleTask();
                gamePublicFunction.closeWindow();
                break;
            case "mainTask":
                LtLog.i(publicFunction.getLineInfo() + "开始主线任务");
//                mFairy.UpState(569);
                taskState = singleTask.mainTask();
                break;

            case "treasureMap":
                LtLog.i(publicFunction.getLineInfo() + "开始挖宝任务");
                taskState = singleTask.treasureMap();
                break;

            case "experiment":
                //阵法试炼
                taskState = teamTask.experiment();
                break;
            case "QSHL":
                LtLog.i(publicFunction.getLineInfo() + "开始皇陵任务");
                taskState = limitlessTask.QSHLOnHook();
                break;
            case "redPackageAndDssist":
                LtLog.i(publicFunction.getLineInfo() + "------start redPackageAndDssist" + ",assist=" + taskMap.get("assist"));
                while (mFairy.condit()) {
                    if (taskMap.get("assist") == 1) {
                        gamePublicFunction.assist();
                    }
                    Thread.sleep(100);
                }
            case "droiyanLJF":
                //决战凌绝峰
                LtLog.i(publicFunction.getLineInfo() + "---------droiyanLJF=");
                taskState = teamTask.droiyanLJF();
                break;
            case "dance":
                timingActivity.dance_activity();
                LtLog.i(publicFunction.getLineInfo() + "---------dance=");
                break;
        }

        LtLog.i(publicFunction.getLineInfo() + "------taskState=" + taskState);

        UpState(taskState);

        mFairy.finish(AtFairyConfig.getTaskID(), 99);

        Thread.sleep(1000);

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

    private void printThrowsError(InterruptedException error) {
        StringWriter sw = new StringWriter();
        error.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        String[] allstr = str.split("\n");
        for (String ss : allstr) {
            LtLog.i(publicFunction.getLineInfo() + "======error======" + ss);
        }
    }

    private void printThrowsError1(Exception error) {
        StringWriter sw = new StringWriter();
        error.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        String[] allstr = str.split("\n");
        for (String ss : allstr) {
            LtLog.i(publicFunction.getLineInfo() + "======error======" + ss);
        }
    }

    private void setTaskList() throws Exception {
        //添加方式 有点乱，找时间优化一下
        xyList.clear();
        optimeList.clear();
        opcountList.clear();
        taskList.clear();
        taskMap.clear();
        initializationUserSet();

        taskID = AtFairyConfig.getOption("task_id");

        LtLog.i(publicFunction.getLineInfo() + "------ taskID ....." + taskID);
        mTask = "";

        if (taskID == null) {
            return;
        }

        if (taskID.equals("399") || taskID.equals("43")) {
            //野外挂机
            mTask = "outdoorsOnHook";
            //mapx 表示侠客岛 ,侠客岛不在世界地图中 作为一个任务在活动窗口里
            if (AtFairyConfig.getOption("mapx").equals("1")) {
                taskMap.put("OnHookMap", 999);
            } else {
                //添加地图 需要修改的函数 goSecurity; selectMap2 ,initMapNameList,screenXY
                for (int i = 1; i <= 100; i++) {
                    if (AtFairyConfig.getOption("map" + Integer.toString(i)).equals("1")) {
                        taskMap.put("OnHookMap", i);
                        break;
                    }
                }
            }
            if (AtFairyConfig.getOption("captain").equals("1")) {
                taskMap.put("captain", 1); //队长
            } else if (AtFairyConfig.getOption("team").equals("1")) {
                taskMap.put("team", 1);//队员
            } else if (AtFairyConfig.getOption("captainRecall").equals("1")) {
                //队长 +召回
                taskMap.put("captain", 1);
                taskMap.put("timing", 1);
                if (!AtFairyConfig.getOption("recall_time").equals("")) {
                    taskMap.put("recall_time", Integer.parseInt(AtFairyConfig.getOption("recall_time")));
                }
            } else if (AtFairyConfig.getOption("retreat").equals("1")) {
                //变成队长退队  默认必须队员
                LtLog.i(publicFunction.getLineInfo() + "------retreat .....");
                taskMap.put("team", 1);
                taskMap.put("retreat", 1);
            }
            if (AtFairyConfig.getOption("xystr").isEmpty()) {
                LtLog.i(publicFunction.getLineInfo() + "------not  xystr .....");
                xystr = "-1,-1";
//                UpState(642);

            } else {
                xystr = AtFairyConfig.getOption("xystr");
                LtLog.i(publicFunction.getLineInfo() + "------  xystr =" + xystr);
            }
            ///timimg
            if (judgeSelected("WLMZ")) {
                taskMap.put("WLMZ", 1);
                if (judgeSelected("rob")) {
                    taskMap.put("rob", 1);
                }
            }
            if (judgeSelected("3311")) {
                LtLog.i(publicFunction.getLineInfo() + "------  3311 WLMZ =");
                taskMap.put("WLMZ", 1);
                if (judgeSelected("rob")) {
                    taskMap.put("rob", 1);
                }
                if (judgeSelected("customize")) {
                    LtLog.i(publicFunction.getLineInfo() + "------  WLMZ customize =");
                    if (!AtFairyConfig.getOption(WLMZ_time1).equals("") && !AtFairyConfig.getOption(WLMZ_time2).equals("") && !AtFairyConfig.getOption(WLMZ_time3).equals("") && !AtFairyConfig.getOption(WLMZ_time4).equals("")) {
                        LtLog.i(publicFunction.getLineInfo() + "------  WLMZ WLMZ_time =");
                        taskMap.put("customize", 1);
                        int time;
                        time = publicFunction.timeToInt(AtFairyConfig.getOption(WLMZ_time1), publicFunction.SECOND);
                        taskMap.put(WLMZ_time1, time);
                        time = publicFunction.timeToInt(AtFairyConfig.getOption(WLMZ_time2), publicFunction.SECOND);
                        taskMap.put(WLMZ_time2, time);
                        time = publicFunction.timeToInt(AtFairyConfig.getOption(WLMZ_time3), publicFunction.SECOND);
                        taskMap.put(WLMZ_time3, time);
                        time = publicFunction.timeToInt(AtFairyConfig.getOption(WLMZ_time4), publicFunction.SECOND);
                        taskMap.put(WLMZ_time4, time);
                    } else {
                        taskMap.put("default", 1);
                    }
                } else {
                    taskMap.put("default", 1);
                }
            }
            if (judgeSelected("familyRoast")) {
                taskMap.put("JZKH", 1);
                if (judgeSelected("sel17")) {
                    //答题
                    taskMap.put("answer", 1);
                }
                if (judgeSelected("sel14")) {
                    //传功
                    taskMap.put("CG", 1);
                }
            }
            for (int i = 1; i <= 3; i++) {
                //家族秘境
                if (judgeSelected("familyMJ" + Integer.toString(i))) {
                    taskMap.put("familyMJ", i);
                    break;
                }
            }
            if (judgeSelected("Ninehour")) {
                taskMap.put("Ninehour", 1);
            }
            if (judgeSelected("ruins")) {
                //遗迹寻宝
                taskMap.put("ruins", 1);
            }
            if (judgeSelected("nhgc")) {
                //南海钩沉
                taskMap.put("nhgc", 1);
            }
            LtLog.i(publicFunction.getLineInfo() + "------  3311 WLMZ =");
//            if(judgeSelected("tenselectmj")){
//                taskMap.put("tenselectmj", 1);
//                for (int i = 1; i <= 18; i++) {
//                    if (judgeSelected("mj_" + Integer.toString(i))) {
//                        //名将
//                        taskMap.put("mj", i);
//                        break;
//                    }
//                }
//                if (judgeSelected("time_mj1")) {
//                    taskMap.put("time_mj1", 1);
//                }
//                if (judgeSelected("time_mj2")) {
//                    taskMap.put("time_mj2", 1);
//                }
//            }else if(judgeSelected("tenselectdm")){
//                //达摩洞 进去挂机就好,摸boss太困难,跨服boss 都是高战，想冲到boss地图都困难
//                taskMap.put("tenselectdm", 1);
//            }else if (judgeSelected("tenselectazxf")){
//                //鏖战襄樊 进去挂机 机制目前看来也是pk
//                taskMap.put("tenselectazxf", 1);
//            }


            for (int i = 1; i <= 18; i++) {
                if (judgeSelected("mj_" + Integer.toString(i))) {
                    //名将
                    taskMap.put("mj", i);
                    break;
                }
            }

            if (judgeSelected("time_mj1")) {
                taskMap.put("time_mj1", 1);
            }
            if (judgeSelected("time_mj2")) {
                taskMap.put("time_mj2", 1);
            }

            if (judgeSelected("wednesdaytensetmj")) {
                taskMap.put("wednesdaytensetmj", 1);
            } else if (judgeSelected("wednesdayAZXYandDM")) {
                //wednesdayAZXYandDM 决定了周三22点检测什么活动，如果勾选此选项，周三至检测 达摩 鏖战襄阳 不再检测名将，未勾选的话照旧去打名将；
                taskMap.put("wednesdayAZXYandDM", 1);//周三只检测达摩洞和鏖战襄阳
            }
            LtLog.i(publicFunction.getLineInfo() + "------  wednesdaytensetmj =" + taskMap.get("wednesdaytensetmj") + " , wednesdayAZXYandDM : " + taskMap.get("wednesdayAZXYandDM"));
            LtLog.i(publicFunction.getLineInfo() + "------  tenselectmj =" + taskMap.get("tenselectmj"));
            LtLog.i(publicFunction.getLineInfo() + "------  tenselectdm =" + taskMap.get("tenselectdm"));
            LtLog.i(publicFunction.getLineInfo() + "------  tenselectazxf =" + taskMap.get("tenselectazxf"));
            for (int i = 1; i < 4; i++) {
                if (judgeSelected("queen_entryway" + Integer.toString(i))) {
//                    taskMap.put("queen",i);
                    taskMap.put("queen", 2);
                }
            }

            for (int i = 1; i <= 9; i++) {
                if (judgeSelected("sh_" + Integer.toString(i))) {
                    taskMap.put("sh", i);
                    break;
                }
            }
            for (int i = 1; i <= 6; i++) {
                if (judgeSelected("sel7_" + Integer.toString(i))) {
                    //白虎堂时间设置
                    taskMap.put("sel7", i);
                    break;
                }
            }
            if (taskMap.get("sel7") > 0) {
                //白虎堂时间设置
                switch (taskMap.get("sel7")) {
                    case 1:
                        //'11:00
                        taskMap.put("BHTminTime", 659);
                        taskMap.put("BHTmaxTime", 663);
                        break;
                    case 2:
                        // '13:00
                        taskMap.put("BHTminTime", 779);
                        taskMap.put("BHTmaxTime", 783);
                        break;
                    case 3:
                        // '15:00
                        taskMap.put("BHTminTime", 899);
                        taskMap.put("BHTmaxTime", 903);
                        break;
                    case 4:
                        // '17:00
                        taskMap.put("BHTminTime", 1019);
                        taskMap.put("BHTmaxTime", 1023);
                        break;
                    case 5:
                        // '20:00
                        taskMap.put("BHTminTime", 1199);
                        taskMap.put("BHTmaxTime", 1203);
                        break;
                    case 6:
                        // '24:00
                        taskMap.put("BHTminTime", 1439);
                        taskMap.put("BHTmaxTime", 1500);
                        break;
                }

            }

            switch (AtFairyConfig.getOption("hsg")){
                case "1":
                    taskMap.put("hsgMinTime",659);
                    taskMap.put("hsgMaxTime",663);
                    break;
                case "2":
                    taskMap.put("hsgMinTime",899);
                    taskMap.put("hsgMaxTime",903);
                    break;
                case "3":
                    taskMap.put("hsgMinTime",1409);
                    taskMap.put("hsgMaxTime",1413);
                    break;
            }



            for (int i = 1; i <= 10; i++) {
                //白虎堂入口设置
                if (judgeSelected("sel10_" + Integer.toString(i))) {
                    taskMap.put("sel10", i - 1);
                    break;
                }
            }
            for (int i = 1; i <= 3; i++) {
                //宋金战场
                if (judgeSelected("sel8_" + Integer.toString(i))) {
                    taskMap.put("sel8", i);
                    break;
                }
            }
            for (int i = 1; i <= 48; i++) {
                //territoryMap 领土战 驻扎地图设置
                if (judgeSelected("territoryMap" + Integer.toString(i))) {
                    taskMap.put("territoryMap", i);
                    LtLog.i(publicFunction.getLineInfo() + "----------------territoryMap = " + taskMap.get("territoryMap"));
                    break;
                }
            }

            if (taskMap.get("sel8") > 0) {
                switch (taskMap.get("sel8")) {
                    case 1:
                        taskMap.put("SJZCminTime", 989);
                        taskMap.put("SJZCmaxTime", 1000);
                        break;
                    case 2:
                        taskMap.put("SJZCminTime", 1229);
                        taskMap.put("SJZCmaxTime", 1235);
                        break;
                    case 3:
                        taskMap.put("SJZCminTime", 1349);
                        taskMap.put("SJZCmaxTime", 1355);
                        break;
                }
            }
            for (int i = 1; i <= 3; i++) {
                if (judgeSelected("sel18_" + Integer.toString(i))) {
                    taskMap.put("XMHJ", i);
                    break;
                }
            }
            if (taskMap.get("XMHJ") > 0) {
                switch (taskMap.get("XMHJ")) {
                    case 1:
                        //16:25
                        taskMap.put("XMHJminTime", 984);
                        taskMap.put("XMHJmaxTime", 987);
                        break;
                    case 2:
                        //20:26
                        taskMap.put("XMHJminTime", 1225);
                        taskMap.put("XMHJmaxTime", 1228);
                        break;
                    case 3:
                        //22:25
                        taskMap.put("XMHJminTime", 1345);
                        taskMap.put("XMHJmaxTime", 1355);
                        break;
                }
            }
            if (judgeSelected("huashan")) {
                taskMap.put("huashan", 1);
            }
            //杂项设置
            if (judgeSelected("sel15")) {
                taskMap.put("setSkill", 1);
            }
            if (judgeSelected("companion")) {
                taskMap.put("companion", 1);
            }
            if (judgeSelected("chest")) {
                taskMap.put("chest", 1);
            }
            if (judgeSelected("assist")) {
                taskMap.put("assist", 1);
            }
            if (judgeSelected("timeoffline")) {
                taskMap.put("timeoffline", 1);
            }
            if (judgeSelected("timing")) {
                taskMap.put("timing", 1);
            }
            if (judgeSelected("AttackCityStop")) {
                taskMap.put("AttackCityStop", 1);
            }
            if (judgeSelected("peace")) {
                taskMap.put("peace", 1);
            }
            //商会任务提交时间设置
            if (judgeSelected("time1")) {
                taskMap.put("outdoorsOnHook_commerce", 30);
                if (judgeSelected("reward1")) {
                    taskMap.put("reward", 1);
                }
            }
            if (judgeSelected("time2")) {
                taskMap.put("outdoorsOnHook_commerce", 240);
                if (judgeSelected("reward1")) {
                    taskMap.put("reward", 1);
                }
            }
            if (judgeSelected("commerceNotTeam")) {
                taskMap.put("commerceNotTeam", 1);
            }
            add_optime_opcount("optime1", YunBiao_Time);//optime1 运镖时间设置
        } else if (taskID.equals("470") || taskID.equals("47")) {
            //副本任务
            mTask = "teamTask";
            if (judgeSelected("member")) {
                taskMap.put("member", 1);
            }
            if (judgeSelected("captain")) {
                taskMap.put("captain", 1);
            }
            if (judgeSelected("reprimand")) {
                taskMap.put("reprimand", 1);
            }

            if (judgeSelected("bandit")) {
                taskMap.put("bandit", 1);
            }

            for (int i = 1; i <= 3; i++) {
                if (judgeSelected("LJF" + Integer.toString(i))) {
                    taskMap.put("LJF", i);
                    break;
                }
            }
            for (int i = 1; i <= 8; i++) {
                if (judgeSelected("teamCopy" + Integer.toString(i))) {
                    taskMap.put("teamCopy", i);
                    break;
                }
            }
        } else if (taskID.equals("472") || taskID.equals("55")) {
            mTask = "mainTask";
        } else if (taskID.equals("480") || taskID.equals("45") || taskID.equals("2252") || taskID.equals("2641")) {
            //单人任务
            mTask = "SingleTask";
            if (judgeSelected("stall")) {
                taskMap.put("stall", 1);
            }
            if (judgeSelected("offline")) {
                taskMap.put("offline", 1);
            }
            if (judgeSelected("BackReward")) {
                taskMap.put("BackReward", 1);
            }
            if (judgeSelected("4861")) {
                taskMap.put("commerce", 1);
                if (judgeSelected("reward1")) {
                    taskMap.put("reward", 1);
                }
                for (int i = 1; i <= 3; i++) {
                    if (judgeSelected(SUBMISSION + Integer.toString(i))) {
                        taskMap.put(SUBMISSION, i);
                        break;
                    }
                }
            }
            if (judgeSelected("meditation")) {
                taskMap.put("meditation", 1);
            }
            if (judgeSelected("Hero")) {
                taskMap.put("Hero", 1);
            }
            if (judgeSelected("Valkyrie")) {
                taskMap.put("Valkyrie", 1);
            }
            if (judgeSelected("FamilyMuse")) {
                taskMap.put("FamilyMuse", 1);
            }
            if (judgeSelected("Acer")) {
                taskMap.put("Acer", 1);
            }
            if (judgeSelected("treasure")) {
                taskMap.put("treasure", 1);
            }
            if (judgeSelected("hutSign")) {
                taskMap.put("hutSign", 1);
            }
            //珍宝阁
            if (judgeSelected("crystal")) {
                taskMap.put("crystal", 1);
            }
            if (judgeSelected("antique_white")) {
                taskMap.put("antique_white", 1);
            }
            if (judgeSelected("antique_green")) {
                taskMap.put("antique_green", 1);
            }
            if (judgeSelected("antique_blue")) {
                taskMap.put("antique_blue", 1);
            }
            if (judgeSelected("book")) {
                taskMap.put("book", 1);
            }
            if (judgeSelected("soul_green")) {
                taskMap.put("soul_green", 1);
            }
            if (judgeSelected("soul_blue")) {
                taskMap.put("soul_blue", 1);
            }
            if (judgeSelected("soul_purple")) {
                taskMap.put("soul_purple", 1);
            }
            if (judgeSelected("goldBox")) {
                taskMap.put("goldBox", 1);
            }
            if (judgeSelected("ExpMedicine")) {
                taskMap.put("ExpMedicine", 1);
            }
            if (judgeSelected("ZQMedicine")) {
                taskMap.put("ZQMedicine", 1);
            }
            //门客
            for (int i = 1; i <= 4; i++) {
                if (judgeSelected("patrons" + Integer.toString(i))) {
                    taskMap.put("patrons", i);
                    break;
                }
            }
            //捐献
            if (judgeSelected("five")) {
                taskMap.put("five", 1);
            }
            if (judgeSelected("ten")) {
                taskMap.put("ten", 1);
            }
        } else if (taskID.equals("514") || taskID.equals("51")) {
            //挖宝
            mTask = "treasureMap";
            if (judgeSelected("gohole")) {
                taskMap.put("gohole", 1);
            }
        } else if (taskID.equals("53") || taskID.equals("1016")) {
            //阵法试炼
            mTask = "experiment";
        } else if (taskID.equals("59") || taskID.equals("1018")) {
            // 秦始皇陵
            mTask = "QSHL";
            if (judgeSelected("QSHL_into")) {
                taskMap.put("QSHL_into", 1);
            }
            LtLog.i(publicFunction.getLineInfo() + "----------------QSHL_into = " + taskMap.get("QSHL_into"));
            for (int i = 1; i <= 3; i++) {
                if (judgeSelected("layer_" + Integer.toString(i))) {
                    taskMap.put("layer", i);
                    break;
                }
            }
            if (AtFairyConfig.getOption("xystr1").isEmpty() && AtFairyConfig.getOption("xystr2").isEmpty() && AtFairyConfig.getOption("xystr3").isEmpty()) {
                LtLog.i(publicFunction.getLineInfo() + "----------------QSHL  not  xystr .....");
                UpState(642);
            } else {
                for (int i = 1; i <= 6; i++) {
                    String key = "xystr" + Integer.toString(i);
                    if (!AtFairyConfig.getOption(key).isEmpty()) {
                        xyList.add(new int[]{Integer.parseInt(AtFairyConfig.getOption(key).split(",")[0]), Integer.parseInt(AtFairyConfig.getOption(key).split(",")[1])});
                        LtLog.i(publicFunction.getLineInfo() + "------ QSHL  xystr" + Integer.toString(i));
                    }
                }
            }
            if (xyList.size() > 1) {
                String[] opcount = AtFairyConfig.getOption("opcount1").split("\\|\\|");
                if (opcount[0].equals("1")) {
                    opcountList.add(Integer.parseInt(opcount[1]));
                    LtLog.i(publicFunction.getLineInfo() + "------  opcount1 ....." + opcountList.get(0));
                }
            }
        } else if (taskID.equals("1248")) {
            mTask = "redPackageAndDssist";
            if (judgeSelected("assist")) {
                taskMap.put("assist", 1);
            }
            if (judgeSelected("redPack")) {
                taskMap.put("redPack", 1);
            }
        } else if (taskID.equals("1490")) {
            //决战凌绝峰
            mTask = "droiyanLJF";
        } else if (taskID.equals("1871")) {
            mTask = "dance";
        } else {
            taskID = null;
        }
        ///测试 使用
        LtLog.i(publicFunction.getLineInfo() + "------ taskList ....." + taskList + ",mTask=" + mTask + ",taskMap=" + taskMap);
    }

    private void initializationUserSet() {
        //初始化用户设置
        //野外挂机
        taskMap.put("OnHookMap", 0);
        taskMap.put("captain", 0);
        taskMap.put("team", 0);
        taskMap.put("retreat", 0);
        taskMap.put("WLMZ", 0);
        taskMap.put("rob", 0);
        taskMap.put("JZKH", 0);
        taskMap.put("answer", 0);
        taskMap.put("CG", 0);
        taskMap.put("Ninehour", 0);
        taskMap.put("territoryMap", 0);//领土战地图设置
        taskMap.put("tenselectmj", 0);
        taskMap.put("tenselectdm", 0);//达摩洞
        taskMap.put("tenselectazxf", 0);//鏖战襄樊  达摩洞·名将·鏖战襄樊  根据选择来打
        taskMap.put("wednesdaytensetmj", 0); //周三只检测达摩洞和鏖战襄阳
        taskMap.put("wednesdayAZXYandDM", 0);
        taskMap.put("nhgc", 0);
        //名将
        taskMap.put("mj", 0);
        taskMap.put("time_mj1", 0);
        taskMap.put("time_mj2", 0);
        taskMap.put("queen", 0);//女帝
        taskMap.put("sh", 0);
        taskMap.put("sel7", 0);
        taskMap.put("BHTminTime", 0);
        taskMap.put("BHTmaxTime", 0);
        taskMap.put("hsgMinTime",0);
        taskMap.put("hsgMaxTime",0);
        taskMap.put("sel10", 0);
        taskMap.put("sel8", 0);
        taskMap.put("SJZCminTime", 0);
        taskMap.put("SJZCmaxTime", 0);
        taskMap.put("XMHJ", 0);
        taskMap.put("XMHJminTime", 0);
        taskMap.put("XMHJmaxTime", 0);
        taskMap.put("huashan", 0);
        taskMap.put("ruins", 0);
        taskMap.put("familyMJ", 0);
        taskMap.put("AttackCityStop", 0);
        taskMap.put("peace", 0);
        taskMap.put("outdoorsOnHook_commerce", 0);
        taskMap.put(YunBiao_Time, -1);
        taskMap.put("default", 0);
        taskMap.put("customize", 0);
        taskMap.put(WLMZ_time1, -1);
        taskMap.put(WLMZ_time2, -1);
        taskMap.put(WLMZ_time3, -1);
        taskMap.put(WLMZ_time4, -1);
        taskMap.put("recall_time", -1);
        //杂项设置
        taskMap.put("setSkill", 0);
        taskMap.put("companion", 0);
        taskMap.put("chest", 0);
        taskMap.put("assist", 0);
        taskMap.put("timeoffline", 0);
        taskMap.put("timing", 0);
        //副本
        taskMap.put("member", 0);
//        taskMap.put("captain", 0);
        taskMap.put("reprimand", 0);
        taskMap.put("bandit", 0);
        taskMap.put("LJF", 0);
        taskMap.put("teamCopy", 0);
        //单人
        taskMap.put("treasure", 0);
        taskMap.put("stall", 0);
        taskMap.put("commerce", 0);
        taskMap.put(SUBMISSION, -1);
        taskMap.put("reward", -1);
        taskMap.put("meditation", 0);
        taskMap.put("Hero", 0);
        taskMap.put("Valkyrie", 0);
        taskMap.put("FamilyMuse", 0);
        taskMap.put("Acer", 0);
        taskMap.put("BackReward", 0);
        taskMap.put("offline", 0);
        taskMap.put("patrons", 0);
        taskMap.put("hutSign", 0);//小筑签到
        //珍宝阁
        taskMap.put("crystal", 0);
        taskMap.put("antique_white", 0);
        taskMap.put("antique_green", 0);
        taskMap.put("antique_blue", 0);
        taskMap.put("book", 0);
        taskMap.put("soul_green", 0);
        taskMap.put("soul_blue", 0);
        taskMap.put("soul_purple", 0);
        taskMap.put("goldBox", 0);
        taskMap.put("ExpMedicine", 0);
        taskMap.put("ZQMedicine", 0);
        //捐献
        taskMap.put("five", 0);
        taskMap.put("ten", 0);
        //挖宝
        taskMap.put("gohole", 0);
        //皇陵挂机
        taskMap.put("layer", 0);
        taskMap.put("QSHL_into", 0);
        //原地挂机
        taskMap.put("redPack", 0);
        //野外挂机-商会任务-是否退队
        taskMap.put("commerceNotTeam", 0); //  1 不退 0 退队
        taskMap.put("commerceOutTeam", 0); //  0 不退 1 退队
    }

    protected boolean judgeSelected(String str) {
//        if (optionJson.optString(str).equals("1")) {
        if (AtFairyConfig.getOption(str).equals("1")) {
            return true;
        } else {
            if (!AtFairyConfig.getOption(str).equals("")) {
                return false;
            }
        }
        return false;
    }

    private void add_optime_opcount(String key, String mapKey) {

        LtLog.i(publicFunction.getLineInfo() + "------------ add_optime_opcount ....." + key);

        String[] str = AtFairyConfig.getOption(key).split("\\|\\|");
        if (str.length < 2) {
            LtLog.i(publicFunction.getLineInfo() + "error  key ....." + key);
            return;
        }

        if (str[0].equals("1")) {
            if (key.indexOf("time") > 0) {
                int time = publicFunction.timeToInt(str[1], "m");
                LtLog.i(publicFunction.getLineInfo() + "------ time ....." + time + ",str[1]:", str[1]);
//                optimeList.add(time);
                taskMap.put(mapKey, time);
            } else {
                taskMap.put(mapKey, Integer.parseInt(str[1]));
//                opcountList.add(Integer.parseInt(str[1]));
                LtLog.i(publicFunction.getLineInfo() + "------ time ....." + ",str[1]:", str[1]);
            }
        }
    }

    public int UpState(int statr)throws Exception {
        String report;
        if (statr == 0) {
            return statr;
        }

        LtLog.i(publicFunction.getLineInfo() + "------jxqy UpState >>>>>>>>>>" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        switch (statr) {
            case 99:
                LtLog.i(publicFunction.getLineInfo() + "------jxqy 任务完成 ....." + statr);
//                    public static final int FAIRY_TYPE_CHECK = 1;
//                    public static final int FAIRY_TYPE_TASK = 2;
//                    public static final int TASK_STATE_FINISH = 99;
//                report = reportState(FAIRY_TYPE_TASK, taskID, TASK_STATE_FINISH);
//                int type, String taskId, int state
                mFairy.finish(AtFairyConfig.getTaskID(),  TASK_STATE_FINISH);
                LtLog.i(publicFunction.getLineInfo() + "------jxqy report .....");
                break;
            case 575:
                LtLog.i(publicFunction.getLineInfo() + "------jxqy UpState ....." + statr);
                mFairy.finish(AtFairyConfig.getTaskID(),  statr);
                LtLog.i(publicFunction.getLineInfo() + "------jxqy report .....");
                break;
            case 577:
                LtLog.i(publicFunction.getLineInfo() + "------jxqy UpState ....." + statr);
                mFairy.finish(AtFairyConfig.getTaskID(),  statr);
                LtLog.i(publicFunction.getLineInfo() + "------jxqy report .....");
                break;
            case 581:
                LtLog.i(publicFunction.getLineInfo() + "------jxqy UpState ....." + statr);
                mFairy.finish(AtFairyConfig.getTaskID(), statr);
                LtLog.i(publicFunction.getLineInfo() + "------jxqy report .....");
                break;
            case 642:
                LtLog.i(publicFunction.getLineInfo() + "------jxqy UpState ....." + statr);
                mFairy.finish(AtFairyConfig.getTaskID(),  statr);
                LtLog.i(publicFunction.getLineInfo() + "------jxqy report .....");
                break;
        }
        return statr;
    }

    public void killApp() {

        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(392, 229, 885, 413, "IDreplace.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------IDreplace->" + result);
            return;
        }
        mFairy.killUserGame();
    }

    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
