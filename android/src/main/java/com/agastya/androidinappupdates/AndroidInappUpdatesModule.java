package com.agastya.androidinappupdates;

import android.app.Activity;
import android.content.IntentSender;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

public class AndroidInappUpdatesModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public AndroidInappUpdatesModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "AndroidInappUpdates";
    }

    protected void checkUpdate(final Promise promise, int appUpdateType){
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(reactContext);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                            && appUpdateInfo.isUpdateTypeAllowed(appUpdateType)) {
                AppUpdateOptions options = AppUpdateOptions.newBuilder(appUpdateType).build();
                final Activity activity = getCurrentActivity();
                Task<Integer> startUpdateFlow = appUpdateManager.startUpdateFlow(appUpdateInfo,activity,options);

                startUpdateFlow.addOnFailureListener(failure -> {
                    promise.reject("reject", "startUpdateFlow failure" + failure.toString());
                });

                startUpdateFlow.addOnSuccessListener(result -> {
                    promise.resolve("Update Successful " + result);
                });
            } else {
                promise.reject("reject", "No update available");
            }
        });

        appUpdateInfoTask.addOnFailureListener(failure -> {
            promise.reject("reject", "checkAppUpdate failure: " + failure.toString());
        });
    }

    @ReactMethod
    public void checkAppUpdate(int appUpdateType, final Promise promise){
        checkUpdate(promise, appUpdateType);
    }




}
