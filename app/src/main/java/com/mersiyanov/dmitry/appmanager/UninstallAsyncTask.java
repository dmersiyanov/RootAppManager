package com.mersiyanov.dmitry.appmanager;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

/**
 * Created by Dmitry on 11.03.2018.
 */

public class UninstallAsyncTask extends AsyncTask<AppInfo, Void, Boolean> {

    private final WeakReference<UninstallListener> uninstallListenerWeakReference;


    public UninstallAsyncTask(UninstallListener uninstallListener) {
        super();
        this.uninstallListenerWeakReference = new WeakReference<>(uninstallListener);
    }

    @Override
    protected Boolean doInBackground(AppInfo... params) {
        AppInfo appInfo = params[0];

        if (appInfo.isSystem()) {
            return RootHelper.uninstallSystem(appInfo.getApkFile());
        } else {
            return RootHelper.uninstall(appInfo.getPackageName());
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        UninstallListener uninstallListener = uninstallListenerWeakReference.get();
        if (uninstallListener != null) {
            if (aBoolean) {
                uninstallListener.onUninstalled();
            } else {
                uninstallListener.onFailed();
            }
        }

    }

    public interface UninstallListener {
        void onUninstalled();

        void onFailed();
    }
}