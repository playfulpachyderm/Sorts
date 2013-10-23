package sorts;

import gui.SortListener;


public class BubbleSort extends Sort
{
	public BubbleSort(int[] a) 
	{
		super(a);
	}

	public BubbleSort(int[] array, SortListener listener) 
	{
		super(array, listener);
	}

	public void sort()
	{
		boolean done = false;
		int amountFinished = 1;
		while (!done)
		{
			done = true;
			for (int i = 0; i < array.length - amountFinished; i++)
			{
				if (compare(i, i+1) > 0) 
				{
					swap(i, i+1);
					done = false;
				}
			}
			amountFinished += 1;
		} 
	}

}
