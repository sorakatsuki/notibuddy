package com.example.notibuddy;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import android.os.Build.*;

public class DeviceURL extends AsyncTask<String, Void, Void> {
	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	
	private ProgressDialog mProgressDialog;
	//device : controller name 
	public String url = "http://ec2-54-219-159-251.us-west-1.compute.amazonaws.com/notibuddyServer/index.php/device";

	String manufacturer = Build.MANUFACTURER;
	String modelString = Build.MODEL;
	
	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
				Log.v("TAG", "do in background");
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost( url ); 
				Log.i("INFO:", "device :" + url);
				
				Log.v("TAG", "do in background");
				Log.v( "TAG", Build.MODEL );
				Log.v( "TAG", Build.DEVICE );
				
				String modelNum = Build.MODEL;
				String buildNum = Build.DISPLAY;
				
				
				try{
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				       /*
						nameValuePairs.add(new BasicNameValuePair( "userEmail", "user mail" ));
				        nameValuePairs.add(new BasicNameValuePair( "userPass", "password" ));
				        */
				        //deviceName, userId, deviceType
					   
				        nameValuePairs.add(new BasicNameValuePair( "deviceName", modelNum ));
					    nameValuePairs.add(new BasicNameValuePair( "userId", Integer.toString(1) ));
					    nameValuePairs.add(new BasicNameValuePair( "deviceType", buildNum ));  
					    
					    //String payload = "deviceName=devicea&userId=123231&deviceType=deviceb";
					    Log.v( "TAG", "pre-http" );
					    //httpPost.setEntity(new UrlEncodedFormEntity( nameValuePairs ));
					    httpPost.setEntity(new UrlEncodedFormEntity( nameValuePairs ));
					    Log.v( "TAG", "pre-execute" );
					    HttpResponse response = httpclient.execute(httpPost);
					    int httpResponseCode = response.getStatusLine().getStatusCode();
					
					Log.v( "TAG", "after execute" );
					Log.v( "TAG", Integer.toString(httpResponseCode) );
					
				}catch(Exception e){
				
					Log.v( "TAG", "error occur" + e.toString() );
				//	Log.v( ÒTAGÓ, ÒError in http connection Ò + e.toString() );

				}

				

		return null;
	}
	
}
