package com.script.fairy;

import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairy2;
import com.script.framework.AtFairyImpl;

/**
 * Created by Administrator on 2019-09-10.
 */


public class TimingActivity {
    private AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    private GamePublicFunction gamePublicFunction;

    public TimingActivity(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(ypFairy);
        gamePublicFunction = new GamePublicFunction(ypFairy);
        LtLog.i(publicFunction.getLineInfo() + "------mFairy->" + mFairy);
    }

    public void anOffical() throws Exception {
        AtFairy2.OpencvResult result;
        long currentTime = publicFunction.getMinuteNumber();
        int index = 0;
        while (mFairy.condit()) {

            currentTime = publicFunction.getMinuteNumber();
            LtLog.i(publicFunction.getLineInfo() + "------currentTime->" + currentTime);
            result = publicFunction.localFindPic(1071, 5, 1272, 45, "CityCombat.png");
            LtLog.i(publicFunction.getLineInfo() + "------CityCombat->" + result);
            if (result.sim < 0.8) {
                //如果不在副本地图，调用回到王城
                gamePublicFunction.goMianCity();
            }
            result = publicFunction.localFindPic(430, 272, 855, 399, "anOfficalCombat.png");
            LtLog.i(publicFunction.getLineInfo() + "------anOfficalCombat->" + result);
            if (result.sim >= 0.8) {
                publicFunction.rndTap(759, 463, 806, 483);//点击确认
                Thread.sleep(1000);
            }
//            result = publicFunction.localFindPic(598, 115, 935, 147, "enemy.png|enemy1.png");
//            LtLog.i(publicFunction.getLineInfo() + "------enemy->" + result);
//            if (result.sim >= 0.8 && index % 60==0) {
//                index=1;
//                publicFunction.rndTap(result.x, result.y - 73, result.x + 20, result.y - 50);//点地方npc头像
//                Thread.sleep(1000);
//            }
            result = publicFunction.localFindPic(598, 115, 935, 147, "enemy.png|enemy1.png");
            LtLog.i(publicFunction.getLineInfo() + "------enemy->" + result);
            if (index % 60 == 0 && result.sim >= 0.8) {
                index=0;
                switch (TaskMain.TCZ) {
                    case 1:
                        publicFunction.rndTap(630, 68, 661, 89);
                        break;
                    case 2:
                        publicFunction.rndTap(759, 66, 780, 90);
                        break;
                    case 3:
                        publicFunction.rndTap(892, 62, 916, 83);
                        break;
                    case 4:
                        result = publicFunction.localFindPic(552, 499, 725, 648, "nationalWar_token.png");
                        LtLog.i(publicFunction.getLineInfo() + "------nationalWar_token->" + result);
                        if (result.sim >= 0.8) {
                            publicFunction.rndTapWH(result.x, result.y, 10, 10);
                        }
                        break;
                }
                Thread.sleep(1000);
            }

            index = index + 1;
            result = publicFunction.localFindPic(1181, 133, 1280, 246, "leave.png");
            LtLog.i(publicFunction.getLineInfo() + "------leave->" + result);
            if (result.sim < 0.8 && currentTime >= 1172) {
                //如果不在副本中，并且时间已经超过19：:32  退出活动
                return;
            }
            result = publicFunction.localFindPic(851,314,969,422, "book6.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------book6->" + result);
                publicFunction.rndTap(470,613,530,636);//点击确认
                Thread.sleep(1000);
                return;
            }
            Thread.sleep(500);
        }
    }
}
