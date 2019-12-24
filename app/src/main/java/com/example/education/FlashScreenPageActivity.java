package com.example.education;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.education.base_res.base.BaseActivity;
import cn.education.base_res.jsbridge.BaseWebView;
import cn.education.base_res.utils.click.OnFilterClickListener;
import cn.router.api.router.WeRouter;
import cn.router.werouter.annotation.bean.RouterBean;

/**
 * 闪屏页
 *
 * @author WANG
 */
public class FlashScreenPageActivity extends BaseActivity {

    ImageView mLogo;

    private boolean interceptor = false;
    private volatile int counnt = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_flash_screen;
    }

    @Override
    protected boolean userButterKnife() {
        return false;
    }

    BaseWebView webView;



    @Override
    protected void realCreate() {
        String pic = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1567702323229&di=9b5a06ad29826896703020479e7a5593&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201703%2F30%2F20170330153834_K3uHf.jpeg";
//        Glide.with(this).load(pic).into(mLogo);
        Map<String, RouterBean> routerMap = WeRouter.getRouterMap();
//        Log.e("WANG", "FlashScreenPageActivity.realCreate." + routerMap.size());
       /* webView = findViewById(R.id.webView);
        findViewById(R.id.btn).setOnClickListener(new OnFilterClickListener() {
            @Override
            protected void mFilterClick(View v) {
                webView.loadUrl("https://www.baidu.com");
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.e("WANG", "FlashScreenPageActivity.onJsAlert" + message);
                return super.onJsAlert(view, url, message, result);
            }
        });


        webView.loadUrl("file:///android_asset/demo.html");*/


        SlidingTabLayout tabLayout = findViewById(R.id.dil_tablayout);
        ViewPager viewPager = findViewById(R.id.viewpager);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return CeshiFragment.newInstance();
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        String[] titles = {"移动","联通","小灵通"};
        tabLayout.setContainerGravity(Gravity.CENTER);
        tabLayout.setViewPager(viewPager,titles);



    }

    public int reverse(int x) {
        boolean is = false;
        if (x < 0) {
            x = Math.abs(x);
            is = true;
        }
        String v = String.valueOf(x);
        StringBuilder sb = new StringBuilder();
        for (int i = (v.length() - 1); i >= 0; i--) {
            sb.append(v.charAt(i));
        }
        String string = sb.toString();
        try {
            int anInt = Integer.parseInt(string);
            Log.e("WANG", "FlashScreenPageActivity.reverse" + anInt);
            return is ? -anInt : anInt;
        } catch (Exception e) {
            return 0;
        }
    }


}
