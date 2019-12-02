package cn.education.base_res.jsbridge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
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
        bridgeWebClient = new BridgeWebClient();
        super.setWebViewClient(bridgeWebClient);
        registerJsBridge();
    }

    @SuppressLint("AddJavascriptInterface")
    public void registerJsBridge(){
        addJavascriptInterface(new AppJsBridge(),"WANG");
    }

}
