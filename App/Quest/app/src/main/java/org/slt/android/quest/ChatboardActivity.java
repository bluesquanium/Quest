package org.slt.android.quest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ChatboardActivity extends AppCompatActivity {
    public static final int MYACITIVITY = Const.ACTIVITYCHATBOARD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatboard);

        //하단 메뉴바 이미지 변경
        ImageView chatButtonImage = (ImageView) findViewById(R.id.chatboardMenuButton);
        chatButtonImage.setImageResource(R.drawable.chat_full_button);
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

    public void onChatboardMenuButtonClicked(View v) {
        if (MYACITIVITY != Const.ACTIVITYCHATBOARD) {
            finish();
            Intent intent = new Intent(getApplicationContext(), ChatboardActivity.class);
            startActivity(intent);
        }
    }

    public void onRoomButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), ChatroomActivity.class);
        startActivity(intent);
    }

    //백버튼 컨트롤

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent);
    }
}
