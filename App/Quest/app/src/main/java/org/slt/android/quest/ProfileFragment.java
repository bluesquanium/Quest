package org.slt.android.quest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment_profile, container, false);

        //SharedPreferences를 통해 사용자 데이터 받기, 아래는 데이터 선언(profile_introduction)

        SharedPreferences userintroduction = getActivity().getSharedPreferences("profile_introduction", Context.MODE_PRIVATE);

        //프로필 이미지 선언
        ImageView profileImage = (ImageView) v.findViewById(R.id.profileImageView);

        //소개글 introductionText 선언
        TextView introductionText = (TextView) v.findViewById(R.id.myIntroductionTextView);

        //SharePreferences를 통해 넣어놓은 데이터를 확인하여 출력하기(데이터 : profile_introduction, 없으면 빈칸)

        introductionText.setText(userintroduction.getString("profile_introduction", ""));

        //SharedPreferences를 통해 사용자 데이터 받기, 아래는 데이터 선언(profile_image)

        SharedPreferences userimage = getActivity().getSharedPreferences("profile_image", Context.MODE_PRIVATE);
        if(userimage.getString("profile_image","") == ""){
            Bitmap basebitmap = BitmapFactory.decodeResource(getActivity().getApplicationContext().getResources(), R.drawable.profile_default);
            profileImage.setImageBitmap(basebitmap);
        } else{
            String bitmapString = userimage.getString("profile_image", "");
            profileImage.setImageBitmap(StringToBitMap(bitmapString));
        }

        profileImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        ImageView settingImage = (ImageView) v.findViewById(R.id.settingImageView);
        settingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        ImageView nextImage = (ImageView) v.findViewById(R.id.nextButtonImage);
        ImageView nextImage1 = (ImageView) v.findViewById(R.id.nextButtonImage1);
        ImageView nextImage2 = (ImageView) v.findViewById(R.id.nextButtonImage2);
        ImageView nextImage3 = (ImageView) v.findViewById(R.id.nextButtonImage3);
        nextImage.setImageResource(R.drawable.next_image);
        nextImage1.setImageResource(R.drawable.next_image);
        nextImage2.setImageResource(R.drawable.next_image);
        nextImage3.setImageResource(R.drawable.next_image);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

}
