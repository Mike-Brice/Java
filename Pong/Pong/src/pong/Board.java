
package pong;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Mike Brice
 */
public class Board extends JPanel{
    
    //Fields
    //Paddles
    private final int height = 150;
    private final int width = 10;
    private final int ball = 8;
    private int paddleA, paddleB;
    private Position ballPosition;
    
    /*
     * paints the ball, paddles, and other decrotative items on the panel
     */
    @Override
    public void paint(Graphics g) {
        
        //Board
        g.setColor(Color.black);
        g.fillRect(0,0, getWidth(), getHeight());
        
        //Left Goal
        g.setColor(new Color(128,128,128));
        g.fillRect(5,5, 7, getHeight() - 10);
        
        //Middle Dashed Line
        g.setColor(new Color(128,128,128,127));
        for (int i = 12; i <= 1002; i += 52) {
            g.fillRect(getWidth() / 2, i, 7, getHeight()/25);
        }
        
        //Right Goal
        g.setColor(new Color(128,128,128));
        g.fillRect(getWidth() - 12, 5, 7, getHeight() - 10);
        
        //Paddles / Ball
        g.setColor(Color.white);
        g.fillRect(40, paddleA, width, height); //Left Paddle
        g.fillRect(getWidth() - 50, paddleB, width, height); //Right Paddle
        g.fillRect(ballPosition.getX(), ballPosition.getY(), ball, ball); //Ball
    }
    
    /** 
     * @param paddleA sets the y coordinate of the left paddle
     * @param paddleB sets the y coordinate of the right paddle
     */
    public void setPaddles(int paddleA, int paddleB) {
        this.paddleA = paddleA;
        this.paddleB = paddleB;
    }
    
    /**
     * @param ballPosition sets the ball position on the board
     */
    public void setBallPosition(Position ballPosition) {
        this.ballPosition = ballPosition;
    }
    
    /**
     * @return true if the ball has entered the left goal
     */
    public boolean isLeftGoal() {
        return ballPosition.getX() <= 0;
    }
    
    /**
     * @return true if the ball has entered the right goal
     */
    public boolean isRightGoal() {
        return ballPosition.getX() >= getWidth();
    }
}