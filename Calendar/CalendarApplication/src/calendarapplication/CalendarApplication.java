
package calendarapplication;

/**
 *
 * @author Mike Brice
 */
public class CalendarApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new CalendarApplication().run();
    }
    
    public void run() {
        CalendarGUI calendarGUI = new CalendarGUI();
        calendarGUI.startUp(); 
    }
}
