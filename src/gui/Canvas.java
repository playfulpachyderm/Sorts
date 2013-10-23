package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Canvas extends JPanel
{
	static final int PAD_X = 50;
	static final int PAD_Y = 30;
	
	static final Color BACKGROUND = new Color(238, 238, 238);
	static final Color BAR_COLOR = new Color(50,50,50);
	static final Color COMPARE = new Color(80,255,80);
	static final Color SWAP = new Color(240, 10, 10);
	static final Color OUTLINE = new Color(0,0,0);
	
	int[] array;
	byte[] mirror;
	
	int barWidth;
	int buff;
	int max;
	
	public void paint(Graphics g)
	{
		// clear
		g.setColor(BACKGROUND);
		g.fillRect(0,0,getWidth(),getHeight());
		
		for (int bar = 0; bar < array.length; bar ++)
		{
			if (mirror[bar] == 0) g.setColor(BAR_COLOR);
			if (mirror[bar] == 1) g.setColor(COMPARE);
			if (mirror[bar] == -1) g.setColor(SWAP);
			
			int height = getUsableHeight()/max * array[bar];
			g.fillRect(PAD_X + bar*barWidth, getHeight()/2 - height/2, barWidth - buff, height);
			g.setColor(OUTLINE);
			g.drawRect(PAD_X + bar*barWidth, getHeight()/2 - height/2, barWidth - buff, height);
		}
	}
	
	public void setArray(int[] array)
	{
		this.array = array;
		mirror = new byte[array.length];
		
		barWidth = getUsableWidth()/array.length;
		buff = barWidth/3;
		
		int largest = Integer.MIN_VALUE;
		for (int i: array)
		{
			if (i > largest) largest = i;
		}
		max = largest;
	}
	
	private int getUsableWidth()
	{
		return getWidth() - PAD_X*2;
	}
	private int getUsableHeight()
	{
		return getHeight() - PAD_Y*2;
	}
}
