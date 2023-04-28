
package com.script.fairy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.auto.scriptsdk.ui.ATSdk;
import com.example.scriptsdkproxy.LocalFairyService;
import com.script.opencvapi.AtFairyService;
import com.script.opencvapi.LtLog;
import com.umeng.commonsdk.UMConfigure;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LtLog.e("onCreate >>>>");

        super.onCreate(savedInstanceState);


        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);

        //友盟预初始化

        UMConfigure.preInit(this,"63a00fa0ba6a5259c4d30dd8","Umeng");
        UMConfigure.init(this,"63a00fa0ba6a5259c4d30dd8","Umeng",UMConfigure.DEVICE_TYPE_PHONE,"");


        setContentView(R.layout.activity_main);

        AtFairyService.startService(this, LocalFairyService.class);
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