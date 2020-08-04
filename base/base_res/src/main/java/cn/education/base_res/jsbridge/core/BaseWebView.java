package cn.education.base_res.jsbridge.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.education.base_res.jsbridge.beans.JsResponse;
import cn.education.base_res.jsbridge.callback.JsToWebView;
import cn.education.base_res.jsbridge.constant.JsConstant;
import cn.education.base_res.jsbridge.utils.ClearCacheUtils;
import cn.education.base_res.utils.ThreadUtils;
import cn.wenet.networkcomponent.debug.WeDebug;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/11/28
 */
@SuppressLint("SetJavaScriptEnabled")
public class BaseWebView extends WebView implements JsToWebView {

    public volatile boolean mHaveAccessWeb = false;

    public static final String SPLI = ".";

    private String APP_CACHE_DIRNAME;

    private Map<String, String> mJsHandler = new HashMap<>();


    private List<JsResponse> mWaitResponse;


    private volatile boolean mLoadFinish = false;

    private JsViewHandler mViewHanlder;

    public BaseWebView(Context context) {
        this(context, null);
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
        //设置h5可访问文件。
        settings.setAllowFileAccess(false);
        //支持H5 Dom缓存。
        settings.setDomStorageEnabled(false);
        //cache。
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        APP_CACHE_DIRNAME = getContext().getFilesDir().getAbsolutePath() + JsConstant.WEB_VIEW_CACHE;
        settings.setAppCachePath(APP_CACHE_DIRNAME);
        //支持H5调用。
        settings.setJavaScriptEnabled(true);
        //是否支持缩放。
        settings.setSupportZoom(true);
        //设置支持两指缩放手势。
//        settings.setBuiltInZoomControls(true);
        //设置界面出现缩放的按钮
//        settings.setDisplayZoomControls(true);

        //设置页面缩放至屏幕大小。
//        settings.setLoadWithOverviewMode(true);
        //设置此属性，可任意比例缩放。
        settings.setUseWideViewPort(true);
        //支持通过JS打开新窗口。
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持多窗口。
        settings.setSupportMultipleWindows(true);
        //打开网页调试内容。
        setWebContentsDebuggingEnabled(true);
        registerJsBridge();
    }

    @Override
    public void clearCache(boolean includeDiskFiles) {
        super.clearCache(includeDiskFiles);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                CookieManager.getInstance().removeAllCookies(null);
            } else {
                CookieManager.getInstance().removeAllCookie();
            }
            Context context = getContext();
            ClearCacheUtils.clearCache(context, APP_CACHE_DIRNAME);
        } catch (Exception e) {
            e.printStackTrace();
            WeDebug.error(e.toString());
        }
    }

    public void registerHandler(String path) {
        int lastIndexOf = path.lastIndexOf(SPLI);
        if (lastIndexOf >= 0) {
            String simpleName = path.substring(lastIndexOf + 1);
            if (!TextUtils.isEmpty(simpleName)) {
                String lower = simpleName.toLowerCase();
                WeDebug.e("register : ", lower);
                if (!mJsHandler.containsKey(lower)) {
                    mJsHandler.put(lower, path);
                }
            }
        }
    }

    public void registerViewHandler(JsViewHandler handler) {
        mViewHanlder = handler;
    }

    @SuppressLint("AddJavascriptInterface")
    protected void registerJsBridge() {
        addJavascriptInterface(new AppJsBridge(this), "name");
    }

    @Override
    public void sendToJs(String method, String code) {
        if (!TextUtils.isEmpty(code)) {
            if (ThreadUtils.isMainThread()) {
                if (mLoadFinish) {
                    code = code.replaceAll("(\\\\)([^utrn])", "\\\\\\\\$1$2");
                    code = code.replaceAll("(?<=[^\\\\])(\")", "\\\\\"");
                    code = code.replaceAll("(?<=[^\\\\])(\')", "\\\\\'");
                    code = code.replaceAll("%7B", URLEncoder.encode("%7B"));
                    code = code.replaceAll("%7D", URLEncoder.encode("%7D"));
                    code = code.replaceAll("%22", URLEncoder.encode("%22"));
                    String format = String.format(JsConstant.JS_HANDLE_MESSAGE_FROM_JAVA, method, code);
                    loadUrl(format);
                    WeDebug.e("开始调用Js ：", format);
                }
            }
        }
    }

    @Override
    public Map<String, String> getHandlers() {
        return mJsHandler;
    }

    @Override
    public void onPageStart(String url) {
        mLoadFinish = false;
    }

    @Override
    public void onPageFinished(String url) {
        WeDebug.logD("onPageFinished  ");
        mLoadFinish = true;
        refreshCacheData();
    }

    @Override
    public Activity getActivity() {
        return (Activity) getContext();
    }

    private void refreshCacheData() {
        if (null != mWaitResponse && mWaitResponse.size() > 0) {
            Iterator<JsResponse> iterator = mWaitResponse.iterator();
            while (iterator.hasNext()) {
                JsResponse next = iterator.next();
                WeDebug.logD("从缓存中读取数据 ： ", next.getNativeMethodName());
                preHandleMessage(next);
                iterator.remove();
            }
        }
    }

    /**
     * 注意此处的运行线程可能是子线程。
     *
     * @param jsResponse
     */
    @Override
    public void dispatcherMessage(JsResponse jsResponse) {
        //判断线程环境，要在主线程中处理数据
        if (ThreadUtils.isMainThread()) {
            if (TextUtils.isEmpty(jsResponse.getCb()) || mLoadFinish) {
                preHandleMessage(jsResponse);
            } else {
                cacheMessage(jsResponse);
            }
        } else {
            post(new JsRunnable(jsResponse));
        }
    }

    /**
     * 预处理数据。
     *
     * @param message
     */
    public void preHandleMessage(JsResponse message) {
        message.setWeiView(this);
        String nativeMethodName = message.getNativeMethodName();
        if (TextUtils.isEmpty(nativeMethodName)) {
            sendToJs(message.getCb(), message.getData());
        } else {
            if (mViewHanlder != null) {
                mViewHanlder.viewHandler(message);
            }
        }
    }

    /**
     * 界面还没加载完毕的时候，这里要先缓存下需要回调的数据。
     *
     * @param message
     */
    public void cacheMessage(JsResponse message) {
        if (null == mWaitResponse) {
            mWaitResponse = new ArrayList<>();
        }
        if (!TextUtils.isEmpty(message.getCb()) && !mWaitResponse.contains(message)) {
            mWaitResponse.add(message);
        }
    }

    private class JsRunnable implements Runnable {

        private JsResponse message;

        private JsRunnable(JsResponse message) {
            this.message = message;
        }

        @Override
        public void run() {
            preHandleMessage(message);
        }
    }

}
