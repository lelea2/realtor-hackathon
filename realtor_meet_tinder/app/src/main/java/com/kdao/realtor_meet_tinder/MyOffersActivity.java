package com.kdao.realtor_meet_tinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kdao.realtor_meet_tinder.customlist.CustomList;

public class MyOffersActivity extends AppCompatActivity {
    ListView list;
    String[] offer = {
            "Property1",
            "Property2",
            "Property3"
    } ;
    Integer[] imageId = {
            R.drawable.list_view_1,
            R.drawable.list_view_2,
            R.drawable.list_view_3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offers);
        CustomList adapter = new CustomList(MyOffersActivity.this, offer, imageId);
        list=(ListView)findViewById(R.id.offer_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MyOffersActivity.this, "You Clicked at " + offer[+position], Toast.LENGTH_SHORT).show();
            }
        });

    }

}