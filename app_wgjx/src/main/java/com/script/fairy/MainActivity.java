
package com.script.fairy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.auto.scriptsdk.ui.ATSdk;
import com.example.scriptsdkproxy.LocalFairyService;
import com.script.opencvapi.AtFairyService;
import com.script.opencvapi.LtLog;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LtLog.e("onCreate >>>>");

        super.onCreate(savedInstanceState);
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