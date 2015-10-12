package example.xfsp.miappstore.adapter;

import android.content.Context;

import java.util.List;

import example.xfsp.miappstore.bean.CategoriesEntity;
import example.xfsp.miappstore.holder.BaseHolder;
import example.xfsp.miappstore.holder.CategoryHolder;
import example.xfsp.miappstore.view.BaseListView;

/**
 * Created by Administrator on 2015/9/22.
 */
public abstract class CategoryListAdapter extends DefaultAdapter<CategoriesEntity> {

    public CategoryListAdapter(List<CategoriesEntity> categoriesEntities, BaseListView listView) {
        super(categoriesEntities, listView);
    }
    public CategoryListAdapter(List<CategoriesEntity> categoriesEntities, BaseListView listView, Context context) {
        super(categoriesEntities, listView, context);
    }

    @Override
    protected void onInnerItemClick(int position) {
        super.onInnerItemClick(position);
    }

    @Override
    public BaseHolder<CategoriesEntity> getHolder() {
        return new CategoryHolder(context);
    }

    @Override
    public String getHostUrl() {
        return getHostUrls();
    }

    public abstract String getHostUrls();

}
