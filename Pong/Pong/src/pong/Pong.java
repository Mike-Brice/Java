
package pong;

/**
 * @author Mike Brice
 */

public class Pong {

    /**
     * Calls run
     * @param args system arguments
     */
    public static void main(String[] args) {
        new Pong().run();
    }
    
    /**
     * runs the program
     */
    public void run() {
        PongGUI pong = new PongGUI();
        pong.start();
    }
}
