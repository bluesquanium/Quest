package org.slt.android.quest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void onOkButtonClicked(View v) {
        Toast.makeText(this, "OK!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onCancelButtonClicked(View v) {
        Toast.makeText(this, "Cancel Button Clicked.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
