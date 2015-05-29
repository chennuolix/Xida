package com.chennuo.siida.xida;

import android.app.ActionBar;
import android.os.Bundle;

/**
 * Created by chennuo on 2015/5/10.
 */
public class MySiida extends BaseActivityForMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysiida_layout);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }




}
