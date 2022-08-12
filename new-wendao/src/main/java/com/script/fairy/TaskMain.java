package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.json.JSONObject;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;

/**
 * Created by Administrator on 2019/1/24 0024.
 */

public class TaskMain {
     AtFairyImpl mFairy;
     Util util;
     TeamTask teamTask;
     SingleTask singleTask;
    FindResult result;
    LimitlessTask limitlessTask;
     public  TaskMain (AtFairyImpl ypFairy) throws Exception {
         mFairy = ypFairy;
         mFairy.setGameName("问道");
         mFairy.setGameVersion(100);
         mFairy.initmMap(30);
         mFairy.initMatTime();
         init();
         util= new Util(mFairy);
         teamTask=new TeamTask(mFairy);
         singleTask=new SingleTask(mFairy);
         limitlessTask=new LimitlessTask(mFairy);
         mFairy.initMatTime();
    }
     public void main() throws Exception {
   /*      Thread.sleep(10000);
         Mat mat3 = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1);
         //将图片存入路径
         //Mat转byte[]
         Imgcodecs.imwrite("/sdcard/screen.png", mat3);
         FunctionClass  functionClass = new FunctionClass(mFairy, mFairy.getContext());
         String token1 =functionClass.getQiNiuToken();
          answer.sendTongTask(token1,"1");*/

         switch (task_id) {
             case 1446:
                 singleTask.novice();
                 break;
             case 1034:
                     util.teamRanks("退队");
                     util.close();
                     singleTask.singleDaily();
                     util.skillSet();
                 break;
             case 1462:
                 if (AtFairyConfig.getOption("zdrw").equals("1")) {
                     util.backCity();
                 }
                     teamTask.sdSetUp();
                     limitlessTask.sd();
                 break;
             case 1450:
                  util.skillSet();
                 break;
             case 1452:
                 if (AtFairyConfig.getOption("kqsb").equals("1")) {
                     util.receiveDouble();
                 }else {
                     util.closeDouble();
                 }
                 if (AtFairyConfig.getOption("zdrw").equals("1")) {
                     util.backCity();
                 }
                 if (AtFairyConfig.getOption("cb").equals("1")) {
                     teamTask.eliminate();
                 }

                 if (AtFairyConfig.getOption("xx").equals("1")) {
                     teamTask.practice();
                 }

                 if (AtFairyConfig.getOption("tggk").equals("1")) {
                      teamTask.tggk();
                 }
                 break;
             case 1448:
                 util.addLittleSet();
                 break;
             case 1486:
                 util.teamRanks("暂离");
                 util.backCity();
                 util.signIn();
                 util.teamRanks("回归");
                 util.close();
                 break;
             case 1488:
                 singleTask.ceshi();
                 break;
             case 1454:
                limitlessTask.xunluo();
                 break;
         }
         mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
         Thread.sleep(2000);
    }

    private int  task_id;
    public void  init() throws Exception {
        task_id=0;
        try {
            JSONObject   optionJson = new JSONObject(AtFairyConfig.getUserTaskConfig());
            LtLog.e(mFairy.getLineInfo("选项列表" + optionJson));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!AtFairyConfig.getOption("task_id").equals("")) {
            task_id = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }
    }

}
