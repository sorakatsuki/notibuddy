package com.sjavengers.notichat;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;
import com.sjavengers.notichat.NotificationReceiver;
import com.sjavengers.notichat.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class MainActivity extends ListActivity {

    // TODO: change this to your own Firebase URL
    private static final String FIREBASE_URL = "https://notichat.firebaseIO.com";

    private String username;
    private Firebase ref;
    private ValueEventListener connectedListener;
    private ChatListAdapter chatListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Make sure we have a username
        setupUsername();

        setTitle("Chatting as " + username);

        // Setup our Firebase ref
        ref = new Firebase(FIREBASE_URL).child("chat");

        // Setup our input methods. Enter key on the keyboard or pushing the send button
        EditText inputText = (EditText)findViewById(R.id.messageInput);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    sendMessage();
                }
                return true;
            }
        });

        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
        final ListView listView = getListView();
        // Tell our list adapter that we only want 50 messages at a time
        chatListAdapter = new ChatListAdapter(ref.limit(50), this, R.layout.chat_message, username);
        listView.setAdapter(chatListAdapter);
        chatListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatListAdapter.getCount() - 1);
            }
        });


        // Launch the intent service to listen for ready-notifications from the server
        Intent notifIntent = new Intent(this, GetNotificationsService.class);
        startService(notifIntent);
        
        // Finally, a little indication of connection status
        connectedListener = ref.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean)dataSnapshot.getValue();
                if (connected) {
                    Toast.makeText(MainActivity.this, "Connected to YouMessage", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Disconnected from YouMessage", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled() {
                // No-op
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        ref.getRoot().child(".info/connected").removeEventListener(connectedListener);
        chatListAdapter.cleanup();
    }

    private void setupUsername() {
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        username = prefs.getString("username", null);
        if (username == null) {
            //Random r = new Random();
            // Assign a random user name if we don't have one saved.
            username = "InterPol";
            //+ r.nextInt(100000);
            prefs.edit().putString("username", username).commit();
        }
    }

    private void sendMessage() {
        EditText inputText = (EditText)findViewById(R.id.messageInput);
        String input = inputText.getText().toString();
        if (!input.equals("")) {
            // Create our 'model', a Chat object
            Chat chat = new Chat(input, username);
            // Create a new, auto-generated child of that chat location, and save our chat data there
            ref.push().setValue(chat);            
            inputText.setText("");
            
            // Send HTTP POST request to server
            postData(username, input);
        }
    }
    
    private void postData(String username, String message)
    {
    	final String myMessage = message;
    	Thread thread = new Thread(new Runnable(){
    		@Override
    	    public void run() {
    	        try {
    	        	// Create a new HttpClient and Post Header
    	            HttpClient httpclient = new DefaultHttpClient();
    	            HttpPost httppost = new HttpPost("http://ec2-54-219-159-251.us-west-1.compute.amazonaws.com/notibuddyServer/index.php/notifAdd");
    	            
    	            try {
    	                // Add your data
    	                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
    	                nameValuePairs.add(new BasicNameValuePair("userId", "2"));
    	                nameValuePairs.add(new BasicNameValuePair("message", myMessage));
    	                nameValuePairs.add(new BasicNameValuePair("serviceId", "1"));
    	                nameValuePairs.add(new BasicNameValuePair("link", ""));
    	                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

    	                // Execute HTTP Post Request
    	                HttpResponse response =httpclient.execute(httppost);
    	         //       int httpStatusCode = response.getStatusLine().getStatusCode();
    	         //       System.out.println("Code is " + httpStatusCode);
    	                
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
}