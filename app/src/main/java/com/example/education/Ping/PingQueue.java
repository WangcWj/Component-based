package com.example.education.Ping;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.internal.Util;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/1/2
 */
public class PingQueue {


    public void run() {
        Process process = null;
        BufferedReader reader = null;
        try {
            process = Runtime.getRuntime().exec("ping -c  4   -w  4   " + "192.168.9.105");
            InputStream inputStream = process.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String content = "";
            while ((content = reader.readLine()) != null) {
                Log.e("cc.wang", "MainActivity.run.2" + content);
                sb.append(content);
            }
            int waitFor = process.waitFor();
            Log.e("cc.wang", "MainActivity.run.3" + waitFor);
            //0 表示正常停止 1 表示网络已连接，但是无法访问，2 表示网络未连接
            if (waitFor == 0) {
                String string = sb.toString();
                int indexOf = string.lastIndexOf("/");
                String substring = string.substring(indexOf + 1);
                Log.e("cc.wang", "测试结束 ：" + substring);
            } else {
                Log.e("cc.wang", "测试失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (null != process) {
                process.destroy();
            }
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ExecutorService executorService;

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), Util.threadFactory("Ping Thread", false));
        }
        return executorService;
    }


}
