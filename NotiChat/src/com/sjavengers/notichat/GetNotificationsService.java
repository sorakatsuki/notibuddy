package com.sjavengers.notichat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

public class GetNotificationsService extends IntentService
{
	private static int notificationId = 0;
	
	public GetNotificationsService() 
	{
        super("GetNotificationsService");
    }
	
	@Override
    protected void onHandleIntent(Intent intent) 
    {    
        Thread thread = new Thread(new Runnable(){
    		@Override
    	    public void run() {
    	        try {
    	        	// Define variables
    	        	String deviceId = "1";
    	        	String userId = "2";
    	        	String methodUri = "http://ec2-54-219-159-251.us-west-1.compute.amazonaws.com/notibuddyServer/index.php/notifCheck";
    	        	
    	        	String fullUri = methodUri + "?deviceId=" + deviceId + "&userId=" + userId;
    	        	
    	        	// Create a new HttpClient and Post Header
    	            HttpClient httpclient = new DefaultHttpClient();
    	            HttpGet httpget = new HttpGet(fullUri);
    	            
    	            try {
    	                System.out.println("About to execute HTTP Post");
    	                // Execute HTTP Post Request
    	                HttpResponse response = httpclient.execute(httpget);
    	                
    	                displayNotification("Ken is awesome");
    	                displayNotification("test");
    	                
    	            } catch (ClientProtocolException e) {
    	                // TODO Auto-generated catch block
    	            } catch (IOException e) {
    	                // TODO Auto-generated catch block
    	            }
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    		}
        });
        thread.start();
    }
	
	private void displayNotification(String notification)
	{
		if(notification != null)
		{
			Intent intent = new Intent(this, NotificationReceiver.class);
			PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
			
			Notification n  = new Notification.Builder(this)
	        .setContentTitle("New IM")
	        .setContentText(notification)
	        .setSmallIcon(R.drawable.firebase_logo)
	        .setContentIntent(pIntent)
	        .setAutoCancel(true)
	        .addAction(R.drawable.firebase_logo, "Reply", pIntent).build();
			
			NotificationManager notificationManager = 
					  (NotificationManager) this.getSystemService("notification");

			notificationManager.notify(notificationId, n);
			notificationId++;
		}
	}
}
