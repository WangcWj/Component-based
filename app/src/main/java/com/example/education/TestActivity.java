package com.example.education;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Map;

import cn.router.api.router.WeRouter;
import cn.router.werouter.annotation.Router;
import cn.router.werouter.annotation.bean.RouterBean;

/**
 * @author WANG
 */
@Router(path = "native://TestActivity")
public class TestActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);
        Map<String, RouterBean> routerMap = WeRouter.getRouterMap();
        Log.e("WANG", "TestActivity.onCreate.size " + routerMap.size());

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeRouter.getInstance().build("native://LoginActivity").navigation(TestActivity.this);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeRouter.getInstance().build("native://MainActivity").navigation(TestActivity.this);
            }
        });



    }


}
