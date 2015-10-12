package example.xfsp.miappstore.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 请求数据工具类
 */
public class HttpUtils {
    //判断当前网络是否可用
    public static boolean isConnectAvailable(Context context){
        if(context!=null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
            if (activeNetworkInfo!=null){
                return activeNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
