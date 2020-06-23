/*
 * Copyright (C) 2017 Couchoutput Studios
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package simon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Mike Brice - Couchoutput Studios
 */
public class SimonGUI extends JFrame implements ActionListener, ComponentListener, MouseListener {
    
    //Fields
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final Screen screen = new Screen();
    private final Random rand;
    private boolean blue, red, green, yellow, gameOver;
    
    //Menus
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menu = new JMenu("File");
    private final JMenuItem newGame = new JMenuItem("New Game");
    private ExecutorService executor;
    
    
    // =========================================================================
    /**
     * 
     */
    public SimonGUI() {
        executor = Executors.newFixedThreadPool(30); //Creates the thread pool
        rand = new Random();
        blue = false;
        red = false;
        green = false;
        yellow = false;
        gameOver = false;
        setup();
        
    }
    // =========================================================================
    /**
     * 
     */
    public void gameOver() {
        gameOver = true;
    }
    
    // =========================================================================
    /**
     * 
     */
    public void play() {
        
        /*//Allows each new instance of the game to be a parallel thread
        Runnable worker = new MyRunnable(screen);
        executor.execute(worker);*/

        System.out.println("Play");
        screen.updateColors(false, false, false, false);
        screen.repaint();
        
        try {
                //sleep 3 seconds
                Thread.sleep(500);
            } 
        
        catch (InterruptedException e) {}
        
        //Amount of lights to turn on per round
        int i = 1;
        while (!gameOver) {
            
            //Turning on the lights
            int j = 0;
            while (j < i && !gameOver) {
                
                blue = false;
                red = false;
                green = false;
                yellow = false;
                
                int color = rand.nextInt(4);
                
                switch (color) {
                    case 0:
                        blue = true;
                        break;
                    case 1:
                        red = true;
                        break;
                    case 2:
                        green = true;
                        break;
                    default:
                        yellow = true;
                        break;
                }
                
                screen.updateColors(blue, red, green, yellow); //blue red green yellow
                screen.repaint();
        
                try {
                    //sleep 3 seconds
                    Thread.sleep(500);
                } 
        
                catch (InterruptedException e) {}
        
                screen.updateColors(false, false, false, false);
                screen.repaint();
                
                try {
                    //sleep 3 seconds
                    Thread.sleep(150);
                } 
        
                catch (InterruptedException e) {}
                j++;
            }
            try {
                    //sleep 3 seconds
                    Thread.sleep(1000);
            } 
        
            catch (InterruptedException e) {}
            i++;
        }
        
        
        //screen.setGameOver();
        screen.repaint();
        
        System.out.println("End Play");
    }
    
    // =========================================================================
    /**
     * 
     */
    private void setupMenu() {
        menu.add(newGame);
        menuBar.add(menu);
    }
    
    // =========================================================================
    /**
     * 
     */
    private void setup() {
        
        setupMenu();
        setJMenuBar(menuBar);
        newGame.addActionListener(this);
        
        setTitle("Simon Says");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(screenSize.width / 2, screenSize.height / 2);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new GridLayout(1,1));
        setLocationRelativeTo(null);
        panels();
        setResizable(true);
        setVisible(true);
        repaint();
        revalidate();
    }
    
    // =========================================================================
    /**
     * 
     */
    private void panels() {
        screen.setBackground(Color.black);
        add(screen);
    }

    // =========================================================================
    // ActionListener
    // =========================================================================
    @Override
    public void actionPerformed(ActionEvent e) {
        //If new game menu item selected, start a new game
        if (e.getSource() == newGame) {
            System.out.println("New Game");
            //Thread.currentThread().interrupt();
            gameOver();
            try {
                    //sleep 3 seconds
                    Thread.sleep(500);
            } 
        
            catch (InterruptedException ex) {}
            
            screen.updateColors(false, false, false, false);
            screen.repaint();
            play();
        }
    } 
    
    // =========================================================================
    // ComponentListener
    // =========================================================================
    @Override
    public void componentResized(ComponentEvent e) {
        screen.setSize(getWidth(), getHeight());
        screen.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    // =========================================================================
    // MouseListener
    // =========================================================================
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

// =============================================================================
//  Runnable Class
// =============================================================================
/**
 * 
 * @author Mike Brice - Couchoutput Studios
 */
class MyRunnable implements Runnable {

    //Fields
    private final Screen screen;
    private final Random rand;
    private boolean blue, red, green, yellow;
    
    // =========================================================================
    /**
     * 
     * @param screen the JPanel that is displaying the game
     */
    public MyRunnable(Screen screen) {
        this.screen = screen;
        rand = new Random();
        blue = false;
        red = false;
        green = false;
        yellow = false;
    }
    
    // =========================================================================
    @Override
    public void run() {
        screen.updateColors(blue, red, green, yellow);
        screen.repaint();
        
        try {
                //sleep 3 seconds
                Thread.sleep(500);
            } 
        
        catch (InterruptedException e) {}
        
        //Amount of lights to turn on per round
        for (int i = 1; i < 10; i++) {
            
            //Turning on the lights
            for (int j = 0; j < i; j++) {
                
                blue = false;
                red = false;
                green = false;
                yellow = false;
                
                int color = rand.nextInt(4);
                
                switch (color) {
                    case 0:
                        blue = true;
                        break;
                    case 1:
                        red = true;
                        break;
                    case 2:
                        green = true;
                        break;
                    default:
                        yellow = true;
                        break;
                }
                
                screen.updateColors(blue, red, green, yellow); //blue red green yellow
                screen.repaint();
        
                try {
                    //sleep 3 seconds
                    Thread.sleep(500);
                } 
        
                catch (InterruptedException e) {}
        
                screen.updateColors(false, false, false, false);
                screen.repaint();
                
                try {
                    //sleep 3 seconds
                    Thread.sleep(150);
                } 
        
                catch (InterruptedException e) {}
            }
            try {
                    //sleep 3 seconds
                    Thread.sleep(1000);
            } 
        
            catch (InterruptedException e) {}
        }
        
        
        //screen.setGameOver();
        screen.repaint();
    }
}
