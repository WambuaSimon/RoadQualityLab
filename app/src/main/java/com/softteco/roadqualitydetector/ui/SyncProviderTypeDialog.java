package com.softteco.roadqualitydetector.ui;

import android.content.Context;

import com.softteco.roadqualitydetector.R;
import com.softteco.roadqualitydetector.adapters.BaseListAdapter;
import com.softteco.roadqualitydetector.adapters.SuspensionListAdapter;
import com.softteco.roadqualitydetector.adapters.SyncDataTypeListAdapter;
import com.softteco.roadqualitydetector.sync.SyncDataType;
import com.softteco.roadqualitydetector.util.PreferencesUtil;

import java.util.Arrays;

public class SyncProviderTypeDialog extends SettingsListDialog {

    public SyncProviderTypeDialog(Context context, SettingsDialogListener listener) {
        super(context, listener);
    }

    @Override
    protected void initUI() {
        super.initUI();
        setDlgTitle(getContext().getString(R.string.sync_data_type));
    }

    protected void onListItemClick(int position) {
        if (getAdapter() != null) {
            SyncDataType type = (SyncDataType) getAdapter().getItem(position);
            if (type != null && dialogListener != null) {
                dialogListener.onRequestClose(type.ordinal());
            }
        }
        dismiss();
    }

    @Override
    protected BaseListAdapter getAdapter() {
        return new SyncDataTypeListAdapter(getContext(), Arrays.asList(SyncDataType.values()));
    }
}
