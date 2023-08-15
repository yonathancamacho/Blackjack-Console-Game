package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a player in the Blackjack game.
 * 
 * @author Yonathan Camacho, 2023
 */
public class BlackjackPlayer extends Player {

    /** The list of cards in the player's hand. */
    private ArrayList<BlackjackCard> hand;

    /** The player's score based on the cards in their hand. */
    private int score;

    /** The card that remains hidden until the player decides to reveal it. */
    private BlackjackCard hiddenCard;

    /**
     * Constructs a new Blackjack player with a given name.
     *
     * @param name the name of the player.
     */
    public BlackjackPlayer(String name) {
        super(name);
        hand = new ArrayList<>();
        score = 0;
    }

    /**
     * Adds a card to the player's hand.
     * The first card added will be considered as a hidden card.
     *
     * @param card the card to add to the hand.
     */
    public void addCardToHand(BlackjackCard card) {
        if (hiddenCard == null && hand.isEmpty()) {
            hiddenCard = card;
        } else {
            hand.add(card);
            calculateScore();
        }
    }

    /**
     * Reveals the hidden card and adds it to the beginning of the hand.
     */
    public void revealHiddenCard() {
        if (hiddenCard != null) {
            hand.add(0, hiddenCard);
            hiddenCard = null;
            calculateScore();
        }
    }

    /**
     * Calculates the score of the player's hand based on Blackjack rules.
     */
    private void calculateScore() {
        int total = 0;
        int aceCount = 0;
        for (BlackjackCard card : hand) {
            if (card.toString().startsWith("A")) {
                aceCount++;
                total += 11;
            } else if (card.toString().startsWith("K") ||
                    card.toString().startsWith("Q") ||
                    card.toString().startsWith("J")) {
                total += 10;
            } else {
                total += card.getValue();
            }
        }

        // Adjusting for Aces if the total is greater than 21
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        this.score = total;
    }

    /**
     * Determines if the player's score is above 21 (busted).
     *
     * @return true if the player is busted, false otherwise.
     */
    public boolean isBusted() {
        return score > 21;
    }

    /**
     * Determines if the player has a Blackjack (a score of 21).
     *
     * @return true if the player has a Blackjack, false otherwise.
     */
    public boolean hasBlackjack() {
        return score == 21;
    }

    /**
     * Facilitates the player's turn, allowing them to hit, stand, or view the
     * hidden card.
     *
     * @param deck the deck of cards to draw from.
     */
    @Override
    public void play(BlackjackDeck deck) {
        Scanner scanner = Main.SCANNER;
        String decision;

        do {
            System.out.println("Your current hand: " + getHand());
            System.out.println("Your current score: " + getScore());
            System.out.println("Do you want to hit, stand, or view hidden card? (Enter 'hit', 'stand' or 'view')");

            decision = scanner.nextLine().toLowerCase();
            System.out.println();

            if ("hit".equals(decision)) {
                BlackjackCard drawnCard = deck.drawCard();
                addCardToHand(drawnCard);
                System.out.println("You drew: " + drawnCard);

                // If drawn card is Ace, ask the player to decide its value
                if (drawnCard.toString().startsWith("A")) {
                    System.out.println("You drew an Ace! Would you like its value to be 1 or 11?");
                    int choice;
                    while (true) {
                        try {
                            choice = Integer.parseInt(Main.SCANNER.nextLine());
                            if (choice == 1 || choice == 11) {
                                // Logic to set Ace value
                                if (choice == 1) {
                                    // Set Ace value to 1
                                    setAceValue(1, drawnCard);
                                }
                                break;
                            } else {
                                System.out.println("Invalid choice. Please enter 1 or 11.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid choice. Please enter 1 or 11.");
                        }
                    }
                }

                if (isBusted()) {
                    System.out.println("You busted with a score of " + getScore() + "!");
                    break;
                }
            } else if ("stand".equals(decision)) {
                System.out.println("You decided to stand with a score of " + getScore() + ".");
                break; // Explicitly break out of the loop
            } else if ("view".equals(decision)) {
                revealHiddenCard();
                System.out.println("Your hidden card was: " + getHand().get(0));
            } else {
                System.out.println("Invalid choice. Please enter 'hit' or 'stand'.");
            }
        } while (!"stand".equals(decision) && !isBusted());
        clearScreen();
    }

    /**
     * @return the list of cards in the player's hand.
     */
    public ArrayList<BlackjackCard> getHand() {
        return hand;
    }

    /**
     * Sets the list of cards in the player's hand and recalculates the score.
     *
     * @param hand the new list of cards for the player.
     */
    public void setHand(ArrayList<BlackjackCard> hand) {
        this.hand = hand;
        calculateScore();
    }

    /**
     * @return the player's current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the value for an Ace card and recalculates the score.
     *
     * @param value the value to set for the Ace.
     * @param card  the Ace card to set the value for.
     */
    private void setAceValue(int value, BlackjackCard card) {
        card.setValue(value);
        calculateScore();
    }

    /**
     * Resets the player's hand and score for a new game.
     */
    public void resetHand() {
        hand.clear();
        hiddenCard = null;
        score = 0;
    }

    /**
     * Clears the terminal screen.
     */
    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

//    public static void clearScreen() {
//        try {
//            if (System.getProperty("os.name").contains("Windows")) {
//                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//            } else {
//                System.out.print("\033[H\033[2J");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
