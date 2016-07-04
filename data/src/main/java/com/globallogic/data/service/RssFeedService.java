package com.globallogic.data.service;

import com.globallogic.data.dto.RssDTO;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by David on 3/23/16.
 */
public interface RssFeedService {
    @GET("/feed")
    Call<RssDTO> getFeeds();
}