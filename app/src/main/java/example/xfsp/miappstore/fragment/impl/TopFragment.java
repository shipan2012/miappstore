package example.xfsp.miappstore.fragment.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lidroid.xutils.bitmap.PauseOnScrollListener;

import java.util.List;

import example.xfsp.miappstore.adapter.TopListAdapter;
import example.xfsp.miappstore.bean.ListAppEntity;
import example.xfsp.miappstore.bean.TopList;
import example.xfsp.miappstore.fragment.base.BaseViewPagerFragment;
import example.xfsp.miappstore.protocal.impl.TopProtocal;
import example.xfsp.miappstore.utils.UiUtils;
import example.xfsp.miappstore.view.BaseListView;
import example.xfsp.miappstore.view.LoadPage;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TopFragment extends BaseViewPagerFragment {
    private TopProtocal topProtocal;
    private TopList load;
    private List<ListAppEntity> listApp;
    private BaseListView listView;
    private TopListAdapter listAdapter;
    private boolean hasMore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        if (listAdapter != null) {
            listAdapter.startObserver();
            listAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }

    /**
     * 不可见时，需要关闭监听
     */

    @Override
    public void onPause() {
        if (listAdapter != null) {
            listAdapter.stopObserver();
            listAdapter.notifyDataSetChanged();
        }
        super.onPause();
    }

    /**
     * 可见时，需要启动监听，以便随时根据下载状态刷新页面
     */


    @Override
    protected View createSuccessView() {
        listView = new BaseListView(getContext()) {
            @Override
            public void refresh() {
                show();
            }
        };
        //第二个参数，满速滑动，第三个快速滑动，false加载，true不加载
        listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
        listApp = load.getListApp();
        listAdapter = new TopListAdapter(listApp, listView, getContext()) {
            @Override
            public String getHostUrls() {
                return load.getHost();
            }
        };
        listAdapter.startObserver();
        listView.setAdapter(listAdapter);
        listView.setSwipeRefreshLayout(swipeRefreshLayout);
        return listView;
    }

    @Override
    public LoadPage.LoadResult loadData() {
        topProtocal = new TopProtocal();
        load = topProtocal.load(0);
        hasMore = load.getHasMore();
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
            UiUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "刷新完毕", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return checkData(load);
    }
}
