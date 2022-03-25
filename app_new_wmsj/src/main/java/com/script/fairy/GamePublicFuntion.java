package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import static com.script.opencvapi.AtFairy2.FAIRY_TYPE_TASK;
import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

public class GamePublicFuntion {
    public AtFairyImpl mFairy;
    public FindResult result;
    public static int mainTime;
    public static int FINISH = 0;
    public static int bossTime;
    public static int unexecuted = 0;
    public static long zhTime = System.currentTimeMillis();

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        FINISH = 0;
        FINISH = 0;
        unexecuted = 0;
    }

    public void skills() throws Exception {
        for (int i = 0; i < 5; i++) {
            mFairy.onTap(1134, 574, 1176, 611, "A", 200);
        }
        mFairy.onTap(1036, 655, 1070, 686, "A", 200);
        mFairy.onTap(1006, 570, 1039, 600, "A", 200);
        mFairy.onTap(1037, 485, 1071, 520, "A", 200);
        mFairy.onTap(1126, 454, 1158, 480, "A", 200);
    }

    public boolean init() throws Exception {
        result = mFairy.findPic(921, 68, 1090, 164, new String[]{"fb.png", "fb1.png"});
        if (result.sim > 0.8f) {
            mFairy.onTap(1051, 111, 1075, 134, "离开副本", 2000);
            mFairy.onTap(722, 469, 753, 490, "确定", 1000);

        } else {
            result = mFairy.findPic(1000, 11, 1067, 80,
                    new String[]{"right.png", "right2.png"});
            if (result.sim > 0.7f) {
                mFairy.onTap(0.7f, result, "右侧缩放栏", 3000);
            } else {
                result = mFairy.findPic(new String[]{"home.png", "home2.png", "bao.png"});
                if (result.sim > 0.73f) {
                    result = mFairy.findPic(698, 14, 1092, 191, "a.png");
                    if (result.sim < 0.8f) {
                        mFairy.onTap(1029, 40, 1041, 53, "右侧缩放栏", 1000);
                    }
                }
            }
        }

        result = mFairy.findPic("yuan.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "原服", 2000);
            mFairy.onTap(725, 471, 756, 487, "确定", 1000);
        }

        unexecuted++;
        if (unexecuted > 2) {
            /**
             * 关闭*/
            close();
            unexecuted = 0;
        }
        result = mFairy.findPic(1107, 228, 1212, 335, new String[]{"home.png", "home2.png", "bao.png"});
        if (result.sim > 0.73f) {
            LtLog.e("在主场景");
            return false;
        }
        return true;
    }//

    public void close() throws Exception {
        for (int i = 1; i <=10; i++) {
            
            result = mFairy.findPic(775, 1, 1275, 299, new String[]{"close1.png", "close7.png"});
            if (result.sim > 0.72f) {
                result = mFairy.findPic(775, 1, 775+(50*i), 299, new String[]{"close1.png", "close7.png"});
                mFairy.onTap(0.72f, result, "关闭[1]", 1000);
            }else{
                break;
            }
        }

        result = mFairy.findPic(814, 1, 1275, 299, "close3.png");
        mFairy.onTap(0.75f, result, "关闭[3]", 1000);
        result = mFairy.findPic(814, 1, 1275, 299, "close4.png");
        mFairy.onTap(0.75f, result, "关闭[4]", 1000);
        result = mFairy.findPic(814, 1, 1275, 299, "close5.png");
        mFairy.onTap(0.75f, result, "关闭[5]", 1000);
        result = mFairy.findPic(814, 1, 1275, 299, "close6.png");
        mFairy.onTap(0.75f, result, "关闭[6]", 1000);

        result = mFairy.findPic("close8.png");
        mFairy.onTap(0.75f, result, "关闭[8]", 1000);
    }

    public void errInit() throws Exception {
        for (int i = 0; i < 3; i++) {
            result = mFairy.findPic("auto1.png");
            mFairy.onTap(0.96f, result, "关闭自动战斗", 1000);

            result = mFairy.findPic("tuoli.png");
            mFairy.onTap(0.8f, result, "脱离", 1000);
        }
        mFairy.onTap(1179, 59, 1210, 85, "点开地图,开始新的任务", 2000);

    }//脱机卡死

    public int ranksNum() throws Exception {

        for (int i = 1; i <= 5; i++) {
            result = mFairy.findPic("rnum" + i + ".png");
            if (result.sim > 0.8f) {
                LtLog.e(mFairy.getLineInfo("当前队伍人数: " + i));
                return i;
            }
        }
        LtLog.e(mFairy.getLineInfo("当前队伍人数: 满人"));
        return 6;

    }//队伍数量

    public void ranksTT() throws Exception {
        for (int i = 1; i <= 5; i++) {
            result = mFairy.findPic("ranks13.png");
            if (result.sim < 0.8f) {
                return;
            }

            result = mFairy.findPic("rnum" + i + ".png");
            if (result.sim < 0.8f) {

                result = mFairy.findPic(287 + ((i - 1) * 175), 532, 378 + ((i - 1) * 175), 584, "ranks15.png");
                if (result.sim < 0.75f) {
                    mFairy.onTap(287 + ((i - 1) * 175) + 30, 532 - 30, 378 + ((i - 1) * 175) - 30, 584 - 30, "点击未跟随队员", 2000);
                    result = mFairy.findPic(479, 404, 1260, 483, "ranks17.png");
                    mFairy.onTap(0.8f, result, "请离队伍", 1000);
                    i = 1;
                }
            }
            mFairy.sleep(1000);
        }

    }//

    public void start() throws Exception {

        LtLog.e(mFairy.getLineInfo("start activity"));
        result = mFairy.findPic(1000, 11, 1067, 80,
                new String[]{"right.png", "right2.png", "jian1.png"});
        if (result.sim > 0.72f) {
            mFairy.onTap(0.72f, result, "右侧缩放栏", 3000);
        } else {
            result = mFairy.findPic(1107, 228, 1212, 335, new String[]{"home.png", "home2.png", "bao.png"});
            if (result.sim > 0.7f) {
                result = mFairy.findPic(698, 14, 1092, 191, "a.png");
                if (result.sim < 0.7f) {
                    mFairy.onTap(1029, 40, 1041, 53, "右侧缩放栏", 3000);
                }
            }
        }
    }//k

    public void initTime() {
        mainTime = (int) System.currentTimeMillis() / 1000;
    }

    public int getTime() {
        return (int) System.currentTimeMillis() / 1000 - mainTime;
    }

    public boolean EndTask(Task task) throws Exception {

        if (task.list.size() == 0) {
            close();
            switch (FINISH) {
                case 0:
                    LtLog.e(mFairy.getLineInfo("勾选任务已经全部完成,End!" + AtFairyConfig.getTaskID()));
                    mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
                    break;
                case 16901:
                    LtLog.e(mFairy.getLineInfo("勾选任务已经全部完成,End!---中途出现战斗失败。"));
                    mFairy.finish(FAIRY_TYPE_TASK, AtFairyConfig.getTaskID(), FINISH);
                    break;
                case 16903:
                    LtLog.e(mFairy.getLineInfo("勾选任务已经全部完成,End!---疯狂石头摆摊出现异常。"));
                    mFairy.finish(FAIRY_TYPE_TASK, AtFairyConfig.getTaskID(), FINISH);
                    break;
                case 16905:
                    LtLog.e(mFairy.getLineInfo("勾选任务已经全部完成,End!---生活任务出现异常。"));
                    mFairy.finish(FAIRY_TYPE_TASK, AtFairyConfig.getTaskID(), FINISH);
                    break;
            }
            mFairy.sleep(3000);
            return true;
        } else {
            LtLog.e("                                         " +
                    "                                              " +
                    "【当前任务:" + task.list.get(0) + "】");
            return false;
        }
    }

    public int bossTime() throws InterruptedException {
        return (int) System.currentTimeMillis() / 1000 - bossTime;
    }//

    public void bossTimeInit() throws InterruptedException {
        bossTime = (int) System.currentTimeMillis() / 1000;
    }

    public void zhTime(int t) throws Exception {
        if (System.currentTimeMillis() - zhTime > (t * 1000)) {
            result = mFairy.findPic("zhaoji.png");
            mFairy.onTap(0.8f, result, "召集", 1000);
            zhTime = System.currentTimeMillis();
        }
    }//


}
