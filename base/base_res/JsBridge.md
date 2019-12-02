### JsBridge

**问题1：** 在当前应用内显示网页，而不是外部游览器。

`WebView`设置`WebViewClient`就好了。

**问题2：** `WebView`调用`loadUrl()`方法之后不显示网页。

在`WebViewClient`的`shouldOverrideUrlLoading()`或者是重载方法中处理。

```java
//5.0之前会被调用。
@Override
public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(decodeUrl);
        return super.shouldOverrideUrlLoading(view,url);
    }
//API21之后才会调用。也就是5.0之后。
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
        return super.shouldOverrideUrlLoading(view,request);
    }
```

**问题3：** 网页打开之后，点击网页里面的二级界面，页面启动之后显示一片空白。

这个问题打印下`WebViewClient`的`onPageFinished()`方法，显示网页连接为`about:blank`。着说明，在`shouldOverrideUrlLoading()`方法中的返回值设置的是`true`。`true`代表自己处理连接，不需要`WebView`处理。所以应该把返回值改为`super `或者`false`。

**问题4：** `WebView`打开的界面不能弹出`Alert`。

`WebView`需要重写：

```java
  webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.e("WANG","FlashScreenPageActivity.onJsAlert"+message);
                return super.onJsAlert(view, url, message, result);
            }
        });
```



 