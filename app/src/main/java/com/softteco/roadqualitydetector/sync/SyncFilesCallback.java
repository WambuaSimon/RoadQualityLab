package com.softteco.roadqualitydetector.sync;

import java.util.List;

public interface SyncFilesCallback {
    void onUploadComplete(List<String> result);
    void onError(Exception e);
}
