package com.example.notibuddy;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.view.Menu;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import android.os.Bundle;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {

	//element for new-comer
	ImageView logo;
	Button newcome;
	ImageView splash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar bar = getActionBar();
		newcome = (Button)findViewById(R.id.newcome);
		splash = (ImageView)findViewById(R.drawable.col);
		bar.hide();
		
		
		
		
		//splash.setScaleType(ScaleType.CENTER_CROP);
		//SystemClock.sleep(3000);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void onDestroy() {
		super.onDestroy();
	}
	
	public void changeToSignUP(View view)	{
		Intent intent = new Intent( MainActivity.this, SignChoice.class );
		//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		startActivity( intent );
	}

}
