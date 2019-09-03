package cn.component.components.utils.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 配合{@link BaseRecycleAdapter} 使用
 *
 * @author Wang
 * @date 2017/2/17
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    /**
     * 缓存所有的ItemView.
     */
    private SparseArray<View> mViews = new SparseArray<>();

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * 加载layoutId视图
     */
    protected static BaseViewHolder getViewHolder(ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new BaseViewHolder(itemView);
    }

    /**
     * 根据View的id获取子视图View.
     */
    public View findView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

}
