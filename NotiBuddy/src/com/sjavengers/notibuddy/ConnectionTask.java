package com.sjavengers.notibuddy;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
 
import android.os.AsyncTask;
import android.util.Log;
 
public class ConnectionTask extends AsyncTask<Void, Void, Boolean> {
 
    private final String LIST_URL = "http://www.intertech.com";
 
    @Override
    protected Boolean doInBackground(Void... arg0) {
        Log.v("ConnectionTask", "Checkinig...");
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 4000);
        HttpConnectionParams.setSoTimeout(params, 7000);
        DefaultHttpClient client = new DefaultHttpClient(params);
        HttpGet get = new HttpGet(LIST_URL);
        HttpResponse res;
        try {
            res = client.execute(get);
        } catch (ClientProtocolException e) {
            Log.v("ConnectionTask", "Unsuccessfully hit Intertech site");
            return false;
        } catch (IOException e) {
            Log.v("ConnectionTask", "Unsuccessfully hit Intertech site");
            return false;
        }
        int code = res.getStatusLine().getStatusCode();
        if (code != 200) {
            Log.v("ConnectionTask", "Unsuccessfully hit Intertech site");
            return false;
        }
        Log.v("ConnectionTask", "Successfully hit Intertech site");
        return true;
    }
 
}