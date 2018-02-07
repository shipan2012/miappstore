package example.xfsp.miappstore.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.flyco.tablayout.SlidingTabLayout;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.fragment.LocalAppFragment;
import example.xfsp.miappstore.fragment.InstallRecordFragment;
import example.xfsp.miappstore.fragment.AppStarFragment;

public class AppListActivity extends AppCompatActivity {
    private String[] TITLES = {"本地应用", "安装记录", "收藏"};
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        ViewPager appListVP = (ViewPager) findViewById(R.id.app_list_vp);
        appListVP.setAdapter(pagerAdapter);
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tab_layout);
        slidingTabLayout.setViewPager(appListVP, TITLES);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.light_toolbar));
        toolbar.setTitle("我的应用");
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new LocalAppFragment();
                    break;
                case 1:
                    fragment = new InstallRecordFragment();
                    break;
                case 2:
                    fragment = new AppStarFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}
