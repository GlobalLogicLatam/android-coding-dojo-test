package com.globallogic.codingdojo.domain.model;

import java.io.Serializable;

/**
 * @author facundo.mengoni
 * @since 0.3
 */
public class Content implements Serializable {
    public String url;
    public String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}