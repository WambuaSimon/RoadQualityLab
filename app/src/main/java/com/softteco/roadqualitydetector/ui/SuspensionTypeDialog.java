package com.softteco.roadqualitydetector.ui;

import android.content.Context;

import com.softteco.roadqualitydetector.R;
import com.softteco.roadqualitydetector.adapters.BaseListAdapter;
import com.softteco.roadqualitydetector.adapters.SuspensionListAdapter;
import com.softteco.roadqualitydetector.util.PreferencesUtil;

import java.util.Arrays;

public class SuspensionTypeDialog extends SettingsListDialog {

    public SuspensionTypeDialog(Context context, SettingsDialogListener listener) {
        super(context, listener);
    }

    @Override
    protected void initUI() {
        super.initUI();
        setDlgTitle(getContext().getString(R.string.suspension_type));
    }

    @Override
    protected BaseListAdapter getAdapter() {
        return new SuspensionListAdapter(getContext(), Arrays.asList(PreferencesUtil.SUSPENSION_TYPES.values()));
    }
}
