package com.globallogic.codingdojo.test;

import com.globallogic.codingdojo.di.component.DaggerTestPresenterComponent;
import com.globallogic.codingdojo.di.module.TestPresenterModule;
import com.globallogic.codingdojo.domain.model.RSS;
import com.globallogic.codingdojo.mock.CustomRepository;
import com.globallogic.codingdojo.mock.TestFeedListView;
import com.globallogic.codingdojo.presenters.FeedsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests the {@link FeedsPresenter} with search.
 *
 * @author julio.kun
 * @since 0.1
 */
@RunWith(JUnit4.class)
public class TestSearchInFeedsPresenter {

    @Inject
    FeedsPresenter presenter;
    @Inject
    CustomRepository mRepository;
    private TestFeedListView view;

    @Before
    public void config() {
        DaggerTestPresenterComponent.builder().testPresenterModule(new TestPresenterModule()).build().inject(this);
        view = spy(new TestFeedListView());
        presenter.setView(view);

        presenter.onResume();
        mRepository.sendResponseFromFile();
    }

    @Test
    public void testSearchWithEmptyString() {
        String textToSearch = "";
        presenter.search(textToSearch);

        assertTrue(view.isShowListScreen());
        assertTrue(view.getRss().getItems().size() == 10);
        verify(view, times(0)).displayNoAvailableDataScreen();

        verify(view, times(2)).displayFeeds(any(RSS.class));
    }

    @Test
    public void testSearchWithOneResult() {
        String textToSearch = "run";
        presenter.search(textToSearch);

        assertTrue(view.isShowListScreen());
        assertNotNull(view.getRss());
        assertNotNull(view.getRss().getItems());
        assertTrue(view.getRss().getItems().size() == 1);

        //Verify that this method is executed twice. The first one when the full list is first loaded.
        //and the second one with the response of the search operation
        verify(view, times(2)).displayFeeds(any(RSS.class));
    }

    @Test
    public void testSearchWithTwoResults() {
        String textToSearch = "Uno";
        presenter.search(textToSearch);

        assertTrue(view.isShowListScreen());
        assertNotNull(view.getRss());
        assertNotNull(view.getRss().getItems());
        assertTrue(view.getRss().getItems().size() == 2);

        //Verify that this method is executed twice. The first one when the full list is first loaded.
        //and the second one with the response of the search operation
        verify(view, times(2)).displayFeeds(any(RSS.class));
    }

    @Test
    public void testSearchWithoutResults() {
        String textToSearch = "arw";
        presenter.search(textToSearch);

        assertTrue(view.isShowNoAvailableDataScreen());
        verify(view, times(1)).displayNoAvailableDataScreen();

        //This method should be executed once, when it loads the full list at the beginning.
        verify(view, times(1)).displayFeeds(any(RSS.class));
    }

    @Test
    public void searchWithThreeSpaces() {
        String textToSearch = "   ";
        presenter.search(textToSearch);

        assertTrue(view.isShowListScreen());
        assertTrue(view.getRss().getItems().size() == 10);
        verify(view, times(0)).displayNoAvailableDataScreen();

        verify(view, times(2)).displayFeeds(any(RSS.class));
    }

    @Test
    public void testSearchWithLeadingSpacesAndOneResult() {
        String textToSearch = "   run";
        presenter.search(textToSearch);

        assertTrue(view.isShowListScreen());
        assertNotNull(view.getRss());
        assertNotNull(view.getRss().getItems());
        assertTrue(view.getRss().getItems().size() == 1);

        //Verify that this method is executed twice. The first one when the full list is first loaded.
        //and the second one with the response of the search operation
        verify(view, times(2)).displayFeeds(any(RSS.class));
    }

    @Test
    public void testSearchWithLeadingSpacesAndTwoResults() {
        String textToSearch = "   Uno";
        presenter.search(textToSearch);

        assertTrue(view.isShowListScreen());
        assertNotNull(view.getRss());
        assertNotNull(view.getRss().getItems());
        assertTrue(view.getRss().getItems().size() == 2);

        //Verify that this method is executed twice. The first one when the full list is first loaded.
        //and the second one with the response of the search operation
        verify(view, times(2)).displayFeeds(any(RSS.class));
    }

    @Test
    public void testSearchWithEndingSpacesAndOneResult() {
        String textToSearch = "run   ";
        presenter.search(textToSearch);

        assertTrue(view.isShowListScreen());
        assertNotNull(view.getRss());
        assertNotNull(view.getRss().getItems());
        assertTrue(view.getRss().getItems().size() == 1);

        //Verify that this method is executed twice. The first one when the full list is first loaded.
        //and the second one with the response of the search operation
        verify(view, times(2)).displayFeeds(any(RSS.class));
    }

    @Test
    public void testSearchWithLeadingAndEndingSpacesAndOneResult() {
        String textToSearch = "   run   ";
        presenter.search(textToSearch);

        assertTrue(view.isShowListScreen());
        assertNotNull(view.getRss());
        assertNotNull(view.getRss().getItems());
        assertTrue(view.getRss().getItems().size() == 1);

        //Verify that this method is executed twice. The first one when the full list is first loaded.
        //and the second one with the response of the search operation
        verify(view, times(2)).displayFeeds(any(RSS.class));
    }
}