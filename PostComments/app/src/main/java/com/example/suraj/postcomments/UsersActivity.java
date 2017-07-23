package com.example.suraj.postcomments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.suraj.postcomments.adapters.UserAdapter;
import com.example.suraj.postcomments.api.PostApi;
import com.example.suraj.postcomments.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {
    private static final String TAG = "Error";

    private RecyclerView rv_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        final UserAdapter userAdapter = new UserAdapter(new ArrayList<User>(), this);
        rv_users = (RecyclerView) findViewById(R.id.rv_users);
        rv_users.setLayoutManager(new LinearLayoutManager(this));
        rv_users.setAdapter(userAdapter);
        userAdapter.setOnUserClickListener(new UserAdapter.OnUserClickListener() {
            @Override
            public void onUserClicked(int userId) {

                Intent i = new Intent(UsersActivity.this, PostActivity.class);
                i.putExtra("userId", userId);
                startActivity(i);
            }
        });

        PostApi postApi = RetrofitObject.getInstance().getPostApi();
        postApi.getUsers().enqueue(new Callback<ArrayList<User>>() {

            @Override
            public void onResponse(@NonNull Call<ArrayList<User>> call, @NonNull Response<ArrayList<User>> response) {
                // Log.d(TAG, "onResponse: 1");
                userAdapter.updateUserList(response.body());
                // Log.d(TAG, "onResponse: "+response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<User>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: Users Loading Fail");
                t.printStackTrace();
            }
        });

    }

}
