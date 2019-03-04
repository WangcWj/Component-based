package com.chanven.lib.cptr.header;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chanven.lib.cptr.PtrUIHandler;

/**
 *
 * @author jiangyongxing
 * @date 2018/4/10
 * 描述：
 */

public abstract class BaseHeaderView extends FrameLayout implements PtrUIHandler {

    public BaseHeaderView(Context context) {
        super(context);
    }

    public BaseHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
