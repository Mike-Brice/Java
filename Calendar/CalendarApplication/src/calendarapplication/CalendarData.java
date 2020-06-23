
package calendarapplication;

/**
 *
 * @author Mike Brice
 */
public class CalendarData {
    //Jan 1st 2017 - sunday
    //Fields
    int feb;
    private final int[] daysInMonth = {31,feb,31,30,31,30,31,31,30,31,30,31};
    
    /**
     * 
     * @param year is the year of interest
     * @return the days in February
     */
    private int daysInFebruary(int year) {
        if (isLeapYear(year))
            return 29;
        else
            return 28;
    }
    
    /**
     * 
     * @param year is the year of interest
     * @return true if it is a leap year or false if it is not a leap year
     */
    private boolean isLeapYear(int year) {
        return year % 4 == 0;
    }
}
