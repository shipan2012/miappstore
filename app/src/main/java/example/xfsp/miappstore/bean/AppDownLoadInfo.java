package example.xfsp.miappstore.bean;

/**
 * Created by Administrator on 2015/10/1.
 */
public class AppDownLoadInfo {


    /**
     * apk : AppStore/09a0a514d421a57897452b86e13921c3cd94068fd
     * apkHash : 6caef1e9009c9312f9afd90aa886773d
     * apkSize : 15636551
     * appendExpansionPackSize : 0
     * diffFileSize : 0
     * fitness : 0
     * gamePackSize : 0
     * hdIcon : {"main":"cdc/4fd97ad6eca5c089335e6ca4c883feb9029772d1"}
     * host : http://f2.market.mi-img.com/download/
     * icon : AppStore/06e3214c041f94d5d3fc40b4f0530034b16977076
     * id : 1021
     * mainExpansionPackSize : 0
     * releaseKeyHash : 9fa1a4fc00ae53cd01085edbf98eb983
     * thumbnail : http://file.market.xiaomi.com/thumbnail/
     */

    private String apk;
    private String apkHash;
    private int apkSize;
    private int appendExpansionPackSize;
    private int diffFileSize;
    private int fitness;
    private int gamePackSize;
    private HdIconEntity hdIcon;
    private String host;
    private String icon;
    private int id;
    private int mainExpansionPackSize;
    private String releaseKeyHash;
    private String thumbnail;

    public void setApk(String apk) {
        this.apk = apk;
    }

    public void setApkHash(String apkHash) {
        this.apkHash = apkHash;
    }

    public void setApkSize(int apkSize) {
        this.apkSize = apkSize;
    }

    public void setAppendExpansionPackSize(int appendExpansionPackSize) {
        this.appendExpansionPackSize = appendExpansionPackSize;
    }

    public void setDiffFileSize(int diffFileSize) {
        this.diffFileSize = diffFileSize;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void setGamePackSize(int gamePackSize) {
        this.gamePackSize = gamePackSize;
    }

    public void setHdIcon(HdIconEntity hdIcon) {
        this.hdIcon = hdIcon;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMainExpansionPackSize(int mainExpansionPackSize) {
        this.mainExpansionPackSize = mainExpansionPackSize;
    }

    public void setReleaseKeyHash(String releaseKeyHash) {
        this.releaseKeyHash = releaseKeyHash;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getApk() {
        return apk;
    }

    public String getApkHash() {
        return apkHash;
    }

    public int getApkSize() {
        return apkSize;
    }

    public int getAppendExpansionPackSize() {
        return appendExpansionPackSize;
    }

    public int getDiffFileSize() {
        return diffFileSize;
    }

    public int getFitness() {
        return fitness;
    }

    public int getGamePackSize() {
        return gamePackSize;
    }

    public HdIconEntity getHdIcon() {
        return hdIcon;
    }

    public String getHost() {
        return host;
    }

    public String getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }

    public int getMainExpansionPackSize() {
        return mainExpansionPackSize;
    }

    public String getReleaseKeyHash() {
        return releaseKeyHash;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public static class HdIconEntity {
        /**
         * main : cdc/4fd97ad6eca5c089335e6ca4c883feb9029772d1
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
