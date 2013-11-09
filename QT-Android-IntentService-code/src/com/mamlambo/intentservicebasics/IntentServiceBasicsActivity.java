package com.mamlambo.intentservicebasics;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class IntentServiceBasicsActivity extends Activity {
    /** Called when the activity is first created. */
    
    private ResponseReceiver receiver;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);
    }
    
    @Override
    public void onDestroy() {
        this.unregisterReceiver(receiver);
        super.onDestroy();
    }

    public void myServiceButtonClickHandler(View target) {
        // Launch an intent service to do some async work
        Intent msgIntent = new Intent(this, SimpleIntentService.class);
        startService(msgIntent);
        Intent notifIntent = new Intent(this, DetectNotifications.class);
        Log.v("Activity", "About to start service");
        startService(notifIntent);
    }

    public class ResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "com.mamlambo.intent.action.MESSAGE_PROCESSED";
        @Override
        public void onReceive(Context context, Intent intent) {
           
            // Update UI, new "message" processed by SimpleIntentService
           TextView result = (TextView) findViewById(R.id.txt_result);
           String text = intent.getStringExtra(SimpleIntentService.PARAM_OUT_MSG);
           result.setText(text);
        }
        
    }
    
}