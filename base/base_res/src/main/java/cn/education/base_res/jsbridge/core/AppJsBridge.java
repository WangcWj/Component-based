package cn.education.base_res.jsbridge.core;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import java.util.Map;
import cn.education.base_res.jsbridge.beans.JsResponse;
import cn.education.base_res.jsbridge.callback.JsBridgeInterface;
import cn.education.base_res.jsbridge.callback.JsToWebView;
import cn.education.base_res.jsbridge.constant.JsConstant;
import cn.education.base_res.utils.GsonUtils;
import cn.wenet.networkcomponent.debug.WeDebug;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/12/1
 */
public class AppJsBridge implements JsBridgeInterface {

    private JsToWebView mWebView;

    AppJsBridge(JsToWebView toWebView) {
        this.mWebView = toWebView;
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
        WeDebug.e("收到Web的调用：", data);
        if (!TextUtils.isEmpty(data)) {
            JsResponse jsResponse = null;
            try {
                jsResponse = GsonUtils.jsonToObj(data, JsResponse.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (null == jsResponse) {
                WeDebug.e("AppJsBridge ：JsResponse 解析失败！ ");
                return;
            }
            Map<String, String> handlers = mWebView.getHandlers();
            if (null == handlers || handlers.size() <= 0) {
                WeDebug.e("AppJsBridge ：handlers size is 0 ");
                return;
            }
            // 处理Js的方法有两种：
            // 一 单对象分发：
            //  也就是一个类中定义所有的方法，这样采用反射机制就能调用特定的方法。
            // 二 多对象分发：
            //  注册多个对象，根据Js返回的对象的名字，也就是本地的方法名字，去确定使用哪个对象去处理该结果。
            //必要的同步处理，因为可能同时Js发起很多请求。当前环境处于子线程。
            synchronized (AppJsBridge.class) {
                String nativeMethodName = jsResponse.getNativeMethodName().toLowerCase();
                String classPath = handlers.get(nativeMethodName);
                JsHandler jsHandler = getJsHandlerByClass(classPath);
                if (null == jsHandler) {
                    WeDebug.e("AppJsBridge ：handlers 中找不到处理该数据的方法");
                    return;
                }
                jsResponse.setTarget(jsHandler);
                WeDebug.e("AppJsBridge ：dispatcherMessage ");
                mWebView.dispatcherMessage(jsResponse);
            }
        }
    }

    /**
     * 调用：window.SwJsBridge.bridgeReturn("return_appInfo")
     * 返回：Json.
     *
     * @return
     */
    @JavascriptInterface
    @Override
    public String bridgeReturn(String data) {
        if (JsConstant.INFO_RETURN.equals(data)) {

        }
        return "";
    }

    private JsHandler getJsHandlerByClass(String path) {
        try {
            Class<?> aClass = Class.forName(path);
            return (JsHandler) aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
