package com.example.oleg.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
// example https://www.youtube.com/watch?v=YBuNtNL7euA
public class MainActivity extends AppCompatActivity implements MainContractor.View{
    @BindView(R.id.my_edit)
    EditText enterUrl;
    @BindView(R.id.enter_text)
    Button responseButton;
    @BindView(R.id.response_google)
    TextView text;
    private Unbinder unbinder;
    private MainContractor.Presenter presentor;
private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        presentor = new MainPresentor(this);
    }
    @OnClick(R.id.enter_text)
    public void request(){
       presentor.onClickButton(enterUrl.getText().toString());
        Log.d(TAG, "request: ");
    }

    @Override
    public void updateText(String textForUpdate) {
        text.setText(textForUpdate);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
