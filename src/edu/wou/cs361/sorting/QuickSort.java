package edu.wou.cs361.sorting;

public class QuickSort implements ISort {
    static long compareCount;

    static long compareTo2(Comparable c, Comparable to){
        ++compareCount;

        return c.compareTo(to);
    }

    @Override
    public long sort(final Comparable[] array) {
        compareCount = 0L;
        if (array == null) throw new IllegalArgumentException();

        quicksort(array, 0, array.length - 1);

        return compareCount;
    }

    private static final int CUTOFF = 10;

    public static final void swapReferences(Object [] array, int index1, int index2) {
        var tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    private static void quicksort(Comparable [] array, int low, int high) {
        if ( low + CUTOFF > high )
            insertionSort(array, low, high );
        else{
            // Sort low, middle, high
            int middle = (low + high) / 2;
            if (compareTo2(array[middle], array[low] ) < 0)
                swapReferences(array, low, middle);
            if (compareTo2(array[high], array[low]) < 0 )
                swapReferences(array, low, high);
            if (compareTo2(array[high], array[middle]) < 0)
                swapReferences(array, middle, high);

            // Place pivot at position high - 1
            swapReferences(array, middle, high - 1);
            var pivot = array[high - 1];

            // Begin partitioning
            int i;
            int j;
            for (i = low, j = high - 1; ;) {
                do {
                    ++i;
                } while (compareTo2(array[i], pivot) < 0);

                do {
                    --j;
                } while (compareTo2(pivot, array[j]) < 0);

                if (i >= j)
                    break;

                swapReferences(array, i, j);
            }

            // Restore pivot
            swapReferences(array, i, high - 1 );

            quicksort(array, low, i - 1 );    // Sort small elements
            quicksort(array, i + 1, high );   // Sort large elements
        }
    }

    private static void insertionSort(Comparable [ ] array, int low, int high ) {
        for (var p = low + 1; p <= high; ++p) {
            var tmp = array[p];
            var j = p;

            for(; j > low && compareTo2(tmp, array[j - 1]) < 0; --j)
                array[j] = array[j - 1];
            array[j] = tmp;
        }
    }

}
