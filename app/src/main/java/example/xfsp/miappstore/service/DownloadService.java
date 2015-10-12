package example.xfsp.miappstore.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import example.xfsp.miappstore.bean.ListAppEntity;
import example.xfsp.miappstore.adapter.DownloadingAdapter;
import example.xfsp.miappstore.bean.DownloadRecord;
import example.xfsp.miappstore.utils.download.DownloadManager;
import example.xfsp.miappstore.utils.download.TaskListenerForNotifacation;

public class DownloadService extends Service implements DownloadManager.DownloadObserver {
    private DownloadManager manager;
    private DownloadingAdapter downloadIngAdapter;

    public DownloadService() {
        manager = DownloadManager.getInstance();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager.registerObserver(new TaskListenerForNotifacation(this, manager));
        if (downloadIngAdapter==null){
            downloadIngAdapter = new DownloadingAdapter(this);
            //注册
            manager.registerObserver(downloadIngAdapter);
        }
    }

    //返回下载Adapter
    private DownloadingAdapter getDownloadAdapter(){
        if (downloadIngAdapter!=null){
            return downloadIngAdapter;
        }else{
            downloadIngAdapter = new DownloadingAdapter(this);
            manager.registerObserver(downloadIngAdapter);
        }
        return downloadIngAdapter;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 强制关闭app,关闭所有服务
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new DownloadServiceBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    @Override
    public void onDownloadProAndSpeedChange(DownloadRecord record) {

    }

    @Override
    public void onDownloadStateChanged(DownloadRecord record) {
        switch (record.getStatus()) {
            case DownloadManager.STATE_WAITING:
                count++;
                break;
            case DownloadManager.STATE_ERROR | DownloadManager.STATE_DOWNLOADED:
                count--;
                if (count == 0) {
                    //停止Service
                    stopSelf();
                }
                break;
            default:
                break;
        }
    }

    //Binder类
    public class DownloadServiceBinder extends Binder {
        //开始下载
        public void startDownload(ListAppEntity appEntity) {
            /**
             * 如果正在下载的集合里面有，弹出正在下载
             * 如果正在下载的里面没有，
             *
             */
            manager.downLoad(appEntity);
        }

        public DownloadingAdapter getDownloadAdapter() {
            return DownloadService.this.getDownloadAdapter();
        }
    }

    private int count = 0;
    /**
     * 任务每完成一次，count--一次，当count==0时，服务停止
     * @param mission
     */


}
