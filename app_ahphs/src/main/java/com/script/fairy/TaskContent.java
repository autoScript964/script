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
    private AtFairyImpl mFairy;
    private String name;
    public static int err = 0;
    public Map<String, Integer> m = new HashMap<>();
    public Map<String, Long> tm = new HashMap<>();
    GamePublicFuntion gamePublicFuntion = null;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTaskName(int taskContentNum) {
        err = 0;
        this.taskContentNum = taskContentNum;
        LtLog.e("                                                                                        【 跳转 TaskName: " + taskContentNum + "】");

    }

    public void setTaskEnd() {
        err = 0;
        taskContentEnd = true;
    }

    public TaskContent(AtFairyImpl mFairy, String name) throws Exception {
        this.mFairy = mFairy;
        this.name = name;
        oneSecond = 0;

        /*** 进入任务循环
         */
        gamePublicFuntion = new GamePublicFuntion(mFairy);
        initTaskContent(name);
    }

    public void initTaskContent(String name) throws Exception {
        create();
        err = 0;
        while (mFairy.condit()) {
            mFairy.touchUp();
            Thread.sleep(300);
            if (taskContentEnd) {
                LtLog.e("                                                                                        【 " + getName() + " 任务结束,End!】");
                LtLog.e("                                                                                        【 切换任务中.....】");
                LtLog.e("                                                                                        【 切换任务中.....】");
                LtLog.e("                                                                                        【 切换任务中.....】");
                break;
            }

            if (taskContentNum == 0) {
                LtLog.e("                                                                                        【 " + getName() + " init】");
                init();
                err = 0;
            }

            if (taskContentNum == 1) {
                if (err >1 || timeMap("errCount",30000)) {
                    LtLog.e("                                                                                        【 " + getName() + " content_01  err:" + err + "】");
                }
                content_01();
            }

            if (taskContentNum == 2) {
                if (err >1 || timeMap("errCount",30000)) {
                    LtLog.e("                                                                                        【 " + getName() + " content_02  err:" + err + "】");
                }
                content_02();
            }

            if (taskContentNum == 3) {
                if (err >1 || timeMap("errCount",30000)) {
                    LtLog.e("                                                                                        【 " + getName() + " content_03  err:" + err + "】");
                }
                content_03();
            }
            if (taskContentNum == 4) {
                if (err >1 || timeMap("errCount",30000)) {
                    LtLog.e("                                                                                        【 " + getName() + " content_04  err:" + err + "】");
                }
                content_04();
            }
            if (taskContentNum == 5) {
                if (err >1 || timeMap("errCount",30000)) {
                    LtLog.e("                                                                                        【 " + getName() + " content_05  err:" + err + "】");
                }
                content_05();
            }
            inOperation();
        }
    }

    void create() throws Exception {
        m = new HashMap<>();
        tm = new HashMap<>();
        judgeOneSecond=0;
    }

    void init() throws Exception {
    }

    void content_01() throws Exception {
    }

    void content_02() throws Exception {
    }

    void inOperation() throws Exception {
    }

    void content_03() throws Exception {
    }

    void content_04() throws Exception {
    }

    void content_05() throws Exception {
    }

    /**
     * 超时处理
     **/
    boolean timeCount(int num, int taskContentNum) {
        err++;
        if (err >= num) {
            if (taskContentNum == 99) {
                taskContentEnd = true;
            } else {
                this.taskContentNum = taskContentNum;
            }
            err = 0;
            return true;
        }
        return false;
    }

    /**
     * 快速找图
     **/
    boolean mapCount(float sim, int x, int y, int x1, int y1, String name) throws Exception {
        for (int i = 0; i < 5; i++) {
            FindResult result = mFairy.findPic(x, y, x1, y1, name);
            if (result.sim >= sim) {
                return true;
            }
        }
        return false;
    }

    boolean mapCount(float sim, String name) throws Exception {
        for (int i = 0; i < 5; i++) {
            FindResult result = mFairy.findPic(name);
            if (result.sim >= sim) {
                return true;
            }
        }
        return false;
    }

    /**
     * 次数判断
     **/
    void frequencyInit(String key) {
        if (m.containsKey(key)) {
            m.put(key, 0);
        }
    }

    boolean frequencyMap(String key, int frequen) {
        if (m.containsKey(key)) {
            m.put(key, m.get(key) + 1);
            if (m.get(key) >= frequen) {
                m.put(key, 0);
                return true;
            }
        } else {
            m.put(key, 1);
        }
        return false;
    }

    void timeMapInit(String key){
        if (tm.containsKey(key)) {
            tm.put(key, System.currentTimeMillis());
        }
    }

   boolean timeMap(String key, long sheep) {
        long s = System.currentTimeMillis();
        if (tm.containsKey(key)) {
            if (s - tm.get(key) >= sheep) {
                tm.put(key, s);
                return true;
            }
        } else {
            tm.put(key, s);
            return false;
        }
        return false;
    }

    public int oneSecond = 0;

    boolean oneSecond() throws Exception {
        if (oneSecond == 0) {
            oneSecond = 1;
            return true;
        }
        return false;
    }//只执行一次

    public int judgeOneSecond = 0;
    boolean judgeOneSecond() throws Exception {
        if (judgeOneSecond == 0) {
            return true;
        }
        return false;
    }//可定义执行一次

    public int modularLookup = 0;
    public FindResult modularLookup(int x, int y, int x1, int y1, String img) throws Exception {
        int calculationHigh = (y1 - y) * modularLookup;
        return mFairy.findPic(x, y + calculationHigh, x1, y1 + calculationHigh, img);
    }//模块查找

    public static  long getTimeStamp(String string) throws Exception {
        if(string.equals("")) {
            return 0;
        }

        String[] arrstr = string.split("\\|\\|");
        String[] new_arrstr;
        if (arrstr.length == 1) {
            new_arrstr = arrstr[0].split(":");
        } else {
            if(!arrstr[0].equals("1")){
                return 0;
            }
            new_arrstr = arrstr[1].split(":");
        }


        return (long)Integer.parseInt(new_arrstr[0]) * 3600000
                + Integer.parseInt(new_arrstr[1]) * 60000
                + Integer.parseInt(new_arrstr[2]) * 1000;
    }

    public  static  int getNumberAssembly(String string) throws Exception {
        if(string.equals("")){
            return -1;
        }
        String[] arrstr = string.split("\\|\\|");
        if (arrstr.length == 1) {
            if(Integer.parseInt(arrstr[0])==0){
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

    /**
     * 滑动
     **/
    class Slide {
        private int[] initSlideRangeIndex = new int[4];
        private int[] slideRangeIndex = new int[4];
        private int moveTime = 200;
        private long endTime = 1000;
        private int moveInitTime = 200;
        private long endInitTime = 1000;

        public Slide(int[] slideXY) {
            slideRangeIndex = slideXY;
            initSlideRangeIndex = slideXY;
            LtLog.e("new Slide >>>>>");
        }


        public Slide(int[] initXY, int[] slideXY) {
            slideRangeIndex = slideXY;
            initSlideRangeIndex = initXY;
            LtLog.e("new Slide >>>>>");
        }

        public void slideRange(int[] count, int type, int init) throws Exception {
            if (count.length > 0) {
                if (err == count[0]) {
                    LtLog.e(mFairy.getLineInfo("slideRange init滑动>>>"));
                    LtLog.e(mFairy.getLineInfo(initSlideRangeIndex[0] + "," + initSlideRangeIndex[1] + "," +
                            initSlideRangeIndex[2] + "," + initSlideRangeIndex[3] + ""));
                    for (int i = 0; i < 5; i++) {
                        mFairy.ranSwipe(initSlideRangeIndex[0], initSlideRangeIndex[1], initSlideRangeIndex[2], initSlideRangeIndex[3], init, moveInitTime, endInitTime);
                    }
                    Thread.sleep(endTime);
                }

                for (int i = 1; i < count.length; i++) {
                    if (err == count[i]) {
                        LtLog.e(mFairy.getLineInfo("slideRange type滑动>>>"));
                        switch (type) {
                            case 0:
                                mFairy.touchDown(type, slideRangeIndex[0], slideRangeIndex[1]);
                                mFairy.touchMove(type, slideRangeIndex[0], slideRangeIndex[3], moveTime);
                                Thread.sleep(endTime);
                                mFairy.touchUp(type);
                                break;
                            case 1:
                                mFairy.touchDown(type, slideRangeIndex[0], slideRangeIndex[1]);
                                mFairy.touchMove(type, slideRangeIndex[2], slideRangeIndex[1], moveTime);
                                Thread.sleep(endTime);
                                mFairy.touchUp(type);
                                break;
                            case 2:
                                mFairy.touchDown(type, slideRangeIndex[0], slideRangeIndex[3]);
                                mFairy.touchMove(type, slideRangeIndex[0], slideRangeIndex[1], moveTime);
                                Thread.sleep(endTime);
                                mFairy.touchUp(type);
                                break;
                            case 3:
                                mFairy.touchDown(type, slideRangeIndex[2], slideRangeIndex[1]);
                                mFairy.touchMove(type, slideRangeIndex[0], slideRangeIndex[1], moveTime);
                                Thread.sleep(endTime);
                                mFairy.touchUp(type);
                                break;
                        }
                    }
                }
            }
        }

        public void slideRange(int[] count, int type) throws Exception {
            if (count.length > 0) {
                for (int i = 0; i < count.length; i++) {
                    if (err == count[i]) {
                        LtLog.e(mFairy.getLineInfo("slideRange type滑动>>>"));
                        switch (type) {
                            case 0:
                                mFairy.touchDown(type, slideRangeIndex[0], slideRangeIndex[1]);
                                mFairy.touchMove(type, slideRangeIndex[0], slideRangeIndex[3], moveTime);
                                Thread.sleep(endTime);
                                mFairy.touchUp(type);
                                break;
                            case 1:
                                mFairy.touchDown(type, slideRangeIndex[0], slideRangeIndex[1]);
                                mFairy.touchMove(type, slideRangeIndex[2], slideRangeIndex[1], moveTime);
                                Thread.sleep(endTime);
                                mFairy.touchUp(type);
                                break;
                            case 2:
                                mFairy.touchDown(type, slideRangeIndex[0], slideRangeIndex[3]);
                                mFairy.touchMove(type, slideRangeIndex[0], slideRangeIndex[1], moveTime);
                                Thread.sleep(endTime);
                                mFairy.touchUp(type);
                                break;
                            case 3:
                                mFairy.touchDown(type, slideRangeIndex[2], slideRangeIndex[1]);
                                mFairy.touchMove(type, slideRangeIndex[0], slideRangeIndex[1], moveTime);
                                Thread.sleep(endTime);
                                mFairy.touchUp(type);
                                break;
                        }
                    }
                }
            }
        }

        /****/
        public void slideRange(int num, int[] count, int type, int init) throws Exception {
            if (count.length > 0) {
                if (num == count[0]) {
                    LtLog.e(mFairy.getLineInfo("slideRange init滑动>>>"));
                    LtLog.e(mFairy.getLineInfo(slideRangeIndex[0] + "," + slideRangeIndex[1] + "," +
                            slideRangeIndex[2] + "," + slideRangeIndex[3] + ""));
                    for (int i = 0; i < 3; i++) {
                        mFairy.ranSwipe(slideRangeIndex[0], slideRangeIndex[1], slideRangeIndex[2], slideRangeIndex[3], init, moveInitTime, endInitTime);
                    }
                    Thread.sleep(endTime);
                }

                for (int i = 1; i < count.length; i++) {
                    if (num == count[i]) {
                        LtLog.e(mFairy.getLineInfo("slideRange type滑动>>>"));
                        LtLog.e(mFairy.getLineInfo(slideRangeIndex[0] + "," + slideRangeIndex[1] + "," +
                                slideRangeIndex[2] + "," + slideRangeIndex[3] + ""));
                        mFairy.ranSwipe(slideRangeIndex[0], slideRangeIndex[1], slideRangeIndex[2], slideRangeIndex[3], type, moveTime, endTime);

                    }
                }
            }
        }

        public void slideRange(int num, int[] count, int type) throws Exception {
            LtLog.e(mFairy.getLineInfo("slideRange >>>"));
            if (count.length > 0) {
                for (int i = 0; i < count.length; i++) {
                    if (num == count[i]) {
                        LtLog.e(mFairy.getLineInfo("slideRange type滑动>>>"));
                        LtLog.e(mFairy.getLineInfo(slideRangeIndex[0] + "," + slideRangeIndex[1] + "," +
                                slideRangeIndex[2] + "," + slideRangeIndex[3] + ""));
                        mFairy.ranSwipe(slideRangeIndex[0], slideRangeIndex[1], slideRangeIndex[2], slideRangeIndex[3], type, moveTime, endTime);
                    }
                }
            }
        }
    }

}
