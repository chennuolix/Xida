package com.chennuo.siida.xida;

/**
 * Created by chennuo on 2015/5/31.
 */
import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
