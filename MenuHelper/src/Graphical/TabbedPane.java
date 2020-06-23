package Graphical;

import javax.swing.JTabbedPane;

import java.awt.Component;
import java.util.ArrayList;

public class TabbedPane extends JTabbedPane {
	
	private ArrayList<Component> panels;
	
	public TabbedPane() {
		panels = new ArrayList<Component>();
	}
	
	@Override
	public void addTab(String title, Component component) {
		super.addTab(title, component);
		panels.add(component);
	}
	
	public ArrayList<Component> getTabs() {
		return panels;
	}

}
