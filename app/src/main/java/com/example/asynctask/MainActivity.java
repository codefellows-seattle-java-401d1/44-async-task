package com.example.asynctask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    Button button;
    ProgressDialog mProgressDialog;
    String url = "https://www.hdwallpapers.in/download/windows_xp_bliss-1920x1200.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ImageDownloader().execute(url);
            }
        });
    }
    private class ImageDownloader extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Downloading Image");
            mProgressDialog.setMessage("Loading.");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {
            String imageURL = URL[0];
            Bitmap bmap = null;
            try{
                InputStream input = new java.net.URL(imageURL).openStream();
                bmap = BitmapFactory.decodeStream(input);
            }catch(Exception e){
                e.printStackTrace();
            }
            return bmap;
        }

        protected void onPostExecute(Bitmap result){
            image.setImageBitmap(result);
            mProgressDialog.dismiss();
        }
    }

}
