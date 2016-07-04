package com.domain.mappers;

import com.domain.model.Content;
import com.globallogic.data.dto.ContentDTO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author facundo.mengoni
 * @since 0.3
 */
public class ContentMapper implements Transformable<ContentDTO, Content> {
    @Inject
    public ContentMapper() {
    }

    @Override
    public Content transform(ContentDTO object) {
        Content content = new Content();
        content.setTitle(object.title);
        content.setUrl(object.url);
        return content;
    }

    @Override
    public List<Content> transform(List<ContentDTO> objects) {
        ArrayList<Content> list = new ArrayList<>();
        for (ContentDTO object : objects) {
            list.add(transform(object));
        }
        return list;
    }
}