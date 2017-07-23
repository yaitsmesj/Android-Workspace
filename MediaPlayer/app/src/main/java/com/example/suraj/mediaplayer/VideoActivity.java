package com.example.suraj.mediaplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView vv = (VideoView)findViewById(R.id.videoView);

        vv.setMediaController(new MediaController(this));
        vv.setVideoURI(Uri.parse("https://github.com/yaitsmesj/Media/blob/master/01%20-%20Course%20Intro.mp4?raw=true"));
        vv.requestFocus();
        vv.start();

    }
}
