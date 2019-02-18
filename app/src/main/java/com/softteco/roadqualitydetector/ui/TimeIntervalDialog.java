package com.softteco.roadqualitydetector.ui;

import android.content.Context;

import com.softteco.roadqualitydetector.R;
import com.softteco.roadqualitydetector.adapters.BaseListAdapter;
import com.softteco.roadqualitydetector.adapters.TimeIntervalListAdapter;
import com.softteco.roadqualitydetector.util.PreferencesUtil;

import java.util.Arrays;

public class TimeIntervalDialog extends SettingsListDialog {

    public TimeIntervalDialog(Context context, SettingsDialogListener listener) {
        super(context, listener);
    }

    @Override
    protected void initUI() {
        super.initUI();
        setDlgTitle(getContext().getString(R.string.time_interval));
    }

    @Override
    protected BaseListAdapter getAdapter() {
        return new TimeIntervalListAdapter(getContext(), Arrays.asList(PreferencesUtil.TIMEINTERVAL.values()));
    }
}