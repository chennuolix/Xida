package com.chennuo.siida.xida;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by chennuo on 2015/5/26.
 */
public class RegisterActivity extends BaseActivityForMenu {



    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private boolean isExist = false;
    private String phone_num;
    private String register_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        Button button_gain = (Button) findViewById(R.id.gain_click);   //获取验证码按钮
        Button button_verificate = (Button) findViewById(R.id.verificate_click);    //验证按钮
        EditText editText_phnum = (EditText) findViewById(R.id.register_phnum);     //获取电话号码
        final EditText editText_register = (EditText) findViewById(R.id.verificate_num);
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        RequestQueue mQueue = Volley.newRequestQueue(this);


        /*
             从服务器获得验证码
        */
        StringRequest stringRequest = new StringRequest("传输验证码的ip",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        register_num = s;
                        Log.d("RegisterActiviry", "已收到验证码");
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("RegisterActivity", volleyError.getMessage(),volleyError);
                    }
                });
        button_verificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_num = editText_register.getText().toString();
                Log.d("验证码:",register_num);
                editor = pref.edit();
                if(register_num.equals("123456")) {
                    editor.putBoolean("isExist", true);
                    editor.putString("phone_num", "15988826143");
                    Log.d("RegisterActivity", "验证码正确");
                    Intent intent = new Intent(RegisterActivity.this,MySiida.class);
                    startActivity(intent);
                } else {
                    editor.putBoolean("isExist",false);
                }
                editor.commit();

            }
        });


    }
}
