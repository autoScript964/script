package com.script.fairy;

import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairy2;
import com.script.framework.AtFairyImpl;

/**
 * Created by Administrator on 2018-10-25.
 */

public class CopyTask {

    private AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    private GamePublicFunction gamePublicFunction;
    private String currentTask;
    private boolean pagoda_fail = false;

    public CopyTask(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(ypFairy);
        gamePublicFunction = new GamePublicFunction(ypFairy);
    }

    public int mCopyTask() throws Exception {
        AtFairy2.OpencvResult result;
        MatTime matTime = new MatTime(mFairy);
        long sleepTime = 0;
        gamePublicFunction.openActivity(gamePublicFunction.openActivity_copy);
        while (mFairy.condit()) {
            sleepTime = matTime.mMatTime(1119, 54, 71, 15, 0.8);
            LtLog.i(publicFunction.getLineInfo() + "------sleepTime->" + sleepTime);
            if (sleepTime >= 30) {
                gamePublicFunction.openActivity(gamePublicFunction.openActivity_copy);
                matTime.resetTime();
            }
            result = publicFunction.localFindPic(121, 0, 249, 115, "dailt.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------dailt->" + result);
                if (TaskMain.taskList.size() > 0) {
                    TaskMain.taskList = gamePublicFunction.lookupTask(TaskMain.taskList, gamePublicFunction.openActivity_copy);//查看任务
                    Thread.sleep(3000);
                }
            }
            if (TaskMain.taskList.size() == 0) {
                LtLog.i(publicFunction.getLineInfo() + "------任务完成->");
                return 99;
            }
            switch (TaskMain.taskList.get(0)) {
                case "sweardong":
                    currencyCopyTask();
                    break;
                case "regain":
                    currencyCopyTask();
                    break;
                case "helpmaster":
                    currencyCopyTask();
                    break;
                case "dreamland":
                    currencyCopyTask();
                    break;
                case "defend":
                    currencyCopyTask();
                    break;
                case "pagoda"://试练塔
                    pagodaTask();
                    break;
                case "star":
                    starTask(); //名将
                    break;
                case "hero":
                    heroTask();
                    break;

            }






            result = publicFunction.localFindPic(1181, 133, 1280, 246, "leave.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------leave->" + result);
                gamePublicFunction.automaticCombat(1);
                matTime.resetTime();
            }
            Thread.sleep(1000);
        }
        return 0;
    }


    private void heroTask() throws Exception {
        AtFairy2.OpencvResult result;
        boolean taskStart = true;

        for (int i = 0; i < 6; i++) {
            result = publicFunction.localFindPic(729, 570, 896, 697, "hero1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------hero1->" + result);
                publicFunction.rndTapWH(result.x, result.y, 67, 27);
                Thread.sleep(1000);
            } else {
                break;
            }
            if (i >= 5) {
                taskStart = false;
                break;
            }
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(824, 449, 1031, 573, "continue.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------continue->" + result);
            publicFunction.rndTapWH(result.x, result.y, 107, 24);
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(668, 95, 928, 277, "pagoda_fail.png");
//        LtLog.i(publicFunction.getLineInfo() + "------pagoda_fail->" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------pagoda_fail->" + result);
            publicFunction.rndTap(662, 498, 724, 531);
            Thread.sleep(10000);
            taskStart = false;
        }
        if (taskStart == false) {
            TaskMain.taskList.remove("hero");
            LtLog.i(publicFunction.getLineInfo() + "------英雄史话 移除任务->");
            gamePublicFunction.closeWindow();
        }
    }

    //名将挑战
    private void starTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(748, 537, 901, 660, "star1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------star1->" + result);
            publicFunction.rndTapWH(result.x, result.y, 53, 23);
            Thread.sleep(500);
            for (int i = 0; i < 10; i++) {
                result = publicFunction.localFindPic(748, 537, 901, 660, "star1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------star1->" + result);
                    publicFunction.rndTapWH(result.x, result.y, 53, 23);
                    Thread.sleep(500);
                } else {
                    break;
                }
                if (i > 5) {
                    TaskMain.taskList.remove("star");
                    gamePublicFunction.closeWindow();
                    return;
                }
                Thread.sleep(1000);
            }


        }
        result = publicFunction.localFindPic(824, 449, 1031, 573, "continue.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------continue->" + result);
            publicFunction.rndTapWH(result.x, result.y, 107, 24);
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(284, 295, 498, 393, "Resurrection.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------Resurrection->" + result);
            publicFunction.rndTap(result.x, result.y, result.x + 30, result.y + 10);
            Thread.sleep(2000);
        }


    }

    //试练塔
    private void pagodaTask() throws Exception {
        AtFairy2.OpencvResult result;

        result = publicFunction.localFindPic(754, 582, 914, 709, "receive.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------receive->" + result);
            publicFunction.rndTapWH(result.x, result.y, 60, 27);
            Thread.sleep(500);
        }
        gamePublicFunction.automaticCombat(1);
        result = publicFunction.localFindPic(668, 95, 928, 277, "pagoda_fail.png");

        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------pagoda_fail->" + result);
            pagoda_fail = true;
        }
        result = publicFunction.localFindPic(438, 302, 679, 404, "pagoda_level.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------pagoda_level->" + result);
            publicFunction.rndTap(616, 468, 670, 487);
            pagoda_fail = true;
        }
        if (pagoda_fail) {
            AtFairy2.OpencvResult result1;
            result1 = publicFunction.localFindPic(607, 502, 729, 627, "reset.png");
            result = publicFunction.localFindPic(835, 504, 943, 622, "one.png");
            if (result.sim >= 0.8 && result1.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------one->" + result);
                LtLog.i(publicFunction.getLineInfo() + "------reset->" + result1);
                publicFunction.rndTapWH(result1.x, result1.y, 22, 25);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(632, 580, 756, 709, "pagoda_automatic.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------pagoda_automatic->" + result1);
                publicFunction.rndTapWH(result.x, result.y, 24, 29);
                Thread.sleep(2000);
                result = publicFunction.localFindPic(835, 503, 946, 622, "zero.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------zero->" + result1);
                    TaskMain.taskList.remove("pagoda");
                    for (int i = 0; i < 3; i++) {
                        gamePublicFunction.closeWindow();
                    }
                    return;
                }

            }
        } else {
            result = publicFunction.localFindPic(885, 578, 1014, 712, "pagoda_hand.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------pagoda_hand->" + result);
                publicFunction.rndTapWH(result.x, result.y, 29, 34);
                Thread.sleep(500);
            }
        }
        result = publicFunction.localFindPic(643, 581, 797, 709, "pagoda_stop.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------pagoda_stop->" + result);
            TaskMain.taskList.remove("pagoda");
            for (int i = 0; i < 3; i++) {
                gamePublicFunction.closeWindow();
            }
        }


    }

    private void currencyCopyTask() throws Exception {
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(1069, 325, 1263, 449, "into.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------into->" + result);
            publicFunction.rndTapWH(result.x, result.y, 94, 24);
            Thread.sleep(1000);

        }
        result = publicFunction.localFindPic(685, 539, 1049, 667, "into1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------into1->" + result);
            publicFunction.rndTapWH(result.x, result.y, 99, 28);
            Thread.sleep(2000);
            gamePublicFunction.clickDetermine(673, 421, 883, 535);
        }

        result = publicFunction.localFindPic(807, 445, 1061, 590, "continue.png");
        if (result.sim >= 0.7) {
            LtLog.i(publicFunction.getLineInfo() + "------continue->" + result);
            publicFunction.rndTapWH(result.x, result.y, 20, 20);
            Thread.sleep(2000);
            gamePublicFunction.clickDetermine(673, 421, 883, 535);
        }
    }

}
