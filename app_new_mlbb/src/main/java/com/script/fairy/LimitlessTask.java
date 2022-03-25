package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.opencv.core.Mat;


/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class LimitlessTask {//无限任务
    public GamePublicFuntion gamePublicFunction;
    public AtFairyImpl mFairy;
    private FindResult result;
    private FindResult result1;

    public LimitlessTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFunction = new GamePublicFuntion(ypFairy);
    }

    long time;
    public static int JS;

    /*
        无限任务
     */
    public void Knights() throws Exception {
        int bj = 0;
        int m = 0;
        int err = 0;
        int num = 0;
        int s = 0;
        int count3 = 0;
        gamePublicFunction.rank();
        while (true) {
            LtLog.e("骑士团任务,bj:" + bj);
            if (bj == 1 || bj == 2) {
                result = mFairy.findPic("UIqst.png");
                if (result.sim > 0.85f) {
                    err = 0;
                } else {
                    Thread.sleep(1000);
                    err++;
                    if (err > 30) {
                        bj = 0;
                    }
                }
            }
            if (bj == 0) {
                gamePublicFunction.init();
                int bool = gamePublicFunction.activity(1, 3, "qst.png");
                if (bool == 1) {
                    break;
                }
                if (bool == 2) {
                    continue;
                }
                count3 = 0;
                m = 0;
                bj = 1;
                s = 0;
                num = 0;
            }

            if (bj == 1) {
                JS = 0;
                result = mFairy.findPic(993, 428, 1250, 717, "qst1.png");
                mFairy.onTap(0.85f, result,  "骑士团任务", 3000);

                result = mFairy.findPic("UIqst.png");
                if (result.sim > 0.85f) {
                    Mat mat;
                    result = mFairy.findPic(161, 156, 384, 218, "qst2.png");
                    if (result.sim > 0.85f) {
                        result = mFairy.findPic(171, 273, 353, 311, "xd.png");
                        if (result.sim > 0.85f) {
                            JS++;
                            mat = mFairy.getScreenMat(result.x + 60, result.y, 88, 26, 1, 0, 0, 1);

                            mFairy.onTap(0.85f, result,  "限定任务", 2000);

                            result = mFairy.findPic("jq.png");
                            mFairy.onTap(0.9f, result,  "接取任务", 2000);

                        } else {
                            mat = mFairy.getScreenMat(207, 278, 102, 26, 1, 0, 0, 1);
                        }
                    } else {
                        result = mFairy.findPic(169, 166, 350, 207, "xd.png");
                        if (result.sim > 0.85f) {
                            JS++;
                            mat = mFairy.getScreenMat(result.x + 60, result.y, 88, 26, 1, 0, 0, 1);

                            mFairy.onTap(0.85f, result,  "限定任务", 2000);

                            result = mFairy.findPic("jq.png");
                            mFairy.onTap(0.9f, result,  "接取任务", 2000);

                        } else {
                            mat = mFairy.getScreenMat(207, 176, 102, 26, 1, 0, 0, 1);
                        }
                    }
                    int le = level();
                    int l = le;
                    for (int i = 0; i < l; i++) {
                        Grade(mat, s, le);
                        s++;
                        le--;
                        if (JS >= 4) {
                            break;
                        }
                    }
                    bj = 2;
                }
            }
            if (bj == 2) {
                gamePublicFunction.activity(1, 3, "qst.png");

                result = mFairy.findPic(993, 428, 1250, 717, "qst1.png");
                mFairy.onTap(0.85f, result,  "骑士团任务", 5000);

                result = mFairy.findPic("UIqst.png");
                if (result.sim > 0.75f) {

                    result = mFairy.findPic(185, 191 + (m * 100), 330, 278 + (m * 100), "jxz.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result,  "任务进行中", 2000);

                        if (m > 3) {
                            result = mFairy.findPic(185, 191 + ((m + 1) * 100), 330, 278 + ((m + 1) * 100), "jxz.png");
                            if (result.sim < 0.85f) {
                                LtLog.e("这是最后一个进行的任务");
                                m = 5;
                            }
                        }
                        mFairy.onTap(1001, 640,1002,641, "前往", 22000);
                    }

                    m++;
                    if (m >= 5) {
                        LtLog.e("进行中的任务已经接取完毕,bj=3");
                        bj = 3;
                        time = System.currentTimeMillis();
                    } else {
                        KnightsChat();
                        gamePublicFunction.battleEnd();
                    }
                }
            }


            if (bj == 3) {
                gamePublicFunction.close();
                result =  mFairy.findPic( new String[]{
                        "chat.png", "chat1.png"});
                if (result.sim > 0.95f) {
                    if (System.currentTimeMillis() - time > 120000) {
                        LtLog.e("提交任务中...End!");
                        bj = 4;
                    } else {
                        gamePublicFunction.chat();
                    }
                }
                result = mFairy.findPic("battle.png");
                mFairy.onTap(0.96f, result,  "点击自动战斗", 1000);

                result = mFairy.findPic("ba.png");
                if (result.sim > 0.85f) {
                    count3 = 0;
                }

                if (gamePublicFunction.stopJudge() == 1) {
                    s = gamePublicFunction.seekTask("daily.png");
                }

                LtLog.e("骑士团, count:" + count3);
                count3++;
                Thread.sleep(2000);
                if (count3 > 15 || s == 1) {
                    bj = 4;
                }
            }
            if (bj == 4) {
                result = mFairy.findPic("UIqst.png");
                if (result.sim > 0.85f) {
                    result = mFairy.findPic(185, 191 + (num * 100), 330, 278 + (num * 100), "jxz.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result,  "任务进行中", 2000);

                        /*result = mFairy.findPic(185, 191 + ((num + 1) * 100), 330, 278 + ((num + 1) * 100), "jxz.png"));
                        if (result.sim < 0.85f) {
                            LtLog.e("这是最后一个进行的任务"));
                            m2 = 4;
                        }*/

                        mFairy.onTap(1001, 640, 1002,641,"前往", 22000);


                        for (int i = 0; i < 5; i++) {
                            result =  mFairy.findPic(new String[]{"chat.png", "chat1.png"});
                            if (result.sim > 0.85f) {
                                mFairy.onTap(0.85f, result, "聊天", 2000);
                                i = 0;
                                num--;
                            } else {
                                mFairy.onTap(576, 329,577,330, "!", 500);
                            }
                        }
                        gamePublicFunction.battleEnd();
                    }
                    num++;
                    if (num >= 5) {
                        LtLog.e("没有要完成的任务");
                        bj = 0;
                    }
                } else {
                    gamePublicFunction.init();
                    gamePublicFunction.activity(1, 3, "qst.png");
                    result = mFairy.findPic(993, 428, 1250, 717, "qst1.png");
                    mFairy.onTap(0.85f, result,  "骑士团任务", 3000);
                }

            }
        }
    }//骑士团

    public int level() throws Exception {
        result = mFairy.findPic(345, 152, 389, 669, "s5.png");
        if (result.sim > 0.9f) {
            LtLog.e("S级");
            return 5;
        }
        result = mFairy.findPic(345, 152, 389, 669, "s4.png");
        if (result.sim > 0.9f) {
            LtLog.e("A级");
            return 4;
        }
        result = mFairy.findPic(345, 152, 389, 669, "s3.png");
        if (result.sim > 0.9f) {
            LtLog.e("B级");
            return 3;
        }
        result = mFairy.findPic(345, 152, 389, 669, "s2.png");
        if (result.sim > 0.9f) {
            LtLog.e("C级");
            return 2;
        }
        result = mFairy.findPic(345, 152, 389, 669, "s1.png");
        if (result.sim > 0.9f) {
            LtLog.e("D级");
            return 1;
        }
        return 5;
    }//

    public void Grade(Mat mat, int num, int level) throws Exception {
        if (num == 0) {
            for (int i = 0; i < 5; i++) {
                result = mFairy.findPic(144, 141 + (i * 100), 400, 241 + (i * 100), "s" + level + ".png");
                if (result.sim > 0.85f) {
                    result = mFairy.findPic(144, 141 + (i * 100), 400, 241 + (i * 100), "qst2.png");
                    if (result.sim < 0.85f) {
                        Mat mat1 = mFairy.getScreenMat(144, 141 + (i * 100), 256, 100, 1, 0, 0, 1);
                        result = mFairy.matchMat(0, 0, mat, mat1);
                        if (result.sim > 0.85f) {
                            mFairy.tap(result.x + 144, result.y + 141 + (i * 100));
                            Thread.sleep(2000);
                            result = mFairy.findPic("jq.png");
                            if (result.sim > 0.9f) {
                                mFairy.onTap(0.9f, result,  "接取任务", 2000);
                                JS++;
                            }
                        }
                        Thread.sleep(2000);
                    }
                }
            }
        } else {
            if (num != 1) {
                for (int o = 0; o < 3; o++) {
                    mFairy.ranSwipe(197, 236, 263, 602, 0, 1000);
                    Thread.sleep(200);
                }
            }
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 5; i++) {
                    result = mFairy.findPic(144, 141 + (i * 100), 400, 241 + (i * 100), "s" + level + ".png");
                    if (result.sim > 0.85f) {
                        result = mFairy.findPic(144, 141 + (i * 100), 400, 241 + (i * 100), "qst2.png");
                        if (result.sim < 0.85f) {
                            Mat mat1 = mFairy.getScreenMat(144, 141 + (i * 100), 256, 100, 1, 0, 0, 1);
                            result = mFairy.matchMat(0, 0, mat, mat1);
                            if (result.sim > 0.85f) {
                                mFairy.tap(result.x + 144, result.y + 141 + (i * 100));
                                Thread.sleep(2000);
                                result = mFairy.findPic("jq.png");
                                if (result.sim > 0.9f) {
                                    mFairy.onTap(0.9f, result,  "接取任务", 2000);
                                    JS++;
                                }
                            }
                            Thread.sleep(2000);
                        }
                    }
                }
                if (j != 2) {
                    mFairy.touchDown(2, 269, 570);
                    mFairy.touchMove(2, 269, 100, 2000);
                    Thread.sleep(2000);
                    LtLog.e("滑动找相同的任务");
                    mFairy.touchUp(2);
                    Thread.sleep(2000);
                }

            }
        }


    }//选择等级

    public void KnightsChat() throws Exception {
        for (int i = 0; i < 5; i++) {
            result =  mFairy.findPic( new String[]{
                    "chat.png", "chat1.png"});
            if (result.sim > 0.85f) {
                mFairy.onTap(0.85f, result, "聊天", 2000);
                i = 0;
            } else {
                mFairy.onTap(576, 329,577,330, "!", 500);
            }
        }
    }

    public void prestige() throws Exception {
        int id;
        int num = 0;
        String str = AtFairyConfig.getOption("cz");
        if (str.equals("")) {
            id = 1;
        } else {
            id = Integer.parseInt(str);
        }
        String name = "c" + id + ".png";
        int bj = 0;
        int count = 0;

        gamePublicFunction.rank();
        while (true) {
            LtLog.e("声望任务,bj:" + bj);
            if (bj == 0) {
                gamePublicFunction.init();
                int bool = gamePublicFunction.activity(1, 7, name);
                if (bool == 1) {
                    break;
                }
                if (bool == 2) {
                    continue;
                }
                bj = 1;

            }

            if (bj == 1) {
                result =  mFairy.findPic(new String[]{
                        "chat.png", "chat1.png"});
                mFairy.onTap(0.85f, result, "聊天", 2000);

                result = mFairy.findPic("sw5.png");
                if (result.sim > 0.85f) {
                    for (int i = 0; i < 3; i++) {
                        result = mFairy.findPic(106, 167, 831, 688, "sw4.png");
                        if (result.sim > 0.85f) {
                            count = 0;
                            mFairy.tap(result.x + 150, result.y);
                            Thread.sleep(2000);
                            mFairy.onTap(1026, 657,1027,658, "购买", 1000);
                            Thread.sleep(5000);
                            break;
                        } else {
                            mFairy.onTap(954, 116, 965, 131,"",2000);
                        }
                    }
                } else {
                    gamePublicFunction.close();
                }
                result = mFairy.findPic(999, 417, 1275, 717, "sw.png");
                mFairy.onTap(0.85f, result,  "声望任务", 1000);

                result = mFairy.findPic(999, 417, 1275, 717, "sw6.png");
                mFairy.onTap(0.85f, result,  "使用", 1000);

                result = mFairy.findPic(999, 417, 1275, 717, "sw1.png");
                mFairy.onTap(0.85f, result,  "接受委托", 1000);

                result = mFairy.findPic(999, 417, 1275, 717, "sw3.png");
                mFairy.onTap(0.85f, result,  "是", 1000);

                result = mFairy.findPic("battle.png");
                mFairy.onTap(0.96f, result,  "点击自动战斗", 1000);

                result = mFairy.findPic("ba.png");
                if (result.sim > 0.85f) {
                    count = 0;
                }
                if (gamePublicFunction.stopJudge() == 1) {
                    num = gamePublicFunction.seekTask("sw2.png");
                }
                LtLog.e("声望任务, count:" + count);
                count++;
                Thread.sleep(2000);
                if (count > 15 || num == 1) {

                }
            }
        }
    }//声望任务

    public void gd() throws Exception {
        time = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - time > 60000) {
                LtLog.e("跟队中...");
            }
            result = mFairy.findPic("gd.png");
            mFairy.onTap(0.85f, result,  "确定", 1000);
            result =  mFairy.findPic(new String[]{
                    "chat.png", "chat1.png"});
            if (result.sim > 0.85f) {
                mFairy.onTap(0.85f, result, "聊天", 2000);
            }
            result1 =  mFairy.findPic(569, 421, 957, 626, "goto3.png");
            if (result1.sim >= 0.8) {

                mFairy.onTap(0.8f,result1,"goto3",1000);
            }
            result1 =  mFairy.findPic(623, 85, 759, 149, "itemBank.png");
            if (result1.sim >= 0.8) {
                mFairy.onTap(0.8f,result1,259, 364, 1023, 568,"itemBank",1000);
            }

        }


    }//跟队

}
