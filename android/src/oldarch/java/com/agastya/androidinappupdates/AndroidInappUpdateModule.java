package com.agastya.androidinappupdates;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;

public class AndroidInappUpdateModule extends ReactContextBaseJavaModule {
    private AndroidInappUpdateImpl impl;

    static final String NAME = AndroidInappUpdateImpl.NAME;

    AndroidInappUpdateModule(ReactApplicationContext context) {
        super(context);
        impl = new AndroidInappUpdateImpl(context);
    }

    @Override
    @NonNull
    public String getName() {
        return AndroidInappUpdateImpl.NAME;
    }

    @Override
    public void onCatalystInstanceDestroy() {
        impl.unregisterListener();
    }

    @ReactMethod
    public void checkAppUpdate(double appUpdateType, double clientVersionStalenessDays, final Promise promise) {
        impl.checkAppUpdate(appUpdateType, clientVersionStalenessDays, promise);
    }

    @ReactMethod
    public void checkUpdateStatus(final Promise promise) {
        impl.checkUpdateStatus(promise);
    }

    @ReactMethod
    public void completeUpdate(final Promise promise) {
        impl.completeUpdate(promise);
    }

}

