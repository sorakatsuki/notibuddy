package com.example.notibuddy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
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

import android.util.Log;

public class SendServer {
	private static final String url = "jdbc:mysql://ec2-54-219-159-251.us-west-1.compute.amazonaws.com:3306/notibuddy";
	private static final String user = "notisvr";
	private static final String pass = "notibuddy"; 
	
	Connection con = null;
	
	//3306
	public String urlHttp = "http://ec2-54-219-159-251.us-west-1.compute.amazonaws.com/notibuddyServer/index.php/add_device";
	
	
	public void send() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from user");
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()){
				int id = rs.getInt( "userId" );
				
				Log.v( "TAG", id + " is id" );
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Log.v( "TAG", "class not found" );
			e.printStackTrace();
		} catch (SQLException e) {
			Log.v( "TAG", "SQL problem" );
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		try {
        	Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
			Log.i("ERROR:", "NO Driver!");
		    e.printStackTrace();
		    return;
        }
        
			
			Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);  

			

			ResultSet rs = statement.executeQuery("SELECT * FROM user"); 
			while ( rs.next() )  
			{  
				int id = rs.getInt( "userId" );
				String nm = rs.getString( "email" );
				String log = rs.getString( "password" );
				
				Log.v("TAG", id + " "+ nm + log );
			} 
			
			con.close();
			*/
	}
	
	private StringBuilder inputStreamToString(InputStream is) {
	     String line = "";
	     StringBuilder total = new StringBuilder();
	     // Wrap a BufferedReader around the InputStream
	     BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	     // Read response until the end
	     try {
	      while ((line = rd.readLine()) != null) { 
	        total.append(line); 
	      }
	     } catch (IOException e) {
	      e.printStackTrace();
	     }
	     // Return full string
	     return total;
	}
	
	public void sendHttp(String email, byte[] password)	{
		HttpClient httpclient = new DefaultHttpClient();
		//String url = "";
		
		// Prepare a request object
	    HttpPost httpPost = new HttpPost( urlHttp ); 
	    
	    try {
		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	       // nameValuePairs.add(new BasicNameValuePair( "email", email ));
	       // nameValuePairs.add(new BasicNameValuePair( "password", password.toString() ));
	        //deviceName, userId, deviceType
		    nameValuePairs.add(new BasicNameValuePair( "deviceName", email ));
		    nameValuePairs.add(new BasicNameValuePair( "userId", Integer.toString(5) ));
		    nameValuePairs.add(new BasicNameValuePair( "deviceType", "deviceeeeee" ));     
	        
		    httpPost.setEntity(new UrlEncodedFormEntity( nameValuePairs ));
		    
		 // Execute HTTP Post Request
            Log.w("SENCIDE", "Execute HTTP Post Request");
            HttpResponse response = httpclient.execute( httpPost );
             
            String str = inputStreamToString(response.getEntity().getContent()).toString();
            Log.w("SENCIDE", str);
             
            if(str.toString().equalsIgnoreCase("true"))
            {
	             Log.w("SENCIDE", "Login successful");
            }else
            {
	             Log.w("SENCIDE", str );            
            }
 
	    } catch(ClientProtocolException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	         e.printStackTrace();
	        }
	    
	    HttpResponse response1 = null;
	    // Execute the request
	    try {
	    	response1 = httpclient.execute( httpPost );
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.d( "TAG", "protocol" );
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d( "TAG", "IOexception" );
			e.printStackTrace();
		}
	    
	    HttpEntity entity1 = null;
	    
	    try {
	        Log.d( "TAG", response1.getStatusLine().toString() );
	        
	        entity1 = response1.getEntity();
	        // do something useful with the response body
	        // and ensure it is fully consumed
	        //EntityUtils.consume(entity1);
	    } finally {
	        //((Object) httpGet).releaseConnection();
	    }
	    
	    
	}
	
	public void grabURL( String url ){
		//new GrabURL().execute( url );
	}
}
