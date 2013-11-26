package sorts;

import gui.SortListener;

public class ShellSort extends InsertionSort
{
	public ShellSort(int[] a) 
	{
		super(a);
	}
	public ShellSort(int[] array, SortListener listener) 
	{
		super(array, listener);
	}
	
	@Override
	public void sort()
	{
		int gap = array.length / 3;
		for (; gap > 1; gap /= 3)
		{
			for (int i = gap; i >= 0; i--)
				gapSort(i, gap);
		}
		gapSort(0, 1);
	}
}
