package com.globallogic.codingdojo.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * @author natalia
 * @since 0.1
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity activity() {
        return this.activity;
    }
}