package example.xfsp.miappstore.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

import example.xfsp.miappstore.BaseActivity;
import example.xfsp.miappstore.BaseApplication;

public class UiUtils {
	/**
	 * 获取到字符数组
	 * @param tabNames  字符数组的id
	 */
	public static String[] getStringArray(int tabNames) {
		return getResource().getStringArray(tabNames);
	}

	public static Resources getResource() {
		return BaseApplication.getApplication().getResources();
	}
	public static Context getContext(){
		return BaseApplication.getApplication();
	}
	/** dip转换px */
	public static int dip2px(int dip) {
		final float scale = getResource().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f);
	}

	/** px转换dip */

	public static int px2dip(int px) {
		final float scale = getResource().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}
	/**
	 * 把Runnable 方法提交到主线程运行
	 * @param runnable
	 */
	public static void runOnUiThread(Runnable runnable) {
		// 如果当前线程是在主线程中运行，直接运行
		if(android.os.Process.myTid()== BaseApplication.getMainThreadId()){
			runnable.run();
		}else{
			//获取主线程的handler
			BaseApplication.getHandler().post(runnable);
		}
	}

	public static View inflate(int id) {
		return View.inflate(getContext(), id, null);
	}

	/**
	 * 根据id获取图片资源
	 * @param id
	 * @return
	 */
	public static Drawable getDrawalbe(int id) {
		return getResource().getDrawable(id);
	}

	public static int getDimens(int homePictureHeight) {
		return (int) getResource().getDimension(homePictureHeight);
	}
	/**
	 * 延迟执行 任务
	 * @param run   任务
	 * @param time  延迟的时间
	 */
	public static void postDelayed(Runnable run, int time) {
		BaseApplication.getHandler().postDelayed(run, time); // 调用Runable里面的run方法
	}
	/**
	 * 取消任务
	 * @param auToRunTask
	 */
	public static void cancel(Runnable auToRunTask) {
		BaseApplication.getHandler().removeCallbacks(auToRunTask);
	}

	//开启一个Activity
	public static void startActivity(Intent intent){
		//如果不在Activity里面打开activity,需要指定任务栈
		if(BaseActivity.baseActivity==null){
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getContext().startActivity(intent);
		}else{
			//前台Activity打开
			BaseActivity.baseActivity.startActivity(intent);
		}
	}

	/**
	 * 格式化时间，返回String
	 * @param time
	 * @return
	 */
	public static String parseDate(long time){
		Date date=new Date(time);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		return simpleDateFormat.format(date);
	}

}
