package example.xfsp.miappstore.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.utils.UiUtils;

/**
 * Created by Administrator on 2015/9/21.
 */
public abstract class BaseListView extends ListView {
    private boolean isLoading = false;//是否正在下拉刷新
    private boolean isLoadingMore = false;//是否正在加载更多

    public BaseListView(Context context) {
        super(context);
        init();
    }


    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        this.setSelector(R.mipmap.nothing);  // 什么都没有
        this.setCacheColorHint(R.mipmap.nothing);
        this.setDivider(UiUtils.getDrawalbe(R.mipmap.devider_bg));
    }


    //下拉刷新
    public void setSwipeRefreshLayout(final SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        this.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int myScrollY = getMyScrollY();
                isLoading = false;
                if (firstVisibleItem == 0 && !isLoading && myScrollY <= 0) {
                    isLoading = true;
                    swipeRefreshLayout.setEnabled(true);
                } else {
                    swipeRefreshLayout.setEnabled(false);
                }
            }
        });
    }

    //每个item相同的情况下可以使用
    public int getMyScrollY() {
        View c = this.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = this.getFirstVisiblePosition();
        int top = c.getTop();
        return -top + firstVisiblePosition * c.getHeight();
    }

    //设置Loading的状态
    public void setLoadingState(boolean loadingState) {
        this.isLoadingMore = loadingState;
    }

    //刷新数据
    public abstract void refresh();
}
