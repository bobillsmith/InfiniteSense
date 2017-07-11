package com.infinitesense.bobill.infinitesense;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.infinitesense.bobill.infinitesense.CommonUtils.StringsUtils;

public class WelcomeActivity extends AppCompatActivity {
    private int delay = 700;
    private final int GO_HOME = 1000;
    private final int GO_GUIDE = 1001;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GO_HOME:
                    go_home();//jump to HomePage
                    break;
                case GO_GUIDE:
                    go_guide();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        SharedPreferences spf = getSharedPreferences(StringsUtils.SharepreferenceName,MODE_PRIVATE);
        boolean firstIn = spf.getBoolean(StringsUtils.IsFirstIn,true);
        //firstIn = true; //test
        if(!firstIn){
            handler.sendEmptyMessageDelayed(GO_HOME,delay);
        }else {
            firstIn = false;
            handler.sendEmptyMessageDelayed(GO_GUIDE,delay);
            spf.edit().putBoolean(StringsUtils.IsFirstIn,firstIn).commit();
        }
    }
    private void go_home(){
        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void go_guide(){
        Intent intent = new Intent(WelcomeActivity.this,VPagerActivity.class);
        startActivity(intent);
        finish();
    }
}
