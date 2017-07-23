package com.example.suraj.postcomments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.suraj.postcomments.R;
import com.example.suraj.postcomments.models.User;

import java.util.ArrayList;

/**
 * Created by Suraj on 6/28/2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder> {

    public static final String TAG = "Error";
    private final Context context;
    private ArrayList<User> userList;
    private OnUserClickListener oucl;

    public UserAdapter(ArrayList<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    public void setOnUserClickListener(OnUserClickListener oucl) {
        this.oucl = oucl;
    }

    public void updateUserList(ArrayList<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @Override
    public UserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.users_list, parent, false);
        return new UserAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserAdapterViewHolder holder, int position) {

        final User user = userList.get(position);
        holder.tv_UserList_Name.setText(user.getName());
        holder.tv_UserList_Username.setText(user.getUsername());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oucl != null) {
                    oucl.onUserClicked(user.getId());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public interface OnUserClickListener {
        void onUserClicked(int userId);
    }

    public class UserAdapterViewHolder extends RecyclerView.ViewHolder {

        final TextView tv_UserList_Username;
        final TextView tv_UserList_Name;
        final View rootView;

        public UserAdapterViewHolder(View itemView) {
            super(itemView);
            tv_UserList_Name = (TextView) itemView.findViewById(R.id.tv_UserList_Name);
            tv_UserList_Username = (TextView) itemView.findViewById(R.id.tv_UserList_Username);
            rootView = itemView;
        }
    }

}
