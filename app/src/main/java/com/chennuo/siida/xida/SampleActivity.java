package com.chennuo.siida.xida;

/**
 * Created by chennuo on 2015/5/9.
 */

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;

import java.util.ArrayList;
import java.util.List;


public class SampleActivity extends BaseActivity {



    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private boolean drawerArrowColor;


    private ListView mListView;
    private List<Video> mVideo;
    private VideoAdapter videoAdapter;


    private SharedPreferences pref ;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_layout);
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        PushAgent mPushAgent = PushAgent.getInstance(SampleActivity.this);
        mPushAgent.enable();
        String device_token = UmengRegistrar.getRegistrationId(this);
        Log.d("device_token",device_token);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);

        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                drawerArrow, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        String[] values = new String[]{
                "                  ",
                "           我的悉达",
                "           消息中心",
                "             设置",
                "           我要讲课",
                "           我要吐槽",
                "           关于悉达",
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.menu_list, values);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        ;
                        break;
                    case 1: //我的悉达
                        mDrawerToggle.setAnimateEnabled(false);
                        drawerArrow.setProgress(1f);
                        boolean isExist = pref.getBoolean("isExist", false);  //判断是否已经验证过了此设备
                        if (!isExist) {
                            Intent intent = new Intent(SampleActivity.this, RegisterActivity.class);
                            startActivity(intent);
                            Log.d("IndexActivity", "To RegisterActivity");
                        } else {
                            Intent intent1 = new Intent(SampleActivity.this, MySiida.class);
                            startActivity(intent1);
                            Log.d("IndexActivity", "To SampleActivity");
                        }
                        break;
                    case 2:  //消息中心
                        mDrawerToggle.setAnimateEnabled(false);
                        drawerArrow.setProgress(0f);
                        Intent intent1 = new Intent(SampleActivity.this, NewsCentre.class);
                        startActivity(intent1);
                        break;
                    case 3:   //设置
                        mDrawerToggle.setAnimateEnabled(true);
                        mDrawerToggle.syncState();
                        break;
                    case 4:   //我要讲课
//                        if (drawerArrowColor) {
//                            drawerArrowColor = false;
//                            drawerArrow.setColor(getResources().getColor(R.color.white));
//                        } else {
//                            drawerArrowColor = true;
//                            drawerArrow.setColor(getResources().getColor(R.color.drawer_arrow_second_color));
//                        }
//                        mDrawerToggle.syncState();
//                        break;
                        Intent signupIntent = new Intent(SampleActivity.this, SignupActivity.class);
                        startActivity(signupIntent);
//                        Intent signupIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/IkiMuhendis/LDrawer"));
//                        startActivity(signupIntent);
                        break;
                    case 5:   //我要吐槽
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/IkiMuhendis/LDrawer"));
                        startActivity(browserIntent);
                        break;
                    case 6:  //关于悉达
                        new AlertDialog.Builder(SampleActivity.this)
                                .setTitle("关于悉达")
                                .setMessage("脏总和丛丛新婚快乐！")
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialoginterface, int i) {
                                                ;
                                            }
                                        }).show();
                        break;
                    case 7:
                        String appUrl = "https://play.google.com/store/apps/details?id=" + getPackageName();
                        Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appUrl));
                        startActivity(rateIntent);
                        break;
                }
            }
        });



        initVideo();    //初始化视频列表
        initView();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(SampleActivity.this, VideoPlayerActivity.class);
                        startActivity(intent);
                        break;
                    case 2:

                        break;








                    /*   视频列表点击事件   */


                }
            }
        });
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.list_of_video);
        mListView.setAdapter(videoAdapter);
    }

    private void initVideo(){
        mVideo = new ArrayList<Video>();
        Video video1 = new Video(R.drawable.yia);
        mVideo.add(video1);
        Video video2 = new Video(R.drawable.era);
        mVideo.add(video2);
        Video video3 = new Video(R.drawable.sana);
        mVideo.add(video3);
        Video video4 = new Video(R.drawable.sia);
        mVideo.add(video4);
        Video video5 = new Video(R.drawable.wua);
        mVideo.add(video5);
        Video video6 = new Video(R.drawable.liua);
        mVideo.add(video6);
        Video video7 = new Video(R.drawable.qia);
        mVideo.add(video7);
        Video video8 = new Video(R.drawable.baa);
        mVideo.add(video8);
        Video video9 = new Video(R.drawable.jiua);
        mVideo.add(video9);
        Video video10 = new Video(R.drawable.shia);
        mVideo.add(video10);
        Video video11 = new Video(R.drawable.shiyia);
        mVideo.add(video11);
        Video video12 = new Video(R.drawable.shiera);
        mVideo.add(video12);
        Video video13 = new Video(R.drawable.shisana);
        mVideo.add(video13);
        Video video14 = new Video(R.drawable.shisia);
        mVideo.add(video14);
        videoAdapter = new VideoAdapter(this,mVideo,R.layout.video_list);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
