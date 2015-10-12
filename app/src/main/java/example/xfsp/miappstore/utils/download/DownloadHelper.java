package example.xfsp.miappstore.utils.download;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Toast;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.bean.ListAppEntity;
import example.xfsp.miappstore.adapter.DownloadingAdapter;
import example.xfsp.miappstore.service.DownloadService;
import example.xfsp.miappstore.utils.UiUtils;

public class DownloadHelper {
    private Context mContext;
    private DownloadService.DownloadServiceBinder mDownloadServiceBinder;
    private Boolean isConnected = false;
    private Object o = new Object();
    private static DownloadingAdapter downloadAdapter;
    private static DownloadHelper Instance;
    //单例设计模式
    public static synchronized DownloadHelper getInstance(Context context) {
        if (Instance == null) {
            Instance = new DownloadHelper(context);
        }
        return Instance;
    }
    public DownloadHelper(Context context) {
        mContext = context;
    }

    //多线程，加锁，服务连接
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadServiceBinder = (DownloadService.DownloadServiceBinder) service;
            synchronized (o) {
                isConnected = true;
                o.notify();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;
        }
    };

    /**
     * @param appEntity 传递download信息，从表中查询
     *                  如果已经下载过：
     *                  1.下载成功了：
     *                  弹出提示框是否重新下载，如果选中重新下载，判断文件是否存在，存在删除，新建下载，传入（Detail）
     *                  2.没有下载完：
     *                  继续上次下载（传入记录信息record）
     *                  3.新建下载（传入detail）
     *                  如果没有下载过，新建下载
     *                  （传入detail）
     *                  <p/>
     *                  DownloadId 和appId相对应
     */
    public void startDownload(final ListAppEntity appEntity) {

        if (NetworkUtils.isNetworkAvailable(mContext)) {
            if (NetworkUtils.isWifiConnected(mContext)) {
                download(appEntity);
            } else {
                new AlertDialog.Builder(mContext)
                        .setTitle(R.string.tip)
                        .setMessage(R.string.no_wifi_download)
                        .setPositiveButton(R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        download(appEntity);
                                    }
                                }).setNegativeButton(R.string.no, null)
                        .create().show();
            }
        } else {
            Toast.makeText(mContext, R.string.no_network, Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * @param appEntity 判断Service是否运行
     *                  没有运行，启动Service
     *                  判断Service是否绑定
     *                  没有绑定，绑定Service,等待，直到Service连接成功
     *                  取出Record里面的信息，调用Service里面的download开始下载
     */
    private void download(final ListAppEntity appEntity) {
        if (!isDownloadServiceRunning()) {
            mContext.startService(new Intent(mContext,
                    DownloadService.class));
        }
        if (mDownloadServiceBinder == null || isConnected == false) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    UiUtils.getContext().bindService(new Intent(mContext,
                                    DownloadService.class), connection,
                            Context.BIND_AUTO_CREATE);
                    synchronized (o) {
                        while (!isConnected) {
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    mDownloadServiceBinder.startDownload(appEntity);
                    downloadAdapter = mDownloadServiceBinder.getDownloadAdapter();
                }
            }.start();
        }else{
            mDownloadServiceBinder.startDownload(appEntity);
        }

    }

    /**
     * 获取下载Adapter
     * @return
     */
    public DownloadingAdapter getDownloadingAdapter(){
        if (downloadAdapter!=null){
            return downloadAdapter;
        }else{
            downloadAdapter = new DownloadingAdapter(mContext);
            DownloadManager.getInstance().registerObserver(downloadAdapter);
            return downloadAdapter;
        }
    }

    /*
     * 判断下载服务是否在运行
     */
    private boolean isDownloadServiceRunning() {
        ActivityManager manager = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager
                .getRunningServices(Integer.MAX_VALUE)) {
            if (DownloadService.class.getName().equals(
                    service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解绑服务
     */
    public void unbindDownloadService() {
        if (isDownloadServiceRunning() && isConnected && connection != null) {
            mContext.unbindService(connection);
        }
    }

}
