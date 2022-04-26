package ru.albonigrum.DSU;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DSU<T> {
    private final Map<T, Integer> elemToIndex;
    private final ArrayList<Integer> parents;
    private final ArrayList<Integer> sizes;

    public DSU() {
        elemToIndex = new HashMap<>();
        parents = new ArrayList<>();
        sizes = new ArrayList<>();
    }

    public DSU(Collection<T> collection) {
        elemToIndex = new HashMap<>(collection.size());
        parents = new ArrayList<>(collection.size());
        sizes = new ArrayList<>(collection.size());
        for (T elem : collection) {
            makeSet(elem);
        }
    }

    public DSU(T[] array) {
        elemToIndex = new HashMap<>(array.length);
        parents = new ArrayList<>(array.length);
        sizes = new ArrayList<>(array.length);
        for (T elem : array) {
            makeSet(elem);
        }
    }

    public void makeSet(T elem) {
        if (!elemToIndex.containsKey(elem)) {
            int index = parents.size();
            elemToIndex.put(elem, index);
            parents.add(index);
            sizes.add(1);
        }
    }

    /**
     * In the usual case, this function is not needed.
     * But it can help in case repeated calls of {@link DSU#findSet(T elem)}
     * @return index of leader element
     */
    public int findSet(int indexElem) {
        if (parents.get(indexElem) == indexElem) {
            return indexElem;
        }
        return findSet(parents.get(indexElem));
    }

    /**
     * findSet(a) == findSet(b) then and only then when a and b in one union.
     * @return index of leader element
     */
    public int findSet(T elem) {
        return findSet(elemToIndex.get(elem));
    }

    public void unionSets(T elem1, T elem2) {
        int index1, index2;
        index1 = findSet(elem1);
        index2 = findSet(elem2);
        if (index1 != index2) {
            if (sizes.get(index1) > sizes.get(index2)) {
                int temp = index1;
                index1 = index2;
                index2 = temp;
            }

            parents.set(index1, index2);
            sizes.set(index2, sizes.get(index1) + sizes.get(index2));
        }
    }

    public int size() {
        return parents.size();
    }
}


