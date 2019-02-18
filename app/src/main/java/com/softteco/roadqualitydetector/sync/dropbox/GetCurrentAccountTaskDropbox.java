package com.softteco.roadqualitydetector.sync.dropbox;

/**
 * Created by bogdan on 13.04.2016.
 */

import android.os.AsyncTask;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import com.softteco.roadqualitydetector.sync.GetAccountCallback;
import com.softteco.roadqualitydetector.sync.SyncDataType;
import com.softteco.roadqualitydetector.users.User;
import com.softteco.roadqualitydetector.util.Constants;
import com.softteco.roadqualitydetector.util.PreferencesUtil;

/**
 * Async task for getting user account info
 */
public class GetCurrentAccountTaskDropbox extends AsyncTask<Void, Void, User> {
    private final DbxClientV2 mDbxClient;
    private final GetAccountCallback mGetDropboxAccountCallback;
    private Exception mException;

    GetCurrentAccountTaskDropbox(DbxClientV2 dbxClient, GetAccountCallback getDropboxAccountCallback) {
        mDbxClient = dbxClient;
        mGetDropboxAccountCallback = getDropboxAccountCallback;
    }

    @Override
    protected void onPostExecute(User user) {
        if (mException != null) {
            mGetDropboxAccountCallback.onError(mException);
        } else {
            mGetDropboxAccountCallback.onComplete(SyncDataType.DROPBOX, user);
        }
    }

    @Override
    protected User doInBackground(Void... params) {
        try {
            FullAccount account = mDbxClient.users().getCurrentAccount();
            if (account != null) {
                User user = new User();
                user.email = account.getEmail();
                user.givenName = account.getName().getDisplayName();
                PreferencesUtil.getInstance().setDropboxAccountUserName(user.givenName);
                return user;
            }
        } catch (DbxException e) {
            mException = e;
        }
        return null;
    }
}
