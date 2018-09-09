package com.gbbeard.asynch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.imageView)
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //see lecture at 4:32 - 33
        ImageDownloadTask getImage = new ImageDownloadTask(image);
        getImage.execute("https://i.imgur.com/pXVsQcG.jpg");
    }

}