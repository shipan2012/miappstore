package example.xfsp.miappstore.fragment.impl;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.xfsp.miappstore.adapter.CategoryListAdapter;
import example.xfsp.miappstore.bean.CategoriesEntity;
import example.xfsp.miappstore.bean.CategoryList;
import example.xfsp.miappstore.fragment.base.BaseViewPagerFragment;
import example.xfsp.miappstore.protocal.impl.CategoryProtocal;
import example.xfsp.miappstore.utils.UiUtils;
import example.xfsp.miappstore.view.BaseListView;
import example.xfsp.miappstore.view.LoadPage;

/**
 * CategoryFragment
 */
public class CategoryFragment extends BaseViewPagerFragment {

    private CategoryList load;
    private List<CategoriesEntity> categoriesEntities;//类别目录集合
    private BaseListView baseListView;

    @Override
    protected View createSuccessView() {
        baseListView = new BaseListView(getContext()) {
            @Override
            public void refresh() {
                show();
            }
        };
        List<CategoriesEntity> categories = load.getCategories();
        //从CategoriesEntity集合中分出游戏
        categoriesEntities = new ArrayList<CategoriesEntity>();
        for (CategoriesEntity entity:categories){
            if (entity.getParentId()==0){
                categoriesEntities.add(entity);
            }
        }
        CategoryListAdapter categoryAdapter = new CategoryListAdapter(categoriesEntities,baseListView,getContext()) {
            @Override
            public String getHostUrls() {
                return load.getHost();
            }
        };
        baseListView.setAdapter(categoryAdapter);
        //设置下拉刷新
        baseListView.setSwipeRefreshLayout(swipeRefreshLayout);
        return baseListView;
    }

    @Override
    public LoadPage.LoadResult loadData() {
        CategoryProtocal categoryProtocal = new CategoryProtocal();
        load = categoryProtocal.load(0);
        if(swipeRefreshLayout!=null&&swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
            UiUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    baseListView.setLoadingState(false);
                    Toast.makeText(getContext(), "刷新完毕", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return checkData(load);
    }

}
