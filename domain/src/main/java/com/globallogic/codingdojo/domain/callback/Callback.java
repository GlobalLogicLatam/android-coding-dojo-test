package com.globallogic.codingdojo.domain.callback;

import com.globallogic.codingdojo.domain.model.RSS;

/**
 * @author facundo.mengoni
 * @since 0.3.0
 */
public interface Callback {
    void onError(Throwable t);

    void onSuccess(RSS rss);

    void onFinish();
}
