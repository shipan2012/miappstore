package example.xfsp.miappstore.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import example.xfsp.miappstore.BaseActivity;
import example.xfsp.miappstore.R;
import example.xfsp.miappstore.bean.AppDetail;
import example.xfsp.miappstore.bean.AppDownLoadInfo;
import example.xfsp.miappstore.bean.ListAppEntity;
import example.xfsp.miappstore.utils.download.DownloadHelper;
import example.xfsp.miappstore.holder.AppChangeLogHolder;
import example.xfsp.miappstore.holder.AppCommentsHolder;
import example.xfsp.miappstore.holder.AppInfosHolder;
import example.xfsp.miappstore.holder.AppIntroHolder;
import example.xfsp.miappstore.holder.AppScreensHolder;
import example.xfsp.miappstore.holder.AppVersionsHolder;
import example.xfsp.miappstore.protocal.impl.AppDetailProtocal;
import example.xfsp.miappstore.protocal.impl.DownLoadInfoProtocal;
import example.xfsp.miappstore.utils.UiUtils;
import example.xfsp.miappstore.view.LoadPage;

/**
 * Created by Administrator on 2015/9/27.
 * App详情页
 */
public class AppDetailActivity extends BaseActivity {

    private int appid;//app的id信息
    private AppDetail load;//获取得到得数据
    private View view;
    private View base_activity_detail;
    private Button btn_download;
    private AppDownLoadInfo downLoadInfo;
    private DownloadHelper helper;
    private ListAppEntity appEntity;

    @Override
    protected void initToolBar() {
        super.initToolBar();
        base_activity_detail = View.inflate(this, R.layout.base_activity_detail, null);
    }

    @Override
    protected void initView() {
        super.initView();
        helper = DownloadHelper.getInstance(this);
        Intent intent = getIntent();
        appid = intent.getIntExtra("appid", 0);
        appEntity = (ListAppEntity) intent.getParcelableExtra("appEntity");
        FrameLayout fl_content = (FrameLayout) base_activity_detail.findViewById(R.id.fl_content);
        LoadPage loadPage = new LoadPage(AppDetailActivity.this) {
            @Override
            protected LoadResult loadServer() {
                return AppDetailActivity.this.loadServer();
            }

            @Override
            protected View createSuccessView() {
                return AppDetailActivity.this.createSucessView();
            }
        };

        fl_content.addView(loadPage);
        setContentView(base_activity_detail);
        Toolbar toolbar = (Toolbar) base_activity_detail.findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    //创建加载成功界面
    private View createSucessView() {
        view = View.inflate(getApplicationContext(), R.layout.activity_detail, null);
        FrameLayout fl_appinfo = (FrameLayout) view.findViewById(R.id.fl_appinfo);
        HorizontalScrollView hl_screen_shots = (HorizontalScrollView) view.findViewById(R.id.hl_screen_shots);
        FrameLayout fl_changelog = (FrameLayout) view.findViewById(R.id.fl_changelog);
        FrameLayout fl_comments = (FrameLayout) view.findViewById(R.id.fl_comments);
        FrameLayout fl_introducation = (FrameLayout) view.findViewById(R.id.fl_introducation);
        FrameLayout fl_versioninfos = (FrameLayout) view.findViewById(R.id.fl_versioninfos);
        FrameLayout fl_bottom = (FrameLayout) view.findViewById(R.id.fl_bottom);


        //appInfo部分
        AppInfosHolder infosHolder = new AppInfosHolder();
        infosHolder.refreshView(load, load.getHost(), 0);
        fl_appinfo.addView(infosHolder.getConvertView());
        //截图部分
        AppScreensHolder screensHolder = new AppScreensHolder();
        screensHolder.refreshView(load, load.getHost(), 0);
        hl_screen_shots.addView(screensHolder.getConvertView());
        //介绍部分
        AppIntroHolder introHolder = new AppIntroHolder();
        introHolder.refreshView(load, load.getHost(), 0);
        fl_introducation.addView(introHolder.getConvertView());

        //日志部分
        AppChangeLogHolder logHolder = new AppChangeLogHolder();
        logHolder.refreshView(load, load.getHost(), 0);
        fl_changelog.addView(logHolder.getConvertView());
        //评论部分
        AppCommentsHolder commentsHolder = new AppCommentsHolder();
        commentsHolder.refreshView(load, load.getHost(), 0);
        fl_comments.addView(commentsHolder.getConvertView());


        //版本信息部分
        AppVersionsHolder versionsHolder = new AppVersionsHolder();
        versionsHolder.refreshView(load, load.getHost(), 0);
        fl_versioninfos.addView(versionsHolder.getConvertView());

        return view;
    }


    //加载服务器数据
    private LoadPage.LoadResult loadServer() {
        AppDetailProtocal appDetailProtocal = new AppDetailProtocal() {
            @Override
            public String getAppId() {
                return appid + "";
            }
        };
        load = appDetailProtocal.load(0);
        //主线程设置名称
        UiUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getSupportActionBar().setTitle(load.getDisPlayName());
            }
        });
        //加载详情顺便加载下载地址
        DownLoadInfoProtocal downLoadInfoProtocal = new DownLoadInfoProtocal() {
            @Override
            public String getAppId() {
                return appid + "";
            }
        };
        downLoadInfo = downLoadInfoProtocal.load(0);

        if (this.load == null) {
            return LoadPage.LoadResult.error;
        } else {
            return LoadPage.LoadResult.success;
        }
    }

    //ToolBar菜单选项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //当菜单选中时
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_search:
                //搜索框
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
