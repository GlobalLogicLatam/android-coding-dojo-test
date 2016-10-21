package com.globallogic.codingdojo.data.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author facundo.mengoni
 * @since 0.3
 */
@Root(strict = false, name = "item")
public class ItemDTO {
    private static String LOG_TAG = ItemDTO.class.getSimpleName();

    @Element
    public String title;
    @Element
    public String link;
    @Element
    public String pubDate;
    @Element
    public String description;
    @Element(name = "content", required = false)
    @Namespace(reference = "http://search.yahoo.com/mrss/", prefix = "media")
    public ContentDTO content;
    @Element(name = "encoded", required = false)
    @Namespace(reference = "http://search.yahoo.com/mrss/", prefix = "content")
    public String fullContent;
    @Element(name = "creator", required = false)
    @Namespace(reference = "http://search.yahoo.com/mrss/", prefix = "dc")
    private String creator;

    public String getDescription() {
        String desc = this.description;
        desc = removeFooter(desc);
        desc = removeImage(desc);
        return desc;
    }

    private String removeFooter(String desc) {
        String descriptionWithoutFooter;
        String readHere = "Leer nota completa";
        if (desc.contains(readHere)) {
            descriptionWithoutFooter = desc.substring(0, desc.indexOf(readHere));
        } else {
            descriptionWithoutFooter = desc.substring(0, desc.indexOf("<p>La entrada"));
        }
        return descriptionWithoutFooter;
    }

    private String removeImage(String desc) {
        String descriptionWithoutImage = desc;
        String tagToRemove = "</div><div>";
        if (desc.contains(tagToRemove)) {
            descriptionWithoutImage = desc.substring(desc.indexOf(tagToRemove), desc.length());
            descriptionWithoutImage = "<div>" + descriptionWithoutImage;
        }
        return descriptionWithoutImage;
    }

    public String getPubDate() {
        if (pubDate == null)
            return "";
        SimpleDateFormat format = new SimpleDateFormat("ccc, dd LLLL yyyy HH:mm:ss Z", Locale.ENGLISH);
        Date date = new Date();
        try {
            date = format.parse(pubDate);
        } catch (ParseException e) {
            /*
            if (e != null && !TextUtils.isEmpty(e.getMessage()))
                Log.e(LOG_TAG, e.getMessage());
            */
        }
        SimpleDateFormat sdf = new SimpleDateFormat("ccc dd LLLL yyyy", Locale.getDefault());
        return sdf.format(date);
    }

    public String getCreator() {
        return creator.replace("\n", "").replace("                ", "");
    }
}