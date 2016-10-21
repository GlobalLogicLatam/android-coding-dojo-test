package com.globallogic.codingdojo.test;

import com.globallogic.codingdojo.domain.model.RSS;
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
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        presenter.setView(view);
        presenter.onResume();

        assertTrue(view.isShowProgress());
        verify(view, never()).hideProgress();
        verify(view, times(1)).showProgress();

        //generates the response.
        mRepository.sendResponse();

        assertFalse(view.isShowProgress());
        assertFalse(view.isShowErrorScreen());
        assertFalse(view.isShowNoAvailableDataScreen());
        assertTrue(view.isShowListScreen());

        assertTrue(view.getRss().getItems().size() == 2);

        verify(view, times(1)).hideProgress();
        verify(view, times(1)).showProgress();
        verify(view, times(1)).displayFeeds(Matchers.any(RSS.class));
        verify(view, never()).displayErrorScreen();
        verify(view, never()).displayNoAvailableDataScreen();

        //try to check the number of arguments.
        ArgumentCaptor<RSS> captor = ArgumentCaptor.forClass(RSS.class);
        verify(view).displayFeeds(captor.capture());
        assertNotNull(captor.getValue());
        assertNotNull(captor.getValue().getItems());
        assertEquals(captor.getValue().getItems().size(), 2);
    }

    @Test
    public void testPresenterWithErrorScreen() {
        presenter.setView(view);
        presenter.onResume();

        assertTrue(view.isShowProgress());
        verify(view, never()).hideProgress();
        verify(view, times(1)).showProgress();

        //generates the response.
        mRepository.sendResponseWithError();

        assertFalse(view.isShowProgress());
        assertTrue(view.isShowErrorScreen());
        assertFalse(view.isShowNoAvailableDataScreen());
        assertFalse(view.isShowListScreen());

        //test state with mockito
        verify(view, times(1)).hideProgress();
        verify(view, times(1)).showProgress();
        verify(view, never()).displayFeeds(Matchers.any(RSS.class));
        verify(view, never()).displayNoAvailableDataScreen();
        verify(view, times(1)).displayErrorScreen();

        assertNull(view.getRss());
    }

    @Test
    public void testPresenterWithEmptyScreen() {
        presenter.setView(view);
        presenter.onResume();

        //check the state of the progress dialog
        assertTrue(view.isShowProgress());
        verify(view, never()).hideProgress();
        verify(view, times(1)).showProgress();

        //generates the response.
        mRepository.sendResponseWithEmptyList();

        assertFalse(view.isShowProgress());
        assertFalse(view.isShowErrorScreen());
        assertTrue(view.isShowNoAvailableDataScreen());
        assertFalse(view.isShowListScreen());

        verify(view, times(1)).hideProgress();
        verify(view, times(1)).showProgress();
        verify(view, never()).displayFeeds(Matchers.any(RSS.class));
        verify(view, times(1)).displayNoAvailableDataScreen();
        verify(view, never()).displayErrorScreen();

        assertNull(view.getRss());
    }
}