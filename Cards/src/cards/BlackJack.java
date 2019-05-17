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
 * This program is intended to be an education tool
 *
 * -----------------------------------------------------------------------------
 *
 * This Class is for the game mechanics of BlackJack. It is supposed to run
 * in parallel with the BlackJackPanel and BlackJackData Classes
 */
package cards;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Mike Brice - Couchoutput Studios
 */
public class BlackJack {
    
    // =========================================================================
    // Fields
    // =========================================================================
    private final int numberOfPlayers = 4;
    private final BlackJackPanel panel;
    private final BlackJackData[] playerData;
    private final Deck deck;
    private final Random rand;
    
    // =========================================================================
    // Constructors
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @param panel the BlackJack panel
     */
    BlackJack(BlackJackPanel panel) {
        this.playerData = new BlackJackData[4];
        this.panel = panel;
        this.deck = new Deck(0, true);
        deck.trulyShuffledDeck();
        rand = new Random(); //A random number generator to be used for deteriming who is the dealer and for the bots moves
        makePlayerData(); //Makes the GoFishPlayer objects
        panel.setBlackJackData(playerData);
        panel.setup();
        int dealer = rand.nextInt(numberOfPlayers); //The player that is playing first and gets to be dealt the first card
        dealer(dealer); //Sets up the order of the player's turns
        makeHands(); //Makes the initial hands for each player
    }
    
    // =========================================================================
    // Game Mechanics
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * Plays the game of BlackJack
     */
    public void play() {
        
    }
    
    private void hitMe() {
        
    }
    
    // -------------------------------------------------------------------------
    /**
     * Makes the BlackJackData, which stores each player's information
     */
    private void makePlayerData() {
        playerData[0] = new BlackJackData("Human");
        for (int i = 1; i < numberOfPlayers; i++) {
            playerData[i] = new BlackJackData("Bot" + i);
        }
    }
    
    // -------------------------------------------------------------------------
    /**
     * Makes the initial hands
     *
     * @param sizeOfStartingHand the size of the starting hand
     */
    private void makeHands() {

        //Dealing out the cards
        //Deals out a new card for each slot in the initial hand
        for (int i = 0; i < 2; i++) {

            //Deals out a new card for each player
            for (int j = 0; j < numberOfPlayers; j++) {

                //Adds the card to the GoFishPlayer object and gets the card from the deck
                playerData[j].addCard(deck.getRemoveCard(0));

            }
        }
    }
    
    // -------------------------------------------------------------------------
    /**
     * Sets up the order of players based on the random dealer
     */
    private void dealer(int dealer) {

        //Takes the left of the dealer into a sub array left
        BlackJackData[] left = Arrays.copyOfRange(playerData, 0, dealer);

        //Takes the right of the dealer including the dealer into a sub array right
        BlackJackData[] right = Arrays.copyOfRange(playerData, dealer, playerData.length);

        //Copies the two sub arrays into data... right goes to left and left goes to right
        System.arraycopy(right, 0, playerData, 0, right.length);
        System.arraycopy(left, 0, playerData, right.length, left.length);
    }
    
    // =========================================================================
    // Getters
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @return the number of players playing BlackJack
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
