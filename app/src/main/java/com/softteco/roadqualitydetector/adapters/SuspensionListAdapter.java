package com.softteco.roadqualitydetector.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softteco.roadqualitydetector.R;
import com.softteco.roadqualitydetector.util.PreferencesUtil;
import com.softteco.roadqualitydetector.util.ViewHolder;

import java.util.List;

/**
 * Created by ppp on 09.06.2015.
 */
public class SuspensionListAdapter extends BaseListAdapter {

    public SuspensionListAdapter(Context context) {
        super(context);
    }

    public SuspensionListAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_list_adapter, parent, false);
        }
        PreferencesUtil.SUSPENSION_TYPES suspension = (PreferencesUtil.SUSPENSION_TYPES) getItem(position);
        final TextView text = ViewHolder.get(view, R.id.text);
        text.setText(String.format("%s", getSuspensionNameByType(suspension)));
        return view;
    }

    private String getSuspensionNameByType(PreferencesUtil.SUSPENSION_TYPES type) {
        switch (type) {
            case SOFT:
                return getContext().getString(R.string.soft);
            case MEDIUM:
                return getContext().getString(R.string.medium);
            case HARD:
                return getContext().getString(R.string.hard);
        }
        return "";
    }
}
