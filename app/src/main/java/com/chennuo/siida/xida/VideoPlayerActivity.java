package com.chennuo.siida.xida;

import android.os.Bundle;

/**
 * Created by chennuo on 2015/5/29.
 */
public class VideoPlayerActivity extends BaseActivityForMenu {


    private String mUrl;


    protected VideoPlayerActivity(String url) {
        this.mUrl = url;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);

    }
}
