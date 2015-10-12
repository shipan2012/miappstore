package example.xfsp.miappstore.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.xfsp.miappstore.BaseActivity;
import example.xfsp.miappstore.R;
import example.xfsp.miappstore.service.DownloadService;
import example.xfsp.miappstore.fragment.VPFragmentFactory;
import example.xfsp.miappstore.fragment.base.BaseViewPagerFragment;
import example.xfsp.miappstore.view.ViewPagerIndicator;

/**
 * Created by Administrator on 2015/10/7.
 */
public class DownLoadActivity extends BaseActivity {

    private boolean isConnected = false;
    private DownloadService.DownloadServiceBinder mBinder;
    private View view;
    private List<String> mDatas = Arrays.asList("下载中", "已下载");
    private ViewPagerIndicator tab_indicator;
    private ViewPager vp_content;
    private List<BaseViewPagerFragment> mTabContents = new ArrayList<BaseViewPagerFragment>();
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void initToolBar() {
        super.initToolBar();
        view = View.inflate(this, R.layout.activity_download, null);
        tab_indicator = (ViewPagerIndicator) view.findViewById(R.id.tab_indicator);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void initView() {
        super.initView();
        //下载界面
        setContentView(view);
        //设置indicator标题
        tab_indicator.setTabItemTitles(mDatas);
        //viewPager
        vp_content = (ViewPager) view.findViewById(R.id.vp);
        //设置关联的ViewPager
        tab_indicator.setViewPager(vp_content, 0);
        //设置indicator监听事件
        tab_indicator.setOnPageChangeListener(new ViewPagerIndicator.PageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = new Intent(this, DownloadService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);

        //Fragment集合
        for (int i = 0; i < mDatas.size(); i++) {
            BaseViewPagerFragment vpFragment = VPFragmentFactory.newInstance().getDownloadFragment(i,this);
            if (vpFragment != null) {
                mTabContents.add(vpFragment);
            }
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };
        vp_content.setAdapter(mAdapter);
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (DownloadService.DownloadServiceBinder) service;
            isConnected = true;
        }

        //连接成功
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;
        }
    };

    @Override
    protected void onDestroy() {
        if (isConnected) {
            unbindService(connection);
        }
        super.onDestroy();
    }

    //ToolBar菜单选项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_download, menu);
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
