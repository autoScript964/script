package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import static com.script.opencvapi.AtFairy2.FAIRY_TYPE_TASK;

public class Abnormal {
    //异常处理
    //监控线程
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    CommonFunction commonFunction;
    MatTime matTime;
    FindResult findResult;
    String task_id;
    int js_1=0,js_2=0,js_3=0,js_4=0,js_5=0;
    int jjsq=0;

    public Abnormal(AtFairyImpl ypFairy)throws Exception{
        mFairy = ypFairy;
        commonFunction = new CommonFunction(mFairy);
        matTime=new MatTime(mFairy);


        task_id= AtFairyConfig.getOption("task_id");
        if (AtFairyConfig.getOption("jjsq").equals("1")){
            jjsq=1;
        }
    }

    public void erro() throws Exception{

        findResult = mFairy.findPic(999,493,1236,618,"huoyue.png");
        mFairy.onTap(0.7f,findResult,1083,571,1104,580,"活跃礼包",1000);

        findResult = mFairy.findPic("shao.png");
        mFairy.onTap(0.8f,findResult,"稍后再来",1000);

        result = mFairy.findPic(304,308,600,425,"kua.png");
        mFairy.onTap(0.8f,result,395,485,421,498,"确定跨服",1000);

        result = mFairy.findPic("hdclose1.png");
        mFairy.onTap(0.8f,result,1106,62,1113,75,"活动 - 关闭",1000);

        if (task_id.equals("403")){
            result = mFairy.findPic2(1013, 448, 1105, 534,commonFunction.setImg("qianwang1.png"));
            if (result.sim>0.8f){
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err前往"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
            }
            result = mFairy.findPic2(911, 550, 1069, 655,commonFunction.setImg("xuexi.png"));
            if (result.sim>0.8f){
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err门派功法引导一键学习"));
                commonFunction.RndCompare(0.8f, 807,598,result, commonFunction.getImg());
            }
            result = mFairy.findPic2(919, 479, 1087, 523,commonFunction.setImg("qwck.png"));
            if (result.sim>0.8f){
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err前往查看"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
            }
            result = mFairy.findPic2(996, 340, 1160, 404,commonFunction.setImg("jsxjn.png"));
            if (result.sim>0.8f){
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err解锁新技能"));
                commonFunction.RndCompare(0.8f, 1069, 497,result, commonFunction.getImg());
            }
            result = mFairy.findPic2(803, 389, 976, 585,commonFunction.setImg("mjmj.png"));
            if (result.sim>0.8f){
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "莫急莫急"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
            }
        }




        result = mFairy.findPic2(1012, 185, 1207, 483,commonFunction.setImg("Bzzzb.png"));
        if (result.sim>0.8f){
            js_4++;
            if (js_4>5){
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err在做准备"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
            }
        }else {
            js_4=0;
        }
        result = mFairy.findPic2(1012, 185, 1207, 483,commonFunction.setImg("duihua3.png"));
        if (result.sim>0.8f){
            js_5++;
            if (js_5>5){
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err对话栏"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
            }
        }else {
            js_5=0;
        }
        if (task_id.equals("1332")){
            result = mFairy.findPic2(commonFunction.setImg("zckq.png"));
            if (result.sim>0.8f){
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err再次开启任务链"));
                commonFunction.RndCompare(0.8f, 402,491, result, commonFunction.getImg());
            }
        }

        result = mFairy.findPic2(commonFunction.setImg("ggt.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err公告图"));
            commonFunction.RndCompare(0.8f, 1075,45, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(commonFunction.setImg("xxxyh.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err休息一会"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(commonFunction.setImg("shijianbugoule.png"));
        if (result.sim>0.8f){
            js_3++;
            if (js_3>5){
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err前往完成"));
                commonFunction.Compare(0.8f, result, commonFunction.getImg());
            }
        }else {
            js_3=0;
        }

        result1 = mFairy.findPic2(65, 273, 267, 613,commonFunction.setImg("qrdl.png"));
        if (result1.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result1, 0.8f, "err七日登录"));
            while (mFairy.condit()){
                result = mFairy.findPic2(225, 527, 1156, 641,commonFunction.setImg("dllq.png"));
                if (result.sim>0.95f) {
                    LtLog.e(commonFunction.getLineInfo(result, 0.95f, "err登录领取"));
                    commonFunction.RndCompare(0.95f, result.x + 10, result.y + 10, result, commonFunction.getImg());
                }else {
                    break;
                }
            }
            commonFunction.RndCompare(0.8f, 1152, 215, result1, commonFunction.getImg());
        }
        result = mFairy.findPic2(498, 170, 771, 326,commonFunction.setImg("baoman.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err++包裹满了"));
            mFairy.finish(FAIRY_TYPE_TASK, task_id, 2043);
            
        }
        result = mFairy.findPic2(498, 170, 771, 326,commonFunction.setImg("cwman.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err++宠物满了"));
            mFairy.finish(FAIRY_TYPE_TASK, task_id, 2043);
            
        }
        result = mFairy.findPicRange (commonFunction.setImg("kfljtc.png"),30);
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err跨服练级弹窗"));
            commonFunction.RndCompare(0.8f, 403, 489, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(commonFunction.setImg("hqmonkey.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err没钱了"));
            mFairy.finish(FAIRY_TYPE_TASK, task_id, 2045);
            
        }
        result = mFairy.findPic2(commonFunction.setImg("yebz.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err没钱了"));
            mFairy.finish(FAIRY_TYPE_TASK, task_id, 2045);
            
        }
        result = mFairy.findPic2(529,72,748,127,commonFunction.setImg("yin_tszz.png"));
        if (result.sim>0.8f){
            js_2++;
            if (js_2>5){
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err提升再战"));
                commonFunction.RndCompare(0.8f, 970, 104, result, commonFunction.getImg());
            }
        }else {
            js_2=0;
        }
        result = mFairy.findPic2(commonFunction.setImg("xlbx.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err++打开修炼宝箱"));
            commonFunction.RndCompare(0.8f, 887,488, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(312,283,965,453,commonFunction.setImg("tcdt.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err++放弃答题"));
            commonFunction.RndCompare(0.8f, 389,488, result, commonFunction.getImg());
        }


        result = mFairy.findPic2(341, 674, 1017, 716,commonFunction.setImg("duihua2.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err++对话栏"));
            commonFunction.RndCompare(0.8f, 620, 714, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(commonFunction.setImg("fhsm.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err返回师门"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(commonFunction.setImg("qxqm.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "取消驱魔"));
            commonFunction.RndCompare(0.8f, 884, 488, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(commonFunction.setImg("make.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "取消打造"));
            commonFunction.RndCompare(0.8f, 1113,36, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(commonFunction.setImg("book.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "取消购买打造"));
            commonFunction.RndCompare(0.8f, 882,486, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(commonFunction.setImg("errsjhbzb.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err升级伙伴装备"));
            commonFunction.RndCompare(0.8f, 879,483, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(918,321,1085,416,commonFunction.setImg("mfgm.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err免费改名"));
            commonFunction.RndCompare(0.8f, 1197,344, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(545,624,744,690, "sygj1.png");
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err手游管家"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(545,237,741,295, "Tdwfw.png");
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err定位服务"));
            commonFunction.RndCompare(0.8f, 881,483, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(552,222,745,309, "dzzh.png");
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err队长召唤"));
            commonFunction.RndCompare(0.8f, 403,491, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(953,594,1100,649, "qxzl.png");
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err取消暂离"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(635, 287, 804, 452, "luxiang.png");
        if (result.sim > 0.8f) {
            js_1++;
            if (js_1>5){
                LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err++录像"));
                commonFunction.RndCompare(0.8f, 849, 176, result, commonFunction.getImg());
                js_1=0;
            }
        }else {
            js_1=0;
        }
        result = mFairy.findPic2(405,304,867,424,commonFunction.setImg("xhhl.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err消耗活力"));
            commonFunction.RndCompare(0.8f, 400,486, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(309, 304, 597, 407,commonFunction.setImg("bzyfz.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err驱魔香不足1分钟"));
            commonFunction.RndCompare(0.8f, 392, 488, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(792,247,1240,452,commonFunction.setImg("guiover.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err捉鬼任务失败"));
            commonFunction.RndCompare(0.8f, 1247,62, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(451,286,999,521,commonFunction.setImg("Blqsb.png"));
        LtLog.e(commonFunction.getLineInfo(result, 0.8f, "领取双倍"));
        if (result.sim>0.8f &&   AtFairyConfig.getOption("ls").equals("1")){
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
        }else if (result.sim>0.8f &&  AtFairyConfig.getOption("ls").equals("")){
            commonFunction.RndCompare(0.8f, 1044,249, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(975,220,1247,480,commonFunction.setImg("amnz.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "爱莫能助"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(317,300,965,435,commonFunction.setImg("fqqyrw.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "+放弃奇遇任务"));
            commonFunction.RndCompare(0.8f, 882,483, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(commonFunction.setImg("dydjd.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "队员等级低"));
            commonFunction.RndCompare(0.8f, 396, 481, result, commonFunction.getImg());
        }


        result = mFairy.findPic2(1112, 101, 1183, 229,commonFunction.setImg("dqrw.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err关闭当前任务"));
            commonFunction.RndCompare(0.8f, 1120, 47, result, commonFunction.getImg());
        }



        result = mFairy.findPic2(525, 227, 765, 297,commonFunction.setImg("change2.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err更换修炼取消"));
            commonFunction.RndCompare(0.8f, 890, 493, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(commonFunction.setImg("xllxm.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err修炼满"));
            commonFunction.RndCompare(0.8f, 875,486, result, commonFunction.getImg());
        }
        result = commonFunction.FindManyPic(298, 156, 526, 242, new String[]{"hdyz.png", "hdyz1.png"}, 0);
        //  result = mFairy.findPic2(298, 156, 526, 242,commonFunction.setImg("hdyz.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err发现滑块"));
            //commonFunction.HaoIhuadong();
            pyHuaDong();
        }


        result = mFairy.findPic2(commonFunction.setImg("unlock.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err滑动解锁"));
            commonFunction.RanSwipe(460, 345, 864, 379, 1, 1300);
        }
        result = mFairy.findPic2(commonFunction.setImg("lingqu.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err领取"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(311,290,967,441,commonFunction.setImg("yin_fbxx.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err发布信息"));
            commonFunction.RndCompare(0.8f, 397, 488, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(454,216,736,340,commonFunction.setImg("bs.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err饱食"));
            commonFunction.RndCompare(0.8f, 818,378, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(commonFunction.setImg("baoshi.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err饱食确定"));
            commonFunction.RndCompare(0.8f, 636,485, result, commonFunction.getImg());
        }


        result = mFairy.findPic2(1154,61,1275,103,commonFunction.setImg("zbbad.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err修装备"));
            commonFunction.RndCompare(0.8f, result.x +2, result.y + 2, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(379,101,487,559,commonFunction.setImg("zbms.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err修装备界面"));
            commonFunction.RndCompare(0.8f, result.x + 464, result.y + 16, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(563,292,1026,433,commonFunction.setImg("xlzb.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err修装备"));
            commonFunction.RndCompare(0.8f, 768,392, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(430,113,849,331,commonFunction.setImg("yxfx.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err游戏分享"));
            commonFunction.RndCompare(0.8f, 946,209, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(430,113,849,331,commonFunction.setImg("dialogue.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err关闭对话框"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(430,113,849,331,commonFunction.setImg("pwsyq.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err排位赛邀请"));
            commonFunction.RndCompare(0.8f, 880,495, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(commonFunction.setImg("renjie.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err情谊录关闭"));
            commonFunction.RndCompare(0.8f, 1120, 47, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(commonFunction.setImg("zzl.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err关闭转转乐"));
            commonFunction.RndCompare(0.8f, 1078, 57, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(416,494,471,542,commonFunction.setImg("dhclose.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err缩小世界对话"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(335, 304, 922, 429,commonFunction.setImg("dzwcz.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err队长未操作，点击取消"));
            commonFunction.RndCompare(0.8f, 880, 480, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(commonFunction.setImg("ljcs.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err连接超时"));
            commonFunction.RndCompare(0.8f, 880, 480, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(commonFunction.setImg("zhidao.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err 我知道了"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(970, 274, 1257, 453,commonFunction.setImg("nzstyhl.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err那真是太遗憾了"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(570, 216, 740, 323,commonFunction.setImg("fqrw.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err放弃任务"));
            commonFunction.RndCompare(0.8f, 889, 483, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(543,222,737,299,commonFunction.setImg("ghdz.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err更换队长拒绝"));
            commonFunction.RndCompare(0.8f, 885,484, result, commonFunction.getImg());
        }

        result = mFairy.findPic2(805, 350, 956, 651,commonFunction.setImg("gmyp.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err购买药品"));
            commonFunction.Compare(0.8f, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(commonFunction.setImg("yaoqingjd.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err取消邀请入队"));
            commonFunction.RndCompare(0.8f, 867,480, result, commonFunction.getImg());
        }
        result = mFairy.findPic2(commonFunction.setImg("errkeju.png"));
        if (result.sim > 0.8f) {
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "err科举取消"));
            commonFunction.RndCompare(0.8f,874,487, result, commonFunction.getImg());
        }
    }
    //py滑动解锁
    public void pyHuaDong() throws Exception {
        result = commonFunction.FindManyPic(298, 156, 526, 242, new String[]{"hdyz.png", "hdyz1.png"}, 0);
        //   result = mFairy.findPic2(298, 156, 526, 242,commonFunction.setImg("hdyz.png"));
        if (result.sim>0.8f){
            LtLog.e(commonFunction.getLineInfo(result, 0.8f, "开始好爱解锁"));
            commonFunction. HaoIhuadong();
        }

    }

}