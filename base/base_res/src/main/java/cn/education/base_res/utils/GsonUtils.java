package cn.education.base_res.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/4/9
 */
public class GsonUtils {

    public static String objectToJson(Object o) {
        try {
            Gson gson = new Gson();
            return gson.toJson(o);
        } catch (Exception e) {
            return "{}";
        }
    }

    public static List<String> jsonToListString(String list) {
        Type type = new TypeToken<List<String>>() {
        }.getType();
        try {
            Gson gson = new Gson();
            return gson.fromJson(list, type);
        } catch (Exception e) {
            return new ArrayList<>(0);
        }
    }

    public static <T> T jsonToObj(String json, Class<T> clz) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, clz);
        } catch (Exception e) {
            return null;
        }
    }

}
