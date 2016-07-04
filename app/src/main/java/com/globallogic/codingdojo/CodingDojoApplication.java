package com.globallogic.codingdojo;

import android.app.Application;

import com.globallogic.codingdojo.di.component.ApplicationComponent;
import com.globallogic.codingdojo.di.component.DaggerApplicationComponent;
import com.globallogic.codingdojo.di.module.ApplicationModule;

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
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}