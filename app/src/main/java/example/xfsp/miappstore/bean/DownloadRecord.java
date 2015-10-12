package example.xfsp.miappstore.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

import example.xfsp.miappstore.http.Constant;
import example.xfsp.miappstore.utils.FileUtils;


@Table(name = "DownloadRecord")
public class DownloadRecord extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    public static DownloadRecord clone(ListAppEntity entity) {
        DownloadRecord downloadRecord = new DownloadRecord();
        //上次通知时间,初始化系统时间
        downloadRecord.mPreviousNotifyTime = System.currentTimeMillis();
        downloadRecord.appId = entity.getId();
        downloadRecord.SaveFileName = entity.getDisplayName();
        downloadRecord.FileSize = 0;
        downloadRecord.DownloadedSize = 0;
        downloadRecord.Status = STATUS.STATE_NONE;
        downloadRecord.SaveDir = FileUtils.getAPKDir()+"/" + entity.getDisplayName() + ".apk";
        downloadRecord.DownloadedPercentage = 0;//默认进度为0
        downloadRecord.HomeIcon = Constant.BaseDownloadUrl+entity.getIcon();//图片地址
        return downloadRecord;
    }

    public class STATUS {
        public static final int STATE_NONE = 0;
        //等待
        public static final int STATE_WAITING = 1;
        //下载中
        public static final int STATE_DOWNLOADING = 2;
        //暂停
        public static final int STATE_PAUSED = 3;
        //下载完毕
        public static final int STATE_DOWNLOADED = 4;
        //下载失败
        public static final int STATE_ERROR = 5;
        //连接中
        public static final int STATE_LINKING = 6;

    }

    public int mProgressNotifyInterval = 1; //两次时间刷新间隔
    public long mLastSecondDownloadTime = 0; //上次下载的时间
    public long mLastSecondDownload = 0;//上次下载多少
    public long currentNotifyTime;//现在通知时间
    public long mPreviousNotifyTime;  //上次通知时间
    public long startTime;  //开始下载时间
    public long endTime;   //上次下载时间
    /**
     * DownLoadDetail存储的信息
     */

    @Column(name = "appId")
    public long appId; //下载id
    @Column(name = "HostUrl")
    public String HostUrl; //HostUrl连接
    @Column(name = "DownloadUrl")
    public String DownloadUrl; //下载链接
    @Column(name = "HomeIcon")
    public String HomeIcon; //图片链接

    @Column(name = "FileSize")
    public long FileSize; //文件总大小
    @Column(name = "DownloadedSize")
    public long DownloadedSize;  //已经下载文件大小
    @Column(name = "DownloadedPercentage")
    public long DownloadedPercentage; //已经下载的百分比
    @Column(name = "Status")
    public int Status;  //当前状态
    @Column(name = "SaveDir")
    public String SaveDir; //存储位置
    @Column(name = "SaveFileName")
    public String SaveFileName; //存储名称


    //新建一个任务默认链接中的状态
    public DownloadRecord() {
    }

    public int getmProgressNotifyInterval() {
        return mProgressNotifyInterval;
    }

    public void setmProgressNotifyInterval(int mProgressNotifyInterval) {
        this.mProgressNotifyInterval = mProgressNotifyInterval;
    }

    public long getmLastSecondDownloadTime() {
        return mLastSecondDownloadTime;
    }

    public void setmLastSecondDownloadTime(long mLastSecondDownloadTime) {
        this.mLastSecondDownloadTime = mLastSecondDownloadTime;
    }

    public long getmLastSecondDownload() {
        return mLastSecondDownload;
    }

    public void setmLastSecondDownload(long mLastSecondDownload) {
        this.mLastSecondDownload = mLastSecondDownload;
    }

    public long getCurrentNotifyTime() {
        return currentNotifyTime;
    }

    public void setCurrentNotifyTime(long currentNotifyTime) {
        this.currentNotifyTime = currentNotifyTime;
    }

    public long getmPreviousNotifyTime() {
        return mPreviousNotifyTime;
    }

    public void setmPreviousNotifyTime(long mPreviousNotifyTime) {
        this.mPreviousNotifyTime = mPreviousNotifyTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getHostUrl() {
        return HostUrl;
    }

    public void setHostUrl(String hostUrl) {
        HostUrl = hostUrl;
    }

    public String getDownloadUrl() {
        return DownloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        DownloadUrl = downloadUrl;
    }

    public String getHomeIcon() {
        return HomeIcon;
    }

    public void setHomeIcon(String homeIcon) {
        HomeIcon = homeIcon;
    }


    public long getFileSize() {
        return FileSize;
    }

    public void setFileSize(long fileSize) {
        FileSize = fileSize;
    }

    public long getDownloadedSize() {
        return DownloadedSize;
    }

    public void setDownloadedSize(long downloadedSize) {
        DownloadedSize = downloadedSize;
    }

    public long getDownloadedPercentage() {
        return DownloadedPercentage;
    }

    public void setDownloadedPercentage(long downloadedPercentage) {
        DownloadedPercentage = downloadedPercentage;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getSaveDir() {
        return SaveDir;
    }

    public void setSaveDir(String saveDir) {
        SaveDir = saveDir;
    }

    public String getSaveFileName() {
        return SaveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        SaveFileName = saveFileName;
    }
}

