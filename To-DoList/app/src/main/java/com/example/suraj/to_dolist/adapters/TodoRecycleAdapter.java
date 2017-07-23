package com.example.suraj.to_dolist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suraj.to_dolist.R;
import com.example.suraj.to_dolist.models.TodoData;

import java.util.ArrayList;

/**
 * Created by Suraj on 6/14/2017.
 */

@SuppressWarnings("DefaultFileTemplate")
public class TodoRecycleAdapter extends RecyclerView.Adapter<TodoRecycleAdapter.TodoViewHolder> {

    private final Context context;
    private final ArrayList<TodoData> todoData;

    public TodoRecycleAdapter(Context context, ArrayList<TodoData> todoData) {
        this.context = context;
        this.todoData = todoData;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.listview_layout,parent,false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        TodoData todoItem = todoData.get(position);
        holder.tv_task.setText(todoItem.getTask());
       holder.tv_place.setText(todoItem.getPlace());
        holder.tv_time.setText(todoItem.getTime());
        if (todoItem.isAlarm()==1)
            holder.iv_alarm.setVisibility(View.VISIBLE);
        else
            holder.iv_alarm.setVisibility(View.INVISIBLE);
    }
    @Override
    public int getItemCount() {
        return todoData.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_task;
        final TextView tv_place;
        final TextView tv_time;
        final ImageView iv_alarm;
        public TodoViewHolder(View itemView){

            super(itemView);
            tv_task=(TextView) itemView.findViewById(R.id.tv_task);
            tv_place=(TextView) itemView.findViewById(R.id.tv_place);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            iv_alarm = (ImageView) itemView.findViewById(R.id.iv_alarm);

        }

    }
}