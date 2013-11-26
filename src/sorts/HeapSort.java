package sorts;

import gui.SortListener;

public class HeapSort extends Sort
{
	private int end;
	
	public HeapSort(int[] array) 
	{
		super(array);
	}

	public HeapSort(int[] array, SortListener listener) 
	{
		super(array, listener);
		end = array.length;
	}

	@Override
	public void sort() 
	{
		for (int x = array.length/2; x >= 0; x--)
			sinkFully(x);
		for (end = array.length; end > 0; )
		{
			swap(0, end-1);
			end -= 1;
			sinkFully(0);
		}
	}
	
	private void sinkFully(int root)
	{
		while ((root = sinkSingle(root)) != -1);
	}
	
	private int sinkSingle(int root)
	{
		int left = leftChild(root);
		int right = rightChild(root);
		
		int biggest;
		if (left == -1) 
			return -1;
		if (right == -1) 
			biggest = left;
		else biggest = compare(left, right) > 0 ? left : right;
		
		if (compare(biggest, root) > 0)
		{
			swap(biggest, root);
			return biggest;
		}
		return -1;
	}
	
	private int parent(int i)
	{
		int ret = (i-1)/2;
		if (ret >= 0) return ret;
		else return -1;
	}
	
	private int leftChild(int i)
	{
		int ret = 2*i + 1;
		if (ret < end) return ret;
		else return -1;
	}
	
	private int rightChild(int i)
	{
		int ret = 2*i + 2;
		if (ret < end) return ret;
		else return -1;
	}    
}