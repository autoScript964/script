package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairy2;
import com.script.framework.AtFairyImpl;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/5/21.
 */

public class GamePublicFunction {

    AtFairyImpl mFairy;
    private PublicFunction publicFunction;
    FindResult findResult;
    //    private Abnormal abnormal;
    private AtFairy2.OpencvResult result;

    public void myFind() {

        //  Imgproc.cornerHarris(grayMat,tempDst,2,3,0.04);

    }

    public GamePublicFunction(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        publicFunction = new PublicFunction(mFairy);
    }


    public boolean activity(int type, String... str) throws Exception {

        int err = 0;

        while (mFairy.condit()) {

            result = publicFunction.localFindPic(582, 8, 721, 80, "activitiesWindow.png" + "|" + "activitiesWindow1.png" + "|" + "activitiesWindow2.png");
            if (result.sim > 0.8) {
                if (err == 0) {
                    switch (type) {
                        case 4:
                            mFairy.onTap(619, 88, 661, 103, "", 500);
                            break;
                    }
                }

                FindResult act = mFairy.findPic(151, 115, 790, 526, str);
                if (act.sim > 0.8f) {

                    findResult = mFairy.findPic(act.x, act.y, act.x + 273, act.y + 127, "attend.png");
                    if (findResult.sim >= 0.8f) {
                        mFairy.onTap(0.8f, findResult, "参加", 3000);
                        return true;
                    }

                    findResult = mFairy.findPic(act.x, act.y, act.x + 273, act.y + 127, new String[]{"complete1.png", "complete1-1.png"});
                    if (findResult.sim >= 0.8f) {
                        mFairy.onTap(1209, 86, 1226, 101, "", 1000);
                        return false;
                    }
                }

                err++;

                if (err == 3 || err == 5 || err == 7) {
                    LtLog.i(publicFunction.getLineInfo() + "滑动找任务");
                    publicFunction.RanSwipe(232, 187, 717, 445, 2, 500);
                }

                if (err > 9) {
                    return false;
                }

            } else {
                closeWindow();

                result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "------openActivity--activity>" + result);
                    publicFunction.rndTap(result.x, result.y, result.x + 10, result.y);
                    Thread.sleep(1500);
                    err = 0;
                }
            }


        }

        return false;
    }


    public List<String> lookupTask(List<String> list) throws Exception {
        //查看执行任务

        AtFairy2.OpencvResult result;
        PublicFunction publicFunction = new PublicFunction(mFairy);
        List<String> list1 = list;
        int second = 0;
        result = publicFunction.localFindPic(582, 8, 721, 80, "activitiesWindow.png" + "|" + "activitiesWindow1.png" + "|" + "activitiesWindow2.png");
        if (result.sim < 0.8) {
            return list1;
        }

        long time = System.currentTimeMillis() / 1000, timex = 0;


        while (mFairy.condit()) {
            int slidingLookup = 0;

            LtLog.i(publicFunction.getLineInfo() + "------------------------PublicFunction----lookupTask--list1.size()=>" + list1.size() + ",,current=" + list1.get(0) + ",timex=" + timex);


            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(0).equals("heroRealm1.png") || list1.get(0).equals("heroRealm2.png") || list1.get(0).equals("heroRealm3.png") || list1.get(0).equals("heroRealm4.png")) {
                    //英雄本 需要特别处理 遍历 4 个英雄本
                    for (int j = 1; j < 5; j++) {
                        LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.get(0)>" + "heroRealm" + Integer.toString(j) + ".png");
                        result = publicFunction.localFindPic(151, 115, 790, 526, "heroRealm" + Integer.toString(j) + ".png");
                        if (result.sim >= 0.85) {
                            LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.get(0)>" + list1.get(0) + "=" + result);
                            break;
                        }
                    }
                } else {
                    result = publicFunction.localFindPic(151, 115, 790, 526, list1.get(0));
                }


                LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.get(0)>" + list1.get(0) + "=" + result);
                if (result.sim >= 0.85) {
                    //找到任务
//                    LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.get(0)>" + list1.get(0) + "=" + result);
                    AtFairy2.OpencvResult result1;
                    result1 = publicFunction.localFindPic(result.x, result.y, result.x + 273, result.y + 127, "attend.png");
                    if (result1.sim >= 0.8) {
                        //点击参加
                        if (list1.get(0).equals("brigands.png")) {
                            //如果是马贼任务 需要特别处理
                            AtFairy2.OpencvResult result2;
                            result2 = publicFunction.localFindPic(result1.x - 139, result1.y - 25, result1.x, result1.y + 20, "unlimited.png");
                            if (result2.sim >= 0.8) {
                                LtLog.i(publicFunction.getLineInfo() + "------------------马贼完成----------lookupTask--unlimited>" + result2);
                                list1.remove(0);
                                continue;
                            }
                        }
                        LtLog.i(publicFunction.getLineInfo() + "------------------------PublicFunction----lookupTask--attend>" + result);

                        if (list1.get(0).equals("everydayTask.png")) {
                        }
                        publicFunction.rndTap(result1.x, result1.y, result1.x + 44, result1.y + 21);
                        Thread.sleep(500);
                        slidingLookup = 1;
                        Collections.swap(list1, 0, list1.indexOf(list1.get(0)));///把当前执行任务调整到第一位
                        return list1;
//                       break;
                    } else {
//                        LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.get(0)>" + result);
                        result1 = publicFunction.localFindPic(result.x, result.y, result.x + 273, result.y + 127, "complete1.png" + "|" + "complete1-1.png");
                        if (result1.sim >= 0.8) {
                            //已完成
                            LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--complete1>" + list1.get(0) + " 任务完成" + result);
                            if (list1.get(0).equals("heroRealm1.png") || list1.get(0).equals("heroRealm2.png") || list1.get(0).equals("heroRealm3.png") || list1.get(0).equals("heroRealm4.png")) {
                                //英雄副本 特别处理
                                list1.remove("heroRealm1.png");
                                list1.remove("heroRealm2.png");
                                list1.remove("heroRealm3.png");
                                list1.remove("heroRealm4.png");
                            } else {
                                LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction-----remove>");
                                list1.remove(0);
                            }
                            LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.size()>" + list1.size() + ",,list1=" + list1);
                            Thread.sleep(30);
                            if (list1.size() == 0) {
                                LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.size()为0");
                                return list1;
                            }
                            Thread.sleep(100);
                            continue;
                        }
                        result1 = publicFunction.localFindPic(result.x, result.y, result.x + 273, result.y + 127, "end.png");
                        if (result1.sim >= 0.8) {
                            LtLog.i(publicFunction.getLineInfo() + "---------end>" + result);
                            //任务结束
                            list1.remove(0);
                            if (list1.size() == 0) {
                                LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.size()为0");
                                return list1;
                            }
                        }
                    }
                }
            }
            if (list1.size() == 0) {
                break;
            }
            if (slidingLookup == 0) {
                if (second >= 5) {
                    LtLog.i(publicFunction.getLineInfo() + "---------从上往下滑动=" + result);
                    publicFunction.RanSwipe(232, 187, 717, 445, 0, 500);
                } else {
                    LtLog.i(publicFunction.getLineInfo() + "---------从下往上滑动=" + result);
                    publicFunction.RanSwipe(232, 187, 717, 445, 2, 500);
                }
                Thread.sleep(1000);
                second = second + 1;
                if (second >= 10) {
                    second = 0;
                }
                Thread.sleep(100);
            }


            result = publicFunction.localFindPic(582, 8, 721, 80, "activitiesWindow.png|activitiesWindow1.png|activitiesWindow2.png");
            if (result.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------------------不在活动窗口-------lookupTask--result=" + result);
                return list1;
            }

            timex = System.currentTimeMillis() / 1000 - time;
            LtLog.i(publicFunction.getLineInfo() + "---------timex=" + timex);
            if (timex >= 300) {
                LtLog.i(publicFunction.getLineInfo() + "---------长时间未找到任务");
                list1.remove(0);
                break;
            } else if (timex >= 120) {
                LtLog.i(publicFunction.getLineInfo() + "---------长时间未找到任务删除list1（0）" + list1.get(0));
                list1.remove(0);
                time = System.currentTimeMillis() / 1000;
                return list1;
            }
        }
        list1 = new ArrayList<String>();
        LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--长时间未找到任务");
        return list1;
    }

    public List<String> lookupTask1(List<String> list) throws Exception {
        //查看执行任务 不点参加
        AtFairy2.OpencvResult result;
        PublicFunction publicFunction = new PublicFunction(mFairy);
        List<String> list1 = list;
        int second = 0;
        result = publicFunction.localFindPic(582, 8, 721, 80, "activitiesWindow.png|activitiesWindow1.png|activitiesWindow2.png");
        if (result.sim < 0.8) {
            LtLog.e(publicFunction.getLineInfo()+"没有在活动场景");
            //如不在活动界面
            return list1;
        }
        LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--activitiesWindow>" + result + "list1.get(0)=" + list1.get(0));
        long time = System.currentTimeMillis() / 1000, timex = 0;

        while (mFairy.condit()) {
            int slidingLookup = 0;
            LtLog.i(publicFunction.getLineInfo() + "------------------------PublicFunction----lookupTask--list1.size()=>" + list1.size() + ",timex=" + timex);
            // for (int i = 0; i < list1.size(); i++) {
            for (int i = 0; i < list1.size(); i++) {
                Thread.sleep(10);
                if (list1.get(0).equals("heroRealm1.png")) {
                    //英雄本 需要特别处理 遍历 4 个英雄本
                    for (int j = 1; j < 5; j++) {

                        result = publicFunction.localFindPicHLS(151, 115, 790, 526, "heroRealm" + Integer.toString(j) + ".png");
                        LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.get(0)>" + "heroRealm" + Integer.toString(j) + ".png" + ",result=" + result);
                        if (result.sim >= 0.85) {
                            LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.get(0)>" + list1.get(0) + "=" + result);
                            break;
                        }
                    }
                } else {
                    result = publicFunction.localFindPic(151, 115, 790, 526, list1.get(0));
                }
                //LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.get(0)>" + list1.get(0) + "=" + result);
                if (result.sim >= 0.8) {
                    //找到任务
                    LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.get(0)>" + list1.get(0) + "=" + result);
                    AtFairy2.OpencvResult result1;
                    result1 = publicFunction.localFindPic(result.x, result.y, result.x + 273, result.y + 127, "attend.png");
                    if (result1.sim >= 0.8) {
                        //点击参加
                        if (list1.get(0).equals("brigands.png")) {
                            //如果是马贼任务 需要特别处理
                            AtFairy2.OpencvResult result2;
                            result2 = publicFunction.localFindPic(result1.x - 139, result1.y - 25, result1.x, result1.y + 20, "unlimited.png");
                            if (result2.sim >= 0.8) {
                                LtLog.i(publicFunction.getLineInfo() + "------------------马贼完成----------lookupTask--unlimited>" + result2);
                                list1.remove(0);
                                continue;
                            }
                        }
                        LtLog.i(publicFunction.getLineInfo() + "------------------------PublicFunction----lookupTask--attend>" + result);
//                       publicFunction.rndTap(result1.x, result1.y, result1.x + 44, result1.y + 21);
                        Thread.sleep(500);
                        slidingLookup = 1;
                        closeWindow();
                        Collections.swap(list1, 0, list1.indexOf(list1.get(0)));///把当前执行任务调整到第一位
                        return list1;
//                       break;
                    } else {
//                        LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.get(0)>" + result);
                        result1 = publicFunction.localFindPic(result.x, result.y, result.x + 273, result.y + 127, "complete1.png|complete1-1.png");
//                        LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.get(0)>" + result1);
//                        LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.get(0)>" + result);
//                        LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---+" + result.x + "," + result.y + "+--list1.get(0)>" + (result.x + 273) + "," + (result.y + 127));
                        if (result1.sim >= 0.8) {
                            //已完成
                            LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--complete1>" + list1.get(0) + " 任务完成" + result);
                            if (list1.get(0).equals("heroRealm1.png") || list1.get(0).equals("heroRealm2.png") || list1.get(0).equals("heroRealm3.png") || list1.get(0).equals("heroRealm4.png")) {
                                //英雄副本 特别处理
                                list1.remove("heroRealm1.png");
                                list1.remove("heroRealm2.png");
                                list1.remove("heroRealm3.png");
                                list1.remove("heroRealm4.png");
                            } else {
                                list1.remove(0);
                            }
                            LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.size()>" + list1.size() + ",,list1=" + list1);
                            Thread.sleep(300);
                            if (list1.size() == 0) {
                                LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--list1.size()为0");
                                return list1;
                            }
                            continue;
                        }
                    }
                }
            }
            if (list1.size() == 0) {
                break;
            }
            if (slidingLookup == 0) {
                if (second >= 4) {
                    publicFunction.RanSwipe(232, 187, 717, 445, 0, 500);
                } else {
                    publicFunction.RanSwipe(232, 187, 717, 445, 2, 500);
                }
                Thread.sleep(1000);
                second = second + 1;
                if (second >= 8) {
                    second = 0;
                }
            }
            result = publicFunction.localFindPic(582, 8, 721, 80, "activitiesWindow.png|activitiesWindow1.png|activitiesWindow2.png");
            AtFairy2.OpencvResult result1 = publicFunction.localFindPicHLS(1033, 548, 1161, 643, "push.png");
            if (result.sim < 0.8 || result1.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------------------不在活动窗口-------lookupTask--result=" + result);
                LtLog.i(publicFunction.getLineInfo() + "---------------------不在活动窗口-------lookupTask--result1=" + result1);
                return list1;
            }
            timex = System.currentTimeMillis() / 1000 - time;
            if (timex >= 300) {
                LtLog.i(publicFunction.getLineInfo() + "---------长时间未找到任务");
                list1.remove(0);
                break;
            } else if (timex >= 120) {
                LtLog.i(publicFunction.getLineInfo() + "---------长时间未找到任务删除list1（0）");
                list1.remove(0);
                time = System.currentTimeMillis() / 1000;
            }
        }
        list1 = new ArrayList<String>();
        LtLog.i(publicFunction.getLineInfo() + "-------------------------PublicFunction---lookupTask--长时间未找到任务");
        return list1;
    }

    public List<String> xylist(int x_1, int y_1, int x_2, int y_2, Mat image) throws InterruptedException {
        //获取两个穴位间白色点的数量
        List<String> list = new ArrayList<String>();
        List<String> list1 = new ArrayList<String>();
//        Imgcodecs.imwrite("/sdcard/" + x_1 + x_2 + y_1 + y_2 + ".png", image);
        double x1, y1, x2, y2;
        if (x_1 < x_2) {
            x1 = (double) x_1;
            x2 = (double) x_2;
            y1 = (double) y_1;
            y2 = (double) y_2;
        } else {
            x1 = (double) x_2;
            x2 = (double) x_1;
            y1 = (double) y_2;
            y2 = (double) y_1;
        }
        //  LtLog.i(publicFunction.getLineInfo() + "-------------------x1=" + x1 + ",y1=" + y1 + ",x2=" + x2 + ",y2=" + y2);
        double k = (y2 - y1) / (x2 - x1);
        double b = y1 - (x1 * ((y2 - y1) / (x2 - x1)));
        //LtLog.i(publicFunction.getLineInfo() + "-------------------k=" + k + ",b=" + b);
        int j;
        for (double i = x1; i < x2; i = i + 0.2) {
            j = (int) Math.round(k * i + b);
//            try {
//                LtLog.i(publicFunction.getLineInfo() + "------j=" + j + ",i=" + (int) i + "-------------image.get((int) i, j)[0]=" + image.get((int) i, j)[0]);
//            } catch (Exception e) {
//
//            }
            if (j == 0) {
                j = (int) y1;
            }

            if (image.get(j, (int) i)[0] == 255.0) {
//                LtLog.i(publicFunction.getLineInfo() + "-------------------image.get(j, (int) i)[0]=" + image.get(j, (int) i)[0]);
                list.add(i + "," + j);
                // list.add(distance + "");
            }

        }
        //   LtLog.i(publicFunction.getLineInfo() + "-------------------list=" + list.size());
        if (y_1 < y_2) {
            x1 = (double) x_1;
            x2 = (double) x_2;
            y1 = (double) y_1;
            y2 = (double) y_2;
        } else {
            x1 = (double) x_2;
            x2 = (double) x_1;
            y1 = (double) y_2;
            y2 = (double) y_1;
        }
        k = (y2 - y1) / (x2 - x1);
        b = y1 - (x1 * ((y2 - y1) / (x2 - x1)));
        //  LtLog.i(publicFunction.getLineInfo() + "-------------------k=" + k + ",b=" + b);
        for (double i = y1; i < y2; i = i + 0.2) {
            j = (int) Math.round((i - b) / k);
            if (j == 0) {
                j = (int) x1;
            }

//            try {
//                LtLog.i(publicFunction.getLineInfo() + "------i=" + (int) i + ",j=" + j + "-------------image.get((int) i, j)[0]=" + image.get((int) i, j)[0]);
//
//            } catch (Exception e) {
//
//            }
            if (image.get((int) i, j)[0] == 255.0) {
//                LtLog.i(publicFunction.getLineInfo() + "-------------------image.get((int) i, j)[0]=" + image.get((int) i, j)[0]);
                list1.add(i + "," + j);
                // list.add(distance + "");
            }
        }
        if (list.size() > list1.size()) {
            return list;
        } else {
            return list1;
        }
    }

    public List<String> Acupuncture() throws Exception {
        //点穴
        List<String> maxList = new ArrayList<String>();
        List<String> listStrXy = new ArrayList<String>();
        List<MatOfPoint> contours = new ArrayList<>();
        int maxListSize = 0;
        Mat img1;
        byte[] mybyte = mFairy.captureInterval().raw;
        for (int j = 0; j < 6; j++) {
            Mat dst, thresh, thresh1;
            Mat dst1 = new Mat();
            mybyte = mFairy.captureInterval().raw;
            img1 = new Mat(720, 1280, CvType.CV_8UC4);
            img1.put(0, 0, mybyte);
            Mat source;
//            source = Imgcodecs.imread("/sdcard/png/acupoint.png", Imgcodecs.IMREAD_GRAYSCALE);
            source = publicFunction.getAcupointMat("acupoint.png");
            Imgproc.cvtColor(source, source, Imgproc.COLOR_RGB2GRAY);
            Imgproc.threshold(source, source, 50, 255, 1);
            //Imgcodecs.imwrite("/sdcard/source.png", source);
            Imgproc.cvtColor(img1, img1, Imgproc.COLOR_BGR2RGB);
            // img1 = Imgcodecs.imread("/sdcard/1111.png");
            Rect roi = new Rect(377, 40, 500, 652);
            dst = new Mat(img1, roi);
            Imgproc.cvtColor(dst, dst, Imgproc.COLOR_RGB2GRAY);
//        Imgcodecs.imwrite("/sdcard/tovalue1.png", dst);
            Imgproc.threshold(dst, img1, 239, 255, 0);
            Imgproc.threshold(dst, dst, 50, 255, 1);
            // Imgcodecs.imwrite("/sdcard/tovalue2.png", img1);
            //  Imgcodecs.imwrite("/sdcard/tovalue3.png", dst);

            Core.addWeighted(dst, 0.5, img1, 0.5, 0, dst);
            Imgproc.threshold(dst, dst, 126, 255, 0);
            //628,108---565,271

            //xylist(628, 108, 565, 271);
            //xylist(565, 271, 628, 108);

            LtLog.i(publicFunction.getLineInfo() + "------------dst.get(253,61)=" + dst.get(62, 253)[0]);
            LtLog.i(publicFunction.getLineInfo() + "------------dst.get(253,61)=" + dst.get(63, 253)[0]);
            // Imgcodecs.imwrite("/sdcard/tovalue.png", dst);
            Mat hierarchy = new Mat();
            contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(dst, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
            //LtLog.i(publicFunction.getLineInfo() +  "---------------------------------contours=" + contours);
            //LtLog.i(publicFunction.getLineInfo() +  "---------------------------------contours.size()=" + contours.size());
            listStrXy = new ArrayList<String>();

            for (int i = 0; i < contours.size(); i++) {
                Rect rect = Imgproc.boundingRect(contours.get(i));
//            LtLog.i(publicFunction.getLineInfo() + "------------rect.height=" + rect.height + ",rect.width=" + rect.width);
                if (rect.height >= 25 && rect.width >= 25 && (rect.height * rect.width) <= 1300) {
                    LtLog.i(publicFunction.getLineInfo() + "------------dst.get(253,61)=" + String.valueOf(i));
//                    Imgcodecs.imwrite("/sdcard/nunber" + String.valueOf(i) + ".png", dst);
                    LtLog.i(publicFunction.getLineInfo() + "------------rect.x=" + rect.x + ",rect.y=" + rect.y + ",dst.w=" + dst.width() + ",dst.h=" + dst.height());
                    Rect roi1 = new Rect(rect.x, rect.y, rect.width + 20, rect.height + 20);
                    Mat dst2 = new Mat(dst, roi1);

                    Imgproc.matchTemplate(dst2, source, dst1, Imgproc.TM_CCOEFF_NORMED);
                    Core.MinMaxLocResult mmr = Core.minMaxLoc(dst1);
                    if (mmr.maxVal >= 0.65) {
                        LtLog.i(publicFunction.getLineInfo() + "------mmr.maxval=" + mmr.maxVal + "__mmr.maxLoc.x=" + (mmr.maxLoc.x + rect.x + 367 - 5 + 30) + "mmr.maxLoc.y=" + (mmr.maxLoc.y + 67 - 5 + rect.y));
                        listStrXy.add((int) (mmr.maxLoc.x + rect.x + 367 - 5 + 29) + "," + (int) (mmr.maxLoc.y + 67 - 5 + rect.y - 10));
                        //  listStrXy.add((int) (mmr.maxLoc.x + rect.x - 5 + 30) + "," + (int) (mmr.maxLoc.y - 5 + rect.y + 18));
                    }
                }
            }
            if (listStrXy.size() > maxListSize) {
                maxListSize = listStrXy.size();
                maxList = listStrXy;
            }
//            dst.release();

            Thread.sleep(1);
        }
        listStrXy = maxList;

        LtLog.i(publicFunction.getLineInfo() + "----------------------listStrXy.size=" + listStrXy.size());

        //        return listStrXy;
        Collections.reverse(listStrXy);

        LtLog.i(publicFunction.getLineInfo() + "------------Acupuncture Nunber=" + contours.size());
//        for (int i = 0; i < listStrXy.size() / 2; i++) {
        for (int i = 0; i < 3; i++) {
            LtLog.i(publicFunction.getLineInfo() + "----------------------listStrXy.get(i)=" + listStrXy.get(i));
            List<String> listStrXy1 = new ArrayList<String>(listStrXy);
            listStrXy1.remove(i);
            int number = 0;//计算穴位与穴位的连线数
            for (int j = 0; j < 3; j++) {
                LtLog.i(publicFunction.getLineInfo() + "----------------------listStrXy1.get(i)=" + listStrXy1.get(i));
                int x1 = Integer.parseInt(listStrXy.get(i).split(",")[0]);
                int y1 = Integer.parseInt(listStrXy.get(i).split(",")[1]);
                int x2 = Integer.parseInt(listStrXy1.get(j).split(",")[0]);
                int y2 = Integer.parseInt(listStrXy1.get(j).split(",")[1]);
                img1 = new Mat(720, 1280, CvType.CV_8UC4);
                img1.put(0, 0, mybyte);
                Imgproc.cvtColor(img1, img1, Imgproc.COLOR_RGB2GRAY);
                Imgproc.threshold(img1, img1, 240, 255, 0);
                List<String> listxyAggregate = xylist(x1, y1, x2, y2, img1);
                LtLog.i(publicFunction.getLineInfo() + "--------x1=" + x1 + ",y1=" + y1 + ",x2=" + x2 + ",y2=" + y2 + ",listxyAggregate.size()=" + listxyAggregate.size());
                if (listxyAggregate.size() > 10) {
                    number = number + 1;
                }
            }
            if (number == 1) {
                Collections.swap(listStrXy, 0, listStrXy.indexOf(listStrXy.get(i)));
                break;
            }
        }


        int xo1 = Integer.parseInt(listStrXy.get(0).split(",")[0]);
        int yo1 = Integer.parseInt(listStrXy.get(0).split(",")[1]);

        LtLog.i(publicFunction.getLineInfo() + "----------------xo1=" + xo1 + ",yo1=" + yo1);


        mFairy.touchDown(1, xo1, yo1);
        Thread.sleep(500);
        int ik = 0;
        for (int i = 0; i < listStrXy.size(); ) {
            List<String> listStrXy1 = new ArrayList<String>(listStrXy);
            if (listStrXy.size() == 1) {
                break;
            }
            listStrXy1.remove(i);
            LtLog.i(publicFunction.getLineInfo() + "----------------" + i + "------listStrXy1.size()=" + listStrXy1.size() + ",x=" + Integer.parseInt(listStrXy.get(i).split(",")[0]) + ",y=" + Integer.parseInt(listStrXy.get(i).split(",")[1]));
            //  double minDistance=9999.0;
            for (int j = 0; j < listStrXy1.size(); j++) {
                listStrXy.get(i).split(",");
                int x1 = Integer.parseInt(listStrXy.get(i).split(",")[0]);
                int y1 = Integer.parseInt(listStrXy.get(i).split(",")[1]);
                int x2 = Integer.parseInt(listStrXy1.get(j).split(",")[0]);
                int y2 = Integer.parseInt(listStrXy1.get(j).split(",")[1]);

                //mybyte = GetCaptureRaw().raw;
                img1 = new Mat(720, 1280, CvType.CV_8UC4);
                img1.put(0, 0, mybyte);
                Imgproc.cvtColor(img1, img1, Imgproc.COLOR_RGB2GRAY);
                Imgproc.threshold(img1, img1, 240, 255, 0);
                List<String> listxyAggregate = xylist(x1, y1, x2, y2, img1);
//                LtLog.i(publicFunction.getLineInfo() +  "-----------------------------x1=" + x1 + ",y1=" + y1 + ",x2=" + x2 + ",y2=" + y2 + ",listxyAggregate.size()=" + listxyAggregate.size());
                double distance = Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
                LtLog.i(publicFunction.getLineInfo() + "--------x1=" + x1 + ",y1=" + y1 + ",x2=" + x2 + ",y2=" + y2 + ",listxyAggregate.size()=" + listxyAggregate.size() + ",distance=" + distance);
                //  Thread.sleep(1000);
                List<String> listStrXy2 = new ArrayList<String>();
                if (listxyAggregate.size() > 1) {
//                    ik = ik + 1;
//                    LtLog.i("++++++++++++++++++++++++++++++++++++++++++++i=" + ik);
//                    if (ik == 3) {
//                        LtLog.i("++++++++++++++++++++++++++++++++++++++++++++i=" + "步长");
//                        mFairy.touchMove(1, x2, y2, 400, 4);
//                    } else {
//
//                    }
                    mFairy.touchMove(1, x2, y2, 400, 6);
                    Thread.sleep(500);
                    //touchDown(x2,y2);
                    listStrXy.remove(i);
                    Collections.swap(listStrXy, 0, listStrXy.indexOf(listStrXy1.get(j)));
                    i = 0;
                    // listStrXy2.add(x1 + "," + y1 + "," + x2 + "," + y2 +"," + distance);
                    break;
                }
            }
            result = publicFunction.localOptimalFindPic(495, 26, 568, 71, 200, 255, 0, "according.png");
            AtFairy2.OpencvResult result2 = publicFunction.localFindPic(452, 11, 681, 83, "acupoint1.png|help1.png");
            if (result.sim < 0.8 && result2.sim < 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "--------according=" + result + ",,help1" + result2);
                return listStrXy;
            }
        }
        mFairy.touchUp(1);
        return listStrXy;
    }

    public void openActivity(int x) throws Exception {
        //x=1 打开日常活动 x=2 打开限时活动

        LtLog.i(publicFunction.getLineInfo() + "开始打开活动");

        PublicFunction publicFunction = new PublicFunction(mFairy);
        AtFairy2.OpencvResult result;
        result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------openActivity--activity>" + result);
            return;
        }
        for (int i = 0; i < 15; i++) {
            result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------openActivity--activity>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y);
                Thread.sleep(300);

            }
            result = publicFunction.localFindPic(582, 8, 721, 80, "activitiesWindow.png|activitiesWindow1.png|activitiesWindow2.png");
            LtLog.i(publicFunction.getLineInfo() + "------openActivity--activityWindow>" + result);
            AtFairy2.OpencvResult result1 = publicFunction.localFindPicHLS(1033, 548, 1161, 643, "push.png");
            if (result.sim >= 0.8 || result1.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------openActivity--activityWindow>" + result);
                Thread.sleep(2000);
                rewardReceive();
                Thread.sleep(300);
                result = publicFunction.localFindPicHLS(161, 79, 286, 116, "dailyActivities.png" + "|" + "dailyActivities1.png");
                if (result.sim >= 0.85) {
                    LtLog.i(publicFunction.getLineInfo() + "-------dailyActivities>" + result);
                    if (x == 2) {
                        publicFunction.rndTap(323, 93, 407, 111);
                        Thread.sleep(300);

                    } else if (x == 4) {
                        publicFunction.rndTap(616, 88, 671, 103);
                        Thread.sleep(300);
                    } else if (x == 1) {
                        return;
                    }
                } else {
                    result = publicFunction.localFindPicHLS(289, 67, 439, 128, "timeLimitActivity.png" + "|" + "timeLimitActivity1.png");
                    LtLog.i(publicFunction.getLineInfo() + "-------timeLimitActivity>" + result);
                    if (result.sim >= 0.80 && x == 2) {
                        LtLog.i(publicFunction.getLineInfo() + "-------timeLimitActivity>" + result);
                        return;
                    }

                    result = publicFunction.localFindPicHLS(567, 61, 727, 137, "xiuxian.png");

                    if (result.sim >= 0.80 && x == 4) {
                        LtLog.i(publicFunction.getLineInfo() + "已经到休闲闲趣界面");
                        return;
                    }

                }
            }
            Thread.sleep(500);
        }
    }

    public void closeWindow() throws Exception {
        //关闭窗口
        for (int i = 0; i < 5; i++) {
            findResult = mFairy.findPic(1173, 46, 1249, 154,"close3.png");
            mFairy.onTap(0.8f,findResult,"关闭",2000);
            /*result = publicFunction.localOptimalFindPic(1173, 46, 1249, 154, 120.0, 255.0, 0, "fork1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------closeWindow----closeWindow--fork=" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 10, result.y + 10);
                Thread.sleep(2000);
                for (int j = 0; j < 10; j++) {
                    result = publicFunction.localOptimalFindPic(1173, 46, 1249, 154, 120.0, 255.0, 0, "fork1.png");
                    if (result.sim < 0.8) {
                        break;
                    }
                    Thread.sleep(1000);
                }
                break;
            }*/
        }
    }

    private void rewardReceive() throws InterruptedException {
        //领取活跃奖励
        PublicFunction publicFunction = new PublicFunction(mFairy);
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            list.add(0, 359 + (i * 153) + ",565");
            list1.add(publicFunction.getColor(list).get(0));
        }
        Thread.sleep(500);
        for (int i = 0; i <= 4; i++) {
            list.add(0, 359 + (i * 153) + ",565");
            if ((publicFunction.getColor(list).get(0)).equals(list1.get(i)) == false) {
                mFairy.tap(359 + (i * 153), 600);
            }
        }
    }

    public void sendTongTask(String mToken, String answer) throws InterruptedException {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd|HHmmss");
        String date = sDateFormat.format(new java.util.Date());
        LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------....." + date.split("\\|"));
        String[] dataTime = date.split("\\|");
        String keyStr = dataTime[0] + "_141_" + answer + "_" + dataTime[1] + ".png";
        LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------....." + keyStr);
        String filePath = "/sdcard/screen.png";
        publicFunction.httpPost(filePath, keyStr, mToken);
        publicFunction.fileDelete(filePath);
        return;
    }

    public void luckDraw() throws Exception {
        //转盘抽奖

        LtLog.i(publicFunction.getLineInfo() + "转盘抽奖");

        PublicFunction publicFunction = new PublicFunction(mFairy);
        AtFairy2.OpencvResult result, result1;
        result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
        if (result.sim > 0.95) {
            LtLog.i(publicFunction.getLineInfo() + "在主场景,没有在抽奖界面");
            return;
        }

        for (int i = 0; i < 30; i++) {

            result = publicFunction.localFindPic(595, 314, 647, 364, "spot.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "抽奖界面");

                publicFunction.rndTapWH(result.x, result.y, 47, 48);
                Thread.sleep(1000);
            }

            //601,514,706,558
            result1 = publicFunction.localFindManyPic(601, 514, 706, 558, new String[]{"luckdraw.png", "luckdraw1.png"});
            if (result1.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "抽奖|领奖");

                publicFunction.rndTapWH(result1.x, result1.y, 47, 20);

                Thread.sleep(1000);
            }

            if (result.sim < 0.8 || result1.sim < 0.8) {
                return;
            }

            result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
            if (result.sim > 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "在主场景,退出抽奖");
                return;
            }
            Thread.sleep(500);
        }
    }

    public void automaticCombat(int Combat_state) throws Exception {
        //自动战斗 0 取消 1开启

        LtLog.i(publicFunction.getLineInfo() + "【切换成：" + (Combat_state == 0 ? "手动" : "自动") + "】");

        PublicFunction publicFunction = new PublicFunction(mFairy);
        AtFairy2.OpencvResult result, result1;
        for (int i = 0; i < 5; i++) {
            result1 = publicFunction.localFindPicHLS(859, 564, 914, 615, "automaticCombat-1.png");
            result = publicFunction.localToValueFindPic(859, 564, 914, 615, 127, 255, 3, "automaticCombat.png" + "|" + "automaticCombat-2.png");
            LtLog.i(publicFunction.getLineInfo() + "-------------------------automaticCombat--->" + result + ",result1=" + result1);
            if (result.sim >= 0.6 || result1.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "-------------------------automaticCombat--->" + result + ",result1=" + result1);
                if (Combat_state == 0) {
                    return;
                } else {
                    publicFunction.rndTap(874, 578, 894, 594);
                    Thread.sleep(300);
                }
            }

            result = publicFunction.localFindPicHLS(859, 564, 914, 615, "automaticCombat1.png" + "|" + "automaticCombat1-1.png" + "|" + "automaticCombat1-2.png");
            LtLog.i(publicFunction.getLineInfo() + "-------------------------automaticCombat1--->" + result);
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "-------------------------automaticCombat1--->" + result);
                if (Combat_state == 1) {
                    return;
                } else {
                    publicFunction.rndTap(874, 578, 894, 594);
                    Thread.sleep(300);
                }
            }
            Thread.sleep(300);
        }
    }

    public void exitHome() throws Exception {
        closeWindow();
        findResult = mFairy.findPic("jiayuan.png");
        if (findResult.sim > 0.8f) {
            mFairy.onTap(0.8f, findResult, "离开家园", 1000);
            mFairy.onTap(759, 468, 784, 479, "", 1000);
        }
    }

    public int outDungeons() {
        //判断是否在副本中
        PublicFunction publicFunction = new PublicFunction(mFairy);
        AtFairy2.OpencvResult result = publicFunction.localFindManyPic(1018, 158, 1279, 362, new String[]{"outDungeons.png", "outDungeons1.png", "outDungeons2.png"});
        if (result.sim >= 0.8) {
            return 1;
        }
        return 0;
    }

    public void follow(int x) throws InterruptedException {
        //点击跟随 1 点击跟随 0取消跟随
        AtFairy2.OpencvResult result;

        LtLog.i(publicFunction.getLineInfo() + "【开始" + (x == 1 ? "跟随" : "取消跟随") + "】");

        for (int i = 0; i < 4; i++) {
//            result = publicFunction.localToValueFindPic(272, 239, 325, 287, 90, 255, 1, "follow.png");
            result = publicFunction.localToValueFindPic(272, 239, 325, 287, 90, 255, 1, "follow.png|follow-1.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "-------------------------follow--->" + result);
                if (x == 0) {
                    return;
                }
                mFairy.tap(300, 258);
                Thread.sleep(1000);
            }
//            result = publicFunction.localToValueFindPic(272, 239, 325, 287, 240, 255, 0, "follow1.png");
            result = publicFunction.localToValueFindPic(272, 239, 325, 287, 240, 255, 0, "follow1.png|follow1-1.png");
            if (result.sim >= 0.75) {
                LtLog.i(publicFunction.getLineInfo() + "------follow1->" + result);
                if (x == 1) {
                    return;
                }
                mFairy.tap(300, 258);
                Thread.sleep(1000);
            }
            Thread.sleep(300);
        }
    }

    public boolean judgeXYChange(int millisecond) {
        //OcrNumber ocrNumber = new OcrNumber(mFairy);
        //判断坐标 millisecond 毫秒内是否有变动  有变动返回 true 无变动 返回 false
        int xy1 = 0, xy2 = 0;
        long time = System.currentTimeMillis();
        long timex = System.currentTimeMillis() - time;
        xy2 = publicFunction.getXY(1118, 31, 1198, 48, 200, 255, 0);
//        xy2=ocrNumber.getNumber(1120,29,74,14,new Scalar(200,200,200),new Scalar(255,255,255));
        while (timex < millisecond) {
            timex = System.currentTimeMillis() - time;
            xy1 = publicFunction.getXY(1118, 31, 1198, 48, 200, 255, 0);
//            xy1=ocrNumber.getNumber(1120,29,74,14,new Scalar(200,200,200),new Scalar(255,255,255));
            if (xy2 != xy1) {

                return true;
            }
        }
        return false;

    }

    public void createTeam() throws Exception {
        //创建队伍
        AtFairy2.OpencvResult result;

        LtLog.i(publicFunction.getLineInfo() + "【开始创建队伍】");

        result = publicFunction.localFindPic(61, 2, 104, 61, "captain.png" + "|" + "captain-1.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "是队长");
            //如果是队长 无需创建
            return;
        }
        result = publicFunction.localFindPic(61, 2, 104, 61, "teamMember.png|teamMember-1.png");
        if (result.sim >= 0.8) {
            //是队员 退出队伍
            LtLog.i(publicFunction.getLineInfo() + "是队员,创建队伍");
            exitTeam();//退出队伍
        }
        result = publicFunction.localFindManyPic(24, 193, 81, 231, new String[]{"task1.png", "task2.png"});
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "切换到队伍栏");
            publicFunction.rndTap(143, 202, 182, 224);//点击组队
            Thread.sleep(2000);
        }
        result = publicFunction.localFindPic(805, 622, 997, 686, "createTeam.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "创建队伍");
            publicFunction.rndTapWH(result.x, result.y, 50, 10);
            Thread.sleep(500);
            return;
        }
        closeWindow();
    }

    public void exitTeam() throws Exception {
        //退出队伍
        AtFairy2.OpencvResult result, result1, result2;
        result = publicFunction.localFindPic(61, 2, 104, 61, "captain.png|captain-1.png");
        result1 = publicFunction.localFindPic(61, 2, 104, 61, "teamMember.png|teamMember-1.png");
        result2 = publicFunction.localFindPic(186, 620, 310, 660, "exitTeam.png");
        if (result.sim >= 0.8 || result1.sim >= 0.8 || result2.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------------captain=" + result + ",teamMember=" + result1);
        } else {
            LtLog.i(publicFunction.getLineInfo() + "------------captain=" + result + ",teamMember=" + result1);
            return;
        }
        for (int i = 0; i < 3; i++) {

            result = publicFunction.localFindManyPic(24, 193, 81, 231, new String[]{"task1.png", "task2.png"});
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------task1=" + result);
                publicFunction.rndTap(143, 202, 182, 224);//点击组队
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(186, 620, 310, 660, "exitTeam.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "------------exitTeam=" + result);
                publicFunction.rndTapWH(result.x, result.y, 50, 10);
                Thread.sleep(2000);
                publicFunction.rndTap(751, 462, 795, 484);//点确认
                Thread.sleep(500);
                return;
            }
        }
    }

    public void setTarget(String target) throws Exception {
        //设置队伍目标 aDragon 一条龙 , experience 江湖历练

        LtLog.i(publicFunction.getLineInfo() + "【设置队伍目标为：" + (target.equals("aDragon") ? "一条龙" : "江湖历练") + "】");

        AtFairy2.OpencvResult result, result1;
        result = publicFunction.localFindManyPic(24, 193, 81, 231, new String[]{"task1.png", "task2.png"});
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "切到队伍栏");
            publicFunction.rndTap(143, 202, 182, 224);//点击组队
            Thread.sleep(500);
        }
        result = publicFunction.localFindPic(802, 617, 926, 664, "propaganda.png");
        if (result.sim >= 0.8) {
            if (target.equals("aDragon")) {
                result = publicFunction.localFindPic(155, 130, 504, 179, "aDragon.png");
                if (result.sim >= 0.8) {
                    return;
                }
            } else if (target.equals("experience")) {
                result = publicFunction.localFindPic(155, 130, 504, 179, "experience.png");
                if (result.sim >= 0.8) {
                    return;
                }
            }

            //点击修改 设置目标
            LtLog.i(publicFunction.getLineInfo() + "点击修改目标");

            publicFunction.rndTap(508, 146, 525, 160);
            Thread.sleep(2000);

        }

        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(579, 277, 847, 367, "mercenary.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "佣兵助战");
                publicFunction.rndTap(484, 461, 532, 486);
                Thread.sleep(10000);
                break;
            }

            result = publicFunction.localFindPic(561, 92, 730, 151, "invitingFriends.png");
            if (result.sim >= 0.8) {
                publicFunction.rndTap(923, 112, 942, 135);
                Thread.sleep(1000);
            }

            result = publicFunction.localFindPic(387, 553, 550, 612, "allow.png");
            result1 = publicFunction.localFindPic(660, 108, 743, 159, "condition.png");
            if (result.sim >= 0.8 || result1.sim >= 0.8) {

                LtLog.i(publicFunction.getLineInfo() + "开始选择目标");

                if (target.equals("aDragon")) {
                    result = publicFunction.localFindPic(432, 195, 542, 366, "aDragon1.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "一条龙");
                        publicFunction.rndTapWH(result.x, result.y, 10, 10);
                        Thread.sleep(500);
                    } else {
                        mFairy.touchDown(489, 229);
                        mFairy.touchMove(489, 534, 500, 1000);
                        mFairy.touchUp();
                        Thread.sleep(3000);
                    }

                    result = publicFunction.localFindPic(456, 254, 539, 462, "levelAbove.png");
                    if (result.sim >= 0.8) {
                        publicFunction.rndTapWH(result.x, result.y, 10, 10);
                        Thread.sleep(300);
                        break;
                    }

                } else if (target.equals("experience")) {
                    result = publicFunction.localFindPic(424, 371, 541, 553, "experience1.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "江湖历练");
                        publicFunction.rndTapWH(result.x, result.y, 10, 10);
                        Thread.sleep(300);
                    } else {
                        mFairy.touchDown(489, 534);
                        mFairy.touchMove(489, 229, 500);
                        mFairy.touchUp();
                        Thread.sleep(1000);
                    }

                    result = publicFunction.localFindPic(456, 254, 539, 462, "field.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "野外");
                        //                1聚贤庄    2天龙寺    3燕子坞    4夜西湖    5擂鼓山    6缥缈峰   7-11燕王古墓  12-16秦皇地宫
                        if (TaskMain.map >= 1 && TaskMain.map <= 6) {
                            mFairy.tap(result.x, result.y);
                        } else if (TaskMain.map >= 7 && TaskMain.map <= 11) {
                            mFairy.tap(result.x, result.y + 58);
                        } else if (TaskMain.map >= 12 && TaskMain.map <= 16) {
                            mFairy.tap(result.x, result.y + 116);
                        }
                        Thread.sleep(1000);
                        break;
                    }
                }
            }
            Thread.sleep(500);
        }

        result = publicFunction.localFindPic(710, 556, 883, 610, "confirm.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "确定");
            publicFunction.rndTapWH(result.x, result.y, 50, 20);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(660, 108, 743, 159, "condition.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "关闭目标和条件窗口");
            publicFunction.rndTap(950, 125, 971, 147);//
            Thread.sleep(500);
        }
    }

    public void tirenAndAgree() throws Exception {
        //踢人 和 同意入队
        AtFairy2.OpencvResult result1;
        result = publicFunction.localFindPic(617, 13, 702, 71, "team.png");
        if (result.sim >= 0.8) {
            LtLog.i("--------------------------Abnormal--activitiesWindow>" + result);
            //不在组队界面 退出
        } else {
            return;
        }
        result1 = publicFunction.localToValueFindPic(366, 319, 1128, 392, 190, 255, 0, "offline.png");
        if (result1.sim >= 0.75) {
            LtLog.i(publicFunction.getLineInfo() + "-------offline.png>" + result);
            mFairy.tap(result1.x, result1.y);
            Thread.sleep(1000);
            result = publicFunction.localFindPic(result1.x - 77, result1.y - 150, result1.x + 112, result1.y + 150, "tiren.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------tiren.png>" + result);
                publicFunction.rndTapWH(result.x, result.y, 50, 20);
                Thread.sleep(1000);
                publicFunction.rndTap(746, 460, 797, 486);
                Thread.sleep(1000);
            }
        }
        result = publicFunction.localFindPic(453, 60, 487, 95, "redDot.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------redDot.png>" + result);
            mFairy.tap(401, 97);
            Thread.sleep(1000);
        }
        for (int i = 0; i < 4; i++) {
            result = publicFunction.localFindPic(157, 527, 1068, 592, "agree.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------agree.png>" + result);
                publicFunction.rndTap(result.x, result.y, result.x + 44, result.y + 21);
                Thread.sleep(1000);
            }
        }
        result = publicFunction.localFindPic(503, 299, 634, 374, "experience2.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------activity.png=" + result);
            publicFunction.rndTap(490, 462, 522, 482);
            Thread.sleep(1000);
        }
        result = publicFunction.localFindPic(617, 13, 702, 71, "team.png");
        if (result.sim >= 0.8) {
            LtLog.i("--------------------------Abnormal--activitiesWindow>" + result);
            publicFunction.rndTap(208, 94, 260, 112);
            Thread.sleep(500);
        }
    }

    public void openTeam() throws Exception {
        //打开组队窗口

        LtLog.i(publicFunction.getLineInfo() + "【开始打开队伍操作】");

        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindManyPic(24, 193, 81, 231, new String[]{"task1.png", "task2.png"});
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "切换回队伍");
                publicFunction.rndTap(143, 202, 182, 224);//点击组队
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(617, 13, 702, 71, "team.png");
            if (result.sim >= 0.8) {
                return;
            }
            result = publicFunction.localFindPic(593, 19, 665, 63, "team2.png");
            if (result.sim >= 0.8) {
                return;
            }

            Thread.sleep(500);
        }
    }

    public void openMap(String str) throws Exception {
        //打开世界地图 world
        //打开当前地图 current
        result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "---------activity.png=" + result);
            return;
        }
        for (int i = 0; i < 4; i++) {
            result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------activity.png=" + result);
                publicFunction.rndTap(1172, 53, 1248, 107);
                Thread.sleep(200);
            }
            if (str.equals("current")) {
                result = publicFunction.localFindPic(733, 572, 1190, 701, "goTo.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "---------goTo.png=" + result);
                    return;
                }

            } else if (str.equals("world")) {
                result = publicFunction.localFindPic(733, 572, 1190, 701, "goTo.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "---------goTo.png=" + result);
                    publicFunction.rndTapWH(result.x, result.y, 100, 30);
                    Thread.sleep(200);
                }
                result = publicFunction.localFindPic(62, 185, 110, 249, "map.png|map1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "---------map.png=" + result);
                    return;
                }
            }
            Thread.sleep(1000);
        }

    }

    public void goLuoYang() throws Exception {

        LtLog.i(publicFunction.getLineInfo() + "【前往安全区】");

        for (int i = 0; i < 10; i++) {
            result = publicFunction.localToValueFindManyPic(1119, 2, 1219, 27, 240, 255, 0, new String[]{"luoYang.png", "suZhou.png"});
            if (result.sim >= 0.8) {
                return;
            }

            openMap("world");
            result = publicFunction.localFindPic(62, 185, 110, 249, "map.png" + "|" + "map1.png");
            if (result.sim >= 0.8) {
                Thread.sleep(1000);
                LtLog.i(publicFunction.getLineInfo() + "---------map.png=" + result);
                publicFunction.rndTap(620, 293, 648, 312);
                for (int j = 0; j < 11; j++) {
                    result = publicFunction.localFindPic(62, 185, 110, 249, "map.png|map1.png");
                    if (result.sim >= 0.8) {
                        LtLog.i(publicFunction.getLineInfo() + "---------map.png=" + result);
                        closeWindow();
                        return;
                    }
                }
                closeWindow();
            }
            Thread.sleep(500);
        }


    }

    public void switchTaskOrTeam(String state) throws Exception {
        //team 组队栏状态 // task 任务栏
        for (int i = 0; i < 10; i++) {
//            result = publicFunction.localRGBTovalueFindPic(6, 183, 101, 241, 70, 255, 1, "R", "task1.png");
            result = publicFunction.localFindPicHLS(1, 196, 225, 229, "team1-1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------team1.png=" + result);
                if (state.equals("team")) {
                    return;
                } else {

                    publicFunction.rndTap(30, 203, 70, 218);
                    Thread.sleep(1000);
                    LtLog.i(publicFunction.getLineInfo() + "---------close=");
                    closeWindow();
                }
            }
//            result = publicFunction.localRGBTovalueFindPic(110, 192, 220, 232, 70, 255, 1, "R", "team1.png");

            result = publicFunction.localFindPicHLS(1, 196, 225, 229, "task1-1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "---------task1.png=" + result);
                if (state.equals("task")) {
                    return;
                } else {
                    publicFunction.rndTap(138, 201, 178, 220);
                    Thread.sleep(1000);
                    LtLog.i(publicFunction.getLineInfo() + "---------close=");
                    closeWindow();
                }
            }
            Thread.sleep(500);
        }

    }

    public void switchSkillOrSet(String state) throws Exception {
        //切换技能或者 设置、skill ,set
        AtFairy2.OpencvResult result;

        LtLog.i(publicFunction.getLineInfo() + "【开始切换右侧栏为：" + (state.equals("set") ? "设置" : "技能") + "】");

        for (int i = 0; i < 4; i++) {
            result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
            if (result.sim > 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【在主场景】");
                break;
            } else {
                closeWindow();
            }
            Thread.sleep(500);
        }

        for (int i = 0; i < 4; i++) {

            result = publicFunction.localFindPic(1171, 329, 1280, 455, "menu.png" + "|" + "menu-1.png");
            if (result.sim >= 0.8) {
                if (state.equals("set")) {
                    Thread.sleep(1000);
                    return;
                }
                publicFunction.rndTap(1229, 385, 1244, 400);
                Thread.sleep(1000);
            }


            result = publicFunction.localFindPic(1171, 330, 1280, 455, "menu1.png|menu1-1.png");
            if (result.sim >= 0.8) {
                if (state.equals("skill")) {
                    return;
                }
                publicFunction.rndTap(1229, 385, 1244, 400);
                Thread.sleep(1000);
            }
            Thread.sleep(500);
        }

        LtLog.i(publicFunction.getLineInfo() + "【切换右侧栏为：" + (state.equals("set") ? "设置" : "技能") + " - 结束】");


    }

    public int buyRope() throws Exception {
        //购买捕兽绳
        result = publicFunction.localFindPic(504, 275, 739, 387, "buyRope.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------------------------buyRope--->" + result + ",,,,buyRope=>" + TaskMain.optionJson.optString("buyRope").equals("1"));
            if (TaskMain.optionJson.optString("buyRope").equals("1")) {
                publicFunction.rndTap(750, 462, 791, 485);//点确认
                Thread.sleep(2000);
            } else {
                mFairy.touchDown(165, 617);
                mFairy.touchMove(301, 601, 0);
                mFairy.touchUp();
                Thread.sleep(1000); //移动一下，取消战斗状态。原因 ： 1 不能通过点击按钮来取消 2 如果出现捕兽绳不足，直接点击取消，还在战斗中，有可能再次触发抓宝宝，提示捕兽绳不足
                publicFunction.rndTap(482, 459, 540, 486);//点取消
                Thread.sleep(1000);

                return 99;
            }
        } else {
            return 0;
        }
        result = publicFunction.localFindPic(812, 136, 919, 240, "rope.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------------------------rope--->" + result);
            publicFunction.rndTap(1087, 182, 1153, 208);//点购买
            Thread.sleep(2000);
        }
        result = publicFunction.localFindPic(414, 412, 601, 533, "temporary.png");
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------------------------temporary--->" + result);
            publicFunction.rndTapWH(result.x, result.y, 87, 21);
            Thread.sleep(2000);
        }
        result = publicFunction.localFindPic(566, 177, 749, 259, "buy1.png");
        LtLog.i(publicFunction.getLineInfo() + "-------------------------buy1--->" + result);
        if (result.sim >= 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "-------------------------buy1--->" + result);
            publicFunction.rndTap(618, 463, 658, 483);//点确定
            Thread.sleep(1000);
        }

        closeWindow();
        return 0;

    }

    public void gameSet() throws Exception {
        for (int i = 0; i < 3; i++) {
            closeWindow();
        }
        for (int i = 0; i < 3; i++) {

            switchSkillOrSet("set");
        }
        for (int i = 0; i < 9; i++) {
            /**
             * 菜单切换
             */
            result = publicFunction.localFindPic(1171, 329, 1280, 455, "menu.png|menu-1.png");
            if (result.sim >= 0.8) {

                result = publicFunction.localFindPic(923, 558, 1268, 705, "set.png" + "|" + "set1.png");
                if (result.sim > 0.8f) {
                    LtLog.i(publicFunction.getLineInfo() + "【设置】");
                    publicFunction.rndTapWH(result.x, result.y, 5, 5);
                }
                Thread.sleep(2000);
            }

            result = publicFunction.localFindPic(608, 0, 767, 103, "system2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【设置界面】");
                publicFunction.rndTap(77, 318, 95, 353);
                Thread.sleep(1000);
                publicFunction.rndTap(403, 418, 439, 430);
                Thread.sleep(500);
                publicFunction.rndTap(1216, 87, 1227, 99);
                break;
            }
        }
    }

    public void switchRole() throws Exception {

        while (mFairy.condit()) {

            /*result = publicFunction.localFindPic(1171, 329, 1280, 455, "menu.png"+"|"+"menu-1.png");
            if (result.sim >= 0.8) {
                publicFunction.rndTap(961, 656, 988, 686);
                Thread.sleep(2000);
            }*/

            findResult = mFairy.findPic(924, 538, 1263, 711, "setup.png");
            mFairy.onTap(0.8f, findResult, "设置", 3000);

            result = publicFunction.localFindPic(608, 0, 767, 103, "system2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【设置界面】");

                findResult = mFairy.findPic("jichu.png");
                mFairy.onTap(0.8f, findResult, "基础", 1000);

                result = publicFunction.localFindPic(857, 294, 1033, 412, "switchRole.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【更换账号】");
                    publicFunction.rndTapWH(result.x, result.y, 76, 18);
                    Thread.sleep(2000);
                }

                result = publicFunction.localFindPic(466, 281, 656, 404, "switchRole1.png");
                if (result.sim >= 0.8) {
                    LtLog.i(publicFunction.getLineInfo() + "【确定更换】");
                    publicFunction.rndTap(748, 459, 792, 483); //点击确认
                    Thread.sleep(10000);

                    result = publicFunction.localFindPic(608, 0, 767, 103, "system2.png");
                    if (result.sim < 0.8) {
                        break;
                    }
                }


            } else {
                switchSkillOrSet("set");
            }


            //进入到选择角色界面即可退出循环，进入游戏过程中的点击操作，Abnormal 会执行，任务线程和 Abnormal线程是共存的
         /*   result = publicFunction.localFindPic(1024, 599, 1240, 720, "playGame.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "【已到达登录界面】");
                break;
            }*/
            Thread.sleep(1000);
        }


    }

    public void outMercenary() throws Exception {
        result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
        if (result.sim < 0.8) {
            LtLog.i(publicFunction.getLineInfo() + "------openActivity--activity>" + result);
            return;
        }
        openTeam();
        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(580, 0, 735, 102, "team.png|team2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------team>" + result);
                publicFunction.rndTap(188, 86, 273, 104);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(417, 192, 1168, 260, "mercenary1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------mercenary1>" + result);
                publicFunction.rndTapWH(result.x, result.y, 25, 22);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(347, 187, 1169, 276, "tiren.png");
            LtLog.i(publicFunction.getLineInfo() + "-------tiren>" + result);
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------tiren>" + result);
                publicFunction.rndTapWH(result.x, result.y, 39, 21);
                Thread.sleep(2000);
            }
            Thread.sleep(100);
        }
        closeWindow();
    }

    //放弃日常
    public void renounceEverday() throws Exception {
        AtFairy2.OpencvResult result;

        for (int i = 0; i < 10; i++) {
            result = publicFunction.localFindPic(0, 153, 126, 274, "task1.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------task1>" + "result=" + result);
                publicFunction.rndTapWH(result.x, result.y, 45, 21);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(210, 91, 332, 458, "everday.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------everday>" + "result=" + result);
                publicFunction.rndTapWH(result.x, result.y, 45, 21);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(540, 598, 744, 681, "renounce.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------renounce>" + "result=" + result);
                publicFunction.rndTapWH(result.x, result.y, 45, 21);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(702, 413, 843, 534, "determine.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------determine>" + "result=" + result);
                publicFunction.rndTapWH(result.x, result.y, 41, 21);
                Thread.sleep(500);
                closeWindow();
                break;
            }

        }
    }

    //回到帮会
    public void goTong() throws Exception {
        LtLog.i(publicFunction.getLineInfo() + "-------goTong>");
        for (int i = 0; i < 10; i++) {
            switchSkillOrSet("set");
            result = publicFunction.localFindPic(932, 179, 1267, 702, "tong2.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------tong2>" + result);
                publicFunction.rndTapWH(result.x, result.y, 29, 32);
                Thread.sleep(2000);
            }
            result = publicFunction.localFindPic(799, 592, 1164, 713, "goTong.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------goTong>" + result);
                publicFunction.rndTapWH(result.x, result.y, 90, 21);
                Thread.sleep(1000);
                closeWindow();
            }
            result = publicFunction.localFindPic(822, 24, 954, 72, "activity.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------activity>" + result);
                openMap("current");
                Thread.sleep(1500);
            }
            result = publicFunction.localFindPic(579, 11, 747, 73, "tong3.png");
            if (result.sim >= 0.8) {
                LtLog.i(publicFunction.getLineInfo() + "-------tong3>" + result);
                closeWindow();
                break;
            } else {
                closeWindow();
            }
            Thread.sleep(1000);
        }


    }

    static List<FindResult> listResult = new ArrayList<>();

    public void dianxue() throws Exception {

        List<Integer> list = new ArrayList<>();

        List<Integer> list1 = new ArrayList<>();

        List<Integer> list2 = new ArrayList<>();

        List<Integer> list3 = new ArrayList<>();

        boolean count = false;

        int num2 = 0;

        long time = System.currentTimeMillis();

        //findResult = mFairy.findPic(374,25,905,694, "acupoints.png");

      /*  LtLog.e(mFairy.getLineInfo(0.7f, findResult, "穴位"));

        if (result.sim > 0.7f) {*/

        Mat mat = mFairy.captureMat();

        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);

        listResult = mFairy.findPic(380, 37, 901, 687, 0.65f, "acupoints.png");

        LtLog.i(publicFunction.getLineInfo() + "acupoints_size :" + listResult.size());

        for (int i = 0; i < listResult.size(); i++) {
            LtLog.i(publicFunction.getLineInfo() + listResult.get(i).x+","+listResult.get(i).y+",50,50");
        }

        for (int i = 0; i < listResult.size(); i++) {

            list.add(listResult.get(i).x);

            list.add(listResult.get(i).y);
        }

        for (int k = 0; k < list.size(); k = k + 2) {

            int totalnowNum = 0;

            int lineNum = 0;

            for (int i = 0; i < list.size(); i = i + 2) {

                int nowNum = 0;

                double r = 0;

                for (int h = 11; h <= 14; h++) {

                    for (int f = 7; f <= 11; f++) {

                        r = Math.sqrt(((list.get(k) + f) - (list.get(i) + f)) * ((list.get(k) + f) - (list.get(i) + f)) + ((list.get(k + 1) + h) - (list.get(i + 1) + h)) *
                                ((list.get(k + 1) + h) - (list.get(i + 1) + h)));

                        for (int j = 0; j < (int) r; j++) {

                            double cx = (j * ((list.get(i) + f) - (list.get(k) + f))) / r + (list.get(k) + f);

                            double cy = (j * ((list.get(i + 1) + h) - (list.get(k + 1) + h))) / r + (list.get(k + 1) + h);

                            if (255.0 - mat.get((int) cy, (int) cx)[0] <= 10) {
                                nowNum++;
                            }
                        }
                    }
                }

                if (r != 0) {
                    nowNum = (int) ((double) nowNum / r * 10);
                }

                if (nowNum > 11) {

                    lineNum++;

                    totalnowNum = totalnowNum + nowNum;
                }
            }

            list1.add(lineNum);

            list2.add(totalnowNum);

        }

        int start = list1.indexOf(1);

        if (start == -1) {

            start = list1.indexOf(2);

        }


        LtLog.i(publicFunction.getLineInfo() + "list1:" + list1.toString());

        int max = Collections.max(list1);

        int end = list1.indexOf(max);

        list.add(list.get(end * 2));

        list.add(list.get(end * 2 + 1));

        int startx = list.get(start * 2);

        int starty = list.get(start * 2 + 1);

        list.remove(start * 2);

        list.remove(start * 2);

        LtLog.e("起点是" + start + "," + startx + "," + starty);

        list3.add(startx);

        list3.add(starty);

      /*      if (!count){

                Thread.sleep(5000);

                mFairy.condit();

                result = mFairy.findPic(69, 272, 673, 1018, "Acupoint.png");

                LtLog.e(mFairy.getLineInfo(0.65f, result, "穴位"));

                if (result.sim <0.65f) {

                    return;

                }

            }*/

        num2 = list.size();

        mFairy.touchDown(2, startx + 9, starty + 11);

        for (int i = 0; i < list.size(); i = i + 2) {

            int num = connect(startx, starty, list.get(i), list.get(i + 1), mat);


            if (num > 11) {

                mFairy.touchMove(2, list.get(i) + 9, list.get(i + 1) + 11, 500);

                LtLog.e("连接" + list.get(i) + "," + list.get(i + 1) + ",num=" + num);

                startx = list.get(i);

                starty = list.get(i + 1);

                list3.add(list.get(i));

                list3.add(list.get(i + 1));

                list.remove(i);

                list.remove(i);

                if (list.size() == 0) {

                    break;

                }

                i = -2;

            }

        }


        if (list.size() != 0) {

            for (int i = 0; i < list.size(); i = i + 2) {

                mFairy.touchMove(2, list.get(i) + 9, list.get(i + 1) + 11, 500);

                list.remove(i);

                list.remove(i);

                if (list.size() == 0) {

                    break;

                }

            }

        }

        mFairy.touchUp(2);

        mFairy.sleep(5000);

        findResult = mFairy.findPic(69, 272, 673, 1018, "Acupoint.png");

        if (findResult.sim > 0.8f) {

            LtLog.e("终点点是" + list3.get(0) + "," + list3.get(1));

            startx = list3.get(num2 - 2);

            starty = list3.get(num2 - 1);

            mFairy.touchDown(8, startx + 9, starty + 11);

            for (int i = list3.size() - 1; i > 0; i = i - 2) {

                // if (num2 > 11) {

                mFairy.touchMove(8, list3.get(i - 1) + 9, list3.get(i) + 11, 500);

                LtLog.e("连接" + list3.get(i - 1) + "," + list3.get(i) + ",num=" + num2);

                list3.remove(i);

                list3.remove(i - 1);

                if (list3.size() == 0) {

                    break;

                }


                i = -2;

                //   }

            }


            if (list3.size() != 0) {

                for (int i = list3.size() - 1; i > 0; i = i - 2) {

                    mFairy.touchMove(8, list3.get(i - 1) + 9, list3.get(i) + 11, 500);

                    list3.remove(i);

                    list3.remove(i - 1);

                    if (list3.size() == 0) {

                        break;

                    }

                }

            }

            mFairy.touchUp(8);

        }


        LtLog.e("time============" + (System.currentTimeMillis() - time));


    }

    public void dianxues()throws Exception{

        listResult = mFairy.findPic(380, 37, 901, 687, 0.72f, "acupoints.png");
        LtLog.i(publicFunction.getLineInfo() + "acupoints_size :" + listResult.size());

        /*for (int i = 0; i < listResult.size(); i++) {
            LtLog.i(publicFunction.getLineInfo() +"   "+listResult.get(i).x+","+listResult.get(i).y+",50,50");
        }*/

        int y = 0;
        for (int i = 0; i < listResult.size(); i++) {

            for (int j = i; j < listResult.size(); j++) {

                if(listResult.get(i).y > listResult.get(j).y){
                    FindResult r = listResult.get(i);
                    listResult.set(i,listResult.get(j));
                    listResult.set(j,r);
                    //LtLog.i(publicFunction.getLineInfo() +"   "+listResult);
                }
            }
        }

        mFairy.touchDown(listResult.get(0).x+12,listResult.get(0).y+6);

        LtLog.i(publicFunction.getLineInfo() +"   "+listResult.get(0).x+","+listResult.get(0).y+",20,20");

        for (int i = 1; i < listResult.size(); i++) {
            LtLog.i(publicFunction.getLineInfo() +"   "+listResult.get(i).x+","+listResult.get(i).y+",50,50");
            mFairy.touchMove(listResult.get(i).x+15,listResult.get(i).y+10,300);
        }
        mFairy.touchUp();
    }

    public int connect(int x, int y, int x1, int y1, Mat mat) throws Exception {
        int nowNum = 0;
        double r = 0;
        for (int h = 11; h <= 14; h++) {
            for (int f = 7; f <= 11; f++) {
                r = Math.sqrt(((x1 + f) - (x + f)) * ((x1 + f) - (x + f)) + ((y1 + h) - (y + h)) * ((y1 + h) - (y + h)));
                for (int j = 0; j < (int) r; j++) {
                    double cx = (j * ((x + f) - (x1 + f))) / r + (x1 + f);
                    double cy = (j * ((y + h) - (y1 + h))) / r + (y1 + h);
                    if (255.0 - mat.get((int) cy, (int) cx)[0] <= 10) {
                        nowNum++;
                    }
                }
            }
        }
        if (r != 0) {
            nowNum = (int) ((double) nowNum / r * 10);
        }
        return nowNum;
    }

    public Mat binaryzationMat(Mat mat, int[] minRange, int[] maxRange) {
        Scalar minValues = new Scalar(minRange[0], minRange[1], minRange[2]);
        Scalar maxValues = new Scalar(maxRange[0], maxRange[1], maxRange[2]);
        Core.inRange(mat, minValues, maxValues, mat);
        minValues = null;
        maxValues = null;
        return mat;
    }

    public int getColorNum(Mat mat, int leftX, int leftY, int rightX, int rightY) {

        if (leftX >= rightX) {
            leftX = 0;
            rightX = 1279;
        }

        if (rightY <= leftY) {
            leftY = 0;
            rightY = 719;
        }

        if (mat == null) {
            LtLog.e("mat为空");
            return 9999;
        }

        List<int[]> l = new ArrayList();

        for (int i = leftX; i < rightX; i++) {
            for (int j = leftY; j < rightY; j++) {
                int a = 0;
                double[] d = mat.get(j, i);
                if (d != null) {
                    a = (int) d[0];
                }
                if (a != 0) {
                    l.add(new int[]{i, j});
                }
            }
        }
        return l.size();
    }

}









