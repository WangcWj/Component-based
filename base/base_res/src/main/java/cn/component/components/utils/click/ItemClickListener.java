package cn.component.components.utils.click;

/**
 * Created to :回调RecyclerView的Item点击事件.
 * 配合:{@link cn.component.components.utils.recyclerview.BaseRecycleAdapter}
 *
 * @author WANG
 * @date 2019/9/3
 */
public interface ItemClickListener<T> {

    /**
     * 当RecyclerView的Item接受到点击事件的时候,就会调用该方法.
     * @param t
     * @param position
     */
    void onItemClick(T t ,int position);
}
