package com.globallogic.codingdojo.test;

import com.globallogic.codingdojo.di.component.DaggerTestPresenterComponent;
import com.globallogic.codingdojo.di.module.TestPresenterModule;
import com.globallogic.codingdojo.mock.CustomRepository;
import com.globallogic.codingdojo.mock.TestFeedListView;
import com.globallogic.codingdojo.presenters.FeedsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import static org.mockito.Mockito.spy;

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
    }

    @Test
    public void testSearchWithEmptyString() {
        String textToSearch = "";
    }

    @Test
    public void testSearchWithOneResult() {
        String textToSearch = "run";
    }

    @Test
    public void testSearchWithTwoResults() {
        String textToSearch = "Uno";
    }

    @Test
    public void testSearchWithoutResults() {
        String textToSearch = "arw";
    }

    @Test
    public void searchWithThreeSpaces() {
        String textToSearch = "   ";
    }

    @Test
    public void testSearchWithLeadingSpacesAndOneResult() {
        String textToSearch = "   run";
    }

    @Test
    public void testSearchWithLeadingSpacesAndTwoResults() {
        String textToSearch = "   Uno";
    }

    @Test
    public void testSearchWithEndingSpacesAndOneResult() {
        String textToSearch = "run   ";
    }

    @Test
    public void testSearchWithLeadingAndEndingSpacesAndOneResult() {
        String textToSearch = "   run   ";
    }
}