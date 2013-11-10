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

public class GrabURL extends AsyncTask<String, Void, Void> {

	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	
	private ProgressDialog mProgressDialog;
	//device : controller name 
	public String urlUser = "http://ec2-54-219-159-251.us-west-1.compute.amazonaws.com/notibuddyServer/index.php/userAdd";

	String manufacturer = Build.MANUFACTURER;
	String modelString = Build.MODEL;
	String email;
	byte[] password;
	
	GrabURL(String emailP, byte[] passwordP) {
		// TODO Auto-generated constructor stub
		email = emailP;
		password = passwordP; 
	}

	protected void onPreExecute() {

		/*
		mProgressDialog = ProgressDialog.show(activity_signup.this, 
                "Loading...", "Data is Loading...");
		*/
		
		
		//nameValuePairs.add(new BasicNameValuePair(“value”,value));
		Log.v("TAG", "preexecute");
	}

	/*protected void doInBackground(String urls) {
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost( urls ); 
		Log.v("URLS:", urls);
		
		Log.v("TAG", "do in background");
	
		try{
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		       // nameValuePairs.add(new BasicNameValuePair( "email", email ));
		       // nameValuePairs.add(new BasicNameValuePair( "password", password.toString() ));
		        //deviceName, userId, deviceType
			    nameValuePairs.add(new BasicNameValuePair( "deviceName", "devicea" ));
			    nameValuePairs.add(new BasicNameValuePair( "userId", Integer.toString(5) ));
			    nameValuePairs.add(new BasicNameValuePair( "deviceType", "deviceb" ));     
		        
			    httpPost.setEntity(new UrlEncodedFormEntity( nameValuePairs ));
		
			
			
			httpclient.execute(httpPost);
			
			Log.v( "TAG", "after execute" );
			
		}catch(Exception e){
		
			Log.v( "TAG", "error occur" + e.toString() );
		//	Log.v( “TAG”, “Error in http connection “ + e.toString() );

		}

			

	}*/

	protected void onPostExecute(Void unused) {
	
		//mProgressDialog.dismiss();
		
		//Toast.makeText(getApplicationContext(), “Value updated”, Toast.LENGTH_SHORT).show();
		Log.v("TAG", "postexecute");

	
	}

	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
		Log.v("TAG", "do in background");
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost( urlUser ); 
		Log.i("INFO:", "grab " + urlUser);
		
		Log.v("TAG", "do in background");
		
		Log.v("TAG", "gradurl email is " + email);
		Log.v("TAG", "do in background");
		
	
		try{
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		       
				nameValuePairs.add(new BasicNameValuePair( "email", email ));
		        nameValuePairs.add(new BasicNameValuePair( "password",  password.toString() ));
		        
		        //deviceName, userId, deviceType
			   
		       
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
		//	Log.v( “TAG”, “Error in http connection “ + e.toString() );

		}

		return null;
	}

}