package cn.education.base_res.utils.click;

import android.view.View;

import java.util.Calendar;

/**
 * 就是过滤下点击事件
 * @author WANG
 * @date 2018/6/28
 */

public abstract class OnFilterClickListener implements View.OnClickListener {

    public  int MIN_CLICK_DELAY_TIME = 500;
    private long lastClickTime = 0;

    public OnFilterClickListener(int delayTime) {
        this.MIN_CLICK_DELAY_TIME = delayTime;
    }

    public OnFilterClickListener() {
    }

    protected abstract void mFilterClick(View v);
    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime >= MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            mFilterClick(v);
        }
    }

}
