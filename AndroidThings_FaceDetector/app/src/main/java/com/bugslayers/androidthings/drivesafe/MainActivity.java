package com.bugslayers.androidthings.drivesafe;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

public class MainActivity extends Activity {

    private static final String TAG = FaceDetector.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img = (ImageView) findViewById(R.id.img);

        final DisplayManager display = new DisplayManager();
        display.init();
        display.setImage(getResources(), R.drawable.neutral_face);

        FirebaseApp.initializeApp(this);
        FaceDetector fc = new FaceDetector(this, img, getMainLooper());
        fc.setListener(new FaceDetector.CameraListener() {
            @Override
            public void onError() {
                // Handle error
            }

            @Override
            public void onSuccess(FaceDetector.FACE_STATUS status) {
                Log.d(TAG, "Face ["+status+"]");

                switch (status) {
                    case SMILING:
                       display.setImage(getResources(), R.drawable.smiling_face);
                       Toast.makeText(MainActivity.this, "smiling", Toast.LENGTH_SHORT).show();
                        break;
                    case LEFT_EYE_OPEN_RIGHT_CLOSE:
                        display.setImage(getResources(), R.drawable.right_eyes_closed);
                        Toast.makeText(MainActivity.this, "Right eye close", Toast.LENGTH_SHORT).show();
                        break;
                    case RIGHT_EYE_OPEN_LEFT_CLOSE:
                        display.setImage(getResources(), R.drawable.left_eyes_closed);
                        Toast.makeText(MainActivity.this, "Left eye close", Toast.LENGTH_SHORT).show();
                        break;
                     default:
                        display.setImage(getResources(), R.drawable.neutral_face);
                }

            }
        });

        fc.capture();
    }
}
