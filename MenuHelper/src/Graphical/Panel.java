package Graphical;

import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;

/**
 * @author Michael J. Brice
 *
 */
public class Panel extends JPanel {

	private LayoutManager layout;
	private int ID;
	
	public Panel() {
		this.layout = new FlowLayout();
		this.ID = -1;
	}
	
	public Panel(LayoutManager layout) {
		super(layout);
		this.layout = layout;
		this.ID = -1;
		
	}
	
	public Panel(int ID) {
		this.layout = new FlowLayout();
		this.ID = ID;
	}
	
	public Panel(LayoutManager layout, int ID) {
		super(layout);
		this.layout = layout;
		this.ID = ID;
	}
	
	public Component add(Component comp) {
		super.add(comp);
		return comp;
	}
	
	public void setLayout(LayoutManager layout) {
		this.layout = layout;
		super.setLayout(layout);
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getID() {
		return this.ID;
	}
}
