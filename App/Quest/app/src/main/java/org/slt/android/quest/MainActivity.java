package org.slt.android.quest;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final int MYACITIVITY = Const.ACTIVITYMAIN;

    public static final int RESULT_OK = 100;

    public static final int START_LOADING = 101;
    public static final int START_LOGIN = 103;
    public static final int START_MAP = 105;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //We do not use layout at mainActivity
        //setContentView(R.layout.activity_main);
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
            case START_LOGIN:
                if(true){
                    Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                    startActivityForResult(intent, START_MAP);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Error Loading login page", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}