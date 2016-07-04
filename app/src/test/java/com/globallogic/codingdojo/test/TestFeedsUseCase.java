package com.globallogic.codingdojo.test;

import com.domain.callback.Callback;
import com.domain.interactors.GetFeedsUseCase;
import com.domain.model.RSS;
import com.globallogic.codingdojo.di.component.DaggerTestPresenterComponent;
import com.globallogic.codingdojo.di.module.TestPresenterModule;
import com.globallogic.codingdojo.mock.CustomRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import static org.mockito.Mockito.spy;

/**
 * @author facundo.mengoni
 * @since 0.1
 */
@RunWith(JUnit4.class)
public class TestFeedsUseCase {

    @Inject
    CustomRepository mRepository;
    @Inject
    GetFeedsUseCase useCase;
    private Callback presenterCallback = spy(new Callback() {
        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onSuccess(RSS rss) {

        }

        @Override
        public void onFinish() {

        }
    });

    @Before
    public void config() {
        DaggerTestPresenterComponent.builder().testPresenterModule(new TestPresenterModule()).build().inject(this);
        useCase.setCallback(presenterCallback);
    }

    @Test
    public void testGetFeeds() {
    }

    @Test
    public void testGetFeedsError() {
    }

    @Test
    public void testSearch() {
    }
}