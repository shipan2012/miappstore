package example.xfsp.miappstore.fragment.impl;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.adapter.DownloadingAdapter;
import example.xfsp.miappstore.utils.download.DownloadHelper;
import example.xfsp.miappstore.fragment.base.BaseViewPagerFragment;
import example.xfsp.miappstore.view.LoadPage;

/**
 * Created by Administrator on 2015/10/8.
 */
public class DownLoadingFragment extends BaseViewPagerFragment {

    private ListView lv_downloading;
    private Context context;
    private DownloadingAdapter downloadAdapter;

    public DownLoadingFragment(Context context) {
        this.context = context;
        //把Adapter加入到监听集合中
        DownloadHelper helper = DownloadHelper.getInstance(context);
        if(downloadAdapter==null){
            downloadAdapter = helper.getDownloadingAdapter();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected View createSuccessView() {
        View view = View.inflate(getContext(), R.layout.activity_downloading, null);
        lv_downloading = (ListView) view.findViewById(R.id.lv_downloading);
        downloadAdapter.setListView(lv_downloading);
        lv_downloading.setAdapter(downloadAdapter);
        return view;

    }

    @Override
    public LoadPage.LoadResult loadData() {
        return LoadPage.LoadResult.success;
    }
}
