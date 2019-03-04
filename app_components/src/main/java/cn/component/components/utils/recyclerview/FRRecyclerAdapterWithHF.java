package cn.component.components.utils.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import java.util.ArrayList;
import java.util.List;

/**
 * create to :一个帮助类,帮助RecyclerView.Adapter实现添加头尾布局的功能,无数据显示"无数据"界面.
 * 基本使用:
 * <p>
 * RecyclerView.Adapter adapter = new RecyclerView.Adapter(); <br>
 * FRRecyclerAdapterWithHF mRecyclerAdapterWithHF = new FRRecyclerAdapterWithHF(adapter);<br>
 * mRecyclerView.setAdapter(mRecyclerAdapterWithHF);<br>
 * mRecyclerAdapterWithHF.addHeader(view);<br>
 * 基础的功能:
 * <p>
 * {@link #addHeader(View)}  : 添加头布局.
 * {@link #addHeader(int, View)} : 按照index添加头布局.
 * {@link #addFooter(View)}  : 添加尾部局.
 * {@link #addFooter(int, View)} : 按照index添加尾布局.
 * {@link #removeHeader(View)}  : 移除头布局.
 * {@link #removeFooter(View)}  : 移除尾部局.
 * {@link #containerFootView(View)}  : 是否包含了该尾部局.
 * {@link #containerHeader(View)}  : 是否包含了该头布局.
 * <p>
 * 注:该类支持无数据展示"空布局"的功能,但是不兼容头尾布局,两者不可兼得.
 * {@link #setUseEmptyView(boolean)} : 启用无数据展示空布局的功能.
 * {@link #setEmptyView(View)}  : 设置没数据时的展示的空布局.
 *
 * @author WANG
 */
public class FRRecyclerAdapterWithHF extends RecyclerView.Adapter<ViewHolder> implements FRBaseRecyclerAdapter.DataEmptyListener {

    public static final int TYPE_MANAGER_OTHER = 0;
    public static final int TYPE_MANAGER_LINEAR = 1;
    public static final int TYPE_MANAGER_GRID = 2;
    public static final int TYPE_MANAGER_STAGGERED_GRID = 3;

    public static final int TYPE_HEADER = 7898;
    public static final int TYPE_FOOTER = 7899;

    private static final String EMPTY_LAYOUT_TAG = "empty_layout_value";

    /**
     * 原始数据Adapter
     */
    private RecyclerView.Adapter<ViewHolder> mAdapter;

    private List<View> mHeaders = new ArrayList<View>();
    private List<View> mFooters = new ArrayList<View>();

    private View mEmptyView;

    private int mManagerType;

    private boolean useEmptyView = false;


    public <T extends RecyclerView.Adapter> FRRecyclerAdapterWithHF(T adapter) {
        super();
        this.mAdapter = adapter;
        adapter.registerAdapterDataObserver(adapterDataObserver);
    }

    /**
     * 是否启动无数据的时候显示空布局的功能 true 启用
     *
     * @param useEmptyView
     */
    public void setUseEmptyView(boolean useEmptyView) {
        if (mAdapter == null) {
            return;
        }
        this.useEmptyView = useEmptyView;
        if (useEmptyView && mAdapter instanceof FRBaseRecyclerAdapter) {
            ((FRBaseRecyclerAdapter) mAdapter).setEmptyListener(this);
        }
    }

    /**
     * 设置空布局
     *
     * @param mEmptyView
     */
    public void setEmptyView(View mEmptyView) {
        this.mEmptyView = mEmptyView;
       // this.mEmptyView.setTag(R.id.empty_layout, EMPTY_LAYOUT_TAG);
    }


    /**
     * 返回布局manager的类型.
     *
     * @return
     */
    public int getManagerType() {
        return mManagerType;
    }

    /**
     * 设置原始Adapter的Manager类型,这里主要对瀑布流(StaggeredGridLayoutManager)布局有用.
     * 如果是瀑布流请务必设置该值.
     *
     * @param mManagerType
     */
    public void setManagerType(int mManagerType) {
        this.mManagerType = mManagerType;
    }

    /**
     * 获取每个ItemView的类型:
     * 1.TYPE_HEADER.
     * 2.TYPE_FOOTER.
     * 3.def.
     *
     * @param position
     * @return
     */
    public int getItemViewTypeHF(int position) {
        return mAdapter.getItemViewType(position);
    }

    /**
     * 获取头布局的总个数
     *
     * @return
     */
    public int getHeadSize() {
        return mHeaders.size();
    }

    /**
     * 获取尾部局的总个数
     *
     * @return
     */
    public int getFootSize() {
        return mFooters.size();
    }

    /**
     * 添加一个头布局,默认追加再所有的头部局后面.
     *
     * @param header 要添加的尾部局
     */
    public void addHeader(View header) {
        if (!mHeaders.contains(header)) {
            mHeaders.add(header);
            // animate
            notifyItemInserted(mHeaders.size() - 1);
        }
    }

