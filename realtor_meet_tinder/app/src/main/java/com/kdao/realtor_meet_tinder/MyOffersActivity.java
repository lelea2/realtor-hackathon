package com.kdao.realtor_meet_tinder;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import android.content.Context;

import com.kdao.realtor_meet_tinder.customlist.CustomList;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import android.app.Dialog;

public class MyOffersActivity extends AppCompatActivity {
    private ListView list;
    private String[] offer = {
            "Property1",
            "Property2",
            "Property3"
    } ;
    private Integer[] imageId = {
            R.drawable.list_view_1,
            R.drawable.list_view_2,
            R.drawable.list_view_3
    };
    private boolean initialLoad = true;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offers);
        setupBottomBar();
        CustomList adapter = new CustomList(MyOffersActivity.this, offer, imageId);
        list=(ListView)findViewById(R.id.offer_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MyOffersActivity.this, "You Clicked at " + offer[+position], Toast.LENGTH_SHORT).show();
                handleOnClickOffer(position);
            }
        });
    }

    /**
     * Helper function to handle on click offer
     */
    private void handleOnClickOffer(int position) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.share_features);

        TextView in_app_msg = (TextView) dialog.findViewById(R.id.in_app_message);
        // if button is clicked, close the custom dialog
        in_app_msg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog.dismiss();
                Intent newIntent = new Intent(MyOffersActivity.this, MessagesActivity.class);
                startActivity(newIntent);
            }
        });
        dialog.show();
    }


    /**
     * Private function to set up bottom bar
     */
    private void setupBottomBar() {
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTabPosition(1);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (initialLoad == true) {
                    initialLoad = false;
                    return;
                }
                if (tabId == R.id.tab_favorites) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
//                    Intent newIntent = new Intent(MyOffersActivity.this, MyOffersActivity.class);
//                    startActivity(newIntent);
                } else if (tabId == R.id.tab_messages) {
                    Intent newIntent = new Intent(MyOffersActivity.this, MessagesActivity.class);
                    startActivity(newIntent);
                } else if (tabId == R.id.tab_home) {
                    Intent newIntent = new Intent(MyOffersActivity.this, MainActivity.class);
                    startActivity(newIntent);
                }
            }
        });
    }

    public void goBack(View v) {
        Intent newIntent = new Intent(MyOffersActivity.this, MainActivity.class);
        startActivity(newIntent);
    }
}