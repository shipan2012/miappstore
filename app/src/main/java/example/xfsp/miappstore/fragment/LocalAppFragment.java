package example.xfsp.miappstore.fragment;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.adapter.LocalAppAdapter;

public class LocalAppFragment extends Fragment {
    List<ApplicationInfo> appInfoList = new ArrayList<>();
    RecyclerView localAppList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PackageManager packageManager = getContext().getPackageManager();
        List<ApplicationInfo> temp = packageManager.getInstalledApplications(
                PackageManager.GET_META_DATA);
        for (ApplicationInfo info : temp) {
            if (!isSystemPackage(info)) {
                appInfoList.add(info);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_app, container, false);

        localAppList = (RecyclerView) view.findViewById(R.id.local_app_list);
        LocalAppAdapter adapter = new LocalAppAdapter(appInfoList);
        localAppList.setLayoutManager(new LinearLayoutManager(getContext()));
        localAppList.setAdapter(adapter);
        return view;
    }

    public boolean isSystemPackage(ApplicationInfo info) {
        return ((info.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }
}
