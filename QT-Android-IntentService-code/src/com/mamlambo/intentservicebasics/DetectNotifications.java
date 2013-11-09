package com.mamlambo.intentservicebasics;

import com.mamlambo.intentservicebasics.IntentServiceBasicsActivity.ResponseReceiver;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;
import android.util.Log;

public class DetectNotifications extends AccessibilityService
{	
	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
	    if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
	 //   	Toast.makeText(getApplicationContext(),
	   // 			"test",
	    //			Toast.LENGTH_LONG).show();
	        Log.i("Detect Notifications","notification: " + event.getText());
	        try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        // Delete notification
	  //      Notification notification = (Notification) event.getParcelableData();
	        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
	        notificationManager.cancelAll();
	    }
	}

	@Override
	protected void onServiceConnected() {
		AccessibilityServiceInfo info = new AccessibilityServiceInfo();
	    info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
	    info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
	    info.notificationTimeout = 100;
	    setServiceInfo(info);
	}

	@Override
	public void onInterrupt() {
		System.out.println("onInterrupt");
	}
}