    /**
     * 根据下标添加一个头布局.
     *
     * @param index  插入的该下标要小于等于头布局集合的size.
     * @param header 插入的头布局.
     */
    public void addHeader(int index, View header) {
        try {
            if (!mHeaders.contains(header) && index <= mHeaders.size()) {
                mHeaders.add(index, header);
                // animate
                notifyItemInserted(mHeaders.size() - 1);
            }
        } catch (Exception e) {

        }
    }

    /**
     * 添加一个尾布局,默认追加再所有的尾部局后面.
     *
     * @param footer 要添加的尾部局
     */
    public void addFooter(View footer) {
        if (!mFooters.contains(footer)) {
            mFooters.add(footer);
            // animate
            notifyItemInserted(mHeaders.size() + getItemCountHF() + mFooters.size() - 1);
        }
    }

    /**
     * 根据下标添加一个尾布局.
     *
     * @param index  插入的该下标要小于等于尾布局集合的size.
     * @param footer 插入的尾布局.
     */
    public void addFooter(int index, View footer) {
        if (!mFooters.contains(footer) && index <= mFooters.size()) {
            mFooters.add(index, footer);
            // animate
            notifyItemInserted(mHeaders.size() + getItemCountHF() + mFooters.size() - 1);
        }
    }

    /**
     * 移除特定的头布局
     *
     * @param header 要移除的头布局
     */
    public void removeHeader(View header) {
        if (mHeaders.contains(header)) {
            // animate
            notifyItemRemoved(mHeaders.indexOf(header));
            mHeaders.remove(header);
        }
    }

    /**
     * 移除一个尾布局
     *
     * @param footer 要移除的尾部局
     */
    public void removeFooter(View footer) {
        if (mFooters.contains(footer)) {
            // animate
            notifyItemRemoved(mHeaders.size() + getItemCountHF() + mFooters.indexOf(footer));
            mFooters.remove(footer);
        }
    }

    /**
     * 头布局中是否已经拥有了该头布局
     *
     * @param header
     * @return true 已经拥有  false
     */
    public boolean containerHeader(View header) {
        if (mHeaders == null && mHeaders.size() <= 0) {
            return false;
        }
        return mHeaders.contains(header);
    }

    /**
     * 头布局中是否已经拥有了该尾布局
     *
     * @param foot
     * @return true 已经拥有  false
     */
    public boolean containerFootView(View foot) {
        if (mFooters == null && mFooters.size() <= 0) {
            return false;
        }
        return mFooters.contains(foot);
    }

    /**
     * 获取原始的Adapter.
     *
     * @return
     */
    public RecyclerView.Adapter<ViewHolder> getOrgAdapter() {
        return mAdapter;
    }

    /**
     * 监听的原始Adapter数据变化.
     * 当此时并没有头尾布局的时候才显示空布局.
     *
     * @param empty = true 数据为空的时候. false 数据不为空.
     */
    @Override
    public void isEmpty(boolean empty) {
        if (!useEmptyView || mEmptyView == null) {
            return;
        }
        if (empty && mHeaders.size() == 0 && mFooters.size() == 0) {
            addHeader(mEmptyView);
        } else {
            removeHeader(mEmptyView);
        }
    }

    /**
     * 作为头尾布局的ViewHolder,父布局为FrameLayout.
     */
    public static class HeaderFooterViewHolder extends ViewHolder {
        FrameLayout base;

        public HeaderFooterViewHolder(View itemView) {
            super(itemView);
            base = (FrameLayout) itemView;
        }
    }

    /**
     * 根据输入的位置来判断该位置的item时是否是头布局
     *
     * @param position
     * @return true 是
     */
    private boolean isHeader(int position) {
        return (position < mHeaders.size());
    }

    /**
     * 根据输入的位置来判断该位置的item时是否是尾布局
     *
     * @param position
     * @return true 是
     */
    private boolean isFooter(int position) {
        return (position >= mHeaders.size() + getItemCountHF());
    }

    @Override
    public final long getItemId(int position) {
        return getItemIdHF(getRealPosition(position));
    }

    public long getItemIdHF(int position) {
        return mAdapter.getItemId(position);
    }

