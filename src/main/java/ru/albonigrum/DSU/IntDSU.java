package ru.albonigrum.DSU;

import java.util.ArrayList;

/**
 * IntDsu faster than {@link DSU<Integer>} because doesn't have Map elemToIndex
 */
public class IntDSU {
    private final ArrayList<Integer> parents;
    private final ArrayList<Integer> sizes;

    public IntDSU() {
        parents = new ArrayList<>();
        sizes = new ArrayList<>();
    }

    public IntDSU(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count of DSU cannot be less 0");
        }
        parents = new ArrayList<>(count);
        sizes = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            makeSet();
        }
    }

    /**
     * Because index and value for IntDSU is same, you cannot create random value
     * (this is lead to excessive increase in the array)
     * @return the index of new elem
     */
    public int makeSet() {
        int index = parents.size();
        parents.add(index);
        sizes.add(1);
        return index;
    }

    /**
     * @return index of leader element
     */
    public int findSet(int elem) {
        if (elem < 0 || elem >= parents.size()) {
            throw new IllegalArgumentException("Illegal elem in IntDSU: " + elem);
        }
        if (parents.get(elem) == elem) {
            return elem;
        }
        return findSet(parents.get(elem));
    }

    public void unionSets(int elem1, int elem2) {
        int leader1, leader2;
        leader1 = findSet(elem1);
        leader2 = findSet(elem2);
        if (leader1 != leader2) {
            if (sizes.get(leader1) > sizes.get(leader2)) {
                int temp = leader1;
                leader1 = leader2;
                leader2 = temp;
            }

            parents.set(leader1, leader2);
            sizes.set(leader2, sizes.get(leader1) + sizes.get(leader2));
        }
    }

    public int size() {
        return parents.size();
    }

}
