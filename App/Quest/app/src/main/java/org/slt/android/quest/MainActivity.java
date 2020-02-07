package org.slt.android.quest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int MYACITIVITY = Const.ACTIVITYMAIN;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case START_LOADING:
                if(true){
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivityForResult(intent, START_LOGIN);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Error Loading login page", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void onProfileMenuButtonClicked(View v) {
        if (MYACITIVITY != Const.ACTIVITYPROFILE) {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        }
    }

    public void onMainMenuButtonClicked(View v) {
        if (MYACITIVITY != Const.ACTIVITYMAIN) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public void onChatMenuButtonClicked(View v) {
        if (MYACITIVITY != Const.ACTIVITYCHAT) {
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            startActivity(intent);
        }
    }
}