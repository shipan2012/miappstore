package example.xfsp.miappstore.adapter;

import android.content.Context;
import android.widget.ListView;

import java.util.List;

import example.xfsp.miappstore.bean.DownloadRecord;
import example.xfsp.miappstore.utils.download.DownloadManager;
import example.xfsp.miappstore.holder.BaseHolder;
import example.xfsp.miappstore.holder.DownLoadHolder;
import example.xfsp.miappstore.view.BaseListView;

/**
 * Created by Administrator on 2015/10/7.
 */
public class DownloadingAdapter extends DefaultAdapter<DownloadRecord> implements DownloadManager.DownloadObserver{
    private Context context;
    private DownloadManager downloadManager;
    private ListView listView;

    public DownloadingAdapter(List<DownloadRecord> downloadRecords, BaseListView listView) {
        super(downloadRecords, listView);
        init();
    }

    public DownloadingAdapter(List<DownloadRecord> downloadRecords, BaseListView listView, Context context) {
        super(downloadRecords, listView, context);
        init();
    }

    public DownloadingAdapter(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        downloadManager = DownloadManager.getInstance();
        //初始化时设置数据
        this.setDatas(downloadManager.getmDownloadingRecords());
    }

    @Override
    public void onDownloadProAndSpeedChange(DownloadRecord record) {
        this.setDatas(downloadManager.getmDownloadingRecords());
        switch (record.getStatus()) {
            case DownloadManager.STATE_WAITING:
                notifyDataSetChanged();
                break;
            case DownloadManager.STATE_LINKING:
                notifyDataSetChanged();
                break;
            case DownloadManager.STATE_DOWNLOADING:
                notifyDataSetChanged();
                break;
            case DownloadManager.STATE_PAUSED:
                notifyDataSetChanged();
                break;

            case DownloadManager.STATE_DOWNLOADED:
                notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDownloadStateChanged(DownloadRecord record) {
        this.setDatas(downloadManager.getmDownloadingRecords());
        switch (record.getStatus()) {
            case DownloadManager.STATE_WAITING:
                notifyDataSetChanged();
                break;
            case DownloadManager.STATE_LINKING:
                notifyDataSetChanged();
                break;
            case DownloadManager.STATE_DOWNLOADING:
                notifyDataSetChanged();
                break;
            case DownloadManager.STATE_PAUSED:
                notifyDataSetChanged();
                break;

            case DownloadManager.STATE_DOWNLOADED:
                notifyDataSetChanged();
                break;
            default:
                break;
        }
    }
    /**
     * 获取Listview
     * @param listView
     */
    public void setListView(ListView listView) {
        this.listView = listView;
    }
    @Override
    public BaseHolder<DownloadRecord> getHolder() {
        DownLoadHolder downLoadHolder = new DownLoadHolder();
        return downLoadHolder;
    }


    @Override
    public String getHostUrl() {
        return null;
    }
}
