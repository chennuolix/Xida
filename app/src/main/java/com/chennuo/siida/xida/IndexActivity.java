package com.chennuo.siida.xida;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chennuo on 2015/5/9.
 */
public class IndexActivity extends BaseActivity {

    private static final int time = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index_layout);
        //AnalyticsConfig.enableEncrypt(true);
        Timer timer = new Timer();
        NewTask myTask = new NewTask();
        timer.schedule(myTask, time);
    }

    class NewTask extends TimerTask {
        public void run(){
            Intent intent = new Intent(IndexActivity.this,SampleActivity.class);
            startActivity(intent);
            ActivityCollector.deleteActivity(IndexActivity.this);
        }
    }
}
