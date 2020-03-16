package org.slt.android.quest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomConditionListAdapter extends ArrayAdapter<ConditionList> {

    Context mCtx;
    int resource;
    List<ConditionList> conditionListList;

    public CustomConditionListAdapter(Context mCtx, int resource, List<ConditionList> conditionListList){
        super(mCtx, resource, conditionListList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.conditionListList = conditionListList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(resource, null);

        TextView conditionName = view.findViewById(R.id.conditionItemTextView);

        ConditionList conditionList = conditionListList.get(position);
        conditionName.setText(conditionList.getConditionName());

        return view;
    }
}
