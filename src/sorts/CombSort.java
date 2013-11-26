package sorts;

import gui.SortListener;

public class CombSort extends BubbleSort
{
	public CombSort(int[] a) 
	{
		super(a);
	}

	public CombSort(int[] array, SortListener listener) 
	{
		super(array, listener);
	}
	public void sort()
	{
		boolean done = false;
		double shrink = 1 + 1/Math.PI;

		int gap = (int) (array.length / shrink);
		for (; gap > 2; gap /= shrink)
			pass(gap, array.length);
		
		//gap = 1;
		//super.sort();

		InsertionSort s = new InsertionSort(array, listener);
		s.sort();
	}
}
