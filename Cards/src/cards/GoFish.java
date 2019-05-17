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
 * This class is for the game mechanics of GoFish. This class is intended to 
 * work with the GoFishPlayer class.
 */
package cards;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Mike Brice - Couchoutput Studios
 */
public class GoFish {

    // =========================================================================
    // Fields
    // =========================================================================
    private final int numberOfPlayers; //Total number of players in the game
    private final GoFishPlayer[] playerData; //An array of GoFishPlayer, which stores the data for each player
    private final Deck deck; //The deck to be used by the game
    private final Random rand; //A random number generator to be used for deteriming who is the dealer and for the bots
    private final int[] pairs; //An array that stores the total number of pairs each player had
    private final Scanner keyboard; //A Scanner for the user to enter in their moves
    private final int sizeOfStartingHand; //The size of each players starting hand and the size of hand to be drawn from the deck when a player runs out of cards
    private final ExecutorService executor; //Used for parallel threads

    // =========================================================================
    // Constructors
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     *
     * @param numberOfPlayers number of players in the game
     * @param sizeOfStartingHand the starting size of the initial hand
     */
    public GoFish(int numberOfPlayers, int sizeOfStartingHand) {
        this.executor = Executors.newFixedThreadPool(numberOfPlayers * 2); //Creates the thread pool
        keyboard = new Scanner(System.in); //A Scanner for the user to enter in their moves
        this.numberOfPlayers = numberOfPlayers; //Sets the number of players in the game
        this.sizeOfStartingHand = sizeOfStartingHand; //Sets the size of the starting hand
        playerData = new GoFishPlayer[numberOfPlayers]; //Initializes the size of the playerData array
        deck = new Deck(0); //Initializes a deck with 0 Jokers
        deck.trulyShuffledDeck(); //Truly Shuffles the deck
        rand = new Random(); //A random number generator to be used for deteriming who is the dealer and for the bots moves
        pairs = new int[numberOfPlayers]; //Stores the pairs from each player
        makePlayerData(); //Makes the GoFishPlayer objects
        int dealer = rand.nextInt(numberOfPlayers); //The player that is playing first and gets to be dealt the first card
        dealer(dealer); //Sets up the order of the player's turns
        makeHands(); //Makes the initial hands for each player
    }

    // =========================================================================
    // Greeting
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     *
     * @return the greeting for Go Fish!
     */
    private String greeting() {
        return "Hello and Welcome to Go Fish! You will be playing againt " + (numberOfPlayers - 1) + " Automated Players.\n";
    }

    // =========================================================================
    // Initializers
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * Makes the GoFishData, which stores each player's information
     */
    private void makePlayerData() {
        playerData[0] = new GoFishPlayer("Human");
        for (int i = 1; i < numberOfPlayers; i++) {
            playerData[i] = new GoFishPlayer("Bot" + i);
        }
    }

    // -------------------------------------------------------------------------
    /**
     * Sets up the order of players based on the random dealer
     */
    private void dealer(int dealer) {

        //Takes the left of the dealer into a sub array left
        GoFishPlayer[] left = Arrays.copyOfRange(playerData, 0, dealer);

        //Takes the right of the dealer including the dealer into a sub array right
        GoFishPlayer[] right = Arrays.copyOfRange(playerData, dealer, playerData.length);

        //Copies the two sub arrays into data... right goes to left and left goes to right
        System.arraycopy(right, 0, playerData, 0, right.length);
        System.arraycopy(left, 0, playerData, right.length, left.length);
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
        for (int i = 0; i < sizeOfStartingHand; i++) {

            //Deals out a new card for each player
            for (int j = 0; j < numberOfPlayers; j++) {

                //Adds the card to the GoFishPlayer object and gets the card from the deck
                playerData[j].addCard(deck.getRemoveCard(0));

            }
        }
    }

