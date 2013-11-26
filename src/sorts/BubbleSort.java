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
		int amountFinished = 0;
		while (!done)
		{
			done = pass(1, array.length - amountFinished);
			amountFinished += 1;
		} 
	}
	boolean pass(int gap, int end)
	{
		boolean done = true;
		for (int i = 0; i < end - gap; i++)
		{
			if (compare(i, i + gap) > 0)
			{
				swap(i, i + gap);
				done = false;
			}
		}
		return done;
	}
}
