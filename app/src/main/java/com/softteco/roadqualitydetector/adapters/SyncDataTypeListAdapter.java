package com.softteco.roadqualitydetector.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softteco.roadqualitydetector.R;
import com.softteco.roadqualitydetector.sync.SyncDataType;
import com.softteco.roadqualitydetector.util.PreferencesUtil;
import com.softteco.roadqualitydetector.util.ViewHolder;

import java.util.List;

public class SyncDataTypeListAdapter extends BaseListAdapter {

    public SyncDataTypeListAdapter(Context context) {
        super(context);
    }

    public SyncDataTypeListAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_list_adapter, parent, false);
        }
        SyncDataType dataType = (SyncDataType) getItem(position);
        final TextView text = ViewHolder.get(view, R.id.text);
        text.setText(getContext().getString(dataType.getNameId()));
        return view;
    }
}
