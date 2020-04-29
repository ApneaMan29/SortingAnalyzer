package edu.wou.cs361.sorting;

public class TestDriver {

    private static final long[][] randomCompares = {
            {2833, 10392, 39881, 157673, 643482},
            {4950, 19900, 79800, 319600, 1279200},
            {542, 1294, 2960, 6713, 15027},
            {672, 1472, 3322, 7629, 17655},
    };

    private static final int[][] tests = {
            {10, 20, 30, 40, 50, 60, 70, 80},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {128, 256, 512, 1024, 2048, 4096, 8192},
            {100, 200, 300, 400, 500, 600},
            {2, 4, 8, 16},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {10, 20, 30, 40, 50, 60, 70, 80}
    };

    private static final long[][] values = {
            {100, 200, 300, 400, 500, 600, 700, 800},
            {1, 8, 27, 64, 125, 216, 343, 512, 719},
            {7, 8, 9, 10, 11, 12, 13},
            {100, 100, 100, 100, 100, 100},
            {2, 8, 24, 64},
            {2, 4, 8, 16, 32, 64, 128, 256, 512},
            {110, 420, 930, 1640, 2550, 3660, 4970, 6480}
    };

    private static final String[] expected = {
            "O(N)",
            "O(N^3)",
            "O(log N)",
            "O(1)",
            "O(N log N)",
            "O(2^N)",
            "O(N^2)"
    };

    private static final int INSERTION = 0;
    private static final int SELECTION = 1;
    private static final int MERGE = 2;
    private static final int QUICK = 3;
    private static final int SORTED = 0;
    private static final int REVERSE = 1;
    private static final int RANDOM = 2;

    private static final int[] sizes = {
            100, 200, 400, 800, 1600
    };

    private static final String[] types = {
            "sorted",
            "reverse",
            "random"
    };

    private static final long[][] sortedCompares = {
            {99, 199, 399, 799, 1599},
            {4950, 19900, 79800, 319600, 1279200},
            {356, 812, 1824, 4048, 8896},
            {488, 1173, 2742, 6279, 14152},
    };

    private static final long[][] reverseCompares = {
            {4950, 19900, 79800, 319600, 1279200},
            {4950, 19900, 79800, 319600, 1279200},
            {316, 732, 1664, 3728, 8256},
            {638, 1543, 3748, 8964, 20968},
    };

    protected static final long[][][] compares = {
            sortedCompares,
            reverseCompares,
            randomCompares
    };

    private static ISort[] sorts = {
            new InsertionSort(),
            new SelectionSort(),
            new MergeSort(),
            new QuickSort()
    };

    private static String[] names = {
            "insertion",
            "selection",
            "merge",
            "quick"
    };

    private static Comparable[][][] makeData(String[] types, int[] sizes) {
        var data = new Comparable[types.length][][];
        var random = new java.util.Random(0);
        for (var type = 0; type < types.length; ++type) {
            data[type] = new Comparable[sizes.length][];
            for (var size = 0; size < sizes.length; ++size) {
                data[type][size] = new Integer[sizes[size]];
                for (var i = 0; i < sizes[size]; ++i)
                    switch (type) {
                        case SORTED:
                            data[type][size][i] = i;
                            break;
                        case REVERSE:
                            data[type][size][i] = sizes[size] - i;
                            break;
                        case RANDOM:
                            data[type][size][i] =
                                    random.nextInt(sizes[size]);
                            break;
                        default:
                            break;
                    }
            }
        }
        return data;
    }

    private static void checkData(long[] expected, long[] actual) {
        for (var i = 0; i < expected.length; ++i) {
            if (expected[i] != actual[i]) {
                System.out.println("At size: " + sizes[i]);
                System.out.println("expected: " + expected[i]);
                System.out.println("actual: " + actual[i]);
                failed();
            }
        }
    }

    private static void checkSort(Comparable[][] arrays) {
        for (var i = 0; i < arrays.length; ++i) {
            for (var j = 0; j < arrays[i].length - 1; ++j)
                if (arrays[i][j].compareTo(arrays[i][j + 1]) > 0) {
                    System.out.println("array not sorted array[" + j + "]=" +
                            arrays[i][j] + " array[" + (j + 1) +
                            "]=" + arrays[i][j + 1]);
                    failed();
                }
        }
    }

    private static void testSorts() {
        for (var sort = 0; sort < sorts.length; sort++) {
            var data = makeData(types, sizes);
            for (var type = 0; type < types.length; ++type) {
                System.out.println("TESTING: " + names[sort] + " sort with " +
                        types[type] + " data");
                var compares = new long[sizes.length];
                for (var size = 0; size < sizes.length; ++size) {
                    compares[size] = sorts[sort].sort(data[type][size]);
                }
                checkSort(data[type]);
                System.out.println("checking compares");
                checkData(TestDriver.compares[type][sort], compares);
                System.out.println("\t\tPASSED");
            }
            System.out.println();
        }
    }

    private static void testAnalyzer() {
        System.out.println("Testing Analyzer");
        var analyzer = new Analyzer();
        for (var test = 0; test < tests.length; ++test) {
            var analysis = analyzer.analyze(tests[test], values[test]);
            if (!expected[test].equals(analysis.getBigO())) {
                System.out.println("expected: " + expected[test]);
                System.out.println("actual: " + analysis.getBigO());
                failed();
            }
        }
        System.out.println("\t\tPASSED");
    }

    private static void failed() {
        System.out.println("\t\tFAILED");
        System.exit(0);
    }

    public static void main(String[] args) {
        testSorts();
        //testAnalyzer();   // uncomment when you're ready to test the Analyzer
    }
}
