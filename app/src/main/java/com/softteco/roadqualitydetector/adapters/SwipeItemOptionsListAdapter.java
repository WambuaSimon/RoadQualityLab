package com.softteco.roadqualitydetector.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softteco.roadqualitydetector.R;
import com.softteco.roadqualitydetector.ui.SwipeListItemSelectionTypes;
import com.softteco.roadqualitydetector.util.PreferencesUtil;
import com.softteco.roadqualitydetector.util.ViewHolder;

import java.util.List;

/**
 * Created by ppp on 09.06.2015.
 */
public class SwipeItemOptionsListAdapter extends BaseListAdapter {

    public SwipeItemOptionsListAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_list_adapter, parent, false);
        }
        SwipeListItemSelectionTypes type = (SwipeListItemSelectionTypes) getItem(position);
        final TextView text = ViewHolder.get(view, R.id.text);
        if (SwipeListItemSelectionTypes.SYNC.equals(type)) {
            setupSyncDataItem(text);
        } else {
            text.setText(getContext().getString(type.getNameId()));
        }
        return view;
    }

    private void setupSyncDataItem(final TextView text) {
        String dataTypeName = PreferencesUtil.getInstance().getSyncProviderTypeName(getContext());
        text.setText(getContext().getString(R.string.sync_item_title, dataTypeName));
    }
}
