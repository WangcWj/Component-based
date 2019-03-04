package cn.component.main.output;
import android.support.v4.app.Fragment;

import cn.component.basecomponent.output.mainmodule.MainModuleMethod;
import cn.component.main.fragment.HomeFragment;
import cn.router.werouter.annotation.Router;

/**
 * Created to :
 *
 * @author WANG
 * @date 2018/10/30
 */
@Router(path = "native://provider/MainOutPutImp")
public class MainOutPutImp implements MainModuleMethod {

    @Override
    public Fragment getFragment() {
        return HomeFragment.newInstance();
    }

    @Override
    public void init() {

    }
}
