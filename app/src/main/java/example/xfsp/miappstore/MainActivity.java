package example.xfsp.miappstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.xfsp.miappstore.activity.DownLoadActivity;
import example.xfsp.miappstore.fragment.VPFragmentFactory;
import example.xfsp.miappstore.fragment.base.BaseViewPagerFragment;
import example.xfsp.miappstore.view.ViewPagerIndicator;

public class MainActivity extends BaseActivity {

    private View view;
    private ViewPager vp_content;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private FragmentPagerAdapter mAdapter;
    private List<BaseViewPagerFragment> mTabContents = new ArrayList<BaseViewPagerFragment>();
    private List<String> mDatas = Arrays.asList("精品", "排行", "分类", "管理");
    private ViewPagerIndicator tab_indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置Tab上的标题
        tab_indicator.setTabItemTitles(mDatas);
        vp_content.setAdapter(mAdapter);
        //设置关联的ViewPager
        tab_indicator.setViewPager(vp_content, 0);

        tab_indicator.setOnPageChangeListener(new ViewPagerIndicator.PageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabContents.get(position).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        view = View.inflate(this, R.layout.activity_main, null);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        toolbar.setBackgroundColor(getResources().getColor(R.color.light_toolbar));
        toolbar.setTitle("应用商店");
        toolbar.setLogo(R.mipmap.logo);//设置Logo
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.app_name, R.string.app_name
        );
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(view);
        vp_content = (ViewPager) view.findViewById(R.id.vp);
        tab_indicator = (ViewPagerIndicator) view.findViewById(R.id.tab_indicator);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < mDatas.size(); i++) {
            BaseViewPagerFragment vpFragment = VPFragmentFactory.newInstance().getVpFragment(i);
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
    }

    //ToolBar菜单选项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //当菜单选中时
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                //搜索框
                break;
            case R.id.download_center:
                Intent intent = new Intent(this, DownLoadActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
