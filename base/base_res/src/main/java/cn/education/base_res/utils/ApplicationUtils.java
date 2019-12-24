package cn.education.base_res.utils;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created to :
 *
 * @author WANG
 * @date 2018/11/1
 */

public class ApplicationUtils {

    /**
     * 是否是主进程
     */
    public static boolean isMainProcess(Context context) {
        String procName = getCurrentProcessName(context);
        return procName == null || procName.equalsIgnoreCase(context.getPackageName());
    }

    /**
     * 获取当前进程名字
     */
    public static String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * need < uses-permission android:name =“android.permission.GET_TASKS” />
     * 判断是否前台运行
     */
    public static boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName componentName = taskList.get(0).topActivity;
            if (componentName != null && componentName.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取App包 信息版本号
     */
    public PackageInfo getPackageInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    public static void killTheApp() {

        System.exit(0);
    }


    /**
     * 1.需要开启通知权限。
     * 2.横幅通知需要打开横幅通知权限。
     *//*
    private void checkPermission() {
        if (!NotificationUtils.isNotificationEnabled(activity)) {
            return;
        }
        sendNotification();
    }

    *//**
     * 显示横幅通知的必须设置的是：
     * 1.横幅通知权限打开。
     * 2.通知的优先级为最高：.setDefaults(Notification.DEFAULT_ALL)。
     * 3.通知要设置为有震动或者声音。
     * 4.Android 5.0以上。
     * <p>
     * 自定义通知栏的布局：https://iluhcm.com/2017/03/12/experience-of-adapting-to-android-notifications/#RemoteViews%E9%80%82%E9%85%8D。
     *//*
    private void sendNotification() {
        //创建通知渠道
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationUtils.createNotificationChannel(activity, CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        }
        NotificationManager manager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.activity, CHANNEL_ID);
        Notification notification = builder.setContentTitle("以为你找到资源")
                .setContentText("开始计时")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .build();

        manager.notify(3, notification);
    }*/

}
