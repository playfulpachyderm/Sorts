package sorts;

import gui.SortListener;

public class InsertionSort extends Sort
{
	public InsertionSort(int[] a) 
	{
		super(a);
	}
	public InsertionSort(int[] array, SortListener listener) 
	{
		super(array, listener);
	}

	@Override
	public void sort() 
	{
		for (int x = 1; x < array.length; x++)
		{
			int firstSmallerThan = -1;
			for (int i = x-1; i >= 0; i--)
			{
				if (compare(x, i) > 0)
				{
					firstSmallerThan = i;
					break;
				}
			}
			insert(x, firstSmallerThan + 1);
		}
	}
}
