package com.globallogic.codingdojo.di.component;

import android.app.Activity;
import android.content.Context;

import com.globallogic.codingdojo.di.module.ApplicationModule;
import com.globallogic.codingdojo.data.repository.FeedsRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author natalia
 * @since 0.1
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(Activity activity);

    Context context();

    FeedsRepository provideFeedRepository();
}