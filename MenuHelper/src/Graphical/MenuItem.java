package Graphical;

import javax.swing.JMenuItem;

/**
 * 
 */

/**
 * @author Michael J. Brice
 *
 */
public class MenuItem extends JMenuItem {

	private String text = "";
	
	public MenuItem(String text) {
		super.setText(text);
		this.text = text;
	}
	
	public String getTitle() {
		return this.text;
	}
}
