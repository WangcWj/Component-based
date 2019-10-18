package cn.education.base_res.output.mainmodule;


import android.support.v4.app.Fragment;

import cn.router.api.provider.WeProvider;

/**
 * Created to :
 *
 * @author WANG
 * @date 2018/10/30
 */
public interface MainModuleMethod  extends WeProvider {

   Fragment getFragment();

}
