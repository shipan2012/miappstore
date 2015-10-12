package example.xfsp.miappstore.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.List;

import example.xfsp.miappstore.holder.BaseHolder;
import example.xfsp.miappstore.view.BaseListView;

/**
 * Created by Administrator on 2015/9/22.
 */
public abstract class DefaultAdapter<Data> extends BaseAdapter implements AdapterView.OnItemClickListener {
    protected List<Data> datas;
    private BaseHolder<Data> holder;
    private String hostUrl;
    protected int position;
    private BaseListView listView;
    public Context context;

    public DefaultAdapter(List<Data> datas,BaseListView listView) {
        this.datas = datas;
        this.listView = listView;
        listView.setOnItemClickListener(this);
    }

    public DefaultAdapter(List<Data> datas,BaseListView listView,Context context) {
        this(datas,listView);
        this.context = context;
    }

    public DefaultAdapter() {
    }

    //手动设置数据
    public void setDatas(List<Data> datas){
        this.datas = datas;
    }

    public void setListView(BaseListView listView){
        this.listView = listView;
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        this.position = position;
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        this.position = position;
        BaseHolder<Data> holder;
        if (convertView == null) {
            holder = getHolder();
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        holder.refreshView(datas.get(position), getHostUrl(), position);
        return holder.getConvertView();
    }



    //由子类实现
    public abstract BaseHolder<Data> getHolder();

    //获取主机地址
    public abstract String getHostUrl();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position -=listView.getHeaderViewsCount();
        onInnerItemClick(position);
    }

    //由子类实现
    protected void onInnerItemClick(int position){

    }



}