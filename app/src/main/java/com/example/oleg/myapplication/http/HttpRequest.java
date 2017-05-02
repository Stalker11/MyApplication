package com.example.oleg.myapplication.http;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;

public class HttpRequest {
    private String myUrl;
    private IRequestCallBack callBack;
    private HttpURLConnection connection;
    private static final String TAG = HttpRequest.class.getSimpleName();
    public HttpRequest(String url, IRequestCallBack callBack) {
        this.myUrl = url;
        this.callBack = callBack;
        request();
    }
    private void request(){
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(myUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    callBack.onSucsess(connection.getContentType().toString());
                    Log.d(TAG, "run: "+connection.getResponseMessage());
                    
                } catch (MalformedURLException e) {
                    callBack.onError(e.getMessage());
                    Log.d(TAG, "run:1 ");
                    e.printStackTrace();
                } catch (IOException e){
                    callBack.onError(e.getMessage());
                    Log.d(TAG, "run:2 ");
                }finally {
                    try{
                        connection.disconnect();
                    }catch (Exception e){
                        Log.d(TAG, "run:3 ");
                    }
                }
            }
        });
    }
}
