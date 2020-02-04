package org.slt.android.quest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class LoadingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO: 현재 이거 잘 작동 안하는 것 같음. 나중에 확인하자.
        Intent intent = getIntent();
        this.setResult(RESULT_OK,intent);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ImageView image = (ImageView)findViewById(R.id.logoImageView);
        image.setImageResource(R.drawable.loading);
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