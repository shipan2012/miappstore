package example.xfsp.miappstore.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.manager.ThreadManager;
import example.xfsp.miappstore.utils.UiUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public abstract class LoadPage extends FrameLayout {
    public static final int STATE_UNKNOW = 0;//未知状态
    public static final int STATE_LOADING = 1;//加载中状态
    public static final int STATE_ERROR = 2;//加载失败
    public static final int STATE_SUCCESS = 3;//加载成功

    public int STATE_CURRENT = STATE_UNKNOW;//当前状态，初始化为未知

    private View loadingView;//加载中的界面
    private View errorView;//加载失败界面
    private View successView;//加载成功界面

    public LoadPage(Context context) {
        super(context);
        init();
    }

    public LoadPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    /**
     * 1.首先创建三种View，并添加
     * 2.根据当前状态判断显示与否
     * 3.根据加载数据后的状态进行判断view显示
     */
    private void init() {
        loadingView = createLoadingView();
        if(loadingView!=null){
            addView(loadingView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        }
        errorView = createErrorView();
        if(errorView!=null){
            addView(errorView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        //根据不同状态，判断显示与否
        showPage();
        show();
    }


    private void showPage() {
        loadingView.setVisibility(STATE_CURRENT == STATE_UNKNOW | STATE_CURRENT == STATE_LOADING ?
                VISIBLE : INVISIBLE);
        errorView.setVisibility(STATE_CURRENT == STATE_ERROR ? VISIBLE : INVISIBLE);
        //如果当前是加载成功状态
        if(STATE_CURRENT==STATE_SUCCESS){
            //并且为空
            if(successView==null){
                successView = createSuccessView();
                if (successView!=null){
                    addView(successView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                }
            }
            successView.setVisibility(VISIBLE);
        }else{
            if(successView!=null){
                successView.setVisibility(INVISIBLE);
            }
        }
    }


    //加载失败界面
    private View createErrorView() {
        View view_error = View.inflate(getContext(), R.layout.loadpage_error, null);
        Button btn_reload = (Button) view_error.findViewById(R.id.btn_reload);
        btn_reload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view_error;
    }

    //加载中界面
    private View createLoadingView() {
        View view_loading = View.inflate(getContext(), R.layout.loadpage_loading, null);
        return view_loading;
    }

    public enum LoadResult {
        error(2), success(3);

        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
    //根据求情返回的结果，判断View的显示
    public void show() {
        //加载失败后，重新加载显示Loading,未知状态显示Loading
        if(STATE_CURRENT==STATE_ERROR||STATE_CURRENT==STATE_UNKNOW){
            STATE_CURRENT = STATE_LOADING;
        }
        //线程池的使用
        // 请求服务器 返回一个结果
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {

            @Override
            public void run() {
                SystemClock.sleep(500);
                final LoadResult result = loadServer();
                //在主线程更新界面
                UiUtils.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (result != null) {
                            STATE_CURRENT = result.getValue();
                            showPage(); // 状态改变了,重新判断当前应该显示哪个界面
                        }
                    }
                });
            }
        });

        showPage();
    }

    //从服务器获取数据
    protected abstract LoadResult loadServer();
    //创建加载成功界面
    protected abstract View createSuccessView();
}
