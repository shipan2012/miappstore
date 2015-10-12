package example.xfsp.miappstore.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.bean.AppDetail;
import example.xfsp.miappstore.utils.UiUtils;

/**
 * Created by Administrator on 2015/9/29.
 */
public class AppChangeLogHolder extends BaseHolder<AppDetail> {

    private View view;
    private TextView tv_content;
    public AppChangeLogHolder() {
        super();
    }
    public AppChangeLogHolder(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        view = View.inflate(UiUtils.getContext(), R.layout.holder_changelog, null);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
        return view;
    }

    @Override
    public void refreshView(AppDetail appDetail, String hostUrl, int position) {
        tv_content.setText(appDetail.getChangeLog());
    }
}
