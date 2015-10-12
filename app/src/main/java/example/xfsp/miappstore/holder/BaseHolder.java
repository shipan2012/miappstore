package example.xfsp.miappstore.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import com.lidroid.xutils.BitmapUtils;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.utils.download.DownloadHelper;
import example.xfsp.miappstore.utils.BitmapHelper;

/**
 * Created by Administrator on 2015/9/22.
 */
public abstract class BaseHolder<Data> {
    private View convertView;
    public BitmapUtils bitmapUtils;
    private Data data;
    public Context context;
    public DownloadHelper mDownloadHelper;

    public BaseHolder() {
        init();
    }

    public BaseHolder(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        if (mDownloadHelper == null) {
            mDownloadHelper = new DownloadHelper(context);
        }
        bitmapUtils = BitmapHelper.getBitmapUtils();
        //加载失败展示图片
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.place_holder_icon);
        //加载中展示图片
        bitmapUtils.configDefaultLoadingImage(R.mipmap.place_holder_icon);
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        this.convertView = initView();
        convertView.setTag(this);
    }

    public View getConvertView() {
        return convertView;
    }

    //初始化布局
    public abstract View initView();

    //刷新数据
    public abstract void refreshView(Data data, String hostUrl, int position);

}