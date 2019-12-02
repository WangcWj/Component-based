package com.example.education;

import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

import cn.education.base_res.application.BaseApplication;
import cn.education.base_res.application.BaseApplicationMethod;
import cn.education.base_res.utils.ApplicationUtils;
import cn.router.api.router.WeRouter;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/9/4
 */
public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        boolean mainProcess = ApplicationUtils.isMainProcess(this);
        if(mainProcess){
            runInManProgress();
        }
    }

    private void runInManProgress() {
        //如果没有别的需求的话需要同步调用
        WeRouter.init(this);
        leakCarry();
      /*  BaseApplicationMethod loginInit = (BaseApplicationMethod) WeRouter
                .getInstance()
                .build("native://LoginApplication")
                .navigation();
        BaseApplicationMethod mainInit = (BaseApplicationMethod) WeRouter
                .getInstance()
                .build("native://MainApplication")
                .navigation();
        loginInit.onCreate();
        mainInit.onCreate();*/
      getApplicationContext();

        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String value = applicationInfo.metaData.getString("login");
            if(!TextUtils.isEmpty(value)){
                Class<?> aClass = Class.forName(value);
                Object o = aClass.newInstance();
                Log.e("WANG","AppApplication.runInManProgress:   "+o);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


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
