package cn.education.base_res.utils.click;

/**
 * Created to :回调RecyclerView的Item点击事件.
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
    void onItemClick(T t, int position);
}
