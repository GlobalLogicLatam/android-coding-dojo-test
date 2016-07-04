package com.domain.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Util that filter a given collection with the given {@link Checker}
 *
 * @author julio.kun
 * @since 0.1
 */
public class CheckerUtil {

    /**
     * Find all the items that meet the condition given by the {@link Checker}
     *
     * @param coll
     * @param chk
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> findAll(Collection<T> coll, Checker<T> chk) {
        ArrayList<T> l = new ArrayList<>();
        for (T obj : coll) {
            if (chk.check(obj))
                l.add(obj);
        }
        return l;
    }

    /**
     * Remove all the items from the given {@link List} that meet the
     * condition given by chk.
     *
     * @param coll
     * @param chk
     * @param <T>
     */
    public static <T> void filterAll(List<T> coll, Checker<T> chk) {
        Iterator<T> iterator = coll.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (chk.check(item)) {
                iterator.remove();
            }
        }
    }
}