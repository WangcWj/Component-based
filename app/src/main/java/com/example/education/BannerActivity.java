package com.example.education;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import cn.example.wang.bannermodule.listener.BannerPagerClickListener;
import cn.example.wang.bannermodule.view.BannerViewLayout;
import cn.router.werouter.annotation.Router;

@Router(path = "native://BannerActivity")
public class BannerActivity extends AppCompatActivity {

    BannerViewLayout bannerView;
    List<Integer> recLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        bannerView = findViewById(R.id.bannerView);
        recLists = new ArrayList<>();

        recLists.add(R.mipmap.timg);
        recLists.add(R.mipmap.b);
        recLists.add(R.mipmap.c);
        recLists.add(R.mipmap.d);
        recLists.add(R.mipmap.f);
        initBannerView(recLists);

    }

    private void initBannerView(List<?> imageurls) {


        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = dip2px(this, 300);
        bannerView
                .setViewPagerLayoutParams(layoutParams)
                .setViewpagerMargin(dip2px(this, 10))
                .setStartPosition(0)
                .setImageLoad(new BannerImageLoadImpl())
                .setImagUrls(imageurls)
                .autoPlay(false)
                .setOffscreenPageLimit(imageurls.size())
                .create();

    }

    public int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
