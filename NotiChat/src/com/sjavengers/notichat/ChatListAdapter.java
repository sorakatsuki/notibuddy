package com.sjavengers.notichat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.*;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.sjavengers.notichat.R;

/**
 * This class is an example of how to use FirebaseListAdapter. It uses the <code>Chat</code> class to encapsulate the
 * data for each individual chat message
 */
public class ChatListAdapter extends FirebaseListAdapter<Chat> {

    // The username for this client. We use this to indicate which messages originated from this user
    private String username;
    
    // The activity
    private Activity activity;

    public ChatListAdapter(Query ref, Activity activity, int layout, String username) {
        super(ref, Chat.class, layout, activity);
        this.username = username;
        this.activity = activity;
    }

    /**
     * Bind an instance of the <code>Chat</code> class to our view. This method is called by <code>FirebaseListAdapter</code>
     * when there is a data change, and we are given an instance of a View that corresponds to the layout that we passed
     * to the constructor, as well as a single <code>Chat</code> instance that represents the current data to bind.
     * @param view A view instance corresponding to the layout we passed to the constructor.
     * @param chat An instance representing the current state of a chat message
     */
    @Override
    protected void populateView(View view, Chat chat) {
        // Map a Chat object to an entry in our listview
        String author = chat.getAuthor();
        TextView authorText = (TextView)view.findViewById(R.id.author);
        authorText.setText(author + ": ");
        // If the message was sent by this user, color it differently
        if (author.equals(username)) {
            authorText.setTextColor(Color.RED);
        } else {
            authorText.setTextColor(Color.BLUE);
   //         notify(chat.getMessage());
        }
        ((TextView)view.findViewById(R.id.message)).setText(chat.getMessage());
    }
    
    public void notify(String input)
	{		
		Intent intent = new Intent(this.activity, NotificationReceiver.class);
		PendingIntent pIntent = PendingIntent.getActivity(this.activity, 0, intent, 0);
		
		Notification n  = new Notification.Builder(this.activity)
        .setContentTitle("New Message")
        .setContentText(input)
        .setSmallIcon(R.drawable.firebase_logo)
        .setContentIntent(pIntent)
        .setAutoCancel(true)
        .addAction(R.drawable.firebase_logo, "Reply", pIntent).build();
		
		NotificationManager notificationManager = 
				  (NotificationManager) this.activity.getSystemService("notification");

		notificationManager.notify(0, n); 
    }
}
