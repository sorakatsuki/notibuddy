package com.example.notibuddy;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class DeviceGet extends AsyncTask<String, Void, Void> {

	String url = "http://ec2-54-219-159-251.us-west-1.compute.amazonaws.com/notibuddyServer/index.php/getDevs/";
	
	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
		HttpClient httpclient = new DefaultHttpClient();		
		
		url = url + "1";
		Log.d( "DeviceGet", url );
		HttpGet httpGet = new HttpGet( url );
		
		BasicResponseHandler responseHandler = new BasicResponseHandler();
		String content;
		
		try {
			 
			content = httpclient.execute(httpGet, responseHandler);
			 
			} catch (ClientProtocolException e) {
			 
			e.printStackTrace();
			 
			content = null;
			 
			} catch (IOException e) {	
			 
			e.printStackTrace();
			 
			content = null;
			 
			}
		return null;
	}
	
}
