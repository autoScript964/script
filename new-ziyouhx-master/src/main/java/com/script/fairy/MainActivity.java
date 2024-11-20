package com.script.fairy;

import android.os.Bundle;

import com.auto.scriptsdk.ui.ATSdk;
import com.example.scriptsdkproxy.LocalFairyService;
import com.script.opencvapi.AtFairyService;
import com.umeng.commonsdk.UMConfigure;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AtFairyService.startService(this, LocalFairyService.class);

        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);
        //友盟预初始化
        UMConfigure.preInit(this,"6390051b88ccdf4b7e9cfd75","Umeng");
        UMConfigure.init(this,"6390051b88ccdf4b7e9cfd75","Umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean flag= ATSdk.getInstance().init(this);
        if(flag){
            finish();
        }
    }




}
