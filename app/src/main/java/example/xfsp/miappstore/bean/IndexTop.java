package example.xfsp.miappstore.bean;

/**
 * Created by Administrator on 2015/9/24.
 */
public class IndexTop {
    private String host;//主机地址
    private int featuredType;//类型，1.app，2.专题
    private String mticon;//图片地址
    private String title;//标题
    private long relatedId;//详情id

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getFeaturedType() {
        return featuredType;
    }

    public void setFeaturedType(int featuredType) {
        this.featuredType = featuredType;
    }

    public String getMticon() {
        return mticon;
    }

    public void setMticon(String mticon) {
        this.mticon = mticon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(long relatedId) {
        this.relatedId = relatedId;
    }
}
