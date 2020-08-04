package cn.education.base_res.jsbridge.utils;

import android.content.Context;



import java.io.File;

import cn.education.base_res.jsbridge.constant.JsConstant;
import cn.wenet.networkcomponent.debug.WeDebug;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/5/13
 */
public class ClearCacheUtils {


    public static void clearCache(Context context, String path) {
        File appCacheDir = new File(path);
        File webViewCacheDir = new File(context.getCacheDir()
                .getAbsolutePath() + JsConstant.WEB_VIEW_CACHE);
        if (webViewCacheDir.exists()) {
            deleteFile(webViewCacheDir);
        }
        if (appCacheDir.exists()) {
            deleteFile(appCacheDir);
        }
    }

    private static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {
            WeDebug.e("delete file no exists ", file.getAbsolutePath());
        }
    }

}
