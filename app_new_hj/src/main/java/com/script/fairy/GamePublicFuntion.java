package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.framework.AtFairyImpl;

public class GamePublicFuntion {
    public AtFairyImpl mFairy;
    public FindResult result;
    public long time = System.currentTimeMillis();

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
    }

    public void close() throws Exception {
        result = mFairy.findPic(387, 5, 713, 999, new String[]{"close1.png", "close2.png", "close3.png"});
        mFairy.onTap(0.85f, result, "关闭", 500);

        result = mFairy.findPic(4,10,143,218, new String[]{"tui1.png","tui2.png"});
        mFairy.onTap(0.85f, result, "返回", 500);

}//关闭

    public void init() throws Exception {
        while (mFairy.condit()) {
            LtLog.e("init    >>>>>>>");

            close();

            click();

            if(qx()==false){
                result = mFairy.findPic(277,520,711,1272, "ok2.png");
                mFairy.onTap(0.85f, result, "ok2", 1000);
            }

            result = mFairy.findPic(519, 825, 706, 1032, "bangzhu.png");
            mFairy.onTap(0.85f, result, "联盟帮助", 1000);

            result = mFairy.findPic("chazhao.png");
            mFairy.onTap(0.85f,result,165,66,237,86,"关闭查找窗口",1000);

            result = mFairy.findPic(1, 1, 719, 1279, "nn2.png");
            if(result.sim>0.75f) {
                mFairy.onTap(0.75f, result, result.x - 50, result.y + 30, result.x - 30, result.y + 50, "指引", 1000);
                continue;
            }

            result = mFairy.findPic(271,26,629,159,new String[]{"guizu.png","guizu4.png","guizu6.png"});
            if (result.sim > 0.85f) {
                return;
            }

            result = mFairy.findPic(530, 995, 714, 1113, "nn20.png");
            mFairy.onTap(0.85f, result, "点击继续", 500);

            result = mFairy.findPic("nn1.png");
            mFairy.onTap(0.85f, result, "跳过", 500);

            result = mFairy.findPic(619, 1147, 706, 1274, new String[]{"nn3.png", "nn17.png"});
            mFairy.onTap(0.85f, result, "对话", 500);

            result = mFairy.findPic(177, 509, 320, 651, "zonglan1.png");
            mFairy.onTap(0.85f,result,"总览",1000);

            result = mFairy.findPic(272,34,391,404,"zhanlue.png");
            mFairy.onTap(0.85f,result,165,66,237,86,"战略",1000);

            result = mFairy.findPic("zhaomu5.png");
            mFairy.onTap(0.85f, result, "确定", 500);
        }
    }//init

    public boolean qx()throws Exception{
        result = mFairy.findPic(52,500,392,1166, new String[]{"qx.png","qx1.png","qx2.png"});
        if(result.sim>0.8f) {
            mFairy.onTap(0.8f, result, "取消", 1000);
            return true;
        }
        return false;
    }//取消

    public void click()throws Exception{
        result = mFairy.findPic(191, 804, 397, 1241, "dian.png");
        mFairy.onTap(0.85f, result, "点击空白处", 1000);
    }//点击空白处

}
