package cn.education.base_res.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by WANG on 2018/7/23.
 * 1.{@link BaseFragment#needCacheView()} 设置是否缓存当前的View.
 *   解决ViewPager切换的时候不设置缓存的个数的时候,Fragment会多次调用onCreate()方法.
 * 2.不想要懒加载的话那请求网络可以放到 {@link BaseFragment#pageInitData()}.
 * <p>
 * 3.懒加载+只加载一次的话 {@link BaseFragment#pageLoadDataOnce()}.
 * 具体情况还是要看ViewPager缓存的个数{@link androidx.viewpager.widget.ViewPager#setOffscreenPageLimit(int)}
 * 如果不设置缓存全部的话,那每次创建Fragment的时候都会调用一次.
 * <p>
 * 4.每次页面可见的时候都调用(不是onResume的效果这是切换到当前Fragment){@link BaseFragment#pageLoadDataOnVisible()}
 * 当设置缓存全部的话,该方法实用.如果没设置缓存的话,效果类似{@link BaseFragment#pageLoadDataOnce()}.
 * <p>
 * 4.{@link BaseFragment#pageOnResume()}对ViewPager的缓存个数没啥影响.
 */
public abstract class BaseFragment extends Fragment {

    private View mRootView;

    protected Activity mActivity;

    protected Context mContext;

    private boolean mViewRealyCreate = false;

    private boolean mFirstLoad = false;

    private boolean mUserVisible = false;

    private boolean mPageStopOrPause = false;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (Activity) context;
    }

    protected boolean needCacheView(){
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(!needCacheView() || mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        }else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if(null != parent){
              parent.removeView(mRootView);
            }
        }
        initView(mRootView);
        mFirstLoad = true;
        mViewRealyCreate = true;
        pageInitData();
        pageVisible();
        initListener();
        return mRootView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            visible();
        } else {
            hide();
        }
    }

    /**
     * 手动调用 show 和 hidden 的时候会调用
     * getFragmentManager().beginTransaction().hide(firstFragment)
     * .show(firstFragment);
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            hide();
        } else {
            visible();
        }
    }

    private void hide() {
        mUserVisible = false;
        pageVisible();
    }

    private void visible() {
        mUserVisible = true;
        pageVisible();
    }

    private void pageVisible() {
        if (mViewRealyCreate && mUserVisible) {
            pageLoadDataOnVisible();
            if (mFirstLoad) {
                mFirstLoad = false;
                pageLoadDataOnce();
            }
        }
    }

    /**
     * 页面可见的时候调用 只会执行一次
     * 不过执行的次数跟ViewPage设置的缓存数有差异,的那个页面被回收的话 还是会走一次
     */
    protected void pageLoadDataOnce() {

    }


    /**
     * 当页面可见的时候就会调用
     */
    protected void pageLoadDataOnVisible() {

    }

    /**
     * 普通加载数据 再OnCreate里面
     */
    protected void pageInitData() {

    }

    /**
     * 每次当页面可见并且执行onResume的时候调用
     */
    protected void pageOnResume() {

    }

    public View findViewById(int layoutId) {
        View view = findView(layoutId);
        if (null == view){
            throw new NullPointerException(" 找不到对应  " + layoutId + "  的View");
        }
        return view;
    }

    private View findView(@IdRes int id) {
        if (null == mRootView){
            throw new NullPointerException(this + "   mRootView Is Null !!!");
        }
        View view = mRootView.findViewById(id);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mUserVisible && mViewRealyCreate){
            pageOnResume();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View rootView);

    protected abstract void initListener();


}
