package cn.education.app_main;

import androidx.core.app.Fragment;

import cn.education.base_res.output.mainmodule.MainModuleMethod;
import cn.router.werouter.annotation.Router;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/9/4
 */
@Router(path = "native://MainModulePutput")
public class MainModulePutput implements MainModuleMethod {

    @Override
    public Fragment getFragment() {
        return null;
    }

    @Override
    public void init() {

    }
}
