package com.chennuo.siida.xida;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

/**
 * Created by chennuo on 2015/5/18.
 */
public class BaseActivityForMenu extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivityForMenu", getClass().getSimpleName());
        ActivityCollector.addActivity(this);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        PushAgent.getInstance(BaseActivityForMenu.this).onAppStart();
//        MobclickAgent.setSessionContinueMillis(60000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.back:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
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
