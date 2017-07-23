package com.example.suraj.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

/**
 * Created by Suraj on 7/20/2017.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback{

    Camera c;
    public CameraView(Context context, Camera camera) {
        super(context);
        this.c = camera;
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        try {
            c.setPreviewDisplay(surfaceHolder);
            c.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        c.stopPreview();
        try {
            c.setPreviewDisplay(surfaceHolder);
            c.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        c.release();
    }

}
