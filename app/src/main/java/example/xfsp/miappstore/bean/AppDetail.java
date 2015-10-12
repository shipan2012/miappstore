package example.xfsp.miappstore.bean;

/**
 * Created by Administrator on 2015/9/28.
 */
public class AppDetail {
    /**
     * id   String
     * host地址 host:String
     * publisherName:String
     * 名称 disPlayName:String
     * 图标 icon:String
     * 评分 ratingScore:float
     * 截图数组：String
     * 功能介绍： introduction:String
     * 更新日志： changeLog:String
     * 多少人评分 :ratingTotalCount:doudle
     *
     */

    private String id;
    private String host;
    private String publisherName;
    private String disPlayName;
    private String icon;
    private double ratingScore;
    private String screenshot;
    private String introduction;
    private String changeLog;
    private double ratingTotalCount;
    private long updateTime;
    private String versionName;
    private int versionCode;
    private long apkSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getDisPlayName() {
        return disPlayName;
    }

    public void setDisPlayName(String disPlayName) {
        this.disPlayName = disPlayName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(double ratingScore) {
        this.ratingScore = ratingScore;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    public double getRatingTotalCount() {
        return ratingTotalCount;
    }

    public void setRatingTotalCount(double ratingTotalCount) {
        this.ratingTotalCount = ratingTotalCount;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public long getApkSize() {
        return apkSize;
    }

    public void setApkSize(long apkSize) {
        this.apkSize = apkSize;
    }

    @Override
    public String toString() {
        return "AppDetail{" +
                "id='" + id + '\'' +
                ", host='" + host + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", disPlayName='" + disPlayName + '\'' +
                ", icon='" + icon + '\'' +
                ", ratingScore=" + ratingScore +
                ", screenshot='" + screenshot + '\'' +
                ", introduction='" + introduction + '\'' +
                ", changeLog='" + changeLog + '\'' +
                ", ratingTotalCount=" + ratingTotalCount +
                ", updateTime=" + updateTime +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", apkSize=" + apkSize +
                '}';
    }
}
