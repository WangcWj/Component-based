package cn.component.login.output;

import android.content.Context;
import android.content.Intent;

import cn.component.basecomponent.output.loginmodule.LoginModuleMethod;
import cn.component.login.LoginActivity;
import cn.router.werouter.annotation.Router;

/**
 * Created to :
 *
 * @author WANG
 * @date 2018/11/1
 */
@Router(path = "native://provider/LoginOutPutImp")
public class LoginOutPutImp implements LoginModuleMethod {

    @Override
    public boolean isLogin() {
        return true;
    }

    @Override
    public void startLoginActivity(Context context) {
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void init() {

    }
}
