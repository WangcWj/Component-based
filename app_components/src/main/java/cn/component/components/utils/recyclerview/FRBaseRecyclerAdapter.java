package cn.component.components.utils.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 对RecyclerView的Adapter的封装.
 * 使用: 继承该类.DATA:为我们要处理的bean. VH : 为该adapter配套的ViewHolder.
 * <p>
 * 1.{@link #setDataList(List)} 设置数据,默认刷新.
 * 2.{@link #getDataList()} 获取基础数据.
 * 3.{@link #getItemViewType(int)} 多布局请重写该方法.
 * 4.{@link #removeAllDataList()} 清除数据.
 * 5.{@link #insertItem(int, Object)} 特定的位置插入数据.
 * 6.{@link #removeItem(int)} 删除特定位置的数据.
 * 7.{@link #updateItem(int, Object)} 更新特定位置的数据.
 * 8.{@link #appendItem(Object)} 再原数据后面再添加一条数据.
 * 9.{@link #appendList(List)} 再原数据后面再添加一个集合.
 * 10.{@link #frontItem(Object)} 再原数据前面添加一条数据.
 * 11.{@link #frontList(List)} 再原数据前面添加一个集合.
 */
public abstract class FRBaseRecyclerAdapter<DATA, VH extends FRBaseRecyclerViewHolder<DATA>> extends RecyclerView.Adapter<VH> {

    private List<DATA> mDataList;

    private final Context mContext;

    private View mEmptyView;

    protected OnItemClickListener<VH> listener;

    protected OnItemLongClickListener<VH> onItemLongClickListener;

    private DataEmptyListener mEmptyListener;

    public FRBaseRecyclerAdapter(Context context, OnItemClickListener<VH> listener) {
        this.mContext = context;
        this.listener = listener;
    }

    public FRBaseRecyclerAdapter(Context context) {
        this(context, null);
    }

    protected Context getContext() {
        return mContext;
    }

    /**
     * 给holder设置数据
     *
     * @param vh
     * @param position
     */
    @Override
    public void onBindViewHolder(final VH vh, final int position) {
        vh.setData(mDataList.get(position), mDataList.size(), position);
        if (listener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(vh, position);
                }
            });
        }

        if (null != onItemLongClickListener) {
            vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(vh, position);
                    return true;
                }
            });
        }
    }

    public void setEmptyListener(DataEmptyListener mEmptyListener) {
        this.mEmptyListener = mEmptyListener;
    }

    /**
     * 每一个位置的item都作为单独一项来设置
     * viewType 设置为position
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }

    /**
     * 填充数据,此方法会清空以前的数据
     *
     * @param dataList 需要显示的数据
     */
    public void setDataList(List<DATA> dataList, boolean notifyDataSetChanged) {
        if (mDataList != null) {
            mDataList.clear();
        }
        if (dataList != null && dataList.size() > 0) {
            addEmptyView(false);
            //有数据的时候
            if (mDataList == null) {
                mDataList = new ArrayList<>();
            }
            mDataList.addAll(dataList);
        } else {
            //没数据的时候
            addEmptyView(true);
        }
        if (notifyDataSetChanged) {
            notifyDataSetChanged();
        }
    }

    /**
     * 监听数据
     */
    private void addEmptyView(boolean empty) {
        if (mEmptyListener != null) {
            mEmptyListener.isEmpty(empty);
        }
    }

    /**
     * 清除数据
     */
    public void removeAllDataList() {
        if (mDataList != null) {
            mDataList.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 在列表某个位置插入数据
     *
     * @param position 需要在哪个位置上插入
     * @param data     插入的数据
     */
    public void insertItem(int position, DATA data) {
        mDataList.add(position, data);
        //插入更新操作
        notifyItemInserted(position);
        //更新position后面的数据的位置
        notifyItemRangeChanged(position, mDataList.size() - position);
    }

    /**
     * 移除某个位置的数据
     *
     * @param position
     */
    public void removeItem(int position) {
        mDataList.remove(position);
        //删除操作更新
        notifyItemRemoved(position);
        //更新position后面的数据的位置
        notifyItemRangeChanged(position, mDataList.size() - position);
    }

    /**
     * 设置原始数据 默认notify
     *
     * @param dataList
     */
    public void setDataList(List<DATA> dataList) {
        setDataList(dataList, true);
    }

    public List<DATA> getDataList() {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        return mDataList;
    }

    /**
     * 更新局部数据
     *
     * @param position item的位置
     * @param data     item的数据
     */
    public void updateItem(int position, DATA data) {
        mDataList.set(position, data);
        notifyDataSetChanged();
    }

    /**
     * 获取一条数据
     *
     * @param position item的位置
     * @return item对应的数据
     */
    public DATA getItem(int position) {
        return mDataList.get(position);
    }

    /**
     * 追加一条数据
     *
     * @param data 追加的数据
     */
    public void appendItem(DATA data) {
        mDataList.add(data);
        notifyDataSetChanged();
    }

    /**
     * 追加一个集合数据
     *
     * @param list 要追加的数据集合
     */
    public void appendList(List<DATA> list) {
        mDataList.addAll(list);
        notifyItemRangeInserted(mDataList.size() - list.size() - 1, list.size());
    }

    /**
     * 再该集合最前面添加一个数据
     *
     * @param data 要前置的数据
     */
    public void frontItem(DATA data) {
        mDataList.add(0, data);
        notifyDataSetChanged();
    }

    /**
     * 再该集合最前面添加一个集合数据
     *
     * @param list 要前置的数据集合
     */
    public void frontList(List<DATA> list) {
        mDataList.addAll(0, list);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener<VH> {
        void onItemClick(VH vh, int position);
    }

    public interface OnItemLongClickListener<VH> {
        void onItemLongClick(VH vh, int position);
    }

    public interface DataEmptyListener {
        void isEmpty(boolean empty);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

}
