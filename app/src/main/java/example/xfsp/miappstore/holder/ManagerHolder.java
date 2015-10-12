package example.xfsp.miappstore.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import example.xfsp.miappstore.utils.UiUtils;

/**
 * Created by Administrator on 2015/9/27.
 */
public class ManagerHolder extends BaseHolder<String> {

    private TextView textView;

    public ManagerHolder(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(UiUtils.getContext());
        textView.setText("成功了！");
        return textView;
    }

    @Override
    public void refreshView(String s, String hostUrl, int position) {
        textView.setText(s);
    }
}
