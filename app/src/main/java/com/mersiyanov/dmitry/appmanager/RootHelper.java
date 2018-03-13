package com.mersiyanov.dmitry.appmanager;

import android.support.annotation.Nullable;

import java.io.File;
import java.util.List;

import eu.chainfire.libsuperuser.Shell;

/**
 * Created by Dmitry on 11.03.2018.
 */

public class RootHelper {

    public static boolean uninstall(String packageName) {
        String output = executeCommand("pm uninstall " + packageName);
        if (output != null && output.toLowerCase().contains("success")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean uninstallSystem(File appApk) {
        executeCommand("mount -o rw,remount /system");
        executeCommand("rm " + appApk.getAbsolutePath());
        executeCommand("mount -o ro,remount /system");

        // Проверяем, удалился ли файл
        String output = executeCommand("ls " + appApk.getAbsolutePath());

        if (output != null && output.trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Nullable
    private static String executeCommand(String command) {
        List<String> stdout = Shell.SU.run(command);
        if (stdout == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : stdout) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }
}
