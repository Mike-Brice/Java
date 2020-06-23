/**
 * 
 */

/**
 * @author Michael J. Brice
 *
 */

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import java.io.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.undo.*;
import javax.swing.text.*;

import Graphical.*;

public class MenuPlanner {

	/*
	 * Start program
	 */
	
	Dimension screenSize;
	
	public void run() {
		Frame mainFrame = new Frame("Menu Planner");
		mainFrame.setLayout(new GridLayout(0,2));
		
		UndoManager manager = new UndoManager();
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
			// ==============================================================================================================
			// mainFrame MenuBar
			// ==============================================================================================================
			MenuBar menuBar = new MenuBar();
			
				// ==============================================================================================================
				// Menu File
				// ==============================================================================================================
				Menu file = new Menu("File");
				
					// ==============================================================================================================
					// MenuItems
					// ==============================================================================================================
					MenuItem newP = new MenuItem("New Menu");
					MenuItem open = new MenuItem("Open Menu");
					MenuItem save = new MenuItem("Save");
					MenuItem saveAs = new MenuItem("Save As");
					MenuItem close = new MenuItem("Close Menu");
					MenuItem print = new MenuItem("Print");
					MenuItem exit = new MenuItem("Exit");
					
					// ==============================================================================================================
					// KeyStrokes
					// ==============================================================================================================
					KeyStroke openKey = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
					open.setAccelerator(openKey);
					
					KeyStroke saveKey = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
					save.setAccelerator(saveKey);
					
					KeyStroke saveAsKey = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK+KeyEvent.SHIFT_DOWN_MASK);
					saveAs.setAccelerator(saveAsKey);
					
					// ==============================================================================================================
					// Event Listeners
					// ==============================================================================================================
					
