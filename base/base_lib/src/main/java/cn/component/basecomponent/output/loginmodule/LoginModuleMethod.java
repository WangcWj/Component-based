package cn.component.basecomponent.output.loginmodule;

import android.content.Context;

import cn.router.api.provider.WeProvider;

/**
 * Created to :
 *
 * @author WANG
 * @date 2018/11/1
 */

public interface LoginModuleMethod extends WeProvider {

    /**
     * 是否已经登录
     * @return true 已经登录  false 未登录
     */
    boolean isLogin();

    /**
     * 启动登录Activity
     * @param context
     */
    void startLoginActivity(Context context);

}
