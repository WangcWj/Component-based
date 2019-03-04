package com.chanven.lib.cptr.header.smileheader;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.R;
import com.chanven.lib.cptr.header.BaseHeaderView;
import com.chanven.lib.cptr.indicator.PtrIndicator;

/**
 * @author WANG
 */
public class SmileHeaderViewCopy extends BaseHeaderView {

    private SmlieAnimView mSmlieAnimView;

    public SmileHeaderViewCopy(Context context) {
        super(context);
        initViews(null);
    }

    public SmileHeaderViewCopy(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    public SmileHeaderViewCopy(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews(attrs);
    }

    protected void initViews(AttributeSet attrs) {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.refresh_header_smile, this);
        mSmlieAnimView = (SmlieAnimView) header.findViewById(R.id.ptr_classic_header_rotate_view_smlieanimview);
    }

    public void setSmileViewColor(int colorInt) {
        mSmlieAnimView.setSlimeColor(colorInt);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mSmlieAnimView.setVisibility(VISIBLE);
        startAnim();
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

    }

    void startAnim() {
        if (mSmlieAnimView.getVisibility() != VISIBLE) {
            return;
        }
        mSmlieAnimView.post(new Runnable() {
            @Override
            public void run() {
                mSmlieAnimView.startAnim();
            }
        });
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        mSmlieAnimView.setVisibility(INVISIBLE);
        mSmlieAnimView.stopAnim();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }

}
