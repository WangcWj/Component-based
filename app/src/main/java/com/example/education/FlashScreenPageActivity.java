package com.example.education;


import android.util.Log;
import android.widget.ImageView;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.education.tab.WeTabLayout;

import java.util.Map;

import cn.education.base_res.base.BaseActivity;
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

    BaseWebView webView;


    @Override
    protected void realCreate() {
        String pic = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1567702323229&di=9b5a06ad29826896703020479e7a5593&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201703%2F30%2F20170330153834_K3uHf.jpeg";
//        Glide.with(this).load(pic).into(mLogo);
        Map<String, RouterBean> routerMap = WeRouter.getRouterMap();
//        Log.e("WANG", "FlashScreenPageActivity.realCreate." + routerMap.size());
        //webView = findViewById(R.id.webView);

       /*  webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.e("WANG", "FlashScreenPageActivity.onJsAlert" + message);
                return super.onJsAlert(view, url, message, result);
            }
        });


        webView.loadUrl("file:///android_asset/demo.html");*/


        WeTabLayout tabLayout = findViewById(R.id.dil_tablayout);
        ViewPager viewPager = findViewById(R.id.viewpager);
        String[] titles = {"移动", "四个字的", "小灵通", "NBA", "私密电影啊", "电影", "小知识", "篮球"};
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return CeshiFragment.newInstance();
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });

        tabLayout.setTabLayout(R.layout.item_sliding_tab_layout);
        tabLayout.setIndicatorBottomMargin(10);
        tabLayout.setTabFillContainer(false);
        tabLayout.attachToViewPager(viewPager, titles);

        /*
        int i = setIndex(0, 10);
        Log.e("cc.wang", "第一个数值是： " + i);
        int position = getPosition(i);
        Log.e("cc.wang", "position是： " + position);
        int page = getPage(i);
        Log.e("cc.wang", "page是： " + page);*/

        String str = " rtt min/avg/max/mdev = 67.315/81.839/116.577/20.197 ms";
        int i = str.lastIndexOf("=");
        String substring = str.substring(i);
        String[] strings = substring.split("/");
        for (int j = 0; j < strings.length; j++) {
            Log.e("cc.wang", "FlashScreenPageActivity.realCreate." + strings[j]);

        }


    }

    private int MASK_BITS = 9;

    private int MASK = 0x1ff;

    private int getPosition(int mask) {
        return ~(MASK << MASK_BITS) & mask;
    }

    private int getPage(int mask) {
        return mask >> MASK_BITS & MASK;
    }


    private int setIndex(int page, int index) {
        return page << MASK_BITS | index;
    }


    //M = 1000, CM = 900, XC = 90, IV = 4.
    public int romanToInt(String s) {
        int num = 0;
        //先取出最左边的字符，找到对应的数值。
        int pre = getValue(s.charAt(0));
        int current = pre;
        //遍历之后的字符，从1开始。
        for (int i = 1; i < s.length(); i++) {
            //下一个字符的数值取出。
            int cu = getValue(s.charAt(i));
            //后面的字符跟前面的字符是组合的。
            if (cu > pre) {
                current = cu - pre;
            } else {
                num += current;
                current = cu;
            }
            pre = cu;
        }
        num += current;
        return num;
    }

    private int getValue(Character character) {
        switch (character) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
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
