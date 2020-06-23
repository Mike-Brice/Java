
package pong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Mike Brice
 */
public class Ball implements Runnable{
    
    //Fields
    private Position pos;
    private Momentum momentum;
    ExecutorService executor = Executors.newFixedThreadPool(16);
    
    /**
     * @param pos initial position of the ball
     */
    public Ball(Position pos) {
        this.pos = pos;
    }
    
    /**
     * @param pos the new position of the ball
     */
    public void updatePosition(Position pos) {
        this.pos = pos;
    }
    
    /**
     * @return the position of the ball
     */
    public Position getPosition() {
        return pos;
    }
    
    public void setMomentum(Momentum momentum) {
        this.momentum = momentum;
    }

    @Override
    public void run() {
        updatePosition(momentum.nextPosition()); //update the ball position based on the next position determined by momentum
    }
}
