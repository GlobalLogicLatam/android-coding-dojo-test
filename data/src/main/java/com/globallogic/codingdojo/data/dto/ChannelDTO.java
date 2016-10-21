package com.globallogic.codingdojo.data.dto;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @author facundo.mengoni
 * @since 0.3
 */
@Root(strict = false, name = "channel")
public class ChannelDTO {
    @ElementList(inline = true, required = false)
    private List<ItemDTO> items;

    public List<ItemDTO> getItems() {
        return items;
    }
}