package edu.wou.cs361.sorting;

/**
 * IAnalyzer interface. No work needed here.
 */
public interface IAnalyzer {
    /**
     * Analyze sorting data
     *
     * @param sizes An Array of values containing the sizes used in sorting
     * @param compares The corresponding Array containing the number of compares measured for each size
     * @return Returns an Analysis object containing the analysis data
     * @throws IllegalArgumentException if the argument is null
     */
    Analysis analyze(final int[] sizes, final long[] compares);

}
