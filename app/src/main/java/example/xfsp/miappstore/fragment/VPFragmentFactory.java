package example.xfsp.miappstore.fragment;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import example.xfsp.miappstore.fragment.base.BaseViewPagerFragment;
import example.xfsp.miappstore.fragment.impl.CategoryFragment;
import example.xfsp.miappstore.fragment.impl.DownLoadedFragment;
import example.xfsp.miappstore.fragment.impl.DownLoadingFragment;
import example.xfsp.miappstore.fragment.impl.IndexFragment;
import example.xfsp.miappstore.fragment.impl.ManagerFragmnet;
import example.xfsp.miappstore.fragment.impl.TopFragment;

/**
 * Created by Administrator on 2015/9/19.
 */
public class VPFragmentFactory {
    private static VPFragmentFactory fragmentFactory = new VPFragmentFactory();
    private Map<Integer,BaseViewPagerFragment> mainMap = new HashMap<Integer,BaseViewPagerFragment>();
    private Map<Integer,BaseViewPagerFragment> downloadIngMap = new HashMap<Integer,BaseViewPagerFragment>();
    private BaseViewPagerFragment baseViewPagerFragment;
    public void VPFragmentFactory(){
    }

    //单例设计模式
    public static VPFragmentFactory newInstance(){
        return fragmentFactory;
    }

    //获取主界面Fragment
    public BaseViewPagerFragment getVpFragment(int position){
        //先从集合中查找
        BaseViewPagerFragment baseViewPagerFragment =null;
        baseViewPagerFragment = mainMap.get(position);
        if(baseViewPagerFragment==null){
            switch (position){
                case 0:
                    //精品
                    baseViewPagerFragment = new IndexFragment();
                    break;
                case 1:
                    //排行
                    baseViewPagerFragment = new TopFragment();
                    break;
                case 2:
                    //分类
                    baseViewPagerFragment = new CategoryFragment();
                    break;
                case 3:
                    //管理
                    baseViewPagerFragment = new ManagerFragmnet();
                    break;
                default:
                    break;
            }
            if (baseViewPagerFragment!=null){
                mainMap.put(position, baseViewPagerFragment);
            }
        }
        return baseViewPagerFragment;
    }

    //获取下载中心Fragment
    public BaseViewPagerFragment getDownloadFragment(int position,Context context){
        //先从集合中查找
        BaseViewPagerFragment downLoadFragment = null;
        downLoadFragment = downloadIngMap.get(position);
        if(downLoadFragment==null){
            switch (position){
                case 0:
                    //下载中
                    downLoadFragment = new DownLoadingFragment(context);
                    break;
                case 1:
                    //已下载
                    downLoadFragment = new DownLoadedFragment(context);
                    break;
                default:
                    break;
            }
            if (downLoadFragment!=null){
                downloadIngMap.put(position, downLoadFragment);
            }
        }
        return downLoadFragment;
    }
}
