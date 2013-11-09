package com.sjavengers.notibuddy;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
 
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
 
public class ConnectionService extends Service {
 
    private ConnectionTask task;
 
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
 
    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("ConnectionChecker", "Connection Checker created");
    }
 
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("ConnectionChecker", "Connection Checker started");
        task = new ConnectionTask();
        task.execute();
        notifyConnectionStatus();
        return super.onStartCommand(intent, flags, startId);
    }
 
    @Override
    public void onDestroy() {
        task.cancel(true);
        Log.v("ConnectionChecker", "Stopping ConnectionChecker");
        super.onDestroy();
    }
 
    private void notifyConnectionStatus() {
        Log.v("ConnectionChecker", "Getting task results.");
        while (true) {
            try {
                Boolean result = task.get(1000, TimeUnit.MILLISECONDS);
                Context context = getApplicationContext();
                PendingIntent contentIntent = PendingIntent.getService(context,
                        0, null, 0);
                Notification notification = new Notification(R.drawable.ic_launcher,
                        "Connection Status", System.currentTimeMillis());
                if (result) {
                    notification.setLatestEventInfo(context,
                            "Connection Service",
                            "Intertech connection available!", contentIntent);
                } else {
                    notification
                            .setLatestEventInfo(context, "Connection Service",
                                    "Intertech connection not available",
                                    contentIntent);
                }
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1, notification);
                Log.v("ConnectionChecker", "Task results displayed.");
                break;
            } catch (TimeoutException e) {
                Log.v("ConnectionChecker", "Task work not completed.");
            } catch (InterruptedException e) {
                Log.v("ConnectionChecker", "Task work interrupted.");
                break;
            } catch (ExecutionException e) {
                Log.v("ConnectionChecker", "Task work failed.");
                break;
            }
        }
        task.cancel(true);
    }
}