package cn.education.base_res.utils.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.education.base_res.R;
import cn.education.base_res.utils.click.OnFilterClickListener;
import cn.education.base_res.utils.click.ItemClickListener;


/**
 * RecycleView的基类adapter,注意加泛型(该泛型是List<>数据里面的泛型).
 *
 * @author Wang
 * @date 2017/2/17
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private ItemClickListener<T> mItemClickListener;

    private List<T> mBaseList;

    private Context mContext;

    public BaseRecycleAdapter(ItemClickListener<T> mItemClickListener, Context mContext) {
        this.mItemClickListener = mItemClickListener;
        this.mContext = mContext;
    }

    public BaseRecycleAdapter(ItemClickListener<T> mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    /**
     * 刷新数据使用这个方法
     *
     * @param data
     */
    public void refreshData(List<T> data) {
        checkList();
        mBaseList.clear();
        mBaseList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 加载更多的时候就调用这个方法.
     *
     * @param data
     */
    public void loadMoveData(List<T> data) {
        checkList();
        mBaseList.addAll(data);
        notifyDataSetChanged();
    }

    private void checkList() {
        if (null == mBaseList) {
            mBaseList = new ArrayList<>();
        }
    }

    /**
     * 获取数据
     *
     * @return
     */
    public List<T> getBaseList() {
        checkList();
        return mBaseList;
    }


    /**
     * 获取Item的Layout资源ID.
     *
     * @param viewType
     * @return
     */
    public abstract int getLayoutId(int viewType);

    /**
     * 更新itemView视图(由子类负责完成)
     *
     * @param holder   当前创建的ViewHolder.
     * @param t        当前位置的Bean数据.
     * @param position 当前的位置.
     */
    public abstract void convert(BaseViewHolder holder, T t, int position);

    /**
     * 根据Position的位置返回数据.
     *
     * @param position
     * @return
     */
    public T getItem(final int position) {
        checkList();
        if(position >= mBaseList.size()){
            return null;
        }
        return mBaseList.get(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = getLayoutId(viewType);
        return BaseViewHolder.getViewHolder(parent, layoutId);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (position >= mBaseList.size()) {
            return;
        }
        T itemModel = mBaseList.get(position);
        //更新itemView视图
        convert(holder, itemModel, position);
        //设置ItemView的点击事件
        View itemView = holder.itemView;
        if (null != itemView) {
            itemView.setTag(R.id.item_position, position);
            itemView.setOnClickListener(new OnFilterClickListener() {
                @Override
                protected void mFilterClick(View v) {
                    Object tag = v.getTag(R.id.item_position);
                    if (tag instanceof Integer) {
                        int position = (int) tag;
                        if (null != mItemClickListener && position >= 0) {
                            mItemClickListener.onItemClick(mBaseList.get(position), position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        checkList();
        return mBaseList.size();
    }

    /**
     * 销毁Adapter.
     */
    public void destroyAdapter() {
        if (mBaseList != null) {
            mBaseList.clear();
        }
        mBaseList = null;
        mItemClickListener = null;
    }
}



