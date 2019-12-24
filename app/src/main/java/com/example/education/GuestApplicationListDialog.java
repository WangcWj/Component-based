package cn.fengrong.blinddate.ui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.mifengkong.frtools.http.request.callback.FRBaseObserver;
import com.mifengkong.frtools.inputmethod.FRView;
import com.mifengkong.frtools.ui.adapter.recyclerview.CusItemClickListener2;
import com.mifengkong.frtools.util.FRDisplayMetrics;

import java.util.ArrayList;
import java.util.List;

import cn.fengrong.blinddate.R;
import cn.fengrong.blinddate.bean.live.ApplicationListBean;
import cn.fengrong.blinddate.bean.live.GuestInfoBean;
import cn.fengrong.blinddate.model.blinddata.GuestApplicationListModel;
import cn.fengrong.blinddate.ui.dialog.adapter.ViewPagerAdapter;
import cn.fengrong.blinddate.ui.dialog.ui.ApplyForListPageView;
import cn.fengrong.blinddate.ui.dialog.ui.AutoHeightViewPager;
import cn.fengrong.blinddate.ui.dialog.ui.GuestDialogListPage;
import cn.fengrong.blinddate.ui.dialog.ui.TabMarginBean;
import cn.fengrong.blinddate.ui.live.listener.DialogRequestDataCallBack;
import cn.fengrong.blinddate.ui.recharge.widget.tablayout.SlidingTabLayout;
import io.reactivex.disposables.Disposable;


/**
 * @author jiangyongxing
 * 描述：嘉宾申请dialog
 */

public class GuestApplicationListDialog extends BaseRequestNetDialog<ApplicationListBean> {

    private Builder mBuilder;
    private int roomId;

    public GuestApplicationListDialog(@NonNull Context context) {
        super(context);
    }

    public GuestApplicationListDialog(@NonNull Context context, int themeResId,Builder builder) {
        super(context, themeResId);
        this.mBuilder = builder;
    }

    protected GuestApplicationListDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        dispose();
        if (roomId == 0 || callback == null) {
            return;
        }
        callback.onReady();
        GuestApplicationListModel.getInstance().execute(String.valueOf(roomId), new FRBaseObserver<ApplicationListBean>() {

            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
                mDisposable = d;
            }

            @Override
            protected void onBaseNext(ApplicationListBean data) {
                callback.onSuccess(data);
            }

            @Override
            protected void onBaseError(Throwable t) {
                super.onBaseError(t);
                callback.onError();
            }
        });
    }

    public void release() {
        if (mBuilder.maleGuestApplicationList != null) {
            mBuilder.maleGuestApplicationList.releaseTimer();
        }

        if (mBuilder.femaleGuestApplicationList != null) {
            mBuilder.femaleGuestApplicationList.releaseTimer();
        }
    }

    public static class Builder {
        private Context context;
        private GuestApplicationListDialog mDialog;
        private SlidingTabLayout mTabLayout;
        private AutoHeightViewPager mViewPager;
        private ViewPagerAdapter mPagerAdapter;
        private String[] title = {"申请上麦（男)", "申请上麦（女)"};
        private List<View> mViews = new ArrayList<>();
        private CusItemClickListener2<GuestInfoBean> mOnItemClickListener;

        //private GuestDialogListPage maleGuestApplicationList;
        //private GuestDialogListPage femaleGuestApplicationList;

        ApplyForListPageView maleGuestApplicationList;
        ApplyForListPageView femaleGuestApplicationList;



        public Builder(Context context) {
            this.context = context;
        }


        public Builder setDialogItemClickListener(CusItemClickListener2<GuestInfoBean> listener) {
            this.mOnItemClickListener = listener;
            return this;
        }

        /**
         * Create the custom dialog
         */
        public GuestApplicationListDialog create() {
            mDialog = new GuestApplicationListDialog(context, R.style.DialogBackground,this);
            Window window = mDialog.getWindow();
            window.setContentView(R.layout.dialog_guest_application_list);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mDialog.setCanceledOnTouchOutside(true);
            mTabLayout = window.findViewById(R.id.dil_tablayout);
            mViewPager = window.findViewById(R.id.dil_viewpager);
            window.findViewById(R.id.dgal_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });

            initTab();
            mDialog.setDataRequestSuccessCallback(new DialogRequestDataCallBack<ApplicationListBean>() {
                @Override
                public void onSuccess(ApplicationListBean data) {
                    if (data != null) {
                        if (maleGuestApplicationList != null) {
                            maleGuestApplicationList.setData(data.getBoy(),1);
                        }

                        if (femaleGuestApplicationList != null) {
                            femaleGuestApplicationList.setData(data.getGirl(),2);
                        }
                    }
                }

                @Override
                public void onReady() {

                }

                @Override
                public void onError() {

                }
            });

            return mDialog;
        }


        private void initTab() {
            int dp25 = FRDisplayMetrics.dp2px(context, 25.5f);
            int dp7 = FRDisplayMetrics.dp2px(context, 7);

            //maleGuestApplicationList = FRView.inflateLayout(context, R.layout.layout_page_guest_application_list, mViewPager, false);
            //femaleGuestApplicationList = FRView.inflateLayout(context, R.layout.layout_page_guest_application_list, mViewPager, false);

            maleGuestApplicationList = FRView.inflateLayout(context, R.layout.layout_page_view_apply_for_list, mViewPager, false);
            femaleGuestApplicationList = FRView.inflateLayout(context, R.layout.layout_page_view_apply_for_list, mViewPager, false);


            mViews.add(maleGuestApplicationList);
            mViews.add(femaleGuestApplicationList);
            if (maleGuestApplicationList != null) {
                maleGuestApplicationList.setItemType(GuestDialogListPage.ITEM_TYPE_IN_ROOM);
                maleGuestApplicationList.setOnItemClickListener(mOnItemClickListener);
            }
            if (femaleGuestApplicationList != null) {
                femaleGuestApplicationList.setItemType(GuestDialogListPage.ITEM_TYPE_IN_ROOM);
                femaleGuestApplicationList.setOnItemClickListener(mOnItemClickListener);
            }


            mPagerAdapter = new ViewPagerAdapter();
            mPagerAdapter.setPages(mViews);
            mPagerAdapter.setTitles(title);
            mViewPager.setAdapter(mPagerAdapter);


            //mTabLayout的Tab容器是个LinearLayout,要么所有的Tab平分布局,要么就是按照LinearLayout的Gravity排列
            //所以 这里让LinearLayout的Gravity为CENTER,然后设置每个Tab的margin,去达到UI要的效果.

            //第一个Tab的margin
            mTabLayout.addTabMarginInfo(0, new TabMarginBean(0, 0, dp25, dp7));
            //第二个Tab的margin
            mTabLayout.addTabMarginInfo(1, new TabMarginBean(dp25, 0, 0, dp7));
            //设置LinearLayout的Gravity
            mTabLayout.setContainerGravity(Gravity.CENTER);
            mTabLayout.setViewPager(mViewPager);
        }

    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }


}
