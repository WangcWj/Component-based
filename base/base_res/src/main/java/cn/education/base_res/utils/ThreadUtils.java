package cn.education.base_res.utils;

import android.os.Looper;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created to :
 *
 * @author WANG
 * @date 2018/11/1
 */

public class ThreadUtils {

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    /**
     * Returns a copy of the given list that is safe to iterate over and perform actions that may
     * modify the original list.
     */
    public static <T> List<T> getSnapshot(Collection<T> other) {
        // toArray creates a new ArrayList internally and this way we can guarantee entries will not
        // be null. See #322.
        List<T> result = new ArrayList<T>(other.size());
        for (T item : other) {
            result.add(item);
        }
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        return result;
    }

    public static Executor runExecutor(Runnable runnable) {
        return runExecutor("", runnable);
    }

    public static Executor runExecutor(String name, Runnable runnable) {
        DefaultThreadFactory threadFactory = new DefaultThreadFactory(name);
        return new ThreadPoolExecutor(1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(),
                threadFactory);
    }

    /**
     * 并发执行.
     *
     * @return
     */
    public static ExecutorService getFixdExecutorService() {
        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new DefaultThreadFactory("SwCloud Ping Thread"));
        return executorService;
    }

    private static class DefaultThreadFactory implements ThreadFactory {

        private String name;

        public DefaultThreadFactory(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            if (TextUtils.isEmpty(name)) {
                name = "ThreadUtils";
            }
            Thread t = new Thread(null, r, name, 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }


}
