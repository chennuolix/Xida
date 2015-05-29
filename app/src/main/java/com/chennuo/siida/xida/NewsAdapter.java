package com.chennuo.siida.xida;

import android.content.Context;

import java.util.List;

/**
 * Created by chennuo on 2015/5/18.
 */
public class NewsAdapter extends CommonAdapter<News> {


    public NewsAdapter(Context context,List<News> datas,int layoutId) {
        super(context,datas,layoutId);
    }


    @Override
    public void convert(ViewHolder holder, News news) {
        holder.setText(R.id.news,news.getNews());
    }
}
