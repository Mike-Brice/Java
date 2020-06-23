import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.undo.UndoManager;

import Graphical.Button;
import Graphical.Frame;
import Graphical.Panel;
import Graphical.TabbedPane;

/**
 * 
 */

/**
 * @author Michael J. Brice
 *
 */
public class MenuMaker extends TabbedPane{

	private Panel[] week;
	private boolean done;
	private String[] days = {" Sunday", " Monday", " Tuesday", " Wednesday", " Thursday", " Friday", " Saturday"};
	private Frame mainFrame;
	private UndoManager manager;
	private Dimension screenSize = new Dimension(0,0);
	
	public MenuMaker(Frame mainFrame, UndoManager manager) {
		
		this.mainFrame = mainFrame;
		setBorder(BorderFactory.createLineBorder(Color.black));
		this.manager = manager;
		done = true;
				
		
	}
	
	public void updateScreenSize(Dimension screenSize) {
		this.screenSize = screenSize;
	}
	
	public void update(int numberOfDays) {
		
		if (done) {
			
			done = false;
			
			int weeks = numberOfDays / 7;
			int remainder = numberOfDays % 7;
			
			// If there are only full weeks
			if (remainder == 0) {
				
				week = new Panel[weeks];
				
				// Week 1
				week[0] = new Panel();
				week[0].setLayout(new GridLayout(0,1));
				for (int j = 0; j < 7; j++) {
					
					Panel panel = makeDayPlanner(days[j]);
					
					week[0].add(panel);
				}
				Panel buttons = new Panel();			
					
				if (weeks > 1) {
					Button next = new Button("Next");
					
					next.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e ) {
							int index = getSelectedIndex();
							setSelectedIndex(index+1);
						}
					});
					
					buttons.add(next);
				}
					
				week[0].add(buttons);
				addTab("Week " + (1), week[0]);
					
