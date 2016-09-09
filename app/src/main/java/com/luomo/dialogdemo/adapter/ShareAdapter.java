package com.luomo.dialogdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luomo.dialogdemo.R;
import com.luomo.dialogdemo.domain.ShareDomain;

import java.util.List;

/**
 * @author :renpan
 * @version :v1.0
 * @class :
 * @date :2016-06-03 14:46
 * @description:
 */
public class ShareAdapter extends BaseAdapter {

    private Context mContext;
    List<ShareDomain> list;

    public ShareAdapter(Context mContext, List<ShareDomain> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.dialog_share_item, null);
        }
        ImageView image = (ImageView) convertView.findViewById(R.id.iv_image);
        TextView name = (TextView) convertView.findViewById(R.id.tv_assistant);
        image.setBackgroundResource(list.get(position).imageResource);
        name.setText(list.get(position).name);
        return convertView;
    }
}
