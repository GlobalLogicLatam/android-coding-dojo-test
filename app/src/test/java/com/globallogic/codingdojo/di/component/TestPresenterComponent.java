package com.globallogic.codingdojo.di.component;

import com.globallogic.codingdojo.di.module.TestPresenterModule;
import com.globallogic.codingdojo.test.TestFeedsMapper;
import com.globallogic.codingdojo.test.TestFeedsPresenter;
import com.globallogic.codingdojo.test.TestFeedsUseCase;
import com.globallogic.codingdojo.test.TestSearchInFeedsPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author facundo.mengoni
 * @since 0.3
 */
@Singleton
@Component(modules = {TestPresenterModule.class})
public interface TestPresenterComponent {
    void inject(TestFeedsPresenter test);

    void inject(TestSearchInFeedsPresenter test);

    void inject(TestFeedsMapper test);

    void inject(TestFeedsUseCase test);
}