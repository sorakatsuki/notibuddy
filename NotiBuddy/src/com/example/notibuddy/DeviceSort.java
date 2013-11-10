package com.example.notibuddy;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;


import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;



public class DeviceSort extends Activity {
	ActionBar bar;

	public String url = "http://ec2-54-219-159-251.us-west-1.compute.amazonaws.com/notibuddyServer/index.php/deviceAdd";

	Button activate;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_devicesort);
		
		Log.v("TAG", "devicesort");
		
		bar = getActionBar();
		
		
		bar.setDisplayShowTitleEnabled(false);
		bar.setBackgroundDrawable( new ColorDrawable(Color.parseColor("#FFD700")) );
		
		
		Resources res = getResources();
        Drawable logo = res.getDrawable(R.drawable.logo_b);
        bar.setLogo( logo );
        bar.setIcon(R.drawable.ic_action_about);
        
		activate = (Button)findViewById(R.id.activate);
		activate.setOnClickListener(new OnClickListener()	{
        	public void onClick(View view) {
        		//changeToSignUPp(view);
        		//handler
        		//ActivityOnService service = new ActivityOnService();
        		
        		callService();
        		
        		
        		
        		
        	}
        });
		
		DeviceGet deviceGet = new DeviceGet();
		deviceGet.execute( url );
		
		/*
		GrabURL grab = new GrabURL();
		grab.execute( url );
		*/
		
		
		//TODO : make and show list of devices
	}
	
	public void callService() {
		Intent inte = new Intent( this, ActivityOnService.class );
		startService( inte );
	}
	
	

	
}