    // =========================================================================
    // Play Method
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * Plays the game of Go Fish!
     */
    public void play() {
        
        System.out.println(greeting());
        
        //Prints the order of turns that the players will play in
        System.out.println("The order of turns are: Starting from 0 to " + (numberOfPlayers - 1));
        printOrder();
        System.out.println("\u001b[35m--------------------------------\n");
        
        
        /*for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println(playerData[i].getPlayer());
            playerData[i].printHand();
        }
        System.out.println("\u001b[35m--------------------------------\n");*/
        //Removes the initial pairs in the hands
        initialPairs();

        /*for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println(playerData[i].getPlayer());
            playerData[i].printHand();
        }*/
        //Runs the game
        while (true) {
            
            //If the deck is empty the game is over
            if (deck.getSizeOfDeck() == 0) {
                System.out.println("Game Over");
                insertSort();
                displayResults();
                break;
            }

            //Iterates for each player before looping back to the top
            for (int i = 0; i < numberOfPlayers; i++) {

                //The user gets to play if the player is set to Human
                if (playerData[i].getPlayer().equals("Human")) {

                    //If the player is playable then they play | A playable player has a hand of cards
                    if (playerData[i].getPlayable()) {
                        System.out.println("\u001b[36mPlayer: " + playerData[i].getPlayer() + " it's your turn!");
                        
                        //Prints the order of players 
                        printOrder();

                        //Prints the players hand
                        System.out.println("\n\u001b[36mYour Hand");
                        playerData[i].printHand();

                        //Asks the user to select which player to ask for a card
                        System.out.print("\u001b[36mPlease enter a player number to play against. A number between 0 and " + (numberOfPlayers - 1) +": ");
                        int pick = keyboard.nextInt();

                        /* 
                        * If the user enters in their own player number then it will ask again or 
                        * if the user enters a number greater then or equal to the number of players or 
                        * the user selects a player with no cards 
                        */
                        while (pick == i || !playerData[i].getPlayable() || pick >= numberOfPlayers) {
                            
                            //If the user picks themselve
                            if (pick == i) {
                                System.out.print("\u001b[36mYou can't choose yourself. Please enter a player number to play against. A number between 0 and " + (numberOfPlayers - 1) +": ");
                            }
                            
                            //If the user picks a player with no cards
                            else if (!playerData[i].getPlayable()) {
                                System.out.print("\u001b[36mYou chose a player with no cards. Please enter a player number to play against. A number between 0 and " + (numberOfPlayers - 1) +": ");
                            }
                            //If the user doesn't pick an available player
                            else {
                                System.out.print("\u001b[36mYou didn't choose a player. Please enter a player number to play against. A number between 0 and " + (numberOfPlayers - 1) +": ");
                            }
                            pick = keyboard.nextInt(); //User input
                        }

                        //Asks them which card they would like to try and pair up. 
                        System.out.print("\u001b[36mPlease enter a card that you would like to pair up: ");
                        int card = keyboard.nextInt();
                        
                        while (card < 0 || card > playerData[i].getSizeOfHand()-1) {
                            System.out.print("\u001b[36mError: Please enter a card that you would like to pair up: ");
                            card = keyboard.nextInt();
                        }

                        //Prints the classic "Do you have any _______" line
                        System.out.println("\u001b[36m" + playerData[pick].getPlayer() + " do you have any " + playerData[i].peekCard(card).getValue() + "'s");

                        //Checks if the other player has a pairng card. If true he gives up the card
                        if (playerData[pick].isPair(playerData[i].peekCard(card))) {
                            System.out.println("\u001b[36mHere you go");
                            
                            //Removes the card from the picked player and the playing player
                            playerData[pick].removeCard(playerData[i].getRemoveCard(card)); 
                            
                            //Checks if either player now has an empty hand | If so then the player that just played draws a new hand first
                            handEmpty(i);
                            handEmpty(pick);
                        } 

                        //Other player does not have card and the user has to draw a new card from the deck
                        else {
                            
                            //Checks if the deck is empty. If so then do nothing
                            if (deck.isEmpty()) {
                                System.out.println("\u001b[36mGo Fish! \nThe Deck is Empty");
                            }
                            
                            //If the deck is not empty, the user draws a card from the deck
                            else {
                                playerData[i].addCard(deck.getRemoveCard(0));
                                System.out.println("\u001b[36mGo Fish! You drew " + playerData[i].peekCard(playerData[i].getSizeOfHand() - 1) +"'s");
                            }

                            //Checks to see if the drawn card is a pair
                            checkForPairs(i);
                            
                            //If the drawn card is a pair and it removes the last of the hand, the user gets a new hand
                            handEmpty(i);
                        }
                    }

                    //If the user cannot play then its because the deck is out of cards and their hand is empty
                    else {
                        System.out.println("\u001b[36mSorry " +playerData[i].getPlayer() +", you ran out of cards and the deck is empty");
                    }
                } 

                //A bot gets to play if the player is not set to Human
                else {
                    
                    //If the player is playable then they play | A playable player has a hand of cards
                    if (playerData[i].getPlayable()) {

                        System.out.println("Player: " + playerData[i].getPlayer() + " it's your turn!");
                        //The Bot randomly selects a player
                        int pick = rand.nextInt(numberOfPlayers);

                        /* 
                        * If the bot enters in their own player number then it will ask again or  
                        * if the bot selects a player with no cards 
                        */
                        while (pick == i || !playerData[i].getPlayable()) {
                            pick = rand.nextInt(numberOfPlayers);
                        }

                        //The bot will randomly choose one of its cards to try and pair up
                        int card = rand.nextInt(playerData[i].getSizeOfHand());

                        //Prints the classic "Do you have any _______" line
                        System.out.println(playerData[pick].getPlayer() + " do you have any " + playerData[i].peekCard(card).getValue() + "'s");

                        //Checks if the other player has a pairng card. If true he gives up the card
                        if (playerData[pick].isPair(playerData[i].peekCard(card))) {
                            System.out.println("Here you go");
                            
                            //Removes the card from the picked player and the playing player
                            playerData[pick].removeCard(playerData[i].getRemoveCard(card));
                            
                            //Checks if either player now has an empty hand | If so then the player that just played draws a new hand first
                            handEmpty(i);
                            handEmpty(pick);
                        } 
    
                        //Other player does not have card and the bot has to draw a new card from the deck
                        else {
                            System.out.println("Go Fish!");
                            
                            //Checks if the deck is empty. If so then do nothing
                            if (deck.isEmpty()) {
                                System.out.println("The Deck is Empty");
                            }
                            
                            //If the deck is not empty, the bot draws a card from the deck
                            else {
                                playerData[i].addCard(deck.getRemoveCard(0));
                            }
                            
                            //Checks if the drawn card is a pair
                            checkForPairs(i);
                            
                            //If the drawn card is a pair and it removes the last of the hand, the bot gets a new hand
                            handEmpty(i);
                        }
                    }
                    
                    //If the bot cannot play then its because the deck is out of cards and their hand is empty
                    else {
                        System.out.println("Sorry " +playerData[i].getPlayer() +", you ran out of cards and the deck is empty");
                    }
                }
                
                //Formatting 
                System.out.println("\u001b[32m--------------------------------");
            }
        }
    }

