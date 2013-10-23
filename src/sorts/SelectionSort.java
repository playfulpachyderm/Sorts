package sorts;

import gui.SortListener;

public class SelectionSort extends Sort
{

	public SelectionSort(int[] a) 
	{
		super(a);
	}
	
	public SelectionSort(int[] array, SortListener listener) 
	{
		super(array, listener);
	}

	@Override
	public void sort() 
	{
		for (int z = 0; z < array.length; z++)
		{
			int smallest = z;
			for (int i = z+1; i < array.length; i++)
			{
				if (compare(smallest, i) > 0) smallest = i;
			}
			swap(smallest, z);
		}
	}
}
