package com.kdao.realtor_meet_tinder;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import android.view.View;

public class ConversationActivity extends AppCompatActivity {

    private boolean initialLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        setupBottomBar();
        TextView toolbar_logo = (TextView) findViewById(R.id.toolbar_logo);
        toolbar_logo.setText("125 Ocean Dr");
    }


    /**
     * Private function to set up bottom bar
     */
    private void setupBottomBar() {
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
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
                    Intent newIntent = new Intent(ConversationActivity.this, MyOffersActivity.class);
                    startActivity(newIntent);
                } else if (tabId == R.id.tab_messages) {
//                    Intent newIntent = new Intent(ConversationActivity.this, ConversationActivity.class);
//                    startActivity(newIntent);
                } else if (tabId == R.id.tab_home) {
                    Intent newIntent = new Intent(ConversationActivity.this, MainActivity.class);
                    startActivity(newIntent);
                }
            }
        });
    }

    public void goBack(View v) {
        Intent newIntent = new Intent(ConversationActivity.this, MyOffersActivity.class);
        startActivity(newIntent);
    }
}
