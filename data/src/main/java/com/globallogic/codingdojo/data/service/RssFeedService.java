package com.globallogic.codingdojo.data.service;

import com.globallogic.codingdojo.data.dto.RssDTO;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by David on 3/23/16.
 */
public interface RssFeedService {
    @GET("/feed")
    Call<RssDTO> getFeeds();
}