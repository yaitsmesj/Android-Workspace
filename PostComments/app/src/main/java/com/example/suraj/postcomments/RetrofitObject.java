package com.example.suraj.postcomments;

import android.util.Log;

import com.example.suraj.postcomments.api.PostApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Suraj on 6/28/2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class RetrofitObject {

    private static final String TAG = "Error";
    private static RetrofitObject retrofitObject = null;
    private final Retrofit retrofit;

    private RetrofitObject() {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitObject getInstance() {
        if (retrofitObject == null) {
            retrofitObject = new RetrofitObject();
        }
        return retrofitObject;
    }

    public PostApi getPostApi() {
        Log.d(TAG, "getPostApi: API Created");
        return this.retrofit.create(PostApi.class);
    }
}

