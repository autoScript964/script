package com.script.fairy;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairy2;
import com.script.framework.AtFairyImpl;
import org.json.JSONException;
import java.util.Random;

public class TeamTask {
    //组队任务
    private AtFairyImpl mFairy;
    private AtFairy2.OpencvResult result;
    private GamePublicFunction gamePublicFunction;
    private PublicFunction publicFunction;
    private long time = System.currentTimeMillis() / 1000, timex = 0;
    private long time1 = System.currentTimeMillis() / 1000, time1x = 0;
    private Random random = new Random(100);
    private int xy = 0, xy1 = 0;
    private long mTime_i=0;
    public TeamTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        gamePublicFunction = new GamePublicFunction(mFairy);
        publicFunction = new PublicFunction(mFairy);
    }

    public int leadTeam() throws Exception {
        Other other=new Other(mFairy);

        try {
            if(TaskMain.optionJson.get("preservationArticles").equals("1")){
                LtLog.i(publicFunction.getLineInfo()+"开始执行保存物品");
                other.preservationArticles();
                //保存物品
            }
            gamePublicFunction.closeWindow();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        gamePublicFunction.closeWindow();
        gamePublicFunction.switchSkillOrSet("skill");
        gamePublicFunction.createTeam();
        gamePublicFunction.closeWindow();
        gamePublicFunction.goLuoYang();
        gamePublicFunction.createTeam();
        gamePublicFunction.closeWindow();
        gamePublicFunction.openActivity(1);
        LtLog.i(publicFunction.getLineInfo() + "-------mFairy.first>" + TaskMain.first);
        LtLog.i(publicFunction.getLineInfo() + "-------mFairy.first>" + TaskMain.first);

        while (mFairy.condit()) {
            if (gamePublicFunction.outDungeons() == 1) {
                LtLog.i(publicFunction.getLineInfo() + "在副本中");
                gamePublicFunction.follow(0);
                gamePublicFunction.automaticCombat(1);//开启自动战斗
                time = System.currentTimeMillis() - time / 1000;
            }

            result = publicFunction.localFindPic(653,1,1104,96, "activity.png");
            if (result.sim > 0.8) {
                //没有活动按钮 不识别坐标
                LtLog.i(publicFunction.getLineInfo() + "在主场景");

                mTime_i = mFairy.mMatTime(1126,27,61,20,0.8f);

                xy = publicFunction.getXY(1118, 31, 1198, 48, 200, 255, 0);
//                xy=ocrNumber.getNumber(1120,29,74,14,new Scalar(200,200,200),new Scalar(255,255,255));
            }else{
                mFairy.initMatTime();
                mTime_i=0;
            }

            /*if (xy != xy1 && xy > 0) {
                LtLog.i(publicFunction.getLineInfo() + "-------xy>" + xy);
                xy1 = xy;
                time = System.currentTimeMillis() / 1000;
            }

            timex = System.currentTimeMillis() / 1000 - time;*/

            LtLog.i(publicFunction.getLineInfo() + "在主场景,发呆时间为：" + mTime_i);

            result = publicFunction.localFindPic(582,8,721,80, "activitiesWindow.png"+"|"+"activitiesWindow1.png|activitiesWindow2.png");
            if (result.sim >= 0.8) {
                //如果在活动界面
                LtLog.i(publicFunction.getLineInfo() + "活动界面  __taskList.size1=" + TaskMain.taskList.size()+ "当前执行任务=" + TaskMain.taskList.get(0));
                if (TaskMain.taskList.size() > 0) {
                    TaskMain.taskList = gamePublicFunction.lookupTask(TaskMain.taskList);//查看任务
                }

                if (TaskMain.taskList.size() == 0) {
                    gamePublicFunction.closeWindow();
                    LtLog.i(publicFunction.getLineInfo() + "---------taskList.size()>" + "任务已完成");
                    try {
                        if(TaskMain.optionJson.get("preservationArticles").equals("1")){
                            LtLog.i(publicFunction.getLineInfo()+"开始执行保存物品");
                            other.preservationArticles();
                            //保存物品
                        }
                        gamePublicFunction.closeWindow();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return 99;
                }

                Thread.sleep(1000);

            } else if (mTime_i >= 15) {
                /**
                 * 在执行反贼入侵任务
                 */
                result = publicFunction.localFindPic(653,1,1104,96, "activity.png");
                if (TaskMain.taskList.get(0).equals("rebel.png") && result.sim >= 0.8) {

                    LtLog.i(publicFunction.getLineInfo()+"【在执行反贼入侵任务】");

                    for (int i = 0; i < 3; i++) {
                        LtLog.i(publicFunction.getLineInfo() + "点击中间");
                        publicFunction.rndTap(629, 350, 650, 448);
                        Thread.sleep(2000);
                        result = publicFunction.localFindPic(490, 364, 693, 412, "eliminate.png");
                        if (result.sim >= 0.8) {
                            LtLog.i(publicFunction.getLineInfo() + "消灭");
                            publicFunction.rndTapWH(result.x, result.y, 50, 20);
                            Thread.sleep(500);
                            time = System.currentTimeMillis() / 1000;
                            break;
                        }
                    }
                }
            }


            int vacancyNumber = 0;

            result = publicFunction.localFindPic(617, 13, 702, 71, "team.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "组队界面");

                matchingADragon();

                result = publicFunction.localFindPic(802, 617, 926, 664, "propaganda.png");
                if (result.sim >= 0.8) {
                    if (Math.abs(random.nextInt()) % 100 >= 90) {
                        LtLog.i(publicFunction.getLineInfo() + "一键喊话");
                        publicFunction.rndTapWH(result.x, result.y, 50, 20);
                        Thread.sleep(500);
                    }
                }

                gamePublicFunction.setTarget("aDragon");//设置组队目标为 一条龙

                gamePublicFunction.tirenAndAgree(); //踢离线  同意申请

                //查看当前有几个空位
                for (int i = 0; i < 4; i++) {
                    result = publicFunction.localFindPic(1000 - (i * 205), 292, 1124 - (i * 205), 418, "vacancy.png");
                    if (result.sim >= 0.75) {
                        vacancyNumber = vacancyNumber + 1;
                    }
                }

                if (vacancyNumber <= 2) {
                    gamePublicFunction.closeWindow();
                    Thread.sleep(1000);
                    gamePublicFunction.follow(1);
                    gamePublicFunction.openActivity(1);
                }

                LtLog.i(publicFunction.getLineInfo() + "-------当前队伍空位>" + vacancyNumber);

            } else if (mTime_i >= 15 && mTime_i < 30) {
                //如果不在组队界面
                result = publicFunction.localFindPic(653,1,1104,96, "activity.png");
                if (TaskMain.taskList.get(0).equals("rebel.png") && result.sim >= 0.8 && gamePublicFunction.outDungeons() == 0) {

                    LtLog.i(publicFunction.getLineInfo()+"【在执行反贼入侵任务】");

                    for (int i = 0; i < 3; i++) {
                        LtLog.i(publicFunction.getLineInfo() + "点击中间");
                        publicFunction.rndTap(629, 410, 650, 448);
                        Thread.sleep(2000);
                        result = publicFunction.localFindPic(512, 361, 693, 414, "open.png");
                        if (result.sim >= 0.8) {
                            break;
                        }
                    }
                }
            } else if (mTime_i >= 30) {
                if (gamePublicFunction.outDungeons() == 0) {
                    gamePublicFunction.openActivity(1);
                    time = System.currentTimeMillis() / 1000;
                }
            }

            LtLog.i(publicFunction.getLineInfo() + "-------comeInto>");

            comeInto();

            result = publicFunction.localFindPic(706, 446, 926, 664, "immediately.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "立刻前往");
                publicFunction.rndTapWH(result.x, result.y, 50, 20);
                Thread.sleep(500);
            }

            result = publicFunction.localFindPic(559, 141, 710, 191, "teamState.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------teamState>" + result);
                publicFunction.rndTap(905, 163, 924, 186);
                Thread.sleep(1000);
                gamePublicFunction.openTeam();//打开组队
                gamePublicFunction.tirenAndAgree();//检查是否有人离线
                gamePublicFunction.closeWindow();
                gamePublicFunction.follow(0);
                gamePublicFunction.follow(1);
            }
            result = publicFunction.localFindPic(465, 265, 694, 347, "applyCaptain.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------applyCaptain>" + result);
                publicFunction.rndTap(486, 462, 530, 481);//点取消
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(561, 92, 730, 151, "invitingFriends.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------invitingFriends=" + result);
                publicFunction.rndTap(923, 112, 942, 135);
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(463, 300, 653, 391, "fullPersonnel.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------fullPersonnel=" + result);
                publicFunction.rndTap(745, 460, 796, 487);  //确定
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(398, 283, 715, 396, "noTeam.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------noTeam=" + result);
                publicFunction.rndTap(752, 464, 787, 481);  //确定
                Thread.sleep(1000);
            }
            result = publicFunction.localFindPic(389, 282, 677, 389, "setTeamTarget.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------setTeamTarget=" + result);
                publicFunction.rndTap(485, 463, 532, 482);  //取消
                Thread.sleep(1000);
                gamePublicFunction.openTeam();
            }
            Thread.sleep(100);
        }
        return 0;
    }

    public int followTeam() throws Exception {
        Other other=new Other(mFairy);
        try {
            if(TaskMain.optionJson.get("preservationArticles").equals("1")){
                other.preservationArticles();
                //保存物品
            }
            gamePublicFunction.closeWindow();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        gamePublicFunction.closeWindow();
        gamePublicFunction.switchSkillOrSet("skill");

        result = publicFunction.localFindPic(61, 2, 104, 61, "captain.png|captain-1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->captain=" + result);
            gamePublicFunction.exitTeam();
        }
        result = publicFunction.localFindPic(61, 2, 104, 61, "teamMember.png|teamMember-1.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------->teamMember=" + result);
            gamePublicFunction.goLuoYang();
        }
        matchingADragon();
        gamePublicFunction.closeWindow();
        gamePublicFunction.switchTaskOrTeam("task");
        timex = 0;
        time = System.currentTimeMillis() / 1000;
        TaskMain.first = false;
        while (mFairy.condit()) {
            if (Math.abs(random.nextInt()) % 100 >= 70) {
                LtLog.i(publicFunction.getLineInfo() + "------followTeam--leadTeam>" + "__timex=" + timex);
            }
            timex = System.currentTimeMillis() / 1000 - time;
            if (gamePublicFunction.outDungeons() == 1) {
                gamePublicFunction.automaticCombat(1);
                time = System.currentTimeMillis() / 1000;
            } else if (timex >= 270 && timex < 300) {
                gamePublicFunction.switchTaskOrTeam("task");
                gamePublicFunction.openActivity(1);
            } else if (timex >= 600) {
                gamePublicFunction.exitTeam();
                matchingADragon();
                gamePublicFunction.closeWindow();
                time = System.currentTimeMillis() / 1000;
            }
            result = publicFunction.localOptimalFindPic(42, 285, 115, 465, 140, 255.0, 0, "brigands1.png");
            if (result.sim >= 0.75 && this.timex >= 60) {
                LtLog.i(publicFunction.getLineInfo() + "------->brigands1=" + result);
                AtFairy2.OpencvResult result1 = publicFunction.localFindPic(result.x - 56, result.y, result.x + 101, result.y + 52, "many.png");
                if (result1.sim >= 0.8) {
                    time = System.currentTimeMillis() / 1000;
                    LtLog.i(publicFunction.getLineInfo() + "-------有多倍>result1=" + result1);
                    publicFunction.rndTapWH(result.x, result.y, 50, 20);
                    Thread.sleep(500);
                } else {
                    gamePublicFunction.openActivity(1);
                }
            }
            result = publicFunction.localFindPic(582,8,721,80, "activitiesWindow.png|activitiesWindow1.png|activitiesWindow2.png");
            if (result.sim >= 0.8) {
                //如果在活动界面
                LtLog.i(publicFunction.getLineInfo() + "---------activitiesWindow>" + result + "__taskList.size1=" + TaskMain.taskList.size());
                if (TaskMain.taskList.size() > 0) {
                    TaskMain.taskList = gamePublicFunction.lookupTask1(TaskMain.taskList);//查看任务
                }
                if (TaskMain.taskList.size() == 0) {
                    gamePublicFunction.closeWindow();
                    LtLog.i(publicFunction.getLineInfo() + "---------taskList.size()>" + "任务已完成");
                    return 99;
                }
                LtLog.i(publicFunction.getLineInfo() + "---------activitiesWindow>" + result + "__taskList.size2=" + TaskMain.taskList.size() + "当前执行任务=" + TaskMain.taskList.get(0));
            }
            gamePublicFunction.luckDraw();
            Thread.sleep(100);
        }
        return 0;
    }

    private void comeInto() throws Exception {
        //eliminate.png
        switch (TaskMain.taskList.get(0)) {
            case "realmLSH.png":
                result = publicFunction.localFindPic(466, 362, 961, 542, "challenge2.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------challenge2=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 50, 20);
                    Thread.sleep(500);
                }
                break;
            case "realmYZW.png":
                result = publicFunction.localFindPic(466, 362, 961, 542, "realmYZW1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------realmYZW1=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 50, 20);
                    Thread.sleep(500);
                }
                break;
            case "realmSJZ.png":
                result = publicFunction.localFindPic(466, 362, 961, 542, "realmSJZ1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------realmSJZ1=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 50, 20);
                    Thread.sleep(500);
                }
                break;
            case "realmPMF.png":
                result = publicFunction.localFindPic(466, 362, 961, 542, "challenge2.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------challenge2=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 50, 20);
                    Thread.sleep(500);
                }
                break;
            case "rebel.png":
                result = publicFunction.localFindPic(490, 364, 693, 412, "eliminate.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------eliminate=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 50, 20);
                    Thread.sleep(500);
                }
                result = publicFunction.localFindPic(417, 335, 492, 379, "rebel1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------rebel1.png=" + result);
                    publicFunction.rndTap(740, 458, 803, 491);
                    Thread.sleep(500);
                }
                result = publicFunction.localFindPic(412, 310, 485, 346, "rebel2.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------rebel2.png=" + result);
                    publicFunction.rndTap(745, 455, 800, 482);
                    Thread.sleep(500);
                }
                result = publicFunction.localFindPic(421, 312, 537, 367, "goToFZ.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------goToFZ.png=" + result);
                    publicFunction.rndTap(744, 458, 798, 487);
                    Thread.sleep(500);
                }
                break;
            case "brigands.png":
                for (int i = 0; i < 10; i++) {
                    result = publicFunction.localOptimalFindPic(42, 285, 115, 465, 140, 255.0, 0, "brigands1.png");
                    //if (result.sim >= 0.75 && this.timex >= 5) {
                    //发呆超过30S 并且任务栏检测到马贼任务
                    if (result.sim >= 0.75 && this.timex >= 30) {
                        LtLog.i(publicFunction.getLineInfo() + "-------currentTask>brigands1=" + result + ",timex=" + timex);
                        gamePublicFunction.openTeam();//打开组队
                        gamePublicFunction.tirenAndAgree();//检查是否有人离线
                        gamePublicFunction.closeWindow();
                        AtFairy2.OpencvResult result1 = publicFunction.localFindPic(result.x - 56, result.y, result.x + 101, result.y + 52, "many.png");
                        if (result1.sim >= 0.8) {
                            LtLog.i(publicFunction.getLineInfo() + "-------有多倍>result1=" + result1);
                            publicFunction.rndTapWH(result.x, result.y, 50, 20);
                            Thread.sleep(1000);
                            this.time = System.currentTimeMillis() / 1000;
                        } else {
                            gamePublicFunction.openActivity(1);
                        }
                    } else {
                        break;
                    }
                    Thread.sleep(100);
                }
                result = publicFunction.localOptimalFindPic(42, 285, 115, 465, 140, 255.0, 0, "brigands1.png");
                if (result.sim >= 0.75) {
                    LtLog.i(publicFunction.getLineInfo() + "-------currentTask>brigands1=" + result);
                    AtFairy2.OpencvResult result1 = publicFunction.localFindPic(result.x - 56, result.y, result.x + 101, result.y + 52, "many.png");
                    if (result1.sim < 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "------->无多倍=" + result1);
                        gamePublicFunction.openActivity(1);
                    }
                }
                gamePublicFunction.switchTaskOrTeam("task");
                gamePublicFunction.luckDraw();
                result = publicFunction.localFindPic(490, 364, 693, 412, "eliminate.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------eliminate=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 50, 20);
                    Thread.sleep(2000);
                    gamePublicFunction.outMercenary();
                }
                break;
            case "shaoshishan.png":
                result = publicFunction.localFindPic(459,347,736,443, "shaoshishan1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------shaoshishan1=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 50, 20);
                    Thread.sleep(2000);
                }

                result = publicFunction.localFindPic(280,96,414,500, "shaoshishan2-1.png" + "|" + "shaoshishan2.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------------shaoshishan2=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 50, 20);
                    Thread.sleep(1000);
                    publicFunction.rndTap(951,603,992,625);//点击挑战
                    Thread.sleep(2000);
                }
                break;
            default:
                result = publicFunction.localFindPic(466, 362, 961, 542, "heroRealm5.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "heroRealm3.png");
                    LtLog.i(publicFunction.getLineInfo() + "------------heroRealm5=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 50, 20);
                    Thread.sleep(500);
                }
                break;
        }
    }

    private void matchingADragon() throws Exception {

        for (int i = 0; i < 5; i++) {
            gamePublicFunction.openTeam();

            result = publicFunction.localFindPic(236, 105, 351, 624, "aDragon2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "点击一条龙");
                publicFunction.rndTapWH(result.x, result.y, 77, 27);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(1001, 629, 1173, 681, "matching.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "自动匹配");
                publicFunction.rndTapWH(result.x, result.y, 77, 27);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(991, 594, 1136, 715, "cancel1.png");
            if (result.sim >= 0.8) {
                return;
            }
            Thread.sleep(500);
        }

    }

}
