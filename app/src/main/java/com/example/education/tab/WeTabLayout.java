package com.example.education.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/1/13
 */
public class WeTabLayout extends HorizontalScrollView {

    public WeTabLayout(Context context) {
        super(context, null, 0);
    }

    public WeTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public WeTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private Context mContext;
    private LinearLayout mTabContainer;

    private void initView(Context context, AttributeSet attrs) {
        initScrollView();
        mContext = context;
        mTabContainer = new LinearLayout(context);
        addView(mTabContainer);
    }

    private void initScrollView() {
        //设置滚动视图是否可以伸缩其内容以填充视口
        setFillViewport(true);
        //重写onDraw方法,需要调用这个方法来清除flag
        setWillNotDraw(false);
        setClipChildren(false);
        setClipToPadding(false);
    }


}
