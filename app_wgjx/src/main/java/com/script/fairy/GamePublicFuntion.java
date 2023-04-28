package com.script.fairy;

import com.example.Answer;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GamePublicFuntion {

    private Answer answer;

    public AtFairyImpl mFairy;
    public FindResult result;
    public long time = System.currentTimeMillis();

    public static ArrayList<String> list = new ArrayList();

    public GamePublicFuntion(AtFairyImpl ypFairy) {

        answer = new Answer(ypFairy);
        mFairy = ypFairy;
    }

    public void init(int type) throws Exception {
        int err = 0;
        boolean home = true;
        while (mFairy.condit()) {
            LtLog.e(mFairy.getLineInfo("GamePublicFuntion init >>>"));

            switch (type) {
                case 1:
                    result = mFairy.findPic("home1.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        mFairy.onTap(0.8f, result, 49, 642, 68, 662, "回城", 1000);
                        home = false;
                    }

                    if (home) {
                        result = mFairy.findPic("home.png");
                        if (result.sim > 0.8f) {
                            err = 0;
                            mFairy.onTap(0.8f, result, 49, 642, 68, 662, "出城 home == true", 500);
                            home = false;
                        }
                    } else {
                        result = mFairy.findPic("home.png");
                        if (result.sim > 0.8f) {
                            LtLog.e(mFairy.getLineInfo("城市界面"));

                            result = mFairy.findPic(124, 138, 227, 232, "sou.png");
                            mFairy.onTap(0.85f, result, "", 500);

                            result = mFairy.findPic("ts.png");
                            mFairy.onTap(0.8f, result, 1212, 655, 1238, 674, "关闭下方缩放栏", 500);

                            return;
                        }
                    }
                    break;
                case 2:
                    result = mFairy.findPic("home.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        mFairy.onTap(0.8f, result, 49, 642, 68, 662, "出城", 1000);
                    }

                    result = mFairy.findPic("home1.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("地图界面"));
                        return;
                    }
                    break;
                case 3:
                    /*result = mFairy.findPic("home1.png");
                    if (result.sim > 0.8f) {
                        err = 0;
                        mFairy.onTap(0.8f, result, 49,642,68,662,"回城", 1500);
                    }*/

                    result = mFairy.findPic(new String[]{"home.png", "home1.png"});
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("城市界面"));

                        result = mFairy.findPic(669, 555, 1274, 712, "position.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "位置图标", 3000);
                        } else if ((result = mFairy.findPic(1131, 354, 1276, 713, "position.png")).sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "位置图标", 3000);
                        } else {
                            result = mFairy.findPic(3, 89, 174, 284, "position.png");
                            mFairy.onTap(0.8f, result, "位置图标", 3000);
                        }
                        /*result= mFairy.findPic(124,138,227,232,"sou.png");
                        mFairy.onTap(0.85f,result,"",500);

                        result = mFairy.findPic("ts.png");
                        mFairy.onTap(0.8f, result, 1212, 655, 1238, 674, "关闭下方缩放栏", 500);*/
                        return;
                    }
                    break;
            }

            close(1);

            yanzheng();

            result = mFairy.findPic(512, 254, 721, 471, new String[]{"chat.png", "chat15.png"});
            mFairy.onTap(0.85f, result, "聊天", 1000);

            result = mFairy.findPic("err1.png");
            mFairy.onTap(0.8f, result, 643, 137, 686, 162, "", 2000);

            FindResult quxiao = mFairy.findPic(571, 294, 1057, 664, "quxiao1.png");
            mFairy.onTap(0.8f, quxiao, "否", 1000);

            result = mFairy.findPic("dinghao.png");
            if (result.sim > 0.8f) {
                mFairy.finish(AtFairyConfig.getTaskID(), 99);
            } else {
                result = mFairy.findPic(487, 295, 759, 693, "ok2.png");
                mFairy.onTap(0.8f, result, "确定", 1000);

                result = mFairy.findPic("ok3.png");
                mFairy.onTap(0.8f, result, "确定", 1000);
            }

            err++;
            if (err > 5) {
                err = 0;
                mFairy.onTap(42, 639, 69, 665, "init_err", 500);
            }
        }

    }//init

    public void close(int num) throws Exception {
        for (int i = 0; i < num; i++) {

            result = mFairy.findPic(916, 10, 1270, 194, new String[]{"close1.png", "close2.png", "close3.png", "close4.png"});
            mFairy.onTap(0.8f, result, "关闭1", 1000);

            result = mFairy.findPic(3, 4, 150, 178, "esc1.png");
            mFairy.onTap(0.8f, result, "返回1", 1000);

            result = mFairy.findPic("close5.png");
            mFairy.onTap(0.8f, result, "关闭2", 1000);

            result = mFairy.findPic(747, 93, 1052, 318, "close6.png");
            mFairy.onTap(0.8f, result, "关闭3", 1000);
        }
    }//关闭

    SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm:ss");
    public int yanzheng_err = 0;
    public long time_err = System.currentTimeMillis();

    public void yanzheng() throws Exception {
        int err = 0;

        FindResult yan1 = mFairy.findPic(400, 65, 1182, 164, "yanzheng3.png");
        mFairy.onTap(0.85f, yan1, "验证箱", 2000);

        FindResult yan2 = mFairy.findPic(400, 326, 1203, 403, "yanzheng.png");
        mFairy.onTap(0.85f, yan2, "验证", 8000);

        FindResult yan3 = mFairy.findPic(647, 636, 902, 714, new String[]{"yanzheng2.png", "yanzheng5.png"});
        if (yan1.sim > 0.85f || yan2.sim > 0.85f || yan3.sim > 0.85f) {

            list.add(df.format(new Date()));

            LtLog.e(mFairy.getLineInfo("验证记录：" + list.size()));

            for (String str : list) {
                LtLog.e(mFairy.getLineInfo(str));
            }

            while (mFairy.condit()) {
                LtLog.e(mFairy.getLineInfo("验证中>>>"));
                Thread.sleep(1000);

                result = mFairy.findPic(376, 269, 890, 510, "yanzheng6.png");
                if (result.sim > 0.85f) {
                    err = 0;
                }

                result = mFairy.findPic(647, 500, 902, 714, new String[]{"yanzheng2.png", "yanzheng5.png","yanzheng7.png"});
                if (result.sim > 0.8f) {
                    break;
                } else {
                    if (err % 3 == 0) {
                        result = mFairy.findPic(400, 326, 1203, 403, "yanzheng.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "验证", 8000);
                            err = 0;
                            continue;
                        }

                        result = mFairy.findPic(400, 65, 1182, 164, "yanzheng3.png");
                        mFairy.onTap(0.85f, result, "验证箱", 2000);
                    }
                }

                err++;
                if (err > 10) {
                    return;
                }
            }


         //好爱网址 http://feng.suanst.com/


            String result_ha =answer.haoai(382, 77, 505, 640,"6005");

            if (!result_ha.equals("") && result_ha != null) {
                try {
                    String[] aa = result_ha.split("\\|");

                    for (String st : aa) {
                        mFairy.tap(Integer.parseInt(st.split(",")[0]) + 382, Integer.parseInt(st.split(",")[1]) + 77);
                        Thread.sleep(500);
                    }

                    mFairy.onTap(747,587,784,603, "确定", 3000);

                    result = mFairy.findPic("yanzheng2.png");
                    mFairy.onTap(0.8f, result, 485, 667, 518, 692, "没有验证出来,刷新", 5000);

                    if (System.currentTimeMillis() - time_err < 180000) {
                        yanzheng_err++;
                    } else {
                        yanzheng_err = 0;
                    }

                    time_err = System.currentTimeMillis();

                } catch (Exception e) {
                    LtLog.e(mFairy.getLineInfo("验证报错!!!"));
                    LtLog.e(mFairy.getLineInfo("验证报错!!!"));
                    LtLog.e(mFairy.getLineInfo("验证报错!!!"));
                    yanzheng_err++;
                }
            } else {
                LtLog.e(mFairy.getLineInfo("验证为空!!!"));
                LtLog.e(mFairy.getLineInfo("验证为空!!!"));
                LtLog.e(mFairy.getLineInfo("验证为空!!!"));
                yanzheng_err++;
            }
        }

        if (yanzheng_err >= 3) {
            ImageJudgeThread.EXIT = true;
            mFairy.finish(AtFairyConfig.getTaskID(), 58801);
        }

    }//验证
}
