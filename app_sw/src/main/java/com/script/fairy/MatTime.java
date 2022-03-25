package com.script.fairy;

import com.script.opencvapi.LtLog;
import com.script.opencvapi.ScreenInfo;
import com.script.framework.AtFairyImpl;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2018/6/27.
 */

public class MatTime {

    private AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    private Mat mat1, mat2;
    private long timex, time;

    public MatTime(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
    }

    public long mMatTime(int x_1, int y_1, int width, int height,double sim) {
        boolean matSim = false;
        ScreenInfo screenInfo=mFairy.capture();
        if(screenInfo.height>720){
            LtLog.i(publicFunction.getLineInfo() + "----screenInfo error ---");
            return 0;
        }
        if(mat1!=null){
            mat1.release();
        }
        mat1 = mFairy.getScreenMat(x_1, y_1, width, height, 1, 0, 0, 1);
        if (mat2 != null && mat1 != null) {
//            LtLog.i(publicFunction.getLineInfo() + "----------------------------mat1.rows=>" + mat1.rows()  + ",mat2.rows="+ mat2.rows());
            try {
                matSim = judgeMatAndMatChange(sim, mat1, mat2);
            } catch (Exception e) {
//                LtLog.i(publicFunction.getLineInfo() + "----------------------------matSim>" + e.toString());
            }
            mat1.release();
            //判断两个矩阵的相似度大于 sim 则返回 true;
        }
        if (matSim) {
//            LtLog.i(publicFunction.getLineInfo() + "----------------------------matSim>" + matSim + ",timex=" + timex + ",time=" + time);
        } else {
            //如果两个矩阵不相等 重置时间
//            LtLog.i(publicFunction.getLineInfo() + "----------------------------matSim>" + matSim );
            time = System.currentTimeMillis() / 1000;
            if(mat2!=null){
                mat2.release();
            }
            mat2 = mFairy.getScreenMat(x_1, y_1, width, height, 1, 0, 0, 1);
        }
        timex = System.currentTimeMillis() / 1000 - time;
        return timex;
    }

    public void resetTime() {
        //重置时间
        time = System.currentTimeMillis() / 1000;
        timex=0;
    }

    public String do_exec(String cmd) {
        String s = "/n";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                s += line + "/n";
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("-------------------------" + cmd + "------" + s);
        return cmd;
    }

    private boolean judgeMatAndMatChange(double sim, Mat mat, Mat tempMat) throws Exception {
        //判断两个矩阵的相似度大于 sim 则返回 true;
        boolean state = false;
        Mat dstMat = new Mat(), dst1 = new Mat(), dst2 = new Mat();
        if (mat.channels() == 3 || tempMat.channels()==3) {
            Imgproc.cvtColor(mat, dst1, Imgproc.COLOR_RGB2HLS);
            Imgproc.cvtColor(tempMat, dst2, Imgproc.COLOR_RGB2HLS);
        }
        Imgproc.matchTemplate(dst1, dst2, dstMat, Imgproc.TM_CCOEFF_NORMED);
        Core.MinMaxLocResult mmr;
        mmr = Core.minMaxLoc(dstMat);
        if (mmr.maxVal >= sim) {
            state = true;
        }
        dstMat.release();
        dst1.release();
        dst2.release();
        return state;
    }

}
