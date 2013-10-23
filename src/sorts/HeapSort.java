package sorts;

import gui.SortListener;

public class HeapSort extends Sort
{
	public HeapSort(int[] array) 
	{
		super(array);
	}

	public HeapSort(int[] array, SortListener listener) 
	{
		super(array, listener);
	}

	@Override
	public void sort() 
	{
		int length = array.length;
		convertToHeap(length);
		
		
	}
	
	private void convertToHeap(int root)
	{
		
	}
	
	private int parent(int i)
	{
		return (i-1)/2;
	}
	
	private int leftChild(int i)
	{
		return 2*i + 1;
	}
	
	private int rightChild(int i)
	{
		int ret = 2*i + 2;
		if (ret < array.length) return ret;
		else return -1;
	}
	

    
}