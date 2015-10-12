package example.xfsp.miappstore.protocal.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import example.xfsp.miappstore.bean.IndexTop;
import example.xfsp.miappstore.protocal.BaseProtocal;

/**
 * Created by Administrator on 2015/9/20.
 */
public class IndexTopProtocal extends BaseProtocal<List<IndexTop>> {

    @Override
    public String getKey() {
        return "Index_Top";
    }

    @Override
    public List<IndexTop> parseJson(String json) {
        List<IndexTop> index_tops = new ArrayList<IndexTop>();
        try {
            JSONObject Object = new JSONObject(json);
            String host = Object.getString("host");
            JSONArray topfeaturedList = Object.getJSONArray("topfeaturedList");
            IndexTop indexTop;
            for (int i=0;i<topfeaturedList.length();i++){
                JSONObject jsonObject = topfeaturedList.getJSONObject(i);
                int featuredType = jsonObject.getInt("featuredType");
                long relatedId = jsonObject.getLong("relatedId");
                String title = jsonObject.getString("title");
                String mticon = jsonObject.getString("mticon");
                indexTop = new IndexTop();
                indexTop.setHost(host);
                indexTop.setFeaturedType(featuredType);
                indexTop.setMticon(mticon);
                indexTop.setTitle(title);
                indexTop.setRelatedId(relatedId);
                index_tops.add(indexTop);
            }
            return index_tops;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getUrl() {
        return "http://app.market.xiaomi.com/apm/topfeatured/top/0?clientId=0&co=CN&densityScaleFactor=0&imei=0&la=zh&marketVersion=0&miuiBigVersionName=0&model=0&newUser=false&os=0&resolution=0&ro=unknown&sdk=0&session=0";
    }
}
