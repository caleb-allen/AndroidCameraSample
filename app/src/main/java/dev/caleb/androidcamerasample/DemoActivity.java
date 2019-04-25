package dev.caleb.androidcamerasample;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        findViewById(R.id.btnCaptureImageExternal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DemoActivity", "Clicked Button");
            }
        });


        // TODO launch an app to take a picture

        // TODO
    }

    private void takePictureExternal(){
        //
    }

    private void saveImage(Bitmap bitmap) throws IOException {

    }
}
