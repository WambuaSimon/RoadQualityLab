package com.softteco.roadqualitydetector.ui;

import android.content.Context;

import com.softteco.roadqualitydetector.R;
import com.softteco.roadqualitydetector.adapters.BaseListAdapter;
import com.softteco.roadqualitydetector.adapters.ExportListAdapter;
import com.softteco.roadqualitydetector.adapters.SuspensionListAdapter;
import com.softteco.roadqualitydetector.util.PreferencesUtil;

import java.util.Arrays;

public class ExportDialog extends SettingsListDialog {

    public ExportDialog(Context context, SettingsDialogListener listener) {
        super(context, listener);
    }

    @Override
    protected void initUI() {
        super.initUI();
        setDlgTitle(getContext().getString(R.string.export_title_dialog));
    }

    @Override
    protected BaseListAdapter getAdapter() {
        return new ExportListAdapter(getContext(), Arrays.asList(PreferencesUtil.EXPORT_TYPES.values()));
    }
}
