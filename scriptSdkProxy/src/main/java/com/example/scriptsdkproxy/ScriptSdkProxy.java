package com.example.scriptsdkproxy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.auto.scriptsdk.ui.ATSdk;
import com.script.content.CUtils;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.AtFairyService;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.utils.AtControl;

import org.json.JSONException;
import org.json.JSONObject;

public class ScriptSdkProxy {

    private static class F {
        @SuppressLint("StaticFieldLeak")
        private static final ScriptSdkProxy sInstance = new ScriptSdkProxy();
    }

    private static ScriptSdkProxy getInstance() {
        return F.sInstance;
    }

    public static void init(AtFairyImpl fairyImpl) {
        LtLog.e("ScriptSdkProxy init ---");
        getInstance()._init(fairyImpl);
    }

    private ScriptSdkProxy() {
        // do nothing
    }

    private AtFairyImpl fairyImpl;
    private void _init(AtFairyImpl fairyImpl) {
        assert fairyImpl != null;
        assert fairyImpl.getContext() != null;
        this.fairyImpl = fairyImpl;

        //fairyImpl.setIme();

        ATSdk.getInstance().init(fairyImpl.getContext());
        ATSdk.getInstance().setTaskChangeListener(this::switchTask);
        ATSdk.getInstance().setAPPEixtListener(this::onSdkExitListener);
        AtFairyConfig.setUseCustomOptionProxy(true);
        AtFairyConfig.setOptionProxy(this::onGetOption);
        fairyImpl.setCompatFairyProxy(this::finish);
        fairyImpl.addOnFiaryEvent(this::onEvent);
        LtLog.e("ScriptSdkProxy init end ---");
    }

    private void onSdkExitListener() {
        LtLog.d("ypf", "onSdkExitListener");
        if(fairyImpl != null){
            fairyImpl.requestExit();
        }
    }

    private volatile String currentTaskData;

    private String onGetOption(String key) {
        try {
            if (TextUtils.isEmpty(currentTaskData)) {
                if (!TextUtils.isEmpty(ATSdk.getInstance().getCurrentTask())) {
                    currentTaskData = ATSdk.getInstance().getCurrentTask();
                }
            }
            //LtLog.e("y__pf-99","taskGetOptions::key:: \"" + key + "\"");
            if (!TextUtils.isEmpty(currentTaskData)) {
                JSONObject jsonObject = new JSONObject(currentTaskData);
               /// LtLog.e("y__pf-99","taskGetOptions::value:: \"" + jsonObject.optString(key) + "\"" );
                return jsonObject.optString(key);
            }
        } catch (JSONException var4) {
            var4.printStackTrace();
        }

        return "";
    }

    private void finish(String s, int i) {
       //fairyImpl.restoreIme();

        LtLog.e("ypf-99", "proxy#finish（1）:: " + s);
        ATSdk.getInstance().onTaskComplete(s);
        LtLog.e("ypf-99", "proxy#finish（2）:: " + s);
        //stop();
        LtLog.e("ypf-99", "proxy#finish（3）:: " + s);
        try {
            CUtils.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void onEvent(Bundle bundle) {
        LtLog.e("ypf-99","123456");

        if (bundle == null) {
            return;
        }

        final String type = bundle.getString(AtFairyImpl.OnCompatFiaryEvent.KEY_TYPE);
        assert type != null;

        if (AtFairyImpl.OnCompatFiaryEvent.TYPE_NEW_TASK_START0.equals(type)) {
            currentTaskData = ATSdk.getInstance().getCurrentTask();
            if(!TextUtils.isEmpty(currentTaskData)){
                //TODO：临时方案。 进一步保证唤醒有可能正在wait状态的线程。
                // compatNotifyLooperAwake();
            }
            LtLog.e("ypf-99",currentTaskData);
//            start();
        }
    }

   /* private void compatNotifyLooperAwake(){
        if(fairyImpl != null){
            fairyImpl.notify();
        }
    }*/

    public void switchTask(){

        final String task = ATSdk.getInstance().getCurrentTask();
        LtLog.d("switchTask in ---111 :"+task);

        if(task == null || !task.equals(currentTaskData)){
            AtControl.getInstance().setEnableControl(true);

            LtLog.d("switchTask in ---222 :"+task);

            this.fairyImpl.switchTask(task);
        }
        /*LtLog.e("ypf-99", "switchTask");
        if(TextUtils.isEmpty(task)){
            LtLog.e("ypf-99", "switchTask-> current task is empty!");
            stop();
            return;
        }
        if(!task.equals(currentTaskData)){
            LtLog.e("ypf-99", "switchTask-> new task is coming!");
            stop();
            start();
            return;
        }
        if(TextUtils.isEmpty(currentTaskData)) {
            LtLog.e("ypf-99", "switchTask-> first time with new task is coming!");
            start();
        }*/
    }

    private void start() {
        assert fairyImpl != null;
        Intent intent = new Intent();
        intent.setAction(AtFairyService.ACTION_RESUME);
        fairyImpl.getContext().sendBroadcast(intent);
        LtLog.e("ypf-99", "start-");
    }

    private void stop() {
        assert fairyImpl != null;
        Intent intent = new Intent();
        intent.setAction(AtFairyService.ACTION_STOP);
        fairyImpl.getContext().sendBroadcast(intent);
        LtLog.e("ypf-99", "stop-");
    }

}
