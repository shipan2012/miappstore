package example.xfsp.miappstore.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * App实体类
 */
public class ListAppEntity implements Parcelable {

    /**
     * apkSize : 32250136
     * displayName : 微信
     * hdIcon : {"main":"AppStore/0c0e4408937f51c53b4edf08a4a62530bd542675e"}
     * icon : AppStore/f4ec7499-a7bc-48af-813d-3aaf86c17e8a
     * id : 1122
     * level1CategoryId : 2
     * level1CategoryName : 聊天与社交
     * level2CategoryId : 0
     * packageName : com.tencent.mm
     * publisherName : 腾讯科技（深圳）有限公司广州分公司
     * ratingScore : 2.5
     * screenshot : AppStore/0bafc4f9bdb63f3c60f06f52423ffe61cec41b124,AppStore/01afc449bcbd343cf1f06a52417ff26ecf66831bb,AppStore/0f0188474b77f49870f701f1e89b2763d0ab14688,AppStore/0bafc4f9bdb63f3c6ef06052483ff361c4c41b124,AppStore/0aafc449bcbd343cf1f0625241fff267cf66831bb
     * source :
     * suitableType : 2
     * updateTime : 1441121473335
     * versionCode : 621
     * versionName : 6.2.5.51_rfe7d7c5
     * videoId : 0
     */

    private int apkSize;
    private String displayName;
    private HdIconEntity hdIcon;
    private String icon;
    private int id;
    private int level1CategoryId;
    private String level1CategoryName;
    private int level2CategoryId;
    private String packageName;
    private String publisherName;
    private float ratingScore;
    private String screenshot;
    private String source;
    private int suitableType;
    private long updateTime;
    private int versionCode;
    private String versionName;
    private int videoId;

    public void setApkSize(int apkSize) {
        this.apkSize = apkSize;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setHdIcon(HdIconEntity hdIcon) {
        this.hdIcon = hdIcon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel1CategoryId(int level1CategoryId) {
        this.level1CategoryId = level1CategoryId;
    }

    public void setLevel1CategoryName(String level1CategoryName) {
        this.level1CategoryName = level1CategoryName;
    }

    public void setLevel2CategoryId(int level2CategoryId) {
        this.level2CategoryId = level2CategoryId;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setRatingScore(float ratingScore) {
        this.ratingScore = ratingScore;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setSuitableType(int suitableType) {
        this.suitableType = suitableType;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getApkSize() {
        return apkSize;
    }

    public String getDisplayName() {
        return displayName;
    }

    public HdIconEntity getHdIcon() {
        return hdIcon;
    }

    public String getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }

    public int getLevel1CategoryId() {
        return level1CategoryId;
    }

    public String getLevel1CategoryName() {
        return level1CategoryName;
    }

    public int getLevel2CategoryId() {
        return level2CategoryId;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public float getRatingScore() {
        return ratingScore;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public String getSource() {
        return source;
    }

    public int getSuitableType() {
        return suitableType;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVideoId() {
        return videoId;
    }

    public static class HdIconEntity implements Parcelable {

        /**
         * main : AppStore/0c0e4408937f51c53b4edf08a4a62530bd542675e
         */

        private String main;

        public void setMain(String main) {
            this.main = main;
        }

        public String getMain() {
            return main;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.main);
        }

        public HdIconEntity() {
        }

        protected HdIconEntity(Parcel in) {
            this.main = in.readString();
        }

        public static final Creator<HdIconEntity> CREATOR = new Creator<HdIconEntity>() {
            public HdIconEntity createFromParcel(Parcel source) {
                return new HdIconEntity(source);
            }

            public HdIconEntity[] newArray(int size) {
                return new HdIconEntity[size];
            }
        };
    }

    @Override
    public String toString() {
        return "ListAppEntity{" +
                "apkSize=" + apkSize +
                ", displayName='" + displayName + '\'' +
                ", hdIcon=" + hdIcon +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", level1CategoryId=" + level1CategoryId +
                ", level1CategoryName='" + level1CategoryName + '\'' +
                ", level2CategoryId=" + level2CategoryId +
                ", packageName='" + packageName + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", ratingScore=" + ratingScore +
                ", screenshot='" + screenshot + '\'' +
                ", source='" + source + '\'' +
                ", suitableType=" + suitableType +
                ", updateTime=" + updateTime +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", videoId=" + videoId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.apkSize);
        dest.writeString(this.displayName);
        dest.writeParcelable(this.hdIcon, flags);
        dest.writeString(this.icon);
        dest.writeInt(this.id);
        dest.writeInt(this.level1CategoryId);
        dest.writeString(this.level1CategoryName);
        dest.writeInt(this.level2CategoryId);
        dest.writeString(this.packageName);
        dest.writeString(this.publisherName);
        dest.writeFloat(this.ratingScore);
        dest.writeString(this.screenshot);
        dest.writeString(this.source);
        dest.writeInt(this.suitableType);
        dest.writeLong(this.updateTime);
        dest.writeInt(this.versionCode);
        dest.writeString(this.versionName);
        dest.writeInt(this.videoId);
    }

    public ListAppEntity() {
    }

    protected ListAppEntity(Parcel in) {
        this.apkSize = in.readInt();
        this.displayName = in.readString();
        this.hdIcon = in.readParcelable(HdIconEntity.class.getClassLoader());
        this.icon = in.readString();
        this.id = in.readInt();
        this.level1CategoryId = in.readInt();
        this.level1CategoryName = in.readString();
        this.level2CategoryId = in.readInt();
        this.packageName = in.readString();
        this.publisherName = in.readString();
        this.ratingScore = in.readFloat();
        this.screenshot = in.readString();
        this.source = in.readString();
        this.suitableType = in.readInt();
        this.updateTime = in.readLong();
        this.versionCode = in.readInt();
        this.versionName = in.readString();
        this.videoId = in.readInt();
    }

    public static final Parcelable.Creator<ListAppEntity> CREATOR = new Parcelable.Creator<ListAppEntity>() {
        public ListAppEntity createFromParcel(Parcel source) {
            return new ListAppEntity(source);
        }

        public ListAppEntity[] newArray(int size) {
            return new ListAppEntity[size];
        }
    };
}