    // =========================================================================
    // Pair Checking
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * Removes the initial pairs
     */
    private void initialPairs() {

        //Increments over all the players
        for (int i = 0; i < numberOfPlayers; i++) {
            
            //Makes a runnable object | A new Thread
            Runnable worker = new MyRunnable(playerData[i]);
            
            //Adds the thread to the work flow and executes the thread
            executor.execute(worker);
        }
        
        //Begins the shutdown of all threads. Shut down is when the thread completes its tasks
        executor.shutdown();
        
        //Wait until all threads are finish
        while (!executor.isTerminated()) {
        }
    }

    // -------------------------------------------------------------------------
    /**
     * Checks if the player got a pair after drawing a card from the deck
     * @param i the index of the playerData array | indicating which player it is checking for pairs
     */
    private void checkForPairs(int i) {
        
        //Increments the left pointer over all the cards except the last card
        for (int left = 0; left < playerData[i].getSizeOfHand() - 1; left++) {

            //Increments the right pointer over all the cards to the right of the left pointer
            for (int right = left + 1; right < playerData[i].getSizeOfHand(); right++) {

                //If the left pointer equals the right pointer then remove the cards
                if (playerData[i].peekCard(left).equalsValue(playerData[i].peekCard(right))) {
                    
                    //Remove the card at the left pointer
                    playerData[i].removeCardAtIndex(left); 
                    
                    //Remove the card at the right pointer - 1
                    //This is because when the left pointer is removed the size of the ArrayList srinks by 1 and therefor the right pointer needs to shrink by 1 to compensate
                    playerData[i].removeCardAtIndex(right-1); 
                    
                    //Increment the pair counter
                    playerData[i].incrementPairs();
                    
                    
                    if (playerData[i].getPlayer().equals("Human")) {
                        //Print that the user got a pair
                        System.out.println("\u001b[36mI got a pair!");
                    }
                    
                    else {
                        //Print that the bot got a pair
                        System.out.println("I got a pair!");
                    }
                }
            }
        }
    }
    
