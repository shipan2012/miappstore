package example.xfsp.miappstore.protocal.impl;

import example.xfsp.miappstore.bean.CategoryList;
import example.xfsp.miappstore.protocal.BaseProtocal;

/**
 * Created by Administrator on 2015/9/21.
 */
public class CategoryProtocal extends BaseProtocal<CategoryList> {

    private CategoryList categoryList;

    @Override
    public String getKey() {
        return "CategoryApps";
    }

    @Override
    public CategoryList parseJson(String json) {
        categoryList = gson.fromJson(json, CategoryList.class);
        return categoryList;
    }

    @Override
    public String getUrl() {
        return "http://app.market.xiaomi.com/apm/category?clientId=0&co=CN&densityScaleFactor=0&imei=0&la=zh&marketVersion=0&miuiBigVersionName=0&model=0&os=0&resolution=0&ro=unknown&sdk=16&session=0";
    }
}
