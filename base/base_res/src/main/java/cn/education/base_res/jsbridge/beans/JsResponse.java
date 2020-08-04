package cn.education.base_res.jsbridge.beans;


import cn.education.base_res.jsbridge.callback.JsToWebView;
import cn.education.base_res.jsbridge.core.JsHandler;
import cn.education.base_res.jsbridge.core.JsViewHandler;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/5/7
 */
public class JsResponse {

    private String data;
    private String nativeMethodName;
    private String cb;

    private JsHandler mTarget;
    private JsToWebView mWeiView;
    private JsViewHandler mViewHandler;

    public JsViewHandler getViewHandler() {
        return mViewHandler;
    }

    public void setViewHandler(JsViewHandler viewHandler) {
        this.mViewHandler = viewHandler;
    }

    public String getCb() {
        return null == cb ? "" : cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    public JsToWebView getWeiView() {
        return mWeiView;
    }

    public void setWeiView(JsToWebView mWeiView) {
        this.mWeiView = mWeiView;
    }

    public JsHandler getTarget() {
        return mTarget;
    }

    public void setTarget(JsHandler mTarget) {
        this.mTarget = mTarget;
    }

    public String getNativeMethodName() {
        return null == nativeMethodName ? "" : nativeMethodName;
    }

    public void setNativeMethodName(String nativeMethodName) {
        this.nativeMethodName = nativeMethodName;
    }

    public String getData() {
        return data == null ? "" : data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public JsRequest getCbRequest(){
        return new JsRequest(data,cb,mWeiView);
    }

}
