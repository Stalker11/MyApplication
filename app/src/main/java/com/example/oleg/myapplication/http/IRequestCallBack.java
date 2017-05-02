package com.example.oleg.myapplication.http;

public interface IRequestCallBack {
    void onSucsess(String response);
    void onError(String error);
}
