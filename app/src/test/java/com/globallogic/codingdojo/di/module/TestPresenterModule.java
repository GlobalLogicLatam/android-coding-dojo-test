package com.globallogic.codingdojo.di.module;

import com.domain.mappers.ContentMapper;
import com.domain.mappers.ItemMapper;
import com.domain.mappers.RssMapper;
import com.domain.mappers.Transformable;
import com.domain.model.Content;
import com.domain.model.Item;
import com.domain.model.RSS;
import com.globallogic.codingdojo.mock.CustomRepository;
import com.globallogic.codingdojo.mock.RSSMockFactory;
import com.globallogic.codingdojo.presenters.BasePresenter;
import com.globallogic.codingdojo.presenters.FeedsPresenter;
import com.globallogic.codingdojo.view.FeedsView;
import com.globallogic.data.dto.ContentDTO;
import com.globallogic.data.dto.ItemDTO;
import com.globallogic.data.dto.RssDTO;
import com.globallogic.data.repository.FeedsRepository;
import com.globallogic.data.service.ServiceFacade;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Callback;

import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.doAnswer;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * @author facundo.mengoni
 * @since 0.3
 */
@Module
public class TestPresenterModule {
    @Provides
    @Singleton
    CustomRepository provideRssSubscriber(RSSMockFactory factory) {
        return new CustomRepository(factory);
    }

    @Provides
    @Singleton
    ServiceFacade provideServiceFacade(final CustomRepository rssCallback) {
        ServiceFacade serviceFacade = mock(ServiceFacade.class);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                retrofit2.Callback<RssDTO> callback = (Callback<RssDTO>) invocation.getArguments()[0];
                rssCallback.setCallback(callback);
                return null;
            }
        }).when(serviceFacade).getFeeds(any(Callback.class));
        return serviceFacade;
    }

    @Provides
    @Singleton
    FeedsRepository provideFeedsRepository(final ServiceFacade facade) {
        return new FeedsRepository() {
            @Override
            public void getFeeds(retrofit2.Callback<RssDTO> callback) {
                facade.getFeeds(callback);
            }
        };
    }

    @Provides
    @Singleton
    BasePresenter<FeedsView> provideFeedsPresenter(FeedsPresenter presenter) {
        return presenter;
    }

    @Provides
    @Singleton
    Transformable<RssDTO, RSS> provideRssMapper(RssMapper mapper) {
        return mapper;
    }

    @Provides
    @Singleton
    Transformable<ItemDTO, Item> provideItemMapper(ItemMapper mapper) {
        return mapper;
    }

    @Provides
    @Singleton
    Transformable<ContentDTO, Content> provideContentMapper(ContentMapper mapper) {
        return mapper;
    }
}