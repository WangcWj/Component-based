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

    BaseWebView baseWebView;

    public AppJsBridge(BaseWebView baseWebView) {
        this.baseWebView = baseWebView;
    }

    @JavascriptInterface
    @Override
    public void bridge(String data) {
        Log.e("WANG", "AppJsBridge.bridge" + data+"   Thread   ="+Thread.currentThread().getName());
    }
}
