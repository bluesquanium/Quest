package org.slt.android.quest3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class LoadingActivity extends Activity {
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        this.setResult(RESULT_OK,intent);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        logo = (ImageView)findViewById(R.id.logo);
        logo.bringToFront();
        startLoading();
    }
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }
}