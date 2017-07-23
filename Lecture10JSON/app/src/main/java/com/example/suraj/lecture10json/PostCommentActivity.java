package com.example.suraj.lecture10json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostCommentActivity extends AppCompatActivity {

    private static final String TAG = "Retro";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);

        textView = (TextView)findViewById(R.id.tvPostId);
        int postId = getIntent().getIntExtra("postId",0);

        Retrofit retrofit = RetrofitObject.getInstance().getRetrofit();
        PostApi postApi = retrofit.create(PostApi.class);

        postApi.getUser(1).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: "+response.body().getName());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }
}
