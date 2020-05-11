package edu.wou.cs361.sorting;

public class MergeSort implements ISort {

    static long compareCount;

    static long compareTo2(Comparable c, Comparable to){
        ++compareCount;

        return c.compareTo(to);
    }


    @Override
    public long sort(final Comparable[] array) {
        compareCount = 0L;

        if (array == null) throw new IllegalArgumentException();

            var tmpArray = new Comparable[array.length];
            mergeSort(array, tmpArray, 0, array.length - 1);

            return compareCount;
        }

        private static void mergeSort(Comparable [] a, Comparable [] tmpArray,
        int left, int right) {
            if (left < right) {
                int center = ( left + right ) / 2;
                mergeSort(a, tmpArray, left, center);
                mergeSort(a, tmpArray, center + 1, right);
                merge(a, tmpArray, left, center + 1, right);
            }
        }

        private static void merge(Comparable [] a, Comparable [ ] tmpArray,
        int leftPos, int rightPos, int rightEnd) {


            var leftEnd = rightPos - 1;
            var tmpPos = leftPos;
            var numElements = rightEnd - leftPos + 1;

            // Main loop
            while (leftPos <= leftEnd && rightPos <= rightEnd)
                if (compareTo2(a[leftPos], a[rightPos]) <= 0)
                    tmpArray[tmpPos++] = a[leftPos++];
                else
                    tmpArray[tmpPos++] = a[rightPos++];

            while (leftPos <= leftEnd)    // Copy rest of first half
                tmpArray[tmpPos++] = a[leftPos++];

            while (rightPos <= rightEnd)  // Copy rest of right half
                tmpArray[tmpPos++] = a[rightPos++];

            // Copy tmpArray back
            for (var i = 0; i < numElements; i++, --rightEnd)
                a[rightEnd] = tmpArray[rightEnd];
        }
    }

