package com.script.fairy;

import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.AtFairyConfig;
import com.script.framework.AtFairyImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2019/3/20.
 */

public class Task {
    public GamePublicFuntion gamePublicFuntion;
    public AtFairyImpl mFairy;
    public static List<String> list;
    public static Map<String, List<Integer>> mMap = new HashMap<>();
    public static FindResult result;
    public static FindResult result1;
    public static int week = 0;
    public static int hour = 0;
    public static int minute = 0;
    public static int battleFailureCount = 0;
    public static int mainScene = 18;//主场景计次
    public static int ranks = 0;//队伍
    public static int err = 0;
    public static int unexecuted = 0;
    public static int lu = 0;
    public static int package_Task = 0;
    public static int pl = 1;
    public static long timePl = System.currentTimeMillis();
    public static int supplement = 0;//自动补充
    public static int setUp = 0;
    public static int fbErr = 4;
    public static int fbEnd = 0;
    public static int hong = 0;
    public static int lan = 0;
    public static long timeHong =System.currentTimeMillis()-90000;
    public static long timeLan =System.currentTimeMillis()-90000;

    public Task(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(mFairy);
    }

    public void unexecutedTask() throws Exception {

        /**
         *  任务开始,离开副本
         */
        gamePublicFuntion.initTime();

        result = mFairy.findPic(921, 68, 1090, 164, new String[]{"fb.png", "fb1.png"});
        if (result.sim > 0.8f) {
            ranks = 0;
            if (mainScene == 18) {
                mFairy.onTap(1051, 111, 1075, 134, "离开副本", 2000);
                mFairy.onTap(722, 469, 753, 490, "确定", 1000);
            } else {
                mainScene = 0;
            }
        }

        result = mFairy.findPic("tiao.png");
        mFairy.onTap(0.85f, result, "跳过", 1000);

        result = mFairy.findPic("jx.png");
        mFairy.onTap(0.85f, result, "继续", 1000);

        result = mFairy.findPic(34, 507, 387, 598, "chat.png");
        mFairy.onTap(0.7f, result, 1188, 670, 1231, 697, "聊天", 1000);

        result = mFairy.findPic("likaifb.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "离开副本", 1000);
            mainScene = 10;
        }
        result = mFairy.findPic("ok1.png");
        mFairy.onTap(0.8f, result, "确定", 1000);

        result = mFairy.findPic("nn27.png");
        mFairy.onTap(0.8f, result, 97, 64, 134, 93, "查看世界地图", 1000);

        result = mFairy.findPic("yuan.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, "原服", 2000);
            mFairy.onTap(725, 471, 756, 487, "确定", 1000);
        }

        /**
         *  close*/
        unexecuted++;
        if (unexecuted > 1) {
            gamePublicFuntion.close();
            unexecuted = 0;
        }
    }

    public void errInit() throws Exception {
        for (int i = 0; i < 3; i++) {
            

            result = mFairy.findPic("auto1.png");
            mFairy.onTap(0.96f, result, "关闭自动战斗", 1000);

            result = mFairy.findPic("tuoli.png");
            mFairy.onTap(0.8f, result, "脱离", 1000);
        }
        mFairy.onTap(1179, 59, 1210, 85, "点开地图,开始新的任务", 3500);
    }//回到主城

