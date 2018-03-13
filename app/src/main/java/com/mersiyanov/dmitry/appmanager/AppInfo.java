package com.mersiyanov.dmitry.appmanager;

import android.graphics.drawable.Drawable;

import java.io.File;

/**
 * Created by Dmitry on 03.03.2018.
 */

public class AppInfo {

    private final String packageName;
    private final int versionCode;
    private final String versionName;
    private final String name;
    private final Drawable icon;
    private final boolean isSystem;
    private final File apkFile;


    public AppInfo(String packageName, int versionCode, String versionName, String name, Drawable icon, boolean isSystem, File apkFile) {
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.name = name;
        this.icon = icon;
        this.isSystem = isSystem;
        this.apkFile = apkFile;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "packageName='" + packageName + '\'' +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", name='" + name + '\'' +
                ", icon=" + icon +
                '}';
    }


    public String getPackageName() {
        return packageName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public File getApkFile() {
        return apkFile;
    }

}
