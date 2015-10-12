package example.xfsp.miappstore.holder;

import android.view.View;
import android.widget.TextView;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.bean.AppDetail;
import example.xfsp.miappstore.utils.UiUtils;

/**
 * Created by Administrator on 2015/9/29.
 */
public class AppVersionsHolder extends BaseHolder<AppDetail> {

    private View view;
    private TextView tv_update_time;
    private TextView tv_version_name;

    @Override
    public View initView() {
        view = View.inflate(UiUtils.getContext(), R.layout.holder_versioninfos, null);
        tv_update_time = (TextView) view.findViewById(R.id.tv_update_time);
        tv_version_name = (TextView) view.findViewById(R.id.tv_version_name);
        return view;
    }

    @Override
    public void refreshView(AppDetail appDetail, String hostUrl, int position) {
        tv_version_name.setText("版本号:"+appDetail.getVersionName());
        long updateTime = appDetail.getUpdateTime();
        String time = UiUtils.parseDate(updateTime);
        tv_update_time.setText("更新日期:"+time);
    }
}
