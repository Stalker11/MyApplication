package com.example.oleg.myapplication;

import android.os.Handler;
import android.util.Log;

import com.example.oleg.myapplication.http.HttpRequest;
import com.example.oleg.myapplication.http.IRequestCallBack;

public class MainPresentor implements MainContractor.Presenter{
    private final MainContractor.View view;
    private static final String TAG = MainPresentor.class.getSimpleName();
    private Handler hand;

    public MainPresentor(MainContractor.View view) {
        this.view = view;
        hand = new Handler();
    }

    @Override
    public void onClickButton(String url) {
          new HttpRequest(url, new IRequestCallBack() {
              @Override
              public void onSucsess(final String response) {
                  hand.post(new Runnable() {
                      @Override
                      public void run() {
                          view.updateText(response);
                      }
                  });
                  Log.d(TAG, "onSucsess: "+response);
              }

              @Override
              public void onError(final String error) {
                  hand.post(new Runnable() {
                      @Override
                      public void run() {
                          view.updateText(error);
                      }
                  });
                  Log.d(TAG, "onError: "+error);
              }
          });
    }
}
