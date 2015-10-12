package example.xfsp.miappstore.utils.download;

import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import example.xfsp.miappstore.bean.AppDownLoadInfo;
import example.xfsp.miappstore.bean.DownloadRecord;
import example.xfsp.miappstore.bean.ListAppEntity;
import example.xfsp.miappstore.http.Constant;
import example.xfsp.miappstore.manager.ThreadManager;
import example.xfsp.miappstore.protocal.impl.DownLoadInfoProtocal;
import example.xfsp.miappstore.utils.FileUtils;
import example.xfsp.miappstore.utils.UiUtils;

public class DownloadManager {
    public static final int STATE_NONE = 0;
    public static final int STATE_WAITING = 1;
    public static final int STATE_DOWNLOADING = 2;
    public static final int STATE_PAUSED = 3;
    public static final int STATE_DOWNLOADED = 4;
    public static final int STATE_ERROR = 5;
    public static final int STATE_LINKING = 6;

    private static int MAX_MISSION_COUNT = 5; //设置线程池最大线程个数
    private static DownloadManager Instance;
    protected ThreadPoolExecutor mExecutorService;
    private TimeUnit mTimeUnit = TimeUnit.SECONDS; //时间单位

    //临时存放各个文件下载信息
    private Map<Long, DownloadRecord> mDownloadMap = new ConcurrentHashMap<Long, DownloadRecord>();
    private List<DownloadObserver> mObservers = new ArrayList<DownloadObserver>();
    private Map<Long, DownLoadTask> mTaskMap = new ConcurrentHashMap<Long, DownLoadTask>();
    private List<DownloadRecord> mDownloadingRecords = new ArrayList<DownloadRecord>();
    private DownloadRecord record;

    private Object a = new Object();

    public static synchronized DownloadManager getInstance() {
        if (Instance == null || Instance.mExecutorService.isShutdown()) {
            Instance = new DownloadManager();
        }
        return Instance;
    }

    private DownloadManager() {
        mExecutorService = new ThreadPoolExecutor(MAX_MISSION_COUNT, MAX_MISSION_COUNT, 15, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());
    }

    /**
     * 查询记录信息
     *
     * @param id
     * @return
     */
    public synchronized DownloadRecord getRecord(long id) {
        DownloadRecord downloadRecord = mDownloadMap.get(id);
        return downloadRecord;
    }

    /**
     * 添加记录信息
     *
     * @param id
     * @return
     */
    public synchronized void putRecord(long id, DownloadRecord record) {
        mDownloadMap.put(id, record);
    }

    //获取所有正在下载的任务信息
    public List<DownloadRecord> getmDownloadingRecords() {
        return mDownloadingRecords;
    }

    /**
     * 注册观察者
     */
    public void registerObserver(DownloadObserver observer) {
        synchronized (mObservers) {
            if (!mObservers.contains(observer)) {
                mObservers.add(observer);
            }
        }
    }

    /**
     * 反注册观察者
     */
    public void unRegisterObserver(DownloadObserver observer) {
        synchronized (mObservers) {
            if (mObservers.contains(observer)) {
                mObservers.remove(observer);
            }
        }
    }

