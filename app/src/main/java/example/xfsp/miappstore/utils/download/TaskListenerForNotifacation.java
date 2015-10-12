package example.xfsp.miappstore.utils.download;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.activity.DownLoadActivity;
import example.xfsp.miappstore.adapter.DownloadingAdapter;
import example.xfsp.miappstore.bean.DownloadRecord;
import example.xfsp.miappstore.utils.UiUtils;

/**
 * Created by Administrator on 2015/10/7.
 */
public class TaskListenerForNotifacation implements DownloadManager.DownloadObserver {
    private Context context;
    private DownloadManager downloadManager;
    private NotificationManager notifiManager;
    private Map<Long, NotificationInfo> notificaions;

    private static int count = 0;
    private final Intent contentIntent;
    private final PendingIntent pendingIntent;
    private DownloadingAdapter downloadingAdapter;

    public TaskListenerForNotifacation(Context context, DownloadManager manager) {
        this.context = context;
        this.downloadManager = manager;
        notifiManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificaions = new ConcurrentHashMap<Long, NotificationInfo>();
        contentIntent = new Intent(context, DownLoadActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 0, contentIntent, PendingIntent.FLAG_ONE_SHOT);

    }


    @Override
    public void onDownloadProAndSpeedChange(DownloadRecord record) {

        switch (record.getStatus()) {
            //没有状态
            case DownloadManager.STATE_NONE:

                break;
            //连接中
            case DownloadManager.STATE_LINKING:
                break;
            //等待状态
            case DownloadManager.STATE_WAITING:

                break;

            case DownloadManager.STATE_DOWNLOADING:
                if (notificaions.get(record.getAppId()) == null) {
                    NotificationInfo info = new NotificationInfo();
                    String downloadSize = downloadManager.getAccurateReadableSize(record);
                    String fileSize = downloadManager.getReadableSize(record);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                            .setSmallIcon(R.mipmap.icon)
                            .setContentTitle(record.getSaveFileName())
                            .setContentText(downloadSize + "/" + fileSize)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setOngoing(true);
                    Intent notificationIntent = new Intent(context, DownLoadActivity.class);
                    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(contentIntent);
                    builder.setProgress(100, 0, false);
                    // Add as notification
                    info.builder = builder;
                    info.count = ++count;
                    notificaions.put(record.getAppId(), info);
                    notifiManager.notify(count, builder.build());
                }
                //从集合中取出通知信息
                NotificationInfo info2 = notificaions.get(record.getAppId());
                NotificationCompat.Builder builder2 = info2.builder;
                int count2 = info2.count;
                int percentage = downloadManager.getPercentage(record);
                String downloadSize = downloadManager.getAccurateReadableSize(record);
                String fileSize = downloadManager.getReadableSize(record);
                builder2.setProgress(100, percentage, false);
                builder2.setContentText(downloadSize + "/" + fileSize);
                notifiManager.notify(count2, builder2.build());
                break;
            //暂停中
            case DownloadManager.STATE_PAUSED:

                break;
            //下载完毕
            case DownloadManager.STATE_DOWNLOADED:
                break;
        }
    }


    @Override
    public void onDownloadStateChanged(DownloadRecord record) {
        switch (record.getStatus()) {
            case DownloadManager.STATE_DOWNLOADED:
                final NotificationInfo info3 = notificaions.get(record.getAppId());
                String fileSize = downloadManager.getReadableSize(record);
                NotificationCompat.Builder builder3 = info3.builder;
                builder3.setProgress(100, 100, false);
                builder3.setContentText(fileSize + "/" + fileSize);
                notifiManager.notify(info3.count, builder3.build());
                //1秒后删除notification
                UiUtils.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notifiManager.cancel(info3.count);
                    }
                }, 1000);
                break;
            default:
                break;
        }
    }

    public class NotificationInfo {
        public int count;
        public NotificationCompat.Builder builder;
    }

}
