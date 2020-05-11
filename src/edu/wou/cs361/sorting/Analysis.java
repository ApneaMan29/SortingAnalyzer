package edu.wou.cs361.sorting;

/**
 *  Object container to hold the analysis metric benchmarks
 *  from the IAnalyzer implementations. No work needed here.
 */

public class Analysis {
    private double[] ratios;
    private double error;
    private String bigO;

    public Analysis(double[] ratios, double error, String bigO) {
        this.ratios = ratios;
        this.error = error;
        this.bigO = bigO;
    }

    public double[] getRatios() {
        return ratios;
    }

    public void setRatios(double[] ratios) {
        this.ratios = ratios;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public String getBigO() {
        return bigO;
    }

    public void setBigO(String bigO) {
        this.bigO = bigO;
    }
}
