package example.xfsp.miappstore.protocal.impl;

import org.json.JSONException;
import org.json.JSONObject;

import example.xfsp.miappstore.bean.AppDetail;
import example.xfsp.miappstore.http.Constant;
import example.xfsp.miappstore.protocal.BaseProtocal;

/**
 * Created by Administrator on 2015/9/28.
 */
public abstract class AppDetailProtocal extends BaseProtocal<AppDetail> {
    @Override
    public String getKey() {
        return "AppDetails";
    }

    @Override
    public AppDetail parseJson(String json) {
        AppDetail appDetail;
        try {
            appDetail = new AppDetail();
            JSONObject jsonObject = new JSONObject(json);
            String host = jsonObject.getString("host");
            JSONObject app = jsonObject.getJSONObject("app");
            String id = app.getString("id");
            String changeLog = app.getString("changeLog");
            String displayName = app.getString("displayName");
            String introduction = app.getString("introduction");
            String publishername = app.getString("publisherName");
            double ratingScore = app.getDouble("ratingScore");
            String icon = app.getString("icon");
            String screenshot = app.getString("screenshot");
            double ratingTotalCount = app.getDouble("ratingTotalCount");
            int versionCode = app.getInt("versionCode");
            String versionName = app.getString("versionName");
            long updateTime = app.getLong("updateTime");
            long apkSize = app.getLong("apkSize");
            appDetail.setHost(host);
            appDetail.setId(id);
            appDetail.setChangeLog(changeLog);
            appDetail.setDisPlayName(displayName);
            appDetail.setIntroduction(introduction);
            appDetail.setPublisherName(publishername);
            appDetail.setRatingScore(ratingScore);
            appDetail.setIcon(icon);
            appDetail.setScreenshot(screenshot);
            appDetail.setRatingTotalCount(ratingTotalCount);
            appDetail.setVersionCode(versionCode);
            appDetail.setVersionName(versionName);
            appDetail.setUpdateTime(updateTime);
            appDetail.setApkSize(apkSize);
            return appDetail;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getUrl() {
        return Constant.DetailPreUrl + getAppId() + Constant.DetailLastUrl;
    }

    public abstract String getAppId();

    @Override
    protected String getParams() {
        return getAppId();
    }
}
