package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class TimingActivity {

    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    CommonFunction commonFunction;
    GamePublicFunction gamePublicFunction;

    public TimingActivity(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        commonFunction = new CommonFunction(mFairy);
        gamePublicFunction = new GamePublicFunction(mFairy);
    }

    //限时活动
    public int timeLimitActivity() throws Exception {
        int h, m, w;

        h = commonFunction.DateHour();
        m = commonFunction.DateMinute();
        w = commonFunction.Week();
        if (h >= 10 && h < 14 && AtFairyConfig.getOption("kjdt").equals("1") && TaskMain.xshd_1 == 0) {
            Imperial();
            TaskMain.xshd_1 = 1;
            return 1;
        }
        if (h >= 17 && h < 20 && AtFairyConfig.getOption("kjdt").equals("1") && TaskMain.xshd_2 == 0) {
            Imperial();
            TaskMain.xshd_2 = 1;
            return 1;
        }
        return 0;
    }

    //科举答题
    public void Imperial() throws Exception {
        int bj = 0;
        int ret;
        int numcolor, numcolor1;
        int js_1 = 0, js_2 = 0, js_3 = 0, js_4 = 0, js_5 = 0, js_6 = 0, js_7 = 0;
       /* Answer answer = new Answer(mFairy);
        Map<String, List<Integer>> answerMap = answer.initializationMap();
        answerMap.put("game_id", Arrays.asList(12));
        answerMap.put("title_Range", Arrays.asList(386,209,511,115));

        answerMap.put("answer_Range", Arrays.asList(523,470,524,538,526,603,         234,46));
        answerMap.put("answer_Range1", Arrays.asList(375,464,898,523,   379,534,896,586,  383,598,894,646));
        // answerMap.put("answer_Range2", Arrays.asList(520,481,  949,481, 552,578,   958,580));
        answerMap.put("pic_range", Arrays.asList(369,451,453,656));*/
        //  answerMap.put("right_pic_Relative_range", Arrays.asList(-200, -10, 232, 33));//没用
        while (mFairy.condit()) {
            Thread.sleep(1500);
            LtLog.e(commonFunction.getLineInfo("科举答题中bj=" + bj));
            if (bj == 0) {
                js_1 = 0;
                js_2 = 0;
                js_3 = 0;
                js_4 = 0;
                js_5 = 0;
                js_6 = 0;
                bj = 1;
            }
            if (bj == 1) {
                ret = gamePublicFunction.mission1("kjdt.png", "kjover.png");
                if (ret == 0) {
                    break;
                } else if (ret == 1) {
                    bj = 2;
                }
            }
            if (bj == 2) {
                result = mFairy.findPic2(commonFunction.setImg("kjks.png"));
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "科举界面"));
                if (result.sim > 0.8f) {
                    mFairy.onTap(523, 470, 524, 471, "", 10);
                    Thread.sleep(5000);
                    js_1 = 0;
                } else {
                    js_1++;
                    if (js_1 > 5) {
                        bj = 0;
                    }
                }
            }
        }
    }
}
