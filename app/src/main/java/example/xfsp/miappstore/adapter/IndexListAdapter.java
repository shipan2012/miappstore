package example.xfsp.miappstore.adapter;

import android.content.Context;

import java.util.List;

import example.xfsp.miappstore.bean.ListAppEntity;
import example.xfsp.miappstore.holder.BaseHolder;
import example.xfsp.miappstore.holder.IndexHolder;
import example.xfsp.miappstore.view.BaseListView;

/**
 * Created by Administrator on 2015/9/22.
 */
public abstract class IndexListAdapter extends DefaultAdapter<ListAppEntity> {
    private List<ListAppEntity> listAppEntities;
    public IndexListAdapter(List<ListAppEntity> listAppEntities, BaseListView listView) {
        super(listAppEntities, listView);
    }

    public IndexListAdapter(List<ListAppEntity> listAppEntities, BaseListView listView, Context context) {
        super(listAppEntities, listView, context);
    }


    @Override
    protected void onInnerItemClick(int position) {
        super.onInnerItemClick(position);
    }

    @Override
    public BaseHolder<ListAppEntity> getHolder() {
        return new IndexHolder(context);
    }

    @Override
    public String getHostUrl() {
        return getHostUrls();
    }

    public abstract String getHostUrls();
}
