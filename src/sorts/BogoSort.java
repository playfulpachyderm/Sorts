package sorts;

import gui.SortListener;

public class BogoSort extends Sort
{
	public BogoSort(int[] a) {
		super(a);
	}
	public BogoSort(int[] array, SortListener listener) 
	{
		super(array, listener);
	}

	public void sort()
	{
		while (!isSorted()) shuffle();
	}
}
