package edu.wou.cs361.sorting;

/**
 * ISort interface. No work needed here.
 */
public interface ISort {

    /**
     * Sort the array into ascending order
     *
     * @param array An Array of Comparable items
     * @return Returns the number of compares done in the sort
     * @throws IllegalArgumentException if the argument is null
     */
    long sort(final Comparable[] array);

}
