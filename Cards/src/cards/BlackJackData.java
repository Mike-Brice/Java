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
 * This program is intended to be an education tool.
 *
 * -----------------------------------------------------------------------------
 *
 * This Class is for storing data for the BlackJack class. It is supposed to run
 * in parallel with the BlackJackPanel and BlackJack Classes.
 */
package cards;

import java.util.ArrayList;

/**
 *
 * @author Mike Brice - Couchoutput Studios
 */
class BlackJackData {
    
    // =========================================================================
    // Fields
    // =========================================================================
    private final ArrayList<Card> hand = new ArrayList();
    private final String player;
    private boolean playable;
    
    // =========================================================================
    // Constructors
    // =========================================================================
    
    /**
     * 
     * @param player name of the player
     */
    public BlackJackData(String player) {
        this.playable = true;
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
    // Printers
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * Prints the Hand to the terminal
     */
    public void printHand() {
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i +": " +hand.get(i).toString() +"'s");
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
