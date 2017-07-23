package com.example.suraj.facebookfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Suraj on 6/14/2017.
 */

public class FeedAdapter extends BaseAdapter{
    Context context;
    ArrayList<String> data;
    String dataItem;
    FeedViewHolder feedViewHolder;
    public FeedAdapter(Context context,ArrayList<String> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position).length()<=10)
            return 0;
        else
            return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        dataItem = (String) getItem(position);
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            if(dataItem.length()<=10)
                convertView=layoutInflater.inflate(R.layout.small_font_layout,null);
            else
                convertView=layoutInflater.inflate(R.layout.large_font_layout,null);

            feedViewHolder = new FeedViewHolder();
            feedViewHolder.tv_data = (TextView)convertView.findViewById(R.id.tv_data);
            convertView.setTag(feedViewHolder);
        }
        feedViewHolder = (FeedViewHolder)convertView.getTag();

        feedViewHolder.tv_data.setText(dataItem);

        return convertView;
    }
    static class FeedViewHolder{
        TextView tv_data;
    }
}
