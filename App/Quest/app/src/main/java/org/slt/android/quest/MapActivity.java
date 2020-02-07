package org.slt.android.quest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MapActivity extends AppCompatActivity {
    public static final int MYACITIVITY = Const.ACTIVITYMAP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    public void onProfileMenuButtonClicked(View v) {
        if (MYACITIVITY != Const.ACTIVITYPROFILE) {
            finish();
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        }
    }

    public void onMapMenuButtonClicked(View v) {
        if (MYACITIVITY != Const.ACTIVITYMAP) {
            finish();
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(intent);
        }
    }

    public void onChatMenuButtonClicked(View v) {
        if (MYACITIVITY != Const.ACTIVITYCHAT) {
            finish();
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            startActivity(intent);
        }
    }
}
