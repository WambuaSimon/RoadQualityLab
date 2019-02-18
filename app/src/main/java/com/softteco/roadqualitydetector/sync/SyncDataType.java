package com.softteco.roadqualitydetector.sync;

import android.content.Context;

import com.softteco.roadqualitydetector.R;
import com.softteco.roadqualitydetector.sync.dropbox.DropboxManager;
import com.softteco.roadqualitydetector.sync.google.GoogleDriveManager;
import com.softteco.roadqualitydetector.util.PreferencesUtil;

public enum SyncDataType {

    DROPBOX (R.string.fr_settings_prividerType_dropbox),
    GOOGLE_DRIVE(R.string.fr_settings_prividerType_google);

    private int nameId;

    SyncDataType(int nameId) {
        this.nameId = nameId;
    }

    public static SyncDataManager getSyncDataManager(Context context) {
        SyncDataType type = PreferencesUtil.getInstance().getSyncProviderType();
        return getSyncDataManager(context, type);
    }

    public static SyncDataManager getSyncDataManager(Context context, SyncDataType type) {
        switch (type) {
            case DROPBOX:
                return new DropboxManager(context);
            case GOOGLE_DRIVE:
                return new GoogleDriveManager(context);
        }
        return null;
    }

    public int getNameId() {
        return nameId;
    }
}
