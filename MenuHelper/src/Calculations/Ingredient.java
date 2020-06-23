/**
 * 
 */
package Calculations;

/**
 * @author Michael J. Brice
 *
 */
public class Ingredient {

	private String name;
	private double amount;
	private String unit;
	
	public Ingredient(String name, double amount, String unit) {
		this.name = name;
		this.amount = amount;
		this.unit = unit;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public String getUnit() {
		return this.unit;
	}
}