					// Open || Opens a open dialog and gets the file name for a menu save
					open.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							JFileChooser fc = new JFileChooser();
							FileFilter filter = new FileNameExtensionFilter("Menu Files", "menu");
							fc.setFileFilter(filter);
						    int i = fc.showOpenDialog(open);    
						    if(i == JFileChooser.APPROVE_OPTION){    
						        File f = fc.getSelectedFile();    
						        String filepath = f.getPath(); 
						        
						        if (!filepath.endsWith(".menu")) {
						        	JOptionPane.showMessageDialog(mainFrame,
					        		    "Not a valid Menu.",
					        		    "Error",
					        		    JOptionPane.ERROR_MESSAGE);
						        }
						         
						    }
						}
						
					});
					
					save.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
						}
						
					});
					
					// Save As || Opens a save as dialog and creates a menu save
					saveAs.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							JFileChooser fc = new JFileChooser(); 
							FileFilter filter = new FileNameExtensionFilter("Menu Files", "menu");
							fc.setFileFilter(filter);
						    int i = fc.showDialog(saveAs, "Save As"); 
						    
						    if(i == JFileChooser.APPROVE_OPTION){    
						        File f = fc.getSelectedFile();    
						        String filepath = f.getPath(); 
						        System.out.println(filepath);
						           
						    }
						    else {
						    	System.out.println("False");
						    }
						}
						
					});
					
					
					
					exit.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
						
					});
					
				// ==============================================================================================================
				// Add Menu Items to File Menu
				// ==============================================================================================================
				file.addMenuItem(newP);
				file.addMenuItem(open);
				file.addMenuItem(save);
				file.addMenuItem(saveAs);
				file.addMenuItem(close);
				file.addSeparator();
				file.addMenuItem(print);
				file.addSeparator();
				file.addMenuItem(exit);
				
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
				
				// ==============================================================================================================
				// Menu Recipes
				// ==============================================================================================================
				Menu recipes = new Menu("Recipes");
				
					// Menu Items
					MenuItem add = new MenuItem("Add Recipe");
					MenuItem editR = new MenuItem("Edit Recipe");
					MenuItem remove = new MenuItem("Remove Recipe");
					MenuItem list = new MenuItem("Your Recipes");
					
					add.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new AddRecipe(mainFrame, Dialog.ModalityType.APPLICATION_MODAL, screenSize);
						}
					});

					
				recipes.addMenuItem(add);
				recipes.addMenuItem(editR);
				recipes.addMenuItem(remove);
				recipes.addMenuItem(list);

			
			// Add menus to menuBar
			menuBar.addMenu(file);
			menuBar.addMenu(edit);
			menuBar.addMenu(recipes);
		
		// Add menu bar to mainFrame
		mainFrame.addMenuBar(menuBar);
		
		// ==============================================================================================================
		// Panels
		// ==============================================================================================================
		
		MenuMaker menuMaker = new MenuMaker(mainFrame, manager);
		menuMaker.updateScreenSize(screenSize);
		
			// ==============================================================================================================
			// Panel Tabbed
			// ==============================================================================================================
			TabbedPane tab = new TabbedPane();
			//tab.setSize(960, 1080);
			tab.setBorder(BorderFactory.createLineBorder(Color.black));
			tab.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
			
			// Name of the tab
			String daysStr = "Days";
			String menuMakerStr = "Menu Planner";
			String menuReviewStr = "Menu Review";

				// Panel days
				Panel days = new Panel();
				
				days.setBorder(BorderFactory.createLineBorder(Color.black));
				days.setLayout(new BorderLayout());
				
			
					// Labels
					JLabel text = new JLabel("Enter the number of days you wish to plan a menu for.");
					JLabel text2 = new JLabel("Bob");
					
					int numberDays = 31;
					Integer[] numbers = new Integer[numberDays];
					
					for (int i = 1; i < numberDays+1; i++) {
						numbers[i-1] = i;
					}
					
					JComboBox<Integer> dayList = new JComboBox<Integer>(numbers);
					dayList.setSelectedIndex(6);
					dayList.setEditable(true);
					dayList.setForeground(Color.BLUE);
					dayList.setFont(new Font("Arial", Font.BOLD, 14));
					JTextField textField = (JTextField)dayList.getEditor().getEditorComponent();
					textField.addKeyListener(new KeyAdapter() {

			            @Override
			            public void keyTyped(KeyEvent e) {
			                char c = e.getKeyChar();
			    
			                // Checks if numbers, delete, backspace, and enter are clicked, and if so allow it through
			                if ((!Character.isDigit(c))) {
			                	if ((c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_DELETE) && (c != KeyEvent.VK_ENTER)) {
			                		e.consume();
			                	}
			                }
			            }
			        });
					
					int[] numberOfDays = new int[1];
						
					// Add Undo Redo ability
					textField.getDocument().addUndoableEditListener(new UndoableEditListener() {
						@Override
						public void undoableEditHappened(UndoableEditEvent e) {
							manager.addEdit(e.getEdit());	
						}
					});
							
					// Get the number of days entered.
					dayList.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
					
							@SuppressWarnings("unchecked")
							JComboBox<Integer> cb = (JComboBox<Integer>)e.getSource();
							numberOfDays[0] = (Integer)cb.getSelectedItem();
					        textField.setEnabled(false);
					        dayList.setEnabled(false);
					        tab.setEnabledAt(tab.indexOfTab(menuMakerStr), true);
					        menuMaker.update(numberOfDays[0]);
					        mainFrame.repaint();
							
					    }
					});
					
					text.setHorizontalAlignment(JLabel.CENTER);
					text.setVerticalAlignment(JLabel.CENTER);
					
				// Add stuff to Panel days
				days.add(text, BorderLayout.NORTH);
				days.add(dayList, BorderLayout.CENTER);
				
				//TabbedPane menuMaker = menuMakerCls.getTabbedPane();
				
				// Panel menuReview
				Panel menuReview = new Panel();
				menuReview.setBorder(BorderFactory.createLineBorder(Color.black));
				
					// Buttons
					Button bill = new Button("bill");
					
					// Add stuff to Panel menuMaker
					menuReview.add(bill);
							
			// Add to tab
			tab.addTab(daysStr, days);
			tab.addTab(menuMakerStr, menuMaker);
			tab.addTab(menuReviewStr, menuReview);
			
			tab.setEnabledAt(tab.indexOfTab(menuMakerStr), false);
			tab.setEnabledAt(tab.indexOfTab(menuReviewStr), false);
			
			tab.setTabPlacement(TabbedPane.TOP);
			tab.setSelectedIndex(0);
			
			// ==============================================================================================================
			// Panel Right
			// ==============================================================================================================
			Panel right = new Panel();
			right.setBorder(BorderFactory.createLineBorder(Color.black));
				
				// Buttons
				Button tim = new Button("Tim");
				
			// Add stuff to Panel Right
			right.add(tim);			
		
		// Add Panels to mainFrame
		mainFrame.add(tab);
		mainFrame.add(right);
		
		mainFrame.pack();
		
		mainFrame.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("Hello");
				
			}

			@Override
			public void componentResized(ComponentEvent e) {
				GraphicsDevice device = mainFrame.getGraphicsConfiguration().getDevice(); // Gets the current monitor
				
				//GraphicsEnvironment enviroment = GraphicsEnvironment.getLocalGraphicsEnvironment(); // Gets the graphical enviroment
				//GraphicsDevice[] gs = enviroment.getScreenDevices(); // Gets all the monitors
				
				//height of the task bar
				//Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(mainFrame.getGraphicsConfiguration());
				//int taskBarSize = scnMax.bottom;
			
				//Dimension deviceD = new Dimension(device.getDisplayMode().getWidth(), device.getDisplayMode().getHeight());
				
				if ((mainFrame.getHeight()+24) == device.getDisplayMode().getHeight() && (mainFrame.getWidth()-16) == device.getDisplayMode().getWidth()) {
					screenSize = new Dimension(device.getDisplayMode().getWidth(), device.getDisplayMode().getHeight());
					menuMaker.updateScreenSize(screenSize);
				}
				else {
					
				}
				
				//System.out.println("JFrame: " + (mainFrame.getHeight()+24) + "x" + (mainFrame.getWidth()-16) + "Device: " + deviceD.height + "x" + deviceD.width);
				
					
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		// open mainFrame
		mainFrame.open();
	}
		
}
