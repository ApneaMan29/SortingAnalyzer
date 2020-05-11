package edu.wou.cs361.sorting;

public class InsertionSort implements ISort {
    long compareCount;

    long compareTo2(Comparable c, Comparable to){
        ++compareCount;

        return c.compareTo(to);
    }

    @Override
    public long sort(final Comparable[] array) {
        compareCount = 0L;

        if (array == null) throw new IllegalArgumentException();

        for (var p = 1; p < array.length; ++p) {
            var tmp = array[p];
            var j = p;

            for ( ; j > 0 && compareTo2(tmp, array[j - 1] ) < 0; --j )
                array[j] = array[j - 1];
            array[j] = tmp;
        }
        return compareCount;
    }
}

//I want to create a compareTo() function that increments a counter for every compare