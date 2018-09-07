package com.android.sooz.async;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.time)
    TextView time;

    protected long startTime;
    protected long pausedTime;
    boolean isTiming;

    protected long lastTimeStamp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        reset();

        //see lecture at 4:32 - 33
        ImageDownloadTask getImage = new ImageDownloadTask(imageView);
        getImage.execute ("http://5tephen.com/moon");
    }

    @OnClick(R.id.reset)
    public void reset(){

        time.setText("0.000");
        isTiming = false;
        pausedTime = 0;

    }
}

