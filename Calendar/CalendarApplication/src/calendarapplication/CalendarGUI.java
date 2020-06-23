
package calendarapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author Mike Brice
 */
public class CalendarGUI extends JFrame implements ComponentListener, ActionListener{
    
    //Fields
    //ArrayList
    ArrayList<CalendarData> calendars = new ArrayList();
    
    //Strings
    private final String[] months = {"January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private final String[] daysOfTheWeek = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    
    //JPanels
    JPanel[] panels = new JPanel[42]; //Panels used to store the subpanels dates and subpanels information
    JPanel[] dates = new JPanel[35]; 
    JPanel monthTitle = new JPanel(); //Panel that holds the current month and year
    JPanel container = new JPanel(new GridLayout(6,7)); //Contains all the panels not including monthTitle
    JPanel[] day = new JPanel[35]; //Subpanels of panels, used to store the date || aka 1 2 3
    
    //JLabels
    JLabel[] weekDay = new JLabel[7];
    JLabel title; //The slected month and year
    
    //JTextPane
    JTextArea[] textArea = new JTextArea[35];
    
    //JScrollPanes
    JScrollPane[] scrollPane = new JScrollPane[35];
    
    //Borders
    Border grayBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
    
    //Date Information
    DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
    DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
    LocalDate localDate = LocalDate.now();
    
    //Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    /**
     * Constructor
     */
    public CalendarGUI() {
    }
    
    /**
     * Starts Up the JFrame
     */
    public void startUp() {
        //Set the title of the JFrame to the current month and year       
        setTitle(months[Integer.parseInt(month.format(localDate)) - 1] +" " +year.format(localDate));
        
        //Sets up the JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(screenSize.width / 2,screenSize.height / 2);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        panels();
        setResizable(true);
        setVisible(true);
        addComponentListener(this);
    }
    
    /**
     * Sets up the panels of the JFrame
     */
    private void panels() {
        
        for (int i = 0; i < 42; i++) {
            //Creates the panels
            panels[i] = new JPanel(new BorderLayout());
            
            //Applies a border to the panels
            panels[i].setBorder(grayBorder);
            
            //Adds the panels to the container panel
            container.add(panels[i]);
        }
        
        //Applies the day of the week to the panels
        for (int i = 0; i < 7; i++) {
            weekDay[i] = new JLabel(daysOfTheWeek[i], SwingConstants.CENTER);
            panels[i].add(weekDay[i]);
            panels[i].setBackground(Color.WHITE);
            panels[i].setSize(10,10);
        }
        
        for (int i = 0; i < 35; i++) {
            
            textArea[i] = new JTextArea();
            textArea[i].setBackground(Color.WHITE);
            textArea[i].setLineWrap(true);

            //Sets the border of the information subpanel to the grayBorder
            textArea[i].setBorder(grayBorder);
            scrollPane[i] = new JScrollPane(textArea[i]);
        }
        
        for (int i = 7; i < 42; i++) {
            
            //Adds the JScrollPanes that contain the JTextAreas to panels || scrollPane is indexed from 0 - 35 where panels is 0 - 42
            panels[i].add(scrollPane[i-7], BorderLayout.CENTER);
            
            //JLabel that holds the temporay day number
            JLabel date = new JLabel("       1 ");
            
            //Creates the subpanel day that contains the day number
            day[i-7] = new JPanel(new GridLayout(1,2));
            
            //Adds the day number JLabel to the day subpanel
            day[i-7].add(date);
            
            //Sets the subpanel day border to grayBorder
            day[i-7].setBorder(grayBorder);
            
            //Adds the day subpanel to panels
            panels[i].add(day[i-7], BorderLayout.NORTH);
        }
        
        //Creates a JLabel that holds the title of the calendar, or the name of the month and year
        title = new JLabel(months[Integer.parseInt(month.format(localDate)) - 1] +" " +year.format(localDate));
        
        //Adds the JLabel to the panel that contains the month and year
        monthTitle.add(title);
        
        //Sets the border of the panel that contains the month and year
        monthTitle.setBorder(grayBorder);
        
        //Adds the panel with the month and year and the container panel to the JFrame
        add(monthTitle, BorderLayout.NORTH);
        add(container, BorderLayout.CENTER);
        
    }
    
    private int getFontSize() {
        return getHeight();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void componentResized(ComponentEvent e) {
        //Sets the font of the Title of the month based on the window size
        title.setFont(new Font("Arial", Font.BOLD, getFontSize() / 30));
        
        //Sets the font of the days of the week based on the window size
        for (int i = 0; i < 7; i++) {
            weekDay[i].setFont(new Font("Arial", Font.BOLD, getFontSize() / 40));
        }
        
        //Sets the size of the JTextPanes
        for (int i = 0; i < 35; i++) {
            textArea[i] = new JTextArea(getWidth(), getHeight());
        }   
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
