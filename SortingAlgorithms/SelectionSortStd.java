
/**
     * selection sort.
     * @param a an array of Comparable items.
     */
    public static void selectionSort(Comparable [] a) {
		for (var i = 0; i < a.length-1; ++i) {
			var min = i;
			for (var j = i + 1; j < a.length; ++j)
			if (a[j].compareTo(a[min]) < 0)
				min = j;

			var tmp = a[min];
			a[min] = a[i];
			a[i] = tmp;
		}
    }