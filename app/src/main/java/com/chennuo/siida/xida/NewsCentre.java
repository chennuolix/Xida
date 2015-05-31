package com.chennuo.siida.xida;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chennuo on 2015/5/18.
 */
public class NewsCentre extends BaseActivityForMenu {

    private ListView mListViewNews;
    private List<News> mNews;
    private NewsAdapter newsAdapter;
    private int message_num = 0;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newscentre_layout);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        SharedPreferences pref = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("Message_num",0);    //将消息数量初始化为零
        String idMessage[] = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
        message_num = pref.getInt("Message_num",0);
        post("http://www.baidu.com", requestListener());

        initNews();
        initViewnews();

        for(i=0;i<10;i++){
            if(idMessage[i].equals("0")){
                break;
            } else {
                News news = new News();
                mNews.add(news);
            }
        }



    }

    private RequestListener requestListener() {
        return new RequestListener() {

            @Override
            public void requestSuccess(String string) {

            }

            @Override
            public void requestError(VolleyError e) {
                e.printStackTrace();
            }
        };
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
