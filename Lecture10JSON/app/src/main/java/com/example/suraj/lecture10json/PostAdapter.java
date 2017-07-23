package com.example.suraj.lecture10json;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Suraj on 6/27/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private ArrayList<Post> posts;
    Context context;

    OnItemClickListener oicl;

    interface OnItemClickListener{
        void onItemClicked(int pos);
    }
    public void setOnItemClickListener(OnItemClickListener oicl){
        this.oicl = oicl;
    }

    PostAdapter(ArrayList<Post> posts, Context context){
        this.posts = posts;
        this.context = context;
    }

    void updatePostList(ArrayList<Post> posts){
        this.posts = posts;
        notifyDataSetChanged();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.recycleview_list,parent,false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        final Post thisPost = posts.get(position);

        holder.tvTitle.setText(thisPost.getTitle());
        holder.tvBody.setText(thisPost.getBody());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oicl!=null){
                    oicl.onItemClicked(thisPost.getId());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvBody;
        View rootView;
        public PostViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
            tvBody = (TextView)itemView.findViewById(R.id.tv_body);
            rootView = itemView;
        }
    }
}
