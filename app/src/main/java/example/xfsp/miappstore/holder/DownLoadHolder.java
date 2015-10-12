package example.xfsp.miappstore.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.bean.DownloadRecord;
import example.xfsp.miappstore.utils.download.DownloadManager;
import example.xfsp.miappstore.utils.UiUtils;

/**
 * Created by Administrator on 2015/10/11.
 */
public class DownLoadHolder extends BaseHolder<DownloadRecord> {

    private ImageView iv_icon;
    private TextView tv_title;
    private TextView tv_speed;
    private TextView tv_downDetail;
    private ProgressBar pb_downloading;

    @Override
    public View initView() {
        View view = View.inflate(UiUtils.getContext(), R.layout.download_item, null);
        iv_icon = (ImageView) view.findViewById(R.id.iv_download_icon);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_speed = (TextView) view.findViewById(R.id.tv_download_speed);
        tv_downDetail = (TextView) view.findViewById(R.id.downingDetail);
        pb_downloading = (ProgressBar) view.findViewById(R.id.pb_downloading);
        return view;
    }

    @Override
    public void refreshView(DownloadRecord record, String hostUrl, int position) {
        DownloadManager instance = DownloadManager.getInstance();
        String fileSize = instance.getReadableSize(record);//文件大小
        String downloadedSize = instance.getAccurateReadableSize(record);//已下载文件大小
        String speed = instance.getAverageReadableSpeed(record);//平均速度
        int percentage = instance.getPercentage(record);

        tv_downDetail.setText(downloadedSize+"/"+fileSize);
        bitmapUtils.display(iv_icon, record.getHomeIcon());
        tv_title.setText(record.getSaveFileName());
        tv_speed.setText(speed);
        switch (record.getStatus()) {
            case DownloadManager.STATE_WAITING:
                tv_speed.setText("等待中");
                break;
            case DownloadManager.STATE_LINKING:
                tv_speed.setText("连接中");
                break;
            case DownloadManager.STATE_DOWNLOADING:
                tv_speed.setText(speed);
                break;
            case DownloadManager.STATE_PAUSED:
                tv_speed.setText("暂停");
                break;

            case DownloadManager.STATE_DOWNLOADED:
                break;
            default:
                break;
        }
        pb_downloading.setProgress(percentage);
    }
}
