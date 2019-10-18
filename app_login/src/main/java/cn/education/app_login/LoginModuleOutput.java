package cn.education.app_login;

import cn.education.base_res.output.loginmodule.LoginModuleMethod;
import cn.router.werouter.annotation.Router;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/9/4
 */

@Router(path = "native://provider/LoginModuleOutput")
public class LoginModuleOutput implements LoginModuleMethod {

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void init() {

    }
}
