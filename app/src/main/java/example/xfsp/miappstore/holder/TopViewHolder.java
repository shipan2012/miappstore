package example.xfsp.miappstore.holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.bean.ListAppEntity;
import example.xfsp.miappstore.bean.DownloadRecord;
import example.xfsp.miappstore.utils.download.DownloadHelper;
import example.xfsp.miappstore.utils.download.DownloadManager;
import example.xfsp.miappstore.utils.PackageUtils;

/**
 * Created by Administrator on 2015/9/22.
 */
public class TopViewHolder extends BaseHolder<ListAppEntity> implements View.OnClickListener {
    private TextView tv_company;
    private TextView tv_title;
    private RatingBar ratingBar;
    private ImageView iv_icon;
    public Button btn_install;
    private DownloadManager mDownloadManager;
    private ListAppEntity listAppEntity;
    private DownloadHelper helper;
    private int currentStatus;

    public TopViewHolder(Context context) {
        super(context);
        helper = new DownloadHelper(context);
        if (mDownloadManager == null) {
            mDownloadManager = DownloadManager.getInstance();
        }
    }


    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.base_app_item, null);
        this.tv_company = (TextView) view.findViewById(R.id.item_app_company);
        this.tv_title = (TextView) view.findViewById(R.id.item_title);
        this.ratingBar = (RatingBar) view.findViewById(R.id.item_rating);
        this.iv_icon = (ImageView) view.findViewById(R.id.item_icon);
        this.btn_install = (Button) view.findViewById(R.id.btn_install);
        return view;
    }

    @Override
    public void refreshView(final ListAppEntity listAppEntity, String hostUrl, final int position) {
        this.listAppEntity = listAppEntity;
        this.tv_company.setText(listAppEntity.getPublisherName());
        this.tv_title.setText(position + 1 + "." + listAppEntity.getDisplayName());
        this.ratingBar.setRating(listAppEntity.getRatingScore());
        bitmapUtils.display(this.iv_icon, hostUrl + listAppEntity.getIcon());

        DownloadRecord record = mDownloadManager.getRecord(listAppEntity.getId());
        if (record == null) {
            record = new DownloadRecord();
            record.setStatus(DownloadManager.STATE_NONE);
        }
        refreshState(record);

        btn_install.setOnClickListener(this);
    }

    public ListAppEntity getData() {
        return listAppEntity;
    }


    @Override
    public void onClick(View v) {
        if (currentStatus == DownloadManager.STATE_NONE || currentStatus == DownloadManager.STATE_PAUSED
                || currentStatus == DownloadManager.STATE_ERROR) {
            helper.startDownload(getData());
        } else if (currentStatus == DownloadManager.STATE_WAITING || currentStatus == DownloadManager.STATE_DOWNLOADING) {
            mDownloadManager.pause(getData());
        } else if (currentStatus == DownloadManager.STATE_DOWNLOADED) {
            mDownloadManager.install(getData().getId());
        }

    }

    public void refreshState(DownloadRecord record) {
        //当前应用的包名，
        String packageName = listAppEntity.getPackageName();
        boolean isContains = PackageUtils.isContains(packageName);
        if (isContains) {
            btn_install.setText("已安装");
            btn_install.setClickable(false);
        } else {
            currentStatus = record.getStatus();
            String percentage = mDownloadManager.getReadablePercentage(record);
            switch (currentStatus) {
                case DownloadManager.STATE_NONE:
                    btn_install.setText("下载");
                    btn_install.setClickable(true);
                    break;
                case DownloadManager.STATE_PAUSED:
                    btn_install.setText("暂停");
                    btn_install.setClickable(true);
                    break;
                case DownloadManager.STATE_ERROR:
                    btn_install.setText("下载");
                    btn_install.setClickable(true);
                    break;
                case DownloadManager.STATE_WAITING:
                    btn_install.setText("等待");
                    btn_install.setClickable(true);
                    break;
                case DownloadManager.STATE_DOWNLOADING:
                    btn_install.setText(percentage);
                    btn_install.setClickable(true);
                    break;
                case DownloadManager.STATE_DOWNLOADED:
                    btn_install.setText("安装");
                    btn_install.setClickable(true);
                    break;
                case DownloadManager.STATE_LINKING:
                    btn_install.setText("连接中");
                    break;
                default:
                    break;
            }
        }

    }
}
