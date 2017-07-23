package com.example.suraj.camera;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG  = "MainActivity";
    Camera camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        camera = Camera.open();
        FrameLayout container = (FrameLayout) findViewById(R.id.container);
//        Button b = new Button(this);
//        b.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
//
//        b.setText("Hoi");
//        container.addView(b);

        camera.setDisplayOrientation(90);
        CameraView cv = new CameraView(this,camera);
        container.addView(cv);

        Button button = (Button)findViewById(R.id.btnPic);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture(view);
            }
        });
    }


    public void getCameraProperties(){
        List<Camera.Size> picSize = camera.getParameters().getSupportedPictureSizes();
        for(Camera.Size s: picSize){
            Log.d(TAG, "onCreate: width="+ s.width+" height="+s.height);
        }
        List<Camera.Size> vidSize = camera.getParameters().getSupportedVideoSizes();
        for(Camera.Size s: vidSize){
            Log.d(TAG, "onCreate: width="+ s.width+" height="+s.height);
        }

        List<Camera.Size> previewSize = camera.getParameters().getSupportedPreviewSizes();
        for(Camera.Size s: previewSize){
            Log.d(TAG, "onCreate: width="+ s.width+" height="+s.height);
        }
    }


    public void takePicture(View view){
        camera.takePicture(null,
                null,
                new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] bytes, Camera camera) {
                        Log.d(TAG, "onPictureTaken: " +bytes.length);
                    }
                });

    }
}
