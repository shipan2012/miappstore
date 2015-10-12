package example.xfsp.miappstore.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * ViewUtils
 */
public class ViewUtils {
    public static void removeParent(View v){
        // 先找到父控件，再通过父控件移除子控件
        ViewParent parent = v.getParent();
        if(parent instanceof ViewGroup){
            ViewGroup group=(ViewGroup) parent;
            group.removeView(v);
        }
    }
}
