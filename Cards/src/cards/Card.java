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
 * This class is intended to work with the Deck class as well as be the 
 * foundation for all the card games found in this package.
 */
package cards;

import java.awt.Image;

/**
 *
 * @author Mike Brice - Couchoutput Studios
 */
public class Card {
    
    // =========================================================================
    // Fields
    // =========================================================================
    private final String value;
    private final String suit;
    private final Image cardGraphic;
    
    // =========================================================================
    // Constructors
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @param value the value of the card
     * @param suit the suit of the card
     */
    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
        cardGraphic = null;
    }
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @param value the value of the card
     * @param suit the suit of the card
     * @param cardGraphic graphic of the card
     */
    public Card(String value, String suit, Image cardGraphic) {
        this.value = value;
        this.suit = suit;
        this.cardGraphic = cardGraphic;
    }
    
    // =========================================================================
    // Getters
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @return the graphic of the card
     */
    public Image getGraphic() {
        return cardGraphic;
    }
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @return the value of the card
     */
    public String getValue() {
        return value;
    }
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @return the suit of the card
     */
    public String getSuit() {
        return suit;
    }
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @return the color of the card | None if the card is a Joker
     */
    public String getColor() {
        switch (suit) {
            case "Spade": //Spades are black
                return "Black";
            case "Club": //Clubs are black
                return "Black";
            case "Heart": //Hearts are red
                return "Red";
            case "Diamond": //Diamonds are red
                return "Red";
            default: //Jokers don't have a color associated with them
                return "None";
        }
    }

    // =========================================================================
    // Equals Methods
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /** 
     *
     * @param card the card to be testes if it equals the value of the card that called this method
     * @return true if they are equal, false if not
     */
    public boolean equalsValue(Card card) {
        return this.value.equals(card.getValue());
    }
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @param card the card to be testes if it equals the suit of the card that called this method
     * @return true if they are equal, false if not
     */
    public boolean equalsSuit(Card card) {
        return this.suit.equals(card.getSuit());
    }
    
    // =========================================================================
    // Override Methods
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @return String representation of the card
     */
    @Override
    public String toString() {
        
        if (value.equals("Joker")) {
            return value;
        }
        return value +" of " +suit;
    }
}
