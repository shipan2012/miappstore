package example.xfsp.miappstore.holder;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.adapter.TopListAdapter;
import example.xfsp.miappstore.utils.UiUtils;

/**
 * Created by Administrator on 2015/9/26.
 */
public class MoreHolder extends BaseHolder<Integer> {
    private ImageView iv_loadmore;
    private AnimationDrawable background;
    private TopListAdapter topListAdapter;
    public static int HASMORE = 0;//加载更多
    public static int ERROR = 1;//加载失败
    public static int EMPTY = 2;//没有更多数据
    private View load_empty;
    private View load_error;
    private View load_more;
    private int resultState;

    public MoreHolder(Context context) {
        super(context);
    }

    public MoreHolder(TopListAdapter topListAdapter, Context context) {
        super(context);
        this.topListAdapter = topListAdapter;
    }


    public void setData(int result){
        this.resultState = result;
        refreshView(resultState,"", 0);
    }
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.item_load_more, null);
        load_empty = view.findViewById(R.id.load_empty);
        load_error = view.findViewById(R.id.load_error);
        load_more = view.findViewById(R.id.load_more);
        iv_loadmore = (ImageView) view.findViewById(R.id.iv_loadmore);
        background = (AnimationDrawable) iv_loadmore.getBackground();
        background.start();
        return view;
    }

    @Override
    public void refreshView(final Integer result, String hostUrl, int position) {
        UiUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                load_more.setVisibility(result == HASMORE ? View.VISIBLE : View.GONE);
                load_error.setVisibility(result == ERROR ? View.VISIBLE : View.GONE);
                load_empty.setVisibility(result == EMPTY ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public View getConvertView() {
        loadMore();
        return super.getConvertView();
    }

    //加载更多
    private void loadMore() {
        //加载更多数据
        topListAdapter.loadMoreData();
    }
}
