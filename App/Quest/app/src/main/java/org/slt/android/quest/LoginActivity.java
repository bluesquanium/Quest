package org.slt.android.quest;

import android.app.Activity;
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



    // UI references.

    private EditText mEmailView;
    private EditText mPasswordView;


    public void login(View view){
        Toast.makeText(this, "곽창근 개띨빡", Toast.LENGTH_SHORT).show();

        // ID 및 Password를 Log로 확인하기
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        Log.i("Id", mEmailView.getText().toString());
        Log.i("password", mPasswordView.getText().toString());
    }

    public void findid(View view){
        Toast.makeText(this, "아이디 까먹었냐 바보야", Toast.LENGTH_SHORT).show();

    }

    public void findpassword(View view){
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

    public void signup(View view){
        Toast.makeText(this, "오이이잉?", Toast.LENGTH_SHORT).show();
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