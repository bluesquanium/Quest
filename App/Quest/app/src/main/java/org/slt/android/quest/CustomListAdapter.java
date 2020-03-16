package org.slt.android.quest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<QuestList> {

    Context mCtx;
    int resource;
    List<QuestList> questList;

    public CustomListAdapter(Context mCtx, int resource, List<QuestList> questList){
        super(mCtx, resource, questList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.questList = questList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(resource, null);

        TextView dateTextView = view.findViewById(R.id.dateTextView);
        TextView questNameTextView = view.findViewById(R.id.questNameTextView);
        TextView questAmountTextView = view.findViewById(R.id.questAmountTextView);
        ImageView questIconImageView = view.findViewById(R.id.questIconImageView);

        QuestList quest = questList.get(position);
        dateTextView.setText(quest.getDate());
        questNameTextView.setText(quest.getQuestname());
        questAmountTextView.setText(quest.getQuestamount());

        Drawable drawable = mCtx.getResources().getDrawable(quest.getImage());
        questIconImageView.setImageDrawable(drawable);

        Button reviewButton = view.findViewById(R.id.reviewButton);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;

    }
}
