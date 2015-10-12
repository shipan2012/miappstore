package example.xfsp.miappstore.fragment.impl;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.adapter.IndexListAdapter;
import example.xfsp.miappstore.bean.IndexList;
import example.xfsp.miappstore.bean.IndexTop;
import example.xfsp.miappstore.fragment.base.BaseViewPagerFragment;
import example.xfsp.miappstore.manager.ThreadManager;
import example.xfsp.miappstore.protocal.impl.IndexProtocal;
import example.xfsp.miappstore.protocal.impl.IndexTopProtocal;
import example.xfsp.miappstore.utils.BitmapHelper;
import example.xfsp.miappstore.utils.UiUtils;
import example.xfsp.miappstore.view.BaseListView;
import example.xfsp.miappstore.view.LoadPage;

/**
 * Created by Administrator on 2015/9/19.
 */
public class IndexFragment extends BaseViewPagerFragment {
    private BaseListView listView;
    private IndexList load;
    private View index_top;
    private ImageView iv_top1;
    private ImageView iv_top2;
    private ImageView iv_top3;
    private ImageView iv_top4;

    @Override
    protected View createSuccessView() {
        listView = new BaseListView(getContext()) {
            @Override
            public void refresh() {
                show();
            }
        };
        IndexListAdapter indexListAdapter = new IndexListAdapter(load.getListApp(),listView,getContext()) {
            @Override
            public String getHostUrls() {
                return load.getHost();
            }
        };
        index_top = View.inflate(getContext(), R.layout.index_top, null);
        initTopView();
        listView.addHeaderView(index_top);
        listView.setAdapter(indexListAdapter);
        listView.setSwipeRefreshLayout(swipeRefreshLayout);
        return listView;
    }

    //初始化TopView布局
    private void initTopView() {
        final BitmapUtils bitmapUtils = BitmapHelper.getBitmapUtils();
        iv_top1 = (ImageView) index_top.findViewById(R.id.iv_top1);
        iv_top2 = (ImageView) index_top.findViewById(R.id.iv_top2);
        iv_top3 = (ImageView) index_top.findViewById(R.id.iv_top3);
        iv_top4 = (ImageView) index_top.findViewById(R.id.iv_top4);
        final List<ImageView> imageViews = new ArrayList<ImageView>();
        imageViews.add(iv_top1);
        imageViews.add(iv_top2);
        imageViews.add(iv_top3);
        imageViews.add(iv_top4);
        //请求网络数据
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run(){
                IndexTopProtocal indexTopProtocal = new IndexTopProtocal();
                final List<IndexTop> load = indexTopProtocal.load(0);
                //在主线程刷新界面
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<load.size();i++){
                            IndexTop indexTop = load.get(i);
                            bitmapUtils.display(imageViews.get(i),indexTop.getHost()+indexTop.getMticon());
                        }
                    }
                });
            }
        });
    }

    @Override
    public LoadPage.LoadResult loadData() {
        IndexProtocal indexProtocal = new IndexProtocal();
        load = indexProtocal.load(0);
        if(swipeRefreshLayout!=null&&swipeRefreshLayout.isRefreshing()){
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
