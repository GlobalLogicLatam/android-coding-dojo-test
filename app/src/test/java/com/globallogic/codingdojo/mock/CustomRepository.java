package com.globallogic.codingdojo.mock;

import com.globallogic.codingdojo.data.dto.RssDTO;

import javax.inject.Inject;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author julio.kun
 * @since 0.1
 */
public class CustomRepository {
    private Callback<RssDTO> callback;
    private RSSMockFactory rssMockFactory;

    @Inject
    public CustomRepository(RSSMockFactory rssMockFactory) {
        this.rssMockFactory = rssMockFactory;
    }

    public void setCallback(Callback<RssDTO> callback) {
        this.callback = callback;
    }

    public void sendResponse() {
        Response<RssDTO> response = Response.success(rssMockFactory.getRssWithTwoItems());
        callback.onResponse(null, response);
    }

    public void sendResponseWithError() {
        callback.onFailure(null, new Exception("Custom error to test error behaviour"));
    }

    public void sendResponseWithEmptyList() {
        Response<RssDTO> response = Response.success(rssMockFactory.getRssWithNoData());
        callback.onResponse(null, response);
    }

    public void sendResponseFromFile() {
        Response<RssDTO> response = Response.success(rssMockFactory.getFullListOfRSS());
        callback.onResponse(null, response);
    }
}