    public boolean mainSceneTask() throws Exception {
        /***
         *   返回主城
         */
        if (err > 9) {
            unexecutedTask();

            result = mFairy.findPic(921, 68, 1090, 164, new String[]{"fb.png", "fb1.png"});
            if (result.sim < 0.8f) {
                errInit();
            } else {
                err = 0;
            }
            return true;
        }

        /**
         *  自动补充
         */
        if (supplement == 0) {
            unexecutedTask();
            mFairy.onTap(50, 41, 74, 64, "点击头像", 3000);
            return true;
        }
        /**
         *  设置
         */
        if (setUp == 0) {
            unexecutedTask();

            for(int i=0;i<3;i++) {
                result = mFairy.findPic(1115, 481, 1274, 667, "setUp.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "设置", 3000);
                } else {
                    mFairy.onTap(1225, 262, 1248, 292, "缩放栏", 2000);
                }
                
            }
            return true;
        }

        if (pl == 0) {
            unexecutedTask();
            result = mFairy.findPic(1107, 228, 1212, 335, new String[]{"home.png", "home2.png"});
            mFairy.onTap(0.7f, result, "点击开始清理背包", 1000);
            return true;
        }

        result = mFairy.findPic(616, 22, 874, 77, "right1.png");
        mFairy.onTap(0.7f, result, "关闭右侧缩放栏", 2000);

        /***
         *  ranks组队点击 */
        if (ranks > 5) {
            unexecutedTask();
            result = mFairy.findPic(921, 68, 1090, 164, new String[]{"fb.png", "fb1.png"});
            if (result.sim > 0.8f) {
                ranks = 0;
            } else {
                for (int i = 0; i < 3; i++) {
                    mFairy.onTap(11, 273, 33, 300, "队伍", 500);
                }
            }
            return true;
        }

        /**
         * mainScene计数控制
         */
        result = mFairy.findPic(609, 510, 722, 573, "lu.png");
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("寻路中...."));
            lu = 0;
            fbErr = 0;
            result = mFairy.findPic("zhaoji.png");
            mFairy.onTap(0.8f, result, "召集", 1000);
            gamePublicFuntion.initTime();
            gamePublicFuntion.bossTimeInit();
        } else {
            lu++;
        }

        LtLog.e("复活次数计次,超过重新匹配 :" + battleFailureCount);
        if (battleFailureCount > 15) {
            LtLog.e(mFairy.getLineInfo("复活次数超过15次,重新匹配队伍"));
            ranks = 8;
            mainScene = 18;
            battleFailureCount = 0;
            return true;
        }
       /* result = mFairy.findPic("jiaotan.png");
        mFairy.onTap(0.8f, result, "交谈", 2000);
*/

       LtLog.e(mFairy.getLineInfo("mainTask..end"));
        return false;
    }

    public void ranksmbTools(String type, String img) throws Exception {
        for (int i = 0; i < 8; i++) {
            result = mFairy.findPic(286, 139, 536, 609, type);
            LtLog.e("ranksmbTools- type -- " + result.sim);
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "目标分类", 1500);
                i = 0;
            }

            result = mFairy.findPic(286, 139, 536, 609, img);
            LtLog.e("ranksmbTools-  img -- " + result.sim);
            if (result.sim > 0.85f) {
                mFairy.onTap(0.85f, result, "找到目标>>>", 1000);
                mFairy.onTap(996, 561, 1028, 585, "确定", 500);

                for (int j = 0; j < 5; j++) {
                    result = mFairy.findPic(608, 68, 853, 169, "err3.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("ranks5.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "退出队伍", 2000);
                            mFairy.onTap(725, 471, 749, 487, "确定", 1000);
                        }
                    }
                    
                }
                return;
            }

            if (i == 0) {
                LtLog.e(mFairy.getLineInfo("初始化匹配目标"));
                for (int j = 0; j < 3; j++) {
                    mFairy.ranSwipe(401, 198, 445, 508, 0, 300, (long)100);
                }
                mFairy.sleep(1500);
                
                continue;
            }

            if (i % 2 == 0 && i != 0) {
                mFairy.ranSwipe(401, 198, 445, 508, 2, 1000, (long)2500);
                continue;
            }
        }
    }//匹配目标

    public void answer() throws Exception {
        mMap.put("game_id", Arrays.asList(169));
        mMap.put("title_Range", Arrays.asList(531, 325, 567, 123));
        mMap.put("answer_Range", Arrays.asList(662, 527, 933, 526, 660, 601, 932, 599, 58, 38));
        mMap.put("answer_Range1", Arrays.asList(
                720, 495, 816, 586,
                1004, 507, 1084, 586,
                736, 581, 809, 651,
                1011, 583, 1082, 650));
        mMap.put("pic_range", Arrays.asList(694, 492, 1099, 665));

    }//智能答题

    /**
     * 场景
     */
    public void package_Task() throws Exception {
        result = mFairy.findPic("packages.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【背包场景 >>> package_Task】"));
            gamePublicFuntion.initTime();
            package_Task++;

            if(list.size()!=0){
                if(list.get(0).equals("wb")){
                    return;
                }
            }
            result = mFairy.findPic(679, 150, 791, 284, "pl.png");
            if (result.sim > 0.85f) {
                LtLog.e(mFairy.getLineInfo("break"));
                package_Task = 8;
            }

            if (package_Task > 7) {
                pl = 1;
                int num = 0;
                if (AtFairyConfig.getOption("qlbb").equals("1")) {
                    while (mFairy.condit()) {
                        num++;
                        if (num > 10) {
                            mFairy.onTap(992, 152, 1013, 174, "关闭", 1000);
                            break;
                        }
                        result = mFairy.findPic("bb1.png");
                        mFairy.onTap(0.8f, result, "分解", 1500);

                        result = mFairy.findPic("bb2.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(386, 571, 417, 590, "分解", 1000);
                            mFairy.onTap(626, 572, 663, 593, "分解", 1000);
                            mFairy.onTap(992, 152, 1013, 174, "关闭", 1000);
                            break;
                        }
                    }
                }
                mFairy.onTap(1209, 69, 1229, 97, "关闭", 1000);
                package_Task = 0;
                return;

            }
        } else {
            package_Task = 0;
            return;
        }

        if (pl != 0) {
            mFairy.onTap(1209, 69, 1229, 97, "关闭", 1000);
            package_Task = 0;
            return;
        }

        result = mFairy.findPic(736, 247, 1093, 616, "wb3.png");
        mFairy.onTap(0.8f, result, "使用", 1500);

        result = mFairy.findPic(713, 150, 962, 338, "wb4.png");
        mFairy.onTap(0.8f, result, "最大", 1000);

        result = mFairy.findPic(738, 419, 961, 550, "wb5.png");
        mFairy.onTap(0.8f, result, "确定", 1000);


        if (package_Task < 3) {
            mFairy.onTap(726, 123, 749, 147, "全部", 1000);
        }
        result = mFairy.findPic(692, 156, 1105, 593,
                new String[]{"pl1.png", "pl2.png", "pl3.png", "pl4.png", "pl5.png"});
        if (result.sim > 0.8f) {

            package_Task = 0;

            mFairy.onTap(0.8f, result, "点击道具", 1000);

            return;
        }

        if (package_Task % 2 == 0 && package_Task != 0) {
            mFairy.ranSwipe(958, 258, 1001, 495, 2, 1000, (long)2500);
        }

    }//

    public void overallSituation() throws Exception {
        result = mFairy.findPic("err6.png");
        mFairy.onTap(0.8f, result, 947, 212, 961, 229, "关闭可突破提示", 1000);

        result = mFairy.findPic(801, 215, 1057, 475, "use.png");
        mFairy.onTap(0.8f, result, "使用", 1000);

        result = mFairy.findPic(801, 215, 1057, 475, "equipment.png");
        mFairy.onTap(0.8f, result, "装备", 1000);

        result = mFairy.findPic("err7.png");
        mFairy.onTap(0.8f, result, 720, 473, 754, 486, "了解信誉分", 1000);

        result = mFairy.findPic("left.png");
        mFairy.onTap(0.8f, result, "左侧缩放栏", 1000);

        result = mFairy.findPic("jia.png");
        mFairy.onTap(0.8f, result, "下方缩放栏", 1000);

        result = mFairy.findPic("jieshou.png");
        mFairy.onTap(0.8f, result, "接受", 1000);

        result = mFairy.findPic("sheng.png");
        mFairy.onTap(0.8f, result, "省电模式", 1000);

        result = mFairy.findPic(533,164,778,265,"fbdj.png");
        if (result.sim > 0.8f) {
            if (AtFairyConfig.getOption("fbdj").equals("1")) {
                result = mFairy.findPic(406,373,543,486,"fbdj1.png");
                mFairy.onTap(0.8f, result, "【玩家勾选：消耗次数】", 1000);
            } else {
                result = mFairy.findPic(406,373,543,486,"fbdj2.png");
                mFairy.onTap(0.8f, result, "【玩家勾选：不消耗次数】", 1000);
            }
            mFairy.onTap(631, 492, 665, 508, "确定", 1000);
        } else {
            result = mFairy.findPic(418, 296, 873, 693, "ok2.png");
            mFairy.onTap(0.8f, result, "确定", 1000);
        }
        result = mFairy.findPic("ok3.png");
        mFairy.onTap(0.8f, result, "确定", 1000);

        result = mFairy.findPic(411, 259, 831, 592, "jia1.png");
        mFairy.onTap(0.8f, result, "领取", 1000);

        result = mFairy.findPic(459, 403, 824, 594, "err1.png");
        mFairy.onTap(0.8f, result, "我知道了", 1000);

        result = mFairy.findPic("wenxin.png");
        mFairy.onTap(0.8f, result, 624, 422, 656, 449, "温馨提示", 1000);

        result = mFairy.findPic("yunpadLogin.png");
        mFairy.onTap(0.8f, result, "启动", 1000);

        result = mFairy.findPic("loginQQ.png");
        mFairy.onTap(0.8f, result, "登录QQ", 1000);

        result = mFairy.findPic2(24, 549, 714, 1155, "checkvx3.png");
        mFairy.onTap(0.8f, result, "登录", 1000);

        result = mFairy.findPic2(21, 641, 704, 1119, "Sign in.png");
        mFairy.onTap(0.8f, result, "Sign in登录", 1000);

        result = mFairy.findPic(new String[]{"login.png", "login1.png"});
        mFairy.onTap(0.8f, result, "login游戏", 1000);
    }

    public void map() throws Exception {
        result1 = mFairy.findPic(47, 20, 207, 147, "map1.png");
        LtLog.e(mFairy.getLineInfo("地图sim :" + result1.sim));
        Task.task_err(0.78f,result1);
        if (result1.sim > 0.78f) {
            LtLog.e(mFairy.getLineInfo("【地图场景】"));
        } else {
            return;
        }
        if (err > 9) {
            mFairy.onTap(0.8f, result1, "世界地图", 1800);

            mFairy.onTap(712, 305, 734, 329, "祖龙城", 1500);

            result = mFairy.findPic("zlc.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, 725, 471, 754, 489, "确定传送", 5000);
            } else {
                mFairy.onTap(1223, 29, 1246, 49, "关闭地图", 1000);
            }
            err = 0;
        } else {
            gamePublicFuntion.close();
        }

    }//地图

    public void jujue() throws Exception {
        result1 = mFairy.findPic(399, 393, 658, 589, "jj.png");
        Task.task_err(0.7f,result1);
        LtLog.e(mFairy.getLineInfo("拒绝,sim :" + result1.sim));
        if (result1.sim > 0.7f) {
            LtLog.e(mFairy.getLineInfo("【拒绝场景】"));

            gamePublicFuntion.initTime();
        } else {
            return;
        }

        result = mFairy.findPic(431, 288, 860, 448, "bpls3.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(726, 503, 751, 523, "同意", 1000);
            return;
        }

        result = mFairy.findPic(384, 347, 650, 543, "jj1.png");
        mFairy.onTap(0.85f, result, result.x + 28, result.y + 28, result.x + 32, result.y + 32, "勾选弹框", 2000);

        result = mFairy.findPic(428, 308, 868, 466, "ranks9.png");
        if (result.sim > 0.8f) {
            mFairy.onTap(0.8f, result, 720, 469, 748, 487, "同意", 2000);

            result = mFairy.findPic("zhaoji.png");
            mFairy.onTap(0.8f, result, "召集", 1000);
            return;
        }
        mFairy.onTap(0.7f, result1, "拒绝", 1000);

    }//拒绝

    public void autos() throws Exception {
        result = mFairy.findPic("autos.png");
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【角色场景】"));

            gamePublicFuntion.initTime();

            if (supplement == 1) {
                gamePublicFuntion.close();
                return;
            }
        } else {
            return;
        }

        mFairy.onTap(1060, 183, 1070, 193, "点击加号", 1500);

        result = mFairy.findPic(708,66,883,158, "autos1.png");
        if (result.sim > 0.8f) {
            if (AtFairyConfig.getOption("autos").equals("1") && list.get(0).equals("ww")) {

                result = mFairy.findPic("autos4.png");
                mFairy.onTap(0.85f, result, "关闭自动补充血量", 1000);

                result = mFairy.findPic("autos5.png");
                mFairy.onTap(0.85f, result, "关闭自动补充真气", 1000);

            } else {
                result = mFairy.findPic("autos2.png");
                mFairy.onTap(0.85f, result, "自动补充血量", 1000);

                result = mFairy.findPic("autos3.png");
                mFairy.onTap(0.85f, result, "自动补充真气", 1000);

                mFairy.onTap(1091, 308, 1124, 336, "一键添加", 500);
                mFairy.onTap(1094, 614, 1127, 645, "一键添加", 500);
            }
            supplement = 1;
            mFairy.onTap(1112, 28, 1134, 45, "", 1000);
            mFairy.onTap(1208, 73, 1224, 91, "", 1000);
        }

    }

    public void setUp() throws Exception {
        result = mFairy.findPic(new String[]{"setScene.png", "setUp1.png"});
        Task.task_err(0.8f,result);
        if (result.sim > 0.8f) {
            LtLog.e(mFairy.getLineInfo("【设置场景】"));
            gamePublicFuntion.initTime();

            if (setUp > 1) {
                mFairy.onTap(1178, 70, 1199, 90, "", 1000);
                return;
            }
        } else {
            return;
        }

        /**
         *  基础设置
         */
        result = mFairy.findPic("setScene.png");
        if (result.sim > 0.8f) {
            for (int i = 1; i <= 3; i++) {
                result = mFairy.findPic("set" + i + ".png");
                mFairy.onTap(0.85f, result, "set", 1000);
            }
            mFairy.onTap(186, 222, 227, 254, "2.5D", 1000);

            mFairy.onTap(1140, 360, 1182, 402, "挂机设置", 1500);

            setUp++;
        }

        /**
         *  挂机设置
         */
        result = mFairy.findPic("setUp1.png");
        if (result.sim > 0.8f) {
            result = mFairy.findPic("set5.png");
            mFairy.onTap(0.8f, result, "关闭> 拒绝组队申请", 1000);

            result = mFairy.findPic("set4.png");
            mFairy.onTap(0.8f, result, "自动同意", 1000);

            mFairy.onTap(1140, 165, 1168, 195, "", 1000);

            setUp++;
        }
    }//设置

    public void autoUse() throws Exception {
        boolean bool = false;
        if (hong != 0 && (System.currentTimeMillis()-timeHong>90000)) {
            int num = mFairy.getColorNum(116, 41, 297, 44, "206,77,74", 0.95);

            LtLog.e("自动补药,红: " + num);

            switch (hong) {
                case 1:
                    if (num < 120) {
                        bool=true;
                    }
                    break;
                case 2:
                    if (num < 180){
                        bool=true;
                    }
                        break;
                case 3:
                    if (num < 230) {
                        bool=true;
                    }
                    break;
            }

            if(bool){
                result = mFairy.findPic(921, 68, 1090, 164, new String[]{"fb.png", "fb1.png"});
                mFairy.onTap(0.8f,result,838,681,854,695,"加红。",1000);
                timeHong=System.currentTimeMillis();
                bool=false;
            }

        }


        if (lan != 0 && (System.currentTimeMillis()-timeLan>90000)) {
            int num2 = mFairy.getColorNum(113, 50, 280, 54, "74,174,206", 0.95);
            LtLog.e("自动补药,蓝：" + num2);
            switch (lan) {
                case 1:
                    if (num2 < 150) {
                        bool=true;
                    }
                    break;
                case 2:
                    if (num2 < 200){
                        bool=true;
                    }
                        break;
                case 3:
                    if (num2 < 250) {
                        bool=true;
                    }
                    break;
            }
            if(bool){
                result = mFairy.findPic(921, 68, 1090, 164, new String[]{"fb.png", "fb1.png"});
                mFairy.onTap(0.8f,result,922,676,941,694,"加蓝。",1000);
                timeLan=System.currentTimeMillis();
            }
        }
    }//自动使用


    public static  int task_err = 0;

    public static void task_err(float sim,FindResult result){
        if(result.sim>sim){
            task_err = 0;
        }else{
            task_err++;
        }
    }
}
