package cn.education.base_res.jsbridge;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/12/1
 */
public class AppJsBridge implements JsBridgeInterface {

    @JavascriptInterface
    @Override
    public void bridge(String data) {
        Log.e("WANG", "AppJsBridge.bridge" + data);
    }
}
