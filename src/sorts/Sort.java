package sorts;

import gui.Main;
import gui.SortListener;

public abstract class Sort 
{
	int[] array;
	SortListener listener;
	
	public abstract void sort();
	
	public Sort(int[] array)
	{
		this(array, null);
	}
	
	static public int[] generate(int length, boolean sequence)
	{
		int[] temp = new int[length];
		if (sequence)
			for (int i = 0; i < length; i++)
				temp[i] = i+1;// - length/2;
		else for (int i = 0; i < length; i++)
			temp[i] = (int) (Math.random()*length + 1);
		return temp;
	}
	
	public Sort(int[] array, SortListener listener)
	{
		this.array = array;
		this.listener = listener;
	}
	
	/**
	 * @param x
	 * @param y
	 * @return result of "greater than" comparison: 1 if x bigger, 0 if equal, -1 if y bigger
	 */
	byte compare(int x, int y)
	{
		if (listener != null) listener.compare(x, y);
		if (array[x] > array[y]) return 1;
		if (array[x] < array[y]) return -1;
		
		return 0;
		
	}
	
	void swap(int x, int y)
	{
		if (x == y) return;
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
		if (listener != null) listener.swap(x, y);
	}
	
	void insert(int x, int position, int gap)
	{
		if (x == position) return;
		int temp = array[x];
		for (int i = x; i > position; i -= gap)
		{
			array[i] = array[i-gap];
		}
		array[position] = temp;
		if (listener != null) listener.insert(x, position, gap);
	}
	
	public boolean isSorted()
	{
		for (int i = 0; i < array.length-1; i++)
		{
			if (compare(i, i+1) > 0) return false;
		}
		return true;
	}
	
	public void shuffle()
	{
		for (int i = 0; i < array.length; i++)
		{
			swap(i, (int) (Math.random()*array.length));
		}
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		for (int i: array) buffer.append(i).append(" ");
		return buffer.toString();
	}

}