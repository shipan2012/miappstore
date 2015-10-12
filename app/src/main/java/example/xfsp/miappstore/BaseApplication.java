package example.xfsp.miappstore;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Administrator on 2015/9/20.
 */
public class BaseApplication extends com.activeandroid.app.Application{
    private static Application application;
    private static int mainThreadId;//主线程号
    private static Handler handler;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        mainThreadId = android.os.Process.myTid();
        handler = new Handler();
    }
    public static Context getApplication(){
        return application;
    }

    //获取主线程的Handler
    public static Handler getHandler(){
        return handler;
    }

    //获取主线程所在的进程号
    public static int getMainThreadId(){
        return mainThreadId;
    }
}
