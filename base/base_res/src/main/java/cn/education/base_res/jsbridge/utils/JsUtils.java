package cn.education.base_res.jsbridge.utils;

import com.google.gson.JsonObject;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/5/14
 */
public class JsUtils {

    public static String errorJson(String cb){
        JsonObject jo = new JsonObject();
        jo.addProperty("cd",cb);
        jo.addProperty("data","");
        return jo.toString();
    }

}
