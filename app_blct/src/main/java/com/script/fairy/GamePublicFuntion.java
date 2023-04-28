package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.List;

public class GamePublicFuntion {

    public AtFairyImpl mFairy;
    public FindResult result;
    public long time = System.currentTimeMillis();

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
    }

    public void close() throws Exception {

        result = mFairy.findPic(651, 7, 1271, 445, new String[]{"close.png", "close1.png", "close2.png", "close3.png", "close4.png"});
        mFairy.onTap(0.8f, result, "关闭", 500);
    }

    public void reset() throws Exception {

        for (int i = 0; i < 5; i++) {

            narrow();

            Thread.sleep(100);

            mFairy.ranSwipe(316,201, 800, 550, 100, 1000);

            FindResult rejs = mFairy.findPic(214, 603, 389, 714, "jiasu.png");
            if(rejs.sim>0.7f){
                LtLog.e(mFairy.getLineInfo("分辨率正确"));
                break;
            }

        }
    }//重置

    void narrow() throws Exception {
        LtLog.e(mFairy.getLineInfo("调整屏幕分辨率"));

        Thread.sleep(100);
        mFairy.onTap(1215, 236, 1225, 238, "", 1000);
        mFairy.touchUp(1);
        mFairy.touchUp(2);
        Thread.sleep(100);
        mFairy.touchDown( 1,1028, 369);
        mFairy.touchDown(2, 142, 254);
        mFairy.touchMove(2, 1028, 369, 100, 100);
        mFairy.touchUp(1);
        mFairy.touchUp(2);

}//调整画面

    public boolean ysj_home() throws Exception {
        result = mFairy.findPic(11, 526, 162, 684, new String[]{"ysj.png", "ysj0.png"});
        if (result.sim > 0.8f) {
            return true;
        }
        return false;
    }

    public void init(boolean ye) throws Exception {

        while (mFairy.condit()) {

            LtLog.e(mFairy.getLineInfo("init >>>"));

            result = mFairy.findPic("chonglian1.png");
            if (result.sim > 0.85f) {
                LtLog.e(mFairy.getLineInfo("在线时间已延长"));
                LtLog.e(mFairy.getLineInfo("在线时间已延长"));
                LtLog.e(mFairy.getLineInfo("在线时间已延长"));
                Thread.sleep(200000);
                result = mFairy.findPic("chonglian1.png");
                mFairy.onTap(0.85f, result, 320, 450, 385, 471, "重新载入", 1000);
            }

            result = mFairy.findPic("fangqi.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "放弃", 1000);
                mFairy.onTap(743, 456, 791, 487, "", 500);
            }

            result = mFairy.findPic(new String[]{"jieshu.png", "jieshu1.png", "ysj4.png"});
            mFairy.onTap(0.8f, result, "结束战斗", 1000);

            result = mFairy.findPic(new String[]{"jieshu2.png", "jieshu3.png", "end1.png", "end2.png"});
            mFairy.onTap(0.8f, result, 758, 459, 798, 472, "确定结束战斗", 1000);

            result = mFairy.findPic("zeng6.png");
            mFairy.onTap(0.8f, result, "关闭聊天框", 500);

            result = mFairy.findPic("zeng6.png");
            mFairy.onTap(0.8f, result, "关闭聊天框", 500);

            result = mFairy.findPic(10, 541, 784, 708, new String[]{"hui.png", "hui1.png"});
            mFairy.onTap(0.75f, result, "回营", 500);

            close();

            if (ysj_home()) {

                if (ye) {
                    return;
                }

                narrow();
                Thread.sleep(100);
                mFairy.ranSwipe(748, 200, 279, 460, 200, 1000);
                //mFairy.ranSwipe(748, 200, 279, 460, 200, 10);
                Thread.sleep(100);

                result = mFairy.findPic(591, 81, 1122, 343, new String[]{"chuan1.png", "chuan3.png"});
                mFairy.onTap(0.7f, result, "船", 500);
            }

            if (home()) {
                return;
            }
        }


    }//init

    public boolean judge_zy() throws Exception {
        long c = mFairy.getColorNum(85, 474, 151, 499, "240,134,134", 0.92f);
        if (c > 100) {
            return true;
        }
        return false;
    }


    public boolean judge_js() throws Exception {
        long c = mFairy.getColorNum(160,386,183,406, "99,99,99", 0.95f);
        if (c > 30) {
            LtLog.e(mFairy.getLineInfo("出现灰色："+c));
            return true;
        }
        return false;
    }

    public boolean home() throws Exception {

        result = mFairy.findPic(new String[]{"home.png", "home1.png"});
        //LtLog.e("home :"+result.sim);
        if (result.sim > 0.8f) {
            return true;
        }
        return false;
    }

    public int get_number(float sim, int x, int y, int x1, int y1, int step, int min, int max, String img, String br) throws Exception {
        String str = "";
        while (true) {

            if (min < (x1 - x)) {

                for (int i = 9; i >= 0; i--) {

                    if (br != null && !br.equals("")) {
                        result = mFairy.findPic2(x, y, x1, y1, br);
                        if (result.sim > 0.8f) {
                            max = 0;
                            break;
                        }
                    }

                    result = mFairy.findPic(x, y, x1, y1, img + "" + i + ".png");

                   // if(img.equals("m")) {
                        //LtLog.e(mFairy.getLineInfo("范围：" + x + "," + y + "," + x1 + "," + y1 + " " + "sim:" + result.sim));
                    //}

                    if (result.sim > sim) {
                        if (step >= 0) {
                            x = result.x + result.width;
                            x1 = result.x + (result.width * 2);
                        } else {
                            x = result.x - (result.width * 2);
                            x1 = result.x;
                        }

                        str = str + i;
                        //if(img.equals("m")) {
                            LtLog.e(mFairy.getLineInfo("范围：" + x + "," + y + "," + x1 + "," + y1 + " " + "sim:" + result.sim));
                            LtLog.e(mFairy.getLineInfo("判断成功:" + str));
                        //}
                        break;
                    }
                }
            }

            if ((x1 - x) > max) {
                LtLog.e(mFairy.getLineInfo("结束 范围："+x+","+y+","+x1+","+y1));
                LtLog.e(mFairy.getLineInfo("这次循环完成了"));
                if (str.equals("")) {
                    return 0;
                } else {
                    if (step >= 0) {
                        return Integer.parseInt(str);
                    } else {
                        StringBuffer sb = new StringBuffer(str);
                        String afterReverse = sb.reverse().toString();
                        return Integer.parseInt(afterReverse);
                    }
                }
            }

            if (step >= 0) {
                x1 = x1 + step;
            } else {
                x = x + step;
            }

        }

    }//资源判断

    public int getColorNum(Mat mat, int leftX, int leftY, int rightX, int rightY, boolean bool) {

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

        boolean ma;
        int number = 0;
        if (bool) {
            for (int i = 0; i < l.size(); i++) {
                ma = false;
                for (int j = (i + 1); j < l.size(); j++) {
                    if (Math.abs(l.get(i)[0] - l.get(j)[0]) <= 1 && Math.abs(l.get(i)[1] - l.get(j)[1]) <= 1) {
                        ma = true;
                        break;
                    }
                }

                if (ma == false) {
                    number++;
                    if (number > 2) {
                        LtLog.e("采集器颜色判断异常");
                        return 0;
                    }
                }
            }
            number = 0;
            for (int i = 0; i < l.size(); i++) {

                for (int j = (i + 1); j < l.size(); j++) {
                    if (l.get(i)[1] == l.get(j)[1]) {
                        number++;
                    }
                }
                if (number >= 25) {
                    return 0;
                } else {
                    number = 0;
                }
            }
        }

        return l.size();
    }


}
