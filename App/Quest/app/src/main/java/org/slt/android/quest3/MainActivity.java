package org.slt.android.quest3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static final int RESULT_OK = 100;

    public static final int START_LOADING = 101;
    public static final int START_LOGIN = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivityForResult(intent,START_LOADING);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode){
            case START_LOADING:
                if(resultCode == RESULT_OK){
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivityForResult(intent,START_LOGIN);
                }
                break;
        }
    }
}
