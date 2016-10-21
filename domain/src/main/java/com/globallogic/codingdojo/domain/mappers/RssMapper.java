package com.globallogic.codingdojo.domain.mappers;

import com.globallogic.codingdojo.domain.model.Item;
import com.globallogic.codingdojo.domain.model.RSS;
import com.globallogic.codingdojo.data.dto.ItemDTO;
import com.globallogic.codingdojo.data.dto.RssDTO;

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