package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import sorts.*;

public class Main extends JFrame implements SortListener
{
	BufferedImage buffer;	
	Font f;
	FontMetrics m;
	
	int[] array;
	Canvas canvas;
	Sort sort;

	int swaps = 0, compares = 0;
	int labelSpace = 100;
	
	int delay = 0;
	
	public Main(int[] array)
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(null);
		
		buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		f = new Font("Book Antiqua", Font.PLAIN, 14);
		setFont(f);
		m = getFontMetrics(f);
		
		this.array = array;
		
		
		canvas = new Canvas();
		canvas.setSize(getUsableWidth(), getUsableHeight() - labelSpace);
		canvas.setArray(array);
		canvas.setLocation(0,0);
		add(canvas);
		
		sort = new HeapSort(array, this);
	}
	
	static public void main(String[] args)
	{
		Main m = new Main(Sort.generate(40, true));
		m.sort.shuffle();
		m.swaps = 0; 
		m.compares = 0;
		m.clear();
		m.setVisible(true);
		
		m.delay = 5;
		m.sort();
		
		m.delay = 500;
		m.sleep(m.delay);
		m.clear();
		m.repaint();
	}
	public void update(Graphics g)
	{
		Graphics g1 = buffer.getGraphics();
		paint(g1);
		g.drawImage(buffer, 0, 0, null);
	}
	
	public void repaint()
	{
		//super.repaint();
		if (getGraphics() != null)
			update(getGraphics());
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		g.setColor(Color.black);
		g.setFont(f);
		String s1 = "Compares:";
		String s2 = "" + compares;
		String s3 = "Swaps:";
		String s4 = "" + swaps;
		
		int leftAlign = getWidth()/2 - m.stringWidth(s1)/2;
		int topAlign = canvas.getHeight() + labelSpace/3;
		int tab = m.stringWidth(s1) + 30;
		
		g.drawString(s1, leftAlign, topAlign);
		g.drawString(s2, leftAlign + tab, topAlign);
		g.drawString(s3, leftAlign, topAlign + m.getHeight());
		g.drawString(s4, leftAlign + tab, topAlign + m.getHeight());
	}
	private void sort()
	{
		sort.sort();
	}
	
	private int getUsableHeight()
	{
		return getHeight() - 39;
	}
	private int getUsableWidth()
	{
		return getWidth() - 16;
	}

	@Override
	public void swap(int n1, int n2) 
	{
		swaps += 1;
		clear();
		canvas.mirror[n1] = -1;
		canvas.mirror[n2] = -1;
		repaint();
		sleep(delay);
	}

	@Override
	public void compare(int n1, int n2) 
	{
		compares += 1;
		clear();
		canvas.mirror[n1] = 1;
		canvas.mirror[n2] = 1;
		repaint();
		sleep(delay);
	}
	
	private void sleep(int millis)
	{
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void clear()
	{
		for (int i = 0; i < array.length; i++)
			canvas.mirror[i] = 0;
	}

	@Override
	public void insert(int x, int position) 
	{
		clear();
		for (int i = position; i <= x; i++)
		{
			canvas.mirror[i] = -1;
			swaps += 1;
		}
		repaint();
		sleep(delay);
	}
}
