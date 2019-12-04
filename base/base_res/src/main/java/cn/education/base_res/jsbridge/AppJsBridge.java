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

    /**
     * data的形式就是：
     * 1.跳转本地界面： native://pagename?
     *
     * @param data
     */
    @JavascriptInterface
    @Override
    public void bridge(String data) {
        Log.e("WANG", "收到Web的调用：" + data+"   Thread   ="+Thread.currentThread().getName());
        JsMessage message = new JsMessage(data);
        baseWebView.dispatcherMessage(message);
    }
}
