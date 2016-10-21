package com.globallogic.codingdojo.domain.model;

import java.util.List;

/**
 * @author facundo.mengoni
 * @since 0.3
 */
public class RSS {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}