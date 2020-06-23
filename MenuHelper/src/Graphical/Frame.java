package Graphical;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.LayoutManager;
import java.awt.FlowLayout;

/**
 * 
 */

/**
 * @author Michael J. Brice
 *
 */
public class Frame extends JFrame {
	
	private String title = "";
	private MenuBar menuBar;
	private LayoutManager layout;
	
	public Frame (String title) {
		super.setTitle(title);
		this.title = title;
		menuBar = new MenuBar();
	}
	
	public void open( ) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		
	}
	
	public void close() {
		setVisible(false);
	}
	
	public void setSize(int x, int y) {
		super.setSize(x,y);
		
	}
	
	public void update() {
		repaint();
		revalidate();
	}
	
	@Override
	public void setLayout(LayoutManager layout) {
		super.setLayout(layout);
		this.layout = layout;
	}
	
	public void addMenuBar(MenuBar menuBar) {
		
		this.menuBar = menuBar;
		this.setJMenuBar(menuBar);
		
	}
	
	public MenuBar returnMenuBar() {
		return this.menuBar;
	}
	
	public String returnTitle( ) {
		return this.title;
	}
	
}
