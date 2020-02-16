package org.slt.android.quest;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
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

import java.io.ByteArrayOutputStream;
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

        //SharedPreferences를 통해 사용자 데이터 받기, 아래는 데이터 선언

        SharedPreferences userintroduction = getSharedPreferences("introduction", MODE_PRIVATE);
        SharedPreferences userdistance = getSharedPreferences("distance", MODE_PRIVATE);
        SharedPreferences useramount = getSharedPreferences("amount", MODE_PRIVATE);

        //SharePreferences를 통해 넣어놓은 데이터를 확인하여 출력하기(데이터 : introduction, 없으면 빈칸)

        EditText introductioninputtext = (EditText) findViewById(R.id.introductionInputText);
        introductioninputtext.setText(userintroduction.getString("introduction", ""));

        //프로필 이미지 profileImage 선언

        profileImage = (ImageView) findViewById(R.id.profileImageView);

        //SharedPreferences를 통해 넣어놓은 데이터를 확인하여 출력하기(데이터 : image, profile_default.png)
        SharedPreferences userimage = getSharedPreferences("image", MODE_PRIVATE);
        if(userimage.getString("image", "") == ""){
            Bitmap basebitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.profile_default);
            profileImage.setImageBitmap(basebitmap);
        } else {
            String bitmapString = userimage.getString("image", "");
            profileImage.setImageBitmap(StringToBitMap(bitmapString));
        }

        //Topbar 이미지들 선언
        ImageView changeImage = (ImageView) findViewById(R.id.changePictureImageView);
        ImageView backImage = (ImageView) findViewById(R.id.backBarImageView);


        // 거리 seekbar 및 거리 텍스트 선언
        final TextView distanceTextView = (TextView) findViewById(R.id.myDistanceTextView);
        SeekBar distanceSeekBar = (SeekBar) findViewById(R.id.distanceSeekBar);

        //SharedPreferences를 통해 받은 거리 Int 데이터를 확인하여 설정해주기(데이터 : distance, 없으면 0)
        distanceSeekBar.setProgress(userdistance.getInt("distance", 0));
        distanceTextView.setText(userdistance.getInt("distance", 0) + " km");

        // 거리 seekbar 세부 설정
        distanceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int stepSize = 10;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //seekbar 이동을 stepSize인 10단위로 제한하기
                progress = ((int)Math.round(progress/stepSize)) * stepSize;
                seekBar.setProgress(progress);
                distanceTextView.setText(progress + " km");

                //SharedPreferences를 사용하여 변경된 거리 Int 데이터를 넣어주기(데이터 : distance)

                SharedPreferences userdistance = getSharedPreferences("distance", MODE_PRIVATE);
                SharedPreferences.Editor editor = userdistance.edit();
                editor.putInt("distance", progress);
                editor.commit();
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

        //SharedPreferences를 통해 받은 금액 Int 데이터를 확인하여 설정해주기(데이터 : amount, 없으면 0)
        amountSeekbar.setProgress(useramount.getInt("amount", 0));
        amountTextView.setText(NumberFormat.getInstance(Locale.getDefault()).
                format(useramount.getInt("amount", 0)) + " ￦");

        amountSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int stepSize = 1000;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //seekbar 이동을 stepSize인 1000단위로 제한하기
                progress = ((int) Math.round(progress/stepSize)) * stepSize;
                seekBar.setProgress(progress);
                //숫자에 쉼표 자동으로 넣게(금액으로 설정)
                amountTextView.setText(NumberFormat.getInstance(Locale.getDefault()).format(progress) + " ￦");

                //SharedPreferences를 사용하여 변경된 금액 Int 데이터를 넣어주기(데이터 : amount)

                SharedPreferences useramount = getSharedPreferences("amount", MODE_PRIVATE);
                SharedPreferences.Editor editor = useramount.edit();
                editor.putInt("amount", progress);
                editor.commit();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //프로필 이미지 바꾸기

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
                //변환된 프로필 이미지를 Bitmap으로 변환시켜서
                Bitmap bitmap = ((BitmapDrawable)profileImage.getDrawable()).getBitmap();
                //SharedPreferences에 저장하기(String으로!)
                SharedPreferences userimage = getSharedPreferences("image", MODE_PRIVATE);
                SharedPreferences.Editor editor = userimage.edit();
                editor.putString("image", BitMapToString(bitmap));
                editor.commit();

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

    public void saveintroduction(View view){
        EditText introductioninputtext = (EditText) findViewById(R.id.introductionInputText);
        String introductiontext = introductioninputtext.getText().toString();
        introductioninputtext.setText(introductiontext);

        SharedPreferences userintroduction = getSharedPreferences("introduction", MODE_PRIVATE);
        SharedPreferences.Editor editor = userintroduction.edit();
        editor.putString("introduction", introductiontext);
        editor.commit();
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

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
    }
}
