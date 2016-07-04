package com.domain.mappers;

import com.domain.model.Content;
import com.domain.model.Item;
import com.globallogic.data.dto.ContentDTO;
import com.globallogic.data.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author facundo.mengoni
 * @since 0.3
 */
public class ItemMapper implements Transformable<ItemDTO, Item> {
    @Inject
    Transformable<ContentDTO, Content> contentMapper;

    @Inject
    public ItemMapper() {
    }

    @Override
    public Item transform(ItemDTO object) {
        Item item = new Item();
        item.setTitle(object.title);
        if (object.content != null) {
            item.setContent(contentMapper.transform(object.content));
        }
        item.setCreator(object.getCreator());
        item.setDescription(object.getDescription());
        item.setFullContent(object.fullContent);
        item.setLink(object.link);
        item.setPubDate(object.pubDate);
        return item;
    }

    @Override
    public List<Item> transform(List<ItemDTO> objects) {
        ArrayList<Item> list = new ArrayList<>();
        for (ItemDTO object : objects) {
            list.add(transform(object));
        }
        return list;
    }
}