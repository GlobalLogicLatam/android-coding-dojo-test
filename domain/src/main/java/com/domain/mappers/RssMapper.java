package com.domain.mappers;

import com.domain.model.Item;
import com.domain.model.RSS;
import com.globallogic.data.dto.ItemDTO;
import com.globallogic.data.dto.RssDTO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author facundo.mengoni
 * @since 0.3
 */
public class RssMapper implements Transformable<RssDTO, RSS> {
    @Inject
    Transformable<ItemDTO, Item> itemMapper;

    @Inject
    public RssMapper() {
    }

    @Override
    public RSS transform(RssDTO object) {
        RSS rss = new RSS();
        if (object.getItems() != null) {
            rss.setItems(itemMapper.transform(object.getItems()));
        }
        return rss;
    }

    @Override
    public List<RSS> transform(List<RssDTO> objects) {
        ArrayList<RSS> list = new ArrayList<>();
        for (RssDTO object : objects) {
            list.add(transform(object));
        }
        return list;
    }
}