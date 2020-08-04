package cn.education.base_res.jsbridge.callback;

import android.app.Activity;
import java.util.Map;
import cn.education.base_res.jsbridge.beans.JsResponse;


/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/5/13
 */
public interface JsToWebView {

    void dispatcherMessage(JsResponse data);

    void sendToJs(String method, String code);

    Map<String, String> getHandlers();

    void onPageStart(String url);

    void onPageFinished(String url);

    Activity getActivity();

}
