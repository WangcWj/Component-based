package cn.education.app_main;

import android.util.Log;

import cn.education.base_res.application.BaseApplicationMethod;
import cn.router.werouter.annotation.Router;

/**
 * 
 * @author WANG
 */
@Router(path = "native://MainApplication")
public class MainApplication implements BaseApplicationMethod {

    @Override
    public void onCreate() {
        Log.e("WANG","MainApplication.onCreate");
    }

    @Override
    public void init() {

    }
}
