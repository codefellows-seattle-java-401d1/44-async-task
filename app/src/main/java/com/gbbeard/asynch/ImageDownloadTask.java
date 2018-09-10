package com.gbbeard.asynch;

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

public class ImageDownloadTask extends
        AsyncTask<String, Integer, Bitmap>{

    ImageView image;

    public ImageDownloadTask(ImageView imageView){

        image = imageView;

    }

    @Override
    protected Bitmap doInBackground(String... imageUrls) {

        try {
            Log.d("BACKGROUND", "downloading");
            URL url = new URL(imageUrls[0]);
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            Bitmap bm = BitmapFactory.decodeStream(bis);

            bis.close();
            is.close();
            Log.d("BACKGROUND", "downloading");

            return bm;

        } catch(IOException e){
            return null;
        }
    }

    //happens on the UI thread
    @Override
    protected void onPostExecute(Bitmap bitmap) {

        Log.d("POST EXECUTE", "done");

        if (bitmap != null) {
            image.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        Log.d("PROGRESS", "" + values[0]);
    }
}