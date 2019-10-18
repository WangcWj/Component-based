package cn.education.base_res.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/2/14
 */
public class ActivityStackManager {

    private static ActivityStackManager instance = new ActivityStackManager();
    public static ActivityStackManager getInstance() {
        return instance;
    }

    private Stack<Activity> mActivityStack = new Stack<>();

    /**
     * 入栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mActivityStack.push(activity);
    }

    /**
     * 出栈
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        mActivityStack.remove(activity);
    }

    /**
     * 彻底退出
     */
    public void finishAllActivity() {
        Activity activity;
        while (!mActivityStack.empty()) {
            activity = mActivityStack.pop();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 查找栈中是否存在指定的activity
     *
     * @param cls
     * @return
     */
    public boolean checkActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    private void finishActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
        }
    }


}
