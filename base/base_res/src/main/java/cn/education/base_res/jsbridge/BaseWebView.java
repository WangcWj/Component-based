package cn.education.base_res.jsbridge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/11/28
 */
@SuppressLint("SetJavaScriptEnabled")
public class BaseWebView extends WebView {

    public final static String JS_HANDLE_MESSAGE_FROM_JAVA = "javascript:_receiveNative('%s');";

    public BridgeWebClient bridgeWebClient;

    public BaseWebView(Context context) {
        super(context);
        init(context);
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        settings.setAppCacheEnabled(false);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        // APP_CACHE_DIRNAME = getContext().getFilesDir().getAbsolutePath() + "/webcache";
        //settings.setAppCachePath(APP_CACHE_DIRNAME);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //打开网页调试内容。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setWebContentsDebuggingEnabled(true);
        }
        bridgeWebClient = new BridgeWebClient();
        super.setWebViewClient(bridgeWebClient);
        registerJsBridge();
    }

    @SuppressLint("AddJavascriptInterface")
    public void registerJsBridge() {
        addJavascriptInterface(new AppJsBridge(this), "WANG");
    }

    public void dispatcherMessage(JsMessage message) {
        if (null == message) {
            return;
        }
        //判断线程环境，要在主线程中处理数据
        if (isMainThread()) {
            handleMessage(message);
        } else {
            post(new JsRunnable(message));
        }
    }

    public void handleMessage(JsMessage message){
        String data = message.getData();
        if(!TextUtils.isEmpty(data)){
            String format = String.format(JS_HANDLE_MESSAGE_FROM_JAVA, data);
            if(isMainThread()) {
                loadUrl(format);
            }
            Log.e("WANG","准备处理Web端的数据："+data+"    Thread =  "+Thread.currentThread().getName());
        }
    }

    public boolean isMainThread(){
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }




    private class JsRunnable implements Runnable {

        private JsMessage message;

        public JsRunnable(JsMessage message) {
            this.message = message;
        }

        @Override
        public void run() {
            handleMessage(message);
        }
    }

}
