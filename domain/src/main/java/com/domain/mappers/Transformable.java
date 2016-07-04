package com.domain.mappers;

import java.util.List;

/**
 * @author facundo.mengoni
 * @since 0.3
 */
public interface Transformable<T, U> {
    U transform(T object);

    List<U> transform(List<T> objects);
}