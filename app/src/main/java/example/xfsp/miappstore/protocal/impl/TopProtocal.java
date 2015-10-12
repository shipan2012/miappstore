package example.xfsp.miappstore.protocal.impl;

import example.xfsp.miappstore.bean.TopList;
import example.xfsp.miappstore.http.Constant;
import example.xfsp.miappstore.protocal.BaseProtocal;

/**
 * Created by Administrator on 2015/9/20.
 */
public class TopProtocal extends BaseProtocal<TopList> {

    @Override
    public String getKey() {
        return "TopApps";
    }

    @Override
    public TopList parseJson(String json) {
        TopList topList = gson.fromJson(json, TopList.class);
        return topList;
    }

    @Override
    public String getUrl() {
        return Constant.TopPreUrl+getIndex()+Constant.TopLastUrl;
    }
}
