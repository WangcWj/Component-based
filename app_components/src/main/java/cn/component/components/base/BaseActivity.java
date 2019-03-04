package cn.component.components.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.component.components.utils.ActivityStackManager;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by WANG on 2018/7/23.
 * 1.处理网络请求,取消订阅.
 * 2.BufferKnife,EventBus等.
 * 3.根据具体的业务去封装.
 */

public abstract class BaseActivity extends AppCompatActivity  {


    protected BaseActivity thisActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        setContentView(layoutId);
        ActivityStackManager.getInstance().addActivity(this);
        initView();
        initData();
        initListener();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    protected void onDestroy() {
        ActivityStackManager.getInstance().removeActivity(this);
        super.onDestroy();
    }
}
