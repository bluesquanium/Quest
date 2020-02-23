package org.slt.android.quest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MapActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        context = this.getBaseContext();

        ImageView mainButtonImage = (ImageView) findViewById(R.id.mainMenuButton);
        mainButtonImage.setImageResource(R.drawable.quest_full_button);

        Fragment mapFragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragment).commit();

    }


    public void onProfileMenuButtonClicked(View view){
        Fragment profileFragment = new ProfileFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();

        ImageView profileButtonImage = (ImageView) findViewById(R.id.profileMenuButton);
        profileButtonImage.setImageResource(R.drawable.profile_full_button);

        ImageView mainButtonImage = (ImageView) findViewById(R.id.mainMenuButton);
        mainButtonImage.setImageResource(R.drawable.quest_empty_button);

        ImageView chatboardButtonImage = (ImageView) findViewById(R.id.chatboardMenuButton);
        chatboardButtonImage.setImageResource(R.drawable.chat_empty_button);
    }

    public void onMapMenuButtonClicked(View view){
        Fragment mapFragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragment).commit();

        ImageView profileButtonImage = (ImageView) findViewById(R.id.profileMenuButton);
        profileButtonImage.setImageResource(R.drawable.profile_empty_button);

        ImageView mainButtonImage = (ImageView) findViewById(R.id.mainMenuButton);
        mainButtonImage.setImageResource(R.drawable.quest_full_button);

        ImageView chatboardButtonImage = (ImageView) findViewById(R.id.chatboardMenuButton);
        chatboardButtonImage.setImageResource(R.drawable.chat_empty_button);
    }

    public void onChatboardMenuButtonClicked(View view){
        Fragment chatboardFragment = new ChatboardFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, chatboardFragment).commit();

        ImageView profileButtonImage = (ImageView) findViewById(R.id.profileMenuButton);
        profileButtonImage.setImageResource(R.drawable.profile_empty_button);

        ImageView mainButtonImage = (ImageView) findViewById(R.id.mainMenuButton);
        mainButtonImage.setImageResource(R.drawable.quest_empty_button);

        ImageView chatboardButtonImage = (ImageView) findViewById(R.id.chatboardMenuButton);
        chatboardButtonImage.setImageResource(R.drawable.chat_full_button);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAffinity(this);
    }
}
