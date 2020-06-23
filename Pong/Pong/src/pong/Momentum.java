package pong;

/**
 *
 * @author Mike Brice
 */
public class Momentum implements Runnable{

    //Fields
    private int x, y; //Velocities
    private final int top; //Conditions
    private final int bottom; //Conditions
    private Paddle paddleA;
    private Paddle paddleB;
    private Position currentPosition;
    private Position nextPosition;

    /**
     * Used to determine how the ball moves throughout the game.
     * @param x initial x velocity
     * @param y initial y velocity
     * @param top y position of the top wall
     * @param bottom y position of the bottom wall
     */
    public Momentum(int x, int y, int top, int bottom) {
        
        //If the initial x velocity and the initial y velocity are 0 then they 
        //are 1 and 1 respectivaly
        if (x == 0 && y == 0) {
            x++;
            y++;
        }
        this.x = x;
        this.y = y;
        this.top = top;
        this.bottom = bottom;
    }

    /**
     * @param paddleA Position of the left paddle
     * @param paddleB Position of the right paddle
     */
    public void setPaddles(Paddle paddleA, Paddle paddleB) {
        this.paddleA = paddleA;
        this.paddleB = paddleB;
    }
    
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }
    
    public Position nextPosition() {
        return nextPosition;
    }
    
    /**
     * 
     * @param currentPosition the current ball position
     * @return The next position depending if the ball is free moving, hitting a wall, or hitting a paddle
     */
    private void nextPosition(Position currentPosition) {
        
        /* 
        * An object that collides with a wall will bounce off the wall at the 
        * same angle that it hit the wall in the opposite direction. 
        * For example if a ball hits the wall at 45 degrees with respect to the 
        * ball's path and the wall then the ball will bounce off the wall at 
        * 135 degrees from the original direction or 45 degrees from the ball's 
        * new path and the wall. The ball will be moving with the same speed
        * just in a new direction.
        */
        
        //Move the ball forward by the x velocity and the y velocity
        this.nextPosition = new Position(currentPosition.getX() + x, currentPosition.getY() + y);
        
        //If the next position is either at the top or past the top turn the ball around
        if (nextPosition.getY() <= top) {
            y = -1 * y; //Flip the y velocity
            this.nextPosition = new Position(currentPosition.getX() + x, currentPosition.getY() + y);
        } 
        //If the next position is either at the bottom or past the bottom turn the ball around
        else if (nextPosition.getY() >= bottom) {
            y = -1 * y; //Flip the y velocity
            this.nextPosition = new Position(currentPosition.getX() + x, currentPosition.getY() + y);
        } 
        //If the next position is hitting the paddle then turn it around
        else if (nextPosition.getX() <= paddleA.getPaddle().getX() && nextPosition.getY() >= paddleA.getPaddle().getY()-5 && nextPosition.getY() <= paddleA.getPaddle().getY() + 155) {
            x = -1 * x; //Flip the x velocity
            this.nextPosition = new Position(currentPosition.getX() + x, currentPosition.getY() + y);
        } 
        //If the next position is hitting the paddle then turn it around
        else if (nextPosition.getX() >= paddleB.getPaddle().getX() && nextPosition.getY() >= paddleB.getPaddle().getY()-5 && nextPosition.getY() <= paddleB.getPaddle().getY() + 155) {
            x = -1 * x; //Flip the x velocity
            this.nextPosition = new Position(currentPosition.getX() + x, currentPosition.getY() + y);
        } 
        //If the ball did not collide with a wall or a paddle then the original nextPosition is used
        else {
        }
    }

    @Override
    public void run() {
        setPaddles(paddleA, paddleB);
        nextPosition(currentPosition);
    }
}