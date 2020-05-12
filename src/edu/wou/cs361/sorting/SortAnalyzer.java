package edu.wou.cs361.sorting;

import java.util.Random;

public class SortAnalyzer {

    private static final int KSIZE1 = 100;
    private static final int KSIZE2 = 200;
    private static final int KSIZE4 = 400;
    private static final int KSIZE8 = 800;
    private static final int KSIZE16 = 1600;

    private static final int[] KSIZES = {KSIZE1, KSIZE2, KSIZE4, KSIZE8, KSIZE16};

    public static final long[] insertionCompares = new long[1600];
    public static final long[] selectionCompares = new long[1600];
    public static final long[] mergeCompares = new long[1600];
    public static final long[] quickCompares = new long[1600];

    static Random random = new Random();

    static Comparable[] sortedArray = new Comparable[1600];
    static Comparable[] reverseArray = new Comparable[1600];
    static Comparable[] randomArray = new Comparable[1600];

    private static final InsertionSort insertionSort = new InsertionSort();
    private static final SelectionSort selectionSort = new SelectionSort();
    private static final MergeSort mergeSort = new MergeSort();
    private static final QuickSort quickSort = new QuickSort();


    public static void main(String[] args){

        var analyzer = new Analyzer();

        resetData();

        arrayOfComparesSorted();
        System.out.println("Insertion Sort with Sorted data " + analyzer.analyze(KSIZES, insertionCompares));
        System.out.println("Selection Sort with Sorted data " + analyzer.analyze(KSIZES, selectionCompares));
        System.out.println("Merge Sort with Sorted data " + analyzer.analyze(KSIZES, mergeCompares));
        System.out.println("Quick Sort with Sorted data " + analyzer.analyze(KSIZES, quickCompares));

        arrayOfComparesReverse();
        System.out.println("Insertion Sort with Reverse data " + analyzer.analyze(KSIZES, insertionCompares));
        System.out.println("Selection Sort with Reverse data " + analyzer.analyze(KSIZES, selectionCompares));
        System.out.println("Merge Sort with Reverse data " + analyzer.analyze(KSIZES, mergeCompares));
        System.out.println("Quick Sort with Reverse data " + analyzer.analyze(KSIZES, quickCompares));

        arrayOfComparesRandom();
        System.out.println("Insertion Sort with Random data " + analyzer.analyze(KSIZES, insertionCompares));
        System.out.println("Selection Sort with Random data " + analyzer.analyze(KSIZES, selectionCompares));
        System.out.println("Merge Sort with Random data " + analyzer.analyze(KSIZES, mergeCompares));
        System.out.println("Quick Sort with Random data " + analyzer.analyze(KSIZES, quickCompares));



    }

    public static void resetData(){
        int max = 999;
        int min = 1;

        for(var n = 0; n < 5; ++n)
            for(var i = 0; i < KSIZES[n]; ++i){
                sortedArray[i] = i + 1;
                reverseArray[i] = (KSIZES[n] - (i + 1));

                randomArray[i] = random.nextInt((max - min) + 1) + min;
        }
    }

    private static void arrayOfComparesSorted(){
        var counter = 0;

        for(var n = 0; n < 5; ++n)
            for(var i = 0; i < KSIZES[n]; ++i){
                insertionCompares[i] = insertionSort.sort(sortedArray);
                selectionCompares[i] = selectionSort.sort(sortedArray);
                mergeCompares[i] = mergeSort.sort(sortedArray);
                quickCompares[i] = quickSort.sort(sortedArray);
            }

        resetData();
    }
    private static void arrayOfComparesReverse(){

        for(var n = 0; n < 5; ++n){
            for(var i = 0; i < SortAnalyzer.KSIZES[n]; ++i){
                insertionCompares[i] = insertionSort.sort(reverseArray);
                selectionCompares[i] = selectionSort.sort(reverseArray);
                mergeCompares[i] = mergeSort.sort(reverseArray);
                quickCompares[i] = quickSort.sort(reverseArray);
            }

            resetData();
        }
    }
    private static void arrayOfComparesRandom(){

        for(var n = 0; n < 5; ++n){
            for(var i = 0; i < SortAnalyzer.KSIZES[n]; ++i){

                insertionCompares[i] = insertionSort.sort(randomArray);
                selectionCompares[i] = selectionSort.sort(randomArray);
                mergeCompares[i] = mergeSort.sort(randomArray);
                quickCompares[i] = quickSort.sort(randomArray);
            }

            resetData();
        }
    }
}

