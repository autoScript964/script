package com.script.fairy;

import android.content.Context;

import com.example.publicfunctionlibrary.FunctionClass;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.LtLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.script.opencvapi.AtFairy2.FAIRY_TYPE_CHECK;
import static com.script.opencvapi.AtFairy2.FAIRY_TYPE_TASK;
import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class TaskMain {
    protected Context mContext;
    protected PublicFunction publicFunction;
    protected SingleTask singleTask;
    protected GamePublicFunction gamePublicFunction;
    protected LimitlessTask limitlessTask;
    protected FamilyTask familyTask;
    protected CopyTask copyTask;
    protected String mTask;
    protected static List<String> taskList;
    protected static boolean invisible = false;
    private String taskID;
    protected static int map;
    protected static int npc;
    protected static int TCZ;
    protected static List<String> familyList;
    private Object lockTask = new Object();
    protected TimingActivity timingActivity;
    protected static int select_feather = 0;
    protected static int select_huangbang = 0;
    protected static int select_book = 0;
    protected static int biaoche = 0;
    public static String clear_string = "";
    protected FunctionClass functionClass;
    protected static List xyList;
    protected List opcountList = new ArrayList();

    private AtFairy2.OpencvResult result;
    private AtFairyImpl mFairy;
    private String sun;

    public TaskMain(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        taskList = new ArrayList<>();
        familyList = new ArrayList<>();
        invisible = false;
        xyList = new ArrayList();
        clear_string="";
        biaoche = 0;
        select_feather = 0;
        select_huangbang = 0;
        select_book = 0;

        mContext = ypFairy.getContext();
        publicFunction = new PublicFunction(ypFairy);
        gamePublicFunction = new GamePublicFunction(ypFairy);
        familyTask = new FamilyTask(ypFairy);
        timingActivity = new TimingActivity(ypFairy);
        limitlessTask = new LimitlessTask(ypFairy);
        singleTask = new SingleTask(ypFairy);
        copyTask = new CopyTask(ypFairy);
        functionClass = new FunctionClass(ypFairy, mContext);

        mFairy.setGameName("御龙在天");
        mFairy.setGameVersion(185);

    }

    public void main() throws Exception {

        setTaskList();

        int taskState = 0;
        for (int i = 0; i < 10; i++) {
            gamePublicFunction.closeWindow();
        }
        LtLog.i(publicFunction.getLineInfo() + "------gameInitializationSet->");
        gamePublicFunction.gameInitializationSet();
        if (mTask.equals("outdoorsOnHook") == false) { //如果不是执行野外挂机
            LtLog.i(publicFunction.getLineInfo() + "------goMianCity->");
            if (mTask.equals("mainTask") == false) { //并且也不是主线任务
                gamePublicFunction.goMianCity();
                gamePublicFunction.closeWindow();
            }
        } else {
            //如果执行野外挂机，并且不在洛阳郊外
            result = publicFunction.localFindPic(1085, 0, 1270, 79, "LuoYang2.png");
            if (result.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------LuoYang2->" + result);
                gamePublicFunction.goMianCity();
                gamePublicFunction.closeWindow();
            }
        }

        LtLog.i(publicFunction.getLineInfo() + "------mFairy.mTask->" + mTask);

        switch (mTask) {
            case "mainTask":
                taskState = singleTask.mainTask();
                break;
            case "SingleTask":
                taskState = singleTask.everydayTask();
                break;
            case "outdoorsOnHook"://野外挂机
                LtLog.i(publicFunction.getLineInfo() + "------mFairy.mTask->" + mTask);
                limitlessTask.outdoorsOnHook();
                break;
            case "copyTask":
                taskState = copyTask.mCopyTask();
                break;
            case "battlefront":
                taskState = singleTask.battlefrontTask();
                break;
            case "treasureMap":
                taskState = singleTask.treasureMap();
                break;
            case "resetGame":
                singleTask.resetGame();
                taskState=99;
                break;
            case "practice":

                break;
            case "link":

                break;
        }

        LtLog.i(publicFunction.getLineInfo() + "------taskState=" + taskState);
        UpState(taskState);
        LtLog.e(mFairy.getLineInfo("勾选任务已经全部完成,End!" + AtFairyConfig.getTaskID()));
        mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);

        return;

    }

    public int UpState(int statr) {
        String report;
        if (statr == 0) {
            return statr;
        }
        LtLog.i(publicFunction.getLineInfo() + "------ylzt UpState >>>>>>>>>>" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        switch (statr) {
            case 99:
                LtLog.i(publicFunction.getLineInfo() + "------ylzt 任务完成 ....." + statr);
                mFairy.finish(FAIRY_TYPE_TASK, taskID, TASK_STATE_FINISH);
                LtLog.i(publicFunction.getLineInfo() + "------ylzt report .....");
                break;
            case 575:
                LtLog.i(publicFunction.getLineInfo() + "------ylzt UpState ....." + statr);
                mFairy.finish(FAIRY_TYPE_CHECK, taskID, statr);
                LtLog.i(publicFunction.getLineInfo() + "------ylzt report .....");
                break;
            case 577:
                LtLog.i(publicFunction.getLineInfo() + "------ylzt UpState ....." + statr);
                mFairy.finish(FAIRY_TYPE_CHECK, taskID, statr);
                LtLog.i(publicFunction.getLineInfo() + "------ylzt report .....");
                break;
            case 581:
                LtLog.i(publicFunction.getLineInfo() + "------ylzt UpState ....." + statr);
                mFairy.finish(FAIRY_TYPE_CHECK, taskID, statr);
                LtLog.i(publicFunction.getLineInfo() + "------ylzt report .....");
                break;
        }
        return statr;
    }

    public void setTaskList() {
        taskList.clear();
        taskID = AtFairyConfig.getOption("task_id");
        LtLog.i(publicFunction.getLineInfo() + "------ taskID ....." + taskID + ",taskList=" + taskList);
        mTask = "";
        if (taskID == null) {
            return;
        }

        if (taskID.equals("115")) {
            mTask = "SingleTask";
            addTaskList("Acer");//签到
            addTaskList("blessingGod");//福神参拜
            addTaskList("musician");//探访乐师
            addTaskList("tripod");//镇国大鼎
            addTaskList("sell");
            addTaskList("salary");
            addTaskList("retrieve");
            addTaskList("wish");
            addTaskList("pig");
            addTaskList("dragon");//捣龙脉
            addTaskList("ZXY_everday");
            if (judgeSelected("invisible")) {
                invisible = true;
            }
            LtLog.i(publicFunction.getLineInfo() + "------invisible= " + invisible);
            if (judgeSelected("huangbang")) {
                taskList.add("huangbang");
            }
            for (int i = 1; i <= 5; i++) {
                if (AtFairyConfig.getOption("huangbang" + Integer.toString(i)).equals("1")) {
                    select_huangbang = i;
                    taskList.add("huangbang");
                    break;
                }
            }
            if (judgeSelected("crusade")) {
                taskList.add("crusade");
            }
            if (judgeSelected("hot_spring")) {//御龙温泉
                taskList.add("hot_spring");
            }
            addTaskList("travel");//周游列国
            addTaskList("anchor");//主播陪你玩
            if (judgeSelected("feather")) {
                taskList.add("feather");//鸡毛信 8:00 23:59
            }

            for (int i = 1; i <= 5; i++) {
                if (AtFairyConfig.getOption("feather_" + Integer.toString(i)).equals("1")) {
                    select_feather = i;
                    taskList.add("feather");
                    break;
                }
            }
            for (int i = 1; i <= 5; i++) {
                //清理道具
                String str = "clear" + Integer.toString(i);
                if (AtFairyConfig.getOption(str).equals("1")) {
                    if (taskList.indexOf("clear") < 0) {
                        taskList.add("clear");
                    }
                    if (clear_string == "") {
                        clear_string = str + ".png";
                    } else {
                        clear_string = clear_string + "|" + str + ".png";
                    }
                }
            }
            if (AtFairyConfig.getOption("clear6").equals("1")) {
                if (taskList.indexOf("clear") < 0) {
                    taskList.add("clear");
                }
                addTaskList("clear6");
            }


            LtLog.i(publicFunction.getLineInfo() + "------clear_string= " + clear_string);
            if (judgeSelected("learn")) {
                taskList.add("learn");//护国寺取经 12:00-23:59
            }
            for (int i = 1; i <= 5; i++) {
                if (AtFairyConfig.getOption("book" + Integer.toString(i)).equals("1")) {
                    select_book = i;
                    taskList.add("learn");
                    break;
                }
            }
            if (judgeSelected("visit")) {//中立区参拜
                taskList.add("visit");
            }
            if (judgeSelected("suppressBandits")) {//剿匪
                taskList.add("suppressBandits");
            }
            if (judgeSelected("quartermaster")) {//运镖
                taskList.add("quartermaster");
                for (int i = 1; i <= 5; i++) {
                    if (AtFairyConfig.getOption("biaoche_" + Integer.toString(i)).equals("1")) {
                        biaoche = i;
                        break;
                    }
                }
            }
        }

        if (taskID.equals("119")) {
            mTask = "copyTask";
            addTaskList("sweardong");
            addTaskList("regain");
            addTaskList("helpmaster");
            addTaskList("dreamland");
            addTaskList("defend");
            addTaskList("pagoda");
            addTaskList("star");
            addTaskList("hero");
        }
        if (taskID.equals("117")) {
            mTask = "battlefront";
            String sun = publicFunction.getCurrSun();
            long currentTime = publicFunction.getMinuteNumber();

            LtLog.i(publicFunction.getLineInfo() + "------sun= " + sun + ",currentTime=" + currentTime);
            //if (sun.equals("星期一") || sun.equals("星期三") || sun.equals("星期五")) {
                if (currentTime > 480 && currentTime < 1380) {
                    addTaskList("hero_battlefield");
                }
            //}


            //if (sun.equals("星期二") || sun.equals("星期四") || sun.equals("星期六") || sun.equals("星期日")) {
                if (currentTime > 480 && currentTime < 1380) {
                    addTaskList("guandu");
                }
            //}
            addTaskList("loong");
        }

        if (taskID.equals("1553")) {
            mTask = "treasureMap";
        }

        if (taskID.equals("113")) {
            mTask = "outdoorsOnHook";

            LtLog.i(publicFunction.getLineInfo() + "------optime1 .....", AtFairyConfig.getOption("optime1"));
            LtLog.i(publicFunction.getLineInfo() + "------3259 .....", AtFairyConfig.getOption("3259"));

            for (int i = 1; i <= 2; i++) {
                if (AtFairyConfig.getOption("radio" + i).equals("1")) {
                    map = i;
                    break;
                }
            }

            for (int i = 1; i <= 5; i++) {
                if (AtFairyConfig.getOption("npc" + i).equals("1")) {
                    npc = i;
                    break;
                }
            }

            for (int i = 1; i <= 4; i++) {
                if (AtFairyConfig.getOption("TCZ" + i).equals("1")) {
                    TCZ = i;
                    taskList.add("anOffical");
                    break;
                }
            }

            LtLog.i(publicFunction.getLineInfo() + "------TCZ   " + TCZ);
            for (int i = 1; i <= 26; i++) {
                if (AtFairyConfig.getOption("map" + i).equals("1")) {
                    map = i;
                    break;
                }
            }

            if (AtFairyConfig.getOption("xystr1").isEmpty() && AtFairyConfig.getOption("xystr2").isEmpty() && AtFairyConfig.getOption("xystr3").isEmpty()) {
                LtLog.i(publicFunction.getLineInfo() + "----------------xystr  not  xystr .....");
            } else {
                for (int i = 1; i <= 3; i++) {
                    String key = "xystr" + Integer.toString(i);
                    if (!AtFairyConfig.getOption(key).isEmpty()) {
                        int x = Integer.parseInt(AtFairyConfig.getOption(key).split(",")[0]);
                        int y = Integer.parseInt(AtFairyConfig.getOption(key).split(",")[1]);
                        LtLog.i(publicFunction.getLineInfo() + "------   xystr" + Integer.toString(i) + "," + x + "," + y);
                        xyList.add(new int[]{x, y});
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
            /*
            if (AtFairyConfig.getOption("xystr").isEmpty()) {
                LtLog.i(publicFunction.getLineInfo() + "------not  xystr .....");
                xystr = "-1,-1";
            } else {
                xystr = AtFairyConfig.getOption("xystr");
                LtLog.i(publicFunction.getLineInfo() + "------ xystr=" + xystr);
            }
            */


            addTaskList("anOffical");
            addTaskList("country");
            addTaskList("revive");
            addTaskList("experience");
            addTaskList("family_experiment");
            addTaskList("treasure");
            if (judgeSelected("family_hero")) {
                familyList.add("family_hero");
            }
            if (judgeSelected("family_snake")) {
                familyList.add("family_snake");
            }
            if (judgeSelected("family_plant")) {
                familyList.add("family_plant");
            }
            if (judgeSelected("family_horse")) {
                familyList.add("family_horse");
            }
//            addTaskList("family_hero");
//            addTaskList("family_snake");
//            addTaskList("family_horse");
//            addTaskList("family_plant");
            LtLog.i(publicFunction.getLineInfo() + "------map= " + map + ",npc=" + npc + ",taskList=" + taskList + ",mTask=" + mTask + ",familyList=" + familyList);
        }
        if (taskID.equals("111")) {
            mTask = "mainTask";
        }
        if (taskID.equals("2059")) {
            mTask = "resetGame";
        }
        LtLog.i(publicFunction.getLineInfo() + "------taskList= " + taskList);
    }

    private void addTaskList(String addTask) {
        if (AtFairyConfig.getOption(addTask).equals("1")) {
            LtLog.i(publicFunction.getLineInfo() + "------add task " + addTask);
            taskList.add(addTask);
        }
    }

    protected boolean judgeSelected(String str) {
        if (AtFairyConfig.getOption(str).equals("1")) {
            return true;
        }
        return false;
    }



}

