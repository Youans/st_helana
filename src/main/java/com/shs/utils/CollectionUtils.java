/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Nabil
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * cast a source to list<AnyType>
     *
     * @param source List<AnyType>
     *
     * @return source List<AnyType>
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> cast(List<?> source) {
        return (List<T>) source;
    }

    /**
     * cast a source to Collection<AnyType>
     *
     * @param source Collection<AnyType>
     *
     * @return source Collection<AnyType>
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> cast(Collection<?> source) {
        return (Collection<T>) source;
    }

    /**
     * cast a source to ArrayList<AnyType>
     *
     * @param source ArrayList<AnyType>
     *
     * @return source ArrayList<AnyType>
     */
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> cast(ArrayList<?> source) {
        return (ArrayList<T>) source;
    }

    /**
     * cast a source to Set<AnyType>
     *
     * @param source Set<AnyType>
     *
     * @return source Set<AnyType>
     */
    @SuppressWarnings("unchecked")
    public static <T> Set<T> cast(Set<?> source) {
        return (Set<T>) source;
    }

    /**
     * cast a source to HashSet<AnyType>
     *
     * @param source HashSet<AnyType>
     *
     * @return source HashSet<AnyType>
     */
    @SuppressWarnings("unchecked")
    public static <T> HashSet<T> cast(HashSet<?> source) {
        return (HashSet<T>) source;
    }

    /**
     * cast a source to CollectionMap<AnyType>
     *
     * @param source CollectionMap<AnyType>
     *
     * @return source CollectionMap<AnyType>
     */
    @SuppressWarnings("unchecked")
    public static <T> CollectionMap<T> cast(CollectionMap<?> source) {
        return (CollectionMap<T>) source;
    }

    /**
     * Shuffle items in list and put the new in list2 in normal way or reversed
     * way as pass by boolean parameter normalShuffle
     *
     * @param list List<AnyType>
     * @param list2 List<AnyType>
     * @param normalShuffle boolean
     */
    public static <T> void shuffle(List<T> list, List<T> list2, boolean normalShuffle) {
        if (!list2.isEmpty()) {
            if (list.isEmpty()) {
                list.addAll(list2);
            } else if (list.size() >= list2.size()) {
                shuffleNormal(list, list2, normalShuffle);
            } else {
                shuffleReversed(list, list2, normalShuffle);
            }
        }
    }

    public static <T> List<T> filterGUIList(List<T> list) {
        List<T> toReturn = list;

        if ((list != null) && (list.contains("-1") || list.contains(-1))) {
            toReturn = new ArrayList<>();
        }

        return toReturn;
    }

    private static <T> void shuffleNormal(List<T> list, List<T> list2, boolean normalShuffle) {
        int step = (normalShuffle)
                ? 1
                : list.size() / ((list2.size() < 2)
                ? 2
                : list2.size());
        int count = 1;
        int list2Iterator = 0;
        int list2index;

        for (int listIterator = step; listIterator < list.size(); listIterator += step) {
            for (int i = 0; i < count; i++) {
                list2index = (list2Iterator + i);

                if (list2index < list2.size()) {
                    list.add(listIterator + list2index, list2.get(list2index));
                }
            }

            list2Iterator += count;
        }
    }

    private static <T> void shuffleReversed(List<T> list, List<T> list2, boolean normalDistribution) {
        int originalListSize = list.size();
        int count = normalDistribution
                ? 1
                : list2.size() / list.size();
        int list2itterator = 0;

        for (int i = 0; i < list.size(); i += (count + 1)) {
            for (int j = 0; j < count; j++) {
                int insertionPos = i + j;

                if ((insertionPos >= list.size()) || (insertionPos >= list2.size())) {
                    break;
                }

                list.add(insertionPos, list2.get(list2itterator++));
            }
        }

        if (list2.size() - originalListSize > 1) {
            list.addAll(list2.subList(list2itterator, list2.size()));
        }
    }

    /**
     * Loaf Index From List by taking a List and a target Object
     *
     * @param list List
     * @param target Object
     *
     * @return index int
     */
    public static int loadIndexFromList(List list, Object target) {
        int count = 0;
        try {
            for (Object object : list) {
                if (object.equals(target)) {
                    return count;
                }
                count++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }
}
