package cn.component.components.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import cn.component.basecomponent.BuildConfig;

/**
 * Created to :
 *
 * @author WANG
 * @date 2018/11/1
 */

public class ApplicationUtils {

    /**
     * 判断是否是主进程 主进程的id为当前包名id
     * @param application
     * @return
     */
    public static boolean isAppMainProcess(Application application) {
        try {
            int pid = android.os.Process.myPid();
            String process = getAppNameByPID(application.getApplicationContext(), pid);
            if (TextUtils.isEmpty(process)) {
                return true;
            } else if (BuildConfig.APPLICATION_ID.equalsIgnoreCase(process)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 根据Pid得到进程名
     */
    public static String getAppNameByPID(Context context, int pid) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                return processInfo.processName;
            }
        }
        return "";
    }

}
