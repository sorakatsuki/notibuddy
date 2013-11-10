package com.example.notibuddy;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SignUp extends Activity {
	
	
	EditText edit;

	Button btn;

	String value;

	String url;
	//Connection conn;
	
	
	
	
	//element for sign up
	EditText email;
	EditText password;
	EditText password2;
	Button   signUp;
	
	//user information
	String emailStr;
	String passwordStr = "";
	String password2Str = "";
	byte[] encryptPwd;
	
	static byte[] key = "1428324560542678".getBytes();
	SecretKey secret;
	
	ActionBar bar;
		
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        
		signUp = (Button)findViewById(R.id.signUpBtn);
	    email = (EditText)findViewById(R.id.email);
	    password = (EditText)findViewById(R.id.password);
	    password2 = (EditText)findViewById(R.id.password2);
		
	    bar = getActionBar();
		
		
		bar.setDisplayShowTitleEnabled(false);
		bar.setBackgroundDrawable( new ColorDrawable(Color.parseColor("#FFD700")) );
		
        
        Resources res = getResources();
        Drawable logo = res.getDrawable(R.drawable.logo_b);
        bar.setLogo( logo );

	    
	    try	{
    		 secret = Encrypt.generateKey();
    	} catch(InvalidKeySpecException inv) {
    	
    	} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	    signUp.setOnClickListener(
	    		new View.OnClickListener()
	            {
	                public void onClick(View view)
	                {
	                    Log.v("EditText", email.getText().toString());
	                    emailStr = email.getText().toString();
	                    passwordStr = password.getText().toString();
	                    password2Str = password2.getText().toString();

	                    if( !passwordStr.equals(password2Str) ) {
	                    	//change it to toast
	                    	Log.v("EditText", "Password is not matched");
	                    	Toast.makeText(getApplicationContext(), 
	                                "Password is not matched", Toast.LENGTH_LONG).show();
	                    	return;
	                    }
	                    
	                    Log.v("EditText", "Password is matched");
	                    
	                    
	                    String decryptPwd = "";
	                    
	                    try {
							encryptPwd = Encrypt.encryptMsg(passwordStr, secret);
							decryptPwd = Encrypt.decryptMsg(encryptPwd, secret);
	                    } catch (InvalidKeyException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchPaddingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvalidParameterSpecException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalBlockSizeException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (BadPaddingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvalidAlgorithmParameterException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    
	                    /*
	                     * TODO : send sahan's DB,
	                     * emailStr, encryptPwd
	                     * and information about this device 
	                     * */  
	                    
	                    
	                    
	                    Log.v("TAG","second");
	                    
	                    //Sign up done.
	                    //add user info to table
	                    GrabURL grab = new GrabURL(emailStr, encryptPwd);
	            		grab.execute( "http://ec2-54-219-159-251.us-west-1.compute.amazonaws.com/notibuddyServer/index.php/userAdd" );
	                    
	                    //add this device to table
	                    DeviceURL deviceURL = new DeviceURL();
	            		deviceURL.execute( "http://ec2-54-219-159-251.us-west-1.compute.amazonaws.com/notibuddyServer/index.php/deviceAdd" );
	            		
	            		//change activity
	                    changeDeviceSort();
	                    
					} 
	                    
	                    
	                    
	                    
	           

	            });
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	protected void onDestroy() {
		super.onDestroy();
	}
	
	public void changeDeviceSort()	{
		//SendServer sendServer = new SendServer();
		//sendServer.send();
		//sendServer.sendHttp(emailStr, encryptPwd);
		
		Intent intent = new Intent( this, DeviceSort.class );
		//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		startActivity( intent );
	}
	
	
	
}
