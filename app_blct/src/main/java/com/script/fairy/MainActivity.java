package com.script.fairy;

import android.app.Activity;
import android.os.Bundle;

import com.auto.scriptsdk.ui.ATSdk;
import com.example.scriptsdkproxy.LocalFairyService;
import com.script.opencvapi.AtFairyService;
import com.script.opencvapi.LtLog;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LtLog.e("onCreate >>>>");

        super.onCreate(savedInstanceState);

      /*  //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);

        //友盟预初始化
        UMConfigure.preInit(this,"63a0232c88ccdf4b7ea8abe1","Umeng");
        UMConfigure.init(this,"63a0232c88ccdf4b7ea8abe1","Umeng", UMConfigure.DEVICE_TYPE_PHONE,"");*/

        setContentView(R.layout.activity_main);

        AtFairyService.startService(this, LocalFairyService.class);

        //finish();
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
