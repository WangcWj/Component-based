package cn.education.base_res.jsbridge.core;
import cn.education.base_res.jsbridge.beans.JsResponse;
/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/5/14
 */
public interface JsViewHandler<T> {

    void viewHandler(JsResponse response);

    T getPresenter();
}
