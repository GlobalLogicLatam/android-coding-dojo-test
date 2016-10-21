package com.globallogic.codingdojo.data.repository;

import com.globallogic.codingdojo.data.dto.RssDTO;

/**
 * @author julio.kun
 * @since 0.3
 */
public interface FeedsRepository {
    void getFeeds(retrofit2.Callback<RssDTO> callback);
}