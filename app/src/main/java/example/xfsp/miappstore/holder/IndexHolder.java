package example.xfsp.miappstore.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.bean.ListAppEntity;

/**
 * Created by Administrator on 2015/9/22.
 */
public class IndexHolder extends BaseHolder<ListAppEntity> {

    private ImageView iv_icon;
    private RatingBar ratingBar;
    private TextView tv_title;
    private TextView tv_company;

    public IndexHolder(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.base_app_item, null);
        iv_icon = (ImageView) view.findViewById(R.id.item_icon);
        ratingBar = (RatingBar) view.findViewById(R.id.item_rating);
        tv_title = (TextView) view.findViewById(R.id.item_title);
        tv_company = (TextView) view.findViewById(R.id.item_app_company);
        return view;
    }

    @Override
    public void refreshView(ListAppEntity listAppEntity, String hostUrl, int position) {
        tv_company.setText(listAppEntity.getPublisherName());
        tv_title.setText(listAppEntity.getDisplayName());
        ratingBar.setRating(listAppEntity.getRatingScore());
        bitmapUtils.display(iv_icon,hostUrl+listAppEntity.getIcon());
    }
}
