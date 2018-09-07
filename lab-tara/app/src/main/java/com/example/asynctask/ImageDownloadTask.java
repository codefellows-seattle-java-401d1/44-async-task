package com.example.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ImageDownloadTask extends AsyncTask<String, Integer, Bitmap> {
    ImageView image;

    public ImageDownloadTask(ImageView imageView) {
        image = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... imageUrls) {
        try {
            Log.d("BACKGROUND", "downloading");
            URL url = new URL(imageUrls[0]);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            Bitmap bm = BitmapFactory.decodeStream(bis);

            bis.close();
            is.close();

            Log.d("BACKGROUND", "complete");
            return bm;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        Log.d("POST EXECUTE", "done");
        if (bitmap != null) {
            image.setImageBitmap(bitmap);
        }
    }
}