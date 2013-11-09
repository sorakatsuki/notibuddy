package com.mamlambo.intentservicebasics;

import com.mamlambo.intentservicebasics.IntentServiceBasicsActivity.ResponseReceiver;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
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
	    System.out.println("onAccessibilityEvent");
	    if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
	    	Toast.makeText(getApplicationContext(),
	    			"test",
	    			Toast.LENGTH_LONG).show();
	        Log.i("Detect Notifications","notification: " + event.getText());
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