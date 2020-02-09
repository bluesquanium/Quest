package org.slt.android.quest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ChatroomActivity extends AppCompatActivity {
    public static final int MYACITIVITY = Const.ACTIVITYCHATROOM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
    }

    public void onBackButtonClicked(View v) {
        finish();
    }
}
