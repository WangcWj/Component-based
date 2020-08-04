package cn.education.base_res.jsbridge.beans;

import android.text.TextUtils;

import cn.education.base_res.jsbridge.callback.JsToWebView;
import cn.education.base_res.jsbridge.utils.JsUtils;
import cn.wenet.networkcomponent.debug.WeDebug;


/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/5/7
 */
public class JsRequest {


    private String data;
    private String cb;

    private JsToWebView mWeiView;

    public JsRequest(String data, String cb, JsToWebView mWeiView) {
        this.data = data;
        this.cb = cb;
        this.mWeiView = mWeiView;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void errorCallBack() {
        String json = JsUtils.errorJson(cb);
        if (null != mWeiView && !TextUtils.isEmpty(cb)) {
            mWeiView.sendToJs(cb, json);
        }
    }

    public void callBack() {
        if (null != mWeiView && !TextUtils.isEmpty(cb) && !TextUtils.isEmpty(data)) {
            mWeiView.sendToJs(cb, data);
        }
    }

    public void recycler(){

    }

    @Override
    public String toString() {
        return "JsRequest{" +
                "data='" + data + '\'' +
                ", cb='" + cb + '\'' +
                ", mWeiView=" + mWeiView +
                '}';
    }
}
