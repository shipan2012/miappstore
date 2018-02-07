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
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import example.xfsp.miappstore.R;

public class LocalAppAdapter extends RecyclerView.Adapter<LocalAppAdapter.ViewHolder> {
    private List<ApplicationInfo> dataSet;
    private Context context;

    public LocalAppAdapter(List<ApplicationInfo> infoList) {
        this.dataSet = (infoList == null) ? new ArrayList<ApplicationInfo>() : infoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View itemView = LayoutInflater.from(context).inflate(
                R.layout.local_app_list_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ApplicationInfo info = dataSet.get(position);
        PackageManager packageManager = context.getPackageManager();
        holder.appLogo.setImageDrawable(packageManager.getApplicationIcon(info));
        holder.appName.setText(packageManager.getApplicationLabel(info));

        Date date = new Date(new File(info.sourceDir).lastModified());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(date).toString();
        holder.installDate.setText(dateStr);

        holder.appSize.setText("");
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView appLogo;
        TextView appName, installDate, appSize;

        public ViewHolder(View itemView) {
            super(itemView);
            appLogo = (ImageView) itemView.findViewById(R.id.app_logo);
            appName = (TextView) itemView.findViewById(R.id.app_name);
            installDate = (TextView) itemView.findViewById(R.id.install_date);
            appSize = (TextView) itemView.findViewById(R.id.app_size);
        }
    }
}
