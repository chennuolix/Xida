package com.chennuo.siida.xida;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by chennuo on 2015/5/16.
 */
public class VideoAdapter extends CommonAdapter<Video> {

    public VideoAdapter(Context context, List<Video> datas,int layoutId){
        super(context, datas,layoutId);
    }


    @Override
    public void convert(ViewHolder holder, Video video) {
        ((ImageView)holder.getView(R.id.video_image)).setImageResource(video.getImageId());
//        ((TextView)holder.getView(R.id.video_name)).setText(video.getName());
    }

}
