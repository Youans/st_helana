/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.utils;

//~--- JDK imports ------------------------------------------------------------
import java.io.Serializable;import com.shs.utils.database.Persistable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 *
 * @author Administrator
 */
public class CollectionMap<AnyType> implements Collection<AnyType>, Iterable<AnyType>, Iterator<AnyType>, Serializable {

    LinkedHashMap<AnyType, AnyType> internaltable;

    /**
     * Collection Map Class Constructor
     *
     * initialize internaltable as LinkedHashMap<AnyType,AnyType>()
     */
    public CollectionMap() {
        internaltable = new LinkedHashMap<>();
    }

    /**
     * Get the Size of the internaltable
     *
     * @return size int
     */
    @Override
    public int size() {
        return internaltable.size();
    }

    /**
     * Check if the internaltable is Empty
     *
     * @return empty boolean
     */
    @Override
    public boolean isEmpty() {
        return internaltable.isEmpty();
    }

    /**
     * Check if internaltable is contains a specific Object
     *
     * @param o Object
     *
     * @return exist boolean
     */
    @Override
    public boolean contains(Object o) {
        return internaltable.containsKey(o);
    }

    /**
     * Iterator Returns an iterator over the elements in this set.
     *
     * @return iterator Iterator<AnyType>
     */
    @Override
    public Iterator<AnyType> iterator() {
        return internaltable.keySet().iterator();
    }

    /**
     * toArray Returns an array of Object[] containing all of the elements in
     * this set.
     *
     * @return array Object[]
     */
    @Override
    public Object[] toArray() {
        return internaltable.keySet().toArray();
    }

    /**
     * toArray Returns an specified type of array containing all of the elements
     * in this set.
     *
     * @param a T[]
     *
     * @return array T[]
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return internaltable.keySet().toArray(a);
    }

    /**
     * Add an item of any type to internaltable after check if this item is not
     * exist already
     *
     * @param e AnyType
     *
     * @return added boolean
     */
    @Override
    public boolean add(AnyType e) {
        boolean toReturn = false;

        if (!internaltable.containsKey(e)) {
            internaltable.put(e, e);
            toReturn = true;
        }

        return toReturn;
    }

    /**
     * Remove an Object from internalTable
     *
     * @param o Object
     *
     * @return removed boolean
     */
    @Override
    public boolean remove(Object o) {
        return internaltable.remove(o) != null;
    }

    /**
     * Check if internaltable contains an Collection of specific type
     *
     * @param c Collection<?>
     *
     * @return contained boolean
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        boolean toReturn = true;

        for (Object object : c) {
            if (toReturn) {
                toReturn = toReturn && internaltable.containsKey(object);
            }
        }

        return toReturn;
    }

    /**
     * Add All is to Add a collection of specific type to internaltable
     *
     * @param c Collection<?>
     *
     * @return added boolean
     */
    @Override
    public boolean addAll(Collection<? extends AnyType> c) {
        boolean toReturn = false;

        if (!containsAll(c)) {
            for (AnyType anytype : c) {
                internaltable.put(anytype, anytype);
            }

            toReturn = true;
        }

        return toReturn;
    }

    /**
     * Remove All is to remove an collection of specific type from internaltable
     *
     * @param c Collection<?>
     *
     * @return removed boolean
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean toReturn = false;

        if (containsAll(c)) {
            toReturn = true;

            for (Object object : c) {
                toReturn = remove(object) && toReturn;
            }
        }

        return toReturn;
    }

    /**
     * Retain All is to remove all objects in internaltable but not in
     * collection passes as a parameter
     *
     * @param c Collection<?>
     *
     * @return retained boolean
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object object : internaltable.keySet()) {
            if (!c.contains(object)) {
                internaltable.remove(object);
            }
        }

        return true;
    }

    /**
     * Clear is to Removes all of the mappings from this map. The map will be
     * empty after this call returns.
     */
    @Override
    public void clear() {
        internaltable.clear();
    }

    /**
     * Get an AnyType item by Key of AnyType
     *
     * @param key AnyType
     *
     * @return item AnyType
     */
    public AnyType get(AnyType key) {
        return internaltable.get(key);
    }

    /**
     * Check if the iterator has a next item
     *
     * @return hasNext boolean
     */
    @Override
    public boolean hasNext() {
        return iterator().hasNext();
    }

    /**
     * Return a next item of the iterator
     *
     * @return nextItem AnyType
     */
    @Override
    public AnyType next() {
        return iterator().next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
