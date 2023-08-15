package ca.sheridancollege.project;

import java.util.List;

/**
 * Represents the main game logic for a game of Blackjack. This class extends
 * the general Game class and handles the mechanics specific to Blackjack.
 * 
 * @author Yonathan Camacho, 2023
 */
public class BlackjackGame extends Game {

    // List of players participating in the game
    private List<BlackjackPlayer> players;

    // The deck of cards used in the game
    private BlackjackDeck deck;

    // The dealer, a special type of player in the game
    private Dealer dealer;

    /**
     * Constructs a new Blackjack game with the specified name and list of players.
     *
     * @param name    The name of the game.
     * @param players The list of players participating in the game.
     */
    public BlackjackGame(String name, List<BlackjackPlayer> players) {
        super(name);
        deck = new BlackjackDeck();
        this.players = players;
        dealer = new Dealer();
    }

    /**
     * Begins the game by shuffling the deck, dealing cards to players and the
     * dealer,
     * and determining the winner.
     */
    @Override
    public void play() {
        deck.shuffle();

        // Deal initial cards to all players
        for (BlackjackPlayer player : players) {
            player.addCardToHand(deck.drawCard()); // Deal one hidden card for each player
            player.addCardToHand(deck.drawCard()); // Deal a second visible card
        }

        // Deal initial cards to the dealer
        dealer.addCardToHand(deck.drawCard()); // Deal one hidden card for the dealer
        dealer.addCardToHand(deck.drawCard()); // Deal a second visible card

        // Handle each player's turn
        for (BlackjackPlayer player : players) {
            System.out.println("It's " + player.getName() + "'s turn:");
            player.play(deck);

            if (player.isBusted()) {
                System.out.println(player.getName() + " busted with a score of " + player.getScore() + "!\n");
            } else {
                System.out.println(player.getName() + " stands with a score of " + player.getScore() + ".\n");
            }
        }

        // Handle dealer's turn
        dealer.play(deck);

        // Determine and declare the winner of the game
        declareWinner();
    }

    /**
     * Compares the scores of players and the dealer to determine and announce the
     * winner.
     * The results are based on Blackjack rules:
     * - A player with a score over 21 is considered "busted" and loses to the
     * dealer.
     * - If the dealer is "busted", any player not busted will win.
     * - If neither are busted, the one with the higher score wins.
     * - In case of a tie score, it's declared as a tie.
     */
    @Override
    public void declareWinner() {
        System.out.println("Dealer's score: " + dealer.getScore() + "\n");

        for (BlackjackPlayer player : players) {
            if (player.isBusted()) {
                // Player busted, dealer wins
                System.out.println("Dealer wins against " + player.getName() + "! Player busted with a score of "
                        + player.getScore());
            } else if (dealer.isBusted()) {
                // Dealer busted, player wins
                System.out.println(player.getName() + " wins! Dealer busted with a score of " + dealer.getScore());
            } else if (player.getScore() > dealer.getScore()) {
                // Player has a higher score without busting
                System.out.println(
                        player.getName() + " wins with a score of " + player.getScore() + " to " + dealer.getScore());
            } else if (dealer.getScore() > player.getScore()) {
                // Dealer has a higher score without busting
                System.out.println("Dealer wins against " + player.getName() + " with a score of " + dealer.getScore()
                        + " to " + player.getScore());
            } else {
                // It's a tie
                System.out.println("It's a tie between " + player.getName() + " and the dealer! Both have a score of "
                        + player.getScore());
            }
        }
    }
}
