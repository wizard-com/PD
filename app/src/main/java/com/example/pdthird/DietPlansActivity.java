package com.example.pdthird;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DietPlansActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    DietListAdapter expandableListAdapter;
    ArrayList<DietTitleItem> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plans);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableDietList);
        expandableListDetail = ExpandableDietListData.getData();
        expandableListTitle = new ArrayList<DietTitleItem>();
        expandableListAdapter = new DietListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        addTitles();
        expandableListAdapter.notifyDataSetChanged();

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }
    private void addTitles(){
        expandableListAdapter.add(new DietTitleItem("#594287", "High-protein diet", R.drawable.protein));
        expandableListAdapter.add(new DietTitleItem("#84B83C", "High-fibre diet", R.drawable.vegetables));
        expandableListAdapter.add(new DietTitleItem("#F9EC00", "Low-fat diet", R.drawable.lowfat));
        expandableListAdapter.add(new DietTitleItem("#FB8A20", "Low-carb diet", R.drawable.lowcarb));
        expandableListAdapter.add(new DietTitleItem("#87D3E7", "Balanced diet", R.drawable.balanced_diet));
    }
}
