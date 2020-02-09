package org.slt.android.quest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 9001;

    public static final String ID = "test";
    public static final String PW = "test";

    private SignInButton mSignInButton;
    private GoogleApiClient mGoogleApiClient;

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Toast.makeText(this, "Google Sign in account.", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "firebaseAuthWithGooogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            //startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                Toast.makeText(this, "LoginSuccess.", Toast.LENGTH_SHORT).show();
                // Google Sign-In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign-In failed
                Toast.makeText(this, "LoginFailed.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public boolean checkLogin(View view){
        // ID 및 Password를 Log로 확인하기
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        String id = mEmailView.getText().toString();
        String pw = mPasswordView.getText().toString();
        Log.i("Id", id + " " + ID);
        Log.i("password", pw + " " + PW);

        if ((id.equals(ID) && pw.equals(PW)) || id.equals("t")) {
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

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();

        //Assign fields
        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);

        //Set click listeners
        mSignInButton.setOnClickListener(this);

        Toast.makeText(this, "Login page.", Toast.LENGTH_SHORT).show();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

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

    //백버튼 컨트롤

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAffinity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}