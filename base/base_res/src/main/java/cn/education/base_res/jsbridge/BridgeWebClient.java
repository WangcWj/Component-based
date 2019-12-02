package cn.education.base_res.jsbridge;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/11/28
 */
public class BridgeWebClient extends WebViewClient {


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Uri url = request.getUrl();
            String scheme = url.getScheme();
            if(scheme.equals("http") || scheme.equals("https")) {
                String authority = url.getAuthority();
                view.loadUrl(authority);
            }
        }else {
            return shouldOverrideUrlLoading(view,request.getUrl().getAuthority());
        }
        Log.e("WANG", "BridgeWebClient.新方法 :   url  =  "+request.getUrl().toString());
        return super.shouldOverrideUrlLoading(view,request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.e("WANG","废弃的那个方法：  "+url);
        String decodeUrl = url;
        try {
            decodeUrl = URLDecoder.decode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        view.loadUrl(decodeUrl);
        return super.shouldOverrideUrlLoading(view,url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.e("WANG","BridgeWebClient.onPageFinished:  "+url);
    }


}
