package com.example.education;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.education.lock.LockPatternView;

import java.util.List;
import java.util.Map;

import cn.router.api.router.WeRouter;
import cn.router.werouter.annotation.Router;
import cn.router.werouter.annotation.bean.RouterBean;

/**
 * @author WANG
 */
@Router(path = "native://TestActivity")
public class TestActivity extends AppCompatActivity  {

    LockPatternView lockPatternView;
    private static final long DELAYTIME = 600L;

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

        lockPatternView = findViewById(R.id.lock);
        lockPatternView.setOnPatternListener(patternListener);
        updateStatus(Status.DEFAULT);

    }
    private LockPatternView.OnPatternListener patternListener = new LockPatternView.OnPatternListener() {

        @Override
        public void onPatternStart() {
            lockPatternView.removePostClearPatternRunnable();
        }

        @Override
        public void onPatternComplete(List<LockPatternView.Cell> pattern) {
            if (pattern != null) {
                lockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
            }
        }
    };

    /**
     * 更新状态
     *
     * @param status
     */
    private void updateStatus(Status status) {
        switch (status) {
            case DEFAULT:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case ERROR:
                lockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
                lockPatternView.postClearPatternRunnable(DELAYTIME);
                break;
            case CORRECT:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            default:
        }
    }

    private enum Status {
        //默认的状态
        DEFAULT(R.string.gesture_default, R.color.grey_a5a5a5),
        //密码输入错误
        ERROR(R.string.gesture_error, R.color.red_f4333c),
        //密码输入正确
        CORRECT(R.string.gesture_correct, R.color.grey_a5a5a5);

        private Status(int strId, int colorId) {
            this.strId = strId;
            this.colorId = colorId;
        }

        private int strId;
        private int colorId;
    }



}
