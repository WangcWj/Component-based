package cn.education.base_res.jsbridge.constant;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/5/7
 */
public class JsConstant {

    public final static String JS_HANDLE_MESSAGE_FROM_JAVA = "javascript:%s('%s');";
    public final static String ERROR_MESSAGE = "错误消息未知！";

    public final static String INFO_RETURN = "return_appInfo";
    public final static String IP_CALLBACK_METHOD = "appDeviceInfoBack";





    public static boolean isSuccessCode(int code) {
        return code >= SUCCESS_CODE && code <= ACCESS_CODE;
    }

    public static final int SUCCESS_CODE = 0;
    public static final int ACCESS_CODE = 100;

    public static final String METHOD_PAY = "pay";
    public final static String WEB_VIEW_CACHE = "/webCache";



    public final static String INTENT_SLAG = "url_flag";





}
