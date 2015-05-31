package com.chennuo.siida.xida;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

/**
 * Created by chennuo on 2015/5/9.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", getClass().getSimpleName());
        ActivityCollector.addActivity(this);
        MobclickAgent.updateOnlineConfig(this);
        MobclickAgent.setDebugMode(true);
        PushAgent.getInstance(BaseActivity.this).onAppStart();
//        MobclickAgent.setSessionContinueMillis(60000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG","onResume");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG","onPause");
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /**
     *
     * @param url
     * @param l
     */
    protected void get(String url, RequestListener l) {
        get(url, null, l);
    }

    /**
     *
     * @param url
     * @param params
     * @param l
     */
    protected void get(String url, RequestParams params, RequestListener l) {
        RequestManager.get(url, this, params, l);
    }

    /**
     *
     * @param url
     * @param l
     */
    protected void post(String url, RequestListener l) {
        post(url, null, l);
    }

    /**
     *
     * @param url
     * @param params
     * @param l
     */
    protected void post(String url, RequestParams params, RequestListener l) {
        RequestManager.post(url, this, params, l);
    }
}