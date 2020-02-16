package org.slt.android.quest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class ProfileActivity extends AppCompatActivity {
    public static final int MYACITIVITY = Const.ACTIVITYPROFILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //SharedPreferences를 통해 사용자 데이터 받기, 아래는 데이터 선언(introduction)

        SharedPreferences userintroduction = getSharedPreferences("introduction", MODE_PRIVATE);

        //소개글 introductionText 선언
        TextView introductionText = (TextView) findViewById(R.id.myIntroductionTextView);

        //SharePreferences를 통해 넣어놓은 데이터를 확인하여 출력하기(데이터 : introduction, 없으면 빈칸)

        introductionText.setText(userintroduction.getString("introduction", ""));

        //프로필 이미지 선언
        ImageView profileImage = (ImageView) findViewById(R.id.profileImageView);

        //SharedPreferences를 통해 사용자 데이터 받기, 아래는 데이터 선언(image)

        SharedPreferences userimage = getSharedPreferences("image", MODE_PRIVATE);
        if(userimage.getString("image","") == ""){
            Bitmap basebitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.profile_default);
            profileImage.setImageBitmap(basebitmap);
        } else{
            String bitmapString = userimage.getString("image", "");
            profileImage.setImageBitmap(StringToBitMap(bitmapString));
        }

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

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e){
            e.getMessage();
            return null;
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
