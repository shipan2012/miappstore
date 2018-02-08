package example.xfsp.miappstore.fragment;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.adapter.AppInfoAdapter;
import example.xfsp.miappstore.bean.AppInfoEntity;
import me.yokeyword.indexablerv.IndexableLayout;

public class LocalAppFragment extends Fragment {
    List<AppInfoEntity> entityList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PackageManager packageManager = getContext().getPackageManager();
        List<ApplicationInfo> temp = packageManager.getInstalledApplications(
                PackageManager.GET_META_DATA);
        for (ApplicationInfo info : temp) {
            if (!isSystemPackage(info)) {
                AppInfoEntity entity = new AppInfoEntity();
                entity.setName((String) packageManager.getApplicationLabel(info));
                entity.setApplicationInfo(info);
                entityList.add(entity);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_app, container, false);
        IndexableLayout indexableLayout = (IndexableLayout) view.findViewById(
                R.id.indexable_layout);
        indexableLayout.setLayoutManager(new LinearLayoutManager(getContext()));
        AppInfoAdapter adapter = new AppInfoAdapter(getContext());
        indexableLayout.setAdapter(adapter);
        indexableLayout.setOverlayStyle_Center();
        indexableLayout.setStickyEnable(false);
        adapter.setDatas(entityList);
        return view;
    }

    public boolean isSystemPackage(ApplicationInfo info) {
        return ((info.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }
}
