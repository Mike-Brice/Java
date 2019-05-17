/*
 * Copyright (C) 2018 Couchoutput Studios
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
 *
 * -----------------------------------------------------------------------------
 *
 * This program is intended to be an educational tool
 *
 * -----------------------------------------------------------------------------
 *
 * This Class is for controlling all of the different card games
 */
package cards;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Mike Brice - Couchoutput Studios
 */
public class Cards {
    
    // =========================================================================
    // Fields
    // =========================================================================
    
    // =========================================================================
    // Main
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Cards().run();
    }
    /**
     * runs the driver
     */
    public void run() {
        //Deck deck = new Deck(2);
        //deck.trulyShuffledDeck();
        //System.out.println(deck.toString());
        
        int numberPlayers = 3;
        int handSize = 5;
        GoFish goFish = new GoFish(numberPlayers, handSize);
        goFish.play();
        
        //BlackJack bj = new BlackJack(numberPlayers);
        
        //CardsGUI games = new CardsGUI();
    }
}

// =============================================================================
// CardsGUI Class
// =============================================================================
/**
 * 
 * @author Mike Brice - Couchoutput Studios
 */
class CardsGUI extends JFrame implements ActionListener {

    // =========================================================================
    // Fields
    // =========================================================================
    private final Game game;
    private final JPanel panel;
    private final JPanel container;
    private final JButton[] cardGames;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    // =========================================================================
    // Constructor
    // =========================================================================

    
    // -------------------------------------------------------------------------
    /**
     * Creates the JFrame for the Game Menu
     */
    public CardsGUI() {
        this.cardGames = new JButton[6];
        this.game = new Game();
        this.panel = new JPanel(new GridLayout(2,3,6,6));
        this.container = new JPanel(new BorderLayout());
        setup();
    }
    // =========================================================================
    // Frame Setup and Controls 
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * Sets up the JFrame
     */
    private void setup() {
        setTitle("Couchoutput Studios' Classic Card Games");
        buttons();
        panels();
        ImageIcon icon = new ImageIcon("ASpade.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(screenSize.width / 2, screenSize.height / 2);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new GridLayout(1,1));
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        repaint();
        revalidate();
    }
    
    // -------------------------------------------------------------------------
    /**
     * Sets up the panels for the JFrame
     */
    private void panels() {
        panel.setBackground(Color.black);
        container.add(game, BorderLayout.CENTER);
        container.add(panel, BorderLayout.SOUTH);
        add(container);
    }
    
    // -------------------------------------------------------------------------
    /**
     * Sets up the buttons for the JFrame
     */
    private void buttons() {
        ImageIcon[] icons = new ImageIcon[6]; //BlackJack, Poker, Go Fish, War, Solitaire, Hearts
        icons[0] = new ImageIcon("BlackJack.png");
        icons[1] = new ImageIcon("Poker.png");
        icons[2] = new ImageIcon("GoFish.png");
        icons[3] = new ImageIcon("War.png");
        icons[4] = new ImageIcon("Solitaire.png");
        icons[5] = new ImageIcon("Hearts.png");
        
        for (int i = 0; i < 6; i++) {
            cardGames[i] = new JButton(icons[i]);
            cardGames[i].setBackground(Color.black);
            cardGames[i].addActionListener(this);
            panel.add(cardGames[i]);
        } 
    }
    
    // =========================================================================
    // ActionListener
    // =========================================================================
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cardGames[0]) {
            setTitle("BlackJack");
            remove(container);
            BlackJackPanel panel = new BlackJackPanel();
            add(panel);
            repaint();
            revalidate();
            
            BlackJack blackJack = new BlackJack(panel);
            blackJack.play();
        }
    }
}
