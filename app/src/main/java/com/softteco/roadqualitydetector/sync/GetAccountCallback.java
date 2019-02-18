package com.softteco.roadqualitydetector.sync;

import com.softteco.roadqualitydetector.users.User;

public interface GetAccountCallback {
    void onComplete(SyncDataType type, User result);
    void onError(Exception e);
}