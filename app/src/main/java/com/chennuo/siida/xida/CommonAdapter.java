package com.chennuo.siida.xida;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by chennuo on 2015/5/16.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected int layoutId;

    public CommonAdapter(Context context,List<T> datas ,int layoutId){
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.layoutId = layoutId;
    }

    public int getCount(){
        return mDatas.size();
    }

    public T getItem(int position){
        return mDatas.get(position);
    }
    public long getItemId(int position){
        return position;
    }

    public View getView(int position,View convertView,ViewGroup parent){
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, layoutId, position);
        convert(holder, getItem(position));
        return holder.getmConvertView();
    }

    public abstract void convert(ViewHolder holder,T t);
}