    // =========================================================================
    // Hand Empty
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * Checks if the player's hand is empty and if so gives them a new hand
     */
    private void handEmpty(int i) {
        
        //If the hand is not empty then do nothing
        if (playerData[i].getSizeOfHand() > 0) {
            
        }
        
        //If the hand is empty then deal out a new hand or remove the player from the game
        else {
            
            //If the deck size is less than the starting hand size then deal out the raminding cards to the hand
            if (deck.getSizeOfDeck() < sizeOfStartingHand && deck.getSizeOfDeck() !=0) {
                
                //Deals out a new hand for the player with an empty hand with the remainding cards
                for (int j = 0; j < deck.getSizeOfDeck(); j++) {

                    //Adds the card to the GoFishPlayer object and gets the card from the deck
                    playerData[i].addCard(deck.getRemoveCard(0));
                }
            }
            
            //If the deck is empty then remove the player from the game
            else if (deck.getSizeOfDeck() == 0) {
                playerData[i].setPlayable();
            }
            
            //If neither the deck is empty and the deck is smaller than the starting hand size then deal out a normal hand
            else {
                
                //Deals out a new hand for the player with an empty hand of the same size as the starting hand
                for (int j = 0; j < sizeOfStartingHand; j++) {

                    //Adds the card to the GoFishPlayer object and gets the card from the deck
                    playerData[i].addCard(deck.getRemoveCard(0));
                }
            }
        }
    }
    
    // =========================================================================
    // Array Sorting
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * Sort the pairs array to rank the players
     */
    private void insertSort() {
        //*NOTE* Insert Sort is only efficient if the array size is small
        
        //Loads the pairs into the pairs array
        for (int i = 0; i < numberOfPlayers; i++) {
            pairs[i] = playerData[i].getNumberOfPairs();
        }
        
        //Sorts the pairs and playerData arrays
        for (int i = 1; i < pairs.length; i++)
        {
            int key = pairs[i];
            GoFishPlayer temp = playerData[i];
            int j = i-1;
 
            /* 
            * Move elements of pairs[0..i-1], that are
            * greater than key, to one position ahead
            * of their current position 
            */
            while (j >= 0 && pairs[j] > key)
            {
                playerData[j+1] = playerData[j];
                pairs[j+1] = pairs[j];
                j = j-1;
            }
            playerData[j+1] = temp;
            pairs[j+1] = key;
        }
    }
    
    // =========================================================================
    // Printers
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * Displays the results of the game
     */
    private void displayResults() {
        
        String place;
        
        System.out.println("Results:");
        
        //Prints each players results in order from most pairs to least pairs
        for (int i = playerData.length - 1; i > -1; i--) {
            
            //If i is the index of the largest amount of pairs then add the place string
            if (i == playerData.length-1) {
                place = " got first place and";
            }
            
            //If i is not the index of the largest amoung then the string place is empty
            else {
                place = "";
            }
            
            //Prints the result
            System.out.println("    " +playerData[i].getPlayer() +place +" has " +pairs[i] +" pairs");
            
        }
    }

    // -------------------------------------------------------------------------
    /** 
     * For printing the player turn order
     */
    public void printOrder() {
        for (int i = 0; i < playerData.length - 1; i++) {
            System.out.print("\u001b[33m" +i + "\u001b[30m: " +playerData[i].toString() +" with " +playerData[i].getSizeOfHand() +" cards, ");
        }
        System.out.print("\u001b[33m" +(playerData.length - 1) + "\u001b[30m: " + playerData[playerData.length - 1].toString() +" with " +playerData[playerData.length - 1].getSizeOfHand() +" cards");
        System.out.println("");
    }
}

// =============================================================================
// Runnable Class
// =============================================================================

/**
 * Allows the initialPairs method to run in parallel threads
 * @author Mike Brice - Couchoutput Studios
 */
class MyRunnable implements Runnable {
    
    // =========================================================================
    // Fields
    // =========================================================================
    private final GoFishPlayer playerData;
    
    // =========================================================================
    // Constructor
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * 
     * @param playerData GoFishPlayer object
     */
    public MyRunnable(GoFishPlayer playerData) {
        this.playerData = playerData;
    }
    
    // =========================================================================
    // Overrides
    // =========================================================================
    
    // -------------------------------------------------------------------------
    @Override
    public void run() {
        
        //Increments the left pointer over all the cards except the last card
        for (int left = 0; left < playerData.getSizeOfHand() - 1; left++) {

            //Increments the right pointer over all the cards
            for (int right = left + 1; right < playerData.getSizeOfHand(); right++) {

                //If the left pointer equals the right pointer then remove the cards
                if (playerData.peekCard(left).equalsValue(playerData.peekCard(right))) {
                    playerData.removeCardAtIndex(left);
                    playerData.removeCardAtIndex(right - 1);
                    playerData.incrementPairs();
                    left--;
                    break;
                }
            }
        }
    }
}
