package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;

import sorts.InsertionSort;
import sorts.Sort;

public class Main extends JFrame implements SortListener
{
	BufferedImage buffer;	
	Font f;
	FontMetrics m;
	
	int[] array;
	Canvas canvas;
	JSlider slider;
	Sort sort;

	int swaps = 0, compares = 0;
	int labelSpace = 100;
	
	int operations = 0;
	
	private static boolean pause = false;
	
	public Main(int[] array)
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1800, 1000);
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
		
		JButton pauseButton = new JButton("pause");
		pauseButton.setSize(100, 30);
		pauseButton.setLocation(300, 900);
		pauseButton.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e) {
				pause = !pause;				
			}
			
		});
		add(pauseButton);
		
		slider = new JSlider(JSlider.HORIZONTAL, -1000, 1000, 0);
		slider.setSize(300, 60);
		slider.setLocation(500, 890);
		slider.setMajorTickSpacing(500);
		slider.setMinorTickSpacing(100);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		add(slider);
		
		sort = new InsertionSort(array, this);
	}
	
	static public void main(String[] edfrgthyujik)
	{
		Main m = new Main(Sort.generate(800, true));
		m.sort.shuffle();
		pause = true;
		m.swaps = 0; 
		m.compares = 0;
		m.clear();
		m.setVisible(true);
		
		m.operationPerformed(); // fake; initial pause
		m.sort();
		
		m.slider.setValue(m.slider.getMaximum());
		m.repaint();
		m.operationPerformed(); // fake; end pause
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
		operationPerformed();
	}

	@Override
	public void compare(int n1, int n2) 
	{
		compares += 1;
		clear();
		canvas.mirror[n1] = 1;
		canvas.mirror[n2] = 1;
		operationPerformed();
	}
	
	private void sleep()
	{
		long delay = slider.getValue();
		try {
			Thread.sleep((delay*delay + delay)/10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
	}
	
	private void clear()
	{
		for (int i = 0; i < array.length; i++)
			canvas.mirror[i] = 0;
	}

	@Override
	public void insert(int x, int position, int gap) 
	{
		clear();
		for (int i = position; i <= x; i += gap)
		{
			canvas.mirror[i] = -1;
			swaps += 1;
		}
		operationPerformed();
	}
	private void operationPerformed()
	{
		int delay = slider.getValue()*10;
		if (delay > 0)
		{
			sleep();
			repaint();
		}
		else
		{
			operations += 1;
			operations %= (1-delay);
			if (operations == 0)
				repaint();
		}	
		while (Main.pause)
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}







