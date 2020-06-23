/**
 * 
 */
package Graphical;

/**
 * @author Michael J. Brice
 *
 */
public class MyListenerEvent {

	private boolean buttonPressed = false;
	private int ID = -1;
	
	public MyListenerEvent () {
		
	}
	
	public void setButtonPressed(boolean status) {
		this.buttonPressed = status;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public boolean getButtonPressed() {
		return this.buttonPressed;
	}
	
	public int getID( ) {
		return this.ID;
	}
}
