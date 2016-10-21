package com.globallogic.codingdojo.data.service;

import com.globallogic.codingdojo.data.dto.RssDTO;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by David on 3/23/16.
 */
public class ServiceFacade {

    private final static String API_URL = "http://private-a972b3-myfirstapp2.apiary-mock.com/";
    private final RssFeedService service;

    public ServiceFacade() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        service = retrofit.create(RssFeedService.class);
    }

    public void getFeeds(retrofit2.Callback<RssDTO> callback) {
        service.getFeeds().enqueue(callback);
    }
}