package pong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @author Mike Brice
 */
public class PongGUI extends JFrame implements ActionListener, KeyListener {

    //Fields
    //Game Data
    private Ball ball;
    private Paddle paddleA;
    private Paddle paddleB;
    private Momentum momentum;
    private final Random rand = new Random();
    private final int[] directions = {4, -4};

    //Frame Data
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private ImageIcon image;
    ExecutorService executor = Executors.newFixedThreadPool(16);


    //Menus
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menu = new JMenu("File");
    private final JMenuItem newGame = new JMenuItem("New Game");
    private final JMenuItem controls = new JMenuItem("Controls");

    //Panels
    private final JPanel container = new JPanel(new BorderLayout());
    private final JPanel top = new JPanel();
    private final JPanel bottom = new JPanel();
    private final JPanel left = new JPanel();
    private final JPanel right = new JPanel();
    private final JFrame cont = new JFrame("Controls");
    private final Board board = new Board();

    /**
     * 
     */
    public void start() {
        setupMenu();
        setJMenuBar(menuBar);
        
        newGame.addActionListener(this);
        controls.addActionListener(this);
        addKeyListener(this);
        setup();
        play();

        repaint();
        revalidate();
    }

    private void play() {
        ball = new Ball(new Position(board.getWidth() / 2, board.getHeight() / 2)); //set the ball to be in the center of the board
        board.setPaddles(paddleA.getPaddle().getY(), paddleB.getPaddle().getY()); //set the paddles to their initial positions
        momentum.setPaddles(paddleA, paddleB); //set the paddles to their initial positions
        ball.setMomentum(momentum);
        while (true) {
            try {
                //sleep 3 seconds
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            board.setBallPosition(ball.getPosition()); //update the ball position on the board
            momentum.setCurrentPosition(ball.getPosition());
            executor.execute(momentum);
            ball.updatePosition(momentum.nextPosition()); //update the ball position based on the next position determined by momentum
            //ball.updatePosition(momentum.nextPosition(ball.getPosition())); //update the ball position based on the next position determined by momentum

            //If the ball is in the left goal, left wins
            if (board.isLeftGoal()) {
                System.out.println("Left Wins");
                break;
            }

            //If the ball is in the right goal, right wins
            if (board.isRightGoal()) {
                System.out.println("Right Wins");
                break;
            }
            repaint();
            revalidate();
        }
    }

    private void setupMenu() {
        menu.add(newGame);
        menu.add(controls);
        menuBar.add(menu);
    }

    private void setup() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(screenSize.width, screenSize.height);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        panels();
        setResizable(true);
        setVisible(true);
        momentum = new Momentum(directions[rand.nextInt(2)], directions[rand.nextInt(2)], 0, board.getHeight());
        paddleA = new Paddle(new Position(40, board.getHeight() / 2 + 10));
        paddleB = new Paddle(new Position(board.getWidth() - 50, board.getHeight() / 2 + 10));
    }

    /**
     * sets up the panels
     */
    private void panels() {
        //Set color of sub-panels
        top.setBackground(new Color(7, 120, 108));
        bottom.setBackground(new Color(7, 120, 108));
        left.setBackground(new Color(7, 120, 108));
        right.setBackground(new Color(7, 120, 108));
        board.setBackground(Color.black);
        container();
        add(container);
    }

    /**
     * sets up the container panel
     */
    private void container() {

        //Add panels to the container panel
        container.add(top, BorderLayout.NORTH);
        container.add(bottom, BorderLayout.SOUTH);
        container.add(left, BorderLayout.EAST);
        container.add(right, BorderLayout.WEST);
        container.add(board, BorderLayout.CENTER);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        //If new game menu item selected, start a new game
        if (e.getSource() == newGame) {
            System.out.println("hello");
            panels();
            momentum = new Momentum(directions[rand.nextInt(2)], directions[rand.nextInt(2)], 0, board.getHeight());
            ball.updatePosition(new Position(board.getWidth() / 2, board.getHeight() / 2));
            board.setBallPosition(ball.getPosition());
            paddleA = new Paddle(new Position(40, board.getHeight() / 2 + 10));
            paddleB = new Paddle(new Position(board.getWidth() - 50, board.getHeight() / 2 + 10));
            repaint();
            revalidate();
            play();
        }

        //If controls menu item selected, open control window
        if (e.getSource() == controls) {
            image = new ImageIcon(getClass().getResource("control.png"));
            JLabel controlsLabel = new JLabel(image);
            cont.getContentPane().add(controlsLabel);
            cont.setSize(screenSize.width / 2, screenSize.height / 2);
            cont.setLocationRelativeTo(null);
            cont.setResizable(false);
            cont.setVisible(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            paddleA.updatePaddle(-20);
            board.setPaddles(paddleA.getPaddle().getY(), paddleB.getPaddle().getY());
            momentum.setPaddles(paddleA, paddleA);
            repaint();
            revalidate();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_S) {
            paddleA.updatePaddle(20);
            board.setPaddles(paddleA.getPaddle().getY(), paddleB.getPaddle().getY());
            momentum.setPaddles(paddleA, paddleB);
            repaint();
            revalidate();
        }
        
        //If UP key is pressed move the right paddle up
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            paddleB.updatePaddle(-20);
            board.setPaddles(paddleA.getPaddle().getY(), paddleB.getPaddle().getY());
            momentum.setPaddles(paddleA, paddleB);
            repaint();
            revalidate();
        }

        //If DOWN key is pressed move the right paddle down
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            paddleB.updatePaddle(20);
            board.setPaddles(paddleA.getPaddle().getY(), paddleB.getPaddle().getY());
            momentum.setPaddles(paddleA, paddleB);
            repaint();
            revalidate();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }
}