package com.globallogic.codingdojo.di.module;

import com.globallogic.codingdojo.domain.mappers.ContentMapper;
import com.globallogic.codingdojo.domain.mappers.ItemMapper;
import com.globallogic.codingdojo.domain.mappers.RssMapper;
import com.globallogic.codingdojo.domain.mappers.Transformable;
import com.globallogic.codingdojo.domain.model.Content;
import com.globallogic.codingdojo.domain.model.Item;
import com.globallogic.codingdojo.domain.model.RSS;
import com.globallogic.codingdojo.data.dto.ContentDTO;
import com.globallogic.codingdojo.data.dto.ItemDTO;
import com.globallogic.codingdojo.data.dto.RssDTO;

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
