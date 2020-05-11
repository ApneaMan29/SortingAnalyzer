
/**
     * Simple insertion sort.
     * @param a an array of Comparable items.
     */
    public static void insertionSort(Comparable [] a) {
        for (var p = 1; p < a.length; ++p) {
            var tmp = a[p];
            var j = p;

            for ( ; j > 0 && tmp.compareTo( a[j - 1] ) < 0; --j )
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }
