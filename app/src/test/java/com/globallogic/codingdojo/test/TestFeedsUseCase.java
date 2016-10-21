package com.globallogic.codingdojo.test;

import com.globallogic.codingdojo.domain.callback.Callback;
import com.globallogic.codingdojo.domain.interactors.GetFeedsUseCase;
import com.globallogic.codingdojo.domain.model.RSS;
import com.globallogic.codingdojo.di.component.DaggerTestPresenterComponent;
import com.globallogic.codingdojo.di.module.TestPresenterModule;
import com.globallogic.codingdojo.mock.CustomRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        verify(presenterCallback, times(0)).onSuccess(any(RSS.class));
        verify(presenterCallback, times(0)).onError(any(Throwable.class));
        verify(presenterCallback, times(0)).onFinish();
        useCase.getFeeds();
        mRepository.sendResponse();
        verify(presenterCallback, times(1)).onSuccess(any(RSS.class));
        verify(presenterCallback, times(0)).onError(any(Throwable.class));
        verify(presenterCallback, times(1)).onFinish();
        useCase.getFeeds();
        verify(presenterCallback, times(2)).onSuccess(any(RSS.class));
        verify(presenterCallback, times(0)).onError(any(Throwable.class));
        verify(presenterCallback, times(2)).onFinish();
    }

    @Test
    public void testGetFeedsError() {
        verify(presenterCallback, times(0)).onSuccess(any(RSS.class));
        verify(presenterCallback, times(0)).onError(any(Throwable.class));
        verify(presenterCallback, times(0)).onFinish();
        useCase.getFeeds();
        mRepository.sendResponseWithError();
        verify(presenterCallback, times(0)).onSuccess(any(RSS.class));
        verify(presenterCallback, times(1)).onError(any(Throwable.class));
        verify(presenterCallback, times(1)).onFinish();
    }

    @Test
    public void testSearch() {
        useCase.getFeeds();
        mRepository.sendResponse();

        verify(presenterCallback, times(1)).onSuccess(any(RSS.class));
        verify(presenterCallback, times(0)).onError(any(Throwable.class));
        verify(presenterCallback, times(1)).onFinish();
        useCase.searchFeeds(null);
        verify(presenterCallback, times(1)).onSuccess(any(RSS.class));
        verify(presenterCallback, times(1)).onError(any(Throwable.class));
        verify(presenterCallback, times(2)).onFinish();
        useCase.searchFeeds("      aa      ");
        verify(presenterCallback, times(2)).onSuccess(any(RSS.class));
        verify(presenterCallback, times(1)).onError(any(Throwable.class));
        verify(presenterCallback, times(3)).onFinish();
        useCase.searchFeeds("      DNX      ");
        verify(presenterCallback, times(3)).onSuccess(any(RSS.class));
        verify(presenterCallback, times(1)).onError(any(Throwable.class));
        verify(presenterCallback, times(4)).onFinish();
        useCase.searchFeeds("      aaa      ");
        verify(presenterCallback, times(4)).onSuccess(any(RSS.class));
        verify(presenterCallback, times(1)).onError(any(Throwable.class));
        verify(presenterCallback, times(5)).onFinish();
    }
}