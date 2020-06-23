package Graphical;

import javax.swing.JMenu;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Michael J. Brice
 *
 */
public class Menu extends JMenu {
	
	private String title = "";
	private ArrayList<MenuItem> menuItems;
	private ArrayList<Menu> menus;
	
	public Menu(String title) {
		super.setText(title);
		this.title = title;
		
		menuItems = new ArrayList<MenuItem>();
		menus = new ArrayList<Menu>();
	}
	
	public void addMenu(Menu item) {
		
		menus.add(item);
		super.add(item);
		
	}
	public void addMenuItem(MenuItem item) {
		
		menuItems.add(item);
		
		super.add(item);
		
	}
	
	
	public String getTitle() {
		return this.title;
	}

}
