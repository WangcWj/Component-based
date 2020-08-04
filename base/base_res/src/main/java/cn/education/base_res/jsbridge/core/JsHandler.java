package cn.education.base_res.jsbridge.core;

import cn.education.base_res.jsbridge.beans.JsResponse;

/**
 * Created to :
 * 约束Native跟H5之间交互的规则。只有JsHandler的子类，才能被H5调用。
 * 其子类必须采用{@link }注解标记，才能被自动注册使用。
 * <pre>
 *     @JsHandlerMark()
 *     public class CusJsHandler implements JsHandler {
 *
 *     @Override
 *     public void handler(JsResponse response) {
 *
 *     }
 * }
 *
 * </pre>
 *
 * @author cc.wang
 * @date 2020/5/14
 */
public interface JsHandler {

    /**
     * 接收并处理来自H5界面的数据。
     *
     * @param response
     */
    void handler(JsResponse response);

}
