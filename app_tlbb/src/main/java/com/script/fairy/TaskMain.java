package com.script.fairy;

import android.content.Context;

import com.example.publicfunctionlibrary.FunctionClass;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairy2;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.script.opencvapi.AtFairy2.FAIRY_TYPE_TASK;
import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class TaskMain {

    AtFairyImpl mFairy;

    public static final String KEY_EVERYDAY = "everyday";
    public static final String KEY_examination = "examination";

    public static List<String> KEY_LIST;

    public Map<String, String> args;

    static {
        KEY_LIST = new ArrayList<>();
        KEY_LIST.add(KEY_EVERYDAY);
        KEY_LIST.add(KEY_examination);
    }

    private SingleTask singleTask;
    protected Abnormal abnormal;
    private TeamTask teamTask;
    private LimitlessTask limitlessTask;
    protected int checkReturn = 0;
    private AtFairy2.OpencvResult result;
    protected GamePublicFunction gamePublicFunction;
    protected PublicFunction publicFunction;
    protected static int tongSet = 0;
    protected static int rewardSet = 0;
    protected static int priceSet = 0;
    protected static int mythicalSet = 0;
    protected String taskID;
    protected static JSONObject optionJson;
    protected static List<String> taskList;
    protected static boolean first = true;
    protected static int tongMeal = 0;
    protected static int copper = 0;
    protected static int silver = 0;
    protected String mTask;
    protected static int collectionSkill = 0;
    protected static int collectionLevel = 0;
    protected static int again = 0;
    protected Context mContext;
    protected static int map = 0;
    private int index = 0;
    protected boolean mtaskState = true;
    protected List<Integer> roleList = new ArrayList();

    protected FunctionClass functionClass;

    private TimingActivity timingActivity;
    private Other other;
    private int timingState = 0;
    private List<String> roleTaskList = new ArrayList<>();

    public TaskMain(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        map = 0;
        first = true;
        tongSet = 0;
        rewardSet = 0;
        priceSet = 0;
        tongMeal = 0;
        copper = 0;
        silver = 0;
        mythicalSet = 0;
        again = 0;
        collectionSkill = 0;
        collectionLevel = 0;
        taskList = new ArrayList<>();
        optionJson = new JSONObject();
        gamePublicFunction = new GamePublicFunction(ypFairy);
        publicFunction = new PublicFunction(ypFairy);
        functionClass = new FunctionClass(ypFairy, mContext);
        mFairy.setGameName("天龙手游");
        mFairy.setGameVersion(452);
        singleTask = new SingleTask(mFairy);
        limitlessTask = new LimitlessTask(mFairy);
        timingActivity = new TimingActivity(mFairy);
        other = new Other(mFairy);
        teamTask = new TeamTask(mFairy);

    }

    public void main() throws Exception {
        /*Thread.sleep(8000);

        while (mFairy.condit()){

            gamePublicFunction.dianxues();

            Thread.sleep(50000);
        }*/

        setTaskList();

        LtLog.i(publicFunction.getLineInfo() + "------TaskTread-taskStart->" + ",mTask=" + mTask);

        int taskState = 0;

        gamePublicFunction.closeWindow();

        gamePublicFunction.gameSet();

        gamePublicFunction.closeWindow();

        gamePublicFunction.switchSkillOrSet("skill");

        switch (mTask) {
            case "mainTask":
                taskState = singleTask.mainTask();
                break;
            case "everydayTask":

                if (roleList.size() > 0) {
                    //切换角色
                    taskState = role();

                } else {
                    taskState = singleTask.everydayTask();
                    LtLog.i(publicFunction.getLineInfo() + "------task=" + taskState + ", online_rewards==" + judgeSelected("online_rewards") + ",mFairy.priceSet=" + priceSet);
                    if (judgeSelected("online_rewards") == true) {
                        LtLog.i(publicFunction.getLineInfo() + "------签到领奖");
                        singleTask.takePrize();
                    }

                    if (priceSet > 0) {
                        LtLog.i(publicFunction.getLineInfo() + "------上架物品");
                        gamePublicFunction.closeWindow();
                        other.upShelf();
                        gamePublicFunction.closeWindow();
                    }
                }
                break;
            case "outdoorsOnHook":
                taskState = limitlessTask.outdoorsOnHook();
                break;
            case "tongAlchemy":
                taskState = singleTask.everydayTask();
                break;
            case "TeamTask":
                    role2();
                break;
            case "collection"://采集
                LtLog.i(publicFunction.getLineInfo() + "------collection");
                taskState = other.collectionTask();
                break;
            case "practice":
                taskState = other.Practice();
                break;
            case "link":
                taskState = singleTask.everydayTask();
                break;
            case "test":
                other.openMap();
                break;
        }

        LtLog.i(publicFunction.getLineInfo() + "------taskState=" + taskState);
        UpState(taskState);
        mFairy.finish(FAIRY_TYPE_TASK, taskID, TASK_STATE_FINISH);

    }

    private void setTaskList() {
        taskList.clear();
        optionJson = new JSONObject();
        LtLog.i(publicFunction.getLineInfo() + "------ taskList ....." + taskList + ",,AtFairyConfig.getOption(\"task_id\")==" + AtFairyConfig.getOption("task_id"));
        taskID = AtFairyConfig.getOption("task_id");



        for (int i = 5; i <= 8; i++) {
            if (AtFairyConfig.getOption("role" + Integer.toString(i)).equals("1") == true) {
                roleList.add(i - 4);
            }
        }

        for (int i = 1; i <= 4; i++) {
            if (AtFairyConfig.getOption("role" + i).equals("1") == true) {
                roleList.add(i);
                break;
            }
        }



        if (taskID.isEmpty()) {
            LtLog.i(publicFunction.getLineInfo() + "------ taskID.isEmpty");
            mtaskState = false;
            return;
        }
        try {
            if (judgeSelected("buyRope")) {
                optionJson.put("buyRope", "1");
            } else {
                optionJson.put("buyRope", "0");
            }
            if (judgeSelected("preservationArticles")) {
                optionJson.put("preservationArticles", "1");
            } else {
                optionJson.put("preservationArticles", "0");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (taskID.equals("327") || taskID.equals("441")) {
            //单人任务
            mTask = "everydayTask";
            try {
                if (judgeSelected("sweep")) {
                    optionJson.put("sweep", "1");
                } else if (judgeSelected("challenge")) {
                    optionJson.put("challenge", "1");
                }
                if (judgeSelected("buy")) {
                    optionJson.put("buy", "1");
                }
                if (judgeSelected("tongHelp")) {
                    optionJson.put("tongHelp", "1");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (AtFairyConfig.getOption("everyday").equals("1") == true) {
                taskList.add("everydayTask.png");
            }
            if (AtFairyConfig.getOption("examination").equals("1") == true) {
                taskList.add("examination2.png");
            }
            if (AtFairyConfig.getOption("hitBesto").equals("1") == true) {
                taskList.add("hitBesto.png");
            }
            if (AtFairyConfig.getOption("branchGold").equals("1") == true) {
                taskList.add("branchGold.png");
            }
            if (AtFairyConfig.getOption("sweep").equals("1") == true || AtFairyConfig.getOption("challenge").equals("1") == true) {
                //英雄试炼 扫荡还是挑战
                taskList.add("heroExperience.png");
            }
            if (judgeSelected("desire")) {
                taskList.add("desire.png");
            }

            tongSet = 0;
            for (int i = 1; i < 7; i++) {
                if (AtFairyConfig.getOption("tong" + Integer.toString(i)).equals("1") == true) {
                    tongSet = i;
                }
            }
            if (tongSet > 0) {
                taskList.add("tongTask.png");
            }
            rewardSet = 0;
            for (int i = 1; i < 6; i++) {
                String rewardSTR = "reward" + Integer.toString(i);
                LtLog.i(publicFunction.getLineInfo() + "------ collectionSkill=" + rewardSTR + ",,,=" + AtFairyConfig.getOption(rewardSTR).equals("1"));
                if (AtFairyConfig.getOption(rewardSTR).equals("1") == true) {
                    rewardSet = i;
                    break;
                }
            }
            if (rewardSet > 0) {
                taskList.add("reward.png");
            }
            priceSet = 0;
            for (int i = 1; i < 4; i++) {
                if (AtFairyConfig.getOption("price" + Integer.toString(i)).equals("1") == true) {
                    priceSet = i;
                    break;
                }
            }
//            领取邮件
            if (judgeSelected("post")) {
                taskList.add("post.png");
            }
            //征战捐献

            for (int i = 1; i <= 7; i++) {
                if (AtFairyConfig.getOption("donationNumber" + Integer.toString(i)).equals("1") == true) {
                    if (judgeSelected("copper")) {
                        copper = i;
                    }
                    if (judgeSelected("silver")) {
                        silver = i;
                    }
                    if (copper > 0 || silver > 0) {
                        taskList.add("BattleDonation.png");
                    }
                    break;
                }
            }
            LtLog.i(publicFunction.getLineInfo() + "------ silver=" + silver + ",copper=" + copper);
            //设置需要完成单人任务角色列表



        } else if (taskID.equals("329") || taskID.equals("65")) {
            mTask = "outdoorsOnHook";
            try {
                if (AtFairyConfig.getOption("xystr").isEmpty()) {
                    optionJson.put("xystr", "-1,-1");
                } else {
                    optionJson.put("xystr", AtFairyConfig.getOption("xystr"));
                }
                if (judgeSelected("captain")) {
                    optionJson.put("captain", "1");
                }
                if (judgeSelected("team")) {
                    optionJson.put("team", "1");
                }
                if (judgeSelected("dice_Answer")) {
                    optionJson.put("dice_Answer", "1");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int i = 1; i <= 16; i++) {
                if (AtFairyConfig.getOption("radio_" + i).equals("1")) {
                    map = i;
                    break;
                }
            }

            if (AtFairyConfig.getOption("zlqj").equals("1")) {
                taskList.add("zlqj.png");
            }
            if (AtFairyConfig.getOption("practice").equals("1")) {
                taskList.add("tongPractice.png");
            }
            if (judgeSelected("guard")) {
                taskList.add("tongGuard.png");
            }
            if (judgeSelected("xjl")) {
                taskList.add("tongDrink.png");
            }
            if (judgeSelected("assassin")) {
                //帮会杀星
                taskList.add("tongAssassin.png");
            }
            for (int i = 1; i <= 3; i++) {
                if (judgeSelected("meal_" + Integer.toString(i)) == true) {
                    this.tongMeal = i;
                    break;
                }
            }
            if (tongMeal > 0) {
                taskList.add("tongMeal.png");
            }
            if (judgeSelected("b_yb")) {
                taskList.add("tongYB.png");
            }
            if (judgeSelected("indiana")) {
                taskList.add("indiana.png");
            }
            if (judgeSelected("hslj")) {
                taskList.add("HSLJ.png");
            }
            if (judgeSelected("h_sl")) {
                taskList.add("SLDZ.png");
            }
            if (judgeSelected("sports")) {
                //门派竞技
                taskList.add("sports.png");
            }
            if (judgeSelected("combatFFJ")) {
                //扶风郡战场
                taskList.add("combatFFJ.png");
            }
            if (judgeSelected("secret")) {
                //要诀争夺战
                taskList.add("secret.png");
            }
            if (judgeSelected("territory")) {
                taskList.add("territory.png");
            }
            if (judgeSelected("football")) {
                taskList.add("footBall.png");
            }
            if (judgeSelected("ywt")) {
                taskList.add("YWT.png");
            }
            if (judgeSelected("collectionTime1")) {
                taskList.add("collectionTime1");
            }
            if (judgeSelected("collectionTime2")) {
                taskList.add("collectionTime2");
            }
            if (judgeSelected("collectionTime3")) {
                taskList.add("collectionTime3");
            }
            if (judgeSelected("collectionTime4")) {
                taskList.add("collectionTime4");
            }
            if (judgeSelected("battle")) {
                //征战天下
                taskList.add("battle.png");
            }

            if (judgeSelected("collectionTime1") || judgeSelected("collectionTime2") || judgeSelected("collectionTime3") || judgeSelected("collectionTime4")) {
                for (int i = 1; i <= 5; i++) {
                    if (judgeSelected("skill" + Integer.toString(i)) == true) {
                        this.collectionSkill = i - 1;
                        break;
                    }
                }
                for (int i = 1; i <= 5; i++) {
                    if (judgeSelected("level" + Integer.toString(i)) == true) {
                        this.collectionLevel = i - 1;
                        break;
                    }
                }
                LtLog.i(publicFunction.getLineInfo() + "------ collectionSkill=" + collectionSkill + ",collectionLevel=" + collectionLevel);

            }
            for (int i = 1; i <= 6; i++) {
                if (judgeSelected("mythical" + Integer.toString(i)) == true) {
                    this.mythicalSet = i;
                    LtLog.i(publicFunction.getLineInfo() + "------ mythicalSet ....." + mythicalSet);
                    taskList.add("mythical.png");
                    break;
                }
            }
            if (judgeSelected("desert")) {
                taskList.add("desert.png");
            }
            //家园
            try {
                optionJson.put("cultivate", "0");
                optionJson.put("cultivateProduction", "0");
                optionJson.put("rear", "0");
                optionJson.put("rearProduction", "0");
                optionJson.put("culture", "0");
                optionJson.put("cultureProduction", "0");
                for (int i = 1; i <= 4; i++) {
                    if (judgeSelected("cultivate" + Integer.toString(i)) == true) {
                        optionJson.put("cultivate", Integer.toString(i));
                        LtLog.i(publicFunction.getLineInfo() + "------ cultivate ....." + i);
                        break;
                    }
                }
                for (int i = 1; i <= 3; i++) {
                    if (judgeSelected("cultivateProduction" + Integer.toString(i))) {
                        optionJson.put("cultivateProduction", Integer.toString(i));
                        LtLog.i(publicFunction.getLineInfo() + "------ cultivateProduction ....." + i);
                        break;
                    }
                }
                for (int i = 1; i <= 4; i++) {
                    if (judgeSelected("rear" + Integer.toString(i)) == true) {
                        optionJson.put("rear", Integer.toString(i));
                        LtLog.i(publicFunction.getLineInfo() + "------ rear ....." + i);
                        break;
                    }
                }
                for (int i = 1; i <= 3; i++) {
                    if (judgeSelected("rearProduction" + Integer.toString(i))) {
                        optionJson.put("rearProduction", Integer.toString(i));
                        LtLog.i(publicFunction.getLineInfo() + "------ rearProduction ....." + i);
                        break;
                    }
                }

                for (int i = 1; i <= 4; i++) {
                    if (judgeSelected("culture" + Integer.toString(i)) == true) {
                        optionJson.put("culture", Integer.toString(i));
                        LtLog.i(publicFunction.getLineInfo() + "------ culture ....." + i);
                        break;
                    }
                }
                optionJson.put("white_book", "0");
                for (int i = 1; i <= 3; i++) {
                    if (judgeSelected("cultureProduction" + Integer.toString(i))) {
                        optionJson.put("cultureProduction", Integer.toString(i));
                        LtLog.i(publicFunction.getLineInfo() + "------ cultureProduction ....." + i);
                        break;
                    }
                }
                //读书
                for (int i = 1; i <= 4; i++) {
                    if (judgeSelected("white_book" + Integer.toString(i))) {
                        optionJson.put("white_book", Integer.toString(i));
                        LtLog.i(publicFunction.getLineInfo() + "------ white_book ....." + i);
                        break;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            LtLog.i(publicFunction.getLineInfo() + "------ tongMeal ....." + tongMeal);
        } else if (taskID.equals("331") || taskID.equals("67")) {
            mTask = "TeamTask";
            if (judgeSelected("y_lsh")) {
                taskList.add("realmLSH.png");
            }
            if (judgeSelected("y_yzw")) {
                taskList.add("realmYZW.png");
            }
            if (judgeSelected("y_sjz")) {
                taskList.add("realmSJZ.png");
            }
            if (judgeSelected("y_pmf")) {
                taskList.add("realmPMF.png");
            }
            if (judgeSelected("hero")) {
                taskList.add("heroRealm1.png");
                taskList.add("heroRealm2.png");
                taskList.add("heroRealm3.png");
                taskList.add("heroRealm4.png");
            }
            try {
                if (judgeSelected("radio1")) {
                    optionJson.put("radio1", "1");
                    if (judgeSelected("shaoshishan")) {
                        taskList.add("shaoshishan.png");
                    }
                } else if (judgeSelected("radio2")) {
                    optionJson.put("radio2", "1");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (judgeSelected("y_fz")) {
                taskList.add("rebel.png");
            }
            if (judgeSelected("y_mz")) {
                taskList.add("brigands.png");
            }
            try {
                if (judgeSelected("meltingto")) {
                    optionJson.put("meltingto", "1");//熔炼经脉装备
                } else {
                    optionJson.put("meltingto", "0");
                }
                if (judgeSelected("melting")) {
                    optionJson.put("melting", "1");//熔炼套装装备
                } else {
                    optionJson.put("melting", "0");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (taskID.equals("333") || taskID.equals("443")) {
            mTask = "tongAlchemy";
            //帮会炼金
            taskList.add("alchemy.png");
        } else if (taskID.equals("325") || taskID.equals("83")) {
            mTask = "mainTask";
        } else if (taskID.equals("361") || taskID.equals("449")) {
            mTask = "collection";
            for (int i = 1; i <= 5; i++) {
                if (judgeSelected("skill" + Integer.toString(i)) == true) {
                    this.collectionSkill = i - 1;
                    break;
                }
            }
            for (int i = 1; i <= 5; i++) {
                if (judgeSelected("level" + Integer.toString(i)) == true) {
                    this.collectionLevel = i - 1;
                    break;
                }
            }
            LtLog.i(publicFunction.getLineInfo() + "------ collectionSkill=" + collectionSkill + ",collectionLevel=" + collectionLevel);
        } else if (taskID.equals("363") || taskID.equals("445")) {
            mTask = "practice";
            taskList.add("practice.png");
            for (int i = 1; i <= 3; i++) {
                if (judgeSelected("again" + Integer.toString(i)) == true) {
                    this.again = i;
                    break;
                }
            }
        } else if (taskID.equals("365") || taskID.equals("447")) {
            mTask = "link";
            try {
                if (judgeSelected("buy")) {
                    optionJson.put("buy", "1");
                }
                if (judgeSelected("tongHelp")) {
                    optionJson.put("tongHelp", "1");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            taskList.add("linkTask.png");
//            tongHelp
        } else if (taskID.equals("1284")) {
            ///测试 使用
            mTask = "test";
        } else {
            taskID = null;
        }

        LtLog.i(publicFunction.getLineInfo() + "------ taskList ....." + taskList + ",optionJson=" + optionJson);
    }

    public int UpState(int statr)throws Exception {
        String report;
        if (statr == 0) {
            return statr;
        }

        LtLog.i(publicFunction.getLineInfo() + "------TLBB UpState >>>>>>>>>>" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        switch (statr) {
            case 99:
                statr = 0;
                LtLog.i(publicFunction.getLineInfo() + "------tlbb UpState 任务完成 ....." + statr);

                mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
                LtLog.i(publicFunction.getLineInfo() + "------tlbb report .....");
                break;
            case 639:
                statr = 0;
                LtLog.i(publicFunction.getLineInfo() + "------tlbb UpState 背包已满 ....." + statr);
//               report = reportState(FAIRY_TYPE_TASK, taskID, TASK_STATE_FINISH);
                mFairy.finish(AtFairyConfig.getTaskID(), 639);
                LtLog.i(publicFunction.getLineInfo() + "------tlbb report .....");
                break;
            case 640:
                statr = 0;
                LtLog.i(publicFunction.getLineInfo() + "------tlbb UpState 没有捕兽绳 ....." + statr);
//               report = reportState(FAIRY_TYPE_TASK, taskID, TASK_STATE_FINISH);
                mFairy.finish(AtFairyConfig.getTaskID(),  640);
                LtLog.i(publicFunction.getLineInfo() + "------tlbb report .....");
                break;
            case 641:
                statr = 0;
                LtLog.i(publicFunction.getLineInfo() + "------tlbb UpState 帮会求助无人帮忙 ....." + statr);
//               report = reportState(FAIRY_TYPE_TASK, taskID, TASK_STATE_FINISH);
                mFairy.finish(AtFairyConfig.getTaskID(),  641);
                LtLog.i(publicFunction.getLineInfo() + "------tlbb report .....");
                break;
            case 634:
                LtLog.i(publicFunction.getLineInfo() + "------tlbb UpState ....." + 634);
                statr = 0;
//                report = reportState(FAIRY_TYPE_CHECK, taskID, 634);
                mFairy.finish(AtFairyConfig.getTaskID(), 634);
                LtLog.i(publicFunction.getLineInfo() + "------tlbb report .....");
                break;
            case 636:
                LtLog.i(publicFunction.getLineInfo() + "------tlbb UpState ....." + 636);
                statr = 0;
//                report = reportState(FAIRY_TYPE_CHECK, taskID, 636);
                mFairy.finish(AtFairyConfig.getTaskID(), 636);
                LtLog.i(publicFunction.getLineInfo() + "------tlbb report .....");
                break;
            case 637:
                LtLog.i(publicFunction.getLineInfo() + "------tlbb UpState ....." + 636);
                statr = 0;
//                report = reportState(FAIRY_TYPE_CHECK, taskID, 637);
                mFairy.finish(AtFairyConfig.getTaskID(),  637);
                LtLog.i(publicFunction.getLineInfo() + "------tlbb report .....");
                break;
        }

        return statr;

    }

    private int role() throws Exception {

        LtLog.i(publicFunction.getLineInfo() + "【开始切换角色 ： " + roleList + "】");

        roleTaskList.addAll(taskList);

        LtLog.i(publicFunction.getLineInfo() + "【要执行的任务roleTaskList ： " + roleTaskList + "】");

        String role = "yes";


        if (role.equals("yes")) {
            if (taskList.size() == 0) {
                taskList.addAll(roleTaskList);
            }

            LtLog.i(publicFunction.getLineInfo() + "【要执行的任务taskList ： " + taskList + "】");

            singleTask.everydayTask();

            LtLog.i(publicFunction.getLineInfo() + "------task=" + ", 签到领奖 online_rewards :" + judgeSelected("online_rewards") + ",上架物品 priceSet :" + priceSet);

            if (judgeSelected("online_rewards") == true) {
                LtLog.i(publicFunction.getLineInfo() + "【签到领奖】");
                singleTask.takePrize();
            }
            if (priceSet > 0) {
                LtLog.i(publicFunction.getLineInfo() + "【上架物品】");
                gamePublicFunction.closeWindow();
                other.upShelf();
                gamePublicFunction.closeWindow();
            }
        }

        for (int i = 0; i < roleList.size(); i++) {

            //mFairy.killUserGame();//由于切换角色会导致网络异常，所以，直接把游戏杀掉。异常线程会负责重启

            gamePublicFunction.switchRole();

            int num = roleList.get(i);

            LtLog.i(publicFunction.getLineInfo() + "【要切换角色：" +num+ "】");

            while (mFairy.condit()) {
                //进入游戏
                result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【主场景】");
                    break;
                }

                FindResult findResult = mFairy.findPic(1000, 614, 1269, 698, new String[]{"playGame.png","playGame2.png"});
                if (findResult.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【选择角色界面】");
    //
                    result = publicFunction.localFindPic(15, 110 + ((num - 1) * 119), 190, 210 + ((num - 1) * 119), "createRole.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "【角色未创建】");
                        role = "not";
                        break;
                    } else {
                        role = "yes";
                        LtLog.i(publicFunction.getLineInfo() + "【选择角色】");
                        publicFunction.rndTap(121, 163 + ((num - 1) * 119), 140, 170 + ((num - 1) * 119));
                        Thread.sleep(1000);
                    }

                    publicFunction.rndTapWH(findResult.x, findResult.y, 116, 27);

                    Thread.sleep(5000);
                }

                gamePublicFunction.closeWindow();

                Thread.sleep(2000);
            }

            LtLog.i(publicFunction.getLineInfo() + "【切换角色 ： " + roleList + " - 结束】");


            if(i ==roleList.size()-1) {
                boolean bool = false;

                for (int j = 1; j <= 4; j++) {
                    if (AtFairyConfig.getOption("role" + j).equals("1")) {
                        bool=true;
                        break;
                    }
                }

                if(bool){
                    LtLog.i(publicFunction.getLineInfo() + "【全部的角色已完成任务】");
                    return 99;
                }
            }

            if (role.equals("yes")) {
                if (taskList.size() == 0) {
                    taskList.addAll(roleTaskList);
                }

                LtLog.i(publicFunction.getLineInfo() + "【要执行的任务taskList ： " + taskList + "】");

                singleTask.everydayTask();

                LtLog.i(publicFunction.getLineInfo() + "------task=" + ", 签到领奖 online_rewards :" + judgeSelected("online_rewards") + ",上架物品 priceSet :" + priceSet);

                if (judgeSelected("online_rewards") == true) {
                    LtLog.i(publicFunction.getLineInfo() + "【签到领奖】");
                    singleTask.takePrize();
                }
                if (priceSet > 0) {
                    LtLog.i(publicFunction.getLineInfo() + "【上架物品】");
                    gamePublicFunction.closeWindow();
                    other.upShelf();
                    gamePublicFunction.closeWindow();
                }
            }


        }

        return 99;
    }

    private int role2() throws Exception {



        roleTaskList.addAll(taskList);

        LtLog.i(publicFunction.getLineInfo()+"开始组队任务");
        LtLog.i(publicFunction.getLineInfo() + "------TaskTread-mFairy.taskList.indexOf(radio1)->" + optionJson.optString("radio1").equals("1"));
        if (optionJson.optString("radio1").equals("1")) {
            LtLog.i(publicFunction.getLineInfo()+"带队");
            teamTask.leadTeam();//带队
        } else {
            LtLog.i(publicFunction.getLineInfo()+"跟随");
            teamTask.followTeam();//跟随
        }

        for (int i = 0; i < roleList.size(); i++) {
            LtLog.i(publicFunction.getLineInfo() + "【开始切换角色 ： " + roleList + "】");
            //mFairy.killUserGame();//由于切换角色会导致网络异常，所以，直接把游戏杀掉。异常线程会负责重启

            gamePublicFunction.switchRole();

            int num = roleList.get(i);

            LtLog.i(publicFunction.getLineInfo() + "【要切换角色：" +num+ "】");

            while (mFairy.condit()) {
                //进入游戏
                result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【主场景】");
                    break;
                }

                AtFairy2.OpencvResult result1 = publicFunction.localFindPic(1000, 603, 1253, 703, "playGame.png");
                if (result1.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【选择角色界面】");

                    result = publicFunction.localFindPic(15, 110 + ((num - 1) * 119), 190, 210 + ((num - 1) * 119), "createRole.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "【角色未创建】");
                        return 99;
                    } else {
                        LtLog.i(publicFunction.getLineInfo() + "【选择角色】");
                        publicFunction.rndTap(121, 163 + ((num - 1) * 119), 140, 170 + ((num - 1) * 119));
                        Thread.sleep(1000);
                    }
                    publicFunction.rndTapWH(result1.x, result1.y, 116, 27);

                    Thread.sleep(5000);
                }

                gamePublicFunction.closeWindow();

                Thread.sleep(2000);
            }

            LtLog.i(publicFunction.getLineInfo() + "【切换角色 ： " + roleList + " - 结束】");


            if(i ==roleList.size()-1) {
                boolean bool = false;

                for (int j = 1; j <= 4; j++) {
                    if (AtFairyConfig.getOption("role" + j).equals("1")) {
                        bool=true;
                        break;
                    }
                }

                if(bool){
                    LtLog.i(publicFunction.getLineInfo() + "【全部的角色已完成任务】");
                    return 99;
                }
            }



            if (taskList.size() == 0) {
                taskList.addAll(roleTaskList);
            }

            LtLog.i(publicFunction.getLineInfo() + "【要执行的任务taskList ： " + taskList + "】");


            LtLog.i(publicFunction.getLineInfo()+"开始组队任务");
            LtLog.i(publicFunction.getLineInfo() + "------TaskTread-mFairy.taskList.indexOf(radio1)->" + optionJson.optString("radio1").equals("1"));
            if (optionJson.optString("radio1").equals("1")) {
                LtLog.i(publicFunction.getLineInfo()+"带队");
               teamTask.leadTeam();//带队
            } else {
                LtLog.i(publicFunction.getLineInfo()+"跟随");
                teamTask.followTeam();//跟随
            }
        }


        return 99;
    }


    protected static boolean judgeSelected(String str) {
        if (AtFairyConfig.getOption(str).equals("1")) {
            return true;
        }
        return false;
    }

}
