package com.android.sooz.async;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        getImage.execute ("https://scontent-sea1-1.cdninstagram.com/vp/22719a292fe21b2298c106e2b17bf18f/5C390EDF/t51.2885-15/sh0.08/e35/s640x640/34197599_218636018917845_4826062202836353024_n.jpg");
    }

}

