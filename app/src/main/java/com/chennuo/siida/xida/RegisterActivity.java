package com.chennuo.siida.xida;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chennuo on 2015/5/26.
 */
public class RegisterActivity extends BaseActivityForMenu {


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private boolean isExist = false;
    private String phone_num;
    private String register_num;
    private String register_num_real;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        Button button_gain = (Button) findViewById(R.id.gain_click);   //获取验证码按钮
        Button button_verificate = (Button) findViewById(R.id.verificate_click);    //验证按钮
        final EditText editText_phnum = (EditText) findViewById(R.id.register_phnum);     //获取电话号码
        final EditText editText_register = (EditText) findViewById(R.id.verificate_num);
        final SharedPreferences pref = getSharedPreferences("user_info", MODE_PRIVATE);
        final RequestQueue mQueue = Volley.newRequestQueue(this);



        /*
        *   新建post的StringRequest。
        * */
        final StringRequest stringRequest_post = new StringRequest(Request.Method.POST,"https://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RegisterActivity-post", "response -> " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG",error.getMessage(),error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //在这里设置我们的post参数
                Map<String,String> map = new HashMap<String,String>();
                phone_num = editText_phnum.getText().toString();
                Log.d("RegisterActivity-phnum",phone_num);
                map.put("phone_number",phone_num);
                return super.getParams();
            }
        };




        /*
               从服务器获得验证码
        */
        final StringRequest stringRequest_get = new StringRequest("https://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        register_num_real = s;
                        Log.d("Real_register_num", s);
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("RegisterActivity", volleyError.getMessage(),volleyError);
            }
        });



        /*
        *      设置“获取验证码”按钮的点击事件——发送post请求，get请求
        */
        button_gain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQueue.add(stringRequest_post);

                mQueue.add(stringRequest_get);
            }
        });


        /*
               设置“验证”按钮的点击事件——验证
        */
        button_verificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_num = editText_register.getText().toString();
                Log.d("验证码:",register_num);
                editor = pref.edit();
                if(register_num.equals(register_num_real)) {
                    editor.putBoolean("isExist", true);
                    editor.putString("phone_num", phone_num);
                    Log.d("RegisterActivity", "验证码正确");
                    Intent intent = new Intent(RegisterActivity.this,MySiida.class);
                    startActivity(intent);
                } else {
                    editor.putBoolean("isExist",false);
                    Toast.makeText(RegisterActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                }
                editor.commit();
            }
        });


    }
}
