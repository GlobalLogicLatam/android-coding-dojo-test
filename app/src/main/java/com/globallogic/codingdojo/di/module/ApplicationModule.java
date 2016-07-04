package com.globallogic.codingdojo.di.module;

import android.app.Application;
import android.content.Context;

import com.globallogic.data.repository.FeedsRepository;
import com.globallogic.data.service.FeedsRepositoryImplementation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author natalia
 * @since 0.1
 */
@Module
public class ApplicationModule {

    private final Application application;


    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    FeedsRepository provideFeedRepository(FeedsRepositoryImplementation feedsRepositoryImplementation) {
        return feedsRepositoryImplementation;
    }
}
