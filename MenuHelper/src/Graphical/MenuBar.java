package Graphical;

import javax.swing.JMenuBar;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Michael J. Brice
 *
 */
public class MenuBar extends JMenuBar {

	private ArrayList<Menu> menus;
	
	public MenuBar() {
		
		menus = new ArrayList();
		
	}
	
	public void addMenu(Menu menu) {
		menus.add(menu);
		add(menu);
		
	}
	
	public ArrayList getMenus() {
		return menus;
	}
	
	public Menu getMenu(String title) {
		int index = menus.indexOf(title); 
		return menus.get(index);
	}
	
}
