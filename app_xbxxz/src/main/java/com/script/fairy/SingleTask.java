

package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;

/**
 * Created by Administrator on 2018/3/13.
 */

public class SingleTask {
    public GamePublicFuntion gamePublicFuntion;
    public FindResult result;
    public AtFairyImpl mFairy;

    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        if (!AtFairyConfig.getOption("xi").equals("")) {
            xi = Integer.parseInt(AtFairyConfig.getOption("xi"));
        }
        if (!AtFairyConfig.getOption("shi").equals("")) {
            shi = Integer.parseInt(AtFairyConfig.getOption("shi"));
        }
    }
    private int xi = 0;
    private int shi = 0;

    public void wx() throws Exception {
        while (mFairy.condit()) {
            if (gamePublicFuntion.timeJudge(60000)) {
                for(int i=0;i<10;i++) {
                    switch (i) {
                        case 1:
                            mFairy.onTap(74, 1207, 105, 1235, "修炼", 2000);

                            mFairy.onTap(346, 1203, 382, 1235, "修炼", 1000);

                            break;
                        case 2:

                            mFairy.onTap(214, 1209, 239, 1237, "灵根", 2000);

                            if (AtFairyConfig.getOption("ling").equals("1")) {
                                mFairy.onTap(603, 490, 636, 510, "升级", 1000);
                                mFairy.onTap(431, 682, 462, 697, "确定", 1000);
                            }

                            switch (xi) {
                                case 1:
                                    mFairy.onTap(652, 199, 671, 216, "金", 1000);
                                    break;
                                case 2:
                                    mFairy.onTap(655, 249, 669, 267, "木", 1000);
                                    break;
                                case 3:
                                    mFairy.onTap(652, 298, 671, 313, "水", 1000);
                                    break;
                                case 4:
                                    mFairy.onTap(654, 347, 669, 363, "火", 1000);
                                    break;
                                case 5:
                                    mFairy.onTap(651, 394, 669, 416, "土", 1000);
                                    break;
                            }
                            mFairy.onTap(433, 675, 476, 706, "确定", 2000);
                            break;
                        case 3:
                            mFairy.onTap(490, 1203, 520, 1238, "洞府", 2000);

                            if (AtFairyConfig.getOption("buy").equals("1")) {
                                mFairy.onTap(501, 144, 531, 163, "召唤", 1000);
                                mFairy.onTap(427, 683, 486, 701, "确定", 2000);
                            }

                            switch (shi) {
                                case 1:
                                    mFairy.onTap(608, 251, 644, 271, "灵石", 1000);
                                    break;
                                case 2:
                                    mFairy.onTap(605, 429, 646, 443, "食物", 1000);
                                    break;
                                case 3:
                                    mFairy.onTap(608, 603, 644, 618, "木材", 1000);
                                    break;
                                case 4:
                                    mFairy.onTap(607, 777, 646, 794, "草药", 1000);
                                    break;
                                case 5:
                                    mFairy.onTap(603, 950, 649, 964, "石材", 1000);
                                    break;
                            }
                            mFairy.onTap(427, 683, 486, 701, "确定", 2000);
                            break;
                        case 4:
                            i = 0;
                            mFairy.onTap(622, 1209, 668, 1246, "仙缘", 2000);

                            if (AtFairyConfig.getOption("yuan").equals("1")) {
                                mFairy.onTap(63, 110, 157, 130, "元婴出窍", 2000);
                                mFairy.onTap(590, 861, 636, 875, "开始", 1500);
                                mFairy.onTap(429, 681, 474, 694, "确定", 1000);
                            }
                            break;
                    }
                }
            }


        }


    }//无限挂机

}

