package com.script.fairy.content;

/**
 * Created by daiepngfei on 2020-08-11
 */
public class ScProxy {

    public static final String TAG = "ScProxy";
    private CaptureEngine mCaptureEngine = new CaptureEngine(this);
    private MatchEngine mMatchEngine = new MatchEngine(this);
    private Assets mAssets = new Assets();
    private ScProxy(){
        WatchDog.ins().start();
        mCaptureEngine.start();
    }

    private static final class F {
        private static final ScProxy sIns = new ScProxy();
    }

    public static Assets assets() {
        return F.sIns.mAssets;
    }

    public static CaptureEngine captureEngine(){
        return F.sIns.mCaptureEngine;
    }

    public static MatchEngine matchEngine() {
        return F.sIns.mMatchEngine;
    }


}
