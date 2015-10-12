package example.xfsp.miappstore.protocal;

import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;

import example.xfsp.miappstore.http.HttpHelper;
import example.xfsp.miappstore.utils.FileUtils;
import example.xfsp.miappstore.utils.HttpUtils;
import example.xfsp.miappstore.utils.UiUtils;

/**
 * Created by Administrator on 2015/9/20.
 */
public abstract class BaseProtocal<T> {
    protected Gson gson;
    private String key;
    private int index;//第几页

    public BaseProtocal() {
        gson = new Gson();
    }

    /**
     * 1.加载本地数据
     * 2.本地数据没有加载服务器
     * 数据不为空，存入本地
     * 3.解析数据
     *
     * @param index
     * @return
     */
    public T load(final int index) {
        this.index = index;
        if (!HttpUtils.isConnectAvailable(UiUtils.getContext())){
            UiUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //主线程更新
                    Toast.makeText(UiUtils.getContext(),"当前没有网络",Toast.LENGTH_SHORT).show();
                }
            });
        }
        //加载本地数据
        String json = loadLocal(index);
        if (json == null) {
            //请求服务器
            if (HttpUtils.isConnectAvailable(UiUtils.getContext())) {
                json = loadServer();
                if(json!=null){
                    saveLocal(json,index);
                }else{
                    //弹出土司表示没有网
                    UiUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //主线程更新
                            Toast.makeText(UiUtils.getContext(),"当前网络状况不好，请稍后重试",Toast.LENGTH_SHORT).show();
                        }
                    });
                    return null;
                }
            } else {
                //弹出土司表示没有网
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //主线程更新
                        Toast.makeText(UiUtils.getContext(),"没有网络，请联网刷新",Toast.LENGTH_SHORT).show();
                    }
                });
                return null;
            }

        }
        //解析数据
        if (json != null) {
            return parseJson(json);
        } else {
            return null;
        }
    }

    //缓存数据到本地
    private void saveLocal(String json, int index) {
        BufferedWriter bw = null;
        try {
            File dir = FileUtils.getCacheDir();
            //第一行写一个过期时间
            File file = new File(dir, getKey() + "_" + index + "_" + getParams());
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(System.currentTimeMillis() + 1000 * 600 + "");//缓存10分钟之内过期
            bw.newLine();//换行
            bw.write(json);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //加载服务器
    private String loadServer() {
        HttpHelper.HttpResult httpResult = HttpHelper.get(getUrl());
        String json = null;
        try{
            json = httpResult.getString();
        }catch (Exception e){
            e.printStackTrace();
            json = null;
        }finally {
           return json;
        }
    }

    //加载本地数据
    private String loadLocal(int index) {
        File dir = FileUtils.getCacheDir();
        File file = new File(dir, getKey() + "_" + index + "_" + getParams());
        if(!file.exists()){
            //文件不存在，直接返回null
            return null;
        }
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            long outOfData = Long.parseLong(br.readLine());//缓存过期截至时间
            if (System.currentTimeMillis() > outOfData) {
                //过期了，就不再使用缓存
                return null;
            } else {
                String str = null;
                StringWriter sw = new StringWriter();
                while ((str = br.readLine()) != null) {
                    sw.write(str);
                }
                return sw.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //额外带的参数
    protected String getParams() {
        return "";
    }

    //关键词
    public abstract String getKey();

    //解析数据
    public abstract T parseJson(String json);

    //获取url地址
    public abstract String getUrl();
    //获取第几页
    protected int getIndex(){
        return this.index;
    }
}
