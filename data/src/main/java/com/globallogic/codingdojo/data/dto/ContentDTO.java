package com.globallogic.codingdojo.data.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author facundo.mengoni
 * @since 0.3
 */
@Root(name = "content", strict = false)
public class ContentDTO {
    @Attribute(name = "url")
    public String url;
    @Element(required = false)
    public String title;
}