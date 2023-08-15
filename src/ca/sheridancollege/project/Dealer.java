package ca.sheridancollege.project;

/**
 * Represents the dealer in the Blackjack game, which inherits from the
 * BlackjackPlayer class.
 * 
 * @author Yonathan Camacho, 2023
 */
public class Dealer extends BlackjackPlayer {

    /**
     * Constructs a new Dealer with the predefined name "Dealer".
     */
    public Dealer() {
        super("Dealer");
    }

    /**
     * Facilitates the dealer's turn.
     * According to Blackjack rules, the dealer must hit until their score is at
     * least 17.
     *
     * @param deck the deck of cards to draw from.
     */
    @Override
    public void play(BlackjackDeck deck) {
        while (getScore() < 17) {
            addCardToHand(deck.drawCard());
        }
    }
}
