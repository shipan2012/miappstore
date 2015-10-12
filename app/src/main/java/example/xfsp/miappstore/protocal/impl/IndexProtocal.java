package example.xfsp.miappstore.protocal.impl;

import example.xfsp.miappstore.bean.IndexList;
import example.xfsp.miappstore.protocal.BaseProtocal;

/**
 * Created by Administrator on 2015/9/20.
 */
public class IndexProtocal extends BaseProtocal<IndexList> {
    @Override
    public String getKey() {
        return "IndexApps";
    }

    @Override
    public IndexList parseJson(String json) {
        IndexList indexList = gson.fromJson(json, IndexList.class);
        return indexList;
    }

    @Override
    public String getUrl() {
        return "http://app.market.xiaomi.com/apm/featured?clientId=0&co=CN&densityScaleFactor=1.5&imei=0&la=zh&marketVersion=147&miuiBigVersionName=V7&model=MI+1S&os=4.12.5&page=0&resolution=480*854&ro=unknown&sdk=16&session=0&stamp=0";
    }
}
