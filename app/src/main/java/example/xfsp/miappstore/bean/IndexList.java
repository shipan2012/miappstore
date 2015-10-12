package example.xfsp.miappstore.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/9/20.
 */
public class IndexList {
    private AdExtrasMapEntity adExtrasMap;
    private String experimentalId;
    private boolean hasMore;
    private String host;
    private String thumbnail;
    private List<ListAppEntity> listApp;
    private List<ListExtrasAppEntity> listExtrasApp;
    private List<ListExtrasGameAppEntity> listExtrasGameApp;
    private List<Integer> positionList;

    public void setAdExtrasMap(AdExtrasMapEntity adExtrasMap) {
        this.adExtrasMap = adExtrasMap;
    }

    public void setExperimentalId(String experimentalId) {
        this.experimentalId = experimentalId;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setListApp(List<ListAppEntity> listApp) {
        this.listApp = listApp;
    }

    public void setListExtrasApp(List<ListExtrasAppEntity> listExtrasApp) {
        this.listExtrasApp = listExtrasApp;
    }

    public void setListExtrasGameApp(List<ListExtrasGameAppEntity> listExtrasGameApp) {
        this.listExtrasGameApp = listExtrasGameApp;
    }

    public void setPositionList(List<Integer> positionList) {
        this.positionList = positionList;
    }

    public AdExtrasMapEntity getAdExtrasMap() {
        return adExtrasMap;
    }

    public String getExperimentalId() {
        return experimentalId;
    }

    public boolean getHasMore() {
        return hasMore;
    }

    public String getHost() {
        return host;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public List<ListAppEntity> getListApp() {
        return listApp;
    }

    public List<ListExtrasAppEntity> getListExtrasApp() {
        return listExtrasApp;
    }

    public List<ListExtrasGameAppEntity> getListExtrasGameApp() {
        return listExtrasGameApp;
    }

    public List<Integer> getPositionList() {
        return positionList;
    }

    public static class AdExtrasMapEntity {
    }

    public static class ListExtrasAppEntity {
        /**
         * apkSize : 11192985
         * displayName : 暴力反击
         * icon : AppStore/708a87c1-4282-4f3b-ba0c-bfcc80e968ba
         * id : 66266
         * level1CategoryId : 15
         * level1CategoryName : 游戏
         * level2CategoryId : 21
         * level2CategoryName : 格斗快打
         * packageName : com.chillingo.fightback.android.rowplay
         * publisherName : Chillingo International
         * ratingScore : 4.5
         * screenshot : AppStore/76aa5b76-768c-456b-bd62-bd8889e7b5ab,AppStore/7f954acb-d1ff-4abb-80e9-720f3fc81c5f,AppStore/fb1666c3-1da6-4363-860d-d36bc837cc8c,AppStore/513192aa-c0aa-4ab1-a02e-bb4350074980
         * suitableType : 2
         * updateTime : 1404874843439
         * versionCode : 1803
         * versionName : 1.8.0
         * videoId : 0
         */

        private int apkSize;
        private String displayName;
        private String icon;
        private int id;
        private int level1CategoryId;
        private String level1CategoryName;
        private int level2CategoryId;
        private String level2CategoryName;
        private String packageName;
        private String publisherName;
        private double ratingScore;
        private String screenshot;
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

        public void setLevel2CategoryName(String level2CategoryName) {
            this.level2CategoryName = level2CategoryName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public void setPublisherName(String publisherName) {
            this.publisherName = publisherName;
        }

        public void setRatingScore(double ratingScore) {
            this.ratingScore = ratingScore;
        }

        public void setScreenshot(String screenshot) {
            this.screenshot = screenshot;
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

        public String getLevel2CategoryName() {
            return level2CategoryName;
        }

        public String getPackageName() {
            return packageName;
        }

        public String getPublisherName() {
            return publisherName;
        }

        public double getRatingScore() {
            return ratingScore;
        }

        public String getScreenshot() {
            return screenshot;
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
    }

    public static class ListExtrasGameAppEntity {
        /**
         * apkSize : 12404224
         * displayName : 星际大战
         * hdIcon : {"main":"AppStore/e8b0a55b-0837-4d5a-95bd-94a422eaa70a"}
         * icon : AppStore/1e0882a2-aa34-47d5-adf3-1ba8cf37bf94
         * id : 63063
         * level1CategoryId : 15
         * level1CategoryName : 游戏
         * level2CategoryId : 25
         * level2CategoryName : 飞行空战
         * packageName : com.wedo1.StarWar
         * publisherName : 厦门云域网络科技有限公司
         * ratingScore : 2.5
         * screenshot : AppStore/71aa9562-12fe-4e2a-b8b3-350952f465bd,AppStore/669c167f-31c6-4ee9-8fdd-a114961ed880,AppStore/22ad3c69-8c58-4bb3-b70d-ec510c1284ac,AppStore/010e0580-f981-4b06-b223-0d024c0665dd
         * source :
         * suitableType : 2
         * updateTime : 1401340511291
         * versionCode : 3
         * versionName : 1.0.2
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
        private String level2CategoryName;
        private String packageName;
        private String publisherName;
        private double ratingScore;
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

        public void setLevel2CategoryName(String level2CategoryName) {
            this.level2CategoryName = level2CategoryName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public void setPublisherName(String publisherName) {
            this.publisherName = publisherName;
        }

        public void setRatingScore(double ratingScore) {
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

        public String getLevel2CategoryName() {
            return level2CategoryName;
        }

        public String getPackageName() {
            return packageName;
        }

        public String getPublisherName() {
            return publisherName;
        }

        public double getRatingScore() {
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

        public static class HdIconEntity {
            /**
             * main : AppStore/e8b0a55b-0837-4d5a-95bd-94a422eaa70a
             */

            private String main;

            public void setMain(String main) {
                this.main = main;
            }

            public String getMain() {
                return main;
            }
        }
    }
}