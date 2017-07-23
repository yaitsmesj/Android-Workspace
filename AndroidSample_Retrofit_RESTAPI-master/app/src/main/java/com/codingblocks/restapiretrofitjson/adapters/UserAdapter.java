package com.codingblocks.restapiretrofitjson.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.interfaces.OnItemClickListener;
import com.codingblocks.restapiretrofitjson.interfaces.onButtonClickListener;
import com.codingblocks.restapiretrofitjson.models.Post;
import com.codingblocks.restapiretrofitjson.models.User;

import java.util.ArrayList;

/**
 * Created by championswimmer on 29/06/17.
 */

public class UserAdapter
        extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    public static final String TAG = "UA";

    private Context context;
    private ArrayList<User> users;
    private onButtonClickListener onButtonClickListener;

    public void setOnButtonClickListener(onButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public UserAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    public void updateUsers(ArrayList<User> newUserList) {
        this.users = newUserList;
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_user, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        final User thisUser = users.get(position);

        holder.tvUserUsername.setText(thisUser.getUsername());
        holder.tvUserName.setText(thisUser.getName());
        holder.tvUserPhone.setText(thisUser.getPhone());
        holder.tvUserEmail.setText(thisUser.getEmail());

        holder.btnPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onButtonClickListener!=null){
                    onButtonClickListener.onButtonClicked(thisUser.getId(),v.getId());
                }
            }
        });
        holder.btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onButtonClickListener!=null){
                    onButtonClickListener.onButtonClicked(thisUser.getId(),v.getId());
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserUsername, tvUserName, tvUserPhone, tvUserEmail;
        View thisView;
        Button btnPosts,btnComments;

        public UserViewHolder(View itemView) {
            super(itemView);
            thisView = itemView;
            tvUserEmail = (TextView) itemView.findViewById(R.id.tvUserEmail);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvUserPhone = (TextView) itemView.findViewById(R.id.tvUserPhone);
            tvUserUsername = (TextView) itemView.findViewById(R.id.tvUserUsername);
            btnPosts = (Button)itemView.findViewById(R.id.btn_posts);
            btnComments = (Button)itemView.findViewById(R.id.btn_comments);
        }
    }
}
