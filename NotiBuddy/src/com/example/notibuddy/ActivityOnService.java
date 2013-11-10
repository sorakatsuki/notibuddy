package com.example.notibuddy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.notibuddy.R;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

public class ActivityOnService extends IntentService
{
    public ActivityOnService() {
        super("ActivityOnService");
    }

    @Override
    protected void onHandleIntent(Intent intent) 
    {    
        Thread thread = new Thread(new Runnable(){
    		@Override
    	    public void run() {
    	        try {
    	        	// Create a new HttpClient and Post Header
    	            HttpClient httpclient = new DefaultHttpClient();
    	            HttpPost httppost = new HttpPost("http://ec2-54-219-159-251.us-west-1.compute.amazonaws.com/notibuddyServer/index.php/devActive");
    	            
    	            try {
    	                // Add your data
    	                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
    	                nameValuePairs.add(new BasicNameValuePair("deviceId", "13"));
    	                nameValuePairs.add(new BasicNameValuePair("userId", "9"));
    	                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    	                
    	                // Execute HTTP Post Request
    	                HttpResponse response =httpclient.execute(httppost);
    	                int httpStatusCode = response.getStatusLine().getStatusCode();
    	    	         System.out.println("Code is " + httpStatusCode);
    	                
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
    	notifyActivityStatus();
    }
    
	private void notifyActivityStatus()
	{		
		Intent intent = new Intent(this, DeviceSort.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
		
		Notification n  = new Notification.Builder(this)
        .setContentTitle("Activity Update")
        .setContentText("This device is currently the active one.")
        .setSmallIcon(R.drawable.col)
        .setContentIntent(pIntent)
        .setAutoCancel(true).build();
		
		NotificationManager notificationManager = 
				  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		for(int i=0; i<6; i++)
		{
			notificationManager.notify(0, n);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}