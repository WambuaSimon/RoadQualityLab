package com.softteco.roadqualitydetector.ui;

import android.content.Context;

import com.softteco.roadqualitydetector.R;
import com.softteco.roadqualitydetector.adapters.BaseListAdapter;
import com.softteco.roadqualitydetector.adapters.SyncModeListAdapter;
import com.softteco.roadqualitydetector.util.PreferencesUtil;

import java.util.Arrays;

public class SynchronizeModeDialog extends SettingsListDialog {

    public SynchronizeModeDialog(Context context, SettingsDialogListener listener) {
        super(context, listener);
    }

    @Override
    protected void initUI() {
        super.initUI();
        setDlgTitle(getContext().getString(R.string.synchronize));
    }

    @Override
    protected BaseListAdapter getAdapter() {
        return new SyncModeListAdapter(getContext(), Arrays.asList(PreferencesUtil.SYNC_MODES.values()));
    }
}
