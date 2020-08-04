package cn.education.base_res.jsbridge.callback;

/**
 * Created to : WebView中注册的对象必须继承该接口。
 *
 * @author WANG
 * @date 2019/12/1
 */
public interface JsBridgeInterface {

    void bridge(String data);

    String bridgeReturn(String data);

}
