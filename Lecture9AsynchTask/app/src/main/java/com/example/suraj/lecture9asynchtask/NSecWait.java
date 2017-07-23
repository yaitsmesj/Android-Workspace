package com.example.suraj.lecture9asynchtask;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by Suraj on 6/25/2017.
 */
class NSecWait extends AsyncTask<Integer, Float, String> {
    private TextView tvToChange;

    public NSecWait(TextView textView) {
        this.tvToChange = textView;
    }

    @Override
    protected String doInBackground(Integer... params) {

        for (int i = 0; i <= (params[0]) * 2; i++) {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() < startTime + 500);
            publishProgress(((float) i) / 2);
        }
        return "BOOM";
    }

    @Override
    protected void onPreExecute() {
        tvToChange.setText("Ready");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        tvToChange.setText(s);
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Float... values) {
        tvToChange.setText(String.valueOf(values[0]));
        super.onProgressUpdate(values);
    }
    //        @Override
//        protected Void doInBackground(Void... params) {
//            Long startTime = System.currentTimeMillis();
//            while(System.currentTimeMillis()<startTime+5000);
//                Log.d(TAG, "doInBackground: The wait is over ");
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        tvResult.setText("Clicked");
////                    }
////                });
//
//            return null;
//        }
}
