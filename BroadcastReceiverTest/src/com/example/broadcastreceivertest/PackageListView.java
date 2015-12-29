package com.example.broadcastreceivertest;

import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class PackageListView extends ListView {
    public PackageListView(Context context) {
        super(context);

        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> applicationInfos = pm.getInstalledApplications(0);
        setAdapter(new PackageListAdapter(applicationInfos));
    }

    private class PackageListAdapter extends BaseAdapter {

        private List<ApplicationInfo> infos;

        public PackageListAdapter(List<ApplicationInfo> infos) {
            this.infos = infos;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return infos == null ? 0 : infos.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return infos.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
