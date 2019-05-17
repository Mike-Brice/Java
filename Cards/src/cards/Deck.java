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
 * This class is intended to work with the Card class as well as be the 
 * foundation for all the card games found in this package.
 */
package cards;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Mike Brice - Couchoutput Studios
 */
public class Deck {

    // =========================================================================
    // Fields
    // =========================================================================
    private final String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; //13 Values
    private final String joker = "Joker";
    private final String[] suits = {"Spade", "Club", "Heart", "Diamond"}; //4 Suits
    private int deckId = 0;
    private final int numberCards;

    private final ArrayList<Card> deck;

    // =========================================================================
    // Constructors
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /** 
     *
     * @param numberJokers the number of jokers in the deck
     */
    public Deck(int numberJokers) {
        this.numberCards = 52 + numberJokers;

        deck = new ArrayList(numberCards);

        createDeck();
    }

    // -------------------------------------------------------------------------
    /** 
     *
     * @param numberJokers numberJokers the number of jokers in the deck
     * @param deckId the deck identification if there are multiple decks in use
     */
    public Deck(int numberJokers, int deckId) {
        this.deckId = deckId;
        this.numberCards = 52 + numberJokers;

        deck = new ArrayList(numberCards);

        createDeck();
    }
    
    // -------------------------------------------------------------------------
    /**
     * 
     * @param numberJokers numberJokers the number of jokers in the deck
     * @param graphic true if there is a graphical card
     */
    public Deck(int numberJokers, boolean graphic) {
        this.numberCards = 52 + numberJokers;

        deck = new ArrayList(numberCards);

        createDeckGraphic();
    }
    
    // =========================================================================
    // Deck Creators
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /** 
     * Creates the deck
     */
    private void createDeck() {
        
        //The value iterator
        int j = 0;
        
        //The suit iterator
        int k = 0;
        
        //Adds the standard 52 cards to the deck
        for (int i = 0; i < 52; i++) {

            deck.add(new Card(values[j], suits[k]));

            j++;
            if (j == 13) {
                j = 0;
                k++;
            }
        }
        
        //Adds the jokers to the deck if they are in use
        if (numberCards > 52) {
            for (int i = 52; i < numberCards; i++) {
                deck.add(new Card(joker, ""));
            }
        }
    }
    
    // -------------------------------------------------------------------------
    /**
     * Creates the deck with a graphical representation
     */
    private void createDeckGraphic() {
        
        //The value iterator
        int j = 0;
        
        //The suit iterator
        int k = 0;
        
        //Adds the standard 52 cards to the deck
        for (int i = 0; i < 52; i++) {

            try {
                File card = new File(values[j] + suits[k] + ".png");
                Image image = ImageIO.read(card);
                deck.add(new Card(values[j], suits[k], image));

                j++;
                if (j == 13) {
                    j = 0;
                    k++;
                }
            }
            catch (IOException e) {
                //Do Nothing
            }
        }
        
        //Adds the jokers to the deck if they are in use
        if (numberCards > 52) {
            for (int i = 52; i < numberCards; i++) {
                deck.add(new Card(joker, ""));
            }
        }
    }
    
    // =========================================================================
    // Getters
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @return the size of the deck
     */
    public int getSizeOfDeck() {
        return deck.size();
    }
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @return the identification of the deck.... if 0 then only one deck is un use
     */
    public int getDeckId() {
        return deckId;
    }
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @param index index within the deck of the card to be returned
     * @return the card at the specified index 
     */
    public Card getCard(int index) {
        return deck.get(index);
    }
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @param index index within the deck of the card to be returned and removed from the deck
     * @return the card at the specified index 
     */
    public Card getRemoveCard(int index) {
        return deck.remove(index);
    }
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @param value the value of the card to be retrieved
     * @param suit the suit of the card to be retrieved 
     * @return returns the index of the specified card... if -1 then the card is not within the deck
     */
    public int getIndexOfCard(String value, String suit) {
        
        for (int i = 0; i < numberCards; i++) {
            if (deck.get(i).getValue().equals(value) && deck.get(i).getSuit().equals(suit)) {
                return i;
            }
        }
        
        return -1;
    }

    // =========================================================================
    // Shufflers
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /** 
     * Shuffles the Deck one time
     */
    private void shuffleDeck() {
        Random rnd = new Random();
        for (int i = deck.size() - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Card a = deck.get(index);
            deck.set(index, deck.get(i));
            deck.set(i, a);
        }
    }
    
    // -------------------------------------------------------------------------
    /**
     * 
     * @param n the number of times to shuffle the deck
     */
    public void shuffledDeck(int n) {
        
        //Shuffles n times
        for (int i = 0; i < n; i++) {
            shuffleDeck();
        }
    }
    
    // -------------------------------------------------------------------------
    /** 
     * Shuffles the deck 7 times for a truly random deck
     */
    public void trulyShuffledDeck() {
        
        //Shuffles 7 times
        for (int i = 0; i < 7; i++) {
            shuffleDeck();
        }
    }
    
    // =========================================================================
    // Is Empty
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @return true if the deck is empty, false if not
     */
    public boolean isEmpty() {
        return deck.isEmpty();
    }
    
    // =========================================================================
    // Printer
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /** 
    * Testing purposes
    */
    public void printDeck() {
        for (int i = 0; i < numberCards; i++) {
            System.out.println(deck.get(i).toString());
        }
    }
    
    // =========================================================================
    // Overrides
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /** 
     * 
     * @return the String representation of the Deck
     */
    @Override
    public String toString() {
        String str = deck.get(0).toString();
        
        for (int i = 1; i < numberCards; i++) {
            str = str.concat("\n" +deck.get(i).toString());
        }
        
        return str;
    }
}
