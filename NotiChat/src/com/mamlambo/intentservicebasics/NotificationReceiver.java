package com.mamlambo.intentservicebasics;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class NotificationReceiver extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i("Receiver", "NotificationReceiver");
    }
}