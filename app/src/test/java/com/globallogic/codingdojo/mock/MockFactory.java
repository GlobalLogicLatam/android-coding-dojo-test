package com.globallogic.codingdojo.mock;

import com.globallogic.codingdojo.domain.model.Content;
import com.globallogic.codingdojo.domain.model.Item;
import com.globallogic.codingdojo.domain.model.RSS;

/**
 * @author julio.kun
 * @since 0.1
 * <p>
 * <p>
 * </p>
 */
public class MockFactory {

    public RSS getRSSNormal() {
        RSS rss = new RSS();

        return rss;
    }

    public Item getTestItemWithAllDetails() {
        Item item = new Item();
        item.title = "Test title";
        item.description = "This is just a silly description to test if it's gonna work correctly";
        item.pubDate = "07/04/2016";
        item.content = getContentWithAllDetail();
        return item;
    }

    public Item getTestItemWithInvalidUrl() {
        Item item = new Item();
        item.title = "Test title";
        item.description = "This is just a silly description to test if it's gonna work correctly";
        item.pubDate = "07/04/2016";
        item.content = getContentWithInvalidUrl();
        return item;
    }

    public Content getContentWithAllDetail() {
        Content content = new Content();
        content.title = "Test content title";
        content.url = "https://d19n1ren9crl9v.cloudfront.net/wp-content/uploads/2015/04/Target-Marketing-Content-Marketing-Kenneth-Gillett.jpg";
        return content;
    }

    public Content getContentWithInvalidUrl() {
        Content content = new Content();
        content.title = "Test content title";
        content.url = "this is not a valid url";
        return content;
    }

}
