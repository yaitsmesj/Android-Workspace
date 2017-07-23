package com.example.suraj.lecture10json;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Suraj on 6/27/2017.
 */
class DownloadTask extends AsyncTask<String, Void, ArrayList<Post>> {
    public static final String TAG = "REST";

    interface OnDownloadListener{
        void onDownloaded(ArrayList<Post> posts);
    }
    private OnDownloadListener odl;
    public DownloadTask(OnDownloadListener odl){
        this.odl = odl;
    }


    @Override
    protected ArrayList<Post> doInBackground(String... params) {

        ArrayList<Post> postList = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         //   Log.d(TAG, "doInBackground: Connection Formed");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String buffer = "";
            while (buffer != null) {
                sb.append(buffer);
                buffer = br.readLine();
            }

            Log.d(MainActivity.TAG, "doInBackground: " + sb.toString());
            JSONArray postJsonArray = new JSONArray(sb.toString());

            postList = new ArrayList<>(postJsonArray.length());
            for (int i = 0; i < postJsonArray.length(); i++) {
                JSONObject postJsonObject = postJsonArray.getJSONObject(i);
                Post post = new Post(
                        postJsonObject.getInt("userId"),
                        postJsonObject.getInt("id"),
                        postJsonObject.getString("title"),
                        postJsonObject.getString("body")
                );

                Log.d(MainActivity.TAG, "doInBackground: " + post.getId());
                postList.add(post);

            }

        } catch (IOException | JSONException e) {

          // e.printStackTrace();
        }
     //   Log.d(TAG, "doInBackground: "+postList);
        return postList;
    }

    @Override
    protected void onPostExecute(ArrayList<Post> posts) {
        super.onPostExecute(posts);
        odl.onDownloaded(posts);
    }
}
