package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import java.util.List;

public class SingleTask {
    public GamePublicFuntion gamePublicFuntion;
    public AtFairyImpl mFairy;
    private int hour;
    private int minute;
    private int week;
    public String activity_name;
    public int activity_type;
    public boolean judgeStop_bool = false;
    public boolean task_bool = false;

    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }

    class SingleTask_Content extends TaskContent {

        public SingleTask_Content(AtFairyImpl mFairy, String name) throws Exception {
            super(mFairy, name);
        }

        void init() throws Exception {
            gamePublicFuntion.init(true);
            setTaskName(1);
        }

        void content_01() throws Exception {
            if (timeCount(10, 0)) {
                if (frequencyMap("activity_end", 1)) {
                    setTaskEnd();
                    return;
                }
            }

            result = mFairy.findPic(795, 61, 1241, 310, "activity.png");
            if (result.sim > 0.8f) {
                err = 0;
                mFairy.onTap(0.8f, result, "活动", 1000);
            }

            result = mFairy.findPic("activity1.png");
            if (result.sim > 0.75f) {

                if (oneSecond()) {
                    mFairy.onTap(1220, 26, 1234, 40, "", 200);
                    mFairy.onTap(320, 23, 765, 50, "", 200);
                    mFairy.onTap(320, 23, 765, 50, "", 200);

                    gamePublicFuntion.activity_type(activity_type);
                }

                FindResult activity = mFairy.findPic(126, 70, 1191, 626, activity_name);
                if (activity.sim > 0.8f) {

                    if (activity_end(activity)) {
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic(activity.x - 90, activity.y + 135, activity.x + 146, activity.y + 356, "qian.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "前往", 3500);
                        setTaskName(2);
                        oneSecond = 0;
                        task_bool = false;
                        frequencyInit("activity_end");
                        return;
                    }
                }

                if (err == 3 || err == 5 || err == 7) {
                    mFairy.onTap(1219, 332, 1239, 350, "下一页活动", 1000);
                }

                return;
            }
        }

        boolean activity_end(FindResult activity) throws Exception {
            result = mFairy.findPic(activity.x - 90, activity.y + 135, activity.x + 146, activity.y + 356,
                    new String[]{"wan1.png", "wan2.png", "wan3.png", "wan4.png", "wan5.png"});
            if (result.sim > 0.75f) {
                setTaskEnd();
                return true;
            }
            return false;
        }

        void cancel() throws Exception {
            FindResult cancel = gamePublicFuntion.cancel();
            mFairy.onTap(0.8f, cancel, "取消 | 拒绝", 500);
        }//取消 | 拒绝

        void inOperation() throws Exception {
            cancel();

            result = mFairy.findPic(470, 495, 641, 600, "song.png");
            if (result.sim > 0.75f) {
                err = 0;
            }

            long l = mFairy.getColorNum(92, 712, 198, 718, "237,194,0", 0.95f);
            if (l > 100) {
                LtLog.e(mFairy.getLineInfo("过图中 >>>"));
                err = 0;
            }

            result = mFairy.findPic(6, 186, 54, 239, "jian1.png");
            mFairy.onTap(0.85f, result, "箭头", 500);

            gamePublicFuntion.fh();
            gamePublicFuntion.click_task();
            gamePublicFuntion.close_use();
            gamePublicFuntion.skip();
        }
    }

    public void li_rank() throws Exception {
        new SingleTask_Content(mFairy, "离开队伍") {
            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(new String[]{"rank1.png", "rank2.png"});
                mFairy.onTap(0.8f, result, "点击队伍", 1000);

                result = mFairy.findPic("rank3.png");
                if (result.sim > 0.8f) {
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(new String[]{"rankScene.png", "rank6.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(12, 552, 217, 670, "rank4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "退出队伍", 1000);
                        mFairy.onTap(727, 424, 757, 441, "", 500);
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic("rank5.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(727, 424, 757, 441, "", 500);
                        setTaskEnd();
                        return;
                    }

                }
            }
        };
    }//离开队伍

    public void li_fb() throws Exception {
        new SingleTask_Content(mFairy, "离开副本") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                if (gamePublicFuntion.fbScene()) {
                    mFairy.onTap(1234, 255, 1258, 269, "", 300);
                    mFairy.onTap(1234, 255, 1258, 269, "", 300);
                    mFairy.onTap(722, 427, 755, 440, "退出副本", 500);
                    setTaskEnd();
                } else {
                    if (frequencyMap("fb_count", 3)) {
                        setTaskEnd();
                        return;
                    }
                }


            }
        };

    }//离开副本

    public void set_up() throws Exception {
        new SingleTask_Content(mFairy, "设置") {
            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("menu.png");
                mFairy.onTap(0.8f, result, "点击菜单", 500);

                result = mFairy.findPic("set1.png");
                mFairy.onTap(0.8f, result, "设置", 500);

                result = mFairy.findPic("set2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    mFairy.onTap(134, 113, 149, 126, "极速", 200);
                    mFairy.onTap(134, 113, 149, 126, "极速", 200);
                    mFairy.onTap(134, 113, 149, 126, "极速", 200);

                    mFairy.onTap(26, 8, 51, 38, "", 500);
                    mFairy.onTap(631, 500, 666, 516, "", 500);
                    setTaskEnd();
                    return;
                } else {
                    result = mFairy.findPic("set3.png");
                    mFairy.onTap(0.8f, result, "画面设置", 500);
                }
            }
        };
    }//设置

    public void tl() throws Exception {
        new SingleTask_Content(mFairy, "脱离卡死") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("menu.png");
                mFairy.onTap(0.8f, result, "点击菜单", 500);

                result = mFairy.findPic("set1.png");
                mFairy.onTap(0.8f, result, "设置", 500);

                result = mFairy.findPic("tl1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(0.8f, result, "脱离卡死", 1000);
                    mFairy.onTap(730,422,763,439,"",5000);
                    setTaskEnd();
                    return;
                }
            }
        };
    }//脱离卡死

    public void set_tf() throws Exception {
        new SingleTask_Content(mFairy, "天赋设置") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                if(gamePublicFuntion.mainScene()){
                    mFairy.onTap(211,27,226,55,"",500);
                }

                result = mFairy.findPic("tf2.png");
                if(result.sim>0.8f){

                    switch (AtFairyConfig.getOption("tf")){
                        case "1":
                            mFairy.onTap(106,101,126,122,"1",500);
                            mFairy.onTap(106,101,126,122,"1",500);
                            break;
                        case "2":
                            mFairy.onTap(195,105,214,119,"2",500);
                            mFairy.onTap(195,105,214,119,"2",500);
                            break;
                    }
                    mFairy.onTap(134,528,178,544,"激活",500);
                    mFairy.onTap(134,528,178,544,"激活",500);
                    mFairy.onTap(30,9,37,30,"",500);
                    setTaskEnd();
                    return;
                }else{
                    result = mFairy.findPic("tf1.png");
                    mFairy.onTap(0.8f,result,"角色信息",500);
                }
            }
        };
    }//天赋设置

    public void cb() throws Exception {
        new SingleTask_Content(mFairy, "补充食物储备") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                if(gamePublicFuntion.mainScene()){
                    mFairy.onTap(211,27,226,55,"",500);
                }


                result = mFairy.findPic("cb5.png");
                if(result.sim>0.8f){
                    err=0;
                    for(int i =0;i<10;i++) {
                        mFairy.onTap(1113,444,1129,461,"",500);
                    }

                    mFairy.onTap(1017,568,1054,581,"",1000);

                    for(int i =0;i<12;i++) {
                        gamePublicFuntion.use();
                    }
                    mFairy.onTap(31,16,49,34,"",500);
                }

                result = mFairy.findPic("cb1.png");
                if(result.sim>0.8f){
                    err=0;

                    result = mFairy.findPic(607,130,1035,623,"cb4.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(0.8f,result,"商会",500);
                        return;
                    }

                    result = mFairy.findPic(607,130,1035,623,"cb3.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(0.8f,result,"获得途径",500);
                        return;
                    }


                    result = mFairy.findPic(323,168,449,258,"cb2.png");
                    mFairy.onTap(0.8f,result,113,277,139,300,"生命储备不足",1000);

                    result = mFairy.findPic(719,167,856,254,"cb2.png");
                    mFairy.onTap(0.8f,result,506,279,526,301,"技力储备不足",1000);

                    result = mFairy.findPic(1109,180,1220,242,"cb2.png");
                    mFairy.onTap(0.8f,result,885,276,910,297,"宠物储备不足",1000);


                    if(frequencyMap("c",1)){
                        mFairy.onTap(30,18,40,39,"",500);
                        setTaskEnd();
                        return;
                    }
                }else{
                    result = mFairy.findPic("cb6.png");
                    mFairy.onTap(0.8f,result,"食物储备",1000);
                }

            }
        };
    }//补充食物储备

    public void use_prop(final String str,final String grade) throws Exception {
        new SingleTask_Content(mFairy, "use_prop") {

            void create() throws Exception {
                super.create();
                LtLog.e("");
                LtLog.e("");
                LtLog.e("");
                LtLog.e("【开始找工具】");
                LtLog.e("【开始找工具】");
                LtLog.e("【开始找工具】");
                LtLog.e("");
                LtLog.e("");
                LtLog.e("");
            }

            void init() throws Exception {
                gamePublicFuntion.init(false);
                oneSecond = 0;
                setTaskName(1);
            }

            boolean judge_prop(FindResult cai) throws Exception {
                LtLog.e(mFairy.getLineInfo("用户勾选" + grade + "级"));
                switch (grade) {
                    case "1":
                        return true;
                    case "2":
                        result = mFairy.findPic(cai.x + 45, cai.y - 55, cai.x + 120, cai.y + 10, "p1.png");
                        if (result.sim < 0.85f) {
                            return true;
                        }
                        break;
                    case "3":
                        if (mFairy.findPic(cai.x + 45, cai.y - 55, cai.x + 120, cai.y + 10, "p1.png").sim < 0.85f
                                && mFairy.findPic(cai.x + 45, cai.y - 55, cai.x + 120, cai.y + 10, "p2.png").sim < 0.85f) {
                            return true;
                        }
                        break;
                    case "4":
                        result = mFairy.findPic(cai.x + 45, cai.y - 55, cai.x + 120, cai.y + 10, "p4.png");
                        if (result.sim > 0.85f) {
                            return true;
                        }
                        break;
                    default:
                        if (mFairy.findPic(cai.x + 45, cai.y - 55, cai.x + 120, cai.y + 10, "p1.png").sim < 0.85f
                                && mFairy.findPic(cai.x + 45, cai.y - 55, cai.x + 120, cai.y + 10, "p2.png").sim < 0.85f) {
                            return true;
                        }
                        break;
                }

                return false;
            }//判断

            void choice() throws Exception {
                LtLog.e(mFairy.getLineInfo("用户勾选" + grade + "级"));
                for (int i = 0; i < 10; i++) {
                    switch (grade) {
                        case "1":
                            result = mFairy.findPic("sp1.png");
                            if (result.sim > 0.85f) {
                                break;
                            } else {
                                mFairy.onTap(1186, 162, 1204, 175, "", 500);
                                mFairy.onTap(1140, 252, 1165, 261, "", 500);
                            }
                            break;
                        case "2":
                            result = mFairy.findPic("sp2.png");
                            if (result.sim > 0.85f) {
                                break;
                            } else {
                                mFairy.onTap(1186, 162, 1204, 175, "", 500);
                                mFairy.onTap(1133, 297, 1160, 314, "", 500);
                            }
                            break;
                        case "3":
                            result = mFairy.findPic("sp3.png");
                            if (result.sim > 0.85f) {
                                break;
                            } else {
                                mFairy.onTap(1186, 162, 1204, 175, "", 500);
                                mFairy.onTap(1135, 348, 1155, 363, "", 500);
                            }
                            break;
                        case "4":
                            result = mFairy.findPic("sp4.png");
                            if (result.sim > 0.85f) {
                                break;
                            } else {
                                mFairy.onTap(1186, 162, 1204, 175, "", 500);
                                mFairy.onTap(1135, 394, 1163, 407, "", 500);
                            }
                            break;
                        default:
                            result = mFairy.findPic("sp3.png");
                            if (result.sim > 0.85f) {
                                break;
                            } else {
                                mFairy.onTap(1186, 162, 1204, 175, "", 500);
                                mFairy.onTap(1135, 348, 1155, 363, "", 500);
                            }
                            break;
                    }
                }
            }//选择

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(795, 61, 1241, 310, "package.png");
                mFairy.onTap(0.8f, result, "背包", 1000);

                result = mFairy.findPic("package1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (oneSecond()) {
                        mFairy.onTap(809, 108, 847, 122, "道具", 500);

                        mFairy.onTap(1081, 603, 1136, 618, "", 500);
                    }

                    List<FindResult> listResult = mFairy.findPic(759, 149, 1195, 573, 0.9f, str);
                    for(int i = listResult.size()-1;i>=0;i--){
                        mFairy.onTap(listResult.get(i).x, listResult.get(i).y, listResult.get(i).x + 1, listResult.get(i).y + 1, "", 1000);

                        FindResult cai = mFairy.findPic(365, 148, 681, 587, "prop4.png");
                        if (cai.sim > 0.8f) {
                            if (judge_prop(cai)) {
                                result = mFairy.findPic(711, 138, 1143, 630, "package2.png");
                                mFairy.onTap(0.8f, result, "使用", 1000);
                                mFairy.onTap(30, 16, 42, 36, "", 500);
                                setTaskEnd();
                                return;
                            }
                            mFairy.onTap(970, 607, 994, 624, "", 1000);
                        }
                    }

                    if (frequencyMap("xunzhao", 1)) {
                        mFairy.ranSwipe(988, 500, 988, 166, 500, 1000);
                        if (frequencyMap("xunzhao_inner", 1)) {
                            mFairy.onTap(30, 16, 42, 36, "", 500);
                            oneSecond = 0;
                            setTaskName(2);
                            gamePublicFuntion.init(false);
                            return;
                        }
                    }
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(721, 67, 1266, 175, "shop.png");
                mFairy.onTap(0.8f, result, "商店", 1000);

                result = mFairy.findPic(new String[]{"shop7.png","shop8.png"});
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("商城界面"));
                    err = 0;

                    if (oneSecond()) {
                        mFairy.onTap(781, 692, 824, 707, "寄售", 1000);
                        mFairy.onTap(101,100,145,112,"我要购买",500);
                        for (int i = 0; i < 3; i++) {
                            result = mFairy.findPic("shop3.png");
                            if (result.sim > 0.8f) {
                                break;
                            }

                            result = mFairy.findPic(315, 127, 365, 601, "shop2.png");
                            mFairy.onTap(0.94f, result, "关闭分类", 1000);
                        }
                        for (int i = 0; i < 3; i++) {
                            mFairy.ranSwipe(205, 253, 205, 468, 200, 200);
                        }
                    }

                    result = mFairy.findPic("shop6.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(374, 177, 539, 302, new String[]{str});
                        LtLog.e(mFairy.getLineInfo("寻找工具 :"+result.sim));
                        if (result.sim > 0.8f) {
                            choice();
                            mFairy.onTap(577, 233, 654, 268, "prop-选择", 1000);
                        } else {
                            if (frequencyMap("shop_bool", 1)) {
                                mFairy.onTap(416, 159, 454, 172, "", 500);
                                oneSecond = 0;
                            }
                            return;
                        }

                        if (gamePublicFuntion.gm()) {
                            setTaskName(0);
                            return;
                        }
                    }

                    result = mFairy.findPic("shop3.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic(395, 179, 1227, 579, new String[]{str});
                        LtLog.e(mFairy.getLineInfo("工具分类 ："+result.sim));
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "prop-分类", 1000);
                            return;
                        }
                    } else {
                        result = mFairy.findPic(109, 146, 299, 589, new String[]{"shop4.png", "shop5.png"});
                        mFairy.onTap(0.8f, result, "采集制作 | 工具", 500);
                    }

                }
            }
        };

        LtLog.e("");
        LtLog.e("");
        LtLog.e("");
        LtLog.e("【结束找工具】");
        LtLog.e("【结束找工具】");
        LtLog.e("【结束找工具】");
        LtLog.e("");
        LtLog.e("");
        LtLog.e("");
    }

    public void wtggb() throws Exception {
        new SingleTask_Content(mFairy, "委托公告板") {

            void create() throws Exception {
                super.create();
                activity_name = "wtggb.png";
                activity_type = 3;
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                gamePublicFuntion.chat_skip();
                gamePublicFuntion.sub();
                gamePublicFuntion.chat_sub();

                result = mFairy.findPic("wtggb1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(142, 149, 190, 173, "委托1", 500);
                    mFairy.onTap(1073, 641, 1141, 651, "", 500);
                    mFairy.onTap(341, 174, 375, 203, "委托2", 500);
                    mFairy.onTap(1073, 641, 1141, 651, "", 500);
                    mFairy.onTap(489, 160, 524, 186, "委托3", 500);
                    mFairy.onTap(1073, 641, 1141, 651, "", 500);
                    mFairy.onTap(151, 291, 191, 327, "委托4", 500);
                    mFairy.onTap(1073, 641, 1141, 651, "", 500);
                    mFairy.onTap(347, 379, 382, 409, "委托5", 500);
                    mFairy.onTap(1073, 641, 1141, 651, "", 500);
                    mFairy.onTap(567, 391, 595, 436, "委托6", 500);
                    mFairy.onTap(1073, 641, 1141, 651, "", 500);

                    result = mFairy.findPic(177, 212, 545, 496, "wtggb3.png");
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                    }

                    gamePublicFuntion.close(1);

                    task_bool = true;
                }


                result = mFairy.findPic("gmScene.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (gamePublicFuntion.gm()) {
                        gamePublicFuntion.close(2);
                    }
                    judgeStop_bool = true;
                }

                if (gamePublicFuntion.mainScene()) {

                    if (err > 5) {
                        task_bool = true;
                    }

                    if (gamePublicFuntion.judgeStop(3, true) || judgeStop_bool) {

                        if (task_bool) {
                            for (int i = 0; i < 5; i++) {
                                result = mFairy.findPic(15, 230, 83, 273 + (i * 43), new String[]{"wtggb2.png", "wtggb4.png", "wtggb5.png"});
                                if (result.sim > 0.75f) {
                                    break;
                                }
                            }
                            if (result.sim > 0.75f) {
                                err = 0;
                                mFairy.onTap(0.75f, result, "委托", 1000);
                                judgeStop_bool = false;

                                result = mFairy.findPic(758, 527, 832, 609, "xl.png");
                                if (result.sim > 0.8f) {
                                    Thread.sleep(3000);
                                    mFairy.initMatTime();
                                    return;
                                }

                                if (gamePublicFuntion.judgeStop(6, false)) {
                                    gamePublicFuntion.auto_battle(30000);
                                }

                            } else {
                                taskSlide.slideRange(new int[]{4, 5, 6, 7, 8}, 2, 0);
                            }

                        } else {
                            gamePublicFuntion.chat_start();
                        }
                    }
                } else {
                    mFairy.initMatTime();
                }
            }
        };
    }//委托公告板

    public int mtzc;
    public void mtzc() throws Exception {
        new SingleTask_Content(mFairy, "码头装船") {

            void create() throws Exception {
                super.create();
                activity_name = "mtzc.png";
                activity_type = 3;
                mtzc = 1;
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                gamePublicFuntion.click_ta(new String[]{"mtzc1.png"});

                result = mFairy.findPic("mtzc2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic("mtzc8.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(35, 14, 48, 38, "", 500);
                        setTaskEnd();
                        return;
                    }

                    LtLog.e(mFairy.getLineInfo("准备装载【" + mtzc + "】个货物"));
                    switch (mtzc) {
                        case 1:
                            mFairy.onTap(215, 256, 258, 286, "", 800);
                            break;
                        case 2:
                            mFairy.onTap(447, 262, 476, 289, "", 800);
                            break;
                        case 3:
                            mFairy.onTap(677, 259, 709, 292, "", 800);
                            break;
                        case 4:
                            mFairy.onTap(223, 428, 245, 451, "", 800);
                            break;
                        case 5:
                            mFairy.onTap(449, 420, 485, 456, "", 800);
                            break;
                        case 6:
                            mFairy.onTap(673, 424, 704, 459, "", 800);
                            break;
                        case 7:
                            mFairy.onTap(926, 574, 1031, 592, "领取全部奖励", 5000);
                            setTaskEnd();
                            break;
                    }

                    result = mFairy.findPic("mtzc3.png");
                    if (result.sim > 0.8f) {
                        mtzc++;
                        return;
                    }

                    result = mFairy.findPic("mtzc4.png");
                    if (result.sim > 0.96f) {
                        mFairy.onTap(0.96f, result, "提交", 500);
                    } else {
                        mFairy.onTap(879, 229, 905, 255, "物品不足", 500);

                    }
                    result = mFairy.findPic(695, 308, 966, 595, "mtzc5.png");
                    mFairy.onTap(0.8f, result, "获取途径", 500);

                    result = mFairy.findPic(700, 156, 999, 522, new String[]{"mtzc6.png", "mtzc7.png"});
                    mFairy.onTap(0.8f, result, "寄售 | 商会", 500);
                }

                if (gamePublicFuntion.gmScene()) {
                    err = 0;
                    if (gamePublicFuntion.gm()) {
                        gamePublicFuntion.close(1);
                    }
                    judgeStop_bool = true;
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true) || judgeStop_bool) {
                        gamePublicFuntion.chat_start();
                    }
                }
            }
        };
    }//码头装船

    public void cwpq() throws Exception {
        new SingleTask_Content(mFairy, "宠物派遣") {

            void create() throws Exception {
                super.create();
                activity_name = "cwpq.png";
                activity_type = 3;
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                gamePublicFuntion.chat_skip();
                gamePublicFuntion.sub();
                gamePublicFuntion.chat_sub();

                gamePublicFuntion.click_ta(new String[]{"syjz.png"});

                result = mFairy.findPic("cwpq4.png");
                mFairy.onTap(0.8f, result, "领取奖励", 500);

                result = mFairy.findPic("cwpq1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("cwpq3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "查看结果", 4000);
                        return;
                    }

                    result = mFairy.findPic(69, 99, 794, 659, "cwpq2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, result.x - 45, result.y + 1, result.x - 40, result.y + 10, "", 500);
                        return;
                    }

                    if (oneSecond() == false) {
                        result = mFairy.findPic(896, 572, 1139, 680, "cwpq8.png");
                        if (result.sim > 0.8f) {
                            long l = mFairy.getColorNum(878, 566, 912, 585, "219,50,66", 0.98f);
                            if (l > 50) {
                                mFairy.onTap(873, 543, 899, 561, "", 800);

                                result = mFairy.findPic(748, 318, 935, 542, "cwpq10.png");
                                mFairy.onTap(0.8f, result, "获得途径", 800);

                                result = mFairy.findPic(745, 132, 963, 499, "cwpq11.png");
                                mFairy.onTap(0.8f, result, "寄售", 800);
                                return;
                            }

                            mFairy.onTap(981, 624, 1044, 639, "派遣宠物", 500);

                        }
                    }

                    result = mFairy.findPic(69, 99, 794, 659, "cwpq5.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "紫色品质", 500);
                        return;
                    }

                    result = mFairy.findPic(69, 99, 794, 659, "cwpq6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "蓝色品质", 500);
                        return;
                    }

                    result = mFairy.findPic(69, 99, 794, 659, "cwpq7.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "绿色品质", 500);
                        return;
                    }
                }

                result = mFairy.findPic("cwpq12.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    for (int i = 0; i < 5; i++) {
                        mFairy.onTap(300, 190 + (i * 100), 310, 200 + (i * 100), "选宠物", 500);
                        result = mFairy.findPic("cwpq13.png");
                        mFairy.onTap(0.8f, result, 509, 451, 551, 464, "不移出上阵宠物", 500);
                    }
                    mFairy.onTap(1027, 554, 1071, 569, "派遣", 500);
                    if (mapCount(0.8f, 660, 87, 845, 282, "cwpq14.png")) {
                        mFairy.onTap(1113, 95, 1135, 111, "宠物不足", 500);
                        setTaskEnd();
                        return;
                    }
                }

                result = mFairy.findPic("gmScene.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (gamePublicFuntion.gm()) {
                        gamePublicFuntion.close(1);
                    }
                }


                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {
                        gamePublicFuntion.chat_start();
                    }
                }
            }
        };
    }//宠物派遣

    public int yscs = 1;
    public void yscs() throws Exception {
        new SingleTask_Content(mFairy, "原神差事") {

            void create() throws Exception {
                super.create();
                activity_name = "yscs.png";
                activity_type = 4;

                if (!AtFairyConfig.getOption("yscs").equals("")) {
                    yscs = Integer.parseInt(AtFairyConfig.getOption("yscs"));
                }
            }

            void cancel() throws Exception {
                FindResult cancel = gamePublicFuntion.cancel();
                if (cancel.sim > 0.8f) {

                    result = mFairy.findPic(530, 197, 727, 341, "yscs6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(725, 424, 762, 441, "前往领取下一轮", 500);
                        task_bool = false;
                        return;
                    }

                    if (frequencyMap("quxiao", 1)) {
                        mFairy.onTap(0.8f, cancel, "取消 | 拒绝", 500);
                    }
                }

            }

            public void cs_bool() throws Exception {

                result = mFairy.findPic(116, 228, 255, 409, "yscs4.png");
                if (result.sim > 0.8f) {
                    yscs = 1;
                }
                result = mFairy.findPic(258, 111, 400, 273, "yscs4.png");
                if (result.sim > 0.8f) {
                    yscs = 2;
                }
                result = mFairy.findPic(394, 257, 524, 398, "yscs4.png");
                if (result.sim > 0.8f) {
                    yscs = 3;
                }
                result = mFairy.findPic(242, 385, 395, 527, "yscs4.png");
                if (result.sim > 0.8f) {
                    yscs = 4;
                }

            }//差事判断

            boolean activity_end(FindResult activity) throws Exception {
                result = mFairy.findPic(activity.x - 90, activity.y + 135, activity.x + 146, activity.y + 356,
                        new String[]{"wan1.png", "wan2.png", "wan3.png", "wan5.png"});
                if (result.sim > 0.75f) {
                    setTaskEnd();
                    return true;
                }
                return false;
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                gamePublicFuntion.chat_skip();
                gamePublicFuntion.sub();
                gamePublicFuntion.chat_sub();
                gamePublicFuntion.close_use();
                gamePublicFuntion.click_ta(new String[]{"yscs1.png"});

                if (gamePublicFuntion.gmScene()) {
                    err = 0;
                    if (gamePublicFuntion.gm()) {
                        gamePublicFuntion.close(2);
                    }
                    judgeStop_bool = true;
                }

                result = mFairy.findPic("yscs2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    cs_bool();

                    switch (yscs) {
                        case 1:
                            mFairy.onTap(173, 313, 203, 353, "水神", 500);
                            break;
                        case 2:
                            mFairy.onTap(317, 177, 335, 196, "风神", 500);
                            break;
                        case 3:
                            mFairy.onTap(431, 305, 461, 342, "土神", 500);
                            break;
                        case 4:
                            mFairy.onTap(304, 436, 340, 475, "火神", 500);
                            break;
                    }

                    result = mFairy.findPic("yscs3.png");
                    mFairy.onTap(0.8f, result, "领取差事", 1000);

                    result = mFairy.findPic(524, 111, 737, 192, "yscs8.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(39, 14, 53, 42, "", 500);
                        setTaskEnd();
                        return;
                    }

                    mFairy.onTap(30, 16, 42, 29, "", 500);
                    judgeStop_bool = true;
                    task_bool = true;
                }


                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true) || judgeStop_bool) {

                        if (err > 5) {
                            task_bool = true;
                        }

                        if (task_bool) {
                            result = mFairy.findPic(15, 212, 83, 442, new String[]{"yscs5.png"});
                            if (result.sim > 0.75f) {
                                err = 0;
                                mFairy.onTap(0.75f, result, "差事", 2500);
                                judgeStop_bool = false;

                                if (gamePublicFuntion.judgeStop(5, false)) {

                                    result = mFairy.findPic(44, 145, 159, 214, "yscs7.png");
                                    if (result.sim > 0.8f) {
                                        switch (yscs) {
                                            case 1:
                                                use_prop("prop3.png","0");
                                                gamePublicFuntion.auto_prop("prop3.png", 30000);
                                                break;
                                            case 2:
                                                use_prop("prop1.png","0");
                                                gamePublicFuntion.auto_prop("prop1.png", 30000);
                                                break;
                                            case 3:
                                                use_prop("prop2.png","0");
                                                gamePublicFuntion.auto_prop("prop2.png", 30000);
                                                break;
                                            case 4:
                                                break;
                                        }
                                    } else {
                                        gamePublicFuntion.auto_battle(30000);
                                    }
                                }

                            } else {
                                taskSlide.slideRange(new int[]{4, 5, 6, 7, 8}, 2, 0);
                            }
                        } else {
                            gamePublicFuntion.chat_start();
                        }
                    }
                } else {
                    mFairy.initMatTime();
                }
            }
        };
    }//原神差事

    public void ttqy() throws Exception {
        new SingleTask_Content(mFairy, "图腾祈愿") {

            void create() throws Exception {
                super.create();
                activity_name = "ttqy.png";
                activity_type = 4;
                oneSecond=0;
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                gamePublicFuntion.close_use();
                gamePublicFuntion.click_ta(new String[]{"ttqy1.png"});

                if (gamePublicFuntion.gmScene()) {
                    err = 0;
                    if (gamePublicFuntion.gm()) {
                        gamePublicFuntion.close(1);
                    }
                }


                result = mFairy.findPic("ttqy5.png");
                mFairy.onTap(0.8f, result, 664, 119, 1079, 536, "商会购买", 500);

                result = mFairy.findPic("ttqy2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    long l;
                    boolean qz_bool = false;

                    result = mFairy.findPic("ttqy11.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(28, 18, 41, 36, "", 500);
                        setTaskEnd();
                        return;
                    }

                    result = mFairy.findPic("ttqy6.png");
                    mFairy.onTap(0.8f, result, "祈愿", 1000);

                    result = mFairy.findPic(new String[]{"ttqy7.png", "ttqy8.png"});
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "获得奖励", 1000);
                        oneSecond = 0;
                        return;
                    }

                    result = mFairy.findPic(627, 407, 1229, 500, "ttqy9.png");
                    if (result.sim > 0.8f) {
                        if (oneSecond()) {
                            if (getTimeStamp(AtFairyConfig.getOption("qztime")) != -1) {
                                LtLog.e(mFairy.getLineInfo("开始求助"));

                                l = mFairy.getColorNum(625, 537, 697, 560, "216,50,66", 0.92f);
                                if (l > 10) {
                                    mFairy.onTap(1054, 535, 1124, 556, "求助", 500);
                                    qz_bool = true;
                                }

                                l = mFairy.getColorNum(836, 536, 908, 557, "216,50,66", 0.92f);
                                if (l > 10) {
                                    mFairy.onTap(928, 444, 944, 457, "求助", 500);
                                    qz_bool = true;
                                }

                                l = mFairy.getColorNum(1054, 535, 1124, 556, "216,50,66", 0.92f);
                                if (l > 10) {
                                    mFairy.onTap(1145, 445, 1161, 458, "求助", 500);
                                    qz_bool = true;
                                }

                                if (qz_bool) {
                                    Thread.sleep(getTimeStamp(AtFairyConfig.getOption("qztime")));
                                }
                            }
                        }
                    }


                    result = mFairy.findPic(361, 132, 894, 514, "ttqy10.png");
                    if (result.sim > 0.8f) {
                        switch (AtFairyConfig.getOption("tt_choice")) {
                            case "1":
                                mFairy.onTap(1155, 106, 1166, 127, "遇到技能书-放弃此轮", 1000);
                                mFairy.onTap(727, 424, 757, 441, "", 500);
                                break;
                            default:
                                setTaskEnd();
                                break;
                        }
                        return;
                    }

                    result = mFairy.findPic(664, 119, 1079, 536, "ttqy4.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "寄售", 1000);
                        return;
                    }

                    result = mFairy.findPic(570, 530, 1191, 644, "ttqy3.png");
                    mFairy.onTap(0.8f, result, "奉上贡品", 1000);
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {
                        gamePublicFuntion.chat_start();
                    }
                }
            }

        };
    }//图腾祈愿

    public void wbt() throws Exception {
        new SingleTask_Content(mFairy, "挖宝图") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
                oneSecond=0;
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(795, 61, 1241, 310, "package.png");
                mFairy.onTap(0.8f, result, "背包", 1000);


                result = mFairy.findPic("map6.png");
                if (result.sim > 0.8f) {
                    err=0;

                    for(int i =0;i<3;i++) {
                        result = mFairy.findPic(219, 6, 1075, 707, new String[]{"bt3.png", "bt4.png"});
                        mFairy.onTap(0.8f, result, "发现宝藏点", 5000);
                    }

                    gamePublicFuntion.close(3);
                    setTaskName(2);
                }

                result = mFairy.findPic("package1.png");
                if (result.sim > 0.8f) {
                    err=0;

                    if(oneSecond()){
                        mFairy.onTap(807,109,844,122,"道具",500);
                    }

                    result = mFairy.findPic(690,102,1167,648,"bt2.png");
                    if(result.sim>0.8f){
                        mFairy.onTap(0.8f,result,"查看",1000);
                        return;
                    }

                    result = mFairy.findPic(758,133,1205,569,"bt1.png");
                    if(result.sim>0.8f){
                        frequencyInit("err_bt");
                        mFairy.onTap(0.8f,result,"发现宝图",1000);
                        return;
                    }

                    if(frequencyMap("bt",1)){
                        if(frequencyMap("err_bt",3)){
                            mFairy.onTap(22,12,47,36,"",500);
                            setTaskEnd();
                            return;
                        }
                        mFairy.ranSwipe(963,500,963,230,500,1000);
                    }
                }

            }

            void content_02() throws Exception {
                timeCount(10,0);

                if(gamePublicFuntion.mainScene()){
                    if(gamePublicFuntion.judgeStop(3,false)){

                        result = mFairy.findPic(new String[]{"bt5.png","bt6.png"});
                        if(result.sim>0.8f){
                            err=0;
                            mFairy.onTap(0.8f,result,"挖掘",6000);
                            mFairy.initMatTime();
                        }

                    }
                }
            }
        };
    }//挖宝图

    public int fl = 0;
    public void fl() throws Exception {
        new SingleTask_Content(mFairy, "福利领取") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
                fl = 0;
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("menu.png");
                mFairy.onTap(0.8f, result, "点击菜单", 500);

                result = mFairy.findPic("fl.png");
                mFairy.onTap(0.8f, result, "福利", 500);

                result = mFairy.findPic("fl1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (oneSecond()) {
                        mFairy.ranSwipe(185, 200, 185, 550, 500, 1000);
                    }

                    if (fl > 5) {
                        mFairy.onTap(31, 14, 47, 40, "", 500);
                        setTaskEnd();
                        return;
                    }

                    mFairy.onTap(160, 130 + (fl * 50), 161, 131 + (fl * 50), "第" + (fl + 1) + "个", 1000);

                    result = mFairy.findPic("fl2.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("每日签到"));
                        switch (mFairy.week()) {
                            case 1:
                                mFairy.onTap(566, 318, 586, 342, "", 500);
                                mFairy.onTap(566, 318, 586, 342, "", 500);
                                break;
                            case 2:
                                mFairy.onTap(683, 325, 713, 349, "", 500);
                                mFairy.onTap(683, 325, 713, 349, "", 500);
                                break;
                            case 3:
                                mFairy.onTap(803, 319, 835, 350, "", 500);
                                mFairy.onTap(803, 319, 835, 350, "", 500);
                                break;
                            case 4:
                                mFairy.onTap(919, 320, 942, 346, "", 500);
                                mFairy.onTap(919, 320, 942, 346, "", 500);
                                break;
                            case 5:
                                mFairy.onTap(601, 489, 631, 514, "", 500);
                                mFairy.onTap(601, 489, 631, 514, "", 500);
                                break;
                            case 6:
                                mFairy.onTap(734, 486, 758, 514, "", 500);
                                mFairy.onTap(734, 486, 758, 514, "", 500);
                                break;
                            case 7:
                                mFairy.onTap(851, 486, 875, 508, "", 500);
                                mFairy.onTap(851, 486, 875, 508, "", 500);
                                mFairy.onTap(997, 485, 1023, 505, "", 500);
                                mFairy.onTap(997, 485, 1023, 505, "", 500);
                                break;
                        }


                    }
                    fl++;
                }
            }
        };
    }//福利领取

    public void edsb() throws Exception {
        new SingleTask_Content(mFairy, "恩典石币领取") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("menu.png");
                mFairy.onTap(0.8f, result, "点击菜单", 500);

                result = mFairy.findPic("bl.png");
                mFairy.onTap(0.8f, result, "部落", 500);

                result = mFairy.findPic("bl1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.onTap(785, 372, 797, 401, "恩典图腾", 500);
                    mFairy.onTap(1097, 520, 1133, 532, "领取", 500);
                    mFairy.onTap(37, 17, 48, 38, "", 500);
                    setTaskEnd();
                    return;
                } else {
                    result = mFairy.findPic("bl2.png");
                    mFairy.onTap(0.8f, result, "主岛建设", 500);
                }
            }
        };
    }//恩典石币领取

    public void mrxl() throws Exception {
        new SingleTask_Content(mFairy, "每日小礼") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(721, 67, 1266, 175, "shop.png");
                mFairy.onTap(0.8f, result, "商店", 1000);

                result = mFairy.findPic("shop1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(99, 111, 410, 281, "mrxl3.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "每日小礼", 300);
                        mFairy.onTap(0.8f, result, "每日小礼", 800);
                        mFairy.onTap(1013, 565, 1055, 580, "", 500);
                        mFairy.onTap(26, 13, 42, 34, "", 500);
                        setTaskEnd();
                        return;
                    } else {
                        result = mFairy.findPic(855, 651, 1138, 717, "mrxl1.png");
                        mFairy.onTap(0.8f, result, "商城", 1000);

                        result = mFairy.findPic(29, 55, 249, 151, "mrxl2.png");
                        mFairy.onTap(0.8f, result, "限购特惠", 1000);
                    }


                }
            }
        };
    }//每日小礼

    public void fj() throws Exception {
        new SingleTask_Content(mFairy, "分解装备") {
            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(795, 61, 1241, 310, "package.png");
                mFairy.onTap(0.8f, result, "背包", 1000);

                result = mFairy.findPic("package1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("fj.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "分解", 500);
                    } else {
                        mFairy.onTap(1103, 106, 1141, 126, "", 500);
                    }
                }

                result = mFairy.findPic("fj1.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if (AtFairyConfig.getOption("fj1").equals("1")) {
                        mFairy.onTap(182, 585, 194, 600, "普通", 500);
                    }

                    if (AtFairyConfig.getOption("fj2").equals("1")) {
                        mFairy.onTap(284, 586, 301, 600, "优秀", 500);
                    }

                    if (AtFairyConfig.getOption("fj3").equals("1")) {
                        mFairy.onTap(378, 580, 394, 601, "精良", 500);
                    }
                    mFairy.onTap(944, 575, 975, 588, "", 500);
                    mFairy.onTap(1069, 97, 1094, 119, "", 500);
                    setTaskEnd();
                    return;
                }

            }
        };
    }//分解装备

    public void jysc(final String str) throws Exception {
        new SingleTask_Content(mFairy, "家园生产 ：" + str) {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic(795, 61, 1241, 310, "jy.png");
                mFairy.onTap(0.8f, result, "家园", 500);

                result = mFairy.findPic("map.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    result = mFairy.findPic(318, 28, 940, 656, str);
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "生产地", 500);
                        mFairy.onTap(1087, 480, 1116, 490, "", 1000);

                        result = mFairy.findPic("jy1.png");
                        mFairy.onTap(0.8f, result, 711, 423, 759, 434, "确定传送到家园岛", 500);

                        result = mFairy.findPic("map.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(33, 18, 49, 44, "", 500);
                        }
                        setTaskName(2);
                        return;
                    }
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                gamePublicFuntion.click_ta(new String[]{"syjz.png"});

                result = mFairy.findPic("sh.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(549, 614, 595, 624, "", 500);
                    mFairy.onTap(26, 18, 46, 48, "", 500);
                    setTaskEnd();
                    return;
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {
                        gamePublicFuntion.chat_start();
                    }
                }
            }
        };
    }//家园生产

    public void jzgf() throws Exception {
        new SingleTask_Content(mFairy, "建造工坊") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic(795, 61, 1241, 310, "jy.png");
                mFairy.onTap(0.8f, result, "家园", 500);

                result = mFairy.findPic("map.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(318, 28, 940, 656, "jzgf.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "建筑工坊", 500);

                        mFairy.onTap(1087, 480, 1116, 490, "", 1000);

                        result = mFairy.findPic("jy1.png");
                        mFairy.onTap(0.8f, result, 711, 423, 759, 434, "确定传送到家园岛", 500);

                        result = mFairy.findPic("map.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(33, 18, 49, 44, "", 500);
                        }
                        setTaskName(2);
                        judgeOneSecond = 0;
                        return;
                    }
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                gamePublicFuntion.click_ta(new String[]{"syjz.png"});

                result = mFairy.findPic("jzgf1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(420, 43, 602, 116, "jzgf8.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(566, 615, 597, 630, "", 500);
                        return;
                    }

                    result = mFairy.findPic(374,39,557,131, "jia2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(565,618,608,631,"",500);
                        mFairy.onTap(29,26,42,39,"",500);
                        setTaskEnd();
                        return;
                    }

                    if (judgeOneSecond()) {
                        switch (AtFairyConfig.getOption("jzgf")) {
                            case "1":
                            case "2":
                            case "3":
                            case "4":
                                result = mFairy.findPic(137, 177, 240, 595, "jzgf2.png");
                                if (result.sim > 0.8f) {
                                    switch (AtFairyConfig.getOption("jzgf")) {
                                        case "1":
                                            mFairy.onTap(127, 247, 220, 280, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "2":
                                            mFairy.onTap(148, 339, 207, 360, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "3":
                                            mFairy.onTap(136, 444, 196, 465, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "4":
                                            mFairy.onTap(124, 536, 175, 555, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                    }
                                } else {
                                    result = mFairy.findPic(101, 55, 216, 713, "jzgf3.png");
                                    mFairy.onTap(0.8f, result, "木板", 500);
                                }
                                break;
                            case "5":
                            case "6":
                            case "7":
                            case "8":
                                result = mFairy.findPic(137, 177, 240, 595, "jzgf4.png");
                                if (result.sim > 0.8f) {
                                    switch (AtFairyConfig.getOption("jzgf")) {
                                        case "5":
                                            mFairy.onTap(150, 310, 208, 337, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "6":
                                            mFairy.onTap(158, 399, 215, 425, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "7":
                                            mFairy.onTap(126, 501, 193, 524, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "8":
                                            mFairy.onTap(123, 603, 183, 616, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                    }

                                } else {
                                    result = mFairy.findPic(101, 55, 216, 713, "jzgf5.png");
                                    mFairy.onTap(0.8f, result, "砖块", 500);
                                }
                                break;
                        }
                        return;
                    }


                    result = mFairy.findPic(442, 35, 573, 119, "jzgf6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(491, 413, 518, 427, "输入数量", 1000);

                        for (int i = 0; i < 5; i++) {
                            mFairy.onTap(629, 219, 638, 235, "", 200);
                        }
                        mFairy.onTap(554, 610, 597, 622, "", 300);
                        mFairy.onTap(554, 610, 597, 622, "开始生产", 300);
                        mFairy.onTap(23, 19, 47, 48, "", 500);
                        setTaskEnd();
                        return;
                    }
                    return;
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {
                        gamePublicFuntion.chat_start();
                    }
                }
            }
        };
    }//建造工坊

    public void rldl() throws Exception {
        new SingleTask_Content(mFairy, "熔炼大炉") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic(795, 61, 1241, 310, "jy.png");
                mFairy.onTap(0.8f, result, "家园", 500);

                result = mFairy.findPic("map.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(318, 28, 940, 656, "rldl.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "熔炼大炉", 500);

                        mFairy.onTap(1087, 480, 1116, 490, "", 1000);

                        result = mFairy.findPic("jy1.png");
                        mFairy.onTap(0.8f, result, 711, 423, 759, 434, "确定传送到家园岛", 500);

                        result = mFairy.findPic("map.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(33, 18, 49, 44, "", 500);
                        }
                        setTaskName(2);
                        judgeOneSecond = 0;
                        return;
                    }
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                gamePublicFuntion.click_ta(new String[]{"syjz.png"});

                result = mFairy.findPic("rldl1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(420, 43, 602, 116, "jzgf8.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(566, 615, 597, 630, "", 500);
                        return;
                    }

                    result = mFairy.findPic(374,39,557,131, "jia2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(565,618,608,631,"",500);
                        mFairy.onTap(29,26,42,39,"",500);
                        setTaskEnd();
                        return;
                    }

                    if (judgeOneSecond()) {
                        switch (AtFairyConfig.getOption("rldl")) {
                            case "1":
                            case "2":
                            case "3":
                            case "4":
                                result = mFairy.findPic(11, 136, 143, 659, "rldl2.png");
                                if (result.sim > 0.8f) {
                                    switch (AtFairyConfig.getOption("rldl")) {
                                        case "1":
                                            mFairy.onTap(127, 247, 220, 280, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "2":
                                            mFairy.onTap(148, 339, 207, 360, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "3":
                                            mFairy.onTap(136, 444, 196, 465, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "4":
                                            mFairy.onTap(124, 536, 175, 555, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                    }
                                } else {
                                    result = mFairy.findPic(101, 55, 216, 713, "rldl3.png");
                                    mFairy.onTap(0.8f, result, "矿锭", 500);
                                }
                                break;
                        }
                        return;
                    }

                    result = mFairy.findPic(442, 35, 573, 119, "jzgf6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(491, 413, 518, 427, "输入数量", 1000);

                        for (int i = 0; i < 5; i++) {
                            mFairy.onTap(629, 219, 638, 235, "", 200);
                        }
                        mFairy.onTap(554, 610, 597, 622, "", 300);
                        mFairy.onTap(554, 610, 597, 622, "开始生产", 300);
                        mFairy.onTap(23, 19, 47, 48, "", 500);
                        setTaskEnd();
                        return;
                    }
                    return;
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {
                        gamePublicFuntion.chat_start();
                    }
                }
            }
        };
    }//熔炼大炉

    public void lhzl() throws Exception {
        new SingleTask_Content(mFairy, "猎户之里") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic(795, 61, 1241, 310, "jy.png");
                mFairy.onTap(0.8f, result, "家园", 500);

                result = mFairy.findPic("map.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(318, 28, 940, 656, "lhzl.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "猎户之里", 500);

                        mFairy.onTap(1087, 480, 1116, 490, "", 1000);

                        result = mFairy.findPic("jy1.png");
                        mFairy.onTap(0.8f, result, 711, 423, 759, 434, "确定传送到家园岛", 500);

                        result = mFairy.findPic("map.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(33, 18, 49, 44, "", 500);
                        }
                        setTaskName(2);
                        judgeOneSecond = 0;
                        return;
                    }
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                gamePublicFuntion.click_ta(new String[]{"syjz.png"});

                result = mFairy.findPic("lhzl1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(420, 43, 602, 116, "jzgf8.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(566, 615, 597, 630, "", 500);
                        return;
                    }

                    result = mFairy.findPic(374,39,557,131, "jia2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(565,618,608,631,"",500);
                        mFairy.onTap(29,26,42,39,"",500);
                        setTaskEnd();
                        return;
                    }

                    if (judgeOneSecond()) {
                        switch (AtFairyConfig.getOption("lhzl")) {
                            case "1":
                            case "2":
                            case "3":
                            case "4":
                                result = mFairy.findPic(6,70,328,717, "lhzl2.png");
                                if (result.sim > 0.8f) {
                                    switch (AtFairyConfig.getOption("lhzl")) {
                                        case "1":
                                            mFairy.onTap(127, 247, 220, 280, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "2":
                                            mFairy.onTap(148, 339, 207, 360, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "3":
                                            mFairy.onTap(136, 444, 196, 465, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "4":
                                            mFairy.onTap(124, 536, 175, 555, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                    }
                                } else {
                                    result = mFairy.findPic(101, 55, 216, 713, "lhzl4.png");
                                    mFairy.onTap(0.8f, result, "皮革", 500);
                                }
                                break;
                            case "5":
                            case "6":
                            case "7":
                            case "8":
                                result = mFairy.findPic(6,70,328,717, "lhzl3.png");
                                if (result.sim > 0.8f) {
                                    switch (AtFairyConfig.getOption("lhzl")) {
                                        case "5":
                                            mFairy.onTap(150, 310, 208, 337, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "6":
                                            mFairy.onTap(158, 399, 215, 425, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "7":
                                            mFairy.onTap(126, 501, 193, 524, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "8":
                                            mFairy.onTap(123, 603, 183, 616, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                    }

                                } else {
                                    result = mFairy.findPic(101, 55, 216, 713, "lhzl5.png");
                                    mFairy.onTap(0.8f, result, "几丁质", 500);
                                }
                                break;
                        }
                        return;
                    }


                    result = mFairy.findPic(442, 35, 573, 119, "jzgf6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(491, 413, 518, 427, "输入数量", 1000);

                        for (int i = 0; i < 5; i++) {
                            mFairy.onTap(629, 219, 638, 235, "", 200);
                        }
                        mFairy.onTap(554, 610, 597, 622, "", 300);
                        mFairy.onTap(554, 610, 597, 622, "开始生产", 300);
                        mFairy.onTap(23, 19, 47, 48, "", 500);
                        setTaskEnd();
                        return;
                    }
                    return;
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {
                        gamePublicFuntion.chat_start();
                    }
                }
            }
        };
    }//猎户之里

    public void fzxw() throws Exception {
        new SingleTask_Content(mFairy, "纺织小屋") {

            void init() throws Exception {
                gamePublicFuntion.init(false);
                setTaskName(1);
            }

            void content_01() throws Exception {
                timeCount(10, 99);

                result = mFairy.findPic(795, 61, 1241, 310, "jy.png");
                mFairy.onTap(0.8f, result, "家园", 500);

                result = mFairy.findPic("map.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(318, 28, 940, 656, "fzxw.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "纺织小屋", 500);

                        mFairy.onTap(1087, 480, 1116, 490, "", 1000);

                        result = mFairy.findPic("jy1.png");
                        mFairy.onTap(0.8f, result, 711, 423, 759, 434, "确定传送到家园岛", 500);

                        result = mFairy.findPic("map.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(33, 18, 49, 44, "", 500);
                        }
                        setTaskName(2);
                        judgeOneSecond = 0;
                        return;
                    }
                }
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                gamePublicFuntion.click_ta(new String[]{"syjz.png"});

                result = mFairy.findPic("fzxw1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(420, 43, 602, 116, "jzgf8.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(566, 615, 597, 630, "", 500);
                        return;
                    }

                    result = mFairy.findPic(374,39,557,131, "jia2.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(565,618,608,631,"",500);
                        mFairy.onTap(29,26,42,39,"",500);
                        setTaskEnd();
                        return;
                    }

                    if (judgeOneSecond()) {
                        switch (AtFairyConfig.getOption("fzxw")) {
                            case "1":
                            case "2":
                            case "3":
                            case "4":
                                result = mFairy.findPic(6,70,328,717, "fzxw2.png");
                                if (result.sim > 0.8f) {
                                    switch (AtFairyConfig.getOption("fzxw")) {
                                        case "1":
                                            mFairy.onTap(127, 247, 220, 280, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "2":
                                            mFairy.onTap(148, 339, 207, 360, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "3":
                                            mFairy.onTap(136, 444, 196, 465, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "4":
                                            mFairy.onTap(124, 536, 175, 555, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                    }
                                } else {
                                    result = mFairy.findPic(101, 55, 216, 713, "fzxw4.png");
                                    mFairy.onTap(0.8f, result, "布匹", 500);
                                }
                                break;
                            case "5":
                            case "6":
                            case "7":
                            case "8":
                                result = mFairy.findPic(6,70,328,717, "fzxw3.png");
                                if (result.sim > 0.8f) {
                                    switch (AtFairyConfig.getOption("fzxw")) {
                                        case "5":
                                            mFairy.onTap(150, 310, 208, 337, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "6":
                                            mFairy.onTap(158, 399, 215, 425, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "7":
                                            mFairy.onTap(126, 501, 193, 524, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                        case "8":
                                            mFairy.onTap(123, 603, 183, 616, "找到", 500);
                                            judgeOneSecond = 1;
                                            break;
                                    }

                                } else {
                                    result = mFairy.findPic(101, 55, 216, 713, "fzxw5.png");
                                    mFairy.onTap(0.8f, result, "木胶", 500);
                                }
                                break;
                        }
                        return;
                    }

                    result = mFairy.findPic(442, 35, 573, 119, "jzgf6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(491, 413, 518, 427, "输入数量", 1000);

                        for (int i = 0; i < 5; i++) {
                            mFairy.onTap(629, 219, 638, 235, "", 200);
                        }
                        mFairy.onTap(554, 610, 597, 622, "", 300);
                        mFairy.onTap(554, 610, 597, 622, "开始生产", 300);
                        mFairy.onTap(23, 19, 47, 48, "", 500);
                        setTaskEnd();
                        return;
                    }
                    return;
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {
                        gamePublicFuntion.chat_start();
                    }
                }
            }
        };
    }//纺织小屋

    /**
     * 限时
     * @throws Exception
     */
    public void ljlx() throws Exception {
        new SingleTask_Content(mFairy, "龙卷来袭") {

            void create() throws Exception {
                super.create();
                activity_name = "ljlx.png";
                activity_type = 2;
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(500);
                gamePublicFuntion.click_ta(new String[]{"ljlx3.png", "ljlx4.png"});

                result = mFairy.findPic("ljlx5.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("ljlx7.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("ljlx1.png");
                    mFairy.onTap(0.8f, result, "准备", 500);
                }


                result = mFairy.findPic(561, 497, 695, 693, "ljlx6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "离开", 1500);
                    setTaskName(0);
                    return;
                }


                result = mFairy.findPic(39, 138, 157, 227, "ljlx2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.initMatTime();
                    int m = (int) (Math.random() * 4) + 3;
                    switch (m) {
                        case 3:
                            mFairy.ranSwipe(158, 574, 168, 436, 1000 * m, 500);
                            break;
                        case 4:
                            mFairy.ranSwipe(158, 574, 305, 580, 1000 * m, 500);
                            break;
                        case 5:
                            mFairy.ranSwipe(158, 574, 17, 579, 1000 * m, 500);
                            break;
                        case 6:
                            mFairy.ranSwipe(158, 574, 162, 689, 1000 * m, 500);
                            break;
                    }

                    mFairy.onTap(843, 652, 862, 666, "", 100);
                    mFairy.onTap(756, 646, 777, 673, "", 100);
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {
                        gamePublicFuntion.chat_start();
                    }
                }
            }
        };
    }//龙卷来袭

    public void xhkg() throws Exception {
        new SingleTask_Content(mFairy, "小虎快滚") {

            void create() throws Exception {
                super.create();
                activity_name = "xhkg.png";
                activity_type = 2;
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(500);
                gamePublicFuntion.click_ta(new String[]{"xhkg1.png", "xhkg2.png"});

                result = mFairy.findPic(535, 83, 786, 191, "pp1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("ljlx7.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("ljlx1.png");
                    mFairy.onTap(0.8f, result, "准备", 500);
                }


                result = mFairy.findPic(561, 497, 695, 693, "ljlx6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "离开", 1500);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(39, 138, 157, 227, "xhkg3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("滑动 >>>"));
                    mFairy.initMatTime();
                    mFairy.ranSwipe(155, 580, 159, 475, 5000, 500);
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                    }
                }
            }
        }

        ;
    }//小虎快滚

    public void gsdzz() throws Exception {
        new SingleTask_Content(mFairy, "钩索大作战") {

            void create() throws Exception {
                super.create();
                activity_name = "gsdzz.png";
                activity_type = 2;
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(500);
                gamePublicFuntion.click_ta(new String[]{"gsdzz1.png", "xhkg2.png"});

                result = mFairy.findPic(535, 83, 786, 191, "pp1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("gsdzz2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("gsdzz3.png");
                    mFairy.onTap(0.8f, result, "准备", 500);
                }

                result = mFairy.findPic(511, 423, 772, 690, "gsdzz4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "离开", 1500);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(39, 138, 157, 227, "gsdzz5.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.ranSwipe(211, 578, 360, 595, 2000, 500);
                    mFairy.onTap(1013, 641, 1036, 664, "", 300);
                    mFairy.onTap(1111, 549, 1145, 574, "", 300);
                    mFairy.ranSwipe(211, 578, 100, 600, 2000, 500);
                    mFairy.onTap(1013, 641, 1036, 664, "", 300);
                    mFairy.onTap(1111, 549, 1145, 574, "", 300);
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                    }
                }
            }
        }

        ;
    }//钩索大作战

    public void xxgj() throws Exception {
        new SingleTask_Content(mFairy, "蟹蟹归家") {

            void create() throws Exception {
                super.create();
                activity_name = "xxgj.png";
                activity_type = 2;
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(500);
                gamePublicFuntion.click_ta(new String[]{"xxgj1.png", "xhkg2.png"});

                result = mFairy.findPic(535, 83, 786, 191, "pp1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("ljlx7.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("ljlx1.png");
                    mFairy.onTap(0.8f, result, "准备", 500);
                }

                result = mFairy.findPic(484,592,785,703, "xxgj3.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "离开", 1500);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(923,61,1192,208, "xxgj2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.ranSwipe(157, 580, 300, 580, 2000, 1000);
                    mFairy.ranSwipe(157, 580, 50, 580, 2000, 1000);
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                    }
                }
            }
        };
    }//蟹蟹归家

    public void dld() throws Exception {
        new SingleTask_Content(mFairy, "大乱斗") {

            void cancel() throws Exception {
                FindResult cancel = gamePublicFuntion.cancel();
                if (cancel.sim > 0.8f) {

                    result = mFairy.findPic("dld2.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("dld3.png");
                        mFairy.onTap(0.8f, result, "准备", 500);
                        return;
                    }

                    if (frequencyMap("quxiao", 1)) {
                        mFairy.onTap(0.8f, cancel, "取消 | 拒绝", 500);
                    }
                }
            }

            void create() throws Exception {
                super.create();
                activity_name = "dld.png";
                activity_type = 2;
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(500);
                gamePublicFuntion.click_ta(new String[]{"dld1.png", "xhkg2.png"});

                result = mFairy.findPic(535, 83, 786, 191, "pp1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("dld2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("dld3.png");
                    mFairy.onTap(0.8f, result, "准备", 500);
                }


                result = mFairy.findPic(539, 489, 734, 568, "dld5.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "离开", 1500);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(35, 149, 146, 205, "dld4.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic(1056, 491, 1191, 633, "dld6.png");
                    if (result.sim > 0.8f) {
                        for (int i = 0; i < 3; i++) {
                            mFairy.onTap(1115, 554, 1142, 580, "A", 200);
                        }
                        mFairy.onTap(973, 405, 995, 431, "", 500);
                        mFairy.onTap(988, 511, 1016, 548, "", 500);
                    }
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                    }
                }
            }
        };
    }//大乱斗

    public void jdls() throws Exception {
        new SingleTask_Content(mFairy, "角斗联赛") {

            void cancel() throws Exception {
                FindResult cancel = gamePublicFuntion.cancel();
                if (cancel.sim > 0.8f) {

                    result = mFairy.findPic("dld2.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("dld3.png");
                        mFairy.onTap(0.8f, result, "准备", 500);
                        return;
                    }

                    if (frequencyMap("quxiao", 1)) {
                        mFairy.onTap(0.8f, cancel, "取消 | 拒绝", 500);
                    }
                }
            }

            void create() throws Exception {
                super.create();
                activity_name = "jdls.png";
                activity_type = 2;
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(500);
                gamePublicFuntion.click_ta(new String[]{"jdls1.png", "xhkg2.png"});

                result = mFairy.findPic(535, 83, 786, 191, "pp1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("jdls2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("jdls3.png");
                    mFairy.onTap(0.8f, result, "准备", 500);
                }

                result = mFairy.findPic(546, 605, 743, 680, "jdls5.png");
                mFairy.onTap(0.8f, result, "自动退出", 1500);

                result = mFairy.findPic(544, 556, 733, 678, "jdls6.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "继续", 1500);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(1027, 454, 1203, 521, "jdls7.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "复活", 1500);
                    err = 0;
                }

                result = mFairy.findPic(35, 149, 146, 205, "jdls4.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    gamePublicFuntion.auto_battle();
                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                    }
                }
            }
        };
    }//角斗联赛

    public void yqdj()throws Exception{
        new SingleTask_Content(mFairy, "御前对决") {

            void inOperation() throws Exception {
                super.inOperation();

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();

                if(minute>45){
                    LtLog.e(mFairy.getLineInfo("到达活动的结束时间,End!"));
                    setTaskEnd();
                    return;
                }
            }

            void cancel() throws Exception {
                FindResult cancel = gamePublicFuntion.cancel();
                if (cancel.sim > 0.8f) {

                    result = mFairy.findPic("yqdj6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(445,387,458,398,"",500);
                        mFairy.onTap(727,451,766,466,"",500);
                        return;
                    }

                    if (frequencyMap("quxiao", 1)) {
                        mFairy.onTap(0.8f, cancel, "取消 | 拒绝", 500);
                    }
                }
            }

            void create() throws Exception {
                super.create();
                activity_name = "yqdj.png";
                activity_type = 2;
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                gamePublicFuntion.click_ta(new String[]{"yqdj1.png", "xhkg2.png"});

                result = mFairy.findPic(561,423,724,611, "yqdj2.png");
                mFairy.onTap(0.8f, result, "御前对决", 1000);

                result = mFairy.findPic("yqdj3.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if(oneSecond()){
                        mFairy.onTap(707,394,728,419,"",500);
                        mFairy.onTap(698,535,733,565,"",500);
                    }

                    result = mFairy.findPic("yqdj4.png");
                    mFairy.onTap(0.8f, result, "自动匹配", 500);

                    result = mFairy.findPic("yqdj5.png");
                    mFairy.onTap(0.8f, result, "开始匹配", 500);

                }else{
                    oneSecond=0;
                }

                result = mFairy.findPic(1027, 454, 1203, 521, "jdls7.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "复活", 1500);
                    err = 0;
                }

                result = mFairy.findPic("yqdj9.png");
                if (result.sim > 0.8f) {
                    err=0;

                    result = mFairy.findPic("yqdj10.png");
                    mFairy.onTap(0.8f, result, "出战", 500);

                    gamePublicFuntion.close(1);
                    return;
                }

                result = mFairy.findPic(917,76,1208,156, "yqdj7.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("yqdj8.png");
                    if(result.sim>0.8f) {
                        mFairy.onTap(0.8f, result, "没有装备宠物", 500);
                        return;
                    }

                    gamePublicFuntion.auto_battle();
                }

                result = mFairy.findPic(620,471,870,700, "yqdj11.png");
                mFairy.onTap(0.8f,result,"确定",500);

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                    }
                }
            }
        };
    }//御前对决

    public void mftt() throws Exception {
        new SingleTask_Content(mFairy, "模仿天团") {

            void create() throws Exception {
                super.create();
                activity_name = "mftt.png";
                activity_type = 2;
            }

            void content_02() throws Exception {
                timeCount(10, 0);
                Thread.sleep(500);
                gamePublicFuntion.click_ta(new String[]{"mftt1.png", "ljlx4.png"});

                result = mFairy.findPic(535, 83, 786, 191, "pp1.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("mftt2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    result = mFairy.findPic("ljlx1.png");
                    mFairy.onTap(0.8f, result, "准备", 500);
                }


                result = mFairy.findPic(561, 497, 695, 693, "mftt4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "离开", 1500);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(46,155,152,207, "mftt3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    mFairy.initMatTime();
                    if(timeMap("mftt",5000,true)) {
                        mFairy.onTap(451, 632, 476, 662, "", 500);
                        mFairy.onTap(532,639,564,659, "", 500);
                        mFairy.onTap(626,636,645,658, "", 500);
                        mFairy.onTap(712,638,736,653, "", 500);
                        mFairy.onTap(801,640,818,660, "", 500);
                    }

                }

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {
                        gamePublicFuntion.chat_start();
                    }
                }
            }
        };
    }//模仿天团

    public void pfsd()throws Exception{
        new SingleTask_Content(mFairy, "破帆死斗") {

            void inOperation() throws Exception {
                super.inOperation();

                hour = mFairy.dateHour();
                minute = mFairy.dateMinute();

                if(minute>45){
                    LtLog.e(mFairy.getLineInfo("到达活动的结束时间,End!"));
                    setTaskEnd();
                    return;
                }
            }

            void cancel() throws Exception {
                FindResult cancel = gamePublicFuntion.cancel();
                if (cancel.sim > 0.8f) {

                    result = mFairy.findPic("yqdj6.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(445,387,458,398,"",500);
                        mFairy.onTap(727,451,766,466,"",500);
                        return;
                    }

                    if (frequencyMap("quxiao", 1)) {
                        mFairy.onTap(0.8f, cancel, "取消 | 拒绝", 500);
                    }
                }
            }

            void create() throws Exception {
                super.create();
                activity_name = "pfsd.png";
                activity_type = 2;
            }

            void content_02() throws Exception {
                timeCount(10, 0);

                gamePublicFuntion.click_ta(new String[]{"yqdj1.png", "xhkg2.png"});

                result = mFairy.findPic(561,423,724,611, "pfsd1.png");
                mFairy.onTap(0.8f, result, "破帆死斗", 1000);

                result = mFairy.findPic("pfsd2.png");
                if (result.sim > 0.8f) {
                    err = 0;

                    if(oneSecond()){
                        mFairy.onTap(707,394,728,419,"",500);
                        mFairy.onTap(698,535,733,565,"",500);
                    }

                    result = mFairy.findPic("yqdj4.png");
                    mFairy.onTap(0.8f, result, "自动匹配", 500);

                    result = mFairy.findPic("yqdj5.png");
                    mFairy.onTap(0.8f, result, "开始匹配", 500);

                }else{
                    oneSecond=0;
                }

                result = mFairy.findPic(1027, 454, 1203, 521, "jdls7.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "复活", 1500);
                    err = 0;
                }

                result = mFairy.findPic(900,137,1220,249, "pfsd3.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    for (int i = 0; i < 5; i++) {
                        mFairy.onTap(1115, 554, 1142, 580, "", 200);
                    }
                    mFairy.onTap(1011, 642, 1035, 666, "1 skill", 300);
                    mFairy.onTap(982, 518, 1009, 544, "2 skill", 300);
                    mFairy.onTap(1084, 414, 1114, 447, "3 skill", 300);
                    mFairy.onTap(1212, 440, 1236, 468, "4 skill", 300);
                }

                result = mFairy.findPic(620,471,870,700, "pfsd4.png");
                mFairy.onTap(0.8f,result,"确定",500);

                if (gamePublicFuntion.mainScene()) {
                    if (gamePublicFuntion.judgeStop(3, true)) {

                    }
                }
            }
        };
    }//破帆死斗


}




