package com.domain.utils;

/**
 * Interface to check that a particular object meets a requirement.
 *
 * @author julio.kun
 * @since 0.1
 */
public interface Checker<T> {
    boolean check(T obj);
}
