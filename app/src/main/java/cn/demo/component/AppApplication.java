package cn.demo.component;

import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

import cn.component.basecomponent.application.BaseApplication;
import cn.component.components.utils.ApplicationUtils;
import cn.router.api.router.WeRouter;

/**
 * Created to :
 *
 * @author WANG
 * @date 2018/10/23
 */

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        leakCarry();
        boolean mainProcess = ApplicationUtils.isAppMainProcess(this);
        if(mainProcess){

        }
        Log.e("WANG","AppApplication.onCreate."+mainProcess );
        WeRouter.init(this);

    }


    private void leakCarry() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
