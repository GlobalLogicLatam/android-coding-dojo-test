package com.globallogic.data.repository;

import com.globallogic.data.dto.RssDTO;

/**
 * @author julio.kun
 * @since 0.3
 */
public interface FeedsRepository {
    void getFeeds(retrofit2.Callback<RssDTO> callback);
}