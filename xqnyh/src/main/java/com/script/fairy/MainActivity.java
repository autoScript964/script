package com.script.fairy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.auto.scriptsdk.ui.ATSdk;
import com.example.scriptsdkproxy.LocalFairyService;
import com.script.opencvapi.AtFairyService;
import com.script.opencvapi.LtLog;
import com.umeng.commonsdk.UMConfigure;


public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        LtLog.e("onCreate >>>>");

        super.onCreate(savedInstanceState);




        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);

        //友盟预初始化

        UMConfigure.preInit(this,"639fd3d688ccdf4b7ea8484f","Umeng");
        UMConfigure.init(this,"639fd3d688ccdf4b7ea8484f","Umeng",UMConfigure.DEVICE_TYPE_PHONE,"");



        setContentView(R.layout.activity_main);
        AtFairyService.startService(this, LocalFairyService.class);
    }



    protected void onResume() {
        super.onResume();
        boolean flag= ATSdk.getInstance().init(this);
        if(flag){
            finish();
        }
    }

}
