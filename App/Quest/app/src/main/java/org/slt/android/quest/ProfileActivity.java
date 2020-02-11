package org.slt.android.quest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    public static final int MYACITIVITY = Const.ACTIVITYPROFILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //프로필 이미지 선언
        ImageView profileImage = (ImageView) findViewById(R.id.profileImageView);

        //세팅 아이콘 이미지 선언
        ImageView settingImage = (ImageView) findViewById(R.id.settingImageView);

        //하단 메뉴바 이미지 변경
        ImageView profileButtonImage = (ImageView) findViewById(R.id.profileMenuButton);
        profileButtonImage.setImageResource(R.drawable.profile_full_button);


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

    //백버튼 컨트롤

    public void profilesetting(View v){
        Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent);
    }
}
