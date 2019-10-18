package com.example.education;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import java.util.Map;

import butterknife.BindView;
import cn.education.base_res.base.BaseActivity;
import cn.education.base_res.utils.click.OnFilterClickListener;
import cn.router.api.router.WeRouter;
import cn.router.werouter.annotation.bean.RouterBean;

/**
 * 闪屏页
 *
 * @author WANG
 */
public class FlashScreenPageActivity extends BaseActivity {

    @BindView(R.id.icon_flash)
    ImageView mLogo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_flash_screen;
    }

    @Override
    protected void realCreate() {
        String pic = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1567702323229&di=9b5a06ad29826896703020479e7a5593&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201703%2F30%2F20170330153834_K3uHf.jpeg";
        Glide.with(this).load(pic).into(mLogo);
        Map<String, RouterBean> routerMap = WeRouter.getRouterMap();
        Log.e("WANG", "FlashScreenPageActivity.realCreate." + routerMap.size());
        mLogo.setOnClickListener(new OnFilterClickListener() {
            @Override
            protected void mFilterClick(View v) {
                WeRouter.getInstance().build("native://TestActivity").navigation(thisActivity());
            }
        });
    }
}