    /**
     * 当下载状态发送改变的时候回调
     */
    public void notifyDownloadStateChanged(final DownloadRecord record) {
        synchronized (mObservers) {
            UiUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (DownloadObserver observer : mObservers) {
                        observer.onDownloadStateChanged(record);
                    }
                }
            }, 0);

        }
    }


    //下载速度和进度发生变化
    protected final void notifyDownloadProAndSpeed(final DownloadRecord record) {
        if (mObservers != null && mObservers.size() != 0) {
            long currentNotifyTime = System.currentTimeMillis();//当前通知时间
            long notifyDuration = currentNotifyTime - record.mPreviousNotifyTime;
            //两次下载时间花销
            long spend = mTimeUnit.convert(notifyDuration,
                    TimeUnit.MILLISECONDS);
            //如果两次间隔大于时间间隔
            if (spend >= record.mProgressNotifyInterval) {
                record.mPreviousNotifyTime = currentNotifyTime;
                //通知所有的观察者速度变化了
                synchronized (mObservers) {
                    UiUtils.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (DownloadObserver observer : mObservers) {
                                observer.onDownloadProAndSpeedChange(record);
                            }
                        }
                    }, 0);
                }
            }

            //上一次下载的时间
            long speedRecordDuration = currentNotifyTime
                    - record.mLastSecondDownloadTime;
            if (TimeUnit.MILLISECONDS.toSeconds(speedRecordDuration) >= 1) {
                //记录上一秒下载时间
                record.mLastSecondDownloadTime = currentNotifyTime;
                //上一秒已经下载了多少
                record.mLastSecondDownload = record.getDownloadedSize();
            }
        }
    }


    public void downLoad(ListAppEntity appEntity) {
        // 先判断是否有这个app的下载信息
        DownloadRecord record = getRecord(appEntity.getId());
        if (record == null) {
            // 如果没有，则根据appInfo创建一个没有URL链接的下载地址
            record = DownloadRecord.clone(appEntity);
        } else {
            //如果不为空，先移除之前保存的状态
            removeListItem(mDownloadingRecords, record);
            notifyDownloadProAndSpeed(record);
        }
        // 判断状态是否为STATE_LINKING,STATE_NONE、STATE_PAUSED、STATE_ERROR。只有这3种状态才能进行下载，其他状态不予处理
        if (record.getStatus() == STATE_NONE
                || record.getStatus() == STATE_PAUSED
                || record.getStatus() == STATE_ERROR) {
            // 下载之前，把状态设置为STATE_WAITING，因为此时并没有产开始下载，只是把任务放入了线程池中，当任务真正开始执行时，才会改为STATE_DOWNLOADING

            DownLoadTask task = new DownLoadTask(record);// 创建一个下载任务，放入线程池
            mTaskMap.put(record.getAppId(), task);
            record.setStatus(STATE_WAITING);
            mDownloadingRecords.add(record);
            if (getRecord(appEntity.getId()) == null) {
                //新建任务信息时，才加进去
                putRecord(record.getAppId(), record);
            }
            notifyDownloadProAndSpeed(record);//同时通知速度和进度发生了改变，用来初始化数据
            notifyDownloadStateChanged(record);// 每次状态发生改变，都需要回调该方法通知所有观察者
            ThreadManager.getInstance().createDownloadPool().execute(task);
        }
    }

    /**
     * 传入记录获取下载地址
     *
     * @param record
     * @return
     */
    private String getDownloadUrl(DownloadRecord record) {
        //从appEntiy中筛选出有用的信息
        //连接服务器取出url
        final long appId = record.getAppId();
        final String[] apkUrl = new String[1];
        DownLoadInfoProtocal downLoadInfoProtocal = new DownLoadInfoProtocal() {
            @Override
            public String getAppId() {
                return appId + "";
            }
        };
        AppDownLoadInfo load = downLoadInfoProtocal.load(0);
        apkUrl[0] = load.getApk();
        return Constant.BaseDownloadUrl + apkUrl[0];
    }

    /**
     * 暂停下载
     *
     * @param data
     */
    public void pause(ListAppEntity data) {
        //找到任务，修改状态值,同时移除线程,取消任务
        DownloadRecord record = getRecord(data.getId());
        if (record != null) {
            record.setStatus(STATE_PAUSED);
            DownLoadTask task = mTaskMap.remove(data.getId());
            ThreadManager.getInstance().createDownloadPool().execute(task);
            notifyDownloadStateChanged(record);
        }
    }


    public class DownLoadTask implements Runnable {
        private DownloadRecord record;

        public DownLoadTask(DownloadRecord record) {
            this.record = record;
        }

        public void run() {
            //判断记录中有没有URL
            if (record.getDownloadUrl() == null) {
                //没有获取URL，通知状态改变了
                record.setStatus(STATE_LINKING);
                notifyDownloadStateChanged(record);
                String url = getDownloadUrl(record);
                record.setDownloadUrl(url);
            }
            record.setStatus(STATE_DOWNLOADING);// 改变下载状态
            record.startTime = System.currentTimeMillis(); //开始时间，系统当前时间
            notifyDownloadStateChanged(record);

            InputStream in = null;
            RandomAccessFile fos = null;
            File accessFile = null;
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection) new URL(record.getDownloadUrl())
                        .openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Range", "bytes=" + record.getDownloadedSize() + "-");
                //当文件没有开始下载，记录文件大小
                if (record.FileSize <= 0)
                    record.setFileSize(httpURLConnection.getContentLength());

                in = httpURLConnection.getInputStream();
                accessFile = new File(FileUtils.getAPKDir(), record.getSaveFileName() + ".apk");
                fos = new RandomAccessFile(accessFile, "rw");
                fos.seek(record.getDownloadedSize());
                byte data[] = new byte[1024];
                int count;
                //接口头文件准备好了
                while (record.getStatus() == STATE_DOWNLOADING && (count = in.read(data, 0, 1024)) != -1) {
                    fos.write(data, 0, count);
                    record.DownloadedSize += count;
                    record.endTime = System.currentTimeMillis();
                    record.setDownloadedPercentage(getPercentage(record));
                    notifyDownloadProAndSpeed(record);//通知速度和进度发生了变化
                }

                // 相等说明下载完了
                if (record.getDownloadedSize() == record.getFileSize()) {
//                    mDownloadingRecords.remove(record);//先移除再通知
                    removeListItem(mDownloadingRecords, record);
                    record.setStatus(STATE_DOWNLOADED);
                    notifyDownloadStateChanged(record);
                } else if (record.getStatus() == STATE_PAUSED) {// 判断状态
                    notifyDownloadStateChanged(record);
                }
            } catch (Exception e) {
                //下载错误
                record.setStatus(STATE_ERROR);
                notifyDownloadStateChanged(record);
                record.setDownloadedSize(0);// 错误状态需要删除文件
                if (accessFile != null && accessFile.exists()) {
                    accessFile.delete();
                }
            } finally {
                try {
                    if (in != null)
                        in.close();
                    if (fos != null)
                        fos.close();
                } catch (IOException e) {
                    record.setStatus(STATE_ERROR);
                    notifyDownloadStateChanged(record);
                }
            }
        }
    }

    /**
     * 移除list中的record
     *
     * @param list
     * @param record
     */
    public void removeListItem(List list, DownloadRecord record) {
        Iterator<DownloadRecord> it = list.iterator();
        while (it.hasNext()) {
            DownloadRecord downloadRecord = it.next();
            if (downloadRecord.getAppId() == record.getAppId()) {
                it.remove();
            }
        }
    }


    //获取平均速度，从开始下载，到现在为止
    public String getAverageReadableSpeed(DownloadRecord record) {
        return FileUtil.getReadableSpeed(record.getDownloadedSize(), getTimeSpend(record),
                TimeUnit.MILLISECONDS);
    }


    /**
     * 获取可读的百分比大小
     *
     * @param record
     * @return
     */
    public String getReadablePercentage(DownloadRecord record) {
        StringBuilder builder = new StringBuilder();
        builder.append(getPercentage(record));
        builder.append("%");
        return builder.toString();
    }

    /**
     * @return 已经下载文件大小占文件总大小的百分比
     */
    public int getPercentage(DownloadRecord downloadRecord) {
        if (downloadRecord.getFileSize() == 0) {
            return 0;
        } else {
            return (int) (downloadRecord.getDownloadedSize() * 100.0f / downloadRecord.getFileSize());
        }
    }

    /**
     * 从开始到截至时间间隔
     *
     * @param record
     * @return
     */
    public long getTimeSpend(DownloadRecord record) {
        if (record.endTime != 0) {
            return (record.endTime - record.startTime);
        } else {
            return (System.currentTimeMillis() - record.startTime);
        }
    }

    public String getAccurateReadableSpeed(DownloadRecord record) {
        return FileUtil.getReadableSize(record.getDownloadedSize() - record.getmLastSecondDownload(),
                false) + "/s";
    }

    /**
     * 获取精确的的已经下载大小
     *
     * @param record
     * @return
     */
    public String getAccurateReadableSize(DownloadRecord record) {
        return FileUtil.getReadableSize(record.getDownloadedSize());
    }

    /**
     * 获取可读的文件大小
     *
     * @return
     */
    public String getReadableSize(DownloadRecord record) {
        return FileUtil.getReadableSize(record.getFileSize());
    }


    protected String getSafeFilename(String name) {
        return name.replaceAll("[\\\\|\\/|\\:|\\*|\\?|\\\"|\\<|\\>|\\|]", "-");
    }


    //设置最大线程数
    public void setMaxMissionCount(int count) {
        if (Instance == null && count > 0)
            MAX_MISSION_COUNT = count;
        else
            throw new IllegalStateException("Can not change max mission count after getInstance been called");
    }

    /**
     * 观察者接口
     */
    public interface DownloadObserver {
        //进度和速度发生了变化
        public void onDownloadProAndSpeedChange(DownloadRecord record);

        public void onDownloadStateChanged(DownloadRecord record);
    }

    /**
     * 安装应用
     */
    public synchronized void install(long appId ) {
        DownloadRecord downloadRecord = getRecord(appId);// 找出下载信息
        if (downloadRecord != null) {// 发送安装的意图
            Intent installIntent = new Intent(Intent.ACTION_VIEW);
            installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            installIntent.setDataAndType(Uri.parse("file://" + downloadRecord.getSaveDir()),
                    "application/vnd.android.package-archive");
            UiUtils.getContext().startActivity(installIntent);
        }
    }
}
