package com.chennuo.siida.xida;

import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by chennuo on 2015/5/31.
 */
public class UpDateActivity extends BaseActivity{
    private String phone_num;
    private int isMessage;
    private String url = "http://www.baidu.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getSharedPreferences("uesr_info",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        post(url,);


    }

    public UpDateActivity(String phone_num,int isMessage) {
        this.isMessage = isMessage;
        this.phone_num = phone_num;
    }

}
