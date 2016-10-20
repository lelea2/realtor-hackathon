package com.kdao.realtor_meet_tinder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import com.bumptech.glide.Glide;
import com.kdao.realtor_meet_tinder.swipecard.FlingCardListener;
import com.kdao.realtor_meet_tinder.swipecard.SwipeFlingAdapterView;
import com.kdao.realtor_meet_tinder.util.Data;
import com.kdao.realtor_meet_tinder.util.Common;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;


public class MainActivity extends AppCompatActivity implements FlingCardListener.ActionDownInterface {

    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<Data> al;
    private SwipeFlingAdapterView flingContainer;
    private boolean isExit = false;

    public static void removeBackground() {
//        viewHolder.background.setVisibility(View.GONE);
        myAppAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set up bar
        setupAppBar();
        setupBottomBar();

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        al = new ArrayList<>();
//        al.add(new Data("http://ap.rdcpix.com/362409781/53f290d3791adca34650b72bee84141al-m0xd-w480_h480_q80.jpg", "873 Ferngrove Dr, Cupertino, CA 95014", 3000000, "test"));
//        al.add(new Data("http://ap.rdcpix.com/370742724/f144f3c038d94ebe090425dbe30e3a73l-m0xd-w480_h480_q80.jpg", "11002 Canyon Vista Dr, Cupertino, CA 95014", 3000000, "test"));
//        al.add(new Data("http://ap.rdcpix.com/1932726199/b9803583e6d8afb40e05fdc255a4f36el-m0xd-w480_h480_q80.jpg", "22923 Longdown Rd, Cupertino, CA 95014", 3000000, "test"));
//        al.add(new Data("http://ap.rdcpix.com/362409781/53f290d3791adca34650b72bee84141al-m0xd-w480_h480_q80.jpg", "873 Ferngrove Dr, Cupertino, CA 95014", 3000000, "test"));
//        al.add(new Data("http://ap.rdcpix.com/370742724/f144f3c038d94ebe090425dbe30e3a73l-m0xd-w480_h480_q80.jpg", "11002 Canyon Vista Dr, Cupertino, CA 95014", 3000000, "test"));
//        al.add(new Data("http://ap.rdcpix.com/1932726199/b9803583e6d8afb40e05fdc255a4f36el-m0xd-w480_h480_q80.jpg", "22923 Longdown Rd, Cupertino, CA 95014", 3000000, "test"));
//        al.add(new Data("http://ap.rdcpix.com/362409781/53f290d3791adca34650b72bee84141al-m0xd-w480_h480_q80.jpg", "873 Ferngrove Dr, Cupertino, CA 95014", 3000000, "test"));
//        al.add(new Data("http://ap.rdcpix.com/362409781/53f290d3791adca34650b72bee84141al-m0xd-w480_h480_q80.jpg", "873 Ferngrove Dr, Cupertino, CA 95014", 3000000, "test"));
//        al.add(new Data("http://ap.rdcpix.com/362409781/53f290d3791adca34650b72bee84141al-m0xd-w480_h480_q80.jpg", "873 Ferngrove Dr, Cupertino, CA 95014", 3000000, "test"));

        al.add(new Data(R.drawable.card_1, "", 0, ""));
        al.add(new Data(R.drawable.card_2, "", 0, ""));
        al.add(new Data(R.drawable.card_3, "", 0, ""));
        al.add(new Data(R.drawable.card_4, "", 0, ""));
        al.add(new Data(R.drawable.card_5, "", 0, ""));
        al.add(new Data(R.drawable.ads, "", 0, ""));

        myAppAdapter = new MyAppAdapter(al, MainActivity.this);
        flingContainer.setAdapter(myAppAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                if (al.size() > 1) {
                    al.remove(0);
                    myAppAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                if (al.size() > 1) {
                    al.remove(0);
                    myAppAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                if (al.size() <= 1) { //dont do anything with the last card which is ads
                    return;
                }
                View view = flingContainer.getSelectedView();
//                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
                System.out.println(">>>>>>>>> scrollPercentage <<<<<<<<<<<" + scrollProgressPercent);
                if (scrollProgressPercent < 0.25 && scrollProgressPercent > 0.2) {
                    shareAction();
                }
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                View view = flingContainer.getSelectedView();
//                view.findViewById(R.id.background).setAlpha(0);
                myAppAdapter.notifyDataSetChanged();
            }
        });
    }

    private void shareAction() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Your body here";
        String shareSub = "Your subject here";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    /**
     * Private function to set up top app bar
     */
    private void setupAppBar() {
        // Always cast your custom Toolbar here, and set it as the ActionBar.
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        // Get the ActionBar here to configure the way it behaves.
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
//        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
//        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
//        ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
    }

    /**
     * Private function to set up bottom bar
     */
    private void setupBottomBar() {
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                System.out.println(">>>>>>>>> tabId: " + tabId);
                if (tabId == R.id.tab_favorites) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    Intent newIntent = new Intent(MainActivity.this, MyOffersActivity.class);
                    startActivity(newIntent);
                } else if (tabId == R.id.tab_messages) {
                    Intent newIntent = new Intent(MainActivity.this, ConversationActivity.class);
                    startActivity(newIntent);
                } else if(tabId == R.id.tab_home) {
                }
            }
        });
    }

    @Override
    public void onActionDownPerform() {
        Log.e("action", "down touchhhh hah");
    }
    public static class ViewHolder {
        public static FrameLayout background;
        public TextView AddressText;
        public TextView PriceText;
        public TextView DescText;
        public ImageView cardImage;
    }

    public class MyAppAdapter extends BaseAdapter {
        public List<Data> offerList;
        public Context context;
        private MyAppAdapter(List<Data> apps, Context context) {
            this.offerList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return offerList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;

            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
//                viewHolder.AddressText = (TextView) rowView.findViewById(R.id.address);
//                viewHolder.PriceText = (TextView) rowView.findViewById(R.id.price);
//                viewHolder.DescText = (TextView) rowView.findViewById(R.id.detail);
//                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
//            viewHolder.AddressText.setText(offerList.get(position).getAddress() + "");
//            viewHolder.PriceText.setText("$" + Common.priceToString((double) offerList.get(position).getPrice()) + "");
//            viewHolder.DescText.setText(offerList.get(position).getDescription() + "");
            Glide.with(MainActivity.this).load(offerList.get(position).getImagePath()).into(viewHolder.cardImage);
            return rowView;
        }
    }
}
