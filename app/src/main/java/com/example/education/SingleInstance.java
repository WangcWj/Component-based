package com.example.education;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/11/10
 */
public class SingleInstance {

    private volatile static SingleInstance singleInstance;

    public static SingleInstance getInstance() {
        if (singleInstance == null) {
            synchronized (SingleInstance.class) {
                if (singleInstance == null) {
                    singleInstance = new SingleInstance();
                }
            }
        }
        return singleInstance;
    }

}
