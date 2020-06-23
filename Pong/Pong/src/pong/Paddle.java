package pong;

/**
 *
 * @author Mike Brice
 */
public class Paddle {

    //Fields
    private Position pos;

    /**
     * @param pos the position of the top left corner of the paddle
     */
    public Paddle(Position pos) {
        this.pos = pos;
        
    }

    /**
     * @param y update to the y coordinate of the paddle
     */
    public void updatePaddle(int y) {
        pos = new Position(pos.getX(), pos.getY() + y);
    }

    /**
     * @return the position of the top left corner of the paddle
     */
    public Position getPaddle() {
        return pos;
    }
}
