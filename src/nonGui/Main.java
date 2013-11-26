package nonGui;

import java.lang.reflect.InvocationTargetException;

import gui.SortListener;
import sorts.*;

public class Main implements SortListener
{
	long swaps = 0, compares = 0;
	
	static public void main(String[] args) throws Exception
	{
		int length = 100000;
		for (Class c: new Class[]
				{
					QuickSort.class,
					CombSort.class,
					HeapSort.class,
					ShellSort.class,
					InsertionSort.class,
					SelectionSort.class,
					BubbleSort.class
				})
		{
			Main m = new Main();
			int[] array = Sort.generate(length, true);
			Sort sort = (Sort) c.getConstructor(new int[0].getClass(), SortListener.class).newInstance(array, m);
			sort.shuffle();
			m.swaps = 0; m.compares = 0;
			sort.sort();
			System.out.println(c.getName());
			//System.out.println("\tlog of compares: " + Math.log(m.compares)/Math.log(length));
			System.out.println("\tlog of swaps:    " + Math.log(m.swaps)/Math.log(length));
			//System.out.println("\t\tlog of total:   " + Math.log(m.compares + m.swaps)/Math.log(length));
		}
	}
	
	
	@Override
	public void swap(int n1, int n2) 
	{
		swaps++;
	}

	@Override
	public void compare(int n1, int n2) 
	{
		compares++;
	}

	@Override
	public void insert(int x, int position, int gap) 
	{
		swaps += (x-position)/gap;
	}
}
