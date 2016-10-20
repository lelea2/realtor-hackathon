package com.kdao.realtor_meet_tinder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kdao.realtor_meet_tinder.swipecard.FlingCardListener;
import com.kdao.realtor_meet_tinder.swipecard.SwipeFlingAdapterView;
import com.kdao.realtor_meet_tinder.util.Data;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements FlingCardListener.ActionDownInterface {

    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<Data> al;
    private SwipeFlingAdapterView flingContainer;

    public static void removeBackground() {


        viewHolder.background.setVisibility(View.GONE);
        myAppAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        al = new ArrayList<>();
        al.add(new Data("http://ap.rdcpix.com/362409781/53f290d3791adca34650b72bee84141al-m0xd-w480_h480_q80.jpg", "873 Ferngrove Dr, Cupertino, CA 95014"));
        al.add(new Data("http://ap.rdcpix.com/370742724/f144f3c038d94ebe090425dbe30e3a73l-m0xd-w480_h480_q80.jpg", "11002 Canyon Vista Dr, Cupertino, CA 95014"));
        al.add(new Data("http://ap.rdcpix.com/1932726199/b9803583e6d8afb40e05fdc255a4f36el-m0xd-w480_h480_q80.jpg", "22923 Longdown Rd, Cupertino, CA 95014"));
        al.add(new Data("http://ap.rdcpix.com/362409781/53f290d3791adca34650b72bee84141al-m0xd-w480_h480_q80.jpg", "873 Ferngrove Dr, Cupertino, CA 95014"));
        al.add(new Data("http://ap.rdcpix.com/370742724/f144f3c038d94ebe090425dbe30e3a73l-m0xd-w480_h480_q80.jpg", "11002 Canyon Vista Dr, Cupertino, CA 95014"));
        al.add(new Data("http://ap.rdcpix.com/1932726199/b9803583e6d8afb40e05fdc255a4f36el-m0xd-w480_h480_q80.jpg", "22923 Longdown Rd, Cupertino, CA 95014"));
        al.add(new Data("http://ap.rdcpix.com/362409781/53f290d3791adca34650b72bee84141al-m0xd-w480_h480_q80.jpg", "873 Ferngrove Dr, Cupertino, CA 95014"));
        al.add(new Data("http://ap.rdcpix.com/362409781/53f290d3791adca34650b72bee84141al-m0xd-w480_h480_q80.jpg", "873 Ferngrove Dr, Cupertino, CA 95014"));
        al.add(new Data("http://ap.rdcpix.com/362409781/53f290d3791adca34650b72bee84141al-m0xd-w480_h480_q80.jpg", "873 Ferngrove Dr, Cupertino, CA 95014"));
        myAppAdapter = new MyAppAdapter(al, MainActivity.this);
        flingContainer.setAdapter(myAppAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                al.remove(0);
                myAppAdapter.notifyDataSetChanged();
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject

            }

            @Override
            public void onRightCardExit(Object dataObject) {

                al.remove(0);
                myAppAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                myAppAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onActionDownPerform() {
        Log.e("action", "bingo");
    }

    public static class ViewHolder {
        public static FrameLayout background;
        public TextView DataText;
        public ImageView cardImage;


    }

    public class MyAppAdapter extends BaseAdapter {


        public List<Data> parkingList;
        public Context context;

        private MyAppAdapter(List<Data> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return parkingList.size();
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
                viewHolder.DataText = (TextView) rowView.findViewById(R.id.bookText);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.DataText.setText(parkingList.get(position).getDescription() + "");

            Glide.with(MainActivity.this).load(parkingList.get(position).getImagePath()).into(viewHolder.cardImage);

            return rowView;
        }
    }
}
