package com.atoken.study.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.atoken.study.R;

public class MvpActivity extends AppCompatActivity implements Contract.View {

    private TextView tvText;
    private Contract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        tvText = findViewById(R.id.tvText);

        mPresenter=new Lesson_Presenter(this);

        findViewById(R.id.btn_mvp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loadDate();
            }
        });
    }

    @Override
    public void update(String text) {
        tvText.setText(text);
    }
}
