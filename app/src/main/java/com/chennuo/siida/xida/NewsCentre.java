package com.chennuo.siida.xida;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chennuo on 2015/5/18.
 */
public class NewsCentre extends BaseActivityForMenu {

    private ListView mListViewNews;
    private List<News> mNews;
    private NewsAdapter newsAdapter;
    private boolean ismessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newscentre_layout);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        initNews();
        initViewnews();
    }

    private void initViewnews() {
        mListViewNews = (ListView) findViewById(R.id.list_of_news);
        mListViewNews.setAdapter(newsAdapter);
    }
    private void initNews() {
        mNews = new ArrayList<News>();
        News news1 = new News("消息1");
        mNews.add(news1);
        News news2 = new News("消息2");
        mNews.add(news2);
        News news3 = new News("消息3");
        mNews.add(news3);
        News news4 = new News("消息4");
        mNews.add(news4);
        News news5 = new News("消息5");
        mNews.add(news5);
        newsAdapter = new NewsAdapter(this,mNews,R.layout.news_list);
    }
}
