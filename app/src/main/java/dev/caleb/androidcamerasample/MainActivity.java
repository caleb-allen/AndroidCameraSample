package dev.caleb.androidcamerasample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button captureImageExternal = findViewById(R.id.btnCaptureImageExternal);
        captureImageExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureExternal();
            }
        });

        Button gallery = findViewById(R.id.btnGallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GalleryActivity.class);
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

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_.jpg";
        File storageDir = getFilesDir();
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        FileOutputStream imageOutputStream = new FileOutputStream(image.getAbsoluteFile());
        // Save a file: path for use with ACTION_VIEW intents
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageOutputStream);
        imageOutputStream.close();

        Log.d("MainActivity", "Saved Image: " + image.getAbsolutePath());
    }
}
