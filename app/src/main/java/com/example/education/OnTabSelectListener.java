package com.example.education;

/**
 * tablayout 事件
 *
 * @author Clem
 * @date 2019-08-21.
 */
public interface OnTabSelectListener {

    /**
     * 选中
     *
     * @param position 下标
     */
    void onTabSelect(int position);

    /**
     * 不再被选中
     *
     * @param position 下标
     */
    void onTabReselect(int position);
}
