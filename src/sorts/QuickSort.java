package sorts;

import gui.SortListener;

public class QuickSort extends Sort
{
	public QuickSort(int[] array) 
	{
		super(array);
	}
	
	public QuickSort(int[] array, SortListener listener) 
	{
		super(array, listener);
	}

	@Override
	public void sort() 
	{
		sort(0, array.length-1);
	}
	
	private void sort(int left, int right)
	{
		if (right - left == 1)
		{
			if (compare(left, right) > 0) 
				swap (left, right);
			return;
		}
		int leftTemp = left;
		int rightTemp = right;
		int pivot = right--;
	
		
		while (left < right)
		{
			while (compare(left, pivot) != 1) 
			{
				left++;
				if (left > right) break;
			}
			while(compare(right, pivot) != -1) 
			{
				right--;
				if (left > right) break;
			}
			if (left < right) swap(right, left);
		}
		insert(pivot, left, 1);

		if (left - leftTemp > 1)
			sort(leftTemp, left - 1);
		if (rightTemp - left > 1)
			sort(left + 1, rightTemp);
		
	}
}
