package example.xfsp.miappstore.holder;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.bean.AppDetail;
import example.xfsp.miappstore.utils.BitmapHelper;
import example.xfsp.miappstore.utils.UiUtils;

/**
 * Created by Administrator on 2015/9/29.
 */
public class AppScreensHolder extends BaseHolder<AppDetail> {

    private ImageView[] ivs;
    private String[] url;
    private BitmapUtils bitmapUtils;

    public AppScreensHolder() {
        bitmapUtils = BitmapHelper.getBitmapUtils();
        //加载失败展示图片
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.place_holder_screen);
        //加载中展示图片
        bitmapUtils.configDefaultLoadingImage(R.mipmap.place_holder_screen);
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
    }

    @Override
    public View initView() {
        View view=UiUtils.inflate(R.layout.holder_screens);
        ivs=new ImageView[5];
        ivs[0]=(ImageView) view.findViewById(R.id.screen_1);
        ivs[1]=(ImageView) view.findViewById(R.id.screen_2);
        ivs[2]=(ImageView) view.findViewById(R.id.screen_3);
        ivs[3]=(ImageView) view.findViewById(R.id.screen_4);
        ivs[4]=(ImageView) view.findViewById(R.id.screen_5);
        return view;
    }

    @Override
    public void refreshView(AppDetail appDetail, String hostUrl, int position) {
        String screenshot = appDetail.getScreenshot();
        url = screenshot.split(",");
        for (int i=0;i< url.length;i++){
            url[i]=hostUrl+url[i];
        }
        for(int i=0;i<5;i++){
            if(i<url.length){
                ivs[i].setVisibility(View.VISIBLE);
                this.bitmapUtils.display(ivs[i], url[i]);
            }else{
                ivs[i].setVisibility(View.GONE);
            }

        }
    }
}
