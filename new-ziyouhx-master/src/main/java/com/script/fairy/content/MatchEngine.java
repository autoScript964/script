package com.script.fairy.content;

import android.util.Log;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

/**
 * Created by daiepngfei on 2020-08-11
 */
public class MatchEngine {

    private static final String TAG = ScProxy.TAG + "#" + "MatchEngine";

    public MatchEngine(ScProxy scProxy) {

    }

    public MatchResult doMatching(Mat bgr8UC3Src, Mat tar, MatchOpt opt) {
        return MatchCore.doMatching(bgr8UC3Src, tar, opt);
    }

    static final class MatchCore {

        public static MatchResult doMatching(Mat bgr8UC3Src, Mat tarMat, MatchOpt opt) {
            if (opt == null) {
                opt = Opt();
            }
            final MatchResult r = new MatchResult(opt.sim);
            try {
                // create regioned-src-mat
                Mat regionSrc;
                if (opt.region != null && !opt.region.empty()) {
                    final int x = Math.max(0, opt.region.x - opt.regionExpension);
                    final int y = Math.max(0, opt.region.y - opt.regionExpension);
                    final int w = Math.min(x + opt.regionExpension + opt.region.width, bgr8UC3Src.width()) - x;
                    final int h = Math.min(y + opt.regionExpension + opt.region.height, bgr8UC3Src.height()) - y;
                    final Rect expandedRegion = new Rect(x, y, w, h);
                    Log.d(TAG, "regionRect: " + expandedRegion.toString() + "/srcMat: " + bgr8UC3Src.width() + "," + bgr8UC3Src.height());
                    regionSrc = new Mat(bgr8UC3Src, expandedRegion);
                } else {
                    regionSrc = new Mat();
                    bgr8UC3Src.copyTo(regionSrc);
                }

                // cvt-color regioned-src-mat with method
                Mat processedMat = MatFactory.createCvtMat(regionSrc, opt.matOpt);

                // match template with cvt-region-src-mat
                Mat resultMat = new Mat();
                Imgproc.matchTemplate(processedMat, tarMat, resultMat, opt.matchMethod);

                // find result
                Core.MinMaxLocResult mmr;
                mmr = Core.minMaxLoc(resultMat);
                r.sim = (float) mmr.maxVal;
                r.x = (int) mmr.maxLoc.x + (opt.region == null || opt.region.empty() ? 0 : opt.region.x);
                r.y = (int) mmr.maxLoc.y + (opt.region == null || opt.region.empty() ? 0 : opt.region.y);

                regionSrc.release();
                resultMat.release();
                processedMat.release();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

    }

    public static final class MatchResult {
        private final float defaultSim;
        private float sim = 0;
        private int x;
        private int y;

        MatchResult(float targetSim) {
            this.defaultSim = targetSim;
        }

        public boolean ok() {
            return sim >= defaultSim;
        }

        public float getSim() {
            return sim;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }


    public static MatchOpt Opt() {
        return new MatchOpt();
    }

    public static class MatchOpt {

        private float sim = 0.8f;
        private MatOpt matOpt;
        private int matchMethod = Imgproc.TM_CCOEFF_NORMED;
        private Rect region;
        private int regionExpension = 10;

        private MatchOpt() {
            matOpt = new MatOpt();
        }

        public MatchOpt region(int x, int y, int w, int h, int expension) {
            this.regionExpension = expension;
            this.region = new Rect(x, y, w, h);
            return this;
        }

        public MatchOpt region(int x, int y, int w, int height) {
            this.region(x, y, w, height, regionExpension);
            return this;
        }

        public MatchOpt method(MatOpt.Method m) {
            this.matOpt.method = m;
            return this;
        }

        public MatchOpt matchMethod(int matchMethod) {
            this.matchMethod = matchMethod;
            return this;
        }

        public MatchOpt sim(float sim) {
            this.sim = sim;
            return this;
        }

        public MatchOpt binArgsThreshold(int threshold) {
            this.matOpt.threshold = threshold;
            return this;
        }

        public MatchOpt binArgsType(int type) {
            this.matOpt.binType = type;
            return this;
        }

        public MatchOpt binArgs(int threshold, int type, int max) {
            this.matOpt.threshold = threshold;
            this.matOpt.binType = type;
            this.matOpt.binMax = max;
            return this;
        }
    }

}
