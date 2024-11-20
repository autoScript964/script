package com.script.fairy.content;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by daiepngfei on 2020-08-12
 */
public class MatFactory {

    /**
     * 
     * @param bgr8UC3Src
     * @param opt
     * @return
     * @throws Exception
     */
    public static Mat createCvtMat(Mat bgr8UC3Src, MatOpt opt) throws Exception {
        Mat mat = new Mat();
        switch (opt.method) {
            case BIN:
                Imgproc.cvtColor(bgr8UC3Src, mat, Imgproc.COLOR_BGR2GRAY);
                Imgproc.threshold(mat, mat, opt.threshold, opt.binMax, opt.binType);
                break;
            case GRAYS:
                Imgproc.cvtColor(bgr8UC3Src, mat, Imgproc.COLOR_BGR2GRAY);
            case HLS:
                Imgproc.cvtColor(bgr8UC3Src, mat, Imgproc.COLOR_BGR2HLS);
                break;
            case HSV:
                Imgproc.cvtColor(bgr8UC3Src, mat, Imgproc.COLOR_BGR2HSV);
                break;
            default:
                mat  = bgr8UC3Src;
        }
        return mat;
    }
}
