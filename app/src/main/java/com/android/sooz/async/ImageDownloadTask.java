package com.android.sooz.async;

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


//this is preferred over creating own threads within Main Activity
//more used for a thing that has a result like loading a photo
//not great for a stopwatch - it doesn't have a firm result - just happens in background

//Async task <Params, Progress, Result>
   //progress can update from outside
   ///String in this case will be a URL
   //builds the doInBackground method
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
