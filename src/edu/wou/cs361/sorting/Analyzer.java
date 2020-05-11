package edu.wou.cs361.sorting;

import java.util.LinkedList;

import static java.lang.StrictMath.*;

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

        var analysis = new Analysis(null, 0, "");

        var ratiosOf1 = new double[sizes.length];
        var ratiosOfLogN = new double[sizes.length];
        var ratiosOfN = new double[sizes.length];
        var ratiosOfNLogN = new double[sizes.length];
        var ratiosOfN2 = new double[sizes.length];
        var ratiosOfN3 = new double[sizes.length];
        var ratiosOf2N = new double[sizes.length];

        var meanOf1 = 0.0;
        var meanOfLogN = 0.0;
        var meanOfN = 0.0;
        var meanOfNLogN = 0.0;
        var meanOfN2 = 0.0;
        var meanOfN3 = 0.0;
        var meanOf2N = 0.0;

        var errorOf1 = 100.0;
        var errorOfLogN = 100.0;
        var errorOfN = 100.0;
        var errorOfNLogN = 100.0;
        var errorOfN2 = 100.0;
        var errorOfN3 = 100.0;
        var errorOf2N = 100.0;

        for(var i = 0; i < sizes.length; ++i) {
            var n = sizes[i];
            double cOfn = compares[i];

            ratiosOf1[i] = cOfn;
            ratiosOfLogN[i] = cOfn / (log(n));
            ratiosOfN[i] = cOfn / (n);
            ratiosOfNLogN[i] = cOfn / (n * log(n));
            ratiosOfN2[i] = cOfn / pow(n, 2);
            ratiosOfN3[i] = cOfn / pow(n, 3);
            ratiosOf2N[i] = cOfn / pow(2, n);
        }

        LinkedList<double[]> ratios = new LinkedList<>();

        ratios.add(ratiosOf1);
        ratios.addLast(ratiosOfLogN);
        ratios.addLast(ratiosOfN);
        ratios.addLast(ratiosOfNLogN);
        ratios.addLast(ratiosOfN2);
        ratios.addLast(ratiosOfN3);
        ratios.addLast(ratiosOf2N);

        for(var i = 0; i < sizes.length; ++i){
            meanOf1 += ratiosOf1[i];
            meanOfLogN += ratiosOfLogN[i];
            meanOfN += ratiosOfN[i];
            meanOfNLogN += ratiosOfNLogN[i];
            meanOfN2 += ratiosOfN2[i];
            meanOfN3 += ratiosOfN3[i];
            meanOf2N += ratiosOf2N[i];
        }

        meanOf1 = meanOf1 /  sizes.length;
        meanOfLogN = meanOfLogN /  sizes.length;
        meanOfN = meanOfN /  sizes.length;
        meanOfNLogN = meanOfNLogN /  sizes.length;
        meanOfN2 = meanOfN2 /  sizes.length;
        meanOfN3 = meanOfN3 /  sizes.length;
        meanOf2N = meanOf2N /  sizes.length;

        for(var i = 0; i < sizes.length; ++i){
            var temp1 = (abs(ratiosOf1[i] - meanOf1) / meanOf1);
            var temp2 = abs(ratiosOfLogN[i] - meanOfLogN) / meanOfLogN;
            var temp3 = abs(ratiosOfN[i] - meanOfN) / meanOfN;
            var temp4 = abs(ratiosOfNLogN[i] - meanOfNLogN)  / meanOfNLogN;
            var temp5 = abs(ratiosOfN2[i] - meanOfN2) / meanOfN2;
            var temp6 = abs(ratiosOfN3[i] - meanOfN3) / meanOfN3;
            var temp7 = abs(ratiosOf2N[i] - meanOf2N) / meanOf2N;

            if(errorOf1 >= temp1)
                errorOf1 = temp1;
            if(errorOfLogN >= temp2)
                errorOfLogN = temp2;
            if(errorOfN >= temp3)
                errorOfN = temp3;
            if(errorOfNLogN >= temp4)
                errorOfNLogN = temp4;
            if(errorOfN2 >= temp5)
                errorOfN2 = temp5;
            if(errorOfN3 >= temp6)
                errorOfN3 = temp6;
            if(errorOf2N >= temp7)
                errorOf2N = temp7;
        }

        double[] errors = {errorOf1, errorOfLogN, errorOfN, errorOfNLogN, errorOfN2, errorOfN3, errorOf2N};

        if(correctBigO(ratios, errors) == ratios.get(0))
            analysis.setBigO("O(1)");
        if(correctBigO(ratios, errors) == ratios.get(1))
            analysis.setBigO("O(log N)");
        if(correctBigO(ratios, errors) == ratios.get(2))
            analysis.setBigO("O(N)");
        if(correctBigO(ratios, errors) == ratios.get(3))
            analysis.setBigO("O(N log N)");
        if(correctBigO(ratios, errors) == ratios.get(4))
            analysis.setBigO("O(N^2)");
        if(correctBigO(ratios, errors) == ratios.get(5))
            analysis.setBigO("O(N^3)");
        if(correctBigO(ratios, errors) == ratios.get(6))
            analysis.setBigO("O(2^N)");

        analysis.setError(findMinError(errors));
        analysis.setRatios(correctBigO(ratios, errors));

        return analysis;
    }

    double findMinError(double[] errors){
        var temp = 13000.0;

        for (double error : errors) {
            if (temp > error) {
                temp = error;
            }
        }

        return temp;
    }

    int errorIndex(double[] errors){
        var temp = 13000.0;
        var index = 0;

        for(var i = 0; i < errors.length; ++i){
            if(temp >= errors[i])
            {
                temp = errors[i];
                index = i;
            }
        }
        return index;
    }

    double[] correctBigO(LinkedList<double[]> ratios, double[] errors){

        return ratios.get(errorIndex(errors));
    }

}