    @Override
    public final int getItemViewType(int position) {
        //多布局一样的判断逻辑
        //优先级 :  头布局   普通布局  尾部局 .
        if (isHeader(position)) {
            return TYPE_HEADER;
        } else if (isFooter(position)) {
            return TYPE_FOOTER;
        }
        //这边也就是验证下普通布局处理的正确性
        int type = getItemViewTypeHF(getRealPosition(position));
        if (type == TYPE_HEADER || type == TYPE_FOOTER) {
            throw new IllegalArgumentException("Item type cannot equal " + TYPE_HEADER + " or " + TYPE_FOOTER);
        }
        return type;
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        // 如果不是头尾布局那就直接调用原始 mAdapter.onCreateViewHolder
        if (type != TYPE_HEADER && type != TYPE_FOOTER) {
            ViewHolder vh = onCreateViewHolderHF(viewGroup, type);
            return vh;
        } else {
            // 如果是头尾布局的话,就创建HeaderFooterViewHolder.
            FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
            //创建的布局默认是WRAP_CONTENT,这里设置为MATCH_PARENT.
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                    .LayoutParams.WRAP_CONTENT));
            return new HeaderFooterViewHolder(frameLayout);
        }
    }

    public ViewHolder onCreateViewHolderHF(ViewGroup viewGroup, int type) {
        return mAdapter.onCreateViewHolder(viewGroup, type);
    }

    @Override
    public final void onBindViewHolder(final ViewHolder vh, int position) {
        //根据position去判断是否是头布局,当然也可以用getItemViewType.
        if (isHeader(position)) {
            View v = mHeaders.get(position);
            //将头布局加载出来,也就是把头布局通过addView方式添加到FrameLayout中.
            prepareHeaderFooter((HeaderFooterViewHolder) vh, v, true);
        } else if (isFooter(position)) {
            View v = mFooters.get(position - getItemCountHF() - mHeaders.size());
            // 同上
            prepareHeaderFooter((HeaderFooterViewHolder) vh, v, false);
        } else {
            //普通的Item就调用mAdapter.onBindViewHolder.
            onBindViewHolderHF(vh, getRealPosition(position));
        }
    }

    /**
     * 普通的Item获取真实的位置,排除头布局的位置.
     *
     * @param position
     * @return
     */
    public int getRealPosition(int position) {
        return position - mHeaders.size();
    }

    public void onBindViewHolderHF(ViewHolder vh, int position) {
        mAdapter.onBindViewHolder(vh, position);
    }

    /**
     * 添加头尾布局到HeaderFooterViewHolder的布局中.
     *
     * @param vh
     * @param view
     */
    private void prepareHeaderFooter(HeaderFooterViewHolder vh, View view, boolean formHeader) {
        //如果布局是瀑布流的话,让头尾布局充满整行.
        if (mManagerType == TYPE_MANAGER_STAGGERED_GRID) {
            StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setFullSpan(true);
            vh.itemView.setLayoutParams(layoutParams);
        } else if (formHeader && useEmptyView && null != mEmptyView) {
            //如果是标识的空布局的view的话 就设置父布局为MATCH_PARENT.
          /*  Object viewTag = view.getTag(R.id.empty_layout);
            ViewGroup.LayoutParams layoutParams = vh.itemView.getLayoutParams();
            if (null != viewTag && EMPTY_LAYOUT_TAG.equals(viewTag.toString())) {
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                vh.itemView.setLayoutParams(layoutParams);
            }*/
        }
        // 移除头尾布局的父布局
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        // 添加头尾布局到父布局中
        vh.base.removeAllViews();
        vh.base.addView(view);

    }

    @Override
    public final int getItemCount() {
        return mHeaders.size() + getItemCountHF() + mFooters.size();
    }

    /**
     * 获取被包装的mAdapter的真实Item数量.
     *
     * @return
     */
    public int getItemCountHF() {
        if (null == mAdapter) {
            return -1;
        }
        return mAdapter.getItemCount();
    }

    public void notifyDataSetChangedHF() {
        notifyDataSetChanged();
    }

    public void notifyItemChangedHF(int position) {
        notifyItemChanged(getRealPosition(position));
    }

    public void notifyItemMovedHF(int fromPosition, int toPosition) {
        notifyItemMovedHF(getRealPosition(fromPosition), getRealPosition(toPosition));
    }

    public void notifyItemRangeChangedHF(int positionStart, int itemCount) {
        notifyItemRangeChanged(getRealPosition(positionStart), itemCount);
    }

    public void notifyItemRangeRemovedHF(int positionStart, int itemCount) {
        notifyItemRangeRemoved(getRealPosition(positionStart), itemCount);
    }

    public void notifyItemRemovedHF(int position) {
        notifyItemRemoved(getRealPosition(position));
    }

    public void notifyItemInsertedHF(int position) {
        notifyItemInserted(getRealPosition(position));
    }

    public void notifyItemRangeInsertedHF(int positionStart, int itemCount) {
        notifyItemRangeInserted(getRealPosition(positionStart), itemCount);
    }

    private AdapterDataObserver adapterDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            notifyItemRangeChanged(positionStart + getHeadSize(), itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            notifyItemRangeInserted(positionStart + getHeadSize(), itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            notifyItemRangeRemoved(positionStart + getHeadSize(), itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            notifyItemMoved(fromPosition + getHeadSize(), toPosition + getHeadSize());
        }
    };

    /**
     * 1.竖直RecyclerView
     * 这个方法就是 如果该布局管理器是GridLayoutManager,那么我们的头尾布局就得设置成强制占一行.
     * 这个设定对头尾的使用也是有一定的限制性.
     * 2.横着的RecyclerView
     * 那就是如果该布局管理器是GridLayoutManager头尾布局将会充满整列.
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

}
