package example.xfsp.miappstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import java.util.ArrayList;
import java.util.List;

import example.xfsp.miappstore.activity.AppDetailActivity;
import example.xfsp.miappstore.bean.ListAppEntity;
import example.xfsp.miappstore.bean.TopList;
import example.xfsp.miappstore.bean.DownloadRecord;
import example.xfsp.miappstore.utils.download.DownloadManager;
import example.xfsp.miappstore.holder.BaseHolder;
import example.xfsp.miappstore.holder.MoreHolder;
import example.xfsp.miappstore.holder.TopViewHolder;
import example.xfsp.miappstore.manager.ThreadManager;
import example.xfsp.miappstore.protocal.impl.TopProtocal;
import example.xfsp.miappstore.utils.UiUtils;
import example.xfsp.miappstore.view.BaseListView;

/**
 * Created by Administrator on 2015/9/22.
 */
public abstract class TopListAdapter extends DefaultAdapter<ListAppEntity> implements DownloadManager.DownloadObserver, AbsListView.RecyclerListener {
    private static final int NORMALTYPE = 0;
    private static final int MORETYPE = 1;
    private MoreHolder moreHolder;
    private TopProtocal topProtocal;
    private int page = 0;//加载第一页了，默认加载第一页
    private TopList load;
    private BaseHolder holder = null;
    private boolean hasMore;//是否还有数据
    private List<ListAppEntity> listAppEntities;
    private ListAppEntity listAppEntity;
    private TopViewHolder topViewHolder;
    private BaseListView listView;
    private List<BaseHolder> mDisplayedHolders;//用于记录所有显示的holder
    private Context context;

    public TopListAdapter(List<ListAppEntity> listAppEntities, BaseListView listView, Context context) {
        super(listAppEntities, listView, context);
        this.listAppEntities = listAppEntities;
        this.listView = listView;
        this.context = context;
        mDisplayedHolders = new ArrayList<BaseHolder>();
    }


    @Override
    protected void onInnerItemClick(int position) {
        super.onInnerItemClick(position);
        Intent intent = new Intent(UiUtils.getContext(), AppDetailActivity.class);
        listAppEntity = listAppEntities.get(position);
        //将每个app的id传入
        intent.putExtra("appid", listAppEntity.getId());
        intent.putExtra("appEntity", listAppEntity);
        UiUtils.startActivity(intent);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    //两种Type类型
    @Override
    public int getItemViewType(int position) {
        if (position == datas.size()) {
            return MORETYPE;
        } else {
            return NORMALTYPE;
        }
    }

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        this.position = position;
        switch (getItemViewType(position)) {
            //判断当前条目是什么类型
            case NORMALTYPE:
                if (convertView == null) {
                    holder = getHolder();
                } else {
                    holder = (BaseHolder) convertView.getTag();
                }
                if (position < datas.size()) {
                    holder.refreshView(datas.get(position), getHostUrl(), position);
                }
                //从屏幕上显示时，加入到集合中,普通holder
                synchronized (mDisplayedHolders) {
                    mDisplayedHolders.add(holder);
                }

                break;
            case MORETYPE:
                if (convertView == null) {
                    holder = getMoreHolder();
                } else {
                    holder = (BaseHolder) convertView.getTag();
                }
                break;
        }
        return holder.getConvertView();
    }


    @Override
    public String getHostUrl() {
        return getHostUrls();
    }

    public abstract String getHostUrls();

    @Override
    public BaseHolder<ListAppEntity> getHolder() {
        topViewHolder = new TopViewHolder(context);
        return topViewHolder;
    }

    //加载更多Holder
    public BaseHolder getMoreHolder() {
        if (moreHolder != null) {
            return moreHolder;
        } else {
            moreHolder = new MoreHolder(this, context);
            return moreHolder;
        }
    }

    //加载更多数据
    public void loadMoreData() {
        topProtocal = new TopProtocal();
        page++;
        //子线程请求服务，主线程刷新数据，否则请求的数据为空
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                load = topProtocal.load(page);
                hasMore = load.getHasMore();
                if (hasMore) {
                    if (load == null) {
                        //加载失败
                        moreHolder.setData(MoreHolder.ERROR);
                    } else {
                        moreHolder.setData(MoreHolder.HASMORE);
                        //加载成功之后，刷新数据
                        UiUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                List<ListAppEntity> listApp = load.getListApp();
                                datas.addAll(listApp);
                                notifyDataSetChanged();
                            }
                        });
                    }
                } else {
                    //没有更多数据
                    moreHolder.setData(MoreHolder.EMPTY);
                }
            }
        });
    }


    /**
     * 将此adapter加入到观察者集合中
     */
    public void startObserver() {
        //注册通知栏监听
        DownloadManager.getInstance().registerObserver(this);
    }

    /**
     * 将此adapter从观察者集合中移除
     */
    public void stopObserver() {
        DownloadManager.getInstance().unRegisterObserver(this);
    }


    //如果从屏幕消失，从集合中移除
    @Override
    public void onMovedToScrapHeap(View view) {
        if (null != view) {
            Object tag = view.getTag();
            if (tag instanceof BaseHolder) {
                BaseHolder holder = (BaseHolder) tag;
                synchronized (mDisplayedHolders) {
                    mDisplayedHolders.remove(holder);
                }
            }
        }
    }

    @Override
    public void onDownloadProAndSpeedChange(DownloadRecord record) {
        refreshHolder(record);
    }

    @Override
    public void onDownloadStateChanged(DownloadRecord record) {
        refreshHolder(record);
        switch (record.getStatus()) {
            case DownloadManager.STATE_DOWNLOADED:
                //下载完成后自动安装
                DownloadManager.getInstance().install(record.getAppId());
                refreshHolder(record);
                break;
            default:
                break;
        }
    }

    /**
     * 获取所有可显示的holder
     *
     * @return
     */
    protected List<BaseHolder> getDisplayedHolders() {
        List<BaseHolder> holders = new ArrayList<BaseHolder>();
        holders.addAll(mDisplayedHolders);
        return holders;
    }


    /**
     * 刷新数据
     *
     * @param record
     */
    private void refreshHolder(final DownloadRecord record) {
        List<BaseHolder> displayedHolders = getDisplayedHolders();
        for (int i = 0; i < displayedHolders.size(); i++) {
            BaseHolder baseHolder = displayedHolders.get(i);
            if (baseHolder instanceof TopViewHolder) {
                final TopViewHolder holder = (TopViewHolder) baseHolder;
                ListAppEntity appInfo = holder.getData();
                if (appInfo.getId() == record.getAppId()) {
                    UiUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            holder.refreshState(record);
                        }
                    });
                }
            }
        }
    }
}
