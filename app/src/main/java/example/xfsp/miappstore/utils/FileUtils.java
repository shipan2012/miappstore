package example.xfsp.miappstore.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2015/9/20.
 */
public class FileUtils {
    public static final String CACHE = "cache";
    public static final String ICON = "icon";
    public static final String ROOT = "miappstore";
    public static final String APK = "apk";

    /**
     * 获取apk缓存地址
     * @return
     */
    public static File getAPKDir(){
        return getDir(APK);
    }

    /**
     * 获取图片缓存路径
     * @return
     */
    public static File getIconDir(){
        return getDir(ICON);
    }

    /**
     * 获取缓存地址
     * @return
     */
    public static File getCacheDir(){
        return getDir(CACHE);
    }


    //获取缓存路径
    public static File getDir(String cache){
        //如果内存卡可用，存入内存卡，内存卡不可用，存入手机内存
        StringBuilder path = new StringBuilder();
        if(isSdAvailable()){
            path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            path.append(File.separator);// '/'
            path.append(ROOT);// '/mnt/sdcard/miappstore'
            path.append(File.separator);
            path.append(cache);// /mnt/sdcard/miappstore/miappstore
            path.append(File.separator);// '/'
        }else{
            File filesDir = UiUtils.getContext().getCacheDir();
            path.append(filesDir.getAbsolutePath());
            path.append(File.separator);// /data/data/包名/cache/
            path.append(cache);
            path.append(File.separator);// '/'
        }
        File file = new File(path.toString());
        if(!file.exists()){
            //文件不存在，创建文件夹
            file.mkdirs();
        }
        return file;
    }

    //判断SD卡是否可用
    private static boolean isSdAvailable(){
        if(Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)){
            return true;
        }else{
            return false;
        }
    }
}
