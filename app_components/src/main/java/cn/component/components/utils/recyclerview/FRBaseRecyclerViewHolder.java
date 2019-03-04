package cn.component.components.utils.recyclerview;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.mifengkong.frtools.util.debug.FRLog;


/**
 * 对RecyclerView的ViewHolder的封装
 * 需要用到position使用{@link #setData(Object, int, int)} 一定就得逻辑写在super()方法之后.
 * 一般再{@link #onItemDataUpdated(Object)} 方法里面写逻辑代码.
 */
public abstract class FRBaseRecyclerViewHolder<DATA> extends RecyclerView.ViewHolder {

    public View itemView;
    private SparseArray<View> mViews;


    public FRBaseRecyclerViewHolder(View view) {
        super(view);
        this.itemView = view;
        mViews = new SparseArray<>();
    }


    /**
     * 根据id来获取布局中的view
     *
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View> T findView(@IdRes int resId) {
        View view = mViews.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
            mViews.put(resId, view);
        }
        return (T) view;
    }

    /**
     * 获取Context实例对象
     *
     * @return
     */
    protected Context getContext() {
        return itemView.getContext();
    }

    protected abstract void onItemDataUpdated(DATA data);

    public final void setData(DATA data) {
        if (null != data) {
            try {
                onItemDataUpdated(data);
            } catch (Exception e) {
                FRLog.error(e);
            }
        }
    }

    public void setData(DATA data, int count, int position) {
        setData(data);
    }
}
