package dev.caleb.androidcamerasample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DemoGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_gallery);
    }

    private List<Bitmap> getImages(){
        File storageDir = getFilesDir();

        File[] files = storageDir.listFiles();

        ArrayList<Bitmap> bitmaps = new ArrayList<>();

        for (File file : files) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            bitmaps.add(bitmap);
        }

        return bitmaps;
    }

    private class ImageAdapter extends BaseAdapter{
        private List<Bitmap> images;

        public ImageAdapter(List<Bitmap> images) {
            this.images = images;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object getItem(int position) {
            return images.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new ImageView(parent.getContext());
            }
            ImageView imageView = (ImageView) convertView;

            imageView.setImageBitmap(images.get(position));

            return imageView;
        }
    }
}
