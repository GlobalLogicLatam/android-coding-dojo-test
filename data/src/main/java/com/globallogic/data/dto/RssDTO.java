package com.globallogic.data.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @author facundo.mengoni
 * @since 0.3
 */
@Root(strict = false, name = "rss")
public class RssDTO {
    @Element
    private ChannelDTO channel;

    public List<ItemDTO> getItems() {
        return channel.getItems();
    }
}