package example.xfsp.miappstore.adapter;

import android.content.Context;

import java.util.List;

import example.xfsp.miappstore.holder.BaseHolder;
import example.xfsp.miappstore.holder.ManagerHolder;
import example.xfsp.miappstore.view.BaseListView;

/**
 * Created by Administrator on 2015/9/27.
 */
public class ManagerAdapter extends DefaultAdapter<String> {
    public ManagerAdapter(List<String> strings, BaseListView listView) {
        super(strings, listView);
    }
    public ManagerAdapter(List<String> strings, BaseListView listView, Context context) {
        super(strings, listView, context);
    }

    @Override
    public BaseHolder<String> getHolder() {
        return new ManagerHolder(context);
    }

    @Override
    public String getHostUrl() {
        return null;
    }
}
