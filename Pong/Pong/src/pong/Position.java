
package pong;

/**
 *
 * @author Mike Brice
 */

public class Position {
        
    //Fields
    private final int x;
    private final int y;
    
    /**
     * @param x x coordinate of the position
     * @param y y coordinate of the position
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return x coordinate of the position
     */
    public int getX() {
        return x;
    }
    
    /**
     * @return y coordinate of the position
     */
    public int getY() {
        return y;
    }
}