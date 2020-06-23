import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import java.awt.Toolkit;

import Graphical.Button;
import Graphical.Menu;
import Graphical.MenuBar;
import Graphical.MenuItem;
import Graphical.MyListenerEvent;
import Graphical.MyListener;
import Graphical.Panel;

public class AddRecipe extends JDialog {

	private UndoManager manager;
	private int counterID = 1;
	Dimension screenSize;
	
	public AddRecipe(Window owner, Dialog.ModalityType modalityType, Dimension size) {
		super(owner, "Add Recipe", modalityType);
		
		setLayout(new BorderLayout());
		manager = new UndoManager();
		screenSize = size;
		
		// Menu Edit
		Menu edit = new Menu("Edit");
		
			// Menu Items
			MenuItem undo = new MenuItem("Undo");
			MenuItem redo = new MenuItem("Redo");
			MenuItem cut = new MenuItem("Cut");
			MenuItem copy = new MenuItem("Copy");
			MenuItem paste = new MenuItem("Paste");
			
			// ==============================================================================================================
			// KeyStrokes
			// ==============================================================================================================
			KeyStroke undoKey = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
			undo.setAccelerator(undoKey);
			
			KeyStroke redoKey = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK+KeyEvent.SHIFT_DOWN_MASK);
			redo.setAccelerator(redoKey);
			
