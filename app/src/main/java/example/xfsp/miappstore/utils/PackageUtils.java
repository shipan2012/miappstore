package example.xfsp.miappstore.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/4.
 */
public class PackageUtils {
    /**
     * 获取所有安装应用的packageName的安装集合
     * @return
     */
    public static List<String> getAllPackageInfos(){
        List<String> appinfos = new ArrayList<String>();
        PackageManager packageManager = UiUtils.getContext().getPackageManager();
        List<PackageInfo> packinfos = packageManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        for(PackageInfo info :packinfos){
            String packname = info.packageName;
            appinfos.add(packname);
        }
        return appinfos;
    }

    public static boolean isContains(String packageName){
        List<String> lists = getAllPackageInfos();
        for (String name:lists){
            if (name.equals(packageName))
                return true;
        }
        return false;
    }
}
