package com.kdao.realtor_meet_tinder;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

public class MessagesActivity extends AppCompatActivity {

    private boolean initialLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        setupBottomBar();
        TextView toolbar_logo = (TextView) findViewById(R.id.toolbar_logo);
        ImageView filter = (ImageView) findViewById(R.id.filter);
        toolbar_logo.setText(R.string.message);
//        filter.setBackground(R.drawable);
    }


    /**
     * Private function to set up bottom bar
     */
    private void setupBottomBar() {
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTabPosition(2);
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
                    Intent newIntent = new Intent(MessagesActivity.this, MyOffersActivity.class);
                    startActivity(newIntent);
                } else if (tabId == R.id.tab_messages) {
//                    Intent newIntent = new Intent(ConversationActivity.this, ConversationActivity.class);
//                    startActivity(newIntent);
                } else if (tabId == R.id.tab_home) {
                    Intent newIntent = new Intent(MessagesActivity.this, MainActivity.class);
                    startActivity(newIntent);
                }
            }
        });
    }

    /**
     * Public helper to handle view conversation details
     */
    public void handleViewDetail(View v) {
        Intent newIntent = new Intent(MessagesActivity.this, ConversationActivity.class);
        startActivity(newIntent);
    }

    public void goBack(View v) {
        Intent newIntent = new Intent(MessagesActivity.this, MainActivity.class);
        startActivity(newIntent);
    }
}
