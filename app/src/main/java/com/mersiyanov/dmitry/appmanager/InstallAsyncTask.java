package com.mersiyanov.dmitry.appmanager;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

/**
 * Created by Dmitry on 11.03.2018.
 */

public class InstallAsyncTask extends AsyncTask<String, Void, Boolean> {

    private final WeakReference<InstallListener> installListenerWeakReference;


    public InstallAsyncTask(InstallListener installListener) {
        super();
        this.installListenerWeakReference = new WeakReference<>(installListener);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String apkPath = params[0];
        return RootHelper.install(apkPath);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        InstallListener installListener = installListenerWeakReference.get();
        if (installListener != null) {
            if (aBoolean) {
                installListener.onInstalled();
            } else {
                installListener.onFailed();
            }
        }
    }

    public interface InstallListener {
        void onInstalled();

        void onFailed();
    }
}