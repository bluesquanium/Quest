package org.slt.android.quest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    public static final String ID = "test";
    public static final String PW = "test";

    // UI references.

    private EditText mEmailView;
    private EditText mPasswordView;


    public boolean checkLogin(View view){
        // ID 및 Password를 Log로 확인하기
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        String id = mEmailView.getText().toString();
        String pw = mPasswordView.getText().toString();
        Log.i("Id", id + " " + ID);
        Log.i("password", pw + " " + PW);

        if (id.equals(ID) && pw.equals(PW)) {
            return true;
        }
        return false;
    }

    public void onFindIdButtonClicked(View view){
        Toast.makeText(this, "아이디 까먹었냐 바보야", Toast.LENGTH_SHORT).show();
    }

    public void onFindPasswordButtonClicked(View view){
        Toast.makeText(this, "비밀번호도 까먹었냐 바보야", Toast.LENGTH_SHORT).show();
    }

    public void facebooklogin(View view){


    }

    public void googlelogin(View view){

    }

    public void naverlogin(View view){

    }

    public void kakaologin(View view){

    }

    public void onCreateAccountButtonClicked(View view){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivityForResult(intent,Const.START_CREATE_ACCOUNT);
    }

    public void onLoginButtonClicked(View view){
        if (checkLogin(view) == true) {
            finish();
        } else {
            Toast.makeText(this, "도지뇬 개띨빡", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Image restore

        ImageView facebooklogo = (ImageView) findViewById(R.id.facebookImageView);
        facebooklogo.setImageResource(R.drawable.facebook);

        ImageView googlelogo = (ImageView) findViewById(R.id.googleImageView);
        googlelogo.setImageResource(R.drawable.google);

        ImageView naverlogo = (ImageView) findViewById(R.id.naverImageView);
        naverlogo.setImageResource(R.drawable.naver);

        ImageView kakaologo = (ImageView) findViewById(R.id.kakaoImageView);
        kakaologo.setImageResource(R.drawable.kakao);

        ImageView logo = (ImageView) findViewById(R.id.logoImageView);
        logo.setImageResource(R.drawable.loading);

        // Set up the login form.

        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        // event handler

        mEmailView.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if(id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL){
                    return true;
                }
                return false;
            }

        });
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    //attemptLogin();

                    return true;
                }
                return false;
            }
        });

    }

}