package example.xfsp.miappstore.fragment.impl;

import android.view.View;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.fragment.base.BaseViewPagerFragment;
import example.xfsp.miappstore.view.LoadPage;

/**
 * Created by Administrator on 2015/9/19.
 */
public class ManagerFragmnet extends BaseViewPagerFragment {
    @Override
    protected View createSuccessView() {
        View view = View.inflate(getContext(), R.layout.fragment_manager, null);
        View rl_myapps = view.findViewById(R.id.rl_myapps);
        rl_myapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @Override
    public LoadPage.LoadResult loadData() {
        return LoadPage.LoadResult.success;
    }
}
