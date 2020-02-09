package org.slt.android.quest;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    public static final int MYACITIVITY = Const.ACTIVITYMAIN;

    public static final int RESULT_OK = 100;

    public static final int START_LOADING = 101;
    public static final int START_LOGIN = 103;
    public static final int START_MAP = 105;

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

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
                //Initialize Firebase Auth
                mFirebaseAuth = FirebaseAuth.getInstance();
                mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser == null) {
                    //Not signed in, launch the Sign In activity
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivityForResult(intent, START_LOGIN);
                } else {
                    Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                    startActivityForResult(intent, START_MAP);
                }
                break;
            case START_LOGIN:
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivityForResult(intent, START_MAP);
                break;
        }
    }
}