package cn.education.base_res.output.loginmodule;

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


}
