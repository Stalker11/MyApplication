package com.example.oleg.myapplication;

public interface MainContractor {
    interface View {
        void updateText(String textForUpdate);
    }
    interface Presenter{
        void onClickButton(String url);
    }
}
