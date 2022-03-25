package com.script.fairy;

import android.graphics.Bitmap;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2019-01-14.
 */

public class Contour {

    //参数 bitmap
    //点击位置
    //返回值 范围,


    public Rect getClickRect(int x, int y, Bitmap bitmap) {
        System.out.println("XYtoXYDistance=" + XYtoXYDistance(x, y, 153 + 9, 443 + 9));
        int ox1 = 0, oy1 = 0, ox2 = 0, oy2 = 0;
        ox1 = x - 100;
        oy1 = y - 100;
        ox2 = x + 100;
        oy2 = y + 100;
        if (ox1 < 0) {
            ox1 = 0;
        }
        if (oy1 < 0) {
            oy1 = 0;
        }
        if (ox2 > bitmap.getWidth()) {
            ox2 = bitmap.getWidth();
        }
        if (oy2 > bitmap.getHeight()) {
            oy2 = bitmap.getHeight();
        }
        Rect contourRect = new Rect(ox1, oy1, ox2 - ox1, oy2 - oy1);

        Mat mat = new Mat();
        Mat mat1 = new Mat();
        List<Mat> mv = new ArrayList<>();
        org.opencv.android.Utils.bitmapToMat(bitmap, mat);
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGRA2RGB);
        Imgcodecs.imwrite("/sdcard/8.png", mat);
        mat = new Mat(mat, contourRect);
        int lightness=lightness_mean_value(mat);
        Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        if(lightness>127){
            Imgproc.erode(mat, mat, dilateElement);
        }else {
            Imgproc.dilate(mat, mat, dilateElement);
        }


//        Imgcodecs.imwrite("/sdcard/8.png", mat);
//        Scalar minValues = new Scalar(127, 127, 127);
//        Scalar maxValues = new Scalar(255, 255, 255);
//        Core.inRange(mat, minValues, maxValues, mat);

        Core.split(mat, mv);
        mat = mv.get(2);

        Imgproc.adaptiveThreshold(mat, mat, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 101, 0);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(mat, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
//        public static void findContours(Mat image, List<MatOfPoint> contours, Mat hierarchy, int mode, int method)
        org.opencv.android.Utils.bitmapToMat(bitmap, mat1);
        int minDistance = 9999;
        int currentDis;
        Rect rect1 = null;
        Rect rect = null;
        int index = 0;
        Imgproc.cvtColor(mat1, mat1, Imgproc.COLOR_BGRA2RGB);
//        System.out.println("------------contours.size()=" + contours.size() + ",x=" + x + ",y=" + y);
//        System.out.println("------------hierarchy=" + hierarchy);
        for (int i = 0; i < contours.size(); i++) {
            rect = Imgproc.boundingRect(contours.get(i));
            rect = new Rect(rect.x + ox1, rect.y + oy1, rect.width, rect.height);

//            System.out.println("------------" + i + "-----" + rect + ",,,," + hierarchy.get(0, i)[0] + "," + hierarchy.get(0, i)[1] + "," + hierarchy.get(0, i)[2] + "," + hierarchy.get(0, i)[3]);
//            Imgproc.rectangle(mat1, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 0));

            currentDis = XYtoXYDistance(x, y, rect.x + (int) (rect.width / 2), rect.y + (int) (rect.height / 2));
//            currentDis = XYtoXYDistance(x, y, rect.x, rect.y);
//            System.out.println("------------currentDis=" + currentDis + ",minDistance=" + minDistance + "------------x2=" + (rect.x + (rect.width / 2)) + ",y2=" + (rect.y + (rect.height / 2)));
            if (currentDis < minDistance && rect.height * rect.width > 100) {
                rect1 = rect;
//                System.out.println("------------minDistance" + minDistance);
                minDistance = currentDis;
                index = i;
            }
        }

//        System.out.println("===================rect1=" + rect1);

        /*
        int maxCurrent=0;
        int index1=3;
        while (mFairy.condit()){
            maxCurrent=index;
            index= (int)hierarchy.get(0,index)[index1];
            System.out.println("===================index=" + index);
            if(index==-1){

                break;
            }
        }
        System.out.println("===================maxCurrent=" + maxCurrent);
        rect1 = Imgproc.boundingRect(contours.get(maxCurrent));
*/

        Imgproc.rectangle(mat1, new Point(rect1.x, rect1.y), new Point(rect1.x + rect1.width, rect1.y + rect1.height), new Scalar(0, 0, 255));


//        Imgproc.drawContours(mat1,contours,-1,new Scalar(0, 0, 255),2);
//        System.out.println("------------" + mat);
        Imgcodecs.imwrite("/sdcard/10.png", mat1);

        return rect1;
    }


    //计算两点间的距离
    public int XYtoXYDistance(int x_1, int y_1, int x_2, int y_2) {
        double distance = Math.sqrt(((x_1 - x_2) * (x_1 - x_2)) + ((y_1 - y_2) * (y_1 - y_2)));
        return (int) distance;
    }
    //计算平均亮度
    public int lightness_mean_value(Mat mat){
//        System.out.println("------------" + mat.size());
        Mat mat1=new Mat();
        Imgproc.cvtColor(mat,mat1,Imgproc.COLOR_RGB2HLS);
        List<Mat> mv = new ArrayList<>();
        Core.split(mat1,mv);

//        System.out.println("------------" +Core.mean(mv.get(0)) + "," + Core.mean(mv.get(1)) + Core.mean(mv.get(2)));
        String lightness_mean=(Core.mean(mv.get(1)).toString())
                .replace("[","")
                .replace("]","")
                .split(",")[0];

        System.out.println("------------" + Double.valueOf(lightness_mean).intValue());
        int lightness= Double.valueOf(lightness_mean).intValue();
        mat1.release();
        return lightness;
    }


}
