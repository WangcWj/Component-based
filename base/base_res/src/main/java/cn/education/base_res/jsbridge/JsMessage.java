package cn.education.base_res.jsbridge;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/12/4
 */
public class JsMessage {
    private String data;

    public JsMessage(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
