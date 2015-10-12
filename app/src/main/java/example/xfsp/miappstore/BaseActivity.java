package example.xfsp.miappstore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理Activity，方便程序退出
 * 所有的Activity继承此Activity
 * @sp不会玩ps
 */
public class BaseActivity extends AppCompatActivity {
    //集合用来存放当前Activity
    private ArrayList<BaseActivity> activities = new ArrayList<BaseActivity>();

    public static BaseActivity baseActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //启动时加入到集合中,加锁，防止同时增加
        synchronized (activities){
            activities.add(this);
        }
        initToolBar();
        initView();
        initData();
    }

    //由子类去实现
    protected void initToolBar() {

    }
    protected void initView() {

    }
    protected void initData() {

    }


    @Override
    protected void onDestroy() {
        //销毁时，从集合中移除
        synchronized (activities){
            activities.remove(this);
        }
        super.onDestroy();
    }

    //那个Acitivity可见，谁就是前台atcivity
    @Override
    protected void onResume() {
        super.onResume();
        baseActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        baseActivity = this;
    }

    //遍历销毁所有Activity
    public void  killAll(){
        //不能一边遍历一边移除，先复制一份
        List<BaseActivity> copy;
        synchronized (activities){
            copy = new ArrayList<BaseActivity>(activities);
        }
        for (BaseActivity activity:copy){
            activities.remove(copy);
        }
        //最后杀死当前进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
