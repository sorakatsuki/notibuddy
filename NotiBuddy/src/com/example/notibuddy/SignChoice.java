package com.example.notibuddy;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SignChoice extends Activity {
	
	ActionBar bar;
	Drawable logo;
	
	Button btn1;
    
    Button btn2;
    String email;
    String password;
    
    EditText emailTxt;
	EditText passwordTxt;
    
    byte[] en_password;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signchoice);
        
        bar = getActionBar();
		bar.setDisplayShowTitleEnabled(false);
		bar.setBackgroundDrawable( new ColorDrawable(Color.parseColor("#FFD700")) );
		
        
        Resources res = getResources();
        Drawable logo = res.getDrawable(R.drawable.logo_b);
        
        bar.setLogo( logo );
        
        btn1 = (Button)findViewById(R.id.signInBtn);
        btn1.setOnClickListener(new OnClickListener()	{
        	public void onClick(View view) {
        		GrabURL grab = new GrabURL( email, en_password );
        		grab.execute( "http://ec2-54-219-159-251.us-west-1.compute.amazonaws.com/notibuddyServer/index.php/userAdd" );
        	}
        });
        
        
        btn2 = (Button)findViewById(R.id.signUpp);
        btn2.setOnClickListener(new OnClickListener()	{
        	public void onClick(View view) {
        		changeToSignUPp(view);
        	}
        });

    
        
        
	}
	
	void changeToSignUPp(View view)	{
		Intent intent = new Intent( this, SignUp.class );
		//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		startActivity( intent );
	}
}
