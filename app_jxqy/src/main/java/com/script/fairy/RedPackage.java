package com.script.fairy;

import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairy2;
import com.script.framework.AtFairyImpl;

/**
 * Created by Administrator on 2019-09-27.
 */

public class RedPackage {

//    private GamePublicFunction gamePublicFunction;
    private PublicFunction publicFunction;
    private AtFairyImpl mFairy;

    public RedPackage(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
//        publicFunction=mFairy.publicFunction;
//        gamePublicFunction = new GamePublicFunction(mFairy);
    }

    public void rob() throws Exception {
        AtFairy2.OpencvResult result;
        boolean redPack_off_on = true;
        if (!android.os.Build.VERSION.RELEASE.equals("5.1.1")){
            LtLog.i(publicFunction.getLineInfo() + "~~~~~~~~~~~~~~~~~~~>redPackage close  " + android.os.Build.VERSION.RELEASE);
            return;
        }else {
            LtLog.i(publicFunction.getLineInfo() + "~~~~~~~~~~~~~~~~~~~>redPackage start  " + android.os.Build.VERSION.RELEASE);
        }
        if (TaskMain.mTask.equals("redPackageAndDssist") && TaskMain.taskMap.get("redPack") == 0) {
            //关闭抢红包
            LtLog.i(publicFunction.getLineInfo() + "-----*************-------->close redPack=");
            redPack_off_on = false;
        }
        while (mFairy.condit()){
            if (TaskMain.mTask.equals("dance")){
                LtLog.i(publicFunction.getLineInfo() + "-----~~~~~~~~~~~~~~~~~~~>task is dance , sleep=");
                while (mFairy.condit()){
                    if (!TaskMain.mTask.equals("dance")){
                        LtLog.i(publicFunction.getLineInfo() + "~~~~~~~~~~~~~~~~~~~>dance break=");
                        break;
                    }
                    Thread.sleep(20000);
                }
            }
            result = publicFunction.localFindPicHLS(714, 355, 835, 480, "redPackage.png");
            if (result.sim >= 0.8 && redPack_off_on) {
                LtLog.i(publicFunction.getLineInfo() + "~~~~~~~~~~~~~~~~~~~>redPackage=" + result);
                publicFunction.rndTapWH(result.x, result.y, 21, 25);
                Thread.sleep(500);
            }
            result = publicFunction.localFindPic(523, 141, 789, 338, "redPackage1.png"+"|"+"redPackage2.png");
            if (result.sim >= 0.8 && redPack_off_on) {
                LtLog.i(publicFunction.getLineInfo() + "~~~~~~~~~~~~~~~~~~~>redPackage1=" + result);
                publicFunction.rndTap(369, 44, 385, 51);
                Thread.sleep(2000);
            }
            Thread.sleep(100);
        }
    }






}
