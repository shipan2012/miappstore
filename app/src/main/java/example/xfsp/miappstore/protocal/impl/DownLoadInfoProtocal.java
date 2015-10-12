package example.xfsp.miappstore.protocal.impl;

import com.google.gson.Gson;

import example.xfsp.miappstore.bean.AppDownLoadInfo;
import example.xfsp.miappstore.http.Constant;
import example.xfsp.miappstore.protocal.BaseProtocal;

/**
 * Created by Administrator on 2015/9/28.
 */
public abstract class DownLoadInfoProtocal extends BaseProtocal<AppDownLoadInfo> {

    private AppDownLoadInfo appDownLoadInfo;

    @Override
    public String getKey() {
        return "DownLoadInfo";
    }

    @Override
    public AppDownLoadInfo parseJson(String json) {
        Gson gson = new Gson();
        appDownLoadInfo = gson.fromJson(json, AppDownLoadInfo.class);
        return appDownLoadInfo;
    }

    @Override
    public String getUrl() {
        return Constant.DownloadPreUrl + getAppId() + Constant.DownloadLastUrl;
    }

    public abstract String getAppId();

    @Override
    protected String getParams() {
        return getAppId();
    }
}
