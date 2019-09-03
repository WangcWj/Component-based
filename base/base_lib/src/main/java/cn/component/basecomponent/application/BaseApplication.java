package cn.component.basecomponent.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created to :解决dex文件超过65536的限制,实现分dex.
 * <p>
 *     如果minSdkVersion设置为21以及更高的话只需要设置 multiDexEnabled true,
 *  如果为20以及以下的话就需要其他的设置:
 *  首先依赖: compile 'com.android.support:multidex:1.0.2'
 *  然后application依赖MultiDexApplication或者再application重写
 *    @Override
 *    protected void attachBaseContext(Context base) {
 *       super.attachBaseContext(base);
 *       MultiDex.install(this);
 *
 *    }
 *
 *    分dex的缺点:
 *    <a>http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/1218/3789.html<a/>
 *
 * <p/>
 *
 * @author WANG
 * @date 2018/10/23
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
