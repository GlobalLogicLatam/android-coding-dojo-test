package com.domain.callback;

import com.domain.model.RSS;

/**
 * @author facundo.mengoni
 * @since 0.3.0
 */
public interface Callback {
    void onError(Throwable t);

    void onSuccess(RSS rss);

    void onFinish();
}
