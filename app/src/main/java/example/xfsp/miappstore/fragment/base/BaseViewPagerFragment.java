package example.xfsp.miappstore.fragment.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.BitmapUtils;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.utils.BitmapHelper;
import example.xfsp.miappstore.view.LoadPage;

/**
 * BaseViewPagerFragment
 */
public abstract class BaseViewPagerFragment extends Fragment {
    public BitmapUtils bitmapUtils;
    //    protected int page = 0;//加载第一页了，默认加载第一页
    private LoadPage loadPage;
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bitmapUtils = BitmapHelper.getBitmapUtils();
        //加载展示图片
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.place_holder_icon);
        //加载中展示图片
        bitmapUtils.configDefaultLoadingImage(R.mipmap.place_holder_icon);
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (swipeRefreshLayout == null) {
            swipeRefreshLayout = new SwipeRefreshLayout(getContext());
            loadPage = new LoadPage(getActivity()) {
                @Override
                protected LoadResult loadServer() {
                    return BaseViewPagerFragment.this.loadData();
                }

                @Override
                protected View createSuccessView() {
                    return BaseViewPagerFragment.this.createSuccessView();
                }
            };
            swipeRefreshLayout.addView(loadPage);
        } else {
            //如果loadPage不为空，首先移除之前loadPage之前的父控件
            example.xfsp.miappstore.utils.ViewUtils.removeParent(swipeRefreshLayout);
        }
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        return swipeRefreshLayout;
    }

    //当页面切换时，重新请求服务器加载数据
    public void show() {
        if (loadPage != null) {
            loadPage.show();
        }
    }

    //创建加载成功的界面
    protected abstract View createSuccessView();

    //加载数据
    public abstract LoadPage.LoadResult loadData();

    /**
     * 校验数据
     */
    public LoadPage.LoadResult checkData(Object object) {
        if (object == null) {
            return LoadPage.LoadResult.error;//  请求服务器失败
        } else {
            return LoadPage.LoadResult.success;
        }
    }

}
