package com.globallogic.codingdojo.data.service;

import com.globallogic.codingdojo.data.dto.RssDTO;
import com.globallogic.codingdojo.data.repository.FeedsRepository;

import javax.inject.Inject;

/**
 * @author julio.kun
 * @since 0.3
 */
public class FeedsRepositoryImplementation implements FeedsRepository {

    private final ServiceFacade service;

    @Inject
    public FeedsRepositoryImplementation() {
        service = new ServiceFacade();
    }

    @Override
    public void getFeeds(retrofit2.Callback<RssDTO> callback) {
        service.getFeeds(callback);
    }
}
