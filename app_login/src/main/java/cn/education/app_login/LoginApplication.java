package cn.education.app_login;

import android.util.Log;

import cn.education.base_res.application.BaseApplicationMethod;
import cn.router.werouter.annotation.Router;

/**
 *
 * @author WANG
 */
@Router(path = "native://LoginApplication")
public class LoginApplication implements BaseApplicationMethod {

    @Override
    public void onCreate() {
         Log.e("WANG","LoginApplication.onCreate");
    }

    @Override
    public void init() {

    }
}
