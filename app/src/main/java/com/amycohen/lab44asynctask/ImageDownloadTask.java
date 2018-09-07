package com.amycohen.lab44asynctask;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
        String imageUrl = imageUrls[0];

        if (imageUrl == null) {
            return null;
        }
        try {
            URL url = new URL(imageUrl);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);

            bufferedInputStream.close();
            inputStream.close();

            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            image.setImageBitmap(bitmap);
        }
    }
}
