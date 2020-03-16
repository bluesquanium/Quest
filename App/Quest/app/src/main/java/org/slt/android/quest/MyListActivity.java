package org.slt.android.quest;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MyListActivity extends AppCompatActivity {

    List<QuestList> questList;
    ListView questingListView;
    ListView questedListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        questList = new ArrayList<>();

        questList.add(new QuestList(R.drawable.delivery, "2020. 03. 05. (목)", "심부름", "5,000 ₩"));
        questList.add(new QuestList(R.drawable.baby, "2020. 03. 05. (목)", "돌봄 - 아기", "5,000 ₩"));
        questList.add(new QuestList(R.drawable.moving, "2020. 03. 05. (목)", "짐 옮기기", "5,000 ₩"));
        questList.add(new QuestList(R.drawable.pet, "2020. 03. 05. (목)", "돌봄 - 애완동물", "5,000 ₩"));
        questList.add(new QuestList(R.drawable.cleaning, "2020. 03. 05. (목)", "청소", "5,000 ₩"));

        questingListView = (ListView) findViewById(R.id.questingListView);
        questedListView = (ListView) findViewById(R.id.questedListView);

        CustomListAdapter customListAdapter = new CustomListAdapter(this, R.layout.custom_list_item, questList);

        questingListView.setAdapter(customListAdapter);
        questedListView.setAdapter(customListAdapter);

        setDynamicHeight(questingListView);
        setDynamicHeight(questedListView);

    }

    public static void setDynamicHeight(ListView listView){
        ListAdapter adapter = listView.getAdapter();
        if(adapter == null){
            return;
        }
        int height = 0;
        int desireWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < adapter.getCount(); i++){
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(desireWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = height + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(layoutParams);
        listView.requestLayout();

    }


}
