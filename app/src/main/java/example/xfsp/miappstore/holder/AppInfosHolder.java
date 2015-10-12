package example.xfsp.miappstore.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.bean.AppDetail;
import example.xfsp.miappstore.utils.UiUtils;

/**
 * Created by Administrator on 2015/9/29.
 */
public class AppInfosHolder extends BaseHolder<AppDetail> {

    private View view;
    private TextView tv_name;
    private ImageView iv_icon;
    private RatingBar rb_score;
    private TextView tv_size_publisher;

    @Override
    public View initView() {
        View view = View.inflate(UiUtils.getContext(), R.layout.holder_appinfos, null);
        tv_name = (TextView) view.findViewById(R.id.tv_appname);
        iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        rb_score = (RatingBar) view.findViewById(R.id.rb_score);
        tv_size_publisher = (TextView) view.findViewById(R.id.tv_size_publisher);
        return view;
    }

    @Override
    public void refreshView(AppDetail appDetail, String hostUrl, int position) {
        tv_name.setText(appDetail.getDisPlayName());
        bitmapUtils.display(iv_icon, hostUrl + appDetail.getIcon());
        rb_score.setRating((float) appDetail.getRatingScore());
        String apkSize = Formatter.formatFileSize(UiUtils.getContext(), appDetail.getApkSize());
        tv_size_publisher.setText(apkSize+" | "+appDetail.getPublisherName());
    }
}
