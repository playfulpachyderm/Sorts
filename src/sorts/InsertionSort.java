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
		gapSort(0, 1);
	}
	
	void gapSort(int begin, int gap)
	{
		for (int x = begin + gap; x < array.length; x += gap)
		{
			int firstSmallerThan = begin-gap; // none found yet;
			for (int i = x - gap; i >= 0; i -= gap)
			{
				if (compare(x, i) > 0)
				{
					firstSmallerThan = i;
					break;
				}
			}
			int wrong = (x-(firstSmallerThan+gap))%gap;
			if (wrong != 0) throw new RuntimeException(String.valueOf(wrong));
			
			insert(x, firstSmallerThan + gap, gap);
		}
	}
}
