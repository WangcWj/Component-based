package cn.education.base_res.base;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import cn.education.base_res.utils.ActivityStackManager;


/**
 * 1.处理网络请求,取消订阅.
 * 2.BufferKnife,EventBus等.
 * 3.根据具体的业务去封装.
 *
 * @author WANG
 * @date 2018/7/23
 */

public abstract class BaseActivity extends AppCompatActivity {


    protected BaseActivity thisActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        setContentView(layoutId);
        ActivityStackManager.getInstance().addActivity(this);
        realCreate();
    }

    protected abstract int getLayoutId();

    protected abstract void realCreate();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().removeActivity(this);
    }
}