				// Week 2 <-> n-1
				for (int i = 1; i < weeks-1; i++) {
					week[i] = new Panel();
					week[i].setLayout(new GridLayout(0,1));
					
					for (int j = 0; j < 7; j++) {
						
						// Add stuff to Panels
						Panel panel = makeDayPlanner(days[j]);
						
						week[i].add(panel);
					}
					buttons = new Panel();			
					
					Button next = new Button("Next");
					Button back = new Button("Back");
					
					next.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e ) {
							int index = getSelectedIndex();
							setSelectedIndex(index+1);
						}
					});
					
					back.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e ) {
							int index = getSelectedIndex();
							setSelectedIndex(index-1);
						}
					});
					
					buttons.setLayout(new GridLayout(0,2));
					buttons.add(back);
					buttons.add(next);
					
					week[i].add(buttons);
					addTab("Week " + (i+1), week[i]);
				}
				
				// Week n
				if (weeks > 1) {
					week[weeks-1] = new Panel();
					week[weeks-1].setLayout(new GridLayout(0,1));
					
					for (int j = 0; j < 7; j++) {
						
						// Add stuff to Panels
						Panel panel = makeDayPlanner(days[j]);
						
						week[weeks-1].add(panel);
					}
					
					
				buttons = new Panel();			
				
				Button back = new Button("Back");
				
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e ) {
						int index = getSelectedIndex();
						setSelectedIndex(index-1);
					}
				});
				
				buttons.setLayout(new GridLayout(0,2));
				buttons.add(back);
				
				week[weeks-1].add(buttons);
				addTab("Week " + (weeks), week[weeks-1]);	
				}
			} // If only full weeks
			
			// If there are not full weeks
			else {
				
				weeks++;
				week = new Panel[weeks];
				
				// Week 1
				week[0] = new Panel();
				week[0].setLayout(new GridLayout(0,1));
				for (int j = 0; j < 7; j++) {
					
					// Add stuff to Panels
					Panel panel = makeDayPlanner(days[j]);
					
					week[0].add(panel);
					
					
				}
				
				Panel buttons = new Panel();			
				
				if (weeks > 1) {
					Button next = new Button("Next");
					
					next.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e ) {
							int index = getSelectedIndex();
							setSelectedIndex(index+1);
						}
					});
					
					buttons.add(next);
				}
				
				week[0].add(buttons);
				
				addTab("Week " + (1), week[0]);
				
				// Week 2 <-> n-1
				for (int i = 1; i < weeks - 1; i++) {
					week[i] = new Panel();
					week[i].setLayout(new GridLayout(0,1));
					
					for (int j = 0; j < 7; j++) {

						// Add stuff to Panels
						Panel panel = makeDayPlanner(days[j]);
						
						week[i].add(panel);
						
					}
					
				buttons = new Panel();			
				
				Button next = new Button("Next");
				Button back = new Button("Back");
				
				next.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e ) {
						int index = getSelectedIndex();
						setSelectedIndex(index+1);
					}
				});
				
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e ) {
						int index = getSelectedIndex();
						setSelectedIndex(index-1);
					}
				});
				
				buttons.setLayout(new GridLayout(0,2));
				buttons.add(back);
				buttons.add(next);
				
				week[i].add(buttons);
				addTab("Week " + (i+1), week[i]);
				}
				
				if (weeks > 1) {
					week[weeks-1] = new Panel();
					week[weeks-1].setLayout(new GridLayout(0,1));
					
					for (int j = 0; j < remainder; j++) {
						
						// Add stuff to Panels
						Panel panel = makeDayPlanner(days[j]);
						
						week[weeks-1].add(panel);
						
						
					}
				buttons = new Panel();			
				
				Button back = new Button("Back");
				
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e ) {
						int index = getSelectedIndex();
						setSelectedIndex(index-1);
					}
				});
				
				buttons.setLayout(new GridLayout(0,2));
				buttons.add(back);;
				
				week[weeks-1].add(buttons);
				addTab("Week " + weeks, week[weeks-1]);
				}
				
			} // If there are not full weeks	
		}	
	}
	
	private Panel makeDayPlanner(String day) {
		// Add stuff to Panels
		Panel panel = new Panel();
		Panel subPanel = new Panel();
		JLabel temp = new JLabel(day);
		Panel dinner = makeMealPlanner("Dinner");
		Panel lunch = makeMealPlanner("Lunch");
		Panel breakfast = makeMealPlanner("Breakfast");
		Panel snacks = makeMealPlanner("Snacks");
		
		subPanel.setLayout(new GridLayout(0,2));
		subPanel.add(dinner, 0);
		subPanel.add(lunch, 1);
		subPanel.add(breakfast, 2);
		subPanel.add(snacks, 3);
			
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setLayout(new BorderLayout());
		panel.add(temp, BorderLayout.NORTH);
		panel.add(subPanel, BorderLayout.CENTER);
		
		return panel;
	}
	
	private Panel makeMealPlanner(String text) {
		Panel panel = new Panel();
		Panel temp = new Panel();
		JCheckBox meal = new JCheckBox(text);
		meal.setToolTipText("Are you having " + text.toLowerCase() + " on this day?" );
		
		String[] recipes = {"Select Recipe", "Add Recipe"};
		
		JComboBox<String> mealSelector = new JComboBox<String>(recipes);
		mealSelector.setToolTipText("Select a meal.");
		
		mealSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				
				String selected = (String)cb.getSelectedItem();
				
				switch(selected) {
				
				case "Select Recipe":
					break;
				case "Add Recipe":
					new AddRecipe(mainFrame, Dialog.ModalityType.APPLICATION_MODAL, screenSize);
					break;
				default:
					break;
				}
		    }
		});
		
		JCheckBox leftOvers = new JCheckBox("Left Overs");
		leftOvers.setToolTipText("Is this meal leftovers?");
		
		panel.setLayout(new GridLayout(0,2));
		panel.add(meal);
		panel.add(temp);
		
		meal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (meal.isSelected()) {
					panel.remove(temp);
					panel.add(mealSelector);
					panel.add(temp);
					panel.add(leftOvers);
					panel.repaint();
					panel.revalidate();
				}
				else {
					panel.remove(mealSelector);
					panel.add(temp);
					panel.repaint();
					panel.revalidate();
				}			
		    }
		});
		return panel;
	}
}
