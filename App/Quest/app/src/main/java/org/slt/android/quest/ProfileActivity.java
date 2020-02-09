package org.slt.android.quest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

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

        // 거리 seekbar 및 거리 텍스트 선언
        final TextView distanceTextView = (TextView) findViewById(R.id.myDistanceTextView);
        SeekBar distanceSeekBar = (SeekBar) findViewById(R.id.distanceSeekBar);

        // 거리 seekbar 세부 설정
        distanceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int stepSize = 10;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //seekbar 이동을 stepSize인 10단위로 제한하기
                progress = ((int)Math.round(progress/stepSize)) * stepSize;
                seekBar.setProgress(progress);
                distanceTextView.setText(progress + " km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // 금액 seekbar 및 금액 텍스트 선언
        final TextView amountTextView = (TextView) findViewById(R.id.myAmountTextView);
        SeekBar amountSeekbar = (SeekBar) findViewById(R.id.amountSeekBar);

        amountSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int stepSize = 1000;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //seekbar 이동을 stepSize인 1000단위로 제한하기
                progress = ((int) Math.round(progress/stepSize)) * stepSize;
                seekBar.setProgress(progress);
                //숫자에 쉼표 자동으로 넣게(금액으로 설정)
                amountTextView.setText(NumberFormat.getInstance(Locale.getDefault()).format(progress) + " ￦");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent);
    }
}
