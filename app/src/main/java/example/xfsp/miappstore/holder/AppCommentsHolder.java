package example.xfsp.miappstore.holder;

import android.view.View;
import android.widget.Button;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.bean.AppDetail;
import example.xfsp.miappstore.utils.UiUtils;

/**
 * Created by Administrator on 2015/9/29.
 */
public class AppCommentsHolder extends BaseHolder<AppDetail> {

    private View view;
    private Button btn_comments;
    private Button btn_sameapps;
    private Button btn_authority;

    @Override
    public View initView() {
        view = View.inflate(UiUtils.getContext(), R.layout.holder_comments, null);
        btn_comments = (Button) view.findViewById(R.id.btn_comments);
        btn_sameapps = (Button) view.findViewById(R.id.btn_sameapps);
        btn_authority = (Button) view.findViewById(R.id.btn_authority);
        return view;
    }

    @Override
    public void refreshView(AppDetail appDetail, String hostUrl, int position) {
    }
}
