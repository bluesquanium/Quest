package org.slt.android.quest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChatboardFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment_chatboard, container, false);

        Button room1 = (Button) v.findViewById(R.id.room1Button);
        Button room2 = (Button) v.findViewById(R.id.room2Button);
        Button room3 = (Button) v.findViewById(R.id.room3Button);

        room1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatroomActivity.class);
                startActivity(intent);
            }
        });

        room2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatroomActivity.class);
                startActivity(intent);
            }
        });

        room3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatroomActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
