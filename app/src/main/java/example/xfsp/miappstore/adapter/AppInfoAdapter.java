package example.xfsp.miappstore.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.bean.AppInfoEntity;
import me.yokeyword.indexablerv.IndexableAdapter;

public class AppInfoAdapter extends IndexableAdapter<AppInfoEntity> {

    private Context context;

    public AppInfoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
        if (context == null) {
            context = parent.getContext();
        }
        View itemView = LayoutInflater.from(context).inflate(R.layout.title_item, parent, false);
        return new TitleViewHolder(itemView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        if (context == null) {
            context = parent.getContext();
        }
        View itemView = LayoutInflater.from(context).inflate(
                R.layout.local_app_list_row, parent, false);
        return new ContentViewHolder(itemView);
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle) {
        ((TitleViewHolder) (holder)).indexTitleTV.setText(indexTitle);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, AppInfoEntity entity) {
        ContentViewHolder viewHolder = (ContentViewHolder) holder;
        ApplicationInfo info = entity.getApplicationInfo();
        PackageManager packageManager = context.getPackageManager();
        viewHolder.appLogo.setImageDrawable(packageManager.getApplicationIcon(info));
        viewHolder.appName.setText(packageManager.getApplicationLabel(info));

        Date date = new Date(new File(info.sourceDir).lastModified());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(date).toString();
        viewHolder.installDate.setText(dateStr);

        viewHolder.appSize.setText("");
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView indexTitleTV;

        public TitleViewHolder(View itemView) {
            super(itemView);
            indexTitleTV = (TextView) itemView.findViewById(R.id.index_title_tv);
        }
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {
        ImageView appLogo;
        TextView appName, installDate, appSize;

        public ContentViewHolder(View itemView) {
            super(itemView);
            appLogo = (ImageView) itemView.findViewById(R.id.app_logo);
            appName = (TextView) itemView.findViewById(R.id.app_name);
            installDate = (TextView) itemView.findViewById(R.id.install_date);
            appSize = (TextView) itemView.findViewById(R.id.app_size);
        }
    }
}
