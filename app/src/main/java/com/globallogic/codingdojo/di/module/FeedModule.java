package com.globallogic.codingdojo.di.module;

import com.domain.mappers.ContentMapper;
import com.domain.mappers.ItemMapper;
import com.domain.mappers.RssMapper;
import com.domain.mappers.Transformable;
import com.domain.model.Content;
import com.domain.model.Item;
import com.domain.model.RSS;
import com.globallogic.data.dto.ContentDTO;
import com.globallogic.data.dto.ItemDTO;
import com.globallogic.data.dto.RssDTO;

import dagger.Module;
import dagger.Provides;

/**
 * @author natalia
 * @since 0.1
 */
@Module
public class FeedModule {

    @Provides
    Transformable<RssDTO, RSS> provideRssMapper(RssMapper mapper) {
        return mapper;
    }

    @Provides
    Transformable<ItemDTO, Item> provideItemMapper(ItemMapper mapper) {
        return mapper;
    }

    @Provides
    Transformable<ContentDTO, Content> provideContentMapper(ContentMapper mapper) {
        return mapper;
    }
}
