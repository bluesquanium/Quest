package org.slt.android.quest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.NumberFormat;
import java.util.Locale;

public class EditProfileActivity extends AppCompatActivity {
    //프로필 이미지 선언
    ImageView profileImage;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profileImage = (ImageView) findViewById(R.id.profileImageView);

        //Topbar 이미지들 선언
        ImageView changeImage = (ImageView) findViewById(R.id.changePictureImageView);
        ImageView backImage = (ImageView) findViewById(R.id.backBarImageView);


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

    public void chagepicture(View view){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            PERMISSION_CODE);
                }

            } else {
                //TODO 버젼이 M보다 낮은 경우 바로 여기로 넘어가는지 확인해야함
                pickImageFromGallery();
            }
        } else {
            pickImageFromGallery();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                profileImage.setImageURI(result.getUri());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void backtoprofile(View view){
        finish();
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
    }


    //백버튼 컨트롤

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
    }
}
