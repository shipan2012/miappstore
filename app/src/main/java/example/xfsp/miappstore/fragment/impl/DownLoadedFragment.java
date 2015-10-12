package example.xfsp.miappstore.fragment.impl;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import example.xfsp.miappstore.fragment.base.BaseViewPagerFragment;
import example.xfsp.miappstore.view.LoadPage;

/**
 * Created by Administrator on 2015/10/8.
 */
public class DownLoadedFragment extends BaseViewPagerFragment {
    private Context context;
    public DownLoadedFragment(Context context) {
        this.context = context;
    }

    @Override
    protected View createSuccessView() {
        TextView textView = new TextView(getContext());
        textView.setText("下载中");
        return textView;
    }

    @Override
    public LoadPage.LoadResult loadData() {
        return LoadPage.LoadResult.success;
    }
}
