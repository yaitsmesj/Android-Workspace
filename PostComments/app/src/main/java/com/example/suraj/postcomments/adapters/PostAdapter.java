package com.example.suraj.postcomments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.suraj.postcomments.R;
import com.example.suraj.postcomments.models.Post;

import java.util.ArrayList;

/**
 * Created by Suraj on 6/29/2017.
 */

@SuppressWarnings("DefaultFileTemplate")
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final Context context;
    private ArrayList<Post> postList;
    private OnPostClickListener opcl;

    public PostAdapter(ArrayList<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    public void setOnPostClickListener(OnPostClickListener opcl) {
        this.opcl = opcl;
    }

    public void updatePostList(ArrayList<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.users_list, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        final Post post = postList.get(position);
        holder.tv_Title.setText(post.getTitle());
        holder.tv_body.setText(post.getBody());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (opcl != null) {
                    opcl.onPostClicked(post.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public interface OnPostClickListener {
        void onPostClicked(int postId);
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        final TextView tv_Title;
        final TextView tv_body;
        final View rootView;

        public PostViewHolder(View itemView) {
            super(itemView);
            tv_Title = (TextView) itemView.findViewById(R.id.tv_UserList_Name);
            tv_body = (TextView) itemView.findViewById(R.id.tv_UserList_Username);
            rootView = itemView;
        }
    }
}
