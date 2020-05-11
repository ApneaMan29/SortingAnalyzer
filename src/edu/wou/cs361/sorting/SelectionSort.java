package edu.wou.cs361.sorting;

public class SelectionSort implements ISort {
    long compareCount;

    long compareTo2(Comparable c, Comparable to){
        ++compareCount;

        return c.compareTo(to);
    }

    @Override
    public long sort(final Comparable[] array) {
        compareCount = 0L;

        if (array == null) throw new IllegalArgumentException();

        for (var i = 0; i < array.length-1; ++i) {
            var min = i;
            for (var j = i + 1; j < array.length; ++j)
                if (compareTo2(array[j], array[min]) < 0)
                    min = j;

            var tmp = array[min];
            array[min] = array[i];
            array[i] = tmp;
        }

        return compareCount;
    }

}
