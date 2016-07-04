package com.globallogic.codingdojo.test;

import com.globallogic.codingdojo.di.component.DaggerTestPresenterComponent;
import com.globallogic.codingdojo.di.module.TestPresenterModule;
import com.globallogic.codingdojo.mock.CustomRepository;
import com.globallogic.codingdojo.mock.TestFeedListView;
import com.globallogic.codingdojo.presenters.BasePresenter;
import com.globallogic.codingdojo.view.FeedsView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import static org.mockito.Mockito.spy;

/**
 * @author julio.kun
 * @since 0.1
 */
@RunWith(JUnit4.class)
public class TestFeedsPresenter {

    @Inject
    BasePresenter<FeedsView> presenter;
    @Inject
    CustomRepository mRepository;
    private TestFeedListView view;

    @Before
    public void config() {
        DaggerTestPresenterComponent.builder().testPresenterModule(new TestPresenterModule()).build().inject(this);
        view = spy(new TestFeedListView());
    }

    @Test
    public void testPresenterWithFullList() {
    }

    @Test
    public void testPresenterWithErrorScreen() {
    }

    @Test
    public void testPresenterWithEmptyScreen() {
    }
}