package example.xfsp.miappstore.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.bean.CategoriesEntity;

/**
 * Created by Administrator on 2015/9/22.
 */
public class CategoryHolder extends BaseHolder<CategoriesEntity> {
    private ImageView iv_icon;
    private TextView tv_name;

    public CategoryHolder(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.category_app_item, null);
        this.iv_icon = (ImageView) view.findViewById(R.id.item_icon);
        this.tv_name = (TextView) view.findViewById(R.id.category_name);
        return view;
    }

    @Override
    public void refreshView(CategoriesEntity categoriesEntity, String hostUrl, int position) {
        this.tv_name.setText(categoriesEntity.getName());
        bitmapUtils.display(this.iv_icon,hostUrl+categoriesEntity.getIcon());
    }
}
