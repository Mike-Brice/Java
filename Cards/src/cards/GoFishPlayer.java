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
 *
 * -----------------------------------------------------------------------------
 *
 * This program is intended to be an education tool.
 *
 * -----------------------------------------------------------------------------
 *
 * This class is for storing individual player data from the GoFish class. 
 * This class is intended to work with the GoFish class.
 */
package cards;

import java.util.ArrayList;

/**
 *
 * @author Mike Brice - Couchoutput Studios
 */
class GoFishPlayer {

    //Fields
    private final ArrayList<Card> hand;
    private int pairs;
    private final String player;
    private boolean playable = true;

    // =========================================================================
    // Constructor
    // =========================================================================
    /**
     *
     * @param player name of the player
     */
    public GoFishPlayer(String player) {
        this.hand = new ArrayList();
        this.player = player;
    }

    // =========================================================================
    // Adders and Setters
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     *
     * @param card the card to be added to the hand
     */
    public void addCard(Card card) {
        hand.add(card);
    }
    
    // -------------------------------------------------------------------------
    /**
     * Sets the player to not playable
     */
    public void setPlayable() {
        playable = false;
    }
    
    // =========================================================================
    // Getters
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * 
     * @param card the card used to find the index of said card value in the hand
     * @return returns the index of the specified card... if -1 then the card is not within the hand
     */
        private int getIndexOfCard(Card card) {
        
        for (int i = 0; i < hand.size(); i++) {
            
            //If the card is in the hand, return the index of the card
            if (hand.get(i).equalsValue(card)) {
                return i;
            }
        }
        
        //If the card is not in the hand, return -1
        return -1;
    }

    // -------------------------------------------------------------------------    
    /**
     *
     * @param index return the card at the specified index of the hand
     * @return the card at the specified index
     */
    public Card peekCard(int index) {
        return hand.get(index);
    }
    
    // -------------------------------------------------------------------------
    /**
     *
     * @param index return the card at the specified index of the hand and remove the card from the hand
     * @return the card at the specified index
     */
    public Card getRemoveCard(int index) {
        return hand.remove(index);
    }
    
    // -------------------------------------------------------------------------
    /**
     *
     * @return the size of the hand
     */
    public int getSizeOfHand() {
        return hand.size();
    }

    // -------------------------------------------------------------------------
    /**
     *
     * @return the number of pairs
     */
    public int getNumberOfPairs() {
        return pairs;
    }

    // -------------------------------------------------------------------------
    /**
     *
     * @return the name of the player
     */
    public String getPlayer() {
        return player;
    }
    
    // -------------------------------------------------------------------------
    /**
     * 
     * @return true if the player can play and false if the player cannot
     */
    public boolean getPlayable() {
            return playable;
    }
    
    // =========================================================================
    // Removers
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * 
     * @param card the card to be removed
     */
    public void removeCard(Card card) {
        int i = getIndexOfCard(card);
        hand.remove(i);
    }
    
    // -------------------------------------------------------------------------
    /**
     * 
     * @param index the index of the card to be removed
     */
    public void removeCardAtIndex(int index) {
        hand.remove(index);
    }

    // =========================================================================
    // Testers
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     *
     * @param card the card to be tested
     * @return true if the card is a pair and false if not
     */
    public boolean isPair(Card card) {
        
        //Looks through the entire hand
        for (Card a : hand) {
            
            //If the card to be paired with equals a card in the hand then return true and increment pairs
            if (a.getValue().equals(card.getValue())) {
                pairs++;
                return true;
            }
        }
        
        //If none of the cards in the hand equals to the card to be paired then return false
        return false;
    }
    
    // =========================================================================
    // Incrementors
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * Increments the Pairs
     */
    public void incrementPairs() {
        pairs++;
    }
    
    // =========================================================================
    // Printers
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * Prints the Hand to the terminal
     */
    public void printHand() {
        for (int i = 0; i < hand.size(); i++) {
            System.out.println("\u001b[33m" +i +"\u001b[30m: " +hand.get(i).toString() +"'s");
        }
        System.out.println("");
    }

    // =========================================================================
    // Overrides
    // =========================================================================
    
    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return player;
    }
}