			KeyStroke cutKey = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK);
			cut.setAccelerator(cutKey);
			
			KeyStroke copyKey = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
			copy.setAccelerator(copyKey);
			
			KeyStroke pasteKey = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
			paste.setAccelerator(pasteKey);
			
			// ==============================================================================================================
			// Event Listeners
			// ==============================================================================================================
			undo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						manager.undo();
					}
					
					catch (Exception ex) {
						
					}
				}
				
			});
			
			redo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						manager.redo();
					}
					
					catch (Exception ex) {
						
					}
				}
				
			});

			cut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Cut");
				}
				
			});
			
			copy.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Copy");
				}
				
			});
			
			paste.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Paste");
				}
				
			});
			
		edit.addMenuItem(undo);
		edit.addMenuItem(redo);
		edit.addSeparator();	
		edit.addMenuItem(cut);
		edit.addMenuItem(copy);
		edit.addMenuItem(paste);
		
		MenuBar menuBar = new MenuBar();
		menuBar.addMenu(edit);
		
		setJMenuBar(menuBar);
		
		// ==============================================================================================================
		// Panels
		// ==============================================================================================================
		
		Panel container = new Panel();
		container.setLayout(new BorderLayout());
		
		JLabel name = new JLabel("Name: ");
		JTextField nameField = new JTextField();
		
		nameField.getDocument().addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				manager.addEdit(e.getEdit());	
			}
		});
		
		nameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				
			}
		});
		
		
		
		Panel namePanel = new Panel();
		namePanel.setLayout(new GridLayout(0,2));
		
		namePanel.add(name);
		namePanel.add(nameField);
		
		Panel ingredients = new Panel(new GridLayout(0,1));
		JLabel ingredient = new JLabel("Ingredients");
		//ingredients.add(ingredient);
		
		ingredients.add(addIngredient(0, ingredients));
		
		
		for (int i = 0; i < 7; i++) {
			ingredients.add(new Panel());
		}
		
		Panel buttons = new Panel();
		Button addMore = new Button("Add More Ingredients");
		addMore.setToolTipText("Press to add another ingredient to recipe." );
		
		addMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (counterID < 8) {
					ingredients.remove(counterID);
					ingredients.add(addIngredient(counterID, ingredients), counterID);
				}
				else {
					ingredients.add(addIngredient(counterID, ingredients));
				}
				
				counterID++;
				update();
			}
		});
		
		buttons.add(addMore);
		
		Panel ing = new Panel(new BorderLayout());
		JScrollPane scroll = new JScrollPane(ingredients);
		ing.add(ingredient, BorderLayout.NORTH);
		ing.add(scroll, BorderLayout.CENTER);
		
		container.add(namePanel, BorderLayout.NORTH);
		container.add(ing, BorderLayout.CENTER);
		container.add(buttons, BorderLayout.SOUTH);
		
		add(container);
		
		pack();
		
		// Landscape
		if (screenSize.width > screenSize.height)
			setSize((int)(screenSize.width / 3.2) , (int)(screenSize.height / 1.35));
		// Portrait
		else
			setSize((int)(screenSize.width / 1.8) , (int)(screenSize.height / 2.4));
		
		
		setResizable(false);
		setVisible(true);
	}
	
	private Panel addIngredient(int ID, Panel ingredients) {
		
		JLabel ingredientLabel = new JLabel("Name");
		ingredientLabel.setToolTipText("<html><p>Name of the ingredient.</p><p>Keep similar ingredients consistant amoung recipes.</p><p>For example: Granulated Sugar.</p> </html>" );
		JLabel amountLabel = new JLabel("Amount");
		JLabel unitLabel = new JLabel("Units");
		JTextField ingredient = new JTextField();
		JTextField amount = new JTextField();
		
		String[] type = {"Volume", "Weight", "Length"};
		String[] volume = {"TSP" , "TBSP", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "MiliLiter", "Liter", "Back"};
		String[] weight = {"Ounce", "Pound", "Gram", "KiloGram", "Back"};
		String[] length = {"Inch (in, \")", "Feet (ft, ')", "CentiMeter (cm)", "MiliMeter (mm)", "Back"};
		
		JComboBox<String> unit = new JComboBox<String>(type);
		
		Panel panel = new Panel(ID);
		Panel subIngredient = new Panel();
		Panel subAmount = new Panel();
		Panel subUnit = new Panel();
		
		
		
		ingredient.getDocument().addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				manager.addEdit(e.getEdit());	
			}
		});
		
		ingredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				
			}
		});
		
		amount.getDocument().addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				manager.addEdit(e.getEdit());	
			}
		});
		
		amount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				
			}
		});
		
		JTextField textField = (JTextField)unit.getEditor().getEditorComponent();
		
		textField.getDocument().addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				manager.addEdit(e.getEdit());	
			}
		});
		
		unit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				
				String selected = (String)cb.getSelectedItem();
				
				switch(selected) {
				
				case "Volume":
					unit.removeAllItems();
					
					for (int i = 0; i < volume.length; i++)
						unit.addItem(volume[i]);
					repaint();
					revalidate();
					break;
					
				case "Weight":
					unit.removeAllItems();
					
					for (int i = 0; i < weight.length; i++)
						unit.addItem(weight[i]);
					repaint();
					revalidate();
					break;
				
				case "Length":
					unit.removeAllItems();
					
					for (int i = 0; i < length.length; i++)
						unit.addItem(length[i]);
					repaint();
					revalidate();
					break;
					
				case "Back":
					unit.removeAllItems();
					
					for (int i = 0; i < type.length; i++)
						unit.addItem(type[i]);
					repaint();
					revalidate();
					break;
					
				default:
					break;
				}
				
				
			}
		});
				
		Button remove = new Button("X");
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Panel temp = (Panel)ingredients.getComponent(ID);
				//updateIngredients(ingredients, ID);
				//update();
				
				MyListenerEvent event = new MyListenerEvent();
				event.setButtonPressed(true);
				event.setID(ID);
				
			}
		});
		
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			        .addComponent(ingredientLabel)
			        .addComponent(ingredient))
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addComponent(amountLabel)
	                .addComponent(amount))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addComponent(unitLabel)
	                .addComponent(unit))
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			        .addComponent(remove))
			);
		
		//layout.linkSize(SwingConstants.HORIZONTAL, amount, ingredient);

		layout.setVerticalGroup(layout.createSequentialGroup()
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		        .addComponent(ingredientLabel)
		        .addComponent(amountLabel)
		        .addComponent(unitLabel)
		        .addComponent(remove))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(ingredient)
                .addComponent(amount)
                .addComponent(unit))
		);
		
		/*		
		panel.add(subIngredient);
			
		panel.add(subAmount);
			
		panel.add(subUnit);
		*/
		
		//remove.setPreferredSize(new Dimension(10, 10));
		
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// Landscape
		if (screenSize.width > screenSize.height)
			panel.setSize((int)(screenSize.width / 8) , (int)(screenSize.height / 8));
		// Portrait
		else
			panel.setSize((int)(screenSize.width / 1.8) , (int)(screenSize.height / 2.4));
	
		return panel;
	
	}
	private void update() {
		repaint();
		revalidate();
	}
	
	// ID = panel to remove
	private void updateIngredients(Panel ingredients, int ID) {
		
		
		int length = ingredients.getComponentCount();
		
		Panel[] panels = new Panel[length];
		
		for (int i = 0; i < length; i++) {
			panels[i] = (Panel)ingredients.getComponent(i);
		}
		//Panel[] panels = (Panel[])ingredients.getComponents();
		
		ArrayList<Panel> test = new ArrayList<Panel>();
		
		int newID = 0;
		
		for (Panel temp : panels) {
			if (temp.getID() != ID) {
				test.add(temp);
				temp.setID(newID);
				newID++;
			}
		}
		
		// Cant remove itself, find away to have button flag panel and have panel remove.
		counterID--;
		System.out.println("ID: " + ID + " Counter: " + counterID);
		ingredients.removeAll();
		
		if (counterID > 7) {
			for (Panel temp: test) {
				ingredients.add(temp);
			}
		}
		else {
			
			if (test.size() != 0) {
				for (Panel temp: test) {
					ingredients.add(temp);
				}
			}
			
			
			ingredients.add(new Panel());
			
		}
		
		update();
	}
}
