package com.globallogic.codingdojo.mock;

import android.content.Context;
import android.support.annotation.NonNull;

import com.globallogic.data.dto.ContentDTO;
import com.globallogic.data.dto.ItemDTO;
import com.globallogic.data.dto.RssDTO;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author julio.kun
 * @since 0.1
 */
public class RSSMockFactory {

    private Context context;

    @Inject
    public RSSMockFactory() {
    }

    private static String resolveBasePath() {
        String moduleName = "app";
        final String path = "./" + moduleName + "/src/test/resources/";
        if (Arrays.asList(new File("./").list()).contains(moduleName)) {
            return path; // version for call unit tests from Android Studio
        }
        return "../" + path; // version for call unit tests from terminal './gradlew test'
    }

    /* Reads file content and returns string.
            * @throws IOException
    */
    public static String readFile(@NonNull final String path) throws IOException {
        final StringBuilder sb = new StringBuilder();
        String strLine;
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            while ((strLine = reader.readLine()) != null) {
                sb.append(strLine);
            }
        } catch (final IOException ignore) {
            //ignore
        }
        return sb.toString();
    }

    public RssDTO getRssWithTwoItems() {
        RssDTO rss = mock(RssDTO.class);
        ArrayList<ItemDTO> items = getMockItems();
        when(rss.getItems()).thenReturn(items);
        return rss;
    }

    public RssDTO getRssWithNoData() {
        RssDTO rssWIthNoData = mock(RssDTO.class);
        when(rssWIthNoData.getItems()).thenReturn(new ArrayList<ItemDTO>());
        return rssWIthNoData;
    }

    private ArrayList<ItemDTO> getMockItems() {
        ArrayList<ItemDTO> items = new ArrayList<>();

        //creates the first item
        ContentDTO firstContent = getContentForTheFirstItem();
        ItemDTO fItem = mock(ItemDTO.class);
        when(fItem.getDescription()).thenReturn("This is a mock description for the first item in the list");
        when(fItem.getPubDate()).thenReturn("Thu 07 April 2016");
        fItem.content = firstContent;
        fItem.title = "First feed";
        items.add(fItem);

        ContentDTO secondContent = getContentForTheSecondItem();
        ItemDTO sItem = mock(ItemDTO.class);
        when(sItem.getDescription()).thenReturn("This is a mock description for the second item.");
        when(sItem.getPubDate()).thenReturn("Thu 07 April 2016");
        sItem.title = "Second Feed";
        sItem.content = secondContent;
        items.add(sItem);

        return items;
    }

    private ContentDTO getContentForTheFirstItem() {
        ContentDTO content = mock(ContentDTO.class);
        content.title = "First feed";
        content.url = "http://conde-makoto.deviantart.com/";
        return content;
    }

    private ContentDTO getContentForTheSecondItem() {
        ContentDTO content = mock(ContentDTO.class);
        content.title = "Second feed";
        content.url = "http://www.google.com";
        return content;
    }

    public RssDTO getFullListOfRSS() {
        try {
            String xmlString = readFile(resolveBasePath() + "feedsfulltotest.xml");
            Serializer serializer = new Persister();
            return serializer.read(RssDTO.class, xmlString);
        } catch (Exception ex) {
            return null;
        }
    }
}
