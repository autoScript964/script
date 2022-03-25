package com.script.fairy;

import com.script.framework.AtFairyApp;
import com.umeng.commonsdk.UMConfigure;

public class YpApplication extends AtFairyApp {

    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(getApplicationContext(),UMConfigure.DEVICE_TYPE_PHONE,"611345aebc78af7b6753dfc6");
    }
}
