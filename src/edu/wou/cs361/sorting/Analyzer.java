package edu.wou.cs361.sorting;

public class Analyzer implements IAnalyzer {
    /**
     * Analyze sorting data
     *
     * @param sizes An Array of values containing the sizes used in sorting
     * @param compares The corresponding Array containing the number of compares measured for each size
     * @return Returns an Analysis object containing the analysis data
     * @throws IllegalArgumentException if the argument is null
     */
    @Override
    public Analysis analyze(int[] sizes, long[] compares) {
        if (sizes.length != compares.length) throw new IllegalArgumentException();

        // your analysis code here

        return null;
    }

}
