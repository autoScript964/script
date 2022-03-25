package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2019/4/11.
 */

public abstract class TaskContent {
    private int taskContentNum = 0;
    private boolean taskContentEnd = false;
    static int Sleep = 1500;
    int err = 0;
    Map<String, Integer> picCountMap = new HashMap<>();
    Map<String, Long> timeKeepMap = new HashMap<>();
    AtFairyImpl ypYxdFairy;

    void setTaskName(int taskContentNum) throws Exception {
        err = 0;
        this.taskContentNum = taskContentNum;
        LtLog.e("                                                                                       【"+logname+"  切换到 content" + taskContentNum + "  err:" + err + "】");
    }

    void setTaskEnd() throws Exception {
        err = 0;
        taskContentEnd = true;
    }

    String logname;

    void taskContent(AtFairyImpl mFairy, String string) throws Exception {
        ypYxdFairy = mFairy;
        logname = string;
        LtLog.e("                                                                                       【"+string+"  content" + taskContentNum + "  err:" + err + "】");
        create();
        while (mFairy.condit()) {
            LtLog.e("                                                                                       【"+string+"  content" + taskContentNum + "  err:" + err + "】");
            if (taskContentEnd) {
                LtLog.e("                                                                                       【"+string+"  当前任务结束,End!】");
                break;
            }
            inOperation();
            if (taskContentNum == 0) {
                content_0();
                continue;
            }
            if (taskContentNum == 1) {
                content_1();
                continue;
            }
            if (taskContentNum == 2) {
                content_2();
                continue;
            }
            if (taskContentNum == 3) {
                content_3();
                continue;
            }
            if (taskContentNum == 4) {
                content_4();
                continue;
            }
            if (taskContentNum == 5) {
                content_5();
                continue;
            }
            if (taskContentNum == 6) {
                content_6();
                continue;
            }
            if (taskContentNum == 7) {
                content_7();
                continue;
            }
            if (taskContentNum == 8) {
                content_8();
                continue;
            }
            if (taskContentNum == 9) {
                content_9();
                continue;
            }
            if (taskContentNum == 10) {
                content_10();
                continue;
            }
        }
    }

    /**
     * 只执行一次
     */
    void create() throws Exception {
    }


    /**
     * 每次while都会执行
     */
    void inOperation() throws Exception {

    }

    /**
     * 对图片就行计次
     *
     * @param sim
     * @param result
     * @param string 给图片设定一个key
     * @return
     * @throws Exception
     */
    int picCount(float sim, FindResult result, String string) throws Exception {
        if (picCountMap.containsKey(string)) {
            if (result.sim > sim) {
                int num = picCountMap.get(string);
                num++;
                picCountMap.put(string, num);
            } else {
                picCountMap.put(string, 0);
            }
        } else {
            picCountMap.put(string, 0);
        }
        //  LtLog.e("计次："+string+"="+picCountMap.get(string));
        return picCountMap.get(string);
    }

    /**
     * 计时
     *
     * @param order  1代表第一次就执行
     * @param t
     * @param string 设置一个key
     * @return
     * @throws Exception
     */
    boolean timekeep(int order, long t, String string) throws Exception {
        if (timeKeepMap.containsKey(string)) {
            if (System.currentTimeMillis() - timeKeepMap.get(string) >= t) {
                timeKeepMap.put(string, System.currentTimeMillis());
                return true;
            }
            // LtLog.e("计时："+string+"="+(System.currentTimeMillis() - timeKeepMap.get(string))+",设置的时间="+t);
        } else {
            timeKeepMap.put(string, System.currentTimeMillis());
            if (order == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 初始化设定的时间
     *
     * @param string
     * @throws Exception
     */
    void timekeepInit(String string) throws Exception {
        timeKeepMap.put(string, System.currentTimeMillis());
    }

    /**
     * 超时处理
     */
    boolean overtime(int num, int taskContentNum) {
        err++;
        if (err >= num) {
            if (taskContentNum == 99) {
                LtLog.e("【异常超时结束当前任务.....】");
                taskContentEnd = true;
            } else {
                LtLog.e("【异常超时切换到" + taskContentNum + ".....】");
                this.taskContentNum = taskContentNum;
            }
            err = 0;
            return true;
        }
        return false;
    }

    /**
     * 处理时间和次数控件
     *
     * @param string
     * @return
     * @throws Exception
     */
    ControlSplit strSplit(String string) throws Exception {

        ControlSplit controlSplit = new ControlSplit();

        String[] arrstr = string.split("\\|\\|");
        if (arrstr.length < 2) {

            String[] arrstr1 = arrstr[0].split(":");
            if (arrstr1.length < 2) {
                controlSplit.count = Integer.parseInt(arrstr1[0]);
                return controlSplit;
            }

            controlSplit.h = Integer.parseInt(arrstr1[0]);
            controlSplit.m = Integer.parseInt(arrstr1[1]);
            controlSplit.s = Integer.parseInt(arrstr1[2]);

            controlSplit.timeMillis = controlSplit.h * 3600000 + controlSplit.m * 60000 + controlSplit.s * 1000;

            return controlSplit;
        }

        controlSplit.choice = Integer.parseInt(arrstr[0]);

        String[] arrstr1 = arrstr[1].split(":");

        if (arrstr1.length < 2) {
            controlSplit.count = Integer.parseInt(arrstr1[0]);
        } else {
            controlSplit.h = Integer.parseInt(arrstr1[0]);
            controlSplit.m = Integer.parseInt(arrstr1[1]);
            controlSplit.s = Integer.parseInt(arrstr1[2]);
            controlSplit.timeMillis = controlSplit.h * 3600000 + controlSplit.m * 60000 + controlSplit.s * 1000;
        }
        return controlSplit;
    }

    void content_0() throws Exception {
    }

    void content_1() throws Exception {
    }

    void content_2() throws Exception {
    }

    void content_3() throws Exception {
    }

    void content_4() throws Exception {
    }

    void content_5() throws Exception {
    }

    void content_6() throws Exception {
    }

    void content_7() throws Exception {
    }

    void content_8() throws Exception {
    }

    void content_9() throws Exception {
    }

    void content_10() throws Exception {
    }

    public class ControlSplit {
        public int choice;
        public int h;
        public int m;
        public int s;
        public long timeMillis;
        public int count;
    }
}
