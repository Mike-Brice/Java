/**
 * 
 */
package Graphical;

import javax.swing.JButton;

/**
 * @author Michael J. Brice
 *
 */
public class Button extends JButton {

	private String text;
	
	public Button(String text) {
		super.setText(text);
		this.text = text;
	}
}
