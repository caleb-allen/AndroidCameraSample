package dev.caleb.androidcamerasample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoActivity extends AppCompatActivity {
    public static int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        findViewById(R.id.btnCaptureImageExternal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DemoActivity", "Clicked Button");
                takePictureExternal();
            }
        });


        findViewById(R.id.btnDemoGallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DemoActivity.this, DemoGalleryActivity.class);
                startActivity(i);
            }
        });

    }

    private void takePictureExternal(){
        Intent takePictureExternalIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureExternalIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureExternalIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            Bitmap imageBitmap = (Bitmap) extras.get("data");

            try {
                saveImage(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveImage(Bitmap bitmap) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;

        File storageDir = getFilesDir();
        File image = File.createTempFile(
                imageFileName,
                "jpg",
                storageDir
        );

        FileOutputStream imageOutputStream = new FileOutputStream(image);

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageOutputStream);

        imageOutputStream.close();

        Log.d("DemoActivity", "Saved the file: " + image.getAbsolutePath());
    }
}
