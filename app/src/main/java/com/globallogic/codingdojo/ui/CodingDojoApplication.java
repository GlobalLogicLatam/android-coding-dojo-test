package com.globallogic.codingdojo.ui;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.BuildConfig;
import com.crashlytics.android.core.CrashlyticsCore;
import com.globallogic.codingdojo.di.component.ApplicationComponent;
import com.globallogic.codingdojo.di.component.DaggerApplicationComponent;
import com.globallogic.codingdojo.di.module.ApplicationModule;

import io.fabric.sdk.android.Fabric;

/**
 * Created by natalia on 30/03/16.
 */
public class CodingDojoApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        Fabric.with(this, new Crashlytics.Builder().core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()).build());
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}