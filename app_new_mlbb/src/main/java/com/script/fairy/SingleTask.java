package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

public class SingleTask {
    public GamePublicFuntion gamePublicFunction;
    public AtFairyImpl mFairy;
    private FindResult result;
    private FindResult result1;
    private FindResult result2;
    long time;
    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFunction = new GamePublicFuntion(ypFairy);
    }

    /*  单人任务  */
    public void college() throws Exception {
        int bj = 0;
        int count = 0;
        int a = 0;
        int num = 0;
        gamePublicFunction.rank();
        while (true) {
            LtLog.e("学院任务,bj:" + bj);

            if (bj == 0) {
                gamePublicFunction.init();
                int bool = activity();
                if (bool == 1) {
                    break;
                }
                if (bool == 2) {
                    continue;
                }
                bj = 1;
                count = 0;
                num = 0;
                time = System.currentTimeMillis();
            }

            if (bj == 1) {
                result = mFairy.findPic(new String[]{"chat.png", "chat1.png"});
                if (result.sim > 0.85f) {
                    if (System.currentTimeMillis() - time > 120000) {
                        bj = 0;
                        continue;
                    }
                    mFairy.onTap(0.85f, result, "聊天", 2000);
                }

                result = mFairy.findPic(1050, 429, 1221, 699, new String[]{"xy1.png", "jh1.png"});
                mFairy.onTap(0.85f, result, "学院任务", 2000);

                result = mFairy.findPic("UIxy.png");
                if (result.sim > 0.85f) {
                    result = mFairy.findPic("xy3.png");
                    mFairy.onTap(0.85f, result,  "全部领取", 2000);

                    result = mFairy.findPic("xy5.png");
                    mFairy.onTap(0.85f,result,  1167, 249, 1168,250,"返回任务", 2000);

                    if (a == 0) {
                        mFairy.onTap(1162, 158, 1163,159,"学院奖励领取", 3000);

                        result = mFairy.findPic("xy2.png");
                        if (result.sim > 0.85f) {
                            for (int i = 0; i < 3; i++) {
                                mFairy.onTap(655, 638,656,639, "领取报酬", 1000);
                            }
                            a = 1;
                            Thread.sleep(2000);
                            mFairy.onTap(1164, 250, 1165,251,"返回任务", 3000);
                        }
                    }
                    result = mFairy.findPic("xy4.png");
                    mFairy.onTap(0.85f, result,  "前往", 2000);

                } else {
                    gamePublicFunction.close();
                }
                result = mFairy.findPic("ba.png");
                if (result.sim > 0.85f) {
                    LtLog.e("战斗中...");
                    Thread.sleep(2000);
                    count = 0;
                }

                if (gamePublicFunction.stopJudge() == 1) {
                    num = gamePublicFunction.seekTask("daily.png");
                }

                LtLog.e("学院任务, count:" + count);
                count++;
                Thread.sleep(2000);
                if (count > 15 || num == 1) {
                    bj = 0;
                }
            }
        }
    }//学院任务 ***

    public int activity() throws Exception {
        int o = 0;
        int count = 0;
        int err = 0;
        while (true) {
            result = mFairy.findPic("activity.png");
            mFairy.onTap(0.85f, result,  "活动", 2000);

            result = mFairy.findPic("UIactivity.png");
            if (result.sim > 0.85f) {
                if (o == 0) {
                    mFairy.onTap(1163, 169,1164,170, "日常", 1000);
                    mFairy.onTap(211, 155,212,156, "高收益", 1000);
                    o = 1;
                }

                result1 = mFairy.findPic(76, 188, 1096, 686, new String[]{"xy.png", "jh.png"});
                if (result1.sim > 0.85f) {
                    result = mFairy.findPic(result1.x + 211, result1.y - 7,
                            result1.x + 365, result1.y + 77, "pp.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result,  "前往", 6000);
                        return 0;
                    } else {
                        count++;
                        result = mFairy.findPic(result1.x + 211, result1.y - 7,
                                result1.x + 365, result1.y + 77, "activityEnd.png");
                        if (result.sim > 0.85f) {
                            LtLog.e("任务已经完成,End!");
                            return 1;
                        }
                    }
                } else {
                    count++;
                }
                if (count == 2 || count == 4 || count == 6 || count == 8) {
                    mFairy.ranSwipe(644, 576, 644, 450, 1500, 1000);
                    Thread.sleep(2000);
                }
            } else {
                gamePublicFunction.close();
                err++;
            }

            if (count > 10) {
                LtLog.e("任务没有找到,End!");
                return 1;
            }
            if (err > 10) {
                LtLog.e("活动异常,End!");
                return 2;
            }
        }
    }//

    public void Bento() throws Exception {
        int bj = 0;
        int count = 0;
        int num = 0;
        gamePublicFunction.rank();
        while (true) {
            LtLog.e("爱心便当,bj:" + bj);

            if (bj == 0) {
                gamePublicFunction.init();
                int bool = gamePublicFunction.activity(1, 2, "bd1.png");
                if (bool == 1) {
                    break;
                }
                if (bool == 2) {
                    continue;
                }
                bj = 1;
                count = 0;
                num = 0;
            }

            if (bj == 1) {

                gamePublicFunction.chat();
                gamePublicFunction.close();

                result = mFairy.findPic(1050, 429, 1221, 699, "bd.png");
                mFairy.onTap(0.85f, result,  "爱心便当", 2000);

                result = mFairy.findPic(1050, 429, 1221, 699, "bd2.png");
                mFairy.onTap(0.85f, result,  "接受任务", 2000);


                result = mFairy.findPic("ba.png");
                if (result.sim > 0.85f) {
                    count = 0;
                }
                if (gamePublicFunction.stopJudge() == 1) {
                    num = gamePublicFunction.seekTask("bd3.png");
                }
                LtLog.e("爱心便当, count:" + count);
                count++;
                Thread.sleep(2000);
                if (count > 12 || num == 1) {
                    bj = 0;
                }


            }
        }
    }//便当 ***

    public void Promise() throws Exception {
        int bj = 0;
        int count = 0;
        gamePublicFunction.rank();
        while (true) {
            LtLog.e("许愿池,bj:" + bj);

            if (bj == 0) {
                gamePublicFunction.init();
                int bool = gamePublicFunction.activity(1, 2, "xyc.png");
                if (bool == 1) {
                    break;
                }
                if (bool == 2) {
                    continue;
                }
                bj = 1;
                count = 0;
            }

            if (bj == 1) {

                result = mFairy.findPic(1050, 429, 1221, 699, "xyc1.png");
                mFairy.onTap(0.85f, result,  "许愿池", 2000);

                result = mFairy.findPic(1050, 429, 1221, 699, "xyc2.png");
                if (result.sim > 0.85f) {
                    count = 0;
                    mFairy.onTap(0.85f, result,  "每日许愿", 2000);
                }
                count++;
                if (count > 15) {
                    bj = 0;
                }


            }
        }
    }//许愿 ***

    public void EverythingGoesWellAndSmoothly() throws Exception {
        int bj = 0;
        int count = 0;
       /* Answer answer = new Answer(mFairy);
        Map<String, List<Integer>> mMap = new HashMap<>();
        mMap.put("game_id", Arrays.asList(109));
        mMap.put("title_Range", Arrays.asList(290, 217, 746, 75));
        mMap.put("answer_Range", Arrays.asList(285, 376, 692, 382, 289, 511, 694, 510, 260, 65));
        mMap.put("answer_Range1", Arrays.asList(
                544, 352, 648, 457,
                949, 355, 1054, 450,
                547, 479, 648, 578,
                959, 481, 1063, 580));
        mMap.put("pic_range", Arrays.asList(467, 322, 1055, 603));*/

        gamePublicFunction.rank();
        while (true) {
            LtLog.e("万事通的考验,bj:" + bj);

            if (bj == 0) {
                gamePublicFunction.init();
                int bool = gamePublicFunction.activity(1, 2, "wst.png");
                if (bool == 1) {
                    break;
                }
                if (bool == 2) {
                    continue;
                }
                bj = 1;
                count = 0;
            }

            if (bj == 1) {

                result = mFairy.findPic(1050, 429, 1221, 699, "wst1.png");
                mFairy.onTap(0.85f, result,  "万事通的考验", 2000);

                result = mFairy.findPic(1050, 429, 1221, 699, "wst3.png");
                mFairy.onTap(0.95f, result,  "今日的考验", 2000);

                result = mFairy.findPic("UIwst.png");
                if (result.sim > 0.85f) {
                    count = 0;
                    mFairy.onTap(388,392,459,423,"A",3000);
                }
                count++;
                Thread.sleep(1000);
                if (count > 10) {
                    bj = 0;
                }
            }
        }


    }//万事通的考验 ***

    public void boos() throws Exception {
        int bj = 0;
        int count = 0;
        gamePublicFunction.rankT();
        while (true) {
            LtLog.e("帮会boos,bj:" + bj);
            if (bj == 0) {
                gamePublicFunction.init();
                while (true) {
                    result = mFairy.findPic("boss.png");
                    mFairy.onTap(0.85f, result,  "缩放栏", 2000);

                    result = mFairy.findPic("boss1.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result,  "家族", 1000);
                        break;
                    }

                }
                bj = 1;
                count = 0;
            }

            if (bj == 1) {
                result = mFairy.findPic("boss2.png");
                mFairy.onTap(0.85f, result,  "活动", 2000);

                result = mFairy.findPic("boss3.png");
                mFairy.onTap(0.85f, result,  "前往挑战", 2000);

                result = mFairy.findPic("ba.png");
                if (result.sim > 0.85f) {
                    count = 0;
                }
                result = mFairy.findPic("UIboos.png");
                if (result.sim > 0.80f) {
                    result = mFairy.findPic(200, 339, 435, 523, "boss5.png");
                    if (result.sim > 0.80f) {
                        mFairy.onTap(0.85f, result,  "领取奖励,帮会boss完成", 1000);
                        gamePublicFunction.init();
                        break;
                    } else {
                        result = mFairy.findPic(200, 339, 435, 523, "boss4.png");
                        mFairy.onTap(0.80f, result,  "挑战", 3000);
                    }
                    result = mFairy.findPic(200, 339, 435, 523, "bossOver.png");
                    if (result.sim > 0.75f) {
                        gamePublicFunction.init();
                        break;
                    }
                    result = mFairy.findPic(200, 339, 435, 523, "bossOver1.png");
                    if (result.sim > 0.75f) {
                        gamePublicFunction.init();
                        break;
                    }
                }
                LtLog.e("帮会boos,count:" + count);
                count++;
                Thread.sleep(3500);
                if (count > 15) {
                    LtLog.e("count超时返回");
                    bj = 0;
                }
            }
        }
    }//家族boss




}




